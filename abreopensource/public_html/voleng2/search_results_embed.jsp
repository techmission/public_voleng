<%@ include file="/template_include/topjspnologin1.inc" %>
<%@page import="java.text.DecimalFormat" %>
<%
String portal=aszPortal; 
String tempPortal=portal;

if(tempPortal.length()>7) if(tempPortal.startsWith("/voleng")) tempPortal=tempPortal.substring(8);
else if(tempPortal.length()>0) if(tempPortal.startsWith("/")) tempPortal=tempPortal.substring(1);

if(aszPortal.equals("/voleng")){
	tempPortal="";
	portal="";
}
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszOrgNamePrint = "";
String aszServiceAreasPrint = "";
String aszOppTitlePrint = "";

int iCounter=0;

String aszPortalLink = aszPortal;

%>
<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<%
String aszFormName = "";
String aszFQParams = "";
String aszFQParamsURL = "";
String aszFQParamsContentTypeFree = "";
String aszFQParamsContentTypeFreeURL = "";
String aszFQParamsPositionTypeFree = "";
String aszFQParamsPositionTypeFreeURL = "";
String aszKeywordSearch = "";
String aszContentType = "content_type:opportunity";
String aszTypePlural = "Opportunities";

String aszFQParamValue = "";
String aszFQParamFacet = "";
String aszKewordsSpaceSeparated = "";
String aszKewordsCommaSeparated = "";
String aszFQSearch = "";
boolean bLocationFacet = false;

