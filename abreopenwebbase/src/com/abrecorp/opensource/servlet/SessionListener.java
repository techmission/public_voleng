package com.abrecorp.opensource.servlet;

/**
* System:       Application Servlet User Session
* Sub-System:   Servlet JSP
* Title:        Application Servlet Session TimeOut Listener
* Description:  Servlet TimeOut
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import javax.servlet.http.*;

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.appserver.*;

public class SessionListener implements HttpSessionBindingListener {

    /**
    * get framework session manger
    */
    public UserSession getSession(){
        return m_UserSessionObj;
    }

    /**
    * create the framework session object
    **/
    public void createSession(TheApplication aServerObj){
        if(null == aServerObj) return;
        if(null != m_UserSessionObj) return;
        m_UserSessionObj = aServerObj.getNewSession();
    }


    /**
    * session destroy
    **/
    public void valueUnbound(HttpSessionBindingEvent se) {
	      if (m_UserSessionObj == null) return ;
        m_UserSessionObj.cleanupSession();
        m_UserSessionObj=null;
    }

    /**
    * session create
    **/
    public void valueBound(HttpSessionBindingEvent se) {}


    // ------------------------- Private Variables
    // ------------------------- Private Variables
    // ------------------------- Private Variables


    private UserSession m_UserSessionObj=null;


}
