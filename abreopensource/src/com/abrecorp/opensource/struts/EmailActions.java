package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Individual Registration Related Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

// Struts MVC objects
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.WordUtils;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.*;

// data transfer objects
import com.abrecorp.opensource.dataobj.*;
// base objects
import com.abrecorp.opensource.base.*;
// Business Rules Layer objects
import com.abrecorp.opensource.voleng.brlayer.*;

import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import java.util.List;;

public class EmailActions extends DispatchAction {

	/**
	* Constructor 
	*/
	public EmailActions() {}

	 /*
     * show email confirm page
     */
     public ActionForward showEmailConfirm(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
            }
        }
        if(session.getAttribute(aszPortal+"_nid") != null ){
            if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
            	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            }
        }
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
     	int iRetCode=0 ;
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
        // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
        // so we'll just forward along to the login page
        boolean b_mobile=false;
        String temp=httpServletRequest.getHeader("host");
        if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
        	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivol.org")	
        ){
            b_mobile=true;
        }
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	if(b_mobile==true){
              	return actionMapping.findForward( "showlogin" );
           	}else{
           		return actionMapping.findForward( "loginstatus" );
           	}
         }
      	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	if(b_mobile==true){
              	return actionMapping.findForward( "showlogin" );
           	}else{
           		return actionMapping.findForward( "loginstatus" );
           	}
         }
         
         OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
         iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj);
         
 		EmailInfoDTO aEmailObj = new EmailInfoDTO();
    	httpServletRequest.setAttribute("emailinfo", aEmailObj);
         if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
         	return actionMapping.findForward( "emailconfirm" );
         }
     	if(b_mobile==true){
          	return actionMapping.findForward( "showlogin" );
       	}else{
       		return actionMapping.findForward( "loginstatus" );
       	}
     }
     // end-of method showEmailConfirm()
    

 	public ActionForward showEmail(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
 	{
 		return sendEmail( actionMapping,  actionForm,  httpServletRequest,  httpServletResponse);
	}
     
     /**
	* sending email from the email page (iwanttohelp.jsp)
	*/
	public ActionForward sendEmail(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
		getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
			if(aszPortalNID.length()==0){
				httpServletRequest.setAttribute("redirectpage","noportalexists");
				return actionMapping.findForward("mappingpage");
			}
		}
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		
    	String aszStatus = "";
		EmailInfoDTO aEmailObj = new EmailInfoDTO();
		if(aszPortal.length()>0){
    	   aEmailObj.setPortalName(aszPortal);
		}
		getLoggedInStatus(httpServletRequest, httpServletResponse);
		int iRetCode=0;
        allocatedIndBRLO( httpServletRequest );
        allocatedEmailBRLO( httpServletRequest );
        allocatedOrgBRLO( httpServletRequest );
        String aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
    	String aszSmtpUserName = m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
    	String aszFromPassword = m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
    	String aszEmailMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
    	String aszEmailSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
    	String aszEmailFaith = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_EMAIL);
    	String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	String aszDomSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
    	String aszDomMainShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
    	String aszDomSecondaryShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
    	String aszDomFaith = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN);
    	String aszDomFaithShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN_SHORT);
    	String aszSiteOrgName = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);

    	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null == aSessDat){
    		OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
    		OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
    		
    		int iOrgNID = aEmailObj.getEmailOrgNID();
    		int iOppNID = aEmailObj.getEmailOppNID();
            iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 

            httpServletRequest.setAttribute( "opp", aOpportObj );
            httpServletRequest.setAttribute( "org", aOrgInfoObj );
        	return actionMapping.findForward( "opppubliclisting" );
    	}
        boolean b_FaithFree=false;
    	if(		(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )) || 
    			(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )) || 
    			(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP )) || 
    			(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELPSIGNIN )) 
    	){
    		b_FaithFree=true;
    	}

    	// need to load the form now.
        iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aEmailObj, true);
        
        
		// contact person info
    	String aszApplicantEmailAddress = aEmailObj.getEmailFromUser();
        String aszMessage = aEmailObj.getEmailBodyText(); 
    	String aszSubdom = m_BaseHelp.getFormData( oFrm, "subdom" ); 

		if(aEmailObj.getEmailSubdom().length() < 2)
			aEmailObj.setEmailSubdom(aszDomMain);
		String aszSubdomain = aEmailObj.getEmailSubdom();

		if (aszSubdomain.equalsIgnoreCase(aszDomMain))
			aszSubdomain = aszDomMainShort;
		else if (aszSubdomain.equalsIgnoreCase(aszDomSecondary))
			aszSubdomain = aszDomSecondaryShort;
		else if (aszSubdomain.equalsIgnoreCase(aszDomFaith))
			aszSubdomain = aszDomFaithShort;
		else if (aszSubdomain.equalsIgnoreCase("www.catholicvolunteering.org"))
			aszSubdomain = "CatholicVolunteering.org";
		if(aszPortal.length()>0)
			aszSubdomain += "/" + aszPortal;
		String aszSiteEmail=aszEmailMain;
    	if(aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomSecondaryShort) ||
    			aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomSecondary)){
    		aszSiteEmail = aszEmailSecondary;
    	}else if(aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomFaithShort) ||
    			aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomFaith)){
    		aszSiteEmail = aszEmailFaith;
    	}

        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        allocatedOrgBRLO( httpServletRequest );
        
