<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


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
</style>

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>


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
int iTemp=0;
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


String aszPublic="display:none;";
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
/*
if (userprofile.getUSPLocalOrgsTID()==iLocalOrgsTID){
	aszLocalOrgs="checked";
}
*/
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
}else{
   aszResume="display: none;";
   aszVolun="display: none;";
} 

String aszSkill2="display: inline;";
int iSkills2 = userprofile.getUSPVolSkills2TID();
String aszSkills2 = userprofile.getUSPVolSkills2();
if(aszSkills2.equalsIgnoreCase("") && (!(iSkills2 > 0) ) ){
   aszSkill2="display: none;";
}

String aszSkill3="display: inline;";
int iSkills3 = userprofile.getUSPVolSkills3TID();
String aszSkills3 = userprofile.getUSPVolSkills3();
if(aszSkills3.equalsIgnoreCase("") && (!(iSkills3 > 0) ) ){
   aszSkill3="display: none;";
}

String aszInterestArea2Display="display: inline;";
int iInterestArea2 = userprofile.getUSPVolInterestArea2TID();
String aszInterestArea2 = userprofile.getUSPVolInterestArea2();
if(aszInterestArea2.equalsIgnoreCase("") && (!(iInterestArea2 > 0) ) ){
   aszInterestArea2Display="display: none;";
}

String aszInterestArea3Display="display: inline;";
int iInterestArea3 = userprofile.getUSPVolInterestArea3TID();
String aszInterestArea3 = userprofile.getUSPVolInterestArea3();
if(aszInterestArea3.equalsIgnoreCase("") && (!(iInterestArea3 > 0) ) ){
   aszInterestArea3Display="display: none;";
}

String aszLang2Display="display: inline;";
int iLang2 = userprofile.getUSPVolLang2TID();
String aszLang2 = userprofile.getUSPVolLang2();
if(aszLang2.equalsIgnoreCase("") && (!(iLang2 > 0) ) ){
   aszLang2Display="display: none;";
}

String aszLang3Display="display: inline;";
int iLang3 = userprofile.getUSPVolLang3TID();
String aszLang3 = userprofile.getUSPVolLang3();
if(aszLang3.equalsIgnoreCase("") && (!(iLang3 > 0) ) ){
   aszLang3Display="display: none;";
}

String aszAffil2Display="display: inline;";
int iAffil2 = userprofile.getUSPOtherAffil2TID();
String aszAffil2 = userprofile.getUSPOtherAffil2();
if(aszAffil2.equalsIgnoreCase("") && (!(iAffil2 > 0) ) ){
   aszAffil2Display="display: none;";
}

String aszAffil3Display="display: inline;";
int iAffil3 = userprofile.getUSPOtherAffil3TID();
String aszAffil3 = userprofile.getUSPOtherAffil3();
if(aszAffil3.equalsIgnoreCase("") && (!(iAffil3 > 0) ) ){
   aszAffil3Display="display: none;";
}

if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
   aszFaith="display: none;";
   aszAffil2Display="display: none;";
   aszAffil3Display="display: none;";
}

String aszPd=userprofile.getUSPPassword();
userprofile.setPasswordConfirm(userprofile.getUSPPassword());

String aszUserType=userprofile.getUSPInternalUserTypeComment();
String aszTacLite=" AND !(tid=8059) AND !(tid=1222) ";

String aszFacebookHide = "";
String aszInternalComment = "drupal";
if(userprofile.getUSPInternalComment().equalsIgnoreCase("facebook") || userprofile.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
	aszInternalComment = "drupalfacebook";
	aszFacebookHide = "style = \"display:none;\"";
}

String aszUPnid="" + userprofile.getUserProfileNID();
String aszUPvid="" + userprofile.getUserProfileVID();
String aszUPlid="" + userprofile.getUserProfileLID();
String aszUID="" + userprofile.getUserUID();
String aszPersonNumber="" + userprofile.getUSPPersonNumber();

//session.putValue ("usertype",aszUserType);  
//session.putValue ("taclite",aszTacLite);  
//session.putValue ("upnid",aszUPnid);
//session.putValue ("upvid",aszUPvid);
//session.putValue ("uplid",aszUPlid);
//session.putValue ("uid",aszUID);
//session.putValue ("password1",aszPd);
//session.putValue ("password2",aszPd);


%>


<script language="javascript">

	function clicked_vol(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
	}
	function clicked_org(){
		document.getElementById('volun').style.display='none';
		document.getElementById('volunteertid').checked=false;
		document.getElementById('publicprofile').style.display='none';
	}

