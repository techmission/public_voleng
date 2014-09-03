<!-- use myaccountsum1.jsp now -->

















<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<html:form action="/register.do"  >
<html:hidden property="method" value="processLogin" />

<!-- BEGIN MAINCONTENT -->

<div id="maincontent">
<div id="pagebanner">

<span style="float: left;">Organization Management</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/">home</a> &gt; 
	<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">account 
	summary</a> 
	&gt; organizational account management  </div>
</div>

 <div id="body">
        <h2>
        <img src="<%=request.getContextPath()%>/techmimg/blank.gif" width="415" height="5"><BR>
		Manage Your Organization
      	</h2>
      <HR width="100%">
      	<div align="left">


      <logic:notEmpty name="orglist">

     
		<h3>Select Organization to Manage</h3><br>
      <TABLE cellSpacing=2 cellPadding=2 align=center width="90%" border=0>
      <TBODY>
      <TR>
        <td><img src="<%=request.getContextPath()%>/techmimg/blank.gif" width="10" height="1"></td>
        <td><img src="<%=request.getContextPath()%>/techmimg/blank.gif" width="10" height="1"></td>
        <td><img src="<%=request.getContextPath()%>/techmimg/blank.gif" width="180" height="1"></td>
        <td><img src="<%=request.getContextPath()%>/techmimg/blank.gif" width="100" height="1"></td>
      </TR>

    <logic:iterate id="org" name="orglist" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO">
      <TR>
        <TD colspan=1>&nbsp; </td>
        <TD colspan=3 align="left">
          <A href="<%=request.getContextPath()%>/org.do?method=showOrgSumm1&orgnumber=<%=org.getORGOrgNumber()%>"><%=org.getORGOrgName()%></A>
        </td>
      </tr>
      </logic:iterate>

      <TR><TD colspan=4>&nbsp; </td></tr>
      </TBODY>
      </TABLE>
      </logic:notEmpty>

		<a href="/org.do?method=showCreateOrgStep1">Add Organization</a>

</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>

    </td>
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->