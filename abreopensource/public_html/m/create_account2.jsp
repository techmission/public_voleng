<%@ include file="/template_include/topjspnologin1.inc" %>
<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/js/jquery-ui-1.8.4.custom.min.js"></script>
	<!--link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" /-->

<link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.base.css" rel="stylesheet" />
<link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.button.css" rel="stylesheet" />
<link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.dialog.css" rel="stylesheet" />
<!--link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.resizable.css" rel="stylesheet" /-->
<link type="text/css" href="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.theme.css" rel="stylesheet" />

	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/jquery-1.4.2.js"></script>
	<!--script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/jquery.bgiframe-2.1.1.js"></script-->
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.position.js"></script>
	<!--script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.resizable.js"></script-->
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="<%=aszPortal%>/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.effects.core.js"></script>
	<style type="text/css">
		body { font-size: 65%; }
		
	</style>
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
.dialog-form { 
	height: 159; 
}
</style>

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
	var mapping = document.getElementById('maptopage2').value;
	if (mapping=="fbapp" ){
		document.getElementById('maptopage').value='fbapp';
	}else {
		document.getElementById('maptopage').value='';
	}
//	alert(document.getElementById('maptopage').value);
	new_account();
}

$(function() {
	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	//$("#dialog").dialog("destroy");
	
	var maptopage2 = $("#maptopage2")
		allFields = $([]).add(maptopage2);
	$("#dialog-form").dialog({
		autoOpen: false,
		//height: 200,
		//width: 350,
		modal: true,
		buttons: {
			'Yes, Take the Test': function() {
				var bValid = true;
				allFields.removeClass('ui-state-error');
				if (bValid) {
					document.getElementById('maptopage2').value='fbapp';
					//$('input:radio[name=maptopage2]').val('fbapp');
					$(this).dialog('close');
				}
			},
			'No, Skip the Test': function() {
					document.getElementById('maptopage2').value='';
					//$('input:radio[name=maptopage2]').val('');
				$(this).dialog('close');
			}
		},
		close: function() {
			//alert('closed');
			submitBoth();
			allFields.val('').removeClass('ui-state-error');
		}
	});
	$('#submitButton')
		.button()
		.click(function() {
			$('#dialog-form').dialog('open');
		});
});
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
		vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332;
int iTemp=0, iTempValue=0;
int iDisasterRlfTID = 21632,iLocalVolTID = 17254,
		iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,
		iSocJustGrpsTID = 17266, iLocalOrgsTID = 21853;
int[] a_iContainer= new int[1];

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList ainterestareaList = new  ArrayList ( 2 );
ArrayList alangList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList avolavailList = new  ArrayList ( 2 );
ArrayList aLookingForList = new  ArrayList ( 2 );
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
String aszVolOpp = userprofile.getUSPVolOrOpportunity();
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
		//$('input:radio[name=voloropp]').filter('[value=Both]').attr('checked', true);
<% }else if(aszVolOpp.equalsIgnoreCase("Organization")){ %>
	var siteUse = 'Organization';
		//$('input:radio[name=voloropp]').filter('[value=Organization]').attr('checked', true);
<% }else if(aszVolOpp.equalsIgnoreCase("Volunteer")){ %>
	var siteUse = 'Volunteer';
		//$('input:radio[name=voloropp]').filter('[value=Volunteer]').attr('checked', true);
<% }else{ %>
	//var siteUse = $('input:radio[name=voloropp]:checked').val();
<% } %>
	if (siteUse=="Volunteer"){
		clicked_vol();
		//$('input:radio[name=voloropp]').filter('[value=Volunteer]').attr('checked', true);
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}else if (siteUse=="Both"){
		clicked_both();
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}else if (siteUse=="Organization"){
		clicked_org();
	}else{
		clicked_vol();
		//$('input:radio[name=voloropp]').filter('[value=Volunteer]').attr('checked', true);
		$('input:checkbox[name=subscribe]').attr('checked', true);
	}
 });  
                                

</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Create Account</span>
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
<span style="float: left;">Create Account</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; registration</div>
</div>
<% } %>
      	
 
        <div align="left">

	<div id="body">
<% // for google analytics tracking: %>
	<% String aszGoalPage = "/create/individual/2"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>
			<h2>Please complete the required fields below to create a full account:</H2>

<div id="form1">
<html:form action="/register.do" target="_self">
<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>


<html:hidden property="method" value="processCreateAccount2" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment"/>

<html:hidden property="internaltaclitecomment" value="<%=aszTacLite%>" />
<html:hidden property="internalusertypecomment" value="<%=aszUserType%>" />
<html:hidden property="upnid" value="<%=aszUPnid%>" />
<html:hidden property="upvid" value="<%=aszUPvid%>" />
<html:hidden property="uplid" value="<%=aszUPlid%>" />
<html:hidden property="uid" value="<%=aszUID%>" />
<input type="hidden" name="maptopage" id="maptopage" >
<input type="hidden" name="maptopage2" id="maptopage2" >
<input type="hidden" name="mappingtopage" id="mappingtopage" >

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

