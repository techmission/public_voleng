<%@ include file="/template_include/topjsploginreq1.inc" %>

<%@ include file="/template/header.inc" %>

<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style>
.section{
<% 
// test to see if it's from a URL that has a narrower template
if(	aszNarrowTheme.equalsIgnoreCase("true") ){%>
padding-left: 75px;
<% }else{ %>
padding-left: 150px;
<% } %> 
}
</style>

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>
<!-- personality type is: <%=userprofile.getUSPPersonality()%> <%=userprofile.getUSPPersonalityTID()%> -->

<%
int vidPosType=34, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		//vidWorkStudy=264, 
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
		vidVolSkill=31, vidService=32, 
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
int iArraySize = 0;

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
        aszSubdomain = "iVolunteering.org";
}

String aszTermsChecked="", aszOrgsiteusetype="", aszChurchsiteusetype="", aszVolunsiteusetype="";
if(userprofile.getUSPSiteUseType().contains("Volunteer") ){
	aszVolunsiteusetype="checked=checked";
}else if(userprofile.getUSPSiteUseType().equals("Organization") ){
	aszOrgsiteusetype="checked=checked";
}else if(userprofile.getUSPSiteUseType().equals("Church") ){
	aszChurchsiteusetype="checked=checked";
}

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

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
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

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

aCodes.getStateInList( aStateList, 101 );
aCodes.getTaxonomyCodeList( askillsList, vidVolSkill );
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

String aszPublic="display:none;";
String aszPhotoLink="display:none;";

String aszInternshipInterest = "";
if(userprofile.getInternshipInterest()) {
  aszInternshipInterest = "checked=true";
}
if(!(userprofile.getUSPAddrCountryName().equals("us") ||
     userprofile.getUSPAddrCountryName().equals("")))
  aszInternshipInterest += " disabled=true";

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
	aszPhotoLink="display:inline;";
}else{
   aszResume="display: none;";
   aszVolun="display: none;";
	aszPhotoLink="display:none;";
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

String aszUserType=userprofile.getUSPInternalUserTypeComment();
String aszTacLite=" AND !(tid=8059) AND !(tid=1222) ";

//session.putValue ("usertype",aszUserType);  
//session.putValue ("taclite",aszTacLite);  
//session.putValue ("upnid",aszUPnid);
//session.putValue ("upvid",aszUPvid);
//session.putValue ("uplid",aszUPlid);
//session.putValue ("uid",aszUID);

int iBirthYear = userprofile.getBirthYear();
String aszBirthYear="";
if(iBirthYear>0)	aszBirthYear=""+iBirthYear;

%>

<script language="javascript">

	function clicked_vol(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('publicprofile').style.display='inline';
		document.getElementById('profilephoto').style.display='inline';
	}
	function clicked_org(){
		document.getElementById('volun').style.display='none';
		document.getElementById('publicprofile').style.display='none';
		document.getElementById('profilephoto').style.display='none';
	}
	function clicked_church(){
		document.getElementById('volun').style.display='none';
		document.getElementById('publicprofile').style.display='none';
		document.getElementById('profilephoto').style.display='none';
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
		document.getElementById('publicprofile').style.display=vis;
	}

$(document).ready(function() {
	var siteUse = $('input:radio[name=siteusetype]:checked').val();
	if (siteUse=="Volunteer" || siteUse=="Both"){
		clicked_vol();
	}else 	if (siteUse=="Church"){
		clicked_church();
	}else {
		clicked_org();
	}
 });                                  


</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Edit Personal Profile</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>


<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Edit Personal Profile</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
	edit personal profile  </div>
</div>
<% } %>

<logic:notEmpty name="userprofile">
</logic:notEmpty>
<logic:empty name="userprofile">
</logic:empty>

 <div id="body">
        
        <BR>

