<%@ include file="/template_include/topjspnologin1.inc" %>

<% if( 	aszMobileSubdomain.length() > 3 ) { %>
        <jsp:include page="/create_account1.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } else { %>
<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<%
String aszMethod = "";
try{
	if(request.getParameter("method") != null){
		if(request.getParameter("method").length()>0){
			aszMethod = request.getParameter("method");
		}
	}
}catch(Exception e){
	aszMethod = "";
}
String aszRedirect = "", aszRedirectURL="";
int iIndexSuffix = 0;
try{
	if(request.getParameter("redirect") !=null){
		aszRedirect=request.getParameter("redirect").toString();
	}else{
		if(aszReferer != null){
			if(aszReferer.length()>0){
				//out.print("referer is "+aszReferer);
				if(aszReferer.contains("/orgs/")){
					aszRedirect=aszReferer.substring(aszReferer.indexOf("/orgs"));
					iIndexSuffix = aszRedirect.indexOf(".jsp");
					aszRedirectURL="&redirect="+aszRedirect.substring(0, iIndexSuffix);
				}else if(aszReferer.contains("/org/")){
					aszRedirect=aszReferer.substring(aszReferer.indexOf("/org"));
					iIndexSuffix = aszRedirect.indexOf(".jsp");
					aszRedirectURL="&redirect="+aszRedirect.substring(0, iIndexSuffix);
				}
			}
		}
	}
}catch(NullPointerException e){
}catch(IndexOutOfBoundsException ex){
//	out.println("indexoutofbounds");
}
//out.print(aszRedirect);
if(aszRedirect.endsWith("-apply")){
//	aszRedirectURL="&redirect="+aszRedirect;
}

//out.println("<br />aszRedirect is "+aszRedirect);
//out.println("<br />iIndexSuffix is "+iIndexSuffix);
//out.println("<br />aszRedirectURL is "+aszRedirectURL);

boolean bFacebookLogin = false;
if(aszMethod.equals("processFacebookConnectClick"))	bFacebookLogin=true;

String aszSource = "";
try{
	if(request.getParameter("source") != null){
		if(request.getParameter("source").length()>0){
			aszSource = request.getParameter("source");
		}
	}
}catch(Exception e){
	aszSource = "";
}

boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") || aszSource.equals("churchvolunteer") ){
	bFaith=true;
}

String aszEmailAddress = "";
if(userprofile.getUSPEmail1Addr().length()>0){
	aszEmailAddress = userprofile.getUSPEmail1Addr();
}else{
	try{
		if(request.getParameter("email1addr") != null){
			if(request.getParameter("email1addr").length()>0){
				aszEmailAddress = request.getParameter("email1addr");
			}
		}
	}catch(Exception e){
		aszEmailAddress = userprofile.getUSPEmail1Addr();
	}
}

String aszUsername = "";
if(userprofile.getUSPUsername().length()>0){
	aszUsername = userprofile.getUSPUsername();
}else{
	try{
		if(request.getParameter("username") != null){
			if(request.getParameter("username").length()>0){
				aszUsername = request.getParameter("username");
			}
		}
	}catch(Exception e){
		aszUsername = userprofile.getUSPUsername();
	}
}

boolean narrow=false; 
boolean mobile=false; 
// test to see if it's from a URL that has a narrower template
if(
	aszNarrowTheme.equalsIgnoreCase("true") ||
	aszNarrowTheme.equalsIgnoreCase("true") 
){
	narrow=true;
}

if( 	aszMobileSubdomain.length() > 3 ) { 
	narrow=true;
	mobile=true;
}

