package com.abrecorp.opensource.voleng.brlayer;

/**
* Created 2006-04-15
* Business Rules Layer Object BRLO
* For Searching through volunteer opportunities
* @author David Marcillo
* ABRE Technology Corp.
*/

//data transfer objects
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
/*
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
*/
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.abrecorp.opensource.dataobj.*;
//base objects
import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.organization.OrganizationObj;
import com.abrecorp.opensource.appcodes.ApplicationCodesObj;

import com.abrecorp.opensource.servlet.SolrItem;
import com.abrecorp.opensource.struts.ActionHelper;
import com.maxmind.geoip.Location;



public class searchOpportunitiesBLO extends ABREBaseBRLO {

	/**
	* Constructor
	*/
	public searchOpportunitiesBLO(){}


    //=== START opportunity search ===>
    //=== START opportunity search ===>
    //=== START opportunity search ===>
	

    /**
	* get opportunity list for an administrator
	*/
	public int getOpportunityList( ArrayList aList, SearchParms aSParm ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;
    	aSParm.setSearchType( OrganizationInfoDTO.LOADBY_UID_ADMIN );

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSParm,0 );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOpportunityList()

    /**
	* get opportunity list for basic search options
	*/
	public int searchForOpportunitiesInDB( ArrayList aList, SearchParms aSParm ){
		int iRetCode=0;
		String aszSQLStatement="", aszPostalCode=null, aszDistance=null, OppType=null ;
		ABREDBConnection pConn=null;
    	MethodInit("searchForOpportunitiesInDB");
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;
		aszPostalCode = aSParm.getPostalCode();
		if(aszPostalCode.length() > 1){
	    	aszPostalCode = aszPostalCode.substring(0,2);
		}
    	aSParm.setPostalCode(aszPostalCode);

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSParm,0 );
    	
    	// save the search query for logging and reporting
    	int iRetCode2 = 0;
    	int iResultsSize=aList.size();
    	aSParm.setSearchResultsCount(iResultsSize);
    	iRetCode2 = aOrganizationObj.insertSearchQuery(pConn, aSParm, iRetCode);
    	
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	        	aOrganizationObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method searchForOpportunitiesInDB()

