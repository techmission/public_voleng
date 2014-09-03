package com.abrecorp.opensource.voleng.brlayer;

/**
* Created 2006-03-29
* Business Rules Layer Object BRLO
* For organizations and opportunities Processing
* @author David Marcillo
* ABRE Technology Corp.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import org.owasp.validator.html.*;


//import org.apache.commons.httpclient.*;


import java.net.MalformedURLException;
import java.net.URL;

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.javamail.ABREJavaSendMail;
import com.abrecorp.opensource.javamail.EmailObj;
import com.abrecorp.opensource.organization.OrganizationDBDAO;
import com.abrecorp.opensource.organization.OrganizationObj;


public class OrganizationBRLO extends BaseVolEngBRLO {

	/**
	* Constructor
	*/
	public OrganizationBRLO() {}
	public OrganizationBRLO(HttpServletRequest request) {
		super.setupApp( request );
	}

	/**
	* send email for opportunity
	* expect from user object and opportunity object
	* loads the ContactUser's info
	*/
    public int prepForSendEmailForOpportunity( PersonInfoDTO aVolunteerPersonObj, PersonInfoDTO aContactPersonObj, int iType, String aszSiteVersion ){
		int iRetCode=0;
    	MethodInit("sendEmailForOpportunity");
        if(null == aVolunteerPersonObj){
    		setErr("Sending email user required");
        	return -1;
        }
        if(null == aContactPersonObj){
    		setErr("Recipient email user required");
        	return -1;
        }
        IndividualsBRLO aIndBRLOObj = new IndividualsBRLO();
        aIndBRLOObj.setBaseAppRef( getBaseAppRef() );
    	
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
        iRetCode = aIndBRLOObj.loadUserByOption( aContactPersonObj, iType, aszRailsDB );
        if(0 != iRetCode && -222!=iRetCode){
    		setErr("Opportunity contact required");
        	return -1;
        }
    	return 0;
    }
    // end-of method prepForSendEmailForOpportunity()

    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>

    /**
	* get opportinuties list for an organization
	*/
	public int getOppListForOrg( ArrayList aList, int iOrgNID){
		return getOppListForOrg(aList, iOrgNID, 0);
	}
	public int getOppListForOrg( ArrayList aList, int iOrgNID, int iSort){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		ABREDBConnection pConnSub=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	if(iSort > 0){
    		aSrchParmObj.setSearchType( iSort ); 
    	}else{
    		aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_ORGNID_MANAGE ); 
    	}

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	pConnSub = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSrchParmObj,0 );
    	if(null != pConn) pConn.free();
    	if(null != pConnSub) pConnSub.free();
    	return iRetCode;
	}
	// end-of method getOppListForOrg()
	
    /**
	* get opportinuties list for a user
	*/
	public int getOppListForAdmin( ArrayList aList, int iUserUID){
		return getOppListForAdmin( aList, iUserUID, OrganizationInfoDTO.LOADBY_UID_ADMIN );
	}
	public int getOppListForAdmin( ArrayList aList, int iUserUID, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iUserUID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setUID( iUserUID );
    	aSrchParmObj.setSearchType( iType ); 

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSrchParmObj,iType );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOppListForAdmin()

    /**
	* get opportunities list for a user
	*/
	public int getOppListForContact( ArrayList aList, int iUserUID){
		int iOrgNID=0;
    	return getOppListForContact(aList, iUserUID, iOrgNID);
	}
	// end-of method getOppListForContact()
	public int getOppListForContact( ArrayList aList, int iUserUID, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		ABREDBConnection pConnSub=null;
    	if(null == aList) return -1;
    	if(iUserUID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setUID( iUserUID );
    	aSrchParmObj.setORGNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID_CONTACT ); 

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	pConnSub = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSrchParmObj,0 );
    	if(null != pConn) pConn.free();
    	if(null != pConnSub) pConnSub.free();
    	return iRetCode;
	}
	// end-of method getOppListForContact()

    /**
	* get opportinuties list for an admin
	*/
	public int getOppListSiteAdmin( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		ABREDBConnection pConnSub=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_SITEADMIN ); 

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	pConnSub = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSrchParmObj,0 );
    	if(null != pConn) pConn.free();
    	if(null != pConnSub) pConnSub.free();
    	return iRetCode;
	}
	// end-of method getOppListAdmin()

	/**
	* load Opportunity record using the legacy voleng id system
	*/
	public int loadOpportunityByURLID( OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic, String aszSiteVersion ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	if(iIDNum< 1){
    		setErr("Opportunity number required");
    		return -1;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadOpportunityByURLID(pConn, aHeadObj, iIDNum, iPublic, getRailsDBBySiteVersion(aszSiteVersion) );

    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadOpportunityByURLID()

	public int loadRequiredDocumentByNid(RequiredDocumentDTO doc, int nid) {
		ABREDBConnection pConn = getDBConn();
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	int ret = aOrganizationObj.loadRequiredDocumentByNid(pConn, doc, nid);
    	if(null != pConn) pConn.free();
    	return ret;
	}
	
	/**
	* load Opportunity record
	public int loadOpportunity( OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic){
		return loadOpportunity( aHeadObj, iIDNum, iPublic, "");
	}
	/**
	* load Opportunity record
	*/
	public int loadOpportunity( OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic, String aszUrlAlias, int iType, String aszSiteVersion, String documentRoot ){
        String aszURLAliasFormat = "volunteer/"; // aOrgInfoObj.getPathAutoOppPattern();
    	return loadOpportunity(  aHeadObj,  iIDNum,  iPublic,  aszUrlAlias,  iType, aszURLAliasFormat, aszSiteVersion, documentRoot );
	}
	public int loadOpportunity( OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic, String aszUrlAlias, int iType, String aszURLAliasFormat, String aszSiteVersion, String documentRoot ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	if(iIDNum< 1 && aszUrlAlias.length()<1){
    		setErr("Opportunity number or URL alias required");
    		return -1;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadOpportunityByID(pConn, aHeadObj, iIDNum, iPublic, aszUrlAlias, iType, aszURLAliasFormat, getRailsDBBySiteVersion(aszSiteVersion));
    	
    	if(aHeadObj.getQuestionnaireType() != null && 
    	aHeadObj.getQuestionnaireType().equals("on_paper")) {
    		loadQuestionnaireServerFile(aHeadObj, documentRoot);
    	}
    	
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadOpportunity()

	/**
	* delete Opportunity record
	*/
	public int deleteOpportunity( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	if(null == aHeadObj) return -1;
    	MethodInit("deleteOpportunity");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOpportunity(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteOpportunity()

	
	
	
    public int loadOrg( OrganizationInfoDTO aOrgInfoObj){
        
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		
   		OrganizationObj aOrganizationObj = new OrganizationObj();
       	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadOrganizationFromDB(pConn, aOrgInfoObj, "", OrganizationInfoDTO.LOADBY_ORGNUMBER, "/org" );
System.out.println(" ********* CRON OUTPUT cron output - line 235 - ran loadOrg");	    	
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
 
	
	
	
	
	
	public enum SocialGraphEligibility {
		ALREADY_SIGNED,
		NOT_ORG_ADMIN,
		ELIGIBLE
	}
	
	public OrganizationInfoDTO getSocialGraphOrg(PersonInfoDTO user) {
		ArrayList<OrganizationInfoDTO> orgs = new ArrayList<OrganizationInfoDTO>(); 
		this.getOrgListForMember(orgs, user.getUserUID());
		if(orgs.size() <= 0) return null;
		OrganizationInfoDTO org = null;
		for(OrganizationInfoDTO o : orgs) {
			if(org == null || o.getORGNID() < o.getORGNID())
				org = o;
		}
		return org;
	}
	
	public SocialGraphEligibility getSocialGraphEligibility(PersonInfoDTO user) {
		OrganizationInfoDTO org = this.getSocialGraphOrg(user);
		if(org == null) return SocialGraphEligibility.NOT_ORG_ADMIN;
		if(org.getSocialGraphSignature() == null 
	    ||!org.getSocialGraphSignature().isEmpty()) 
			return SocialGraphEligibility.ALREADY_SIGNED;
		return SocialGraphEligibility.ELIGIBLE;
	}
	
	
	
	public void signSocialGraphContract(PersonInfoDTO user, OrganizationInfoDTO o) {
		int nid = getSocialGraphOrg(user).getORGNID();
		if(!(o.getSocialGraphIAgreeDataUsage() > 0) 
		|| !(o.getSocialGraphIAgreeCaching() > 0)
		|| !(o.getSocialGraphIAgreeLogo() > 0) 
		|| !(o.getSocialGraphIAgreeFinal() > 0)) {
			o.appendErrorMsg("You must agree to all of the terms and conditions. ");
		}
		if(o.getSocialGraphExplanation() == null 
	    || o.getSocialGraphExplanation().isEmpty()) {
			o.appendErrorMsg("You must provide an explanation of why you want to use the Social Graph API. ");
		}
		if(o.getSocialGraphSignature() == null
		|| o.getSocialGraphSignature().isEmpty()) {
			o.appendErrorMsg("You must provide a digital signature. ");
		}
		if(o.getErrorMsg() == null 
	    || o.getErrorMsg().isEmpty()) {
	    	if(new OrganizationDBDAO().signSocialGraphContract(getDBConn(), user, o, nid) != 0)
	    		o.appendErrorMsg("There was an error with our system. Please try again later. ");
		}
	}
	
	/**
	* edit Opportunity record
	*/
	public int editOpportunity( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aHeadObj, String aszSiteVersion, String documentRoot){
		return editOpportunity(aPersObj, aOrgObj, aHeadObj, "", aszSiteVersion, documentRoot);
	}
	public int editOpportunity( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aHeadObj, String aszToken, String aszSiteVersion, String documentRoot){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("editOpportunity");

    	if(aszToken==AppSessionDTO.TOKEN_CREATEOPPORTDISASTER){
    		// might have less requirements
    	}
    	// make sure to pass some of the free-text stuff that might have been copied from MS word or something through the clean input
    	aHeadObj.setOPPTitle(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPTitle(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setOPPRequirements(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPRequirements(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setOPPDescription(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPDescription(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	
    	aszTemp = aHeadObj.getOPPTitle();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Position Title Required  \r\n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getOPPDescription();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Position Description Required  \r\n");
    		bMinOK=false;
    	}
    	/*
    	aszTemp = aHeadObj.getOPPCategories();
    	iTemp = aHeadObj.getOPPServiceArea1TID();
    	if( (aszTemp.length() < 3) && iTemp < 1 && aHeadObj.getOPPPositionTypeTID() != 33389){ // jobs don't require Service Areas
    		aHeadObj.appendErrorMsg("- Opportunity Service Area Required  \r\n");
    		bMinOK=false;
    	}
    	*/
    	aszTemp = aHeadObj.getOPPRequiredCodeType();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must indicate whether you require that volunteers/applicants for this position be Christian  \r\n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getOPPStatus();
    	iTemp = aHeadObj.getOPPPositionTypeTID();
    	if( (aszTemp.length() < 3) && iTemp < 1){
    		aHeadObj.appendErrorMsg("- Please indicate the Position Type opportunity\r\n");
    		bMinOK=false;
    	}
    	if(		iTemp==4794 || // Local
    			iTemp==4796 || // STM
    			iTemp==100){// Local or Virtual; ie. it requires an address
        	aszTemp = aHeadObj.getOPPAddrLine1();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- Street Address required for non-Virtual Positions  \r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrCity();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- City required for non-Virtual Positions  \r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrPostalcode();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- Postal Code Required for non-Virtual Positions" +
        				"\n     (if your position does not have a postal code, please enter \"no postal\" in the postal code box)\r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Country Required for non-Virtual Opportunities  \r\n");
        		bMinOK=false;
        	}
    	}
    	if(	iTemp==4796 ){// if STM, additionally require trip length to be filled out
        	iTemp = aHeadObj.getOPPTripLengthTID();
        	if( iTemp < 1){
        		aHeadObj.appendErrorMsg("- Please indicate the length of this trip\r\n");
        		bMinOK=false;
        	}
    	}
    	if(aHeadObj.getQuestionnaireType() != null && 
    	aHeadObj.getQuestionnaireType().equals("on_paper") && 
        (aHeadObj.getQuestionnaireServerFile() == null && (aHeadObj.getQuestionnaireClientFile() == null || aHeadObj.getQuestionnaireClientFile().getFileName().length() == 0))) {
    		aHeadObj.appendErrorMsg(" - Please select an application file to upload\r\n");
    		bMinOK = false;
    	} 
    	if(aHeadObj.getQuestionnaireClientFile() != null &&
        aHeadObj.getQuestionnaireClientFile().getFileName().length() > 0 &&
    	!Arrays.asList(new String[] {
    		"application/pdf",
    		"application/msword",
    		"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    		"text/plain",
    		"application/rtf",
    		"text/rtf"
    	}).contains(aHeadObj.getQuestionnaireClientFile().getContentType())) {
    		aHeadObj.appendErrorMsg("- File type must be .pdf, .doc, .docx, .txt or .rtf\r\n");
    		bMinOK = false;
    	}
    	if(aHeadObj.getQuestionnaireClientFile() != null &&
    	aHeadObj.getQuestionnaireClientFile().getFileName().length() > 0 &&
    	aHeadObj.getQuestionnaireClientFile().getFileSize() > 1024000) {
    	    aHeadObj.appendErrorMsg("- File is too large. File size must not exceed 1MB\r\n");
    	    bMinOK = false;
    	}
    	if(aHeadObj.getRequiredDocumentsToAdd() != null) {
    		for(RequiredDocumentDTO doc : aHeadObj.getRequiredDocumentsToAdd()) {
    			if(doc.getClientFile() == null) {
    				aHeadObj.appendErrorMsg("- Please select a file to upload\r\n");
    				bMinOK = false;
    			}
    			if(doc.getName().length() == 0) {
    				aHeadObj.appendErrorMsg("- Please enter a name for the document\r\n");
    				bMinOK = false;
    			}
    			if(doc.getClientFile() != null &&
    			doc.getClientFile().getFileName().length() > 0 &&
    			!Arrays.asList(new String[] {
    		    		"application/pdf",
    		    		"application/msword",
    		    		"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    		    		"text/plain",
    		    		"application/rtf",
    		    		"text/rtf"
    		    }).contains(doc.getClientFile().getContentType())) {
    	    		aHeadObj.appendErrorMsg("- File type must be .pdf, .doc, .docx, .txt or .rtf\r\n");
    	    		bMinOK = false;
    			}
    			if(doc.getClientFile() != null &&
    			doc.getClientFile().getFileName().length() > 0 &&
    			doc.getClientFile().getFileSize() > 1024000) {
    	    	    aHeadObj.appendErrorMsg("- File is too large. File size must not exceed 1MB\r\n");
    	    		bMinOK = false;
    			}
    		}
    		
    	}
    	
    	if(false == bMinOK){
    		return -22;
    	}
    	//aHeadObj.setOPPNID( aOrgObj.getORGNID() );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	//aHeadObj.setORGNID( aOrgObj.getORGNID() ); //commented out b/c it's already set to this
    	aHeadObj.setORGOrgName( aOrgObj.getORGOrgName() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGWebpage( aOrgObj.getORGWebpage() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGNumVolOrg( aOrgObj.getORGNumVolOrg() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGOnethirdP( aOrgObj.getORGOnethirdP() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGAffiliation( aOrgObj.getORGAffiliation() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner( aOrgObj.getORGPartner() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner2( aOrgObj.getORGPartner2() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner3( aOrgObj.getORGPartner3() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner4( aOrgObj.getORGPartner4() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner5( aOrgObj.getORGPartner5() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGMembertype( aOrgObj.getORGMembertype() ); // added to the opp so that less joins are not required for opp searches

    	aHeadObj.setOPPUID( aOrgObj.getORGUID() ); // allows creates the new opportunity with the primary owner of the parent org
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.editOpportunity(pConn, aHeadObj, getRailsDBBySiteVersion(aszSiteVersion), documentRoot);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method editOpportunity()

	/**
	* create Opportunity record
	*/
	public int createOpportunity( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aHeadObj, String aszSiteVersion, String documentRoot ){
		return createOpportunity( aPersObj, aOrgObj, aHeadObj, "", getRailsDBBySiteVersion(aszSiteVersion), documentRoot );
	}
	public int createOpportunity( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aHeadObj, String aszToken, String aszSiteVersion, String documentRoot ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null, aszURLAlias=null ;
    	boolean bMinOK=true;
    	MethodInit("createOpportunity");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);

    	if(aszToken==AppSessionDTO.TOKEN_CREATEOPPORTDISASTER){
    		// might have less requirements
    	}

    	// make sure to pass some of the free-text stuff that might have been copied from MS word or something through the clean input
    	aHeadObj.setOPPTitle(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPTitle(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setOPPRequirements(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPRequirements(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setOPPDescription(m_AppCodesBRLOObj.cleanInput(aHeadObj.getOPPDescription(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getOPPSubdom().length()<1){
    		aHeadObj.setOPPSubdom(aszDomMain);
    	}

    	aszTemp = aHeadObj.getOPPTitle();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Position Title Required  \r\n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getOPPDescription();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Position Description Required  \r\n");
    		bMinOK=false;
    	}
    	/* - taken out as a required field 5-7-12
    	// 2008-04-24 - add Service Area to now be a required field (at least one)
    	aszTemp = aHeadObj.getOPPCategories();
    	iTemp = aHeadObj.getOPPServiceArea1TID();
    	if( (aszTemp.length() < 3) && iTemp < 1 && aHeadObj.getOPPPositionTypeTID() != 33389){ // jobs don't require Service Areas
    		aHeadObj.appendErrorMsg("- Opportunity Service Area Required  \r\n");
    		bMinOK=false;
    	}
    	*/
    	// limit size of user-entered aHeadObj.getServiceAreasArray() to be no more than 5, to help accuracy of search results
    	if(aHeadObj.getServiceAreasArray().length>5){
    		aHeadObj.appendErrorMsg("- Please limit your Service Area Tags to 5 or less to help improve accuracy of search results.  \r\n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getOPPRequiredCodeType();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must indicate whether you require that volunteers/applicants for this position be Christian  \r\n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getOPPStatus();
    	iTemp = aHeadObj.getOPPPositionTypeTID();
    	if( (aszTemp.length() < 3) && iTemp < 1){
    		aHeadObj.appendErrorMsg("- Please indicate the Position Type \r\n");
    		bMinOK=false;
    	}
    	if(	(	iTemp==4794 || // Local
    			iTemp==4796 || // STM
    			iTemp==100
    	)&& aHeadObj.getORGNID()!=iDisasterReliefOrgNID){// Local or Virtual; ie. it requires an address
        	aszTemp = aHeadObj.getOPPAddrLine1();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- Street Address required for non-Virtual Positions \r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrCity();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- City required for non-Virtual Positions  \r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrPostalcode();
        	if(aszTemp.length() < 3){
        		aHeadObj.appendErrorMsg("- Postal Code Required for non-Virtual Positions  " +
        				"\n     (if your position does not have a postal code, please enter \"no postal\" in the postal code box)\r\n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getOPPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Country Required for non-Virtual Positions  \r\n");
        		bMinOK=false;
        	}
    	}
    	if(	iTemp==4796 ){// if STM , additionally require trip length to be filled out 
        	iTemp = aHeadObj.getOPPTripLengthTID();
        	if( iTemp < 1){
        		aHeadObj.appendErrorMsg("- Please indicate the length of this trip\r\n");
        		bMinOK=false;
        	}
    	}
    	if(aHeadObj.getQuestionnaireClientFile() != null &&
    	aHeadObj.getQuestionnaireClientFile().getFileName().length() > 0 &&
    	!Arrays.asList(new String[] {
    	    "application/pdf",
    	    "application/msword",
    	    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    	    "text/plain",
    	    "application/rtf",
    	    "text/rtf"
    	}).contains(aHeadObj.getQuestionnaireClientFile().getContentType())) {
    	    aHeadObj.appendErrorMsg("- File type must be .pdf, .doc, .docx, .txt or .rtf\r\n");
    	    bMinOK = false;
    	}
    	if(aHeadObj.getQuestionnaireClientFile() != null &&
    	aHeadObj.getQuestionnaireClientFile().getFileName().length() > 0 &&
    	aHeadObj.getQuestionnaireClientFile().getFileSize() > 1024000) {
    	    aHeadObj.appendErrorMsg("- File is too large. File size must not exceed 1MB");
    	    bMinOK = false;
    	}
    	if(aHeadObj.getQuestionnaireType() != null && 
    	aHeadObj.getQuestionnaireType().equals("on_paper") &&
    	(aHeadObj.getQuestionnaireServerFile() == null && (aHeadObj.getQuestionnaireClientFile() == null || aHeadObj.getQuestionnaireClientFile().getFileName().length() == 0))) {
    	    aHeadObj.appendErrorMsg(" - Please select an application file to upload");
    	    bMinOK = false;
    	}
    	if(aHeadObj.getRequiredDocumentsToAdd() != null) {
    		for(RequiredDocumentDTO doc : aHeadObj.getRequiredDocumentsToAdd()) {
    			if(doc.getClientFile() == null) {
    				aHeadObj.appendErrorMsg("- Please select a file to upload\r\n");
    				bMinOK = false;
    			}
    			if(doc.getName().length() == 0) {
    				aHeadObj.appendErrorMsg("- Please enter a name for the document\r\n");
    				bMinOK = false;
    			}
    			if(doc.getClientFile() != null &&
    			doc.getClientFile().getFileName().length() > 0 &&
    			!Arrays.asList(new String[] {
    		    		"application/pdf",
    		    		"application/msword",
    		    		"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    		    		"text/plain",
    		    		"application/rtf",
    		    		"text/rtf"
    		    }).contains(doc.getClientFile().getContentType())) {
    	    		aHeadObj.appendErrorMsg("- File type must be .pdf, .doc, .docx, .txt or .rtf\r\n");
    	    		bMinOK = false;
    			}
    			if(doc.getClientFile() != null &&
    			doc.getClientFile().getFileName().length() > 0 &&
    			doc.getClientFile().getFileSize() > 1024000) {
    	    	    aHeadObj.appendErrorMsg("- File is too large. File size must not exceed 1MB");
    	    		bMinOK = false;
    			}
    		}
    		
    	}

    	if(false == bMinOK){
    		return -22;
    	}

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aHeadObj.setORGOrgName( aOrgObj.getORGOrgName() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGWebpage( aOrgObj.getORGWebpage() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGNumVolOrg( aOrgObj.getORGNumVolOrg() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGOnethirdP( aOrgObj.getORGOnethirdP() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGAffiliation( aOrgObj.getORGAffiliation() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner( aOrgObj.getORGPartner() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner2( aOrgObj.getORGPartner2() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner3( aOrgObj.getORGPartner3() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner4( aOrgObj.getORGPartner4() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGPartner5( aOrgObj.getORGPartner5() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGMembertype( aOrgObj.getORGMembertype() ); // added to the opp so that less joins are not required for opp searches
    	aHeadObj.setORGurlID( aOrgObj.getORGurlID() );
    	aHeadObj.setORGNID( aOrgObj.getORGNID() );
    	aHeadObj.setOPPProgNumber( 0 );

    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.createOpportunity(pConn, aHeadObj, getRailsDBBySiteVersion(aszSiteVersion), documentRoot );
    	
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method createOpportunity()


    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>


    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>

	
    /**
	* get organization from organizationinfo table
	*/
	public int loadOrganizationFromDB( OrganizationInfoDTO aHeadObj, int iType, String aszSiteVersion ){
        String aszURLAliasFormat = "org/"; // aOrgInfoObj.getPathAutoOppPattern();
    	return loadOrganizationFromDB(  aHeadObj, iType, aszURLAliasFormat, aszSiteVersion );
	}
	public int loadOrganizationFromDB( OrganizationInfoDTO aHeadObj, int iType, String aszURLAliasFormat, String aszSiteVersion ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	String aszAssoc = aOrganizationObj.getNatlAssociation(pConn, aHeadObj.getNatlAssociationNID());
    	if(aszAssoc==null) 	aszAssoc="";
    	aHeadObj.setNatlAssociation(aszAssoc);
    	iRetCode = aOrganizationObj.loadOrganizationFromDB(pConn, aHeadObj, getRailsDBBySiteVersion(aszSiteVersion), iType, aszURLAliasFormat );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadOrganizationFromDB()

	
    /**
	* get organization details from organizationinfo table
	*/
	public int loadOrganizationDetFromDB(  OrganizationDetailsInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadOrganizationDetFromDB(pConn, aHeadObj, iType );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadOrganizationDetFromDB()

	
	/**
	* create Organization record
	*/
	public int createOrganization( OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iTemp;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null;
    	boolean bMinOK=true;
    	MethodInit("createOrganization");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);

    	// make sure to pass some of the free-text stuff that might have been copied from MS word or something through the clean input
    	aHeadObj.setORGOrgName(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGOrgName(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setORGMissionStatement(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGMissionStatement(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setORGOrgDescription(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGOrgDescription(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getORGSubdom().length()<1){
    		aHeadObj.setORGSubdom(aszDomMain);
    	}
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Organization Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCountryName();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Country Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrPostalcode();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Postal Code Required \n");
    		bMinOK=false;
    	}
    	
    	aszTemp = aHeadObj.getORGMembertype();
    	iTemp = aHeadObj.getORGMembertypeTID();
    	if(aszTemp.length() < 1 && iTemp < 1){
    		aHeadObj.appendErrorMsg("- Membership Type required \n");
    		bMinOK=false;
    	}
    	/*
    	if(aHeadObj.getTypesOfOppsArray().length < 1){
    		aHeadObj.appendErrorMsg("- Primary Types of Opportunities required \n");
    		bMinOK=false;
    	}
}else{
    		int[] a_iTypesOfOppsArray = aHeadObj.getTypesOfOppsArray();
			for(int indexArray=0; indexArray < a_iTypesOfOppsArray.length; indexArray++){
				iTemp = a_iTypesOfOppsArray[indexArray];
				if(iTemp==iSendHostIntlTID){
		    		int iTID=aHeadObj.getORGTakesIntlVolsTID();
		    		if(iTID != iTakesIntlVolsTID){
			    		aHeadObj.appendErrorMsg("- 'Sending/Hosting International Missionaries' Must Allow Visas \n");
			    		bMinOK=false;
		    		}
				}
			}
    	}
*/
/*
    	aszTemp = aHeadObj.getORGOrgContactEmail();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Organizational Email required,  ");
    		bMinOK=false;
    	}
    	*/
    	aszTemp = aHeadObj.getORGAddrLine1();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Street Address required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCity();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- City required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGOrgPhone1();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Phone required \n");
    		bMinOK=false;
    	}
        if(!this.einValidation(aHeadObj)) {
      	   aHeadObj.appendErrorMsg("- Employee Identification Number is Invalid.");
      	   bMinOK=false;
        }
    	aszTemp = aHeadObj.getORGMissionStatement();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Mission Statement required \n");
    		bMinOK=false;
    	}
    	if(aHeadObj.getUsePortal().equalsIgnoreCase("Yes")){
	    	aszTemp = aHeadObj.getPortalNameModified();
	    	if( (aszTemp.length() < 2) ){
	    		aHeadObj.appendErrorMsg("- Directory Name for your Portal required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp="";
    	}
    	if(aHeadObj.getCurrentDomain().equalsIgnoreCase("churchvol")|| aHeadObj.getORGMembertype().equalsIgnoreCase("Church") ){
    		// not required fields for churchvolunteering; still need to propagate response for int'l vols
    		aHeadObj.setORGTakesIntlVolsTID(iDoesNotTakeIntlVolsTID);

	    	aszTemp = aHeadObj.getORGProgramTypes();
	    	iTemp = aHeadObj.getORGProgramTypes1TID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.setORGProgramTypes1TID(iProgTypeChurchOutreachTID);
	    	}

    		// in this case, Denominational Affiliation IS a required field
	    	aszTemp = aHeadObj.getORGDenomAffil();
	    	iTemp = aHeadObj.getORGDenomAffiliationTID();
	    	if( (aszTemp.length() < 3) && iTemp < 1 && aHeadObj.getDenominationalAffilsArray().length==0){
	    		aHeadObj.appendErrorMsg("- Denominational Affiliation required \n");
	    		bMinOK=false;
	    	}
	    	iTemp=0;
    	}else{
/*    		
	    	aszTemp = aHeadObj.getORGProgramTypes();
	    	iTemp = aHeadObj.getORGProgramTypes1TID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.appendErrorMsg("- Program Type required \n");
	    		bMinOK=false;
	    	}
*/	    	
	    	iTemp = aHeadObj.getORGNumVolOrg();
	    	if(iTemp<0){
	    		aHeadObj.appendErrorMsg("- Number of Volunteers Per Year required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getORGOnethirdP();
	    	if(aszTemp.length() < 1){
	    		aHeadObj.appendErrorMsg("- Low Income response required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getORGTakesIntlVols();
	    	iTemp = aHeadObj.getORGTakesIntlVolsTID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.appendErrorMsg("- You must indicate whether you will assist International Volunteers with Visas, etc \n");
	    		bMinOK=false;
	    	}
	    	iTemp=0;
		}
    	/*
    	// validate email address format
    	aszTemp = aHeadObj.getORGOrgContactEmail();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("Email Format Error / Invalid Email Address");
    		return -22;
    	}
    	*/

    	if(false == bMinOK) return -22;

    	aHeadObj.setPortalHeaderTags(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeaderTags(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalHeader(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeader(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalCSS(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalCSS(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalFooter(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalFooter(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.IsOrganizationInSystem(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	if(0 == iRetCode){
    		setErr("Organization " + aHeadObj.getORGOrgName() + " in " + aHeadObj.getORGAddrCountryName() + ", " + 
    				aHeadObj.getORGAddrPostalcode() + " already exists in system. ");
    		return -1;
    	}
    	// make sure that the Webpage is entered with http:// for drupalized links
    	aszTemp = aHeadObj.getORGWebpage();
    	if(aszTemp.length() > 5 ){
        	String aszTemp2=aszTemp.substring(0,5);
        	if (! aszTemp2.equalsIgnoreCase("http:") ){
        		aHeadObj.setORGWebpage("http://" + aHeadObj.getORGWebpage());        		
        	}
    	}

    	// make sure that the Donate URL is entered with http:// for drupalized links
    	aszTemp = aHeadObj.getORGDonateURL();
    	if (aszTemp.length() > 5){
        	String aszTemp2 =aszTemp.substring(0,5);
        	if( !(
        			(aszTemp2.equalsIgnoreCase("http:")) ||
        			(aszTemp2.equalsIgnoreCase("https")) 
        		)){
        		aHeadObj.setORGDonateURL("http://" + aszTemp);
        	}
    	}

    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.createOrganization(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method createOrganization()


	/**
	* edit Organization record
	*/
	public int updateOrganization( OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateOrganization");

    	// make sure to pass some of the free-text stuff that might have been copied from MS word or something through the clean input
    	aHeadObj.setORGOrgName(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGOrgName(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setORGMissionStatement(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGMissionStatement(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	aHeadObj.setORGOrgDescription(m_AppCodesBRLOObj.cleanInput(aHeadObj.getORGOrgDescription(),m_AppCodesBRLOObj.LOOSELY_CLEANED));
    	
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Organization Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCountryName();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Country Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrPostalcode();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Postal Code Required \n");
    		bMinOK=false;
    	}
    	
    	iTemp = aHeadObj.getORGMembertypeTID();
    	if(aszTemp.length() < 1 && iTemp < 1){
    		aHeadObj.appendErrorMsg("- Membership Type required \n");
    		bMinOK=false;
    	}
    	/*    	
    	if(aHeadObj.getTypesOfOppsArray().length < 1){
    		aHeadObj.appendErrorMsg("- Primary Types of Opportunities required \n");
    		bMinOK=false;
    	}
    	else{
    		int[] a_iTypesOfOppsArray = aHeadObj.getTypesOfOppsArray();
			for(int indexArray=0; indexArray < a_iTypesOfOppsArray.length; indexArray++){
				iTemp = a_iTypesOfOppsArray[indexArray];
				if(iTemp==iSendHostIntlTID){
		    		int iTID=aHeadObj.getORGTakesIntlVolsTID();
		    		if(iTID != iTakesIntlVolsTID){
//			    		aHeadObj.appendErrorMsg("- 'Sending/Hosting International Missionaries' Must Allow Visas and Have Opportunities in Another Country \n");
			    		aHeadObj.appendErrorMsg("- 'Sending/Hosting International Missionaries' Must Allow Visas \n");
			    		bMinOK=false;
		    		}
				}
			}
    	}
    	/*
    	aszTemp = aHeadObj.getORGOrgContactEmail();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Organizational Email required,  ");
    		bMinOK=false;
    	}
    	*/
    	aszTemp = aHeadObj.getORGAddrLine1();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Street Address required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCity();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- City required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGOrgPhone1();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Phone required \n");
    		bMinOK=false;
    	}
    	
        if(!this.einValidation(aHeadObj)) {
     	   aHeadObj.appendErrorMsg("- Employee Identification Number is Invalid.");
     	   bMinOK=false;
        }
    	
    	aszTemp = aHeadObj.getORGMissionStatement();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Mission Statement required \n");
    		bMinOK=false;
    	}
    	if(aHeadObj.getUsePortal().equalsIgnoreCase("Yes")){
    		// in this case, Denominational Affiliation IS a required field
	    	aszTemp = aHeadObj.getPortalNameModified();
	    	if( (aszTemp.length() < 2) ){
	    		aHeadObj.appendErrorMsg("- Directory Name for your Portal required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp="";
    	}
    	if(aHeadObj.getCurrentDomain().equalsIgnoreCase("churchvol")|| aHeadObj.getORGMembertype().equalsIgnoreCase("Church") ){
    		// not required fields for churchvolunteering; still need to propagate response for int'l vols
    		aHeadObj.setORGTakesIntlVolsTID(iDoesNotTakeIntlVolsTID);
	    	aszTemp = aHeadObj.getORGProgramTypes();
	    	iTemp = aHeadObj.getORGProgramTypes1TID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.setORGProgramTypes1TID(iProgTypeChurchOutreachTID);
	    	}
	    	
    		// in this case, Denominational Affiliation IS a required field
	    	aszTemp = aHeadObj.getORGDenomAffil();
	    	iTemp = aHeadObj.getORGDenomAffiliationTID();
	    	if( (aszTemp.length() < 3) && iTemp < 1 && aHeadObj.getDenominationalAffilsArray().length==0){
	    		aHeadObj.appendErrorMsg("- Denominational Affiliation required \n");
	    		bMinOK=false;
	    	}
	    	iTemp=0;
    	}else{
/*	    	
    		aszTemp = aHeadObj.getORGProgramTypes();
	    	iTemp = aHeadObj.getORGProgramTypes1TID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.appendErrorMsg("- Program Type required \n");
	    		bMinOK=false;
	    	}
	    	iTemp=0;
*/
	    	iTemp = aHeadObj.getORGNumVolOrg();
	    	if(iTemp<0){
	    		aHeadObj.appendErrorMsg("- Number of Volunteers Per Year required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getORGOnethirdP();
	    	if(aszTemp.length() < 1){
	    		aHeadObj.appendErrorMsg("- Low Income response required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getORGTakesIntlVols();
	    	iTemp = aHeadObj.getORGTakesIntlVolsTID();
	    	if( (aszTemp.length() < 3) && iTemp < 1){
	    		aHeadObj.appendErrorMsg("- You must indicate whether you will assist International Volunteers with Visas, etc \n");
	    		bMinOK=false;
	    	}
	    	iTemp=0;
		}
    	/*
    	// validate email address format
    	aszTemp = aHeadObj.getORGOrgContactEmail();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("Email Format Error / Invalid Email Address");
    		return -22;
    	}
    	*/

    	if(false == bMinOK) return -22;

    	aHeadObj.setPortalHeaderTags(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeaderTags(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalHeader(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeader(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalCSS(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalCSS(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	aHeadObj.setPortalFooter(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalFooter(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));

    	// make sure that the Webpage is entered with http:// for drupalized links
    	aszTemp = aHeadObj.getORGWebpage();
    	if (aszTemp.length() > 5){
        	String aszTemp2 =aszTemp.substring(0,5);
        	if( !(aszTemp2.equalsIgnoreCase("http:")) ){
        		aHeadObj.setORGWebpage("http://" + aszTemp);
        	}
    	}

    	// make sure that the Donate URL is entered with http:// for drupalized links
    	aszTemp = aHeadObj.getORGDonateURL();
    	if (aszTemp.length() > 5){
        	String aszTemp2 =aszTemp.substring(0,5);
        	if( !(
        			(aszTemp2.equalsIgnoreCase("http:")) ||
        			(aszTemp2.equalsIgnoreCase("https")) 
        		)){
        		aHeadObj.setORGDonateURL("http://" + aszTemp);
        	}
    	}

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.editOrganization(pConn, aHeadObj );
//    	if( iType != OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC ){
    	if(aHeadObj.getPortalNameModified().length()>0){
    		int iRetCode2 = aOrganizationObj.setFavoritesPortal(pConn, aHeadObj );
    	}
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method updateOrganization()


	/**
	* removeAssociation
	*/
	public int removeAssociation( OrganizationInfoDTO aHeadObj, int iNatlAssocNID, ArrayList aList ){
		int iRetCode=-1;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	if(iNatlAssocNID < 1) return -1;
    	MethodInit("removeAssociation");

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	// lookup the actual term for the national affiliation - words
    	String aszAssoc = aOrganizationObj.getNatlAssociation(pConn, iNatlAssocNID);
    	
    	if(aszAssoc != null){
    		iRetCode = aOrganizationObj.removeAssociation(pConn, aHeadObj, aszAssoc, aList );
    	}
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method removeAssociation()



	/**
	* edit Organization record
	*/
	public int updatePortal( OrganizationInfoDTO aHeadObj ){
		return updatePortal( aHeadObj, 0);
	}
	public int updatePortal( OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null;
    	boolean bMinOK=true;
    	MethodInit("updatePortal");

    	// in this case, Denominational Affiliation IS a required field
	    aszTemp = aHeadObj.getPortalNameModified();
	    if( (aszTemp.length() < 2) ){
	    	aHeadObj.appendErrorMsg("- Directory Name for your Portal required \n");
	    	bMinOK=false;
	    }
	    aszTemp="";
		
    	if(false == bMinOK) return -22;

    	// if the current user is National Association manager, bypass the cleanInput 2012-01-19
    	if( iType != OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC ){
	    	aHeadObj.setPortalHeaderTags(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeaderTags(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
	    	aHeadObj.setPortalHeader(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalHeader(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
	    	aHeadObj.setPortalCSS(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalCSS(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
	    	aHeadObj.setPortalFooter(m_AppCodesBRLOObj.cleanInput(aHeadObj.getPortalFooter(),m_AppCodesBRLOObj.TIGHTLY_CLEANED));
    	}

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.editPortal(pConn, aHeadObj, iType );
    	if( iType != OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC ){
    		int iRetCode2 = aOrganizationObj.setFavoritesPortal(pConn, aHeadObj );
    	}
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method updatePortal()

	
    /**
	* loadChildOpps for an organization
	*/
	public int loadChildOpps( OrganizationInfoDTO aOrgInfoObj ){
		AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
		return loadChildOpps(aOrgInfoObj, aHeadObj);
	}
	public int loadChildOpps( OrganizationInfoDTO aOrgInfoObj, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(aOrgInfoObj == null) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadChildOpps(pConn, aOrgInfoObj, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadChildOpps()
	


	
    /**
	* loadFavoriteOpps for an organization
	*/
	public int loadFavoriteOpps( OrganizationInfoDTO aHeadObj, boolean feeds ){
		int iRetCode=-1;
		ABREDBConnection pConn=null;
    	if(aHeadObj == null) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadFavoriteOpps(pConn, aHeadObj, feeds );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadFavoriteOpps()
	
    /**
	* loadFavoriteOrgs for an organization
	*/
	public int loadFavoriteOrgs( OrganizationInfoDTO aHeadObj ){
		int iType=0;
		return loadFavoriteOrgs(aHeadObj, iType);
	}
	public int loadFavoriteOrgs( OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(aHeadObj == null) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.loadFavoriteOrgs(pConn, aHeadObj, iType );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method loadFavoriteOrgs()
	



	
    /**
	* favoriteOpps for an organization
	*/
	public int favoriteOpps( OrganizationInfoDTO aOrgInfoObj ){
		int iType=0;
        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
		return favoriteOpps(aOrgInfoObj, iType, aHeadObj);
	}
	public int favoriteOpps( OrganizationInfoDTO aOrgInfoObj, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(aHeadObj == null) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.favoriteOpps(pConn, aOrgInfoObj, iType, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method favoriteOpps()
	
    /**
	* favoriteOrgs for an organization
	*/
	public int favoriteOrgs( OrganizationInfoDTO aOrgInfoObj ){
		int iType=0;
        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
		return favoriteOrgs(aOrgInfoObj, iType, aHeadObj);
	}
	public int favoriteOrgs( OrganizationInfoDTO aOrgInfoObj, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(aHeadObj == null) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.favoriteOrgs(pConn, aOrgInfoObj, iType, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method favoriteOrgs()
	
	
    /**
	* setNatlAssocOrgsAndOpps
	*/
	public int setNatlAssocOrgsAndOpps( OrganizationInfoDTO aOrgInfoObj, int iOrgAffilTID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		if(aOrgInfoObj==null) return -1;
    	if(iOrgAffilTID == 0) return -1;
    	
    	AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
    	
    	int iAddTemp=0, iRemoveTemp=0, iAddIndex=0, iRemoveIndex=0, count=0;
		int[] iNewOrgNids = aOrgInfoObj.getORGFavoritedORGNIDsArray();
		int[] iPrevOrgNids = aOrgInfoObj.getORGPrevFavoritedORGNIDsArray();

		int[] iTempAddOrgNids = new int[1000];
		int[] iTempRemoveOrgNids = new int[1000];
		int[] iTempAddOppNids = new int[1000];
		int[] iTempRemoveOppNids = new int[1000];

    	for(int i=0; i < iNewOrgNids.length; i++){
    		iAddTemp=0;
    		int value = iNewOrgNids[i];
    		for(int j=0; j < iPrevOrgNids.length; j++){
    			if(iPrevOrgNids[j]==value){
    				iAddTemp=-1;
    			}
    		}
    		if(iAddTemp == 0){// exists in New, but not in Previous; add it
    			iTempAddOrgNids[iAddIndex]=value;
    			iAddIndex++;
    		}
    	}
		int[] iAddOrgNids = new int[iAddIndex];
		for(int i=0; i<iAddIndex; i++){
			try{
				iAddOrgNids[i]=iTempAddOrgNids[i];
			}catch(ArrayIndexOutOfBoundsException e){}
		}
    	aHeadObj.setAddFavORGNIDsArray(iAddOrgNids);
    	
    	for(int i=0; i < iPrevOrgNids.length; i++){
    		iRemoveTemp=0;
    		int value = iPrevOrgNids[i];
    		for(int j=0; j < iNewOrgNids.length; j++){
    			if(iNewOrgNids[j]==value){
    				iRemoveTemp=-1;
    			}
    		}
    		if(iRemoveTemp == 0){// exists in Previous, but not in New; remove it
    			iTempRemoveOrgNids[iRemoveIndex]=value;
    			iRemoveIndex++;
    		}
    	}
    	
		int[] iRemoveOrgNids = new int[iRemoveIndex];
		for(int i=0; i<iRemoveIndex; i++){
			try{
				iRemoveOrgNids[i]=iTempRemoveOrgNids[i];
			}catch(ArrayIndexOutOfBoundsException e){}
		}
    	aHeadObj.setRemoveFavORGNIDsArray(iRemoveOrgNids);
    	// child opps for REMOVING nids
    	count=0;
    	for(int i=0; i<iRemoveIndex; i++){
    		//iterate through all the remove org nids and get lists of each orgs' opps
   	    	int iAssocOrgNID = iRemoveOrgNids[i];
   	    	if(iAssocOrgNID>0){
    	        ArrayList aAssocOrgOppList = new ArrayList();
				iRetCode = getOppListForOrg( aAssocOrgOppList, iAssocOrgNID, OrganizationInfoDTO.LOADBY_ORGNID_MANAGE);
				Iterator<OrgOpportunityInfoDTO> itr_aAssocOrgOppList = aAssocOrgOppList.iterator();
   	    	    while(itr_aAssocOrgOppList.hasNext()){
   	    	    	OrgOpportunityInfoDTO orgElement_aAssocOrgOppList=new OrgOpportunityInfoDTO();
   	  	            orgElement_aAssocOrgOppList = itr_aAssocOrgOppList.next();
   	    	    	int iAssocOrgOppNID = orgElement_aAssocOrgOppList.getOPPNID();
   	    	    	if(iAssocOrgOppNID>0){
   	    	    		iTempRemoveOppNids[count] = orgElement_aAssocOrgOppList.getOPPNID();
       	  	            count++;
   	    	    	}
   	    	    }
   	    	}

    	}
    	int[] iRemoveOppNids = new int[count];
		for(int i=0; i<iRemoveIndex; i++){
			try{
				iRemoveOppNids[i]=iTempRemoveOppNids[i];
			}catch(ArrayIndexOutOfBoundsException e){}
		}
    	aHeadObj.setRemoveFavOPPNIDsArray(iRemoveOppNids);

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setNatlAssocOrgsAndOpps(pConn, aOrgInfoObj, iOrgAffilTID, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setNatlAssocOrgsAndOpps()

    /**
	* setNatlAssocOpps
	*/
	public int setNatlAssocOpps( OrganizationInfoDTO aOrgInfoObj, int iOrgAffilTID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		if(aOrgInfoObj==null) return -1;
    	if(iOrgAffilTID == 0) return -1;
    	
    	AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
    	
    	int iAddTemp=0, iRemoveTemp=0, iAddIndex=0, iRemoveIndex=0, count=0;
		int[] iNewOppNids = aOrgInfoObj.getORGFavoritedOPPNIDsArray();
		int[] iPrevOppNids = aOrgInfoObj.getORGPrevFavoritedOPPNIDsArray();

		int[] iTempAddOppNids = new int[1000];
		int[] iTempRemoveOppNids = new int[1000];

    	for(int i=0; i < iNewOppNids.length; i++){
    		iAddTemp=0;
    		int value = iNewOppNids[i];
    		for(int j=0; j < iPrevOppNids.length; j++){
    			if(iPrevOppNids[j]==value){
    				iAddTemp=-1;
    			}
    		}
    		if(iAddTemp == 0){// exists in New, but not in Previous; add it
    			iTempAddOppNids[iAddIndex]=value;
    			iAddIndex++;
    		}
    	}
		int[] iAddOppNids = new int[iAddIndex];
		for(int i=0; i<iAddIndex; i++){
			try{
				iAddOppNids[i]=iTempAddOppNids[i];
			}catch(ArrayIndexOutOfBoundsException e){}
		}
    	aHeadObj.setAddFavOPPNIDsArray(iAddOppNids);
    	
    	for(int i=0; i < iPrevOppNids.length; i++){
    		iRemoveTemp=0;
    		int value = iPrevOppNids[i];
    		for(int j=0; j < iNewOppNids.length; j++){
    			if(iNewOppNids[j]==value){
    				iRemoveTemp=-1;
    			}
    		}
    		if(iRemoveTemp == 0){// exists in Previous, but not in New; remove it
    			iTempRemoveOppNids[iRemoveIndex]=value;
    			iRemoveIndex++;
    		}
    	}
    	
		int[] iRemoveOppNids = new int[iRemoveIndex];
		for(int i=0; i<iRemoveIndex; i++){
			try{
				iRemoveOppNids[i]=iTempRemoveOppNids[i];
			}catch(ArrayIndexOutOfBoundsException e){}
		}
    	aHeadObj.setRemoveFavOPPNIDsArray(iRemoveOppNids);
    	
    	aHeadObj.setAddFavORGNIDsArray(new int[0]);
    	aHeadObj.setRemoveFavORGNIDsArray(new int[0]);

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setNatlAssocOrgsAndOpps(pConn, aOrgInfoObj, iOrgAffilTID, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setNatlAssocOpps()
	

    /**
	* does portal exist for any of these orgs?
	*/
	public String doesPortalExistForOrgs( ArrayList aList){
		String aszPortal="";
		ABREDBConnection pConn=null;
    	if(null == aList) return "";
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	aszPortal = aOrganizationObj.doesPortalExistForOrgs(pConn, aList );
    	if(null != pConn) pConn.free();
    	return aszPortal;
	}
	// end-of method doesPortalExistForOrgs()

	
    /**
	* get organization list for an admin
	*/
	public int getOrgListForMember( ArrayList aList, int iOrgNID){
		return getOrgListForMember( aList, iOrgNID, OrganizationInfoDTO.LOADBY_UID ); // default is to load by siteadmin
	}
	public int getOrgListForMember( ArrayList aList, int iMemberID, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iMemberID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setUID( iMemberID );
    	aSrchParmObj.setSearchType( iType );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrganizationList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgListForMember()
	public int getOrgListForAssociation( ArrayList aList, int iPortalNID, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iPortalNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setPortalNID( iPortalNID );
    	aSrchParmObj.setSearchType( iType );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrganizationList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgListForAssociation()

	
    /**
	* get organization list for a contact
	*/
	public int getOrgListForContact( ArrayList aList, int iMemberID){
    	return getOrgListForContact(aList, iMemberID, OrganizationInfoDTO.LOADBY_UID_CONTACT);
	}
	public int getOrgListForContact( ArrayList aList, int iMemberID, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iMemberID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setUID( iMemberID );
    	if(iType== OrganizationInfoDTO.LOADBY_NATL_ASSOC){
    		aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_NATL_ASSOC );
    	}else{
    		aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID_CONTACT );
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrganizationList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgListForContact()

	
    /**
	* get organization for an administrator
	*/
	public int getOrgListAdmin( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_SITEADMIN );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrganizationList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgListAdmin()
	
	
	
    //=== BEGIN OrgAdmins section
    //=== BEGIN OrgAdmins section
    //=== BEGIN OrgAdmins section
    /**
	* get contact list for an organization as an array of uids
	*/
	public int setOrgAdminListArray( OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgInfoObj) return -1;
    	int iOrgNID = aOrgInfoObj.getORGNID();
    	if(iOrgNID < 1) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setOrgAdminListArray(pConn, aOrgInfoObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setOrgAdminListArray()

	
    /**
	* get contact list for an organization
	*/
	public int getOrgAdminList( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrgAdminList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgAdminList()
	
    /**
	* add another organization administrator
	*/
	public int insertAdditionalOrgAdmin( OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("insertAdditionalOrgAdmin");
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.insertAddOrgAdmin(pConn, aHeadObj, aIndivObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method insertAdditionalOrgAdmin()

	/**
	* delete Org admin record
	*/
	public int deleteOrgAdmin( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	MethodInit("deleteOrgAdmin");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aOrgObj.setORGNID( aOrgObj.getORGNID() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOrgAdmin(pConn, aPersObj,aOrgObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteOrgAdmin()
	
    //=== END OrgAdmins section
    //=== END OrgAdmins section
    //=== END OrgAdmins section

	
	
    //=== BEGIN OrgContacts section
    //=== BEGIN OrgContacts section
    //=== BEGIN OrgContacts section
    /**
	* get contact list for an organization
	*/
	public int getOrgContactList( ArrayList aList, int iNID, String aszUseCase){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrgContactList(pConn, aList, aSrchParmObj, aszUseCase );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgContactList()

	
    /**
	* get contact list for an organization, referencing a given opp (like for editing opp contacts)
	*/
	public int getOrgContactList( ArrayList aList, OrgOpportunityInfoDTO aOpportObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(aOpportObj==null) return -1;
    	int iOrgNID = aOpportObj.getORGNID();
    	int iOppNID = aOpportObj.getOPPNID();
    	if(iOrgNID < 1) return -1;
    	if(iOppNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setORGNID( iOrgNID );
    	aSrchParmObj.setOPPNID( iOppNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrgContactList(pConn, aList, aSrchParmObj,null );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgContactList()
    /**
	* get opportunities for an organization contact as an array of nids
	*/
	public int setOppsForOrgContactListArray( OrganizationInfoDTO aOrgInfoObj, int iUID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgInfoObj) return -1;
    	int iOrgNID = aOrgInfoObj.getORGNID();
    	if(iOrgNID < 1) return -1;
    	if(iUID < 1) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setOppsForOrgContactListArray(pConn, aOrgInfoObj, iUID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setOppsForOrgContactListArray()

	
    /**
	* get contact list for an organization as an array of uids
	*/
	public int setOrgContactListArray( OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgInfoObj) return -1;
    	int iOppNID = aOrgInfoObj.getORGNID();
    	if(iOppNID < 1) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setOrgContactListArray(pConn, aOrgInfoObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setOrgContactListArray()

	
	/**
	* reset updateORGAdmins contact 
	*/
	public int updateORGAdmins( ArrayList aListIdsAndEmailNotifyFlag, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aListIdsAndEmailNotifyFlag) return -1;
    	MethodInit("updateORGAdmins");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.updateORGAdmins( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method updateORGAdmins()
	
	/**
	* reset updateORGContacts contact 
	*/
	public int updateORGContacts( ArrayList aListIdsAndEmailNotifyFlag, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aListIdsAndEmailNotifyFlag) return -1;
    	MethodInit("updateORGContacts");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.updateORGContacts( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method updateORGContacts()
	/**
	* delete Org contact (&admin) record
	*/
	public int deleteOrgContact( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	MethodInit("deleteOrgContact");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aOrgObj.setORGNID( aOrgObj.getORGNID() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOrgContact(pConn, aPersObj,aOrgObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteOrgContact()
	
	
    /**
	* add another organization contact
	*/
	public int insertAdditionalOrgContact( OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("insertAdditionalOrgContact");
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.insertAddOrgContact(pConn, aHeadObj, aIndivObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method insertAdditionalOrgAdmin()

    //=== END OrgContacts section
    //=== END OrgContacts section
    //=== END OrgContacts section

	
	
    //=== BEGIN OppContacts section
    //=== BEGIN OppContacts section
    //=== BEGIN OppContacts section
	
    /**
	* get contact list for an opportunity as an array of uids
	*/
	public int setOppContactListArray( OrgOpportunityInfoDTO aOpportObj){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOpportObj) return -1;
    	int iOppNID = aOpportObj.getOPPNID();
    	if(iOppNID < 1) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setOppContactListArray(pConn, aOpportObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setOppContactListArray()
	
    /**
	* get contact list for an email
	*/
	public int getOppVolContactList( ArrayList aList, int iOppNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOppNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOppNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOppVolContactList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOppContactList()
	
	
    /**
	* get contact list for an org for email
	*/
	public int getOrgContactList( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrgContactList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgContactList()
	
    /**
	* get contact list for an organization
	*/
	public int getOppContactList( ArrayList aList, int iOppNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOppNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOppNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOppContactList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOppContactList()
	
    /**
	* add another opportunity contact 
	*/
	public int insertAdditionalOppContact( OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("insertAdditionalOppContact");
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("opportunity required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.insertAddOppContact(pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method insertAdditionalOppContact()


	/**
	* delete Org contact (&admin) record
	*/
	public int deleteOppContact( PersonInfoDTO aPersObj, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("deleteOppContact");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOppContact(pConn, aPersObj,aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteOppContact()
	
	
    /**
	* reset resetOppContact contact 
	*/
	public int resetOppContact( OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("resetOppContact");
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.resetOppContact(pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method resetOppContact()
	
	
    /**
	* reset resetOppPrimaryContact contact 
	*/
	public int resetOppPrimaryContact( OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("resetOppContact");
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("opportunity required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.resetOppPrimaryContact(pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method resetOppPrimaryContact()
	
	/**
	* reset updateOPPContacts contact 
	*/
	public int updateOPPContacts( ArrayList aListIdsAndEmailNotifyFlag, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aListIdsAndEmailNotifyFlag) return -1;
    	MethodInit("updateOPPContacts");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.updateOPPContacts( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method updateOPPContacts()
	
	
    //=== END OppContacts section
    //=== END OppContacts section
    //=== END OppContacts section


	/**
	* delete Opportunity record
	*/
	public int deleteNonProfit( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	MethodInit("deleteOpportunity");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aOrgObj.setORGUpdateId( aPersObj.getUSPPersonNumber() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOrganizationFromDB(pConn, aOrgObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteNonProfit()

	/**
	* delete Org_detail record
	*/
	public int deleteOrgDetail( PersonInfoDTO aPersObj, OrganizationDetailsInfoDTO aOrgObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	MethodInit("deleteOpportunity");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	//aOrgObj.setDETUpdateId( aPersObj.getUSPPersonNumber() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOrganizationDetailsFromDB(pConn, aOrgObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteNonProfit()


	
	/**
	* create Organization Detail record
	*/
	public int createOrganizationDet( OrganizationInfoDTO aHeadObj, OrganizationDetailsInfoDTO aDetailObj ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("createOrganizationDet");
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Organization Name Required \n");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -22;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aDetailObj.setDETOrgNID( aHeadObj.getORGNID() );
    	//aDetailObj.setDETOrgNumber( aHeadObj.getORGOrgNumber() );
    	//aDetailObj.setDETPrimaryPerson( aHeadObj.getORGPrimaryPerson() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.createOrganizationDet(pConn, aHeadObj, aDetailObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method createOrganization()

	/**
	* edit Organization Details record
	*/
	public int updateOrganizationDet( OrganizationDetailsInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateOrganizationDet");
    	/*
    	aszTemp = aHeadObj.getORGOnethirdP();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("Low Income response required,  ");
    		bMinOK=false;
    	}
    	*/
    	int temp = aHeadObj.getDETNumVolOrg();
    	if(temp<0){
    		aHeadObj.appendErrorMsg("Number of Volunteers Per Year required \n");
    		bMinOK=false;
    	}
    	

    	if(false == bMinOK) return -22;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.editOrganizationDet(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method updateOrganizationDet()

	
	/**
	* checkIfORGTakesIntlVols
	*/
	public int checkIfORGTakesIntlVols( OrganizationInfoDTO aOrgInfoObj, OrgOpportunityInfoDTO aOpportObj, PersonInfoDTO aIndivObj ){
    	if(null == aOrgInfoObj || null == aOpportObj || null == aIndivObj) return -1;
    	MethodInit("checkIfORGTakesIntlVols");
    	String aszTemp = aOrgInfoObj.getORGTakesIntlVols();
    	int iPositionType = aOpportObj.getOPPPositionTypeTID();
    	int iIntlVols = aOrgInfoObj.getORGTakesIntlVolsTID();
    	if(iIntlVols==iDoesNotTakeIntlVolsTID){
    		if(iPositionType != iVirtualTID){
        		/*
        		if(! aOpportObj.getOPPAddrCountryName().equalsIgnoreCase(aIndivObj.getUSPAddrCountryName())){
        			return 999;
        		}
        		*/
        		if(! aOrgInfoObj.getORGAddrCountryName().equalsIgnoreCase(aIndivObj.getUSPAddrCountryName())){
        			return 999;
        		}
    		}
    	}

    	return 0;
	}
	// end-of method checkIfORGTakesIntlVols()
	
	/**
	* Does Organization Exist In System
	*/
	public int IsOrgNameZipCountryInSystem( OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("IsOrgNameZipCountryInSystem");
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Organization Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrPostalcode();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Postal Code Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCountryName();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("Country Required \n");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -22;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.IsOrganizationInSystem(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method IsOrgNameZipCountryInSystem()

	/**
	* Does Organization Exist In System
	*/
	public int IsOrgInfoInSystem( OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iTemp=0;
		int temp;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("IsOrgInfoInSystem");
    	
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Organization Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrPostalcode();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Postal Code Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getORGAddrCountryName();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Country Required \n");
    		bMinOK=false;
    	}
    	

    	if(false == bMinOK) return -22;

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.IsOrganizationInSystem(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method IsOrgInfoInSystem()


	/**
	* check email address format
	*/
    public int checkEmailFormat( String emailin ){
		int iRetCode=0;
        if(null == emailin) return -1;
        String aszTemp = emailin.trim();
        if(aszTemp.length() < 5) return -1;
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.checkEmailFormat( emailin );
		return iRetCode;
	}
	// end-of method checkEmailFormat()

	/**
	* check if contacts have changed
	*/
    public ArrayList getIntArrayListDifferences( int[] a_iInit, int[] a_iModified ){
        if(null == a_iInit) return  null;
        if(null == a_iModified) return  null;
        int i=0, j=0, iTempID=0,iRetCode=0;
        int[] a_iDifferenceInit = new int[2];
        
        ArrayList aList = new ArrayList(2);
        // iterate through this initial list to see if there are any in initial NOT IN modified.
        for(i=0; i<a_iInit.length; i++){
        	iTempID = a_iInit[i];
        	if(iTempID>0){
	        	// see if a_iModified contains this int
//	        	iRetCode=java.util.Arrays.binarySearch(a_iModified, iTempID); // **************************doesn't appear to be working
	        	iRetCode=-1;
	        	for(j=0; j<a_iModified.length; j++){
	        		if(a_iModified[j]==iTempID){
	        			iRetCode=j;
		        		break;
	        		}
	        	}
	        	if(iRetCode < 0){
	        		// not in modified list anymore, but was in initial list; it was Removed
	        		a_iDifferenceInit = new int[2];
	        		a_iDifferenceInit[0]=iTempID;
	        		a_iDifferenceInit[1]=iRemoved;
	        		aList.add(a_iDifferenceInit);
	        	}
        	}
        }
        // then iterate through modified list to see if there are any ADDED
        for(i=0; i<a_iModified.length; i++){
        	iTempID = a_iModified[i];
        	// see if a_iInit contains this int
        	if(iTempID>0){
//	        	iRetCode=java.util.Arrays.binarySearch(a_iInit, iTempID); // **************************doesn't appear to be working
	        	iRetCode=-1;
        		for(j=0; j<a_iInit.length; j++){
	        		if(a_iInit[j]==iTempID){
	        			iRetCode=j;
		        		break;
	        		}
	        	}
	        	if(iRetCode < 0){
	        		// not in initial list; in modified - this is a difference; it was Added
	        		a_iDifferenceInit = new int[2];
	        		a_iDifferenceInit[0]=iTempID;
	        		a_iDifferenceInit[1]=iAdded;
	        		aList.add(a_iDifferenceInit);
	        	}
        	}
        }
		return aList;
	}
	// end-of method getIntArrayListDifferences()
    
    public ArrayList getEmailNotifyFlag( ArrayList aListIdContacts, ArrayList aListIdEmailContacts){
        ArrayList aListTmp = new ArrayList(2);
    	return getEmailNotifyFlag(aListIdContacts, aListIdEmailContacts, aListTmp,0,0);
    }
    public ArrayList getEmailNotifyFlag( ArrayList aListIdContacts, ArrayList aListIdEmailContacts, int iInitPrimaryContactUID, int iModifiedPrimaryContactUID){
        ArrayList aListTmp = new ArrayList(2);
    	return getEmailNotifyFlag(aListIdContacts, aListIdEmailContacts, aListTmp,iInitPrimaryContactUID,iModifiedPrimaryContactUID);
    }
    /**
	* check if contacts have changed
	*/
    public ArrayList getEmailNotifyFlag( ArrayList aListIdContacts, ArrayList aListIdEmailContacts, ArrayList aListIdAdmins, int iInitPrimaryContactUID, int iModifiedPrimaryContactUID ){
        if(null == aListIdContacts && null == aListIdEmailContacts && iInitPrimaryContactUID <1 && iModifiedPrimaryContactUID < 1) return  null;
        boolean listContains = false, b_skipAddToList=false;
        int[] a_iIdAndEmailNotifyFlag = new int[2];
        int iCount=0, itmp;
        int[] a_iUIDs = new int[255];
        ArrayList aList = new ArrayList(2);
        
        // 0. If Different Primary Contact, add to the EmailNotify Flag and remove from other 2 ArrayLists  - both for init primary contact and new
        if(iInitPrimaryContactUID != iModifiedPrimaryContactUID){
        	// first, address email notifications for Inital contact (removed as primary)
        	// 1. if Still receiving emails
        	// 2. if not receiving emails, but can still modify
        	// 3. if can no longer modify
        	// might just skip over some of these cases...
            int j=0;
       		// check if iInitPrimaryContactUID in aListIdEmailContacts
            listContains = false;
        	for(j=0; j<aListIdEmailContacts.size(); j++){ 
                int[] iIdAndFlagInside = (int[])aListIdEmailContacts.get(j);
                int iUIDInside = iIdAndFlagInside[0];
                if(iInitPrimaryContactUID == iUIDInside && iInitPrimaryContactUID>0){
                	listContains = true;
                	break;
                }
            }
        	if(listContains == true){
//        		aListIdEmailContacts.remove(j);// uncomment if we want to send an email for Primary Contact
        	}
        	for(j=0; j<aListIdContacts.size(); j++){ 
                int[] iIdAndFlagInside = (int[])aListIdContacts.get(j);
                int iUIDInside = iIdAndFlagInside[0];
                if(iInitPrimaryContactUID == iUIDInside && iInitPrimaryContactUID>0){
                	listContains = true;
                	break;
                }
            }
        	if(listContains == true){
//        		aListIdContacts.remove(j);// uncomment if we want to send an email for Primary Contact
        	}
        	if(iInitPrimaryContactUID>0){
	        	a_iIdAndEmailNotifyFlag=new int[2];
	    		a_iIdAndEmailNotifyFlag[0]=iInitPrimaryContactUID;
	    		a_iIdAndEmailNotifyFlag[1]= iNoLongerPrimaryContact;
	    		aList.add(a_iIdAndEmailNotifyFlag);
        	}
        	
       		// check if iInitPrimaryContactUID in aListIdEmailContacts
            listContains = false;
        	for(j=0; j<aListIdEmailContacts.size(); j++){ 
                int[] iIdAndFlagInside = (int[])aListIdEmailContacts.get(j);
                int iUIDInside = iIdAndFlagInside[0];
                if(iInitPrimaryContactUID == iUIDInside && iInitPrimaryContactUID>0){
                	listContains = true;
                	break;
                }
            }
        	if(listContains == true){
//        		aListIdEmailContacts.remove(j);// uncomment if we want to send an email for Primary Contact
        	}
        	for(j=0; j<aListIdContacts.size(); j++){ 
                int[] iIdAndFlagInside = (int[])aListIdContacts.get(j);
                int iUIDInside = iIdAndFlagInside[0];
                if(iModifiedPrimaryContactUID == iUIDInside){
                	listContains = true;
                	break;
                }
            }
        	if(listContains == true){
//        		aListIdContacts.remove(j); // uncomment if we want to send an email for Primary Contac
        	}
        	a_iIdAndEmailNotifyFlag=new int[2];
    		a_iIdAndEmailNotifyFlag[0]=iModifiedPrimaryContactUID;
    		a_iIdAndEmailNotifyFlag[1]=iNewPrimaryContact;
    		aList.add(a_iIdAndEmailNotifyFlag);
    		a_iUIDs[iCount]=iModifiedPrimaryContactUID;
    		iCount++;
        	
        }
        
        // 1. in aListIdEmailContacts - 
        for(int i=0; i<aListIdEmailContacts.size(); i++){ 
            int[] iIdAndFlag = (int[])aListIdEmailContacts.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
        	// a. REMOVED
            if(iFlag==iRemoved){
                listContains = false;
                int j=0;
           		// check if in aListIdContacts
            	for(j=0; j<aListIdContacts.size(); j++){ 
                    int[] iIdAndFlagInside = (int[])aListIdContacts.get(j);
                    if(iIdAndFlagInside == iIdAndFlag){
                    	listContains = true;
                    	break;
                    }
                }
        		if(iUID>0){
	            	if(listContains == true){
	        			// if yes
	    					// EMAIL iNoLongerEmailOrContact(iNoLongerGetsEmails && iNoLongerIsOppContact)
	    					// REMOVE THIS VALUE (&following flag) FROM aListIdContacts
	                	a_iIdAndEmailNotifyFlag=new int[2];
	            		a_iIdAndEmailNotifyFlag[0]=iUID;
	            		a_iIdAndEmailNotifyFlag[1]=iNoLongerEmailOrContact;
	            		aList.add(a_iIdAndEmailNotifyFlag);
	            		
	            		aListIdContacts.remove(j);
	            	}else{
	                	a_iIdAndEmailNotifyFlag=new int[2];
	            		a_iIdAndEmailNotifyFlag[0]=iUID;
	            		a_iIdAndEmailNotifyFlag[1]=iNoLongerGetsEmails;
	            		aList.add(a_iIdAndEmailNotifyFlag);
	            	}
        		}
            // b. ADDED
            }else if(iFlag==iAdded){
                listContains = false;
                int j=0;
           		// check if in aListIdContacts
            	for(j=0; j<aListIdContacts.size(); j++){ 
                    int[] iIdAndFlagInside = (int[])aListIdContacts.get(j);
                    if(iIdAndFlagInside == iIdAndFlag){
                    	listContains = true;
                    	break;
                    }
                }
            	if(listContains == true){
        			// if yes
            			// EMAIL iNowEmailAndContact(iNowGetsEmails && iNowIsOppContact)
    					// REMOVE THIS VALUE (&following flag) FROM aListIdContacts
                	a_iIdAndEmailNotifyFlag=new int[2];
            		a_iIdAndEmailNotifyFlag[0]=iUID;
            		a_iIdAndEmailNotifyFlag[1]=iNowEmailAndContact;
                	for(itmp=0;itmp<=iCount; itmp++){
                		if(a_iUIDs[itmp]==iUID){
                			b_skipAddToList=true;
                			break;
                		}
                	}
                	if(b_skipAddToList==false){
                		aList.add(a_iIdAndEmailNotifyFlag);
                		a_iUIDs[iCount]=iUID;
                		iCount++;
                	}
        			b_skipAddToList=false;
            		
            		aListIdContacts.remove(j);
            	}else{
                	a_iIdAndEmailNotifyFlag=new int[2];
            		a_iIdAndEmailNotifyFlag[0]=iUID;
            		a_iIdAndEmailNotifyFlag[1]=iNowGetsEmails;
                	for(itmp=0;itmp<=iCount; itmp++){
                		if(a_iUIDs[itmp]==iUID){
                			b_skipAddToList=true;
                			break;
                		}
                	}
                	if(b_skipAddToList==false){
                		aList.add(a_iIdAndEmailNotifyFlag);
                		a_iUIDs[iCount]=iUID;
                		iCount++;
                	}
        			b_skipAddToList=false;
            	}
            }
        }
        // 2. in aListIdContacts -
        for(int i=0; i<aListIdContacts.size(); i++){ 
            int[] iIdAndFlag = (int[])aListIdContacts.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
        	// a. REMOVED
            if(iFlag==iRemoved && iUID>0){
                // EMAIL iNoLongerIsOppContact
            	a_iIdAndEmailNotifyFlag=new int[2];
            	a_iIdAndEmailNotifyFlag[0]=iUID;
            	a_iIdAndEmailNotifyFlag[1]=iNoLongerIsOppContact;
            	aList.add(a_iIdAndEmailNotifyFlag);
            // b. ADDED
            }else if(iFlag==iAdded){
                // EMAIL iNowIsOppContact
            	a_iIdAndEmailNotifyFlag=new int[2];
            	a_iIdAndEmailNotifyFlag[0]=iUID;
            	a_iIdAndEmailNotifyFlag[1]=iNowIsOppContact;
            	for(itmp=0;itmp<=iCount; itmp++){
            		if(a_iUIDs[itmp]==iUID){
            			b_skipAddToList=true;
            			break;
            		}
            	}
            	if(b_skipAddToList==false){
                	aList.add(a_iIdAndEmailNotifyFlag);
            		a_iUIDs[iCount]=iUID;
            		iCount++;
            	}
    			b_skipAddToList=false;
            }
        }
        // 3. in aListIdAdmins -
        for(int i=0; i<aListIdAdmins.size(); i++){ 
            int[] iIdAndFlag = (int[])aListIdAdmins.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
        	// a. REMOVED
            if(iFlag==iRemoved && iUID>0){
                // EMAIL iNoLongerIsOppContact
            	a_iIdAndEmailNotifyFlag=new int[2];
            	a_iIdAndEmailNotifyFlag[0]=iUID;
            	a_iIdAndEmailNotifyFlag[1]=iRemovedAdmin;
            	aList.add(a_iIdAndEmailNotifyFlag);
            // b. ADDED
            }else if(iFlag==iAdded){
                // EMAIL iNowIsOppContact
            	a_iIdAndEmailNotifyFlag=new int[2];
            	a_iIdAndEmailNotifyFlag[0]=iUID;
            	a_iIdAndEmailNotifyFlag[1]=iAddedAdmin;
            	for(itmp=0;itmp<=iCount; itmp++){
            		if(a_iUIDs[itmp]==iUID){
            			b_skipAddToList=true;
            			break;
            		}
            	}
            	if(b_skipAddToList==false){
                	aList.add(a_iIdAndEmailNotifyFlag);
            		a_iUIDs[iCount]=iUID;
            		iCount++;
            	}
    			b_skipAddToList=false;
            }
        }
		return aList;
	}
	// end-of method getIntArrayListDifferences()
    
    /**
     * generate URL alias
     */
    public int generateURLAlias( OrganizationInfoDTO aHeadObj, String param ){
    	int iRetCode=0;
		ABREDBConnection pConn=null;
		String aszPuncRemove = "s:1:\"0\";";
		String aszPuncSeparator = "s:1:\"1\";";
		String aszPuncNothing = "s:1:\"2\";";
    	if(null == aHeadObj) return -1;
    	String aszURLAlias = "";
    	if(param.equals("urlalias")){
    		aszURLAlias = aHeadObj.getORGUrlAlias();
    	}else if(param.equals("portalpath")){
    		aszURLAlias = aHeadObj.getPortalNameModified();
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	//String aszCustomizedURLAlias = aCodes.getPathAutoURLAliasInfo( aCodesObj, aszURLAlias );
    	iRetCode = aOrganizationObj.getPathAutoURLAliasInfo( pConn, aHeadObj );

    	//iRetCode = m_AppCodesBRLOObj.getPathAutoURLAliasInfo(pConn, aList);
    	if(null != pConn) pConn.free();
       	
    	String aszSeparator = aHeadObj.getPathAutoSeparator();
    	int iFirstColon = aszSeparator.indexOf(":", 0);
    	int iSecondColon = aszSeparator.indexOf(":", iFirstColon+1);
    	
    	int iLength= aszSeparator.length();
    	aszSeparator=aszSeparator.substring(iSecondColon+2, iLength-2);
    	aHeadObj.setPathAutoSeparator(aszSeparator);
       	
    	
    	String aszPathAutoOrgPattern = aHeadObj.getPathAutoOrgPattern();
    	iFirstColon = aszPathAutoOrgPattern.indexOf(":", 0);
    	iSecondColon = aszPathAutoOrgPattern.indexOf(":", iFirstColon+1);
    	iLength= aszPathAutoOrgPattern.length();
    	aszPathAutoOrgPattern=aszPathAutoOrgPattern.substring(iSecondColon+2, iLength-2);
    	aszPathAutoOrgPattern = aszPathAutoOrgPattern.replaceAll("\\[title-raw\\]", "");
    	aszPathAutoOrgPattern = aszPathAutoOrgPattern.replaceAll("[title]", "");
    	aHeadObj.setPathAutoOrgPattern(aszPathAutoOrgPattern);
    	
    	String aszPathAutoOppPattern = aHeadObj.getPathAutoOppPattern();
    	iFirstColon = aszPathAutoOppPattern.indexOf(":", 0);
    	iSecondColon = aszPathAutoOppPattern.indexOf(":", iFirstColon+1);
    	iLength= aszPathAutoOppPattern.length();
    	aszPathAutoOppPattern=aszPathAutoOppPattern.substring(iSecondColon+2, iLength-2);
    	aszPathAutoOppPattern = aszPathAutoOppPattern.replaceAll("\\[title-raw\\]", "");
    	aszPathAutoOppPattern = aszPathAutoOppPattern.replaceAll("\\[title\\]", "");
    	aHeadObj.setPathAutoOppPattern(aszPathAutoOppPattern);
    	
    	// cycle through all the special word cases - from um_variable where name like 'pathauto_ignore_words'
    	// 		--> s:180:"a,an, as,.........."
    	//		- also account for spaces and commas in the string of 180 chars; remove spaces. then comma delimit
    	String aszIgnoreWords = aHeadObj.getPathAutoIgnoreWords();//"s:180:\"a,an,as,at,before,but,by,for,from,is,in,into,like,of,off,on,onto,per,since,than,the,this,that,to,up,via,with, it, you, that, he, was, for, are, i, his, they, be, one, have, or, had\"";
    	iFirstColon = aszIgnoreWords.indexOf(":", 0);
    	iSecondColon = aszIgnoreWords.indexOf(":", iFirstColon+1);
    	String aszWordsCharLength = aszIgnoreWords.substring(iFirstColon+1 , iSecondColon);
    	int iWordsCharLength = Integer.parseInt(aszWordsCharLength);
    	int iWordsLength = 0;
    	for(int i=1; i<aszIgnoreWords.length(); i++){ //i<iWordsCharLength; i++){
            final char c = aszIgnoreWords.charAt(i);
            if (c == ',') {
            	iWordsLength++;
            }
    	}
    	int iNumWords = iWordsLength;
    	if(iNumWords>0)iNumWords++;
    	int iStart=iSecondColon+2, iEnd=aszIgnoreWords.indexOf(",");
    	String tmpString = "";
    	for(int i=0; i<iNumWords; i++){
    		tmpString = aszIgnoreWords.substring(iStart,iEnd);
    		//tmpString = tmpString.replaceAll(" ","");
    		//tmpString = tmpString.replaceAll("\"","");
    		
    		tmpString = tmpString.trim(); //get rid of any whitespace in case they were entered as "word, word" instead of "word,word"
        	aszURLAlias = aszURLAlias.replaceAll("(?i)^"+tmpString+" "," ");//aHeadObj.getPathAutoSeparator()); starts with case-insensitive "word "
        	aszURLAlias = aszURLAlias.replaceAll(" (?i)"+tmpString+"$"," ");//aHeadObj.getPathAutoSeparator()); ends with case-insensitive " word"
//        	aszURLAlias = aszURLAlias.replaceAll("-(?i)"+tmpString+" "," ");//aHeadObj.getPathAutoSeparator());
        	aszURLAlias = aszURLAlias.replaceAll(" (?i)"+tmpString+"-"," ");//aHeadObj.getPathAutoSeparator());
//        	aszURLAlias = aszURLAlias.replaceAll("-(?i)"+tmpString+"-"," ");//aHeadObj.getPathAutoSeparator());
        	aszURLAlias = aszURLAlias.replaceAll(" (?i)"+tmpString+" "," ");//aHeadObj.getPathAutoSeparator());
       		iStart = iEnd+1;
           	if(i == (iWordsLength - 1)){
        		iEnd = aszIgnoreWords.length()-2;
        	}else if(i < iWordsLength){
        		iEnd = aszIgnoreWords.indexOf(",",iStart);
        	}
    	}
    	
    	if(aHeadObj.getPathAutoPuncAmp().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("&",""); //
    	}else if(aHeadObj.getPathAutoPuncAmp().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("&",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncAstrsk().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\*",""); //
    	}else if(aHeadObj.getPathAutoPuncAstrsk().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\*",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncAt().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("@",""); //
    	}else if(aHeadObj.getPathAutoPuncAt().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("@",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncBckTck().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("`",""); //
    	}else if(aHeadObj.getPathAutoPuncBckTck().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("`",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncBckSlsh().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\\\",""); //*****************************failed!! throws an error to DispatchAction.class
    	}else if(aHeadObj.getPathAutoPuncBckSlsh().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\\\",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncCaret().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\^",""); //
    	}else if(aHeadObj.getPathAutoPuncCaret().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\^",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncColon().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(":",""); //
    	}else if(aHeadObj.getPathAutoPuncColon().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(":",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncComma().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(",",""); //
    	}else if(aHeadObj.getPathAutoPuncComma().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(",",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncDolrSign().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\$",""); //
    	}else if(aHeadObj.getPathAutoPuncDolrSign().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\$",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncDblQuotes().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\"",""); //
    	}else if(aHeadObj.getPathAutoPuncDblQuotes().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\"",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncEqual().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("=",""); //
    	}else if(aHeadObj.getPathAutoPuncEqual().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("=",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncExclam().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("!",""); //
    	}else if(aHeadObj.getPathAutoPuncExclam().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("!",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncGrtr().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(">",""); //
    	}else if(aHeadObj.getPathAutoPuncGrtr().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(">",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncHash().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("#",""); //
    	}else if(aHeadObj.getPathAutoPuncHash().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("#",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncHyphen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("-",""); //
    	}else if(aHeadObj.getPathAutoPuncHyphen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("-",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftCrly().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\{",""); //
    	}else if(aHeadObj.getPathAutoPuncLftCrly().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\{",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftParen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\(",""); //
    	}else if(aHeadObj.getPathAutoPuncLftParen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\(",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftSq().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\[",""); //
    	}else if(aHeadObj.getPathAutoPuncLftSq().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\[",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLess().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("<",""); //
    	}else if(aHeadObj.getPathAutoPuncLess().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("<",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPerc().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("%",""); //
    	}else if(aHeadObj.getPathAutoPuncPerc().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("%",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPeriod().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\.",""); //
    	}else if(aHeadObj.getPathAutoPuncPeriod().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\.",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPipe().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\|",""); //
    	}else if(aHeadObj.getPathAutoPuncPipe().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\|",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPlus().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\+",""); //
    	}else if(aHeadObj.getPathAutoPuncPlus().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\+",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncQuest().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\?",""); //
    	}else if(aHeadObj.getPathAutoPuncQuest().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\?",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncSingleQut().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("'",""); //
    	}else if(aHeadObj.getPathAutoPuncSingleQut().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("'",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtCurly().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\}",""); //
    	}else if(aHeadObj.getPathAutoPuncRtCurly().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\}",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtParen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\)",""); //
    	}else if(aHeadObj.getPathAutoPuncRtParen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\)",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtSq().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\]",""); //
    	}else if(aHeadObj.getPathAutoPuncRtSq().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\]",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncSemiColon().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(";",""); //
    	}else if(aHeadObj.getPathAutoPuncSemiColon().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(";",aHeadObj.getPathAutoSeparator()); //
        	aszURLAlias = aszURLAlias.replaceAll(";",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncTilde().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("~",""); //
        	aszURLAlias = aszURLAlias.replaceAll("~",""); //
    	}else if(aHeadObj.getPathAutoPuncTilde().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("~",aHeadObj.getPathAutoSeparator()); //
        	aszURLAlias = aszURLAlias.replaceAll("~",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncUnderScore().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("_",""); //
        	aszURLAlias = aszURLAlias.replaceAll("_",""); //
    	}else if(aHeadObj.getPathAutoPuncUnderScore().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("_",aHeadObj.getPathAutoSeparator()); //
        	aszURLAlias = aszURLAlias.replaceAll("_",aHeadObj.getPathAutoSeparator()); //
    	}

    	aszURLAlias = aszURLAlias.replaceAll(" ",aHeadObj.getPathAutoSeparator()); //space
    	aszURLAlias = aszURLAlias.replaceAll("\\W",aHeadObj.getPathAutoSeparator()); //(look up regex on wikipedia)	\W 	[^\w] 	non-word character
    	aszURLAlias = aszURLAlias.replaceAll("^"+aHeadObj.getPathAutoSeparator(),""); //leading getPathAutoSeparator
    	aszURLAlias = aszURLAlias.replaceAll(aHeadObj.getPathAutoSeparator()+"$",""); //trailing getPathAutoSeparator
    	aszURLAlias = aszURLAlias.replaceAll("^-",""); //leading dash
    	aszURLAlias = aszURLAlias.replaceAll("-$",""); //trailing dash
    	aszURLAlias = aszURLAlias.replaceAll("^_",""); //leading underscore
    	aszURLAlias = aszURLAlias.replaceAll("_$",""); //trailing underscore
    	while(aszURLAlias.contains( aHeadObj.getPathAutoSeparator()+aHeadObj.getPathAutoSeparator() )== true ){
        	aszURLAlias = aszURLAlias.replaceAll(aHeadObj.getPathAutoSeparator()+aHeadObj.getPathAutoSeparator(),aHeadObj.getPathAutoSeparator()); //whatever the separater is set to be int he db
    	}
    	while(aszURLAlias.contains("--")== true ){
        	aszURLAlias = aszURLAlias.replaceAll("--","-"); //double dashes
    	}

    	aszURLAlias = aszURLAlias.toLowerCase();
    	if(param.equals("urlalias")){
    		aHeadObj.setORGUrlAlias(aszURLAlias);
    	}else if(param.equals("portalpath")){
//    		aHeadObj.setPortalName(aszURLAlias);
    		aHeadObj.setPortalNameModified(aszURLAlias);
//    		aHeadObj.setPortalName(aszURLAlias);
    	}
    	
    	
    	return iRetCode;
    }

	/**
	* Does URL Alias already exist in DB? ... if so, tag on -0, -1, -2, ... and keep re-checking.
	*/
    public String URLAlias( String aszURLAlias, int iNID, ArrayList aList ){
    	int iRetCode=0;
    	String isURLAlias = "false";
    	OrganizationObj aOrganizationObj = new OrganizationObj();

    	String aszURLsrc = null, aszURLdst = null;
		if(null != aList){
			for(int index=0; index < aList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aList.get(index);
				if(null == aAppCode) continue;
				aszURLsrc = aAppCode.getURLsrc();
				aszURLdst = aAppCode.getURLdst();
				if(aszURLAlias.equalsIgnoreCase(aszURLdst)){
					if(aszURLsrc.equalsIgnoreCase("node/"+iNID)){
						// then the URL is fine; maybe clear the DTO method so that we can see that we don't want to insert/update that
						isURLAlias = "falseNoChange";
						return isURLAlias;
					}else{
						// need to add -0 and check again; then add -1 and check again, etc, until we find one not existing
						isURLAlias = "true";
						return isURLAlias;
					}
				}
			}
		}
    	
    	return isURLAlias;
    }

	/**
	* Does URL Alias already exist in DB? ... if so, tag on -0, -1, -2, ... and keep re-checking.
	*/
    public int IsURLAliasInSystem( OrganizationInfoDTO aHeadObj, int iNID ){
    	int iRetCode=0;
    	ArrayList aList = new ArrayList(2);
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszURLAlias = aHeadObj.getORGUrlAlias();
    	String tmpURLAlias = aszURLAlias;
    	String newURLAlias = "";
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	//String aszCustomizedURLAlias = aCodes.getPathAutoURLAliasInfo( aCodesObj, aszURLAlias );
    	iRetCode = aOrganizationObj.checkPathAutoURLAliasExisting( pConn, aList, aHeadObj );
    	if(null != pConn) pConn.free();

    	String aszURLsrc = null, aszURLdst = null;
    	// the following is poorly coded, b/c we don't know how many numbers we'll have to tag on or not... how many nested if's, etc...
		if(null != aList){
			for(int i=0; i<aList.size(); i++){
				newURLAlias = URLAlias(aszURLAlias, iNID, aList);
				if(newURLAlias.equalsIgnoreCase("true")){
					aszURLAlias = tmpURLAlias + "-" + i;
				}else{
					break;
				}
			}
		}
		if(newURLAlias.equalsIgnoreCase("falseNoChange") || newURLAlias.equalsIgnoreCase("trueNotUnique")){
			aHeadObj.setORGUrlAlias("");
		}else{
			aHeadObj.setORGUrlAlias(aszURLAlias);
		}
    	
  	
    	return iRetCode;
    }


	/**
	* Does PortalName already exist in DB? ... if so, tag on -0, -1, -2, ... and keep re-checking.
	*/
    public String PortalName( String aszPortalName, int iNID, ArrayList aList ){
    	int iRetCode=0;
    	String isPortalName = "false";
    	OrganizationObj aOrganizationObj = new OrganizationObj();

    	String aszURLsrc = null, aszURLdst = null;
		if(null != aList){
			for(int index=0; index < aList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aList.get(index);
				if(null == aAppCode) continue;

				// need to iterate through aAppCodeInfo and see if aszPortalName exists as the APCKeycode in any of them; if yes, then check if same tid
				int iTid = aAppCode.getAPCIdSubType();
				String aszKeycode = aAppCode.getAPCKeycode();
				if(aszPortalName.equalsIgnoreCase(aszKeycode)){ // the word exists; now need to check if it only exists with given nid
					ABREDBConnection pConn=null;
			    	pConn = getDBConn();
			    	//check if the portal name already exists, but in reference to the current nid (ie, no update)
			    	iRetCode = aOrganizationObj.PortalName( pConn, iTid, iNID );
			    	if(null != pConn) pConn.free();
			    	
					if(iRetCode == 777){
						// then the URL is fine; maybe clear the DTO method so that we can see that we don't want to insert/update that
						isPortalName = "falseNoChange";
						return isPortalName;
					}else{
						// need to add -0 and check again; then add -1 and check again, etc, until we find one not existing
						isPortalName = "true";
						return isPortalName;
					}
				}
			}
		}
    	return isPortalName;
    }


	/**
	* Does PortalName already exist in DB? ... if so, tag on -0, -1, -2, ... and keep re-checking.
	*/
    public int IsPortalNameInSystem( OrganizationInfoDTO aHeadObj, int iNID ){
    	int iRetCode=0;
    	ArrayList aList = new ArrayList(2);
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszPortalName = aHeadObj.getPortalNameModified();
    	String tmpPortalName = aszPortalName;
    	String newPortalName = "";
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	//String aszCustomizedURLAlias = aCodes.getPathAutoURLAliasInfo( aCodesObj, aszURLAlias );
    	iRetCode = aOrganizationObj.checkPathAutoPortalNameExisting( pConn, aList, aHeadObj );
    	if(null != pConn) pConn.free();

    	String aszURLsrc = null, aszURLdst = null;
    	// the following is poorly coded, b/c we don't know how many numbers we'll have to tag on or not... how many nested if's, etc...
		if(iRetCode == 0){ // alr************** need to test incrementing to avoid duplicates (accounting for allowing the same for same tid)
	    	if(null != aList){
				for(int i=0; i<aList.size(); i++){
					newPortalName = PortalName(aszPortalName, iNID, aList);
					if(	aszPortalName.equalsIgnoreCase("org")		||
						aszPortalName.equalsIgnoreCase("volunteer")	||
						aszPortalName.equalsIgnoreCase("job")		||
						aszPortalName.equalsIgnoreCase("jobs")		||
						aszPortalName.equalsIgnoreCase("cms")		||
						aszPortalName.equalsIgnoreCase("tmc")		||
						aszPortalName.equalsIgnoreCase("tm")		||
						aszPortalName.equalsIgnoreCase("cv")		||
						aszPortalName.equalsIgnoreCase("esa")		||
						aszPortalName.equalsIgnoreCase("bg")		||
						aszPortalName.equalsIgnoreCase("bg")				
					){
						newPortalName="true";
					}
					if(newPortalName.equalsIgnoreCase("true")){
						aszPortalName = tmpPortalName + "-" + i;
					}else{
						break;
					}
				}
				if(newPortalName.equalsIgnoreCase("falseNoChange") || newPortalName.equalsIgnoreCase("trueNotUnique")){
					aHeadObj.setPortalNameModified("");
				}else{
					aHeadObj.setPortalNameModified(aszPortalName);
				}
			}
		}
    
		else{
			aHeadObj.setPortalNameModified(aszPortalName);
		}
    	
  	
    	return iRetCode;
    }

    /**
	* get email list for an organization, opportunity, or volunteer
	*/
	public int getEmailList( ArrayList aList, EmailInfoDTO aHeadObj, int iType){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iType < 1) return -1;

    	EmailObj aEmailObjObj = new EmailObj();
    	pConn = getDBConn();
    	iRetCode = aEmailObjObj.getEmailList(pConn, aList, aHeadObj, iType);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getEmailList()

	

	// 				BEGIN LEGACY CONTACT CODE
	// 				BEGIN LEGACY CONTACT CODE
	// 				BEGIN LEGACY CONTACT CODE
    /**
	* add another organization contact LEGACY
	*/
	public int insertAdditionalOrgContact_LEGACY( OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("insertAdditionalOrgContact");
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.insertAddOrgContactInt_LEGACY(pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method insertAdditionalOrgContact()

	/**
	* delete Org contact record
	*/
	public int deleteOrgContact_LEGACY( PersonInfoDTO aPersObj, OrganizationInfoDTO aOrgObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgObj) return -1;
    	MethodInit("deleteOpportunity");
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	aOrgObj.setORGNID( aOrgObj.getORGNID() );
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.deleteOrgContactFromDB_LEGACY(pConn, aPersObj,aOrgObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteOrgContact()
	
    /**
	* reset organization contact 
	*/
	public int resetOrgContact_LEGACY( OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("resetOrgContact");
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("owner user id required");
    		return -2;
    	}
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	
    	iRetCode = aOrganizationObj.resetOrgContact_LEGACY(pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method resetOrgContact_LEGACY()
	
    /**
	* get contact list for an organization
	*/
	public int getOrganizationContactList_LEGACY( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrganizationContactList_LEGACY(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrganizationContactList_LEGACY()

	
    /**
	* get contact list for an organization
	*/
	public int getOrgVolContactList_LEGACY( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOrgVolContactList_LEGACY(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgVolContactList()



	
    /**
	* get contact list for an organization
	*/
	public int checkOwnership_LEGACY( int iOrgNID, int iUID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(iOrgNID < 1) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.checkOrgContact_LEGACY(pConn, iOrgNID, iUID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method checkOwnership()
	
	
    /**
	* set primary contact for an organization
	*/
	public int setOrgPrimaryContact_LEGACY( OrganizationInfoDTO aOrgInfoObj, PersonInfoDTO aContactPersonObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aOrgInfoObj) return -1;
    	if(null == aContactPersonObj) return -1;
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.setOrgPrimaryContact_LEGACY(pConn, aOrgInfoObj, aContactPersonObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method setOrgPrimaryContact()
	
	// 				END LEGACY CONTACT CODE
	// 				END LEGACY CONTACT CODE
	// 				END LEGACY CONTACT CODE

	
	
	
	public boolean IsOrgDomestic(OrganizationInfoDTO aOrgInfoObj) {
		String countryName = aOrgInfoObj.getCountryName();
	    return countryName.equals("United States");
	}
		
	public String getORGDonateURLFallBackToGuideStar(OrganizationInfoDTO aOrgInfoObj){
		String s = aOrgInfoObj.getORGFedTaxId();
		if(aOrgInfoObj.getORGDonateURL() != null && aOrgInfoObj.getORGDonateURL().length() > 0) return aOrgInfoObj.getORGDonateURL();
		if(aOrgInfoObj.getORGAddrCountryName().equalsIgnoreCase("us")
		     &&
		   aOrgInfoObj.getORGFedTaxId() != null
		     &&
		   aOrgInfoObj.getORGFedTaxId().length() > 0
		     && 
		   this.einValidation(aOrgInfoObj))
		  return "http://www2.guidestar.org/ReportNonProfit.aspx?ein=" + aOrgInfoObj.getORGFedTaxId();
		return "";
	}
	
	public String getORGDonateURLFallBackToNetworkForGood(OrganizationInfoDTO aOrgInfoObj){
		String s = aOrgInfoObj.getORGFedTaxId();
		if(aOrgInfoObj.getORGDonateURL() != null && aOrgInfoObj.getORGDonateURL().length() > 0) return aOrgInfoObj.getORGDonateURL();
		if(aOrgInfoObj.getORGAddrCountryName().equalsIgnoreCase("us")
		     &&
		   aOrgInfoObj.getORGFedTaxId() != null
		     &&
		   aOrgInfoObj.getORGFedTaxId().length() > 0
		     && 
		   this.einValidation(aOrgInfoObj))
		  return "https://www.networkforgood.org/donation/MakeDonation.aspx?ORGID2=" + aOrgInfoObj.getORGFedTaxId();
		return "";
	}
	
	private boolean einValidation(OrganizationInfoDTO aOrgInfoObj) {
		String countryName = aOrgInfoObj.getORGAddrCountryName();
		if(countryName == null || !countryName.equals("us")) return true;
		
		String ein = aOrgInfoObj.getORGFedTaxId();
		if(ein == null || ein.length() == 0) return true;
		
		if(ein.length() != 10) return false;
		
		for(int i = 0; i < 10; i++) {
			if(i == 2) {
				if(ein.charAt(i) != '-') return false;  
			}
			else {
				if(!Character.isDigit(ein.charAt(i))) return false;
			}
		}
		
		return true;
	}

	private String getRailsDBBySiteVersion(String aszSiteVersion) {
	    if(aszSiteVersion.equals("development"))
	    	return this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    return this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	}
	
	private int loadQuestionnaireServerFile(OrgOpportunityInfoDTO aHeadObj, String documentRoot) {
		if(aHeadObj.getOPPNID() < 0) return -1;
			
        File dir = new File(
        	new File(documentRoot).getParent() + File.separator +
        	"files" + File.separator +
            "opportunity_documents" + File.separator +  
            aHeadObj.getOPPNID() + File.separator + 
            "questionnaire" + File.separator
        );
        
    	File latestFile = null;
        if(dir.exists()) {
        	if(!dir.isDirectory()) return -1;
        	String latestTimestamp = null;
            for(File f : dir.listFiles()) {
            	String[] tokens = f.getName().split("\\.");
            	if(tokens.length <= 0) continue;
            	String timestamp = tokens[0];
            	if(latestFile == null || timestamp.compareTo(latestTimestamp) > 0) {
            		latestTimestamp = timestamp;
            		latestFile = f;
            	}
            }        	
        }
        
        aHeadObj.setQuestionnaireServerFile(latestFile);
		return 0;
	}
	
	private int iWarningTime = 30; //number of days until expiration - warning
	private ApplicationCodesBRLO m_AppCodesBRLOObj= new ApplicationCodesBRLO();
	
	private int iTakesIntlVolsTID=22119;
	private int iDoesNotTakeIntlVolsTID=22120;
	private int iVirtualTID=4795;
	private int iProgTypeChurchOutreachTID=25188;
	private static final int iSendHostIntlTID=32163;
	private static final int iHurrSandyOrgNID = 494934;
	private static final int iDisasterReliefOrgNID = 511070;

	private static final String POLICY_FILE_LOCATION = "antisamy-myspace-1.4.4.xml";
	private static final int iRemoved=1, iAdded=2, iNowGetsEmails=3, iNoLongerGetsEmails=4, iNowIsOppContact=5, iNoLongerIsOppContact=6, 
		iNowEmailAndContact=7, iNoLongerEmailOrContact=8, iNewPrimaryContact=9, iNoLongerPrimaryContact=10, iNowIsORGContact=11, iNoLongerIsORGContact=12,
		iRemovedAdmin=21, iAddedAdmin=22; 
	private static final int iNowIsOrgContactLegacy = 80, iNoLongerOrgContact=81, iNowGetsEmailsLegacy=82, iNoLongerGetsEmailsLegacy=83;
}
