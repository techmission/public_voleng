<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%//@ include file="/template/home_header.inc" %>
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- index_basic.jsp; aszPortal is <%=aszPortal%>-->

<%
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
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );
aCodes.getAppCodeList( aLocalAffilList, 175 );

ArrayList aPosFreqList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeList( aPosFreqList, vidPosFreq );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

String aszPartnerSecondDiv="display: none;";
// hide if not on specialized partner; if on specialized partner, show 2nd row by default, b/c first will be set to partner
if(	aszHostID.equalsIgnoreCase("volengsalvationarmy") ||
	aszHostID.equalsIgnoreCase("volengagrm")		
){ 
	aszPartnerSecondDiv="display: table-row;";
}
%>




<script language="javascript">

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}
function display_address_fields(){
	document.getElementById('postaloppLocal').style.display='table-row';
	document.getElementById('countrylabelopp').style.display='table-row';
}
function hide_address_fields(){
	document.getElementById('postaloppLocal').style.display='none';
	document.getElementById('countrylabelopp').style.display='none';
	document.getElementById('postalcodeLocal').value='';
	document.getElementById('countryLocal').value='';
}
function toggle(chkbox, item1, item2) {
	var disableSetting = (chkbox.checked) ? "true" : "false";
	if (document.getElementById(item1).disabled == false){
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
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
  
<br /><br />

<!-- Local Volunteering search-->
<div id="cvoppsrch" style="display:inline;">
	
<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>

<table style="font-size:12px; margin-left: 100px;">
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
			<strong>Skills</strong>
		</td>
		<td colspan="2">
        	<SELECT id="skills1idLocal" name="skills1id" class="smalldropdown" >
				<option value="" selected></option>
				<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
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
		 <td align="right"><strong>Location </strong></td> 
         <td align="left" colspan="2">
					<input type="radio" name="hqoroffsite" VALUE="hq" id="hqoroffsite_hq" onclick="hide_address_fields()"> Church Campus </INPUT>    &nbsp;&nbsp; 
					<input type="radio" name="hqoroffsite" VALUE="offsite" id="hqoroffsite_offsite" onclick="display_address_fields()"> Local Missions - Off-site</INPUT>
					<input type="radio" name="hqoroffsite" VALUE="offsite_intl" id="hqoroffsite_offsite" onclick="display_address_fields()"> Global Missions - Off-site</INPUT>
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
			<input type="checkbox" styleClass="check" value="4795" id="virt_volLocal" name="postype" onclick="toggle(this, 'countryLocal', 'postalcodeLocal')" />Search <span style="color: rgb(47, 85, 131); cursor: help;" title="Volunteers who share their skills remotely" onmouseover="this.style.cursor="help";">Virtual</span> Volunteer Opportunities
		</td>
	</tr>
	<tr id="postaloppLocal" style="display:none;">
		<td align="right">
			<strong>Postal Code</strong>
		</td>
		<td style="width:80px;">
			<input name="postalcode" type="text" id="postalcodeLocal" style="width:60px;" size="20" />
		</td>
	</tr>
	<tr id="countrylabelopp" style="display:none;">
		<td align="right">
			<strong>Country</strong>
		</td>
		<td colspan="2">
			<SELECT id="countryLocal" name="country" size="1" style="z-index: 0;" class="smalldropdown" onchange="selected_display(this, 'postaloppLocal')">
				<OPTION value=""></OPTION>
				<%
				aszTemp= "";//"US";
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
		<td align="right"></td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
		<td align="left"></td>
	</tr>
</table>
</form>
<br/>
</div>
<!-- end Local Volunteering search-->

</div>
</div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
