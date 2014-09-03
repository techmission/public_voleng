<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="theStatus" name="orgForm" property="errormsg" type="java.lang.String"/>
<%
int iCount=0;
String aszClass="";
int[] a_iContainer= new int[1];
int iArraySize = 0;
int iTechMissionMember=778, iCCDAMember=22118;
boolean bNatlAssoc = false;

if(aszPortal.equals("/voleng")) portal="";
else if(session.getAttribute(tempPortal+"_type") != null ) if(session.getAttribute(tempPortal+"_type").toString().length() > 0) {
	portal = "";
	bNatlAssoc=true;
	aszOrgOrChurch="Association";
}

String thereWasError;
if(theStatus.equalsIgnoreCase("")){
  thereWasError = "?neworg&method=processcreateorg";
}else{
  thereWasError = "?orgnew&method=processcreateorg";
}

String actionURL="/org.do"; 
if (aszPath.equalsIgnoreCase("/voleng2/nonp-start44.jsp")){ 
	actionURL = actionURL + thereWasError; 
}

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}
%>


<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>


<%

String aszHeaderTags = org.getPortalHeaderTags();
if(aszHeaderTags.length()<1){
	aszHeaderTags="<title>"+org.getORGOrgName()+" ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>";
}
String aszCSS = org.getPortalCSS();
if(aszCSS.length()<1){
	aszCSS="body {\n"+
		"    font-family: Arial,Helvetica,Verdana,sans-serif;\n"+
		"    font-size: 12px;\n"+
		"}\n\r"+
		"#maincontent {\n"+
		"    background-color: #EFEFEF;\n"+
		"    color: #000000;\n"+
		"    width: 772px;\n"+
		"}\n\r"+
		"#breadcrumbs {\n"+
		"    background-color: #FFFFFF;\n"+
		"    color: #000000;\n"+
		"    font-size: 11px;\n"+
		"}\n\r"+
		"#breadcrumbs a {\n"+
		"    color: #000000;\n"+
		"    text-decoration: underline;\n"+
		"    font-weight: normal;\n"+
		"}\n\r"+
		"a {\n"+
		"    color: #000000;\n"+
		"    font-weight: inherit;\n"+
		"}\n\r"+
		"#advanced_layout_collapse.collapsible .fieldset-legend {\n"+
		"    border-bottom: 1px solid #728DD4;\n"+
		"    color: #728DD4;\n"+
		"    font-size: 16px;\n"+
		"    font-weight: bold;\n"+
		"}\n\r"+
		"#advanced_layout_collapse.collapsed .fieldset-legend {\n"+
		"    border-bottom: 1px solid #728DD4;\n"+
		"    color: #728DD4;\n"+
		"    font-size: 16px;\n"+
		"    font-weight: bold;\n"+
		"}\n\r"+
		"#location h2, #orgchurchinfo h2, #additionalinfo h2 {\n"+
		"    border-bottom: 1px solid #728DD4;\n"+
		"}\n\r"+
		"h2 {\n"+
		"    color: #728DD4;\n"+
		"    font-size: 16px;\n"+
		"    font-weight: bold;\n"+
		"}\n\r"+
		"h3 {\n"+
		"    color: #596E9F;\n"+
		"    font-size: 14px;\n"+
		"}\n\r"+
		"hr {\n"+
		"    color:#596e9f;\n"+
		"    width:auto;\n"+
		"}";
}
String aszFooter = org.getPortalFooter();
if(aszFooter.length()<1){
	aszFooter="<br><a href=&quot;"+aszPortal+"/register.do?method=showHome&quot;><span>Home</span></a>&nbsp;&nbsp;|&nbsp;	";
	if(  false == LoginBean.IsSessionLoggedIn( request ) ){ 
		aszFooter+="<a href=&quot;"+aszPortal+"/login.jsp&quot; ><span>Login</span></a>&nbsp;&nbsp;|&nbsp;" +	
			"<a href=&quot;"+aszPortal+"/individualregistration.jsp&quot;><span>Create Account</span></a>&nbsp;&nbsp;|&nbsp;";	
	} 
		aszFooter+="<a href=&quot;"+aszPortal+"/advancedsearch.jsp&quot;><span>Search</span></a>&nbsp;&nbsp;|&nbsp;"+	
			"<a href=&quot;"+aszPortal+"/volunteerlistings.jsp&quot;><span>All Opportunities</span></a>&nbsp;&nbsp;|&nbsp;"+	
			"<a href=&quot;"+aszPortal+"/organizationlistings.jsp&quot;><span>All "+aszOrgOrChurchPlural+"</span></a>";	
	
	if(  true == LoginBean.IsSessionLoggedIn( request ) ){ 
		if( 
				LoginBean.getLoggedInUserType( request ).equalsIgnoreCase("Organization") ||
				LoginBean.getLoggedInUserType( request ).equalsIgnoreCase("Church") 
		){
	        aszFooter+="&nbsp;|&nbsp;&nbsp;<a href=&quot;"+aszPortal+"/orgmanagement.jsp&quot;><span>Manage "+aszOrgOrChurch+"</span></a>";
		}else{
	        aszFooter+="&nbsp;|&nbsp;&nbsp;<a href=&quot;"+aszPortal+"/register.do?method=showIndAcctSum1&quot;><span>My Account</span></a>";
		} 
		aszFooter+="&nbsp;|&nbsp;&nbsp;<a href=&quot;"+aszPortal+"/register.do?method=logout&quot;><span>Logout</span></a>";
	} 
}

