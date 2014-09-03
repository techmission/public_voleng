package com.abrecorp.opensource.dbutils;

/**
* Code generation Utility class
* For Database tables
* ABRE Technology Inc.
* Clemente D Marcillo January 1999
* Copyright:    Copyright (c) 1998-2006
*/

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.DBUtilInfo;

import java.util.*;

public class ABREDataBaseUtilObj extends ABREBase {

	/**
	** Constructor
	*/
	public ABREDataBaseUtilObj(){}


    /**
	* execute SQL input statement
    * @param DBUtilInfo - the data base utility data object
    * @return 0 if all worked ok, non-zero if any errors
    */
    public int execSQLInputString(ABREDBConnection pConn, DBUtilInfo aInfo, ArrayList aList ){
		int iRetCode=0;
        String aszTemp=null;
		MethodInit("execSQLInputString");
        if(null == aInfo){
			setErr("data object required");
			return 1;
        }
        aszTemp = aInfo.getProcessType() ;
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("process request type required");
			return 1;
        }
        allocDBHelperObjects();
        if( aszTemp.equalsIgnoreCase("EXECSQLSTRING") ) {
    		iRetCode = m_GetTableInfoDBObj.execSQLInputString(pConn, aInfo, aList );
        } else {
			setErr("process \"" + aszTemp + "\" not implemented" );
			return 1;
        }
		if(0 != iRetCode){
            m_GetTableInfoDBObj.copyErrObj(getErrObj());
			return 1;
		}
		return 0;
    }
    // end-of execSQLInputString()


    /**
	* generate ABRE Infrastructure common Classes from Database Table
    * @param DBUtilInfo - the data base utility data object
    * @return 0 if all worked ok, non-zero if any errors
    */
    public int genClassesFromTable(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
        String aszTemp=null;
		MethodInit("genClassesFromTable");
        if(null == aInfo){
			setErr("data object required");
			return -1;
        }
        aszTemp = aInfo.getProcessType() ;
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("process request type required");
			return -1;
        }
        allocDBHelperObjects();
        if( aszTemp.equalsIgnoreCase("XMLREQUETTYPE") ) {
    		iRetCode = m_GetTableInfoDBObj.generateXMLClasses(pConn, aInfo );
        } else if( aszTemp.equalsIgnoreCase("DATAPROCESSTYPE") ) {
    		iRetCode = m_GetTableInfoDBObj.generateDBTableClasses(pConn, aInfo );
        } else {
			setErr("process \"" + aszTemp + "\" not implemented" );
			return -1;
        }
		if(0 != iRetCode){
            m_GetTableInfoDBObj.copyErrObj(getErrObj());
			return -1;
		}
		return iRetCode;
    }
    // end-of genClassesFromTable()

    //================================================ Private Methods
    //================================================ Private Methods
    //================================================ Private Methods

    /**
    * allocate database Helper classes
    */
    private void allocDBHelperObjects(){
        if(null == m_GetTableInfoDBObj){
            m_GetTableInfoDBObj = new GetDBTableInfoDBDAO();
            // m_GetTableInfoDBObj.setLogObj(getLogObj());
            // m_GetTableInfoDBObj.setDbaseMgr(getDbaseMgr());
        }
    }

    //=======================================> Private Variables
    //=======================================> Private Variables
    //=======================================> Private Variables

    private GetDBTableInfoDBDAO m_GetTableInfoDBObj=null;




}
