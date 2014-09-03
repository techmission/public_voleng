<%@ include file="/template_include/topjspnologin1.inc" %>



<%@ page import="org.apache.commons.lang.StringUtils" %>

<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<%

String aszLandingParamsURL = "fq=org_nid:" + org.getORGNID();
String aszLandingParamsHash = "fq=org_nid:" + org.getORGNID();


int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, 
		vidWorkStudy=264, vidTripLength=263, vidPosFreq=268, vidSchedDate=269, vidFaith=281, vidBenefits=286,
		vidCauseTopic = 8,
		vidState=52, vidCity=15, vidCountry=261,// vidSubRegion=261, 
		vidRegion=38, 
		vidIntlVols=342, vidFaithSpec=341, vidPortalName=343, vidPrimaryOppTypes=349;

int iTemp0=0;
int iCount0=0;
String aszDisplay0="";

int[] a_iTypesOfOppsArray = org.getTypesOfOppsArray();
int[] a_iServiceAreasArray = org.getServiceAreasArray();
int[] a_iSkillsArray = org.getSkillsArray();
int[] a_iGreatForArray = org.getGreatForArray();
int[] a_iTripLengthArray = org.getTripLengthArray();

int iTypesOfOppsArraySize = a_iTypesOfOppsArray.length; 
int iServiceAreasArraySize = a_iServiceAreasArray.length; 
int iSkillsArraySize = a_iSkillsArray.length; 
int iGreatForArraySize = a_iGreatForArray.length; 
int iTripLengthArraySize = a_iTripLengthArray.length; 

int iOppDataSize = iTypesOfOppsArraySize + iServiceAreasArraySize + iSkillsArraySize + iGreatForArraySize + iTripLengthArraySize;

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aPrimaryOppTypesList = new  ArrayList ( 2 );
ArrayList aPositionTypesList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList aGreatForList = new  ArrayList ( 2 );
ArrayList aTripLengthList = new  ArrayList ( 2 );
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
aCodes.getTaxonomyCodeList( aPositionTypesList, vidPosType );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aPrimaryOppTypesList, vidPrimaryOppTypes );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( aGreatForList, vidVolInfo );
aCodes.getTaxonomyCodeList( aTripLengthList, vidTripLength );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO orgBRLO = null;
if(request.getAttribute("orgBRLO")!=null){
	orgBRLO = (com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO) request.getAttribute("orgBRLO");
}

int iSpiritDevelTID = 5239;
int iOtherProgramTID = 5236;
String aszMission="";
String aszDescription="";
String aszStatement="";
if(aszHostID.equalsIgnoreCase("volengmobile")){
	aszMission = org.formatForPrint(org.getORGMissionStatement(),35);
	aszDescription = org.formatForPrint(org.getORGOrgDescription(),35);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),35);
}else if( (aszHostID.equalsIgnoreCase( "voleng1" )) || 
		(aszHostID.equalsIgnoreCase( "volengivol" )) || 
 		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" ))
 ){ 
	aszMission = org.formatForPrint(org.getORGMissionStatement(),120);
	aszDescription = org.formatForPrint(org.getORGOrgDescription(),120);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),120);
}else {
	aszMission = org.formatForPrint(org.getORGMissionStatement(),75);
	aszDescription = org.formatForPrint(org.getORGOrgDescription(),75);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),75);
} 

String aszLocalAffil="display: none;";
if( (aszHostID.equalsIgnoreCase( "volengboston" ) || (aszHostID.equalsIgnoreCase( "volengnewengland" )))){
   aszLocalAffil="display: inline;";
}


boolean b_showDescription = true;
if(org.getORGMembertype().equalsIgnoreCase("Foundation")){ 
	if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	
		b_showDescription = true;
	}else{
		b_showDescription = false;
	}
}

float fLatitude = org.getLatitude(), fLongitude=org.getLongitude();
String aszCountry="",aszState="",aszCity="";
String aszCountryISO= org.getORGAddrCountryName();
String aszStateAbbrev = org.getORGAddrStateprov();
aszCity=org.getORGAddrCity();
if(null != aStateList){
	for(int index=0; index < aStateList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
		if(null == aAppCode) continue;
		String aszOptRqCode = aAppCode.getCSPStateCode();
		if(aszOptRqCode.equalsIgnoreCase( aszStateAbbrev ) ) {
			aszState=aAppCode.getCSPStateName();
		}
	}
}
if(null != aCountryList){
	for(int index=0; index < aCountryList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
		if(null == aAppCode) continue;
		String aszOptRqCode = aAppCode.getCTRIso();
		if(aszOptRqCode.equalsIgnoreCase( aszCountryISO ) ) {
			aszCountry=aAppCode.getCTRPrintableName();
		}
	}
}

String aszFullAddress = "";
String aszFullAddressTagged = "";

