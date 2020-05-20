package br.com.ywassa.galaxy.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ywassa.galaxy.controller.Resources.WeatherResource;
import br.com.ywassa.galaxy.domain.Forecast;
import br.com.ywassa.galaxy.dto.ResponseDTO;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.infra.exception.ErrorResponse;
import br.com.ywassa.galaxy.infra.test.AbstractIntegrationTest;

public class WeatherControllerTest extends AbstractIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	private void init() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Sql("/import/WeatherControllerTest.shouldFindByDay.sql")
	public void shouldFindByDay() throws Exception {
		final MockHttpServletResponse getResponse = mockMvc.perform(
				get(WeatherResource.ROOT)
						.queryParam(WeatherResource.TOKEN_DAY, "1")
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		final String getJsonResponse = getResponse.getContentAsString();
		final ResponseDTO response = objectMapper.readValue(getJsonResponse, ResponseDTO.class);
		assertNotNull(response);
		assertEquals(1L, response.getDay());
		assertEquals(Forecast.DRY, response.getForecast());
	}

	@Test
	public void shouldNotFindByDay() throws Exception {
		final MockHttpServletResponse getResponse = mockMvc.perform(
				get(WeatherResource.ROOT)
						.queryParam(WeatherResource.TOKEN_DAY, "1")
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), getResponse.getStatus());

		final String getJsonResponse = getResponse.getContentAsString();
		final ErrorResponse response = objectMapper.readValue(getJsonResponse, ErrorResponse.class);
		assertNotNull(response);
		assertNotNull(response.getMessage());
	}

	@Test
	public void calculateForecast() throws Exception {
		final MockHttpServletResponse postResponse = mockMvc.perform(
				post(WeatherResource.ROOT)
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertEquals(HttpStatus.OK.value(), postResponse.getStatus());

		final String postJsonResponse = postResponse.getContentAsString();
		final StatisticDTO statistic = objectMapper.readValue(postJsonResponse, StatisticDTO.class);
		assertNotNull(statistic);
		assertEquals(11L, statistic.getDryDays());
		assertEquals(2L, statistic.getExcellentDays());
		assertEquals(1189L, statistic.getRainDays());
		assertEquals(2953L, statistic.getMaxRainDay());
		assertEquals(2450, statistic.getRegularDay());
	}
}