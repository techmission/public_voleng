package com.abrecorp.opensource.base;

/**
* Title:   Base Application Batch Class
* Description:  To Create and Maintain Generic methods and services
* Copyright:    Copyright (c) 2001
* Company:     Marcillo Inc.
* @author    David Marcillo
* @version 1.0
*/

import java.util.*;
import java.sql.*;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;


public class ABREDBConnManager extends ABREBase {


	/**
	* Constructor
	*/
    public ABREDBConnManager(){}


	/**
	* get the next unique id number for a requested attribute string
    * March 2005 added a second try - David Marcillo
	*/
	public synchronized int getNextUniqueID(String attribute){
		int lUniqueID=-1,iRetCode=0;
		ABREDBConnection pConn=null;
		if(null == attribute) return -1;
		if(attribute.length() < 1) return -1;
		pConn = getConn();
		if(null == pConn) return -1;
		lUniqueID = getNextBDUniqueID(pConn,attribute,UNIQUEID_TABLE);
		if(null != pConn) pConn.free();
        if( lUniqueID > 0 ) return lUniqueID;
        // try a second time
		pConn = getConn();
		if(null == pConn) return -1;
		lUniqueID = getNextBDUniqueID(pConn,attribute,UNIQUEID_TABLE);
		if(null != pConn) pConn.free();
		return lUniqueID;
	}
    // end-of method getNextUniqueID()


	/**
	* Set the maximum number of connections.
	*/
	public void setMaxConnections(int iMax){
		iInitConnections = iMax;
	}

	/**
	* Set the maximum loop counter
	*/
	public void setMaxLoopCounter(int iMax){
		m_iMaxLoopCounter = iMax;
	}


	/**
	* Initializes the database connection pool.
	* This method uses JDBC style initialization.
	* 
	* @param dbUrl url describing the database to connect to (dependent on driver being used).
	* @param className fully qualified name of the JDBC driver class
	* @param DBUser database user name
	* @param DBPassword database password
	* 
	*/
	public int connectJDBC(String dbURL,String className,String DBUser,String DBPassword) {
        int iRetCode=0;
		MethodInit("connectJDBC");
		m_DatabaseURL = dbURL;
		m_DbClassName = className;
		m_DBManagerUserID = DBUser;
		m_DBManagerPassword = DBPassword;
        iRetCode = connectUsingJDBC();
		return iRetCode;
	}


	/**
	* check connection availability
	*/
    public int checkConnections(){
        // if(null == m_ConnectionList) return 1;
        ABREDBConnection pConn = getConn();
        if(null == pConn){
            return 1;
        }
        pConn.free();
        return 0;
    }


	/**
	* Get next available connection from the connection pool.
	*/
	public synchronized ABREDBConnection getConn(){
		int iRetCode=0, iMaxItems=0, iIndex=0;
		ABREDBConnection pConn=null;			
		MethodInit("getConn");
        if(null == m_ConnectionList){
            iRetCode = connectUsingJDBC();
            if(0 != iRetCode) return null;
        }
		/* search for an open statement */
		iMaxItems = m_ConnectionList.size();
		while(true){
			if(iIndex >= iMaxItems) break;
			pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
			if(0 == pConn.used()){
				iRetCode = pConn.connect();
				if(12345678 == iRetCode){
                    pConn=null;
				} else if(1 == iRetCode){
					pConn.copyErrObj (getErrObj());
				} else {
					pConn.setInuse();
					break;
				}
			}
			pConn=null;
			iIndex++;
		}
		if(null == pConn){
            if( 12345678 == iRetCode ) closeAllConnections();
			setErr("overflow Database Connection pool");
		} else {
            logDebug(8,"DatabaseManager::getConn() index " + (iIndex + 1));
		}
		return pConn;
	}
    // end-of getConn()


