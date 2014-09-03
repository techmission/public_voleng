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

public class CVIEmailBaseMethods   extends ABREBaseBRLO {

    protected String replaceTokenStrings(String text, ApplicationInfoDTO aApplicInfoObj, OrganizationInfoDTO aOrgInfoObj){
    	int iRetCode=0;
    	CronTasksBRLO(  );

    	String aszOrgName = "";
    	if(aOrgInfoObj!=null){
    		aszOrgName = aOrgInfoObj.getORGOrgName();
            ArrayList<PersonInfoDTO> aOrgContactList = new ArrayList<PersonInfoDTO>();
            iRetCode = m_CronTasksBRLOObj.getOrgContactList( aOrgContactList, aOrgInfoObj.getORGNID() );

    		String element=null;
        	StringBuilder sbOppEmailList = new StringBuilder();
    	    Iterator<PersonInfoDTO> itrOrgContact = aOrgContactList.iterator();
    	    while (itrOrgContact.hasNext()) {
    	      element = itrOrgContact.next().getUSPEmail1Addr();
    	      if(sbOppEmailList.length()>1){
    		      sbOppEmailList.append(", ");
    	      }
    	      sbOppEmailList.append(element);
    	    }    	    
            text=text.replaceAll("\\{token:org_email\\}", sbOppEmailList.toString() );
    	}
    	String aszInternType = "";
    	String[] a_aszInternTypes = aApplicInfoObj.getInternTypesArray();
    	int iInternTypesNum =a_aszInternTypes.length; 
    	for(int x=0; x<iInternTypesNum; x++){
    		String aszInternTypeTemp = a_aszInternTypes[x];
    		if(x>0){
    			if(x==iInternTypesNum-1){
    				aszInternType += ", and the ";
    			}else{
    				aszInternType += ", the ";
    			}
    		}
    		aszInternType += aszInternTypeTemp;
    	}
    	String aszGender = aApplicInfoObj.getGender();
    	String aszCapitalHeOrShe = "He";
    	String aszHeOrShe = "he";
    	String aszHisOrHer = "his";
    	String aszHimOrHer = "him";
    	if(aszGender.equalsIgnoreCase("female")){
    		aszCapitalHeOrShe = "She";
    		aszHeOrShe = "she";
    		aszHisOrHer = "her";
    		aszHimOrHer = "her";    		
    	}
    	
        text=text.replaceAll("\\{token:org_name\\}", aszOrgName );
    	text=text.replaceAll("\\{token:first_name\\}", aApplicInfoObj.getNameFirst());
    	text=text.replaceAll("\\{token:last_name\\}", aApplicInfoObj.getNameLast());
    	text=text.replaceAll("\\{token:applic_nid\\}", aApplicInfoObj.getNID()+"");
    	text=text.replaceAll("\\{token:city\\}", aApplicInfoObj.getAddrCity());
    	text=text.replaceAll("\\{token:state\\}", aApplicInfoObj.getAddrStateprov());
    	text=text.replaceAll("\\{token:internship_type\\}", aszInternType);
    	text=text.replaceAll("\\{token:applic_nid\\}", aApplicInfoObj.getNID()+"");
    	text=text.replaceAll("\\{token:applicant_phone\\}", aApplicInfoObj.getPhone());
    	text=text.replaceAll("\\{token:applicant_email\\}", aApplicInfoObj.getEmailAddr());
    	// gender-specific pronoun use:
    	text=text.replaceAll("\\{token:gender-they\\}", aszHeOrShe);
    	text=text.replaceAll("\\{token:gender-their\\}", aszHisOrHer);
    	text=text.replaceAll("\\{token:gender-They\\}", aszCapitalHeOrShe);
    	text=text.replaceAll("\\{token:gender-them\\}", aszHimOrHer);

    	return text;
    }

	protected int sendApplicEmails(ApplicationInfoDTO aApplicInfoObj, EmailInfoDTO aEmailInfoObj, int iEmailNID){
        String aszSMTPServer=this.getSitePropertyValue(ABREAppServerDef.MAIL_SMTP_HOST);
    	String aszSmtpUserName = this.getSitePropertyValue(ABREAppServerDef.MAIL_SMTP_FROM);
    	String aszFromPassword =this.getSitePropertyValue(ABREAppServerDef.MAIL_SMTP_USERPASSWORD);
    	String aszEmailMain =this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
    	String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);

