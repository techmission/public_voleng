<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<%@page import="java.util.Iterator"%>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

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
			"src=\"http://<%=aszSubdomain%>/"+portal+"oppsrch.do?method=processOppSearchAllPaste&orgnid="+ inid +"p&skipbanner=skipbanner\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
}

if(portal.length>0 ){	
	document.getElementById('createportallink').innerHTML = '<a  class="add-new-buttn add-new-button-lg" href="/'+portal+'org.do?method=showOrgManagement&orgnid='+inid+'">Manage Portal</a>';
}else{
<% if(portal.length()<1 && showPortalInfo==true ){ %>	
	document.getElementById('createportallink').innerHTML = '<a  class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showPortalManage&orgnid='+inid+'">Yes, Create Portal</a>';
<% } %>
}
	document.getElementById('embed_div_inner').innerHTML = '<select id="embedselect" name="embedselect" size="1" class="smalldropdown" style="width: 175px;" onchange="update_embed(this, \'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')"> ' +
		'	<option value="embed_list">Embed All Your Listings</option> ' +
		'	<option value="embed_preview">Embed a Preview of Your Listings</option> ' +
		'	<option value="embed_listurl">Link to URL of Your Listings</option> ' +
		'	<option value="embed_searchbox">Embed a Search Form on Your Site</option> ' +
		'</select>';
	document.getElementById('previewnuminput').innerHTML = '<input type="text" value="" name="preview" id="preview" onchange="changeIframeText(\'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')">';
	document.getElementById('colorbckgrndinput').innerHTML = '<input type="text" value="#FFFFFF" name="bckgrnd" id="bckgrnd" onchange="changeIframeText\'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')">';
	document.getElementById('colorbordrinput').innerHTML = '<input type="text" value="#000000" name="brdr" id="brdr" onchange="changeIframeText(\'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')">';
	document.getElementById('colorlinktxtinput').innerHTML = '<input type="text" value="#003366" name="atxt" id="atxt" onchange="changeIframeText(\'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')">';
	document.getElementById('colortxtinput').innerHTML = '<input type="text" value="black" name="txt" id="txt" onchange="changeIframeText(\'oppsrch.do?method=processOppSearchAllPaste&orgnid='+inid+'\')">';
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
	  <span id="title">Share on Website</span>
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
<span style="float: left;">Share on Website</span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> account summary</a></div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>
<div id="body">

<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
	<div class="manageorg_container">
<% }else{ %>
	<div class="manageorg_container wide">
<% } %>

<%
int iHighNID=0;
String aszCurrName="", aszAlphName="";

if(aOrgNids.size()<1){
%>
<% }else{ %>

<div id="organizationopps">
	<h3 class="heading3_select">Promote Your <%=aszOrgOrChurchOpp%> Listings:</h3>


<%
String aszPortalLinkEmbed = aszPortal;
if(aszPortalLinkEmbed.equals("/voleng")) aszPortalLinkEmbed="";
if(aszHostID.equalsIgnoreCase("volengchurch")){
	if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
		aszPortalLinkEmbed = aszPortal.substring(7,aszPortal.length());
	}	
}
if(aszPortalLinkEmbed.length()<1){
	if(aCurrentUserObj.getNatlAssociationPortal().length()>0){
		aszPortalLinkEmbed = "/"+aCurrentUserObj.getNatlAssociationPortal();
	}
}
if(aszSubdomain.equalsIgnoreCase("ChurchVolunteering.org")){
	aszSubdomain="www."+aszSubdomain;
}

String aszShowListings = "oppsrch.do?method=processOppSearchAllPaste&orgnid="+iCurrNID+"";

String aszSearchPage = "oppsrch.do?method=showIncludeSearch";
String aszDimensions = "WIDTH=\"650\" HEIGHT=\"400\"";
String aszSearchDimensions = "WIDTH=\"400\" HEIGHT=\"225\"";

String aszListingsLink = "http://" + aszSubdomain + "/" + aszShowListings;
if(aszPortalLinkEmbed.length() < 0){ 
	aszListingsLink = "http://" + aszSubdomain + aszPortalLinkEmbed + "/volunteerlistings.jsp";
}
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
//alert(page);
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

<p style="font-size:11px; font-style:italic;">Paste your <%=aszOrgOrChurch.toLowerCase()%>'s service opportunity listings right into your own webpage or blog by including the code below (like embedding YouTube videos). You may adjust the width, height and scrollbar options to your fit once you have pasted the above code into your site.</p>

<table cellpadding="10" cellspacing="0" border="0">
<tr>
<td  style="border-right: 1px solid #CCC;" align="center" valign="top">
<img width="200" src="/imgs/mock-up_img.jpg" />
</td>
<td valign="top">
<div id="embed_div" style="display:inline;">
	<div id="embed_div_wouldyoulike" style="display:none;">
		<b>Would you like to: </b>
		<div id="embed_div_inner" style="display:inline;">
			<select id="embedselect" name="embedselect" size="1" class="smalldropdown" style="width: 175px;" onchange="update_embed(this, 'oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')"> 
				<option value="embed_listurl">Link to URL of Your Listings</option> 
				<option value="embed_list">Embed All Your Listings</option> 
				<option value="embed_preview">Embed a Preview of Your Listings</option> 
				<option value="embed_searchbox">Embed a Search Form on Your Site</option> 
			</select>
		</div>
		<br><br>
	</div>

<div id="iframe_list" style="display:none;">
<table cellpadding="2" cellspacing="0" border="0">

<tr id="preview_row" style="display:none;">
	<td>Number of Listings: </td>
	<td>
		<div id="previewnuminput">
			<input type="text" value="" name="preview" id="preview" onchange="changeIframeText('oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')">
		</div>
	</td>
</tr>

<tr>
	<td>Background Color: </td>		
	<td>
		<div id="colorbckgrndinput">
			<input type="text" value="#FFFFFF" name="bckgrnd" id="bckgrnd" onchange="changeIframeText('oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of border: </td>		
	<td>
		<div id="colorbordrinput">
			<input type="text" value="#000000" name="brdr" id="brdr" onchange="changeIframeText('oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of link text: </td>	
	<td>
		<div id="colorlinktxtinput">
			<input type="text" value="#003366" name="atxt" id="atxt" onchange="changeIframeText('oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')">
		</div>
	</td>
</tr>
<tr>
	<td>Color of text: </td>		
	<td>
		<div id="colortxtinput">
			<input type="text" value="black" name="txt" id="txt" onchange="changeIframeText('oppsrch.do?method=processOppSearchAllPaste&orgnid=<%=iCurrNID%>')">
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
		<input type="text" name="organizationlistings" size="90" styleClass="textinputwhite" value="<%=aszListingsLink%>"/>
		<br />
	</div>
	<div id="div_iframe" style="display:none;">
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

   
    </div>
    </div>

<script language="javascript">

$(document).ready(function() {
	document.getElementById("embed_div").style.display = 'inline';
	hash_changed();
showorg(<%=iTempNID%>, 'opportunities' );
 }); 

$(window).bind('hashchange', function() {
	hash_changed();
});

function hash_changed() {
	var url_hash = window.location.hash;
	if(url_hash.length > 1){
		url_hash = url_hash.substring(1);
		$('#embedselect').val(url_hash);
		$('#embedselect').change();
	}
}
</script>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
