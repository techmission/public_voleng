<!-- start JSP information -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information -->

<html>
<head>

<style>
body{
	font-family: Arial,Helvetica,Verdana,sans-serif;
	font-size:	12px;
}
.criticaltext {
    color: #F37C16;
    font-weight: bold;
}
#maincontent table {
    font-size: 12px;
}
</style>

</head>

<body>
<div id="maincontent">
<%
out.print("<!-- portal parameter is: "+portal+"-->");
String fileName = "";
if(session.getAttribute("fileName")!=null){
	fileName=(String)session.getAttribute("fileName");
}
out.print("<!-- fileName parameter is: "+fileName+"-->");
if(session.getAttribute(portal+"_banner")!=null){
	fileName=(String)session.getAttribute(portal+"_banner");
}
out.print("<!-- aszPortal  is: "+aszPortal+"-->");
out.print("<!-- fileName  is: "+fileName+"-->");

String fileType = "image";
if(request.getParameter("method")!=null){
	String method=(String)request.getParameter("method");
	if(method.equals("showResumePost")){
		fileType="resume";
	}
}

if(fileName.length()>0 && fileType.equals("image")){
%>



<p style="margin-left: 15px;">
	<font size="4" color="#596e9f">Current Banner:</font>
	<br>
<img width="360" height="65" border="0" alt="/imgs/banners/<%=fileName%>" src="/imgs/banners/<%=fileName%>">
</p>





<% } %>

<%@ include file="/template_include/google_adwords_orgpage.inc" %>

<p align="center">
<form action="<%=request.getContextPath()%>/fileUploadAndSave.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="portal" value="<%=portal%>" />
<input type="hidden" name="fileType" id="fileType" value="<%=fileType %>" />
<center>
<table>
	<tr>
		<td>
			Select image file to upload for site banner: 
		</td>
		<td align="left">
			<input type="file" name="theFile"/> 
		</td>
	</tr>
		<td width="330"></td>
		<td>
		    <input type="submit" value="Upload Image">
		</td>
	</tr>
</table>
</center>
</form>
</p>
</div>
</body>
</html>
