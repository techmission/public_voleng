<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

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


<!-- BEGIN MAINCONTENTs -->
<% if (!((aszHostID.equalsIgnoreCase("volengesa")) || (aszHostID.equalsIgnoreCase("volengagrm"))) ) { %>
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>
<% } %>

<div id="welcomebox">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volengegc" )) ||
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
){ %>
<div id="welcomebox_photo">
<% }else{ %>
<div style="float:left;">
<% } %>
<img src="http://www.christianvolunteering.org/imgs/welcomebox_photo.jpg" alt="Christian Volunteering: Search to find opportunities in urban ministry, short term missions, and church volunteering." 
<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" )) ){ %>
	width="399" 
<% } else { %>
	width="417" 
<% } %>
<% if ( (aszHostID.equalsIgnoreCase( "volenggospel" )) || (aszHostID.equalsIgnoreCase( "volenggospelcom" )) ){ %> 
<% } else { %>
	height="227" 
<% } %>
/>
</div>

<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
<%@ include file="/template_include/home_jointsearch.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_search.inc" %>
<% } %>
</div>

<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>


<div class="cleardiv"></div>

<div id="midbox1">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
                (aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
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
){ %>
	<img src="http://www.christianvolunteering.org/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/shorttermmissions.jsp"><img src="http://www.christianvolunteering.org/imgs/volunteers.gif" alt="Search opportunities for church volunteers in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	  <A HREF="<%=aszPortal%>/shorttermmissions.jsp"><img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Search opportunities for church volunteers in urban ministry and short term missions." width="250" height="158" border="0"/><br clear="all"  /></A>
	<% } else { %>
	<A HREF="<%=aszPortal%>/shorttermmissions.jsp"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_photo.gif" alt="Search opportunities for church volunteers in urban ministry and short term missions."width="250" height="158" border="0"/></A> 
	<% } %>
<% } %>




 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
	   <br/>
 <h1>Volunteer in Disaster Relief</h1>
 <h3>Browse by Length of Mission Trip:</h3>

	 	<ul>

		<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&duration=Less+than+a+week&searchkey1=&Submit=Submit">

		Less than a week </a></li>

		<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&duration=1+to+2+weeks&searchkey1=&Submit=Submit">

		One to two weeks </a></li> 

		

		<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&duration=3+to+4+weeks&searchkey1=&Submit=Submit">

		Three to four weeks </a> </li>

			

		<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&duration=1+to+2+months&searchkey1=&Submit=Submit">

		One to two months </a> </li></ul>
		
		<h3>Browse by Region:</h3>
		

		<ul><li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&distance=City&country=US&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the United States">United States,</a>

<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=CA&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Canada">Canada</a></li>

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Africa&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Africa">AFRICA:</a>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=GH&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Ghana">Ghana,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=KE&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Kenya">Kenya,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=UG&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Uganda">Uganda,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=ZW&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Zimbabwe">Zimbabwe</a></li>	

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Asia&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Asia">ASIA:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=CN&Submit=Submit" title="Church Volunteer Short Term Missions Trips in China">China,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=IN&Submit=Submit" title="Church Volunteer Short Term Missions Trips in India">India,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=TH&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Thailand">Thailand,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=VN&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Vietnam">Vietnam</a></li>

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Asia+Pacific+and+Oceania&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Asia Pacific and Oceania">ASIA PACIFIC & OCEANIA</a></li>

		<!--<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=AU&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Australia">Australia,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=NZ&Submit=Submit" title="Church Volunteer Short Term Missions Trips in New Zealand">New Zealand,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=PH&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Philippines">Philippines</a>-->

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Caribbean&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Caribbean">CARIBBEAN:</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=DO&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Dominican Republic">Dominican Republic</a></li>
		
<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Europe&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Europe">EUROPE</a><!--

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=CZ&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Czech Republic">Czech Republic,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=HU&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Hungary">Hungary,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=RU&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Russian Federation">Russian Federation,</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&country=UA&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Ukraine">Ukraine,</a>	

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&country=GB&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the United Kingdom">United Kingdom</a>--></li>

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Latin+America&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Latin America">LATIN AMERICA:</a>

	    <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=CR&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Costa Rica">Costa Rica,</a>		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=MX&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Mexico">Mexico</a></li>

<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&region=Middle+East&Submit=Submit" title="Church Volunteer Short Term Missions Trips in the Middle East">MIDDLE EAST:</a>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions

		+%2F+Volunteer+Internship&country=KG&Submit=Submit" title="Church Volunteer Short Term Missions Trips in Kyrgyzstan">Kyrgyzstan</a></li></ul>
  </div>
	  
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>
<% } %>