	/**
	* Get next available connection from the connection pool.
	*/
	public synchronized ABREDBConnection getCronConn(){
		int iRetCode=0, iMaxItems=0, iIndex=0;
		ABREDBConnection pConn=null;			
		MethodInit("getConn");
        if(null == m_ConnectionList){
            iRetCode = connectUsingJDBC();
            if(0 != iRetCode) return null;
        }
		/* search for an open statement */
		iMaxItems = m_ConnectionList.size();
		while(true){
			if(iIndex >= iMaxItems) break;
			pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
			if(0 == pConn.used()){
				iRetCode = pConn.connect();
				if(12345678 == iRetCode){
                    pConn=null;
				} else if(1 == iRetCode){
					pConn.copyErrObj (getErrObj());
				} else {
					pConn.setInuse();
					break;
				}
			}
			pConn=null;
			iIndex++;
		}
		if(null == pConn){
            if( 12345678 == iRetCode ) closeAllConnections();
			setErr("overflow Database Connection pool");
		} else {
            logDebug(8,"DatabaseManager::getConn() index " + (iIndex + 1));
		}
		return pConn;
	}
    // end-of getConn()


	/**
	 * Provides information about the current state of each connection in the connection pool.
	 * @param html if true html line breaks will be inserted at the end of each line.
	 * @return a String object containing the connection information.
	 */
	public String dumpConnectionList(boolean html) {
		StringBuffer msg = new StringBuffer(5000);
		String lf = html ? "<br>" : "\n";
		ABREDBConnection con = null;
		int index = 1;
		if (m_ConnectionList == null)
			msg.append("Connection list is null.");
		msg.append("Connection list size: " + m_ConnectionList.size() + lf);
		Enumeration eConList = m_ConnectionList.elements();
		while (eConList.hasMoreElements()) {
			con = (ABREDBConnection)eConList.nextElement();
			msg.append("Connection #"+ index++ + ": Inuse="+(con.isUsed()?"Y":"N") + " ConCt=" + con.getConnectionCount() + 
					   " ConErrs=" + con.getConnectionErrorCount());
			if (con.isUsed())
				msg.append(" sql=" + con.getCurrentSQL());
			else
				msg.append(" connected=" + con.isConnected());
			msg.append(lf);
		}
		return msg.toString();
	}
    // end-of dumpConnectionList()
	

	/**
	 * Returns a string containing information about the database the connection pool is connected to
	 * as well as information about the JDBC/ODBC driver being used.
	 * @param html if true html line breaks will be inserted at the end of each line.
	 * @return a String object containing the connection information.
	 */
	public String dumpConnectionInfo(boolean html) {
		Connection testConn = null;
		DatabaseMetaData meta = null;
		StringBuffer msg = new StringBuffer(5000);
		String lf = html ? "<br>" : "\r\n";
		// create temporary connection
		try {
			Class.forName (m_DbClassName);
			testConn = DriverManager.getConnection(m_DatabaseURL,m_DBManagerUserID,m_DBManagerPassword);
			}
		catch (Exception e) {
			return "Failed getting connection info (database connection failed)." + lf;
		}
		// retreive metadata from temporary connection
		try {
			meta = testConn.getMetaData();
		}
		catch (Exception e) {
			try {testConn.close();} catch (SQLException ex) {};
			return "Failed getting database metadata." + lf;
		}
		// create stringbuffer containing connection information.
		try {
			msg.append("Connection information:");
			msg.append(lf);

			msg.append("Database: ");
			msg.append(meta.getDatabaseProductName());
			msg.append(", ");
			msg.append(meta.getDatabaseProductVersion());
			msg.append(lf);
		
			msg.append("Connected to: ");
			msg.append(meta.getURL());
			msg.append(" as ");
			msg.append(meta.getUserName());
			msg.append(lf);
		
			msg.append("Using: ");
			msg.append(meta.getDriverName());
			msg.append(", ");
			msg.append(meta.getDriverVersion());
		}
		catch (Exception e) {
			try {testConn.close();} catch (SQLException ex) {};
			return "Failed getting connection information (metadata call failed)." + lf;
		}
		
		try {testConn.close();}	catch (SQLException e) {};
		return msg.toString();
	}
    // end-of dumpConnectionInfo()


