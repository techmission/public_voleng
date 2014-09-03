<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information login required  -->
<%//@ include file="/template_include/facebookapi_init.inc" %>

<% out.print("<!-- Portal:" +  request.getParameter("portal") + " -->"); %>


<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.*" %>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.util.Enumeration"%>

<%//@ include file="/template_include/facebookapi_keys.inc" %>


<% /*IndividualsBRLO aIndivObj = new IndividualsBRLO();
	String signed_request = request.getParameter("signed_request");
	out.print("signed_request: " + request.getParameter("signed_request"));
	JSONObject jsonObj = aIndivObj.decodeFacebookSignedRequest(signed_request, appsecret);
	
//String payload = "{\"algorithm\":\"HMAC-SHA256\",\"expires\":1296086400,\"issued_at\":1296082370,\"oauth_token\":\"137290946282487|2.2PjqKQCFHp5tMRwX25onEw__.3600.1296086400-100001012198473|W4cH-6QLjT_15bV-YlmXqYeWsxQ\",\"user\":{\"locale\":\"en_US\",\"country\":\"us\"},\"user_id\":\"100001012198473\"}";
//JSONObject jsonObj = new JSONObject(payload);

out.println("algorithm: " + jsonObj.getString("algorithm"));
out.println("expires: " + jsonObj.getInt("expires"));
out.println("oauth_token: " + jsonObj.getString("oauth_token"));
out.println("user_id: " + jsonObj.getString("user_id"));

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
*/

Date today ; 
long localUnixTime;
today = new java.util.Date();
localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
if(appapikey!=null && appsecret!=null){
	if(	aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || 
		aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || 
		aszHostID.equalsIgnoreCase( "volengfycsandbox" ) 
	){ 
		




		if(session.getAttribute("facebookapikey")==null){
			session.setAttribute("facebookapikey",appapikey);
		}else if(session.getAttribute("facebookapikey").toString().length()<1){
			session.setAttribute("facebookapikey",appapikey);
		}
		if(session.getAttribute("facebooksecretkey")==null){
			session.setAttribute("facebooksecretkey",appsecret);
		}else if(session.getAttribute("facebooksecretkey").toString().length()<1){
			session.setAttribute("facebooksecretkey",appsecret);
		}

		String sessionKey = "";
		String expireTime = "";
		if(session.getAttribute("FB_session_key")!=null){
			sessionKey = (String) session.getAttribute("FB_session_key");
		}
		if(session.getAttribute("FB_session_key_expire")!=null){
			expireTime = (String) session.getAttribute("FB_session_key_expire");
		}
		long expireLong=0;
		try{
			expireLong = Long.parseLong(expireTime.trim());
		}catch(NumberFormatException nfe){
		}
		long tempLong = localUnixTime+3600;
//		out.print("<br><br><br><br><br><br><br><br>current time: " +localUnixTime+"<BR>time with padding: " +tempLong+"<BR>expiretime: " +expireLong+"<BR>");
//		out.print("<!--current time: " +localUnixTime+"<BR>time with padding: " +tempLong+"<BR>expiretime: " +expireLong+"-->");
		
		
		boolean needsSessionKey=false;
		if(sessionKey == null){
			needsSessionKey=true;
//			out.print("null");
		} else if(sessionKey.length()<1){
			needsSessionKey=true;
//			out.print("length less than 1");
		} else if( (localUnixTime+3600) > expireLong){
			needsSessionKey=true;
//			out.print("time is passed");
		}else{
//			out.print("none of the above triggered");
		}
		
		if(session.getAttribute("FB_session_key") == null ){
//			out.print("<br>null session key<br>");
			session.setAttribute("FB_session_key","");  // clearing Value in session Object
			session.setAttribute("FB_session_key_expire","");  // clearing Value in session Object
		}else{
//			
			if( (localUnixTime+3600) > expireLong){ // if the current server time + 5 hours (GMT) is later than expire, then grab a new sessionkey
				/*String AsessionKey = request.getParameter(FacebookParam.SESSION_KEY.toString()); // Session Key passed as request parameter
				String ASexpireTime = request.getParameter(FacebookParam.EXPIRES.toString());		// Session Key expire time passed as request parameter
//				out.print("<!--current time is past expired-->");
				if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
//					out.print("  worldchanger");
				} else if(aszHostID.equalsIgnoreCase("volengfycsandbox")){
//					out.print("  sandbox test");
				} else {
//					out.print("  find-your-calling");
				}*/
			}
		}
		String frameURL = "";
		
		//out.print("Session Key: " + sessionKey);
		//out.print("Session var: " + session.getAttribute("FB_session_key"));
		
	}
} 
%>

