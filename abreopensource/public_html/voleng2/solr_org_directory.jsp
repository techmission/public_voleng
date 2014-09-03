<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<%@page import="java.text.DecimalFormat" %>

<%
String aszLandingParamsURL="";
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
Volunteer Organizations</title>
<% 
	}else{ 
%>
Christian Volunteer and Short Term Missions Organizations</title>
<% 
	}
%>


<meta name="description" content="<%=aszDetails%> Search <%=aszOrgOrChurchOpp%> Organizations: <%=aszSubdomain%>" />
<meta name="keywords" content="<%=aszOrgName%>, <%=aszCountryName%> <%=aszRegionName%> volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism">

<%
}

String aszSearchTerms = "<input type=\"hidden\" name=\"method\" value=\"processSearch\" /> \n\r";
String aszSearchURL = "?method=processSearch";

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
  <div id="oppsearch" class="solr">
	  <span id="title">Organization Directory</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% } %>


<script language="javascript">
function toggle_display(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapse_class=" collapse";
	var expanded_class=" expanded";
	var e = document.getElementById(facet_field); 
	if(e.style.display=="none"){
		e.style.display="block";
		e.parentNode.className=default_class+handle_class+expanded_class;
	}else{
		e.style.display="none";
		e.parentNode.className=default_class+handle_class+collapse_class;
	}
}
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
	var root_facet_element = document.getElementById(id); 
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class;
		
		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
				if(list_element.style == undefined){
				}else{
					list_element.style.display="block";
				}
				list_element.className=default_class+expanded_class;
			}
		}
	}
}
</script>
<style>
#contentwrapper{
	background:#ECF1FC;
}

.collapsed{
	visibility:hidden;
}
.expanded{
	visibility:visible;
}

.filter li {
    font-size: 1em;
    line-height: 1.3em;
    list-style: none outside none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    position: relative;
    white-space: nowrap;
}

</style>

<div id="result">

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results directory">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Organization Directory</span>

<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; organization search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->
<br>


<div id="sorts">
</div>


<%//@ include file="/template_include/landing_page_facet_links.inc" %>
<%@ include file="/template_include/directory_page_facet_links.inc" %>

<br>
<ul class="filters" id="filters">		

	<li class="filter handle expanded directory" id="facet_service_areas">
		<a class="handle" onClick="toggle_display('service_areas')"><span class="i">&nbsp;</span> <span class="label">Service Area:</span></a>
					<ul id="service_areas" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("service_areas")){ 
						String aszFacets0 = "sa";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
		
	<li class="filter handle expanded directory" id="facet_primary_opp_type">
		<a class="handle" onClick="toggle_display('primary_opp_type')"><span class="i">&nbsp;</span> <span class="label">Organizations By Focus:</span></a>
					<ul id="primary_opp_type" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("primary_opp_type")){ 
						String aszFacets0 = "pot";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
	</li>
<!--		
	<li class="filter handle expanded directory" id="facet_position_type">
		<a class="handle" onClick="toggle_display('position_type')"><span class="i">&nbsp;</span> <span class="label">Position Types:</span></a>
					<ul id="position_type" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("position_type")){ 
						String aszFacets0 = "pt";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
-->
	<li class="filter handle expanded directory" id="facet_great_for">
		<a class="handle" onClick="toggle_display('great_for')"><span class="i">&nbsp;</span> <span class="label">Great for:</span></a>
					<ul id="great_for" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("great_for")){ 
						String aszFacets0 = "gf";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle expanded directory" id="facet_trip_length">
		<a class="handle" onClick="toggle_display('trip_length')"><span class="i">&nbsp;</span> <span class="label">Length of Trip:</span></a>
					<ul id="trip_length" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("trip_length")){ 
						String aszFacets0 = "tl";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
		
	<li class="filter handle expanded directory" id="facet_region">
		<a class="handle" onClick="toggle_display('region')"><span class="i">&nbsp;</span> <span class="label">Region:</span></a>
					<ul id="region" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("region")){ 
						String aszFacets0 = "r";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>		
</ul>
<br clear="all" />
<ul class="filters" id="filters">				
<% // expand if STM, though %>
	<li class="filter handle expanded" id="facet_adv">
		<a class="handle" onClick="toggle_display('advanced_facets')"><span class="i">&nbsp;</span> <span class="label">Advanced</span></a>
		<ul id="advanced_facets" class="filters" style="display:block;">
		
			<li class="filter handle expanded directory advanced" id="facet_country_tax">
				<a class="handle" onClick="toggle_display('country_tax')"><span class="i">&nbsp;</span> <span class="label">Country:</span></a>
					<ul id="country_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("country_tax")){ 
						String aszFacets0 = "ctr";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
		
			<li class="filter handle expanded directory advanced" id="facet_province_tax">
				<a class="handle" onClick="toggle_display('province_tax')"><span class="i">&nbsp;</span> <span class="label">Province:</span></a>
					<ul id="province_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("province_tax")){ 
						String aszFacets0 = "st";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
		
			<li class="filter handle expanded directory advanced" id="facet_city_tax">
				<a class="handle" onClick="toggle_display('city_tax')"><span class="i">&nbsp;</span> <span class="label">Metro Area:</span></a>
					<ul id="city_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("city_tax")){ 
						String aszFacets0 = "ct";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
				
<% // expand if org is selected, though %>
			<li class="filter handle expanded directory advanced" id="facet_denom_affil">
				<a class="handle" onClick="toggle_display('denom_affil')"><span class="i">&nbsp;</span> <span class="label">Denomination:</span></a>
					<ul id="denom_affil" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("denom_affil")){ 
						String aszFacets0 = "d";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>
</ul>
<br clear="all" />
<ul class="filters" id="filters">				
			<li class="filter handle expanded directory advanced" id="facet_org_affil">
				<a class="handle" onClick="toggle_display('org_affil')"><span class="i">&nbsp;</span> <span class="label">Organizational Affiliation:</span></a>
					<ul id="org_affil" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
				<% 
					if(facets[0].equals("org_affil")){ 
						String aszFacets0 = "na";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li> 
<!--				
<% // expand if org is selected, though %>
			<li class="filter handle expanded directory advanced" id="facet_afg">
				<a class="handle" onClick="toggle_display('source')"><span class="i">&nbsp;</span> <span class="label">Search from Third Party Sites:</span></a>
					<ul id="source" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
								<% if(facets[0].equals("source")){ %>
									<a href="/oppsrch.do?method=processSearch<%=aszFQParams%>&fq=content_type:organization&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;#<%=aszFQParams%>&fq=content_type:organization&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;"><%=facets[1]%> (<%=facets[2]%>)</a>
									<br> 
								<% } %>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
-->		
		</ul>
	</li>
</ul>

<!--
	<div id="nav">
		<ul id="pager"></ul>  <div id="pager-header"></div>
	</div>
	<br>
	<div id="docs"></div>
-->
</div>



<br><br>


<!-- portal listings? -->
<% if(true == LoginBean.IsSessionLoggedIn( request ) && true==false){ %>
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
		

</div></div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->

