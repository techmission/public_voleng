<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszMobileSubdomain.length()>2){ %>
	<jsp:include page="/fullsearch.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else   if(  (aszHostID.equalsIgnoreCase( "volengexample" ))
){ // these templates get screwed up with this new page; include the old start page for vols
%>
        <jsp:include page="/fullsearch.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } else { %>





<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Advanced Search for Christian volunteer opportunities, short term urban mission trips, and virtual volunteer opportunities</title>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<!--link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" /-->
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

<style>

.left-column{
	float: left;
	padding: 3px;
	//padding:0;
	width: 205px;
	text-align:right;
}
.left-column-wide{
	float: left;
	padding: 3px;
	//padding:0;
	width: 265px;
	text-align:right;
	line-height:2em;
}
.right-column{
	float: left;
	//float:none;
	padding: 3px;
	//padding:0;
}
#orginfo h2, #posTypeAdv h2 {
	border-bottom: 1px solid #728DD4; 
	padding-bottom:3px;
}
</style>

<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
<% } %>


<script language="javascript">
function localopp_search()
{ 
document.getElementById('cvoppsrch').style.display='inline';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "active";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "";
}

function intrn_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "active";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "";
}

function stm_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "active";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "";
}

function pro_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='inline';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "active";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "";
}

function org_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='inline';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "active";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "";
}

function trn_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='inline';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "active";
document.getElementById('searchadv').className = "";
}

function adv_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='inline';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('searchadv').className = "active";
}


function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

function toggle(chkbox, item1, item2) {
	var disableSetting = (chkbox.checked) ? true : false;
	if (document.getElementById(item1).disabled == false){
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
	}
}
function toggle_position_type(chkbox, item1, item2) {
	var disableSetting = (chkbox.checked) ? true : false;
	if (document.getElementById(item1).disabled == false){
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
	}
//	alert(disableSetting);
	if(disableSetting==true){
//		alert('is set to virtual');
		document.getElementById('localtabpostype').value = 4795;
	}else{
//		alert('is NOT set to virtual');
		document.getElementById('localtabpostype').value = 4794;
	}
}
/* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */

function displayDisabled(chkbox, item1, setting) {
    document.getElementById(item1).disabled = setting;
}
/* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */


function selected_display(object, item1){
	var inputvalue=object.value;
	if(inputvalue=="us")
		document.getElementById(item1).style.display='table-row';
	else
		document.getElementById(item1).style.display='none';
}
function selected_disabled(object, item1){
	var inputvalue=object.value;
	if(inputvalue=="us")
		document.getElementById(item1).disabled = false;
	else
		document.getElementById(item1).disabled = true;
}



<% if(aszHostID.equalsIgnoreCase("volengchurch") || aszHostID.equalsIgnoreCase("volengexample")){ %>
function update_serviceareas(newlist){
	var addressSet = $('#hqoroffsite_mychurch option:selected').val();
	if(addressSet=="offsite" || addressSet=="offsite_intl" || addressSet=="noaddress"){
		var servElem = document.getElementById('oppservicelistchrisvol'); 
	}else{// if (addressSet=="hq" || addressSet=="address"){
		var servElem = document.getElementById('oppservicelistchurchvol'); 
	}
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
	
	newElem.options.length = 0;
	for(var i=0; i<servElem.options.length; i++) {
		tmpValue = servElem.options[i].value;
		tmpText = servElem.options[i].text;

		newElem.options[newElem.options.length] = new Option(tmpText,tmpValue); 
	}
}
<% } %>


