<%@ include file="/template_include/topjspnologin1.inc" %>
<%@page import="java.text.DecimalFormat" %>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<%
String portal=aszPortal; 
String tempPortal=portal;

if(tempPortal.length()>8) if(tempPortal.startsWith("/voleng")) tempPortal=tempPortal.substring(8);
else if(tempPortal.length()>1) if(tempPortal.startsWith("/")) tempPortal=tempPortal.substring(1);

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
String aszHashSearch = "";

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
//out.print("<div style=\"display:none\">params are "+a_aszFQParams+"</div>");		
		for(int i = 0; i < a_aszFQParams.length; i++){
			aszFQParamValue = a_aszFQParams[i];
//			aszFQSearch+=aszFQParamValue + "&";
			if((aszFQParamValue.contains("location_distance:"))){
				aszFQParamValue = aszFQParamValue.replaceAll("location_distance:40.2336_km","{!geofilt pt="+aszLat+","+aszLong+" sfield=latlng d=40.2336}");
				aszFQSearch+=aszFQParamValue + "&";
			}else if(aszFQParamValue.contains(":") && !(aszFQParamValue.contains("latlng")) && !(aszFQParamValue.contains("_distance:"))){
				int iTmp = aszFQParamValue.indexOf(":");
				if(iTmp>-1 && aszFQParamValue.length()>iTmp+1){
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
						aszFQParamValue=aszFQParamValue.replaceAll("~~", ".");
						aszFQParamValue=aszFQParamValue.replaceAll("~", "&");
						aszFQParamValue=aszFQParamValue.replaceAll(";", ".");
						aszFQParamFacetURL = "ctp";
						if(aszFQParamValue.contains("org")) aszFQParamValueURL = "org";
						else if(aszFQParamValue.contains("resume")) aszFQParamValueURL = "resume";
						else if(aszFQParamValue.contains("job")) aszFQParamValueURL = "job";
						else aszFQParamValueURL = "opp";
					}else if( aszFQParamFacet.contains("position_type") ){
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
					}else if( aszFQParamFacet.contains("org_nid") ){
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
					}else if(! aszFQParamValue.equals("true") ){
						aszFQParamValue = aszFQParamValue.substring(iTmp+1);
						aszFQParamValue = aszFQParamValue.replaceAll("latlng:[(]","{!geofilt pt=");
						aszFQParamValue = aszFQParamValue.replaceAll("[)]_distance:"," sfield=latlng d=");
				    	aszFQParamValue = aszFQParamValue.replaceAll("_km","}");
						aszFQParamValue=aszFQParamValue.replaceAll("_", " ");
						aszFQParamValue=aszFQParamValue.replaceAll("[|]", "/");
						aszFQParamValue=aszFQParamValue.replaceAll("~~", ".");
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
							aszFQParamFacet.contains("ctp") 	||
							aszFQParamFacet.contains("mem") 	||
							aszFQParamFacet.contains("org_member_type") 	||
							aszFQParamFacet.contains("hidden_source") 	||
							aszFQParamFacet.contains("ctp") 	||
							aszFQParamFacet.contains("full_user")
						){  
						}else{
							aszKewordsSpaceSeparated += aszFQParamValue;
							aszKewordsCommaSeparated += aszFQParamValue;
						}
//			aszFQSearch+=aszFQParamFacet + aszFQParamValue + "&";
						
					}
					if(! aszKeywordSearch.contains(aszFQParamValue) ){
						aszFQParamFacetURL = aszFQParamFacet;
aszFQParamValueURL = aszFQParamValue;
//						aszFQParamValueURL = aszFQParamValue.replaceAll("{!geofilt pt=","latlng:(");
//						aszFQParamValueURL = aszFQParamValueURL.replaceAll(" sfield=latlng d=",")_distance:");
//				    	aszFQParamValueURL = aszFQParamValueURL.replaceAll("}","_km");

//*						aszFQParamValueURL = aszFQParamValueURL.replaceAll(" ","_");
//*						aszFQParamValueURL = aszFQParamValueURL.replaceAll("/","|");
//*						aszFQParamValueURL = aszFQParamValueURL.replaceAll(".", "~~");
//*						aszFQParamValueURL = aszFQParamValueURL.replaceAll("&", "~");
	//					aszFQParamValueURL = aszFQParamValueURL.replaceAll(";", ".");
						if(aszFQParamValueURL.length() > 1){
							if(aszFQParamValueURL.endsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(0,aszFQParamValueURL.length()-1);
							if(aszFQParamValueURL.startsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(1);
							if(! aszFQParamFacet.equals("content_type")){
								aszKeywordSearch+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
								aszFQSearch+="&"+aszFQParamFacet+":"+aszFQParamValue;
								
								
//*								aszFQParamsURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamFacet is: "+aszFQParamFacet+"  & aszFQParamValue is: "+aszFQParamValue+" -->");
								if(!aszFQParamFacet.equals("content_type")){
									aszFQParamsContentTypeFree+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
//*									aszFQParamsContentTypeFreeURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamsContentTypeFree is: "+aszFQParamsContentTypeFree+"  & aszFQParamsContentTypeFreeURL is: "+aszFQParamsContentTypeFreeURL+" -->");
								}
								if(!aszFQParamFacet.equals("position_type")){
									aszFQParamsPositionTypeFree+="&fq="+aszFQParamFacet+":"+aszFQParamValue;
//*									aszFQParamsPositionTypeFreeURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
//out.println("<!-- aszFQParamsPositionTypeFree is: "+aszFQParamsPositionTypeFree+"  & aszFQParamsPositionTypeFreeURL is: "+aszFQParamsPositionTypeFreeURL+" -->");
								}
							}
						}
					}
				}else if(! aszKeywordSearch.contains(aszFQParamValue) ){
					aszKeywordSearch+="&fq="+aszFQParamValue;
				}	
			}
//			aszFQSearch+=aszFQParamValue + "&";
		}
	}
	if(aszKewordsSpaceSeparated.endsWith(", ") && aszKewordsSpaceSeparated.length()>2){
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

if( portal.length()>0 && (! portal.contains("/voleng") )  && (! aszPortal.contains("cityvision") )){ // redirect to a non-solr version
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
//*aszFQParamsURL = aszFQParamsURL.replaceAll("\"","&quot;");
//*aszFQParamsContentTypeFreeURL = aszFQParamsContentTypeFreeURL.replaceAll("\"","&quot;");
//*aszFQParamsPositionTypeFreeURL = aszFQParamsPositionTypeFreeURL.replaceAll("\"","&quot;");
aszFQParamsURL = aszFQParams.replaceAll("\"","&quot;");
aszFQParamsContentTypeFreeURL = aszFQParamsContentTypeFree.replaceAll("\"","&quot;");
aszFQParamsPositionTypeFreeURL = aszFQParamsPositionTypeFree.replaceAll("\"","&quot;");

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

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.churchvolunteering.org")){
	aszSubdomain = "ChurchVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}

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
<% } %> 

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search"><%//=aszKeywordSearch%></div>
	<div id="fq_search"><%=aszFQSearch%></div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
	<div id="geofilt_facet"></div>
<%
String aszGeoFiltData = searchinfo.getGeoFilter();
if(aszGeoFiltData.length()==0 && aszFQParamValue.contains("geofilt"))	aszGeoFiltData=aszFQParamValue;
%>	
	<div id="geofilt"><%= aszGeoFiltData %></div>
	<div id="content_type_search"><%=aszContentType%></div>
	<div id="fqparamsurlhash"><%=aszFQParamsURL  %></div>
	
	aszFQParams is <%=aszFQParams%>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQSearch is <%=aszFQSearch%>
	aszFQParamValue is <%=aszFQParamValue%>
		aszKeywordSearch is <%=aszKeywordSearch%>

	<div id="portal_url"><% if(aszPortal.length()>0) out.print(aszPortal); %></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
</div>

<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
<logic:notEmpty name="alist" >
	<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
		// separate out categories for output and re-word categories for no-faith
		String	aszServiceAreas = org.getOPPCategories();
		if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
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
		}
		aszServiceAreas=aszServiceAreas.replaceAll("^,","").replaceAll(",$","").replaceAll(",(?=[^()]*)", ", ");

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
						aszOppLocation=aAppCode.getCSPStateName();
						if(aszCity.equalsIgnoreCase( "" ) ) {
							aszOppLocation=aAppCode.getCSPStateName();
						}
					}
				}
			}
		} else if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
					aszOppLocation=aAppCode.getCTRPrintableName();
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppLocation=aAppCode.getCTRPrintableName();
					}
				}
			}
		}
		if (aszCity.length() > 1) {
			aszOppLocation = ", " + aszOppLocation;
		}
		aszOppLocation = org.getOPPAddrCity() + aszOppLocation;
		aszOppTitlePrint = org.getOPPTitle();
		aszOrgNamePrint = org.getORGOrgName();

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
		}
		if(aszORGUrlAlias.length()<1){
			aszORGUrlAlias=aszOPPUrlAlias;
		}
		if(iPortalUID > 0){
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}else if(org.getOPPPositionType().contains("Virtual")){
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}else{ 
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}
		String aszMemberClass="class=\"listing\"";
		if(org.getORGMember()>0){
			aszMemberClass="class=\"member listing\"";
		}

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
		String	aszFrequency = org.getOPPFreq().replaceAll(",(?=[^()]*)", ", ");
		String	aszTripLength = org.getOPPTripLength().replaceAll(",(?=[^()]*)", ", ");
		String	aszGreatFor = org.getOPPGreatForAreas().replaceAll(",(?=[^()]*)", ", ");
		String	aszBenefits = org.getOPPBenefits().replaceAll(",(?=[^()]*)", ", ");
		String	aszWorkStudy = org.getOPPWorkStudy().replaceAll(",(?=[^()]*)", ", ");
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
			aszSTMDetails += " <b>Duration:</b>&nbsp;" + aszTripLength + "&nbsp;";
		}
		if(aszSTMDetails.length()>1){
			aszSTMDetails = "<br>" + aszSTMDetails.substring(1);
		}
		
		int iTemp=0; long iTime=0;
