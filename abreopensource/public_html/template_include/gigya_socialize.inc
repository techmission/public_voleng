<!-- /template_include/gigya_linkAccounts.inc -->
<jsp:useBean id="UserSessionBeanInc" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%@ page import="java.io.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="java.security.SignatureException" %>
<%@ page import="javax.crypto.Mac" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>

<%@ include file="/template_include/gigya_socialize_keys.inc" %>

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
 * compare this local signature to the signature that was passed in the eters in order to verify gigya socialize
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
		(aszPath.equalsIgnoreCase("/voleng2/socialize_create_account2.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/iwanttohelp_socialize.jsp")) 
){ %>

function printResponse(response) {      
	 if ( response.errorCode == 0 ) {                   
         //alert('User account has been linked to gigya account');    
     }    
     else {    
         //alert('Error :' + response.errorMessage);    
     }    
}   

function printResponseUserInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var user = response['user'];
        var userUID = String(user['UID']);
		if(user['UID'].length > 3){
			userUID = userUID.substr(0,4);
		}
		if(userUID == "_gid"){
			document.getElementById("gigyaError").innerHTML = '<br>&nbsp;&nbsp;&nbsp;&nbsp;Error:&nbsp;&nbsp;This account may have already been linked with a different ' + user['loginProvider'] + ' account';
		}else if(user){
			document.getElementById("gigyaWelcome").innerHTML = '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You have successfully linked this account with your ' + user['loginProvider'] + ' account';
		}
    } else {
        //alert('Error :' + response.errorMessage);
    }  
}


<% 
// NEED INITIAL LINK OF ACCOUNT TO GIGYA ACCOUNT
if( !
		(aszPath.equalsIgnoreCase("/voleng2/socialize_create_account2.jsp")) 
){ %>

$(document).ready(function() {
   gigya.services.socialize.linkAccounts(conf,link_accounts_params);
gigya.services.socialize.getUserInfo(conf,{callback:printResponseUserInfo});
 }); 
<% } %>

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
else if( aszMethodInit.equalsIgnoreCase("processLogin") && (!(aszPath.equalsIgnoreCase("/voleng2/volunteerdashboard.jsp"))))
{ %>
function printResponse(response) {  
    if ( response.errorCode == 0 ) {  
        //alert('After notifyLogin');      
    }  
     else {    
         //alert('Error :' + response.errorMessage);    
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
         //alert('Error :' + response.errorMessage);    
     }    
}   
$(document).ready(function() {
   gigya.services.socialize.logout(conf,{callback:printResponse});
 }); 
var logout_s=
{
	callback: printResponse
};

<% } 
// NEED INITIAL LINK OF ACCOUNT TO GIGYA ACCOUNT
else if( 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_unlinkaccount.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_unlinkaccount.jsp")) 
){ %>

function printResponse(response) {      
	 if ( response.errorCode == 0 ) {                   
     }    
     else {    
         //alert('Error :' + response.errorMessage);    
     }    
}   

var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
		callback: printResponse,
	signature: '<%=mySignature%>',
	sessionExpire: 0
};

var edit_connections_ui_params=
{
	width: 350,
	height: 400,
	containerID: 'gigyaEditConnectionsUI',
	showCaption: true,
	captionText: 'Manage Social Network Connections',
	showTermsLink: false,
	hideGigyaLink: true,
	enabledProviders: 'facebook,twitter,google,yahoo,myspace'

};

//gigya.services.socialize.notifyLogin(conf,link_accounts_params);
gigya.services.socialize.showEditConnectionsUI(conf,edit_connections_ui_params);

function unlinkSocializeAccount(){
   //gigya.services.socialize.unlinkAccounts(conf,{});
   //alert('You have successfully unlinked this account from your Social Network login');
   //volunteerDashboard();
} 

function volunteerDashboard() {
	window.location = '<%=aszPortal%>/volunteerdashboard.jsp'
}