</script>
<!-- fullsearch.jsp -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(true==false){ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch" >
	  <span id="title" >search </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

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
  



<%//BEGIN@  include file="/template_include/home_search7tabs.inc" %>
	
    <div id="all_tabs">
	<A style="text-decoration: none" HREF="<%=aszPortal%>/search.jsp"><h2>Find a Place to Volunteer</h2></A>

	<div id="vol-opp" style="display:inline;">
<% if(aszHostID.equalsIgnoreCase("volengexample")       ||
(aszPortal.length()>0 && aszHostID.equalsIgnoreCase("volengchurch") && (!(aszPortal.equals("/voleng"))))
){ %>
	<a id="opplocal"  class="active" onClick="localopp_search()" name="method" value="processOppSearchAll" href="#searchOpportunities">My Church</a>
<% }else{ %>
	<a id="opplocal"  class="active" onClick="localopp_search()" name="method" value="processOppSearchAll" href="#searchOpportunities">Local Volunteering</a>
<% } %>
	</div>
    
    <div id="internship" style="display:inline;">
	<a id="intrn" onClick="intrn_search()" name="method" value="processOppSearchAll" href="#searchInternships">Internship</a>
	</div> 
      
<% if ( (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<div id="short-term" style="display:none;">
<% }else{ %>
        <div id="short-term" style="display:inline;">
<% } %>
	<a id="stm2" onClick="stm_search()" name="method" value="processOppSearchAll" href="#searchShortTermMissions">Short-term Missions</a>
	</div> 
    

    <div id="vol-pro" style="display:inline;">
	<a id="pro" onClick="pro_search()" name="method" value="processOppSearchAll" href="#searchProfessionals">For Professionals</a>
	</div>
	
	<div id="organizations" style="display:inline;">
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
	<a id="organiz" onClick="org_search()" name="method" value="processOrgSearchAll" href="#searchChurch">Find My Church</a>
<% }else{ %>
	<a id="organiz" onClick="org_search()" name="method" value="processOrgSearchAll" href="#searchOrganizations">Organizations</a>
<% } %>
	</div>

	<div id="content" style="display:inline;">
	<a id="trn" onClick="trn_search()" name="method" value="allsearch" href="#searchTraining">Training</a> <!-- used to be umcont_search()-->
	</div> 
	
	<div id="searchlong" style="display:inline;">
	<a id="searchadv" onClick="adv_search()" name="method" value="allsearch" href="#searchAdvanced">Advanced</a>
	</div> 
<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 0px; //margin-top:-7px;" />
</div> <!-- end: div id all_tabs-->




<!-- Local Volunteering search-->
<div id="cvoppsrch" style="display:inline;">


<% if(aszHostID.equalsIgnoreCase("volengexample")       ||
(aszPortal.length()>0 && aszHostID.equalsIgnoreCase("volengchurch") && (!(aszPortal.equals("/voleng"))))
){ %>
	
<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" value="advsrchLocalPortal" name="formname" id="formname"/>
<input type="hidden" value="4794" name="postype" id="localtabpostype" />

<br />
<table style="font-size:12px; margin-left: 100px;">
	<tr>
		 <td align="right"><strong>Location </strong></td> 
         <td align="left" colspan="2">
			<select id="hqoroffsite_mychurch" name="hqoroffsite" size="1" class="smalldropdown" style="width: 175px;" onChange="update_serviceareas('serviceareaMyChurch')"> 
				<option value="" selected="selected"></option> 
				<option value="hq">Church Campus</option> 
				<option value="offsite">Local Missions - Off-site</option> 
				<option value="offsite_intl">Global Missions</option> 
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="serviceareaMyChurch" name="servicearea1" size="1" class="smalldropdown" > 
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
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		 <td align="right"><strong>Frequency </strong></td> 
         <td align="left" colspan="2">
			<select id="frequency" name="frequency" size="1" class="smalldropdown" style="width: 175px;"> 
				<option value="" selected="selected"></option> 
				<%
					for(int index=0; index < aPosFreqList.size(); index++){
						iTemp = 0;
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPosFreqList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
		<td align="left"></td>
	</tr>
</table>
</form>
<% }else{ %>
	
<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" value="advsrchLocal" name="formname" id="formname"/>
<input type="hidden" value="4794" name="postype" id="localtabpostype" />


<table style="font-size:12px; margin-left: 100px;">
	<tr id="postaloppLocal">
		<td align="right">
			<strong>Postal Code</strong>
		</td>
		<td style="width:80px;">
			<input name="postalcode" type="text" id="postalcodeLocal" style="width:60px;" size="20" />
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="servicearea1Local" name="servicearea1" size="1" class="smalldropdown" > 
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol")){
							aAppCode.setAPCDisplay(aAppCode.getAPCDisplay().replaceAll("Ministry", "Services"));
						}
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
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
			<strong>Skills</strong>
		</td>
		<td colspan="2">
        	<SELECT id="skills1idLocal" name="skills1id" class="smalldropdown" >
				<option value="" selected></option>
				<%
				if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (iSubType == 4748 ||
							iSubType == 4749){
						}else if (iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				}else{
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
				%>
			</SELECT>
		</td>
	</tr>
	<tr id="countrylabelopp">
		<td align="right">
			<strong>Country</strong>
		</td>
		<td colspan="2">
			<SELECT id="countryLocal" name="country" size="1" style="z-index: 0;" class="smalldropdown" onChange="selected_display(this, 'postaloppLocal')">
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
							out.print(" selected=selected ");
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeLocal', false);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppLocal').style.display='table-row';\" ");
						} else {
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeLocal', true);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppLocal').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
				%>
			</SELECT>
         </td>
	</tr>
	<tr>
		 <td align="right"><strong>Great for </strong></td> 
         <td align="left" colspan="2">
			<input type="checkbox" value="<%=aszGroupTID%>" name="greatforgroup" id="greatforgroupLocal" />&nbsp;Group 
            <input type="checkbox" value="<%=aszFamilyTID%>" name="greatforfamily" id="greatforfamilyLocal"/>&nbsp;Family
            <input type="checkbox" value="<%=aszKidTID%>" name="greatforkid" id="greatforkidLocal"/>&nbsp;Kid
            <input type="checkbox" value="<%=aszTeenTID%>" name="greatforteen" id="greatforteenLocal"/>&nbsp;Teen 
            <input type="checkbox" value="<%=aszSeniorTID%>" name="greatforsenior" id="greatforseniorLocal"/>&nbsp;Senior 
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" value="<%=aszYesWorkStudyTID%>" name="workstudy" id="workstudyLocal" />Search for Work Study Volunteer Opportunities
			<br/>
			<input type="checkbox" styleClass="check" value="4795" id="virt_volLocal" name="virt_volLocal" onClick="toggle_position_type(this, 'countryLocal', 'postalcodeLocal')" />Search <span style="color: rgb(47, 85, 131); cursor: help;" title="Volunteers who share their skills remotely" onMouseOver="this.style.cursor="help";">Virtual</span> Volunteer Opportunities
		</td>
	</tr>
  
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="searchfeeds" name="source" id="source"/>Search opportunities from third-party partners' sites <img src="http://www.christianvolunteering.org/imgs/afg-blue_100.png" alt="AllForGood" width="100"/>
		</td>
	</tr>



