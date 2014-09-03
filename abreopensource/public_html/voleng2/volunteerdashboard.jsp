<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style>
.profile_box{
padding: 10px;
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
width: 400px;
height: 200px;
margin: 0 100px 100px;
<% }else{ %>
width: 200px;
height: 300px;
margin: 0 20px 20px 10px;
<% } %>
}

<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
.profile_box .left-column{
	float: left;
	padding: 10px 20px;
	text-align:right;
	width: 160px;
}
.profile_box .right-column{
	float: left;
	padding: 10px 20px;
}
<% } %>

<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
.org_box{
width: 400px;
height: auto;
margin: 0px 100px 20px;
}
.find_box{
margin: 0px 75px 20px;
}
<% } %>
</style>

</head>


<% out.println("<!--"+request.getQueryString()+"-->"); %>
<% 
if(request.getParameter("gigya") != null){
	out.println("<!--first loop-->");
	String aszgig = request.getParameter("gigya");
	out.println("<!--"+request.getParameter("gigya")+"-->");
	if(request.getParameter("gigya").equalsIgnoreCase("gigyaCreateAccount")){ 
%>
<% out.println("<!--second loop-->"); %>
<% out.println("<!--beforehand-->"); %>
<!--<%=request.getQueryString()%>-->
<%//@ include file="/template_include/gigya_linkAccounts.inc" %>
<% out.println("<!--afterwards-->"); %>
<% }} %>

<% int iTmp=0; %>

<% // for google analytics tracking: %>
<% String aszGoalPage; %>
<%
if(!(request.getParameter("method")==null)){
	out.println("<!-- method posting was: " +  request.getParameter("method") + " -->");
	if(request.getParameter("method").equalsIgnoreCase("processcreateorg")){
%>
		<% aszGoalPage = "/organization/create/finish"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
	} else if(request.getParameter("method").equalsIgnoreCase("processRegistration")){
%>
		<% aszGoalPage = "/confirm/individual"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
} } else { 
%>
	<%@include file="/template_include/footer_google_analytics.inc"%>
<% } %>
<% // : end of for google analytics tracking %>

<%
String aszUID="" + aCurrentUserObj.getUserUID();

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/volunteerdashboard.jsp">account home</a></div>
</div>
<% } %>

<!-- personality type is: <%=aCurrentUserObj.getUSPPersonality()%> <%=aCurrentUserObj.getUSPPersonalityTID()%>-->
<% if(b_includeLeftSidebar==true){ %>
 <div id="body" style="padding: 0px">
<% }else{ %> 
<div id="body">
<% } %> 
<% 
// test to see if it's from a URL that has a narrower template
if(b_includeLeftSidebar==true ||
	aszNarrowTheme.equalsIgnoreCase("true") ||
	aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengfacebook") ||
        aszHostID.equalsIgnoreCase("volengfycsandbox") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
	aszSecondaryHost.equalsIgnoreCase("volengivol") ||
aszHostID.equalsIgnoreCase("volengchurch") 
){
%>
<%
}else{
%>
<!-- Vertical Ad -->
<div style="float:left;padding:10px 0px;">
<a href="http://www.christianvolunteering.org/search.jsp"><img src="http://www.christianvolunteering.org/imgs/Vertical_ad.gif"></a>
</div>
<!-- end: Vertical Ad --> 
<% } %>
 
<% if(b_includeLeftSidebar==true){ %>
 <div style="float:left; padding-left: 25px; text-align:center;">
<% }else{ %>
 <div style="float:left; padding-left: 50px; text-align:center;">
<% } %> 
<br>
      	<div>


	<br/>

<!-- Manage Personal Profile section-->
      <div class="profile_box">
		<h2 align="center">Manage Personal Profile</h2>
		
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
<div class="left-column">
     	<a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit Volunteer Profile </a>
          <br><a href="http://www.urbanministry.org/user/me/edit#edit-picture-upload-wrapper">Add Picture</a>
           <br><a href="http://www.urbanministry.org/user/me/edit">Account Settings</a>
          <br><a href="http://www.urbanministry.org/user/me/edit">Change Password</a>
</div>
<div class="right-column">
          <a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Manage Subscriptions</a>
          <br><a href="http://www.urbanministry.org/user/<%=aCurrentUserObj.getUserUID()%>/favorites">View Favorites</a>
          <br><a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals">View Referral History</a>
          <br><div id="socializeLinks"></div>
</div>
<% }else{ %>
     	<a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit Volunteer Profile </a>
          <br><a href="<%=aszPortal%>/register.do?method=showPersonalityTest">Take Personality Test</a>
          <br><a href="<%=aszPortal%>/oppsrch.do?method=showMyMinistryOpps&showhome=true">My Recommended Opportunities</a>
          <br><a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Manage Subscriptions</a>
          <br><a href="http://www.urbanministry.org/user/<%=aCurrentUserObj.getUserUID()%>/favorites">View Favorites</a>

          <br><a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals">View Referral History</a>
          <br><a href="http://www.urbanministry.org/user/me/edit#edit-picture-upload-wrapper">Add Picture</a>
          <br><a href="http://www.urbanministry.org/user/me/edit">Account Settings</a>
          <br><a href="http://www.urbanministry.org/user/me/edit">Change Password</a>
          <br><div id="socializeLinks"></div>
<% } %>
 </div><!--end: profile_box -->
 
   