String aszLat = "", aszLong = "";
String aszZIP = searchinfo.getZIP();
if(aszZIP.length() > 0){
	ArrayList aLocationDataList = new  ArrayList ( 2 );
	aCodes.getZipCodeData( aLocationDataList, aszZIP );
	if(null != aLocationDataList){
		try{
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLocationDataList.get(0);
		aszLat = aAppCode.getLatitude();
		aszLong = aAppCode.getLongitude();
		}catch(java.lang.IndexOutOfBoundsException ex){
			System.out.println("jsp file   Exception "+ex.getMessage());	
		}
	}
}
try{
	if(request.getParameter("formname") != null){
		aszFormName = request.getParameter("formname");
	}
	if(request.getParameter("q") != null){
		String[] a_aszQParams = new String[255];
		a_aszQParams = request.getParameterValues("q");
		for(int i = 0; i < a_aszQParams.length; i++){
			aszKeywordSearch+="q="+a_aszQParams[i] + "&";
		}
	}
	if(request.getParameter("fq") != null){
		String aszFQParamFacetURL = "";
		String aszFQParamValueURL = "";
		String[] a_aszFQParams = new String[255];
		a_aszFQParams = request.getParameterValues("fq");
		for(int i = 0; i < a_aszFQParams.length; i++){
			aszFQParamValue = a_aszFQParams[i];
			aszFQSearch+=aszFQParamValue + "&";
			if(aszFQParamValue.contains(":") && !(aszFQParamValue.contains("latlng")) && !(aszFQParamValue.contains("_distance:"))){
				int iTmp = aszFQParamValue.indexOf(":");
				if(aszFQParamValue.length()>iTmp+1){
					aszFQParamFacet = aszFQParamValue.substring(0,iTmp);
					if(aszFQParamFacet.equals("content_type")){
						aszContentType = aszFQParamValue;
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
						aszFQParamValue = aszFQParamValue.replaceAll("location","{!geofilt pt="+aszLat+","+aszLong+" sfield=latlng ");
						aszFQParamValue = aszFQParamValue.replaceAll("_distance:","d=");
						aszFQParamValue = aszFQParamValue.replaceAll("latlng:[(]","{!geofilt pt="+aszLat+","+aszLong);
						aszFQParamValue = aszFQParamValue.replaceAll("[)]_distance:"," sfield=latlng d=");
						aszFQParamValue = aszFQParamValue.replaceAll("_km","}");
						aszFQParamValue = aszFQParamValue.replaceAll("[|]","/");
						aszFQParamValue=aszFQParamValue.replaceAll("_", " ");
						aszFQParamValue=aszFQParamValue.replaceAll("[|]", "/");
						aszFQParamValue=aszFQParamValue.replaceAll("~", "&");
						aszFQParamValue=aszFQParamValue.replaceAll(";", ".");
						aszFQParamFacetURL = "ctp";
						if(aszFQParamValue.contains("org")) aszFQParamValueURL = "org";
						else aszFQParamValueURL = "opp";
					}else if( aszFQParamFacet.contains("position_type") ){
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
					}else if( aszFQParamFacet.contains("org_nid") ){
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
					}else{
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
						aszFQParamValue = aszFQParamValue.replaceAll("latlng:[(]","{!geofilt pt=");
						aszFQParamValue = aszFQParamValue.replaceAll("[)]_distance:"," sfield=latlng d=");
				    	aszFQParamValue = aszFQParamValue.replaceAll("_km","}");
						aszFQParamValue=aszFQParamValue.replaceAll("_", " ");
						aszFQParamValue=aszFQParamValue.replaceAll("[|]", "/");
						aszFQParamValue=aszFQParamValue.replaceAll("~", "&");
						aszFQParamValue=aszFQParamValue.replaceAll(";", ".");
						if(aszKewordsSpaceSeparated.length()>1){
							if(	aszFQParamFacet.contains("position_type") ){
							}else if(	
								aszFQParamFacet.equals("city_tax")		|| 
								aszFQParamFacet.equals("region")		|| 
								aszFQParamFacet.equals("country_tax")	|| 
								aszFQParamFacet.equals("province_tax")	|| 
								aszFQParamFacet.equals("location")	|| 
								aszFQParamFacet.equals("location_data")
							){
								bLocationFacet = true;
							}else{
								aszKewordsSpaceSeparated += ", ";
							}
							aszKewordsCommaSeparated  += ", ";
						}
						if(bLocationFacet==true){
							aszKewordsSpaceSeparated = aszFQParamValue + " " + aszKewordsSpaceSeparated;
							aszKewordsCommaSeparated = aszFQParamValue + " " + aszKewordsCommaSeparated;
						}else if(
							aszFQParamFacet.contains("content_type") 	||
							aszFQParamFacet.contains("ctp")
						){  
						}else{
							aszKewordsSpaceSeparated += aszFQParamValue;
							aszKewordsCommaSeparated += aszFQParamValue;
						}
					}
					if(! aszKeywordSearch.contains(aszFQParamValue) ){
						aszFQParamFacetURL = aszFQParamFacet;
aszFQParamValueURL = aszFQParamValue;
//						aszFQParamValueURL = aszFQParamValue.replaceAll("{!geofilt pt=","latlng:(");
//						aszFQParamValueURL = aszFQParamValueURL.replaceAll(" sfield=latlng d=",")_distance:");
//				    	aszFQParamValueURL = aszFQParamValueURL.replaceAll("}","_km");
						aszFQParamValueURL = aszFQParamValueURL.replaceAll(" ","_");
						aszFQParamValueURL = aszFQParamValueURL.replaceAll("/","|");
						aszFQParamValueURL = aszFQParamValueURL.replaceAll("&", "~");
						aszFQParamValueURL = aszFQParamValueURL.replaceAll(";", ".");
						if(aszFQParamValueURL.length() > 1){
							if(aszFQParamValueURL.endsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(0,aszFQParamValueURL.length()-1);
							if(aszFQParamValueURL.startsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(1);
							if(! aszFQParamFacet.equals("content_type")){
								if(aszFQParamFacet.equals("loc")){
									aszFQParamFacet = "location_data";
								}else if(aszFQParamFacet.equals("ct")){
									aszFQParamFacet = "city_tax";
								}else if(aszFQParamFacet.equals("ctr")){
									aszFQParamFacet = "country_tax";
								}else if(aszFQParamFacet.equals("st")){
									aszFQParamFacet = "province_tax";
								}else if(aszFQParamFacet.equals("r")){
									aszFQParamFacet = "region";
								}else if(aszFQParamFacet.equals("na")){
									aszFQParamFacet = "org_affil";
								}else if(aszFQParamFacet.equals("sa")){
									aszFQParamFacet = "service_areas";
								}else if(aszFQParamFacet.equals("sk")){
									aszFQParamFacet = "skills";
								}else if(aszFQParamFacet.equals("gf")){
									aszFQParamFacet = "great_for";
								}else if(aszFQParamFacet.equals("f")){
									aszFQParamFacet = "frequency";
								}else if(aszFQParamFacet.equals("b")){
									aszFQParamFacet = "benefits_offered";
								}else if(aszFQParamFacet.equals("tl")){
									aszFQParamFacet = "trip_length";
								}else if(aszFQParamFacet.equals("d")){
									aszFQParamFacet = "denom_affil";
								}else if(aszFQParamFacet.equals("pot")){
									aszFQParamFacet = "primary_opp_type";
								}else if(aszFQParamFacet.equals("pt")){
									aszFQParamFacet = "position_type";
								}else if(aszFQParamFacet.equals("zip")){
									aszFQParamFacet = "postal_code";
								}else if(aszFQParamFacet.equals("mem")){
									aszFQParamFacet = "org_member_type";
								}else if(aszFQParamFacet.equals("ctp")){
									aszFQParamFacet = "content_type";
									if(aszFQParamValue.contains("org"))	aszFQParamValue = "organization";
									else	aszFQParamValue = "opportunity";
								}
								aszKeywordSearch+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
								aszFQParamsURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamFacet is: "+aszFQParamFacet+"  & aszFQParamValue is: "+aszFQParamValue+" -->");
								if(!aszFQParamFacet.equals("content_type")){
									aszFQParamsContentTypeFree+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
									aszFQParamsContentTypeFreeURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamsContentTypeFree is: "+aszFQParamsContentTypeFree+"  & aszFQParamsContentTypeFreeURL is: "+aszFQParamsContentTypeFreeURL+" -->");
								}
								if(!aszFQParamFacet.equals("position_type")){
									aszFQParamsPositionTypeFree+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
									aszFQParamsPositionTypeFreeURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamsPositionTypeFree is: "+aszFQParamsPositionTypeFree+"  & aszFQParamsPositionTypeFreeURL is: "+aszFQParamsPositionTypeFreeURL+" -->");
								}
							}
						}
					}
				}else if(! aszKeywordSearch.contains(aszFQParamValue) ){
					aszKeywordSearch+="&fq="+aszFQParamValue;
				}	
			}
		}
	}
	if(aszKewordsSpaceSeparated.endsWith(", ")){
		aszKewordsSpaceSeparated = aszKewordsSpaceSeparated.substring(0,aszKewordsSpaceSeparated.length()-2);
	}
	aszKewordsSpaceSeparated = aszKewordsSpaceSeparated.replaceAll("\"","") + " ";
	aszKewordsCommaSeparated = aszKewordsCommaSeparated.replaceAll("\"","") + " ";
	if(request.getParameter("field.facet") != null){
		String[] a_aszFieldFacetParams = new String[255];
		a_aszFieldFacetParams = request.getParameterValues("field.facet");
		for(int i = 0; i < a_aszFieldFacetParams.length; i++){
			aszKeywordSearch+="field.facet="+a_aszFieldFacetParams[i] + "&";
		}
	}
}catch(NullPointerException e){
}
if(aszContentType.contains("org")){
	aszTypePlural = "Organizations";
}

if( portal.length()>0 && (! portal.contains("/voleng") )){ // redirect to a non-solr version
	String newURL = portal+"/oppsrch.do?method=processOppSearchAll&source=feeds";
if(aszContentType.contains("org")){
		newURL = portal+"/oppsrch.do?method=processOrgSearchAll";
	}
	response.setStatus(301);
	response.setHeader( "Location", "http://www.christianvolunteering.org" + newURL );		
	response.setHeader( "Connection", "close" );
}

aszFQParams = aszKeywordSearch.replaceAll("\"","&quot;");
aszFQParamsContentTypeFree = aszFQParamsContentTypeFree.replaceAll("\"","&quot;");
aszFQParamsPositionTypeFree = aszFQParamsPositionTypeFree.replaceAll("\"","&quot;");
//if(aszFQParams.length()>0)	aszFQParams = aszFQParams.substring(0,aszFQParams.length()-1);
if(aszFQParams.endsWith("&")){
	aszFQParams = aszFQParams.substring(0,aszFQParams.length()-1);
}
if(aszFQParams.startsWith("&")){
	aszFQParams = aszFQParams.substring(1);
}
if(aszFQParamsContentTypeFree.endsWith("&")){
	aszFQParamsContentTypeFree = aszFQParamsContentTypeFree.substring(0,aszFQParamsContentTypeFree.length()-1);
}
if(aszFQParamsContentTypeFree.startsWith("&")){
	aszFQParamsContentTypeFree = aszFQParamsContentTypeFree.substring(1);
}
if(aszFQParamsPositionTypeFree.endsWith("&")){
	aszFQParamsPositionTypeFree = aszFQParamsPositionTypeFree.substring(0,aszFQParamsPositionTypeFree.length()-1);
}
if(aszFQParamsPositionTypeFree.startsWith("&")){
	aszFQParamsPositionTypeFree = aszFQParamsPositionTypeFree.substring(1);
}
aszFQParamsURL = aszFQParamsURL.replaceAll("\"","&quot;");
aszFQParamsContentTypeFreeURL = aszFQParamsContentTypeFreeURL.replaceAll("\"","&quot;");
aszFQParamsPositionTypeFreeURL = aszFQParamsPositionTypeFreeURL.replaceAll("\"","&quot;");
//if(aszFQParamsURL.length()>0)	aszFQParamsURL = aszFQParamsURL.substring(0,aszFQParamsURL.length()-1);
if(aszFQParamsURL.endsWith("&")){
	aszFQParamsURL = aszFQParamsURL.substring(0,aszFQParamsURL.length()-1);
}
if(aszFQParamsURL.startsWith("&")){
	aszFQParamsURL = aszFQParamsURL.substring(1);
}
if(aszFQParamsContentTypeFreeURL.endsWith("&")){
	aszFQParamsContentTypeFreeURL = aszFQParamsContentTypeFreeURL.substring(0,aszFQParamsContentTypeFreeURL.length()-1);
}
if(aszFQParamsContentTypeFreeURL.startsWith("&")){
	aszFQParamsContentTypeFreeURL = aszFQParamsContentTypeFreeURL.substring(1);
}
if(aszFQParamsPositionTypeFreeURL.endsWith("&")){
	aszFQParamsPositionTypeFreeURL = aszFQParamsPositionTypeFreeURL.substring(0,aszFQParamsPositionTypeFreeURL.length()-1);
}
if(aszFQParamsPositionTypeFreeURL.startsWith("&")){
	aszFQParamsPositionTypeFreeURL = aszFQParamsPositionTypeFreeURL.substring(1);
}
if(aszFQSearch.endsWith("&")){
	aszFQSearch = aszFQSearch.substring(0,aszFQSearch.length()-1);
}
if(aszKeywordSearch.endsWith("&")){
	aszKeywordSearch = aszKeywordSearch.substring(0,aszKeywordSearch.length()-1);
}
if(aszKeywordSearch.startsWith("&")){
	aszKeywordSearch = aszKeywordSearch.substring(1);
}
if(aszKeywordSearch.length()<1){
	aszKeywordSearch = "q=content_type:opportunity";
}

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
String aszSearchCity = searchinfo.getCity();
String aszState = searchinfo.getState();
String aszCountry = searchinfo.getCountry();
String aszRegion = searchinfo.getRegion();

String aszLatitude = searchinfo.getSearchLatitude1();
String aszLongitude = searchinfo.getSearchLongitude1();

int iCountDisplay=0,iNumDisplay=10;
String aszDisplayClass="";

if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;

String aszTitleTag = "";
if(aszKewordsSpaceSeparated.length() > 1){
	if(aszKewordsSpaceSeparated.endsWith(" ")){
		aszTitleTag = aszKewordsSpaceSeparated.substring(0,aszKewordsSpaceSeparated.length()-1) + ": ";
	}
}
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ 
	aszTitleTag += "Volunteer Opportunities";
}else{ 
	aszTitleTag += "Christian Volunteer and Short Term Missions " + aszTypePlural + ": " + aszSubdomain;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="image_src" href="/imgs/logo.gif"/>
	<title><%=aszTitleTag%></title>
<meta name="description" content="<%=aszKewordsSpaceSeparated%> Search <%=aszOrgOrChurchOpp%> and Short Term Missions <% if(aszTypePlural.contains("pportunities"))out.print("Trip ");out.print(aszTypePlural); %>: <%=aszSubdomain%>" />
<meta name="keywords" content="<%=aszKewordsCommaSeparated%>, volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, orphanage, medical missions, christian internship, ministry internship, online volunteering, volunteer online">
<% 
} 
bSkipBanner=true;
%> 
<base target="_blank" />

<!-- start header information -->
<%//@ include file="/template/header.inc" %>


<% String aszBodyClass=""; %>
<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
<% } else{ %>
<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_header_tags_include.inc" %>
<% } %>
<% if( bHeaderSet == false ){ %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="image_src" href="/imgs/logo.gif"/>
	<% if(	
				(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/nonp-view11.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")) ||
				(aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")) ||
				(false==true) 
		){ // don't load the title tag or the meta description tag here - it has already been included in the file itself %>
	<% } else if(aszPortalHeaderTags.length()>0){ %>
<%=aszPortalHeaderTags%>
	<% }else{ %>
		<title>
			ChristianVolunteering.org: 
Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>
	<% } %>
	<meta name="description" content="Christianvolunteering.org matches volunteers with Christian social service organizations, nonprofits and churches to provide opportunities in urban ministry, youth programs, short-term missions and virtual volunteering.">
	<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism, youth work, digital divide, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<% } %>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<% 
if(	aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp") ||
    aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")
){
	// it's already set in the file; don't set it here
%>
<!-- robots meta set -->
<% }else if(aszHostID.equalsIgnoreCase( "voleng1" )) { %>
<meta name="robots" content="index, follow, noarchive">
<%	}else{ %>
<meta name="robots" content="noindex, nofollow, noarchive">
<% } %>

<%@ include file="/template_include/menu_classes.inc" %>

<% 
ApplicationCodesBRLO aCodes2 = new ApplicationCodesBRLO( request );
ArrayList aService2List = new  ArrayList ( 2 );
aCodes2.getAppCodeList( aService2List, 161 );
%>

<link href="http://www.christianvolunteering.org/template_include/css/new_menu.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-wide.css" rel="stylesheet" type="text/css" />
<!--link href="http://www.christianvolunteering.org/template_include/css/navigation.css" rel="stylesheet" type="text/css" /-->
<!--[if IE 6]>
	<link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 7]>
        <link href="http://www.christianvolunteering.org/template_include/css/standard-wide-ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="http://www.christianvolunteering.org/template_include/css/format_boxes_chrisvol.css?1" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://www.christianvolunteering.org/misc/jquery.js"></script>
<!--DRUPAL NAVIGATION js (only needed for IE; CSS does it in other browsers)-->
<!--[if IE]>
<script type="text/javascript" src="/misc/drupal.js"></script>
<![endif]-->

<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="http://www.urbanministry.org/sites/all/themes/um/includes/hovermenu.js"></script>

<link href="http://www.urbanministry.org/sites/all/themes/um/includes/network_band/network-band.css" rel="stylesheet" type="text/css" />

<!-- method is: <%=aszMethodName%> -->
<% if(b_includeAjaxSolr==true){ %>
<%@ include file="/template_include/ajax-solr_includes.inc" %>
<% } %>


<!-- location data country is: <%=maxmind_area_code%> -->
<!-- location data zip code is: <%=maxmind_countryName%> -->
</head>

<% 
if( aszPortalTemplate.equals("basic") ){ 
	aszBodyClass+=" portal";
}
%>
<script TYPE="text/javascript">
<!--
function popup(mylink, windowname)
{
	if (! window.focus)return true;
	var href;
	if (typeof("<%=request.getContextPath()%>/register.do?method=showHelp") == 'string')
	   href="<%=request.getContextPath()%>/register.do?method=showHelp";
	else
	   href="<%=request.getContextPath()%>/register.do?method=showHelp".href;
	window.open(href, windowname, 'width=400,height=200,scrollbars=yes');
	return false;
}
//-->
</script>

<body  id="chrisvol" class="<%=aszBodyClass%>" >
<%@ include file="/template_include/facebookapi_keys.inc" %>
<%@ include file="/template_include/facebook_graphapi_init.inc" %>


<div id="wrapper">
<% 
String aszContentWrapperClass="";
if( aszPortalTemplate.equals("basic") ){ 
	aszContentWrapperClass+="basic ";
}
if(b_includeLeftSidebar==true ){
	aszContentWrapperClass += "solr";
}
if(bSkipBanner==true){ 
	aszContentWrapperClass += " nobanner";
}
%>

<div id="contentwrapper" class="<%=aszContentWrapperClass%>">


<% } %>


