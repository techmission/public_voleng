<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Articles and Links for Equipping & Managing Volunteers and for Volunteers</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Why ChristianVolunteering.org?</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Why ChristianVolunteering.org?</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt;  <a href="<%=aszPortal%>/training.jsp">training</a> &gt; why christianvolunteering.org?</div> 
</div>
<% } %>
    
		<div id="body">	  
<% if (!(aszSecondaryHost.equalsIgnoreCase("volengivol") )){ %>
<h2><p>1. More people are needed to serve the kingdom!</p></h2>
<p>He told them, "The harvest is plentiful, but the workers are few. Ask the Lord of the harvest, therefore, to send out workers into his harvest field."
<br>Luke 10:2</p>
<!--<h2><p>2. The world is unjust.</p></h2>
<p>Though I cry, 'I've been wronged!' I get no response; though I call for help, there is no justice.<br>
Job 19:7</p>-->
<h2><p>2. God calls us to work justice.</p></h2>
<p>Learn to do right! Seek justice, encourage the oppressed. Defend the cause of the fatherless, plead the case of the widow.
<br>Isaiah 1:17</p>
<p>Religion that God our Father accepts as pure and faultless is this: to look after orphans and widows in their distress and to keep oneself 
from being polluted by the world.<br>James 1:27</p>
<p>In the same way, faith by itself, if it is not accompanied by action, is dead. <br>James 2:17</p>

<p>Is not this the kind of fasting I have chosen: to loose the chains of injustice and untie the cords of the yoke, to set the oppressed free 
and break every yoke? Is it not to share your food with the hungry and to provide the poor wanderer with shelter-- when you see the naked, to 
clothe him, and not to turn away from your own flesh and blood? Then your light will break forth like the dawn, and your healing will quickly 
appear; then your righteousness will go before you, and the glory of the Lord will be your rear guard.
<br>Isaiah 58:6</p>

<!--<h2><p>3. We're serving others to serve Jesus.</p></h2>
<p>For I was hungry and you gave me something to eat, I was thirsty and you gave me something to drink, I was a stranger and you invited me in,
I needed clothes and you clothed me, I was sick and you looked after me, I was in prison and you came to visit me ... I tell
 you the truth, whatever you did for one of the least of these brothers of mine, you did for me.<br>Matthew 25:35-36, 40</p>-->

<p>For more on why we do what we do, see <a href="<%=aszPortal%>/about.jsp">About ChristianVolunteering.org</a>.</p>
<% } %>

	
</div>
</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
