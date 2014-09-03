<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->

<html:form action="/email.do" focus="fromuser" >
<html:hidden property="method" value="processSendFeedback" />
<html:hidden property="touser" value="<%=aszEmailHost%>" />

<div id="maincontent">
<div id="pagebanner">

<span style="float: left;">Feedback for <%=aszSubdomain%> </span><img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/index.jsp">home</a> &gt; feedback  </div>
</div>

 <div id="body">
        <img src="/imgs/blank.gif" width="415" height="10"><BR>

      	<div align="left">

      <TABLE cellSpacing=0 cellPadding=2 align=center width="537" border=0 height="213">
		<tr>
        <TD align="left" colspan=3 height="22">
          <BR>&nbsp;<BR>
        </TD>
      </tr>
		<tr>
        <TD class=formLabel align=right >
          <b>Your Email </b>
        </TD><td width="2"></td>
        <TD align=left>
          <html:text property="fromuser" size="25" maxlength="50" styleClass="textinputwhite" />
		</TD>
        <TD height="25">&nbsp;</TD>
        </tr>
		
		<tr><TD height="25">&nbsp;</TD>
        </tr>
        
		<tr>
        <TD class=formLabel align=right  valign="top">
          <b>Message </b>
        </TD><td width="2"></td>
        <TD align=left>
            <html:textarea property="emailbodytxt" rows="15" cols="70" styleClass="textinputwhite"/>
		</TD>
        <TD height="25">&nbsp;</TD>
        </tr>
		
		<tr><TD height="25">&nbsp;</TD>
        </tr>
		<tr>
			<td>&nbsp;</td><td></td>
        <TD><INPUT class=submit type=submit value="Send My Feedback"> </TD>
			<td height="31">&nbsp;</td>
		</tr>
		<tr>
          <TD align="center" colspan=3 height="19"><FONT color="red">

          <bean:write name="emailForm" property="errormsg" />
          </FONT></TD>
      </tr>
		<tr>
			<td width="176"></td>
			<td width="191"></td>
			<td height="18" width="158"></td>
		</tr>
      </TABLE>

</div>

</html:form>
</div>


    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
