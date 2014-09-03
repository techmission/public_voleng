<!-- BEGING HEADER -->

<% 
//session.putValue ("MyIdentifier1","");  // Initialize Value into session Object - used for drupalized G.A.
String aszHomeHeader = aszIncludePath + "home_header.jsp";
%>

<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
<% } else { %>



<% if(aszHostID.equalsIgnoreCase( "voleng1" )) { %>

	<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

<% } else { %>

	<META NAME="ROBOTS" CONTENT="INDEX, NOFOLLOW">

<% } %>

<% String aszBodyClass=""; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

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

<link href="/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<% if(! ( ( aszPath.equalsIgnoreCase("/voleng2/virtual_vol.jsp")) ||
		( aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
		(aszHostID.equalsIgnoreCase("volengchurch")))){ %>
<link href="/template_include/css/jazzy.css" rel="stylesheet" type="text/css" />
<% } %>
<% if ( ((
        aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp") ||
        aszPath.equalsIgnoreCase("/voleng2/church_volunteering.jsp")
      )) || aszHostID.equalsIgnoreCase("volengchurch") )
{ %>
<link href="/template_include/css/churchvol-wide.css" rel="stylesheet" type="text/css" />
<% //} else if (( aszPath.equalsIgnoreCase("/voleng2/christians_in_technology.jsp") || aszPath.equalsIgnoreCase("/voleng2/christians_in_technology_instructions.jsp") )) { %>
<% //title>Christians in Technology - Find Volunteer Opportunities at ChristianVolunteering.org</title%>
<% } %>

<link href="/template_include/css/navigation.css" rel="stylesheet" type="text/css" />
<link href="/template_include/css/nice_menus.css" rel="stylesheet" type="text/css" />

<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->


<!--DRUPAL NAVIGATION js (only needed for IE; CSS does it in other browsers)-->

<script type="text/javascript" src="/misc/jquery.js"></script>
<script type="text/javascript" src="/template_include/js/jquery.curvycorners.js"></script>

<!-- ************************************* gigya socialize ************************************** -->
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?services=socialize"></script> 

<script type="text/javascript">
				$(document).ready(function() { 
						doStuff(); 
						$("#facebook-login").click( function(){ facebookLogin(); return false; });
				});
				
        var conf = 
        {
            // Please replace this with your API key
            APIKey: 'APIKey'
        };

        // This method is activated when page is loaded
        function doStuff() {
            // register for login event
            gigya.services.socialize.addEventHandlers(conf, { context: { str: 'congrats on your' }, onLogin: onLoginHandler });
						/* facebookLogin(); */
				}
			
			function facebookLogin() {
				gigya.services.socialize.login(conf, {		
					'provider': 'facebook',
					'redirectURL': '/createaccount.jsp',
					'useFacebookConnect': true
				});
			};

        // onLogin Event handler
        function onLoginHandler(eventObj) {
            alert(eventObj.context.str + ' ' + eventObj.eventName);
            // verify the signature ...
        }

        
            // Display a status message according to the response.
            function linkAccounts_callback(response)
            {
                alert('linkAccounts response: ' + response.status + ', ' + response.statusMessage);
            }
        
    </script>

<script type="text/javascript">
																

			
</script>
<!--[if IE]>

<script type="text/javascript" src="/misc/drupal.js"></script>
<script type="text/javascript" src="/template_include/nice_menus.js"></script>
<![endif]-->

<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/includes/hovermenu.js"></script>

<script type="text/javascript">

<!--

function MM_preloadImages() {

 //v3.0

  var d=document;

 if(d.images){

 if(!d.MM_p) d.MM_p=new Array();

    var i,j=d.MM_p.length,a=MM_preloadImages.arguments;

 for(i=0; i<a.length; i++)

    if (a[i].indexOf("#")!=0){

 d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}}



function MM_swapImgRestore() { 

//v3.0

  var i,x,a=document.MM_sr;

 for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++)

 x.src=x.oSrc;}

function MM_findObj(n, d) {

 //v4.01  var p,i,x;  

if(!d) d=document; 

if((p=n.indexOf("?"))>0&&parent.frames.length) {

    d=parent.frames[n.substring(p+1)].document;

 n=n.substring(0,p);}

  if(!(x=d[n])&&d.all) x=d.all[n];

 for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];

  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);

  if(!x && d.getElementById) x=d.getElementById(n);

 return x;}function MM_swapImage() { //v3.0  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)

   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}}

//-->

</script>

<link href="http://www.urbanministry.org/themes/universal_assets/network_band/network-band.css" rel="stylesheet" type="text/css" />

  <%@ include file="/template_include/menu_classes.inc" %>

</head>




<body id="chrisvol" class="<%=aszBodyClass%>">

<%@ include file="/template_include/network_band/network-band.inc" %>



<div id="wrapper">

<div id="contentwrapper" class="home">

<div id="banner" class="home"><div id="logo">

 <a href="<%=request.getContextPath()%>">

<img src="<%=request.getContextPath()%>/imgs/logo.gif" alt="Christian Volunteer and Short Term Missions Search" width="417" height="86" border="0"/></a> </div>

<div id="search" class="home"><div id="searchcontent" width="300">



<%@ include file="/template_include/login_form.inc" %>






</div></div>



<div  class="cleardiv"></div>

</div>

<% } %>


