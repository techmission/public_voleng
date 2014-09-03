<%@ include file="/template_include/topjspnologin1.inc" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Christian Family Volunteering: Search thousands of family-friendly missions trips and volunteer opportunities: ChristianVolunteering.org. Find opportunities in urban ministry, orphanages and medical missions.</title>
<meta name="keywords" content="family volunteer, family missions trips, christian family, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, volunteer online, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Search thousands of family-friendly volunteer opportunities and missions trips! Find opportunities in urban ministry, orphanages and medical missions.">
<meta name="robots" content="index, follow, noarchive">

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

String aszLandingParamsURL = "gf/Great_for_Families";
String aszLandingParamsHash = "fq=great_for:%22Great%20for%20Families%22";
%>

<script language="javascript">
$(document).ready(function() {
	$('input#query').val('Great for Families - (keyword)');
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Family Volunteer Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Family Volunteer Organizations');

	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);
})
</script>

<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage">
	<div style="float:left;">
		<img src="imgs/family_chrisvol_org.jpg" alt="Search thousands of family-friendly volunteer opportunities and missions trips! Find opportunities in urban ministry, Christian internships, and church volunteering."  width="417" height="234" />
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%@ include file="/template_include/directory_page_facet_links.inc" %>
<% iNumDisplay=5; %>

	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		<div id="midbox1_content"><!-- style="height: 400px;"-->
		    <br/>
		
            <h2><p>Family Volunteering Opportunities</p></h2>
        
			<p>
				<li class="filter handle expanded landingpage" id="facet_service_areas">
					<a class="handle" onClick="toggle_display('service_areas')"><span class="label">By Service Area:</span></a>
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
				<li class="filter handle expanded landingpage" id="facet_primary_opp_type">
					<a class="handle" onClick="toggle_display('primary_opp_type')"><span class="label">By Type:</span></a>
					<ul id="primary_opp_type" class="tagcloud toggle comma" style="display:block;">
						<% iCountDisplay=0; %>
						<logic:notEmpty name="facetlist" >
							<logic:iterate id="facets" name="facetlist" type="String[]">
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
				<li class="filter handle expanded landingpage" id="facet_province_tax">
					<a class="handle" onClick="toggle_display('province_tax')"><span class="label">By State:</span></a>
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
					<a class="handle" onClick="toggle_display('city_tax')"><span class="label">By City:</span></a>
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
					<a class="handle" onClick="toggle_display('country_tax')"><span class="label">By Country:</span></a>
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
					<a class="handle" onClick="toggle_display('region')"><span class="label">By Region:</span></a>
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
		<img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" />
	</div>
	
	<div id="midbox2" class="landingpage" style="font-size: 12px;">
		<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
		<div id="midbox2_photo">
			<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="/imgs/HowThisSiteWorks.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content"><!-- style="height: 400px;"-->
			  <br />
  <h2><p>How Family Volunteering Works</p></h2>
<p>
Family.ChristianVolunteering.org helps connect you with local family volunteer opportunities and family missions trips that are fun and appropriate for all ages.</p>

		 <ol><li><b><a href="/advancedsearch.jsp" alt="Discover Christian Family Volunteer Opportunities" title="Search Christian family volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">Search for Christian Family Volunteer Opportunities</a></b>.</li>
		 <li><b><a href="/individualregistration.jsp" title="Register for Christian family volunteer, family missions trips, virtual volunteer, and urban ministry service opportunities" alt="Sign up here for Christian Family Volunteer Opportunities"><strong>Register to get a free account</strong></a></b> for your family.</li>
		 <li>Click &quot;I want to volunteer&quot; to be matched with the organization by E-mail. </li>
		 <li>The organization will get back to you with the next steps for your family volunteering experience.</li>

		 <li>Protect your family by researching and checking references on the organizations you consider, especially for family missions trips.</li>
		</div>
		<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" />
	</div>
	
	<div id="midbox3" class="landingpage" style="font-size: 12px;">
		<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
		<div id="midbox3_photo">
			<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html"><img src="/imgs/EquippingFamily.gif" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox3_photo-->  
		<div id="midbox3_content"><!-- style="height: 400px;"-->
<br>
	 <h2><a href="http://www.urbanministry.org/family_volunteering" title="Family Urban Ministry Training"><p>Christian Family Volunteer Training</p></a></h2>
	  	
		<p><b><a href="http://www.urbanministry.org/family_volunteering">Family Volunteering Resources</a></b></p>
		
		<h3><b><a href="http://www.urbanministry.org/faceted_search/results/content_type%3Avideo+taxonomy%3A7469" title="Christian Family Volunteering Videos"><p>Videos</p></a></b></h3>

		
		<ul><li><b><a href="http://www.urbanministry.org/raising-charitable-children-mom-matters-episode-3">Raising Charitable Children (Mom Matters)</a></b></li>
		
		<li><b><a href="http://www.urbanministry.org/family-mission-project-devotional-0">Family Mission Project Devotional</a></b></li>
		
		<li><b><a href="http://www.urbanministry.org/service-learning-opportunities-better-tv">Service Learning Opportunities (Better TV)</a></b></li></ul>
		
		<h3><b><a href="http://www.urbanministry.org/faceted_search/results/content_type%3Aamazon_node+taxonomy%3A7469" title="Christian Family Volunteering Books"><p>Books<p></a></p></h3>
		
		<ul><li><b><a href="http://www.urbanministry.org/giving-family-raising-our-children-help-others">The Giving Family</a></b></li>
		
		<li><b><a href="http://www.urbanministry.org/raising-kids-who-will-make-difference-helping-your-family-live-integrity-value-simplicity-and-care-o">Raising Kids Who Will Make a Difference</a></b></li>

		
		<li><b><a href="http://www.urbanministry.org/what-kids-really-want-money-cant-buy">What Kids Really Want that Money Can't Buy</a></b></li>
		</ul>
		
		<h3><b><a href="http://www.urbanministry.org/faceted_search/results/content_type%3Aweblink+taxonomy%3A7469" title="Christian Family Volunteering Links"><p>Links</p></a></b></h3>	
		
		<ul>
		<li><b><a href="http://www.sixseeds.org">SixSeeds.org</a></b></li>
		<li><b><a href="http://doinggoodtogether.org/index.html">Doing Good Together: Raising Caring Kids</a></b></li>
		<li><b><a href="http://www.parenthood.com/article-topics/kids_amp_volunteering_getting_started.html">Kids & Volunteering: Getting Started</a></b></li>

</ul>
		</div>
		<img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
	</div>
</div>

<div class="cleardiv"></div>


<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="num_types_in_facet">5</div>
	<div id="page_type">landingpage</div>

	<div id="keyword_search">great_for:"Great for Families"</div>
	<div id="fq_search">great_for:"Great for Families"</div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>


<script type="text/javascript" src="/template_include/js/display_toggle_landingpage.js"></script>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
