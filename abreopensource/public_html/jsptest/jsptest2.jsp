<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%
Enumeration enum=null;
%>
<meta http-equiv="Pragma" Content="no-cache">
<meta http-equiv="Expires" Content="Tuesday, 14-Dec-1971 04:30:00 GMT">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<TITLE>JSP Test 102</TITLE>
</HEAD>
<TABLE cellpadding=4 cellspacing=0 border=0 width=300 align="left">
<TBODY>
    <TR><TD colspan=3><H2><A href="/">Go Home</a></h2></td></tr>
    <TR><TD colspan=3><H2><A href="jsptest.jsp">Go jsptest.jsp</a></h2></td></tr>
    <TR><TD colspan=3><H2><A href="jsptest2.jsp">Go jsptest2.jsp</a></h2></td></tr>
    <TR><TD colspan=3><H2><A href="jsptest2.jst">test 404 error</a></h2></td></tr>
<TR>
    <td colspan=3>
        <%
            if ( null == request ){
                out.println("request object is null");
            } else {
                out.println("request object is OK");
            }
        %>
    </TD>
</TR>
</TR>
    <td colspan=3>
      Context Path: request.getContextPath() : <%= request.getContextPath() %>
    </TD>
<TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            if ( null == request.getSession() ){
                out.println("session object is null");
            } else {
                out.println("session object is OK");
            }
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " Session Attributes Follow : <BR>" );
            enum = request.getSession().getAttributeNames();
            if( null != enum ){
                while ( enum.hasMoreElements() ) {
                    String aszName = (String) enum.nextElement() ;
                    if( null == aszName ) continue ; 
                    aszName = aszName.trim();
                    if( aszName.length() < 1 ) continue ;
                    out.println( " &nbsp; &nbsp; \"" + aszName + "\"   <BR>" );
                }
            }
//out.println(request.getSession().getAttribute("counter"));
//out.println(request.getSession().getAttribute("username"));
out.println();
out.println("Session id:");
out.println(session.getId());
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " Request Headers Follow : <BR>" );
            enum = request.getHeaderNames() ;
            if( null != enum ){
                while ( enum.hasMoreElements() ) {
                    String aszName = (String) enum.nextElement() ;
                    if( null == aszName ) continue ; 
                    aszName = aszName.trim();
                    if( aszName.length() < 1 ) continue ;
                    out.println( " &nbsp; &nbsp; \"" + aszName + "\"   <BR>" );
                }
            }
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " Session Attributes Follow : <BR>" );
            Object objenum = request.getAttributeNames();
            enum = request.getAttributeNames();
            if( null != enum ){
                while ( enum.hasMoreElements() ) {
                    String aszName = (String) enum.nextElement() ;
                    if( null == aszName ) continue ;
                    aszName = aszName.trim();
                    if( aszName.length() < 1 ) continue ;
                    out.println( " &nbsp; &nbsp; \"" + aszName + "\"   <BR>" );
                }
            }
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " PathTransalated Follows : \"" + request.getPathTranslated() + "\"   <BR>" );
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " ServletPath Follows : \"" + request.getServletPath() + "\"   <BR>" );
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " Servlet Context Follows : <BR>" );
            if( null != request.getSession().getServletContext() ){
                enum = request.getSession().getServletContext().getAttributeNames();
                if( null != enum ){
                    while ( enum.hasMoreElements() ) {
                        String aszName = (String) enum.nextElement() ;
                        if( null == aszName ) continue ; 
                        aszName = aszName.trim();
                        if( aszName.length() < 1 ) continue ;
                        out.println( " &nbsp; &nbsp; \"" + aszName + "\"<BR>" );
                    }
                }
            } else {
                out.println( " null Servlet Context <BR>" );
            }
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " ServerInfo Follows : \"" + request.getSession().getServletContext().getServerInfo() + "\"   <BR>" );
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>
<TR>
    <td colspan=3>
        <%
            out.println( " RealPath Follows : \"" + request.getSession().getServletContext().getRealPath("/") + "\"   <BR>" );
        %>
    </TD>
</TR>
<TR><td COLSPAN=3><HR></td></TR>

</TBODY>
</TABLE>
</FORM>
</BODY>
</html>
