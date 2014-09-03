<!-- /template_include/gigya_linkAccounts.inc -->
<%@ include file="/template_include/gigya_socialize_keys.inc" %>
<jsp:useBean id="UserSessionBeanInc" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%@ page import="java.io.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="java.security.SignatureException" %>
<%@ page import="javax.crypto.Mac" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>
<%
String aszMethodInit="";
if(!(request.getParameter("method") == null)){
	aszMethodInit=request.getParameter("method");
}

// security checking code copied/modified from:
// http://www.nsftools.com/tips/Base64Test.java and 
// http://docs.amazonwebservices.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/index.html?AuthJavaSampleHMACSignature.html 

PersonInfoDTO aCurrentUserObjInc = UserSessionBeanInc.getCurrentUser( request);
byte[] binaryKey = null;
String HMAC_SHA1_ALGORITHM = "HmacSHA1";
String mySignature=null;
Date date = new Date();
int iTimeStamp = (int) (date.getTime() * .001);
String aszTimestamp = iTimeStamp + ""; 
String aszLocalUID = ""+aCurrentUserObjInc.getUserUID();

/**
 * calculate a local signature, using the private SecretKey set on our gigya socialize account
 * compare this local signature to the signature that was passed in the parameters in order to verify gigya socialize
 */
// baseString = timestamp + "_" + UID
String baseString = aszTimestamp + "_" + aszLocalUID;
// convert your secretKey from its BASE64 encoding to a binary array; secret key can be retrieved in your gigya partner account
// binaryKey = ConvertFromBase64(secretKey);
try {
	BASE64Decoder decoder = new BASE64Decoder();
	binaryKey = decoder.decodeBuffer(secretKey);
} catch (Exception e) {
	e.printStackTrace();
}
try {
	// get an hmac_sha1 key from the raw key bytes
	SecretKeySpec signingKey = new SecretKeySpec(binaryKey, HMAC_SHA1_ALGORITHM);
		
	// get an hmac_sha1 Mac instance and initialize with the signing key
	Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	mac.init(signingKey);
		
	// compute the hmac on input data bytes
	// binarySignature = hmacsha1(binaryKey, baseString)
	byte[] binarySignature = mac.doFinal(baseString.getBytes());
	
	// base64-encode the hmac
	// mySignature = ConvertToBase64(binarySignature);
	try {
		BASE64Encoder encoder = new BASE64Encoder();
		mySignature = encoder.encodeBuffer(binarySignature);
		//out.println("<!--Local Signature = " + mySignature + "-->");
	} catch (Exception e) {
		e.printStackTrace();
	}
} catch (Exception e) {
	throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
}
// trim the return carriage that for some reason is getting added to the end of mySignature
mySignature=mySignature.trim();
%>
<!-- ************************************* gigya socialize ************************************** -->
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?apiKey=<%=gigyaApiKey%>"></script> 

<script type="text/javascript">
				
var conf = 
{
	APIKey: '<%=gigyaApiKey%>'
			,enabledProviders: 'facebook,twitter,google,yahoo'		
}

<% 
// NEED INITIAL LINK OF ACCOUNT TO GIGYA ACCOUNT
if( 
		(aszPath.equalsIgnoreCase("/voleng2/socializedashboard.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/iwanttohelp_socialize.jsp")) 
){ %>
function printResponse(response) {      
     if ( response.errorCode == 0 ) {                   
         document.getElementById("gigyaWelcome").innerHTML = '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You have successfully linked this account with your Social Network account';
     }    
     else {    
         alert('Error :' + response.errorMessage);    
     }    
}   
$(document).ready(function() {
   gigya.services.socialize.linkAccounts(conf,link_accounts_params);
 }); 
var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
		callback: printResponse,
	signature: '<%=mySignature%>',
	sessionExpire: 0
};

 
<% }
// NEED TO NOTIFY GIGYA THAT THE USER IS LOGGED IN; THIS IS CALLED WHETHER LOGGED IN THROUGH GIGYA OR LOCAL APPLICATION
else if(aszMethodInit.equalsIgnoreCase("processLogin"))
{ %>
function printResponse(response) {  
    if ( response.errorCode == 0 ) {  
        alert('After notifyLogin');      
    }  
     else {    
         alert('Error :' + response.errorMessage);    
     }    
}  
$(document).ready(function() {
   gigya.services.socialize.notifyLogin(conf,link_accounts_params);
 }); 
var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
	signature: '<%=mySignature%>',
		callback: printResponse,
	sessionExpire: 0
};

 
<% }
// NEED TO LOG USER OUT OF GIGYA ACCOUNT AS WELL
else if(aszMethodInit.equalsIgnoreCase("logout"))
{ %>
function printResponse(response) {      
     if ( response.errorCode == 0 ) {                   
         //alert('User has logged out');    
     }    
     else {    
         alert('Error :' + response.errorMessage);    
     }    
}   
$(document).ready(function() {
   gigya.services.socialize.logout(conf,{callback:printResponse});
 }); 
var logout_params=
{
	callback: printResponse
};

<% } %>

function unlinkSocializeAccount(){
   gigya.services.socialize.unlinkAccounts(conf,{});
   alert('You have successfully unlinked this account from your Social Network login');
   volunteerDashboard();
} 

function volunteerDashboard() {
	window.location = '/volunteerdashboard.jsp'
}
</script>
