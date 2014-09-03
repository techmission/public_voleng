<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>
<title>Christian Volunteer Network: Sitemap</title>
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
	  <span id="title">Contact Us</span>
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
<span style="float: left;">Sitemap </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; sitemap </div>

</div>
<% } %>



 <div id="body">

 <head>

                    <meta name="keywords" content="community, computer, urban, ministry, training, technology, vulnerable communities, vunerable lives, at risk youth, low income, poor, online safety, protecting kids online, urban ministry internship, paid internship">

                    <meta name="description" content="Explore our wide range of resources in Technology and ministry">

                </head>


<div align="left">

			            	<blockquote>

<p><a href="<%=aszPortal%>/" title="Begin here to recruit urban ministry volunteers and find Christian Volunteer and Church Volunteer opportunities">Home Page</a></p>

					<h5>VOLUNTEERS</h5>

					<ul id="navbullet">

        <li><a href="<%=aszPortal%>/login.jsp">Login</a></li>

        <li><a href="<%=aszPortal%>/register.do?method=logout">Logout</a></li>

        <li><a href="<%=aszPortal%>/individualregistration.jsp">Create Account</a></li>

		<li><a href="<%=aszPortal%>/search.jsp">Search Opportunities</a></li>     

		<li><a href="<%=aszPortal%>/advancedsearch.jsp">Advanced Search</a></li>     

        <li><a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit Account</a></li>

        <li><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">Manage Account</a></li>

		<li><a href="<%=aszPortal%>/volunteerlistings.jsp">Show All Volunteer Listings</a></li>      

					<li><a href="<%=aszPortal%>/virtualvolunteer.jsp">Virtual Volunteer Opportunities</a></li>

					<li><a href="<%=aszPortal%>/shorttermmissions.jsp">Short Term Urban Mission Trips</a></li>

