<%@ include file="/template_include/topjspnologin1.inc" %>

<meta name="robots" content="index, follow, noarchive">

<title>Christian Gap Year Short-Term Missions Trip Search & Christian Ministry Internships: ChristianVolunteering.org</title>
<meta name="keywords" content="Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYchristian gap year, christian gap year missions trip, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering,WI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Search over thousands of Christian Gap Year missions trips and volunteer opportunities! Find opportunities in urban ministry, Christian internships, and church volunteering.">

<% bHeaderSet=true; %>
<%@ include file="/template/home_header.inc" %>
<!-- end wide header information -->
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->






<% 
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );

String aszLandingParamsURL = "pt/Short-term_Missions_|_Volunteer_Internship";
String aszLandingParamsHash = "fq=position_type:%22Short-term Missions / Volunteer Internship%22";
%>

<script language="javascript">
$(document).ready(function() {
	window.location.hash='fq=content_type:opportunity&fq=intern_type:"City Vision Internship"';

	$('#srchmethod').val('Short-term Missions / Volunteer Internship');
	$('.srchmethod').val('Short-term Missions / Volunteer Internship');
	
	$('#search_input').find('a:first').attr('href', '/s/pt/Short-term_Missions_|_Volunteer_Internship/ctp/opp/results.jsp"#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Short-term Missions Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/pt/Short-term_Missions_|_Volunteer_Internship/ctp/org/results.jsp"#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Short-term Missions Organizations');

	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);

	$('input#queryLoc').val("<%=maxmind_postal%> (zip code)");
//	$('input#query').val("Hurricane Sandy - (Service Area)");
	$('#position_type_local').hide();
	$('#position_type_virtual').hide();
	$('#content_type').hide();
	$('#member_type').hide();
	$('a#stm').text('Featured Christian Internships');
	$('stm').attr('href', '<%=aszPortal%>/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=org_nid:73734&fq=content_type:opportunity');
	$('#stm').removeClass('inactive').addClass('active');
});
</script>

