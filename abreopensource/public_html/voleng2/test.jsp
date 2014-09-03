<!-- start JSP information -->
<%@ include file="/template/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<% //@ include file="/template/header.inc" %>

	<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<% 
ApplicationCodesBRLO aCodes2 = new ApplicationCodesBRLO( request );
ArrayList aService2List = new  ArrayList ( 2 );
aCodes2.getAppCodeList( aService2List, 161 );
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<% // REGIONAL (but same template)-Specific 1 %>
<% // included below is code for regional sites; these should hardly differ from the normal template, so there is no point in having the file included more than once %>

<% if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>

<title>TwinCities.ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>

<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>

<title>Indy.ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>

<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )){ %>

<title>Miami Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>

<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" )){ %>

<title>Chicago Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>

<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )){ %>

<title>Denver.ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>

<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )){ %>

<title>Los Angeles Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>

<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )){ %>

<title>New York Christian Volunteer Directory: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Community Service & Virtual Online Volunteering Opportunities: ChristianVolunteering.org</title>


<% } else if(

		(aszHostID.equalsIgnoreCase( "volengboston" )) ||

		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||

		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		

){ %>

<title>Christian Volunteering Network with City Serve: Matching Volunteers with Urban Ministry Opportunities</title>

<% } else { %>

<title>ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>

<% } %>









<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">



<link href="http://www.christianvolunteering.org/template/standard-wide_anya.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template/navigation_anya.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template/nice_menus.css" rel="stylesheet" type="text/css" />

<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script type="text/javascript" src="template/menu_home.js">
</script>

<!--DRUPAL NAVIGATION js-->

<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>

<script type="text/javascript" src="http://www.urbanministry.org/misc/drupal.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/modules/nice_menus/nice_menus.js"></script>




<script type="text/javascript" src="http://www.christianvolunteering.org/template/menu.js"></script>

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

if (typeof("<%=aszPortal%>/register.do?method=showHelp") == 'string')

   href="<%=aszPortal%>/register.do?method=showHelp";

else

   href="<%=aszPortal%>/register.do?method=showHelp".href;

window.open(href, windowname, 'width=400,height=200,scrollbars=yes');

return false;

}

//-->

</SCRIPT>



<body  id="chrisvol" onLoad="MM_preloadImages('<%=aszPortal%>/imgs/nav_home_on.gif','<%=aszPortal%>/imgs/nav_search_on.gif','<%=aszPortal%>/imgs/nav_volunteer_on.gif','<%=aszPortal%>/imgs/nav_nonprofits_on.gif','<%=aszPortal%>/imgs/nav_myaccount_on.gif','<%=aszPortal%>/imgs/nav_training_on.gif','<%=aszPortal%>/imgs/nav_about_on.gif'); init()">

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>
<%@ include file="/template/network_band/network-band.inc" %>
<% } %>

<div id="wrapper">



<div id="contentwrapper">



<div id="banner">





<% // REGIONAL (but same template)-Specific 2 %>

<% // included below is code for regional sites; these should hardly differ from the normal template, so there is no point in having the file included more than once %>



<div id="logo">
<% if(

		(aszHostID.equalsIgnoreCase( "volengboston" )) ||

		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||

		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		

){ %>
<table>
<tr>
<td><a href="http://www.aes-egc.org/cityserve"><img src="http://www.christianvolunteering.org/imgs/cityserve_logo_small_EGC_copy.gif" alt="City Serve" border="0" /></a></td>
<td>
<% } %> <a href="<%=aszPortal%>/" >



<% if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>

<img src="/imgs/logo_TWIN_350.gif" alt="ChristianVolunteering.org" border="0"/></a>

<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>

<img src="<%=aszPortal%>/imgs/indi_logo.gif" alt="ChristianVolunteering.org" width="417" height="86" border="0"/></a>

<a href="http://www.starfishinitiative.org"><img src="<%=aszPortal%>/imgs/starfish1.JPG" border="0">	

