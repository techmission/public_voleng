<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->
<bean:define id="theStatus" name="orgForm" property="errormsg" type="java.lang.String"/>
<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<% int iUserListCount=0; %>
<logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
	<% iUserListCount++; %>
</logic:iterate>
<%
int iCount=0;
String aszClass="";
int[] a_iContainer= new int[1];
int iArraySize = 0;

String thereWasError;
if(theStatus.equalsIgnoreCase("")){
  thereWasError = "?newopp";
}else{
  thereWasError = "?oppnew";
}

String actionURL="/org.do"; 
if ( (aszPath.equalsIgnoreCase("/voleng2/nonp-addlisting.jsp")) || (aszPath.equalsIgnoreCase("/voleng2/nonp-dupliclisting.jsp"))){ 
	actionURL = actionURL + thereWasError; 
}
if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
        aszSubdomain = "iVolunteering.org";
}

String aszMemberType = org.getORGMembertype();
boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") ||
	(
		aszMemberType.equalsIgnoreCase("Church")  && 
		(
			aszHostID.equalsIgnoreCase("voleng1") 	|| 
			aszHostID.equalsIgnoreCase("default")	||
			aszHostID.equalsIgnoreCase("volengexample")
		)
	)
){
	bFaith=true;
	aszOrgOrChurch="Church";
	aszOrgOrChurchOpp="Ministry";
}



int iTemp = 0;


// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidPosFreq=268, vidSchedDate=269, vidBenefits=286;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iJob=33389, iBoth=100;

// vidMemberType=42
//int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120, iSTSeasonal=25200;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;

// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszJobTID = "" + iJob, aszBothTID = "" + iBoth;
//String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing, aszSTSeasonalTID = "" + iSTSeasonal;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch") || bFaith==true ){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aHourCommitList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

ArrayList aServiceList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getAppCodeList( aHourCommitList, 171 );

String aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),80);

String aszBoth="", aszLoc="", aszVirt="", aszMiss="", aszJob="", aszWS="", aszWSNo="";
String aszReqCodeChck="", aszReqCodeNoChck="", aszBackChckChck="", aszBackChckNoChck="", aszReferReqChck="", aszReferReqNoChck="", 
	aszOneTimeChck="", aszOngoingChck="", aszSTSeasonalChck="", aszDateNoneChck="", aszDateRecurringChck="", aszDateYearChck="";

if(opp.getOPPStatus2().length()>1){
	aszBoth="checked=checked";
}else if(opp.getOPPPositionTypeTID()==iLocal){
	aszLoc="checked=checked";
}else if(opp.getOPPPositionTypeTID()==iWorkStudy){
	aszWS="checked=checked";
}else if(opp.getOPPPositionTypeTID()==iJob){
	aszJob="checked=checked";
}
if( opp.getOPPWorkStudyTID() == iYesWorkStudy){
	aszWS="checked=checked";
}else if( opp.getOPPWorkStudyTID() == iNoWorkStudy){
	aszWSNo="checked=checked";
}

String aszOppRequiredCodeType = opp.getOPPRequiredCodeType();
if(aszOppRequiredCodeType.equalsIgnoreCase("Yes")){

   aszReqCodeChck="checked=checked";
}else if(aszOppRequiredCodeType.equalsIgnoreCase("No")){

   aszReqCodeNoChck="checked=checked";
}
if(opp.getOPPBackgroundCheck().equalsIgnoreCase("Yes")){
   aszBackChckChck="checked=checked";
}else if(opp.getOPPBackgroundCheck().equalsIgnoreCase("No")){
   aszBackChckNoChck="checked=checked";
}
if(opp.getOPPReferenceReq().equalsIgnoreCase("Yes")){
   aszReferReqChck="checked=checked";
}else if(opp.getOPPReferenceReq().equalsIgnoreCase("No")){
   aszReferReqNoChck="checked=checked";
}
if(opp.getOPPFrequencyTID() == iOneTime){
   aszOneTimeChck="checked=checked";
}else if(opp.getOPPFrequencyTID() == iOngoing){
   aszOngoingChck="checked=checked";
}else if(opp.getOPPFrequencyTID() == iSTSeasonal){
   aszSTSeasonalChck="checked=checked";
}
if(opp.getOPPDaterequiredTID() == iNoDate){
   aszDateNoneChck="checked=checked";
}else if(opp.getOPPDaterequiredTID() == iRecurringDate){
   aszDateRecurringChck="checked=checked";
}else if(opp.getOPPDaterequiredTID() == iYearDate){
   aszDateYearChck="checked=checked";
}