<!-- END HEADER -->



<!-- end header information -->


<!-- start navigation information -->
<%//@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search"><%//=aszKeywordSearch%></div>
	<div id="fq_search"><%//=aszFQSearch%></div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="geofilt"><%= searchinfo.getGeoFilter() %></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
</div>

<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%@ include file="/template_include/iterate_solr_results.inc" %>
<%@ include file="/template_include/search_sidebar.inc" %>
</div>





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
					var init_class = list_element.className;
					if(init_class=='filter collapsed'){
						list_element.style.display="block";
						list_element.className = default_class+expanded_class;
					}
				}
			}
		}
	}
	var more_link = 'more_link_'+facet_field;
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display='none'
		more_link_element.className = collapsed_class;
	}
}

$(document).ready(function() {
	
//	window.location.replace(window.location.href.replace('#', '') + '#<%=aszKeywordSearch%>');
<% if(aszFormName.equals("cleanurl")){ %>
//	window.location.replace(window.location.href + '#<%=aszKeywordSearch%>');
<% } %>
	var filter_class = 'filter';
	var handle_class = ' handle';
	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';
	
	var filter_data = $('#filter_data_heading').text();
	var contenttype_data = $('#contenttype_heading').text();
	var service_areas_element = document.getElementById('service_areas'); 
	var group_size_element = document.getElementById('group_size'); 
	var benefits_element = document.getElementById('benefits_offered'); 
	var length_of_trip_element = document.getElementById('trip_length'); 
	var frequency_element = document.getElementById('frequency'); 
	var advanced_element = document.getElementById('advanced_facets'); 
	var country_element = document.getElementById('country_tax'); 
	var region_element = document.getElementById('region'); 
	var denom_affil_element = document.getElementById('denom_affil'); 
	var affil_element = document.getElementById('org_affil'); 
	var member_type_element = document.getElementById('org_member_type'); 
	var primary_opp_type_element = document.getElementById('primary_opp_type'); 
	var third_party_element = document.getElementById('source'); 
		
	var facet_service_areas_element = document.getElementById('facet_service_areas'); 
	var facet_group_size_element = document.getElementById('facet_group_size'); 
	var facet_great_for_element = document.getElementById('facet_great_for'); 
	var facet_benefits_offered_element = document.getElementById('facet_benefits_offered'); 
	var facet_length_of_trip_element = document.getElementById('facet_trip_length'); 
	var facet_frequency_element = document.getElementById('facet_frequency'); 
	var facet_advanced_element = document.getElementById('facet_adv'); 
	var facet_country_element = document.getElementById('facet_country_tax'); 
	var facet_region_element = document.getElementById('facet_region'); 
	var facet_denom_affil_element = document.getElementById('facet_denom_affil'); 
	var facet_affil_element = document.getElementById('facet_org_affil'); 
	var facet_member_type_element = document.getElementById('facet_org_member_type'); 
	var facet_primary_opp_type_element = document.getElementById('facet_primary_opp_type'); 
	var facet_third_party_element = document.getElementById('facet_afg'); 
	var facet_province_element = document.getElementById('facet_province_tax'); 
	var facet_city_element = document.getElementById('facet_city_tax'); 
		
	var hashURL = '' + window.location.hash;
//console.log('hashURL is: ' + hashURL);
	var hash_org = hashURL.indexOf("content_type:organization");
	var hash_job = hashURL.indexOf("content_type:job");
	var hash_opp = hashURL.indexOf("content_type:opportunity");
	var hash_stm = hashURL.indexOf("Short-term Missions / Volunteer Internship");
	var hash_virt = hashURL.indexOf("Virtual Volunteering (from home)");
	
	$('#service_areas_label').text('Service Areas');
//console.log('hash_org is: ' + hash_org + '; hash_opp is: ' + hash_opp + '; hash_stm is: ' + hash_stm + '; hash_virt is: ' + hash_virt);
		if(hash_org != -1){
			$('#local').removeClass('active');
			$('#virtual').removeClass('active');
			$('#stm').removeClass('active');
			$('#organization').addClass('active');
			$('#job').removeClass('active');
			
			$('#srchmethod').val('organization');
			$('#filter_data_heading').text(filter_data);
			//$('#contenttype_heading').text(contenttype_data);
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Organizations');
				
			$('#simplyhired').hide();
				service_areas_element.style.display='none';
				facet_service_areas_element.className=filter_class+handle_class+collapsed_class;
				group_size_element.style.display='none';
				facet_group_size_element.style.display='none';
				facet_group_size_element.className=filter_class+handle_class+collapsed_class;
				frequency_element.style.display='none';
				facet_frequency_element.style.display='none';
				facet_frequency_element.className=filter_class+handle_class+collapsed_class;
				benefits_element.style.display='none';
				facet_benefits_offered_element.style.display='none';
				facet_benefits_offered_element.className=filter_class+handle_class+collapsed_class;
				third_party_element.style.display='none';
//				facet_third_party_element.style.display='none';
				facet_third_party_element.className=filter_class+handle_class+collapsed_class;
				
				primary_opp_type_element.style.display='block';
				facet_primary_opp_type_element.style.display='block';
				facet_primary_opp_type_element.className=filter_class+handle_class+expanded_class;

				advanced_element.style.display='block';
				facet_advanced_element.className=filter_class+handle_class+expanded_class;
				affil_element.style.display='block';
				facet_affil_element.className=filter_class+handle_class+expanded_class;
				denom_affil_element.style.display='block';
				facet_denom_affil_element.className=filter_class+handle_class+expanded_class;
				member_type_element.style.display='block';
				facet_member_type_element.className=filter_class+handle_class+expanded_class;
				country_element.style.display='block';
				facet_country_element.className=filter_class+handle_class+expanded_class;
				region_element.style.display='block';
				facet_region_element.className=filter_class+handle_class+expanded_class;
				primary_opp_type_element.style.display='block';
				facet_primary_opp_type_element.className=filter_class+handle_class+expanded_class;
		}else if(hash_job != -1){
			$('#local').removeClass('active');
			$('#virtual').removeClass('active');
			$('#stm').removeClass('active');
			$('#organization').removeClass('active');
			$('#job').addClass('active');
			
			$('#srchmethod').val('job');
			$('#filter_data_heading').text(filter_data);
			//$('#contenttype_heading').text(contenttype_data);
			$('#contenttype_title').text('Job');
			$('#contenttype_heading').text('Job Postings');

			$('#service_areas_label').text('Job Categories');
			
			facet_great_for_element.style.display='none';  
			facet_group_size_element.style.display='none'; 
			facet_benefits_offered_element.style.display='none'; 
			facet_length_of_trip_element.style.display='none'; 
			facet_frequency_element.style.display='none'; 

			facet_member_type_element.style.display='none';
			advanced_element.style.display='block';
			facet_advanced_element.className=filter_class+handle_class+expanded_class;
			$('#simplyhired').show();
		}else{
			if(hash_stm != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship ');
				
				$('#simplyhired').hide();
				$('#filter_data_heading').text(filter_data);
				//$('#contenttype_heading').text(contenttype_data);
//				$('#contenttype_heading').text('Short-term Missions / Volunteer Internship Volunteer Opportunities');
				benefits_element.style.display='block';
				facet_benefits_offered_element.className=filter_class+handle_class+expanded_class;
				length_of_trip_element.style.display='block';
				facet_length_of_trip_element.className=filter_class+handle_class+expanded_class;
				country_element.style.display='block';
				facet_country_element.className=filter_class+handle_class+expanded_class;
				region_element.style.display='block';
				facet_region_element.className=filter_class+handle_class+expanded_class;
			}else if(hash_virt != -1){
				
				$('#simplyhired').hide();
				$('#srchmethod').val('Virtual Volunteering (from home)');
				$('#filter_data_heading').text(filter_data);
				//$('#contenttype_heading').text(contenttype_data);
//				$('#contenttype_heading').text('Virtual Volunteer Opportunities');
				facet_country_element.style.display='none';
				facet_region_element.style.display='none';
				facet_province_element.style.display='none';
				facet_city_element.style.display='none';
				facet_great_for_element.style.display='none';  
				facet_group_size_element.style.display='none'; 
				facet_benefits_offered_element.style.display='none'; 
				facet_length_of_trip_element.style.display='none'; 
				facet_frequency_element.style.display='none'; 
			}else{
				$('#srchmethod').val('opportunity');
				$('#filter_data_heading').text(filter_data);
				//$('#contenttype_heading').text(contenttype_data);
				$('#contenttype_heading').text('Volunteer Opportunities');
			}
		}
	$('#results_type').text($('#srchmethod').val());
  });

