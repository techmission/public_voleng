<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
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


<!-- BEGIN MAINCONTENT -->
<% 
session.putValue ("MyIdentifier1","");  // Initialize Value into session Object
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Log In</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>


<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Log In </span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; log in  </div>
</div>
<% } %>

 <div id="body">
        <img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="415" height="10"><BR>

      	<div align="left">
<form method="post" name="individualForm" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processLogin<%}%>" >
<input type="hidden" name="method" value="processLogin" />
      <TABLE cellSpacing=0 cellPadding=2 align=center width="537" border=0 height="213">
		<tr>
        <TD align="left" colspan=3 height="42">
          <b>If you do not yet have an account you can 
			<a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;</b>
          If you have already registered with <%=aszSubdomain%>, enter your email and password below.
          <BR>&nbsp;<BR>
        </TD>
      </tr>
		<tr>
        <TD class=formLabel align=right>
          <b>Email</b>
        </TD>
        <TD align=left>
    <input type="text" name="email1addr" size="25" maxlength="50" styleClass="textinputwhite" />
		</TD>
        <TD height="25">&nbsp;</TD>
        </tr>
		<tr>
        <TD class=formLabel align=right>
          Password 
        </TD>
        <TD align=left>
	<input type="password" name="password1" size="25" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
		</TD>
      		<td height="27">&nbsp;</td>
		</tr>        	
		<tr>
			<td>&nbsp;</td>
        <TD><INPUT class=submit type=submit value="Log in"> </TD>
			<td height="31">&nbsp;</td>
		</tr>
        <tr><td height="10"></td></tr>
		<tr>
        <TD colspan=3 height="29">
                <b>NOTE:</b> If you have an account with 
<a href="http://www.christianvolunteering.org">ChristianVolunteering.org</a>
<% if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
, <a href="http://www.ccda.org">CCDA.org</a>
<% } %>
 or <a href="http://www.urbanministry.org">UrbanMinistry.org</a>
				, please log in with the email and password you have provided on that account.
		</TD></tr>
        <tr><td height="10"></td></tr>
        <tr><td height="30" colspan=2>
	        Need <a href="http://www.urbanministry.org/help">help</a>?  &nbsp;
	        Forgot your 
<a href= "http://www.urbanministry.org/user/password">password</A>? 
<!--			Other <a href="mailto:<%=aszEmailHost%>">login problems</A>?
	        Email <a href="mailto:<%=aszEmailHost%>"><%=aszSubdomain%></a>  -->
		</td></tr></table>
</form>

<html:form action="/register.do" >
        <FONT color="red"><center><bean:write name="individualForm" property="errormsg" /></center></FONT>
</html:form>


</div>


      <P>
      <BR>&nbsp;</P></div>

    
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
