<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a></div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>


 <div id="body">

 <h2>Test page for authenticated use cases</h2>
<br>
</div>

   
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
