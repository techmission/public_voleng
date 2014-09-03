<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<%@page import="java.text.DecimalFormat" %>

<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>
<%
// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszOrgName = searchinfo.getOrgName();
String aszLandingParamsURL="";

String aszTempCountry= searchinfo.getCountry();
String aszTempRegion= searchinfo.getRegion();

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.churchvolunteering.org")){
	aszSubdomain = "ChurchVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}

String aszDetails = "", aszCountryName="", aszRegionName="";
if(aszOrgName.length()>0){
	aszDetails+= aszOrgName + ": ";
}
	// output city searched
	if (!(searchinfo.getCity().equalsIgnoreCase(""))){
		aszDetails+= searchinfo.getCity() + ": ";
	}
	// output country searched
	if(null != aCountryList){
		for(int index=0; index < aCountryList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
			if(null == aAppCode) continue;
			String aszOptRqCode = aAppCode.getCTRIso();
			if((aszOptRqCode.equalsIgnoreCase( aszTempCountry ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
				aszDetails+= aAppCode.getCTRPrintableName() + ": ";
				aszCountryName= aAppCode.getCTRPrintableName() +",";
			}
		}
	}
	// output region searched
	if(null != aRegionList){
		for(int index=0; index < aRegionList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
			if(null == aAppCode) continue;
			String aszOptRqCode = aAppCode.getAPCDisplay();
			String aszOptRqCode2 = aszOptRqCode.substring(0,3);
			if((aszOptRqCode.equalsIgnoreCase( aszTempRegion ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
				if ((!(aszOptRqCode2.equalsIgnoreCase( "USA" ))) && (!(aszOptRqCode.equalsIgnoreCase( "Canada" ))) ){
					aszDetails+= aAppCode.getAPCDisplay() + ": ";
					aszRegionName= aAppCode.getAPCDisplay() +",";
				}
			}
		}
	}
%>



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
	<title><%=aszSubdomain%>: 

<%
	if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
Volunteer Opportunities</title>
<% 
	}else{ 
%>
Christian Volunteer and Short Term Missions Opportunities</title>
<% 
	}
%>
<meta name="description" content="Search <%=aszOrgOrChurchOpp%> Opportunities: <%=aszSubdomain%>" />
<meta name="keywords" content="volunteer, Christian, church, Christian volunteering, Christian volunteer, church volunteer, nonprofit, community,service, urban ministry, youth, social justice, missions, short term missions, missionary, virtual volunteer, volunteer match, volunteer solutions, online volunteering, volunteer online, activism">
<%
}
%> 

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Opportunities Directory</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% } %>


<script language="javascript">
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
					list_element.style.display="block";
				}
				list_element.className=default_class+expanded_class;
			}
		}
	}
}
</script>
<style>
#contentwrapper{
	background:#ECF1FC;
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

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results directory">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Opportunities Search Results</span>

<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/register.do?method=showIndReg1"><%out.print(aszOrgOrChurchOpp.toLowerCase());%></a> &gt; opportunities search results  </div>
</div>
<% } %>

<div id="body">
<!-- portal <%=aszPortal%> -->
<br>

<div id="results_type" style="display:none;">opportunity</div>

<div id="sorts">
</div>


<% if( 	aszMobileSubdomain.length() < 3 ) { %>
<H1>
<%=aszOrgOrChurchOpp%> 
<% if (searchinfo.getOPPPositionTypeTID()==iShortTerm) { %>
& Short Term <% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )) { %>Service <% }else{ %>Missions <% } %>
<% } %>
Opportunities
<%
// output city searched
if (!(searchinfo.getCity().equalsIgnoreCase(""))){
	out.print(": " + searchinfo.getCity());
}
		// output country searched
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(	(aszOptRqCode.equalsIgnoreCase( aszTempCountry ) ) 	&& 
					(!(aszOptRqCode.equalsIgnoreCase( "USA" ) ))	&&
					(!(aszOptRqCode.equalsIgnoreCase( "US" ) ))	&&
					(!(aszOptRqCode.equalsIgnoreCase( "" ) ))
				) {
				out.print(": " + aAppCode.getCTRPrintableName());}
			}
		}
		// output region searched
		if(null != aRegionList){
			for(int index=0; index < aRegionList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				String aszOptRqCode2 = aszOptRqCode.substring(0,3);
				if((aszOptRqCode.equalsIgnoreCase( aszTempRegion ) ) && (!(aszOptRqCode.equalsIgnoreCase( "" ) ))) {
					if ((!(aszOptRqCode2.equalsIgnoreCase( "USA" ))) && (!(aszOptRqCode.equalsIgnoreCase( "Canada" ))) ){
						out.print(": " + aAppCode.getAPCDisplay());
					}
				}
			}
		}

