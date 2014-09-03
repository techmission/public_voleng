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
<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<% 
String aszReqType = "";
try{
	aszReqType="&requesttype="+request.getParameter("requesttype");
}catch(NullPointerException ex){
}catch(Exception e){}
int iUserListCount=0; 
String aszClass="";
%>
<logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
	<% iUserListCount++; %>
</logic:iterate>

<%
int iCount=0;
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

table#contact th.contactRcvEmail{
width:110px;
}

table#contact th.contactCanEdit{
width:110px;
}
</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Opportunity Contacts for <%=opp.getOPPTitle()%></span>
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
  <span style="float: left;">Opportunity Contacts for <%=opp.getOPPTitle()%></span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=opp.getOPPTitle()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOppContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&oppnid=<%=opp.getOPPNID()%>&role=siteadmin">manage contacts</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage <%out.print(aszOrgOrChurchOpp.toLowerCase());%> opportunities</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=org.getORGOrgName()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOppContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&oppnid=<%=opp.getOPPNID()%><%=aszReqType%>">manage contacts</a> 
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
<H2>Manage Contacts for <em><%=opp.getOPPTitle()%></em>:</H2>

<% if(org.getErrorMsg().length() < 3 ){ %>
	<% if(request.getParameter("method").equalsIgnoreCase("addBrandNewContact")){ %>
		<br>
<font style="font-size:14px">
		A new user with email address <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a>
		<br>
		has been added as a Contact for <b><%=org.getORGOrgName()%></b>
		<br><br>
		<i>Email <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a> to inform him/her that they now have
		 been added as Volunteer Contacts for the following Opportunity: <%=opp.getOPPTitle()%></i>
</font>
		<br>
		<br>
	<% }else if(request.getParameter("method").equalsIgnoreCase("addNewContact")){ %>
		<br>
<font style="font-size:14px">
		<a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>
		has been added as a Volunteer Contact for <b><%=opp.getOPPTitle()%></b>
		<br><br>
		<i>Email this user, <a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>, to inform him/her that they now have
		<br>
		been added as Volunteer Contacts on <b><%=opp.getOPPTitle()%></b></i>
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
		has been removed from the Contacts of <%=opp.getOPPTitle()%>.
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
	<% } %>
<% } %>

<br>




<%

int i=0;
String aszOrgAdminTxt = aszOrgOrChurch + " Administrator";
String aszOrgAdminChecked = ""; String aszOppContactChecked = ""; String aszOppContactRcvEmailChecked = ""; String aszIsPrimaryContact = ""; String aszInitDisabledEdit="";
boolean b_isAdmin = false;
boolean b_isContact= false;
boolean b_isPrimaryContact= false;
boolean b_receivesEmail= false;
int[] a_iOrgAdminUIDs = org.getORGAdminUIDsArray();
int[] a_iOppContactUIDs = opp.getOPPContactUIDsArray();
int[] a_iOppContactUIDsRcvEmail = opp.getOPPContactUIDsRcvEmailArray();

