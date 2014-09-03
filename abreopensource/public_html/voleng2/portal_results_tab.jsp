<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/misc/jquery.js"></script>

<%
// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

// vidMemberType=42
int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264										&workstudy=8104
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidRoomBoard=265										&roomboard=iYesRoomBoard8106
int iNoRoomBoard=8105, iYesRoomBoard=8106;

// vidStipend=266
int iNoStipend=8107, iYesStipend=8108;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;

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
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszOrgNamePrint = "";
String aszServiceAreasPrint = "";
String aszOppTitlePrint = "";

int iCounter=0;
int iPopularity=0;
String aszPopularity="";
String aszPopularityImg="";

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}

%>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<bean:define id="orgportal" name="orgportal" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<%
	String aszTempCity= searchinfo.getCity();
	String aszTempCountry= searchinfo.getCountry();
	String aszTempRegion= searchinfo.getRegion();
%>


<% //if (aszHostID.equalsIgnoreCase("voleng1")){ %>
<% 

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}
%>

<title><%=aszSubdomain%>: 
<%
// output city searched
if (!(searchinfo.getCity().equalsIgnoreCase(""))){
	out.print(searchinfo.getCity() + ": ");
}
		
		// output country searched
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if((aszOptRqCode.equalsIgnoreCase( aszTempCountry ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
				out.print(aAppCode.getCTRPrintableName() + ": ");}
			}
		}
		// output region searched
		if(null != aRegionList){
			for(int index=0; index < aRegionList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				String aszOptRqCode2 = aszOptRqCode.substring(0,3);
				if((aszOptRqCode.equalsIgnoreCase( aszTempRegion ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
					if ((!(aszOptRqCode2.equalsIgnoreCase( "USA" ))) && (!(aszOptRqCode.equalsIgnoreCase( "Canada" ))) ){
						out.print(aAppCode.getAPCDisplay() + ": ");
					}
				}
			}
		}

int[] a_iOrgFavoriteOpps=orgportal.getORGFavoritedOPPNIDsArray();
int length=a_iOrgFavoriteOpps.length;
int i=0;
int[] a_iOrgFavoriteOppsFromFeed=orgportal.getORGFavoritedOPPNIDsFromFeedArray();
int lengthFromFeed=a_iOrgFavoriteOppsFromFeed.length;
int iFromFeed=0;
boolean b_checkAllDefault = false;
if(length<1 && lengthFromFeed<1){
	b_checkAllDefault = true;
}

%>
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
Volunteer Opportunities</title>
<% }else{ %>
Christian Volunteer and Short Term Missions Opportunities</title>
<% } %>



<style>
.number {
	float: left;
	margin-right:15px;
}
submitbutton{
	display:none;
}
</style>
</head>




<!-- BEGIN MAINCONTENT -->
<!--div id="maincontent">



<div id="body"-->


<div id="sorts">
<form name="searchForm" action="<%=aszPortal%>/oppsrch.do" method="get" >
<input type="hidden" name="method" value="processOppSearchAll" />
<input type="hidden" name="postype" value="<%="" + searchinfo.getOPPPositionTypeTID()%>" />
<input type="hidden" name="duration" value="<%=searchinfo.getDuration()%>" />
<input type="hidden" name="postalcode" value="<%=searchinfo.getPostalCode()%>" />
<input type="hidden" name="distance" value="<%=searchinfo.getDistance()%>" />
<input type="hidden" name="servicearea1" value="<%="" + searchinfo.getOPPServiceArea1TID()%>" />
<input type="hidden" name="serviceareas" value="<%="" + searchinfo.getServiceAreasTIDs()%>" />
<input type="hidden" name="orgname" value="<%=searchinfo.getOrgName()%>" />
<input type="hidden" name="org" value="<%="" + searchinfo.getNID()%>" />
<input type="hidden" name="skills1id" value="<%="" + searchinfo.getOPPSkills1TID()%>" />
<input type="hidden" name="skilltypes" value="<%="" + searchinfo.getSkillsTIDs()%>" />
<input type="hidden" name="greatforkid" value="<%="" + searchinfo.getGreatForKidTID()%>" />
<input type="hidden" name="greatforsenior" value="<%="" + searchinfo.getGreatForSeniorTID()%>" />
<input type="hidden" name="greatforteen" value="<%="" + searchinfo.getGreatForTeenTID()%>" />
<input type="hidden" name="greatforgroup" value="<%="" + searchinfo.getGreatForGroupTID()%>" />
<input type="hidden" name="greatforfamily" value="<%="" + searchinfo.getGreatForFamilyTID()%>" />
<input type="hidden" name="mingroup" value="<%="" + searchinfo.getMinSize()%>" />
<input type="hidden" name="maxgroup" value="<%="" + searchinfo.getMaxSize()%>" />
<input type="hidden" name="city" value="<%=searchinfo.getCity()%>" />
<input type="hidden" name="citytid" value="<%="" + searchinfo.getCityTID()%>" />
<input type="hidden" name="state" value="<%=searchinfo.getState()%>" />
<input type="hidden" name="prov" value="<%=searchinfo.getOthrProv()%>" />
<input type="hidden" name="country" value="<%=searchinfo.getCountry()%>" />
<input type="hidden" name="countrytid" value="<%="" + searchinfo.getCountryTID()%>" />
<input type="hidden" name="region" value="<%=searchinfo.getRegion()%>" />
<input type="hidden" name="denomaffiltid" value="<%="" + searchinfo.getDenomAffilTID()%>" />
<input type="hidden" name="orgaffil1tid" value="<%="" + searchinfo.getOrgAffil1TID()%>" />
<input type="hidden" name="orgaffil2tid" value="<%="" + searchinfo.getOrgAffil2TID()%>" />
<input type="hidden" name="orgaffil3tid" value="<%="" + searchinfo.getOrgAffil3TID()%>" />
<input type="hidden" name="orgaffil4tid" value="<%="" + searchinfo.getOrgAffil4TID()%>" />
<input type="hidden" name="orgaffil5tid" value="<%="" + searchinfo.getOrgAffil5TID()%>" />
<input type="hidden" name="localaffil" value="<%=searchinfo.getLocalAffil()%>" />
<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>

<img style="float:right; padding-top:10px" border="0" src="http://www.christianvolunteering.org/imgs/next-step_button.png" alt="next step" onclick="next_step()"/>

<% if(request.getParameter("method").equals("portalFavoriteOppsList")){ %>
<% }else{ %>
<% } %>
<br clear="all"/>

<table cellpadding=0 cellspacing=0>
<tr>
	<td>
		<input type="checkbox" class="form-checkbox" value="1" name="toggleSelect" id="toggleSelect" onclick="toggleCheckAllOppsTab(this)">
		Select All
	</td>
<td width="10"></td>
	<td>
		<input type="button" name="addToListingsOpp" value="Add to My <%=aszOrgOrChurch%> Listings" onClick="addToListings()">
	</td>
<td width="20"></td>
<td>
Sort results by: </td>
<td></td>
<td>
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value="">Popularity</option>
<% if( searchinfo.getPostalCode().equalsIgnoreCase( "" ) && searchinfo.getNotLclPostalCode().equalsIgnoreCase( "" )){ %>
<% } else { %>
		<option value="distance">Distance</option>
<% } %>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <%//option value="prov">Province (outside US & Canada)</option%>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
	<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity (Annually)</option>
    </SELECT>
</td>
<td width="10"></td>
<td>
<input type="submit" name="Submit" value="Go">

</form>

</td>
</tr>
</table>

<br clear="all">

<!-- form to submit to "favorite" opps; gets inputs set via javascript with the checkbox values -->
<form name="orgForm" action="<%=aszPortal%>/org.do?method=portalFavoriteOppsList" method="post">
<input type="hidden" name="method" value="portalFavoriteOppsList" >
<input type="hidden" name="requesttype" id="requesttype" value="" >

<%
out.print("<!--<br><br><br><br><h1>size of fav's is " +  length + " and size from feeds is " + lengthFromFeed + "</h1><br><br><br><br><br>-->");
%>
		<logic:notEmpty name="alist" >
			<logic:iterate id="opp" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">

<%			
// separate out categories for output and re-word categories for no-faith
String	aszServiceAreas = opp.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszServiceAreas=aszServiceAreas.replaceAll("Bible Study","");
	aszServiceAreas=aszServiceAreas.replaceAll("Christian/Catholic Schools","");
	aszServiceAreas=aszServiceAreas.replaceAll("Church Planting","");
	aszServiceAreas=aszServiceAreas.replaceAll("Evangelism","");
	aszServiceAreas=aszServiceAreas.replaceAll("Family / Adults Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Religious Education","");
	aszServiceAreas=aszServiceAreas.replaceAll("Single Parents / Crisis Pregnancy","");
	aszServiceAreas=aszServiceAreas.replaceAll("Vacation Bible School","");
	aszServiceAreas=aszServiceAreas.replaceAll("Women's Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Disabilities Ministry","Disabilities Outreach");
	aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry / Hunger","Food Service / Hunger");
	aszServiceAreas=aszServiceAreas.replaceAll("Prison Ministry","Prison Outreach");
}
aszServiceAreas=aszServiceAreas.replaceAll("^,","");
aszServiceAreas=aszServiceAreas.replaceAll(",$","");
aszServiceAreas=aszServiceAreas.replaceAll(",(?=[^()]*)", ", ");

	String aszCity= opp.getOPPAddrCity();
	String aszTemp1= opp.getOPPAddrCountryName();
	String aszTemp2 = opp.getOPPAddrStateprov();
	String aszOppLocation = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppLocation=aAppCode.getCSPStateName();
					}else {
						aszOppLocation=aAppCode.getCSPStateName();
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
					aszOppLocation=aAppCode.getCTRPrintableName();
				}else {
					aszOppLocation=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppLocation = ", " + aszOppLocation;
}
aszOppLocation = opp.getOPPAddrCity() + aszOppLocation;
aszOppTitlePrint = opp.getOPPTitle();
aszOrgNamePrint = opp.getORGOrgName();


