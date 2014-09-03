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

<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<% int iUserListCount=0; %>
<logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
	<% iUserListCount++; %>
</logic:iterate>
<%
String questionnaireOnlineTypeSelected = "";
String questionnaireOnPaperTypeSelected = "";
if(opp.getQuestionnaireType() != null) {
  if(opp.getQuestionnaireType().equals("online")) questionnaireOnlineTypeSelected = "CHECKED";
  if(opp.getQuestionnaireType().equals("on_paper")) questionnaireOnPaperTypeSelected = "CHECKED";
}
String resumeRequiredChecked = opp.getResumeRequired() > 0 ? "CHECKED" : "";
String coverLetterRequiredChecked = opp.getCoverLetterRequired() > 0 ? "CHECKED" : "";

int iCount=0;
String aszClass="";
int[] a_iContainer= new int[1];
int iArraySize = 0;












if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}

String aszMemberType = org.getORGMembertype();
boolean bFaith=false;
if(aszMemberType.equalsIgnoreCase("Church")){
	bFaith=true;
	aszOrgOrChurch="Church";
	aszOrgOrChurchOpp="Ministry";
}


int iTemp = 0;
// logic to determine whether this opportunity is a CVC Internship; if so, there are some special formatting changes
int iOrgAssocCVCTID = 22155;
boolean b_CVCInternshipSiteApproved = false;

// b_CVCInternshipSiteApproved if the site has been approved as a city vision host site
if(org.getCVInternSiteApproved().equals("City Vision")){
	b_CVCInternshipSiteApproved = true;
}

int iWarningTime = 30; //number of days until expiration - warning

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
iYesWorkStudy=33192;

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

int iSiteChrisVolTID = 25133;
int iSiteID=iSiteChrisVolTID;

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList aBenefitsList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aHourCommitList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

ArrayList aServiceList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );

aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyWeightCodeList( aBenefitsList, vidBenefits );
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
String aszCreed="display: none;";
String aszOppRequiredCodeType = opp.getOPPRequiredCodeType();
if(aszOppRequiredCodeType.equalsIgnoreCase("Yes")){
   aszCreed="display: table-row;";
   aszReqCodeChck="checked=checked";
}else if(aszOppRequiredCodeType.equalsIgnoreCase("No")){
   aszCreed="display: none;";
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
if(aszOppRequiredCodeDesc.equalsIgnoreCase("Organizational Statement of Faith")){
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
String aszDateReq="display: table-row;";
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

String aszRoomBoard="", aszStipend="", aszMedInsur="", aszTransport="", aszAmeriCorps="", aszUndergradTuition="", aszGradTuition="";
int iRoomBoard=opp.getOPPRoomBoardTID();
if (iRoomBoard > 0) {
	aszRoomBoard="checked";
}
int iStipend=opp.getOPPStipendTID();
if (iStipend > 0) {
	aszStipend="checked";
}
int iMedInsur=opp.getOPPMedInsurTID();
if (iMedInsur > 0) {
	aszMedInsur="checked";
}
int iTransport=opp.getOPPTransportTID();
if (iTransport > 0) {
	aszTransport="checked";
}
int iAmeriCorps=opp.getOPPAmeriCorpsTID();
if (iAmeriCorps > 0) {
	aszAmeriCorps="checked";
}

String aszPrivateOpp="", aszPublicOpp="", aszHQ="", aszOffSite="", aszOffSiteIntl="";
if(opp.getOPPPrivate()==1){
	aszPrivateOpp="checked=checked";
}else if(opp.getOPPPrivate()==0){

	aszPublicOpp="checked=checked";
}

if(opp.getOPPHQorOffSite().equalsIgnoreCase("hq")){
	aszHQ="checked";
}else if(opp.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")){
	aszOffSiteIntl="checked";
}else{
	aszOffSite="checked";
}


String aszPaymnt="display: none;";
iStipend = opp.getOPPStipendTID();
if(iStipend > 0){// "Paid Stipend" taxonomy term_number
	aszPaymnt="display: table-row;";
}


String aszLanguage = opp.getOPPLanguages();
String aszAddr1=opp.getOPPAddrLine1();
if(aszAddr1.equalsIgnoreCase("")){
	aszAddr1=org.getORGAddrLine1();
}
String aszCity=opp.getOPPAddrCity();
if(aszCity.equalsIgnoreCase("")){
	aszCity=org.getORGAddrCity();
}
String aszPostal=opp.getOPPAddrPostalcode();
if(aszPostal.equalsIgnoreCase("")){
	aszPostal=org.getORGAddrPostalcode();
}
String aszState= opp.getOPPAddrStateprov();
if(aszState.equalsIgnoreCase("")){
	aszState= org.getORGAddrStateprov();
}
String aszCountry= opp.getOPPAddrCountryName();
if(aszCountry.equalsIgnoreCase("")){
	aszCountry= org.getORGAddrCountryName();
}



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



String aszFaith="display: table-row;";
String aszOrgFaith = org.getORGMembertype();
if(aszHostID.equalsIgnoreCase("volengchurch")){
   aszFaith="display: table-row;";
}else if(aszOrgFaith.equalsIgnoreCase("Non-Profit (Non-Christian)") || aszOrgFaith.equalsIgnoreCase("")){
   aszFaith="display: none;";
}


String aszCurrentUserContact = "";
try{
	if(request.getParameter("requesttype").contains("ByContact")){
		aszCurrentUserContact="ByContact";
	}
}catch(Exception e){}
%>

<script language="javascript">

function deleteOpportunity(){
	if( false == confirm("Are you sure that you want to delete this opportunity?\n") ){
		return;
	} 
	document.orgForm.method.value = "deleteOpportunity";
<%if(aszPortal.length()>0){%>
	document.orgForm.action = '<%=aszPortal%>/org.do?method=deleteOpportunity';
<% } %>
	document.orgForm.requesttype.value = "edit";
	document.orgForm.submit();
}


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
	$('.opp_or_position_full_upper_pl').text('Job Postings (paid)');
	$('.opp_or_position_full_lower_pl').text('Job Postings (paid)');
	
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
	
<%
int iTripLengthTID = opp.getOPPTripLengthTID();
String aszFullTripLengthOptions = "", aszLimitedTripLengthOptions = "";
String aszFullServiceAreaOptions = "", aszLimitedServiceAreaOptions = "";
String aszFullBenefitsOfferedOptions = "", aszLimitedBenefitsOfferedOptions = "";
String aszFullQuestionnairesOptions = "", aszCVInternQuestionnairesOptions = "";

for(int index=0; index < aDurationList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
	if(null == aAppCode) continue;
	int iTid = aAppCode.getAPCIdSubType();
	aszFullTripLengthOptions+=" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " ;
	if(iTid == iTripLengthTID ) aszFullTripLengthOptions+=" selected=selected ";
	aszFullTripLengthOptions+=" >" + aAppCode.getAPCDisplay() + "</option> ";
}

iTemp = 0;
a_iContainer = opp.getServiceAreasArray();
for(int index=0; index < aServiceList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
	if(null == aAppCode) continue;
	int iSubType = aAppCode.getAPCIdSubType();
	if (iSubType == 4759 || iSubType == 4773 || iSubType == 4763){ // office, Adults,  or Youth
		aszLimitedServiceAreaOptions += "<div id=\"edit-taxonomy-"+ vidService +"-"+ iSubType+"-wrapper\" class=\"form-item\" >"+
			"<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-"+iSubType+"\" >" +
			"<input type=\"checkbox\" id=\"serviceareatidsarray\" name=\"serviceareatidsarray\" value=\""+ iSubType + "\" class=\"form-checkbox form-checkboxes-scroll\" ";
		for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
			if(a_iContainer[indexArray]==iSubType) aszLimitedServiceAreaOptions += " checked ";
		}
		aszLimitedServiceAreaOptions += ">"+aAppCode.getAPCDisplay()+"</label></div>";
	}
	
		aszFullServiceAreaOptions += "<div id=\"edit-taxonomy-"+ vidService +"-"+ iSubType+"-wrapper\" class=\"form-item\" >"+
			"<label class=\"option\" for=\"edit-taxonomy-"+vidService+"-"+iSubType+"\" >" +
			"<input type=\"checkbox\" id=\"serviceareatidsarray\" name=\"serviceareatidsarray\" value=\""+ iSubType + "\" class=\"form-checkbox form-checkboxes-scroll\" ";
		for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
			if(a_iContainer[indexArray]==iSubType) aszFullServiceAreaOptions += " checked ";
		}
		aszFullServiceAreaOptions += ">"+aAppCode.getAPCDisplay()+"</label></div>";
	iTemp++;
}
aszFullServiceAreaOptions = aszFullServiceAreaOptions.replaceAll("'", "&#39;");

a_iContainer= new int[150];
a_iContainer = opp.getBenefitsArray();
for(int index=0; index < aBenefitsList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aBenefitsList.get(index);
	if(null == aAppCode) continue;
	int iSubType = aAppCode.getAPCIdSubType();
	
	if (iSubType == 38524 || iSubType == 38537 || iSubType == 11547 || iSubType == 11547 || iSubType == 38844){ // undergrad, graduate, paid intern, summer intern, masters in mstsm
		aszLimitedBenefitsOfferedOptions  += "<div id=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"-wrapper\" class=\"form-item\" > "+
			"<label class=\"option\" for=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"\" > " +
			"<input type=\"checkbox\" id=\"benefitstidsarray-"+iSubType+"\" name=\"benefitstidsarray\"  onclick=\"selected_benefits(this)\" value=\""+aAppCode.getAPCIdSubType()+"\" class=\"form-checkbox form-checkboxes-noscroll\"";
		for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
			if(a_iContainer[indexArray]==iSubType) aszLimitedBenefitsOfferedOptions += " checked ";
		}
		aszLimitedBenefitsOfferedOptions += ">"; 
		if(aAppCode.getAPCIdSubType()==iYesWorkStudy){
			aszLimitedBenefitsOfferedOptions += "Federal Work Study Pay for College Students";
		}else{
			aszLimitedBenefitsOfferedOptions += aAppCode.getAPCDisplay().replaceAll("'","&#39;");
		}
		aszLimitedBenefitsOfferedOptions += "</label></div>";
	}else if(iSubType != iRoomBoardTID){
		aszLimitedBenefitsOfferedOptions  += "<div style=\"display:none;\" id=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"-wrapper\" class=\"form-item\" > "+
			"<label class=\"option\" for=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"\" > " +
			"<input type=\"checkbox\" id=\"benefitstidsarray-"+iSubType+"\" name=\"benefitstidsarray\" onclick=\"selected_benefits(this)\"  value=\""+aAppCode.getAPCIdSubType()+"\" class=\"form-checkbox form-checkboxes-noscroll\"";
		for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
			if(a_iContainer[indexArray]==iSubType) aszLimitedBenefitsOfferedOptions += " checked ";
		}
		aszLimitedBenefitsOfferedOptions += ">"; 
		if(aAppCode.getAPCIdSubType()==iYesWorkStudy){
			aszLimitedBenefitsOfferedOptions += "Federal Work Study Pay for College Students";
		}else{
			aszLimitedBenefitsOfferedOptions += aAppCode.getAPCDisplay().replaceAll("'","&#39;");
		}
		aszLimitedBenefitsOfferedOptions += "</label></div>";
	}
	
	aszFullBenefitsOfferedOptions += "<div id=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"-wrapper\" class=\"form-item\" > "+
		"<label class=\"option\" for=\"edit-taxonomy-"+vidBenefits+"-"+aAppCode.getAPCIdSubType()+"\" > " +
		"<input type=\"checkbox\" id=\"benefitstidsarray-"+iSubType+"\" name=\"benefitstidsarray\"  onclick=\"selected_benefits(this)\" value=\""+aAppCode.getAPCIdSubType()+"\" class=\"form-checkbox form-checkboxes-noscroll\"";
	for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
		if(a_iContainer[indexArray]==iSubType) aszFullBenefitsOfferedOptions += " checked ";
	}
	aszFullBenefitsOfferedOptions += ">"; 
	if(aAppCode.getAPCIdSubType()==iYesWorkStudy){
		aszFullBenefitsOfferedOptions += "Federal Work Study Pay for College Students";
	}else{
		aszFullBenefitsOfferedOptions += aAppCode.getAPCDisplay().replaceAll("'","&#39;");
	}
	aszFullBenefitsOfferedOptions += "</label></div>";
} 

aszLimitedTripLengthOptions+=" <option value=\"8092\" " ;
if(8092 == iTripLengthTID ) aszLimitedTripLengthOptions+=" selected=selected ";
aszLimitedTripLengthOptions+=" >Summer (June-Aug)</option> ";
aszLimitedTripLengthOptions+=" <option value=\"8093\" " ;
if(8093 == iTripLengthTID ) aszLimitedTripLengthOptions+=" selected=selected ";
aszLimitedTripLengthOptions+=" >One Year</option> ";

String aszCVCInternSelected = "";
if(opp.getOPPInternType().equalsIgnoreCase("City Vision Internship")) {
  aszCVCInternSelected = " CHECKED";
}

aszFullQuestionnairesOptions = "<input type=\"radio\" id=\"questionnaire_type\" name=\"questionnaire_type\" " +
	" value=\"online\" " + questionnaireOnlineTypeSelected + " />Online " +
	"<select id=\"questionnaire_id\" name=\"questionnaire_id\"> " +
 	" <option value=\"0\" ";
if(opp.getQuestionnaireId() == 0){
	aszFullQuestionnairesOptions += "SELECTED";
}
aszFullQuestionnairesOptions += "></option>  <option value=\"-1\" ";
if(opp.getQuestionnaireId() == -1){
	aszFullQuestionnairesOptions += "SELECTED";
}
aszFullQuestionnairesOptions += ">Create new form...</option>";
for(QuestionnaireDTO q : org.getQuestionnaires()) { 
	aszFullQuestionnairesOptions += "<option value=\""+q.getID()+"\" ";
	if(opp.getQuestionnaireId() == q.getID()){
		aszFullQuestionnairesOptions += "SELECTED";
	}
	aszFullQuestionnairesOptions += ">"+q.getTitle()+"</option>";
}
aszFullQuestionnairesOptions += "</select><span id=\"edit_questionnaire_link\"></span><br />" +
	"<input type=\"radio\" id=\"questionnaire_type\" name=\"questionnaire_type\" " +
	"value=\"on_paper\" " + questionnaireOnPaperTypeSelected + " />On Paper";
if(opp.getQuestionnaireServerFile() != null) { 
	aszFullQuestionnairesOptions += "(<a href=\"" + aszPortal + opp.getQuestionnairePublicPath() + "\">application." + 
		opp.getQuestionnaireServerFileExtension() + "</a>)";
} 
aszFullQuestionnairesOptions += "<input type=\"file\" id=\"questionnaire_file\" name=\"questionnaire_file\" />";
aszCVInternQuestionnairesOptions = "<input type=\"radio\" id=\"questionnaire_type\" name=\"questionnaire_type\" " +
	"value=\"online\" CHECKED />Online<select id=\"questionnaire\" name=\"questionnaire\">" +
	"<option value=\"7\" SELECTED>City Vision Internship Application</option></select><span id=\"edit_questionnaire_link\"></span>";
%>

