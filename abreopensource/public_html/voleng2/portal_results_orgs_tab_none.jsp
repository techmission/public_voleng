<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information --><head>
<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Organization Search Results</title>

<% } else { %>
<% } %>

<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded" />

<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>


<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-ui-1.8.4.custom.min.js"></script>

<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.base.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.button.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.dialog.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.theme.css" rel="stylesheet" />

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/jquery-1.4.2.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.button.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.effects.core.js"></script>

<style>
.number {
	float: left;
	margin-right:15px;
}
.dialog-form { 
	height: 159; 
}
#modalDialog { visibility: hidden; position: absolute; left: 0px; top: 0px; width:100%; height:100%; text-align:center; z-index: 1000; background: repeat-x scroll 50% 50% #AAAAAA; opacity: 0.3;}
#modalDialogInner  { width:300px; margin: 100px 300px; background-color: #fff; border:1px solid #000; padding:15px; text-align:center; visibility: hidden; position: absolute; text-align:center; z-index: 1000; background: #fff; opacity: 1.0;
}
</style>

</head>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<bean:define id="orgportal" name="orgportal" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>


<!-- BEGIN MAINCONTENT -->

<div id="maincontent">


<%
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszOrgNamePrint = "";
String aszProgramTypePrint = "";
String aszOppTitlePrint = "";
int iPopularity=0;
String aszPopularity="";
String aszPopularityImg="";

int tidSpiritualDevelopment = 5239;

int iCounter=0;
int i=0;
int[] a_iOrgFavoriteOrgs=orgportal.getORGFavoritedORGNIDsArray();
int length=a_iOrgFavoriteOrgs.length;

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}

%>

<div id="body">

<img style="float:right; padding-top:10px" border="0" src="http://www.christianvolunteering.org/imgs/next-step_button.png" alt="next step" onClick="next_step()"/>
<br clear="all"/>


<table><tr><td width=120></td><td>

<form name="searchForm" action="<%=aszPortal%>/oppsrch.do" method="get" focus="postalcode" >
<input type="hidden" name="method" value="processOrgSearchAll" />

<%
String aszPosType=""+searchinfo.getOPPPositionTypeTID();
String aszProgramType=""+searchinfo.getProgramTypeTID();
String aszDenomAffil=""+searchinfo.getDenomAffilTID();
String aszOrgAffil1TID=""+searchinfo.getOrgAffil1TID();
String aszOrgAffil2TID=""+searchinfo.getOrgAffil2TID();
String aszOrgAffil3TID=""+searchinfo.getOrgAffil3TID();
String aszOrgAffil4TID=""+searchinfo.getOrgAffil4TID();
String aszOrgAffil5TID=""+searchinfo.getOrgAffil5TID();
%>

<input type="hidden" name="postype" value="<%=aszPosType%>" />
<input type="hidden" name="duration" value="<%=searchinfo.getDuration()%>" />
<input type="hidden" name="postalcode" value="<%=searchinfo.getPostalCode()%>" />
<input type="hidden" name="distance" value="<%=searchinfo.getDistance()%>" />
<input type="hidden" name="programtypetid" value="<%=aszProgramType%>" />
<input type="hidden" name="orgname" value="<%=searchinfo.getOrgName()%>" />
<input type="hidden" name="city" value="<%=searchinfo.getCity()%>" />
<input type="hidden" name="state" value="<%=searchinfo.getState()%>" />
<input type="hidden" name="prov" value="<%=searchinfo.getOthrProv()%>" />
<input type="hidden" name="country" value="<%=searchinfo.getCountry()%>" />
<input type="hidden" name="region" value="<%=searchinfo.getRegion()%>" />
<input type="hidden" name="affiliationtid" value="<%=aszDenomAffil%>" />
<input type="hidden" name="orgaffil1tid" value="<%=aszOrgAffil1TID%>" />
<input type="hidden" name="orgaffil2tid" value="<%=aszOrgAffil2TID%>" />
<input type="hidden" name="orgaffil3tid" value="<%=aszOrgAffil3TID%>" />
<input type="hidden" name="orgaffil4tid" value="<%=aszOrgAffil4TID%>" />
<input type="hidden" name="orgaffil5tid" value="<%=aszOrgAffil5TID%>" />
<input type="hidden" name="requesttype" value="<%=searchinfo.getSearchRequestType()%>" />