<% if(bAssoc==true){ %>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>Search only this Association 
		</td>
	</tr>
<% } %>
<% 
/*if(bAssoc==true){ 
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="false" name="assoconly" id="assoconly"/>Search Entire Site 
		</td>
	</tr>
 } 
*/%>


	<tr>
		<td align="right"></td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
		<td align="left"></td>
	</tr>
</table>
</form>
<br/>
<b>
	<a href="http://www.urbanministry.org/redirect-home" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities near your city" style="float: right;"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) 	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) 			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ %>
		target="_new"
	<% } %>
	>Browse Opportunities in Your City</a>
</b>



<% } %>



</div>
<!-- end Local Volunteering search-->


<!--- Internship Search   -->
<div id="cvintrnsearch" style=" display:none;">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" value="advsrchIntern" name="formname" id="formname"/>

<table width="480" height="200" border="0" style="font-size:12px; margin-left: 100px;">

	<tr id="postaloppIntrn">
		<td align="right">
			<strong>Postal Code</strong>
		</td>
		<td width="100%">
			<input name="postalcode" type="text" id="postalcodeIntrn" style="width:60px;" size="20" />
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="servicearea1Intrn" name="servicearea1" size="1" class="smalldropdown" > 
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Disabilities Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 6843){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Senior / Elderly Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
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
			<SELECT id="regiontidIntrn" name="regiontid" size="1" style="z-index: 0;" class="smalldropdown">
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
		<td align="right"><strong>Length of Trip</strong>
		</td><td width="100%">
							<!--SELECT id="durationAdv" name="duration" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
			        			<option value=""></option>
			        			<%
								aszTemp="";
								for(int index=0; index < aDurationList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
									if(null == aAppCode) continue;
									String aszOptRqCode = aAppCode.getAPCDisplay();
									out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
									if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
									out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
								}
								%>
							</SELECT-->
			<SELECT id="durationIntrn" name="duration" size="1" style="z-index: 0;" class="smalldropdown">
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
		<td align="right">
			<strong>Country</strong>
		</td>
		<td colspan="2">
			<SELECT id="countryIntrn" name="country" size="1" style="z-index: 0;" class="smalldropdown" onChange="selected_display(this, 'postaloppIntrn')">
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
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeIntrn', false);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppIntrn').style.display='table-row';\" ");
						} else {
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodeIntrn', true);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppIntrn').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<h5 class="benefits">Benefits Desired:</h5>
		</td>
		<td colspan="2">
			<div class="search_options" style="float:left; margin-right: 20px;"> 
				<input type="checkbox" value="<%=aszYesWorkStudyTID%>" name="workstudy" id="workstudyIntrn" />College Work Study Pay
				<br>
				<input id="roomboardIntrn" name="roomboard" type="checkbox" value="<%=aszRoomBoardTID%>" />Room &amp; Board 
				<br>
				<input id="stipendIntrn" name="stipend" type="checkbox" value="<%=aszStipendTID%>" />Stipend
			</div><!-- end: div id search_options-->
			<div class="benefits" style="float:left;">		
				<input id="medinsurIntrn" name="medinsur" type="checkbox" value="<%=aszMedInsurTID%>" />Medical Insurance 
				<br>
				<input id="transportIntrn" name="transport" type="checkbox" value="<%=aszTransportTID%>" />Transportation
				<br>
				<input id="americorpsIntrn" name="americorps" type="checkbox" value="<%=aszAmeriCorpsTID%>" />AmeriCorps
			</div><!-- end: div id benefits-->
		</td>
	</tr>