<% } 
// DECIDE whether or not to show the links for managing gigya account.  Should show in the case of this being a legit gigya user
else if( 
		(aszPath.equalsIgnoreCase("/voleng2/volunteerdashboard.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/volunteerdashboard.jsp")) 
){ %>

function printResponse(response) {      
	 if ( response.errorCode == 0 ) {                   
     }    
     else {    
         //alert('Error :' + response.errorMessage);    
     }    
}   

function printResponseUserInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var user = response['user'];
		//alert(response['user']['providers']);
		var providers = String(response['user']['providers']);
		if(providers.length>1){
			var strUnlink = '<a href=\"<%=aszPortal%>/register.do?method=showUnlinkSocialize\">Manage Social Networking</a>\n\n';
			document.getElementById("socializeLinks").innerHTML = strUnlink;
		}
    }
    else {
        //alert('Error :' + response.errorMessage);
    }  
}

var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
		callback: printResponse,
	signature: '<%=mySignature%>',
	sessionExpire: 0
};


gigya.services.socialize.notifyLogin(conf,link_accounts_params);
gigya.services.socialize.getUserInfo(conf,{callback:printResponseUserInfo});


<% } 
// NEED INITIAL LINK OF ACCOUNT TO GIGYA ACCOUNT
else if( 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_unlinkaccount.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_data.jsp")) 
){ %>

function printResponse(response) {      
	 if ( response.errorCode == 0 ) {                   
     }    
     else {    
         //alert('Error :' + response.errorMessage);    
     }    
}   



function printResponseUserInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var user = response['user'];
        var msgUserInfo = '<pre>User '+user['nickname'] + '\n\nFirst Name: ' +user['firstName'] + '\n\nLast Name: ' +user['lastName'] + '\n\n\nPhoto can be seen at:\n   ' + user['photoURL']+'\n\n<img border=0 src=\"' + user['photoURL']+'\"></pre>';
		document.getElementById("gigyainfo").innerHTML=msgUserInfo;
        
		var strUserInfo = '<pre>User Information:\n\n';
		for(prop in user){
			strUserInfo+=prop + " value: "+user[prop]+ "\n";
		}
		strUserInfo += '</pre>\n\n';
		if(strUserInfo){
				document.getElementById("gigyaUserInfo").innerHTML = strUserInfo;
		}
        
		
		var strUserIntro = 'You have successfully logged in with Gigya Socialize through your '+user['loginProvider']+' account<pre>\n</pre>';
		strUserIntro += 'This ChristianVolunteering user account is set up on Gigya to work with the following providers: '+user['providers']+'\n\n';
		document.getElementById("gigyaIntro").innerHTML = strUserIntro;
		
		var users = user['providers'];
        
		var identities = user['identities'];
		var identitiesList = String(identities).split(',');//split on commas
		var strUserIdentities = '<pre>(User Identities Object:)\n';
		var tempUsers=users;
		for(prop in identities){
			strUserIdentities+=prop + " value: "+identities[prop]+ "\n";
		}
		strUserIdentities += '</pre>\n\n';
		if(strUserIdentities){
				document.getElementById("gigyaUserIdentities").innerHTML = strUserIdentities;
		}
		
		var strUserIdentities2 = '<pre><u>Providers\' identities Object:</u>\n';
		var identities2 = user['identities']['facebook'];
		strUserIdentities2+='\n\n<b>Facebook:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['twitter'];
		strUserIdentities2+=prop + '\n\n<b>Twitter:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['google'];
		strUserIdentities2+=prop + '\n\n<b>Google:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['yahoo'];
		strUserIdentities2+=prop + '\n\n<b>Yahoo:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		strUserIdentities2 += '</pre>\n\n';
		if(strUserIdentities2){
				document.getElementById("gigyaUserIdentities2").innerHTML = strUserIdentities2;
		}
		
        
		var capabilities = user['capabilities'];
		var strUserCapabilities = '<pre>(User Capabilities Object:)\n';
		for(prop in capabilities){
			strUserCapabilities+=prop + " value: "+capabilities[prop]+ "\n";
		}
		strUserCapabilities += '</pre>\n\n';
		if(strUserCapabilities){
				document.getElementById("gigyaUserCapabilities").innerHTML = strUserCapabilities;
		}
        
		var strUserInfo2 = '<pre>(User Info Object:)\n';
		var obj = response;
		for(prop in obj){
			strUserInfo2+=prop + " value: "+obj[prop]+ "\n";
		}
		strUserInfo2 += '</pre>\n\n';
		if(strUserInfo2){
				document.getElementById("gigyaUserInfo2").innerHTML = strUserInfo2;
		}
    }
    else {
        //alert('Error :' + response.errorMessage);
    }  
}

var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
		callback: printResponse,
	signature: '<%=mySignature%>',
	sessionExpire: 0
};

var friend_selector_params=
{
	width: 400,
	height: 600,
	containerID: 'gigyaFriends',
	showCaption: true,
	captionText: 'Social Network Friends',
	showEditLink: true
//	,okButtonText: ''
};


gigya.services.socialize.notifyLogin(conf,link_accounts_params);
gigya.services.socialize.getUserInfo(conf,{callback:printResponseUserInfo});
gigya.services.socialize.showFriendSelectorUI(conf,friend_selector_params);


function printResponseSessionInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var authToken = response['authToken'];
        var IV = response['IV'];
        var sessionExpiration = response['sessionExpiration'];
        
        var sessionHandle = response['sessionHandle'];
        var tokenExpiration = response['tokenExpiration'];
        var tokenSecret = response['tokenSecret'];
        
        var msgSessionInfo = 'authToken : ' +authToken + '\n';
        msgSessionInfo+= 'IV : ' +IV + '\n';            
        msgSessionInfo+= 'sessionExpiration : ' +sessionExpiration + '\n';          
        msgSessionInfo+= 'sessionHandle : ' +sessionHandle + '\n';  
        msgSessionInfo+= 'tokenExpiration : ' +tokenExpiration + '\n';  
        msgSessionInfo+= 'tokenSecret : ' +tokenSecret + '\n';  
        
			if(msgSessionInfo){
				document.getElementById("gigyaSessionInfo").innerHTML = msgSessionInfo;
			}
    }
    else {
        //alert('Error :' + response.errorMessage);
    }
}

//var s2 = {
//    : 'facebook',
//    : 'twitter',
//    callback:printResponseSessionInfo
//};

//gigya.services.socialize.getSessionInfo(conf,s2);





function printResponseFriends(response) {  
    if ( response.errorCode == 0 ) {             
        var myFriends = response['friends'].asArray();          
        if ( null!=myFriends && myFriends.length>0) {        
            var msgFriends = '<table border =1><tr><td colspan=\"3\">' + response['context']['myFriendsTitle'] + '</td></tr>';        
            for (var index in myFriends) {
                var currFriend = myFriends[index];
                msgFriends += '<tr><td> <img height=90 width=90 src=\"'+ currFriend['photoURL']+ '\"></td><td>'+currFriend['nickname']+'</td><td><a href='+ currFriend['photoURL']+ '>'+ currFriend['photoURL']+ '</a></td><td>isSiteUser: '+ currFriend['isSiteUser']+ '</a></td></tr>' ;                 
            }            
			msgFriends = msgFriends + '</table>';
			if(msgFriends){
				document.getElementById("gigyafriends").innerHTML = msgFriends;
			}
        }
        else {
            alert('No friends were returned for this user');
        }
    }
    else {
        //alert('Error :' + response.errorMessage);
    }
}

var context = {
        myFriendsTitle : 'My friends are:'
};

var paramsFriends = {
    detailLevel:'extended',
    context:context, 
    //UIDs:'<%=aszLocalUID%>,_gid_1FyT4hKPcD+oCM121MM+Q==',
    callback:printResponseFriends
};

gigya.services.socialize.getFriendsInfo(conf,paramsFriends);




function unlinkSocializeAccount(){
   gigya.services.socialize.unlinkAccounts(conf,{});
   alert('You have successfully unlinked this account from your Social Network login');
   volunteerDashboard();
} 

function volunteerDashboard() {
	window.location = '/volunteerdashboard.jsp'
}

















































<% } 
// PAGE THE USER SEES TO MANAGE THEIR SOCIALIZE ACCOUNT
else if( 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_manage.jsp")) || 
		(aszPath.equalsIgnoreCase("/voleng2/socialize_data.jsp")) 
){ %>

function printResponse(response) {      
	 if ( response.errorCode == 0 ) {                   
     }    
     else {    
         //alert('Error :' + response.errorMessage);    
     }    
}   



function printResponseUserInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var user = response['user'];
        var msgUserInfo = '<pre>User '+user['nickname'] + '\n\nFirst Name: ' +user['firstName'] + '\n\nLast Name: ' +user['lastName'] + '\n\n\nPhoto can be seen at:\n   ' + user['photoURL']+'\n\n<img border=0 src=\"' + user['photoURL']+'\"></pre>';
		document.getElementById("gigyainfo").innerHTML=msgUserInfo;
        
		var strUserInfo = '<pre>User Information:\n\n';
		for(prop in user){
			strUserInfo+=prop + " value: "+user[prop]+ "\n";
		}
		strUserInfo += '</pre>\n\n';
		if(strUserInfo){
				document.getElementById("gigyaUserInfo").innerHTML = strUserInfo;
		}
        
		
		var strUserIntro = 'You have successfully logged in with Gigya Socialize through your '+user['loginProvider']+' account<pre>\n</pre>';
		strUserIntro += 'This ChristianVolunteering user account is set up on Gigya to work with the following providers: '+user['providers']+'\n\n';
		document.getElementById("gigyaIntro").innerHTML = strUserIntro;
		
		var users = user['providers'];
        
		var identities = user['identities'];
		var identitiesList = String(identities).split(',');//split on commas
		var strUserIdentities = '<pre>(User Identities Object:)\n';
		var tempUsers=users;
		for(prop in identities){
			strUserIdentities+=prop + " value: "+identities[prop]+ "\n";
		}
		strUserIdentities += '</pre>\n\n';
		if(strUserIdentities){
				document.getElementById("gigyaUserIdentities").innerHTML = strUserIdentities;
		}
		
		var strUserIdentities2 = '<pre><u>Providers\' identities Object:</u>\n';
		var identities2 = user['identities']['facebook'];
		strUserIdentities2+='\n\n<b>Facebook:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['twitter'];
		strUserIdentities2+=prop + '\n\n<b>Twitter:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['google'];
		strUserIdentities2+=prop + '\n\n<b>Google:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		identities2 = user['identities']['yahoo'];
		strUserIdentities2+=prop + '\n\n<b>Yahoo:</b>\n';
		for(prop in identities2){
			strUserIdentities2+=prop + " value: "+identities2[prop]+ "\n";
		}
		strUserIdentities2 += '</pre>\n\n';
		if(strUserIdentities2){
				document.getElementById("gigyaUserIdentities2").innerHTML = strUserIdentities2;
		}
		
        
		var capabilities = user['capabilities'];
		var strUserCapabilities = '<pre>(User Capabilities Object:)\n';
		for(prop in capabilities){
			strUserCapabilities+=prop + " value: "+capabilities[prop]+ "\n";
		}
		strUserCapabilities += '</pre>\n\n';
		if(strUserCapabilities){
				document.getElementById("gigyaUserCapabilities").innerHTML = strUserCapabilities;
		}
        
		var strUserInfo2 = '<pre>(User Info Object:)\n';
		var obj = response;
		for(prop in obj){
			strUserInfo2+=prop + " value: "+obj[prop]+ "\n";
		}
		strUserInfo2 += '</pre>\n\n';
		if(strUserInfo2){
				document.getElementById("gigyaUserInfo2").innerHTML = strUserInfo2;
		}
    }
    else {
        //alert('Error :' + response.errorMessage);
    }  
}

