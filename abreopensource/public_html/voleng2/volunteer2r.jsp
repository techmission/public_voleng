<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<%@page import="java.text.DecimalFormat" %>

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

%>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<%
int iPositionType = searchinfo.getOPPPositionTypeTID();
String aszDuration = searchinfo.getDuration();
String aszPostalCode = searchinfo.getPostalCode();
String aszDistance = searchinfo.getDistance();
int iServiceArea1 = searchinfo.getOPPServiceArea1TID();
int iServiceArea2 = searchinfo.getOPPServiceArea2TID();
int iServiceArea3 = searchinfo.getOPPServiceArea3TID();
String aszOrgName = searchinfo.getOrgName();

String aszTempCity= searchinfo.getCity();
String aszTempCountry= searchinfo.getCountry();
String aszTempRegion= searchinfo.getRegion();

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.churchvolunteering.org")){
	aszSubdomain = "ChurchVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}

String aszDetails = "", aszCountryName="", aszRegionName="";
if(aszOrgName.length()>0){
	aszDetails+= aszOrgName + ": ";
}
	// output city searched
	if (!(searchinfo.getCity().equalsIgnoreCase(""))){
		aszDetails+= searchinfo.getCity() + ": ";
	}
	// output country searched
	if(null != aCountryList){
		for(int index=0; index < aCountryList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
			if(null == aAppCode) continue;
			String aszOptRqCode = aAppCode.getCTRIso();
			if((aszOptRqCode.equalsIgnoreCase( aszTempCountry ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
				aszDetails+= aAppCode.getCTRPrintableName() + ": ";
				aszCountryName= aAppCode.getCTRPrintableName() +",";
			}
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
					aszDetails+= aAppCode.getAPCDisplay() + ": ";
					aszRegionName= aAppCode.getAPCDisplay() +",";
				}
			}
		}
	}
%>



<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

	<title><%=aszSubdomain%>: 
	<%=aszDetails%>

<%
	if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
Volunteer Opportunities</title>
<% 
	}else{ 
%>
Christian Volunteer and Short Term Missions Opportunities</title>
<% 
	}
%>


<meta name="description" content="<%=aszDetails%> Search <%=aszOrgOrChurchOpp%> Opportunities: <%=aszSubdomain%>" />
<meta name="keywords" content="<%=aszOrgName%>, <%=aszCountryName%> <%=aszRegionName%> volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism">

<%
}

String aszSearchTerms = "<input type=\"hidden\" name=\"method\" value=\"processOppSearchAll\" /> \n\r";
String aszSearchURL = "?method=processOppSearchAll";

if(iPositionType>0){
	aszSearchTerms += "<input type=\"hidden\" name=\"postype\" value=\""+iPositionType+"\" /> \n\r";
	aszSearchURL += "&postype="+iPositionType;
}

if(aszDuration.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"duration\" value=\""+aszDuration+"\" /> \n\r";
	aszSearchURL += "&duration="+aszDuration;
}

if(aszPostalCode.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"postalcode\" value=\""+aszPostalCode+"\" /> \n\r";
	aszSearchURL += "&postalcode="+aszPostalCode;
}

if(aszDistance.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"distance\" value=\""+aszDistance+"\" /> \n\r";
	aszSearchURL += "&distance="+aszDistance;
}

if(iServiceArea1 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"servicearea1\" value=\""+iServiceArea1+"\" /> \n\r";
	aszSearchURL += "&servicearea1="+iServiceArea1;
}

if(iServiceArea2 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"servicearea2\" value=\""+iServiceArea2+"\" /> \n\r";
	aszSearchURL += "&servicearea2="+iServiceArea2;
}

if(iServiceArea3 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"servicearea3\" value=\""+iServiceArea3+"\" /> \n\r";
	aszSearchURL += "&servicearea3="+iServiceArea3;
}

if(aszOrgName.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgname\" value=\""+aszOrgName+"\" /> \n\r";
	aszSearchURL += "&orgname="+aszOrgName;
}

