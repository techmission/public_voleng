package com.abrecorp.opensource.javamail;

/**
* Created 2006-09-20
* Organization Interface Object
* @author David Marcillo  Ali McCracken
* ABRE Technology Corp.
*/

import java.util.ArrayList;

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;

public class EmailObj extends ABREBase {

	/*
	* SQUARE PROXIMITY SEARCH
	*/

	// Pi divided by 180
	public static final double PI_DIV_BY_RAD=0.0174; 
	// Number of miles per degree of latitude.
	public static final double MILES_PER_LATITUDE=69.1; 
	// Radius of the earth in miles
	public static final double EARTH_RADIUS_IN_MILES=3956.0; 


	/**
	** Constructor
	*/
	public EmailObj(){}


    //=== START Table emailinfo ===>

    /**
	* delete organization from organizationinfo table
	*/
	public int deleteEmailFromDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteEmailFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.deleteEmailFromDB( pConn, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteEmailFromDB()

	

    /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyCodeList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getTaxonomyCodeList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return -1;
    	}
    	if(null == aSrchParmObj){
    		setErr("null search object");
    		return -1;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.getTaxonomyCodeListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getAppCodeList()

	/**
	* create logApplication email
	* return not zero for fail, return 0 for success
	*/
	public int logApplication(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("logApplication");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.logApplicationDB( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method logApplication()


	/**
	* updateLogApplication email
	* return not zero for fail, return 0 for success
	*/
	public int updateLogApplication(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("logApplication");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.updateLogApplicationDB( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method updateLogApplication()

	/**
	* create getEmailId email
	* return not zero for fail, return 0 for success
	*/

	public int getEmailId(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iMinutes ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("getEmailId");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.getEmailIdDB( pConn, aHeadObj, iMinutes );
    	return iRetCode;
	}
    // end-of method getEmailId()


	/**
	* create a new email
	* return not zero for fail, return 0 for success
	*/
	public int createEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("createEmail");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.insertEmailIntDB( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method createEmail()

	/**
	* updateEmail a  email
	* return not zero for fail, return 0 for success
	*/
	public int updateEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("updateEmail");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	if(iType==1){
    		iRetCode = aDBAObj.updateStatusDBEmail( pConn, aHeadObj );
    	}else{
    		iRetCode = aDBAObj.updateDBEmail( pConn, aHeadObj );
    	}
    	
    	return iRetCode;
	}
    // end-of method updateEmail()

	/**
	* load one email
	* return not zero for fail, return 0 for success
	*/
	public int loadEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		return 	loadEmail( pConn,  aHeadObj, 0 );
		
	}
	public int loadEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadEmail");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.getEmailFromDB( pConn, aHeadObj, iType );
    	return iRetCode;
	}
    // end-of method loadEmail()


	/**
	* load one email for application email system, given the nid of the email
	* return not zero for fail, return 0 for success
	*/
	public int loadApplicEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iIdNum ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadEmail");
    	if(null == aHeadObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(iIdNum<1){
    		setErr("no id set for nid");
    		return -3;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.loadApplicEmailFromDB(pConn, aHeadObj, iIdNum );
    	return iRetCode;
	}
    // end-of method loadApplicEmail()

	public int loadApplicEmailFlag(ABREDBConnection pConn, EmailInfoDTO aEmailObj, int iApplicNID, int iOrgNID, int iEmailFlID, int iType ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadApplicEmailFlag");
    	if(null == aEmailObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(iApplicNID<1){
    		setErr("no id set for applicnid");
    		return -3;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.loadApplicEmailFlagFromDB(pConn, aEmailObj, iApplicNID, iOrgNID, iEmailFlID, iType );
    	return iRetCode;
	}
    // end-of method loadApplicEmailFlag()

	/**
	* create a new email
	* return not zero for fail, return 0 for success
	*/
	public int insertApplicEmailFlag(ABREDBConnection pConn, ApplicationInfoDTO aApplicInfoObj, OrganizationInfoDTO aOrgInfoObj, int iNID ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("createEmail");
    	if(null == aApplicInfoObj){
    		setErr("null email object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}

    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.insertApplicEmailFlagIntDB(pConn, aApplicInfoObj, aOrgInfoObj, iNID );
    	return iRetCode;
	}
    // end-of method createEmail()


	/**
	* search for emails
	* return not zero for fail, return 0 for success
	*/
	public int getEmailList(ABREDBConnection pConn, ArrayList aListObj, EmailInfoDTO aHeadObj, int iType){
		int iRetCode=0;
    	MethodInit("getEmailList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null email search object");
    		return -1;
    	}
    	EmailDBDAO aDBAObj = new EmailDBDAO();
    	iRetCode = aDBAObj.getDBListEmail( pConn, aListObj, aHeadObj, iType );
    	if(0 != iRetCode){
    		aDBAObj.copyErrObj(getErrObj());
    		ErrorsToLog();
    	}
    	return iRetCode;
	}
    // end-of method getOpportunityList()



	//=========> START Private Methods
    //=========> START Private Methods
    //=========> START Private Methods


	/**
	* generate an organization code key from a number
	*//*
	private String generateOrgCodeKey( int idnum ){
		if(idnum < 1) return null;
		String aszTemp="ORGNUM-" + idnum;
		return aszTemp;
	}
	// end-of method generateOrgCodeKey()

	/**
	* generate an opportunity code key from a number
	*//*
	private String generateOppCodeKey( int idnum ){
		if(idnum < 1) return null;
		String aszTemp="OPPNUM-" + idnum;
		return aszTemp;
	}
	// end-of method generateOppCodeKey()
*/

    //=========> END   Private Methods
    //=========> END   Private Methods
    //=========> END   Private Methods


}