//popularity
iPopularity = opp.getOPPPopularity();
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
int iLastSpace = opp.getOPPDescTeaser().lastIndexOf(" ");
//String aszDescTeaser=opp.getOPPDescTeaser().substring(0,iLastSpace);
//int epoch = opp.getOPPUpdateDt();

long lTime = opp.getOPPUpdateDtNum();
String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (lTime*1000));

// program in forwarding if the opportunity is Faith-Filled
String aszOPPUrlAlias=opp.getOPPUrlAlias(), aszORGUrlAlias=opp.getORGUrlAlias();
String aszOPPUrlAliasShortened="", aszORGUrlAliasShortened="";
boolean feed=false;
if(aszOPPUrlAlias.contains("feed/")){
	feed=true;
	try{
		aszOPPUrlAliasShortened=opp.getOPPUrlAlias().substring(15) + "&feed=true";
	}catch(Exception e){}
}else{
	try{
		aszOPPUrlAliasShortened=opp.getOPPUrlAlias().substring(10);
	}catch(Exception e){}
}
try{
	aszORGUrlAliasShortened=opp.getORGUrlAlias().substring(4);
}catch(Exception e){}
if(opp.getOPPFaithSpecTID()==21998 && aszSecondaryHost.equalsIgnoreCase("default") ){ 
	aszOPPUrlAlias="http://www.christianvolunteering.org/"+opp.getOPPUrlAlias()+".jsp";
	aszORGUrlAlias="http://www.christianvolunteering.org/"+opp.getORGUrlAlias()+".jsp";
}else{ 
	aszOPPUrlAlias=aszPortal+"/org.do?method=showOpport&urlalias="+aszOPPUrlAliasShortened+"&requesttype=myResultsAdminSelect";
	aszORGUrlAlias=aszPortal+"/"+opp.getORGUrlAlias()+".jsp";
}
String aszMemberClass="";
if(opp.getORGMember()>0){
	aszMemberClass="class=\"member\"";
}

