<!-- anonymous.jsp -->

<% 
session.setAttribute("drupalsession","login");  // Storing Value into session Object
%>

<script type="text/javascript">

<% //window.onload=parent.parent.document.getElementById('forward').click();%>


window.onload=parent.parent.window.location.reload();
</script>

