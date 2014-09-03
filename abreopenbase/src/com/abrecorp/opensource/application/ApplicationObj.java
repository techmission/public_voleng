package com.abrecorp.opensource.application;

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
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;

public class ApplicationObj extends ABREBase {

	/**
	** Constructor
	*/
	public ApplicationObj(){}

	/**
	* load an opportunity
	* return not zero for fail, return 0 for success
	*/
	public int loadOneApplication(ABREDBConnection pConn, ApplicationInfoDTO aHeadObj, int iIDNum, int iKeyId, int iType ){
		int iRetCode=0;
    	String aszTemp=null ;
    	MethodInit("loadOneApplication");
    	if(null == aHeadObj){
    		setErr("null application object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if(iIDNum< 1 && iType!=ApplicationInfoDTO.LOADBY_APPLICANT_USER){
    		setErr("Application ID required");
    		return -1;
    	}
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();
    	if(aHeadObj.getSource().equals("sf")){
    		iRetCode = aDBAObj.loadOneApplicationFromFeedsDB(pConn, aHeadObj, iIDNum );
    	}else{
    		iRetCode = aDBAObj.loadOneApplicationFromDB( pConn, aHeadObj, iIDNum, iKeyId, iType );
    	}
    	return iRetCode;
	}
    // end-of method loadOneApplication()



	/**
	* create a new insertApplicationIntoDB
	* return not zero for fail, return 0 for success
	*/
	public int insertApplication(ABREDBConnection pConn, ApplicationInfoDTO aHeadObj){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("insertApplication");	
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization node id required");
//    		return -2;
    	}
    	if( aHeadObj.getOPPNID() < 1){
    		setErr("opportunity node id required");
//    		return -2;
    	}
    	if( aHeadObj.getUID() < 1){
    		setErr("user uid required");
    		return -2;
    	}
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();
    	iRetCode = aDBAObj.insertApplicationIntoDB( pConn, aHeadObj );
    	
    	if(iRetCode != 0) return iRetCode;
    	return iRetCode;
	}
    // end-of method insertApplication()



	/**
	*  updateApplication
	* return not zero for fail, return 0 for success
	*/
	public int updateApplication(ABREDBConnection pConn, ApplicationInfoDTO aHeadObj){
		int iRetCode=0, iRetCode2=0;
    	String aszTemp=null ;
    	MethodInit("updateApplication");	
    	if(null == aHeadObj){
    		setErr("null organiztion object");
    		return -1;
    	}
    	if(null == pConn){
    		setErr("null database connection");
    		return -2;
    	}
    	if( aHeadObj.getNID() < 1){
    		setErr("application node id required");
    		return -2;
    	}
    	if( aHeadObj.getORGNID() < 1){
    		setErr("organization node id required");
//    		return -2;
    	}
    	if( aHeadObj.getOPPNID() < 1){
    		setErr("opportunity node id required");
//    		return -2;
    	}
    	if( aHeadObj.getUID() < 1){
    		setErr("user uid required");
    		return -2;
    	}
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();
    	iRetCode = aDBAObj.editApplicationInDB( pConn, aHeadObj );
    	
    	if(iRetCode != 0) return iRetCode;
    	return iRetCode;
	}
    // end-of method updateApplication()

    /**
	* search for opportunities
	* return not zero for fail, return 0 for success
	*/
	public int getApplicationsList(ABREDBConnection pConn, ArrayList aListObj, int iIdNum, int iType){
		int iRetCode=0;
    	MethodInit("getApplicationsList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return -1;
    	}
    	if(0 == iIdNum){
    		if( iType!= ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER && iType!= ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER_APPLICANT){
	    		setErr("null search object");
	    		return -1;
    		}
    	}
System.out.println("ApplicationObj line 172; iType is "+iType);    	
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();    	
    	iRetCode = aDBAObj.getApplicationsDBList( pConn, aListObj, iType, iIdNum, false );
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getApplicationsList()
	
	
    /**
	* getApplicMatchesList
	*/
	public int getApplicMatchesList(ABREDBConnection pConn, ArrayList<ApplicationInfoDTO> aListObj){
		int iRetCode=0;
    	MethodInit("getApplicationsList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aListObj){
    		setErr("null array object");
    		return -1;
    	}
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();    	
    	iRetCode = aDBAObj.getApplicMatchesList( pConn,  aListObj);
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getApplicMatchesList()
	
	
    /**
	* insertApplicMatchIntoDB
	*/
	public int insertApplicMatch(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj){
		int iRetCode=0;
    	MethodInit("getApplicationsList");
    	if(null == pConn){
    		setErr("null database object");
    		return -1;
    	}
    	if(null == aApplicantObj){
    		setErr("null array object");
    		return -1;
    	}
    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();    	
    	iRetCode = aDBAObj.insertApplicMatchIntoDB( pConn,  aApplicantObj);
    	//don't log if there just are no results
    	if(-2 != iRetCode){
	    	if(0 != iRetCode){
	    		aDBAObj.copyErrObj(getErrObj());
	    		ErrorsToLog();
	    	}
    	}
    	return iRetCode;
	}
    // end-of method getApplicMatchesList()


	/**
	* getApplicMatchSource
	*/
	public String getApplicMatchSource( ABREDBConnection pConn, int iOrgNID, int iApplicNID ){
		int iRetCode=0;
    	String aszSource=null ;
    	MethodInit("getApplicMatchSource");
    	if(iOrgNID < 1){
    		setErr("null application object");
    		return null;
    	}
    	if(iApplicNID < 1){
    		setErr("null database connection");
    		return null;
    	}

    	ApplicationDBDAO aDBAObj = new ApplicationDBDAO();
    	aszSource=aDBAObj.getApplicMatchSource(  pConn,  iOrgNID,  iApplicNID );

    	return aszSource;
	}
    // end-of method getApplicMatchSource()



}
