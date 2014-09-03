<%@ include file="/template_include/topjspnologin1.inc" %>

<%
String aszCompleteRedirectURL="";

String aszHostDomain = request.getHeader("host");


aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&fq=intern_type:%22City%20Vision%20Internship%22#fq=intern_type:%22City%20Vision%20Internship%22&fq=content_type:opportunity";

out.print(aszCompleteRedirectURL);
response.sendRedirect(aszCompleteRedirectURL);
%>
<p>If you see this error page, please copy the text of this page and email it to us at support@christianvolunteering.org to let us know</p>
<p>Thank you!</p>

