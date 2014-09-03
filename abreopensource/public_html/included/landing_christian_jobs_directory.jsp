<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<title>Christian Jobs Directory: Search over 10,000 Christian jobs and ministry internships, ChristianVolunteering.org</title>

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%

String aszLandingParamsURL = "ctp/job";
String aszLandingParamsHash = "fq=content_type:job";
%>

<!-- custom JS for the homepage - TODO: put this in a file -->
<script type="text/javascript">
// Events for hiding/showing stories on homepage.
function hideStoriesOpp() {
	$('#opp1_s').hide(); $('#opp1').removeClass('active_tab');
	$('#opp2_s').hide(); $('#opp2').removeClass('active_tab');
	$('#opp3_s').hide(); $('#opp3').removeClass('active_tab');
	$('#opp4_s').hide(); $('#opp4').removeClass('active_tab');
}

function hideStoriesOrg() {
	$('#org1_s').hide(); $('#org1').removeClass('active_tab');
	$('#org2_s').hide(); $('#org2').removeClass('active_tab');
	$('#org3_s').hide(); $('#org3').removeClass('active_tab');
	$('#org4_s').hide(); $('#org4').removeClass('active_tab');
}

function hideStoriesChurch() {
	$('#church1_s').hide(); $('#church1').removeClass('active_tab');
	$('#church2_s').hide(); $('#church2').removeClass('active_tab');
	$('#church3_s').hide(); $('#church3').removeClass('active_tab');
	$('#church4_s').hide(); $('#church4').removeClass('active_tab');
}

$(document).ready(function() {
	window.location.hash='fq=content_type:opportunity&fq=intern_type:"City Vision Internship"';
	$('.srchmethod').val('Short-term Missions / Volunteer Internship');
	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
	
	$(".round_bottom_right").corner("round 50px br"); /*this is a Rounded Bottom Right corner*/
	
	$(".round_bottom").corner("bottom"); /*this is a Rounded Bottom corner*/
	$(".only_top_right").corner("round tr").parent().css('padding', '4px').corner("round tr 10px"); /*this is a Rounded Top Right Only corner*/
	$(".bottom_right").corner("round br").parent().css('padding', '4px').corner("round br 10px"); /*this is a Rounded Top Right Only corner*/
	
	$(".n").corner("notch 14px").parent().css('padding', '4px').corner("notch 4px"); /* this is notched corners*/

	$('#srchmethod').val('Jobs');
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/results.jsp#<%=aszLandingParamsHash%>');
	$('#search_input').find('a:first').html('Browse Jobs');
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
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


%>

<style>
#welcomebox h1.image_text {
    font-family: Gill Sans, Arial Narrow,Arial,Helvetica,sans-serif;
    font-size: 26px;
    margin-bottom: 10px;
    text-align: right;
    color: #FFFFFF;
}
</style>
<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div class="image" style="float:left;"> <!-- img can have maximum width of about 485px; -->
<div style="position: relative; background: url(http://www.christianvolunteering.org/imgs/pic/walking.jpg); width: 417px; height: 234px;">

<div style="position: absolute; bottom: 0; left: 0.5em; width: 400px; font-weight: bold; color: #fff;">
<h1 class="image_text">Search over 10,000 jobs<br />in Christian organizations</h1>
</div>
</div>
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>


<div class="cleardiv"></div>

<div style="margin-top:10px;">

<!-- ==========================   Job Seekers    ==============================================================-->
 <div class="container_stories_box">
 
<div id="stories_box" class="c">
      <div id="stories_header">
       <h3 class="title"> <a href="http://www.cityvisioninternships.org">City Vision Internships</a></h3>
    </div><!-- end stories_header -->


	<div style="" id="opp1_s">
		<div id="img_opp1" class="round_bottom_right"></div>
		<div class="main_text">
<p>In the past 10 years, we have placed over <strong>500 full-time interns</strong> and <strong>68,000+ volunteers</strong> in hundreds of Christian ministries.</p>
<br/>
	<div class="wFormContainer"><div class="wForm wFormWidth"><form method="post" id="id2315497" class="labelsLeftAligned hintsTooltip" action="http://www.tfaforms.com/responses/processor"><input type="hidden" value="" name="tfa_FirstName" id="tfa_FirstName"><input type="hidden" value="" name="tfa_LastName" id="tfa_LastName"><input type="hidden" value="" name="tfa_Company" id="tfa_Company"><div id="tfa_Email-D" class="oneField"><input type="text" value="Email" title="Email" size="20" name="tfa_Email" id="tfa_Email" class="validate-email clearOnFocus required"></div><div class="actions"><input type="submit" value="Learn More" name="tfa_submitAction" id="submit-" class="primaryAction"><input type="hidden" value="238464" name="tfa_dbFormId" id="tfa_dbFormId"><input type="hidden" value="" name="tfa_dbResponseId" id="tfa_dbResponseId"><input type="hidden" value="6d6627450fd68f2f08bbde3756e0cd82" name="tfa_dbControl" id="tfa_dbControl"><input type="hidden" value="2" name="tfa_dbVersionId" id="tfa_dbVersionId"></div></form></div></div>	
          </div><!-- end: main_text -->
	</div><!-- end: story1_s-->
        
     
        </div><!--end: div#stories_box.c-->
  
</div> <!--end: div.container_stories_box-->


  <!-- ==========================   Recruiters    ==============================================================-->
 <div class="container_org_stories_box">
 
<div id="org_stories_box" class="c">
      <div id="org_stories_header">
        <h3 class="title"><a href="http://www.cityvisioninternships.org">One Year Ministry Internships</a></h3>
    </div><!-- end stories_header -->
        
        
	<div style="" id="org1_s">
		<div id="img_org1" class="round_bottom_right"></div>
		<div class="main_text">
		  <ul><li>Housing and meals are provided by hosting ministry (or a cash equivalent living stipend).</li>
<li>Interns without Bachelor's degree receive free tuition in <a href="http://www.cityvision.edu">City Vision College</a> for a full year of college paid by hosting ministry. Any Pell grant or other federal financial aid paid as cash payment to student (up to $5,550).</li>
<li>Interns with Bachelor's degree receive  a living stipend from $200-$1,200/month (depending on site).</li></ul>
                 </div><!-- end volunteerinfo -->
	</div>
        
         
  </div><!-- end : .org_stories_box .c  -->
</div> <!--end: div.container_stories_box-->  
    <!-- ==========================   Internships    ==============================================================-->
<div class="container_church_stories_box"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="http://www.cityvisioninternships.org">Summer Christian Internships</a></h3>
    </div><!-- end stories_header -->
        
	<div style="" id="church1_s">
		<div id="img_church1" class="round_bottom_right"></div>
		<div class="main_text">
		<ul><li>Housing and meals are provided by hosting ministry (or a cash equivalent living stipend).</li>
<li>Free tuition in City Vision College for a summer courses paid by hosting ministry.</li>
<li>Some sites may provide an additional stipend for outstanding candidates.</li></ul>
                </div><!-- end main_text -->
	</div>
  </div>
</div> <!--end: div.container_stories_box-->     


  
<div class="cleardiv"></div>
</div>




<div id="endingbox" class="landingpage">


<div id="" class="search_results" style="padding:0px 20px;">
<% 
String aszFQParams="fq=position_type:%22Short-term Missions / Volunteer Internship%22";
String aszFQParamsURL="pt/Short-term_Missions_|_Volunteer_Internship";

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
		</div>
		<div class="results-left" id="position_type_virtual" style="display: none;">
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
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
