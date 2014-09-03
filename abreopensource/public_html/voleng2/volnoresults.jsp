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
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
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

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">volunteer opportunities</span>
	<%//@ include file="/template_include/navigation_search_bar.inc" %>
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
		<span style="float: left;">volunteer opportunities</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">
			home</a> &gt; <a href="<%=aszPortal%>/oppsrch.do?method=showSearch">
			volunteer</a> &gt; opportunities search results  </div>
	</div>
<% } %>


<div id="body">
 	<center><h3>  
 	  <br>
 	  <fieldset style="width: 508px; height: 350px">
 	  <br><br>
      <i>We're sorry, there are no results for your search.</i>
      <br><br><hr width=75%><br>
      Please try another search below:
      <br><br>

			<div style="display:inline;" id="cvoppsrch">
	
<form action="<%=aszPortal%>/oppsrch.do" method="get" name="homesearch">
<input type="hidden" value="processOppSearchAll" name="method"/>
<input type="hidden" value="City" name="distance"/>
<input type="hidden" value="" name="voltype"/>
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>


<table style="font-size: 12px; margin:0;">
<tbody><tr>
<td align="right" style="width:90px;">
<div id="postallabeloppLocal"><strong>Postal Code</strong></div>
      </td><td style="width: 80px;"><div id="postaloppLocal"><input type="text" size="20" style="width: 60px;" id="postalcode" name="postalcode"/></div>
</td>
</tr>
<tr><td align="right">
<strong>Service Area</strong>
</td><td colspan="2">
				<SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" > 
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
</td></tr>
<tr><td align="right">
<strong>Skills</strong>
</td><td colspan="2">
        <select id="skills1id" name="skills1id" class="smalldropdown" >
	<option value="" selected></option>
			<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4748 ||
							iSubType == 4749){
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
			%>

				</SELECT>
</td></tr>
<tr><td align="right">

<strong id="countrylabelopp">Country</strong></td><td colspan="2">
         
	<SELECT id="country" name="country" size="1" style="z-index: 0;" class="smalldropdown">
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
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postaloppLocal').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postaloppLocal').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT>		 
		 </td></tr>
         
		 <tr><td align="right"><strong>I am a</strong></td> 
         <td align="left" colspan="2">
			<input type="checkbox" value="<%=aszGroupTID%>" name="greatforgroup" id="greatforgroup" />
            &nbsp;Group 
            <input type="checkbox" value="<%=aszFamilyTID%>" name="greatforfamily" id="greatforfamily"/>
        	&nbsp;Family
            <input type="checkbox" value="<%=aszKidTID%>" name="greatforkid" id="greatforkid"/>
        	&nbsp;Kid
            <input type="checkbox" value="<%=aszTeenTID%>" name="greatforteen" id="greatforteen"/>
            &nbsp;Teen 
            <input type="checkbox" value="<%=aszSeniorTID%>" name="greatforsenior" id="greatforsenior"/>
            &nbsp;Senior 

</td>
</tr>
<tr><td/><td colspan="2">
<input type="checkbox" value="<%=aszYesWorkStudyTID%>" name="workstudy" id="workstudy" />Search for Work Study Volunteer Opportunities
<br/><input type="checkbox" styleClass="check" value="4795" id="virt_vol" name="postype" onclick="toggle(this, 'country', 'postalcode')" />Search <span style="color: rgb(47, 85, 131); cursor: help;" title="Volunteers who share their skills remotely" onmouseover="this.style.cursor="help";">Virtual</span> Volunteer Opportunities
</td></tr>
		 <tr><td align="right">
</td><td>
<input type="submit" style="width: 100px; height: 25px; font-weight: bold;" value="Search" id="submit" name="imageField"/>
</td><td align="left"/></tr></tbody></table>
</form>
<br/>

</div>
      
      
      
      
      
      <br>
      </fieldset><br><br>
    </h3>
    </center>
</div>

</div>

<!-- start sidebar information -->

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