//        aOpportObj.setPortalUID( aszPortalUID);
        if(bNatlAssoc==true){
       	 aOpportObj.setRequestType("natlassoc");
        }


    	// email content - volunteer info
    	int iVolUID = aCurrentUserObj.getUserUID();
    	aEmailObj.setEmailVolUID(iVolUID);
		int iUserAlreadyApplied = m_EmailBRLOObj.loadEmail( aEmailObj ); 

   		//first get the email_id from the application_inits table
		iRetCode = m_IndBRLOObj.getEmailId( aEmailObj, 10 );
   		
		//then add email to db table emailinfo
        aEmailObj.setEmailSentStatus("initial Click");
		iRetCode = m_IndBRLOObj.createEmail( aEmailObj, iRetCode );

		
        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aEmailObj.getEmailOppNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
        aEmailObj.setEmailSentStatus("loaded opportunity");
    	int iOppID = aOpportObj.getOPPNID();
    	int iOrgID = aOpportObj.getORGNID();
    	String aszOppName = aOpportObj.getOPPTitle();
    	String aszOrgName = aOpportObj.getOPPOrgName();
    	String aszOrgAddr1 =  aOpportObj.getOPPAddrLine1();
    	String aszOrgCity = aOpportObj.getOPPAddrCity();
    	String aszOrgState =  aOpportObj.getOPPAddrStateprov();
    	String aszOrgProv =  aOpportObj.getOPPAddrOthrprov();
    	String aszOrgCountry = aOpportObj.getOPPAddrCountryName();
    	aszOrgCountry = aszOrgCountry.toUpperCase(); // so that us shows as US in the email
		String aszOppBkgrnd = aOpportObj.getOPPBackgroundCheck();
    	if(aszOppBkgrnd.length() > 1){
    		if (aszOppBkgrnd.equalsIgnoreCase("Yes")){
    			aszOppBkgrnd="\n\nThis organization requires a background check.";
    		}
    		else	aszOppBkgrnd="";
    	}
    	String aszOppReference = aOpportObj.getOPPReferenceReq(); 
    	if(aszOppReference.length() > 1){
    		if (aszOppReference.equalsIgnoreCase("Yes")){
    			aszOppReference="\n\nThis organization requires references.";
    		}
    		else	aszOppReference="";
    	}
    	
    	aEmailObj.setEmailOrgName(aszOrgName);
    	aEmailObj.setEmailOppName(aszOppName);
    	aEmailObj.setEmailOrgAddr1(aszOrgAddr1);
    	aEmailObj.setEmailOrgCity(aszOrgCity);
    	aEmailObj.setEmailOrgState(aszOrgState);
    	aEmailObj.setEmailOrgProv(aszOrgProv);
    	aEmailObj.setEmailOrgCountry(aszOrgCountry);
    	
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );
		httpServletRequest.setAttribute("opp", aOpportObj);
        if(iRetCode !=0 ){
        	// return to a diff page; failed to load the opportunity
        }
        
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID(aOpportObj.getORGNID());
        aEmailObj.setEmailOrgNID(aOpportObj.getORGNID());
        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
        
        aEmailObj.setEmailSentStatus("loaded org");
    	// org, opp info to list
    	String aszOrgPh = aOrgInfoObj.getORGOrgPhone1();
    	String aszOrgWeb =  aOrgInfoObj.getORGWebpage();
    	aEmailObj.setEmailOrgWeb( aszOrgWeb );   	
    	aEmailObj.setEmailOrgPhone(aszOrgPh);

    	iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );
        
		String aszVolHasUploaded = aCurrentUserObj.getUSPResumeFilePath();
    	String aszVolRailsID = "" + aCurrentUserObj.getUserRailsID();
    	String aszUserName = aCurrentUserObj.getUSPUsername();
    	String aszVolFirstN = aCurrentUserObj.getUSPNameFirst();
    	String aszVolLastN = aCurrentUserObj.getUSPNameLast();
    	String aszVolPhone1 = aCurrentUserObj.getUSPPhone1();
    	String aszVolPhone1Type = aCurrentUserObj.getUSPPhone1Type();
    	String aszVolPhone2 = aCurrentUserObj.getUSPPhone2();
    	String aszVolPhone2Type = aCurrentUserObj.getUSPPhone2Type();
		String aszSkillTypes = aCurrentUserObj.getUSPSkillTypes();
		String aszRailsSkills = aCurrentUserObj.getUserRailsSkills();
    	String aszVolChristian = aCurrentUserObj.getUSPChristianP(); 
    	if(	b_FaithFree==true ){
    		aszVolChristian = "";
    	}else{
        	if(aszVolChristian.length() < 2){
        		if ((aCurrentUserObj.getUSPChristianP().equalsIgnoreCase("Y")) || (aCurrentUserObj.getUSPChristianP().equalsIgnoreCase("Yes")) ){
       				aszVolChristian="Is this person a Christian?   Yes";
        		}else if ( (aCurrentUserObj.getUSPChristianP().equalsIgnoreCase("N")) || (aCurrentUserObj.getUSPChristianP().equalsIgnoreCase("No")) ){
       				aszVolChristian="Is this person a Christian?   No";
        		}else {
       				aszVolChristian="This person has not indicated their faith.";
        		}
        	}    		
    	}

    	aEmailObj.setEmailVolFN(aszVolFirstN);
    	aEmailObj.setEmailVolPhone1(aszVolPhone1);
    	aEmailObj.setEmailVolChris(aszVolChristian);
    	aEmailObj.setEmailVolLN(aszVolLastN);
		
        // ~ July 9, 2013
        List<String> errors = m_EmailBRLOObj.checkForRequiredDocuments(aEmailObj, aCurrentUserObj, aOpportObj);
        if(errors.size() > 0) {
        	aszStatus = "checkForRequiredDocuments returned errors: "+errors;
            aEmailObj.setEmailSentStatus(aszStatus);
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

    		String path = aszRailsPrefixPath + "cor~voleng~positions~" + aOpportObj.getOPPNID() + "~apply?";
        	String separator = "";
        	
        	aszStatus = "checkForRequiredDocuments returned errors: ";

        	for(String e : errors) {
        		aszStatus+= ";                       " + e;
        		path += (separator + "errors[]=" + URLEncoder.encode(e, "UTF-8"));
        		separator = "&";
        	}
            aEmailObj.setEmailSentStatus(aszStatus);
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

    		httpServletRequest.setAttribute("redirect", path);
        	return actionMapping.findForward("mappingpage");
        }
        
        boolean b_isJob = false;
        String aszUrlAlias =  aOpportObj.getOPPUrlAlias();
        if(aszUrlAlias.startsWith("job/") || aszUrlAlias.startsWith("jobs/")){
        	 b_isJob=true;
        }
        String aszApplicantType = "volunteer";
        String aszOpportunity = "opportunity";
        String aszOpportunityVerbose = "volunteer opportunity";
    	String aszVolCorHR = "current volunteer coordinator";
        if(b_isJob==true){
        	aszApplicantType="applicant";
        	aszOpportunity="job";
        	aszOpportunityVerbose="job opportunity";
        	aszVolCorHR="HR department";
        }

    	// load contact user information
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
        iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );    	

        aEmailObj.setEmailSentStatus("loaded contact user");
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

        String aszContactFN = aContactPersonObj.getUSPNameFirst();
    	String aszContactLN = aContactPersonObj.getUSPNameLast();
    	String aszOrgEmail =  aContactPersonObj.getUSPEmail1Addr(); 

    	aEmailObj.setEmailContactFN(aszContactFN);
    	aEmailObj.setEmailContactLN(aszContactLN);
   	
    	String aszOppURLAddress = "";
		String aszSubdomainPortal = aszSubdomain;
		if(aszSubdomain.startsWith("ChristianVolunteering.org")){
			aszOppURLAddress += "http://www.ChristianVolunteering.org";
		}else{
			aszOppURLAddress += "http://" + aszSubdomain;
		}
		if(aszPortal.length()>0){
			aszOppURLAddress += "/" + aszPortal;
			aszSubdomainPortal = aszSubdomain + "/" + aszPortal;
		}
		
		String aszRequiredDocs = "";
		boolean b_requiredDocs = false;
		if(aOpportObj.getResumeRequired() != 0 ||
				aOpportObj.getCoverLetterRequired() != 0 ||
				aOpportObj.getQuestionnaireType().equals("on_paper") ||
				aOpportObj.getRequiredDocuments().size()>0
		){
			b_requiredDocs = true;
			aszRequiredDocs = "  \nTo view the applicant's required document(s), please download the file from " +
					"http://www.christianvolunteering.org/email.do?method=showVolunteerReferrals&listtype=orgmanagement&orgnid="+aOpportObj.getORGNID()+".";
		}
		
		
		aszOppURLAddress += "/"+aOpportObj.getOPPUrlAlias()+".jsp";
    	httpServletRequest.setAttribute("emailinfo", aEmailObj);
		//    	should store ALL OF THE REST OF THE MESSAGE HERE, IN EMAIL
		String aszSubject = aszSubdomainPortal + " Referral: " + aszOppName;

	   	if(aszVolHasUploaded.length() > 0){
	       	aszVolHasUploaded = "  If you'd like to download a copy of this individual's resume, please log in and go to " +
       			"http://www.christianvolunteering.org/profiles/" + aszUserName;
	   	}
		String msgTextAddress = "Dear " + aszContactFN + " " + aszContactLN + ",\n\n";
		String msgTextIntro = 
				"The following " + aszApplicantType + " has shown interest in your " + aszOpportunity + " posting with" + " " + aszSubdomain + 
				".  Please note, " + aszSubdomain + " does not interview or check references of " + aszApplicantType + "s to verify full compliance " +
				"with your organization, as that is the responsibility of your organization." +aszVolHasUploaded+
				"\n\nIt is very important that you either send an E-mail to " + aszApplicantEmailAddress + " or respond " +
				"by phone to " + aszVolFirstN + " " + aszVolLastN + " at  " + aszVolPhone1 + " in a timely manner, " +
				"as the " + aszApplicantType + " is willing and waiting for your response.";    
		if(aszMessage.length()>0){
			msgTextIntro += "\n\nMessage from " + aszApplicantType +
					"\n----------------------------------------------------------\n" +
					aszMessage +
					"\n----------------------------------------------------------\n" ;
		}
		String aszPhoneInfo = "Phone:   " + aszVolPhone1;
		if(aszVolPhone1Type.length()>0)	aszPhoneInfo += " (" + aszVolPhone1Type + ")";
		if(aszVolPhone2.length()>0)	aszPhoneInfo += "\n         " + aszVolPhone2;
		if(aszVolPhone2Type.length()>0)	aszPhoneInfo += " (" + aszVolPhone2Type + ")";
		String aszAllSkills = "";
		if(aszSkillTypes.length() > 0 || aszRailsSkills.length() > 0)
			aszAllSkills = "Skills:  ";
		aszAllSkills += aszSkillTypes;
		if(aszRailsSkills.length()>0){
			if(aszAllSkills.length()>10)	aszAllSkills += "\n         ";
			aszAllSkills += aszRailsSkills;
		}
		String msgBodyIntro = msgTextAddress + msgTextIntro +
    		"\n\n" +
    		"Organization Name:   " + aszOrgName + "\n" +
    		WordUtils.capitalize(aszOpportunity) + " Name:    " + aszOppName + "\n" +
    		WordUtils.capitalize(aszOpportunity) + " Profile: " + aszOppURLAddress +
    		"\n\n\n" +
    		WordUtils.capitalize(aszApplicantType) + " Contact Information:" + "\n\n" +
    		"Name:    " + aszVolFirstN + " " + aszVolLastN + "\n" +
    		"Email:   " + aszApplicantEmailAddress + "\n" +
    		aszPhoneInfo + "\n" +
    		"Profile: " + "http://www.christianvolunteering.org/profiles/" + aszUserName + //aszVolProfile + 
    		
    		aszRequiredDocs +
    		
    		"\n" + aszAllSkills;
    	if(	b_FaithFree==true ){
    		msgBodyIntro = msgBodyIntro + 
    		"\n\n----------------------------------------------------------\n" ;
    	}else{
    		msgBodyIntro = msgBodyIntro + 
    		"\n\n" + aszVolChristian + 
    		"\n\n----------------------------------------------------------\n" ;
    	}
        
    	String msgTextClosing = 
   			"Who should receive these emails? If you'd like to edit these settings, log in and select this " + aszOpportunity + 
   			" and click on \"Contacts\", or go directly to http://";
   		
   		if(aszSubdomain.equalsIgnoreCase(aszDomMainShort))				msgTextClosing += "www.";
   		else if(aszSubdomainPortal.startsWith("ChristianVolunteering"))	msgTextClosing += "www.";
   		else if(aszSubdomainPortal.startsWith("iVolunteering"))			msgTextClosing += "www.";
   		else if(aszSubdomainPortal.startsWith("ChurchVolunteering"))	msgTextClosing += "www.";
   		else if(aszSubdomainPortal.startsWith("CatholicVolunteering"))	msgTextClosing += "www.";
   		
   		msgTextClosing = msgTextClosing + aszSubdomainPortal + "/org.do?method=showOppOrgContacts&orgnid=" + iOrgID + "&oppnid=" + iOppID +
	    	" and confirm that the correct Contacts are set to \"Receive Emails\".";
   		msgTextClosing = msgTextClosing + "\n\nThis email has been sent to you through " + aszSubdomain + 
        	". If you have any questions, please email us at " + aszSiteEmail + 
        	".\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName + ".\n" + 
        	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";
    	httpServletRequest.setAttribute("emailinfo", aEmailObj);

    	String aszSMTPAuthenticate = "Y";
    	aEmailObj.setEmailSubjectText( aszSubject );
    	aEmailObj.setEmailBodyTextIntro( msgBodyIntro );
    	aEmailObj.setEmailBodyText( aszMessage );
    	aEmailObj.setEmailBodyTextClosing( msgTextClosing );
    	aEmailObj.setEmailSMTPServerName( aszSMTPServer );
    	aEmailObj.setEmailFromUserPassword( aszFromPassword );
    	aEmailObj.setSMTPUserName( aszSmtpUserName );
    	aEmailObj.setEmailSubdom( aszSubdom );   	
        aEmailObj.setSMTPAuthRequired( true );
        if(aszSMTPAuthenticate.equalsIgnoreCase("Y"))
        	aEmailObj.setSMTPAuthRequired( true );
    	else 
        	aEmailObj.setSMTPAuthRequired( false );
	    
        

        aEmailObj.setEmailSentStatus("email text updated; contact list not yet loaded");
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

        
        // make sure all the EMAILs are collected for Volunteer Contacts on an opportunity
        ArrayList<PersonInfoDTO> aList = new ArrayList<PersonInfoDTO>();
		iRetCode = m_OrgBRLOObj.getOppVolContactList( aList, iOppID ); // should make new 2011-03-02 *************************************************************************

        aEmailObj.setEmailSentStatus("contact list loaded");
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

		String element=null;
    	StringBuilder sbOppEmailList = new StringBuilder();
	    Iterator<PersonInfoDTO> itr = aList.iterator();
	    while (itr.hasNext()) {
	      element = itr.next().getUSPEmail1Addr();
	      sbOppEmailList.append(element);
	      sbOppEmailList.append(", ");
	    }
	    String aszOppEmailList = sbOppEmailList.toString();
	    if(iRetCode==0 && aszOppEmailList.length()>0){
	    	aEmailObj.setEmailOrg( aszOppEmailList );
	    	aEmailObj.setEmailToUsers( aszOppEmailList );
	    	aEmailObj.setEmailToUser( aszOppEmailList );
	    	aEmailObj.setEmailOrg(aszOppEmailList);
	    }else{
	    	aEmailObj.setEmailOrg( aszOrgEmail );
	    	aEmailObj.setEmailToUsers( aszOrgEmail );
	    	aEmailObj.setEmailToUser( aszOrgEmail );
	    	aEmailObj.setEmailOrg(aszOrgEmail);	    	
	    }

        aEmailObj.setEmailSentStatus("contact list confirmed");
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );


	    String action = "emailconfirm";
		String aszSendEmailOK = "Y";
    	if(aszSendEmailOK.equalsIgnoreCase("Y")){
    		aszSendEmailOK="N";
    		// first make sure that this volunteer did not already apply to this opportunity
//            iRetCode = m_EmailBRLOObj.loadEmail( aEmailObj ); 
            if(iUserAlreadyApplied == 1 && aEmailObj.getEmailVolUID()!=0){ // this user has already applied to this position; load page with its contact info and inform user of match on sent_dt
        		// clear out the email for signin process
            	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	// clear the Token Key for this session for the next login
            	if(aSessDat.getTokenKey() != null && aSessDat.getTokenKey().length()>1) aSessDat.setTokenKey(null);
            	if(aSessDat.getOrgNID() > 1) aSessDat.setOrgNID(null);
            	if(aSessDat.getOppNID() > 1) aSessDat.setOppNID(null);
            	if(aSessDat.getSubdomain() != null && aSessDat.getSubdomain().length() > 1) aSessDat.setSubdomain(null);
            	if(aSessDat.getSiteEmail() != null && aSessDat.getSiteEmail().length() > 1) aSessDat.setSiteEmail(null);
            	//set error message for the user

                aEmailObj.setEmailSentStatus("user already applied to this opportunity");
        		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

            	aEmailObj.setErrorMsg("You have previously emailed and shown interest in this " + aszOpportunity + 
            			" on " + aEmailObj.getEmailSentDt() + " EST. "+
            			"\n\rIf you would like to reach the Contact Person, see the information provided below:");
            	return actionMapping.findForward( "emailconfirm" );
            }
    		

            aEmailObj.setEmailSentStatus("just before send email");
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );
/*
    		iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
    		
    		if(0 == iRetCode) {
    			aEmailObj.setEmailSentStatus( "sent" );
    		} else {
    			aEmailObj.setEmailSentStatus( aEmailObj.getErrorMsg() );	
    		}
    		
    		//add email to db
            aEmailObj.setEmailSentStatus("sent - just after send email");
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );
*/    		
            if(aOpportObj.getQuestionnaireType() != null 
            	&& aOpportObj.getQuestionnaireType().equals("online")
            	&& aOpportObj.getOpportunitiesQuestionnaireId() > 0
            	&& m_IndBRLOObj.getQuestionnaireInstanceId(aCurrentUserObj, aOpportObj, aszSiteVersion) <= 0
            	&& 0 == iRetCode
            ) {
            	String aszRailsNewQuestionnaireInstance = aszRailsPrefixPath + "cor~voleng~opportunities_questionnaires~" + aOpportObj.getOpportunitiesQuestionnaireId() + "~instances~new";
            	httpServletRequest.setAttribute("redirect", aszRailsNewQuestionnaireInstance);

                aEmailObj.setEmailSentStatus("sent - requires questionnaire: "+aszRailsNewQuestionnaireInstance);
        		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 0 );

            	action = "mappingpage";
            }

            
    		allocatedApplBRLO( httpServletRequest );

            aEmailObj.setEmailSentStatus("sent - just before uploadRequiredDocuments");
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );

    		m_EmailBRLOObj.uploadRequiredDocuments(aEmailObj, aOpportObj, aCurrentUserObj, getServlet().getServletContext().getRealPath("/"), m_IndBRLOObj, m_AppCodesBRLOObj, httpServletRequest);
    		

    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
            aEmailObj.setEmailSentStatus("sent - just after uploadRequiredDocuments");
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );

    		
    		

    		iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
    		
    		if(0 == iRetCode) {
    			aEmailObj.setEmailSentStatus( "sent" );
    		} else {
    			aEmailObj.setEmailSentStatus( aEmailObj.getErrorMsg() );	
    		}
    		
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );
    		
    		
    		
    		
    		

    		// need to clear out the Email Obj here, rather than store it all
    	} else {
    		aEmailObj.appendErrorMsg("You chose to cancel.");

            aEmailObj.setEmailSentStatus("sent - chose to cancel w required docs");
    		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );


    	}
    	
    	// send email to volunteer
    	httpServletRequest.setAttribute("emailinfo", aEmailObj);

    	aEmailObj.setSMTPAuthRequired( true );

    	/**
    	 * **************************************************************************************
    	 * END of organization's email
    	 * **************************************************************************************
    	 * **************************************************************************************
    	 * **************************************************************************************
    	 * **************************************************************************************
    	 * **************************************************************************************
    	 * BEGINNING of volunteer's email
    	 * **************************************************************************************
    	 */

        aEmailObj.setEmailSentStatus("sent - start vol's email");
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );

    	
    	//beginning of volunteer's email code    	
    	httpServletRequest.setAttribute("emailinfo", aEmailObj);

    	//    	should store ALL OF THE REST OF THE MESSAGE HERE, IN EMAIL
        msgTextAddress = "Dear " + aszVolFirstN + " " + aszVolLastN + ",\n\n";
        msgTextIntro = "Thank you for your interest in the following " + aszOpportunityVerbose + " "+
        	"through " + aszSubdomain + ". An email has been sent to " + 
        	aszOrgName + " telling them that you are interested, along with your resume. " +
        	"\n\nWe suggest that you contact " + aszOrgName + " either by phone (asking for the contact below or their " +
        	aszVolCorHR + ") or by E-mail at " + aszOrgEmail + 
        	".\n\n";
    	if(aszOrgCountry.length() > 1 && (!(aszOrgCountry.equalsIgnoreCase("US")))){
    		msgTextIntro = msgTextIntro + "Please note, that " + aszSubdomain + " does not verify the organizations posted in our directory.  " +
    				"Because of this, we recommend that before you conduct any international travel for any " + aszOpportunity +", you check references from " +
    				"sources you trust.  You can find our full list of recommendations for undertaking international opportunities at: " +
    				"http://www.urbanministry.org/stmsuggestions. \n\n";
        	if(aEmailObj.getEmailSTMReferencesText().length() > 2){
            	msgTextIntro = msgTextIntro + "----------------------------------------------------------\n\n"+
            		"References: This organization has provided references of individuals or churches that have participated " +
            		"in their short term missions trip opportunities:\n\n" +
            		aEmailObj.getEmailSTMReferencesText() + "\n\n----------------------------------------------------------\n\n" ;
        	}
    	}
    	
    	msgTextIntro = msgTextIntro +
        	"If you don't hear back from this organization in a " +
        	"reasonable time frame, please contact us at "+ aszSiteEmail + ".";
        msgBodyIntro = msgTextAddress + msgTextIntro +
    		"\n\n----------------------------------------------------------\n\n" +
    		WordUtils.capitalize(aszOpportunity) + " Title:    " + aszOppName + "\n" +
    		WordUtils.capitalize(aszOpportunity) + " Profile: " + aszOppURLAddress +
    		"\n\n\n" +
    		"Organization Information:\n" +  
        	"-------------------------\n" + 
    		"Organization Name:   " + aszOrgName + 
    		"\n\nAddress:       " + aszOrgAddr1 + "\n                     " + aszOrgCity + 
    		", " + aszOrgState + aszOrgProv + "\n                     " + aszOrgCountry + 
    		"\nPhone:               " + aszOrgPh + "\nWebsite:             " + aszOrgWeb + 
    		"\n\nContact Person:     " + aszContactFN + " " + aszContactLN + 
    		"\nContact Email(s):    " + aszOppEmailList;
        if(aszOppBkgrnd.length()>1){
            msgBodyIntro = msgBodyIntro + aszOppBkgrnd;
        }
        if(aszOppReference.length()>1){
            msgBodyIntro = msgBodyIntro + aszOppReference;
        }
        msgBodyIntro = msgBodyIntro + "\n\n----------------------------------------------------------\n\n" ;
    	if(aszOrgCountry.length() < 1 || ((aszOrgCountry.equalsIgnoreCase("US")))){
        	if(aEmailObj.getEmailSTMReferencesText().length() > 2){
            	msgBodyIntro = msgBodyIntro + 
            		"References: This organization has provided references of individuals or churches that have participated " +
            		"in their short term missions trip opportunities:\n\n" +
            		aEmailObj.getEmailSTMReferencesText() + "\n\n----------------------------------------------------------\n\n" ;
        	}
    	}

       
    	msgTextClosing =  
        	"\nThis email has been sent to you through " + aszSubdomain + ". " +
        	"\nIf you have any questions, please email us at "+ aszSiteEmail + 
        	".\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName + ".\n" + 
        	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";

    	aEmailObj.setEmailFromUser( aszSiteEmail );
    	aEmailObj.setEmailContactFN( aszContactFN );
    	aEmailObj.setEmailContactLN( aszContactLN );
    	aEmailObj.setEmailOppName( aszOppName );
    	aEmailObj.setEmailOrgName( aszOrgName );
    	aEmailObj.setEmailOrgAddr1( aszOrgAddr1 );
    	aEmailObj.setEmailOrgCity( aszOrgCity );
    	aEmailObj.setEmailOrgState( aszOrgState );
    	aEmailObj.setEmailOrgProv( aszOrgProv );
    	aEmailObj.setEmailOrgCountry( aszOrgCountry );
 //   	aEmailObj.setEmailOrg( aszOrgEmail );
    	aEmailObj.setEmailOrgPhone( aszOrgPh );
    	aEmailObj.setEmailToUser( aszApplicantEmailAddress );
    	aEmailObj.setEmailSubjectText( aszSubject );
    	aEmailObj.setEmailBodyTextIntro( msgBodyIntro );
    	aEmailObj.setEmailBodyText( "" );
    	aEmailObj.setEmailBodyTextClosing( msgTextClosing );
    	aEmailObj.setEmailBodyTextRes(aszVolHasUploaded);//( aszMessageRes );
    	aEmailObj.setEmailResumeFilePath( aszVolHasUploaded );
    	aEmailObj.setEmailVolFN( aszVolFirstN );
    	aEmailObj.setEmailVolLN( aszVolLastN );
    	aEmailObj.setEmailOppBkgrnd( aszOppBkgrnd );   	

		
    	if(aszSMTPAuthenticate.equalsIgnoreCase("Y")){
	    	aEmailObj.setSMTPAuthRequired( true );
		} else {
	    	aEmailObj.setSMTPAuthRequired( false );
		}
	
		aszSendEmailOK = "Y";
		if(aszSendEmailOK.equalsIgnoreCase("Y")){
			aszSendEmailOK="N";
			iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
		}
		// end of volunteer email code

        aEmailObj.setEmailSentStatus("sent - sent vol's email");
        
        // switch back before running the update on the db row
        aEmailObj.setEmailToUser(aszOrgEmail);
        aEmailObj.setEmailFromUser(aszApplicantEmailAddress);
        
		iRetCode = m_IndBRLOObj.updateEmail( aEmailObj, 1 );


		// clear out the email for signin process
    	aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );

    	// clear the Token Key for this session for the next login
    	if(aSessDat.getTokenKey() != null && aSessDat.getTokenKey().length()>1){
    		aSessDat.setTokenKey(null);
    	}
    	if(aSessDat.getOrgNID() > 1){
    		aSessDat.setOrgNID(null);
    	}
    	if(aSessDat.getOppNID() > 1){
    		aSessDat.setOppNID(null);
    	}
    	if(aSessDat.getSubdomain() != null && aSessDat.getSubdomain().length() > 1){
    		aSessDat.setSubdomain(null);
    	}
    	if(aSessDat.getSiteEmail() != null && aSessDat.getSiteEmail().length() > 1){
    		aSessDat.setSiteEmail(null);
    	}

		
    	//2006-09-08
    	return actionMapping.findForward( action );

    }
    // end-of method showEmail()

    
    private void allocatedApplBRLO(HttpServletRequest aRequest) {
		if(null == aRequest) return;
		if(null == m_AppCodesBRLOObj){
			m_AppCodesBRLOObj = new ApplicationCodesBRLO();
			m_AppCodesBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}

	/**
	* sending email from the applicant's user profile/resume page - contact user
	*/
	public ActionForward contactUserEmail(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
   {
		getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
			if(aszPortalNID.length()==0){
				httpServletRequest.setAttribute("redirectpage","noportalexists");
				return actionMapping.findForward("mappingpage");
			}
		}
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		
		EmailInfoDTO aEmailObj = new EmailInfoDTO();
		if(aszPortal.length()>0){
			aEmailObj.setPortalName(aszPortal);
		}
		getLoggedInStatus(httpServletRequest, httpServletResponse);
		int iRetCode=0;
		allocatedIndBRLO( httpServletRequest );
	    allocatedEmailBRLO( httpServletRequest );
	    allocatedOrgBRLO( httpServletRequest );
	    String aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
	   	String aszSmtpUserName = m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
	   	String aszFromPassword = m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
	   	String aszEmailMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
	   	String aszEmailSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
	   	String aszEmailFaith = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_EMAIL);
	   	String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
	   	String aszDomSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
	   	String aszDomMainShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
	   	String aszDomSecondaryShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
	   	String aszDomFaith = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN);
	   	String aszDomFaithShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN_SHORT);
	   	String aszSiteOrgName = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);

	   	String aszRailsEdit = aszRailsPrefixPath + aszRailsEditPath;
	   	DynaActionForm oFrm = (DynaActionForm)actionForm;
	   	AppSessionDTO aSessDat=null;
	   	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	   	if(null == aSessDat){
			  httpServletRequest.setAttribute("redirect", aszRailsEdit);
		 	  return actionMapping.findForward("mappingpage");			  
	   	}

   	// need to load the form now.
       iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aEmailObj, false);
		// contact person info
       String aszSendingEmailAddress = aCurrentUserObj.getUSPEmail1Addr();
       String aszMessage = aEmailObj.getEmailBodyText(); 
       String aszSubdom = m_BaseHelp.getFormData( oFrm, "subdom" ); 

		if(aEmailObj.getEmailSubdom().length() < 2)
			aEmailObj.setEmailSubdom(aszDomMain);
		String aszSubdomain = aEmailObj.getEmailSubdom();

		if (aszSubdomain.equalsIgnoreCase(aszDomMain))
			aszSubdomain = aszDomMainShort;
		else if (aszSubdomain.equalsIgnoreCase(aszDomSecondary))
			aszSubdomain = aszDomSecondaryShort;
		else if (aszSubdomain.equalsIgnoreCase(aszDomFaith))
			aszSubdomain = aszDomFaithShort;
		else if (aszSubdomain.equalsIgnoreCase("www.catholicvolunteering.org"))
			aszSubdomain = "CatholicVolunteering.org";
		
		if(aszPortal.length()>0)
			aszSubdomain += "/" + aszPortal;
		String aszSiteEmail=aszEmailMain;
	   	if(aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomSecondaryShort) ||
	   			aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomSecondary)){
	   		aszSiteEmail = aszEmailSecondary;
	   	}else if(aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomFaithShort) ||
	   			aEmailObj.getEmailSubdom().equalsIgnoreCase(aszDomFaith)){
	   		aszSiteEmail = aszEmailFaith;
	   	}

       PersonInfoDTO aReceivingIndivObj = new PersonInfoDTO();
       aReceivingIndivObj.setUSPEmail1Addr(aEmailObj.getEmailToUser());
       iRetCode = m_IndBRLOObj.loadUserByOption( aReceivingIndivObj, PersonInfoDTO.LOADBY_EMAIL_AS_CONTACT, aszSiteVersion ); 
       if(iRetCode !=0 ){
       	// return to a diff page; failed to load the opportunity
       }
  	
	   	String aszSendingIndivFN = aCurrentUserObj.getUSPNameFirst();
	   	String aszSendingIndivLN = aCurrentUserObj.getUSPNameLast();
	   	String aszSendingIndivRailsID = "" + aCurrentUserObj.getUserRailsID();
	   	String aszSendingIndivName = aCurrentUserObj.getUSPUsername();
	   	int[] a_iOrgNIDsArray = aCurrentUserObj.getOrgNIDsArray();

	   	String aszVolHasUploaded = aReceivingIndivObj.getUSPResumeFilePath();
	   	String aszVolRailsID = "" + aReceivingIndivObj.getUserRailsID();
	   	String aszUserName = aReceivingIndivObj.getUSPUsername();
	   	String aszReceivingIndivFN = aReceivingIndivObj.getUSPNameFirst();
	   	String aszReceivingIndivLN = aReceivingIndivObj.getUSPNameLast();
		String aszSubdomainPortal = aszSubdomain;
	   	
		// "vol" is always the sending; in this case, it's usually an org contacting a vol, but legacy db field names makes it confusing
	   	aEmailObj.setEmailVolFN(aszSendingIndivFN);
	   	aEmailObj.setEmailVolLN(aszSendingIndivLN);
	   	aEmailObj.setEmailVolUID(aCurrentUserObj.getUserUID());
	   	
	   	aEmailObj.setEmailContactFN(aszReceivingIndivFN);
	   	aEmailObj.setEmailContactLN(aszReceivingIndivLN);
	   	aEmailObj.setEmailOrgNID(aReceivingIndivObj.getUserUID()); // so that this is stored somewhere; "org" is always receiving
	   	
	   	aEmailObj.setEmailSubdom(aszSubdomain);
		
		String aszURL = "http://" + aszSubdomain;
		if(aszSubdomain.startsWith("ChristianVolunteering.org")){
			aszURL = "http://www.ChristianVolunteering.org";
		}else if(aszSubdomain.contains("ChurchVolunteering.org")){
			if(aszSubdomain.startsWith("ChurchVolunteering.org")){
				aszURL = "http://www.ChurchVolunteering.org";
			}
		}
		
		String aszProfileURL = aszURL + "/profiles/";
		String aszSendingIndivProfileURL = aszProfileURL + aCurrentUserObj.getUSPUsername(); 
		String aszReceivingIndivProfileURL = aszProfileURL + aReceivingIndivObj.getUSPUsername(); 
		aEmailObj.setEmailReceivingUserName(aReceivingIndivObj.getUSPUsername());
		
		httpServletRequest.setAttribute("emailinfo", aEmailObj);
		
		//    	should store ALL OF THE REST OF THE MESSAGE HERE, IN EMAIL
		String aszSubject = aszSendingIndivFN + " " + aszSendingIndivLN + " Contacted You on " + aszSubdomainPortal;

		String msgTextAddress = "Dear " + aszReceivingIndivFN + " " + aszReceivingIndivLN + ",\n\n";
		String msgTextIntro = 
				aszSendingIndivFN + " " + aszSendingIndivLN + " contacted you on " + aszSubdomainPortal + " with the following " +
				"message:\n" + 
				"\n----------------------------------------------------------\n";
		
		String msgBodyIntro = msgTextAddress + msgTextIntro;
		String msgTextClosing =  
			"\n----------------------------------------------------------\n" +
  			"\nYou can find out more about this person at: " + aszSendingIndivProfileURL;
		if(a_iOrgNIDsArray.length > 0){
			int iOrgNID = a_iOrgNIDsArray[0];
			if(iOrgNID > 0){
				OrganizationInfoDTO aOrgObj = new OrganizationInfoDTO();
				iRetCode = m_OrgBRLOObj.loadOrganizationFromDB(  aOrgObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion);
				if(iRetCode==0){
					msgTextClosing += " or " + aszURL + "/" + aOrgObj.getORGUrlAlias() +".jsp";
				}
			}
		}
		msgTextClosing += "\n\nIf you wish to report this person for sending inappropriate or spam messages, " +
				"please send a message to info@christianvolunteering.org";

	   	aEmailObj.setEmailSubjectText( aszSubject );
	   	aEmailObj.setEmailBodyTextIntro( msgBodyIntro );
	   	aEmailObj.setEmailBodyText( aszMessage );
	   	aEmailObj.setEmailBodyTextClosing( msgTextClosing );
	   	aEmailObj.setEmailSMTPServerName( aszSMTPServer );
	   	aEmailObj.setEmailFromUserPassword( aszFromPassword );
	   	aEmailObj.setSMTPUserName( aszSmtpUserName );
	   	aEmailObj.setEmailSubdom( aszSubdom ); 
	   	aEmailObj.setSMTPAuthRequired( true );
       	aEmailObj.setSMTPAuthRequired( true );
	    
        aEmailObj.setEmailFromUser(aszSendingEmailAddress);
    	aEmailObj.setEmailToUsers( aReceivingIndivObj.getUSPEmail1Addr() );
    	aEmailObj.setEmailToUser( aReceivingIndivObj.getUSPEmail1Addr() );
    	   		
   		iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
   		
   		if(0 == iRetCode) {
   			aEmailObj.setEmailSentStatus( "contacted user" );
   		} else {
   			aEmailObj.setEmailSentStatus( aEmailObj.getErrorMsg() );	
   		}
   		//add email to db
   		iRetCode = m_IndBRLOObj.createEmail( aEmailObj );
   		
   		// need to clear out the Email Obj here, rather than store it all
		// clear out the email for signin process
	   	aSessDat=null;
	   	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	
	   	// clear the Token Key for this session for the next login
	   	if(aSessDat.getTokenKey() != null && aSessDat.getTokenKey().length()>1){
	   		aSessDat.setTokenKey(null);
	   	}
	   	if(aSessDat.getOrgNID() > 1){
	   		aSessDat.setOrgNID(null);
	   	}
	   	if(aSessDat.getOppNID() > 1){
	   		aSessDat.setOppNID(null);
	   	}
	   	if(aSessDat.getSubdomain() != null && aSessDat.getSubdomain().length() > 1){
	   		aSessDat.setSubdomain(null);
	   	}
	   	if(aSessDat.getSiteEmail() != null && aSessDat.getSiteEmail().length() > 1){
	   		aSessDat.setSiteEmail(null);
	   	}
	
			
	   	//2006-09-08
	   	return actionMapping.findForward( "emailcontactconfirm" );

   }
   // end-of method contactUserEmail()


    /**
     * show showEmailHistory summary page
     */
     public ActionForward showEmailHistory(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
     	getPortalInfo( httpServletRequest, httpServletResponse);
     	
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
       		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
   			httpServletRequest.setAttribute("redirectpage","noportalexists");
   			return actionMapping.findForward("mappingpage");
       	}
       }
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
        // so we'll just forward along to the login page
        boolean b_mobile=false;
        String temp=httpServletRequest.getHeader("host");
        if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
        	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivol.org")	
        ){
            b_mobile=true;
        }
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	
         		return actionMapping.findForward( "showlogin" );
         	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
         	}
         }
      	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
         	}
         }
 		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_IWANTTOHELP );
			  }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
 	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
 	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
          	return actionMapping.findForward("mappingpage");
     //                       	return actionMapping.findForward( "createaccount2" );
		 }
         if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
         	}
         }

         EmailInfoDTO aEmailObj = new EmailInfoDTO();

         allocatedEmailBRLO( httpServletRequest );
         ArrayList aList = new ArrayList();
         //iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aIndivObj.getUserUID() ); 
         int iType=0;
         String orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
         String oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
         String voluid="";//String voluid=m_BaseHelp.getFormData(oFrm,"voluid");

         String aszSiteUseType=m_BaseHelp.getFormData(oFrm,"listtype");
         if(aszSiteUseType.equalsIgnoreCase("orgmanagement")){
        	 iType=EmailInfoDTO.LOADBY_ORGANIZATION;
        	 // check ownership/management rights on the given organization first; else return a dashboard page
        	 if(orgnid==null || orgnid.length()<1){
            	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	 }
  			int iOrgNID = Integer.parseInt(orgnid);
  			aEmailObj.setEmailOrgNID(iOrgNID);
  	        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
  	        aOrgInfoObj.setORGNID( iOrgNID );
  	        aOrgInfoObj.setORGUID( aIndivObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
  	        allocatedOrgBRLO( httpServletRequest );
  	        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
   	        if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
  	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	        }
         }else if(aszSiteUseType.equalsIgnoreCase("oppmanagement")){
        	 iType=EmailInfoDTO.LOADBY_OPPORTUNITY;
        	 // check ownership/management rights on the given opportunity first; else return a dashboard page
        	 if(oppnid==null || oppnid.length()<1){
            	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	 }
   			int iOrgNID = Integer.parseInt(orgnid);
  			int iOppNID = Integer.parseInt(oppnid);
  			aEmailObj.setEmailOppNID(iOppNID);
  	         
  			// might actually want to change and do test on organization, so that we can still show opportunities that may have been removed
  	        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
  	        aOrgInfoObj.setORGNID( iOrgNID );
  	        aOrgInfoObj.setORGUID( aIndivObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
  	        allocatedOrgBRLO( httpServletRequest );
  	        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
   	        if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
  	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	        }
         }else{
        	 iType=EmailInfoDTO.LOADBY_VOLUNTEER;
 			 int iVolUID = aIndivObj.getUserUID();
 			aEmailObj.setEmailVolUID(iVolUID);
         }
         
         iRetCode = m_EmailBRLOObj.getEmailList(  aList, aEmailObj, iType ); 
	        if(iRetCode!=0 && iRetCode!=-1){// doesn't seem to be a list for this user?? somehow this is broken
  	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	        }

         httpServletRequest.setAttribute( "emaillist", aList );
         iRetCode = m_IndActHelp.fillEmailDataForm(oFrm, aEmailObj);
         
         return actionMapping.findForward( "emailhistory" );
     }
     // end-of method showEmailHistory()    


     /**
      * show showVolunteerReferrals summary page
      */
      public ActionForward showVolunteerReferrals(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	int iRetCode=0 ;
     	getPortalInfo( httpServletRequest, httpServletResponse);
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
       		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
   			httpServletRequest.setAttribute("redirectpage","noportalexists");
   			return actionMapping.findForward("mappingpage");
       	}
       }
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
        // so we'll just forward along to the login page
        boolean b_mobile=false;
        String temp=httpServletRequest.getHeader("host");
        if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
        	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivol.org")	
        ){
            b_mobile=true;
        }
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	
          		return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }
       	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aIndivObj) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }
          if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }

          EmailInfoDTO aEmailObj = new EmailInfoDTO();

          allocatedEmailBRLO( httpServletRequest );
          ArrayList aList = new ArrayList();
          //iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aIndivObj.getUserUID() ); 
          int iType=0;
          String orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
          String oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
          String voluid="";//String voluid=m_BaseHelp.getFormData(oFrm,"voluid");

          String aszSiteUseType=m_BaseHelp.getFormData(oFrm,"listtype");
 	        boolean bAdminRole=false;
   	        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
   	        if(! (aszRole==null || aszRole.equals(null))){
   	       	 if(aszRole.equals("siteadmin")){
   	         	bAdminRole=true;
   	       	 }else{
   	       		 aszRole="";
   	       	 }
   	        }
   	        boolean bByContact = false;
   	        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
   	        if(aszReqType.contains("ByContact")){
   	       	 bByContact = true;
   	        }
 	        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
          if(aszSiteUseType.equalsIgnoreCase("orgmanagement")){
	         	iType=EmailInfoDTO.LOADBY_ORGANIZATION;
	         	// check ownership/management rights on the given organization first; else return a dashboard page
	         	if(orgnid==null || orgnid.length()<1){
	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
	   			int iOrgNID = Integer.parseInt(orgnid);
	   			aEmailObj.setEmailOrgNID(iOrgNID);
	   	         aOrgInfoObj.setORGNID( iOrgNID );
	   	         aOrgInfoObj.setORGUID( aIndivObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	   	         allocatedOrgBRLO( httpServletRequest );
	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
	   	         if(	aIndivObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
	   	       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
	   	         ){
	   	       	  	aOrgInfoObj.setSiteAdministratorUID( aIndivObj.getUserUID() ); 
	   	             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
	   	         }else{
	                    if(aIndivObj.getNatlAssociationNID()>0){
	              	       	 aOrgInfoObj.setNatlAssociationNID(aIndivObj.getNatlAssociationNID());
	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
	              	     }else{
	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	              	     }
	   	         }
	   	         if(iRetCode == -111){
	   	          	if(bByContact == true){
	                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
	                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	           	}
	   	          	else	   	         	if(bAdminRole==true){
	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	   	 	          	return actionMapping.findForward( "noaccess" );
	   	         	}else{
	   	         		// org did not exist
	   	             	return actionMapping.findForward( "orgnoresults" );
	   	         	}
	   	         }
	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	   	        }
          }else if(aszSiteUseType.equalsIgnoreCase("oppmanagement")){
	         	 iType=EmailInfoDTO.LOADBY_OPPORTUNITY;
	         	 // check ownership/management rights on the given opportunity first; else return a dashboard page
	         	 if(oppnid==null || oppnid.length()<1){
	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	 }
	    			int iOrgNID = Integer.parseInt(orgnid);
	   			int iOppNID = Integer.parseInt(oppnid);
	   			aEmailObj.setEmailOppNID(iOppNID);
	   	         
	   			// might actually want to change and do test on organization, so that we can still show opportunities that may have been removed
	   	         aOrgInfoObj.setORGNID( iOrgNID );
	   	         aOrgInfoObj.setORGUID( aIndivObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	   	         allocatedOrgBRLO( httpServletRequest );
	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
	   	         if(	aIndivObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
	   	       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
	   	         ){
	   	       	  	aOrgInfoObj.setSiteAdministratorUID( aIndivObj.getUserUID() ); 
	   	             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
	   	         }else{
	                    if(aIndivObj.getNatlAssociationNID()>0){
	              	       	 aOrgInfoObj.setNatlAssociationNID(aIndivObj.getNatlAssociationNID());
	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
	              	     }else{
	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	              	     }
	   	         }
	   	         if(iRetCode == -111){
		   	        if(bByContact == true){
		                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
		                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
		           	}else if(bAdminRole==true){
	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	   	 	          	return actionMapping.findForward( "noaccess" );
	   	         	}else{
	   	         		// org did not exist
	   	             	return actionMapping.findForward( "orgnoresults" );
	   	         	}
	   	         }
	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	   	        }
          }else{
	         	iType=EmailInfoDTO.LOADBY_VOLUNTEER;
	  			int iVolUID = aIndivObj.getUserUID();
	  			aEmailObj.setEmailVolUID(iVolUID);
          }
          
     	    httpServletRequest.setAttribute("org", aOrgInfoObj);
       	    m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
          iRetCode = m_EmailBRLOObj.getEmailList(  aList, aEmailObj, iType ); 
 	        if(iRetCode!=0 && iRetCode!=-1){// doesn't seem to be a list for this user?? somehow this is broken
   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   	        }

          httpServletRequest.setAttribute( "emaillist", aList );
          iRetCode = m_IndActHelp.fillEmailDataForm(oFrm, aEmailObj);
          
          m_BaseHelp.loadOrgsOppsLists( httpServletRequest,  aIndivObj);
          
          return actionMapping.findForward( "volunteerreferrals" );
      }
      // end-of method showVolunteerReferrals()    


      /**
       * show showEmailAttachments summary page
       */
       public ActionForward showEmailAttachments(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	int iRetCode=0 ;
       	getPortalInfo( httpServletRequest, httpServletResponse);
       	
     	String aszPortal="", aszPortalNID="";
         if(httpServletRequest.getParameter("portal") != null ){
             if(httpServletRequest.getParameter("portal").length() > 0){
                 aszPortal = httpServletRequest.getParameter("portal");
             }
         }
         if(session.getAttribute(aszPortal+"_nid") != null ){
             if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
             	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
             }
         }
         if(aszPortal.length()>0){
         	if(aszPortalNID.length()==0){
         		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
     			httpServletRequest.setAttribute("redirectpage","noportalexists");
     			return actionMapping.findForward("mappingpage");
         	}
         }
         DynaActionForm oFrm = (DynaActionForm)actionForm;
         AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe,
         // so we'll just forward along to the login page
         boolean b_mobile=false;
         if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
          	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
          	httpServletRequest.getHeader("host").contains("m.chrisvol.org")	
          ){
              b_mobile=true;
          }
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	
           		return actionMapping.findForward( "showlogin" );
           	}else{
              	if(b_mobile==true){
                    	return actionMapping.findForward( "showlogin" );
                 	}else{
                 		return actionMapping.findForward( "loginstatus" );
                 	}
           	}
         }
         PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
        	 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		 return actionMapping.findForward( "showlogin" );
        	 }else{
        		 if(b_mobile==true){
                    	return actionMapping.findForward( "showlogin" );
        		 }else{
                 		return actionMapping.findForward( "loginstatus" );
        		 }
        	 }
         }
   		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  		     httpServletRequest.setAttribute("userprofile", aIndivObj);
   	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
   	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
            	return actionMapping.findForward("mappingpage");
  		 }
   		 if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
   			 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
   				 return actionMapping.findForward( "showlogin" );
   			 }else{
   				 if(b_mobile==true){
   					 return actionMapping.findForward( "showlogin" );
   				 }else{
                 	return actionMapping.findForward( "loginstatus" );
   				 }
   			 }
   		 }
   		 
   		 EmailInfoDTO aEmailObj = new EmailInfoDTO();
   		 allocatedEmailBRLO( httpServletRequest );
   		 String aszEmailID=m_BaseHelp.getFormData(oFrm,"emailid");
   		 
   		 if(aszEmailID==null || aszEmailID.length()<1){
   			 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		 }
   		 // might actually want to change and do test on organization, so that we can still show opportunities that may have been removed
   		 aEmailObj.setEmailId(aszEmailID);
   		 iRetCode = m_EmailBRLOObj.loadEmail( aEmailObj, 1 ); // load by email id rather than by org or volunteer
   		 
   		 if(iRetCode!=0 || aEmailObj.getEmailOppNID()<1){
   			 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		 }
   		 allocatedOrgBRLO( httpServletRequest );
   		 // confirm that the current user has contact access to     			aEmailObj.setEmailOppNID(iOppNID);
   		 int iOppNID = aEmailObj.getEmailOppNID();
   		 OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
   		 aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
   		 int iRetCode2 = 
   				 m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 0,"", 
   						 OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, 
   						 httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublisheed
   		 if(iRetCode2 != 0){
   			 // user down not have access rights
   			 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		 }
   		 httpServletRequest.setAttribute( "emailinfo", aEmailObj );
         iRetCode = m_IndActHelp.fillEmailDataForm(oFrm, aEmailObj);
           
         return actionMapping.findForward( "emailattachments" );
       }
       // end-of method showEmailAttachments()    




      /**
       * show loadApplications summary page
       */
       public ActionForward loadApplications(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	int iRetCode=0 ;
      	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
            }
        }
        if(session.getAttribute(aszPortal+"_nid") != null ){
            if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
            	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            }
        }
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			httpServletRequest.setAttribute("redirectpage","noportalexists");
    			return actionMapping.findForward("mappingpage");
        	}
        }
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
         // so we'll just forward along to the login page
         boolean b_mobile=false;
         String temp=httpServletRequest.getHeader("host");
         if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
         	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
         	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivol.org")	
         ){
             b_mobile=true;
         }
   		getLoggedInStatus(httpServletRequest, httpServletResponse);
   		if(aszLoggedInStatus.equals("showlogin")){
   	    	return actionMapping.findForward( "showlogin" );
   		}else if(aszLoggedInStatus.equals("processCookieLogin")){
   	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		}
     	if(null == aSessDat){
         	return actionMapping.findForward( "showlogin" );
     	}



           ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();

           allocatedEmailBRLO( httpServletRequest );
           ArrayList aList = new ArrayList();
           //iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aIndivObj.getUserUID() ); 
           int iType=0;
           String orgnid=null;
           String oppnid=null;
           try{
        	   orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
           }catch(Exception e){}
           try{
        	   oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
           }catch(Exception e){}
           
           int voluid=aCurrentUserObj.getUserUID();

           String aszSiteUseType=m_BaseHelp.getFormData(oFrm,"listtype");
  	        boolean bAdminRole=false;
    	        boolean bByContact = false;
    	        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
    	        if(aszReqType.contains("ByContact")){
    	       	 bByContact = true;
    	        }
  	        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
  	        int iIdNum = 0;
           if(aszSiteUseType.equalsIgnoreCase("orgmanagement")){
 	         	iType=ApplicationInfoDTO.LOADBY_ORG;
 	         	// check ownership/management rights on the given organization first; else return a dashboard page
 	         	if(orgnid==null || orgnid.length()<1){
 	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	         	}
 	   			int iOrgNID = Integer.parseInt(orgnid);
 	   			
 	   			iIdNum = iOrgNID;
 	   			
 	   			aApplicObj.setORGNID(iOrgNID);
 	   	         aOrgInfoObj.setORGNID( iOrgNID );
 	   	         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
 	   	         allocatedOrgBRLO( httpServletRequest );
 	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
 	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
 	   	         if(aCurrentUserObj.getNatlAssociationNID()>0){
 	              	       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
 	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
 	             }else{
 	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
 	              	     
 	   	         }
 	   	         if(iRetCode == -111){
 	   	          	if(bByContact == true){
 	                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
 	                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
 	           	}
 	   	          	else	   	         	if(bAdminRole==true){
 	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	   	 	          	return actionMapping.findForward( "noaccess" );
 	   	         	}else{
 	   	         		// org did not exist
 	   	             	return actionMapping.findForward( "orgnoresults" );
 	   	         	}
 	   	         }
 	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
 	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	   	        }
           }else if(aszSiteUseType.equalsIgnoreCase("oppmanagement")){
 	         	 iType=ApplicationInfoDTO.LOADBY_OPP;
 	         	 // check ownership/management rights on the given opportunity first; else return a dashboard page
 	         	 if(oppnid==null || oppnid.length()<1){
 	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	         	 }
 	    		int iOrgNID = Integer.parseInt(orgnid);
 	   			int iOppNID = Integer.parseInt(oppnid);
 	   			
 	   			iIdNum = iOppNID;
 	   			
 	   			aApplicObj.setOPPNID(iOppNID);
 	   	         
 	   			// might actually want to change and do test on organization, so that we can still show opportunities that may have been removed
 	   	         aOrgInfoObj.setORGNID( iOrgNID );
 	   	         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
 	   	         allocatedOrgBRLO( httpServletRequest );
 	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
 	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
 	   	         if(aCurrentUserObj.getNatlAssociationNID()>0){
 	              	       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
 	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
           	     }else{
 	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
 	   	         }
 	   	         if(iRetCode == -111){
 		   	        if(bByContact == true){
 		                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
 		                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
 		           	}else if(bAdminRole==true){
 	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	   	 	          	return actionMapping.findForward( "noaccess" );
 	   	         	}else{
 	   	         		// org did not exist
 	   	             	return actionMapping.findForward( "orgnoresults" );
 	   	         	}
 	   	         }
 	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
 	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	   	        }
           }else{
 	         	iType=EmailInfoDTO.LOADBY_VOLUNTEER;
 	  			int iVolUID = aCurrentUserObj.getUserUID();
 	   			
 	   			iIdNum = iVolUID;
 	   			
 	  			aApplicObj.setUID(iVolUID);
           }
           
      	    httpServletRequest.setAttribute("org", aOrgInfoObj);
        	    m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           iRetCode = m_EmailBRLOObj.getApplicationList(  aList, aApplicObj, iIdNum, iType ); 
  	        if(iRetCode!=0 && iRetCode!=-1){// doesn't seem to be a list for this user?? somehow this is broken
    	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	        }

           httpServletRequest.setAttribute( "appliclist", aList );
 //          iRetCode = m_IndActHelp.fillEmailDataForm(oFrm, aApplicObj);
           
           
           return actionMapping.findForward( "applications" );
