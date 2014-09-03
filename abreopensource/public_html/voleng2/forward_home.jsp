<%
	response.setStatus(301);
	response.setHeader( "Location", "http://www.christianvolunteering.org" );		
	response.setHeader( "Connection", "close" );
%>