%>


		<div id="search-results" class="<%=aszFeedsClass%>">
			<div <%=aszMemberClass%>>
				<%iCounter++;%><%=iCounter%>.&nbsp;
				<A class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszOppTitlePrint%></A>
				&nbsp;&nbsp;
				<A class="org_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
				<div class="opp-info">
					<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
					<%=aszSTMDetails%>
					<br>
					<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
				</div>
			</div>
		<hr />
		</div>

	</logic:iterate>
</logic:notEmpty>
</div><!-- end solr_results div -->

<%@ include file="/template_include/search_results_sidebar.inc" %>
<!-- end sidebar.inc -->

<!-- end sidebar information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%//@ include file="/template/partner_navigation.inc" %>
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
	<h2 class="shorter"><span id="filter_data_heading"><%=aszKewordsSpaceSeparated%></span><span id="contenttype_heading"> Volunteer Opportunities</span></h2>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>

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
					<a class="active" id="local" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsPositionTypeFreeURL%>&fq=position_type:%22Local Volunteering (in person)%22#<%=aszFQParamsPositionTypeFree%>&fq=position_type:%22Local Volunteering (in person)%22">Local Volunteering</a>
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
					<a class="" id="virtual" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsPositionTypeFreeURL%>&fq=position_type:%22Virtual Volunteering (from home)%22#<%=aszFQParamsPositionTypeFree%>&fq=position_type:%22Virtual Volunteering (from home)%22">Virtual</a>
				<% 		
						iCountDisplay++;
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="" id="virtual" href="">Virtual</a>
			<% } %>
		</div>
		<div class="results-left" id="position_type_stm">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Short-term Missions / Volunteer Internship") && iCountDisplay==0){ 
				%>
					<a class="" id="stm" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsPositionTypeFreeURL%>&fq=position_type:%22Short-term Missions / Volunteer Internship%22#<%=aszFQParamsPositionTypeFree%>&fq=position_type:%22Short-term Missions / Volunteer Internship%22">Short-term Missions</a>
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
	
        
    <div id="organization_name" >
		<div class="results-left" id="organization_name_cv_tab">
			<a class="" id="CityVision" href="#fq=organization_name:%22City%20Vision%22">Internships</a>
		</div>
	</div>      
