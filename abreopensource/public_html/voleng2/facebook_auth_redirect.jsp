<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<%//@ include file="/template_include/facebookapi_keys.inc" %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>

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


<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<script language="JavaScript">
<!--Script courtesy of http://www.web-source.net - Your Guide to Professional Web Site Design and Development
var time = null
function redirect() {
<% if(aszRemoteHost.equalsIgnoreCase( "findyourcalling.christianvolunteering.org" )){ %>
		window.location = 'http://apps.facebook.com/find-your-calling';
	<% } else if (aszRemoteHost.equalsIgnoreCase( "findyourcalling.ivolunteering.org" )){ %>
		window.location = 'http://apps.facebook.com/worldchanger';
	<% } else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
		window.location = 'http://apps.facebook.com/fycsandbox';
	<% } %>

}


window.onload = redirectToInterests;
$(document).ready(function() {
	redirect();
 });
//-->
</script>

</head>

<!--body -->
<body onLoad="redirect()">

<div id="account_box">
  
<div class="main_text">

<center>
 <h5>Thank you for authorizing Find Your Calling.  You will be redirected in a few seconds.</h5>

<br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
<br>

	
</div>
</div> 



<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->