int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, 
		vidIntlVols=342, vidPrimaryOppTypes=349;
int iTakesIntlVolsTID=22119, iDoesNotTakeIntlVolsTID=22120;
int iTemp=0;
String aszTakesIntlVolsTID=iTakesIntlVolsTID + "";
String aszDoesNotTakeIntlVolsTID=iDoesNotTakeIntlVolsTID + "";

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aPrimaryOppTypesList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList aLanguageList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aPrimaryOppTypesList, vidPrimaryOppTypes );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyWeightCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

boolean siteInterestDisabled = 
  !(org.getORGAddrCountryName().equalsIgnoreCase("us") ||
    org.getORGAddrCountryName().equalsIgnoreCase("ca"));

String aszSiteInterestYes = "";
if(org.getSiteInterest())
  aszSiteInterestYes += "checked ";
if(siteInterestDisabled)
  aszSiteInterestYes += "disabled";

String aszSiteInterestNo = "";
if(!org.getSiteInterest())
  aszSiteInterestNo += "checked ";
if(siteInterestDisabled)
  aszSiteInterestNo += "disabled";

String aszLocalAffil="display: none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
   aszLocalAffil="display: inline;";
}

String aszNumServed="";
int iNumServed = org.getORGNumServed();
if(iNumServed > 0){
	aszNumServed=""+iNumServed;
}

String aszNumVolOrg="";
int iNumVolOrg = org.getORGNumVolOrg();
if(iNumVolOrg > 0){
	aszNumVolOrg=""+iNumVolOrg;
}

String aszNumVolOrgIntl="";
int iNumVolOrgInt = org.getORGNumVolOrgIntl();
if(iNumVolOrgInt > 0){
	aszNumVolOrgIntl=""+iNumVolOrgInt;
}

String aszMemberType = org.getORGMembertype();


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
out.print("<!-- aCurrentUserObj.getUSPSiteUseType() is: "+aCurrentUserObj.getUSPSiteUseType()+" -->");
if(aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
	bOrg=true;
}

String aszOneThirdPov = org.getORGOnethirdP();
int iInternational = org.getORGTakesIntlVolsTID();
String aszFormalTrain = org.getORGFormalTrain();

String aszChrisNonP="",aszChrisChurch="",aszNonPNonC="",aszChrisBus="";
if(aszMemberType.equalsIgnoreCase("Parachurch") ){
	aszChrisNonP="checked";
out.print("<!-- aszChrisNonP member type is: "+aszMemberType+" and "+org.getORGMembertypeTID()+" -->");
}else if(aszMemberType.equalsIgnoreCase("Christian Business") ){
	aszChrisBus="checked";
out.print("<!-- aszChrisBus member type is: "+aszMemberType+" and "+org.getORGMembertypeTID()+" -->");
}else if(aszMemberType.equalsIgnoreCase("Church") ){// aszPortal.length()>0){ 


	aszPortalReqFields = "";
//	aszPortalStyleBold = "";
	
	
	aszChrisChurch="checked";
out.print("<!-- aszChrisChurch member type is: "+aszMemberType+" and "+org.getORGMembertypeTID()+" -->");
}else if(aszMemberType.equalsIgnoreCase("Non-Profit (Non-Christian)") ){ 
	aszNonPNonC="checked";
out.print("<!-- aszNonPNonC member type is: "+aszMemberType+" and "+org.getORGMembertypeTID()+" -->");
} 

%>

<script language="javascript">
function clickListings(listings){
	var value=listings.value;
	if(value=='Yes'){
		document.getElementById('listings_link').style.display='block';
	}else{
		document.getElementById('listings_link').style.display='none';
	}
}
function checkDirectoryNameFormat(dir){
	var element=dir.value;
	var subSection=element.substring(4,0);
	if( subSection=='http' || subSection=='www.' ){
		alert('This is a directory path added to our normal domain to create a portal site for you.  Please do not enter a full Web URL Address.');
		$('input:text[name=portalname]').val('<%=org.getPortalNameOrig()%>');
	}
}

 
function display_additionalinfocontent(){
	document.getElementById('advanced_layout').style.display='block';
	document.getElementById('advanced_layout_collapse').className = "collapsible";
}

function toggle_additionalinfocontent(){
	if (document.getElementById('additionalinfocontent').style.display=='none'){
		document.getElementById('additionalinfocontent').style.display='block';
		document.getElementById('additionalinfocollapse').className = "collapsible";
	}else{
		document.getElementById('additionalinfocontent').style.display='none';
		document.getElementById('additionalinfocollapse').className = "collapsible";
	}
}
 
function display_additionalinfocontent(){
	document.getElementById('additionalinfocontent').style.display='block';
	document.getElementById('additionalinfocollapse').className = "collapsible";
}


