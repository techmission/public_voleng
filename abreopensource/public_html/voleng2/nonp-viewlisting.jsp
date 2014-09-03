<%@ include file="/template_include/topjsploginreq1.inc" %>

<%! 
  public static String replaceQuotes(String s) {
    if(s == null) return null;
    return s.replaceAll("\"", "&quot;");
  }
%>

<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%//@ include file="/template_include/facebookapi_keys.inc" %>


<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<% 
String aszGoalPage="volunteer/opp";

if(aszSubdomain.equalsIgnoreCase("ChristianVolunteering.org"))
	aszSubdomain="www.ChristianVolunteering.org";
else if(aszSubdomain.equalsIgnoreCase("ChurchVolunteering.org"))
	aszSubdomain="www.ChurchVolunteering.org";
else if(aszSubdomain.equalsIgnoreCase("iVolunteering.org"))
	aszSubdomain="www.iVolunteering.org";

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );

String aszOrgName = org.getORGOrgName();
String aszOrgURLAlias = aszPortal + "/" + org.getORGUrlAlias() + ".jsp";
if(aszOrgName.length()<1){
	aszOrgName=opp.getORGOrgName();
	aszOrgURLAlias="";
}

String aszSource = opp.getOPPSource();

String aszDisplayFeeds = "display:inline";
String aszDisplayFeedsTable = "display:table-row";
String aszDisplayFeedsTableOrgName = "display:table-row";
if(aszSource.equalsIgnoreCase("feeds") || aszSource.equalsIgnoreCase("simplyhired")){
	aszDisplayFeeds = "display:none";
	aszDisplayFeedsTable = "display:none";
	if(aszOrgName.length()>0){
		aszDisplayFeedsTableOrgName = "display:table-row";
	}else{
		aszDisplayFeedsTableOrgName = "display:none";
	}
}
String aszDisplayLocationType = aszDisplayFeedsTable;
boolean b_isJob = false;
if(opp.getOPPPositionTypeTID() == 33389 || aszSource.equalsIgnoreCase("simplyhired")){
	b_isJob = true;
	aszDisplayLocationType = "display:none";
	aszDisplayFeedsTable = "display:none";
}

String aszCategory = "Service";
String aszHelpButtonText = "I Want To Help!";
String aszHelpButtonAction = "processIWantToHelp1";
if(aszSource.equalsIgnoreCase("feeds")){
	aszHelpButtonAction = "processIWantToHelpExternal";
}else if(aszSource.equalsIgnoreCase("simplyhired")){
	aszHelpButtonAction = "simplyhired";
}
if(b_isJob == true){
	aszCategory = "Job";
	if(aszSource.equalsIgnoreCase("simplyhired")){
		aszHelpButtonText = "Find out More";
	}else{
		aszHelpButtonText = "Apply";
	}
}
String aszType = "Volunteer Opportunity";
String aszTypeTitle = "Volunteer in ";
if(b_isJob==true){
	aszType = "Job Posting";
	aszTypeTitle = "";
}

