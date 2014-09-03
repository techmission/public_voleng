package com.abrecorp.opensource.base;

/**
* A class that holds a single database connection.  
* Provides connection pooling services.  
* Part of the database manager set of classes.
* @version 5.0
* @author David Marcillo
*/

import java.util.*;
import java.sql.*;
import java.text.DateFormat;

public class ABREDBConnection extends ABREBase {

    /**
    * Constructor
    */
    public ABREDBConnection(){}


	//=== START public JDBC methods =====>
	//=== START public JDBC methods =====>
	//=== START public JDBC methods =====>
	//=== START public JDBC methods =====>
	//=== START public JDBC methods =====>
	//=== START public JDBC methods =====>


	/**
	* Creates a new PreparedStatement object containing the pre-compiled statement.
	*/
	public int PrepQuery(String qry){
		return prepareDBPrepQuery( qry );
	}
	// end-of method ExecDBPrepQry()


	/**
	* Execute a SQL statement that returns a single ResultSet.  
	* Returns -1 if error other wise 0 for no errors
	*/
	public int RunUpdateQuery(String qry){
		int iRetCode = AllocStatement();
		if(0 != iRetCode) return iRetCode;
		return RunDBUpdateQuery( qry ) ;
	}
	// end-of method RunUpdateQuery()


	/**
	* Get the next record from a ResultSet.
	*/
	public boolean getNextResult(){
		return getNextDBResult();
	}
	// end-of method getNextResult()

	
	public String getCurrentSQL(){
        return null;
		// return m_PrimaryStatement.getCurrentQuery();
	}


	/**
	* get in use start clock time
	*/
	public long getInUseStartTime(){
	 return m_lInuseStartTime;
	}

	/**
	* get column Count
	*/
	public int getColumnCount(){
		return getTableColumnCountInternal();
	}
    // end-of getColumnCount()

	/**
	 * get column type
	 */
	public int getColumnType(int index){
		return ColumnType(index);
	}

	/**
	 * get column precision size
	 */
	public int ColumnPrecisionSize(int index){
		return ColumnPrecision(index);
	}

	/**
	 * get column scalar size
	 */
	public int ColumnScalarSize(int index){
		return ColumnScalar(index);
	}

	/**
	 * get is column currency content
	 */
	public boolean ColumnIsCurrency(int index){
		return ColumnCurrency(index);
	}

	/**
	* get current table or query column label name
	*/
	public String getColumnLabel(int index){
		return ColumnLabel(index);
	}
    // end-of getColumnLabel()

	/**
	 * get column type Name
	 */
	public String getColumnNameType(int index){
		return ColumnTypeName(index);
	}

	/**
	 * get column table schedma Name
	 */
	public String getColumnSchemaName(int index){
		return ColumnSchemaName(index);
	}

	/**
	 * get column java Name
	 */
	public String getColumnClassName(int index){
		return ColumnClassName(index);
	}

	/**
	 * get column display size
	 */
	public int getColumnDisplaySize(int index){
		return ColumnDisplaySize(index);
	}

	/**
	* Replace all the single quotes in the SQL statement with two single quotes to prepare for the query.
	*/
	public String replacesinglequote(String s){
		if (s==null){ return "";}
		if (s.length()==0) {return "";}
		int pos=s.indexOf("'");
        if(pos < 1) return s;
		String temp="";
		while(pos>-1){
			temp+=s.substring(0,pos+1)+"'";
			s=s.substring(pos+1);
			pos=s.indexOf("'");
		}
		temp+=s;
		return temp;
	}

	/**
	** Return in in-use status.
	*/
	public boolean isUsed(){
		if(0 == m_iInUseStatus){
			return false;
		} else {
			return true;
		}
	}

	/**
	* Return in use status.
	*/
	public int used(){
		return m_iInUseStatus;
	}
	/**
		Set in use status flag to in use.
	*/
	public void setInuse(){
		ClearConnection();
		m_iInUseStatus=1;
		m_lInuseStartTime = System.currentTimeMillis();
	}
	/**
		Clear the connection and set in use status flag to not in use.
	*/
	public void setNotInuse(){
		ClearConnection();
		m_iInUseStatus=0;
		m_lInuseStartTime = System.currentTimeMillis();
	}
	/**
		Set the index.
	*/
	public int setIndex(int item){
		if(item < 1){
			return 1;
		}
		if(iItemIdex > 0){
			return 1;
		}
		iItemIdex = item ;
		return 0;
	}
	/**
		Return the index for an item.
	*/
	public int getIndex(int item){
		return iItemIdex;
	}
	/**
		Sets the database class name.
	*/
	public int setDbClassName( String name ){
		MethodInit("setDbClassName");
		if(null != m_DbClassName){
			setErr("m_DbClassName variable not null");
			return 1;
		}
		if(null == name){
			setErr("null input name");
			return 1;
		}
		m_DbClassName = name ;
		return 0;
	}
	/**
		Set the database URL.
	*/
	public int setDbUrl( String url ){
		MethodInit("setDbUrl");
		if(null != m_DatabaseURL){
			setErr("m_DatabaseURL variable not null");
			return 1;
		}
		if(null == url){
			setErr("null input url");
			return 1;
		}
		m_DatabaseURL = url;
		return 0;
	}
	/**
		Set the User ID.
	*/
	public int setUserID( String user ){
		MethodInit("setUserID");
		if(null != m_DBManagerUserID){
			setErr(" userid variable not null");
			return 1;
		}
		if(null == user){
			setErr("null input user");
			return 1;
		}
		m_DBManagerUserID = user;
		return 0;
	}

	
	/**
	 * Returns the number of times that this connection object has been connected to the database.
	 */
	protected int getConnectionCount()
	{
		return m_connCt;
	}
	
	
	/**
	 * Returns the number of connection related errors that have occured in this object.
	 */
	protected int getConnectionErrorCount()
	{
		return m_connErrCt;
	}