int iOrgNID = searchinfo.getNID();
if(iOrgNID > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgnid\" value=\""+iOrgNID+"\" /> \n\r";
	aszSearchURL += "&orgnid="+iOrgNID;
}

int iSkills1 = searchinfo.getOPPSkills1TID();
if(iSkills1 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"skills1id\" value=\""+iSkills1+"\" /> \n\r";
	aszSearchURL += "&skills1id="+iSkills1;
}

int iSkills2 = searchinfo.getOPPSkills2TID();
if(iSkills2 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"skills2id\" value=\""+iSkills2+"\" /> \n\r";
	aszSearchURL += "&skills2id="+iSkills2;
}

int iSkills3 = searchinfo.getOPPSkills3TID();
if(iSkills3 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"skills3id\" value=\""+iSkills3+"\" /> \n\r";
	aszSearchURL += "&skills3id="+iSkills3;
}

int iFreqSearch = searchinfo.getOPPFrequencyTID();
if(iFreqSearch > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"frequency\" value=\""+iFreqSearch+"\" /> \n\r";
	aszSearchURL += "&frequency="+iFreqSearch;
}

int iGreatForKid = searchinfo.getGreatForKidTID();
if(iGreatForKid > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"greatforkid\" value=\""+iGreatForKid+"\" /> \n\r";
	aszSearchURL += "&greatforkid="+iGreatForKid;
}

int iGreatForSenior = searchinfo.getGreatForSeniorTID();
if(iGreatForSenior > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"greatforsenior\" value=\""+iGreatForSenior+"\" /> \n\r";
	aszSearchURL += "&greatforsenior="+iGreatForSenior;
}

int iGreatForTeen = searchinfo.getGreatForTeenTID();
if(iGreatForTeen > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"greatforteen\" value=\""+iGreatForTeen+"\" /> \n\r";
	aszSearchURL += "&greatforteen="+iGreatForTeen;
}

int iGreatForGroup = searchinfo.getGreatForGroupTID();
if(iGreatForGroup > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"greatforgroup\" value=\""+iGreatForGroup+"\" /> \n\r";
	aszSearchURL += "&greatforgroup="+iGreatForGroup;
}

int iGreatForFamily = searchinfo.getGreatForFamilyTID();
if(iGreatForFamily > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"greatforfamily\" value=\""+iGreatForFamily+"\" /> \n\r";
	aszSearchURL += "&greatforfamily="+iGreatForFamily;
}

int iMinSize = searchinfo.getMinSize();
if(iMinSize > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"mingroup\" value=\""+iMinSize+"\" /> \n\r";
	aszSearchURL += "&mingroup="+iMinSize;
}

int iMaxSize = searchinfo.getMaxSize();
if(iMaxSize > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"maxgroup\" value=\"="+iMaxSize+"\" /> \n\r";
	aszSearchURL += "&maxgroup="+iMaxSize;
}

String aszSearchCity = searchinfo.getCity();
if(aszSearchCity.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"city\" value=\""+aszSearchCity+"\" /> \n\r";
	aszSearchURL += "&city="+aszSearchCity;
}

String aszState = searchinfo.getState();
if(aszState.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"state\" value=\""+aszState+"\" /> \n\r";
	aszSearchURL += "&state="+aszState;
}

String aszCountry = searchinfo.getCountry();
if(aszCountry.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"country\" value=\""+aszCountry+"\" /> \n\r";
	aszSearchURL += "&country="+aszCountry;
}

String aszRegion = searchinfo.getRegion();
if(aszRegion.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"region\" value=\""+aszRegion+"\" /> \n\r";
	aszSearchURL += "&region="+aszRegion;
}

int iDenomAffil = searchinfo.getDenomAffilTID();
if(iDenomAffil > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"denomaffiltid\" value=\""+iDenomAffil+"\" /> \n\r";
	aszSearchURL += "&denomaffiltid="+iDenomAffil;
}