$('a#stm').click(function(){
	var service_areas_element = document.getElementById('service_areas'); 
	var benefits_element = document.getElementById('benefits_offered'); 
	var length_of_trip_element = document.getElementById('trip_length'); 
	var advanced_element = document.getElementById('advanced_facets'); 
	var country_element = document.getElementById('country_tax'); 
	var region_element = document.getElementById('region'); 
	var denom_affil_element = document.getElementById('denom_affil'); 
	var affil_element = document.getElementById('org_affil'); 
	var member_type_element = document.getElementById('org_member_type'); 
	var primary_opp_type_element = document.getElementById('primary_opp_type'); 
		
	var facet_service_areas_element = document.getElementById('facet_service_areas'); 
	var facet_benefits_offered_element = document.getElementById('facet_benefits_offered'); 
	var facet_length_of_trip_element = document.getElementById('facet_trip_length'); 
	var facet_advanced_element = document.getElementById('facet_adv'); 
	var facet_country_element = document.getElementById('facet_country_tax'); 
	var facet_region_element = document.getElementById('facet_region'); 
	var facet_denom_affil_element = document.getElementById('facet_denom_affil'); 
	var facet_affil_element = document.getElementById('facet_org_affil'); 
	var facet_member_type_element = document.getElementById('org_member_type'); 
	var facet_primary_opp_type_element = document.getElementById('facet_primary_opp_type'); 
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
			$('#filter_data_heading').text(filter_data);
			//$('#contenttype_heading').text(contenttype_data);
//				$('#contenttype_heading').text('Short-term Missions / Volunteer Internship');
				benefits_element.style.display='block';
				facet_benefits_offered_element.className=filter_class+handle_class+expanded_class;
				length_of_trip_element.style.display='block';
				facet_length_of_trip_element.className=filter_class+handle_class+expanded_class;
				country_element.style.display='block';
				facet_country_element.className=filter_class+handle_class+expanded_class;
				region_element.style.display='block';
				facet_region_element.className=filter_class+handle_class+expanded_class;
				
			$('#simplyhired').hide();
});  
$('a#job').click(function(){
	$('#simplyhired').show();
});  
</script>

