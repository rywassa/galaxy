package br.com.ywassa.galaxy.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.infra.test.AbstractIntegrationTest;

public class WeatherServiceTest extends AbstractIntegrationTest {

	@Autowired
	private WeatherService weatherService;

	@Test
	public void shouldCalculateForecastForTenYears() {
		final StatisticDTO statistic = weatherService.forecastForTenYears();

		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2451, statistic.getRegularDay() );
	}

	@Test
	public void shouldCalculateForecastForTwentyYears() {
		StatisticDTO statistic = weatherService.forecastForTenYears();

		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2451, statistic.getRegularDay() );

		statistic = weatherService.forecastForTenYears();

		assertEquals(1L, statistic.getDryDays());
		assertEquals(0L, statistic.getExcellentDays());
		assertEquals(1232L, statistic.getRainDays());
		assertEquals(7273L, statistic.getMaxRainDay());
		assertEquals(2421, statistic.getRegularDay() );
	}
}