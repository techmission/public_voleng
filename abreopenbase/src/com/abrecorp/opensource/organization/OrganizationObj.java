package com.abrecorp.opensource.organization;

/**
* Created 2006-04-15
* Organization Interface Object
* @author David Marcillo
* ABRE Technology Corp.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.RequiredDocumentDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationDetailsInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;

public class OrganizationObj extends ABREBase {

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
	public OrganizationObj(){}

	/**
	* calculates distance between two Zip Codes in miles 
	* by passing the latitude and longitude coordinates found in the database
	* RadiusofEarth can be any unit of measure: 
	* statute miles, nautical miles, or kilometers. 
	* The radius of Earth is 6378.1 kilometers or 3963.1 statute miles.
	*/
	public int calcDistanceInMiles(double latA, double longA, double latB, double longB){
	  double theDistance = (Math.sin(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB)) +
			  Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) *
			  Math.cos(Math.toRadians(longA - longB)));
	  double dItem101 = Math.acos(theDistance);
	  double dItem202 = Math.toDegrees(dItem101); 
	  double dItem303 = dItem202 * 69.09;  
	  return (int)dItem303 ;
	}
	// end-of method calcDistanceInMiles()

	/*
    * To implement Square Proximity Geo Coding Search
    * input: latitude and longitude in degrees, plus radius in miles
	* return: latitude 1,2  and longitude 1,2 for search 
	*/
	public int getSearchLatLong( SearchParms aParm ){
		if(null == aParm) return -1;
		String aszLong1 = aParm.getInputLongitude1();
		if(aszLong1.length() < 1){
			return -1;
		}
		double dInputLong = convertToDouble( aszLong1);
		String aszLat1 = aParm.getInputLatitude1();
		if(aszLat1.length() < 1){
			return -1;
		}
		double dInputLat = convertToDouble( aszLat1);
		String aszRadius = aParm.getDistance();
		if(aszRadius.length() < 1){
			return -1;
		}
		double inputRadius = convertToDouble( aszRadius);
		double dCosOfINput = Math.cos(dInputLat * PI_DIV_BY_RAD );
		double dLongMiles = ( MILES_PER_LATITUDE * dCosOfINput );
		double dlat_degrees = ( inputRadius / MILES_PER_LATITUDE ) ; 
		double dlong_degrees = (inputRadius / dLongMiles ) ; 
		double dLat1 = dInputLat - dlat_degrees;
		aParm.setSearchLatitude1( "" + dLat1 );
		double dLat2 = dInputLat + dlat_degrees;
		aParm.setSearchLatitude2( "" + dLat2 );
		double dLong1 = dInputLong - dlong_degrees;
		aParm.setSearchLongitude1( "" + dLong1 );
		double dLong2 = dInputLong + dlong_degrees;
		aParm.setSearchLongitude2( "" + dLong2 );
		return 0;
	}
	
	// end-of method getLatLongFromPostal()
	public int getLatLongFromPostal( ABREDBConnection pConn, SearchParms aParm ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadOpportunityByURLID");
    	if(null == aParm){
    		setErr("null search object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.searchForZipCodeLatLongInDB( pConn, aParm, 0 );
    	return iRetCode;
	}
	// end-of method getLatLongFromPostal()


    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>


	/**
	* load an opportunity
	* return not zero for fail, return 0 for success
	*/
	public int loadOpportunityByID(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic, String aszUrlAlias , int iType, String aszURLAliasFormat, String aszRailsDB ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadOpportunityByID");
    	if(null == aHeadObj){
    		setErr("null Opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(iIDNum< 1 && aszUrlAlias.length()<1){
    		setErr("Opportunity number or URL alias required");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	String aszType=aHeadObj.getRequestType();
    	if(iType==OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS || iType==OrganizationInfoDTO.LOADBY_NID_FEED){
    		iRetCode = aDBAObj.loadFromFeedsDB( pConn, aHeadObj, iType, aszType, iIDNum, aszUrlAlias );
    	}else{
    		iRetCode = aDBAObj.loadOneOpportunityFromDB( pConn, aHeadObj, iType, iIDNum, aszUrlAlias, aszURLAliasFormat, aszRailsDB );
    	}
    	return iRetCode;
	}
    // end-of method loadOpportunityByID()

	/**
	* load an opportunity Legacy id
	* return not zero for fail, return 0 for success
	*/
	public int loadOpportunityByURLID(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, int iIDNum, int iPublic, String aszRailsDB ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadOpportunityByURLID");
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(iIDNum< 1){
    		setErr("opportunity number required");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	if (iPublic == 0) { // can view both PUBLISHED and UNPUBLISHED
        	iRetCode = aDBAObj.loadOneOpportunityFromDB( pConn, aHeadObj, OrganizationInfoDTO.LOADBY_ORGNUMBER, iIDNum, null, aszRailsDB );
    	} else if (iPublic == 1) { // can view both PUBLISHED and UNPUBLISHED
        	iRetCode = aDBAObj.loadOneOpportunityFromDB( pConn, aHeadObj, OrganizationInfoDTO.LOADBY_OPPURL_ID_W_ALIAS, iIDNum, null, aszRailsDB );
    	} else { // can only view PUBLISHED
        	iRetCode = aDBAObj.loadOneOpportunityFromDB( pConn, aHeadObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, iIDNum, null, aszRailsDB );
    	}
    	return iRetCode;
	}
    // end-of method loadOpportunityByIDLegacy()

	/**
	* delete opportunity
	* return not zero for fail, return 0 for success
	*/
	public int deleteOpportunity(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOpportunity");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOpportunityInDB( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method deleteOpportunity()

	/**
	* edit opportunity
	* return not zero for fail, return 0 for success
	*/
	public int editOpportunity(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, String aszRailsDB, String documentRoot ){
		int iRetCode=0,iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("editOpportunity");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	aszTemp = aHeadObj.getOPPTitle();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("name required  ");
    		return -1;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}/*
    	aszTemp = aHeadObj.getOPPOppcodekey();
    	if(aszTemp.length() < 2){
    		aszTemp = generateOppCodeKey( aHeadObj.getOPPOppNumber() );
    		aHeadObj.setOPPOppcodekey( aszTemp );
    	}*/
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.editOpportunityInDB( pConn, aHeadObj, aszRailsDB );
    	
//    	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn,  aHeadObj );
    	if(iRetCode != 0) return iRetCode;
    	
    	if(aHeadObj.getQuestionnaireType().equals("on_paper"))
    		iRetCode = writeDocumentFile(aHeadObj, documentRoot, "questionnaire" + File.separator, Long.toString(System.currentTimeMillis() / 1000L), aHeadObj.getQuestionnaireClientFile());
    	
    	if(aHeadObj.getRequiredDocumentsToAdd() != null) {
	    	for(RequiredDocumentDTO doc : aHeadObj.getRequiredDocumentsToAdd()) {
	    		if(doc.getClientFile() != null && doc.getClientFile().getFileName().length() > 0) {
	    			int ret = writeDocumentFile(aHeadObj, documentRoot, "other" + File.separator, Integer.toString(doc.getNid()), doc.getClientFile());
	    			if(iRetCode >= 0) iRetCode = ret;
	    		}
	    	}
    	}
    	
    	return iRetCode;
	}
    // end-of method editOpportunity()
	
	private int writeDocumentFile(OrgOpportunityInfoDTO aHeadObj, String documentRoot, String dir, String filename, FormFile file) {
		String extension;
		if(file.getContentType().equals("application/pdf")){
			extension = ".pdf";
		}else if(file.getContentType().equals("application/msword")){
			extension = ".doc";
		}else if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
			extension = ".docx";
		}else if(file.getContentType().equals("text/plain")){
			extension = ".txt";
		}else if(file.getContentType().equals("application/rtf")){
			extension = ".rtf";
		}else if(file.getContentType().equals("text/rtf")){
			extension = ".rtf";
		}else {
			return -3;
		}
		String serverFileName = filename + extension;
		File serverFile = new File(
	        (new File(documentRoot)).getParent() + File.separator +  
	  	    "files" + File.separator + 
	        "opportunity_documents" + File.separator +
	        aHeadObj.getOPPNID() + File.separator +
	        dir +
	        serverFileName
	    );
		
		serverFile.getParentFile().mkdirs();
		try {
			(new FileOutputStream(serverFile)).write(file.getFileData());
		} catch (FileNotFoundException e) {
			return -1;
		} catch (IOException e) {
			return -2;
		}
		return 0;
	}

	/**
	* create a new opportunity
	* return not zero for fail, return 0 for success
	*/
	public int createOpportunity(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, String aszRailsDB, String documentRoot ){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("createOpportunity");
System.out.println("inside call for object createOpportunity");
System.out.println("aHeadObj.getOPPUID()  is "+aHeadObj.getOPPUID() );		
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	aszTemp = aHeadObj.getOPPTitle();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("name required  ");
    		return -1;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization node id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertOpportunityIntoDB( pConn, aHeadObj, aszRailsDB );
System.out.println(" OrgObj file line 334   iRetCode is "+iRetCode);    	
//    	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn,  aHeadObj );
    	
    	if(iRetCode != 0) return iRetCode;
    	
    	if(aHeadObj.getQuestionnaireType().equals("on_paper"))
    		iRetCode = writeDocumentFile(aHeadObj, documentRoot, "questionnaire" + File.separator, Long.toString(System.currentTimeMillis() / 1000L), aHeadObj.getQuestionnaireClientFile());
    	
    	if(aHeadObj.getRequiredDocumentsToAdd() != null) {
	    	for(RequiredDocumentDTO doc : aHeadObj.getRequiredDocumentsToAdd()) {
	    		if(doc.getClientFile() != null && doc.getClientFile().getFileName().length() > 0) {
	    			int ret = writeDocumentFile(aHeadObj, documentRoot, "other" + File.separator, Integer.toString(doc.getNid()), doc.getClientFile());
	    			if(iRetCode >= 0) iRetCode = ret;
	    		}
	    	}
    	}
    	
    	return iRetCode;
	}
    // end-of method createOpportunity()

    /**
	* search for opportunities
	* return not zero for fail, return 0 for success
	*/
	public int getOpportunityList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int iType){
		int iRetCode=0;
    	MethodInit("getOpportunityList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOpportunityDBList( pConn, aListObj, aSrchParmObj, iType,false );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getOpportunityList()
	public int getOpportunityFeedsList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int iType){
		int iRetCode=0;
    	MethodInit("getOpportunityFeedsList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOpportunityDBList( pConn, aListObj, aSrchParmObj, iType, true );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getOpportunityFeedsList()

    /**
	* search for opportunities
	* return not zero for fail, return 0 for success
	*/
	public int insertSearchQuery(ABREDBConnection pConn, SearchParms aSrchParmObj, int iType){
		int iRetCode=0;
    	MethodInit("insertSearchQuery");
    	int iYieldedResults=0;
    	if(iType==0)	iYieldedResults=1;
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aSrchParmObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertSearchQuery( pConn, aSrchParmObj, iYieldedResults );
    	//don't log if there just are no results
    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
    	}
    	return iRetCode;
	}
    // end-of method insertSearchQuery()

    /**
	* search for searchForZipCodeLatLongInDB
	* return not zero for fail, return 0 for success
	*/
	public int searchForZipCodeLatLongInDB(ABREDBConnection pConn, SearchParms aSrchParmObj, int iUse){
		int iRetCode=0;
    	MethodInit("searchForZipCodeLatLongInDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aSrchParmObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.searchForZipCodeLatLongInDB( pConn, aSrchParmObj, iUse );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method searchForZipCodeLatLongInDB()

	
    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>




    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>

    /**
	* delete organization from organizationinfo table 
	*/
	public int deleteOrganizationFromDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOrganizationFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOrganizationFromDB( pConn, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteOrganizationFromDB()

	
    /**
	* delete organization from org_details table // ***** might want to delet org, then org_detail
	*/
	public int deleteOrganizationDetailsFromDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj){
		int iRetCode=0;
    	MethodInit("deleteOrganizationDetailsFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOrgDetailsFromDB( pConn, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteOrganizationDetailsFromDB()

	

    /**
	* get organization from organizationinfo table
	*/
	public int loadOrganizationFromDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, String aszRailsDB, int iType, String aszURLAliasFormat ){
		int iRetCode=0;
    	MethodInit("loadOrganizationFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	
    	String aszType=aHeadObj.getRequestType();
    	if(aszURLAliasFormat.equals("orgs/") || iType==OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS){
    		iRetCode = aDBAObj.loadOrganizationFromFeedsDB( pConn, aHeadObj, iType );
    	}else{
    		iRetCode = aDBAObj.loadOrganizationFromDB( pConn, aHeadObj, aszRailsDB, iType );
    	}
    	return iRetCode;
	}
	// end-of method loadOrganizationFromDB()

	
    /**
	* get organization details from organizationinfo table
	*/
	public int loadOrganizationDetFromDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	MethodInit("loadOrganizationDetFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.loadOrgDetailFromDB( pConn, aHeadObj, iType );
    	return iRetCode;
	}
	// end-of method loadOrganizationFromDB()

	

    /**
	* loadChildOpps for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int loadChildOpps( ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("loadChildOpps");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.loadChildOppsDB( pConn, aOrgInfoObj, aHeadObj );
    	return iRetCode;
	}
    // end-of method loadChildOpps()
	
	
	

    /**
	* loadFavoriteOpps for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int loadFavoriteOpps( ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, boolean feeds ){
		int iRetCode=0, iRetCode2=0;
    	MethodInit("loadFavoriteOpps");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.loadFavoriteOppsDB( pConn, aHeadObj, feeds );
    	return iRetCode;
	}
    // end-of method loadFavoriteOpps()
	
	

    /**
	* loadFavoriteOrgs for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int loadFavoriteOrgs( ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
    	MethodInit("loadFavoriteOrgs");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.loadFavoriteOrgsDB( pConn, aHeadObj, iType);
    	return iRetCode;
	}
    // end-of method loadFavoriteOrgs()
	


	

    /**
	* favoriteOpps for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int favoriteOpps( ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0, iRetCode2=0;
    	MethodInit("favoriteOpps");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.favoriteOppsDB( pConn, aOrgInfoObj, false, iType, aHeadObj );
    	// go through for feeds as well
    	iRetCode2 = aDBAObj.favoriteOppsDB( pConn, aOrgInfoObj, true, iType, aHeadObj );
    	return iRetCode;
	}
    // end-of method favoriteOpps()
	
	

    /**
	* favoriteOrgs for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int favoriteOrgs( ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("favoriteOrgs");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null search object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.favoriteOrgsDB( pConn, aOrgInfoObj, iType, aHeadObj);
    	return iRetCode;
	}
    // end-of method favoriteOrgs()
	

    /**
	* setNatlAssocOrgsAndOpps
	*/
	public int setNatlAssocOrgsAndOpps( ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, int iOrgAffilTID, AppCodeInfoDTO aHeadObj){
		int iRetCode=0;
    	MethodInit("setNatlAssocOrgsAndOpps");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr("null org object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null  object");
    		return -1;
    	}
    	if(0 == iOrgAffilTID){
    		setErr("needs an affiliation");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setNatlAssocOrgsAndOppsDB( pConn, aOrgInfoObj, iOrgAffilTID, aHeadObj);
    	return iRetCode;
	}
    // end-of method setNatlAssocOrgsAndOpps()
	
    //=== BEGIN OrgAdmins section
    //=== BEGIN OrgAdmins section
    //=== BEGIN OrgAdmins section

    /**
	* set an array in the org object of all the uids that are admins
	*/
	public int setOrgAdminListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0;
    	MethodInit("setOrgAdminListArray");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setOrgAdminDBListArray( pConn, aOrgInfoObj );
    	return iRetCode;
	}
    // end-of method setOrgAdminListArray()

    /**
	* search for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOrgAdminList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrgAdminList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrgAdminDBList( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOrgAdminList()

	
	/**
	* add additional owner to organization 
	* return not zero for fail, return 0 for success
	*/
	public int insertAddOrgAdmin(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("insertAddOrgAdmin");
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertAddOrgAdminIntDB( pConn, aHeadObj, aIndivObj );
    	return iRetCode;
	}
    // end-of method insertAddOrgAdmin()


    /**
	* delete organization administrator from organizationinfo table 
	*/
	public int deleteOrgAdmin(ABREDBConnection pConn,  PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOrgAdmin");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOrgAdminFromDB( pConn, aIndivObj, aHeadObj );
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
	* set an array in the org object of all the uids that are admins
	*/
	public int setOppsForOrgContactListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj, int iUID){
		int iRetCode=0;
    	MethodInit("setOppsForOrgContactListArray");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setOppsForOrgContactDBListArray( pConn, aOrgInfoObj, iUID );
    	return iRetCode;
	}
    // end-of method setOppsForOrgContactListArray()

    /**
	* set an array in the opp object of all the uids that are contacts
	*/
	public int setOrgContactListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0;
    	MethodInit("setOrgContactListArray");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setOrgContactDBListArray( pConn, aOrgInfoObj );
    	return iRetCode;
	}
    // end-of method setOrgContactListArray()
    /**
	* search for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOrgContactList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String aszUseCase){
		int iRetCode=0;
    	MethodInit("getOrgContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrgContactDBList( pConn, aListObj, aSrchParmObj,aszUseCase );
    	return iRetCode;
	}
    // end-of method getOrgContactList()


	/**
	* reset the updateORGAdmins( status for this user and opp - whether they receive emails or not
	*/
	public int updateORGAdmins( ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag , OrganizationInfoDTO aHeadObj){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("updateORGAdmins");
    	if(null == aListIdsAndEmailNotifyFlag){
    		setErr("null object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.updateORGAdminsDB( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	return iRetCode;
	}
    // end-of method updateORGAdmins()

	/**
	* reset the updateORGContacts( status for this user and opp - whether they receive emails or not
	*/
	public int updateORGContacts( ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag , OrganizationInfoDTO aHeadObj){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("updateORGContacts");
    	if(null == aListIdsAndEmailNotifyFlag){
    		setErr("null object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.updateORGContactsDB( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	return iRetCode;
	}
    // end-of method updateORGContacts()

    /**
	* delete organization CONTACT - includes administrators, but not limited to admins from organizationinfo table 
	*/
	public int deleteOrgContact(ABREDBConnection pConn,  PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOrgContact");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOrgContactFromDB( pConn, aIndivObj, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteOrgContact()
	
	
	/**
	* add additional contact to organization 
	* return not zero for fail, return 0 for success
	*/
	public int insertAddOrgContact(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("insertAddOrgContact");
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertAddOrgContactIntDB( pConn, aHeadObj, aIndivObj );
    	return iRetCode;
	}
    // end-of method insertAddOrgContact()

    //=== END OrgContacts section
    //=== END OrgContacts section
    //=== END OrgContacts section

      
      //=== BEGIN OppContacts section
      //=== BEGIN OppContacts section
      //=== BEGIN OppContacts section

    /**
	* set an array in the opp object of all the uids that are contacts
	*/
	public int setOppContactListArray(ABREDBConnection pConn,  OrgOpportunityInfoDTO aOpportObj){
		int iRetCode=0;
    	MethodInit("setOppContactListArray");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOpportObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setOppContactDBListArray( pConn, aOpportObj );
    	return iRetCode;
	}
    // end-of method setOppContactListArray()

    /**
	* search for opportunity's contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOppContactList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOppContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOppContactDBList( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOppContactList()

    /**
	* search for opportunity's email contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOppVolContactList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOppContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOppVolContactDBList( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOppContactList()

    /**
	* search for Org's email contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOrgContactList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrgContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrgContactDBList( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOrgContactList()


	
	/**
	* add additional volunteer contact to opportunity 
	* return not zero for fail, return 0 for success
	*/
	public int insertAddOppContact(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("insertAddOppContact");
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("opportunity id required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertAddOppContactIntDB( pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	return iRetCode;
	}
    // end-of method insertAddOppContact()

	
    /**
	* delete opportunity CONTACTS
	*/
	public int deleteOppContact(ABREDBConnection pConn,  PersonInfoDTO aIndivObj, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOppContact");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOppContactFromDB( pConn, aIndivObj, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteOppContact()
	


	/**
	* reset the Volunteer Contact status for this user and opp - whether they receive emails or not
	*/
	public int resetOppPrimaryContact(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("resetOppPrimaryContact");
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("opportunity required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.resetOppPrimaryContactFromDB( pConn, aIndivObj, aHeadObj, iIsVolunteerContact );
    	return iRetCode;
	}
    // end-of method resetOppPrimaryContact()


	/**
	* reset the Volunteer Contact status for this user and opp - whether they receive emails or not
	*/
	public int resetOppContact(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("resetOppContact");
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getOPPNID() < 3){
    		aHeadObj.appendErrorMsg("opportunity required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.resetOppContactFromDB( pConn, aIndivObj, aHeadObj, iIsVolunteerContact );
    	return iRetCode;
	}
    // end-of method resetOppContact()


	/**
	* reset the updateOPPContacts( status for this user and opp - whether they receive emails or not
	*/
	public int updateOPPContacts( ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag , OrgOpportunityInfoDTO aHeadObj){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("updateOPPContacts");
    	if(null == aListIdsAndEmailNotifyFlag){
    		setErr("null object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null opportunity object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.updateOPPContactsDB( pConn, aListIdsAndEmailNotifyFlag, aHeadObj );
    	return iRetCode;
	}
    // end-of method updateOPPContacts()

	
	
	
    //=== END OppContacts section
    //=== END OppContacts section
    //=== END OppContacts section
	
	/**
	* search for organization
	* return not zero for fail, return 0 for success
	*/
	public int getOrganizationList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrganizationList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrganizationDBList( pConn, aListObj, aSrchParmObj, false );
    	return iRetCode;
	}
    // end-of method getOrganizationList()
	public int getOrganizationFeedsList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrganizationFeedsList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrganizationDBList( pConn, aListObj, aSrchParmObj, true );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getOrganizationFeedsList()

    /**
	* search for organization
	* return not zero for fail, return 0 for success
	*/
	public String doesPortalExistForOrgs(ABREDBConnection pConn, ArrayList aListObj){
		String aszPortal="";
    	MethodInit("doesPortalExistForOrgs");
    	if(null == pConn){
    		setErr("null database object");
    		return "";
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return "";
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	aszPortal = aDBAObj.doesPortalExistForOrgs( pConn, aListObj);
    	return aszPortal;
	}
    // end-of method getOrganizationList()



	/**
	* edit organization
	* return not zero for fail, return 0 for success
	*/
	public int editOrganization(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("editOrganization");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("name required  ");
    		return -1;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.editOrganizationInDB( pConn, aHeadObj );
//    	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn,  aHeadObj );
    	return iRetCode;
	}
    // end-of method editOrganization()


	/**
	* getNatlAssociation
	*/
	public String getNatlAssociation(ABREDBConnection pConn, int iNatlAssocNID){
		int iRetCode=0;
		String aszNatlAssoc="";
    	String aszTemp=null ;
    	MethodInit("getNatlAssociation");
    	if(iNatlAssocNID < 1 ){
    		setErr("no affiliation to remove");
    		return null;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return null;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	aszNatlAssoc = aDBAObj.getNatlAssociationInDB( pConn, iNatlAssocNID );
    	return aszNatlAssoc;
	}
    // end-of method getNatlAssociation()


	/**
	* removeAssociation
	*/
	public int removeAssociation(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, String aszAffil, ArrayList aList ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("removeAssociation");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(aszAffil.length()<1){
    		setErr("no affiliation to remove");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.removeAssociationInDB( pConn, aHeadObj, aszAffil, aList );
    	return iRetCode;
	}
    // end-of method removeAssociation()

	/**
	* edit organization editPortal
	* return not zero for fail, return 0 for success
	*/
	public int editPortal(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("editPortal");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
	    aszTemp = aHeadObj.getPortalNameModified();
    	if(aszTemp.length() < 3){
	    	aHeadObj.appendErrorMsg("- Directory Name for your Portal required \n");
    		return -1;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.editPortalInDB( pConn, aHeadObj, iType );
    	return iRetCode;
	}
    // end-of method editPortal()

	/**
	* edit organization setFavoritesPortal - will need to favorite all child opps
	* return not zero for fail, return 0 for success
	*/
	public int setFavoritesPortal(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("setFavoritesPortal");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization number required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setFavoritesPortal( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method setFavoritesPortal()
	

	/**
	* edit organization details
	* return not zero for fail, return 0 for success
	*/
	public int editOrganizationDet(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("editOrganizationDet");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	/*
    	aszTemp = aHeadObj.getORGOnethirdP();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("Low Income response required,  ");
    		return -2;
    	}
    	*/
    	int temp = aHeadObj.getDETNumVolOrg();
    	if(temp<0){
    		aHeadObj.appendErrorMsg("Number of Volunteers Per Year required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.editOrgDetailInDB( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method editOrganizationDet()

	
	
	/**
	* create a new organization
	* return not zero for fail, return 0 for success
	*/
	public int createOrganization(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("createOrganization");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("name required  ");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertOrganizationIntDB( pConn, aHeadObj );
//    	iRetCode2 = aDBAObj.LocationTaxonomyDB( pConn,  aHeadObj );
    	return iRetCode;
	}
    // end-of method createOrganization()

	
	/**
	* create a new organization detail
	* return not zero for fail, return 0 for success
	*/
	public int createOrganizationDet(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, OrganizationDetailsInfoDTO aDetailObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("createOrganizationDet");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("name required  ");
    		return -1;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization nid required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertOrgDetailsIntDB( pConn, aDetailObj );
    	return iRetCode;
	}
    // end-of method createOrganizationDet()
	
	
	
	
    /**
	* does organization exist in system
	* return not zero = email address not found
	* return 0 = email found in system
	*/
	public int IsOrganizationInSystem(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("IsOrganizationInSystem");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		aHeadObj.appendErrorMsg("null database connection ");
    		return -2;
    	}
    	aszTemp = aHeadObj.getORGOrgName();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("person codekey required  ");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	return aDBAObj.IsOrganizationInSystem( pConn, aHeadObj , 222 );
	}
    // end-of method IsOrganizationInSystem()


    /**
	* get pathauto rules for url alias generation
	* return not zero for fail, return 0 for success
	*/
	public int getPathAutoURLAliasInfo( ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("getPathAutoURLAliasInfo");
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getPathAutoURLAliasInfo( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method getPathAutoURLAliasInfo()

    /**
	* check to see if the URL alias already exists; basically need an array of existing URLs of 'URLAlias%'
	* return not zero for fail, return 0 for success
	*/
	public int checkPathAutoURLAliasExisting( ABREDBConnection pConn, ArrayList aListObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("checkPathAutoURLAliasExisting");
    	if(null == aListObj) return -2;
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.checkPathAutoURLAliasExisting( pConn, aListObj, aHeadObj );
    	return iRetCode;
	}
    // end-of method getPathAutoURLAliasInfo()


    /**
	* check to see if the URL alias already exists; basically need an array of existing URLs of 'URLAlias%'
	* return not zero for fail, return 0 for success
	*/
	public int checkPathAutoPortalNameExisting( ABREDBConnection pConn, ArrayList aListObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("checkPathAutoPortalNameExisting");
    	if(null == aListObj) return -2;
    	if(null == aHeadObj) return -2;
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.checkPathAutoPortalNameExisting( pConn, aListObj, aHeadObj );
    	return iRetCode;
	}
    // end-of method checkPathAutoPortalNameExisting()


    /**
	* check to see if the URL alias already exists; basically need an array of existing URLs of 'URLAlias%'
	* return not zero for fail, return 0 for success
	*/
	public int PortalName( ABREDBConnection pConn, int iTid, int iNID ){
		int iRetCode=0;
    	MethodInit("PortalName");
    	if(1 == iTid) return -2;
    	if(1 > iNID) return -2;
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.PortalName( pConn, iTid, iNID );
    	return iRetCode;
	}
    // end-of method checkPathAutoPortalNameExisting()



	//=========> START Private Methods
    //=========> START Private Methods
    //=========> START Private Methods



	/**
	 * BEGIN LEGACY Methods
	 * BEGIN LEGACY Methods
	 * BEGIN LEGACY Methods
	 * BEGIN LEGACY Methods
	 */
	
	/**
	* add additional owner to organization LEGACY
	* return not zero for fail, return 0 for success
	*/
	public int insertAddOrgContactInt_LEGACY(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("insertAddOrgContact");
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.insertAddOrgContactIntDB_LEGACY( pConn, aHeadObj, aIndivObj, iIsVolunteerContact );
    	return iRetCode;
	}
    // end-of method insertAddOrgContactIntDB_LEGACY()

    /**
	* delete organization contact from organizationinfo table 
	*/
	public int deleteOrgContactFromDB_LEGACY(ABREDBConnection pConn,  PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("deleteOrgContactFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.deleteOrgContactFromDB_LEGACY( pConn, aIndivObj, aHeadObj );
    	return iRetCode;
	}
	// end-of method deleteOrgContactFromDB()
	
	/**
	* reset the Volunteer Contact status for this user and org - whether they receive emails or not
	*/
	public int resetOrgContact_LEGACY(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
    	String aszTemp=null ;
    	int iTemp = 0;
    	MethodInit("resetOrgContact");
    	if(null == aHeadObj){
    		setErr("null organization object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(aHeadObj.getORGNID() < 3){
    		aHeadObj.appendErrorMsg("organization required  ");
    		return -1;
    	}
    	if( aIndivObj.getUserUID() < 1){
    		setErr("new owner user id required");
    		return -2;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.resetOrgContactFromDB_LEGACY( pConn, aIndivObj, aHeadObj, iIsVolunteerContact );
    	return iRetCode;
	}
    // end-of method resetOrgContact_LEGACY()
    /**
	* search for organization's contacts LEGACY
	* return not zero for fail, return 0 for success
	*/
	public int getOrganizationContactList_LEGACY(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrganizationContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrganizationContactDBList_LEGACY( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOrganizationContactList_LEGACY()
    /**
	* search for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int getOrgVolContactList_LEGACY(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getOrgVolContactList");
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
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.getOrgVolContactDBList_LEGACY( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getOrganizationContactList()
    /**
	* search for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int checkOrgContact_LEGACY(ABREDBConnection pConn, int iOrgNID, int iUID){
		int iRetCode=0;
    	MethodInit("checkOrgContact");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(0 == iOrgNID){
    		setErr(" An organization id must be provided. ");
    		return -1;
    	}
    	if(0 == iUID){
    		setErr(" A user id must be provided. ");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.checkOrgContactDB_LEGACY( pConn, iOrgNID, iUID );
    	return iRetCode;
	}
    // end-of method checkOrgContactDB()

    /**
	* search for organization's contacts
	* return not zero for fail, return 0 for success
	*/
	public int setOrgPrimaryContact_LEGACY(ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, PersonInfoDTO aContactPersonObj){
		int iRetCode=0;
    	MethodInit("setOrgPrimaryContact");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aOrgInfoObj){
    		setErr(" An organization must be provided. ");
    		return -1;
    	}
    	if(null == aContactPersonObj){
    		setErr(" A user must be provided. ");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	iRetCode = aDBAObj.setOrgPrimaryContactDB_LEGACY( pConn, aOrgInfoObj, aContactPersonObj );
    	return iRetCode;
	}
    // end-of method setOrgPrimaryContact(()

	/**
	 * END LEGACY Methods
	 * END LEGACY Methods
	 * END LEGACY Methods
	 * END LEGACY Methods
	 */


	/**
	* generate an organization code key from a number
	*/
	private String generateOrgCodeKey( int idnum ){
		if(idnum < 1) return null;
		String aszTemp="ORGNUM-" + idnum;
		return aszTemp;
	}
	// end-of method generateOrgCodeKey()

	/**
	* generate an opportunity code key from a number
	*/
	private String generateOppCodeKey( int idnum ){
		if(idnum < 1) return null;
		String aszTemp="OPPNUM-" + idnum;
		return aszTemp;
	}
	// end-of method generateOppCodeKey()

	public int loadRequiredDocumentByNid(ABREDBConnection pConn,
			RequiredDocumentDTO doc, int nid) {
    	MethodInit("loadRequiredDocumentFromDB");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == doc){
    		setErr("null document object");
    		return -1;
    	}
    	OrganizationDBDAO aDBAObj = new OrganizationDBDAO();
    	return aDBAObj.loadRequiredDocumentFromDB( pConn, doc, nid);
	}


    //=========> END   Private Methods
    //=========> END   Private Methods
    //=========> END   Private Methods


}