<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )){ %>

<img src="<%=aszPortal%>/imgs/logo_org_Miami.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/> </a> 

<a href="http://www.fcfcfl.org"><img src="<%=aszPortal%>/imgs/fcfcfl-logo.gif" border="0">

<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" )){ %>

<img src="<%=aszPortal%>/imgs/logo_org_Chicago.gif" alt="Christian Volunteer and Short Term Missions Search" width="408" height="80" border="0"/> </a> 

<a href="http://www.hlicchicago.org"><img src="<%=aszPortal%>/imgs/hlicchicago1.gif" border="0"></a><br>

<img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif">

<a href="http://www.youthpartners.net"><img src="<%=aszPortal%>/imgs/logo_horiz2.jpg" border="0">


<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )){ %>

<img src="<%=aszPortal%>/imgs/logo_org_Denver.gif" alt="Christian Volunteer and Short Term Missions Search" width="400" height="80" border="0"/> </a> 

<a href="http://www.hlic.org"><img src="http://www.christianvolunteering.org/imgs/hlic1.jpg" border="0"></a><br>

<img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif">

<a href="http://www.youthpartners.net"><img src="http://www.christianvolunteering.org/imgs/logo_horiz3.jpg" border="0">



<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )){ %>

<img src="<%=aszPortal%>/imgs/logo_org_LosAngeles.gif" alt="Christian Volunteer and Short Term Missions Search" width="365" height="70" border="0"/> </a><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif">

<a href="http://www.uywi.org"><img src="<%=aszPortal%>/imgs/uywi4.gif "border="0"></a><br> 

<img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif"><img src="http://www.christianvolunteering.org/imgs/blank.gif">

<a href="http://www.hlic-la.org"><img src="<%=aszPortal%>/imgs/hlicla1.gif" border="0">



<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )){ %>

<img src="<%=aszPortal%>/imgs/logo_org_NewYork.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/> </a> 

<a href="http://www.hlic.org"><img src="http://www.christianvolunteering.org/imgs/hlic1.jpg" border="0">



<% } else if(aszHostID.equalsIgnoreCase( "volengseattle" )){ %>

<img src="http://www.christianvolunteering.org/seattle/imgs/CV_logo_Seattle.gif" alt="Christian Volunteer and Short Term Missions Search" width="400" height="80" border="0"/>



<% } else if(

		(aszHostID.equalsIgnoreCase( "volengboston" )) ||

		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||

		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		

){ %>

<img src="http://www.christianvolunteering.org/imgs/NE_small.jpg" alt="ChristianVolunteering.org" border="0"/>

<% }else{ %>

<img src="<%=aszPortal%>/imgs/logo_org.gif" alt="ChristianVolunteering.org" width="417" height="86" border="0"/>

<% } %></a>
<% if(

		(aszHostID.equalsIgnoreCase( "volengboston" )) ||

		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||

		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		

){ %>
</td>
<td><a href="http://www.visionnewengland.com"><img src="http://www.christianvolunteering.org/imgs/pic/vne_small.jpg" alt="Vision New England" border="0"/></a></td>
</tr></table>
<% } %>

</div>









