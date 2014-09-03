<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>

<%//@ include file="/template_include/facebookapi_keys.inc" %>
<%@page import="com.google.code.facebookapi.FacebookWebappHelper"%>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
		
	//	FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

	//if(facebookHelp.requireLogin("register.do?method=showMyMinistryOpps")) return;
	//if(facebookHelp.requireFrame("register.do?method=showMyMinistryOpps")) return;
}
%>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>

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


String aszProgramTypePrint = "";
int tidSpiritualDevelopment = 5239;
String aszProgramType=""+searchinfo.getProgramTypeTID();

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

//ArrayList aResultsList = new  ArrayList ( 2 );

ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

//aCodes.advanceSearchOpportunities( aResultsList, searchinfo, searchinfo.LATEST_LOCAL );

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
int iCounterTotal=0;
int iPopularity=0;
String aszPopularity="";
String aszPopularityImg="";

boolean noOpps=true;
%>

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

%>
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
Volunteer Opportunities</title>
<% }else{ %>
Christian Volunteer and Short Term Missions Opportunities</title>
<% } %>


<!-- start header information -->
<%//@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%//@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

</head>

<%
String[] a_sLookingforarray = request.getParameterValues("lookingforarray");

%>


<!-- BEGIN MAINCONTENT -->
<!--div id="maincontent">



<div id="body"-->

<div id="resultsNumber">
</div>
<% iCounter=0; %>
		<logic:notEmpty name="alistlatestlocal" >
<% noOpps=false; %>
<h2>Local Opportunities:</h2>

<table border=0 class="MsoNormalTable">
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
	<tr>
		<td height="35"></td><td>&nbsp;</td>
		<td valign="top">
			<h3>Title&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td>&nbsp;</td>
		<td valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td>&nbsp;</td>
		<td valign="top">
			<h3>Organization&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td>&nbsp;</td>
		<td valign="top">
			<h3>Location&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td>&nbsp;</td>
		<td valign="top">
			<h3>Service Areas&nbsp;&nbsp;&nbsp;</h3>
		</td>
	</tr>
        
			<logic:iterate id="org" name="alistlatestlocal" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}
	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
	String aszOppAddress = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppAddress=aAppCode.getCSPStateName();
					}else {
						aszOppAddress=aAppCode.getCSPStateName();
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
					aszOppAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOppAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppAddress = ", " + aszOppAddress;
}
aszOppAddress = org.getOPPAddrCity() + aszOppAddress;
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOppAddress=org.formatForPrint(aszOppAddress,2);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
} else { 
	aszOppAddress = org.formatForPrint(aszOppAddress,1);
	//aszOppAddress = org.formatForPrint(aszOppAddress,10);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	//aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),15);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
//	//aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),15);
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=11><hr></td></tr>
		
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>
				</td><td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td></td>
				<TD valign="top">
					<%=aszOrgNamePrint%>
				</TD><td></td>
				<TD valign="top">
					<%=aszOppAddress%>&nbsp;
				</TD><td></td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=11><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>

<% iCounter=0; %>
		<logic:notEmpty name="alistlatestvirtual" >
<% noOpps=false; %>
<h2>Virtual Volunteer Opportunities:</h2>

<!--table border=0 class="MsoNormalTable" style="text-align:left; background-color:#eeeeee; border:1px solid #000000;"-->
<table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0>
	<tr>
		<td height="35"></td><td>&nbsp;</td>
		<td>
			<h3>Title&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td>
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td>
			<h3>Organization&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td>
			<h3>Service Areas&nbsp;&nbsp;&nbsp;</h3>
		</td>
	</tr>
        
			<logic:iterate id="org" name="alistlatestvirtual" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
//String	aszCat1 = org.getDataByPosition(org.getOPPCategories(),';',1);
//String	aszCat2 = org.getDataByPosition(org.getOPPCategories(),';',2);
//String	aszCat3 = org.getDataByPosition(org.getOPPCategories(),';',3);
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=9><hr></td></tr>
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>&nbsp;&nbsp;
				</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td>&nbsp;&nbsp;</td>
				<TD valign="top">
					<%=aszOrgNamePrint%>&nbsp;&nbsp;&nbsp;&nbsp;
				</TD><td>&nbsp;&nbsp;</td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=9><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>
		
<% iCounter=0; %>
		<logic:notEmpty name="alistlatestgrpfam" >
<% noOpps=false; %>
<h2>Group/Family Opportunities:</h2>

<table border=0 class="MsoNormalTable">
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
	<tr>
		<td height="35"></td><td>&nbsp;</td>
		<td valign="top">
			<h3>Title</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Organization&nbsp;&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Location</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Service Areas</h3>
		</td>
    <td height="30">&nbsp;</td>
	</tr>
        
			<logic:iterate id="org" name="alistlatestgrpfam" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}
	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
	String aszOppAddress = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppAddress=aAppCode.getCSPStateName();
					}else {
						aszOppAddress=aAppCode.getCSPStateName();
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
					aszOppAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOppAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppAddress = ", " + aszOppAddress;
}
aszOppAddress = org.getOPPAddrCity() + aszOppAddress;
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOppAddress=org.formatForPrint(aszOppAddress,2);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
} else { 
	aszOppAddress = org.formatForPrint(aszOppAddress,1);
	//aszOppAddress = org.formatForPrint(aszOppAddress,10);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	//aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),15);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
//	//aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),15);
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=11><hr></td></tr>
		
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>
				</td><td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td></td>
				<TD valign="top">
					<%=aszOrgNamePrint%>
				</TD><td></td>
				<TD valign="top">
					<%=aszOppAddress%>&nbsp;
				</TD><td></td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=11><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>

		
<% iCounter=0; %>
		<logic:notEmpty name="alistlateststm" >
<% noOpps=false; %>
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
<h2>Short-Term Service Opportunities:</h2>
<% }else{ %>
<h2>Short-Term Missions Opportunities:</h2>
<% } %>

