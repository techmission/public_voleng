<!-- this file should get included in a hidden iframe on facebook app index pages -->
facebook login
<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<!-- this file should get included in a hidden iframe on facebook app index pages -->

<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.Timestamp" %>

<%@ include file="/template_include/facebookapi_keys.inc" %>
<%@ include file="/template_include/facebookapi_init.inc" %>

<%
out.println("login timestamp: " + timestamp);
out.println("login session timestamp: " + session.getAttribute("FB_Timestamp"));
//String timestamp="";
if(session.getAttribute("facebookapikey")==null || session.getAttribute("facebookapikey").equals(null)){
	session.setAttribute("facebookapikey",appapikey);
	out.print("was null... now is: "+appapikey);
}else if(session.getAttribute("facebookapikey").toString().length()<1){
	session.setAttribute("facebookapikey",appapikey);
	out.print(appapikey);
}
if(appsecret.length()>0){out.print(";secret key gets set;");}
if(session.getAttribute("facebooksecretkey")==null || session.getAttribute("facebooksecretkey").equals(null)){
	session.setAttribute("facebooksecretkey",appsecret);
	out.println("app secret has been set");
}else if(session.getAttribute("facebooksecretkey").toString().length()<1){
	session.setAttribute("facebooksecretkey",appsecret);
	out.println("app secret has been set");
}

/*IndividualsBRLO aIndivObj = new IndividualsBRLO();
 String signed_request = request.getParameter("signed_request");
 out.print("signed_request: " + request.getParameter("signed_request"));
 JSONObject jsonObj = aIndivObj.decodeFacebookSignedRequest(signed_request, appsecret);

if(session.getAttribute("FB_session_key")==null && jsonObj !=null){
			//sessionKey = (String) session.getAttribute("FB_session_key");
			session.setAttribute("FB_session_key",jsonObj.getString("oauth_token"));
}
if(session.getAttribute("FB_session_key_expire")==null  && jsonObj != null){
			//expireTime = (String) session.getAttribute("FB_session_key_expire");
			session.setAttribute("FB_session_key_expire",jsonObj.getString("expires"));
}
if(session.getAttribute("FB_User_ID")==null && jsonObj != null){
	session.setAttribute("FB_User_ID", jsonObj.getString("user_id"));
}

String timestamp="";
if(jsonObj.getString("issued_at") != null){
			timestamp=jsonObj.getString("issued_at");
			out.print(timestamp);
			if(session.getAttribute("FB_Timestamp") == null && timestamp != null){
				session.setAttribute("FB_Timestamp",timestamp);  // set init timestamp as a local session var for auth returning users
			}
}*/

String aszFBUserId="";
	aszFBUserId= (String)session.getAttribute("FB_User_ID");
	out.print("User id: " + aszFBUserId);
	if(aszFBUserId != null){
		if(!	( session.getAttribute("FB_User_ID")==null || session.getAttribute("FB_User_ID").equals(null) )	){
			session.setAttribute("FB_User_ID",aszFBUserId);
		}
		out.print("api key:"+appapikey);
	
		out.println("<br> timestamp: " + session.getAttribute("FB_Timestamp") + "<br><br>");
//		if(request.getParameter("FB_Timestamp") != null){
		if(!	( session.getAttribute("FB_Timestamp")==null || session.getAttribute("FB_Timestamp").equals(null) )	){
			timestamp=(String)session.getAttribute("FB_Timestamp");
			out.print(timestamp);
			//if(session.getAttribute("FB_Timestamp") == null || session.getAttribute("FB_Timestamp").equals(null) ){
				//session.setAttribute("FB_Timestamp",timestamp);  // set init timestamp as a local session var for auth returning users
			//}
			//session.setAttribute("FB_Timestamp",timestamp);  // set init timestamp as a local session var for auth returning users
		}else{
			out.print("no timestamp parameter");
		}

String tempRefererMethod=aszReferer;
%>
<script type="text/javascript">
function myfunc () {
var frm = document.getElementById("fb_login_form");
<% if(! (tempRefererMethod.contains("&secondtime=true")) ){ %>
//alert("timestamp: <%=timestamp%>");
//alert("fbuserid: <%=fbuserid%>");
//alert("aszFBUserId: <%=aszFBUserId%>");
frm.submit();
<% } %>
}
window.onload = setTimeout('myfunc()',1000);
</script>
<%
		//if(session.getAttribute("FB_User_ID") == null ){
			session.setAttribute("FB_User_ID",aszFBUserId);  // set the Facebook User ID as a local session var
		//}
%>

<form action="<%=request.getContextPath()%>/register.do" method="get" id="fb_login_form">
<input type="hidden" value="loginFBUser" name="method" id="method">
<input type="hidden" value="<%=aszFBUserId%>" name="facebookuid" id="facebookuid">
<input type="hidden" value="<%=timestamp%>" name="timestamp" id="timestamp">
<input type="hidden" value="<%=tempRefererMethod%>" name="tempreferermethod" id="tempreferermethod">
</form>



<%
		String str_date="";
		str_date = timestamp;
		int l_date=0;
		if(str_date.length()>0){
				l_date=Float.valueOf(str_date.trim()).intValue();
		}
		int iTimeLimit=2;
		
		//	first, verify that the timestamp is within 20 min 240,000*10 milliseconds of the GMT timestamp now() - format YYYY-MM-DD HH:MM:SS
		DateFormat formatter ; 
		Date date, today ; 
		long remoteUnixTime, localUnixTime;
		boolean withinTimeLimit=false;
		today = new java.util.Date();out.print(today+"");
		formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		//date = (Date)formatter.parse(str_date);
		remoteUnixTime = l_date;//.intValue();//Integer.parseInt(str_date);//date.getTime();
		localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
		java.sql.Timestamp timeStampDate = new Timestamp(today.getTime());
		long diff=java.lang.Math.abs(localUnixTime - remoteUnixTime);
		iTimeLimit=iTimeLimit*60; // 300,000 or 120,000
		    
		// causes issues for changes in Daylight Savings Time, b/c we are using an old jdk; the .jsp validation works, but this .java validation is an hour off
		    
		// test to see if within 5 min
		if(diff > iTimeLimit){ 
			// if greater than 5 minutes, then test to see if it's 55-65 minutes off; 
			//		this accounts for a possible hour adjustment where DST has not been updated to new North American schedule ~2009
			if( !((3600-iTimeLimit) < diff && diff < (3600+iTimeLimit)) ){ // 55 min < diff < 65 min (or 58 min < diff < 62 min)
				withinTimeLimit=false;
			}else{
				// timestamps are roughly 1 hour off, give or take 5 min.  for now, this counts as validated (DST)
				withinTimeLimit=true;
			}
		}else{
			// the timestamps are within 5 minutes; time suggested by gigya to validate against
			withinTimeLimit=true;
		}
	}
%>
