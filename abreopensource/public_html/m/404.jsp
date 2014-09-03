<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<% if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/hlic/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } %>