<% if( !( aszPortalTemplate.equals("main") || aszPortal.contains("meettheneed") )   ){ %>
<div id="chrisvol_nav">
	<a href="<%=aszPortal%>/register.do?method=showHome"><span>ChristianVolunteering Home</span></a>&nbsp;&nbsp;|&nbsp;	
<% if(  false == LoginBean.IsSessionLoggedIn( request ) ){ %>
	<a href="<%=aszPortal%>/login.jsp" ><span>Login</span></a>&nbsp;&nbsp;|&nbsp;	
	<a href="<%=aszPortal%>/individualregistration.jsp"><span>Create Account</span></a>&nbsp;&nbsp;|&nbsp;	
<% } %>
	<a href="<%=aszPortal%>/advancedsearch.jsp"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
	<a href="<%=aszPortal%>/volunteerlistings.jsp"><span>All Opportunities</span></a>&nbsp;&nbsp;|&nbsp;	
	<a href="<%=aszPortal%>/organizationlistings.jsp"><span>All <%=aszOrgOrChurchPlural%></span></a>	

<% if(  true == LoginBean.IsSessionLoggedIn( request ) ){ %>
	<% if( 
			LoginBean.getLoggedInUserType( request ).equals("Organization") ||
			LoginBean.getLoggedInUserType( request ).equals("Church") 
	){ 
		if(bManageAssoc==true || aszOrgOrChurch.equalsIgnoreCase("Association")){
	%>
	        &nbsp;|&nbsp;&nbsp;<a href="<%=aszPortal%>/associationmanagement.jsp"><span>Manage Association</span></a>
	<% 	}else{ %>
	        &nbsp;|&nbsp;&nbsp;<a href="<%=aszPortal%>/orgmanagement.jsp"><span>Manage <%=aszOrgOrChurch%></span></a>
	<% }}else{ %>
        &nbsp;|&nbsp;&nbsp;<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1"><span>My Account</span></a>
	<% } %>
	&nbsp;|&nbsp;&nbsp;<a href="<%=aszPortal%>/register.do?method=logout"><span>Logout</span></a>
<% } %>
</div>
<!--br-->
<% } %>
