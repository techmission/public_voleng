<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<% if( aszHostID.equalsIgnoreCase( "volengfycsandbox" ) ) 
{ // these templates get screwed up with this new page; include the old start page for vols
%>
	<jsp:include page="/personality_ministry_areas2.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>


<%@page import="java.util.*" %>
<%@page import="java.util.List"%>
<%@page import="java.io.IOException"%>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<%//@ include file="/template_include/facebookapi_keys.inc" %>

<% boolean needsLoginIFrame = false; %>

<!--
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script>
  FB.init({
    appId  : '<%=appapikey%>',
    status : true, // check login status
    cookie : true, // enable cookies to allow the server to access the session
    xfbml  : true  // parse XFBML
  }); 
</script>
<script type="text/javascript">

function status(){
FB.getLoginStatus(function(response) {
  if (response.session) {
    // logged in and connected user, someone you know
	alert("logged in");
  } else {
    // no user session available, someone you dont know
	alert("not logged in");
  }
});
}


FB.login(function(response) {
alert("login");
  if (response.session) {
    if (response.perms) {
      // user is logged in and granted some permissions.
      // perms is a comma separated list of granted permissions
	  alert("logged in, granted perms");
	  FB.api('/me', function(response) {
  		alert(response.email);
		});

    } else {
      // user is logged in, but did not grant any permissions
	  alert("logged in, no perms");
	  FB.api('/me', function(response) {
  		alert(response.name);
		});

    }
  } else {
    // user is not logged in
	alert("not logged in");
  }
}, {perms:'email'});

</script>
-->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<div id="fb-root"></div>
 <script src="http://connect.facebook.net/en_US/all.js"></script>
 <script>
   FB.init({
     appId  : '<%=appid%>',
     status : true, // check login status
     cookie : true, // enable cookies to allow the server to access the session
     xfbml  : true,  // parse XFBML
	 oauth  : true
   });
   
   window.fbAsyncInit = function(){
   
      FB.Canvas.setAutoGrow();
	  
	}
 </script>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<% 
Date today ; 
long localUnixTime;
today = new java.util.Date();
localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison

JSONObject obj = new JSONObject();
obj.put("pic", "http://www.christianvolunteering.org/imgs/results-img.gif");
obj.put("name", "");
out.println("<!-- ali modified -->");

if(appapikey!=null && appsecret!=null){
	if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || 
		aszHostID.equalsIgnoreCase( "volengfycsandbox" ) 
	){ 
		//FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );
		if(session.getAttribute("facebookapikey")==null){
			session.setAttribute("facebookapikey",appapikey);
		}else if(session.getAttribute("facebookapikey").toString().length()<1){
			session.setAttribute("facebookapikey",appapikey);
		}
		if(session.getAttribute("facebooksecretkey")==null){
			session.setAttribute("facebooksecretkey",appsecret);
		}else if(session.getAttribute("facebooksecretkey").toString().length()<1){
			session.setAttribute("facebooksecretkey",appsecret);
		}

		String sessionKey = "";
		String expireTime = "";
		if(session.getAttribute("FB_session_key")!=null){
			sessionKey = (String) session.getAttribute("FB_session_key");
		}
		if(session.getAttribute("FB_session_key_expire")!=null){
			expireTime = (String) session.getAttribute("FB_session_key_expire");
		}
		long expireLong=0;
		try{
			expireLong = Long.parseLong(expireTime.trim());
		}catch(NumberFormatException nfe){
		}
		long tempLong = localUnixTime+3600;
		//out.print("<br><br><br><br><br><br><br><br>current time: " +localUnixTime+"<BR>time with padding: " +tempLong+"<BR>expiretime: " +expireLong+"<BR>");
		//out.print("<!--current time: " +localUnixTime+"<BR>time with padding: " +tempLong+"<BR>expiretime: " +expireLong+"-->");
		
		//out.println("<br>session key originally:" + sessionKey);
		if(session.getAttribute("FB_session_key") == null ){
			session.setAttribute("FB_session_key","");  // clearing Value in session Object
			session.setAttribute("FB_session_key_expire","");  // clearing Value in session Object
		}else{
			if( (localUnixTime+3600) > expireLong){ // if the current server time + 5 hours (GMT) is later than expire, then grab a new sessionkey
				//String AsessionKey = request.getParameter(FacebookParam.SESSION_KEY.toString()); // Session Key passed as request parameter
				//String ASexpireTime = request.getParameter(FacebookParam.EXPIRES.toString());		// Session Key expire time passed as request parameter
				out.print("<!--current time is past expired-->");
				/*%>
				<script language="javascript" type="text/javascript">
				alert('Your session key has expired. To renew it, we must redirect you to the homepage.');
				</script>
				<%*/
				// require going back to the homepage to get a new session key; also require login with it
				if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
					out.print("  <!--worldchanger-->");
					//if(facebookHelp.requireLogin("http://apps.facebook.com/worldchanger/")) return;
					//if(facebookHelp.requireFrame("http://apps.facebook.com/worldchanger/")) return;
				} else if(aszHostID.equalsIgnoreCase("volengfycsandbox")){
					out.print("  <!--sandbox test-->");
					//if(facebookHelp.requireLogin("http://apps.facebook.com/fycsandbox/")) return;
					//if(facebookHelp.requireFrame("http://apps.facebook.com/fycsandbox/")) return;
				} else {
					out.print("  <!--find-your-calling-->");
					//if(facebookHelp.requireLogin("http://apps.facebook.com/find-your-calling/")) return;
					//if(facebookHelp.requireFrame("http://apps.facebook.com/find-your-calling/")) return;
				}
			}
			
			
			
			
			String frameURL = "register.do?method=showPersonalityMinistryAreas2Test&personalitytypetid=" + userprofile.getUSPPersonalityTID() + 
				"&personalitytype=" + userprofile.getUSPPersonality() + "&personalitypageno=" + userprofile.getUSPPersonalityPageNo() +
				"&personalitytypee=" + userprofile.getUSPPersonalityE() + "&personalitytypei=" + userprofile.getUSPPersonalityI() +
				"&personalitytypes=" + userprofile.getUSPPersonalityS() + "&personalitytypen=" + userprofile.getUSPPersonalityF() + 
				"&personalitytypet=" + userprofile.getUSPPersonalityT() + "&personalitytypej=" + userprofile.getUSPPersonalityJ() +
				"&personalitytypep=" + userprofile.getUSPPersonalityP() + "&personalityei=" + userprofile.getUSPPersonalityEI() +
				"&personalitysn=" + userprofile.getUSPPersonalitySN() + "&personalityft=" + userprofile.getUSPPersonalityFT() + 
				"&personalityjp=" + userprofile.getUSPPersonalityJP() + "&serviceareas=" + userprofile.getUSPServiceAreas() + 
				"&skilltypes=" + userprofile.getUSPSkillTypes() + "&lookingfor=" + userprofile.getUSPLookingFor() + "&otherskills="
				+ userprofile.getUSPOtherSkills() + "&otherpassions=" + userprofile.getUSPOtherPassions() + "&errormsg=" 
				+ userprofile.getErrorMsg();
			//if(facebookHelp.requireLogin(frameURL)) return;
			//if(facebookHelp.requireFrame(frameURL)) return;
			
		}
	}
} 
if(appapikey!=null && appsecret!=null){
	if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || 
		aszHostID.equalsIgnoreCase( "volengfycsandbox" ) 
	){ 
		//FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );
	
	} 
}

int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, 
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
		vidVolSkill=31, vidVolInterestArea=32, 
		vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332,
		vidOtherPassions=338, vidOtherSkills=339, vidOtherLearningInterests=340;
int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519,
	ministryAreasTID=12521, iLocalVolTID = 17254, iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
	iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266,
	personalityTypeTID = userprofile.getUSPPersonalityTID();
int iTemp=0;
int iArraySize = 0;

String aszServiceVID = vidService + "";
String aszSkillVID = vidSkill + "";
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
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aLookingForList = new ArrayList ( 2 );
ArrayList aSkillList = new ArrayList ( 2 );
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

aCodes.getCountryList( aCountryList, 101 );
aCodes.getStateInList( aStateList, 101 );

aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor );
aCodes.getTaxonomyCodeList( aSkillList, vidVolSkill );

aCodes.getTaxonomyTermList( aUserLookingForList, 1, aszUserLookingFor);
aCodes.getTaxonomyTermList( aUserSkillList, 1, aszUserSkillList );
aCodes.getTaxonomyTermList( aUserServiceList, 1, aszUserServiceList );

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

boolean isTidList = true;
String tempTerms = aszUserOtherLearningInterests;

tempTerms = aszUserOtherLearningInterests;
if(tempTerms.length() > 1){
	if(tempTerms.indexOf(",") == -1)
		tempTerms = aszUserOtherLearningInterests + ",";
	tempTerms = tempTerms.substring(0, tempTerms.indexOf(","));
	try{
		int tid = Integer.parseInt(tempTerms);
	} catch (NumberFormatException e) {
		isTidList = false;
	}
}
	