int iOrgAffil1 = searchinfo.getOrgAffil1TID();
if(iOrgAffil1 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgaffil1tid\" value=\""+iOrgAffil1+"\" /> \n\r";
	aszSearchURL += "&orgaffil1tid="+iOrgAffil1;
}

int iOrgAffil2 = searchinfo.getOrgAffil2TID();
if(iOrgAffil2 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgaffil2tid\" value=\""+iOrgAffil2+"\" /> \n\r";
	aszSearchURL += "&orgaffil2tid="+iOrgAffil2;
}

int iOrgAffil3 = searchinfo.getOrgAffil3TID();
if(iOrgAffil3 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgaffil3tid\" value=\""+iOrgAffil3+"\" /> \n\r";
	aszSearchURL += "&orgaffil3tid="+iOrgAffil3;
}

int iOrgAffil4 = searchinfo.getOrgAffil4TID();
if(iOrgAffil4 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgaffil4tid\" value=\""+iOrgAffil4+"\" /> \n\r";
	aszSearchURL += "&orgaffil4tid="+iOrgAffil4;
}

int iOrgAffil5 = searchinfo.getOrgAffil5TID();
if(iOrgAffil5 > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"orgaffil5tid\" value=\""+iOrgAffil5+"\" /> \n\r";
	aszSearchURL += "&orgaffil5tid="+iOrgAffil5;
}

String aszLocalAffil = searchinfo.getLocalAffil();
if(aszLocalAffil.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"localaffil\" value=\""+aszLocalAffil+"\" /> \n\r";
	aszSearchURL += "&localaffil="+aszLocalAffil;
}

String aszLatitude = searchinfo.getSearchLatitude1();
if(aszLatitude.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"latitude\" value=\""+aszLatitude+"\" /> \n\r";
	aszSearchURL += "&latitude="+aszLatitude;
}

String aszLongitude = searchinfo.getSearchLongitude1();
if(aszLongitude.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"longitude\" value=\""+aszLongitude+"\" /> \n\r";
	aszSearchURL += "&longitude="+aszLongitude;
}

String aszRequestType = searchinfo.getSearchRequestType();
if(aszRequestType.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"requesttype\" value=\""+aszRequestType+"\" /> \n\r";
	aszSearchURL += "&requesttype="+aszRequestType;
}

String aszHQOffSite = searchinfo.getOPPHQorOffSite();
if(aszHQOffSite.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"hqoroffsite\" value=\""+aszHQOffSite+"\" /> \n\r";
	aszSearchURL += "&hqoroffsite="+aszHQOffSite;
}

String aszSearchKey1 = searchinfo.getSearchKey();
if(aszSearchKey1.length() > 0){
	//aszSearchTerms += "<input type=\"hidden\" name=\"searchkey1\" value=\""+aszSearchKey1+"\" /> \n\r";
	aszSearchURL += "&searchkey1="+aszSearchKey1;
}

String aszSearchKey2 = searchinfo.getSearchKey2();
if(aszSearchKey2.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"searchkey2\" value=\""+aszSearchKey2+"\" /> \n\r";
	aszSearchURL += "&searchkey2="+aszSearchKey2;
}

String aszSearchKey3 = searchinfo.getSearchKey3();
if(aszSearchKey3.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"searchkey3\" value=\""+aszSearchKey3+"\" /> \n\r";
	aszSearchURL += "&searchkey3="+aszSearchKey3;
}

String aszSource = searchinfo.getSource();
if(aszSource.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"source\" value=\""+aszSource+"\" /> \n\r";
	aszSearchURL += "&source="+aszSource;
}

if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
	aszSearchTerms += "<input type=\"hidden\" name=\"requiredcodetype\" value=\"No\" /> \n\r";
	aszSearchURL += "&requiredcodetype=No";
}


aszCurrentURL = "/oppsrch.do" + aszSearchURL;


boolean bFaithSearch=false;

