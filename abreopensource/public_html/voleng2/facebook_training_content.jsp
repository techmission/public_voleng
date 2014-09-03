<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<%@page import="java.util.*" %>
<%@page import="java.util.List"%>

<%@page import="java.io.IOException"%>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.google.code.facebookapi.FacebookException"%>
<%@page import="com.google.code.facebookapi.FacebookJsonRestClient"%>
<%@page import="com.google.code.facebookapi.FacebookParam"%>
<%@page import="com.google.code.facebookapi.schema.*"%>
<%@page import="com.google.code.facebookapi.ProfileField"%>
<%@page import="com.google.code.facebookapi.FacebookWebappHelper"%>
<%@page import="com.google.code.facebookapi.TemplatizedAction"%>
<%@page import="com.google.code.facebookapi.Permission"%>
<%@page import="com.google.code.facebookapi.BundleActionLink"%>
<%@ include file="/template_include/facebookapi_keys.inc" %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->



<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>
<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
/*
if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" ) || aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ 
		
		FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

//	if(facebookHelp.requireLogin("register.do?method=showFacebookTrainingContent")) return;
	if(facebookHelp.requireFrame("register.do?method=showFacebookTrainingContent")) return;
}
*/
%>

<script type="text/javascript">
// everything is wrapped in the XD function to reduce namespace collisions
var XD = function(){

    var interval_id,
    last_hash,
    cache_bust = 1,
    attached_callback,
    window = this;

    return {
        postMessage : function(message, target_url, target) {
            if (!target_url) {
                return;
            }
            target = target || parent;  // default to parent
            if (window['postMessage']) {
                // the browser supports window.postMessage, so call it with a targetOrigin
                // set appropriately, based on the target_url parameter.
                target['postMessage'](message, target_url.replace( /([^:]+:\/\/[^\/]+).*/, '$1'));
            } else if (target_url) {
                // the browser does not support window.postMessage, so use the window.location.hash fragment hack
                target.location = target_url.replace(/#.*$/, '') + '#' + (+new Date) + (cache_bust++) + '&' + message;
            }
        },
        receiveMessage : function(callback, source_origin) {
            // browser supports window.postMessage
            if (window['postMessage']) {
                // bind the callback to the actual event associated with window.postMessage
                if (callback) {
                    attached_callback = function(e) {
                        if ((typeof source_origin === 'string' && e.origin !== source_origin)
                        || (Object.prototype.toString.call(source_origin) === "[object Function]" && source_origin(e.origin) === !1)) {
                             return !1;
                         }
                         callback(e);
                     };
                 }
                 if (window['addEventListener']) {
                     window[callback ? 'addEventListener' : 'removeEventListener']('message', attached_callback, !1);
                 } else {
                     window[callback ? 'attachEvent' : 'detachEvent']('onmessage', attached_callback);
                 }
             } else {
                 // a polling loop is started & callback is called whenever the location.hash changes
                 interval_id && clearInterval(interval_id);
                 interval_id = null;
                 if (callback) {
                     interval_id = setInterval(function() {
                         var hash = document.location.hash,
                         re = /^#?\d+&/;
                         if (hash !== last_hash && re.test(hash)) {
                             last_hash = hash;
                             callback({data: hash.replace(re, '')});
                         }
                     }, 100);
                 }
             }
         }
    };
}();
</script>
<script type="text/javascript">
function resizeIframe(message) {
  var h = message.data;
	if ( !isNaN( h ) && h > 0 ) {
	  // Adds some extra padding.
	  h = parseInt(h) + 30;
		// Sets the iframe height
    $('#iframe-inner').attr('height', h);
  }
	// Goes up to top of page.
	FB.CanvasClient.scrollTo(0,0);
}
$(function(){
  // Creates the iframe for the training content.  
  var src = 'http://www.urbanministry.org/training-content#' + encodeURIComponent( document.location.href );
  $( '<iframe src="' + src + '" width="720" height="1600" id="iframe-inner" scrolling="no" frameborder="0"><\/iframe>' ).appendTo( '#iframe' );  
	// Listens for message from training content iframe, so it can resize.
  XD.receiveMessage(function(e) { resizeIframe(e); }, 'http://www.urbanministry.org' );
});
</script>



<!-- Add code to display collapsible div for Training Questionairre here -->



<%
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

/*String tempSkills = aszVolSkills;
int Tid = 0;
while (tempSkills.length() > 1) {
			int commaPlace = tempSkills.indexOf(",");
			
			// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
			if(tempSkills.contains(",")){
				int commaPlace = tempName.indexOf(",");
				Tid = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			}else{
				tempSkills = Integer.parseInt(tempSkills);
			}
	aCodes.getTaxonomyRelatedCodeList( aOppSkills , 336, Tid );
}

for (int index = 0; index < aOppSkills.length(); index++){
	aszOppSkills = aszOppSkills + "," + aOppSkills.get(index);
}*/

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



<br />
<div id='collapsibleblock'>
<a href="javascript:;" onmousedown="if(document.getElementById('body').style.display == 'none'){ document.getElementById('body').style.display = 'block'; document.getElementById('expanded').style.display = 'block'; document.getElementById('collapsed').style.display = 'none';}else{ document.getElementById('body').style.display = 'none'; document.getElementById('collapsed').style.display = 'block'; document.getElementById('expanded').style.display = 'none';}"> <div id='collapsed' style="display:block"> Add or Edit Training Interests &#9660; </div> <div id='expanded' style="display:none"> Add or Edit Training Interests &#9650; </div> </a> </div>
<div id="body" style="display:none">

<div id="form1">

<noscript>
Javascript is disabled.  This form will not work for you.<br><br>
Please enable Javascript in your browser<!-- <% //or click <a href="%=request.getContextPath()>/register.do?method=showVolCreateNonJS">here</a> to proceed.%>-->
</noscript>

<html:form action="/register.do" target="_self" >

<html:hidden property="method" value="updatePersonalitySelectFields" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="siteusetype" value="Volunteer" />
<html:hidden property="personinternalcomment" value="facebook" />
<html:hidden property="personalitypage" value="facebooktrainingcontent" />

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

<button type="button" id="submitButton" onClick="update()">Save</button>

<hr />





</html:form>
</div>
</div>

<div id="iframe"></div>

<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

<script type="text/javascript">
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
		new_account();
	}
}

function update() {
	
	setSpiritualDev();
	setMinistryAreasCause();
	setGlobalIssues();
	setOrganizationalDev();
	setReconciliation();
	
	//document.forms["individualForm"].elements["method"].value = "updatePersonalityAccount";	
	//document.forms.individualForm.action='<%=aszPortal%>/register.do';
	
	document.forms["individualForm"].submit();
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
%>

document.getElementById('body').style.display = 'block';

<%
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

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->