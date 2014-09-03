<!-- start sidebar -->


<div id="sidebar">

<div id="sidebar_content">
<br>

<%  if(	! (
        (aszHostID.equalsIgnoreCase( "volengworldvision" )) ||
        (aszHostID.equalsIgnoreCase( "volengpartners" )) ||
	(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
	(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
	(aszHostID.equalsIgnoreCase( "volenggospelcom" ))  
)){  %>
<!--SEARCH OPPORTUNITIES-->
  <%@ include file="/template_include/sidebar_search.inc" %>

<hr class="sidebar_hr"> 
 
<% } %>

<% if(!(aszAuthPage.equalsIgnoreCase("true"))){
	if(!(	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp")	||
				aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp")	||
				aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp")	
	)){ 
		if(! (aszHostID.equalsIgnoreCase("volengurbmin")) ){
%>
	<!--Register NOW-->
	  <%@ include file="/template_include/sidebar_register.inc" %>
	<hr class="sidebar_hr"> 

<%
		}
	} 
}
%>
 
<!--MY ACCOUNT-->
  <%@ include file="/template_include/sidebar_myaccnt.inc" %>

<hr width="129" height="1"> 

<!--VOLUNTEERS-->
  <%@ include file="/template_include/sidebar_vol.inc" %>

<hr width="129" height="1"> 


<!--ORGANIZATIONS-->
  <%@ include file="/template_include/sidebar_org.inc" %>
  
<hr width="129" height="1"> 

 <br clear="all">

</div>

<% if(aszHostID.equalsIgnoreCase("volengurbmin")){ %>
<!--img src="http://volunteer.urbanministry.org/imgs_um/sidebar_bottom.gif" width="155" height="23" /-->
<% } else if(aszHostID.equalsIgnoreCase("volengpartners")){ %>
<img src="http://www.christianvolunteering.org/imgs/sidebar_bottom.gif" width="151" height="23" />
<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
<img src="http://roundtrip.christianvolunteering.org/imgs_roundtrip/bg_rightnav-end.gif" width="175" height="46" />
<% } %>


</div>

<div class="cleardiv"></div>
<!-- end sidebar -->
