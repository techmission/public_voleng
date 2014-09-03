<style>
</style>
<script TYPE="text/javascript">
$("#post-menu").click(function() {
  alert("Handler for .click() called.");
  $("#post-content-menu").show();
});
function click_post(){
	$("#post-content-menu").show();
}
function hide_post(){
	if( $('#post-content-menu').is(':hidden') ) {
	    $("#post-content-menu").show();
	}
	else {
	    $("#post-content-menu").hide();
	}  
}
</script>
<% if( true == LoginBean.IsSessionLoggedIn( request ) ){ %>
<% 
int iCurrentUserOrgNID = aCurrentUserObjLoggedIn.getORGNID();
out.print("<!-- org nid is " + iCurrentUserOrgNID + "-->");
int[] a_iCurrentUserOrgNIDs = aCurrentUserObjLoggedIn.getOrgNIDsArray();
for(int i=0; i<a_iCurrentUserOrgNIDs.length; i++){
out.print("<!-- org nid is " + a_iCurrentUserOrgNIDs[i] + "-->");
}
%>
<div id="search" class="home">
	<div id="loggedInBlock" class="loggedInBlock">
		<div class="content-menu-outer">
			<% if(aszRemoteHost.contains(":7001")){ %>
			<a href="http://chrisvol.org:3000/voleng/profiles/mine/edit"><%=aCurrentUserObjLoggedIn.getUSPUsername()%></a>
			<% }else{ %>
			<a href="/profiles/mine/edit"><%=aCurrentUserObjLoggedIn.getUSPUsername()%></a>
			<% } %>
			&nbsp;|&nbsp; 
		</div>
		<div class="content-menu-outer">
			<div id="post-menu" class="content-menu-outer"><a class="top-link" href="#" onclick="click_post()">post</a></div>
			<div id="post-content-menu" onmouseout="hide_post()">
				<ul class="login_menu">
					<li>
						<a title="" href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Organization</a>
					</li>
<% if(iCurrentUserOrgNID > 0){ %>					
					<li>
						<a title="" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iCurrentUserOrgNID%>&opppositiontypetid=">Volunteer Opportunity</a>
					</li>
					<li>
						<a title="" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iCurrentUserOrgNID%>&opppositiontypetid=4796">Internship</a>
					</li>
					<li>
						<a title="" href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=iCurrentUserOrgNID%>&opppositiontypetid=33389">Job</a>
					</li>
<% } %>					
					<li>
			<a href="/profiles/mine/edit_resume">Resum&eacute;</a>
					</li>
				</ul>
			</div>
			&nbsp;|&nbsp; 
		</div>
		<div class="content-menu-outer">
			<a href="<%=aszPortal%>/register.do?method=logout">logout</a>
		</div>
	</div>
</div>




<% }else if (
	aszHostID.equalsIgnoreCase("volengcatholic")	&&
		(!(
			aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account2.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account3.jsp") ||
	        aszPath.equalsIgnoreCase("/voleng2/socialize_create_account2.jsp")        ||
	        aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp")        ||
//			aszLoggedIn.equalsIgnoreCase("false") ||
			aszPath.equalsIgnoreCase("/socialize.jsp") ||
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
			aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account1.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/facebook_connect_createaccount.jsp") ||
	        aszPath.equalsIgnoreCase("/socialize-login.jsp")      
		))	
){	
%>
<%//@ include file="/template_include/login_form_non-gigya.inc" %>

<!-- non-fb login version -->
<div id="search" class="home">
<form name="individualForm" method="post" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processLogin<%}%>">

<input type="hidden" name="method" value="processLogin">
  <table border="0" cellpadding="0" cellspacing="0" style="font-size: 11px;">
    <tr>
      <td>
     <input type="text" style="font-size: 11px; width:93px;" onblur="if(this.value==''){this.value='email';}" onfocus="if(this.value=='email'){this.value='';}" value="email" id="email1addr" name="email1addr"/></td>
      <td>&nbsp;&nbsp;<input onblur="if(this.value==''){this.value='password';}" onfocus="if(this.value=='password'){this.value='';}" style="font-size: 11px; width:93px;" type="password" name="password1" maxlength="50" />
        &nbsp;&nbsp;<input name="submit" type="submit" id="submit" value="Log In"> </td>
      </tr>
	  <tr>

		  <td><a href="<%=aszPortal%>/individualregistration.jsp" style="text-decoration: none; font-size: 11px;">create account</a>  </td>
		  <td><a href="http://www.urbanministry.org/user/password" style="text-decoration: none; font-size: 11px;">forgot your password?</a></td>
	  </tr>

  </table>

</form>
</div>
<% }else{ %>

<div id="search" class="home">
<% if(	false == LoginBean.IsSessionLoggedIn( request )	&&
		(!(
			aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account2.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account3.jsp") ||
	        aszPath.equalsIgnoreCase("/voleng2/socialize_create_account2.jsp")        ||
	        aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp")        ||
//			aszLoggedIn.equalsIgnoreCase("false") ||
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
			aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/create_account1.jsp") ||
			aszPath.equalsIgnoreCase("/socialize.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/facebook_connect_createaccount.jsp") ||
	        aszPath.equalsIgnoreCase("/socialize-login.jsp")      
		))	
){ %>

<form name="individualForm" method="post" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processLogin<%}%>">
	<input type="hidden" name="method" value="processLogin">
	<table border="0" cellpadding="0" cellspacing="0" style="font-size: 11px;">
		<tr>
		  <td>
		 <input type="text" style="font-size: 11px; width:93px;" onblur="if(this.value==''){this.value='email';}" onfocus="if(this.value=='email'){this.value='';}" value="email" id="email1addr" name="email1addr"/></td>
		  <td>&nbsp;&nbsp;<input style="font-size: 11px; width:93px;" type="password" name="password1" maxlength="50" />
			&nbsp;&nbsp;<input name="submit" type="submit" id="submit" value="Log In"> </td>
		  </tr>
		  <tr align="right">
			<td colspan="2">
				<table border=0 margin=0>
					<tr>
						<td colspan="2">
                          <a style="position: relative; left:-3px;" href="<%=request.getContextPath()%>/register.do?method=authorizeLinkedinConnectClick"><img height="20px" src="http://www.christianvolunteering.org/imgs/linkedin_login_button_short.png"/></a>
                          <a style="position: relative; left:-3px;" href="<%=request.getContextPath()%>/register.do?method=authorizeFacebookConnectClick"><img height="20px" src="http://www.christianvolunteering.org/imgs/facebook_login_button_short.png"/></a>
						</td>
						<td><a style="font-size:11px; position: relative; left:-3px; text-decoration:none; text-align:left; margin-right:0px;"  href="<%=aszPortal%>/individualregistration.jsp">create<br>account</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<% } %>
</div>

<% } %>
