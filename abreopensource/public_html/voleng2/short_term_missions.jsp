<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ %>
	<jsp:include page="/ivol/service_trip.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>

	<jsp:include page="/short_term_missions.jsp" flush="false">

		<jsp:param name="a" value="" />

	</jsp:include>

<% } else { %>
	<jsp:include page="/included/short_term_missions_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } %>

