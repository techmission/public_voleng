<%@ include file="/template_include/topjsploginreq1.inc" %>

<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

<style>
.better-select div.form-checkboxes-scroll {
  border: 1px solid #666;
  max-height: 95px;
  max-width: 400px;
  overflow: auto;
  word-wrap: break-word;
}
.better-select div.form-checkboxes-noscroll {
  border: 1px solid #666;
  overflow: hidden;
  word-wrap: break-word;
}
.moreDetails {
	text-align:left; 
	background-color:#FFF; 
	border:3px solid #93B6F0; 
	padding:10px; 
}
</style>

<SCRIPT TYPE="text/javascript">
<!--
function popup(mylink, windowname)
{
if (! window.focus)return true;
var href;
if (typeof(mylink) == 'string')
   href=mylink;
else
   href=mylink.href;
window.open(href, windowname, 'width=500,height=400,scrollbars=yes');
return false;
}
//-->

</SCRIPT>


<noscript>
Javascript is disabled.  This form will not work for you.<br><br>
Please enable Javascript in your browser<!-- <% //or click <a href="%=request.getContextPath()>/register.do?method=showVolCreateNonJS">here</a> to proceed.%>-->
</noscript>

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<script language="javascript">

function new_account() {
	document.forms["individualForm"].submit();	
} 

function submitBoth() {
	new_account();
}

function toggleDiv(divid){
    if(document.getElementById(divid).style.display == 'none'){
      document.getElementById(divid).style.display = 'block';
      if(divid == 'moreDetails'){
      	document.getElementById(divid).style.margin = '10px';
      	//document.getElementById(divid).style.padding = '10px';
      }
    }else{
      document.getElementById(divid).style.display = 'none';
    }
}
</script>


<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		//vidWorkStudy=264, 
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
		vidVolSkill=31, vidVolInterestArea=32, 
		vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332,
		vidCauseTopic=8;
int iTemp=0, iTempValue=0;
int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519,
	ministryAreasTID=12521, iLocalVolTID = 17254, iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
	iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266,
	iDisasterRlfTID = 21632,iLocalOrgsTID = 21853,
	personalityTypeTID = userprofile.getUSPPersonalityTID();
int[] a_iContainer= new int[1];


String aszSpiritualTID = spiritualTID + "";
String aszGlobalIssuesTID = globalIssuesTID + "";
String aszOrganizationalDevelopmentTID = organizationalDevelopmentTID + "";
String aszReconciliationTID = reconciliationTID + "";
String aszMinistryAreasTID = ministryAreasTID + "";
String aszPersonalityTypeTID = personalityTypeTID + "";
String aszVolSkills = userprofile.getUSPVolSkills();
String aszOppSkills = "";
String aszUserSkillList = userprofile.getUSPSkillTypes();
String aszUserServiceList = userprofile.getUSPServiceAreas();
String aszUserLookingFor = userprofile.getUSPLookingFor();
String aszUserMinistryAreas = userprofile.getUSPMinistryAreasCause();
String aszUserSpiritualDev = userprofile.getUSPSpiritualDev();
String aszUserGlobalIssues = userprofile.getUSPGlobalIssues();
String aszUserOrganizationalDev = userprofile.getUSPOrganizationalDev();
String aszUserReconciliation = userprofile.getUSPReconciliation();
String aszUserOtherLearningInterests = userprofile.getUSPOtherLearningInterests();

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList ainterestareaList = new  ArrayList ( 2 );
ArrayList alangList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList avolavailList = new  ArrayList ( 2 );
ArrayList aLookingForList = new  ArrayList ( 2 );

ArrayList aSpiritualList = new ArrayList ( 2 );
ArrayList aGlobalIssuesList = new ArrayList ( 2 );
ArrayList aOrganizationalDevelopmentList = new ArrayList ( 2 );
ArrayList aReconciliationList = new ArrayList ( 2 );
ArrayList aMinistryAreasList = new ArrayList ( 2 );
ArrayList aRelatedAreasList = new ArrayList ( 2 );
ArrayList aOppSkills = new ArrayList ( 2 );
ArrayList aUserSkillList = new ArrayList ( 2 );
ArrayList aUserServiceList = new ArrayList ( 2 );
ArrayList aUserCauseList = new ArrayList ( 2 );
ArrayList aUserLookingForList = new ArrayList ( 2 );
ArrayList aUserOtherLearningInterestsList = new ArrayList ( 2 );

ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getTaxonomyCodeList( askillsList, vidVolSkill );
aCodes.getTaxonomyCodeList( ainterestareaList, vidVolInterestArea );
aCodes.getTaxonomyCodeList( alangList, vidVolLang, 303 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( afiliationList, vidVolDenom );
aCodes.getTaxonomyCodeList( apartnersList, vidVolOrgAffil );
aCodes.getTaxonomyCodeList( avolavailList, vidVolAvail );
aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor, 303 );