<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>


<style>
#contentwrapper{
	background:url("http://www.christianvolunteering.org/imgs/bodycontent_patt_wide-950.gif") repeat-y scroll right top #FFFFFF;
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

<!-- start sidebar information -->
<!-- start sidebar.inc -->

<div id="sidebar" class="search_results" style="display:none;">
	<div id="sidebar_content">
		<br>
	<h3 id="filter_label">Filters Applied</h3><ul id="removeall"></ul>
	<ul id="selection"></ul>

	<div class="cleardiv"></div>
<ul class="filters" id="filters">		
	<li id="facet_dist">
		<div id="label" style="float:left">Distance:</div>
		<div id="dist" class="tagcloud toggle" style="float:left; display:block;">
				<SELECT id="d" name="d" class="smalldropdown" > 
					<option value="1.609344">exact location</option>
					<option value="8.04672">within 5 miles</option>
					<option value="16.09344">within 10 miles</option>
					<option value="24.14016">within 15 miles</option>
					<option value="40.2336" selected="selected">within 25 miles</option>
					<option value="80.4672">within 50 miles</option>
					<option value="160.9344">within 100 miles</option>
					<option value="Virtual">virtual</option>
				</SELECT>
		</div>
		<br clear="all"/>
	</li>
		
		<hr class="sidebar_hr"> 
	<li id="facet_sort">
		<div id="sortby" style="float:left">Sort:</div>
		<div id="sort" class="tagcloud toggle" style="float:left; display:block;">
	<SELECT id="sortkey" name="sortkey" class="smalldropdown"> 
        <option value="tm_member_i desc, popularity desc">Popularity</option>
