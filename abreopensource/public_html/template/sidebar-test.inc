<!-- start sidebar.inc   (/template/sidebar-test.inc)-->

<div id="sidebar">
<div id="sidebar_content">
<p></p>

<!--*******************sidebar_search.inc************************************-->
<!--SEARCH OPPORTUNITIES-->
  <!-- %@ include file="/template_include/sidebar_search.inc" %-->

 <h5>Search Opportunities </h5>
<form name="searchForm3" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchTop">
<input style="width:55px;" type="text" value="zip code" name="postalcode" maxlength="5" onFocus="clearText(this)" />
 <input name="submit" type="submit" id="submit" value="GO"/> 

</form>
<!--*******************************************************-->


<hr class="sidebar_hr"> 

<!--********************sidebar_myaccnt.inc***********************************-->
<!--MY ACCOUNT-->
  <!-- %@ include file="/template_include/sidebar_myaccnt.inc" %-->

 <h5><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">MY ACCOUNT</a></h5>
  <ul class="navbullet">
	<li><a href="<%=request.getContextPath()%>/individualregistration.jsp">Create Account</a></li>
	<li><a href=<%=request.getContextPath()%>/login.jsp">Login</a></li>
	<li><a href=<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">Manage Account</a></li>
	<li><a href=http://www.urbanministry.org/user/password">Reset Password</a></li>
  	<!-- <li><a href=<%=request.getContextPath()%>/manageorg.jsp">Manage Organization</a></li>
    <li><a href=<%=request.getContextPath()%>/managepersonal.jsp">Manage Personal Account</a></li> -->
	</ul>
<!--*******************************************************-->

<hr class="sidebar_hr">   
  
   
   
<!--*********************sidebar_vol.inc**********************************-->
<!--VOLUNTEERS-->
  <!-- %@ include file="/template_include/sidebar_vol.inc" %-->
   
 <h5><a href=<%=request.getContextPath()%>/individualregistration.jsp" title="Register to Volunteer">VOLUNTEERS</a></h5>
        <ul class="navbullet">
		<li><a href=<%=request.getContextPath()%>/advancedsearch.jsp">Search Opportunities</a></li>
        <li><a href=http://www.urbanministry.org/volunteer/browse-directory">Browse Opportunities</a></li>
        <li><a href=<%=request.getContextPath()%>/shorttermmissions.jsp" >Short Term Missions</a></li>      
 </ul>
<!--*******************************************************-->

 
 <hr class="sidebar_hr"> 


<!--**************************sidebar_org.inc*****************************-->
<!--ORGANIZATIONS-->
  <!-- %@ include file="/template_include/sidebar_org.inc" %-->

<h5><a href=<%=request.getContextPath()%>/recruitvolunteers.jsp" title="Recruit Volunteers">ORGANIZATIONS</a></h5>
   <ul class="navbullet">
   <li><a href=<%=request.getContextPath()%>/recruitvolunteers.jsp ">Recruit Volunteers</a></li>
    <li><a href=http://www.urbanministry.org/node/add/job">Recruit Staff</a></li>
	<li><a href=http://www.urbanministry.org/grants">Get Grants</a></li>
    <li><a href=http://www.urbanministry.org/member-benefits">Nonprofit Discounts</a></li>
    <li><a href=http://www.cityvision.edu">Nonprofit College Courses</a></li>
 </ul>       
  
<h5><a href=http://www.churchvolunteering.org" title="Church Volunteering">CHURCHES</a></h5> 
  <br>
<!--*******************************************************-->


 <hr class="sidebar_hr"> 
  
  </div>
<% if(aszHostID.equalsIgnoreCase("voleng1")){ %>
<iframe id="content" name="content" src="http://www.christianvolunteering.org/urbanministry/chrisvol_hooks/cv_subscriptions_sidebar.php" frameborder="0" width="135" scrolling="no" ></iframe>
<% } %>
<img src="http://www.christianvolunteering.org/imgs/sidebar_bottom.gif" width="151" height="23" />
</div>

<!-- end sidebar.inc -->
