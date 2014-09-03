package com.abrecorp.opensource.cronjobs;

/**
* System:       Struts Action Layer
* Title:        Base Struts Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

// Struts MVC objects
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class MyJob implements Job {

    public void execute(JobExecutionContext context)
     throws JobExecutionException {
    
        System.out.println(" ****************************** Cron executing **************************  ");
        System.out.println(" ****************************** Cron executing **************************  ");
        System.out.println(" ****************************** Cron executing **************************  ");
        System.out.println(" ****************************** Cron executing **************************  ");
        
    }
}