<% if(b_CVCInternshipSiteApproved==true){ %>
function toggle_cvc_internship(){	
	if($('#interntype').is(':checked')){
		$('#great_for_info').hide();
		$('#additionalinfo').hide();
		$('#oppdateinfo').hide();
		$('#cost-and-deadline').hide();
			
		
		$('#trip_length').html('<select id="opptriplengthtid" name="opptriplengthtid" onclick="check_triplength()" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" > <option value=""></option><%= aszLimitedTripLengthOptions%></select>');
		$('#better-select-edit-taxonomy-<%=vidService%>').html('<div id="better-select-edit-taxonomy-32" class="better-select betterfixed"><div class="form-item"><div class="form-checkboxes form-checkboxes-scroll"><%= aszLimitedServiceAreaOptions%></div></div></div>');
		$('#benefits_offered').html('<%= aszLimitedBenefitsOfferedOptions%>');
//			$('#requireddocuments').hide();

		$('#resume_required').attr('checked',true);
		$('#resume_required').attr( 'readOnly' , true);

		$('#questionnaire_section').attr( 'readOnly' , true);
		$('#questionnaire_section').html('<%= aszCVInternQuestionnairesOptions%>');
		$('.cover_letter').hide();
		$('.additional_documents').hide();
			
//			set_edit_questionnaire_link();

	}else{
		$('#great_for_info').show();
		$('#additionalinfo').show();
		$('#oppdateinfo').show();
		$('#cost-and-deadline').show();
		$('#trip_length').html('<select id="opptriplengthtid" name="opptriplengthtid" onclick="check_triplength()" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" > <option value=""></option><%= aszFullTripLengthOptions%></select>');
		$('#better-select-edit-taxonomy-<%=vidService%>').html('<div id="better-select-edit-taxonomy-32" class="better-select betterfixed"><div class="form-item"><div class="form-checkboxes form-checkboxes-scroll"><%= aszFullBenefitsOfferedOptions%></div></div></div>');
		$('#benefits_offered').html('<%= aszFullBenefitsOfferedOptions%>');
//			$('#requireddocuments').show();

		$('#resume_required').attr({ readOnly: false });

		$('#questionnaire_section').attr({ readOnly: false });
		$('#questionnaire_section').html('<%= aszFullQuestionnairesOptions%>');
			
		$('.cover_letter').show();
		$('.additional_documents').show();
		

<% if(resumeRequiredChecked.equalsIgnoreCase("")){ %>
		$('#resume_required').attr('checked',false);
<% } else { %>
		$('#resume_required').attr('checked',true);
<% } %>
	}

}	

function selected_benefits(item){
	var is_set = false;
	if( $('#benefitstidsarray-38524').is(':checked') || $('#benefitstidsarray-38524').is(':checked') || $('#benefitstidsarray-11547').is(':checked') || $('#benefitstidsarray-11547').is(':checked') ){
		is_set = true; 
	}
	if(is_set){
		$('#benefitstidsarray-38612').attr('checked','checked');
	}
}
<% }else{ %>
function toggle_cvc_internship(){	
	return;
}
function selected_benefits(el){
	return;	
}
<% } %>

	function check_triplength(){
//		console.log('opptriplengthtid   ' + $('#opptriplengthtid').val());
//		console.log('opppositiontypetid.postypemiss   ' + $("input[type='radio'][id='opppositiontypetid][class='postypemiss']").val());
//		console.log('#opppositiontypetid.postypemiss   ' + $("input[type='radio'][id='opppositiontypetid][class='postypemiss']:checked"));
		
		if(	$('#interntype').is(':checked')				&&
				$('#opptriplengthtid').val() == 8092 	&&
				$("input[type='radio'][id='opppositiontypetid][class='postypemiss']:checked")
		){
//			console.log('triggered');
			$('#benefitstidsarray-38612').attr('checked','checked');
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
#location h2, #oppdateinfo h2, #additionalinfo h2, #requireddocuments h2 {
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
	background-color: #003366;
    font-size: 14px;
}
.accountboxoff{
	background-color: #999999;
    font-size: 14px;
}
.accounton{
	color: #003366;
}
.accountoff{
	color: #999999;
}
</style>

<link href="http://www.christianvolunteering.org/template_include/css/CalendarControl.css" rel="stylesheet" type="text/css">
<script src="http://www.christianvolunteering.org/template_include/js/CalendarControl.js" language="javascript"></script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Edit <span class="opp_or_position">Opportunity</span> for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Edit <span class="opp_or_position">Opportunity</span></span>
	<% } %>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
	<% if(bAdminRole==true){ %>
  <span style="float: left;">Edit <span class="opp_or_position">Opportunity</span> for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Edit <span class="opp_or_position">Opportunity</span></span>
	<% } %>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%out.print(aszOrgOrChurch.toLowerCase());%> management </a>
	<% }else{ %>
		<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
		<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%out.print(aszOrgOrChurch.toLowerCase());%> management </a>
	<% } %>
	 &gt; edit <span class="opp_or_position_lower">opportunity</span>
	</div>
<% if(aszNoSearchNav=="true"){ %>
  </div>
<% } %>




























<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>



<form action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=processEditOpp<%}%>" focus="opptitle" name="orgForm" enctype="multipart/form-data" method="post">
<input type="hidden" name="method" value="processEditOpp" />
<input type="hidden" name="orgnid" value="<%=opp.getORGNID()%>" />
<input type="hidden" name="oppnid" value="<%=opp.getOPPNID()%>" />
<input type="hidden" name="role" />
<input type="hidden" name="oppexpiredrenew" value="" value="<%=opp.getOPPExpiredRenew()%>" />
<input type="hidden" name="requesttype" value="edit<%=aszCurrentUserContact%>" />
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="urlalias" value="<%=opp.getOPPUrlAlias()%>" />

	<div id="body">
      <h2>
      		Opportunity Posting for Organization <bean:write name="orgForm" property="orgname"/><BR>
      </h2>
      <HR width="98%">
<br>      
      <h3><A href="javascript:deleteOpportunity()"><IMG SRC="http://www.christianvolunteering.org/imgs/delete.gif" border=0 alt="delete this opportunity" width="20" height="20" ></A>&nbsp; Click the X to permanently delete this <span class="opp_or_position_lower">opportunity</span>.</h3>
	  <br><br>
<center><span class="criticaltext">
<table id="expiration" style="border:2px solid #7C99E6; padding: 10px;">
<tr><td colspan=2>
<%
	iTime = opp.getOPPExpirationDt();
	aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
if (opp.getOPPExpirationTime() / 86400 < 0){ %>
This <span class="opp_or_position_lower">opportunity</span> listing expired on 
	<%	out.print( aszTemp ); %>
.<br>Would you like to renew this <span class="opp_or_position_lower">opportunity</span> for a year from today?
&nbsp;&nbsp;<INPUT class=submit type=submit value=Renew>
<% }else if (opp.getOPPExpirationTime() / 86400 <= iWarningTime){ %>
This <span class="opp_or_position_lower">opportunity</span> listing is about to expire on 
	<%	out.print( aszTemp ); %>
.<br>Would you like to renew this <span class="opp_or_position_lower">opportunity</span> for a year from today?
&nbsp;&nbsp;<INPUT class=submit type=submit value=Renew>
<% } else if (opp.getOPPPublished() == 0){ %>
<br><br>This <span class="opp_or_position_lower">opportunity</span> listing has been submitted for moderation and won't be listed publicly until it has been approved.
<% }else{ %>
This <span class="opp_or_position_lower">opportunity</span> listing is set to expire on 
	<%	out.print( aszTemp ); %>
.<br>Would you like to renew this <span class="opp_or_position_lower">opportunity</span> for a year from today?
&nbsp;&nbsp;<INPUT class=submit type=submit value=Renew>
<% } %>
</td></tr></table>
</span></center>


