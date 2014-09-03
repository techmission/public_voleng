<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
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


<p align="center"><font size="5" color="#000080">File Upload Had Errors</font></p>



<%
String fileName = "";
if(request.getAttribute("fileName")!=null){
	fileName=(String)request.getAttribute("fileName");
}
if(request.getAttribute(aszPortal+"_banner")!=null){
	fileName=(String)request.getAttribute(aszPortal+"_banner");
}

if(fileName.length()>0){
%>
<p align="center">
	<font size="5" color="#000080">Current Banner:</font>
	<br>
	<img src="<%=aszPortal%>imgs/banners/<%=fileName%>" alt="<%=aszPortal%>/imgs/banners/<%=fileName%>">
</p>
<% 
} 
%>

<form action="<%=aszPortal%>/FileUploadAndSave.do" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td align="center" colspan="2">
			<font size="4">Try Again: </font>
		</td>
	</tr>
	<tr>
		<td align="right">
			File Name
		</td>
		<td align="left">
			<input type="file" name="theFile"/> 
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type="submit" value="Upload File">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
