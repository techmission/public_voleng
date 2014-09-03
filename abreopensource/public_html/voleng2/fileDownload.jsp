<%@ include file="/template_include/topjspnologin1.inc" %>
<%@ include file="/template/header.inc" %>
<%@ include file="/template/navigation.inc" %>

<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<%
int uid = userprofile.getUserUID();
String aszCategories = "";
String[] a_aszCategories = userprofile.getUSPServiceAreasStringArray();
for(String value : a_aszCategories){
	aszCategories += value + ", ";
}
if(aszCategories.endsWith(", ")){
	aszCategories = aszCategories.substring(0,aszCategories.length()-2);
}
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">View Resum&eacute;</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">View Resum&eacute;</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">View Resum&eacute;</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
	view resum&eacute;  </div>
</div>
<% } %>
	
	<div id="body">
 
<br /><br />

<div align="center">
</div>

<br />
<h1><%=userprofile.getUSPNameFirst() %> <%=userprofile.getUSPNameLast()%></h1>

<hr width="100%">
<table border="0">
	<tr>
		<td>
			<table id="listingdetail" class="listingdetail" border="0">
				<tr>
					<td class="left">
						<table class="info" border="0" cellpadding="2" cellspacing="0">
							<tr style="name">
								<th width=100 align="right">Service Areas:</th>
								<td></td>
								<td>
									<%=aszCategories %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<a href="<%=request.getContextPath() %>/fileDownload.do?resume=true&uid=<%=uid %>" class="volunteer button" src="../template/image/help.gif">
	download resum&eacute;
</a> 

<div class="clear" style="height: 5px;"></div>

<HR width="100%">							

<pre><div id="textareaformat">
<h3><img border="0" src="http://<%=aszSubdomain%>/imgs/bullet_4.gif" width="7" height="7" /> Description</h3>
</div></pre>


</div>

  </div>  

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %>
<!-- end footer information -->