<table border=0 class="MsoNormalTable">
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
	<tr>
		<td height="35"></td><td></td>
		<td valign="top">
			<h3>Title</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Organization&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Location&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Service Areas&nbsp;</h3>
		</td>
	</tr>
        
			<logic:iterate id="org" name="alistlateststm" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}
	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
	String aszOppAddress = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppAddress=aAppCode.getCSPStateName();
					}else {
						aszOppAddress=aAppCode.getCSPStateName();
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
					aszOppAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOppAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppAddress = ", " + aszOppAddress;
}
aszOppAddress = org.getOPPAddrCity() + aszOppAddress;
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOppAddress=org.formatForPrint(aszOppAddress,2);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
} else { 
	aszOppAddress = org.formatForPrint(aszOppAddress,1);
	//aszOppAddress = org.formatForPrint(aszOppAddress,10);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	//aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),15);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
//	//aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),15);
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=11><hr></td></tr>
		
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>
				</td><td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td></td>
				<TD valign="top">
					<%=aszOrgNamePrint%>
				</TD><td></td>
				<TD valign="top">
					<%=aszOppAddress%>&nbsp;
				</TD><td></td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=11><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>




		
<% iCounter=0; %>
		<logic:notEmpty name="alistlatestsumintrn" >
<% noOpps=false; %>
<h2>Summer Internship Opportunities:</h2>

<table border=0 class="MsoNormalTable">
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
	<tr>
		<td height="35"></td><td></td>
		<td valign="top">
			<h3>Title&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Organization&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Location&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Service Areas&nbsp;&nbsp;&nbsp;</h3>
		</td>
	</tr>
        
			<logic:iterate id="org" name="alistlatestsumintrn" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}
	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
	String aszOppAddress = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppAddress=aAppCode.getCSPStateName();
					}else {
						aszOppAddress=aAppCode.getCSPStateName();
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
					aszOppAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOppAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppAddress = ", " + aszOppAddress;
}
aszOppAddress = org.getOPPAddrCity() + aszOppAddress;
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOppAddress=org.formatForPrint(aszOppAddress,2);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
} else { 
	aszOppAddress = org.formatForPrint(aszOppAddress,1);
	//aszOppAddress = org.formatForPrint(aszOppAddress,10);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	//aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),15);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
//	//aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),15);
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=11><hr></td></tr>
		
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>
				</td><td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td></td>
				<TD valign="top">
					<%=aszOrgNamePrint%>
				</TD><td></td>
				<TD valign="top">
					<%=aszOppAddress%>&nbsp;
				</TD><td></td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=11><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>
		
<% iCounter=0; %>
		<logic:notEmpty name="alistlatestintrn" >
<% noOpps=false; %>
<h2>Internship Opportunities:</h2>

<table border=0 class="MsoNormalTable">
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
	<tr>
		<td height="35"></td><td>&nbsp;</td>
		<td valign="top">
			<h3>Title&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Organization&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Location&nbsp;&nbsp;&nbsp;</h3>
		</td>
    <td></td>
		<td valign="top">
			<h3>Service Areas&nbsp;&nbsp;&nbsp;</h3>
		</td>
	</tr>
        
			<logic:iterate id="org" name="alistlatestintrn" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
// separate out categories for output and re-word categories for no-faith
String	aszCat1 = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	if ((aszCat1.equalsIgnoreCase("Bible Study")) || 
		(aszCat1.equalsIgnoreCase("Christian/Catholic Schools")) ||
		(aszCat1.equalsIgnoreCase("Church Planting")) ||
		(aszCat1.equalsIgnoreCase("Evangelism")) ||
		(aszCat1.equalsIgnoreCase("Family / Adults Ministry")) ||
		(aszCat1.equalsIgnoreCase("Religious Education")) ||
		(aszCat1.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
		(aszCat1.equalsIgnoreCase("Vacation Bible School")) ||
		(aszCat1.equalsIgnoreCase("Women's Ministry"))){
			org.setOPPCategories("");
	}else if (aszCat1.equalsIgnoreCase("Disabilities Ministry")){
			org.setOPPCategories("Disabilities Outreach");
	}else if (aszCat1.equalsIgnoreCase("Food Ministry / Hunger")){
			org.setOPPCategories("Food Service / Hunger");
	}else if (aszCat1.equalsIgnoreCase("Prison Ministry")){
			org.setOPPCategories("Prison Outreach");
	}
}
	String aszCity= org.getOPPAddrCity();
	String aszTemp1= org.getOPPAddrCountryName();
	String aszTemp2 = org.getOPPAddrStateprov();
	String aszOppAddress = "";
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppAddress=aAppCode.getCSPStateName();
					}else {
						aszOppAddress=aAppCode.getCSPStateName();
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
					aszOppAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOppAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOppAddress = ", " + aszOppAddress;
}
aszOppAddress = org.getOPPAddrCity() + aszOppAddress;
if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOppAddress=org.formatForPrint(aszOppAddress,2);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
} else { 
	aszOppAddress = org.formatForPrint(aszOppAddress,1);
	//aszOppAddress = org.formatForPrint(aszOppAddress,10);
//	aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
//	//aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),15);
//	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
//	//aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),15);
}

