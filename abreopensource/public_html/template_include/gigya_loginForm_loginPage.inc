<%@ include file="/template_include/gigya_socialize_keys.inc" %>
<!-- ************************************* gigya socialize ************************************** -->
<script type="text/javascript" lang="javascript" src="http://cdn.gigya.com/JS/gigya.js?apiKey=<%=gigyaApiKey%>"></script> 

<script type="text/javascript">
				
var conf = 
{
	APIKey: '<%=gigyaApiKey%>'
			,enabledProviders: 'facebook,twitter,google,yahoo'		
}

</script>
<style>
#divConnect{
<% if(aszPath.equalsIgnoreCase("/voleng2/create_account1.jsp")) { %>
display: none;
<% }else{ %>
padding-left: 90px;
}
<% } %>
</style>
<!--div id="gigya" align="center"-->
<div id="divConnect" align="center"></div>  


<script type="text/javascript">
var login_params=
{
//	headerText: '     login with:',
	redirectURL: '<%=aszPortal%>/socialize.jsp'
	,showTermsLink: 'false'
	,hideGigyaLink: 'true'
	,height: '55'
	,width: '150'
	,containerID: 'divConnect'
	,UIConfig: '<config><body><controls><snbuttons buttonsize="20"></snbuttons></controls><background frame-color="Transparent"></background></body></config>'
	,useFacebookConnect: 'true'
	,useHTML: 'true'
}
</script>
<script type="text/javascript">
   gigya.services.socialize.showLoginUI(conf,login_params);
</script>

<!--/div-->


