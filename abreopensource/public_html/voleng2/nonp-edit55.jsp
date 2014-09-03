<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<%
int iCount=0;
String aszClass="";
int[] a_iContainer= new int[1];
int iArraySize = 0;
int iTechMissionMember=778, iCCDAMember=22118;

if(aszPortal.equals("/voleng")) portal="";
else if(session.getAttribute(tempPortal+"_type") != null ) if(session.getAttribute(tempPortal+"_type").toString().length() > 0) portal = "";

if(org.getORGNumVolOrg() < 0){
	org.setORGNumVolOrg(0);
}
if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}
boolean portalMethod=false;

String aszMemberType = org.getORGMembertype();
if(aszMemberType.length()<1){
	aszMemberType = aCurrentUserObj.getUSPSiteUseType();
}
boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") ||aszMemberType.equalsIgnoreCase("Church") ){
	bFaith=true;
	aszOrgOrChurch="Church";
	aszOrgOrChurchOpp="Ministry";
}
boolean bOrg=false;
if(aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
	bOrg=true;
}

String aszAdjective = "organizational";
if(aszOrgOrChurch.equalsIgnoreCase("Church")) aszAdjective = "church";

String aszPortalSectionDisplay="display:none;";

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
String tempDom="www.ChristianVolunteering.org";
if(aszHostID.equalsIgnoreCase("volengchurch")){ 
	tempDom="www.ChurchVolunteering.org"; 
} 

int iNumOppsOrg = org.getORGNumOppsOrg(); 
%>

<form action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=processEditOrg<%}%>" focus="addr1" name="orgForm"  method="post">
<input type="hidden" name="method" value="processEditOrg" />
<input type="hidden" name="requesttype" value="edit" />
<input type="hidden" name="orgnid" value="<%=org.getORGNID()%>"/>
<input type="hidden" name="orguid" value="<%=org.getORGUID()%>"/>
<input type="hidden" name="role" />
<input type="hidden" name="subdomain" value="<%=aszSecondaryHost%>" />
<% if(bFaith==true){ %>
<input type="hidden" name="orgchrismembertype" id="orgchrismembertype" value="Church" />
<% } %>
<input type="hidden" name="urlalias"  />

<% if(iNumOppsOrg>0){ %>
<input type="hidden" name="numopps" value="<%=""+iNumOppsOrg%>" />
<% } %>
<% /*
<input type="hidden" name="portalname"  value="< %=org.getPortalNameOrig()% >" /> 
<input type="hidden" id="portalheadertags" value="< %=aszHeaderTags% >" />
<input type="hidden" id="portalheader" value="< %=org.getPortalHeader()% >" />
<input type="hidden" id="aszCSS" value="< %=aszCSS% >" />
<input type="hidden" id="portalfooter" value="< %=aszFooter% >" />
*/ %>
<%
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
ArrayList aPositionTypesList = new  ArrayList ( 2 );
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
aCodes.getTaxonomyCodeList( aPositionTypesList, vidPosType );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyWeightCodeList( aPrimaryOppTypesList, vidPrimaryOppTypes );
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

String aszLocalAffil="display: none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
   aszLocalAffil="display: inline;";
}

String aszPartner="display: table-row;";
String aszOrgPartner = org.getORGPartner();
if(aszOrgPartner.equalsIgnoreCase("") || aszOrgPartner.equalsIgnoreCase("none")){
   aszPartner="display: none;";
}
String aszPartner2="display: table-row;";
String aszOrgPartner2 = org.getORGPartner2();
if(aszOrgPartner2.equalsIgnoreCase("") || aszOrgPartner2.equalsIgnoreCase("none")){
   aszPartner2="display: none;";
}
String aszPartner3="display: table-row;";
String aszOrgPartner3 = org.getORGPartner3();
if(aszOrgPartner3.equalsIgnoreCase("") || aszOrgPartner3.equalsIgnoreCase("none")){
   aszPartner3="display: none;";
}
String aszPartner4="display: table-row;";
String aszOrgPartner4 = org.getORGPartner4();
if(aszOrgPartner4.equalsIgnoreCase("") || aszOrgPartner4.equalsIgnoreCase("none")){
   aszPartner4="display: none;";
}