<br>
<div id="form">
<FONT color="red"><bean:write name="orgForm" property="errormsg" /></FONT><br>
		
    <div class="left-column-wide" style="font-weight:bold;">
		Position Title <span class="criticaltext">*</span>
    </div> 
	<div class="right-column" style="width: 425px;">
		<input type="text" name="opptitle" size="40" styleClass="textinputwhite" value="<%=opp.getOPPTitle()%>"/>
		<span style="float:right"><span class="criticaltext">*</span> Required Items</span>
	</div>
	<br clear="all" />
<%
boolean b_PortalDisplay=false;
boolean b_PortalFaithDisplay=false;
boolean b_defaultDisplay=true;
if(b_CVCInternshipSiteApproved==true){
	b_PortalDisplay=false;
	b_PortalFaithDisplay=false;
	b_defaultDisplay=false;
}else if(portal.length()>0 && showPortalInfo==true){
	b_PortalDisplay=true;
	b_PortalFaithDisplay=false;
	b_defaultDisplay=false;
}else if(portal.length()>0 && showPortalInfo==true && bFaith==true){
	b_PortalDisplay=true;
	b_PortalFaithDisplay=true;
	b_defaultDisplay=false;
}
%>
<% if(b_defaultDisplay){ %>	
	<div class="left-column" style="font-weight:bold; //height:65px;">
		Position Type <span class="criticaltext">*</span>  <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div class="right-column" style="display:inline;">
		<input type="radio" styleClass="radio" value="<%=aszLocalTID%>" name="opppositiontypetid" id="opppositiontypetid" class="postypelocal" onclick="clicked_local()" <%=aszLoc%> /> Local Volunteering (in person)<br />
		<input type="radio" styleClass="radio" value="<%=aszVirtualTID%>" name="opppositiontypetid" id="opppositiontypetid" class="postypevirt" onclick="clicked_virtual(addr1,city,state,postalcode,country)" <%=aszVirt%> /> Virtual Volunteering (from home)<br />
		<input type="radio" styleClass="radio" value="<%=aszShortTermTID%>" name="opppositiontypetid" id="opppositiontypetid" class="postypemiss" onclick="clicked_missions(addr1,city,state,postalcode,country)" <%=aszMiss%>/> Short-term Missions / Volunteer Internship<br />
		<input type="radio" styleClass="radio" value="<%=aszJobTID%>" name="opppositiontypetid" id="opppositiontypetid" class="postypejob" onclick="clicked_job()" <%=aszJob%> /> Job Posting (paid)
	</div>
	<br clear="all" />
	<br clear="all" />
<% } %>



<% if(b_CVCInternshipSiteApproved == true){ %>
    <div class="left-column-wide" style="font-weight:bold;">
			<input type="checkbox" styleClass="checkbox" value="City Vision Internship" name="interntype" id="interntype" <%= aszCVCInternSelected %> onclick="toggle_cvc_internship()" />
    </div> 
	<div class="right-column" style="width: 425px;">
		This is a City Vision Internship
	</div>
	<br clear="all" />
	<br clear="all" />

<% } %>

<% if(b_PortalDisplay){ %>	
	<div class="left-column" style="font-weight:bold;">
		Position Type <span class="criticaltext">*</span>  <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div class="right-column" style="display:inline;">
		<input type="radio" name="hqoroffsite" VALUE="hq" id="hqoroffsite_hq" onclick="clicked_hqoroffsite()" "<%=aszHQ%>"> Volunteering in Church Campus/Headquarters/Small Groups</input><br />
		<input type="radio" name="hqoroffsite" VALUE="offsite" id="hqoroffsite_offsite" onclick="clicked_hqoroffsite()" "<%=aszOffSite%>"> Local Missions - Off Site</INPUT><br />
		<input type="radio" name="hqoroffsite" VALUE="offsite_intl" id="hqoroffsite_offsite" onclick="clicked_hqoroffsite()" "<%=aszOffSiteIntl%>"> International Missions - Off Site</INPUT>
	</div>
	<br clear="all" />
	<div class="left-column">
	</div>
	<div id="location_postype" class="right-column" style="display:none;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszLocalTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_local()" <%=aszLoc%> /> Local Volunteering (in person)<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszVirtualTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_virtual(addr1,city,state,postalcode,country)" <%=aszVirt%> /> Virtual Volunteering (from home)<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszShortTermTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_missions(addr1,city,state,postalcode,country)" <%=aszMiss%> /> Short-term Missions / Volunteer Internship<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszJobTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_job()" <%=aszJob%> /> Job Posting (paid)
			<br />
	</div>
	<br clear="all" />
<% }else if(b_CVCInternshipSiteApproved){ %>
	<div class="left-column" style="font-weight:bold;">
		Position Type <span class="criticaltext">*</span>  <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div id="location_postype" class="right-column">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszLocalTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_local()" <%=aszLoc%> /> Local Volunteering (in person)<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszVirtualTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_virtual(addr1,city,state,postalcode,country)" <%=aszVirt%> /> Virtual Volunteering (from home)<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszShortTermTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_missions(addr1,city,state,postalcode,country)" <%=aszMiss%> /> Short-term Missions / Volunteer Internship<br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" styleClass="radio" value="<%=aszJobTID%>" name="opppositiontypetid" id="opppositiontypetid" onclick="clicked_job()" <%=aszJob%> /> Job Posting (paid)
			<br />
	</div>
	<br clear="all" />
<% } %>	

	<div class="left-column" style="font-weight:bold;">
		Description & Requirements <span class="criticaltext">*</span>&nbsp <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div class="right-column">
<textarea name="oppdesc" rows="11" cols="78" styleClass="textinputwhite"/><%=opp.getOPPDescription()%></textarea>
	</div>
	<br clear="all" />
	
	
    <div class="left-column" style="font-weight:bold;">
         Do you require that <span id="vol_or_applicant">volunteers</span> for this position be Christian? <span class="criticaltext">*</span>
	</div>
    <div class="right-column">
		<input type="radio" styleClass="radio" value="Yes" name="oppreqcodetype" <%=aszReqCodeChck%> onclick="javascript:document.getElementById('creed').style.display='inline';" />	Yes &nbsp;
		<input type="radio" styleClass="radio" value="No" name="oppreqcodetype" <%=aszReqCodeNoChck%> onclick="javascript:document.getElementById('creed').style.display='none';" />No
    </div> 
    <br clear="all" />  
      
    <div style="display:none;" id="creed">      
	    <div class="left-column">
			Creed / Christian Belief
	    </div>
	    <div class="right-column">
			<select name="oppreqcodename" class="smalldropdown" size="1">
				<option value="" onclick="javascript:document.getElementById('orgStatement').style.display='none';" selected></option>
				<%
					String aszTempSelect="";
					aszTemp = opp.getOPPRequiredCodeDesc();
					if(aszTemp.equalsIgnoreCase("Church Member")){
						aszTempSelect=" selected=selected ";
					}
				%>
				<% if (bFaith==true && portal.length()>0){ %>
				<option value="Church Member" onclick="javascript:document.getElementById('orgStatement').style.display='none';" <%=aszTempSelect%> >Must be Church Member</option>
				<% } %>
				<%
					for(int index=0; index < acreedList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)acreedList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
							if(aszOptRqCode.contains( "Statement of Faith" ) ) {
								out.println("onclick=\"showOrgStmt(event);\" selected=selected ");
							}else{
								out.println("onclick=\"javascript:document.getElementById('orgStatement').style.display='none';\" selected=selected ");
							}
						} else {
							if(aszOptRqCode.contains( "Statement of Faith" ) ) {
								out.println("onclick=\"showOrgStmt(event);\" ");
							}else{
								out.println("onclick=\"javascript:document.getElementById('orgStatement').style.display='none';\" ");
							}
						}
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
			<div id="orgStatement" style="<%=aszOrgStatement%>">
				<br><br>
				Organizational Statement of Faith
				<pre><div id="textareaformat"><%=aszStatement%></div></pre>
				<br>
			</div>
		</div>
    </div>
	<br clear="all" />
	
