<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<title>Free Christian Foundation Grants Directory</title>

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%
maxmind_postal="";
String aszLandingParamsHash = "fq=content_type:organization&fq=org_member_type:Foundation";
%>

<!-- custom JS for the homepage - TODO: put this in a file -->
<script type="text/javascript">
$(document).ready(function() {
	var filter_class = 'filter';
	var handle_class = ' handle';
	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';

	window.location.hash='<%=aszLandingParamsHash%>';
	$('.srchmethod').val('Foundation');
	
	$('#browse_links').hide();
//console.log('query loc is '+	$('#queryLoc').val());
	$('#queryLoc').val('');
//console.log('query loc is '+	$('#queryLoc').val());
	$('#search_location #queryLoc').val('');
	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
	
	$(".round_bottom_right").corner("round 50px br"); /*this is a Rounded Bottom Right corner*/
	
	$(".round_bottom").corner("bottom"); /*this is a Rounded Bottom corner*/
	$(".only_top_right").corner("round tr").parent().css('padding', '4px').corner("round tr 10px"); /*this is a Rounded Top Right Only corner*/
	$(".bottom_right").corner("round br").parent().css('padding', '4px').corner("round br 10px"); /*this is a Rounded Top Right Only corner*/
	
	$(".n").corner("notch 14px").parent().css('padding', '4px').corner("notch 4px"); /* this is notched corners*/

	$('#srchmethod').val('Foundation');
	
	$('#search_input').find('a:first').hide();
	$('#search_input').find('a:first').hide();


//	$('input#queryLoc').val("<%=maxmind_postal%> (zip code)");
//	$('input#query').val("Hurricane Sandy - (Service Area)");
	$('#position_type').hide();
	$('#content_type').hide();
	$('Foundation').attr('href', '<%=aszPortal%>/oppssrch.do?method=processSearch&<%=aszLandingParamsHash%>#<%=aszLandingParamsHash%>');
	$('#Foundation').removeClass('inactive').addClass('active');
	$('#queryLoc').val('');
	$('#query').val('Funding Interests, Keywords');
	$('.location').val('Location (Country, Zipcode)');
	$('.location').val('');
	$('.keywords_label').text('i.e. children, food ministry, homelessness, computers, teaching, etc');
//console.log('62 query loc is '+	$('#queryLoc').val());

		$('#content_type_search').text('content_type:organization');	
		$('#results_type').text('organization');	
		
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').addClass('active');

//console.log('867  adding Foundation is: '+value);
						
		$('#srchmethod').val('Foundation');	
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();	
		$('#simplyhired').hide();	
						
		$('#num_opps').hide();
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Foundations');
		$('#contenttype_heading').text('Christian Foundation Grants');
		$('#service_areas_label').text('Funding Interests:');
		$('#facet_afg').hide();
		$('#facet_region').hide();
		$('#facet_city_tax').hide();
		$('#facet_country_tax').hide();
//		$('#facet_country_tax_label').text('Geographic Scope Country:');
		$('#facet_province_tax_label').text('Geographic Scope State:');
//	console.log(' service_areas_label is: ' + $('#service_areas_label').text() );
//console.log(' contenttype_heading is: ' + $('#contenttype_heading').text() );
											
		$('#service_areas').show();
		$('#facet_service_areas').removeClass(collapsed_class).addClass(expanded_class);
							
		$('#facet_great_for').hide();  
		$('#facet_benefits_offered').hide(); 
		$('#facet_trip_length').hide(); 
					
		$('#group_size').hide();
		$('#facet_group_size').hide();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		$('#frequency').hide();
		$('#facet_frequency').hide();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		$('#benefits_offered').hide();
		$('#facet_benefits_offered').hide();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		$('#source').hide();
		$('#facet_afg').removeClass(expanded_class).addClass(collapsed_class);
									
		$('#primary_opp_type').hide();
		$('#facet_primary_opp_type').removeClass(expanded_class).addClass(collapsed_class);
					
		$('#advanced_facets').hide();
		$('#facet_adv').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#affil').hide();
		$('#facet_org_affil').removeClass(expanded_class).addClass(collapsed_class);
		$('#denom_affil').hide();
		$('#facet_denom_affil').removeClass(expanded_class).addClass(collapsed_class);
		

		$('#org_member_type').hide();
		$('#facet_org_member_type').hide();
		
					
//console.log('about to trigger toggle financial data');		
			$('#total_giving').show();
			$('#facet_total_giving').show();
			$('#facet_total_giving').removeClass(collapsed_class).addClass(expanded_class);
			$('#net_assets').show();
			$('#facet_net_assets').show();
			$('#facet_net_assets').removeClass(collapsed_class).addClass(expanded_class);
			$('#assets').show();
			$('#facet_assets').show();
			$('#facet_assets').removeClass(collapsed_class).addClass(expanded_class);
			$('#income').show();
			$('#facet_income').show();
			$('#facet_income').removeClass(collapsed_class).addClass(expanded_class);
			$('#expenditures').show();
			$('#facet_expenditures').show();
			$('#facet_expenditures').removeClass(collapsed_class).addClass(expanded_class);

		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();

		$('#facet_dist').hide();
		$('#facet_sort').hide();
		$('#content_type_heading').hide();
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

String aszHashSearch="", aszFQSearch="", aszDisplayClass="", aszLongitude="", aszLatitude="",aszKeywordSearch="", aszFormName="";
int iNumDisplay=0, iCountDisplay=0;
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
.main_text li { margin-left: -10px; } 
.main_text ul li{ margin-left: -10px; }
#maincontent{
	margin-left: 15px;
}
#all_tabs{
	margin-top: 20px;
}
#welcomebox h1.image_text {
    font-family: Gill Sans, Arial Narrow,Arial,Helvetica,sans-serif;
    font-size: 26px;
    margin-bottom: 10px;
    text-align: right;
    color: #FFFFFF;
}


