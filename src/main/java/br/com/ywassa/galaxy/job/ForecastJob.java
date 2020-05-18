package br.com.ywassa.galaxy.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ywassa.galaxy.service.WeatherService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class ForecastJob implements Job {

	@Autowired
	private WeatherService weatherService;

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) {
		log.info("Forecast Job Started");
		weatherService.forecastForTenYears();
		log.info("Forecast Job Ended");
	}
}