package com.abrecorp.opensource.servlet;

/**
* System:       Java Server Pages Base Utility
* Title:        Application Servlet Base JSP Abstract Class
* Description:  Application Framework Initialization
* Copyright:    Copyright (c) 1998-2003
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import javax.servlet.*;
import javax.servlet.http.*;

import com.abrecorp.opensource.appserver.*;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.AppSessionDTO;
import com.abrecorp.opensource.base.*;

public class BaseServletABRE extends HttpServlet {

    /**
	* Return The Current User Session Navigation Data Object
	*/
	public AppSessionDTO getSessionNavData(HttpServletRequest request){
	   	UserSession session = getSessionManager( request );
	    if(null == session) return null;
	    return session.getSessionNavigationData();
	}
	// end-of method getSessionNavData()

    /**
    * Is Current Session Logged-In ?
    */
	public boolean IsSessionLoggedIn(HttpServletRequest request){
    	UserSession session = getSessionManager( request );
        if(null == session) return false;
        PersonInfoDTO aHeadObj = session.getCurrentUser();
        if(null == aHeadObj) return false;
        if(aHeadObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK) return true;
        if(aHeadObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL) return true; // *** modified - added August 2010 by ali
        return false;
	}
    // end-of method IsSessionLoggedIn()

    /**
    * getLoggedInUserType
    */
	public String getLoggedInUserType(HttpServletRequest request){
    	UserSession session = getSessionManager( request );
        if(null == session) return "";
        PersonInfoDTO aHeadObj = session.getCurrentUser();
        if(null == aHeadObj) return "";
        return aHeadObj.getUSPSiteUseType();
	}
    // end-of method getLoggedInUserType()

    /**
    * Return The Current User Object
    */
    public PersonInfoDTO getCurrentUser(HttpServletRequest request){
    	UserSession session = getSessionManager( request );
        if(null == session) return null;
        return session.getCurrentUser();
    }
    // end-of method getCurrentUser()


    /**
    * Set The Current User Object
    */
    public int setCurrentUser(HttpServletRequest request, PersonInfoDTO aHeadObj ){
    	UserSession session = getSessionManager( request );
        if(null == session) return -1;
        session.setCurrentUser( aHeadObj );
        return 0;
    }
    // end-of method setCurrentUser()

    /**
    * Return Session Manager Object
    */
    public UserSession getSessionManager(HttpServletRequest request){
    	HttpSession session = request.getSession();
        if(null == session) return null;
    	SessionListener aSessionListenerObj = (SessionListener)session.getAttribute( ABREBaseDef.WEBSESSIONKEY );
    	if (aSessionListenerObj == null){
            TheApplication aServerObj = getAppServer(request);
            if (aServerObj == null) return null;
            aSessionListenerObj = new SessionListener();
            aSessionListenerObj.createSession(aServerObj);
            session.setAttribute( ABREAppServerDef.WEBSESSIONKEY, aSessionListenerObj);
    	}
        return aSessionListenerObj.getSession();
    }
    // end-of method getSessionManager()

    /**
    * Return The One and Only Application Object
    */
    public TheApplication getAppServer(HttpServletRequest request){
    	HttpSession session = request.getSession();
        if(null == session) return null;
        ServletContext aServletContext = session.getServletContext();
        TheApplication aServerObj = (TheApplication) aServletContext.getAttribute( ABREBaseDef.WEBAPPSERVERKEY );
        return aServerObj;
    }
    // end-of method getAppServer()

    /**
    * Return A Database Connection Object
    */
    public ABREDBConnection getDBConn( HttpServletRequest request ) {
        TheApplication TheApp = getAppServer(request);
        if(null == TheApp) return null;
        return TheApp.getDBConn();
    }
    // end-of method getDBConn()

	public ABREAppServer getAppServer() {
		// TODO Auto-generated method stub
		return null;
	}


}
