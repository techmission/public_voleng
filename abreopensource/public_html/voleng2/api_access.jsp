<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<title>
About Christian Social Graph API 
</title>
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

<style>
#oppsearch {
    background-color: #DCE4FA;
    border-bottom: 1px solid #003366;
    border-top: 1px solid #003366;
    float: left;
    margin: 0;
    text-align: left;
    width: auto;
}
ul.search input.watermark {
    font-size: 12px;
    height: auto;
    width: 120px;
    color: gray;
    font-style: italic;
    padding-left: 3px;
}
input.watermark {
    width: 120px;
}
#search_solr {
	float: left;
    font-size: 12px;
	margin-right: 3px;
}
#queryLoc{
	float: right;
}
</style>

<script>
$(document).ready(function() {	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
});
</script>

<!-- BEGIN MAINCONTENTs -->
<div style="margin: 2px 8px 5px; padding: 0px 8px 0px;">
	<h1 class="blue">About the Christian Social Graph API</h1>
</div>
<div class="cleardiv"></div>

<div style="margin: 0px 0px 0px 15px;">

<div class="outer" style="margin-right:3px;"> 
<div id="inner" class="c inner">

<h2>What is the Christian Social Graph API?</h2>
<ul class="no-indent">
	<li>Our API provides data to use in your website including 10k+ volunteer opportunities, 100k+ ministries and 15k+ Christian jobs. You select the data you want.</li>
	<li>Data comes as an RSS Feed (XML, JSON and CSV/Excel formats also supported)</li>
	<li>Requires that your organization apply for a "key" and agree to terms of use
		<ul class="sub-list-no-indent">
			<li>Intended for live access through programming on websites and applications</li>
			<li>No use in mailing lists and no reselling data</li>
		</ul>
	</li>
</ul>

<h2>Who is the API for?</h2>
<ul class="no-indent">
	<li>Web developers for megachurches that want to create customized volunteer and job sections of their websites</li>
	<li>Ministries interested in creating citywide or national volunteer and job directories</li>
	<li>Christian colleges wanting to integrate data into their job boards</li>
	<li>Ministries interested in providing regionally focused websites and apps with localized data (Christian Radio, TV)</li>
	<li>Church and volunteer management systems</li>
	<li>The following is a list of our <a href="<%=aszPortal%>/register.do?method=showSyndicationPartners">syndication partners</a></li>
</ul>
<h2>What are the steps to Use the API?</h2>
<ol class="no-indent">
	<li><a href="<%=aszPortal%>/orgmanagement.jsp">Create organizational account</a> and post organization profile</li>
	<li><a href="<%=aszPortal%>/apitos.jsp">Apply for an API Key</a></li>
	<li><a href="<%=aszPortal%>/advancedsearch.jsp">Search</a> for the data you want on ChristianVolunteering.org</li>
	<li><a href="<%=aszPortal%>/api.jsp">Use API Page</a> to translate your search into an API request.</li>
	<li>Import data into your website using the API request URL</li>
</ol>

</div>
</div>

<div class="outer" style="margin-left:3px;">
<div id="inner" class="c inner">

<object id="scPlayer" style="margin-top: 40px;" width="425" height="318" type="application/x-shockwave-flash" data="http://content.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/scplayer.swf" ><param name="movie" value="http://content.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/scplayer.swf" /><param name="quality" value="high" /><param name="bgcolor" value="#FFFFFF" /><param name="flashVars" value="thumb=http://content.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/FirstFrame.png&containerwidth=960&containerheight=703&autohide=true&autostart=false&loop=false&showendscreen=true&showsearch=false&showstartscreen=true&tocdoc=left&xmp=sc.xmp&content=http://content.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/Using%20ChristianVolunteering.org%27s%20Christian%20Social%20Graph%20API.mp4&blurover=false" /><param name="allowFullScreen" value="true" /><param name="scale" value="showall" /><param name="allowScriptAccess" value="always" /><param name="base" value="http://content.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/" /><iframe type="text/html" frameborder="0" scrolling="no" style="overflow:hidden;" src="http://www.screencast.com/users/techmission/folders/Default/media/366a35d2-03ec-4960-a134-2bf2f17279fd/embed" height="703" width="960" ></iframe></object>
</div>
</div>

<div class="cleardiv"></div>

<br />
</div>
<div class="cleardiv"></div>


<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
