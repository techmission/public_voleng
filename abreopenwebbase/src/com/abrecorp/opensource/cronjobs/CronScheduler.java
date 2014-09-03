package com.abrecorp.opensource.cronjobs;

/**
* System:       Java Server Pages Test Utility
* Title:        Test Basic Servlet Functions
* Description:  
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import org.quartz.Trigger;
import org.quartz.JobKey;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import com.abrecorp.opensource.cronjobs.MyJob;
import com.abrecorp.opensource.cronjobs.InitScreenedCVIApplicantEmail;

public class CronScheduler {

    public CronScheduler() throws Exception {
        
    	System.out.println("CronScheduler called successfully");
        SchedulerFactory sf = new StdSchedulerFactory();        
        Scheduler sche = sf.getScheduler();        
        sche.start();

        sche.deleteJob(new JobKey("CVIApplicantEmailOrg1", "CVIApplicantEmailOrg1"));
        sche.deleteJob(new JobKey("CVIApplicantEmailOrg2", "CVIApplicantEmailOrg2"));
        sche.deleteJob(new JobKey("CVIApplicantEmailOrg11", "CVIApplicantEmailOrg11"));
        sche.deleteJob(new JobKey("CVIApplicantEmailOrg12", "CVIApplicantEmailOrg12"));
        sche.deleteJob(new JobKey("CVIApplicantFollowUpEmails1", "CVIApplicantFollowUpEmails1"));
        sche.deleteJob(new JobKey("CVIApplicantFollowUpEmails2", "CVIApplicantFollowUpEmails2"));
        sche.deleteJob(new JobKey("CVIApplicantFollowUpEmails3", "CVIApplicantFollowUpEmails3"));
        sche.deleteJob(new JobKey("CVIApplicantFollowUpEmails4", "CVIApplicantFollowUpEmails4"));

        /*
        sche.deleteJob(new JobKey("CVIApplicantEmailOrg", "CVIApplicantEmailOrg"));

        sche.deleteJob(new JobKey("CVIApplicantEmailOrgA", "CVIApplicantEmailOrgA"));
        sche.deleteJob(new JobKey("CVIApplicantFollowUpEmailsA", "CVIApplicantFollowUpEmailsA"));
        */

        //"0 0 12 * * ?" Fire at 12pm (noon) every day
       
	    JobDetail jInitScreenedCVI = newJob(InitScreenedCVIApplicantEmail.class)
	    		.withIdentity("CVIApplicantEmailOrgA", "CVIApplicantEmailOrgA")
	    		.build();
	    Trigger crInitScreenedCVI = newTrigger()
	    		.withIdentity("CVIApplicantEmailOrgA", "CVIApplicantEmailOrgA")
	    		.startNow()
	    		.withSchedule(cronSchedule("0 22 */4 * * ?")) // runs every 4 hours starting at 1222am every day
	    		.build();
	    sche.scheduleJob(jInitScreenedCVI, crInitScreenedCVI);
        
	    JobDetail jFollowupEmailsCVI = newJob(FollowUpCVIApplicantsLeadsEmail.class)
	    		.withIdentity("CVIApplicantFollowUpEmailsA", "CVIApplicantFollowUpEmailsA")
	    		.build();
	    Trigger crFollowupEmailsCVI = newTrigger()
	    		.withIdentity("CVIApplicantFollowUpEmailsA", "CVIApplicantFollowUpEmailsA")
	    		.startNow()
	    		.withSchedule(cronSchedule("0 38 */4 * * ?")) // runs every 4 hours starting at 1238am every day
	    		.build();
	    sche.scheduleJob(jFollowupEmailsCVI, crFollowupEmailsCVI);

    }
}