<!-- start JSP information -->

<%@ include file="/template_include/topjspnologin1.inc" %>

<!-- end JSP information -->



<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>



<title>Christian Gap Year: Short-Term Missions & Christian Urban Internships Search: ChristianVolunteering.org</title>



<% } else { %>

<% } %>



<% if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>

<% } else { %>



<% /*if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ */%>



<!-- start wide header information -->

<%@ include file="/template/home_header.inc" %>

<!-- end wide header information -->



<% //} else { %>



<!-- start standard header information -->

<%//@ include file="/template/virtual_home_header.inc" - commented out b/c it made the page too big and crashed it %>

<!-- end standard header information -->



<% //} %>



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

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );



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



<div id="welcomebox" class="wide">
<div id="welcomebox_photo" class="beige" >
<img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Search over 1,000 short-term missions trips and volunteer opportunities for your Christian gap year! Find gap year opportunities in urban missions, Christian internships, and church volunteering." width="417" height="227" />

		</div>

<div style="float:right;">

<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase("volengurbmin"))
){ %>
<%@ include file="/template_include/home_gapyearsearch.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_search.inc" %>
<% } %>
</div>
</div>


<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>





<div class="cleardiv"></div>



<div id="midbox1">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

  <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Search thousands of Christian gap year urban missions trips." width="250" height="158" border="0"/><br clear="all"  /></A>

<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteerbox_photo-wide.gif" alt="Search thousands of Christian gap year trips."width="313" height="158" border="0"/><br clear="all"  /></A>

<% } else { %>

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteerbox_photo.gif" alt="Search thousands of Christian gap year trips."width="250" height="158" border="0"/><br clear="all"  /></A>

<% } %>



 <!--  <br clear="all"  /> -->

  <div id="midbox1_content">

  <br>

	 <h2><p>Browse by Type of <br/>Christian Gap Year Trip</p></h2>



	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&greatforgroup=4793&Submit=Submit" title="For Group Christian Gap Year Missions Trips and Volunteer Internships">Christian Gap Year Trips for Groups</A> </p>

	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Christian Gap Year Missions Trips and Volunteer Internships for individuals">Christian Gap Year Trips for Individuals</A></p>

	 

	 <h2><p>Browse by Length of <br/>Christian Gap Year Trip</p></h2>

	 	<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8091&searchkey1=&Submit=Submit">

		Less than a week </a><br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8094&searchkey1=&Submit=Submit">

		One to two weeks </a><br> 

		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8095&searchkey1=&Submit=Submit">

		Three to four weeks </a> <br>

			

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8096&searchkey1=&Submit=Submit">

		One to two months </a> <br>

		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8097&searchkey1=&Submit=Submit">

		Three to five months </a> <br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=8099&searchkey1=&Submit=Submit">

		One to two years </a> <br>

		</p>

							  </div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>

<% } else { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>

<% } %>







<div id="midbox2">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/uywi/organizationbox_photo_uywi.gif" alt="Search over 1,000 Christian gap year missions trips and volunteer opportunities! Find gap year opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" border="0"/><br clear="all" /></a>

<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Search over 1,000 Christian gap year  missions trips and volunteer opportunities! Find gap year opportunities in urban missions, Christian internships, and church volunteering." width="245" height="158" alt="picture of church" border="0"/></a>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>



<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo-wide.gif" alt="Search over 1,000 Christian gap year missions trips and volunteer opportunities! Find gap year opportunities in urban missions, Christian internships, and church volunteering." width="316" height="168" alt="picture of church" border="0" /></a>

<% } else { %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Search over 1,000 Christian gap year missions trips and volunteer opportunities! Find gap year opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" alt="picture of church" border="0"/></a>

<% } %>







  <div id="midbox2_content">

<br>

<!-- ead 10.5.07 <h2><p>

Browse by Region</p></h2>

		<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&country=US&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the United States">United States</a><br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Africa&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Africa">Africa</a><br>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=Uganda&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Africa">Uganda,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=Kenya&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Africa">Kenya</a></p>			

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Asia&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Asia">Asia</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Caribbean&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in the Caribbean">Caribbean</a>	</p>		

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Europe&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Europe">Europe</a>	</p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Latin+America&Submit=Submit" title="Short Term Missions Trips and Volunteer Internships in Latin America">Latin America</a>	</p>

	  <br />

	  <br /> -->

<h2><p>

