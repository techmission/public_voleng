<!-- /roundtrip/template/home_jointsearch_customized.inc  -->

<%
boolean bAssoc=false;
if(aszPortalType.equals("natlassoc")) bAssoc=true;
int iTemp=0;
%>

<%//@ include file="/template/topjspnologin1.inc" %>

<%
/**
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aServiceList, 161 );
aCodes.getAppCodeList( aProgramList, 172 );
aCodes.getAppCodeList( afiliationList, 163 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getAppCodeList( apartnersList, 167 );
aCodes.getAppCodeList( askillsList, 169 );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

**/



// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;
String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;

%>

<script language="javascript">

function topopp_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='inline';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
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
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "";
document.getElementById('org').className= "active";
document.getElementById('stm').className = "";
}

function topstm_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stm').className = "active";
}

function umcont_search()
{
document.getElementById('cvsearch').style.display='none';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='inline';

document.getElementById('cont').className = "active";
document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stm').className = "";
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

<style>

a#opp, a#org, a#stm, a#cont {
cursor:pointer; 
text-decoration:none; 
color:#000000; 
font-size:10px;
//font-size:12px; 
font-weight: normal;
background:transparent url(../imgs/tabs-off1_beige.png) no-repeat; 
border-right: 2px solid #B7884E; 
padding:3px 5px 0;
font-weight: bold;
color:#B7884E;
}
a#opp.active, a#org.active, a#stm.active, a#cont.active {
background:#B8966C url(../imgs/tabs-on1_beige.png) no-repeat; 
border-right: 2px solid  #66332B;
font-weight: bold;
color:#FFF;
}
</style>

<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" )) ){ %>
	<div style="float:left; margin: 0px 0px 0px 10px; width: 285px;">
<% } else if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
                (aszHostID.equalsIgnoreCase( "volengchurch" )) ||
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
                (aszHostID.equalsIgnoreCase( "volengleadingoutreach" )) ||
                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:left; margin: 0px 0px 5px 10px; width: 485px;">
<% } else { %>
	<div style="float:left; margin: 0px 0px 5px 10px; width: 300px;">
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
		(aszHostID.equalsIgnoreCase( "volengccda" )) 
		//||
		//(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
	<table><tr><td>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) 
){ %>
	<table height=40 border=0 padding=0><tr><td>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else { %>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>search</h2></A>
<% } %>


<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) || 
	(aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
){ // show stm first for shorttermmissions page%>
	
	
	<div id="short-term" style="display:inline">
	<a id="stm" class="active" onClick="topstm_search()" name="method" value="processOppSearchAll">Short-term Missions</a>
	</div> 
	
	<div id="vol-opp" style="display:inline">
	<a id="opp" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities</a>
	</div>
	
	<div id="organizations" style="display:inline">
	<a id="org" onClick="toporg_search()" name="method" value="processOrgSearchAll">Organizations</a>
	</div>

<% } else { %>
	<div id ="vol-opp" style="display:inline">
	<a id="opp" class="active" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities</a>
	</div>
	
<div id="organizations" style="display:inline">
<a id="org" onClick="toporg_search()" name="method" value="processOrgSearchAll">Organizations</a>
</div>
	
	<div id="short-term" style="display:inline">
	<a id="stm" onClick="topstm_search()" name="method" value="processOppSearchAll">Short-term Missions</a>
	</div>
<% } %>

<div id="content" style="display:inline">
<a id="cont" onClick="umcont_search()" name="method" value="allsearch"> Content </a> 
</div> 

<hr size="2" color="#66332B" width="100%" style=" margin-top: 0px;" />


<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvsearch" style="display:inline">



<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
                (aszHostID.equalsIgnoreCase( "volengchurch" )) ||
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
                (aszHostID.equalsIgnoreCase( "volengleadingoutreach" )) ||
                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 20px; width: 465px;">
<% } else { %>
	<div>
<% } %>


<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) || 
	(aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
){ // show stm first for shorttermmissions page%>
	<div id="cvoppsearch" style="display:none">
<% } else { %>
	<div id="cvoppsearch" style="display:inline">
<% } %>

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" value="feeds" name="source" id="source"/>
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<input type="hidden" name="requesttype" value="urlalias">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="partner" value="Salvation Army">
<% } %>

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
							}else if (iSubType == 4788){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Visitation/Friendship</option> ");
							}else if (iSubType == 4763){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Children and Youth</option> ");
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
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postalopp').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postalopp').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr>
		 <tr><td align="right">
<div id="postallabelopp"><strong>Postal Code</strong></div>
      </td><td style="width:80px;"><div id="postalopp"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
