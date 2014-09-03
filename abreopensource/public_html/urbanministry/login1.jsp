<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="template/um_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/empty_navigation.inc" %>
<!-- end navigation information -->


<!-- BEGIN MAINCONTENT -->

<html:form action="/register.do" focus="username" >
<html:hidden property="method" value="processLogin" />

<div id="maincontent">
<div id="pagebanner">

<span style="float: left;">Log In </span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/register.do?method=showHome">home</a> &gt; log in  </div>
</div>

 <div id="body">
        <img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="415" height="10"><BR>

      	<div align="left">

      <TABLE cellSpacing=0 cellPadding=2 align=center width="537" border=0 height="213">
		<tr>
        <TD align="left" colspan=3 height="42">
          <b>If you do not yet have an account you can 
			<a href="<%=request.getContextPath()%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;</b>
          If you have already registered with ChristianVolunteering, enter your email and password below.
          <BR>&nbsp;<BR>
        </TD>
      </tr>
		<tr>
        <TD class=formLabel align=right>
          <p align="left"><b>Email</b>
        </TD>
        <TD align=left>
          <html:text property="username" size="25" maxlength="50" styleClass="textinputwhite" />
		</TD>
        <TD height="25">&nbsp;</TD>
        </tr>
		<tr>
        <TD class=formLabel align=right>
          <p align="left">Password 
        </TD>
        <TD align=left>
          <html:password property="password1" size="25" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
		</TD>
      		<td height="27">&nbsp;</td>
		</tr>        	
		<tr>
			<td>&nbsp;</td>
        <TD><INPUT class=submit type=submit value="Log in"> </TD>
			<td height="31">&nbsp;</td>
		</tr>
		<tr>
          <TD align="center" colspan=3 height="19"><FONT color="red"><bean:write name="individualForm" property="errormsg" /></FONT></TD>
      </tr>
		<tr>
        <TD colspan=3 height="29">
                <b>NOTE:</b> If you have an account with <a href="http://www.urbanministry.org">UrbanMinistry.org</a>, log in with the email and password you have provided on <a href="http://www.urbanministry.org">UrbanMinistry.org</a>.
		</TD></tr>
        <tr><td height="30"></td></tr>
        <tr><td height="30" colspan=2>
	        Forgot your <a href= "http://www.urbanministry.org/user/password">password</A>? 
			Other <a href="mailto:info@christianvolunteering.org">login problems</A>?
	        Email <a href="mailto:info@christianvolunteering.org">ChristianVolunteering.org</a> 
		</td></tr></table>

</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>

    
    </div>

<!-- start sidebar information -->
<%@ include file="template/um_sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="template/um_footer.inc" %><!-- end footer information -->