$(document).ready(function() {
	var siteUse = $('input:radio[name=siteusetype]:checked').val();
	if (siteUse==undefined || siteUse==null ){
		var siteUse="Volunteer";
		$('input:radio[name=siteusetype]')[0].checked=true;
	}
	if (siteUse=="Volunteer" || siteUse=="Both"){
		clicked_vol();
	}else {
		clicked_org();
	}
 });                                  


</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Add to Personal Profile</span>
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
<span style="float: left;">Add to Personal Profile</span>
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
        
<% // for google analytics tracking: %>

<% 
session.putValue ("MyIdentifier1","drupal");  // Storing Value into session Object
%>

<%//=userprofile.getUSPInternalUserTypeComment()%>
	<% String aszGoalPage = "/create/individual"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>

        <BR>

<html:form action="/register.do?indivaccnt" focus="username" >
<html:hidden property="method" value="processRegistration" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment" value="<%=aszInternalComment%>" />
<html:hidden property="internaltaclitecomment" value="<%=aszTacLite%>" />
<html:hidden property="internalusertypecomment" value="<%=aszUserType%>" />
<!--html:hidden property="personid" value="<%=aszPersonNumber%>" /-->
<html:hidden property="upnid" value="<%=aszUPnid%>" />
<html:hidden property="upvid" value="<%=aszUPvid%>" />
<html:hidden property="uplid" value="<%=aszUPlid%>" />
<html:hidden property="uid" value="<%=aszUID%>" />
<html:hidden property="password1" value="<%=aszPd%>" />
<html:hidden property="password2" value="<%=aszPd%>" />
<html:hidden property="personalityei"/>
<html:hidden property="personalitysn"/>
<html:hidden property="personalityft"/>
<html:hidden property="personalityjp"/>
<html:hidden property="personalitytype"/>
<html:hidden property="personalitytypetid"/>
<html:hidden property="serviceareas"/>
<html:hidden property="skilltypes"/>
<html:hidden property="lookingfor"/>
<html:hidden property="spiritualdev"/>
<html:hidden property="ministryareascause"/>
<html:hidden property="globalissues"/>
<html:hidden property="organizationaldev"/>
<html:hidden property="reconciliation"/>
<html:hidden property="otherskills"/>
<html:hidden property="otherpassions"/>
<html:hidden property="otherlearninginterests"/>

		<div align="left">
      <h2>A recent update to our system requires that you fill out additional fields for your account to work correctly. Please fill out the following fields (* indicates required).
      <br></h2>
      <FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>
              <br><span class="criticaltext">*</span> <b>Before changing it, remember that your email address is used to login.</b>
              <br><br>
              
            <table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
			  <tr>
                <TD>Username<span class="criticaltext">*</span></TD>
                <TD>
                <html:text property="username" styleId="username" size="30" styleClass="textinputwhite"/>
                </TD>
					<td height="23"></td>
				</tr>
			  <tr>
                <TD>Email<span class="criticaltext">*</span></TD>
                <TD>
                <html:text property="email1addr" styleId="email1addr" size="30" styleClass="textinputwhite"/>
                </TD>
					<td height="23"></td>
				</tr>
				<tr>
                <TD>First name <span class="criticaltext">*</span> </TD>
                <TD><html:text property="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
				<tr>
                <TD>Last name <span class="criticaltext">*</span> </TD>
                <TD><html:text property="namelast" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
			<tr><td colspan=4>
				
<br><br>
Are you using this site as a volunteer, an organization, or both? <span class="criticaltext">*</span><br>           	
<html:radio styleClass="radio" value="Volunteer" property="siteusetype" onclick="clicked_vol()" /> Volunteer &nbsp;
<html:radio styleClass="radio" value="Organization" property="siteusetype" onclick="clicked_org()" /> Organization &nbsp;
<html:radio styleClass="radio" value="Both" property="siteusetype" onclick="clicked_vol()" /> Both 
            	
<br><br>
</td></tr>
	<tr><td colspan=2>

<div id="volun" style="<%=aszVolun%>">
<table border=0 cellpadding=2 cellspacing=2>        	
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" onclick="document.getElementById('publicprofile').style.display='inline';" <%=aszDirectory%>/></td>
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
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
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




