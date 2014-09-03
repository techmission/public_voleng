package com.abrecorp.opensource.voleng.brlayer;

/**
* Created 2006-09-20
* Business Rules Layer Object BRLO
* For Indovidual Processing
* @author David Marcillo   Ali McCracken
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.WordUtils;
import org.apache.struts.upload.FormFile;
import org.quartz.utils.DBConnectionManager;

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.javamail.ABREJavaSendMail;
import com.abrecorp.opensource.javamail.EmailObj;
import com.abrecorp.opensource.organization.OrganizationObj;
import com.abrecorp.opensource.application.ApplicationObj;

public class EmailBRLO extends BaseVolEngBRLO{//ABREBaseBRLO {

	/**
	* Constructor
	*/
	public EmailBRLO() {}
	public EmailBRLO(HttpServletRequest request) {
		super.setupApp( request );
	}

	//=== START send email Section ===>
	//=== START send email Section ===>
	//=== START send email Section ===>

	/**
	* check email address format
	*/
    public int checkEmailFormat( String emailin ){
		int iRetCode=0;
        if(null == emailin) return -1;
        emailin = emailin.trim();
        if(emailin.length() < 5) return -1;
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.checkEmailFormat( emailin );
		return iRetCode;
	}
	// end-of method checkEmailFormat()

	/**
	* send email message
	*/
	public int sendEmailMSG( EmailInfoDTO aHeadObj, PersonInfoDTO currentPerson, OrgOpportunityInfoDTO opp ){
		int iRetCode=0;
		if(null == aHeadObj){
			return -1;
		}
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.sendEmailMessage( aHeadObj );
		aHeadObj.appendErrorMsg( aEmailSendObj.getAllMessages() );
		return iRetCode;
	}
	// end-of method sendEmailMSG()

	//=== END send email Section ===>
	//=== END send email Section ===>
	//=== END send email Section ===>

    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>


	/**
	* delete Opportunity record
	*/
	public int deleteEmail( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("deleteEmail");
    	EmailObj aEmailObj = new EmailObj();
    	//aOrgObj.setORGUpdateId( aPersObj.getUSPPersonNumber() );
    	pConn = getDBConn();
    	iRetCode = aEmailObj.deleteEmailFromDB(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteEmail()

	/**
	* create Email record
	*/
	public int createEmail( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("createEmail");
    	EmailObj aEmailObj = new EmailObj();
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getEmailSubdom().length()<1){
    		aHeadObj.setEmailSubdom(aszDomMain);
    	}

    	// validate email address format
    	aszTemp = aHeadObj.getEmailFromUser();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("email format error");
    		return -1;
    	}
    	/*
    	if(null != pConn) pConn.free();
    	if(0 == iRetCode){
    		setErr("organization found");
    		return -1;
    	}
    	*/
    	int iNextID = getNextUniqueID( ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
    	if( iNextID < 1 ){
    		setErr("FILE EmailBRLO, createEmail :/n     error getting unique id for " + ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
    		ErrorsToLog();
    		aHeadObj.appendErrorMsg(" database is not available at this time, please try later ");
    		return -1;
    	}
    	aHeadObj.setEmailId( iNextID );
    	pConn = getDBConn();
    	iRetCode = aEmailObj.createEmail(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method createEmail()


	/**
	* load Opportunity Match (email) record
	*/
	public int loadEmail( EmailInfoDTO aHeadObj ){
		return loadEmail(  aHeadObj, 0 );
	}
	public int loadEmail( EmailInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("loadEmail");
    	EmailObj aEmailObj = new EmailObj();
    	pConn = getDBConn();
    	iRetCode = aEmailObj.loadEmail(pConn, aHeadObj, iType );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadEmail()

	/**
	* get email list for an organization, opportunity, or volunteer
	*/
	public int getEmailList( ArrayList aList, EmailInfoDTO aHeadObj, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iType < 1) return -1;
    	EmailObj aEmailObj = new EmailObj();
    	pConn = getDBConn();
    	iRetCode = aEmailObj.getEmailList(pConn, aList, aHeadObj, iType);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getEmailList()

	/*
	 * set the text for the email to be sent based on the objects that were passed
	 */
	public int setEmailText( PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, OrgOpportunityInfoDTO aOppInfoObj, EmailInfoDTO aEmailObj ){
		int iRetCode=0;
		
		String aszApplicantEmailAddress = "";
		String aszOrgEmail = "";
		
		if(aEmailObj.getEmailFromUser().length()>0)
			aszApplicantEmailAddress = aEmailObj.getEmailFromUser();
		else
			aszApplicantEmailAddress = aIndivObj.getUSPEmail1Addr();
		
		if(aEmailObj.getEmailToUser().length()>0){
			aszOrgEmail = aEmailObj.getEmailToUser();
		}else{
			aszOrgEmail = aOrgInfoObj.getORGOrgContactEmail();
			aEmailObj.setEmailToUsers(aszOrgEmail);
			aEmailObj.setEmailToUser(aszOrgEmail);
		}
		
		aEmailObj.setEmailFromUser(aszApplicantEmailAddress);
		
		String aszOrgName = aOrgInfoObj.getORGOrgName();
		
		String msgTextAddress = "Dear " + aEmailObj.getEmailBodyTextIntro() + "\n\n";
// abandoned code
		
		
		return iRetCode;
	}
	
	/**
	* create createApplication record
	*/
	public int createApplication( ApplicationInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	String aszTemp = "",aszIneligibilityReason="";
    	int iTemp=0;
    	boolean bMinOK = true;
    	boolean bDisqualified = false;
    	MethodInit("createApplication");
    	aHeadObj.setTitle(aHeadObj.getNameFirst()+" "+aHeadObj.getNameLast());
    	ApplicationObj aApplicObj = new ApplicationObj();
    	
    	iTemp = aHeadObj.getUID();
    	if(iTemp>0){
    		aHeadObj.setUserProfileLink("http://www.urbanministry.org/user/"+iTemp+"/profile/uprofile");
    	}
System.out.println("EmailBRLO 236 - resume filepath: "+aHeadObj.getResumeFilePath());		

    	// field requirements 
    	aszTemp = aHeadObj.getNameFirst();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- First Name is a Required Field \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getNameLast();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Last Name is a Required Field \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getEmailAddr();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Email is a Required Field \n");
    		bMinOK=false;
    	}else{
	    	iRetCode = checkEmailFormat( aszTemp );
	    	if(0 != iRetCode){
	    		aHeadObj.appendErrorMsg("- Email format error \n");
	    		bMinOK = false;
	    	}
    	}
    	aszTemp = aHeadObj.getPhone();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Phone Number is a Required Field \n");
    		bMinOK=false;
    	}
    	iTemp = aHeadObj.getSourceTID();
    	if(iTemp < 1 ){
    		aHeadObj.appendErrorMsg("- Please Provide the Method in which you heard about City Vision Internships \n");
    		bMinOK=false;
    	}
    	iTemp = aHeadObj.getInternLengthTID();
    	if(iTemp < 1 ){
    		aHeadObj.appendErrorMsg("- Are you looking for a one year internship, or a summer internship? \n");
    		bMinOK=false;
    	}
    	int[] a_iTemp = aHeadObj.getInternTypeTIDsArray();
    	if(a_iTemp.length < 1 ){
    		aHeadObj.appendErrorMsg("- Please specify the type of internship you are interested in \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getHasBachelors();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Do you currently have a Bachelor's Degree? \n");
    		bMinOK=false;
    	}else if(aszTemp.equalsIgnoreCase("No")){
	    	iTemp = aHeadObj.getCredits();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- How many college credits do you have? \n");
	    		bMinOK=false;
	    	}
    	}

    	aszTemp = aHeadObj.getChristian();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you a Christian? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getChristian().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not a Christian\n\n";
    	}
    	aszTemp = aHeadObj.getAgeRequirement();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you at least 18 years old? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getAgeRequirement().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not 18+ Years of age\n\n";
    	}

    	aszTemp = aHeadObj.getDiploma();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- You must indicate whether you have a diploma or GED \n");
    		bMinOK=false;
    	}else if(!(aHeadObj.getDiploma().contains("Diploma") || aHeadObj.getDiploma().contains("GED") )){
    		bDisqualified = true;
    		aszIneligibilityReason+="Does not have Diploma or GED\n\n";
    	}
    	iTemp = aHeadObj.getCitizenTID();
    	if(iTemp < 1 ){
    		aHeadObj.appendErrorMsg("- You must indicate your United States citizenship status \n");
    		bMinOK=false;
    	}else if(aHeadObj.getCitizenTID()== 38798){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not a valid Citizenship Status\n\n";
    	}
    	aszTemp = aHeadObj.getTimeCommitAbility();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you able to commit to the full length of the internship? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getTimeCommitAbility().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not able to commit to the length of internship\n\n";
    	}
    	aszTemp = aHeadObj.getBachelorsAttending();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Do you have a Bachelor's degree or have you been recently active in school? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getBachelorsAttending().equalsIgnoreCase("No")){
    		// make sure the student was current in the past 5 years
    		int year = Calendar.getInstance().get(Calendar.YEAR);
    		int i_lastYrSchool = aHeadObj.getLastYrActiveHS();
    		if( (year-i_lastYrSchool) > 5){
    			bDisqualified = true;
        		aszIneligibilityReason+="No bachelors and not recently active in school; has been more than 5 years since last in school\n\n";
    		}
    	}

    	
    	if(bDisqualified){
    		aHeadObj.setScreened(-5);
    		aHeadObj.setIneligibilityReason(aszIneligibilityReason);
    	}
    	pConn = getDBConn();
    	iRetCode = aApplicObj.insertApplication(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	
    	if(bDisqualified){
    		return -5;
    	}
    	return iRetCode;
	}
	// end-of method createApplication()

	/**
	* create updateApplication record
	*/
	public int updateApplication( ApplicationInfoDTO aHeadObj, int iCreateStep ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	String aszTemp = "",aszIneligibilityReason="";
    	int iTemp=0;
    	boolean bMinOK = true;
    	boolean bDisqualified = false;
		MethodInit("updateApplication");
    	ApplicationObj aApplicObj = new ApplicationObj();
    	
    	
    	iTemp = aHeadObj.getUID();
    	if(iTemp>0){
    		aHeadObj.setUserProfileLink("http://www.urbanministry.org/user/"+iTemp+"/profile/uprofile");
    	}
System.out.println("EmailBRLO 379 - resume filepath: "+aHeadObj.getResumeFilePath());		
    	
    	// field requirements for 1
    	if(iCreateStep == 1){
	    	aszTemp = aHeadObj.getNameFirst();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- First Name is a Required Field \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getNameLast();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Last Name is a Required Field \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getEmailAddr();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Email is a Required Field \n");
	    		bMinOK=false;
	    	}else{
		    	iRetCode = checkEmailFormat( aszTemp );
		    	if(0 != iRetCode){
		    		aHeadObj.appendErrorMsg("- Email format error \n");
		    		bMinOK = false;
		    	}
	    	}
	    	aszTemp = aHeadObj.getPhone();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Phone Number is a Required Field \n");
	    		bMinOK=false;
	    	}
	    	iTemp = aHeadObj.getSourceTID();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- Please Provide the Method in which you heard about City Vision Internships \n");
	    		bMinOK=false;
	    	}
	    	iTemp = aHeadObj.getInternLengthTID();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- Are you looking for a one year internship, or a summer internship? \n");
	    		bMinOK=false;
	    	}
	    	int[] a_iTemp = aHeadObj.getInternTypeTIDsArray();
	    	iTemp = aHeadObj.getInternTypeTID();
	    	if(a_iTemp.length < 1 ){
	    		aHeadObj.appendErrorMsg("- Please specify the type of internship you are interested in \n");
	    		bMinOK=false;
	    	}


    	
    	}
    	
    	// field requirements for Stage 2
    	if(iCreateStep == 2){
	    	aszTemp = aHeadObj.getAddrLine1();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Street Address is Required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getAddrCity();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- City is Required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getAddrStateprov();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- State/Province is Required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getAddrPostalcode();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Zipcode is Required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getAddrCountryName();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Country is Required \n");
	    		bMinOK=false;
	    	}
	    	// testimony is supposed to be 200-500 words; do a                  (\b[^\s]+\b){250,500}
	    	/* test data 
	    	 word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word word 
	    	 */
	    	aszTemp = aHeadObj.getTestimony();
	    	String trimmed = aszTemp.trim();
	    	int iTestimonyLength = trimmed.length();
	    	if( iTestimonyLength < 1200 ){ 
	    		aHeadObj.appendErrorMsg("- Testimony is not long enough. Please take the time to answer thoroughly, as this is the most important factor in hiring decisions.\n");
	    		bMinOK=false;
	    	}
	    	if(iTestimonyLength > 10000){ 
	    		aHeadObj.appendErrorMsg("- Testimony is too long enough. Please try to limit your response to 10,000 characters.\n");
	    		bMinOK=false;
	    	}
	    	/*
	    	int iWords = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
	    	if(!( (iWords > 100) ) ){ //&& (iWords < 500) 
	    		aHeadObj.appendErrorMsg("- Testimony is not at least 200 words \n");
	    		bMinOK=false;
	    	}
	    	*/
	    	aszTemp = aHeadObj.getGeoPref();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Geographic Preference is Required \n");
	    		bMinOK=false;
	    	}
    	}
    	
    	// field requirements for Stage 3
    	if(iCreateStep == 3){
	    	iTemp = aHeadObj.getPosPrefTID();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- Please indicate the type of position you prefer \n");
	    		bMinOK=false;
	    	}
	    	/*
	    	iTemp = aHeadObj.getSkillsTID();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- Please indicate if you have any special skills or experience \n");
	    		bMinOK=false;
	    	}
	    	*/
	    	aszTemp = aHeadObj.getInternReason();
	    	if(aszTemp.length() < 8 ){
	    		aHeadObj.appendErrorMsg("- Please indicate why you are interested in this program - your answer is too short \n");
	    		bMinOK=false;
	    	}
	    	iTemp = aHeadObj.getWorkEnvironTID();
	    	if(iTemp < 1 ){
	    		aHeadObj.appendErrorMsg("- What type of work environment do you prefer? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getChurch();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Are you actively attending a local church? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getMajor();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- What would your prefered major be? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getCareerGoals();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide your career goals \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getHrlyCommit();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Are you able to commit to the hours? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getLivableStipend();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Is the stipend livable for you? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getLivableStipendExpl();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please indicate how the stipend is livable or not for you \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getCrimRecord();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Have you ever been convicted of a crime? \n");
	    		bMinOK=false;
	    	}
	    	if(aszTemp.equalsIgnoreCase("Yes")){
		    	aszTemp = aHeadObj.getCrimDescrip();
		    	if(aszTemp.length() < 1 ){
		    		aHeadObj.appendErrorMsg("- Please describe the circumstances regarding your criminal conviction \n");
		    		bMinOK=false;
		    	}
	    	}
	    	java.util.Date d_temp = aHeadObj.getDOBDt();
	    	if(d_temp.equals(null) ){
	    		aHeadObj.appendErrorMsg("- Please provide your date of birth \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getHousing();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Do you need housing? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getStartTime();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide when you can start \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getForwardResume();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- May we forward your resume to other comparible organizations? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getWebcam();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Do you have a webcam? \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getPastoralRef();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide a Pastoral Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getPastoralRefChurch();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide the Church or Ministry for your Pastoral Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getPastoralRefPhone();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide a phone number for your Pastoral Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getPastoralRefEmail();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide an Email address for your Pastoral Reference \n");
	    		bMinOK=false;
	    	}else{
		    	iRetCode = checkEmailFormat( aszTemp );
		    	if(0 != iRetCode){
		    		aHeadObj.appendErrorMsg("- Email format error for Pastoral Reference \n");
		    		bMinOK = false;
		    	}
	    	}
	    	aszTemp = aHeadObj.getProfRef();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide a Professional Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getProfRefOrg();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide the Organization for your Professional Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getProfRefPhone();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide a phone number for your Professional Reference \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getProfRefEmail();
	    	if(aszTemp.length() < 1 ){
	    		aHeadObj.appendErrorMsg("- Please provide an Email address for your Professional Reference \n");
	    		bMinOK=false;
	    	}else{
		    	iRetCode = checkEmailFormat( aszTemp );
		    	if(0 != iRetCode){
		    		aHeadObj.appendErrorMsg("- Email format error for Professional Reference \n");
		    		bMinOK = false;
		    	}
	    	}
    	}
    	aszTemp = aHeadObj.getChristian();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you a Christian? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getChristian().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not a Christian\n\n";
    	}
    	aszTemp = aHeadObj.getAgeRequirement();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you at least 18 years old? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getAgeRequirement().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not 18+ Years of age\n\n";
    	}

    	aszTemp = aHeadObj.getDiploma();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- You must indicate whether you have a diploma or GED \n");
    		bMinOK=false;
    	}else if(!(aHeadObj.getDiploma().contains("Diploma") || aHeadObj.getDiploma().contains("GED") )){
    		bDisqualified = true;
    		aszIneligibilityReason+="Does not have Diploma or GED\n\n";
    	}
    	iTemp = aHeadObj.getCitizenTID();
    	if(iTemp < 1 ){
    		aHeadObj.appendErrorMsg("- You must indicate your United States citizenship status \n");
    		bMinOK=false;
    	}else if(aHeadObj.getCitizenTID()== 38798){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not a valid Citizenship Status\n\n";
    	}
    	aszTemp = aHeadObj.getTimeCommitAbility();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Are you able to commit to the full length of the internship? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getTimeCommitAbility().equalsIgnoreCase("No")){
    		bDisqualified = true;
    		aszIneligibilityReason+="Not able to commit to the length of internship\n\n";
    	}
    	aszTemp = aHeadObj.getBachelorsAttending();
    	if(aszTemp.length() < 1 ){
    		aHeadObj.appendErrorMsg("- Do you have a Bachelor's degree or have you been recently active in school? \n");
    		bMinOK=false;
    	}else if(aHeadObj.getBachelorsAttending().equalsIgnoreCase("No")){
    		// make sure the student was current in the past 5 years
    		int year = Calendar.getInstance().get(Calendar.YEAR);
    		int i_lastYrSchool = aHeadObj.getLastYrActiveHS();
    		if( (year-i_lastYrSchool) > 5){
    			bDisqualified = true;
        		aszIneligibilityReason+="No bachelors and not recently active in school; has been more than 5 years since last in school\n\n";
    		}
    	}

    	if(bDisqualified){
    		aHeadObj.setScreened(-5);
    		aHeadObj.setIneligibilityReason(aszIneligibilityReason);
    	}

    	
    	if(false == bMinOK) return -1;
    	
    	pConn = getDBConn();
    	iRetCode = aApplicObj.updateApplication(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	if(bDisqualified){
    		return -5;
    	}
    	return iRetCode;
	}
	// end-of method updateApplication()


	/**
	* load loadApplication Match (email) record
	*/
	public int loadApplication( ApplicationInfoDTO aHeadObj, int iIdNum, int iKeyId, int iType ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("loadApplication");
    	ApplicationObj aApplicObj = new ApplicationObj();
    	pConn = getDBConn();
System.out.println("EmailBRLO line 678 iType is "+iType);    	
    	iRetCode = aApplicObj.loadOneApplication(pConn, aHeadObj, iIdNum, iKeyId, iType );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadApplication()

	/**
	* get getApplicationList list for an organization, opportunity, or volunteer
	*/
	public int getApplicationList( ArrayList aList, ApplicationInfoDTO aHeadObj, int iIdNum, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iType < 1) return -1;
    	ApplicationObj aApplicObj = new ApplicationObj();
    	pConn = getDBConn();
    	iRetCode = aApplicObj.getApplicationsList(pConn, aList, iIdNum, iType);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getApplicationList()
	
    

	/**
	* send email message
	*/
	public int sendEmailMSG( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		if(null == aHeadObj){
			return -1;
		}
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
        
		iRetCode = aEmailSendObj.sendEmailMessage( aHeadObj );
		aHeadObj.appendErrorMsg( aEmailSendObj.getAllMessages() );
		return iRetCode;
	}
	// end-of method sendEmailMSG()
	
	/**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getTaxonomyCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	EmailObj aEmailObj = new EmailObj();
		pConn = getDBConn();
		iRetCode = aEmailObj.getTaxonomyCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>

	public List<String> checkForRequiredDocuments(EmailInfoDTO aHeadObj, PersonInfoDTO user, OrgOpportunityInfoDTO opp) {
		List<String> errors = new LinkedList<String>();
		
		if(opp.getResumeRequired() != 0 && (user.getUSPResumeFilePath() == null || user.getUSPResumeFilePath().length() <= 0)) {
			errors.addAll(checkFile(aHeadObj.getResumeFile(), "Resume"));
		}
		
		if(opp.getCoverLetterRequired() != 0) {
			errors.addAll(checkFile(aHeadObj.getCoverLetterFile(), "Cover Letter"));
		}
		
		if(opp.getQuestionnaireType().equals("on_paper")) {
			errors.addAll(checkFile(aHeadObj.getApplicationFile(), "Application"));
		}
		
		for(RequiredDocumentDTO doc : opp.getRequiredDocuments()) {
			errors.addAll(checkFile(aHeadObj.getRequiredDocumentFiles().get(doc.getNid()), doc.getName()));
		}
		
		return errors;
	}
	
	public int uploadRequiredDocuments(EmailInfoDTO aHeadObj, OrgOpportunityInfoDTO opp, PersonInfoDTO user, String documentRoot, IndividualsBRLO individualsBRLO, ApplicationCodesBRLO appCodesBRLO, HttpServletRequest httpServletRequest) {
		int ret = 0;
		
		String baseDir = (new File(documentRoot)).getParent() + File.separator + 
		         "files" + File.separator;
		
		String applicationBaseDir = baseDir +
				"filled_out_opportunity_documents" + File.separator +
				aHeadObj.getEmailId() + File.separator;
		
		if(opp.getResumeRequired() != 0 && (user.getUSPResumeFilePath() == null || user.getUSPResumeFilePath().length() <= 0)) {
			File f = getDestinationFile(aHeadObj.getResumeFile(), baseDir + File.separator + "resumes" + File.separator + individualsBRLO.getResumeFileName(user));
			ret = writeFile(aHeadObj.getResumeFile(), f);
			if(ret >= 0) ret = individualsBRLO.linkResume(user);
			if(ret >= 0) ret = individualsBRLO.indexAndTagResume(f, httpServletRequest, user, appCodesBRLO);
		}
		
		if(opp.getCoverLetterRequired() != 0) {
			int r = writeFile(aHeadObj.getCoverLetterFile(), getDestinationFile(aHeadObj.getCoverLetterFile(), applicationBaseDir + "cover_letter"));
System.out.println("EmailBRLO - line 825; after write; before aszFileName cover letter is set ");			
			String aszFileName = getDestinationFileName(aHeadObj.getCoverLetterFile(), applicationBaseDir + "cover_letter");
System.out.println("EmailBRLO - line 827; aszFileName cover letter is "+aszFileName);			
			if(ret >= 0) ret = r;
			int iFileNameLength = aszFileName.length();
			if(iFileNameLength>0){
				int iIndexFileName = aszFileName.indexOf("cover_letter");
				try{
					aszFileName = aszFileName.substring(iIndexFileName, iFileNameLength);
				}catch(IndexOutOfBoundsException e){
					System.out.println("index out of bounds");
				}
			}
			aHeadObj.setCoverLetterFileName(aszFileName);
		}
		
		if(opp.getQuestionnaireType().equals("on_paper")) {
			int r = writeFile(aHeadObj.getApplicationFile(), getDestinationFile(aHeadObj.getApplicationFile(), applicationBaseDir + "application"));
			String aszFileName = getDestinationFileName(aHeadObj.getCoverLetterFile(), applicationBaseDir + "application");
System.out.println("EmailBRLO - line 835; aszFileName application is "+aszFileName);			
			if(ret >= 0) ret = r;
			int iFileNameLength = aszFileName.length();
			if(iFileNameLength>0){
				int iIndexFileName = aszFileName.indexOf("application");
				try{
					aszFileName = aszFileName.substring(iIndexFileName, iFileNameLength);
				}catch(IndexOutOfBoundsException e){
					System.out.println("index out of bounds");
				}
			}
			aHeadObj.setApplicationFileName(aszFileName);
		}
		
		String aszOtherDocs = null;
		int iCount=0;
		for(RequiredDocumentDTO doc : opp.getRequiredDocuments()) {
			FormFile f = aHeadObj.getRequiredDocumentFiles().get(doc.getNid());
			int r = writeFile(f, getDestinationFile(f, applicationBaseDir + "other_docs" + File.separator + doc.getNid()));
			String aszFileName = getDestinationFileName(f, applicationBaseDir + "other_docs" + File.separator + doc.getNid());
			if(ret >= 0) ret = r;
			if(iCount>0){
				aszOtherDocs+=";";
			}
			int iFileNameLength = aszFileName.length();
			if(iFileNameLength>0){
				int iIndexLastSlash = 0;
				iIndexLastSlash = aszFileName.lastIndexOf("/");
				if(iIndexLastSlash < 1){
					aszFileName.lastIndexOf("\\");
				}
				aszFileName = aszFileName.substring(iIndexLastSlash, iFileNameLength);
				aszOtherDocs+=aszFileName;
			}
		}
		if(aszOtherDocs != null){
			aHeadObj.setOtherDocsFileName(aszOtherDocs);
		}
		
		return ret;
	}
	
	private List<String> checkFile(FormFile f, String fieldName) {
		List<String> errors = new LinkedList<String>();
		
		if(f == null || f.getFileName().length() <= 0)
			errors.add(fieldName + " is required.");		
		else { 
			if(!Arrays.asList(new String[] {
				"application/pdf",
				"application/msword",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
				"text/plain",
				"application/rtf",
				"text/rtf"
			}).contains(f.getContentType())) {
				errors.add(fieldName  +  " must be .pdf, .doc, .docx, .txt or .rtf\r\n");
			}
			if(f.getFileSize() > 1024000) {
				errors.add(fieldName + " is too large. The file must not exceed 1MB.");
			}
		}
        
		return errors;
	}
	
	private String getDestinationFileName(FormFile clientFile, String destinationFilename) {
		String extension;
		if(clientFile.getContentType().equals("application/pdf")){
			extension = ".pdf";
		}else if(clientFile.getContentType().equals("application/msword")){
			extension = ".doc";
		}else if(clientFile.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
			extension = ".docx";
		}else if(clientFile.getContentType().equals("text/plain")){
			extension = ".txt";
		}else if(clientFile.getContentType().equals("application/rtf")){
			extension = ".rtf";
		}else if(clientFile.getContentType().equals("text/rtf")){
			extension = ".rtf";
		}else {
			return null;
		}
		return destinationFilename+extension;
	}
	
	private File getDestinationFile(FormFile clientFile, String destinationFilename) {
		String extension;
		if(clientFile.getContentType().equals("application/pdf")){
			extension = ".pdf";
		}else if(clientFile.getContentType().equals("application/msword")){
			extension = ".doc";
		}else if(clientFile.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
			extension = ".docx";
		}else if(clientFile.getContentType().equals("text/plain")){
			extension = ".txt";
		}else if(clientFile.getContentType().equals("application/rtf")){
			extension = ".rtf";
		}else if(clientFile.getContentType().equals("text/rtf")){
			extension = ".rtf";
		}else {
			return null;
		}
		return new File(destinationFilename + extension);
	}
	
	private int writeFile(FormFile clientFile, File serverFile) {		
		serverFile.getParentFile().mkdirs();
		try {
			(new FileOutputStream(serverFile)).write(clientFile.getFileData());
		} catch (FileNotFoundException e) {
			return -1;
		} catch (IOException e) {
			return -2;
		}
		return 0;
	}
	
	
	
	
	private static int iOrgApplicMatchEmailNID = 546506, iApplicConfirmEmailNID = 544402;

}