String aszOrgStatement="display: none;";
String aszOppRequiredCodeDesc = opp.getOPPRequiredCodeDesc();
if(aszOppRequiredCodeDesc.equalsIgnoreCase("Organizational Statement of Faith") && (! aszSecondaryHost.equalsIgnoreCase("volengivol") )){
   aszOrgStatement="display: inline;";
}
String aszVirtual="display: inline;";
int iOppStatus = opp.getOPPPositionTypeTID();
if(iOppStatus==iVirtual){
   aszVirtual="display: none;";
	aszVirt="checked=checked";
}
String aszMissions="display: none;";
int iOppStatusM = opp.getOPPPositionTypeTID();
//out.println("opp position type: "+ iOppStatusM);
if(iOppStatusM==iShortTerm){
   aszMissions="display: inline;";
	aszMiss="checked=checked";
}
String aszDateReq="display: inline;";
int iOppDaterequired = opp.getOPPDaterequiredTID();
if(iOppDaterequired==iNoDate){
   aszDateReq="display: none;";
}

String aszGroups="", aszTeens="", aszKids="", aszSeniors="", aszFamilies="";






if( (
	(opp.getOPPGreatFor1TID() == iGroup) ||
	(opp.getOPPGreatFor2TID() == iGroup) ||
	(opp.getOPPGreatFor3TID() == iGroup) ||
	(opp.getOPPGreatFor4TID() == iGroup) ||
	(opp.getOPPGreatFor5TID() == iGroup) 
) ){
   aszGroups="checked";
}
if( (
	(opp.getOPPGreatFor1TID() == iTeen) ||
	(opp.getOPPGreatFor2TID() == iTeen) ||
	(opp.getOPPGreatFor3TID() == iTeen) ||
	(opp.getOPPGreatFor4TID() == iTeen) ||
	(opp.getOPPGreatFor5TID() == iTeen) 
) ){
   aszTeens="checked";
}
if( (
	(opp.getOPPGreatFor1TID() == iKid) ||
	(opp.getOPPGreatFor2TID() == iKid) ||
	(opp.getOPPGreatFor3TID() == iKid) ||
	(opp.getOPPGreatFor4TID() == iKid) ||
	(opp.getOPPGreatFor5TID() == iKid) 
) ){
   aszKids="checked";
}
if( (
	(opp.getOPPGreatFor1TID() == iSenior) ||
	(opp.getOPPGreatFor2TID() == iSenior) ||
	(opp.getOPPGreatFor3TID() == iSenior) ||
	(opp.getOPPGreatFor4TID() == iSenior) ||
	(opp.getOPPGreatFor5TID() == iSenior) 
) ){
   aszSeniors="checked";
}
if( (
	(opp.getOPPGreatFor1TID() == iFamily) ||
	(opp.getOPPGreatFor2TID() == iFamily) ||
	(opp.getOPPGreatFor3TID() == iFamily) ||
	(opp.getOPPGreatFor4TID() == iFamily) ||
	(opp.getOPPGreatFor5TID() == iFamily) 
) ){
   aszFamilies="checked";
}

String aszRoomBoard="", aszStipend="", aszMedInsur="", aszTransport="", aszAmeriCorps="";
if (opp.getOPPRoomBoardTID() > 0) {

	aszRoomBoard="checked";
}
int iStipend=opp.getOPPStipendTID();
if (iStipend > 0) {
	aszStipend="checked";
}

if (opp.getOPPMedInsurTID() > 0) {
	aszMedInsur="checked";
}

if (opp.getOPPTransportTID() > 0) {
	aszTransport="checked";
}

if (opp.getOPPAmeriCorpsTID() > 0) {
	aszAmeriCorps="checked";
}

String aszPrivateOpp="", aszPublicOpp="", aszHQ="", aszOffSite="", aszOffSiteIntl="";
if(opp.getOPPPrivate()==1){
	aszPrivateOpp="checked=checked";
}else if(opp.getOPPPrivate()==0){

	aszPublicOpp="checked=checked";
}

