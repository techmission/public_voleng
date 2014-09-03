
<div id="fb-root"></div>
<% if(! aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp") ){ %>

<!-- asyncronous include of  <script src="http://connect.facebook.net/en_US/all.js"></script> -->

<script type="text/javascript">
window.fbAsyncInit = function() {//onload = function() {//
  FB.init({appId: '<%=tempappid%>', status: false, cookie: true,
           xfbml: true, oauth  : true});
  fbApiInitialized = true;
afterInit();

 
// whenever the user logs in, we tell our login service
// commented out so that our system does not automatically log them in and redirect the user to a different page. 
// 			may want to re-enable, and just take the user back to the page he/she came from, rather than the dashboard
//    FB.Event.subscribe('auth.login', function() {
//       window.location = "<%=request.getContextPath()%>/register.do?method=processFacebookConnectClick";
//    });
    
    FB.getLoginStatus(function(response){
    	var loginElement = document.getElementById('fb-login-li');
    	var facebookElement = document.getElementById('facebookConnectLinks');
    	if(response.status == 'connected'){
    		//alert('connected');
    		if(loginElement){
    		//alert('');
    			loginElement.innerHTML = 
    			'<a href="<%=request.getContextPath()%>/register.do?method=processFacebookConnectClick"><img src="http://www.christianvolunteering.org/imgs/facebook_connect_button.gif"></a>';
    		}
    	} else {
    		//alert('not connected');
    		if(loginElement){
    			loginElement.innerHTML = 
    			'<fb:login-button scope="email">Login/Register with Facebook</fb:login-button>';
    		}
    		
    		FB.XFBML.parse(document.getElementById('fb-login-li'));
    		if(facebookElement){
    			//facebookElement.innerHTML = 
    			//'<a href="<%=aszPortal%>/register.do?method=processFacebookConnectClick">Link to Facebook Account</a>';
    		}
    	}
    });
	
	
	
};
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
function fbEnsureInit(callback) {
    if (!window.fbApiInitialized) {
        setTimeout(function() { fbEnsureInit(callback); }, 50);
    } else {
        if (callback) { callback(); }
    }
}
function afterInit(){
	return;
}  
  


  
</script>

<% } %>