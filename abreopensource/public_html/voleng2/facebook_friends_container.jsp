<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<%@page import="java.util.List"%>

<%@page import="java.io.IOException"%>

<%//@ include file="/template_include/facebookapi_keys.inc" %>



<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

 <% 
String code = request.getParameter("code");

//String redirectUrl = "https://graph.facebook.com/oauth/access_token?client_id=137290946282487&redirect_uri=http://fycsandbox.christianvolunteering.org/&client_secret=" + appsecret + "&code=" + code + "&type=web_server";

String domain = "";
if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){
	domain = "find-your-calling";
} else if (aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" )){
	domain = "worldchanger";
} else if (aszHostID.equalsIgnoreCase( "volengfycsandbox" )){
	domain = "fycsandbox";
}
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
      //FB.Canvas.setAutoResize();
	  FB.Canvas.setSize({height: 1300});
	  
	}
	
	FB.Event.subscribe('auth.login', function(response) {
		top.location.href = "http://apps.facebook.com/<%=domain%>/personalitytest.jsp";
	});
	

 </script>
 
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<script>
	/* function share(){
	 //alert('sharing');
	 var action_links = [{'name':'Take the Personality Test','link':'http://apps.facebook.com/<%=domain%>/register.do?method=showPersonalityTest'}];
		 FB.ui(
			{
			 method: 'feed',
			 name: 'Find Your Calling',
			 link: 'http://apps.facebook.com/<%=domain%>',
			 picture: 'http://facebook.christianvolunteering.org/imgs/FYC-Logo75.gif',
			 description: 'Take a personality test and get mapped to hundreds of volunteer opportunities based on your interests.',
			 actions: action_links,
			 
			 app_id: '<%=appid%>',
			},
			function(response) {
				if (response && response.post_id) {
				 //alert('Post was published');
				} else {
				 //alert('Post was not published');
				}
			});
			
			
	 }*/
	 </script>

<script type="text/javascript">

	// Make sure the user has authorized the app
	window.onload = 
	FB.getLoginStatus(function(response) {		if(response.authResponse){
			//logged in and connected user, someone you know
			//alert('session');
	
			var exclude_ids = "";
		   
		   FB.api('/me', function(response) {
		   //alert('query');     
		   		var query = FB.Data.query('SELECT uid, name, pic_square FROM user WHERE is_app_user=1 and uid IN (SELECT uid2 FROM friend WHERE uid1 = me())');
				query.wait(function(){
					//alert(rows[0].name);
					
					FB.Array.forEach(query.value, function(row){
						var fb_uid = row.uid;
						var um_uid;
						
						exclude_ids += row.uid + ",";
						
						//alert('fb_uid' + fb_uid);
						<% if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" )){ %>
						var url =  "http://facebook.christianvolunteering.org/cms/facebook-drupal-uid/" + fb_uid;
						<% } else { %>
						var url = "http://facebook.ivolunteering.org/cms/facebook-drupal-uid/" + fb_uid;
						<% } %>
						$.get(url, 
			      			{},
				  			function(data){
				  				um_uid = data;
								//alert("data: " + data);
								if(um_uid == 'none'){
									document.getElementById('friends-outer').innerHTML +=
										'<a onclick="javascript:profileInvite(' + fb_uid + ');">' +
										'<div class="friends-inner">' +
										'<span><b>' + row.name + "</b></span><br />" +
										'<img src="' + row.pic_square + '" />' +
										'</div></a>';
								} else {
									document.getElementById('friends-outer').innerHTML +=
										'<div class="friends-inner">' +
										'<a href="/register.do?method=showFacebookTypeInfoContainer&umdst=user/' + um_uid + '&fb-theme=1&faith=0">' +
										'<span><b>' + row.name + "</b></span><br />" +
										'<img src="' + row.pic_square + '" />' +
										'</div></a>';
							 	}
				  			}
						 ); //end .get
						
						
						
					}); //end FB.Array.forEach
				}); //end query.wait
			}); //end FB.api
		  // alert(exclude_ids);
		   //document.write("</div></center>");
			//writeFriendInfo();
			

			} else { 
				//no user session available, redirect to OAuth Workflow
				//alert('no session');
				redirect();

				}
		});
		

function profileInvite(uid){
//alert('profileInvite');
//alert(uid);
var message;
	<% if( aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
		message = "A friend wants to know your personality type.  You have received an invite to take a personality test on WorldChanger to complete your profile. Taking the test is easier than ever!";
	<% } else if (aszRemoteHost.equalsIgnoreCase("fycsandbox.christianvolunteering.org")) { %>
		message = "A friend wants to know your personality type.  You have received an invite to take a personality test on WorldChanger to complete your profile. Taking the test is easier than ever!";
	<% } else { %>
		message = "A friend wants to know your personality type.  You have received an invite to take a personality test on Find Your Calling to complete your profile. Taking the test is easier than ever!";
	<% } %>

	FB.ui(
	{
  		method: 'apprequests',
		message: message,
		to: uid,
	},
	function(response) {
		//new_account();
	}
	);
}
	
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
</script>