if(isTidList){
aCodes.getTaxonomyTermList( aUserOtherLearningInterestsList, 1, aszUserOtherLearningInterests );
} else {
aCodes.getTaxonomyTIDListFromNames( aUserOtherLearningInterestsList, 1, aszUserOtherLearningInterests, 340);
}

// Go through aUserOtherPassionsList and create a comma seperated string of all the 
// terms in order to use it to fill in the text box
String aszUserOtherLearningInterestNames = "";
for(int index=0; index < aUserOtherLearningInterestsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherLearningInterestsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if(aszUserOtherLearningInterestNames == "")
								aszUserOtherLearningInterestNames = aszUserOtherLearningInterestNames + aszDisplay;
							else
								aszUserOtherLearningInterestNames = aszUserOtherLearningInterestNames + ", " + aszDisplay;
}

session.putValue ("MyIdentifier1","");  // Initialize Value into session Object

String aszPublic="display:none;";

int iVolDirectorytid = 8864;
String aszVolDirectorytid="" + iVolDirectorytid;

// I am looking for... options:

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
// end I am looking for... options

session.putValue ("usertype","");  
session.putValue ("taclite","");  
session.putValue ("upnid","");
session.putValue ("upvid","");
session.putValue ("uplid","");
session.putValue ("uid","");

String aszSubmitDisplay="display:inline;";

%>

<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.cv.org:7001" )){ %>
<div id="chrisvol_nav">
<a href="<%=aszPortal%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
<a href="<%=aszPortal%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>&nbsp;&nbsp;|&nbsp;
<a href="http://www.urbanministry.org/redirect-home" class="level-1"><span>My City</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/about.jsp" class="level-1"><span>About</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>

</div>
<br>
<% } %>
<!-- end navigation information -->
</div>

<div id="pagebanner">
<span style="float: left;">Learning Interests/Passions</span>
</div>



<br />
<div id="body">

<!--
<a href="https://graph.facebook.com/oauth/authorize?client_id=d21dc158788b65f4a4cbb8f4a0da33bf&redirect_uri=http://fycsandbox.christianvolunteering.org&scope=email&display=popup">Give EMail</a> -->

<div id="form1">

<noscript>
Javascript is disabled.  This form will not work for you.<br><br>
Please enable Javascript in your browser<!-- <% //or click <a href="%=request.getContextPath()>/register.do?method=showVolCreateNonJS">here</a> to proceed.%>-->
</noscript>

<html:form action="/register.do?indivaccnt" target="_self" >

<html:hidden property="method" value="updatePersonalitySelectFields" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="siteusetype" value="Volunteer" />
<html:hidden property="personinternalcomment" value="facebook" />
<html:hidden property="personalitypage" value="personalityministryareas2" />

<html:hidden property="personalitypageno"  />
<html:hidden property="personalitytypei"  />
<html:hidden property="personalitytypee"  />
<html:hidden property="personalitytypes"  />
<html:hidden property="personalitytypen"  />
<html:hidden property="personalitytypef" />
<html:hidden property="personalitytypet"  />
<html:hidden property="personalitytypej" />
<html:hidden property="personalitytypep"  />
<html:hidden property="personalitytype" />
<html:hidden property="personalitytypetid"  />
<html:hidden property="serviceareas" />
<html:hidden property="skilltypes"  />
<html:hidden property="lookingfor"  />
<html:hidden property="spiritualdev"  />
<html:hidden property="ministryareascause"  />
<html:hidden property="globalissues"  />
<html:hidden property="organizationaldev"  />
<html:hidden property="reconciliation"  />
<html:hidden property="otherpassions"  />
<html:hidden property="otherskills"  /> 
<html:hidden property="personalityei" />
<html:hidden property="personalitysn" />
<html:hidden property="personalityft" />
<html:hidden property="personalityjp" />


