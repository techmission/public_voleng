<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<% if(aszHostID.equalsIgnoreCase( "volengfia" )){ %>
	<jsp:include page="/faithinaction/volunteer3.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/included/volunteer3_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } %>

