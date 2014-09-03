
<%@ page contentType="text/html; charset=UTF-8"%>  <% //Ztext/html;charset=windows-1252"%>

<%@ page language="java" %>
<%@ page import="com.abrecorp.opensource.base.*" %>
<%@ page import="com.abrecorp.opensource.dataobj.*" %>
<%@ page import="com.abrecorp.opensource.voleng.brlayer.*" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<jsp:useBean id="LoginBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />

<% 
boolean bHeaderSet=false;

String aszURLdomain="";
//Application API Key and application secret from creating app in FB
String appapikey = "";
String appsecret = "";
String appid = "";
// used for all versions of christianvolunteering for posting iwanttohelp to the fb user's feed, if they're logged in
String tempappid="";
String tempapikey="";
String tempsecretkey="";

String aszAuthPage="false";
String aszGoalTracking="";
String aszTemp=null;
int iRetCode=0;
String aszNarrowTheme="";
String aszPartnerTemplate="";
String aszNoSearchNav="false";
String aszEmptySearch="";
String aszLoggedIn="";

String aszPortalTemplate="main";
int iPortalUID=0;

String aszPath = request.getServletPath(); 
String aszCurrentURL = "";
String aszMobileSubdomain="";

if(	true == LoginBean.IsSessionLoggedIn( request )	){
	response.setHeader("Cache-Control", "no-cache");
}else if(aszPath.equalsIgnoreCase("/voleng2/login1.jsp") || aszPath.equalsIgnoreCase("/voleng2/loginstatus.jsp")){
	response.setHeader("Cache-Control", "no-cache");
}else {
	if(! (request.getParameter("method") == null) ){
		if(request.getParameter("method").equals("logout")){
			response.setHeader("Cache-Control", "no-cache");
		}else{
			response.setHeader("Cache-Control", "public, max-age=10800");
		}
	}else{
		response.setHeader("Cache-Control", "public, max-age=10800");
	}
}
response.setHeader("P3P", "CP=\"CAO PSA OUR\"");

//HttpSession session = req.getSession(true); //Creating a Session instance
if((!	(aszPath.equalsIgnoreCase("/voleng2/myaccountsum1.jsp"))
)||(	session.getAttribute("MyIdentifier1") == null )
 ){
	session.setAttribute("MyIdentifier1","");  // clearing Value in session Object
}
/*
if(!	(aszPath.equalsIgnoreCase("/voleng2/myaccountsum1.jsp")) ){
	session.putValue ("MyIdentifier1","");  // clearing Value in session Object
}
*/


String aszHTMLTitle = "TechMission Volunteer Engine";
String aszHostID="default";
String aszRemoteHost = request.getHeader("host"); 
String aszReferer = request.getHeader("referer"); 
String aszSecondaryHost="default";
String aszEmailHost="info@christianvolunteering.org";

String aszLandingPage = "";
try{
	if(request.getParameter("landingpage") != null){
		aszLandingPage = request.getParameter("landingpage");
	}
}catch(NullPointerException e){
}

