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
<style>
div#maincontent {
    width: 915px;
}
</style>

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
<div id="maincontent">
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
<br />


<br />

	<div class="htmlSection ui-draggable" >
			<h2>** Please Note: Your application will NOT be submitted to us until you click <strong>"upload new file"</strong> or <strong>"use your existing resum&eacute;"</strong>.</h2>
	</div>
	
<hr width="100%">

<br />
<h1><%=userprofile.getUSPNameFirst() %> <%=userprofile.getUSPNameLast()%></h1>

<hr width="100%">

<% if(userprofile.getUSPResumeFilePath().length() > 0){ %>
<style>
#new_resume{
	text-align:left;
	float:left;
	margin-left: 30px;
	padding-top:6px;
}

#existing_resume{
	text-align:left;
	float:left;
	margin-left: 30px;
	padding-top:6px;
    padding-left: 10px;
    padding-top: 5px;
}
</style>
<center>

<div style="padding-bottom:60px; margin-left: 200px;">
	<h2 style="float:left; " >You can upload a new resum&eacute; here:</h2>
	<br clear="all" /><br>
	<div id="new_resume">
		<form action="<%=request.getContextPath() %>/fileUploadAndSave.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="fileType" id="fileType" value="resume" />
			<input type="hidden" name="source" id="source" value="cvinternapplic" />
			<input type="hidden" name="uid" id="uid" value="<%=uid %>" />
			<input type="file" name="theFile"/> 
		</form>

		<div style="float:left;">
				<input class="volunteer" type="submit" value="upload new file" src="../template/image/help.gif" >
		</div>
	</div>
		
	<br clear="all"><br><br><br><br>
	<h2 style="float:left; " >Or click here to use your existing resum&eacute;:</h2>
	<br clear="all" /><br>
	<div id="existing_resume">
		<form action="<%=request.getContextPath() %>/cityvision/email.do?method=showApplicSent" method="get">
			  <input type="hidden" name="method" id="method" value="showApplicSent" />
			 <input class="volunteer" type="submit" value="use your existing resum&eacute;" src="../template/image/help.gif" >
		</form>
	</div>
</div>

<br clear="all" />
<br /><br />
<h3 style="float:left; padding-top:17px;" >To view your <strong>CURRENT</strong> Resum&eacute; file for review, click here:</h3>
<a style="float:left;" href="<%=request.getContextPath() %>/fileDownload.do?resume=true&uid=<%=uid %>" id="volunteer" class="volunteer" src="../template/image/help.gif">
	download resum&eacute;
</a> 

</center>
<br clear="all" />
</form>

<hr width="100%">							

<% }else{ %>
<form action="<%=request.getContextPath() %>/fileUploadAndSave.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="fileType" id="fileType" value="resume" />
<input type="hidden" name="source" id="source" value="cvinternapplic" />
<input type="hidden" name="uid" id="uid" value="<%=uid %>" />
<center>
<div style="padding-bottom:60px;">
<h2 style="float:left; " >To COMPLETE Your Application, Upload your Resum&eacute; here:</h2>
<div style="text-align:left; float:left; margin-left: 10px; padding-top:6px;">
			<input type="file" name="theFile"/> 
<br /><br /><br />
<input class="volunteer" type="submit" value="upload file" src="../template/image/help.gif" >
</div>
</div>
<br clear="all"/><br>
	<div class="htmlSection ui-draggable" id="tfa_9755162050360">
		<div class="htmlContent" id="tfa_9755162050360-HTML">
			<p>Once you click "upload file", your application will be submitted to us.</p>
			<br /><br />
			<h2>**PLEASE NOTE: Your application is INCOMPLETE until you upload your resum&eacute; with us.</h2>
		</div>
	</div>

<br clear="all" />

<br />
<HR width="100%">							

</center>
</form>

<% } %>


<form action="<%=request.getContextPath() %>/cityvision/email.do?method=showApplicSent" method="get">
<input type="hidden" name="method" id="method" value="showApplicSent" />
<br><br>
<br><br><br>

<div style="align:left;">

		<input type="button" onclick="history.go(-1)" value="Cancel" class="secondaryAction">
	<!-- input type="submit" value="Submit" class="primaryAction"> -->
</div> 




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