<% if(bAssoc==true){ %>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>Search only this Association 
		</td>
	</tr>
<% } %>
	<tr>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
		<td colspan=2>
			<b>
				<a href="http://www.urbanministry.org/volunteer/search/results/taxonomy%3A4796" title="browse all internship opportunities" style="float:right;"
				<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
						aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
						aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
				){ %> target="_new"
				<% } %>
				>Browse All Internships</a>
			</b>
		</td>
	</tr>
</table>
</form>
</div><!-- end cvintrnsearch -->
<!---  end Internship Search-->


<!--- Short Term Missions Search-->
<div id="cvstmsearch" style="display:none">
<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" value="advsrchSTM" name="formname" id="formname"/>

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
			<SELECT id="countrySTM" name="country" size="1" style="z-index: 0;" class="smalldropdown" onChange="selected_display(this, 'postaloppSTM')">
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Disabilities Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 6843){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Senior / Elderly Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
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
			<SELECT id="regiontidSTM" name="regiontid" size="1" style="z-index: 0;" class="smalldropdown">
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
							<SELECT id="durationSTM" name="duration" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
			        			<option value=""></option>
			        			<%
								aszTemp="";
								for(int index=0; index < aDurationList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
									if(null == aAppCode) continue;
									String aszOptRqCode = aAppCode.getAPCDisplay();
									out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
									if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
									out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
								}
								%>
							</SELECT>
			<!--SELECT id="durationSTM" name="duration" size="1" style="z-index: 0;" class="smalldropdown">
				<option value=""></option>
				<option value="8091">Less than a week</option>
				<option value="8094">1 to 2 weeks</option>
				<option value="8095">3 to 4 weeks</option>
				<option value="8096">1 to 2 months</option>
				<option value="8092">Summer</option>
				<option value="8097">3 to 5 months</option>
				<option value="8093">1 year</option>
				<option value="8099">1 to 2 years</option>
			</SELECT-->
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

<% if(bAssoc==true){ %>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>Search only this Association 
		</td>
	</tr>
<% } %>
	<tr>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
	</tr>
</table>
</form>

<b>
	<a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="browse Christian short term urban missions, social service ministry internship volunteer opportunities" style="float: right;">Browse Short-term Missions page</a>
</b>
</div><!-- end cvstmsearch -->
<!--- end Short Term Missions Search-->

<!-- For Professionals search-->
<div id="cvprosearch" style="display:none;">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<input type="hidden" value="advsrchProf" name="formname" id="formname"/>

<table style="font-size:12px; margin-left:100px;" border="0">
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="servicearea1Pro" name="servicearea1" size="1" class="smalldropdown" > 
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Disabilities Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 6843){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Senior / Elderly Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
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
			<strong>Skills</strong>
		</td>
		<td colspan="2">
        	<SELECT id="skills1idPro" name="skills1id" class="smalldropdown" >
				<option value="" selected></option>
				<%
				if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (iSubType == 4748 ||
							iSubType == 4749){
						}else if (iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				}else{
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
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
			<strong>Country</strong>
		</td>
		<td colspan="2">
			<SELECT id="countryPro" name="country" size="1" style="z-index: 0;" class="smalldropdown" onChange="selected_disabled(this, 'postalcodePro')">
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
							out.print(" selected=selected ");
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodePro', false);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppPro').style.display='table-row';\" ");
						} else {
//out.print( "onClick=\"javascript:displayDisabled(this, 'postalcodePro', true);\" ");
//out.print( "onClick=\"javascript:document.getElementById('postaloppPro').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
				%>
			</SELECT>
		</td>
	</tr>
	<tr id="postaloppPro">
		<td align="right">
			<strong>Postal Code</strong>
		</td>
		<td style="width:80px;">
	      	<input name="postalcode" type="text" id="postalcodePro" style="width:60px;" size="20" />
		</td>
		<td align="left">
	      	<br />
	      	<input type="checkbox" styleClass="check" value="4761" id="servicearea2Pro" name="servicearea2"/><strong>Search Board Member Opportunities</strong>
	      	<br />
		</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td></td>
		<td align="left">
			<input type="checkbox" styleClass="check" value="4795" id="virt_volPro" name="postype" onClick="toggle(this, 'countryPro', 'postalcodePro')"/><!--"checked"--><strong>Search Virtual Volunteer Opportunities</strong>
		</td>
	</tr>

<% if(bAssoc==true){ %>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>Search only this Association 
		</td>
	</tr>
<% } %>
	<tr>
		<td>&nbsp;</td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
	</tr>
</table>
</form>
<b>
	<a href="http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4795" title="browse all virtual volunteering opportunities by skill set" style="float: right;"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ %>
		target="_new"
	<% } %>
	>Browse Virtual Volunteering by Skill</a>