var link_accounts_params=
{
	siteUID: <%=aszLocalUID%>,
	timestamp: <%=aszTimestamp%>,
		callback: printResponse,
	signature: '<%=mySignature%>',
	sessionExpire: 0
};

var edit_connections_ui_params=
{
	width: 400,
	height: 600,
	containerID: 'gigyaEditConnectionsUI',
	showCaption: true,
	captionText: 'Manage Social Network Connections',
	showTermsLink: false
};


gigya.services.socialize.notifyLogin(conf,link_accounts_params);
gigya.services.socialize.getUserInfo(conf,{callback:printResponseUserInfo});
gigya.services.socialize.showEditConnectionsUI(conf,edit_connections_ui_params);


function printResponseSessionInfo(response) {  
    if ( response.errorCode == 0 ) {             
        var authToken = response['authToken'];
        var IV = response['IV'];
        var sessionExpiration = response['sessionExpiration'];
        
        var sessionHandle = response['sessionHandle'];
        var tokenExpiration = response['tokenExpiration'];
        var tokenSecret = response['tokenSecret'];
        
        var msgSessionInfo = 'authToken : ' +authToken + '\n';
        msgSessionInfo+= 'IV : ' +IV + '\n';            
        msgSessionInfo+= 'sessionExpiration : ' +sessionExpiration + '\n';          
        msgSessionInfo+= 'sessionHandle : ' +sessionHandle + '\n';  
        msgSessionInfo+= 'tokenExpiration : ' +tokenExpiration + '\n';  
        msgSessionInfo+= 'tokenSecret : ' +tokenSecret + '\n';  
        
			if(msgSessionInfo){
				document.getElementById("gigyaSessionInfo").innerHTML = msgSessionInfo;
			}
    }
    else {
        //alert('Error :' + response.errorMessage);
    }
}

//var s2 = {
//    : 'facebook',
//    : 'twitter',
//    callback:printResponseSessionInfo
//};

//gigya.services.socialize.getSessionInfo(conf,s2);





function printResponseFriends(response) {  
    if ( response.errorCode == 0 ) {             
        var myFriends = response['friends'].asArray();          
        if ( null!=myFriends && myFriends.length>0) {        
            var msgFriends = '<table border =1><tr><td colspan=\"3\">' + response['context']['myFriendsTitle'] + '</td></tr>';        
            for (var index in myFriends) {
                var currFriend = myFriends[index];
                msgFriends += '<tr><td> <img height=90 width=90 src=\"'+ currFriend['photoURL']+ '\"></td><td>'+currFriend['nickname']+'</td><td><a href='+ currFriend['photoURL']+ '>'+ currFriend['photoURL']+ '</a></td><td>isSiteUser: '+ currFriend['isSiteUser']+ '</a></td></tr>' ;                 
            }            
			msgFriends = msgFriends + '</table>';
			if(msgFriends){
				document.getElementById("gigyafriends").innerHTML = msgFriends;
			}
        }
        else {
            alert('No friends were returned for this user');
        }
    }
    else {
        //alert('Error :' + response.errorMessage);
    }
}

var context = {
        myFriendsTitle : 'My friends are:'
};

var paramsFriends = {
    detailLevel:'extended',
    context:context, 
    //UIDs:'<%=aszLocalUID%>,_gid_1FyT4hKPcD+oCM121MM+Q==',
    callback:printResponseFriends
};

gigya.services.socialize.getFriendsInfo(conf,paramsFriends);

<% } %>


</script>
