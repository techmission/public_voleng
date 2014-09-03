<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

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

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">ChristianVolunteering.org on Your Site</span>
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

<span style="float: left;">ChristianVolunteering.org on Your Site</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; 
	<a href="<%=aszPortal%>/org.do?method=showPasteForms">
	christianvolunteering.org on your site</a></div>
</<% } %>
div>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="/imgs/pic/blowing.jpg" width="245" height="204" ></td>
    <td valign="top" class="intsplash"><h1>
	ChristianVolunteering.org on your website!</h1>
      <p LANG="en-US" STYLE="margin-bottom: 0in">Below is the code allowing you 
		to put the search forms for ChristianVolunteering.org on your website. 
		Now volunteers can find volunteer opportunities like yours from your own 
		website! Simply cut and paste this code onto your website.</p>
	</td>
  </tr>
</table>
</div>

<div id="body">
  

<div align="left">
       <a><p>Which search form would you like?</p></a>
       
	 <p><a href= "<%=aszPortal%>/org.do?method=showPasteForms#short_form_white">
		Short form with white background</a> &nbsp;</p>      
	 <p><a href= "<%=aszPortal%>/org.do?method=showPasteForms#short_form">
		Short form with transparent background</a> &nbsp;</p>
       <p><a href= "<%=aszPortal%>/org.do?method=showPasteForms#medium_form">
		Medium form with transparent background</a>&nbsp;</p>
       <p><a href= "<%=aszPortal%>/org.do?method=showPasteForms#medium_form_white">
		Medium form with white background</a>&nbsp;</p>

       <p><a href="#advanced_form">Advanced form</a>&nbsp;</p>

		<br>

		
	<hr width="100%"> <h3>Advanced Form Example</h3><br><hr width="100%">
		
<!-- start actual form code here (rendered as a form) -->		
		
		
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
	}
	function orgadv_search(){
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "";
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
document.getElementById('adv').className = "active";
}

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

function toggle(chkbox, item1, item2) {
    //var visSetting = (chkbox.checked) ? "hidden" : "visible";
	var disableSetting = (chkbox.checked) ? "true" : "false";
	if (document.getElementById(item1).disabled == false)
	{
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}
	else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
	}
}
/* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */

</script>

<html:form action="/oppsrch.do" method="get"  >
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<html:hidden property="requiredcodetype" value="No" />
<% } %>


<br><br>
	<TABLE class="outer" cellSpacing=0 cellPadding=2 border=0 width="89%"  >
	<tr>
<% if( aszNarrowTheme=="true" ) { %>
	<td></td>
<% }else{ %>
	<td width=15%></td>
<% } %>
	<td>
<span class= "criticaltext">*</span><span style="font-weight: bold"> Please complete the required field below.</span>
	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 width="88%"  >
		<!-- MSTableType="layout" -->
		<tr><td colspan=5>
		<br>
      <fieldset style="width: 400px; height: 55px" >
      <table>
          <tr><TD class=set>
			<strong>Select One</strong> <span class= "criticaltext">*</span>	</TD>
          <TD valign="middle" colspan="2">
         
			<input type="radio" name="method" checked value="processOppSearchAll" onclick="opp_search()"> Search Opportunities &nbsp;&nbsp;<br>
			<input type="radio" name="method" value="processOrgSearchAll" onclick="orgadv_search()"> Search Organizations<br>
	</TD></tr></table>
      </fieldset><br><br></td>
    <TD></TD>
					</tr>
</table>

