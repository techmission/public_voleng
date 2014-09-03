<!-- start file navigation.inc -->
<% if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>
	<jsp:include page="/template/agrm_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengboston" )){ %>
  <%@ include file="/template/cityservenavigation.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
	<jsp:include page="/template/ccdanavigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengcityserve" )){ %>
  <%@ include file="/template/cityservenavigation.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/template/hlic_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenglfa" )){ %>
	<jsp:include page="/template/empty_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )){ %>
  <%@ include file="/template/cityservenavigation.inc" %>  
<% } else if(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )){ %>
	<jsp:include page="/template/salvationarmy_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengsojo" )){ %>
	<jsp:include page="/template/empty_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	<jsp:include page="/template/uywinavigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengworldvision" )){ %>
	<jsp:include page="/template/worldv_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
  <%@ include file="/template/ivolnavigation.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
	<jsp:include page="/template/youthpartner_navigation.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>

<!-- start file keem_navigation.inc -->

<!-- include standard navigation (image section) -->
<%@ include file="/template/standard_navigation_top.inc" %>

<!-- Begin Menus -->
<!-- include standard navigation (dropdown section) -->
<%@ include file="/template/standard_navigation_dropdown.inc" %>


<!-- End Menus -->
</div>
<% } %>
<!-- end file navigation.inc -->
