<%@ include file="/template_include/topjspnologin1.inc" %>



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

<title>Christian Volunteer Network: Advanced Search for Christian volunteer opportunities, short term urban mission trips, and virtual volunteer opportunities</title>

<link rel="canonical" href="http://www.christianvolunteering.org/advancedsearch.jsp" />

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<!--link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" /-->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
boolean bAssoc=false;
if(aszPortalType.equals("natlassoc")) bAssoc=true;

int iTemp=0;


String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null, aszSubmit="display: inline;";

String aszPartnerSecondDiv="display: none;";
// hide if not on specialized partner; if on specialized partner, show 2nd row by default, b/c first will be set to partner
if(	aszHostID.equalsIgnoreCase("volengsalvationarmy") ||
	aszHostID.equalsIgnoreCase("volengagrm")		
){ 
	aszPartnerSecondDiv="display: table-row;";
}


String aszChurchDisplay="display:none;";
if(aszHostID.equalsIgnoreCase("volengchurch")){
	aszChurchDisplay="display:inline;";
}
%>


 <div style="display:none;">
	<div id="portal_name"><%=aszPortalName %></div>
</div>

 <div id="oppsearch" class="solr">
	  <span id="title">search</span>
	<%//@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>

<script language="javascript">
$(document).ready(function() {
	$('#fq_search').text(window.location.hash)
  });
</script>

<div id="result">

<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->


<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">search interns</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">volunteer</a> &gt; 
	search for opportunities  </div>
</div>
<% } %>

<div id="body">
<div id="populate" style="display:none;"></div>  
	
    <div id="all_tabs">
	<a style="text-decoration: none" href="<%=aszPortal%>/search.jsp"><h2>Find an Internship Applicant</h2></a>
<hr size="2" color="#4D4D4D" width="100%" style="margin-top: 0px; //margin-top:-7px;" />
</div> <!-- end: div id all_tabs-->
<!--br /-->		

<div id="searchpage">

<div id="searchform">
<%//@ include file="/template_include/applicants_solr_search.inc" %>
</div>

</div>


<div id="show_results">
		<div id="sort" class="tagcloud toggle" style="float:right; display:none;">
		<span class="label">Sort By:</span>
	<SELECT id="sortkey" name="sortkey" class="smalldropdown" > 
        <option value="tm_member_i desc, popularity desc"></option>
        <option value="tm_member_i desc, popularity desc" >Popularity</option>
        <option value="org_name asc, tm_member_i desc, popularity desc" >Organization Name</option>
        <option value="title_literal asc, tm_member_i desc, popularity desc" >Opportunity Title</option>
        <option value="city_tax asc, tm_member_i desc, popularity desc" >City</option>
        <option value="province_tax asc, tm_member_i desc, popularity desc" >State (US & Canada)</option>
        <option value="country_tax asc, tm_member_i desc, popularity desc" >Country</option>
		<option value="lat_updated_dt desc, tm_member_i desc, popularity desc" >Date Last Updated</option>
        <option value="num_volunteers_org desc, tm_member_i desc, popularity desc" ># Volunteers / Organization</option>
        <option value="num_volunteers_opp desc, tm_member_i desc, popularity desc" ># Volunteers / Opportunity</option>
    </SELECT>

<br clear="all"/>
		</div>

	<div id="nav">
		<ul id="pager"></ul>  <div id="pager-header"></div>
	</div>
	<br>
	<div id="docs"></div>
</div>
</div>

<div class="cleardiv"></div>


</div>
</div>

<!-- start sidebar information -->
<%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

