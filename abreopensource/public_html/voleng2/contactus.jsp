<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

<title>ChristianVolunteering.org: Contact Us</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Contact Us</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Contact Us</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Contact Us </span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; contact us</div>
</div>
<% } %>

<div id="body">
<br>
<table border="0" width="500"><tr>
<td>&nbsp;
</td>
<td>
<dl>
	<dt>&nbsp;</dt>
	<h3>Mailing Address/Business Office:</h3><br>
	<dt>TechMission</dt>
	<dt>48 Pleasant Street</dt>
	<dt>Dorchester, MA&nbsp; 02125</dt>
</dl>
<!-- Removed the phone and fax number since people were finding them via Google -->
<!-- <p>Phone: 617-282-9798<br>
  Fax: 617-825-0313</p>
<p> -->
<p>For help using our site, please visit the <a href="http://www.urbanministry.org/help">Help Forums</a>
<br />Or you can email <a href="mailto:info@christianvolunteering.org">info@christianvolunteering.org</a> or call us at 617-282-9798 x104.</p> 
</td></tr></table>


<!--  h3><strong>
  <br>
Directions to Business Office</strong></h3>
<p><strong>Driving Directions:</strong> From I-93. Take exit 11B (Route 203, 
Granite Ave., Ashmont) Go over the bridge and through the light. Turn left 
(west) at second light onto Gallivan Blvd. You will see a McDonalds and a Mobil 
Gas Station at the turn. Turn right (north) at third light onto Washington St. 
You will see a gas station. Shortly after the first light, Torrey Street will be 
on the left.&nbsp; We are on the first floor of 31 Torrey Street.</p>
<p><strong>T Directions:</strong> Take the Red line to Ashmont. Exit the T 
station, and turn right (North) on to Dorchester Ave. At the first major 
intersection, take the left fork, onto Talbot. At the first light, take the left 
fork on to Welles Ave. Walk to the end of Welles Ave. to take a left onto 
Washington Street. Torrey Street will be the first street immediately on the 
right.&nbsp; We are on the first floor of 31 Torrey Street.&nbsp; It's about a 
ten minute walk from the T station. </p-->

<br />
<br />
<iframe width="640" scrolling="no" height="480" frameborder="0" src="http://maps.google.com/maps?f=q&source=s_q&hl=en&geocode=&q=48+Pleasant+St,+Boston,+MA+02125&sll=37.0625,-95.677068&sspn=59.467068,114.169922&ie=UTF8&hq=&hnear=48+Pleasant+St,+Boston,+Suffolk,+Massachusetts+02125&ll=42.315415,-71.059490&spn=0.030478,0.054932&z=14&output=embed" marginwidth="0" marginheight="0"></iframe>

</div></div>
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->