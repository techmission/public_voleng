<!-- start JSP information -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information -->


<%//@ include file="/template_include/facebookapi_keys.inc" %>
<%@page import="com.google.code.facebookapi.FacebookWebappHelper"%>
<%@page import="java.util.*" %>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
	//FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

	String frameURL = "";
		if(session.getAttribute("FB_session_key") == null ){
			if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
				frameURL += "http://apps.facebook.com/worldchanger/";
			} else if (aszRemoteHost.equalsIgnoreCase("fycsandbox.christianvolunteering.org")) {
				frameURL += "http://apps.facebook.com/fycsandbox/";
			} else {
				frameURL += "http://apps.facebook.com/find-your-calling/";
			}
		} 
		else { 
		}
	
	frameURL += "/register.do?method=showMyMinistryOpps";

	//if(facebookHelp.requireLogin(frameURL)) return;
	//if(facebookHelp.requireFrame(frameURL)) return;
}
%>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<% 
out.print("<!-- aszPortal is "+aszPortal +" -->");

String code = request.getParameter("code");

String redirectUrl = "https://graph.facebook.com/oauth/access_token?client_id="+ appid + "&redirect_uri=http://fycsandbox.christianvolunteering.org/&client_secret=" + appsecret + "&code=" + code + "&type=web_server";
%>
<div id="fb-root"></div>
 <script src="http://connect.facebook.net/en_US/all.js"></script>
 <script>
   FB.init({
     appId  : '<%=appid%>',
     status : true, // check login status
     cookie : true, // enable cookies to allow the server to access the session
     xfbml  : true  // parse XFBML
   });
   
   window.fbAsyncInit = function(){
      FB.Canvas.setAutoResize();
	  
	}
	
	//FB.Event.subscribe('auth.login', function(response) {
	//	top.location.href = "http://apps.facebook.com/fycsandbox/personalitytest.jsp";
	//});
 </script>



<script type="text/javascript">
	// Make sure the user has authorized the app
	
	
	window.onload = FB.getLoginStatus(function(response) {
		if(response.session){
			//logged in and connected user, someone you know
			//alert('session');
			//getAccessToken(); //don't need an access token on this page...
			//alert(window.location.host);
			//if(window.location.host == 'fycsandbox.christianvolunteering.org'){
			<% //String redirect = request.getParameter("redirect");
				//if((redirect != null) && ! (redirect.equals(""))){
					//if (redirect.equalsIgnoreCase("true")){ %>
						//top.location.href = "http://apps.facebook.com/fycsandbox/personalitytest.jsp";
			<% 		//} 
				//}			
			%>

		} else { 
			//no user session available, redirect to OAuth Workflow
			//alert('no session');
			redirect();
	/*FB.ui(
   		{
    	 method: 'oauth',
    	 scope: 'email',
		 client_id: '',
		
  		},
   		function(response) {
     		if (response && response.code) {
      		 alert('Permission acquired');
     		} else if (response && response.error){
     		  alert('Error authorizing: ' + response.errorDescription);
    		} else {
	 			alert('Failed');
			}
   		});*/


		}});
	
	function redirect(){
		<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>		&redirect_uri=http://facebook.christianvolunteering.org/facebook_auth_redirect.jsp?redirect=true&scope=email";
	<% } else if (aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://facebook.ivolunteering.org/facebook_auth_redirect.jsp?redirect=true&scope=email";
	<% } else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/facebook_auth_redirect.jsp&scope=email";
	<% } %>
		//top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/ministryopportunities.jsp?redirect=true&scope=email";
	}
</script>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>
<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
int iTemp=0;

// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		vidBenefits=286,
		vidVolSkill=18, vidVolDenom=262, vidVolOrgAffil=20, vidState=52, vidCountry=261, vidVolVirt=49,
		vidVolLang=48, vidVolInterestArea=46, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

// vidMemberType=42
int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264										&workstudy=8104
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;

// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;

int iLocalVolTID = 17254,iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,
		iSocJustGrpsTID = 17266, iLocalOrgsTID = 21853, iSTMTID=17258
		, personalityTypeTID = userprofile.getUSPPersonalityTID();

