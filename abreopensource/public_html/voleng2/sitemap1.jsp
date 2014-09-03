<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->



<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/sitemap.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/included/sitemap_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>

<% } %>
