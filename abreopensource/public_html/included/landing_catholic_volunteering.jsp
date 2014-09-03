<%@ include file="/template_include/topjspnologin1.inc" %>

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ %>
	<jsp:include page="/ivol/service_trip.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% 
}else{ 
	bHeaderSet=true;
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Short-Term Missions Trip Search & Christian Ministry Internships: ChristianVolunteering.org</title>
<meta name="description" content="Search thousands short-term missions trips and christian ministry internships! Find opportunities in medical missions, orphanages and construction." />
<meta name="keywords" content="short term missions, Christian internships, ministry internships, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision" />

<!-- start wide header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end wide header information -->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );

String aszLandingParamsURL = "pt/Short-term_Missions_|_Volunteer_Internship";
String aszLandingParamsHash = "fq=position_type:%22Short-term Missions / Volunteer Internship%22";
%>

<script language="javascript">
$(document).ready(function() {
	window.location.hash='fq=content_type:opportunity&fq=intern_type:"City Vision Internship"';
	
	$('#srchmethod').val('Short-term Missions / Volunteer Internship');
	$('.srchmethod').val('Short-term Missions / Volunteer Internship');
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Short-term Missions Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Short-term Missions Organizations');

	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);

        $('#tfa_Email').focus(function() { $('#tfa_Email').val("") });
        $('#tfa_Email').blur(function() { $('#tfa_Email').val('Email') });

	$('input#queryLoc').val("<%=maxmind_postal%> (zip code)");
//	$('input#query').val("Hurricane Sandy - (Service Area)");
	$('#position_type_local').hide();
	$('#position_type_virtual').hide();
	$('#content_type').hide();
	$('#member_type').hide();
	$('a#stm').text('Featured Christian Internships');
	$('stm').attr('href', '<%=aszPortal%>/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=org_nid:73734&fq=content_type:opportunity');
	$('#stm').removeClass('inactive').addClass('active');
});
</script>

<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div style="float:left;"> <!-- img can have maximum width of about 485px; -->
		<img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Search thousands short-term missions trips and christian ministry internships! Find opportunities in medical missions, orphanages and construction."  width="417" height="234" />
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%//@ include file="/template_include/landing_page_facet_links.inc" %>
<%@ include file="/template_include/directory_page_facet_links.inc" %>
<% iNumDisplay=5; // ? not sure why only showing 5? %>
	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="http://www.cityvisioninternships.org"><img src="<%=aszPortal%>/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		
		<div id="midbox1_content"><!-- style="height: 400px;"-->
                <h2><a href="http://www.cityvisioninternships.org">City Vision Internships</a></h2>
		<p>In the past 10 years, we have placed over <strong>500 full-time interns</strong> and <strong>68,000+ volunteers</strong> in hundreds of Christian ministries.</p>
