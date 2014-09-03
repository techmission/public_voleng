<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  --><head>

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>
<title>Christian Volunteer Network: <bean:write name="orgForm" property="orgname"/>, <bean:write name="orgForm" property="city"/>
<% } %>

</title>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>



<%
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );

com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO orgBRLO = new com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO();
try{
	orgBRLO = (com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO) request.getAttribute("orgBRLO");
}catch(java.lang.NullPointerException ex){
}catch(Exception e){}

String aszMission;
String aszDescription;
String aszStatement;

String aszLocalAffil="display: none;";
if( (aszHostID.equalsIgnoreCase( "volengboston" ) || (aszHostID.equalsIgnoreCase( "volengnewengland" )))){
   aszLocalAffil="display: inline;";
}

%>



<%
if( (aszHostID.equalsIgnoreCase( "voleng1" )) || 
		(aszHostID.equalsIgnoreCase( "volengivol" )) || 
 		(aszHostID.equalsIgnoreCase( "default" )) ||
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
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
 ){ 
%>
<%
	aszMission = org.formatForPrint(org.getORGMissionStatement(),120);
	aszDescription = org.formatForPrint(org.getORGOrgDescription(),120);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),120);
%>
<% }else{ %>
<%
	aszMission = org.formatForPrint(org.getORGMissionStatement(),75);
	aszDescription = org.formatForPrint(org.getORGOrgDescription(),75);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),75);
%>
<% } %>


<html:form action="/org.do" focus="addr1" >


<input type="hidden" name="method" value="processEditOrg" />
<input type="hidden" name="requesttype" value="edit" />
<html:hidden property="orgnid" />
<html:hidden property="orgname" />
<html:hidden property="postalcode" />
<html:hidden property="country" />

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Preview Profile</span>
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

<span style="float: left;">Preview Profile</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp">organization management</a> &gt; 
	<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" > <A href="<%=aszPortal%>/org.do?method=showOrgSumm1&orgnid=<bean:write name='orgForm' property='orgnid' />" >manage this organization </a> &gt; preview profile</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==false){ %><div style="display:none;"><% } %>
<%@ include file="/template_include/left_sidebar_org.inc" %>
<% if(b_includeLeftSidebar==false){ %></div><% } %>

  <div id="body">
      	<div align="left">
<div>
	<div>
		<div>
			<!-- titles -->
<br>			
<center><span class="criticaltext">
<% 
if (org.getORGPublished() == 0){
	out.print("This organization has been submitted for moderation and won't be listed publicly until it has been approved.");
}
%>
</span></center>
<br>
						<table border="0" width=90%>

							<tr>
								<td colspan=4 width=85%><h1><bean:write name="orgForm" property="orgname"/></h1></td>
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
					<td width=10%></td>
							<% }else{ %>
					<td width=5%></td>
							<% } %>
					
					
					<td>
			<table class="organizationdetail" border="0">
				<tr>
					<td class="left">
						<table class="info" border="0" cellpadding="2" cellspacing="0">
							<tr>
								<td></td><th  align="right" valign="top">Web:</th><td></td>
								<td><a href="<bean:write name="orgForm" property="orgwebpage"/>"><bean:write name="orgForm" property="orgwebpage"/></a>
								</td>
							</tr>
							<tr>
								<td></td><th  align="right" valign="top">Donation URL:</th><td></td>
								<td><a href="<%=orgBRLO.getORGDonateURLFallBackToGuideStar(org)%>"><%=orgBRLO.getORGDonateURLFallBackToGuideStar(org)%></a></td>
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

									  <br />
								  </td>
							</tr>
							
							<tr>
								<td></td><th  align="right" valign="top">Phone:</th><td></td>
								<td><bean:write name="orgForm" property="phone1"/><br /></td>
							</tr>
							
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


  					<div id="navcontainer">
				</div>
				</div>
			</div>
		

</div>



      <HR width="100%">
<pre><div id="textareaformat"><h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Mission Statement</h3>	
<%=aszMission%>
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Description</h3>
<%=aszDescription%>
	<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Statement of Faith</h3><br>
<%=aszStatement%>
</div>
</pre>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Program Type</h3><br>
<p><bean:write name="orgForm" property="programtypes"  /></p>
<br>

<div style="<%=aszLocalAffil%>">
<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Local Affiliation</h3><br>
<p><bean:write name="orgForm" property="localaffil"  /></p>
<br>
</div>


<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Denominational Affiliation</h3><br>
<p><bean:write name="orgForm" property="orgaffiliation"  /></p>
<br>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Partners:</h3><br>
<p><bean:write name="orgForm" property="orgpartner"  /></p>
<p><bean:write name="orgForm" property="orgpartner2"  /></p>
<p><bean:write name="orgForm" property="orgpartner3"  /></p>
<p><bean:write name="orgForm" property="orgpartner4"  /></p>
<p><bean:write name="orgForm" property="orgpartner5"  /></p>
<br>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Do you Require Formal Orientation Training?</h3><br>
<p>
<%=aszTemp = org.getORGFormalTrain()%>
</p>



</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>

    
    </div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