aCodes.getTaxonomyChildCodeList( aSpiritualList, 8, spiritualTID );
aCodes.getTaxonomyChildCodeList( aGlobalIssuesList, 8, globalIssuesTID);
aCodes.getTaxonomyChildCodeList( aOrganizationalDevelopmentList, 8, organizationalDevelopmentTID);
aCodes.getTaxonomyChildCodeList( aReconciliationList, 8, reconciliationTID);
aCodes.getTaxonomyChildCodeList( aMinistryAreasList, 8, ministryAreasTID );

aCodes.getTaxonomyRelatedCodeList( aRelatedAreasList, 336, personalityTypeTID );

aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserMinistryAreas );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserSpiritualDev );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserGlobalIssues );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserOrganizationalDev );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserReconciliation );


int iCCDA=1188;
int iWorldVision=1228;
int iHLIC=1209;
int iSalvArmy=1219;
int iAGRM=1179;
int iYPN=1232;
int iABS=11545;


session.putValue ("MyIdentifier1","");  // Initialize Value into session Object

String aszPublic="display:none;";


// I am looking for... options:
/*
String aszLookingForVID="" + vidLookingFor;

String aszLocalVolTID="" + iLocalVolTID;
String aszGroupFamilyTID="" + iGroupFamilyTID;
String aszVolBoardTID="" + iVolBoardTID;
String aszVolVirtTID="" + iVolVirtTID;
String aszIntrnTID="" + iIntrnTID;
String aszSumIntrnTID="" + iSumIntrnTID;
String aszWorkStudyTID="" + iWorkStudyTID;
String aszJobsTID="" + iJobsTID;
String aszConferenceTID="" + iConferenceTID;
String aszConsultingTID="" + iConsultingTID;
String aszSocJustGrpsTID="" + iSocJustGrpsTID;
String aszLocalOrgsTID="" + iLocalOrgsTID;
// end I am looking for... options
*/
session.putValue ("usertype","");  
session.putValue ("taclite","");  
session.putValue ("upnid","");
session.putValue ("upvid","");
session.putValue ("uplid","");
session.putValue ("uid","");

String aszSubmitDisplay="display:inline;";