// non-dropdown taxonomy term id's (tid)

// vidRoomBoard=265										&roomboard=iYesRoomBoard8106
int iNoRoomBoard=8105, iYesRoomBoard=8106;

// vidStipend=266
int iNoStipend=8107, iYesStipend=8108;


int tidworkstudyUser=6225;
int tidBoardMemberUser=6217;
//int iVolBoardTID=17256;
int tidBoardMemberOpp=4761;
int tidworkstudyOpp=4769;
//int iLocalVolTID=17254;
//int iSumIntrnTID=17259;
//int iIntrnTID=17258;
//int iVolVirtTID=17257;
//int iGroupFamilyTID=17255;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszBothTID = "" + iBoth;
String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;

String aszUserLookingFor = userprofile.getUSPLookingFor();
String aszUserOtherPassions = userprofile.getUSPOtherPassions();
String aszUserOtherSkills = userprofile.getUSPOtherSkills();
String aszUserSkillList = userprofile.getUSPSkillTypes();
String aszUserServiceList = userprofile.getUSPServiceAreas();
String aszLookingForVID="" + vidLookingFor;
String aszServiceVID = vidService + "";

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList aLanguageList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLookingForList = new  ArrayList ( 2 );
ArrayList aUserLookingForList = new ArrayList ( 2 );
ArrayList aUserOtherPassionsList = new ArrayList ( 2 );
ArrayList aUserOtherSkillsList = new ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ArrayList aRelatedAreasList = new ArrayList ( 2 );
ArrayList aUserSkillList = new ArrayList ( 2 );
ArrayList aUserServiceList = new ArrayList ( 2 );

ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

/* New code for iVol/ChrisVol exclusions, make sure to take out any other reference to service list */
int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;


if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );
// End of New code

aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor, 303 );
aCodes.getTaxonomyTermList( aUserLookingForList, 1, aszUserLookingFor);
aCodes.getTaxonomyRelatedCodeList( aRelatedAreasList, 336, personalityTypeTID );

aCodes.getTaxonomyTermList( aUserLookingForList, 1, aszUserLookingFor);
aCodes.getTaxonomyTermList( aUserSkillList, 1, aszUserSkillList );
aCodes.getTaxonomyTermList( aUserServiceList, 1, aszUserServiceList );

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


String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

String aszLocal = "display:none;";
String aszVirtual = "display:none;";
String aszGrpFamily = "display:none;";
String aszSTM = "display:none;";
String aszSumIntrn = "display:none;";
String aszIntrn = "display:none;";
String aszWorkStudy = "display:none;";
String aszBoard = "display:none;";
String aszTraining = "display:none;";
String aszLatest = "display:inline;";
String aszLocalOrgs = "display:none;";
/*
if(aCurrentUserObj.getUSPLocalVolTID()==iLocalVolTID){
	aszLocal = "display:inline;";
}
if(aCurrentUserObj.getUSPVolVirtTID()==iVolVirtTID){
	aszVirtual = "display:inline;";
}
if(aCurrentUserObj.getUSPGroupFamilyTID()==iGroupFamilyTID){
	aszGrpFamily = "display:inline;";
}
if(aCurrentUserObj.getUSPLocalVolTID()==tidworkstudyUser){
	aszSTM = "display:inline;";
}
if(aCurrentUserObj.getUSPSumIntrnTID()==iSumIntrnTID){
	aszSumIntrn = "display:inline;";
}
if(aCurrentUserObj.getUSPIntrnTID()==iIntrnTID){
	aszIntrn = "display:inline;";
}
*/

//out.print(aCurrentUserObj.getUSPLookingFor());


