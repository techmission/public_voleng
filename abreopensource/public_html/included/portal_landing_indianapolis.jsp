<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information --><head>

<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );
%>

<script language="javascript">
$(document).ready(function() {
	$('#queryLoc').val('Indianapolis, IN');
    $('#search_input').find('a:first').attr('href', '/oppsrch.do?method=processSolrSearch#fq=content_type:opportunity&fq=city_tax:%22Indianapolis,%20IN%22');
	$('#search_input').find('a:last').attr('href', '/oppsrch.do?method=processSolrSearch#fq=content_type:organization&fq=city_tax:%22Indianapolis,%20IN%22');
});
</script>

<!-- BEGIN MAINCONTENT -->


<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="num_types_in_facet">5</div>
	<div id="page_type">landingpage</div>

	<div id="keyword_search">city_tax:"Indianapolis, IN"</div>
	<div id="fq_search">city_tax:"Indianapolis, IN"</div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>

<div id="welcomebox" class="wide landingpage">
	<div style="float:left;"> <!-- img can have maximum width of about 485px; -->
		<img src="<%=aszPortal%>/imgs_chicago/Chicago_night_skyline1.gif" width="414" height="228" alt="Search short-term missions trips and volunteer opportunities in the Chicago area! Find opportunities in urban ministry, Christian internships, and church volunteering."  >
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">

	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		<div id="midbox1_content">
			<br>
			<p>
	<li class="filter handle expanded landingpage" id="facet_position_type">
	    <a class="handle" onClick="toggle_display('position_type')"><span class="label">Browse by Type</span></a>
		<ul id="position_type" class="tagcloud toggle comma" style="display:block;"></ul>
	</li>			
			</p>
			<h2><p>	Browse by Location</p></h2>
			<p>
              <ul>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Chicago,%20IL%22&fq=content_type:opportunity">Chicago</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22San%20Diego,%20CA%22&fq=content_type:opportunity">San Diego</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Boston,%20MA%22&fq=content_type:opportunity">Boston</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Houston,%20TX%22&fq=content_type:opportunity">Houston</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Denver,%20CO%22&fq=content_type:opportunity">Denver</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Washington,%20DC%22&fq=content_type:opportunity">Washington, DC</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=location:%22Los%22&fq=content_type:opportunity&fq=city_tax:%22Los%20Angeles,%20CA%22">Los Angeles</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=province_tax:%22New%20York%22&fq=content_type:opportunity">New York City</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Indianapolis,%20IN%22&fq=content_type:opportunity">Indianapolis</a></li>
                <li><a href="/oppsrch.do?method=processSolrSearch#fq=city_tax:%22Seattle,%20WA%22&fq=content_type:opportunity">Seattle</a></li>
              </ul>
			</p>
		</div>
		<img src="/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" />
	</div>


	<div id="midbox2" class="landingpage">
		<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
		<div id="midbox2_photo">
			<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content">
			<br>
			<p>
			<li class="filter handle expanded landingpage" id="facet_service_areas">
				<a class="handle" onClick="toggle_display('service_areas')"><span class="label">Browse by Service Area</span></a>
				<ul id="service_areas" class="tagcloud toggle comma" style="display:block;"></ul>
			</li>
			</p>
		</div>
		<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" />
	</div>


	<div id="midbox3" class="landingpage">
		<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
		<div id="midbox3_photo">
			<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html"><img src="<%=aszPortal%>/imgs/training_img.gif" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox3_photo-->  
		<div id="midbox3_content">
			<br>
	        <p>
			  <li class="filter handle expanded landingpage" id="facet_skills">
				<a class="handle" onClick="toggle_display('skills')"><span class="label">Browse by Skills / Career</span></a>
				<ul id="skills" class="tagcloud toggle comma" style="display:block;"></ul>
			  </li>		
			</p>
		</div>
		<img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
	</div>
</div>
<div class="cleardiv"></div>

<div id="endingbox" class="landingpage">
	<img src="http://www.christianvolunteering.org/imgs/endingbox_corner.gif" id="right_corner_endingbox"/>
	<div style="float:left;">
		<img src="/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities"/>
	</div>
	<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">
		<h2><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches">Organizations</a></h2>
		<p>Create an account for your organization to post Christian volunteer opportunities and recruit volunteers online. </p> 
		<p><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">Register to Recruit Christian Volunteers</a></p>
		<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers</a></p>
		
	</div>
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<div style="margin: 5px 20px 5px 20px;" align="left">
		<h2><p>ChristianVolunteering.org in the news:</p></h2>
		<p><a href="http://headlines.agapepress.org/archive/1/92007c.asp" title="TechMission Launches Online Christian Volunteer Opportunity Directory">TechMission Launches Online Christian Volunteer Opportunity Directory</a></p>
		<p><a href="http://www.christiannewswire.com/news/646341804.html" title="PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays">PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays</a></p>
		<p><a href="http://www.christiannewswire.com/news/453011207.html" title="PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers">PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers</a></p>
		<p><a href="http://www.wdcmedia.com/newsArticle.php?ID=2102" title="Taking Christian Volunteerism to the HighTech Level">Taking Christian Volunteerism to the HighTech Level</a></p>
		<p><a href="http://www.ydr.com/religion/ci_4515784">NEW WEB SITE: Find Christian Volunteer opportunities</a></p>
		<p><a href="http://www.breakingchristiannews.com/articles/display_art.html?ID=3151" title="New Website a Great Tool for Christian Volunteers and Organizations">New Website a Great Tool for Christian Volunteers and Organizations</a></p>
	</div>
</div>

<div class="cleardiv"></div>

<script type="text/javascript" src="/template_include/js/display_toggle_landingpage.js"></script>

<!-- start footer information -->
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