if(opp.getOPPHQorOffSite().equalsIgnoreCase("offsite")){
	aszOffSite="checked";
}else if(opp.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")){
	aszOffSiteIntl="checked";
}else{
	aszHQ="checked";
}


String aszPaymnt="display: none;";

if(iStipend > 0){// "Paid Stipend" taxonomy term_number
	aszPaymnt="display: inline;";
}





String aszLanguage = opp.getOPPLanguages();
String aszAddr1=opp.getOPPAddrLine1();

String aszCity=opp.getOPPAddrCity();

String aszPostal=opp.getOPPAddrPostalcode();

String aszState= opp.getOPPAddrStateprov();

String aszCountry= opp.getOPPAddrCountryName();


String aszIntTemp="", aszMonthTemp="";

long iTime = 0;
String aszAppDeadlineDate, aszStartDate="", aszEndDate="", 
	aszStartDateMonth="", aszStartDateDate="", aszStartDateYear="", 
	aszEndDateMonth="", aszEndDateDate="", aszEndDateYear="";
iTime = opp.getOPPApplicDeadlineNum();
if (iTime > 0) {
	aszAppDeadlineDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
}else{
	aszAppDeadlineDate = "";
}
iTime = 0;
iTime = opp.getOPPStartDtNum();
if (iTime > 0) {
	aszStartDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
	if(aszStartDate.length()>1){
		aszStartDateMonth = aszStartDate.substring(0,2);
		aszStartDateDate = aszStartDate.substring(3,5);
		aszStartDateYear = aszStartDate.substring(6,10);
	}
}else{
	aszStartDate = "";
}
iTime = 0;
iTime = opp.getOPPEndDtNum();
if (iTime > 0) {
	aszEndDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
	if(aszEndDate.length()>1){
		aszEndDateMonth = aszEndDate.substring(0,2);
		aszEndDateDate = aszEndDate.substring(3,5);
		aszEndDateYear = aszEndDate.substring(6,10);
	}
}else{
	aszEndDate = "";
}


// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

















%>

<script language="javascript">
  function showOrgStmt( e )
  {
    if ( !e ) {
	    var e = e || window.event;
	    e.cancelBubble = true;
	    if (e.stopPropagation) e.stopPropagation();
    }
    /* rest of function... */
	document.getElementById('orgStatement').style.display='inline';
  }

function internal_serviceareas(newlist,num){
	var servElem = document.getElementById('oppservicelistchurchvol'+num);
	var servIndex = servElem.selectedIndex;
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
	
	newElem.options.length = 0;
	for(var i=0; i<servElem.options.length; i++) { 
		tmpValue = servElem.options[i].value;
		tmpText = servElem.options[i].text;

		if (i != servIndex) { 
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue); 
		}else{
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue, " \"selected=selected\" "); 
			if(num>0)	document.getElementById('oppservicearea'+num).style.display='inline';
		}
	}
	
}
function outreach_serviceareas(newlist,num){
	var servElem = document.getElementById('oppservicelistchrisvol'+num); 
	var servIndex = servElem.selectedIndex;
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
	
	newElem.options.length = 0;
	for(var i=0; i<servElem.options.length; i++) {
		tmpValue = servElem.options[i].value;
		tmpText = servElem.options[i].text;

		if (i != servIndex) { 
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue); 
		}else{
			newElem.options[newElem.options.length] = new Option(tmpText,tmpValue, " \"selected=selected\" "); 
			if(num>0)	document.getElementById('oppservicearea'+num).style.display='inline';
		}
	}
	
}


function clicked_private(box){
	var vis = (box.checked);
}

