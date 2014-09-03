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
	  <span id="title">Short Term Missions Resources</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Short Term Missions Resources</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; <a href="<%=aszPortal%>/training.jsp">training</a> &gt; 
	short term missions resources</div> 
</div>
<% } %>
    
		<div id="body">	  
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<% }else{ %>
<p><h1><a href="#agents">Travel Agents,</a>
<a href="#flights">Find Cheap Flights Online,</a>
<a href="#insurance">Insurance,</a>
<a href="#conferences">Conferences,</a>
<a href="#resources">Other Resources,</a>
<a href="#safety">Safety,</a>
<a href="#fundraising">Fundraising Ideas,</a>
<a href="#train">Training</a>
</h1></p>

<p><a name="agents"><h1>Travel Agents</h1></a></p>

<p>Need help booking transportation? Here's a list of travel agents
which work with missions teams.</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.amazon.com/Leadership-Management-Volunteer-Programs-Administrators/dp/1555425313/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/outland_travel_sm.jpg" border="0" width="130" height="68"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.outlandtravel.com/">www.outlandtravel.com</a>
	</td>
	</tr>

	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.christianmissiontravel.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/cmt_sm.jpg" border="0" width="200" height="75"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.christianmissiontravel.com/">www.christianmissiontravel.com/</a>
	</td>
	</tr>
	
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.womito.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/womito.jpg" border="0" width="350" height="55"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.womito.com/">www.womito.com</a>
	</td>
	</tr>	

	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.raptimusa.com" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/raptim.jpg" border="0" width="350" height="44"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.raptimusa.com">www.raptimusa.com</a>
	</td>
	</tr>	
	
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.ministrytravel.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/mintrav.jpg" border="0" width="182" height="142"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.ministrytravel.com/">www.ministrytravel.com</a>
	</td>
	</tr>	
	
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.intermissionsworldtravel.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/intermissions.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.intermissionsworldtravel.com/">www.intermissionsworldtravel.com</a>	
	</td>
	</tr>	
	
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.missionaryair.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/worldtravel.jpg" border="0" width="350" height="38"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.missionaryair.com/">www.missionaryair.com</a>	
	</td>
	</tr>

	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.missiontravel.com.au/home/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/mission_travel_services.jpg" border="0" width="300" height="53"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.missiontravel.com.au/home/">www.missiontravel.com.au/home/</a>
	</td>
	</tr>

	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.mtstravel.com/relig/religP3.html" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/mts.gif" border="0" width="239" height="46"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.mtstravel.com/relig/religP3.html">www.mtstravel.com/relig/religP3.html</a>	
	</td>
	</tr>	

</table>

<p>&nbsp;</p>
<p><a name="flights"><h1>Find Cheap Flights Online</h1></a></p>
<p>&nbsp;</p>
<table>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.expedia.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/expedia.gif" border="0" width="185" height="50"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.expedia.com/">www.expedia.com</a>
	</td>
	</tr>
	
		<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.expedia.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/travelocity.gif" border="0" width="189" height="58"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.Travelocity.com/">www.Travelocity.com</a>
	</td>
	</tr>

		<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.Priceline.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/priceline.jpg" border="0" width="300" height="239"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.Priceline.com/">www.Priceline.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.cheapflights.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/cheapflights.jpg" border="0" width="281" height="142" ></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.cheapflights.com/">www.cheapflights.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.sidestep.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/sidestep.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.sidestep.com/">www.sidestep.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.airfare.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/airfare.jpg" border="0" width="275" height="148"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.airfare.com/">www.airfare.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.kayak.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/kayak.jpg" border="0" width="197" height="113"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.kayak.com/">www.kayak.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.cheaptickets.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/cheaptickets.jpg" border="0" width="288" height="146"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.cheaptickets.com/">www.cheaptickets.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.orbitz.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/orbitz.jpg" border="0" width="206" height="71"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.orbitz.com/">www.orbitz.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.sta-travel.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/sta-travel.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.sta-travel.com/">www.sta-travel.com</a>
	</td>
	</tr>
	<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.travelcuts.com/" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/travelcuts.jpg" border="0" width="322" height="95"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.travelcuts.com/">www.travelcuts.com</a>
	</td>
	</tr>	
</table>

<p>&nbsp;</p>
<p><a name="insurance"><h1>Insurance</h1></a></p>	
<p>&nbsp;</p>


<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.gomissiontrip.com" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/gomission.jpg" border="0" width="391" height="129"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.gomissiontrip.com/">www.gomissiontrip.com</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.missionaryhealth.net/shortterm.htm" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/missionhealth.jpg" border="0" width="300" height="67"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.missionaryhealth.net/shortterm.htm/">www.missionaryhealth.net/shortterm.htm</a>
	</td>
	</tr>
</table>
	
<p>&nbsp;</p>	
<p><a name="conferences"><h1>Conferences</h1></a></p>		
<p>&nbsp;</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.nstmc.org" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/nstmc.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.nstmc.org">National Short-Term Mission Conference</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.urbana.org" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/urbana.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.urbana.org">Urbana - Intervarsity Student Missions Conference</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.internationalministries.org/opportunities/Volunteers/discovery_teams/2003/index.htm" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/international.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.internationalministries.org/opportunities/Volunteers/discovery_teams/2003/index.htm">Short Term Mission Leader Training and Cross Cultural Orientation</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.stemintl.org/training/stmlc" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/stmlc.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.stemintl.org/training/stmlc">Minneapolis Short-Term Mission Leaders' Conference April 19–21, 2007</a>
	</td>
	</tr>
</table>

<p>&nbsp;</p>
<p><a name="resources"><h1>Other Resources</h1></a></p>		
<p>&nbsp;</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.missionfinder.org/training.htm#personal" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/missionfinder.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.missionfinder.org/training.htm#personal">Extensive List of Missions Training </a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.timshen.truepath.com/wmission.htm" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/training_pack.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.timshen.truepath.com/wmission.htm">Free World Mission Training Pack</a>
	</td>
	</tr>
</table>

<p>&nbsp;</p>
<p><a name="safety"><h1>Safety</h1></a></p>		
<p>&nbsp;</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.brotherhoodmutual.com/NAV-pages/navart72-MissionPossible.shtml" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/safety.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.brotherhoodmutual.com/NAV-pages/navart72-MissionPossible.shtml">Mission Possible: Planning a Safe Short-Term Mission Trip</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.mission2go.com/Adventure-Skills-Articles/travel-security-personal.htm" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/mission2go.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.mission2go.com/Adventure-Skills-Articles/travel-security-personal.htm">Personal Security</a>
	</td>
	</tr>
</table>

<p>&nbsp;</p>
<p><a name="fundraising"><h1>Fundraising Ideas</h1></a></p>		
<p>&nbsp;</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.internationalministries.org/opportunities/Volunteers/Volunteer%20Website/documents/FundraisingideafromSTMbook.pdf" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/international_m.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.internationalministries.org/opportunities/Volunteers/Volunteer%20Website/documents/FundraisingideafromSTMbook.pdf">Fundraising Ideas</a>
	</td>
	</tr>
</table>

<p>&nbsp;</p>
<p><a name="train"><h1>Training</h1></a></p>
<p>&nbsp;</p>

<table id="table1" border="0" cellpadding="0" cellspacing="0">

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://home.snu.edu/~hculbert/short.htm" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/nazarene.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://home.snu.edu/~hculbert/short.htm">Training from Southern Nazarene University</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.stmstandards.org/standards-overview.php" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/excellence.jpg" border="0" width="386" height="156"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.stmstandards.org/standards-overview.php">Standards of Excellence in Short-term Missions</a>
	</td>
	</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="615" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="130"><a href="http://www.dentaltrainingformissions.com" target="_blank">
	<img src="http://www.christianvolunteering.org/imgs/pic/dental.jpg" border="0" width="300" height="225"></a></td>
    <td height="152" width="288"><strong><em>

	<a href="http://www.dentaltrainingformissions.com">Dental Training for Missions</a>
	</td>
	</tr>
</table>
<% } %>
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
