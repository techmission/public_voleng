package com.abrecorp.opensource.appserver;

/**
* System:       Application Layer Interfaces
* Title:        Application User Session Controller Object
* Description:  Application Framework
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;

public class UserSession extends ABRESession {

    /**
    * constructor
    */
    public UserSession(){}

	/**
	* set access to application wide object
	*/
	public void setAppRef(TheApplication aApp){
		// super.setAppRef(aApp);
		if(null == m_MyAppServer){
  			m_MyAppServer=aApp;
        // setLogObj(aApp.getLogObj());
		}
	}
    // end-of method setAppRef()

	/**
	* clean up
	*/
	public void cleanupSession(){
		cleanup();
		super.cleanupSession();
	}
    // end-of method cleanupSession()

	/**
	* get current user session data object
	*/
	public PersonInfoDTO getCurrentUser(){
		allocateUserDataObj();
		return m_UserSession ;
	}
    // end-of method getCurrentUser()
	public void setCurrentUser( PersonInfoDTO aHeadObj ){
		m_UserSession = aHeadObj;
		return ;
	}
    // end-of method setCurrentUser()


	/**
	* get session navigation data object
	*/
	public AppSessionDTO getSessionNavigationData(){
		allocateUserNavigationDataObj();
		return m_UserSessionNagData ;
	}


	//====== Private Methods
    //====== Private Methods
    //====== Private Methods


    /**
    * clean up all objects
    */
    private void cleanup(){
        m_MyAppServer=null;
    }
    // end-of method cleanup()


    /**
    * allocate user session data objects
    */
    private void allocateUserDataObj(){
        if(null == m_UserSession) {
            m_UserSession = new PersonInfoDTO();
        }
    }
    // end-of method allocateUserDataObj()

    /**
     * allocate user session data objects
     */
     private void allocateUserNavigationDataObj(){
         if(null == m_UserSessionNagData) {
        	 m_UserSessionNagData = new AppSessionDTO();
         }
     }
     // end-of method allocateUserNavigationDataObj()

    //====== Private Variables
    //====== Private Variables
    //====== Private Variables

    // The one Application Server Object
    private TheApplication m_MyAppServer=null;

    // Current User Session Object
    private PersonInfoDTO m_UserSession=null;

    // Current User Session Navigation Data Object
    private AppSessionDTO m_UserSessionNagData=null;

}