<% //if( searchinfo.getPostalCode().length()>0 || aszLatitude.length() > 0 || aszLatitude.length() > 0){ %>
		<option value="geodist(latlng ,<%=aszLatitude%>,<%=aszLongitude%>) asc, tm_member_i desc, popularity desc" id="distance_sort" style="display:none;">Distance</option>
<% //} %>
        <option value="organization_name asc, tm_member_i desc, popularity desc" >Organization Name</option>
        <option value="opportunity_title asc, tm_member_i desc, popularity desc" >Opportunity Title</option>
        <option value="city_tax asc, tm_member_i desc, popularity desc" >City</option>
        <option value="province_tax asc, tm_member_i desc, popularity desc" >State (US & Canada)</option>
        <option value="country_tax asc, tm_member_i desc, popularity desc" >Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denom_affil asc, tm_member_i desc, popularity desc" >Denominational Affiliation</option>
<% } %>
		<option value="lat_updated_dt desc, tm_member_i desc, popularity desc" >Date Last Updated</option>
        <option value="num_volunteers_org desc, tm_member_i desc, popularity desc" ># Volunteers / Organization</option>
        <option value="num_volunteers desc, tm_member_i desc, popularity desc" ># Volunteers / Opportunity</option>
    </SELECT>

		</div>
<br clear="all"/><br clear="all"/>
</li>
	<li class="filter handle expanded" id="facet_primary_opp_type" style="display:none;">
		<a class="handle" onClick="toggle_display('primary_opp_type')"><span class="i bullet">&nbsp;</span> <span class="label">Oppportunity Types:</span></a>
		<ul id="primary_opp_type" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("primary_opp_type")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "pot";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_primary_opp_type" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_primary_opp_type'); return false;" style="display:inline" href="#primary_opp_type">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle expanded" id="facet_service_areas">
		<a class="handle" onClick="toggle_display('service_areas')"><span class="i bullet">&nbsp;</span> <span class="label" id="service_areas_label">Service Area:</span></a>
		<ul id="service_areas" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("service_areas")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "sa";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_service_areas" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_service_areas'); return false;" style="display:inline" href="#service_areas">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle collapse" id="facet_great_for">
		<a class="handle" onClick="toggle_display('great_for')"><span class="i bullet">&nbsp;</span> <span class="label">Great for:</span></a>
		<ul id="great_for" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("great_for")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "gf";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_great_for" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_great_for'); return false;" style="display:inline" href="#great_for">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
	<li class="filter handle collapse" id="facet_frequency">
		<a class="handle" onClick="toggle_display('frequency')"><span class="i bullet">&nbsp;</span> <span class="label">Frequency:</span></a>
		<ul id="frequency" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("frequency")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "f";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_frequency" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_frequency'); return false;" style="display:inline" href="#frequency">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_benefits_offered">
		<a class="handle" onClick="toggle_display('benefits_offered')"><span class="i bullet">&nbsp;</span> <span class="label">Benefits:</span></a>
		<ul id="benefits_offered" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("benefits_offered")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "b";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_benefits_offered" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_benefits_offered'); return false;" style="display:inline" href="#benefits_offered">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_trip_length">
		<a class="handle" onClick="toggle_display('trip_length')"><span class="i bullet">&nbsp;</span> <span class="label">Length of Trip:</span></a>
		<ul id="trip_length" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("trip_length")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "tl";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_trip_length" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_trip_length'); return false;" style="display:inline" href="#trip_length">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle collapse" id="facet_region">
		<a class="handle" onClick="toggle_display('region')"><span class="i bullet">&nbsp;</span> <span class="label">Region:</span></a>
		<ul id="region" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("region")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "r";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_region" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_region'); return false;" style="display:inline" href="#region">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
	<li class="filter handle collapse" id="facet_country_tax">
		<a class="handle" onClick="toggle_display('country_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Country:</span></a>
		<ul id="country_tax" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("country_tax")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "ctr";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_country_tax" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_country_tax'); return false;" style="display:inline" href="#country_tax">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_adv">
		<a class="handle" onClick="toggle_display('advanced_facets')"><span class="i bullet">&nbsp;</span> <span class="label">Advanced</span></a>
		<ul id="advanced_facets" class="filters" style="display:none;">
			<li class="filter"><hr class="sidebar_hr"> </li>
				
			<li class="filter handle collapse" id="facet_org_member_type">
				<a class="handle" onClick="toggle_display('org_member_type')"><span class="i bullet">&nbsp;</span> <span class="label">Organization Type:</span></a>
				<ul id="org_member_type" class="tagcloud toggle" style="display:none;">
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("org_member_type")){ 
								aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						String aszFacets0 = "mem";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
					} 
				%>
						</logic:iterate>
					</logic:notEmpty>
				</ul>
			</li>

			<li class="filter handle collapse" id="facet_denom_affil">
				<a class="handle" onClick="toggle_display('denom_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Denomination:</span></a>
				<ul id="denom_affil" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("denom_affil")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "d";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_denom_affil" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_denom_affil'); return false;" style="display:inline" href="#denom_affil">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>
				
