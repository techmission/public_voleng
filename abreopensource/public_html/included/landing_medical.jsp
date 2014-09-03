<%@ include file="/template_include/topjspnologin1.inc" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Medical Missions Trips Search: Thousands of Trips and Volunteer Opportunities: ChristianVolunteering.org</title>
<meta keywords="medical missions, medical missions trips, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision" />
<meta description="Search thousands of medical missions trips and volunteer opportunities! Find opportunities in urban ministries, Christian internships, and church volunteering." />

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

String aszLandingParamsURL = "sa/Health_and_Medicine/pt/Short-term_Missions_|_Volunteer_Internship";
String aszLandingParamsHash = "fq=position_type:%22Short-term Missions / Volunteer Internship%22&fq=service_areas:%22Health and Medicine%22";
%>

<script language="javascript">
$(document).ready(function() {
	$('#srchmethod').val('Short-term Missions / Volunteer Internship');
	
	$('input#query').val("Health and Medicine - (Service Area)");
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Medical Missions Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Medical Missions Organizations');

	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);
});
</script>

<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div style="float:left;"> <!-- img can have maximum width of about 485px; -->
		<img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Search thousands of medical missions trips and volunteer opportunities! Find opportunities in urban ministries, Christian internships, and church volunteering."  width="417" height="234" />
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%@ include file="/template_include/directory_page_facet_links.inc" %>

	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/orphanage_nepal2.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		<div id="midbox1_content"><!-- style="height: 400px;"-->
			<br>
			<h2 class="head"><p>Medical Missions Opportunities</p></h2>
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
			<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/Africa 468 (rcasenhiser).jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content"><!-- style="height: 400px;"-->
			<br/>
			<h2 class="head"><p>Medical Missions Opportunities by Location</p></h2>

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
			<h2 class="head"><p>Medical Missions Organizations</p></h2>
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
	<img src="http://www.christianvolunteering.org/imgs/endingbox_corner.gif" id="right_corner_endingbox"/>
	<div style="float:left;">
		<img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities" />
	</div>
	<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">
		<p><h2 style="margin-left:0;"><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="urban ministries, short term urban missions organizations, and churches">For Organizations Posting Opportunities</a></h2>
		<p>Create an account for your organization to post Christian <strong>short term missions </strong>and ministry internship opportunities to recruit volunteers online. </p> 
	  <p><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">Register to Recruit Volunteers</a></p>
		<p><a href="<%=aszPortal%>/register.do?method=showlogin" title="login">Login</a></p>
		<p><a href="http://www.urbanministry.org/tag/short_term_missions?theme=churchvol">Short Term Missions Videos</a></p>
      <p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A117?theme=churchvol">Short Term Missions Documents</a></p>
        <p><a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions?theme=churchvol">Short Term Missions Wiki Encyclopedia</a></p>
</div>
	
		
</div>

<div class="cleardiv"></div>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
    <div id="page_type">landingpage</div>  
	<div id="keyword_search">service_areas:"Health and Medicine"</div>
	<div id="fq_search">service_areas:"Health and Medicine"</div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>


<script type="text/javascript" src="/template_include/js/display_toggle_landingpage.js"></script>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
