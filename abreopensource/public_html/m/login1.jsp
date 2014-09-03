<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>ChristianVolunteering.org: Login to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers</title>

<% } else { %>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<% 
session.putValue ("MyIdentifier1","");  // Initialize Value into session Object
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Log In </span>
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

 <div id="body">
      <br/> 
 <h2 style="margin-bottom: 0px;">Login or Register</h2>
         <hr color="#CCCCCC" size="1" style="margin-top: 3px;">
    
	
<form method="post" name="individualForm" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processLogin<%}%>" >
<input type="hidden" name="method" value="processLogin" />
	
    <div style="float:left;">
		<p align="right"><b>Email</b>
			<input type="text" name="email1addr" size="15" maxlength="50" styleClass="textinputwhite" />
		</p>
		<p align="right">Password
			<input type="password" name="password1" size="15" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
		</p>
     </div> 
      
     <div style="clear:both"></div>   


<% if(
//	aszHostID.equalsIgnoreCase("volengivol") ||
	aszHostID.equalsIgnoreCase("volengchurch") 
){ %>
<INPUT class=submit type=submit value="Log in" style="margin-top:0px;">
<br /><br />
<% }else{ %>
    	<p style="position:absolute; padding:15px 0px 10px 3px; margin:0;">
      <INPUT class=submit type=submit value="Log in" style="float:left; margin-top:0px;">
<%//@ include file="/template_include/gigya_loginForm_loginPage.inc" %>
<% } %>
</form>	
<html:form action="/register.do" >
        <FONT color="red"><center><bean:write name="individualForm" property="errormsg" /></center></FONT>
</html:form>
	
<br><br>
<br/><b>NOTE:</b> 
If you do not yet have an account you can 
			<a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;<br>
			If you have an account with 
<a href="http://www.urbanministry.org">UrbanMinistry.org</a>
, please log in with the email and password you have provided on that account.		
	
	        Forgot your 
<a href= "http://www.urbanministry.org/user/password">password</A>? 
			Other <a href="mailto:<%=aszEmailHost%>">login problems</A>?
	        Email <a href="mailto:<%=aszEmailHost%>"><%=aszEmailHost%></a> 
<br>	
    

    </div>
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
