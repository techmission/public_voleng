<!-- start sidebar -->

<div id="sidebar">
<div id="sidebar_content">

<%  if( ! (
        (aszHostID.equalsIgnoreCase( "volengworldvision" )) ||
        (aszHostID.equalsIgnoreCase( "volenggospel" )) ||
        (aszHostID.equalsIgnoreCase( "volenggospelcom" ))
)){  %>
<!--SEARCH OPPORTUNITIES-->
  <%@ include file="/template_include/sidebar_search.inc" %>
<% } %>

<hr class="sidebar_hr"> 


<!--MY ACCOUNT-->
  <%@ include file="/template_include/sidebar_myaccnt.inc" %>

   <hr width="129" height="1" />

<!--VOLUNTEERS-->
  <%@ include file="/template_include/sidebar_vol.inc" %>

   <hr width="129" height="1" />

<!--ORGANIZATIONS-->
  <%@ include file="/template_include/sidebar_org.inc" %>
  
   <hr width="129" height="1" />
   <br />

</div>
</div>


<div class="cleardiv"></div>
</div>

<!-- This Stuff Breaks YoungLife
</div>
</div>
</div></div>
-->

<!-- end sidebar -->
