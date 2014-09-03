<!-- start JSP information -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information -->


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<script type="text/javascript"> 

function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 

document.onkeypress = stopRKey; 

</script>

<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>


<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-ui-1.8.4.custom.min.js"></script>

<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.base.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.button.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.dialog.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.theme.css" rel="stylesheet" />

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/jquery-1.4.2.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.button.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.effects.core.js"></script>

<style>

.left-column{
	float: left;
	padding: 3px;
	width: 205px;
	text-align:right;
}
.left-column-wide{
	float: left;
	padding: 3px;
	width: 265px;
	text-align:right;
	line-height:2em;
}
.right-column{
	float: left;
	padding: 3px;
}
#orginfo h2, #posTypeAdv h2 {
	border-bottom: 1px solid #728DD4; 
	padding-bottom:3px;
}

#search-results .listing_info {
	padding-left:35px;
}
.dialog-form { 
	height: 159; 
}

#progressbar div{
		color:#ffffff;
    font-size: 14px;
    font-weight: bold;
		margin-right:0;
		padding:3px 7px 0;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
		background-color: #79A44E;
    font-size: 14px;
}
.accountboxoff{
	background-color: #BBBBBB;
    font-size: 14px;
}
.accounton{
	color: #003366;
	text-decoration:none;
}
.accountoff{
	color: #BBBBBB;
}

.number, #search-results .listing_info {//display:inline;}

#all_tabs {
    margin-bottom: 0;
}
#modalDialog { visibility: hidden; position: absolute; left: 0px; top: 0px; width:100%; height:100%; text-align:center; z-index: 1000; background: repeat-x scroll 50% 50% #AAAAAA; opacity: 0.3;}
#modalDialogInner  { width:300px; margin: 0px 200px; background-color: #fff; border:1px solid #000; padding:15px; text-align:center; visibility: hidden; position: absolute; text-align:center; z-index: 1000; background: #fff; opacity: 1.0;
submitbutton{
	display:none;
}

</style>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
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



int iLocalVolTID = 17254,iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,
		iSocJustGrpsTID = 17266, iLocalOrgsTID = 21853, iSTMTID=17258;

// non-dropdown taxonomy term id's (tid)

// vidRoomBoard=265										&roomboard=iYesRoomBoard8106
int iNoRoomBoard=8105, iYesRoomBoard=8106;

// vidStipend=266
int iNoStipend=8107, iYesStipend=8108;


int tidworkstudyUser=6225;
int tidBoardMemberUser=6217;
//int iVolBoardTID=17256;
int tidBoardMemberOpp=4761;
int tidworkstudyOpp=4769;
//int iLocalVolTID=17254;
//int iSumIntrnTID=17259;
//int iIntrnTID=17258;
//int iVolVirtTID=17257;
//int iGroupFamilyTID=17255;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszBothTID = "" + iBoth;
String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;

String search=null, aszPartnerSecondDiv="display: none;", aszSubmit="display: none;";

String aszLocal = "display:inline;";
String aszVirtual = "display:inline;";
String aszGrpFamily = "display:inline;";
String aszSTM = "display:inline;";
String aszSumIntrn = "display:inline;";
String aszIntrn = "display:inline;";
String aszLocalOrgs = "display:inline;";
String aszAdvanced = "display:inline;";


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

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

ArrayList aPosFreqList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeList( aPosFreqList, vidPosFreq );


String aszLocalRegion = "";
if(org.getORGAddrCountryName().equalsIgnoreCase("us")){
	if(org.getCityTID() > 0){
		aszLocalRegion = "&citytid=" + org.getCityTID();
	}else{
		aszLocalRegion = "&postalcode=" + org.getORGAddrPostalcode();
	}
}else{
	if(org.getCountryTID()>0){
		aszLocalRegion = "&countrytid=" + org.getCountryTID();
	}else{
		aszLocalRegion = "&country=" + org.getORGAddrCountryName();
	}
}
String aszNotLocalRegion = "";
if(org.getORGAddrCountryName().equalsIgnoreCase("us")){
	if(org.getCityTID() > 0){
		aszNotLocalRegion = "&notlocalcitytid=" + org.getCityTID();
	}else{
		aszNotLocalRegion = "&notlocalpostal=" + org.getORGAddrPostalcode();
	}
}else{
	if(org.getCountryTID()>0){
		aszNotLocalRegion = "&notlocalcntrytid=" + org.getCountryTID();
	}else{
		aszNotLocalRegion = "&notcountry=" + org.getORGAddrCountryName();
	}
}

String localURL="oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsAdminSelect"+
	aszLocalRegion+// need to somehow NEGATE local for STM
	"&postype=4794"+
	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	localURL+="&requiredcodetype=No";
}

