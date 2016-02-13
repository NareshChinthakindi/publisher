/**
 * 
 */
package com.verisk.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Naresh
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PublisherSourceLookUpJob extends QuartzJobBean 
{

	public static final String COUNT = "count";
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	protected void executeInternal(JobExecutionContext jobExeContext) throws JobExecutionException 
	{
		System.out.println("Test");
	   JobDataMap dataMap = jobExeContext.getJobDetail().getJobDataMap();
  	   int cnt = dataMap.getInt(COUNT);
	   JobKey jobKey = jobExeContext.getJobDetail().getKey();
	   System.out.println(jobKey+": "+name+": "+ cnt);
	   cnt++;
	   dataMap.put(COUNT, cnt);
		
	}

}
