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

<% //if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "fycsandbox.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ %>
<%//@ include file="/template_include/facebookapi_keys.inc" %>

<% //} %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<%@ include file="/template_include/facebookapi_init.inc" %>
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
background:none;
height:auto;
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
<% if(aszReferer.contains("&secondtime=true")){ %>
$(document).ready(function() {
	redirectToPersonalityTest();
 });
<% } %>
//-->
</script>


<body onLoad="timer=setTimeout('redirectToPersonalityTest()',10000)">




<div id="account_box">


  
<div class="main_text">

<h5>You must first take the personality/interest test.  You will be redirected in a few seconds.</h5>
<br>
 <center>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
	

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
if( session.getAttribute("FB_User_ID_init") != null || session.getAttribute("FB_User_ID_init") == ""){
	if( user != null){
		if(user.length() > 0){
			if(! session.getAttribute("FB_User_ID").toString().equalsIgnoreCase(session.getAttribute("FB_User_ID_init").toString()) ){
				session.removeAttribute("FB_User_ID_init");
				session.setAttribute("FB_User_ID",user);
				needsLoginIFrame=true;
			}
		}
	}
}
//out.print(needsLoginIFrame);
//needsLoginIFrame=true;// comment out eventually
if(	needsLoginIFrame == true ){
if(aszReferer != null){
if(! aszReferer.contains("&secondtime=true")){
%>
<iframe name="logintest" id="logintest" src="<%=request.getContextPath()%>/register.do?method=showFacebookLogin&FB_Timestamp=<%=timestamp%>&FB_User_ID=<%=fbuserid%>" height="500" width="500" style="display:none" scrolling="no" ></iframe>
<iframe name="loginfooter" id="loginfooter" src="<%=request.getContextPath()%>/register.do?method=showFacebookLogin&FB_Timestamp=<%=timestamp%>&FB_User_ID=<%=fbuserid%>" height="500" width="500" style="display:none" scrolling="no" ></iframe>
<% }}} %>

</body>    
</html>
