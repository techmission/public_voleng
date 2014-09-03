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


// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidOrgAffil=5
int iABSTID=11545;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;
String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 	aszBothTID = "" + iBoth;
String aszABSTID = "" + iABSTID;

int iTemp=0;
%>

<script language="javascript">

function topopp_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='inline';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';

document.getElementById('opp').className = "active";
document.getElementById('org').className = "";
document.getElementById('stmiss').className = "";
}

function toporg_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsrch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';

document.getElementById('opp').className = "";
document.getElementById('org').className= "active";
document.getElementById('stmiss').className = "";
}

function topstm_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';

document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stmiss').className = "active";
}

function umcont_search()
{
document.getElementById('cvsearch').style.display='none';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';

document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stmiss').className = "";
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

function showPostalOpp(aszCtr) {
    if ($(aszCtr)==('us')) {
        $('#postalopp :input').attr('disabled', false);
    } else {
        $('#postalopp :input').attr('disabled', true);
    }   
}
function showPostalOrg(aszCtr) {
    if ($(aszCtr)==('us')) {
        $('#postalorg :input').removeAttr('disabled');
    } else {
        $('#postalorg :input').attr('disabled', true);
    }   
}
function showPostalStm(aszCtr) {
    if ($(aszCtr)==('us')) {
        $('#postalstm :input').removeAttr('disabled');
    } else {
        $('#postalstm :input').attr('disabled', true);
    }   
}

function selected_disabled(object, item1){
	var inputvalue=object.value;
	if(inputvalue=="us")
		document.getElementById(item1).disabled = false;
	else
		document.getElementById(item1).disabled = true;
}

</script>

<style>
a#opp, a#org, a#stmiss, a#cont {
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
//margin:0;
}
a#opp.active, a#org.active, a#stmiss.active, a#cont.active {
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

        <div style="float:left; margin: 0px 0px 5px 5px; width: 440px;">


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
	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) 
){ %>
	<table height=40 border=0 padding=0><tr><td>
	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="<%=request.getContextPath()%>/login.jsp">login</a></b></td></tr></table>
<% } else if(
                (aszHostID.equalsIgnoreCase( "volengworldvision")) 
			//||  (aszHostID.equalsIgnoreCase( "volengabs")) 
){ %>
        <table height=40 border=0 padding=0><tr><td>
        <A style="text-decoration: none" HREF="<%=request.getContextPath()%>/advancedsearch.jsp"><h2>Find a Place to Volunteer</h2></A></td>
        <td width="100"></td><td>
        <b><a href="<%=request.getContextPath()%>/login.jsp">login</a></b></td></tr></table>
<% } else { %>
        <A style="text-decoration: none" HREF="<%=request.getContextPath()%>/advancedsearch.jsp"><h2>Find a Place to Volunteer</h2></A>
<% } %>


<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) || (aszHostID.equalsIgnoreCase( "volengroundtrip" )) || (aszHostID.equalsIgnoreCase( "volengagrm" ))){ // show stm first for shorttermmissions page%>
	
	
	<div id="short-term" style="display:inline">
	<a id="stmiss" class="active" onClick="topstm_search()" name="method" value="processOppSearchAll">Short Term <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>Service<% }else{ %>Missions<% } %></a>
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
	<a id="stmiss" onClick="topstm_search()" name="method" value="processOppSearchAll">Short-term <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>Service<% }else{ %>Missions<% } %></a>
	</div>
<% } %>

<!-- <div id="content" style="display:inline">
<a id="cont" onClick="umcont_search()" name="method" value="allsearch"> Content </a> 
</div> --> 

<hr size="2" color="#456D34" width="100%" style="margin-top:0; //margin-top:-8px;" />


<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvsearch" style="display:inline">



<% 
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
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
                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
                (aszHostID.equalsIgnoreCase( "volengagrm" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 10px;">
<% } else { %> 
	<div>
<%  } %>


<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) || (aszHostID.equalsIgnoreCase( "volengroundtrip" )) || (aszHostID.equalsIgnoreCase( "volengagrm" ))){ // show stm first for shorttermmissions page%>
	<div id="cvoppsearch" style="display:none">
<% } else { %>
	<div id="cvoppsearch" style="display:inline">
<% } %>

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
<input type="hidden" value="homeMinifiedOppsrch" name="formname" id="formname"/>

<table style="font-size:12px;" border="0">
 <tr>
         <td align="right">
<div id="postallabelopp"><strong>Postal Code</strong></div>
      </td><td style="width:80px;"><div id="postalopp"><input name="postalcode" type="text" id="postalcodeopp" style="width:60px;" size="20" /></div>
