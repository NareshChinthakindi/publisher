package com.verisk.publisher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.verisk.job.PublisherSourceLookUpJob;


@Configuration
@ComponentScan("com.verisk")
@EnableAutoConfiguration
public class PublisherApplication extends ServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean()
	{
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		
		obj.setTargetBeanName("simpleJobTest");
		obj.setTargetMethod("myTask");
		
		return obj;
	}
	
	@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean()
	{
		SimpleTriggerFactoryBean obj = new SimpleTriggerFactoryBean();
		obj.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		obj.setStartDelay(3000);
		obj.setRepeatInterval(30000);
		obj.setRepeatCount(3);
		return obj;
	}
	
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean()
	{
		JobDetailFactoryBean obj = new JobDetailFactoryBean();
		
		obj.setJobClass(PublisherSourceLookUpJob.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "RAM");
		map.put(PublisherSourceLookUpJob.COUNT, 1);
		obj.setJobDataAsMap(map);
		obj.setGroup("mygroup");
		obj.setName("myjob");
		
		return obj;
	}
	
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
		return stFactory;
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean()
	{
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
		return scheduler;
	}
}