%>
</H1>      
<% } %>

<%@ include file="/template_include/directory_page_facet_links.inc" %>

<%
if(aszFQParams.endsWith("&")){
	aszFQParams = aszFQParams.substring(0,aszFQParams.length()-1);
}
if(aszFQParams.startsWith("&")){
	aszFQParams = aszFQParams.substring(1);
}
%>
<ul class="filters" id="filters">		
	<li class="filter handle expanded directory" id="facet_service_areas">
		<a class="handle" onClick="toggle_display('service_areas')"><span class="i">&nbsp;</span> <span class="label">Service Area:</span></a>
					<ul id="service_areas" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("service_areas")){ 
						String aszFacets0 = "sa";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
		
	<li class="filter handle expanded directory" id="facet_position_type">
		<a class="handle" onClick="toggle_display('position_type')"><span class="i">&nbsp;</span> <span class="label">Position Types:</span></a>
					<ul id="position_type" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type")){ 
						String aszFacets0 = "pt";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
	
	<li class="filter handle expanded directory" id="facet_great_for">
		<a class="handle" onClick="toggle_display('great_for')"><span class="i">&nbsp;</span> <span class="label">Great for:</span></a>
					<ul id="great_for" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("great_for")){ 
						String aszFacets0 = "gf";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>		
		
	<li class="filter handle expanded directory" id="facet_frequency">
		<a class="handle" onClick="toggle_display('frequency')"><span class="i">&nbsp;</span> <span class="label">Frequency:</span></a>
					<ul id="frequency" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("frequency")){ 
						String aszFacets0 = "f";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle expanded directory" id="facet_benefits_offered">
		<a class="handle" onClick="toggle_display('benefits_offered')"><span class="i">&nbsp;</span> <span class="label">Benefits:</span></a>
					<ul id="benefits_offered" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("benefits_offered")){ 
						String aszFacets0 = "b";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&","~");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
		
<% // expand if STM, though %>
	<li class="filter handle expanded directory" id="facet_trip_length">
		<a class="handle" onClick="toggle_display('trip_length')"><span class="i">&nbsp;</span> <span class="label">Length of Trip:</span></a>
					<ul id="trip_length" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("trip_length")){ 
						String aszFacets0 = "tl";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>
		
	<li class="filter handle expanded directory" id="facet_region">
		<a class="handle" onClick="toggle_display('region')"><span class="i">&nbsp;</span> <span class="label">Region:</span></a>
					<ul id="region" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("region")){ 
						String aszFacets0 = "r";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
	</li>		
</ul>
<br clear="all" />
<ul class="filters" id="filters">				
<% // expand if STM, though %>
	<li class="filter handle expanded" id="facet_adv">
		<a class="handle" onClick="toggle_display('advanced_facets')"><span class="i">&nbsp;</span> <span class="label">Advanced</span></a>
		<ul id="advanced_facets" class="filters" style="display:block;">
		
			<li class="filter handle expanded directory advanced" id="facet_country_tax">
				<a class="handle" onClick="toggle_display('country_tax')"><span class="i">&nbsp;</span> <span class="label">Country:</span></a>
					<ul id="country_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("country_tax")){ 
						String aszFacets0 = "ctr";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
		
			<li class="filter handle expanded directory advanced" id="facet_province_tax">
				<a class="handle" onClick="toggle_display('province_tax')"><span class="i">&nbsp;</span> <span class="label">Province:</span></a>
					<ul id="province_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("province_tax")){ 
						String aszFacets0 = "st";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
		
			<li class="filter handle expanded directory advanced" id="facet_city_tax">
				<a class="handle" onClick="toggle_display('city_tax')"><span class="i">&nbsp;</span> <span class="label">Metro Area:</span></a>
					<ul id="city_tax" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("city_tax")){ 
						String aszFacets0 = "ct";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
				
			<li class="filter handle expanded directory advanced" id="facet_afg">
				<a class="handle" onClick="toggle_display('source')"><span class="i">&nbsp;</span> <span class="label">Search from Third Party Sites:</span></a>
					<ul id="source" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
								<% if(facets[0].equals("source")){ %>
									<%
										String aszSourcePhrase = facets[1];
										if(aszSourcePhrase.equalsIgnoreCase("AllForGood")){
											aszSourcePhrase = "<img src=\"http://www.christianvolunteering.org/imgs/afg-blue_60.png\">";
									%>
										<a href="/oppsrch.do?method=processSearch<%=aszFQParams%>&fq=content_type:opportunity&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;#<%=aszFQParams%>&fq=content_type:opportunity&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;"><%=aszSourcePhrase%> (<%=facets[2]%>)</a>
										<br> 
								<% }} %>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>		
</ul>
<br clear="all" />
<ul class="filters" id="filters">				
<% // expand if org is selected, though %>
			<li class="filter handle expanded directory advanced" id="facet_denom_affil">
				<a class="handle" onClick="toggle_display('denom_affil')"><span class="i">&nbsp;</span> <span class="label">Denomination:</span></a>
					<ul id="denom_affil" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("denom_affil")){ 
						String aszFacets0 = "d";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li>
<% // expand if org is selected, though %>
			<li class="filter handle expanded directory advanced" id="facet_org_affil">
				<a class="handle" onClick="toggle_display('org_affil')"><span class="i">&nbsp;</span> <span class="label">Organizational Affiliation:</span></a>
					<ul id="org_affil" class="tagcloud toggle comma" style="display:block;">
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("org_affil")){ 
						String aszFacets0 = "na";
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", "~~");
						String aszFacets1Hash = facets[1].replaceAll("&","%26");
				%>
						<a href="/s<%=aszFQParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/results.jsp#fq=<%=facets[0]%>:&quot;<%=aszFacets1Hash%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>
						<br>	
				<% 		
					} 
				%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
			</li> 
		</ul>
	</li>
