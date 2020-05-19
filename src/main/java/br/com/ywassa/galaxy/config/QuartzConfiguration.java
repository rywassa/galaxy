package br.com.ywassa.galaxy.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import br.com.ywassa.galaxy.infra.job.AutowiringSpringBeanJobFactory;
import br.com.ywassa.galaxy.job.ForecastJob;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class QuartzConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private QuartzProperties quartzProperties;

	@Value("${br.com.ywassa.forecast.cron}")
	private String forecastCron;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		final AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);

		final Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());

		final SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		factory.setQuartzProperties(properties);
		factory.setJobFactory(jobFactory);
		return factory;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		final JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(ForecastJob.class);
		jobDetailFactory.setName("Galaxy Job");
		jobDetailFactory.setDescription("Galaxy Forecast");
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	@Bean
	public CronTriggerFactoryBean trigger(final JobDetail jobDetail){
		final CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setName("Forecast Trigger");
		trigger.setCronExpression(forecastCron);
		trigger.setJobDetail(jobDetail);
		return trigger;
	}

	@Bean
	public Scheduler scheduler(final Trigger trigger,
							   final JobDetail job,
							   final SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
		final Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.clear();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		return scheduler;
	}
}