//           return actionMapping.findForward( "volunteerreferrals" );
       }
       // end-of method loadApplications()    



       /**
        * show loadOneApplication summary page
        */
        public ActionForward loadOneApplication(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	int iRetCode=0 ;
       	getPortalInfo( httpServletRequest, httpServletResponse);
     	String aszPortal="", aszPortalNID="";
         if(httpServletRequest.getParameter("portal") != null ){
             if(httpServletRequest.getParameter("portal").length() > 0){
                 aszPortal = httpServletRequest.getParameter("portal");
             }
         }
         if(session.getAttribute(aszPortal+"_nid") != null ){
             if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
             	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
             }
         }
         if(aszPortal.length()>0){
         	if(aszPortalNID.length()==0){
         		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
     			httpServletRequest.setAttribute("redirectpage","noportalexists");
     			return actionMapping.findForward("mappingpage");
         	}
         }
         	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
          // so we'll just forward along to the login page
          boolean b_mobile=false;
          String temp=httpServletRequest.getHeader("host");
          if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
          	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
          	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
          	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
          	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
          	httpServletRequest.getHeader("host").contains("m.ivol.org")	
          ){
              b_mobile=true;
          }
    		getLoggedInStatus(httpServletRequest, httpServletResponse);
    		if(aszLoggedInStatus.equals("showlogin")){
    	    	return actionMapping.findForward( "showlogin" );
    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		}
      	if(null == aSessDat){
          	return actionMapping.findForward( "showlogin" );
      	}



            ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();

            allocatedEmailBRLO( httpServletRequest );
            int iType=0;
            String orgnid=null;
            String oppnid=null;
            try{
         	   orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
            }catch(Exception e){}
            try{
         	   oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
            }catch(Exception e){}
            
            iRetCode = m_IndActHelp.getApplicDataFromForm(oFrm, aApplicObj);
            int voluid=aCurrentUserObj.getUserUID();

            String aszSiteUseType=m_BaseHelp.getFormData(oFrm,"listtype");
   	        boolean bAdminRole=false;
     	        boolean bByContact = false;
     	        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
     	        if(aszReqType.contains("ByContact")){
     	       	 bByContact = true;
     	        }
   	        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
   	        int iIdNum = aApplicObj.getNID();
   	        int iKeyId = 0;
            if(aszSiteUseType.equalsIgnoreCase("orgmanagement")){
  	         	iType=ApplicationInfoDTO.LOADBY_ORG;
  	         	// check ownership/management rights on the given organization first; else return a dashboard page
  	         	if(orgnid==null || orgnid.length()<1){
  	         		System.out.println("triggered error on line 1614 of EmailActions");
  	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	         	}
  	   			int iOrgNID = Integer.parseInt(orgnid);
  	   			
  	   		iKeyId = iOrgNID;
  	   			
  	   			aApplicObj.setORGNID(iOrgNID);
  	   	         aOrgInfoObj.setORGNID( iOrgNID );
  	   	         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
  	   	         allocatedOrgBRLO( httpServletRequest );
  	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
  	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
  	   	         if(aCurrentUserObj.getNatlAssociationNID()>0){
  	              	       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
  	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
  	             }else{
  	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
  	              	     
  	   	         }
  	   	         if(iRetCode == -111){
  	   	          	if(bByContact == true){
  	                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
  	                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
  	           	}
  	   	          	else	   	         	if(bAdminRole==true){
  	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
  	   	 	          	return actionMapping.findForward( "noaccess" );
  	   	         	}else{
  	   	         		// org did not exist
  	   	             	return actionMapping.findForward( "orgnoresults" );
  	   	         	}
  	   	         }
  	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
  	         		System.out.println("triggered error on line 1648 of EmailActions");
  	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	   	        }
            }else if(aszSiteUseType.equalsIgnoreCase("oppmanagement")){
  	         	 iType=ApplicationInfoDTO.LOADBY_OPP;
  	         	 // check ownership/management rights on the given opportunity first; else return a dashboard page
  	         	 if(oppnid==null || oppnid.length()<1){
   	         		System.out.println("triggered error on line 1655 of EmailActions");
  	             	 return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	         	 }
  	    		int iOrgNID = Integer.parseInt(orgnid);
  	   			int iOppNID = Integer.parseInt(oppnid);
  	   			
  	   			iKeyId = iOppNID;
  	   			
  	   			aApplicObj.setOPPNID(iOppNID);
  	   	         
  	   			// might actually want to change and do test on organization, so that we can still show opportunities that may have been removed
  	   	         aOrgInfoObj.setORGNID( iOrgNID );
  	   	         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
  	   	         allocatedOrgBRLO( httpServletRequest );
  	   	         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
  	   	         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
  	   	         if(aCurrentUserObj.getNatlAssociationNID()>0){
  	              	       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
  	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion );
            	     }else{
  	              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
  	   	         }
  	   	         if(iRetCode == -111){
  		   	        if(bByContact == true){
  		                 	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
  		                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
  		           	}else if(bAdminRole==true){
  	   	 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
  	   	 	          	return actionMapping.findForward( "noaccess" );
  	   	         	}else{
  	   	         		// org did not exist
  	   	             	return actionMapping.findForward( "orgnoresults" );
  	   	         	}
  	   	         }
  	    	    if(iRetCode!=0 || aOrgInfoObj.getORGOrgName().length()<1){// current user does not appear to have administrative rights on this organization or org doesn't appear to exist
  	         		System.out.println("triggered error on line 1690 of EmailActions");
  	   	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	   	        }
            }else{
  	         	iType=ApplicationInfoDTO.LOADBY_APPLICANT_USER;
  	  			int iVolUID = aCurrentUserObj.getUserUID();
  	   			
  	  		iKeyId = iVolUID;
  	   			
  	  			aApplicObj.setUID(iVolUID);
            }
            
       	    httpServletRequest.setAttribute("org", aOrgInfoObj);
         	    m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
         	    
				boolean b_cvInternSiteApproved = false;
				// check to see if the current user has access rights on any cvintern_approved orgs
				ArrayList aCVCSitesList = new ArrayList( 2);
				allocatedApplicCodesBRLO( httpServletRequest );
		           allocatedOrgBRLO( httpServletRequest );
				m_AppCodesBRLOObj.getCVInternOrgSitesList(aCVCSitesList);
				// org_nids is a_cvinternOrgsList.
				for(int index=0; index < aCVCSitesList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVCSitesList.get(index);
					if(null == aAppCode) continue;
					int iOptRqCode = aAppCode.getAPCIdSubType();
					int iOrgNIDTmp = aAppCode.getAPCIdSubType();
					// try to load the iOrgNIDTmp by admin or contact and if it succeeds, then it can show the search results.
			        OrganizationInfoDTO aOrgInfoObjTmp = new OrganizationInfoDTO();
			        aOrgInfoObjTmp.setORGNID( iOrgNIDTmp );
		             aOrgInfoObjTmp.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObjTmp, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	                 if(iRetCode == -111){
	                	 // if it failed, try to load via contact
	                	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObjTmp, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	                 }
	                 if(iRetCode==0){
	                	 b_cvInternSiteApproved = true; 
	                	 break;
	                 }

				}