<div id="notall">

	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 width="88%"  id="table1" >
		<tr><td > <!--width="110"-->
			<div id="postallabel">Postal Code &amp; Country</div><div id="countrylabel" style="display:none;">Country</div></TD>
          <TD colspan="2">
			<div id="postal" style="float:left; margin-right:5px;"><html:text property="postalcode" styleId="postalcode" size="5" maxlength="5" styleClass="textinputwhite"/></div>




        	<div id="countrysection"><SELECT id="country" name="country" class="smalldropdown" style="z-index: 0; width:110px; //width:150px; float:left;"> 
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
					out.print( "onClick=\"javascript:document.getElementById('postallabel').style.display='inline';javascript:document.getElementById('countrylabel').style.display='none';javascript:document.getElementById('postal').style.display='inline';javascript:document.getElementById('distlabel').style.display='inline';javascript:document.getElementById('dist').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
              </SELECT></div>



</TD>
			<td height="26">&nbsp;</td>
		</tr>
		<tr>
                <TD valign="top" ><!--width="100"-->
	<div id="servicearealabel">
				Select Service Areas <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
	<div id="programtypelabel" style="display:none;">
				Select Program Type <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
				</TD>
                <TD colspan="2">
                
                
	<div id="servicearea">
				<SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" onchange="javascript:document.getElementById('serviceareadisplay2').style.display='inline';"> 

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
								iSubType == 4787 ||
								iSubType == 4789 ||
								iSubType == 7341 ||
								iSubType == 7342 ){
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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
				<div id="serviceareadisplay2" style="display:none;"><p>
				<SELECT id="servicearea2" name="servicearea2" class="smalldropdown" onchange="javascript:document.getElementById('serviceareadisplay3').style.display='inline';" > 

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
								iSubType == 4787 ||
								iSubType == 4789 ||
								iSubType == 7341 ||
								iSubType == 7342 ){
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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

				</SELECT></p></div>
                <div id="serviceareadisplay3" style="display:none;"><p>
				<SELECT id="servicearea3" name="servicearea3" class="smalldropdown" > 

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
								iSubType == 4787 ||
								iSubType == 4789 ||
								iSubType == 7341 ||
								iSubType == 7342 ){
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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

				</SELECT></p></div>
	</div>
	<div id="programtype1" style="display:none;">
				<SELECT id="programtypetid" name="programtypetid" size="1" class="smalldropdown" > 

				<option value="" selected="selected"></option> 
				<%
				for(int index=0; index < aProgramList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
					if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if(aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							if (iSubType == 5239){
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
	</div>
                </TD>
			<td></td>
			<td height="28">&nbsp;</td>
			</tr>


		<tr>
        <TD>
		<div id="regionlabel">Region</div></TD>
        <TD colspan="2">
        	<div id="regionsection"><SELECT id="regiontid" name="regiontid" class="smalldropdown"> 
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
              </SELECT></div>
		</TD>
			<td height="26">&nbsp;</td>
		</tr>


		<tr>
          <TD><div id="distlabel">
			Distance</div></TD>
          <TD colspan="2"><div id="dist">
          		<SELECT id="distance" name="distance" class="smalldropdown" > 
        <option value="5">5 miles (8K)</option>

        <option value="20">20 miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
               </SELECT></div>
               
         </TD>
                	<td></td>
					<td height="25">&nbsp;</td>
                	</td></tr>

		<tr>
        <TD width="191">
        <div id="grouplabel">
		I am a</div></TD>
        <TD colspan="2">
        <div id="group">
            <html:checkbox styleClass="check" value="<%=aszGroupTID%>" property="greatfor4" onclick="javascript:document.getElementById('group').style.display='inline';"/>
            &nbsp;Group 
            <html:checkbox styleClass="check" value="<%=aszFamilyTID%>" property="greatfor5"/>
        	&nbsp;Family
            <html:checkbox styleClass="check" value="<%=aszKidTID%>" property="greatfor1"/>
        	&nbsp;Kid
            <html:checkbox styleClass="check" value="<%=aszTeenTID%>" property="greatfor3"/>
            &nbsp;Teen 
            <html:checkbox styleClass="check" value="<%=aszSeniorTID%>" property="greatfor2"/>
            &nbsp;Senior 
        </div>
        </TD>
			<td></td>
			<td height="29">&nbsp;</td>
	  </tr>


		<tr>
			<TD colspan=3 ><HR></TD>
			<td></td>
			<td height="25">&nbsp;</td>
			</tr>
		<tr>
          <TD colspan=5>
<div id="opptype" >

	<table >
		<tr>
          <TD class=set>
			Choose One <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
			</TD>
          <TD valign="middle" colspan="2">
			<dl>
                                <dt><html:radio styleClass="radio" value="<%=aszLocalTID%>" property="postype" onclick="clicked_local()" /> Local Volunteering (in
                                person)</dt>
                                <dt><html:radio styleClass="radio" value="<%=aszVirtualTID%>" property="postype" onclick="clicked_virtual()" />
                                Virtual Volunteering (location flexible)</dt>
<% //if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
                                <dt><html:radio styleClass="radio" value="<%=aszShortTermTID%>" property="postype" onclick="clicked_missions()" /> Short-term <% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>Missions <% } %>/ Volunteer Internship</dt>
<% //} %>                                
                                <div id="missions" style="display:none; ">
	<table><tr><td width=40></td><td>
	<div id="better-select-edit-taxonomy-286" class="better-select">
		<div class="form-item">
			<label>Benefits Desired: </label>
			<div class="form-checkboxes form-checkboxes-scroll">
				<div id="edit-taxonomy-286-11546-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11546">
						<input id="roomboard" name="roomboard" type="checkbox" value="<%=aszRoomBoardTID%>" />
						Room & Board &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11547-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11547">
						<input id="stipend" name="stipend" type="checkbox" value="<%=aszStipendTID%>" />
						Stipend &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11548-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11548">
						<input id="medinsur" name="medinsur" type="checkbox" value="<%=aszMedInsurTID%>" />
						Medical Insurance &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11549-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11549">
						<input id="transport" name="transport" type="checkbox" value="<%=aszTransportTID%>" />
						Transportation &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11550-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11550">
						<input id="americorps" name="americorps" type="checkbox" value="<%=aszAmeriCorpsTID%>" />
						AmeriCorps &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
			</div>
		</div>
	</div>
	</td></tr></table>
</div>
                                <dt><html:radio styleClass="radio" value="" property="postype" onclick="clicked_all()" /> All Volunteer Positions</dt>
                                <br/>
          						<dt><html:checkbox styleClass="radio" value="<%=aszYesWorkStudyTID%>" property="workstudy" />Search for Work Study Volunteer Opportunities</dt></dl>
  </td>
  </tr>
	</table>
</div>	
</td></tr>				
		<tr>
          <TD colspan=4>
      <div id="missions-duration" style="display:none">
      <table cellspacing=0 cellpadding=2 >

		<tr>
          <TD >
			Duration</TD>
          <TD colspan="2">
<select id="duration" name="duration" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
        	<option value=""></option>
        	<%
			aszTemp="";
			for(int index=0; index < aDurationList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			%>
		</select>
				</td>
      		      		
					<td height="29">&nbsp;</td>
                	</tr>
<tr>
			<td height="29">Minimum Group Size</td>
			<td><html:text property="mingroup" size="5" maxlength="5" styleClass="textinputwhite"/></td>
			<td>&nbsp;&nbsp;Maximum Group Size</td>
			<td><html:text property="maxgroup" size="5" maxlength="5" styleClass="textinputwhite"/></td>
			
  		     		
					
                	</tr>
        </table>
        </div>
        </td></tr>
					
		<tr>
          <TD colspan=4>
          <div id="virtualaddr">
          <table cellspacing=0 cellpadding=0>
          
          <!--postal code and distance -->                	
 <tr>
			<td height="15">&nbsp;</td>
</tr>
			
		<tr><td colspan=5>	
      <fieldset style="width: 400px; height: 75px" align="center">
			<table>
		<tr>
        <TD>
		City</TD>
        <TD colspan="2"><html:text property="city" styleId="city" size="15" maxlength="50" styleClass="textinputwhite"/></TD>
			<td></td>
			<td height="30">&nbsp;</td>
			</tr>
		<tr>
        <TD>
		State</TD>
        <TD >
        	<SELECT id="state" name="state" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
				<%
				for(int index=0; index < aStateList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
					if(null == aAppCode) continue;
					out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
					out.println(" > " + aAppCode.getCSPStateName() + " </option> ");
				}
				%>
              </SELECT></td><td><!--Other html:text property="prov" size="10" styleClass="textinputwhite"/-->
              
        </TD>
			<td></td>
			<td height="37">&nbsp;</td>
		</tr>


<!-- country and region -->

		</table></fieldset>
		</td></tr>
		
<tr>
			<td height="15">&nbsp;</td>
</tr>
                	</table></div></td>
			</tr>
			



<!-- service area and program types -->
		<tr>
			<TD colspan=3 ><HR></TD>
			<td height="25">&nbsp;</td>
			</tr>
		<tr>
	        <TD>
			Organization<br>Name</TD>
	        <TD colspan="2">
	            <html:text property="orgname" styleId="orgname" size="30" maxlength="200" styleClass="textinputwhite"/>
	        </TD>
			<td height="26">&nbsp;</td>
		</tr>

		<tr>
        <TD valign="top">
		<div id="skillsgrouplabel">Skills Needed</div></TD>
        <TD TD valign="top" colspan="2">
	<div id="skillsgroup"><p>
        <select id="skills1id" name="skills1id" class="smalldropdown" onchange="javascript:document.getElementById('skill2').style.display='inline';">
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

				</SELECT></p>
				<div id="skill2" style="display:none;"><p>
        <select id="skills2id" name="skills2id" class="smalldropdown" onchange="javascript:document.getElementById('skill3').style.display='inline';">
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

				</SELECT></p></div>
                <div id="skill3" style="display:none;"><p>
        <select id="skills3id" name="skills3id" class="smalldropdown" >
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

				</SELECT></p></div>
	</div>
</TD>
       </TR>

<!-- kid, group, seniors,...-->
<!-- group size -->					

</td></tr>

<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

		<tr>
          <TD class=set valign="top">
			Denominational<br>Affiliation</TD>
          <TD colspan="2">
          <select id="denomaffiltid" name="denomaffiltid" class="smalldropdown" >
	<option value="" selected></option>
			<%
			if(aszHostID.equalsIgnoreCase("volengcatholic")){
				aszTemp="Catholic";
				iTemp=992;			
			} else {
				aszTemp="";
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

		    </select>
		    </TD>
			<td height="26">&nbsp;</td>
			</tr>
		<tr>
          <TD>
			Affiliations</TD>
              <TD width="478" valign="top">
			<SELECT id="orgaffil1tid" name="orgaffil1tid" class="smalldropdown" style="margin-top: 5px;" 
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  			
			){ %>
				onchange="javascript:document.getElementById('affiliation2').style.display='inline';"
			<% } %>
			> 
			<option value=""></option>
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
			if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
				aszTemp="Salvation Army";
				iTemp=1069;			
			} else if(aszHostID.equalsIgnoreCase("volengagrm")){
				aszTemp="Association of Gospel Rescue Missions (AGRM)";			
				iTemp=717;			
			} else {
				aszTemp="";
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
	<tr><td></td>
	<td>
	</td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation2" 
			<% // hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  			
			){ %>
				style="display: none;"
			<% } %>
			> 

			<SELECT id="orgaffil2tid" name="orgaffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation3').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
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
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation3" style="display: none;">

			<SELECT id="orgaffil3tid" name="orgaffil3tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation4').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
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
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation4" style="display: none;">

			<SELECT id="orgaffil4tid" name="orgaffil4tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation5').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
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
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation5" style="display: none;">

			<SELECT id="orgaffil5tid" name="orgaffil5tid" class="smalldropdown" style="margin-top: 5px;"  > 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
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
	</div></td></tr>
	<tr><td colspan=4>
<div style="<%=aszLocalAffil%>" >
	<table border="0" cellpadding="0" cellspacing="0" >
					
	<tr><TD width=110  height="30">
	Local Affiliation:</td><td>
			<select id="localaffil" name="localaffil" class="smalldropdown">
			<option value=""></option>

					<%
					aszTemp = "";
					for(int index=0; index < aLocalAffilList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLocalAffilList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
					%>
				</select></td><td height=45></td></tr>
</table></div>				
	</td></tr>

<% } //end section of not showing affiliations for non-faith versions of the site %>
	
<% if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
<tr><td height=29></td></tr>
<% } %>
	
	
	<tr><td>Sort results by: </td><td>
	<div id="sortstm" style="display:inline;">
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity  (Annually)</option>
    </SELECT>
    </div>
	<div id="sortopps" style="display:none;">
	<SELECT id="searchkey2" name="searchkey2" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity  (Annually)</option>
    </SELECT>
    </div>
	<div id="sortorgs" style="display:none;">
	<SELECT id="searchkey3" name="searchkey3" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
    </SELECT>
    </div>
</td></tr>
<table>
		<tr>
			<td width="105">&nbsp;</td>
          <TD colspan="2">
          
	<input type="submit" name="Submit" value="Submit">
</TD>
			<td width="4"></td>
			<td height="45" width="28">&nbsp;</td>
		</tr>
      </table>
</td></tr></table>
</html:form>


		
		
		</div>
		
		
	<hr width="100%"> <a name= "advanced_form"></a><h3>Advanced Form Code</h3><br><hr width="100%">

     
<p><b><i>in header: </i></b></p>

<p><br />
  &lt;script language=&quot;javascript&quot;&gt;<br />
function opp_search(){<br />
document.getElementById('notall').style.display='inline';<br />
document.getElementById('servicearealabel').style.display='inline';<br />
document.getElementById('servicearea').style.display='inline';<br />
document.getElementById('programtypelabel').style.display='none';<br />
document.getElementById('programtype1').style.display='none';<br />
document.getElementById('opptype').style.display='inline';<br />
document.getElementById('skillsgrouplabel').style.display='inline';<br />
document.getElementById('skillsgroup').style.display='inline';<br />
document.getElementById('sortstm').style.display='inline';<br />
document.getElementById('sortorgs').style.display='none';<br />
}<br />
function orgadv_search(){<br />
document.getElementById('notall').style.display='inline';<br />
document.getElementById('servicearealabel').style.display='none';<br />
document.getElementById('servicearea').style.display='none';<br />
document.getElementById('programtypelabel').style.display='inline';<br />
document.getElementById('programtype1').style.display='inline';<br />
document.getElementById('regionlabel').style.display='inline';<br />
document.getElementById('regionsection').style.display='inline';<br />
document.getElementById('countrylabel').style.display='inline';<br />
document.getElementById('countrysection').style.display='inline';<br />
document.getElementById('opptype').style.display='none';<br />
document.getElementById('missions').style.display='none';<br />
document.getElementById('missions-duration').style.display='none';<br />
document.getElementById('virtualaddr').style.display='inline';<br />
document.getElementById('skillsgrouplabel').style.display='none';<br />
document.getElementById('skillsgroup').style.display='none';<br />
document.getElementById('grouplabel').style.display='none';<br />
document.getElementById('group').style.display='none';<br />
document.getElementById('sortstm').style.display='none';<br />
document.getElementById('sortopps').style.display='none';<br />
document.getElementById('sortorgs').style.display='inline';<br />
}</p>
<p> function clicked_local(){<br />
  document.getElementById('regionlabel').style.display='inline';<br />
  document.getElementById('regionsection').style.display='inline';<br />
  document.getElementById('countrylabel').style.display='inline';<br />
  document.getElementById('countrysection').style.display='inline';<br />
  document.getElementById('postallabel').style.display='inline';<br />
  document.getElementById('postal').style.display='inline';<br />
  document.getElementById('distlabel').style.display='inline';<br />
  document.getElementById('dist').style.display='inline';<br />
  document.getElementById('missions').style.display='none';<br />
  document.getElementById('missions-duration').style.display='none';<br />
  document.getElementById('virtualaddr').style.display='inline';<br />
  document.getElementById('skillsgrouplabel').style.display='inline';<br />
  document.getElementById('skillsgroup').style.display='inline';<br />
  document.getElementById('grouplabel').style.display='inline';<br />
  document.getElementById('group').style.display='inline';<br />
  document.getElementById('sortstm').style.display='inline';<br />
  document.getElementById('sortopps').style.display='none';<br />
  }<br />
  function clicked_virtual(){<br />
  document.getElementById('regionlabel').style.display='none';<br />
  document.getElementById('regionsection').style.display='none';<br />
  document.getElementById('countrylabel').style.display='none';<br />
  document.getElementById('countrysection').style.display='none';<br />
  document.getElementById('postallabel').style.display='none';<br />
  document.getElementById('postal').style.display='none';<br />
  document.getElementById('distlabel').style.display='none';<br />
  document.getElementById('dist').style.display='none';<br />
  document.getElementById('missions').style.display='none';<br />
  document.getElementById('missions-duration').style.display='none';<br />
  document.getElementById('virtualaddr').style.display='none';<br />
  document.getElementById('skillsgrouplabel').style.display='inline';<br />
  document.getElementById('skillsgroup').style.display='inline';<br />
  document.getElementById('grouplabel').style.display='none';<br />
  document.getElementById('group').style.display='none';<br />
  document.getElementById('sortstm').style.display='inline';<br />
  document.getElementById('sortopps').style.display='none';<br />
  }<br />
  function clicked_missions(){<br />
  document.getElementById('regionlabel').style.display='inline';<br />
  document.getElementById('regionsection').style.display='inline';<br />
  document.getElementById('countrylabel').style.display='inline';<br />
  document.getElementById('countrysection').style.display='inline';<br />
  document.getElementById('postallabel').style.display='inline';<br />
  document.getElementById('postal').style.display='inline';<br />
  document.getElementById('distlabel').style.display='inline';<br />
  document.getElementById('dist').style.display='inline';<br />
  document.getElementById('missions').style.display='inline';<br />
  document.getElementById('missions-duration').style.display='inline';<br />
  document.getElementById('virtualaddr').style.display='inline';<br />
  document.getElementById('skillsgrouplabel').style.display='inline';<br />
  document.getElementById('skillsgroup').style.display='inline';<br />
  document.getElementById('grouplabel').style.display='inline';<br />
  document.getElementById('group').style.display='inline';<br />
  document.getElementById('sortstm').style.display='inline';<br />
  document.getElementById('sortopps').style.display='none';<br />
  }<br />
  function clicked_all(){<br />
  document.getElementById('regionlabel').style.display='inline';<br />
  document.getElementById('regionsection').style.display='inline';<br />
  document.getElementById('countrylabel').style.display='inline';<br />
  document.getElementById('countrysection').style.display='inline';<br />
  document.getElementById('postallabel').style.display='inline';<br />
  document.getElementById('postal').style.display='inline';<br />
  document.getElementById('distlabel').style.display='inline';<br />
  document.getElementById('dist').style.display='inline';<br />
  document.getElementById('missions').style.display='inline';<br />
  document.getElementById('missions-duration').style.display='inline';<br />
  document.getElementById('virtualaddr').style.display='inline';<br />
  document.getElementById('skillsgrouplabel').style.display='inline';<br />
  document.getElementById('skillsgroup').style.display='inline';<br />
  document.getElementById('grouplabel').style.display='inline';<br />
  document.getElementById('group').style.display='inline';<br />
  document.getElementById('sortstm').style.display='inline';<br />
  document.getElementById('sortopps').style.display='none';<br />
  }<br />
  function clicked_both(){<br />
  document.getElementById('regionlabel').style.display='inline';<br />
  document.getElementById('regionsection').style.display='inline';<br />
  document.getElementById('countrylabel').style.display='inline';<br />
  document.getElementById('countrysection').style.display='inline';<br />
  document.getElementById('postallabel').style.display='inline';<br />
  document.getElementById('postal').style.display='inline';<br />
  document.getElementById('distlabel').style.display='inline';<br />
  document.getElementById('dist').style.display='inline';<br />
  document.getElementById('missions').style.display='none';<br />
  document.getElementById('missions-duration').style.display='none';<br />
  document.getElementById('virtualaddr').style.display='inline';<br />
  document.getElementById('skillsgrouplabel').style.display='inline';<br />
  document.getElementById('skillsgroup').style.display='inline';<br />
  document.getElementById('grouplabel').style.display='none';<br />
  document.getElementById('group').style.display='none';<br />
  document.getElementById('sortstm').style.display='inline';<br />
  document.getElementById('sortopps').style.display='none';<br />
  }</p>
<p>function localopp_search()<br />
  { <br />
  document.getElementById('cvoppsrch').style.display='inline';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;active&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>function intrn_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='inline';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;active&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>function stm_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='inline';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;active&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>function pro_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='inline';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;active&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>function org_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='inline';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;active&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>&nbsp;</p>
<p>function trn_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='inline';<br />
  document.getElementById('cvadvsearch').style.display='none';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;active&quot;;<br />
  document.getElementById('adv').className = &quot;&quot;;<br />
  }</p>
<p>function adv_search()<br />
  {<br />
  document.getElementById('cvoppsrch').style.display='none';<br />
  document.getElementById('cvintrnsearch').style.display='none';<br />
  document.getElementById('cvstmsearch').style.display='none';<br />
  document.getElementById('cvprosearch').style.display='none';<br />
  document.getElementById('cvorgsrch').style.display='none';<br />
  document.getElementById('cvtrnsearch').style.display='none';<br />
  document.getElementById('cvadvsearch').style.display='inline';</p>
<p>document.getElementById('opplocal').className = &quot;&quot;;<br />
  document.getElementById('intrn').className = &quot;&quot;;<br />
  document.getElementById('stm').className = &quot;&quot;;<br />
  document.getElementById('pro').className = &quot;&quot;;<br />
  document.getElementById('organiz').className = &quot;&quot;;<br />
  document.getElementById('trn').className = &quot;&quot;;<br />
  document.getElementById('adv').className = &quot;active&quot;;<br />
  }</p>
<p>function clearText(thefield){<br />
  if (thefield.defaultValue==thefield.value)<br />
  thefield.value = &quot;&quot;;<br />
  }</p>
<p>function toggle(chkbox, item1, item2) {<br />
  //var visSetting = (chkbox.checked) ? &quot;hidden&quot; : &quot;visible&quot;;<br />
  var disableSetting = (chkbox.checked) ? &quot;true&quot; : &quot;false&quot;;<br />
  if (document.getElementById(item1).disabled == false)<br />
  {<br />
  document.getElementById(item1).disabled = true;<br />
  document.getElementById(item2).disabled = true;<br />
  }<br />
  else {<br />
  document.getElementById(item1).disabled = false;<br />
  document.getElementById(item2).disabled = false;<br />
  }<br />
  }<br />
  /* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */</p>
<p>&lt;/script&gt;</p>
<p></p>
<p><b><i>in body:</i></b></p>
<p><br />
  &lt;form name=&quot;searchForm&quot; method=&quot;get&quot; action=&quot;<%=aszSubdomain%>/oppsrch.do&quot;&gt;</p>
<p>&nbsp;</p>
<p>&lt;br&gt;&lt;br&gt;<br />
  &lt;TABLE class=&quot;outer&quot; cellSpacing=0 cellPadding=2 border=0 width=&quot;89%&quot;  &gt;<br />
  &lt;tr&gt;</p>
<p> &lt;td width=15%&gt;&lt;/td&gt;</p>
<p> &lt;td&gt;<br />
  &lt;span class= &quot;criticaltext&quot;&gt;*&lt;/span&gt;&lt;span style=&quot;font-weight: bold&quot;&gt; Please complete the required field below.&lt;/span&gt;<br />
  &lt;TABLE class=&quot;searchtoolfull&quot; cellSpacing=0 cellPadding=2 border=0 width=&quot;88%&quot;  &gt;<br />
  &lt;!-- MSTableType=&quot;layout&quot; --&gt;<br />
  &lt;tr&gt;&lt;td colspan=5&gt;<br />
  &lt;br&gt;</p>
<p> &lt;fieldset style=&quot;width: 400px; height: 55px&quot; &gt;<br />
  &lt;table&gt;<br />
  &lt;tr&gt;&lt;TD class=set&gt;<br />
  &lt;strong&gt;Select One&lt;/strong&gt; &lt;span class= &quot;criticaltext&quot;&gt;*&lt;/span&gt;	&lt;/TD&gt;<br />
  &lt;TD valign=&quot;middle&quot; colspan=&quot;2&quot;&gt;<br />
  <br />
  &lt;input type=&quot;radio&quot; name=&quot;method&quot; checked value=&quot;processOppSearchAll&quot; onclick=&quot;opp_search()&quot;&gt; Search Opportunities &amp;nbsp;&amp;nbsp;&lt;br&gt;</p>
<p> &lt;input type=&quot;radio&quot; name=&quot;method&quot; value=&quot;processOrgSearchAll&quot; onclick=&quot;orgadv_search()&quot;&gt; Search Organizations&lt;br&gt;<br />
  &lt;/TD&gt;&lt;/tr&gt;&lt;/table&gt;<br />
  &lt;/fieldset&gt;&lt;br&gt;&lt;br&gt;&lt;/td&gt;<br />
  &lt;TD&gt;&lt;/TD&gt;<br />
  &lt;/tr&gt;<br />
  &lt;/table&gt;</p>
<p>&lt;div id=&quot;notall&quot;&gt;</p>
<p> &lt;TABLE class=&quot;searchtoolfull&quot; cellSpacing=0 cellPadding=2 border=0 width=&quot;88%&quot;  id=&quot;table1&quot; &gt;</p>
<p> &lt;tr&gt;&lt;td &gt; &lt;!--width=&quot;110&quot;--&gt;<br />
  &lt;div id=&quot;postallabel&quot;&gt;Postal Code &amp;amp; Country&lt;/div&gt;&lt;div id=&quot;countrylabel&quot; style=&quot;display:none;&quot;&gt;Country&lt;/div&gt;&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;div id=&quot;postal&quot; style=&quot;float:left; margin-right:5px;&quot;&gt;&lt;input type=&quot;text&quot; name=&quot;postalcode&quot; maxlength=&quot;5&quot; size=&quot;5&quot; value=&quot;&quot; id=&quot;postalcode&quot; class=&quot;textinputwhite&quot;&gt;&lt;/div&gt;<br />
</p>
<p>&nbsp;</p>
<p> &lt;div id=&quot;countrysection&quot;&gt;&lt;SELECT id=&quot;country&quot; name=&quot;country&quot; class=&quot;smalldropdown&quot; style=&quot;z-index: 0; width:110px; //width:150px; float:left;&quot;&gt; <br />
  &lt;OPTION value=&quot;&quot;&gt;&lt;/OPTION&gt; <br />
  &lt;option value=&quot;af&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Afghanistan&lt;/option&gt; <br />
  &lt;option value=&quot;ax&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Aland Islands&lt;/option&gt; <br />
  &lt;option value=&quot;al&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Albania&lt;/option&gt; <br />
  &lt;option value=&quot;dz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Algeria&lt;/option&gt; <br />
  &lt;option value=&quot;as&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;American Samoa&lt;/option&gt; <br />
  &lt;option value=&quot;ad&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Andorra&lt;/option&gt; <br />
  &lt;option value=&quot;ao&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Angola&lt;/option&gt; <br />
  &lt;option value=&quot;ai&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Anguilla&lt;/option&gt; <br />
  &lt;option value=&quot;aq&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Antarctica&lt;/option&gt; <br />
  &lt;option value=&quot;ag&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Antigua and Barbuda&lt;/option&gt; <br />
  &lt;option value=&quot;ar&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Argentina&lt;/option&gt; <br />
  &lt;option value=&quot;am&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Armenia&lt;/option&gt; <br />
  &lt;option value=&quot;aw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Aruba&lt;/option&gt; <br />
  &lt;option value=&quot;ac&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ascension Island&lt;/option&gt; <br />
  &lt;option value=&quot;au&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Australia&lt;/option&gt; <br />
  &lt;option value=&quot;at&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Austria&lt;/option&gt; <br />
  &lt;option value=&quot;az&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Azerbaijan&lt;/option&gt; <br />
  &lt;option value=&quot;bs&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bahamas&lt;/option&gt; <br />
  &lt;option value=&quot;bh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bahrain&lt;/option&gt; <br />
  &lt;option value=&quot;bd&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bangladesh&lt;/option&gt; <br />
  &lt;option value=&quot;bb&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Barbados&lt;/option&gt; <br />
  &lt;option value=&quot;by&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Belarus&lt;/option&gt; <br />
  &lt;option value=&quot;be&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Belgium&lt;/option&gt; <br />
  &lt;option value=&quot;bz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Belize&lt;/option&gt; <br />
  &lt;option value=&quot;bj&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Benin&lt;/option&gt; <br />
  &lt;option value=&quot;bm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bermuda&lt;/option&gt; <br />
  &lt;option value=&quot;bt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bhutan&lt;/option&gt; <br />
  &lt;option value=&quot;bo&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bolivia&lt;/option&gt; <br />
  &lt;option value=&quot;ba&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bosnia and Herzegovina&lt;/option&gt; <br />
  &lt;option value=&quot;bw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Botswana&lt;/option&gt; <br />
  &lt;option value=&quot;bv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bouvet Island&lt;/option&gt; <br />
  &lt;option value=&quot;br&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Brazil&lt;/option&gt; <br />
  &lt;option value=&quot;io&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;British Indian Ocean Territory&lt;/option&gt; <br />
  &lt;option value=&quot;bn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Brunei Darussalam&lt;/option&gt; <br />
  &lt;option value=&quot;bg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Bulgaria&lt;/option&gt; <br />
  &lt;option value=&quot;bf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Burkina Faso&lt;/option&gt; <br />
  &lt;option value=&quot;bi&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Burundi&lt;/option&gt; <br />
  &lt;option value=&quot;kh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cambodia&lt;/option&gt; <br />
  &lt;option value=&quot;cm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cameroon&lt;/option&gt; <br />
  &lt;option value=&quot;ca&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Canada&lt;/option&gt; <br />

  &lt;option value=&quot;cv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cape Verde&lt;/option&gt; <br />
  &lt;option value=&quot;ky&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cayman Islands&lt;/option&gt; <br />
  &lt;option value=&quot;cf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Central African Republic&lt;/option&gt; <br />
  &lt;option value=&quot;td&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Chad&lt;/option&gt; <br />
  &lt;option value=&quot;cl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Chile&lt;/option&gt; <br />
  &lt;option value=&quot;cn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;China&lt;/option&gt; <br />
  &lt;option value=&quot;cx&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Christmas Island&lt;/option&gt; <br />
  &lt;option value=&quot;cc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cocos (Keeling) Islands&lt;/option&gt; <br />
  &lt;option value=&quot;co&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Colombia&lt;/option&gt; <br />
  &lt;option value=&quot;km&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Comoros&lt;/option&gt; <br />
  &lt;option value=&quot;cg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Congo&lt;/option&gt; <br />
  &lt;option value=&quot;cd&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Congo, the Democratic Republic of the&lt;/option&gt; <br />
  &lt;option value=&quot;ck&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cook Islands&lt;/option&gt; <br />
  &lt;option value=&quot;cr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Costa Rica&lt;/option&gt; <br />
  &lt;option value=&quot;ci&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cote D'Ivoire&lt;/option&gt; <br />
  &lt;option value=&quot;hr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Croatia&lt;/option&gt; <br />
  &lt;option value=&quot;cu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cuba&lt;/option&gt; <br />
  &lt;option value=&quot;cy&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Cyprus&lt;/option&gt; <br />
  &lt;option value=&quot;cz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Czech Republic&lt;/option&gt; <br />
  &lt;option value=&quot;dk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Denmark&lt;/option&gt; <br />
  &lt;option value=&quot;dj&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Djibouti&lt;/option&gt; <br />
  &lt;option value=&quot;dm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Dominica&lt;/option&gt; <br />
  &lt;option value=&quot;do&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Dominican Republic&lt;/option&gt; <br />
  &lt;option value=&quot;tp&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;East Timor&lt;/option&gt; <br />
  &lt;option value=&quot;ec&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ecuador&lt;/option&gt; <br />
  &lt;option value=&quot;eg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Egypt&lt;/option&gt; <br />
  &lt;option value=&quot;sv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;El Salvador&lt;/option&gt; <br />
  &lt;option value=&quot;gq&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Equatorial Guinea&lt;/option&gt; <br />
  &lt;option value=&quot;er&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Eritrea&lt;/option&gt; <br />
  &lt;option value=&quot;ee&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Estonia&lt;/option&gt; <br />
  &lt;option value=&quot;et&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ethiopia&lt;/option&gt; <br />
  &lt;option value=&quot;fk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Falkland Islands (Malvinas)&lt;/option&gt; <br />
  &lt;option value=&quot;fo&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Faroe Islands&lt;/option&gt; <br />
  &lt;option value=&quot;fj&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Fiji&lt;/option&gt; <br />
  &lt;option value=&quot;fi&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Finland&lt;/option&gt; <br />
  &lt;option value=&quot;fr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;France&lt;/option&gt; <br />
  &lt;option value=&quot;gf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;French Guiana&lt;/option&gt; <br />
  &lt;option value=&quot;pf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;French Polynesia&lt;/option&gt; <br />
  &lt;option value=&quot;tf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;French Southern Territories&lt;/option&gt; <br />
  &lt;option value=&quot;ga&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Gabon&lt;/option&gt; <br />
  &lt;option value=&quot;gm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Gambia&lt;/option&gt; <br />
  &lt;option value=&quot;ge&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Georgia&lt;/option&gt; <br />
  &lt;option value=&quot;de&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Germany&lt;/option&gt; <br />
  &lt;option value=&quot;gh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ghana&lt;/option&gt; <br />
  &lt;option value=&quot;gi&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Gibraltar&lt;/option&gt; <br />
  &lt;option value=&quot;gr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Greece&lt;/option&gt; <br />
  &lt;option value=&quot;gl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Greenland&lt;/option&gt; <br />
  &lt;option value=&quot;gd&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Grenada&lt;/option&gt; <br />
  &lt;option value=&quot;gp&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guadeloupe&lt;/option&gt; <br />
  &lt;option value=&quot;gu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guam&lt;/option&gt; <br />
  &lt;option value=&quot;gt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guatemala&lt;/option&gt; <br />
  &lt;option value=&quot;gn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guinea&lt;/option&gt; <br />
  &lt;option value=&quot;gw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guinea-Bissau&lt;/option&gt; <br />
  &lt;option value=&quot;gy&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Guyana&lt;/option&gt; <br />
  &lt;option value=&quot;ht&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Haiti&lt;/option&gt; <br />
  &lt;option value=&quot;hm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Heard Island and Mcdonald Islands&lt;/option&gt; <br />
  &lt;option value=&quot;va&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Holy See (Vatican City State)&lt;/option&gt; <br />
  &lt;option value=&quot;hn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Honduras&lt;/option&gt; <br />
  &lt;option value=&quot;hk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Hong Kong&lt;/option&gt; <br />
  &lt;option value=&quot;hu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Hungary&lt;/option&gt; <br />
  &lt;option value=&quot;is&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Iceland&lt;/option&gt; <br />
  &lt;option value=&quot;in&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;India&lt;/option&gt; <br />
  &lt;option value=&quot;id&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Indonesia&lt;/option&gt; <br />
  &lt;option value=&quot;ir&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Iran, Islamic Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;iq&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Iraq&lt;/option&gt; <br />
  &lt;option value=&quot;ie&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ireland&lt;/option&gt; <br />
  &lt;option value=&quot;im&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Isle of Man&lt;/option&gt; <br />
  &lt;option value=&quot;il&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Israel&lt;/option&gt; <br />
  &lt;option value=&quot;it&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Italy&lt;/option&gt; <br />
  &lt;option value=&quot;jm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Jamaica&lt;/option&gt; <br />
  &lt;option value=&quot;jp&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Japan&lt;/option&gt; <br />
  &lt;option value=&quot;je&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Jersey&lt;/option&gt; <br />
  &lt;option value=&quot;jo&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Jordan&lt;/option&gt; <br />
  &lt;option value=&quot;kz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Kazakhstan&lt;/option&gt; <br />
  &lt;option value=&quot;ke&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Kenya&lt;/option&gt; <br />
  &lt;option value=&quot;ki&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Kiribati&lt;/option&gt; <br />
  &lt;option value=&quot;kp&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Korea, Democratic People's Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;kr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Korea, Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;kw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Kuwait&lt;/option&gt; <br />
  &lt;option value=&quot;kg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Kyrgyzstan&lt;/option&gt; <br />
  &lt;option value=&quot;la&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Lao People's Democratic Republic&lt;/option&gt; <br />
  &lt;option value=&quot;lv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Latvia&lt;/option&gt; <br />
  &lt;option value=&quot;lb&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Lebanon&lt;/option&gt; <br />
  &lt;option value=&quot;ls&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Lesotho&lt;/option&gt; <br />
  &lt;option value=&quot;lr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Liberia&lt;/option&gt; <br />
  &lt;option value=&quot;ly&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Libyan Arab Jamahiriya&lt;/option&gt; <br />
  &lt;option value=&quot;li&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Liechtenstein&lt;/option&gt; <br />
  &lt;option value=&quot;lt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Lithuania&lt;/option&gt; <br />
  &lt;option value=&quot;lu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Luxembourg&lt;/option&gt; <br />
  &lt;option value=&quot;mo&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Macao&lt;/option&gt; <br />
  &lt;option value=&quot;mk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Macedonia, the Former Yugoslav Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;mg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Madagascar&lt;/option&gt; <br />
  &lt;option value=&quot;mw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Malawi&lt;/option&gt; <br />
  &lt;option value=&quot;my&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Malaysia&lt;/option&gt; <br />
  &lt;option value=&quot;mv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Maldives&lt;/option&gt; <br />
  &lt;option value=&quot;ml&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mali&lt;/option&gt; <br />
  &lt;option value=&quot;mt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Malta&lt;/option&gt; <br />
  &lt;option value=&quot;mh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Marshall Islands&lt;/option&gt; <br />
  &lt;option value=&quot;mq&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Martinique&lt;/option&gt; <br />
  &lt;option value=&quot;mr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mauritania&lt;/option&gt; <br />
  &lt;option value=&quot;mu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mauritius&lt;/option&gt; <br />
  &lt;option value=&quot;yt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mayotte&lt;/option&gt; <br />
  &lt;option value=&quot;mx&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mexico&lt;/option&gt; <br />
  &lt;option value=&quot;fm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Micronesia, Federated States of&lt;/option&gt; <br />
  &lt;option value=&quot;md&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Moldova, Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;mc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Monaco&lt;/option&gt; <br />
  &lt;option value=&quot;mn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mongolia&lt;/option&gt; <br />
  &lt;option value=&quot;me&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Montenegro&lt;/option&gt; <br />
  &lt;option value=&quot;ms&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Montserrat&lt;/option&gt; <br />
  &lt;option value=&quot;ma&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Morocco&lt;/option&gt; <br />
  &lt;option value=&quot;mz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Mozambique&lt;/option&gt; <br />
  &lt;option value=&quot;mm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Myanmar&lt;/option&gt; <br />

  &lt;option value=&quot;na&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Namibia&lt;/option&gt; <br />
  &lt;option value=&quot;nr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Nauru&lt;/option&gt; <br />
  &lt;option value=&quot;np&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Nepal&lt;/option&gt; <br />
  &lt;option value=&quot;nl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Netherlands&lt;/option&gt; <br />
  &lt;option value=&quot;an&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Netherlands Antilles&lt;/option&gt; <br />
  &lt;option value=&quot;nc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;New Caledonia&lt;/option&gt; <br />
  &lt;option value=&quot;nz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;New Zealand&lt;/option&gt; <br />
  &lt;option value=&quot;ni&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Nicaragua&lt;/option&gt; <br />
  &lt;option value=&quot;ne&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Niger&lt;/option&gt; <br />
  &lt;option value=&quot;ng&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Nigeria&lt;/option&gt; <br />
  &lt;option value=&quot;nu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Niue&lt;/option&gt; <br />
  &lt;option value=&quot;nf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Norfolk Island&lt;/option&gt; <br />
  &lt;option value=&quot;mp&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Northern Mariana Islands&lt;/option&gt; <br />
  &lt;option value=&quot;no&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Norway&lt;/option&gt; <br />
  &lt;option value=&quot;om&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Oman&lt;/option&gt; <br />
  &lt;option value=&quot;pk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Pakistan&lt;/option&gt; <br />
  &lt;option value=&quot;pw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Palau&lt;/option&gt; <br />
  &lt;option value=&quot;ps&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Palestinian Territory, Occupied&lt;/option&gt; <br />
  &lt;option value=&quot;pa&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Panama&lt;/option&gt; <br />
  &lt;option value=&quot;pg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Papua New Guinea&lt;/option&gt; <br />
  &lt;option value=&quot;py&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Paraguay&lt;/option&gt; <br />
  &lt;option value=&quot;pe&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Peru&lt;/option&gt; <br />
  &lt;option value=&quot;ph&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Philippines&lt;/option&gt; <br />
  &lt;option value=&quot;pn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Pitcairn&lt;/option&gt; <br />
  &lt;option value=&quot;pl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Poland&lt;/option&gt; <br />
  &lt;option value=&quot;pt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Portugal&lt;/option&gt; <br />
  &lt;option value=&quot;pr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Puerto Rico&lt;/option&gt; <br />
  &lt;option value=&quot;qa&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Qatar&lt;/option&gt; <br />
  &lt;option value=&quot;re&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Reunion&lt;/option&gt; <br />
  &lt;option value=&quot;ro&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Romania&lt;/option&gt; <br />
  &lt;option value=&quot;ru&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Russian Federation&lt;/option&gt; <br />
  &lt;option value=&quot;rw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Rwanda&lt;/option&gt; <br />
  &lt;option value=&quot;sh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saint Helena&lt;/option&gt; <br />
  &lt;option value=&quot;kn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saint Kitts and Nevis&lt;/option&gt; <br />
  &lt;option value=&quot;lc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saint Lucia&lt;/option&gt; <br />
  &lt;option value=&quot;pm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saint Pierre and Miquelon&lt;/option&gt; <br />
  &lt;option value=&quot;vc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saint Vincent and the Grenadines&lt;/option&gt; <br />
  &lt;option value=&quot;ws&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Samoa&lt;/option&gt; <br />
  &lt;option value=&quot;sm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;San Marino&lt;/option&gt; <br />
  &lt;option value=&quot;st&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Sao Tome and Principe&lt;/option&gt; <br />
  &lt;option value=&quot;sa&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Saudi Arabia&lt;/option&gt; <br />
  &lt;option value=&quot;sn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Senegal&lt;/option&gt; <br />
  &lt;option value=&quot;rs&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Serbia&lt;/option&gt; <br />
  &lt;option value=&quot;sc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Seychelles&lt;/option&gt; <br />
  &lt;option value=&quot;sl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Sierra Leone&lt;/option&gt; <br />
  &lt;option value=&quot;sg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Singapore&lt;/option&gt; <br />
  &lt;option value=&quot;sk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Slovakia&lt;/option&gt; <br />
  &lt;option value=&quot;si&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Slovenia&lt;/option&gt; <br />
  &lt;option value=&quot;sb&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Solomon Islands&lt;/option&gt; <br />
  &lt;option value=&quot;so&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Somalia&lt;/option&gt; <br />
  &lt;option value=&quot;za&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;South Africa&lt;/option&gt; <br />
  &lt;option value=&quot;gs&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;South Georgia and the South Sandwich Islands&lt;/option&gt; <br />
  &lt;option value=&quot;es&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Spain&lt;/option&gt; <br />
  &lt;option value=&quot;lk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Sri Lanka&lt;/option&gt; <br />
  &lt;option value=&quot;sd&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Sudan&lt;/option&gt; <br />
  &lt;option value=&quot;sr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Suriname&lt;/option&gt; <br />
  &lt;option value=&quot;sj&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Svalbard and Jan Mayen&lt;/option&gt; <br />
  &lt;option value=&quot;sz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Swaziland&lt;/option&gt; <br />
  &lt;option value=&quot;se&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Sweden&lt;/option&gt; <br />
  &lt;option value=&quot;ch&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Switzerland&lt;/option&gt; <br />
  &lt;option value=&quot;sy&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Syrian Arab Republic&lt;/option&gt; <br />
  &lt;option value=&quot;tw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Taiwan&lt;/option&gt; <br />
  &lt;option value=&quot;tj&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tajikistan&lt;/option&gt; <br />
  &lt;option value=&quot;tz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tanzania, United Republic of&lt;/option&gt; <br />
  &lt;option value=&quot;th&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Thailand&lt;/option&gt; <br />
  &lt;option value=&quot;tl&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Timor-Leste&lt;/option&gt; <br />
  &lt;option value=&quot;tg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Togo&lt;/option&gt; <br />
  &lt;option value=&quot;tk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tokelau&lt;/option&gt; <br />
  &lt;option value=&quot;to&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tonga&lt;/option&gt; <br />
  &lt;option value=&quot;tt&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Trinidad and Tobago&lt;/option&gt; <br />
  &lt;option value=&quot;tn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tunisia&lt;/option&gt; <br />
  &lt;option value=&quot;tr&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Turkey&lt;/option&gt; <br />
  &lt;option value=&quot;tm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Turkmenistan&lt;/option&gt; <br />
  &lt;option value=&quot;tc&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Turks and Caicos Islands&lt;/option&gt; <br />
  &lt;option value=&quot;tv&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Tuvalu&lt;/option&gt; <br />
  &lt;option value=&quot;ug&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Uganda&lt;/option&gt; <br />
  &lt;option value=&quot;ua&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Ukraine&lt;/option&gt; <br />
  &lt;option value=&quot;ae&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;United Arab Emirates&lt;/option&gt; <br />
  &lt;option value=&quot;uk&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;United Kingdom&lt;/option&gt; <br />
  &lt;option value=&quot;us&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='inline';javascript:document.getElementById('countrylabel').style.display='none';javascript:document.getElementById('postal').style.display='inline';javascript:document.getElementById('distlabel').style.display='inline';javascript:document.getElementById('dist').style.display='inline';&quot;  &gt;United States&lt;/option&gt; <br />
  &lt;option value=&quot;um&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;United States Minor Outlying Islands&lt;/option&gt; <br />
  &lt;option value=&quot;uy&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Uruguay&lt;/option&gt; <br />
  &lt;option value=&quot;uz&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Uzbekistan&lt;/option&gt; <br />
  &lt;option value=&quot;vu&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Vanuatu&lt;/option&gt; <br />
  &lt;option value=&quot;ve&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Venezuela&lt;/option&gt; <br />
  &lt;option value=&quot;vn&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Viet Nam&lt;/option&gt; <br />
  &lt;option value=&quot;vg&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Virgin Islands, British&lt;/option&gt; <br />
  &lt;option value=&quot;vi&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Virgin Islands, U.s.&lt;/option&gt; <br />
  &lt;option value=&quot;wf&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Wallis and Futuna&lt;/option&gt; <br />
  &lt;option value=&quot;eh&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Western Sahara&lt;/option&gt; <br />
  &lt;option value=&quot;ye&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Yemen&lt;/option&gt; <br />
  &lt;option value=&quot;zm&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Zambia&lt;/option&gt; <br />
  &lt;option value=&quot;zw&quot; onClick=&quot;javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('countrylabel').style.display='inline';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';&quot;  &gt;Zimbabwe&lt;/option&gt; </p>
<p> &lt;/SELECT&gt;&lt;/div&gt;</p>
<p>&nbsp;</p>
<p>&lt;/TD&gt;<br />
  &lt;td height=&quot;26&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD valign=&quot;top&quot; &gt;&lt;!--width=&quot;100&quot;--&gt;<br />
  &lt;div id=&quot;servicearealabel&quot;&gt;<br />
  Select Service Areas &lt;a href=&quot;/register.do?method=showHelp&quot; onClick=&quot;return popup(this, 'help')&quot;&gt;help&lt;/A&gt;</p>
<p> &lt;/div&gt;<br />
  &lt;div id=&quot;programtypelabel&quot; style=&quot;display:none;&quot;&gt;<br />
  Select Program Type &lt;a href=&quot;/register.do?method=showHelp&quot; onClick=&quot;return popup(this, 'help')&quot;&gt;help&lt;/A&gt;<br />
  &lt;/div&gt;<br />
  &lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  <br />
  <br />
  &lt;div id=&quot;servicearea&quot;&gt;<br />
  &lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot; size=&quot;1&quot; class=&quot;smalldropdown&quot; onchange=&quot;javascript:document.getElementById('serviceareadisplay2').style.display='inline';&quot;&gt; </p>
<p> &lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
  &lt;option value=&quot;4758&quot;  &gt;Addiction and Recovery&lt;/option&gt; <br />
  &lt;option value=&quot;4759&quot;  &gt;Administrative&lt;/option&gt; <br />
  &lt;option value=&quot;4760&quot;  &gt;Bible Study&lt;/option&gt; <br />
  &lt;option value=&quot;4761&quot;  &gt;Board Member&lt;/option&gt; <br />
  &lt;option value=&quot;4762&quot;  &gt;Camp Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;4763&quot;  &gt;Children and Youth&lt;/option&gt; <br />
  &lt;option value=&quot;7341&quot;  &gt;Christian/Catholic Schools&lt;/option&gt; <br />
  &lt;option value=&quot;4764&quot;  &gt;Church Planting&lt;/option&gt; <br />
  &lt;option value=&quot;4765&quot;  &gt;Community Development&lt;/option&gt; <br />
  &lt;option value=&quot;4766&quot;  &gt;Computers and Technology&lt;/option&gt; <br />
  &lt;option value=&quot;4767&quot;  &gt;Disabilities Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4769&quot;  &gt;Disaster Relief&lt;/option&gt; <br />
  &lt;option value=&quot;4768&quot;  &gt;Education and Literacy&lt;/option&gt; <br />
  &lt;option value=&quot;4770&quot;  &gt;Employment Training&lt;/option&gt; <br />
  &lt;option value=&quot;4771&quot;  &gt;Engineering and Construction&lt;/option&gt; <br />
  &lt;option value=&quot;4772&quot;  &gt;Evangelism&lt;/option&gt; <br />
  &lt;option value=&quot;4773&quot;  &gt;Family / Adults Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4774&quot;  &gt;Food Ministry / Hunger&lt;/option&gt; <br />
  &lt;option value=&quot;4775&quot;  &gt;Health and Medicine&lt;/option&gt; <br />
  &lt;option value=&quot;4776&quot;  &gt;Homelessness and Housing&lt;/option&gt; <br />
  &lt;option value=&quot;4777&quot;  &gt;Immigrants and Refugees&lt;/option&gt; <br />
  &lt;option value=&quot;4778&quot;  &gt;Justice and Legal&lt;/option&gt; <br />
  &lt;option value=&quot;4779&quot;  &gt;Language Translation&lt;/option&gt; <br />
  &lt;option value=&quot;4780&quot;  &gt;Mentoring&lt;/option&gt; <br />
  &lt;option value=&quot;4781&quot;  &gt;Orphanage&lt;/option&gt; <br />
  &lt;option value=&quot;4782&quot;  &gt;Prison Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;7342&quot;  &gt;Religious Education&lt;/option&gt; <br />
  &lt;option value=&quot;6843&quot;  &gt;Senior/Elderly Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4783&quot;  &gt;Single Parents / Crisis Pregnancy&lt;/option&gt; <br />
  &lt;option value=&quot;4784&quot;  &gt;Sports and Recreation&lt;/option&gt; <br />
  &lt;option value=&quot;4785&quot;  &gt;Teaching&lt;/option&gt; <br />
  &lt;option value=&quot;4786&quot;  &gt;Tutoring&lt;/option&gt; <br />
  &lt;option value=&quot;4787&quot;  &gt;Vacation Bible School&lt;/option&gt; <br />
  &lt;option value=&quot;4788&quot;  &gt;Visitation / Friendship&lt;/option&gt; <br />
  &lt;option value=&quot;4789&quot;  &gt;Women's Ministry&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;</p>
<p> &lt;div id=&quot;serviceareadisplay2&quot; style=&quot;display:none;&quot;&gt;&lt;p&gt;<br />
  &lt;SELECT id=&quot;servicearea2&quot; name=&quot;servicearea2&quot; class=&quot;smalldropdown&quot; onchange=&quot;javascript:document.getElementById('serviceareadisplay3').style.display='inline';&quot; &gt; </p>
<p> &lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
  &lt;option value=&quot;4758&quot;  &gt;Addiction and Recovery&lt;/option&gt; <br />
  &lt;option value=&quot;4759&quot;  &gt;Administrative&lt;/option&gt; <br />
  &lt;option value=&quot;4760&quot;  &gt;Bible Study&lt;/option&gt; <br />
  &lt;option value=&quot;4761&quot;  &gt;Board Member&lt;/option&gt; <br />
  &lt;option value=&quot;4762&quot;  &gt;Camp Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;4763&quot;  &gt;Children and Youth&lt;/option&gt; <br />
  &lt;option value=&quot;7341&quot;  &gt;Christian/Catholic Schools&lt;/option&gt; <br />
  &lt;option value=&quot;4764&quot;  &gt;Church Planting&lt;/option&gt; <br />
  &lt;option value=&quot;4765&quot;  &gt;Community Development&lt;/option&gt; <br />
  &lt;option value=&quot;4766&quot;  &gt;Computers and Technology&lt;/option&gt; <br />
  &lt;option value=&quot;4767&quot;  &gt;Disabilities Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4769&quot;  &gt;Disaster Relief&lt;/option&gt; <br />
  &lt;option value=&quot;4768&quot;  &gt;Education and Literacy&lt;/option&gt; <br />
  &lt;option value=&quot;4770&quot;  &gt;Employment Training&lt;/option&gt; <br />
  &lt;option value=&quot;4771&quot;  &gt;Engineering and Construction&lt;/option&gt; <br />
  &lt;option value=&quot;4772&quot;  &gt;Evangelism&lt;/option&gt; <br />
  &lt;option value=&quot;4773&quot;  &gt;Family / Adults Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4774&quot;  &gt;Food Ministry / Hunger&lt;/option&gt; <br />
  &lt;option value=&quot;4775&quot;  &gt;Health and Medicine&lt;/option&gt; <br />
  &lt;option value=&quot;4776&quot;  &gt;Homelessness and Housing&lt;/option&gt; <br />
  &lt;option value=&quot;4777&quot;  &gt;Immigrants and Refugees&lt;/option&gt; <br />
  &lt;option value=&quot;4778&quot;  &gt;Justice and Legal&lt;/option&gt; <br />
  &lt;option value=&quot;4779&quot;  &gt;Language Translation&lt;/option&gt; <br />
  &lt;option value=&quot;4780&quot;  &gt;Mentoring&lt;/option&gt; <br />
  &lt;option value=&quot;4781&quot;  &gt;Orphanage&lt;/option&gt; <br />
  &lt;option value=&quot;4782&quot;  &gt;Prison Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;7342&quot;  &gt;Religious Education&lt;/option&gt; <br />
  &lt;option value=&quot;6843&quot;  &gt;Senior/Elderly Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4783&quot;  &gt;Single Parents / Crisis Pregnancy&lt;/option&gt; <br />
  &lt;option value=&quot;4784&quot;  &gt;Sports and Recreation&lt;/option&gt; <br />
  &lt;option value=&quot;4785&quot;  &gt;Teaching&lt;/option&gt; <br />
  &lt;option value=&quot;4786&quot;  &gt;Tutoring&lt;/option&gt; <br />
  &lt;option value=&quot;4787&quot;  &gt;Vacation Bible School&lt;/option&gt; <br />
  &lt;option value=&quot;4788&quot;  &gt;Visitation / Friendship&lt;/option&gt; <br />
  &lt;option value=&quot;4789&quot;  &gt;Women's Ministry&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;&lt;/p&gt;&lt;/div&gt;</p>
<p> &lt;div id=&quot;serviceareadisplay3&quot; style=&quot;display:none;&quot;&gt;&lt;p&gt;<br />
  &lt;SELECT id=&quot;servicearea3&quot; name=&quot;servicearea3&quot; class=&quot;smalldropdown&quot; &gt; </p>
<p> &lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
  &lt;option value=&quot;4758&quot;  &gt;Addiction and Recovery&lt;/option&gt; <br />
  &lt;option value=&quot;4759&quot;  &gt;Administrative&lt;/option&gt; <br />
  &lt;option value=&quot;4760&quot;  &gt;Bible Study&lt;/option&gt; <br />
  &lt;option value=&quot;4761&quot;  &gt;Board Member&lt;/option&gt; <br />
  &lt;option value=&quot;4762&quot;  &gt;Camp Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;4763&quot;  &gt;Children and Youth&lt;/option&gt; <br />
  &lt;option value=&quot;7341&quot;  &gt;Christian/Catholic Schools&lt;/option&gt; <br />
  &lt;option value=&quot;4764&quot;  &gt;Church Planting&lt;/option&gt; <br />
  &lt;option value=&quot;4765&quot;  &gt;Community Development&lt;/option&gt; <br />
  &lt;option value=&quot;4766&quot;  &gt;Computers and Technology&lt;/option&gt; <br />
  &lt;option value=&quot;4767&quot;  &gt;Disabilities Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4769&quot;  &gt;Disaster Relief&lt;/option&gt; <br />
  &lt;option value=&quot;4768&quot;  &gt;Education and Literacy&lt;/option&gt; <br />
  &lt;option value=&quot;4770&quot;  &gt;Employment Training&lt;/option&gt; <br />
  &lt;option value=&quot;4771&quot;  &gt;Engineering and Construction&lt;/option&gt; <br />
  &lt;option value=&quot;4772&quot;  &gt;Evangelism&lt;/option&gt; <br />
  &lt;option value=&quot;4773&quot;  &gt;Family / Adults Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4774&quot;  &gt;Food Ministry / Hunger&lt;/option&gt; <br />
  &lt;option value=&quot;4775&quot;  &gt;Health and Medicine&lt;/option&gt; <br />
  &lt;option value=&quot;4776&quot;  &gt;Homelessness and Housing&lt;/option&gt; <br />
  &lt;option value=&quot;4777&quot;  &gt;Immigrants and Refugees&lt;/option&gt; <br />
  &lt;option value=&quot;4778&quot;  &gt;Justice and Legal&lt;/option&gt; <br />
  &lt;option value=&quot;4779&quot;  &gt;Language Translation&lt;/option&gt; <br />
  &lt;option value=&quot;4780&quot;  &gt;Mentoring&lt;/option&gt; <br />
  &lt;option value=&quot;4781&quot;  &gt;Orphanage&lt;/option&gt; <br />
  &lt;option value=&quot;4782&quot;  &gt;Prison Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;7342&quot;  &gt;Religious Education&lt;/option&gt; <br />
  &lt;option value=&quot;6843&quot;  &gt;Senior/Elderly Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4783&quot;  &gt;Single Parents / Crisis Pregnancy&lt;/option&gt; <br />
  &lt;option value=&quot;4784&quot;  &gt;Sports and Recreation&lt;/option&gt; <br />
  &lt;option value=&quot;4785&quot;  &gt;Teaching&lt;/option&gt; <br />
  &lt;option value=&quot;4786&quot;  &gt;Tutoring&lt;/option&gt; <br />
  &lt;option value=&quot;4787&quot;  &gt;Vacation Bible School&lt;/option&gt; <br />
  &lt;option value=&quot;4788&quot;  &gt;Visitation / Friendship&lt;/option&gt; <br />
  &lt;option value=&quot;4789&quot;  &gt;Women's Ministry&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;&lt;/p&gt;&lt;/div&gt;</p>
<p> &lt;/div&gt;<br />
  &lt;div id=&quot;programtype1&quot; style=&quot;display:none;&quot;&gt;<br />
  &lt;SELECT id=&quot;programtypetid&quot; name=&quot;programtypetid&quot; size=&quot;1&quot; class=&quot;smalldropdown&quot; &gt; </p>
<p> &lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
  &lt;option value=&quot;5229&quot; <br />
  &gt;Computer Center&lt;/option&gt; <br />
  &lt;option value=&quot;5230&quot; <br />
  &gt;Education/Employment Program&lt;/option&gt; <br />
  &lt;option value=&quot;5231&quot; <br />
  &gt;Food Program&lt;/option&gt; <br />
  &lt;option value=&quot;5232&quot; <br />
  &gt;Health Program&lt;/option&gt; <br />
  &lt;option value=&quot;5233&quot; <br />
  &gt;Homeless Program&lt;/option&gt; <br />
  &lt;option value=&quot;5234&quot; <br />
  &gt;Housing Program&lt;/option&gt; <br />
  &lt;option value=&quot;5235&quot; <br />
  &gt;Immigration Program&lt;/option&gt; <br />
  &lt;option value=&quot;5236&quot; <br />
  &gt;Other Program&lt;/option&gt; <br />
  &lt;option value=&quot;5237&quot; <br />
  &gt;Rehabilitation Program&lt;/option&gt; <br />
  &lt;option value=&quot;5238&quot; <br />
  &gt;Seniors Program&lt;/option&gt; <br />
  &lt;option value=&quot;5239&quot; <br />
  &gt;Spiritual Development Program&lt;/option&gt; <br />
  &lt;option value=&quot;6730&quot; <br />
  &gt;Youth Program&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt; <br />
  &lt;/div&gt;</p>
<p> &lt;/TD&gt;<br />
  &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;28&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
</p>
<p> &lt;tr&gt;<br />
  &lt;TD&gt;<br />
  &lt;div id=&quot;regionlabel&quot;&gt;Region&lt;/div&gt;&lt;/TD&gt;</p>
<p> &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;div id=&quot;regionsection&quot;&gt;&lt;SELECT id=&quot;regiontid&quot; name=&quot;regiontid&quot; class=&quot;smalldropdown&quot;&gt; <br />
  &lt;OPTION value=&quot;&quot;&gt;&lt;/OPTION&gt; <br />
  &lt;option value=&quot;4948&quot;  &gt;Africa&lt;/option&gt; <br />
  &lt;option value=&quot;8418&quot;  &gt;Americas&lt;/option&gt; <br />
  &lt;option value=&quot;4947&quot;  &gt;Asia&lt;/option&gt; <br />
  &lt;option value=&quot;4946&quot;  &gt;Europe&lt;/option&gt; <br />
  &lt;option value=&quot;4944&quot;  &gt;Latin America&lt;/option&gt; <br />
  &lt;option value=&quot;4950&quot;  &gt;Middle East&lt;/option&gt; <br />
  &lt;option value=&quot;4949&quot;  &gt;Oceania&lt;/option&gt; </p>
<p> &lt;/SELECT&gt;&lt;/div&gt;</p>
<p> &lt;/TD&gt;<br />
  &lt;td height=&quot;26&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
</p>
<p> &lt;tr&gt;<br />
  &lt;TD&gt;&lt;div id=&quot;distlabel&quot;&gt;<br />
  Distance&lt;/div&gt;&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;&lt;div id=&quot;dist&quot;&gt;</p>
<p> &lt;SELECT id=&quot;distance&quot; name=&quot;distance&quot; class=&quot;smalldropdown&quot; &gt; <br />
  &lt;option value=&quot;5&quot;&gt;5 miles (8K)&lt;/option&gt;</p>
<p> &lt;option value=&quot;20&quot;&gt;20 miles (32K)&lt;/option&gt;</p>
<p> &lt;option value=&quot;City&quot; selected=&quot;selected&quot;&gt;City&lt;/option&gt;<br />
  &lt;option value=&quot;Region&quot;&gt;Region&lt;/option&gt;<br />
  &lt;option value=&quot;Country&quot;&gt;Country&lt;/option&gt;</p>
<p> &lt;option value=&quot;Virtual&quot;&gt;Virtual&lt;/option&gt;<br />
  &lt;/SELECT&gt;&lt;/div&gt;<br />
  <br />
  &lt;/TD&gt;<br />
  &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;25&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;</p>
<p> &lt;tr&gt;<br />
  &lt;TD width=&quot;191&quot;&gt;</p>
<p> &lt;div id=&quot;grouplabel&quot;&gt;<br />
  I am a&lt;/div&gt;&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;div id=&quot;group&quot;&gt;<br />
  &lt;input type=&quot;checkbox&quot; name=&quot;greatfor4&quot; value=&quot;4793&quot; onclick=&quot;javascript:document.getElementById('group').style.display='inline';&quot; class=&quot;check&quot;&gt;<br />
  &amp;nbsp;Group <br />
  &lt;input type=&quot;checkbox&quot; name=&quot;greatfor5&quot; value=&quot;7536&quot; class=&quot;check&quot;&gt;<br />
  &amp;nbsp;Family<br />
  &lt;input type=&quot;checkbox&quot; name=&quot;greatfor1&quot; value=&quot;4790&quot; class=&quot;check&quot;&gt;</p>
<p> &amp;nbsp;Kid<br />
  &lt;input type=&quot;checkbox&quot; name=&quot;greatfor3&quot; value=&quot;4791&quot; class=&quot;check&quot;&gt;<br />
  &amp;nbsp;Teen <br />
  &lt;input type=&quot;checkbox&quot; name=&quot;greatfor2&quot; value=&quot;4792&quot; class=&quot;check&quot;&gt;<br />
  &amp;nbsp;Senior <br />
  &lt;/div&gt;<br />
  &lt;/TD&gt;<br />
  &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;29&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
</p>
<p> &lt;tr&gt;<br />
  &lt;TD colspan=3 &gt;&lt;HR&gt;&lt;/TD&gt;<br />
  &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;25&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD colspan=5&gt;<br />
  &lt;div id=&quot;opptype&quot; &gt;</p>
<p> &lt;table &gt;<br />
  &lt;tr&gt;<br />
  &lt;TD class=set&gt;<br />
  Choose One &lt;a href=&quot;/register.do?method=showHelp&quot; onClick=&quot;return popup(this, 'help')&quot;&gt;help&lt;/A&gt;<br />
  &lt;/TD&gt;<br />
  &lt;TD valign=&quot;middle&quot; colspan=&quot;2&quot;&gt;<br />
  &lt;dl&gt;</p>
<p> &lt;dt&gt;&lt;input type=&quot;radio&quot; name=&quot;postype&quot; value=&quot;4794&quot; onclick=&quot;clicked_local()&quot; class=&quot;radio&quot;&gt; Local Volunteering (in<br />
  person)&lt;/dt&gt;<br />
  &lt;dt&gt;&lt;input type=&quot;radio&quot; name=&quot;postype&quot; value=&quot;4795&quot; onclick=&quot;clicked_virtual()&quot; class=&quot;radio&quot;&gt;<br />
  Virtual Volunteering (location flexible)&lt;/dt&gt;</p>
<p> &lt;dt&gt;&lt;input type=&quot;radio&quot; name=&quot;postype&quot; value=&quot;4796&quot; onclick=&quot;clicked_missions()&quot; class=&quot;radio&quot;&gt; Short-term Missions / Volunteer Internship&lt;/dt&gt;<br />
    <br />
  &lt;div id=&quot;missions&quot; style=&quot;display:none; &quot;&gt;<br />
  &lt;table&gt;&lt;tr&gt;&lt;td width=40&gt;&lt;/td&gt;&lt;td&gt;</p>
<p> &lt;div id=&quot;better-select-edit-taxonomy-286&quot; class=&quot;better-select&quot;&gt;<br />
  &lt;div class=&quot;form-item&quot;&gt;<br />
  &lt;label&gt;Benefits Desired: &lt;/label&gt;<br />
  &lt;div class=&quot;form-checkboxes form-checkboxes-scroll&quot;&gt;<br />
  &lt;div id=&quot;edit-taxonomy-286-11546-wrapper&quot; class=&quot;form-item&quot;&gt;<br />
  &lt;label class=&quot;option&quot; for=&quot;edit-taxonomy-286-11546&quot;&gt;<br />
  &lt;input id=&quot;roomboard&quot; name=&quot;roomboard&quot; type=&quot;checkbox&quot; value=&quot;11546&quot; /&gt;<br />
  Room &amp; Board &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;</p>
<p> &lt;/label&gt;<br />
  &lt;/div&gt;<br />
  &lt;div id=&quot;edit-taxonomy-286-11547-wrapper&quot; class=&quot;form-item&quot;&gt;<br />
  &lt;label class=&quot;option&quot; for=&quot;edit-taxonomy-286-11547&quot;&gt;<br />
  &lt;input id=&quot;stipend&quot; name=&quot;stipend&quot; type=&quot;checkbox&quot; value=&quot;11547&quot; /&gt;<br />
  Stipend &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;<br />
  &lt;/label&gt;<br />
  &lt;/div&gt;</p>
<p> &lt;div id=&quot;edit-taxonomy-286-11548-wrapper&quot; class=&quot;form-item&quot;&gt;<br />
  &lt;label class=&quot;option&quot; for=&quot;edit-taxonomy-286-11548&quot;&gt;<br />
  &lt;input id=&quot;medinsur&quot; name=&quot;medinsur&quot; type=&quot;checkbox&quot; value=&quot;11548&quot; /&gt;<br />
  Medical Insurance &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;<br />
  &lt;/label&gt;<br />
  &lt;/div&gt;<br />
  &lt;div id=&quot;edit-taxonomy-286-11549-wrapper&quot; class=&quot;form-item&quot;&gt;<br />
  &lt;label class=&quot;option&quot; for=&quot;edit-taxonomy-286-11549&quot;&gt;</p>
<p> &lt;input id=&quot;transport&quot; name=&quot;transport&quot; type=&quot;checkbox&quot; value=&quot;11549&quot; /&gt;<br />
  Transportation &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;<br />
  &lt;/label&gt;<br />
  &lt;/div&gt;<br />
  &lt;div id=&quot;edit-taxonomy-286-11550-wrapper&quot; class=&quot;form-item&quot;&gt;<br />
  &lt;label class=&quot;option&quot; for=&quot;edit-taxonomy-286-11550&quot;&gt;<br />
  &lt;input id=&quot;americorps&quot; name=&quot;americorps&quot; type=&quot;checkbox&quot; value=&quot;11550&quot; /&gt;<br />
  AmeriCorps &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;</p>
<p> &lt;/label&gt;<br />
  &lt;/div&gt;<br />
  &lt;/div&gt;<br />
  &lt;/div&gt;<br />
  &lt;/div&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;<br />
  &lt;/div&gt;<br />
  &lt;dt&gt;&lt;input type=&quot;radio&quot; name=&quot;postype&quot; value=&quot;&quot; checked=&quot;checked&quot; onclick=&quot;clicked_all()&quot; class=&quot;radio&quot;&gt; All Volunteer Positions&lt;/dt&gt;</p>
<p> &lt;br/&gt;<br />
  &lt;dt&gt;&lt;input type=&quot;checkbox&quot; name=&quot;workstudy&quot; value=&quot;8104&quot; class=&quot;radio&quot;&gt;Search for Work Study Volunteer Opportunities&lt;/dt&gt;&lt;/dl&gt;<br />
  &lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;/table&gt;<br />
  &lt;/div&gt; <br />
  &lt;/td&gt;&lt;/tr&gt; <br />
  &lt;tr&gt;<br />
  &lt;TD colspan=4&gt;<br />
  &lt;div id=&quot;missions-duration&quot; style=&quot;display:none&quot;&gt;</p>
<p> &lt;table cellspacing=0 cellpadding=2 &gt;</p>
<p> &lt;tr&gt;<br />
  &lt;TD &gt;<br />
  Duration&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;select id=&quot;duration&quot; name=&quot;duration&quot; class=&quot;smalldropdown&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot; &gt;<br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;Less than a week&quot;  &gt;Less than a week&lt;/option&gt; <br />
  &lt;option value=&quot;Summer (June-Aug)&quot;  &gt;Summer (June-Aug)&lt;/option&gt; <br />
  &lt;option value=&quot;One Year&quot;  &gt;One Year&lt;/option&gt; <br />
  &lt;option value=&quot;1 to 2 weeks&quot;  &gt;1 to 2 weeks&lt;/option&gt; <br />
  &lt;option value=&quot;3 to 4 weeks&quot;  &gt;3 to 4 weeks&lt;/option&gt; <br />
  &lt;option value=&quot;1 to 2 months&quot;  &gt;1 to 2 months&lt;/option&gt; <br />
  &lt;option value=&quot;3 to 5 months&quot;  &gt;3 to 5 months&lt;/option&gt; <br />
  &lt;option value=&quot;6 to 12 months&quot;  &gt;6 to 12 months&lt;/option&gt; <br />
  &lt;option value=&quot;1 to 2 years&quot;  &gt;1 to 2 years&lt;/option&gt; <br />
  &lt;option value=&quot;2 to 3 years&quot;  &gt;2 to 3 years&lt;/option&gt; </p>
<p> &lt;/select&gt;</p>
<p> &lt;/td&gt;<br />
    <br />
  &lt;td height=&quot;29&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;td height=&quot;29&quot;&gt;Minimum Group Size&lt;/td&gt;<br />
  &lt;td&gt;&lt;input type=&quot;text&quot; name=&quot;mingroup&quot; maxlength=&quot;5&quot; size=&quot;5&quot; value=&quot;&quot; class=&quot;textinputwhite&quot;&gt;&lt;/td&gt;<br />
  &lt;td&gt;&amp;nbsp;&amp;nbsp;Maximum Group Size&lt;/td&gt;<br />
  &lt;td&gt;&lt;input type=&quot;text&quot; name=&quot;maxgroup&quot; maxlength=&quot;5&quot; size=&quot;5&quot; value=&quot;&quot; class=&quot;textinputwhite&quot;&gt;&lt;/td&gt;</p>
<p> <br />
    <br />
    <br />
  &lt;/tr&gt;<br />
  &lt;/table&gt;<br />
  &lt;/div&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;<br />
  <br />
  &lt;tr&gt;<br />
  &lt;TD colspan=4&gt;<br />
  &lt;div id=&quot;virtualaddr&quot;&gt;<br />
  &lt;table cellspacing=0 cellpadding=0&gt;<br />
  <br />
  &lt;!--postal code and distance --&gt; <br />
  &lt;tr&gt;</p>
<p> &lt;td height=&quot;15&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  <br />
  &lt;tr&gt;&lt;td colspan=5&gt; <br />
  &lt;fieldset style=&quot;width: 400px; height: 75px&quot; align=&quot;center&quot;&gt;<br />
  &lt;table&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;<br />
  City&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;&lt;input type=&quot;text&quot; name=&quot;city&quot; maxlength=&quot;50&quot; size=&quot;15&quot; value=&quot;&quot; id=&quot;city&quot; class=&quot;textinputwhite&quot;&gt;&lt;/TD&gt;</p>
<p> &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;30&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;<br />
  State&lt;/TD&gt;<br />
  &lt;TD &gt;<br />
  &lt;SELECT id=&quot;state&quot; name=&quot;state&quot; class=&quot;smalldropdown&quot;&gt; <br />
  &lt;OPTION value=&quot;&quot;&gt;&lt;/OPTION&gt; <br />
  &lt;option value=&quot;AL&quot;  &gt; Alabama &lt;/option&gt; <br />
  &lt;option value=&quot;AK&quot;  &gt; Alaska &lt;/option&gt; <br />
  &lt;option value=&quot;AB&quot;  &gt; Alberta &lt;/option&gt; <br />
  &lt;option value=&quot;AS&quot;  &gt; American Samoa &lt;/option&gt; <br />
  &lt;option value=&quot;AZ&quot;  &gt; Arizona &lt;/option&gt; <br />
  &lt;option value=&quot;AR&quot;  &gt; Arkansas &lt;/option&gt; <br />
  &lt;option value=&quot;BC&quot;  &gt; British Columbia &lt;/option&gt; <br />
  &lt;option value=&quot;CA&quot;  &gt; California &lt;/option&gt; <br />
  &lt;option value=&quot;CO&quot;  &gt; Colorado &lt;/option&gt; <br />
  &lt;option value=&quot;CT&quot;  &gt; Connecticut &lt;/option&gt; <br />
  &lt;option value=&quot;DE&quot;  &gt; Delaware &lt;/option&gt; <br />
  &lt;option value=&quot;DC&quot;  &gt; District of Columbia &lt;/option&gt; <br />
  &lt;option value=&quot;FM&quot;  &gt; Federated States Of Micronesia &lt;/option&gt; <br />
  &lt;option value=&quot;FL&quot;  &gt; Florida &lt;/option&gt; <br />
  &lt;option value=&quot;GA&quot;  &gt; Georgia &lt;/option&gt; <br />
  &lt;option value=&quot;GU&quot;  &gt; Guam &lt;/option&gt; <br />
  &lt;option value=&quot;HI&quot;  &gt; Hawaii &lt;/option&gt; <br />
  &lt;option value=&quot;ID&quot;  &gt; Idaho &lt;/option&gt; <br />
  &lt;option value=&quot;IL&quot;  &gt; Illinois &lt;/option&gt; <br />
  &lt;option value=&quot;IN&quot;  &gt; Indiana &lt;/option&gt; <br />
  &lt;option value=&quot;IA&quot;  &gt; Iowa &lt;/option&gt; <br />
  &lt;option value=&quot;KS&quot;  &gt; Kansas &lt;/option&gt; <br />
  &lt;option value=&quot;KY&quot;  &gt; Kentucky &lt;/option&gt; <br />
  &lt;option value=&quot;LA&quot;  &gt; Louisiana &lt;/option&gt; <br />
  &lt;option value=&quot;ME&quot;  &gt; Maine &lt;/option&gt; <br />
  &lt;option value=&quot;MB&quot;  &gt; Manitoba &lt;/option&gt; <br />
  &lt;option value=&quot;MH&quot;  &gt; Marshall Islands &lt;/option&gt; <br />
  &lt;option value=&quot;MD&quot;  &gt; Maryland &lt;/option&gt; <br />
  &lt;option value=&quot;MA&quot;  &gt; Massachusetts &lt;/option&gt; <br />
  &lt;option value=&quot;MI&quot;  &gt; Michigan &lt;/option&gt; <br />
  &lt;option value=&quot;MN&quot;  &gt; Minnesota &lt;/option&gt; <br />
  &lt;option value=&quot;MS&quot;  &gt; Mississippi &lt;/option&gt; <br />
  &lt;option value=&quot;MO&quot;  &gt; Missouri &lt;/option&gt; <br />
  &lt;option value=&quot;MT&quot;  &gt; Montana &lt;/option&gt; <br />
  &lt;option value=&quot;NE&quot;  &gt; Nebraska &lt;/option&gt; <br />
  &lt;option value=&quot;NV&quot;  &gt; Nevada &lt;/option&gt; <br />
  &lt;option value=&quot;NB&quot;  &gt; New Brunswick &lt;/option&gt; <br />
  &lt;option value=&quot;NH&quot;  &gt; New Hampshire &lt;/option&gt; <br />
  &lt;option value=&quot;NJ&quot;  &gt; New Jersey &lt;/option&gt; <br />
  &lt;option value=&quot;NM&quot;  &gt; New Mexico &lt;/option&gt; <br />
  &lt;option value=&quot;NY&quot;  &gt; New York &lt;/option&gt; <br />
  &lt;option value=&quot;NF&quot;  &gt; Newfoundland &lt;/option&gt; <br />
  &lt;option value=&quot;NC&quot;  &gt; North Carolina &lt;/option&gt; <br />
  &lt;option value=&quot;ND&quot;  &gt; North Dakota &lt;/option&gt; <br />
  &lt;option value=&quot;MP&quot;  &gt; Northern Mariana Islands &lt;/option&gt; <br />
  &lt;option value=&quot;NT&quot;  &gt; Northwest Territories &lt;/option&gt; <br />
  &lt;option value=&quot;NS&quot;  &gt; Nova Scotia &lt;/option&gt; <br />
  &lt;option value=&quot;OH&quot;  &gt; Ohio &lt;/option&gt; <br />
  &lt;option value=&quot;OK&quot;  &gt; Oklahoma &lt;/option&gt; <br />
  &lt;option value=&quot;ON&quot;  &gt; Ontario &lt;/option&gt; <br />
  &lt;option value=&quot;OR&quot;  &gt; Oregon &lt;/option&gt; <br />
  &lt;option value=&quot;PW&quot;  &gt; Palau &lt;/option&gt; <br />
  &lt;option value=&quot;PA&quot;  &gt; Pennsylvania &lt;/option&gt; <br />
  &lt;option value=&quot;PE&quot;  &gt; Prince Edward Island &lt;/option&gt; <br />
  &lt;option value=&quot;PR&quot;  &gt; Puerto Rico &lt;/option&gt; <br />
  &lt;option value=&quot;QC&quot;  &gt; Quebec &lt;/option&gt; <br />
  &lt;option value=&quot;PQ&quot;  &gt; Quebec &lt;/option&gt; <br />
  &lt;option value=&quot;RI&quot;  &gt; Rhode Island &lt;/option&gt; <br />
  &lt;option value=&quot;SK&quot;  &gt; Saskatchewan &lt;/option&gt; <br />
  &lt;option value=&quot;SC&quot;  &gt; South Carolina &lt;/option&gt; <br />
  &lt;option value=&quot;SD&quot;  &gt; South Dakota &lt;/option&gt; <br />
  &lt;option value=&quot;TN&quot;  &gt; Tennessee &lt;/option&gt; <br />
  &lt;option value=&quot;TX&quot;  &gt; Texas &lt;/option&gt; <br />
  &lt;option value=&quot;UT&quot;  &gt; Utah &lt;/option&gt; <br />
  &lt;option value=&quot;VT&quot;  &gt; Vermont &lt;/option&gt; <br />
  &lt;option value=&quot;VI&quot;  &gt; Virgin Islands &lt;/option&gt; <br />
  &lt;option value=&quot;VA&quot;  &gt; Virginia &lt;/option&gt; <br />
  &lt;option value=&quot;WA&quot;  &gt; Washington &lt;/option&gt; <br />
  &lt;option value=&quot;WV&quot;  &gt; West Virginia &lt;/option&gt; <br />
  &lt;option value=&quot;WI&quot;  &gt; Wisconsin &lt;/option&gt; <br />
  &lt;option value=&quot;WY&quot;  &gt; Wyoming &lt;/option&gt; <br />
  &lt;option value=&quot;YT&quot;  &gt; Yukon &lt;/option&gt; </p>
<p> &lt;/SELECT&gt;&lt;/td&gt;&lt;td&gt;&lt;!--Other html:text property=&quot;prov&quot; size=&quot;10&quot; styleClass=&quot;textinputwhite&quot;/--&gt;</p>
<p> <br />
  &lt;/TD&gt;<br />
  &lt;td&gt;&lt;/td&gt;<br />
  &lt;td height=&quot;37&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
</p>
<p>&lt;!-- country and region --&gt;</p>
<p> &lt;/table&gt;&lt;/fieldset&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;<br />
</p>
<p>&lt;tr&gt;<br />
  &lt;td height=&quot;15&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;/table&gt;&lt;/div&gt;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
</p>
<p>&nbsp;</p>
<p>&lt;!-- service area and program types --&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD colspan=3 &gt;&lt;HR&gt;&lt;/TD&gt;<br />
  &lt;td height=&quot;25&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;</p>
<p> &lt;tr&gt;<br />
  &lt;TD&gt;<br />
  Organization&lt;br&gt;Name&lt;/TD&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;input type=&quot;text&quot; name=&quot;orgname&quot; maxlength=&quot;200&quot; size=&quot;30&quot; value=&quot;&quot; id=&quot;orgname&quot; class=&quot;textinputwhite&quot;&gt;<br />
  &lt;/TD&gt;<br />
  &lt;td height=&quot;26&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;</p>
<p> &lt;tr&gt;<br />
  &lt;TD valign=&quot;top&quot;&gt;<br />
  &lt;div id=&quot;skillsgrouplabel&quot;&gt;Skills Needed&lt;/div&gt;&lt;/TD&gt;<br />
  &lt;TD TD valign=&quot;top&quot; colspan=&quot;2&quot;&gt;<br />
  &lt;div id=&quot;skillsgroup&quot;&gt;&lt;p&gt;<br />
  &lt;select id=&quot;skills1id&quot; name=&quot;skills1id&quot; class=&quot;smalldropdown&quot; onchange=&quot;javascript:document.getElementById('skill2').style.display='inline';&quot;&gt;<br />
  &lt;option value=&quot;&quot; selected&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;4719&quot;  &gt;Accountant / Bookkeeper / Auditor&lt;/option&gt; <br />
  &lt;option value=&quot;4720&quot;  &gt;Administrator / Office Skills&lt;/option&gt; <br />
  &lt;option value=&quot;4721&quot;  &gt;Agriculture&lt;/option&gt; <br />
  &lt;option value=&quot;4722&quot;  &gt;Architect / Engineer&lt;/option&gt; <br />
  &lt;option value=&quot;4723&quot;  &gt;Artist (Performing / Creative)&lt;/option&gt; <br />
  &lt;option value=&quot;4724&quot;  &gt;Audiovisual&lt;/option&gt; <br />
  &lt;option value=&quot;8121&quot;  &gt;Aviation&lt;/option&gt; <br />
  &lt;option value=&quot;4727&quot;  &gt;Business / Marketing Specialist&lt;/option&gt; <br />
  &lt;option value=&quot;4728&quot;  &gt;Computer / Tech Support&lt;/option&gt; <br />
  &lt;option value=&quot;4729&quot;  &gt;Computer Programmer&lt;/option&gt; <br />
  &lt;option value=&quot;4730&quot;  &gt;Computer Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4731&quot;  &gt;Construction / Maintenance&lt;/option&gt; <br />
  &lt;option value=&quot;4732&quot;  &gt;Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;8122&quot;  &gt;Deaf Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4734&quot;  &gt;Doctor / Dentist&lt;/option&gt; <br />
  &lt;option value=&quot;4735&quot;  &gt;Editor / Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4736&quot;  &gt;Electrician&lt;/option&gt; <br />
  &lt;option value=&quot;4737&quot;  &gt;Event Planning&lt;/option&gt; <br />
  &lt;option value=&quot;4738&quot;  &gt;Financial Services / Advisor&lt;/option&gt; <br />
  &lt;option value=&quot;4739&quot;  &gt;First Aid / CPR Certified&lt;/option&gt; <br />
  &lt;option value=&quot;4740&quot;  &gt;Fundraiser&lt;/option&gt; <br />
  &lt;option value=&quot;4741&quot;  &gt;Grant / Proposal Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4742&quot;  &gt;Language Translator / Interpreter&lt;/option&gt; <br />
  &lt;option value=&quot;4744&quot;  &gt;Lawyer / Legal Services&lt;/option&gt; <br />
  &lt;option value=&quot;4745&quot;  &gt;Musician / Worship Leader&lt;/option&gt; <br />
  &lt;option value=&quot;4746&quot;  &gt;Photographer&lt;/option&gt; <br />
  &lt;option value=&quot;4747&quot;  &gt;Plumber / HVAC&lt;/option&gt; <br />
  &lt;option value=&quot;4748&quot;  &gt;Prayer Minister / Intercessor&lt;/option&gt; <br />
  &lt;option value=&quot;4749&quot;  &gt;Preacher / Minister&lt;/option&gt; <br />
  &lt;option value=&quot;4750&quot;  &gt;Public Relations&lt;/option&gt; <br />
  &lt;option value=&quot;4752&quot;  &gt;Social Worker&lt;/option&gt; <br />
  &lt;option value=&quot;4753&quot;  &gt;Teacher / Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4754&quot;  &gt;Transportation Services&lt;/option&gt; <br />
  &lt;option value=&quot;4755&quot;  &gt;Video Production&lt;/option&gt; <br />
  &lt;option value=&quot;4756&quot;  &gt;Web / Graphics Designer&lt;/option&gt; <br />
  &lt;option value=&quot;4757&quot;  &gt;Youth Worker / Childcare&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;&lt;/p&gt;</p>
<p> &lt;div id=&quot;skill2&quot; style=&quot;display:none;&quot;&gt;&lt;p&gt;<br />
  &lt;select id=&quot;skills2id&quot; name=&quot;skills2id&quot; class=&quot;smalldropdown&quot; onchange=&quot;javascript:document.getElementById('skill3').style.display='inline';&quot;&gt;<br />
  &lt;option value=&quot;&quot; selected&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;4719&quot;  &gt;Accountant / Bookkeeper / Auditor&lt;/option&gt; <br />
  &lt;option value=&quot;4720&quot;  &gt;Administrator / Office Skills&lt;/option&gt; <br />
  &lt;option value=&quot;4721&quot;  &gt;Agriculture&lt;/option&gt; <br />
  &lt;option value=&quot;4722&quot;  &gt;Architect / Engineer&lt;/option&gt; <br />
  &lt;option value=&quot;4723&quot;  &gt;Artist (Performing / Creative)&lt;/option&gt; <br />
  &lt;option value=&quot;4724&quot;  &gt;Audiovisual&lt;/option&gt; <br />
  &lt;option value=&quot;8121&quot;  &gt;Aviation&lt;/option&gt; <br />
  &lt;option value=&quot;4727&quot;  &gt;Business / Marketing Specialist&lt;/option&gt; <br />
  &lt;option value=&quot;4728&quot;  &gt;Computer / Tech Support&lt;/option&gt; <br />
  &lt;option value=&quot;4729&quot;  &gt;Computer Programmer&lt;/option&gt; <br />
  &lt;option value=&quot;4730&quot;  &gt;Computer Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4731&quot;  &gt;Construction / Maintenance&lt;/option&gt; <br />
  &lt;option value=&quot;4732&quot;  &gt;Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;8122&quot;  &gt;Deaf Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4734&quot;  &gt;Doctor / Dentist&lt;/option&gt; <br />
  &lt;option value=&quot;4735&quot;  &gt;Editor / Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4736&quot;  &gt;Electrician&lt;/option&gt; <br />
  &lt;option value=&quot;4737&quot;  &gt;Event Planning&lt;/option&gt; <br />
  &lt;option value=&quot;4738&quot;  &gt;Financial Services / Advisor&lt;/option&gt; <br />
  &lt;option value=&quot;4739&quot;  &gt;First Aid / CPR Certified&lt;/option&gt; <br />
  &lt;option value=&quot;4740&quot;  &gt;Fundraiser&lt;/option&gt; <br />
  &lt;option value=&quot;4741&quot;  &gt;Grant / Proposal Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4742&quot;  &gt;Language Translator / Interpreter&lt;/option&gt; <br />
  &lt;option value=&quot;4744&quot;  &gt;Lawyer / Legal Services&lt;/option&gt; <br />
  &lt;option value=&quot;4745&quot;  &gt;Musician / Worship Leader&lt;/option&gt; <br />
  &lt;option value=&quot;4746&quot;  &gt;Photographer&lt;/option&gt; <br />
  &lt;option value=&quot;4747&quot;  &gt;Plumber / HVAC&lt;/option&gt; <br />
  &lt;option value=&quot;4748&quot;  &gt;Prayer Minister / Intercessor&lt;/option&gt; <br />
  &lt;option value=&quot;4749&quot;  &gt;Preacher / Minister&lt;/option&gt; <br />
  &lt;option value=&quot;4750&quot;  &gt;Public Relations&lt;/option&gt; <br />
  &lt;option value=&quot;4752&quot;  &gt;Social Worker&lt;/option&gt; <br />
  &lt;option value=&quot;4753&quot;  &gt;Teacher / Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4754&quot;  &gt;Transportation Services&lt;/option&gt; <br />
  &lt;option value=&quot;4755&quot;  &gt;Video Production&lt;/option&gt; <br />
  &lt;option value=&quot;4756&quot;  &gt;Web / Graphics Designer&lt;/option&gt; <br />
  &lt;option value=&quot;4757&quot;  &gt;Youth Worker / Childcare&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;&lt;/p&gt;&lt;/div&gt;</p>
<p> &lt;div id=&quot;skill3&quot; style=&quot;display:none;&quot;&gt;&lt;p&gt;<br />
  &lt;select id=&quot;skills3id&quot; name=&quot;skills3id&quot; class=&quot;smalldropdown&quot; &gt;<br />
  &lt;option value=&quot;&quot; selected&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;4719&quot;  &gt;Accountant / Bookkeeper / Auditor&lt;/option&gt; <br />
  &lt;option value=&quot;4720&quot;  &gt;Administrator / Office Skills&lt;/option&gt; <br />
  &lt;option value=&quot;4721&quot;  &gt;Agriculture&lt;/option&gt; <br />
  &lt;option value=&quot;4722&quot;  &gt;Architect / Engineer&lt;/option&gt; <br />
  &lt;option value=&quot;4723&quot;  &gt;Artist (Performing / Creative)&lt;/option&gt; <br />
  &lt;option value=&quot;4724&quot;  &gt;Audiovisual&lt;/option&gt; <br />
  &lt;option value=&quot;8121&quot;  &gt;Aviation&lt;/option&gt; <br />
  &lt;option value=&quot;4727&quot;  &gt;Business / Marketing Specialist&lt;/option&gt; <br />
  &lt;option value=&quot;4728&quot;  &gt;Computer / Tech Support&lt;/option&gt; <br />
  &lt;option value=&quot;4729&quot;  &gt;Computer Programmer&lt;/option&gt; <br />
  &lt;option value=&quot;4730&quot;  &gt;Computer Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4731&quot;  &gt;Construction / Maintenance&lt;/option&gt; <br />
  &lt;option value=&quot;4732&quot;  &gt;Counselor&lt;/option&gt; <br />
  &lt;option value=&quot;8122&quot;  &gt;Deaf Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;4734&quot;  &gt;Doctor / Dentist&lt;/option&gt; <br />
  &lt;option value=&quot;4735&quot;  &gt;Editor / Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4736&quot;  &gt;Electrician&lt;/option&gt; <br />
  &lt;option value=&quot;4737&quot;  &gt;Event Planning&lt;/option&gt; <br />
  &lt;option value=&quot;4738&quot;  &gt;Financial Services / Advisor&lt;/option&gt; <br />
  &lt;option value=&quot;4739&quot;  &gt;First Aid / CPR Certified&lt;/option&gt; <br />
  &lt;option value=&quot;4740&quot;  &gt;Fundraiser&lt;/option&gt; <br />
  &lt;option value=&quot;4741&quot;  &gt;Grant / Proposal Writer&lt;/option&gt; <br />
  &lt;option value=&quot;4742&quot;  &gt;Language Translator / Interpreter&lt;/option&gt; <br />
  &lt;option value=&quot;4744&quot;  &gt;Lawyer / Legal Services&lt;/option&gt; <br />
  &lt;option value=&quot;4745&quot;  &gt;Musician / Worship Leader&lt;/option&gt; <br />
  &lt;option value=&quot;4746&quot;  &gt;Photographer&lt;/option&gt; <br />
  &lt;option value=&quot;4747&quot;  &gt;Plumber / HVAC&lt;/option&gt; <br />
  &lt;option value=&quot;4748&quot;  &gt;Prayer Minister / Intercessor&lt;/option&gt; <br />
  &lt;option value=&quot;4749&quot;  &gt;Preacher / Minister&lt;/option&gt; <br />
  &lt;option value=&quot;4750&quot;  &gt;Public Relations&lt;/option&gt; <br />
  &lt;option value=&quot;4752&quot;  &gt;Social Worker&lt;/option&gt; <br />
  &lt;option value=&quot;4753&quot;  &gt;Teacher / Trainer&lt;/option&gt; <br />
  &lt;option value=&quot;4754&quot;  &gt;Transportation Services&lt;/option&gt; <br />
  &lt;option value=&quot;4755&quot;  &gt;Video Production&lt;/option&gt; <br />
  &lt;option value=&quot;4756&quot;  &gt;Web / Graphics Designer&lt;/option&gt; <br />
  &lt;option value=&quot;4757&quot;  &gt;Youth Worker / Childcare&lt;/option&gt; <br />
</p>
<p> &lt;/SELECT&gt;&lt;/p&gt;&lt;/div&gt;</p>
<p> &lt;/div&gt;<br />
  &lt;/TD&gt;<br />
  &lt;/TR&gt;</p>
<p>&lt;!-- kid, group, seniors,...--&gt;<br />
  &lt;!-- group size --&gt; </p>
<p>&lt;/td&gt;&lt;/tr&gt;</p>
<p>&nbsp;</p>
<p> &lt;tr&gt;<br />
  &lt;TD class=set valign=&quot;top&quot;&gt;<br />
  Denominational&lt;br&gt;Affiliation&lt;/TD&gt;</p>
<p> &lt;TD colspan=&quot;2&quot;&gt;<br />
  &lt;select id=&quot;denomaffiltid&quot; name=&quot;denomaffiltid&quot; class=&quot;smalldropdown&quot; &gt;<br />
  &lt;option value=&quot;&quot; selected&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;981&quot;  &gt;A.M.E.&lt;/option&gt; <br />
  &lt;option value=&quot;982&quot;  &gt;A.M.E. Zion&lt;/option&gt; <br />
  &lt;option value=&quot;983&quot;  &gt;Adventist&lt;/option&gt; <br />
  &lt;option value=&quot;984&quot;  &gt;Anglican&lt;/option&gt; <br />
  &lt;option value=&quot;985&quot;  &gt;Apostolic&lt;/option&gt; <br />
  &lt;option value=&quot;986&quot;  &gt;Assemblies of God&lt;/option&gt; <br />
  &lt;option value=&quot;987&quot;  &gt;Baptist/American Baptist&lt;/option&gt; <br />
  &lt;option value=&quot;988&quot;  &gt;Baptist/Free Will Baptist&lt;/option&gt; <br />
  &lt;option value=&quot;989&quot;  &gt;Baptist/Other&lt;/option&gt; <br />
  &lt;option value=&quot;990&quot;  &gt;Baptist/Southern Baptist&lt;/option&gt; <br />
  &lt;option value=&quot;991&quot;  &gt;Bible Church&lt;/option&gt; <br />
  &lt;option value=&quot;8783&quot;  &gt;Calvary Chapel&lt;/option&gt; <br />
  &lt;option value=&quot;992&quot;  &gt;Catholic&lt;/option&gt; <br />
  &lt;option value=&quot;993&quot;  &gt;Charismatic&lt;/option&gt; <br />
  &lt;option value=&quot;994&quot;  &gt;Charismatic Episcopal Church&lt;/option&gt; <br />
  &lt;option value=&quot;995&quot;  &gt;Christian &amp; Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;996&quot;  &gt;Christian Church/DOC&lt;/option&gt; <br />
  &lt;option value=&quot;997&quot;  &gt;Christian Methodist Episcopal Church/CME&lt;/option&gt; <br />
  &lt;option value=&quot;998&quot;  &gt;Christian Reformed/CRC&lt;/option&gt; <br />
  &lt;option value=&quot;999&quot;  &gt;Church of Christ/COC&lt;/option&gt; <br />
  &lt;option value=&quot;1000&quot;  &gt;Church of God in Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1001&quot;  &gt;Church of God/Anderson&lt;/option&gt; <br />
  &lt;option value=&quot;1002&quot;  &gt;Church of God/Cleveland&lt;/option&gt; <br />
  &lt;option value=&quot;19536&quot;  &gt;Church of Our Lord Jesus Christ of the Apostolic Faith&lt;/option&gt; <br />
  &lt;option value=&quot;1003&quot;  &gt;Congregational&lt;/option&gt; <br />
  &lt;option value=&quot;1142&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1004&quot;  &gt;Episcopal&lt;/option&gt; <br />
  &lt;option value=&quot;1005&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1006&quot;  &gt;Evangelical Free Church&lt;/option&gt; <br />
  &lt;option value=&quot;1146&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;1007&quot;  &gt;Foursquare Gospel Church&lt;/option&gt; <br />
  &lt;option value=&quot;19541&quot;  &gt;Full Gospel Baptist Church Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1008&quot;  &gt;Holiness&lt;/option&gt; <br />
  &lt;option value=&quot;1009&quot;  &gt;Holiness-Apostolic&lt;/option&gt; <br />
  &lt;option value=&quot;1010&quot;  &gt;Holiness-Pentecostal&lt;/option&gt; <br />
  &lt;option value=&quot;1011&quot;  &gt;Independent&lt;/option&gt; <br />
  &lt;option value=&quot;1012&quot;  &gt;Interdenominational&lt;/option&gt; <br />
  &lt;option value=&quot;1013&quot;  &gt;International Council of Community Churches&lt;/option&gt; <br />
  &lt;option value=&quot;1014&quot;  &gt;Jesus Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1015&quot;  &gt;Lutheran/ELCA&lt;/option&gt; <br />
  &lt;option value=&quot;1016&quot;  &gt;Lutheran/LCMS&lt;/option&gt; <br />
  &lt;option value=&quot;1017&quot;  &gt;Lutheran/WELS&lt;/option&gt; <br />
  &lt;option value=&quot;1018&quot;  &gt;Mennonite&lt;/option&gt; <br />
  &lt;option value=&quot;1019&quot;  &gt;Methodist&lt;/option&gt; <br />
  &lt;option value=&quot;19543&quot;  &gt;National Baptist Convention of America, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;19542&quot;  &gt;National Baptist Convention, USA&lt;/option&gt; <br />
  &lt;option value=&quot;19544&quot;  &gt;National Missionary Baptist Convention of America&lt;/option&gt; <br />
  &lt;option value=&quot;1020&quot;  &gt;Nazarene&lt;/option&gt; <br />
  &lt;option value=&quot;1021&quot;  &gt;Non-denominational&lt;/option&gt; <br />
  &lt;option value=&quot;1022&quot;  &gt;Orthodox&lt;/option&gt; <br />
  &lt;option value=&quot;1023&quot;  &gt;Other&lt;/option&gt; <br />
  &lt;option value=&quot;1024&quot;  &gt;Parachurch Organization&lt;/option&gt; <br />
  &lt;option value=&quot;1025&quot;  &gt;Pentecostal&lt;/option&gt; <br />
  &lt;option value=&quot;19548&quot;  &gt;Pentecostal Assemblies of the World&lt;/option&gt; <br />
  &lt;option value=&quot;1026&quot;  &gt;Presbyterian&lt;/option&gt; <br />
  &lt;option value=&quot;19547&quot;  &gt;Primitive Baptist Church&lt;/option&gt; <br />
  &lt;option value=&quot;19545&quot;  &gt;Progressive National Baptist Convention&lt;/option&gt; <br />
  &lt;option value=&quot;1027&quot;  &gt;Quaker&lt;/option&gt; <br />
  &lt;option value=&quot;1028&quot;  &gt;Reformed/RCA&lt;/option&gt; <br />
  &lt;option value=&quot;1029&quot;  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1030&quot;  &gt;Union&lt;/option&gt; <br />
  &lt;option value=&quot;1031&quot;  &gt;United Church of Canada&lt;/option&gt; <br />
  &lt;option value=&quot;1032&quot;  &gt;United Church of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;19535&quot;  &gt;United Holy Church of America&lt;/option&gt; <br />
  &lt;option value=&quot;1033&quot;  &gt;United Methodist&lt;/option&gt; <br />
  &lt;option value=&quot;1034&quot;  &gt;Uniting (Australia, NZ, etc.)&lt;/option&gt; <br />
  &lt;option value=&quot;1035&quot;  &gt;Vineyard&lt;/option&gt; <br />
  &lt;option value=&quot;1036&quot;  &gt;Wesleyan&lt;/option&gt; <br />
</p>
<p> &lt;/select&gt;</p>
<p> &lt;/TD&gt;<br />
  &lt;td height=&quot;26&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;<br />
  Affiliations&lt;/TD&gt;<br />
  &lt;TD width=&quot;478&quot; valign=&quot;top&quot;&gt;<br />
  &lt;SELECT id=&quot;orgaffil1tid&quot; name=&quot;orgaffil1tid&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot; 
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  	&&
					(aszHostID.equalsIgnoreCase("volengabs")==false)  			
			){ %>
  onchange=&quot;javascript:document.getElementById('affiliation2').style.display='inline';&quot;<br />
			<% } %>
  &gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;</p>
<p>
  &lt;option value=&quot;11545&quot;
  <% if(aszHostID.equalsIgnoreCase("volengabs")){ out.print(" selected=selected "); } %>
  &gt;American Bible Society&lt;/option&gt; <br />
  &lt;option value=&quot;717&quot;
  <% if(aszHostID.equalsIgnoreCase("volengagrm")){ out.print(" selected=selected "); } %>
  &gt;Association of Gospel Rescue Missions (AGRM)&lt;/option&gt; <br />
  &lt;option value=&quot;1037&quot;  &gt;Billy Graham Evangelistic Association&lt;/option&gt; <br />
  &lt;option value=&quot;3850&quot;  &gt;Blazing Grace&lt;/option&gt; <br />
  &lt;option value=&quot;1038&quot;  &gt;Bread for the World&lt;/option&gt; <br />
  &lt;option value=&quot;1039&quot;  &gt;Call to Renewal&lt;/option&gt; <br />
  &lt;option value=&quot;1040&quot;  &gt;Campus Crusade for Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1041&quot;  &gt;Catholic Charities&lt;/option&gt; <br />
  &lt;option value=&quot;1042&quot;  &gt;Catholic Charities USA&lt;/option&gt; <br />
  &lt;option value=&quot;1043&quot;  &gt;Catholic Medical Mission Board&lt;/option&gt; <br />
  &lt;option value=&quot;1044&quot;  &gt;Catholic Relief Services&lt;/option&gt; <br />
  &lt;option value=&quot;6&quot;  &gt;Center for Youth Studies&lt;/option&gt; <br />
  &lt;option value=&quot;21109&quot;  &gt;Chalmers Center&lt;/option&gt; <br />
  &lt;option value=&quot;1045&quot;  &gt;Christian Aid Ministries&lt;/option&gt; <br />
  &lt;option value=&quot;1046&quot;  &gt;Christian Children's Fund&lt;/option&gt; <br />
  &lt;option value=&quot;4&quot;  &gt;Christian Community Development Association (CCDA)&lt;/option&gt; <br />
  &lt;option value=&quot;1047&quot;  &gt;Christian Legal Society&lt;/option&gt; <br />
  &lt;option value=&quot;1048&quot;  &gt;Christian Reformed Church in North America&lt;/option&gt; <br />
  &lt;option value=&quot;1049&quot;  &gt;Community Technology Center Network (CTCNet)&lt;/option&gt; <br />
  &lt;option value=&quot;1050&quot;  &gt;Compassion International&lt;/option&gt; <br />
  &lt;option value=&quot;1051&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1052&quot;  &gt;Devos&lt;/option&gt; <br />
  &lt;option value=&quot;1646&quot;  &gt;Emmanuel Gospel Center&lt;/option&gt; <br />
  &lt;option value=&quot;1053&quot;  &gt;Evangelical Association for the Promotion of Education&lt;/option&gt; <br />
  &lt;option value=&quot;1054&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1055&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;3040&quot;  &gt;Evangelicals for Social Action&lt;/option&gt; <br />
  &lt;option value=&quot;483&quot;  &gt;FaithInAction&lt;/option&gt; <br />
  &lt;option value=&quot;2652&quot;  &gt;FASTEN&lt;/option&gt; <br />
  &lt;option value=&quot;1057&quot;  &gt;Feed the Children&lt;/option&gt; <br />
  &lt;option value=&quot;1058&quot;  &gt;Focus on the Family&lt;/option&gt; <br />
  &lt;option value=&quot;1059&quot;  &gt;Food for the Poor&lt;/option&gt; <br />
  &lt;option value=&quot;7&quot;  &gt;Fuller's Center for Youth and Family Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;3849&quot;  &gt;Gospel Communications&lt;/option&gt; <br />
  &lt;option value=&quot;1060&quot;  &gt;Habitat for Humanity&lt;/option&gt; <br />
  &lt;option value=&quot;1061&quot;  &gt;Hands On Network&lt;/option&gt; <br />
  &lt;option value=&quot;1062&quot;  &gt;Here's Life Inner City&lt;/option&gt; <br />
  &lt;option value=&quot;1063&quot;  &gt;HUD Neighborhood Networks&lt;/option&gt; <br />
  &lt;option value=&quot;9&quot;  &gt;Kingdomworks&lt;/option&gt; <br />
  &lt;option value=&quot;1064&quot;  &gt;Leadership Foundations of America&lt;/option&gt; <br />
  &lt;option value=&quot;1065&quot;  &gt;Map International, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;1066&quot;  &gt;Mennonite Central Committee&lt;/option&gt; <br />
  &lt;option value=&quot;7093&quot;  &gt;Mission America Coalition&lt;/option&gt; <br />
  &lt;option value=&quot;1067&quot;  &gt;National Council of Churches of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1068&quot;  &gt;Reformed Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;8708&quot;  &gt;ReNew Partnerships&lt;/option&gt; <br />
  &lt;option value=&quot;1069&quot;
  <% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){ out.print(" selected=selected "); } %>  
  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1070&quot;  &gt;Samaritan's Purse&lt;/option&gt; <br />
  &lt;option value=&quot;7502&quot;  &gt;SixSeeds.org&lt;/option&gt; <br />
  &lt;option value=&quot;2402&quot;  &gt;Sojourners&lt;/option&gt; <br />
  &lt;option value=&quot;10&quot;  &gt;TechMission&lt;/option&gt; <br />
  &lt;option value=&quot;778&quot;  &gt;TechMission Member&lt;/option&gt; <br />
  &lt;option value=&quot;4234&quot;  &gt;TechSoup&lt;/option&gt; <br />
  &lt;option value=&quot;1071&quot;  &gt;The Christian and Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;4846&quot;  &gt;Urban Onramps&lt;/option&gt; <br />
  &lt;option value=&quot;5&quot;  &gt;Urban Youth Workers Institute (UYWI)&lt;/option&gt; <br />
  &lt;option value=&quot;715&quot;  &gt;UrbNet&lt;/option&gt; <br />
  &lt;option value=&quot;1073&quot;  &gt;Volunteers of America&lt;/option&gt; <br />
  &lt;option value=&quot;1074&quot;  &gt;World Church Service&lt;/option&gt; <br />
  &lt;option value=&quot;1075&quot;  &gt;World Vision&lt;/option&gt; <br />
  &lt;option value=&quot;1076&quot;  &gt;Wycliffe Bible Translators&lt;/option&gt; <br />
  &lt;option value=&quot;1077&quot;  &gt;YMCAs in the United States&lt;/option&gt; <br />
  &lt;option value=&quot;1078&quot;  &gt;Young Life&lt;/option&gt; <br />
  &lt;option value=&quot;8&quot;  &gt;YouthPartnersNet / CompassionWorks&lt;/option&gt; </p>