String allURL="oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsAdminSelect"+
	"&postype=";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	allURL+="&requiredcodetype=No";
}
String stmURL="oppsrch.do?"+
	"method=processOppSearchAll"+
	"&requesttype=myResultsAdminSelect";//+
//	"&postype=4796";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	stmURL+="&requiredcodetype=No";
}
String localOrgsURL="oppsrch.do?"+
	"method=processOrgSearchAll"+
	"&requesttype=myResultsAdminSelect"+
	aszLocalRegion+
	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	localOrgsURL+="&requiredcodetype=No";
}
String stmSearchURL="oppsrch.do?"+ // should have a search in this page itself that then loads its results via ajax with this url
	"method=processOppSearchAll"+
	"&requesttype=myResultsAdminSelect"+
	aszLocalRegion;//+// need to somehow NEGATE local for STM
//	aszLocalRegion+
//	"&postype=4794"+
//	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	stmSearchURL+="&requiredcodetype=No";
}
String advancedSearchURL="oppsrch.do?"+ // should have a search in this page itself that then loads its results via ajax with this url
	"method=processOppSearchAll"+
	"&requesttype=myResultsAdminSelect"+
	aszNotLocalRegion;//+
//	"&postype=4794"+
//	"&distance=City";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	advancedSearchURL+="&requiredcodetype=No";
}

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}

%>


<script language="javascript">
function selected_disabled(object, item1){
	var inputvalue=object.value;
	if(inputvalue.length>0)
		document.getElementById(item1).disabled = true;
	else
		document.getElementById(item1).disabled = false;
}
function ministry_local_orgs(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';
	
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_all').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "active";
	
	sendRequest('GET', '<%=aszPortal%>/<%=localOrgsURL%>');
}
function ministry_local(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';
	
	document.getElementById('ministry_tab_local_one_time').className = "active";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_all').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', '<%=aszPortal%>/<%=localURL%>');
}
function ministry_all(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';
	
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_all').className = "active";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', '<%=aszPortal%>/<%=allURL%>');
}
function ministry_stm(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';
	
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "active";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_all').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', '<%=aszPortal%>/<%=stmURL%>');
}
function stm_search_tab(){
	document.getElementById('ajax_res').style.display='none';
	document.getElementById('ministry_stm_search').style.display='inline';
	document.getElementById('ministry_stm_search_link').style.display='inline';
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';

	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_all').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_stm_search').className = "active";
	document.getElementById('ministry_tab_local_orgs').className = "";
}
function advanced_search_tab(){
	document.getElementById('ajax_res').style.display='none';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	document.getElementById('ministry_advanced_search').style.display='inline';
	document.getElementById('ministry_advanced_search_link').style.display='inline';

	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_all').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "active";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
}

function orgsrch_search(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	var orgsrchSearchURL = '<%=aszPortal%>/oppsrch.do';
	orgsrchSearchURL = orgsrchSearchURL + '?method=processOrgSearchAll&requesttype=myResultsAdminSelect';
	
	if(document.getElementById('postalcodeorgsrch').value != null){
		orgsrchSearchURL = orgsrchSearchURL + '&postalcode=' + document.getElementById('postalcodeorgsrch').value;
	}
	if(document.getElementById('distanceorgsrch').value != null){
		orgsrchSearchURL = orgsrchSearchURL + '&distance=' + document.getElementById('distanceorgsrch').value;
	}
	
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';

	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "active";
	
	sendRequest('GET', orgsrchSearchURL);
}

function oppsrchnone_search(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	var oppsrchnoneSearchURL = '<%=aszPortal%>/oppsrch.do';
	oppsrchnoneSearchURL = oppsrchnoneSearchURL + '?method=processOppSearchAll&requesttype=myResultsAdminSelect';
	
	if(document.getElementById('postalcodeoppsrchnone').value != null){
		oppsrchnoneSearchURL = oppsrchnoneSearchURL + '&postalcode=' + document.getElementById('postalcodeoppsrchnone').value;
	}
	if(document.getElementById('servicearea1oppsrchnone').value != null){
		oppsrchnoneSearchURL = oppsrchnoneSearchURL + '&servicearea1=' + document.getElementById('servicearea1oppsrchnone').value;
	}
	if(document.getElementById('skills1idoppsrchnone').value != null){
		oppsrchnoneSearchURL = oppsrchnoneSearchURL + '&skills1=' + document.getElementById('skills1idoppsrchnone').value;
	}
	if(document.getElementById('countryoppsrchnone').value != null){
		oppsrchnoneSearchURL = oppsrchnoneSearchURL + '&country=' + document.getElementById('countryoppsrchnone').value;
	}
	if(document.getElementById('greatforgroupoppsrchnone').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforgroup=' + document.getElementById('greatforgroupoppsrchnone').value;
	}
	if(document.getElementById('greatforfamilyoppsrchnone').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforfamily=' + document.getElementById('greatforfamilyoppsrchnone').value;
	}
	if(document.getElementById('greatforkidoppsrchnone').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforkid=' + document.getElementById('greatforkidoppsrchnone').value;
	}
	if(document.getElementById('greatforteenoppsrchnone').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforteen=' + document.getElementById('greatforteenoppsrchnone').value;
	}
	if(document.getElementById('greatforsenioroppsrchnone').checked==true){
		advancedSearchURL = advancedSearchURL + '&senior=' + document.getElementById('greatforsenioroppsrchnone').value;
	}
	
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';

	document.getElementById('ministry_tab_local_one_time').className = "active";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', oppsrchnoneSearchURL);
}

