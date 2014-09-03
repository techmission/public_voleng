<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<%
String currentURLPath=aszSubdomain;
if(aszPortal.length()>0){
	if(aszPortal.startsWith("/")){
		//currentURLPath+=aszPortal;
	}else{
		currentURLPath+="/"+aszPortal;
	}
}
currentURLPath+="/";
out.println("<!-- URL Alias is: " + org.getORGUrlAlias() + " -->");
//
if(org.getORGUrlAlias().length() > 1){
	if(aszPath.equalsIgnoreCase("/voleng2/nonp-view21.jsp")){
		response.setStatus(301);
		response.setHeader( "Location", "http://" + currentURLPath + org.getORGUrlAlias() + ".jsp" );		
	}
}
//out.print("Location:  "+ "http://" + currentURLPath + org.getORGUrlAlias() + ".jsp");
%>