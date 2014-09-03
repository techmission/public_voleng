<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<% String aszBodyClass=""; %>

<% if ( ((
	aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") || 
	aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp") 
      )) || aszHostID.equalsIgnoreCase("volengchurch") )
{ %>

<title>Church Volunteer Training & Search - Find Short-Term Missions and Group Opportunities for Church Volunteers at ChristianVolunteering.org</title>

<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>

<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>

<% } else { %>

<title>Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>

<% } %>

<meta name="keywords" content="christian volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">

<meta name="description" content="Christianvolunteering.org is the largest directory of Christian volunteer opportunities in the world providing opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">

<meta name="viewport" content="width=device-width">
	<link href="http://www.christianvolunteering.org/template_include/css/mobile.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/standard.css" rel="stylesheet" type="text/css" />
<% if(! ( ( aszPath.equalsIgnoreCase("/voleng2/virtual_vol.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/businessasmissionvolunteering.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
		(aszHostID.equalsIgnoreCase("volengchurch")))){ %>
<link href="<%=request.getContextPath()%>/template_include/css/jazzy.css" rel="stylesheet" type="text/css" />
<% } %>
<% if ( ((
        aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") ||
        aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp")
      )) || aszHostID.equalsIgnoreCase("volengchurch") )
{ %>
<link href="<%=request.getContextPath()%>/template_include/css/churchvol-wide.css" rel="stylesheet" type="text/css" />
<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>
<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>
<% } %>

<link href="<%=request.getContextPath()%>/template_include/css/navigation.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template_include/css/nice_menus.css" rel="stylesheet" type="text/css" />

<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
	<link href="http://www.christianvolunteering.org/template_include/css/format_boxes_chrisvol.css" rel="stylesheet" type="text/css" />
	<link href="http://www.christianvolunteering.org/template_include/css/mobile.css" rel="stylesheet" type="text/css" />



<!--DRUPAL NAVIGATION js (only needed for IE; CSS does it in other browsers)-->

<script type="text/javascript" src="<%=request.getContextPath()%>/misc/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template_include/js/jquery.curvycorners.js"></script>

<!--[if IE]>

<script type="text/javascript" src="/misc/drupal.js"></script>
<script type="text/javascript" src="/template_include/nice_menus.js"></script>
<![endif]-->

<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/includes/hovermenu.js"></script>

  <%@ include file="/template_include/menu_classes.inc" %>
  
  
  
  
  <% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>
</head>


</head>




<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/facebookapi_keys.inc" %>
    <%@ include file="/template_include/facebook_graphapi_init.inc" %>

<div id="wrapper">

<div id="contentwrapper" class="home">


<div id="banner" class="home"><div id="logo">

 <a href="<%=request.getContextPath()%>/">


<img src="http://www.christianvolunteering.org/imgs/ChristianVolunteering_logo_small.png" alt="Christian Volunteer and Short Term Missions Search" border="0"/></a>

</div>


<%@ include file="/template_include/login_form.inc" %>

<div style="padding-top:5px;" id="ad-20989" class="image-advertisement"><a href="http://www.techmissioncorps.org/"><img height="65" width="270" title="Click to view Internship Opportunities" alt="Click to view Internship Opportunities" src="http://www.urbanministry.org/files/tmc_internship_ad-short.gif"></a></div> 

<div  class="cleardiv"></div>

</div>