String aszFaith="display: table-row;";
String aszOrgFaith = org.getORGMembertype();
if(aszHostID.equalsIgnoreCase("volengchurch")){
   aszFaith="display: table-row;";
}else if(aszOrgFaith.equalsIgnoreCase("Non-Profit (Non-Christian)") || aszOrgFaith.equalsIgnoreCase("")){
   aszFaith="display: none;";
}

// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

String aszOneThirdPov = org.getORGOnethirdP();
int iInternational = org.getORGTakesIntlVolsTID();
String aszFormalTrain = org.getORGFormalTrain();

String aszTakesIntlVols="", aszDoesNotTakeIntlVols="";
String aszHasListings="", aszDoesNotHaveListings="";
String aszChrisNonP="",aszChrisChurch="",aszNonPNonC="",aszChrisBus="";
String aszOneThirdPovYes="", aszOneThirdPovNo="", aszFormTrainYes="", aszFormTrainNo="";
if(iInternational ==iTakesIntlVolsTID){ 
	aszTakesIntlVols="checked";
}else if(iInternational==iDoesNotTakeIntlVolsTID){ 
	aszDoesNotTakeIntlVols="checked";
}
if(org.getORGHasListings().equalsIgnoreCase("Yes")){ 
	aszHasListings="checked";
}else{ 
	aszDoesNotHaveListings="checked";
}
out.print("<!-- member type is: "+aszMemberType+" and "+org.getORGMembertypeTID()+" -->");
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
if(aszOneThirdPov.equalsIgnoreCase("Yes") ){ 
	aszOneThirdPovYes="checked";
}else if(aszOneThirdPov.equalsIgnoreCase("No") ){ 
	aszOneThirdPovNo="checked";
} 
if(aszFormalTrain.equalsIgnoreCase("Yes") ){ 
	aszFormTrainYes="checked";
}else if(aszFormalTrain.equalsIgnoreCase("No") ){
	aszFormTrainNo="checked";
} 
%>


<%
int[] a_iPositionTypesArray= new int[100];
iTemp = 0;
a_iPositionTypesArray = org.getPositionTypesArray();
for(int index=0; index < aPositionTypesList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPositionTypesList.get(index);
	if(null == aAppCode) continue;
	int iSubType = aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	for(int indexArray=0; indexArray < a_iPositionTypesArray.length; indexArray++){
		iTemp = a_iPositionTypesArray[indexArray];
		if(iTemp==iSubType){ 
			out.println(" <input type=\"hidden\" name=\"positiontypetidsarray\" value=\""+iTemp+"\"> " );
		}
	}
}
%>


<script language="javascript">
function deleteNonProfit(){
	if( false == confirm("Are you sure that you want to delete your <%=aszOrgOrChurch.toLowerCase()%>?\n") ){
		alert("Your <%=aszOrgOrChurch.toLowerCase()%> has NOT been deleted");
		return;
	} 
	document.orgForm.method.value = "deleteNonProfit";
<%if(aszPortal.length()>0){%>
	document.orgForm.action = '<%=aszPortal%>/org.do?method=deleteNonProfit';
<% } %>
	document.orgForm.requesttype.value = "edit";
	document.orgForm.submit();
}

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



