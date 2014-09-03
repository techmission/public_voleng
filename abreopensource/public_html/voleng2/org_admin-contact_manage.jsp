<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
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
if(aszOppNid==null || aszOppNid.equalsIgnoreCase("null")){
	aszOppNid="";
}

int iUserListCount=0; 
int iTempListCount=0, iUIDTemp=0;
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
	  <span id="title">Administrators & Contacts for <%=org.getORGOrgName()%></span>
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
  <span style="float: left;">Administrators & Contacts for <%=org.getORGOrgName()%></span>
<div id="banner_side_graphic"></div>
<% } %>
<div id="breadcrumbs">
<a href="<%=aszPortal%>/">home</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=org.getORGOrgName()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" />&role=siteadmin">manage contacts</a> 
	<% }else{ %>
		<a href="<%=aszPortal%>/orgmanagement.jsp">manage <%out.print(aszOrgOrChurchOpp.toLowerCase());%> opportunities</a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name="orgForm" property="orgnid" />">manage <%=org.getORGOrgName()%></a> &gt; 
		<a href="<%=aszPortal%>/org.do?method=showOrgContacts&orgnid=<bean:write name="orgForm" property="orgnid" /><%=aszReqType%>">manage contacts</a> 
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
<H2>Manage Administrators & Contacts for <em><%=org.getORGOrgName()%></em>:</H2>

<% if(org.getErrorMsg().length() < 3 ){ %>
	<% if(request.getParameter("method").equalsIgnoreCase("addBrandNewContact")){ %>
		<br>
<font style="font-size:14px">
		A new user with email address <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a>
		<br>
		has been added as a Contact for <b><%=org.getORGOrgName()%></b>
		<br><br>
		<i>Email <a href="mailto:<%=request.getParameter("email1addr")%>"><%=request.getParameter("email1addr")%></a> to inform him/her that they now have
		 been added as Volunteer Contacts for the following Opportunity: <%=org.getORGOrgName()%></i>
</font>
		<br>
		<br>
	<% }else if(request.getParameter("method").equalsIgnoreCase("addNewContact")){ %>
		<br>
<font style="font-size:14px">
		<a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>
		has been added as a Volunteer Contact for <b><%=org.getORGOrgName()%></b>
		<br><br>
		<i>Email this user, <a href="mailto:<%=request.getParameter("email")%>"><%=request.getParameter("email")%></a>, to inform him/her that they now have
		<br>
		been added as Volunteer Contacts on <b><%=org.getORGOrgName()%></b></i>
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
	<% } %>
<% }else if(
		org.getErrorMsg().contains("is currently listed as a contact on some of your Opportunities")	||
		org.getErrorMsg().contains("contacts of opportunities, and have therefore not been removed")
){ %>
<div align="left" style="padding: 10px; 100px;">
<font color="red"><%=org.getErrorMsg()%></font>
	<br />
	<logic:iterate id="contactnotremoved" name="contactnotremovedlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
		<% if(contactnotremoved.getUserUID()>0){ %>
			<br />
			<%=contactnotremoved.getUSPNameFirst()%> <%=contactnotremoved.getUSPNameLast()%>
				<logic:notEmpty name="contactopplist">
					is currently a contact on the following opportunities:
					<ol>
					<logic:iterate id="opp" name="contactopplist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
					<%  // if getUserOppNIDsArray contains the opp nid, then list it
						if(contactnotremoved.getUserUID()==opp.getOPPUID()){
					%>
						<li>
							<a href="<%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=org.getORGNID()%>&oppnid=<%=opp.getOPPNID()%>&requesttype=edit#manage_contacts"><%=opp.getOPPTitle()%></a>
						</li>
					<%
						}
					%>
					</logic:iterate>
					</ol>	
				</logic:notEmpty>	
		<% } %>
	</logic:iterate>
	</ol>
</div>
<% }else{ %>
<font color="red"><%=org.getErrorMsg()%></font>
<br>
<% } %>

<br>



  <div id="contactinfo">
<form action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=processEditOppContacts<%}%>" name="orgForm" method="get">
<input type="hidden" name="method" value="processEditOrgContactAdmins" />
<input type="hidden" name="requesttype" value="<%=aszCurrentUserContact%>" />
<input type="hidden" name="orgnid" value="<%=aszOrgNid%>" />
<input type="hidden" name="oppnid" value="<%=aszOppNid%>" />

<%

