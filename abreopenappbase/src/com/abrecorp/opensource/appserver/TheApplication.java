package com.abrecorp.opensource.appserver;

/**
* System:       Application Layer Interfaces
* Title:        The One And Only Application Server Object
* Description:  Application Framework Initialization
* Copyright:    Copyright (c) 1997-2007
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
* 12/04/2006 added singleton for The Application Object
*/

import java.util.*;

import com.abrecorp.opensource.base.*;

public class TheApplication extends ABREAppServer {

	// singleton
	private static TheApplication m_oInstance=null;

    /**
	* singleton
	*/
	public static TheApplication getInstance(){
		if(null == m_oInstance){
			m_oInstance = new TheApplication();
		}
		return m_oInstance;
	}
	// end-of methos getInstance()

	/**
    * Constructor
    * made protected for singleton
    */
    protected TheApplication(){}

    /**
    * Initialize application services application folder and property file name
    */
    public int Init(String folder, String propertyfile, String siteconfig ){
        int iRetCode=0;
        MethodInit("Init");
        iRetCode = super.InitApplication( folder, propertyfile, siteconfig);
        return iRetCode;
    }
    // end-of Init()


	/**
	* create a new session object
	*/
	public synchronized UserSession getNewSession(){
		MethodInit("getNewSession");
		UserSession aNewSessionObj = new UserSession();
		super.addNewSession(aNewSessionObj);
		aNewSessionObj.setAppRef(this);
		return aNewSessionObj;
	}
    // end-of getNewSession()




    // ----------------------------- Protected Methods
    // ----------------------------- Protected Methods
    // ----------------------------- Protected Methods


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


    // ---------------------------- Private Methods
    // ---------------------------- Private Methods
    // ---------------------------- Private Methods


    // ---------------------------- Private Variables
    // ---------------------------- Private Variables
    // ---------------------------- Private Variables


    /* List of session objects */
    private Hashtable m_SessionList=null;
    private int m_iSessionID=0;


}
