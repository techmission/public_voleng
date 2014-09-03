<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/work_study.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/included/work_study_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>

<% } %>
