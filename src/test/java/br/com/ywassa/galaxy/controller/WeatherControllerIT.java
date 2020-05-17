package br.com.ywassa.galaxy.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import br.com.ywassa.galaxy.controller.Resources.WeatherResource;
import lombok.AccessLevel;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class WeatherControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@PostConstruct
	private void init() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void findByEpochDay() throws Exception {
		final MockHttpServletResponse response = mockMvc.perform(
				get(WeatherResource.ROOT, WeatherResource.TOKEN_DAY + "=1")
						.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


	}

	@Test
	public void calculateForecast() throws Exception {

	}

}