<%
//out.print("Session vars: ");
/*Enumeration keys = session.getAttributeNames();
while (keys.hasMoreElements())
{
  String key = (String)keys.nextElement();
  //out.println(key + ": " + session.getValue(key) + "<br>");
}*/
%>

<%
//out.println("request vars: ");
/*Enumeration keys2 = request.getParameterNames();
while (keys2.hasMoreElements())
{
  String key = (String)keys2.nextElement();
  //out.println(key + ": " + request.getParameter(key) + "<br>");
}*/
%>



<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<% 
String code = request.getParameter("code");

//String redirectUrl = "https://graph.facebook.com/oauth/access_token?client_id=" + appid + "&redirect_uri=http://fycsandbox.christianvolunteering.org/&client_secret=" + appsecret + "&code=" + code + "&type=web_server";
%>
<div id="fb-root"></div>
 <script src="http://connect.facebook.net/en_US/all.js"></script>
 <script>
   FB.init({
     appId  : '<%=appid%>',
     status : true, // check login status
     cookie : true, // enable cookies to allow the server to access the session
     xfbml  : true,  // parse XFBML
	 oauth  : true
   });
   
   window.fbAsyncInit = function(){
      //alert('init');
      FB.Canvas.setAutoGrow();
	  //FB.Canvas.setSize({height: 1700});
	  
	}
	
	
	
	//FB.Event.subscribe('auth.login', function(response) {
	//	top.location.href = "http://apps.facebook.com/fycsandbox/personalitytest.jsp";
	//});
 </script>



<script type="text/javascript">



	// Make sure the user has authorized the app
	window.onload = FB.getLoginStatus(function(response) {
		if(response.authResponse){
			//logged in and connected user, someone you know
			//alert('session');
			//getAccessToken(); //don't need an access token on this page...
			//alert(window.location.host);
			//if(window.location.host == 'fycsandbox.christianvolunteering.org'){
			<% //String redirect = request.getParameter("redirect");
				//if((redirect != null) && ! (redirect.equals(""))){
					//if (redirect.equalsIgnoreCase("true")){ %>
						//top.location.href = "http://apps.facebook.com/fycsandbox/personalitytest.jsp";
			<% 		//} 
				//}			
			%>
			var uid = FB.getSession().uid;
		//alert(uid);
		    document.forms["individualForm"].elements["facebookuid"].value = uid;
			
		} else { 
			//no user session available, redirect to OAuth Workflow
			//alert('no session');
			redirect();
	/*FB.ui(
   		{
    	 method: 'oauth',
    	 scope: 'email',
		 client_id: '<%=appid%>',
		 response_type: 'code',
		 redirect_uri: 'http://fycsandbox.christianvolunteering.org/register.do?method=showPersonalityTest',
		 display: 'iframe',
  		},
   		function(response) {
     		if (response && response.code) {
      		 alert('Permission acquired');
     		} else if (response && response.error){
     		  alert('Error authorizing: ' + response.errorDescription);
    		} else {
	 			alert('Failed');
			}
   		});*/


		}});
	
	function redirect(){
		<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>		&redirect_uri=http://facebook.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://facebook.ivolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
		top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/register.do?method=showFacebookAuthRedirect&redirect=true&scope=email";
	<% } %>
		//top.location.href = "http://graph.facebook.com/oauth/authorize?client_id=<%=appid%>&redirect_uri=http://fycsandbox.christianvolunteering.org/ministryopportunities.jsp?redirect=true&scope=email";
	}
	
	function getAccessToken(){
		//alert('get access token');
	
		<% /*String accessToken = ""; 
		try{
			URL url = new URL(redirectUrl);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			BufferedInputStream in = new BufferedInputStream(stream);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			//BufferedOutputStream buffOut = new BufferedOutputStream(output);
			int i;
			while ((i = in.read()) != -1){
				//buffOut.write(i);
				output.write(i);
			}
			accessToken = output.toString();
			//buffOut.flush();
			}
			catch(IOException e){
				
			}*/
		%>
		//alert('');
	}
</script>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.cv.org:7001" )){ %>
<div id="chrisvol_nav">
<a href="<%=request.getContextPath()%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
<a href="<%=request.getContextPath()%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>&nbsp;&nbsp;|&nbsp;
<a href="http://www.urbanministry.org/redirect-home" class="level-1"><span>My City</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/about.jsp" class="level-1"><span>About</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>

</div>
<br>
<% } %>
<!-- end navigation information -->
</div>

<%
int vidPersonalityType = 336;

String aszPersonalityVID = vidPersonalityType + "";

ArrayList aPersonalityList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );

aCodes.getTaxonomyCodeList( aPersonalityList, vidPersonalityType );

%>


<div id="pagebanner">
<span style="float: left;">Personality Test</span>
</div>

<%@ include file="/template_include/personality_test_progress_bar.inc" %>

<br />
<div id="body">




<div id="form">
    
<div id="description">
<p><h3>Directions: Please answer the following questions as carefully, honestly, and quickly as possible. 
Remember there are no right answers, only your best answers.  We will use the results of this test to recommend ministry areas 
and then provide specific  suggestions from our directory of over 5,000 ministry opportunities.</h3></p><br />
</div><br />

<html:form method="post" action="/register.do">
<html:hidden property="method" value="showPersonalityTest2" />
<html:hidden property="personalitypageno" value="1" />
<html:hidden property="personalitytypei" value="0" />
<html:hidden property="personalitytypee" value="0" />
<html:hidden property="personalitytypes" value="0" />
<html:hidden property="personalitytypen" value="0" />
<html:hidden property="personalitytypef" value="0" />
<html:hidden property="personalitytypet" value="0" />
<html:hidden property="personalitytypej" value="0" />
<html:hidden property="personalitytypep" value="0" />
<html:hidden property="numberofquestions" value="13" />
<html:hidden property="personalitytype" value="" />
<html:hidden property="personalitytypetid"  value="0" />
<html:hidden property="facebookuid" value="" />

<h3>Already know your Myers-Briggs Type?  Select: </h3><br />
<select name="personalityTypeDrop">
<% 

int iArraySize = aPersonalityList.size();

for(int index=0; index < aPersonalityList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPersonalityList.get(index);
							if(null == aAppCode) continue;
							int iTid = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.print("<option name=\"" + aAppCode.getAPCIdSubType() + 
								"\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							out.print(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
%>
</select>


<button type="button" value="submitType" name="submitType" onclick="skip()">Skip to Next Step</button>


<table>
<tr>
	<td class="set" colspan="2">1. <b>When you come to a new situation, you usually: </b></td> 
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="e" name="1" /> 
	          	try it right away, and learn from doing
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="i" name="1" />
        	  	like to watch first and try it later
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set" colspan="2">2. <b>Do you think people should be more:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="s" name="2" /> 
	          	sensible and practical
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="n" name="2" />
        	  	imaginative and inspired
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">3.<b> When you come to an uncertain situation: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="f" name="3" /> 
	          	you usually trust your feelings more
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="t" name="3" />
        	  	you usually trust your thinking more
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">4. <b> Do you prefer when things are:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="j" name="4" /> 
	          	planned and structured
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="p" name="4" />
        	  	spontaneous and unplanned
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">5.<b> Do you spend most of your time:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="e" name="5" /> 
	          	often in bigger groups and seldom alone
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="i" name="5" />
        	  	in smaller groups or alone
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">6. <b> It is better to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="s" name="6" /> 
	          	be able to accept what is
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="n" name="6" />
        	  	try to change things
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">7. <b>Is it worse to do:  </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="f" name="7" />  
	          	mean things
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="t" name="7" /> 
        	  	unfair things
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">8. <b> When it comes to decisions: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="j" name="8" /> 
	          	you usually make them quickly and easily
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="p" name="8" /> 
        	  	you usually have trouble making up your mind
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">9. <b> After a day spent with a lot of people do you: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="e" name="9" /> 
	          	feel energized and stimulated
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="i" name="9" />
        	  	feel drained and like being alone
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">10. <b> When you need to get something important done, you prefer to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="s" name="10" /> 
	          	do it the way that has worked before
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="n" name="10" />
        	  	do it a new way you just thought of
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">11. <b> Which is a bigger compliment: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="f" name="11" />
	          	&quot;he/she is really nice&quot;
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="t" name="11" />
        	  	&quot;he/she is really smart&quot;
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">12. <b> When it comes to time, are you more likely to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="j" name="12" /> 
	          	usually be on time
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="p" name="12" />
        	  	be pretty flexible
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">13. <b> When you are in a group do you usually: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="e" name="13"/>  
	          	do a lot of the talking
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="i" name="13"/> 
        	  	listen and talk only a little
          </dt>
        <br>
    </td>
</tr>
  
<!--
 -->

</table>
  <button type="button" value="Submit" name="Submit" onClick="tallyScores()">Next</button>
   <!-- <input type="submit" value="Submit" /> -->
  </html:form>

</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>
</div>



<script type="text/javascript" src="<%=request.getContextPath()%>/template_include/js/process_personality.js"></script>

<% // for google analytics tracking: %>
<% 
String aszGoalPage;
aszGoalPage = "/personalitytest/1";
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

