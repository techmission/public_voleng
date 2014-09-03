<%@ include file="/template_include/topjspnologin1.inc" %>

<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

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
int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519,
	ministryAreasTID=12521, iLocalVolTID = 17254, iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
	iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266,
	iDisasterRlfTID = 21632,iLocalOrgsTID = 21853,
	personalityTypeTID = userprofile.getUSPPersonalityTID();
int iTemp=0, iTempValue=0;
int iArraySize = 0;
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

String aszUserType=userprofile.getUSPInternalUserTypeComment();
String aszTacLite=" AND !(tid=8059) AND !(tid=1222) ";
%>


<script language="javascript">

	function clicked_vol(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
	}
	function clicked_both(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
	}
	function clicked_org(){
		document.getElementById('volun').style.display='none';
		document.getElementById('volunteertid').checked=false;
		document.getElementById('publicprofile').style.display='none';
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=false;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=false;
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
	}

$(document).ready(function() {
 });  
                                

</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Create Account</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Create Account</span>
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

<div id="form1">
<h2>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Please complete the required fields below to create a full account:
</H2>
<html:form action="/register.do" target="_self">
<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>


<html:hidden property="method" value="processUpdateAccount3" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment"/>

<html:hidden property="internaltaclitecomment" value="<%=aszTacLite%>" />
<html:hidden property="internalusertypecomment" value="<%=aszUserType%>" />
<html:hidden property="upnid" value="<%=aszUPnid%>" />
<html:hidden property="upvid" value="<%=aszUPvid%>" />
<html:hidden property="uplid" value="<%=aszUPlid%>" />
<html:hidden property="uid" value="<%=aszUID%>" />

<html:hidden property="email1addr" value="<%=userprofile.getUSPEmail1Addr()%>" />
<html:hidden property="username" value="<%=userprofile.getUSPUsername()%>" />
<html:hidden property="namefirst" value="<%=userprofile.getUSPNameFirst()%>" />
<html:hidden property="namelast" value="<%=userprofile.getUSPNameLast()%>" />
<html:hidden property="phone1" value="<%=userprofile.getUSPPhone1()%>"/>
<html:hidden property="agreeflag1" value="Yes" />
<html:hidden property="siteusetype" value="<%=userprofile.getUSPSiteUseType()%>" />
<html:hidden property="addrpostalcode" value="<%=userprofile.getUSPAddrPostalcode()%>" />
<html:hidden property="addrcountryname" value="<%=userprofile.getUSPAddrCountryName()%>" />



<%
int length=0;
int[] a_iLookingFor=userprofile.getUSPLookingForArray();
length=a_iLookingFor.length;
for(int i=0; i<length; i++){
	out.println("<input type=\"hidden\" name=\"lookingforarray\" id=\"lookingforarray\" value=\""+a_iLookingFor[i]+"\">"); 
}
int[] a_iSkill=userprofile.getUSPSkillTypesArray();
length=a_iSkill.length;
for(int i=0; i<length; i++){
	out.println("<input type=\"hidden\" name=\"skilltypesarray\" id=\"skilltypesarray\" value=\""+a_iSkill[i]+"\">"); 
}
int[] a_iServiceArea=userprofile.getUSPServiceAreasArray();
length=a_iServiceArea.length;
for(int i=0; i<length; i++){
	out.println("<input type=\"hidden\" name=\"serviceareasarray\" id=\"serviceareasarray\" value=\""+a_iServiceArea[i]+"\">"); 
}
%>


<html:hidden property="volunteertid" value="<%=\"\"+userprofile.getUSPVolunteerTID()%>" />

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

	<div width="500" border="0" id="firstsection" class="section">
				

<div id="address">
<b>Address:</b>
<div  style="border: 1px solid #666; margin: 30; width:400px;">
<table>
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
</table>
</div>
<br>
</div>

