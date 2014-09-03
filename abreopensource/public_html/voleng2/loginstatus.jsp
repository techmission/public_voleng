<%
if(!(request.getServletPath().equalsIgnoreCase("/voleng2/login1.jsp"))){
	if ( request.getHeader("host").contains(":7001") || request.getHeader("host").contains(":8080")  || request.getHeader("host").contains("cv.org") ){
%>
<iframe name="loginstatus" id="loginstatus" src="http://www.um.org/sites/all/modules/custom/cookie_login/cookie_drupal.php" height="0" width="0" ></iframe>
<% 
	}else{
%>
<iframe name="loginstatus" id="loginstatus" src="http://www.urbanministry.org/sites/all/modules/custom/cookie_login/cookie_drupal.php" height="0" width="0" ></iframe>
<% 
		} 
}
%>

<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

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
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Processing User Status...</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>


<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Processing User Status... </span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; log in  </div>
</div>
<% } %>

 <div id="body">
 
 <br><br><br>
 <br><br>
<center>
<h2>Processing Your User Status... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>

<form action="<%=aszPortal%>" name="forwardmap" style="display:none">
<input name="forward" id="forward" type="button" value="Reload Page" onClick="window.location.reload()">
</form>

   <br><br>
   <br><br>
   <br><br>
   <div id="refresh page warning">
   <h3>NOTE: If you are in Internet Explorer, and have been on this page for a while,<br>click <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">here</a> to log in and continue browsing the site</h3> 
   </div>
</center>

    </div>
    </div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
