<!-- start JSP information -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information -->


<%@ include file="/template_include/facebookapi_keys.inc" %>
<%@page import="com.google.code.facebookapi.FacebookWebappHelper"%>
<%@page import="java.util.*" %>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
	FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

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

	if(facebookHelp.requireLogin(frameURL)) return;
	if(facebookHelp.requireFrame(frameURL)) return;
}
%>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>


<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-ui-1.8.4.custom.min.js"></script>
	<!--link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" /-->

<link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.base.css" rel="stylesheet" />
<link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.button.css" rel="stylesheet" />
<link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.dialog.css" rel="stylesheet" />
<!--link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.resizable.css" rel="stylesheet" /-->
<link type="text/css" href="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.theme.css" rel="stylesheet" />

	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/jquery-1.4.2.js"></script>
	<!--script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/jquery.bgiframe-2.1.1.js"></script-->
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.position.js"></script>
	<!--script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.resizable.js"></script-->
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="http://www.churchvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.effects.core.js"></script>

<style>
.dialog-form { 
	height: 159; 
}
</style>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
int iTemp=0;



%>


<script language="javascript">
function load_ajax_page(){
	sendRequest('GET', "<%=aszPortal%>/org.do?method=showPortalBannerPost");
}

	
$(document).ready(function() {
	load_ajax_page();
 });  
</script>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(true==false){ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch" >
	  <span id="title" >search </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/advancedsearch.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
	

<div id="results" style="display:inline">

<div id="all_tabs">
<h2>Choose from pre-existing Opportunities to show on your Church site:</h2>



<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
</div> <!-- end: div id all_tabs-->


<div class="cleardiv"></div>


<div id="ajax_res">
	<center>
		<br>
		<h2>Please wait while we load your opportunities... </h2>
		<br>
		<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
	</center>
</div>

<div class="cleardiv"></div>
<p><br /></p>
</div>
</div>


<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

