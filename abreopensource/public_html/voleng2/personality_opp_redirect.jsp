<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information login required  -->


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

<%// if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ %>
<%//@ include file="/template_include/facebookapi_keys.inc" %>

<%// } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<%@ include file="/template_include/facebookapi_init.inc" %>

<script type="text/javascript">
/*var base64ToString = function(str) {
return (new Buffer(str || "", "base64")).toString("ascii");
};

var base64UrlToString = function(str) {
return base64ToString( base64UrlToBase64(str) );
};

var base64UrlToBase64 = function(str) {
var paddingNeeded = (4- (str.length%4));
for (var i = 0; i < paddingNeeded; i++) {
str = str + '=';
}
return str.replace(/\-/g, '+').replace(/_/g, '/')
};

app.get('/fb', function(req, res) {
var signed_request = req.param('signed_request');
var parts = signed_request.split('.');
var sig = base64UrlToBase64(parts[0]);
var payload = parts[1];
var data = JSON.parse(base64UrlToString(payload));
if (!data.user_id) {
// send over to authorize url
}
else {
// lets verify
         if (data.algorithm.toUpperCase() !== 'HMAC-SHA256') {
res.send('Unknown algorithm. Expected HMAC-SHA256');
return;
}
var secret = 'appsecret';
var hmac = require('crypto').createHmac('sha256', secret);
hmac.update(payload);
var expected_sig = hmac.digest('base64');
if (sig != expected_sig){
console.log('expected [' + expected_sig + '] got [' + sig + ']');
res.send('Hello, this is my app! you are CHEATING! .. expected [' + expected_sig + '] got [' + sig + ']');
}
else {
res.send('Hello, this is my app! you passed verification and are ' + data.user_id);
}
}
});*/
</script>


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
}
#account_box h3 {
font-size:24px;
padding:10px 20 0;
margin:0px;
}
#account_box h4 {
font-size:30px;
color:#1E5761;
padding:0px 35px;
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
#links {text-align:center;}
.main_text td {font-size:11px!important; padding-left:15px;}
#characteristics, #ministrystrengths {width:340px; padding:10px; float:left;}
#ministryWeaknesses, #growthpath, #notablepeople {padding:10px;}
a { color:#000000; font-weight:inherit;}
#footer {padding:0px;}
</style>

<!-- end header information -->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<script language="JavaScript">
<!--Script courtesy of http://www.web-source.net - Your Guide to Professional Web Site Design and Development
var time = null
function redirectToPersonalityTest() {
window.location = 'register.do?method=showPersonalityTest'
}
function redirectToOpps(){
window.location = 'oppsrch.do?method=showMyMinistryOpps'
}
function redirectToInterests(){
window.location = 'register.do?method=showPersonalityMinistryAreas'
}
<% if(aszReferer.contains("&secondtime=true")){ %>
window.onload = redirectToInterests;
$(document).ready(function() {
	redirectToInterests();
 });
<% } %>
//-->
</script>

</head>

<!--body -->
<body onLoad="timer=setTimeout('redirectToInterests()',10000)">

<div id="account_box">
  
<div class="main_text">

<center>
 <h5>You must first specify your ministry interests.  You will be redirected in a few seconds.</h5>
 <% //out.print("timestamp: " + timestamp);
 	//out.print("fbuserid: " + fbuserid);
	%>
<br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
<br>

	
</div>
</div> 
<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%
boolean needsLoginIFrame = false;
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
try{
	if( session.getAttribute("FB_User_ID_init") != null){
			if(user.length() > 0){
				if(! session.getAttribute("FB_User_ID").toString().equalsIgnoreCase(session.getAttribute("FB_User_ID_init").toString()) ){
					session.removeAttribute("FB_User_ID_init");
					session.setAttribute("FB_User_ID",user);
					needsLoginIFrame=true;
				}
			}
	}
}catch(NullPointerException ex){}
//out.print(needsLoginIFrame);
//needsLoginIFrame=true;// comment out eventually
if(	needsLoginIFrame == true ){
if(aszReferer != null){
if(! aszReferer.contains("&secondtime=true")){
%>
<div id="logindiv" style="display:none;">
<iframe name="logintest" id="logintest" src="<%=request.getContextPath()%>/register.do?method=showFacebookLogin&FB_Timestamp=<%=timestamp%>&FB_User_ID=<%=fbuserid%>" height="10" width="600" style="display:inline" scrolling="no" ></iframe>
<iframe name="loginfooter" id="loginfooter" src="<%=request.getContextPath()%>/register.do?method=showFacebookLogin&FB_Timestamp=<%=timestamp%>&FB_User_ID=<%=fbuserid%>" height="10" width="600" style="display:inline" scrolling="no" ></iframe>
</div>
<% }}} %>

</body>    
<%//@ include file="/template_include/footer_login.inc" %>



