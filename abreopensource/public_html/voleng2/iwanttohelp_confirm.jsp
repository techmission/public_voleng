<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->




<bean:define id="emailinfo" name="emailinfo" type="com.abrecorp.opensource.dataobj.EmailInfoDTO"/>

<!--script language="javascript">
function SubmitPost() {
  document.emailForm.submit() ;
  return false;
}
</script-->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );


	String aszSent = "display:none";
  
 // OrgOpportunityInfoDTO opp = (OrgOpportunityInfoDTO) request.getAttribute("opp");
 // int questionnaireInstanceId = (Integer) request.getAttribute("questionnaire_instance_id");
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">I Want to Help!</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">I Want to Help!</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> </div>
</div>
<% } %>

<html:form action="/email.do"  >

<% // for google analytics tracking: %>
	<% String aszGoalPage = "/volunteer/confirm"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
	<% //@include file="/template/footer_google_analytics.inc"%>
	<% //div id="body" onLoad=urchinTracker("/funnel_G3/step3.html") %>
<% // : end of for google analytics tracking %>
		<div id="body">
			<div align="left">
				<br><br><br>
				<%
					if (emailinfo.getErrorMsg() == ""){
						aszSent = "display: inline;";
					}
					else if (emailinfo.getErrorMsg() == "Email message sent OK! Email message sent OK!"){
						aszSent = "display: inline;";
					}



					else{
						//aszSent = "display: none;";
						out.println("<h3>" + "<span class=criticaltext>" + emailinfo.getErrorMsg() + "</span>" + "</h3>"  + "<br>");








// REMOVE - this is temporary!!! 2008-01-23
//added as a temporary notification fix
//emails are sending to orgs, but not to volunteers, so...

if(emailinfo.getErrorMsg().startsWith("You have previously")){
}else{ 
						out.println("<b><br><br>NOTE: If the message above informs you of an error code '451 Temporary local problem - please try later Invalid Addresses', we want to inform you that your email inquiry should have successfully been sent to the Organizational contact, however a copy was not delivered to your email.<br><br>Therefore, if you'd like to make any future contact with the Organization, please refer to the information listed below:<br><br></b>");
} 



						aszSent = "display: inline;";
//volunteers still get to see output to see the organization's contact information
// END REMOVE

					}
				%>

				<div style="<%=aszSent%>">
					<% if(emailinfo.getErrorMsg().startsWith("You have previously")){ %>
					<% }else{ %><%=emailinfo.getErrorMsg()%>
						<h3>Your email was successfully sent to <%=emailinfo.getEmailOrgName()%>. </h3>
					<% } %>
					<br><br>
					Below is the contact information for the <%out.print(aszOrgOrChurch.toLowerCase());%>.
					<br><br>
          
					<table cellspacing=2 cellpadding=2 border=0>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Opportunity Name:</b></td>
							<td><%=emailinfo.getEmailOppName()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b><%=aszOrgOrChurch%> Name:</b></td>
							<td><%=emailinfo.getEmailOrgName()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b><%=aszOrgOrChurch%> Website:</b></td>
							<td>
								<a href="http://<%=emailinfo.getEmailOrgWeb()%>"><%=emailinfo.getEmailOrgWeb()%></a>
							</td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b><%=aszOrgOrChurch%> Address:</b></td>
							<td>
								<%=emailinfo.getEmailOrgAddr1()%>
								<br>
								<%=emailinfo.getEmailOrgCity()%>,&nbsp;
					<%
					aszTemp= emailinfo.getEmailOrgState();
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

								<%=emailinfo.getEmailOrgProv()%>
								<br>
				<%
					
					
					aszTemp= emailinfo.getEmailOrgCountry();
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

							</td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Opportunity Contact Name:</b></td>
							<td><%=emailinfo.getEmailContactFN()%>&nbsp;<%=emailinfo.getEmailContactLN()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Contact Phone:</b></td>
							<td><%=emailinfo.getEmailOrgPhone()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Contact Email:</b></td>
							<td><%=emailinfo.getEmailOrg()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
					</table>
				</div>
			</div>
		</div>

		<P>
			<BR>
		</P>
</html:form>
		
	</div>
	
  

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<!-- Google Code for ChristianVolunteering.org Registered User Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "9qG9CLzeiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=9qG9CLzeiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
