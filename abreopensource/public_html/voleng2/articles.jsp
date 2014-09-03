<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Articles for Equipping & Managing Volunteers and for Volunteers</title>

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
	  <span id="title">Volunteer Management Articles</span>
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

<span style="float: left;">Volunteer Management Articles</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt;  <a href="<%=aszPortal%>/training.jsp">training</a> &gt; articles</div> 
</div>
<% } %>
    
		<div id="body">	 
<br>		
<h1><a href="#online">Online / Tech Volunteers</a>, <a href="#planning">Planning</a>, <a href="#recruitment">Recruitment</a>,
 <a href="#management">Management</a>, <a href="#recognition">Recognition</a>, <a href="#legal">Legal Issues</a></h1>
	
<p><h3><a name="online">Online / Tech Volunteers</a></h3></p><br>
<ul><li><p><a href="http://www.techsoup.org/howto/articlepage.cfm?ArticleId=357&topicid=7">Structuring a successful volunteer project: getting the most from your technical volunteer.</a> </p></li>
<li><p><a href="http://www.techsoup.org/learningcenter/volunteers/page5427.cfm">Virtual Volunteering: Planning and Benefits</a> </p></li> 
<li><p><a href="http://www.charityvillage.com/cv/research/rvol26.html">Are 'Virtual Volunteers' the wave of the future?</a> </p></li> 
<li><p><a href="http://www.techsoup.org/binaries/Files/TechVolMan2001v1.2.pdf">Working with Technical Volunteers</a> </p></li>
<li><p><a href="http://www.serviceleader.org/old/vv/">The Virtual Volunteering Project</a></p></li>
<li><p><a href="http://www.coyotecommunications.com/volunteer/ovmyths.html">Myths About Virtual Volunteers</a></p></li>
<li><p><a href="http://www.coyotecommunications.com/volunteer/ovresearch.html">Virtual Volunteering Statistics</a></p></li>

</ul> 

<p><h3><a name="planning">Planning</a></h3></p><br>
<ul>
<li><p><a href="http://www.techsoup.org/learningcenter/volunteers/page5098.cfm">Write a Volunteer Job Description: What to include in the all-important volunteer job description.</a></p> </li>
<li><p><a href="http://www.volunteer.ca/volcan/eng/iwork/vol-managment.php?display=3,0,2">Volunteer Management Theory.</a> </p></li>
<li><p><a href="http://www.casanet.org/program-management/volunteer-manage/worksht.htm">Staff worksheet: planning a volunteer position.</a></p></li>
<li><p><a href="http://www.charityvillage.com/cv/research/rvol42.html">Spring Cleaning: Revamp! Refurbish! and Renew! Your Volunteer Program</a></p></li>
<li><p><a href="http://www.casanet.org/program-management/volunteer-manage/essenvol.htm#Supervision">Essential Volunteer Management.</a> </p></li></ul>

<p><h3><a name="recruitment">Recruitment</a></h3></p><br>
<ul><li><p><a href="http://www.techsoup.org/learningcenter/volunteers/page6010.cfm">Shared Widsom: Recruiting and Managing Volunteers.</a></p></li>
<li><p><a href="http://www.techsoup.org/howto/articlepage.cfm?ArticleId=398&topicid=7">Getting help that is helpful: how to get the right volunteers.</a></p></li>
<li><p><a href="http://www.pastors.com/articles/MotivatingVolunteers.asp">Motivating Volunteers in Your Church: A Twelve-Point Checklist.</a></p></li>
</ul> 

<p><h3><a name="management">Management</a></h3></p><br>
<ul>
<li><p><a href="http://www.techsoup.org/learningcenter/volunteers/page5108.cfm">Volunteer Management Mistakes to Avoid</a></p></li>
<li><p><a href="http://www.pastors.com/RWMT/?id=244&artid=9099&expand=1">When you have to remove a volunteer</a></p></li></ul>

<p><h3><a name="recognition">Recognition</a></h3></p><br>
<ul><li><p><a href="http://www.charityvillage.com/cv/archive/acov/acov05/acov0513.html">Volunteer Recognition: Appreciating volunteers 52 weeks of the year.</a> </p></li>
<li><p><a href="http://www.charityvillage.com/cv/research/rvol38.html">Supporting, recognizing and challenging volunteers</a> </p></li>
<li><p><a href="http://www.charityvillage.com/cv/research/rvol33.html">Methods of screening potential volunteers</a> </p></li>
<li><p><a href="http://www.charityvillage.com/cv/research/rvol23.html">Matching the "Thank You" to the volunteer.</a></p></li>
<li><p><a href="http://www.pastors.com/article.asp?ArtID=9769">How to speak your volunteers' love language.</a></p></li>
<li><p><a href="http://www.presidentialserviceawards.gov/">President's Volunteer Service Award</a></p></li>
<li><p><a href="http://www.paperdirect.com">Paper Direct</a> Buy certificates to thank volunteers</p></li>

</ul>

<p><h3><a name="legal">Legal Issues</a></h3></p><br>
<ul><li><p><a href="http://www.techsoup.org/howto/articlepage.cfm?ArticleId=201&topicid=7">Volunteers and Legal Issues: Limiting Your Liability.</a> </p></li></ul>
			
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->