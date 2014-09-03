<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<%
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO orgBRLO = (com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO) request.getAttribute("orgBRLO");

String aszMission;
String aszDescription;
String aszStatement;

String aszLocalAffil="display: none;";
if( (aszHostID.equalsIgnoreCase( "volengboston" ) || (aszHostID.equalsIgnoreCase( "volengnewengland" )))){
   aszLocalAffil="display: inline;";
}
%>


<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>


<%
	String aszTemp1= org.getORGAddrCountryName();
	String aszTemp2 = org.getORGAddrStateprov();
	String aszTemp3;
%>

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>
	<title><bean:write name="orgForm" property="orgname"/>, <bean:write name="orgForm" property="city"/>,
<%
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
				out.print("" + aAppCode.getCSPStateName() );}
			}
		}
	} else {
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
				out.print("" + aAppCode.getCTRPrintableName() + "");}
			}
		}
	}
%>: Christian Volunteer Network
</title>
<% } %>


<!-- start header information -->
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
int iSpiritDevelTID = 5239;

aszMission = org.formatForPrint(org.getORGMissionStatement(),35);
aszDescription = org.formatForPrint(org.getORGOrgDescription(),35);
aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),35);
%>

</head>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Organization Profile</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Organization Profile</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/advancedsearch.jsp">search</a> &gt; organization profile</div>
</div>
<% } %>


  <div id="body">
      	<div align="left">
<div>
	<div>
		<div>
			<!-- titles -->
<br>
						<table border="0" width=90%>

							<tr>
								<td colspan=4 width=85%><h1><bean:write name="orgForm" property="orgname"/>, <bean:write name="orgForm" property="city"/>,
<%
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
				out.print("" + aAppCode.getCSPStateName() );}
			}
		}
	} else {
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
				out.print("" + aAppCode.getCTRPrintableName() + "");}
			}
		}
	}
%></h1></td>
								<td></td>
								<td valign="top" align="right">
								<h3>
									<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a>]</font>
								</h3>
								</td>
			
				    		</tr>
				    	</table>

			<HR width="100%">
			<!-- invite notice -->

					<table border=0><tr>
							<% if( (aszHostID.equalsIgnoreCase("voleng1")) || (aszHostID.equalsIgnoreCase("volengnewengland")) || (aszHostID.equalsIgnoreCase("volengboston"))) { %>
					<td width=5%></td>
							<% }else{ %>
					<td width=5%></td>
							<% } %>
					
					
					<td>
			
			<table class="organizationdetail" border="0">
				<tr>
					<td class="left">
						<table class="info" border="0" cellpadding="2" cellspacing="0">
							
							<tr>
								<td></td><th  align="right" valign="top">Contact Name:</th><td></td>
								<td <% if(!(aszNarrowTheme.equalsIgnoreCase("true"))){ %> width=160<% } %> >
									
									<bean:write name="orgForm" property="contactfirstname"/>
									<bean:write name="orgForm" property="contactlastname"/>	
									
								</td>
							</tr>

							
							<tr>
								<td></td><th  align="right" valign="top">Web:</th><td></td>
								<td>
								<a href="<bean:write name="orgForm" property="orgwebpage"/>"><bean:write name="orgForm" property="orgwebpage"/></a>
								</td>
							</tr>
					
							<tr>
								<td></td><th  align="right" valign="top">Address:</th><td></td>
								<td><bean:write name="orgForm" property="addr1"/>&nbsp; &nbsp;
									<bean:write name="orgForm" property="addr2"/><br />
									<bean:write name="orgForm" property="city"/>, 
					<%
					aszTemp= org.getORGAddrStateprov();
					if(null != aStateList){
						for(int index=0; index < aStateList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCSPStateCode();
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
							out.println("" + aAppCode.getCSPStateName() + "");}
						}
					}
					%>

									<bean:write name="orgForm" property="prov"/>&nbsp;&nbsp;&nbsp;
									  <bean:write name="orgForm" property="postalcode"/><br /> 
				<%
					
					
					aszTemp= org.getORGAddrCountryName();
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
							out.println("" + aAppCode.getCTRPrintableName() + "");}
						}
					}
					
				%>

							</tr>
							
							
<% if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	
%>
							<tr>
								<td></td><th  align="right" valign="top">Phone:</th><td></td>
								<td><bean:write name="orgForm" property="phone1"/><br /></td>
							</tr>