<html:hidden property="agreeflag2" value="Yes"/>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

<div id="better-select-edit-taxonomy-<%=aszServiceVID%>" class="">
		<label><h3>Please check the <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("service"); } else { out.print("ministry"); } %> areas you might be interested in. <span class="criticaltext">* </h3></span> </label><br />
		<div class="form-checkboxes form-checkboxes-scroll">
<table >
<% if(! aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<tr><td>
<h4>Spiritual Development</h4><br />
</td></tr>
<tr><td>
<%
iTemp = 0;
iArraySize = aSpiritualList.size();
iArraySize = iArraySize / 3;
for(int index=0; index < aSpiritualList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSpiritualList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <div id=\"edit-taxonomy-" + aszSpiritualTID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszSpiritualTID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize - 1)) out.print(" </td><td> ");
							if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
						}
%>

</td></tr>
<% } %>

<tr><td>
<h4>Ministry Areas</h4><br />
</td></tr>
<tr><td>
<%
iTemp = 0;
iArraySize = aMinistryAreasList.size();
iArraySize = iArraySize / 3;
for(int index=0; index < aMinistryAreasList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aMinistryAreasList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <div id=\"edit-taxonomy-" + aszMinistryAreasTID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszMinistryAreasTID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize - 1)) out.print(" </td><td> ");
							if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
						}
%>

</td></tr>
<tr><td>
<br>
<h4>Global Issues</h4><br />
</td></tr>
<tr><td>
<%
iTemp = 0;
iArraySize = aGlobalIssuesList.size();
iArraySize = iArraySize / 3;
for(int index=0; index < aGlobalIssuesList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aGlobalIssuesList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if( ((iTid == 588) || (iTid == 4236)) && aszSecondaryHost.equalsIgnoreCase("volengivol")){
							} else { out.print(" <div id=\"edit-taxonomy-" + aszGlobalIssuesTID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszGlobalIssuesTID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize - 1)) out.print(" </td><td> ");
							if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
							}
						}
%>


</td></tr>

<tr><td>
<br>
<h4>Organizational Development</h4><br />
</td></tr>
<tr><td>
<%
iTemp = 0;
iArraySize = aOrganizationalDevelopmentList.size();
iArraySize = iArraySize / 3;
for(int index=0; index < aOrganizationalDevelopmentList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aOrganizationalDevelopmentList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <div id=\"edit-taxonomy-" + aszOrganizationalDevelopmentTID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszOrganizationalDevelopmentTID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize)) out.print(" </td><td> ");
							if(index == (2 * iArraySize + 1)) out.print(" </td><td> ");
						}
%>


</td></tr>

<tr><td>
<br>
<h4> Reconciliation & Culture </h4><br />
</td></tr>
<tr><td>
<%
iTemp = 0;
iArraySize = aReconciliationList.size();
iArraySize = iArraySize / 3;
for(int index=0; index < aReconciliationList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aReconciliationList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if( ((iTid == 5085) || (iTid == 575) || (iTid == 713)) && aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							} else { out.print(" <div id=\"edit-taxonomy-" + aszReconciliationTID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszReconciliationTID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize)) out.print(" </td><td> ");
							if(index == (2 * iArraySize + 1)) out.print(" </td><td> ");
							}
						}
%>


</td></tr>
</table>


<h3> Please write in any other <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("service"); } else { out.print("ministry"); } %> areas (seperated by commas) that you would like to learn more about: </h3><br />
<input type="textbox" id="otherlearninginterests" name="otherlearninginterests" value = "<%=aszUserOtherLearningInterestNames%>" size="60"><br /><br />
		</div>
</div>

<hr />


