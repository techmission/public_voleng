package com.abrecorp.opensource.servlet;

/**
* System:       AT-CARE
* Title:        Application Servlet Container Initialization
* Description:  Application Framework Initialization
* Copyright:    Copyright (c) 1998-2004
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
* modifications: 12/04/2006 changed for singleton
*/

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

import com.abrecorp.opensource.appserver.*;
import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;

public class ServletAppInit extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    /**
    * application wide variables
    **/
    private static ServletContext m_ServletContext = null;

    /**
    * Initialize global variables
    **/
    public void init(ServletConfig config) throws ServletException{
        String aszConfigFolderName=null, aszFullPathConfigFolder=null, aszConfigFileName=null;
        m_ServletContext = config.getServletContext();
        String aWebServerRealPath = m_ServletContext.getRealPath("/");
        super.init(config);

        /**
        * get configuration file folder name
        */
        aszConfigFolderName = m_ServletContext.getInitParameter( ABREBaseDef.CONFIGFOLDERNAME );
        if(null == aszConfigFolderName){
            aszConfigFolderName="WEB-INF";
        }
        aszConfigFolderName = aszConfigFolderName.trim();
        if(aszConfigFolderName.length() < 2){
            aszConfigFolderName="WEB-INF";
        }


        /**
        * get Site configuration file name like database information folders
        */
        String aszConfigSiteFileName = m_ServletContext.getInitParameter( ABREBaseDef.APPLICATIONSITEPROPERTYFILE );
        if(null == aszConfigSiteFileName){
            aszConfigSiteFileName="ApplicationSiteResources";
        }
        aszConfigSiteFileName = aszConfigSiteFileName.trim();
        if(aszConfigSiteFileName.length() < 2){
            aszConfigSiteFileName="ApplicationSiteResources";
        }

        /**
        * get application configuration file name
        */
        aszConfigFileName = m_ServletContext.getInitParameter( ABREBaseDef.APPLICATIONPROPERTYFILE );
        if(null == aszConfigFileName){
            aszConfigFileName="ApplicationResources";
        }
        aszConfigFileName = aszConfigFileName.trim();
        if(aszConfigFileName.length() < 2){
            aszConfigFileName="ApplicationResources";
        }

        aszFullPathConfigFolder = aWebServerRealPath + aszConfigFolderName ;
        
        // create a new application server object
        // TheApplication aTheAppObj = new TheApplication();
        // implemented singleton
        TheApplication aTheAppObj = TheApplication.getInstance();
        int iRetCode = aTheAppObj.Init(aszFullPathConfigFolder,aszConfigFileName,aszConfigSiteFileName);
        if(0 != iRetCode){
            System.out.println("Error: initializing application server object");
        }
        aTheAppObj.setCurentDateTime();

        // save server object in Application storage pool
        try {
            getServletContext().setAttribute( ABREBaseDef.WEBAPPSERVERKEY, aTheAppObj );
        } catch (Exception e) {
            System.out.println("appinit load exception" + e.toString());
            throw new UnavailableException("Cannot load appinit ");
        }

    }
    // end-of mothod init()


    /**
    * Clean up resources
    **/
    public void destroy() {
        try {
            getServletContext().removeAttribute( ABREBaseDef.WEBAPPSERVERKEY );
        } catch (Exception e) {
        }
        super.destroy();
    }
    // end-of mothod destroy()



    /**
    * HTTP Get request
    **/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int iRetCode=0;
        RequestDispatcher aReqDispObj=null;

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>ApplicationInit</title></head>");
        out.println("<body>");

        if(null == m_ServletContext) {
            out.println("<p>null ServletContext</p>");
        } else {
            out.println("<p>m_ServletContext.getRealPath() &nbsp; &nbsp;&nbsp;");
            out.println( m_ServletContext.getRealPath("/") +"</p>");
            out.println("<p>configuration file folder name &nbsp; &nbsp;&nbsp;");
            out.println( m_ServletContext.getInitParameter((String)ABREBaseDef.CONFIGFOLDERNAME) +"</p>");
            out.println("<p>Site configuration file name &nbsp; &nbsp;&nbsp;");
            out.println( m_ServletContext.getInitParameter((String)ABREBaseDef.APPLICATIONSITEPROPERTYFILE) +"</p>");
            out.println("<p>Application configuration file name &nbsp; &nbsp;&nbsp;");
            out.println( m_ServletContext.getInitParameter((String)ABREBaseDef.APPLICATIONPROPERTYFILE) +"</p>");
        }

        HttpSession session=null;
        ServletContext aServletContext=null;
        TheApplication aServerObj=null;
        SessionListener aSessionListenerObj=null;
        UserSession aUserSessionObj=null;
        PersonInfoDTO aLoginUser=null;

        // get session context
    	session = request.getSession();
        if(null == session) {
            out.println("<p>jsp session in NULL</p>");
        } else {
            out.println("<p>jsp session OK</p>");
        }

        if(null != session) {
            aServletContext = session.getServletContext();
            if(null == aServletContext){
                out.println("<p>session Context is NULL</p>");
            } else {
                out.println("<p>session Context OK</p>");
            }
        }

        if(null != session) {
        	aSessionListenerObj = (SessionListener)session.getAttribute(ABREBaseDef.WEBSESSIONKEY);
            if(null == aSessionListenerObj) {
                out.println("<p>SessionListener is NULL</p>");
            } else {
                aUserSessionObj = aSessionListenerObj.getSession();
                out.println("<p>SessionListener OK</p>");
            }
        }

        if(null != aServletContext) {
            aServerObj = (TheApplication) aServletContext.getAttribute(ABREBaseDef.WEBAPPSERVERKEY);
            if(null == aServerObj){
                out.println("<p>The Application Object is NULL</p>");
            } else {
                out.println("<p>The Application Object is OK</p>");
            }
    	}

        if(null != aServerObj){
            out.println("<p><U>The Application Object Section START</U>");
            out.println("<BR>&nbsp;&nbsp;App Init Error: "+ aServerObj.IsInitError() +"");
            out.println("<BR>&nbsp;&nbsp;Can Log Folder be read: "+ aServerObj.canLogFolderBeRead() +"");
            out.println("<BR>&nbsp;&nbsp;App Config Folder be read: "+ aServerObj.getAppConfigFolder() +"");
            out.println("<BR>&nbsp;&nbsp;App Attachment Folder: "+ aServerObj.getSitePropertyValue( ABREBaseDef.ATTACH_FILE1_LOCATION ) +"");
            out.println("<BR>&nbsp;&nbsp;Primary Database JDBC Driver: "+ aServerObj.getSitePropertyValue( ABREAppServerDef.DATABASE_DEF_JDBC ) +"");
            out.println("<BR>&nbsp;&nbsp;Primary Database JDBC URL: "+ aServerObj.getSitePropertyValue( ABREAppServerDef.DATABASE_DEF_DBURL ) +"");
            // aServerObj.ReInitApplication();
            // aServerObj.ReInitPrimaryDatabase();
            // aServerObj.ReInitSecondaryDatabase();
            out.println("<BR><U>The Application Object Section END</U></p>");
        }

    	if(null != aUserSessionObj) {
            aLoginUser = aUserSessionObj.getCurrentUser();
            out.println("<p>found ApplicationPerson </p>");
            if(null != aLoginUser) {
                if(111 == aLoginUser.getUserIsLoggedIntoSystem()){
                    out.println("<p>user is not logged-in </p>");
                } else {
                    out.println("<p>YES! user is logged-in </p>");
                }
            }
        }

        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
    // end-of DoGet()

    /**
    * HTTP Post request
    **/
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost( request, response );
    }
    // end-off doPost()




}
