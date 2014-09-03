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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.organization.OrganizationObj;
import com.abrecorp.opensource.servlet.BaseServletABRE;

public class FollowUpCVIApplicantsLeadsEmail   extends ABREBaseBRLO implements Job {

    public void execute(JobExecutionContext context)
     throws JobExecutionException {
    
        System.out.println(" ****************************** FollowUpCVIApplicantsLeadsEmail Cron executing **************************  ");

    	/**
    	* get email list for an organization, opportunity, or volunteer
    	*/
        ArrayList<ApplicationInfoDTO> aList =  new ArrayList<ApplicationInfoDTO>();
        ApplicationInfoDTO aApplicInfoObj = new ApplicationInfoDTO();
        
   		int iRetCode=0;
   		
    	CronTasksBRLO(  );

    	aList =  new ArrayList<ApplicationInfoDTO>();
        EmailInfoDTO aEmailApplicationConfirmationObj = new EmailInfoDTO();
        EmailInfoDTO aEmailApplicationMonthlyReminderObj = new EmailInfoDTO();
        EmailInfoDTO aEmailApplicationCloseoutObj = new EmailInfoDTO();
        EmailInfoDTO aEmailLeadPg2Obj = new EmailInfoDTO();
        EmailInfoDTO aEmailLeadPg3Obj = new EmailInfoDTO();
        EmailInfoDTO aEmailLeadResumeObj = new EmailInfoDTO();
        EmailInfoDTO aEmailLeadCloseoutObj = new EmailInfoDTO();
		
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailApplicationConfirmationObj, iApplicConfirmEmailNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailApplicationMonthlyReminderObj, iApplicMonthlyReminderNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailApplicationCloseoutObj, iApplicCloseoutNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailLeadPg2Obj, iLeadIncompletePg2NID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailLeadPg3Obj, iLeadIncompletePg3NID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailLeadResumeObj, iLeadIncompleteResumeNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailLeadCloseoutObj, iLeadCloseoutNID);
       	
   		iRetCode=m_CronTasksBRLOObj.loadApplicList(aList,ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER); 
   		// should this just be all; it grabs the screened value and runs the appropriate email depending on 
   		//combination of the screened value, the application date, and the fact that placed!=1

        Date today = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, -aEmailLeadPg2Obj.getNumDaysTrigger() );
        Date date_triggerLeadCompleteReminder = cal.getTime(); 
        
        cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, -aEmailApplicationMonthlyReminderObj.getNumDaysTrigger() );
        Date date_monthlyReminder = cal.getTime(); 
        
        cal.setTime(today);
        cal.add(Calendar.DATE, -aEmailApplicationCloseoutObj.getNumDaysTrigger() );
        Date date_expireWarning = cal.getTime(); 

        // iterate through aList - list of applications
   		Iterator<ApplicationInfoDTO> itr = aList.iterator();
   		int iRetCode2=0;
	    while (itr.hasNext()) {
	    	aApplicInfoObj = itr.next();
	    	int iApplicNID = aApplicInfoObj.getNID();
	    	iRetCode = m_CronTasksBRLOObj.loadOneApplication(aApplicInfoObj, iApplicNID);
	    	Date d_applyDate = aApplicInfoObj.getApplyDt();
	    	int iScreened = aApplicInfoObj.getScreened();
	    	// 1. check to see if it already has a record for this email_nid in the database; if not, proceed.
	    	//		if it does have a record, skip to the next itr.hasNext();
           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailApplicationConfirmationObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iApplicConfirmEmailNID);
           	if(iRetCode2==-111 && iScreened >-1) {// hasn't been sent yet
           		iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailApplicationConfirmationObj, iApplicConfirmEmailNID);
           	}
           	if(d_applyDate!=null){
		    	if(d_applyDate.before(date_triggerLeadCompleteReminder) ){// 7+ days have passed; 
	           		if(iScreened==iLeadPg1Done){
			           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailLeadPg2Obj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iLeadIncompletePg2NID);
			           	if(iRetCode2==-111){ // hasn't been sent yet
		           			iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailLeadPg2Obj, iLeadIncompletePg2NID);
		           		}
		           	}
	           		if(iScreened==iLeadPg2Done){
			           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailLeadPg3Obj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iLeadIncompletePg3NID);
			           	if(iRetCode2==-111){ // hasn't been sent yet
		    		    	iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailLeadPg3Obj, iLeadIncompletePg3NID);
		           		}
		           	}
	           		if(iScreened==iLeadPg3Done){
			           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailLeadPg3Obj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iLeadIncompleteResumeNID);
			           	if(iRetCode2==-111){ // hasn't been sent yet
		    		    	iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailLeadResumeObj, iLeadIncompleteResumeNID);
		           		}
		           	}
		    	}
		    	
		    	if(iScreened>iLeadReadyForApproval && d_applyDate.before(date_monthlyReminder) && d_applyDate.after(date_expireWarning)){// 30-90 days have passed; 
		    		// this will only trigger the reminder 1 time, rather than each month; need to look into fixing this
		           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailApplicationMonthlyReminderObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iApplicMonthlyReminderNID);
		           	if(iRetCode2==-111) {// hasn't been sent yet
		           		iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailApplicationMonthlyReminderObj, iApplicMonthlyReminderNID);
		           	}
		    	}
		    	
		    	if(d_applyDate.before(date_expireWarning) ){// 90+ days have passed; 
		    		// this will only trigger the reminder 1 time, rather than each month; need to look into fixing this
		           	if(aApplicInfoObj.getScreened() > 0){
		           		iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailApplicationCloseoutObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iApplicCloseoutNID);
			           	if(iRetCode2==-111) {
			           		iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailApplicationCloseoutObj, iApplicCloseoutNID);
			           	}
		           	}
		    		// this will only trigger the reminder 1 time, rather than each month; need to look into fixing this
		           	if(iScreened < iLeadReadyForApproval){
			           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailLeadCloseoutObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iLeadCloseoutNID);
			           	if(iRetCode2==-111) {// hasn't been sent yet
			           		iRetCode = m_CVIBaseMethodObj.sendApplicEmails(aApplicInfoObj, aEmailLeadCloseoutObj, iLeadCloseoutNID);
			           	}
		           	}
		    	}
           	}
	    }
    }
   
	/**
	* allocate business rule layes object for organization 
	*/
	private void CronTasksBRLO(  ){
		if(null == m_CronTasksBRLOObj){
			m_CronTasksBRLOObj = new CronTasksBRLO();
			m_CronTasksBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer(  ) );
		}
	}

    
	//====== END Private Methods ===>
    //====== END Private Methods ===>
    //====== END Private Methods ===>

	//====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>

	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private CronTasksBRLO m_CronTasksBRLOObj =null;
	private CVIEmailBaseMethods m_CVIBaseMethodObj = new CVIEmailBaseMethods();
	
	private static int iOrgApplicMatchEmailNID = 546506, iOrgApplicMatchRecommendEmailNID = 547238, iApplicConfirmEmailNID = 544402,
			iApplicMonthlyReminderNID=544403, iApplicCloseoutNID=544404, 
			iLeadIncompletePg2NID=544405, iLeadIncompletePg3NID=544406, 
			iLeadIncompleteResumeNID=553037, iLeadCloseoutNID=544407, 
			iLeadPg1Done=-10, iLeadPg2Done=-9, iLeadPg3Done=-8, iLeadReadyForApproval=0;
	
}