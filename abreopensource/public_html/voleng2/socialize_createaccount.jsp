<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->
<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Register to Find Christian Volunteer Opportunities</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style>
.create_box{
	width: 400px;
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
	height:370px; 
	min-height: 370px;
	margin: 0 10px 10px 10px;
}
.create_box a, .fb_box a{ color:#003366; text-decoration:none; line-height:2em;}
.create_box a:hover, .fb_box a:hover {text-decoration:underline;}
.login_box{
	width: 400px;
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
	height:160px; 
	min-height: 160px;
	margin: 0 10px 10px 10px;
}
.login_box a, .fb_box a{ color:#003366; text-decoration:none; line-height:2em;}
.login_box a:hover, .fb_box a:hover {text-decoration:underline;}
h3{margin:0px;}
</style>

<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		vidVolSkill=18, vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolInterestArea=46, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332;
int iTemp=0;
int iLocalVolTID = 17254,iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,iSocJustGrpsTID = 17266;

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList ainterestareaList = new  ArrayList ( 2 );
ArrayList alangList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList avolavailList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getTaxonomyCodeList( askillsList, vidVolSkill );
aCodes.getTaxonomyCodeList( ainterestareaList, vidVolInterestArea );
aCodes.getTaxonomyCodeList( alangList, vidVolLang );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( afiliationList, vidVolDenom );
aCodes.getTaxonomyCodeList( apartnersList, vidVolOrgAffil );
aCodes.getTaxonomyCodeList( avolavailList, vidVolAvail );

int iCCDA=1188;
int iWorldVision=1228;
int iHLIC=1209;
int iSalvArmy=1219;
int iAGRM=1179;
int iYPN=1232;
int iABS=11545;

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

session.putValue ("usertype","");  session.putValue ("taclite","");  session.putValue ("upnid","");  session.putValue ("upvid","");  session.putValue ("uplid","");  session.putValue ("uid","");

String aszSubmitDisplay="display:inline;";

if(
		(aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengroundtrip")) 
){ 
	aszSubmitDisplay="display:none;";
} 



String aszSubdomainShort = aszSubdomain;
if (aszSubdomainShort.equalsIgnoreCase("www.ChristianVolunteering.org")){
	aszSubdomainShort = "ChristianVolunteering.org";
}
%>

<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="java.security.SignatureException" %>
<%@ page import="javax.crypto.Mac" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.sql.Timestamp" %>

<%@ include file="/template_include/gigya_socialize_keys.inc" %>

<%
// security checking code copied/modified from: http://www.nsftools.com/tips/Base64Test.java and 
// http://docs.amazonwebservices.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/index.html?AuthJavaSampleHMACSignature.html 

// grab the data provided by gigya socialize
String aszProvider=""; String aszEmail=""; String aszUsername=""; String aszUID=""; String aszTimestamp=""; String aszSignature=""; 
String aszFirst=""; String aszLast=""; String aszPostal=""; String aszCountry=""; String aszState=""; String aszCity=""; String str_date="";
byte[] binaryKey = null;
String HMAC_SHA1_ALGORITHM = "HmacSHA1";
String mySignature=null;
DateFormat formatter ; 
Date date, today ; 
long socializeUnixTime, localUnixTime;
boolean withinTimeLimit=false;

String aszFacebookUID="";









/*
String aszQueryString = request.getQueryString();
out.println(aszQueryString);
*/
if(request.getParameter("provider") != null){
	aszProvider=request.getParameter("provider");
}
if(aszProvider != null){
	if(aszProvider.equalsIgnoreCase("facebook")){
		aszFacebookUID=request.getParameter("loginProviderUID");
	}
}
if(request.getParameter("email1addr") != null){
	aszEmail=request.getParameter("email1addr");
}
if(request.getParameter("username") != null){
	aszUsername=request.getParameter("username");
}
if(request.getParameter("uid") != null){
	aszUID=request.getParameter("uid");
}
if(request.getParameter("timestamp") != null){
	aszTimestamp=request.getParameter("timestamp");
}
if(request.getParameter("signature") != null){
	aszSignature=request.getParameter("signature");
}
if(request.getParameter("namefirst") != null){
	aszFirst=request.getParameter("namefirst");
}
if(request.getParameter("namelast") != null){
	aszLast=request.getParameter("namelast");
}
if(request.getParameter("addrpostalcode") != null){
	aszPostal=request.getParameter("addrpostalcode");
}
if(request.getParameter("addrcountryname") != null){
	aszCountry=request.getParameter("addrcountryname");
}
if(request.getParameter("addrstateprov") != null){
	aszState=request.getParameter("addrstateprov");
}
if(request.getParameter("addrcity") != null){
	aszCity=request.getParameter("addrcity");
}	
	
// fix the URL encoding on the timestamp, signature, uid (make sure any spaces are spaces, - are -, : are :, etc...
aszTimestamp = aszTimestamp.replace("%20", " ").replace("%2D", "-").replace("%3A", ":").replace("%3D", "=").replace("%5F", "_");
aszUID = aszUID.replace("%20", " ").replace("%2D", "-").replace("%3A", ":").replace("%3D", "=").replace("%5F", "_");
aszSignature = aszSignature.replace("%20", " ").replace("%2D", "-").replace("%3A", ":").replace("%3D", "=").replace("%5F", "_");
aszUsername = aszUsername.replace("%20", " ").replace("%2D", "-").replace("%3A", ":").replace("%3D", "=").replace("%5F", "_").replace("%u30c3", "");
	
// first, verify that the timestamp is within 240,000 milliseconds of the GMT timestamp now() - format YYYY-MM-DD HH:MM:SS
str_date=aszTimestamp;
today = new java.util.Date();
formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
try {    
    date = (Date)formatter.parse(str_date);
	socializeUnixTime = date.getTime();
	localUnixTime = today.getTime() + 18000000; // adjust for GMT comparison
    java.sql.Timestamp timeStampDate = new Timestamp(today.getTime());
	long diff=java.lang.Math.abs(localUnixTime - socializeUnixTime);
		    
	// causes issues for changes in Daylight Savings Time, b/c we are using an old jdk; the .jsp validation works, but this .java validation is an hour off
		    
	// test to see if within 5 min
	if(diff > 300000){ 
		// if greater than 5 minutes, then test to see if it's 55-65 minutes off; 
		//		this accounts for a possible hour adjustment where DST has not been updated to new North American schedule ~2009
		if( !(3300000 < diff && diff < 3900000) ){ // 55 min < diff < 65 min
			withinTimeLimit=false;
		}else{
			// timestamps are roughly 1 hour off, give or take 5 min.  for now, this counts as validated (DST)
			withinTimeLimit=true;
		}
	}else{
		// the timestamps are within 5 minutes; time suggested by gigya to validate against
		withinTimeLimit=true;
	}
}
catch (ParseException e){
}    
   
/**
 * calculate a local signature, using the private SecretKey set on our gigya socialize account
 * compare this local signature to the signature that was passed in the parameters in order to verify gigya socialize
 */
// baseString = timestamp + "_" + UID
String baseString = aszTimestamp + "_" + aszUID;
// convert your secretKey from its BASE64 encoding to a binary array; secret key can be retrieved in your gigya partner account
// binaryKey = ConvertFromBase64(secretKey);
try {
	BASE64Decoder decoder = new BASE64Decoder();
	binaryKey = decoder.decodeBuffer(secretKey);
} catch (Exception e) {
	e.printStackTrace();
}
try {
	// get an hmac_sha1 key from the raw key bytes
	SecretKeySpec signingKey = new SecretKeySpec(binaryKey, HMAC_SHA1_ALGORITHM);//.getBytes(), HMAC_SHA1_ALGORITHM);
		
	// get an hmac_sha1 Mac instance and initialize with the signing key
	Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	mac.init(signingKey);
		
	// compute the hmac on input data bytes
	// binarySignature = hmacsha1(binaryKey, baseString)
	byte[] binarySignature = mac.doFinal(baseString.getBytes());
	
	// base64-encode the hmac
	// mySignature = ConvertToBase64(binarySignature);
	try {
		BASE64Encoder encoder = new BASE64Encoder();
		mySignature = encoder.encodeBuffer(binarySignature);
	} catch (Exception e) {
		e.printStackTrace();
	}
} catch (Exception e) {
	throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
}
// trim the return carriage that for some reason is getting added to the end of mySignature
mySignature=mySignature.trim();

if(aszEmail.length() < 2){ aszEmail=""; }
if(aszUsername.length() < 2){ aszUsername=""; }
if(aszFirst.length() < 2){ aszFirst=""; }
if(aszLast.length() < 2){ aszLast=""; }
if(aszPostal.length() < 2){ aszPostal=""; }
if(aszCountry.length() < 2){ aszCountry=""; }
if(aszState.length() < 2){ aszState=""; }
if(aszCity.length() < 2){ aszCity=""; }

if(null != aCountryList){
	for(int index=0; index < aCountryList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
		if(null == aAppCode) continue;
		if(aAppCode.getCTRPrintableName().equalsIgnoreCase( aszCountry ) ) {
			aszCountry=aAppCode.getCTRIso();
		}
	}
}
if(null != aStateList){
	for(int index=0; index < aStateList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
		if(null == aAppCode) continue;
		if(aAppCode.getCSPStateName().equalsIgnoreCase( aszState ) ) {
			aszState=aAppCode.getCSPStateCode();
		}
	}
}
%>

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

<script language="javascript">

<% if( aszHostID.equalsIgnoreCase("volengworldvision") ){ %>
function newsletterPartner() {
	// test to see if the user has checked off that they want to subscribe to the partner newsletter
	//if(document.getElementByName(individualForm).elements["partnernewsletter"].checked){
	if(document.getElementById("partnernewsletter").checked){
		document.forms["newsLetterSignUp"].elements["email"].value = document.forms["individualForm"].elements["email1addr"].value;
		document.forms["newsLetterSignUp"].elements["NAME_FIRST"].value = document.forms["individualForm"].elements["namefirst"].value;
		document.forms["newsLetterSignUp"].elements["NAME_LAST"].value = document.forms["individualForm"].elements["namelast"].value;
		document.forms["newsLetterSignUp"].elements["txtADDR_1"].value = document.forms["individualForm"].elements["addrline1"].value;
		document.forms["newsLetterSignUp"].elements["txtADDR_CITY"].value = document.forms["individualForm"].elements["addrcity"].value;
		document.forms["newsLetterSignUp"].elements["txtADDR_ZIP"].value = document.forms["individualForm"].elements["addrpostalcode"].value;
		document.forms["newsLetterSignUp"].elements["cboADDR_STATE_CD"].value = document.forms["individualForm"].elements["addrstateprov"].value;
		document.forms["newsLetterSignUp"].elements["cboADDR_COUNTRY_NAME"].value = document.forms["individualForm"].elements["addrcountryname"].value;
		document.forms["newsLetterSignUp"].submit();
	}
}
<% } else if( aszHostID.equalsIgnoreCase("volengroundtrip") ){ %>
function newsletterPartner() {
	// test to see if the user has checked off that they want to subscribe to the partner newsletter
	if(document.getElementById("partnernewsletter").checked){
		document.forms["newsletterRoundTrip"].elements["email"].value = document.forms["individualForm"].elements["email1addr"].value;
		document.document.forms["newsletterRoundTrip"].submit();
	}
}
<% } %>
function newsletter() {
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if(document.getElementById("newsletter").checked){
		document.forms["newsletterUMusers"].elements["email"].value = document.forms["individualForm"].elements["email1addr"].value;
		document.forms["newsletterUMusers"].elements["groups"].value = document.forms["individualForm"].elements["newsletter"].value;
		document.forms["newsletterUMusers"].submit();
	}
} 
function new_account() {
		//document.forms["individualForm"].elements["agreeflag2"].value = document.forms["individualForm"].elements["agreeflag1"].value;
//	document.forms["individualForm"].submit();	
	document.forms["createForm"].submit();	
} 
function linkAccounts() {
//	document.forms["individualForm"].elements["method"].value = "processLogin";	
//	document.forms.individualForm.action='<%=aszPortal%>/register.do';
	//document.getElementById('smTag').style.display='none';
	//document.getElementById('userNm').style.display='none';
	//document.getElementById('passConfirm').style.display='none';
	//document.getElementById('firstName').style.display='none';
	//document.getElementById('lastName').style.display='none';
	//document.getElementById('siteUse').style.display='none';
	//document.getElementById('volunteerRow').style.display='none';
	//document.getElementById('splash2').style.display='none';
		//document.getElementById('newsletter').checked=false;
	document.getElementById('body').style.display='none';
	document.getElementById('processing').style.display='inline';
	document.getElementById('individualForm').style.display='none';
	document.getElementById('registerTitle').style.display='none';
	document.getElementById('submitButton').style.display='none';
	document.forms["individualForm"].elements["email1addr"].value = document.forms["loginForm"].elements["email1"].value;
	document.forms["individualForm"].elements["password1"].value = document.forms["loginForm"].elements["pass"].value;
//	document.forms["individualForm"].submit();
	document.forms["loginForm"].submit();	
} 
/*
function createAccount() {
	document.forms["individualForm"].elements["method"].value = "processRegistration";
	//document.forms.individualForm.action='<%=aszPortal%>/register.do?indivaccnt';
	document.getElementById('smTag').style.display='inline';
	document.getElementById('userNm').style.display='table-row';
	document.getElementById('passConfirm').style.display='table-row';
	document.getElementById('firstName').style.display='table-row';
	document.getElementById('lastName').style.display='table-row';
	document.getElementById('siteUse').style.display='table-row';
	document.getElementById('volunteerRow').style.display='table-row';
	document.getElementById('splash2').style.display='inline';
		document.getElementById('newsletter').checked=true;
} 
*/
function submitBoth() {
<%
/* if( (aszHostID.equalsIgnoreCase("volengworldvision") ) || (aszHostID.equalsIgnoreCase("volengroundtrip") ) ){ %>
	setTimeout("newsletterPartner()", 0);
<% } %>
	setTimeout("newsletter()", 0);
	
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if((document.getElementById("newsletter").checked) 
<% if( 
	(aszHostID.equalsIgnoreCase("volengworldvision") ) || 
	(aszHostID.equalsIgnoreCase("volengroundtrip") ) 
){ %>
		|| (document.getElementById("partnernewsletter").checked)
<% } %>
	){
		//document.getElementById('form1').style.display='none';
		//document.getElementById('processing').style.display='inline';
		
		setTimeout("new_account()", 10000);
	}else{ 
		new_account();
	}
<% */ %>
		new_account();
}

	function clicked_vol(){
//		document.getElementById('volun').style.display='inline';
//		document.getElementById('volunteertid').checked=true;
//		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
//		document.getElementById("newsletter").value="38";
	}
	function clicked_both(){
//		document.getElementById('volun').style.display='inline';
//		document.getElementById('volunteertid').checked=true;
//		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
//		document.getElementById("newsletter").value="35,38";
	}
	function clicked_org(){
//		document.getElementById('volun').style.display='none';
//		document.getElementById('volunteertid').checked=false;
//		document.getElementById('publicprofile').style.display='none';
//		document.getElementById('localvoltid').checked=false;
//		document.getElementById('volvirttid').checked=false;
		//document.getElementById('profilephoto').style.display='none';
//		document.getElementById("newsletter").value="35";
	}
	function clicked_public(box){
//		var vis = (box.checked) ? "block" : "none";
		//document.getElementById('publicprofile').style.display=vis;
	}

$(document).ready(function() {
	document.getElementById('body').style.display='inline-block';
	document.getElementById('processing').style.display='none';
//	$('input[name=newsletter]').attr('checked', true); 
<% if(
		(aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengroundtrip"))
){ %>
//	$('input[name=partnernewsletter]').attr('checked', true); 
<% } %>

	var siteUse = $('input:radio[name=voloropp]:checked').val();
	if (siteUse=="Volunteer"){
		clicked_vol();
	}else if (siteUse=="Both"){
		clicked_both();
	}else{
		clicked_org();
	}
 });  
                                

</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Social Network Account</span>
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
<span style="float: left;">Social Network Account</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; registration</div>
</div>
<% } %>
      	
          <div align="left">
	<div id="body" style="display:none;">
<% // for google analytics tracking: %>
	<%// String aszGoalPage = "/create/individual"; %>
	<%//@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
	<%@include file="/template_include/footer_google_analytics.inc"%>
<% // : end of for google analytics tracking %>
			<br><h3>Please complete the required fields below to create an account.</H3>

					<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>
<div align="center">

<div class="login_box">
<br><center><h3>Link your existing ChristianVolunteering.org account:</h3></center><br>
<form action="<%=aszPortal%>/register.do" focus="username" target="_self" id="loginForm" name="loginForm" method="post">
<input type="hidden" id="method" name="method" value="processLogin" />
<input type="hidden" id="subdomain" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="provider" id="provider" value="<%=aszProvider%>"  />
<input type="hidden" name="signature" id="signature" value="<%=aszSignature%>" >
<input type="hidden" name="timestamp" id="timestamp" value="<%=aszTimestamp%>" >
<input type="hidden" name="uid" id="uid" value="<%=aszUID%>" >
<input type="hidden" name="personinternalcomment" id="personinternalcomment" value="linkGigyaSocialize"  />
<input type="hidden" name="agreeflag2" id="agreeflag2" value="Yes" >
<input type="hidden" name="facebookuid" id="facebookuid" value="<%=aszFacebookUID%>" >

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

	<table width="97%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
	<!--table width="80%" border="0" cellpadding="0" cellspacing="0"-->
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="text" name="email1" id="email1" styleId="email" size="35" styleClass="textinputwhite" value="<%=aszEmail%>"></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ><b>Password</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="password" name="pass" id="pass" styleId="password" size="35" styleClass="textinputwhite" value=""></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ></TD>
       			<TD height="23"></TD>
		</tr>
		<tr>
        		<TD ></TD>
       			<TD height="23">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="loginButton" name="loginButton" onclick="linkAccounts()">Continue</button>
				</TD>
		</tr>
</table>
</form>
</div>

<div class="create_box">
<br><center><h3>or... Create a New Account</h3></center><br>
<form action="<%=aszPortal%>/register.do" focus="username" target="_self" id="createForm" name="createForm" method="post">
<input type="hidden" id="method" name="method" value="processCreateAccount1" />
<input type="hidden" id="subdomain" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="provider" id="provider" value="<%=aszProvider%>"  />
<input type="hidden" name="signature" id="signature" value="<%=aszSignature%>" >
<input type="hidden" name="timestamp" id="timestamp" value="<%=aszTimestamp%>" >
<input type="hidden" name="uid" id="uid" value="<%=aszUID%>" >
<input type="hidden" name="personinternalcomment" id="personinternalcomment" value="linkGigyaSocialize"  />
<input type="hidden" name="agreeflag2" id="agreeflag2" value="Yes" >
<input type="hidden" name="facebookuid" id="facebookuid" value="<%=aszFacebookUID%>" >

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

<table width="97%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
	<tr>
		<TD width=130><b>Username</b> <span class="criticaltext">*</span></TD>
		<TD height="23"><input type="text" id="username" name="username" styleId="username" size="30" styleClass="textinputwhite" value="<%=aszUsername%>"/></TD>
		<td width=75></td>
	</tr>
	<tr>
		<TD><b>Email</b> <span class="criticaltext">*</span></TD>
		<TD height="23"><input type="text" id="email1addr" name="email1addr" styleId="email1addr" size="30" styleClass="textinputwhite" value="<%=aszEmail%>"/></TD>
	</tr>
	<tr>
		<TD><b>New Password</b> <span class="criticaltext">*</span> </TD>
		<TD height="23" colspan="2">
			<input type="password" id="password1" name="password1" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
		<TD><b>Confirm Password</b> <span class="criticaltext">*</span> </TD>
		<TD height="23" colspan="2">
			<input type="password" id="password2" name="password2" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
        <TD><b>First name</b> <span class="criticaltext">*</span></TD>
        <TD  height="23">
        	<input type="text" id="namefirst" name="namefirst" styleId="namefirst" size="30" styleClass="textinputwhite" value="<%=aszFirst%>"/>
        </TD>
	</tr>
	<tr>
		<TD><b>Last name</b> <span class="criticaltext">*</span></TD>
		<TD  height="23">
			<input type="text" id="namelast" name="namelast" styleId="namelast" size="30" styleClass="textinputwhite" value="<%=aszLast%>"/>
		</TD>
	</tr>
	<tr>
		<td colspan=3>		
			<br>
			<b>Are you using this site as a volunteer, an organization, or both?</b> <span class="criticaltext">*</span><br>           	
			<input type="radio" styleClass="radio" value="Volunteer" id="voloropp" name="voloropp" onclick="clicked_vol()" /> Volunteer / Individual &nbsp;
			<input type="radio" styleClass="radio" value="Organization" id="voloropp" name="voloropp" onclick="clicked_org()" /> Organization &nbsp;
			<input type="radio" styleClass="radio" value="Both" id="voloropp" name="voloropp" onclick="clicked_both()" /> Both 
		</td>
	</tr>      	
	<tr>
		<td colspan=3>		
			<br>
			<b>Terms & Conditions</b><br>
			<table>
				<tr>
					<td>
						<input type="checkbox" value="Yes" id="agreeflag1" name="agreeflag1"/>
					</td>
					<td>
						I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and I am at least 16 years old or have my parent's permission to use this site.<span class="criticaltext">*</span>
					</td>
				</tr>
			<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
				<tr>
					<td>
						<input type="checkbox" styleClass="check" value="Yes" id="agreeflagworldvision" name="agreeflagworldvision"/>
					</td>
					<td>
						I agree to the World Vision <a href="http://www.worldvision.org/content.nsf/pages/privacy-policy">privacy policy</a>.<span class="criticaltext">*</span>
					</td>
				</tr>
			<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="submitButton" name="submitButton" onclick="submitBoth()">Continue</button>
		<!--INPUT class=submit type=submit value=Continue--></td>
	</tr>
	<tr>
		<td colspan=3>&nbsp;<span class="criticaltext">*</span>  Required Item</td>
	</tr>
</table>

</form>
</div>
	</div>  
<%
/*
	<table border="0" cellpadding="0" cellspacing="0" align="center" >
<tr><td valign="top" height=40 colspan=3>
<!--a href="#linkAccounts" onclick="linkAccounts()">Already a site user?</a-->
<h3>Link your existing ChristianVolunteering.org account:</h3>
</td><td width=20>
</td></tr>
<tr><td height=40 width=40>
</td><td width=20>
</td><td>

<form id="loginForm" name="loginForm">
	<table width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="text" name="email1" id="email1" styleId="email" size="35" styleClass="textinputwhite" value="<%=aszEmail%>"></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ><b>Password</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="password" name="pass" id="pass" styleId="password" size="35" styleClass="textinputwhite" value=""></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ></TD>
       			<TD height="23"></TD>
		</tr>
		<tr>
        		<TD ></TD>
       			<TD height="23">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="loginButton" name="loginButton" onclick="linkAccounts()">Continue</button>
				</TD>
		</tr>
</table>
</form>

</td></tr>
<tr><td height=5 colspan=2></td></tr>
<tr id="registerTitle"><td valign="top" height=30 colspan=3>
<!--a href="#createAccount" onclick="createAccount()">Register a new user</a-->
<h3>OR... Register a new user:</h3>
</td><td width=20>	

</td></tr>
<tr><td height=40 width=40>
</td><td width=20>
</td><td>
<div id="form1" align="center">
<form action="<%=aszPortal%>/register.do?indivaccnt" method="post" focus="username" target="_self" name="individualForm" id="individualForm">
<input type="hidden" name="method" id="method" value="processRegistration" >
<input type="hidden" name="subdomain" id="subdomain" value="<%=aszSubdomain%>" >
<input type="hidden" name="provider" id="provider" value="<%=aszProvider%>"  />
<input type="hidden" name="signature" id="signature" value="<%=aszSignature%>" >
<input type="hidden" name="timestamp" id="timestamp" value="<%=aszTimestamp%>" >
<input type="hidden" name="uid" id="uid" value="<%=aszUID%>" >
<input type="hidden" name="personinternalcomment" id="personinternalcomment" value="linkGigyaSocialize"  />
<input type="hidden" name="agreeflag2" id="agreeflag2" value="Yes" >

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

            <!--nested table -->
	<table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
              <!-- MSTableType="layout" -->
				
		<tr id="userNm">
                <TD width=130><b>Username</b> <span class="criticaltext">*</span></TD>
                <TD><input type="text" name="username" id="username" styleId="username" size="30" styleClass="textinputwhite" value="<%=aszUsername%>"></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="text" name="email1addr" id="email1addr" styleId="email1addr" size="35" styleClass="textinputwhite" value="<%=aszEmail%>"></TD>
		</tr>
		<tr>
                <TD ><b><div id="smTag">New </div>Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<input type="password" name="password1" id="password1" size="25" styleClass="textinputwhite" redisplay="false" onClick="javascript:urchinTracker('/create/individual');">
                </TD>
		</tr>
		<tr id="passConfirm">
                <TD width=130><b>Confirm Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<input type="password" name="password2" id="password2" size="25" styleClass="textinputwhite" redisplay="false" >
                </TD>
		</tr>
		<tr id="firstName">
                <TD ><b>First name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text" name="namefirst" id="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite" value="<%=aszFirst%>"></TD>
		</tr>
		<tr id="lastName">
                <TD ><b>Last name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text" name="namelast" id="namelast" styleId="namelast" size="25" styleClass="textinputwhite" value="<%=aszLast%>"></TD>
		</tr>
              	
<tr id="siteUse"><td colspan=3>				
				
<br>
<b>Are you using this site as a potential volunteer, an organization, or both?</b> <span class="criticaltext">*</span><br>           	
<input type="radio" styleClass="radio" value="Volunteer" name="voloropp" id="voloropp" onclick="clicked_vol()" > Volunteer &nbsp;
<input type="radio" styleClass="radio" value="Organization" name="voloropp" id="voloropp" onclick="clicked_org()" > Organization &nbsp;
<input type="radio" styleClass="radio" value="Both" name="voloropp" id="voloropp" onclick="clicked_both()" > Both 
            	
<br><br>
</td></tr>

<!--tr id="volunteerRow"><td colspan=3-->				
<tr id="volunteerRow"><td colspan=3>				
<div id="volun" style="display: none;">

<table border=0 cellpadding=2 cellspacing=2>        	
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" onclick="clicked_public(this)"></td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
			</tr>
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" onclick="clicked_public(this)" /></td>
				<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("voelngivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile in the following areas (...coming soon):</td>
			</tr>

</table>
<br>


<div id="publicprofile" style="<%=aszPublic%>">
<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="better-select">
		<label>I am Looking for: <span class="criticaltext">*</span> </label>
		<div class="form-checkboxes form-checkboxes-scroll">
<table ><tr><td>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszLocalVolTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszLocalVolTID%>">
					<input id="localvoltid" name="localvoltid" type="checkbox" value="<%=aszLocalVolTID%>" />
					Local Volunteer Opportunities
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszGroupFamilyTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszGroupFamilyTID%>">
					<input id="groupfamilytid" name="groupfamilytid" type="checkbox" value="<%=aszGroupFamilyTID%>" />
					Group/Family Volunteer Opportunities
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszVolBoardTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszVolBoardTID%>">
					<input id="volboardtid" name="volboardtid" type="checkbox" value="<%=aszVolBoardTID%>" />
					Volunteer on a Nonprofit Board
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszVolVirtTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszVolVirtTID%>">
					<input id="volvirttid" name="volvirttid" type="checkbox" value="<%=aszVolVirtTID%>" />
					Virtual Volunteering (Remotely from Home)
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszIntrnTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszIntrnTID%>">
					<input id="intrntid" name="intrntid" type="checkbox" value="<%=aszIntrnTID%>" />
					Ministry Internship (year or longer)
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszSumIntrnTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszSumIntrnTID%>">
					<input id="sumintrntid" name="sumintrntid" type="checkbox" value="<%=aszSumIntrnTID%>" />
					Summer Internship
				</label>
			</div>
		</td><td>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszWorkStudyTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszWorkStudyTID%>">
					<input id="workstudytid" name="workstudytid" type="checkbox" value="<%=aszWorkStudyTID%>" />
					Work Study Opportunities
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszJobsTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszJobsTID%>">
					<input id="jobstid" name="jobstid" type="checkbox" value="<%=aszJobsTID%>" />
					Jobs in Urban Ministry
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszConferenceTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszConferenceTID%>">
					<input id="conferencetid" name="conferencetid" type="checkbox" value="<%=aszConferenceTID%>" />
					Opportunities to Speak at Conferences
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszConsultingTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszConsultingTID%>">
					<input id="consultingtid" name="consultingtid" type="checkbox" value="<%=aszConsultingTID%>" />
					Consulting Opportunities
				</label>
			</div>
			<div id="edit-taxonomy-<%=aszLookingForVID%>-<%=aszSocJustGrpsTID%>-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-<%=aszLookingForVID%>-<%=aszSocJustGrpsTID%>">
					<input id="socjustgrpstid" name="socjustgrpstid" type="checkbox" value="<%=aszSocJustGrpsTID%>" />
					Local Social Justice Groups and Christians
				</label>
			</div>
			<div id="edit-taxonomy-0-0-wrapper" class="form-item">
				<label class="option" for="edit-taxonomy-0-0">
					<input id="socjustgrpstid" name="socjustgrpstid" type="hidden" value="" />
					&nbsp;
				</label>
			</div>
</td></tr></table>
		</div>
</div>
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
                <TD ><input type="text" name="phone1" id="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr>
        <TD>Street</TD>
        <TD height="23"><input type="text" name="addrline1" id="addrline1" styleId="addrline1" size="25" styleClass="textinputwhite"/></TD>
		</tr>

		<tr>
        <TD>City</TD>
        <TD height="23"><input type="text" name="addrcity" id="addrcity" styleId="addrcity" size="25" styleClass="textinputwhite" value="<%=aszCity%>"/></TD>
		</tr>
		
		<tr>
<TD>State/Province</TD>
<TD height="23">
                <SELECT id="addrstateprov" name="addrstateprov" class="smalldropdown"> 
					<option value=""></option>
					<%
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
                <TD colspan=2 height="23"><input type="text" name="addrpostalcode" id="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD><b>Country</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=4>
                	<SELECT id="addrcountryname" name="addrcountryname" class="smalldropdown" > 
					<option value=""></option>
					<%
					if(null != aCountryList){
					aszTemp=aszCountry;
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
</table>
</div>
<br>
</div>

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
	<tr>
    <TD colspan=2>Personal Skills </TD>
    <TD colspan=2 height="30">
		    <select class="smalldropdown" id="volskills1tid" name="volskills1tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('skills2').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if (iSubType == 4745){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Musician</option> ");
							}else if (iSubType == 8122){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Deaf Services</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iSubType == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					} 
				%>
			</select>
    </TD>
	</tr>
		<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="skills2"  style="display: none;">
		    <select class="smalldropdown" id="volskills2tid" name="volskills2tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('skills3').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if (iSubType == 4745){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Musician</option> ");
							}else if (iSubType == 8122){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Deaf Services</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iSubType == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					} 
				%>
			</select>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="skills3"  style="display: none;">
		    <select class="smalldropdown" id="volskills3tid" name="volskills3tid" style="margin-top: 5px;">
               <option value=""></option>
               <%
					iTemp = 0;
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
							for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if (iSubType == 4745){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Musician</option> ");
							}else if (iSubType == 8122){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >Deaf Services</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iSubType == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					} 
				%>
			</select>
		</div>
		</td>
	</tr>
				

	<tr>
    <TD colspan=2>Volunteer Interest Area </TD>
    <TD colspan=2 height="30">
		    <select class="smalldropdown" id="volinterestarea1tid" name="volinterestarea1tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('interestarea2').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
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
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
							}else if (iSubType == 6843){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Senior / Elderly Outreach</option> ");
							}else if (iSubType == 4774){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Food Service / Hunger</option> ");
							}else if (iSubType == 4782){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Prison Outreach</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
				} else {
					for(int index=0; index < ainterestareaList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iSubType == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				} 
			%>
			</select>
    </TD>
	</tr>
		<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="interestarea2"  style="display: none;">
		    <select class="smalldropdown" id="volinterestarea2tid" name="volinterestarea2tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('interestarea3').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
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
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
							}else if (iSubType == 6843){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Senior / Elderly Outreach</option> ");
							}else if (iSubType == 4774){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Food Service / Hunger</option> ");
							}else if (iSubType == 4782){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Prison Outreach</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
				} else {
					for(int index=0; index < ainterestareaList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iSubType == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				} 
			%>
			</select>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="interestarea3"  style="display: none;">
		    <select class="smalldropdown" id="volinterestarea3tid" name="volinterestarea3tid" style="margin-top: 5px;">
               <option value=""></option>
               <%
					iTemp = 0;
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
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
							}else if (iSubType == 6843){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Senior / Elderly Outreach</option> ");
							}else if (iSubType == 4774){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Food Service / Hunger</option> ");
							}else if (iSubType == 4782){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >Prison Outreach</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iSubType == iTemp) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}
				} else {
					for(int index=0; index < ainterestareaList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iSubType == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				} 
			%>
			</select>
		</div>
		</td>
	</tr>
				
	<tr>
    <TD colspan=2>Languages Spoken </TD>
    <TD colspan=2 height="30">
		    <select class="smalldropdown" id="vollang1tid" name="vollang1tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('lang2').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
					for(int index=0; index < alangList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)alangList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
    </TD>
	</tr>
		<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="lang2"  style="display: none;">
		    <select class="smalldropdown" id="vollang2tid" name="vollang2tid" style="margin-top: 5px;" onchange="javascript:document.getElementById('lang3').style.display='inline';">
               <option value=""></option>
               <%
					iTemp = 0;
					for(int index=0; index < alangList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)alangList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan=2></td>
		<td colspan=2>
		<div id="lang3"  style="display: none;">
		    <select class="smalldropdown" id="vollang3tid" name="vollang3tid" style="margin-top: 5px;">
               <option value=""></option>
               <%
					iTemp = 0;
					for(int index=0; index < alangList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)alangList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
		</div>
		</td>
	</tr>
	<tr>
        <TD colspan=2 valign="top">
Personal Volunteer<br>Resum&egrave;<br>(PUBLICLY VISIBLE)<br><A href="javascript: alert('Enter your personal resum&egrave; in the box to the right. When you indicate interest in a volunteer opportunity, we will e-mail your resum&egrave to the organization for you. Organizations will also be able to search resum&egrave;s to find the volunteers that they are looking for.')">[What's This?]</A>
		</TD>
        <TD height="38" colspan="2">
			<textarea name="volresume" cols="70" rows="20"></textarea>
    	</TD>
	</tr>

<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

	<tr><td colspan=4 height="10"></td></tr>

			<tr><td colspan=2>
					Are You a Christian?</td><td>
					<input type="radio" styleClass="check" value="Yes" name="indivchristian" id="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<input type="radio" styleClass="check" value="No" name="indivchristian" id="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
		<tr><td colspan=3>		
		<div id="faith"  style="display: none;">
		<br>
			<table width="519" border="0" cellpadding="0" cellspacing="0" id="splash">
			<tr>
				<td height="30" colspan=2>Do You Attend Church Regularly?</td>
				<td>
						<input type="radio" styleClass="check" value="Yes" name="attendchurch" id="attendchurch"/> Yes
						&nbsp; &nbsp; 
						<input type="radio" styleClass="check" value="No" name="attendchurch" id="attendchurch"/> No
				</td>
			</tr>
			<tr>
				<td height="30">Denominational Affiliation</td>
				<td colspan=2>
						<select id="indivdenomaffiltid" name="indivdenomaffiltid" class="smalldropdown">
							<option value=""></option>
							<%
								iTemp = 0;
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
				iTemp = 0;
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
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 			
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
		</table>
	</div>
</td></tr>

<% } %>
</table></div></td></tr>

<tr><td colspan=3>
<table width="540" border="0" cellpadding="0" cellspacing="0" id="splash2" >
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="38" id="newsletter" name="newsletter"/>
		</td><td colspan=3>
	        Subscribe me to the ChristianVolunteering.org newsletter
        </TD>
    </tr>
<% if(aszHostID.equalsIgnoreCase("volengworldvision")) { %>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="worldvision" id="partnernewsletter" name="partnernewsletter"/>
		</td><td colspan=3>
	        Subscribe me to the World Vision newsletter
        </TD>
     </tr>
<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")) { %>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="roundtrip" id="partnernewsletter" name="partnernewsletter"/>
		</td><td colspan=3>
	        Subscribe me to Christianity Today International's Round Trip newsletter.
            By checking, you will sign up for the free Round Trip Missions newsletter--helping church leaders make the most of short-term missions.
        </TD>
    </tr>
<% } %>

		<tr>
			<TD height="20" colspan=4><b>Terms & Conditions</b></TD>
        </tr>
		<tr>     
	        <TD height="30" >
			<input type="checkbox" styleClass="check" value="Yes" name="agreeflag1" id="agreeflag1"/>
			</td><td colspan=3>
	        I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and am at least 16 years old or have my parent's permission to use 
	        	<a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a>.<span class="criticaltext">*</span>
	        </TD>
        </tr>

<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
		<tr>
			<TD height="30">
				<input type="checkbox" styleClass="check" value="Yes" id="agreeflagworldvision" name="agreeflagworldvision"/>
			</td><td colspan=3>
				I agree to the World Vision <a href="http://www.worldvision.org/content.nsf/pages/privacy-policy">privacy policy</a>.<span class="criticaltext">*</span>
			</TD>
		</tr>
<% } %>
<NOSCRIPT>
<tr>
                        <td width="40">&nbsp;</td>
                        <td width="90">&nbsp;</td>
                <TD height="75">
                  <DIV class=clear style="HEIGHT: 10px"></DIV>
                  <INPUT type=hidden name=submit> <INPUT class=submit type=submit value=Continue>
                 </TD>
                        </tr>

</NOSCRIPT>
				<tr>
                	<TD colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td>
              		<td height="30"></td>
				</tr>

            </TABLE>
</td></tr>
</table>

</form>




</div>

<button type="button" id="submitButton" onclick="submitBoth()">Continue</button>



</td></tr></table>
<% */ %>


<div id="newsletterForms" style="display:none;">

<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>

<form name="newsLetterSignUp" target="_blank" action="https://webi1.worldvision.org/worldvision/BI_Global/Signup.aspx" method="get" style="margin:0;padding:0;" onsubmit="window.open(this.action,'nlsu','scrolling=0,toolbar=0,width=400,height=400'); return false;" > 
<input type="text" name="email" id="search2" value="e-mail address" onfocus="if(this.value=='e-mail address'){this.value='';}" onblur="if(this.value==''){this.value='e-mail address';}" style="height:15px; font-size:11px;"/> 
	<input type="text" name="NAME_FIRST">
	<input type="text" name="NAME_LAST">
	<input type="text" name="txtADDR_1">
	<input type="text" name="txtADDR_CITY">
	<input type="text" name="txtADDR_ZIP">
	<input type="text" name="cboADDR_STATE_CD">
	<input type="text" name="cboADDR_COUNTRY_NAME">
<input type="image" src="<%=aszPortal%>/imgs/blank.gif" /> 
</form>

<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>

<form name="newsletterRoundTrip" action="http://lists.christianitytoday.com/subscribe/subscribe.tml" method="POST" target="_blank">
<input type="text" class="text" name="email" value="your e-mail address" onFocus="this.value='';">
<input type="hidden" name="list" value="roundtripmissions">
<input type="hidden" name="confirm" value="one"><input type="hidden" name="showconfirm" value="F">
<input type="hidden" name="url" value="https://w1.buysub.com/servlet/OrdersGateway?cds_mag_code=TDY&cds_page_id=45824">
<input type="image" name="imageField" src="<%=aszPortal%>/imgs/blank.gif" width="15" height="17" title="Go" alt="Go" style="vertical-align: middle;">
</form>

<% } %>

<form id="civicrm-subscribe-form" name="newsletterUMusers" target="ifr" action="http://www.christianvolunteering.org/cms/user/subscribe?destination=cms/subscribe/newsletters" method="post">
    UrbanMinistry Group: <input type="text" name="groups" id="edit-groups" value="group#">
    <br />
    <label for="edit-email" class="email">Email: </label> 
    <input type="text" class="form-text required" id="edit-email" maxlength="100" name="email" />  
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" />   
    <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" />  
</form>


</div>


<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>

<br>
<b>PLEASE NOTE:</b> Creating an account on <a href="http://<%=aszSubdomainShort%>"><%=aszSubdomainShort%></a> will also create an 
account for you on <a href="http://www.urbanministry.org">UrbanMinistry.org</a>
<% if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
			<p>This section of the RoundTripMissions site uses a separate account on our partner site ChristianVolunteering.org, which requires a separate login. An account is required either to connect with any service opportunity or to post any service opportunity.</p>
<% } else { %>
			<p>If you are the contact for an organization, fill out this form. 
			After clicking &quot;continue&quot; you will be brought to your personal 
			account summary where you can add your organization's information and volunteer opportunities.</p>
<% } %>


<div id="processing" style="display:none;">
<center>
<h2>Please wait while we process your registration... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/.gif"/>
</center>
</div>


      </div>

<div id="processingLogin" style="display:none;">	
 <br><br>
<center>
<h2>Please wait while we log you in to our system... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
</div>
	  </div></div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
