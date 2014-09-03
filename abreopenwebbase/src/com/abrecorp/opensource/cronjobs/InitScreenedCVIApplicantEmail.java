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

public class InitScreenedCVIApplicantEmail   extends ABREBaseBRLO implements Job {

    public void execute(JobExecutionContext context)
     throws JobExecutionException {
    
        System.out.println(" ****************************** InitScreenedCVIApplicantEmail TEST Cron executing **************************  ");
        System.out.println(" ****************************** InitScreenedCVIApplicantEmail TEST Cron executing **************************  ");
        System.out.println(" ****************************** InitScreenedCVIApplicantEmail TEST Cron executing **************************  ");
        
    	/**
    	* get email list for an organization, opportunity, or volunteer
    	*/
        ArrayList<ApplicationInfoDTO> aList =  new ArrayList<ApplicationInfoDTO>();
        ApplicationInfoDTO aApplicInfoObj = new ApplicationInfoDTO();
        
   		int iRetCode=0, iRetCode2=0;
        String aszSMTPServer=this.getSitePropertyValue("mail.smtp.host");
    	String aszSmtpUserName = this.getSitePropertyValue("mail.smtp.from");
    	String aszFromPassword =this.getSitePropertyValue("mail.smtp.userpassword");
    	String aszEmailMain =this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
    	String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
   		
    	CronTasksBRLO(  );

		// FIRST TASK - update the um_content_field_cvintern_org_source_flag to have a record for EACH um_content_field_cvintern_org
    	// 1 - load every record in um_content_field_cvintern_org
    	// 		2 - for each record, query um_content_field_cvintern_org_source_flag to see if there is a record
    	//			3 - if so, SKIP to next record
    	//			4 - if NOT, INSERT INTO um_content_field_cvintern_org_source_flag ALL the same values, only with the added field of
    	//					source='RECOMMEND'
    	iRetCode = m_CronTasksBRLOObj.getApplicMatchesList( aList);
	    Iterator<ApplicationInfoDTO> itr = aList.iterator();
	    while (itr.hasNext()) {
	    	aApplicInfoObj = itr.next();
	    	int iApplicNID = aApplicInfoObj.getNID();
	    	int iOrgNID = aApplicInfoObj.getORGNID();
	    	if(iApplicNID==546141){
	    		System.out.println("triggered");
	    	}
	    	
	    	String aszSource=m_CronTasksBRLOObj.getApplicMatchSource(iOrgNID, iApplicNID);
	    	boolean b_newRecommendation = false;
	    	if(aszSource==null){
	    		b_newRecommendation = true;
	    	}else if(!	( aszSource.equals("RECOMMEND") || aszSource.equals("REQUESTED") )	){
	    		b_newRecommendation = true;
	    	}
	    	if(b_newRecommendation && iApplicNID>0 && iOrgNID>0){
	    		// it does NOT already exist
	    		// write it to the database in um_content_field_cvintern_org_source_flag
	    		aApplicInfoObj.setSource("RECOMMEND");
	    		iRetCode = m_CronTasksBRLOObj.insertApplicMatch(aApplicInfoObj);
	    	}
	    }
    	
    	
    	aList =  new ArrayList<ApplicationInfoDTO>();
        EmailInfoDTO aEmailMatchRequestedInfoObj = new EmailInfoDTO();
        EmailInfoDTO aEmailMatchRecommendedInfoObj = new EmailInfoDTO();
        EmailInfoDTO aEmailApplicationReceivedObj = new EmailInfoDTO();
		
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailMatchRequestedInfoObj, iOrgApplicMatchEmailNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailMatchRecommendedInfoObj, iOrgApplicMatchRecommendEmailNID);
        iRetCode=m_CronTasksBRLOObj.loadEmailMsg(aEmailApplicationReceivedObj, iApplicConfirmEmailNID);
       	// NOTE: aEmailInfoObj now stores the email template to send to the organizations on an initial match
       	
