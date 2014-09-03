<%@page import="java.util.*" %>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>

<% 
Date dToday ; 
long lLocalUnixTime;
dToday = new java.util.Date();
lLocalUnixTime = (dToday.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
//out.println("local time is:<BR>"+lLocalUnixTime+"<BR>");		    


IndividualsBRLO aIndivObj = new IndividualsBRLO();
String signed_request = request.getParameter("signed_request");
 
//out.print("signed_request init: " + request.getParameter("signed_request"));
 
JSONObject jsonObj = null;
try{
	if( signed_request != null && (! signed_request.equals(""))){
		jsonObj = aIndivObj.decodeFacebookSignedRequest(signed_request, appsecret);
	}
}catch(NullPointerException e){
	jsonObj = new JSONObject();
}

if(jsonObj != null){

	//set the appropriate session variables if they aren't set.  Make sure that the appropriate keys exist in the jsonObj first
	if(session.getAttribute("FB_session_key")==null && jsonObj.has("oauth_token")){
				//sessionKey = (String) session.getAttribute("FB_session_key");
				session.setAttribute("FB_session_key",jsonObj.getString("oauth_token"));
	}
	if(session.getAttribute("FB_session_key_expire")==null  && jsonObj.has("expires")){
				//expireTime = (String) session.getAttribute("FB_session_key_expire");
				session.setAttribute("FB_session_key_expire",jsonObj.getString("expires"));
	}
	if((session.getAttribute("FB_User_ID")==null || session.getAttribute("FB_User_ID").equals("")) && jsonObj.has("user_id")){
	    //out.print("user id from json obj: " + jsonObj.getString("user_id"));
		session.setAttribute("FB_User_ID", jsonObj.getString("user_id"));
		//out.print("user if from session: " + session.getAttribute("FB_User_ID").toString());
	}
}

//out.print("has user_id: ");
//out.print(jsonObj.has("user_id"));

String expireTime = "";		

String sessionKey="";

//out.print(jsonObj);

if(jsonObj != null) {
	if(jsonObj.has("oauth_token")){
		sessionKey = jsonObj.getString("oauth_token");
	}
	if(jsonObj.has("expires")){
		expireTime = jsonObj.getString("expires");
	}
}


String user =null;

/*if(jsonObj !=null){
		out.println(
		//"<BR><BR>EXPIRES:<BR>"+ jsonObj.getString("expires")+
		//"<BR><BR>Time:<BR>"+request.getParameter("fb_sig_time")+
		//"<BR><BR>Session Key:<BR>"+ jsonObj.getString("oauth_token")+
		//"<BR><BR>API_KEY:<BR>"+request.getParameter("fb_sig_api_key")+
		//"<BR><BR>ADDED:<BR>"+request.getParameter(FacebookParam.ADDED.toString())+
		//"<BR><BR>Friends:<BR>"+request.getParameter(FacebookParam.FRIENDS.toString())+
		//"<BR><BR>EXT_PERMS:<BR>"+request.getParameter(FacebookParam.EXT_PERMS.toString())+
		//"<BR><BR>Request Method:<BR>"+request.getParameter(FacebookParam.REQUEST_METHOD.toString())+
		"<BR><BR>"); 
}*/

if(session.getAttribute("FB_session_key") == null ){
	session.setAttribute("FB_session_key",sessionKey);  // clearing Value in session Object
}
else{
//	out.println("<br>session key from attribute: "+session.getAttribute("FB_session_key")+"<br>");
}

if (sessionKey != null){
		if(sessionKey.length() > 0){
			//out.println("test");
			if(! session.getAttribute("FB_session_key").toString().equalsIgnoreCase(sessionKey) ){
				session.setAttribute("FB_session_key",sessionKey);
			}
		}
}
if(session.getAttribute("FB_session_key_expire") != null ){
	long expireLongOrig=0;
	try{
		expireLongOrig = Long.parseLong(session.getAttribute("FB_session_key_expire").toString().trim());
	}catch(NumberFormatException nfe){
	}
	//out.print("<br>expire - in session attribute: "+expireLongOrig+"<br>");
	if(lLocalUnixTime+3600 > expireLongOrig){
		session.setAttribute("FB_session_key",sessionKey);
		//out.println("<br>reset session key attribute: "+session.getAttribute("FB_session_key")+"<br>");
	}
}

if(
session.getAttribute("FB_session_key_expire") == null ){
	session.setAttribute("FB_session_key_expire",expireTime);  // clearing Value in session Object
}
if (expireTime != null){
		if(expireTime.length() > 0){
			if(! session.getAttribute("FB_session_key_expire").toString().equalsIgnoreCase(expireTime) ){
				session.setAttribute("FB_session_key_expire",expireTime);
			}
		}
}


String timestamp="";
String fbuserid="";

if (sessionKey != null && jsonObj != null){
						
	if(jsonObj.has("user_id")){ 

		user = jsonObj.getString("user_id");  // get user as a string.  User info passed as request parameter
		//out.print("user: " + user);
		
		Long userLong = new Long(user);
		if(session.getAttribute("FB_session_key") == null ){
			session.setAttribute("FB_session_key",sessionKey);  // set the Facebook Session key as a local session var
		}
		//out.print("user is: "+user+"<br>");
		
		if(user.length()>0){
			fbuserid=user;
		}
		if(session.getAttribute("FB_User_ID") == null ){
			session.setAttribute("FB_User_ID",userLong);  // set the Facebook User ID as a local session var
		}
		if(user.length() > 0){
			if(! session.getAttribute("FB_User_ID").toString().equalsIgnoreCase(user) ){
				session.setAttribute("FB_User_ID_init",session.getAttribute("FB_User_ID"));
				session.setAttribute("FB_User_ID",userLong);
			}
		}
		//out.println("<br>fb user id: "+session.getAttribute("FB_User_ID"));
		if(jsonObj.getString("issued_at") != null){
			timestamp=jsonObj.getString("issued_at");
			//out.print(timestamp);
			if((session.getAttribute("FB_Timestamp") == null || session.getAttribute("FB_Timestamp").equals("")) && timestamp != null){
				session.setAttribute("FB_Timestamp",timestamp);  // set init timestamp as a local session var for auth returning users
			}
		}

		
	}
	}
//out.print("timestamp: " + timestamp);
//out.print("session timestamp: " + session.getAttribute("FB_Timestamp"));

%>
