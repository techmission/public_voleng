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
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title"></span>
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
<span style="float: left;"></span>
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

						out.println("<b><br><br>NOTE: If the message above informs you of an error code '451 Temporary local problem - please try later Invalid Addresses', we want to inform you that your email inquiry should have successfully been sent to the Organizational contact, however a copy was not delivered to your email.<br><br>Therefore, if you'd like to make any future contact with the Organization, please refer to the information listed below:<br><br></b>");

						aszSent = "display: inline;";
//volunteers still get to see output to see the organization's contact information
// END REMOVE


					}
				String aszURL = "http://" + aszSubdomain;
				if(aszSubdomain.startsWith("ChristianVolunteering.org")){
					aszURL = "http://www.ChristianVolunteering.org";
				}else if(aszSubdomain.contains("ChurchVolunteering.org")){
					if(aszSubdomain.startsWith("ChurchVolunteering.org")){
						aszURL = "http://www.ChurchVolunteering.org";
					}
				}
				
				String aszProfileURL = aszURL + "/profiles/";
				String aszSendingIndivProfileURL = aszProfileURL + aCurrentUserObj.getUSPUsername(); 
				String aszReceivingIndivProfileURL = aszProfileURL + emailinfo.getEmailReceivingUserName(); 
				%>

				<div style="<%=aszSent%>">
					<h3>Your email was successfully sent to <%=emailinfo.getEmailContactFN()%> <%=emailinfo.getEmailContactLN()%>. </h3>
					<br><br>
					Below is the contact information for the user you emailed.
					<br><br>
					
					<table cellspacing=2 cellpadding=2 border=0>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Name:</b></td>
							<td><%=emailinfo.getEmailContactFN()%> <%=emailinfo.getEmailContactLN()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>Email Address:</b></td>
							<td><%=emailinfo.getEmailToUser()%></td>
						</tr>
						<tr><td colspan=2><hr><td></tr>
						<tr>
							<td><b>User Profile:</b></td>
							<td>
								<a href="<%=aszReceivingIndivProfileURL%>"><%=aszReceivingIndivProfileURL%></a>
							</td>
						</tr>
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
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->