if (	iServiceArea1 == 4760 	||	iServiceArea2 == 4760 	||	iServiceArea3 == 4760 	||
		iServiceArea1 == 4764 	||	iServiceArea2 == 4764 	||	iServiceArea3 == 4764 	||
		iServiceArea1 == 4772 	||	iServiceArea2 == 4772 	||	iServiceArea3 == 4772 	||
		iServiceArea1 == 4773	||	iServiceArea2 == 4773	||	iServiceArea3 == 4773	||
		iServiceArea1 == 4783	||	iServiceArea2 == 4783	||	iServiceArea3 == 4783	||
		iServiceArea1 == 4758	||	iServiceArea2 == 4758	||	iServiceArea3 == 4758	||
		iServiceArea1 == 4787	||	iServiceArea2 == 4787	||	iServiceArea3 == 4787	||
		iServiceArea1 == 4789 	||	iServiceArea2 == 4789 	||	iServiceArea3 == 4789 	||
		iServiceArea1 == 7341 	||	iServiceArea2 == 7341 	||	iServiceArea3 == 7341 	||
		iServiceArea1 == 7342	||	iServiceArea2 == 7342	||	iServiceArea3 == 7342	||
		iSkills1 == 4748 		||	iSkills2 == 4748 		||	iSkills3 == 4748 		||
		iSkills1 == 4749 		||	iSkills2 == 4749 		||	iSkills3 == 4749 		
){

	bFaithSearch=true;
}

if( bFaithSearch==true  ){
	if(! aszHostID.equalsIgnoreCase("volengchurch") ){
		if( aszMobileSubdomain.length() > 3 ) { 
			response.setStatus(301);
			response.setHeader( "Location", "http://m.churchvolunteering.org/oppsrch.do" +  aszSearchURL + "&redirected=true" );		
			response.setHeader( "Connection", "close" );
		}else{
			response.setStatus(301);
			response.setHeader( "Location", "http://www.churchvolunteering.org/oppsrch.do" +  aszSearchURL + "&redirected=true" );		
			response.setHeader( "Connection", "close" );
		}
	}
}

%> 

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Opportunities Search Results</span>
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
<span style="float: left;">Opportunities Search Results</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; opportunities search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->
<br>
<div id="sorts">
<form action="<%=aszPortal%>/oppsrch.do" method="get" name="searchForm">
<input type="hidden" value="oppSrchResultsSort" name="formname" id="formname"/>
	<%=aszSearchTerms%>
<% if( 	aszMobileSubdomain.length() < 3 ) { %>
	<table cellpadding=0 cellspacing=0><tr><td width=80%>
	&laquo; <a href="<%=aszPortal%>/advancedsearch.jsp">Back to Advanced Search</a>
	</td><td >
	
	Sort results by: </td><td></td></tr>
	<tr><td width=80%></td><td >
<% } %>
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
<% if( 	aszMobileSubdomain.length() > 3 ) { %>
        <option value="">Sort results by:</option>
<% } %>
        <option value="" <%if(searchinfo.getSearchKey().equals("")){out.print("selected=selected");}%>>Popularity</option>
<% if( searchinfo.getPostalCode().length()>0 || aszLatitude.length() > 0 || aszLatitude.length() > 0){ %>
		<option value="distance">Distance</option>
<% } %>
        <option value="organization" <%if(searchinfo.getSearchKey().equals("organization")){out.print("selected=selected");}%>>Organization Name</option>
        <option value="opportunity" <%if(searchinfo.getSearchKey().equals("opportunity")){out.print("selected=selected");}%>>Opportunity Title</option>
        <option value="city" <%if(searchinfo.getSearchKey().equals("city")){out.print("selected=selected");}%>>City</option>
        <option value="state" <%if(searchinfo.getSearchKey().equals("state")){out.print("selected=selected");}%>>State (US & Canada)</option>
        <option value="country" <%if(searchinfo.getSearchKey().equals("country")){out.print("selected=selected");}%>>Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil" <%if(searchinfo.getSearchKey().equals("denomaffil")){out.print("selected=selected");}%>>Denominational Affiliation</option>
        <option value="affil" <%if(searchinfo.getSearchKey().equals("affil")){out.print("selected=selected");}%>>Affiliation</option>
        <option value="stmdur" <%if(searchinfo.getSearchKey().equals("stmdur")){out.print("selected=selected");}%>>Duration (Short-term Missions Only)</option>
        <option value="stmcost" <%if(searchinfo.getSearchKey().equals("stmcost")){out.print("selected=selected");}%>>Cost (Short-term Missions Only)</option>