	/**
	 * Turns JDBC tracing on for this connection pool. This tracing is provided by the java JDBC package (java.sql).
	 * @param fileName the file to use for logging.
	 * @return true if turning tracing on is successfull, false otherwise.
	 */
	public synchronized boolean enableJDBCTracing(String fileName) {
		FileOutputStream sqlStream = null;
		PrintStream sqlLog = null;		
		if (fileName == null) {
			fileName = m_jdbcTraceFile;
		}
		if (fileName == null) {
			setErr("JDBC log file has not been specified.");
			return false;
		}
		// turn driver manager logging on
		try {
			sqlStream = new FileOutputStream(fileName,true);
			sqlLog = new PrintStream(sqlStream);
			}
		catch (IOException e) {
			setErr("Failed opening file: " + fileName);
			setErr(e.toString());
			setErr(e.getMessage());
            ErrorsToLog();
			return false;
			}
		DriverManager.setLogStream(sqlLog);
		m_jdbcTraceFile = fileName;
		return true;
	}
	// end-of enableJDBCTracing()


	/**
	* Turns JDBC tracing off for this connection pool. This tracing is provided by the java JDBC package (java.sql)
	*/
	public synchronized void disableJDBCTracing() {
		DriverManager.setLogStream(null);
	}
	

	/**
	* Returns the file name that is being used for JDBC tracing.
	* @return a valid filename if tracing is on, null otherwise.
	*/
	public String getJDBCTraceFileName() {
		return m_jdbcTraceFile;
	}


	/**
	* Returns true if JDBC tracing is on, false otherwise.
	*/
	public boolean jdbcTracingOn() {
		return DriverManager.getLogStream() != null;
	}


	/**
	* close all connections.
	*/
	public int closeAllConnections(){
		ABREDBConnection con = null;
		int index = 1, iConCt;
		if (m_ConnectionList == null)
			return 0;
		iConCt = m_ConnectionList.size();
		for (index = 0; index < iConCt; index++) {
			con = (ABREDBConnection)m_ConnectionList.elementAt(index);
			if (con != null){
				if (con.isUsed())
					logDebug(4,"WARNING: Closing in use connection");
				con.diconnectFromDB();
				m_ConnectionList.setElementAt(null, index);
			}
		}
		m_ConnectionList = null;
		return 0;
	}
    // end-of closeAllConnections()


	/**
	* set maximum inactive connections
	* for connection monitor thread
	*/
	public void setMaxInActiveOpenConnections(int number){
		m_MaxInActiveOpenConnections=number;
	}

	/**
	* set background thread active or inActive
	* for connection monitor thread
	*/
	public void setDBMonitorThreadActive(String aszActive){
		if(null == aszActive){
			m_DBMonitorThreadActive=1;
			return;
		}
		if(aszActive.length() < 1){
			m_DBMonitorThreadActive=1;
			return;
		}
		if(aszActive.equalsIgnoreCase("N")){
			m_DBMonitorThreadActive=0;
			return;
		}
		m_DBMonitorThreadActive=1;
	}
    // end-of setDBMonitorThreadActive()

	/**
	* set max idle connection time
	* for connection monitor thread
	*/
	public void setMaxIdelConnectionTime(long number){
		m_MaxIdleConnectionTime=number;
	}

	/**
	* set max in use connection time
	* for connection monitor thread
	*/
	public void setMaxInUseConnectionTime(long number){
		m_MaxInUseConnectionTime=number;
	}

	/**
	* set background thread sleep time
	* for connection monitor thread
	*/
	public void setBackgroundThreadSleepTime(long number){
		m_backgroundThreadSleepTime=number;
	}



    //=============== START Inner Class
    //=============== START Inner Class
    //=============== START Inner Class


