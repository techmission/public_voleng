package com.abrecorp.opensource.servlet;

/**
* Created 2006-07-28
* Web Access Layer
* For Generating Database Objects
* @author David Marcillo
* ABRE Technology Corp.
*/

// data transfer objects
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.base.ABREBaseDef;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dbutils.ABREDataBaseUtilObj;

import java.util.ArrayList;

public class DBUtilServlet extends BaseServletBRLO {

	/**
	* execute SQL Statement
	* return -1 for fail, return 0 for success
	*/
	public int execSQLStatement( DBUtilInfo aHeadObj, ArrayList aList ){
		int iRetCode=0;
        String aszTemp=null;
		ABREDBConnection pConn=null;
		MethodInit("execSQLStatement");
    	if(null == aHeadObj){
			setErr("database DTO object required");
    		return -1;
    	}
    	aHeadObj.setProcessType( "EXECSQLSTRING" );
    	ABREDataBaseUtilObj aDBUtilObj = new ABREDataBaseUtilObj();
		pConn = getDBConn();
		iRetCode = aDBUtilObj.execSQLInputString( pConn, aHeadObj, aList );
    	if(null != pConn) pConn.free();
    	setErrMsg( aDBUtilObj.getAllMessages() );
    	return iRetCode;
	}
    // end-of method execSQLStatement()

	/**
	* generate framework classes for a database table
	* return -1 for fail, return 0 for success
	*/
	public int genTableFrameWorkFiles( DBUtilInfo aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
		String aszTemp=null;
		MethodInit("genTableFrameWorkFiles");
    	if(null == aHeadObj){
			setErr("database DTO object required");
    		return -1;
    	}
    	// get attach file folder location
    	String aszFolderLoc = getSitePropertyValue(ABREBaseDef.ATTACH_FILE1_LOCATION);
    	if(null == aszFolderLoc) aszFolderLoc="";
    	if(aszFolderLoc.length() < 3){
			setErr("attach file folder definition required");
    		return -1;
    	}
    	aHeadObj.setFolderName( aszFolderLoc );
    	aszTemp = aHeadObj.getCodeGenSTyleType();
    	if(aszTemp.length() < 2){
    		aHeadObj.setCodeGenSTyleType("opensource");
    	}
    	ABREDataBaseUtilObj aDBUtilObj = new ABREDataBaseUtilObj();
		pConn = getDBConn();
		iRetCode = aDBUtilObj.genClassesFromTable( pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	setErrMsg( aDBUtilObj.getAllMessages() + " " + aszFolderLoc );
    	return iRetCode;
	}
    // end-of method genTableFrameWorkFiles()

}
