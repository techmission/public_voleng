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

<html:form action="/email.do"  >
<html:hidden property="method" value="showFeedbackEmailConfirm" />


<div id="pagebanner">
<span style="float: left;">Email Sent</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> </div>
</div>

	<div id="maincontent">

	
		<div id="body">
			<div align="left">
				<br><br><br>
Your has been sent to .<br><br><br>
If you are still encountering difficulties, please email us at 


				


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