if(
	aCurrentUserObj.getUSPLookingFor().contains(","+iWorkStudyTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iWorkStudyTID+",") ||
	aCurrentUserObj.getUSPVolInterestArea1TID()==tidworkstudyUser || 
	aCurrentUserObj.getUSPVolInterestArea2TID()==tidworkstudyUser || 
	aCurrentUserObj.getUSPVolInterestArea3TID()==tidworkstudyUser ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iWorkStudyTID ) > 0 
){
	aszWorkStudy = "display:inline;";
}
if(
	aCurrentUserObj.getUSPVolBoardTID()==iVolBoardTID || 
	aCurrentUserObj.getUSPLookingFor().contains(","+iVolBoardTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iVolBoardTID+",") ||
	aCurrentUserObj.getUSPVolInterestArea1TID()==tidBoardMemberUser || 
	aCurrentUserObj.getUSPVolInterestArea2TID()==tidBoardMemberUser || 
	aCurrentUserObj.getUSPVolInterestArea3TID()==tidBoardMemberUser ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iVolBoardTID ) > 0 
){
	aszBoard = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iLocalVolTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iLocalVolTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iLocalVolTID ) > 0 
){
	aszLocal = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iVolVirtTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iVolVirtTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iVolVirtTID ) > 0 
){
	aszVirtual = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iGroupFamilyTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iGroupFamilyTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iGroupFamilyTID ) > 0 
){
	aszGrpFamily = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iSTMTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iSTMTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iSTMTID ) > 0 
){
	aszSTM = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iSumIntrnTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iSumIntrnTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iSumIntrnTID ) > 0 
){
	aszSumIntrn = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iIntrnTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iIntrnTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iIntrnTID ) > 0 
){
	aszIntrn = "display:inline;";
}
if(aCurrentUserObj.getUSPLookingFor().contains(","+iLocalOrgsTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iLocalOrgsTID+",") ||
	Arrays.binarySearch(aCurrentUserObj.getUSPLookingForArray(),iLocalOrgsTID ) > 0 
){
	aszLocalOrgs = "display:inline;";
}

String aszSkills="";
String aszServiceAreas="";
int iPrayerIntercessor = 4748, iPreacher = 4749;
int iBibleStudy=4760, iChurchPlanting=4764 ,iEvangelism=4772 ,iFamAdMin=4773 , iSingleParCrisisPreg=4783 , iVBS=4787 , iWomensMin=4789 , iChrisCathSchools=7341 ,iReligiousEd=7342;

if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	//skills
	if(
		aCurrentUserObj.getUSPSkillTypes().startsWith(iPrayerIntercessor+",") ||
		aCurrentUserObj.getUSPSkillTypes().startsWith(iPreacher+",") 
	){
		aCurrentUserObj.setUSPSkillTypes(aCurrentUserObj.getUSPSkillTypes().substring(5,aCurrentUserObj.getUSPSkillTypes().length()));
	}
	if(
		aCurrentUserObj.getUSPSkillTypes().contains(","+iPrayerIntercessor+",") ||
		aCurrentUserObj.getUSPSkillTypes().contains(","+iPreacher+",") 
	){
		aCurrentUserObj.setUSPSkillTypes(aCurrentUserObj.getUSPSkillTypes().replace(","+iPrayerIntercessor+",",","));
		aCurrentUserObj.setUSPSkillTypes(aCurrentUserObj.getUSPSkillTypes().replace(","+iPreacher+",",","));
	}
	//service areas
	if(
		aCurrentUserObj.getUSPServiceAreas().startsWith(iBibleStudy+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iChurchPlanting+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iEvangelism+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iFamAdMin+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iSingleParCrisisPreg+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iVBS+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iWomensMin+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iChrisCathSchools+",") ||
		aCurrentUserObj.getUSPServiceAreas().startsWith(iReligiousEd+",") 
	){
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().substring(5,aCurrentUserObj.getUSPServiceAreas().length()));
	}
	if(
		aCurrentUserObj.getUSPServiceAreas().contains(","+iBibleStudy+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iChurchPlanting+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iEvangelism+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iFamAdMin+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iSingleParCrisisPreg+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iVBS+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iWomensMin+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iChrisCathSchools+",") ||
		aCurrentUserObj.getUSPServiceAreas().contains(","+iReligiousEd+",") 
	){
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iBibleStudy+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iChurchPlanting+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iEvangelism+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iFamAdMin+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iSingleParCrisisPreg+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iVBS+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iWomensMin+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iChrisCathSchools+",",","));
		aCurrentUserObj.setUSPServiceAreas(aCurrentUserObj.getUSPServiceAreas().replace(","+iReligiousEd+",",","));
	}
}
if(aCurrentUserObj.getUSPSkillTypes().length()>0){
	aszSkills="&skilltypes="+aCurrentUserObj.getUSPSkillTypes().substring(0,aCurrentUserObj.getUSPSkillTypes().length()-1);
}
if(aCurrentUserObj.getUSPServiceAreas().length()>0){
	aszServiceAreas="&serviceareas="+aCurrentUserObj.getUSPServiceAreas().substring(0,aCurrentUserObj.getUSPServiceAreas().length()-1);
}