<div id="maincontent">
 <div id="body">
<div id="friendInfo">
    <center>
	  <h5>Below is a list of your friends who use this application.  Click them to see their profile.</h5>
	  <div id="friends-outer" class="friends-outer">
	  </div>
	</center>
</div>


<div id="right-column">
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
<fb:serverfbml style="width: 776px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://facebook.ivolunteering.org/register.do?method=showFacebookFriendsContainer" method="POST" invite="true" type="WorldChanger" content="Join me on WorldChanger, where you can find out your personality type and find ministry opportunities that fit your personality! <fb:req-choice url='http://apps.facebook.com/worldchanger' label='Join'"> 
  <fb:multi-friend-selector cols="3" showborder="false" exclude_ids=exclude_ids actiontext="Invite your Facebook Friends to use WorldChanger" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml>
  
<% } else if(aszHostID.equalsIgnoreCase( "volengfycsandbox" )){ %>
   <fb:serverfbml style="width: 776px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://fycsandbox.christianvolunteering.org/register.do?method=showFacebookFriendsContainer" method="POST" invite="true" type="FYCSandbox" content="Join me on FYCSandbox, where you can find out your personality type and find ministry opportunities that fit your personality! <fb:req-choice url='http://apps.facebook.com/fycsandbox' label='Join'"> 
  <fb:multi-friend-selector cols="3" showborder="false" exclude_ids=exclude_ids actiontext="Invite your Facebook Friends to use FYCSandbox" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml>
  
<% } else { %>
<fb:serverfbml style="width: 776px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://facebook.christianvolunteering.org/register.do?method=showFacebookFriendsContainer" method="POST" invite="true" type="Find Your Calling" content="Join me on FindYourCalling, where you can find out your personality type and find ministry opportunities that fit your personality! <fb:req-choice url='http://apps.facebook.com/find-your-calling' label='Join'>">
  <fb:multi-friend-selector cols="3" showborder="false" exclude_ids=exclude_ids actiontext="Invite your Facebook Friends to use Find Your Calling" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml>
  <% } %>

</div>

</div></div>

<style type="text/css">
* {//margin:0; //padding:0;}
h4{margin-bottom:5px;}
#maincontent.sidebarless{width: 100%; backround-image:none; border:none;}
#contentwrapper {background-image:none; background-color:#FFF; width:760px;}
#pagebanner {//width:950px;}
.main_text {
padding:10px;
font-size:11px;
}
.main_text p {padding-bottom: 10px;}
#account_box table {text-align:left;}
.imgleft{float:left; width:140px; height:200px}
.imgleft2{float:left; width:365px; height:265px}
#account_box{
border:5px solid #E7E8B6; 
float:left;
margin-left:0;
width:700px;
min-height:400px;
//padding-top:10px;
}
#account_box h3 {
font-size:24px;
padding:10px 20 0;
//padding:0px 20 0px;
margin:0px;
}
#account_box h4 {
font-size:30px;
color:#1E5761;
padding:0px 35px;
//padding:10px 0 10px 10px;
margin:0px;
}
#account_box h5 {
font-size:18px;
margin:0px;
font-style:italic;
font-weight:normal;
padding-bottom:5px;
}
#account_box h6 {
font-size:14px;
padding:10px;
margin:0px;
color:#1E5761;
float:right;
}
.main_text th {
color:#1E5761;
float:left;
font-size:14px;
}
#account_box h4 .main_text { //font-weight:normal; //color:#000;}
#links {text-align:center;}
.main_text td {width: 400px;}
#characteristics, #ministrystrengths, #ministryWeaknesses, #growthpath, #notablepeople {width:400px; padding:10px 10px 0 0; float:left;}
#left-column {//float:left; //width:400px;}
#LookingFor, #ServiceAreas, #ministrySkills, #ministryInterests {padding:10px 10px 0 0;}
a { color:#000000; font-weight:inherit;}
#footer {padding:0px;}

#friends-outer {
overflow-y:auto;
width:700px;
//min-height:80px;
//height:240px;
}
#friends-outer .friends-inner {
width:55px;
height:100px;
float:left;
padding:7px;
}
.friends-inner span img{
padding:2px;
border:1px solid #E0E0E0;
}
</style>
<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />

<div style="clear:both;"></div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->