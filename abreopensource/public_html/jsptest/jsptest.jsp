<%
String aszHostName = request.getHeader("host");
String aszHostID="default";
if(aszHostName.equalsIgnoreCase( "www.christianvolunteering.org:10060" )){
    aszHostID="voleng1";
}
%>
<html>
<head>
<title>Test JSP page</title>
</head>
<body bgcolor=white>
<table border="0">
    <tr>
        <td><h1>Sample Application JSP Page </h1>
        This is the output of a JSP page and It displays useful values from the request currently processing.
        </td>
    </tr>
    <TR><TD colspan=1><H2><A href="/">Go Home</a></h2></td></tr>
    <TR><TD colspan=1><H2><A href="jsptest.jsp">Go jsptest.jsp</a></h2></td></tr>
    <TR><TD colspan=1><H2><A href="jsptest2.jsp">Go jsptest2.jsp</a></h2></td></tr>
    <TR><TD colspan=1><H2><A href="jsptest2.jst">test 404 error</a></h2></td></tr>
</table>
<table border="0" border="100%">
    <tr>  
        <th align="right">Get Header: request.getHeader("host")</th>  
        <td align="left">
            <%= request.getHeader("host") %>   <%=aszHostID%>
        </td>
    </tr>
    <tr>  
        <th align="right">Context Path: request.getContextPath()</th>  
        <td align="left">
            <%= request.getContextPath() %>
        </td>
    </tr>
    <tr>  
        <th align="right">Path Information: request.getPathInfo() </th>  
        <td align="left">
            <%= request.getPathInfo() %>
        </td>
    </tr>
    <tr>  
        <th align="right">Query String: request.getQueryString()</th>  
        <td align="left">
            <%= request.getQueryString() %>
        </td>
    </tr>
    <tr>  
        <th align="right">Request Method: request.getMethod()</th>  
        <td align="left">
            <%= request.getMethod() %>
        </td>
    </tr>
    <tr>  
    <th align="right">Servlet Path: request.getServletPath()</th>  
        <td align="left">
            <%= request.getServletPath() %>
        </td>
    </tr>
</table>
</body>
</html>
