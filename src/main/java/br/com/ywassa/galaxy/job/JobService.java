package br.com.ywassa.galaxy.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ywassa.galaxy.service.WeatherService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobService {

	@Autowired
	private WeatherService weatherService;

	@Scheduled(cron = "${br.com.ywassa.job.cron}")
	void runJob() {
		log.info("Job Started");
		weatherService.forecastForTenYears();
		log.info("Job Finished");
	}
}