	/**
	* background thread to monitor open connections
	*/
	class monitorConnectionsBackground extends Thread
	{
		// wake up every defined time and check database connections
		public void run(){
			int iRetCode=0;
			boolean bContinue=true;
			while(bContinue){
				if(m_DBMonitorThreadActive < 0){
					m_DBMonitorThreadActive=1;
				}
				if(m_DBMonitorThreadActive == 0){
					bContinue=false;
					break;
				}
				if(m_backgroundThreadSleepTime < 1){
					m_backgroundThreadSleepTime=m_backgroundThreadDeafultSleepTime;
				}
				iRetCode = monitorConnectionsInUse();
				iRetCode = monitorConnectionPoolNotInUse();
                m_iInternalLoopCounter++;
                if( m_iInternalLoopCounter > m_iMaxLoopCounter ) {
                    logDebug(4,"DatabaseManager::run() connections max-in-use:" +  m_iInUseMaxCounter + " Max-Idle:" + m_iMaxIdleConnections );
                    m_iInternalLoopCounter=0;
                    m_iInUseMaxCounter=0;
                    m_iMaxIdleConnections=0;
                    // added these two methods 3/12/2005 marcillo
                    freeIdleAllConnections();
                    connectFirstFreeConnection();
                }
				try {
					Thread.sleep(m_backgroundThreadSleepTime);
				} catch (InterruptedException e) {}
				}
			}
            // end-of run()

        /**
        * monitor Database connections open but not in use 
        * - in other words - reduce the pool of connections
        */
        private int monitorConnectionPoolNotInUse(){
            int iMaxIdle=0;
            ABREDBConnection pConn=null;
            boolean bPoolIsOverAllocated=true;
            if(null == m_ConnectionList){
                return 0;
            }
            if(m_MaxInActiveOpenConnections < 0){
                m_MaxInActiveOpenConnections=m_MaxInActiveDefaultOpenConnections;
            }
            if(m_MaxInActiveOpenConnections == 0){
                return 0;
            }
            while(bPoolIsOverAllocated){
                iMaxIdle = getTotalIdleConnections();
                if(iMaxIdle <= m_MaxInActiveOpenConnections){
                    bPoolIsOverAllocated=false;
                    if(iMaxIdle > 0){
						// logDebug(8,"DatabaseManager::monitorConnectionPoolNotInUse() connectFirstFreeConnection() " );
                        connectFirstFreeConnection();
                        connectFirstFreeConnection();
                        try { Thread.sleep(999); } 
                        catch (InterruptedException e) {}
						logDebug(8,"DatabaseManager::monitorConnectionPoolNotInUse() freeLastIdleConnection() " );
                        freeFirstIdleConnection();
                    } else {
                        connectFirstFreeConnection();
                        connectFirstFreeConnection();
						// logDebug(8,"DatabaseManager::monitorConnectionPoolNotInUse() connectFirstFreeConnection() " );
                    }
                } else {
                    freeLastIdleConnection();
                    freeFirstIdleConnection();
                    bPoolIsOverAllocated=false;
                    // logDebug(8,"DatabaseManager::monitorConnectionPoolNotInUse() freeLastIdleConnection() " );
                }
            }
            if(iMaxIdle > m_iMaxIdleConnections) m_iMaxIdleConnections=iMaxIdle;
            return 0;
        }
        // end-of monitorConnectionPoolNotInUse()

		/**
		* connected a free connection in normal order
        * added 3/3/2005 marcillo
		*/
		private void connectFirstFreeConnection(){
			int iRetCode, iMaxItems, iIndex=0;
			ABREDBConnection pConn=null;
			if(null == m_ConnectionList){
				return;
			}
			// connection first free connection
			iMaxItems = m_ConnectionList.size();
			for (iIndex=0; iIndex < iMaxItems; iIndex++ ){
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
				if(null == pConn) continue ;
                if(pConn.isUsed()) continue;
                if(!pConn.isConnected()){
                    iRetCode = pConn.connect();
                    break;
                }
			}
            // end while tru loop
		}
        // end-of connectFirstFreeConnection()