<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%
if( false == UserSessionBean.IsSessionLoggedIn( request ) ){	
   	// not logged in
	needsLoginIFrame = true;
}else{
	PersonInfoDTO aCurrentUserObj = UserSessionBean.getCurrentUser( request);	
	if(null == aCurrentUserObj){	
    	// not logged in
		needsLoginIFrame = true;
	}else{
		if(aCurrentUserObj.getUserUID()>0){
			// user is logged in
			needsLoginIFrame = false;
		}else{
			// user is not in
			needsLoginIFrame = true;
		}
//out.print(needsLoginIFrame);
	}
} 
//out.print(needsLoginIFrame);
//needsLoginIFrame=true;// comment out eventually
if(	needsLoginIFrame == false ){
%>

<pre><font color="red"><bean:write name="individualForm" property="errormsg" /></font></pre>
<button type="button" id="submitButton" onClick="update()">Submit</button>

<% }else{ %>
<a href="#updateAccount" onclick="updateAccount()">Login with an existing account</a><br />
<p> You may use your account from iVolunteering.org, ChristianVolunteering.org, or UrbanMinistry.org. </p> <br>
or<a href="#newAccount" onclick="showNewAccount()"> Register a New Account</a>
<pre><font color="red"><bean:write name="individualForm" property="errormsg" /></font></pre>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="splash" align="left" >
		<tr id="userNm">
                <TD width=120><b>Username</b> <span class="criticaltext">*</span></TD>
                <TD><html:text property="username" styleId="username" size="25" styleClass="textinputwhite"/></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD width=120><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><html:text property="email1addr" styleId="email1addr" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD width=120><b><div id="smTag">New </div>Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23">
					<html:password property="password1" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
                <TD id="passConfirmLabel" width=120 align="left"><b>Confirm Password</b> <span class="criticaltext">*</span> </TD>
                <TD id="passConfirm" height="23">
					<html:password property="password2" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
		</tr>

		<tr id="name">
                <TD width=120><b>Name: First</b> <span class="criticaltext">*</span></TD>
				<% if(obj.isNull("first_name")) { %>
				<TD  height="23"><input type="text" name="namefirst" id="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite"  /></TD>
				<% } else { %>
                <TD  height="23"><input type="text" value="<%=obj.get("first_name")%>" name="namefirst" id="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite"  /></TD>
				<% } if (obj.isNull("last_name")) { %>
				<TD width=120 align="left"><b>Last</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text"  name="namelast" id="namelast" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
				<% } else { %>
				 <TD width=120 align="left"><b>Last</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text" value="<%=obj.get("last_name")%>" name="namelast" id="namelast" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
				<% } %>
		</tr>
		<tr id="phone">
                <TD width=120>
				<b>Phone</b> <span class="criticaltext">*</span>
				</TD>
                <TD ><html:text property="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr id="zip">
                <TD><b>Zip Code: </b> <span class="criticaltext">*</span> </TD>
                <TD colspan=2 height="23"><html:text property="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
		</tr>
		<tr id="country">
                <TD height="27"><b>Country</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=3>
                	<SELECT id="addrcountryname" name="addrcountryname" class="smalldropdown" > 
					<option value=""></option>
					<%
					aszTemp=userprofile.getUSPAddrCountryName();
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso()+ "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
                    </SELECT>
                </TD>
		</tr>
	</table>
<br clear="all" />
  <table id="checkboxes">
		<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" />			        </td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
		</tr>
    
			<tr>
				<td height="30" >
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" /></td>
				<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile:</td>
			</tr>
      
			<tr>     
        <TD height="30" >
			<input type="checkbox" value="38" id="newsletter" name="newsletter"/>
			</td><td colspan=3>
	        Subscribe me to the Find Your Calling's ChristianVolunteering.org newsletter
      	</TD>
    </tr>

		<tr>     
	        <TD height="30" >
			<html:checkbox styleClass="check" value="Yes" property="agreeflag1"/>
			</td><td colspan=3>
	        I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a>.<span class="criticaltext">*</span>
	        </TD>
        </tr>

</table>
<% } %>

</html:form>

<% if(	needsLoginIFrame == true ){ %>

<div style="display:none;">
<br />
<form id="civicrm-subscribe-form" name="newsletterUMusers" target="ifr" action="http://www.christianvolunteering.org/cms/user/subscribe?destination=cms/subscribe/newsletters" method="post">
    UrbanMinistry Group: <input type="text" name="groups" id="edit-groups" value="38">
    <br />
    <label for="edit-email" class="email">Email: </label> 
    <input type="text" class="form-text required" id="edit-email" maxlength="100" name="email" />  
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" />   
    <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" />  
</form>
</div>



<button type="button" id="submitButton" onClick="submitBoth()">Submit</button>

<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>


<% } %>
  


</div>


<div id="processing" style="display:none;">
<center>
<h2>Please wait while we process your registration... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
</div>
</div>
</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->



<script language="javascript">

