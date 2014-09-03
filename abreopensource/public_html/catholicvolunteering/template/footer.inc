<!-- start file footer.inc -->
<div id="footer">

<% if( aszPortalTemplate.equals("basic") ){ %>
	<%@ include file="/template/basic_footer.inc" %>
<% }else if( aszPortalTemplate.equals("main") ){ %>
	<%@ include file="/template/main_footer.inc" %>
<% } %>
</div>
<%@ include file="/template_include/footer_google_analytics.inc" %>
<%@ include file="/template_include/footer_piwik.inc" %>
<%@ include file="/template_include/footer_login.inc" %>
</body>
</html>

<!-- end file footer.inc -->