%>

    <table id="contact" class="contact">


    <TR>
    	<th class="contactName">Name</th>
    	<th colspan=2 class="contactEmail">Email Address</th>
    	<th class="contactPrimary">Primary Contact</th>
    	<th class="contactRcvEmail">Receive<br>Volunteer Emails</th>
    	<th class="contactCanEdit">Remove User as Contact</th><!--contactCanEdit-->
    </tr>

    <!--TR><th>Name</th><th colspan=2>Email Address</th><th>Primary Contact</th><th>Receive<br>Volunteer Emails</th><th>Remove User as Contact</th></tr-->
    <logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
    	<%
    		aszClass="";
    		iCount++;
    		aszOrgAdminChecked = ""; aszOppContactChecked = ""; aszOppContactRcvEmailChecked = ""; aszIsPrimaryContact = "  ";aszInitDisabledEdit="";
    		b_isAdmin = false;
    		b_isContact= false;
    		b_isPrimaryContact= false;
    		b_receivesEmail= false;
    		
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}
    		if(opp.getOPPPrimaryPersonUID() == userprofile.getUserUID()){
    			aszIsPrimaryContact = " checked=true ";
    			b_isPrimaryContact = true;
    		}
    		
    		// if the current uid is in the "Administrators" array of the parent organization, there is no control from this page over their edit access
    		for(i=0; i<a_iOrgAdminUIDs.length; i++){
    			if(a_iOrgAdminUIDs[i]==userprofile.getUserUID()){
    				b_isAdmin = true;
    				aszOrgAdminChecked = " checked ";
    			}
    		}
    		// if the current uid is a contact of the given opp
    		for(i=0; i<a_iOppContactUIDs.length; i++){
    			if(a_iOppContactUIDs[i]==userprofile.getUserUID()){
    				b_isContact = true;
    				aszOppContactChecked = " checked ";
     				aszOrgAdminChecked = " checked ";
    			}
    		}
    		// if the current uid is a contact of the given opp & receives email
    		for(i=0; i<a_iOppContactUIDsRcvEmail.length; i++){
    			if(a_iOppContactUIDsRcvEmail[i]==userprofile.getUserUID()){
    				b_receivesEmail = true;
    				aszOppContactRcvEmailChecked = " checked ";
    				aszInitDisabledEdit = " disabled ";
    			}
    		}
    	%>
      <TR class="<%=aszClass%>">
        <TD><!--uid:<% out.println(userprofile.getUserUID());%>&nbsp;uprofile nid:<% out.println(userprofile.getUserProfileNID());%>-->
          		<%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>
		</td><td>
          		<%=userprofile.getUSPEmail1Addr()%>
        </td>
        <td>
        <a href="mailto:<%=userprofile.getUSPEmail1Addr()%>">
        <img class="email" height="17" width="22" border="0" title="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" alt="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" valign="middle" src="http://www.christianvolunteering.org/imgs/mail.gif"/></td>
        </a>
        <% if(userprofile.getIsPrimaryVolunteerContact()==0 ){ %>
        <td align="center" style="padding: 0px;padding-right:3px;">
         	<A href="<%=aszPortal%>/org.do?method=setOppPrimaryContact&uid=<%=userprofile.getUserUID()%><%=aszReqType%>&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&subdomain=<%=aszSubdomain%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Make Primary</A>
 		</td><td align="center" style="padding: 0px; padding-left:2px;">	
        	<% if(userprofile.getIsVolunteerContact()==0){ %>
        	No <br><em><font style="font-size:11px;">(<A href="<%=aszPortal%>/org.do?method=resetOppContact&uid=<%=userprofile.getUserUID()%><%=aszReqType%>&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&isemailcontact=1&subdomain=<%=aszSubdomain%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">change</A>)</font></em>
        	<% }else{ %>
        	Yes <br><em><font style="font-size:11px;">(<A href="<%=aszPortal%>/org.do?method=resetOppContact&uid=<%=userprofile.getUserUID()%><%=aszReqType%>&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&isemailcontact=0&subdomain=<%=aszSubdomain%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">change</A>)</font></em>
        	<% } %>
        </td><td align="center">
        	<% if(b_isAdmin == true){ %>
	        	<em>Administrator<br><a href="<%=aszPortal%>/org.do?method=showRemoveOrgAdmin&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</em>
        	<% }else{ %>
	        	<A href="<%=aszPortal%>/org.do?method=showRemoveOppContact&uid=<%=userprofile.getUserUID()%><%=aszReqType%>&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</A>
        	<% } %>
        </td>
        	

        <% } else { %>
        <td align="center">
        	YES
        </td><td colspan=2 align="center">
        	<i>can't delete primary contact</i>
        </td>
        <% } %>
      </tr>
      </logic:iterate>
      </table>
<br><br>



<A href="<%=aszPortal%>/org.do?method=showAddOppContact&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Contact</a>

    

</div>

  <br>
<ul>
<li>All users in this list have <b>Administrative</b> privileges on the given Opportunity, which allows them to edit or delete the given Opportunity, as well as add a new Opportunity listed under the same <%=aszOrgOrChurch%></li>
<li>The <b>Primay Contact</b>'s Name and Email Address will be given to Volunteers in response to Volunteer Inquiries, as well as listed on the public <%=aszOrgOrChurchOpp%> Opportunity Profile</li>
<li>ALL <b>Volunteer Contacts</b> who are marked to receive emails will receive Volunteer Inquiry Emails in effort to match the volunteer with this Opportunity</li>
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