String aszLocalRegion = "";
if(aCurrentUserObj.getUSPAddrCountryName().equalsIgnoreCase("us")){
	if(aCurrentUserObj.getCityTID() > 0){
		aszLocalRegion = "&citytid=" + aCurrentUserObj.getCityTID();
	}else{
		aszLocalRegion = "&postalcode=" + aCurrentUserObj.getUSPAddrPostalcode();
	}
}else{
	if(aCurrentUserObj.getCountryTID()>0){
		aszLocalRegion = "&countrytid=" + aCurrentUserObj.getCountryTID();
	}else{
		aszLocalRegion = "&country=" + aCurrentUserObj.getUSPAddrCountryName();
	}
}
String localURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	aszLocalRegion+
	"&postype=4794"+
	"&distance=City"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	localURL+="&requiredcodetype=No";
}
String virtualURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&postype=4795"+
	"&distance=City"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	virtualURL+="&requiredcodetype=No";
}
String grpFamURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	aszLocalRegion+
	"&postype="+
	"&greatforgroup=4793"+
	"&greatforfamily=7536"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	grpFamURL+="&requiredcodetype=No";
}
String stmURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&postype=4796"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	stmURL+="&requiredcodetype=No";
}
String sumInternURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&postype=4796"+
	"&distance=City"+
	"&duration=Summer+%28June-Aug%29"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	sumInternURL+="&requiredcodetype=No";
}
String internURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&postype=4796"+
	"&distance=City"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	internURL+="&requiredcodetype=No";
}
String workStudyURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	aszLocalRegion+
	"&postype=4796"+
	"&workstudy="+aszYesWorkStudyTID;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	workStudyURL+="&requiredcodetype=No";
}
String boardURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&servicearea1="+tidBoardMemberOpp;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	boardURL+="&requiredcodetype=No";
}
String noTabsURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	aszLocalRegion+
	"&postype=4794"+
	"&distance=City"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	localURL+="&requiredcodetype=No";
}
String trainingURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsTab"+
	"&taxonomies=4793,7536,4790,4796,4794"+
	"&postype=4795"+
	"&servicearea1=4766"+
	"&servicearea2=4779"+
	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	trainingURL+="&requiredcodetype=No";
}
String localOrgsURL="/oppsrch.do?"+
	"method=processOrgSearchAll"+
	"&requesttype=myResultsTab"+
	aszLocalRegion+
	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	localOrgsURL+="&requiredcodetype=No";
}

int[] a_iLookingFor = aCurrentUserObj.getUSPLookingForArray();
String aszLookingFor = "";
String aszLookingForArray = "";

for(int indexArray=0; indexArray < a_iLookingFor.length; indexArray++){
	aszLookingForArray += "&lookingforarray="+a_iLookingFor[indexArray];
	aszLookingFor += a_iLookingFor[indexArray]+",";
}

String latestURL="/oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsLatestTab"+
	aszLocalRegion+
//	aszLookingForArray+
	"&lookingfor="+aszLookingFor+
	"&distance=City"+
	aszSkills+
	aszServiceAreas;
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	latestURL+="&requiredcodetype=No";
}


out.print("<!-- aszPortal is "+aszPortal +" localURL is " +localURL+" -->");

%>