<p> &lt;/SELECT&gt;</p>
<p> &lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;<br />
  &lt;td&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;&lt;/td&gt;&lt;td&gt;<br />
  &lt;div id=&quot;affiliation2&quot; 
			<% // hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  	 && 
					(aszHostID.equalsIgnoreCase("volengabs")==false)  			
			){ %>
				style=&quot;display: none;&quot;
			<% } %>  
  &gt; </p>
<p> &lt;SELECT id=&quot;orgaffil2tid&quot; name=&quot;orgaffil2tid&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot; onchange=&quot;javascript:document.getElementById('affiliation3').style.display='inline';&quot;&gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;</p>
<p> &lt;option value=&quot;11545&quot;  &gt;American Bible Society&lt;/option&gt; <br />
  &lt;option value=&quot;717&quot;  &gt;Association of Gospel Rescue Missions (AGRM)&lt;/option&gt; <br />
  &lt;option value=&quot;1037&quot;  &gt;Billy Graham Evangelistic Association&lt;/option&gt; <br />
  &lt;option value=&quot;3850&quot;  &gt;Blazing Grace&lt;/option&gt; <br />
  &lt;option value=&quot;1038&quot;  &gt;Bread for the World&lt;/option&gt; <br />
  &lt;option value=&quot;1039&quot;  &gt;Call to Renewal&lt;/option&gt; <br />
  &lt;option value=&quot;1040&quot;  &gt;Campus Crusade for Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1041&quot;  &gt;Catholic Charities&lt;/option&gt; <br />
  &lt;option value=&quot;1042&quot;  &gt;Catholic Charities USA&lt;/option&gt; <br />
  &lt;option value=&quot;1043&quot;  &gt;Catholic Medical Mission Board&lt;/option&gt; <br />
  &lt;option value=&quot;1044&quot;  &gt;Catholic Relief Services&lt;/option&gt; <br />
  &lt;option value=&quot;6&quot;  &gt;Center for Youth Studies&lt;/option&gt; <br />
  &lt;option value=&quot;21109&quot;  &gt;Chalmers Center&lt;/option&gt; <br />
  &lt;option value=&quot;1045&quot;  &gt;Christian Aid Ministries&lt;/option&gt; <br />
  &lt;option value=&quot;1046&quot;  &gt;Christian Children's Fund&lt;/option&gt; <br />
  &lt;option value=&quot;4&quot;  &gt;Christian Community Development Association (CCDA)&lt;/option&gt; <br />
  &lt;option value=&quot;1047&quot;  &gt;Christian Legal Society&lt;/option&gt; <br />
  &lt;option value=&quot;1048&quot;  &gt;Christian Reformed Church in North America&lt;/option&gt; <br />
  &lt;option value=&quot;1049&quot;  &gt;Community Technology Center Network (CTCNet)&lt;/option&gt; <br />
  &lt;option value=&quot;1050&quot;  &gt;Compassion International&lt;/option&gt; <br />
  &lt;option value=&quot;1051&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1052&quot;  &gt;Devos&lt;/option&gt; <br />
  &lt;option value=&quot;1646&quot;  &gt;Emmanuel Gospel Center&lt;/option&gt; <br />
  &lt;option value=&quot;1053&quot;  &gt;Evangelical Association for the Promotion of Education&lt;/option&gt; <br />
  &lt;option value=&quot;1054&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1055&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;3040&quot;  &gt;Evangelicals for Social Action&lt;/option&gt; <br />
  &lt;option value=&quot;483&quot;  &gt;FaithInAction&lt;/option&gt; <br />
  &lt;option value=&quot;2652&quot;  &gt;FASTEN&lt;/option&gt; <br />
  &lt;option value=&quot;1057&quot;  &gt;Feed the Children&lt;/option&gt; <br />
  &lt;option value=&quot;1058&quot;  &gt;Focus on the Family&lt;/option&gt; <br />
  &lt;option value=&quot;1059&quot;  &gt;Food for the Poor&lt;/option&gt; <br />
  &lt;option value=&quot;7&quot;  &gt;Fuller's Center for Youth and Family Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;3849&quot;  &gt;Gospel Communications&lt;/option&gt; <br />
  &lt;option value=&quot;1060&quot;  &gt;Habitat for Humanity&lt;/option&gt; <br />
  &lt;option value=&quot;1061&quot;  &gt;Hands On Network&lt;/option&gt; <br />
  &lt;option value=&quot;1062&quot;  &gt;Here's Life Inner City&lt;/option&gt; <br />
  &lt;option value=&quot;1063&quot;  &gt;HUD Neighborhood Networks&lt;/option&gt; <br />
  &lt;option value=&quot;9&quot;  &gt;Kingdomworks&lt;/option&gt; <br />
  &lt;option value=&quot;1064&quot;  &gt;Leadership Foundations of America&lt;/option&gt; <br />
  &lt;option value=&quot;1065&quot;  &gt;Map International, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;1066&quot;  &gt;Mennonite Central Committee&lt;/option&gt; <br />
  &lt;option value=&quot;7093&quot;  &gt;Mission America Coalition&lt;/option&gt; <br />
  &lt;option value=&quot;1067&quot;  &gt;National Council of Churches of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1068&quot;  &gt;Reformed Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;8708&quot;  &gt;ReNew Partnerships&lt;/option&gt; <br />
  &lt;option value=&quot;1069&quot;  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1070&quot;  &gt;Samaritan's Purse&lt;/option&gt; <br />
  &lt;option value=&quot;7502&quot;  &gt;SixSeeds.org&lt;/option&gt; <br />
  &lt;option value=&quot;2402&quot;  &gt;Sojourners&lt;/option&gt; <br />
  &lt;option value=&quot;10&quot;  &gt;TechMission&lt;/option&gt; <br />
  &lt;option value=&quot;778&quot;  &gt;TechMission Member&lt;/option&gt; <br />
  &lt;option value=&quot;4234&quot;  &gt;TechSoup&lt;/option&gt; <br />
  &lt;option value=&quot;1071&quot;  &gt;The Christian and Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;4846&quot;  &gt;Urban Onramps&lt;/option&gt; <br />
  &lt;option value=&quot;5&quot;  &gt;Urban Youth Workers Institute (UYWI)&lt;/option&gt; <br />
  &lt;option value=&quot;715&quot;  &gt;UrbNet&lt;/option&gt; <br />
  &lt;option value=&quot;1073&quot;  &gt;Volunteers of America&lt;/option&gt; <br />
  &lt;option value=&quot;1074&quot;  &gt;World Church Service&lt;/option&gt; <br />
  &lt;option value=&quot;1075&quot;  &gt;World Vision&lt;/option&gt; <br />
  &lt;option value=&quot;1076&quot;  &gt;Wycliffe Bible Translators&lt;/option&gt; <br />
  &lt;option value=&quot;1077&quot;  &gt;YMCAs in the United States&lt;/option&gt; <br />
  &lt;option value=&quot;1078&quot;  &gt;Young Life&lt;/option&gt; <br />
  &lt;option value=&quot;8&quot;  &gt;YouthPartnersNet / CompassionWorks&lt;/option&gt; <br />
  <br />
  &lt;/SELECT&gt;</p>