<% } %>
		<option value="updatedt" <%if(searchinfo.getSearchKey().equals("updatedt")){out.print("selected=selected");}%>>Date Last Updated</option>
<% if( 	aszMobileSubdomain.length() < 3 ) { %>
        <option value="numvolorg" <%if(searchinfo.getSearchKey().equals("numvolorg")){out.print("selected=selected");}%>>Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol" <%if(searchinfo.getSearchKey().equals("oppnumvol")){out.print("selected=selected");}%>>Number of Volunteers / Opportunity (Annually)</option>
<% }else{ %>
        <option value="numvolorg" <%if(searchinfo.getSearchKey().equals("numvolorg")){out.print("selected=selected");}%>>Number of Volunteers / Organization</option>
        <option value="oppnumvol" <%if(searchinfo.getSearchKey().equals("oppnumvol")){out.print("selected=selected");}%>>Number of Volunteers / Opportunity</option>
<% } %>
    </SELECT>
<% if( 	aszMobileSubdomain.length() < 3 ) { %>
	</td><td>
<input type="submit" name="Submit" value="Go">

</td></tr></table>
<% }else{ %>
<input type="submit" name="Submit" value="Go">
<% } %>

</form>
</div>


<% if( 	aszMobileSubdomain.length() < 3 ) { %>
<H1>
<%=aszOrgOrChurchOpp%> 
<% if (searchinfo.getOPPPositionTypeTID()==iShortTerm) { %>
& Short Term <% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )) { %>Service <% }else{ %>Missions <% } %>
<% } %>
Opportunities
<%
// output city searched
if (!(searchinfo.getCity().equalsIgnoreCase(""))){
	out.print(": " + searchinfo.getCity());
}
		// output country searched
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(	(aszOptRqCode.equalsIgnoreCase( aszTempCountry ) ) 	&& 
					(!(aszOptRqCode.equalsIgnoreCase( "USA" ) ))	&&
					(!(aszOptRqCode.equalsIgnoreCase( "US" ) ))	&&
					(!(aszOptRqCode.equalsIgnoreCase( "" ) ))
				) {
				out.print(": " + aAppCode.getCTRPrintableName());}
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
						out.print(": " + aAppCode.getAPCDisplay());
					}
				}
			}
		}

%>
</H1>      
<% } %>
<% int iFeedsResults=0; %>
<div id="resultsNumber">
<b>&nbsp;</b>  
</div>

<br>
        
		<logic:notEmpty name="alist" >
			<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszServiceAreas = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
/*
	aszServiceAreas=aszServiceAreas.replaceAll("Small Group/Bible Study","");
	aszServiceAreas=aszServiceAreas.replaceAll("Christian/Catholic Schools","");
	aszServiceAreas=aszServiceAreas.replaceAll("Church Planting","");
	aszServiceAreas=aszServiceAreas.replaceAll("Evangelism/Seeker Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Family / Adults Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Sunday School/Religious Ed","");
	aszServiceAreas=aszServiceAreas.replaceAll("Single Parents / Crisis Pregnancy","");
	aszServiceAreas=aszServiceAreas.replaceAll("Single Parents/Crisis Pregnancy","");
	aszServiceAreas=aszServiceAreas.replaceAll("Vacation Bible School","");
	aszServiceAreas=aszServiceAreas.replaceAll("Women's Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Children and Youth Ministry","Children and Youth");
	aszServiceAreas=aszServiceAreas.replaceAll("Disabilities Ministry","Disabilities Outreach");
	aszServiceAreas=aszServiceAreas.replaceAll("Visitation/Pastoral Care","Visitation");
	aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry / Hunger","Food Service / Hunger");
	aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry","Foods Outreach");
	aszServiceAreas=aszServiceAreas.replaceAll("Prison Ministry","Prison Outreach");
*/	
	aszServiceAreas=aszServiceAreas.replaceAll("Ministry","Services");
	aszServiceAreas=aszServiceAreas.replaceAll("Worship/Music/Choir","Music");
	aszServiceAreas=aszServiceAreas.replaceAll("Christian Camping","Camping");
	aszServiceAreas=aszServiceAreas.replaceAll("Ministry","Services");
	aszServiceAreas=aszServiceAreas.replaceAll("Ministry","Services");
}
aszServiceAreas=aszServiceAreas.replaceAll("^,","");
aszServiceAreas=aszServiceAreas.replaceAll(",$","");
aszServiceAreas=aszServiceAreas.replaceAll(",(?=[^()]*)", ", ");

	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
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
aszOppLocation = org.getOPPAddrCity() + aszOppLocation;
aszOppTitlePrint = org.getOPPTitle();
aszOrgNamePrint = org.getORGOrgName();