%>
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
ul.columns {float:left; width:auto;}
ul.columns li {padding-left:0px; //list-style: square;}
ul.columns li a{ color:#003366; text-decoration:none;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
.profile_box{
<% if(mobile==false){ %>
	width: 375px;
<% } %>
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
	padding:5px;
	margin-left: 150px;
	float:left;
    font-family: arial,helvitica,sans-serif;
<% if(narrow==true){ %>
	margin: 0 10px 10px 60px;
	height:390px; 
<% }else if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	height:390px; 
<% }else{ %>
	height:370px; 
<% } %>
.profile_box a, .fb_box a{ color:#003366; text-decoration:none; line-height:2em;}
.profile_box a:hover, .fb_box a:hover {text-decoration:underline;}
.fb_box {
<% if(mobile==false){ %>
	width: 281px;
<% } %>
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
        font-family: arial,helvitica,sans-serif;
<% if(narrow==true){ %>
	height:390px; 
<% }else if( aszHostID.equalsIgnoreCase("volengworldvision") ){ %>
	height:440px; 
<% } else if( aszHostID.equalsIgnoreCase("volengroundtrip") ){ %>
	height:450px; 
	font-family: arial,helvitica,sans-serif;
<% }else if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	height:390px; 
<% }else{ %>
	height:370px; 
<% } %>
	padding:5px;
<% if(narrow==true){ %>
	margin: 0 10px 10px 100px;
<% }else{ %>
	margin: 0 10px 10px 10px;
<% } %>
	align: right;
	//height:390px; float:left;
}
.fb_box h2 {text-align:center;}
.fb_box ol {width:210px;}
#fb-login-li{
	padding:10px;
	margin-left:30px;
}
#fb-login-li a{line-height:1.3em;}

h3{margin:0px;}

a.add-new-buttn { 
background-color:#83A2F4;
border:1px solid #4D73CF;
color:#FFFFFF;
font-weight:bold;
padding:3px;
width:140px;
text-decoration:none;
}
a.add-new-buttn:hover {
background-color:#4D73CF;
text-decoration:none;
color:#FFF;
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

<script language="javascript">
function new_account() {
	document.forms["individualForm"].submit();	
} 

</script>


<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
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
String aszLocalOrgsTID="" + iLocalOrgsTID;
// end I am looking for... options

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

String aszTermsChecked="", aszOrgsiteusetype="", aszChurchsiteusetype="", aszVolunsiteusetype="";
if(userprofile.getUSPSiteUseType().contains("Volunteer") ){
	aszVolunsiteusetype="checked=checked";
}else if(userprofile.getUSPSiteUseType().equals("Organization") ){
	aszOrgsiteusetype="checked=checked";
}else if(userprofile.getUSPSiteUseType().equals("Church") ){
	aszChurchsiteusetype="checked=checked";
}

if(userprofile.getUSPAgree1Fg().equalsIgnoreCase("Yes")){
	aszTermsChecked="checked=checked";
}
%>


<script language="javascript">

	function clicked_vol(){
		document.getElementById('termsConditions').style.display='inline';
		document.getElementById('termsConditionsChurch').style.display='none';
		document.getElementById('termsConditionsOrg').style.display='none';
			
		document.getElementById('church_name').style.display='none';
		document.getElementById('user_name').style.display='inline';
	}
	function clicked_org(){
		document.getElementById('termsConditions').style.display='none';
		document.getElementById('termsConditionsOrg').style.display='inline';
		document.getElementById('termsConditionsChurch').style.display='none';
	}
	function clicked_church(){
		document.getElementById('termsConditions').style.display='none';
		document.getElementById('termsConditionsChurch').style.display='inline';
		document.getElementById('termsConditionsOrg').style.display='none';
		document.getElementById('church_name').style.display='inline';
		document.getElementById('user_name').style.display='none';
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
	}

$(document).ready(function() {


<% if(bFacebookLogin==true){ %>
	processFacebookCookie();
<% } %>




<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	$('input[name=newsletter]').attr('checked', true); 
<% } %>
	var siteUse = $('input:radio[name=siteusetype]:checked').val();
	if (siteUse=="Volunteer"){
		clicked_vol();
	}else if (siteUse=="Church"){
		clicked_church();
		document.getElementById('church_name').style.display='inline';
		document.getElementById('user_name').style.display='none';
	}else if (siteUse=="Organization"){
		clicked_org();
	}else{
	<%  if(bFaith==true && 
		(
			aszPortal.length()<1 || aszPortal.equals("/voleng")
		) 
		&& (!( userprofile.getUSPSiteUseType().contains("Volunteer") ))
	){ 
	%>
		clicked_church();
		$('input:radio[name=siteusetype]').filter('[value=Church]').attr('checked', true);
	<% }else{ %>
		clicked_vol();
		$('input:radio[name=siteusetype]').filter('[value=Volunteer]').attr('checked', true);
	<% } %>
	}
 });  



<% String aszEmail=""; String aszUID=""; %>
<% if(bFacebookLogin==true){ %>
var fbuid;
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function processFacebookCookie(){
//var facebook_cookie = readCookie('fbs_<%=tempappid%>');
var facebook_cookie = readCookie('fbsr_<%=tempappid%>');
 if(facebook_cookie){
 	//alert(facebook_cookie);
	parse_facebook_cookie(facebook_cookie);
	fillFieldsFromFacebook();
	//alert('fbuid: ' + fbuid);
	if(fbuid != null){
		document.forms["loginForm"].elements["facebookuid"].value = fbuid;
		document.forms["createForm"].elements["facebookuid"].value = fbuid;
	}
 }
 }
 
 function parse_facebook_cookie(cookie){
 	var access_token, session_key, expires, sig;
	
	var tokenStr, sessionStr, uidStr, expiresStr, sigStr;
	tokenStr = 'access_token=';
	sessionStr = 'session_key=';
	sigStr = 'sig=';
	uidStr = 'uid=';
	expiresStr = 'expires=';
	
	//get access_token
	var temp = cookie.split('&');
	for(var i=0; i <temp.length; i++){
		var c = temp[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(tokenStr)==0) access_token = c.substring(tokenStr.length, c.length);
		if (c.indexOf(sessionStr)==0) session_key = c.substring(sessionStr.length, c.length);
		if (c.indexOf(sigStr)==0) sig = c.substring(sigStr.length, c.length);
		if (c.indexOf(uidStr)==0) fbuid = c.substring(uidStr.length, c.length-1);
		if (c.indexOf(expiresStr)==0) expires = c.substring(expiresStr.length, c.length);
	}
	//alert(tokenStr + access_token);
	//alert(sessionStr + session_key);
	//alert(sigStr + sig);
	//alert(uidStr + fbuid);
	//alert(expiresStr + expires);
 }
 
 function fillFieldsFromFacebook(){
 var userEmail, first_name, last_name;
 	FB.api('/me', function(response) {
		////alert(response.name);
		userEmail = response.email;
		first_name = response.first_name;
		last_name = response.last_name;
		
		//alert(userEmail);
		//alert(first_name);
		//alert(last_name);
		
		if(userEmail!=null){
			document.forms["loginForm"].elements["email1addr"].value = userEmail;
			document.forms["createForm"].elements["email1addr"].value = userEmail;
		}
		if(first_name!=null)
			document.forms["createForm"].elements["namefirst"].value = first_name;
		if(last_name!=null)
			document.forms["createForm"].elements["namelast"].value = last_name;
		});
 }
 
<% } %>
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

	<div id="body" class="wide">
<% // for google analytics tracking: %>
	<% String aszGoalPage = "/create/individual"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>
<div id="containerdiv">

<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif">
<bean:write name="individualForm" property="errormsg" /></pre>
</FONT>
 <div style="clear:both;"></div>
<h2 style="text-align:center">Create Account</h2>
<div style="text-align:center"><a href="<%=request.getContextPath()%>/register.do?method=authorizeLinkedinConnectClick<%=aszRedirectURL%>"><img src="http://www.christianvolunteering.org/imgs/linkedin_login_button.png" /></a></div>
<div style="text-align:center;font-weight:bold;padding:5px;">OR</div>
<div style="text-align:center"><a href="<%=request.getContextPath()%>/register.do?method=authorizeFacebookConnectClick<%=aszRedirectURL%>"><img src="http://www.christianvolunteering.org/imgs/facebook_login_button.png" /></a></div>
<div style="text-align:center;font-weight:bold;padding:5px;">OR</div>
<h2 style="text-align:center"><a href="#" onclick="$('#long_create').show();">Create Account without LinkedIn or Facebook</a></h2>
<span id="long_create">
<div class="profile_box" style="margin-left:auto;margin-right:auto;float:none;width:400px;height:342px;">

<form name="individualForm" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processCreateAccount1<%}%>" focus="username" target="_self" method="post">
<input type="hidden" name="method" value="processCreateAccount1" />
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="personinternalcomment" value="<%=userprofile.getUSPInternalComment()%>"/>
<input type="hidden" name="redirect" value="<%=aszRedirect%>"/>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>


