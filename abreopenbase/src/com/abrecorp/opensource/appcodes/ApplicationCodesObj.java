package com.abrecorp.opensource.appcodes;

/**
* Created 2006-05-05
* Application codes Interface Object
* @author David Marcillo
* ABRE Technology Corp.
*/
import java.util.ArrayList;

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;

public class ApplicationCodesObj extends ABREBase {

	/**
	** Constructor
	*/
	public ApplicationCodesObj(){}

    //=== START Table country_stateprovince ===>
    //=== START Table country_stateprovince ===>
    //=== START Table country_stateprovince ===>

    /**
	* geta list of states
	* return not zero for fail, return 0 for success
	*/
	public int getStateInList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getStateInList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getStateProviceListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getStateInList()

    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>

    //=== START Table country ===>
    //=== START Table country ===>
    //=== START Table country ===>

    /**
	* geta list of counytries
	* return not zero for fail, return 0 for success
	*/
	public int getCountryList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getCountryList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getCountryListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getCountryList()

    //=== END   Table country ===>
    //=== END   Table country ===>
    //=== END   Table country ===>

	//=== START Table application_codes ===>
    //=== START Table application_codes ===>
    //=== START Table application_codes ===>

    /**
	* search for Application Codes
	* return not zero for fail, return 0 for success
	*/
	public int getAppCodeList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getAppCodeList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getAppCodeListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getAppCodeList()

	

    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>


    /**
	* search for getZipCodes
	* return not zero for fail, return 0 for success
	*/
	public int getZipCodes(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getAppCodeList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getZipCodesFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getZipCodes()


    /**
	* search for getZipCodeData
	* return not zero for fail, return 0 for success
	*/
	public int getZipCodeData(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
    	MethodInit("getAppCodeList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getZipCodeDataFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getZipCodeData()

	//=== START Table application_codes ===>
    //=== START Table application_codes ===>
    //=== START Table application_codes ===>

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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyCodeListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getTaxonomyCodeList()


    /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyCodeListByRelated(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		return getTaxonomyCodeListByRelated(pConn, aListObj, aSrchParmObj, 0);
	}
	public int getTaxonomyCodeListByRelated(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int iOrder){
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	if(iOrder==0)
    		iRetCode = aDBAObj.getTaxonomyCodeListByRelatedFromDB( pConn, aListObj, aSrchParmObj );
    	else
    		iRetCode = aDBAObj.getTaxonomyCodeListByRelatedTID1FromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getTaxonomyCodeListByRelated()


    /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyParentCodeList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyParentCodeListFromDB( pConn, aListObj, aSrchParmObj );
    	return iRetCode;
	}
    // end-of method getAppCodeList()

	   /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyChildCodeList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int parentTID){
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyChildCodeListFromDB( pConn, aListObj, aSrchParmObj, parentTID );
    	return iRetCode;
	}
    // end-of method getAppCodeList()
	
	   /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyRelatedCodeList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int parentTID){
		int iRetCode=0;
    	MethodInit("getTaxonomyRelatedCodeList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyRelatedCodeListFromDB( pConn, aListObj, aSrchParmObj, parentTID );
    	return iRetCode;
	}
    // end-of method getAppCodeList()
	   /**
	* 
	*/
	public int getTaxonomyTID(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, String aszTaxonomy, int iVid){
		int iRetCode=0;
    	MethodInit("getTaxonomyTID");
    	if(iVid < 1) return -1;
    	if(null == aHeadObj){
        	setErr("null object passed");
        	return -1;
        }
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aszTaxonomy){
    		setErr("no taxonomy was provided");
    		return -1;
    	}

    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyTIDFromDB( pConn, aHeadObj, aszTaxonomy, iVid );
    	return iRetCode;
	}
    // end-of method getAppCodeList()
	
	/**
	* Get Taxonomy Name from TID
	*/
	public int getTaxonomyName(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, int iTid, int iVid){
		int iRetCode=0;
    	MethodInit("getTaxonomyTID");
    	if(iVid < 1) return -1;
    	if(null == aHeadObj){
        	setErr("null object passed");
        	return -1;
        }
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(0 == iTid){
    		setErr("no taxonomy was provided");
    		return -1;
    	}

    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyNameFromDB( pConn, aHeadObj, iTid, iVid );
    	return iRetCode;
	}
    // end-of method getAppCodeList()
	
    /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyTermList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String terms){
		int iRetCode=0;
    	MethodInit("getTaxonomyTermList");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyTermListFromDB( pConn, aListObj, aSrchParmObj, terms );
    	return iRetCode;
	}
    // end-of method getAppCodeList()

