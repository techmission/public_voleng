<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<%@page import="java.util.Iterator"%>

<%
int iTemp=0;
int vidService=32, vidPosFreq=268;

String aszOrgPortalName = "";

boolean bByContact=false;
String aszByContact = "";
String aszMemberType = "";
if(aszMemberType.length()<1){
	aszMemberType = aCurrentUserObj.getUSPSiteUseType();
}
boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") ||
	(
		aszMemberType.equalsIgnoreCase("Church")  && 
		(
			aszHostID.equalsIgnoreCase("voleng1") 	|| 
			aszHostID.equalsIgnoreCase("default")	||
			aszHostID.equalsIgnoreCase("volengexample")
		)
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

if(tempPortal.length()>7) if(tempPortal.startsWith("/voleng")) tempPortal=tempPortal.substring(8);
else if(tempPortal.length()>0) if(tempPortal.startsWith("/")) tempPortal=tempPortal.substring(1);

if(aszPortal.equals("/voleng")){
	tempPortal="";
	portal="";
}
if(tempPortal.length()>0 && tempPortal.equalsIgnoreCase(aCurrentUserObj.getNatlAssociationPortal())) bManageAssoc=true;

int iNumber=0;
// make an array of numbers - it will be filled with all the values of org NID's
ArrayList aOrgNids = new  ArrayList();
int iContactAdminsCount=0, iContactOrAdmin=0;
ArrayList aOrgNidAdminOrContact = new  ArrayList();
int[] a_iOrgNidAdminOrContact = new  int[2];
String aszSelected = "";

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}
if(aCurrentUserObj.getNatlAssociationPortal().length()>0){
	aszPortalLink =  "/" + aCurrentUserObj.getNatlAssociationPortal(); 
}

int iCurrNID=0, iTempNID=0;
if(!(request.getParameter("orgnid")==null)){
	String aszNID = (String) request.getParameter("orgnid");
	if(aszNID.length() > 1){
		iCurrNID = Integer.parseInt(aszNID);
	}
	iTempNID=iCurrNID;
}else if(aszPortalNID.length()>0){
	try{
		iCurrNID = Integer.parseInt(aszPortalNID);
	}catch(Exception e){}
	if(bManageAssoc==false)	iTempNID=iCurrNID;
}
out.print("<!-- iCurrNID is "+ iCurrNID+" -->");
ArrayList aOrgNidAndPortal = new ArrayList();
String[] a_aszOrgNidAndPortal = new String[2];
%>

<div style="display:inline;">
	<logic:iterate id="orgnids" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
      <%  
      	aOrgNids.add(orgnids.getORGNID()+""); 
      	
      	if(orgnids.getRequestType().equals("ByContact")){
      		iContactOrAdmin=1;
      		bByContact=true;
      		aszByContact="ByContact";
      	}else{
      		iContactOrAdmin=0;
      	}
      	a_iOrgNidAdminOrContact[0]=orgnids.getORGNID(); 
      	a_iOrgNidAdminOrContact[1]=iContactOrAdmin; 
      	aOrgNidAdminOrContact.add(a_iOrgNidAdminOrContact);
      	
      	a_aszOrgNidAndPortal = new String[2];
      	a_aszOrgNidAndPortal[0]=orgnids.getORGNID()+"";
      	a_aszOrgNidAndPortal[1]=orgnids.getPortalName();
      	aOrgNidAndPortal.add(a_aszOrgNidAndPortal);
      %>
	<SELECT id="opportunitieslist<%=orgnids.getORGNID()%>" name="opportunitieslist<%=orgnids.getORGNID()%>" style="display:none">
		<OPTION value="" selected>-- click to select --</OPTION> 
		<logic:iterate id="opp" name="opplist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
			<% if(opp.getORGNID()==orgnids.getORGNID()){ %>
				<option value="<%=opp.getOPPNID()%>"><%=opp.getOPPTitle()%></option>
			<% } %>
		</logic:iterate>
	</SELECT>
    </logic:iterate>
</div>

<script language="javascript">

