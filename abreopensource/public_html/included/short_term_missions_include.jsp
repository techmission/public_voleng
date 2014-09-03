<!-- start JSP information -->

<%@ include file="/template_include/topjspnologin1.inc" %>

<!-- end JSP information -->



<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Short-Term Missions Search & Christian Ministry Internships: ChristianVolunteering.org</title>

<% } else { %>

<% } %>



<!-- start wide header information -->

<%@ include file="/template/home_header.inc" %>

<!-- end wide header information -->


<!-- start navigation information -->

<%@ include file="/template/navigation.inc" %>

<!-- end navigation information -->



<% 
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidTripLength=263, vidRoomBoard=265, vidPosFreq=268, vidSchedDate=269, vidBenefits=286;


ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList aLanguageList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );
aCodes.getAppCodeList( aLocalAffilList, 175 );

int iSiteChrisVolTID = 25133;
int iSiteID=iSiteChrisVolTID;


ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );



String ua = request.getHeader( "User-Agent" );

boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );

boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );

response.setHeader( "Vary", "User-Agent" );

%>


<!-- BEGIN MAINCONTENT -->

<% if(aszHostID.equalsIgnoreCase( "volengagrm" ) ){ %>
</head>


<div style="clear: both; background:url('<%=aszPortal%>/imgs/home_shadowstrip_clr.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>

<% } else { %>

<div style="clear: both; background:url('<%=aszPortal%>/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>

<% } %>



<div id="welcomebox" class="shorttermmissionspage">

<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Search over 5,000 short-term missions trips and volunteer opportunities! Find opportunities in urban ministry, Christian internships, and church volunteering."  
<% if ( aszNarrowTheme=="true" ){ %>
	width="399" 
<% } else { %>
	width="417" 
<% } %>
height="234" />
</div>

<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
        (aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengurbmin"))
){ %>
<%@ include file="/template_include/home_jointsearch.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_search.inc" %>
<% } %>
</div>


<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>





<div class="cleardiv"></div>



<div id="midbox1">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) 
	//|| aszHostID.equalsIgnoreCase( "volengworldvision" ) 
){ %>

  <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Search thousands of short term missions trips and Christian ministry internships." width="250" height="158" border="0"/><br clear="all"  /></A>
  
  <% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>

 <img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="249"/>
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/orphanage_nepal2.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="229" style="margin-left:13px;" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->

<% } else if( aszNarrowTheme=="true" ) { %>

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteerbox_photo.gif" alt="Search thousands of short term missions trips and Christian ministry internships."width="250" height="158" border="0"/><br clear="all"  /></A>

<% } else { %>

 <img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/orphanage_nepal2.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->

<% } %>



 <!--  <br clear="all"  /> -->

  <div id="midbox1_content" style="height: 400px;">

  <br>

	 <h2 class="head"><p>Summer Ministry Internships</p></h2>
	 
	 <h2><p>Service Area</p></h2>

	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&orgaffil1tid=717&Submit=Submit" title="Rescue Mission Summer Ministry Internships">Rescue Missions</a> <br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4763&Submit=Submit" title="Children and Youth Work Summer Ministry Internships">Children &amp; Youth</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4765&Submit=Submit" title="Community Development Summer Ministry Internships">Community Development</a><br/>

	 <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4775&Submit=Submit" title="Health and Medicine Summer Ministry Internships">Health &amp; Medicine</a><br/>
	 
	 <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4785&Submit=Submit" title="Teaching Summer Ministry Internships">Teaching</a></p>

	 <h2><p>Location</p></h2>

	 	<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&regiontid=8418&Submit=Submit">

		East Coast U.S. </a><br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&regiontid=8418&Submit=Submit">

		West Coast U.S. </a><br> 

		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&regiontid=8418&Submit=Submit">

		Southern U.S. </a> <br>

			

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&regiontid=8418&Submit=Submit">

		Central U.S. </a> </p>

	<h2><p>Benefits Offered</p></h2>
	
	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&roomboard=11546&Submit=Submit">Room &amp; Board</a><br/>
	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&stipend=11547&Submit=Submit">Stipend</a></p>
		

							  </div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) 
	//|| aszHostID.equalsIgnoreCase( "volengworldvision" ) 
){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>

<% } else if( aszNarrowTheme=="true" ) { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>

<% } else { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>

<% } %>