if(org.getORGAddrLine1() != null && !org.getORGAddrLine1().isEmpty()) {
	aszFullAddress += org.getORGAddrLine1() + "<br/>";
	aszFullAddressTagged += "<span itemprop=\"streetAddress\">" + org.getORGAddrLine1() + "</span><br/>";
}
if (aszCity.length()>1){
	aszFullAddress += aszCity;
	aszFullAddressTagged += "<span itemprop=\"addressLocality\">" + aszCity + "</span>";
	if ((aszCountry.length()>1 || aszState.length()>1)){
		aszFullAddress += ", ";
		aszFullAddressTagged += ", ";
	}
}
if(aszState != null && !aszState.isEmpty()){
	aszFullAddress += aszState + "<br/>";
	aszFullAddressTagged += "<span itemprop=\"addressRegion\">" + aszState + "</span>&nbsp;&nbsp;";
}
if(org.getORGAddrPostalcode() != null && !org.getORGAddrPostalcode().isEmpty() ){
	if(! 
		(
			org.getORGAddrPostalcode().equalsIgnoreCase("no postal") || 
			org.getORGAddrPostalcode().equalsIgnoreCase("na") || 
			org.getORGAddrPostalcode().equalsIgnoreCase("n/a")
		) 
	){
		aszFullAddress += org.getORGAddrPostalcode() + "<br/>";
		aszFullAddressTagged += "<span itemprop=\"postalCode\">" + org.getORGAddrPostalcode() + "</span>&nbsp;&nbsp;";
	}
}
if(aszCountry != null && !aszCountry.isEmpty()){
	if(aszFullAddressTagged.length()>0) aszFullAddressTagged += "<br />";
	aszFullAddressTagged += "<span itemprop=\"addresscountry\">" + aszCountry + "</span>";
	if(!( aszCountry.equalsIgnoreCase("us") || aszCountry.equalsIgnoreCase("united states") )){
		aszFullAddress += aszCountry;
	}else{
		aszFullAddress += "<br />";
	}	
}
if(aszFullAddressTagged.length()>0){
//	aszFullAddressTagged = "<span itemprop=\"address\" itemscope=\"\" itemtype=\"http://schema.org/PostalAddress\">" + aszFullAddressTagged +</span>";
}

aszCurrentURL = org.getORGUrlAlias()+".jsp";
%>

<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) 
){ 
	bHeaderSet=true;
	String aszShortenedSubdomain = aszSubdomain;
	if(aszSubdomain.startsWith("www.")){
		aszShortenedSubdomain = aszSubdomain.substring(4);
	}
	String aszTitle = org.getORGOrgName();
	if (aszCity.length()>1){
		aszTitle += ", " + aszCity;
	}
	if ( aszCountry.length()>1 ){
		if(aszCountryISO.equalsIgnoreCase("US")){
			if( aszState.length()>1 ){
				aszTitle += ", " + aszState;
			}else{
				aszTitle += ", " + aszCountry;
			}
		}else{
			aszTitle += ", " + aszCountry;
		}
	}
	aszTitle += ": Organizational Profile and Volunteer Opportunities: " + aszShortenedSubdomain;
	String aszMetaDescription = org.getORGOrgName() + ": " + org.getORGMissStmntTeaser() + " Organizational Profile and Volunteer Opportunities: " + aszShortenedSubdomain;
	String aszMetaKeywords = org.getORGOrgName() + ", " + aszCity + ", " + aszState + ", " + aszCountry + ", ";
	if(org.getORGPartner().length()>0) {
		aszMetaKeywords += org.getORGPartner()+", ";
	}
	if( aszSecondaryHost.equalsIgnoreCase("volengivol") ){ 
	}else{

if( iServiceAreasArraySize > 0){
					iTemp0=0;
					iCount0=0;
					for(int index=0; index < aServiceList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iServiceAreasArray.length; indexArray++){
							iTemp0 = a_iServiceAreasArray[indexArray];
							aszDisplay0 = aAppCode.getAPCDisplay();
							if(iTemp0==iSubType){
								if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4788){
									aszDisplay0 = "Visitation / Friendship";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4763){
									aszDisplay0 = "Children and Youth";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4767){
									aszDisplay0 = "Disabilities Outreach";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 6843){
									aszDisplay0 = "Senior / Elderly Outreach";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4774){
									aszDisplay0 = "Food Service / Hunger";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4782){
									aszDisplay0 = "Prison Outreach";
								}
		aszMetaKeywords += aszDisplay0;
								iCount0++;
								if(iCount0 < iServiceAreasArraySize){
									aszMetaKeywords += ", ";
								}
							}
						}
					}
}
if( iSkillsArraySize > 0){
					iTemp0=0;
					iCount0=0;
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iSkillsArray.length; indexArray++){
							iTemp0 = a_iSkillsArray[indexArray];
							if(iTemp0==iSubType){
								aszDisplay0 = aAppCode.getAPCDisplay();
								if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& (
									iSubType == 4748 ||
									iSubType == 4749
								)){
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4745){
									aszDisplay0 = "Musician";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 8122){
									aszDisplay0 = "Deaf Services";
								}
		aszMetaKeywords += aszDisplay0;
								iCount0++;
								if(iCount0 < iSkillsArraySize){
									aszMetaKeywords += ", ";
								}
							}
						}
					}
}

	}
	aszMetaKeywords += "volunteer, Christian, church, catholic, mission, foundation, internships, Christian volunteer, church volunteer, nonprofit, ministry, missions trip, short term missions, soup kitchen, orphanages, missionary, charities, ministry, ministries, virtual volunteer, online volunteering, volunteer online";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="image_src" href="/imgs/logo.gif"/>
	<title><%=aszTitle%></title>

<% // canonical links: http://www.seomoz.org/blog/canonical-url-tag-the-most-important-advancement-in-seo-practices-since-sitemaps %>
<% if(
		showPortalInfo==true	&& 
		aszPortal.length()>0 	&& 
		org.getORGUID()==iPortalUID
){
	aszCanonicalLink = "http://"+aszSubdomain+aszPortal+"/"+org.getORGUrlAlias()+".jsp";
%>
	<link rel="canonical" href="http://www.christianvolunteering.org<%=aszPortal%>/<%=org.getORGUrlAlias()%>.jsp" />
<% }else{ 
	aszCanonicalLink = "http://"+aszSubdomain+"/"+org.getORGUrlAlias()+".jsp";
%>
	<link rel="canonical" href="http://www.christianvolunteering.org/<%=org.getORGUrlAlias()%>.jsp" />
<% } %>

