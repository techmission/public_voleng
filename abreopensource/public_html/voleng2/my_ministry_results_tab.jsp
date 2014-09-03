<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>

<%//@ include file="/template_include/facebookapi_keys.inc" %>


<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
		
	//	FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

	//if(facebookHelp.requireLogin("register.do?method=showMyMinistryOpps")) return;
	//if(facebookHelp.requireFrame("register.do?method=showMyMinistryOpps")) return;
}
%>

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
%>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
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




<!-- BEGIN MAINCONTENT -->
<!--div id="maincontent">



<div id="body"-->


<div id="sorts">
<html:form action="/oppsrch.do" method="get" >
<html:hidden property="method" value="processOppSearchAll" />
<html:hidden property="postype" value="<%=\"\" + searchinfo.getOPPPositionTypeTID()%>" />
<html:hidden property="duration" value="<%=searchinfo.getDuration()%>" />
<html:hidden property="postalcode" value="<%=searchinfo.getPostalCode()%>" />
<html:hidden property="distance" value="<%=searchinfo.getDistance()%>" />
<html:hidden property="servicearea1" value="<%=\"\" + searchinfo.getOPPServiceArea1TID()%>" />
<html:hidden property="serviceareas" value="<%=\"\" + searchinfo.getServiceAreasTIDs()%>" />
<html:hidden property="orgname" value="<%=searchinfo.getOrgName()%>" />
<html:hidden property="org" value="<%=\"\" + searchinfo.getNID()%>" />
<html:hidden property="skills1id" value="<%=\"\" + searchinfo.getOPPSkills1TID()%>" />
<html:hidden property="skilltypes" value="<%=\"\" + searchinfo.getSkillsTIDs()%>" />
<html:hidden property="greatforkid" value="<%=\"\" + searchinfo.getGreatForKidTID()%>" />
<html:hidden property="greatforsenior" value="<%=\"\" + searchinfo.getGreatForSeniorTID()%>" />
<html:hidden property="greatforteen" value="<%=\"\" + searchinfo.getGreatForTeenTID()%>" />
<html:hidden property="greatforgroup" value="<%=\"\" + searchinfo.getGreatForGroupTID()%>" />
<html:hidden property="greatforfamily" value="<%=\"\" + searchinfo.getGreatForFamilyTID()%>" />
<html:hidden property="mingroup" value="<%=\"\" + searchinfo.getMinSize()%>" />
<html:hidden property="maxgroup" value="<%=\"\" + searchinfo.getMaxSize()%>" />
<html:hidden property="city" value="<%=searchinfo.getCity()%>" />
<html:hidden property="citytid" value="<%=\"\" + searchinfo.getCityTID()%>" />
<html:hidden property="state" value="<%=searchinfo.getState()%>" />
<html:hidden property="prov" value="<%=searchinfo.getOthrProv()%>" />
<html:hidden property="country" value="<%=searchinfo.getCountry()%>" />
<html:hidden property="countrytid" value="<%=\"\" + searchinfo.getCountryTID()%>" />
<html:hidden property="region" value="<%=searchinfo.getRegion()%>" />
<html:hidden property="denomaffiltid" value="<%=\"\" + searchinfo.getDenomAffilTID()%>" />
<html:hidden property="orgaffil1tid" value="<%=\"\" + searchinfo.getOrgAffil1TID()%>" />
<html:hidden property="orgaffil2tid" value="<%=\"\" + searchinfo.getOrgAffil2TID()%>" />
<html:hidden property="orgaffil3tid" value="<%=\"\" + searchinfo.getOrgAffil3TID()%>" />
<html:hidden property="orgaffil4tid" value="<%=\"\" + searchinfo.getOrgAffil4TID()%>" />
<html:hidden property="orgaffil5tid" value="<%=\"\" + searchinfo.getOrgAffil5TID()%>" />
<html:hidden property="localaffil" value="<%=searchinfo.getLocalAffil()%>" />
<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
<html:hidden property="requiredcodetype" value="No" />
<% } %>
<table cellpadding=0 cellspacing=0><tr><td width=80%></td><td >
Sort results by: </td><td></td></tr>
<tr><td width=80%></td><td >
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value="">Popularity</option>
<% if( searchinfo.getPostalCode().equalsIgnoreCase( "" )){ %>
<% } else { %>
		<option value="distance">Distance</option>
<% } %>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <%//option value="prov">Province (outside US & Canada)</option%>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
	<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity (Annually)</option>
    </SELECT>
