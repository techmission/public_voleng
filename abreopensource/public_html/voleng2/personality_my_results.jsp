<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<%
//String redirectURL = "https://graph.facebook.com/oauth/authorize?client_id=b25eee679593f530b86d0ef38db92b07&redirect_uri=http://www.facebook.com/worldchanger&scope=stream_publish";
	//response.sendRedirect(redirectURL);
%>
<%@page import="java.util.List"%>

<%@page import="java.io.IOException"%>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<%//@ include file="/template_include/facebookapi_keys.inc" %>



<FONT color="red"><bean:write name="individualForm" property="errormsg" /></FONT>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<%@ include file="/template_include/facebookapi_init.inc" %>
<!-- end header information -->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<%
	

int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		vidVolSkill=18, vidVolDenom=262, vidVolOrgAffil=20, vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolInterestArea=46, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332,
		 vidOtherPassions=338, vidOtherSkills=339, vidOtherLearningInterests=340;
int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519,
	ministryAreasTID=12521, iLocalVolTID = 17254, iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266, iSTMTID=17258,
	personalityTypeTID = userprofile.getUSPPersonalityTID();
int iTemp=0;

String aszServiceVID = vidService + "";

String aszSkillVID = vidSkill + "";

String aszSpiritualTID = spiritualTID + "";
String aszGlobalIssuesTID = globalIssuesTID + "";
String aszOrganizationalDevelopmentTID = organizationalDevelopmentTID + "";
String aszReconciliationTID = reconciliationTID + "";
String aszMinistryAreasTID = ministryAreasTID + "";
String aszPersonalityTypeTID = personalityTypeTID + "";
String aszUserSkillList = userprofile.getUSPSkillTypes();
String aszUserServiceList = userprofile.getUSPServiceAreas();
String aszUserMinistryAreas = userprofile.getUSPMinistryAreasCause();
String aszUserSpiritualDev = userprofile.getUSPSpiritualDev();
String aszUserGlobalIssues = userprofile.getUSPGlobalIssues();
String aszUserOrganizationalDev = userprofile.getUSPOrganizationalDev();
String aszUserReconciliation = userprofile.getUSPReconciliation();
String aszUserLookingFor = userprofile.getUSPLookingFor();
String aszUserOtherSkills = userprofile.getUSPOtherSkills();
String aszUserOtherService = userprofile.getUSPOtherPassions();
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
ArrayList aUserSkillList = new ArrayList ( 2 );
ArrayList aUserServiceList = new ArrayList ( 2 );
ArrayList aUserCauseList = new ArrayList ( 2 );
ArrayList aUserLookingForList = new ArrayList ( 2 );
ArrayList aUserOtherSkillsList = new ArrayList ( 2 );
ArrayList aUserOtherPassionsList = new ArrayList ( 2 );
ArrayList aUserOtherLearningInterestsList = new ArrayList ( 2 );

ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

aCodes.getCountryList( aCountryList, 101 );
aCodes.getStateInList( aStateList, 101 );

aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor );
aCodes.getTaxonomyCodeList( aSkillList, vidVolSkill );


aCodes.getTaxonomyChildCodeList( aSpiritualList, 8, spiritualTID );
aCodes.getTaxonomyChildCodeList( aGlobalIssuesList, 8, globalIssuesTID);
aCodes.getTaxonomyChildCodeList( aOrganizationalDevelopmentList, 8, organizationalDevelopmentTID);
aCodes.getTaxonomyChildCodeList( aReconciliationList, 8, reconciliationTID);
aCodes.getTaxonomyChildCodeList( aMinistryAreasList, 8, ministryAreasTID );

aCodes.getTaxonomyRelatedCodeList( aRelatedAreasList, 336, personalityTypeTID );

aCodes.getTaxonomyTermList( aUserLookingForList, 1, aszUserLookingFor);
aCodes.getTaxonomyTermList( aUserSkillList, 1, aszUserSkillList );
aCodes.getTaxonomyTermList( aUserServiceList, 1, aszUserServiceList );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserMinistryAreas );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserSpiritualDev );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserGlobalIssues );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserOrganizationalDev );
aCodes.getTaxonomyTermList( aUserCauseList, 1, aszUserReconciliation );
out.print("<!-- EXAMPLE " + aszUserServiceList + "-->");
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

