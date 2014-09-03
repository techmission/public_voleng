<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<%
int iTemp=0, iContactOrAdmin=0;
long iTime = 0;
int iWarningTime = 30; //number of days until expiration - warning
String aszMemberType = "";
if(aszMemberType.length()<1){
	aszMemberType = aCurrentUserObj.getUSPSiteUseType();
}
boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") ||
	(
		aszMemberType.equalsIgnoreCase("Church")  && 
		aszSecondaryHost.equalsIgnoreCase("default")
	)
){
	bFaith=true;
	aszOrgOrChurch="Church";
	aszOrgOrChurchOpp="Ministry";
}
boolean bOrg=false;
if(aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
	bOrg=true;
}
boolean bByContact=false;
String aszByContact = "";

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}

int iCurrNID=0;
if(!(request.getParameter("orgnid")==null)){
	String aszNID = (String) request.getParameter("orgnid");
	if(aszNID.length() > 1){
		iCurrNID = Integer.parseInt(aszNID);
	}
}

%>
<style>
#maincontent.left-sidebar-org #body {
    width: 730px;
}
h3, h4 {margin:0px;}
.heading3_select{ font-size:18px;}

.heading4_manage{ 
	display: inline; 
	margin-right:10px; 
	font-size:14px; 
	color: #666666; 
}
.contactsContent{
	display: inline;
	float:right;
	padding-right: 35px;
}
.manageContacts{
	width:400px;
}
#body ul{
	list-style-type:none;
	padding-left: 0;
}
#body li{
	float:left;
	padding: 0 10px 0 0;
}
table#contact td {
	padding: 5px 9px;
}
table#contact th {
	padding: 0px 5px;
}
.portal-bold{
	font-weight:bold;
}
.left-column-wide{
	float: left;
	padding: 3px;
	width: 360px;
	text-align:right;
}
.left-column{
	float: left;
	padding: 3px;
	width: 290px;
	line-height:2em;
	text-align:right;
}
.right-column{
	float: left;
	padding: 3px;
}
.span-columns{
	padding: 10px;
}

</style>
<!--  (request.getParameter("method") is <%= request.getParameter("method") %> -->
<% // for google analytics tracking: 
String aszGoalPage; 
if(!(request.getParameter("method")==null)){
	if(request.getParameter("method").equalsIgnoreCase("processCreateOpportstep1")){
%>
		<% aszGoalPage = "/opportunity/create/finish"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
	} else if(request.getParameter("method").equalsIgnoreCase("processcreateorg")){
%>
		<% aszGoalPage = "/organization/create/finish"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
	} else if(request.getParameter("method").equalsIgnoreCase("processCreateAccount1")){
%>
		<% aszGoalPage = "/confirm/individual"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
	} else { 
%>
	<%@include file="/template_include/footer_google_analytics.inc"%>
<%
	}
} else { 
%>
	<%@include file="/template_include/footer_google_analytics.inc"%>
<% 
} 
// : end of for google analytics tracking
%>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Manage Opportunities for <%=org.getORGOrgName()%></span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Manage Opportunities for <%=org.getORGOrgName()%></span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> account summary</a></div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==false){ %><div style="display:none;"><% } %>
<%@ include file="/template_include/left_sidebar_org.inc" %>
<% if(b_includeLeftSidebar==false){ %></div><% } %>
<% if(iCurrNID==0)	iCurrNID=iSidebarCurrOrgNID; %>

<% 
boolean b_CVCInternshipSiteApproved = false;
if(org.getCVInternSiteApproved().equals("City Vision"))		b_CVCInternshipSiteApproved=true;
%>
<div id="body" style="padding-top: 10px">

<% if(b_CVCInternshipSiteApproved){ %>
<% if(org.getCVInternInterest()>0){ %>
<br />
<a href="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&cvintern=true&fq=content_type:resume&fq=org_nid:<%=""+iCurrNID %>&fq=applic_org_names:&quot;<%=org.getORGOrgName() %>&quot;#fq=content_type:resume&fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&fq=content_type:resume&fq=org_nid:<%=""+iCurrNID %>&fq=applic_org_names:&quot;<%=org.getORGOrgName() %>&quot;" class="add-new-buttn add-new-button-lg highlight-buttn">View City Vision Internship Applicants for My Site</a>
<% } %>
<br />


	<h3 class="heading3_select" style="display: inline;"><a href="<%=request.getContextPath() %>/cityvision/oppsrch.do?method=processSearch&cvintern=true&fq=content_type:resume" class="add-new-buttn add-new-button-lg highlight-buttn">View City Vision Internship Applicants for All Sites</a></h3>
	<!-- h3 class="heading3_select" style="display: inline;"><a href="<%=request.getContextPath() %>/cityvision/internlistings.jsp" class="add-new-buttn add-new-button-lg">View City Vision Internship Applicants</a></h3 -->
<br /><br /><br />
<% } %>