System.out.println(" line 1732 - b_cvInternSiteApproved is "+b_cvInternSiteApproved);
				//initially try to load as an org admin
				if(b_cvInternSiteApproved == true){
					aApplicObj.setUID(0);
					iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, iIdNum, iKeyId, ApplicationInfoDTO.LOADBY_APPROVED_SITE );
			        
			        if(aApplicObj.getResumeFilePath().length() < 1 && ! aApplicObj.getSource().equals("sf")){
			        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
			        }
				}else{
					iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, iIdNum, iKeyId, iType );
			        
			        if(aApplicObj.getResumeFilePath().length() < 1){
			        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
			        }
				}
   	        if(iRetCode!=0 && iRetCode!=-1){// doesn't seem to be a list for this user?? somehow this is broken
	         		System.out.println("triggered error on line 1741 of EmailActions");
   	        	
     	        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	        }
   	        if(! aApplicObj.getSource().equals("sf")){
	   	        PersonInfoDTO aIndivObj = new PersonInfoDTO();
	   	        aIndivObj.setUserUID(aApplicObj.getUID());
	            iRetCode = m_IndBRLOObj.loadUserByOption( aIndivObj ,aIndivObj.LOADBY_UID, aszSiteVersion);//re-load the user again to get an updated orgnid that does exist
		        if(aApplicObj.getResumeFilePath().length() < 1){
		   	        aApplicObj.setResumeFilePath(aIndivObj.getUSPResumeFilePath());
		        }
		        if(aApplicObj.getUsername().length() < 1){
		            aApplicObj.setUsername(aIndivObj.getUSPUsername());
		        }
   	        }
   	        
            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
  //          iRetCode = m_IndActHelp.fillEmailDataForm(oFrm, aApplicObj);
            
            return actionMapping.findForward( "application" );
