<!-- start sidebar.inc -->

<div id="sidebar">
<div id="sidebar_content">
<p></p>


<!--REGISTER NOW--> 
<% if(!(aszAuthPage.equalsIgnoreCase("true"))){
	if(!(	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/login1.jsp")	||
		aszPath.equalsIgnoreCase("/createaccount.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp")	
	)){ 
%>
	<!--Register NOW-->
	  <%@ include file="/template_include/sidebar_register.inc" %>
	<hr class="sidebar_hr"> 

<%
	} 
}
%>
 

<!--MY ACCOUNT-->
  <%@ include file="/template_include/sidebar_myaccnt.inc" %>

<hr class="sidebar_hr"> 

<!--VOLUNTEERS-->
  <%@ include file="/template_include/sidebar_vol.inc" %>

<hr class="sidebar_hr"> 

<!--ORGANIZATIONS-->
  <%@ include file="/template_include/sidebar_org.inc" %>

<hr class="sidebar_hr"> 

  </div>
<% if(false==true){//aszHostID.equalsIgnoreCase("voleng1")){ %>
<iframe id="content" name="content" src="http://www.christianvolunteering.org/urbanministry/chrisvol_hooks/cv_subscriptions_sidebar.php" frameborder="0" width="135" scrolling="no" ></iframe>
<% } %>
<img src="http://www.christianvolunteering.org/imgs/sidebar_bottom.gif" width="151" height="23" />
</div>
<!-- end sidebar.inc -->