String aszShortenedURLAlias = "";
String aszCountry="",aszState="",aszCity="";
String aszCountryISO= opp.getOPPAddrCountryName();
String aszStateAbbrev = opp.getOPPAddrStateprov();
aszCity=opp.getOPPAddrCity();
if(null != aStateList){
	for(int index=0; index < aStateList.size(); index++){
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
		if(null == aAppCode) continue;
		String aszOptRqCode = aAppCode.getCSPStateCode();
		if(aszOptRqCode.equalsIgnoreCase( aszStateAbbrev ) ) {
			if(aszCity.equalsIgnoreCase( "" ) ) {
				aszState=aAppCode.getCSPStateName();
			}else {
				aszState=aAppCode.getCSPStateName();
			}
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

if(opp.getOPPAddrLine1() != null && !opp.getOPPAddrLine1().isEmpty()) {
	aszFullAddress += opp.getOPPAddrLine1() + "<br/>";
	aszFullAddressTagged += "<span itemprop=\"streetAddress\">" + opp.getOPPAddrLine1() + "</span><br/>";
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
if(opp.getOPPAddrPostalcode() != null && !opp.getOPPAddrPostalcode().isEmpty() ){
	if(! 
		(
			opp.getOPPAddrPostalcode().equalsIgnoreCase("no postal") || 
			opp.getOPPAddrPostalcode().equalsIgnoreCase("na") || 
			opp.getOPPAddrPostalcode().equalsIgnoreCase("n/a")
		) 
	){
		aszFullAddress += opp.getOPPAddrPostalcode() + "<br/>";
		aszFullAddressTagged += "<span itemprop=\"postalCode\">" + opp.getOPPAddrPostalcode() + "</span>&nbsp;&nbsp;";
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


String aszSimplyHiredDomain = "simplyhired.com";
if(aszCountry.equalsIgnoreCase("ar")){
	aszSimplyHiredDomain = "simplyhired.com.ar";
}else if(aszCountry.equalsIgnoreCase("au")){
	aszSimplyHiredDomain = "simplyhired.com.au";
}else if(aszCountry.equalsIgnoreCase("at")){
	aszSimplyHiredDomain = "simplyhired.at";
}else if(aszCountry.equalsIgnoreCase("be")){
	aszSimplyHiredDomain = "simplyhired.be";
}else if(aszCountry.equalsIgnoreCase("br")){
	aszSimplyHiredDomain = "simplyhired.com.br";
}else if(aszCountry.equalsIgnoreCase("ca")){
	aszSimplyHiredDomain = "simplyhired.ca";
}else if(aszCountry.equalsIgnoreCase("cn")){
	aszSimplyHiredDomain = "simplyhired.cn";
}else if(aszCountry.equalsIgnoreCase("fr")){
	aszSimplyHiredDomain = "simplyhired.fr";
}else if(aszCountry.equalsIgnoreCase("de")){
	aszSimplyHiredDomain = "simplyhired.de";
}else if(aszCountry.equalsIgnoreCase("in")){
	aszSimplyHiredDomain = "simplyhired.co.in";
}else if(aszCountry.equalsIgnoreCase("ie")){
	aszSimplyHiredDomain = "simplyhired.ie";
}else if(aszCountry.equalsIgnoreCase("it")){
	aszSimplyHiredDomain = "simplyhired.it";
}else if(aszCountry.equalsIgnoreCase("jp")){
	aszSimplyHiredDomain = "simplyhired.jp";
}else if(aszCountry.equalsIgnoreCase("kr")){
	aszSimplyHiredDomain = "simplyhired.kr";
}else if(aszCountry.equalsIgnoreCase("mx")){
	aszSimplyHiredDomain = "simplyhired.mx";
}else if(aszCountry.equalsIgnoreCase("nl")){
	aszSimplyHiredDomain = "simplyhired.nl";
}else if(aszCountry.equalsIgnoreCase("pt")){
	aszSimplyHiredDomain = "simplyhired.pt";
}else if(aszCountry.equalsIgnoreCase("ru")){
	aszSimplyHiredDomain = "simplyhired.ru";
}else if(aszCountry.equalsIgnoreCase("za")){
	aszSimplyHiredDomain = "za.simplyhired.com";
}else if(aszCountry.equalsIgnoreCase("es")){
	aszSimplyHiredDomain = "simplyhired.es";
}else if(aszCountry.equalsIgnoreCase("")){
	aszSimplyHiredDomain = "simplyhired.se";
}else if(aszCountry.equalsIgnoreCase("ch")){
	aszSimplyHiredDomain = "simplyhired.ch";
}else if(aszCountry.equalsIgnoreCase("gb")){
	aszSimplyHiredDomain = "simplyhired.co.uk";
}

String aszSimplyHiredLinkDomain = "";
if(aszCountry.equalsIgnoreCase("za")){
	aszSimplyHiredLinkDomain = aszSimplyHiredDomain;
}else{
	aszSimplyHiredLinkDomain = "www." + aszSimplyHiredDomain;
}

if( opp.getOPPUrlAlias() != null){
	aszShortenedURLAlias = opp.getOPPUrlAlias();
	int iURLAlias = aszShortenedURLAlias.length();
	if( iURLAlias > 10 ){
		try{
			aszShortenedURLAlias = aszShortenedURLAlias.substring( 10 );//, iURLAlias );
			if(request.getParameter("redirected") != null){
			}else{
				// program in forwarding if the opportunity is Faith-Filled
				if(opp.getOPPFaithSpecTID()==21998 && aszSecondaryHost.equalsIgnoreCase("default") && (! aszHostID.equalsIgnoreCase("volengchurch")) ){ 
//					response.setStatus(301);
//					response.setHeader( "Location", "http://www.churchvolunteering.org/org.do?method=showOpport&urlalias=" + aszShortenedURLAlias + "&redirected=true" );		
//					response.setHeader( "Connection", "close" );
				}
				if(	(! aszCountryISO.equalsIgnoreCase("us") )  	&& 
					aszSecondaryHost.equalsIgnoreCase("default") 				&& 
					(! aszHostID.equalsIgnoreCase("volengchurch")) 
				){ 
/*					response.setStatus(301);
					response.setHeader( "Location", "http://www.churchvolunteering.org/org.do?method=showOpport&urlalias=" + aszShortenedURLAlias + "&redirected=true" );		
					response.setHeader( "Connection", "close" );
*/
				}
			}
		}catch(IndexOutOfBoundsException ex){
		}
	}
}

String aszOppNID = "" + opp.getOPPNID();
String aszOrgNID = "" + opp.getORGNID();

String aszPositionType = "";
if (opp.getOPPStatus2().length()>1){
	aszPositionType = "Local or Virtual Volunteering ";
}else{
	if (aszSecondaryHost.equalsIgnoreCase("volengivol") && opp.getOPPPositionTypeTID()== 4796) {
		aszPositionType = "Short-term Service / Volunteer Internship";
	}else{
		aszPositionType = opp.getOPPStatus();
	}
}

boolean bMapDisplay=false;
String aszDescription="", aszStatement="", aszRequirements="", aszAddDetail="", aszSTMReference="", aszCostPymntIncludes="";

String aszVirtual="display:none;";
float fLatitude = opp.getLatitude(), fLongitude=opp.getLongitude();
int iPositionTypeTID = opp.getOPPPositionTypeTID();
if(iPositionTypeTID!=4795){
	aszVirtual="display:table-row;";
	if(
		(fLatitude!=0.0 && fLongitude!=0.0) ||
		(aszCity.length()>0 && aszState.length()>0)	||
		(aszCity.length()>0 && aszCountry.length()>0)
	){
		bMapDisplay=true;
	}
}else if(aszState.length()>0 || aszCountry.length()>0){
   aszVirtual="display:table-row;";
	if(
		(fLatitude!=0.0 && fLongitude!=0.0) ||
		(aszCity.length()>0 && aszState.length()>0)	||
		(aszCity.length()>0 && aszCountry.length()>0)
	){
		bMapDisplay=true;
	}
}

String aszHrly="display: inline;";
double aszOppHrly = opp.getOPPCommitHourly();
if(aszOppHrly ==0.0){
	aszHrly="display: none;";
}

String aszNumPosition="display: inline;";
int aszOppNumPosition = opp.getOPPVolsNeeded();
if(aszOppNumPosition==0){
	aszNumPosition="display: none;";
}

String aszNumVol="display: inline;";
int aszOppNumVol = opp.getOPPNumVolOpp();
if(aszOppNumVol==0){
	aszNumVol="display: none;";
}

String aszReqBelief="display: none;";
String aszOrgBelief = opp.getOPPRequiredCodeType();
if(aszOrgBelief.equalsIgnoreCase("Yes")){
   aszReqBelief="display: inline;";
}

String aszStatementFaith="display: none;";
String aszOrgStatement = opp.getOPPRequiredCodeDesc();
if(aszOrgStatement.equalsIgnoreCase("Organizational Statement of Faith")){
   aszStatementFaith="display: inline;";
}

String aszAddDetailSect="display: none;";
String aszAddDetails = opp.getOPPAddDetails();
if(aszAddDetails.length()>0){
   aszAddDetailSect="display: inline;";
}

String aszSTMReferenceSect="display: none;";
String aszSTMReferences = opp.getOPPSTMReferences();
if(aszSTMReferences.length()>0){
   aszSTMReferenceSect="display: inline;";
}

String aszLanguageSection="display: inline;";
String aszLanguage = opp.getOPPLanguages();
if(aszLanguage.equalsIgnoreCase("")){
   aszLanguageSection="display: none;";
}

String aszShortTermSect="display: none;";
boolean bShortTerm=false;
String aszShortTerm = opp.getOPPStatus();
if(aszShortTerm.equalsIgnoreCase("Short-term Missions / Volunteer Internship")){
	bShortTerm=true;
   aszShortTermSect="display: inline;";
}

String aszTempCost="display: none;";
double iTempCost=opp.getOPPCostUsd();
if(iTempCost > 0.0){
   aszTempCost="display: inline;";
}

String aszAmntPaidSect="display: none;";
String aszAmntPaid=opp.getOPPAmntPd();
if(aszAmntPaid.length() > 0){
   aszAmntPaidSect="display: inline;";
}

String aszRegionSect="display: none;";
String aszRegion=opp.getOPPRegion();
if(aszRegion.length() > 0){
   aszRegionSect="display: inline;";
}

String aszGroup="display: none;";
String aszOppFocusAreas = 	opp.getOPPFocusAreas() + ", " + 
							opp.getOPPFocusAreas2() + ", " + 
							opp.getOPPFocusAreas3() + ", " + 
							opp.getOPPFocusAreas4() + ", " + 
							opp.getOPPFocusAreas5(); 
aszOppFocusAreas=aszOppFocusAreas.replaceAll(", , ","");
aszOppFocusAreas=aszOppFocusAreas.replaceAll(", , ","");
aszOppFocusAreas=aszOppFocusAreas.replaceAll(", , ","");
if(aszOppFocusAreas.startsWith(", ")) aszOppFocusAreas = aszOppFocusAreas.substring(2);
if(aszOppFocusAreas.endsWith(", ")) aszOppFocusAreas = aszOppFocusAreas.substring(0, aszOppFocusAreas.length()-2);
aszOppFocusAreas=aszOppFocusAreas.replaceAll(", , ","");
int iOppFocusArea1 = opp.getOPPGreatFor1TID();
int iOppFocusArea2 = opp.getOPPGreatFor2TID();
int iOppFocusArea3 = opp.getOPPGreatFor3TID();
int iOppFocusArea4 = opp.getOPPGreatFor4TID();
int iOppFocusArea5 = opp.getOPPGreatFor5TID();
if( iOppFocusArea5 == 4793 ){
	if(
		opp.getOPPGroupMin()>0 ||
		opp.getOPPGroupMax()>0
	){
		aszGroup="display: inline;";
	}
}
String	aszDescr = opp.getOPPDescription();

String aszDisplayServiceAreas="display:table-row;";
// separate out categories for output and re-word categories for no-faith
String	aszServiceAreas = opp.getOPPCategories();
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
aszServiceAreas=aszServiceAreas.replaceAll("^,","");
aszServiceAreas=aszServiceAreas.replaceAll(",$","");
aszServiceAreas=aszServiceAreas.replaceAll(",(?=[^()]*)", ", ");
if(aszServiceAreas.length()<1)	aszDisplayServiceAreas="display:none;";

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

if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ // don't show faith-related fields or taxonomy values
   aszReqBelief="display: none;";
   aszStatementFaith="display: none;";
   aszShortTermSect="display: none;";
   aszTempCost="display: none;";
}

String aszDomainNameTitle = "Christian Volunteer Opportunity";	
if(aszHostID.equalsIgnoreCase( "volengcatholic" )) {
	aszDomainNameTitle = "Catholic Volunteer Opportunity";	
}else if(aszHostID.equalsIgnoreCase( "volengchurch" ) ){
	aszDomainNameTitle = "Church Volunteer Opportunity";	
}else if(aszHostID.equalsIgnoreCase( "volengfamily" )){
	aszDomainNameTitle = "Family Christian Volunteer Opportunity";	
}else if(b_isJob==true){
	aszDomainNameTitle = "Christian Job Posting, ChristianVolunteering.org";
}

String aszEscapedOppTitle = opp.getOPPTitle();
int pos=aszEscapedOppTitle.indexOf("'");
if(pos > 0) {
	aszEscapedOppTitle=aszEscapedOppTitle.replaceAll("'","\\\\'");
}
String aszEscapedOrgName = aszOrgName;
pos=aszEscapedOrgName.indexOf("'");
if(pos > 0) {
	aszEscapedOrgName=aszEscapedOrgName.replaceAll("'","\\\\'");
}

aszCurrentURL = opp.getOPPUrlAlias()+".jsp";

String aszTitle = opp.getOPPTitle();
String aszLocation = "";
if(b_isJob==true) aszTitle += " Job Posting";
if(aszOrgName.length()>0) aszTitle += ", " + aszOrgName;
if (aszCity.length()>0) aszLocation = ", " + aszCity;
if ( (aszCountry.length()>0) || (aszState.length()>0) ){	
	aszLocation += ", " ;
	if(aszCountryISO.equalsIgnoreCase("US")){
		aszLocation += aszState ;
	} else {
		aszLocation += aszCountry ;
	}
}
aszTitle +=  aszLocation;
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
<head>
<link rel="image_src" href="/imgs/logo.gif"/>
<title><%=aszTypeTitle%><%=aszTitle%>: <%=aszDomainNameTitle%>
</title>


<% } else { } %>

<% // canonical links: http://www.seomoz.org/blog/canonical-url-tag-the-most-important-advancement-in-seo-practices-since-sitemaps %>
<%
if(request.getParameter("redirected") != null){
%>
	<link rel="canonical" href="http://www.churchvolunteering.org/<%=opp.getOPPUrlAlias()%>.jsp" />
<%
}else if(
		showPortalInfo==true	&& 
		aszPortal.length()>0 	&& 
		opp.getOPPPrivate()==1
){
	aszCanonicalLink = "http://"+aszSubdomain+aszPortal+"/"+opp.getOPPUrlAlias()+".jsp";
%>
	<link rel="canonical" href="http://<%=aszSubdomain%><%=aszPortal%>/<%=opp.getOPPUrlAlias()%>.jsp" />
<%
}else{ // could be a portal, but the opp is not private, so its prefered domain is actually w/o the portal mentioned - need to check that this is the spec
	// program in forwarding if the opportunity is Faith-Filled OR non-US
	if(	opp.getOPPFaithSpecTID()==21998	||
		(! aszCountryISO.equalsIgnoreCase("us") )
	){ 
		aszCanonicalLink = "http://www.churchvolunteering.org/"+opp.getOPPUrlAlias()+"jsp";
%>
	<link rel="canonical" href="http://www.churchvolunteering.org/<%=opp.getOPPUrlAlias()%>.jsp" />
<%
	}else{
		aszCanonicalLink = "http://www.christianvolunteering.org/"+opp.getOPPUrlAlias()+"jsp";
%>
	<link rel="canonical" href="http://www.christianvolunteering.org/<%=opp.getOPPUrlAlias()%>.jsp" />
<% }} %>
	  <meta itemprop="description" content="<p><%=replaceQuotes(opp.getOPPDescription())%></p><p><b>Additional Details:</b> <%=replaceQuotes(opp.getOPPAddDetails())%></p><p><b>Duration:</b> <%=replaceQuotes(opp.getOPPDuration())%></p><p><b>Great For:</b> <%=replaceQuotes(aszOppFocusAreas)%></p><p><b>Organization's Mission Statement:</b> <%=replaceQuotes(org.getORGOrgStmtFaith())%></p><p><b>Trip Length:</b> <%=replaceQuotes(opp.getOPPTripLength())%></p><p><b>Cost of Trip (Per Person):</b> <%=opp.getOPPCostUsd() %> <%=replaceQuotes(opp.getOPPCostTerm())%></p><p><b>Cost of This Trip Includes:</b> <%=replaceQuotes(opp.getOPPCostInclude())%></p><p><b>References Required?:</b> <%=replaceQuotes(opp.getOPPReferenceReq())%></p><p><b>Application Deadline:</b> <%=replaceQuotes(opp.getOPPAppDeadline())%></p>" />
<meta name="keywords" content="<%=aszServiceAreas%>, <%=aszSkills%>, <%=opp.getOPPTitle()%>, <%=aszOrgName%>, <%=aszLocation%>, volunteer, Christian, church, catholic, mission, foundation, internships, Christian volunteer, church volunteer, nonprofit, ministry, missions trip, short term missions, soup kitchen, orphanages, missionary, charities, ministry, ministries, virtual volunteer, online volunteering, volunteer online, salvation army" />

<% 
String aszMetaRobots = "noindex, nofollow, noarchive";
if(aszHostID.equalsIgnoreCase("voleng1")){
	aszMetaRobots = "index, follow, noarchive";
}else if(aszCanonicalLink.contains("churchvolunteering")){
	aszMetaRobots = "index, nofollow, noarchive";
}
%>
<meta name="robots" content="<%=aszMetaRobots%>" />

<meta name="ICBM" content="<% out.print(fLatitude + "," + fLongitude);%>" />

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!--/node/<%=opp.getOPPNID()%>-->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%

// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

	int iTemp = 0;
	long iTime = 0;
	int iWarningTime = 30; //number of days until expiration - warning

JSONObject obj = new JSONObject();
obj.put("pic", "http://www.christianvolunteering.org/imgs/results-img.gif");
obj.put("name", "");

String aszName = obj.get("name")+"";
if(aszName.length()<1){
	aszName=aCurrentUserObj.getUSPNameFirst() + " " +aCurrentUserObj.getUSPNameLast();
}
if(b_isJob==false){
	if(aszName.length()<3){
		aszName = "I am interested in volunteering"; 
	}else{
		aszName += " wants to volunteer";
	}
}else{
	if(aszName.length()<3){
		aszName = "I am interested in this position"; 
	}else{
		aszName += " is interested in this position";
	}
}

String currentURL=aszSubdomain+"/";
if(aszPortal.length()>0){
	currentURL+=aszPortal+"/"+opp.getOPPUrlAlias()+".jsp";
}else{
	currentURL+=opp.getOPPUrlAlias()+".jsp";
}
out.print("<!-- aszPortal is: " + aszPortal + " -->");

if(opp.getOPPSource().equalsIgnoreCase("feeds") || opp.getOPPSource().equalsIgnoreCase("simplyhired")){ 
%>
<script type="text/javascript">
function publish(obj) {
	volunteer_clicked(obj);
}
</script>
<%
}else if(	(
	aszHostID.equalsIgnoreCase("volengexample") || 
	aszHostID.equalsIgnoreCase( "volenggospel" ) ||
	aszHostID.equalsIgnoreCase( "volenggospelcom" ) ||
	aszHostID.equalsIgnoreCase( "volengchurch" ) ||
	aszRemoteHost.contains("christianvolunteering.org")||
	aszRemoteHost.contains(":7001")
	) && 
	(! (aszPortal.length()>0 && (! aszPortal.contains("voleng")) ))
){
%>

<script type="text/javascript">
window.fbAsyncInit = function() {//onload = function() {//
  FB.init({appId: '<%=tempappid%>', status: true, cookie: true,
           xfbml: true});
  fbApiInitialized = true;
afterInit();

    // whenever the user logs in, we tell our login service
    FB.Event.subscribe('auth.login', function() {
       window.location = "<%=request.getContextPath()%>/register.do?method=processFacebookConnectClick";
    });
    
    FB.getLoginStatus(function(response){
    	var loginElement = document.getElementById('fb-login-li');
    	var facebookElement = document.getElementById('facebookConnectLinks');
    	if(response.status == 'connected'){
    		//alert('connected');
    		if(loginElement){
    			loginElement.innerHTML = 
    			'<a href="<%=request.getContextPath()%>/register.do?method=processFacebookConnectClick"><img src="http://www.christianvolunteering.org/imgs/facebook_connect_button.gif" /></a>';
    		}
    	} else {
    		//alert('not connected');
    		if(loginElement){
    			loginElement.innerHTML = 
    			'<fb:login-button perms="email"> Login/Register with Facebook</fb:login-button>';
    		}
    		
    		FB.XFBML.parse(document.getElementById('fb-login-li'));
    		if(facebookElement){
    			//facebookElement.innerHTML = 
    			//'<a href="<%=aszPortal%>/register.do?method=processFacebookConnectClick">Link to Facebook Account</a>';
    		}
    	}
    });
	
	
	
};
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
function fbEnsureInit(callback) {
    if (!window.fbApiInitialized) {
        setTimeout(function() { fbEnsureInit(callback); }, 50);
    } else {
        if (callback) { callback(); }
    }
}
function afterInit(){
	return;
}  
  
function publish(obj) {
	var browserName=navigator.userAgent; 
	if(browserName.indexOf("Chrome") != -1
		&& false==true
	){
		volunteer_clicked(obj);
	}else{
		FB.getLoginStatus(function(response) {
		// response.status=unknown (NOT logged in user), notConnected (for logged in user who has NOT authenticated the app), connected (for logged in user who HAS authenticated the app)
		  if (response.status != "unknown") {
			// logged in and connected user, someone you know
			FB.ui(
			   {
				 method: 'stream.publish',
				 attachment: {
<% if(b_isJob == true){ %>
				   	name: 'Come join me in looking at the Job Posting \'\'<%=aszEscapedOppTitle%>\'\' for  <%=aszEscapedOrgName%>',
				   	caption: '{*actor*} is interested in the following job posting \'\'<%=aszEscapedOppTitle%>\'\'',
				   	href: 'http://<%=currentURL%>',
					 media:[{
					   'type':'image',
					   'src':'http://www.christianvolunteering.org/imgs/chrisvol_logo_fb.jpg',
					   'href':'http://<%=currentURL%>'
					 }]
				 },
				 action_links: [
				   {'text':'View the Job Posting','href':'http://<%=aszSubdomain%>/<%=opp.getOPPUrlAlias()%>.jsp'}
<% }else{ %>
				   	name: 'Come join me in volunteering in the opportunity \'\'<%=aszEscapedOppTitle%>\'\' for  <%=aszEscapedOrgName%>',
				   	caption: '{*actor*} wants to volunteer for the opportunity \'\'<%=aszEscapedOppTitle%>\'\'',
				   	href: 'http://<%=currentURL%>',
					 media:[{
					   'type':'image',
					   'src':'http://www.christianvolunteering.org/imgs/chrisvol_logo_fb.jpg',
					   'href':'http://<%=currentURL%>'
					 }]
				 },
				 action_links: [
				   {'text':'View the opportunity','href':'http://<%=aszSubdomain%>/<%=opp.getOPPUrlAlias()%>.jsp'}
<% } %>
				 ],
				 user_message_prompt: 'Add a personalized message'
			   },
			   function(response) {
				 if (response && response.post_id) {
					volunteer_clicked(obj);
				 } else {
					volunteer_clicked(obj);
				 }
			  }
			);
		  } else {
			// no user session available, someone you dont know
			volunteer_clicked(obj);
		  }
		});
		volunteer_clicked(obj);
	  }
}
</script>

<% }else{ %>
<script type="text/javascript">
function publish(obj) {
	volunteer_clicked(obj);
}
</script>
<% } %>    


<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
<script language="javascript">
function clicked_map(){
	document.getElementById('narrowMap').style.display='inline';
	check_resize();
}
</script>
<% } %>



<%
aszSTMReference = "References from people who have done Short Term Missions with this organization before are available upon request. Submit \"I Want to Volunteer!\" to receive more information.";
if( aszNarrowTheme=="true" ){ 
	aszDescription = opp.formatForPrint(opp.getOPPDescription(),75);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),75);
	aszRequirements = opp.formatForPrint(opp.getOPPRequirements(),75);
	aszAddDetail = opp.formatForPrint(opp.getOPPAddDetails(),75);
	aszSTMReference = opp.formatForPrint(aszSTMReference,75);
	aszCostPymntIncludes = opp.formatForPrint(opp.getOPPCostInclude(),75);
}else{
	aszDescription = opp.formatForPrint(opp.getOPPDescription(),110);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),110);
	aszRequirements = opp.formatForPrint(opp.getOPPRequirements(),110);
	aszAddDetail = opp.formatForPrint(opp.getOPPAddDetails(),110);
	aszSTMReference = opp.formatForPrint(aszSTMReference,110);
	aszCostPymntIncludes = opp.formatForPrint(opp.getOPPCostInclude(),110);
} 

