<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<% 
//aszPortal=request.getContextPath()+"/cityvision"; 
portal="cityvision";
%>
<bean:define id="applicinfo" name="applicinfo" type="com.abrecorp.opensource.dataobj.ApplicationInfoDTO"/>

<!-- start header information -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">var NREUMQ=NREUMQ||[];NREUMQ.push(["mark","firstbyte",new Date().getTime()]);</script><link rel="image_src" href="/imgs/logo.gif"/>
	
<title> ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>
	
	<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
	<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">


	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="robots" content="index, follow, noarchive">
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


<!-- add in IE-specific fixes for facet CSS -->
<!--[if IE]>
	<link href="/template_include/css/format_boxes_ie.css" rel="stylesheet" type="text/css" />
<![endif]-->


</head>
<body  id="chrisvol" class="nav1-my-account portal" >
<div id="wrapper">


<div id="contentwrapper" class="basic solr">


	
<link href="http://www.christianvolunteering.org/template_include/css/narrow_portal.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-basic.css" rel="stylesheet" type="text/css" />

<style>

#banner .bannerimage{
	float: left;
	padding: 3px 3px 0px 3px;
	width: 375px;
	border: 1px;
	line-height:2em;
}
#banner .portalsearch{
	float: left;
	padding: 3px 3px 0px 3px;
}
#banner a{
	font-weight: bold;
	font-size: 20px;
	color: #596E9F;
	margin-top: 10px;
	margin-bottom: 10px;
}
body{
background: none repeat scroll 0 0 #939575;
}
.city-vision-portal #header {
    font-size: 11px;
    height: 70px;
    margin: 0 18px 0 0;
    padding: 0 32px 10px 20px;
    width: 830px;
	overflow: hidden;
	word-wrap: break-word;
	line-height: normal;
    text-align: left;
}

.city-vision-portal #header a {
	font-family: arial,Arial,Helvetica,sans-serif;
    font-weight: normal;
    text-decoration: none;
	color: #5D6E83;
}

.city-vision-portal #header a img {
    border: 0 none;
}

.city-vision-portal #logo {
    margin: 5px 0 0;
    padding: 0;
    width: 100%;
	float: left;
    margin-right: 15px;
}


.city-vision-portal #menu {
    background: url("/imgs/nav_bg.gif") repeat-x scroll left top transparent;
    border-top: medium none;
    font-size: 17px;
    font-weight: bold;
    height: 33px;
    margin: 0;
    padding: 0 0 0 16px;
    text-transform: capitalize;
 /*   width: 856px; */
    border-bottom: 1px solid #B7884E;
    clear: both;
    font-family: arial,helvetica,sans-serif;
    position: relative;
    z-index: 500;
	line-height: normal;
    text-align: left;
	border-top: 9px solid #66332B;
}
.cityvision #menu {
background: transparent url("http://www.cityvision.edu//sites/all/themes/cityvision/images/nav_bg.png") repeat-x scroll left top;
font-size: 17px;
font-weight: bold;
height: 33px;
margin: 0;
padding: 0 0 0 16px;
text-transform: capitalize;
width: 950px;
border-top: none;
}
#menu {
background: #E0A470 url("http://www.cityvision.edu//sites/all/themes/um/imgs/nav_patt2.gif") repeat-x top left;
height: 27px;
clear: both;
position: relative;
border-top: solid 1px #fcecd9;
border-bottom: solid 1px #b7884e;
font-family: arial,helvetica,sans-serif;
z-index: 500;
padding-left: 7px
}
.city-vision-portal #menu a {
    text-decoration: none;
}

.city-vision-portal .block-nice_menus {
    line-height: normal;
/*    z-index: 10; */
}

.city-vision-portal ul.nice-menu:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}

.city-vision-portal #menu ul {
    list-style: none outside none;
    margin: 0;
    padding: 0;
}

.city-vision-portal ul.nice-menu, ul.nice-menu ul {
    position: relative;
    z-index: 5;
}

.city-vision-portal #menu ul.nice-menu li {
    background: url("http://www.cityvision.edu/sites/all/themes/cvc_internship/images/nav_item_bg.gif") no-repeat scroll right top transparent;
    border-right: medium none;
    display: inline;
    float: left;
    font-weight: bold;
    list-style-type: none;
    margin: 0;
    position: relative;
}

.city-vision-portal #menu ul.nice-menu li {
    font-size: 13px;
}

