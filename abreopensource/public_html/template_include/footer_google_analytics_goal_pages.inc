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
<% if (aszHostID.equalsIgnoreCase("volengcatholic")) { %>
	,['c._setAccount', 'UA-768097-40'],   
		['c._trackPageview','<%=aszGoalPage%>'] 
<%} %>
);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0]; 
	s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- end file footer_google_analytics_goal_pages.inc -->

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


