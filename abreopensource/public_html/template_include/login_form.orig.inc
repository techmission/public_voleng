
<div id="search" class="home">
<% if(!(
	aszPath.equalsIgnoreCase("/socialize-login.jsp") ||
	aszPath.equalsIgnoreCase("/createaccount.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/login1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/logintest.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test2.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test3.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test4.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test5.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test6.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_my_results.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp") ||
	aszLoggedIn.equalsIgnoreCase("true") 
)){ %>
<form name="individualForm" method="post" action="<%=request.getContextPath()%>/register.do">

<input type="hidden" name="method" value="processLogin">

  <table border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">

    <tr>

      <td>

     <input type="text" style="font-size: 11px;" onblur="if(this.value==''){this.value='e-mail';}" onfocus="if(this.value=='e-mail'){this.value='';}" value="e-mail" id="email1addr" name="email1addr"/></td>

      <td><input style="width:120px;" type="password" value="" name="password1" maxlength="50" />

        <input name="submit" type="submit" id="submit" value="Log In"> </td>

      </tr>
	  <tr>
	  <td><a href="<%=request.getContextPath()%>/individualregistration.jsp" style="text-decoration: none; font-size: 11px;">create account</a>  </td>
	  <td><a href="http://www.urbanministry.org/user/password" style="text-decoration: none; font-size: 11px;">forgot your password?</a>
</td>
	  </tr>

  </table>

</form>
<% } %>
</div>

<% if ( (
	aszPath.equalsIgnoreCase("/gigya.jsp") || 
	aszPath.equalsIgnoreCase("/voleng2/test-anon.jsp") || 
	//aszPath.equalsIgnoreCase("/voleng2/logintest.jsp") || 
	aszPath.equalsIgnoreCase("/socialize-login.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/createaccount.jsp") || 
	aszPath.equalsIgnoreCase("/createaccount.jsp") 
) ) { %>
<%@ include file="/template_include/gigya_loginForm.inc" %>
<% } %>