<meta property="og:title" content="<%=org.getORGOrgName()%><% if (aszCity.length()>1){	out.print(", ");}%><%=aszCity%><% if ( (aszCountry.length()>1) || (aszState.length()>1) ){	out.print(", ");}%>
<%
	if(aszCountryISO.equalsIgnoreCase("US")){
		out.print("" + aszState);
	} else {
		out.print("" + aszCountry);
	}
%>" />
<meta property="og:description" content="<%=org.getORGMissionStatement()%>" />
<meta property="og:url" content="http://christianvolunteering.org/<%=org.getORGUrlAlias()%>.jsp" />
<meta property="og:site_name" content="ChristianVolunteering.org" />
<meta property="og:image" content="at http://www.christianvolunteering.org/imgs/christianvolunteering-square-4b.png" />

<meta name="description" content="<%=aszMetaDescription%>">
<meta name="keywords" content="<%=aszMetaKeywords%>">

<meta name="ICBM" content="<% out.print(fLatitude + "," + fLongitude);%>">

<% 
String aszMetaRobots = "noindex, nofollow, noarchive";
if(aszHostID.equalsIgnoreCase("voleng1")){
	aszMetaRobots = "index, follow, noarchive";
}
%>
<meta name="robots" content="<%=aszMetaRobots%>" />
<% } %>

<%@ include file="/template/header.inc" %>
<!-- end header information -->

<style>
.button {
font: bold 11px Arial;
text-decoration: none;
background-color: #EEEEEE;
color: #333333;
padding: 2px 6px 2px 6px;
border-top: 1px solid #CCCCCC;
border-right: 1px solid #333333;
border-bottom: 1px solid #333333;
border-left: 1px solid #CCCCCC;
}
</style>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
String aszFaith="display:inline;";
if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){
	aszFaith="display:none;";
}

int iTemp=0;
int iCount=0;
String aszDisplay="";
%>

<% if(fLatitude != 0.0 && fLongitude != 0.0){ %>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=yes" />
    <style type="text/css">
      #map_canvas { 
      	width: 300px; 
      	height: 270px; 
      	position: relative; 
      }
    </style>
<% } %>
<script type="text/javascript">
$(document).ready(function() {
	<% //if(org.getORGHasListings().equalsIgnoreCase("yes")) { %>
	<% if(org.getORGNumOpps()>0) { %>
    window.location.hash='fq=content_type:opportunity&fq=org_nid:<%=org.getORGNID()%>'; 
    <% } %>
    $('a#org').click(function(e) { 
      tab_selected('org');
      e.preventDefault();
    });
    $('a#opps').click(function(e) { 
      tab_selected('opps');
      e.preventDefault();
    });
    $('a#guidestar').click(function(e) { 
      tab_selected('guidestar');
      e.preventDefault();
    });
    $('#guidestar_content').hide();
    $('#opps_content').hide();
    $('#org_content').show();
<% if(fLatitude != 0.0 && fLongitude != 0.0){ %>
	var myLatlng = new google.maps.LatLng(<%=fLatitude%>,<%=fLongitude%>);
    var infowindow = new google.maps.InfoWindow();
	var myOptions = {
	  zoom: 14,
	  center: myLatlng,
	  mapTypeId: google.maps.MapTypeId.ROADMAP,
	}
	var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	var marker = new google.maps.Marker({
	    position: myLatlng,
	    title:"<%=org.getORGOrgName()%>"
	});

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent('<%=org.getORGOrgName()%><br /><br /><%=aszFullAddress%><a style="position: static; overflow: visible; float: right; padding-right:25px; display: inline; " target="_blank" href="http://maps.google.com/maps?q=<%=fLatitude%>,<%=fLongitude%>&amp;hq=&amp;hnear=<%=fLatitude%>,<%=fLongitude%>&amp;z=14&amp;t=m"><div style="cursor: pointer; ">Google maps</div></a>');
    infowindow.open(map, marker)
  });
  google.maps.event.addListener(infowindow, 'closeclick', function() {
    map.setCenter(myLatlng);
  });
	// To add the marker to the map, call setMap();
	marker.setMap(map);
<% } %>
});
</script>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="page_title"><%=aszOrgOrChurch%> Profile</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="page_title"><%=aszOrgOrChurch%> Profile</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>


<div style="display:none;">

<rdf:RDF xmlns:rdf=http://www.w3.org/1999/02/22-rdf-syntax-ns# xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"> <geo:Point>

<geo:lat><%=fLatitude%></geo:lat>

<geo:long><%=fLongitude%></geo:long>

</geo:Point> </rdf:RDF>

</div>

<!-- primary contact user http://www.urbanministry.org/user/<%=org.getORGUID()%>/edit -->

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;"><%=aszOrgOrChurch%> Profile</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/advancedsearch.jsp">search</a> &gt; <%=aszOrgOrChurch.toLowerCase()%> profile</div>
</div>
<% } %>


  <div id="body">
  <!-- titles -->
<br>
						<table border="0" width=90%>

							<tr>
								<td colspan=4 width=85%><meta itemprop="title" content="<bean:write name="orgForm" property="orgname"/>" /><h1 itemprop="name"><bean:write name="orgForm" property="orgname"/><% if (aszCity.length()>1){	out.print(", ");}%><%=aszCity%><% if ( (aszCountry.length()>1) || (aszState.length()>1) ){	out.print(", ");}%>
<%
	if(aszCountryISO.equalsIgnoreCase("US")){
		out.print("" + aszState);
	} else {
		out.print("" + aszCountry);
	}
%>
</h1></td>
								<td></td>
								<td valign="top" align="right">
								<h3>
									<font class="link"> [ <a href="#" onClick="window.history.back(); return false;">Back</a>]</font>
								</h3>
								</td>
			
				    		</tr>
				    	</table>

