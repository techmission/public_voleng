<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ %>
	<jsp:include page="/ivol/virtual_vol.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengchurch" ) ){ %>
        <jsp:include page="/virtual_vol.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } else { %>
        <jsp:include page="/included/virtual_vol_include.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } %>