Sort results alphabetically by: 
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value=""> -- Select Sort -- </option>
        <option value="organization">Organization Name</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
<% } %>
        <option value="updatedt">Last Updated</option>
        <option value="">Number of Volunteers / Organization</option>
    </SELECT> <input type="submit" name="Submit" value="Go">

</form>

</td></tr>

<tr class="selectBoxRow">
<td>
<input type="checkbox" class="form-checkbox" value="1" name="toggleSelect" id="toggleSelect" onclick="toggleCheckAllOrgTab(this)">
Select All
</td>
<td colspan=2><input type="button" name="modalDialog" value="Add to My <%=aszOrgOrChurch%> Listings" onClick="showModalDialog()"></td>
</tr>
</table>

<br clear="all">

<div id="modalDialog">  </div>
<div id="modalDialogInner"> 
Would you like to add all the opportunities belonging to these Organizations/Churches to your listings as well?
<br>
<input type="button" name="questionYes" value="Yes" onClick="submitModalDialogYes()">
<input type="button" name="questionNo" value="No" onClick="submitModalDialogNo()">
</div>


<!-- form to submit to "favorite" orgs; gets inputs set via javascript with the checkbox values -->
<form name="orgForm" id="orgForm" action="<%=aszPortal%>/org.do?method=portalFavoriteOrgsList" method="post">
<input type="hidden" name="method" value="portalFavoriteOrgsList" >
<input type="hidden" name="favoritechildopps" id="favoritechildopps"  >
		<logic:notEmpty name="alist" >
			<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
<%
// separate out categories for output and re-word categories for no-faith
String	aszProgramTypes = org.getORGProgramTypes();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszProgramTypes=aszProgramTypes.replaceAll("Spiritual Development Program","");
	aszProgramTypes=aszProgramTypes.replaceAll("Church Outreach","");
}
aszProgramTypes=aszProgramTypes.replaceAll("^,","");
aszProgramTypes=aszProgramTypes.replaceAll(",$","");
aszProgramTypes=aszProgramTypes.replaceAll(",(?=[^()]*)", ", ");

	String aszCity= org.getORGAddrCity();
	String aszTemp1= org.getORGAddrCountryName();
	String aszTemp2 = org.getORGAddrStateprov();
	String aszLocation = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszLocation=aAppCode.getCSPStateName();
					}else {
						aszLocation=aAppCode.getCSPStateName();
					}
				}
			}
		}
	} else {
	if(null != aCountryList){
		for(int index=0; index < aCountryList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
			if(null == aAppCode) continue;
			String aszOptRqCode = aAppCode.getCTRIso();
			if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
				if(aszCity.equalsIgnoreCase( "" ) ) {
					aszLocation=aAppCode.getCTRPrintableName();
				}else {
					aszLocation=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszLocation = ", " + aszLocation;
}
aszLocation = org.getORGAddrCity() + aszLocation;
aszOrgNamePrint = org.getORGOrgName();


//popularity
aszPopularity=""; aszPopularityImg="";
iPopularity = org.getORGPopularity();
if(iPopularity > 110){
	aszPopularityImg="star-5.gif";
	aszPopularity="&#9733;&#9733;&#9733;&#9733;&#9733;";
}else if(iPopularity > 80){
	aszPopularityImg="star-4.gif";
	aszPopularity="&#9733;&#9733;&#9733;&#9733;&#9734;";
}else if(iPopularity > 50){
	aszPopularityImg="star-3.gif";
	aszPopularity="&#9733;&#9733;&#9733;&#9734;&#9734;";
}else if(iPopularity > 25){
	aszPopularityImg="star-2.gif";
	aszPopularity="&#9733;&#9733;&#9734;&#9734;&#9734;";
}else if(iPopularity > 0){
	aszPopularityImg="star-1.gif";
	aszPopularity="&#9733;&#9734;&#9734;&#9734;&#9734;";
}else{
	aszPopularityImg="star-0.gif";
	aszPopularity="&#9734;&#9734;&#9734;&#9734;&#9734;";
}
int iLastSpace = org.getORGMissStmntTeaser().lastIndexOf(" ");
String aszMemberClass="";
if(org.getORGMember()>0){
	aszMemberClass="class=\"member\"";
}
String	aszMemberType = org.getORGMembertype();
aszMemberType=aszMemberType.replaceAll("^,","");
aszMemberType=aszMemberType.replaceAll(",$","");
aszMemberType=aszMemberType.replaceAll(",(?=[^()]*)", ", ");

String	aszOrgAffiliation = org.getORGAffiliation();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
//	aszOrgAffiliation=aszOrgAffiliation.replaceAll("Missions","");
}
aszOrgAffiliation=aszOrgAffiliation.replaceAll("^,","");
aszOrgAffiliation=aszOrgAffiliation.replaceAll(",$","");
aszOrgAffiliation=aszOrgAffiliation.replaceAll(",(?=[^()]*)", ", ");

String	aszDenomination = org.getORGDenomAffil();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
//	aszDenomination=aszDenomination.replaceAll("Missions","");
}
aszDenomination=aszDenomination.replaceAll("^,","");
aszDenomination=aszDenomination.replaceAll(",$","");
aszDenomination=aszDenomination.replaceAll(",(?=[^()]*)", ", ");

String aszAffiliations = "";
if(aszOrgAffiliation.length() > 1 && aszDenomination.length() > 1){
	aszAffiliations = "<br><b>Affiliations:</b> "+ aszOrgAffiliation + ", " + aszDenomination;
}else if(aszOrgAffiliation.length() > 1){
	aszAffiliations = "<br><b>Affiliations:</b> "+ aszOrgAffiliation;
}else if(aszDenomination.length() > 1){
	aszAffiliations = "<br><b>Affiliations:</b> "+ aszDenomination;
}

long lTime = org.getORGUpdateDtNum();
String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (lTime*1000));