<div class="results-left" id="all_tabs" style="margin-bottom: 15px;">
	<div class="results-left"><a href="#" id="org" class="active">Organization Details</a></div>
	<% //if(org.getORGHasListings().equalsIgnoreCase("yes")) { %>
	<% if(org.getORGNumOpps()>0) { %>
	<div class="results-left"><a href="#" id="opps" class="">Service Opportunities</a></div>
	<% } %>	
	<% if(org.getIsIRSCertified()) { %>
	  <div class="results-left"><a href="#" id="guidestar" class="">Guidestar Profile</a></div>
	<% } %>	
</div>

<HR width=100% size="2" color="4d4d4d" margin-top="0px;" />
      	<div align="left" itemscope itemtype="http://schema.org/NGO">
      	<div id="org_content">
<div>
	<div>
		<div>
			
	
						
						
			<!-- invite notice -->

			
			
				<table class="organizationdetail" border="0">
				<tr>
					<td class="left">
						<table class="info" border="0" cellpadding="2" cellspacing="0">
						<% if(org.getORGWebpage().length()>1){ %>
							<tr>
								<td></td><th  align="right" valign="top">Web:</th><td></td>
								<td>
								<a href="<bean:write name="orgForm" property="orgwebpage" />" itemprop="url""><bean:write name="orgForm" property="orgwebpage"/></a>
								</td>
							</tr>
						<% } %>
							<tr>
								<td></td><th  align="right" valign="top">Address:</th><td></td>
								<td itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">
								    <%=aszFullAddressTagged%>
<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
<br />
<a href="#narrowMap" onClick="document.getElementById('narrowMap').style.display='inline';" >map location</a>
<br />
<% } %>
								</td>
							</tr>
							
<% if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	%>
							<tr>
								<td></td><th  align="right" valign="top">Phone:</th><td></td>
								<td><span itemprop="telephone"><bean:write name="orgForm" property="phone1"/></span><br /></td>
							</tr>
<% 	if(org.getORGOrgPhone2().length()>0){ %>							
							<tr>
								<td></td><td></td>><td></td>
								<td>
									<span itemprop="telephone"><%=org.getORGOrgPhone2()%></span>
									<br />
								</td>
							</tr>
<%
	}
}
if(org.getORGFax1().length()>0){ %>							
							<tr>
								<td></td><th  align="right" valign="top">Fax:</th><td></td>
								<td><span itemprop="faxNumber"><bean:write name="orgForm" property="fax1"/></span><br /></td>
							</tr>
<% 
}
if(org.getIsIRSCertified()){ %>							
							<tr>
								<td></td><th align="right" valign="top">Is 501(c)(3) nonprofit certified by the IRS?:</th><td></td>
								<td>Yes</td>
							</tr>
<% } 
if(org.getORGNumVolOrg()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Number of Volunteers Serving Nationally:</th><td></td>
								<td>
									<%=org.getORGNumVolOrg()%>
								</td>
							</tr>
<% } 
if(org.getORGNumVolOrgIntl()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Number of Volunteers Serving Internationally:</th><td></td>
								<td>
									<%=org.getORGNumVolOrgIntl()%>
								</td>
							</tr>
<% }
if(org.getORGNumServed()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Number of People Being Served:</th><td></td>
								<td>
									<%=org.getORGNumServed()%>
								</td>
							</tr>
<% }
if(org.getGeoFocus().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Geographical Focus:</th><td></td>
								<td>
                                                                        <% out.print(org.getGeoFocus().replaceAll(";",", "));%>
								</td>
							</tr>
<% }
if(org.getFundingLimitations().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Funding Limitations:</th><td></td>
								<td>
									<%=org.getFundingLimitations()%>
								</td>
							</tr>
<% }
if(org.getTypeOfFunder().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Type of Funder:</th><td></td>
								<td>
									<%=org.getTypeOfFunder()%>
								</td>
							</tr>
<% }
if(org.getORGFedTaxId().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">EIN:</th><td></td>
								<td>
									<%=org.getORGFedTaxId()%>
								</td>
							</tr>
<% }
if(org.getYearFounded()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Year Founded:</th><td></td>
								<td>
									<%=org.getYearFounded()%>
								</td>
							</tr>
<% }
if(org.getAssets().length()>0 && (! org.getAssets().contains("$0"))){ %>
							<tr>
								<td></td><th  align="right" valign="top">Assets:</th><td></td>
								<td>
									<%=org.getAssets()%>
								</td>
							</tr>
<% }
if(org.getIncome().length()>0 && (! org.getIncome().contains("$0"))){ %>
							<tr>
								<td></td><th  align="right" valign="top">Income:</th><td></td>
								<td>
									<%=org.getIncome()%>
								</td>
							</tr>
<% }
if(org.getExpenditures().length()>0 && (! org.getExpenditures().contains("$0"))){ %>
							<tr>
								<td></td><th  align="right" valign="top">Expenditures:</th><td></td>
								<td>
									<%=org.getExpenditures()%>
								</td>
							</tr>
<% }
if(org.getTotalGiving().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">Total Giving:</th><td></td>
								<td>
									<%=org.getTotalGiving()%>
								</td>
							</tr>
<% }
if(org.getNTEEMajorCat().length()>0){ %>
							<tr>
								<td></td><th  align="right" valign="top">NTEE Major Categories:</th><td></td>
								<td>
									<%=org.getNTEEMajorCat()%>
								</td>
							</tr>
<% }
if(org.getNTEEMinorCat().length()>0){ %>
                                                        <tr>
                                                                <td></td><th  align="right" valign="top">NTEE Minor Categories:</th><td></td>
                                                                <td>
                                                                        <%=org.getNTEEMinorCat()%>
                                                                </td>
                                                        </tr>
<% } %>



						</table>
					</td>