if(
		(aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengroundtrip")) 
){ 
	aszSubmitDisplay="display:none;";
} 

String aszDirectory = "";
int iVolDirectorytid = 8864;
String aszVolDirectorytid="" + iVolDirectorytid;
if (userprofile.getUSPVolunteerTID()==iVolDirectorytid){
	aszDirectory="checked";
	aszPublic="display:inline;";
}

String aszSubscribe = "";
if (userprofile.getUSPSubscribe()==1){
	aszSubscribe="checked";
	aszPublic="display:inline;";
}

// I am looking for... options:
String aszLookingForVID="" + vidLookingFor;

String aszLocalVol = "";
//int iLocalVolTID = 17254;
String aszLocalVolTID="" + iLocalVolTID;
if (userprofile.getUSPLocalVolTID()==iLocalVolTID){
	aszLocalVol="checked";
}

String aszGroupFamily = "";
//int iGroupFamilyTID = 17255;
String aszGroupFamilyTID="" + iGroupFamilyTID;
if (userprofile.getUSPGroupFamilyTID()==iGroupFamilyTID){
	aszGroupFamily="checked";
}

String aszBoard = "";
//int iVolBoardTID = 6583;
//int iVolBoardTID = 17256;
String aszVolBoardTID="" + iVolBoardTID;
if (userprofile.getUSPVolBoardTID()==iVolBoardTID){
	aszBoard="checked";
}

String aszVirtual = "";
//int iVolVirtTID = 6582;
//int iVolVirtTID = 17257;
String aszVolVirtTID="" + iVolVirtTID;
if (userprofile.getUSPVolVirtTID()==iVolVirtTID){
	aszVirtual="checked";
}

String aszIntrn = "";
//int iIntrnTID = 17258;
String aszIntrnTID="" + iIntrnTID;
if (userprofile.getUSPIntrnTID()==iIntrnTID){
	aszIntrn="checked";
}

String aszSumIntrn = "";
//int iSumIntrnTID = 17259;
String aszSumIntrnTID="" + iSumIntrnTID;
if (userprofile.getUSPSumIntrnTID()==iSumIntrnTID){
	aszSumIntrn="checked";
}

String aszWorkStudy = "";
//int iWorkStudyTID = 17260;
String aszWorkStudyTID="" + iWorkStudyTID;
if (userprofile.getUSPWorkStudyTID()==iWorkStudyTID){
	aszWorkStudy="checked";
}

String aszJobs = "";
//int iJobsTID = 17261;
String aszJobsTID="" + iJobsTID;
if (userprofile.getUSPJobsTID()==iJobsTID){
	aszJobs="checked";
}

String aszConference = "";
//int iConferenceTID = 17262;
String aszConferenceTID="" + iConferenceTID;
if (userprofile.getUSPConferenceTID()==iConferenceTID){
	aszConference="checked";
}

String aszConsulting = "";
//int iConsultingTID = 17265;
String aszConsultingTID="" + iConsultingTID;
if (userprofile.getUSPConsultingTID()==iConsultingTID){
	aszConsulting="checked";
}

String aszSocJustGrps = "";
//int iSocJustGrpsTID = 17266;
String aszSocJustGrpsTID="" + iSocJustGrpsTID;
if (userprofile.getUSPSocJustGrpsTID()==iSocJustGrpsTID){
	aszSocJustGrps="checked";
}

String aszLocalOrgs = "";
//int iLocalOrgsTID = 21853;
String aszLocalOrgsTID="" + iLocalOrgsTID;
if (userprofile.getUSPLocalOrgsTID()==iLocalOrgsTID){
	aszLocalOrgs="checked";
}
// end I am looking for... options

String aszFaith="display: inline;";
String aszBelief = userprofile.getUSPChristianP();
if(aszBelief.equalsIgnoreCase("No") || aszBelief.equalsIgnoreCase("")){
   aszFaith="display: none;";
}

String aszResume="display: inline;";
String aszVolun="display: inline;";
String aszVolOpp = userprofile.getUSPSiteUseType();
if( (aszVolOpp.equalsIgnoreCase("Volunteer")) || (aszVolOpp.equalsIgnoreCase("Both"))){
   aszResume="display: inline;";
   aszVolun="display: inline;";
	//aszPhotoLink="display:inline;";
}else{
   aszResume="display: none;";
   aszVolun="display: none;";
	//aszPhotoLink="display:none;";
} 

String aszSkill2="display: inline;";
int iSkills1 = userprofile.getUSPVolSkills1TID();
String aszSkills1 = userprofile.getUSPVolSkills();
if(aszSkills1.equalsIgnoreCase("") && (!(iSkills1 > 0) ) ){
   aszSkill2="display: none;";
}

String aszSkill3="display: inline;";
int iSkills2 = userprofile.getUSPVolSkills2TID();
String aszSkills2 = userprofile.getUSPVolSkills2();
if(aszSkills2.equalsIgnoreCase("") && (!(iSkills2 > 0) ) ){
   aszSkill3="display: none;";
}

String aszInterestArea2Display="display: inline;";
int iInterestArea1 = userprofile.getUSPVolInterestArea1TID();
String aszInterestArea1 = userprofile.getUSPVolInterestArea1();
if(aszInterestArea1.equalsIgnoreCase("") && (!(iInterestArea1 > 0) ) ){
   aszInterestArea2Display="display: none;";
}

String aszInterestArea3Display="display: inline;";
int iInterestArea2 = userprofile.getUSPVolInterestArea2TID();
String aszInterestArea2 = userprofile.getUSPVolInterestArea2();
if(aszInterestArea2.equalsIgnoreCase("") && (!(iInterestArea2 > 0) ) ){
   aszInterestArea3Display="display: none;";
}

String aszLang2Display="display: inline;";
int iLang1 = userprofile.getUSPVolLang1TID();
String aszLang1 = userprofile.getUSPVolLang1();
if(aszLang1.equalsIgnoreCase("") && (!(iLang1 > 0) ) ){
   aszLang2Display="display: none;";
}

String aszLang3Display="display: inline;";
int iLang2 = userprofile.getUSPVolLang2TID();
String aszLang2 = userprofile.getUSPVolLang2();
if(aszLang2.equalsIgnoreCase("") && (!(iLang2 > 0) ) ){
   aszLang3Display="display: none;";
}

String aszAffil2Display="display: inline;";
int iAffil1 = userprofile.getUSPOtherAffil1TID();
String aszAffil1 = userprofile.getUSPOtherAffilP();
if(aszAffil1.equalsIgnoreCase("") && (!(iAffil1 > 0) ) ){
   aszAffil2Display="display: none;";
}

String aszAffil3Display="display: inline;";
int iAffil2 = userprofile.getUSPOtherAffil2TID();
String aszAffil2 = userprofile.getUSPOtherAffil2();
if(aszAffil2.equalsIgnoreCase("") && (!(iAffil2 > 0) ) ){
   aszAffil3Display="display: none;";
}

if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
   aszFaith="display: none;";
   aszAffil2Display="display: none;";
   aszAffil3Display="display: none;";
}

