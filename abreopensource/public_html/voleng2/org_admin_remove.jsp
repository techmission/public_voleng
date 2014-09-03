<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<%
// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

String aszPortalLink = aszPortal;
if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
	aszPortalLink = aszPortal.substring(7,aszPortal.length());
}
%>

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

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Remove Administrator from <%=aszOrgOrChurch%> <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Remove Administrator from <%=aszOrgOrChurch%></span>
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
  <span style="float: left;">Remove Administrator from <%=aszOrgOrChurch%> <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Remove Administrator from <%=aszOrgOrChurch%></span>
	<% } %>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />&role=siteadmin">manage admins</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage organizations</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />">manage admins</a> 
	<% } %>
&gt; remove administrator
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
<FONT color="red"><bean:write name="orgForm" property="errormsg" /></FONT><br />
      	<div align="center">
<br><br>
     <fieldset width=300>
<LEGEN><h3>Remove Administrator:</h3></legen>
<br><br><br>
<font style="font-size:14px">
Are you sure you would like to remove <b><%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%></b>
<br>
from the administrators for
<b><%=org.getORGOrgName()%></b>?
</font>
<br><br><br>

<A href="<%=aszPortal%>/org.do?method=processRemoveOrgAdmin&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%>&first=<%=userprofile.getUSPNameFirst()%>&last=<%=userprofile.getUSPNameLast()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="button">Remove as Administrator</A>
&nbsp;&nbsp;&nbsp;&nbsp;
<A href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="button">Cancel</A>

<br><br>
      </fieldset>




    

</div>

      <P>
      <BR>&nbsp;</P></div>

    </div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
