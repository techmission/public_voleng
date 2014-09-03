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
int iCount=0;
boolean b_isAdmin = false;

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

</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Organization Contacts for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Organization Contacts</span>
	<% } %>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
	<% if(bAdminRole==true){ %>
  <span style="float: left;">Organization Contacts for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Organization Contacts</span>
	<% } %>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&role=siteadmin">manage contacts</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage organizations</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <bean:write name="orgForm" property="orgname" /></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" />">manage contacts</a> 
	<% } %>
</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>


<div id="body">
      	<div align="center">
<br><br>
<H2>Manage Contacts for <%=org.getORGOrgName()%>:</H2>
<!--error: <%=org.getErrorMsg().length()%><br><br>-->
<% if(org.getErrorMsg().length() < 3 ){ %>
	<% if(request.getParameter("method").equalsIgnoreCase("addBrandNewContact")){ %>
		<br>
<font style="font-size:14px">
		A new user with email address <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a>
		<br>
		has been added as a Contact for <b><%=org.getORGOrgName()%></b>
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
		has been added as a Contact for <b><%=org.getORGOrgName()%></b>
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
		The contact 
		<% if( !( 	(request.getParameter("first").length() < 1 || request.getParameter("first")==null)
				||
					(request.getParameter("last").length() < 1 || request.getParameter("last")==null)
		)){%>
			<%=request.getParameter("first")%> <%=request.getParameter("last")%>
		<%} %>
		has been removed from the Contacts of <%=org.getORGOrgName()%>.
</font>
		<br>
		<br>
	<% }else if(request.getParameter("method").equalsIgnoreCase("setPrimaryContact")){ %>
		<br>
<font style="font-size:14px">
		The primary contact has been changed.
</font>
		<br>
		<br>
	<% }else{ %>
<FONT color="red"><bean:write name="orgForm" property="errormsg" /></FONT><br />	
	<% } %>
<% }else{ %>
<FONT color="red"><bean:write name="orgForm" property="errormsg" /></FONT><br />	
<% } %>

<%

int i=0;
int[] a_iOrgAdminUIDs = org.getORGAdminUIDsArray();

%>
<br>
    <table border=1 cellpadding=4 cellspacing=1 class="detail">
    <TR><th>Name</th><th colspan=2>Email Address</th><th>Remove Contacts</th></tr>
    <logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
    	<%
    		aszClass="";
    		iCount++;
    		b_isAdmin = false;
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}
    		
    		// if the current uid is in the "Administrators" array of the parent organization, there is no control from this page over their edit access
    		for(i=0; i<a_iOrgAdminUIDs.length; i++){
    			if(a_iOrgAdminUIDs[i]==userprofile.getUserUID()){
    				b_isAdmin = true;
    			}
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
        </td><td align="center">
        	<% if(b_isAdmin == true){ %>
	        	<em>Administrator<br><a href="<%=aszPortal%>/org.do?method=showRemoveOrgAdmin&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</em>
        	<% }else{ %>
    	    	<A href="<%=aszPortal%>/org.do?method=showRemoveOrgContact&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</A>
        	<% } %>
        </td>

      </tr>
      </logic:iterate>
      </table>
<br>


<!--
<br>
<A href="<%=aszPortal%>/org.do?method=showAddContact&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Contact/Administrator</a>
-->
    

</div>

<br><ul>
<li>All users in these lists have <em>partial</em> <b>Administrative</b> privileges (or full, if they are listed as "Administrator") on the given <%=aszOrgOrChurch%>, which allows them to add or delete Users (other Administrators) and Organizations</li>
</ul>

</div>

    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- Google Code for ChristianVolunteering.org Registered Organization Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "y02aCMTdiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=y02aCMTdiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->