$(document).ready(function() {
	if($('#err').text().length > 0){
		$('#err').show();
	}else{
		$('#err').hide();
	}
<% if(opp.getOPPPositionTypeTID()==iJob){ %>
	clicked_job();
<% } %>
<%// if(aszHostID.equalsIgnoreCase("volengchurch") && opp.getOPPPrivate()==-1){ %>
<% if(	bFaith==true &&
		portal.length()>1 && 
		opp.getOPPPrivate()==-1
){ %>
        $('input[name=privateopp]').attr('checked', true);
<% } %>
<% if(opp.getOPPHQorOffSite().equalsIgnoreCase("offsite")  || opp.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")       ){ %>
        outreach_serviceareas('oppservicearea1tid','');
        outreach_serviceareas('oppservicearea2tid','2');
        outreach_serviceareas('oppservicearea3tid','3');
<% } %>

<% if(portal.length()>0 && showPortalInfo==true){ %>	
	<% if(iOppStatusM==iShortTerm){ %>
		$('input:radio[name=hqoroffsite]').filter('[value=offsite_intl]').attr('checked', true);
	<% }else if(iOppStatusM==iLocal && (! (opp.getOPPHQorOffSite().equalsIgnoreCase("hq")))){ %>
		$('input:radio[name=hqoroffsite]').filter('[value=offsite]').attr('checked', true);
	<% } %>
	clicked_hqoroffsite();
<% } %>

 });

	function change_address(){
		var addressSet = $('input:radio[name=hqoroffsite]:checked').val();
		if(addressSet=="offsite" || addressSet=="offsite_intl" || addressSet=="noaddress"){
			$('input:text[name=addr1]').val('');
			$('input:text[name=city]').val('');
			$('input:text[name=postalcode]').val('');			
			$("#state").val("");
			$("#country").val("");	
<% if(bFaith==true){ %>
			outreach_serviceareas('oppservicearea1tid','');
			outreach_serviceareas('oppservicearea2tid','2');
			outreach_serviceareas('oppservicearea3tid','3');
<% } %>
		}else if (addressSet=="hq" || addressSet=="address"){
			$('input:text[name=addr1]').val('<%=aszAddr1%>');
			$('input:text[name=city]').val('<%=aszCity%>');
			$('input:text[name=postalcode]').val('<%=aszPostal%>');			
			$("#state").val("<%=aszState%>");
			$("#country").val("<%=aszCountry%>");	
<% if(bFaith==true){ %>
			internal_serviceareas('oppservicearea1tid','');
			internal_serviceareas('oppservicearea2tid','2');
			internal_serviceareas('oppservicearea3tid','3');
<% } %>
		}
	}
	
	// clear default values from fields when selected (used with address)
	function clearText(thefield,thefield2,thefield3,thefield4,thefield5,thefield6){
	if (thefield.defaultValue==thefield.value)
	thefield.value = ""
	if (thefield2.defaultValue==thefield2.value)
	thefield2.value = ""
	if (thefield3.defaultValue==thefield3.value)
	thefield3.value = ""
	if (thefield4.defaultValue==thefield4.value)
	thefield4.value = ""
	if (thefield5.defaultValue==thefield5.value)
	thefield5.value = ""
	} 

	function clicked_local(){
		document.getElementById('missions').style.display='none';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('great_for_info').style.display='inline';
		document.getElementById('oppdateinfo').style.display='inline';
		document.getElementById('additionalinfo').style.display='inline';
		$('#vol_or_applicant').text('volunteers');
		$('#category').text("Service");
		$('.opp_or_position').text('Opportunity');
		$('.opp_or_position_lower').text('opportunity');
		$('.opp_or_position_full_upper_pl').text('<%=aszOrgOrChurchOpp%> Opportunities');
		$('.opp_or_position_full_lower_pl').text('volunteer opportunities');
	}
	function clicked_virtual(){
		document.getElementById('missions').style.display='none';
		document.getElementById('virtualaddr').style.display='none';
		document.getElementById('great_for_info').style.display='inline';
		document.getElementById('oppdateinfo').style.display='inline';
		document.getElementById('additionalinfo').style.display='inline';
		$('#vol_or_applicant').text('volunteers');
		$('#category').text("Service");
		$('.opp_or_position').text('Opportunity');
		$('.opp_or_position_lower').text('opportunity');
		$('.opp_or_position_full_upper_pl').text('<%=aszOrgOrChurchOpp%> Opportunities');
		$('.opp_or_position_full_lower_pl').text('volunteer opportunities');
	}
	function clicked_missions(){
		document.getElementById('missions').style.display='inline';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('great_for_info').style.display='inline';
		document.getElementById('oppdateinfo').style.display='inline';
		document.getElementById('additionalinfo').style.display='inline';
		$('#category').text("Service");
		$('#vol_or_applicant').text('volunteers');
		$('.opp_or_position').text('Opportunity');
		$('.opp_or_position_lower').text('opportunity');
		$('.opp_or_position_full_upper_pl').text('<%=aszOrgOrChurchOpp%> Opportunities');
		$('.opp_or_position_full_lower_pl').text('volunteer opportunities');
	}
	function clicked_job(){
		document.getElementById('missions').style.display='none';
		document.getElementById('virtualaddr').style.display='inline';
		document.getElementById('great_for_info').style.display='none';
		document.getElementById('oppdateinfo').style.display='none';
		document.getElementById('additionalinfo').style.display='none';
		$('#vol_or_applicant').text('Applicants');
		$('#category').text("Job");
		$('.opp_or_position').text('Job Posting (paid)');
		$('.opp_or_position_lower').text('Job Posting (paid)');
		$('.opp_or_position_full_upper_pl').text('Job Posting (paid)s');
		$('.opp_or_position_full_lower_pl').text('Job Posting (paid)s');
	}
	function stipend(){
		document.getElementById('is_paid').style.display='inline';
	}
	function no_stipend(){
		document.getElementById('is_paid').style.display='none';
	}

	function clicked_hqoroffsite(){
		var siteUse = $('input:radio[name=hqoroffsite]:checked').val();
		if (siteUse=="hq"){
			$('input:radio[name=opppositiontypetid]').filter('[value=<%=aszLocalTID%>]').attr('checked', true);
			document.getElementById('location_postype').style.display='none';
			clicked_local();
		}else if (siteUse=="offsite"){
			$('input:radio[name=opppositiontypetid]').filter('[value=<%=aszLocalTID%>]').attr('checked', true);
			document.getElementById('location_postype').style.display='inline';
			clicked_local();
		}else if (siteUse=="offsite_intl"){
			$('input:radio[name=opppositiontypetid]').filter('[value=<%=aszShortTermTID%>]').attr('checked', true);
			document.getElementById('location_postype').style.display='inline';
			clicked_missions();
		}

		var addressSet = $('input:radio[name=hqoroffsite]:checked').val();
		if(addressSet=="offsite" || addressSet=="offsite_intl" || addressSet=="noaddress"){
			$('input:text[name=addr1]').val('');
			$('input:text[name=city]').val('');
			$('input:text[name=postalcode]').val('');			
			$("#state").val("");
			$("#country").val("");	
<% if(showPortalInfo==true){ %>
			outreach_serviceareas('oppservicearea1tid','');
			outreach_serviceareas('oppservicearea2tid','2');
			outreach_serviceareas('oppservicearea3tid','3');
<% } %>
		}else if (addressSet=="hq" || addressSet=="address"){
			$('input:text[name=addr1]').val('<%=aszAddr1%>');
			$('input:text[name=city]').val('<%=aszCity%>');
			$('input:text[name=postalcode]').val('<%=aszPostal%>');			
			$("#state").val("<%=aszState%>");
			$("#country").val("<%=aszCountry%>");	
<% if(showPortalInfo==true){ %>
			internal_serviceareas('oppservicearea1tid','');
			internal_serviceareas('oppservicearea2tid','2');
			internal_serviceareas('oppservicearea3tid','3');
<% } %>
		}
	}
