<%
try{
	aszLocationSearch = request.getParameter("location");
	if(aszLocationSearch==null) aszLocationSearch="";
}catch(NullPointerException e){
	aszLocationSearch = "";
}

try{
	aszKeywordSearch = request.getParameter("tag");
	if(aszKeywordSearch==null) aszKeywordSearch="";
	else if(aszKeywordSearch.length()>0) aszKeywordSearch="keywords:\""+aszKeywordSearch+"\"";
}catch(NullPointerException e2){
	aszKeywordSearch = "";
}

try{
	aszPositionTypeSearch = request.getParameter("positiontype");
	if(aszPositionTypeSearch==null) aszPositionTypeSearch="";
}catch(NullPointerException e2){
	aszPositionTypeSearch = "";
}

try{
	aszContentTypeSearch = request.getParameter("contenttype");
	if(aszContentTypeSearch==null) aszContentTypeSearch="";
}catch(NullPointerException e2){
	aszContentTypeSearch = "";
}

%>