<p> &lt;/div&gt;&lt;/td&gt;&lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;&lt;/td&gt;&lt;td&gt;<br />
  &lt;div id=&quot;affiliation3&quot; style=&quot;display: none;&quot;&gt;</p>
<p> &lt;SELECT id=&quot;orgaffil3tid&quot; name=&quot;orgaffil3tid&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot; onchange=&quot;javascript:document.getElementById('affiliation4').style.display='inline';&quot;&gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;11545&quot;  &gt;American Bible Society&lt;/option&gt; <br />
  &lt;option value=&quot;717&quot;  &gt;Association of Gospel Rescue Missions (AGRM)&lt;/option&gt; <br />
  &lt;option value=&quot;1037&quot;  &gt;Billy Graham Evangelistic Association&lt;/option&gt; <br />
  &lt;option value=&quot;3850&quot;  &gt;Blazing Grace&lt;/option&gt; <br />
  &lt;option value=&quot;1038&quot;  &gt;Bread for the World&lt;/option&gt; <br />
  &lt;option value=&quot;1039&quot;  &gt;Call to Renewal&lt;/option&gt; <br />
  &lt;option value=&quot;1040&quot;  &gt;Campus Crusade for Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1041&quot;  &gt;Catholic Charities&lt;/option&gt; <br />
  &lt;option value=&quot;1042&quot;  &gt;Catholic Charities USA&lt;/option&gt; <br />
  &lt;option value=&quot;1043&quot;  &gt;Catholic Medical Mission Board&lt;/option&gt; <br />
  &lt;option value=&quot;1044&quot;  &gt;Catholic Relief Services&lt;/option&gt; <br />
  &lt;option value=&quot;6&quot;  &gt;Center for Youth Studies&lt;/option&gt; <br />
  &lt;option value=&quot;21109&quot;  &gt;Chalmers Center&lt;/option&gt; <br />
  &lt;option value=&quot;1045&quot;  &gt;Christian Aid Ministries&lt;/option&gt; <br />
  &lt;option value=&quot;1046&quot;  &gt;Christian Children's Fund&lt;/option&gt; <br />
  &lt;option value=&quot;4&quot;  &gt;Christian Community Development Association (CCDA)&lt;/option&gt; <br />
  &lt;option value=&quot;1047&quot;  &gt;Christian Legal Society&lt;/option&gt; <br />
  &lt;option value=&quot;1048&quot;  &gt;Christian Reformed Church in North America&lt;/option&gt; <br />
  &lt;option value=&quot;1049&quot;  &gt;Community Technology Center Network (CTCNet)&lt;/option&gt; <br />
  &lt;option value=&quot;1050&quot;  &gt;Compassion International&lt;/option&gt; <br />
  &lt;option value=&quot;1051&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1052&quot;  &gt;Devos&lt;/option&gt; <br />
  &lt;option value=&quot;1646&quot;  &gt;Emmanuel Gospel Center&lt;/option&gt; <br />
  &lt;option value=&quot;1053&quot;  &gt;Evangelical Association for the Promotion of Education&lt;/option&gt; <br />
  &lt;option value=&quot;1054&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1055&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;3040&quot;  &gt;Evangelicals for Social Action&lt;/option&gt; <br />
  &lt;option value=&quot;483&quot;  &gt;FaithInAction&lt;/option&gt; <br />
  &lt;option value=&quot;2652&quot;  &gt;FASTEN&lt;/option&gt; <br />
  &lt;option value=&quot;1057&quot;  &gt;Feed the Children&lt;/option&gt; <br />
  &lt;option value=&quot;1058&quot;  &gt;Focus on the Family&lt;/option&gt; <br />
  &lt;option value=&quot;1059&quot;  &gt;Food for the Poor&lt;/option&gt; <br />
  &lt;option value=&quot;7&quot;  &gt;Fuller's Center for Youth and Family Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;3849&quot;  &gt;Gospel Communications&lt;/option&gt; <br />
  &lt;option value=&quot;1060&quot;  &gt;Habitat for Humanity&lt;/option&gt; <br />
  &lt;option value=&quot;1061&quot;  &gt;Hands On Network&lt;/option&gt; <br />
  &lt;option value=&quot;1062&quot;  &gt;Here's Life Inner City&lt;/option&gt; <br />
  &lt;option value=&quot;1063&quot;  &gt;HUD Neighborhood Networks&lt;/option&gt; <br />
  &lt;option value=&quot;9&quot;  &gt;Kingdomworks&lt;/option&gt; <br />
  &lt;option value=&quot;1064&quot;  &gt;Leadership Foundations of America&lt;/option&gt; <br />
  &lt;option value=&quot;1065&quot;  &gt;Map International, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;1066&quot;  &gt;Mennonite Central Committee&lt;/option&gt; <br />
  &lt;option value=&quot;7093&quot;  &gt;Mission America Coalition&lt;/option&gt; <br />
  &lt;option value=&quot;1067&quot;  &gt;National Council of Churches of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1068&quot;  &gt;Reformed Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;8708&quot;  &gt;ReNew Partnerships&lt;/option&gt; <br />
  &lt;option value=&quot;1069&quot;  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1070&quot;  &gt;Samaritan's Purse&lt;/option&gt; <br />
  &lt;option value=&quot;7502&quot;  &gt;SixSeeds.org&lt;/option&gt; <br />
  &lt;option value=&quot;2402&quot;  &gt;Sojourners&lt;/option&gt; <br />
  &lt;option value=&quot;10&quot;  &gt;TechMission&lt;/option&gt; <br />
  &lt;option value=&quot;778&quot;  &gt;TechMission Member&lt;/option&gt; <br />
  &lt;option value=&quot;4234&quot;  &gt;TechSoup&lt;/option&gt; <br />
  &lt;option value=&quot;1071&quot;  &gt;The Christian and Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;4846&quot;  &gt;Urban Onramps&lt;/option&gt; <br />
  &lt;option value=&quot;5&quot;  &gt;Urban Youth Workers Institute (UYWI)&lt;/option&gt; <br />
  &lt;option value=&quot;715&quot;  &gt;UrbNet&lt;/option&gt; <br />
  &lt;option value=&quot;1073&quot;  &gt;Volunteers of America&lt;/option&gt; <br />
  &lt;option value=&quot;1074&quot;  &gt;World Church Service&lt;/option&gt; <br />
  &lt;option value=&quot;1075&quot;  &gt;World Vision&lt;/option&gt; <br />
  &lt;option value=&quot;1076&quot;  &gt;Wycliffe Bible Translators&lt;/option&gt; <br />
  &lt;option value=&quot;1077&quot;  &gt;YMCAs in the United States&lt;/option&gt; <br />
  &lt;option value=&quot;1078&quot;  &gt;Young Life&lt;/option&gt; <br />
  &lt;option value=&quot;8&quot;  &gt;YouthPartnersNet / CompassionWorks&lt;/option&gt; <br />
  <br />
  &lt;/SELECT&gt;</p>