function newsletter() {
	var individualForm = document.forms.individualForm;
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if(individualForm.elements["newsletter"].checked){
		document.forms["newsletterUMusers"].elements["email"].value = document.forms["individualForm"].elements["email1addr"].value;
		document.forms["newsletterUMusers"].elements["groups"].value = document.forms["individualForm"].elements["newsletter"].value;
		document.forms["newsletterUMusers"].submit();
	}
} 
function new_account() {
	
	//show();
	
	document.forms["individualForm"].submit();	
} 


function setSpiritualDev() {
	var spiritualDev = "";
<%
iTemp = 0;
if(! aszSecondaryHost.equalsIgnoreCase("volengivol")){
for(int index=0; index < aSpiritualList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSpiritualList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if((iTid != 4769) && (iTid != 4761)){
								out.println(" if(document.getElementById('" + iTid + "').checked)");
								out.println("spiritualDev = spiritualDev + document.getElementById('" +
									iTid + "').value + \",\";");
							}
}
}
%>
	
	var individualForm = document.forms.individualForm;
	individualForm.spiritualdev.value = spiritualDev;
	
	//alert('spiritualDev: ' + individualForm.spiritualdev.value);
}

function setMinistryAreasCause() {
	var ministryAreasCause = "";
<%
iTemp = 0;
for(int index=0; index < aMinistryAreasList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aMinistryAreasList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" if(document.getElementById('" + iTid + "').checked)");
							out.println("ministryAreasCause = ministryAreasCause + document.getElementById('" +
								iTid + "').value + \",\";");
						}
%>		
	
	var individualForm = document.forms.individualForm;
	individualForm.ministryareascause.value = ministryAreasCause;
	//alert(ministryAreasCause);
}

function setGlobalIssues() {
	var globalIssues = "";
<%
iTemp = 0;
for(int index=0; index < aGlobalIssuesList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aGlobalIssuesList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if( ((iTid == 588) || (iTid == 4236)) && aszSecondaryHost.equalsIgnoreCase("volengivol")){
							} else {
								out.println(" if(document.getElementById('" + iTid + "').checked)");
								out.println("globalIssues = globalIssues + document.getElementById('" +
									iTid + "').value + \",\";");
							}
						}
%>
	
	var individualForm = document.forms.individualForm;
	individualForm.globalissues.value = globalIssues;
	//alert(globalIssues);
}

function setOrganizationalDev() {
	var organizationalDev = "";
<%
iTemp = 0;
for(int index=0; index < aOrganizationalDevelopmentList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aOrganizationalDevelopmentList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" if(document.getElementById('" + iTid + "').checked)");
							out.println("organizationalDev = organizationalDev + document.getElementById('" +
								iTid + "').value + \",\";");
						}
%>		

	
	var individualForm = document.forms.individualForm;
	individualForm.organizationaldev.value = organizationalDev;
	//alert(organizationalDev);
}

function setReconciliation() {
	var reconciliation = "";
<%
iTemp = 0;
for(int index=0; index < aReconciliationList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aReconciliationList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if( ((iTid == 5085) || (iTid == 575) || (iTid == 713)) && aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							} else {
								out.println(" if(document.getElementById('" + iTid + "').checked)");
								out.println("reconciliation = reconciliation + document.getElementById('" +
									iTid + "').value + \",\";");
							}
						}
%>		

	
	var individualForm = document.forms.individualForm;
	individualForm.reconciliation.value = reconciliation;
	//alert(reconciliation);
}

function testAll() {
	if(! aszSecondaryHost.equalsIgnoreCase("volengivol")){
	setSpiritualDev();
	}
	setMinistryAreasCause();
	setGlobalIssues();
	setOrganizationalDev();
	setReconciliation();
	document.forms["individualForm"].submit();	
}

function submitBoth() {
//alert("submitBoth function");
	setSpiritualDev();
	setMinistryAreasCause();
	setGlobalIssues();
	setOrganizationalDev();
	setReconciliation();
	
	setTimeout("newsletter()", 0);
	
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if(document.forms["individualForm"].elements["newsletter"].checked){
		document.getElementById('form1').style.display='none';
		document.getElementById('processing').style.display='inline';
		
		setTimeout("new_account()", 10000);
	}else{
	//alert("new account, no newsletter");
		new_account();
	}
}