String aszUPnid="" + userprofile.getUserProfileNID();
String aszUPvid="" + userprofile.getUserProfileVID();
String aszUPlid="" + userprofile.getUserProfileLID();
String aszUID="" + userprofile.getUserUID();
String aszPersonNumber="" + userprofile.getUserUID();


String aszCountrySet="display:table-row;";
if(	userprofile.getUSPAddrCountryName().length()>1 &&
	(! userprofile.getUSPAddrCountryName().equalsIgnoreCase("us") )
){
	aszCountrySet="display:none;";
}else{
	aszCountrySet="display:table-row;";
}

String aszUserType=userprofile.getUSPInternalUserTypeComment();
String aszTacLite=" AND !(tid=8059) AND !(tid=1222) ";
%>


<script language="javascript">

	function clicked_vol(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
//		document.forms["individualForm"].elements["newsletter"].value="38";
	}
	function clicked_both(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
//		document.forms["individualForm"].elements["newsletter"].value="35,38";
	}
	function clicked_org(){
		document.getElementById('volun').style.display='none';
		document.getElementById('volunteertid').checked=false;
		document.getElementById('publicprofile').style.display='none';
//		document.getElementById('localvoltid').checked=false;
//		document.getElementById('volvirttid').checked=false;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=false;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=false;
		//document.getElementById('profilephoto').style.display='none';
//		document.forms["individualForm"].elements["newsletter"].value="35";
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
		//document.getElementById('publicprofile').style.display=vis;
	}

	function selected_country(object){
		var country=object.value;
		//alert('**'+country+'**');
		if(country=="us")
			document.getElementById('postalrow').style.display='table-row';
		else
			document.getElementById('postalrow').style.display='none';
		
	}
function clicked_us(){
	document.getElementById('postalrow').style.display='table-row';
}
function clicked_non_us(){
	document.getElementById('postalrow').style.display='none';
}


$(document).ready(function() {
<% if(aszVolOpp.equalsIgnoreCase("Both")){ %>
	var siteUse = 'Both';
		//$('input:radio[name=siteusetype]').filter('[value=Both]').attr('checked', true);
<% }else if(aszVolOpp.equalsIgnoreCase("Organization")){ %>
	var siteUse = 'Organization';
		//$('input:radio[name=siteusetype]').filter('[value=Organization]').attr('checked', true);
<% }else if(aszVolOpp.equalsIgnoreCase("Volunteer")){ %>
	var siteUse = 'Volunteer';
		//$('input:radio[name=siteusetype]').filter('[value=Volunteer]').attr('checked', true);
<% }else{ %>
	//var siteUse = $('input:radio[name=siteusetype]:checked').val();
<% } %>
	if (siteUse=="Volunteer"){
		clicked_vol();
		//$('input:radio[name=siteusetype]').filter('[value=Volunteer]').attr('checked', true);
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}else if (siteUse=="Both"){
		clicked_both();
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}else if (siteUse=="Organization"){
		clicked_org();
	}else{
		clicked_vol();
		//$('input:radio[name=siteusetype]').filter('[value=Volunteer]').attr('checked', true);
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}
 });  
                                

</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Manage My Subscriptions</span>
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
<span style="float: left;">Manage My Subscriptions</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; registration</div>
</div>
<% } %>
      	
          <div align="left">

	<div id="body">
			<br><h2>Weekly subscriptions are based on a combination of your Skills, Interest Areas, and Address:</H2>

<div id="form1">
<html:form action="/register.do" target="_self">
<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>


<html:hidden property="method" value="processIndProfEdit1" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment"/>

<html:hidden property="internaltaclitecomment" value="<%=aszTacLite%>" />
<html:hidden property="internalusertypecomment" value="<%=aszUserType%>" />
<html:hidden property="upnid" value="<%=aszUPnid%>" />
<html:hidden property="upvid" value="<%=aszUPvid%>" />
<html:hidden property="uplid" value="<%=aszUPlid%>" />
<html:hidden property="uid" value="<%=aszUID%>" />