</script>
<style>
.left-column{
	float: left;
	padding: 3px;
	width: 265px;
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
#location h2, #oppdateinfo h2, #additionalinfo h2 {
	border-bottom: 1px solid #728DD4; 
	padding-bottom:3px;
}
.better-select div.room-board {
	border:none;
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
</style>
<link href="http://www.christianvolunteering.org/template_include/css/CalendarControl.css" rel="stylesheet" type="text/css">
<script src="http://www.christianvolunteering.org/template_include/js/CalendarControl.js" language="javascript"></script>


<form action="<%=aszPortal%><%=actionURL%><%if(aszPortal.length()>0){%>?method=processCreateOpportstep1<%}%>" focus="opptitle" name="orgForm" method="post">
<input type="hidden" name="method" value="processCreateOpportstep1" />
<input type="hidden" name="orgnid" value="<%=org.getORGNID()%>" />
<input type="hidden" name="role" />
<input type="hidden" name="oppexpiredrenew" value="<%=opp.getOPPExpiredRenew()%>" />
<input type="hidden" name="requesttype" value="ByContact" />
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
<input type="hidden" name="oppreqcodetype" value="No" />
<input type="hidden" name="oppreqcodename" value="" />
<% } %>
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="urlalias"  />

<input type="hidden" name="opppositiontypeid" value="<%=aszLocalTID%>" />
<input type="hidden" name="opppositiontypetid" value="<%=aszLocalTID%>" />
<input type="hidden" name="oppreqcodetype" value="No" />
<input type="hidden" name="shortform" value="true" />
<input type="hidden" name="serviceareatidsarray" value="35949" />
<input type="hidden" name="country" value="us" />

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Add <span class="opp_or_position">Opportunity</span> for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Add <span class="opp_or_position">Opportunity</span></span>
	<% } %>
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
		<% if(bAdminRole==true){ %>
	  <span style="float: left;">Add <span class="opp_or_position">Opportunity</span> for <bean:write name='orgForm' property='orgname' /></span>
		<% }else{ %>
	  <span style="float: left;">Add <span class="opp_or_position">Opportunity</span></span>
		<% } %>
	<% } %>
	<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
		<% if(bAdminRole==true){ %>
			<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%out.print(aszOrgOrChurch.toLowerCase());%> management </a>
		<% }else{ %>
			<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
			<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%out.print(aszOrgOrChurch.toLowerCase());%> management </a>
		<% } %>
			 &gt; add <span class="opp_or_position_lower">opportunity</span>
		</div>
	<% if(aszNoSearchNav=="true"){ %>
	  </div>
	<% } %>