String aszRequirementsSection="display:none";
if(aszRequirements.length()>0){
	aszRequirementsSection="display:inline";
}

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
    
<script type="text/javascript">
$(document).ready(function() {
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
	    title:"<%=opp.getOPPTitle()%>"
	});

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent('<!--<%=opp.getOPPTitle()%><br /><br />--><%=aszFullAddress%><br /><a style="position: static; overflow: visible; float: right; padding-right:25px; display: inline; " target="_blank" href="http://maps.google.com/maps?q=<%=fLatitude%>,<%=fLongitude%>&amp;hq=&amp;hnear=<%=fLatitude%>,<%=fLongitude%>&amp;z=14&amp;t=m"><div style="cursor: pointer; ">Google maps</div></a>');
    infowindow.open(map, marker);
  });
  google.maps.event.addListener(infowindow, 'closeclick', function() {
    map.setCenter(myLatlng);
  });
  
	// To add the marker to the map, call setMap();
	marker.setMap(map);
});
</script>
<% } %>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Preview Opportunity for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Preview Opportunity</span>
	<% } %>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>






<!-- BEGIN MAINCONTENT -->
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >


<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
	<% if(bAdminRole==true){ %>
  <span style="float: left;">Preview Opportunity for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Preview Opportunity</span>
	<% } %>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" >organization management </a>
	<% }else{ %>
		<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
		<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" >organization management </a>
	<% } %>
			&gt; preview opportunity  </div>