<% if(iCurrNID==0){ %>
<h2>You do not currently have an Organization posted</h2>
<br>
		<div id="addneworglink" style="display:inline;">
			<a class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" >Add Organization</a>
		</div>
	
<% }else if(bHasOpps == false){ %>
<br /><br />
<h2>You do not currently have any Opportunities</h2>
<%
}else{

int iCount=0;
String aszClass="";
%>
<div id="organizationopps">
<table id="contact" style="contact" border="1">
	<tr>
		<th align="center" colspan="2">Opportunity Title</th>
<% if(org.getLoadByMethod() == LOADBY_UID_CONTACT){ %>	
		<th align="center" colspan="4">Manage</th>
<% }else{ %>	
		<th align="center" colspan="6">Manage</th>
<% } %>	
		<th align="center">Expires on</th>
	</tr>
	<logic:iterate id="opp" name="opplist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
		<%
			iCount++;
			int iOppNID=opp.getOPPNID();
	      	if(org.getRequestType().equals("ByContact")){
	      		iContactOrAdmin=1;
	      		bByContact=true;
	      		aszByContact="ByContact";
	      	}else if(org.getLoadByMethod() == LOADBY_UID_CONTACT){ 
	      		aszByContact="ByContact";
	      	}else{
	      		iContactOrAdmin=0;
	      	}
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}else{
    			aszClass="";
    		}
		%>
	<tr class="<%=aszClass%>">
	<td>
		<%=iCount%>
	</td>
	<td width="175">
		<b><%=opp.getOPPTitle()%></b>
	</td>
<% if(org.getLoadByMethod() != LOADBY_UID_CONTACT){ %>	
	<td>
		<A href="<%=aszPortal%>/org.do?method=showOppOrgContacts&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>&requesttype=edit<%=aszByContact%>">Contacts</A>
	</td>
	<td align="center">
<% if(org.getCVInternInterest()>0 && opp.getOPPTitle().contains("City Vision Internship:")){ %>
		<a href="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&cvintern=true&fq=content_type:resume&fq=org_nid:<%=""+iCurrNID %>&fq=applic_org_names:&quot;<%=org.getORGOrgName() %>&quot;#fq=content_type:resume&fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&fq=content_type:resume&fq=org_nid:<%=""+iCurrNID %>&fq=applic_org_names:&quot;<%=org.getORGOrgName() %>&quot;">View Referrals</a>
<% }else{ %>
		<a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals&listtype=oppmanagement&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>">View Referrals</a>
<% } %>
	</td>
<% } %>
	<td>
		<A href="<%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>&requesttype=view<%=aszByContact%>">Preview</A>
	</td><td>
		<A href="<%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>&requesttype=edit<%=aszByContact%>">Edit</A>
	</td><td>
		<A href="<%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>&requesttype=edit<%=aszByContact%>">Delete</A>
	</td><td>
		<A href="<%=aszPortal%>/org.do?method=showOpportunityDuplic&orgnid=<%=iCurrNID%>&oppnid=<%=iOppNID%>&requesttype=edit<%=aszByContact%>" >Duplicate</A>
	</td><td>
	
	<%
	iTime = opp.getOPPExpirationDt();
	aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));// doesn't appear to be calculating correctly
	if (opp.getOPPExpirationTime() / 86400 < 0){ 
	%>
<strong><%	out.print( aszTemp ); %></strong>
<% }else if (opp.getOPPExpirationTime() / 86400 <= iWarningTime){ %>
<strong><%	out.print( aszTemp ); %></strong>
<% }else{ %>
<%	out.print( aszTemp ); %>
<% } %>
	</td>
	</tr>
	</logic:iterate>
</table>
</div><!-- end div "organizationopps" -->
	
<% } %>
<% if(org.getLoadByMethod() != LOADBY_UID_CONTACT){ %>
<br>
		<div>
			<a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals&listtype=orgmanagement&orgnid=<%=iCurrNID%>" >Show all referrals for all opportunities</a>
		</div>
	
<br>
		<div id="addnewopplink" style="display:inline;">
			<a class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iCurrNID%>&requesttype=<%=aszByContact%>" >Add New Opportunity</a>
		</div>
<% } %>
		

      </div><!-- end div "body" -->
      </div><!-- end div "maincontent" -->
    

 
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>

<!-- Google Code for ChristianVolunteering.org Registered Organizations (Tag) -->
<!-- Remarketing tags may not be associated with personally identifiable information or placed on pages related to sensitive categories. For instructions on adding this tag and more information on the above requirements, read the setup guide: google.com/ads/remarketingsetup -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_label = "O5fNCPTz7gQQ7Iqc3gM";
var google_custom_params = window.google_tag_params;
var google_remarketing_only = true;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/1002898796/?value=0&amp;label=O5fNCPTz7gQQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
