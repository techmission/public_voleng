package com.abrecorp.opensource.base;

/**
* Title:   Base Application Session Class
* Description:
* Copyright:    Copyright (c) 2001
* Company:
* @author
* @version 1.0
*/


public abstract class ABRESession extends ABREBase {



    /**
    * access to application wide services
    */
    public void setBaseAppRef(ABREAppServer aApp){
        if(null == aApp ) return ;
        if(null == m_ABREAppServerObj){
            m_ABREAppServerObj = aApp;
            setLogObj(aApp.getLogObj());
        }
    }


    /**
    * set session ID
    */
    public void setSessionID(int sess){
        m_iSessionID=sess;
    }

    /**
    * get session ID
    */
    public int getSessionID(){
        return m_iSessionID;
    }


    /**
    * get JSP Servlet Engine Session Create Time
    */
    public long getCreationTime(){
        return m_lSessionCreateTime ;
    }
    public void setCreationTime( long number ){
        m_lSessionCreateTime = number ;
    }


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

    // ---------------------------- Protected Methods
    // ---------------------------- Protected Methods
    // ---------------------------- Protected Methods

	/**
	* session cleanup
	*/
	protected void cleanupSession(){
        m_aszSessionVariables=null;
        m_iMaxInactiveInterval=0;
        m_lSessionCreateTime=0;
		if (null == m_ABREAppServerObj) return ;
		if(m_iSessionID < 1) return;
		m_ABREAppServerObj.removeSession(m_iSessionID);
	}


    // ---------------------------- Private Variables
    // ---------------------------- Private Variables
    // ---------------------------- Private Variables

    private int m_iSessionID=0;
    private ABREAppServer m_ABREAppServerObj=null;


    // JSP Servlet Runtime Information
    private int m_iMaxInactiveInterval=0;
    private long m_lSessionCreateTime=0;
    private String m_aszSessionVariables=null;



}