$(document).ready(function() {

<%
if(aszMemberType.equalsIgnoreCase("Church") ){// aszPortal.length()>0){ 
%>
	document.getElementById('onethirdpov_div').className = "left-column-wide";
	document.getElementById('visa_div').className = "left-column-wide";
	document.getElementById('progtype_div').className = "left-column";
	document.getElementById('numvolorg_div').className = "left-column-wide";
	document.getElementById('numvolorgintl_div').className = "left-column-wide";
	document.getElementById('denomaffil_div').className = "left-column portal-bold";
<%
}else if(aszPortal.length()>0){ 
%>
	document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
	document.getElementById('visa_div').className = "left-column-wide portal-bold";
	document.getElementById('progtype_div').className = "left-column portal-bold";
	document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
	document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
	document.getElementById('denomaffil_div').className = "left-column";
<%
}else{ 
%>
	document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
	document.getElementById('visa_div').className = "left-column-wide portal-bold";
	document.getElementById('progtype_div').className = "left-column portal-bold";
	document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
	document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
	document.getElementById('denomaffil_div').className = "left-column";
<%
}

if(showPortalInfo==true && bFaith) { 
%>
//	$('input:radio[name=hasportal]:checked').val()='Yes';
//	$('#hasportal_yes').attr('checked','checked');
	document.getElementById('hasportal_yes').checked=true;
	document.getElementById('hasportal_no').checked=false;
<% }else{ %>
//	$('input:radio[name=hasportal]:checked').val()='Yes';
//	$('#hasportal_no').attr('checked','checked');
	document.getElementById('hasportal_yes').checked=false;
	document.getElementById('hasportal_no').checked=true;
<% 
} 
%>
	var hasPortal = $('input:radio[name=hasportal]:checked').val();
	if (hasPortal=="Yes"){
	}
<%
// for churchvol, make this a collapsed block initially
if( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") ){
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

function toggle_showFaith(){
		var siteUse = $('input:radio[name=orgchrismembertype]:checked').val();
		var hasPortal = $('input:radio[name=hasportal]:checked').val();
		if (siteUse=="Church"){
			document.getElementById('faith').style.display='inline';
			document.getElementById('portal_info').style.display='inline';
			document.getElementById('onethirdpov_div').className = "left-column-wide";
			document.getElementById('visa_div').className = "left-column-wide";
			document.getElementById('progtype_div').className = "left-column";
			document.getElementById('numvolorg_div').className = "left-column-wide";
			document.getElementById('numvolorgintl_div').className = "left-column-wide";
			document.getElementById('denomaffil_div').className = "left-column portal-bold";
		}else if (siteUse=="Parachurch"){
			document.getElementById('faith').style.display='inline';
			document.getElementById('portal_info').style.display='none';
			document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
			document.getElementById('visa_div').className = "left-column-wide portal-bold";
			document.getElementById('progtype_div').className = "left-column portal-bold";
			document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
			document.getElementById('numvolorgintl_div').className = "left-column-wide";
			document.getElementById('denomaffil_div').className = "left-column";
		}else{
			document.getElementById('faith').style.display='none';
			document.getElementById('portal_info').style.display='none';
			document.getElementById('onethirdpov_div').className = "left-column-wide portal-bold";
			document.getElementById('visa_div').className = "left-column-wide portal-bold";
			document.getElementById('progtype_div').className = "left-column portal-bold";
			document.getElementById('numvolorg_div').className = "left-column-wide portal-bold";
			document.getElementById('numvolorgintl_div').className = "left-column-wide portal-bold";
			document.getElementById('denomaffil_div').className = "left-column";
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
	//padding:0;
	width: 290px;
	text-align:right;
}
#form .left-column{
	float: left;
	padding: 3px;
	//padding:0;
	width: 290px;
	line-height:2em;
	text-align:right;
}
#form .right-column{
	float: left;
	//float:none;
	padding: 3px;
	//padding:0;
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
    font-size: 14px;
    font-weight: bold;
		height:1.2em;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
	background-color: #003366;
    font-size: 14px;
}
.accountboxoff{
	background-color: #999999;
    font-size: 14px;
}
.accounton{
	color: #003366;
}
.accountoff{
	color: #999999;
}
.portal #all_tabs a {padding:0 5px;}
.portal #all_tabs {padding:0 0 5px 0; height:26px;}

</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Edit <%=aszOrgOrChurch%> Profile for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Edit <%=aszOrgOrChurch%> Profile</span>
	<% } %>
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
	<% if(bAdminRole==true){ %>
  <span style="float: left;">Edit <%=aszOrgOrChurch%> Profile for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Edit <%=aszOrgOrChurch%> Profile</span>
	<% } %>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
&gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> management</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%=aszOrgOrChurch.toLowerCase()%> management </a>
	<% }else{ %>
	<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" > <A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" >manage this <%=aszOrgOrChurch.toLowerCase()%> </a> 
	<% } %>
&gt; edit <%=aszOrgOrChurch.toLowerCase()%> profile</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

  <div id="body">
<br>
<div id="form">
        <table><tr><td>
  <A href="javascript:deleteNonProfit()"><IMG SRC="http://www.christianvolunteering.org/imgs/delete.gif" border=0 alt="delete this non profit" width="20" height="20" ></A>&nbsp; </td>
	<td valign="top"><h3>Click the X to permanently delete this <%=aszOrgOrChurch.toLowerCase()%>.</h3>
	</td></tr></table>

	<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="orgForm" property="errormsg" /></pre></FONT>
	
