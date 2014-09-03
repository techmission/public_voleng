<% if(!(
			aszPath.equalsIgnoreCase("/socialize.jsp") ||
	aszPath.equalsIgnoreCase("/socialize-login.jsp") ||
	aszPath.equalsIgnoreCase("/createaccount.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/socialize_createaccount.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/login1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/loginstatus.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/logintest.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test2.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test3.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test4.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test5.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_test6.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/personality_my_results.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volacctedit1.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp") ||
	aszLoggedIn.equalsIgnoreCase("true") 
)){ %>
<!-- ************************************* gigya socialize ************************************** -->
		<% if ( aszRemoteHost.contains("localhost") ){ %>
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?apiKey=apikey"></script> 
		<% }else  if ( aszRemoteHost.contains("cv.org:7001") ){ %>
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?apiKey=apikey"></script> 
		<% }else{ %>
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?apiKey=apikey"></script> 
		<% } %>

<script type="text/javascript">
				
var conf = 
{
<% if(aszRemoteHost.contains("localhost")){ %>
	APIKey: 'APIKey'
			,enabledProviders: 'facebook,twitter,openid'
<% }else  if ( aszRemoteHost.contains("cv.org:7001") ){ %>
	APIKey: 'APIKey'
			,enabledProviders: 'facebook,twitter,openid'
<% }else{ %>
	APIKey: 'APIKey'
			,enabledProviders: 'facebook,twitter,google,yahoo'
<% } %>			
}

</script>

<div id="gigya" align="middle">
<div id="divConnect"></div>  
<script type="text/javascript">
var login_params=
{
	headerText: '     login with:',
	redirectURL: '<%=aszPortal%>/socialize.jsp'
	,showTermsLink: 'false'
	,hideGigyaLink: 'true'
	,height: '55'
	,width: '150'
	,containerID: 'divConnect'
	,UIConfig: '<config><body><controls><snbuttons buttonsize="20"></snbuttons></controls><background frame-color="Transparent"></background></body></config>'
	,useFacebookConnect: 'true'
}
</script>
<script type="text/javascript">
   gigya.services.socialize.showLoginUI(conf,login_params);
</script>

</div>
<% } %>