String	aszSkills = opp.getSkillTypes();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszSkills=aszSkills.replaceAll("Bible Study","");
	aszSkills=aszSkills.replaceAll("Evangelism","");
	aszSkills=aszSkills.replaceAll("Disabilities Ministry","Disabilities Outreach");
	aszSkills=aszSkills.replaceAll("Food Ministry / Hunger","Food Service / Hunger");
	aszSkills=aszSkills.replaceAll("Prison Ministry","Prison Outreach");
}
aszSkills=aszSkills.replaceAll("^,","");
aszSkills=aszSkills.replaceAll(",$","");
aszSkills=aszSkills.replaceAll(",(?=[^()]*)", ", ");

String	aszPositionType = opp.getOPPPositionType();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszPositionType=aszPositionType.replaceAll("Missions","Service Trip");
}
aszPositionType=aszPositionType.replaceAll("^,","");
aszPositionType=aszPositionType.replaceAll(",$","");
aszPositionType=aszPositionType.replaceAll(",(?=[^()]*)", ", ");
String aszLocation = "";
if(aszPositionType.contains("Virtual")){
	if(aszPositionType.contains("Local")){
		aszLocation = aszOppLocation + " or " + " Virtual Volunteering";//aszPositionType;
	}else{
		aszLocation = aszPositionType;
	}
}else{
	aszLocation = aszOppLocation;
}
String	aszFrequency = opp.getOPPFreq();
String	aszTripLength = opp.getOPPTripLength();
String	aszGreatFor = opp.getOPPGreatForAreas();
String	aszBenefits = opp.getOPPBenefits();
String	aszWorkStudy = opp.getOPPWorkStudy();
aszFrequency=aszFrequency.replaceAll(",(?=[^()]*)", ", ");
aszTripLength=aszTripLength.replaceAll(",(?=[^()]*)", ", ");
aszGreatFor=aszGreatFor.replaceAll(",(?=[^()]*)", ", ");
aszBenefits=aszBenefits.replaceAll(",(?=[^()]*)", ", ");
aszWorkStudy=aszWorkStudy.replaceAll(",(?=[^()]*)", ", ");