<% if(bFaith==true && portal.length()>0){ %>		
	<div class="span-columns">
		<input type="checkbox" name="privateopp"  value="1" "<%=aszPrivateOpp%>" onclick="clicked_private(this)"/> Do not list this <span class="opp_or_position_lower">opportunity</span> in the public directory.  (It will be available within your private <% out.print(aszOrgOrChurchDescript.toLowerCase()); %> directory)
	</div>
		
<% } %>		
	
	<div class="left-column-wide" id="service_area_label">	
		Select <span id="category">Service</span> Areas &nbsp <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
		
	</div>
	<div class="right-column">
		<div id="better-select-edit-taxonomy-<%=vidService%>" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes form-checkboxes-scroll">
<%
a_iContainer= new int[150];
iTemp = 0;
a_iContainer = opp.getServiceAreasArray();
for(int index=0; index < aServiceList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
%>
<div id="edit-taxonomy-<%=vidService%>-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-<%=vidService%>-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="serviceareatidsarray" name="serviceareatidsarray" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-scroll"
	<%
	for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
		if(a_iContainer[indexArray]==iSubType) out.print(" checked ");
	}
	%> 

		><%=aAppCode.getAPCDisplay()%>
	</label>
</div>
<%
	iTemp++;
}
%>
				</div>
			</div>
		</div>
	</div>
	<br clear="all" />
<div style="display:none;">
<%
a_iContainer= new int[150];
a_iContainer = opp.getServiceAreasArray();
for(int index=0; index < aServiceList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
%>
<%=aAppCode.getAPCDisplay()%>
<%
}
%>
</div>
       
  <div id="great_for_info">
	<div class="left-column">
		For what types of volunteers is this <%out.print(aszOrgOrChurchOpp.toLowerCase());%> <span class="opp_or_position_lower">opportunity</span> suitable?
	</div>
    <div class="right-column">
		<input type="checkbox" styleClass="check" value="<%=aszKidTID%>" name="greatfor1tid" id="greatfor1tid" <%=aszKids%> />Kids
	    <input type="checkbox"  styleClass="check" value="<%=aszSeniorTID%>" name="greatfor2tid" id="greatfor2tid" <%=aszSeniors%> />Seniors
	    <input type="checkbox"  styleClass="check" value="<%=aszTeenTID%>" name="greatfor3tid" id="greatfor3tid" <%=aszTeens%> />Teens &nbsp;
	    <input type="checkbox"  styleClass="check" value="<%=aszGroupTID%>" name="greatfor4tid" id="greatfor4tid" <%=aszGroups%> onclick="javascript:document.getElementById('group').style.display='inline';"/>Groups
	    <input type="checkbox"  styleClass="check" value="<%=aszFamilyTID%>" name="greatfor5tid" id="greatfor5tid" <%=aszFamilies%> />Families &nbsp;
	</div>   
    <br clear="all" />   
  </div>
           
  <div id="group" style="display:none;">
	<div class="left-column">
		Group Minimum Size
	</div>
	<div class="right-column">
		<input type="text" name="groupmin" size="7" styleClass="textinputwhite" value="<%=opp.getOPPGroupMin()%>"/>
	</div>
	<br clear="all" /> 
       
	<div class="left-column">
		Group Maximum Size
	</div>
	<div class="right-column">
		<input type="text" name="groupmax" size="7" styleClass="textinputwhite" value="<%=opp.getOPPGroupMax()%>"/>
	</div>
	<br clear="all" />
  </div>
  
<% if(showPortalInfo==true && portal.length()>0){ }else{ %>	
	<div class="left-column">
		Do you require a formal background check?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="radio" value="Yes" name="backgroundcheck" <%=aszBackChckChck%> /> Yes &nbsp;
		<input type="radio" styleClass="radio" value="No" name="backgroundcheck" <%=aszBackChckNoChck%> /> No
	</div>
	<br clear="all" />	
	
	<div class="left-column">
		Do you require references?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="radio" value="Yes" name="referencereq" <%=aszReferReqChck%> /> Yes &nbsp;
		<input type="radio" styleClass="radio" value="No" name="referencereq" <%=aszReferReqNoChck%> /> No
	</div>
	<br clear="all" />	
<% } %>

	
  <div id="location">
	<br> 
	<h2>Location Information</h2>
	
	<div id="virtualaddr" style="<%=aszVirtual%>">
		<br>