tempTerms = aszUserOtherService;
if(tempTerms.length() > 1){
	if(tempTerms.indexOf(",") == -1)
		tempTerms = aszUserOtherService + ",";
	tempTerms = tempTerms.substring(0, tempTerms.indexOf(","));
	try{
		int tid = Integer.parseInt(tempTerms);
	} catch (NumberFormatException e) {
		isTidList = false;
	}
}

if(isTidList){
aCodes.getTaxonomyTermList( aUserOtherPassionsList, 1, aszUserOtherService );
} else {
aCodes.getTaxonomyTIDListFromNames( aUserOtherPassionsList, 1, aszUserOtherService, 338);
}

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

String personality = userprofile.getUSPPersonality();
//out.print("personality type is: <br>"+personality+"<br>");
String personalityName = "";

if (personality.equalsIgnoreCase("ENFP"))
	personalityName = "The Inspirer";
else if (personality.equalsIgnoreCase("ENFJ"))
	personalityName = "The Giver";
else if (personality.equalsIgnoreCase("ENTJ"))
	personalityName = "The Chief";
else if (personality.equalsIgnoreCase("ENTP"))
	personalityName = "The Visionary";
else if (personality.equalsIgnoreCase("ESFJ"))
	personalityName = "The Caregiver";
else if (personality.equalsIgnoreCase("ESFP"))
	personalityName = "The Performer";
else if (personality.equalsIgnoreCase("ESTJ"))
	personalityName = "The Guardian";
else if (personality.equalsIgnoreCase("ESTP"))
	personalityName = "The Doer";
else if (personality.equalsIgnoreCase("INFJ"))
	personalityName = "The Protector";
else if (personality.equalsIgnoreCase("INFP"))
	personalityName = "The Idealist";
else if (personality.equalsIgnoreCase("INTJ"))
	personalityName = "The Strategist";
else if (personality.equalsIgnoreCase("INTP"))
	personalityName = "The Thinker";
else if (personality.equalsIgnoreCase("ISFJ"))
	personalityName = "The Nurturer";
else if (personality.equalsIgnoreCase("ISFP"))
	personalityName = "The Artist";
else if (personality.equalsIgnoreCase("ISTJ"))
	personalityName = "The Duty Fulfiller";
else if (personality.equalsIgnoreCase("ISTP"))
	personalityName = "The Mechanic";

String domain = "";
if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){
	domain = "find-your-calling";
} else if (aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )){
	domain = "worldchanger";
} else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){
	domain = "fycsandbox";
}

JSONObject obj = new JSONObject();
obj.put("pic", "http://www.christianvolunteering.org/imgs/results-img.gif");
obj.put("name", "");
//out.print(aszRemoteHost);
if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" ) || aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ 
		
		

	//String sessionKey = (String) session.getAttribute("FB_session_key");
	
	//out.println(sessionKey);
   if(session.getAttribute("FB_session_key") == null ){
			session.setAttribute("FB_session_key","");  // clearing Value in session Object
	}
	
 %>

<% } %>


<div style="display:none;">
<br />
<form id="personalityPublishForm" name="personalityPublishForm" target="_self" action="<%=aszPortal%>/register.do?method=processPersonalityPublish" method="post">
    <br />
</form>
</div>

