<!-- BEGIN HEADER -->
<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
  <%@ include file="/template/ivol_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>
	<jsp:include page="/template/agrm_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengboston" )){ %>
  <%@ include file="/template/cityserve_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
	<jsp:include page="/template/ccdaheader.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengcityserve" )){ %>
  <%@ include file="/template/cityserve_header.inc" %>
<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/template/hlic_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenglfa" )){ %>
	<jsp:include page="/template/lfa_header.inc" flush="true">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )){ %>
  <%@ include file="/template/cityserve_header.inc" %>    
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



<% 
ApplicationCodesBRLO aCodes2 = new ApplicationCodesBRLO( request );
ArrayList aService2List = new  ArrayList ( 2 );
aCodes2.getAppCodeList( aService2List, 161 );
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>
<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">

<!-- <link href="<%=request.getContextPath()%>/template/standard.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=request.getContextPath()%>/template/standard-wide.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<link href="<%=request.getContextPath()%>/template/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script type="text/javascript" src="<%=request.getContextPath()%>/template/menu.js"></script>
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

function runSearch2(){
	zip = document.searchForm2.postalcode.value;
	//    cat = document.searchForm2.category1.value;
	if(zip == ""){              // cat == "" && 
		return;
	}
	document.searchForm2.submit();
}

//-->
</script>

<SCRIPT TYPE="text/javascript">
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




</head>

<body onLoad="MM_preloadImages('<%=request.getContextPath()%>/imgs/nav_home_on.gif','<%=request.getContextPath()%>/imgs/nav_search_on.gif','<%=request.getContextPath()%>/imgs/nav_volunteer_on.gif','<%=request.getContextPath()%>/imgs/nav_nonprofits_on.gif','<%=request.getContextPath()%>/imgs/nav_myaccount_on.gif','<%=request.getContextPath()%>/imgs/nav_training_on.gif','<%=request.getContextPath()%>/imgs/nav_about_on.gif'); init()">

<div id="wrapper">

<div id="contentwrapper">

<div id="banner">
<div id="logo"> <a href="http://www.christianvolunteering.org">
<img src="<%=request.getContextPath()%>/imgs/logo_org.gif" alt="ChristianVolunteering.org" width="417" height="86" border="0"/></a>
</div>

<div id="search">
<img src="<%=request.getContextPath()%>/imgs/search_boxtop.gif" width="334" height="10" /><br clear="all" />
<div id="searchcontent">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/imgs/search_boxtitle.gif" alt="Search Opportunities" width="121" height="16" /><br />


<form name="searchForm2" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchTop">
  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">
      <tr><td height=2></td></tr>
    <tr>
    	<td width=20></td>
      <td width="65" align="right">Postal Code</td>
      <td><input style="width:80px;" type="text" name="postalcode" maxlength="5" />
              <A href="javascript:runSearch2();"><img src="<%=request.getContextPath()%>/imgs/search_arrow.gif" alt="go" width="19" height="19" border="0" align="absmiddle" /></A>
</td>
      <tr><td height=1></td></tr>
      </tr>
    <tr>
    	<td width=20></td>
      <td>&nbsp;</td>
      <td><a href="<%=request.getContextPath()%>/advancedsearch.jsp" title="Advanced Search for Christian Volunteer Opportunities">advanced search</a></td>
      </tr>
  </table>
</form>

</div>

</div>
<div class="cleardiv"></div>
</div>
<!-- END HEADER -->
<% } %>
