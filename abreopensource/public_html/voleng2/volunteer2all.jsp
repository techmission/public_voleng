<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Advanced Search for Christian volunteer opportunities, short term urban mission trips, and virtual volunteer opportunities</title>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->

<script language="javascript">
	function opp_search(){
		document.getElementById('notall').style.display='inline';
		document.getElementById('servicearealabel').style.display='inline';
		document.getElementById('servicearea').style.display='inline';
		document.getElementById('programtypelabel').style.display='none';
		document.getElementById('programtype1').style.display='none';
		document.getElementById('opptype').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortorgs').style.display='none';
		document.getElementById('googleSearchAll').style.display='none';
	}
	function org_search(){
		document.getElementById('notall').style.display='inline';
		document.getElementById('servicearealabel').style.display='none';
		document.getElementById('servicearea').style.display='none';
		document.getElementById('programtypelabel').style.display='inline';
		document.getElementById('programtype1').style.display='inline';
		document.getElementById('regionlabel').style.display='inline';
		document.getElementById('regionsection').style.display='inline';
		document.getElementById('countrylabel').style.display='inline';
		document.getElementById('countrysection').style.display='inline';
		document.getElementById('opptype').style.display='none';
		document.getElementById('missions').style.display='none';
		document.getElementById('missions-duration').style.display='none';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='none';
		document.getElementById('skillsgroup').style.display='none';
		document.getElementById('grouplabel').style.display='none';
		document.getElementById('group').style.display='none';
		document.getElementById('sortstm').style.display='none';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='inline';
		document.getElementById('googleSearchAll').style.display='none';
	}
	function all_search(){
		document.getElementById('notall').style.display='none';
		document.getElementById('googleSearchAll').style.display='inline';
	}

	function clicked_local(){
		document.getElementById('regionlabel').style.display='inline';
		document.getElementById('regionsection').style.display='inline';
		document.getElementById('countrylabel').style.display='inline';
		document.getElementById('countrysection').style.display='inline';
		document.getElementById('postallabel').style.display='inline';
		document.getElementById('postal').style.display='inline';
		document.getElementById('distlabel').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missions').style.display='none';
		document.getElementById('missions-duration').style.display='none';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('grouplabel').style.display='inline';
		document.getElementById('group').style.display='inline';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
	}
	function clicked_virtual(){
		document.getElementById('regionlabel').style.display='none';
		document.getElementById('regionsection').style.display='none';
		document.getElementById('countrylabel').style.display='none';
		document.getElementById('countrysection').style.display='none';
		document.getElementById('postallabel').style.display='none';
		document.getElementById('postal').style.display='none';
		document.getElementById('distlabel').style.display='none';
		document.getElementById('dist').style.display='none';
		document.getElementById('missions').style.display='none';
		document.getElementById('missions-duration').style.display='none';
		document.getElementById('virtualaddr').style.display='none';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('grouplabel').style.display='none';
		document.getElementById('group').style.display='none';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
	}
	function clicked_missions(){
		document.getElementById('regionlabel').style.display='inline';
		document.getElementById('regionsection').style.display='inline';
		document.getElementById('countrylabel').style.display='inline';
		document.getElementById('countrysection').style.display='inline';
		document.getElementById('postallabel').style.display='inline';
		document.getElementById('postal').style.display='inline';
		document.getElementById('distlabel').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missions').style.display='inline';
		document.getElementById('missions-duration').style.display='inline';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('grouplabel').style.display='inline';
		document.getElementById('group').style.display='inline';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
	}
	function clicked_all(){
		document.getElementById('regionlabel').style.display='inline';
		document.getElementById('regionsection').style.display='inline';
		document.getElementById('countrylabel').style.display='inline';
		document.getElementById('countrysection').style.display='inline';
		document.getElementById('postallabel').style.display='inline';
		document.getElementById('postal').style.display='inline';
		document.getElementById('distlabel').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missions').style.display='inline';
		document.getElementById('missions-duration').style.display='inline';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('grouplabel').style.display='inline';
		document.getElementById('group').style.display='inline';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
	}
	function clicked_both(){
		document.getElementById('regionlabel').style.display='inline';
		document.getElementById('regionsection').style.display='inline';
		document.getElementById('countrylabel').style.display='inline';
		document.getElementById('countrysection').style.display='inline';
		document.getElementById('postallabel').style.display='inline';
		document.getElementById('postal').style.display='inline';
		document.getElementById('distlabel').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missions').style.display='none';
		document.getElementById('missions-duration').style.display='none';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('skillsgrouplabel').style.display='inline';
		document.getElementById('skillsgroup').style.display='inline';
		document.getElementById('grouplabel').style.display='none';
		document.getElementById('group').style.display='none';
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
	}
</script>

<%
int iTemp=0;

// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
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

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
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
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch" ">
	  <span id="title" >search </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">advanced search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<div id="body">
 
<%

String search=null;

String aszPartnerSecondDiv="display: none;";
// hide if not on specialized partner; if on specialized partner, show 2nd row by default, b/c first will be set to partner
if(     aszHostID.equalsIgnoreCase("volengsalvationarmy") ||
        aszHostID.equalsIgnoreCase("volengagrm")
){
        aszPartnerSecondDiv="display: table-row;";
}%> 

     <div align="left">
     <br>
<%@  include file="/template_include/advanced_search_form.inc" %>
    </div>
    </div>
      <P>
      <BR>&nbsp;</P>
</div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