<% if(showPortalInfo==true && portal.length()>0){ %>	
		<div class="span-columns">
			Please Specify the Location of the POSITION:
		</div>
	<br clear="all" />
<% }else{ %>		
    <div class="left-column">
			Please Specify the Location of the POSITION:
	</div>
	<div class="right-column">
		<div id="addrquestion" style="display:inline;" class="span-columns">
			<input type="radio" name="hqoroffsite" VALUE="hq" id="hqoroffsite_hq" onclick="change_address()" "<%=aszHQ%>"> Use the address below </INPUT>    &nbsp;OR&nbsp; 
			<input type="radio" name="hqoroffsite" VALUE="offsite" id="hqoroffsite_offsite" onclick="change_address()" "<%=aszOffSite%>"> Enter a new address for this <span class="opp_or_position_lower">opportunity</span></INPUT>
			<br>
		</div>
    </div>
		<br clear="all" />
<% } %>
        
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
		
		<div class="left-column" style="clear:both;"> 
			Country
		</div>
		<div class="right-column">
			<select name="country" id="country" class="smalldropdown">
				<option value=""></option>
				<%
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszCountry ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
			  %>
			</select>
		</div>
		<br clear="all" />
		
	</div><!-- close id="virtualaddr" -->
  </div><!-- close  id="location"-->

  <div id="oppdateinfo">
	<br>
    <h2><span class="opp_or_position">Opportunity</span> Date Information</h2>
    
	<div class="left-column-wide">
		Position Duration
	</div>
	<div class="right-column">
		<input type="radio" styleClass="radio" value="<%=aszOngoingTID%>" name="oppfrequencytid" <%=aszOngoingChck%> /> Ongoing position&nbsp;&nbsp;
		<input type="radio" styleClass="radio" value="<%=aszOneTimeTID%>" name="oppfrequencytid" <%=aszOneTimeChck%> /> One-time position&nbsp;&nbsp;
		<input type="radio" styleClass="radio" value="<%=aszSTSeasonalTID%>" name="oppfrequencytid" <%=aszSTSeasonalChck%> /> Short Term Service/Seasonal 
	</div>
    <br clear="all" />
	
	<div class="left-column">
		Hourly Commitment
	</div>
	<div class="right-column">
		<input type="text" name="hoursrequired" size="5" styleClass="textinputwhite" value="<%=opp.getOPPCommitHourly()%>"/>
		&nbsp; per 
		<SELECT id="hourstype" name="hourstype" class="smalldropdown">
		<%
			aszTemp = opp.getOPPCommitType();
			for(int index=0; index < aHourCommitList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aHourCommitList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
		%>
		</SELECT>
	</div>
	<br clear="all" />
	
	<div class="left-column">
		<div style="//height:45px;"><span class="opp_or_position">Opportunity</span> Date(s)</div>
	</div>
	<div class="right-column">
		<input type="radio" styleClass="check" value="<%=aszNoDateTID%>" name="daterequiredtid" <%=aszDateNoneChck%> onclick="javascript:document.getElementById('dates').style.display='none';" />No, this <span class="opp_or_position_lower">opportunity</span> is not scheduled for a particular date <br />
		<input type="radio" styleClass="check" value="<%=aszRecurringDateTID%>" name="daterequiredtid" <%=aszDateRecurringChck%> onclick="javascript:document.getElementById('dates').style.display='inline';" />This position is available during the following date(s) each year  <br />
		<input type="radio" styleClass="check" value="<%=aszYearDateTID%>" name="daterequiredtid" <%=aszDateYearChck%> onclick="javascript:document.getElementById('dates').style.display='inline';" />This position is available during the following date(s) only in this year 
	</div>
	<br clear="all" />
	<!-- input names startdate and enddate -->
  <div id="dates">
	<div class="left-column">			
		Start Date (MM/DD/YYYY)
	</div>
	<div class="right-column">
		<input type="text" name="startdate" id="startdate"  onfocus="showCalendarControl(this);" size="15" styleClass="textinputwhite" value="<%=aszStartDate%>"/>
	</div>
	<br clear="all" />
	
	<div class="left-column">
		End Date (MM/DD/YYYY)
	</div>
	<div class="right-column">
		<input type="text" name="enddate" id="enddate"  onfocus="showCalendarControl(this);" size="15" styleClass="textinputwhite" value="<%=aszEndDate%>"/>
	</div>
	<br clear="all" />
  </div><!-- end div id dates-->
  </div><!-- end  id="oppdateinfo" -->
  
  <div id="additionalinfo">
  	<br>
    <h2>Additional Information</h2>
	
	<div class="left-column">
		Number of Positions 
    </div>
  	<div class="right-column"> 
		<input type="text" name="volsneeded" size="5" styleClass="textinputwhite" value="<%=opp.getOPPVolsNeeded()%>"/>
	</div>
	<br clear="all" />
  
  	<div class="left-column">
		Number of Volunteers Who Have Served in This Position in the Past Year  
  	</div>
  	<div class="right-column">
		<input type="text" name="oppnumvol" size="5"  value="<%=opp.getOPPNumVolOpp()%>" styleClass="textinputwhite"/>
	</div>
    <br clear="all" />
<% if(showPortalInfo==true && portal.length()>0){ %>	
	<div class="left-column">
		Do you require a formal background check?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="radio" value="Yes" name="backgroundcheck" <%=aszBackChckChck%> /> Yes &nbsp;
		<input type="radio" styleClass="radio" value="No" name="backgroundcheck" <%=aszBackChckNoChck%> /> No
	</div>
	<br clear="all" />	
	
	<div class="left-column">
		Do you require references?
	</div>
	<div class="right-column">
		<input type="radio" styleClass="radio" value="Yes" name="referencereq" <%=aszReferReqChck%> /> Yes &nbsp;
		<input type="radio" styleClass="radio" value="No" name="referencereq" <%=aszReferReqNoChck%> /> No
	</div>
	<br clear="all" />	
<% } %>
  </div><!-- end   <div id="additionalinfo"-->


  
  <div id="requireddocuments">
  	<br>
    <h2>Required Documents</h2>
	  <div class="left-column">
		  Application
    </div>
    <div class="right-column" id="questionnaire_section"> 
      <input type="radio" id="questionnaire_type" name="questionnaire_type" value="online" <%=questionnaireOnlineTypeSelected%> />
      Online
      <select id="questionnaire_id" name="questionnaire_id">
        <option value="0" <%if(opp.getQuestionnaireId() == 0){%>SELECTED<%}%>></option>
        <option value="-1" <%if(opp.getQuestionnaireId() == -1){%>SELECTED<%}%>>Create new form...</option>
        <% for(QuestionnaireDTO q : org.getQuestionnaires()) { %>
          <option value="<%=q.getID()%>" <%if(opp.getQuestionnaireId() == q.getID()){%>SELECTED<%}%>><%=q.getTitle()%></option>
        <% } %>
      </select>
      <span id="edit_questionnaire_link"></span>
      <br />
      <input type="radio" id="questionnaire_type" name="questionnaire_type" value="on_paper" <%=questionnaireOnPaperTypeSelected%> />
      On Paper
      <% if(opp.getQuestionnaireServerFile() != null) { %>
        (<a href="<%=aszPortal%>/org.do?method=downloadQuestionnaireFile&oppnid=<%=opp.getOPPNID()%>">application.<%=opp.getQuestionnaireServerFileExtension()%></a>)
      <% } %>
      <input type="file" id="questionnaire_file" name="questionnaire_file" />
	</div>
    <br clear="all" />
     
    <div class="left-column">
		  Resume
    </div>
    <div class="right-column"> 
      <input type="checkbox" id="resume_required" name="resume_required" value="1" <%=resumeRequiredChecked%> />
	  </div>
    <br clear="all" />
    
    <div class="left-column cover_letter">
		  Cover Letter
    </div>
    <div class="right-column cover_letter"> 
      <input type="checkbox" id="cover_letter_required" name="cover_letter_required" value="1" <%=coverLetterRequiredChecked%> />
	  </div>
	  <br clear="all" />
    
    <div class="left-column additional_documents">
      Additional Documents
    </div>
    <div class="right-column additional_documents">
      <div id="existing_documents">
        <% if(opp.getRequiredDocumentsToShowOnForm() != null) { %>
          <% for(RequiredDocumentDTO doc : opp.getRequiredDocumentsToShowOnForm()) { %>
            <div>
              <a href="aszPortal/org.do?method=downloadRequiredDocumentFile&docnid=<%=doc.getNid()%>"><%=doc.getName()%>.<%=doc.getExtension()%></a> 
              <a onclick="remove_document.call(this, '<%=doc.getNid()%>');">X</a>
            </div>
          <% } %>
        <% } %>
      </div>
      <div id = "required_documents_to_remove">
        <% if(opp.getRequiredDocumentToRemoveNids() != null) { %>
          <% for(int nid : opp.getRequiredDocumentToRemoveNids()) { %>
            <input name="required_document_to_remove_nids" type="hidden" value="<%=nid%>" />
          <% } %>
        <% } %>
      </div>
      <div id="additional_documents_to_add">
        <% if(opp.getRequiredDocumentsToAdd() != null) { %>
          <% int j = 1; for(RequiredDocumentDTO doc : opp.getRequiredDocumentsToAdd()) { %>
            <div>
              <table>
                <tr>
                  <td>Name: </td>
                  <td><input type="text" name="required_document_to_add_name_<%=j%>" value="<%=doc.getName()%>" /></td>
                </tr>
                <tr>
                  <td>File: </td>
                  <td><input type="file" name="required_document_to_add_file_<%=j%>" /> <a class="remove_document_to_add">X</a></td>
                </tr>
              </table>
            </div>
          <% j++; } %>
        <% } %>
      </div>
      <% if(opp.getRequiredDocumentsToAdd() == null || opp.getRequiredDocumentsToAdd().size() < 3) { %>
        <a id="add_additional_document_link">Add Additional Document</a>
      <% } %>
    </div>
    <br clear="all" />
    
  </div>
  
  <div id="contactinfo">
  	<br>
    <a name="manage_contacts"><h2>Manage Contacts for this <span class="opp_or_position">Opportunity</span></h2></a>
	

<%

int i=0;
String aszOrgAdminTxt = aszOrgOrChurch + " Administrator";
String aszOrgAdminChecked = ""; String aszOppContactChecked = ""; String aszOppContactRcvEmailChecked = ""; String aszIsPrimaryContact = ""; String aszInitDisabledEdit="";
boolean b_isAdmin = false;
boolean b_isContact= false;
boolean b_isPrimaryContact= false;
boolean b_receivesEmail= false;
int[] a_iOrgAdminUIDs = org.getORGAdminUIDsArray();
int[] a_iOppContactUIDs = opp.getOPPContactUIDsArray();
int[] a_iOppContactUIDsRcvEmail = opp.getOPPContactUIDsRcvEmailArray();

%>
<script language="javascript">
function clicked_make_primary(box){
	var vis = (box.checked) ? "block" : "none";
	var value= (box.value);

	var temp=0;
	var temp2="";
	var temp3="";
	
	var tempPrimaryDiv="";
	var tempPrimarySecondaryDiv="";
	var tempPrimaryEmailElement="";
	
	var emailElementName = "oppcontactuidsrcvemailarray";
	var emailElement = "oppcontactuidsrcvemailarray["+value+"]";
	var editElementName = "oppcontactuidsarray";
	var editElement = "oppcontactuidsarray["+value+"]";
	var tempEmailElementName = "emailarraytemp";
	var tempEmailElement = "emailarraytemp["+value+"]";
	var tempEditElementName = "editarraytemp";
	var tempEditElement = "editarraytemp["+value+"]";

	var emailElementDiv = "emailarraychbx["+value+"]";
	var editElementDiv = "editarraychbx["+value+"]";
	var tempEmailElementDiv = "tempemailarraychbx["+value+"]";
	var tempEditElementDiv = "tempeditarraychbx["+value+"]";

	if(vis=="block"){
		// initially re-enable all email elements (will re-disable new primary contact later in code)
<% for(i=0; i<iUserListCount; i++){ %>
		temp = document.getElementsByName(emailElementName)[<%out.print(i);%>].value;
		temp2="emailarraychbx["+temp+"]";
		temp3="tempemailarraychbx["+temp+"]";
		document.getElementsByName(emailElementName)[<%out.print(i);%>].disabled=false;
		document.getElementById(temp2).style.display='inline';
		document.getElementById(temp3).style.display='none';
<% } %>
	temp=0;
	temp2="";
	temp3="";
	
	// initially re-enable for editing; however, if the value is in the array of admin ids, should then check off and disable again
<% 
int iUIDTemp=0, iTempListCount=0;
for(i=0; i<iUserListCount; i++){ %>
		temp = document.getElementsByName(editElementName)[<%out.print(i);%>].value;
		temp2="emailarraychbx["+temp+"]";
		temp3="tempemailarraychbx["+temp+"]";
		document.getElementsByName(editElementName)[<%out.print(i);%>].disabled=false;
		document.getElementById(temp2).style.display='inline';
		document.getElementById(temp3).style.display='none';
<% }  %>

<%
for(i=0; i<iUserListCount; i++){
	for(iTempListCount=0; iTempListCount<a_iOppContactUIDsRcvEmail.length; iTempListCount++){
		iUIDTemp=a_iOppContactUIDsRcvEmail[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){ <% // if the array element uid DOES receive emails %>
			document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
			
			document.getElementById(tempEditElementDiv).style.display='inline';
		}
<%	
	} // end itereate through receives emails users 
} // end iterate through all users

iUIDTemp=0;
for(i=0; i<iUserListCount; i++){ // iterate through all users
	for(iTempListCount=0; iTempListCount<a_iOrgAdminUIDs.length; iTempListCount++){ // find all the admin users; can't edit their "edit" option ever
		iUIDTemp=a_iOrgAdminUIDs[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){
			document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
			document.getElementById(tempEditElementDiv).style.display='inline';
		}
<%	
	} // end iterate through is org admin 
}  // end iterate through all users
%>
		document.getElementById(emailElement).checked=true;
		document.getElementById(tempEmailElement).checked=true;
		document.getElementById(tempEmailElementDiv).style.display='inline';
		document.getElementById(emailElementDiv).style.display='none';
		
		document.getElementById(editElement).checked=true;
		document.getElementById(tempEditElement).checked=true;
		document.getElementById(tempEditElementDiv).style.display='inline';
		document.getElementById(editElementDiv).style.display='none';
		document.getElementById(tempEditElement).disabled=true;
	}else{	
<% for(i=0; i<iUserListCount; i++){ // iterate through all users %>
		document.getElementsByName(emailElementName)[<%out.print(i);%>].disabled=false;
<% } %>
		document.getElementById(editElement).disabled=false;
	}
}

function clicked_receive_emails(box){
	var vis = (box.checked) ? "block" : "none";
	var value= (box.value);
	var temp=0; var temp2=""; var temp3="";
	var emailElement = "oppcontactuidsrcvemailarray["+value+"]";
	var editElement = "oppcontactuidsarray["+value+"]";
	var editElementName = "oppcontactuidsarray";
	var tempEmailElementName = "emailarraytemp";
	var tempEmailElement = "emailarraytemp["+value+"]";
	var tempEditElementName = "editarraytemp";
	var tempEditElement = "editarraytemp["+value+"]";

	var emailElementDiv = "emailarraychbx["+value+"]";
	var editElementDiv = "editarraychbx["+value+"]";
	var tempEmailElementDiv = "tempemailarraychbx["+value+"]";
	var tempEditElementDiv = "tempeditarraychbx["+value+"]";
	if(vis=="block"){
		document.getElementById(editElement).checked=true;
		document.getElementById(tempEditElement).checked=true;
		document.getElementById(tempEditElementDiv).style.display='inline';
		document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
	}else{
		document.getElementById(editElement).disabled=false;
		document.getElementById(editElementDiv).style.display='inline';
			document.getElementById(editElement).disabled=false;
		document.getElementById(tempEditElementDiv).style.display='none';
<%


// something in the code following then shows the incorrect edit divs again


for(i=0; i<iUserListCount; i++){
	for(iTempListCount=0; iTempListCount<a_iOrgAdminUIDs.length; iTempListCount++){
		iUIDTemp=a_iOrgAdminUIDs[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){
			temp=<%=iUIDTemp%>;
			temp2="editarraychbx["+temp+"]";
			temp3="tempeditarraychbx["+temp+"]";
			document.getElementsByName(tempEditElementName)[<%out.print(i);%>].disabled=true;
			document.getElementById(temp2).style.display='none';
			document.getElementById(temp3).style.display='inline';
		}
<% }} %>
	}
}

</script>

    <table id="contact" class="contact" border="1">
    <TR>
    	<th class="contactName">Name</th>
    	<th colspan=2 class="contactEmail">Email Address</th>
    	<th class="contactPrimary">Primary Contact</th>
    	<th class="contactRcvEmail">Receive<br>Volunteer Emails</th>
    	<th class="contactCanEdit">Can Edit <span class="opp_or_position">Opportunity</span></th>
    </tr>
    <logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
    	<%
    		aszClass="";
    		iCount++;
    		aszOrgAdminChecked = ""; aszOppContactChecked = ""; aszOppContactRcvEmailChecked = ""; aszIsPrimaryContact = "  ";aszInitDisabledEdit="";
    		b_isAdmin = false;
    		b_isContact= false;
    		b_isPrimaryContact= false;
    		b_receivesEmail= false;
    		
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}
    		
    		if(opp.getOPPPrimaryPersonUID() == userprofile.getUserUID()){
    			aszIsPrimaryContact = " checked=true ";
    			b_isPrimaryContact = true;
    		}
    		
    		// if the current uid is in the "Administrators" array of the parent organization, there is no control from this page over their edit access
    		for(i=0; i<a_iOrgAdminUIDs.length; i++){
    			if(a_iOrgAdminUIDs[i]==userprofile.getUserUID()){
    				b_isAdmin = true;
    				aszOrgAdminChecked = " checked ";
    			}
    		}
    		// if the current uid is a contact of the given opp
    		for(i=0; i<a_iOppContactUIDs.length; i++){
    			if(a_iOppContactUIDs[i]==userprofile.getUserUID()){
    				b_isContact = true;
    				aszOppContactChecked = " checked ";
     				aszOrgAdminChecked = " checked ";
    			}
    		}
    		// if the current uid is a contact of the given opp & receives email
    		for(i=0; i<a_iOppContactUIDsRcvEmail.length; i++){
    			if(a_iOppContactUIDsRcvEmail[i]==userprofile.getUserUID()){
    				b_receivesEmail = true;
    				aszOppContactRcvEmailChecked = " checked ";
    				aszInitDisabledEdit = " disabled ";
    			}
    		}
    	%>
      <TR class="<%=aszClass%>">
        <TD><!--uid:<% out.println(userprofile.getUserUID());%>&nbsp;uprofile nid:<% out.println(userprofile.getUserProfileNID());%>-->
          		<%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>
		</td><td width=200>
          		<%=userprofile.getUSPEmail1Addr()%>
        </td>
        <td>
        <a href="mailto:<%=userprofile.getUSPEmail1Addr()%>">
        <img class="email" height="17" width="22" border="0" title="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" alt="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" valign="middle" src="http://www.christianvolunteering.org/imgs/mail.gif"/></td>
        </a>
        <td align="center">