<!--		<div class="results-left" id="organization_name_cv_tab">
			<a class="" id="CityVision" href="#fq=intern_type:%22City Vision Internship%22">Internships</a>
			<a class="" id="CityVision" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsPositionTypeFreeURL%>&fq=intern_type:%22City Vision Internship%22#<%=aszFQParamsPositionTypeFree%>&fq=organization_name:%22City%20Vision%22&fq=org_nid:73734">Internships</a>
		</div>-->  
	<div id="content_type">
		<div class="results-left" id="content_type_job_tab">
			<a class="" id="job" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsContentTypeFreeURL%>&fq=content_type:job#<%=aszFQParamsContentTypeFree%>&fq=content_type:job">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab">
			<a class="" id="organization" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsContentTypeFreeURL%>&fq=content_type:organization&fq=-org_member_type:#<%=aszFQParamsContentTypeFree%>&fq=content_type:organization&fq=-org_member_type:Foundation">Organizations</a>
		</div>
		<div class="results-left" id="content_type_res_tab">
			<a class="" id="resume" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsContentTypeFreeURL%>&fq=content_type:resume#<%=aszFQParamsContentTypeFree%>&fq=content_type:resume">Resum&eacute;s</a>
		</div>
	</div>
	
	<div id="member_type">
		<div class="results-left" id="member_type_fdn_tab">
			<a class="" id="Foundation" href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParamsContentTypeFreeURL%>&fq=content_type:organization&fq=org_member_type:&quot;Foundation&quot;#<%=aszFQParamsContentTypeFree%>&fq=content_type:organization&fq=org_member_type:&quot;Foundation&quot;">Foundations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->



