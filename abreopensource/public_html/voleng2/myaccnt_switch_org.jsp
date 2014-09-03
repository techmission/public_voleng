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

<span style="float: left;">Edit Personal Profile</span>
<% if(! (aszPartnerTemplate.equalsIgnoreCase("true")) ){ %>
<img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<% } %>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/volunteerdashboard.jsp">account summary</a> &gt; 
	edit personal profile  </div>
</div>

<logic:notEmpty name="userprofile">
</logic:notEmpty>
<logic:empty name="userprofile">
</logic:empty>

 <div id="body">
        
        <BR>



<center>
<br><br>
<br><br>
<h3>Are You Sure You Want to Switch to an Organization Profile?</h3>
<br><br>
<br><br>


<table border = 0><tr><td>
<html:form action="/register.do"  >
<html:hidden property="method" value="processIndProfEdit1" />

<html:hidden property="siteusetype" value="Both" />
<html:hidden property="orgcreation" value="Yes" />
<html:hidden property="uid" value="<%=aszUID%>" />

<INPUT name=submit class=submit type=submit value="Yes"> 
</html:form>
</td><td>
<form action="<%=aszPortal%>/volunteerdashboard.jsp"> 
<INPUT class=submit type=submit value="Cancel"> 
</form>
</td></tr></table>
</center>

</div>

    </div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- Google Code for ChristianVolunteering.org Registered Organization Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "y02aCMTdiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=y02aCMTdiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
