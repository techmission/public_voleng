<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>


<%
String aszUID="" + userprofile.getUserUID();
%>


<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<div id="pagebanner">

<span style="float: left;">Manage Social Networking Links</span>
<% if(! (aszPartnerTemplate.equalsIgnoreCase("true")) ){ %>
<img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<% } %>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/volunteerdashboard.jsp">account summary</a> &gt; 
	unlink social networking  </div>
</div>

<logic:notEmpty name="userprofile">
</logic:notEmpty>
<logic:empty name="userprofile">
</logic:empty>

 <div id="body">
        
        <BR>

<br><a href="<%=aszPortal%>/volunteerdashboard.jsp"><< Go Back</a>
<center>
<br><br>
<div id="gigyaIntro"></div>
<br />
<div id="gigyaEditConnectionsUI" name="gigyaEditConnectionsUI">
</div>

</center>

</div>

    </div>

<%@ include file="/template_include/gigya_socialize.inc" %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