Browse by Region</p></h2>

		<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&distance=City&country=US&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the United States">United States,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CA&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Canada">Canada</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Africa&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Africa">AFRICA:</a>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=GH&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Ghana">Ghana,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=KE&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Kenya">Kenya,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=UG&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Uganda">Uganda,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=ZW&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Zimbabwe">Zimbabwe</a></p>			

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Asia&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Asia">ASIA:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CN&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in China">China,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=IN&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in India">India,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=TH&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Thailand">Thailand,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=VN&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Viet Nam">Viet Nam</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Asia+Pacific+and+Oceania&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Asia Pacific and Oceania">ASIA PACIFIC & OCEANIA:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=AU&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Australia">Australia,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=NZ&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in New Zealand">New Zealand,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=PH&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Philippines">Philippines</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Caribbean&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Caribbean">CARIBBEAN:</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=DO&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Dominican Republic">Dominican Republic</a></p>

        <p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Europe&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Europe">EUROPE:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CZ&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Czech Republic">Czech Republic,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=HU&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Hungary">Hungary,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=RU&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Russian Federation">Russian Federation,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=UA&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Ukraine">Ukraine,</a>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=GB&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the United Kingdom">United Kingdom</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Latin+America&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Latin America">LATIN AMERICA:</a>

	    <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=CR&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Costa Rica">Costa Rica,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=MX&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Mexico">Mexico</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=Middle+East&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in the Middle East">MIDDLE EAST:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&country=KG&Submit=Submit" title="Christian Gap Year Trips and Volunteer Internships in Kyrgyzstan">Kyrgyzstan</a></p>

	  <br />

	  <br />
	  

	</div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>

<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

	

<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>

<% } else { %>

	  <img src="<%=aszPortal%>/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>

<% } %>





<div id="midbox3">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

 <img src="<%=aszPortal%>/imgs/uywi/trainingbox_photo_uywi.gif" alt="Short Term Missions Training and Orientation" width="248" height="158" border="0"/><br clear="all" /></a>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">

<img src="<%=aszPortal%>/imgs/trainingbox_photo-wide.gif" alt="Christian Gap Year Missions Training and Orientation"width="316" height="168" border="0"/><br clear="all" /></a>

<% } else { %>

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">

<img src="<%=aszPortal%>/imgs/trainingbox_photo.gif" alt="Christian Gap Year Missions Training and Orientation"width="248" height="158" border="0"/><br clear="all" /></a>

<% } %>

  

  <div id="midbox3_content">

	<br>

	<h2><p>	Browse by Service Area / Skill</p></h2>

	<p>

	<A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&postalcode=&distance=City&category1=Children+and+Youth&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Children and Youth volunteering">

	Children and Youth</a></p>

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&postalcode=&distance=City&category1=Engineering+and+Construction&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Construction volunteering">

	Engineering and Construction for Christian Gap Year</A> </p>

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&postalcode=&distance=City&category1=Homelessness+and+Housing&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Homelessness and Housing volunteering">

	Homelessness and Housing</A></p>

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&postalcode=&distance=City&category1=Education+and+Literacy&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Education and Literacy volunteering">

	Education and Literacy</A></p>

	<p>

	<a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+/+Volunteer+Internship&region=&category1=Health+and+Medicine&imageField.x=39&imageField.y=11">

	Medical Christian Gap Year Trips</a></p>

	<p>

	<A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796City&category1=Sports+and+Recreation&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Sports and Recreation volunteering">

	Sports and Recreation for Christian Gap Year</A></p>

	<!--<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796City&category1=Visitation+%2F+Friendship&Submit=Submit">

	Visitation and Friendship</A></p>-->

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&skills1=Computer+%2F+Tech+Support&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Computer or Tech Support volunteering">

	Computer / Tech Support for Christian Gap Year</A></p>

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&skills1=Fundraiser&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Fundraiser volunteering">

	Fundraiser</A></p>

	<p><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&skills1=Teacher+%2F+Trainer&Submit=Submit" title="Find Christian Gap Year Missions Trips and Volunteer Internships in Teacher or Trainer volunteering">

	Teacher / Trainer</A></p>



  </div>

	

	

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

  </div>



<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

  <img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>



  <% } else { %>

    <img src="<%=aszPortal%>/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>



    <% } %>



</p>



<div id="endingbox">

<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, Christian gap year, short term missions, and virtual volunteer opportunities" /></div>

<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">

  <p>

  

  <h2><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="urban ministries, Christian gap year urban missions organizations, and churches">

	Organizations</a></h2>  </p>

	

	<p>Directory of thousands of <strong>Christian gap year</strong> missions and volunteer opportunities. Create an account for your organization to post <strong>Christian gap year</strong> missions and internship opportunities to recruit volunteers online. </p> 

	<p><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">

	Register to Recruit Volunteers</a></p>

	<p><a href="<%=aszPortal%>/register.do?method=showlogin" title="login">

	Login</a></p>

<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers and Volunteers</a></p>	



</div>

<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<div style="margin: 5px 20px 5px 20px;" align="left">

	

	<%@ include file="/jsprsscron/chrisvolnewsfeeds3top.php" %></div>

	

<% } else { %>

<% } %>

<!--<div class="cleardiv"></div>-->



<!--<div class="cleardiv"></div>-->

</div>



<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>






<% } else if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="17" height="197" /></div>

<% } else { %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>

<% } %>





  

<div class="cleardiv"></div>



<% } %>



<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->
