<%
String aszContent = "";
try{
	aszContent=request.getAttribute("content").toString();
//	request.removeAttribute("content");
}catch(NullPointerException e){
}

out.print(aszContent);
out.print("<br>docs:<br>");
String aszDocs = "";
try{
	aszDocs=request.getAttribute("docs").toString();
//	request.removeAttribute("docs");
}catch(NullPointerException e){
}

out.print(aszDocs);
%>