<script language="javascript">
function ministry_local(){
	document.getElementById('ministry_tab_local_one_time').className = "active";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=localURL%>');
}
function ministry_virtual(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "active";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=virtualURL%>');
}
function ministry_grp_family(){ 
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "active";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=grpFamURL%>');
}
function ministry_stm(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "active";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=stmURL%>');
}
function ministry_sum_intrn(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "active";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=sumInternURL%>');
}
function ministry_intrn(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "active";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=internURL%>');
}
function ministry_work_study(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "active";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=workStudyURL%>');
}
function ministry_board(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "active";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=boardURL%>');
}
function no_tabs(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=noTabsURL%>');
}
function training(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=trainingURL%>');
}
function latest(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "active";
	document.getElementById('ministry_tab_local_orgs').className = "";
	sendRequest('GET', '<%=aszPortal%><%=latestURL%>');
}
function ministry_local_orgs(){
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_virtual').className = "";
	document.getElementById('ministry_tab_grp_family').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_sum_intrn').className = "";
	//document.getElementById('ministry_tab_intrn').className = "";
	document.getElementById('ministry_tab_work_study').className = "";
	document.getElementById('ministry_tab_board').className = "";
	document.getElementById('latest_tab').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "active";
	sendRequest('GET', '<%=aszPortal%><%=localOrgsURL%>');
}

	
$(document).ready(function() {
	var hashtag = '';
	if(location.hash){
		hashtag = location.hash.substring(1);
	}
	//alert (hashtag);
	if (hashtag == "localorgs"){
		ministry_local_orgs();
	}else if (hashtag == "local"){
		ministry_local();
	}else if (hashtag == "virtual"){
		ministry_virtual();
	}else if (hashtag == "groupfam"){
		ministry_grp_family();
	}else if (hashtag == "stm"){
		ministry_stm();
	}else if (hashtag == "sumintern"){
		ministry_sum_intrn();
	}else if (hashtag == "intern"){
		ministry_intrn();
	}else if (hashtag == "workstudy"){
		ministry_work_study();
	}else{
		ministry_local();
<% /*if(aCurrentUserObj.getUSPLookingFor().contains(","+iLocalVolTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iLocalVolTID+",")
){ %>
	ministry_local();
<% }
else if(aCurrentUserObj.getUSPLookingFor().contains(","+iVolVirtTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iVolVirtTID+",")
){ %>
	ministry_virtual();
<% }
else if(aCurrentUserObj.getUSPLookingFor().contains(","+iGroupFamilyTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iGroupFamilyTID+",")
){ %>
	ministry_grp_family();
<% }
else if(aCurrentUserObj.getUSPLookingFor().contains(","+iSumIntrnTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iSumIntrnTID+",")
){ %>
	ministry_sum_intrn();

<% }
else if(aCurrentUserObj.getUSPLookingFor().contains(","+iIntrnTID+",") || 
	aCurrentUserObj.getUSPLookingFor().startsWith(iIntrnTID+",")
){ %>
	ministry_intrn();
<% }
else
{ %>
	no_tabs();
<% } */%>
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

}
else {
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
}
 });  


function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

function toggle(chkbox, item1, item2) {
    //var visSetting = (chkbox.checked) ? "hidden" : "visible";
	var disableSetting = (chkbox.checked) ? "true" : "false";
	if (document.getElementById(item1).disabled == false)
	{
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}
	else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
	}
}
/* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */

</script>

<style>

