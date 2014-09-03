<% String aszBodyClass=""; %>

<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
<% } else{ %>

<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_header_tags_include.inc" %>
<% } %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

<% if(	
			(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
			(aszPath.equalsIgnoreCase("/voleng2/nonp-view11.jsp")) ||
			(aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")) ||
			(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")) ||
			(aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")) ||
			(false==true) 
	){ // don't load the title tag or the meta description tag here - it has already been included in the file itself %>
<% } else if(aszPortalHeaderTags.length()>0){ %>
<%=aszPortalHeaderTags%>
<% }else{ %>
	<title>
	<% if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>
		TwinCities.ChristianVolunteering.org: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>
		Indy.ChristianVolunteering.org: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )){ %>
		Miami Christian Volunteer Directory: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" )){ %>
		Chicago Christian Volunteer Directory: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )){ %>
		Denver.ChristianVolunteering.org: 
	<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )){ %>
		Los Angeles Christian Volunteer Directory: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )){ %>
		New York Christian Volunteer Directory: 
	<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )){ %>
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
Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>
<% } %>
	<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
	<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<% if(aszHostID.equalsIgnoreCase( "voleng1" )) { %>
	<meta name="robots" content="index, follow, noarchive">
<% }else{
	if(aszHostID.equalsIgnoreCase( "volengchurch" )) { 
		if(	aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp") ||
            aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")
		){
%>
		        <meta name="robots" content="index, nofollow, noarchive">
<%
 		}else{
%>
		        <meta name="robots" content="noindex, nofollow, noarchive">
<%
		}
	}else{
%>
		<meta name="robots" content="noindex, nofollow, noarchive">
<% 
	}
} 
%>
<%@ include file="/template_include/menu_classes.inc" %>

<% 
ApplicationCodesBRLO aCodes2 = new ApplicationCodesBRLO( request );
ArrayList aService2List = new  ArrayList ( 2 );
aCodes2.getAppCodeList( aService2List, 161 );
%>

<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/navigation.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 7]>
        <link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="http://www.christianvolunteering.org/template_include/css/format_boxes_chrisvol.css?1" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://www.christianvolunteering.org/misc/jquery.js"></script>
<!--DRUPAL NAVIGATION js (only needed for IE; CSS does it in other browsers)-->
<!--[if IE]>
<script type="text/javascript" src="/misc/drupal.js"></script>
<![endif]-->

<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/includes/hovermenu.js"></script>

<link href="http://www.urbanministry.org/sites/all/themes/um/includes/network_band/network-band.css" rel="stylesheet" type="text/css" />

<!-- method is: <%=aszMethodName%> -->
<% if(b_includeAjaxSolr==true){ %>
<%//@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>
</head>

<% 
if( aszPortalTemplate.equals("basic") ){ 
	aszBodyClass+=" portal";
}
%>

<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/facebookapi_keys.inc" %>
<%@ include file="/template_include/facebook_graphapi_init.inc" %>

<% if( (aszHostID.equalsIgnoreCase( "voleng1" )) || (aszHostID.equalsIgnoreCase( "volengcatholic" )) ){ %>
	<% if( aszPortalTemplate.equals("main") ){ %>
		<%@ include file="/template_include/network_band/network-band.inc" %>
	<% } %>
<% } %>

<div id="wrapper">
<% 
String aszContentWrapperClass="";
if(b_includeLeftSidebar==true ){
	aszContentWrapperClass = "solr";
}
%>
<div id="contentwrapper" class="<%=aszContentWrapperClass%>">

<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_header_include.inc" %>
<% }else if( aszPortalTemplate.equals("main") ){ %>
	<%@ include file="/template/main_header_include.inc" %>
<% } %>

<!-- END HEADER -->
