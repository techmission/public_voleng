package com.abrecorp.opensource.base;

/**
* Title:        ABRE Base Class
* Description:  Provide Basic Services To Business Rules Layer Objects
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. DAvid Marcillo
* @version      1.0
*/

import java.lang.Math;

public abstract class ABREBaseBRLO extends ABREBase {

    /**
    * get application level property value
    */
    public String getAppPropertyValue(String parm){
        if(null == m_ABREAppServerObj) return "";
        if(null == parm) return "";
        String aszValue = parm.trim();
        if(aszValue.length() < 2) return "";
        aszValue = m_ABREAppServerObj.getAppPropertyValue(parm);
        if(null == aszValue) return "";
        return aszValue;
    }
    // end-of method getAppPropertyValue()

    /**
    * get site level property value
    */
    public String getSitePropertyValue(String parm){
        if(null == m_ABREAppServerObj) return "";
        if(null == parm) return "";
        String aszValue = parm.trim();
        if(aszValue.length() < 2) return "";
        aszValue = m_ABREAppServerObj.getSitePropertyValue(parm);
        if(null == aszValue) return "";
        return aszValue;
    }
    // end-of method getSitePropertyValue()


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
    // end-of method setBaseAppRef()

	/**
    * access to application wide services
    */
    public ABREAppServer getBaseAppRef(){
        if(null == m_ABREAppServerObj ) return null;
        return m_ABREAppServerObj;
    }
    // end-of method setBaseAppRef()


	/**
	* get the next unique id number for a requested attribute string
    * March 2005 added a second try - David Marcillo
	*/
	public int getNextUniqueID(String attribute){
        if(null == m_ABREAppServerObj) return -1;
		return m_ABREAppServerObj.getNextUniqueID(attribute);
	}
    // end-of method getNextUniqueID()


    /**
    * get a database connection object
    */
    public ABREDBConnection getDBConn(){
        if(null == m_ABREAppServerObj) return null;
        return m_ABREAppServerObj.getDBConn();
    }
    // end-of method getDBConn()


    /**
    * get a database connection object
    */
    public ABREDBConnection getCronDBConn(){
//        if(null == m_ABREAppServerObj) m_ABREAppServerObj=new ABREAppServer();
    	
        return com.abrecorp.opensource.base.ABREAppServer.getCronDBConn();
    }
    // end-of method getCronDBConn()

    

    /**
    * clean up
    */
    public void cleanup(){
    	m_ABREAppServerObj=null;
    }
    // end-of method cleanup()


    /**
    * Cleup all application objects
    */
    public void dispose(){
        cleanup();
    }
    // end-of method dispose()


    // ---------------------------- Private Variables
    // ---------------------------- Private Variables
    // ---------------------------- Private Variables


	private ABREAppServer m_ABREAppServerObj=null;

}
