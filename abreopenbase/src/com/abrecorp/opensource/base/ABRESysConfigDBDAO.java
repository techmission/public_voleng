package com.abrecorp.opensource.base;

/**
* generated DataAccess class
* For Table systemconfiginfo
*/


public class ABRESysConfigDBDAO extends ABREBase {

	/**
	** Constructor
	*/
	public ABRESysConfigDBDAO(){}

	/** START Code generated DataAccess Methods For Table systemconfiginfo ********/
	/** START Code generated DataAccess Methods For Table systemconfiginfo ********/
	/** START Code generated DataAccess Methods For Table systemconfiginfo ********/



	/**
	* update a row in table systemconfiginfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateSysConfigItemInDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE systemconfiginfo SET " +
			" config_type=?,active_flag=?,update_dt={fn now()},update_id=?" +
			" ,interval_num=?,interval_type=?,lastrun_dt=?,run_status=?,class_name=?,location=?" +
			" ,value=?,tokens=?" +
			" WHERE config_number=? ";
		MethodInit("updateSysConfigItemInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(aHeadObj.getCONFConfigNumber() < 1){
			setErr("onfig number required");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFConfigType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFActiveFlag() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFUpdateId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFIntervalNum() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFIntervalType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDate( aHeadObj.getCONFLastrunDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFRunStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFClassName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFLocation() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFValue() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFTokens() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFConfigNumber() );
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
	// end-of method updateSysConfigItemInDB()


    /**
	* insert a row into table systemconfiginfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertSysConfigItemIntoDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj, int iNextNumber ){
		int iRetCode=0;
		String aszTemp=null;
		String aszSQL101 = " INSERT INTO systemconfiginfo ( " +
			"config_number, config_code, config_type, active_flag, create_dt" +
			", update_dt, create_id, update_id, interval_num, interval_type, lastrun_dt" +
			", run_status, class_name, location, value, tokens)" +
			" values(?,?,?,?, {fn now()} , {fn now()} ,?,?,?,?" +
			",?,?,?,?,?,?)" ;
		MethodInit("insertSysConfigItemIntoDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(iNextNumber < 1){
			setErr("unique config number required");
			return -1;
		}
		aszTemp = aHeadObj.getCONFConfigCode();
		if(aszTemp.length() < 3){
			setErr("config_code required");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFConfigNumber() );
		iRetCode = pConn.setPrepQryInt( iNextNumber );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFConfigCode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFConfigType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFActiveFlag() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFCreateId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFUpdateId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCONFIntervalNum() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFIntervalType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDate( aHeadObj.getCONFLastrunDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFRunStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFClassName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFLocation() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFValue() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCONFTokens() );
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
	// end-of method insertSysConfigItemIntoDB()


    /**
	* load one record from systemconfiginfo table
	*/
	public int loadSysConfigRecordFromDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj, int iType, int iInput, String aszInput ){
		int iRetCode=0;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
		"config_number,config_code,config_type,active_flag,create_dt," +
		"update_dt,create_id,update_id,interval_num,interval_type,lastrun_dt," +
		"run_status,class_name,location,value,tokens" +
		" FROM systemconfiginfo " ;
		MethodInit("loadSysConfigRecordFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		switch(iType){
			// by config_code
			case 1:
				if(null == aszInput){
					setErr("config_code required");
					return -1;
				}
				aszTemp = aszInput.trim();
				if(aszTemp.length() < 3){
					setErr("config_code required");
					return -1;
				}
				aszTemp = aszTemp.toUpperCase();
				aszSQL2 = aszSQL101 + " WHERE UPPER(config_code)='" + aszTemp + "' ";
				break;
			default:
				setErr("type not supported");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		if(pConn.getNextResult()){
			iRetCode=0;
			aHeadObj.setCONFConfigNumber(pConn.getDBInt("config_number"));
			aHeadObj.setCONFConfigCode(pConn.getDBString("config_code"));
			aHeadObj.setCONFConfigType(pConn.getDBString("config_type"));
			aHeadObj.setCONFActiveFlag(pConn.getDBString("active_flag"));
			aHeadObj.setCONFCreateDt(pConn.getDBTimestamp("create_dt"));
			aHeadObj.setCONFUpdateDt(pConn.getDBTimestamp("update_dt"));
			aHeadObj.setCONFCreateId(pConn.getDBInt("create_id"));
			aHeadObj.setCONFUpdateId(pConn.getDBInt("update_id"));
			aHeadObj.setCONFIntervalNum(pConn.getDBInt("interval_num"));
			aHeadObj.setCONFIntervalType(pConn.getDBString("interval_type"));
			aHeadObj.setCONFLastrunDt(pConn.getDBTimestamp("lastrun_dt"));
			aHeadObj.setCONFRunStatus(pConn.getDBString("run_status"));
			aHeadObj.setCONFClassName(pConn.getDBString("class_name"));
			aHeadObj.setCONFLocation(pConn.getDBString("location"));
			aHeadObj.setCONFValue(pConn.getDBString("value"));
			aHeadObj.setCONFTokens(pConn.getDBString("tokens"));
		}
		return iRetCode;
	}
	// end-of method loadSysConfigRecordFromDB()

	/** END Code generated DataAccess Methods For Table systemconfiginfo ********/
	/** END Code generated DataAccess Methods For Table systemconfiginfo ********/
	/** END Code generated DataAccess Methods For Table systemconfiginfo ********/

}
/* End Of Generated DataAccess Class ABRESysConfigDBDAO */