<p> &lt;/div&gt;&lt;/td&gt;&lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;&lt;/td&gt;&lt;td&gt;<br />
  &lt;div id=&quot;affiliation4&quot; style=&quot;display: none;&quot;&gt;</p>
<p> &lt;SELECT id=&quot;orgaffil4tid&quot; name=&quot;orgaffil4tid&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot; onchange=&quot;javascript:document.getElementById('affiliation5').style.display='inline';&quot;&gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;11545&quot;  &gt;American Bible Society&lt;/option&gt; <br />
  &lt;option value=&quot;717&quot;  &gt;Association of Gospel Rescue Missions (AGRM)&lt;/option&gt; <br />
  &lt;option value=&quot;1037&quot;  &gt;Billy Graham Evangelistic Association&lt;/option&gt; <br />
  &lt;option value=&quot;3850&quot;  &gt;Blazing Grace&lt;/option&gt; <br />
  &lt;option value=&quot;1038&quot;  &gt;Bread for the World&lt;/option&gt; <br />
  &lt;option value=&quot;1039&quot;  &gt;Call to Renewal&lt;/option&gt; <br />
  &lt;option value=&quot;1040&quot;  &gt;Campus Crusade for Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1041&quot;  &gt;Catholic Charities&lt;/option&gt; <br />
  &lt;option value=&quot;1042&quot;  &gt;Catholic Charities USA&lt;/option&gt; <br />
  &lt;option value=&quot;1043&quot;  &gt;Catholic Medical Mission Board&lt;/option&gt; <br />
  &lt;option value=&quot;1044&quot;  &gt;Catholic Relief Services&lt;/option&gt; <br />
  &lt;option value=&quot;6&quot;  &gt;Center for Youth Studies&lt;/option&gt; <br />
  &lt;option value=&quot;21109&quot;  &gt;Chalmers Center&lt;/option&gt; <br />
  &lt;option value=&quot;1045&quot;  &gt;Christian Aid Ministries&lt;/option&gt; <br />
  &lt;option value=&quot;1046&quot;  &gt;Christian Children's Fund&lt;/option&gt; <br />
  &lt;option value=&quot;4&quot;  &gt;Christian Community Development Association (CCDA)&lt;/option&gt; <br />
  &lt;option value=&quot;1047&quot;  &gt;Christian Legal Society&lt;/option&gt; <br />
  &lt;option value=&quot;1048&quot;  &gt;Christian Reformed Church in North America&lt;/option&gt; <br />
  &lt;option value=&quot;1049&quot;  &gt;Community Technology Center Network (CTCNet)&lt;/option&gt; <br />
  &lt;option value=&quot;1050&quot;  &gt;Compassion International&lt;/option&gt; <br />
  &lt;option value=&quot;1051&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1052&quot;  &gt;Devos&lt;/option&gt; <br />
  &lt;option value=&quot;1646&quot;  &gt;Emmanuel Gospel Center&lt;/option&gt; <br />
  &lt;option value=&quot;1053&quot;  &gt;Evangelical Association for the Promotion of Education&lt;/option&gt; <br />
  &lt;option value=&quot;1054&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1055&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;3040&quot;  &gt;Evangelicals for Social Action&lt;/option&gt; <br />
  &lt;option value=&quot;483&quot;  &gt;FaithInAction&lt;/option&gt; <br />
  &lt;option value=&quot;2652&quot;  &gt;FASTEN&lt;/option&gt; <br />
  &lt;option value=&quot;1057&quot;  &gt;Feed the Children&lt;/option&gt; <br />
  &lt;option value=&quot;1058&quot;  &gt;Focus on the Family&lt;/option&gt; <br />
  &lt;option value=&quot;1059&quot;  &gt;Food for the Poor&lt;/option&gt; <br />
  &lt;option value=&quot;7&quot;  &gt;Fuller's Center for Youth and Family Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;3849&quot;  &gt;Gospel Communications&lt;/option&gt; <br />
  &lt;option value=&quot;1060&quot;  &gt;Habitat for Humanity&lt;/option&gt; <br />
  &lt;option value=&quot;1061&quot;  &gt;Hands On Network&lt;/option&gt; <br />
  &lt;option value=&quot;1062&quot;  &gt;Here's Life Inner City&lt;/option&gt; <br />
  &lt;option value=&quot;1063&quot;  &gt;HUD Neighborhood Networks&lt;/option&gt; <br />
  &lt;option value=&quot;9&quot;  &gt;Kingdomworks&lt;/option&gt; <br />
  &lt;option value=&quot;1064&quot;  &gt;Leadership Foundations of America&lt;/option&gt; <br />
  &lt;option value=&quot;1065&quot;  &gt;Map International, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;1066&quot;  &gt;Mennonite Central Committee&lt;/option&gt; <br />
  &lt;option value=&quot;7093&quot;  &gt;Mission America Coalition&lt;/option&gt; <br />
  &lt;option value=&quot;1067&quot;  &gt;National Council of Churches of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1068&quot;  &gt;Reformed Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;8708&quot;  &gt;ReNew Partnerships&lt;/option&gt; <br />
  &lt;option value=&quot;1069&quot;  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1070&quot;  &gt;Samaritan's Purse&lt;/option&gt; <br />
  &lt;option value=&quot;7502&quot;  &gt;SixSeeds.org&lt;/option&gt; <br />
  &lt;option value=&quot;2402&quot;  &gt;Sojourners&lt;/option&gt; <br />
  &lt;option value=&quot;10&quot;  &gt;TechMission&lt;/option&gt; <br />
  &lt;option value=&quot;778&quot;  &gt;TechMission Member&lt;/option&gt; <br />
  &lt;option value=&quot;4234&quot;  &gt;TechSoup&lt;/option&gt; <br />
  &lt;option value=&quot;1071&quot;  &gt;The Christian and Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;4846&quot;  &gt;Urban Onramps&lt;/option&gt; <br />
  &lt;option value=&quot;5&quot;  &gt;Urban Youth Workers Institute (UYWI)&lt;/option&gt; <br />
  &lt;option value=&quot;715&quot;  &gt;UrbNet&lt;/option&gt; <br />
  &lt;option value=&quot;1073&quot;  &gt;Volunteers of America&lt;/option&gt; <br />
  &lt;option value=&quot;1074&quot;  &gt;World Church Service&lt;/option&gt; <br />
  &lt;option value=&quot;1075&quot;  &gt;World Vision&lt;/option&gt; <br />
  &lt;option value=&quot;1076&quot;  &gt;Wycliffe Bible Translators&lt;/option&gt; <br />
  &lt;option value=&quot;1077&quot;  &gt;YMCAs in the United States&lt;/option&gt; <br />
  &lt;option value=&quot;1078&quot;  &gt;Young Life&lt;/option&gt; <br />
  &lt;option value=&quot;8&quot;  &gt;YouthPartnersNet / CompassionWorks&lt;/option&gt; <br />
  <br />
  &lt;/SELECT&gt;</p>
