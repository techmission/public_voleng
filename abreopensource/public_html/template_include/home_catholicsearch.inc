<%
// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;
String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 	aszBothTID = "" + iBoth;

int iTemp=0;
%>


<script language="javascript">

function topopp_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='inline';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('opp').className = "active";
document.getElementById('org').className = "";
document.getElementById('stm').className = "";
}

function toporg_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('opp').className = "";
document.getElementById('org').className = "active";
document.getElementById('stm').className = "";
}

function topstm_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stm').className = "active";
}

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

</script>


<style>

a#opp, a#org, a#stm, a#cont {
cursor:pointer; 
text-decoration:none; 
color:#000000; 
font-size:1em;
//font-size:12px; 
font-weight: normal;
<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
background:transparent url(/imgs/tabs-off2.gif) no-repeat;  
<% } else { %>
background:transparent url(/imgs/tabs-off.png) no-repeat;  
<% } %>
border-right: 1px solid #5e9048; 
padding:3px 5px 0;
font-weight: bold;
color:#456D34;
margin-right:3px;
//margin:0;
}
a#opp.active, a#org.active, a#stm.active, a#cont.active {
<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
background:transparent url(http://www.christianvolunteering.org/imgs/tabs-on2.gif) no-repeat scroll 0 0;
border-right:1px solid #9f9481;
color:#FFFFFF;
font-weight:bold;
<% } else { %>
background:transparent url(/imgs/tabs-on.png) no-repeat;  
<% } %>
border-right: 1px solid #456D34;
font-weight: bold;
color:#FFF;
}
h5.benefits {margin: 0; font-size:12px; color:#000;white-space:nowrap;}
select#country {width: 150px;}
.cvtrnsearch_inside{ margin: 0px 0px 5px 150px; width: auto;}
.first_column_orgsearch {width:143px; padding-right:5px; text-align:right; font-weight:bold; display:inline-block; white-space:nowrap; float:left;}
form div.form_row {
  clear: left;
  display: block;
  zoom: 1;
  margin:0;
  padding: 2px 3px;
}
</style>


	<div style="float:left; margin: 0px 0px 5px 10px; width: 485px;">


	<h2>Catholic Volunteer Opportunities</h2>

	<div id ="vol-opp" style="display:inline"><a id="opp" class="active" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities </a> </div>
	
<div id="organizations" style="display:inline"><a id="org"onClick="toporg_search()" name="method" value="processOrgSearchAll"> Organizations </a> </div>
	
	<div id="short-term" style="display:inline"><a id="stm" onClick="topstm_search()" name="method" value="processOppSearchAll"> Volunteer Internships </a> </div>

<hr size="2" color="#456D34" width="100%" style="margin-top:0; //margin-top:-8px;" />


<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvsearch" style="display:inline">



	<div style="float:center; margin: 0px 0px 5px 20px; width: 465px;">


	<div id="cvoppsearch" style="display:inline">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" value="catholic_oppsrch" name="formname" id="formname"/>

<table style="font-size:12px;" border="0"><tr><td align="right">
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
</td></tr><tr><td align="right">

<strong>Country</strong></td><td colspan="2">
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
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postalopp').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postalopp').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr><tr><td align="right">
<div id="postallabelopp"><strong>Postal Code</strong></div>
      </td><td><div id="postalopp"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
</td><td align="right">
<strong>&nbsp;&nbsp;Search Only Catholic Opportunities</strong><input type="checkbox" styleClass="check" value="Catholic" id="affiliation" name="affiliation" "checked">
</td>
</tr><tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
</div><!-- end cvoppsearch -->





<div id="cvorgsearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="partner" value="Salvation Army">
<% } %>
<input type="hidden" value="catholic_orgsrch" name="formname" id="formname"/>

<table style="font-size:12px;" border="0"><tr><td align="right">
<strong>Program Type</strong>
</td><td colspan="2">
<SELECT id="programtypetid" name="programtypetid" size="1" class="smalldropdown" style="width:210px; //width:auto;" > 

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
</td></tr><tr><td align="right">
<strong>Region</strong>
</td><td colspan="2">
		<SELECT id="regiontid" name="regiontid" class="smalldropdown" style="width: 140px;"> 
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
              </SELECT></td></tr><tr><td colspan=3>
</td></tr><tr><td align="right">

<strong>Country</strong></td><td colspan="2">
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
					out.print( "onClick=\"javascript:document.getElementById('postallabelorg').style.display='inline';javascript:document.getElementById('postalorg').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelorg').style.display='none';javascript:document.getElementById('postalorg').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr>
<tr><td align="right">
<div id="postallabelorg"><strong>Postal Code</strong></div>
      </td><td><div id="postalorg"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
</td><td align="right">
<strong>&nbsp;&nbsp;Search Only Catholic Organizations</strong><input type="checkbox" styleClass="check" value="992" id="denomaffiltid" name="denomaffiltid" "checked">
</td>
</tr>
<tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
<b><a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse organizations</a></b>
</div><!-- end cvorgsearch -->







<% if(aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")){ // show stm first for shorttermmissions page%>
	<div id="cvstmsearch" style="display:inline">
<% } else { %>
	<div id="cvstmsearch" style="display:none">
<% } %>

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<input type="hidden" value="catholic_stm" name="formname" id="formname"/>

<table style="font-size:12px;" border="0"><tr><td align="right">
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
</td></tr><tr><td align="right">
<strong>Region</strong>
</td><td colspan="2">
		<SELECT id="regiontid" name="regiontid" class="smalldropdown" style="width: 140px;"> 
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
              </SELECT></td></tr><tr><td align="right">

<strong>Country</strong></td><td colspan="2">
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
					//out.print(" selected=selected ");
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postalopp').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postalopp').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr><tr><td align="right">
<div id="postallabelstm"><strong>Postal Code</strong></div>
      </td><td><div id="postalstm"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
</td><td align="right">
<strong>&nbsp;&nbsp;&nbsp;&nbsp;Search Only Catholic Opportunities </strong><input type="checkbox" styleClass="check" value="992" id="denomaffiltid" name="denomaffiltid" "checked">
</td>
</tr><tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
</div><!-- end cvstmsearch -->






<b><a href="<%=aszPortal%>/advancedsearch.jsp" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>
<!-- Developer:  you can configure your widget's values via Javascript.  Learn more at http://docs.widgetbox.com/developers/viral-installer/ -->
            <form id="christianvolunteering_search_GetWidgetForm" style="display: none;">
                <input type="hidden" name="appInstance.instanceName" value="" /> <input type="hidden" name="appInstance.width" value="" /> <input type="hidden" name="appInstance.height" value="" /> <input type="hidden" name="new_setting" value="" />
            </form>
            <script src='http://www.widgetbox.com/get_widget_btn.jsp?sourceId=3fccaa85-b4d1-4ea0-b410-61c30c03e2db&ns=christianvolunteering_search'></script>











</div>
<div class="cleardiv"></div>
</div>


</div>