<html:hidden property="volresume"/>
<html:hidden property="volavailtid"/>

<%

if(
	userprofile.getUSPEmail1Addr().startsWith("facebooktemp") && 
	userprofile.getUSPEmail1Addr().endsWith("@urbanministry.org")
){
	userprofile.setUSPEmail2Addr(userprofile.getUSPEmail1Addr());
	userprofile.setUSPEmail1Addr("");
%>
	<html:hidden property="email2addr" value="<%=userprofile.getUSPEmail2Addr()%>"/>
<% }else{ %>
	<html:hidden property="email2addr" value=""/>
<% }
 
if(userprofile.getUSPEmail1Addr().length() > 2 ){ 
%>
<input type="hidden" name="email1addr" id="email1addr" value="<%=userprofile.getUSPEmail1Addr()%>" />
<% 
}
if(userprofile.getUSPUsername().length() > 2 ){ %>
<html:hidden property="username" value="<%=userprofile.getUSPUsername()%>" />
<% }		if(userprofile.getUSPNameFirst().length() > 1 ){ %>
<html:hidden property="namefirst" value="<%=userprofile.getUSPNameFirst()%>" />
<% }		if(userprofile.getUSPNameLast().length() > 1 ){ %>
<html:hidden property="namelast" value="<%=userprofile.getUSPNameLast()%>" />
<% } %>

<html:hidden value="<%=aszVolOpp%>" property="siteusetype" />
<% if(userprofile.getUSPAgree1Fg().length() > 0 || true==true){ %>
<html:hidden styleClass="check" value="Yes" property="agreeflag1"/>
<% } %>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

            <!--nested table -->
<% // test to see if it's from a URL that has a narrower template
if(	aszNarrowTheme.equalsIgnoreCase("true") ){ %>
	<table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
<% }else{ %>
	<table border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
<% } %>
             <!-- MSTableType="layout" -->
				
    <% if(userprofile.getUSPUsername().length() > 2 ){ %>
     	<%//=userprofile.getUSPUsername()%>
    <% }else{ %>
		<tr>
                <TD width=130><b>Username</b> <span class="criticaltext">*</span></TD>
                <TD>
                	<html:text property="username" styleId="username" size="35" styleClass="textinputwhite"/>
                </TD>
				<td height="23"></td>
		</tr>
    <% } %>
    <% if(userprofile.getUSPEmail1Addr().length() > 2 ){ %>
    <% }else{ %>
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
                <TD>
                	<%//=userprofile.getUSPEmail1Addr()%>
                	<html:text property="email1addr" styleId="email1addr" size="35" styleClass="textinputwhite"/>
                </TD>
		</tr>
	<% } %>
    <% if(userprofile.getUSPNameFirst().length() > 1 ){ %>
    	<%//=userprofile.getUSPNameFirst()%>
    <% }else{ %>
		<tr>
                <TD ><b>First name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23">
                	<html:text property="namefirst" styleId="namefirst" size="35" styleClass="textinputwhite"/>
                </TD>
		</tr>
    <% } %>
    <% if(userprofile.getUSPNameLast().length() > 1 ){ %>
    	<%//=userprofile.getUSPNameLast()%>
    <% }else{ %>
		<tr>
                <TD ><b>Last name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23">
                	<html:text property="namelast" styleId="namelast" size="35" styleClass="textinputwhite"/>
                </TD>
		</tr>
    <% } %>
              	

<tr><td colspan=3>				
<div id="volun" style="display: inline;">

<table border=0 cellpadding=2 cellspacing=2>        	
			<tr style="display:none;">
				<td height="30">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" /></td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
			</tr>
			<tr>
				<td height="30">
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" /></td>
				<td colspan=2>Subscribe me to weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile in the following areas:</td>
			</tr>

</table>
<br>


<div id="publicprofile" style="<%=aszPublic%>">
<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="better-select betterfixed">
		<label><b>I am Looking for: </b></label>
		<div class="form-checkboxes form-checkboxes-noscroll">