<p> &lt;/div&gt;&lt;/td&gt;&lt;/tr&gt;<br />
  &lt;tr&gt;<br />
  &lt;TD&gt;&lt;/td&gt;&lt;td&gt;<br />
  &lt;div id=&quot;affiliation5&quot; style=&quot;display: none;&quot;&gt;</p>
<p> &lt;SELECT id=&quot;orgaffil5tid&quot; name=&quot;orgaffil5tid&quot; class=&quot;smalldropdown&quot; style=&quot;margin-top: 5px;&quot;  &gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;11545&quot;  &gt;American Bible Society&lt;/option&gt; <br />
  &lt;option value=&quot;717&quot;  &gt;Association of Gospel Rescue Missions (AGRM)&lt;/option&gt; <br />
  &lt;option value=&quot;1037&quot;  &gt;Billy Graham Evangelistic Association&lt;/option&gt; <br />
  &lt;option value=&quot;3850&quot;  &gt;Blazing Grace&lt;/option&gt; <br />
  &lt;option value=&quot;1038&quot;  &gt;Bread for the World&lt;/option&gt; <br />
  &lt;option value=&quot;1039&quot;  &gt;Call to Renewal&lt;/option&gt; <br />
  &lt;option value=&quot;1040&quot;  &gt;Campus Crusade for Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1041&quot;  &gt;Catholic Charities&lt;/option&gt; <br />
  &lt;option value=&quot;1042&quot;  &gt;Catholic Charities USA&lt;/option&gt; <br />
  &lt;option value=&quot;1043&quot;  &gt;Catholic Medical Mission Board&lt;/option&gt; <br />
  &lt;option value=&quot;1044&quot;  &gt;Catholic Relief Services&lt;/option&gt; <br />
  &lt;option value=&quot;6&quot;  &gt;Center for Youth Studies&lt;/option&gt; <br />
  &lt;option value=&quot;21109&quot;  &gt;Chalmers Center&lt;/option&gt; <br />
  &lt;option value=&quot;1045&quot;  &gt;Christian Aid Ministries&lt;/option&gt; <br />
  &lt;option value=&quot;1046&quot;  &gt;Christian Children's Fund&lt;/option&gt; <br />
  &lt;option value=&quot;4&quot;  &gt;Christian Community Development Association (CCDA)&lt;/option&gt; <br />
  &lt;option value=&quot;1047&quot;  &gt;Christian Legal Society&lt;/option&gt; <br />
  &lt;option value=&quot;1048&quot;  &gt;Christian Reformed Church in North America&lt;/option&gt; <br />
  &lt;option value=&quot;1049&quot;  &gt;Community Technology Center Network (CTCNet)&lt;/option&gt; <br />
  &lt;option value=&quot;1050&quot;  &gt;Compassion International&lt;/option&gt; <br />
  &lt;option value=&quot;1051&quot;  &gt;Cooperative Baptist Fellowship&lt;/option&gt; <br />
  &lt;option value=&quot;1052&quot;  &gt;Devos&lt;/option&gt; <br />
  &lt;option value=&quot;1646&quot;  &gt;Emmanuel Gospel Center&lt;/option&gt; <br />
  &lt;option value=&quot;1053&quot;  &gt;Evangelical Association for the Promotion of Education&lt;/option&gt; <br />
  &lt;option value=&quot;1054&quot;  &gt;Evangelical Covenant Church&lt;/option&gt; <br />
  &lt;option value=&quot;1055&quot;  &gt;Evangelical Lutheran Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;3040&quot;  &gt;Evangelicals for Social Action&lt;/option&gt; <br />
  &lt;option value=&quot;483&quot;  &gt;FaithInAction&lt;/option&gt; <br />
  &lt;option value=&quot;2652&quot;  &gt;FASTEN&lt;/option&gt; <br />
  &lt;option value=&quot;1057&quot;  &gt;Feed the Children&lt;/option&gt; <br />
  &lt;option value=&quot;1058&quot;  &gt;Focus on the Family&lt;/option&gt; <br />
  &lt;option value=&quot;1059&quot;  &gt;Food for the Poor&lt;/option&gt; <br />
  &lt;option value=&quot;7&quot;  &gt;Fuller's Center for Youth and Family Ministry&lt;/option&gt; <br />
  &lt;option value=&quot;3849&quot;  &gt;Gospel Communications&lt;/option&gt; <br />
  &lt;option value=&quot;1060&quot;  &gt;Habitat for Humanity&lt;/option&gt; <br />
  &lt;option value=&quot;1061&quot;  &gt;Hands On Network&lt;/option&gt; <br />
  &lt;option value=&quot;1062&quot;  &gt;Here's Life Inner City&lt;/option&gt; <br />
  &lt;option value=&quot;1063&quot;  &gt;HUD Neighborhood Networks&lt;/option&gt; <br />
  &lt;option value=&quot;9&quot;  &gt;Kingdomworks&lt;/option&gt; <br />
  &lt;option value=&quot;1064&quot;  &gt;Leadership Foundations of America&lt;/option&gt; <br />
  &lt;option value=&quot;1065&quot;  &gt;Map International, Inc.&lt;/option&gt; <br />
  &lt;option value=&quot;1066&quot;  &gt;Mennonite Central Committee&lt;/option&gt; <br />
  &lt;option value=&quot;7093&quot;  &gt;Mission America Coalition&lt;/option&gt; <br />
  &lt;option value=&quot;1067&quot;  &gt;National Council of Churches of Christ&lt;/option&gt; <br />
  &lt;option value=&quot;1068&quot;  &gt;Reformed Church in America&lt;/option&gt; <br />
  &lt;option value=&quot;8708&quot;  &gt;ReNew Partnerships&lt;/option&gt; <br />
  &lt;option value=&quot;1069&quot;  &gt;Salvation Army&lt;/option&gt; <br />
  &lt;option value=&quot;1070&quot;  &gt;Samaritan's Purse&lt;/option&gt; <br />
  &lt;option value=&quot;7502&quot;  &gt;SixSeeds.org&lt;/option&gt; <br />
  &lt;option value=&quot;2402&quot;  &gt;Sojourners&lt;/option&gt; <br />
  &lt;option value=&quot;10&quot;  &gt;TechMission&lt;/option&gt; <br />
  &lt;option value=&quot;778&quot;  &gt;TechMission Member&lt;/option&gt; <br />
  &lt;option value=&quot;4234&quot;  &gt;TechSoup&lt;/option&gt; <br />
  &lt;option value=&quot;1071&quot;  &gt;The Christian and Missionary Alliance&lt;/option&gt; <br />
  &lt;option value=&quot;4846&quot;  &gt;Urban Onramps&lt;/option&gt; <br />
  &lt;option value=&quot;5&quot;  &gt;Urban Youth Workers Institute (UYWI)&lt;/option&gt; <br />
  &lt;option value=&quot;715&quot;  &gt;UrbNet&lt;/option&gt; <br />
  &lt;option value=&quot;1073&quot;  &gt;Volunteers of America&lt;/option&gt; <br />
  &lt;option value=&quot;1074&quot;  &gt;World Church Service&lt;/option&gt; <br />
  &lt;option value=&quot;1075&quot;  &gt;World Vision&lt;/option&gt; <br />
  &lt;option value=&quot;1076&quot;  &gt;Wycliffe Bible Translators&lt;/option&gt; <br />
  &lt;option value=&quot;1077&quot;  &gt;YMCAs in the United States&lt;/option&gt; <br />
  &lt;option value=&quot;1078&quot;  &gt;Young Life&lt;/option&gt; <br />
  &lt;option value=&quot;8&quot;  &gt;YouthPartnersNet / CompassionWorks&lt;/option&gt; <br />
  <br />
  &lt;/SELECT&gt;</p>