       	/*
       	 * 1. get a list of all applications with screened>0; aApplicList
       	 * 
       	 * 2. iterate - for each application, load the data for the orglist; join to 
       	 * 			um_content_field_cvintern_org & get all the org_nids - store in an array
       	 * 
       	 * 		a. iterate - for each orgnid, check if the emailflag exists in um_content_field_cvi_email_flag
       	 * 			i.	if exists, skip to the next org in list
       	 * 			ii.	if NOT.... PROCEED:
       	 * 					compose email with data retrieved into aEmailInfoObj; to replaceAll on all the {token:??} items
       	 * 
       	 */
       	
   		iRetCode=m_CronTasksBRLOObj.loadApplicList(aList,ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER_APPLICANT);

   		// iterate through aList - list of applications
	    itr = aList.iterator();
	    while (itr.hasNext()) {
//System.out.println(" ********* CRON OUTPUT cron output - line 87 - inside itr.hasNext()");	    	
	    	aApplicInfoObj = itr.next();
//System.out.println(" ********* CRON OUTPUT cron output - line 89 - set aApplicInfoObj");	    	
	    	int iApplicNID = aApplicInfoObj.getNID();
	    	// for each org, we have to load their orgnids
	    	iRetCode = m_CronTasksBRLOObj.loadOneApplication(aApplicInfoObj, iApplicNID);
	    	
	    	
	    	// 1. check to see if it already has a record for this email_nid in the database; if not, proceed.
	    	//		if it does have a record, skip to the next itr.hasNext();
           	iRetCode2 = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailApplicationReceivedObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID, iApplicConfirmEmailNID);
	    	
