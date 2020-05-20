package br.com.ywassa.galaxy.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ywassa.galaxy.domain.Forecast;
import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.domain.Weather;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.infra.test.AbstractIntegrationTest;

public class WeatherServiceTest extends AbstractIntegrationTest {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private GalaxyService galaxyService;

	@PersistenceContext
	private EntityManager entityManager;

	private LocalDate getOriginDateFromGalaxy() {
		final Galaxy galaxy = galaxyService.findById(WeatherService.GALAXY_ID);
		return galaxy.getOriginDate();
	}

	private void assertTotalDays(final LocalDate from, final LocalDate to, final StatisticDTO statistic) {
		final long total = to.toEpochDay() - from.toEpochDay();
		assertEquals(total, statistic.getDryDays()
				+ statistic.getExcellentDays()
				+ statistic.getRainDays()
				+ statistic.getRegularDay());
	}

	/**
	 * It must be done, because the lose precision when the last angle is persisted in each process request
	 * @return
	 */
	private StatisticDTO callForecastForTenYearsAndFlushClear() {
		final StatisticDTO statistic = weatherService.forecastForTenYears();
		entityManager.flush();
		entityManager.clear();
		return statistic;
	}

	@Test
	public void shouldCalculateForecastForTenYears() {
		final StatisticDTO statistic = callForecastForTenYearsAndFlushClear();

		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2450, statistic.getRegularDay());

		final LocalDate from = getOriginDateFromGalaxy();
		final LocalDate to = from.plusYears(10);
		assertTotalDays(from, to, statistic);
	}

	@Test
	public void shouldCalculateForecastForTwentyYears() {
		StatisticDTO statistic = callForecastForTenYearsAndFlushClear();

		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2450, statistic.getRegularDay() );

		statistic = callForecastForTenYearsAndFlushClear();

		assertEquals(0L, statistic.getDryDays());
		assertEquals(0L, statistic.getExcellentDays());
		assertEquals(1216L, statistic.getRainDays());
		assertEquals(4789L, statistic.getMaxRainDay());
		assertEquals(2437L, statistic.getRegularDay() );

		final LocalDate originDate = getOriginDateFromGalaxy();
		final LocalDate from = originDate.plusYears(10);
		final LocalDate to = from.plusYears(10);
		assertTotalDays(from, to, statistic);
	}

	@Test
	public void shouldCalculateForecastForTenYearsAndCheckForecastForFiveDays() {
		callForecastForTenYearsAndFlushClear();

		final TypedQuery<Weather> query = entityManager.createQuery("select w from Weather w where w.day <= 5 order by day", Weather.class);
		final List<Weather> weathers = query.getResultList();

		assertEquals(5, weathers.size());

		final Iterator<Weather> iterator = weathers.iterator();
		final Weather day1 = iterator.next();
		assertEquals(Long.valueOf(1L), day1.getDay());
		assertEquals(Forecast.DRY, day1.getForecast());

		final Weather day2 = iterator.next();
		assertEquals(Long.valueOf(2L), day2.getDay());
		assertEquals(Forecast.REGULAR, day2.getForecast());

		final Weather day3 = iterator.next();
		assertEquals(Long.valueOf(3L), day3.getDay());
		assertEquals(Forecast.REGULAR, day3.getForecast());

		final Weather day4 = iterator.next();
		assertEquals(Long.valueOf(4L), day4.getDay());
		assertEquals(Forecast.REGULAR, day4.getForecast());

		final Weather day5 = iterator.next();
		assertEquals(Long.valueOf(5L), day5.getDay());
		assertEquals(Forecast.REGULAR, day5.getForecast());
	}
}