<p> &lt;/div&gt;&lt;/td&gt;&lt;/tr&gt;<br />
  &lt;tr&gt;&lt;td colspan=4&gt;<br />
  &lt;div style=&quot;display:none;&quot; &gt;<br />
  &lt;table border=&quot;0&quot; cellpadding=&quot;0&quot; cellspacing=&quot;0&quot; &gt;<br />
  <br />
  &lt;tr&gt;&lt;TD width=110  height=&quot;30&quot;&gt;<br />
  Local Affiliation:&lt;/td&gt;&lt;td&gt;<br />
  &lt;select id=&quot;localaffil&quot; name=&quot;localaffil&quot; class=&quot;smalldropdown&quot;&gt;<br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;</p>
<p> &lt;option value=&quot;BMA&quot;  &gt;BMA&lt;/option&gt; <br />
  &lt;option value=&quot;COPAHNI&quot;  &gt;COPAHNI&lt;/option&gt; <br />
  &lt;option value=&quot;EGC&quot;  &gt;EGC&lt;/option&gt; <br />
  &lt;option value=&quot;Ten Point&quot;  &gt;Ten Point&lt;/option&gt; <br />
  &lt;option value=&quot;Vineyard&quot;  &gt;Vineyard&lt;/option&gt; </p>
<p> &lt;/select&gt;&lt;/td&gt;&lt;td height=45&gt;&lt;/td&gt;&lt;/tr&gt;<br />
  &lt;/table&gt;&lt;/div&gt; <br />
  &lt;/td&gt;&lt;/tr&gt;<br />
