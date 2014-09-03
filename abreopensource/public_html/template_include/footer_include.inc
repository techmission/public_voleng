<!-- start file footer.inc -->


<!-- back to /template/footer.inc -->



</div>

<% if(	(aszPath.equalsIgnoreCase("/voleng2/myaccountsum1.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-start33.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-start44.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-summary66.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-addlisting.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-dupliclisting.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/volcreateacct1.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/iwanttohelp.jsp")) ||
		(aszPath.equalsIgnoreCase("/voleng2/emailconfirmation.jsp")) ||
		(false==true) 
){ // don't load the google analytics here - it has already been loaded earlier in the page for goal tracking %>
<% } else { %>
<%@ include file="/template_include/footer_google_analytics.inc" %>

<% } %>
</body>
</html>

<!-- end file footer.inc -->
