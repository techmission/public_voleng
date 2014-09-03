<%@ include file="/template_include/topjspnologin1.inc" %>


<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

<title>Christian Volunteer Network: Advanced Search for Christian volunteer opportunities, short term urban mission trips, and virtual volunteer opportunities</title>

<link rel="canonical" href="http://www.christianvolunteering.org/advancedsearch.jsp" />

<% } %>

<!-- start header information -->
<%//@ include file="/template/header.inc" %>
<!-- end header information -->
<!--link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" /-->
<!-- start navigation information -->
<%//@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
boolean bAssoc=false;
if(aszPortalType.equals("natlassoc")) bAssoc=true;

int iTemp=0;
// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidTripLength=263, vidRoomBoard=265, vidPosFreq=268, vidSchedDate=269, vidBenefits=286;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

// vidMemberType=42
int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264										&workstudy=8104
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;

// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszBothTID = "" + iBoth;
String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

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

ArrayList aServiceList = new  ArrayList ( 2 );
if(aszHostID.equalsIgnoreCase("volengchurch")){
	aCodes.getTaxonomyCodeList( aServiceList, vidService );
}else{
	aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
}
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

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

ArrayList aPosFreqList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeList( aPosFreqList, vidPosFreq );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null, aszSubmit="display: inline;";

String aszPartnerSecondDiv="display: none;";
// hide if not on specialized partner; if on specialized partner, show 2nd row by default, b/c first will be set to partner
if(	aszHostID.equalsIgnoreCase("volengsalvationarmy") ||
	aszHostID.equalsIgnoreCase("volengagrm")		
){ 
	aszPartnerSecondDiv="display: table-row;";
}


String aszChurchDisplay="display:none;";
if(aszHostID.equalsIgnoreCase("volengchurch")){
	aszChurchDisplay="display:inline;";
}
%>


  <div id="oppsearch" class="solr">
	  <span id="title">search</span>
	<%//@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>

<script language="javascript">
$(document).ready(function() {
	$('#fq_search').text(window.location.hash)
  });
</script>

<div id="result">

<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->


<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<div id="body">
<div id="populate" style="display:none;"></div>  
	
    <div id="all_tabs">
	<a style="text-decoration: none" href="<%=aszPortal%>/search.jsp"><h2>Find a Place to Volunteer</h2></a>
<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 0px; //margin-top:-7px;" />
</div> <!-- end: div id all_tabs-->
<!--br /-->		

<div id="searchpage">

<div id="searchform">
<%@ include file="/template_include/home_solr_search.inc" %>
</div>

</div>


<div id="show_results">
		<div id="sort" class="tagcloud toggle" style="float:right; display:none;">
		<span class="label">Sort By:</span>
	<SELECT id="sortkey" name="sortkey" class="smalldropdown" > 
        <option value="tm_member_i desc, popularity desc"></option>
        <option value="tm_member_i desc, popularity desc" >Popularity</option>
<%/* if( searchinfo.getPostalCode().length()>0 || aszLatitude.length() > 0 || aszLatitude.length() > 0){ %>
		<option value="tm_member_i desc, popularity desc">Distance</option>
<% } */%>
        <option value="org_name asc, tm_member_i desc, popularity desc" >Organization Name</option>
        <option value="title_literal asc, tm_member_i desc, popularity desc" >Opportunity Title</option>
        <option value="city_tax asc, tm_member_i desc, popularity desc" >City</option>
        <option value="province_tax asc, tm_member_i desc, popularity desc" >State (US & Canada)</option>
        <option value="country_tax asc, tm_member_i desc, popularity desc" >Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denom_affil asc, tm_member_i desc, popularity desc" >Denominational Affiliation</option>
        <option value="org_affil asc, tm_member_i desc, popularity desc" >Affiliation</option>
        <!--option value="length_of_trip_tid asc, tm_member_i desc, popularity desc" >Duration (Short-term Missions Only)</option-->
        <!--option value="tm_member_i desc, popularity desc" >Cost (Short-term Missions Only)</option-->
<% } %>
		<option value="lat_updated_dt desc, tm_member_i desc, popularity desc" >Date Last Updated</option>
        <option value="num_volunteers_org desc, tm_member_i desc, popularity desc" ># Volunteers / Organization</option>
        <option value="num_volunteers_opp desc, tm_member_i desc, popularity desc" ># Volunteers / Opportunity</option>
    </SELECT>

<br clear="all"/>
		</div>

	<div id="nav">
		<ul id="pager"></ul>  <div id="pager-header"></div>
	</div>
	<br>
	<div id="docs"></div>
</div>
</div>

<div class="cleardiv"></div>


</div>
</div>

<!-- start sidebar information -->
<%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%//@ include file="/template/footer.inc" %><!-- end footer information -->

