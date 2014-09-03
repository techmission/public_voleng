<%@ include file="/template_include/topjsploginreq1.inc" %>
<%@page import="java.text.DecimalFormat" %>

<%
portal=aszPortal; 
tempPortal=portal;

if(tempPortal.length()>8) if(tempPortal.startsWith("/voleng")) tempPortal=tempPortal.substring(8);
else if(tempPortal.length()>1) if(tempPortal.startsWith("/")) tempPortal=tempPortal.substring(1);

if(aszPortal.equals("/voleng")){
	tempPortal="";
	portal="";
}
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aRegionList, 176 );
String aszTitlePrint = "";

int iCounter=0;

%>
<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<%

String aszContentType = "content_type:resume";
String aszTypePlural = "Candidates";

String aszFQParamValue = "";
String aszFQParamFacet = "";
String aszKewordsSpaceSeparated = "";
String aszKewordsCommaSeparated = "";
String aszFQSearch = "";
boolean bLocationFacet = false;

String aszLat = "", aszLong = "";
String aszZIP = searchinfo.getZIP();
if(aszZIP.length() > 0){
	ArrayList aLocationDataList = new  ArrayList ( 2 );
	aCodes.getZipCodeData( aLocationDataList, aszZIP );
	if(null != aLocationDataList){
		try{
		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLocationDataList.get(0);
		aszLat = aAppCode.getLatitude();
		aszLong = aAppCode.getLongitude();
		}catch(java.lang.IndexOutOfBoundsException ex){
			System.out.println("jsp file   Exception "+ex.getMessage());	
		}
	}
}

if(aszContentType.contains("org")){
	aszTypePlural = "Organizations";
}



if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.churchvolunteering.org")){
	aszSubdomain = "ChurchVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}

String aszLatitude = searchinfo.getSearchLatitude1();
String aszLongitude = searchinfo.getSearchLongitude1();

int iCountDisplay=0,iNumDisplay=10;
String aszDisplayClass="";

if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;

String aszTitleTag = "";
if(aszKewordsSpaceSeparated.length() > 1){
	if(aszKewordsSpaceSeparated.endsWith(" ")){
		aszTitleTag = aszKewordsSpaceSeparated.substring(0,aszKewordsSpaceSeparated.length()-1) + ": ";
	}
}
if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ 
	aszTitleTag += "Volunteer Opportunities";
}else{ 
	aszTitleTag += "Christian Volunteer and Short Term Missions " + aszTypePlural + ": " + aszSubdomain;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="image_src" href="/imgs/logo.gif"/>
	<title><%=aszTitleTag%></title>
<meta name="description" content="<%=aszKewordsSpaceSeparated%> Search <%=aszOrgOrChurchOpp%> and Short Term Missions <% if(aszTypePlural.contains("pportunities"))out.print("Trip ");out.print(aszTypePlural); %>: <%=aszSubdomain%>" />
<meta name="keywords" content="<%=aszKewordsCommaSeparated%>, volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, orphanage, medical missions, christian internship, ministry internship, online volunteering, volunteer online">
<% } %> 

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<div style="display:none">
<div id="content_type_search">
<%=aszContentType %>
cvintern_applicant:"City Vision Intern Applicant"
</div>
</div>
<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
</div><!-- end solr_results div -->

<%//										@ include file="/template_include/search_results_sidebar.inc" %>
<%// had to comment out above b/c it's somehow making the search page continually reload another search %>
<!-- end sidebar.inc -->






































<script language="javascript">

$('#menu_sidebar').click(function(){
//console.log('triggered');
	$('#sidebar').show();
});
function toggle_menu_sidebar(){
	var sidebar = document.getElementById("sidebar"); 
	var menu_sidebar = document.getElementById("menu_sidebar"); 
	if($('#sidebar').is(":hidden") ){
		$('#sidebar').show();
	}else{
		$('#sidebar').hide();
	}
}