<% if(aszNoSearchNav=="true"){ %>
	</div>
<% } %>


<div style="display:none;">

<rdf:RDF xmlns:rdf=http://www.w3.org/1999/02/22-rdf-syntax-ns# xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"> <geo:Point>

<geo:lat><%=fLatitude%></geo:lat>

<geo:long><%=fLongitude%></geo:long>

</geo:Point> </rdf:RDF>

</div>

<!-- primary contact user http://www.urbanministry.org/user/<%=opp.getOPPUID()%>/edit -->

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner" class="shorter">
	
		<span style="float: left;"><%=aszType%></span>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/oppsrch.do?method=showSearch">search</a> 
			&gt; <%=aszType%>  </div>
	</div>
<% } %>

<!-- address is <%=opp.getOPPAddrLine1()%> -->
	
<div align="left">
<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>
	<div id="body" itemscope itemtype="http://schema.org/JobPosting">
	  <meta <% if(aszTitle != null && !aszTitle.isEmpty()) { %> itemprop="title" <% } %> content="<%=opp.getOPPNID()%>" />
	  <meta itemprop="description" content="<p><%=replaceQuotes(opp.getOPPDescription())%></p><p><b>Additional Details:</b> <%=replaceQuotes(opp.getOPPAddDetails())%></p><p><b>Duration:</b> <%=replaceQuotes(opp.getOPPDuration())%></p><p><b>Great For:</b> <%=replaceQuotes(aszOppFocusAreas)%></p><p><b>Organization's Mission Statement:</b> <%=replaceQuotes(org.getORGOrgStmtFaith())%></p><p><b>Trip Length:</b> <%=replaceQuotes(opp.getOPPTripLength())%></p><p><b>Cost of Trip (Per Person):</b> <%=opp.getOPPCostUsd() %> <%=replaceQuotes(opp.getOPPCostTerm())%></p><p><b>Cost of This Trip Includes:</b> <%=replaceQuotes(opp.getOPPCostInclude())%></p><p><b>References Required?:</b> <%=replaceQuotes(opp.getOPPReferenceReq())%></p><p><b>Application Deadline:</b> <%=replaceQuotes(opp.getOPPAppDeadline())%></p>" />
	  <meta <% if(aszOrgURLAlias != null && !aszOrgURLAlias.isEmpty()) { %> itemprop="url" <% } %> content="<%=replaceQuotes(aszOrgURLAlias)%>" />
	  <meta itemprop="qualifications" content="<p><%=replaceQuotes(aszRequirements)%></p><p><b>Langauges Required:</b> <%=replaceQuotes(opp.getOPPLanguages())%></p>" />
	  <meta itemprop="salaryCurrency" content="USD" />