function toggle_showFaith(){
	var siteUse = $('input:radio[name=orgchrismembertype]:checked').val();
	var hasPortal = $('input:radio[name=hasportal]:checked').val();
	if (siteUse=="Church"){
		document.getElementById('faith').style.display='inline';
		document.getElementById('portal_info').style.display='inline';
		document.getElementById('directory_div').className = "left-column-wide portal-bold";
		document.getElementById('onethirdpov_div').className = "left-column-wide";
		document.getElementById('visa_div').className = "left-column-wide";
		document.getElementById('progtype_div').className = "left-column";
		document.getElementById('numvolorg_div').className = "left-column-wide";
		document.getElementById('numvolorgintl_div').className = "left-column-wide";
		document.getElementById('denomaffil_div').className = "left-column portal-bold";
<% if(showPortalInfo==true && bFaith) { %>
	document.getElementById('hasportal_yes').checked=true;
	document.getElementById('hasportal_no').checked=false;
	$('input:radio[name=hasportal]').filter('[value=Yes]').attr('checked', true);
<% }else if(bFaith==true) { %>
	document.getElementById('hasportal_yes').checked=true;
	document.getElementById('hasportal_no').checked=false;
	$('input:radio[name=hasportal]').filter('[value=Yes]').attr('checked', true);
<% } %>
	}else if (siteUse=="Parachurch"){
		document.getElementById('faith').style.display='inline';
		document.getElementById('portal_info').style.display='none';
		document.getElementById('directory_div').className = "left-column-wide";
		document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
		document.getElementById('visa_div').className = "left-column-wide portal-bold";
		document.getElementById('progtype_div').className = "left-column portal-bold";
		document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
		document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
		document.getElementById('denomaffil_div').className = "left-column";
	}else{
		document.getElementById('faith').style.display='none';
		document.getElementById('portal_info').style.display='none';
		document.getElementById('directory_div').className = "left-column-wide";
		document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
		document.getElementById('visa_div').className = "left-column-wide portal-bold";
		document.getElementById('progtype_div').className = "left-column portal-bold";
		document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
		document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
		document.getElementById('denomaffil_div').className = "left-column";
	}
}


$(document).ready(function() {

<% if(bFaith==true){ %>
	$('input:hidden[name=orgchrismembertype]').val('Church');
	clicked_church();
<% } %>
<% if(aszMemberType.equalsIgnoreCase("Parachurch") ){ %>
	$('#orgchrismembertype_chrisnonp').attr('checked','checked');
<% }else if(aszMemberType.equalsIgnoreCase("Non-Profit (Non-Christian)") ){ %>
	$('#orgchrismembertype_nonpnonchris').attr('checked','checked');
<% } %>

<% if(aszOneThirdPov.equalsIgnoreCase("Yes") ){ %>
	$('#onethirdpov_yes').attr('checked','checked');
<% }else if(aszOneThirdPov.equalsIgnoreCase("No") ){ %>
	$('#onethirdpov_no').attr('checked','checked');
<% } %>

<% if(iInternational ==iTakesIntlVolsTID){ %>
	$('#intlvols_yes').attr('checked','checked');
<% }else if(iInternational==iDoesNotTakeIntlVolsTID){ %>
	$('#intlvols_no').attr('checked','checked');
<% } %>

<% if(aszFormalTrain.equalsIgnoreCase("Yes") ){ %>
	$('#orgformaltrain_yes').attr('checked','checked');
<% }else if(aszFormalTrain.equalsIgnoreCase("No") ){ %>
	$('#orgformaltrain_no').attr('checked','checked');
<% } %>

	var hasPortal = $('input:radio[name=hasportal]:checked').val();

	var siteUse = $('input:radio[name=orgchrismembertype]:checked').val();
console.log('siteUse is '+siteUse);	
	if (siteUse=="Non-Profit (Non-Christian)"){
		document.getElementById('faith').style.display='none';
	}else {
		document.getElementById('faith').style.display='inline';
	}


<%
if(aszMemberType.equalsIgnoreCase("Church") ){// aszPortal.length()>0){ 
%>
	document.getElementById('directory_div').className = "left-column-wide portal-bold";
	document.getElementById('onethirdpov_div').className = "left-column-wide";
	document.getElementById('visa_div').className = "left-column-wide";
	document.getElementById('progtype_div').className = "left-column-wide";
	document.getElementById('numvolorg_div').className = "left-column-wide";
		document.getElementById('numvolorgintl_div').className = "left-column-wide";
	document.getElementById('denomaffil_div').className = "left-column portal-bold";
<%
}else if(aszPortal.length()>0){ 
%>
	document.getElementById('directory_div').className = "left-column-wide portal-bold";
	document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
	document.getElementById('visa_div').className = "left-column-wide portal-bold";
	document.getElementById('progtype_div').className = "left-column portal-bold";
	document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
	document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
	document.getElementById('denomaffil_div').className = "left-column";
	<%}else{ %>
	document.getElementById('directory_div').className = "left-column-wide";
	document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
	document.getElementById('visa_div').className = "left-column-wide portal-bold";
	document.getElementById('progtype_div').className = "left-column portal-bold";
	document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
	document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
	document.getElementById('denomaffil_div').className = "left-column";
<%
}

// for churchvol, make this a collapsed block initially
if( bFaith==true ){
	if(
		org.getORGProgramTypes1TID() > 0		||
		aszOneThirdPov.equalsIgnoreCase("Yes")	||
		iInternational ==iTakesIntlVolsTID		||
		org.getORGNumVolOrg() > 0				||
		org.getORGDonateURL().length() > 0		||
		org.getORGFedTaxId().length() > 0		||
		org.getORGNumServed() > 0				||
		aszFormalTrain.equalsIgnoreCase("Yes")
	){
%>
	display_additionalinfocontent();
<% }} %>
	
 });
 