String aszORGUrlAlias=org.getORGUrlAlias() + ".jsp";

out.println("<!--<br>-------------------------------------------------------------------------------------------------------------<br>-->");

String aszTeaser = org.getORGMissStmntTeaser();
if(aszTeaser.length() > 50){
	aszTeaser = "<div class=\"teaser\">"+org.getORGMissStmntTeaser()+
		"...<A class=\"more_link\" href=\""+aszPortal+aszORGUrlAlias+"\">more ></A></div>";
}
%>

<div id="search-results">
			<div <%=aszMemberClass%>>
<%iCounter++;%>
<div class="number">
<% 
int test=0;
for(i=0; i<length; i++){
	if(a_iOrgFavoriteOrgs[i]==org.getORGNID()){
		test++;
%>
<input type="hidden" name="favoritedorgnids" id="favoritedorgnids" value="<% out.print(""+a_iOrgFavoriteOrgs[i]); %>" checked>
<% }}
if(test==0){ %>
<input type="checkbox" name="orgnids" id="orgnids<%=""+org.getORGNID()%>" value="<%=""+org.getORGNID()%>" >
<% }else{ %>
<input type="checkbox" name="orgnids" id="orgnids<%=""+org.getORGNID()%>" value="<%=""+org.getORGNID()%>" checked>
<% }  %>
</div>
<div class="listing_info" >
<%=iCounter%>.&nbsp;

<A class="opp_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
<!--div class="teaser"-->
<%=aszTeaser%>
<!--/div-->
<div class="opp-info">
<b>Program Type:</b>&nbsp;<%=aszProgramTypes%>
<!-- <%=aszAffiliations%> 
<br> -->&nbsp;&nbsp;
<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
<b>Date Last Updated:</b>&nbsp;<%out.print(date);%>
</div>
</div>
</div>
<hr  clear="all"/>
</div>
			</logic:iterate>
		</logic:notEmpty>
<div id="submitbutton" style="display:none;">
<input type="submit" name="submit" id="submitOrgForm" value="submit">
</div>
</form>
<input type="button" name="modalDialog" value="Add to My <%=aszOrgOrChurch%> Listings" onClick="showModalDialog()">
		
<script type="text/javascript">
function showModalDialog() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
}
function submitModalDialogYes() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 

	document.getElementById("favoritechildopps").value='true';
			submitForm();
}
function submitModalDialogNo() { 
	el = document.getElementById("modalDialog"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 
	el = document.getElementById("modalDialogInner"); 
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible"; 

	document.getElementById("favoritechildopps").value='false';
			submitForm();
}

function submitForm(){
	document.forms["orgForm"].submit();	
}
</script>
		
</div></div>
