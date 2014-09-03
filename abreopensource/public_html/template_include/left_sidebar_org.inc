<%
if(aszRemoteHost.equalsIgnoreCase( "chrisvol.org:7001" )){
	aszSubdomain = "chrisvol.org:7001";
}
int iNumOrgsList = 0;
String sidebarPortal=portal;
int iSidebarCurrOrgNID=0, iSidebarCurrOppNID=0, iSidebarTempNID=0, iSidebarTempCount=0;
String aszSidebarSelected="";
boolean bSidebarManageAssoc=false;
String aszSidebarByContact = "";
String aszSidebarMemberType = "";
if(aszSidebarMemberType.length()<1){
	aszSidebarMemberType = aCurrentUserObj.getUSPSiteUseType();
}
if(sidebarPortal.length()>0 && sidebarPortal.equalsIgnoreCase(aCurrentUserObj.getNatlAssociationPortal())) bSidebarManageAssoc=true;
if(!(request.getParameter("orgnid")==null)){
	String aszSidebarNID = (String) request.getParameter("orgnid");
	if(aszSidebarNID.length() > 1){
		iSidebarCurrOrgNID = Integer.parseInt(aszSidebarNID);
	}
	iSidebarTempNID=iSidebarCurrOrgNID;
}else if(aszPortalNID.length()>0){
	try{
		iSidebarCurrOrgNID = Integer.parseInt(aszPortalNID);
	}catch(Exception e){}
	if(bSidebarManageAssoc==false)	iSidebarTempNID=iSidebarCurrOrgNID;
}
if(!(request.getParameter("oppnid")==null)){
	String aszSidebarNID = (String) request.getParameter("oppnid");
	if(aszSidebarNID.length() > 1){
		iSidebarCurrOppNID = Integer.parseInt(aszSidebarNID);
	}
}
out.print("<!-- iSidebarCurrOrgNID is "+ iSidebarCurrOrgNID+" -->");
aszSidebarMemberType="Organizations";
/*
	if(org != null){
		if(org.getORGMembertype().equalsIgnoreCase("Church")) aszSidebarMemberType="Church";
	}
*/

String aszSidebarOrgContainer = "org-container";
if(bHasAssoc==true){
	aszSidebarOrgContainer = "opp-container";
}
int iSidebarOrgNumOpps = 0;

try{
	aHasOppsList = (ArrayList) request.getAttribute("opplist");
	int iListSize = aHasOppsList.size();
	if(iListSize > 0){
		bHasOpps = true;
	}
}catch(NullPointerException ex){}catch(Exception e){}
%>
<logic:iterate id="sidebar_org_nid" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
<% 
	if(iSidebarCurrOrgNID < 1){
		iSidebarCurrOrgNID=sidebar_org_nid.getORGNID();
		iSidebarOrgNumOpps=sidebar_org_nid.getORGNumOppsOrg();
	}else if(iSidebarCurrOrgNID==sidebar_org_nid.getORGNID()){
		iSidebarOrgNumOpps=sidebar_org_nid.getORGNumOppsOrg();
	}
%>
</logic:iterate>
<% 
String aszSidebarOrgLink = aszPortal+"/org.do?method=shownonpeditstep1&reqtype=edit&orgnid="+iSidebarCurrOrgNID;
if(iSidebarCurrOrgNID < 1)	aszSidebarOrgLink = aszPortal+"/org.do?method=showCreateOrgStep1"; 
String aszSidebarContactsLink = "/org.do?method=showOrgAdmins&orgnid="+iSidebarCurrOrgNID;
String aszSidebarOppLink = aszPortal+"/org.do?method=showOrgManageListings&orgnid="+iSidebarCurrOrgNID;
if(iSidebarCurrOrgNID < 1)	aszSidebarOppLink = aszPortal+"/org.do?method=showCreateOrgStep1"; 
else if(iSidebarOrgNumOpps==0)	aszSidebarOppLink = aszPortal+"/org.do?method=showOrgAddOpp1&orgnid="+iSidebarCurrOrgNID; 

boolean bSidebarContactOnly=false;
String aszSidebarContactDisplay="display:block;";
%>