<!-- end: Manage Personal Profile section-->    
     
<% if(aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){ }else{ %>      
	

<!-- Manage Organization section-->  


      <div class="org_box">	
		 <h2 align="center">Manage <%=aszOrgOrChurchPlural%></h2>
		
      
	<logic:iterate id="org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
      <% iTmp = org.getORGNID(); %>
<% if(org.getPortalName().length()>0){ %>
	<a style="line-height:1em;" title="Click to manage organizational profile" href="/<%=org.getPortalName()%>/org.do?method=showOrgManagement&orgnid=<%=org.getORGNID()%>"><%=org.getORGOrgName()%></>
<% }else{ %>
	<a style="line-height:1em;" title="Click to manage organizational profile" href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<%=org.getORGNID()%>"><%=org.getORGOrgName()%></>
<% } %>
       <br/>
       <br/>
    </logic:iterate>
<% if(! (aszHostID.equalsIgnoreCase("volengchurch") )){ %>
<p align="center">
<% if (aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer") && iTmp==0){ %>
<A class="add-new-buttn add-new-button-sm" title="Click to create a new organizational profile" href="<%=aszPortal%>/register.do?method=showCreateOrgFromVol">Add New <%=aszOrgOrChurch%></A></p>
<% }else{ %>
<A class="add-new-buttn add-new-button-sm" title="Click to create a new organizational profile" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add New <%=aszOrgOrChurch%></A></p>
<% } %>
<% } %>		
 </div><!--end: org_box -->
 <!--end:  Manage Organization section--> 
    
 <div style="clear:both;"></div>
    
     
     
          
          <p>
          <h2 align="center" style="margin: 0px;">Find a Place to Volunteer</h2>
            <div class="find_box">

<h3 align="center" style="margin-top:0px;"><a href="<%=aszPortal%>/advancedsearch.jsp" style="padding: 0px 10px;">Find  Opportunities </a>| <a href="<%=aszPortal%>/advancedsearch.jsp#searchOrganizations" style="padding: 0px 10px;">Find  <%=aszOrgOrChurchPlural%> </a></h3>
            <p>
            <ul class="columns">
            <li><a href="<%=aszPortal%>/advancedsearch.jsp">Local</a></li>
	<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
            <li><a href="http://family.christianvolunteering.org/">Family</a></li>
            <li><a href="http://www.churchvolunteering.org/">Group</a></li>
	<% } %>
            </ul>
            <ul class="columns">
	<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
            <li><a href="<%=aszPortal%>/shorttermmissions.jsp">Short Term Missions</a></li>
	<% } %>
            <li><a href="<%=aszPortal%>/advancedsearch.jsp#searchProfessional">On Nonprofit Board</a></li>
            <li><a href="<%=aszPortal%>/advancedsearch.jsp#searchProfessional">Using Professional Skills</a></li> 
            </ul>
            <ul class="columns">
            <li><a href="<%=aszPortal%>/virtualvolunteer.jsp">Virtual (from home)</a></li>
            <li><a href="<%=aszPortal%>/advancedsearch.jsp#searchInternships">Internship/Work Study</li>
	<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
            <li><a href="http://www.urbanministry.org/jobs">Urban Ministry Job</a></li>
	<% } %>
</ul>
</p>


            </div><!-- end div find_box-->
          </p>
<div style="clear:both;"></div>
<% } %> 

		
<%
if(aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")){
%>
<div style="clear:both;"></div>
<p></p> 
            <div class="find_box">

NOTE:<br>
You are logged in as a TechMission Administrator.<br>
To manage an organization of a provided NID, go to:<br>
<br>
<%=aszSubdomain%>/org.do?method=showAdminOrgManagement&orgnid=
<br><br> & fill in with the org's node id at the end of the URL
<div style="clear:both;"></div>
</div>
<% } %>
      
         
      </div>
   
    </div>
	
<% 
// test to see if it's from a URL that has a narrower template
if( aszNarrowTheme.equalsIgnoreCase("true") ||
	aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengfacebook") ||
        aszHostID.equalsIgnoreCase("volengfycsandbox") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
        aszHostID.equalsIgnoreCase("volengccda") ||
	aszSecondaryHost.equalsIgnoreCase("volengivol") ||
aszHostID.equalsIgnoreCase("volengchurch")
){
%>
<%
}else if(b_includeLeftSidebar==true ){
%>
<!-- Vertical Ad -->
<div style="float:left;padding:10px 0px 0px 10px">
<a href="http://www.christianvolunteering.org/search.jsp"><img src="http://www.christianvolunteering.org/imgs/Vertical_ad.gif"></a>
</div>
<!-- end: Vertical Ad --> 
<% } %>

</div>
	
</div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<!-- Google Code for ChristianVolunteering.org Registered User Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "9qG9CLzeiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=9qG9CLzeiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>

<!-- Google Code for ChristianVolunteering.org Registered Users (Tag) -->
<!-- Remarketing tags may not be associated with personally identifiable information or placed on pages related to sensitive categories. For instructions on adding this tag and more information on the above requirements, read the setup guide: google.com/ads/remarketingsetup -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_label = "7JVvCOz07gQQ7Iqc3gM";
var google_custom_params = window.google_tag_params;
var google_remarketing_only = true;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/1002898796/?value=0&amp;label=7JVvCOz07gQQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
