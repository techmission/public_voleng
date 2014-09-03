<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->
<% 
session.setAttribute("drupalsession","loggedin");  // Storing Value into session Object
%>
<script type="text/javascript">

window.onload=parent.parent.document.getElementById('forward').click();

</script>



<html:form action="/register.do"  >
<html:hidden property="method" value="processLogin" />


 <div id="body">

 <h2>Account Summary</h2>
<br>
      	<div align="center">


      <fieldset style="width: 70%; height: 80px">
		<h3><LEGEN><%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></legen></h3>
      &nbsp;

          <A href="<%=aszPortal%>/register.do?method=showIndEditProfile1">Edit <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%> Profile</A>

      </fieldset>


</div>

</html:form>

