<!-- start sidebar -->

<div id="sidebar">

<div id="sidebar_content">
<br>

<%  if(	! ((aszHostID.equalsIgnoreCase( "volenggospel" )) ||
			(aszHostID.equalsIgnoreCase( "volenggospelcom" ))  )
){  %>
<h5>Search Opportunities </h5>
<form name="searchForm2" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchTop">
<table><tr><td>
<h5>Postal Code</h5></td><td><input style="width:48px; height:17px" type="text" name="postalcode" maxlength="5" />
</td></tr>
<tr><td></td><td>
<input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/search_arrow.gif" alt="go" width="16" height="16" border="0" align="right" />
</td></tr></table>
</form>
<hr width="129" height="1"> 
<% } %>

<!--VOLUNTEERS-->
<h5>VOLUNTEERS</h5>
    <ul id="navbullet">
  <%@ include file="/template_include/sidebar_vol.inc" %>
        </ul>



<hr width="129" height="1"> 
  <!--img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="110" height="1" vspace="10"-->


<!--ORGANIZATIONS-->
<h5>ORGANIZATIONS</h5>
    <ul id="navbullet">
  <%@ include file="/template_include/sidebar_org.inc" %>
        </ul>

  
  
<hr width="129" height="1"> 
  <!--img src="http://www.christianvolunteering.org/techmimg/nav_divider.gif" width="110" height="1" vspace="10"-->
 <br clear="all">

</div>

<% if(aszHostID.equalsIgnoreCase("volengurbmin")){ %>
<img src="http://volunteer.urbanministry.org/imgs_um/sidebar_bottom.gif" width="155" height="23" />
<% } else if(aszHostID.equalsIgnoreCase("volengpartners")){ %>
<img src="http://www.christianvolunteering.org/imgs/sidebar_bottom.gif" width="151" height="23" />
<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
<img src="http://roundtrip.christianvolunteering.org/imgs_roundtrip/bg_rightnav-end.gif" width="175" height="46" />
<% } %>


</div>

<div class="cleardiv"></div>
<!-- end sidebar -->