//            return actionMapping.findForward( "volunteerreferrals" );
        }
        // end-of method loadOneApplication()    

    	
        
        /**
         * showCreateApplication page
         */
         public ActionForward showCreateApplication(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
        	 getPortalInfo( httpServletRequest, httpServletResponse);
        	String aszPortal="", aszPortalNID="";
            if(httpServletRequest.getParameter("portal") != null ){
                if(httpServletRequest.getParameter("portal").length() > 0){
                    aszPortal = httpServletRequest.getParameter("portal");
                }
            }
            if(session.getAttribute(aszPortal+"_nid") != null ){
                if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
                	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                }
            }
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
        			return actionMapping.findForward("404");
            	}
            }
            ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
            DynaActionForm oFrm = (DynaActionForm)actionForm;
	           String orgnid=null;
	           String oppnid=null;
	           try{
	        	   orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
	           }catch(Exception e){}
	           try{
	        	   oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
	           }catch(Exception e){}

        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    		getLoggedInStatus(httpServletRequest, httpServletResponse);
    		if(aszLoggedInStatus.equals("showlogin")){
    			
				aSessDat.setTokenKey(null);
				aSessDat.setOrgNID(orgnid);
				aSessDat.setOppNID(oppnid);
				aSessDat.setSubdomain(null);
				aSessDat.setSiteEmail(null);
				aSessDat.setTokenKey( AppSessionDTO.TOKEN_CVINTERNAPPLIC );
    			
    	    	return actionMapping.findForward( "showlogin" );
    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		}
	      	if(null == aSessDat){
	          	return actionMapping.findForward( "showlogin" );
	      	}

	           
	           int voluid=aCurrentUserObj.getUserUID();
	           

	           allocatedOrgBRLO( httpServletRequest );
	            allocatedEmailBRLO( httpServletRequest );
	            allocatedIndBRLO( httpServletRequest );
	           OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
	           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	           
	           aApplicObj.setORGNID(orgnid);
	           aOpportObj.setORGNID(orgnid);
	           aOrgInfoObj.setORGNID(orgnid);
	           
	           aApplicObj.setOPPNID(oppnid);
	           aOpportObj.setOPPNID(oppnid);

	           aApplicObj.setUID(voluid);
	           aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());

	           int iRetCode = 0;
	           if(aOrgInfoObj.getORGNID()>0){
	        	   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
	           }
	           if(aOpportObj.getOPPNID()>0){
	        	   iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
	           }
	           
	            iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, 0, voluid, ApplicationInfoDTO.LOADBY_APPLICANT_USER ); 
	            
	            if(aApplicObj.getResumeFilePath().length() < 1){
	            	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
	            }

		           if(aApplicObj.getNameFirst().isEmpty())	aApplicObj.setNameFirst(aCurrentUserObj.getUSPNameFirst());
		           if(aApplicObj.getNameLast().isEmpty())	aApplicObj.setNameLast(aCurrentUserObj.getUSPNameLast());
		           if(aApplicObj.getEmailAddr().isEmpty())	aApplicObj.setEmailAddr(aCurrentUserObj.getUSPEmail1Addr());
		           if(aApplicObj.getPhone().isEmpty())	aApplicObj.setPhone(aCurrentUserObj.getUSPPhone1());

		           
	            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
	            httpServletRequest.setAttribute( "org", aOrgInfoObj );
	            httpServletRequest.setAttribute( "opp", aOpportObj );
        	return actionMapping.findForward( "applicationform1" );

         }
         // end-of method showCreateApplication()

      /**
       * processCreateApplication1 page
       */
       public ActionForward processCreateApplication1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   getPortalInfo( httpServletRequest, httpServletResponse);
    	   String aszPortal="", aszPortalNID="";
    	   if(httpServletRequest.getParameter("portal") != null ){
              if(httpServletRequest.getParameter("portal").length() > 0){
                  aszPortal = httpServletRequest.getParameter("portal");
              }
          }
          if(session.getAttribute(aszPortal+"_nid") != null ){
              if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
              	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
              }
          }
          if(aszPortal.length()>0){
          	if(aszPortalNID.length()==0){
      			return actionMapping.findForward("404");
          	}
          }
          int iRetCode=0 ;
          ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
          DynaActionForm oFrm = (DynaActionForm)actionForm;

      	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
  		getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
    	if(null == aSessDat){
        	return actionMapping.findForward( "showlogin" );
    	}

        iRetCode = m_IndActHelp.getApplicDataFromForm(oFrm, aApplicObj);
        iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, 0, aCurrentUserObj.getUserUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER ); 
        
        int iApplicNID = 0, iApplicVID = 0;
        if(aApplicObj.getNID()>0){
        	iApplicNID = aApplicObj.getNID();
        }
        if(aApplicObj.getVID()>0){
        	iApplicVID = aApplicObj.getVID();
        }
        
        if(aApplicObj.getResumeFilePath().length() < 1){
        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
        }
System.out.println("EmailBRLO 1926 - resume filepath: "+aApplicObj.getResumeFilePath());		

        // need to load the form now.
        iRetCode = m_IndActHelp.getApplicDataFromForm(oFrm, aApplicObj);
        aApplicObj.setUID(aCurrentUserObj.getUserUID());

      	httpServletRequest.setAttribute("applicinfo", aApplicObj);

        aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
        aApplicObj.setUID(aCurrentUserObj.getUserUID());
        aApplicObj.setNID(iApplicNID);
        aApplicObj.setVID(iApplicVID);
        
        allocatedEmailBRLO( httpServletRequest );
//        iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, 0, aCurrentUserObj.getUserUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER ); 
        int iRetCode2 = 0;
        
        
        // if application nid=0, create a new one; if one already exists, update it
        if(iApplicNID>0){
        	iRetCode2 = m_EmailBRLOObj.updateApplication( aApplicObj, 1 );
        }else{
        	iRetCode2 = m_EmailBRLOObj.createApplication( aApplicObj );
        }
        if(iRetCode2==-5){
        	// the person is disqualified; we inserted them into our db, but they are not eligible candidates
        	httpServletRequest.setAttribute("redirectportal", aszPortal);
        	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERN_DQ);
        	return actionMapping.findForward( "mappingpage" );

        }
        
        iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, 0, aCurrentUserObj.getUserUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER ); 
        
        if(aApplicObj.getResumeFilePath().length() < 1){
        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
        }
System.out.println("EmailBRLO 1963 - resume filepath: "+aApplicObj.getResumeFilePath());		

        if(aApplicObj.getAddrLine1().isEmpty())	aApplicObj.setAddrLine1(aCurrentUserObj.getUSPAddrLine1());
        if(aApplicObj.getAddrCity().isEmpty())	aApplicObj.setAddrCity(aCurrentUserObj.getUSPAddrCity());
        if(aApplicObj.getAddrStateprov().isEmpty())	aApplicObj.setAddrStateprov(aCurrentUserObj.getUSPAddrStateprov());
        if(aApplicObj.getAddrPostalcode().isEmpty())	aApplicObj.setAddrPostalcode(aCurrentUserObj.getUSPAddrPostalcode());
        if(aApplicObj.getAddrCountryName().isEmpty())	aApplicObj.setAddrCountryName(aCurrentUserObj.getUSPAddrCountryName());

        
        allocatedIndBRLO( httpServletRequest );
       if(aCurrentUserObj.getUSPNameFirst().isEmpty())	aCurrentUserObj.setUSPNameFirst(aApplicObj.getNameFirst());
       if(aCurrentUserObj.getUSPNameLast().isEmpty())	aCurrentUserObj.setUSPNameLast(aApplicObj.getNameLast());
       if(aCurrentUserObj.getUSPEmail1Addr().isEmpty())	aCurrentUserObj.setUSPEmail1Addr(aApplicObj.getEmailAddr());
       if(aCurrentUserObj.getUSPPhone1().isEmpty())	aCurrentUserObj.setUSPPhone1(aApplicObj.getPhone());
       if(aCurrentUserObj.getUSPAddrLine1().isEmpty())	aCurrentUserObj.setUSPAddrLine1(aApplicObj.getAddrLine1());
       if(aCurrentUserObj.getUSPAddrCity().isEmpty())	aCurrentUserObj.setUSPAddrCity(aApplicObj.getAddrCity());
       if(aCurrentUserObj.getUSPAddrStateprov().isEmpty())	aCurrentUserObj.setUSPAddrStateprov(aApplicObj.getAddrStateprov());
       if(aCurrentUserObj.getUSPAddrCountryName().isEmpty())	aCurrentUserObj.setUSPAddrCountryName(aApplicObj.getAddrCountryName());
       if(aCurrentUserObj.getUSPAddrPostalcode().isEmpty())	aCurrentUserObj.setUSPAddrPostalcode(aApplicObj.getAddrPostalcode());
       if(aCurrentUserObj.getUSPChristianP().isEmpty())		aCurrentUserObj.setUSPChristianP(aApplicObj.getChristian());
       
       aCurrentUserObj.setUSPInternalUserTypeComment(aszCVIntern);
       m_IndBRLOObj.updateCVInternApplicant( aCurrentUserObj , aszSiteVersion);
       

       allocatedOrgBRLO( httpServletRequest );
       OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
       OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
       
       aOpportObj.setORGNID(aApplicObj.getORGNID());
       aOrgInfoObj.setORGNID(aApplicObj.getORGNID());
       aOpportObj.setOPPNID(aApplicObj.getOPPNID());


        iRetCode = 0;
       if(aOrgInfoObj.getORGNID()>0){
    	   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
       }
       if(aOpportObj.getOPPNID()>0){
    	   iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
       }
 
        httpServletRequest.setAttribute( "applicinfo", aApplicObj );
        httpServletRequest.setAttribute( "org", aOrgInfoObj );
        httpServletRequest.setAttribute( "opp", aOpportObj );        
        if(iRetCode2 != 0){
        	m_BaseHelp.setFormData(oFrm,"errormsg", aApplicObj.getErrorMsg() );
        	return actionMapping.findForward( "applicationform1" );
        }


        //aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	return actionMapping.findForward( "applicationform2" );
      	
       }
       // end-of method processCreateApplication1()
      
   	
       
       /**
        * showCreateApplication2 page
        */
        public ActionForward showCreateApplication2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
       	 getPortalInfo( httpServletRequest, httpServletResponse);
       	String aszPortal="", aszPortalNID="";
           if(httpServletRequest.getParameter("portal") != null ){
               if(httpServletRequest.getParameter("portal").length() > 0){
                   aszPortal = httpServletRequest.getParameter("portal");
               }
           }
           if(session.getAttribute(aszPortal+"_nid") != null ){
               if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
               	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
               }
           }
           if(aszPortal.length()>0){
           	if(aszPortalNID.length()==0){
       			return actionMapping.findForward("404");
           	}
           }
           ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
           DynaActionForm oFrm = (DynaActionForm)actionForm;

       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   		getLoggedInStatus(httpServletRequest, httpServletResponse);
   		if(aszLoggedInStatus.equals("showlogin")){
			
			aSessDat.setTokenKey( AppSessionDTO.TOKEN_CVINTERNAPPLIC2 );
			
   	    	return actionMapping.findForward( "showlogin" );
   		}else if(aszLoggedInStatus.equals("processCookieLogin")){
   	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		}
	      	if(null == aSessDat){
	          	return actionMapping.findForward( "showlogin" );
	      	}

	           String orgnid=null;
	           String oppnid=null;
	           String nid=null;
	           int iIdNum=0;
	           try{
	        	   orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
	           }catch(Exception e){}
	           try{
	        	   oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
	           }catch(Exception e){}
	           try{
	        	   nid=m_BaseHelp.getFormData(oFrm,"nid");
	        	   iIdNum = m_BaseHelp.convertToInt( nid );
	           }catch(Exception e){}
	           
	           int voluid=aCurrentUserObj.getUserUID();
	           

	           allocatedOrgBRLO( httpServletRequest );
	            allocatedEmailBRLO( httpServletRequest );
	           OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
	           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	           
	           aApplicObj.setORGNID(orgnid);
	           aOpportObj.setORGNID(orgnid);
	           aOrgInfoObj.setORGNID(orgnid);
	           
	           aApplicObj.setOPPNID(oppnid);
	           aOpportObj.setOPPNID(oppnid);
	           
	           aApplicObj.setUID(voluid);

	           aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
	           

	           int iRetCode = 0;
	           iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, iIdNum, voluid, ApplicationInfoDTO.LOADBY_APPLICANT_USER);
	           
	           if(aApplicObj.getResumeFilePath().length() < 1){
	           	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
	           }
System.out.println("EmailBRLO 2102 - resume filepath: "+aApplicObj.getResumeFilePath());		

	           if(aApplicObj.getAddrLine1().isEmpty())	aApplicObj.setAddrLine1(aCurrentUserObj.getUSPAddrLine1());
	           if(aApplicObj.getAddrCity().isEmpty())	aApplicObj.setAddrCity(aCurrentUserObj.getUSPAddrCity());
	           if(aApplicObj.getAddrStateprov().isEmpty())	aApplicObj.setAddrStateprov(aCurrentUserObj.getUSPAddrStateprov());
	           if(aApplicObj.getAddrPostalcode().isEmpty())	aApplicObj.setAddrPostalcode(aCurrentUserObj.getUSPAddrPostalcode());
	           if(aApplicObj.getAddrCountryName().isEmpty())	aApplicObj.setAddrCountryName(aCurrentUserObj.getUSPAddrCountryName());

	           
	           if(iRetCode != 0){
	        	   // user doesn't have an application started; load the first page of application process instead
	        	   
	           }

	            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
	            if(aOrgInfoObj.getORGNID()<=0){
	            	aOrgInfoObj.setORGNID(aApplicObj.getORGNID());
	            }
	           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
	            httpServletRequest.setAttribute( "org", aOrgInfoObj );
	            
	            if(aOpportObj.getOPPNID()<=0){
	            	aOpportObj.setOPPNID(aApplicObj.getOPPNID());
	            }
		           iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
	            httpServletRequest.setAttribute( "opp", aOpportObj );
	            
       	return actionMapping.findForward( "applicationform2" );

        }
        // end-of method showCreateApplication2()
  

        /**
         * processCreateApplication2 page
         */
         public ActionForward processCreateApplication2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
      	   getPortalInfo( httpServletRequest, httpServletResponse);
      	   String aszPortal="", aszPortalNID="";
      	   if(httpServletRequest.getParameter("portal") != null ){
                if(httpServletRequest.getParameter("portal").length() > 0){
                    aszPortal = httpServletRequest.getParameter("portal");
                }
            }
            if(session.getAttribute(aszPortal+"_nid") != null ){
                if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
                	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                }
            }
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
        			return actionMapping.findForward("404");
            	}
            }
            int iRetCode=0 ;
            ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
            DynaActionForm oFrm = (DynaActionForm)actionForm;

        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    		getLoggedInStatus(httpServletRequest, httpServletResponse);
    		if(aszLoggedInStatus.equals("showlogin")){
    	    	return actionMapping.findForward( "showlogin" );
    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		}
      	if(null == aSessDat){
          	return actionMapping.findForward( "showlogin" );
      	}
        allocatedOrgBRLO( httpServletRequest );
        allocatedEmailBRLO( httpServletRequest );
       OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
       OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();

      	
        iRetCode = m_IndActHelp.getApplicDataFromForm2(oFrm, aApplicObj);
        iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, aApplicObj.getNID(), aApplicObj.getUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER);
        
        if(aApplicObj.getResumeFilePath().length() < 1){
        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
        }