<!--br clear="all"/-->
	<div id="nav">
		<ul id="pager" class="pager"></ul>  <ul id="pager-header" class="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
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
<div style="text-align: right;">
<span style="font-size:20px; color:green;">*</span> 
<span style="font-size:10px; position:relative; top:-5px; font-family:Arial,sans-serif;color: rgb(51, 51, 51);">
<a style="color:#333;text-decoration:none" href="http://employers.simplyhired.com/e/25542/2014-03-05/65bqd/76691605" rel="nofollow">Jobs</a> by</span> <a STYLE="text-decoration:none" href="http://employers.simplyhired.com/e/25542/2014-03-05/65bqd/76691605"><img src="http://www.jobamatic.com/c/jbb/images/simplyhired.png" alt="Simply Hired"></a>
</div>
<!-- 
	<span style="font-size:20px; color:green;">*</span> <a STYLE="text-decoration:none" href="http://www.simplyhired.com/">
        <img border="0" title="Jobs by SimplyHired" alt="Jobs by SimplyHired" src="http://www.christianvolunteering.org/imgs/Logo_SHpartner.png"/>
    </a>
 -->    
</div>

<div id="nav_close" class="nav">
<ul id="pager_close" class="pager"></ul>  <ul id="pager-header_close" class="pager-header"></ul> 
</div>

</div>


<br><br>
		

</div></div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%//@ include file="/template/footer.inc" %>

<!-- start file footer.inc -->
<div id="footer">
	

<!-- start file cv_footer.inc -->
<!-- @ include file="/template_include/footer_chrisvol.inc" -->
<!-- BEGIN Footer -->

