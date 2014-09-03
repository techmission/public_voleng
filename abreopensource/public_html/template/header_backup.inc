<% String aszBodyClass=""; %>
<% if(true==true){ %>
<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_header_tags_include.inc" %>
<% } %>
<% if( bHeaderSet == false ){ %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="image_src" href="/imgs/logo.gif"/>
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
		<% if(aszHostID.equals("volengivol")){ %>
			<%=aszSubdomain%>:
		<% }else{ %>
			ChristianVolunteering.org: 
		<% } %>
Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>
	<% } %>
	<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
	<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<% } %>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<% 
if(	aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp") ||
    aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")
){
	// it's already set in the file; don't set it here
%>
<!-- robots meta set -->
<% }else if(aszHostID.equalsIgnoreCase( "voleng1" )) { %>
<meta name="robots" content="index, follow, noarchive">
<%	}else{ %>
<meta name="robots" content="noindex, nofollow, noarchive">
<% } %>

<%@ include file="/template_include/menu_classes.inc" %>


<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<!--link href="http://www.christianvolunteering.org/template_include/css/navigation.css" rel="stylesheet" type="text/css" /-->
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
<%@ include file="/template_include/ajax-solr_includes.inc" %>



<!-- location data country is: <%=maxmind_area_code%> -->
<!-- location data zip code is: <%=maxmind_countryName%> -->
</head>

<% 
if( aszPortalTemplate.equals("basic") ){ 
	aszBodyClass+=" portal";
}
%>
<script TYPE="text/javascript">
<!--
function popup(mylink, windowname)
{
	if (! window.focus)return true;
	var href;
	if (typeof("<%=request.getContextPath()%>/register.do?method=showHelp") == 'string')
	   href="<%=request.getContextPath()%>/register.do?method=showHelp";
	else
	   href="<%=request.getContextPath()%>/register.do?method=showHelp".href;
	window.open(href, windowname, 'width=400,height=200,scrollbars=yes');
	return false;
}
//-->
</script>

<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/facebookapi_keys.inc" %>
<%@ include file="/template_include/facebook_graphapi_init.inc" %>

<% if(  aszPortalTemplate.equals("main") && ((aszHostID.equalsIgnoreCase( "voleng1" )) || (aszHostID.equalsIgnoreCase( "volengcatholic" ))) ){ %>
		<%@ include file="/template_include/network_band/network-band.inc" %>
<% } %>

<div id="wrapper">
<% 
String aszContentWrapperClass="";
if( aszPortalTemplate.equals("basic") ){ 
	aszContentWrapperClass+="basic ";
}
if(b_includeLeftSidebar==true ){
	aszContentWrapperClass += "solr";
}
if(bSkipBanner==true && aszPortalTemplate.equals("basic") ){ 
	aszContentWrapperClass += " nobanner";
}
%>

<div id="contentwrapper" class="<%=aszContentWrapperClass%>">

<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_header_include.inc" %>
<% }else if( aszPortalTemplate.equals("main") ){ %>
	<%@ include file="/template/main_header_include.inc" %>
<% } %>





<!-- END HEADER -->
