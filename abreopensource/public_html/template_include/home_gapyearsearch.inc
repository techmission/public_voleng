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
document.getElementById('searchumcontent').style.display='none';
document.getElementById('cont').style.backgroundColor = "";
document.getElementById('opp').style.backgroundColor = "#EEEEEE";
document.getElementById('org').style.backgroundColor = "";
document.getElementById('stm').style.backgroundColor = "";
}

function toporg_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';
document.getElementById('cont').style.backgroundColor = "";
document.getElementById('opp').style.backgroundColor = "";
document.getElementById('org').style.backgroundColor = "#EEEEEE";
document.getElementById('stm').style.backgroundColor = "";
}

function topstm_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('searchumcontent').style.display='none';
document.getElementById('cont').style.backgroundColor = "";
document.getElementById('opp').style.backgroundColor = "";
document.getElementById('org').style.backgroundColor = "";
document.getElementById('stm').style.backgroundColor = "#EEEEEE";
}

function umcont_search()
{
document.getElementById('cvsearch').style.display='none';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='inline';
document.getElementById('cont').style.backgroundColor = "#EEEEEE";
document.getElementById('opp').style.backgroundColor = "";
document.getElementById('org').style.backgroundColor = "";
document.getElementById('stm').style.backgroundColor = "";
}

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

</script>

<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" )) ){ %>
	<div style="float:left; margin: 0px 0px 0px 10px; width: 285px;">
<% } else if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:left; margin: 10px 0px 5px 10px; width: 485px;">
<% } else { %>
	<div style="float:left; margin: 10px 0px 5px 10px; width: 300px;">
<% } %>


<% if (aszHostID.equalsIgnoreCase( "volengcatholic" )){ %>
	<h2>Catholic Volunteering</h2>
<% } else if (aszHostID.equalsIgnoreCase( "volengchicago" )){ %>
	<h2>Chicago Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )) { %>
	<h2>Denver Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )) { %>
	<h2>Indianapolis Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )) { %>
	<h2>Los Angeles Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )) { %>
	<h2>Miami Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )) { %>
	<h2>New England Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )) { %>
	<h2>New York Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengseattle" )) { %>
	<h2>Seattle Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" )) { %>
	<h2>Twin Cities Christian Volunteering</h2>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
	<table><tr><td>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/search.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) 
){ %>
	<table height=40 border=0 padding=0><tr><td>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/search.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else { %>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/search.jsp"><h2>search</h2></A>
<% } %>

	
	
	<div id="short-term" style="display:inline"><a id="stm" style="background: #eeeeee; cursor:pointer; text-decoration:none; color:#000000; font-size:12px; font-weight: normal" onClick="topstm_search()" name="method" value="processOppSearchAll"> Gap Year Internships </a> |</div> 
	
	<div id="vol-opp" style="display:inline"><a id="opp" style="cursor:pointer; text-decoration:none; color:#000000; font-size:12px; font-weight: normal" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities </a> |</div>
	
	<div id="organizations" style="display:inline"><a id="org" style="cursor:pointer; text-decoration:none; color:#000000; font-size:12px; font-weight: normal" onClick="toporg_search()" name="method" value="processOrgSearchAll"> Organizations </a> |</div>

<div id="content" style="display:inline"><a id="cont" style="cursor:pointer; text-decoration:none; color:#000000; font-size:12px; font-weight: normal" onClick="umcont_search()" name="method" value="allsearch"> Content </a></div> 

<br/><br/>


<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvsearch" style="display:inline">



<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 20px; width: 465px;">
<% } else { %>
	<div>
<% } %>


	<div id="cvoppsearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="1069">
<% } %>
<input type="hidden" value="gapyrOppsrch" name="formname" id="formname"/>

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
<strong>&nbsp;&nbsp;&nbsp;&nbsp;Search Opportunities for Groups</strong><input type="checkbox" id="greatforgroup" name="greatforgroup" value="<%=aszGroupTID%>"
<% if(aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) { 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> /></td>
</tr><tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
<br/>
<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
</div><!-- end cvoppsearch -->





<div id="cvorgsearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="1069">
<% } %>
<input type="hidden" value="gapyrOrgsrch" name="formname" id="formname"/>

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
	<SELECT id="region" name="region" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		for(int index=0; index < aRegionList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
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
<tr>
<td align="right"><div id="postallabelorg"><strong>Postal Code</strong></div></td>
<td colspan="2"><div id="postalorg"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div></td>
</tr>
<tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
<b><a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse organizations</a></b>
</div><!-- end cvorgsearch -->







	<div id="cvstmsearch" style="display:inline">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<input type="hidden" value="gapyrSTMsrch" name="formname" id="formname"/>

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
	<SELECT id="region" name="region" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		for(int index=0; index < aRegionList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
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
<strong>&nbsp;&nbsp;&nbsp;&nbsp;Search Opportunities for Groups</strong><input type="checkbox" id="greatforgroup" name="greatforgroup" value="<%=aszGroupTID%>"
<% if(aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) { 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> /></td>
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



<!-- urbanministry search -->

<div id="searchumcontent" style="display:none">



<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 20px; width: 350px;">
<% } else { %>
	<div>
<% } %>



Search thousands of articles, sermons and videos<br/>on volunteering, urban ministry, and social justice:
<br/><br/>

<!--begin Google Custom Search -->
<form name="search-form" class="search-form" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form">

<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
<input type="text" maxlength="128" class="textinputwhite" style="width:220px;" name="query" id="edit-query"  value="Enter Keyword" onClick="clearText(this)" />
<input type="submit" name="op" id="edit-sa" value="Search" />
<input type="hidden" name="form_token" id="edit-google-cse-results-searchbox-form-form-token" value="f1596786f0691ac334237131d60e5654"  />
<input type="hidden" name="form_id" id="edit-google-cse-results-searchbox-form" value="google_cse_results_searchbox_form"  />
</form>
<div id="search_results"><noscript><a href="http://www.google.com/cse?q=&cx=017405804136166701815:nc9jskbtk8y&cof=FORID:0">View the results at Google</a>, or enable JavaScript to view them here.</noscript></div>
    <script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script>      <!-- end Google Custom Search-->
	
<br/><a href="http://www.urbanministry.org/faceted_search">advanced search</a>&nbsp;|&nbsp;<a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions">browse articles</a>

<br/><br/>Powered by <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

</form>



</div>


</div>


</div>