<div id="footer">
<!-- begin:  LINKS -->
<table class="footer_sections" cellspacing="0">
<tr>
	<td class="footer_sections_left">Partner Volunteer Sites: </td>
	<td class="footer_sections_right">
		<!--a href="http://ccda.christianvolunteering.org">CCDA</a><br/-->
		<a href="http://uywi.christianvolunteering.org" title="ChristianVolunteering.org: Christian Volunteer and Short Term Missions Opportunities">UYWI</a><br/>
		<a href="http://hlic.christianvolunteering.org" title="Here's Life Inner City">HLIC</a><br/>
		<a href="http://missionamerica.christianvolunteering.org" title="ChristianVolunteering.org: Christian Volunteer and Short Term Missions Opportunities">Mission America</a>
	</td>
	<td class="footer_sections_right">
		<a href="http://agrm.christianvolunteering.org" title="ChristianVolunteering.org: Christian Volunteer and Short Term Missions Opportunities">AGRM</a><br/>
		<a href="http://putyourfaithinaction.christianvolunteering.org" title="TechMission Faith In Action Volunteer Engine">FaithInAction</a><br/>
		<a href="<%=aszPortal%>/register.do?method=showSyndicationPartners">Syndication Partners</a>
	</td>
	<td class="footer_sections_right">
		<!--<a href="http://salvationarmy.christianvolunteering.org">Salvation Army</a><br/> -->
		<a href="http://gospel.christianvolunteering.org" title="ChristianVolunteering.org: Christian Volunteer and Short Term Missions Opportunities">Gospel.com</a><br/>
		<a href="http://www.catholicvolunteering.org" title="Search thousands of Catholic short term missions trips an volunteer opportunities: CatholicVolunteering.org">Catholic Volunteer Opportunities</a><br/>
		<a href="http://www.ivolunteering.org" title="iVolunteering Network: Matching Volunteers with Urban Outreach Opportunities">iVolunteering</a>
	</td>
</tr>

<% if(  (aszHostID.equalsIgnoreCase("voleng1")) 
){ %>  
<tr>
	<td class="footer_sections_left"> Regional Volunteer Sites:</td>
	<td class="footer_sections_right">
		<a href='http://www.christianvolunteering.org/s/ct/Los_Angeles,_CA/ctp/opp/results.jsp#fq=city_tax:"Los Angeles, CA"&fq=content_type:opportunity' title="Los Angeles, CA: Christian Volunteer and Short Term Missions Opportunities: ChristianVolunteering.org">Los Angeles, CA</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/New_York,_NY/ctp/opp/results.jsp#fq=city_tax:"New York, NY"&fq=content_type:opportunity' title="New York, NY: Christian Volunteer and Short Term Missions Opportunities: ChristianVolunteering.org">New York, NY</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/Chicago,_IL/ctp/opp/results.jsp#fq=city_tax:"Chicago, IL"&fq=content_type:opportunity' title="Chicago, IL: Christian Volunteer and Short Term Missions Opportunities: ChristianVolunteering.org">Chicago, IL</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/Boston,_MA/ctp/opp/results.jsp#fq=city_tax:"Boston, MA"&fq=content_type:opportunity' title="Boston, MA: Christian Volunteer and Short Term Missions Opportunities: ChristianVolunteering.org">Boston, MA</a>
	</td>
	<td class="footer_sections_right">
		<a href='http://www.christianvolunteering.org/s/ct/Atlanta,_GA/ctp/opp/results.jsp#fq=city_tax:"Atlanta, GA"&fq=content_type:opportunity' title="Atlanta, GA: Christian Volunteer and Short Term Missions Opportunities">Atlanta, GA</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/Washington,_DC/ctp/opp/results.jsp#fq=city_tax:"Washington, DC"&fq=content_type:opportunity' title="Washington, DC: Christian Volunteer and Short Term Missions Opportunities">Washington, DC</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/Philadelphia,_PA/ctp/opp/results.jsp#fq=city_tax:"Philadelphia, PA"&fq=content_type:opportunity' title="Philadelphia, PA: Christian Volunteer and Short Term Missions Opportunities">Philadelphia, PA</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/Dallas,_TX/ctp/opp/results.jsp#fq=city_tax:"Dallas, TX"&fq=content_type:opportunity' title="Dallas, TX: Christian Volunteer and Short Term Missions Opportunities">Dallas, TX</a>
	</td>
	<td class="footer_sections_right">
		<a href='http://www.christianvolunteering.org/s/ct/Minneapolis-St;_Paul,_MN/ctp/opp/results.jsp#fq=city_tax:"Minneapolis-St. Paul, MN"&fq=content_type:opportunity' title="Minneapolis-St. Paul: Christian Volunteer and Short Term Missions Opportunities">Minneapolis-St. Paul, MN</a><br/>
		<a href='http://www.christianvolunteering.org/s/ct/San_Diego,_CA/ctp/opp/results.jsp#fq=city_tax:"San Diego, CA"&fq=content_type:opportunity' title="San Diego, CA: Christian Volunteer and Short Term Missions Opportunities">San Diego, CA</a><br/>
		<a href="http://www.christianvolunteering.org/zipmap.jsp" title="Christian Volunteer Network Sitemap">Zip Code Sitemap</a><br/>
		<a href="/virtualvolunteer.jsp" title="Virtual Volunteer and Online Volunteering Opportunities: ChristianVolunteering.org">Virtual Volunteering</a>
	</td>
</tr>
<% } %>