<html:form action="/register.do"  >
<input type="hidden" name="method" value="processIndProfEdit1" />
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="internaltaclitecomment" value="<%=aszTacLite%>" />
<input type="hidden" name="internalusertypecomment" value="<%=aszUserType%>" />
<!--html:hidden property="personid" value="<%=aszPersonNumber%>" /-->
<input type="hidden" name="upnid" value="<%=aszUPnid%>" />
<input type="hidden" name="upvid" value="<%=aszUPvid%>" />
<input type="hidden" name="uplid" value="<%=aszUPlid%>" />
<input type="hidden" name="uid" value="<%=aszUID%>" />

		<div align="left">
      <h2>Please complete the required fields below.</h2>
      <FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT><br>

            <table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >			  
			  <tr>
                <TD><b>Username</b> <span class="criticaltext">*</span> </TD>
                <TD>
                <input type="text" name="username" value="<%=userprofile.getUSPUsername()%>" styleId="username" size="30" styleClass="textinputwhite"/>
                </TD>
					<td height="23"></td>
				</tr>
			  <tr>
                <TD><b>Email</b> <span class="criticaltext">*</span> </TD>
                <TD>
                <input type="text" name="email1addr" value="<%=userprofile.getUSPEmail1Addr()%>" styleId="email1addr" size="30" styleClass="textinputwhite"/>
                </TD>
					<td height="23"></td>
				</tr>
				<tr>
                <TD colspan=2>Click here to change your password:&nbsp;
                <input type="radio" name="showPassword" onclick="javascript:document.getElementById('password').style.display='inline';" ></TD>
					<td height="23"></td>
				</tr>
<tr><td colspan=2>
<div id="password" style="display:none;"><hr>
<table cellpadding=0 cellspacing=0 border=0>
				<tr>
                <TD width="150">New Password</TD>
                <TD height="30">
					<input type="password" name="password1" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
             		</tr>
				<tr>
                <TD>Confirm Password</TD>
                <TD height="30">
					<input type="password" name="password2" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
				</tr>
		</table><hr></div></td></tr>
				<tr>
                <TD><b>First name</b> <span class="criticaltext">*</span> </TD>
                <TD><input type="text" name="namefirst" value="<%=userprofile.getUSPNameFirst()%>" styleId="namefirst" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
				<tr>
                <TD><b>Last name</b> <span class="criticaltext">*</span> </TD>
                <TD><input type="text" name="namelast" value="<%=userprofile.getUSPNameLast()%>" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
				
			<tr><td colspan=4>
				
<br>
	<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
			<b>Are you using this site as a volunteer/individual or an organization?</b> <span class="criticaltext">*</span><br>           	
	<% }else{ %>
		<% if(aszSecondaryHost.equalsIgnoreCase("volengurbmin")){ %>
				<b>Are you using this site as an individual or an organization?</b> <span class="criticaltext">*</span><br>           	
		<% }else{ %>
				<b>Are you using this site as a volunteer, organization, or church?</b> <span class="criticaltext">*</span><br>           	
		<% } %>
	<% } %>
			<input type="radio" styleClass="radio" value="Volunteer" name="siteusetype" id="siteusetype" onclick="clicked_vol()" <%=aszVolunsiteusetype%> /> Volunteer / Individual &nbsp;
		<% if(! aszHostID.equalsIgnoreCase("volengchurch")){ %>
			<input type="radio" styleClass="radio" value="Organization" name="siteusetype" id="siteusetype" onclick="clicked_org()"  <%=aszOrgsiteusetype%> /> <%=aszOrgOrChurch%> &nbsp;
		<% } %>
	<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
	<% }else{ %>
			<input type="radio" styleClass="radio" value="Church" name="siteusetype" id="siteusetype" onclick="clicked_church()" <%=aszChurchsiteusetype%> /> Church 
	<% } %>
            	
<br><br>
</td></tr>
	<tr><td colspan=2>

