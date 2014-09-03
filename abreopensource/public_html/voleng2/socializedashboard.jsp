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
ul.columns {float:left; width:auto;}
ul.columns li {padding-left:0px; //list-style: square;}
ul.columns li a{ color:#003366; text-decoration:none;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
.profile_box{width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; height:300px; padding:10px; float:left; margin: 0 20px 20px 10px;}
.profile_box a, .org_box a{ color:#003366; text-decoration:none; line-height:2em;}
.profile_box a:hover, .org_box a:hover {text-decoration:underline;}
.org_box {width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; min-height:300px; //height:200px; padding:10px; float:left;}
.find_box {padding: 10px; border: 3px solid #93B6F0; float:left; text-align:left; width:480px;}
h3{margin:0px;}

a.add-new-buttn { 
background-color:#83A2F4;
border:1px solid #4D73CF;
color:#FFFFFF;
font-weight:bold;
padding:3px;
width:140px;
text-decoration:none;
}
a.add-new-buttn:hover {
background-color:#4D73CF;
text-decoration:none;
color:#FFF;
}

</style>

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
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
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
<span style="float: left;">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/volunteerdashboard.jsp">account home</a></div>
</div>
<% } %>


 <div id="body">
 
<% 
// test to see if it's from a URL that has a narrower template
if(aszNarrowTheme.equalsIgnoreCase("true")){ 
}else if (	aszHostID.equalsIgnoreCase("volengccda")){
}else if (	aszSecondaryHost.equalsIgnoreCase("volengivol")){
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
 
 
 
 
 
 
<div id="gigyaWelcome" name="gigyaWelcome" class="welcome message">
</div>
<div id="gigyaError" name="gigyainfo"  class="messages error">
</div>
</center>


 
 
 <div style="float:left; padding-left: 50px; text-align:center;">
 
<br>
      	<div>


	<br/>

<!-- Manage Personal Profile section-->
      <div class="profile_box">
		<h2 align="center">Manage Personal Profile</h2>
     <a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit Volunteer Profile </a>
          <br/>
					<a href="http://apps.facebook.com/find-your-calling/register.do?method=showPersonalityTest">Take Personality Test</a><br/>
					<a href="http://apps.facebook.com/find-your-calling/oppsrch.do?method=showMyMinistryOpps&showhome=true">My Recommended Opportunities</a><br/>
          <a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Manage Subscriptions</a>
          <br />
            <a href="http://www.urbanministry.org/user/<%=aCurrentUserObj.getUserUID()%>/favorites">View Favorites</a>
          <br />
            <a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals">View Referral History</a>
          <br />
            <a href="http://www.urbanministry.org/user/me/edit#edit-picture-upload-wrapper">Add Picture</a>
            <br />
            <a href="http://www.urbanministry.org/user/me/profile/uprofile">Account Settings</a>
            <br />
            <a href="http://www.urbanministry.org/user/me/edit">Change Password</a>
            <br />
            <a href="http://www.urbanministry.org/bloggers">Blog on UrbanMinistry.org</a>
            <br />
            <a href="<%=aszPortal%>/register.do?method=showUnlinkSocialize">Manage Social Networking</a>
            <div id="socializeLinks"></div>

 </div><!--end: profile_box -->
 
   
<!-- end: Manage Personal Profile section-->    
     
	

<!-- Manage Organization section-->  


      <div class="org_box">	
		 <h2 align="center">Manage Organizations</h2>
		
      
	<logic:iterate id="org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
      <% iTmp = org.getORGNID(); %>
<A style="line-height:1em;" title="Click to manage organizational profile" href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<%=org.getORGNID()%>"><%=org.getORGOrgName()%></A>
       <br/>
       <br/>
    </logic:iterate>
<p align="center">
<% if (aCurrentUserObj.getUSPVolOrOpportunity().equalsIgnoreCase("Volunteer") && iTmp==0){ %>
<A class="add-new-buttn" title="Click to create a new organizational profile" href="<%=aszPortal%>/register.do?method=showCreateOrgFromVol">Add New Organization</A></p>
<% }else{ %>
<A class="add-new-buttn" title="Click to create a new organizational profile" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add New Organization</A></p>
<% } %>		
		

      

 </div><!--end: org_box -->
 <!--end:  Manage Organization section--> 
    
 <div style="clear:both;"></div>
    
     
     
          
          <p>
          <h2 align="center" style="margin: 0px;">Find a Place to Volunteer</h2>
            
            
            <div class="find_box">

<h3 align="center" style="margin-top:0px;"><a href="<%=aszPortal%>/advancedsearch.jsp" style="padding: 0px 10px;">Find  Opportunities </a>| <a href="<%=aszPortal%>/advancedsearch.jsp#searchOrganizations" style="padding: 0px 10px;">Find  Organizations </a></h3>
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
<div style="clear:both;"></div>
            </div>
          </p>
         
      </div>


</div>

   
    </div>
</div>

<%@ include file="/template_include/gigya_socialize.inc" %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
