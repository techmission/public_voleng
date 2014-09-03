
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
String fileName=(String)request.getAttribute("fileName");
%>

<p align="center"><font size="5" color="#000080">File Successfully Received</font></p>
<p align="center"><a href="<%=request.getContextPath()%>/imgs/banners/<%=fileName%>">Click here to download</a></p>

</div>
</body>
</html>