<% // expand if org is selected, though %>
			<li class="filter handle collapse" id="facet_org_affil">
				<a class="handle" onClick="toggle_display('org_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Organizational Affiliation:</span></a>
				<ul id="org_affil" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("org_affil")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "na";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_org_affil" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_org_affil'); return false;" style="display:inline" href="#org_affil">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li> 
		
			<li class="filter handle collapse" id="facet_province_tax">
				<a class="handle" onClick="toggle_display('province_tax')"><span class="i bullet">&nbsp;</span> <span class="label">State/Province:</span></a>
				<ul id="province_tax" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("province_tax")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "st";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_province_tax" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_province_tax'); return false;" style="display:inline" href="#province_tax">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_city_tax">
				<a class="handle" onClick="toggle_display('city_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Metro Area:</span></a>
				<ul id="city_tax" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("city_tax")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						String aszFacets0 = "ct";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						String aszHashSearch = aszFQParams;
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_city_tax" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_city_tax'); return false;" style="display:inline" href="#city_tax">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_group_size" style="display:none;">
				<a class="handle" onClick="toggle_display('group_size')"><span class="i bullet">&nbsp;</span> <span class="label">Group Size:</span></a>
				<ul id="group_size" class="tagcloud toggle" style="display:none;"></ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_afg">
				<a class="handle" onClick="toggle_display('source')"><span class="i bullet">&nbsp;</span> <span class="label">Search from Third Party Sites:</span></a>
				<ul id="source" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% if(facets[0].equals("source")){ %>
							<%
							String aszSourcePhrase = facets[1];
							if(! aszSourcePhrase.contains("bil_import")){
								if(aszSourcePhrase.equalsIgnoreCase("AllForGood")){
									aszSourcePhrase = "<img src=\"http://www.christianvolunteering.org/imgs/afg-blue_60.png\">";
									String aszFQParamsLink = aszFQParams;
									if(aszFQParams.length()>0){
									if(! (aszFQParams.startsWith("&") )){
										aszFQParamsLink = "&" + aszFQParams;
									}
								}
								String aszHashSearch = aszFQParams;
								if(aszHashSearch.length() > 0) aszHashSearch += "&";
									aszHashSearch += "fq="+aszContentType;
									if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
									aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
							%>
									<a href="<%=aszPortal%>/oppsrch.do?method=processSearch<%=aszFQParamsLink%>&fq=<%=aszContentType%>&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;#<%=aszHashSearch%>"><%=aszSourcePhrase%> (<%=facets[2]%>)</a>
									<br> 
							<% 
								}
							}
							%>
						<% } %>
						</logic:iterate>
					</logic:notEmpty>
				</ul>
			</li>		
		</ul>
	</li>