<% 
if(	(!(aszNarrowTheme.equalsIgnoreCase("true")	))	&&
		(	
				(fLatitude != 0.0 && fLongitude != 0.0)			||
				(aszCity.length()>0 && aszState.length()>0)		||
				(aszCity.length()>0 && aszCountry.length()>0)
		)
){ 
%>
							<td height="270" width="300">
<% if(fLatitude != 0.0 && fLongitude != 0.0){ %>
<div id="map_canvas" width="300" height="270"></div>
<% }else if(aszCity.length()>0 && aszState.length()>0){ %>
<iframe width="300" height="270" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;key=AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=<%=aszCity%>,<%=aszState%>&amp;aq=&amp;sll=&amp;sspn=0.006659,0.009645&amp;ie=UTF8&amp;hq=&amp;hnear=<%=aszCity%>,<%=aszState%>&amp;t=m&amp;ll=&amp;spn=0.068774,0.102654&amp;z=12&amp;iwloc=&amp;output=embed"></iframe>
<br />
<small><a href="http://maps.google.com/maps?f=q&amp;key=AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=<%=aszCity%>,<%=aszState%>&amp;aq=&amp;sll=&amp;sspn=0.006659,0.009645&amp;ie=UTF8&amp;hq=&amp;hnear=<%=aszCity%>,<%=aszState%>&amp;t=m&amp;ll=&amp;spn=0.068774,0.102654&amp;z=12&amp;iwloc=&amp;" style="color:#0000FF;text-align:left" target="_new">View Larger Map</a></small>
<% }else if(aszCity.length()>0 && aszCountry.length()>0){ %>
<iframe width="300" height="270" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;key=AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=<%=aszCity%>,<%=aszCountry%>&amp;aq=&amp;sll=&amp;sspn=0.006659,0.009645&amp;ie=UTF8&amp;hq=&amp;hnear=<%=aszCity%>,<%=aszCountry%>&amp;t=m&amp;ll=&amp;spn=0.068777,0.102654&amp;z=12&amp;iwloc=&amp;output=embed"></iframe>
<br />
<small><a href="http://maps.google.com/maps?f=q&amp;key=AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=<%=aszCity%>,<%=aszCountry%>&amp;aq=&amp;sll=&amp;sspn=0.006659,0.009645&amp;ie=UTF8&amp;hq=&amp;hnear=<%=aszCity%>,<%=aszCountry%>&amp;t=m&amp;ll=&amp;spn=0.068777,0.102654&amp;z=12&amp;iwloc=&amp;" style="color:#0000FF;text-align:left" target="_new">View Larger Map</a></small>
<% } %>
							
							</td>
<% } %>

				</tr>
			</table>
					<div class="clear" style="height: 5px;"></div>

  					<div id="navcontainer">
					</div>
				</div>
			</div>
		
<table border=0><tr><td>
<% 
int iTemp2=org.getORGOrgNumber();  
String aszTemp2=iTemp2+""; 
int iTemp3=org.getORGNID();  
String aszTemp3=iTemp3+""; 

String aszOrgNameURL = org.getORGOrgName().replaceAll(" ","_");
aszOrgNameURL = aszOrgNameURL.replaceAll("/","|");
aszOrgNameURL = aszOrgNameURL.replaceAll("&", "~");
aszOrgNameURL = aszOrgNameURL.replaceAll("\\.", ";");

if( org.getSource().length()<1 || org.getSource().startsWith("bil_") ){


if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) || (aszPortal.length()>0 && !(aszPortal.contains("voleng")) && !(aszPortal.contains("cityvision")))){
%> 
	<form name="searchForm" action="<%=aszPortal%>/oppsrch.do?" method="get"> 
	<input type="hidden" name="method" value="processOppSearchAll" /> 
	<input type="hidden" name="orgnid" value="<%=aszTemp3%>"/> 
	<% if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ // don't show faith-related fields or taxonomy values %>
	<input type="hidden" name="requiredcodetype" value="No">
	<% } %>
	<INPUT type=hidden name=submit><INPUT class="button" type=submit value="View this <%=aszOrgOrChurch%>'s Opportunities"> 
	</form>
<% }else if(org.getORGNumOppsPublished() > 0){ %>
<% 		if(org.getCVInternSiteApproved().equals("City Vision")){ %>
	<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=organization_name:&quot;<%=org.getORGOrgName()%>&quot;&fq=org_nid:<%=org.getORGNID()%>&fq=content_type:opportunity&fq=intern_type:&quot;City Vision Internship&quot;#fq=organization_name:&quot;<%=org.getORGOrgName()%>&quot;&fq=org_nid:<%=org.getORGNID()%>&fq=content_type:opportunity&fq=intern_type:&quot;City Vision Internship&quot;" class="button">View this <%=aszOrgOrChurch%>'s Opportunities</a>
<% 		}else{ %>
	<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=organization_name:&quot;<%=org.getORGOrgName()%>&quot;&fq=org_nid:<%=org.getORGNID()%>&fq=content_type:opportunity#fq=organization_name:&quot;<%=org.getORGOrgName()%>&quot;&fq=org_nid:<%=org.getORGNID()%>&fq=content_type:opportunity" class="button">View this <%=aszOrgOrChurch%>'s Opportunities</a>
<% 		} %>
<% } %>
	</td><td>
	<a href="http://www.urbanministry.org/node/<%=org.getORGNID()%>#comments" class="button"<% if(aszHostID.equalsIgnoreCase("volengfacebook") || aszHostID.equalsIgnoreCase("volengfacebookivol")){%> target="_new"<% } %>>Recommend this <%=aszOrgOrChurch%></a>
<% } %>
	</td>