<div class="org-mgmt-widget left-sidebar-org-widget" id="org-mgmt-widget">
<% if(bHasAssoc==true){ %>
    <div class="org-container left" id="assoc_container">
      <div class="left">
	      <h3 class="org-mgmt-link"><a href="/<%=aCurrentUserObj.getNatlAssociationPortal()%>/associationmanagement.jsp">Associations</a></h3>
		  <div style="clear: both;"></div><input id="assoc_dropdown_clicked" value="false" type="hidden" />
		  <select id="associations" name="associations">
<%
String[] a_aszNationalAssocationPortals = aCurrentUserObj.getUSPNatlAssociationPortalsArray();
for(int i=0; i< a_aszNationalAssocationPortals.length; i++){
	String aszNationalAssociationFull = a_aszNationalAssocationPortals[i];
	int iIndexOfSemicolon = aszNationalAssociationFull.indexOf(";");
	String aszNationalAssociationPortal = "";
	String aszNationalAssociationTitle = "";

	if(	aszNationalAssociationFull.length() > 0 && 
		aszNationalAssociationFull.length() > iIndexOfSemicolon+1
	){
		try{
			aszNationalAssociationPortal = aszNationalAssociationFull.substring(0,iIndexOfSemicolon);
			aszNationalAssociationTitle = aszNationalAssociationFull.substring(iIndexOfSemicolon+1);
%>
			<option value="<%=aszNationalAssociationPortal%>" <% if(aszPortal.equals("/"+aszNationalAssociationPortal)){%>selected="selected" <%}%>><%=aszNationalAssociationTitle%></option>
<%
		}catch (Exception e){}
	}
} 
%>
		  </select>
	
	    </div>
	    <div class="arrow_container" id="assoc_arrow"></div>
    </div>
<% } %>
    <div class="<%=aszSidebarOrgContainer%> left" id="org_container">
      <div class="left">
	      <h3 class="org-mgmt-link"><a href="<%=aszSidebarOrgLink%>"><%=aszSidebarMemberType%></a></h3>
	      <div class="multiple">	  
<% if(iSidebarCurrOrgNID>0){ %>
	      <p class="add-buttn sub add"><a href="<%=aszSidebarOrgLink%>">Edit</a></p>
<% } %>
	      <p class="add-buttn sub add" style="margin-bottom:8px;"><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Add</a></p>
	      </div>
		  <div style="clear: both;"></div><input id="org_dropdown_clicked" value="false" type="hidden" />
		<select id="sidebar_organizations" name="organizationlist" style="display: inline; width:180px; ">
			<logic:iterate id="sidebar_org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
		    <%
		    iNumOrgsList++;
	      	aszSidebarSelected="";
	      	if(sidebar_org.getRequestType().equals("ByContact") || sidebar_org.getLoadByMethod() == LOADBY_UID_CONTACT){
	      		aszSidebarByContact="ByContact";
	      		bSidebarContactOnly=true;
	      		aszSidebarContactDisplay="display:none;";
	      	}
	      	if(iSidebarCurrOrgNID < 1){
	      		iSidebarCurrOrgNID=sidebar_org.getORGNID();
	      		aszSidebarContactsLink = "/org.do?method=showOrgAdmins&orgnid="+iSidebarCurrOrgNID;
	      	}
	      	if(sidebar_org.getORGNID()==iSidebarCurrOrgNID){
				aszSidebarSelected="selected=selected";
			}
			if(iSidebarTempCount==0 && iSidebarTempNID<1)	iSidebarTempNID=sidebar_org.getORGNID();
			iSidebarTempCount++;
		    %>
		    <option value="<%=sidebar_org.getORGNID()%>" <%=aszSidebarSelected%> ><%=sidebar_org.getORGOrgName()%></option>
		    </logic:iterate>
		</select>
	    </div>
	    <div class="arrow_container" id="org_arrow"></div>
    </div>
	
    <div class="opp-container left" id="opp_container">
      <div class="left">
	      <h3 class="org-mgmt-link opp-mgmt-link"><a href="<%=aszSidebarOppLink%>">Opportunities</a></h3>
