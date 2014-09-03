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

String aszOrgNid = "";
try{
	aszOrgNid=request.getParameter("orgnid");
}catch(NullPointerException ex){
}catch(Exception e){}

String aszOppNid = "";
try{
	aszOppNid=request.getParameter("oppnid");
}catch(NullPointerException ex){
}catch(Exception e){}

int iUserListCount=0; 
String aszClass="";
String aszCurrentUserContact = "";
try{
	if(request.getParameter("requesttype").toString().contains("ByContact")){
		aszCurrentUserContact="ByContact";
	}
}catch(NullPointerException ex){}catch(Exception e){}
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

.add-new-buttn{
background-color:#83A2F4;
border:1px solid #4D73CF;
color:#FFFFFF;
font-weight:bold;
padding:3px;
text-decoration:none;
width:140px;
}
</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Contacts for <%=opp.getOPPTitle()%></span>
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
  <span style="float: left;">Contacts for <%=opp.getOPPTitle()%></span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=opp.getOPPTitle()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOppOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&oppnid=<%=opp.getOPPNID()%>&role=siteadmin">manage contacts</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage <%out.print(aszOrgOrChurchOpp.toLowerCase());%> opportunities</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=org.getORGOrgName()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOppOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&oppnid=<%=opp.getOPPNID()%><%=aszReqType%>">manage contacts</a> 
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



  <div id="contactinfo">
<form action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=processEditOppContacts<%}%>" name="orgForm" method="get">
<input type="hidden" name="method" value="processEditOppContacts" />
<input type="hidden" name="requesttype" value="<%=aszCurrentUserContact%>" />
<input type="hidden" name="orgnid" value="<%=aszOrgNid%>" />
<input type="hidden" name="oppnid" value="<%=aszOppNid%>" />

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
<script language="javascript">
function clicked_make_primary(box){
	var vis = (box.checked) ? "block" : "none";
	var value= (box.value);

	var temp=0;
	var temp2="";
	var temp3="";
	
	var tempPrimaryDiv="";
	var tempPrimarySecondaryDiv="";
	var tempPrimaryEmailElement="";
	
	var emailElementName = "oppcontactuidsrcvemailarray";
	var emailElement = "oppcontactuidsrcvemailarray["+value+"]";
	var editElementName = "oppcontactuidsarray";
	var editElement = "oppcontactuidsarray["+value+"]";
	var tempEmailElementName = "emailarraytemp";
	var tempEmailElement = "emailarraytemp["+value+"]";
	var tempEditElementName = "editarraytemp";
	var tempEditElement = "editarraytemp["+value+"]";

	var emailElementDiv = "emailarraychbx["+value+"]";
	var editElementDiv = "editarraychbx["+value+"]";
	var tempEmailElementDiv = "tempemailarraychbx["+value+"]";
	var tempEditElementDiv = "tempeditarraychbx["+value+"]";

	if(vis=="block"){
		// initially re-enable all email elements (will re-disable new primary contact later in code)
<% for(i=0; i<iUserListCount; i++){ %>
		temp = document.getElementsByName(emailElementName)[<%out.print(i);%>].value;
		temp2="emailarraychbx["+temp+"]";
		temp3="tempemailarraychbx["+temp+"]";
		document.getElementsByName(emailElementName)[<%out.print(i);%>].disabled=false;
		document.getElementById(temp2).style.display='inline';
		document.getElementById(temp3).style.display='none';
<% } %>
	temp=0;
	temp2="";
	temp3="";
	
	// initially re-enable for editing; however, if the value is in the array of admin ids, should then check off and disable again
<% 
int iUIDTemp=0, iTempListCount=0;
for(i=0; i<iUserListCount; i++){ %>
		temp = document.getElementsByName(editElementName)[<%out.print(i);%>].value;
		temp2="emailarraychbx["+temp+"]";
		temp3="tempemailarraychbx["+temp+"]";
		document.getElementsByName(editElementName)[<%out.print(i);%>].disabled=false;
		document.getElementById(temp2).style.display='inline';
		document.getElementById(temp3).style.display='none';
<% }  %>

<%
for(i=0; i<iUserListCount; i++){
	for(iTempListCount=0; iTempListCount<a_iOppContactUIDsRcvEmail.length; iTempListCount++){
		iUIDTemp=a_iOppContactUIDsRcvEmail[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){ <% // if the array element uid DOES receive emails %>
			document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
			
			document.getElementById(tempEditElementDiv).style.display='inline';
		}
<%	
	} // end itereate through receives emails users 
} // end iterate through all users

iUIDTemp=0;
for(i=0; i<iUserListCount; i++){ // iterate through all users
	for(iTempListCount=0; iTempListCount<a_iOrgAdminUIDs.length; iTempListCount++){ // find all the admin users; can't edit their "edit" option ever
		iUIDTemp=a_iOrgAdminUIDs[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){
			document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
			document.getElementById(tempEditElementDiv).style.display='inline';
		}
<%	
	} // end iterate through is org admin 
}  // end iterate through all users
%>
		document.getElementById(emailElement).checked=true;
		document.getElementById(tempEmailElement).checked=true;
		document.getElementById(tempEmailElementDiv).style.display='inline';
		document.getElementById(emailElementDiv).style.display='none';
		
		document.getElementById(editElement).checked=true;
		document.getElementById(tempEditElement).checked=true;
		document.getElementById(tempEditElementDiv).style.display='inline';
		document.getElementById(editElementDiv).style.display='none';
		document.getElementById(tempEditElement).disabled=true;
	}else{	
<% for(i=0; i<iUserListCount; i++){ // iterate through all users %>
		document.getElementsByName(emailElementName)[<%out.print(i);%>].disabled=false;
<% } %>
		document.getElementById(editElement).disabled=false;
	}
}

