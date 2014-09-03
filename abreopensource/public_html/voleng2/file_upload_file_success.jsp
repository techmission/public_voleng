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
	  <span id="title">Manage Resum&eacute;</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Manage Resum&eacute;</span>
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

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Manage Resum&eacute;</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1">account summary</a> &gt; 
	manage resum&eacute;  </div>
</div>
<% } %>
	
	<div id="body">
 
<br /><br />

<div align="center">
</div>

<br />
<h1><%=userprofile.getUSPNameFirst() %> <%=userprofile.getUSPNameLast()%></h1>

<center><h2>Resum&eacute; File Successfully Uploaded</h2></center>
<br />

<hr width="100%">
<form action="<%=aszPortal%>/fileUploadAndSave.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="fileType" id="fileType" value="resume" />
<center>
<div style="padding-bottom:60px;">
<h2 style="float:left; " >Please Upload your Resum&eacute; here:</h2>
<div style="text-align:left; float:left; margin-left: 10px; padding-top:6px;">
			<input type="file" name="theFile"/> 
<br /><br /><br />
<input class="volunteer" type="submit" value="upload file" src="../template/image/help.gif" >
</div>
</div>

<br clear="all" />

<br /><br />
<HR width="100%">							
<br /><br />

<% if(userprofile.getUSPResumeFilePath().length() > 0){ %>

<h3 style="float:left; padding-top:17px;" >Or download your current one for review here:</h3>
<a style="float:left;" href="<%=request.getContextPath() %>/fileDownload.do?resume=true&uid=<%=uid %>" class="volunteer button" src="../template/image/help.gif">
	download resum&eacute;
</a> 
<br />
<h3 style="float:left; padding-top:17px;" >To delete your resum&eacute;, please click here:</h3>
<a style="float:left;" href="<%=request.getContextPath() %>/register.do?method=processDeleteResume" class="volunteer button" src="../template/image/help.gif">
	delete resum&eacute;
</a> 

<% } %>

</center>
</form>


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

