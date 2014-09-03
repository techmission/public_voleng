<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Equipping & Managing Volunteers</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent"><div id="pagebanner">

<span style="float: left;">Training</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; training</div> 
</div>

       
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" style="padding: 15px; background:#7693DC url(http://www.christianvolunteering.org/imgs/int_splash_patt.gif) repeat-x"><h1>
	Volunteer Management Training and Volunteer Training</h1>ChristianVolunteering.org provides free training and resources for 
	organizations and volunteers alike.</td>
    <td><img src="<%=aszPortal%>/imgs/pic/book.jpg" title="Christian volunteer training and church volunteer recruitment"/></td>
  </tr>
</table>
		<div id="body">	 
	 <table class="searchtoolfull" cellspacing="0" cellpadding="2" border="0" width="501" height="1106">
        <!-- MSTableType="layout" -->
		<tr>
			 <td valign="top" colspan="3" height="39">
			<h1>Organizations </h1></td>
			
				</tr>
		<tr height="200" valign="top">
			<td height="200"valign="top"><h3>Volunteer Management Training for Urban Ministries and Churches </h3>
					<ul><li><a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Ministries and Churches">Webcast</a> 
					<a href="<%=aszPortal%>/files/Volunteer_Management_online_version.ppt" target="_new" title="Volunteer Management Training">Powerpoint</a> </li> 
					
					<li><p><a href="<%=aszPortal%>/files/Templates_Manual.doc" target="_new" title="Collection of templates for Volunteer Management">
					Christian Volunteer Management Manual (contains templates and resources)</a></p></li>
					
					<li>Volunteer Management <a href="<%=aszPortal%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles,</a>&nbsp<a href="<%=aszPortal%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
					Links,</a> and <a href="<%=aszPortal%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a></li></ul></td>
				<td height="200" valign="top"><h3>Full Urban Ministry and Church Service Volunteer Orientation</h3>
					<ul><li><a href="<%=aszPortal%>/files/Volunteer_Orientation/Presentation_Files/index.html" title="Full Urban Ministry and Church Service Volunteer Orientation">Webcast</a> 
					<a href="<%=aszPortal%>/files/vol_orientation_CCDA.ppt" target="_new" title="Customizable Volunteer Orientation for Urban Ministries, churches, and volunteer managers">Customizable Powerpoint</a></li></ul> 
				</td>
				</tr>
				<tr height="100"><td height="100"><h3>Emmanuel Gospel Center's CityServe Standards of Excellence</h3>
					<ul><li>for Volunteers in Urban Ministries and Churches: 
					<a href="<%=aszPortal%>/volunteerstandards.jsp" title="Standards of Excellence and Volunteer Training for Volunteers in Urban Ministries and Churches">
					View</a>&nbsp<a href="http://www.aes-egc.org/cityserve/Standards_of_Excellence_vol.pdf" title="Standards of Excellence and Volunteer Training for Volunteers in Urban Ministries and Churches"target="_new">Download PDF</a></li>

					<li><p>for Host Organizations: <a href="<%=aszPortal%>/organizationstandards.jsp">View</a>&nbsp
					<a href="http://www.aes-egc.org/CityServe/Standards%20of%20Excellence%20host.pdf"> Download PDF</a></li></ul></td></tr>
					
		<tr>
			<td height="30"><h1>Volunteers</h1></td>			</tr>
		<tr height="50">
			<td height="50">Urban Ministry and Church Volunteer Orientation
				<ul>
					<li><p><a href="<%=aszPortal%>/files/Volunteer_Orientation/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Ministries and Churches">Webcast</a>
					<a href="<%=aszPortal%>/files/vol_orientation_online.ppt" title="Urban Service and Church Volunteer Volunteer Orientation Powerpoint">Powerpoint</a></p></li></td>
		</tr>
		<tr height="50">
			<td height="50">
					<p><h3>Emmanuel Gospel Center's CityServe Standards of Excellence</h3></p> <br>
					<ul><li><p><a href="<%=aszPortal%>/register.do?method=showCityServeTrain2" title="Standards of Excellence for Volunteer Management in Urban Ministries and Churches">
					for Leading Volunteers in Urban Ministries and Churches</a> (pdf)</p></li></ul>
</td></tr>
		
		<tr height="30">
			<td height="30" width="285"><a href="<%=aszPortal%>/register.do?method=showBookRecommendations" title="Book Recommendations">Book Recommendations, </a>
				&nbsp
				<a href="<%=aszPortal%>/stmresources.jsp" title="Short-Term Missions Articles and Links">Short-Term Missions Training, </a>
				&nbsp
				<a href="<%=aszPortal%>/devotionals.jsp" title="Christian Devotionals on volunteering, service, volunteer management, urban ministry">Devotionals on Service,</a>
				&nbsp
				<a href="http://www.techmission.org/training/conference_materials.php" title="Technology Conference Materials">Technology Conference Materials, </a>
				&nbsp
				<a href="http://www.urbanministry.org" title="Urban Ministry">Urban Ministry, </a>
				&nbsp
				<a href="http://www.safefamilies.org/presentations.php" title="Online Safety">Online Safety</a></td>
		</tr>
				
      </table>			
			
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->