<style type="text/css">
* {//margin:0; //padding:0;}
h4{margin-bottom:5px;}
#maincontent.sidebarless{width: 100%; backround-image:none; border:none;}
#contentwrapper {background-image:none; background-color:#FFF; width:760px;}
#pagebanner {//width:950px;}
.main_text {
padding:10px;
font-size:11px;
}
.main_text p {padding-bottom: 10px;}
#account_box table {text-align:left;}
.imgleft{float:left; width:140px; height:200px}
.imgleft2{float:left; width:365px; height:265px}
#account_box{
border:5px solid #E7E8B6; 
float:left;
margin-left:0;
width:700px;
min-height:400px;
//padding-top:10px;
background:none;
height:auto;
}
#account_box h3 {
font-size:24px;
padding:10px 20 0;
//padding:0px 20 0px;
margin:0px;
}
#account_box h4 {
font-size:30px;
color:#1E5761;
padding:0px 35px;
//padding:10px 0 10px 10px;
margin:0px;
}
#account_box h5 {
font-size:18px;
margin:0px;
font-style:italic;
font-weight:normal;
padding-bottom:5px;
}
#account_box h6 {
font-size:14px;
padding:10px;
margin:0px;
color:#1E5761;
float:right;
}
.main_text th {
color:#1E5761;
float:left;
font-size:14px;
}
#account_box h4 .main_text { //font-weight:normal; //color:#000;}
#links {text-align:center;}
.main_text td {width: 400px;}
#characteristics, #ministrystrengths, #ministryWeaknesses, #growthpath, #notablepeople {width:400px; padding:10px 10px 0 0; float:left;}
#left-column {//float:left; //width:400px;}
#LookingFor, #ServiceAreas, #ministrySkills, #ministryInterests {padding:10px 10px 0 0;}
a { color:#000000; font-weight:inherit;}
#footer {padding:0px;}
</style>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

 <div id="body">
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



<script type="text/javascript">
var pic;
	// Make sure the user has authorized the app
	window.onload = FB.getLoginStatus(function(response) {
		
		if(response.authResponse){
			//logged in and connected user, someone you know
			//alert('session');
			
		
			var fullName;
   			var firstName;
  			var gender;
			
			FB.api('/me', function(response) {
		   //alert('query');
		   		var query = FB.Data.query('SELECT uid, first_name, name, sex FROM user WHERE uid = me()');
				query.wait(function(user){
					//alert('wait');
					var image = document.getElementById('image');
              		image.src = 'http://graph.facebook.com/' + user[0].uid + '/picture';
			  		fullName = user[0].name;
					
					firstName = user[0].first_name;
					gender = user[0].sex;
					
					//alert(fullName);
					//alert(firstName);
					//alert(gender);
					<%  if(userprofile.getPersonalityPublished() != 1){ 
					//The user has not published yet, so set up the Dialog to ask them to
							//session.setAttribute("AskedToPublish", "true");  // set the flag to say the user has been asked to publish
					%>
					//alert('not published');
						var action_links = [{'name':'Take the Personality Test','link':			'http://apps.facebook.com/<%=domain%>/register.do?method=showPersonalityTest'}];
   
	
						var description = firstName + ' just took the FindYourCalling Personality Test and is an <%=userprofile.getUSPPersonality()%> \'\'<%=personalityName%>\'\'!';
						//alert(description);
	
						if(gender == 'female'){
							description += ' She ';
						} else if (gender == 'male'){
							description += ' He ';
						} else {
							description += ' He/She ';
						}
						
						description += 'found hundreds of ministry opportunities that match ';
						
							if(gender == 'female'){
							description += 'her ';
						} else if (gender == 'male'){
							description += 'his ';
						} else {
							description += 'his/her ';
						}
	
						description += 'type!';
						
						
						//alert(description);
						
						var title = fullName + ' is an <%=userprofile.getUSPPersonality()%> \'\'<%=personalityName%>\'\'!';
					   

		 				FB.ui(
						{
						 method: 'feed',
						 name: title,
						 link: 'http://apps.facebook.com/<%=domain%>',
						 caption: 'Find Your Calling',
						 
						 picture: 'http://facebook.christianvolunteering.org/imgs/FYC-Logo75.gif',
					
						 description: description,
						 actions: action_links,
						 app_id: '<%=appid%>',
					   },
					   function(response) {
						 if (response && response.post_id) {
						   //alert('Post was published.');
						   document.forms["personalityPublishForm"].submit();
						 } else {
						   //alert('Post was not published.');
						   document.forms["personalityPublishForm"].submit();
						 }
					   }
					 );


				<% } %>
					
				});
			});
			




			<% //String redirect = request.getParameter("redirect");
				//if(redirect != null){
					//if(! redirect.equalsIgnoreCase("false")){ %>
						//top.location.href = "http://apps.facebook.com/<%=domain%>/register.do?method=showPersonalityResults&redirect=false"; 
			<% 		//} }
							
			%>
				
			//getAccessToken(); //get the access token for the Graph API

		} else { 
			//no user session available, redirect to OAuth Workflow
			//alert('no session');
			redirect();

		}});
		 
	function redirect(){
		<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>		&redirect_uri=http://facebook.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://facebook.ivolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } %>
		//top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/ministryopportunities.jsp?redirect=true&scope=email";
	}
		

	

</script>
<!--
personalitytypei <%="" + userprofile.getUSPPersonalityI()%>" />
personalitytypee <%="" + userprofile.getUSPPersonalityE()%>" />
personalitytypes <%="" + userprofile.getUSPPersonalityS()%>" />
personalitytypen <%="" + userprofile.getUSPPersonalityN()%>" />
personalitytypef <%="" + userprofile.getUSPPersonalityF()%>" />
personalitytypet <%="" + userprofile.getUSPPersonalityT()%>" />
personalitytypej <%="" + userprofile.getUSPPersonalityJ()%>" />
personalitytypep <%="" + userprofile.getUSPPersonalityP()%>" />
personalitytype <%=userprofile.getUSPPersonality()%>" />
-->
<%
 	//String permURL = "http://www.facebook.com/authorize.php?api_key=" + appapikey + 
	//	"&v=1.0&ext_perm=publish_stream";
	String permURL = "http://graph.facebook.com/oauth/authorize?client_id=" + appapikey +
		"&redirect_uri=http://facebook.ivolunteering.org&scope=publish_stream";	
%>


<script language="javascript">
/*function redirect() {
	window.location = "http://graph.facebook.com/oauth/authorize?client_id=b25eee679593f530b86d0ef38db92b07&redirect_uri=http://facebook.ivolunteering.org&scope=publish_stream";
}*/
</script>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ %>



<% } %>



