<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

</head>
<style>
ul.columns {padding-left:10px; float:left; margin-right: 10px;}
ul.columns li {padding-left:0px;}
ul.columns li a{ color:#003366; text-decoration:none; font-weight:normal;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
.profile_box a, .org_box a{ color:#003366; text-decoration:none; line-height:2em;}
</style>

<% // for google analytics tracking & goal tracking: %>
<% String aszGoalPage; %>
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

<html:form action="/register.do"  >
<html:hidden property="method" value="processLogin" />

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
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a></div>
</div>
<% } %>


 <div id="body">
 
<% 
// test to see if it's from a URL that has a narrower template
if (	aszHostID.equalsIgnoreCase("volengabs") ||
		aszHostID.equalsIgnoreCase("volengagrm") ||
		aszHostID.equalsIgnoreCase("volengcityreaching") ||
		aszHostID.equalsIgnoreCase("volengesa") ||
		aszHostID.equalsIgnoreCase("volengextfocused") ||
		aszHostID.equalsIgnoreCase("volenghlic") ||
		aszHostID.equalsIgnoreCase("volengmissionamerica") ||
		aszHostID.equalsIgnoreCase("volengfia") ||
		aszHostID.equalsIgnoreCase("volengsojo") ||
		aszHostID.equalsIgnoreCase("volenguywi") ||
		aszHostID.equalsIgnoreCase("volengworldvision") ||
		aszHostID.equalsIgnoreCase("volengyounglife") ||
		aszHostID.equalsIgnoreCase("volengyouthpartners") 
){ 
}else{
%>
<!-- Vertical Ad -->
<div style="float:left;padding:10px 0px;">
<a href="http://www.christianvolunteering.org/search.jsp"><img src="http://www.christianvolunteering.org/imgs/Vertical_ad.gif"></a>
</div>
<!-- end: Vertical Ad --> 
<% } %>
 
 <div style="float:left; padding-left: 50px; text-align:center;">
 
<br>
      	<div>


<% if( (aszHostID.equalsIgnoreCase("voleng1")) 
	|| 
                (aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
(aszHostID.equalsIgnoreCase("volengnewengland")) 
	|| (aszHostID.equalsIgnoreCase("volengboston"))
	|| (aszHostID.equalsIgnoreCase("volengurbmin"))
	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>
	<br/>
<% }else{ %>
	<br/>
<% } %>

<!-- Manage Personal Profile section-->
      <div  class="profile_box" style="width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; height:200px; padding:10px; float:left; margin: 0 20px 20px 10px;">
		<h2 align="center">Manage Personal Profile</h2>
     <A href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit Volunteer Profile </A>
          <br/>
          <a href="http://www.urbanministryjobs.org/subscribe/newsletters">E-mail  Alerts &amp; Newsletters </a>
          <br />
            <a href="http://www.urbanministry.org/user/me/edit#edit-picture-upload-wrapper">Add Picture</a>
            <br />
            <a href="http://www.urbanministry.org/user/me/profile/uprofile">Account Settings</a>
            <br />
            <a href="http://www.urbanministry.org/user/me/edit">Change Password</a>
            <br />
            <a href="http://www.urbanministry.org/bloggers">Blog on UrbanMinistry.org</a>

 </div><!--end: profile_box -->
 
   
<!-- end: Manage Personal Profile section-->    
     
	

<!-- Manage Organization section-->  


      <div  class="org_box" style="width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; height:200px; padding:10px; float:left;">	
		 <h2 align="center">Manage Organizations</h2>
		
      
	<logic:iterate id="org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
      
          <A href="<%=aszPortal%>/org.do?method=showOrgSumm1&orgnid=<%=org.getORGNID()%>"><br/><%=org.getORGOrgName()%></A>
       
    </logic:iterate>
<br/>
<A href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add New Organization</A>
		

      


<% if(aCurrentUserObj.IsAuthAccessTo(PersonInfoDTO.AUTH_ADMIN)){ %> 
<br>     
      
		<h3>System Administration</h3>
      &nbsp;
     
          Management System&nbsp; [ <a href="<%=aszPortal%>/register.do?method=showSysAdmin1">GO</A> ]
     

<% } %>




</html:form>
 </div><!--end: org_box -->
 
 
 
 <!--end:  Manage Organization section--> 
    
 <div style="clear:both;"></div>
    
     
     
          
          <p>
          <h2 align="center" style="margin: 0px;">Find a Place to Volunteer</h2>
            
            
            <div style="padding: 10px; border: 3px solid #93B6F0; float:left; text-align:left; width:480px;">

<h3 align="center" style="margin-top:0px;"><a href="<%=aszPortal%>/advancedsearch.jsp" style="padding: 0px 10px;">Find  Opportunities </a>| <a href="<%=aszPortal%>/oppsrch.do?method=processOrgSearchAll&voltype=&duration=&postalcode=&distance=City&programtype=&orgname=&city=&state=&prov=&country=us&region=&affiliation=&partner=&partner2=&partner3=&partner4=&partner5=&localaffil=&searchkey1=state&Submit=Go" style="padding: 0px 10px;">Find  Organizations </a></h3>
            <p>
            <ul class="columns">
            <li><a href="<%=aszPortal%>/advancedsearch.jsp">Local</a></li>
            <li><a href="http://www.christianvolunteering.org/virtualvolunteer.jsp">Virtual (from home)</a></li>
            <li><a href="http://family.christianvolunteering.org/">Family</a></li>
            <li><a href="http://www.churchvolunteering.org/">Group</a></li>
            </ul>
            <ul class="columns">
            <li><a href="http://www.christianvolunteering.org/shorttermmissions.jsp">Short Term Missions</a></li>
            <li><a href="<%=aszPortal%>/advancedsearch.jsp#searchProfessional">On Nonprofit Board</a></li>
            <li> <a href="<%=aszPortal%>/advancedsearch.jsp#searchProfessional">Using  Professional Skills</a></li> 
            </ul>
            <ul class="columns">
            <li><a href="<%=aszPortal%>/advancedsearch.jsp#searchInternships">Internship/Work Study</li>
            <li><a href="http://www.urbanministry.org/jobs">Urban Ministry Job</a></li>
</ul>
</p>
<div style="clear:both;"></div>
            </div>
          </p>
         
      </div>


</div>

   
    </div>
</div>
<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