function submitForm(cancelled) {
//alert("submit");
document.forms["individualForm"].elements["method"].value = "updatePersonalityAccount";	
	document.forms.individualForm.action='<%=aszPortal%>/register.do';
	document.forms["individualForm"].submit();
}

function update() {
	
	setSpiritualDev();
	setMinistryAreasCause();
	setGlobalIssues();
	setOrganizationalDev();
	setReconciliation();
	
	document.forms["individualForm"].elements["method"].value = "updatePersonalityAccount";	
	document.forms.individualForm.action='<%=aszPortal%>/register.do';
	
	//alert("update");
	//show();
	document.forms["individualForm"].submit();	
	
}



function updateAccount() {
	document.forms["individualForm"].elements["method"].value = "processLogin";	
	document.forms.individualForm.action='<%=aszPortal%>/register.do';
	document.getElementById('smTag').style.display='none';
	document.getElementById('userNm').style.display='none';
	document.getElementById('passConfirm').style.display='none';
	document.getElementById('passConfirmLabel').style.display='none';
	document.getElementById('name').style.display='none';
	document.getElementById('phone').style.display='none';
	document.getElementById('zip').style.display='none';
	document.getElementById('country').style.display='none';
	document.getElementById('checkboxes').style.display='none';
	document.getElementById('newsletter').checked=false;
	document.getElementById('volunteertid').checked=false;
	//document.getElementById('body').style.display='none';
	//document.getElementById('processing').style.display='inline';
	//document.getElementById('individualForm').style.display='none';
	//document.getElementById('registerTitle').style.display='none';
	//document.getElementById('submitButton').style.display='none';
	//document.forms["individualForm"].elements["email1addr"].value = document.forms["loginForm"].elements["email1"].value;
	//document.forms["individualForm"].elements["password1"].value = document.forms["loginForm"].elements["pass"].value;
	//document.forms["individualForm"].submit();
}

function showNewAccount() {
	document.forms["individualForm"].elements["method"].value = "processRegistration";	
	document.forms.individualForm.action='<%=aszPortal%>/register.do?indivaccnt';
	document.getElementById('smTag').style.display='inline';
	document.getElementById('userNm').style.display='table-row';
	document.getElementById('passConfirm').style.display='table-cell';
	document.getElementById('passConfirmLabel').style.display='table-cell';
	document.getElementById('name').style.display='table-row';
	document.getElementById('phone').style.display='table-row';
	document.getElementById('zip').style.display='table-row';
	document.getElementById('country').style.display='table-row';
	document.getElementById('checkboxes').style.display='inline';
	document.getElementById('newsletter').checked=true;
	document.getElementById('volunteertid').checked=true;
	//document.getElementById('body').style.display='none';
	//document.getElementById('processing').style.display='inline';
	//document.getElementById('individualForm').style.display='none';
	//document.getElementById('registerTitle').style.display='none';
	//document.getElementById('submitButton').style.display='none';
	//document.forms["individualForm"].elements["email1addr"].value = document.forms["loginForm"].elements["email1"].value;
	//document.forms["individualForm"].elements["password1"].value = document.forms["loginForm"].elements["pass"].value;
	//document.forms["individualForm"].submit();
}

$(document).ready(function() {
		$('input[name=volunteertid]').attr('checked', true);
	<% if(userprofile.getErrorMsg().length()<1){ %>
		$('input[name=newsletter]').attr('checked', true);
	<% } %>
<%
if( aszUserMinistryAreas.equalsIgnoreCase("") && aszUserSpiritualDev.equalsIgnoreCase("") 
	&& aszUserGlobalIssues.equalsIgnoreCase("") && aszUserOrganizationalDev.equalsIgnoreCase("")
	&& aszUserReconciliation.equalsIgnoreCase("")) {

iTemp = 0;

for(int index=0; index < aRelatedAreasList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRelatedAreasList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" $('input[name=" + aAppCode.getAPCIdSubType() + "]').attr('checked', true);");
						}

}
else {
	for(int index=0; index < aUserCauseList.size(); index++) {
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserCauseList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" $('input[name=" + aAppCode.getAPCIdSubType() + "]').attr('checked', true);");
						}
	
}
%>


 }); 
</script>

<% // for google analytics tracking: %>
<% 
String aszGoalPage;
aszGoalPage = "/personalitytest/6";
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
<% } %>
