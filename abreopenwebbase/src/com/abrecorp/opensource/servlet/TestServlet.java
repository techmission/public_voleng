package com.abrecorp.opensource.servlet;

/**
* System:       Java Server Pages Test Utility
* Title:        Test Basic Servlet Functions
* Description:  
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class TestServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private String m_aszClassName=this.getClass().getName();

    /**
    * application wide variables
    **/
    private static ServletContext m_ServletContext = null;
    private String m_aszRealPath=null;

    /**
    * Initialize global variables
    **/
    public void init(ServletConfig config) throws ServletException{
        m_ServletContext = config.getServletContext();
        m_aszRealPath = m_ServletContext.getRealPath("/");
        super.init(config);
    }
    // end-of method init()

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int iRetCode=0;
        RequestDispatcher aReqDispObj=null;

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>"+ m_aszClassName +"</title></head>");
        out.println("<body>");
        out.println("<p>The servlet "+ m_aszClassName +" has received a doGet() request. This is the reply.</p>");

        if(null == m_ServletContext) {
            out.println("<p>null ServletContext</p>");
        } else {
            out.println("<p>m_ServletContext.getRealPath() &nbsp; &nbsp;&nbsp;");
            out.println( m_ServletContext.getRealPath("/") +"</p>");
        }

        HttpSession session=null;
        ServletContext aServletContext=null;
        // get session context
        session = request.getSession();
        if(null == session) {
            out.println("<p>null jsp session</p>");
        } else {
            out.println("<p>jsp in session</p>");
            out.println("<p> session.getMaxInactiveInterval() is "+ session.getMaxInactiveInterval() +"</p>");
            out.println("<p> session.getCreationTime() is "+ session.getCreationTime() +"</p>");
            out.println("<p>Session Attributes Follow</p>");
            Enumeration aEnumObj = session.getAttributeNames();
            if( null != aEnumObj ){
                while ( aEnumObj.hasMoreElements() ) {
                    String aszName = (String) aEnumObj.nextElement() ;
                    if( null == aszName ) continue ; 
                    aszName = aszName.trim();
                    if( aszName.length() < 1 ) continue ;
                    out.println("<p> Name: "+ aszName +"</p>");
                }
            }
            out.println("<p><BR>Request Headers Follow :</p>");
            aEnumObj = request.getHeaderNames() ;
            if( null != aEnumObj ){
                while ( aEnumObj.hasMoreElements() ) {
                    String aszName = (String) aEnumObj.nextElement() ;
                    if( null == aszName ) continue ; 
                    aszName = aszName.trim();
                    if( aszName.length() < 1 ) continue ;
                    out.println("<p> Value: "+ aszName +"</p>");
                }
            }
            if( null != request.getPathTranslated() ){
                out.println("<p> <BR>PathTransalated Follows : \""+ request.getPathTranslated() +"\"  </p>");
            }
            if( null != request.getServletPath() ){
                out.println("<p> <BR>ServletPath Follows : \""+ request.getServletPath() +"\"  </p>");
            }
            out.println("<p><BR>Servlet Context Follow :</p>");
            aServletContext = session.getServletContext();
            if( null != aServletContext ){
                aEnumObj = aServletContext.getAttributeNames();
                if( null != aEnumObj ){
                    while ( aEnumObj.hasMoreElements() ) {
                        String aszName = (String) aEnumObj.nextElement() ;
                        if( null == aszName ) continue ; 
                        aszName = aszName.trim();
                        if( aszName.length() < 1 ) continue ;
                        out.println("<p> Value: "+ aszName +"</p>");
                    }
                }
                out.println("<p> <BR> ServerInfo Follows: \""+ aServletContext.getServerInfo() +"\" </p>");
                out.println("<p> <BR> RealPath Follows: \""+ aServletContext.getRealPath("/") +"\" </p>");
            }

        }


        out.println("</body></html>");
        out.close();
    }
    // end-of method doGet()




}