<% if(orgBRLO!=null){
if(orgBRLO.getORGDonateURLFallBackToGuideStar(org).length() > 10){ %>
<td>
<a href="<%=orgBRLO.getORGDonateURLFallBackToNetworkForGood(org)%>" class="button">
<% 
// test to see if it's from a URL that has a narrower template
 if(aszNarrowTheme.equalsIgnoreCase("true")){ 
%>
Donate Now
<% } else { %>
Donate to this <%=aszOrgOrChurch%>
<% } %></a>
</td>
<% } }%>

</tr></table>

</div>

<% if(aszNarrowTheme.equalsIgnoreCase("true")){ // have the option to show the iframe here if it's a narrow theme %>
<div id="narrowMap" style="display:none;">
      <HR width="100%">
<br>							
<iframe src="http://www.urbanministry.org/node/<%=org.getORGNID()%>/show-map/notheme" width="320" height="320" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" allowtransparency="true">
  <p><a href="http://www.urbanministry.org/node/<%=org.getORGNID()%>/show-map">View map</a></p>
</iframe>
<br>
							
</div>
<% } %>
     
<% 
if( org.getSource().length()<1 ){
if( org.getORGHasListings().equalsIgnoreCase("Yes") ){
	String aszListingsURL =  org.getORGListingsURL();
	if(aszListingsURL.startsWith("http://")){
	}else{
		aszListingsURL = "http://"+aszListingsURL;
	}
%>
<a href="<%=aszListingsURL%>">View More Listings on <%=aszOrgOrChurch%>'s Website</a>
<% } %>

<% if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	
%>
<table><tr><td>
<form id="favorites" method="post" action="http://www.urbanministry.org/add-favorites"<% if(aszHostID.equalsIgnoreCase("volengfacebook") || aszHostID.equalsIgnoreCase("volengfacebookivol")){%> target="_new"<% } %>>     
<input type="hidden" id="nid" name="nid" value="<%=org.getORGNID()%>">
<input type="hidden" id="title" name="title" value="<%=org.getORGOrgName()%>">
<input type="hidden" id="type" name="type" value="Organization">
<input type="submit" value="Add to Favorites" name="submit" style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;">
</form>       
</td><td></td><td>
<form id="abuse" method="post" action="http://www.urbanministry.org/report-abuse"<% if(aszHostID.equalsIgnoreCase("volengfacebook") || aszHostID.equalsIgnoreCase("volengfacebookivol")){%> target="_new"<% } %>>     
<input type="hidden" id="nid" name="nid" value="<%=org.getORGNID()%>">
<input type="hidden" id="title" name="title" value="<%=org.getORGOrgName()%>">
<input type="hidden" id="type" name="type" value="Organization">
<input type="submit" value="Report Abuse" name="submit" style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;">
</form>   
</td></tr></table>    
<% } else { %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;font-weight:normal;">Login to Add to Favorites or Report Abuse</a>
<% }} %>

<HR width="100%">							

<div id="textareaformat">

<% if( true == b_showDescription ){	%>

<% if(aszMission.length()>0){ %>	
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Mission Statement</h3>	<br />
<%=aszMission%>
<br />
<br />
<% } 
if(aszDescription.length()>0){ %>
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Description</h3><br />
<%=aszDescription%>

<br />
<br />
<% } %>

<span style="display:none" itemprop="description">
  <p>
    <%=aszDescription%>
  </p>
  <p>
    Mission Statement: <%=aszMission%>
  </p>
</span>

<% if(aszStatement.length() > 1){ %>
<div style="<%=aszFaith%>">
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Statement of Faith</h3><br />
<%=aszStatement%>
</div>
<br />
<br />
<% } %>


<% }else{ %>
<h2><a href="<%=request.getContextPath() %>/login.jsp">Login</a> or <a href="<%=request.getContextPath() %>/individualregistration.jsp">Create an Account</a> to View Full Details</h2>
<br />
<% } %>

</div>


<% if(org.getDenomAffilsPublic().length() > 1){ %>
<div style="<%=aszFaith%>">
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Denominational Affiliations</h3><br>
<p><%=org.getDenomAffilsPublic()%></p>
<br>
</div>
<% } %>

<div style="<%=aszFaith%>">

<div style="<%=aszLocalAffil%>">
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Local Affiliation</h3><br>
<p><bean:write name="orgForm" property="localaffil"  /></p>
<br>
</div>


<% if(org.getOrgAffiliations().length() > 1){ %>
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Affiliations</h3><br>
<p><%=org.getOrgAffiliations()%></p>
<br>
<% } %>
</div>

<% if(org.getORGProgramTypes().length()>0){ %>
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Program Type:</h3><br>
<% if(
	aszSecondaryHost.equalsIgnoreCase("volengivol") && 
	org.getORGProgramTypes1TID()==iSpiritDevelTID
){ }else{ 
	out.print(org.getORGProgramTypes());
} %>
<br /><br />
<% } %>

<% if(org.getORGFormalTrain().length()>0){ %>
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Do you Require Formal Orientation Training?</h3><br>
<%=aszTemp = org.getORGFormalTrain()%>
<br /><br />
<% } %>

<%
iTemp=0;
iCount=0;
aszDisplay="";