//popularity
iPopularity = org.getOPPPopularity();
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
int iLastSpace = org.getOPPDescTeaser().lastIndexOf(" ");
//String aszDescTeaser=org.getOPPDescTeaser().substring(0,iLastSpace);
//int epoch = org.getOPPUpdateDt();

long lTime = org.getOPPUpdateDtNum();
String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (lTime*1000));
if(lTime < 1){
	if(org.getOPPUpdateDt()!=null){
		date =  new java.text.SimpleDateFormat("MM/dd/yyyy").format(org.getOPPUpdateDt());
	}
}

out.print("<!-- Opp uid: "+org.getOPPUID()+", Portal uid: "+iPortalUID+" --> ");
//String 
if(iPortalUID > 0){
	if(org.getOPPUID()==iPortalUID){
		out.print("<!--this opportunity is owned by the portal org/church; need to check for offsite or at HQ -->");
		if(org.getOPPHQorOffSite().equalsIgnoreCase("hq")){
			aszOppLocation ="Church building: " + aszOppLocation; //aszLocationHQorOffSite = "HQ: " + aszOppLocation;
		}else if(org.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")){
			out.print("<!-- sets to off site -->");
			aszOppLocation ="Global: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
		}else{
			out.print("<!-- sets to off site -->");
			aszOppLocation ="Off-Site: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
		}
	}else if(! org.getOPPAddrCountryName().equalsIgnoreCase("us")) {
		out.print("<!-- sets to off site -->");
		aszOppLocation ="Global: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
	}else{
		out.print("<!-- sets to off site -->");
		aszOppLocation ="Off-Site: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
	}
}

// program in forwarding if the opportunity is Faith-Filled
String aszOPPUrlAlias=org.getOPPUrlAlias(), aszORGUrlAlias=org.getORGUrlAlias(), aszFeedsClass="";
boolean feed=false;
if(aszOPPUrlAlias.contains("feed/")){
	feed=true;
	aszFeedsClass="feeds";
	iFeedsResults++;
	aszPopularityImg="afg-blue_60.png";
}
if(aszORGUrlAlias.length()<1){
	aszORGUrlAlias=aszOPPUrlAlias;
}
if(iPortalUID > 0){
	aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
}else if(org.getOPPFaithSpecTID()==21998 && aszMobileSubdomain.length() > 3 ){ 
	aszOPPUrlAlias="http://m.churchvolunteering.org/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias="http://m.churchvolunteering.org/"+aszORGUrlAlias+".jsp";
}else if(org.getOPPFaithSpecTID()==21998 && aszSecondaryHost.equalsIgnoreCase("default") ){ 
	aszOPPUrlAlias="http://www.churchvolunteering.org/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias="http://www.churchvolunteering.org/"+aszORGUrlAlias+".jsp";
}else if(org.getOPPPositionType().contains("Virtual")){
	aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
}else if(
	(! org.getOPPAddrCountryName().equalsIgnoreCase("us") )  && 
	(aszSecondaryHost.equalsIgnoreCase("default") || 
	feed==false )
) {
	aszOPPUrlAlias="http://www.churchvolunteering.org/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias="http://www.churchvolunteering.org/"+aszORGUrlAlias+".jsp";
}else{ 
	aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
	aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
}
String aszMemberClass="class=\"listing\"";
if(org.getORGMember()>0){
	aszMemberClass="class=\"member listing\"";
}