</script>

<style>
.portal-bold{
	font-weight:bold;
}
.better-select div.form-checkboxes-scroll {
  border: 1px solid #666;
  max-height: 95px;
  max-width: 400px;
  overflow: auto;
  word-wrap: break-word;
}
.better-select div.form-checkboxes-noscroll {
  border: 1px solid #666;
  max-width: 620px;
  overflow: hidden;
  word-wrap: break-word;
}
.better-select div.form-item {
  max-width: 620px;
  margin-left: auto ;
  margin-right: auto ;
}

#advanced_layout_collapse.collapsed .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-collapsed.png") no-repeat scroll 5px 50% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
#advanced_layout_collapse.collapsible .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-expanded.png") no-repeat scroll 5px 65% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}


#additionalinfocollapse.collapsed .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-collapsed.png") no-repeat scroll 5px 50% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
#additionalinfocollapse.collapsible .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-expanded.png") no-repeat scroll 5px 65% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}

<% if( !(aszNarrowTheme.equalsIgnoreCase("true"))  ) { %>
#form{
	margin-left: 20px;
}
<% } %>

#form .left-column-wide{
	float: left;
	padding: 3px;
	width: 290px;
	text-align:right;
}
#form .left-column{
	float: left;
	padding: 3px;
	width: 290px;
	line-height:2em;
	text-align:right;
}
#form .right-column{
	float: left;
	//float:none;
	padding: 3px;
	//padding:2px 0;
}
#form .span-columns{
	padding: 10px;
/*	float: left; */
}
#location #form .left-column{
	line-height:1em;
}
#location h2, #orgchurchinfo h2, #additionalinfo h2 {
	border-bottom: 1px solid #728DD4; 
	padding-bottom:3px;
}
.criticaltext {padding:3px;}

#progressbar {7px 0;}
#progressbar div{
		color:#ffffff;
    font-size: 14px;
    font-weight: bold;
		margin-right:0;
		padding:3px 7px 0;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
	background-color: #79A44E;
    font-size: 14px;
}
.accountboxoff{
	background-color: #BBBBBB;
    font-size: 14px;
}
.accounton{
	color: #003366;
	text-decoration:none;
}
.accountoff{
	color: #BBBBBB;
}

#progressbar div{
		color:#ffffff;
    font-size: 14px;
    font-weight: bold;
		margin-right:0;
		padding:3px 7px 0;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
		background-color: #79A44E;
    font-size: 14px;
}
.accountboxoff{
	background-color: #BBBBBB;
    font-size: 14px;
}
.accounton{
	color: #003366;
	text-decoration:none;
}
.accountoff{
	color: #BBBBBB;
}

</style>

<form action="<%=aszPortal%><%=actionURL%>" focus="addr1" name="orgForm" method="post">

<input type="hidden" name="method" value="processcreateorg" />
<input type="hidden" name="requesttype" value="create" />
<input type="hidden" name="orgname" value="<%=org.getORGOrgName()%>" />
<!--input type="hidden" name="postalcode" /-->
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<% if(bFaith==true){ %>
<input type="hidden" name="orgchrismembertype" id="orgchrismembertype"  value="Church"/>
<% } %>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title"><%=aszOrgOrChurch%> Profile</span>
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
<span style="float: left;">Register an <%=aszOrgOrChurch%> </span>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> management</a> &gt; register an <%=aszOrgOrChurch.toLowerCase()%>  </div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
<% // for google analytics tracking: %>
	<% String aszGoalPage = "/organization/create/step2"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>
  

	<!-- START PROGRESSBAR -->
<DIV id="progressbar">
<% if(bFaith==true ){ %>      
<table cellpadding="2"><tr>
	<td><div class="accountboxon">1</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accounton">Add <%=aszOrgOrChurch%><br>Profile</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">2</div></td>
		<td><a href="<%=aszPortal%>/churchopportunities.jsp" class="accountoff">Choose from Existing Organizations<br>& Service Opportunties</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">3</div></td>
		<td><a href="#" class="accountoff">Create Own <%=aszOrgOrChurch%><br><%=aszOrgOrChurchOpp%> Opportunities</a></td>
</tr></table>

<% }else{ %>
<table cellpadding="2"><tr>
	<td><div class="accountboxoff">1</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accountoff">Check Status</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxon">2</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accounton">Add <%=aszOrgOrChurch%> Profile</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxoff">3</div></td>
		<td><a href="#" class="accountoff">Add <%=aszOrgOrChurch%> <%=aszOrgOrChurchOpp%><br>Opportunity Listing</a></td>
</tr></table>
<% } %>
</DIV>
	
	<!-- END PROGRESSBAR -->

<hr style="color:#728DD4;" />

Please note that this <%=aszOrgOrChurch.toLowerCase()%> will submitted for moderation and won't be listed publicly until it has been approved.
<br />
	
<p><strong>NOTE: The more complete you fill out your organization profile below, the more often your opportunities will appear on our website, so please fill in as much information as possible.</strong></strong></p>