if(iOppDataSize > 0 && org.getORGNumOppsPublished() > 0 ){
%>

<div id="oppdata">

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Types of Opportunities:</h3><br>

<div style="padding-left: 10px;">
<%
	if( iTypesOfOppsArraySize > 0){
%>							
	<b>Primary Opportunity Types:</b><br />
	<div style="padding-left:5px;">
<%
					iTemp=0;
					iCount=0;
					for(int index=0; index < aPrimaryOppTypesList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPrimaryOppTypesList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						aszDisplay = aAppCode.getAPCDisplay();
						if(iSubType==32162){
							aszDisplay = "Local Volunteer Opportunities";
						}else if(iSubType==32163){
							aszDisplay = "Volunteer Opportunities Nationally";
						}else if(iSubType==32164){
							aszDisplay = "Hosting Foreign Interns or Short Term Missionaries";
						}else if(iSubType==32165){
							aszDisplay = "Virtual Volunteering (from home)";
						}
						for(int indexArray=0; indexArray < a_iTypesOfOppsArray.length; indexArray++){
							iTemp = a_iTypesOfOppsArray[indexArray];
							if(iTemp==iSubType){
								out.print(aszDisplay);
								iCount++;
								if(iCount < iTypesOfOppsArraySize){
									out.print(", ");
								}
							}
						}
					}
%>
	</div>
	<br />
<% 
	} 
	if( iServiceAreasArraySize > 0){
%>							
	<b>Service Areas:</b><br />
	<div style="padding-left:5px;">
<%
					iTemp=0;
					iCount=0;
					for(int index=0; index < aServiceList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iServiceAreasArray.length; indexArray++){
							iTemp = a_iServiceAreasArray[indexArray];
							aszDisplay = aAppCode.getAPCDisplay();
							String term = aszDisplay;
							if(iTemp==iSubType){
								if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4788){
									aszDisplay = "Visitation / Friendship";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4763){
									aszDisplay = "Children and Youth";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4767){
									aszDisplay = "Disabilities Outreach";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 6843){
									aszDisplay = "Senior / Elderly Outreach";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4774){
									aszDisplay = "Food Service / Hunger";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4782){
									aszDisplay = "Prison Outreach";
								}
%>
								<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=org_nid:<%=org.getORGNID()%>&fq=service_areas:&quot;<%=term%>&quot;#fq=org_nid:<%=org.getORGNID()%>&fq=service_areas:&quot;<%=term%>&quot;"><%=aszDisplay%></a>					
<%
								iCount++;
								if(iCount < iServiceAreasArraySize){
									out.print(", ");
								}
							}
						}
					}
%>
	</div>
	<br />
<% 
	}
	if( iSkillsArraySize > 0){
%>							
	<b>Skills:</b><br />
	<div style="padding-left:5px;">
<%
					iTemp=0;
					iCount=0;
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iSkillsArray.length; indexArray++){
							iTemp = a_iSkillsArray[indexArray];
							if(iTemp==iSubType){
								aszDisplay = aAppCode.getAPCDisplay();
								String term = aszDisplay;
								if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& (
									iSubType == 4748 ||
									iSubType == 4749
								)){
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 4745){
									aszDisplay = "Musician";
								}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& iSubType == 8122){
									aszDisplay = "Deaf Services";
								}
								
								%>
								<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=org_nid:<%=org.getORGNID()%>&fq=skills:&quot;<%=term%>&quot;#fq=org_nid:<%=org.getORGNID()%>&fq=skills:&quot;<%=term%>&quot;"><%=aszDisplay%></a>					
<%
								iCount++;
								if(iCount < iSkillsArraySize){
									out.print(", ");
								}
							}
						}
					}
%>
	</div>
	<br />
<% 
	}
	if( iGreatForArraySize > 0){
%>							
	<b>Great For:</b><br />
	<div style="padding-left:5px;">
<%
					iTemp=0;
					iCount=0;
					for(int index=0; index < aGreatForList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aGreatForList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iGreatForArray.length; indexArray++){
							iTemp = a_iGreatForArray[indexArray];
							if(iTemp==iSubType){
								aszDisplay = aAppCode.getAPCDisplay();
%>
								<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=org_nid:<%=org.getORGNID()%>&fq=great_for:&quot;<%=aszDisplay%>&quot;#fq=org_nid:<%=org.getORGNID()%>&fq=great_for:&quot;<%=aszDisplay%>&quot;"><%=aszDisplay%></a>					
<%
								iCount++;
								if(iCount < iGreatForArraySize){
									out.print(", ");
								}
							}
						}
					}
%>
	</div>
	<br />
<% 
	}
	if( iTripLengthArraySize > 0){
%>							
	<b>Length of Trip:</b><br />
	<div style="padding-left:5px;">
<%
					iTemp=0;
					iCount=0;
					for(int index=0; index < aTripLengthList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aTripLengthList.get(index);
						if(null == aAppCode) continue;
						int iSubType = aAppCode.getAPCIdSubType();
						for(int indexArray=0; indexArray < a_iTripLengthArray.length; indexArray++){
							iTemp = a_iTripLengthArray[indexArray];
							if(iTemp==iSubType){
								aszDisplay = aAppCode.getAPCDisplay();
%>
								<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=org_nid:<%=org.getORGNID()%>&fq=trip_length:&quot;<%=aszDisplay%>&quot;#fq=org_nid:<%=org.getORGNID()%>&fq=trip_length:&quot;<%=aszDisplay%>&quot;"><%=aszDisplay%></a>					
<%
								iCount++;
								if(iCount < iTripLengthArraySize){
									out.print(", ");
								}
							}
						}
					}
%>
	</div>
	<br />
<% 
	}
%>
</div>
<br />
</div>
<% }


