	
<% if(!aszHostID.equalsIgnoreCase( "volengivol" )){ %>

<%@ include file="/template_include/cv_footer.inc" %>

<% } else  if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>

<%//@ include file="/template_include/ivol_footer.inc" %>
<!-- start file ivol_footer.inc -->

<div id="footer" style="padding:10px;line-height:1.6em; text-align:center;">

<a href="http://www.urbanministry.org/terms?theme=urbanresource">privacy policy</a> | <a href="<%=request.getContextPath()%>/register.do?method=showContact">contact us</a> | <a href="mailto:feedback@ivolunteering.org">feedback</a> | <a href="<%=request.getContextPath()%>/register.do?method=showSitemap">sitemap</a> | <a href="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&id=13">donate</a> | <a href="http://www.techmission.org">techmission.org</a> | <a href="http://www.safefamilies.org">safefamilies.org</a> | <a href="http://www.techmission.org/cd/tmnonprofit.php">nonprofit freeware</a><br />

<% } %>

<!-- back to /template/footer.inc -->



</div>

<script type="text/javascript" src="http://www.urbanministry.org/themes/universal_assets/network_band/network-band.js"></script>