<div id="midbox2">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
                (aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
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
		(aszHostID.equalsIgnoreCase( "volengtwincities" ))  ||
			(aszHostID.equalsIgnoreCase( "volengurbmin" ))
){ %>
<img src="http://www.christianvolunteering.org/imgs/organizationbox_top-wide.gif" width="315" height="12" />
<div id="midbox2_photo">
<a href="<%=aszPortal%>/advancedsearch.jsp"><img src="http://www.christianvolunteering.org/imgs/organizationbox.gif" alt="Search for church volunteer group opportunities and training resources to inspire small group service." width="295" height="156" alt="picture of church" border="0" /><br clear="all" /></a>
	</div><!-- end: midbox2_photo-->

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	<a href="<%=aszPortal%>/advancedsearch.jsp">
	<div id="midbox2_photo"><img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_photo_uywi.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" border="0"/></a></div>
	<% } else if(aszHostID.equalsIgnoreCase( "volengesa" )) { %>
	<a href="<%=aszPortal%>/advancedsearch.jsp"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo.gif" alt="Search for church volunteer group opportunities and training resources to inspire small group service." width="245" height="158" alt="picture of church" border="0" /><br clear="all" /></a>
	<% } else { %>
	<a href="<%=aszPortal%>/advancedsearch.jsp"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo.gif" alt="Search for church volunteer group opportunities and training resources to inspire small group service." width="253" height="158" alt="picture of church" border="0"/><br clear="all" /></a>
	<% } %>
<% } %>



  <div id="midbox2_content">
<br/>
	<h1>Church Volunteer Group Opportunities</h1>
<ol>
<li><a href="http://www.urbanministry.org/churchresources" title="Bible Studies, Sermons for Church Volunteers on Justice and Service">Get Free Materials on UrbanMinistry.org</a>.
<ul><li><a href="http://www.urbanministry.org/faceted_search/results/taxonomy:4700,4599">Sermon Outlines</a> and <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:113,4599">Bible Studies</a> on Justice & Service</li>
<li>Small group discussion materials (<a href="http://www.urbanministry.org/faceted_search/results/taxonomy:115">audio</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:116">video</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:120">books</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:113">Bible studies</a> &amp; <a href="http://www.urbanministry.org/wiki/encyclopedia-urban-ministry">articles</a>).</li></ul></li>
<li>Use materials in sermons and small group discussions. Get instructions on how to print, burn CDs or download videos.</li>
<li>Find group volunteer opportunities using the search box above.</li>
<li>Do group volunteer opportunities in small groups and have discussions to debrief the experience.</li>
<li>Repeat.</li></ol>
	  <br />
	  <br />
	  <br />
	  <br/>
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>
<% } else if(aszHostID.equalsIgnoreCase( "volengesa" )) { %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom.gif" width="245" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>
<% } %>


<div id="midbox3">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
                (aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
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
		(aszHostID.equalsIgnoreCase( "volengtwincities" ))||
			(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
<img src="http://www.christianvolunteering.org/imgs/trainingbox_top-wide.gif" width="309" height="12" />
<div id="midbox3_photo">
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/churches.gif" alt="Church Volunteer Management Training and Christian Volunteer Orientation"width="290" height="156" border="0"/><br clear="all" /></a>
</div><!-- end: midbox3_photo-->  

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
 <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_photo_uywi.gif" alt="Volunteer Management Training and Christian Volunteer Orientation" width="248" height="158" border="0"/><br clear="all" /></a>
	<% } else { %>
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/trainingbox_photo.gif" alt="Church Volunteer Management Training and Christian Volunteer Orientation"width="248" height="158" border="0"/><br clear="all" /></a>
	<% } %>
<% } %>


  
  <div id="midbox3_content">
	<br>
