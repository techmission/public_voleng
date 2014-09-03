<!-- start JSP information -->
<%@ include file="/template/topjspnologin1.inc" %>
<!-- end JSP information -->



<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/sitemap.jsp" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/included/sitemap_include.jsp" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>

<% } %>
