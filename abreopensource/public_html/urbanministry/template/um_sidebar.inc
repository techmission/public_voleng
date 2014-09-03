<!-- start sidebar.inc -->

<div id="sidebar">

<div id="sidebar_content">
<br>

<img src="http://christianvolunteering.org/imgs/search_boxtitle.gif" alt="Search Opportunities" width="121" height="16" />

<form name="searchForm2" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchTop">
Postal Code <input style="width:40px; height:17px" type="text" name="postalcode" maxlength="5" />
 <input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/search_arrow.gif" alt="go" width="16" height="16" border="0" align="top" /></form>
</form>
<br>
<hr width="129" height="1">
<br> 



<h5>VOLUNTEERS</h5>
    <ul id="navbullet">
        <li><a href="<%=request.getContextPath()%>/login.jsp">Login</a></li>        <li><a href="<%=request.getContextPath()%>/register.do?method=logout">Logout</a></li>        <li><a href="<%=request.getContextPath()%>/individualregistration.jsp" title="Register for church volunteer opportunities">Volunteer Registration</a></li>		<li><a href="<%=request.getContextPath()%>/search.jsp" title="Search for Christian Volunteer Service Opportunities">Search to Volunteer</a></li>     		<li><a href="<%=request.getContextPath()%>/advancedsearch.jsp" title="Advanced Search for Church Volunteer Opportunities">Advanced Search</a></li>             <li><a href="<%=request.getContextPath()%>/register.do?method=showIndEditProfile1" title="Update Volunteering and Service Account">Edit Volunteer Account</a></li>        <li><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" title="Manage Church Volunteering and Service Account">Manage Volunteering</a></li>      
        </ul>

  <img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="110" height="1" vspace="10"> 

<h5>ORGANIZATIONS</h5>
    <ul id="navbullet">
    <li><a href="<%=request.getContextPath()%>/login.jsp">Login</a></li>        <li><a href="<%=request.getContextPath()%>/register.do?method=logout">Logout</a></li>	      <li><a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" title="Register to Recruit Christian Volunteers">Start Volunteer Recruitment</a></li>          <li><a href="<%=request.getContextPath()%>/org.do?method=showCreateOrgStep1" title="Create Volunteer Recruitment Account">Create Recruitment Account</a></li>        <li><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" title="Manage Organizational Account">Manage Recruitment Account</a></li>
	          </ul>

  <img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="110" height="1" vspace="10"> 


</div></div>

<div class="cleardiv"></div>

<!-- end sidebar.inc -->

