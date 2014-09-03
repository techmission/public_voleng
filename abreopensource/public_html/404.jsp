<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- 404.jsp -->

<%
			response.setStatus(404);
%>

<% if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>
	<jsp:include page="/agrm/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if( (aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" )) ){ %>
	<jsp:include page="/newengland/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
	<jsp:include page="/ccda/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if( (aszHostID.equalsIgnoreCase( "volenggospel" )) || (aszHostID.equalsIgnoreCase( "volenggospelcom" ))){ %>
	<jsp:include page="/gospelchrisvol/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/hlic/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengfia" )){ %>
	<jsp:include page="/faithinaction/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>
	<jsp:include page="/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
	<jsp:include page="/ivol/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenglfa" )){ %>
	<jsp:include page="/lfa/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )){ %>
	<jsp:include page="/salvationarmy/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengsojo" )){ %>
	<jsp:include page="/sojourners/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>
	<jsp:include page="/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	<jsp:include page="/uywi/500.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if( (aszHostID.equalsIgnoreCase( "volengworldvision" )) || (aszHostID.equalsIgnoreCase( "volengpyfia" )) ) { %>
	<jsp:include page="/worldvision/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
	<jsp:include page="/youthpartnersnet/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengyounglife" )){ %>
	<jsp:include page="/younglife/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>

<% } else if(aszPortal.length() < 1) { %>
	<jsp:include page="/404.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } else { %>
	<jsp:include page="/404b.shtml" flush="false">
		<jsp:param name="" value="" />
	</jsp:include>
<% } %>