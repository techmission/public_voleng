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
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38;

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
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );


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
<div id="shadowstrip" 
<% if ( !(
        (aszHostID.equalsIgnoreCase("volengcityreaching")) ||
	(aszHostID.equalsIgnoreCase("volengmissionamerica")) ||
	(aszHostID.equalsIgnoreCase("volengsaddleback")) 
) ) { %>
style="clear: both; background:url('<%=request.getContextPath()%>/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px"
<% } %>
>&nbsp;</div>
<% } %>

<div id="welcomebox" class="beige">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
	        (aszHostID.equalsIgnoreCase("volengcityreaching")) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volengegc" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengmissionamerica" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsaddleback" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
<div id="welcomebox_photo" class="beige">
<% }else{ %>
<div style="float:left;">
<% } %>
<img src="<%=request.getContextPath()%>/imgs/welcomebox_photo.jpg" alt="Christian Volunteering: Search to find opportunities in urban ministry, short term missions, and church volunteering." 
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
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
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
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
<%@ include file="/template_include/home_jointsearch.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_search.inc" %>
<% } %>
</div>

<div id="welcomebox_end_img" style="float:left;">
<% if ( 
	        (aszHostID.equalsIgnoreCase("volengcityreaching")) ||
		(aszHostID.equalsIgnoreCase( "volengmissionamerica" )) ||
		(aszHostID.equalsIgnoreCase("volengsaddleback")) 
) { %>
<% }else{ %>
<img src="<%=request.getContextPath()%>/imgs/welcomebox_right_trans.gif" width="17" height="227" />
<% } %>
</div>


<div class="cleardiv"></div>

<div id="midbox1">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
	<img src="<%=request.getContextPath()%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
	<div id="midbox1_photo">
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	  <A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Here you can find Christian volunteer opportunities in urban ministry and short term missions." width="250" height="158" border="0"/><br clear="all"  /></A>
	<% } else { %>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/volunteerbox_photo.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="250" height="158" border="0"/></A> 
	<% } %>
<% } %>




 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
	  <p><A HREF="<%=request.getContextPath()%>/search.jsp"><img src="<%=request.getContextPath()%>/imgs/volunteerbox_title.gif" border="0" alt="Register Christian Volunteer Opportunities or Short Term Missions" width="90" height="27" title="Christian volunteers,church volunteers, short term urban missionaries, virtual volunteers, and urban ministry volunteers start here"/></A></p>
	  <p>ChristianVolunteering.org matches volunteers to volunteer opportunities similar to how job sites like Monster.com work, but for volunteering.
	  <ol>
        <li><a href="<%=request.getContextPath()%>/advancedsearch.jsp" alt="Discover Christian Volunteer Opportunities" title="Discover search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities"> Search for Christian Volunteer Opportunities</a>, <a href="<%=request.getContextPath()%>/shorttermmissions.jsp" alt="Discover Christian Virtual Volunteer Opportunities" title="Short Term Missions Trips">Short Term Missions Trips</a> and <a href="<%=request.getContextPath()%>/virtualvolunteer.jsp" title="Christian Virtual Volunteering"> Virtual Volunteering</a> opportunities</li>
        <li><a href="<%=request.getContextPath()%>/individualregistration.jsp" title="Register to find Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" alt="Sign up Here for Christian Volunteer Opportunities"><strong>Register for a free account </strong></a></li>
        <li>Click &quot;I want to volunteer&quot; to be matched  with the organization by E-mail. </li>
      </ol>
      </p>

		<!--<p><a href="<%=request.getContextPath()%>/login.jsp" alt="login" title="login">
		Login</a> or 
		<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" alt="Manage Personal Account" title="Manage Personal Christian Volunteer and Church Volunteer Account">
		Manage Your Account</a></p>-->
			<p><a href="<%=request.getContextPath()%>/organizationlistings.jsp" alt="View All Christian Volunteer Listings" title="View All Christian Volunteer, Short Term Urban Missions, Virtual Volunteer, and Urban Ministry Service Listings"></a></p>
  </div>
	  
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
	  <img src="<%=request.getContextPath()%>/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="<%=request.getContextPath()%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>
<% } else { %>
	  <img src="<%=request.getContextPath()%>/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>
<% } %>



<div id="midbox2">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" ))  ||
			(aszHostID.equalsIgnoreCase( "volengurbmin" ))
){ %>
<img src="<%=request.getContextPath()%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
<div id="midbox2_photo">
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="<%=request.getContextPath()%>/imgs/organizationbox.gif" alt="Organizations can recruit Christian volunteers here." width="295" height="156" alt="picture of church" border="0" /><br clear="all" /></a>
	</div><!-- end: midbox2_photo-->

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
	<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	<div id="midbox2_photo"><img src="<%=request.getContextPath()%>/imgs/uywi/organizationbox_photo_uywi.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" border="0"/></a></div>
	<% } else if(aszHostID.equalsIgnoreCase( "volengesa" )) { %>
	<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="<%=request.getContextPath()%>/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="245" height="158" alt="picture of church" border="0" /><br clear="all" /></a>
	<% } else { %>
	<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="<%=request.getContextPath()%>/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" alt="picture of church" border="0"/><br clear="all" /></a>
	<% } %>