		/**
		* free First connections
		*/
		private void freeFirstIdleConnection(){
			int iRetCode, iMaxItems, iIndex=0;
			ABREDBConnection pConn=null;
			if(null == m_ConnectionList){
				return;
			}
			if(m_MaxIdleConnectionTime < 0){
				m_MaxIdleConnectionTime = m_MaxDefaultIdleConnectionTime;
			}
			iMaxItems = m_ConnectionList.size();
			for ( iIndex=0; iIndex < iMaxItems; iIndex++){
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
				if(null == pConn) continue ;
                if( pConn.isUsed() ) continue ;
                if( false == pConn.isConnected()) continue ;
                pConn.diconnectFromDB();
                // DebugMessage(3,"CDbaseManager::freeFirstIdleConnection() connection " + (iMaxItems + 1 ));
                break;
			}
            // end while true loop
		}
        // end-of freeFirstIdleConnection()

		/**
		* free connections in revers order
		*/
		private void freeLastIdleConnection(){
			int iRetCode, iMaxItems, iIndex=0;
			long lInUseStartTime=0,lCurrentTime=0,lInUseIntevalTime=0;
			ABREDBConnection pConn=null;
			if(null == m_ConnectionList){
				return;
			}
			if(m_MaxIdleConnectionTime < 0){
				m_MaxIdleConnectionTime = m_MaxDefaultIdleConnectionTime;
			}
			// free las idel connection
			iMaxItems = m_ConnectionList.size();
			while (true){
				iMaxItems--;
				if(iMaxItems < 1) break;
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iMaxItems);
				if(null != pConn){
					if(!pConn.isUsed()) {
						if(pConn.isConnected()){
                            pConn.diconnectFromDB();
                            break ;
						}
					}
				}
			}
            // end while true loop
		}
        // end-of freeLastIdleConnection()

		/**
		* free connections in idle state
		*/
		private void freeIdleAllConnections(){
			int iRetCode, iMaxItems, iIndex=0;
			long lInUseStartTime=0,lCurrentTime=0,lInUseIntevalTime=0;
			ABREDBConnection pConn=null;
			if(null == m_ConnectionList) return;
			if(m_MaxIdleConnectionTime < 0){
				m_MaxIdleConnectionTime = m_MaxDefaultIdleConnectionTime;
			}
			iMaxItems = m_ConnectionList.size();
			for ( iIndex=0; iIndex < iMaxItems; iIndex++){
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
				if(null == pConn) continue ;
                if( pConn.isUsed() ) continue ;
                if( false == pConn.isConnected()) continue ;
                lInUseStartTime = pConn.getInUseStartTime();
                lCurrentTime = System.currentTimeMillis();
                lInUseIntevalTime = lCurrentTime - lInUseStartTime;
                if(lInUseIntevalTime > m_MaxIdleConnectionTime){
                    pConn.diconnectFromDB();
                }
			}
            // end while true loop
		}
        // end-of freeIdleAllConnections()
	
		/**
		* get total number of connection open to DB but idle
		*/
		private int getTotalIdleConnections(){
			int iMaxConn=0,iIndex=0;
			int iMaxIdle=0;
			ABREDBConnection pConn=null;
			if(null == m_ConnectionList){
				return 0;
			}
			iMaxConn = m_ConnectionList.size();
			iMaxIdle=0;
			for(iIndex=0; iIndex < iMaxConn; iIndex++){
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
				if(null != pConn){
					if(!pConn.isUsed()) {
						if(pConn.isConnected()){
							iMaxIdle++;
						}
					}
				}
			}
			return iMaxIdle;
		}
        // end-of getTotalIdleConnections()