</p>
<p> </p>
<p> <br />
    <br />
  &lt;tr&gt;&lt;td&gt;Sort results by: &lt;/td&gt;&lt;td&gt;</p>
<p> &lt;div id=&quot;sortstm&quot; style=&quot;display:inline;&quot;&gt;<br />
  &lt;SELECT id=&quot;searchkey1&quot; name=&quot;searchkey1&quot; class=&quot;smalldropdown&quot; &gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;distance&quot;&gt;Distance&lt;/option&gt;<br />
  &lt;option value=&quot;organization&quot;&gt;Organization Name&lt;/option&gt;<br />
  &lt;option value=&quot;opportunity&quot;&gt;Opportunity Title&lt;/option&gt;<br />
  &lt;option value=&quot;city&quot;&gt;City&lt;/option&gt;</p>
<p> &lt;option value=&quot;state&quot;&gt;State (US &amp; Canada)&lt;/option&gt;<br />
  &lt;option value=&quot;prov&quot;&gt;Province (outside US &amp; Canada)&lt;/option&gt;<br />
  &lt;option value=&quot;country&quot;&gt;Country&lt;/option&gt;</p>
<p> &lt;option value=&quot;denomaffil&quot;&gt;Denominational Affiliation&lt;/option&gt;<br />
  &lt;option value=&quot;affil&quot;&gt;Affiliation&lt;/option&gt;</p>
<p> &lt;option value=&quot;stmdur&quot;&gt;Duration (Short-term Missions Only)&lt;/option&gt;<br />
  &lt;option value=&quot;stmcost&quot;&gt;Cost (Short-term Missions Only)&lt;/option&gt;</p>
<p> &lt;option value=&quot;updatedt&quot;&gt;Date Last Updated&lt;/option&gt;<br />
  &lt;option value=&quot;numvolorg&quot;&gt;Number of Volunteers / Organization (Annually)&lt;/option&gt;<br />
  &lt;option value=&quot;oppnumvol&quot;&gt;Number of Volunteers / Opportunity  (Annually)&lt;/option&gt;<br />
  &lt;/SELECT&gt;</p>
<p> &lt;/div&gt;<br />
  &lt;div id=&quot;sortopps&quot; style=&quot;display:none;&quot;&gt;<br />
  &lt;SELECT id=&quot;searchkey2&quot; name=&quot;searchkey2&quot; class=&quot;smalldropdown&quot; &gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;<br />
  &lt;option value=&quot;distance&quot;&gt;Distance&lt;/option&gt;<br />
  &lt;option value=&quot;organization&quot;&gt;Organization Name&lt;/option&gt;<br />
  &lt;option value=&quot;opportunity&quot;&gt;Opportunity Title&lt;/option&gt;<br />
  &lt;option value=&quot;city&quot;&gt;City&lt;/option&gt;</p>
<p> &lt;option value=&quot;state&quot;&gt;State (US &amp; Canada)&lt;/option&gt;<br />
  &lt;option value=&quot;prov&quot;&gt;Province (outside US &amp; Canada)&lt;/option&gt;<br />
  &lt;option value=&quot;country&quot;&gt;Country&lt;/option&gt;</p>
<p> &lt;option value=&quot;denomaffil&quot;&gt;Denominational Affiliation&lt;/option&gt;<br />
  &lt;option value=&quot;affil&quot;&gt;Affiliation&lt;/option&gt;</p>
<p> &lt;option value=&quot;updatedt&quot;&gt;Date Last Updated&lt;/option&gt;<br />
  &lt;option value=&quot;numvolorg&quot;&gt;Number of Volunteers / Organization (Annually)&lt;/option&gt;<br />
  &lt;option value=&quot;oppnumvol&quot;&gt;Number of Volunteers / Opportunity  (Annually)&lt;/option&gt;<br />
  &lt;/SELECT&gt;<br />
  &lt;/div&gt;<br />
  &lt;div id=&quot;sortorgs&quot; style=&quot;display:none;&quot;&gt;<br />
  &lt;SELECT id=&quot;searchkey3&quot; name=&quot;searchkey3&quot; class=&quot;smalldropdown&quot; &gt; <br />
  &lt;option value=&quot;&quot;&gt;&lt;/option&gt;</p>
<p> &lt;option value=&quot;distance&quot;&gt;Distance&lt;/option&gt;<br />
  &lt;option value=&quot;organization&quot;&gt;Organization Name&lt;/option&gt;<br />
  &lt;option value=&quot;city&quot;&gt;City&lt;/option&gt;<br />
  &lt;option value=&quot;state&quot;&gt;State (US &amp; Canada)&lt;/option&gt;<br />
  &lt;option value=&quot;prov&quot;&gt;Province (outside US &amp; Canada)&lt;/option&gt;</p>
<p> &lt;option value=&quot;country&quot;&gt;Country&lt;/option&gt;</p>
<p> &lt;option value=&quot;denomaffil&quot;&gt;Denominational Affiliation&lt;/option&gt;<br />
  &lt;option value=&quot;affil&quot;&gt;Affiliation&lt;/option&gt;</p>
<p> &lt;option value=&quot;updatedt&quot;&gt;Date Last Updated&lt;/option&gt;<br />
  &lt;option value=&quot;numvolorg&quot;&gt;Number of Volunteers / Organization (Annually)&lt;/option&gt;</p>
<p> &lt;/SELECT&gt;<br />
  &lt;/div&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;<br />
  &lt;table&gt;<br />
  &lt;tr&gt;<br />
  &lt;td width=&quot;105&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;TD colspan=&quot;2&quot;&gt;<br />
  <br />
  &lt;input type=&quot;submit&quot; name=&quot;Submit&quot; value=&quot;Submit&quot;&gt;<br />
  &lt;/TD&gt;<br />
  &lt;td width=&quot;4&quot;&gt;&lt;/td&gt;</p>
<p> &lt;td height=&quot;45&quot; width=&quot;28&quot;&gt;&amp;nbsp;&lt;/td&gt;<br />
  &lt;/tr&gt;<br />
  &lt;/table&gt;<br />
  &lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;<br />
  &lt;/form&gt;</p>
<p><br>
  &nbsp;
  </p>
</p>
</div>		
		

    </div>


    
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->