</ul>

<!--
	<div id="nav">
		<ul id="pager"></ul>  <div id="pager-header"></div>
	</div>
	<br>
	<div id="docs"></div>
-->
</div>



<br><br>


<!-- portal listings? -->
<% if(true == LoginBean.IsSessionLoggedIn( request ) && true==false){ %>
<!-- logged in -->
	<% if(aszPortal.length()>0  ){ %>
<!-- has portal -->
		<% if(	aszHostID.equalsIgnoreCase("volengchurch")	|| 
				aszHostID.equalsIgnoreCase("voleng1")		|| 
				aszHostID.equalsIgnoreCase("volengexample")	|| 
				aszHostID.equalsIgnoreCase("default")
		){ %>	
<!-- correct domain -->
			<% if(bSkipBanner==false){ %>
				<br>
					<div id="embedcollapse" onClick="toggle_embedcontent()" class=" collapsed">
						<legend class="collapse-processed">
							<span class="fieldset-legend">Embed on Your Site&nbsp;&nbsp;
			<!--a href="#embed_div" onClick="show_embed_div()">Embed on Your Site &gt;</a-->
							</span>
						</legend>
					</div>
				<%@include file="/template_include/embed_options.inc"%>
			<% } %>
		<% } %>
	<% } %>
<% } %>
		

</div></div>
<!-- start sidebar information --><%//@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->