<% // for google analytics tracking: %>
<% 
String aszGoalPage=""; 
boolean b_included=false;
if(!(request.getParameter("method")==null)){
	if(request.getParameter("method").equalsIgnoreCase("processcreateorg")){
		b_included=true;
		aszGoalPage = "/organization/create/finish";
%>
		<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<%
		aszGoalPage = "/opportunity/create"; 
%>
		<%@include file="/template_include/footer_google_analytics_goal_pages_additional.inc"%>
<%
	}
}
if(b_included==false){
	aszGoalPage = "/opportunity/create"; 
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<%
}
%>
<% // : end of for google analytics tracking %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
	<!-- START PROGRESSBAR -->
<DIV id="progressbar">
<% if(showPortalInfo==true && aszPortal.length()>0 && bFaith==true){ %>      
<table cellpadding="2"><tr>
	<td><div class="accountboxoff">1</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid=<%=org.getORGNID()%>" class="accountoff">Add <%=aszOrgOrChurch%><br>Profile</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">2</div></td>
		<td><a href="<%=aszPortal%>/churchopportunities.jsp" class="accountoff">Choose from Existing Organizations<br>& Service Opportunties</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxon">3</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>" class="accounton">Create Own <%=aszOrgOrChurch%><br><span class="opp_or_position_full_upper_pl"><%=aszOrgOrChurchOpp%> Opportunities</span></a></td>
</tr></table>
<p id="steps_description">
<h3 style="padding-bottom:20px;">
<font style="color:#003366">STEP 3:</font> 
In this step, add any <span class="opp_or_position_full_lower_pl">volunteer opportunities</span> provided by your <%=aszOrgOrChurch%> directly. 
This could include internal activities like Sunday School, Setup, Small Group Leaders 
or it could also include outreach activities and international missions that are set up specifically by your <%=aszOrgOrChurch%> and are not listed in the previous step 
(food pantry, soup kitchen, etc.) <a href="<%=aszPortal%>/orgmanagement.jsp"><img style="float:right; padding:7px;" border="0" src="/imgs/next-step_button.png" alt="next step" /></a>
</h3>
</p>
<% }else{ %>
<table cellpadding="2"><tr>
	<td><div class="accountboxoff">1</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accountoff">Check Status</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxoff">2</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accountoff">Add <%=aszOrgOrChurch%> Profile</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxon">3</div></td>
	<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=org.getORGNID()%>" class="accounton">Add <%=aszOrgOrChurch%> <%=aszOrgOrChurchOpp%><br><span class="opp_or_position">Opportunity</span> Listing</a></td>
</tr></table>
<% } %>
	</DIV>

	<!-- END PROGRESSBAR -->

<hr style="color:#728DD4;" />

Please note that this <% out.print(aszOrgOrChurchOpp.toLowerCase());%> <span class="opp_or_position_lower">opportunity</span> will submitted for moderation and won't be listed publicly until it has been approved.
<br />
<strong>You may also Add Opportunities in Bulk using an <a href="http://www.urbanministry.org/import-opps">Excel file</a></strong>
<br /><br />
<div id="form">
    
