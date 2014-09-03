<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<%
int iTemp=0;
int vidService=32, vidPosFreq=268;
int iNumber=0;

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

int iCurrNID=0;
if(!(request.getParameter("orgnid")==null)){
	String aszNID = (String) request.getParameter("orgnid");
	if(aszNID.length() > 1){
		iCurrNID = Integer.parseInt(aszNID);
	}
}

String aszNatlAssoc = "";
String aszNatlAssocNID = "";
try{
	aszNatlAssoc=request.getAttribute("natlassocname").toString();
	request.removeAttribute("natlassocname");
}catch(NullPointerException e){}
try{
	aszNatlAssocNID=request.getAttribute("natlassocnid").toString();
	request.removeAttribute("natlassocnid");
}catch(NullPointerException e){}

%>

<div style="display:none;">
	<logic:iterate id="orgnids" name="assocorglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
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
      %>
    </logic:iterate>
</div>

<script language="javascript">

function showorg(inid,newlist){
	var oppElem = document.getElementById('opportunitieslist'+inid); 
	var oppIndex = oppElem.selectedIndex;
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
	
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

	var iframeinput = "<iframe id=\"content\" WIDTH=\"650\" HEIGHT=\"400\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%><%=aszPortal%>/orglistings"+ inid +".jsp&skipbanner=skipbanner\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;

<% if(portal.length()<1 && showPortalInfo==true ){ %>	
	document.getElementById('createportallink').innerHTML = '<a  class="add-new-buttn" href="<%=aszPortal%>/org.do?method=showPortalManage&orgnid='+inid+'">Yes, Create Portal</a>';
<% } %>
}

$(document).ready(function() {
<% if(showPortalInfo==true ){ // portal.length()>0 && %>	
	document.getElementById("embed_div").style.display = 'inline';
<% } %>
 }); 

function remove_assoc(orgname, orgnid, assocnid){
	if( false == confirm("Are you sure you want to remove " + orgname + " from <%=aszNatlAssoc%>?\n") ){
		alert("" + orgname + " is still listed under <%=aszNatlAssoc%>");
		return;
	} 
	window.location.href=('<%=aszPortal%>/org.do?method=processRemoveAssociation&orgnid='+orgnid+'&nid='+assocnid+'&requesttype=edit<%=aszByContact%>');
}
</script>

<style>
h3, h4 {margin:0px;}

a.heading {
/*    color: #003366; */
    color: #596E9F;
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
}
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
#maincontent.left-sidebar-org #body {
    margin-top: -16px;
    padding: 0 3px 10px;
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
table#contact td {
	padding: 5px;
}
table#contact th {
	padding: 0px 5px;
}
.manageorg_container {
    background-color: #FFFFFF;
    border: 3px solid #93B6F0;
    margin: 25px auto;
    padding: 10px;
    width: auto;
}
.manageorg_container.wide {
    width: auto;
}
table#contact {
    margin-left: auto;
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
	  <span id="title">Manage National Association</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Manage National Association: <%=aszNatlAssoc%></span>
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

<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. <a href="<%=aszPortal%>/org.do?method=showPortalManage" class="add-new-buttn add-new-button-lg">Manage National Association Portal Styles</a></h3>

<!--a href="<%=aszPortal%>/org.do?method=showPortalManage" class="add-new-buttn add-new-button-lg">Manage National Association Portal</a-->

<br>
	<hr color="#CCCCCC" width="100%" size="1">  

<% if(aszPortal.contains("cityvision")){ %>
<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. <a href="<%=aszPortal%>/org.do?method=showCVInternManage" class="add-new-buttn add-new-button-lg">Manage City Vision Internships Portal</a></h3>

<br>
	<hr color="#CCCCCC" width="100%" size="1">  

<% } %>

<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. <A class="add-new-buttn add-new-button-lg" href="<%=aszPortal%>/portalopportunities.jsp">Manage Organizations Listed Under <%=aszNatlAssoc%> in Bulk</A></h3>
		
		<!--A class="heading" href="<%=aszPortal%>/portalopportunities.jsp">Manage External Ministry Opportunities</A-->


<br>
	<hr color="#CCCCCC" width="100%" size="1">  
<%
int iHighNID=0;
String aszCurrName="", aszAlphName="";

if(aOrgNids.size()<1){
%>
<% }else{ %>

<% iNumber++; %>
	<h3 class="heading3_select" style="display: inline;"><%=iNumber%>. Or Manage Individual Organizations Listed Under <%=aszNatlAssoc%>:</h3>
<% 
int iCount=0;
String aszClass="";
%><br><br>
<table id="contact" style="contact" border="0">
	<logic:iterate id="assocorglist" name="assocorglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
		<%
			iCount++;
			int iOrgNID=assocorglist.getORGNID();
      	if(assocorglist.getRequestType().equals("ByContact")){
      		iContactOrAdmin=1;
      		bByContact=true;
      		aszByContact="ByContact";
      	}else{
      		iContactOrAdmin=0;
      	}
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}else{
    			aszClass="";
    		}

String aszOrgNameNoQuote = assocorglist.getORGOrgName();
        if (aszOrgNameNoQuote==null){ 
       	    aszOrgNameNoQuote= "";
        }
	    if (aszOrgNameNoQuote.length()==0) {
			aszOrgNameNoQuote= "";
		}
	else{
		int pos=aszOrgNameNoQuote.indexOf("'");
        if(pos < 1) aszOrgNameNoQuote=aszOrgNameNoQuote;
		String temp="";
		while(pos>-1){
			temp+=aszOrgNameNoQuote.substring(0,pos)+"\\'";
			aszOrgNameNoQuote=aszOrgNameNoQuote.substring(pos+1);
			pos=aszOrgNameNoQuote.indexOf("'");
		}
		temp+=aszOrgNameNoQuote;
		aszOrgNameNoQuote= temp;
    }
		%>
	<tr class="<%=aszClass%>">
	<td>
		<%=iCount%>
	</td>
	<td width="175">
		<b><%=assocorglist.getORGOrgName()%></b>
	</td>
	<td align="center">
		<a href="<%=aszPortal%>/org.do?method=shownonpeditstep1&orgnid=<%=iOrgNID%>&reqtype=edit<%=aszByContact%>">Edit Organization</a>
	</td><td align="center">
		<a href="<%=aszPortal%>/org.do?method=shownonpvieworg&orgnid=<%=iOrgNID%>&requesttype=view<%=aszByContact%>">Preview Organization</a>
	</td><td align="center">
		<a href="<%=aszPortal%>/org.do?method=showOrgManageListings&orgnid=<%=iOrgNID%>&requesttype=<%=aszByContact%>">Manage Opportunities</a>
	</td><td align="center">
		<a href="<%=aszPortal%>/email.do?method=showVolunteerReferrals&listtype=orgmanagement&orgnid=<%=iOrgNID%>">View Referrals</a>
	</td><td align="center">
		<a onClick="remove_assoc('<%=aszOrgNameNoQuote%>',<%=iOrgNID%>,<%=aszNatlAssocNID%>)" href="#">Remove Association</a>
	</td><td>
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<%=iOrgNID%>&requesttype=edit<%=aszByContact%>" >Administrators</a>
	</td>
	</tr>
	</logic:iterate>
</table>

<br clear="all">
<br>
<% } %>

      </div>
	
<br><br>
<br clear="all">    
</form>

   
    </div>
    </div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