<table ><tr><td>
              <%
					a_iContainer= new int[100];
					iTemp = 0;
					a_iContainer = userprofile.getUSPLookingForArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < aLookingForList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if(iSubType!=iDisasterRlfTID && iSubType!=iConferenceTID && iSubType!=iConsultingTID){
								out.println(" <div id=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"lookingforarray\" id=\"lookingforarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==4)	out.println("</td><td>");
							}
						}
					} else { 
						for(int index=0; index < aLookingForList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if(iSubType!=iDisasterRlfTID && iSubType!=iConferenceTID && iSubType!=iConsultingTID){
								out.println(" <div id=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"lookingforarray\" id=\"lookingforarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==4)	out.println("</td><td>");
							}
						}
					} 					
				%>
</td></tr></table></div>
<br>
</div>

<br>
<div id="address">
<label><h3 onclick="toggleDiv('addressDetails')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  Modify My Location</h3></label>
<br>
<div  style="display:none; border: 1px solid #666; margin: 10;" id="addressDetails">
<table>
				<tr style="display:none;">
                <TD width=120>
				Phone <span class="criticaltext">*</span>
				</TD>
                <TD ><html:text property="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr>
        <TD>Street</TD>
        <TD height="23"><html:text property="addrline1" styleId="addrline1" size="25" styleClass="textinputwhite"/></TD>
		</tr>

		<tr>
        <TD>City</TD>
        <TD height="23"><html:text property="addrcity" styleId="addrcity" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		
		<tr>
<TD>State/Province</TD>
<TD height="23">
                <html:select property="addrstateprov"> 
					<option value=""></option>
					<%
					if(null != aStateList){
						for(int index=0; index < aStateList.size(); index++){
							aszTemp = userprofile.getUSPAddrStateprov();
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCSPStateCode();
							out.println(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
						}
					}
					%>
              </html:select>
</TD>
</tr>
				<tr>
                <TD>Country <span class="criticaltext">*</span> </TD>
                <TD colspan=4>
                	<SELECT id="addrcountryname" name="addrcountryname" class="smalldropdown" onchange="selected_country(this)"> 
					<option value=""></option>
					<%
					if(null != aCountryList){
						aszTemp=userprofile.getUSPAddrCountryName();
						if(aszTemp.length()<2) aszTemp="us";
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
								out.println(" selected=selected ");//onClick=\"clicked_us()\" ");
							}else{
								//out.println(" onClick=\"clicked_non_us()\" ");
							}
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
                    </SELECT>
                </TD>
              		<td height="27"></td>
		</tr>
		<tr id="postalrow" style="<%=aszCountrySet%>">
                <TD>Postal Code <span class="criticaltext">*</span> </TD>
                <TD colspan=2 height="23"><html:text property="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
		</tr>
</table>
</div>
<br>
</div>


<label><h3 onclick="toggleDiv('moreDetails')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  More Details</h3></label>
<br>
<div id="moreDetails" style="display:none;">
    <div id="better-select-edit-taxonomy-<%out.print(vidVolSkill);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('skillsCheckboxes')"><b><img src="http://www.urbanministry.org/misc/menu-expanded.png">  Personal Skills</b></label>
	    <!--html:select property="skilltypesarray" multiple="true" size=5 -->
	    <!--select name="skilltypesarray" multiple="true" size=5 width=100-->
<div id="skillsCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=215>
<% 
					a_iContainer= new int[100];
					iTemp = 0;
					a_iContainer = userprofile.getUSPSkillTypesArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if (iSubType == 4745){
								out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> Musician </label>");
								out.println("</div>");
							}else if (iSubType == 8122){
								out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> Deaf Outreach </label>");
								out.println("</div>");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> "+ aAppCode.getAPCDisplay()+ "	</label>");
								out.println("</div>");
							}
							iTemp++;
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
	 						}
 						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
							out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
							out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
								iTempValue = a_iContainer[indexArray];
								if(iTempValue==iSubType) out.print(" checked ");
							}

							out.println(" /> "+ aAppCode.getAPCDisplay()+ "	</label>");
							out.println("</div>");
							iTemp++;

							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
 							}
 						}
					} 					
%>
				</td></tr>
			</table><!--Total: <% out.print(iTemp); %> = 38/3 columns=13 per column-->
			</div>
			</div>
			</div>
			<!--/select-->
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidVolInterestArea);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('serviceAreasCheckboxes')"><b><img src="http://www.urbanministry.org/misc/menu-expanded.png">  Volunteer Interest Areas</b></label>
		    <!--html:select property="serviceareasarray" multiple="true" size=5 -->
		    <!--select name="serviceareasarray" multiple="true" size=5 width=100-->
<div id="serviceAreasCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=215>