<%	
if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ) {	
	 if( userprofile.getUSPPersonality().equalsIgnoreCase( "ISTJ" )){ %>
	<%@ include file="/included/personality_istj_nf.html" %>
	<% }  else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISFJ" )){ %>
    <%@ include file="/included/personality_isfj_nf.html" %>
   <% }  else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INFJ" )){ %>
    <%@ include file="/included/personality_infj_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INTJ" )){ %>
   <%@ include file="/included/personality_intj_nf.html" %>
   <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISTP" )){ %>
    <%@ include file="/included/personality_istp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISFP" )){ %>
    <%@ include file="/included/personality_isfp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INTP" )){ %>
    <%@ include file="/included/personality_intp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESTP" )){ %>
    <%@ include file="/included/personality_estp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESFP" )){ %>
    <%@ include file="/included/personality_esfp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENFP" )){ %>
    <%@ include file="/included/personality_enfp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENTP" )){ %>
    <%@ include file="/included/personality_entp_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESTJ" )){ %>
    <%@ include file="/included/personality_estj_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESFJ" )){ %>
   <%@ include file="/included/personality_esfj_nf.html" %>
   <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENFJ" )){ %>
    <%@ include file="/included/personality_enfj_nf.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENTJ" )){ %>
    <%@ include file="/included/personality_entj_nf.html" %>
		<% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INFP")){ %>
	<%@ include file="/included/personality_infp_nf.html" %>
    <% } else { %>
    <%//@ include file="/included/personality_test_redirect.html" %>
    <% } %>