<input type="radio" name="oppprimarycontactuid" onclick="clicked_make_primary(this)" id="oppprimarycontactuid" value="<%=userprofile.getUserUID()%>" <%=aszIsPrimaryContact%> />
 		</td><td align="center">	
	<% if(b_isPrimaryContact == true){ %>
<div id="emailarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsrcvemailarray" onclick="clicked_receive_emails(this)" id="oppcontactuidsrcvemailarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
<div id="tempemailarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="emailarraytemp" disabled onclick="clicked_receive_emails(this)" id="emailarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
	<% }else{ %>
<div id="emailarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="oppcontactuidsrcvemailarray" onclick="clicked_receive_emails(this)" id="oppcontactuidsrcvemailarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
<div id="tempemailarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="emailarraytemp" disabled onclick="clicked_receive_emails(this)" id="emailarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
	<% } %>
        </td><td align="center">
    <% if(b_isAdmin == true){ %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
    <% } else { %>
        	<% if(b_isPrimaryContact == true || b_receivesEmail == true ){ // || b_isContact == true %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
 			<% }else{ %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
			<% } %>
    <% } %>
        </td>
        	

      </tr>
      </logic:iterate>
      </table>

  </div><!-- end   <div id="contactinfo"-->






	
<%
String aszSTMtitle="Short-term Missions / Volunteer Internship";
%>
  <div id="missions" style="<%=aszMissions%>">
  	<br>
    <h2><%=aszSTMtitle%> Information</h2>
	
	<div class="left-column-wide" style="font-weight:bold;">
		Length of Trip/Internship <span class="criticaltext">*</span>
    </div>
  	<div class="right-column" id="trip_length">
		<select id="opptriplengthtid" name="opptriplengthtid" onclick="check_triplength()" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
			<option value=""></option>
		    <%
			iTemp = opp.getOPPTripLengthTID();
		    if(b_CVCInternshipSiteApproved==false){
				for(int index=0; index < aDurationList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					if(iTid == iTemp ) out.print(" selected=selected ");
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
		    }else{
				out.print(" <option value=\"8092\" " );
				if(8092 == iTemp ) out.print(" selected=selected ");
				out.println(" >Summer (June-Aug)</option> ");
		    	
				out.print(" <option value=\"8093\" " );
				if(8093 == iTemp ) out.print(" selected=selected ");
				out.println(" >One Year</option> ");
		    } %>
		</select>
	</div>
	<br clear="all" />
  

	<div class="left-column-wide" style="font-weight:bold;">
			<label>Benefits Offered: </label>
    </div>
	<div class="right-column">
		<div id="better-select-edit-taxonomy-<%=vidBenefits%>" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes" id="benefits_offered">
<%
a_iContainer= new int[150];
a_iContainer = opp.getBenefitsArray();
for(int index=0; index < aBenefitsList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aBenefitsList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
 %>
<div id="edit-taxonomy-<%=vidBenefits%>-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-<%=vidBenefits%>-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="benefitstidsarray-<%=aAppCode.getAPCIdSubType()%>" name="benefitstidsarray" onclick="selected_benefits(this)" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-noscroll"
	<%
	for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
		if(a_iContainer[indexArray]==iSubType) out.print(" checked ");
	}
	
	%> 
		> 
	<%
		if(aAppCode.getAPCIdSubType()==iYesWorkStudy){
			out.print("Federal Work Study Pay for College Students");
		}else{
			out.print(aAppCode.getAPCDisplay());
		}
	%>
	</label>
</div>
<% } %>
				</div>
			</div>
		</div>
	</div>

  <br clear="all" />