else{

	 

	if(org.getServiceAreas().length()>0){
%>							
	<b><% if(org.getORGMembertype().equalsIgnoreCase("Foundation")){ %>Funding Interests<% }else{ %>Service Areas<% } %>:</b><br />
	<div style="padding-left:5px;">
<%
		String aszServiceAreas = org.getServiceAreas().replaceAll(", ",";");
		int iCountSemicolon = StringUtils.countMatches(aszServiceAreas, ";")+1;
		String[] a_aszServiceAreas = new String[iCountSemicolon];
		int i=0, iSeparator=0;
		try{
			String aszServiceAreasTemp = aszServiceAreas;
			for(i=0; i<= iCountSemicolon; i++){
				if(aszServiceAreasTemp.contains(";")){
//					out.print("aszServiceAreasTemp is "+aszServiceAreasTemp+"<br />");
					iSeparator = aszServiceAreasTemp.indexOf(";");
//					out.print("i is "+i+"; iSeparator is "+iSeparator+"<br />");
					a_aszServiceAreas[i] = aszServiceAreasTemp.substring(0,iSeparator);
					aszServiceAreasTemp = aszServiceAreasTemp.substring(iSeparator+1);
				}else{
//					out.print("no ;   i is "+i+"<br />");
					a_aszServiceAreas[i]=aszServiceAreasTemp;
					break;
				}
			}
		}catch(Exception e){
			out.print("caught exception: "+e+"; i is "+i+"; iSeparator is "+iSeparator+"<br />");
		}
		// iterate through the String array to list out terms
		for(i=0; i<a_aszServiceAreas.length;i++){
			String term = a_aszServiceAreas[i];
			aszDisplay = term;
			if (aszSecondaryHost.equalsIgnoreCase("volengivol")&& term.equals("Visitation/Pastoral Care") ){
				aszDisplay = "Visitation / Friendship";
			}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&&  term.equals("Children and Youth Ministry") ){
				aszDisplay = "Children and Youth";
			}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&&  term.equals("Disabilities Ministry") ){
				aszDisplay = "Disabilities Outreach";
			}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&&  term.equals("Senior Ministry / Elder Care") ){
				aszDisplay = "Senior / Elderly Outreach";
			}else if (aszSecondaryHost.equalsIgnoreCase("volengivol")&&  term.equals("Prison Ministry") ){
				aszDisplay = "Prison Outreach";
			}
			
			%>
			<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&fq=org_nid:<%=org.getORGNID()%>&fq=service_areas:&quot;<%=aszDisplay%>&quot;#fq=org_nid:<%=org.getORGNID()%>&fq=service_areas:&quot;<%=aszDisplay%>&quot;"><%=aszDisplay%></a>					
<%
			if(i < a_aszServiceAreas.length-1){
				out.print(", ");
			}
		}
		%></div><% 
	}
}



%>












</div>
<div id="opps_content">
<div id="" class="search_results" style="padding:0px 20px;">
<% 
String aszFQParams=aszLandingParamsHash;
String aszFQParamsURL=aszLandingParamsURL;
String aszOrgNamePrint="";
String aszOppTitlePrint="";
String aszContentType="";
String aszKewordsSpaceSeparated="";
int iCounter=0;
//String DecimalFormat="";
%>

<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search">org_nid:<%=org.getORGNID()%></div>
	<div id="fq_search">org_nid:<%=org.getORGNID()%></div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQParams is <%=aszFQParams%>
</div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>

<%@ include file="/template_include/search_sidebar.inc" %>
</div><!-- end solr_results div -->

<br clear="all"/>


<div class="results-left" id="all_tabs" style="display: none;">
	<div id="position_type" style="display: none;">
		<div class="results-left" id="position_type_local" style="display: none;">
		</div>
		<div class="results-left" id="position_type_virtual" style="display: none;">
        </div>
	</div>
    
		<div class="results-left" id="position_type_stm" style="display:none;">
			<a class="" id="stm" href="">Featured Christian Internships</a>
		</div>
	
	<div id="content_type" style="display: none;">
		<div class="results-left" id="content_type_job_tab" style="display: none;">
			<a class="" id="job" href="">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab" style="display: none;">
			<a class="" id="organization" href="">Organizations</a>
		</div>
		<div class="results-left" id="content_type_res_tab" style="display: none;">
			<a class="" id="resume" href="">Resum&eacute;s</a>
		</div>
	</div>
	
	<div id="member_type" >
		<div class="results-left" id="member_type_fdn_tab" >
			<a class="" id="Foundation" href="/oppsrch.do?method=processSearch&<%=aszLandingParamsHash%>#<%=aszLandingParamsHash%>">Foundations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->

	<div id="nav">
		<ul id="pager"></ul>  <ul id="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
	</div>
	<br clear="all">


<div id="map_container" style="display:none;">
	<br />
	<div id="map" style="width: 100%; height: 400px;">
	</div>
	<br /><br />
</div>

	
	<div id="docs" ></div>
	
	
<div id="simplyhired" style="display:none;">
</div>

	<div class="cleardiv"></div>
</div>
</div>
<% if(org.getIsIRSCertified()) { %>

  <div id="guidestar_content">
    <iframe id="guidestar_iframe" src="http://www.christianvolunteering.org/guidestar_profile.php?ein=<%=org.getORGFedTaxId()%>" frameborder="0" scrolling="no" style="width:100%;"></iframe>
  </div>
<% } %>




</div>



<% if(aszPortal.length()>0 && (aszHostID.equalsIgnoreCase("volengchurch") || aszHostID.equalsIgnoreCase("volengexample"))){ %>	
<% if(bSkipBanner==false){ %>
<br>
<button type="button" id="embed_button" onClick="show_embed_div()">Embed on Your Site</button>
	<%@include file="/template_include/embed_options.inc"%>
<% } %>
<% } %>
		
	

      <P>
      <BR>&nbsp;</P></div>

    
    </div>
    

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->



<script>

  $('#guidestar_content iframe').load(function() {
         $('#guidestar_content').show();
	 this.style.height = this.contentDocument.body.scrollHeight + 'px';
         $('#guidestar_content').hide();
  });
  
  function tab_selected(tab) {
    $('div#all_tabs a').removeClass('active');
    $('div#all_tabs a#'+tab).addClass('active');
    $('#org_content').hide();
    $('#opps_content').hide();
    $('#guidestar_content').hide();
    $('#' + tab + '_content').show();
  }
  
</script>
