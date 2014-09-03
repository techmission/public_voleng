package com.abrecorp.opensource.appcodes;

/**
* Code generated DataAccess class
* For Table application_codes
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;

import java.util.ArrayList;

class ApplicationCodesDBDAO extends ABREBase {

	/**
	** Constructor
	*/
	public ApplicationCodesDBDAO(){}

    //=== START Table country ===>
    //=== START Table country ===>
    //=== START Table country ===>

	public int getCountryListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"iso,name,printable_name,iso3,numcode,region" +
			" FROM " + aszVolengDB + "country ";
        MethodInit("getCountryListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case 101 :
				aszSQL2 = aszSQL101 + " ORDER BY printable_name ASC " ;
				break;
			case 201 :
				aszSQL2 = aszSQL101 + " ORDER BY weight DESC, printable_name ASC " ;
				break;
			default:
				aszSQL2 = aszSQL101 + " ORDER BY printable_name ASC " ;
				break;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();

			aHeadObj.setCTRIso(pConn.getDBString("iso"));
			aHeadObj.setCTRName(pConn.getDBString("name"));
			aHeadObj.setCTRPrintableName(pConn.getDBString("printable_name"));
			aHeadObj.setCTRIso3(pConn.getDBString("iso3"));
			aHeadObj.setCTRNumcode(pConn.getDBInt("numcode"));
			aHeadObj.setCTRRegion(pConn.getDBString("region"));
			
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getCountryListFromDB()

    //=== END   Table country ===>
    //=== END   Table country ===>
    //=== END   Table country ===>

	//=== START Table country_stateprovince ===>
	//=== START Table country_stateprovince ===>
    //=== START Table country_stateprovince ===>

	/**
	* select a list of application codes from table application_codes
	*/
	public int getStateProviceListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"state_code,state_name,country_code,region" +
			" FROM " + aszVolengDB + "country_stateprovince ";
        MethodInit("getStateProviceListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case 101 :
			default:
				aszSQL2 = aszSQL101 + " ORDER BY state_name ASC " ;
				break;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setCSPStateCode(pConn.getDBString("state_code"));
			aHeadObj.setCSPStateName(pConn.getDBString("state_name"));
			aHeadObj.setCSPCountryCode(pConn.getDBString("country_code"));
			aHeadObj.setCSPRegion(pConn.getDBString("region"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getStateProviceListFromDB()

    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>

	//=== START Table application_codes ===>
    //=== START Table application_codes ===>
    //=== START Table application_codes ===>

	/**
	* select a list of application codes from table application_codes
	*/
	public int getAppCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"id_type,id_sub_type,id_sort,keycode,display," +
			"description,functional_area" +
			" FROM " + aszVolengDB + "application_codes ";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getPersonNumber() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE id_type=" + aSrchParmObj.getPersonNumber() + " ORDER BY display ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getPersonNumber() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE id_type=" + aSrchParmObj.getPersonNumber() + " ORDER BY keycode ASC " ;
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("id_type"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("id_sub_type"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("id_sort"));
			aHeadObj.setAPCKeycode(pConn.getDBString("keycode"));
			aHeadObj.setAPCDisplay(pConn.getDBString("display"));
			aHeadObj.setAPCDescription(pConn.getDBString("description"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("functional_area"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()

	/**
	* insert a row into table application_codes
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertDBAPC(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL101 = " INSERT INTO " + aszVolengDB + "application_codes ( " +
			"id_type, id_sub_type, id_sort, keycode, display" +
			", description, functional_area)" +
			" values(?,?,?,?,?,?,?)" ;
		MethodInit("insertDBAPC");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getAPCIdType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getAPCIdSubType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getAPCIdSort() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getAPCKeycode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getAPCDisplay() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getAPCDescription() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getAPCFunctionalArea() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method insertDBAPC()

    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>


	/**
	* insert a row into table application_codes
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int trackFileDownload(ABREDBConnection pConn, PersonInfoDTO aCurrentUserObj, PersonInfoDTO aContactPersonObj ){
		int iRetCode=0;
		String aszSQL101 = " INSERT INTO " + aszVolengDB + "file_download_tracking ( " +
			"file_location,file_type,download_time,file_owner_uid,file_owner_email,file_owner_first_name,file_owner_last_name," +
			"current_user_uid,current_user_email,current_user_first_name,current_user_last_name,status)" +
			" values(?,'resume',{fn now()},?,?,?,?,?,?,?,?,'success')" ;
		MethodInit("insertDBAPC");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aCurrentUserObj){
			setErr("null input object");
			return -1;
		}
		if(null == aContactPersonObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aContactPersonObj.getUSPResumeFilePath() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aContactPersonObj.getUserUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aContactPersonObj.getUSPEmail1Addr() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aContactPersonObj.getUSPNameFirst() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aContactPersonObj.getUSPNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aCurrentUserObj.getUserUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aCurrentUserObj.getUSPEmail1Addr() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aCurrentUserObj.getUSPNameFirst() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aCurrentUserObj.getUSPNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method trackFileDownload()

    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>

	/**
	* select a list of getZipCodesFromDB
	*/
	public int getZipCodesFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
		String aszSQL2 = " SELECT zip, latitude, longitude FROM " + aszDrupalDB + "zipcodes ";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(44000);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setPostal(pConn.getDBString("zip"));
			aHeadObj.setLatitude(pConn.getDBDouble("latitude"));
			aHeadObj.setLongitude(pConn.getDBDouble("longitude"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getZipCodesFromDB()

	/**
	* select a list of getZipCodeDataFromDB
	*/
	public int getZipCodeDataFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0;
		String aszSQL2 = " SELECT zip, latitude, longitude " +
				" FROM " + aszDrupalDB + "zipcodes " +
				" WHERE zip LIKE '" + aSrchParmObj.getZIP() + "'";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setPostal(pConn.getDBString("zip"));
			aHeadObj.setLatitude(pConn.getDBDouble("latitude"));
			aHeadObj.setLongitude(pConn.getDBDouble("longitude"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getZipCodeDataFromDB()


	//=== START Table taxonomy um_term_data ===>
    //=== START Table taxonomy um_term_data ===>
    //=== START Table taxonomy um_term_data ===>

	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"tid,vid,name,description,weight" +
			" FROM " + aszDrupalDB + "term_data ";
        MethodInit("getTaxonomyCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY name ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY tid ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_SORTID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY weight,name ASC " ;
				aSrchParmObj.setmaxSearchResultRows(255); // so that it can load a long list, like Languages taxonomy
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
//			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()



	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	* 
	* JOIN related terms to determine which site it's coming through 
	* (ie, ChurchVol, ChrisVol, iVol, etc - these will be related terms to the service area or whatever taxonomy)
	* 
	*/
	public int getTaxonomyCodeListByRelatedFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		// tid1 is the related term; we're looking for tid2
		String aszSQL101 = " SELECT " +
				"t.tid,t.vid,t.name,t.description,t.weight" +
				" FROM " + aszDrupalDB + "term_data t ," + aszDrupalDB + "term_relation r" +
				" WHERE t.vid=" + aSrchParmObj.getSearchOrder() + " AND r.tid1=" + aSrchParmObj.getRelatedTerm() + " AND r.tid2=t.tid ";
		return getTaxonomyCodeListByRelatedFromDB(pConn, aListObj, aSrchParmObj, aszSQL101);
	}
	public int getTaxonomyCodeListByRelatedTID1FromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		// tid2 is the related term; we're looking for tid1
		String aszSQL101 = " SELECT " +
				"t.tid,t.vid,t.name,t.description,t.weight" +
				" FROM " + aszDrupalDB + "term_data t ," + aszDrupalDB + "term_relation r" +
				" WHERE t.vid=" + aSrchParmObj.getSearchOrder() + " AND r.tid2=" + aSrchParmObj.getRelatedTerm() + " AND r.tid1=t.tid ";
		return getTaxonomyCodeListByRelatedFromDB(pConn, aListObj, aSrchParmObj, aszSQL101);
	}
	public int getTaxonomyCodeListByRelatedFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String aszSQL101){
		int iRetCode=0;
		String aszSQL2=null ;
		// JOIN related term of ChrisVol/ChurchVol/iVol (iSiteVID).... then do where iSiteVID's tid=siteTID aSrchParmObj.getRelatedTerm()
        MethodInit("getTaxonomyCodeListByRelatedFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " GROUP BY t.name ORDER BY t.name ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " GROUP BY t.name ORDER BY t.tid ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_SORTID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " GROUP BY t.name ORDER BY t.weight,t.name ASC " ;
				aSrchParmObj.setmaxSearchResultRows(255); // so that it can load a long list, like Languages taxonomy
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("t.vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("t.tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("t.weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("t.name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("t.name"));
			aHeadObj.setAPCDescription(pConn.getDBString("t.name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("t.name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getTaxonomyCodeListByRelatedFromDB()


	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyParentCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"d.tid,d.vid,d.name,d.weight" +
			" FROM " + aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h ";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=0 ORDER BY d.name ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=0 ORDER BY d.tid ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_SORTID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=0 " +
						" ORDER BY d.weight,d.name ASC " ;
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("d.vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("d.tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("d.weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("d.name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("d.name"));
			aHeadObj.setAPCDescription(pConn.getDBString("d.name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("d.name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()

	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyChildCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int parentTID){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"d.tid,d.vid,d.name,d.weight" +
			" FROM " + aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h ";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=" + parentTID + " ORDER BY d.name ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=" + parentTID + "ORDER BY d.tid ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_SORTID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE d.vid=" + aSrchParmObj.getSearchOrder() + " AND d.tid=h.tid AND h.parent=" + parentTID +
						" ORDER BY d.weight,d.name ASC " ;
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("d.vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("d.tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("d.weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("d.name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("d.name"));
			aHeadObj.setAPCDescription(pConn.getDBString("d.name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("d.name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getTaxonomyChildCodeListFromDB()
	
	/**
	* select a list of taxonomy codes from table taxonomy um_term_relation
	*/
	public int getTaxonomyRelatedCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int parentTID){
		int iRetCode=0, iIDType=0 ;
		
		String aszSQL101 = " SELECT " +
			"r.tid1,r.tid2" +
			" FROM " + aszDrupalDB + "term_relation r "
			+ "WHERE r.tid1=" + parentTID + " ORDER BY r.tid1 ASC ";
        MethodInit("getTaxonomyRelatedCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("r.tid1"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("r.tid2"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getTaxonomyRelatedCodeListFromDB()
	
	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyTIDFromDB(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, String aszTaxonomy, int iVid ){ //ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"tid,vid,name,weight" +
			" FROM " + aszDrupalDB + "term_data " +
					"WHERE name = '" + aszTaxonomy + "' AND vid = " + iVid;
        MethodInit("getAppCodeListFromDB");
        if(null == aHeadObj){
        	setErr("null object passed");
        	return -1;
        }
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aszTaxonomy){
			setErr("no taxonomy provided");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = -1;
            
        while(pConn.getNextResult()){	
        	iRetCode=0;
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
        }
		return iRetCode;
	}
	// end-of method getTaxonomyTIDFromDB()
	
	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyNameFromDB(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, int iTid, int iVid ){ //ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"tid,vid,name,weight" +
			" FROM " + aszDrupalDB + "term_data " +
					"WHERE tid = '" + iTid + "' AND vid = " + iVid;
        MethodInit("getAppCodeListFromDB");
        if(null == aHeadObj){
        	setErr("null object passed");
        	return -1;
        }
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(0 == iTid){
			setErr("no taxonomy provided");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = -1;
            
        while(pConn.getNextResult()){	
        	iRetCode=0;
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
        }
		return iRetCode;
	}
	// end-of method getTaxonomyTIDFromDB()
	
	/**
	* select the list of given taxonomy codes from table taxonomy um_term_data
	* the terms desired are given as a comma-delimited String
	*/
	public int getTaxonomyTermListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String terms){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101=null; 
        MethodInit("getTaxonomyTermListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		//Make sure we actually have some terms before querying		
		if((terms == "") || terms.equals(",")){
			return 999;
		}
		/*
		aszSQL101 = " SELECT " +
			"tid,vid,name,description,weight" +
			" FROM " + aszDrupalDB + "term_data " + 
			"WHERE tid IN ( '";
		int commaPlace=0,tempTID=0;
		while (terms.length() > 1) {
			commaPlace = terms.indexOf(",");//5
			if(commaPlace>0){
				try{
					tempTID = Integer.parseInt(terms.substring(0, commaPlace));
					terms = terms.substring((commaPlace + 1), terms.length());
					aszSQL101 = aszSQL101 + tempTID + "','";
				}catch(Exception e){}
			}
		}
		
		
		String tempString = aszSQL101.substring(aszSQL101.length() - 2, aszSQL101.length());
		if(tempString.equals(",'")){
			aszSQL101 = aszSQL101.substring(0, aszSQL101.length() - 2);
		}
		aszSQL101 = aszSQL101 + ")";
		*/
		if(terms.endsWith(",")) terms=terms.substring(0, terms.length()-2);
		//Make sure we actually have some terms before querying		
		if(terms.length()<1){
			return 999;
		}
		aszSQL101 = " SELECT " +
			"tid,vid,name,description,weight" +
			" FROM " + aszDrupalDB + "term_data " + 
			"WHERE tid IN ( " + terms + ")";
		//aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
			aListObj.add(aHeadObj);
		}
		
		return 0;
	}
	// end-of method getAppCodeListFromDB()

	/**
	* select the list of given taxonomy codes from table taxonomy um_term_data
	* the terms desired are given as a comma-delimited String
	*/
	public int getTaxonomyTIDListFromNamesFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String terms, int vid){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"tid,vid,name,description,weight" +
			" FROM " + aszDrupalDB + "term_data " + 
			"WHERE name IN ( '";
		
		// check to make sure there are actually terms before processing the query
		if(terms == "")
			return -1;
		
		while (terms.length() > 1) {
			//int commaPlace = terms.indexOf(",");
			String tempName = terms;
			// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
			if(tempName.contains(",")){
				int commaPlace = tempName.indexOf(",");
				tempName = tempName.substring(0, commaPlace);
				terms = terms.substring((commaPlace + 1), terms.length());
				if(terms.charAt(0) == ' ')
					terms = terms.substring(1, terms.length());
			}else{
				tempName = tempName;//.substring(0, commaPlace);
				terms = "";//tempName;
			}

			aszSQL101 = aszSQL101 + tempName + "','";
			
		}
		String tempString = aszSQL101.substring(aszSQL101.length() - 2, aszSQL101.length());
		if(tempString.equals(",'")){
			aszSQL101 = aszSQL101.substring(0, aszSQL101.length() - 2);
		}
		
		aszSQL101 = aszSQL101 + ") AND vid = " + vid;
        MethodInit("getTaxonomyTIDListFromNamesFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		//aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()
	
	
	/**
	* select the list of cityvision intern approved Organizations
	*/
	public int getCVInternOrgSitesListFromDB(ABREDBConnection pConn, ArrayList aListObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101=null; 
        MethodInit("getCVInternOrgSitesListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		/*
		aszSQL101 = " SELECT n.nid, title " +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_organization a " + 
			" WHERE a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=n.nid ";
		*/
		aszSQL101 = " SELECT n.nid, title, td.name metro  FROM " + 
				aszDrupalDB + "node n, " + aszDataDB + "tbl_taxonomy_metro td , " + aszDrupalDB + "content_type_organization a , " + 
				aszDrupalDB + "content_field_volorg_opp_reference op_ref, " + aszDrupalDB + "content_type_volunteer_opportunity opp " + 
				"WHERE n.status=1 AND n.nid=a.nid AND a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=td.nid " +
				"AND a.nid=op_ref.nid AND op_ref.field_volorg_opp_reference_nid=opp.nid " +
				"AND opp.field_intern_type_value='City Vision Internship' " +
				" GROUP BY n.nid ORDER BY title";
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		int[] a_iCVInternSiteOrgNIDs = new int[200];
		int i=0;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			String aszDisplay = pConn.getDBString("title") + " - " + pConn.getDBString("metro");
			int iNID = pConn.getDBInt("nid");
			a_iCVInternSiteOrgNIDs[i] = iNID;
			i++;
			aHeadObj.setAPCIdType(iNID);
			aHeadObj.setAPCIdSubType(iNID);
			aHeadObj.setAPCIdSort(aszDisplay);
			aHeadObj.setAPCKeycode(aszDisplay);
			aHeadObj.setAPCDisplay(aszDisplay);
			aHeadObj.setAPCDescription(aszDisplay);
			aHeadObj.setAPCFunctionalArea(aszDisplay);
			aListObj.add(aHeadObj);
		}
		aszSQL101 = " SELECT n.nid, title, td.name province  FROM " + 
				aszDrupalDB + "node n, " + aszDataDB + "tbl_taxonomy_province td , " + aszDrupalDB + "content_type_organization a , " + 
				aszDrupalDB + "content_field_volorg_opp_reference op_ref, " + aszDrupalDB + "content_type_volunteer_opportunity opp " + 
				"WHERE n.status=1 AND n.nid=a.nid AND a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=td.nid " +
				"AND a.nid=op_ref.nid AND op_ref.field_volorg_opp_reference_nid=opp.nid " +
				"AND opp.field_intern_type_value='City Vision Internship' " +
				" GROUP BY n.nid ORDER BY title";
			//aListObj.clear();
System.out.println("AppCodesDBDAO line 1322 - metro was not yet set, so do a second query w/o metro but with province");			
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
		    iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			int iNID = pConn.getDBInt("nid");
			if(iNID > 0){
				boolean b_alreadyExists = false;
				// iterate through a_iCVInternSiteOrgNIDs to see if the value already exists
				for(int j=0; j<i+1; j++){
					try{
						int iTmpNID = a_iCVInternSiteOrgNIDs[j];
						if(iTmpNID==iNID){
							b_alreadyExists=true;
						}
					}catch( Exception e){
						
					}
				}
				if(b_alreadyExists==false){
					a_iCVInternSiteOrgNIDs[i] = iNID;
					i++;
					String aszDisplay = pConn.getDBString("title") + " - " + pConn.getDBString("province");
					aHeadObj.setAPCIdType(iNID);
					aHeadObj.setAPCIdSubType(iNID);
					aHeadObj.setAPCIdSort(aszDisplay);
					aHeadObj.setAPCKeycode(aszDisplay);
					aHeadObj.setAPCDisplay(aszDisplay);
					aHeadObj.setAPCDescription(aszDisplay);
					aHeadObj.setAPCFunctionalArea(aszDisplay);
					aListObj.add(aHeadObj);
				}
			}
		}
		aszSQL101 = " SELECT n.nid, title, td.name country  FROM " + 
				aszDrupalDB + "node n, " + aszDataDB + "tbl_taxonomy_country td , " + aszDrupalDB + "content_type_organization a , " + 
				aszDrupalDB + "content_field_volorg_opp_reference op_ref, " + aszDrupalDB + "content_type_volunteer_opportunity opp " + 
				"WHERE n.status=1 AND n.nid=a.nid AND a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=td.nid " +
				"AND a.nid=op_ref.nid AND op_ref.field_volorg_opp_reference_nid=opp.nid " +
				"AND opp.field_intern_type_value='City Vision Internship' " +
				" GROUP BY n.nid ORDER BY title";

		/*
		aszSQL101 = " SELECT n.nid, title " +
				" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_organization a , " + 
				aszDrupalDB + "content_field_volorg_opp_reference op_ref, " + aszDrupalDB + "content_type_volunteer_opportunity opp " + 
				" WHERE n.status=1 AND a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=n.nid " +
				"AND a.nid=op_ref.nid AND op_ref.field_volorg_opp_reference_nid=opp.nid " +
				"AND opp.field_intern_type_value='City Vision Internship' " +
				" GROUP BY n.nid ORDER BY title";
				*/
			//aListObj.clear();
System.out.println("AppCodesDBDAO line 1385 - metro & provice were not yet set, so do a second query w/o those but with country");			
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
		    iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			int iNID = pConn.getDBInt("nid");
			if(iNID > 0){
				boolean b_alreadyExists = false;
				// iterate through a_iCVInternSiteOrgNIDs to see if the value already exists
				for(int j=0; j<i+1; j++){
					try{
						int iTmpNID = a_iCVInternSiteOrgNIDs[j];
						if(iTmpNID==iNID){
							b_alreadyExists=true;
						}
					}catch( Exception e){
						
					}
				}
				if(b_alreadyExists==false){
					a_iCVInternSiteOrgNIDs[i] = iNID;
					i++;
					String aszDisplay = pConn.getDBString("title") + " - " + pConn.getDBString("country");
					aHeadObj.setAPCIdType(iNID);
					aHeadObj.setAPCIdSubType(iNID);
					aHeadObj.setAPCIdSort(aszDisplay);
					aHeadObj.setAPCKeycode(aszDisplay);
					aHeadObj.setAPCDisplay(aszDisplay);
					aHeadObj.setAPCDescription(aszDisplay);
					aHeadObj.setAPCFunctionalArea(aszDisplay);
					aListObj.add(aHeadObj);
				}
			}
		}
		
		aszSQL101 = " SELECT n.nid, title " +
				" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_organization a , " + 
				aszDrupalDB + "content_field_volorg_opp_reference op_ref, " + aszDrupalDB + "content_type_volunteer_opportunity opp " + 
				" WHERE n.status=1 AND a.field_cvintern_site_approved_value = 'City Vision' AND a.nid=n.nid " +
				"AND a.nid=op_ref.nid AND op_ref.field_volorg_opp_reference_nid=opp.nid " +
				"AND opp.field_intern_type_value='City Vision Internship' " +
				" GROUP BY n.nid ORDER BY title";
				
			//aListObj.clear();
System.out.println("AppCodesDBDAO line 1385 - metro & provice were not yet set, so do a second query w/o any location taxonomy");			
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
		    iRetCode=0;
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			int iNID = pConn.getDBInt("nid");
			if(iNID > 0){
				boolean b_alreadyExists = false;
				// iterate through a_iCVInternSiteOrgNIDs to see if the value already exists
				for(int j=0; j<i+1; j++){
					try{
						int iTmpNID = a_iCVInternSiteOrgNIDs[j];
						if(iTmpNID==iNID){
							b_alreadyExists=true;
						}
					}catch( Exception e){
						
					}
				}
				if(b_alreadyExists==false){
					aHeadObj.setAPCIdType(iNID);
					aHeadObj.setAPCIdSubType(iNID);
					aHeadObj.setAPCIdSort(pConn.getDBString("title"));
					aHeadObj.setAPCKeycode(pConn.getDBString("title"));
					aHeadObj.setAPCDisplay(pConn.getDBString("title"));
					aHeadObj.setAPCDescription(pConn.getDBString("title"));
					aHeadObj.setAPCFunctionalArea(pConn.getDBString("title"));
					aListObj.add(aHeadObj);
				}
			}
		}
		
		
		return 0;
	}
	/**
	* get pathauto rules for url alias generation
	*/
	public int getPathAutoURLAliasInfo( ABREDBConnection pConn, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
			" name,value " +
			" FROM " + aszDrupalDB + "variable WHERE name LIKE 'pathauto_ignore_words' OR name LIKE 'pathauto_punctuation_%' " +
			" OR name LIKE 'pathauto_separator' ";
        MethodInit("getPathAutoURLAliasInfo");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			//AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
            if(pConn.getDBString("name").equalsIgnoreCase("pathauto_separator")){
            	if(pConn.getDBString("value").length() > 0){
            		aHeadObj.setPathAutoSeparator(pConn.getDBString("value"));
            	}else{
            		aHeadObj.setPathAutoSeparator("");
            	}
            
            }else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_ignore_words")){
            	aHeadObj.setPathAutoIgnoreWords(pConn.getDBString("value"));
            	
            }else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_ampersand")){
            	aHeadObj.setPathAutoPuncAmp(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_asterisk")){
            	aHeadObj.setPathAutoPuncAstrsk(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_at")){
            	aHeadObj.setPathAutoPuncAt(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_backtick")){
            	aHeadObj.setPathAutoPuncBckTck(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_back_slash")){
            	aHeadObj.setPathAutoPuncBckSlsh(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_caret")){
            	aHeadObj.setPathAutoPuncCaret(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_colon")){
            	aHeadObj.setPathAutoPuncColon(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_comma")){
            	aHeadObj.setPathAutoPuncComma(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_dollar")){
            	aHeadObj.setPathAutoPuncDolrSign(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_double_quotes")){
            	aHeadObj.setPathAutoPuncDblQuotes(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_equal")){
            	aHeadObj.setPathAutoPuncEqual(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_exclamation")){
            	aHeadObj.setPathAutoPuncExclam(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_greater_than")){
            	aHeadObj.setPathAutoPuncGrtr(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_hash")){
            	aHeadObj.setPathAutoPuncHash(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_hyphen")){
            	aHeadObj.setPathAutoPuncHyphen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_curly")){
            	aHeadObj.setPathAutoPuncLftCrly(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_parenthesis")){
            	aHeadObj.setPathAutoPuncLftParen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_square")){
            	aHeadObj.setPathAutoPuncLftSq(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_less_than")){
            	aHeadObj.setPathAutoPuncLess(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_percent")){
            	aHeadObj.setPathAutoPuncPerc(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_period")){
            	aHeadObj.setPathAutoPuncPeriod(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_pipe")){
            	aHeadObj.setPathAutoPuncPipe(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_plus")){
            	aHeadObj.setPathAutoPuncPlus(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_question_mark")){
            	aHeadObj.setPathAutoPuncQuest(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_quotes")){
            	aHeadObj.setPathAutoPuncSingleQut(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_curly")){
            	aHeadObj.setPathAutoPuncRtCurly(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_parenthesis")){
            	aHeadObj.setPathAutoPuncRtParen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_square")){
            	aHeadObj.setPathAutoPuncRtSq(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_semicolon")){
            	aHeadObj.setPathAutoPuncSemiColon(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_tilde")){
            	aHeadObj.setPathAutoPuncTilde(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_underscore")){
            	aHeadObj.setPathAutoPuncUnderScore(pConn.getDBString("value"));
            }
			//aHeadObj.setPathAutoPunctuation(pConn.getDBString("name"));
			//aHeadObj.setPathAutoPunctuationValue(pConn.getDBString("value"));
			//aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()
	
	
	/**
	* get portal data from db
	*/
	public int getPortalInfo(ABREDBConnection pConn, AppCodeInfoDTO aHeadObj ){ 
		int iRetCode=0, iNID=0, iTempNID=0 ;
		String aszPortal = aHeadObj.getPortal();
        MethodInit("getPortalInfo");
        if(null == aHeadObj){
        	setErr("null object passed");
        	return -1;
        }
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aszPortal){
			setErr("no portal provided");
			return -1;
		}
		// church portal is a taxonomy.  
		String aszSQL101 = " SELECT td.tid,td.vid,name,weight, nid,tn.vid " +
			" FROM " + aszDrupalDB + "term_data td, " + aszDrupalDB + "term_node tn " +
			" WHERE td.tid=tn.tid AND name LIKE '" + aszPortal + "' AND td.vid = " + iPortalVID;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = -1;
          
		// ****************************************** hopefully is an IF and not a WHILE; only want one result for this!!! ******
        if(pConn.getNextResult()){	
        	iRetCode=0;
        	iNID=pConn.getDBInt("nid");
			aHeadObj.setPortalNID(iNID);
        }
        
        if(iNID > 0){
        	aszSQL101 = " SELECT  " +
			"field_volorg_portal_banner_value,field_volorg_portal_header_tags_value,field_volorg_portal_header_value," +
			"field_volorg_portal_css_value,field_volorg_portal_footer_value" +
				" FROM " + aszDrupalDB + "content_type_organization " +
				" WHERE nid = " + iNID;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = -1;
	          
			// ****************************************** hopefully is an IF and not a WHILE; only want one result for this!!! ******
	        if(pConn.getNextResult()){	
	        	iRetCode=0;
				aHeadObj.setPortalBanner(pConn.getDBString("field_volorg_portal_banner_value"));
				aHeadObj.setPortalHeaderTags(pConn.getDBString("field_volorg_portal_header_tags_value"));
				aHeadObj.setPortalHeader(pConn.getDBString("field_volorg_portal_header_value"));
				aHeadObj.setPortalCSS(pConn.getDBString("field_volorg_portal_css_value"));
				aHeadObj.setPortalFooter(pConn.getDBString("field_volorg_portal_footer_value"));
	        }
	        
	        if(iRetCode==-1){// load of an organization for this portal failed; try National Association
	        	aszSQL101 = " SELECT  " +
				"field_natlassoc_portal_banner_value,field_natlassoc_portal_hdr_tags_value,field_natlassoc_portal_hdr_value," +
				"field_natlassoc_portal_css_value,field_natlassoc_portal_footer_value" +
					" FROM " + aszDrupalDB + "content_type_national_association " +
					" WHERE nid = " + iNID;
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				
				iRetCode = pConn.RunQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode = -1;
		          
				// ****************************************** hopefully is an IF and not a WHILE; only want one result for this!!! ******
		        if(pConn.getNextResult()){	
		        	iRetCode=0;
					aHeadObj.setPortalBanner(pConn.getDBString("field_natlassoc_portal_banner_value"));
					aHeadObj.setPortalHeaderTags(pConn.getDBString("field_natlassoc_portal_hdr_tags_value"));
					aHeadObj.setPortalHeader(pConn.getDBString("field_natlassoc_portal_hdr_value"));
					aHeadObj.setPortalCSS(pConn.getDBString("field_natlassoc_portal_css_value"));
					aHeadObj.setPortalFooter(pConn.getDBString("field_natlassoc_portal_footer_value"));
					aHeadObj.setRequestType("natlassoc");
		        }
		        if(iRetCode==0){ // get the name and tid of the OrgAffiliation
		        	aszSQL101 = " SELECT  " +
						"tn.tid, tn.nid, tn.vid version_id, td.name, td.vid vocab_id " +
						" FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
						" WHERE tn.nid = " + iNID + " AND tn.tid=td.tid AND td.vid=" + vidOrgAffil;
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode = pConn.RunQuery(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode = -1;
			        if(pConn.getNextResult()){	
			        	iRetCode=0;
						aHeadObj.setPortalOrgAffil(pConn.getDBString("name"));
						aHeadObj.setPortalOrgAffilTID(pConn.getDBInt("tid"));
			        }
		        }
	        }
	        // get uid of primary person; either check delta=0 in the org_owner tbl, or check uid of node
        	aszSQL101 = " SELECT uid, title " +
				" FROM " + aszDrupalDB + "node " +
				" WHERE nid = " + iNID;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = -1;
	        
	        if(pConn.getNextResult()){	
	        	iRetCode=0;
				aHeadObj.setPortalUID(pConn.getDBInt("uid"));
				aHeadObj.setPortalOrgName(pConn.getDBString("title")); // may also be a National Association title, instead
	        }
    	
        	
        }
		return iRetCode;
	}
	// end-of method getPortalInfo()

	
	// get users uid from the db given the users Facebook ID
	public int getUIDFromFacebookUIDFromDB(ABREDBConnection pConn, String facebookUID){
		int iRetCode=0, iIDType=0 ;
		
		MethodInit("getUIDFromFacebookUID");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == facebookUID){
			setErr("null input String");
			return -1;
		}
		if(facebookUID.equalsIgnoreCase("")){
			setErr("blank input String");
			return -1;
		}
		
		
		String aszSQL101 = " SELECT " +
			"uid" +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile up " + 
			"WHERE up.field_facebook_uid_value = " + facebookUID + 
			" and up.nid = n.nid and n.type like 'uprofile'";
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		
		while(pConn.getNextResult()){
            iRetCode=0;
			
			return pConn.getDBInt("uid");
			
		}
		return 0;
		 
			
	}
	
//	 get users personality type from the db given the users Facebook ID
	public int getPersonalityFromFacebookUIDFromDB(ABREDBConnection pConn, String facebookUID){
		int iRetCode=0, iIDType=0 ;
		
		MethodInit("getPersonalityFromFacebookUIDFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == facebookUID){
			setErr("null input String");
			return -1;
		}
		if(facebookUID.equalsIgnoreCase("")){
			setErr("blank input String");
			return -1;
		}
		
		
		String aszSQL101 = " SELECT " +
			"tn.tid" +
			" FROM " + aszDrupalDB + "term_node tn, " +
			aszDrupalDB + "term_data td, " +
			aszDrupalDB + "content_type_uprofile up " + 
			"WHERE tn.tid = td.tid and td.vid=336 and up.nid=tn.nid and up.field_facebook_uid_value = " + facebookUID;
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		
		while(pConn.getNextResult()){
            iRetCode=0;
			
			return pConn.getDBInt("tn.tid");
			
		}
		return 0;
		 
			
	}

	
	//private String aszDrupalDB = "urbmi5_drupal.";
	private static final String aszDrupalDB = "um_";
	private static final String aszDataDB = "urbmi5_data.";
	private static final String aszVolengDB = "techmi5_voleng.";
	//private String aszVolengDB = "";
	private static final int iPortalVID = 343, iSiteVID = 344;
	private static final int vidOrgAffil=5,vidMetro=15;
}