<strong>NOTE: The more complete you fill out your organization profile below, the more often your opportunities will appear on our website, so please fill in as much information as possible.</strong></strong>
<br /><br />

	<div class="left-column-wide">
		<%=aszOrgOrChurch%> Name <span class="criticaltext">*</span> 
	</div>
	<div class="right-column">
		<b><input type="text" name="orgname" size="30" styleClass="textinputwhite" value="<%=org.getORGOrgName()%>"/></b>
	</div>
	<br clear="all" />

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
	<br clear="all" />
<% } %>	



	<div id="orgchurchinfo">
		
		<h2><%=aszOrgOrChurch%> Information</h2>
	
		<div class="left-column" style="font-weight:bold;">
			Mission Statement: &nbsp; <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help#mission_statement')">help</A>
			<span class="criticaltext">*</span></div>
      <div class="right-column">
<textarea name="orgmissionstatement" rows="5" cols="68" styleClass="textinputwhite"/><%=org.getORGMissionStatement()%></textarea>
		</div>
		<br clear="all" />

		<div class="left-column-wide">
			<%=aszOrgOrChurch%> Description: &nbsp; <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
			<BR />(Do not list volunteer opportunities here.)<br></div>
      <div class="right-column">
<textarea name="orgdescription" rows="5" cols="68" styleClass="textinputwhite"/><%=org.getORGOrgDescription()%></textarea>
		</div>
		<br clear="all" />

		<div id="faith"