String aszInterestAreas = "";
if(aszServiceAreas.length() > 1 && aszWorkStudy.length() > 1){
	aszInterestAreas = aszServiceAreas + ", " + aszWorkStudy;
}else if(aszServiceAreas.length() > 1){
	aszInterestAreas = aszServiceAreas;
}else if(aszWorkStudy.length() > 1){
	aszInterestAreas = aszWorkStudy;
}

String aszSTMDetails = "";
if(aszBenefits.length()>1){
	aszSTMDetails += " <b>Benefits Offered:</b>&nbsp;" + aszBenefits + "&nbsp;";
}
if(aszTripLength.length()>1){
	aszSTMDetails += " <b>Length of Trip:</b>&nbsp;" + aszTripLength + "&nbsp;";
}
if(aszSTMDetails.length()>1){
	aszSTMDetails = "<br>" + aszSTMDetails.substring(1);
}


int iStartDate = opp.getOPPStartDtNum();
int iEndDate = opp.getOPPEndDtNum();
String aszScheduledDate = "";

aszTemp = opp.getOPPDaterequired();
int iDateReqTID = opp.getOPPDaterequiredTID();
int iTemp=0; long iTime=0;
iTemp = iDateReqTID;
if(iTemp==8132 || iTemp==8133 || iTemp==8134){
    if(opp.getOPPStartDtNum()> 0 ){
	    if(iTemp==8134){			
			iTime = opp.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
			aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
			aszScheduledDate = aszTemp;
			iTime = opp.getOPPEndDtNum();
			if (iTime > 0){
				if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
					aszScheduledDate += " - " + aszTemp;
				}
			}
	    }else if(iTemp==8133){			
			iTime = opp.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
			aszScheduledDate = aszTemp;
			iTime = opp.getOPPEndDtNum();
			if (iTime > 0){
				if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
					aszScheduledDate += " - " + aszTemp;
				}
			}
	    }
    }
} else {//(this opportunity is not scheduled for a specific date)
	aszScheduledDate = "";
}
String aszDatePrint = "";

if(aszFrequency.contains("One-time")){
	aszDatePrint = " <b>" + aszFrequency;
	if(aszScheduledDate.length()>1){
		aszDatePrint += " on: </b> " + aszScheduledDate + "&nbsp;&nbsp;";
	}else{
		aszDatePrint += "</b>&nbsp&nbsp";
	}
}



