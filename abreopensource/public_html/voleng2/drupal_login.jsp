<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<%
/*
 * gets loaded in 
 */
%>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="java.security.SignatureException" %>
<%@ page import="javax.crypto.Mac" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.sql.Timestamp" %>

<%
// encrypt the signature here and then pass it to aszURLdom in a form; 
//	on that end, cookie_login.module will use the same info (including a secret key) to encrypt and then compare
			/*
				1. GET variable $cookie_login_secret_key from included keys file
				2. SET info for form that eventually will be used to set the COOKIE: 
						$base_string: uid, nonce, domain, action, (timestamp) 
				3. HMACSHA1 encrypt $base_string using $cookie_login_secret_key 
				4. pass the result from #3 as the $SIGNATURE to be used in the COOKIE
			*/

// gets set in an included file:
String apiKeyCookieLogin = ""; // public
String secretKeyCookieLogin = ""; // private
%>
<%@ include file="/template_include/keys_cookie_login.inc" %>
<%
		long localUnixTime;
		Date today = new java.util.Date();
		localUnixTime = today.getTime() ;//+ 18000000; // adjust for GMT comparison
		/**
		 * calculate a signature to pass, using the private SecretKey set for cookie_login module
		 */		
		String mySignature = "";
		byte[] binaryKey = null;
    	Random r1 = new Random();  
    	String nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
    	nonce = nonce.substring(0,9);
		int iUID = 0;
		String aszUID = "";
		if(aCurrentUserObj != null){
			iUID = aCurrentUserObj.getUserUID();
		}
		aszUID = "" + iUID;
		String baseString = localUnixTime + "_" + nonce + "_" + aszUID + "_" + apiKeyCookieLogin;
		
		try{
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(secretKeyCookieLogin.getBytes(), "HmacSHA1");
			mac.init(secret);
			byte[] digest = mac.doFinal(baseString.getBytes());
			String enc = new String(digest);
			out.println(enc);
			String result = "";
			for (int i=0; i < digest.length; i++) {
			    result += Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
			}
			mySignature = result;	
		}catch (Exception e){
			out.println(e.getMessage());
		}
		
		// trim the return carriage that for some reason is getting added to the end of mySignature
		mySignature=mySignature.trim();
%>

<%
String aszURLdom = "http://www.urbanministry.org";
		if ( request.getHeader("host").contains(":7001") || request.getHeader("host").contains(":8080")  || request.getHeader("host").contains("cv.org") ){
			aszURLdom = "http://www.um.org";
		}else{
			aszURLdom = "http://www.urbanministry.org";
		}
%>
<script type="text/javascript">
function myfunc () {
var frm = document.getElementById("cookie-login-form");
<% if(iUID>0){ %>
frm.submit();
<% } %>
}
window.onload = myfunc;
</script>

<div style="display:none">
<form action="<%=aszURLdom%>/sites/all/modules/custom/cookie_login/cookie_external.php" accept-charset="UTF-8" method="post" id="cookie-login-form">
	<input type="hidden" name="referer" id="referer" value="<%=aszRemoteHost%><%=aszPortal%>" />
	<input type="hidden" maxlength="60" name="id" id="edit-name" value="<%=aszUID%>" />
	<input type="hidden" name="role" id="role" value="siteadmin" />
	<input type="hidden" name="nonce" id="nonce" value="<%=nonce%>" />
	<input type="hidden" name="timestamp" id="timestamp" value="<%=localUnixTime%>" />
	<input type="hidden" name="signature" id="signature" value="<%=mySignature%>" />
	<input type="hidden" name="base_string" id="base_string" value="<%=baseString%>" />
	<input type="submit" name="form-submit" id="form-submit" value="submit" class="form-submit" />
	<input type="hidden" name="form_id" id="form_id" value="cookie_login_block"  />
</form>
</div>
<% 
	if (!( session.getAttribute("drupalsession") == "loggedin" )){ 
		session.setAttribute("drupalsession","loggedin");  // Storing Value into session Object
	}
%>