System.out.println("EmailBRLO 2183 - resume filepath: "+aApplicObj.getResumeFilePath());		

        // need to load the new data now.
        iRetCode = m_IndActHelp.getApplicDataFromForm2(oFrm, aApplicObj);

        if(iRetCode != 0){
        	// this application does not belong to the current user
        	return  actionMapping.findForward( "showlogin" );
        }

          aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
          aApplicObj.setUID(aCurrentUserObj.getUserUID());
          


    		int iRetCode2 = m_EmailBRLOObj.updateApplication( aApplicObj, 2 );
            if(iRetCode2==-5){
            	// the person is disqualified; we inserted them into our db, but they are not eligible candidates
            	httpServletRequest.setAttribute("redirectportal", aszPortal);
            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERN_DQ);
            	return actionMapping.findForward( "mappingpage" );

            }
            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
            if(aOrgInfoObj.getORGNID()<=0){
            	aOrgInfoObj.setORGNID(aApplicObj.getORGNID());
            }
           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
            httpServletRequest.setAttribute( "org", aOrgInfoObj );
            
            if(aOpportObj.getOPPNID()<=0){
            	aOpportObj.setOPPNID(aApplicObj.getOPPNID());
            }
	           iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
            httpServletRequest.setAttribute( "opp", aOpportObj );

            if(iRetCode2 != 0){
            	m_BaseHelp.setFormData(oFrm,"errormsg", aApplicObj.getErrorMsg() );
            	return actionMapping.findForward( "applicationform2" );
            }
            
            allocatedIndBRLO( httpServletRequest );
           if(aCurrentUserObj.getUSPNameFirst().isEmpty())	aCurrentUserObj.setUSPNameFirst(aApplicObj.getNameFirst());
           if(aCurrentUserObj.getUSPNameLast().isEmpty())	aCurrentUserObj.setUSPNameLast(aApplicObj.getNameLast());
           if(aCurrentUserObj.getUSPEmail1Addr().isEmpty())	aCurrentUserObj.setUSPEmail1Addr(aApplicObj.getEmailAddr());
           if(aCurrentUserObj.getUSPPhone1().isEmpty())	aCurrentUserObj.setUSPPhone1(aApplicObj.getPhone());
           if(aCurrentUserObj.getUSPAddrLine1().isEmpty())	aCurrentUserObj.setUSPAddrLine1(aApplicObj.getAddrLine1());
           if(aCurrentUserObj.getUSPAddrCity().isEmpty())	aCurrentUserObj.setUSPAddrCity(aApplicObj.getAddrCity());
           if(aCurrentUserObj.getUSPAddrStateprov().isEmpty())	aCurrentUserObj.setUSPAddrStateprov(aApplicObj.getAddrStateprov());
           if(aCurrentUserObj.getUSPAddrCountryName().isEmpty())	aCurrentUserObj.setUSPAddrCountryName(aApplicObj.getAddrCountryName());
           if(aCurrentUserObj.getUSPAddrPostalcode().isEmpty())	aCurrentUserObj.setUSPAddrPostalcode(aApplicObj.getAddrPostalcode());
           if(aCurrentUserObj.getUSPChristianP().isEmpty())		aCurrentUserObj.setUSPChristianP(aApplicObj.getChristian());
           
           aCurrentUserObj.setUSPInternalUserTypeComment(aszCVIntern);
           m_IndBRLOObj.updateCVInternApplicant( aCurrentUserObj , aszSiteVersion);
           
       	
        	//aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	return actionMapping.findForward( "applicationform3" );
        	
         }
         // end-of method processCreateApplication2()
        
         
         /**
          * showCreateApplication3 page
          */
          public ActionForward showCreateApplication3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
         	 getPortalInfo( httpServletRequest, httpServletResponse);
         	String aszPortal="", aszPortalNID="";
             if(httpServletRequest.getParameter("portal") != null ){
                 if(httpServletRequest.getParameter("portal").length() > 0){
                     aszPortal = httpServletRequest.getParameter("portal");
                 }
             }
             if(session.getAttribute(aszPortal+"_nid") != null ){
                 if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
                 	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                 }
             }
             if(aszPortal.length()>0){
             	if(aszPortalNID.length()==0){
         			return actionMapping.findForward("404");
             	}
             }
             ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
             DynaActionForm oFrm = (DynaActionForm)actionForm;

         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     		getLoggedInStatus(httpServletRequest, httpServletResponse);
     		if(aszLoggedInStatus.equals("showlogin")){
    			
    			aSessDat.setTokenKey( AppSessionDTO.TOKEN_CVINTERNAPPLIC3 );
    			
     	    	return actionMapping.findForward( "showlogin" );
     		}else if(aszLoggedInStatus.equals("processCookieLogin")){
     	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     		}
  	      	if(null == aSessDat){
  	          	return actionMapping.findForward( "showlogin" );
  	      	}

  	           String orgnid=null;
  	           String oppnid=null;
  	           String nid=null;
  	           int iIdNum=0;
  	           try{
  	        	   orgnid=m_BaseHelp.getFormData(oFrm,"orgnid");
  	           }catch(Exception e){}
  	           try{
  	        	   oppnid=m_BaseHelp.getFormData(oFrm,"oppnid");
  	           }catch(Exception e){}
  	           try{
  	        	   nid=m_BaseHelp.getFormData(oFrm,"nid");
  	        	   iIdNum = m_BaseHelp.convertToInt( nid );
  	           }catch(Exception e){}
  	           
  	           int voluid=aCurrentUserObj.getUserUID();
  	           

  	           allocatedOrgBRLO( httpServletRequest );
  	           OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
  	           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
  	           
  	           aApplicObj.setORGNID(orgnid);
  	           aOpportObj.setORGNID(orgnid);
  	           aOrgInfoObj.setORGNID(orgnid);
  	           
  	           aApplicObj.setOPPNID(oppnid);
  	           aOpportObj.setOPPNID(oppnid);
  	           
  	           aApplicObj.setUID(voluid);

  	         aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
  	         
  	            allocatedEmailBRLO( httpServletRequest );

  	           int iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
  	           iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
  	           iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, iIdNum, voluid, ApplicationInfoDTO.LOADBY_APPLICANT_USER);
  	         
  	         if(aApplicObj.getResumeFilePath().length() < 1){
  	        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
  	        }
System.out.println("EmailBRLO 2328 - resume filepath: "+aApplicObj.getResumeFilePath());		

  	            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
  	            httpServletRequest.setAttribute( "org", aOrgInfoObj );
  	            httpServletRequest.setAttribute( "opp", aOpportObj );
         	return actionMapping.findForward( "applicationform3" );

          }
          // end-of method showCreateApplication3()
    

          /**
           * processCreateApplication3 page
           */
           public ActionForward processCreateApplication3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
        	   getPortalInfo( httpServletRequest, httpServletResponse);
        	   String aszPortal="", aszPortalNID="";
        	   if(httpServletRequest.getParameter("portal") != null ){
                  if(httpServletRequest.getParameter("portal").length() > 0){
                      aszPortal = httpServletRequest.getParameter("portal");
                  }
              }
              if(session.getAttribute(aszPortal+"_nid") != null ){
                  if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
                  	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                  }
              }
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
          			return actionMapping.findForward("404");
              	}
              }
              int iRetCode=0 ;
              ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
              DynaActionForm oFrm = (DynaActionForm)actionForm;

          	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      		getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
        	if(null == aSessDat){
            	return actionMapping.findForward( "showlogin" );
        	}

            allocatedEmailBRLO( httpServletRequest );

            aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
            aApplicObj.setUID(aCurrentUserObj.getUserUID());
            
     	
          iRetCode = m_IndActHelp.getApplicDataFromForm3(oFrm, aApplicObj);
          iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, aApplicObj.getNID(), aApplicObj.getUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER);
          
          if(aApplicObj.getResumeFilePath().length() < 1){
          	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
          }
System.out.println("EmailBRLO 2388 - resume filepath: "+aApplicObj.getResumeFilePath());		

          if(iRetCode != 0){
          	// this application does not belong to the current user
          	return  actionMapping.findForward( "showlogin" );
          }
          // need to load the new data now.
            iRetCode = m_IndActHelp.getApplicDataFromForm3(oFrm, aApplicObj);

            aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
            aApplicObj.setUID(aCurrentUserObj.getUserUID());
            
            

          	httpServletRequest.setAttribute("applicinfo", aApplicObj);

      		iRetCode = m_EmailBRLOObj.updateApplication( aApplicObj, 3 );
            if(iRetCode==-5){
            	// the person is disqualified; we inserted them into our db, but they are not eligible candidates
            	httpServletRequest.setAttribute("redirectportal", aszPortal);
            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERN_DQ);
            	return actionMapping.findForward( "mappingpage" );

            }
          	
            if(iRetCode != 0){
            	m_BaseHelp.setFormData(oFrm,"errormsg", aApplicObj.getErrorMsg() );
      	        aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());
      	        iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, 0, aCurrentUserObj.getUserProfileNID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER);
      	        
      	        if(aApplicObj.getResumeFilePath().length() < 1){
      	        	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
      	        }
System.out.println("EmailBRLO 2421 - resume filepath: "+aApplicObj.getResumeFilePath());		
            	httpServletRequest.setAttribute("applicinfo", aApplicObj);
            	
            	allocatedOrgBRLO( httpServletRequest );
            	OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
            	OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            	
            	int orgnid = aApplicObj.getORGNID();
            	aOpportObj.setORGNID(orgnid);
            	aOrgInfoObj.setORGNID(orgnid);
            	
            	int oppnid = aApplicObj.getOPPNID();
            	aOpportObj.setOPPNID(oppnid);
            	
            	iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
            	iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, aApplicObj.getOPPNID(), 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published

 	            httpServletRequest.setAttribute( "org", aOrgInfoObj );
  	            httpServletRequest.setAttribute( "opp", aOpportObj );
            	return actionMapping.findForward( "applicationform3" );
            }
            
            allocatedIndBRLO( httpServletRequest );
           if(aCurrentUserObj.getUSPNameFirst().isEmpty())	aCurrentUserObj.setUSPNameFirst(aApplicObj.getNameFirst());
           if(aCurrentUserObj.getUSPNameLast().isEmpty())	aCurrentUserObj.setUSPNameLast(aApplicObj.getNameLast());
           if(aCurrentUserObj.getUSPEmail1Addr().isEmpty())	aCurrentUserObj.setUSPEmail1Addr(aApplicObj.getEmailAddr());
           if(aCurrentUserObj.getUSPPhone1().isEmpty())	aCurrentUserObj.setUSPPhone1(aApplicObj.getPhone());
           if(aCurrentUserObj.getUSPAddrLine1().isEmpty())	aCurrentUserObj.setUSPAddrLine1(aApplicObj.getAddrLine1());
           if(aCurrentUserObj.getUSPAddrCity().isEmpty())	aCurrentUserObj.setUSPAddrCity(aApplicObj.getAddrCity());
           if(aCurrentUserObj.getUSPAddrStateprov().isEmpty())	aCurrentUserObj.setUSPAddrStateprov(aApplicObj.getAddrStateprov());
           if(aCurrentUserObj.getUSPAddrCountryName().isEmpty())	aCurrentUserObj.setUSPAddrCountryName(aApplicObj.getAddrCountryName());
           if(aCurrentUserObj.getUSPAddrPostalcode().isEmpty())	aCurrentUserObj.setUSPAddrPostalcode(aApplicObj.getAddrPostalcode());
           if(aCurrentUserObj.getUSPChristianP().isEmpty())		aCurrentUserObj.setUSPChristianP(aApplicObj.getChristian());
           
           if(aCurrentUserObj.getUSPBirthDt()==null)		aCurrentUserObj.setUSPBirthDt(aApplicObj.getDOBDt());
           if(aApplicObj.getDOBDt()!=null){
	           int iDOBYr=0;
	           Calendar cal = Calendar.getInstance();
	           try{
		           cal.setTime(aApplicObj.getDOBDt());
		           iDOBYr = cal.get(Calendar.YEAR);
	           if(aCurrentUserObj.getBirthYear()<1)	aCurrentUserObj.setBirthYear(iDOBYr);
	           }catch(Exception ex){
	        	   
	           }
	       }
           
           aCurrentUserObj.setUSPInternalUserTypeComment(aszCVIntern);
           m_IndBRLOObj.updateCVInternApplicant( aCurrentUserObj, aszSiteVersion );
           
            
            // if user has not yet submitted a resume, first redirect to the resume posting page; otherwise, go ahead and send to applicationsent
            PersonInfoDTO aIndivObj = new PersonInfoDTO();
            aIndivObj.setUserUID(aApplicObj.getUID());
            allocatedIndBRLO( httpServletRequest );
            iRetCode = m_IndBRLOObj.loadUserByOption(aIndivObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion);
//            if(aIndivObj.getUSPResumeFilePath().length()<5){
            	return showResumePost( actionMapping, actionForm, httpServletRequest, httpServletResponse);
//            }
            
            //aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
 //       	return actionMapping.findForward( "applicationresumeform" );
//         	return actionMapping.findForward( "applicsent" );
          	
           }
           // end-of method processCreateApplication3()
          
        	
        	/*
        	* show showResumePost page
        	*/
        	public ActionForward showResumePost(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        	{
            	getPortalInfo( httpServletRequest, httpServletResponse);
            	String aszPortal="", aszPortalNID="";
                if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
                if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        		if(aszPortal.length()>0){
                	if(aszPortalNID.length()==0){
                		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            			//httpServletRequest.setAttribute("redirectpage","noportalexists");
            			//return actionMapping.findForward("mappingpage");
            			return actionMapping.findForward("404");
                	}
                }
            	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    			aSessDat.setTokenKey( AppSessionDTO.TOKEN_RESUME_POST );
                if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
                    getLoggedInStatus(httpServletRequest, httpServletResponse);
              		if(aszLoggedInStatus.equals("showlogin")){
            			
            			
              	    	return actionMapping.findForward( "showlogin" );
              		}else if(aszLoggedInStatus.equals("processCookieLogin")){
              	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              		}
                }
        	   PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        	   if(null == aIndivObj) {
        		   //aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITINDIV );
        	          getLoggedInStatus(httpServletRequest, httpServletResponse);
        	    		if(aszLoggedInStatus.equals("showlogin")){
        	    	    	return actionMapping.findForward( "showlogin" );
        	    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
        	    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	    		}
        	   }
        	   httpServletRequest.setAttribute("userprofile", aIndivObj);
       	    	return actionMapping.findForward( "fileuploadpage" );
        	}
        	// end-of method showResumePost()
      	
     	
          
         	
        	/*
         	* show showApplicSent page
         	*/
         	public ActionForward showApplicSent(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         	{
         		getPortalInfo( httpServletRequest, httpServletResponse);
         		String aszPortal="", aszPortalNID="";
             if(httpServletRequest.getParameter("portal") != null ){
                 if(httpServletRequest.getParameter("portal").length() > 0){
                     aszPortal = httpServletRequest.getParameter("portal");
                 }
             }
             if(session.getAttribute(aszPortal+"_nid") != null ){
                 if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
                 	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
                 }
             }
             if(aszPortal.length()>0){
             	if(aszPortalNID.length()==0){
         			return actionMapping.findForward("404");
             	}
             }
             ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
             DynaActionForm oFrm = (DynaActionForm)actionForm;

         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     		getLoggedInStatus(httpServletRequest, httpServletResponse);
     		if(aszLoggedInStatus.equals("showlogin")){
     	    	return actionMapping.findForward( "showlogin" );
     		}else if(aszLoggedInStatus.equals("processCookieLogin")){
     	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     		}
 	      	if(null == aSessDat){
 	          	return actionMapping.findForward( "showlogin" );
 	      	}

 	           
 	           int voluid=aCurrentUserObj.getUserUID();
 	           

 	           allocatedOrgBRLO( httpServletRequest );
 	            allocatedEmailBRLO( httpServletRequest );

 	           aApplicObj.setUID(voluid);
 	           aApplicObj.setVolNID(aCurrentUserObj.getUserProfileNID());

	 	           int[] orgnids=null;
	 	           try{
	 	        	   orgnids=m_BaseHelp.getFormDataIntArray(oFrm,"orgnids");
	 	           }catch(Exception e){}
	 	           aApplicObj.setORGNIDsArray(orgnids);
 	           int iRetCode = 0;
 	           
 	            iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, 0, voluid, ApplicationInfoDTO.LOADBY_APPLICANT_USER ); 
 	           
 	           if(aApplicObj.getResumeFilePath().length() < 1){
 	          	aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
 	          }
