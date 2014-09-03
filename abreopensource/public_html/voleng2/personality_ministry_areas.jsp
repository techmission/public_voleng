<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<% if( aszHostID.equalsIgnoreCase( "volengfycsandbox" ) ) 
{ // these templates get screwed up with this new page; include the old start page for vols
%>
	<jsp:include page="/personality_ministry_areas.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>


<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>

<% boolean needsLoginIFrame = false; %>

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<% String userStr = "";
JSONObject obj = new JSONObject();
String aszFBUserID = "";
if(session.getAttribute("FB_User_ID")!=null){
	aszFBUserID = session.getAttribute("FB_User_ID").toString();
}

/*
if(appapikey!=null && appsecret!=null){
	if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || 
		aszHostID.equalsIgnoreCase( "volengfycsandbox" ) 
	){ 
		if(session.getAttribute("FB_session_key") == null ){
			session.setAttribute("FB_session_key","");  // clearing Value in session Object
		}
	}
	if(session.getAttribute("FB_User_ID") == null){
		session.setAttribute("FB_User_ID", userStr);
	}else if(session.getAttribute("FB_User_ID").toString().length()<1){
		session.setAttribute("FB_User_ID", userStr);
	}
	if(session.getAttribute("FB_User_ID_init") == null){
		session.setAttribute("FB_User_ID_init", userStr);
	}else if(session.getAttribute("FB_User_ID_init").toString().length()<1){
		session.setAttribute("FB_User_ID_init", userStr);
	}
	out.print("<!-- FB User: " + session.getAttribute("FB_User_ID") + " -->");
	aszFBUserID += session.getAttribute("FB_User_ID");
} 
*/
%>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<% 
String code = "";
if(request.getParameter("code")!=null){
	code = request.getParameter("code");
}
//String redirectUrl = "https://graph.facebook.com/oauth/access_token?client_id=" + appid + "&redirect_uri=http://fycsandbox.christianvolunteering.org/&client_secret=" + appsecret + "&code=" + code + "&type=web_server";
%>
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
	
	/*function showPermissions(){
		if(document.forms["individualForm"].elements["subscribe"].checked){
		FB.login(function(response) {
  			if (response.session) {
    			if (response.perms) {
      			// user is logged in and granted some permissions.
      			// perms is a comma separated list of granted permissions
   				 } else {
				// user is logged in, but did not grant any permissions
    			}
  			} else {
    		// user is not logged in
  			}
		}, {perms:'email'});
		} else {
		}

	}*/
</script>

<script type="text/javascript">
	if (!window.console) console = {log: function() {}};
/*function gup( name )
{
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}*/
	
function redirect(){
	<% if(aszRemoteHost.equalsIgnoreCase( "findyourcalling.christianvolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://findyourcalling.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszRemoteHost.equalsIgnoreCase( "findyourcalling.ivolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://findyourcalling.ivolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } %>
	//alert('redirecting');
	//top.location.href = "http://www.facebook.com/dialog/oauth/?scope=email&client_id=123050457758183&redirect_uri=http://fycsandbox.christianvolunteering.org/register.do?method=showMinistryAreas&response_type=code";
		//top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/register.do?method=showMinistryAreas&redirect=true&scope=email";
}
	
function getAccessToken(){
console.log('get access token');
		<% /*String accessToken = ""; 
		try{
			URL url = new URL(redirectUrl);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			BufferedInputStream in = new BufferedInputStream(stream);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			//BufferedOutputStream buffOut = new BufferedOutputStream(output);
			int i;
			while ((i = in.read()) != -1){
				//buffOut.write(i);
				output.write(i);
			}
			accessToken = output.toString();
			//buffOut.flush();
			}
			catch(IOException e){
				
			}*/
		%>
}
</script>

<!--link href="http://www.urbanministry.org/sites/all/themes/facebook/custom.css" rel="stylesheet" type="text/css" /-->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, 
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
		vidVolSkill=31, vidVolInterestArea=32, 
		vidVolDenom=262, vidVolOrgAffil=20, vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332,
		vidOtherPassions=338, vidOtherSkills=339, vidOtherLearningInterests=340, vidPersonalityType = 336;
int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519,
	ministryAreasTID=12521, iLocalVolTID = 17254, iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
	iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266,
	personalityTypeTID = userprofile.getUSPPersonalityTID();
int iTemp=0;
String aszServiceVID = vidService + "";
String aszSkillVID = vidSkill + "";
String aszPersonalityVID = vidPersonalityType + "";
String aszSpiritualTID = spiritualTID + "";
String aszGlobalIssuesTID = globalIssuesTID + "";
String aszOrganizationalDevelopmentTID = organizationalDevelopmentTID + "";
String aszReconciliationTID = reconciliationTID + "";
String aszMinistryAreasTID = ministryAreasTID + "";
String aszPersonalityTypeTID = personalityTypeTID + "";
String aszUserSkillList = userprofile.getUSPSkillTypes();
String aszUserServiceList = userprofile.getUSPServiceAreas();
String aszUserLookingFor = userprofile.getUSPLookingFor();
String aszUserOtherPassions = userprofile.getUSPOtherPassions();
String aszUserOtherSkills = userprofile.getUSPOtherSkills();

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aPersonalityList = new ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aLookingForList = new ArrayList ( 2 );
ArrayList aSkillList = new ArrayList ( 2 );
ArrayList aSpiritualList = new ArrayList ( 2 );
ArrayList aGlobalIssuesList = new ArrayList ( 2 );
ArrayList aOrganizationalDevelopmentList = new ArrayList ( 2 );
ArrayList aReconciliationList = new ArrayList ( 2 );
ArrayList aMinistryAreasList = new ArrayList ( 2 );
ArrayList aRelatedAreasList = new ArrayList ( 2 );
ArrayList aUserSkillList = new ArrayList ( 2 );
ArrayList aUserServiceList = new ArrayList ( 2 );
ArrayList aUserCauseList = new ArrayList ( 2 );
ArrayList aUserLookingForList = new ArrayList ( 2 );
ArrayList aUserOtherPassionsList = new ArrayList ( 2 );
ArrayList aUserOtherSkillsList = new ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

ArrayList aServiceList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

aCodes.getCountryList( aCountryList, 101 );
aCodes.getStateInList( aStateList, 101 );

aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor, 303 );
aCodes.getTaxonomyCodeList( aSkillList, vidVolSkill );
aCodes.getTaxonomyCodeList( aPersonalityList, vidPersonalityType);

aCodes.getTaxonomyTermList( aUserLookingForList, 1, aszUserLookingFor);
aCodes.getTaxonomyTermList( aUserSkillList, 1, aszUserSkillList );
aCodes.getTaxonomyTermList( aUserServiceList, 1, aszUserServiceList );
//aCodes.getTaxonomyTermList( aUserOtherPassionsList, 1, aszUserOtherPassions );
//aCodes.getTaxonomyTermList( aUserOtherSkillsList, 1, aszUserOtherSkills );

aCodes.getTaxonomyChildCodeList( aSpiritualList, 8, spiritualTID );
aCodes.getTaxonomyChildCodeList( aGlobalIssuesList, 8, globalIssuesTID);
aCodes.getTaxonomyChildCodeList( aOrganizationalDevelopmentList, 8, organizationalDevelopmentTID);
aCodes.getTaxonomyChildCodeList( aReconciliationList, 8, reconciliationTID);
aCodes.getTaxonomyChildCodeList( aMinistryAreasList, 8, ministryAreasTID );

aCodes.getTaxonomyRelatedCodeList( aRelatedAreasList, 336, personalityTypeTID );

// Load the Free Tagging fields, have to have conditionals because the first time they come to the results page, these
// will be passed as a comma-seperated list of words (ie. "cooking, camping"), but all other times it will be passed as 
// a comma-separated list of tids (ie. "123, 234")
boolean isTidList = true;
String tempTerms = aszUserOtherSkills;

if(tempTerms.length() > 1) {
	if(tempTerms.indexOf(",") == -1)
		tempTerms = aszUserOtherSkills + ",";
	tempTerms = tempTerms.substring(0, tempTerms.indexOf(","));
	try{
		int tid = Integer.parseInt(tempTerms);
	} catch (NumberFormatException e) {
		isTidList = false;
	}
}

if(isTidList){
	aCodes.getTaxonomyTermList( aUserOtherSkillsList, 1, aszUserOtherSkills );
} else {
	aCodes.getTaxonomyTIDListFromNames( aUserOtherSkillsList, 1, aszUserOtherSkills, 339);
}

tempTerms = aszUserOtherPassions;
if(tempTerms.length() > 1){
	if(tempTerms.indexOf(",") == -1)
		tempTerms = aszUserOtherPassions + ",";
	tempTerms = tempTerms.substring(0, tempTerms.indexOf(","));
	try{
		int tid = Integer.parseInt(tempTerms);
	} catch (NumberFormatException e) {
		isTidList = false;
	}
}

if(isTidList){
	aCodes.getTaxonomyTermList( aUserOtherPassionsList, 1, aszUserOtherPassions );
} else {
	aCodes.getTaxonomyTIDListFromNames( aUserOtherPassionsList, 1, aszUserOtherPassions, 338);
}

// Go through aUserOtherSkillsList and create a comma seperated string of all the 
// terms in order to use it to fill in the text box
String aszUserOtherSkillNames = "";
for(int index=0; index < aUserOtherSkillsList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherSkillsList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	if(aszUserOtherSkillNames == "")
		aszUserOtherSkillNames = aszUserOtherSkillNames + aszDisplay;
	else
		aszUserOtherSkillNames = aszUserOtherSkillNames + ", " + aszDisplay;
}

// Go through aUserOtherPassionsList and create a comma seperated string of all the 
// terms in order to use it to fill in the text box
String aszUserOtherPassionNames = "";
for(int index=0; index < aUserOtherPassionsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherPassionsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if(aszUserOtherPassionNames == "")
								aszUserOtherPassionNames = aszUserOtherPassionNames + aszDisplay;
							else
								aszUserOtherPassionNames = aszUserOtherPassionNames + ", " + aszDisplay;
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
<a href="<%=request.getContextPath()%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
<a href="<%=request.getContextPath()%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>&nbsp;&nbsp;|&nbsp;
<a href="http://www.urbanministry.org/redirect-home" class="level-1"><span>My City</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/about.jsp" class="level-1"><span>About</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>
</div>
<br>
<% } %>
<!-- end navigation information -->
</div>

<div id="pagebanner">
	<span style="float: left;">Ministry Interests/Skills</span>
</div>

<%@ include file="/template_include/personality_test_progress_bar.inc" %>

<br />
<div id="body">
	<div id="form1">
	
	<noscript>
	Javascript is disabled.  This form will not work for you.<br><br>
	Please enable Javascript in your browser<!-- <% //or click <a href="%=request.getContextPath()>/register.do?method=showVolCreateNonJS">here</a> to proceed.%>-->
	</noscript>

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

%>

	<html:form method="post" action="/register.do">
	<html:hidden property="method" value="updatePersonalitySelectFields" />
	<html:hidden property="personalitypage" value="personalityministryarea" />
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
	<html:hidden property="personalityei" />
	<html:hidden property="personalitysn" />
	<html:hidden property="personalityft" />
	<html:hidden property="personalityjp" />
	<html:hidden property="namefirst"  />
	<html:hidden property="namelast"  />
	<html:hidden property="username" />
	<html:hidden property="agreeflag1" value="Yes" />
	<html:hidden property="email1addr" />
	<html:hidden property="facebookuid" />
	<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
	<html:hidden value="Volunteer" property="siteusetype" />
	<html:hidden value="facebook" property="personinternalcomment" /> 

<pre><font color="red"><bean:write name="individualForm" property="errormsg" /><%=userprofile.getErrorMsg()%></font></pre>
	<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="">
		<label><h3>In what types of <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("service"); } else { out.print("ministry service"); } %> might you be interested? (check all that apply): <span class="criticaltext">*</span></h3> </label><br />
		<div class="form-checkboxes form-checkboxes-scroll">
			<table ><tr><td>
			<% 
			iTemp = 17254;
			int iArraySize = aLookingForList.size();
			iArraySize = 5;
			
			for(int index=0; index < aLookingForList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
				if(null == aAppCode) continue;
				int iTid = aAppCode.getAPCIdSubType();
				String aszDisplay = aAppCode.getAPCDisplay();
				if(iTid == 17254 || iTid == 17255 || iTid == 17261 || iTid == 17266 || iTid == 17258 || iTid == 17259 ||
				   iTid == 17257 || iTid == 17256 || iTid == 17260 || iTid == 21853){
					out.print(" <div id=\"edit-taxonomy-" + aszLookingForVID + "-0-wrapper\" class=\"form-item\">" + 
							"<label class=\"option\" for=\"edit-taxonomy-" + aszLookingForVID + "-0\">" +
							"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid == iTemp ) out.print(" checked ");
						out.println(" >");
						if(iTid == 17254){
							out.print("Local Volunteer Opportunities");
						}else if(iTid == 17255){
							out.print("Group/Family Volunteer Opportunities");
						}else if(iTid == 17261 && !aszSecondaryHost.equalsIgnoreCase("volengivol")){
							out.print("Jobs in Urban Ministry");
						}else if(iTid == 17266){
							out.print("Local Social Justice Groups & Events");
						}else if(iTid == 17258){
							if(aszSecondaryHost.equalsIgnoreCase("volengivol"))
								out.print("Short Term Internship");

							else
								out.print("Short Term Missions / Ministry Internship");
						}else if(iTid == 17259){
							out.print("Summer Internships");
						}else if(iTid == 17257){
							out.print("Virtual Volunteering");
						}else if(iTid == 17256){
							out.print("Volunteer on a Nonprofit Board");
						}else if(iTid == 17260){
							out.print("Work Study Opportunities");
						}else if(iTid == 21853){
							out.print("Local Organizations");
						}else{
							out.print(aAppCode.getAPCDisplay());
						}	
						out.print("</label></div>");
						if(index == (iArraySize-1)) out.print(" </td><td> ");
					}
				}
			%>
			</td></tr></table>
		</div>
	</div>
	
	<div id="better-select-edit-taxonomy-area" class="">
		<label><h3>The ministry areas checked below are often of interest to people of your personality type.  Please check (or uncheck) this list so that it reflects ministry areas that you might be interested in.<span class="criticaltext">*</h3></span> </label><br />
		<div class="form-checkboxes form-checkboxes-scroll">
			<table >
				<tr>
					<td>
<% 
//iTemp = 17254;
iArraySize = aServiceList.size();
iArraySize = iArraySize / 2;

for(int index=0; index < aServiceList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	out.print(" <div id=\"edit-taxonomy-" + aszServiceVID + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + aszServiceVID + "-0\">" +
				"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" checked ");
				out.println(" >");
	if(iTid == 4758){
		out.print( "Serving those with Addictions" );
	}else if(iTid == 4759){
		out.print( "Doing Administrative Tasks");
	}else if(iTid == 4760){
		out.print( "Helping With Bible Study");
	}else if(iTid == 4762){
		out.print( "Serving as Camp Counselor");
	}else if(iTid == 4763){
		out.print( "Working Children and Youth");
	}else if(iTid == 7341){
		out.print( "Serving in Christian/Catholic Schools");
	}else if(iTid == 4764){
		out.print( "Helping Plant Churches");
	}else if(iTid == 4765){
		out.print( "Serving At Risk Communities");
	}else if(iTid == 4766){
		out.print( "Working with Computers and Technology");
	}else if(iTid == 4767){
		out.print( "Serving People with Disabilities");
	}else if(iTid == 4768){
		out.print( "Helping Education and Literacy");
	}else if(iTid == 4770){
		out.print( "Helping Provide Employment Training");
	}else if(iTid == 4771){
		out.print( "Helping Construction and Engineering");
	}else if(iTid == 4771){
		out.print( "Doing Evangelism");
	}else if(iTid == 4773){
		if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print("Serving");
		else out.print("Ministering to");
		out.print(" Families and Adults");
	}else if(iTid == 4774){	
		out.print( "Serving Food to Those in Need");
	}else if(iTid == 4775){
		out.print( "Providing Medical Services");
	}else if(iTid == 4776){
		out.print( "Working with the Homeless");
	}else if(iTid == 4777){
		out.print( "Working with Immigrants and Refugees");
	}else if(iTid == 4778){
		out.print( "Providing Legal Aid");
	}else if(iTid == 4779){
		out.print( "Helping Translate Languages");
	}else if(iTid == 4780){	
		out.print( "Providing Mentoring");
	}else if(iTid == 4781){
		out.print( "Serving in Orphanage");
	}else if(iTid == 4782){
		if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print( "Serving Those in Prison");
		else out.print("Ministering in Prisons");
	}else if(iTid == 7342){	
		out.print( "Teaching Sunday School or Providing Religious Education");
	}else if(iTid == 6843){
		if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print( "Serving Seniors and the Elderly");
		else out.print("Ministering to Seniors and the Elderly");
	}else if(iTid == 4783){
	 	if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print( "Serving Single Parents or those in Crisis Pregnancy");
		else out.print("Ministering to Single Parents or those in Crisis Pregnancy");
	}else if(iTid == 4784){
	 	if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print( "Serving through Sports and Recreation");
		else out.print("Ministering through Sports and Recreation");
	}else if(iTid == 4785){
		out.print( "Teaching");
	}else if(iTid == 4786){
		out.print( "Tutoring");
	}else if(iTid == 4787){
		out.print( "Teaching Vacation Bible School");
	}else if(iTid == 4788){
		out.print( "Visiting those who Might be Homebound or in Need of Friendship");
	}else if(iTid == 4789){	
		if (aszSecondaryHost.equalsIgnoreCase("volengivol")) out.print( "Helping Women");
		else out.print("Ministering to Women");
	}else { 
		out.print( aAppCode.getAPCDisplay() ); 
	}
	out.print("</label></div> ");
	if(index == (iArraySize)) out.print(" </td><td> ");
}
%>
					</td>
				</tr>
			</table>
			<br />
			<h3> Please fill in other areas that  you feel passionate about (seperated by commas): </h3> <br />
			<input type="textbox" id="otherpassions" name="otherpassions" value = "<%=aszUserOtherPassionNames%>" size = "60"> 
		</div>
	</div>
	<hr />
	<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="">
		<label><h3> Please check (or uncheck) <% if (! aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("ministry"); } %> areas where you have skills, experience or education. We have already checked skill areas that are often of interest to people of your personality type.<span class="criticaltext">* </h3></span> </label><br />
		<div class="form-checkboxes form-checkboxes-scroll">
			<table ><tr><td>
<%
iTemp = 0;
iArraySize = aSkillList.size();
iArraySize = iArraySize / 3;
if( aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
	for(int index=0; index < aSkillList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSkillList.get(index);
		if(null == aAppCode) continue;
		int iTid = aAppCode.getAPCIdSubType();
		String aszDisplay = aAppCode.getAPCDisplay();
		int iSubType = aAppCode.getAPCIdSubType();
		if (iSubType == 971 ||
			iSubType == 972){
		}else if (iSubType == 968){
			out.print(" <div id=\"edit-taxonomy-" + aszSkillVID + "-0-wrapper\" class=\"form-item\">" + 
						"<label class=\"option\" for=\"edit-taxonomy-" + aszSkillVID + "-0\">" +
						"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" checked ");
						out.println(" >Musician</label></div> ");
						if(index == (iArraySize - 1)) out.print(" </td><td> ");
						if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
		}else if (iSubType == 8124){
			out.print(" <div id=\"edit-taxonomy-" + aszSkillVID + "-0-wrapper\" class=\"form-item\">" + 
						"<label class=\"option\" for=\"edit-taxonomy-" + aszSkillVID + "-0\">" +
						"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" checked ");
						out.println(" >Deaf Outreach</label></div> ");
						if(index == (iArraySize - 1)) out.print(" </td><td> ");
						if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
		}else{
			out.print(" <div id=\"edit-taxonomy-" + aszSkillVID + "-0-wrapper\" class=\"form-item\">" + 
						"<label class=\"option\" for=\"edit-taxonomy-" + aszSkillVID + "-0\">" +
						"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" checked ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
						if(index == (iArraySize - 1)) out.print(" </td><td> ");
						if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
		}
	}
} else {
	for(int index=0; index < aSkillList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSkillList.get(index);
		if(null == aAppCode) continue;
		int iTid = aAppCode.getAPCIdSubType();
		//int aszTid = (String) aAppCode.getAPCIdSubType();
		String aszDisplay = aAppCode.getAPCDisplay();
		out.print(" <div id=\"edit-taxonomy-" + aszSkillVID + "-0-wrapper\" class=\"form-item\">" + 
					"<label class=\"option\" for=\"edit-taxonomy-" + aszSkillVID + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid == iTemp) out.print(" checked ");
					out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
					if(index == (iArraySize - 1)) out.print(" </td><td> ");
					if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
	}
}
%>
			</td></tr></table>
			<br />
			
			<h3> Please write in any other areas where you have skills, experience or education (seperated by commas): </h3><br />
			<input type="textbox" id="otherskills" name="otherskills" value = "<%=aszUserOtherSkillNames%>" size = "50">
			<br /><br />

<% if( needsLoginIFrame == true){ %>
			<div class="email" id="email">
				<h3> Sign up for updates: </h3><br />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id="splash" align="left" >
					<tr>
						<td height="30" ><input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" onclick="showPermissions();" /></td>
						<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile:</td>
					</tr>
				</table>
			</div>
			<br /><br />
			<br />
			<h3> We need your zip code (if you are in the US) to show you local ministry opportunities in your area. </h3><br />
			<table>
				<tr id="postal">
					<td> <b>Zip Code: </b> <span class="criticaltext">*</span> </td>
					<td> <input type="text"  name="addrpostalcode" id="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/> </td>
				</tr>
				<tr id="country">
					<td height="27"><b>Country</b> <span class="criticaltext">*</span> </td>
					<td colspan=3>
						<select id="addrcountryname" name="addrcountryname" class="smalldropdown" > 
						<option value=""></option>
						<%
						String defaultCountry = "us";
						aszTemp=userprofile.getUSPAddrCountryName();
						if(null != aCountryList){
							for(int index=0; index < aCountryList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getCTRIso();
								out.println(" <option value=\"" + aAppCode.getCTRIso()+ "\" " );
								if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
								if(aszOptRqCode.equalsIgnoreCase( defaultCountry ) ) out.println(" selected=selected ");
								out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
							}
						}
						%>
						</select>
					</td>
				</tr>
			</table>
<% } %>
		</div>
<pre><font color="red"><bean:write name="individualForm" property="errormsg" /></font></pre>
	</div>
	<button type="button" value="Submit" name="Submit" onClick="populateTaxonomies()">Submit</button>
</html:form>


</div>
</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->



<script language="javascript">

function setLookingFor() {
	var lookingFor = "";
<%
iTemp = 0;
for(int index=0; index < aLookingForList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	//int aszTid = (String) aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	if((iTid != 17265) && (iTid != 17262) && (iTid != 21632) ){
		if((aszSecondaryHost.equalsIgnoreCase("volengivol") && ((iTid == 17258) || (iTid == 21631) ||(iTid == 17261)))){
		} else {
			out.println(" if(document.getElementById('" + iTid + "').checked)");
			out.println("lookingFor = lookingFor + document.getElementById('" +
			iTid + "').value + \",\";");
		}
	}
}
%>
	var individualForm = document.forms.individualForm;
	individualForm.lookingfor.value = lookingFor;
console.log('lookingFor: ' + individualForm.lookingfor.value);
}

function setMinistryAreaTypes() {
	var ministryAreas = "";
<%
iTemp = 0;
for(int index=0; index < aServiceList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	//int aszTid = (String) aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	if((iTid != 4769) && (iTid != 4761) && (iTid != 25154) && (iTid != 25155) && (iTid != 25156) && (iTid != 25157) && (iTid != 25158) && (iTid !=25159) && (iTid != 25160) && (iTid != 25161) && (iTid != 25162) && (iTid != 25163) && (iTid != 25164) && (iTid != 25165) && (iTid != 25166) && (iTid != 25167) && (iTid != 25168) && (iTid != 25169) && (iTid != 25170) && (iTid != 25171) && (iTid != 25172)){
		if((aszSecondaryHost.equalsIgnoreCase("volengivol") && ((iTid == 4760) || (iTid == 7341) ||
			(iTid == 4764) || (iTid == 4772) || (iTid == 7342) || (iTid == 4787)))){
		} else {
			out.println(" if(document.getElementById('" + iTid + "').checked)");
			out.println("ministryAreas = ministryAreas + document.getElementById('" +
			iTid + "').value + \",\";");
		}
	}
}
%>
	var individualForm = document.forms.individualForm;
	individualForm.serviceareas.value = ministryAreas;
console.log('ministry areas: ' + individualForm.serviceareas.value);
}

function setSkillTypes() {
	var skills = "";
<%
iTemp = 0;
for(int index=0; index < aSkillList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSkillList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	//int aszTid = (String) aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
	if((aszSecondaryHost.equalsIgnoreCase("volengivol") && ((iTid == 971) || (iTid == 972)))){
	} else {
		out.println(" if(document.getElementById('" + iTid + "').checked)");
		out.println("skills = skills + document.getElementById('" +
		iTid + "').value + \",\";");
	}
}
%>		
	var individualForm = document.forms.individualForm;
	individualForm.skilltypes.value = skills;
console.log(skills);
}

function populateTaxonomies() {
console.log('call setMinistryAreaTypes');
	setMinistryAreaTypes();
console.log('call setSkillTypes');
	setSkillTypes();
console.log('call setLookingFor');
	setLookingFor();
console.log('call setUpSubmission');
setUpSubmission();
}
var email;
var firstName;
var lastName;
function setUpSubmission(){
	//getEmail();
console.log('inside setUpSubmission');
	var uid = 0;
	<% if(aszFBUserID != null && !aszFBUserID.equals("")) { %>
	  uid = <%=aszFBUserID%>;//FB.getSession().uid; - no longer working; fixed 2012-02-08 - ali
	<% } %>
console.log('uid is: ' + uid);
	document.forms["individualForm"].elements["facebookuid"].value = uid;
		
<% /*if(session.getAttribute("FB_User_ID") == null){
	session.setAttribute("FB_User_ID", uid);
}else if(session.getAttribute("FB_User_ID").toString().length()<1){
	session.setAttribute("FB_User_ID", uid);
}
if(session.getAttribute("FB_User_ID_init") == null){
	session.setAttribute("FB_User_ID_init", uid);
}else if(session.getAttribute("FB_User_ID_init").toString().length()<1){
	session.setAttribute("FB_User_ID_init", uid);
}*/
%>
		/*FB.api('/me', function (response){
			//alert(response.email);
			//alert(response.first_name);
			//alert(response.last_name);
			email = response.email;
			//alert(email);
			firstName = response.first_name;
			lastName = response.last_name;
		});*/
	FB.api('/me', function(response) {
console.log('query');
		var query = FB.Data.query('SELECT uid, first_name, last_name, email FROM user WHERE uid = me()');
console.log('query line 874 - before wait');
		query.wait(function(response){
console.log('query line 876 - in wait');
			email = response[0].email;
			firstName = response[0].first_name;
			lastName = response[0].last_name;
console.log(email);
console.log(firstName);
console.log(lastName);
					
			document.forms["individualForm"].elements["email1addr"].value = email;
			document.forms["individualForm"].elements["namefirst"].value = firstName;
			document.forms["individualForm"].elements["namelast"].value = lastName;
		
			var fullName = firstName + lastName;
			document.forms["individualForm"].elements["username"].value = fullName;	
			var method = document.forms["individualForm"].elements["method"].value;
console.log("method: " + method);	
console.log("personality type: " + document.forms["individualForm"].elements["personalitytype"].value);
console.log("personality tid: " + document.forms["individualForm"].elements["personalitytypetid"].value);
			if(method == "processFacebookCreateAccount"){
console.log("first: " + firstName + " last: " + lastName);
console.log(document.forms["individualForm"].elements["addrpostalcode"].value);
				//showInvite();
				showNewInvite();
			}else {
				//setTimeout("new_account()", 5000);
				new_account();
			}
		});
	});
}

function showNewInvite(){
var message;
	<% if( aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
		message = "Join me on WorldChanger, where you can find out your personality type and find volunteer opportunities that fit your personality!";
	<% } else if (aszRemoteHost.equalsIgnoreCase("fycsandbox.christianvolunteering.org")) { %>
		message = "Join me on WorldChanger, where you can find out your personality type and find volunteer opportunities that fit your personality!";
	<% } else { %>
		message = "Join me on Find Your Calling, where you can find out your personality type and find ministry opportunities that fit your personality!";
	<% } %>

	FB.ui(
	{
  		method: 'apprequests',
		message: message,
	},
	function(response) {
		new_account();
	}
	);
}

function new_account(){
	document.forms["individualForm"].submit();
}

// Make sure the user has authorized the app
window.onload = FB.getLoginStatus(function(response) {
	if(response.authResponse){
		//logged in and connected user, someone you know
		//alert('session');
		//getAccessToken(); //don't need an access token on this page...
		//if(gup('redirect') == "true"){
			//top.location.href = "http://apps.facebook.com/fycsandbox/personalitytest.jsp";
		//}
	} else { 
		//no user session available, redirect to OAuth Workflow
		redirect();
	}
	
	
	
	<% if(needsLoginIFrame == true){ %>
	document.forms["individualForm"].elements["method"].value = "processFacebookCreateAccount";
	//document.forms["individualForm"].elements["username"].value = fullName;
<% } else { %>
	document.forms["individualForm"].elements["method"].value = "updatePersonalitySelectFields";	
<% } %> 

	var d = new Date()
	var gmtHours = d.getTimezoneOffset()*(-60);
	$('form[name="individualForm"]').prepend("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

	
	

	<%
iTemp = 0;

if( aszUserLookingFor.equalsIgnoreCase("") && aszUserSkillList.equalsIgnoreCase("") && aszUserServiceList.equalsIgnoreCase("")) {
	for(int index=0; index < aRelatedAreasList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRelatedAreasList.get(index);
		if(null == aAppCode) continue;
		int iTid = aAppCode.getAPCIdSubType();
		//String aszDisplay = aAppCode.getAPCDisplay();
		out.println(" $('input[name=" + aAppCode.getAPCIdSubType() + "]').attr('checked', true);");
	}

}else {
	for(int index=0; index < aUserSkillList.size(); index++) {
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserSkillList.get(index);
		if(null == aAppCode) continue;
		int iTid = aAppCode.getAPCIdSubType();
		//String aszDisplay = aAppCode.getAPCDisplay();
		out.println(" $('input[name=" + aAppCode.getAPCIdSubType() + "]').attr('checked', true);");
	}

	for(int index=0; index < aUserServiceList.size(); index++) {
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserServiceList.get(index);
		if(null == aAppCode) continue;
		int iTid = aAppCode.getAPCIdSubType();
		//String aszDisplay = aAppCode.getAPCDisplay();
		out.println(" $('input[name=" + aAppCode.getAPCIdSubType() + "]').attr('checked', true);");
	}
	
	for(int index=0; index < aUserLookingForList.size(); index++) {
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserLookingForList.get(index);
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
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

<% } %>