<table width="97%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
	<tr>
		<td colspan=3>		
	<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
			<b>Are you using this site as a volunteer/individual or an organization?</b> <span class="criticaltext">*</span><br>           	
	<% }else{ %>
		<% if(aszSecondaryHost.equalsIgnoreCase("volengurbmin")){ %>
				<b>Are you using this site as an individual or an organization?</b> <span class="criticaltext">*</span><br>           	
		<% }else{ %>
				<b>Are you using this site as a volunteer, organization, or church?</b> <span class="criticaltext">*</span><br>           	
		<% } %>
	<% } %>
			<input type="radio" styleClass="radio" value="Volunteer" name="siteusetype" id="siteusetype" onclick="clicked_vol()" "<%=aszVolunsiteusetype%>" /> Volunteer / Individual &nbsp;
		<% if(! aszHostID.equalsIgnoreCase("volengchurch")){ %>
			<input type="radio" styleClass="radio" value="Organization" name="siteusetype" id="siteusetype" onclick="clicked_org()"  "<%=aszOrgsiteusetype%>" /> <%=aszOrgOrChurch%> &nbsp;
		<% } %>
	<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
	<% }else{ %>
			<input type="radio" styleClass="radio" value="Church" name="siteusetype" id="siteusetype" onclick="clicked_church()" "<%=aszChurchsiteusetype%>" /> Church 
	<% } %>
		</td>
	</tr>      	
	<tr><td height=10></td></tr>

	<tr>
		<TD width=130>
			<span id="church_name" style="display:none; font-weight:bold;">Church Name</span>
			<span id="user_name" style="font-weight:bold;">Username</span> 
			<span class="criticaltext">*</span>
		</TD>
		<TD height="23"><input type="text" name="username" id="username" styleId="username" size="30" styleClass="textinputwhite" value="<%=aszUsername%>"/></TD>
		<td width=75></td>
	</tr>

	<tr>
		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
		<TD height="23"><input type="text" name="email1addr" id="email1addr" styleId="email1addr" size="30" styleClass="textinputwhite" value="<%=aszEmailAddress%>"/></TD>
	</tr>
	<tr>
		<TD ><b>New Password</b> <span class="criticaltext">*</span> </TD>
		<TD height="23" colspan="2">
			<input type="password" name="password1" id="password1" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
		<TD><b>Confirm Password</b> <span class="criticaltext">*</span> </TD>
		<TD height="23" colspan="2">
			<input type="password" name="password2" id="password2" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
        <TD ><b>First name</b> <span class="criticaltext">*</span></TD>
        <TD  height="23">
        	<input type="text" name="namefirst" id="namefirst" styleId="namefirst" size="30" styleClass="textinputwhite" value="<%=userprofile.getUSPNameFirst()%>"/>
        </TD>
	</tr>
	<tr>
		<TD ><b>Last name</b> <span class="criticaltext">*</span></TD>
		<TD  height="23">
			<input type="text" name="namelast" id="namelast" styleId="namelast" size="30" styleClass="textinputwhite" value="<%=userprofile.getUSPNameLast()%>"/>
		</TD>
	</tr>
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<tr><td height=10></td></tr>
	<tr><TD colspan=3>
		<b>Newsletters</b><br>
		<table>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="2" id="newsletter" name="newsletter"/>
		</td><td colspan=3>
	        Subscribe me to the ChristianVolunteering.org newsletter
        </TD>
    </tr>
