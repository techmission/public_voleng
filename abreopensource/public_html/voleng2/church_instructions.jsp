<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Add Volunteer Opportunities to Your Church Website</title>

<% } else { %>
<% } %>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%
String aszBoston = "display:none";
if ( (aszHostID.equalsIgnoreCase("volengboston")) || (aszHostID.equalsIgnoreCase("volengnewengland")) ){
	aszBoston = "display:inline";
}
%>

</head>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Add Volunteer Opportunities to Your Church Website</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Add Volunteer Opportunities to Your Church Website</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
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
<span style="float: left;">Add Volunteer Opportunities to Your Church Website</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/churchinstructions.jsp">add volunteer opportunities to your church website</a></div>
</div>
<% } %>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="http://www.ChristianVolunteering.org/imgs/pic/addvolopp1.gif" width="417" height="227" ></td>
    <td valign="top" class="intsplash"><h1>
	Add Volunteer Opportunities to Your Church Website</h1>
      <p LANG="en-US" STYLE="margin-bottom: 0in">Below are the steps to adding your volunteer listings to your church's website. Your volunteer opportunity listings will update automatically as you change them on ChristianVolunteering.org.</p>
	</td>
  </tr>
</table>
</div>

<div id="body">
<img src="http://www.christianvolunteering.org/imgs/churchvol_listings.jpg" height="299" width="308" align="right" vspace="5" alt="Example of volunteer listings on church website" />
<ol><li><a href="<%=aszPortal%>/individualregistration.jsp">Create a free account.</a></li>
<li><a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1">Enter organization information after logging in.</a></li>
<li><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">List Your Church's Outreach Volunteer Opportunities</a> after entering organization information.</li>
<li>Cut and paste "code" to your website manager to insert volunteer opportunities into your site.  
<ul><li>This code appears at the bottom of your organization's profile page once you have added your organization information (step 2).</li>
<li>After you have inserted the code on your website, any changes you make to your volunteer opportunities on ChristianVolunteering.org will automatically update on your site.</li>
<li>If you've done it correctly, you should have a page similar to: <a href="http://www.bostonvineyard.org/getinvolved/volunteer/">http://www.bostonvineyard.org/getinvolved/volunteer/</a>.</li></ul>
</li></ol>

<p><strong>Advantages:</strong></p>

<ol><li>Provides a simplified way to manage all your church volunteer outreach opportunities without web design knowledge.  Your updates to volunteer opportunities automatically get updated on your website.</li>
<li>Provide one location on your church website where church members can find all your church volunteer outreach opportunities. You will receive E-mail notifications from those interested in your volunteer opportunities. </li>
<li>Connect your church members with the largest directory of Christian volunteer opportunities on the Web.</li>
<li>In the future, we will provide reporting capabilities to churches on the activities of their volunteers.</li></ol>

</div>

</div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->