	/**
		Set the password.
	*/
	public int setPassword( String pass ){
		MethodInit("setPassword");
		if(null != m_DBManagerPassword){
			setErr(" password variable not null");
			return 1;
		}
		if(null == pass){
			setErr("null input password");
			return 1;
		}
		m_DBManagerPassword = pass;
		return 0;
	}

	/**
	* disconnect this connection from database
	*/
	public int diconnectFromDB(){
		MethodInit("diconnectFromDB");
		ClearConnection();
		m_iInUseStatus=0;
		// Are we allready connected ?
		if(null != m_JDBCconnectionObj){
			try {
				m_JDBCconnectionObj.close();
			} catch (SQLException ex) {
				m_JDBCconnectionObj=null;
			}
		}
		m_JDBCconnectionObj=null;
		m_lInuseStartTime = System.currentTimeMillis();
		return 0;
	}
    // end-of diconnectFromDB()

	/**
	* Attempt to establish a connection to the given database URL. 
	* The DriverManager attempts to select an appropriate driver from the set of registered JDBC drivers. 
	*/
	public int connect(){
		int iRetCode;
		boolean connClosed=true;
		MethodInit("connect");
		// Are we allready connected ?
		if(null != m_JDBCconnectionObj){
			try{
				connClosed = m_JDBCconnectionObj.isClosed(); 
			} catch (SQLException ex) {
				connClosed = true;
				m_JDBCconnectionObj=null;
			}
			if(false == connClosed){
				return 0;
			}
		}
		// Connect to database
		iRetCode = ConnectToDB();
		return iRetCode;
	}
    // end-of connect()

	/**
	* Allocate database connection Statement.  
	* SQL statements without parameters are normally executed using Statement objects. 
	* If the same SQL statement is executed many times, it is more efficient to use a PreparedStatement 
	*/
	public int getDBStmt(){
		int iRetCode;
		iRetCode = AllocStatement();
		return iRetCode;
	}
    // end-of getDBStmt()

	/**
		Allocate database connection Statement.  
		Note:  don't use this, use getDBStmt instead.
	*/
	public Statement getStatement(){
		int iRetCode;
		iRetCode = AllocStatement();
		if(1 == iRetCode){
			return null;
		}
		return m_JDBCstatement;
	}

	/**
	* Execute a prepared SQL statement query.
	*/
	public int ExePrepQry(){
		int iRetCode=1;
		iRetCode = PrepQry_exe();
		return iRetCode;
	}

	/**
	* Set the maximum number of rows to be returned to a resultset from a query.
	*/
	public int setMaxRows(int i){
		return setDBMaxReturnRows(i);
	}
    // end-of method setMaxRows()




	/**
	*	Set a long parameter value in a prepared SQL statement.
	*/
	public int setPrepQryInt(int index, int item){
		return setDBPrepQryIntItem( item );
	}

	/**
	*	Set a Long parameter value in a prepared SQL statement.
	*/
	public int setPrepQryLong(long item){
		return setDBPrepQryLongItem( item );
	}

	/**
	*	Set a int parameter value in a prepared SQL statement.
	*/
	public int setPrepQryInt( int item ){
		return setDBPrepQryIntItem( item );
	}
	// end-of method setPrepQryInt()

	/**
	* Set a string parameter value in a prepared SQL statement.
	*/
	public int setPrepQryString( String item ){
		return setDBPrepQryStringItem( item );
	}
	// end-of method setPrepQryString()

	/**
	* Set a date parameter value in a prepared SQL statement.
	*/
	public int setPrepQryDate( java.util.Date item ){
		return setDBPrepQueryDateItem( item );
	}
	// end-of method setPrepQryDate()

	/**
	* Set a date parameter value in a prepared SQL statement.
	*/
	public int setPrepQryDateNull( java.util.Date item ){
		return setDBPrepQueryDateNullItem( item );
	}
	// end-of method setPrepQryDate()



	/**
	*  Set a double parameter value in a prepared SQL statement.
	*/
	public int setPrepQryDouble( double item ){
		return setDBPrepQueryDoubleItem( item );
	}
	// end-of method setPrepQryDouble()


	/**
	* Execute a prepared SQL statement query.
	*/
	public int ExecutePrepQry(){
		return executeDBPreparedQueryStatement();
	}
	// end-of method ExecutePrepQry()

	/**
	* Execute a prepared SQL statement update query.
	*/
	public int ExeUpdtPrepQry(){
		return executeDBPreparedQueryStatement();
	}
	// end-of method ExeUpdtPrepQry()

	/**
	* Creates a new PreparedStatement object containing the pre-compiled statement.
	*/
	public int PrepQry(String qry){
		return prepareDBPrepQuery( qry );
	}

