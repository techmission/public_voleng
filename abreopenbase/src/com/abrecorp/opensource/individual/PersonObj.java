package com.abrecorp.opensource.individual;

import com.abrecorp.opensource.base.ABREAppServerDef;
import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;

public class PersonObj extends ABREBase {

	/**
	** Constructor
	*/
	public PersonObj(){}

    /**
	* load user from database
	*/
	public int loadUserByOption(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType, String aszRailsDB ){
		int iRetCode=0;
    	MethodInit("loadUserByOption");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, iType, aszRailsDB );
		return iRetCode;
	}
    // end-of method loadUserByOption()

    /**
	* loadUserContactData from database
	*/
	public int loadUserContactData(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("loadUserContactData");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.loadUserContactDataFromDB( pConn, aHeadObj );
		return iRetCode;
	}
    // end-of method loadUserContactData()

    /**
	* get uid of provided email address and password from database
	*/
	public int getUIDFromEmail(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("loadUserByOption");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.getUIDFromEmailDB( pConn, aHeadObj );
		return iRetCode;
	}
    // end-of method getUIDFromEmail()

    /**
	* get uid of provided email address and password from database
	*/
	public int getUID(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	MethodInit("loadUserByOption");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.loadUIDFromDB( pConn, aHeadObj, iType );
		return iRetCode;
	}
    // end-of method loadUserByOption()

    /**
	* IsSessionIDInSystem in sessions table? with given ip address
	*/
	public int IsSessionIDInSystem(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("IsSessionIDInSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
        if( aHeadObj.getCookieAuthorize() == PersonInfoDTO.COOKIE_USER) { // a user authenticated through a cookie does not have any user data other than the user id
	    	aszTemp = aHeadObj.getSessionValue();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("Session ID Required  ");
	    		return -1;
	    	}
	    	aszTemp = aHeadObj.getSessionIP();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("Session IP Address Required  ");
	    		return -1;
	    	}
	    	iRetCode = aDBAObj.IsSessionIDInSystem( pConn, aHeadObj );
    	}

    	if(-1 == iRetCode) {
    		// aHeadObj.appendErrorMsg(" don't log in the user; session doesn't exist with given ip ");
            return -1;
    	} else if( (-222 == iRetCode) || (-555 == iRetCode)){
    		return iRetCode;
    	}

    	if(0 != iRetCode) return -1;
    	return 0;
	}
    // end-of method IsSessionIDInSystem()

    /**
	* deleteSessionIDFromSystem in sessions table? with given ip address
	*/
	public int deleteSessionIDFromSystem(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("deleteSessionIDFromSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
        if( aHeadObj.getCookieAuthorize() == PersonInfoDTO.COOKIE_USER) { // a user authenticated through a cookie does not have any user data other than the user id
	    	aszTemp = aHeadObj.getSessionValue();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("Session ID Required  ");
	    		return -1;
	    	}
	    	aszTemp = aHeadObj.getSessionIP();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("Session IP Address Required  ");
	    		return -1;
	    	}
	    	iRetCode = aDBAObj.deleteSessionIDFromSystem( pConn, aHeadObj );
    	}

    	if(-1 == iRetCode) {
    		// aHeadObj.appendErrorMsg(" don't log in the user; session doesn't exist with given ip ");
            return -1;
    	} else if( (-222 == iRetCode) || (-555 == iRetCode)){
    		return iRetCode;
    	}

    	if(0 != iRetCode) return -1;
    	return 0;
	}
    // end-of method deleteSessionIDFromSystem()
    /**
	* login a user
	*/
	public int updateLogin(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	MethodInit("updateLogin");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}

    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	String aszInitInternalComment = aHeadObj.getUSPInternalComment();
    	
    	// update the login timestamp for the user who just logged in
    	if(aHeadObj.getCookieAuthorize()==PersonInfoDTO.COOKIE_USER){
        	iRetCode = aDBAObj.updateDBUSPLogin( pConn, aHeadObj, PersonInfoDTO.COOKIE_USER );
    	}else{
        	iRetCode = aDBAObj.updateDBUSPLogin( pConn, aHeadObj, 0 );// 2009-08-24
    	}

    	return 0;
	}
    // end-of method updateLogin()


    /**
	* login a user
	*/
	public int loginUser(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType, String aszRailsDB ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loginUser");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}

    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	int iTemp=0;
    	String aszInputPassword = aHeadObj.getUSPPassword();
    	String aszInitInternalComment = aHeadObj.getUSPInternalComment();

        if(iType == PersonInfoDTO.LOADBY_UID){
	    	iTemp = aHeadObj.getUserUID();
	    	if(iTemp < 3){
	    		aHeadObj.appendErrorMsg("User ID Required  ");
	    		return -1;
	    	}
	    	if(!( aHeadObj.getCookieAuthorize() == PersonInfoDTO.COOKIE_USER) ){ // a user authenticated through a cookie does not have any user data other than the user id
		    	aszTemp = aHeadObj.getUSPEmail1Addr();
		    	if(aszTemp.length() < 3){
		    		aHeadObj.appendErrorMsg("User Email Address Required  ");
		    		return -1;
		    	}
	    	}
	    	iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_UID, aszRailsDB );
    	}else if(iType == PersonInfoDTO.LOADBY_FACEBOOK_UID){
    		if(! aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebookConnect") && ! aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkFacebookAccount")){
	    		aszTemp=aHeadObj.getTimestamp();
		    	if(aszTemp.length() < 3){
		    		aHeadObj.appendErrorMsg("Facebook timestamp Required  ");
		    		return -1;
		    	}
	    		aszTemp=aHeadObj.getFBapikey();
		    	if(aszTemp.length() < 3){
		    		aHeadObj.appendErrorMsg("Facebook API Key Required  ");
		    		return -1;
		    	}
    		}
	    	iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_FACEBOOK_UID, aszRailsDB );
    	}else if(iType == PersonInfoDTO.LOADBY_LINKEDIN_ID) {
    		iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_LINKEDIN_ID, aszRailsDB );
    	} else {
	    	aszTemp = aHeadObj.getUSPEmail1Addr();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("User Email Address Required  ");
	    		return -1;
	    	}
	    	aszTemp = aHeadObj.getUSPPassword();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("Password Required  ");
	    		return -1;
	    	}
	    	if(iType == PersonInfoDTO.LOADBY_AUTH_ADMIN_PWD){
		    	iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_AUTH_ADMIN_PWD, aszRailsDB );
	    	}else{
	    		iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_USERNAME, aszRailsDB );
	    	}
        }

    	if(-1 == iRetCode) {
    		aHeadObj.appendErrorMsg(" Email Address and/or Password not found in Database ");
            return -1;
    	} else if( (-222 == iRetCode) || (-555 == iRetCode)){
    		return iRetCode;
    	}

    	if(0 != iRetCode) return -1;
    	
    	// update the login timestamp for the user who just logged in
    	if(aHeadObj.getCookieAuthorize()==PersonInfoDTO.COOKIE_USER){
        	iRetCode = aDBAObj.updateDBUSPLogin( pConn, aHeadObj, PersonInfoDTO.COOKIE_USER );
    	}else{
        	iRetCode = aDBAObj.updateDBUSPLogin( pConn, aHeadObj, 0 );// 2009-08-24
    	}

    	return 0;
	}
    // end-of method loginUser()

    /**
	* login a user
	*/
	public int userLoggedIn(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loginUser");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.loadUserFromDB( pConn, aHeadObj, PersonInfoDTO.LOADBY_UIDEMAIL, aszRailsDB );
    	if(-1 == iRetCode) {
            return -1;
    	} else if( (-222 == iRetCode) || (-555 == iRetCode)){
    		return iRetCode;
    	}

    	if(0 != iRetCode) return -1;

    	return 0;
	}
    // end-of method loginUser()
	
	

    /**
	* updateResume
	*/
	public int updateResume(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("updateResume");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.updateResume( pConn, aHeadObj );
    	if(0 != iRetCode) return -1;
    	return 0;
	}
    // end-of method updateResume()

    /**
	* linkResume
	*/
	public int linkResume(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("linkResume");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.linkResume( pConn, aHeadObj );
    	if(0 != iRetCode) return -1;
    	return 0;
	}
    // end-of method linkResume()
	
	
    /**
	* does email address exist in system
	* return not zero = email address not found
	* return 0 = email found in system
	*/
	public int IsPersonCodeKeyInSystem(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("IsPersonCodeKeyInSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	aszTemp = aHeadObj.getUSPPercodekey();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("person codekey required  ");
    		return -1;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	return aDBAObj.IsPersonCodeKeyInSystem( pConn, aszTemp );
	}
    // end-of method IsPersonCodeKeyInSystem()


    /**
	* does email address exist in system
	* return not zero = email address not found
	* return 0 = email found in system
	*/
	public int IsEmailInSystem(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("IsEmailInSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("email required  ");
    		return -1;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	return aDBAObj.IsEmailAddrInSystem( pConn, aszTemp );
	}
    // end-of method IsEmailInSystem()

    /**
	* does username exist in system
	* return not zero = user-id not found
	* return 0 = user-id found in system
	*/
	public int IsUserIDInSystem(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0, iTemp=0;
    	String aszTemp=null ;
    	MethodInit("IsUserIDInSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("username required  ");
    		return -1;
    	}
    	iTemp = aHeadObj.getUserUID();
    	/*
    	// there now is a case where the uid would = 0 b/c it hasn't been generated yet, until it's inserted into users table
    	iTemp = aHeadObj.getUserUID(); 
    	if(iTemp < 1){
    		aHeadObj.appendErrorMsg("user id required  ");
    		return -1;
    	}
    	*/
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	return aDBAObj.IsUserIDInSystem( pConn, aszTemp, iTemp );
	}
    // end-of method IsUserIDInSystem()


	public String getUsernameByUID(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("getUsernameByUID");
    	if(null == aHeadObj) return "";
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return "";
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	return aDBAObj.getUsernameByUID( pConn, aHeadObj );
	}
    // end-of method getUsernameByUID()


	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHead(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB ){
System.out.println("inside updateIndividualHead in PersonObj");
		int iRetCode=0, iRetCode2=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHead");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	/* --> legacy id
    	if( aHeadObj.getUSPPersonNumber() < 1){
    		setErr("person number required");
    		return -2;
    	}
    	*/
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.updateDBUSP( pConn, aHeadObj, aszRailsDB );
    	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn, aHeadObj );
    	//iRetCode = aDBAObj.updateDBUSPEmail1( pConn, aHeadObj );// 2006-09-12
    	//iRetCode = aDBAObj.updateDBUSPUserName( pConn, aHeadObj );// 2008-08-22
    	// 2007-05-25   commented out below b/c username is tied to email now, so it's all updated in updateDBUSPEmail1()  
    	//iRetCode = aDBAObj.updateDBUSPUserName( pConn, aHeadObj );// 2006-09-12
    	// or could do 3 separate updateindivheads and call them three times in brlo
		return iRetCode;
	}
    // end-of method updateIndividualHead()

	public int updateIndividualPortal(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0, iRetCode2=0;
		String aszTemp=null;
    	MethodInit("updateIndividualPortal");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.updateIndividualPortalDB( pConn, aHeadObj );
		return iRetCode;
	}

//2006-09-12 -ali
	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadEmail(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadEmail");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	/* --> legacy id
    	if( aHeadObj.getUSPPersonNumber() < 1){
    		setErr("person number required");
    		return -2;
    	}
    	*/
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPEmail1( pConn, aHeadObj );// 2006-09-12

    	// or could do 3 separate updateindivheads and call them three times in brlo
		return iRetCode;
	}
    // end-of method updateIndividualHeadEmail()
	
	
	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadUserName(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadUserName");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	/* --> legacy id
    	if( aHeadObj.getUSPPersonNumber() < 1){
    		setErr("person number required");
    		return -2;
    	}
    	*/
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPUserName( pConn, aHeadObj );// 2006-09-12
    	// or could do 3 separate updateindivheads and call them three times in brlo
		return iRetCode;
	}
    // end-of method updateIndividualHeadUserName()
	
//2006-09-12 -ali
	
	
	
	
	
	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadPwd(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPwd");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	/* --> legacy id
    	if( aHeadObj.getUSPPersonNumber() < 1){
    		setErr("person number required");
    		return -2;
    	}
    	*/
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPPwd( pConn, aHeadObj );// 2006-09-12
    	// or could do 3 separate updateindivheads and call them three times in brlo
		return iRetCode;
	}
    // end-of method updateIndividualHeadPwd()
	//end ali 2006-09-12
	
	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadFacebookConnection(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadFacebookUID");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPFacebookConnection( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()

	/**
	* update person record in database
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadPersonality(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPersonality");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPPersonality( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()

	/**
	* update person record in database - only update interests and skills data
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadInterestsSkills(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPersonality");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPInterestsSkills( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()
	
	/**
	* update person record in database - only update learning interests data
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadLearningInterests(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadLearningInterests");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPLearningInterests( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()
	
	
	/**
	* update person record in database - only update select data based on the page the call came from
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadPersonalitySelectFields(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String personalityPage ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPersonalitySelectFields");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPPersonalitySelectFields( pConn, aHeadObj, personalityPage );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()
	
	/**
	* 
	*/
	public int updateIndividualHeadMinistryAreasTest(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPersonality");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPMinistryAreas( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadMinistryAreasTest()
	
	/**
	* update person record in database (update just their facebook published status)
	* return not zero = failed
	* return 0 = success
	*/
	public int updateIndividualHeadPersonalityPublished(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadPersonalityPublished");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPPersonalityPublished( pConn, aHeadObj );

		return iRetCode;
	}
    // end-of method updateIndividualHeadPersonality()
	
	/**
	* insert new person record into database
	* return not zero = failed
	* return 0 = success
	*/
	public int insertIndividualHead(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType, String aszRailsDB ){
		int iRetCode=0,iRetCode2=0;
		String aszTemp=null;
    	MethodInit("insertIndividualHead");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
System.out.print("createAccount1 - insertIndividualHead - iType is ");
    	
        switch( iType ){
	    	case PersonInfoDTO.CREATE_USER_PT1 :
System.out.println(" CREATE_USER_PT1");
	        	iRetCode = aDBAObj.insertUserDB( pConn, aHeadObj, aszRailsDB );
   	        	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn, aHeadObj );
	    		break;
	    	case PersonInfoDTO.CREATE_USER_CVINTERNAPPLIC :
System.out.println(" CREATE_USER_CVINTERNAPPLIC");
	        	iRetCode = aDBAObj.insertUserDataDB( pConn, aHeadObj, aszRailsDB );
	    		break;
	    	case PersonInfoDTO.CREATE_USER_PT2 :
System.out.println(" CREATE_USER_PT2");
	        	iRetCode = aDBAObj.insertUserDataDB( pConn, aHeadObj, aszRailsDB );
	        	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn, aHeadObj );
	    		break;
	    	case PersonInfoDTO.CREATE_USER_ORG_CONTACT :
System.out.println(" CREATE_USER_ORG_CONTACT");
	        	iRetCode = aDBAObj.insertContactUserDB( pConn, aHeadObj, aszRailsDB);
System.out.println(" done with insert   CREATE_USER_ORG_CONTACT");
	    		break;
	    	case PersonInfoDTO.CREATE_USER_FBAPP:
System.out.println(" CREATE_USER_FBAPP");
	    		iRetCode = aDBAObj.insertUserDB(pConn, aHeadObj, aszRailsDB);
	    		//iRetCode = aDBAObj.insertDBUSP(pConn, aHeadObj);
	    		if(iRetCode != 0){
	    			return iRetCode;
	    		}
	    		iRetCode = aDBAObj.insertUserDataDB(pConn, aHeadObj, aszRailsDB);
	    		iRetCode2 = aDBAObj.LocationTaxonomyDB(pConn, aHeadObj);
	    		break;
	    	case PersonInfoDTO.MIGRATE_DRUPAL_USER :
System.out.println(" MIGRATE_DRUPAL_USER");
	    		aHeadObj.setUSPInternalUserTypeComment("users");
	        	iRetCode = aDBAObj.insertUserDB( pConn, aHeadObj, aszRailsDB );
	    		break;
	        default:
System.out.println(" default");
	        	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	        	iRetCode = aDBAObj.insertDBUSP( pConn, aHeadObj, aszRailsDB );
	        	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn, aHeadObj );
	        	break;
	    }
System.out.println(" end of insertindivhead; iRetCode is "+iRetCode);        
		return iRetCode;
	}
    // end-of method insertIndividualHead()
	
	/**
	* generate a contact for the drupal user; needs to at least have a uprofile nid...............*******or does it???????????????????????????????
	*/
	public int generateContact(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("insertIndividualHead");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.generateContactFromDrupalDBUSP( pConn, aHeadObj );
		return iRetCode;
	}
    // end-of method insertIndividualHead()
	
	/**
	 * Add the Connection Source Taxonomy (for a user coming through Facebook Connect)
	 */
	public int linkFacebookConnectUser(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode = 0;
		String aszTemp = null;
		MethodInit("linkFacebookConnectUser");
		if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
    	iRetCode = aDBAObj.linkFacebookConnectUserDB( pConn, aHeadObj );
		return iRetCode;
		
	}

	/**
	* IsSessionIDInSystem in sessions table? with given ip address
	*/
	public int isFacebookUserInSystem(ABREDBConnection pConn, String facebookUID){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("isFacebookUserInSystem");
    	if(null == facebookUID) return -2;
    	if(null == pConn){
    		//aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
        
	    iRetCode = aDBAObj.isFacebookUserInSystem( pConn, facebookUID );

    	return iRetCode;
	}
    // end-of method isFacebookUserInSystem()

	//=========> START Private Methods
    //=========> START Private Methods
    //=========> START Private Methods


	/**
	* generate a person code key from a number
	*/
	private String generatePersonCodeKey( int idnum ){
		if(idnum < 1) return null;
		String aszTemp="PERKEY" + idnum;
		return aszTemp;
	}
	// end-of method generatePersonCodeKey()

	/**
	* 
	*/
	public int isLinkedInUserInSystem(ABREDBConnection pConn, String linkedInId, String aszRailsDB){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("isLinkedInUserInSystem");
    	if(null == linkedInId) return -2;
    	if(null == pConn){
    		//aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();
        
	    iRetCode = aDBAObj.isLinkedInUserInSystem( pConn, linkedInId, aszRailsDB );

    	return iRetCode;
	}
	
	public int updateIndividualHeadLinkedinConnection(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String railsDB) {
		int iRetCode=0;
		String aszTemp=null;
    	MethodInit("updateIndividualHeadLinkedinConnection");
    	if(null == aHeadObj) return -2;
    	if( aHeadObj.getUserUID() < 1){
    		setErr("user id required");
    		return -2;
    	}
    	
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	PersonDBDAO aDBAObj = new PersonDBDAO();

    	iRetCode = aDBAObj.updateDBUSPLinkedinConnection( pConn, aHeadObj, railsDB );

		return iRetCode;
	}
	
    //=========> END   Private Methods
    //=========> END   Private Methods
    //=========> END   Private Methods
private static final String aszOrganization = "Organization";

public int getQuestionnaireInstanceId(ABREDBConnection pConn,
		PersonInfoDTO person, OrgOpportunityInfoDTO opp, String aszRailsDB) {
	int iRetCode=0;
	MethodInit("getQuestionnaireInstanceId");
	if(null == person || null == opp) return -2;
	if(null == pConn){
		//aHeadObj.appendErrorMsg("null database connection ");
		return -2;
	}
	PersonDBDAO aDBAObj = new PersonDBDAO();
    
    iRetCode = aDBAObj.getQuestionnaireInstanceId( pConn, person, opp, aszRailsDB );

	return iRetCode;
}




}
