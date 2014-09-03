<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<bean:define id="theStatus" name="orgForm" property="errormsg" type="java.lang.String"/>
<%
String thereWasError;
if(theStatus.equalsIgnoreCase("")){
  thereWasError = "?newopp";
}else{
  thereWasError = "?oppnew";
}

String actionURL="/org.do"; 
if (aszPath.equalsIgnoreCase("/voleng2/nonp-dupliclisting.jsp")){ 
	actionURL = actionURL + thereWasError; 
}
%>


	<jsp:include page="/voleng2/nonp-addlisting.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
