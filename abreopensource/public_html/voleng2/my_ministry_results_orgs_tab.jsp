<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Organization Search Results</title>

<% } else { %>
<% } %>
<script type="text/javascript" src="<%=aszPortal%>/misc/jquery.js"></script>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>


<!-- BEGIN MAINCONTENT -->
<div id="maincontent">


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
int iPopularity=0;
String aszPopularity="";
String aszPopularityImg="";

int tidSpiritualDevelopment = 5239;

int iCounter=0;
%>

<div id="body">
<table><tr><td width=120></td><td>

<html:form action="/oppsrch.do" method="get" focus="postalcode" >
<html:hidden property="method" value="processOrgSearchAll" />

<%
String aszPosType=""+searchinfo.getOPPPositionTypeTID();
String aszProgramType=""+searchinfo.getProgramTypeTID();
String aszDenomAffil=""+searchinfo.getDenomAffilTID();
String aszOrgAffil1TID=""+searchinfo.getOrgAffil1TID();
String aszOrgAffil2TID=""+searchinfo.getOrgAffil2TID();
String aszOrgAffil3TID=""+searchinfo.getOrgAffil3TID();
String aszOrgAffil4TID=""+searchinfo.getOrgAffil4TID();
String aszOrgAffil5TID=""+searchinfo.getOrgAffil5TID();
%>

<html:hidden property="postype" value="<%=aszPosType%>" />
<html:hidden property="duration" value="<%=searchinfo.getDuration()%>" />
<html:hidden property="postalcode" value="<%=searchinfo.getPostalCode()%>" />
<html:hidden property="distance" value="<%=searchinfo.getDistance()%>" />
<html:hidden property="programtypetid" value="<%=aszProgramType%>" />
<html:hidden property="orgname" value="<%=searchinfo.getOrgName()%>" />
<html:hidden property="city" value="<%=searchinfo.getCity()%>" />
<html:hidden property="state" value="<%=searchinfo.getState()%>" />
<html:hidden property="prov" value="<%=searchinfo.getOthrProv()%>" />
<html:hidden property="country" value="<%=searchinfo.getCountry()%>" />
<html:hidden property="region" value="<%=searchinfo.getRegion()%>" />
<html:hidden property="affiliationtid" value="<%=aszDenomAffil%>" />
<html:hidden property="orgaffil1tid" value="<%=aszOrgAffil1TID%>" />
<html:hidden property="orgaffil2tid" value="<%=aszOrgAffil2TID%>" />
<html:hidden property="orgaffil3tid" value="<%=aszOrgAffil3TID%>" />
<html:hidden property="orgaffil4tid" value="<%=aszOrgAffil4TID%>" />
<html:hidden property="orgaffil5tid" value="<%=aszOrgAffil5TID%>" />
<html:hidden property="requesttype" value="<%=searchinfo.getSearchRequestType()%>" />
<html:hidden property="localaffil" value="<%=searchinfo.getLocalAffil()%>" />

Sort results alphabetically by: 
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
    </SELECT> <input type="submit" name="Submit" value="Go">

</html:form>

</td></tr></table>

     

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

String aszORGUrlAlias=org.getORGUrlAlias() + ".jsp";

out.println("<!--<br>-------------------------------------------------------------------------------------------------------------<br>-->");

String aszTeaser = org.getORGMissStmntTeaser();
if(aszTeaser.length() > 50){
	aszTeaser = "<div class=\"teaser\">"+org.getORGMissStmntTeaser()+
		"...<A class=\"more_link\" href=\""+aszPortal+aszORGUrlAlias+"\">more ></A></div>";
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
		</logic:notEmpty>
		

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById("resultsNumber").innerHTML = '<b><%=iCounter%> results found:</b>';
 }); 
</script>
</div></div>
