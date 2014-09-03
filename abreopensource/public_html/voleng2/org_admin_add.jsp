<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Register to Recruit Volunteers</title>

<% } else { %>
<% } %>


<bean:define id="theStatus" name="orgForm" property="errormsg" type="java.lang.String"/>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<%
ArrayList aCountryList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getCountryList( aCountryList, 101 );

// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

String aszRole="";
if(! (request.getParameter("role")==null || request.getParameter("role").equals(null)) ){
	aszRole=request.getParameter("role");
}
String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Add <%=aszOrgOrChurch%> Administrator for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Add <%=aszOrgOrChurch%> Administrator</span>
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
  <span style="float: left;">Add <%=aszOrgOrChurch%> Administrator for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Add <%=aszOrgOrChurch%> Administrator</span>
	<% } %>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />&role=siteadmin">manage administrators</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage organizations</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />">manage administrators</a> 
	<% } %>
&gt; add administrator
</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
<br>
&laquo; <a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" /><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Back to Manage Administrators</a>
<br/>
		<!--h2>Add an Administrator:</h2-->
<FONT color="red"><bean:write name="orgForm" property="errormsg" /><%=org.getErrorMsg()%></FONT><br />
      <p style="font-weight: bold">Please complete all required fields below.</p><br>

<h3>Add Existing User as Administrator:</h3><br>     
      If you are adding an existing user in our system as an administrator for this organization, please enter the Email address below:
      <BR>
      <DIV class=clear style="HEIGHT: 5px"></DIV>
<br>


<form name="orgForm" action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=addNewOrgAdmin<%}%>" >
<input type="hidden" name="method" value="addNewOrgAdmin" />
<input type="hidden" name="orgnid" value="<%=org.getORGNID()%>"/>
	<% if(bAdminRole==true){ %>
<input type="hidden" name="role" value="<%=aszRole%>" />
	<% } %>
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />


Email Address:&nbsp;<span class="criticaltext">*</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="email" size="50" styleClass="textinputwhite"/>&nbsp;&nbsp;&nbsp;
<INPUT type=hidden name=submit> <INPUT class=submit type=submit value="Add Administrator">
<br>


</form>




<br><br>

<hr>

<br><br>
<b>OR...</b>
<br><br><h3>Add a Brand New Administrator:</h3><br>
<!--
(NOTE: It is YOUR responsibility to notify any new users of a new account, including Email and Password)
-->
<br><br>

<% /**/ %>




<form method="post" name="orgForm" action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=addBrandNewOrgAdmin<%}%>" >
<input type="hidden" name="method" value="addBrandNewOrgAdmin" />
<input type="hidden" name="subdomain" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteusetype" value="Organization" />
<input type="hidden" name="orgnid" value="<%=org.getORGNID()%>"/>
	<% if(bAdminRole==true){ %>
<input type="hidden" name="role" value="<%=aszRole%>" />
	<% } %>
<input type="hidden" name="volunteertid" value="8865" />


            <!--nested table -->
            <table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
              <!-- MSTableType="layout" -->
				
			  <tr>
                <TD width=130>Username <span class="criticaltext">*</span></TD>
                <TD>
                <input type="text" name="username" styleId="username" size="30" styleClass="textinputwhite"/>
                </TD>
					<td height="23"></td>
				</tr>
				<tr>
        		<TD >Email Address <span class="criticaltext">*</span></TD>
       			<TD height="23"><input type="text" name="email1addr" styleId="email1addr" size="35" styleClass="textinputwhite"/></TD>
       			</tr>
				<tr>
                <TD >New Password <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<input type="password" name="password1" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
             		</tr>
				<tr>
                <TD >Confirm Password <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<input type="password" name="password2" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
					</tr>



				<tr>
                <TD >First name <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text" name="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite"/></TD>
				</tr>
				<tr>
                <TD >Last name <span class="criticaltext">*</span></TD>
                <TD  height="23"><input type="text" name="namelast" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
              		</tr>
              	

<tr><td colspan=3>
<table width="540" border="0" cellpadding="0" cellspacing="0" id="splash" >




<tr>
              		<td width="40">&nbsp;</td>
              		<td width="90">&nbsp;</td>
                <TD height="75">
                  <DIV class=clear style="HEIGHT: 10px"></DIV>
                  <INPUT type=hidden name=submit> <INPUT class=submit type=submit value="Add New Administrator"> 
                 </TD>
             		</tr>
				<tr>
                <TD colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td>
              		<td height="21"></td>
				</tr>

            </TABLE>
</td></tr>
</table>

</form>

<% /* */ %>
</div>



      <BR></div>


<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
