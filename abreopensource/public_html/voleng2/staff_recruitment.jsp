<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Equipping & Managing Volunteers</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Staff Recruitment</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Staff Recruitment</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; staff recruitment</div> 
</div>
<% } %>

       
		<div id="body">	 
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<h1>Christian Staff Recruitment Websites</h1>




<table width="428" height="1030">
<!-- MSTableType="layout" -->
<tr>
    <td colspan="2" height="45">
	<img src="../imgs/main_dividerline.gif" width="550" height="3" vspace="20" border="0"></td>
  		</tr>
<tr>
    <td align="center" width="130"><a href="http://www.christianjobs.com" target="_blank">
	<img src="<%=aszPortal%>/imgs/pic/christianjobs.jpg" border="0"></a></td>
    <td height="154"><a href="http://www.christianjobs.com" target="_blank">
    <strong><em>www.christianjobs.com</em></strong></a><br><br>
	<p>Great website for finding employees. Easy to navigate and popular.<br> 
	30 day listings<br>
	$149 each<br>
	$720 for a six-pack</p> 
	
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="45">
	<img src="../imgs/main_dividerline.gif" width="550" height="3" vspace="20" border="0"></td>
  		</tr>
<tr>
    <td align="center"><a href="http://www.intercristo.com" target="_blank">
	<img src="<%=aszPortal%>/imgs/pic/intercristo.jpg" border="0"></td>
    <td height="152"><a href="http://www.intercristo.com" target="_blank">
    <strong><em>www.intercristo.com</em></strong></a><br><br>
    Popular in the western US. Website is easy to use. <br>30 day listings<br>$90 each <br>$345 for a five-pack
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="45">
	<img src="../imgs/main_dividerline.gif" width="550" height="3" vspace="20" border="0"></td>
  		</tr>
<tr>
    <td align="center"><a href="http://www.christianplacements.com" target="_blank">
	<img src="<%=aszPortal%>/imgs/pic/christianplacements.jpg" border="0"></td>
    <td height="152"><a href="http://www.christianplacements.com"><strong><em>www.christianplacements.com</em></strong></a><br>
	 Seems to be used mostly by churches.<br>30 day listings<br>$85 each
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="45">
	<img src="../imgs/main_dividerline.gif" width="550" height="3" vspace="20" border="0"></td>
  		</tr>
<tr>
    <td width="400" align="center"><a href="http://www.www.christiancareercenter.com.com" target="_blank">
	<img src="<%=aszPortal%>/imgs/pic/christiancareercenter.jpg" border="0"></td>
    <td height="173"><a href="http://www.christiancareercenter.com"><strong><em>www.christiancareercenter.com</em></strong></a><br>
    This website is a little difficult to navigate. For churches or ministries that can't afford a featured job, they will 
    let you list a limited job posting for free. To get more power for your listing, <br>Featured 90-day listings <br>
    $50 each <br>$399 for a ten-pack 
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="45">
	<img src="../imgs/main_dividerline.gif" width="550" height="3" vspace="20" border="0"></td>
  		</tr>
<tr>
    <td align="center" width="130"><a href="http://www.nacelink.com" target="_blank">
	<img src="<%=aszPortal%>/imgs/pic/nacelink.jpg" border="0"></a></td>
    <td height="152" width="288"><a href="http://www.nacelink.com"><strong><em>www.nacelink.com</em></strong></a><br>
    Post listing at up to 200 college websites at one time. Great way to target recent college grads. 
    Pricing depends on how many schools you list at. 
      		</td>
  		</tr>

</table>

<% } %>
			


</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