<% } else {
	//Include the appropriate file based on personality type
   if( userprofile.getUSPPersonality().equalsIgnoreCase( "ISTJ" )){ %>
	<%@ include file="/included/personality_istj.html" %>
	<% }  else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISFJ" )){ %>
    <%@ include file="/included/personality_isfj.html" %>
   <% }  else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INFJ" )){ %>
    <%@ include file="/included/personality_infj.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INTJ" )){ %>
   <%@ include file="/included/personality_intj.html" %>
   <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISTP" )){ %>
    <%@ include file="/included/personality_istp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ISFP" )){ %>
    <%@ include file="/included/personality_isfp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INTP" )){ %>
    <%@ include file="/included/personality_intp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESTP" )){ %>
    <%@ include file="/included/personality_estp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESFP" )){ %>
    <%@ include file="/included/personality_esfp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENFP" )){ %>
    <%@ include file="/included/personality_enfp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENTP" )){ %>
    <%@ include file="/included/personality_entp.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESTJ" )){ %>
    <%@ include file="/included/personality_estj.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ESFJ" )){ %>
   <%@ include file="/included/personality_esfj.html" %>
   <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENFJ" )){ %>
    <%@ include file="/included/personality_enfj.html" %>
    <% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "ENTJ" )){ %>
    <%@ include file="/included/personality_entj.html" %>
		<% } else if (userprofile.getUSPPersonality().equalsIgnoreCase( "INFP")){ %>
	<%@ include file="/included/personality_infp.html" %>
    <% } else { %>
    <%//@ include file="/included/personality_test_redirect.html" %>
    <% } %>
<% }  %>







<div id="profileAddDiv"></div>

<% if ( ! userprofile.getUSPPersonality().equals("")){ %>


<div id="right-column">

<div id="LookingFor" class="LookingFor">
<table>

<th>Service Interests</th>


<%
int iArraySize = aUserLookingForList.size();
for(int index=0; index < aUserLookingForList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserLookingForList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	String aszDisplay = aAppCode.getAPCDisplay();
			if(iTid==17261){
				if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
					out.print("<tr><td><a href=\"#");
				}else{
					out.print("<tr><td><a target=\"_new\" href=\"http://www.urbanministry.org/jobs");
				}
			}else if(iTid==17266){
				if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
					out.print("<tr><td><a target=\"_new\" href=\"http://www.urbanresource.net");
				}else{
					out.print("<tr><td><a target=\"_new\" href=\"http://www.urbanministry.org/redirect-home");
				}
			}else{
				if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
					out.print("<tr><td><a href=\"/volunteeropportunities.jsp");
				}else{
					out.print("<tr><td><a href=\"/ministryopportunities.jsp");
				}
				if(iTid==17254){
					out.print("#local");
				}else if(iTid==17257){
					out.print("#virtual");
				}else if(iTid==17255){
					out.print("#groupfam");
				}else if(iTid==17258){
					out.print("#stm");
				}else if(iTid==17259){
					out.print("#sumintern");
				}else if(iTid==17260){
					out.print("#workstudy");
				}
			}	
			out.print( "\">" + aszDisplay + "</a></td></tr>" );
}


%>
<% if ( (aUserLookingForList.size() != 0) ){ %>
 
<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Customize this list</a></td></tr>
</table>
</div>
<% } else { %>

<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Add Service Interests</a></td></tr>
</table>
</div>
<% } %>



<div id="ServiceAreas" class="ServiceAreas">
<table>

<th>Service Areas</th>


<%
iArraySize = aUserServiceList.size(); out.print("<!-- service list array size is:" + iArraySize + "  -->");
for(int index=0; index < aUserServiceList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserServiceList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
}

iArraySize = aUserOtherPassionsList.size();
for(int index=0; index < aUserOtherPassionsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherPassionsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
}
%>

<% if ( (aUserServiceList.size() != 0) || (aUserOtherPassionsList.size() != 0)  ){ %>
 
<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Customize this list</a></td></tr>
</table>
</div>
<% } else { %>

<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Add Service Areas</a></td></tr>
</table>
</div>
<% } %>
</table>
</div>

<div id="ministrySkills" class="ministrySkills">
<table>

<th>Ministry Skills</th>


<%
 iArraySize = aUserSkillList.size();
