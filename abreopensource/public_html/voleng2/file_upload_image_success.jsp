<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->



<p align="center"><font size="5" color="#000080">File Successfully Received</font></p>

<%
String fileName = "";
if(session.getAttribute("fileName")!=null){
	fileName=(String)session.getAttribute("fileName");
}
out.print(fileName);
if(session.getAttribute(aszPortal+"_banner")!=null){
	fileName=(String)session.getAttribute(aszPortal+"_banner");
}
out.print(fileName);

if(session.getAttribute("fileType")!=null){
	session.removeAttribute("fileType");
}

if(fileName.length()>0){
%>
<p align="center">
<img width="360" height="65" border="0" alt="<%=aszPortal%>/imgs/banners/<%=fileName%>" src="<%=aszPortal%>/imgs/banners/<%=fileName%>">
</p>
<% } %>
