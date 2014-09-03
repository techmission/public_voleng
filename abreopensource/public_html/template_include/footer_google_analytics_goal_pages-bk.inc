<!-- start file footer_google_analytics.inc -->
<%
aszGoalTracking=aszPath;
boolean partnerDomain=false;
%>

<script type="text/javascript">

var _gaq = _gaq || [];
  
_gaq.push(   
	['_setAccount', 'UA-768097-3'],   
		['_trackPageview','<%=aszGoalPage%>'],   
	['b._setAccount', 'UA-768097-10'],   
		['b._trackPageview','<%=aszGoalPage%>'] 
<% if (aszHostID.equalsIgnoreCase("volengnewengland")) { %>
	,['c._setAccount', 'UA-768097-17'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengcatholic")) { %>
	,['c._setAccount', 'UA-768097-40'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengchurch")) { %>
	,['c._setAccount', 'UA-768097-56'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengabs")) { %>
	,['c._setAccount', 'UA-768097-44'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengagrm")) { %>
	,['c._setAccount', 'UA-768097-47'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<%} else if ( (aszHostID.equalsIgnoreCase("volengccda")) ||
(aszHostID.equalsIgnoreCase("volengchicago")) ){ %>
	,['c._setAccount', 'UA-768097-13'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
	,['d._setAccount', 'UA-768097-16'],   
		['d._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengesa")) { %>
	,['c._setAccount', 'UA-768097-45'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfacebook")) { %>
	,['c._setAccount', 'UA-768097-52'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfacebookivol")) { %>
	,['c._setAccount', 'UA-768097-53'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfycsandbox")) { %>
	,['c._setAccount', 'UA-768097-55'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfamily")) { %>
	,['c._setAccount', 'UA-768097-34'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfia")) { %>
<%//var partnerTracker = _gat._getTracker("UA-1803221-1");%>
	,['c._setAccount', 'UA-768097-35'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volenggospel")) ||
(aszHostID.equalsIgnoreCase("volenggospelcom")) ) {%>
	,['c._setAccount', 'UA-768097-23'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volenghlic"))  ||
(aszHostID.equalsIgnoreCase("volengnewyork")) ){ %>
	,['c._setAccount', 'UA-768097-14'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengmissionamerica")) { %>
	,['c._setAccount', 'UA-768097-33'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengpartners")) { %>
	,['c._setAccount', 'UA-768097-38'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengroundtrip")) { %>
	,['c._setAccount', 'UA-768097-46'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengsaddleback")) { %>
	,['c._setAccount', 'UA-768097-31'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if (aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	,['c._setAccount', 'UA-768097-30'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volenguywi")) ||
(aszHostID.equalsIgnoreCase("volenguywi")) ) {%>
	,['c._setAccount', 'UA-768097-41'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volengworldvision")) ||
(aszHostID.equalsIgnoreCase("volenguywi")) ) {%>
	,['c._setAccount', 'UA-768097-43'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volengyouthpartners")) ||
(aszHostID.equalsIgnoreCase("volengdenver")) ) {%>
	,['c._setAccount', 'UA-768097-15'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<%} %>
);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; 
	s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- end file footer_google_analytics.inc -->

<!-- Google Code for ChristianVolunteering.org Visitors Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "v6DrCOzYiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=v6DrCOzYiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>


<!-- include quanticast footer -->
<%@ include file="/template_include/footer_quanticast.inc" %>


