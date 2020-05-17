package br.com.ywassa.galaxy.controller;

import static br.com.ywassa.galaxy.controller.Resources.WeatherResource;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ywassa.galaxy.domain.Weather;
import br.com.ywassa.galaxy.dto.ResponseDTO;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import br.com.ywassa.galaxy.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Weather Service")
@RestController
@RequestMapping(path = WeatherResource.ROOT)
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@ApiOperation(value = "Get forecast by epoch", response = ResponseDTO.class)
	@GetMapping
	public ResponseDTO findByEpochDay(
			@RequestParam(WeatherResource.TOKEN_DAY)
			@Min(1)
			final Long day) {
		final Weather weather = weatherService.findByDay(day);
		return new ResponseDTO(day, weather.getForecast());
	}

	@ApiOperation(value = "Calculate forecast")
	@PostMapping
	public StatisticDTO calculateForecast() {
		return weatherService.forecastForTenYears();
	}
}