<div id="volun" style="<%=aszVolun%>">
<table border=0 cellpadding=2 cellspacing=2>     
            <tr>
				<td height="30">
				<input type="checkbox" styleClass="Check" value="1" name="internshipinterest" id="internshipinterest" <%=aszInternshipInterest%> /></td>
				<td colspan=2>Please send me information about applying for <a href="http://www.christianvolunteering.org/cms/cv/internships">ChristianVolunteering.org's City Vision Internship program</a> that provides free accredited college courses and room/board for full-time interns?</td>
			</tr> 	
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" onclick="clicked_public(this)" <%=aszDirectory%>/></td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
			</tr>
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" onclick="clicked_public(this)" <%=aszSubscribe%>/></td>
				<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("voelngivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile in the following areas:</td>
			</tr>

</table>
<br>






<div id="publicprofile" style="<%=aszPublic%>">
<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="better-select">
		<label>I am Looking for: <span class="criticaltext">*</span> </label>
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
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==2)	out.println("</td><td>");
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
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==2)	out.println("</td><td>");
							}
						}
					} 					
				%>
</td></tr></table></div>
<br>
</div>






<div id="address">
Address:
<div  style="border: 1px solid #666; margin: 10;">
<table>
				<tr>
                <TD width=120>
				<b>Phone</b> <span class="criticaltext">*</span>
				</TD>
                <TD ><input type="text" name="phone1" value="<%=userprofile.getUSPPhone1()%>" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr>
        <TD>Street</TD>
        <TD height="23"><input type="text" name="addrline1" value="<%=userprofile.getUSPAddrLine1()%>" styleId="addrline1" size="25" styleClass="textinputwhite"/></TD>
		</tr>

		<tr>
        <TD>City</TD>
        <TD height="23"><input type="text" name="addrcity" value="<%=userprofile.getUSPAddrCity()%>" styleId="addrcity" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		
		<tr>
<TD>State/Province</TD>
<TD height="23">
                <SELECT id="addrstateprov" name="addrstateprov" class="smalldropdown"> 
					<option value=""></option>
					<%
					aszTemp = userprofile.getUSPAddrStateprov();
					if(null != aStateList){
						for(int index=0; index < aStateList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCSPStateCode();
							out.println(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
						}
					}
					%>
              </SELECT><!--&nbsp; Other Province html:text property="addrprov" styleId="addrprov" size="12" styleClass="textinputwhite"/-->
</TD>
</tr>
				<tr>
                <TD><b>Postal Code</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=2 height="23"><input type="text" name="addrpostalcode" value="<%=userprofile.getUSPAddrPostalcode()%>" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
              </tr>
				<tr>
                <TD><b>Country</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=4>
                	<SELECT id="addrcountryname" name="addrcountryname" class="smalldropdown" > 
					<option value=""></option>
					<%
					aszTemp= userprofile.getUSPAddrCountryName();
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>

                    </SELECT>
                    <script>
                      $('#addrcountryname').change(function() {
                        if($(this).val() != 'us') {
                          $('#internshipinterest').removeAttr('checked');
						  $('#internshipinterest').attr('disabled', true);
						}
						else {
						  $('#internshipinterest').removeAttr('disabled');
						}
                      }); 
                    </script>
                </TD>
              		<td height="27"></td>
				</tr>
				<tr>
                <TD><b>Birth Year</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=2 height="23"><input type="text" name="birthyear" value="<%=aszBirthYear%>" size="12" styleClass="textinputwhite"/></TD>
              </tr>
</table>
</div>
</div>

<table border=0>        	
			<tr>
				<td>
				Volunteer Availability</td>
				<td colspan=2 height=35>
		    <select class="smalldropdown" id="volavailtid" name="volavailtid" style="margin-top: 5px;" >
               <option value=""></option>
               <%
					aszTemp = userprofile.getUSPVolAvail();
					iTemp = userprofile.getUSPVolAvailTID();
					for(int index=0; index < avolavailList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)avolavailList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
				
				</td>
				</tr>
</table>				
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidService);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Volunteer Interest Area : </b></label>
		    <!--html:select property="serviceareasarray" multiple="true" size=5 -->
		    <!--select name="serviceareasarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[50];
					iTemp = 0;
					a_iContainer = userprofile.getUSPServiceAreasArray();
						for(int index=0; index < aServiceList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && (
								iSubType == 4760 || // Bible Study
								iSubType == 4764 || // Church Planting
								iSubType == 4772 || // Evangelism
								iSubType == 4773 || // Family / Adults Ministry
								iSubType == 4783 || // Single Parents/Crisis Pregnancy
								iSubType == 4787 || // Vacation Bible School
								iSubType == 4789 || // Women's Ministry
								iSubType == 7341 || // Christian/Catholic Schools
								iSubType == 7342 )){ // Religious Education
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 4763){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Children and Youth</label> ");
								out.println(" </div> ");
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 4788){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Visitation/Friendship</label> ");
								out.println(" </div> ");
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 4767){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Disabilities Outreach</label> ");
								out.println(" </div> ");
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 6843){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Senior / Elderly Outreach</label> ");
								out.println(" </div> ");
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 4774){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Food Service / Hunger</label> ");
								out.println(" </div> ");
							}else if (aszSecondaryHost.equalsIgnoreCase( "volengivol" ) && iSubType == 4782){
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Prison Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
								out.println(" </div> ");
							}
							iTemp++;
						}
				%>
			</div>
			</div>
			</div>
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidVolLang);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Languages Spoken:  </b></label>
		    <!--html:select property="languagesarray" multiple="true" size=5 -->
		    <!--select name="languagesarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[255];
					iTemp = 0;
					a_iContainer = userprofile.getUSPLanguagesArray();
						for(int index=0; index < alangList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)alangList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolLang+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolLang+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"languagesarray\" name=\"languagesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
				%>
			</div>
			</div>
			</div>
			<!--/html:select-->
			<br>
			<br>
