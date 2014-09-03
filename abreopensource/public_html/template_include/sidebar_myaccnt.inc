 <h5><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">MY ACCOUNT</a></h5>
  <ul class="navbullet">
<% if(true == LoginBean.IsSessionLoggedIn( request )  ){ %>
	<li><a href="<%=aszPortal%>/register.do?method=logout">Logout</a></li>
<% }else{ %>
	<li><a href="<%=aszPortal%>/individualregistration.jsp">Create Account</a></li>
	<li><a href="<%=aszPortal%>/login.jsp">Login</a></li>
<% } %>
	<li><a href="<%=aszPortal%>/volunteerdashboard.jsp">Account Home</a></li>
    <li><a href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Personal Account</a></li>
  	<li><a href="<%=aszPortal%>/orgmanagement.jsp">Manage Organization</a></li>
	<li><a href="http://www.urbanministry.org/user/password">Reset Password</a></li>
	</ul>