function clicked_receive_emails(box){
	var vis = (box.checked) ? "block" : "none";
	var value= (box.value);
	var temp=0; var temp2=""; var temp3="";
	var emailElement = "oppcontactuidsrcvemailarray["+value+"]";
	var editElement = "oppcontactuidsarray["+value+"]";
	var editElementName = "oppcontactuidsarray";
	var tempEmailElementName = "emailarraytemp";
	var tempEmailElement = "emailarraytemp["+value+"]";
	var tempEditElementName = "editarraytemp";
	var tempEditElement = "editarraytemp["+value+"]";

	var emailElementDiv = "emailarraychbx["+value+"]";
	var editElementDiv = "editarraychbx["+value+"]";
	var tempEmailElementDiv = "tempemailarraychbx["+value+"]";
	var tempEditElementDiv = "tempeditarraychbx["+value+"]";
	if(vis=="block"){
		document.getElementById(editElement).checked=true;
		document.getElementById(tempEditElement).checked=true;
		document.getElementById(tempEditElementDiv).style.display='inline';
		document.getElementById(editElementDiv).style.display='none';
			document.getElementById(editElement).disabled=false;
	}else{
		document.getElementById(editElement).disabled=false;
		document.getElementById(editElementDiv).style.display='inline';
			document.getElementById(editElement).disabled=false;
		document.getElementById(tempEditElementDiv).style.display='none';
<%


// something in the code following then shows the incorrect edit divs again


for(i=0; i<iUserListCount; i++){
	for(iTempListCount=0; iTempListCount<a_iOrgAdminUIDs.length; iTempListCount++){
		iUIDTemp=a_iOrgAdminUIDs[iTempListCount];
%>
		if(document.getElementsByName(editElementName)[<%out.print(i);%>].value==<%=iUIDTemp%>){
			temp=<%=iUIDTemp%>;
			temp2="editarraychbx["+temp+"]";
			temp3="tempeditarraychbx["+temp+"]";
			document.getElementsByName(tempEditElementName)[<%out.print(i);%>].disabled=true;
			document.getElementById(temp2).style.display='none';
			document.getElementById(temp3).style.display='inline';
		}
<% }} %>
	}
}

</script>

    <table id="contact" class="contact" border="1">
    <TR>
    	<th class="contactName">Name</th>
    	<th colspan=2 class="contactEmail">Email Address</th>
    	<th class="contactPrimary">Primary Contact</th>
    	<th class="contactRcvEmail">Receives Emails</th>
    	<th class="contactCanEdit">Can Edit Opportunity</th>
    	<th class="contactRcvEmail">Remove</th>
    </tr>
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
    			aszOppContactRcvEmailChecked = " checked ";
    			aszOppContactChecked = " checked ";
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
		</td><td width=200>
          		<%=userprofile.getUSPEmail1Addr()%>
        </td>
        <td>
        <a href="mailto:<%=userprofile.getUSPEmail1Addr()%>">
        <img class="email" height="17" width="22" border="0" title="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" alt="Send Email to <%=userprofile.getUSPNameFirst()%>&nbsp;<%=userprofile.getUSPNameLast()%>" valign="middle" src="http://www.christianvolunteering.org/imgs/mail.gif"/></td>
        </a>
        <td align="center">
<input type="radio" name="oppprimarycontactuid" onclick="clicked_make_primary(this)" id="oppprimarycontactuid" value="<%=userprofile.getUserUID()%>" <%=aszIsPrimaryContact%> />
 		</td>
		<td align="center">	
	<% if(b_isPrimaryContact == true){ %>
<div id="emailarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsrcvemailarray" onclick="clicked_receive_emails(this)" id="oppcontactuidsrcvemailarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
<div id="tempemailarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="emailarraytemp" disabled onclick="clicked_receive_emails(this)" id="emailarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
	<% }else{ %>
<div id="emailarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="oppcontactuidsrcvemailarray" onclick="clicked_receive_emails(this)" id="oppcontactuidsrcvemailarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
<div id="tempemailarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="emailarraytemp" disabled onclick="clicked_receive_emails(this)" id="emailarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactRcvEmailChecked%> />
</div>
	<% } %>
        </td>
        <td align="center">
    <% if(b_isAdmin == true){ %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
    <% } else { %>
        	<% if(b_isPrimaryContact == true || b_receivesEmail == true ){ // || b_isContact == true %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
 			<% }else{ %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="oppcontactuidsarray" id="oppcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOppContactChecked%> />
</div>
			<% } %>
    <% } %>
        </td>
        
        
        
        <td align="center">	
        	<% if(b_isAdmin == true){ %>
	        	<em>Administrator<br><a href="<%=aszPortal%>/org.do?method=showRemoveOrgAdmin&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</em>
        	<% }else{ %>
    	    	<A href="<%=aszPortal%>/org.do?method=showRemoveOrgContact&uid=<%=userprofile.getUserUID()%>&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>">Remove</A>
        	<% } %>
        </td>	

      </tr>
      </logic:iterate>
      </table>

  </div>
<br>
	<div class="right-column">
		<INPUT class="add-new-buttn" type="submit" value="Update">
	</div>
</form>
  <!-- end   <div id="contactinfo"-->




<br><br><br><br>
<A href="<%=aszPortal%>/org.do?method=showAddOppContact&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Contact</a>

    

</div>

  <br>
<ul>
<li>All users in these lists have <em>partial</em> <b>Administrative</b> privileges (or full, if they are listed as "Administrator") on the given <%=aszOrgOrChurch%>, which allows them to add or delete Users (other Administrators) and Organizations</li>
<li>The <b>Primay Contact</b>'s Name and Email Address will be given to Volunteers in response to Volunteer Inquiries</li>
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