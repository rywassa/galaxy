package br.com.ywassa.galaxy.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ywassa.galaxy.dto.StatisticDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Transactional
public class WeatherServiceIT {

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