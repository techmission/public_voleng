<% // login the user to um.org if they just came from logging in on cv.org

//String aszURLdomain = "http://www.urbanministry.org";
aszURLdomain = "http://www.urbanministry.org";
if ( request.getHeader("host").contains(":7001") || request.getHeader("host").contains(":8080")  || request.getHeader("host").contains("cv.org") ){
	aszURLdomain = "http://www.um.org";
}else{
	aszURLdomain = "http://www.urbanministry.org";
}

out.println("<!-- URL domain is: " +  aszURLdomain + "-->");
if(!(session.getAttribute("drupalsession")==null)){
	out.println("<!--session attribute drupalsession is: " + session.getAttribute("drupalsession") + "-->");
}
if(! (aszReferer == null)){ // first make sure it's not the first time the user is visiting the site; ie, there is no referer URL
//	if(!(session.getAttribute("drupalsession")==null)){
	if ( 	aszPath.equalsIgnoreCase("/index.jsp") &&
		session.getAttribute("drupalsession") == "" ){ // we'll assume the user logged out; the logout function has cleared the drupalsession attribute
%>

<iframe name="footerlogout" id="footerlogout" src="<%=aszURLdomain%>/sites/all/modules/custom/cookie_login/cookie_clear.php" height="0" width="0" style="display:none"></iframe>
<%
//	}
	} else if ( // in this else, Referer still is not null; if Referer is null, we don't want to include either of the footer iframes...
			(!( session.getAttribute("drupalsession") == "loggedin" )) && 
			( 
				aszAuthPage == "true" || 
				aszPath.equalsIgnoreCase("/voleng2/placeholder.jsp") 
			)
	){ 
		if(	aszPath.equalsIgnoreCase("/voleng2/create_account1.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp") ||
			aszPath.equalsIgnoreCase("/voleng2/login1.jsp") ||
			aszPath.equalsIgnoreCase("/index.jsp")
		){
%>

<%
		}else{ // the drupal session is not set to logged in & the user is not currently on a login page or create account page
%>

<iframe name="footerlogin" id="footerlogin" src="<%=aszPortal%>/register.do?method=showDrupalLogin" height="0" width="0" style="display:none"></iframe>
<% 
		} 
	}
} 
 


else{
%>
<iframe name="footerlogin" id="footerlogin" src="<%=aszPortal%>/register.do?method=showDrupalLogin" height="0" width="0" style="display:none"></iframe>
<%
}



%>