body div#endingbox li {
    height: auto;
}
</style>
<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div class="image" style="float:left;"> <!-- img can have maximum width of about 485px; -->
<div style="position: relative; background: url(http://www.christianvolunteering.org/imgs/pic/moneycash.jpg); width: 417px; height: 234px;">

<div style="position: absolute; bottom: 0; left: 0.5em; width: 400px; font-weight: bold; color: #fff; text-shadow: black 0.2em 0.1em 0.2em">
<h1 class="image_text">Search over 10,000 Christian<br />and faith-friendly foundations</h1>
</div>
</div>
	</div>
<h2 style="margin: 5px 5px -5px 435px;">Search Christian Foundation Grants</h2>

	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>


<div class="cleardiv"></div>

<div style="margin-top:10px;">

<!-- ==========================   Job Seekers    ==============================================================-->
 <div class="container_stories_box">
 
<div id="stories_box" class="c">
      <div id="stories_header">
        <h3 class="title"><a href="http://www.cityvision.edu?theme=cvc_internship">Certified Fundraising Executive Courses</a></h3>
    </div><!-- end stories_header -->


	<div style="" id="opp1_s">
		<div id="img_opp1" class="round_bottom_right"></div>
		<div class="main_text" style="height:auto;">
<ul style="margin-left: -15px;">
	<li><a href="http://www.cityvision.edu/cms/cv/course-309-fund-raising-basics?theme=cvc_internship">Fund Raising Basics</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-310-intermediate-fund-raising?theme=cvc_internship">Intermediate Fund Raising</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-302-nonprofit-administration?theme=cvc_internship">Nonprofit Administration</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-303-financial-accounting?theme=cvc_internship">Nonprofit Accounting</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-405-financial-planning-nonprofits?theme=cvc_internship">Nonprofit Financial Planning</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-304-ministry-management?theme=cvc_internship">Ministry Management and Leadership</a></li>
	<li><a href="http://www.cityvision.edu/cms/cv/course-305-human-resources?theme=cvc_internship">Human Resources</a></li>
</ul>
<br/>
          </div><!-- end: main_text -->
	</div><!-- end: story1_s-->
        
     
        </div><!--end: div#stories_box.c-->
  
</div> <!--end: div.container_stories_box-->


  <!-- ==========================   Recruiters    ==============================================================-->
 <div class="container_org_stories_box">
 