<table border=0>        	
				
			<tr>
				<td>
				Volunteer Availability</td>
				<td colspan=2 height=30>
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
    <div id="better-select-edit-taxonomy-<%out.print(vidVolSkill);%>" class="better-select betterfixed" <%=aszFacebookHide%>>
    <div class="form-item">
	<label><b>Personal Skills: </b></label>
		    <!--html:select property="skilltypesarray" multiple="true" size=5 -->
		    <!--select name="skilltypesarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[50];
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
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Musician</label> ");
								out.println(" </div> ");
							}else if (iSubType == 8122){
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Deaf Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
								out.println(" </div> ");
							}
							iTemp++;
						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
					} 					
				%>
			</div>
			</div>
			</div>
			<!--/select-->
			<!--/html:select-->
	
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidVolInterestArea);%>" class="better-select betterfixed" <%=aszFacebookHide%>>
    <div class="form-item">
	<label><b>Volunteer Interest Area : </b></label>
		    <!--html:select property="serviceareasarray" multiple="true" size=5 -->
		    <!--select name="serviceareasarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[50];
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
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Disabilities Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 6843){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Senior / Elderly Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4774){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Food Service / Hunger</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4782){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
								out.println(" >Prison Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
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
					} else { 
						for(int index=0; index < ainterestareaList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
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
                

<table border=0>        	
	<tr><td colspan=2 height="30"></td></tr>
				<tr>
                <TD>
				Phone <span class="criticaltext">*</span></FONT>
				</TD>
                <TD ><html:text property="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr>
        <TD>Address</TD>
        <TD height="30"><html:text property="addrline1" styleId="addrline1" size="25" styleClass="textinputwhite"/></TD>
		</tr>

		<tr>
        <TD>City</TD>
        <TD height="30"><html:text property="addrcity" styleId="addrcity" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		
		<tr>
<TD>State/Province <%=userprofile.getUSPAddrStateprov()%></TD>
<TD height="30">
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
                <TD>Postal Code<FONT class=req>&nbsp;<span class="criticaltext">*</span>&nbsp;</FONT> </TD>
                <TD colspan=2 height="23"><html:text property="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
              </tr>
				<tr>
                <TD>Country &nbsp;<span class="criticaltext">*</span>&nbsp; </TD>
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
                </TD>
              		<td height="27"></td>
				</tr>
				
				
				
<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

				
	<tr><td colspan=4 height="30"></td></tr>

			<tr><td >
					&nbsp;Are You a Christian?</td><td>
					<html:radio styleClass="check" value="Yes" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<html:radio styleClass="check" value="No" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
<tr><td colspan="3">
		<br><br>
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
								
								
								
								if ( (userprofile.getUSPOtherAffilP().length() < 1 || userprofile.getUSPOtherAffil1TID() < 1) ){
									// set values for customized partner (subdomain) to be first choice for organizational affiliation
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
									} else if(aszHostID.equalsIgnoreCase("volengyouthpartners")){
										iTemp = iYPN;
										aszTemp="YouthPartnersNET";			
									}
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
				<td></td>
				<td colspan=2>
					<% if ( (userprofile.getUSPOtherAffilP().length() < 1 || userprofile.getUSPOtherAffil1TID() < 1) ){
							// hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
							// if on specialized partner, show 2nd div by default, b/c first will be set to partner
							if(	(aszHostID.equalsIgnoreCase("volengccda")==false) &&
								(aszHostID.equalsIgnoreCase("volengfia")==false) &&
								(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
								(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
								(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
								(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 			
							){
								aszAffil2Display = "display: none;";
							}			
						}
					%>
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
				

<tr><td colspan=3>
<br>
<table width="540" border="0" cellpadding="0" cellspacing="0" id="splash" >
				<tr>
                <TD height="20" colspan=4>Terms &amp; Conditions </TD>
              		</tr>
		<tr>     
        <TD height="30" >
		<input type="checkbox" styleClass="check" value="Yes" id="agreeflag1" name="agreeflag1" checked=checked/>
		</td><td colspan=3>
        I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a>.<span class="criticaltext">*</span>
        </TD>
        </tr>
				<tr>
              
                <TD height="30" >
					<html:checkbox styleClass="check" value="Yes" property="agreeflag2"/>
				</td><td colspan=3>
                	I am at least 16 years old or have my parent's permission to use 
					<a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a>.<span class="criticaltext">*</span>
                </TD>
                	</tr>


<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
				<tr>
              
                <TD height="30" >
					<html:checkbox styleClass="check" value="Yes" property="agreeflagworldvision"/>
				</td><td colspan=3>
                	I agree to the World Vision <a href="http://www.worldvision.org/content.nsf/pages/privacy-policy">privacy policy</a>.<span class="criticaltext">*</span>
                </TD>
                	</tr>
<% } %>



<tr>
              		<td width="40">&nbsp;</td>
              		<td width="90">&nbsp;</td>
                <TD height="75">
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
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