	// Step 2 - Run A Query And Return A Resultset
	// Submit a query, creating a ResultSet object
	/**
	* Execute a SQL statement that returns a single ResultSet.  Returns a return code.
	*/
	public int RunQry(String qry){
		int iRetCode=1;
		MethodInit("RunQry");
		result_close();
		try {
			m_JDBCresultset = m_JDBCstatement.executeQuery (qry);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			setErr("error running Qry: " + qry);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			setErr("error running Qry: " + qry);
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		if(0 != iRetCode){
			CheckForReConnect();
            // ErrorsToLog();
		}
		m_CurrentQueryStatement=qry;
		return iRetCode;
	}
    // end-of RunQry()

	/**
	*	Execute a SQL INSERT, UPDATE or DELETE statement. 
	*	In addition, SQL statements that return nothing such as SQL DDL statements can be executed. 
	*/
	public int RunUpdate(String qry){
		int iRetCode=1;
//System.out.println("line 513 - qry is "+qry);
		if(null == m_JDBCstatement) return 1;
		result_close();
		try {
//System.out.println("line 517 - run m_JDBCstatement.executeUpdate(qry) ");
			m_affectedRows = m_JDBCstatement.executeUpdate(qry);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			if (ex.getErrorCode() == 1062){ // added 2008-08-20 - ali - written in to handle a duplicate in some DBDAO cases (drupalized taxonomy restrictions)
				iRetCode=1062;
			}
			MethodInit("RunUpdate");
			setErr("Qry: " + qry);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("RunUpdate");
			setErr("Qry: " + qry);
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_CurrentQueryStatement=qry;
		return iRetCode;
	}
    // end-of RunUpdate()


	/**
	* Execute a SQL statement that returns a single ResultSet.  
	* Returns -1 if error other wise 0 for no errors
	*/
	public int RunQuery(String qry){
		return execDBQueryString( qry ) ;
	}
	// end-of method RunQuery()


	/**
	* Get a specific record from current ResultSet.
	*/
	public long getDBLong(String itemname){
		return getDBLongItem(itemname);
	}
	// end-of method getDBLong()

	/**
	* Get a specific record from current ResultSet.
	*/
	public int getDBInt(String itemname){
		if(itemname==null ){
System.out.println("inside getDBInt; itemname is : "+itemname);
		}
		return getDBIntItem(itemname);
	}
	// end-of method getDBInt()

	/**
	* Get a specific record from current ResultSet.
	*/
	public double getDBDouble(String itemname){
		return getDBDoubleItem(itemname);
	}
	// end-of method getDBDouble()



	/**
	* Get a specific record from current ResultSet.
	*/
	public String getDBString(String itemname){
		return getDBStringItem(itemname);
	}
	// end-of method getDBString()


	/**
	* Get the value of a column in the current row as a Java java.sql.Date.
	*/
	public java.sql.Date getDBDate(String itemname){
		return getDBDateItem(itemname);
	}
	// end-of method getDBDate()


	/**
	* Returns a datebase date/time field as a java.sql.Timestamp object. This object extends(thinly) a java.util.Date so it can 
	* be used more or less interchangably (as long as you don't mind loosing the nanoseconds, which no database supports anyway).
	* java.sql.
	*/
	public Timestamp getDBTimestamp(String itemname){
		return getDBTimestampItem(itemname);
	}
	// end-of method getDBTimestamp()



	/**
	* Close the ResultSet, the Statement and the PreparedStatement.
	*/
	public void statement_close(){
		CloseAllStmts();
	}
    // end-of statement_close()

	/**
	* Disable auto commit off for the connection.
	*/
	public int StartTransaction(){
		return setAutoCommitOff();
	}
    // end-of StartTransaction()

	/**
	* Commit makes all changes made since the previous commit/rollback permanent and releases any database locks currently held by the Connection. 
	* This method should only be used when auto commit has been disabled. 
	*/
	public int commit(){
		return setCommit();
	}
    // end-of commit()

	/**
	* Rollback drops all changes made since the previous commit/rollback and releases any database locks currently held by the Connection.
	* This method should only be used when auto commit has been disabled. 
	*/
	public int rollback(){
		return setRollBack();
	}
    // end-of rollback()

	/**
	* Returns the connection to the connection pool.  
	* Close the ResultSet, the Statement and the PreparedStatement.  
	* Enables auto commit for the connection.  
	* Set the in use indicator for the connection to false.
	*/
	public void free(){
		close();
	}
    // end-of free()

	public void close(){
		ClearConnection();
		m_iInUseStatus=0;
		m_lInuseStartTime = System.currentTimeMillis();
		m_CurrentQueryStatement=null;
	}
    // end-of close()

	/**
	* Returns the number of rows affected by the last INSERT, UPDATE or DELETE query.  
	* Not available for SELECT statements (value is not returned for SELECT queries).
	*/
	public int rowsAffected() {
		return m_affectedRows;
	}
    // end-of rowsAffected()

	/**
	*  Returns true if this connection is actually connected to the database
	*/
	protected boolean isConnected(){
		if (m_JDBCconnectionObj == null)
			return false;
		else {
			try {
				return !m_JDBCconnectionObj.isClosed();
				}
			catch (SQLException e) {
				return false;
				}
			}
	}
    // end-of method isConnected()



	//=== START private JDBC methods =====>
	//=== START private JDBC methods =====>
	//=== START private JDBC methods =====>
	//=== START private JDBC methods =====>
	//=== START private JDBC methods =====>


	/**
	*	Execute a SQL INSERT, UPDATE or DELETE statement. 
	*	In addition, SQL statements that return nothing such as SQL DDL statements can be executed. 
	*/
	private int RunDBUpdateQuery(String qry){
		int iRetCode=1,iCount=-1;

		if(null == m_JDBCstatement) return 1;
		result_close();
		try {
			iCount = m_JDBCstatement.executeUpdate(qry);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("RunDBUpdateQuery");
			setErr("Qry: " + qry);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("RunDBUpdateQuery");
			setErr("Qry: " + qry);
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_CurrentQueryStatement=qry;
		return iRetCode;
	}
    // end-of RunDBUpdateQuery()



	/**
	* Creates a new PreparedStatement object containing the pre-compiled statement.
	*/
	private int prepareDBPrepQuery(String qry){
		int iRetCode=1;
		m_iPrepQueryItemIndex=0;
		CloseAllStmts();
		m_CurrentQueryStatement=qry;
		try {
			m_PreStatement = m_JDBCconnectionObj.prepareStatement(qry);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("PrepQry");
			setErr("Qry: " + qry);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("PrepQry");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
//
//		CloseAllStmts(); // 2008-12-17 -> would this then allow me to be able to run a RunQry/RunQuery/RunUpdate after a prep?
//
		return iRetCode;
	}
	// end-of method prepareDBPrepQuery()

	/**
	*  Set a double parameter value in a prepared SQL statement.
	*/
	private int setDBPrepQueryDoubleItem( double item){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setPrepQryDouble");
			setErr("null statement");
			return 1;
		}
		try {
			 m_PreStatement.setDouble(m_iPrepQueryItemIndex,item);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setPrepQryDouble");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setPrepQryDouble");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
	// end-of method setDBPrepQueryDoubleItem()

	/**
	* Set a date parameter value in a prepared SQL statement.
	* if the date is null then null passed to database
	*/
	private int setDBPrepQueryDateNullItem( java.util.Date item ){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setDBPrepQueryDateNullItem");
			setErr("null m_PreStatement");
			return 1;
		}
        java.util.Date dtNow=null;
        java.sql.Date dtToday=null; 
        if(null == item){
            dtNow=null; 
        	dtToday=null; 
        } else {
            dtNow = item;
        	dtToday = new java.sql.Date(dtNow.getYear(), dtNow.getMonth(), dtNow.getDate()); 
        }
		try {
			 m_PreStatement.setDate(m_iPrepQueryItemIndex,dtToday);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setDBPrepQueryDateNullItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setDBPrepQueryDateNullItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of setDBPrepQueryDateNullItem()


	/**
	* Set a date parameter value in a prepared SQL statement.
	*/
	private int setDBPrepQueryDateItem( java.util.Date item ){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setDBPrepQueryDateItem");
			setErr("null statement");
			return 1;
		}
        java.util.Date dtNow=null;
        if(null == item){
            dtNow = new java.util.Date(); 
        } else {
            dtNow = item;
        }
        java.sql.Date dtToday = new java.sql.Date(dtNow.getYear(), dtNow.getMonth(), dtNow.getDate()); 
		try {
			 m_PreStatement.setDate(m_iPrepQueryItemIndex,dtToday);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setDBPrepQueryDateItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setDBPrepQueryDateItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of setDBPrepQueryDateItem()

	/**
	* Set a string parameter value in a prepared SQL statement.
	*/
	private int setDBPrepQryStringItem( String item){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setDBPrepQryStringItem");
			setErr("null statement");
			return 1;
		}
		// String temp=replacesinglequote(item);
		try {
			 m_PreStatement.setString(m_iPrepQueryItemIndex,item);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryStringItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryStringItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
	// end-of method setDBPrepQryStringItem()

	/**
	*	Set a int parameter value in a prepared SQL statement.
	*/
	private int setDBPrepQryIntItem( int item ){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setDBPrepQryIntItem");
			setErr("null statement");
			return 1;
		}
		try {
			 m_PreStatement.setInt(m_iPrepQueryItemIndex,item);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryIntItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryIntItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
	// end-of method setDBPrepQryIntItem()


	/**
	*	Set a long parameter value in a prepared SQL statement.
	*/
	private int setDBPrepQryLongItem( long item ){
		int iRetCode=1;
		m_iPrepQueryItemIndex++;
		if(null == m_PreStatement){
			MethodInit("setDBPrepQryIntItem");
			setErr("null statement");
			return 1;
		}
		try {
			 m_PreStatement.setLong(m_iPrepQueryItemIndex,item);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryIntItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setDBPrepQryLongItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
	// end-of method setDBPrepQryLongItem()


	/**
	* Get the value of a column in the current row as a Java int.
	*/
	private int getDBIntItem(String index){
		int lresult=0;
		if(null == m_JDBCresultset){
System.out.println("inside getDBIntItem  m_JDBCresultset is null");
			return -1;
		}
		try {
			lresult = m_JDBCresultset.getInt(index);
		} catch (SQLException ex) {
System.out.println("error in getDBIntItem - SQLException ex ");
			MethodInit("getDBIntItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
System.out.println("   SQLException ex "+ex);
			}
            ErrorsToLog();
		} catch (java.lang.Exception ex) {
System.out.println("error in getDBIntItem - java.lang.Exception ex) "+ex.getMessage());
			MethodInit("getDBIntItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
            ErrorsToLog();
		}
		return lresult;
	}
    // end-of method getDBIntItem()

	/**
	* Get the value of a column in the current row as a Java long.
	*/
	private long getDBLongItem(String index){
		long lresult=0;
		if(null == m_JDBCresultset) return -1;
		try {
			lresult = m_JDBCresultset.getLong(index);
		} catch (SQLException ex) {
			MethodInit("getLong");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
            ErrorsToLog();
		} catch (java.lang.Exception ex) {
			MethodInit("getLong");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
            ErrorsToLog();
		}
		return lresult;
	}
    // end-of method getDBLongItem()

	/**
	* Get the value of a column in the current row as a Java double.
	*/
	private double getDBDoubleItem(String index){
		double dresult=0;
		if(null == m_JDBCresultset) return -1;
		try {
			dresult = m_JDBCresultset.getDouble(index);
		} catch (SQLException ex) {
			MethodInit("getDBDoubleItem");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
            ErrorsToLog();
		} catch (java.lang.Exception ex) {
			MethodInit("getDBDoubleItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
            ErrorsToLog();
		}
		return dresult;
	}	
    // end-of method getDBDoubleItem()

	/**
	* Get the value of a column in the current row as a Java java.sql.Date.
	*/
	private java.sql.Date getDBDateItem(String index){
		java.sql.Date result = null;
		if(null == m_JDBCresultset) return null;
		if(0 == m_iInUseStatus){
			MethodInit("getDBDateItem");
			setErr("connection not in use");
			return null;
		}
		try {
			result = m_JDBCresultset.getDate(index);
		} catch (SQLException ex) {
			MethodInit("getDBDateItem() for " + index);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
            ErrorsToLog();
		} catch (java.lang.Exception ex) {
			MethodInit("getDBDateItem");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
            ErrorsToLog();
		}
		return result;
	}
    // end-of method getDBDateItem()

	/**
	* Get the value of a column in the current row as a Java String.
	*/
	private String getDBStringItem(String index){
		String sresult="";
		if(null == m_JDBCresultset) return null;
		try {
			sresult = m_JDBCresultset.getString(index);
			if(null == sresult){
				sresult="";
			}
		} catch (SQLException ex) {
			MethodInit("getDBStringItem() for " + index);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
            ErrorsToLog();
		} catch (java.lang.Exception ex) {
			MethodInit("getString");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
            ErrorsToLog();
		}
		return sresult.trim();
	}
    // end-of method getDBStringItem()

	/**
	* Returns a datebase date/time field as a java.sql.Timestamp object. This object extends(thinly) a java.util.Date so it can 
	* be used more or less interchangably (as long as you don't mind loosing the nanoseconds, which no database supports anyway).
	* java.sql.
	*/
	private Timestamp getDBTimestampItem(String index){
		java.sql.Timestamp result = null;
		if(null == m_JDBCresultset) return null;
		if(0 == m_iInUseStatus){
			MethodInit("getTimestamp");
			setErr("connection not in use");
			return null;
		}
		try {
			result = m_JDBCresultset.getTimestamp(index);
		} catch (SQLException ex) {
			MethodInit("getTimestamp() for " + index);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			MethodInit("getTimestamp");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return result;
	}
    // end-of method getDBTimestampItem()


	/**
    * Get the next record from a ResultSet.
	*/
	private boolean getNextDBResult(){
		boolean iret=false;
		if(null == m_JDBCresultset) return false;
		try {
			iret=m_JDBCresultset.next();
		} catch (SQLException ex) {
			MethodInit("getNextDBResult");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			MethodInit("getNextDBResult");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iret;
	}
    // end-of method getNextDBResult()

	/**
	* Execute a SQL statement that returns a single ResultSet.  
	* Returns a ResultSet.
	*/
	private int execDBQueryString(String qry){
        int iRetCode=0;
		result_close();
		try {
			m_JDBCresultset = m_JDBCstatement.executeQuery (qry);
//System.out.println("m_JDBCresultset is "+m_JDBCresultset);			
		} catch (SQLException ex) {
			MethodInit("RunQuery");
			iRetCode=-1;
            iRetCode=ex.getErrorCode();
			setErr("Qry: " + qry);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			MethodInit("RunQuery");
			iRetCode=-1;
			setErr("Qry: " + qry);
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_CurrentQueryStatement=qry;
		return iRetCode;
	}
    // end-of method execDBQueryString()

	/**
	* Set the maximum number of rows to be returned to a resultset from a query.
	*/
	private int setDBMaxReturnRows(int i){
		int iRetCode=1;
		int m_result;
		if (i<=0) return  1;
		if(null == m_JDBCstatement) return 1;
		result_close();
		try {
			m_JDBCstatement.setMaxRows(i);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setMaxRows");
			setErr("i: " + i);
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setMaxRows");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of method setMaxRows()

	/**
	* check current sql server error to see if we need to reconnect to the database
	*/
	private void CheckForReConnect(){
		int iRetCode=0;
        int iReconnect=0;
		if((10054 == m_iSQLErrorCode) || ((11 == m_iSQLErrorCode))){
            iReconnect=1;
		}
        if(m_aszSQLErrorState != null){
            if(m_aszSQLErrorState.equalsIgnoreCase("HY000")){
                iReconnect=1;
            }
            if(m_aszSQLErrorState.equalsIgnoreCase("08S01")){
                iReconnect=1;
            }
            if(m_aszSQLErrorState.equalsIgnoreCase("08003")){
                iReconnect=1;
            }
        }
        if(iReconnect == 1){
			setErr("attempting database reconnection");
			try {
				m_JDBCconnectionObj.close();
			} catch (SQLException ex) {
				m_JDBCconnectionObj=null;
			}
			m_JDBCconnectionObj=null;
			iRetCode=ConnectToDB();
			if(0 != iRetCode){
				setErr("failed... database reconnected");
			} else {
				setErr("OK... database reconnected");
                m_aszSQLErrorState=null;
                m_iSQLErrorCode=0;
			}
        }
	}
    // end-of method CheckForReConnect()

    /**
    * Connect This JDBC Connection object to database socket 
    */
	private int ConnectToDB(){
		int iRetCode=1;
		try {
			Class.forName (m_DbClassName);
			// connect to data source
			m_JDBCconnectionObj = DriverManager.getConnection(m_DatabaseURL,m_DBManagerUserID,m_DBManagerPassword);
			// Check warnings
			// checkForWarning (m_JDBCconnectionObj.getWarnings ());
			// Get Database MetaData information
			// m_dbmeta = m_JDBCconnectionObj.getMetaData ();
			// setErr("Connected to: " + m_dbmeta.getURL());
			// setErr("Driver: " + m_dbmeta.getDriverName());
			// setErr("Version: " + m_dbmeta.getDriverVersion());
			// setErr("Database: " + m_JDBCconnectionObj.getCatalog());
			// System.out.println("");
			iRetCode=0;
            m_connCt++;
			m_lInuseStartTime = System.currentTimeMillis();
		} catch (SQLException ex) {
			iRetCode=1;
			while (ex != null) {
				m_iSQLErrorCode = ex.getErrorCode();
                m_aszSQLErrorState = ex.getSQLState();
                if(m_aszSQLErrorState != null){
                    if(m_aszSQLErrorState.equalsIgnoreCase("HY000")){
                        ClearConnection();
                        iRetCode=12345678;
                    }
                    if(m_aszSQLErrorState.equalsIgnoreCase("08S01")){
                        ClearConnection();
                        iRetCode=12345678;
                    }
                    if(m_aszSQLErrorState.equalsIgnoreCase("08001")){
                        ClearConnection();
                        iRetCode=12345678;
                    }
                    if(m_aszSQLErrorState.equalsIgnoreCase("08003")){
                        ClearConnection();
                        iRetCode=12345678;
                    }
                }
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of ConnectToDB()

	/**
	*  run prepare query statement
	*/
	private int PrepQry_exe(){
		int iRetCode=1;
		if(null == m_PreStatement){
			MethodInit("PrepQry_exe");
			setErr("null prep statement");
			return 1;
		}
		result_close();
		try {
			m_JDBCresultset = m_PreStatement.executeQuery();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("PrepQry_exe");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		//CloseAllStmts(); // 2008-12-17 -> would this then allow me to be able to run a RunQry/RunQuery/RunUpdate after a prep?  Looks like it does not 2009-01-16
		return iRetCode;
	}
    // end-of method PrepQry_exe()

	/**
	*  run prepare update statement
	*/
	private int executeDBPreparedQueryStatement(){
		int iRetCode=1;
		int iCount=0;
		if(null == m_PreStatement){
			MethodInit("executeDBPreparedQueryStatement");
			setErr("null statement");
			return 1;
		}
		try {
			iCount = m_PreStatement.executeUpdate();
			if(iCount < 1){
				iRetCode=1;
				MethodInit("executeDBPreparedQueryStatement");
				setErr(m_CurrentQueryStatement);
				setErr("no records updated");
			}

			iRetCode=0;
		} catch (SQLException ex) {
System.out.println("SQLException   "+ex);

			MethodInit("executeDBPreparedQueryStatement");
			setErr(m_CurrentQueryStatement);
            iRetCode=ex.getErrorCode();
            if(0 == iRetCode) iRetCode=1;
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
//System.out.println("SQLException "+ex);				
			}
		} catch (java.lang.Exception ex) {			
			iRetCode=1;
			MethodInit("executeDBPreparedQueryStatement");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
System.out.println("java.lang.Exception  "+ex);				
		}
		// 2009-01-20 - the following line might not be necessary, since RunQry/RunQuery/RunUpdate after a prep can be ExePrepQry/ExecutePrepQry afterwards
		CloseAllStmts(); // 2008-12-17 -> would this then allow me to be able to run a RunQry/RunQuery/RunUpdate after a prep?  Looks like it does not 2009-01-16
		return iRetCode;
	}
    // end-of method executeDBPreparedQueryStatement()


	/**
	* rollback current transaction
	*/
	private int setRollBack(){
		int iRetCode;
		try {
			m_JDBCconnectionObj.rollback();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setRollBack");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setRollBack");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of method setRollBack()

	/**
	* commit current transaction
	*/
	private int setCommit(){
		int iRetCode;
		try {
			m_JDBCconnectionObj.commit();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setCommit");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setCommit");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of method setCommit()

	/**
	* set connection to auto commit off
	*/
	private int setAutoCommitOff(){
		int iRetCode;
		CloseAllStmts();		
		try {
			m_JDBCconnectionObj.setAutoCommit(false);
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("setAutoCommitOff");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("setAutoCommitOff");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		return iRetCode;
	}
    // end-of method setAutoCommitOff()

	/**
	* set connection to auto commit on
	*/
	private void setAutoCommitOn(){
        if(null == m_JDBCconnectionObj) return ;
		try {
			m_JDBCconnectionObj.setAutoCommit(true);
		} catch (SQLException ex) {
			MethodInit("setAutoCommitOn");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			MethodInit("setAutoCommitOn");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
	}
    // end-of method setAutoCommitOn()

    //======== Start MATA DATA Section ====>
    //======== Start MATA DATA Section ====>
    //======== Start MATA DATA Section ====>

	/**
	* get JDBC result set meta data
	*/
	private int getJDBCresultsetMetaData(){
		if(null == m_JDBCresultset){
			setErr("null result set");
			return 1;
		}
		if(null != m_Result_Meta) return 0;
		try {
			m_Result_Meta = m_JDBCresultset.getMetaData();
		} catch (SQLException ex) {
			MethodInit("getJDBCresultsetMetaData");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return 1;
		}
		return 0;
	}
    // end-of getJDBCresultsetMetaData()

	/**
	* Retrieves the designated column's SQL type
	*/
	private int ColumnType(int index){
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return -1;
		try {
			iCount = m_Result_Meta.getColumnType(index);
		} catch (SQLException ex) {
			MethodInit("ColumnType");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return -1;
		}
		return iCount;
	}
    // end-of method ColumnType()

	/**
	 * Indicates the designated column's normal maximum width in characters
	 */
	private int ColumnDisplaySize(int index){
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return -1;
		try {
			iCount = m_Result_Meta.getColumnDisplaySize(index);
		} catch (SQLException ex) {
			MethodInit("ColumnDisplaySize");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return -1;
		}
		return iCount;
	}
    // end-of method ColumnDisplaySize()

	/**
	* Gets the designated column's suggested title for use in printouts and displays
	*/
	private String ColumnLabel(int index){
		String name=null;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return null;
		try {
			name = m_Result_Meta.getColumnLabel(index);
		} catch (SQLException ex) {
			MethodInit("ColumnLabel");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return null;
		}
		return name;
	}
    // end-of method ColumnLabel()

	/**
	* Retrieves the designated column's database-specific type name
	*/
	private String ColumnTypeName(int index){
		String name=null;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return null;
		try {
			name = m_Result_Meta.getColumnTypeName(index);
		} catch (SQLException ex) {
			MethodInit("ColumnTypeName");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return null;
		}
		return name;
	}
    // end-of method ColumnTypeName()

	/**
	* Get the designated column's table's schema.
	*/
	private String ColumnSchemaName(int index){
		String name=null;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return null;
		try {
			name = m_Result_Meta.getSchemaName(index);
		} catch (SQLException ex) {
			MethodInit("ColumnSchemaName");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return null;
		}
		return name;
	}
    // end-of ColumnSchemaName()

	/**
	* Returns the fully-qualified name of the Java class whose instances are 
    * manufactured if the method ResultSet.getObject is called to retrieve a value from the column.
	*/
	private String ColumnClassName(int index){
		String name=null;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return null;
		try {
			name = m_Result_Meta.getColumnClassName(index);
		} catch (SQLException ex) {
			MethodInit("ColumnClassName");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return null;
		}
		return name;
	}
    // end-of ColumnClassName()

	/**
	* get table column count
	*/
	private int getTableColumnCountInternal(){
		int iCount=getJDBCresultsetMetaData();
		if(0 != iCount) return -1;
		try {
			iCount = m_Result_Meta.getColumnCount();
		} catch (SQLException ex) {
			MethodInit("getTableColumnCountInternal");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return -1;
		}
		return iCount;
	}
    // end-of getTableColumnCountInternal()

	/**
	* Get the designated column's number of decimal digits
	*/
	private int ColumnPrecision(int index){
		int iPrec=-1;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return -1;
		try {
			iPrec = m_Result_Meta.getPrecision(index);
		} catch (SQLException ex) {
			MethodInit("ColumnPrecision");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return -1;
		}
		return iPrec;
	}
    // end-of ColumnPrecision()

	/**
	* Gets the designated column's number of digits to right of the decimal point.
	*/
	private int ColumnScalar(int index){
		int iPrec=-1;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return -1;
		try {
			iPrec = m_Result_Meta.getScale(index);
		} catch (SQLException ex) {
			MethodInit("ColumnScalar");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return -1;
		}
		return iPrec;
	}
    // end-of ColumnScalar()

	/**
	* Indicates whether the designated column is a cash value.
	*/
	private boolean ColumnCurrency(int index){
		boolean iPrec=false;
		int iCount=getTableColumnCountInternal();
		if(iCount < index) return false;
		try {
			iPrec = m_Result_Meta.isCurrency(index);
		} catch (SQLException ex) {
			MethodInit("ColumnCurrency");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
			return false;
		}
		return iPrec;
	}
    // end-of ColumnCurrency()

    //======== End MATA DATA Section ====>
    //======== End MATA DATA Section ====>
    //======== End MATA DATA Section ====>

	/**
	* save SQL error messages
	*/
	private void setSQLErr(SQLException ex){
        m_iSQLErrorCode = ex.getErrorCode();
        m_aszSQLErrorState = ex.getSQLState();
        if(null == m_aszSQLErrorState) m_aszSQLErrorState="";
        if(m_aszSQLErrorState.equalsIgnoreCase("08003")){
    		setErr("SQLState: " + ex.getSQLState());
            return ;
        }
        if(m_aszSQLErrorState.equalsIgnoreCase("08S01")){
    		setErr("SQLState: " + ex.getSQLState());
            return ;
        }
		setErr("SQLState: " + ex.getSQLState());
		setErr("Message: " + ex.getMessage());
		setErr("Code: " + ex.getErrorCode());
	}
    // end-of setSQLErr()

	//-------------------------------------------------------------------
	// checkForWarning
	// Checks for and displays warnings.  Returns true if a warning
	// existed,  Note that there could be multiple warnings chained together
	//-------------------------------------------------------------------
	private boolean checkForWarning (SQLWarning warn) throws SQLException {
		boolean rc = false;
		// If SQLWarning object, display the messages. 
		if (warn != null) {
			MethodInit("checkForWarning");
			rc = true;
			while (warn != null) {
				setErr("SQLState: " + warn.getSQLState ());
				setErr("Message: " + warn.getMessage ());
				setErr("Code: " + warn.getErrorCode ());
				warn = warn.getNextWarning ();
			}
		}
		return rc;
	}
    // end-of checkForWarning()


	/**
	*  allocate a database statement
	*/
	private int AllocStatement(){
		int iRetCode=1;
		CloseAllStmts();
		try {
			m_JDBCstatement = m_JDBCconnectionObj.createStatement();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("AllocStatement");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("AllocStatement");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
        if( 0 != iRetCode){
            CheckForReConnect() ;
        }
		return iRetCode;
	}
    // end-of method AllocStatement()

    /**
    * clear all Connect related stuff
    */
	private void ClearConnection(){
		CloseAllStmts();
		setAutoCommitOn();
	}
    // end-of method ClearConnection()

    /**
    * close all Connect statements
    */
	private void CloseAllStmts(){
		result_close();
		close_Stmt();
		close_PrepStmt();
	}
    // end-of method CloseAllStmts()

    /**
    * close all Connect result set data
    */
	private void result_close(){
		m_affectedRows = 0;
		if(null == m_JDBCresultset) return ;
		try {
			m_JDBCresultset.close();
		} catch (SQLException ex) {
			MethodInit("result_close");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			MethodInit("result_close");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_JDBCresultset=null;
		m_Result_Meta=null;
	}
    // end-of result_close()

    /**
	* allocate database connection
    * this is not applicable here
	*/
	private int allocateConnect(){
		if(null != m_JDBCconnectionObj) return 0;
		// m_JDBCconnectionObj = getConn();
		return 0;
	}
	// end-of method allocateConnect()

    /*
    * close JDBC statement
    */
	private int close_Stmt(){
		int iRetCode=0;
		if(null == m_JDBCstatement) return iRetCode;
		try {
			m_JDBCstatement.close();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("close_Stmt");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("close_Stmt");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_JDBCstatement=null;
		return iRetCode;
	}
    // end-of method close_Stmt()

    /*
    * close JDBC prep-statement
    */
	private int close_PrepStmt(){
		int iRetCode=0;
		if(null == m_PreStatement) return iRetCode;
		try {
			m_PreStatement.close();
			iRetCode=0;
		} catch (SQLException ex) {
			iRetCode=1;
			MethodInit("close_PrepStmt");
			while (ex != null) {
				setSQLErr(ex);
				ex = ex.getNextException ();
			}
		} catch (java.lang.Exception ex) {
			iRetCode=1;
			MethodInit("close_PrepStmt");
			setErr("java.lang.Exception");
			setErr(ex.getMessage());
		}
		m_PreStatement=null;
		return iRetCode;
	}
    // end-of close_PrepStmt()


    //================ Private Variables
    //================ Private Variables
    //================ Private Variables

	private Connection m_JDBCconnectionObj=null;

    // connection statement
	private Statement m_JDBCstatement=null;

    // connection result set
	private ResultSet m_JDBCresultset=null;
	private ResultSetMetaData m_Result_Meta=null;

    // connection prepare statement
	private PreparedStatement m_PreStatement=null;
	private int m_iPrepQueryItemIndex=0;

	private int m_iSQLErrorCode=0;
    private String m_aszSQLErrorState=null;

	private int iItemIdex=0;
	private DatabaseMetaData m_dbmeta=null;
	private String m_DbClassName=null;
	private String m_DatabaseURL=null;
	private String m_DBManagerUserID=null;
	private String m_DBManagerPassword=null;
	private int m_iInUseStatus=0;
	private int m_affectedRows=0;

	private String m_CurrentQueryStatement=null;

	private long m_lInuseStartTime=0;

	// number of times that connection errors have occured on this connection
	private int m_connErrCt = 0;

	// number of times that this class have been connected to the database
	private int m_connCt = 0;


}