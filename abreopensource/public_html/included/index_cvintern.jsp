<%@ include file="/template_include/topjspnologin1.inc" %>
<%@ include file="/template/home_header.inc" %>


included
<%
String aszCompleteRedirectURL="";

aszCompleteRedirectURL=null;
//aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch";

out.print(aszCompleteRedirectURL);
response.sendRedirect("http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&fq=intern_type:%22City%20Vision%20Internship%22#fq=intern_type:%22City%20Vision%20Internship%22&fq=content_type:opportunity");

%> 