.city-vision-portal #menu li {
    margin: 0;
    padding: 0;
}

.city-vision-portal #menu ul.nice-menu li a {
    height: 24px;
    padding: 0 10px 0;
}

.city-vision-portal #menu a {
    font-weight: bold;
	display: block;
	color: #404040;
    font-size: 15px;
    text-decoration: none;
	font-family: arial,Arial,Helvetica,sans-serif;
}

.city-vision-portal #chrisvol ul.nice-menu a:link, #chrisvol ul.nice-menu a:visited {
    color: #404040;
}

.city-vision-portal ul.nice-menu-down li a {
  text-transform: capitalize;
}

#maincontent.search_results #all_tabs {
width: 700px;
    padding: 0px;
}

#all_tabs.results-left{
width: 700px;
    padding: 0px;
}

h2{
color: #697A9D;
}
h1{
    color: #475A82;
}
.volunteer {
    background-color: #475A82;
}
a.volunteer {
    background-color: #475A82;
}
a#volunteer {
    background-color: #475A82;
}
#contentwrapper {
    width: 950px;
}
</style>


<div id="banner">
<div class="city-vision-portal">
<div id="header">
  <div id="logo">
    <a title="Home" href="http://www.cityvisioninternships.org">
      <img alt="City Vision College" class="cvc-logo" src="http://www.cityvisioninternships.org/sites/all/themes/cityvision/logo.png"/>
    </a>
    <a href="http://www.christianvolunteering.org" title="ChristianVolunteering">
      <img style="margin:10px 10px 10px 30px;" src="http://www.christianvolunteering.org/imgs/christianvolunteering-only-text-v4.png" class="cv-logo" alt="ChristianVolunteering">
    </a>

  </div>
  <div style="clear:both;"></div>
</div>

<div id="menu">
  <div id="block-nice_menus-2" class="block block-nice_menus odd header ">
	<div class="block-inner">
	   <div class="content">
         <ul id="nice-menu-2" class="nice-menu nice-menu-down sf-js-enabled">
		   <li class="menu-1110 menu-path-urbanministryinternships active-trail first odd"><a class="active" title="" href="http://www.cityvisioninternships.org">Home</a></li>
           <li class="menu-1103 menu-path-cms-cv-intern-apply even "><a title="Apply for Christian Urban Ministry Internship" href="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication">Apply Now</a></li>
           <li class="menu-1109 menu-path-cms-cv-intern-stories odd "><a href="http://www.cityvisioninternships.org/member-stories">What It's Like</a></li>
           <li class="menu-1111 menu-path-node-1043 odd "><a title="Sites" href="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&fq=intern_type:%22City%20Vision%20Internship%22">Sites & Positions</a></li>
<li class="menu-1170 menuparent menu-path-cityvisionedu-cms-cv-site-application even"><a href="http://www.cityvisioninternships.org/site-application" title="">Host an Intern</a>
</li>
<li class="menu-1171 menuparent menu-path-orgmanagement odd"><a href="http://www.christianvolunteering.org/orgmanagement.jsp" title="">Manage Organization</a>
</li>
<li class="menu-1172 menuparent menu-path-manageaccount even"><a href="http://www.christianvolunteering.org/profiles/mine/edit" title="">Manage Account</a>
</li>
         </ul>
	  </div>
    </div>
  </div>
</div>
</div>
</div>



<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="pagebanner" style="margin-left:20px;">
<span style="float: left;">City Vision Internship Application</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/cityvision/register.do?method=showHome">home</a> </div>
</div>

		<div id="body">
			<div align="left">
				<br><br><br>
				<div>
					<h3>Your application has been received. This will put your resume in our general pool of internship applicants, 
					and it will be up to ministry sites to contact you.  
					If you would like to apply to specific ministry sites (which we recommend), 
					then go the the <a href="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&fq=intern_type:%22City%20Vision%20Internship%22">list of ministry sites</a> and click apply for each site you are interested in
					<br><br>
					You can view your application at 
					<a href="http://www.christianvolunteering.org/cityvision/email.do?method=loadOneApplication&nid=<%=applicinfo.getNID()%>">http://www.christianvolunteering.org/cityvision/email.do?method=loadOneApplication&nid=<%=applicinfo.getNID()%></a>. </h3>
					<br><br>
					<br><br>
					
				</div>
			</div>
		</div>
	</div>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