    	aszEmailMain = "info@cityvisioninternships.org";
    	
    	if(aApplicInfoObj==null)	System.out.println("CVIEmailBaseMethods - line 99 - aApplicInfoObj is null");
    	if(aEmailInfoObj==null)	System.out.println("CVIEmailBaseMethods - line 100 - aEmailInfoObj is null");
    	if(iEmailNID<1)	System.out.println("CVIEmailBaseMethods - line 101 - iEmailNID is less than 1");

    	CronTasksBRLO(  );

    	// 1. check to see if it already has a record for this email_nid in the database; if not, proceed.
    	//		if it does have a record, skip to the next itr.hasNext();
    	// check if record exists with email_id=iOrgApplicMatchEmailNID,
//    	int iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag(
//    			aEmailInfoObj, aApplicInfoObj.getNID(), 0, 
//    			ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID, iOrgApplicMatchRecommendEmailNID);
          	
    	int iRetCode = m_CronTasksBRLOObj.loadApplicEmailFlag(
    			aEmailInfoObj, aApplicInfoObj.getNID(), 0, 
    			ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID, iEmailNID);
        if(iRetCode == -111 ){
       		// does not yet exist, so we proceed with composing and sending the email          		
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
        		aszToEmail = replaceTokenStrings(aszToEmail, aApplicInfoObj, null);
            }
            if(aszSubject.length()>0){
            	aszSubject = replaceTokenStrings(aszSubject, aApplicInfoObj, null);
	           }
            if(aszMessageIntro.length()>0){
            	aszMessageIntro = replaceTokenStrings(aszMessageIntro, aApplicInfoObj, null);
	           }
            if(aszMessage.length()>0){
            	aszMessage = replaceTokenStrings(aszMessage, aApplicInfoObj, null);
	           }
            if(aszMessageClosing.length()>0){
            	aszMessageClosing = replaceTokenStrings(aszMessageClosing, aApplicInfoObj, null);
	        }
            aEmailTextObj.setEmailSMTPServerName( aszSMTPServer );
            aEmailTextObj.setEmailFromUserPassword( aszFromPassword );
            aEmailTextObj.setSMTPUserName( aszSmtpUserName );
            aEmailTextObj.setEmailSubjectText(aszSubject);
            aEmailTextObj.setEmailBodyTextIntro( aszMessageIntro + aszMessage);
//          aEmailTextObj.setEmailBodyText( aszMessage );
            aEmailTextObj.setEmailBodyTextClosing( aszMessageClosing );

            aEmailTextObj.setEmailFromUser(aszFromUser);
            aEmailTextObj.setEmailFromUserName(aszFromUserName);
            aEmailTextObj.setEmailToUser(aszToEmail);
            aEmailTextObj.setEmailBCCAddress(aszBCCEmail);

            iRetCode = m_CronTasksBRLOObj.sendEmailMSG(aEmailTextObj);
System.out.println("		********* CRON OUTPUT cron output - EMAIL SENT to "+aszToEmail+" and BCC "+aszBCCEmail+"   - iRetCode is "+iRetCode+" ***********************");
                
            // update and insert a record into um_content_field_cvi_email_flag
            iRetCode = m_CronTasksBRLOObj.insertIntoApplicEmailFlag(  aApplicInfoObj,  null, iEmailNID);
        }
        return iRetCode;
	}

	/**
	* allocate business rule layer object for organization 
	*/
	private void CronTasksBRLO(  ){
		if(null == m_CronTasksBRLOObj){
			m_CronTasksBRLOObj = new CronTasksBRLO();
			m_CronTasksBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer(  ) );
		}
	}

	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private CronTasksBRLO m_CronTasksBRLOObj =null;
	
	private static int iOrgApplicMatchEmailNID = 546506, iOrgApplicMatchRecommendEmailNID = 547238, iApplicConfirmEmailNID = 544402,
			iApplicMonthlyReminderNID=544403, iApplicCloseoutNID=544404, 
			iLeadIncompletePg2NID=544405, iLeadIncompletePg3NID=544406, iLeadCloseoutNID=544407;
	
}