<html:hidden value="<%=aszVolOpp%>" property="voloropp" />
<% if(userprofile.getUSPAgree1Fg().length() > 0 || true==true){ %>
<html:hidden styleClass="check" value="Yes" property="agreeflag1"/>
<% } %>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

            <!--nested table -->
	<table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
				
    <% if(userprofile.getUSPUsername().length() > 2 ){  }else{ %>
		<tr>
                <TD width=130><b>Username</b> <span class="criticaltext">*</span></TD>
                <TD>
                	<html:text property="username" styleId="username" size="35" styleClass="textinputwhite"/>
                </TD>
				<td height="23"></td>
		</tr>
    <% } if(userprofile.getUSPEmail1Addr().length() > 2 ){  }else{ %>
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
                <TD>
                	<%//=userprofile.getUSPEmail1Addr()%>
                	<html:text property="email1addr" styleId="email1addr" size="35" styleClass="textinputwhite"/>
                </TD>
		</tr>
	<% } if(userprofile.getUSPNameFirst().length() > 1 ){ }else{ %>
		<tr>
                <TD ><b>First name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23">
                	<html:text property="namefirst" styleId="namefirst" size="35" styleClass="textinputwhite"/>
                </TD>
		</tr>
    <% } if(userprofile.getUSPNameLast().length() > 1 ){  }else{ %>
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
			<tr>
				<td height="30">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" onclick="clicked_public(this)"/></td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
			</tr>
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" onclick="clicked_public(this)" /></td>
				<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile in the following areas:</td>
			</tr>

</table>
<br>


<div id="publicprofile" style="<%=aszPublic%>">
<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="better-select betterfixed">
		<label><b>I am Looking for: </b><span class="criticaltext">*</span> </label>
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
								//if(index==4)	out.println("</td><td>");
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
								//if(index==4)	out.println("</td><td>");
							}
						}
					} 					
				%>
</td></tr></table></div>
<br>
</div>

<div id="address">
<b>Address:</b>
<div  style="border: 1px solid #666; margin: 10;">
<table>
				<tr>
                <TD width=120>
				Phone <span class="criticaltext">*</span>
				</TD>
                <TD ><html:text property="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
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

    <div id="better-select-edit-taxonomy-<%out.print(vidVolSkill);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Personal Skills: </b></label>
	    <!--html:select property="skilltypesarray" multiple="true" size=5 -->
	    <!--select name="skilltypesarray" multiple="true" size=5 width=100-->
<div class="form-checkboxes form-checkboxes-noscroll">
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
								out.println(" /> ");
								out.println("Musician");
								out.println("	</label>");
								out.println("</div>");
							}else if (iSubType == 8122){
								out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println("Deaf Outreach");
								out.println("	</label>");
								out.println("</div>");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ vidVolSkill+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"skilltypesarray\" id=\"skilltypesarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
							}
							iTemp++;
							/*
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
	 						}
							*/
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

							out.println(" /> ");
							out.println(aAppCode.getAPCDisplay());
							out.println("	</label>");
							out.println("</div>");
							iTemp++;
							/*
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
 							}
							*/
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
	<label><b>Volunteer Interest Area : </b></label>
		    <!--html:select property="serviceareasarray" multiple="true" size=5 -->
		    <!--select name="serviceareasarray" multiple="true" size=5 width=100-->
<div class="form-checkboxes form-checkboxes-noscroll">
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
							/*
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==13)	out.println("</td><td width=215>");
								else if(iTemp==26)	out.println("</td><td width=210>");
	 						}
							*/
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
							/*
							if(	aszNarrowTheme.equalsIgnoreCase("true") ){
								if(iTemp==19)	out.println("</td><td width=215>");
							}else{ 
								if(iTemp==12)	out.println("</td><td width=215>");
								else if(iTemp==24)	out.println("</td><td width=210>");
 							}
							*/
 						}
					} 					
%>







				</td></tr>
			</table><!--Total: <% out.print(iTemp); %> = 35/3 columns=12 per column-->
			</div>
			</div>
			</div>
			<!--/html:select-->
<table border=0>        	
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

<tr><td colspan=3>
<table width="540" border="0" cellpadding="0" cellspacing="0" id="splash" >

<% if(! (userprofile.getUSPAgree1Fg().length() > 0) && false==true ){ %>
	<tr>     
        <TD height="30" >
			<html:checkbox styleClass="check" value="Yes" property="agreeflag1"/>
		</td><td colspan=3>
	        I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and I am at least 16 years old or have my parent's permission to use this site.<span class="criticaltext">*</span>

        </TD>
    </tr>

<% } %>
<tr style="padding-top:5px;">
                        <td width="130"></td>
                        <td width="120">&nbsp;</td>
                <TD >
                  <!--INPUT class=submit type=submit value=Continue-->
                 </TD>
                        </tr>

            </TABLE>
</td></tr><tr><td colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td></tr>
</table>


<div id="dialog-form">
<br>
	<div> <!--style="padding-left:20px;"--><font style="font-size:14px;text-transform:none;">Would you like to take our Find Your Calling personality test to help you match to service interests?</font></div>
	<!-- Would you like to take a Free personality test?</font></div> -->
</div>

</html:form>

<div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" id="submitButton">Continue</button>
</div>

<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>

</div>



      </div></div></div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
