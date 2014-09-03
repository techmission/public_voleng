<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Register to Recruit Volunteers</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%

ArrayList aCountryList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getCountryList( aCountryList, 101 );
boolean bNatlAssoc = false;

if(aszPortal.equals("/voleng")) portal="";
else if(session.getAttribute(tempPortal+"_type") != null ) if(session.getAttribute(tempPortal+"_type").toString().length() > 0) {
	portal = "";
	bNatlAssoc=true;
}

String aszOrgName = "";
if(request.getParameter("orgname") != null){
	aszOrgName = request.getParameter("orgname");
}
%>
<style>
#progressbar {
font-size:11px;
padding-top:7px;
}
#progressbar div {
color:#FFFFFF;
float:left;
font-size:10px;
height:1.4em;
margin-right:6px;
padding:0 5px;
text-align:center;
vertical-align:middle;
}
#progressbar ul {
list-style-type:none;
margin:0;
padding:0;
}
#progressbar li {
float:left;
margin:0;
padding:0 10px 0 0;
}
#progressbar div.boxon {
background-color:#F37C16;
}
#progressbar div.boxoff {
background-color:#999999;
}
#progressbar div{
		color:#ffffff;
    font-size: 14px;
    font-weight: bold;
		margin-right:0;
		padding:3px 7px 0;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
		background-color: #79A44E;
    font-size: 14px;
}
.accountboxoff{
	background-color: #BBBBBB;
    font-size: 14px;
}
.accounton{
	color: #003366;
	text-decoration:none;
}
.accountoff{
	color: #BBBBBB;
}

</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Register an Organization </span>
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

<span style="float: left;">Register an <%=aszOrgOrChurch%> </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> management</a> &gt; register an <%=aszOrgOrChurch.toLowerCase()%>  </div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>
<div id="body">
		<h2>Add <%=aszOrgOrChurch%></h2>
      <div class="hrule"></div>



   <!-- START PROGRESSBAR -->
      <DIV id="progressbar">
      <UL>
<% if(aszHostID.equalsIgnoreCase("volengchurch")){ %>      
<table cellpadding="2"><tr>
	<td><div class="accountboxon">1</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accounton">Add <%=aszOrgOrChurch%><br>Profile</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">2</div></td>
		<td><a href="<%=aszPortal%>/churchopportunities.jsp" class="accountoff">Choose from Existing Organizations<br>& Service Opportunties</a></td>
	<td></td><td></td><td></td><td></td><td></td><td><div class="accountboxoff">3</div></td>
		<td><a href="#" class="accountoff">Create Own <%=aszOrgOrChurch%><br><%=aszOrgOrChurchOpp%> Opportunities</a></td>
</tr></table>

<% }else{ %>
<table cellpadding="2"><tr>
	<td><div class="accountboxon">1</div></td>
		<td style="padding: 5px 10px; border: 1px solid #79A44E; background: #F9FAFC;"><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accounton">Check Status</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxoff">2</div></td>
		<td><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1" class="accountoff">Add <%=aszOrgOrChurch%> Profile</a></td>
	<td></td>
	<td></td><td></td><td></td><td></td>
	<td><div class="accountboxoff">3</div></td>
		<td><a href="#" class="accountoff">Add <%=aszOrgOrChurch%> <%=aszOrgOrChurchOpp%><br>Opportunity Listing</a></td>
</tr></table>
<% } %>
      </UL>
      </DIV>
      <!-- END PROGRESSBAR -->

<br /><br />
      <p style="font-weight: bold">Please complete all required fields below.</p>

<% if(bNatlAssoc==false){ %>
<form action="<%=request.getContextPath()%>/org.do?neworg" focus="orgname" id="orgForm" name="orgForm">
<% }else{ %>
<form action="<%=aszPortal%>/org.do?neworg" focus="orgname" id="orgForm" name="orgForm">
<% } %>
<input type="hidden" name="method" value="processcreatenonpstep1" />
     
      Enter your <%=aszOrgOrChurch.toLowerCase()%>'s name and ZIP/Postal Code below to see if you are already registered with TechMission.
      <BR>
      <DIV class=clear style="HEIGHT: 5px"></DIV>
<br>
<table width=90%><tr>

<% if( !(aszNarrowTheme=="true") ) { %>
	<td width=10%></td>
<% }else{ %>
	<td></td>
<% } %>

<td>
            <table width="410" border="0" cellpadding="2" cellspacing="0" id="splash" >
              
              <TR>
                <TD>Name <span class="criticaltext">*</span></TD>
                <TD colsapn=2>
                	<input type="text" name="orgname" size="30" styleClass="textinputwhite" value="<%=aszOrgName%>"/>
                </TD>
              </TR>
              <TR>
                <TD>Postal Code <span class="criticaltext">*</span></TD>
                <TD colspan=2>
                	<input type="text" name="postalcode" size="30" styleClass="textinputwhite"/>
                </TD>
              </TR>
              <TR>
                <TD colspan=1>Country <span class="criticaltext">*</span></TD>
                <TD colspan=2>
                	<SELECT id="country" name="country" class="smalldropdown" >
                    <OPTION value="US" selected>United States</OPTION> 

					<%
					aszTemp= "";
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>

                  </SELECT> 
                </TD>
              </TR>
              <TR>
                <TD>&nbsp;</TD>
                <TD>
                  <DIV class=clear style="HEIGHT: 10px"></DIV><INPUT type=hidden name=submit> <INPUT class=submit type=submit value=Continue >
              </TD><!-- onClick="javascript:urchinTracker('/organization/create/step2');" -->
                <TD>&nbsp;</TD>
              </TR>
              <TR>
                <TD colspan=3><span class= "criticaltext">*</span><span style="font-weight: bold"> Required Item</span></td>
              </TR>

              <TR>
                <TD colspan=3 class="error" ><bean:write name="orgForm" property="errormsg" /></td>
              </TR>

          </TABLE>
</td></tr></table>     
</div>

</form>

      <P>
      <BR>&nbsp;</P></div>


<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>


<%
	String aszGoalPage = "";
	String aszGoalGA = "";
	if(request.getParameter("newuser")!=null){
		try{
			aszGoalGA=request.getParameter("newuser");
		}catch(NullPointerException e){
			aszGoalGA = "";
		}
	}
if(aszGoalGA.equalsIgnoreCase("confirm") || request.getParameter("method").equalsIgnoreCase("processCreateAccount1")){
	aszGoalPage = "/confirm/individual"; 
%>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% } %>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %>

<!-- end footer information -->