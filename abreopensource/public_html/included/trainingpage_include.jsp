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

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Training</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Training</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; training</div> 
</div>
<% } %>

       
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" style="padding: 15px; background:#7693DC url(/imgs/int_splash_patt.gif) repeat-x"><h1>
	Volunteer Management Training and Volunteer Training</h1>ChristianVolunteering.org provides free training and resources for 
	organizations and volunteers alike.</td>
    <td><img src="/imgs/pic/book.jpg" title="Christian volunteer training and church volunteer recruitment"/></td>
  </tr>
</table>
		<div id="body">	 
	 <table class="searchtoolfull" cellspacing="0" cellpadding="2" border="0">
        <tbody>
        
			<tr>
			 <td valign="top" colspan="2">
			<h1>Organizations </h1>
			<h3>Volunteer Management Training for <br>Urban Ministries and Churches </h3><br>
					<ul><li><a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Ministries and Churches">Webcast</a> 
					<a href="http://www.christianvolunteering.org/files/Volunteer_Management_online_version.ppt" target="_new" title="Volunteer Management Training">Powerpoint</a> </li> 
					
					<li><p><a href="http://www.christianvolunteering.org/files/Templates_Manual.doc" target="_new" title="Collection of templates for Volunteer Management">
					Christian Volunteer Management Manual (contains templates and resources)</a></p></li>
					
					<li>Volunteer Management <a href="<%=aszPortal%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles,</a>&nbsp<a href="<%=aszPortal%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
					Links,</a> and <a href="<%=aszPortal%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a></li></ul>
				<p><h3>Full Urban Ministry and Church Service Volunteer Orientation</h3></p><br> 
					<ul><li><p><a href="http://www.christianvolunteering.org/files/Volunteer_Orientation_Faith_2007/Presentation_Files/index.html" title="Full Urban Ministry and Church Service Volunteer Orientation">Webcast</a> 
					<a href="http://www.christianvolunteering.org/files/vol_orientation_CCDA.ppt" target="_new" title="Customizable Volunteer Orientation for Urban Ministries, churches, and volunteer managers">Customizable Powerpoint</a></p></li></ul> 
				
				</td></tr>			

				<tr><td><h1>Volunteers</h1> 
				<h3><p>Urban Ministry and Church Volunteer Orientation</p></h3>
				<ul>
					<li><p>Training for&nbsp; Christian Volunteers on How to Find a Volunteer Placement and Serve Well<a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Ministries and Churches"> Webcast</a>
					<a href="http://www.christianvolunteering.org/files/vol_orientation_online.ppt" title="Urban Service and Church Volunteer Volunteer Orientation Powerpoint">Powerpoint</a></p></li>
					<li><p>Training for Christian Volunteers on How to Serve Well<a href="http://www.christianvolunteering.org/files/Volunteer_Orientation_Faith_2007/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Ministries and Churches"> Webcast</a>
					<a href="http://www.christianvolunteering.org/files/vol_orientation_2007_faith_NoAudio.ppt" title="Urban Service and Church Volunteer Volunteer Orientation Powerpoint">Powerpoint</a></p></li></ul>

					<p><a href="<%=aszPortal%>/spiritualgifts.jsp">Spiritual Gifts</a></p>
				</td></tr>		

				<tr><td><h2><a href="<%=aszPortal%>/tmctraining.jsp">TechMission Corps Training</a></h2>
				</td></tr>	

				<tr><td>
	 			<a href="<%=aszPortal%>/register.do?method=showBookRecommendations" title="Book Recommendations">Book Recommendations, </a>
				&nbsp
				<a href="<%=aszPortal%>/stmresources.jsp" title="Short-Term Missions Articles and Links">Short-Term Missions Training, </a>
				&nbsp
				<a href="<%=aszPortal%>/virtualresources.jsp" title="Virtual Volunteering Resources, Articles, and Links">Virtual Volunteering Resources, </a>
				&nbsp
				<a href="<%=aszPortal%>/devotionals.jsp" title="Christian Devotionals on volunteering, service, volunteer management, urban ministry">Devotionals on Service,</a>
				&nbsp
				<a href="<%=aszPortal%>/calling.jsp" title="Christian Calling and Vocation">Christian Calling and Vocation, </a>
				&nbsp
				<a href="http://www.techmission.org/training/conference_materials.php" title="Technology Conference Materials">Technology Conference Materials, </a>
				&nbsp
				<a href="http://www.urbanministry.org" title="Urban Ministry">Urban Ministry, </a>
				&nbsp
				<a href="http://www.safefamilies.org/presentations.php" title="Online Safety">Online Safety</a>
				
				</p></p></p></p>
          </td>
        </tr>
      </table>			
			
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->