<% if(bSidebarContactOnly == false 
												|| bSidebarContactOnly == true
){ %>
		  <div class="multiple">
			  <p class="add-buttn sub">
			  <% if(aszPortal.length()>0){ %>
				  <a href="<%=aszPortal%>/portalopportunities.jsp" >Import</a>
			  <% }else{ %>
				  <a href="http://www.urbanministry.org/import-opps" >Import</a>
			  <% } %>
			  </p>
			  <p class="add-buttn sub add">
				  <a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iSidebarCurrOrgNID%>&requesttype=<%=aszSidebarByContact%>" >Add</a>
			  </p>
		  </div>
<% } %>
		  <div style="clear: both;"></div>
		  <div class="opp-mgmt-link"><input id="opp_dropdown_clicked" value="false" type="hidden" />
			<select id="sidebar_opportunities" name="opportunities" class"smalldropdown" style="width:180px;" >
				<option value="0" >All Opportunities</option> 
				<logic:iterate id="sidebar_opp" name="opplist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
					<% if(sidebar_opp.getORGNID()==iSidebarCurrOrgNID){ %>
						<option value="<%=sidebar_opp.getOPPNID()%>"
						<% // if in the URL, select that one by default
							if(sidebar_opp.getOPPNID()==iSidebarCurrOppNID && iSidebarCurrOppNID>0){
								aszSidebarContactsLink = "/org.do?method=showOppOrgContacts&orgnid="+iSidebarCurrOrgNID+"&oppnid="+iSidebarCurrOppNID;
						%>
							selected="selected"
						<% } %> ><%=sidebar_opp.getOPPTitle()%></option>
					<% } %>
				</logic:iterate>
			</select>
		</div>
	</div>
    <div class="arrow_container" id="opp_arrow"></div>
    </div>

    <div class="contacts-container  left" id="contacts_container" style=<%=aszSidebarContactDisplay%>>
      <div class="left">
      <h3 class="org-mgmt-link" id="contacts-link">
      	<a href="<%=aszPortal%><%=aszSidebarContactsLink%>">Contacts</a>
      </h3>
    </div>
    <div class="arrow_container" id="contacts_arrow"></div>
    </div>
  
    <div class="share-container  left" id="share_container">
      <div class="left">
      <h3 class="org-mgmt-link padded">
<% if(sidebarPortal.length()>0){ %>
      	<a href="<%=aszPortal%>/org.do?method=showPortalManage&orgnid=<%=iSidebarCurrOrgNID%>">Share on Website</a>
<% }else{ %>
      	<a href="<%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_listurl">Share on Website</a>
<% } %>
      </h3>
	  <div style="clear: both;"></div><input id="embed_dropdown_clicked" value="false" type="hidden" />
      <select id="sidebar_embed" name="sidebar_embed" class"smalldropdown" style="width:180px;" > 
      	<option value="embed_listurl" <% if(aszPortal.length()==0){ %> selected="selected"<% } %> >Link to URL of Your Listings</option> 
		<option value="embed_list">Embed All Your Listings</option> 
		<option value="embed_preview">Embed a Preview of Your Listings</option> 
		<option value="embed_searchbox">Embed a Search Form on Your Site</option> 
		<option value="embed_directory" <% if(sidebarPortal.length()>0){ %> selected="selected"<% } %> >Create Hosted Volunteering Website</option> 
	</select>
      
    </div>
    <div class="arrow_container" id="share_arrow"></div>
    </div>

</div>
<!-- end sidebar.inc -->

<script language="javascript">
function sidebar_showorg(inid,newlist){
	var oppElem = document.getElementById('sidebar_opportunitieslist'+inid); 
	var oppIndex = oppElem.selectedIndex;
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
<% if(bSidebarManageAssoc==false){ %>
	var portal='<%=sidebarPortal%>/';
<% }else{ %>
	var portal='';
<% } %>
}