<%=org.getErrorMsg()%>

<br />

<h1 <% if(aszTitle != null && !aszTitle.isEmpty()) { %> itemprop="name" <% } %>><%=aszTitle%></h1>

<br>			
<center><span class="criticaltext">
<% if (opp.getOPPExpirationTime() / 86400 < 0){ %>
This opportunity listing expired on 
<%
	iTime = opp.getOPPExpirationDt();
	aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
	out.print( aszTemp );
%>
.  Please click <a href="<%=aszPortal%>/org.do?method=processEditOpp&orgnid=<%=opp.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&oppexpiredrenew=renew">here</a> to renew this opportunity.
<% }else if (opp.getOPPExpirationTime() / 86400 <= iWarningTime){ %>
This opportunity listing is about to expire.  Please click <a href="<%=aszPortal%>/org.do?method=processEditOpp&orgnid=<%=opp.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&oppexpiredrenew=renew">here</a> to renew this opportunity.
<% }else if (opp.getOPPPublished() == 0){ %>
This opportunity listing has been submitted for moderation and won't be listed publicly until it has been approved.
<% } %>
</span></center>
<br>

<HR width="100%">
<table border="0">
	<tr>
<% if(!(bMapDisplay==true) && !(aszNarrowTheme.equalsIgnoreCase("true"))){ %>
		<td width=160></td>
<% }else{ %>
		<td width=5%></td>
<% } %>
		<td>
		
			<table id="listingdetail" class="listingdetail" border="0">
				<tr>
					<td class="left">