<label><b>
Personal Volunteer Resum&eacute;  (PUBLICLY VISIBLE) <A href="javascript: alert('Enter your personal resum&eacute;  in the box to the right. When you indicate interest in a volunteer opportunity, we will e-mail your resum&egrave to the organization for you. Organizations will also be able to search resum&eacute; s to find the volunteers that they are looking for.')">[What's This?]</A>
</b></label>
                <html:textarea rows="20" cols="65" property="volresume"/>











<br><br>

<table border=0>        	
			<tr>
				<td colspan=2>
				Volunteer Availability</td>
				<td colspan=2 height=30>
		    <select class="smalldropdown" id="volavailtid" name="volavailtid" style="margin-top: 5px;" >
               <option value=""></option>
               <%
					iTemp = 0;
					for(int index=0; index < avolavailList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)avolavailList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
				
				</td>
			</tr>
</table>
</div>
<br><h3>Please check your Learning Interests/Passions:</h3><br>
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidCauseTopic+"-"+spiritualTID);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Ministry Areas/Causes:  </b></label>
		    <!--html:select property="ministryareascausearray" multiple="true" size=5 -->
		    <!--select name="ministryareascausearray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-noscroll">
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
	<label><b>Spiritual Development:  </b></label>
		    <!--html:select property="spiritualdevarray" multiple="true" size=5 -->
		    <!--select name="spiritualdevarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-noscroll">
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
	<label><b>Global Issues:  </b></label>
		    <!--html:select property="globalissuesarray" multiple="true" size=5 -->
		    <!--select name="globalissuesarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-noscroll">
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
	<label><b>Organizational Development:  </b></label>
		    <!--html:select property="organizationaldevarray" multiple="true" size=5 -->
		    <!--select name="organizationaldevarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-noscroll">
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
	<label><b>Reconciliation & Culture:  </b></label>
		    <!--html:select property="reconciliationarray" multiple="true" size=5 -->
		    <!--select name="reconciliationarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-noscroll">
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












<table border=0>        	
<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

	<tr><td colspan=4 height="10"></td></tr>

			<tr><td >
					&nbsp;Are You a Christian?</td><td>
					<html:radio styleClass="check" value="Yes" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<html:radio styleClass="check" value="No" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
	<tr><td colspan=4 height="10"></td></tr>
