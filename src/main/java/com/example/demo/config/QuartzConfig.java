package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.example.demo.quartz.QuartzDemo;

@Configuration
public class QuartzConfig {
	/**
	* 1.创建 Job 对象
	*/
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		//关联我们自己的 Job 类
		factory.setJobClass(QuartzDemo.class);
		return factory;
	}
	/**
	* 2.创建 Trigger 对象
	* 简单的 Trigger
	*/
//	@Bean
//	public SimpleTriggerFactoryBean
//	simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
//		
//		SimpleTriggerFactoryBean factory = new
//		SimpleTriggerFactoryBean();
//		//关联 JobDetail 对象
//		factory.setJobDetail(jobDetailFactoryBean.getObject());
//		//该参数表示一个执行的毫秒数
//		factory.setRepeatInterval(2000);
//		//重复次数
//		factory.setRepeatCount(5);
//		return factory;
//	}
	
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(
			JobDetailFactoryBean jobDetailFactoryBean) {
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
		//关联 JobDetail 对象
		factory.setJobDetail(jobDetailFactoryBean.getObject());
		// 设置触发时间
		factory.setCronExpression("0/2 * * * * ?");
		return factory;
		
	}
	/**
	* 3.创建 Scheduler 对象
	*/
	@Bean
	public SchedulerFactoryBean
	schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean,
			MyAdaptableJobFactory  myAdaptableJobFactory){
//		SimpleTriggerFactoryBean simpleTriggerFactoryBean
		
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		//关联 trigger
//		factory.setTriggers(simpleTriggerFactoryBean.getObject());
		
		factory.setTriggers(cronTriggerFactoryBean.getObject());
		
		factory.setJobFactory(myAdaptableJobFactory);
		
		return factory;
	}
}