int i=0;
String aszOrgAdminTxt = aszOrgOrChurch + " Administrator";
String aszOrgAdminChecked = ""; String aszOrgContactChecked = ""; String aszOppContactRcvEmailChecked = ""; String aszIsPrimaryContact = ""; String aszInitDisabledEdit="";
boolean b_isAdmin = false;
boolean b_isContact= false;
int[] a_iOrgAdminUIDs = org.getORGAdminUIDsArray();
int[] a_iOrgContactUIDs = org.getORGContactUIDsArray();

%>
<script language="javascript">

function clicked_admin(box){
	var vis = (box.checked) ? "block" : "none";
	var value= (box.value);
	var temp=0; var temp2=""; var temp3="";
	var editElement = "orgcontactuidsarray["+value+"]";
	var editElementName = "orgcontactuidsarray";
	var tempEditElementName = "editarraytemp";
	var tempEditElement = "editarraytemp["+value+"]";

	var editElementDiv = "editarraychbx["+value+"]";
	var tempEditElementDiv = "tempeditarraychbx["+value+"]";
	if(vis=="block"){
		document.getElementById(editElement).checked=true;
		document.getElementById(tempEditElement).checked=true;
		document.getElementById(tempEditElementDiv).style.display='inline';
		document.getElementById(editElementDiv).style.display='none';
		document.getElementById(editElement).disabled=false;
	}else{
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
		document.getElementById(editElement).disabled=false;
	}
}

</script>

    <table id="contact" class="contact" border="1">
    <TR>
    	<th class="contactName">Name</th>
    	<th colspan=2 class="contactEmail">Email Address</th>
    	<th class="contactCanEdit"><%=aszOrgOrChurch%> Contact</th>
    	<th class="contactRcvEmail"><%=aszOrgOrChurch%> Administrator</th>
    </tr>
    <logic:iterate id="userprofile" name="userlist" type="com.abrecorp.opensource.dataobj.PersonInfoDTO">
    	<%
    		aszClass="";
    		iCount++;
    		aszOrgAdminChecked = ""; aszOrgContactChecked = ""; aszOppContactRcvEmailChecked = ""; aszIsPrimaryContact = "  ";aszInitDisabledEdit="";
    		b_isAdmin = false;
    		b_isContact= false;
    		
    		if(iCount%2 != 0){ // if ODD
    			aszClass="gray";
    		}
    		
    		// if the current uid is in the "Administrators" array of the parent organization, there is no control from this page over their edit access
    		for(i=0; i<a_iOrgAdminUIDs.length; i++){
    			if(a_iOrgAdminUIDs[i]==userprofile.getUserUID()){
    				b_isAdmin = true;
    				aszOrgAdminChecked = " checked ";
    				aszOrgContactChecked = " checked ";
    			}
    		}
    		// if the current uid is a contact of the given opp
    		for(i=0; i<a_iOrgContactUIDs.length; i++){
    			if(a_iOrgContactUIDs[i]==userprofile.getUserUID()){
    				b_isContact = true;
    				aszOrgContactChecked = " checked ";
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
    <% if(b_isAdmin == true ){ %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="orgcontactuidsarray" id="orgcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
</div>
    <% } else { %>
<div id="editarraychbx[<%=userprofile.getUserUID()%>]" style="display:inline;">
	<input type="checkbox" name="orgcontactuidsarray" id="orgcontactuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgContactChecked%> />
</div>
<div id="tempeditarraychbx[<%=userprofile.getUserUID()%>]" style="display:none;">
	<input type="checkbox" name="editarraytemp" disabled id="editarraytemp[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgContactChecked%> />
</div>
    <% } %>
        </td>
        
        
        
        <td align="center">	
	<input type="checkbox" name="orgadminuidsarray" onClick="clicked_admin(this)" id="orgadminuidsarray[<%=userprofile.getUserUID()%>]" value="<%=userprofile.getUserUID()%>" <%=aszOrgAdminChecked%> />
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

<a href="<%=aszPortal%>/org.do?method=showAddOrgContact&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Contact</a>
&nbsp;&nbsp;&nbsp;

<a href="<%=aszPortal%>/org.do?method=showAddOrgAdmin&orgnid=<%=org.getORGNID()%><% if(bAdminRole==true){ %>&role=siteadmin<% } %>" class="add-new-buttn">Add a New Administrator</a>

    

</div>

  <br>
<ul>
<li>An <b>Administrator</b> has full privileges, if they are listed as "Administrator" on the given <%=aszOrgOrChurch%>, which allows them to add or remove Users (other Administrators & Contacts), <%=aszOrgOrChurch%>, or Opportunities</li>
<li>A <b>Contact</b> has <em><b>partial Administrative</b></em> privileges on the given <%=aszOrgOrChurch%>, which allows them to add or delete Users (other Contacts) and manage the specific Opportunities they are a Contact for or post new Opportunities</li>
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