for(int index=0; index < aUserSkillList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserSkillList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
}
iArraySize = aUserOtherSkillsList.size();
for(int index=0; index < aUserOtherSkillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherSkillsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
}
%>



<% if ( (aUserSkillList.size() != 0) ||  (aUserOtherSkillsList.size() != 0) ){ %>
 
<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Customize this list</a></td></tr>
</table>
</div>
<% } else { %>

<tr><td><a href="/register.do?method=showPersonalityMinistryAreasTest">Add Ministry Skills</a></td></tr>
</table>
</div>
<% } %>

<div id="ministryInterests" class="ministryInterests">
<table>
<% if ( (aUserCauseList.size() != 0) || (aUserOtherLearningInterestsList.size() != 0) ) { %>
<th>Learning Interests</th>
<% } %>

<%
 iArraySize = aUserCauseList.size();
for(int index=0; index < aUserCauseList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserCauseList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
}
 iArraySize = aUserOtherLearningInterestsList.size();
 for(int index=0; index < aUserOtherLearningInterestsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aUserOtherLearningInterestsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<tr><td>" + aszDisplay + "</td></tr>" );
} 

String learningInterestsURL = "register.do?method=showPersonalityMinistryAreas2Test&personalitytypetid=" + userprofile.getUSPPersonalityTID() + 
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
				
String ministryInterestsURL = "register.do?method=showPersonalityMinistryAreasTest&personalitytypetid=" + userprofile.getUSPPersonalityTID() + 
			"&personalitytype=" + userprofile.getUSPPersonality() + "&personalitypageno=" + userprofile.getUSPPersonalityPageNo() +
			"&personalitytypee=" + userprofile.getUSPPersonalityE() + "&personalitytypei=" + userprofile.getUSPPersonalityI() +
			"&personalitytypes=" + userprofile.getUSPPersonalityS() + "&personalitytypen=" + userprofile.getUSPPersonalityF() + 
			"&personalitytypet=" + userprofile.getUSPPersonalityT() + "&personalitytypej=" + userprofile.getUSPPersonalityJ() +
			"&personalitytypep=" + userprofile.getUSPPersonalityP() + "&personalityei=" + userprofile.getUSPPersonalityEI() +
			"&personalitysn=" + userprofile.getUSPPersonalitySN() + "&personalityft=" + userprofile.getUSPPersonalityFT() + 
			"&personalityjp=" + userprofile.getUSPPersonalityJP();
%>



<% if ( (aUserCauseList.size() != 0) || (aUserOtherLearningInterestsList.size() != 0) ) { %>
<tr><td><a href="<%=learningInterestsURL%>">Customize this list</a></td></tr>
</table>
</div>
<% } else { %>

<tr><td><a href="<%=learningInterestsURL%>">Add Learning Interests</a></td></tr>
</table>
</div>
<% } %>

</div>
<% } %>

<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<a style="float: right;" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities near your city" href="/volunteeropportunities.jsp"><img align="right" style="padding:10px; //margin-right:35px;" border="none" src="http://christianvolunteering.org/imgs/vol-opp-button.gif"/></a>
<% } else { %>
<a style="float: right;" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities near your city" href="/ministryopportunities.jsp"><img align="right" style="padding:5px 10px 10px;" border="none" src="http://christianvolunteering.org/imgs/ministry-oppor-button.gif"/></a>
<% } %>




</div>


</div>

<!--
 <fb:serverfbml style="width: 750px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://facebook.ivolunteering.org/" method="POST" invite="true" type="WorldChanger" content="		Take a personality test to find out your type, then get matched to volunteer opportunities that may interest you! <fb:req-choice url='http://apps.facebook.com/worldchanger' 
             label='Take Personality Test' />"> 
  <fb:multi-friend-selector showborder="false" actiontext="Invite your Facebook Friends to use WorldChanger" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml> 
-->

<% //=userprofile.getPersonalityPublished()%>


</div>
</div>




<!-- start sidebar information -->

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
<%@include file="/template/footer.inc" %><!-- end footer information -->



