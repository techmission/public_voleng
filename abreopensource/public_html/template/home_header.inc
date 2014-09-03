<% 
//session.putValue ("MyIdentifier1","");  // Initialize Value into session Object - used for drupalized G.A.
String aszHomeHeader = aszIncludePath + "home_header.jsp";
%>



<% String aszBodyClass=""; %>

<% if( bHeaderSet == false ){ %>
<%
/*
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
*/
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<% if(aszHostID.equalsIgnoreCase( "voleng1" )) { %>
	<meta name="robots" content="index, follow, noarchive">
	<% } else { %>
	<meta name="robots" content="noindex, nofollow, noarchive">
	<% } %>

	<% if ( ((
		aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") || 
		aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp") 
		  )) || aszHostID.equalsIgnoreCase("volengchurch") )
	{ %>
	<title>Church Volunteer Training & Search - Find Short-Term Missions and Group Opportunities for Church Volunteers at ChristianVolunteering.org</title>
	<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>
	<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>
	<% } else { %>
		<title>
		<% if(aszHostID.equalsIgnoreCase( "volengtwincities" ) || aszLandingPage.equalsIgnoreCase( "twincities" )){ %>
			TwinCities.ChristianVolunteering.org: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengindy" ) || aszLandingPage.equalsIgnoreCase( "indy" )){ %>
			Indy.ChristianVolunteering.org: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" ) || aszLandingPage.equalsIgnoreCase( "miami" )){ %>
			Miami Christian Volunteer Directory: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" ) || aszLandingPage.equalsIgnoreCase( "chicago" )){ %>
			Chicago Christian Volunteer Directory: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" ) || aszLandingPage.equalsIgnoreCase( "denver" )){ %>
			Denver.ChristianVolunteering.org: 
		<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" ) || aszLandingPage.equalsIgnoreCase( "losangeles" )){ %>
			Los Angeles Christian Volunteer Directory: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" ) || aszLandingPage.equalsIgnoreCase( "newyork" )){ %>
			New York Christian Volunteer Directory: 
		<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" ) || aszLandingPage.equalsIgnoreCase( "newengland" )){ %>
			Christian Volunteering Network with City Serve: 
		<% } else if(	
				(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/nonp-view11.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")) ||
				(false==true) 
		){ // don't load the title tag or the meta description tag here - it has already been included in the file itself %>
		<% } else { %>
			ChristianVolunteering.org: 
		<% } %>
	Search over 10,000 Christian missions trips and volunteer opportunities. Find opportunities in orphanages, medical missions, urban ministry, Christian internships, and church volunteering.</title>
	<% } %>

<% if(aszLandingPage.equalsIgnoreCase( "foundationgrants" )){ %>
<meta name="keywords" content="Christian Grants, Christian Foundation, community, service, urban ministry, youth, grants, missions, TechMission">
<meta name="description" content="Free Christian Foundation Grants Directory with almost 10,000 grants and foundations">
<% } else { %>
<meta name="keywords" content="Christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Search over 10,000 Christian missions trips and volunteer opportunities: ChristianVolunteering.org. Find opportunities in orphanages, medical missions, urban ministry, Christian internships, and church volunteering.">
<% } %>
<% } %>

<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/format_boxes_chrisvol.css" rel="stylesheet" type="text/css" />
<% if(! ( ( aszPath.equalsIgnoreCase("/voleng2/virtual_vol.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/businessasmissionvolunteering.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
		(aszHostID.equalsIgnoreCase("volengchurch")))){ %>
<link href="http://www.christianvolunteering.org/template_include/css/jazzy.css" rel="stylesheet" type="text/css" />
<% } %>
<% if ( ((
        aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") ||
        aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp")
      )) || aszHostID.equalsIgnoreCase("volengchurch") )
{ %>
<link href="http://www.christianvolunteering.org/template_include/css/churchvol-wide.css" rel="stylesheet" type="text/css" />
<% } %>

<link href="http://www.christianvolunteering.org/template_include/css/nice_menus.css" rel="stylesheet" type="text/css" />

<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->



<!--DRUPAL NAVIGATION js (only needed for IE; CSS does it in other browsers)-->

<script type="text/javascript" src="http://www.christianvolunteering.org/misc/jquery.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery.curvycorners.js"></script>

<!--[if IE]>

<script type="text/javascript" src="http://www.christianvolunteering.org/misc/drupal.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/nice_menus.js"></script>
<![endif]-->

<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/includes/hovermenu.js"></script>
<link href="http://www.urbanministry.org/sites/all/themes/um/includes/network_band/network-band.css" rel="stylesheet" type="text/css" />

<%@ include file="/template_include/menu_classes.inc" %>
<% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>

</head>

<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/facebookapi_keys.inc" %>
    <%@ include file="/template_include/facebook_graphapi_init.inc" %>

<%@ include file="/template_include/network_band/network-band.inc" %>



<div id="wrapper">

<div id="contentwrapper" class="home">

<div id="banner" class="home">
<div id="logo">

<a href="<%=request.getContextPath()%>/" >
<% if(aszHostID.equalsIgnoreCase( "volengcatholic" ) || aszLandingPage.equalsIgnoreCase( "catholic" )){ %>
<img src="/imgs/catholic_volunteering_org.jpg" alt="Catholic Volunteer and Short Term Missions Search" width="417" height="85" border="0"/></a>

<% } else if(aszHostID.equalsIgnoreCase( "volengchurch" ) || aszLandingPage.equalsIgnoreCase( "church" )){ %>
<img src="/imgs/churchvol_logo.gif" alt="Curch Volunteer and Short Term Missions Search" width="360" height="65" border="0"/></a>

<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" ) || aszLandingPage.equalsIgnoreCase( "chicago" )){ %>
<img src="/imgs_chicago/logo_org_Chicago.gif" alt="Christian Volunteer and Short Term Missions Search" width="408" height="80" border="0"/> </a>

<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" ) || aszLandingPage.equalsIgnoreCase( "twincities" )){ %>
<img src="/imgs_twincities/logo_TWIN_350.gif" alt="ChristianVolunteering.org" border="0"/></a>

<% } else if(aszHostID.equalsIgnoreCase( "volengindy" ) || aszLandingPage.equalsIgnoreCase( "indy" )){ %>
<img src="/imgs_indy/indi_logo.gif" alt="ChristianVolunteering.org" width="417" height="86" border="0"/></a>
<a href="http://www.starfishinitiative.org"><img src="/imgs_indy/starfish1.JPG" border="0">	

<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" ) || aszLandingPage.equalsIgnoreCase( "miami" )){ %>
<img src="/imgs_miami/logo_org_Miami.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/> </a> 
<a href="http://www.fcfcfl.org"><img src="/imgs_miami/fcfcfl-logo.gif" border="0">
<a href="http://www.hlicchicago.org"><img src="/imgs_chicago/hlicchicago1.gif" border="0"></a><br>
<img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif">
<a href="http://www.youthpartners.net"><img src="/imgs_chicago/logo_horiz2.jpg" border="0">

<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" ) || aszLandingPage.equalsIgnoreCase( "denver" )){ %>
<img src="/imgs_denver/logo_org_Denver.gif" alt="Christian Volunteer and Short Term Missions Search" width="400" height="80" border="0"/> </a> 
<a href="http://www.hlic.org"><img src="/imgs/hlic1.jpg" border="0"></a><br>
<img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif">
<a href="http://www.youthpartners.net"><img src="/imgs/logo_horiz3.jpg" border="0">

<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" ) || aszLandingPage.equalsIgnoreCase( "losangeles" )){ %>
<img src="/imgs_losangeles/logo_org_LosAngeles.gif" alt="Christian Volunteer and Short Term Missions Search" width="365" height="70" border="0"/> </a><img src="/imgs/blank.gif"><img src="/imgs/blank.gif">
<a href="http://www.uywi.org"><img src="/imgs_losangeles/uywi4.gif "border="0"></a><br> 
<img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif"><img src="/imgs/blank.gif">
<a href="http://www.hlic-la.org"><img src="/imgs_losangeles/hlicla1.gif" border="0">

<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" ) || aszLandingPage.equalsIgnoreCase( "newyork" )){ %>
<img src="/imgs_newyork/logo_org_NewYork.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/> </a> 
<a href="http://www.hlic.org"><img src="/imgs/hlic1.jpg" border="0">

<% } else if(aszHostID.equalsIgnoreCase( "volengseattle" ) || aszLandingPage.equalsIgnoreCase( "seattle" )){ %>
<img src="/imgs_seattle/CV_logo_Seattle.gif" alt="Christian Volunteer and Short Term Missions Search" width="400" height="80" border="0"/>

<% } else if(
                (aszHostID.equalsIgnoreCase( "volengboston" )) ||
                (aszHostID.equalsIgnoreCase( "volengcityserve" )) ||
                (aszHostID.equalsIgnoreCase( "volengnewengland" )) || aszLandingPage.equalsIgnoreCase( "newengland" )
){ %>
<table>
<tr>
<td><a href="http://newengland.christianvolunteering.org/"><img src="/imgs/NE-Logos-header1.gif" alt="ChristianVolunteering.org - New England, Black Ministerial Alliance"  title="ChristianVolunteering.org - New England"border="0" /></a></td>

<td><a href="http://newengland.christianvolunteering.org/"><img src="/imgs/NE-Logos-header2.gif" alt="Vision New England, Emmanuel Gospel Center"  title="ChristianVolunteering.org - New England" border="0" /></a></td>

<td>
</td>
</tr></table>
<% }else{ %>
<img width="347" height="51" border="0" alt="ChristianVolunteering.org" src="/imgs/christianvolunteering-10000.png">
<!--img src="http://www.christianvolunteering.org/imgs/christianvolunteering-10000.png" alt="ChristianVolunteering.org" width="347" height="34751" border="0"/-->
<% } %></a>


</div>

<%@ include file="/template_include/login_form.inc" %>

<div class="image-advertisement" id="ad-507245"><a href="http://www.cityvision.edu"><img src="http://www.urbanministry.org/files/internship-ad-2.gif" alt="Learn more about City Vision College internships" title="Learn more about City Vision College internships" width="270" height="65" style="float: right; margin-right: 13px;"></a></div>

<div  class="cleardiv"></div>

</div>