<h1>Church Volunteer Training</h1>
<ul><li><a href="http://www.urbanministry.org/wiki/webcast-workshops-volunteers" title="Church Volunteer Training Webcasts">Webcasts and Training Presentations for Church Volunteers</a></li>
<li><a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions" title="Church volunteer articles & resources">Volunteer Wiki Encyclopedia</a></li>
<li><a href="http://www.urbanministry.org/wiki/volunteer-management-presentation-webcasts" title="Volunteer Management Manual and Training for Church Volunteer Supervision">Volunteer Management Manual and Training</a></li>
<li><a href="http://www.urbanministry.org/faceted_search/results/taxonomy:120,92,4599" title="Volunteering Book Recommendations for Churches">Recommended Church Volunteer Books</a></li></ul>
<ul><li><a href="http://www.urbanministry.org/wiki/volunteer-toolkit-practical-equipment-effective-volunteer-management" title="Church Volunteer Management Toolkit">Church Volunteer Toolkit</a></li>
<li><a href="http://www.urbanministry.org/files/TMC_Volunteer_Coordinator_Manual.doc" title="Parachurch Volunteer Coordinator Training Manual">Parachurch Volunteer Toolkit</a></li>
<li><a href="http://www.urbanministry.org/wiki/mentoring-programs-toolkit-equipping-your-organization-effective-outreach" title="Church Volunteer Training for Effective Mentoring Programs">Mentoring Programs Toolkit</a></li></ul>
<ul><li><a href="http://www.urbanministry.org/spiritual_gifts">Spiritual Gifts Tests</a>, <a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling">Christian Calling</a></li>
	  <li><a href="http://www.urbanministry.org/wiki/increase-volunteerism-your-congregation">Increase Volunteerism in Your Congregation</a></li>
	  <li><a title="Training Steps for Church Volunteer Group Leaders in Connecting to Local Charities and Ministries" href="http://www.urbanministry.org/wiki/standards-excellence-volunteer-group-leaders">Church Volunteer Group Leader Training</a></li>
      <li><a href="http://www.urbanministry.org/wiki/hidden-benefits-helping">The Hidden Benefits of Helping</a></li>
	  <li><a href="http://www.urbanministry.org/wiki/devotionals" title="Devotionals for Church Volunteers">Devotionals on Volunteering</a></li>
	  <li><a href="http://www.urbanministry.org/wiki/standards-excellence-volunteers" title="Church Volunteer Standards of Excellence">Standards of Excellence for Volunteers</a></li></ul>
	  <br/>
	  <br/>
	  <br/>
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
  <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_bottom_uywi.gif" width="248" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>
<% } else { %>
  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>
<% } %>

<div id="endingbox">


<img id="right_corner_endingbox" src="http://www.christianvolunteering.org/imgs/endingbox_corner.gif"/>
<p>

<h1>Suggested Small Group Discussions</h1>
<table hspace="0" vspace="0" border="0" width="95%">

<tr><td class="head" colspan="2">Bible Studies</td>
<td class="head" colspan="2">Video</td>
<td class="head" colspan="2">Audio</td></tr>

<tr><td><a href="http://www.urbanministry.org/reconciliation-across-social-class"><img src="http://www.urbanministry.org/files/images/DrawMeCloser.thumbnail.jpg" border="0" width="100" height="75" alt="Jesus embracing a child" /></a></td><td><a href="http://www.urbanministry.org/reconciliation-across-social-class">Andrew Sears: Reconciliation Across Social Class</a></td>
<td><a href="http://www.urbanministry.org/full-urban-ministry-volunteer-orientation-training-volunteers-how-serve-well-0"><img src="http://www.urbanministry.org/files/images/full_orientation_0.jpg" border="0" width="100" height="75" alt="Church Volunteer Training: Volunteer Orientation Presentation on Service in Urban Ministries" /></a></td>
<td><a href="http://www.urbanministry.org/full-urban-ministry-volunteer-orientation-training-volunteers-how-serve-well-0">Full Urban Ministry Volunteer Orientation: Training for Church Volunteers on How to Serve Well</a></td>
<td><a href="http://www.urbanministry.org/rev-alvin-bibbs-and-ms-debbie-kreml-rediscovering-lost-art-leading-volunteer-driven-ministry-ccda-20"><img src="http://www.urbanministry.org/files/images/alvin-bibbs.jpg" alt="Rev. Alvin Bibbs & Debbie Kreml: Recovering the Lost Art of Leading a Volunteer-Driven Ministry" border="0" /></a></td><td><a href="http://www.urbanministry.org/rev-alvin-bibbs-and-ms-debbie-kreml-rediscovering-lost-art-leading-volunteer-driven-ministry-ccda-20">Rev. Alvin Bibbs & Debbie Kreml: Recovering the Lost Art of Leading a Volunteer-Driven Ministry</a></td></tr>

