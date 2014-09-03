
<div id="banner">

<div id="logo">

<a href="<%=request.getContextPath()%>/" >
<% if(aszHostID.equalsIgnoreCase( "volengcatholic" ) || aszLandingPage.equalsIgnoreCase( "catholic" )){ %>
	<img src="/imgs/catholic_volunteering_org.jpg" alt="Catholic Volunteer and Short Term Missions Search" width="417" height="85" border="0"/>
<% }else{ %>
	<img src="/imgs/christianvolunteering-10000.png" alt="ChristianVolunteering.org" width="347" height="51" border="0"/>
<% } %>
</a>

</div>

<%@ include file="/template_include/login_form.inc" %>

<div class="image-advertisement" id="ad-507245"><a href="http://www.cityvision.edu/urbanministryinternships"><img src="http://www.urbanministry.org/files/internship-ad-2.gif" alt="Learn more about City Vision College internships" title="Learn more about City Vision College internships" width="270" height="65" style="float: right; margin-right: 13px; margin-top: 5px;"></a></div>

<div class="cleardiv"></div>

</div>

<% } %>
