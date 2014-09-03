<%
	if(true == LoginBean.IsSessionLoggedIn( request ) && (!(
		aszPath.equalsIgnoreCase("/voleng2/login1.jsp")	 ||
		aszPath.equalsIgnoreCase("/voleng2/volcreateaccount1.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/create_account1.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/create_account2.jsp")	||
                aszPath.equalsIgnoreCase("/voleng2/socialize_create_account2.jsp")        ||
                aszPath.equalsIgnoreCase("/socialize-login.jsp")        ||
                aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp")        ||
                aszPath.equalsIgnoreCase("/voleng2/create_account3.jsp")        ||
                aszPath.equalsIgnoreCase("/voleng2/create_account2.jsp")        ||
                aszPath.equalsIgnoreCase("/voleng2/create_account2.jsp")        ||
		aszPath.equalsIgnoreCase("/createaccount.jsp")				||
		aszHostID.equalsIgnoreCase("voleng1")						||
		aszHostID.equalsIgnoreCase("volengyounglife")				||
		aszHostID.equalsIgnoreCase("volengdefault")	
)	)){ 
%>
<script type="text/javascript">

function clearField(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

</script>

<h5><a href="<%=aszPortal%>/individualregistration.jsp">Create Account</a> </h5>
<form name="searchForm3" method="post" action="<%=aszPortal%>/register.do">
<input type="hidden" name="method" value="showCreateAccount1">
<input style="width:85px;" type="text" value="email address" name="email1addr" onFocus="clearField(this)" />

 <input name="submit" type="submit" id="submit" value="GO"/> 

</form>
<% } %>