<% 
	if(!(aszNarrowTheme.equalsIgnoreCase("true"))){ 
		if(bMapDisplay==true){ // don't display if it's a virutal opportunity
%>
						<table class="info" border="0" cellpadding="2" cellspacing="0">
							<tr>
								<td>
<% }} %>
						<table class="info" border="0" cellpadding="2" cellspacing="0">
							<tr style="<%=aszDisplayFeedsTableOrgName%>">
								<th align="right">Organization:</th>
								<td></td>
								<td <% if(!(aszNarrowTheme.equalsIgnoreCase("true")) && bMapDisplay==true){ %>width=160<% } %>>
								  <span itemprop="hiringOrganization" itemscope itemtype="http://schema.org/NGO">
								    <meta <% if(aszOrgURLAlias != null && !aszOrgURLAlias.isEmpty()) { %> itemprop="url" <% } %> content="<%=aszOrgURLAlias%>" />
									<A href="<%=aszOrgURLAlias%>" <% if(aszOrgName != null && !aszOrgName.isEmpty()) { %> itemprop="name" <% } %>>
										<%=aszOrgName%>
									</A>
							      </span>
								</td>
							</tr>
							
							<logic:notEmpty name="orgForm" property="contactlastname">
							<tr style="<%=aszDisplayFeedsTable%>">
								<th  align="right" valign="top">Contact Name:</th><td></td>
								<td>
									<bean:write name="orgForm" property="contactfirstname"/>
									<bean:write name="orgForm" property="contactlastname"/>	
								</td>
							</tr>
							</logic:notEmpty>

							<% if(aszPositionType != null && !aszPositionType.isEmpty()) { %>
							<tr style="<%=aszDisplayLocationType%>">
								<th  align="right" valign="top">Position Type:</th><td></td>
								<td itemprop="employmentType" >
									<%=aszPositionType%>
								</td>
							</tr>
							<% } %>
							<tr style="<%=aszVirtual%>">
								<th  align="right" valign="top">
										Address:
								</th>
								<td></td>
								<td itemprop="jobLocation" itemscope itemtype="http://schema.org/Place">
								  <span itemprop="geo" itemscope itemtype="http://schema.org/GeoCoordinates">
								      <% if(opp.getLatitude() != 0) { %>
										  <meta itemprop="latitude" content="<%=opp.getLatitude()%>" />
									  <% } %>
									  <% if(opp.getLongitude() != 0) { %>
										  <meta itemprop="longitude" content="<%=opp.getLongitude()%>" />
									  <% } %>
								  </span>
								  <span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">
										<%=aszFullAddressTagged%>
										<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
										<br />
