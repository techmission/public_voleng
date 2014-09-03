<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/trainingpage.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengboston" )){ %>
	<jsp:include page="/newengland/trainingpage.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>	
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )){ %>
	<jsp:include page="/newengland/trainingpage.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>	
<% } else { %>
	<jsp:include page="/included/trainingpage_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>

<% } %>