<div class="wFormContainer" style="margin-left: 10px;"><div class="wForm wFormWidth"><form method="post" id="id2315497" class="labelsLeftAligned hintsTooltip" action="http://www.tfaforms.com/responses/processor"><input type="hidden" value="" name="tfa_FirstName" id="tfa_FirstName"><input type="hidden" value="" name="tfa_LastName" id="tfa_LastName"><input type="hidden" value="" name="tfa_Company" id="tfa_Company"><div id="tfa_Email-D" class="oneField"><input type="text" value="Email" title="Email" size="20" name="tfa_Email" id="tfa_Email" class="validate-email clearOnFocus required"></div><div class="actions"><input type="submit" value="Learn More" name="tfa_submitAction" id="submit-" class="primaryAction"><input type="hidden" value="238464" name="tfa_dbFormId" id="tfa_dbFormId"><input type="hidden" value="" name="tfa_dbResponseId" id="tfa_dbResponseId"><input type="hidden" value="6d6627450fd68f2f08bbde3756e0cd82" name="tfa_dbControl" id="tfa_dbControl"><input type="hidden" value="2" name="tfa_dbVersionId" id="tfa_dbVersionId"></div></form></div></div>
                </div>
		<img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" />
	</div>
	
	<div id="midbox2" class="landingpage">
		<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
		<div id="midbox2_photo">
			<a href="http://www.cityvisioninternships.org"><img src="<%=aszPortal%>/imgs/organizationbox.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content"><!-- style="height: 400px;"-->
                        <br/>
			<h2 class="head"><a href="http://www.cityvisioninternships.org">One Year Ministry Internships</a></h2>
		<ul><li>Housing and meals are provided by hosting ministry (or a cash equivalent living stipend</li>
                    <li>Interns without Bachelor's degree receive free tuition in <a href="http://www.cityvision.edu">City Vision College</a> for a full year of college paid by hosting ministry. Any Pell grant or other federal financial aid paid as cash payment to student (up to $5,550).</li>
                    <li>Interns with bachelor's degree receive a living stipend from $200-$1,200/month (depending on site).</li></ul>
<br/>
<p class="learn-more-link"><a href="http://www.cityvisioninternships.org">FIND OUT MORE</a></p>
                </div>
		<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" />
	</div>
	
	<div id="midbox3" class="landingpage">
		<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
		<div id="midbox3_photo">
			<a href="http://www.cityvisioninternships.org"><img src="<%=aszPortal%>/imgs/missions_westafrica.jpg" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox3_photo-->  
		<div id="midbox3_content"><!-- style="height: 400px;"-->
			<br/>
			<h2 class="head"><a href="http://www.cityvisioninternships.org">Summer Christian Internships</a></h2>
                <ul><li>Housing and meals are provided by hosting ministry (or a cash equivalent living stipend).</li>
                <li>Free tuition in <a href="http://www.cityvision.edu">City Vision College</a> for summer courses paid by hosting ministry.</li>
                <li>Some sites may provide an additional stipend for outstanding candidates.</li></ul>	
<br/><br/><br/><br/><br/><p class="learn-more-link"><a href="http://www.cityvisioninternships.org">FIND OUT MORE</a></p>
  </div>
                <img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
	
</div>


<div class="cleardiv"></div>

<div id="endingbox" class="landingpage">
<div id="" class="search_results" style="padding:0px 20px;">
<% 
aszFQParams=aszLandingParamsHash;
aszFQParamsURL=aszLandingParamsURL;
String aszOrgNamePrint="";
String aszOppTitlePrint="";
String aszContentType="";
String aszKewordsSpaceSeparated="";
int iCounter=0;
//String DecimalFormat="";
%>

<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search">organization_name:&quot;City Vision&quot;&org_nid:73734</div>
	<div id="fq_search">organization_name:&quot;City Vision&quot;&org_nid:73734</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQParams is <%=aszFQParams%>
</div>

  <div id="hd" class="solr" style="padding: 5px 0px;">
	<%@ include file="/template_include/navigation_search_bar_solr_no_search_postal.inc" %>
  </div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
<a href="http://www.christianvolunteering.org/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=org_nid:73734&fq=content_type:opportunity&fq=organization_name:&quot;City%20Vision&quot;">City Vision Internships</a>

<%@ include file="/template_include/search_sidebar.inc" %>
</div><!-- end solr_results div -->

<br clear="all"/>

<div class="results-left" id="all_tabs">
	<div id="position_type" style="display: none;">
		<div class="results-left" id="position_type_local" style="display: none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Local Volunteering (in person)") && iCountDisplay==0){ 
				%>
					<a class="active" id="local" href="">Local Volunteering</a>
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="active" id="local" href="">Local Volunteering</a>
			<% } %>
		</div>
		<div class="results-left" id="position_type_virtual" style="display: none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Virtual Volunteering (from home)") && iCountDisplay==0){ 
				%>
					<a class="" id="virtual" href="">Virtual</a>
				<% 		
						iCountDisplay++;
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="" id="virtual" href="">Virtual</a>
			<% } %>
		</div>
	</div>
    
		<div class="results-left" id="position_type_stm">
			<a class="" id="stm" href="<%=aszPortal%>/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=organization_name:&quot;City Vision&quot;&fq=org_nid:73734&fq=content_type:opportunity">Featured Christian Internships</a>
		</div>
	
	<div id="content_type" style="display: none;">
		<div class="results-left" id="content_type_job_tab" style="display: none;">
			<a class="" id="job" href="b">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab" style="display: none;">
			<a class="" id="organization" href="">Organizations</a>
		</div>
		<div class="results-left" id="content_type_res_tab" style="display: none;">
			<a class="" id="resume" href="">Resum&eacute;s</a>
		</div>
	</div>
	
	<div id="member_type" style="display: none;">
		<div class="results-left" id="member_type_fdn_tab" style="display: none;">
			<a class="" id="Foundation" href="">Foundations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->


	<div id="nav">
		<ul id="pager"></ul>  <ul id="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
	</div>
	<br clear="all">


<div id="map_container" style="display:none;">
	<br />
	<div id="map" style="width: 100%; height: 400px;">
	</div>
	<br /><br />
</div>

	
	<div id="docs" ></div>
	
	
<div id="simplyhired" style="display:none;">
</div>

	<div class="cleardiv"></div>
</div>
</div>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
<% } %>