<a href="#narrowMap" onClick="document.getElementById('narrowMap').style.display='inline';" >map location</a>
										<br />
										<% } %>
								  </span>
								</td>
							</tr>
							
							<tr style="<%=aszDisplayFeedsTable%>">
								<th align="right">Great For:</th><td></td>
								<td class="act_area">
									<%=aszOppFocusAreas%>
								</td>
							</tr>
							<tr style="<%=aszDisplayServiceAreas%>">
								<th  align="right"><%=aszCategory%> Area(s):</th><td></td>
								<td  class="act_area">
									<%=aszServiceAreas%>
								</td>
							</tr>
						
							<tr style="<%=aszDisplayFeedsTable%>">
								<th align="right"><div style="<%=aszHrly%>">Hourly Commitment:</div></th>
								<td></td>
								<td>
									<div style="<%=aszHrly%>">
										<%=opp.getOPPCommitHourly()%> per: <%=opp.getOPPCommitType()%>
									</div>
								</td>
							</tr>
							<tr style="<%=aszDisplayFeedsTable%>">
								<th align=right>
									<div style="<%=aszNumPosition%>">Number of Positions:</div>
								</th>
								<td></td>
								<td>
									<div style="<%=aszNumPosition%>"><%=opp.getOPPVolsNeeded()%></div>
								</td>
							</tr>
							<tr style="<%=aszDisplayFeedsTable%>">
								<th align=right>
									<div style="<%=aszNumVol%>">
										Number of Volunteers in this<br /> Position in the Past Year:
									</div>
								</th>
								<td></td>
								<td>
									<div style="<%=aszNumVol%>">
										<%=opp.getOPPNumVolOpp()%>
									</div>				
								</td>
							</tr>
							<tr style="<%=aszDisplayFeedsTable%>">
								<th align=right> One-time / Ongoing Position:</th>
								<td></td>
								<td>
									<%=opp.getOPPFreq()%>					
								</td>
							</tr>
							<tr>
								<th align=right></th>
								<td></td>
								<td>
								</td>
							</tr>

						</table>
					</td>
<% 
	if(!(aszNarrowTheme.equalsIgnoreCase("true"))){ 
		// don't display if it's a virutal opportunity
		if(bMapDisplay==true){

%>
								<td style="<%=aszVirtual%>">


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
<!--div id="map_canvas" style="width:100%; height:100%"></div-->

								</td>
							</tr>							
						</table>
<%
		}
	} 
%>
				</tr>
			</table>
		</td>
	</tr>
</table>

<div class="clear" style="height: 5px;"></div>

<HR width="100%">							

<pre><div id="textareaformat">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Description</h3>
<%=aszDescription%>
</div></pre>

<pre><div id="textareaformat" style="<%=aszRequirementsSection%>">

<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Requirements </h3>

<span><%=aszRequirements%></span>

</div></pre>


<div id="languages" style="<%=aszLanguageSection%>">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Language </h3><br />
<p><%=opp.getOPPLanguages()%></p></div>

<% if (opp.getOPPWorkStudyTID()== 8104){ %>
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> 
<%=opp.getOPPWorkStudy()%>
	</h3><p></p><br />
<% } %>
	

<% if(! aszHostID.equalsIgnoreCase("volengivol"))
		if(opp.getOPPRequiredCodeType()=="Yes"){  {%>
			<h3>Volunteers for This Position Are Required to be Christian</h3>
			<p></p>
			<br />
			
			<div id="orgBelief" style="<%=aszReqBelief%>">
			<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Required Creed / Christian Belief</h3><br />
			<p><%=opp.getOPPRequiredCodeDesc()%></p>
			<br />
		<% } %>
	
	<div id="orgStatement" style="<%=aszStatementFaith%>">
		<pre><div id="textareaformat"><h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Organizational Statement of Faith</h3><br />
	<%=aszStatement%></div></pre></div></div>
<% } %>

<div id="group" style="<%=aszGroup%>">

<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Group Minimum and Maximum</h3><br />
<p><%=opp.getOPPGroupMin()%> - 
<%=opp.getOPPGroupMax()%></p>

</div>

<% if(bShortTerm==false){ %>
<div id="addDetails" style="<%=aszAddDetailSect%>">
<pre><div id="textareaformat"><h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Additional Detail</h3>
<%=aszAddDetail%>
</div></pre>
</div>
<% } %>

<div id="shortTermMissFields" style="<%=aszShortTermSect%>">
<br />
<hr>
<h4>Short Term Mission Trip Details</h4><br /><hr>