<div id="midbox2">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" )
        //|| aszHostID.equalsIgnoreCase( "volengworldvision" )
){ %>

<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/uywi/organizationbox_photo_uywi.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" border="0"/><br clear="all" /></a>

<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>

 <img src="<%=aszPortal%>/imgs/organizationbox_top.gif" width="245"/>
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/Africa 468 (rcasenhiser).jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="225" style="margin-left:13px;" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox2_photo-->


<% } else if( aszNarrowTheme=="true" ) { %>

<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" alt="picture of church" border="0"/></a>

<% } else { %>

 <img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="312" height="12" />
	<div id="midbox2_photo">
<A HREF="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/Africa 468 (rcasenhiser).jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox2_photo-->

<% } %>







  <div id="midbox2_content" style="height: 400px;">
<br/>
<h2 class="head"><p>

Short Term Missions</p></h2>

<h2><p>Region</p></h2>

		<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&country=US&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the United States">United States,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CA&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Canada">Canada</a><br/>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4948&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Africa">AFRICA:</a>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=GH&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Ghana">Ghana,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=KE&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Kenya">Kenya,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=UG&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Uganda">Uganda,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=ZW&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Zimbabwe">Zimbabwe</a><br/>			

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4947&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Asia">ASIA:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CN&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in China">China,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=IN&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in India">India,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=TH&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Thailand">Thailand,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=VN&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Vietnam">Vietnam</a><br/>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4949&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Asia Pacific and Oceania">ASIA PACIFIC & OCEANIA:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=AU&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Australia">Australia</a>
<br/>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4945&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the Caribbean">CARIBBEAN:</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=DO&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the Dominican Republic">Dominican Republic</a><br/>

        <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4946&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Europe">EUROPE:</a>

				<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=RU&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the Russian Federation">Russian Federation,</a>


		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=UK&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the United Kingdom">United Kingdom</a><br/>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4944&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Latin America">LATIN AMERICA:</a>

	    <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CR&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Costa Rica">Costa Rica,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=MX&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Mexico">Mexico</a><br/>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&regiontid=4950&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the Middle East">MIDDLE EAST:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=KG&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Kyrgyzstan">Kyrgyzstan</a></p>

<h2><p>Service Area / Skill</p></h2>

	<p>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&servicearea1=4763&Submit=Submit" title="Find Short Term Missions Trips and Volunteer Internships in Children and Youth volunteering">

	Children and Youth</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&servicearea1=4776&Submit=Submit" title="Find Short Term Missions Trips and Volunteer Internships in Homelessness and Housing volunteering">

	Homelessness and Housing</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&servicearea1=4768&Submit=Submit" title="Find Short Term Missions Trips and Volunteer Internships in Education and Literacy volunteering">

	Education and Literacy</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=&servicearea1=4775&Submit=Submit">

	Medical Missions Trips</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&skills1id=4728&Submit=Submit" title="Find Short Term Missions Trips and Volunteer Internships in Computer or Tech Support volunteering">

	Computer / Tech Support in Missions</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&skills1id=4740&Submit=Submit" title="Find Short Term Missions Trips and Volunteer Internships in Fundraiser volunteering">

	Fundraiser</a><br/>

	
<h2><p>Benefits Offered</p></h2>

<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&roomboard=11546&searchkey1=&Submit=Submit">Room and Board</a><br/>

<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&stipend=11547&searchkey1=&Submit=Submit">Stipend</a></p>
	  <br />

	  <br />
	  

	</div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" )
        //|| aszHostID.equalsIgnoreCase( "volengworldvision" )
){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>

<% } else if( aszNarrowTheme=="true" ) { %>

	  <img src="<%=aszPortal%>/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>

<% } else { %>

<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>
<% } %>





