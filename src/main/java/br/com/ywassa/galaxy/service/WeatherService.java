package br.com.ywassa.galaxy.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ywassa.galaxy.domain.Forecast;
import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.domain.Planet;
import br.com.ywassa.galaxy.domain.Weather;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.exception.DataNotFoundException;
import br.com.ywassa.galaxy.geometric.CircularMotionCalculator;
import br.com.ywassa.galaxy.geometric.Line;
import br.com.ywassa.galaxy.geometric.Point;
import br.com.ywassa.galaxy.geometric.Triangle;
import br.com.ywassa.galaxy.repository.WeatherRepository;

@Service
public class WeatherService {

	private static final Point POINT_SUN = new Point(0.0,0.0);
	private static final long TEN_YEARS = 10L;
	public static final Integer GALAXY_ID = 1;

	@Autowired
	private GalaxyService galaxyService;

	@Autowired
	private WeatherRepository weatherRepository;

	public Weather findByDay(final Long day) {
		if (day < 1) {
			throw new IllegalArgumentException("Day is less than 0");
		}

		final Galaxy galaxy = galaxyService.findById(GALAXY_ID);
		final Weather weather = weatherRepository.findByGalaxyAndDay(galaxy, day);
		if (weather == null) {
			throw new DataNotFoundException("Forecast Not Found");
		}

		return weather;
	}

	@Transactional
	public StatisticDTO forecastForTenYears() {
		final Galaxy galaxy = galaxyService.findById(GALAXY_ID);
		final Long day = galaxy.getDay();
		final LocalDate now = LocalDate.now();
		final LocalDate from = now.plusDays(day);
		final LocalDate to = from.plusYears(TEN_YEARS);
		return calculateForecast(galaxy, from, to);
	}

	public StatisticDTO calculateForecast(final Galaxy galaxy, final LocalDate from, final LocalDate to) {
		if (from.isAfter(to)) {
			throw new IllegalArgumentException();
		}

		final Planet planetA = galaxy.getPlanetOne();
		final Planet planetB = galaxy.getPlanetTwo();
		final Planet planetC = galaxy.getPlanetThree();

		final double angularSpeedInRadA = getAngularSpeedInRadUsingClockwise(planetA);
		final double angularSpeedInRadB = getAngularSpeedInRadUsingClockwise(planetB);
		final double angularSpeedInRadC = getAngularSpeedInRadUsingClockwise(planetC);

		final double distanceInKmA = planetA.getDistanceInKm().doubleValue();
		final double distanceInKmB = planetB.getDistanceInKm().doubleValue();
		final double distanceInKmC = planetC.getDistanceInKm().doubleValue();

		double lastAngleInRadA = planetA.getLastAngleInRad().doubleValue();
		double lastAngleInRadB = planetB.getLastAngleInRad().doubleValue();
		double lastAngleInRadC = planetC.getLastAngleInRad().doubleValue();

		final long totalDays = to.toEpochDay() - from.toEpochDay() + 1;
		double maxPerimeter = Double.MIN_VALUE;
		final ForecastResponse response = new ForecastResponse();
		for (long i = 0; i < totalDays; i++) {
			final long day = i + 1;
			final double angleA = lastAngleInRadA;
			final double angleB = lastAngleInRadB;
			final double angleC = lastAngleInRadC;
			final Point pointA = Point.of(angleA, distanceInKmA);
			final Point pointB = Point.of(angleB, distanceInKmB);
			final Point pointC = Point.of(angleC, distanceInKmC);
			final Line line = new Line(pointA, pointB);

			if (line.isAligned(pointC)) {
				response.addWeather(line.isAligned(POINT_SUN) ?
						new Weather(day, Forecast.DRY, galaxy) :
						new Weather(day, Forecast.EXCELLENT, galaxy));
			} else {
				final Triangle triangle = new Triangle(pointA, pointB, pointC);
				final double perimeter = triangle.perimeter();
				if (triangle.contains(POINT_SUN)) {
					response.addWeather(new Weather(day, Forecast.RAIN, galaxy));
					if (perimeter > maxPerimeter) {
						maxPerimeter = perimeter;
						response.setMaxRainDay(day);
					}
				} else {
					response.addWeather(new Weather(day, Forecast.REGULAR, galaxy));
				}
			}

			lastAngleInRadA = CircularMotionCalculator.newAngle(angularSpeedInRadA, angleA);
			lastAngleInRadB = CircularMotionCalculator.newAngle(angularSpeedInRadB, angleB);
			lastAngleInRadC = CircularMotionCalculator.newAngle(angularSpeedInRadC, angleC);
		}

		planetA.setLastAngleInRad(BigDecimal.valueOf(lastAngleInRadA));
		planetB.setLastAngleInRad(BigDecimal.valueOf(lastAngleInRadB));
		planetC.setLastAngleInRad(BigDecimal.valueOf(lastAngleInRadC));

		weatherRepository.saveAll(response.getWeathers());
		galaxyService.save(galaxy);

		return response.getStatistic();
	}

	private double getAngularSpeedInRadUsingClockwise(final Planet planet) {
		final double angularSpeed = CircularMotionCalculator.convertAngularSpeedInDegreeByDayToRadByDay(planet.getAngularSpeedInDegreeByDay().doubleValue());
		return planet.isClockwise() ? angularSpeed * (-1) : angularSpeed;
	}
}