String	aszSkills = org.getSkillTypes();
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

String	aszPositionType = org.getOPPPositionType();
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
String	aszFrequency = org.getOPPFreq();
String	aszTripLength = org.getOPPTripLength();
String	aszGreatFor = org.getOPPGreatForAreas();
String	aszBenefits = org.getOPPBenefits();
String	aszWorkStudy = org.getOPPWorkStudy();
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
//	aszSTMDetails += " <b>Length of Trip:</b>&nbsp;" + aszTripLength + "&nbsp;";
	aszSTMDetails += " <b>Duration:</b>&nbsp;" + aszTripLength + "&nbsp;";
}
if(aszSTMDetails.length()>1){
	aszSTMDetails = "<br>" + aszSTMDetails.substring(1);
}


int iStartDate = org.getOPPStartDtNum();
int iEndDate = org.getOPPEndDtNum();
String aszScheduledDate = "";

aszTemp = org.getOPPDaterequired();
int iDateReqTID = org.getOPPDaterequiredTID();
int iTemp=0; long iTime=0;
iTemp = iDateReqTID;
if(iTemp==8132 || iTemp==8133 || iTemp==8134){
    if(org.getOPPStartDtNum()> 0 ){
	    if(iTemp==8134){			
			iTime = org.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
			aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
			aszScheduledDate = aszTemp;
			iTime = org.getOPPEndDtNum();
			if (iTime > 0){
				if(org.getOPPStartDtNum()!=org.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
//					aszScheduledDate += " - " + aszTemp;
				}
			}
	    }else if(iTemp==8133){			
			iTime = org.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
			aszScheduledDate = aszTemp;
			iTime = org.getOPPEndDtNum();
			if (iTime > 0){
				if(org.getOPPStartDtNum()!=org.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
//					aszScheduledDate += " - " + aszTemp;
				}
			}
	    }
    }
} else {//(this opportunity is not scheduled for a specific date)
	aszScheduledDate = "";
}
String aszDatePrint = "";

/*
if(aszFrequency.contains("One-time")){
	aszDatePrint = " <b>" + aszFrequency;
	if(aszScheduledDate.length()>1){
		aszDatePrint += " on: </b> " + aszScheduledDate + "&nbsp;&nbsp;";
	}else{
		aszDatePrint += "</b>&nbsp&nbsp";
	}
}
*/


if(iDateReqTID==8133 || iDateReqTID==8134){
	if(aszScheduledDate.length()>1){
		aszDatePrint = " <b>" + aszFrequency;
		if(iDateReqTID==8133){// every year
			aszDatePrint += " on: </b> " + aszScheduledDate + ", annually&nbsp;&nbsp;";
		}else if(iDateReqTID==8134){// just the one time/year
			aszDatePrint += " on: </b> " + aszScheduledDate + "&nbsp;&nbsp;";
		}else{
			aszDatePrint += "</b>&nbsp&nbsp";
		}
	}else if(aszFrequency.contains("One-time")){
		aszDatePrint = " <b>" + aszFrequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
	}else if(iFreqSearch > 0){
		aszDatePrint = " <b>Frequency:</b> " + aszFrequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
	}
}else if(iFreqSearch > 0){
	aszDatePrint = " <b>Frequency:</b> " + aszFrequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
}else if(aszFrequency.contains("One-time")){
	aszDatePrint = " <b>" + aszFrequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
}

double dOppDistance = org.getOPPDistance();
String aszOPPDistance = "";
if (dOppDistance > -1){ 
   	DecimalFormat twoDForm = new DecimalFormat("#.##");
	dOppDistance = Double.valueOf(twoDForm.format(dOppDistance)).doubleValue();
	aszOPPDistance = dOppDistance + " miles";
} 

