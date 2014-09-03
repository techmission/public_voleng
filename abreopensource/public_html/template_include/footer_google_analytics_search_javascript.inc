<!-- start file footer_google_analytics.inc -->
<%
aszGoalTracking=aszPath;
boolean partnerDomain=false;
%>

<script type="text/javascript">

var _gaq = _gaq || [];

function call_google_analytics(jsPageName){  
	var trackPageName = '/search/?q=search&'+jsPageName;
	_gaq.push(   
		['_setAccount', 'UA-768097-3'],   
			['_trackPageview',trackPageName],   
		['b._setAccount', 'UA-768097-10'],   
			['b._trackPageview',trackPageName] 
	<% if (aszHostID.equalsIgnoreCase("volengcatholic")) { %>
		,['c._setAccount', 'UA-768097-40'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengchurch")) { %>
		,['c._setAccount', 'UA-768097-56'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengagrm")) { %>
		,['c._setAccount', 'UA-768097-47'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengfacebook")) { %>
		,['c._setAccount', 'UA-768097-52'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengfacebookivol")) { %>
		,['c._setAccount', 'UA-768097-53'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengfycsandbox")) { %>
		,['c._setAccount', 'UA-768097-55'],   
			['c._trackPageview',trackPageName] 
	<% } else if (aszHostID.equalsIgnoreCase("volengfia")) { %>
	<%//var partnerTracker = _gat._getTracker("UA-1803221-1");%>
		,['c._setAccount', 'UA-768097-35'],   
			['c._trackPageview',trackPageName] 
	<% } else if ( (aszHostID.equalsIgnoreCase("volenghlic"))  ||
	(aszHostID.equalsIgnoreCase("volengnewyork")) ){ %>
		,['c._setAccount', 'UA-768097-14'],   
			['c._trackPageview',trackPageName] 
	<% } else if ( (aszHostID.equalsIgnoreCase("volenguywi")) ||
	(aszHostID.equalsIgnoreCase("volenguywi")) ) {%>
		,['c._setAccount', 'UA-768097-41'],   
			['c._trackPageview',trackPageName] 
	<%} %>
	);
}
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0]; 
	s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- end file footer_google_analytics.inc -->

<!-- include quanticast footer -->
<%@ include file="/template_include/footer_quanticast.inc" %>


