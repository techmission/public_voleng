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
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Training</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Training</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; training</div> 
</div>
<% } %>
    
		<div id="body">	  
<p><h1>Travel Links</h1> </p>

<p><h3>Travel Agents</h3></p>
<p>&nbsp;</p>
<p>Need help booking transportation? Here's a list of travel agents
which work with missions teams.</p>
<ul><li><p><a href="http://www.outlandtravel.com/">www.outlandtravel.com</a> </p></li>	
<li><p><a href="http://www.christianmissiontravel.com/">www.christianmissiontravel.com/</a></p></li>	
<li><p><a href="http://www.womito.com/">www.womito.com</a></p></li>	
<li><p><a href="http://www.raptimusa.com">www.raptimusa.com</a></p></li>	
<li><p><a href="http://www.ministrytravel.com/">www.ministrytravel.com</a></p></li>	
<li><p><a href="http://www.intermissionsworldtravel.com/">www.intermissionsworldtravel.com</a></p></li>	
<li><p><a href="http://www.missionaryair.com/">www.missionaryair.com</a></p></li>	
<li><p><a href="http://www.missiontravel.com.au/home/">www.missiontravel.com.au/home/</a></p></li>	
<li><p><a href="http://www.mtstravel.com/relig/religP3.html">www.mtstravel.com/relig/religP3.html</a></p></li></ul>	
<p><h3>Find Cheap Flights Online</h3></p>	
<p>&nbsp;</p>
<ul><li><p><a href="http://www.expedia.com/">www.expedia.com</a></p></li>	
<li><p><a href="http://www.Travelocity.com/">www.Travelocity.com</a></p></li>	
<li><p><a href="http://www.Priceline.com/">www.Priceline.com</a></p></li>	
<li><p><a href="http://www.cheapflights.com/">www.cheapflights.com</a></p></li>	
<li><p><a href="http://www.sidestep.com/">www.sidestep.com</a></p></li>	
<li><p><a href="http://www.airfare.com/">www.airfare.com</a></p></li>	
<li><p><a href="http://www.kayak.com/">www.kayak.com</a></p></li>	
<li><p><a href="http://www.cheaptickets.com/">www.cheaptickets.com</a></p></li>	
<li><p><a href="http://www.orbitz.com/">www.orbitz.com</a></p></li>	
<li><p><a href="http://www.sta-travel.com/">www.sta-travel.com</a></p></li>	
<li><p><a href="http://www.travelcuts.com/">www.travelcuts.com</a></p></li></ul>	
	
	

	
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->