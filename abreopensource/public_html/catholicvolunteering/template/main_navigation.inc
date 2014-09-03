<!-- start file template/main_navigation.inc -->


<!-- Begin Menus (div#navigation) -->
<div id="navigation">
<% out.println("<!-- the current page path is: "+ aszPath + " -->"); %>

<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
<!-- Include file /jsprsscron/ivol_new_menu.htm -->
<%@ include file="/jsprsscron/ivol_new_menu.htm" %>
<% }else{ %>
<!-- Include file /jsprsscron/chrisvol_new_menu.htm -->
<%@ include file="/jsprsscron/chrisvol_new_menu.htm" %>
<% } %>
</div>
<!-- End Menus (/div#navigation) -->

<!-- end file template/main_navigation.inc -->