a#ministry_tab_local_one_time, a#ministry_tab_virtual, a#ministry_tab_grp_family, a#ministry_tab_stm, a#ministry_tab_sum_intrn, 
a#ministry_tab_intrn, a#ministry_tab_work_study, a#ministry_tab_board, a#trn, a#latest_tab, a#ministry_tab_local_orgs{
cursor:pointer; 
text-decoration:none; 
color:#000000; 
font-size:1em;
//font-size:12px; 
font-weight: normal;
background:transparent url(<%=request.getContextPath()%>/imgs/tabs-off1.png) no-repeat; 
border-right: 1px solid #4D4d4d; 
padding:5px 5px 2px;
margin-bottom: 0px;
font-weight: bold;
color:#0E3E7C!important;
text-decoration:none!important;
}
a#ministry_tab_local_one_time.active, a#ministry_tab_virtual.active, a#ministry_tab_grp_family.active, a#ministry_tab_stm.active, 
a#ministry_tab_sum_intrn.active, a#ministry_tab_intrn.active, a#ministry_tab_work_study.active, a#ministry_tab_board.active, 
a#trn.active, a#latest_tab.active, a#ministry_tab_local_orgs.active{
background:transparent url(<%=request.getContextPath()%>/imgs/tabs-on1.png) no-repeat; 
border-right: 1px solid #4d4d4D;
font-weight: bold;
color:#FFF!important;
text-decoration:none!important;
}
#all_tabs {width:100%; margin-bottom: 20px;}
h5.benefits {margin: 0; font-size:12px; color:#000;}
select#country {width: 150px;}

</style>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(true==false){ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch" >
	  <span id="title" >search </span>
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

<span style="float: left;">search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/">home</a> &gt; <a href="<%=request.getContextPath()%>/search.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<div id="body">
	

<a href="javascript:;" onmousedown="if(document.getElementById('results').style.display == 'none'){ document.getElementById('results').style.display = 'inline'; document.getElementById('expanded').style.display = 'inline'; document.getElementById('collapsed').style.display = 'none';}else{ document.getElementById('results').style.display = 'none'; document.getElementById('collapsed').style.display = 'inline'; document.getElementById('expanded').style.display = 'none';}">Add or Edit Your Service Interests/Skills<div id='collapsed' style="display:inline"> &#9660; </div><div id='expanded' style="display:none">&#9650;</div></a>  
<div id="results" style="display:none">
 

<!-- Begin Ministry Areas section -->
<div id="personality">
<html:form method="post" action="/register.do">

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

<html:hidden property="method" value="updatePersonalityInterestsSelectFields" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden value="Volunteer" property="siteusetype" />
<html:hidden value="facebook" property="personinternalcomment" /> 

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>





			
</td></tr></table>
		</div>

<pre><font color="red"><bean:write name="individualForm" property="errormsg" /><%=userprofile.getErrorMsg()%></font></pre>
<div id="better-select-edit-taxonomy-<%=vidLookingFor+""%>" class="">
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
							} else if(iTid == 4763){
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
							if(index == (iArraySize-1)) out.print(" </td><td> ");
						}
 
%>
</td></tr>
</table>
<br />

<h3> Please fill in other areas that  you feel passionate about (seperated by commas): </h3> <br />
<input type="textbox" id="otherpassions" name="otherpassions" value = "<%=aszUserOtherPassionNames%>" size = "60"> 
		</div>
</div>

<hr />

<div id="better-select-edit-taxonomy-<%=vidLookingFor+""%>" class="">
		<label><h3> Please check (or uncheck) <% if (! aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("ministry"); } %> areas where you have skills, experience or education. We have already checked skill areas that are often of interest to people of your personality type.<span class="criticaltext">* </h3></span> </label><br />
		<div class="form-checkboxes form-checkboxes-scroll">
<table ><tr><td>

<%
iTemp = 0;
iArraySize = askillsList.size();
iArraySize = iArraySize / 3;
if( aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 971 ||
								iSubType == 972){
							}else if (iSubType == 968){
								out.print(" <div id=\"edit-taxonomy-" + vidSkill + "-0-wrapper\" class=\"form-item\">" + 
								"<label class=\"option\" for=\"edit-taxonomy-" + vidSkill + "-0\">" +
								"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" checked ");
								out.println(" >Musician</label></div> ");
								if(index == (iArraySize - 1)) out.print(" </td><td> ");
								if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
							}else if (iSubType == 8124){
								out.print(" <div id=\"edit-taxonomy-" + vidSkill + "-0-wrapper\" class=\"form-item\">" + 
								"<label class=\"option\" for=\"edit-taxonomy-" + vidSkill + "-0\">" +
								"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" checked ");
								out.println(" >Deaf Outreach</label></div> ");
								if(index == (iArraySize - 1)) out.print(" </td><td> ");
								if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
							}else{
								out.print(" <div id=\"edit-taxonomy-" + vidSkill + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + vidSkill + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize - 1)) out.print(" </td><td> ");
							if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
							}
						}
} else {
for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							//int aszTid = (String) aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <div id=\"edit-taxonomy-" + vidSkill + "-0-wrapper\" class=\"form-item\">" + 
				"<label class=\"option\" for=\"edit-taxonomy-" + vidSkill + "-0\">" +
					"<input id=\"" + aAppCode.getAPCIdSubType() + "\"  type=\"checkbox\" name=\"" + aAppCode.getAPCIdSubType() + "\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" checked ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</label></div> ");
							if(index == (iArraySize - 1)) out.print(" </td><td> ");
							if(index == (2 * iArraySize - 1)) out.print(" </td><td> ");
						}
}
%>
			