<div id="cost-and-deadline"> 

	<div class="left-column">
		Amount Paid (if applicable)<br>(please note this should be in US$)
    </div>
  	<div class="right-column"> 
		<input type="text" name="amntpd" value="<%=opp.getOPPAmntPd()%>" size="5" styleClass="textinputwhite"/>
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
  </div>
	<br clear="all" />

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
</div>


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
 
  
	<div class="left-column">
		<DIV class=clear style="HEIGHT: 10px"></DIV>
	</div>
	<div class="right-column">
		<INPUT class=submit type=submit value=Continue>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT class=submit type=submit value=Renew> 
	</div>
	<br clear="all" />
	&nbsp;<span class="criticaltext">*</span><span style="font-weight: bold;"> Required Item</span>
	
	
	
	</div><!-- end id form -->
	</div><!-- end id body -->

</div><!-- end maincontent-->

</form>

<script>
  function countDocuments() {
    return $('#additional_documents_to_add > div').size() + $('#existing_documents > div').size(); 
  }

  remove_document = function(nid) {
    $(this).parent().remove();
    $('#required_documents_to_remove').append('<input type="hidden" name="required_document_to_remove_nids" value="' + nid + '" />');
    if(countDocuments() < 3) $('#add_additional_document_link').show();
  };

  remove_document_to_add = function() {
    $(this).parent().parent().parent().parent().parent().remove();
    if(countDocuments() < 3) $('#add_additional_document_link').show();
  };

  $(document).ready(function() {
    $('#add_additional_document_link').bind("click", function() {
      var i = $('#additional_documents_to_add > div').size() + 1;
      if(countDocuments() <= 3)
        $('#additional_documents_to_add').append('<div><table><tr><td>Name: </td><td><input type="text" name="required_document_to_add_name_'+i+'"/></td></tr><tr><td>File: </td><td><input type="file" name="required_document_to_add_file_'+i+'" /> <a class="remove_document_to_add" onclick="remove_document_to_add.call(this);">X</a></td></tr></table></div>');
      if(countDocuments() >= 3)
        $('#add_additional_document_link').hide();
    });
    $("a.remove_document_to_add").bind("click", remove_document_to_add);
    if(countDocuments() >= 3) $('#add_additional_document_link').hide();
    
    set_edit_questionnaire_link();
    $("select[name='questionnaire_id']").change(function() { set_edit_questionnaire_link(); });
    
    if($('#interntype').is(':checked')){
    	toggle_cvc_internship();
    }
   });
  
  function set_edit_questionnaire_link() {
    var questionnaireId = parseInt($("select[name='questionnaire_id']").val());
    var content = '';
    if(questionnaireId > 0)
      content = '<a href="<%=aszRailsPortal%>/questionnaires/'+questionnaireId+'" target="_blank">View selected questionnaire</a>';
    $("#edit_questionnaire_link").html(content);
    <% if(opp.getOPPPositionTypeTID()==iJob){ %>
	clicked_job();
<% } %>
<% if(showPortalInfo==true && portal.length()>1 ){ %>	
	clicked_hqoroffsite();
<% } %>
  }
</script>

<% if( b_includeLeftSidebar==false ){ %>
<%//@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
