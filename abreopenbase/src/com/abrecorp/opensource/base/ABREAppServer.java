package com.abrecorp.opensource.base;

/**
* Title:        Base Application Server Class
* Description:  To Create and Maintain Generic methods and services
* Copyright:    Copyright (c) 1998-2003
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import java.util.*;
import java.io.File;

public abstract class ABREAppServer extends ABREBase {

    /**
	* get next unique ID for a given attribute
	*/
	public int getNextUniqueID(String attribute){
        if(null == m_DatabaseManager) return -1;
		return m_DatabaseManager.getNextUniqueID(attribute);
	}
    // end-of method getNextUniqueID()

    /**
    * get a database connection object
    */
    public ABREDBConnection getDBConn(){
        if(null == m_DatabaseManager) return null;
        return m_DatabaseManager.getConn();
    }
    // end-of method getDBConn()

    /**
    * get a database connection object
    */
    public static ABREDBConnection getCronDBConn(){
        if(null == m_DatabaseManager) InitCronDatabase();
        return m_DatabaseManager.getCronConn();
    }
    // end-of method getDBConn()


    /**
    * get application database manager object
    */
    public ABREDBConnManager getDatabaseManager(){
        if(null == m_DatabaseManager) return null;
        if( 0 != m_DatabaseManager.checkConnections() ) return null;
        return m_DatabaseManager;
    }
    // end-of method getDatabaseManager()

    /**
    * Re-Initialize application services
    */
    public int ReInitApplication(){
        return reInitAppObjects();
    }
    // end-of method ReInitApplication()

    /**
    * get Site level property object
    */
    public ABREPropertyFile getSiteProperties(){
        if(null == m_SitePropertyObject) return null;
        return m_SitePropertyObject;
    }
    // end-of method getSiteProperties()

    /**
    * get site level property value
    */
    public String getSitePropertyValue(String parm){
        if(null == m_SitePropertyObject) return null;
        return m_SitePropertyObject.getProperty(parm);
    }
    // end-of method getSitePropertyValue()

    /**
    * get Application level property object
    */
    public ABREPropertyFile getAppProperties(){
        if(null == m_ApplicationPropertyObject) return null;
        return m_ApplicationPropertyObject;
    }
    // end-of method getAppProperties()

    /**
    * get application level property value
    */
    public String getAppPropertyValue(String parm){
        if(null == m_ApplicationPropertyObject) return null;
        return m_ApplicationPropertyObject.getProperty(parm);
    }
    // end-of method getAppPropertyValue()

    /**
    * get Application config folder location
    */
    public String getAppConfigFolder(){
        if(null == m_ApplicationFolderName) return "";
        String aszFullFilePath = m_ApplicationFolderName + m_ConfigFolder;
        return aszFullFilePath;
    }
    // end-of method getAppConfigFolder()

    /**
    * init error
    */
    public boolean IsInitError(){
        if(true == m_bInitError){
            reInitAppObjects();            
        }
        return m_bInitError;
    }
    // end-of method IsInitError()

    /**
    * Cleup all application objects
    */
    public void cleanup(){
        if(null != m_DatabaseManager){
            m_DatabaseManager.setDBMonitorThreadActive("N");
            m_DatabaseManager.closeAllConnections();
        }
        m_bSystemShutDown=true;
    }
    // end-of method cleanup()

    /**
    * get counter
    */
    public synchronized int getCount(){
        m_iCounter++;
        return m_iCounter;
    }
    // end-of method getCount()

    /**
    * ShutDown Flag
    */
    public boolean IsSystemShutDown(){
        return m_bSystemShutDown;
    }
    // end-of method IsSystemShutDown()

    public void startSystemShutDown(){
        m_bSystemShutDown=true;
    }
    // end-of method startSystemShutDown()

    /**
    * Re-Fresh Monitors Flag
    */
    public boolean IsMonitorRefresh(){
        return m_bMonitorRefreshFlag;
    }
    // end-of method IsMonitorRefresh()

    public void setMonitorRefresh(boolean flag){
        m_bMonitorRefreshFlag=flag;
    }
    // end-of method setMonitorRefresh()

    /**
    * Re-Fresh Property Files
    */
    public void PropertyFilesRefresh(){
        int iRetcode=0;
        m_SitePropertyObject=null;
        m_ApplicationPropertyObject=null;
        iRetcode = initApplicationLevelPropertyObject();
    }
    // end-of PropertyFilesRefresh()

    //==== START Logger Sections ====>
    //==== START Logger Sections ====>
    //==== START Logger Sections ====>

    /**
    * can log folder be read
    */
    public String canLogFolderBeRead(){
        String aszLogFolder = getSitePropertyValue( "app.log4j.foldername" );
        if(null == aszLogFolder){
          return "null log folder" ;
        }
        if(aszLogFolder.length() < 2){
          return "empty log folder" ;
        }
        File aFile = new File( aszLogFolder );
        if(null == aFile){
          return "can not create File Class for " + aszLogFolder  ;
        }
        if( false == aFile.canRead() ){
          return "can not read log folder " + aszLogFolder  ;
        }
        if( false == aFile.canWrite() ){
          return "can read but not write to log folder " + aszLogFolder  ;
        }
        if( false == aFile.isDirectory() ){
          return "can read and write to log folder " + aszLogFolder + " , but not a directory" ;
        }
        return "yes ! a directory and Yes we can read and write to log folder " + aszLogFolder;
    }
    // end-of canLogFolderBeRead()

    /**
    * get log file list
    */
    public String [] getLogFileList(){
        String aszLogFolder = getSitePropertyValue( "app.log4j.foldername" );
        if(null == aszLogFolder) return null ;
        if(aszLogFolder.length() < 2) return null ;
        File aFile = new File( aszLogFolder );
        if(null == aFile) return null ;
        if( false == aFile.isDirectory() ) return null ;
        return aFile.list() ;
    }
    // end-of getLogFileList()

    /**
    * close Log4j File
    */
    public void closeLoggerFile(){
        closeLog4J();
    }
    // end-of closeLoggerFile()

    /**
    * rename Log4j File
    */
    public void renameLoggerFile(String filename){
        if(null == filename) return ;
        String aszTemp = filename.trim();
        if( aszTemp.length() < 3 ) return ;
        String aszLogFolder = getSitePropertyValue( "app.log4j.foldername" );
        if(null == aszLogFolder) return ;
        if(aszLogFolder.length() < 2) return ;
        String oldfilename = aszLogFolder + File.separatorChar + filename ;
        File aFile = new File( oldfilename );
        if(null == aFile) return ;
        if( false == aFile.isFile() ) return  ;
        if( false == aFile.exists() ) return  ;
        if( false == aFile.canRead() ) return  ;
        if( false == aFile.canWrite() ) return  ;
        int index=101; 
        String newfilename=null;
        boolean bOK=false;
        while(true){
            index++;
            newfilename = aszLogFolder + File.separatorChar + getAlphaNumbers ( filename + index ) + ".txt" ;
            File aNewFile = new File( newfilename );
            try {
                bOK = aFile.renameTo( aNewFile ) ;
            } catch ( SecurityException exp ) {
                bOK=false;
            }
            if ( bOK ) break ;
        }
    }
    // end-of renameLoggerFile()

    /**
    * Re-Fresh Log4j File
    */
    public void LoggerFilesRefresh(){
        int iRetcode=0;
        closeLog4J();
        iRetcode = initializeLoggerObject();
    }
    // end-of LoggerFilesRefresh()

    //==== END   Logger Sections ====>
    //==== END   Logger Sections ====>
    //==== END   Logger Sections ====>


    /**
    * get Session List
    */
    public Enumeration getSessionList(){
        if(null == m_SessionList) return null;
        if( m_SessionList.isEmpty() ) return null;
        return m_SessionList.elements();
    }
    // end-of getSessionList()

	/**
	* set current date time
	*/
	public void setCurentDateTime(){
        ABREDate aDateObj = new ABREDate();
        aDateObj.setCountry( "US" );
        aDateObj.setLanguage( "en" );
        setCreateYear( aDateObj.getCurrentYear() );
        setCreateMonth( aDateObj.getCurrentMonth() );
        setCreateDay( aDateObj.getCurrentDay() );
        setCreateHour( aDateObj.getCurrentHour() );
        aDateObj.clearAll();
		return ;
	}
    // end-of setCurentDateTime()

    /**
    * Session Create year
    */
    public int getCreateYear(){
        return m_iSessionCreateYear ;
    }
    public void setCreateYear( int number ){
        m_iSessionCreateYear = number ;
    }
    private int m_iSessionCreateYear=0;

    /**
    * Session Create month
    */
    public int getCreateMonth(){
        return m_iSessionCreateMonth ;
    }
    public void setCreateMonth( int number ){
        m_iSessionCreateMonth = number ;
    }
    private int m_iSessionCreateMonth=0;

    /**
    * Session Create day
    */
    public int getCreateDay(){
        return m_iSessionCreateDay ;
    }
    public void setCreateDay( int number ){
        m_iSessionCreateDay = number ;
    }
    private int m_iSessionCreateDay=0;

    /**
    * Session Create Hour
    */
    public int getCreateHour(){
        return m_iSessionCreateHour ;
    }
    public void setCreateHour( int number ){
        m_iSessionCreateHour = number ;
    }
    private int m_iSessionCreateHour=0;

    // ----------------------------- Protected Methods
    // ----------------------------- Protected Methods
    // ----------------------------- Protected Methods

    /**
    * create a new session object
    */
    protected void addNewSession(ABRESession aSess){
        Long aLongObj=null;
        if(null == m_SessionList){
                m_SessionList = new Hashtable();
        }
        m_iSessionID++;
        try {
                aLongObj = new Long(m_iSessionID);
        } catch (Exception ex){
                aLongObj=null;
        }
        if(null != aLongObj){
            m_SessionList.put(aLongObj,aSess);
            aSess.setSessionID(m_iSessionID);
            // aSess.setAppRef(this);
            logDebug(4,"session created: " + m_iSessionID);
        }
        aSess.setBaseAppRef(this);
        return ;
    }
    // end-of addNewSession()

    /**
    * remove session object
    */
    protected synchronized void removeSession(int id){
        Long aLongObj=null;
        if(null == m_SessionList) return;
        if(id < 1) return;
        try {
                aLongObj = new Long(id);
        } catch (Exception ex){
                aLongObj=null;
        }
        if(null != aLongObj){
            m_SessionList.remove(aLongObj);
            logDebug(4,"session removed " + id);
        }
        return ;
    }
    // end-of removeSession()

    /**
    * Initialize application services
    * application folder and property file name
    */
    protected int InitApplication(String folder, String propertyfile, String siteconfig){
        int iRetCode=0;
        MethodInit("InitApplication");
        if(null == folder){
            setErr("application folder required");
            m_ApplicationFolderName=null;
            return 1;
        }
        if(folder.length() < 2){
            setErr("application folder required");
            m_ApplicationFolderName=null;
            return 1;
        }
        m_ApplicationFolderName = folder.trim();
        if(m_ApplicationFolderName.length() < 2){
            setErr("application folder required");
            m_ApplicationFolderName=null;
            return 1;
        }
        if(null == propertyfile){
            setErr("application property file required");
            m_ApplicationPropertyFileName=null;
            return 1;
        }
        if(propertyfile.length() < 2){
            setErr("application property file required");
            m_ApplicationPropertyFileName=null;
            return 1;
        }
        m_ApplicationPropertyFileName = propertyfile.trim();
        if(m_ApplicationPropertyFileName.length() < 2){
            setErr("application property file required");
            m_ApplicationPropertyFileName=null;
            return 1;
        }
        if(null == siteconfig){
            setErr("site property file required");
            m_SitePropertyFileName=null;
            return 1;
        }
        if(siteconfig.length() < 2){
            setErr("site property file required");
            m_SitePropertyFileName=null;
            return 1;
        }
        m_SitePropertyFileName = siteconfig.trim();
        if(m_SitePropertyFileName.length() < 2){
            setErr("site property file required");
            m_SitePropertyFileName=null;
            return 1;
        }
        iRetCode = reInitAppObjects();
        return iRetCode;
    }
    // end-of InitApplication()

    // -------------------------------- Private Methods
    // -------------------------------- Private Methods
    // -------------------------------- Private Methods

    /**
    * re-initialize application objects
    */
    private int reInitAppObjects(){
        int iRetcode=0;
        iRetcode = initApplicationLevelPropertyObject();
        if(0 != iRetcode) return 1;
        iRetcode = initializeLoggerObject();
        if(0 != iRetcode) return 1;
        iRetcode = InitDatabase();
        if(0 != iRetcode) return 1;
        if(null != m_DatabaseManager){
            if( 0 != m_DatabaseManager.checkConnections() ) return 1;
        }
        m_bInitError=false;
        return 0;
     }
    // end-of reInitAppObjects()

    /**
    * initializa application level property object
    */
    private int initApplicationLevelPropertyObject(){
        int iRetCode=0;
        String aszFullFilePath=null;
        if(null == m_SitePropertyFileName) {
            setErr("site property file name required");
            return 1;
        }
        if(m_SitePropertyFileName.length() < 2) {
            setErr("site property file name required");
            return 1;
        }
        if(null == m_ApplicationPropertyFileName) {
            setErr("application property file name required");
            return 1;
        }
        if(m_ApplicationPropertyFileName.length() < 2) {
            setErr("application property file name required");
            return 1;
        }
        if(null == m_ApplicationFolderName) {
            setErr("application folder required");
            return 1;
        }
        if(m_ApplicationFolderName.length() < 2) {
            setErr("application folder required");
            return 1;
        }
        if(null == m_SitePropertyObject){
            m_SitePropertyObject = new ABREPropertyFile();
            aszFullFilePath = m_ApplicationFolderName + File.separator + m_SitePropertyFileName;
            iRetCode = m_SitePropertyObject.load(aszFullFilePath);
            if(iRetCode != 0) {
                m_SitePropertyObject=null;
                setErr("error loading site property file " + m_SitePropertyFileName + " from " + m_ApplicationFolderName);
                return 1;
            }
            logInfo("ABREAppServer: Using site property file (" + aszFullFilePath + ")");
        }
        if(null == m_ApplicationPropertyObject){
            m_ApplicationPropertyObject = new ABREPropertyFile();
            aszFullFilePath = m_ApplicationFolderName + File.separator + m_ApplicationPropertyFileName;
            iRetCode = m_ApplicationPropertyObject.load(aszFullFilePath);
            if(iRetCode != 0) {
                m_ApplicationPropertyObject=null;
                setErr("error loading application property file " + m_ApplicationPropertyFileName + " from " + m_ApplicationFolderName);
                return 1;
            }
            logInfo("ABREAppServer: Using application property file (" + aszFullFilePath + ")");
        }
        return 0;
    }
    // end-of initApplicationLevelPropertyObject()

    //==== START Logger Sections ====>
    //==== START Logger Sections ====>
    //==== START Logger Sections ====>


    /**
    * init trace log object
    */
    private int initializeLoggerObject(){
        if(null != m_ABRELogger) return 0;
        if(null == m_SitePropertyObject){
            System.out.println("Error: site property file missing");
            return 1;
        }
        String aszLoggerFolderName = m_SitePropertyObject.getProperty( ABREAppServerDef.LoggerFolder );
        if(null == aszLoggerFolderName) {
            System.out.println("Error: logger folder name missing from system property");
            return 1;
        }
        String aszLoggerFileName = m_SitePropertyObject.getProperty( ABREAppServerDef.LoggerFile );
        if(null == aszLoggerFileName) {
            System.out.println("Error: logger file name missing from system property");
            return 1;
        }
        String aszLoggerCategoryName = m_SitePropertyObject.getProperty( ABREAppServerDef.LoggerCategoryName );
        if(null == aszLoggerCategoryName) {
            System.out.println("Error: logger category name missing from system property");
            return 1;
        }
        String aszFullFilePath = aszLoggerFolderName + File.separator + aszLoggerFileName;
        m_ABRELogger = new ABRELogger();
        int iRetCode = m_ABRELogger.createLogCategory( aszLoggerCategoryName, aszFullFilePath );
        if(0 != iRetCode){
            m_ABRELogger=null;
        }
        setLogObj( m_ABRELogger ) ;
        String aszDebugLevel = m_SitePropertyObject.getProperty(ABREAppServerDef.DEBUGLEVEL);
        m_ABRELogger.setDebugLevel(aszDebugLevel);
        return 0;
    }
    // end-of initializeLoggerObject()

    //==== END   Logger Sections ====>
    //==== END   Logger Sections ====>
    //==== END   Logger Sections ====>


    /**
    * Initialize Database Connection
    */
    private int InitDatabase(){
        int iRetCode, iTempMaxConn=0;
        String aszTmpValue=null;

        if(null == m_SitePropertyObject) return 1;
        if(null != m_DatabaseManager) return 0;

        /**
        * extract JDBC class name for driver
        */
        m_JDBCClassName = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_JDBC);
        if(null == m_JDBCClassName){
            setErr("jdbc data source name missing from configuration");
            return 1;
        }
        if(m_JDBCClassName.length() < 2){
            setErr("jdbc data source name missing from configuration");
            return 1;
        }

        /**
        * extract Database JDBC URL
        */
        m_JDBCDBURL = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_DBURL);
        if(null == m_JDBCDBURL){
            setErr("jdbc URL missing from configuration");
            return 1;
        }
        if(m_JDBCDBURL.length() < 2){
            setErr("jdbc URL missing from configuration");
            return 1;
        }

        /**
        * Extract Database Login User
        */
        m_DBUser = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_USER);
        if(null == m_DBUser){
            setErr("data source userID missing from configuration");
            return 1;
        }
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MAXCONNECTIONS);
        iTempMaxConn = convertToInt( aszTmpValue ) ;
        if(iTempMaxConn > 1){
            m_DBMaxConnections = iTempMaxConn;
        }
        m_DBPassword = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_PASSWORD);
        if(null == m_DBPassword){
            m_DBPassword="";
        }
        // DB Monitor Thread - Active or Not-Active - default is active
        m_DBMonThreadActive = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_ACTIVE);
        // DB Monitor Thread - Sleep Time in Milliseconds
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_SLEEPTIME);
        m_lDBMonThreadSleepTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Maximum Connection In Use Time in Milliseconds
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MAXINUSETIME);
        m_lDBMonThreadMaxInUseTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Minimum InActive Connections
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MIN_INACTIVE_CONN);
        iTempMaxConn = convertToInt( aszTmpValue ) ;
        m_iDBMonThreadMinInActiveConnections = iTempMaxConn;
        // DB Monitor Thread - Maximum InActive Connection Time in Milliseconds
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MAX_INACTIVE_TIME);
        m_lDBMonThreadMaxInActiveConnTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Loop Counter 
        aszTmpValue = m_SitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_LOOP_COUNT_THREAD);
        m_iDBMaxLoppCounter = convertToInt( aszTmpValue ) ;
        m_DatabaseManager=null;
        if(null == m_DatabaseManager){
            m_DatabaseManager = new ABREDBConnManager();
            m_DatabaseManager.setLogObj(getLogObj());
        }
        if(null == m_DatabaseManager){
            setErr("error allocating Database manager Object");
            return 1;
        }
        m_DatabaseManager.setMaxLoopCounter(m_iDBMaxLoppCounter);
        m_DatabaseManager.setMaxConnections(m_DBMaxConnections);
        m_DatabaseManager.setDBMonitorThreadActive(m_DBMonThreadActive);
        m_DatabaseManager.setBackgroundThreadSleepTime(m_lDBMonThreadSleepTime);
        m_DatabaseManager.setMaxInUseConnectionTime(m_lDBMonThreadMaxInUseTime);
        m_DatabaseManager.setMaxInActiveOpenConnections(m_iDBMonThreadMinInActiveConnections);
        m_DatabaseManager.setMaxIdelConnectionTime(m_lDBMonThreadMaxInActiveConnTime);

        iRetCode = m_DatabaseManager.connectJDBC(m_JDBCDBURL,m_JDBCClassName,m_DBUser,m_DBPassword);
        if(iRetCode > 0){
            setErr("error connecting to data source " + m_JDBCDBURL);
            m_DatabaseManager.copyErrObj(getErrObj());
            ErrorsToLog();
            return 1;
        }
        logInfo("ABREAppServer: JDBC-URL(" + m_JDBCDBURL + ") JDBC-Class-Name("+ m_JDBCClassName + ") Initialized with MaxConnections(" + m_DBMaxConnections + ")");
        return 0;
    }
    // end-of InitDatabase()



    /**
    * Initialize Database Connection
    */
    private static int InitCronDatabase(){
        int iRetCode, iTempMaxConn=0;
        String aszTmpValue=null;

        if(null != m_DatabaseManager) return 0;

        /**
        * extract JDBC class name for driver
        */
        m_JDBCClassName = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_JDBC);
        if(null == m_JDBCClassName){
//            setErr("jdbc data source name missing from configuration");
            return 1;
        }
        if(m_JDBCClassName.length() < 2){
//            setErr("jdbc data source name missing from configuration");
            return 1;
        }

        /**
        * extract Database JDBC URL
        */
        m_JDBCDBURL = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_DBURL);
        if(null == m_JDBCDBURL){
 //           setErr("jdbc URL missing from configuration");
            return 1;
        }
        if(m_JDBCDBURL.length() < 2){
//            setErr("jdbc URL missing from configuration");
            return 1;
        }

        /**
        * Extract Database Login User
        */
        m_DBUser = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_USER);
        if(null == m_DBUser){
//            setErr("data source userID missing from configuration");
            return 1;
        }
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MAXCONNECTIONS);
        iTempMaxConn = convertToInt( aszTmpValue ) ;
        if(iTempMaxConn > 1){
            m_DBMaxConnections = iTempMaxConn;
        }
        m_DBPassword = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_PASSWORD);
        // DB Monitor Thread - Active or Not-Active - default is active
        m_DBMonThreadActive = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_ACTIVE);
        // DB Monitor Thread - Sleep Time in Milliseconds
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_SLEEPTIME);
        m_lDBMonThreadSleepTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Maximum Connection In Use Time in Milliseconds
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MAXINUSETIME);
        m_lDBMonThreadMaxInUseTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Minimum InActive Connections
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MIN_INACTIVE_CONN);
        iTempMaxConn = convertToInt( aszTmpValue ) ;
        m_iDBMonThreadMinInActiveConnections = iTempMaxConn;
        // DB Monitor Thread - Maximum InActive Connection Time in Milliseconds
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_MONITOR_THREAD_MAX_INACTIVE_TIME);
        m_lDBMonThreadMaxInActiveConnTime = convertToLong( aszTmpValue );
        // DB Monitor Thread - Loop Counter 
        aszTmpValue = m_CronSitePropertyObject.getProperty(ABREAppServerDef.DATABASE_DEF_LOOP_COUNT_THREAD);
        m_iDBMaxLoppCounter = convertToInt( aszTmpValue ) ;
        m_DatabaseManager=null;
        if(null == m_DatabaseManager){
            m_DatabaseManager = new ABREDBConnManager();
//            m_DatabaseManager.setLogObj(getLogObj());
        }
        if(null == m_DatabaseManager){
//            setErr("error allocating Database manager Object");
            return 1;
        }
        m_DatabaseManager.setMaxLoopCounter(m_iDBMaxLoppCounter);
        m_DatabaseManager.setMaxConnections(m_DBMaxConnections);
        m_DatabaseManager.setDBMonitorThreadActive(m_DBMonThreadActive);
        m_DatabaseManager.setBackgroundThreadSleepTime(m_lDBMonThreadSleepTime);
        m_DatabaseManager.setMaxInUseConnectionTime(m_lDBMonThreadMaxInUseTime);
        m_DatabaseManager.setMaxInActiveOpenConnections(m_iDBMonThreadMinInActiveConnections);
        m_DatabaseManager.setMaxIdelConnectionTime(m_lDBMonThreadMaxInActiveConnTime);

        iRetCode = m_DatabaseManager.connectJDBC(m_JDBCDBURL,m_JDBCClassName,m_DBUser,m_DBPassword);
        if(iRetCode > 0){
//            setErr("error connecting to data source " + m_JDBCDBURL);
//            m_DatabaseManager.copyErrObj(getErrObj());
//            ErrorsToLog();
            return 1;
        }
