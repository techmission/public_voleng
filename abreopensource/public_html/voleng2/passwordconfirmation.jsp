<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<html:form action="/register.do"  >
<html:hidden property="method" value="showPasswordConfirm" />


<div id="pagebanner">
<span style="float: left;">Password Sent</span><img  style="float:right" /int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">Account Summary</a> </div>
</div>

	<div id="maincontent">

	
		<div id="body">
			<div align="left">
				<br><br><br>
<h3>Check your email at the address you have provided.</h3><br><br><br>
<b>Click <a href="<%=aszPortal%>/register.do?method=showlogin">here</a> to login, once you have received your password.</b><br><br>


<br><br><br>
<br><br><br>


If you are still encountering difficulties, please email us at <a href="mailto:<%=aszEmailHost%>"><%=aszEmailHost%></a>

			</div>
		</div>

		<P>
			<BR>
		</P>
		
	</div>
	
</html:form>
  
</div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