</td></tr>
</table>
<br />

<h3> Please write in any other areas where you have skills, experience or education (seperated by commas): </h3><br />
<input type="textbox" id="otherskills" name="otherskills" value = "<%=aszUserOtherSkillNames%>" size = "60">

		</div>
</div>

<button type="button" value="Submit" name="Submit" onClick="populateTaxonomies()">Save</button>

</html:form>
</div>

<!-- END Ministry Areas SECTION -->

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
							if((iTid != 17265) && (iTid != 17262) && (iTid != 21632) && (iTid != 21853)){
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
	
	//alert('lookingFor: ' + individualForm.lookingfor.value);
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
							if((iTid != 4769) && (iTid != 4761) && (iTid != 25154) && (iTid != 25155) && (iTid != 25156) && (iTid != 25157) && (iTid != 25158) && (iTid !=25159) && (iTid != 25160) && (iTid != 25161) && (iTid != 25162) && (iTid != 25163) && (iTid != 25164) && (iTid != 25165) && (iTid != 25166) && (iTid != 25167) && (iTid != 25168) && (iTid != 25169) && (iTid != 25170) && (iTid != 25171) && (iTid != 25172) ){
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
	
	//alert('ministry areas: ' + individualForm.serviceareas.value);
}

function setSkillTypes() {
	var skills = "";
<%
iTemp = 0;
for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
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
	//alert(skills);
}

function populateTaxonomies() {
	setMinistryAreaTypes();
	setSkillTypes();
	setLookingFor();
	
	// Set the personality type
	/*var individualForm = document.forms.individualForm;
	if(individualForm.personalityTypeDrop.value != "none"){
		individualForm.personalitytypetid.value = individualForm.personalityTypeDrop.value;
		individualForm.personalitytype.value = "";
	}*/
	
	document.forms["individualForm"].submit();
}

function updatePersonality() {
	var individualForm = document.forms.individualForm;
	individualForm.personalitytypetid.value = individualForm.personalityTypeDrop.value;
	individualForm.personalitytype.value = "";
}

function update() {
	
	setLookingFor();
	setMinistryAreaTypes();
	setSkillTypes();
	
	// Set the personality type
	var individualForm = document.forms.individualForm;
	if(individualForm.personalityTypeDrop.value != "none"){
		individualForm.personalitytypetid.value = individualForm.personalityTypeDrop.value;
		individualForm.personalitytype.value = "";
	}
	//document.forms["individualForm"].elements["method"].value = "updatePersonalityAccount";	
	//document.forms.individualForm.action='<%=request.getContextPath()%>/register.do';
	
	document.forms["individualForm"].submit();
}

</script>




<div id="all_tabs">
<A style="text-decoration: none" HREF="<%=request.getContextPath()%>/search.jsp"><h2>Find a Place to Volunteer</h2></A>

	<div id="ministrySectionLatest" class="ministryTab" style="<%=aszLatest%>" >
	<a id="latest_tab" class="#" class="active" onClick="latest()" name="requesttype" value="myResultsLatestTab" href="#latest">Latest</a>
	</div> 

	<div id="ministrySectionLocal" class="ministryTab" style="<%=aszLocal%>">
	<a id="ministry_tab_local_one_time" onClick="ministry_local()" name="requesttype" value="myResultsTab" href="#local"><span>Local</span></a>
	</div>
    
    <div id="ministrySectionVirtual" class="ministryTab" style="<%=aszVirtual%>">
	<a id="ministry_tab_virtual" class="#" onClick="ministry_virtual()" name="requesttype" value="myResultsTab" href="#virtual">Virtual</a>
	</div> 
      
	<div id="ministrySectionGrpFamily" class="ministryTab" style="<%=aszGrpFamily%>">
	<a id="ministry_tab_grp_family" class="#" onClick="ministry_grp_family()" name="requesttype" value="myResultsTab" href="#groupfam">Group/Family</a>
	</div> 
    
    <div id="ministrySectionSTM" class="ministryTab" style="<%=aszSTM%>">
	<a id="ministry_tab_stm" class="#" onClick="ministry_stm()" name="requesttype" value="myResultsTab" href="#stm"><% if ( aszSecondaryHost.equalsIgnoreCase("volengivol")) { } else {%> Missions /  <% } %>Internships</a>
	</div>
	
	<div id="ministrySectionSumIntrn" class="ministryTab" style="<%=aszSumIntrn%>">
	<a id="ministry_tab_sum_intrn" class="#" onClick="ministry_sum_intrn()" name="requesttype" value="myResultsTab" href="#sumintern">Summer Internships</a>
	</div>

	<!--div id="ministrySectionIntrn" class="ministryTab" style="<%=aszIntrn%>">
	<a id="ministry_tab_intrn" class="#" onClick="ministry_intrn()" name="requesttype" value="myResultsTab" href="#intern">All Internships</a>
	</div--> 

	<div id="ministrySectionWorkStudy" class="ministryTab" style="<%=aszWorkStudy%>">
	<a id="ministry_tab_work_study" class="#" onClick="ministry_work_study()" name="requesttype" value="myResultsTab" href="#workstudy">Work Study</a>
	</div> 

	<div id="ministrySectionBoard" class="ministryTab" style="<%=aszBoard%>">
	<a id="ministry_tab_board" class="#" onClick="ministry_board()" name="requesttype" value="myResultsTab" href="#board">Board Member</a>
	</div> 

	<div id="ministrySectionLocalOrgs" class="ministryTab" style="<%=aszLocalOrgs%>">
	<a id="ministry_tab_local_orgs" class="#" onClick="ministry_local_orgs()" name="requesttype" value="myResultsTab" href="#localorgs"><span>Organizations</span></a>
	</div>

<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
</div> <!-- end: div id all_tabs-->

<div id="ajax_res">
<center>
<br>
<h2>Please wait while we load your opportunities... </h2>
 <br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
</div>

<div class="cleardiv"></div>

<%/*
<script language="javascript">
//* add ability for a user to go directly to a link with a specific tab /
function myfunc () {
	if (window.location.hash.indexOf('searchOpportunities') != -1) { 
		var the_timeout = setTimeout("localopp_search();",0);
	}else if (window.location.hash.indexOf('searchInternships') != -1) { 
		var the_timeout = setTimeout("intrn_search();",0);
	}else if (window.location.hash.indexOf('searchShortTermMissions') != -1) { 
		var the_timeout = setTimeout("stm_search();",0);
	}else if (window.location.hash.indexOf('searchProfessional') != -1) { 
		var the_timeout = setTimeout("pro_search();",0);
	}else if (window.location.hash.indexOf('searchOrganizations') != -1) { 
		var the_timeout = setTimeout("org_search();",0);
	}else if (window.location.hash.indexOf('searchTraining') != -1) { 
		var the_timeout = setTimeout("trn_search();",0);
	}else if (window.location.hash.indexOf('searchAdvanced') != -1) { 
		var the_timeout = setTimeout("adv_search();",0);
	}
}
window.onload = myfunc();

</script>
*/
%>

<P><BR>&nbsp;</P>

</div>
</div>

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

