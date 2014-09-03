<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- this is a placeholder file that gets loaded in an iframe rather than a more detailed file, so that it does not mess up analytics or anything -->
<% if( aszHostID.equalsIgnoreCase( "volengfycsandbox" ) ){ %>
        <jsp:include page="/placeholder.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } else { %>

<FONT color="red"><bean:write name="individualForm" property="errormsg" /></FONT>

<%
String aszURL="http://"+aszRemoteHost;
out.println("<br>fb id is... "+session.getAttribute("FB_User_ID"));

String tempRefererMethod="";
if(!(request.getParameter("tempreferermethod")==null)){
	tempRefererMethod=request.getParameter("tempreferermethod");
	out.print(tempRefererMethod);
	if(session.getAttribute("FB_User_ID")!=null){
%>
temp referer method is: <%=tempRefererMethod%><br><br>
URL is: <%=aszURL%><br><br>
<script type="text/javascript">
function myfunc () {
var frm = document.getElementById("myform");
<% if(tempRefererMethod.length()>1){ 
	if(
		tempRefererMethod.startsWith(aszURL+"/?ref=") || 
		tempRefererMethod.startsWith(aszURL+"/register.do") || 
		tempRefererMethod.startsWith(aszURL+"/org.do") || 
		tempRefererMethod.startsWith(aszURL+"/oppsrch.do")
	){ 
		if(! (tempRefererMethod.contains("&secondtime=true")) ){ 
%>
<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%
if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	
%>
			frm.submit();
<%} 
 		} 
	} 
} %>
}
</script>

<body onLoad="timer=setTimeout('myfunc()',3000)">

<form action="<%=tempRefererMethod%>&secondtime=true" method="post" id="myform" target="_parent">
</form>

</body>
<%
}}
%>
<%//=aCurrentUserObj.getFBapikey()%>
<%@ include file="/template_include/footer_login.inc" %>
<% } %>
