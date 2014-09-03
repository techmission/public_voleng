<% String aszBodyClass=""; %>
<% if(	
		(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-view11.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")) ||
		(false==true) 
){ // don't load the title tag or the meta description tag here - it has already been included in the file itself %>
<% } else { %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo_org.gif"/>
<head>
<title>Catholic Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: CatholicVolunteering.org</title>
<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<% } %>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/navigation.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/nice_menus.css" rel="stylesheet" type="text/css" />

<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->

<!--[if IE 7]>
        <link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="<%=request.getContextPath()%>/template_include/css/format_boxes_chrisvol.css" rel="stylesheet" type="text/css" />

<!--DRUPAL NAVIGATION js-->
<script type="text/javascript" src="/misc/jquery.js"></script>
<script type="text/javascript" src="/misc/drupal.js"></script>
<script type="text/JavaScript">
<!--
function runSearch2(){
	zip = document.searchForm2.postalcode.value;
	//    cat = document.searchForm2.category1.value;
	if(zip == ""){              // cat == "" && 
		return;
	}
	document.searchForm2.submit();
}
//-->

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
</SCRIPT>

<% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>

</head>
<% 
ApplicationCodesBRLO aCodes2 = new ApplicationCodesBRLO( request );
ArrayList aService2List = new  ArrayList ( 2 );
aCodes2.getAppCodeList( aService2List, 161 );
%>
  <%@ include file="/template_include/menu_classes.inc" %>
<% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>



<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/alexa_analytics.inc" %>
<%@ include file="/template_include/facebookapi_keys.inc" %>

<div id="wrapper">

<% 
String aszContentWrapperClass="";
if(b_includeLeftSidebar==true ){
	aszContentWrapperClass = " solr";
}
%>
<div id="contentwrapper" class="<%=aszContentWrapperClass%>">



<div id="banner">

<div id="logo">

<a href="<%=request.getContextPath()%>/"><img src="/imgs/catholic_volunteering_org.jpg" alt="Catholic Volunteer and Short Term Missions Search" width="417" height="85" border="0"/></a>

</div>

<%@ include file="/template_include/login_form.inc" %>


<div class="cleardiv"></div>

</div>






<!-- END HEADER -->