function stm_search(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_stm_search').style.display='inline';
	document.getElementById('ministry_stm_search_link').style.display='inline';
	var stmSearchURL = '<%=aszPortal%>/oppsrch.do';
	stmSearchURL = stmSearchURL + '?method=processOppSearchAll&requesttype=myResultsAdminSelect<%=aszNotLocalRegion%>';
	
	if(document.getElementById('postalcodeSTM').value != null){
		stmSearchURL = stmSearchURL + '&postalcode=' + document.getElementById('postalcodeSTM').value;
	}
//alert ('1'+stmSearchURL);
	if(document.getElementById('countrySTM').value != null){
		stmSearchURL = stmSearchURL + '&country=' + document.getElementById('countrySTM').value;
	}
	if(document.getElementById('regiontidSTM').value != null){
		stmSearchURL = stmSearchURL + '&regiontid=' + document.getElementById('regiontidSTM').value;
	}
//alert ('2'+stmSearchURL);
	if(document.getElementById('servicearea1STM').value != null){
		stmSearchURL = stmSearchURL + '&servicearea1=' + document.getElementById('servicearea1STM').value;
	}
//alert ('3'+stmSearchURL);
	if(document.getElementById('durationSTM').value != null){
		stmSearchURL = stmSearchURL + '&duration=' + document.getElementById('durationSTM').value;
	}
//alert ('4'+stmSearchURL);
	if(document.getElementById('roomboardSTM').checked==true){
		stmSearchURL = stmSearchURL + '&roomboard=' + document.getElementById('roomboardSTM').value;
	}
//alert ('5'+stmSearchURL);
	if(document.getElementById('stipendSTM').checked==true){
		stmSearchURL = stmSearchURL + '&stipend=' + document.getElementById('stipendSTM').value;
	}
//alert ('6'+stmSearchURL);
	if(document.getElementById('medinsurSTM').checked==true){
		stmSearchURL = stmSearchURL + '&medinsur=' + document.getElementById('medinsurSTM').value;
	}
//alert ('7'+stmSearchURL);
	if(document.getElementById('transportSTM').checked==true){
		stmSearchURL = stmSearchURL + '&transport=' + document.getElementById('transportSTM').value;
	}
//alert ('8'+stmSearchURL);
	if(document.getElementById('americorpsSTM').checked==true){
		stmSearchURL = stmSearchURL + '&americorps=' + document.getElementById('americorpsSTM').value;
	}

//alert (stmSearchURL);
	
	document.getElementById('ministry_advanced_search').style.display='none';
	document.getElementById('ministry_advanced_search_link').style.display='none';

	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "";
	document.getElementById('ministry_tab_stm_search').className = "active";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', stmSearchURL);
}

	
function advanced_search(){
	document.getElementById('ajax_res').style.display='inline';
	document.getElementById('ministry_advanced_search').style.display='inline';
	document.getElementById('ministry_advanced_search_link').style.display='inline';
	var advancedSearchURL = '<%=aszPortal%>/oppsrch.do';
	advancedSearchURL = advancedSearchURL + '?requesttype=myResultsAdminSelect';
<% if(bAssoc==false){ %>
	if(document.getElementById('methodAdvOrg').checked==true){
		advancedSearchURL = advancedSearchURL + '&method=' + document.getElementById('methodAdvOrg').value;
	} else if(document.getElementById('methodAdvOpp').checked==true){
		advancedSearchURL = advancedSearchURL + '&method=' + document.getElementById('methodAdvOpp').value;
	}else{
		advancedSearchURL = advancedSearchURL + '&method=processOppSearchAll';
	}
//alert(advancedSearchURL);	
	if(document.getElementById('postalcodeAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&postalcode=' + document.getElementById('postalcodeAdv').value;
	}
	if(document.getElementById('countryAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&country=' + document.getElementById('countryAdv').value;
	}
	if(document.getElementById('servicearea1Adv').value != null){
		advancedSearchURL = advancedSearchURL + '&servicearea1=' + document.getElementById('servicearea1Adv').value;
	}
	if(document.getElementById('servicearea2Adv').value != null){
		advancedSearchURL = advancedSearchURL + '&servicearea2=' + document.getElementById('servicearea2Adv').value;
	}
	if(document.getElementById('servicearea3Adv').value != null){
		advancedSearchURL = advancedSearchURL + '&servicearea3=' + document.getElementById('servicearea3Adv').value;
	}
	if(document.getElementById('regiontidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&regiontid=' + document.getElementById('regiontidAdv').value;
	}
	if(document.getElementById('greatforgroupAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforgroup=' + document.getElementById('greatforgroupAdv').value;
	}
	if(document.getElementById('greatforfamilyAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforfamily=' + document.getElementById('greatforfamilyAdv').value;
	}
	if(document.getElementById('greatforkidAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforkid=' + document.getElementById('greatforkidAdv').value;
	}
	if(document.getElementById('greatforteenAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&greatforteen=' + document.getElementById('greatforteenAdv').value;
	}
	if(document.getElementById('greatforseniorAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&senior=' + document.getElementById('greatforseniorAdv').value;
	}
	if(document.getElementById('frequencyAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&frequency=' + document.getElementById('frequencyAdv').value;
	}
	if(document.getElementById('postypeAdvLocal').checked==true){
		advancedSearchURL = advancedSearchURL + '&postype=' + document.getElementById('postypeAdvLocal').value;
	}else if(document.getElementById('postypeAdvVirtual').checked==true){
		advancedSearchURL = advancedSearchURL + '&postype=' + document.getElementById('postypeAdvVirtual').value;
	}else if(document.getElementById('postypeAdvSTM').checked==true){
		advancedSearchURL = advancedSearchURL + '&postype=' + document.getElementById('postypeAdvSTM').value;
	}else if(document.getElementById('postypeAdvAll').checked==true){
		advancedSearchURL = advancedSearchURL + '&postype=' + document.getElementById('postypeAdvAll').value;
	}
	if(document.getElementById('roomboardAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&roomboard=' + document.getElementById('roomboardAdv').value;
	}
	if(document.getElementById('stipendAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&stipend=' + document.getElementById('stipendAdv').value;
	}
	if(document.getElementById('medinsurAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&medinsur=' + document.getElementById('medinsurAdv').value;
	}
	if(document.getElementById('transportAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&transport=' + document.getElementById('transportAdv').value;
	}
	if(document.getElementById('americorpsAdv').checked==true){
		advancedSearchURL = advancedSearchURL + '&americorps=' + document.getElementById('americorpsAdv').value;
	}
	if(document.getElementById('durationAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&duration=' + document.getElementById('durationAdv').value;
	}
	if(document.getElementById('mingroupAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&mingroup=' + document.getElementById('mingroupAdv').value;
	}
	if(document.getElementById('maxgroupAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&maxgroup=' + document.getElementById('maxgroupAdv').value;
	}
	if(document.getElementById('cityAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&city=' + document.getElementById('cityAdv').value;
	}
	if(document.getElementById('stateAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&state=' + document.getElementById('stateAdv').value;
	}
	if(document.getElementById('orgnameAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&orgname=' + document.getElementById('orgnameAdv').value;
	}
	if(document.getElementById('skills1idAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&skills1id=' + document.getElementById('skills1idAdv').value;
	}
	if(document.getElementById('skills2idAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&skills2id=' + document.getElementById('skills2idAdv').value;
	}
	if(document.getElementById('skills3idAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&skills3id=' + document.getElementById('skills3idAdv').value;
	}
	if(document.getElementById('denomaffiltidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&denomaffiltid=' + document.getElementById('denomaffiltidAdv').value;
	}
	if(document.getElementById('orgaffil1tidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&orgaffil1tid=' + document.getElementById('orgaffil1tidAdv').value;
	}
	if(document.getElementById('orgaffil2tidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&orgaffil2tid=' + document.getElementById('orgaffil2tidAdv').value;
	}
	if(document.getElementById('orgaffil3tidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&orgaffil3tid=' + document.getElementById('orgaffil3tidAdv').value;
	}
	if(document.getElementById('programtypetidAdv').value != null){
		advancedSearchURL = advancedSearchURL + '&programtypetid=' + document.getElementById('programtypetidAdv').value;
	}
<% }else{ %>
	advancedSearchURL = advancedSearchURL + '&method=processOrgSearchAll&assoconly=false';
	if(document.getElementById('postalcodeOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&postalcode=' + document.getElementById('postalcodeOrg').value;
	}
	if(document.getElementById('regionOrg').value != null){
		var region=document.getElementById('regionOrg').value;
		if(region.length > 0){
			advancedSearchURL = advancedSearchURL + '&regiontid=' + document.getElementById('regionOrg').value;
		}else if(document.getElementById('countryOrg').value != null){
			advancedSearchURL = advancedSearchURL + '&country=' + document.getElementById('countryOrg').value;
		}
	}else if(document.getElementById('countryOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&country=' + document.getElementById('countryOrg').value;
	}
	if(document.getElementById('orgnameOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&orgname=' + document.getElementById('orgnameOrg').value;
	}
	if(document.getElementById('programtypetidOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&programtype=' + document.getElementById('programtypetidOrg').value;
	}
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	if(document.getElementById('denomaffiltidOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&denomaffiltid=' + document.getElementById('denomaffiltidOrg').value;
	}
<% } %>
	if(document.getElementById('orgaffil1tidOrg').value != null){
		advancedSearchURL = advancedSearchURL + '&orgaffil1tid=' + document.getElementById('orgaffil1tidOrg').value;
	}
<% } %>
//alert (advancedSearchURL);
	
	document.getElementById('ministry_stm_search').style.display='none';
	document.getElementById('ministry_stm_search_link').style.display='none';
	
	document.getElementById('ministry_tab_local_one_time').className = "";
	document.getElementById('ministry_tab_stm').className = "";
	document.getElementById('ministry_tab_advanced_search').className = "active";
	document.getElementById('ministry_tab_stm_search').className = "";
	document.getElementById('ministry_tab_local_orgs').className = "";
	
	sendRequest('GET', advancedSearchURL);
}

function alertAll(){alert('hello world');}

function checkAll(field){
	for(i=0; i < field.length; i++)
		field[i].checked=true;
}
function uncheckAll(field){
	for(i=0; i < field.length; i++)
		field[i].checked=false;
}
function toggleCheckAllOrgTab(obj){
	if (obj.checked==1){
		checkAll(document.orgForm.orgnids);
	}else{
		uncheckAll(document.orgForm.orgnids);
	}
}
function toggleCheckAllOppsTab(obj){
	if (obj.checked==1){
		checkAll(document.orgForm.oppnids);
	}else{
		uncheckAll(document.orgForm.oppnids);
	}
}
function addToListings() { 
//alert('submitForm');
			submitForm();
}
function next_step(){
<% if(bAssoc==true){ %>
	window.location='<%=aszPortal%>/associationmanagement.jsp';
<% } %>
	var orgnid = $('#orgnid').text();
	var portal = $('#portal').text();
	var association = $('#association').text();
	
	// set mapping redirect accordingly (windowlocation)
	var new_location = portal+'/org.do?method=showOrgAddOpp1&orgnid='+orgnid;
	if(association==true || association=='true'){
		new_location = portal+'/associationmanagement.jsp';
	}
	$('#requesttype').val(new_location);
	
	// change input tag so that it handles the redirect in mapping page rather than reloading the same page (needs a new window.location)
	document.getElementById('submitOrgForm').click();

}
	
$(document).ready(function() {
	var hashtag = '';
	if(location.hash){
		hashtag = location.hash.substring(1);
	}
<% if(bManageAssoc==true){  %>
//	advanced_search_tab();
	ministry_local_orgs();
<% }else{ %>
	if (hashtag == "localorgs"){
		ministry_local_orgs();
	}else if (hashtag == "local"){
		ministry_local();
	}else if (hashtag == "stm"){
		ministry_stm();
	}else if (hashtag == "all"){
		ministry_all();
	}else if (hashtag == "stmsearch"){
		stm_search_tab();
	}else if (hashtag == "advancedsearch"){
		advanced_search_tab();
	}else{
		ministry_local();
	}
<% } %>
 });  

$(function() {
	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	//$("#dialog").dialog("destroy");
	
	var maptopage2 = $("#maptopage2")
		allFields = $([]).add(maptopage2);
	$("#dialog-form").dialog({
		autoOpen: false,
		//height: 200,
		//width: 350,
		modal: true,
		buttons: {
			'Yes, Add to my listings': function() {
				var bValid = true;
				allFields.removeClass('ui-state-error');
				if (bValid) {
					document.getElementById('favoritechildopps').value=true;
					$(this).dialog('close');
				}
			},
			'No, I will individually select opportunities': function() {
					document.getElementById('favoritechildopps').value=false;
				$(this).dialog('close');
			}
		},
		close: function() {
			submitForm();
			allFields.val('').removeClass('ui-state-error');
		}
	});
	$('#submitOrgsButton') .button()
		.click(function() {
//alert('hello world');			
			$('#dialog-form').dialog('open');
		});
});
function submitForm(){
	document.getElementById('submitOrgForm').click();
//	document.forms["orgForm"].submit();	
}
function showModalDialog() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
}
function submitModalDialogYes() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 

	document.getElementById("favoritechildopps").value='true';
//alert('submitForm');
			submitForm();
}
function submitModalDialogNo() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 

	document.getElementById("favoritechildopps").value='false';
//alert('submitForm');
			submitForm();
}
</script>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(true==false){ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch" >
	  <span id="title" >search </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/advancedsearch.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
<div id="orgnid" style="display:none;"><%=org.getORGNID()%></div>
<div id="portal" style="display:none;"><%=aszPortal%></span></div>
<div id="association" style="display:none;"><% out.print(""+bAssoc);%></div>

<% if(bAssoc==false){ %>
<!-- START progressbar -->
<DIV id="progressbar">
<table cellpadding="2"><tr>
	<td><div class="accountboxoff">1</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid=<%=org.getORGNID()%>" class="accountoff">Add <%=aszOrgOrChurch%><br>Profile</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxon">2</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/churchopportunities.jsp" class="accounton">Choose from Existing Organizations<br>& Service Opportunties</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">3</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>" class="accountoff">Create Own <%=aszOrgOrChurch%><br><%=aszOrgOrChurchOpp%> Opportunities</a></td>
</tr></table>
<p id="steps_description">
<h3 style="padding-bottom:25px;">
<%
String aszDisplayStepsDescription="long";
try{if(aszReferer.contains("churchopportunities.jsp")	||
	aszReferer.contains("portalopportunities.jsp")	||
	aszReferer.contains("portalFavoriteOppsList")		||
	aszReferer.contains("portalFavoriteOrgsList")	
){
	aszDisplayStepsDescription="short";
}
}catch(Exception e){}
if(aszDisplayStepsDescription.equals("short")){
%>
<!--a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>"> <img style="float:right; padding-top:10px" border="0" src="http://www.christianvolunteering.org/imgs/next-step_button.png" alt="next step" /></a-->
<% }else{ %>
<font style="color:#003366">STEP 2:</font> 
In this step you add service opportunities in parachurches and nonprofits where you would like your church members to serve as a part of outreach 
to the community. You can add local or global missions opportunities by checking the box. 
Items you check will appear on your church portal. 
You will add internal service opportunities for your church next. <!--a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>"> <img style="float:right; padding-top:10px" border="0" src="http://www.christianvolunteering.org/imgs/next-step_button.png" alt="next step" /></a-->
<% } %>
</h3>
</p>
</DIV>

<!-- END progressbar -->
<% }else{ %>
<a href="<%=aszPortal%>/associationmanagement.jsp"> <img style="float:right; padding-top:10px" border="0" src="http://www.christianvolunteering.org/imgs/next-step_button.png" alt="next step" /></a>
<br clear="all">
<% } %>
	
<% 
String aszDisplayManageAssoc = "display:inline;";
if(bManageAssoc==true){ 
	aszDisplayManageAssoc = "display:none;";
}
%>

<div id="results" style="display:inline" clear="all">

<div id="all_tabs" class="portallist">
<!--h2>Choose from pre-existing Service Opportunities to show on your Church site:</h2-->

	<div style="<%=aszDisplayManageAssoc%>" id="ministrySectionLocal" class="ministryTab">
	<a id="ministry_tab_local_one_time" class="active" onClick="ministry_local()" name="requesttype" value="myResultsAdminSelect" href="#local"><span>Local Missions</span></a>
	</div>

	<div style="display:inline;" id="ministrySectionLocalOrgs" class="ministryTab">
	<a id="ministry_tab_local_orgs" class="active" onClick="ministry_local_orgs()" name="requesttype" value="myResultsAdminSelect" href="#localorgs"><span><%if(bManageAssoc==true){%>Browse All <% } %>Organizations</span></a>
	</div>

    <div style="display:none;" id="ministrySectionSTM" class="ministryTab">
	<a id="ministry_tab_stm" class="active" onClick="ministry_stm()" name="requesttype" value="myResultsAdminSelect" href="#stm">Short-Term<% if ( aszSecondaryHost.equalsIgnoreCase("volengivol")) { %> Interships<%} else {%> Missions<% } %> Opportunities</a>
	</div>

	<div style="display:inline;" id="ministrySectionAll" class="ministryTab">
	<a id="ministry_tab_all" class="active" onClick="ministry_all()" name="requesttype" value="myResultsAdminSelect" href="#all"><span>All Opportunities</span></a>
	</div>

    <div style="<%=aszDisplayManageAssoc%>" id="ministrySectionSTMSearch" class="ministryTab">
	<a id="ministry_tab_stm_search" class="active" onClick="stm_search_tab()" name="requesttype" value="myResultsAdminSelect" href="#stmsearch">Search Global<% if ( aszSecondaryHost.equalsIgnoreCase("volengivol")) { %> Interships<%} else {%> Missions<% } %></a>
	</div>

    <div style="display:inline;" id="ministrySectionAdvancedSearch" class="ministryTab">
	<a id="ministry_tab_advanced_search" class="active" onClick="advanced_search_tab()" name="requesttype" value="myResultsAdminSelect" href="#advancedsearch"><%if(bManageAssoc==true){%>Search Organizations<% }else{ %>Advanced Search<% } %></a>
	</div>
	

<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 0px;" />
</div> <!-- end: div id all_tabs-->



<!--- Stm link-->
<div id="ministry_stm_search_link" style="display:none;">
<a href="#stmsearchbox"><b>Refine your search</b></a>
<br />
</div>

<!--- advanced link-->
<div id="ministry_advanced_search_link" style="display:none;">
<a href="#advancedsearchbox"><b>Refine your search</b></a>
<br />
</div>



<div id="ajax_res">
	<center>
		<br>
		<h2>Please wait while we load your opportunities... </h2>
		<br>
		<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
		<br /><br /><br /><br /><br />
<!--button type="button" id="submitOrgsButton">Add to my Church Listings dialog</button-->
	</center>
</div>


<div class="cleardiv"></div>


<!--- Short Term Missions Search-->
<div id="ministry_stm_search" style="display:none;">
<br /><hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
<br />
<a name="stmsearchbox"> </a>
<form name="stmsearchbox" id="stmsearchbox" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" id="method" value="processOppSearchAll">
<input type="hidden" name="distance" id="distance" value="City">
<input type="hidden" name="postype" id="postype" value="4796">
<input type="hidden" name="requesttype" id="requesttype" value="myResultsAdminSelect">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" id="requiredcodetype" value="No">
<% } %>

<table style="font-size:12px; margin-left:100px;" border="0">
	<tr id="postaloppSTM">
		<td align="right">
			<strong>Postal Code</strong>
		</td>
		<td>
			<input name="postalcode" type="text" id="postalcodeSTM" style="width:60px;" size="20" />
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Country</strong>
		</td>
		<td colspan="2">
			<SELECT id="countrySTM" name="country" size="1" style="z-index: 0;" class="smalldropdown" onchange="selected_display(this, 'postaloppSTM')">
				<OPTION value=""></OPTION>
				<%
				aszTemp= "US";
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
							//out.print(" selected=selected ");
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeSTM', false);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppSTM').style.display='table-row';\" ");
						} else {
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeSTM', true);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppSTM').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="servicearea1STM" name="servicearea1" size="1" class="smalldropdown" > 
				<option value="" selected="selected"></option> 
				<%
					for(int index=0; index < aServiceList.size(); index++){
						iTemp = 0;
						aszTemp = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							if (iSubType == 4760 ||
								iSubType == 4764 ||
								iSubType == 4772 ||
								iSubType == 4773 ||
								iSubType == 4783 ||
								iSubType == 4785 ||
								iSubType == 4787 ||
								iSubType == 4789 ||
								iSubType == 7341 ||
								iSubType == 7342 ){
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
							}else if (iSubType == 6843){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Senior / Elderly Outreach</option> ");
							}else if (iSubType == 4774){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Food Service / Hunger</option> ");
							}else if (iSubType == 4782){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Prison Outreach</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Region</strong>
		</td>
		<td colspan="2">
			<SELECT id="regiontidSTM" name="regiontid" size="1" style="z-index: 0;" class="smalldropdown" onchange="selected_disabled( this, 'countrySTM')">
				<OPTION value=""></OPTION>
				<%
					for(int index=0; index < aRegionList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Length of Trip</strong>
		</td>
		<td>
			<SELECT id="durationSTM" name="duration" size="1" style="z-index: 0;" class="smalldropdown">
				<option value=""></option>
				<option value="8091">Less than a week</option>
				<option value="8094">1 to 2 weeks</option>
				<option value="8095">3 to 4 weeks</option>
				<option value="8096">1 to 2 months</option>
				<option value="8092">Summer</option>
				<option value="8097">3 to 5 months</option>
				<option value="8093">1 year</option>
				<option value="8099">1 to 2 years</option>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<h5 class="benefits">Benefits Desired:</h5>
		</td>
		<td>
			<div class="search_options" style="float:left; margin-right: 20px;" class="form-item">
				<input id="roomboardSTM" name="roomboard" type="checkbox" value="<%=aszRoomBoardTID%>" />Room &amp; Board
				<br/>
				<input id="stipendSTM" name="stipend" type="checkbox" value="<%=aszStipendTID%>" />Stipend
			</div> <!--end div search options-->
			
			<div class="benefits" style="width:150px; float:left;">
				<input id="medinsurSTM" name="medinsur" type="checkbox" value="<%=aszMedInsurTID%>" />Medical Insurance
				<br/>
				<input id="transportSTM" name="transport" type="checkbox" value="<%=aszTransportTID%>" />Transportation
				<br/>
				<input id="americorpsSTM" name="americorps" type="checkbox" value="<%=aszAmeriCorpsTID%>" />AmeriCorps
			</div> <!--end div benefits-->
		</td>
	</tr>
</table>
</form>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" id="submitButton" onclick="stm_search()">Search</button>
<br><br />
<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
<br>
</div><!-- end cvstmsearch -->
<!--- end Short Term Missions Search-->





<!--- Advanced Search-->
<div id="ministry_advanced_search" style="display:none;">
<br /><hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
<br />
<a name="advancedsearchbox"> </a>
<% if(bManageAssoc==false){ %>
<%@ include file="/template_include/advanced_search_form.inc" %>
<% }else{ %>


<form name="orgsearch" id="orgsearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
<input type="hidden" name="portallist" value="true">
<% } %>
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<input type="hidden" name="requesttype" value="urlalias">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" name="assoconly" value="false">


<table style="font-size:12px; margin-left:100px;" border="0">
	<tr>
		<td align="right">
			<strong>
				Postal Code &amp; Country
			</strong>
		</td>
		<td colspan="2">
			<div style="float:left; margin-right:5px;">
				<input name="postalcode" type="text" id="postalcodeOrg" size="20" style="width:60px;"/>
			</div>
			<SELECT id="countryOrg" name="country" size="1" style="z-index: 0; width:110px; float:left;" class="smalldropdown" onchange="selected_disabled(this, 'postalcodeOrg')">
				<option value=""></option>
		    	<%
				aszTemp= "US";
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
							out.print(" selected=selected ");
//							out.print( "onClick=\"javascript:document.getElementById('postallabelOrg').style.display='none';javascript:document.getElementById('postalOrg').style.display='inline';\" ");
						} else {
//							out.print( "onClick=\"javascript:document.getElementById('postallabelOrg').style.display='inline';javascript:document.getElementById('postalOrg').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Region</strong>
		</td>
		<td colspan="2">
			<SELECT id="regionOrg" name="regiontid" size="1" style="z-index: 0;" class="smalldropdown" onchange="selected_disabled( this, 'countryOrg')">
				<OPTION value=""></OPTION>
				<%
					for(int index=0; index < aRegionList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<TD align="right">
			<strong>Organization Name</strong>
		</TD>
		<TD colspan="2">
			<input type="text" name="orgname" id="orgnameOrg" size="30" maxlength="200" styleClass="textinputwhite"/>
		</TD>
	</tr>
	<tr>
		<td align="right">
			<strong>Program Type</strong>
		</td>
		<td colspan="2">
			<SELECT id="programtypetidOrg" name="programtypetid" size="1" class="smalldropdown" >
				<option value="" selected="selected"></option>
				<%
				for(int index=0; index < aProgramList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
					if(null == aAppCode) continue;
					String aszOptRqCode = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					if(aszSecondaryHost.equalsIgnoreCase("volengivol")) {
						if (iSubType == 5239 || iSubType == 25188){
						}else{
							out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}else{
						out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
				%>
			</SELECT> 
		</td>
	</tr>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<tr>
		<TD class=set valign="top" align="right">
			<strong>Denominational<br>Affiliation</strong>
		</TD>
		<TD colspan="2">
			<SELECT id="denomaffiltidOrg" name="denomaffiltid" class="smalldropdown" >
				<option value="" selected></option>
				<%
				if(aszHostID.equalsIgnoreCase("volengcatholic")){
					iTemp=992;			
				} else {
					iTemp=0;
				}
				for(int index=0; index < afiliationList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
					if(null == aAppCode) continue;
					String aszOptRqCode = aAppCode.getAPCDisplay();
					int iTid = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid==iTemp) out.print(" selected=selected ");
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
	
				}
				%>
		    </SELECT>
		</TD>
	</tr>
	<tr>
		<TD align="right">
			<strong>Affiliations</strong>
		</TD>
		<TD valign="top">
			<SELECT id="orgaffil1tidOrg" name="orgaffil1tid" class="smalldropdown" style="margin-top: 5px;"><!-- onchange="javascript:document.getElementById('affiliation2').style.display='table-row';"--> 
				<option value=""></option>
				<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
				if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
					iTemp=1069;			
				} else if(aszHostID.equalsIgnoreCase("volengagrm")){
					iTemp=717;			
				} else {
					iTemp=0;
				}
				for(int index=0; index < apartnersList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
					if(null == aAppCode) continue;
					String aszOptRqCode = aAppCode.getAPCDisplay();
					int iTid = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid == iTemp ) out.print(" selected=selected ");
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
				%>
			</SELECT>
		</td>
	</tr>
<% } %>

	<tr>
		<td colspan=3></td>
	</tr>
</table>
</form>


<% } %>
<!--hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" /-->
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" id="submitButton" onclick="advanced_search()">Search</button>
<br><br />
<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 1px;" />
<br>
</div>
<!--- end Advanced Search-->

<a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>"><img style="float:right; margin-top:-15px;" border="0" src="/imgs/next-step_button.png" alt="next step" /></a>

<div class="cleardiv"></div>


<p><br /></p>
</div>
</div>
</div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

