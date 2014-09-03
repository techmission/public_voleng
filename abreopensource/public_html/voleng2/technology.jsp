<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template_include/chris_tech_header.inc" %>
<!-- end header information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>
<title>Christian Volunteer Network: Add Volunteer Opportunities to Your Church Website</title>
<% } else { %>
<% } %>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
String aszBoston = "display:none";
if ( (aszHostID.equalsIgnoreCase("volengboston")) || (aszHostID.equalsIgnoreCase("volengnewengland")) ){
	aszBoston = "display:inline";
}
%>

</head>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Welcome to Christians in Technology</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Welcome to Christians in Technology</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/churchinstructions.jsp">volunteer opportunities in technology</a></div>
</div>
<% } %>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="http://www.ChristianVolunteering.org/images/guy_with_laptop_crop.jpg" width="245" height="204" ></td>
    <td valign="top" class="intsplash" width="50%">
	<h1>Volunteer Areas</h1>
<ul>
<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=4766&postype=&searchkey1=state&Submit=Submit" target="_blank">Computer/Tech Support </a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4729&searchkey1=state&Submit=Submit" target="_blank">Computer Programmer</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&searchkey1=state&Submit=Submit" target="_blank">Web/Graphics Design</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4722&searchkey1=state&Submit=Submit" target="_blank">Architect/Civil Engineer</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=8121&searchkey1=state&Submit=Submit" target="_blank">Aviation</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&searchkey1=state&Submit=Submit" target="_blank">Computer Trainer</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4731&searchkey1=state&Submit=Submit" target="_blank">Construction</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4736&searchkey1=state&Submit=Submit" target="_blank">Electrician</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4747&searchkey1=state&Submit=Submit" target="_blank">Plumber/HVAC</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4755&searchkey1=state&Submit=Submit" target="_blank">Video Production</a></li>

<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4745&searchkey1=state&Submit=Submit" target="_blank">Scientist/Math</a></li>
</ul>
</td> 

<!--Right Column of intsplash-->   
<td valign="top" class="intsplash" width="50%">
<h1>Connect with Christians in Technology:</h1>
<ul class="nobullets">
<li><a href="http://www.facebook.com/group.php?gid=14023982213 "><img src="/imgs/find_us_on_facebook_badge75.gif" width="75" title="Find us on Facebook" alt="Facebook"></a></li> 
<li><a href="http://www.linkedin.com/e/gis/84224/2D623626A2EA"><img src="/imgs/LinkedIn_75px.gif" title="Find us on LinkedIn" alt="LinkedIn"></a></li>
<li><a href="http://www.urbanministry.org/wiki/professional-technology-and-ministry-organizations">Other Christians and <br/> Technology Organizations</a></li>
</ul>
</td>
 
  </tr>
</table>
</div>

<div id="body" align="center">
<!--IFrame insert-->
<h1 align="left">Virtual Volunteer Opportunities</H1>

<iframe id="content" style="WIDTH: 580; HEIGHT: 400;" name="content" src="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAllPaste&postype=4795&servicearea1=4766&Submit=Submit" frameborder="0" width="580" scrolling="yes" height="400"></iframe>
<!-- IFrame insert -->
</div>

</div>


<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
