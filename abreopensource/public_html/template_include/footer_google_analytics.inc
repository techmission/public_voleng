<% 
aszGoalTracking="false";

if( request.getParameter("method")==null ){
	aszGoalTracking="false"; // the homepage is not a goal page; the only page w/o a method is likely index.jsp
} else if( !(request.getParameter("method")==null) ){
	out.println("<!--(posted from google analytics include) method posting = " + request.getParameter("method") + "-->");

	if(	
		//(aszPath.equalsIgnoreCase("/voleng2/nonp-start33.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/search_results_ajax-solr.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-start44.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-summary66.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-addlisting.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-dupliclisting.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/login1.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_test.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_test2.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_test3.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_test4.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas.jsp")) ||
                (aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp")) ||
 		(aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
//?		(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")) ||//?
		(aszPath.equalsIgnoreCase("/voleng2/iwanttohelp.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/emailconfirmation.jsp")) ||
		(request.getParameter("method").equalsIgnoreCase("processcreateorg")) || // test to see if the user got to this page via create org method
		(request.getParameter("method").equalsIgnoreCase("processRegistration")) || // test to see if the user got to this page via create indiv method
		(request.getParameter("method").equalsIgnoreCase("processCreateOpportstep1")) || // test to see if the user got to this page via create opp method
		(false==true) 
	){ // don't load the google analytics here - it has already been loaded earlier in the page for goal tracking 
        out.println("<!--aszGoalTracking is true-->");
	
		aszGoalTracking="true"; 
	} 
}else{ 
        out.println("<!--aszGoalTracking is false-->");

	aszGoalTracking="false"; // any of the above cases are not homepages
}
%>


<% 
if(aszGoalTracking.equalsIgnoreCase("false")){
%>



<!-- start file footer_google_analytics.inc -->


<!--script type="text/javascript" src="http://www.christianvolunteering.org/files/ga.js"></script-->

<script type="text/javascript">

var _gaq = _gaq || [];
  
_gaq.push(   
	['_setAccount', 'UA-768097-3'],   
		['_trackPageview'],   
	['b._setAccount', 'UA-768097-10'],   
		['b._trackPageview'] 
<% if (aszHostID.equalsIgnoreCase("volengcatholic")) { %>
	,['c._setAccount', 'UA-768097-40'],   
		['c._trackPageview'] 
<% } else if (aszHostID.equalsIgnoreCase("volengchurch")) { %>
	,['c._setAccount', 'UA-768097-56'],   
		['c._trackPageview'] 
<% } else if (aszHostID.equalsIgnoreCase("volengagrm")) { %>
	,['c._setAccount', 'UA-768097-47'],   
		['c._trackPageview'] 
<% } else if (aszHostID.equalsIgnoreCase("volengfia")) { %>
<%//var partnerTracker = _gat._getTracker("UA-1803221-1");%>
	,['c._setAccount', 'UA-768097-35'],   
		['c._trackPageview'] 
<% } else if ( (aszHostID.equalsIgnoreCase("volenghlic"))  ||
(aszHostID.equalsIgnoreCase("volengnewyork")) ){ %>
	,['c._setAccount', 'UA-768097-14'],   
		['c._trackPageview'] 
<% } %>
);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
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



<% } %>