function toggle_display(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapse_class=" collapse";
	var expanded_class=" expanded";
	var e = document.getElementById(facet_field); 
	if(e.style.display=="none"){
		e.style.display="block";
		e.parentNode.className=default_class+handle_class+expanded_class;
	}else{
		e.style.display="none";
		e.parentNode.className=default_class+handle_class+collapse_class;
	}
}
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
	var root_facet_element = document.getElementById(id); 
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class;

		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
				if(list_element.style == undefined){
				}else{
					var init_class = list_element.className;
					if(init_class=='filter collapsed'){
						list_element.style.display="block";
						list_element.className = default_class+expanded_class;
					}
				}
			}
		}
	}
	var more_link = 'more_link_'+facet_field;
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display='none'
		more_link_element.className = collapsed_class;
	}
}


$(document).ready(function() {
//console.log('document ready');
//console.log('aszFQSearch is <%=aszFQSearch%>');



	var filter_class = 'filter';
	var handle_class = ' handle';
	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';
	
	var filter_data = $('#filter_data_heading').text();
	var contenttype_data = $('#contenttype_heading1').text();
	var service_areas_element = document.getElementById('intern_type'); 
	var group_size_element = document.getElementById('intern_pos_type'); 
	var benefits_element = document.getElementById('cvintern_applicant'); 
	var length_of_trip_element = document.getElementById('has_bachelors'); 
	var frequency_element = document.getElementById('credits_range'); 
	var advanced_element = document.getElementById('intern_length'); 
	var country_element = document.getElementById('gender'); 
	var city_element = document.getElementById('age_range'); 
	var region_element = document.getElementById('city'); 
	var denom_affil_element = document.getElementById('province'); 
	var affil_element = document.getElementById('country'); 
	
	var facet_service_areas_element = document.getElementById('facet_intern_type'); 
	var facet_group_size_element = document.getElementById('facet_intern_pos_type'); 
	var facet_benefits_element = document.getElementById('facet_cvintern_applicant'); 
	var facet_length_of_trip_element = document.getElementById('facet_has_bachelors'); 
	var facet_frequency_element = document.getElementById('facet_credits_range'); 
	var facet_advanced_element = document.getElementById('facet_intern_length'); 
	var facet_country_element = document.getElementById('facet_gender'); 
	var facet_city_element = document.getElementById('facet_age_range'); 
	var facet_region_element = document.getElementById('facet_city'); 
	var facet_denom_affil_element = document.getElementById('facet_province'); 
	var facet_affil_element = document.getElementById('facet_country'); 
		
	var hashURL = '' + window.location.hash;
//console.log('hashURL is: ' + hashURL);
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#organization').removeClass('active');
		$('#job').removeClass('active');
		$('#resume').addClass('active');
		$('#foundation').removeClass('active');
			

//		expenditures_element.style.display='none';
//		facet_expenditures_element.className=filter_class+handle_class+collapsed_class;

		$('#srchmethod').val('City Vision Intern Applicant');
		if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
			$('#filter_data_heading').text(filter_data);
		}

	$('#results_type').text($('#srchmethod').val());
	$('#search-results').hide();
//console.log('872 net_assets is: ' + $('#net_assets').html());
});


</script>

<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>


<style>
#searchform{
/*	display:none; */
}
.head{
	color: #003366;
    font-size: 1em;
    font-weight: normal;
    padding: 5px 0;
	margin-bottom: 10px;
}
#contentwrapper{
	background:url("http://www.christianvolunteering.org/imgs/bodycontent_patt_wide-950.gif") repeat-y scroll right top #FFFFFF;
}
.collapsed{
	visibility:hidden;
}
.expanded{
	visibility:visible;
}
.filter li {
    font-size: 1em;
    line-height: 1.3em;
    list-style: none outside none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    position: relative;
    white-space: nowrap;
}
</style>

<div id="result">