out.println("<!--<br>" + aszSkills + " " + aszPositionType + " freqency: " + aszFrequency + " " +aszTripLength+ " " + aszGreatFor+ " " +aszBenefits + " " + aszWorkStudy+"<br>-->");
%>

<div id="search-results">

			<div <%=aszMemberClass%>>
<%iCounter++;%>
<div class="number">
<%
int test=0, testFromFeed=0;
for(i=0; i<length; i++){
	if(a_iOrgFavoriteOpps[i]==opp.getOPPNID()){
		test++;
%>
<input type="hidden" name="favoritedoppnids" id="favoritedoppnids" value="<% out.print(""+a_iOrgFavoriteOpps[i]); %>" checked>
<% 
	}
}
for(iFromFeed=0; iFromFeed<lengthFromFeed; iFromFeed++){
	if(a_iOrgFavoriteOppsFromFeed[iFromFeed]==opp.getOPPNID()){
		testFromFeed++;
%>
<input type="hidden" name="favoritedoppnidsfromfeed" id="favoritedoppnidsfromfeed" value="<% out.print(""+a_iOrgFavoriteOppsFromFeed[iFromFeed]); %>" checked>
<% 
	}
}
if(b_checkAllDefault == true){ 
	if(feed==false){
%>
	<input type="checkbox" name="oppnids" id="oppnids<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" checked>
<% 
	} else if(feed==true){ 
%>
	<input type="checkbox" name="oppnidsfromfeed" id="oppnidsfromfeed<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" checked>
<%
	}
}else{
	if(feed==false){
		if(test==0 && b_checkAllDefault == false){ %>
		<input type="checkbox" name="oppnids" id="oppnids<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" >
		<% }else{ %>
		<input type="checkbox" name="oppnids" id="oppnids<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" checked>
		<% } 
	}else if(feed==true){
		if(testFromFeed==0 && b_checkAllDefault == false){ %>
		<input type="checkbox" name="oppnidsfromfeed" id="oppnidsfromfeed<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" >
		<% }else{ %>
		<input type="checkbox" name="oppnidsfromfeed" id="oppnidsfromfeed<%=""+opp.getOPPNID()%>" value="<%=""+opp.getOPPNID()%>" checked>
		<% }  
	}
}%>
</div>
<div class="listing_info" >
<%=iCounter%>.&nbsp;
<A class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszOppTitlePrint%></A>
&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
&nbsp;&nbsp;
<A class="org_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
<div class="teaser">
<%out.print(opp.getOPPDescTeaser());%>...
<A class="more_link" href="<%=aszOPPUrlAlias%>">more ></A>
</div>
<div class="opp-info">
<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
<%=aszSTMDetails%>
<br>
<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
<%=aszDatePrint%><b>Date Last Updated:</b>&nbsp;<%out.print(date);%>
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
<input type="button" name="addToListingsOpp" value="Add to My <%=aszOrgOrChurch%> Listings" onClick="addToListings()">
		
<script type="text/javascript">
$(document).ready(function() {

if(a_iOrgFavoriteOpps.size<1 && a_iOrgFavoriteOppsFromFeed.size<1){
	toggleCheckAllOppsTab(document.getElementById('toggleSelect'));
}else{

<%
for(i=0; i<length; i++){
%>
	$('input[id=oppnids<% out.print(""+a_iOrgFavoriteOpps[i]); %>]').attr('checked', true); 
<% } %>
<%
for(i=0; i<lengthFromFeed; i++){
%>
	$('input[id=oppnidsfromfeed<% out.print(""+a_iOrgFavoriteOppsFromFeed[i]); %>]').attr('checked', true); 
<% } %>
}

 }); 
 
function checkAll(field){
	for(i=0; i < field.length; i++)
		field[i].checked=true;
}
function uncheckAll(field){
	for(i=0; i < field.length; i++)
		field[i].checked=false;
}

</script>

<!--/div></div-->
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%//@ include file="/template/footer.inc" %><!-- end footer information -->
