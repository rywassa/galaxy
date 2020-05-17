package br.com.ywassa.galaxy.service;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import br.com.ywassa.galaxy.domain.Forecast;
import br.com.ywassa.galaxy.domain.Weather;
import br.com.ywassa.galaxy.dto.StatisticDTO;
import lombok.Getter;
import lombok.Setter;

class ForecastResponse {
	private Map<Forecast, Long> statistics = new EnumMap<>(Forecast.class);

	@Getter
	private List<Weather> weathers = new ArrayList<>();

	@Setter
	private long maxRainDay;

	public void addWeather(final Weather weather) {
		weathers.add(weather);
		statistics.compute(weather.getForecast(), (k, v) -> v != null ? v + 1 : 1);
	}

	public StatisticDTO getStatistic() {
		return StatisticDTO.builder()
				.dryDays(statistics.getOrDefault(Forecast.DRY, 0L))
				.excellentDays(statistics.getOrDefault(Forecast.EXCELLENT, 0L))
				.regularDay(statistics.getOrDefault(Forecast.REGULAR, 0L))
				.rainDays(statistics.getOrDefault(Forecast.RAIN, 0L))
				.maxRainDay(maxRainDay)
				.build();
	}
}