<% 
					a_iContainer= new int[100];
					iTemp = 0;
					a_iContainer = userprofile.getUSPServiceAreasArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < ainterestareaList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4760 || // Bible Study
								iSubType == 4764 || // Church Planting
								iSubType == 4772 || // Evangelism
								iSubType == 4773 || // Family / Adults Ministry
								iSubType == 4783 || // Single Parents/Crisis Pregnancy
								iSubType == 4787 || // Vacation Bible School
								iSubType == 4789 || // Women's Ministry
								iSubType == 7341 || // Christian/Catholic Schools
								iSubType == 7342 ){ // Religious Education
							}else if (iSubType == 4767){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}

								out.println(" >Disabilities Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 6843){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}

								out.println(" >Senior / Elderly Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4774){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}

								out.println(" >Food Service / Hunger</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4782){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}

								out.println(" >Prison Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}

								out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
								out.println(" </div> ");
							}
							iTemp++;
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
	 						}
 						}
					} else { 
						for(int index=0; index < ainterestareaList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+ vidVolInterestArea+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
							out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolInterestArea+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
							out.print("	<input type=\"checkbox\"  name=\"serviceareasarray\" id=\"serviceareasarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
								iTempValue = a_iContainer[indexArray];
								if(iTempValue==iSubType) out.print(" checked ");
							}

							out.println(" /> ");
							out.println(aAppCode.getAPCDisplay());
							out.println("	</label>");
							out.println("</div>");
							iTemp++;

							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==12)	out.println("</td><td width=215>");
								else if(iTemp==24)	out.println("</td><td width=210>");
 							}
 						}
					} 					
%>







				</td></tr>
			</table><!--Total: <% out.print(iTemp); %> = 35/3 columns=12 per column-->
			</div>
			</div>
			</div>
			<!--/html:select-->






<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+spiritualTID);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('ministryAreasCauseCheckboxes')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  <b>Ministry Areas/Causes </b></label>
		    <!--html:select property="ministryareascausearray" multiple="true" size=5 -->
		    <!--select name="ministryareascausearray" multiple="true" size=5 width=100-->
		    <div id="ministryAreasCauseCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=190>
<%
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPMinistryAreasArray();
					for(int index=0; index <aMinistryAreasList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aMinistryAreasList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.println(" <div id=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
						out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
						out.print("	<input type=\"checkbox\"  name=\"ministryareascausearray\" id=\"ministryareascausearray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
							iTempValue = a_iContainer[indexArray];
							if(iTempValue==iSubType) out.print(" checked ");
						}
						out.println(" /> ");
						out.println(aAppCode.getAPCDisplay());
						out.println("	</label>");
						out.println("</div>");
						iTemp++;

						if(	aszNarrowTheme.equalsIgnoreCase("true") ){
							if(iTemp==14)	out.println("</td><td width=215>");
						}else{ 
							if(iTemp==10)	out.println("</td><td width=215>");
							else if(iTemp==19)	out.println("</td><td width=200>");
						} 
					}
%>
				</td></tr>
			</table>
			</div>
			</div>
			</div>
			<!--/html:select-->
<% if(! aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+spiritualTID);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('spiritualDevelopmentCheckboxes')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  <b>Spiritual Development</b></label>
		    <!--html:select property="spiritualdevarray" multiple="true" size=5 -->
		    <!--select name="spiritualdevarray" multiple="true" size=5 width=100-->
		    <div id="spiritualDevelopmentCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=190>
<%
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPSpiritualDevArray();
					for(int index=0; index <aSpiritualList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSpiritualList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.println(" <div id=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
						out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
						out.print("	<input type=\"checkbox\"  name=\"spiritualdevarray\" id=\"spiritualdevarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
							iTempValue = a_iContainer[indexArray];
							if(iTempValue==iSubType) out.print(" checked ");
						}
						out.println(" /> ");
						out.println(aAppCode.getAPCDisplay());
						out.println("	</label>");
						out.println("</div>");
						iTemp++;

						if(	aszNarrowTheme.equalsIgnoreCase("true") ){
							if(iTemp==3)	out.println("</td><td width=215>");
						}else{ 
							if(iTemp==2)	out.println("</td><td width=215>");
							else if(iTemp==4)	out.println("</td><td width=200>");
						} 
					}
					out.println("</table>");
%>
			</div>
			</div>
			</div>
			<!--/html:select-->
<% } %>
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+globalIssuesTID);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('globalIssuesCheckboxes')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  <b>Global Issues</b></label>
		    <!--html:select property="globalissuesarray" multiple="true" size=5 -->
		    <!--select name="globalissuesarray" multiple="true" size=5 width=100-->
		    <div id="globalIssuesCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=190>