</ul>



      <div class="clear"></div>

	</div>
</div>
<! -- end sidebar.inc -->

<!-- end sidebar information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;"><div id="contenttype_title">Volunteer Opportunities</div></span>

<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; opportunities search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->

  <div id="hd" class="solr">
	<div id="heading" style="display:none;">
		<h2 class="shorter"><span id="filter_data_heading"><%=aszKewordsSpaceSeparated%></span><span id="contenttype_heading"> Volunteer Opportunities</span></h2>
	</div>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>




<% if( 	aszMobileSubdomain.length() < 3  ) { %>
<br clear="all"/>

<div class="results-left" id="all_tabs">
	<div id="position_type">
		<div class="results-left" id="position_type_local">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Local Volunteering (in person)") && iCountDisplay==0){ 
				%>
					<a class="active" id="local" href="<%=aszPortal%>/s<%=aszFQParamsPositionTypeFreeURL%>/pt/Local_Volunteering_(in_person)/results.jsp#<%=aszFQParamsPositionTypeFree%>&fq=position_type=%22Local Volunteering (in person)%22">Local Volunteering</a>
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="active" id="local" href="">Local Volunteering</a>
			<% } %>
		</div>
		<div class="results-left" id="position_type_virtual">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Virtual Volunteering (from home)") && iCountDisplay==0){ 
				%>
					<a class="" id="virtual" href="<%=aszPortal%>/s<%=aszFQParamsPositionTypeFreeURL%>/pt/Virtual_Volunteering_(from_home)/results.jsp#<%=aszFQParamsPositionTypeFree%>&fq=position_type=%22Virtual Volunteering (from home)%22">Virtual Volunteering</a>
				<% 		
						iCountDisplay++;
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="" id="virtual" href="">Virtual Volunteering</a>
			<% } %>
		</div>
		<div class="results-left" id="position_type_stm">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Short-term Missions / Volunteer Internship") && iCountDisplay==0){ 
				%>
					<a class="" id="stm" href="<%=aszPortal%>/s<%=aszFQParamsPositionTypeFreeURL%>/pt/Short-term_Missions_|_Volunteer_Internship/results.jsp#<%=aszFQParamsPositionTypeFree%>&fq=position_type=%22Short-term Missions / Volunteer Internship%22">Short-term Missions</a>
				<% 		
						iCountDisplay++;
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="" id="stm" href="">Short-term Missions</a>
			<% } %>
		</div>
	</div>
	
	<div id="content_type">
		<div class="results-left" id="content_type_job_tab" style="display:none;">
			<a class="" id="job" href="<%=aszPortal%>/s<%=aszFQParamsContentTypeFreeURL%>/ctp/job/results.jsp#<%=aszFQParamsContentTypeFree%>&fq=content_type=job">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab">
			<a class="" id="organization" href="<%=aszPortal%>/s<%=aszFQParamsContentTypeFreeURL%>/ctp/org/results.jsp#<%=aszFQParamsContentTypeFree%>&fq=content_type=organization">Organizations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->

<% } %>


<!--br clear="all"/-->
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

	
	<div id="docs"></div>
	
	
<div id="simplyhired" style="display:none;">
<br />
<span style="font-size:20px; color:green;">*</span> Jobs by <a href="http://www.simplyhired.com/" style="text-decoration:none"><span style="color: rgb(0, 159, 223); font-weight: bold;">Simply</span><span style="color: rgb(163, 204, 64); font-weight: bold;">Hired</span></a>
</div>
</div>


<br><br>
</div></div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<div id="footer"></div>
</div>
<%//@ include file="/template/footer.inc" %>
<!-- end footer information -->