	    	int[] a_iOrgNIDs = aApplicInfoObj.getORGNIDsArray();
	    	for(int i=0; i<a_iOrgNIDs.length; i++){
	    		int iOrgNID = a_iOrgNIDs[i];
	    		// check if record exists with email_id=iOrgApplicMatchEmailNID,
	    		// cvintern_site_org_nid=iOrgNID && applic_nid=iApplicNID
	    		
	    		// STEP 1: will have to look up the appnid-orgnid pair first in the um_content_field_cvintern_org_source_flag table to see what the source is
	    		// 		if the source is set to RECOMMEND or '', then we first have to load iOrgApplicMatchRecommendEmailNID, and if it's not been used,
	    		//			then we proceed to use the aEmailMatchRecommendedInfoObj object;
	    		//		otherwise, if the source is set to REQUESTED, then we have to load iOrgApplicMatchEmailNID, and use aEmailMatchRequestedInfoObj
	    		// ** IN BOTH CASES, we will have to do a Database query to ensure that there is NO RESULT for BOTH CASES; (if one is a match, then we skip it)
	    		String aszSource=m_CronTasksBRLOObj.getApplicMatchSource(iOrgNID, iApplicNID);
	    		if(aszSource.equalsIgnoreCase("RECOMMEND")){
	    			// 1st check to see if the RECOMMEND email was sent
		           	iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailMatchRecommendedInfoObj, iApplicNID, iOrgNID, ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID, iOrgApplicMatchRecommendEmailNID);
	    			if(iRetCode==-111){ // if it was NOT yet sent, make sure that we didn't already send an email for the "requested" match, either
	    				iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailMatchRequestedInfoObj, iApplicNID, iOrgNID,  ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID,iOrgApplicMatchEmailNID);
	    			}
	    		}else if(aszSource.equals("REQUESTED")){
	    			// 1st check to see if the requested email was sent
		           	iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailMatchRequestedInfoObj, iApplicNID, iOrgNID,  ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID,iOrgApplicMatchEmailNID);
	    			if(iRetCode==-111){ // if it was NOT yet sent, make sure that we didn't already send an email for the "recommend" match, either
	    				iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag( aEmailMatchRecommendedInfoObj, iApplicNID, iOrgNID,  ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID,iOrgApplicMatchRecommendEmailNID);
	    			}
	    		}
	    		
	           	
	           	if(iRetCode == -111 ){
	           		// does not yet exist, so we proceed with composing and sending the email
	                EmailInfoDTO aEmailInfoObj = aEmailMatchRequestedInfoObj;
	                int iNID = iOrgApplicMatchEmailNID;
	                if(aszSource.equalsIgnoreCase("RECOMMEND")){
	                	aEmailInfoObj = aEmailMatchRecommendedInfoObj;
	                	iNID = iOrgApplicMatchRecommendEmailNID;
	                }
	           		
	           		// load the organization
	           		OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	            	OrganizationObj aOrganizationObj = new OrganizationObj();
	            	aOrgInfoObj.setORGNID(iOrgNID);
	            	
	        	   // String aszToEmail = sbOppEmailList.toString();
	            	iRetCode = m_CronTasksBRLOObj.loadOrg(aOrgInfoObj);

	                EmailInfoDTO aEmailTextObj = new EmailInfoDTO();

	                String aszSubject = aEmailInfoObj.getEmailSubjectText();
	                String aszMessageIntro = aEmailInfoObj.getEmailBodyTextIntro();
	                String aszMessage = aEmailInfoObj.getEmailBodyText();
	                String aszMessageClosing = aEmailInfoObj.getEmailBodyTextClosing();
	                
	                String aszToEmail =  aEmailInfoObj.getEmailToUser();
	                String aszBCCEmail =  aEmailInfoObj.getEmailBCCAddress();
	                String aszFromUser =  aEmailInfoObj.getEmailFromUser();
	                String aszFromUserName = aEmailInfoObj.getEmailFromUserName();

	                aEmailTextObj.setEmailFromUser("info@christianvolunteering.org");
	                aEmailTextObj.setEmailFromUser("info@cityvisioninternships.org");
	                aEmailTextObj.setEmailSubdom( aszDomMain );   	
	                aEmailTextObj.setSMTPAuthRequired( true );
	                aEmailTextObj.setEmailMessageType(EmailInfoDTO.SINGLE_USER_TEXT_MESSAGE) ;
	                             
	                aszSMTPServer="localhost";
	            	aszSmtpUserName = this.getSitePropertyValue(ABREAppServerDef.MAIL_SMTP_FROM);
	            	aszFromPassword = this.getSitePropertyValue(ABREAppServerDef.MAIL_SMTP_USERPASSWORD);
	            	aszEmailMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
	            	aszEmailMain = "info@cityvisioninternships.org";
	            	aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
	            	
	            	if(aszToEmail.length()>0){
	            		aszToEmail = m_CVIBaseMethodObj.replaceTokenStrings(aszToEmail, aApplicInfoObj, aOrgInfoObj);
		            }
	            	if(aszSubject.length()>0){
	            		aszSubject = m_CVIBaseMethodObj.replaceTokenStrings(aszSubject, aApplicInfoObj, aOrgInfoObj);
		            }
	            	if(aszMessageIntro.length()>0){
	            		aszMessageIntro = m_CVIBaseMethodObj.replaceTokenStrings(aszMessageIntro, aApplicInfoObj, aOrgInfoObj);
		            }
	            	if(aszMessage.length()>0){
	            		aszMessage = m_CVIBaseMethodObj.replaceTokenStrings(aszMessage, aApplicInfoObj, aOrgInfoObj);
		            }
	            	if(aszMessageClosing.length()>0){
	            		aszMessageClosing = m_CVIBaseMethodObj.replaceTokenStrings(aszMessageClosing, aApplicInfoObj, aOrgInfoObj);
		            }
	            	aEmailTextObj.setEmailSMTPServerName( aszSMTPServer );
	            	aEmailTextObj.setEmailFromUserPassword( aszFromPassword );
	            	aEmailTextObj.setSMTPUserName( aszSmtpUserName );
	            	aEmailTextObj.setEmailSubjectText(aszSubject);
	            	aEmailTextObj.setEmailBodyTextIntro( aszMessageIntro + aszMessage);
//	            	aEmailTextObj.setEmailBodyText( aszMessage );
	            	aEmailTextObj.setEmailBodyTextClosing( aszMessageClosing );

	                aEmailTextObj.setEmailToUser(aszToEmail);
	                aEmailTextObj.setEmailBCCAddress(aszBCCEmail);
	                aEmailTextObj.setEmailFromUser(aszFromUser);
	                aEmailTextObj.setEmailFromUserName(aszFromUserName);

	                iRetCode = m_CronTasksBRLOObj.sendEmailMSG(aEmailTextObj);
	                System.out.println(" ********* CRON OUTPUT cron output - EMAIL SENT to Org about Match - iRetCode is "+iRetCode+" ***********************");
	                
	                // update and insert a record into um_content_field_cvi_email_flag
	                iRetCode = m_CronTasksBRLOObj.insertIntoApplicEmailFlag(  aApplicInfoObj,  aOrgInfoObj, iNID);
	            	
	           	}

	           	if(iRetCode2 == -111 ){
	           		// does not yet exist, so we proceed with composing and sending the email
	                EmailInfoDTO aEmailInfoObj = aEmailApplicationReceivedObj;
   	
	                EmailInfoDTO aEmailTextObj = new EmailInfoDTO();

	                String aszSubject = aEmailInfoObj.getEmailSubjectText();
	                String aszMessageIntro = aEmailInfoObj.getEmailBodyTextIntro();
	                String aszMessage = aEmailInfoObj.getEmailBodyText();
	                String aszMessageClosing = aEmailInfoObj.getEmailBodyTextClosing();
	                
	                String aszToEmail =  aEmailInfoObj.getEmailToUser();
	                String aszBCCEmail =  aEmailInfoObj.getEmailBCCAddress();
	                String aszFromUser =  aEmailInfoObj.getEmailFromUser();
	                String aszFromUserName = aEmailInfoObj.getEmailFromUserName();

	                aEmailTextObj.setEmailSubdom( aszDomMain );   	
	                aEmailTextObj.setSMTPAuthRequired( true );
	                aEmailTextObj.setEmailMessageType(EmailInfoDTO.SINGLE_USER_TEXT_MESSAGE) ;
	                             

	            	
	            	if(aszToEmail.length()>0){
	            		aszToEmail = m_CVIBaseMethodObj.replaceTokenStrings(aszToEmail, aApplicInfoObj, null);
		            }
	            	if(aszSubject.length()>0){
	            		aszSubject = m_CVIBaseMethodObj.replaceTokenStrings(aszSubject, aApplicInfoObj, null);
		            }
	            	if(aszMessageIntro.length()>0){
	            		aszMessageIntro = m_CVIBaseMethodObj.replaceTokenStrings(aszMessageIntro, aApplicInfoObj, null);
		            }
	            	if(aszMessage.length()>0){
	            		aszMessage = m_CVIBaseMethodObj.replaceTokenStrings(aszMessage, aApplicInfoObj, null);
		            }
	            	if(aszMessageClosing.length()>0){
	            		aszMessageClosing = m_CVIBaseMethodObj.replaceTokenStrings(aszMessageClosing, aApplicInfoObj, null);
		            }
	            	aEmailTextObj.setEmailSMTPServerName( aszSMTPServer );
	            	aEmailTextObj.setEmailFromUserPassword( aszFromPassword );
	            	aEmailTextObj.setSMTPUserName( aszSmtpUserName );
	            	aEmailTextObj.setEmailSubjectText(aszSubject);
	            	aEmailTextObj.setEmailBodyTextIntro( aszMessageIntro + aszMessage);
//	            	aEmailTextObj.setEmailBodyText( aszMessage );
	            	aEmailTextObj.setEmailBodyTextClosing( aszMessageClosing );
	            	
	                aEmailTextObj.setEmailToUser(aszToEmail);
	                aEmailTextObj.setEmailBCCAddress(aszBCCEmail);
	                aEmailTextObj.setEmailFromUser(aszFromUser);
	                aEmailTextObj.setEmailFromUserName(aszFromUserName);

	                iRetCode = m_CronTasksBRLOObj.sendEmailMSG(aEmailTextObj);
	                System.out.println(" ********* CRON OUTPUT cron output - EMAIL SENT to notify applicant of application received - iRetCode is "+iRetCode+" ***********************");
	                
	                // update and insert a record into um_content_field_cvi_email_flag
	                iRetCode = m_CronTasksBRLOObj.insertIntoApplicEmailFlag(  aApplicInfoObj,  null,iApplicConfirmEmailNID );
	            	
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
			iApplicMonthlyReminderNID=544403, iApplicCloseoutNID=544404, iLeadIncompletePg2NID=544405, iLeadIncompletePg3NID=544406, iLeadCloseoutNID=544407;

}