</b>
<br/>
<b>
	<a href="http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4761 " title="browse all opportunities for board members" style="float: right;"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ %>
		target="_new"
	<% } %>
	>Browse All Board Positions</a>
</b>
</div><!-- end cvprosearch -->
<!-- end For Professionals search-->


<!--- Organization Search-->
<div id="cvorgsrch" style="display:none;">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>
<input type="hidden" name="portallist" value="true">
<input type="hidden" value="advsrchOrgChurch" name="formname" id="formname"/>
<% }else{ %>
<input type="hidden" value="advsrchOrg" name="formname" id="formname"/>
<% } %>

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
			<SELECT id="countryOrg" name="country" size="1" style="z-index: 0; width:110px; float:left;" class="smalldropdown" onChange="selected_disabled(this, 'postalcodeOrg')">
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

<% if(bAssoc==true){ %>
	<tr>
		<td></td>
		<td colspan=2>
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>Search only this Association 
		</td>
	</tr>
<% } %>
	<tr>
		<td colspan=3></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/></a>
		</td>
	</tr>
</table>
</form>
<b>
	<a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ %>
		target="_new"
	<% } %>
	>browse organizations</a>
</b>
</div><!-- end cvorgsrch -->
<!--- end Organization Search-->




<!-- Training Materials search-->
<div id="cvtrnsearch" style="display:none;">
<center>
	<p>Search thousands of articles, sermons and videos on volunteering, urban ministry, and social justice:</p>