<div id="midbox3">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" )
        //|| aszHostID.equalsIgnoreCase( "volengworldvision" )
){ %>

 <img src="<%=aszPortal%>/imgs/uywi/trainingbox_photo_uywi.gif" alt="Short Term Missions Training and Orientation" width="248" height="158" border="0"/><br clear="all" /></a>
 
 <% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>

 <img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="245"/>
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/advancedsearch.jsp"><img src="<%=aszPortal%>/imgs/missions_westafrica.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="225" style="margin-left:13px;" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox3_photo-->

<% } else if( aszNarrowTheme=="true" ) { %>

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">

<img src="<%=aszPortal%>/imgs/trainingbox_photo.gif" alt="Short Term Missions Training and Orientation"width="248" height="158" border="0"/><br clear="all" /></a>

<% } else { %>

<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
<div id="midbox3_photo">
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html">
<img src="<%=aszPortal%>/imgs/missions_westafrica.jpg" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
</div><!-- end: midbox3_photo-->  
<% } %>

  

  <div id="midbox3_content" style="height: 400px;">
<br/>
	<h2 class="head"><p>One to Three Year<br/> Ministry Internships</p></h2>
	
	<h2><p>Length<p></h2>
	
	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=one+year&searchkey1=&Submit=Submit">One year</a><br />
		
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=1+to+2+years&searchkey1=&Submit=Submit">

		One to two years </a> <br />
		
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=2+to+3+years&searchkey1=&Submit=Submit">

		Two to three years </a></p>
		
	
	<h2><p>Benefits Offered</p></h2>

<p><a href="http://www.urbanministry.org/volunteer/mapshortterm?filter0[]=11546">Room and Board<br/>

<a href="http://www.urbanministry.org/volunteer/mapshortterm?filter1[]=11547">Stipend</a></p>

	


  </div>

	

	

<% if(aszHostID.equalsIgnoreCase( "volenguywi" )
        //|| aszHostID.equalsIgnoreCase( "volengworldvision" )
){ %>

  </div>


<% } else if( aszNarrowTheme=="true" ) { %>

    <img src="<%=aszPortal%>/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>

  <% } else { %>
  <img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>

    <% } %>



</p>



<div id="endingbox">
<img src="http://www.christianvolunteering.org/imgs/endingbox_corner.gif" id="right_corner_endingbox"/>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities" /></div>

<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">

  <p>

  

  <h2 style="margin-left:0;"><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="urban ministries, short term urban missions organizations, and churches">

	For Organizations Posting Opportunities</a></h2>  </p>

	

	<p>Create an account for your organization to post Christian <strong>short term missions </strong>and ministry internship opportunities to recruit volunteers online. </p> 

	<p><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">

	Register to Recruit Volunteers</a></p>

	<p><a href="<%=aszPortal%>/register.do?method=showlogin" title="login">

	Login</a></p>

<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers and Volunteers</a></p>	

</div>

<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<div style="margin: 5px 20px 5px 20px;" align="left">
<h2>Short Term Missions and Christian Ministry Internship Training Resources</h2>
<p><a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/Presentation_Files/index.html">How to Find a Volunteer Placement and Serve Well</a> (<a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/Presentation_Files/index.html">Webcast</a>) (<a href="http://www.christianvolunteering.org/files/vol_orientation_online.ppt">PPT</a>)</p>
<p><a href="http://www.urbanministry.org/tag/short_term_missions?theme=churchvol">Short Term Missions Books/Media</a></p>
<p><a href="http://www.urbanministry.org/tag/short_term_missions?theme=churchvol">Short Term Missions Videos</a></p>
<p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A117?theme=churchvol">Short Term Missions Documents</a></p>
<p><a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions?theme=churchvol">Short Term Missions Wiki Encyclopedia</a></p>
<p><a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling?theme=churchvol">Personality, Vocation, and Calling</a></p>
<p><a href="http://www.urbanministry.org/spiritual_gifts?theme=churchvol">Spiritual Gifts Resources</a></p>


</div>

	

<% } else { %>

<% } %>

<!--<div class="cleardiv"></div>-->



<!--<div class="cleardiv"></div>-->

</div>



<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>






<% } else if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="17" height="197" /></div>

<% } else { /* %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>

<% */ } %>





  

<div class="cleardiv"></div>




<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->