<p><span class= "criticaltext">*</span><span style="font-weight: bold"> Please complete all required fields below.</span></p>

<div id="form">

	<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="orgForm" property="errormsg" /></pre></FONT>
	
	
	<div class="left-column-wide">
		<%=aszOrgOrChurch%> Name &nbsp;
	</div>
	<div class="right-column">
		<b><bean:write name="orgForm" property="orgname" /></b>
	</div>
	<div class="cleardiv"></div>
<% if(bFaith==false ){ %>	
	<div class="left-column" style="font-weight:bold;">
		Organization Type <span class="criticaltext">*</span>
	</div>
	<div class="right-column">
		<div class="left-column-small">
			<input type="radio" styleClass="check" value="Parachurch" name="orgchrismembertype" <%=aszChrisNonP%> id="orgchrismembertype_chrisnonp" onclick="toggle_showFaith()"/> Christian Non-Profit/Parachurch 
			<br />
			<input type="radio" styleClass="check" value="Non-Profit (Non-Christian)" name="orgchrismembertype" <%=aszNonPNonC%> id="orgchrismembertype_nonpnonchris" onclick="toggle_showFaith()" /> Non-Profit (Non-Christian)
		</div>
		<div class="right-column">
<% //if(bOrg==false){ %>
			<input type="radio" styleClass="check" value="Church" name="orgchrismembertype" <%=aszChrisChurch%> id="orgchrismembertype_church" onclick="toggle_showFaith()"/> Church
			<br />
<% //} %>
			<input type="radio" styleClass="check" value="Christian Business" name="orgchrismembertype" <%=aszChrisBus%> id="orgchrismembertype_chrisbus" onclick="toggle_showFaith()" /> Christian Business
		</div>
	</div>
	<div class="cleardiv"></div>
<% } %>	



	<div id="orgchurchinfo">
		<br>
		
		<h2><%=aszOrgOrChurch%> Information</h2>
	
		<div class="left-column" style="font-weight:bold;">
			Mission Statement: &nbsp; <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help#mission_statement')">help</A>
			<span class="criticaltext">*</span></div>
      <div class="right-column">
<textarea name="orgmissionstatement" rows="5" cols="68" styleClass="textinputwhite"/><%=org.getORGMissionStatement()%></textarea>
		</div>
		<div class="cleardiv"></div>

		<div class="left-column-wide">
			<%=aszOrgOrChurch%> Description: &nbsp; <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
			<BR />(Do not list volunteer opportunities here.)<br></div>
      <div class="right-column">
<textarea name="orgdescription" rows="5" cols="68" styleClass="textinputwhite"/><%=org.getORGOrgDescription()%></textarea>
		</div>
		<div class="cleardiv"></div>

		<div id="faith"
<% 			if(		(aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 	&&
					(! aszOrgOrChurch.equalsIgnoreCase("Church"))
){ %>
	style="display: none;"
<% } %>
	>
			<div class="left-column">
				<%=aszOrgOrChurch%> Statement of Faith:<br></div>
        <div class="right-column">
<textarea name="orgstmtfaith" rows="5" cols="68" styleClass="textinputwhite"/><%=org.getORGOrgStmtFaith()%></textarea>
			</div>
			<div class="cleardiv"></div>

	
<% if( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") ){%>
	<div class="left-column-wide portal-bold" id="denomaffil_div">	
				Denominational Affiliation:<span class="criticaltext">*</span>
<% }else{ %>
	<div class="left-column-wide" id="denomaffil_div">	
				Denominational Affiliation:
<% } %>				
		<a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
	<div class="right-column"> 
		<div id="better-select-edit-taxonomy-<%=vidDenomAffil%>" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes form-checkboxes-scroll org-page">
<%
a_iContainer= new int[150];
iTemp = 0;
a_iContainer = org.getDenominationalAffilsArray();
for(int index=0; index < afiliationList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
%>
<div id="edit-taxonomy-<%=vidDenomAffil%>-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-<%=vidService%>-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="denomtidsarray" name="denomtidsarray" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-scroll"
	<%
	for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
		if(a_iContainer[indexArray]==iSubType) out.print(" checked ");
	}
	%> 
		><%=aAppCode.getAPCDisplay()%>
	</label>
</div>
<%
	iTemp++;
}
%>
				</div>
			</div>
		</div>
	</div>
	<br clear="all" />

	
	<div class="left-column-wide">	
		Association/Other Affiliations: <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
	<div class="right-column">
		<div id="better-select-edit-taxonomy-<%=vidOrgAffil%>" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes form-checkboxes-scroll org-page">
<%
a_iContainer= new int[150];
iTemp = 0;
a_iContainer = org.getOrgAffiliationsArray();
for(int index=0; index < apartnersList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();

	if(iSubType!=iTechMissionMember && iSubType != iCCDAMember){
%>
<div id="edit-taxonomy-<%=vidOrgAffil%>-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-<%=vidService%>-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="orgaffiltidsarray" name="orgaffiltidsarray" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-scroll"
	<%
		for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
			if(a_iContainer[indexArray]==iSubType) out.print(" checked ");
		}
	%> 
		><%=aAppCode.getAPCDisplay()%>
	</label>
</div>
<%
		iTemp++;
	}
}
%>
				</div>
			</div>
		</div>
	</div>
	<br clear="all" />
			
		</div><!-- end faith div -->
	
	</div><!-- end orgchurchinfo div -->