</td><td>
<input type="submit" name="Submit" value="Go">

</html:form>

</td></tr></table>
</div>
<div id="resultsNumber">
<b>&nbsp;</b>    
</div>
        
		<logic:notEmpty name="alist" >
			<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">

<%			
// separate out categories for output and re-word categories for no-faith
String	aszServiceAreas = org.getOPPCategories();
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	aszServiceAreas=aszServiceAreas.replaceAll("Bible Study","");
	aszServiceAreas=aszServiceAreas.replaceAll("Christian/Catholic Schools","");
	aszServiceAreas=aszServiceAreas.replaceAll("Church Planting","");
	aszServiceAreas=aszServiceAreas.replaceAll("Evangelism","");
	aszServiceAreas=aszServiceAreas.replaceAll("Family / Adults Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Religious Education","");
	aszServiceAreas=aszServiceAreas.replaceAll("Single Parents / Crisis Pregnancy","");
	aszServiceAreas=aszServiceAreas.replaceAll("Vacation Bible School","");
	aszServiceAreas=aszServiceAreas.replaceAll("Women's Ministry","");
	aszServiceAreas=aszServiceAreas.replaceAll("Disabilities Ministry","Disabilities Outreach");
	aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry / Hunger","Food Service / Hunger");
	aszServiceAreas=aszServiceAreas.replaceAll("Prison Ministry","Prison Outreach");
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

// program in forwarding if the opportunity is Faith-Filled
String aszOPPUrlAlias="", aszORGUrlAlias="";
if(org.getOPPFaithSpecTID()==21998 && aszSecondaryHost.equalsIgnoreCase("default") ){ 
	aszOPPUrlAlias="http://www.churchvolunteering.org/"+org.getOPPUrlAlias()+".jsp";
	aszORGUrlAlias="http://www.churchvolunteering.org/"+org.getORGUrlAlias()+".jsp";
}else{ 
	aszOPPUrlAlias=aszPortal+"/"+org.getOPPUrlAlias()+".jsp";
	aszORGUrlAlias=aszPortal+"/"+org.getORGUrlAlias()+".jsp";
}
String aszMemberClass="";
if(org.getORGMember()>0){
	aszMemberClass="class=\"member\"";
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
	aszSTMDetails += " <b>Length of Trip:</b>&nbsp;" + aszTripLength + "&nbsp;";
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
					aszScheduledDate += " - " + aszTemp;
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
					aszScheduledDate += " - " + aszTemp;
				}
			}
	    }
    }
} else {//(this opportunity is not scheduled for a specific date)
	aszScheduledDate = "";
}
String aszDatePrint = "";

if(aszFrequency.contains("One-time")){
	aszDatePrint = " <b>" + aszFrequency;
	if(aszScheduledDate.length()>1){
		aszDatePrint += " on: </b> " + aszScheduledDate + "&nbsp;&nbsp;";
	}else{
		aszDatePrint += "</b>&nbsp&nbsp";
	}
}



out.println("<!--<br>" + aszSkills + " " + aszPositionType + " freqency: " + aszFrequency + " " +aszTripLength+ " " + aszGreatFor+ " " +aszBenefits + " " + aszWorkStudy+"<br>-->");
%>

<div id="search-results">
			<div <%=aszMemberClass%>>
<%iCounter++;%><%=iCounter%>.&nbsp;

<A class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszOppTitlePrint%></A>
&nbsp;<img src="http://www.christianvolunteering.org/imgs/<%=aszPopularityImg%>"/>
&nbsp;&nbsp;
<A class="org_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
<div class="teaser">
<%out.print(org.getOPPDescTeaser());%>...
<A class="more_link" href="<%=aszPortal%><%=aszOPPUrlAlias%>">more ></A>
</div>
<div class="opp-info">
<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
<%=aszSTMDetails%>
<br>
<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
<%=aszDatePrint%><b>Date Last Updated:</b>&nbsp;<%out.print(date);%>
</div>
</div>
<hr />
</div>

			</logic:iterate>
		</logic:notEmpty>
		
<script type="text/javascript">
$(document).ready(function() {
	document.getElementById("resultsNumber").innerHTML = '<b><%=iCounter%> results found:</b>';
 }); 
</script>

<!--/div></div-->
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%//@ include file="/template/footer.inc" %><!-- end footer information -->
