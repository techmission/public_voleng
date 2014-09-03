<% if(bSkipBanner==true && ! aszPortal.contains("meettheneed")){ %>
<base target="_blank" />
<% } %>
<link href="http://www.christianvolunteering.org/template_include/css/narrow_portal.css" rel="stylesheet" type="text/css" />
<link href="http://www.christianvolunteering.org/template_include/css/standard-basic.css" rel="stylesheet" type="text/css" />
<%
out.println("<!-- is loading basic_header -->");
		out.println("<!--has a portal parameter: " + aszPortalName + "-->");
			out.println("<!--has a portal banner: " + aszPortalBanner + "-->");
			out.println("<!--has a portal header: " + aszPortalHeader + "-->");
			out.println("<!--has a portal banner: " + aszPortalCSS + "-->");
			out.println("<!--has a portal banner: " + aszPortalFooter + "-->");
			out.println("<!--has a portal entity name: " + aszPortalOrgName + " -->");
			out.println("<!--has a portal nid: " + aszPortalNID + "-->");
			out.println("<!-- and the iPortalNID int value is: "+iPortalNID+" -->");
			out.println("<!--has a portal uid: " + aszPortalUID + "-->");
			out.println("<!-- and the int value is: "+iPortalUID+" -->");
			out.println("<!--has a portal type: " + aszPortalType + " -->");
%>

<style>
<%
out.print(aszBackgroundColor);
out.print(aszBorderColor);
out.print(aszLinkTextColor);
out.print(aszTextColor);
if(bSkipBanner==true){
%>
#contentwrapper{
	width: auto;
}
#maincontent{
	width: auto;
}
<% } %>
#banner .bannerimage{
	float: left;
	padding: 3px 3px 0px 3px;
	width: 375px;
	border: 1px;
	line-height:2em;
}
#banner .portalsearch{
	float: left;
	padding: 3px 3px 0px 3px;
}
#banner a{
	font-weight: bold;
	font-size: 20px;
	color: #596E9F;
	margin-top: 10px;
	margin-bottom: 10px;
}
<%=aszPortalCSS%>
</style>

<% if(aszPortalHeader.length()>0){ %>
<div id="banner">
<%=aszPortalHeader%>
</div>
<% //}else if(aszPortalBanner.length()>0 && aszPortalBanner.length()<1){ %>

<% }else{ %>
<div id="banner">
<% if(bSkipBanner==false){ %>
	<div class="bannerimage">
	
	<% if(aszPortalBanner.length()>0){ %>
		<a class="banneralt" href="<%=aszPortal%>/"><img src="<%=request.getContextPath()%>/imgs/banners/<%=aszPortalBanner%>" border="0" alt="<%=aszPortalOrgName%> Volunteering" title="<%=aszPortalOrgName%> Volunteering" class="banneralt" style="max-width:375px; max-height:120px;" /></a>
	<% }else{ %>
		<a class="banneralt" href="<%=aszPortal%>/"><%=aszPortalOrgName%> Volunteering</a>
<div style="float:right; padding: 2px;"><a href="http://www.christianvolunteering.org" border="0"><img src="/imgs/cv_logo_powered_by.gif" alt="Powered by ChristianVolunteering" border="0"></a></div>
<div  class="cleardiv"></div>
	<% } %>
	</div>
	
	<%@ include file="/template_include/portal_header_search.inc" %>
<% } %>
</div>

<% } %>