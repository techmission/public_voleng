<!-- start file navigation.inc -->

<% if(
		(aszHostID.equalsIgnoreCase( "volengegc" )) ||
		(aszHostID.equalsIgnoreCase( "volengextfocused" )) ||
		(aszHostID.equalsIgnoreCase( "volengfia" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volenglfa" )) ||
		(aszHostID.equalsIgnoreCase( "volengmissionamerica" )) ||
		(aszHostID.equalsIgnoreCase( "volengsaddleback" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengworldvision" )) 
){ %>
  <%@ include file="/template_include/empty_navigation.inc" %>

<% } else { %>




<!-- Begin Menus -->
<!-- include standard navigation (drupal) -->
<div>
<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="Navigation elements." id="navigation">
<tr>
<td colspan="2">
<%@ include file="/jsprsscron/catholicvol_menus.htm" %>
</td></tr></table>



<!-- End Menus -->
</div>


<% } %>
<!-- end file home_navigation.inc -->
