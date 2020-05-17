package br.com.ywassa.galaxy.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.domain.Planet;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.repository.WeatherRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Transactional
public class WeatherServiceTest {

	@Autowired
	private WeatherService weatherService;

	@MockBean
	private WeatherRepository weatherRepository;

	@MockBean
	private GalaxyService galaxyService;

	private Galaxy buildGalaxy() {
		final Galaxy galaxy = new Galaxy();
		galaxy.setId(WeatherService.GALAXY_ID);
		galaxy.setName("Galaxy M");
		galaxy.setOriginDate(LocalDate.of(2020,5,17));
		galaxy.setDay(0L);
		galaxy.setPlanetOne(new Planet(1L,
				"Ferengi",
				1.0,
				true,
				500,
				BigDecimal.ZERO));
		galaxy.setPlanetTwo(new Planet(2L,
				"Betasoide",
				3.0,
				true,
				2000,
				BigDecimal.ZERO));
		galaxy.setPlanetThree(new Planet(3L,
				"Vulcano",
				5.0,
				false,
				1000,
				BigDecimal.ZERO));

		return galaxy;
	}

	@Test
	public void shouldCalculateForecastForTenYears() {
		Mockito.when(weatherRepository.saveAll(Mockito.anyList())).then(AdditionalAnswers.returnsFirstArg());
		Mockito.when(galaxyService.findById(WeatherService.GALAXY_ID)).thenReturn(buildGalaxy());

		final StatisticDTO statistic = weatherService.forecastForTenYears();

		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2451, statistic.getRegularDay() );
	}
}