if(			(aszRemoteHost.equalsIgnoreCase( "christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "christianvolunteering.net" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.christianvolunteering.net" )) ||
			(aszRemoteHost.equalsIgnoreCase( "christianvolunteering.com" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.christianvolunteering.com" )) ||
			(aszRemoteHost.equalsIgnoreCase( "chrisvol.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "chrisvol.org:7001" )) ||
			(aszRemoteHost.equalsIgnoreCase( "cv.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "cv.org:7001" ))
){
    aszHostID="voleng1";
}else if(	(aszRemoteHost.equalsIgnoreCase( "m.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.m.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.chrisvol.org:7001" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.cv.org:7001" ))
){
    aszHostID="volengmobile";
	aszMobileSubdomain="m.christianvolunteering.org";
}else if(	(aszRemoteHost.equalsIgnoreCase( "m.churchvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.m.churchvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.churchvol.org:7001" ))
){
    aszHostID="volengchurch";
	aszMobileSubdomain="m.christianvolunteering.org";
}else if(	(aszRemoteHost.equalsIgnoreCase( "volunteer.urbanministry.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "volunteer.um.org:7001" )) ||
			(aszRemoteHost.equalsIgnoreCase( "urbanministry.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "um.cv.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "um.cv.org:7001" ))
){
    aszHostID="volengurbmin";
    aszEmailHost="info@urbanministry.org";
}else if(	aszRemoteHost.equalsIgnoreCase( "agrm.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "agrm.cv.org:7001" )				||
			aszRemoteHost.equalsIgnoreCase( "agrm.cv.org" )
){
    aszHostID="volengagrm";
    aszSecondaryHost="partner";
}else if(	aszRemoteHost.equalsIgnoreCase( "www.catholicvolunteering.org" )					||
			aszRemoteHost.equalsIgnoreCase( "catholicvolunteering.org" )						||
			aszRemoteHost.equalsIgnoreCase( "catholicvolunteering.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "catholic.cv.org:7001" )
){
    aszHostID="volengcatholic";

}else if(	aszRemoteHost.equalsIgnoreCase( "hlic.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "hlic.cv.org:7001" )				||
			aszRemoteHost.equalsIgnoreCase( "hlic.cv.org" )
){
    aszHostID="volenghlic";
    aszSecondaryHost="partner";
}else if(	aszRemoteHost.equalsIgnoreCase( "example.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "example.cv.org:7001" )
){
    aszHostID="volengexample";
}


String aszSubdomain = ""; 
String aszIncludePath = "";
String aszNavigationIncludeFile = "";
String aszPortal="";
String aszPortalDirectory="";

if( request.getParameter("portal") != null ){	
	if(	request.getParameter("portal").length() > 0	
//		&& aszHostID.equalsIgnoreCase("volengchurch")
	){
		aszPortal+=request.getParameter("portal");
		if(! (aszPortal.startsWith("/")) ){
			aszPortal = "/" + aszPortal;
		}
		aszPortalDirectory+=aszPortal;
	}
}
if(	request.getContextPath().length()>0	){
	aszPortal = request.getContextPath() + aszPortal;
}
// set aszHostID so that things like google analytics still work for the national associations we link to from our footer
if( aszPortal.contains("MissionAmerica") ){
    aszHostID="volengmissionamerica";
    aszSecondaryHost="partner";
}else if( aszPortal.contains("uywi") ){
    aszHostID="volenguywi";
    aszSecondaryHost="partner";
}else if( aszPortal.contains("hlic") ){
}else if( aszPortal.contains("agrm") ){
    aszHostID="volengagrm";
    aszSecondaryHost="partner";
}
//out.print("<!-- " +  aszPortal + " -->");

if(aszHostID.equalsIgnoreCase( "volengurbmin" )){
 	aszSubdomain = "UrbanMinistry.ChristianVolunteering.org"; 
 	aszIncludePath = "/urbanministry/template/";
} else if(aszHostID.equalsIgnoreCase( "volengcatholic" )){
 	aszSubdomain = "www.CatholicVolunteering.org"; 
 	aszIncludePath = "/catholicvolunteering/template/";
} else if(aszHostID.equalsIgnoreCase( "volenghlic" )){
 	aszSubdomain = "HLIC.ChristianVolunteering.org"; 
 	aszIncludePath = "/hlic/template/";
} else if(aszHostID.equalsIgnoreCase( "volengfia" )){
 	aszSubdomain = "PutYourFaithInAction.ChristianVolunteering.org"; 
 	aszIncludePath = "/faithinaction/template/";
} else{
 	aszSubdomain = "www.ChristianVolunteering.org"; 
 	aszIncludePath = "/template/";
} 

// set whether this is a narrow theme or a wide theme (used for css theming/templating)
if (	
		aszHostID.equalsIgnoreCase("volenghlic") ||
		aszHostID.equalsIgnoreCase("volengfia") ||
		aszSecondaryHost.equalsIgnoreCase("volengmobile") 
) { 
	aszNarrowTheme="true";
}



// by default, a given theme will include the new navigation_search; 
// set this string to "true" if it is NOT supposed to load/show it;
// 	- additionally, check for this to be set to "true" in order to show the search
if (	
		aszHostID.equalsIgnoreCase("volengagrm") || //?? could be added to include it??
		aszHostID.equalsIgnoreCase("volengfia") ||
		aszHostID.equalsIgnoreCase("volenghlic") 

) { 
	aszNoSearchNav="true";
}

//out.println("<!-- " + aszSecondaryHost + " -->");

PersonInfoDTO aCurrentUserObjLoggedIn = LoginBean.getCurrentUser( request);
int iLoggedInUserOrgNID=0, iLoggedInUserOppNID=0;
if(aCurrentUserObjLoggedIn.getUserUID() > 0){ 
	iLoggedInUserOrgNID = aCurrentUserObjLoggedIn.getORGNID();
	iLoggedInUserOppNID = aCurrentUserObjLoggedIn.getOPPNID();
}

boolean bSkipBanner=false;
String aszSkipBanner="";
try{
	if(request.getParameter("skipbanner") != null){
		aszSkipBanner=request.getParameter("skipbanner");
		if(aszSkipBanner.equals("skipbanner")){
			bSkipBanner=true;
		}
	}
}catch(Exception e){}
out.print("<!-- skipbanner is "+ bSkipBanner+" -->");

String aszOrgOrChurch = "Organization";
String aszOrgOrChurchPlural = "Organizations";
String aszOrgOrChurchDescript = "Organizational";
String aszOrgOrChurchOpp = "Volunteer";
String aszChrisVolOrChurch = "ChristianVolunteering.org";
String aszNoDisplayChurch = "";
String aszPortalReqFields = "<span class=\"criticaltext\">*</span>";
String aszPortalStyleBold = "style=\"font-weight:bold;\"";
if(aszHostID.equalsIgnoreCase("volengchurch")){
	aszOrgOrChurch = "Church";
	aszOrgOrChurchPlural = "Churches";
	aszOrgOrChurchDescript = "Church";
	aszOrgOrChurchOpp = "Ministry";
	aszChrisVolOrChurch = "ChurchVolunteering.org";
	aszNoDisplayChurch="style=\"display:none;\"";
	aszPortalReqFields = "";
	aszPortalStyleBold = "";
}


if( aszPortal.length()>0 && (! (aszPortal.equals("/voleng")) )){ 
	aszPortalTemplate="basic";
}	

boolean showPortalInfo=false;
if(	aszHostID.equalsIgnoreCase("voleng1") 	|| 
	aszHostID.equalsIgnoreCase("volengchurch")	|| 
	aszHostID.equalsIgnoreCase("default")	||
	aszHostID.equalsIgnoreCase("volengexample")
) {
	showPortalInfo=true;
}

String aszPortalName = "", aszPortalBanner = "", aszPortalHeaderTags = "", aszPortalHeader = "", aszPortalCSS = "", aszPortalFooter = "", 
	aszPortalOrgName = "", aszPortalNID = "", aszPortalUID = "", aszPortalType = "";
String aszPreviewSearch="",aszBackgroundColor="",aszBorderColor="",aszLinkTextColor="",aszTextColor="";
int iPortalNID = 0;

com.maxmind.geoip.Location location = null;
int maxmind_area_code = 0;
String maxmind_city = "";
String maxmind_countryCode = "";
String maxmind_countryName = "";
int maxmind_dma_code = 0;
float maxmind_latitude = 0f;
float maxmind_longitude = 0f;
int maxmind_metro_code = 0;
String maxmind_postal = "";
String maxmind_region = "";
try{
	location = (com.maxmind.geoip.Location) request.getAttribute("location");
	if(location != null){
		if(location.area_code > 0){
			maxmind_area_code=location.area_code;
		}
		if(location.city != null){
			maxmind_city=location.city;
		}
		if(location.countryCode != null){
			maxmind_countryCode=location.countryCode;
		}
		if(location.countryName != null){
			maxmind_countryName=location.countryName;
		}
		maxmind_dma_code=location.dma_code;
		maxmind_latitude=location.latitude;
		maxmind_longitude=location.longitude;
		if(location.metro_code > 0){
			maxmind_metro_code=location.metro_code;
		}
		if(location.postalCode != null){
			maxmind_postal=location.postalCode;
		}
		if(location.region != null){
			maxmind_region=location.region;
		}
	}
}catch(NullPointerException ex){
}catch (Exception e){
}

String aszMethodName = "";
try{
	aszMethodName = request.getParameter("method");
	if(aszMethodName==null) aszMethodName="";
}catch(NullPointerException e){
	aszMethodName = "";
}
boolean b_includeAjaxNavigation=false;
boolean b_includeLeftSidebar=false;
boolean b_includeAjaxSolr=false; 
if(	aszHostID.equalsIgnoreCase("voleng1") 	|| 
	aszHostID.equalsIgnoreCase("default") 	|| 
	aszHostID.equalsIgnoreCase("volengcatholic") 	|| 
	aszHostID.equalsIgnoreCase("volengchurch") 	|| 
	aszHostID.equalsIgnoreCase("volengmobile") 	|| 
	aszHostID.equalsIgnoreCase("default")
){
	b_includeAjaxNavigation=true;
	b_includeLeftSidebar=true;
	b_includeAjaxSolr=true;
}else if(	
		aszHostID.equalsIgnoreCase("default")
){
		b_includeAjaxSolr=true;
}
if(	aszPath.equalsIgnoreCase("/voleng2/search_results.jsp")	||
    aszPath.equalsIgnoreCase("/voleng2/search_results_embed.jsp")	||
    aszPath.equalsIgnoreCase("/voleng2/solr_directory.jsp") ||
	aszPath.equalsIgnoreCase("/index.jsp")	||
    aszPath.equalsIgnoreCase("/voleng2/search.jsp") 
){ 
	b_includeAjaxSolr=true;
}
if(aszMethodName.equals("showDrupalLogin")){
	b_includeAjaxSolr=false;
}

String aszCanonicalLink = "";
boolean bHasAssoc=false;
boolean bManageAssoc=false;

String aszGoogleMapsKey = "AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4";
if(aszRemoteHost.contains( ":7001" ) )	aszGoogleMapsKey ="";
%>