aszOppTitlePrint = org.formatForPrint(org.getOPPTitle(),1);
if(aszNarrowTheme=="true"){
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
}else{
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
}

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
%>
		<tr><td colspan=11><hr></td></tr>
		
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top" align="left">
					<% if(org.getOPPUrlAlias().length()>1){%>
						<A href="<%=aszPortal%>/<%=org.getOPPUrlAlias()%>.jsp">
					<% }else{ %>	
						<A href="<%=aszPortal%>/org/opp<%=org.getOPPurlID()%>.jsp">
					<% } %>
					<%=aszOppTitlePrint%></A>
				</td><td>&nbsp;&nbsp;</td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td>&nbsp;&nbsp;</td>
				<TD valign="top">
					<%=aszOrgNamePrint%>
				</TD><td>&nbsp;&nbsp;</td>
				<TD valign="top">
					<%=aszOppAddress%>&nbsp;
				</TD><td>&nbsp;&nbsp;</td>
				<TD valign="top">
					<% // output service areas with a ,&nbsp; in between, but not if there is only one category %>				
					<%=org.getOPPCategories()%>
				</TD>
			</tr>
			</logic:iterate>
		<tr><td colspan=11><hr></td></tr>
</table>
<br><br>
		</logic:notEmpty>

<% iCounter=0; %>
		<logic:notEmpty name="alistlatestlocalorgs" >
<% noOpps=false; %>
<h2>Local Organizations:</h2>
<table border=0 class="MsoNormalTable" width=650>
<!--table class="searchtoolfull" cellSpacing=1 cellPadding=0 border=0-->
      <!--TABLE class="searchtoolfull" cellSpacing=0 cellPadding=1 border=0 id="table1" >-- 
        <!-- MSTableType="layout" -->
		<!-- width="543"  > -->
        <tr>
        <td></td><td></td>
          <TD valign="top">
			<h3>Title&nbsp;&nbsp;&nbsp;</h3></TD><td></td>
          <TD valign="top">
			<h3>Popularity&nbsp;&nbsp;&nbsp;</h3></TD><td></td>
          <TD valign="top">
			<h3>Location&nbsp;&nbsp;&nbsp;</h3></TD><td></td>
          <TD valign="top">
			<h3>Program Type&nbsp;&nbsp;&nbsp;</h3></TD><td height="30">&nbsp;</td>
        </tr>
        <TR>
        	<TD colspan=9><HR></TD>
        </TR>
       
			<logic:iterate id="org" name="alistlatestlocalorgs" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">