<tr>
	<td class="footer_sections_left">Sections:</td>
	<td class="footer_sections_right">
                <a title="Christin Foundation Grants" href="http://www.christianvolunteering.org/foundationgrants.jsp">Christian Foundation Grants Directory</a><br/>
		<a href="http://www.christianvolunteering.org/churchvolunteer.jsp" title="Church Volunteers' Resources and Search">Church Volunteer Resources</a><br/> 
		<a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="Short Term Missions Trip Search">Short Term Missions</a><br/>
		<a href="/christianfamilyvolunteering.jsp" title="Christian family volunteer opportunities, family hmethod missions trips, service learning">Christian Family Volunteering</a><br/>
		<a href="/medicalmissions.jsp" title="Medical Missions Volunteering">Medical Missions Volunteering</a>
	</td>
	<td class="footer_sections_right">
		<a href="/organizationlistings.jsp" title="Christian Volunteer and Short Term Missions Organizations">Organization Sitemap</a><br/>
		<a href="/directoryorgs.jsp" title="ChristianVolunteering.org: Christian Volunteer and Short Term Missions Organizations">Organization Directory</a><br/>
		<a href="http://www.christianvolunteering.org/christiangapyear.jsp" title="Christian gap year volunteer internships and short-term missions">Christian Gap Year</a><br/>
		<a href="http://www.christianvolunteering.org/disasterrelief.jsp" title="Christian Disaster Relief Volunteering">Christian Disaster Relief Volunteering</a><br/>
		<a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions" title="UrbanSermons.org | Encyclopedia of Volunteering: Encyclopedia of Urban Ministry">Volunteer Management Wiki</a><br/>
	</td>
	<td class="footer_sections_right">
		<a href="/volunteerlistings.jsp" title="Christian volunteer, church volunteer, urban ministry service, and short term missions volunteer listings">Volunteer Listings</a><br/>
		<a href="/directory.jsp" title="Christian volunteer, church volunteer, urban ministry service, and short term missions volunteer listings">Volunteer Sitemap</a><br/>
		<a href="/christianorphanage.jsp" title="Volunteer in a Christian Orphanage: Find Missions Trips: ChristianVolunteering.org">Volunteer in a Christian Orphanage </a><br/>
		<a href="/technologymissions.jsp" title="Volunteer in Technology in Missions">Volunteer in Technology in Missions</a><br/>
		<a href="/christianjobs.jsp" title="Christian Jobs in Ministry">Christian Jobs in Ministry</a>
	</td>
</tr>

<tr>
	<td class="footer_sections_left">TechMission Sites:</td>
	<td class="footer_sections_right">
<a href="http://www.techmission.org" title="Technology and Urban Ministry, Christian Community Computer Centers Addressing the Digital Divide">TechMission.org</a><br/>
<a href="http://www.urbanministry.org" title="UrbanMinistry.org: Urban ministry MP3s, talks, podcast, workshops, presentations, Webcasts, training, on racism, racial reconciliation, ethnic identity, classism, sexism, grants">UrbanMinistry.org</a><br/>
<a href="http://www.urbansermons.org" title="Urban Sermons, Audio, Video, Articles, MP3 Podcasts, and Church Resources">UrbanSermons.org</a>
	</td>
	<td class="footer_sections_right">
	<a href="http://www.cityvision.edu" title="Online Urban Ministry Courses in Theology, Missions, Accounting, CFRE Fundraising, Management, Evangelism and Counseling">CityVision.edu</a><br/>