<% 			if(		(aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 	 	&&
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
			<br clear="all" />

	
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
if( ! ( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") )){
%>
	<div class="left-column-wide nonportal-bold" id="onethirdpov_div">
		Are more than one-third of the participants in your <%=aszOrgOrChurch.toLowerCase()%>/programs low income (below 150% of the poverty level)?  Feel free to estimate. <%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="onethirdpov" id="onethirdpov_yes"<%=aszOneThirdPovYes%> /> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="onethirdpov" id="onethirdpov_no" <%=aszOneThirdPovNo%> /> No
	</div>

	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numvolorg_div">
		Number of volunteers placed by your organization<br />(annually) <%=aszPortalReqFields%>
    </div>
	<div class="right-column" style="padding-top:10px;">
    <input type="text"  name="orgnumvol" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrg%>"/>
	</div>
<!--
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numvolorgint_div">
		Volunteers & Interns Placed from Foreign Countries<br> (annually) &nbsp;
    </div>
	<div class="right-column">
    <input type="text"  name="orgnumvolintl" size="5" styleClass="textinputwhite" value="<%=aszNumVolOrgIntl%>"/>
	</div>
-->	
	<br clear="all" />
	<br />
	
	<div class="left-column-wide nonportal-bold" id="visa_div">
		Are you willing to assist volunteers in getting a visa to travel to your opportunities if they are outside the country?<%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="<%=aszTakesIntlVolsTID%>" name="intlvols" id="intlvols_yes" <%=aszTakesIntlVols%>/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="<%=aszDoesNotTakeIntlVolsTID%>" name="intlvols" id="intlvols_no" <%=aszDoesNotTakeIntlVols%>/> No
	</div>
	<br clear="all" />
	

<!-- -->	
<% if(iNumOppsOrg < 1){ %>
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numopps_div">
		Number of Volunteer Opportunities
    </div>
	<div class="right-column">
    <input type="text"  name="numopps" size="5" styleClass="textinputwhite" value="<%=""+iNumOppsOrg%>"/>
	</div>
<% } %>
<!-- -->	
	<div class="cleardiv"></div>
<% 
}	// end move to NOT required later expanded section for churchvol
%>
	
</div>

	<div id="location">
		<br>
		
		<h2><%=aszOrgOrChurch%> Location Information</h2>
	
		<div class="left-column" style="font-weight:bold;">
			Address 1 <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="addr1" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrLine1()%>"/>
		</div>
		<br clear="all" />
	
		<div class="left-column">
			Address 2
		</div>
		<div class="right-column">
			<input type="text"  name="addr2" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrLine2()%>"/>
		</div>
		<br clear="all" />
	
		<div class="left-column" style="font-weight:bold;">
			City <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="city" size="30" styleClass="textinputwhite" value="<%=org.getORGAddrCity()%>"/>
		</div>
		<br clear="all" />
	
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
		<br clear="all" />
	
		<div class="left-column">
			Postal Code
		</div>
		<div class="right-column">
			<input type="text"  name="postalcode" size="10" styleClass="textinputwhite" value="<%=org.getORGAddrPostalcode()%>"/>
		</div>
		<br clear="all" />
	
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
		<br clear="all" />
	
		<div class="left-column" style="font-weight:bold;">
			Phone # for Volunteer Inquiries <span class="criticaltext">*</span>
		</div>
		<div class="right-column">
			<input type="text"  name="phone1" size="30" styleClass="textinputwhite" value="<%=org.getORGOrgPhone1()%>"/>
		</div>
		<br clear="all" />
	
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
		<br clear="all" />
	
		<div class="left-column">
			Website
		</div>
		<div class="right-column">
			<input type="text"  name="orgwebpage" size="30" styleClass="textinputwhite" value="<%=org.getORGWebpage()%>"/>
		</div>
		<br clear="all" />
	</div>

	
<%
// move to NOT required later expanded section for churchvol
if( ! ( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") )){
%>
	<div class="left-column">
		<%=aszOrgOrChurch%> Donation URL
	</div>
	<div class="right-column">
		<input type="text"  name="orgdonateurl" size="24" styleClass="textinputwhite" value="<%=org.getORGDonateURL()%>"/>
	</div>
	<br clear="all" />

	<div class="left-column" id="EIN_description"></div>
	<div class="right-column" id="EIN"></div>
	
	<script>
	  <% if(org.getORGAddrCountryName() == null || org.getORGAddrCountryName().length() <= 0 || org.getORGAddrCountryName().toLowerCase().equals("us")) { %>
	    domesticEIN();
	  <% } else { %>
	    foreignEIN();
	  <% } %>    
	</script>
	
	<br clear="all" />
<% } %>


	<div id="additionalinfo">
		<br>
		

<%
// for churchvol, make this a collapsed block initially
if( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") ){
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
if( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") ){
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
%>
<div id="edit-taxonomy-<%=vidPrimaryOppTypes%>-<%=iSubType%>-wrapper" class="form-item">
	<label class="option" for="edit-taxonomy-<%=vidPrimaryOppTypes%>-<%=iSubType%>" />
<!-- NOTE******************************** make onclick="stm(this)" for the input when this element = stm, then show the labels/etc for the foreign/natl -->
		<input type="checkbox"  name="typesofoppstidsarray" id="typesofoppstidsarray[<%=iSubType%>]" value="<%=iSubType%>" 
<%
		
							for(int indexArray=0; indexArray < a_iTypesOfOppsArray.length; indexArray++){
								iTemp = a_iTypesOfOppsArray[indexArray];
								if(iTemp==iSubType) out.print(" checked ");
							}
%>
/> <%=aszDisplay%></label>
</div>
<%			
							iTemp++;
							if(index==4)	out.println("</td><td>");
						}
%>
</td></tr></table></div>
	</div>
<br>


<%
// move to NOT required later expanded section for churchvol
if( aszHostID.equalsIgnoreCase("volengchurch") || aszMemberType.equalsIgnoreCase("Church") ){
%>
	
	<div class="left-column-wide" id="onethirdpov_div">
		Are more than one-third of the participants in your <%=aszOrgOrChurch.toLowerCase()%>/programs low income (below 150% of the poverty level)?  Feel free to estimate. <%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="onethirdpov" id="onethirdpov_yes"/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="onethirdpov" id="onethirdpov_no" /> No
	</div>
	<br clear="all" />
	<br>
	
	<div class="left-column-wide" id="visa_div">
		Are you willing to assist volunteers in getting a visa to travel to your opportunities if they are outside the country?<%=aszPortalReqFields%>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="<%=aszTakesIntlVolsTID%>" name="intlvols" id="intlvols_yes" <%=aszTakesIntlVols%>/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="<%=aszDoesNotTakeIntlVolsTID%>" name="intlvols" id="intlvols_no" <%=aszDoesNotTakeIntlVols%>/> No
	</div>
<!-- -->	
<% if(iNumOppsOrg < 1){ %>
	<br clear="all" />
	<div class="left-column-wide nonportal-bold" id="numopps_div">
		Number of Volunteer Opportunities
    </div>
	<div class="right-column">
    <input type="text"  name="numopps" size="5" styleClass="textinputwhite" value="<%=""+iNumOppsOrg%>"/>
	</div>
<% } %>
<!-- -->	
	<div class="cleardiv"></div>
	
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Do You Have a Listing of Your Volunteer Opportunities<br>on Your <%=aszOrgOrChurch%>'s Website?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="haslistings" id="haslistings_yes" onclick="clickListings(this)" <%=aszHasListings%>/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="haslistings" id="haslistings_no" onclick="clickListings(this)" <%=aszDoesNotHaveListings%>/> No
	</div>
	<div class="cleardiv"></div>
<div id="listings_link" style="display:none;">
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Web Address of <%=aszOrgOrChurch%>'s<br>Volunteer Opportunity Listings:
	</div>
	<div class="right-column">
    <input type="text"  name="listingsurl" size="30" styleClass="textinputwhite" value="<%=org.getORGListingsURL()%>"/>
	</div>
	<div class="cleardiv"></div>
</div>	





	<div class="left-column">
		<%=aszOrgOrChurch%> Donation URL
	</div>
	<div class="right-column">
		<input type="text"  name="orgdonateurl" size="24" styleClass="textinputwhite" value="<%=org.getORGDonateURL()%>"/>
	</div>
	<br clear="all" />

	<div class="left-column">
		EIN: (Employer Identification Number)
	</div>
	<div class="right-column">
		<input type="text"  name="orgfedtaxid" size="30" styleClass="textinputwhite" value="<%=org.getORGFedTaxId()%>"/>
	</div>
	<br clear="all" />


<% 
}	// end move to NOT required later expanded section for churchvol
%>

	
		<div class="left-column-wide">
			Number of People Served by This <%=aszOrgOrChurch%> Annually (Feel Free to Estimate)
		</div>
		<div class="right-column">
			<input type="text"  name="orgnumserved" size="5" styleClass="textinputwhite" value="<%=org.getORGNumServed()%>"/>
		</div>
		<br clear="all" />
	
		<div class="left-column-wide">
			Do You Require Formal Orientation Training for Volunteers?
		</div>
		<div class="right-column">
			<input type="radio" styleClass="check" value="Yes" name="orgformaltrain" id="orgformaltrain_yes" <%=aszFormTrainYes%>/> Yes 
			&nbsp; &nbsp; 
			<input type="radio" styleClass="check" value="No" name="orgformaltrain" id="orgformaltrain_no" <%=aszFormTrainNo%>/> No
		</div>
		<br clear="all" />
	
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Do You Have a Listing of Your Volunteer Opportunities<br>on Your <%=aszOrgOrChurch%>'s Website?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="Yes" name="haslistings" id="haslistings_yes" onclick="clickListings(this)" <%=aszHasListings%>/> Yes 
		&nbsp; &nbsp; 
		<input type="radio" styleClass="check" value="No" name="haslistings" id="haslistings_no" onclick="clickListings(this)" <%=aszDoesNotHaveListings%>/> No
	</div>
	<div class="cleardiv"></div>
<div id="listings_link" style="display:none;">
	<div class="left-column-wide nonportal-bold" id="haslistings_div">
		Web Address of <%=aszOrgOrChurch%>'s<br>Volunteer Opportunity Listings:
	</div>
	<div class="right-column">
    <input type="text"  name="listingsurl" size="30" styleClass="textinputwhite" value="<%=org.getORGListingsURL()%>"/>
	</div>
	<div class="cleardiv"></div>
</div>	

	

<% if(bFaith==false ){ %>	
    <div class="span-columns">
ChristianVolunteering.org has a <a href="http://www.cityvision.edu/cms/cv/site-application" target="_new">City Vision Internship program</a> where we can provide a full-time (35hours/week) intern at a ministry for a year. The ministry pays $3,500 plus room/board (or equivalent stipend).  Would your organization be interested in finding out more about this program?  
&nbsp;&nbsp;&nbsp;&nbsp;
		<input <%=aszSiteInterestYes%> type="radio" styleClass="check" value="1" name="site_interest" id="site_interest_yes" /> Yes
		&nbsp;&nbsp;&nbsp;
		<input <%=aszSiteInterestNo%> type="radio" styleClass="check" value="0" name="site_interest" id="site_interest_no" /> No 	
	</div>
	<br clear="all" />
	
<% } %>	
	</div><!-- end additionalinfocontent div-->
	</div><!-- end additionalinfo div-->

<br>
	<div class="left-column">
	</div>
	<div class="right-column">
		<INPUT class=submit type=submit value=Continue>
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
<%@ include file="/template/footer.inc" %><!-- end footer information -->
