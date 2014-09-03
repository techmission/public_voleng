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



<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Socialize Account Information</span>
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

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Socialize Account Information</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; socialize account information</div>
</div>
<% } %>

<logic:notEmpty name="userprofile">
</logic:notEmpty>
<logic:empty name="userprofile">
</logic:empty>

 <div id="body">
        
        <BR>



<center>
<br><br>
<br><br>
<h3>The Alerts on this Page Contain Information on Your Socialize Account</h3>
<br><br>
<br><br>


<div id="gigyainfo" name="gigyainfo">
</div>
</center>
<br />
<div id="gigyaIntro" name="gigyaIntro">
</div>
<br />
<div id="gigyaEditConnectionsUI" name="gigyaEditConnectionsUI">
</div>
<br />
<div id="gigyaUserInfo" name="gigyaUserInfo">
</div>
<br />
<div id="gigyaUserIdentities" name="gigyaUserIdentities">
</div>
<br />
<div id="gigyaUserIdentities2" name="gigyaUserIdentities2">
</div>
<br />
<div id="gigyaUserCapabilities" name="gigyaUserCapabilities">
</div>
<br />
<div id="gigyaUserInfo2" name="gigyaUserInfo2">
</div>
<br />
<div id="gigyaSessionInfo" name="gigyaSessionInfo">
</div>
<br />
<div id="gigyafriends" name="gigyafriends">
</div>


</div>

    </div>

<%@ include file="/template_include/gigya_socialize.inc" %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
