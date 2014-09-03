<%
//Application API Key and application secret from creating app in FB
 appapikey = "";
 appsecret = "";
 appid = "";

if(aszHostID.equalsIgnoreCase("volengfycsandbox")){
	appid = "sandboxappid";
	appapikey = "sandboxappapikey";
	appsecret = "sandboxappsecret";
} else{
		appid = "appid";
		appapikey = "appapikey";
		appsecret = "appsecret";
}		

// used for all versions of christianvolunteering for posting iwanttohelp to the fb user's feed, if they're logged in
 tempappid="";
 tempapikey="";
 tempsecretkey="";

if(request.getHeader("host").contains("7001")){
	tempappid="tempappid";
	tempapikey="tempapikey";
	tempsecretkey="tempsecretkey";
}else if(
	aszHostID.equalsIgnoreCase( "volenggospel" ) ||
	aszHostID.equalsIgnoreCase( "volenggospelcom" ) 
){
	tempappid="tempappid";
	tempapikey="tempapikey";
	tempsecretkey="tempsecretkey";
}else if(aszRemoteHost.contains("hurricanesandy")){
	tempappid="tempappid";
	tempapikey="tempapikey";
	tempsecretkey="tempsecretkey";
}else if(aszHostID.equalsIgnoreCase("volengchurch")){
	tempappid="tempappid";
	tempapikey="tempapikey";
	tempsecretkey="tempsecretkey";
}else if(aszRemoteHost.contains("christianvolunteering.org")){
	tempappid="tempappid";
	tempapikey="tempapikey";
	tempsecretkey="tempsecretkey";
}

out.print("<!-- api host id is: "+aszHostID+" -->");
out.print("<!-- tempappid id is: "+tempappid+" -->");
%>