<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page language="java" %>
<%@ page import="com.abrecorp.opensource.base.*" %>
<%@ page import="com.abrecorp.opensource.dataobj.*" %>
<%@ page import="com.abrecorp.opensource.voleng.brlayer.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
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

String aszAuthPage="true";
String aszGoalTracking="";
String aszPath = request.getServletPath();  
String aszCurrentURL = "";
String aszHostID="default";
String aszRemoteHost = request.getHeader("host"); 
String aszReferer = request.getHeader("referer"); 
String aszSecondaryHost="default";
String aszEmailHost="info@christianvolunteering.org";
String aszNarrowTheme="";
String aszPartnerTemplate="";
String aszNoSearchNav="false";
String aszEmptySearch="";
String aszLoggedIn="true";
//out.print("<!--  Referer is " + aszReferer +  "-->");

String aszPortalTemplate="main";
int iPortalUID=0;

String aszMobileSubdomain="";

response.setHeader("Cache-Control", "no-cache");
response.setHeader("P3P", "CP=\"CAO PSA OUR\"");


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
}else if(	(aszRemoteHost.equalsIgnoreCase( "ivolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.ivolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "ivol.org:7001" ))
){
    aszHostID="volengivol";
    aszSecondaryHost="volengivol";
    aszEmailHost="info@ivolunteering.org";
}else if(	(aszRemoteHost.equalsIgnoreCase( "m.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.m.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.chrisvol.org:7001" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.cv.org:7001" ))
){
    aszHostID="volengmobile";
	aszMobileSubdomain="m.christianvolunteering.org";
}else if(	(aszRemoteHost.equalsIgnoreCase( "m.ivolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "www.m.ivolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "m.ivol.org:7001" ))	||
			(aszRemoteHost.equalsIgnoreCase( "partners.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "partners.cv.org:7001" ))
){
    aszHostID="volengivol";
    aszSecondaryHost="volengivol";
	aszMobileSubdomain="m.christianvolunteering.org";
    aszEmailHost="info@ivolunteering.org";
}else if(	(aszRemoteHost.equalsIgnoreCase( "volunteer.urbanministry.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "volunteer.um.org:7001" )) ||
			(aszRemoteHost.equalsIgnoreCase( "urbanministry.christianvolunteering.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "um.cv.org" )) ||
			(aszRemoteHost.equalsIgnoreCase( "um.cv.org:7001" ))
){
    aszHostID="volengurbmin";
//    aszSecondaryHost="volengivol";
    aszEmailHost="info@urbanministry.org";

}else if(	aszRemoteHost.equalsIgnoreCase( "www.catholicvolunteering.org" )					||
			aszRemoteHost.equalsIgnoreCase( "catholicvolunteering.org" )						||
			aszRemoteHost.equalsIgnoreCase( "catholicvolunteering.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "catholic.cv.org:7001" )
){
    aszHostID="volengcatholic";
}else if(	aszRemoteHost.equalsIgnoreCase( "www.churchvolunteering.org" )						||
			aszRemoteHost.equalsIgnoreCase( "churchvolunteering.org" )							||
			aszRemoteHost.equalsIgnoreCase( "www.churchvolunteering.com" )						||
			aszRemoteHost.equalsIgnoreCase( "churchvolunteering.com" )							||
			aszRemoteHost.equalsIgnoreCase( "churchvolunteering.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "www.churchvol.org" )								||
			aszRemoteHost.equalsIgnoreCase( "churchvol.org" )									||
			aszRemoteHost.equalsIgnoreCase( "www.churchvol.org:7001" )							||
			aszRemoteHost.equalsIgnoreCase( "churchvol.org:7001" )								||
			aszRemoteHost.equalsIgnoreCase( "church.cv.org:7001" )
){
    aszHostID="volengchurch";
}else if(aszRemoteHost.equalsIgnoreCase( "fycsandbox.christianvolunteering.org" )){
    aszHostID="volengfycsandbox";
}else if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )			||
			aszRemoteHost.equalsIgnoreCase( "findyourcalling.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "facebook.cv.org:7001" )
){
    aszHostID="volengfacebook";
}else if(	aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )			||
			aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )		||
			aszRemoteHost.equalsIgnoreCase( "worldchanger.ivolunteering.org" )		||
			aszRemoteHost.equalsIgnoreCase( "worldchanger.ivolunteering.org:8008" )
){
    aszHostID="volengfacebookivol";
    aszSecondaryHost="volengivol";
    aszEmailHost="info@ivolunteering.org";
}else if(	aszRemoteHost.equalsIgnoreCase( "example.christianvolunteering.org" )	||
			aszRemoteHost.equalsIgnoreCase( "example.cv.org:7001" )
){
    aszHostID="volengexample";
}


String aszSubdomain = ""; 
String aszIncludePath = "";
String aszNavigationIncludeFile = "";
String aszPortal="";
String aszRailsPortal="";
if(request.getScheme() != null && 
   request.getServerName() != null &&
   request.getContextPath() != null) {
     String port = "";
     if(request.getServerPort() == 7001) port = ":3000";
     aszRailsPortal = request.getScheme() + "://" + request.getServerName() + port + request.getContextPath();
}
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

if(aszHostID.equalsIgnoreCase( "volengivol" )){
 	aszSubdomain = "iVolunteering.org"; 
 	aszIncludePath = "/ivol/template/";
} else if(aszHostID.equalsIgnoreCase( "volengurbmin" )){
 	aszSubdomain = "UrbanMinistry.ChristianVolunteering.org"; 
 	aszIncludePath = "/urbanministry/template/";
} else if(aszHostID.equalsIgnoreCase( "volengcatholic" )){
 	aszSubdomain = "www.CatholicVolunteering.org"; 
 	aszIncludePath = "/catholicvolunteering/template/";
} else if(aszHostID.equalsIgnoreCase( "volengchurch" )){
        aszSubdomain = "www.ChurchVolunteering.org";
        aszIncludePath = "/churchvolunteering/template/";
}else if(aszHostID.equalsIgnoreCase( "volengfycsandbox" )){
 	aszSubdomain = "FindYourCalling.ChristianVolunteering.org"; 
 	aszIncludePath = "/fycsandbox/template/";
}else if(aszHostID.equalsIgnoreCase( "volengfacebook" )){
 	aszSubdomain = "FindYourCalling.ChristianVolunteering.org"; 
 	aszIncludePath = "/facebook/template/";
}else if(aszHostID.equalsIgnoreCase( "volengfacebookivol" )){
 	aszSubdomain = "WorldChanger.iVolunteering.org"; 
 	aszIncludePath = "/worldchanger/template/";
} else{
 	aszSubdomain = "www.ChristianVolunteering.org"; 
 	aszIncludePath = "/template/";
} 

// set whether this is a narrow theme or a wide theme (used for css theming/templating)
if (	
		aszSecondaryHost.equalsIgnoreCase("volengmobile") 
) { 
	aszNarrowTheme="true";
}

// set whether this is a normal template or partner template (used for css theming/templating)
if (
                aszHostID.equalsIgnoreCase("volengexample") ||
                aszHostID.equalsIgnoreCase("volengfacebook") ||
				aszHostID.equalsIgnoreCase("volengfbsandbox") ||
                aszHostID.equalsIgnoreCase("volengfacebookivol") ||
                ( aszHostID.equalsIgnoreCase("volengchurch") && request.getContextPath().length()>0 )
) {
        aszPartnerTemplate="true";
}


if( false == UserSessionBean.IsSessionLoggedIn( request ) ){	
%>

    	<jsp:forward page="/register.do?method=showlogin" />
<%}
PersonInfoDTO aCurrentUserObj = UserSessionBean.getCurrentUser( request);
PersonInfoDTO aCurrentUserObjLoggedIn = LoginBean.getCurrentUser( request);
int iLoggedInUserOrgNID=0, iLoggedInUserOppNID=0;
if(aCurrentUserObjLoggedIn.getUserUID() > 0){ 
	iLoggedInUserOrgNID = aCurrentUserObjLoggedIn.getORGNID();
	iLoggedInUserOppNID = aCurrentUserObjLoggedIn.getOPPNID();
}

if(null == aCurrentUserObj){	
%>
    	<jsp:forward page="/register.do?method=showlogin" />
<%
}

String aszTemp=null;
int iTopMenuReq=202;
int iSubPageType=101;
int iPageOptionType=101;
int iRetCode=0;
String aszHTMLTitle = "TechMission Volunteer Engine";

boolean bSkipBanner=false;
String aszSkipBanner="";
try{
	aszSkipBanner=request.getParameter("skipbanner");
	if(aszSkipBanner.equals("skipbanner")){
		bSkipBanner=true;
	}
}catch(Exception e){}


String aszOrgOrChurch = "Organization";
String aszOrgOrChurchPlural = "Organizations";
String aszOrgOrChurchDescript = "Organizational";
String aszOrgOrChurchOpp = "Volunteer";
String aszChrisVolOrChurch = "ChristianVolunteering.org";
String aszNoDisplayChurch = "";
String aszPortalReqFields = "<span class=\"criticaltext\">*</span>";
String aszPortalStyleBold = "style=\"font-weight:bold;\"";

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
String portal=aszPortal; 
String tempPortal=portal;

if(tempPortal.length()>7) if(tempPortal.startsWith("/voleng")) tempPortal=tempPortal.substring(8);
else if(tempPortal.length()>0) if(tempPortal.startsWith("/")) tempPortal=tempPortal.substring(1);

if(aszPortal.equals("/voleng")){
	tempPortal="";
	portal="";
}
boolean bHasAssoc=false;
boolean bManageAssoc=false;
if(tempPortal.length()>0 && tempPortal.equalsIgnoreCase(aCurrentUserObj.getNatlAssociationPortal())) bManageAssoc=true;
if(aCurrentUserObj.getNatlAssociationPortal().length()>0)	bHasAssoc=true;

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
boolean b_includeAjaxSolrFlag=false; 
boolean b_includeAjaxSolr=false; 
if( aszPath.equalsIgnoreCase("/voleng2/volunteerdashboard.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp")
){
	if(	aszHostID.equalsIgnoreCase("voleng1") 	|| 
		aszHostID.equalsIgnoreCase("default") 	|| 
		aszHostID.equalsIgnoreCase("volengfamily") 	|| 
		aszHostID.equalsIgnoreCase("volengcatholic") 	|| 
		aszHostID.equalsIgnoreCase("volengchurch") 	|| 
	aszHostID.equalsIgnoreCase("volengmobile") 	|| 
		aszHostID.equalsIgnoreCase("default")
	){
		b_includeAjaxNavigation=true;
		b_includeLeftSidebar=true;
		b_includeAjaxSolrFlag=true;
	}else if(	aszHostID.equalsIgnoreCase("volengivol") 	|| 
		aszHostID.equalsIgnoreCase("volengfacebook") 	|| 
		aszHostID.equalsIgnoreCase("default")
	){
		b_includeAjaxSolrFlag=true;
	}
}else if(	aszPath.equalsIgnoreCase("/voleng2/search_results.jsp")	||
    aszPath.equalsIgnoreCase("/voleng2/search_results_embed.jsp")	||
	aszPath.equalsIgnoreCase("/voleng2/search_results_ajax-solr.jsp")	||
	aszPath.equalsIgnoreCase("/voleng2/solr_directory.jsp")	||
    aszPath.equalsIgnoreCase("/index.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/search.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/add_org_contact.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/association_management.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/downloaduploadedfile.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/FileUploadAndSave.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/nonp-addlisting.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/nonp-edit55.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/nonp-edit66.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/nonp-editlisting.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/nonp-start33.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/nonp-start44.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/nonp-start55.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/nonp-view11.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/opp-org_contact_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/opp_contact_add.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/opp_contact_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/opp_contact_remove.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_admin-contact_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_admin_add.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_admin_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_admin_remove.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_contact_add.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_contact_remove.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/org_share_on_website.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/orgmanage_listings.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/orgmanagement.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/portal_banner_post.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/portal_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/portal_opportunities.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/volunteer_referrals.jsp")  ||
	aszPath.startsWith("/voleng2/portal_results")
){ 
	if(	aszHostID.equalsIgnoreCase("voleng1") 	|| 
		aszHostID.equalsIgnoreCase("volengfamily") 	|| 
		aszHostID.equalsIgnoreCase("volengcatholic") 	|| 
		aszHostID.equalsIgnoreCase("volengchurch") 	|| 
		aszHostID.equalsIgnoreCase("default")
	){
		aszPortalTemplate="main";
		b_includeLeftSidebar=true;
	}
}
if(
    aszPath.equalsIgnoreCase("/voleng2/portal_banner_post.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/portal_manage.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/portal_opportunities.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/application_sent.jsp")  
){
	aszPortalTemplate="basic";
		b_includeLeftSidebar=false;
}
if(	aszPath.equalsIgnoreCase("/voleng2/search_results.jsp")	||
	aszPath.equalsIgnoreCase("/voleng2/search_results_ajax-solr.jsp")	||
	aszPath.equalsIgnoreCase("/voleng2/solr_directory.jsp")	||
    aszPath.equalsIgnoreCase("/index.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/search.jsp")  ||
    aszPath.equalsIgnoreCase("/voleng2/application_list.jsp")  ||
	aszPath.equalsIgnoreCase("/voleng2/test-anon.jsp"
)
){ 
	if(b_includeAjaxSolrFlag ||
    aszPath.equalsIgnoreCase("/voleng2/application_list.jsp")){
		b_includeAjaxSolr=true;
	}
}
if(aszMethodName.equals("showDrupalLogin") || aszMethodName.equals("loadOneApplication")){
	b_includeAjaxSolr=false;
}

boolean bHasOpps = false;
ArrayList aHasOppsList = new ArrayList(2);

String aszCanonicalLink = "";

String aszGoogleMapsKey = "AIzaSyBnyQenhINsijpU8vQpvMIDr7iaaKZZLa4";
if(aszRemoteHost.contains( ":7001" ) )	aszGoogleMapsKey ="";

int LOADBY_UID_CONTACT=106;
%>
<!-- b_includeAjaxSolr is <%=b_includeAjaxSolr%> -->