<%}%>
							
							<tr>
								<td></td><th  align="right" valign="top">Fax:</th><td></td>
								<td><bean:write name="orgForm" property="fax1"/><br /></td>
							</tr>

							<tr>
								<td></td><th  align="right" valign="top">Number of Volunteers Serving:</th><td></td>
								<td>
									<%
		            					int temp;
		            					temp = org.getORGNumVolOrg();
		            					if(temp==0){
											out.println("(not available)");
		           						}
		            					else {
											out.println(temp);
		            					}
		            				%>
								</td>
							</tr>
							
							<tr>
								<td></td><th  align="right" valign="top">Number of People Being Served:</th><td></td>
								<td>
									<%
		            					int newtemp;
		            					newtemp = org.getORGNumServed();
		            					if(newtemp==0){
											out.println("(not available)");
		           						}
		            					else {
											out.println(newtemp);
		            					}
		            				%>
								</td>
							</tr>
									
						</table>
					</td>

				</tr>
			</table>
			</td></tr>
			</table>

					<div class="clear" style="height: 5px;"></div>

  					<div id="navcontainer">
					</div>
				</div>
			</div>
		
<table border=0><tr><td>
<% 
int iTemp2=org.getORGOrgNumber();  
aszTemp2=iTemp2+""; 
int iTemp3=org.getORGNID();  
aszTemp3=iTemp3+""; 
%> 
<html:form action="/oppsrch.do?" method="get"> 
<html:hidden property="method" value="processOppSearchAll" /> 
<html:hidden property="orgnid" value="<%=aszTemp3%>"/> 
<% if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ // don't show faith-related fields or taxonomy values %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
<INPUT type=hidden name=submit><INPUT class="button" type=submit value="View this Organization's Opportunities"> 
</html:form>
</td><td>
<a href="http://www.urbanministry.org/node/<%=org.getORGNID()%>#comments" class="button"<% if(aszHostID.equalsIgnoreCase("volengfacebook") || aszHostID.equalsIgnoreCase("volengfacebookivol")){%> target="_new"<% } %>>Recommend this Organization</a>
</td>

<% if(orgBRLO.getORGDonateURLFallBackToGuideStar(org) != null && orgBRLO.getORGDonateURLFallBackToGuideStar(org).length() > 0){ %>
<td>
<a href="<%=orgBRLO.getORGDonateURLFallBackToGuideStar(org)%>" class="button">
<% 
// test to see if it's from a URL that has a narrower template
 if(aszNarrowTheme.equalsIgnoreCase("true")){ 
%>
Donate Now
<% } else { %>
Donate to this Organization
<% } %></a>
</td>
<% } %>

</tr></table>

</div>

     

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
<% } %>

<HR width="100%">							

<pre><div id="textareaformat">

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Mission Statement</h3>	
<%=aszMission%>
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Description</h3>
<%=aszDescription%>
<div style="<%=aszFaith%>">
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Statement of Faith</h3><br>
<%=aszStatement%>
</div>
</div>
</pre>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Program Type</h3><br>
<p>
<% if(
	aszSecondaryHost.equalsIgnoreCase("volengivol") && 
	org.getORGProgramTypes1TID()==iSpiritDevelTID
){ }else{ %>
<bean:write name="orgForm" property="programtypes"  />
<% } %>
</p>
<br>

<div style="<%=aszFaith%>">
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Denominational Affiliation</h3><br>
<p><bean:write name="orgForm" property="orgaffiliation"  /></p>
<br>
</div>

<div style="<%=aszFaith%>">

<div style="<%=aszLocalAffil%>">
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Local Affiliation</h3><br>
<p><bean:write name="orgForm" property="localaffil"  /></p>
<br>
</div>


<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Partner</h3><br>
<p><bean:write name="orgForm" property="orgpartner"  /></p>
<p><bean:write name="orgForm" property="orgpartner2"  /></p>
<p><bean:write name="orgForm" property="orgpartner3"  /></p>
<p><bean:write name="orgForm" property="orgpartner4"  /></p>
<p><bean:write name="orgForm" property="orgpartner5"  /></p>
<br>
</div>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Do you Require Formal Orientation Training?</h3><br>
<p>
<%=aszTemp = org.getORGFormalTrain()%>
</p>


</div>


      <P>
      <BR>&nbsp;</P></div>

    
    </div>
    

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