<!-- start sidebar information -->
<!-- start sidebar.inc -->
<div id="menu_sidebar" onclick="toggle_menu_sidebar()">
<img src="http://www.christianvolunteering.org/imgs/mobile-menu-button.png" alt="menu" border="0" width="30px;" />
</div>

<style>
#sidebar.search_results {
    width: 240px;
}
</style>

<div id="sidebar" class="search_results">
	<div id="sidebar_content">
		<br>
	<h3 id="filter_label">Filters Applied</h3><ul id="removeall"></ul>
	<ul id="selection"></ul>
	<div class="cleardiv"></div>
<ul class="filters" id="filters">		
	<li id="facet_dist" >
		<div id="label" style="float:left">Distance:</div>
		<div id="dist" class="tagcloud toggle" style="float:left; display:block;">
				<SELECT id="d" name="d" class="smalldropdown" > 
					<option value="1.609344">exact location</option>
					<option value="8.04672">within 5 miles</option>
					<option value="16.09344">within 10 miles</option>
					<option value="24.14016">within 15 miles</option>
					<option value="40.2336" selected="selected">within 25 miles</option>
					<option value="80.4672">within 50 miles</option>
					<option value="160.9344">within 100 miles</option>
					<option value="Virtual">virtual</option>
				</SELECT>
		</div><!-- end dist -->
		<br clear="all"/>
	</li>
		
		<hr class="sidebar_hr"> 
	<li id="facet_sort">
		<div id="sortby" style="float:left">Sort:</div>
		<div id="sort" class="tagcloud toggle" style="float:left; display:block;">
			<select id="sortkey" name="sortkey" class="smalldropdown"> 
				<option value="last_updated_dt desc, screened desc, source desc" >Date Last Updated</option>
		        <option value="title_literal asc" >Name</option>
				<option value="screened desc, last_updated_dt desc, source desc" >Recommended</option>
		        <option value="city_tax asc, last_updated_dt desc, screened desc" >City</option>
		        <option value="province_tax asc, last_updated_dt desc, screened desc" >State (US & Canada)</option>
		        <option value="country_tax asc, last_updated_dt desc, screened desc" >Country</option>
		    </select>
		</div>
<br clear="all"/><br clear="all"/>
	</li>
    