<div id="err" style="display:none;"><pre><FONT color="red"><bean:write name="orgForm" property="errormsg" /></FONT></pre></div>
		
    <div class="left-column-wide" style="font-weight:bold;">
			One Line Summary of Need or Project <span class="criticaltext">*</span>
    </div> 
	<div class="right-column" style="width: 425px;">
		<input type="text" name="opptitle" size="40" styleClass="textinputwhite" value="<%=opp.getOPPTitle()%>"/>
		
		<span style="float:right"><span class="criticaltext">*</span> Required Items</span>
	</div>
	<br clear="all" />

<% if(portal.length()>0 && showPortalInfo==true){ }else{ %>
        	
	<br clear="all" />
	<br clear="all" />
<% } %>
	
	<div class="left-column" style="font-weight:bold;">
		Detailed Description, Requirements & Contact Information <span class="criticaltext">*</span>&nbsp <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div class="right-column">
<textarea name="oppdesc" rows="11" cols="78" styleClass="textinputwhite"/><%=opp.getOPPDescription()%></textarea>
	</div>
	<br clear="all" />
	
	
	<div id="location">
	<br> 
	<h2>Location Information</h2>
	
	<div id="virtualaddr" style="<%=aszVirtual%>">
		
	<br clear="all" />

        
        <div class="left-column">
			Address 1
        </div>
		<div class="right-column">
			<input type="text" name="addr1" size="33" styleClass="textinputwhite" value="<%=aszAddr1%>" />
		</div>
		
		<div class="left-column">
			City
		</div>
		<div class="right-column">
			<input type="text" name="city" size="33" styleClass="textinputwhite" value="<%=aszCity%>" />
		</div> 
		
		<div class="left-column"> 
			State
		</div>
		<div class="right-column">
			<SELECT id="state" name="state" class="smalldropdown"> 
				<OPTION value=""></OPTION> 
				<%
				if(null != aStateList){
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszState ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
					}
				}
				%>
			</SELECT>
        </div>
		
		<div class="left-column">   
			Postal Code
		</div>
		<div class="right-column">
			<input type="text" name="postalcode" size="5" styleClass="textinputwhite" value="<%=aszPostal%>" />
		</div>
		
		<br clear="all" />
		
	</div><!-- close id="virtualaddr" -->
  </div><!-- close  id="location"-->
	














	