    /**
	* get searchForZipCodeLatLongInDB list for basic search options
	*/
	public int searchForZipCodeLatLongInDB( SearchParms aSParm, int iUse ){
		int iRetCode=0;
		String aszPostalCode=null,aszNOTPostalCode=null ;
		ABREDBConnection pConn=null;
    	MethodInit("searchForZipCodeLatLongInDB");
    	if(null == aSParm) return -1;
		aszPostalCode = aSParm.getPostalCode();
		aszNOTPostalCode = aSParm.getNotLclPostalCode();
		if (aszPostalCode.length() < 1 && aszNOTPostalCode.length() < 1){
			return 0; // no postal code to check against to grab lat/long.... doesn't really matter; just skip it
		}

    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	iRetCode = aOrganizationObj.searchForZipCodeLatLongInDB(pConn, aSParm, iUse );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	        	aOrganizationObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method searchForZipCodeLatLongInDB()

	public int advanceSearchOpportunities( ArrayList aList, SearchParms aSParm, int iType, boolean latest, AppCodeInfoDTO aHeadObj ){
		if(latest==true)aSParm.setSearchLatest(true);
		return advanceSearchOpportunities( aList, aSParm, iType, latest, false, aHeadObj );
	}
	public int advanceSearchOpportunities( ArrayList aList, SearchParms aSParm, boolean feeds, AppCodeInfoDTO aHeadObj ){
		return advanceSearchOpportunities( aList, aSParm, 0, false, feeds, aHeadObj );
	}
	public int advanceSearchOpportunities( ArrayList aList, SearchParms aSParm, AppCodeInfoDTO aHeadObj ){
		return advanceSearchOpportunities( aList, aSParm, 0, false, false, aHeadObj );
	}
	
	/**
	* get opportunity list for advance search options
	*/
	public int advanceSearchOpportunities( ArrayList aList, SearchParms aSParm, int iType, boolean latest, boolean feeds, AppCodeInfoDTO aHeadObj ){
		int iAmeriCorps=-1, iMedInsur=-1, iRoomBoard=-1, iStipend=-1, iTransport=-1, iDenomAffil=-1,
			iFrequency, iFocusAreaKid=-1, iFocusAreaTeen=-1, iFocusAreaSenior=-1, iFocusAreaGroup=-1, iFocusAreaFamily=-1, 
			iRegion,iRetCode=0, 
			iMaxGroup=0, iMinGroup=0, iOppType, iOrgAffil1, iOrgAffil2, iOrgAffil3, iOrgAffil4, iOrgAffil5, iOrgNid=0,
			iServiceArea1, iServiceArea2, iServiceArea3, iServiceAreasTIDs=0, iServiceAreasNumbers=0, iServiceAreasLength=0,
			iSkills, iSkills2, iSkills3, iSkillsTIDs=0, iSkillsNumbers=0, iSkillsLength=0,iFaithTID=0,
			iTripLength, iWorkStudy=0, 
			iStart=0, iEnd=0;
		String aszSQLStatement="",aszFromSQLStatement="",aszWhereSQLStatement="",
			aszSQLStmnt="",aszFromSQLStmnt="",aszWhereSQLStmnt="", // used for for case where it's just the administrator and want to grab orgnid, etc
			OppType=null,aszCauseTopics=null, aszDenomAffil=null, 
			aszPostalCode=null, aszDistance=null, aszCity=null, aszState=null, aszCountry=null, 
			aszAmeriCorps=null, aszMedInsur=null, aszRoomBoard=null, aszStipend=null, aszTransport=null, 
			aszFocusArea=null, aszFocusArea2=null, aszFocusArea3=null, aszFocusArea4=null, aszFocusArea5=null,
			aszSkillsTIDs=null,aszServiceAreasTIDs=null,
			aszReqCode=null,
			aszOtherAffils=null, aszOtherAffil=null, aszOtherAffil2=null, aszOtherAffil3=null, aszOtherAffil4=null, aszOtherAffil5=null, 
			aszOrgName=null, aszLocalAffil=null, aszWorkStudy=null;
		boolean bNotUS=false;
		ABREDBConnection pConn=null;
    	MethodInit("advanceSearchOpportunities");
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;

		String mainDB=aszDrupalDB;
		if( feeds==true ){
			mainDB=aszFeedsDB;
		}

		aSParm.setDistanceSearched(false);

		// add Looking For Array
		if ( aSParm.getUSPLookingForArray()!=null ){
			if ( aSParm.getUSPLookingForArray().length > 0 || aSParm.getLookingForTIDs().length() > 0 ){
				if(iType==aSParm.LATEST_LOCAL){
					aSParm.setOPPPositionTypeTID(iLocal);
				}
				if(iType==aSParm.LATEST_VIRTUAL){
					aSParm.setOPPPositionTypeTID(iVirtual);
				}
				if(iType==aSParm.LATEST_GRP_FAMILY){
					aSParm.setGreatForGroupTID(iGroup);
					aSParm.setGreatForFamilyTID(iFamily);
				}
				if(iType==aSParm.LATEST_STM){
					aSParm.setOPPPositionTypeTID(iShortTerm);
				}
				if(iType==aSParm.LATEST_SUM_INTRN){
					aSParm.setOPPPositionTypeTID(iShortTerm);
				}
				if(iType==aSParm.LATEST_INTRN){
					aSParm.setOPPPositionTypeTID(iShortTerm);
				}
				if(iType==aSParm.LATEST_WORK_STUDY){
					aSParm.setOPPWorkStudyTID(iWorkStudy);
				}
				if(iType==aSParm.LATEST_BOARD){
					aSParm.setServiceAreasTIDs(""+iOppBoard);
					aSParm.setSkillsTIDs(null); //??? for Board members, should it have their skills, or no?
				}
				if(iType==aSParm.LATEST_TRAINING){
					//aSParm.setOPPPositionTypeTID(iLocal);
				}
			}else{
//				aSParm.setOPPPositionTypeTID(iLocal);
			}
		}else{
//			aSParm.setOPPPositionTypeTID(iLocal);
		}
    	// set up the From and Where statements for the generic taxonomies to join one instance of the simulated materialized view of all taxonomies
		iOppType = aSParm.getOPPPositionTypeTID();
		OppType = aSParm.getOppVolType();
		// if nothing is selected, make the search not specify (should return all)
		if(OppType.length() < 2 && iOppType < 1){
		} else {
			OppType = OppType.toUpperCase();
			if(OppType.length() > 1){
				if(OppType.contains("LOCAL"))iOppType = iLocal;
				else if(OppType.contains("MISSIONS"))iOppType = iShortTerm;
				else if(OppType.contains("VIRTUAL"))iOppType = iVirtual;
			}
			if (iOppType > 0){
					aszWhereSQLStatement = aszWhereSQLStatement + 
						" AND position_type_tid=" + iOppType + "  \n";
			}
		}
		
	    //=== START ADDRESS ===>
	    //=== START ADDRESS ===>
	    //=== START ADDRESS ===>
		
		aszDistance = aSParm.getDistance();
		String aszDistanceSearch="";
		if(	aszDistance.equalsIgnoreCase("Country") || 
    			aszDistance.equalsIgnoreCase("Virtual")
    	){
			aszDistanceSearch="";
    	}else{
    		if(aszDistance.equalsIgnoreCase("5")){
    			aszDistanceSearch = "5";
    		}else if(aszDistance.equalsIgnoreCase("20")){
    			aszDistanceSearch = "20";
        	}else if(aszDistance.equalsIgnoreCase("City")){
    			aszDistanceSearch = "25";
        	}else if(aszDistance.equalsIgnoreCase("Region")){
    			aszDistanceSearch = "50";
        	}else{
    			aszDistanceSearch = "25";
        	}
    	}
		// new distance search, if given lat/long (eventually can just do a select query of the postal code from techmi5_voleng.zip_codes db table
		// to grab the lat/long; for now, just use it if it's straight inputted
		String aszLatitude = aSParm.getSearchLatitude1();
		String aszLongitude = aSParm.getSearchLongitude1();
		aszPostalCode = replacesinglequote(aSParm.getPostalCode());
		
		
		
		
		
		// ?? maybe do a separate query first - if lat/long is NOT set, but PostalCode IS, maybe do a query on the PostalCode and look up its lat/long
		// then work with that - ?????????????????????
		OrganizationObj aOrganizationObj;
		if(aszLatitude.length()==0 && aszLongitude.length()==0){
			if(aszPostalCode.length()==5){
				aOrganizationObj = new OrganizationObj();
		    	pConn = getDBConn();
		    	iRetCode = aOrganizationObj.getLatLongFromPostal(pConn, aSParm );
		    	if(null != pConn) pConn.free();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		if( (aszLatitude.length()>0 || aszLongitude.length()>0) && aszDistanceSearch.length()>0){
			aSParm.setDistanceSearched(true);
			aszSQLStatement += ", (3956 * (2 * ASIN(SQRT(POWER(SIN((("+aszLatitude+"-Latitude)*0.017453293)/2),2) " +
				"+COS("+aszLatitude+"*0.017453293) *COS(Latitude*0.017453293) " +
				"*POWER(SIN((("+aszLongitude+"-Longitude)*0.017453293)/2),2))))) AS distance";
			aszWhereSQLStatement += "\n       AND " +
				"(3956 * (2 * ASIN(SQRT(" +
				"POWER(SIN((("+aszLatitude+"-Latitude)*0.017453293)/2),2) +" +
				"COS("+aszLatitude+"*0.017453293) *" +
				"COS(Latitude*0.017453293) *" +
				"POWER(SIN((("+aszLongitude+"-Longitude)*0.017453293)/2),2)" +
					")))) <= "+ aszDistanceSearch +" ";//ORDER BY Distance";

		}else{// do old version of search being substring
			
			aszSQLStatement += ", -1 as distance ";
			
			// old school postal searching
			// Postal Code
			if(aszPostalCode.length() > 4){
	    		if(	aszDistance.equalsIgnoreCase("Country") || 
	    			aszDistance.equalsIgnoreCase("Virtual")
	    		){
	    			iRetCode=0;
	    		}else{
	    			if(aszDistance.equalsIgnoreCase("5")){
	        			aszPostalCode = aszPostalCode.substring(0,4);
	    			}else if(aszDistance.equalsIgnoreCase("20")){
	        			aszPostalCode = aszPostalCode.substring(0,3);
	        		}else if(aszDistance.equalsIgnoreCase("City")){
	        			aszPostalCode = aszPostalCode.substring(0,2);
	        		}else if(aszDistance.equalsIgnoreCase("Region")){
	        			aszPostalCode = aszPostalCode.substring(0,1); 
	        		}
	    			aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code like ('"+ aszPostalCode + "%') \n";
	    		}
			}else if(aszPostalCode.length() > 0){ 
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code like ('"+ aszPostalCode + "%') \n";
			}else{
				iRetCode=0;			
			}
			aSParm.setPostalCode(aszPostalCode);
		}
		if(aSParm.getNotLclPostalCode().length()>0){
			if( (aSParm.getNotLclSearchLongitude1().length()>0 || aSParm.getNotLclSearchLatitude1().length()>0) ){
				aSParm.setDistanceSearched(true);
				if(aszSQLStatement.contains("distance")){}else{
					aszSQLStatement += ", (3956 * (2 * ASIN(SQRT(POWER(SIN((("+aSParm.getNotLclSearchLatitude1()+"-Latitude)*0.017453293)/2),2) " +
					"+COS("+aSParm.getNotLclSearchLatitude1()+"*0.017453293) *COS(Latitude*0.017453293) " +
					"*POWER(SIN((("+aSParm.getNotLclSearchLongitude1()+"-Longitude)*0.017453293)/2),2))))) AS distance";
				}
				aszWhereSQLStatement += "\n       AND " +
					"(3956 * (2 * ASIN(SQRT(" +
					"POWER(SIN((("+aSParm.getNotLclSearchLatitude1()+"-Latitude)*0.017453293)/2),2) +" +
					//"COS(42.289793*0.017453293) *" +
					"COS("+aSParm.getNotLclSearchLatitude1()+"*0.017453293) *" +
					"COS(Latitude*0.017453293) *" +
					"POWER(SIN((("+aSParm.getNotLclSearchLongitude1()+"-Longitude)*0.017453293)/2),2)" +
						")))) > 20";//+ aszDistanceSearch +" ";//ORDER BY Distance";  NOT LOCAL

			}else{// do old version of search being substring
				if(aszSQLStatement.contains("distance")){}else{
					aszSQLStatement += ", -1 as distance ";
				}				
				// old school postal searching
				// Postal Code
				aszPostalCode = replacesinglequote(aSParm.getNotLclPostalCode());
				if(aszPostalCode.length() > 4){
		    		if(	aszDistance.equalsIgnoreCase("Country") || 
		    			aszDistance.equalsIgnoreCase("Virtual")
		    		){
		    			iRetCode=0;
		    		}else{
		    			if(aszDistance.equalsIgnoreCase("5")){
		        			aszPostalCode = aszPostalCode.substring(0,4);
		    			}else if(aszDistance.equalsIgnoreCase("20")){
		        			aszPostalCode = aszPostalCode.substring(0,3);
		        		}else if(aszDistance.equalsIgnoreCase("City")){
		        			aszPostalCode = aszPostalCode.substring(0,2);
		        		}else if(aszDistance.equalsIgnoreCase("Region")){
		        			aszPostalCode = aszPostalCode.substring(0,1); 
		        		}
		    			aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code NOT like ('"+ aszPostalCode + "%') \n";
		    		}
				}else if(aszPostalCode.length() > 0){ 
					aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code NOT like ('"+ aszPostalCode + "%') \n";
				}else{
					iRetCode=0;			
				}
				aSParm.setNotLclPostalCode(aszPostalCode);
			}
		}
		
		
		
		
		//do location query stuff unless myrestultstab or virtual
		if(! (
				aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTab")		|| 
				aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTabLatest")	|| 
				iOppType == iVirtual
			) 
		){
			// City
			aszCity = replacesinglequote(aSParm.getCity());
			if (aszCity.length() > 2){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND city LIKE '" +  aszCity + "' \n";			
			}
			// State
			aszState = aSParm.getState();
			if (aszState.length() > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND province LIKE '" +  aszState + "' \n";			
			}
			// Country
			aszCountry = aSParm.getCountry();
			if (aszCountry.length() > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND country LIKE '" +  aszCountry + "' \n";			
			}
			// NOTCountry
			aszCountry = aSParm.getNotCountry();
			if (aszCountry.length() > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND country NOT LIKE '" +  aszCountry + "' \n";			
			}
	
			// REGION
			iRegion = aSParm.getRegionTID();
			if (iRegion > 1 ){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (region_tid=" + iRegion + ")  \n";
			}
		}	
		
		//=== END ADDRESS ===>
	    //=== END ADDRESS ===>
	    //=== END ADDRESS ===>

		// (short-term missions field) - drupal's trip length (not frequency)
		iTripLength = aSParm.getOPPTripLengthTID();
		if (iTripLength > 1 ){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (trip_length_tid=" + iTripLength + ")  \n";
		}

		// frequency - one time or ongoing
		iFrequency = aSParm.getOPPFrequencyTID();
		if (iFrequency > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (frequency_tid=" + iFrequency + ")  \n";
		}

		// Room & Board Provided (short-term missions field) - 
		aszRoomBoard = aSParm.getOPPRoomBoard();
		iRoomBoard = aSParm.getOPPRoomBoardTID();
		if (aszRoomBoard.length() > 1 || iRoomBoard > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (benefits_offered_tid=" + iRoomBoard + " OR benefits_offered LIKE '" + aszRoomBoard + "')  \n";
		}
		// Stipend Provided (short-term missions field) - 
		aszStipend = aSParm.getOPPStipend();
		iStipend = aSParm.getOPPStipendTID();
		if (aszStipend.length() > 1 || iStipend > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (benefits_offered_tid=" + iStipend + " OR benefits_offered LIKE '" + aszStipend + "')  \n";
		}
		// Medical Insurance Provided (short-term missions field) - 
		aszMedInsur = aSParm.getOPPMedInsur();
		iMedInsur = aSParm.getOPPMedInsurTID();
		if (aszMedInsur.length() > 1 || iMedInsur > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (benefits_offered_tid=" + iMedInsur + " OR benefits_offered LIKE '" + aszMedInsur + "')  \n";
		}
		// Transportation Provided (short-term missions field) - 
		aszTransport = aSParm.getOPPTransport();
		iTransport = aSParm.getOPPTransportTID();
		if (aszTransport.length() > 1 || iTransport > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (benefits_offered_tid=" + iTransport + " OR benefits_offered LIKE '" + aszTransport + "')  \n";
		}
		// AmeriCorps (short-term missions field) - 
		aszAmeriCorps = aSParm.getOPPAmeriCorps();
		iAmeriCorps = aSParm.getOPPAmeriCorpsTID();
		if (aszAmeriCorps.length() > 1 || iAmeriCorps > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (benefits_offered_tid=" + iAmeriCorps + " OR benefits_offered LIKE '" + aszAmeriCorps + "')  \n";
		}

		// is Work Study provided? 
		aszWorkStudy = aSParm.getOPPWorkStudy();
		iWorkStudy = aSParm.getOPPWorkStudyTID();
		if (aszWorkStudy.length() > 1 || iWorkStudy > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (work_study_tid=" + iWorkStudy + " OR work_study LIKE '" + aszWorkStudy + "')  \n";
		}
		
		//Cause Topics
		// process the comma delimited string of tids and join to cause/topics
		aszCauseTopics = aSParm.getCauseTopics();
		if(aszCauseTopics.length() > 1){
			aszSQLStatement = aszSQLStatement + ",\n       CauseTermData.name cause_topic";
			aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node Cause, " + mainDB + "term_data CauseTermData  ";
			aszWhereSQLStatement = aszWhereSQLStatement + " AND ( (Cause.vid=opp.vid ) AND (Cause.tid = CauseTermData.tid ) " +
				" AND (CauseTermData.vid= " + vidCauseTopic + " ) ) AND ( Cause.tid IN ( " + aszCauseTopics + " ) )\n";
		}

		//Focus Area - kid; Focus Area 2 - senior; Focus Area 3 - teen; Focus Area 4 - group; Focus Area 5 - family
		if (	(aSParm.getFocusArea().length() > 1) || (aSParm.getFocusArea2().length() > 1) || 
				(aSParm.getFocusArea3().length() > 1) || (aSParm.getFocusArea4().length() > 1) || 
				(aSParm.getFocusArea5().length() > 1)
		){
			aszFocusArea = replacesinglequote(aSParm.getFocusArea()).toUpperCase();
			aszFocusArea2 = replacesinglequote(aSParm.getFocusArea2()).toUpperCase();
			aszFocusArea3 = replacesinglequote(aSParm.getFocusArea3()).toUpperCase();
			aszFocusArea4 = replacesinglequote(aSParm.getFocusArea4()).toUpperCase();
			aszFocusArea5 = replacesinglequote(aSParm.getFocusArea5()).toUpperCase();
			if(aszFocusArea.contains("KIDS")) 		iFocusAreaKid = iKid;
			if(aszFocusArea2.contains("SENIOR")) 	iFocusAreaSenior = iSenior;
			if(aszFocusArea3.contains("TEEN")) 		iFocusAreaTeen = iTeen;
			if(aszFocusArea4.contains("GROUP")) 	iFocusAreaGroup = iGroup;
			if(aszFocusArea5.contains("FAMILY")) 	iFocusAreaFamily = iFamily;
		}
		if (	(aSParm.getGreatForKidTID() >1 ) || (aSParm.getGreatForSeniorTID() >1 ) || (aSParm.getGreatForTeenTID() >1 ) || 
				(aSParm.getGreatForGroupTID() >1 ) || (aSParm.getGreatForFamilyTID() >1 ) 
		){
			if(iFocusAreaKid < 1) 		iFocusAreaKid = aSParm.getGreatForKidTID();
			if(iFocusAreaSenior < 1)	iFocusAreaSenior = aSParm.getGreatForSeniorTID();
			if(iFocusAreaTeen < 1)		iFocusAreaTeen = aSParm.getGreatForTeenTID();
			if(iFocusAreaGroup < 1)		iFocusAreaGroup = aSParm.getGreatForGroupTID();
			if(iFocusAreaFamily < 1)	iFocusAreaFamily = aSParm.getGreatForFamilyTID();
		}
		if(iFocusAreaKid==0) iFocusAreaKid= -1;
		if(iFocusAreaSenior==0) iFocusAreaSenior=-1;
		if(iFocusAreaTeen==0) iFocusAreaTeen=-1;
		if(iFocusAreaGroup==0) iFocusAreaGroup=-1;
		if(iFocusAreaFamily==0) iFocusAreaFamily=-1;
		if(iFocusAreaKid > 1 || iFocusAreaSenior > 1 || iFocusAreaTeen > 1 || iFocusAreaGroup > 1 || iFocusAreaFamily > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND ( " +
					"\n       great_for_tid=" + iFocusAreaKid + " OR " +
					"\n       great_for_tid=" + iFocusAreaSenior + " OR " +
					"\n       great_for_tid=" + iFocusAreaTeen + " OR " +
					"\n       great_for_tid=" + iFocusAreaGroup +  " OR " +
					"\n       great_for_tid=" + iFocusAreaFamily + 
				"\n)  \n";
		}

		aszReqCode = aSParm.getReqCode().toUpperCase();
		if (aszReqCode.equalsIgnoreCase("NO")){
			//iNonFaith=9397; -> set at bottom for a private int for use in full code file
			// if aSParm.getReqCode() is No, then this is a non-faith search; should actually be searching in new taxonomy that we generate: Faith
			// 	private int iNoFaithActivity = 21996, iFaithBased = 21997, iFaithFilled = 21998;
			aszWhereSQLStatement = aszWhereSQLStatement + // include No Faith, or Faith-Based (but not Faith-Filled)
				" AND (" +
				"\n       (faith_tid=" + iNoFaithActivity + " OR faith_tid=" + iFaithBased + ") OR  " +
				"\n       (faith_tid IS NULL AND faith_or_nofaith_tid=" + iNonFaith + " )  OR  " +
				"\n       (faith_tid IS NULL AND faith_or_nofaith_tid IS NULL AND faith_req LIKE '" + aszReqCode + "')  " +
				"\n)  \n";
		}else if (aszReqCode.equalsIgnoreCase("Yes")){
			aszWhereSQLStatement = aszWhereSQLStatement + // include No Faith, or Faith-Based (but not Faith-Filled)
				" AND (" +
				"\n       (faith_tid=" + iFaithFilled + ") OR  " +
				"\n       (faith_tid IS NULL AND faith_or_nofaith_tid=" + iNonFaith + " )  OR  " +
				"\n       (faith_tid IS NULL AND faith_or_nofaith_tid IS NULL AND faith_req LIKE '" + aszReqCode + "')  " +
				"\n)  \n";
		}else{
			iFaithTID = aSParm.getFaithTID();
			if (iFaithTID==iFaithFilled){
				// 	private int iNoFaithActivity = 21996, iFaithBased = 21997, iFaithFilled = 21998;
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (faith_tid=" + iFaithFilled;
				// Country NOT US; this combined would show churchvol linked results (faithfilled +/or notUS)
				bNotUS = aSParm.getNotUS();
				if (bNotUS==true){
					aszWhereSQLStatement+= " OR country NOT LIKE 'us' ";			
				}
				aszWhereSQLStatement+=")\n";
			}else if (iFaithTID==iFaithBased){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (faith_tid=" + iFaithBased + ")\n";
			}else if (iFaithTID==iNoFaithActivity){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (faith_tid=" + iNoFaithActivity + ")\n";
			}
		}

		// Denominational Affiliation
		aszDenomAffil = replacesinglequote(aSParm.getDenomAffil());
		iDenomAffil = aSParm.getDenomAffilTID();
		if (aszDenomAffil.length() > 1 && iDenomAffil < 1 ){
			aszDenomAffil = aszDenomAffil.toUpperCase();
			aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node DenomAffil, " + mainDB + "term_data DenomAffilTermData "; 
			aszWhereSQLStatement = aszWhereSQLStatement + " AND (DenomAffil.vid=opp.vid ) AND (DenomAffil.tid = DenomAffilTermData.tid ) " +
				" AND (DenomAffilTermData.vid)=" +  vidDenomAffil + 
				" AND  ( DenomAffilTermData.name LIKE '" +  aszDenomAffil + "' OR DenomAffil.tid = " + iDenomAffil + " ) \n";			
		}
		
		// Other (Organizational) Affiliation 
		if(aHeadObj.getPortalOrgAffilTID()>0 && aSParm.getAssocOnly()==true){
			iOrgAffil1=aHeadObj.getPortalOrgAffilTID();
			if (iOrgAffil1 > 1){
				aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node OrgAffil, " + mainDB + "term_data OrgAffilTermData "; 
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND ( " +
						"     OrgAffil.vid=opp.vid  " +
						" AND OrgAffil.tid = OrgAffilTermData.tid  " +
						" AND OrgAffilTermData.vid = " + vidOrgAffil + " " +
						" AND OrgAffil.tid ="+ iOrgAffil1 + "   " +
					") \n";
			}
		}else{
			aszOtherAffils = "";
			aszOtherAffil = replacesinglequote(aSParm.getPartner()).toUpperCase();
			aszOtherAffil2 = replacesinglequote(aSParm.getPartner2()).toUpperCase();
			aszOtherAffil3 = replacesinglequote(aSParm.getPartner3()).toUpperCase();
			aszOtherAffil4 = replacesinglequote(aSParm.getPartner4()).toUpperCase();
			aszOtherAffil5 = replacesinglequote(aSParm.getPartner5()).toUpperCase();
			iOrgAffil1 = aSParm.getOrgAffil1TID();
			iOrgAffil2 = aSParm.getOrgAffil2TID();
			iOrgAffil3 = aSParm.getOrgAffil3TID();
			iOrgAffil4 = aSParm.getOrgAffil4TID();
			iOrgAffil5 = aSParm.getOrgAffil5TID();
			if( ( iOrgAffil1 > 1 ) || ( iOrgAffil2 > 1 ) || ( iOrgAffil3 > 1 ) || ( iOrgAffil4 > 1 ) || ( iOrgAffil5 > 1 ) || 
					(aszOtherAffil.length() > 3) || (aszOtherAffil2.length() > 3) || (aszOtherAffil3.length() > 3) || 
					(aszOtherAffil4.length() > 3) || (aszOtherAffil5.length() > 3)){
				aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node OrgAffil, " + mainDB + "term_data OrgAffilTermData "; 
				aszWhereSQLStatement = aszWhereSQLStatement + " AND ( (OrgAffil.vid=opp.vid ) AND (OrgAffil.tid = OrgAffilTermData.tid ) " +
						" AND (OrgAffilTermData.vid = " + vidOrgAffil + ") AND " +
					"(" ;
				if ( aszOtherAffil.length() > 3 || iOrgAffil1 > 1 ){
					if (iOrgAffil1 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OrgAffil.tid ="+ iOrgAffil1 + "  ";
						aszOtherAffils = aszOtherAffils + iOrgAffil1;
					}else{
						aszWhereSQLStatement = aszWhereSQLStatement + " (OrgAffilTermData.name LIKE ('%"+ aszOtherAffil + "%') ) ";
					}
				}
				if ( aszOtherAffil2.length() > 3 || iOrgAffil2 > 1 ){
					if ( aszOtherAffil.length() > 3 || iOrgAffil1 > 1 ){
						aszWhereSQLStatement = aszWhereSQLStatement + " OR ";
						aszOtherAffils = aszOtherAffils + ",";
					}
					if (iOrgAffil2 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OrgAffil.tid ="+ iOrgAffil2 + "  ";
						aszOtherAffils = aszOtherAffils + iOrgAffil2;
					}else{
						aszWhereSQLStatement = aszWhereSQLStatement + " (OrgAffilTermData.name LIKE ('%"+ aszOtherAffil2 + "%') ) ";
					}
				}
				if ( aszOtherAffil3.length() > 3 || iOrgAffil3 > 1 ){
					if ( aszOtherAffil.length() > 3 || iOrgAffil1 > 1 || aszOtherAffil2.length() > 3 || iOrgAffil2 > 1 ){
						aszWhereSQLStatement = aszWhereSQLStatement + " OR ";
						aszOtherAffils = aszOtherAffils + ",";
					}
					if (iOrgAffil3 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OrgAffil.tid ="+ iOrgAffil3 + "  ";
						aszOtherAffils = aszOtherAffils + iOrgAffil3;
					}else{
						aszWhereSQLStatement = aszWhereSQLStatement + " (OrgAffilTermData.name LIKE ('%"+ aszOtherAffil3 + "%') ) ";
					}
				}
				if ( aszOtherAffil4.length() > 3 || iOrgAffil4 > 1 ){
					if ( aszOtherAffil.length() > 3 || iOrgAffil1 > 1 || aszOtherAffil2.length() > 3 || iOrgAffil2 > 1
							 || aszOtherAffil3.length() > 3 || iOrgAffil3 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OR ";
						aszOtherAffils = aszOtherAffils + ",";
					}
					if (iOrgAffil4 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OrgAffil.tid ="+ iOrgAffil4 + "  ";
						aszOtherAffils = aszOtherAffils + iOrgAffil4;
					}else{
						aszWhereSQLStatement = aszWhereSQLStatement + " (OrgAffilTermData.name LIKE ('%"+ aszOtherAffil4 + "%') ) ";
					}
				}
				if ( aszOtherAffil5.length() > 3 || iOrgAffil5 > 1 ){
					if ( aszOtherAffil.length() > 3 || iOrgAffil1 > 1 || aszOtherAffil2.length() > 3 || iOrgAffil2 > 1
							|| aszOtherAffil3.length() > 3 || iOrgAffil3 > 1 || aszOtherAffil4.length() > 3 || iOrgAffil4 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OR ";
						aszOtherAffils = aszOtherAffils + ",";
					}
					if (iOrgAffil5 > 1){
						aszWhereSQLStatement = aszWhereSQLStatement + " OrgAffil.tid ="+ iOrgAffil5 + "  ";
						aszOtherAffils = aszOtherAffils + iOrgAffil5;
					}else{
						aszWhereSQLStatement = aszWhereSQLStatement + " (OrgAffilTermData.name LIKE ('%"+ aszOtherAffil5 + "%') ) ";
					}
				}
				aszWhereSQLStatement = aszWhereSQLStatement + ") ) \n";
			}
		}
		
	//if(aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTab") && iOppType == iVirtual){
		//skip the location query stuff
		int iCity=0, iCountry=0;
		if(iOppType == iVirtual){
		}else{
			// City
			iCity = aSParm.getCityTID();
			if (iCity > 1 ){
				aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node City, " + mainDB + "term_data CityTermData "; 
				aszWhereSQLStatement = aszWhereSQLStatement + " AND City.vid=opp.vid  AND City.tid = CityTermData.tid  " +
						" AND CityTermData.vid=" +  vidCity + 
						" AND  " + " City.tid = " + iCity + "  \n";			
				if(aSParm.getNotLclCityTID() > 0){
					aszWhereSQLStatement = aszWhereSQLStatement + " AND  " + " City.tid != " + aSParm.getNotLclCityTID() + "  \n";			
				}
			}else if(aSParm.getNotLclCityTID() > 0){
				iCity = aSParm.getNotLclCityTID();
				if (iCity > 1 ){
					aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node City, " + mainDB + "term_data CityTermData "; 
					aszWhereSQLStatement = aszWhereSQLStatement + " AND City.vid=opp.vid  AND City.tid = CityTermData.tid  " +
							" AND CityTermData.vid=" +  vidCity + 
							" AND  " + " City.tid != " + iCity + "  \n";			
				}
			}
			// Country
			iCountry = aSParm.getCountryTID();
			if (iCountry > 1 ){
				aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node Country, " + mainDB + "term_data CountryTermData "; 
				aszWhereSQLStatement = aszWhereSQLStatement + " AND Country.vid=opp.vid  AND Country.tid = CountryTermData.tid  " +
						" AND CountryTermData.vid=" +  vidCountry + 
						" AND  " + " Country.tid = " + iCountry + "  \n";			
				if(aSParm.getNotLclCntryTID() > 0){
					aszWhereSQLStatement = aszWhereSQLStatement + " AND  " + " Country.tid != " + aSParm.getNotLclCntryTID() + "  \n";			
				}
			}else if(aSParm.getNotLclCntryTID() > 0){
				iCountry = aSParm.getNotLclCntryTID();
				if (iCountry > 1 ){
					aszFromSQLStatement = aszFromSQLStatement + ", " + mainDB + "term_node Country, " + mainDB + "term_data CountryTermData "; 
					aszWhereSQLStatement = aszWhereSQLStatement + " AND Country.vid=opp.vid  AND Country.tid = CountryTermData.tid  " +
							" AND CountryTermData.vid=" +  vidCountry + 
							" AND  " + " Country.tid != " + iCountry + "  \n";			
				}
			}
		}			

		// do an OR between Skills OR Service Areas
		if(aSParm.getSearchRequestType().equalsIgnoreCase("myResultsTab") || aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTab")){
			aszSkillsTIDs = aSParm.getSkillsTIDs();
			iSkillsLength = aszSkillsTIDs.length();
			aszServiceAreasTIDs = aSParm.getServiceAreasTIDs();
			iServiceAreasLength = aszServiceAreasTIDs.length();

			iStart=0; iEnd=0;
	        if(aszSkillsTIDs.endsWith(","))aszSkillsTIDs = aszSkillsTIDs.substring(0, iSkillsLength-1);
	        if(aszServiceAreasTIDs.endsWith(","))aszServiceAreasTIDs = aszServiceAreasTIDs.substring(0, iServiceAreasLength-1);
	        
	        if(iSkillsLength > 0 || iServiceAreasLength > 0){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND ( service_areas_tid IN(" + aszServiceAreasTIDs + ") OR skills_tid IN(" + aszSkillsTIDs + ") ) \n";
	        }
		}else{
			// assume user came through search form - Skills AND Service Areas
			aszSkillsTIDs = aSParm.getSkillsTIDs();
			iSkills = aSParm.getOPPSkills1TID();
			iSkills2 = aSParm.getOPPSkills2TID();
			iSkills3 = aSParm.getOPPSkills3TID();		
			if (iSkills > 0){
				if (aszSkillsTIDs.length() > 1 ){
					if(!(aszSkillsTIDs.endsWith(",")))		aszSkillsTIDs = aszSkillsTIDs + ",";
				}
				aszSkillsTIDs = aszSkillsTIDs + iSkills ;
			}
			if (iSkills2 > 0){
				if (aszSkillsTIDs.length() > 1 ){
					if(!(aszSkillsTIDs.endsWith(",")))		aszSkillsTIDs = aszSkillsTIDs + ",";
				}
				aszSkillsTIDs = aszSkillsTIDs + iSkills2 ;
			}
			if (iSkills3 > 0){
				if (aszSkillsTIDs.length() > 1 ){
					if(!(aszSkillsTIDs.endsWith(",")))		aszSkillsTIDs = aszSkillsTIDs + ",";
				}
				aszSkillsTIDs = aszSkillsTIDs + iSkills3 ;
			}
			//aszTaxonomyTIDs = "4793,7536,4790,4796,4794";
			iSkillsLength = aszSkillsTIDs.length();
			iSkillsNumbers = aszSkillsTIDs.replaceAll("[^,]","").length();
			if (iSkillsLength > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (skills_tid IN(" + aszSkillsTIDs + ")  )\n";
			}

			// generic Service Areas
			aszServiceAreasTIDs = aSParm.getServiceAreasTIDs();
			//aszTaxonomyTIDs = "4793,7536,4790,4796,4794";
			iServiceArea1 = aSParm.getOPPServiceArea1TID();
			iServiceArea2 = aSParm.getOPPServiceArea2TID();
			iServiceArea3 = aSParm.getOPPServiceArea3TID();		
			// ???????????? in this next part, should there be a trailing comma, or no? I don't remember...
			if (iServiceArea1 > 0){
				if (aszServiceAreasTIDs.length() > 1 ){
					if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
				}
				aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea1 ;
			}
			if (iServiceArea2 > 0){
				if (aszServiceAreasTIDs.length() > 1 ){
					if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
				}
				aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea2 ;
			}
			if (iServiceArea3 > 0){
				if (aszServiceAreasTIDs.length() > 1 ){
					if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
				}
				aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea3 ;
			}
			iServiceAreasLength = aszServiceAreasTIDs.length();
			iServiceAreasNumbers = aszServiceAreasTIDs.replaceAll("[^,]","").length();
			if (iServiceAreasLength > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (service_areas_tid IN(" + aszServiceAreasTIDs + ")  )\n";
			}
		}


		// Organization Name
		aszOrgName = replacesinglequote(aSParm.getOrgName());
		if (aszOrgName.length() > 2){
			aszOrgName = aszOrgName.toUpperCase();
			aszWhereSQLStatement = aszWhereSQLStatement + "       AND org_name LIKE '%" +  aszOrgName + "%' \n"; //"       AND opp.field_volopp_org_name_value LIKE '%" +  aszOrgName + "%' \n";			
		}

		// Organization NID
        // see if this is going through a portal or not
        int iPortalUID = aSParm.getPortalUID();
        if( iPortalUID < 1 || iPortalUID == iMeetTheNeedUID){
			iOrgNid = aSParm.getNID();
			if (iOrgNid > 1){
				aszWhereSQLStmnt = aszWhereSQLStmnt + "       AND opp.field_volopp_org_reference_nid = " +  iOrgNid + " \n";			
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND org_nid = " +  iOrgNid + " \n"; 			
			}
        }
		// short-term missions - group size minimum 
		iMinGroup = aSParm.getMinSize();
		if (iMinGroup > 0){
			aszWhereSQLStatement  = aszWhereSQLStatement  + "       AND group_min >= " +  iMinGroup + " \n"; 
		}
		
		// short-term missions - group size maximum
		iMaxGroup = aSParm.getMaxSize();
		if (iMaxGroup > 0){
			aszWhereSQLStatement  = aszWhereSQLStatement  + "       AND group_max <= " +  iMaxGroup + " \n"; 
		}
		// local affiliation - no longer stripped out to a separate table; is now a field in opp: field_volopp_local_affil_value
		aszLocalAffil = replacesinglequote(aSParm.getLocalAffil());
		if (aszLocalAffil.length() > 2){
			aszWhereSQLStatement  = aszWhereSQLStatement  + "       AND local_affil LIKE '" +  aszLocalAffil + "' \n"; 
		}
		
		
		aSParm.setSearchSQLStatement( aszSQLStatement );
		aSParm.setSearchFromSQLStatement( aszFromSQLStatement );
		aSParm.setSearchWhereSQLStatement( aszWhereSQLStatement );

		aSParm.setSearchSQLStmnt( aszSQLStmnt );
		aSParm.setSearchFromSQLStmnt( aszFromSQLStmnt );
		aSParm.setSearchWhereSQLStmnt( aszWhereSQLStmnt );
		
		aOrganizationObj = new OrganizationObj();
//		OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	if(feeds==false){
    		aSParm.setSearchedFeeds(0);
    		iRetCode = aOrganizationObj.getOpportunityList(pConn, aList, aSParm, iType );
    	}else{
    		aSParm.setSearchedFeeds(1);
    		iRetCode = aOrganizationObj.getOpportunityFeedsList(pConn, aList, aSParm, iType );
    	}
    	
    	// save the search query for logging and reporting
    	int iRetCode2 = 0;
    	int iResultsSize=aList.size();
    	aSParm.setSearchResultsCount(iResultsSize);
    	if(feeds==false){ 
    		if(aSParm.getPortalName().length()>0 && aSParm.getNID()<1) {
    			String aszSearchForm = aSParm.getSearchForm(); 
    			if(iType==aSParm.PORTAL_OWN_LIST){
	    			aszSearchForm += "_Portal_Own"; 
	    		}else if(iType==aSParm.PORTAL_OTHERFAV_LIST) { 
	    			if(aszSearchForm.endsWith("_Portal_Own")) {
	    				aszSearchForm = aszSearchForm.substring(0, aszSearchForm.length()-11);
	    			}
	    			aszSearchForm += "_Portal_OtherFavorites"; 
	    		}
    			aSParm.setSearchForm(aszSearchForm);
    		}
    		if(! aSParm.getUserAgent().contains("bot")){ // dont' log searches from bots b/c it gets too large in the data
    			iRetCode2 = aOrganizationObj.insertSearchQuery(pConn, aSParm, iRetCode);
    		}
    		if(aSParm.getPortalName().length()>0 && aSParm.getNID()<1) { 
    			String aszSearchForm = aSParm.getSearchForm(); 
    			if(aszSearchForm.endsWith("_Portal_Own")) {
    				aszSearchForm = aszSearchForm.substring(0, aszSearchForm.length()-11);
    			}
    			if(aszSearchForm.endsWith("_Portal_OtherFavorites")) {
    				aszSearchForm = aszSearchForm.substring(0, aszSearchForm.length()-22);
    			}
    			aSParm.setSearchForm(aszSearchForm);
    		}
    	}
    	
    	if(0 != iRetCode){
    		if(-2 != iRetCode){
            	aOrganizationObj.copyErrObj(getErrObj());
        		//ErrorsToLog();
    		}
    	}
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method advanceSearchOpportunities()

	//====== END Search For Christian Volunteer Opportunities ===>
	//====== END Search For Christian Volunteer Opportunities ===>
	//====== END Search For Christian Volunteer Opportunities ===>
	
	
	
	//====== START Search For Christian Volunteer Organizations ===>
	//====== START Search For Christian Volunteer Organizations ===>
	//====== START Search For Christian Volunteer Organizations ===>

    /**
	* get organization list for advance search options - ali - 2006-11-27
	*/
	public int advanceSearchOrganizations( ArrayList aList, SearchParms aSParm ){
        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
        return advanceSearchOrganizations( aList, aSParm, aHeadObj, false );
	}
	public int advanceSearchOrganizations( ArrayList aList, SearchParms aSParm, AppCodeInfoDTO aHeadObj, boolean feeds ){
		int iRetCode=0, iOrgAffil1, iOrgAffil2, iOrgAffil3, iOrgAffil4, iOrgAffil5, iDenomAffil=-1, iMemberType=-1, iProgramType=-1, iRegion=-1, iLenBeg=-1,
			iStart=0, iEnd=0;
		String aszSQLStatement="",aszFromSQLStatement="",aszWhereSQLStatement="",aszTmpSQL=null,
			aszSQLStmnt="",aszFromSQLStmnt="",aszWhereSQLStmnt="", // used for for normal taxonomy, but using the materialized view for the node
			aszPostalCode=null, aszDistance=null, OppType=null, aszProgramType=null,
			aszCity=null, aszState=null, aszProv=null, aszCountry=null, aszRegion=null,
			aszReqCode=null, aszDenomAffil=null, aszOtherAffils=null,
			aszOtherAffil=null, aszOtherAffil2=null, aszOtherAffil3=null, aszOtherAffil4=null, aszOtherAffil5=null, 
			aszOrgName=null, aszLocalAffil=null, aszMemberType=null;
		ABREDBConnection pConn=null;
    	MethodInit("advanceSearchOrganizations");
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;
    	
		// Postal Code
		aszPostalCode = replacesinglequote(aSParm.getPostalCode());
		if(aszPostalCode.length() > 4){
    		aszDistance = aSParm.getDistance();
    		if(	aszDistance.equalsIgnoreCase("Country") || 
    			aszDistance.equalsIgnoreCase("Virtual")
    		){
    			iRetCode=0;
    		}else{
    			if(aszDistance.equalsIgnoreCase("5")){
        			aszPostalCode = aszPostalCode.substring(0,4);
    			}else if(aszDistance.equalsIgnoreCase("20")){
        			aszPostalCode = aszPostalCode.substring(0,3);
        		}else if(aszDistance.equalsIgnoreCase("City")){
        			aszPostalCode = aszPostalCode.substring(0,2);
        		}else if(aszDistance.equalsIgnoreCase("Region")){
        			aszPostalCode = aszPostalCode.substring(0,1); 
        		}
    			aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code like ('"+ aszPostalCode + "%') \n";
    		}
		}else if(aszPostalCode.length() > 0){ 
			aszWhereSQLStatement = aszWhereSQLStatement + "       AND postal_code like ('"+ aszPostalCode + "%') \n";
		}else{
			iRetCode=0;			
		}
		aSParm.setPostalCode(aszPostalCode);

		//do location query stuff unless myrestultstab or virtual
		if(! (
				aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTab")		|| 
				aSParm.getSearchRequestType().equalsIgnoreCase("myResultsLatestTabLatest")	
			) 
		){
			// City
			aszCity = replacesinglequote(aSParm.getCity());
			if (aszCity.length() > 2){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND city LIKE '" +  aszCity + "' \n";			
			}
			// State
			aszState = aSParm.getState();
			if (aszState.length() > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND province LIKE '" +  aszState + "' \n";			
			}
			// Country
			aszCountry = aSParm.getCountry();
			if (aszCountry.length() > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + "       AND country LIKE '" +  aszCountry + "' \n";			
			}
	
			// REGION
			iRegion = aSParm.getRegionTID();
			if (iRegion > 1 ){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (region_tid=" + iRegion + ")  \n";
			}
		}	

		//=== END ADDRESS ===>
	    //=== END ADDRESS ===>
	    //=== END ADDRESS ===>
		
		// Organization Name
		aszOrgName = replacesinglequote(aSParm.getOrgName());
		if (aszOrgName.length() > 2){
			aszOrgName = aszOrgName.toUpperCase();
			aszWhereSQLStatement = aszWhereSQLStatement + "       AND title LIKE '%" +  aszOrgName + "%' \n"; 			
		}

		// Membership type (Non-Profit/Church/etc)
		iMemberType = aSParm.getORGMembertypeTID();
		aszMemberType = aSParm.getORGMembertype();
		if (iMemberType > 1 ){
			aszWhereSQLStatement = aszWhereSQLStatement + 
			" AND (member_type_tid=" + iMemberType + " OR member_type LIKE'" + aszMemberType + "' )  \n";
		}

		// Denominational Affiliation
		aszDenomAffil = replacesinglequote(aSParm.getDenomAffil());
		iDenomAffil = aSParm.getDenomAffilTID();
		if (iDenomAffil > 1 ){//&& aszDenomAffil.length() > 1 ){
//			aszDenomAffil = aszDenomAffil.toUpperCase();
			//aszWhereSQLStatement = aszWhereSQLStatement + 
			//" AND (denom_tid=" + iDenomAffil + " OR denom LIKE '" + aszDenomAffil + "')  \n";
			aszWhereSQLStatement = aszWhereSQLStatement + 
			" AND (denom_tid=" + iDenomAffil + " )  \n";
		}
		
		if(aHeadObj.getPortalOrgAffilTID()>0 && aSParm.getAssocOnly()==true){
			iOrgAffil1=aHeadObj.getPortalOrgAffilTID();
			if (iOrgAffil1 > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (	org_affil_tid =" + iOrgAffil1 + " )\n";
			}
		}else{
			// Other (Organizational) Affiliation 
			aszOtherAffils = "";
			String aszOrgAffils = aSParm.getOrgAffils();
			String aszOrgAffilTIDs = aSParm.getOrgAffilTIDs();
			aszOtherAffil = replacesinglequote(aSParm.getPartner()).toUpperCase();
			aszOtherAffil2 = replacesinglequote(aSParm.getPartner2()).toUpperCase();
			aszOtherAffil3 = replacesinglequote(aSParm.getPartner3()).toUpperCase();
			aszOtherAffil4 = replacesinglequote(aSParm.getPartner4()).toUpperCase();
			aszOtherAffil5 = replacesinglequote(aSParm.getPartner5()).toUpperCase();
			iOrgAffil1 = aSParm.getOrgAffil1TID();
			iOrgAffil2 = aSParm.getOrgAffil2TID();
			iOrgAffil3 = aSParm.getOrgAffil3TID();
			iOrgAffil4 = aSParm.getOrgAffil4TID();
			iOrgAffil5 = aSParm.getOrgAffil5TID();
			if (iOrgAffil1 > 0 || aszOtherAffil.length()>1){
				if (aszOrgAffilTIDs.length() > 1 || aszOrgAffils.length() > 1 ){
					if(!(aszOrgAffilTIDs.endsWith(",")))		aszOrgAffilTIDs = aszOrgAffilTIDs + ",";
					if(!(aszOrgAffils.endsWith(",")))			aszOrgAffils = aszOrgAffils + ",";
				}
				aszOrgAffilTIDs = aszOrgAffilTIDs + iOrgAffil1 ;
				aszOrgAffils = aszOrgAffils + "'" + aszOtherAffil + "'";
			}
			if (iOrgAffil2 > 0 || aszOtherAffil2.length()>1){
				if (aszOrgAffilTIDs.length() > 1 || aszOrgAffils.length() > 1 ){
					if(!(aszOrgAffilTIDs.endsWith(",")))		aszOrgAffilTIDs = aszOrgAffilTIDs + ",";
					if(!(aszOrgAffils.endsWith(",")))			aszOrgAffils = aszOrgAffils + ",";
				}
				aszOrgAffilTIDs = aszOrgAffilTIDs + iOrgAffil2 ;
				aszOrgAffils = aszOrgAffils + "'" + aszOtherAffil2 + "'";
			}
			if (iOrgAffil3 > 0 || aszOtherAffil3.length()>1){
				if (aszOrgAffilTIDs.length() > 1 || aszOrgAffils.length() > 1 ){
					if(!(aszOrgAffilTIDs.endsWith(",")))		aszOrgAffilTIDs = aszOrgAffilTIDs + ",";
					if(!(aszOrgAffils.endsWith(",")))			aszOrgAffils = aszOrgAffils + ",";
				}
				aszOrgAffilTIDs = aszOrgAffilTIDs + iOrgAffil3 ;
				aszOrgAffils = aszOrgAffils + "'" + aszOtherAffil3 + "'";
			}
			if (iOrgAffil4 > 0 || aszOtherAffil4.length()>1){
				if (aszOrgAffilTIDs.length() > 1 || aszOrgAffils.length() > 1 ){
					if(!(aszOrgAffilTIDs.endsWith(",")))		aszOrgAffilTIDs = aszOrgAffilTIDs + ",";
					if(!(aszOrgAffils.endsWith(",")))			aszOrgAffils = aszOrgAffils + ",";
				}
				aszOrgAffilTIDs = aszOrgAffilTIDs + iOrgAffil4 ;
				aszOrgAffils = aszOrgAffils + "'" + aszOtherAffil4 + "'";
			}
			if (iOrgAffil5 > 0 || aszOtherAffil5.length()>1){
				if (aszOrgAffilTIDs.length() > 1 || aszOrgAffils.length() > 1 ){
					if(!(aszOrgAffilTIDs.endsWith(",")))		aszOrgAffilTIDs = aszOrgAffilTIDs + ",";
					if(!(aszOrgAffils.endsWith(",")))			aszOrgAffils = aszOrgAffils + ",";
				}
				aszOrgAffilTIDs = aszOrgAffilTIDs + iOrgAffil5 ;
				aszOrgAffils = aszOrgAffils + "'" + aszOtherAffil5 + "'";
			}

			//aszTaxonomyTIDs = "4793,7536,4790,4796,4794";
			int iOrgAffilTIDsLength = aszOrgAffilTIDs.length();
			int iOrgAffilsLength = aszOrgAffils.length();
			if (iOrgAffilTIDsLength > 1 || iOrgAffilsLength > 1){
				aszWhereSQLStatement = aszWhereSQLStatement + 
					" AND (	(org_affil_tid IN(" + aszOrgAffilTIDs + ")  ))\n";//  OR (org_affil IN(" + aszOrgAffils + ")  ))\n";
			}
		}
		

		// City
		int iCity = aSParm.getCityTID();
		if (iCity > 1 ){
			aszFromSQLStatement = aszFromSQLStatement + ", " + aszDrupalDB + "term_node City, " + aszDrupalDB + "term_data CityTermData "; 
			aszWhereSQLStatement = aszWhereSQLStatement + " AND City.vid=org.vid  AND City.tid = CityTermData.tid  " +
					" AND CityTermData.vid=" +  vidCity + 
					" AND  " + " City.tid = " + iCity + "  \n";			
		}
		// Country
		int iCountry = aSParm.getCountryTID();
		if (iCountry > 1 ){
			aszFromSQLStatement = aszFromSQLStatement + ", " + aszDrupalDB + "term_node Country, " + aszDrupalDB + "term_data CountryTermData "; 
			aszWhereSQLStatement = aszWhereSQLStatement + " AND Country.vid=org.vid  AND Country.tid = CountryTermData.tid  " +
					" AND CountryTermData.vid=" +  vidCountry + 
					" AND  " + " Country.tid = " + iCountry + "  \n";			
		}
		
		// generic Program Types
		String aszProgramTypeTIDs = aSParm.getProgramTypeTID()+"";
		//aszTaxonomyTIDs = "4793,7536,4790,4796,4794";
		iProgramType = aSParm.getProgramTypeTID();
		// generic Service Areas
		// ???????????? in this next part, should there be a trailing comma, or no? I don't remember...
		if (iProgramType > 0){
			if (aszProgramTypeTIDs.length() > 1 ){
				if(!(aszProgramTypeTIDs.endsWith(",")))	aszProgramTypeTIDs = aszProgramTypeTIDs + ",";
			}
			aszProgramTypeTIDs = aszProgramTypeTIDs + iProgramType ;
		}
		int iProgramTypeLength = aszProgramTypeTIDs.length();
		int iProgramTypeNumbers = aszProgramTypeTIDs.replaceAll("[^,]","").length();
		if (iProgramTypeLength > 1){
			aszWhereSQLStatement = aszWhereSQLStatement + 
				" AND (program_type_tid IN(" + aszProgramTypeTIDs + ")  )\n";
		}

		// local affiliation
		aszLocalAffil = replacesinglequote(aSParm.getLocalAffil());
		if (aszLocalAffil.length() > 2){
			aszLocalAffil = aszLocalAffil.toUpperCase();
			aszWhereSQLStatement  = aszWhereSQLStatement  + "       AND local_affil LIKE '" +  aszLocalAffil + "' \n"; 
		}

		
		aSParm.setSearchSQLStatement( aszSQLStatement );
		aSParm.setSearchFromSQLStatement( aszFromSQLStatement );
		aSParm.setSearchWhereSQLStatement( aszWhereSQLStatement );
/*
		aSParm.setSearchSQLStmnt( aszSQLStmnt );
		aSParm.setSearchFromSQLStmnt( aszFromSQLStmnt );
		aSParm.setSearchWhereSQLStmnt( aszWhereSQLStmnt );
*/		
		aSParm.setSearchSQLStmnt( aszSQLStatement );
		aSParm.setSearchFromSQLStmnt( aszFromSQLStatement );
		aSParm.setSearchWhereSQLStmnt( aszWhereSQLStatement );

		OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
    	if(feeds==false){
    		aSParm.setSearchedFeeds(0);
    		iRetCode = aOrganizationObj.getOrganizationList(pConn, aList, aSParm );
    	}else{
    		aSParm.setSearchedFeeds(1);
    		iRetCode = aOrganizationObj.getOrganizationFeedsList(pConn, aList, aSParm );
    	}
    	
    	// save the search query for logging and reporting
    	int iRetCode2 = 0;
    	int iResultsSize=aList.size();
    	aSParm.setSearchResultsCount(iResultsSize);
    	iRetCode2 = aOrganizationObj.insertSearchQuery(pConn, aSParm, iRetCode);
    	
    	if(0 != iRetCode){
        	aOrganizationObj.copyErrObj(getErrObj());
    		//ErrorsToLog();
    	}
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method advanceSearchOrganizations()

	//====== END Search For Christian Volunteer Organizations ===>
	//====== END Search For Christian Volunteer Organizations ===>
	//====== END Search For Christian Volunteer Organizations ===>

	
	//====== START Search For Non-Christian Volunteer Opportunities ===>
    //====== START Search For Non-Christian Volunteer Opportunities ===>
    //====== START Search For Non-Christian Volunteer Opportunities ===>
	
	
    /**
	* get opportunity list for search options - non-Christian
	*/
	public int searchForOpportunitiesInDBIVol( ArrayList aList, SearchParms aSParm ){
		String aszReqCode=null;
		ABREDBConnection pConn=null;
    	MethodInit("searchForOpportunitiesInDBIVol");
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;
		// IVOLUNTEERING - This should equal NO, regardless
		aszReqCode = "NO";
		aSParm.setReqCode(aszReqCode);
    	return searchForOpportunitiesInDB(aList, aSParm);
	}
	// end-of method searchForOpportunitiesInDBIVol()

    /**
	* get opportunity list for advance search options - non-Christian
	*/
	public int advanceSearchOpportunitiesIVol( ArrayList aList, SearchParms aSParm, AppCodeInfoDTO aHeadObj ){
		String aszReqCode=null;
    	MethodInit("advanceSearchOpportunitiesIVol");
    	if(null == aList) return -1;
    	if(null == aSParm) return -1;
		
		// IVOLUNTEERING - This should equal NO, regardless
		aszReqCode = "NO";
		aSParm.setReqCode(aszReqCode);
    	return advanceSearchOpportunities( aList, aSParm, aHeadObj );
	}
	// end-of method advanceSearchOpportunitiesIVol()

    //====== END Search For Non-Christian Volunteer Opportunities ===>
    //====== END Search For Non-Christian Volunteer Opportunities ===>
    //====== END Search For Non-Christian Volunteer Opportunities ===>

	
	
	
	/**
	* get opportunity list for solr search options
	*/
	public int solrSearch( HttpServletRequest httpServletRequest, ArrayList aList, ArrayList facetList, SearchParms aSParm, AppCodeInfoDTO aHeadObj ){
		String content_type = "content_type:opportunity";
		String aszFormat = "";
		return solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj, content_type, aszFormat);
	}
	public int solrSearch( HttpServletRequest httpServletRequest, ArrayList aList, ArrayList facetList, SearchParms aSParm, AppCodeInfoDTO aHeadObj, String content_type){
		String aszFormat = "";
		return solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj, content_type, aszFormat);
	}
	public int solrSearch( HttpServletRequest httpServletRequest, ArrayList aList, ArrayList facetList, SearchParms aSParm, AppCodeInfoDTO aHeadObj, String content_type, String aszFormat){
		MethodInit("solrSearch");
		int iRetCode=0;
		boolean bNotUS=false;
		ABREDBConnection pConn=null;
		if(null == aList) return -1;
		if(null == aSParm) return -1;
		if(content_type.length() < 1) content_type="content_type:opportunity"; // default to opportunity
		String[] aszFQ = aSParm.getFilterQueryArray();
		String aszQ = aSParm.getFilterQuery();
		String aszQuery =  content_type;
		if(content_type.equals("content_type:organization")){
			if(! aSParm.getORGMembertype().equalsIgnoreCase("Foundation")){
				int iFilterQuerySize = aszFQ.length;
				try{
					if(iFilterQuerySize>0){
						String[] aszFQalt = aszFQ;
						aszFQ = new String[iFilterQuerySize+1];
						// iterate through aszFQ and add to new array; then at the end, add the NOT FOUNDATION
						for(int i=0; i<iFilterQuerySize; i++){
							aszFQ[i] = aszFQalt[i];
						}
						aszFQ[iFilterQuerySize] = "-org_member_type:Foundation"; // NOT foundation
					}
				}catch(java.lang.ArrayIndexOutOfBoundsException e){
System.out.println("line 1278 searchOpportunitiesBLO.java - Encountered java.lang.ArrayIndexOutOfBoundsException - iFilterQuerySize is "+ iFilterQuerySize);
				}
			}
		}
		if(aszQ.length()>0){
			aszQuery = "(" + content_type + " AND " + aszQ + ") ";
		}
		
    	String aszLatitude = "", aszLongitude = "";
		String aszPostalCode = aSParm.getZIP();
		if(aszPostalCode.length() > 0){
			// get latitude and longitude based on the postal code
			ArrayList aListObj = new ArrayList(2);
			pConn = getDBConn();
	    	// save the search query for logging and reporting
			ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
	   		iRetCode = aApplicationCodesObj.getZipCodeData(pConn, aListObj, aSParm);
	    	if(null != pConn) pConn.free();
	    	
			if(null != aListObj){
				try{
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aListObj.get(0);
					if(null != aAppCode){
						aszLatitude = aAppCode.getLatitude();
						aszLongitude = aAppCode.getLongitude();
					}
				}catch(java.lang.IndexOutOfBoundsException ex){
System.out.println("1305 searchOpportunitiesBLO  Exception "+ex.getMessage());	
				}
			}
		
		}
		
	    boolean b_applicationSearch = false;
	    for(int i=0; i<aszFQ.length; i++){
	    	if(aszFQ[i]!=null){
		    	if(aszFQ[i].contains("cvintern_")){
		    		b_applicationSearch = true;
		    		break;
		    	}
	    	}
	    }


		//SolrServer
		if(server==null){
			server = getServer(httpServletRequest);
		}
		if(server!=null){
			SolrQuery query = new SolrQuery();
		    query.setQuery(aszQuery);
		    query.setRows(aSParm.getmaxSearchResultRows());
		    query.setSortField( "source", SolrQuery.ORDER.desc );
		    query.addSortField( "tm_member_i", SolrQuery.ORDER.desc );
		    query.addSortField( "popularity", SolrQuery.ORDER.desc );
		    
		    query.addSortField( "last_updated_dt", SolrQuery.ORDER.desc );
		    
		    // different sort order for applicants, though
		    if(b_applicationSearch == true){

			    query.setSortField( "screened", SolrQuery.ORDER.desc );
			    query.addSortField( "last_updated_dt", SolrQuery.ORDER.desc );
			    query.addSortField( "source", SolrQuery.ORDER.desc );
			    
		    }

		    if(aszLatitude.length()>0 && aszLongitude.length()>0){
		    	String aszGeoLocation = "{!geofilt pt="+aszLatitude+","+aszLongitude+" sfield=latlng d=40.2336}";
		    	query.addFilterQuery(aszGeoLocation);
		    	}
   				for(int i=0; i<aszFQ.length; i++){
   			    	String aszFilter = aszFQ[i];
   			    	if(aszFilter==null){
   			    		continue;
   			    	}
		    	// newly commented out		    	aszFilter = aszFilter.replaceAll("location","{!geofilt pt="+aszLatitude+","+aszLongitude+" sfield=latlng ");

		    	aszFilter = aszFilter.replaceAll("_distance:"," d=");
		    	aszFilter = aszFilter.replaceAll("latlng:[(]","{!geofilt pt="+aszLatitude+","+aszLongitude);
		    	aszFilter = aszFilter.replaceAll("[)]_distance:"," sfield=latlng d=");
		    	aszFilter = aszFilter.replaceAll("_km","}");
		    	aszFilter = aszFilter.replaceAll("~[|]","{");
		    	aszFilter = aszFilter.replaceAll("[|]~","}");
		    	aszFilter = aszFilter.replaceAll("latlng:[(]","{!geofilt pt=");
		    	aszFilter = aszFilter.replaceAll("[|]","/");
		    	aszFilter = aszFilter.replaceAll("~~",".");
		    	aszFilter = aszFilter.replaceAll("~","&");
		    	aszFilter = aszFilter.replaceAll(";",".");
		    	

		    	aszFilter = aszFilter.replaceAll("service areas search","service_areas_search");
				aszFilter = aszFilter.replaceAll("skills search","skills_search");
				aszFilter = aszFilter.replaceAll("location data search","location_data_search");
				aszFilter = aszFilter.replaceAll("city tax search","city_tax_search");
				aszFilter = aszFilter.replaceAll("country tax search","country_tax_search");
				aszFilter = aszFilter.replaceAll("province tax search","province_tax_search");
				aszFilter = aszFilter.replaceAll("org affil search","org_affil_search");
				aszFilter = aszFilter.replaceAll("great for search","great_for_search");
				aszFilter = aszFilter.replaceAll("benefits offered search","benefits_offered_search");
				aszFilter = aszFilter.replaceAll("denom affil search","denom_affil_search");
				aszFilter = aszFilter.replaceAll("primary opp type search","primary_opp_type_search");
				aszFilter = aszFilter.replaceAll("position type search","position_type_search");
				aszFilter = aszFilter.replaceAll("content type search","content_type_search");
				aszFilter = aszFilter.replaceAll("trip length search","trip_length_search");

				aszFilter = aszFilter.replaceAll("service areas","service_areas");
				aszFilter = aszFilter.replaceAll("location data","location_data");
				aszFilter = aszFilter.replaceAll("city tax","city_tax");
				aszFilter = aszFilter.replaceAll("country tax","country_tax");
				aszFilter = aszFilter.replaceAll("province tax","province_tax");
				aszFilter = aszFilter.replaceAll("org affil","org_affil");
				aszFilter = aszFilter.replaceAll("great for","great_for");
				aszFilter = aszFilter.replaceAll("benefits offered","benefits_offered");
				aszFilter = aszFilter.replaceAll("denom affil","denom_affil");
				aszFilter = aszFilter.replaceAll("primary opp type","primary_opp_type");
				aszFilter = aszFilter.replaceAll("position type","position_type");
				aszFilter = aszFilter.replaceAll("content type","content_type");
				aszFilter = aszFilter.replaceAll("postal code","postal_code");
				aszFilter = aszFilter.replaceAll("trip length","trip_length");
				aszFilter = aszFilter.replaceAll("looking for","looking_for");
				aszFilter = aszFilter.replaceAll("org memeber type","org_member_type");
//				if(aszFilter.contains("content_type:organization") || aszFilter.contains("ctp:\"org\"")){
//					aszContentType = "organization";
//				}else 
				if(aszFilter.contains("\"")){
					// make sure to catch any potential queries from bad links, etc, that may have included the " at the beginning of the term, but not properly closed it
					if(aszFilter.endsWith(")") && 
							(aszFilter.contains("geofilt") || aszFilter.endsWith("\")") )
					){ // don't add a quote at the end of the geofilt expression, which is already fixed above
					}else if(! aszFilter.endsWith("\"")){ 
						aszFilter+="\""; 
					}
				}
				String aszFilterFacet = "";
				String aszFilterValue = "";
				if(aszFilter.contains(":") && !(aszFilter.contains("geo"))){
					int iColonIndex = aszFilter.indexOf(":");
					aszFilterFacet = aszFilter.substring(0,iColonIndex);
					if(aszFilter.length()>iColonIndex+1){
						if(aszFilterFacet.equals("loc")) 			aszFilterFacet = "location_data";
						else if(aszFilterFacet.equals("ct"))		aszFilterFacet = "city_tax";
						else if(aszFilterFacet.equals("ctr"))		aszFilterFacet = "country_tax";
						else if(aszFilterFacet.equals("st"))		aszFilterFacet = "province_tax";
						else if(aszFilterFacet.equals("r"))			aszFilterFacet = "region";
						else if(aszFilterFacet.equals("na"))		aszFilterFacet = "org_affil";
						else if(aszFilterFacet.equals("sa"))		aszFilterFacet = "service_areas";
						else if(aszFilterFacet.equals("sk"))		aszFilterFacet = "skills";
						else if(aszFilterFacet.equals("gf"))		aszFilterFacet = "great_for";
						else if(aszFilterFacet.equals("f"))			aszFilterFacet = "frequency";
						else if(aszFilterFacet.equals("b"))			aszFilterFacet = "benefits_offered";
						else if(aszFilterFacet.equals("tl"))		aszFilterFacet = "trip_length";
						else if(aszFilterFacet.equals("d"))			aszFilterFacet = "denom_affil";
						else if(aszFilterFacet.equals("pot"))		aszFilterFacet = "primary_opp_type";
						else if(aszFilterFacet.equals("pt"))		aszFilterFacet = "position_type";
						else if(aszFilterFacet.equals("zip"))		aszFilterFacet = "postal_code";
						else if(aszFilterFacet.equals("mem"))		aszFilterFacet = "org_member_type";
						else if(aszFilterFacet.equals("lf"))		aszFilterFacet = "looking_for";
						
						if(aszFilterFacet.equals("ctp") || aszFilterFacet.equals("c")){
							aszFilterFacet = "content_type";
							if(aszFilter.contains("org")) aszFilterValue = "organization";
							else if(aszFilter.contains("job")) aszFilterValue = "job";
							else if(aszFilter.contains("res")) aszFilterValue = "resume";
							else if(aszFilter.contains("fdn")) aszFilterValue = "Foundation";
							else aszFilterValue = "opportunity";
						}else if(aszFilter.length()>iColonIndex+1){
							aszFilterValue = aszFilter.substring(iColonIndex+1);
						}
						aszFilterValue = aszFilterValue.replaceAll("_"," ");
						aszFilterValue = aszFilterValue.replaceAll("&","\\&");
						int iTempIndex = 0;
						iTempIndex = aszFilterValue.indexOf(" OR ");
						if(iTempIndex>0){ char temp=aszFilterValue.charAt(iTempIndex-1);
							if(aszFilterValue.charAt(iTempIndex-1) != '\"'){
								aszFilterValue = aszFilterValue.replace(" OR ","\" OR \"");
							}
						}
						iTempIndex = aszFilterValue.indexOf(" AND ");
						if(iTempIndex>0){
							if(aszFilterValue.charAt(iTempIndex-1) != '\"'){
								aszFilterValue = aszFilterValue.replace(" AND ","\" AND \"");
							}
						}
						if (	a_FilterArray.contains(aszFilterFacet) || 
								aszFilterFacet.equals("organization_name") || 
								aszFilterFacet.equals("org_nid") || 
								aszFilterFacet.equals("applic_org_nids") || 
								aszFilterFacet.equals("applic_org_names") || 
								aszFilterFacet.equals("org_nid")
						){ // || aszFilterFacet.contains("cvintern")
							aszFilter = aszFilterFacet + ":" + aszFilterValue;
							if(aszFilterValue.contains("Minneapolis")){
//								System.out.println("		ASZFILTER IS "+aszFilter);
							}
						}else{
							continue;
						}
						
						
						if(aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND service areas:\"Orphanage\"")){
							query.addFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\"");
							query.addFilterQuery("service_areas:\"Orphanage\"");
						}else if(aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND service areas:\"Health and Medicine\"")){
							query.addFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\"");
							query.addFilterQuery("service_areas:\"Health and Medicine\"");
						}else if(
								aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND service areas:\"Computers and Technology\" OR \"Tech: Graphics/Web/IT Ministry\"") ||
								aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND (service areas:\"Computers and Technology\" OR \"Tech: Graphics/Web/IT Ministry\")") ||
								aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND (service areas:(\"Computers and Technology\" OR \"Tech: Graphics/Web/IT Ministry\")") ||
								aszFilter.equals("position_type:\"Short-term Missions / Volunteer Internship\" AND (service areas:(\"Computers and Technology OR Tech: Graphics/Web/IT Ministry\")")
						){
							query.addFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\"");
							query.addFilterQuery("service_areas:(\"Computers and Technology\" OR \"Tech: Graphics/Web/IT Ministry\")");
						}else{
							query.addFilterQuery(aszFilter);
						}
					}
				}else if(aszFilter.contains("geo")){
			    	query.addFilterQuery(aszFilter);
			    	aSParm.setGeoFilter(aszFilter);
				}
		    }

		    if(aszFormat.equals("zip")){
			    query.addFacetField("postal_code");
		    }else{
       	    	if(b_applicationSearch == true){
					query.addFilterQuery("screened:[1 TO 2]");

					query.addFacetField("pos_pref");
				    query.addFacetField("geo_pref");
				    query.addFacetField("intern_type");
				    query.addFacetField("pos_pref");
				    query.addFacetField("cvintern_applicant");
				    query.addFacetField("has_bachelors");
				    query.addFacetField("credits_range");
				    query.addFacetField("intern_length");
				    query.addFacetField("gender");
				    query.addFacetField("age_range");
				    
				    query.addFacetField("city");
				    query.addFacetField("province");
				    query.addFacetField("country");
				    query.addFacetField("city_tax");
				    query.addFacetField("province_tax");
				    query.addFacetField("country_tax");
				    query.addFacetField("region");

       	    	}else{
				    query.addFacetField("service_areas");
				    query.addFacetField("total_giving");
				    query.addFacetField("net_assets");
				    query.addFacetField("assets");
				    query.addFacetField("income");
				    query.addFacetField("expenditures");
				    query.addFacetField("skills");
				    query.addFacetField("position_type");		    	
				    query.addFacetField("primary_opp_type");		    	
				    query.addFacetField("frequency");		    	
				    query.addFacetField("benefits_offered");		    	
				    query.addFacetField("position_type");
				    query.addFacetField("great_for");
				    query.addFacetField("trip_length");
				    query.addFacetField("city_tax");
				    query.addFacetField("province_tax");
				    query.addFacetField("country_tax");
				    query.addFacetField("region");
				    query.addFacetField("denom_affil");
				    query.addFacetField("org_affil");
				    query.addFacetField("org_member_type");
				    query.addFacetField("looking_for");
       	    	}
		    }
		    query.addFacetField("source");

		    QueryResponse rsp = null;
			try {
				rsp = server.query( query );
			} catch (SolrServerException e) {
				System.out.println("SolrServerException in server.query( query ): ");
				e.printStackTrace();
				return 0;
			} catch (Exception ex) {
				System.out.println("Exception in server.query( query ): ");
				ex.printStackTrace();
				return 0;
			}
		    List<FacetField> facets = rsp.getFacetFields();
		    List<FacetField> limitingFacets = rsp.getLimitingFacets();
		    Iterator<FacetField> itr_facetsList = limitingFacets.iterator();
		    while(itr_facetsList.hasNext()){
		    	FacetField facet = itr_facetsList.next();
		    	List<FacetField.Count> Values = facet.getValues();
		    	// iterate through Values
		    	Iterator<FacetField.Count> itr_values = Values.iterator();
		    	while(itr_values.hasNext()){
		    		SearchParms search = new SearchParms();
		    		FacetField.Count facet_count = itr_values.next();
		    		String aszFacetValue = facet_count.getName();
		    		long lFacetValueCount = facet_count.getCount();
		    		FacetField aszFacetFieldName = facet_count.getFacetField();
		    		String[] facetsArray = new String[3];
		    		facetsArray[0]=aszFacetFieldName.getName();
		    		facetsArray[1]=aszFacetValue;
		    		facetsArray[2]=lFacetValueCount+"";
		    		if(lFacetValueCount > 0){
		    			facetList.add(facetsArray);
		    		}
		    	}
		    }

		    SolrDocumentList docs = rsp.getResults();
		    List<SolrItem> beans = null;
			  try{
				  beans = rsp.getBeans(SolrItem.class);
			  }catch(NullPointerException ex){
					System.out.println("null pointer error on getBeans");
					ex.printStackTrace();
			  }catch(Exception e){
					System.out.println("Error in rsp.getBeans call");
					e.printStackTrace();
			  }
		    if(beans == null) return 0;
		    
    	    Iterator<SolrItem> itr_aList = beans.iterator();
    	    iRetCode=2;
       	    while(itr_aList.hasNext()){
       	    	SolrItem item = itr_aList.next();
       	    	
       	    	if(b_applicationSearch == true){
           			ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
           			aApplicObj.setTitle(item.getTitle());
           			aApplicObj.setOPPNID(item.getNID());
           			aApplicObj.setNID(item.getNID());
           			
           			if(item.getApplicNID() > 0)	aApplicObj.setNID(item.getApplicNID());
           			
           			aApplicObj.setOPPVID(item.getID());
           			aApplicObj.setInternTypes(item.getInternType());
           			aApplicObj.setAddrCity(item.getCity());
           			aApplicObj.setAddrStateprov(item.getProvince());
           			aApplicObj.setAddrCountryName(item.getCountryISO());
           			aApplicObj.setCityTax(item.getCityTax());
           			aApplicObj.setProvTax(item.getProvinceTax());
           			aApplicObj.setCountryTax(item.getCountryTax());
           			aApplicObj.setAddrPostalcode(item.getPostalCode());
           			aApplicObj.setSource(item.getSource());
           			
           			aApplicObj.setLastUpdatedDt(item.getLastUpdated());
           			
           			aApplicObj.setGeoPref(item.getGeoPref());
           			aApplicObj.setHasBachelors(item.getHasBachelors());
           			aApplicObj.setInternLength(item.getInternLength());
           			aApplicObj.setGender(item.getGender());
           			aApplicObj.setCVInternApplic(item.getCVInternApplicant());
           			aApplicObj.setAgeRange(item.getAgeRange());
           			aApplicObj.setCreditsRange(item.getCreditsRange());
           			aApplicObj.setPosPref(item.getInternPosType());
           			
/* throwing an error:
 * 
 * Error in rsp.getBeans call
java.lang.RuntimeException: Exception while setting value : 2 on int com.abrecorp.opensource.servlet.SolrItem.screened
        at org.apache.solr.client.solrj.beans.DocumentObjectBinder$DocField.set(DocumentObjectBinder.java:380)


Error in rsp.getBeans call
java.lang.RuntimeException: Exception while setting value : 0 on int com.abrecorp.opensource.servlet.SolrItem.screened
        at org.apache.solr.client.solrj.beans.DocumentObjectBinder$DocField.set(DocumentObjectBinder.java:380)
        at org.apache.solr.client.solrj.beans.DocumentObjectBinder$Do
        
 */
           			aApplicObj.setScreened(item.getCVInternScreened());
           		//	aApplicObj.setORGNamesArray(item.getOrgName());
           			
           			aList.add(aApplicObj);

       	    	}else{
       	    	
       	    	
       	    	
       			OrgOpportunityInfoDTO aOrgOppObj = new OrgOpportunityInfoDTO();
       			aOrgOppObj.setOPPTitle(item.getTitle());
       			aOrgOppObj.setOPPNID(item.getNID());
       			aOrgOppObj.setOPPVID(item.getID());
       			aOrgOppObj.setOPPOrgName(item.getOrgName());
       			aOrgOppObj.setSkillTypes(item.getSkills());
       			aOrgOppObj.setOPPPositionType(item.getPositionType());
       			aOrgOppObj.setOPPUrlAlias(item.getURLAlias());
       			aOrgOppObj.setOPPAddrCity(item.getCity());
       			aOrgOppObj.setOPPAddrStateprov(item.getProvince());
       			aOrgOppObj.setOPPAddrCountryName(item.getCountryISO());
       			aOrgOppObj.setOPPAddrPostalcode(item.getPostalCode());
       			aOrgOppObj.setCityName(item.getCityTax());
       			aOrgOppObj.setStateName(item.getProvinceTax());
       			aOrgOppObj.setCountryName(item.getCountryTax());
       			aOrgOppObj.setRegionName(item.getRegion());
       			aOrgOppObj.setOPPCategories(item.getServiceAreas());
       			aOrgOppObj.setOPPNumVolOpp(item.getNumVolunteers());
       			aOrgOppObj.setOPPUpdateDt(item.getLastUpdated());
       			aOrgOppObj.setOPPStartDt(item.getStartDate());
       			aOrgOppObj.setOPPEndDt(item.getEndDate());
       			aOrgOppObj.setOPPPopularity(item.getPopularity());
       			aOrgOppObj.setOPPDescTeaser(item.getDescription());
       			aOrgOppObj.setOPPFreq(item.getFrequency());
       			aOrgOppObj.setOPPTripLength(item.getTripLength());
       			aOrgOppObj.setOPPGreatForAreas(item.getGreatFor());
       			aOrgOppObj.setOPPBenefits(item.getBenefitsOffered());
       			aOrgOppObj.setOPPWorkStudy(item.getWorkStudy());
       			aOrgOppObj.setOPPSource(item.getSource());
       			// fill in org data:
       			aOrgOppObj.setORGOrgName(item.getOrgName());
       			aOrgOppObj.setORGUrlAlias(item.getOrgURLAlias());
       			aOrgOppObj.setORGMember(item.getOrgMember());
       			aOrgOppObj.setORGAffiliation(item.getDenomAffil());
       			aOrgOppObj.setORGPartner(item.getOrgAffil());
       			aOrgOppObj.setORGProgramTypes(item.getProgramType());
       			aOrgOppObj.setPortalName(item.getPortal());
//       		aOrgOppObj.setORGPrimaryOppType(item.getPrimaryOppTypes());
       			
       			aList.add(aOrgOppObj);
       	    	}
       			iRetCode=0;
       	    }
		}
		
		OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getDBConn();
   	
    	// save the search query for logging and reporting
    	int iRetCode2 = 0;
    	int iResultsSize=aList.size();
    	aSParm.setSearchResultsCount(iResultsSize);
   		iRetCode2 = aOrganizationObj.insertSearchQuery(pConn, aSParm, iRetCode);
    	if(0 != iRetCode){
    		if(-2 != iRetCode){
            	aOrganizationObj.copyErrObj(getErrObj());
    		}
    	}
/**
 *     	
 */
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method solrSearchOpportunities()

	//====== END Search For Christian Volunteer Opportunities ===>
	//====== END Search For Christian Volunteer Opportunities ===>
	//====== END Search For Christian Volunteer Opportunities ===>
	

	/**
     * CommonsHttpSolrServer is thread-safe and if you are using the following constructor,
     * you *MUST* re-use the same instance for all requests.  If instances are created on
     * the fly, it can cause a connection leak. The recommended practice is to keep a
     * static instance of CommonsHttpSolrServer per solr server url and share it for all requests.
     * See https://issues.apache.org/jira/browse/SOLR-861 for more details
	 */
	private CommonsHttpSolrServer getServer( HttpServletRequest httpServletRequest){
		if(server!=null){
			return server;
		}
		String aszSolrUser = this.getSitePropertyValue(ABREAppServerDef.SOLR_USERNAME);
		String aszSolrPassword = this.getSitePropertyValue(ABREAppServerDef.SOLR_PASSWORD);
		String aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_PRIMARY_HOST);
		String aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_PRIMARY_URL);
		String mainDB=aszDrupalDB;
		if ( 	httpServletRequest.getHeader("host").contains(":7001") || 
				httpServletRequest.getHeader("host").contains("staging-christianvolunteering.org")
		) {
			aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_STAGING_URL);
			aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_STAGING_HOST);
		}
		try{
			server = new CommonsHttpSolrServer( aszSolrURL );
		}catch(MalformedURLException e){
			System.out.print("MalformedURLException: ");
			e.printStackTrace();
		}
		server.getHttpClient().getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials(aszSolrUser, aszSolrPassword);
		AuthScope authScope = new AuthScope(aszSolrHost,8080, AuthScope.ANY_REALM);
		server.getHttpClient().getState().setCredentials(authScope, defaultcreds);
		return server;
	}
	
	private CommonsHttpSolrServer server = null;
	

	// array of possible facets:
	private static final Set<String> a_FilterArray = new HashSet<String>(Arrays.asList(new String[]
			{"location_data","city_tax","country_tax","province_tax","region","org_affil",
				"service_areas","skills","great_for","frequency","benefits_offered","trip_length","denom_affil",
				"primary_opp_type","position_type","postal_code","content_type","title","org_name","screened","cvintern_screened",
				"cvintern_placed", "cvintern_applicant","pos_pref","geo_pref","intern_type",
				"has_bachelors","credits_range","intern_length","gender","age_range","city","province","country"}
	));
	// drupal taxonomy (for term_data) - vocabulary id's - where vid=?
	// indicate the database if necessary
	private static final String aszDrupalDB = "um_";
	private static final String aszDrupalDBname = "";
	private static final String aszDataDB = "urbmi5_data.";
	private static final String aszFeedsDB = "techmi5_drufeeds.";
	private static final String aszVolengDB = "techmi5_voleng.";
	//private String aszVolengDB = "";
	private static final int iMeetTheNeedUID = 59185;
	private int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidWorkStudy=264, vidTripLength=263, vidPosFreq=268, vidSchedDate=269, vidFaith=281, vidBenefits=286,
		vidCauseTopic = 8, vidCity=15, vidCountry=261, vidFaithSpec=341;
	private int iYesStipend = 8107, iNoStipend = 8108;
	private int iNonFaith = 9397;
	private int iNoFaithActivity = 21996, iFaithBased = 21997, iFaithFilled = 21998;
	// vidVolInfo=33
	private int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;
	// vidPosType=34
	int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797;
	int iOppBoard=4761;
}
