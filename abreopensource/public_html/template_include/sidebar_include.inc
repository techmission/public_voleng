<!-- start sidebar.inc -->

<div id="sidebar">
<div id="sidebar_content">
<p></p>
   <ul id="navbullet">
        </ul>
        <h5>VOLUNTEERS</h5>
    <ul id="navbullet">
        <li><a href="<%=aszPortal%>/login.jsp">Login</a></li>
        <li><a href="<%=aszPortal%>/register.do?method=logout">Logout</a></li>
        <li><a href="<%=aszPortal%>/individualregistration.jsp" title="Register for church volunteer opportunities">Volunteer Registration</a></li>
		<%//li><a href="<%=request.getContextPath()%/search.jsp" title="Search for Christian Volunteer Service Opportunities">Search to Volunteer</a></li%>
     		<li><a href="<%=aszPortal%>/advancedsearch.jsp" title="Advanced Search for Church Volunteer Opportunities">Advanced Search</a></li>
             <li><a href="<%=aszPortal%>/register.do?method=showIndEditProfile1" title="Update Volunteering and Service Account">Edit Volunteer Account</a></li>
        <%//li><a href="<%=request.getContextPath()%/register.do?method=showIndAcctSum1" title="Manage Church Volunteering and Service Account">Manage Volunteering</a></li%>
        <li><a href="http://www.urbanministry.org" title="Urban Ministry Training">UrbanMinistry.org Training</a></li>
              </ul>
      </ul>
  <img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="129" height="1" vspace="10">
   <ul>
<h5>ORGANIZATIONS</h5>
    <ul id="navbullet">
    <li><a href="<%=aszPortal%>/login.jsp">Login</a></li>
        <li><a href="<%=aszPortal%>/register.do?method=logout">Logout</a></li>
	      <li><a href="<%=aszPortal%>/recruitvolunteers.jsp" title="Register to Recruit Christian Volunteers">Start Volunteer Recruitment</a></li>
          <li><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" title="Create Volunteer Recruitment Account">Create Recruitment Account</a></li>
        <li><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" title="Manage Organizational Account">Manage Recruitment Account</a></li>
        <li><a href="http://www.urbanministry.org/member-benefits" title="Premium Membership">Premium Membership</a></li>
	          </ul>
  </ul>
  <img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="129" height="1" vspace="10">
   
   
   </ul>
  </div>
<% if(aszHostID.equalsIgnoreCase("voleng1")){ %>
<iframe id="content" name="content" src="http://www.christianvolunteering.org/urbanministry/chrisvol_hooks/cv_subscriptions_sidebar.php" frameborder="0" width="135" scrolling="no" ></iframe>
<% } %>
<% if(aszHostID.equalsIgnoreCase("volengurbmin")){ %>
<img src="http://volunteer.urbanministry.org/imgs_um/sidebar_bottom.gif" width="151" height="23" />
<% }else{ %>
<img src="http://www.christianvolunteering.org/imgs/sidebar_bottom.gif" width="151" height="23" />
<% } %>
</div>
<!-- end sidebar.inc -->
