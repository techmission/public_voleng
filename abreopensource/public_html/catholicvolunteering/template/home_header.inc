<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<HTML><HEAD>
<% if (( aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") || aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp") )) { %>

<title>Church Volunteer Training & Search - Find Short-Term Missions and Group Opportunities for Church Volunteers at ChristianVolunteering.org</title>

<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>

<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>

<% } else { %>

<title>Catholic Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: CatholicVolunteering.org</title>

<% } %>

<meta name="keywords" content="catholic, catholic volunteering, christian volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, catholic volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA">

<meta name="description" content="CatholicVolunteering.org is the largest directory of Catholic volunteer opportunities in the world providing opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<META NAME="ROBOTS" CONTENT="INDEX, NOFOLLOW">

<% String aszBodyClass=""; %>
<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<!-- added by Anya - styling for nice_menu -->
<link href="http://www.christianvolunteering.org/template_include/css/navigation.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/nice_menus.css" rel="stylesheet" type="text/css" />


<% if (( aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") || aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp") )) { %>
<link href="http://www.christianvolunteering.org/template_include/churchvol-wide.css" rel="stylesheet" type="text/css" />
<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>
<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>
<% } %>
<link href="<%=request.getContextPath()%>/template_include/css/format_boxes_chrisvol.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template_include/css/jazzy.css" rel="stylesheet" type="text/css" />

<!--DRUPAL NAVIGATION js - added by Akeem 2008-03-26-->

<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>

<script type="text/javascript" src="http://www.urbanministry.org/misc/drupal.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/modules/nice_menus/nice_menus.js"></script>
<!-- end Drupal navigation js -->


<%@ include file="/template_include/menu_classes.inc" %>
<% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>

</head>



<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/alexa_analytics.inc" %>
<%@ include file="/template_include/facebookapi_keys.inc" %>



<div id="wrapper">



<div id="contentwrapper" class="home">



<div id="banner" class="home">



<div id="logo"> <a href="<%=request.getContextPath()%>">

<img src="/imgs/catholic_volunteering_org.jpg" alt="Catholic Volunteer and Short Term Missions Search" width="417" height="85" border="0"/> </a> 

</div>
<!-- include login_form -->
<%@ include file="/template_include/login_form.inc" %>


<div  class="cleardiv"></div>

</div>