</table></td></tr>
<% } %>
	<tr>
		<td colspan=3>		
			<b>Terms & Conditions</b><br>
			<table>
				<tr>
					<td>
						<input type="checkbox" styleClass="check" value="Yes" name="agreeflag1" id="agreeflag1" "<%=aszTermsChecked%>" />
					</td>
					<td>
<div id="termsConditions">
						I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and I am at least 16 years old or have my parent's permission to use this site.<span class="criticaltext">*</span>
</div>
<div id="termsConditionsOrg" style="display:none;">						
						I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and I am involved in coordinating volunteers for this organization.<span class="criticaltext">*</span>
</div>
<div id="termsConditionsChurch" style="display:none;">						
						I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a> and I am involved in coordinating volunteers for this church.<span class="criticaltext">*</span>
</div>
					</td>
				</tr>
			<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
				<tr>
					<td>
						<input type="checkbox" styleClass="check" value="Yes" name="agreeflagworldvision"/>
					</td>
					<td>
						I agree to the World Vision <a href="http://www.worldvision.org/content.nsf/pages/privacy-policy">privacy policy</a>.<span class="criticaltext">*</span>
					</td>
				</tr>
			<% } %>
			</table>
		</td>
	</tr>
</table>
<div style="padding-top: 5px;">
<span class="criticaltext">*</span>  Required Item
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" id="submitButton" value="Continue"/>
</div>

</form>
<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>



</div>


<% if(narrow==true){ %><br><div style="clear:both;"></div><br><% } %>

<div style="clear:both;"></div>
<% if(! aszHostID.equalsIgnoreCase("volengurbmin") ){ %>
<b>PLEASE NOTE:</b> Creating an account on <a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a> will also create an 
account for you on <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

<% if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
			<p>This section of the RoundTripMissions site uses a separate account on our partner site ChristianVolunteering.org, which requires a separate login. An account is required either to connect with any service opportunity or to post any service opportunity.</p>
<% } else { %>
			<p>If you are the contact for an organization, fill out this form. 
			After clicking &quot;continue&quot; you will be brought to your personal 
			account summary where you can add your organization's information and volunteer opportunities.</p>
<% } %>
<% } %>

</div>
<span>
<div id="processing" style="display:none;">
<center>
 <br><br>
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

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
<% } %>