</center>
<div id="training_materials" style="width:300px; margin:auto;">
<!--begin Google Custom Search -->
<form name="search-form" class="search-form" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ 
	%>
		target="_new"
	<% } %>
	>
	<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
	<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
	<input type="text" maxlength="128" class="textinputwhite" style="width:220px;" name="query" id="edit-query"  value="Enter Keyword" onClick="clearText(this)" />
	<input type="submit" name="op" id="edit-sa" value="Search" />
	<input type="hidden" name="form_token" id="edit-google-cse-results-searchbox-form-form-token" value="f1596786f0691ac334237131d60e5654"  />
	<input type="hidden" name="form_id" id="edit-google-cse-results-searchbox-form" value="google_cse_results_searchbox_form"  />
</form>
<div id="search_results">
<noscript>
	<a href="http://www.google.com/cse?q=&cx=017405804136166701815:nc9jskbtk8y&cof=FORID:0"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) 	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) 			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ 
	%>
		target="_new"
	<% } %>
	>View the results at Google</a>, or enable JavaScript to view them here.
</noscript>
</div>
<script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script> <!-- end Google Custom Search-->
<br/><a href="http://www.urbanministry.org/faceted_search"
<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) 	|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) 			|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
){ 
%>
	target="_new"
<% } %>
>advanced search</a>&nbsp;|&nbsp;<a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions"
<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
){ 
%>
	target="_new"
<% } %>
>browse articles</a>

<br/><br/>
Powered by <a href="http://www.urbanministry.org"
<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
){ %>
	target="_new"
<% } %>
>UrbanMinistry.org</a>
</div>

<br/>
<b>
	<a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;"
	<% if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )	|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			|| 
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )
	){ %>
		target="_new"
	<% } %>
	>browse opportunities</a>
</b>

</div><!-- end cvtrnsearch -->
<!-- end Training Materials search-->

<!--- Advanced Search-->
<div id="cvadvsearch" style="display:none">
<%@  include file="/template_include/advanced_search_form.inc" %>
<%//@ include file="/template_include/home_search7tabs_advanced.jsp" --> ali will work on this tab later, as far as getting unique element ids, etc%>
</div><!-- end cvadvsearch -->
<!--- end Advanced Search-->


<!-- urbanministry search -->
<div id="srchumcontent" style="display:none">
</div>


<div class="cleardiv"></div>

<%//END@  include file="/template_include/home_search7tabs.inc" %>

<script language="javascript">
/* add ability for a user to go directly to a link with a specific tab */
function myfunc () {
	if (window.location.hash.indexOf('searchOpportunities') != -1) { 
		var the_timeout = setTimeout("localopp_search();",0);
	}else if (window.location.hash.indexOf('searchInternships') != -1) { 
		var the_timeout = setTimeout("intrn_search();",0);
	}else if (window.location.hash.indexOf('searchShortTermMissions') != -1) { 
		var the_timeout = setTimeout("stm_search();",0);
	}else if (window.location.hash.indexOf('searchProfessional') != -1) { 
		var the_timeout = setTimeout("pro_search();",0);
	}else if (window.location.hash.indexOf('searchOrganizations') != -1) { 
		var the_timeout = setTimeout("org_search();",0);
	}else if (window.location.hash.indexOf('searchChurch') != -1) { 
		var the_timeout = setTimeout("org_search();",0);
	}else if (window.location.hash.indexOf('searchTraining') != -1) { 
		var the_timeout = setTimeout("trn_search();",0);
	}else if (window.location.hash.indexOf('searchAdvanced') != -1) { 
		var the_timeout = setTimeout("adv_search();",0);
	}
}
window.onload = myfunc();
</script>

<P><BR>&nbsp;</P>



<div id="diffserviceareas" style="display:none;">
		<SELECT id="oppservicelistchrisvol"> 
			<option value=""></option>
			<%
				for(int index=0; index < aServiceSiteChrisVolList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceSiteChrisVolList.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</SELECT>
		<SELECT id="oppservicelistchurchvol"> 
			<option value=""></option>
			<%
				for(int index=0; index < aServiceSiteChurchVolList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceSiteChurchVolList.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</SELECT>
</div>




</div>
</div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->


<% } %>
