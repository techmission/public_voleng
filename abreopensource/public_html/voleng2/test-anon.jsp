<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<%@page import="java.text.DecimalFormat" %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Opportunities Search Results</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% } %>


<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results directory">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Opportunities Search Results</span>

<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; opportunities search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->
<br>

</div></div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->

