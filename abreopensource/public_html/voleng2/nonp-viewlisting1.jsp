<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>

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
out.println("<!-- URL Alias is: " + opp.getOPPUrlAlias() + " -->");
//
if(opp.getOPPUrlAlias().length() > 1){
	if(aszPath.equalsIgnoreCase("/voleng2/nonp-viewlisting1.jsp")){
		response.setStatus(301);

// program in forwarding if the opportunity is Faith-Filled
		if(opp.getOPPFaithSpecTID()==21998){
			response.setHeader( "Location", "http://www.churchvolunteering.org/" + aszPortal + opp.getOPPUrlAlias() + ".jsp" );		
		}else{
			response.setHeader( "Location", "http://" + currentURLPath + opp.getOPPUrlAlias() + ".jsp" );		
		}

		response.setHeader( "Connection", "close" );
	}
}
//
%> 

<head>
