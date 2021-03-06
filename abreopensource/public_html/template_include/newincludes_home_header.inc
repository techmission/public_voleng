<!-- BEGING HEADER -->
<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
  <%@ include file="/template/ivol_home_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>
	<jsp:include page="/template/agrm_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengboston" )){ %>
  <%@ include file="/template/cityserve_home_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
	<jsp:include page="/template/ccdaheader.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengcityserve" )){ %>
  <%@ include file="/template/cityserve_home_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/template/hlic_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenglfa" )){ %>
	<jsp:include page="/template/lfa_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )){ %>
  <%@ include file="/template/cityserve_home_header.inc" %> 
<% } else if(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )){ %>
	<jsp:include page="/template/salvationarmy_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengsojo" )){ %>
	<jsp:include page="/template/sojourners_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
	<jsp:include page="/template/uywi_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengworldvision" )){ %>
	<jsp:include page="/template/worldv_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
	<jsp:include page="/template/youthpartner_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else{ %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>
<meta name="keywords" content="christian volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Christianvolunteering.org is the largest directory of Christian volunteer opportunities in the world providing opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
<link href="template/standard.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="template/menu_home.js"></script>
<script type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<body onload="MM_preloadImages('imgs/nav_home_on.gif','imgs/nav_search_on.gif','imgs/nav_volunteer_on.gif','imgs/nav_nonprofits_on.gif','imgs/nav_myaccount_on.gif','imgs/nav_training_on.gif','imgs/nav_about_on.gif'); init();">

<div id="wrapper">

<div id="contentwrapper" class="home">

<div id="banner" class="home">

<div id="logo"> <a href="http://www.christianvolunteering.org">
<img src="<%=request.getContextPath()%>/imgs/logo_org.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/> </a> </div>

<div id="search" class="home">
<img src="<%=request.getContextPath()%>/imgs/search_boxtop.gif" width="334" height="10" /><br clear="all" />
<div id="searchcontent"><img src="<%=request.getContextPath()%>/imgs/search_logintitle.gif" alt="Log In Christian Volunteering" width="49" height="16" /><br />
<form name="individualForm" method="post" action="<%=request.getContextPath()%>/register.do">
<input type="hidden" name="method" value="processLogin">
  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">
    <tr>
      <td width="90" align="right">e-mail</td>
      <td><input style="width:120px;" type="text" name="username" maxlength="50" /></td>
      </tr>
    <tr>
      <td align="right">password</td>
      <td><input style="width:120px;" type="password" name="password1" maxlength="50" />
        <input name="submit" type="image" id="submit" value="Log In" src="<%=request.getContextPath()%>/imgs/search_arrow.gif" /></td>
      </tr>
  </table>
</form>



</div>

</div>
<div  class="cleardiv"></div>
</div>
<% } %>