System.out.println("EmailBRLO 2595 - resume filepath: "+aApplicObj.getResumeFilePath());		

 		           if(aApplicObj.getNameFirst().isEmpty())	aApplicObj.setNameFirst(aCurrentUserObj.getUSPNameFirst());
 		           if(aApplicObj.getNameLast().isEmpty())	aApplicObj.setNameLast(aCurrentUserObj.getUSPNameLast());
 		           if(aApplicObj.getEmailAddr().isEmpty())	aApplicObj.setEmailAddr(aCurrentUserObj.getUSPEmail1Addr());
 		           if(aApplicObj.getPhone().isEmpty())	aApplicObj.setPhone(aCurrentUserObj.getUSPPhone1());

 		           
 	 	           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
 	 	           OrgOpportunityInfoDTO aOppInfoObj = new OrgOpportunityInfoDTO();
 	 	           EmailInfoDTO aEmailObj = new EmailInfoDTO();
 		           // send the email if an org (possibly just a new org??) was selected
 		           for(int i=0; i<orgnids.length; i++){
 		        	   
	 	 	           aOrgInfoObj = new OrganizationInfoDTO();
	 	 	           aOrgInfoObj.setORGNID(orgnids[i]);
	 	 	           if(aOrgInfoObj.getORGNID()>0){
	 	 	        	   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
	 	 	           }
	 	 	           aCurrentUserObj.setUSPEmail1Addr(aApplicObj.getEmailAddr());
	 	 	           
	 		           if(iRetCode==0){// loading the org was successful; proceed with the email
	 		        	   // make sure all the EMAILs are collected for Volunteer Contacts on an opportunity
	 		        	   ArrayList<PersonInfoDTO> aList = new ArrayList<PersonInfoDTO>();
	 		        	   iRetCode = m_OrgBRLOObj.getOrgContactList( aList, orgnids[i] ); // should make new 2011-03-02 *************************************************************************
	 		        	   
	 		        	   String aszEmail=null;
	 		        	   StringBuilder sbOrgEmailList = new StringBuilder();
	 		        	   StringBuilder sbOrgContactNamesList = new StringBuilder();
	 		        	   Iterator<PersonInfoDTO> itr = aList.iterator();
	 		        	   String aszName = "";
	 		        	   while (itr.hasNext()) {
	 		        		   PersonInfoDTO tmpIndivObj = itr.next();
	 		        		   aszEmail = tmpIndivObj.getUSPEmail1Addr();
	 		        		   sbOrgEmailList.append(aszEmail);
	 		        		   sbOrgEmailList.append(", ");
	 		        		   aszName = tmpIndivObj.getUSPNameFirst() + " " + tmpIndivObj.getUSPNameLast();
	 		        		  sbOrgContactNamesList.append(aszName);
	 		        		 sbOrgContactNamesList.append(", ");
	 		        	   }
	 		        	   String aszOrgEmailList = sbOrgEmailList.toString();
	 		        	   if(iRetCode==0 && aszOrgEmailList.length()>0){
	 		        		   aEmailObj.setEmailOrg( aszOrgEmailList );
	 		        		   aEmailObj.setEmailToUsers( aszOrgEmailList );
	 		        		   aEmailObj.setEmailToUser( aszOrgEmailList );
	 		        		   aEmailObj.setEmailOrg(aszOrgEmailList);
	 		        	   }
	 		        	   String aszOrgContactNamesList = sbOrgContactNamesList.toString();
	 		        	   aEmailObj.setEmailBodyTextIntro( aszOrgContactNamesList );
	 		        	   
	 		        	   iRetCode = m_EmailBRLOObj.setEmailText(  aCurrentUserObj, aOrgInfoObj, aOppInfoObj, aEmailObj );
	 		           }
 		           }
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 	            httpServletRequest.setAttribute( "applicinfo", aApplicObj );
 	            httpServletRequest.setAttribute( "org", aOrgInfoObj );
            	return actionMapping.findForward( "applicsent" );

         	}
         	// end-of method showApplicSent()
       	
      	
           

     /**
      * show Volunteer Dashboard account summary page one
      */
      public ActionForward showVolunteerDashboard(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	int iRetCode=0 ;
      	getPortalInfo( httpServletRequest, httpServletResponse);
     	
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
       		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
   			httpServletRequest.setAttribute("redirectpage","noportalexists");
   			return actionMapping.findForward("mappingpage");
       	}
       }
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
        // so we'll just forward along to the login page
        boolean b_mobile=false;
        String temp=httpServletRequest.getHeader("host");
        if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
        	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivol.org")	
        ){
            b_mobile=true;
        }
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	
          		return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }
       	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aIndivObj) {
          	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }
          if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
            	if(b_mobile==true){
                  	return actionMapping.findForward( "showlogin" );
               	}else{
               		return actionMapping.findForward( "loginstatus" );
               	}
          	}
          }
          allocatedOrgBRLO( httpServletRequest );
          ArrayList aList = new ArrayList();
          //iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aIndivObj.getUSPPersonNumber() ); // 2009-07-20 - will eventually be uid-ized
          iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aIndivObj.getUserUID()); 
          httpServletRequest.setAttribute( "orglist", aList );
          iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
      	if(null != aSessDat){
          	aSessDat.setTokenKey(null);
          	aSessDat.setOrgNID(null);
          	aSessDat.setOppNID(null);
          	aSessDat.setSubdomain(null);
          	aSessDat.setSiteEmail(null);
      	}
          
          return actionMapping.findForward( "volunteerdashboard" );
      }
      // end-of method showVolunteerDashboard()    

	
    
    /**
     * showSendFeedback page
     */
     public ActionForward showSendFeedback(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	 getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
            }
        }
        if(session.getAttribute(aszPortal+"_nid") != null ){
            if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
            	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            }
        }
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	//2006-10-06
    	return actionMapping.findForward( "sendfeedback" );

    	//return actionMapping.findForward( "myacctsumm1" );
     }
     // end-of method showSendFeedback()
    
     
    /**
     * processSendFeedback page
     */
     public ActionForward processSendFeedback(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	 getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
            }
        }
        if(session.getAttribute(aszPortal+"_nid") != null ){
            if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
            	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            }
        }
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
     	int iRetCode=0 ;
         EmailInfoDTO aEmailObj = new EmailInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);//"www.christianvolunteering.org";
    	String aszDomMainShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
    	String aszSiteOrgName = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
     	
      	
    	// need to load the form now.
        iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aEmailObj, false);

    	String aszIndEmail =  m_BaseHelp.getFormData( oFrm, "fromuser" ); 
    	// need to load the form now.
        String aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
    	String aszSmtpUserName = m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
    	String aszFromPassword = m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
    	String aszTo = "feedback@christianvolunteering.org" ; 
    	String aszSubject = "Feedback for ChristianVolunteering.org" ; 
    	String aszSubdom = m_BaseHelp.getFormData( oFrm, "subdom" ); 
    	

    	String aszFeedbackMess =  m_BaseHelp.getFormData( oFrm, "emailbodytxt" ); 

        String msgText =  aszFeedbackMess +
        	
        	"\n\n----------------------------------------\n\n" + 
        	"\nThis email has been sent to you through " + aszDomMain +  
        	"\nIf you have any questions, please email us at feedback@" + aszDomMainShort + 
        	"\nThank you for using " + aszDomMainShort + ", powered by " + aszSiteOrgName + ".\n" + 
        	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";

    	httpServletRequest.setAttribute("emailinfo", aEmailObj);

    	String aszSMTPAuthenticate = "Y";

    	aEmailObj.setEmailSMTPServerName( aszSMTPServer );
    	aEmailObj.setEmailFromUserPassword( aszFromPassword );
    	aEmailObj.setSMTPUserName( aszSmtpUserName );
    	aEmailObj.setEmailSubjectText( aszSubject );
    	aEmailObj.setEmailFromUser( aszIndEmail );
    	aEmailObj.setEmailToUser( aszTo );
    	aEmailObj.setEmailSubdom( aszSubdom );
    	aEmailObj.setEmailBodyText( msgText );
    	m_BaseHelp.setFormData( oFrm, "touser" , aEmailObj.getEmailToUser() ); 

    	httpServletRequest.setAttribute("emailinfo", aEmailObj);

        aEmailObj.setSMTPAuthRequired( true );

        	
        if(aszSMTPAuthenticate.equalsIgnoreCase("Y")){
        	aEmailObj.setSMTPAuthRequired( true );
    	} else {
        	aEmailObj.setSMTPAuthRequired( false );
    	}

    	String aszSendEmailOK = "Y";//m_BaseHelp.getFormData( oFrm, "employment" );
    	if(aszSendEmailOK.equalsIgnoreCase("Y")){
    		aszSendEmailOK="N";
    		//iRetCode = m_IndBRLOObj.createEmail( aEmailObj );
    		//iRetCode = m_EmlBRLOObj.sendEmailMSG( aEmailObj );
    		iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
    		
    		if(0 == iRetCode) {
    			aEmailObj.setEmailSentStatus( "sent" );
    		} else {
    			aEmailObj.setEmailSentStatus( aEmailObj.getErrorMsg() );	
    		}
    		//add email to db
    		iRetCode = m_IndBRLOObj.createEmail( aEmailObj );
    		
    		
    		// need to clear out the Email Obj here, rather than store it all
    	} else {
    		aEmailObj.appendErrorMsg("You chose to cancel.");
    	}
    	
    	
    	
    	//aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	return actionMapping.findForward( "feedbackconfirm" );
    	
     }
     // end-of method processSendFeedback()
    
    
     /**
      * showFeedback Confirm page
      */
      public ActionForward showFeedbackEmailConfirm(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  getPortalInfo( httpServletRequest, httpServletResponse);
     	String aszPortal="", aszPortalNID="";
         if(httpServletRequest.getParameter("portal") != null ){
             if(httpServletRequest.getParameter("portal").length() > 0){
                 aszPortal = httpServletRequest.getParameter("portal");
             }
         }
         if(session.getAttribute(aszPortal+"_nid") != null ){
             if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
             	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
             }
         }
         if(aszPortal.length()>0){
         	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
         	}
         }
     	//2006-09-08
     	return actionMapping.findForward( "feedbackconfirm" );//showlogin
      }
      // end-of method showInfoEmailConfirm()
 
  
      
      
      
      /**
       * showSendInfo page
       */
       public ActionForward showSendInfo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   getPortalInfo( httpServletRequest, httpServletResponse);
      	String aszPortal="", aszPortalNID="";
          if(httpServletRequest.getParameter("portal") != null ){
              if(httpServletRequest.getParameter("portal").length() > 0){
                  aszPortal = httpServletRequest.getParameter("portal");
              }
          }
          if(session.getAttribute(aszPortal+"_nid") != null ){
              if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
              	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
              }
          }
          if(aszPortal.length()>0){
          	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
          	}
          }
      	return actionMapping.findForward( "sendinfo" );
       }
       // end-of method showSendInfo()
      
       
      /**
       * processSendInfo page
       */
       public ActionForward processSendInfo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   getPortalInfo( httpServletRequest, httpServletResponse);
      	String aszPortal="", aszPortalNID="";
          if(httpServletRequest.getParameter("portal") != null ){
              if(httpServletRequest.getParameter("portal").length() > 0){
                  aszPortal = httpServletRequest.getParameter("portal");
              }
          }
          if(session.getAttribute(aszPortal+"_nid") != null ){
              if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
              	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
              }
          }
          if(aszPortal.length()>0){
          	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
          	}
          }
       	int iRetCode=0 ;
           EmailInfoDTO aEmailObj = new EmailInfoDTO();
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	
        	
        	String aszEmailMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);//"info@christianvolunteering.org";
        	String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);//"www.christianvolunteering.org";
        	String aszDomMainShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
        	String aszSiteOrgName = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
      	// need to load the form now.
          iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aEmailObj, false);

      	String aszIndEmail =  m_BaseHelp.getFormData( oFrm, "fromuser" ); 
      	// need to load the form now.
        String aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
    	String aszSmtpUserName = m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
    	String aszFromPassword = m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
      	String aszTo = aszEmailMain ; 
      	String aszSubject = aszDomMainShort ; 
    	String aszSubdom = m_BaseHelp.getFormData( oFrm, "subdom" ); 
      	

      	String aszFeedbackMess =  m_BaseHelp.getFormData( oFrm, "emailbodytxt" ); 

          String msgText =  aszFeedbackMess +
          	
          	"\n\n----------------------------------------\n\n" + 
          	"\nThis email has been sent to you through " + aszDomMain + 
          	"\nIf you have any questions, please email us at info@" + aszDomMainShort + 
          	"\nThank you for using " + aszDomMainShort + ", powered by " + aszSiteOrgName + ".\n" + 
          	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";

      	httpServletRequest.setAttribute("emailinfo", aEmailObj);

      	String aszSMTPAuthenticate = "Y";

      	aEmailObj.setEmailSMTPServerName( aszSMTPServer );
      	aEmailObj.setEmailFromUserPassword( aszFromPassword );
      	aEmailObj.setSMTPUserName( aszSmtpUserName );
      	aEmailObj.setEmailSubjectText( aszSubject );
      	aEmailObj.setEmailFromUser( aszIndEmail );
      	aEmailObj.setEmailToUser( aszTo );
      	aEmailObj.setEmailSubdom( aszSubdom );
      	aEmailObj.setEmailBodyText( msgText );
      	m_BaseHelp.setFormData( oFrm, "touser" , aEmailObj.getEmailToUser() ); 

      	httpServletRequest.setAttribute("emailinfo", aEmailObj);

          aEmailObj.setSMTPAuthRequired( true );

          	
          if(aszSMTPAuthenticate.equalsIgnoreCase("Y")){
          	aEmailObj.setSMTPAuthRequired( true );
      	} else {
          	aEmailObj.setSMTPAuthRequired( false );
      	}

      	String aszSendEmailOK = "Y";//m_BaseHelp.getFormData( oFrm, "employment" );
      	if(aszSendEmailOK.equalsIgnoreCase("Y")){
      		aszSendEmailOK="N";
      		//iRetCode = m_IndBRLOObj.createEmail( aEmailObj );
      		//iRetCode = m_EmlBRLOObj.sendEmailMSG( aEmailObj );
      		iRetCode = m_IndBRLOObj.sendEmailMSG( aEmailObj );
      		
      		if(0 == iRetCode) {
      			aEmailObj.setEmailSentStatus( "sent" );
      		} else {
      			aEmailObj.setEmailSentStatus( aEmailObj.getErrorMsg() );	
      		}
      		//add email to db
      		iRetCode = m_IndBRLOObj.createEmail( aEmailObj );
      		
      		
      		// need to clear out the Email Obj here, rather than store it all
      	} else {
      		aEmailObj.appendErrorMsg("You chose to cancel.");
      	}
      	
      	
      	
      	//aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	return actionMapping.findForward( "feedbackconfirm" );
      	
       }
       // end-of method processSendInfo()
      
      

        
        
      
      /**
       * showPassword Confirm page - No Result
       */
       public ActionForward showNoPwdConfirm(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   getPortalInfo( httpServletRequest, httpServletResponse);
      	String aszPortal="", aszPortalNID="";
          if(httpServletRequest.getParameter("portal") != null ){
              if(httpServletRequest.getParameter("portal").length() > 0){
                  aszPortal = httpServletRequest.getParameter("portal");
              }
          }
          if(session.getAttribute(aszPortal+"_nid") != null ){
              if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
              	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
              }
          }
          if(aszPortal.length()>0){
          	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
          	}
          }
      	//2006-09-08
      	return actionMapping.findForward( "nopwdconfirm" );//showlogin
       }
       // end-of method showForgotPassword()
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    //====== START Private Methods ===>
    //====== START Private Methods ===>
    //====== START Private Methods ===>


       /**
        * get portal information for page loading
        *//*
       private void getPortalInfo( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
	       	String aszPortal = "", aszFileLocation = "";
	    	int iRetCode=0, iOrgNID=0, iPortalUID=0;
	      	HttpSession session=httpServletRequest.getSession();
	        if(httpServletRequest.getParameter("portal") != null ){
	            if(httpServletRequest.getParameter("portal").length() > 0){
	                aszPortal = httpServletRequest.getParameter("portal");
	                if(
	                		session.getAttribute(aszPortal + "_banner") == null	||
	                		session.getAttribute(aszPortal + "_nid") == null	||
	                		session.getAttribute(aszPortal + "_uid") == null
	                ){
		                
		                // do a quick db query to get the filename of the banner image for this portal.  query will also get the org(church)/portal result
		            	AppCodeInfoDTO aEmailObj = new AppCodeInfoDTO();
		            	aEmailObj.setPortal(aszPortal);
		                allocatedIndBRLO( httpServletRequest );
		                iRetCode = m_IndBRLOObj.getPortalInfo( aEmailObj );
		
		                if (iRetCode == 0){
		                	aszFileLocation = aEmailObj.getPortalBanner();
		                	iOrgNID = aEmailObj.getPortalNID();
		                	iPortalUID = aEmailObj.getPortalUID();
		                	if(aszFileLocation.length()>0){
		                     	session.setAttribute(aszPortal + "_banner", aszFileLocation);
		                	}
		                	if(iOrgNID>0){
		                     	session.setAttribute(aszPortal + "_nid", ""+iOrgNID);
		                	}
		                	if(iPortalUID>0){
		                     	session.setAttribute(aszPortal + "_uid", ""+iPortalUID);
		                	}
		             	}
	                }
	            }
	        }
       }*/

       /**
        * get logged in status of user; if not, set a value to return the user to either login screen or cookielogin
        */
       protected void getLoggedInStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

     	  aszLoggedInStatus = "";
           if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
           		aszLoggedInStatus="showlogin";
           		return;
           	}else{
           		aszLoggedInStatus="processCookieLogin"  ;
           		return;
           	}
           }
        	 aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
           if(null == aCurrentUserObj) {
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
           		aszLoggedInStatus="showlogin";
           		return;
           	}else{
           		aszLoggedInStatus="processCookieLogin"  ;
           		return;
           	}
           }
       }
       /**
        * get portal information for page loading
       * @throws IOException 
        */
        protected void getPortalInfo( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      	  session=httpServletRequest.getSession();
      	  aszPortal = ""; aszPortalNID="";
      	  String aszFileLocation = "", aszPortalHeaderTags="", aszPortalHeader="", aszPortalCSS="", aszPortalFooter="",
      			  aszPortalOrgName = "", aszRequestType="";
      	  int iRetCode=0, iNID=0, iPortalUID=0;
            aszSiteVersion="default";
            if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
      				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
      				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
      			){
      			aszSiteVersion="development";
      		}else if(	httpServletRequest.getHeader("host").contains("staging-" ) 
      			){
      			aszSiteVersion="staging";
      		}
      	  if(httpServletRequest.getParameter("portal") != null ){
              if(httpServletRequest.getParameter("portal").length() > 0){
                  aszPortal = httpServletRequest.getParameter("portal");
                  
                  if(
                  		session.getAttribute(aszPortal + "_banner") == null	||
                  		session.getAttribute(aszPortal + "_header_tags") == null	||
                  		session.getAttribute(aszPortal + "_header") == null	||
                  		session.getAttribute(aszPortal + "_css") == null	||
                  		session.getAttribute(aszPortal + "_footer") == null	||
                  		session.getAttribute(aszPortal + "_org_name") == null	||
                  		session.getAttribute(aszPortal + "_nid") == null	||
                  		session.getAttribute(aszPortal + "_uid") == null	||
                  		session.getAttribute(aszPortal + "_type") == null
                  ){
  	                // do a quick db query to get the filename of the banner image for this portal.  query will also get the org(church)/portal result
  	            	AppCodeInfoDTO aEmailObj = new AppCodeInfoDTO();
  	            	aEmailObj.setPortal(aszPortal);
  	                allocatedIndBRLO( httpServletRequest );
  	                iRetCode = m_IndBRLOObj.getPortalInfo( aEmailObj );
  	
  	                if (iRetCode == 0){
  	                	aszFileLocation = aEmailObj.getPortalBanner();
  	                	aszPortalHeaderTags = aEmailObj.getPortalHeaderTags();
  	                	aszPortalHeader = aEmailObj.getPortalHeader();
  	                	aszPortalCSS = aEmailObj.getPortalCSS();
  	                	aszPortalFooter = aEmailObj.getPortalFooter();
  	                	aszPortalOrgName = aEmailObj.getPortalOrgName();
  	                	iNID = aEmailObj.getPortalNID();
  	                	iPortalUID = aEmailObj.getPortalUID();
  	                	aszRequestType = aEmailObj.getRequestType();
  	                	if(aszFileLocation.length()>0){
  	                     	session.setAttribute(aszPortal + "_banner", aszFileLocation);
  	                	}
  	                	if(aszPortalHeaderTags.length()>0){
  	                     	session.setAttribute(aszPortal + "_header_tags", aszPortalHeaderTags);
  	                	}
  	                	if(aszPortalHeader.length()>0){
  	                     	session.setAttribute(aszPortal + "_header", aszPortalHeader);
  	                	}
  	                	if(aszPortalCSS.length()>0){
  	                     	session.setAttribute(aszPortal + "_css", aszPortalCSS);
  	                	}
  	                	if(aszPortalFooter.length()>0){
  	                     	session.setAttribute(aszPortal + "_footer", aszPortalFooter);
  	                	}
  	                	if(aszPortalOrgName.length()>0){
  	                     	session.setAttribute(aszPortal + "_org_name", aszPortalOrgName);
  	                	}
  	                	if(iNID>0){
  	                     	session.setAttribute(aszPortal + "_nid", ""+iNID);
  	                	}
  	                	if(iPortalUID>0){
  	                     	session.setAttribute(aszPortal + "_uid", ""+iPortalUID);
  	                	}
  	                	//if(aszRequestType.length()>0){
  	                	String aszTempt = aszPortal + "_type: "+aszRequestType;
  	                     	session.setAttribute(aszPortal + "_type", aszRequestType);
  	                	//}
  	                	/*
  	                	else if(aszRequestTypeSubmitted == "natlassoc"){
  	                     	session.setAttribute(aszPortal + "_type", aszRequestTypeSubmitted);
  	                	}
  	                	*/
  	             	}
                  }
              }
          }

          try {
            httpServletRequest.setAttribute("location", m_BaseHelp.getLocation(httpServletRequest));
          }
          catch(IOException e) {
            System.out.println("Error looking up location:");
            e.printStackTrace();
          }
        	aszPortalRequestType = "";
        	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
        	if(aszPortal.length()<1){
  	      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
  	        aszPortalUID = "";
        	}else{
  	      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
  	        if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	      	if(session.getAttribute(aszPortal+"_type") != null ) 
  	      		if(session.getAttribute(aszPortal+"_type").toString().length() > 0) {
  	      			aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
  	      			
  	      		}
        	}
      }


// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
    /**
     * process cookie login
     */
     public ActionForward processCookieLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
System.out.println("processCookieLogin called");    	 
     	int iRetCode=0, iRetCode2=0 ;
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	getPortalInfo( httpServletRequest, httpServletResponse);
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
       	}
       }
 		String aszIPAddress = httpServletRequest.getRemoteAddr();

         allocatedIndBRLO( httpServletRequest );
      	
         // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
         // so we'll just forward along to the login page
         boolean b_mobile=false;
         String temp=httpServletRequest.getHeader("host");
         if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
  			httpServletRequest.getHeader("host").contains("http://m.chrisvol.org")	||
  			httpServletRequest.getHeader("host").contains("http://m.chrisvol.org")	||
         	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
         	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivol.org")	
         ){
             b_mobile=true;
         }
         String authCookieValue="";
         Cookie[] cookies = httpServletRequest.getCookies();
         String cookieName="chrisvolAuth";
         if(cookies != null){
	         for(int i=0; i<cookies.length; i++) {
	         	Cookie cookie = cookies[i];
	         	if (cookieName.equals(cookie.getName()))
	         		authCookieValue = cookie.getValue();
	         }
	         if(authCookieValue != null){
	         	if(authCookieValue.length()>0){
System.out.println(" chrisvolAuth cookie found");	         		
	//         		int itmp = PersonInfoDTO.COOKIE_USER;
	//         		iRetCode = m_IndBRLOObj.validateSignatureCookie( authCookieValue, aszIPAddress, itmp );
	//     			int indexForUID = authCookieValue.lastIndexOf(".");
	//     	 		String aszUID = authCookieValue.substring(indexForUID+1);
	//     	 		aIndivObj.setUserUID(aszUID);
	     	 		aIndivObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	     	 		aIndivObj.setSessionIP(aszIPAddress);
	     	 		aIndivObj.setSessionValue(authCookieValue);
	     	 		// does this session id exist in the sessions table in the db? and with the given IP address?
	     	 		iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aIndivObj );
	         		if( iRetCode == 0 ){
	         			// login the user
	         	        iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
	         	        if( (iRetCode != 0) && (iRetCode != -222) ){
	         	          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
	                    	if(b_mobile==true){
	                          	return actionMapping.findForward( "showlogin" );
	                       	}else{
	                       		return actionMapping.findForward( "loginstatus" );
	                       	}
	         	        }
	         	        iRetCode2=iRetCode;
	         	        // test if this is a full user or not; could have signed up through FB app, or create account process and still be partial
				     	AppSessionDTO aSessDat=null;
				       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
				        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
	         	        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
	         	            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
	         	        }else{
	         	            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
	         	        }
	         	        int iRetCode3=iRetCode;
	         	        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	         	        	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
	         	        	if(session.getAttribute("FB_User_ID")!=null){
	         	       		 if(session.getAttribute("FB_User_ID").toString().length()>1){
	         	       			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
	         	       		 }
	         	             if(session.getAttribute("facebookapikey")==null ){
	         	          		aIndivObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	         	             }else if (session.getAttribute("facebooksecretkey")==null){
	         	          		aIndivObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	         	             }else{
	         	             	String aszFBapikey = session.getAttribute("facebookapikey").toString();
	         	             	if(aszFBapikey.length()>1){
	         	             		aIndivObj.setFBapikey(aszFBapikey);
	         	             	}else{
	         	             		aIndivObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	         	             	}
	         	             	String aszFBsecretkey = session.getAttribute("facebooksecretkey").toString();
	         	             	if(aszFBsecretkey.length()>1){
	         	             		aIndivObj.setFBsecretkey(aszFBsecretkey);
	         	             	}else{
	         	             		aIndivObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	         	             	}
	         	             	boolean validateFBapikey = false;
	         	             }
	         	       	 	}
	         	        	if(iRetCode2!=-222){
	         	                iRetCode = m_IndBRLOObj.updateIndividualHead( aIndivObj, aszSiteVersion );
	         	            }
	         	        }
	         	        
	         	        aIndivObj.processTokens();
	         	    	if(aIndivObj.getProvider().length()>0){
	         	    		session.setAttribute("socialize", aIndivObj.getProvider());
	         	    	}
	         	        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
	         		        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
System.out.println(" line 11148 - currentuser set");	         		        
	         		        allocatedOrgBRLO( httpServletRequest );
	          		    	if(null != aSessDat){
	         		        	int iOppNID = aSessDat.getOppNID();
	         		        	int iOrgNID = aSessDat.getOrgNID();
	         		        	String aszToken = aSessDat.getTokenKey();
	         		        	aSessDat.setTokenKey(null);
	         	        	}
	         	    	}
	         	        
	         	        if(iRetCode2 != -222){
	         	        	// if the user is coming through facebook, we will still have their information, but we need to ask the user for more information and forward them to drupalaccount page
	         		        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	         		        	session.setAttribute("usecase", "volunteer");
	         		        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	         		        }
	         	        }
	         	        
	         	        ArrayList aList = new ArrayList();
	         	        if(iRetCode2 != -222){
	         		        iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aIndivObj.getUserUID()); 
	         		        httpServletRequest.setAttribute( "orglist", aList );
	         	        }
	
	         	        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
	         	        //if the user has an account in drupal, but we do not have all the fields that are required for voleng
	         	        //if(iRetCode2 == -222){
	         	        if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL ){	
	         	          	m_BaseHelp.setFormData(oFrm,"username", aIndivObj.getUSPUsername() );
	         	          	m_BaseHelp.setFormData(oFrm,"email1addr", aIndivObj.getUSPEmail1Addr() );
	         	          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
	         	          	m_BaseHelp.setFormData(oFrm,"password1", aIndivObj.getUSPPassword() );
	         	          	m_BaseHelp.setFormData(oFrm,"password2", aIndivObj.getUSPPassword() );
	         	          	m_BaseHelp.setFormData(oFrm,"internalusertypecomment", aIndivObj.getUSPInternalUserTypeComment() );
	         	        	m_BaseHelp.setFormData(oFrm,"upnid", "" + aIndivObj.getUserProfileNID() );
	         	        	m_BaseHelp.setFormData(oFrm,"upvid", "" + aIndivObj.getUserProfileVID() );
	         	        	m_BaseHelp.setFormData(oFrm,"uplid", "" + aIndivObj.getUserProfileLID() );
	         	        	m_BaseHelp.setFormData(oFrm,"uid", "" + aIndivObj.getUserUID() );
	         	         	m_BaseHelp.setFormData(oFrm,"internalcomment", "drupal"  );
	
	         	        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	         	            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
                	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
                	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                         	return actionMapping.findForward("mappingpage");
                    //                       	return actionMapping.findForward( "createaccount2" );
	         	       }
	         	        
	         	        session.setAttribute("usecase", "organization");
	         	        //httpServletRequest.setAttribute("organization", aOrgInfoObj);
	         	        String aszOrgNid="";
	         	        session.setAttribute("orgmanagementnid", aszOrgNid);
//	         	        return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
	         		}else{
	//               	return actionMapping.findForward( "loginstatus" );
	         		}
	         	} 
	         }
     	}
         
     	if(b_mobile==true){
          	return actionMapping.findForward( "showlogin" );
       	}else{
System.out.println(" returns loginstatus ");       		
       		return actionMapping.findForward( "loginstatus" );
       	}
     }
     // end-of method processLogin()
     

    /**
	* allocate business rule layers object for individual 
	*/
	private void allocatedIndBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_IndBRLOObj){
			m_IndBRLOObj = new IndividualsBRLO();
			m_IndBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedIndBRLO()



	/**
	* allocate business rule layes object for organization 
	*/
	private void allocatedOrgBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_OrgBRLOObj){
			m_OrgBRLOObj = new OrganizationBRLO();
			m_OrgBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedOrgBRLO()


	/**
	* allocate business rule layes object for email 
	*/
	private void allocatedEmailBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_EmailBRLOObj){
			m_EmailBRLOObj = new EmailBRLO();
			m_EmailBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedEmailBRLO()

	/**
	* allocate business rule layes object for organization 
	*/
	private void allocatedApplicCodesBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_AppCodesBRLOObj){
			m_AppCodesBRLOObj = new ApplicationCodesBRLO();
			m_AppCodesBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedApplicCodesBRLO()

	//====== END Private Methods ===>
    //====== END Private Methods ===>
    //====== END Private Methods ===>

	//====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>

	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private ActionHelper m_BaseHelp = new ActionHelper();
	private IndividualActionHelper m_IndActHelp = new IndividualActionHelper();
	private IndividualsBRLO m_IndBRLOObj=null;
	private OrganizationBRLO m_OrgBRLOObj=null;
	private EmailBRLO m_EmailBRLOObj = null;//new EmailBRLO();
	private ApplicationCodesBRLO m_AppCodesBRLOObj = null;
	//private ApplicationCodesBRLO m_AppCodeBRLOObj = new ApplicationCodesBRLO();
	
  	private String aszRailsPrefixPath = "";
  	private static final String aszRailsEditPath = "profiles~mine~edit";
  	private static final String aszRailsEditBasicPath = "profiles~mine~edit_basic";
	private static final String aszRailsAccountCreatePath = "profiles~mine~create_basic";
	private static final String aszRailsAccountCreatePageLEGACY = "cor~voleng~profiles~mine~create_basic";
	
	private OrganizationActionHelper m_OrgActHelp = new OrganizationActionHelper();
	public String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	private HttpSession session = null;
	private String aszSiteVersion = null;
	private PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
	private String aszLoggedInStatus = ""; 
	private static final String aszCVIntern = "cvintern";

	private int iLocalVolTID = 17254,iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266,iLocalOrgsTID = 21853,
		iVolDirectorytid = 8864;
	private int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
	vidDenomAffil=19, vidOrgAffil=5, 
	//vidVolDenom=262, vidVolOrgAffil=20, 
	vidVolDenom=19, vidVolOrgAffil=5, 
	vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
	vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
	//vidVolSkill=18, vidVolInterestArea=46, 
	vidVolSkill=31, vidVolInterestArea=32,
	vidVolServiceArea=32, vidVolCause=8, 
	vidState=52, vidCity=15, vidCountry=261, vidVolVirt=49,
	vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332, vidPersonality=336,
	vidCauseTopic=8, vidOtherPassions=338, vidOtherSkills=339, vidOtherLearningInterests=340;
	private int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519, ministryAreasTID=12521;
}