<% } %>



  <div id="midbox2_content">
	<p><A HREF="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	<img src="<%=request.getContextPath()%>/imgs/organizationbox_title.gif" border="0" alt="nonprofit organizations, urban ministries, short term urban missions organizations, and churches" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches" width="116" height="27" /></A></p>
	<p>You can <strong>start </strong><b>recruiting volunteers online for free in less than 5 minutes</b>.  
	<ol>
	  <li><a href="<%=request.getContextPath()%>/individualregistration.jsp" title="create Christian volunteer or short term missions trips">Create a free account</a></li>
      <li><a href="<%=request.getContextPath()%>/login.jsp" alt="Login Volunteer Recruitment Account" title="Login Christian Volunteer Recruitment Account">Login to start to recruit volunteers</a></li>
	  <li><a href="<%=request.getContextPath()%>/org.do?method=showCreateOrgStep1">Enter organization information</a> after logging in.</li>
	  <li><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" title="Post volunteer opportunities and short term missions trips">Post Volunteer Opportunities</a> after entering organization information. </li>
	  <li><A href="<%=request.getContextPath()%>/searchform.jsp" alt="Christian Volunteers can search from your website!" title="Volunteers can search from your website!">Put ChristianVolunteering.org search form on your website</A> (optional) </li>
	</ol>
	
	<br />
	  <br />
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
	  <img src="<%=request.getContextPath()%>/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="<%=request.getContextPath()%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>
<% } else if(aszHostID.equalsIgnoreCase( "volengesa" )) { %>
	  <img src="<%=request.getContextPath()%>/imgs/organizationbox_bottom.gif" width="245" height="12" /></div>
<% } else { %>
	  <img src="<%=request.getContextPath()%>/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>
<% } %>


<div id="midbox3">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" ))||
			(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
<img src="<%=request.getContextPath()%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
<div id="midbox3_photo">
<a href="http://www.churchvolunteering.org">
<img src="<%=request.getContextPath()%>/imgs/churches.gif" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
</div><!-- end: midbox3_photo-->  

<% }else{ %>
	<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" )){ %>
 <img src="<%=request.getContextPath()%>/imgs/uywi/trainingbox_photo_uywi.gif" alt="Church Volunteer Resources and Search" width="248" height="158" border="0"/><br clear="all" /></a>
	<% } else { %>
<a href="http://www.churchvolunteering.org">
<img src="<%=request.getContextPath()%>/imgs/trainingbox_photo.gif" alt="Church Volunteer Resources and Search"width="248" height="158" border="0"/><br clear="all" /></a>
	<% } %>
<% } %>


  
  <div id="midbox3_content">
	<p><a href="http://www.churchvolunteering.org">
	<% if (aszHostID.equalsIgnoreCase("volengcatholic")) { %>
	<img src="<%=request.getContextPath()%>/imgs_catholic/parishes.gif" border="0" alt="Christian volunteer training" title="Christian Volunteer Recruitment Training, Church Volunteer Management Training, Volunteer Orientation" width="85" height="27" /><% } else{ %> <img src="<%=request.getContextPath()%>/imgs/trainingbox_title.gif" border="0" alt="Christian volunteer training" title="Christian Volunteer Recruitment Training, Church Volunteer Management Training, Volunteer Orientation" width="85" height="27" /><% } %></a></p>

	
	<ul>
<li><a href="http://www.churchvolunteering.org">Church Volunteer Resources</a></li>
	  <li><a href="http://www.urbanministry.org/faceted_search/results/taxonomy:4700,4599">Sermon Outlines</a> and <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:113,4599">Bible Studies</a><br/> on Justice & Service</li>
	  <li>Small group discussion materials (<a href="http://www.urbanministry.org/faceted_search/results/taxonomy:115">audio</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:116">video</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:120">books</a>, <a href="http://www.urbanministry.org/faceted_search/results/taxonomy:113">Bible studies</a> &amp; <a href="http://www.urbanministry.org/wiki/encyclopedia-urban-ministry">articles</a>).</li>
	  <li><a href="http://www.urbanministry.org/wiki/volunteer-toolkit-practical-equipment-effective-volunteer-management" title="Church Volunteer Management Toolkit">Church Volunteer Toolkit</a></li>
<li><a href="http://www.urbanministry.org/spiritual_gifts">Spiritual Gifts Tests</a>, <a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling">Personality Tests, Christian Calling and Vocation</a></li>
	  <li><a href="<%=request.getContextPath()%>/churchinstructions.jsp">Add Volunteer Opportunities<br/> to Your Church Website</a></li>
	</ul>
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volenguywi" ) ){ %>
  <img src="<%=request.getContextPath()%>/imgs/uywi/trainingbox_bottom_uywi.gif" width="248" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
  <img src="<%=request.getContextPath()%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>
<% } else { %>
  <img src="<%=request.getContextPath()%>/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>
<% } %>

<div id="endingbox">
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
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
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" ))||
			(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>

<img id="right_corner_endingbox" src="<%=request.getContextPath()%>/imgs/endingbox_corner.gif"/>
	<h1>Christian Volunteering Resources</h1>
	<%@ include file="/jsprsscron/volunteering_view.htm" %>
<% } else { %>
<% } %>
<!--<div class="cleardiv"></div>-->
</div>

<% if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>
<div style="float:left;" id="ending_pic"><!--<img src="<%=request.getContextPath()%>/imgs/endingbox_right_clr.gif" width="17" height="197" />--></div>
<% } else if(aszHostID.equalsIgnoreCase( "voleng1" ) ) { %>
<% } else { %>
<div style="float:left;" id="ending_pic"><!--<img src="<%=request.getContextPath()%>/imgs/endingbox_right_clr.gif" width="21" height="197" />--></div>
<% } %>

  
<div class="cleardiv"></div>


<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