function showorg(inid,newlist){
	var oppElem = document.getElementById('opportunitieslist'+inid); 
	var oppIndex = oppElem.selectedIndex;
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
<% if(bManageAssoc==false){ %>
	var portal='<%=tempPortal%>/';
<% }else{ %>
	var portal='';
<%
}
String aszTempOrgNid="", aszTempPortal="";
int iTempOrgNid=0;
Iterator<String[]> itrOrgNidAndPortal = aOrgNidAndPortal.iterator();
String[] OrgNidAndPortalElement=new String[2];
while (itrOrgNidAndPortal.hasNext()) {
	OrgNidAndPortalElement = itrOrgNidAndPortal.next();
	aszTempOrgNid = OrgNidAndPortalElement[0];
	aszTempPortal = OrgNidAndPortalElement[1];
	try{
		iTempOrgNid=Integer.parseInt(aszTempOrgNid);
	}catch(NumberFormatException e){
		iTempOrgNid=0;
	}catch(Exception ex){
		iTempOrgNid=0;
	}
	if(iTempOrgNid>0 && aszTempPortal.length()>0){
%>
	if(inid==<%=iTempOrgNid%>){
		portal='<%=aszTempPortal%>/';
	}
<%
	}
}

%>
	newElem.options.length = 0;
	for(var i=0; i<oppElem.options.length; i++) {
		tmpValue = oppElem.options[i].value;
		tmpText = oppElem.options[i].text;

		if (i != oppIndex) { 
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue); 
		}else{
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue, " \"selected=selected\" "); 
			if(inid>0)	document.getElementById(newlist).style.display='inline';
		}
	}
	document.getElementById('organization').style.display='inline';
	document.getElementById('neworglink').style.display='inline';
	document.getElementById('manageOrg').style.display='inline';

	document.getElementById('editorglink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid='+inid+'">Edit</A>';
	document.getElementById('editorgoutcomeslink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=shownonpeditstep2&reqtype=edit&orgnid='+inid+'">Edit outcomes</A>';
	document.getElementById('vieworglink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=shownonpvieworg&orgnid='+inid+'">View</A>';
	document.getElementById('vieworgreferralslink').innerHTML = '<A href="<%=aszPortal%>/email.do?method=showVolunteerReferrals&listtype=orgmanagement&orgnid='+inid+'">View referrals</A>';
	document.getElementById('deleteorglink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid='+inid+'">Delete</A>';
	document.getElementById('viewAdminLink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid='+inid+'">Administrators</a>';
	document.getElementById('addAdminLink').innerHTML = '<A href="<%=aszPortal%>/org.do?method=showAddOrgAdmin&orgnid='+inid+'">Add New Administrator</a>';
	document.getElementById('addnewopplink').innerHTML = '<A class="add-new-buttn add-new-button-lg" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid='+inid+'&requesttype=<%=aszByContact%>" >Add New Opportunity</A>';
	document.getElementById('editoppcontactslink').innerHTML = '<A href="javascript:showOppContacts('+inid+',\'edit<%=aszByContact%>\')">Contacts</A>';
	document.getElementById('editopplink').innerHTML = '<A href="javascript:editOpportunity('+inid+',\'edit<%=aszByContact%>\')">Edit</A>';
	document.getElementById('prevopplink').innerHTML = '<A href="javascript:editOpportunity('+inid+',\'view<%=aszByContact%>\')">Preview</A>';
	document.getElementById('deleteopplink').innerHTML = '<A href="javascript:editOpportunity('+inid+',\'edit<%=aszByContact%>\')">Delete</A>';
	document.getElementById('duplicopplink').innerHTML = '<A href="javascript:duplicOpportunity('+inid+',\'edit<%=aszByContact%>\')">Duplicate</A>';
	document.getElementById('advmanagelistingslink').innerHTML = '<a href="<%=aszPortal%>/org.do?method=showOrgManageListings&orgnid='+inid+'">Manage All</a>';

if(portal.length>1){ 
	var iframeinput = "<iframe id=\"content\" WIDTH=\"650\" HEIGHT=\"400\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%>/"+portal+"oppsrch.do?method=showPortalListings&skipbanner=skipbanner\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
}else{
	var iframeinput = "<iframe id=\"content\" WIDTH=\"650\" HEIGHT=\"400\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%>/"+portal+"orglistings"+ inid +".jsp&skipbanner=skipbanner\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
}

if(portal.length>0 ){	
	document.getElementById('createportallink').innerHTML = '<a  class="add-new-buttn add-new-button-lg" href="/'+portal+'org.do?method=showOrgManagement&orgnid='+inid+'">Manage Portal</a>';
}else{
<% if(portal.length()<1 && showPortalInfo==true ){ %>	
	document.getElementById('createportallink').innerHTML = '<a  class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showPortalManage&orgnid='+inid+'">Yes, Create Portal</a>';
<% } %>
}
	document.getElementById('embed_div_inner').innerHTML = '<select id="embedselect" name="embedselect" size="1" class="smalldropdown" style="width: 175px;" onchange="update_embed(this, \'orglistings'+inid+'.jsp\')"> ' +
		'	<option value="embed_list">Embed All Your Listings</option> ' +
		'	<option value="embed_preview">Embed a Preview of Your Listings</option> ' +
		'	<option value="embed_listurl">Link to URL of Your Listings</option> ' +
		'	<option value="embed_searchbox">Embed a Search Form on Your Site</option> ' +
		'</select>';
	document.getElementById('previewnuminput').innerHTML = '<input type="text" value="" name="preview" id="preview" onchange="changeIframeText(\'orglistings'+inid+'.jsp\')">';
	document.getElementById('colorbckgrndinput').innerHTML = '<input type="text" value="#FFFFFF" name="bckgrnd" id="bckgrnd" onchange="changeIframeText\'orglistings'+inid+'.jsp\')">';
	document.getElementById('colorbordrinput').innerHTML = '<input type="text" value="#000000" name="brdr" id="brdr" onchange="changeIframeText(\'orglistings'+inid+'.jsp\')">';
	document.getElementById('colorlinktxtinput').innerHTML = '<input type="text" value="#003366" name="atxt" id="atxt" onchange="changeIframeText(\'orglistings'+inid+'.jsp\')">';
	document.getElementById('colortxtinput').innerHTML = '<input type="text" value="black" name="txt" id="txt" onchange="changeIframeText(\'orglistings'+inid+'.jsp\')">';
}

function editOpportunity(orgnid, value1){
		code = document.orgForm.opportunities.value; 
		if(code == ''){
			alert('opportunity selection required.');
			return ; 
		} 
		document.orgForm.oppnid.value = document.orgForm.opportunities.value;
		document.orgForm.orgnid.value = orgnid; 
		document.orgForm.method.value = "showOpportunityEdit";
		document.orgForm.requesttype.value = value1; 
		document.orgForm.submit();
} 
function duplicOpportunity(orgnid, value1){
		code = document.orgForm.opportunities.value; 
		if(code == ''){
			alert('opportunity selection required.');
			return ; 
		} 
		document.orgForm.oppnid.value = document.orgForm.opportunities.value;
		document.orgForm.orgnid.value = orgnid; 
		document.orgForm.method.value = "showOpportunityDuplic";
		document.orgForm.requesttype.value = value1; 
		document.orgForm.submit();
} 
function showOppContacts(orgnid, value1){
		code = document.orgForm.opportunities.value; 
		if(code == ''){
			alert('opportunity selection required.');
			return ; 
		} 
		document.orgForm.oppnid.value = document.orgForm.opportunities.value;
		document.orgForm.orgnid.value = orgnid; 
		document.orgForm.method.value = "showOppOrgContacts";
		document.orgForm.requesttype.value = value1; 
		document.orgForm.submit();
} 

</script>

<style>
h3, h4 {margin:0px;}
.heading3_select{ font-size:18px;}

.heading4_manage{ 
	display: inline; 
	margin-right:10px; 
	font-size:14px; 
	color: #666666; 
}
.heading_link{ 
	display: inline; 
	float:right; 
	font-size:12px; 
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
a.heading4_manage{
    color: #666666;
    float:inline;
    font-size: 14px;
    font-weight: bold;
    margin-right: 10px;
    text-decoration: none;
}
#oppsearch.manage_style{
	display:inline; 
	background-color:#83A2F4; 
	width:772px; 
	margin-top:10px; 
	margin-bottom:10px;
}
#oppsearch.manage_style span#title {
    background-color: #83A2F4;
    border-right: 1px solid #003366;
    color: #fff;
}
</style>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<form action="<%=aszPortal%>/org.do" name="orgForm" method="get">
<input type="hidden" name="method" value="showOpportunityPreview" />
<input type="hidden" name="orguid" />
<input type="hidden" name="oppnid" value="0" />
<input type="hidden" name="orgnid" />
<input type="hidden" name="requesttype" />


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Manage <%=aszOrgOrChurch%></span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" <% if(portal.length()<1){ %> class="sidebarless"<% } %>>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Manage <%=aszOrgOrChurch%></span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> account summary</a></div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(bManageAssoc==true){ %>
  <div id="oppsearch" class="manage_style" >
	  <span id="title">Currently Managing Organization as National Association Manager</span>
  </div>
<% }else if(aCurrentUserObj.getNatlAssociationNID()>0){ %>
  <div id="oppsearch" class="manage_style" >
	  <span id="title">Currently Managing <%=aszOrgOrChurch%></span>
  </div>
<% } %>

<% // for google analytics tracking: 
String aszGoalPage; 
if(!(request.getParameter("method")==null)){
	if(request.getParameter("method").equalsIgnoreCase("processcreateorg")){
%>
		<% aszGoalPage = "/organization/create/finish"; %>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% 
	} else if(request.getParameter("method").equalsIgnoreCase("processCreateOpportstep1")){
%>
		<% aszGoalPage = "/opportunity/create/finish"; %>
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
<% } 
// : end of for google analytics tracking %>

<div id="body">
<% if(portal.length()>0){ %>

<% if(portal.length() < 1){ %>
	<div class="manageorg_container wide">
<% }else if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
	<div class="manageorg_container">
<% }else{ %>
	<div class="manageorg_container wide">
<% } %>

<% if(aCurrentUserObj.getNatlAssociationNID()>0){ %>
		<br>
<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. National Association:</h3>
		<br>  
<center>
<%
String aszPortalShort = portal;
if(aszPortalShort.startsWith("/voleng") && aszPortalShort.length()>7) aszPortalShort=aszPortalShort.substring(8);
else if(aszPortalShort.startsWith("/") && aszPortalShort.length()>1) aszPortalShort=aszPortalShort.substring(1);

String aszNationalAssociation = aCurrentUserObj.getNatlAssociation();
String aszNationalAssociationPortal = aCurrentUserObj.getNatlAssociationPortal();
String[] aszNationalAssociationNames = aCurrentUserObj.getUSPNatlAssociationsArray();
String[] aszNationalAssociationPortals = aCurrentUserObj.getUSPNatlAssociationPortalsArray();
for(int i=0; i<aszNationalAssociationPortals.length; i++){
	String aszNatlAssocPortal = aszNationalAssociationPortals[i];
	int indexSemicolon = aszNatlAssocPortal.indexOf(";");
	if(aszNatlAssocPortal.length() > 0 && aszNatlAssocPortal.length() > indexSemicolon){
		try{
			aszNatlAssocPortal = aszNatlAssocPortal.substring(0,indexSemicolon);
		}catch (Exception e){}
	}
	if(aszNatlAssocPortal.equals(aszPortalShort)){
		try{
			aszNationalAssociationPortal = aszNatlAssocPortal;
			aszNationalAssociation = aszNationalAssociationNames[i];
		}catch(Exception e){}
	}
}
%>
<!--a class="heading4_manage" href="<%=request.getContextPath()%>/<%=aszNationalAssociationPortal%>/assocationmanagement.jsp">Manage <%=aCurrentUserObj.getNatlAssociation()%></a-->
<!--a class="add-new-buttn  add-new-button-lg" href="<%=request.getContextPath()%><%=aszPortalLink%>/associationmanagement.jsp">Manage <%=aszNationalAssociation%></a-->
<a class="add-new-buttn  add-new-button-lg" href="<%=request.getContextPath()%>/<%=aszNationalAssociationPortal%>/associationmanagement.jsp">Manage <%=aszNationalAssociation%></a>
</center>
<br>
	<hr color="#CCCCCC" width="100%" size="1">  

<% } %>



<%
int iHighNID=0;
String aszCurrName="", aszAlphName="";

if(aOrgNids.size()<1){
%>
	<center>
		<br>
<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. <%=aszOrgOrChurch%>:</h3>
		<br>  
	<% if (aCurrentUserObj.getUSPVolOrOpportunity().equalsIgnoreCase("Volunteer")){ %>
		<A class="add-new-buttn" title="Click to create a new organizational profile" href="<%=aszPortal%>/register.do?method=showCreateOrgFromVol">Add New <%=aszOrgOrChurch%></A></p>
	<% }else{ %>
		<% if(aszPortal.length()>0 && aszHostID.equalsIgnoreCase("volengchurch")){ %>	
		<% }else{ %>
		<A class="add-new-buttn" title="Click to create a new organizational profile" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add New <%=aszOrgOrChurch%></A></p>
		<% } %>
	<% } %>		
	</center>
	</div>
<% }else{ %>

	<% iNumber++; %>
	<% if(aszPortal.length()>0 && showPortalInfo==true){ %>	
		<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. <%=aszOrgOrChurch%>:</h3>
	<% }else{ %>
		<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. Select <%=aszOrgOrChurch%>:</h3>
	<% } %>
	<% int iTempCount=0; %>
	<SELECT id="organizationlist" name="organizationlist" onchange="showorg( this.value, 'opportunities' )" style="display: inline; width:200px; ">
		<logic:iterate id="org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
	    <%
			if(org.getORGMembertype().equalsIgnoreCase("Church")) aszMemberType="Church";
	      	aszSelected="";
	      	if(iCurrNID < 1){
	      		iCurrNID=org.getORGNID();
	      	}
			if (!( session.getAttribute("orgmanagementnid") == null )){ 
				String aszNID = (String) session.getAttribute("orgmanagementnid");
				if(aszNID.length() > 1){
					int iOrgNID = Integer.parseInt(aszNID);
					if( iOrgNID == org.getORGNID()){
						aszSelected="selected=selected";
						iCurrNID=iOrgNID;
					}
				}
	      	}
	      	if(org.getORGNID()==iCurrNID){
				aszSelected="selected=selected";
			}
			if(iTempCount==0 && iTempNID<1)	iTempNID=org.getORGNID();
			iTempCount++;
	    %>
	    <OPTION value="<%=org.getORGNID()%>" <%=aszSelected%> ><%=org.getORGOrgName()%></OPTION>
	    </logic:iterate>
	</SELECT>
		<div id="neworglink" style="display:inline;">
	<% if(	aszPortal.length()>0 && (!(aszPortal.equals("/voleng")))){ 
	}else if(bByContact==false){ %>
	 <h3 style="display: inline; margin-left:10px; margin-right:10px;">OR</h3> 
	<A class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add New <%=aszOrgOrChurch%></A>
	<% } %>
	</div>
<br clear="all">
<br>


<div id="organization" >
<% if(bByContact==false){ %>
	<h4 class="heading4_manage">Manage <%=aszOrgOrChurch%> Profile:</h4>
	<div id="manageOrg" style="display:inline;">
		<div id="editorglink" style="display:inline;">
		      <A href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid=<%=iCurrNID%>">Edit</A>
	    </div>
	    &nbsp; | &nbsp; 
	    <div id="editorgoutcomeslink" style="display:inline;">
		      <A href="<%=aszPortal%>/org.do?method=shownonpeditstep2&reqtype=edit&orgnid=<%=iCurrNID%>">Edit outcomes</A>
	    </div>
	    &nbsp; | &nbsp; 
	    <div id="vieworglink" style="display:inline;">
		      <A href="<%=aszPortal%>/org.do?method=shownonpvieworg&orgnid=<%=iCurrNID%>">View</A>
	    </div>
	    &nbsp; | &nbsp; 
	    <div id="vieworgreferralslink" style="display:inline;">
		      <A href="<%=aszPortal%>/email.do?method=showVolunteerReferrals&listtype=orgmanagement&orgnid=<%=iCurrNID%>">View referrals</A>
	    </div>
	    &nbsp; | &nbsp;
	    <div id="deleteorglink" style="display:inline;">
		      <A href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid=<%=iCurrNID%>">Delete</A>
	    </div>
	</div>
	<br /><br />
	<div class="manageContacts">
		<h4 class="heading4_manage">Manage Contacts:</h4>
		<div class="contactsContent">
			<div id="viewAdminLink" style="display:inline;">
				<A href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<%=iCurrNID%>">Administrators</a>
			</div>
			<div style="display:inline;">&nbsp; | &nbsp;</div>
			<div id="addAdminLink" style="display:inline;">
				<A href="<%=aszPortal%>/org.do?method=showAddOrgAdmin&orgnid=<%=iCurrNID%>">Add New Administrator</a>
			</div>
		</div>
	</div>
	<br>
<% if(bManageAssoc==false){ %>
	<center>
	<% if(	aszPortal.length()>0 && 
			aszHostID.equalsIgnoreCase("volengchurch") && 
			(! (aszPortal.equals("/voleng")) )
	){ %>
		<A class="add-new-buttn add-new-button-lg" href="<%=aszPortal%>/churchopportunities.jsp">Manage Parachurch Missions Opportunities</A>
	<% }else if(portal.length()>0 && showPortalInfo==true ){ %>	
		<A class="add-new-buttn add-new-button-lg" href="<%=aszPortal%>/portalopportunities.jsp">Manage External Ministry Opportunities</A>
	<% }else{ %>
		<a class="add-new-buttn" href="<%=aszPortal%>/memberbenefits.jsp">Apply for Premium Membership</a>
	<% } %>
	</center>
<% } %>
<% }else{ %>
    <div id="manageOrg">
    </div>
<% } %>	  
</div><!-- end div id="organization" -->

<div id="organizationopps">
	<hr color="#CCCCCC" width="100%" size="1">  
<% iNumber++; %>
	<h3 class="heading3_select"><%=iNumber%>. Select<%//=aszOrgOrChurchOpp%> Opportunity (Volunteering, Internships & Jobs):</h3>
	<center>
		<SELECT id="opportunities" name="opportunities" class"smalldropdown" style="width:200px;" >
			<OPTION value="" selected>-- click to select --</OPTION> 
			<logic:iterate id="opp" name="opplist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
				<% if(opp.getORGNID()==iCurrNID){ %>
					<option value="<%=opp.getOPPNID()%>"><%=opp.getOPPTitle()%></option>
				<% } %>
			</logic:iterate>
		</SELECT>
		&nbsp; <h3 style="display: inline; margin-right:10px;">OR</h3>
		<div id="addnewopplink" style="display:inline;">
			<A class="add-new-buttn add-new-button-lg" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iCurrNID%>&requesttype=<%=aszByContact%>" >Add New Opportunity</A>
		</div>
	</center>
	<br/>
	<h4 class="heading4_manage">Manage <%=aszOrgOrChurchOpp%> Opportunity:</h4>
	<div id="advmanagelistingslink" style="display:inline;">
		<a href="<%=aszPortal%>/org.do?method=showOrgManageListings&orgnid=<%=iCurrNID%>">Manage All</a>
	</div>
	&nbsp; | &nbsp;
	<div id="editopplink" style="display:inline;">
		<A href="javascript:editOpportunity(<%=iCurrNID%>,'edit<%=aszByContact%>')">Edit</A>
	</div>
	&nbsp; | &nbsp;
	<div id="editoppcontactslink" style="display:inline;">
		<A href="javascript:showOppContacts(<%=iCurrNID%>,'edit<%=aszByContact%>')">Contacts</A>
	</div>
	&nbsp; | &nbsp;
	<div id="prevopplink" style="display:inline;">
		<A href="javascript:editOpportunity(<%=iCurrNID%>,'view<%=aszByContact%>')">Preview</A>
	</div>
	&nbsp; | &nbsp;
	<div id="deleteopplink" style="display:inline;">
		<A href="javascript:editOpportunity(<%=iCurrNID%>,'edit<%=aszByContact%>')">Delete</A>
	</div>
	&nbsp; | &nbsp;
	<div id="duplicopplink" style="display:inline;">
		<A href="javascript:duplicOpportunity(<%=iCurrNID%>,'edit<%=aszByContact%>')" >Duplicate</A>
	</div>
	<br/><br/>




	<br/>
<% iNumber++; %>

<% 
// iterate through aOrgNidAndPortal; each element in the list is a_aszOrgNidAndPortalGet; find a_aszOrgNidAndPortalGet[1] where a_aszOrgNidAndPortalGet[0]==iCurrNID
//Iterator<String[]> // - defined in code earlier in page 
itrOrgNidAndPortal = aOrgNidAndPortal.iterator();
OrgNidAndPortalElement=new String[2];
while (itrOrgNidAndPortal.hasNext()) {
	OrgNidAndPortalElement = itrOrgNidAndPortal.next();
	aszTempOrgNid = OrgNidAndPortalElement[0];
	aszTempPortal = OrgNidAndPortalElement[1];
	try{
		iTempOrgNid=Integer.parseInt(aszTempOrgNid);
	}catch(NumberFormatException e){
		iTempOrgNid=0;
	}catch(Exception ex){
		iTempOrgNid=0;
	}
	if(iTempOrgNid == iCurrNID && aszTempPortal.length()>0){
		aszOrgPortalName = aszTempPortal;
	}
}
out.print("<!--  ");
	out.print("portal is : "+portal);
	if( aszOrgPortalName.length() > 0 ) aszOrgPortalName = "/" + aszOrgPortalName;
	out.print("     aszOrgPortalName is : "+aszOrgPortalName);
	if(portal.contains("/voleng")){
		out.print("portal is : "+portal);
		aszOrgPortalName = "/voleng" + aszOrgPortalName;
		out.print("aszOrgPortalName is : "+aszOrgPortalName);
	}
out.print(" -->");
if( aszOrgPortalName.length() > 0 && !(aszOrgPortalName.equalsIgnoreCase(portal))){ // show link to manage portal; user is not necessarily currently in this organization's portal 
%>
	<hr color="#CCCCCC" width="100%" size="1"> 
	<h3 class="heading3_select"><%=iNumber%>. Manage Your <%=aszOrgOrChurch%>'s Directory Portal</h3>
	<br/>
	<center>
		<div id="createportallink" style="padding-left:35px;">
			<a  class="add-new-buttn add-new-button-lg" href="<%=aszOrgPortalName%>/org.do?method=showOrgManagement&orgnid=<%=iCurrNID%>">Manage Portal</a>
		</div>
	</center>
	<br/>
<% }else if( portal.length()>0 && aszOrgPortalName.equalsIgnoreCase(portal) ){%>
	<hr color="#CCCCCC" width="100%" size="1"> 
	<h3 class="heading3_select"><%=iNumber%>. Manage Portal Appearance</h3>
	<br/>
	<center>
		<a class="add-new-buttn add-new-button-lg" href="<%=portal%>/org.do?method=showPortalManage&orgnid=<%=iCurrNID%>">Manage Portal</a>
	</center>
	<br/>
<% }else if(portal.length()==0){ %>
	<hr color="#CCCCCC" width="100%" size="1"> 
	<% iNumber++; %>
	<h3 class="heading3_select"><%=iNumber%>. Mass Import Multiple Volunteer Opportunities Through Excel:</h3>
	<br/>
	<center>
		<a class="add-new-buttn add-new-button-lg" href="http://www.urbanministry.org/import-opps">Import Opportunities</a>
	</center>
	<br/>
<% } %>






<% if(portal.length()>0 && showPortalInfo==true){ %>
	<hr color="#CCCCCC" width="100%" size="1"> 
<% iNumber++; %>
	<h3 class="heading3_select"><%=iNumber%>. Promote Your <% if(iNumber==4){ out.print("Organization's ");} %><%=aszOrgOrChurchOpp%> Listings:</h3>
<% } %>

<% if((portal.length()>0 && showPortalInfo==true) || ( aszOrgPortalName.length() > 0 && !(aszOrgPortalName.equalsIgnoreCase(portal)))){ %>	
<% }else{ %>
	<% if( showPortalInfo==true ){ 
	// ask if they want to convert to a portal.  if yes, either bring them to the edit with that pre-selected, or an independent page that just sets up the portals
	%>
	<hr color="#CCCCCC" width="100%" size="1"> 
<% iNumber++; %>
	<h3 class="heading3_select"><%=iNumber%>. Promote Your <% if(iNumber==4){ out.print("Organization's ");} %><%=aszOrgOrChurchOpp%> Listings:</h3>

	<div id="ask_portal_info">
		<br clear="all" />
		<div class="left-column-wide" id="directory_div">
			Would you like to have a Volunteer Directory Portal on ChristianVolunteering branded for your <%=aszOrgOrChurch%>?
			<br>
			<em>ie: ChristianVolunteering.org/Your<%=aszOrgOrChurch%>Name/</em>
		</div>
		<div class="right-column" id="createportallink">
			<a  class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showPortalManage&orgnid=<%=iCurrNID%>">Yes, Create Portal</a>
		</div>
		<br clear="all" />
	</div>
	<% } %>




<p style="font-size:11px; font-style:italic;">Paste your <%=aszOrgOrChurch.toLowerCase()%>'s service opportunity listings right into your own webpage or blog by including the code below (like embedding YouTube videos). You may adjust the width, height and scrollbar options to your fit once you have pasted the above code into your site.</p>

<table cellpadding="10" cellspacing="0" border="0">
<tr>
<td  style="border-right: 1px solid #CCC;" align="center" valign="top">
<img width="200" src="/imgs/mock-up_img.jpg" />
</td>
<td valign="top">

<%
String aszPortalLinkEmbed = aszPortal;
if(aszHostID.equalsIgnoreCase("volengchurch")){
	if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
		aszPortalLinkEmbed = aszPortal.substring(7,aszPortal.length());
	}	
}

if(aszSubdomain.equalsIgnoreCase("ChurchVolunteering.org")){
	aszSubdomain="www."+aszSubdomain;
}

String aszShowListings = "orglistings"+iCurrNID+".jsp";

String aszSearchPage = "oppsrch.do?method=showIncludeSearch";
String aszDimensions = "WIDTH=\"650\" HEIGHT=\"400\"";
String aszSearchDimensions = "WIDTH=\"400\" HEIGHT=\"225\"";
%>

<script type="text/javascript">
function toggle_embedcontent(){
	if (document.getElementById('embed_div').style.display=='none'){
		document.getElementById('embed_div').style.display='block';
		document.getElementById('embedcollapse').className = "collapsible";
	}else{
		document.getElementById('embed_div').style.display='none';
		document.getElementById('embedcollapse').className = "collapsed";
	}
}
</script>
<style>
#embedcollapse.collapsed .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-collapsed.png") no-repeat scroll 155px 50% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
#embedcollapse.collapsible .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-expanded.png") no-repeat scroll 155px 65% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
</style>

<div id="embed_div" style="display:inline;">
	<b>Would you like to: </b>
	<div id="embed_div_inner" style="display:inline;">
		<select id="embedselect" name="embedselect" size="1" class="smalldropdown" style="width: 175px;" onchange="update_embed(this, 'orglistings<%=iCurrNID%>.jsp')"> 
			<option value="embed_listurl"  selected="selected">Link to URL of Your Listings</option> 
			<option value="embed_list">Embed All Your Listings</option> 
			<option value="embed_preview">Embed a Preview of Your Listings</option> 
			<option value="embed_searchbox">Embed a Search Form on Your Site</option> 
		</select>
	</div>
	<br><br>

<script type="text/javascript">
// Created by: Matt Murphy | http://www.matts411.com/
// This script downloaded from www.JavaScriptBank.com
function autoSelect(selectTarget) {
  		if(selectTarget.tagName == 'TEXTAREA' || (selectTarget.tagName == "INPUT" && selectTarget.type == "text")) {
  			 selectTarget.select();
  		} else if(window.getSelection) { // FF, Safari, Opera
   			var sel = window.getSelection();
   			var range = document.createRange();
   			range.selectNode(selectTarget.firstChild);
   			sel.removeAllRanges();
   			sel.addRange(range);
  		} else { // IE
   			document.selection.empty();
   			var range = document.body.createTextRange();
   			range.moveToElementText(selectTarget);
   			range.select();
  		}
}
function update_embed(embed_object, page){
	var dimensions='<%=aszDimensions%>';
	var embed_choice = embed_object.options[embed_object.selectedIndex].value;
	var sortbyembed= document.getElementById('sortby_embed');
	var sortby_choice_embed= sortbyembed.options[sortbyembed.selectedIndex].value;
	var preview= document.getElementById('preview').value;
	var bckgrnd= document.getElementById('bckgrnd').value;
	var brdr= document.getElementById('brdr').value;
	var atxt= document.getElementById('atxt').value;
	var txt= document.getElementById('txt').value;
	if(bckgrnd.charAt(0)=='#'){
		bckgrnd=bckgrnd.substring(1,bckgrnd.length);
	}
	if(brdr.charAt(0)=='#'){
		brdr=brdr.substring(1,brdr.length);
	}
	if(atxt.charAt(0)=='#'){
		atxt=atxt.substring(1,atxt.length);
	}
	if(txt.charAt(0)=='#'){
		txt=txt.substring(1,txt.length);
	}
	switch(embed_choice){
		case 'embed_preview':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display="inline";
			document.getElementById('preview_row').style.display="table-row";
			document.getElementById("sort_by_row").style.display = 'table-row';
			page=page;
			dimensions='<%=aszDimensions%>';
			break;
		case 'embed_list':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display="inline";
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'table-row';
			page=page;
			dimensions='<%=aszDimensions%>';
			preview='';
			break;
		case 'embed_listurl':
			document.getElementById('listurl').style.display="inline";
			document.getElementById('iframe_list').style.display="none";
			document.getElementById('div_iframe').style.display="none";
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'none';
			page=page;
			dimensions='<%=aszDimensions%>';
			preview='';
			break;
		case 'embed_searchbox':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display="inline";
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'none';
			page='<%=aszSearchPage%>';
			dimensions='<%=aszSearchDimensions%>';
			preview='';
			break;
		default:
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display="inline";
			document.getElementById('preview_row').style.display="none";
<% if( aszCurrentURL.contains("oppsrch.do?method=proccess") || aszCurrentURL.contains("showChurchListings") ){ %>
			document.getElementById("sort_by_row").style.display = 'table-row';
<% }else{ %>
			document.getElementById("sort_by_row").style.display = 'none';
<% } %>
			page=page;
			dimensions='<%=aszDimensions%>';
			preview='';
			break;
	}

	var iframeinput = "<iframe id=\"content\" "+ dimensions +" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/"+ page +"&skipbanner=skipbanner&preview=" + preview + 
			"&searchkey1="+sortby_choice_embed + "&bckgrnd=" + bckgrnd +
			"&brdr=" + brdr + "&atxt=" + atxt + "&txt=" + txt + "\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
	autoSelect(document.getElementById('orglistings'));
}
function changeIframeText(page){
	var sortbyembed= document.getElementById('sortby_embed');
	var sortby_choice_embed= sortbyembed.options[sortbyembed.selectedIndex].value;
	var preview= document.getElementById('preview').value;
	var bckgrnd= document.getElementById('bckgrnd').value;
	var brdr= document.getElementById('brdr').value;
	var atxt= document.getElementById('atxt').value;
	var txt= document.getElementById('txt').value;
	if(bckgrnd.charAt(0)=='#'){
		bckgrnd=bckgrnd.substring(1,bckgrnd.length);
	}
	if(brdr.charAt(0)=='#'){
		brdr=brdr.substring(1,brdr.length);
	}
	if(atxt.charAt(0)=='#'){
		atxt=atxt.substring(1,atxt.length);
	}
	if(txt.charAt(0)=='#'){
		txt=txt.substring(1,txt.length);
	}
	var iframeinput = "<iframe id=\"content\" WIDTH=\"650\" HEIGHT=\"400\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/"+ page +"&skipbanner=skipbanner&preview=" + preview + 
			"&searchkey1="+sortby_choice_embed + "&bckgrnd=" + bckgrnd +
			"&brdr=" + brdr + "&atxt=" + atxt + "&txt=" + txt + "\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
}
function showPreviewIframe(){
	var iframeinput= document.getElementById('orglistings').value;
	document.getElementById('iframe_preview').innerHTML = iframeinput;
}
function show_embed_div(){
	document.getElementById("embed_div").style.display = 'inline';
}
</script>

<div id="iframe_list">
<table cellpadding="2" cellspacing="0" border="0">

<tr id="preview_row" style="display:none;">
	<td>Number of Listings: </td>
	<td>
		<div id="previewnuminput">
			<input type="text" value="" name="preview" id="preview" onchange="changeIframeText('orglistings<%=iCurrNID%>.jsp')">
		</div>
	</td>
</tr>

<tr>
	<td>Background Color: </td>		
	<td>
		<div id="colorbckgrndinput">
			<input type="text" value="#FFFFFF" name="bckgrnd" id="bckgrnd" onchange="changeIframeText('orglistings<%=iCurrNID%>.jsp')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of border: </td>		
	<td>
		<div id="colorbordrinput">
			<input type="text" value="#000000" name="brdr" id="brdr" onchange="changeIframeText('orglistings<%=iCurrNID%>.jsp')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of link text: </td>	
	<td>
		<div id="colorlinktxtinput">
			<input type="text" value="#003366" name="atxt" id="atxt" onchange="changeIframeText('orglistings<%=iCurrNID%>.jsp')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of text: </td>		
	<td>
		<div id="colortxtinput">
			<input type="text" value="black" name="txt" id="txt" onchange="changeIframeText('orglistings<%=iCurrNID%>.jsp')">
		</div>
	</td>
</tr>
<tr id="sort_by_row"><td>Sort Results By: </td>		
<td colspan=3>
	<SELECT id="sortby_embed" name="searchkey1" class="smalldropdown"  onchange="changeIframeText()" style="width: 200px;"> 
        <option value="">Popularity</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
<% if(aszCurrentURL.contains("postalcode") ){ %>
		<option value="distance">Distance</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity (Annually)</option>
    </SELECT>
</td>
</tr>
</table>
</div>

</td></tr>
<tr><td colspan=2>

	<div id="listurl" style="display:none;">
		<span class="label_embeds">URL:&nbsp;</span>
		<input type="text" name="organizationlistings" size="90" styleClass="textinputwhite" value="http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/volunteerlistings.jsp"/>
	</div>
	<div id="div_iframe">
	<br>
	<b>Place the following code into your site:&nbsp;</b>
		<div id="iframe_text">
		<input onclick="autoSelect(this)" type="text" name="orglistings" id="orglistings" size="90" styleClass="textinputwhite" 
		value='<iframe id="content" WIDTH="650" HEIGHT="400" name="content" frameborder="0" scrolling="yes" 
		src="http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/<%=aszShowListings%>&skipbanner=skipbanner"  style="overflow: auto;"></iframe>'/>
		</div>
	</div>
	<br>
	<button type="button" id="prviewIframe" onclick="showPreviewIframe()">Preview Embed</button>
	
	<div id="iframe_preview">
	</div>

	<div id="search_box" style="display:none;">
	<b>Add this search to your site:&nbsp;</b>
<textarea cols="100" rows="10" wrap="off">

</textarea>
	</div>


</td>
</tr>
</table>	
<% } %>
		
		</div>
        
<% if(portal.length()>0 && showPortalInfo==true ){ 
	if(aszMemberType.equalsIgnoreCase("Church") ){ 
		aszSubdomain="www.ChurchVolunteering.org";
	}
	if(bFaith==true){ 
		aszCurrentURL = "/oppsrch.do?method=showChurchListings"; 
	}else{ 
		aszCurrentURL = "/oppsrch.do?method=showPortalListings"; 
	} 
%>
	<%@include file="/template_include/embed_options.inc"%>
<% } %>

      </div>
	
<br><br>
<br clear="all">    
</form>

<% } %>
   
<% }else{ %>
<style>
.left-sidebar-org-widget {
    float: none;
    margin-left: auto;
    margin-right: auto;
}
</style>
	<%@ include file="/template_include/left_sidebar_org.inc" %>

<% } %>
    </div>
    </div>
<script language="javascript">

$(document).ready(function() {
<% if(showPortalInfo==true ){ // portal.length()>0 && %>	
	document.getElementById("embed_div").style.display = 'inline';
<% } %>
showorg(<%=iTempNID%>, 'opportunities' );
 }); 

</script>

<!-- start sidebar information -->
<//%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- Google Code for ChristianVolunteering.org Registered Organization Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "y02aCMTdiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=y02aCMTdiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
