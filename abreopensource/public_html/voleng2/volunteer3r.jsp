<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

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

<title>Christian Volunteer Network: Organization Search Results</title>

<% } else { %>
<% } %>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

</head>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Organization Search Results</span>
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
<span style="float: left;">Organization Search Results</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1">volunteer</a> &gt; 
	organization search results  </div>
</div>
<% } %>

<%
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aServiceList, 161 );
aCodes.getAppCodeList( aProgramList, 172 );
aCodes.getAppCodeList( afiliationList, 163 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getAppCodeList( apartnersList, 167 );
aCodes.getAppCodeList( askillsList, 169 );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszOrgNamePrint = "";
String aszProgramTypePrint = "";
String aszOppTitlePrint = "";

int tidSpiritualDevelopment = 5239;

int iCounter=0;
int iPopularity=0;
String aszPopularity="";
String aszPopularityImg="";
%>

<div id="body">

<br/>

<form name="searchForm" action="<%=aszPortal%>/oppsrch.do" method="get" focus="postalcode" >
<input type="hidden" name="method" value="processOrgSearchAll" />
<input type="hidden" value="orgSrchResultsSort" name="formname" id="formname"/>

<%
String aszPosType=""+searchinfo.getOPPPositionTypeTID();
String aszProgramType=""+searchinfo.getProgramTypeTID();
String aszDenomAffil=""+searchinfo.getDenomAffilTID();
String aszOrgAffil1TID=""+searchinfo.getOrgAffil1TID();
String aszOrgAffil2TID=""+searchinfo.getOrgAffil2TID();
String aszOrgAffil3TID=""+searchinfo.getOrgAffil3TID();
String aszOrgAffil4TID=""+searchinfo.getOrgAffil4TID();
String aszOrgAffil5TID=""+searchinfo.getOrgAffil5TID();

boolean bPortalLink=false;
if(searchinfo.getSearchPortals()==true){
	bPortalLink=true;
}

String aszDuration = searchinfo.getDuration();
String aszPostalCode = searchinfo.getPostalCode();
String aszDistance = searchinfo.getDistance();
String aszOrgName = searchinfo.getOrgName();

String aszTempCity= searchinfo.getCity();
String aszTempCountry= searchinfo.getCountry();
String aszTempRegion= searchinfo.getRegion();



String aszSearchTerms = "<input type=\"hidden\" name=\"method\" value=\"processOppSearchAll\" /> \n\r";
String aszSearchURL = "?method=processOrgSearchAll";

boolean bSearchPortals = searchinfo.getSearchPortals();
if(bSearchPortals == true){
	aszSearchTerms += "<input type=\"hidden\" name=\"portallist\" value=\""+bSearchPortals+"\" /> \n\r";
	aszSearchURL += "&portallist="+bSearchPortals;
}

if(aszPostalCode.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"postalcode\" value=\""+aszPostalCode+"\" /> \n\r";
	aszSearchURL += "&postalcode="+aszPostalCode;
}

if(aszDistance.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"distance\" value=\""+aszDistance+"\" /> \n\r";
	aszSearchURL += "&distance="+aszDistance;
}

if(aszProgramType.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"programtypetid\" value=\""+aszProgramType+"\" /> \n\r";
	aszSearchURL += "&programtypetid="+aszProgramType;
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

String aszSearchKey1 = searchinfo.getSearchKey();
if(aszSearchKey1.length() > 0){
	aszSearchTerms += "<input type=\"hidden\" name=\"searchkey1\" value=\""+aszSearchKey1+"\" /> \n\r";
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
aszCurrentURL = "/oppsrch.do" + aszSearchURL;

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
<input type="hidden" name="localaffil" value="<%=searchinfo.getLocalAffil()%>" />
<input type="hidden" name="portallist" value="<%=searchinfo.getSearchPortals()%>" />


<table cellpadding=0 cellspacing=0><tr><td width=80%>
&laquo; <a href="<%=aszPortal%>/advancedsearch.jsp">Back to Advanced Search</a>
</td><td >

Sort results by: </td><td></td></tr>
<tr><td width=80%></td><td >
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
    </SELECT> 
</td><td>
<input type="submit" name="Submit" value="Go">

</td></tr></table>

</form>

<% if(bPortalLink==true){ %>
<br><center>
<h3>
Is your church not listed? Contact your church Volunteer Coordinator to<br>add your church.  <a href="mailto:paste your pastor's email address here?subject=What%20do%20you%20think%20about%20our%20Church%20using%20ChurchVolunteering.org?&amp;body=I%20recently%20came%20across%20a%20website%20called%20ChurchVolunteering.org.%20%20It%20makes%20it%20easy%20for%20churches%20to%20have%20their%20members%20sign%20up%20for%20volunteer%20opportunities%20on%20the%20church%20website.%20%20I%20thought%20it%20might%20be%20something%20of%20interest%20to%20our%20church.%0A%0ATheir%20model%20is%20that%20the%20church%20leadership%20needs%20to%20be%20the%20one%20to%20make%20the%20call%20of%20whether%20to%20sign%20up.%20%20So%20do%20you%20think%2C%20it%20might%20be%20something%20we%20would%20want%20to%20do%3F%20%20If%20so%2C%20who%20do%20you%20think%20would%20be%20the%20right%20person%20to%20sign%20up%20for%20this%20and%20who%20would%20I%20talk%20to%2C%20to%20get%20a%20link%20from%20our%20church%20website%3F%0A%0AThanks%20for%20your%20consideration!%0A%0A">ChurchVolunteering.org</a>
</h3>
</center><br>
<% } %>
      

<div id="resultsNumber">
<b>&nbsp;</b>  
</div>

<br>
        
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

String aszORGUrlAlias=aszPortal+org.getORGUrlAlias() + ".jsp";
if(aszPortal.length()>0){
	aszORGUrlAlias=aszPortal+"/"+org.getORGUrlAlias() + ".jsp";
}

if(bPortalLink==true && org.getPortalName().length()>0){
//	aszORGUrlAlias="http://www.churchvolunteering.org/" + org.getPortalName() + "/" + org.getORGUrlAlias() + ".jsp";
	aszORGUrlAlias="http://www.churchvolunteering.org/" + org.getPortalName() + "/volunteerlistings.jsp";
}
out.println("<!-- request.getContextPath() " + request.getContextPath() + " -->");
out.println("<!-- org.getPortalName() " + org.getPortalName() + " -->");
out.println("<!-- org.getORGUrlAlias() " + org.getORGUrlAlias() + " -->");

out.println("<!--<br>-------------------------------------------------------------------------------------------------------------<br>-->");

String aszTeaser = org.getORGMissStmntTeaser();
if(aszTeaser.length() > 50){
	aszTeaser = "<div class=\"teaser\">"+org.getORGMissStmntTeaser()+
		"...<A class=\"more_link\" href=\""+aszORGUrlAlias+"\">more ></A></div>";
}
%>

<div id="search-results">
			<div <%=aszMemberClass%>>
<%iCounter++;%><%=iCounter%>.&nbsp;

<A class="opp_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
<div class="opp-info">
<%=aszTeaser%>
<b>Program Type:</b>&nbsp;<%=aszProgramTypes%>
<!-- <%=aszAffiliations%> 
<br> -->&nbsp;&nbsp;
<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
<b>Date Last Updated:</b>&nbsp;<%out.print(date);%>
</div>
</div>
<hr />


</div>

			</logic:iterate>

<% if(true == LoginBean.IsSessionLoggedIn( request )	&& 
	(
		aszPortal.length()>0 && 
		(
			aszHostID.equalsIgnoreCase("volengchurch")	|| 
			aszHostID.equalsIgnoreCase("voleng1")		|| 
			aszHostID.equalsIgnoreCase("volengexample")	|| 
			aszHostID.equalsIgnoreCase("default")
		)
	)
){ %>	
<% if(bSkipBanner==false){ %>
<br>
<button type="button" id="embed_button" onclick="show_embed_div()">Embed on Your Site</button>
	<%@include file="/template_include/embed_options.inc"%>
<% } %>
<% } %>
		</logic:notEmpty>
		

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById("resultsNumber").innerHTML = '<b><%=iCounter%> results found:</b>';
 }); 
</script>
</div></div>
<!-- start sidebar information --><%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