<div id="stmReference" style="<%=aszSTMReferenceSect%>">
<pre><div id="textareaformat">
<%=aszSTMReference%>
</div></pre>
</div>

<div id="addDetails" style="<%=aszAddDetailSect%>">
<pre><div id="textareaformat"><h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Additional Detail</h3><br /><br /><%=aszAddDetail%>
</div></pre>
</div>

<div id="regionSect" style="<%=aszRegionSect%>">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Region</h3><br />
<p><%=opp.getOPPRegion()%></p>
</div>

<% 
if(opp.getOPPRoomBoard().length()>0 || opp.getOPPStipend().length()>0 ||opp.getOPPMedInsur().length()>0 || 
	opp.getOPPTransport().length()>0 || opp.getOPPAmeriCorps().length()>0 || opp.getOPPBenefits().length()>0){
%>
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Benefits Offered:</h3><br />
<ul itemprop="benefits">

<%
String aszOutputBenefits = "";
aszOutputBenefits = "<li>"+opp.getOPPBenefits().replaceAll(",", "</li><li>") ;
int iLengthBenefits = aszOutputBenefits.length();
if(aszOutputBenefits.endsWith("<li>") && iLengthBenefits>=4)
	aszOutputBenefits=aszOutputBenefits.substring(0,iLengthBenefits-4);

out.print(aszOutputBenefits);
		
%>

<% 
	if(opp.getOPPRoomBoard().length()>0){
		out.println("<li>" + opp.getOPPRoomBoard());
		out.println("</li>");
	}
	if(opp.getOPPStipend().length()>0){
		out.println("<li>" + opp.getOPPStipend());
		out.println("</li>");
	}
	if(opp.getOPPMedInsur().length()>0){
		out.println("<li>" + opp.getOPPMedInsur());
		out.println("</li>");
	}
	if(opp.getOPPTransport().length()>0){
		out.println("<li>" + opp.getOPPTransport());
		out.println("</li>");
	}
	if(opp.getOPPAmeriCorps().length()>0){
		out.println("<li>" + opp.getOPPAmeriCorps());
		out.println("</li>");
	}
}
out.println("</ul>");
%>



<div id="paid" style="<%=aszAmntPaidSect%>">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Amount Paid (if any)</h3><br />
<p <% if(opp.getOPPAmntPd() != null && !opp.getOPPAmntPd().isEmpty()) { %>itemprop="baseSalary" <% } %>><%=opp.getOPPAmntPd()%></p>
</div>

<div id="cost" style="<%=aszTempCost%>">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Cost (per person)</h3><br />
<p>$<%=opp.getOPPCostUsd()%> <%=opp.getOPPCostTerm()%></p>
</div>

<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> This cost includes:</h3><br />
<p><%=aszCostPymntIncludes%></p>

<%
	iTime = opp.getOPPApplicDeadlineNum();
	if (iTime > 0){
%>
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Application Deadline (MM/DD/YYYY)</h3><br />
<p>
<%
		aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
		out.println( aszTemp );
%>
</p>
<%
	}
%>

<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Length of Trip</h3><br />
<p><%=opp.getOPPTripLength()%></p>


</div>

<table>
<tr>
			<th align=right> Initially Posted:</th><td></td>
								<td itemprop="datePosted">
			<%				
				iTime = opp.getOPPCreateDtNum();
				aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
				out.println(aszTemp);
			%>
							
										</td>
							</tr>
							<tr>
										<th align=right> Last Updated:</th><td></td>
								<td>
			<%				
				iTime = opp.getOPPUpdateDtNum();
				aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
				out.println(aszTemp);
			%>
							
										</td>
							</tr></table>

<table class="info" border="0" cellpadding="2" cellspacing="0">
	<%
	aszTemp = opp.getOPPDaterequired();
	iTemp = opp.getOPPDaterequiredTID();
    if(iTemp==8132 || iTemp==8133 || iTemp==8134){
    if(opp.getOPPStartDtNum()> 0 ){%>
 <HR width="100%">
<%
		out.println("<tr><th align=\"right\" valign=\"top\" colspan=\"3\">" + aszTemp + ":</th><td></td></tr><tr><td width=50></td><td>");
	    if(iTemp==8134){			
			iTime = opp.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
			out.println(" Start Date: &nbsp; "+ aszTemp +"<BR />");
			iTime = opp.getOPPEndDtNum();
			if (iTime > 0){
				if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
					out.println(" End Date: &nbsp;&nbsp; "+ aszTemp +"<BR />");
				}
			}
	    }else if(iTemp==8133){			
			iTime = opp.getOPPStartDtNum();
			aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
			out.println(" Start Date: &nbsp; "+ aszTemp +"<BR />");
			iTime = opp.getOPPEndDtNum();
			if (iTime > 0){
				if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
					aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
					out.println(" End Date: &nbsp;&nbsp; "+ aszTemp +"<BR />");
				}
			}
	    }
    }
    } else if(b_isJob==false) {
		out.println("<tr><td colspan=\"3\"><br />(this opportunity is not scheduled for a specific date)");
    }
    %>
</td></tr></table>
      <HR width="100%">

<br />


			
<% if(b_isJob==false || (b_isJob==true && !aszSource.equalsIgnoreCase("simplyhired")) ){ %>
	<% if(aszNarrowTheme.equalsIgnoreCase("true")){ // have the option to show the iframe here if it's a narrow theme 
		// don't display if it's a virutal opportunity
		if(bMapDisplay==true){
	%>
			<br clear="all" />
			<div id="narrowMap" style="display:none;">
				<HR width="100%" />
				<br />							
<iframe src="http://www.urbanministry.org/node/<%=opp.getOPPNID()%>/show-map/notheme" width="320" height="320" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" allowtransparency="true">
  <p><a href="http://www.urbanministry.org/node/<%=opp.getOPPNID()%>/show-map">View map</a></p>
</iframe>
				<br />							
			</div>
	<% }} %>
<% } %>

		
	
</div>

</div>

  </div>  
  
		                

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %>
<!-- end footer information -->