<%
// move to NOT required later expanded section for churchvol
if( ! bFaith==true){
%>
	<div class="left-column-wide nonportal-bold" id="onethirdpov_div">
		Are more than one-third of the participants in your <%=aszOrgOrChurch.toLowerCase()%>/programs low income (below 150% of the poverty level)?  Feel free to estimate. <%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="onethirdpov" id="onethirdpov_yes"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="onethirdpov" id="onethirdpov_no" /> No
	</div>
	<div class="cleardiv"></div>
	<br>
	
	<div class="left-column-wide nonportal-bold" id="visa_div">
		Are you willing to assist volunteers in getting a visa to travel to your opportunities if they are outside the country?<%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="<%=aszTakesIntlVolsTID%>" name="intlvols" id="intlvols_yes"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="<%=aszDoesNotTakeIntlVolsTID%>" name="intlvols" id="intlvols_no"/> No
	</div>
	<div class="cleardiv"></div>
	
<div class="cleardiv"></div>
	
	<div class="left-column-wide nonportal-bold" id="numvolorg_div">
		Number of volunteers placed by your organization<br />(annually) <%=aszPortalReqFields%>
    </div>
		<div class="right-column" style="padding-top:10px;">
    <input type="text"  name="orgnumvol" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrg%>"/>
	</div>
<!-- 	
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numvolorgint_div">
		Volunteers & Interns Placed from/to Foreign Countries<br> (annually) <%=aszPortalReqFields%>
    </div>
	<div class="right-column">
    <input type="text"  name="orgnumvolintl" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrgIntl%>"/>
	</div>
-->	
<!-- -->	
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numopps_div">
		Number of Volunteer Opportunities
    </div>
	<div class="right-column">
    <input type="text"  name="numopps" size="5" styleClass="textinputwhite" value=""/>
	</div>
<!-- -->	
	<div class="cleardiv"></div>
	

<% 
}	// end move to NOT required later expanded section for churchvol
%>


	<div id="location">
		<br>
		
		<h2><%=aszOrgOrChurch%> Location Information</h2>
	
		<div class="left-column" style="font-weight:bold;">
			Address 1 <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="addr1" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrLine1()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			Address 2
		</div>
		<div class="right-column">
			<input type="text"  name="addr2" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrLine2()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column" style="font-weight:bold;">
			City <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="city" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrCity()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			State/Province
		</div>
		<div class="right-column">
			<SELECT id="state" name="state" class="smalldropdown"> 
				<option value="" selected></option>
				<%
					aszTemp= org.getORGAddrStateprov();
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						out.println(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
					}
				%>
			</SELECT><!--&nbsp; &nbsp;Other input type="text"  name="prov" size="10" styleClass="textinputwhite"/-->
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			Postal Code <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="postalcode" size="10" styleClass="textinputwhite" value="<%=org.getORGAddrPostalcode()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			Country
		</div>
		
		<script>
		  function domesticEIN() {
		    $('div#EIN').html(
			  '<input type="text" name="ein_1" value="<%= org.getORGFedTaxId1() %>" size=2 maxlength=2 onkeyup="if(this.value.length == 2) $(this).next().focus();"/> - <input type="text" name="ein_2" value="<%= org.getORGFedTaxId2() %>" size=7 maxlength=7/>'
			);
			$('div#EIN_description').html(
			  'Employer Identification Number (EIN)<br/><i>Don\'t know your EIN? You can look it up <a href="http://www2.guidestar.org/" target="_blank">here</a>.</i>'
			);
		  }
		  
		  function foreignEIN() {
		    $('div#EIN').html(
              '<input type="text" name="ein_1" value="<%= org.getORGFedTaxId1() + (org.getORGFedTaxId2() != null && org.getORGFedTaxId2().length() > 0 ? "-" + org.getORGFedTaxId2() : "") %>" />'
			);
		    $('div#EIN_description').html(
			  'Charity ID'
			);
		  }
		  
		  function enableSiteInterest() {
		    $('#site_interest_yes').removeAttr('disabled');
			$('#site_interest_no').removeAttr('disabled');
		  }
		  
		  function disableAndUncheckSiteInterest() {
		    $('#site_interest_yes').attr('disabled', true);
			$('#site_interest_no').attr('disabled', true);
			
			$('#site_interest_yes').removeAttr('checked');
			$('#site_interest_no').attr('checked', true);
		  }
		
		  function countryOnChange(value) {
		    if(value == "us")
			  domesticEIN();
			else
			  foreignEIN();
			  
			if(value == "us" || value == "ca")
			  enableSiteInterest();

			else 
			  disableAndUncheckSiteInterest(); 
		  }

		</script>
		
		<div class="right-column">
			<SELECT id="country" name="country" class="smalldropdown" onChange="countryOnChange(this.value);"> 
				<%
					aszTemp=org.getORGAddrCountryName();
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column" style="font-weight:bold;">
			Phone # for Volunteer Inquiries <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="phone1" size="30" styleClass="textinputwhite" value="<%=org.getORGOrgPhone1()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			Secondary Organization Phone #
		</div>
		<div class="right-column">
			<input type="text"  name="phone2" size="30" styleClass="textinputwhite" value="<%=org.getORGOrgPhone2()%>"/>
		</div>
		<br clear="all" />
	
	
		<div class="left-column">
			Fax
		</div>
		<div class="right-column">
			<input type="text"  name="fax1" size="30" styleClass="textinputwhite" value="<%=org.getORGFax1()%>"/>
		</div>
		<div class="cleardiv"></div>
	
		<div class="left-column">
			Website
		</div>
		<div class="right-column">
			<input type="text"  name="orgwebpage" size="30" styleClass="textinputwhite" value="<%=org.getORGWebpage()%>"/>
		</div>
		<div class="cleardiv"></div>
	</div>