</td></span>


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
	<SELECT id="country" name="country" onchange="selected_disabled( this, 'postalcodeopp')" size="1" style="z-index: 0;" class="smalldropdown">
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
				} else {
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT>
         </td>
         </tr>
		 <tr><td align="right">
</td><td>
<% if (
	(aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
        (aszHostID.equalsIgnoreCase( "volengleadingoutreach" )) 
 ){ %>
<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold; float:right;"/></a>
<% }else{ %>
<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold; float:right;"/></a>
<% } %>
</td><td align="left"><input type="checkbox" styleClass="check" value="4795" id="virt_vol" name="postype" onclick="toggle(this, 'country', 'postalcode')" /><strong>Search Virtual Opportunities</strong><br/>
<input type="checkbox" styleClass="check"  value="<%=aszGroupTID%>" name="greatforgroup"  id="greatforgroup"  
<% if((aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) || (aszHostID.equalsIgnoreCase("volengchurch")) ){ 
					// make box selected on church volunteer page %>
checked="checked"
<% } %> /><strong>Search Group Opportunities</strong>

<% if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<br /><input type="checkbox" styleClass="check"  name="orgaffil1tid" id="orgaffil1tid" value="<%=aszABSTID%>" checked="checked"><strong>Search American Bible Society<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Opportunities</strong>
<% } %>

		 
        

</td></tr></table>
</form>
<br/>
<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
</div><!-- end cvoppsearch -->




<!--====================================== Organization Search ====================================================-->
<div id="cvorgsrch" style="display:none;">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
<input type="hidden" value="homeMinifiedOrgsrch" name="formname" id="formname"/>

<div class="form_row">
<div class="first_column_orgsearch">Organization Name</div>
<input type="text" name="orgname" id="orgname" size="35" maxlength="200" styleClass="textinputwhite"/>
</div><!-- end: form_row-->

<div class="form_row">

<div class="first_column_orgsearch">
<div id="postallabelorg">Postal Code &amp; Country</div>
</div>
<div id="postalorg" style="float:left; margin-right:5px;"><input name="postalcode" type="text" id="postalcodeorg" style="width:60px;" size="20" /></div>
	<SELECT id="country" name="country" onchange="selected_disabled( this, 'postalcodeorg' )" size="1" style="z-index: 0; width:140px; //width:150px; float:left;" class="smalldropdown">
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
				} else {
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
      </SELECT>
 </div><!-- end: customised css div-->     

<div class="form_row">
<div class="first_column_orgsearch">Program Type</div>
<SELECT id="programtypetid" name="programtypetid" size="1" class="smalldropdown" style="width:210px;" > 

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
 </div><!-- end: form_row--> 
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

<div class="form_row">
<div class="first_column_orgsearch">Denominational Affiliation</div>
<select id="denomaffiltid" name="denomaffiltid" class="smalldropdown" style="width:210px;" >
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
 </div><!-- end: form_row--> 
 
 
 
 
 <div class="form_row">
 <div class="first_column_orgsearch" style="">Affiliations</div>
			<SELECT id="orgaffil1tid" name="orgaffil1tid" class="smalldropdown" style="width:210px;" 
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;

			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengabs")==false)  	&&		
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
			} else if(aszHostID.equalsIgnoreCase("volengabs")){
				aszTemp="American Bible Society";			
				iTemp=11545;			
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
</div><!-- end: form_row--> 
<% } %>


<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold; float:right;"/></a>
</form>
<b><a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">browse organizations</a></b>
</div><!-- end cvorgsrch -->
<div style="clear:both;"></div>
<!--- =======================================End Organization Search================================================-->







<% if( (aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")) || (aszHostID.equalsIgnoreCase( "volengroundtrip" )) || (aszHostID.equalsIgnoreCase( "volengagrm" ))){ // show stm first for shorttermmissions page%>
	<div id="cvstmsearch" style="display:inline">
<% } else { %>
	<div id="cvstmsearch" style="display:none">
<% } %>

<!-- ============================= Short Term Missions Search===========================================-->

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="4796">
<input type="hidden" name="requesttype" value="urlalias">
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
<input type="hidden" value="homeMinifiedSTMsrch" name="formname" id="formname"/>

<table style="font-size:12px;" border="0">

<tr>
<td width="98" align="right"><div id="postallabelstm"><strong>Postal Code</strong></div></td>
<td width="152"><div id="postalstm" style="display: inline;">
<input name="postalcode" type="text" id="postalcodestm" style="width:60px;" size="20" /></div>
</td>
<td width="147"><input id="roomboard" name="roomboard" type="checkbox" value="<%=aszRoomBoardTID%>" /> Room &amp; Board</td>
</tr>
<tr>
<td align="right">
<strong>Service Area</strong>
</td>
<td>			  <SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" style="width:140px;" > 
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
<td><input id="stipend" name="stipend" type="checkbox" value="<%=aszStipendTID%>" /> Stipend</td>
</tr>
<tr><td align="right">
<strong>Region</strong>
</td><td>
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
              </SELECT></td>
         <td><input id="medinsur" name="medinsur" type="checkbox" value="<%=aszMedInsurTID%>" /> Medical Insurance</td>

         </tr>
         
      <tr>
      <td align="right" style="white-space:nowrap;">
	  <strong>Length of Trip</strong></td>
      <td>
	  <select id="duration" name="duration" size="1" style="z-index: 0; width:140px;" class="smalldropdown">
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
	</td>
<td><input id="transport" name="transport" type="checkbox" value="<%=aszTransportTID%>" /> Transportation</td>

</tr><tr><td align="right">

<strong>Country</strong></td>
<td><SELECT id="country" name="country" onchange="selected_disabled( this, 'postalcodestm' )" size="1" style="z-index: 0; width:140px;" class="smalldropdown">
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
				} else {
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td>
         
         <td><input id="americorps" name="americorps" type="checkbox" value="<%=aszAmeriCorpsTID%>" /> AmeriCorps</td>

         </tr>


               
			</div>
            
		</div>
	</div>

</table>
<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold; float:right;"/>
</form>
<br/>
<b><a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="browse Christian short term urban missions, social service ministry internship volunteer opportunities">Browse Short Term Missions page</a></b>

	</div><!-- end cvstmsearch -->
    
<!--- ======================================= End Short Term Missions Search ====================================================-->










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
                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
                (aszHostID.equalsIgnoreCase( "volengagrm" )) ||
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
<input type="hidden" name="requesttype" value="urlalias">
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
<div style="clear:both;"></div>