</td></span><td align="left"><input type="checkbox" styleClass="check" value="<%=aszGroupTID%>" id="greatforgroup" name="greatforgroup" 
<% if((aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) || (aszHostID.equalsIgnoreCase("volengchurch")) ){ 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> /><strong>Search Opportunities for Groups</strong></td>
</tr>
		 <tr><td align="right">
</td><td>
<% if (
        (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
	(aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
) { %>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_search-gray.gif" alt="Search" width="59" height="24" border="0" /></a>
<% }else{ %>
<input type="submit" style="width: 80px; height: 25px; font-weight: bold; float: right;" value="Search" id="submit" name="imageField">
<% } %>
</td><td align="left"><input type="checkbox" styleClass="check" value="4795" id="virt_vol" name="postype" onclick="toggle(this, 'country', 'postalcode')" /><strong>Search Virtual Volunteer Opportunities</strong></td></tr></table>
</form>
<br/>
<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
</div><!-- end cvoppsearch -->





<div id="cvorgsearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">
<input type="hidden" name="requesttype" value="urlalias">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="partner" value="Salvation Army">
<% } %>

<table style="font-size:12px;" border="0"><tr><td align="right">
<strong>Program Type</strong>
</td><td colspan="2">
	<SELECT id="programtype" name="programtype" size="1" style="z-index: 0;" class="smalldropdown">
		<option value="" selected="selected"></option> 
		<%
		for(int index=0; index < aProgramList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
			out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
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
</td></tr>
<tr>
<td align="right"><div id="postallabelorg"><strong>Postal Code</strong></div></td>
<td colspan="2"><div id="postalorg"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div></td>
</tr>
<tr><td align="right">

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
</td><td>
<% if (
        (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
) { %>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_search-gray.gif" alt="Search" width="59" height="24" border="0" /></a>
<% }else{ %>
<input type="submit" style="width: 80px; height: 25px; font-weight: bold; float: right;" value="Search" id="submit" name="imageField">
<% } %>
</td></tr></table>
</form>
<b><a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse organizations</a></b>
</div><!-- end cvorgsearch -->







<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) ||
        (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
){ // show stm first for shorttermmissions page
%>
	<div id="cvstmsearch" style="display:inline">
<% } else { %>
	<div id="cvstmsearch" style="display:none">
<% } %>

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" value="feeds" name="source" id="source"/>
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<input type="hidden" name="requesttype" value="urlalias">

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
							}else if (iSubType == 4788){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Visitation/Friendship</option> ");
							}else if (iSubType == 4763){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Children and Youth</option> ");
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
<div id="postallabelstm"><strong>Postal Code</strong></div>
      </td><td width="100%"><div id="postalstm" style="display: inline;"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
	  <div style="display: inline; margin-left: 10px;">
	  <strong>Length of Trip</strong>
	  <select id="duration" name="duration" size="1" style="z-index: 0;" class="smalldropdown">
	  <option value=""></option>
	  <option value="Less than a week">Less than a week</option>
	  <option value="1 to 2 weeks">1 to 2 weeks</option>
	  <option value="3 to 4 weeks">3 to 4 weeks</option>
	  <option value="1 to 2 months">1 to 2 months</option>
	  <option value="summer">Summer</option>
	  <option value="3 to 5 months">3 to 5 months</option>
	  <option value="one year">1 year</option>
	  <option value="1 to 2 years">1 to 2 years</option>
	  </select>
	  </div>
</td>
</tr><tr><td align="right">

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
         </SELECT></td></tr><tr><td></td><td>
<% if (
        (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" ))
) { %>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_search-gray.gif" alt="Search" width="59" height="24" border="0" /></a>
<% }else{ %>
<input type="submit" style="width: 80px; height: 25px; font-weight: bold; float: right;" value="Search" id="submit" name="imageField">
<% } %>
<div style="margin-top: -20px; margin-left: 60px;">&nbsp;<input type="checkbox" styleClass="check" value="<%=aszGroupTID%>" id="greatforgroup" name="greatforgroup" 
<% if((aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) || (aszHostID.equalsIgnoreCase("volengchurch")) ){ 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> />for Groups <input type="checkbox" styleClass="check" value="8106" id="roomboard" name="roomboard" />with Room &amp; Board  <input type="checkbox" styleClass="check" value="8108" id="stipend" name="stipend" />with Stipend </span></div></td></tr></table>
</form>
<b><a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="browse Christian short term urban missions, social service ministry internship volunteer opportunities" style="float: right;">browse opportunities</a></b>
</div><!-- end cvstmsearch -->






<b><a href="<%=aszPortal%>/advancedsearch.jsp" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>










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
                (aszHostID.equalsIgnoreCase( "volengleadingoutreach" )) ||
                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
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
<!--end:/roundtrip/template/home_jointsearch_customized.inc  -->
