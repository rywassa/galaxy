package br.com.ywassa.galaxy.service;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.ywassa.galaxy.domain.Forecast;
import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.domain.Weather;
import br.com.ywassa.galaxy.dto.StatisticDTO;

public class ForecastResponseTest {

	@Test
	public void shouldCollectStatistics() {
		final ForecastResponse response = new ForecastResponse();
		final Galaxy galaxy = new Galaxy();
		long day = 1;
		response.addWeather(new Weather(day++, Forecast.DRY, galaxy));
		response.addWeather(new Weather(day++, Forecast.DRY, galaxy));

		response.addWeather(new Weather(day++, Forecast.REGULAR, galaxy));
		response.addWeather(new Weather(day++, Forecast.REGULAR, galaxy));
		response.addWeather(new Weather(day++, Forecast.REGULAR, galaxy));

		response.addWeather(new Weather(day++, Forecast.EXCELLENT, galaxy));
		response.addWeather(new Weather(day++, Forecast.EXCELLENT, galaxy));
		response.addWeather(new Weather(day++, Forecast.EXCELLENT, galaxy));
		response.addWeather(new Weather(day++, Forecast.EXCELLENT, galaxy));

		response.setMaxRainDay(day);
		response.addWeather(new Weather(day++, Forecast.RAIN, galaxy));
		response.addWeather(new Weather(day++, Forecast.RAIN, galaxy));
		response.addWeather(new Weather(day++, Forecast.RAIN, galaxy));
		response.addWeather(new Weather(day++, Forecast.RAIN, galaxy));
		response.addWeather(new Weather(day++, Forecast.RAIN, galaxy));

		final StatisticDTO statistic = response.getStatistic();
		assertEquals(2L, statistic.getDryDays());
		assertEquals(3L, statistic.getRegularDay());
		assertEquals(4L, statistic.getExcellentDays());
		assertEquals(5L, statistic.getRainDays());
		assertEquals(10L, statistic.getMaxRainDay());
	}
}