<% if(aszHostID.equalsIgnoreCase("voleng1")) { %>

					<li><b>Regional Volunteering</b></li>

					<ul>

						<li>

						<a href="http://indy.christianvolunteering.org" title="Indianapolis Volunteering in Urban Ministry and Short Term Missions">Indianapolis Volunteering in Urban Ministry and Short Term Missions</a></li>

						<li>

						<a href="http://newengland.christianvolunteering.org" title="Boston and New England Volunteering in Urban Ministry and Short Term Missions">Boston and New England Volunteering in Urban Ministry and Short Term Missions</a></li>

						<li>

						<a href="http://twincities.christianvolunteering.org" title="Twin Cities Volunteering in Urban Ministry and Short Term Missions">Twin Cities Volunteering in Urban Ministry and Short Term Missions</a></li>

					</ul>



<!--

					<li><a href="http://twincities.christianvolunteering.org" title"Twin Cities Area Volunteering in Urban Ministry and Short Term Missions">

					Twin Cities Area Volunteering in Urban Ministry and Short Term Missions</a></li>

					<li><a href="http://indy.christianvolunteering.org" title"Indianapolis Area Volunteering in Urban Ministry and Short Term Missions">

					Indianapolis Area Volunteering in Urban Ministry and Short Term Missions</a></li>

-->

<% } %>

					</ul><hr>

					

					

					<h5>CHRISTIAN VOLUNTEER RESOURCES</h5>

					<ul id="navbullet">



        <li><a href="<%=aszPortal%>/individualregistration.jsp" title="Christian Volunteer and Church Volunteers register here to begin your urban ministry service">

        Christian Volunteer and Church Volunteer Registration</a></li>

		<li><a href="<%=aszPortal%>/search.jsp" title="Search for Christian Volunteer,Church Volunteer, and Urban Ministry Service Opportunities">

		Search to Volunteer for Urban Ministry and Christian Volunteer Opportunities</a></li>     

		<li><a href="<%=aszPortal%>/advancedsearch.jsp" title="Advanced Search for Volunteering in Urban Ministry and Churches">

		Advanced Search for Christian Volunteer, Short Term Missions, and Virtual Volunteering</a></li>

		<li><a href= "<%=aszPortal%>/shorttermmissions.jsp" title="Short Term Missions in Urban Ministry Search">

		Short Term Missions in Urban Ministry Search</a></li>

			

		<li><a href="http://www.christianvolunteering.org/virtualvolunteer.jsp" title"Virtual Volunteering and Online Volunteering in Urban Ministry and Service">

		Virtual Volunteering in Urban Ministry and Service</a></li>    

        <li><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" title="Manage Volunteer and Volunteer Management Account">

        Manage Volunteer Account</a></li>



					</ul><hr>

					

					

					<h5>ORGANIZATIONS</h5>

					<ul>

    <li><a href="<%=aszPortal%>/login.jsp">Login</a></li>

        <li><a href="<%=aszPortal%>/register.do?method=logout">Logout</a></li>



	      <li><a href="<%=aszPortal%>/recruitvolunteers.jsp" title="Start Volunteer Recruitment for Urban Ministry, Short Term Missions, and Church Volunteers">

	      Start Volunteer Recruitment</a></li>

          <li><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" title="Create Volunteer Recruitment Account for Urban Miinistry Service Volunteers">

          Create Volunteer Recruitment Account for Urban Ministry Service Volunteers</a></li>

        <li><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" title="Manage Volunteer Recruitment Account for Urban Miinistry Service Volunteers">

        Manage Volunteer Recruitment Account for Urban Ministry Service Volunteers</a></li>

        

        

					</ul>

					<hr>

					<h5>TRAINING</h5><br>

					<H4>General</H4><br>

					<ul>

					<li><a href="http://www.urbanministry.org/taxonomy/term/92,120" title="Books on urban ministry and church service">Urban Ministry Book Recommendations</a></li>

                    <li><a href="http://www.urbanministry.org/conference-training-materials-topic" title="Urban Ministry Technology Conference Materials">Urban Ministry Technology Conference Materials</a></li>

                    <li><a href="http://www.urbanministry.org" title="Urban Ministry and Church Service Training">Urban Ministry and Church Service Training</a></li>

					<li><a href="http://www.safefamilies.org/presentations.php">Online Safety</a></li>

					<li><a href="http://www.urbanministry.org/wiki/professional-technology-and-ministry-organizations" title="Technology Links">

			        Professional, Technology, and Ministry Organizations</a></li>

					<li><a href="http://www.christianvolunteering.org/technology.jsp" title="Christians in Technology">Technology</a></li>




					</ul>

					<H4>For Organizations</H4><br>

					<ul>

					<li><a href="http://www.urbanministry.org/wiki/christian-volunteer-management-articles">Christian Volunteer Management Articles</a></li>

					<li><a href="http://www.urbanministry.org/wiki/">Christian Volunteer Management Links</a></li>

					<li><a href="http://www.urbanministry.org/wiki/volunteer-management-links">Christian Volunteer Management Presentations</a></li>

					<li><a href="http://www.urbanministry.org/wiki/christian-staff-recruitment-websites">Staff Recruitment</a></li>

					<li><a href="http://www.urbanministry.org/wiki/work-study">Work Study</a></li> 


					<li><a href="http://www.urbanministry.org/volunteer-management-training-urban-ministries-churches" title="Volunteer Management Training Webcast for Urban Ministries and Churches">Volunteer Management Training Webcast for Urban Ministries and Churches</a> </li>

					<!--<li><a href="http://www.christianvolunteering.org/files/Volunteer_Management_online_version.ppt" target="_new" title="Volunteer Management Training for Urban Ministries and Churches">

					Volunteer Management Training Powerpoint for Urban Ministries and Churches</a></li> ead 10.9.07 - elim redundancy (PPt linked from webcast) -->

					<li></a><a href="http://www.christianvolunteering.org/files/Templates_Manual.doc" target="_new" title="Collection of templates for Volunteer Management">

					Christian Volunteer Management Manual (contains templates and resources)</li>

					<li></a><a href="http://www.christianvolunteering.org/files/other_sources.doc" target="_new">

					List of other resources for Volunteer Management in Urban Ministry and Church Service</a></li> <!-- keep these two where they are for now? ead 10.9.07 -->

					<li><a href="http://www.urbanministry.org/full-urban-ministry-and-church-service-volunteer-orientation-training-christian-volunteers-how-serve" title="Full Urban Ministry and Church Service Volunteer Orientation">Full Urban Ministry and Church Service Volunteer Orientation</a></li> 

					<li><a href="http://www.christianvolunteering.org/files/vol_orientation_customizable.ppt" target="_new" title="Customizable Volunteer Orientation for Urban Ministries, churches, and volunteer managers">Customizable Christian Volunteer Orientation </a></li>

					<li><a href="http://www.urbanministry.org/wiki/volunteer-management-presentation-webcasts">TechMission Corps Training</a></li>

					<li><a href="http://www.urbanministry.org/wiki/standards-excellence-host-organizations" target="_new" title="Standards of Excellence for Volunteer Management in Urban Ministries and Churches">

					Standards of Excellence for Organizations in Urban Ministries and Churches</a></li>

					</ul>

					<H4>For Volunteers</H4><br>

					<ul>

			        <li><a href="http://www.urbanministry.org/wiki/articles-and-training-virtual-volunteering">Resources for Virtual Volunteers</a></li>

			        <li><a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling">Christian Calling and Vocation</a></li>

			        <li><a href="http://www.urbanministry.org/spiritual_gifts">Spiritual Gifts</a></li>

			        <li><a href="http://www.urbanministry.org/wiki/short-term-mission-resources">Short Term Missions Resources</a></li>

			        <li><a href="http://www.urbanministry.org/wiki/devotionals">Devotionals for Volunteers</a></li>

					<!-- <li><a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/start.html" title="Urban Service and Church Volunteer Orientation Webcast">Urban Service and Church Volunteer Orientation Webcast</a></li> ead 10.9.07 this seems redundant -->

					<li><a href="http://www.urbanministry.org/training-christian-volunteers-how-find-volunteer-placement-and-serve-well">Training for Christian Volunteers on How to Find a Volunteer Placement and Serve Well: Webcast</a></li>	

					<li><a href="http://www.urbanministry.org/wiki/standards-excellence-volunteers" target="_new" title="Standards of Excellence and Volunteer Training for Volunteers in Urban Ministries and Churches">

					Standards of Excellence for Volunteers in Urban Ministries and Churches</a></li>	

					<li><a href="http://www.urbanministry.org/wiki/standards-excellence-volunteer-group-leaders" target="_new" title="Standards of Excellence for Volunteer Management in Urban Ministries and Churches">

					Standards of Excellence for Leading Volunteers in Urban Ministries and Churches</a></li>

					</ul>

					<hr>

