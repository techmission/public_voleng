<%@ include file="/template_include/topjspnologin1.inc" %>

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ %>
	<jsp:include page="/ivol/virtual_vol.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% 
} else { 
	bHeaderSet=true; 
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Virtual Volunteer and Online Volunteering Opportunities: ChristianVolunteering.org</title>
<meta name="keywords" content="virtual volunteer, online volunteering, volunteer online, microvolunteering, microvolunteer, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, orphanage, medical missions, christian internship, ministry internship, community, service, urban ministry, youth, social justice, missions, missionary, virtual volunteer, online volunteering, TechMission, CCDA, UYWI, Salvation Army, City Union Mission, Gospel Mission, World Vision">
<meta name="description" content="Search over 1,000 Christian virtual volunteer opportunities! Volunteer online for ministries all over the world from your own home through microvolunteering.">

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

String aszLandingParamsURL = "pt/Virtual_Volunteering_(from_home)";
String aszLandingParamsHash = "fq=position_type:%22Virtual Volunteering (from home)%22";
%>

<script language="javascript">
$(document).ready(function() {
	$('#srchmethod').val('Virtual Volunteering (from home)');
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Virtual Volunteer Opportunities');

	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Virtual Volunteer Organizations');

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
		<img src="<%=aszPortal%>/imgs/pic/VolunteerNYourPJs4CVPage.jpg" alt="Search over 1,000 Christian virtual volunteer opportunities! Volunteer online for ministries all over the world from your own home through microvolunteering."  width="417" height="234" />
	</div>
	<%@ include file="/template_include/home_solr_search.inc" %>
</div>
<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>

<div class="cleardiv"></div>

<div id="result" class="midbox_section">
<%@ include file="/template_include/directory_page_facet_links.inc" %>
<% iNumDisplay=15; %>

	<div id="midbox1" class="landingpage">
		<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
		<div id="midbox1_photo">
			<a href="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/orphanage_nepal2.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions." width="295" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox1_photo-->
		
		<div id="midbox1_content"><!-- style="height: 400px;"-->
<br>
<h2><p><b>What is Virtual Volunteering and Online Volunteering?</b></p></h2>

	<p>Have you ever wanted to volunteer without having to travel? Do you have professional or technical 
	skills that you want to use in volunteering? Virtual volunteering allows you to do this by volunteering 
	online from 
	your home or office. 
	<p> Look around this page to find an opportunity using your skills, interests, and passions.</p>

	<p><a href="http://www.christianvolunteering.org/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity">View All Virtual Volunteering Opportunities</a></p>

		</div>
		<img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" />
	</div>
	
	<div id="midbox2" class="landingpage">
		<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
		<div id="midbox2_photo">
			<a href="<%=aszPortal%>/advancedsearch.jsp#searchShortTermMissions"><img src="<%=aszPortal%>/imgs/work-from-home2.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></a>
		</div><!-- end: midbox2_photo-->
		<div id="midbox2_content"><!-- style="height: 400px;"-->
			<br/>
			<p>
				<li class="filter handle expanded landingpage" id="facet_service_areas">
					<a class="handle" onClick="toggle_display('service_areas')"><span class="label">Browse By Service Area:</span></a>
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
		</div>
		<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" />
	</div>
	
	<div id="midbox3" class="landingpage">
		<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
		<div id="midbox3_photo">
			<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/Presentation_Files/index.html"><img src="<%=aszPortal%>/imgs/313251515_9d6929f671_b.jpg" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
		</div><!-- end: midbox3_photo-->  
		<div id="midbox3_content"><!-- style="height: 400px;"-->
			<br/>
			<p>
				<li class="filter handle expanded landingpage" id="facet_skills">
					<a class="handle" onClick="toggle_display('skills')"><span class="label">Browse by Skills / Career:</span></a>
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
		</div>
		<img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
	</div>
</div>


<div class="cleardiv"></div>


<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="num_types_in_facet">15</div>
	<div id="page_type">landingpage</div>

	<div id="keyword_search">position_type:"Virtual Volunteering (from home)"</div>
	<div id="fq_search">position_type:"Virtual Volunteering (from home)"</div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>


<script type="text/javascript" src="/template_include/js/display_toggle_landingpage.js"></script>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
<% } %>