<% if(bHasAssoc==true){ %>
 $("#associations").click(function(){
 	$("#assoc_dropdown_clicked").val("true");
		$("#associations").change(function(){
		    portal=$("#associations").val();
			var url = '';
			if(portal.length==0){
				url = 'http://<%=aszSubdomain%>/<%=aCurrentUserObj.getNatlAssociationPortal()%>/associationmanagement.jsp';
			}else{
				url = 'http://<%=aszSubdomain%>/' + portal + '/associationmanagement.jsp';
			}
			window.location=url;; 
	 	});
 });
 $("#assoc_container").click(function(){
 	var dropdown_clicked = $("#assoc_dropdown_clicked").val();
	if(dropdown_clicked == "false"){
	    portal=$("#associations").val();
		var url = '';
		if(portal.length==0){
			url = 'http://<%=aszSubdomain%>/<%=aCurrentUserObj.getNatlAssociationPortal()%>/associationmanagement.jsp';
		}else{
			url = 'http://<%=aszSubdomain%>/' + portal + '/associationmanagement.jsp';
		}
		window.location=url;; 
	}
 });
 $("#assoc_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#assoc_container").mouseout(function(){
 	document.body.style.cursor='default';
 });
<% } %>
<% if(iNumOrgsList < 2){ // if there is only one org, make clicking on the org open the edit page %>
 $("#sidebar_organizations").click(function(){
        inid=$(this).val();
		var url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid='+inid;
		window.location=url;; 
 });
 $("#org_container").click(function(){
		var url = 'http://<%=aszSubdomain%><%=aszSidebarOrgLink%>';
		window.location=url;; 
 });
 $("#org_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#org_container").mouseout(function(){
 	document.body.style.cursor='default';
 });
<% }else{ %>
 $("#sidebar_organizations").click(function(){
 	$("#org_dropdown_clicked").val("true");
		$("#sidebar_organizations").change(function(){
	        inid=$(this).val();
			var url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid='+inid;
			window.location=url;; 
	 	});
 });
 $("#org_container").click(function(){
 	var dropdown_clicked = $("#org_dropdown_clicked").val();
		if(dropdown_clicked == "false"){
	        inid=$("#sidebar_organizations").val();
			var url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=shownonpeditstep1&reqtype=edit&orgnid='+inid;
			window.location=url;; 
	 	}
 	
 });
 $("#org_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#org_container").mouseout(function(){
 	document.body.style.cursor='default';
 });
<% } %>

 $("#sidebar_opportunities").click(function(){
 	$("#opp_dropdown_clicked").val("true");
		$("#sidebar_opportunities").change(function(){
			var url = '';
	        inid=$(this).val();
			if(inid==0){
				url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgManageListings&orgnid=<%=iSidebarCurrOrgNID%>';
			}else{
				url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=iSidebarCurrOrgNID%>&oppnid='+inid+'&requesttype=edit';
			}
			window.location=url;; 
	 	});
 });

 $("#opp_container").click(function(){
 	var dropdown_clicked = $("#opp_dropdown_clicked").val();
	if(dropdown_clicked == "false"){
	    inid=$("#sidebar_opportunities").val();
		var url = '';
		if(inid==0){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgManageListings&orgnid=<%=iSidebarCurrOrgNID%>';
		}else{
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOpportunityEdit&orgnid=<%=iSidebarCurrOrgNID%>&oppnid='+inid+'&requesttype=edit';
		}
		window.location=url;; 
	}
 });
 $("#opp_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#opp_container").mouseout(function(){
 	document.body.style.cursor='default';
 });
 $("#contacts_container").click(function(){
		var url = 'http://<%=aszSubdomain%><%=aszPortal%><%=aszSidebarContactsLink%>';
		window.location=url;; 
 });

 $("#contacts_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#contacts_container").mouseout(function(){
 	document.body.style.cursor='default';
 });

 $("#sidebar_embed").click(function(){
 	$("#embed_dropdown_clicked").val("true");
	var url = '';
    var embed=$(this).val();
	$("#sidebar_embed").change(function(){
		embed=$(this).val();
		if(embed=='embed_listurl'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_listurl';
		}else if(embed=='embed_list'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_list';
		}else if(embed=='embed_preview'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_preview';
		}else if(embed=='embed_searchbox'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_searchbox';
		}else if(embed=='embed_directory'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showPortalManage&orgnid=<%=iSidebarCurrOrgNID%>';
		}else{
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>';
		}
		window.location=url;
	});
	setTimeout(
		function() {
		var hash_value_sidebar = window.location.hash;
		if(hash_value_sidebar.length > 1){
			hash_value_sidebar = hash_value_sidebar.substring(1);
			if(embed != hash_value_sidebar){
				if(embed=='embed_listurl'){
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_listurl';
				}else if(embed=='embed_list'){
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_list';
				}else if(embed=='embed_preview'){
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_preview';
				}else if(embed=='embed_searchbox'){
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_searchbox';
				}else if(embed=='embed_directory'){
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showPortalManage&orgnid=<%=iSidebarCurrOrgNID%>';
				}else{
					url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>';
				}
				window.location=url;
			}
		}		
	}, 3000);
 });
 $("#share_container").click(function(){
 	var dropdown_clicked = $("#embed_dropdown_clicked").val();
	var url = '';
	var embed=$("#sidebar_embed").val();
	if(dropdown_clicked == "false"){
		if(embed=='embed_listurl'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_listurl';
		}else if(embed=='embed_list'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_list';
		}else if(embed=='embed_preview'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_preview';
		}else if(embed=='embed_searchbox'){
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>#embed_searchbox';
		}else if(embed=='embed_directory'){
				url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showPortalManage';
		}else{
			url = 'http://<%=aszSubdomain%><%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iSidebarCurrOrgNID%>';
		}
		window.location=url;; 
	}
 });
 $("#share_container").mouseover(function(){
 	document.body.style.cursor='pointer';
 	document.body.style.cursor='hand';
 });
 $("#share_container").mouseout(function(){
 	document.body.style.cursor='default';
 });

	
	function sidebar_showopp(inid, newlist) {
		var oppElem = document.getElementById('contacts-container');
		$('#contacts-container').html('<h3 class="org-mgmt-link"><a href="<%=aszPortal%>/org.do?method=showOppOrgContacts&orgnid=<%=iSidebarCurrOrgNID%>&oppnid='+nid+'">Contacts</a></h3>'); 
	}
	
	function sidebar_showassoc(id){
		var url = 'http://<%=aszSubdomain%>/'+id+'/associationmanagement.jsp';
		window.location=url;; 
	}

</script>