    /**
	* search for Taxonomy Codes
	* return not zero for fail, return 0 for success
	*/
	public int getTaxonomyTIDListFromNames(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String terms, int vid){
		int iRetCode=0;
    	MethodInit("getTaxonomyTIDListFromNames");
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
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getTaxonomyTIDListFromNamesFromDB( pConn, aListObj, aSrchParmObj, terms, vid );
    	return iRetCode;
	}
    // end-of method getAppCodeList()


	public int getCVInternOrgSitesList(ABREDBConnection pConn, ArrayList aListObj){
		int iRetCode=0;
    	MethodInit("getCVInternOrgSitesList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return -1;
    	}
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getCVInternOrgSitesListFromDB( pConn, aListObj );
    	return iRetCode;
	}
    // end-of method getAppCodeList()

    /**
	* trackFileDownload
	*/
	public int trackFileDownload(ABREDBConnection pConn, PersonInfoDTO aCurrentUserObj, PersonInfoDTO aContactPersonObj ){
		int iRetCode=0;
    	MethodInit("trackFileDownload");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aCurrentUserObj){
    		setErr("null array object");
    		return -1;
    	}
    	if(null == aContactPersonObj){
    		setErr("null search object");
    		return -1;
    	}
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.trackFileDownload(pConn, aCurrentUserObj, aContactPersonObj );
    	return iRetCode;
	}
    // end-of method trackFileDownload()
	
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
	
    /**
	* get a users UID given their Facebook user id
	* return not zero if uid found, return 0 if not found, return -1 for fail
	*/
	public int getUIDFromFacebookUID(ABREDBConnection pConn, String facebookUID){
		int iRetCode=0;
    	MethodInit("getTaxonomyTIDListFromNames");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == facebookUID){
    		setErr("null String object");
    		return -1;
    	}
    	if(facebookUID.equalsIgnoreCase("")){
			setErr("blank input String");
			return -1;
		}
    	
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getUIDFromFacebookUIDFromDB( pConn, facebookUID );
    	return iRetCode;
	}
	
    /**
	* get a users Personality Type given their Facebook user id
	* return not zero if uid found, return 0 if not found, return -1 for fail
	*/
	public int getPersonalityFromFacebookUID(ABREDBConnection pConn, String facebookUID){
		int iRetCode=0;
    	MethodInit("getPersonalityFromFacebookUID");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == facebookUID){
    		setErr("null String object");
    		return -1;
    	}
    	if(facebookUID.equalsIgnoreCase("")){
			setErr("blank input String");
			return -1;
		}
    	
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getPersonalityFromFacebookUIDFromDB( pConn, facebookUID );
    	return iRetCode;
	}



    /**
	* get pathauto rules for url alias generation
	* return not zero for fail, return 0 for success
	*/
	public int getPathAutoURLAliasInfo( ABREDBConnection pConn, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("getPathAutoURLAliasInfo");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getPathAutoURLAliasInfo( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method getPathAutoURLAliasInfo()



    /**
	* getPortalInfo
	*/
	public int getPortalInfo( ABREDBConnection pConn, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
    	MethodInit("getPortalInfo");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	ApplicationCodesDBDAO aDBAObj = new ApplicationCodesDBDAO();
    	iRetCode = aDBAObj.getPortalInfo( pConn, aHeadObj );
    	return iRetCode;
	}
    // end-of method getPortalInfo()


}