<%
// move to NOT required later expanded section for churchvol
if( ! bFaith==true){
%>

	<div class="left-column">
		<%=aszOrgOrChurch%> Donation URL
	</div>
	<div class="right-column">
		<input type="text"  name="orgdonateurl" size="24" styleClass="textinputwhite" value="<%=org.getORGDonateURL()%>"/>
	</div>
	<div class="cleardiv"></div>

	<div class="left-column" id="EIN_description"></div>
	<div class="right-column" id="EIN"></div>
	
	<script>
	  <% if(org.getORGAddrCountryName() == null || org.getORGAddrCountryName().length() <= 0 || org.getORGAddrCountryName().toLowerCase().equals("us")) { %>
	    domesticEIN();
	  <% } else  { %>
	    foreignEIN();
	  <% } %>
	    
	</script>
	
	<div class="cleardiv"></div>


<% 
}	// end move to NOT required later expanded section for churchvol
%>



	<div id="additionalinfo">
		<br>
		

<%
// for churchvol, make this a collapsed block initially
if( bFaith==true){
%>
		<div id="additionalinfocollapse" onclick="toggle_additionalinfocontent()" class=" collapsible">
			<legend class="collapse-processed">
				<span class="fieldset-legend">&nbsp;&nbsp;&nbsp;&nbsp;Additional Information</span>
			</legend>
		</div>

<% }else{ %>
		<h2>Additional Information</h2>
<% } %>

	



<%
// for churchvol, make this a collapsed block initially
if( bFaith==true){
%>
	<div id="additionalinfocontent" style="display:none;">

<% }else{ %>
	<div id="additionalinfocontent">
<% } %>


<br clear="all"/>
	
<div id="better-select-edit-taxonomy-<%=vidPrimaryOppTypes%>" class="better-select">
	<div class="left-column nonportal-bold" id="progtype_div">
		Primary Types of Volunteer Opportunities <span class="criticaltext">*</span>
	</div>
	<div class="right-column">
		<div class="form-checkboxes form-checkboxes-noscroll">
<table ><tr><td>
              <%
					int[] a_iTypesOfOppsArray= new int[100];
					iTemp = 0;
					a_iTypesOfOppsArray = org.getTypesOfOppsArray();
						for(int index=0; index < aPrimaryOppTypesList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPrimaryOppTypesList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if(iSubType==32162){
								aszDisplay = "Local Volunteer Opportunities";
							}else if(iSubType==32163){
								aszDisplay = "Planning/Hosting Domestic Missions Trips<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(within the Same Country)";
							}else if(iSubType==32164){
								aszDisplay = "Sending/Hosting International Missionaries";
							}else if(iSubType==32165){
								aszDisplay = "Virtual Volunteering (from home)";
							}else if(iSubType==32225){
								aszDisplay = "Short-Term Missions";
							}
							out.println(" <div id=\"edit-taxonomy-"+ vidPrimaryOppTypes+"-"+ iSubType + "-wrapper\" class=\"form-item\"> " );
							out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidPrimaryOppTypes+"-"+ iSubType + "\" > " );
							out.print("	<input type=\"checkbox\"  name=\"typesofoppstidsarray\" id=\"typesofoppstidsarray[" + iSubType + "]\" value=\"" + iSubType + "\" " );
							for(int indexArray=0; indexArray < a_iTypesOfOppsArray.length; indexArray++){
								iTemp = a_iTypesOfOppsArray[indexArray];
								if(iTemp==iSubType) out.print(" checked ");
							}
							out.println(" /> ");
							out.println(aszDisplay);
							out.println("	</label>");
							out.println("</div>");
							iTemp++;
							if(index==4)	out.println("</td><td>");
						}
				%>
</td></tr></table></div>
	</div>
<br>
</div>
	<br clear="all" />