<% if( (aszHostID.equalsIgnoreCase("voleng1")) || (aszHostID.equalsIgnoreCase("default")) ){%>

<div id="searchjoint">

<img src="http://www.christianvolunteering.org/imgs/search_boxtop.gif" width="420" height="10" /><br clear="all" />



	<div id="searchcontent">
<!--
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="http://www.christianvolunteering.org/imgs/search_boxtitle.gif" alt="Search Opportunities" width="121" height="16" /><br />


-->

<!-- top search goes here -->
<%@ include file="/template/topsearchMine.inc" %>


<!-- end top search -->

<br>

</div>



</div>



<% } else { %>

<div id="search">

<img src="http://www.christianvolunteering.org/imgs/search_boxtop.gif" width="334" height="10" /><br clear="all" />



<% if( (aszHostID.equalsIgnoreCase("volengmiami")) || (aszHostID.equalsIgnoreCase("volengchicago")) || (aszHostID.equalsIgnoreCase("volenglosangeles")) || (aszHostID.equalsIgnoreCase("volengdenver")) ) { %>

	<div id="searchcontent" class="higher">

<% } else { %>



	<div id="searchcontent">



<% } %>



&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="http://www.christianvolunteering.org/imgs/search_boxtitle.gif" alt="Search Opportunities" width="121" height="16" /><br />

<form name="searchForm2" method="get" action="<%=aszPortal%>/oppsrch.do">

<input type="hidden" name="method" value="processOppSearchTop">

  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">

      <tr><td height=2></td></tr>

    <tr>

    	<td width=20></td>

      <td width="65" align="right">Postal Code</td>

      <td><input style="width:80px;" type="text" name="postalcode" maxlength="5" />

              <A href="javascript:runSearch2();"><img src="http://www.christianvolunteering.org/imgs/search_arrow.gif" alt="go" width="19" height="19" border="0" align="absmiddle" /></A>

</td>

      <tr><td height=1></td></tr>

      </tr>

    <tr>

    	<td width=20></td>

      <td>&nbsp;</td>

      <td><a href="<%=aszPortal%>/advancedsearch.jsp" title="Advanced Search for Christian Volunteer Opportunities">advanced search</a></td>

      </tr>

  </table>

</form>

</div>

</div>

<% } %>

<div class="cleardiv"></div>

</div>

<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<!-- BEGIN MAINCONTENT -->

<html:form action="/register.do" focus="username" >
<html:hidden property="method" value="processLogin" />

<div id="maincontent">
<div id="pagebanner">

<span style="float: left;">testing page </span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; testing page  </div>
</div>

 <div id="body">
        <img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="415" height="10"><BR>

      	<div align="left">

      <TABLE cellSpacing=0 cellPadding=2 align=center width="537" border=0 height="213">
		<tr>
        <TD align="left" colspan=3 height="42">
          <b>If you do not yet have an account you can 
			<a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;</b>
          If you have already registered with ChristianVolunteering, enter your email and password below.
          <BR>&nbsp;<BR>
        </TD>
      </tr>
		<tr>
        <TD class=formLabel align=right>
          <p align="left"><b>Email</b>
        </TD>
        <TD align=left>
          <html:text property="username" size="25" maxlength="50" styleClass="textinputwhite" />
		</TD>
        <TD height="25">&nbsp;</TD>
        </tr>
		<tr>
        <TD class=formLabel align=right>
          <p align="left">Password 
        </TD>
        <TD align=left>
          <html:password property="password1" size="25" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
		</TD>
      		<td height="27">&nbsp;</td>
		</tr>        	
		<tr>
			<td>&nbsp;</td>
        <TD><INPUT class=submit type=submit value="Log in"> </TD>
			<td height="31">&nbsp;</td>
		</tr>
		<tr>
          <TD align="center" colspan=3 height="19"><FONT color="red"><bean:write name="individualForm" property="errormsg" /></FONT></TD>
      </tr>
		<tr>
        <TD colspan=3 height="29">
                <b>NOTE:</b> If you have an account with 
<a href="http://www.urbanministry.org">UrbanMinistry.org</a>
<% if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
 or <a href="http://www.ccda.org">CCDA.org</a>
<% } %>
				, please log in with the email and password you have provided on that account.
		</TD></tr>
        <tr><td height="30"></td></tr>
        <tr><td height="30" colspan=2>
	        Forgot your 
<% if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
<a href= "http://www.ccda.org/passwordreset">password</A>? 
<% } else  {%>
<a href= "http://www.urbanministry.org/user/password">password</A>? 
<% } %>
			Other <a href="mailto:info@christianvolunteering.org">login problems</A>?
	        Email <a href="mailto:info@christianvolunteering.org">ChristianVolunteering.org</a> 
		</td></tr></table>

</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>

    
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->