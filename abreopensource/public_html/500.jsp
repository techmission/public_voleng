<% response.setStatus(500); %>

<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 

<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<%
String connectionURL="";
String userName="";
String password="";
%>

<%@ include file="/template_include/db.inc" %>

<%
String aszCurrURL = request.getRequestURL().toString();
String aszQueryString = request.getQueryString();
String aszUserAgent = request.getHeader("user-agent");

// declare a connection by using Connection interface 
Connection connection = null;
// declare object of Statement interface that uses for executing sql statements.
PreparedStatement pstatement = null;
// Load JBBC driver "com.mysql.jdbc.Driver"
Class.forName("com.mysql.jdbc.Driver").newInstance();
int updateQuery = 0;
try {
	//Create a connection by using getConnection() method that takes parameters of string type connection url, user name and password to connect to database. 
    connection = java.sql.DriverManager.getConnection(connectionURL, userName, password);
    // sql query to insert values in the secified table.
    String queryString = 
       	"INSERT INTO error_logs(timestamp,referrer,query_string,current_url,subdomain,user_agent) " +
       	" VALUES ({fn now()}, ?, ?, ?, ?, ?)";
    // createStatement() is used for create statement object that is used for sending sql statements to the specified database. 
    pstatement = connection.prepareStatement(queryString);
    pstatement.setString(1, aszReferer);
    pstatement.setString(2, aszQueryString);
    pstatement.setString(3, aszCurrURL);
    pstatement.setString(4, aszRemoteHost);
    pstatement.setString(5, aszUserAgent);
    updateQuery = pstatement.executeUpdate();
}catch (Exception ex) {
	out.print(ex);
	out.println("<br>Unable to connect to database.");
}finally {
	// close all the connections.
	pstatement.close();
	connection.close();
}
%>



<% if( (aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" )) ){ %>
	<jsp:include page="/newengland/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>
	<jsp:include page="/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>
	<jsp:include page="/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>

<% } else { %>
	<jsp:include page="/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } %>