<a href="http://www.ilsnova.com">ILS NOVA: Adult Basic Education, Christian GED</a><br />
<a href="http://www.safefamilies.org" title="Free Parental Controls & Internet Filtering Software We-Blocker Download - Online Safety Workshops - Christian Accountability, Pornography and Sex Addiction Recovery">
SafeFamilies.org</a><br/>
	</td>
	<td class="footer_sections_right">
		<a href="http://www.cityvisioninternships.org" title="One Year and Summer Christian College Work Study Internships in Urban Ministry">Christian Ministry Internships</a>
                <a href="http://www.christianfreeware.org/" title="Free Christian Software CD for download including church management software, worship projection, accountability software, freeware, open source ministry and Bible software"> 
		ChristianFreeware.org</a><br/>
		<a href="http://www.christianvolunteering.org" title="Christian Volunteer Directory: Volunteering for Urban Ministries and Short Term Urban Missions Opportunities">Volunteer: ChristianVolunteering.org</a> <br/>
	</td>
</tr>

</table><!-- end:LINKS -->

<table class="footer_links"><!-- Table that contains share buttn and CV links-->
<tr>
 <td style="text-align: left; width:50px; padding-top: 30px;"></td> 

<!-- ADS INSERTED HERE -->
<td>
<div class="advertisement" id="group-tids-4496">
<script type="text/javascript" src="http://www.christianvolunteering.org/modules_um/ad/serve.php?q=1&amp;k=7a15268e1dcf7769adc47075b2f267d1&amp;t=4496"></script>
</div>
</td>

<!--/ADS INSERTED-->

<td class="copyright">

<p>
<a href="http://www.urbanministry.org/frontpageview/feed"><img src="http://www.urbanministry.org/imgs/Facebook_Buttons_Images/SocialMediaImages4CVFooter.jpg" alt="social media networks" border="0" align="texttop" usemap="#Map"/>
<map name="Map">
  <area shape="rect" coords="0,2,22,24" href="http://www.facebook.com/christianvolunteering">
  <area shape="rect" coords="25,2,47,21" href="http://www.youtube.com/techmissioninc">
  <area shape="rect" coords="48,2,73,20" href="http://twitter.com/remotefaith">
</map>
</a>
</p>

<p style="font-size: 1.2em;"><a style="text-decoration:underline" href="mailto:info@christianvolunteering.org">Email us</a> for support.</p>

<p>
<!-- main page feed -->
<a href="http://www.urbanministry.org/frontpageview/feed"><img src="http://www.urbanministry.org/files/images/rss-sm.png" align="texttop" alt="RSS" title="RSS feed" width="16" height="16" /> RSS</a> |
<!--/ main page feed -->
<!-- Buttn Facebook -->
<script>function fbs_click() {u=location.href;t=document.title;window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(u)+'&t='+encodeURIComponent(t),'sharer','toolbar=0,status=0,width=626,height=436');return false;}</script>
<a href="http://www.facebook.com/share.php?u=<url>" class="fb_share_button" onClick="return fbs_click()" target="_blank" style="text-decoration:none;">Share</a> |
<!--/ Buttn Facebook -->
<a href="http://www.urbanministry.org/terms">privacy</a> | 
<a href="http://www.christianvolunteering.org/apiaccess.jsp">api</a> |
<a href="<%=aszPortal%>/contact.jsp" title="Contact Christian Volunteering">contact</a> | 
<a href="mailto:feedback@christianvolunteering.org">feedback</a> | 
<a href="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&id=13"><img height="16" align="texttop" title="donate to TechMission" alt="donate" src="http://www.urbanministry.org/imgs/donate.gif"/></a> <br/> 
  
</p>
</td>

</tr>
</table><!-- end: Table that contains UM logo, share buttn and UM links-->

<script type="text/javascript" src="http://www.urbanministry.org/themes/universal_assets/network_band/network-band.js"></script>

<!-- include quanticast footer -->

<!-- include footer login -->
<%//@ include file="/template_include/footer_login.inc" %>


<!-- end file cv_footer.inc -->

<!-- back to /template/footer.inc -->



</div>


</div>
<%@ include file="/template_include/footer_google_analytics.inc" %>

</body>
</html>

<!-- end file footer.inc -->

<!-- end footer information -->


