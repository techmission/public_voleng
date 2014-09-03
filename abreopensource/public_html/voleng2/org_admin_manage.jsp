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
String aszClass="";
int	iCount=0;
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
h3, h4 {margin:0px; //margin: 5px 0;}
.heading3_select{ font-size:18px;}

.heading4_manage{ display: inline-block; margin-right:10px; font-size:14px; color: #666666; }
.add-new-buttn{
background-color:#83A2F4;
border:1px solid #4D73CF;
color:#FFFFFF;
font-weight:bold;
padding:3px;
text-decoration:none;
white-space:nowrap;
line-height:3em;
}
a.add-new-buttn:hover {
background-color:#4D73CF;
text-decoration:none;
color:#FFF;
}
.label_embeds {display: inline-block; text-align:right; font-weight:bold; width:50px;}
table#detail tr.gray {
    background-color: #E9E8E8;
}

</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title"><%=aszOrgOrChurch%> Administrators for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title"><%=aszOrgOrChurch%> Administrators</span>
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
  <span style="float: left;"><%=aszOrgOrChurch%> Administrators for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;"><%=aszOrgOrChurch%> Administrators</span>
	<% } %>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />&role=siteadmin">manage administrators</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage <%out.print(aszOrgOrChurch.toLowerCase());%>s</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgAdmins&orgnid=<bean:write name="orgForm" property="orgnid" />">manage administrators</a> 
	<% } %>
</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

<div id="body">
      	<div align="center">
<br><br>
<H2>Manage Administrators for <%=org.getORGOrgName()%>:</H2>

<% if(org.getErrorMsg().length() < 3 ){ %>
	<% if(request.getParameter("method").equalsIgnoreCase("addBrandNewContact")){ %>
		<br>
<font style="font-size:14px">
		A new user with email address <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a>
		<br>
		has been added as an Administrator for <b><%=org.getORGOrgName()%></b>
		<br><br>
		<i>Email <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a> to inform him/her that they now have
		 administrative privileges on <%=org.getORGOrgName()%></i>
</font>
		<br>
		<br>
	<% }else if(request.getParameter("method").equalsIgnoreCase("addNewContact")){ %>
		<br>
<font style="font-size:14px">
		<a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>
		has been added as an Administrator for <b><%=org.getORGOrgName()%></b>
		<br><br>
		<i>Email this user, <a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>, to inform him/her that they now have
		<br>
		administrative privileges on <b><%=org.getORGOrgName()%></b></i>
</font>
		<br>
		<br>
	<% }else if(request.getParameter("method").equalsIgnoreCase("processRemoveOrgContact")){ %>
		<br>
<font style="font-size:14px">
		The administrator 
		<% if( !( 	(request.getParameter("first").length() < 1 || request.getParameter("first")==null)
				||
					(request.getParameter("last").length() < 1 || request.getParameter("last")==null)
		)){%>
			<%=request.getParameter("first")%> <%=request.getParameter("last")%>
		<%} %>
		has been removed from the list of Administrators of <%=org.getORGOrgName()%>.
</font>
		<br>
		<br>
	<% } %>
<% } %>

<br>
    <table border=1 cellpadding=4 cellspacing=1 class="detail" id="detail" style="border-collapse:collapse">
    <TR><th>Name</th><th colspan=2>Email Address</th><th>Remove Administrator</th></tr>
    <logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
<%
    		aszClass="";
    		iCount++;
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}
%>
      <TR class="<%=aszClass%>">
        <TD><!--uid:<% out.println(userprofile.getUserUID());%>&nbsp;uprofile nid:<% out.println(userprofile.getUserProfileNID());%>-->
          		<%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>
		</td><td width=200>
          		<%=userprofile.getUSPEmail1Addr()%>
        </td>
        <td>
        <a href="mailto:<%=userprofile.getUSPEmail1Addr()%>">
        <img class="email" height="17" width="22" border="0" title="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" alt="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" valign="middle" src="http://www.christianvolunteering.org/imgs/mail.gif"/></td>
        </a>
        <td align="center">
        	<A href="<%=aszPortal%>/org.do?method=showRemoveOrgAdmin&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Delete</A>
        </td>
        	
      </tr>
      </logic:iterate>
      </table>
<br><br>



<A href="<%=aszPortal%>/org.do?method=showAddOrgAdmin&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Administrator</a>

    

</div>

  <br>
<ul>
<li>All users in this list have <b>Administrative</b> privileges on the given <%=aszOrgOrChurch%>, which allows them to add or delete Users (other Administrators) edit this <%=aszOrgOrChurch%>, and add, delete or edit <%=aszOrgOrChurchOpp%> Opportunities</li>
<li>The <b>Primary Administrator/Contact</b>'s Name will be provided on the public <%=aszOrgOrChurch%> Profile</li>
</ul>


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