<%
String aszFQParams = "";
String aszHashSearch = "";
if(request.getParameter("fq") != null){
	String[] a_aszFQParams = new String[255];
	a_aszFQParams = request.getParameterValues("fq");
		for(int i = 0; i < a_aszFQParams.length; i++){
			aszFQParamValue = a_aszFQParams[i];
			aszFQSearch+=aszFQParamValue + "&";
				
		}
}
%>
    <div class="head"> 
		<a id="top_applic" href="#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&fq=screened:2&fq=cvintern_placed:0">Top Candidates</a>
		<br />
		<a id="unscr_applic" href="#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&fq=screened:1&fq=cvintern_placed:0">Unscreened Candidates</a>
		<br />
		<a id="all_applic" href="#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&fq=screened:[1 TO 2]&fq=cvintern_placed:0">All Candidates</a>
	</div>
	
	<li class="filter handle expanded" id="facet_intern_type">
		<a class="handle" onClick="toggle_display('intern_type')"><span class="i bullet">&nbsp;</span> <span class="label">Intern Types:</span></a>
		<ul id="intern_type" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("intern_type")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_intern_type" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_intern_type'); return false;" style="display:inline" href="#intern_type">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>


	<li class="filter handle expanded" id="facet_pos_pref">
		<a class="handle" onClick="toggle_display('pos_pref')"><span class="i bullet">&nbsp;</span> <span class="label">Preferred Position Type:</span></a>
		<ul id="pos_pref" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("pos_pref") && facets[1].length()>0){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
//out.println("<!-- aszHashSearch="+aszHashSearch+" -->");						
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
//						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0 && iCountDisplay < 3){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_pos_pref" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_pos_pref'); return false;" style="display:inline" href="#pos_pref">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_work_environ">
		<a class="handle" onClick="toggle_display('work_environ')"><span class="i bullet">&nbsp;</span> <span class="label">Work Environment:</span></a>
		<ul id="work_environ" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("work_environ")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_work_environ" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_work_environ'); return false;" style="display:inline" href="#work_environ">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>


	<li class="filter handle expanded" id="facet_degree_prog">
		<a class="handle" onClick="toggle_display('degree_prog')"><span class="i bullet">&nbsp;</span> <span class="label">Degree Program:</span></a>
		<ul id="degree_prog" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("degree_prog")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_degree_prog" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_degree_prog'); return false;" style="display:inline" href="#degree_prog">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>


	<li class="filter handle expanded" id="facet_has_bachelors">
		<a class="handle" onClick="toggle_display('has_bachelors')"><span class="i bullet">&nbsp;</span> <span class="label">Has a Bachelor's Degree:</span></a>
		<ul id="has_bachelors" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("has_bachelors")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
//						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_has_bachelors" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_has_bachelors'); return false;" style="display:inline" href="#has_bachelors">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_credits_range">
		<a class="handle" onClick="toggle_display('credits_range')"><span class="i bullet">&nbsp;</span> <span class="label">Earned College Credits:</span></a>
		<ul id="credits_range" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("credits_range")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_credits_range" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_credits_range'); return false;" style="display:inline" href="#credits_range">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_age_range">
		<a class="handle" onClick="toggle_display('age_range')"><span class="i bullet">&nbsp;</span> <span class="label">Age Range:</span></a>
		<ul id="age_range" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">

				<% 
					if(facets[0].equals("age_range")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							//aszHashSearch += "fq="+aszContentType;
							try{
								if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
								if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							}catch(Exception e){}
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
							if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_age_range" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_age_range'); return false;" style="display:inline" href="#age_range">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_gender" >
		<a class="handle" onClick="toggle_display('gender')"><span class="i bullet">&nbsp;</span> <span class="label">Gender:</span></a>
		<ul id="gender" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">

				<% 
					if(facets[0].equals("gender")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							//aszHashSearch += "fq="+aszContentType;
							try{
								if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
								if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							}catch(Exception e){}
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
							if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_gender" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_gender'); return false;" style="display:inline" href="#gender">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle expanded" id="facet_intern_length" >
		<a class="handle" onClick="toggle_display('intern_length')"><span class="i bullet">&nbsp;</span> <span class="label">Length of Internship:</span></a>
		<ul id="intern_length" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("intern_length")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							//aszHashSearch += "fq="+aszContentType;
							try{
								if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
								if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							}catch(Exception e){}
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
							if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_intern_length" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_intern_length); return false;" style="display:inline" href="#intern_length">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>	

<!-- 
			<li class="filter handle expanded" id="facet_province">
				<a class="handle" onClick="toggle_display('province')"><span class="i bullet">&nbsp;</span> <span id="facet_province_label" class="label">State/Province:</span></a>
				<ul id="province" class="tagcloud toggle" >
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("province")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						}
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_province" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_province'); return false;" style="display:inline" href="#province">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
 -->		
			<li class="filter handle expanded" id="facet_city_tax">
				<a class="handle" onClick="toggle_display('city_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Metro Area:</span></a>
				<ul id="city_tax" class="tagcloud toggle" >
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("city_tax")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
//						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
							}
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_city_tax" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_city_tax'); return false;" style="display:inline" href="#city_tax">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
	<li class="filter handle expanded" id="facet_geo_pref">
		<a class="handle" onClick="toggle_display('geo_pref')"><span class="i bullet">&nbsp;</span> <span class="label">Geographic Preference:</span></a>
		<ul id="geo_pref" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("geo_pref")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
						}
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_geo_pref" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_geo_pref'); return false;" style="display:inline" href="#geo_pref">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_country_tax">
		<a class="handle" onClick="toggle_display('country_tax')"><span class="i bullet">&nbsp;</span> <span id="facet_country_tax_label" class="label">Country:</span></a>
		<ul id="country_tax" class="tagcloud toggle" >
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("country_tax")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						//aszHashSearch += "fq="+aszContentType;
						try{
							if((aszHashSearch.endsWith("&fq=") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
							if((aszHashSearch.endsWith("&fq=&") ) ) aszHashSearch = aszHashSearch.substring(0,aszHashSearch.lastIndexOf("&fq="));
						}catch(Exception e){}
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
						if(facets[1].length()>0){
				%>
						<li <%=aszDisplayClass%>>
							<a href="<%=request.getContextPath() %><%= aszPortal %>/oppsrch.do?method=processSearch&cvintern=true&<%=aszHashSearch%>#fq=cvintern_applicant:&quot;City Vision Intern Applicant&quot;&<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
							iCountDisplay++;
						} 
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_country_tax" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_country_tax'); return false;" style="display:inline" href="#country_tax">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		


		</ul>
	</li>
</ul>


<!-- 
      <h3>By Date</h3>
      <div id="calendar"></div>
      <br /><br />
      <h3>By Country</h3>
      <div id="countries"></div>
      <div id="preview"></div>
<br /><br /><br /><br /><br />

 -->

      <div class="clear"></div>

	</div>
</div>











<!-- end sidebar information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<% //@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;"><div id="contenttype_title">Internship Candidates</div></span>

<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; opportunities search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->

  <div id="hd" class="solr">
	<h2 class="shorter"><span id="filter_data_heading"><%=aszKewordsSpaceSeparated%></span><span id="contenttype_heading1">Internship Candidates</span></h2>
	<%@ include file="/template_include/applic_navigation_search_bar_solr.inc" %>
  </div>

<br clear="all"/>

<img src="http://www.christianvolunteering.org/imgs/star.jpg" width="12px" title="Recommended Candidate Based on Screening Interview"/> <span style="color:#777777;">Recommended Candidate Based on Screening Interview</span>

<!--br clear="all"/-->
	<div id="nav">
		<ul id="pager" class="pager"></ul>  <ul id="pager-header" class="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
	</div>
	<br clear="all">


<div id="map_container" style="display:none;">
	<br />
	<div id="map" style="width: 100%; height: 400px;">
	</div>
	<br /><br />
</div>

		<div id="search-results" style="display:none;">
	
<logic:notEmpty name="alist" >
	<logic:iterate id="applic" name="alist" type="com.abrecorp.opensource.dataobj.ApplicationInfoDTO">
<%			
		String aszCity= applic.getCityTax();
		String aszCountry= applic.getAddrCountryName();
		String aszProvince = applic.getAddrStateprov();
		String aszOppLocation = "";
		if (aszCity.length() > 1) {
			aszOppLocation = aszCity;
		}
		if((! aszCountry.equalsIgnoreCase("United States")) && aszCountry.length()>0 ){
			if(aszCity.length()>0 ) {
				aszOppLocation+=", ";
			}
			aszOppLocation += aszCountry;
		}
		if(aszOppLocation.length() == 0){
			aszOppLocation = aszProvince;
		}
		aszTitlePrint = applic.getTitle();
		if(aszTitlePrint.length()==0){
			aszTitlePrint = applic.getNameFirst() + " " + applic.getNameLast();
		}
		//popularity
		
		// program in forwarding if the opportunity is Faith-Filled
		String aszOPPUrlAlias="email.do?method=loadOneApplication&nid="+applic.getNID();
		if(applic.getSource().equals("sf") || applic.getSource().equalsIgnoreCase("salesforce")){
			aszOPPUrlAlias += "&src=sf";
		}
		String aszFeedsClass="";
		boolean feed=false;
		if(aszOPPUrlAlias.contains("feed/")){
			feed=true;
			aszFeedsClass="feeds";
			iFeedsResults++;
		}
		aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias;
		String aszMemberClass="class=\"listing\"";

		String aszLocation = aszOppLocation;
		
		String aszInformation = "";

		String	aszInternType = "";
		String[] a_aszContainer = applic.getInternTypesArray();
		for(int i=0; i<a_aszContainer.length; i++){
			if(i>0)	aszInternType += ", ";
			aszInternType += a_aszContainer[i];
		}
		aszInternType += applic.getInternTypes();
		String aszPosPref = applic.getPosPref();
		String aszGeoPref = applic.getGeoPref();
		String aszHasBachelors = applic.getHasBachelors();
		String aszInternLength = applic.getInternLength();
		String aszGender = applic.getGender();
		String aszCVInternApplic = applic.getCVInternApplic();
		String aszAgeRange = applic.getAgeRange();
		String aszCreditsRange = applic.getCreditsRange();
		
		if(aszHasBachelors.equals("Yes"))		aszInformation += "<strong>Has Bachelor's Degree</strong><br>";
		if(aszInternType.length()>0)	aszInformation += "<strong>Intern Type:</strong>&nbsp;" + aszInternType + "<br>";
		if(aszPosPref.length()>0)		aszInformation += "<strong>Position Preference:</strong>&nbsp;" + aszPosPref + "<br>";
		
		if(aszLocation.length()>0)			aszInformation +="<strong>Location:</strong>&nbsp;"+aszLocation + "<br>";
		if(aszGeoPref.length()>0)		aszInformation += "<strong>Geographic Preference:</strong>&nbsp;" + aszGeoPref + "<br>";
		
		if(aszInternLength.length()>0)		aszInformation += "<strong>Internship Length:</strong>&nbsp;" + aszInternLength + "<br>";
		if(aszGender.length()>0)		aszInformation += "<strong>Gender:</strong>&nbsp;" + aszGender + "&nbsp;&nbsp;&nbsp;";
		if(aszAgeRange.length()>0)		aszInformation += "<strong>Age Range:</strong>&nbsp;" + aszAgeRange + "&nbsp;&nbsp;&nbsp;";
		if(aszCreditsRange.length()>0)		aszInformation += "<strong>Earned Credits:</strong>&nbsp;" + aszCreditsRange + "<br>";
		
		// also display last updated
		java.util.Date d_LastUpdated = null;
		d_LastUpdated = applic.getLastUpdatedDt();
		String aszLastUpdated = new java.text.SimpleDateFormat("MM/dd/yyyy").format(d_LastUpdated);
		if((System.currentTimeMillis()-d_LastUpdated.getTime()) > 10000){
			if(d_LastUpdated != null)		
			aszInformation += "<strong>Last Updated:</strong>&nbsp;" + aszLastUpdated + "<br>";
//			aszInformation += "<strong>Last applic.getLastUpdatedDt().getTime():</strong>&nbsp;" + applic.getLastUpdatedDt().getTime() + "<br>";
//			aszInformation += "<strong>Last System.currentTimeMillis():</strong>&nbsp;" + System.currentTimeMillis() + "<br>";
		}
		
		String aszHighlighted = "";

		int iScreened = applic.getScreened();		
		if(iScreened == 2){
			aszHighlighted = "&nbsp;&nbsp;&nbsp;<img src=\"/imgs/star.jpg\" width=\"12px\">";
		}


%>

<!--  iScreened is <%=""+iScreened%> -->


			<div <%=aszMemberClass%>>
				<%iCounter++;%><%=iCounter%>.&nbsp;
				<a class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszTitlePrint%></a><%=aszHighlighted %>
				&nbsp;&nbsp;
				
				<div class="opp-info">
					<%=aszInformation%>
				</div>
			</div>
		<hr />

	</logic:iterate>
</logic:notEmpty>
		</div>

	<table id="docs" border="0"></table>
	

<div id="nav_close" class="nav">
<ul id="pager_close" class="pager"></ul>  <ul id="pager-header_close" class="pager-header"></ul> 
</div>

</div>


<br><br>
		

</div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %>
<!-- end footer information -->