<%
String aszSTMtitle="Short-term Missions / Volunteer Internship";
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszSTMtitle="Short-Term Service Trip / Volunteer Internship";
}
%>
  <div id="missions" style="<%=aszMissions%>">
  	<br>
    <h2><%=aszSTMtitle%> Information</h2>
	
	<div class="left-column-wide" style="font-weight:bold;">
		Length of Trip/Internship <span class="criticaltext">*</span>
    </div>
  	<div class="right-column">
		<select id="opptriplengthtid" name="opptriplengthtid" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
			<option value=""></option>
		    <%
				iTemp = opp.getOPPTripLengthTID();
				for(int index=0; index < aDurationList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid == iTemp ) out.print(" selected=selected ");
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</select>
	</div>
	<br clear="all" />
  

	<div id="better-select-edit-taxonomy-286" class=" span-columnsbetter-select">
	    <div class="left-column" style="//height:85px;">
			<label>Benefits Offered: </label>
	     </div>
	     <div class="right-column">
			<div class="form-checkboxes form-checkboxes-scroll room-board">
				<div id="edit-taxonomy-286-11546-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11546">
						<input type="checkbox" value="<%=aszRoomBoardTID%>" name="roomboardtid" id="roomboardtid" <%=aszRoomBoard%> />
						Room & Board
					</label>
				</div>
				<div id="edit-taxonomy-286-11547-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11547">
						<input type="checkbox" value="<%=aszStipendTID%>" name="stipendtid" id="stipendtid" <%=aszStipend%> onclick="stipend()"/>
						Stipend
					</label>
				</div>
				<div id="edit-taxonomy-286-11548-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11548">
						<input type="checkbox" value="<%=aszMedInsurTID%>" name="medinsurtid" id="medinsurtid" <%=aszMedInsur%> />
						Medical Insurance
					</label>
				</div>
				<div id="edit-taxonomy-286-11549-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11549">
						<input type="checkbox" value="<%=aszTransportTID%>" name="transporttid" id="transporttid" <%=aszTransport%> />
						Transportation
					</label>
				</div>
				<div id="edit-taxonomy-286-11550-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11550">
						<input type="checkbox" value="<%=aszAmeriCorpsTID%>" name="americorpstid" id="americorpstid" <%=aszAmeriCorps%> />
						AmeriCorps
					</label>
	        	</div>
				<div id="edit-taxonomy-<%=aszYesWorkStudyTID%>-wrapper" class="form-item" <%=aszNoDisplayChurch%> >
					<label class="option" for="edit-taxonomy-<%=aszYesWorkStudyTID%>">
						<input type="checkbox" value="<%=aszYesWorkStudyTID%>" name="oppworkstudytid" id="oppworkstudytid" <%=aszWS%> />
						Federal Work Study Pay for College Students
					</label>
				</div>
			</div>
		</div>
	</div><!-- end div class="span-columns" -->
  <br clear="all" />

	<div class="left-column">
		Cost (per person)<br>(please note this should be in US$)
    </div>
  	<div class="right-column"> 
		<input type="text" name="oppcost" value=""<%=opp.getOPPCostUsd()%> size="5" styleClass="textinputwhite"/>
	</div>
	<br clear="all" />
  
	<div class="left-column">
		Cost (per person)<br>(please note this should be in US$)
    </div>
  	<div class="right-column"> 
		<input type="text" name="oppcost" value=""<%=opp.getOPPCostUsd()%> size="5" styleClass="textinputwhite"/>
		&nbsp;
		<SELECT id="oppcostterm" name="oppcostterm" class="smalldropdown"> 
			<option value=""></option>
			<%
				for(int index=0; index < aHourCommitList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aHourCommitList.get(index);
					if(null == aAppCode) continue;
					out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
			<option value="total">Total</option>
		</SELECT>
	</div>
	<br clear="all" />
  
  <div  id="is_paid" style="<%=aszPaymnt%>">
	<div class="left-column">
		Amount Paid (to volunteer)
    </div>
  	<div class="right-column"> 
			<input type="text" name="amntpd" value="<%=opp.getOPPAmntPd()%>" size="20" styleClass="textinputwhite"/>
	</div>
	<br clear="all" />
  </div>

	<div class="left-column">
		The above costs/payments include: (please clarify)
    </div>
  	<div class="right-column"> 
<textarea name="costinclude" rows="6" cols="70" styleClass="textinputwhite"/><%=opp.getOPPCostInclude()%></textarea>
	</div>
	<br clear="all" />


	<div class="left-column">
		Application Deadline (MM/DD/YYYY)
    </div>
  	<div class="right-column"> 
	<input type="text" id="appdeadline" name="appdeadline" size="15" styleClass="textinputwhite" value="<%=aszAppDeadlineDate%>"/>	
	</div>
	<br clear="all" />


	<div class="left-column">
		Additional Details
    </div>
  	<div class="right-column"> 
<textarea name="additionaldetail" rows="6" cols="70" styleClass="textinputwhite"/><%=opp.getOPPAddDetails()%></textarea>
	</div>
	<br clear="all" />


	<div class="left-column">
		Please submit name, address and phone number of references of individuals or churches that have participated in your short term missions trip opportunities:
    </div>
  	<div class="right-column"> 
<textarea name="stmreferences" rows="6" cols="70" styleClass="textinputwhite"/><%=opp.getOPPSTMReferences()%></textarea>
	</div>
	<br clear="all" />

  </div><!-- end  id="missions" -->
 
  <br />
	<div class="left-column">
		<DIV class=clear style="HEIGHT: 10px"></DIV>
	</div>
	<div class="right-column">
<% if(aszPortal.length()>0){ %>
		<INPUT TYPE="image" SRC="/imgs/next-step_button.png" BORDER="0" ALT="next step">
<% }else{ %>
		<INPUT class=submit type=submit value=Continue>
<% } %>
	</div>
	<br clear="all" />
	&nbsp;<span class="criticaltext">*</span><span style="font-weight: bold;"> Required Item</span>
	
	
	
	</div><!-- end id form -->
	</div><!-- end id body -->

</div><!-- end maincontent-->

</form>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%//@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