<tr><td colspan="3">
		<div id="faith"  style="<%=aszFaith%>">
			<table width="519" border="0" cellpadding="0" cellspacing="0" id="splash">
			<tr>
				<td height="30" colspan=2>&nbsp;Do You Attend Church Regularly?</td>
				<td>
						<html:radio styleClass="check" value="Yes" property="attendchurch"/> Yes
						&nbsp; &nbsp; 
						<html:radio styleClass="check" value="No" property="attendchurch"/> No
				</td>
			</tr>
			<tr>
				<td height="30">&nbsp;Denominational Affiliation</td>
				<td colspan=2>
						<select id="indivdenomaffiltid" name="indivdenomaffiltid" class="smalldropdown">
							<option value=""></option>
							<%
								aszTemp = userprofile.getUSPDenomAffilP();
								iTemp = userprofile.getUSPDenomAffilTID();
								for(int index=0; index < afiliationList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
									if(iTid == iTemp ) out.print(" selected=selected ");
									out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
								}
							%>
						</select>
				</td>
			</tr>
			
			<tr>
				<td height="30">&nbsp;Other Affiliation</td>
				<td colspan=2>
						<SELECT id="indivotheraffil1tid" name="indivotheraffil1tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil2').style.display='inline';">
							<option value=""></option>
							<% 
								aszTemp = userprofile.getUSPOtherAffilP();
								iTemp = userprofile.getUSPOtherAffil1TID();
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 22118 ||
										iSubType == 778 ||
										iSubType == 8059 ||
										iSubType == 1222){
									}else{
										out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
										if(iTid == iTemp ) out.print(" selected=selected ");
										out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
									}
								}
							%>
						</SELECT>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan=2>
					<div id="affil2" style="<%=aszAffil2Display%>">
						<SELECT id="indivotheraffil2tid" name="indivotheraffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil3').style.display='inline';"> 
						<option value=""></option>
						
							<% 
								aszTemp = userprofile.getUSPOtherAffilP();
								iTemp = userprofile.getUSPOtherAffil2TID();
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 8059 ||
										iSubType == 1222){
									}else{
										out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
										if(iTid == iTemp ) out.print(" selected=selected ");
										out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
									}
								}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan=2>
					<div id="affil3" style="<%=aszAffil3Display%>">
						<SELECT id="indivotheraffil3tid" name="indivotheraffil3tid" class="smalldropdown" style="margin-top: 5px;" > 
						<option value=""></option>
						
							<% 
								aszTemp = userprofile.getUSPOtherAffilP();
								iTemp = userprofile.getUSPOtherAffil3TID();
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 8059 ||
										iSubType == 1222){
									}else{
										out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
										if(iTid == iTemp ) out.print(" selected=selected ");
										out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
									}
								}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan=2>
					<div id="affil4" style="display: none;">
						<SELECT class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil5').style.display='inline';"> 
							<option value=""></option>
							<%
							aszTemp = "";
							for(int index=0; index < apartnersList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getAPCDisplay();
								out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
								if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan=2>
					<div id="affil5" style="display: none;">
						<SELECT class="smalldropdown" style="margin-top: 5px;"> 
							<option value=""></option>
							<%
							aszTemp = "";
							for(int index=0; index < apartnersList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getAPCDisplay();
								out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
								if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
		</table>
	</div>
</td></tr>
				
<% } %>
			
			
				
				
				</table></div>
</td></tr>
				
			<tr><td colspan=4>
<div id="profilephoto" style="<%=aszPhotoLink%>">
<a href="http://www.urbanministry.org/user/<%=aszUID%>/edit#edit-picture-upload-wrapper">Add Photo</a>				
</div>
</td></tr>
				

<tr><td colspan=3>


<table border="0" cellpadding="0" cellspacing="0" id="splash" >
<tr>
              		<td width="190">&nbsp;</td>
                <TD height="23">
                  <DIV class=clear style="HEIGHT: 10px"></DIV>
                  <INPUT type=hidden name=submit> <INPUT class=submit type=submit value=Continue> 
                 </TD>
             		</tr>
				<tr>
                <TD colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td>
              		<td height="21"></td>
				</tr>

            </TABLE>
</td></tr></table>

</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>
    
    </div>


<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>

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

<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
