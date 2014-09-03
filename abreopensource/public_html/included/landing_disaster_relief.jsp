<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Disaster Relief: ChristianVolunteering.org</title>
<meta keywords="disaster relief, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision" />
<meta description="Search disaster relief volunteer opportunities! Find opportunities in urban ministries, Christian internships, and church volunteering." />

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%

String aszLandingParamsURL = "sa/Disaster_Relief";
String aszLandingParamsHash = "fq=service_areas:%22Disaster Relief%22";
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
//	$('#search_heading').html('<h2 style="margin: -30px 0 0 18px;">Search Disaster Relief Volunteer Opportunities</h2>');
	$('#find_heading').html('Disaster Relief ');
		
	$('input#query').val("Disaster Relief - (Service Area)");
	
	$('#browse_links').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#browse_links').find('a:first').html('Browse Disaster Relief Opportunities');
	
	$('#browse_links').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#browse_links').find('a:last').html('Browse Disaster Relief Organizations');
	
	$('#queryLoc').val('');
//	$('#srchmethod option[value="organization"]').attr("selected", "selected");

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

maxmind_postal="";
%>

<style>
#welcomebox h1.image_text {
    font-family: Gill Sans, Arial Narrow,Arial,Helvetica,sans-serif;
    font-size: 26px;
    text-align: right;
    color: #FFFFFF;
    text-shadow: 0.1em 0.1em #333333;
}
#welcomebox.landingpage #home_solr_search {
    margin-top: 45px;
}
div#welcomebox #search_checkboxes {
    display: block;
    float: left;
    padding-left: 70px;
    text-align: right;
}
</style>
<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage tall">
	<div class="image" style="float:left;"> <!-- img can have maximum width of about 485px; -->
<div style="position: relative; width: 417px; height: 320px;">
<iframe width="420" height="315" src="http://www.youtube.com/embed/tTleG3TkOoU" frameborder="0" allowfullscreen></iframe>

</div>
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;">
<img width="17" height="320" src="http://www.christianvolunteering.org/imgs/welcomebox_right_408.gif">
</div>


<div class="cleardiv"></div>

<div style="margin-top: 10px;">
<div style="background-color: #ecf1fc; border: 4px solid #728dd4; border-radius: 8px; margin: 8px;">
<h2 style="margin-left: 10px; color: #728dd4">Disaster Relief Encyclopedia Wiki</h2>
<!-- begin TechMission ChristianVolunteering embed code -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://www.cityvision.edu/wiki/sites/all/modules/custom/remote_block/js/jquery.ba-postmessage.min.js"></script>
<script type="text/javascript">
jQuery(function(){
  var if_height,
    src = 'http://www.cityvision.edu/wiki/remote-block/html/quicktabs/disaster_relief_resources#' + encodeURIComponent( document.location.href ),
    iframe = $( '<iframe " src="' + src + '" width="1000" height="460" scrolling="no" frameborder="0"><\/iframe>' )
      .appendTo( '#iframe' );
  jQuery.receiveMessage(function(e){
    var h = Number( e.data.replace( /.*if_height=(\d+)(?:&|$)/, '$1' ) );
    if ( !isNaN( h ) && h > 0 && h !== if_height ) {
      iframe.height( if_height = h );
    }
  }, 'http://www.cityvision.edu' );
});
</script>
<div id="iframe"></div>
</div>
<!-- end embed code -->

  
<div class="cleardiv"></div>
</div>


<!-- start footer information -->
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
