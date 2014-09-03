<%@ include file="/template_include/topjspnologin1.inc" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="catholic, catholic volunteer, catholic missions, catholic missions trip, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">

<meta name="description" content="Search thousands of Catholic short term missions trips and volunteer opportunities! Find opportunities in medical missions, orphanages, urban ministries, Catholic internships, and parish volunteering.">

<title>Search thousands of Catholic short term missions trips and volunteer opportunities: CatholicVolunteering.org</title>

<% bHeaderSet=true; %>
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->
<script language="javascript">
$(document).ready(function() {
	$('input#query').val("Catholic - (Denominational Affiliation)");
 	
	$('#search_input').find('a:first').attr('href', '/s/d/Catholic/ctp/opp/results.jsp#fq=denom_affil:"Catholic"&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Catholic Volunteering Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/d/Catholic/ctp/org/results.jsp#fq=denom_affil:"Catholic"&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Catholic Volunteering Organizations');	

	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);
});
</script>
<style>
#midbox1_content, #midbox2_content, #midbox3_content { height: 320px; }
#endingbox h3 {
	margin-bottom: 10px;
	clear: both;  
}
</style>


<!-- BEGIN MAINCONTENTs -->
<div id="welcomebox" class="wide landingpage">
	<div style="float:left;">
		<img src="<%=request.getContextPath()%>/imgs/welcomebox_photo.jpg" alt="Search thousands of Catholic short term missions trips and volunteer opportunities! Find opportunities in urban ministries, Catholic internships, and parish volunteering." width="417" height="231" /><!-- height="227" />-->
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%@ include file="/template_include/landing_page_facet_links.inc" %>
<% iNumDisplay=5; %>

	<div id="midbox1" class="landingpage">
		<img src="<%=request.getContextPath()%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/volunteers.gif" alt="Search Catholic volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all"  /></A>
		</div><!-- end: midbox1_photo-->




 <!--  <br clear="all"  /> -->
  <div id="midbox1_content" style="height:230px; font-size:12px;">
  			<br>
			<h2 class="head"><p>Volunteers</p></h2>
	  <p>CatholicVolunteering.org matches volunteers to volunteer opportunities like Monster.com matches jobseekers to jobs.
	  <ol>
        <li><a href="<%=request.getContextPath()%>/advancedsearch.jsp" alt="Discover Catholic  Volunteer Opportunities" title="Discover search Catholic  volunteer,Parish volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities"> Search for Catholic  Volunteer Opportunities</a>, <a href="<%=request.getContextPath()%>/shorttermmissions.jsp" alt="Discover Catholic  Virtual Volunteer Opportunities" title="Volunteer Internships">Volunteer Internships</a> and <a href="<%=request.getContextPath()%>/virtualvolunteer.jsp" title="Catholic  Virtual Volunteering"> Virtual Volunteering</a> opportunities</li>
        <li><a href="<%=request.getContextPath()%>/individualregistration.jsp" title="Register to find Catholic  volunteer,Parish volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" alt="Sign up Here for Catholic  Volunteer Opportunities"><strong>Register for a free account </strong></a></li>
        <li>Click &quot;I want to volunteer&quot; to be matched  with the organization by E-mail. </li>
      </ol>
      </p>

			<p><a href="<%=request.getContextPath()%>/organizationlistings.jsp" alt="View All Catholic  Volunteer Listings" title="View All Catholic  Volunteer, Short Term Urban Missions, Virtual Volunteer, and Urban Ministry Service Listings"></a></p>
  </div>
	  
	  <img src="<%=request.getContextPath()%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>



<div id="midbox2" style="font-size:12px;">
	<img src="<%=request.getContextPath()%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
	<div id="midbox2_photo">
			<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/organizationbox.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
	</div><!-- end: midbox2_photo-->
  <div id="midbox2_content" style="height:230px;">
    <br>
	<h2 class="head"><p>Organizations</p></h2>
	<p>You can <strong>start </strong><b>recruiting volunteers online for free in less than 5 minutes</b>.  
	<ol>
	  <li><a href="<%=request.getContextPath()%>/individualregistration.jsp" title="create Catholic  volunteer or Volunteer Internships">Create a free account</a></li>
      <li><a href="<%=request.getContextPath()%>/login.jsp" alt="Login Volunteer Recruitment Account" title="Login Catholic  Volunteer Recruitment Account">Login to start to recruit volunteers</a></li>
	  <li><a href="<%=request.getContextPath()%>/org.do?method=showCreateOrgStep1">Enter organization information</a> after logging in.</li>
	  <li><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" title="Post volunteer opportunities and Volunteer Internships">Post Volunteer Opportunities</a> after entering organization information. </li>
	  <li><A href="<%=request.getContextPath()%>/searchform.jsp" alt="Catholic  Volunteers can search from your website!" title="Volunteers can search from your website!">Put CatholicVolunteering.org search form on your website</A> (optional) </li>
	</ol>
	
	<br />
	  <br />
  </div>
	  <img src="<%=request.getContextPath()%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>


<div id="midbox3" style="font-size:12px;">
	<img src="<%=request.getContextPath()%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
	<div id="midbox3_photo">
		<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart">
		<img src="<%=request.getContextPath()%>/imgs/parish.gif" alt="Parish Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
	</div><!-- end: midbox3_photo-->  

  <div id="midbox3_content" style="height:230px;">
  	<br>
	<h2 class="head"><p>Parishes</p></h2>
	<ul>
      <li><a href="http://www.catholicvolunteering.org/org.do?method=showOrgStart" title="Recruit Volunteers for Charities, Religious Education and Catholic Schools">Recruit Volunteers for Charities, Religious Education and Catholic Schools</a></li>
      <li><a href="http://www.catholicvolunteering.org/org.do?method=showOrgStart" title="Post Internship Opportunities">Post Internship Opportunities</a></li>
      <li><a href="<%=request.getContextPath()%>/churchinstructions.jsp">Add CatholicVolunteering.org<br/> to Your Parish Website</a></li>
	  <li><a href="http://www.urbanministry.org/wiki/volunteer-toolkit-practical-equipment-effective-volunteer-management" title="Parish Volunteer Management Toolkit">Parish Volunteer Toolkit</a></li>
	</ul>
  </div>
  <img src="<%=request.getContextPath()%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>

<div id="endingbox" class="landingpage">
	<img src="http://www.christianvolunteering.org/imgs/endingbox_corner.gif" id="right_corner_endingbox"/>
	<!--div style="float:left;">
		<img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities" />
	</div-->
	<div style="float:left; margin: 5px 20px 25px 20px;">
	<h1>Catholic Volunteering Resources</h1>
	<%@ include file="/jsprsscron/volunteering_view.htm" %>
</div>
<div class="cleardiv"></div>
<br clear="all" />
</div>

  
<div class="cleardiv"></div>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="num_types_in_facet">5</div>
	<div id="page_type">landingpage</div>

	<div id="keyword_search"></div>
	<div id="fq_search"></div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>


<script type="text/javascript" src="/template_include/js/display_toggle_landingpage.js"></script>

<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->