<% 
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPGlobalIssuesArray();
					for(int index=0; index <aGlobalIssuesList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aGlobalIssuesList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						if(aszSecondaryHost.equalsIgnoreCase("volengivol") &&
						( 
							iSubType==588 || //Global Missions
							iSubType==4236 //Short-term Missions
						)
						){
						}else{
							out.println(" <div id=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
							out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
							out.print("	<input type=\"checkbox\"  name=\"globalissuesarray\" id=\"globalissuesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );

							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
								iTempValue = a_iContainer[indexArray];
								if(iTempValue==iSubType) out.print(" checked ");
							}
							out.println(" /> ");
							out.println(aAppCode.getAPCDisplay());
							out.println("	</label>");
							out.println("</div>");
							iTemp++;

							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==8)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==5)	out.println("</td><td width=215>");
								if(iTemp==10)	out.println("</td><td width=200>");
							} 
						}
					}
%>
				</td></tr>
			</table>
			</div>
			</div>
			</div>
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+organizationalDevelopmentTID);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('orgDevelopmentCheckboxes')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  <b>Organizational Development</b></label>
		    <!--html:select property="organizationaldevarray" multiple="true" size=5 -->
		    <!--select name="organizationaldevarray" multiple="true" size=5 width=100-->
		    <div id="orgDevelopmentCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=190>
<% 
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPOrganizationalDevArray();
					for(int index=0; index <aOrganizationalDevelopmentList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aOrganizationalDevelopmentList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.println(" <div id=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
						out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
						out.print("	<input type=\"checkbox\"  name=\"globalissuesarray\" id=\"globalissuesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
							iTempValue = a_iContainer[indexArray];
							if(iTempValue==iSubType) out.print(" checked ");
						}
						out.println(" /> ");
						out.println(aAppCode.getAPCDisplay());
						out.println("	</label>");
						out.println("</div>");
						iTemp++;
						if(	aszNarrowTheme.equalsIgnoreCase("true") ){
							if(iTemp==4)	out.println("</td><td width=215>");
						}else{ 
							if(iTemp==3)	out.println("</td><td width=215>");
							if(iTemp==6)	out.println("</td><td width=200>");
						} 
					}
%>
				</td></tr>
			</table>
			</div>
			</div>
			</div>
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+reconciliationTID);%>" class="better-select betterfixed">
    <div class="form-item">
<br>
<label onclick="toggleDiv('reconcilCultureCheckboxes')"><img src="http://www.urbanministry.org/misc/menu-expanded.png">  <b>Reconciliation & Culture</b></label>
		    <!--html:select property="reconciliationarray" multiple="true" size=5 -->
		    <!--select name="reconciliationarray" multiple="true" size=5 width=100-->
		    <div id="reconcilCultureCheckboxes" class="form-checkboxes form-checkboxes-noscroll" style="display:none;">
	    <table border=0>
	    	<tr>
	    		<td width=190>
<% 
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPReconciliationArray();
					for(int index=0; index <aReconciliationList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aReconciliationList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						if(aszSecondaryHost.equalsIgnoreCase("volengivol") &&
							( 	iSubType==5085 || //Gospel Music
								iSubType==575 //Incarnational Ministry
							)
						){
						}else{
							out.println(" <div id=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
							out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidCauseTopic+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
							out.print("	<input type=\"checkbox\"  name=\"globalissuesarray\" id=\"globalissuesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );

							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
								iTempValue = a_iContainer[indexArray];
								if(iTempValue==iSubType) out.print(" checked ");
							}
							out.println(" /> ");
							out.println(aAppCode.getAPCDisplay());
							out.println("	</label>");
							out.println("</div>");
							iTemp++;

							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==4)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==3)	out.println("</td><td width=215>");
								if(iTemp==6)	out.println("</td><td width=200>");
							} 
						}
					}
%>
				</td></tr>
			</table>
			</div>
			</div>
			</div>
			<!--/html:select-->















</div>
<table border=0 style="display:none;">        	
<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

	<tr><td colspan=4 height="10"></td></tr>

			<tr><td colspan=2>
					Are You a Christian?</td><td>
					<html:radio styleClass="check" value="Yes" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<html:radio styleClass="check" value="No" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
		<tr><td colspan=3>		
		<div id="faith"  style="display: none;">
	</div>
</td></tr>

<% } %>
</table></div></td></tr>
</table>

</html:form>



<div align=center>
<br>
<button type="button" id="submitButton" onclick="submitBoth()">Continue</button>
</div>
<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>

</div>



<div id="processing" style="display:none;">
<center>
<h2>Please wait while we process your registration... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
</div>


      </div></div></div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
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
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
