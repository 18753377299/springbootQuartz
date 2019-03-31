package com.example.demo.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.user.service.UserService;

public class QuartzDemo implements Job{

	@Autowired
	private UserService userService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("==============execute:"+new Date());
		String user = userService.showUser();
		System.out.println("=================="+user);
	}
	
	
	
}