//        logInfo("ABREAppServer: JDBC-URL(" + m_JDBCDBURL + ") JDBC-Class-Name("+ m_JDBCClassName + ") Initialized with MaxConnections(" + m_DBMaxConnections + ")");
        return 0;
    }
    // end-of InitCronDatabase()


    // ---------------------------- Private Variables
    // ---------------------------- Private Variables
    // ---------------------------- Private Variables

    // application property file name
    private String m_ConfigFolder="\\WEB-INF\\";
    private ABREPropertyFile m_ApplicationPropertyObject=null;
    private ABREPropertyFile m_SitePropertyObject=null;
    private static ABREPropertyFile m_CronSitePropertyObject=null;
    private String m_SitePropertyFileName=null;
    private String m_ApplicationPropertyFileName=null;
    private String m_ApplicationFolderName=null;

    /* List of session objects */
    private Hashtable m_SessionList=null;
    private int m_iSessionID=0;

    private boolean m_bInitError=true;

	/* Configuration related Variables */
	private static String m_JDBCClassName=null;
    private static String m_JDBCDBURL=null;
	private static String m_DBUser=null;
	private static String m_DBPassword=null;

	private static int m_DBMaxConnections=20;
	private static int m_iDBMaxLoppCounter=0;
	private String m_LogFileName=null;

	// Database background monitor threads
	private static String m_DBMonThreadActive=null;
	private static long m_lDBMonThreadSleepTime=0;
	private static long m_lDBMonThreadMaxInUseTime=0;
	private static long m_lDBMonThreadMaxInActiveConnTime=0;
	private static int m_iDBMonThreadMinInActiveConnections=0;

    private int m_iCounter=1;

	private static ABREDBConnManager m_DatabaseManager=null;

    private boolean m_bSystemShutDown=false;
    private boolean m_bMonitorRefreshFlag=false;


    // Logger services
    private ABRELogger m_ABRELogger=null;

}