<%
// move to NOT required later expanded section for churchvol
if( bFaith==true){
%>
	
	<div class="left-column-wide" id="onethirdpov_div">
		Are more than one-third of the participants in your <%=aszOrgOrChurch.toLowerCase()%>/programs low income (below 150% of the poverty level)?  Feel free to estimate. <%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="onethirdpov" id="onethirdpov_yes"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="onethirdpov" id="onethirdpov_no" /> No
	</div>
	<div class="cleardiv"></div>
	
	
	<div class="left-column-wide" id="visa_div">
		Are you willing to assist volunteers in getting a visa to travel to your opportunities if they are outside the country?<%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="<%=aszTakesIntlVolsTID%>" name="intlvols" id="intlvols_yes"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="<%=aszDoesNotTakeIntlVolsTID%>" name="intlvols" id="intlvols_no"/> No
	</div>
	<div class="cleardiv"></div>
	
	<div class="left-column-wide nonportal-bold" id="numvolorg_div">
		Number of volunteers placed by your organization<br />(annually) <%=aszPortalReqFields%>
    </div>
		<div class="right-column" style="padding-top:10px;">
    <input type="text"  name="orgnumvol" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrg%>"/>
	</div>
<!-- 
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numvolorgint_div">
		Volunteers & Interns Placed from Foreign Countries<br> (annually) <%=aszPortalReqFields%>
    </div>
	<div class="right-column">
    <input type="text"  name="orgnumvolintl" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrgIntl%>"/>
	</div>
-->	
<!-- -->	
	<div class="left-column-wide nonportal-bold" id="numopps_div">
		Number of Volunteer Opportunities
    </div>
	<div class="right-column">
    <input type="text"  name="numopps" size="5" styleClass="textinputwhite" value=""/>
	</div>
<!-- -->	
	<div class="cleardiv"></div>
	
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Do You Have a Listing of Your Volunteer Opportunities<br>on Your <%=aszOrgOrChurch%>'s Website?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="haslistings" id="haslistings_yes" onclick="clickListings(this)"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="haslistings" id="haslistings_no" onclick="clickListings(this)" /> No
	</div>
	<div class="cleardiv"></div>
<div id="listings_link" style="display:none;">
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Web Address of <%=aszOrgOrChurch%>'s<br>Volunteer Opportunity Listings:
	</div>
	<div class="right-column">
    <input type="text"  name="listingsurl" size="30" styleClass="textinputwhite" value=""/>
	</div>
	<div class="cleardiv"></div>
</div>	
	<br>





	<div class="left-column">
		<%=aszOrgOrChurch%> Donation URL
	</div>
	<div class="right-column">
		<input type="text"  name="orgdonateurl" size="24" styleClass="textinputwhite" value="<%=org.getORGDonateURL()%>"/>
	</div>
	<div class="cleardiv"></div>

	<div class="left-column">
		EIN: (Employer Identification Number)
	</div>
	<div class="right-column">
		<input type="text"  name="orgfedtaxid" size="30" styleClass="textinputwhite" value="<%=org.getORGFedTaxId()%>"/>
	</div>
	<div class="cleardiv"></div>


<% 
}	// end move to NOT required later expanded section for churchvol
%>

		<div class="left-column-wide">
			Number of People Served by This <%=aszOrgOrChurch%> Annually (Feel Free to Estimate)
		</div>
		<div class="right-column">
			<input type="text"  name="orgnumserved" size="5" styleClass="textinputwhite" value="<%=aszNumServed%>"/>
		</div>
		<br clear="all" />
	
		<div class="left-column-wide">
			Do You Require Formal Orientation Training for Volunteers?
		</div>
		<div class="right-column">
			<input type="radio" styleClass="check" value="Yes" name="orgformaltrain" id="orgformaltrain_yes"/> Yes 
			&nbsp; &nbsp; 
			<input type="radio" styleClass="check" value="No" name="orgformaltrain" id="orgformaltrain_no"/> No
		</div>
		<div class="cleardiv"></div>

	
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Do You Have a Listing of Your Volunteer Opportunities<br>on Your <%=aszOrgOrChurch%>'s Website?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="haslistings" id="haslistings_yes" onclick="clickListings(this)"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="haslistings" id="haslistings_no" onclick="clickListings(this)" /> No
	</div>
	<div class="cleardiv"></div>
<div id="listings_link" style="display:none;">
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Web Address of <%=aszOrgOrChurch%>'s<br>Volunteer Opportunity Listings:
	</div>
	<div class="right-column">
		<input type="text"  name="listingsurl" size="30" styleClass="textinputwhite" value=""/>
	</div>
	<div class="cleardiv"></div>
</div>	
	<br>

<% if(bFaith==false ){ %>	
    <div class="span-columns">
ChristianVolunteering.org has a <a href="http://www.cityvision.edu/cms/cv/site-application" target="_new">City Vision Internship program</a> where we can provide a full-time (35hours/week) intern at a ministry for a year. The ministry pays $3,500 plus room/board (or equivalent stipend).  Would your organization be interested in finding out more about this program?  
&nbsp;&nbsp;&nbsp;&nbsp;
		<input <%=aszSiteInterestYes%> type="radio" styleClass="check" value="1" name="site_interest" id="site_interest_yes" /> Yes
		&nbsp;&nbsp;&nbsp;
		<input <%=aszSiteInterestNo%> type="radio" styleClass="check" value="0" name="site_interest" id="site_interest_no" /> No 	
	</div>
	<div class="cleardiv"></div>
<% } %>	
	</div><!-- end additionalinfocontent -->
	</div><!-- end additionalinfo div-->

<br>
	<div class="left-column">
	</div>
	<div class="right-column">
<% if(bFaith==true){ %>
		<INPUT TYPE="image" SRC="/imgs/next-step_button.png" BORDER="0" ALT="next step">
<% }else{ %>
		<INPUT class=submit type=submit value=Continue>
<% } %>
	</div>

</div> <!-- end form div -->
</div> <!-- end body div -->

</div> <!-- end maincontent div -->

</form>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %>
<!-- end footer information -->