<% if (aszHostID.equalsIgnoreCase("voleng1")) { %>					

					<h5>PARTNER SITES</h5><br>

				

					<p><a href="http://agrm.christianvolunteering.org">The Association of Gospel Rescue Mission's (AGRM) ChristianVolunteering Site</a><br>

					<a href="http://ccda.christianvolunteering.org">The Christian Community Development Association's (CCDA) ChristianVolunteering Site</a><br>					

					<a href="http://hlic.christianvolunteering.org">Here's Life Inner City's (HLIC) ChristianVolunteering Site</a></p><br>

					<hr>

<% } %>					

					<h5>ABOUT</h5>

					<ul><li>

					<a href="<%=aszPortal%>/about.jsp" title="About Website with Christian Volunteer, Church volunteers, Urban Ministry Service, and Short Term Missions Opportunities">About ChristianVolunteering.org</a></li>

					<li><a href="<%=aszPortal%>/biblical.jsp">Why ChristianVolunteering.org</a></li>

					<li><a href="http://www.urbanministry.org/about-techmission-inc">About TechMission</a></li>

					<li><a href="<%=aszPortal%>/FAQs.jsp">Frequently Asked Questions</a></li>					

					<li><a href="http://www.urbanministry.org/staff">Staff </a></li>

                    <li><a href="http://www.urbanministry.org/board">Board</a></li>

                    </ul>

                 	

                 	

					<a href="http://www.techmission.org/about/privacypolicy.php">Privacy Policy</a><br>

					<a href="https://secure.groundspring.org/dn/index.php?aid=13769">Donate</a><br>

					<a href="<%=aszPortal%>/contact.jsp">Contact Us</a><p>

					<hr>

					

					

															

					<h5><a href="<%=aszPortal%>/volunteerlistings.jsp" color="blue" title="Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS">SEE ALL Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS</a></h5><br>

					<h5><a href="<%=aszPortal%>/organizationlistings.jsp" color="blue" title="Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS">SEE ALL Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service ORGANIZATION LISTINGS</a></h5>

					

					

					

					

					</blockquote>

</div>

            

            </div>

</div>

<%@ include file="/template/sidebar.inc" %>

<!-- end sidebar information -->

<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->