<tr><td><a href="http://www.urbanministry.org/why-should-we-preach-good-news-poor"><img src="http://www.urbanministry.org/files/images/paul-fritz.thumbnail.jpg" alt="Paul Fritz - Why Should We Preach the Good News to the Poor?" border="0" /></a></td><td><a href="http://www.urbanministry.org/why-should-we-preach-good-news-poor">Why Should We Preach the Good News to the Poor?</a></td>
<td><a href="http://www.urbanministry.org/dr-bob-lupton-saturday-evening-plenary-ccda-2000-video"><img src="http://www.urbanministry.org/files/images/bob_lupton.thumbnail.jpg" alt="Dr. Bob Lupton: Christian Community Development, Past and Future - CCDA 2000 plenary sermon" border="0" height="75" width="100" /></a></td><td><a href="http://www.urbanministry.org/dr-bob-lupton-saturday-evening-plenary-ccda-2000-video">Dr. Bob Lupton: Christian Community Development, Past and Future</a></td>
<td><a href="http://www.urbanministry.org/dr-john-perkins-ccda-101"><img src="http://www.urbanministry.org/files/images/johnp.thumbnail.jpg" alt="Dr. John Perkins: CCDA 101" border="0" /></a></td><td><a href="http://www.urbanministry.org/dr-john-perkins-ccda-101">Dr. John Perkins: CCDA 101</a></td></tr>

<tr><td><a href="http://www.urbanministry.org/how-church-should-respond-poverty"><img src="http://www.urbanministry.org/files/images/matt-gilchrist.thumbnail.gif" alt="Matt Gilchrist - How the Church Should Respond to Poverty" border="0" /></a></td><td><a href="http://www.urbanministry.org/how-church-should-respond-poverty">How the Church Should Respond to Poverty</a></td>
<td><a href="http://www.urbanministry.org/brennan-manning-kingdomworks-1999-video"><img src="http://www.urbanministry.org/files/images/brennan.thumbnail.jpg" alt="Brennan Manning: Being Cool in Christ Jesus, Kingdomworks 1999 plenary sermon" border="0" height="75" width="100" /></a></td><td><a href="http://www.urbanministry.org/brennan-manning-kingdomworks-1999-video">Brennan Manning: Being Cool in Christ Jesus</a></td>
<td><a href="http://www.urbanministry.org/dr-randy-white-equipping-college-students-transform-city-without-doing-harm-ccda-2006-audio"><img src="http://www.urbanministry.org/files/images/randy-white.jpg" border="0" alt="Dr. Randy White: Equipping College Students to Transform the City Without Doing Harm" /></a></td><td><a href="http://www.urbanministry.org/dr-randy-white-equipping-college-students-transform-city-without-doing-harm-ccda-2006-audio">Dr. Randy White: Equipping College Students to Transform the City Without Doing Harm</a></td></tr>

<tr><td><a href="http://www.urbanministry.org/mission-amazing-grace-seek-justice-rescue-oppressed"><img src="http://www.urbanministry.org/files/images/DavidReynolds10175.thumbnail.jpg" alt="R. David Reynolds: A Mission of Amazing Grace: Seek Justice, Rescue the Oppressed" border="0" /></a></td><td><a href="http://www.urbanministry.org/mission-amazing-grace-seek-justice-rescue-oppressed">A Mission of Amazing Grace: Seek Justice, Rescue the Oppressed</a></td>
<td><a href="http://www.urbanministry.org/dr-tony-evans-thursday-morning-plenary-ccda-2001-video"><img src="http://www.urbanministry.org/files/images/5_0.thumbnail.jpg" alt="Dr. Tony Evans: Jesus' Philosophy of Ministry - CCDA 2001 plenary sermon" border="0" height="75" width="100" /></a></td><td><a href="http://www.urbanministry.org/dr-tony-evans-thursday-morning-plenary-ccda-2001-video">Dr. Tony Evans: Jesus' Philosophy of Ministry</a></td>
<td><a href="http://www.urbanministry.org/reid-carpenter-bud-ipema-mobilizing-body-christ-working-together-unity-ccda-2005-audio"><img src="http://www.urbanministry.org/files/images/Reid.thumbnail.jpg" border="0" alt="Reid Carpenter & Bud Ipema: Mobilizing the Body of Christ - Working Together in Unity" /></a></td><td><a href="http://www.urbanministry.org/reid-carpenter-bud-ipema-mobilizing-body-christ-working-together-unity-ccda-2005-audio">Reid Carpenter & Bud Ipema: Mobilizing the Body of Christ - Working Together in Unity</a></td></tr>
</table>

<!--<div class="cleardiv"></div>-->
</div>

<% if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>
<div style="float:left;" id="ending_pic"><!--<img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="17" height="197" />--></div>
<% } else if(aszHostID.equalsIgnoreCase( "voleng1" ) ) { %>
<% } else { %>
<div style="float:left;" id="ending_pic"><!--<img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="21" height="197" />--></div>
<% } %>

  
<div class="cleardiv"></div>


<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