<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div style="float:left;">
		<img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Christian Gap Year missions trips and volunteer opportunities! Find opportunities in urban ministry, Christian internships, and church volunteering."  width="417" height="234" />
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%//@ include file="/template_include/landing_page_facet_links.inc" %>
<%@ include file="/template_include/directory_page_facet_links.inc" %>
<% iNumDisplay=5; // ? not sure why only showing 5? %>
	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		
		<div id="midbox1_content"><!-- style="height: 400px;"-->
			<br>
			<h2 class="head"><p>Short Term Missions & Internships by Interest</p></h2>
			<p>
				<li class="filter handle expanded landingpage" id="facet_service_areas">
					<a class="handle" onClick="toggle_display('service_areas')"><span class="label">Opportunities By Service Area:</span></a>
					<ul id="service_areas" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("service_areas")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "sa";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_service_areas'); return false;" style="display:inline" href="#service_areas">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			 </p>
             
             <p>
				<li class="filter handle expanded landingpage" id="facet_skills">
					<a class="handle" onClick="toggle_display('skills')"><span class="label">Opportunities By Skills:</span></a>
					<ul id="skills" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0;%>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("skills")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "sk";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_skills'); return false;" style="display:inline" href="#skills">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			 </p>
		
			<p>
				<li class="filter handle expanded landingpage" id="facet_benefits_offered">
					<a class="handle" onClick="toggle_display('benefits_offered')"><span class="label">Opportunities By Benefits:</span></a>
					<ul id="benefits_offered" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("benefits_offered")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "b";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_benefits_offered'); return false;" style="display:inline" href="#benefits_offered">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
            <p>
				<li class="filter handle expanded landingpage" id="facet_trip_length">
					<a class="handle" onClick="toggle_display('benefits_offered')"><span class="label">Opportunities By Trip Length:</span></a>
					<ul id="trip_length" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("trip_length")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "tl";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_trip_length'); return false;" style="display:inline" href="#trip_length">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
            <p>
				<li class="filter handle expanded landingpage" id="facet_great_for">
					<a class="handle" onClick="toggle_display('great_for')"><span class="label">Opportunities By Great For:</span></a>
					<ul id="great_for" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("great_for")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "gf";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_great_for'); return false;" style="display:inline" href="#great_for">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
		</div>
		<img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" />
	</div>
	
	<div id="midbox2" class="landingpage">
		<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
		<div id="midbox2_photo">
			<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/organizationbox.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content"><!-- style="height: 400px;"-->
			<br/>
			<h2 class="head"><p>Short Term Missions Opportunities by Location</p></h2>

            <p>
				<li class="filter handle expanded landingpage" id="facet_province_tax">
					<a class="handle" onClick="toggle_display('province_tax')"><span class="label">Opportunities By State:</span></a>
					<ul id="province_tax" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("province_tax")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "st";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_province_tax'); return false;" style="display:inline" href="#province_tax">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
            <p>
				<li class="filter handle expanded landingpage" id="facet_city_tax">
					<a class="handle" onClick="toggle_display('city_tax')"><span class="label">Opportunities By City:</span></a>
					<ul id="city_tax" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("city_tax")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "ct";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_city_tax'); return false;" style="display:inline" href="#city_tax">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
            <p>
				<li class="filter handle expanded landingpage" id="facet_country_tax">
					<a class="handle" onClick="toggle_display('country_tax')"><span class="label">Opportunities By Country:</span></a>
					<ul id="country_tax" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("country_tax")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "ctr";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_country_tax'); return false;" style="display:inline" href="#country_tax">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            
			<p>
				<li class="filter handle expanded landingpage" id="facet_region">
					<a class="handle" onClick="toggle_display('region')"><span class="label">Opportunities By Region:</span></a>
					<ul id="region" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
							<% 
								if(facets[0].equals("region")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "r";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:opportunity"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_region'); return false;" style="display:inline" href="#region">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
		</div>
		<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" />
	</div>
	
	<div id="midbox3" class="landingpage">
		<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
		<div id="midbox3_photo">
			<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html"><img src="<%=aszPortal%>/imgs/missions_westafrica.jpg" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox3_photo-->  
		<div id="midbox3_content"><!-- style="height: 400px;"-->
			<br/>
			<h2 class="head"><p>Short Term Missions Organizations</p></h2>
			<p>
				<li class="filter handle expanded landingpage" id="facet_primary_opp_type">
					<a class="handle" onClick="toggle_display('primary_opp_type')"><span class="label">Organizations By Focus:</span></a>
				    <ul id="primary_opp_type" class="tagcloud toggle comma org" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
							<% 
								if(facets[0].equals("primary_opp_type")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "pot";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszLandingParamsURL%>/<%=aszFacets0%>/<%=aszFacets1%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_primary_opp_type'); return false;" style="display:inline" href="#primary_opp_type">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            <p>
				<li class="filter handle expanded landingpage" id="facet_org_affil">
					<a class="handle" onClick="toggle_display('org_affil')"><span class="label">Organizations By Affiliation:</span></a>
				    <ul id="org_affil" class="tagcloud toggle comma org" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
							<% 
								if(facets[0].equals("org_affil")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "na";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_org_affil'); return false;" style="display:inline" href="#org_affil">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            <p>
				<li class="filter handle expanded landingpage" id="facet_country_tax2">
					<a class="handle" onClick="toggle_display('country_tax')"><span class="label">Organizations By Country:</span></a>
				    <ul id="country_tax2" class="tagcloud toggle comma org" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
							<% 
								if(facets[0].equals("country_tax")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "ctr";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_country_tax2'); return false;" style="display:inline" href="#country_tax">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
            <p>
				<li class="filter handle expanded landingpage" id="facet_region2">
					<a class="handle" onClick="toggle_display('region')"><span class="label">Organizations By Region:</span></a>
				    <ul id="region2" class="tagcloud toggle comma org" style="display:block;">
						<% iCountDisplay=0; %>					
						<logic:notEmpty name="facetorglist" >
							<logic:iterate id="facets" name="facetorglist" type="String[]">
							<% 
								if(facets[0].equals("region")){ 
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
									String aszFacets0 = "r";
									String aszFacets1 = facets[1].replaceAll(" ","_");
									aszFacets1 = aszFacets1.replaceAll("/","|");
									aszFacets1 = aszFacets1.replaceAll("&", "~");
									aszFacets1 = aszFacets1.replaceAll("\\.", ";");
									if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\"";
									else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							%>
									<span <%=aszDisplayClass%>>
										<a href="<%=aszPortal%>/s/<%=aszFacets0%>/<%=aszFacets1%>/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=<%=facets[0]%>:&quot;<%=facets[1].replaceAll("&", "%26")%>&quot;&fq=content_type:organization"><%=facets[1]%> (<%=facets[2]%>)</a>,&nbsp;
									</span>	
							<% 		if(iCountDisplay == iNumDisplay){ // ouptut more link		%>
									<span style="display:inline;" id="more_link_facet_service_areas" class="filter expanded">
										<a onclick="toggle_facet('facet_region2'); return false;" style="display:inline" href="#region">See more &gt;&gt;</a>
									</span>							
							<%
									}
									iCountDisplay++;
								} 
							%>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</li>
			</p>
		</div>
		<img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
	</div>
	
</div>

<div class="cleardiv"></div>

<div id="endingbox" class="landingpage">
<div id="" class="search_results" style="padding:0px 20px;">
<% 
aszFQParams=aszLandingParamsHash;
aszFQParamsURL=aszLandingParamsURL;
String aszOrgNamePrint="";
String aszOppTitlePrint="";
String aszContentType="";
String aszKewordsSpaceSeparated="";
int iCounter=0;
//String DecimalFormat="";
%>

<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search">organization_name:&quot;City Vision&quot;&org_nid:73734</div>
	<div id="fq_search">organization_name:&quot;City Vision&quot;&org_nid:73734</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQParams is <%=aszFQParams%>
</div>

  <div id="hd" class="solr" style="padding: 5px 0px;">
	<%@ include file="/template_include/navigation_search_bar_solr_no_search_postal.inc" %>
  </div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
<a href="http://www.christianvolunteering.org/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=org_nid:73734&fq=content_type:opportunity&fq=organization_name:&quot;City%20Vision&quot;">City Vision Internships</a>

<%@ include file="/template_include/search_sidebar.inc" %>
</div><!-- end solr_results div -->

<br clear="all"/>

<div class="results-left" id="all_tabs">
	<div id="position_type" style="display: none;">
		<div class="results-left" id="position_type_local" style="display: none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Local Volunteering (in person)") && iCountDisplay==0){ 
				%>
					<a class="active" id="local" href="">Local Volunteering</a>
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="active" id="local" href="">Local Volunteering</a>
			<% } %>
		</div>
		<div class="results-left" id="position_type_virtual" style="display: none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("position_type") && facets[1].equals("Virtual Volunteering (from home)") && iCountDisplay==0){ 
				%>
					<a class="" id="virtual" href="">Virtual</a>
				<% 		
						iCountDisplay++;
					}
				%>
				</logic:iterate>
			</logic:notEmpty>
			<% if(iCountDisplay==0){ %>
				<a class="" id="virtual" href="">Virtual</a>
			<% } %>
		</div>
	</div>
    
		<div class="results-left" id="position_type_stm">
			<a class="" id="stm" href="<%=aszPortal%>/s/organization_name/City_Vision/org_nid/73734/ctp/opp/results.jsp#fq=organization_name:&quot;City Vision&quot;&fq=org_nid:73734&fq=content_type:opportunity">Featured Christian Internships</a>
		</div>
	
	<div id="content_type" style="display: none;">
		<div class="results-left" id="content_type_job_tab" style="display: none;">
			<a class="" id="job" href="b">Jobs</a>
		</div>
		<div class="results-left" id="content_type_tab" style="display: none;">
			<a class="" id="organization" href="">Organizations</a>
		</div>
		<div class="results-left" id="content_type_res_tab" style="display: none;">
			<a class="" id="resume" href="">Resum&eacute;s</a>
		</div>
	</div>
	
	<div id="member_type" style="display: none;">
		<div class="results-left" id="member_type_fdn_tab" style="display: none;">
			<a class="" id="Foundation" href="">Foundations</a>
		</div>
	</div>
    
	<br clear="all">
	<hr width="100%" size="2" color="#4D4D4D" style="margin-top: 0px; //margin-top:-7px;">
</div> <!-- end: div id all_tabs-->


	<div id="nav">
		<ul id="pager"></ul>  <ul id="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
	</div>
	<br clear="all">


<div id="map_container" style="display:none;">
	<br />
	<div id="map" style="width: 100%; height: 400px;">
	</div>
	<br /><br />
</div>

	
	<div id="docs" ></div>
	
	
<div id="simplyhired" style="display:none;">
</div>

	<div class="cleardiv"></div>
</div>
</div>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