out.println("<!-- opp nid= "+ org.getOPPNID() +" -->");
out.println("<!--<br>" + aszSkills + " " + aszPositionType + " freqency: " + aszFrequency + " " +aszTripLength+ " " + aszGreatFor+ " " +aszBenefits + " " + aszWorkStudy+"<br>-->");
%>

<% if(iFeedsResults==1){ %>
<br><br>
<div id="feeds-results" style="border:1px solid #555; padding:0 10px;">

<div id="search-results">
<h2>Opportunities from Third Party Partner Websites:</h2>
<img style="padding:5px 10px 10px;" src="http://www.christianvolunteering.org/imgs/afg-blue_200.png" alt="AllForGood" width="200"/>
<hr />
</div>
<% } %>

<div id="search-results" class="<%=aszFeedsClass%>">
<% if( 	aszMobileSubdomain.length() < 3 ) { %>
	<div <%=aszMemberClass%>>
		<%iCounter++;%><%=iCounter%>.&nbsp;
		
		<A class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszOppTitlePrint%></A>
		<% if(feed==true){ %>
		&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>" width="60"/>
		<% }else{ %>
		&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
		<% } %>
		&nbsp;&nbsp;
		<A class="org_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
	
		<div class="teaser">
		<%out.print(org.getOPPDescTeaser());%>...
		<A class="more_link" href="<%=aszOPPUrlAlias%>">more ></A>
		</div>
	
		<div class="opp-info">
			<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
		
			<%=aszSTMDetails%>
		
			<br>
			<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
			<%=aszDatePrint%>
		
			<b>Date Last Updated:</b>&nbsp;<%out.print(date);%>
		</div>
		<% if (org.getOPPDistance()>-1){ %>
			<!--<%=aszOPPDistance%>-->
		<% } %>
	</div>
<% }else{ %>
	<A href="<%=aszOPPUrlAlias%>" class="item">
	<div <%=aszMemberClass%>>
		<%iCounter++;%><%=iCounter%>.&nbsp;
		
		<font class="opp_link"><%=aszOppTitlePrint%></font>
		&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
		<br>
		<%=aszOrgNamePrint%>
	
		<div class="opp-info">
			<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
			<br>
			<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
			<%=aszDatePrint%>
		</div>
		
		<% if (org.getOPPDistance()>-1){ %>
		<div class="opp-info">
			<%=aszOPPDistance%>
		</div>
		<% } %>
	</div>
	<span class="arrow"> </span>
	</A>
<% } %>
<hr />
</div>

			</logic:iterate>
		</logic:notEmpty>
<% if(iFeedsResults>0){ %>
</div>
<% } %>


<!-- portal listings? -->
<% if(true == LoginBean.IsSessionLoggedIn( request ) ){ %>
<!-- logged in -->
	<% if(aszPortal.length()>0  ){ %>
<!-- has portal -->
		<% if(	aszHostID.equalsIgnoreCase("volengchurch")	|| 
				aszHostID.equalsIgnoreCase("voleng1")		|| 
				aszHostID.equalsIgnoreCase("volengexample")	|| 
				aszHostID.equalsIgnoreCase("default")
		){ %>	
<!-- correct domain -->
			<% if(bSkipBanner==false){ %>
				<br>
					<div id="embedcollapse" onClick="toggle_embedcontent()" class=" collapsed">
						<legend class="collapse-processed">
							<span class="fieldset-legend">Embed on Your Site&nbsp;&nbsp;
			<!--a href="#embed_div" onClick="show_embed_div()">Embed on Your Site &gt;</a-->
							</span>
						</legend>
					</div>
				<%@include file="/template_include/embed_options.inc"%>
			<% } %>
		<% } %>
	<% } %>
<% } %>
		

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById("resultsNumber").innerHTML = '<b><%=iCounter%> results found:</b>';
 }); 
</script>
</div></div>
<!-- start sidebar information --><%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->