<%
//popularity
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
// only print out if this organization is not a Spiritual Development Program
if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){
      if(!
              (
                      (org.getORGProgramTypes1TID() == tidSpiritualDevelopment) ||
                      (org.getORGProgramTypes().equalsIgnoreCase("Spiritual Development Program"))
              )
      ){
%>
			<TR>
				<td valign="top"><%iCounter++;iCounterTotal++;%><%=iCounter%></td><td></td>
				<TD valign="top">

				<A href="<%=aszPortal%>/<%=org.getORGUrlAlias()%>.jsp">
				
<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
} else { 
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),1);
} %>      

				<%=aszOrgNamePrint%>
				</A>
				</TD>
				<td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td></td>
				<TD valign="top">
<%
	String aszCity= org.getORGAddrCity();
	String aszTemp1= org.getORGAddrCountryName();
	String aszTemp2 = org.getORGAddrStateprov();
	String aszOrgAddress = "";

	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOrgAddress=aAppCode.getCSPStateName();
					}else {
						aszOrgAddress=aAppCode.getCSPStateName();
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
					aszOrgAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOrgAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOrgAddress = ", " + aszOrgAddress;
}
aszOrgAddress = org.getORGAddrCity() + aszOrgAddress;

if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOrgAddress=org.formatForPrint(aszOrgAddress,2);
} else { 
	aszOrgAddress = org.formatForPrint(aszOrgAddress,1);
} %>      


					<%=aszOrgAddress%>
				</TD><td></td>
				<TD valign="top">
				
				<%
					aszProgramTypePrint = org.formatForPrintSemi(org.getORGProgramTypes(),10);
				%>

				<%=aszProgramTypePrint%>
				
				
				
				</TD>
			</tr>
			<TR><TD colspan=9><HR></TD></TR>
<%
	}
}else{
%>
			<TR>
				<td valign="top"><%iCounter++;%><%=iCounter%></td><td></td>
				<TD valign="top">

				<A href="<%=aszPortal%>/<%=org.getORGUrlAlias()%>.jsp">
				
<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),2);
} else { 
	aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),1);
} %>      

				<%=aszOrgNamePrint%>
				</A>
				</TD><td></td>
				<td valign="top" align="left">
					<%//=aszPopularity%>
					<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/><br>
				</td><td>&nbsp;&nbsp;</td>
				<TD valign="top">
<%
	String aszCity= org.getORGAddrCity();
	String aszTemp1= org.getORGAddrCountryName();
	String aszTemp2 = org.getORGAddrStateprov();
	String aszOrgAddress = "";

	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOrgAddress=aAppCode.getCSPStateName();
					}else {
						aszOrgAddress=aAppCode.getCSPStateName();
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
					aszOrgAddress=aAppCode.getCTRPrintableName();
				}else {
					aszOrgAddress=aAppCode.getCTRPrintableName();
				}
			}
		}
	}
}
int iCityLength = 0;
if (aszCity.length() > 1) {
	aszOrgAddress = ", " + aszOrgAddress;
}
aszOrgAddress = org.getORGAddrCity() + aszOrgAddress;

if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ 
	aszOrgAddress=org.formatForPrint(aszOrgAddress,2);
} else { 
	aszOrgAddress = org.formatForPrint(aszOrgAddress,1);
} %>      


					<%=aszOrgAddress%>
				</TD><td></td>
				<TD valign="top">
				
				<%
					aszProgramTypePrint = org.formatForPrintSemi(org.getORGProgramTypes(),10);
				%>

				<%=aszProgramTypePrint%>
				
				
				
				</TD>
			</tr>
			<TR><TD colspan=9><HR></TD></TR>

<% } %>
			</logic:iterate>
		</logic:notEmpty>
		
</table>
<% if(noOpps==true){ %>
<br><br><br>
<center><h2>It appears there are no listings for your search posted within the last week.</h2></center>
<% } %>

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById("resultsNumber").innerHTML = '<b><%=iCounterTotal%> results found:</b>';
 }); 
</script>

<!--/div></div-->
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%//@ include file="/template/footer.inc" %><!-- end footer information -->
