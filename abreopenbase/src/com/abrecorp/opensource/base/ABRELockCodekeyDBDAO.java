package com.abrecorp.opensource.base;

/**
* generated DataAccess class
* For Table lockcodekey
*/

public class ABRELockCodekeyDBDAO extends ABREBase {

	/**
	** Constructor
	*/
	public ABRELockCodekeyDBDAO(){}

	/** START Code generated DataAccess Methods For Table lockcodekey ********/
	/** START Code generated DataAccess Methods For Table lockcodekey ********/
	/** START Code generated DataAccess Methods For Table lockcodekey ********/


	/**
	* insert a row into table lockcodekey
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertLockCodekeyIntoDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL101 = " INSERT INTO lockcodekey ( " +
			"lock_number, codekey, create_dt, status, processid)" +
			" values(?,?, {fn now()} ,?,?)" ;
		MethodInit("insertLockCodekeyIntoDB");
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getLOKLockNumber() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getLOKCodekey() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getLOKStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getLOKProcessid() );
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
	// end-of method insertLockCodekeyIntoDB()


	/**
	* update a row in table lockcodekey
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateLockCodekeyInDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE lockcodekey SET " +
			",lock_number=?,codekey=?,status=?,processid=?" +
			" WHERE something=? ";
		MethodInit("updateLockCodekeyInDB");
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getLOKLockNumber() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getLOKCodekey() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getLOKStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getLOKProcessid() );
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
	// end-of method updateLockCodekeyInDB()

	/**
	* delete a row from table lockcodekey
	*/
	public int deleteDBLOK(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL=null;
		String aszSQL101 = "DELETE FROM lockcodekey WHERE lock_number= " ;
		MethodInit("deleteDBLOK");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		aszSQL = aszSQL101 + "something" ;
		iRetCode = pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunUpdate(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method deleteDBLOK()


	/**
	* select one item from table lockcodekey
	*/
	public int loadLockCodekeyFromDB(ABREDBConnection pConn, SystemRequestInfoDTO aHeadObj, int iType, int iInPut1, String aszInPut2){
		int iRetCode=0,iCount=0;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
			"lock_number,codekey,create_dt,status,processid" +
			" FROM lockcodekey " ;
		MethodInit("loadLockCodekeyFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		switch(iType){
			// all items
			case 1:
				aszSQL2 = aszSQL101 ;
				break;
			default:
				setErr("type not supported");
				return 1;
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
			aHeadObj.setLOKLockNumber(pConn.getDBInt("lock_number"));
			aHeadObj.setLOKCodekey(pConn.getDBString("codekey"));
			aHeadObj.setLOKCreateDt(pConn.getDBTimestamp("create_dt"));
			aHeadObj.setLOKStatus(pConn.getDBInt("status"));
			aHeadObj.setLOKProcessid(pConn.getDBString("processid"));
		}
		return iRetCode;
	}
	// end-of method loadLockCodekeyFromDB()


	/** END Code generated DataAccess Methods For Table lockcodekey ********/
	/** END Code generated DataAccess Methods For Table lockcodekey ********/
	/** END Code generated DataAccess Methods For Table lockcodekey ********/


}
/* End Of Generated DataAccess Class ABRELockCodekeyDBDAO */