		/**
		* monitor Database connections left in use 
		* - on purpose by a baddd ... badddd developer
		*/
		private int monitorConnectionsInUse(){
			int iMaxConn=0,iIndex=0,iInUseCounter=0;
			long lInUseStartTime=0,lCurrentTime=0,lInUseIntevalTime=0;
			ABREDBConnection pConn=null;
			if(m_MaxInUseConnectionTime < 0){
				m_MaxInUseConnectionTime=m_DefaultMaxInUseConnectionTime;
			}
			if(m_MaxInUseConnectionTime == 0){
				return 0;
			}
			if(null == m_ConnectionList){
				return 0;
			}
            iInUseCounter=0;
			iMaxConn = m_ConnectionList.size();
			for(iIndex=0; iIndex < iMaxConn; iIndex++){
				pConn = (ABREDBConnection)m_ConnectionList.elementAt(iIndex);
				if(pConn.isUsed()) {
                    iInUseCounter++;
					lInUseStartTime = pConn.getInUseStartTime();
					lCurrentTime = System.currentTimeMillis();
					lInUseIntevalTime = lCurrentTime - lInUseStartTime;
					if(lInUseIntevalTime > m_MaxInUseConnectionTime){
						pConn.setNotInuse();
						pConn.diconnectFromDB();
						logDebug(2,"DatabaseManager::monitorConnectionsInUse() release from InUse connection " + (iIndex+1));
					}
				}
			}
            if( iInUseCounter > m_iInUseMaxCounter) m_iInUseMaxCounter=iInUseCounter;
			return 0;
		}
        // end-of monitorConnectionsInUse()

	}
    // end-of inner class monitorConnectionsBackground

    //=============== END   Inner Class
    //=============== END   Inner Class
    //=============== END   Inner Class


    //================= Private Methods
    //================= Private Methods
    //================= Private Methods


	//-------------------------------------------------------------------
	// checkForWarning
	// Checks for and displays warnings.  Returns true if a warning
	// existed,  Note that there could be multiple warnings chained together
	//-------------------------------------------------------------------
	private boolean checkForWarning (SQLWarning warn)
			throws SQLException
	{
		boolean rc = false;
		// If SQLWarning object, display the messages. 
		if (warn != null) {
			setErr("*** SQL Warning ***");
			rc = true;
			while (warn != null) {
				setErr("SQLState: " + warn.getSQLState ());
				setErr("Message: " + warn.getMessage ());
				setErr("ErrorCode: " + warn.getErrorCode ());
				warn = warn.getNextWarning ();
			}
		}
		return rc;
	}
    // end-of checkForWarning()



	private void setSQLErr(SQLException ex){
		int i = 0;
		String fullMsg;
		while (ex != null) {
			fullMsg = "SQLState: ";
			fullMsg += ex.getSQLState();
			fullMsg += " Message: ";
			fullMsg += ex.getMessage();
			fullMsg += " ErrorCode: ";
			fullMsg += ex.getErrorCode();
			if (i == 0)
				setErr(fullMsg);
			else
				setErr(fullMsg);
			ex = ex.getNextException ();
			i++;
		}
        // ErrorsToLog();
	}
    // end-of setSQLErr()


	/*
	** CHANGE IT TO PUBLIC METHOD
	*/
	private int getNextBDUniqueID(ABREDBConnection pCon,String attribute, String aszTable){
		int iRetCode;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		String Qry1=null;
		if(null == pCon){
			// MethodInit("getNextBDUniqueID");
			// setErr("null database connection");
			return -1;
		}
		if(null == aszTable){
			// MethodInit("getNextBDUniqueID");
			// setErr("null database table name");
			return -1;
		}
		iRetCode=pCon.getDBStmt();
		if(0 != iRetCode){
			// MethodInit("getNextBDUniqueID");
			// pCon.copyErrObj(getErrObj());
			return -1;
		}
		// prepare query
		Qry1 = "SELECT next_id FROM  " + aszVolengDB + aszTable + " WHERE pool_id='" + attribute + "'";
		iRetCode = pCon.RunQry(Qry1);
		if(0 != iRetCode){
			// MethodInit("getNextBDUniqueID");
			// pCon.copyErrObj(getErrObj());
            // ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pCon.getNextResult()){
			lUniqueID = pCon.getDBInt("next_id");
		} else {
			lUniqueID=-1;
			return -1;
		}
		lNextUniqueID = lUniqueID + 1 ;
		Qry1 = "UPDATE " + aszVolengDB + aszTable + " SET next_id=" + lNextUniqueID + 
			   " WHERE pool_id='" + attribute + "'";
		iRetCode = pCon.RunUpdate(Qry1);
		if(0 != iRetCode){
			// MethodInit("getNextBDUniqueID");
			// pCon.copyErrObj(getErrObj());
			return -1;
		}
		return lUniqueID;
	}
    // end-of method getNextBDUniqueID()


	/**
    * Allocate The connection pool 
    */
	private int AllocateConnList(){
		int iCurrCount, iIndex ;
		if(null == m_ConnectionList){
			m_ConnectionList = new Vector(iInitConnections,iConnectionIncrement);
		}
		if(null == m_ConnectionList){
			setErr("null Database Connection pool");
			return 1;
		}
		/* Init All Items */
		iCurrCount = m_ConnectionList.size();
		while(iCurrCount < iInitConnections){
			ABREDBConnection aConnection = new ABREDBConnection();			
			m_ConnectionList.addElement(aConnection);
			iCurrCount = m_ConnectionList.size();
			aConnection.setIndex(iCurrCount);
			aConnection.setDbClassName(m_DbClassName);
			aConnection.setDbUrl(m_DatabaseURL);
			aConnection.setUserID(m_DBManagerUserID);
			aConnection.setPassword(m_DBManagerPassword);
			aConnection.setLogObj(getLogObj());
		}
		return 0;
	}
    // end-of AllocateConnList()


	/**
	* Initializes the database connection pool.
	* This method uses JDBC style initialization.
	* dbUrl url describing the database to connect to (dependent on driver being used).
	* className fully qualified name of the JDBC driver class
	* DBUser database user name
	* DBPassword database password
	*/
	private int connectUsingJDBC() {
		Connection testConn = null;
        int iRetCode=0;
		try {
			Class.forName (m_DbClassName);
			testConn = DriverManager.getConnection(m_DatabaseURL,m_DBManagerUserID,m_DBManagerPassword);
		} catch (SQLException ex) {
			setSQLErr(ex);
			return 1;
		} catch (java.lang.Exception ex) {
			setErr(ex.getMessage());
			return 1;
		}
		// Close initial connection
		if (null != testConn) {
			try {
				testConn.close();
			} catch (SQLException ex) {
				setSQLErr(ex);
			} catch (java.lang.Exception ex) {
				setErr(ex.getMessage());
			}
			testConn = null;
		}
		// Allocate Connection List
		if (AllocateConnList() > 0)
			return 1;
		// start backgroung monitor thread
		if(null == m_MonitorThread){
			m_MonitorThread = new monitorConnectionsBackground();
			m_MonitorThread.start();
		}
		return 0;
    }
    // end-of connectUsingJDBC()


    //================================================ Private Variables
    //================================================ Private Variables
    //================================================ Private Variables

	private long m_DefaultMaxInUseConnectionTime=7777777;  //  maximum in use connection time default value
	private long m_MaxInUseConnectionTime=-1;
	private long m_backgroundThreadSleepTime=-1;
	private long m_backgroundThreadDeafultSleepTime=99999; // default background thread sleep time
	private int m_DBMonitorThreadActive=-1;
	private int m_MaxInActiveOpenConnections=-1;
	private int m_MaxInActiveDefaultOpenConnections=3;  // maximum inactive but open connections
	private long m_MaxIdleConnectionTime=-1;
	private long m_MaxDefaultIdleConnectionTime=7777777;  //  maximum in idle connection time default value

	private monitorConnectionsBackground m_MonitorThread=null;

	private String m_DatabaseURL=null; 
	// private String m_DatabaseBase =null;
	private String m_DBManagerUserID=null;
	private String m_DBManagerPassword=null;
	private String m_jdbcTraceFile = null;
	private Vector m_ConnectionList=null;
	private String m_DbClassName=null;
	// 
	private Connection m_DBManagerTestConnection=null;
	private DatabaseMetaData m_DMA=null;
	private int iInitConnections = 10;
	private int iConnectionIncrement = 1;
    private int m_iMaxLoopCounter=0;
    private int m_iInUseMaxCounter=0;
    private int m_iMaxIdleConnections=0;
    private int m_iInternalLoopCounter=0;

    private final static String UNIQUEID_TABLE="id_pools";

	private String aszDrupalDB = "um_";
	private String aszVolengDB = "techmi5_voleng.";

}
