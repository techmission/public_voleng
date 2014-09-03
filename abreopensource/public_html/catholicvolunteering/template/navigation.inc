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
<div id="navigation">
<% out.println("<!-- the current page path is: "+ aszPath + "-->"); %>

<%@ include file="/jsprsscron/chrisvol_new_menu.htm" %>

<!-- changing comment -->


<!-- End Menus -->
</div>


<% } %>
<!-- end file home_navigation.inc -->