<label><b>
Personal Volunteer Resum&eacute;  (PUBLICLY VISIBLE) <A href="javascript: alert('Enter your personal resum&eacute;  in the box to the right. When you indicate interest in a volunteer opportunity, we will e-mail your resum&egrave to the organization for you. Organizations will also be able to search resum&eacute; s to find the volunteers that they are looking for.')">[What's This?]</A>
</b></label>
			<html:textarea rows="20" cols="70" property="volresume"/>

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
	<div width="500" border="0" id="secondsection" class="section">
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
									iTempValue = a_iContainer[indexArray];
									if(iTempValue==iSubType) out.print(" checked ");
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


<table border=0>        	
<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

	<tr><td colspan=4 height="10"></td></tr>

			<tr><td colspan=1 width="217">
					Are You a Christian?</td><td>
					<html:radio styleClass="check" value="Yes" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<html:radio styleClass="check" value="No" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
		<tr><td colspan=3>		
		<div id="faith"  style="<%=aszFaith%>">
			<table border="0" cellpadding="0" cellspacing="0" id="splash">
			<tr>
				<td height="30" colspan=2>Do You Attend Church Regularly?</td>
				<td>
						<html:radio styleClass="check" value="Yes" property="attendchurch"/> Yes
						<html:radio styleClass="check" value="No" property="attendchurch"/> No
				</td>
			</tr>
			<tr>
				<td height="30">Denominational Affiliation</td>
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
				<td height="30">Other Affiliation</td>
				<td colspan=2>
						<SELECT id="indivotheraffil1tid" name="indivotheraffil1tid" class="smalldropdown" style="margin-top: 5px;"
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengabs")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 			
			){ 
			%>
				onchange="javascript:document.getElementById('affil2').style.display='inline';"
			 <% } %>
			> 
			<option value=""></option>
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
			if(aszHostID.equalsIgnoreCase("volengccda")){
				iTemp = iCCDA;
				aszTemp="Christian Community Development Association (CCDA)";
			} else if(aszHostID.equalsIgnoreCase("volengfia")){
				iTemp = iWorldVision;
				aszTemp="World Vision";			
			} else if(aszHostID.equalsIgnoreCase("volenghlic")){
				iTemp = iHLIC;
				aszTemp="Here's Life Inner City";			
			} else if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
				iTemp = iSalvArmy;
				aszTemp="Salvation Army";			
			} else if(aszHostID.equalsIgnoreCase("volengagrm")){
				iTemp = iAGRM;
				aszTemp="Association of Gospel Rescue Missions (AGRM)";			
			} else if(aszHostID.equalsIgnoreCase("volengabs")){
				iTemp = iABS;
				aszTemp="American Bible Society";			
			} else if(aszHostID.equalsIgnoreCase("volengyouthpartners")){
				iTemp = iYPN;
				aszTemp="YouthPartnersNET";			
			} else {
								iTemp = userprofile.getUSPOtherAffil1TID();
			}
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
				<td></td><td colspan=2>
					<div id="affil2" 
			<% // hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengabs")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 	&&
					userprofile.getUSPOtherAffil1TID() < 1		
			){ %>
				style="display: none;"
			<% } %>
			> 
						<SELECT id="indivotheraffil2tid" name="indivotheraffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil3').style.display='inline';"/>
			<option value=""></option>
			
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
								iTemp = 0;
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
				<td></td><td colspan=2>
					<div id="affil3" style="display: none;">
						<SELECT id="indivotheraffil3tid" name="indivotheraffil3tid" class="smalldropdown" style="margin-top: 5px;" />
						<%//onchange="javascript:document.getElementById('affil4').style.display='inline';"%> 
							<option value=""></option>
							<%
								iTemp = 0;
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
				<td></td><td colspan=2>
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
				<td></td><td colspan=2>
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
			<tr><td width="150"></td><td width="72"></td><td></td></tr>
		</table>
	</div>
</td></tr>

<% } %>
</table>

<table>
<tr><td colspan=3 height="15"></td></tr>

<tr>
	<td width="250"></td>
	<TD>
		<INPUT class=submit type=submit value=Continue>
	</TD>
</tr>
<tr><td colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td></tr>
</table>
</div>
</html:form>



<div align=center style="display:none;">
<button type="button" id="submitButton" onclick="submitBoth()">Continue</button>
</div>

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
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
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