<div id="org_stories_box" class="c">
      <div id="org_stories_header">
        <h3 class="title"><a href="http://www.cityvision.edu?theme=cvc_internship">Degree Programs for Fundraisers</a></h3>
    </div><!-- end stories_header -->
        
        
	<div style="" id="org1_s">
		<div id="img_org1" class="round_bottom_right"></div>
		<div class="main_text">
		  <p>
          Bachelor's degrees in 
          <ul>
          	<li><a href="http://www.cityvision.edu/cms/cv/npo-management-program?theme=cvc_internship">Nonprofit Management</a></li>
          	<li><a href="http://www.cityvision.edu/cms/cv/urban-missions-program?theme=cvc_internship">Urban Missions</a></li>
          	<li><a href="http://www.cityvision.edu/cms/cv/addiction-studies-program?theme=cvc_internship">Addiction Studies</a></li>
          </ul>
          </p>
                 </div><!-- end volunteerinfo -->
	</div>
        
         
  </div><!-- end : .org_stories_box .c  -->
</div> <!--end: div.container_stories_box-->  
    <!-- ==========================   Internships    ==============================================================-->
<div class="container_church_stories_box"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="http://www.cityvisioninternships.org">Fundraising and Ministry Internships</a></h3>
    </div><!-- end stories_header -->
        
	<div style="" id="church1_s">
		<div id="img_church1" class="round_bottom_right"></div>
		<div class="main_text">
		<ul style="margin-left: -15px;">
            <li><a href="http://www.cityvisioninternships.org/site-application">Apply to Host a Fundraising or Ministry Intern at your Organization</a></li>
            <li><a href="http://www.cityvisioninternships.org">Apply to be an Intern and Get Free Undergraduate or Graduate Tuition</a></li>
        </ul>
                </div><!-- end main_text -->
	</div>
  </div>
</div> <!--end: div.container_stories_box-->     


  
<div class="cleardiv"></div>
</div>




<div id="endingbox" class="landingpage">


<div id="" class="search_results" style="padding:0px 20px;">
<% 
String aszFQParams=aszLandingParamsHash;
String aszFQParamsURL=aszLandingParamsHash;

String aszOrgNamePrint="";
String aszOppTitlePrint="";
String aszContentType="content_type:organization";
String aszKewordsSpaceSeparated="";
int iCounter=0;
//String DecimalFormat="";
%>

<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<%//@include file="/template_include/footer_google_analytics_search_javascript.inc"%>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search">content_type:organization&amp;org_member_type:Foundation</div>
	<div id="fq_search">content_type:organization&amp;org_member_type:Foundation</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQParams is <%=aszFQParams%>
</div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
<a href="http://www.christianvolunteering.org/oppsrch.do?method=processSearch&<%=aszLandingParamsHash%>#<%=aszLandingParamsHash%>">Christian Foundation Grants</a>

</div><!-- end solr_results div -->
<%@ include file="/template_include/search_results_sidebar.inc" %>


<div id="maincontent" class="search_results" style="width:650px;">

  <div id="hd" class="solr" style="display:none">
	<h2 class="shorter"><span id="contenttype_heading">Christian Foundation Grants</span></h2>
	<%@ include file="/template_include/navigation_search_bar_solr_no_search_postal.inc" %>
  </div>


<div class="results-left" id="all_tabs">
	<div id="position_type" style="display: none;">
		<div class="results-left" id="position_type_local" style="display: none;">
		</div>
		<div class="results-left" id="position_type_virtual" style="display: none;">
        </div>
	</div>
    
		<div class="results-left" id="position_type_stm" style="display:none;">
			<a class="" id="stm" href="">Featured Christian Internships</a>
		</div>
	
	<div id="content_type" style="display: none;">
		<div class="results-left" id="content_type_job_tab" style="display: none;">
			<a class="" id="job" href="">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab" style="display: none;">
			<a class="" id="organization" href="">Organizations</a>
		</div>
		<div class="results-left" id="content_type_res_tab" style="display: none;">
			<a class="" id="resume" href="">Resum&eacute;s</a>
		</div>
	</div>
	
	<div id="member_type" >
		<div class="results-left" id="member_type_fdn_tab" >
			<a class="" id="Foundation" href="/oppsrch.do?method=processSearch&<%=aszLandingParamsHash%>#<%=aszLandingParamsHash%>">Foundations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->


	<div id="nav">
		<ul id="pager"></ul>  <ul id="pager-header"></ul>  <ul id="map_link" class="volunteer" style="display:none;">Map These Results</ul>
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
</div>
</div>

<!-- start footer information -->
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->

