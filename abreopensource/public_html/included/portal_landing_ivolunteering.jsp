<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<script type="text/javascript">
function hideStoriesOpp() {
	$('#opp1_s').hide(); $('#opp1').removeClass('active_tab');
	$('#opp2_s').hide(); $('#opp2').removeClass('active_tab');
	$('#opp3_s').hide(); $('#opp3').removeClass('active_tab');
	$('#opp4_s').hide(); $('#opp4').removeClass('active_tab');
}

function hideStoriesOrg() {
	$('#org1_s').hide(); $('#org1').removeClass('active_tab');
	$('#org2_s').hide(); $('#org2').removeClass('active_tab');
	$('#org3_s').hide(); $('#org3').removeClass('active_tab');
	$('#org4_s').hide(); $('#org4').removeClass('active_tab');
}

function hideStoriesChurch() {
	$('#church1_s').hide(); $('#church1').removeClass('active_tab');
	$('#church2_s').hide(); $('#church2').removeClass('active_tab');
	$('#church3_s').hide(); $('#church3').removeClass('active_tab');
	$('#church4_s').hide(); $('#church4').removeClass('active_tab');
}

$(document).ready(function() {
	// hide/show volunteer stories based on clicked tab
	$('#opp1').click(function() { hideStoriesOpp(); $('#opp1').addClass('active_tab'); $('#opp1_s').show(); });
	$('#opp2').click(function() { hideStoriesOpp(); $('#opp2').addClass('active_tab'); $('#opp2_s').show(); });
	$('#opp3').click(function() { hideStoriesOpp(); $('#opp3').addClass('active_tab'); $('#opp3_s').show(); });
	$('#opp4').click(function() { hideStoriesOpp(); $('#opp4').addClass('active_tab'); $('#opp4_s').show(); });
	// hide/show organization stories based on clicked tab
	$('#org1').click(function() { hideStoriesOrg(); $('#org1').addClass('active_tab'); $('#org1_s').show(); });
	$('#org2').click(function() { hideStoriesOrg(); $('#org2').addClass('active_tab'); $('#org2_s').show(); });
	$('#org3').click(function() { hideStoriesOrg(); $('#org3').addClass('active_tab'); $('#org3_s').show(); });
	$('#org4').click(function() { hideStoriesOrg(); $('#org4').addClass('active_tab'); $('#org4_s').show(); });
	// hide/show organization stories based on clicked tab
	$('#church1').click(function() { hideStoriesChurch(); $('#church1').addClass('active_tab'); $('#church1_s').show(); });
	$('#church2').click(function() { hideStoriesChurch(); $('#church2').addClass('active_tab'); $('#church2_s').show(); });
	$('#church3').click(function() { hideStoriesChurch(); $('#church3').addClass('active_tab'); $('#church3_s').show(); });
	$('#church4').click(function() { hideStoriesChurch(); $('#church4').addClass('active_tab'); $('#church4_s').show(); });
	
	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
	
	$(".round_bottom_right").corner("round 50px br"); /*this is a Rounded Bottom Right corner*/
	
	$(".round_bottom").corner("bottom"); /*this is a Rounded Bottom corner*/
	$(".only_top_right").corner("round tr").parent().css('padding', '4px').corner("round tr 10px"); /*this is a Rounded Top Right Only corner*/
	$(".bottom_right").corner("round br").parent().css('padding', '4px').corner("round br 10px"); /*this is a Rounded Top Right Only corner*/
	
	$(".n").corner("notch 14px").parent().css('padding', '4px').corner("notch 4px"); /* this is notched corners*/
	
});
</script>

<!-- BEGIN MAINCONTENT -->
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="num_types_in_facet">5</div>
	<div id="page_type">landingpage</div>

	<div id="keyword_search">position_type:"Short-term Missions / Volunteer Internship"</div>
	<div id="fq_search">position_type:"Short-term Missions / Volunteer Internship"</div>
	<div id="fq_search_2">content_type:opportunity</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
</div>


<div id="result" class="midbox_section">

<div id="container_welcomebox" class="landingpage">
	<div id="welcomebox" class="bottom_right landingpage">
		<div id="welcomebox_photo">
			<a href="http://apps.facebook.com/worldchanger"><img src="<%=aszPortal%>/imgs/homepage_img.gif" alt="iVolunteering.org matches volunteers to volunteer opportunities similar to how job sites like Monster.com work, but for volunteering." width="200" /></a>
		</div>
		<div style="float:left; margin: 10px 0px 5px 10px; width: 300px; z-index: -1;">
			<%@ include file="/template_include/home_solr_search.inc" %>
		</div>
	</div>
	<div class="cleardiv"></div>
</div>
<!-- end welcomebox -->


	<div id="container_about" class="landingpage">
		<div id="about_us" class="c">
			<h3 class="title">About ivolunteering.org</h3> 
			<br />ivolunteering.org is a free directory with
			<ul>
				<li>5,778 volunteer opportunities 
				<li>11,250 organizations
				<li>Providing 26,842 volunteer matches since 2008
			</ul>
			<p>
				ivolunteering.org and UrbanMinistry.org are programs of TechMission, Inc. Our national partners represent over <span class="numbers">20,000 local ministries</span> that serve over <span class="numbers">30 million under-resourced individuals </span>annually.
			</p>
		</div>
	</div>
	<!--end container_about-->

<!-- ==========================   VOLUNTEER STORIES    ==============================================================-->
<div class="container_stories_box"  style="margin-bottom:15px;">
	<div id="stories_box" class="c">
    	<div id="stories_header">
			<h3 class="title"> <a href="<%=aszPortal%>/volunteergettingstarted.jsp">Volunteers</a></h3>
			<ul id="numbers">
				<li id="opp1" class="tab active_tab">1</li>
				<li id="opp2" class="tab">2</li>
				<li id="opp3" class="tab">3</li>
				<li id="opp4" class="tab">4</li>
			</ul>
		</div><!-- end stories_header -->


        <div style="" id="opp1_s">
			<div id="img_opp1" class="round_bottom_right"></div>
			<div class="main_text"><a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story"><h4 class="title">Kevin's Story</h4></a>
				<p>Kevin Maples never expected to help organizations rescue children from sexual abuse, provide jobs for women, or eradicate poverty when he searched ChristianVolunteering.org for a volunteer opportunity.</p>
			</div><!-- end: main_text -->
			<a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story"class="more">more...</a>
		</div><!-- end: story1_s-->
        
		<div style="display: none;" id="opp2_s">
            <div id="img_opp2" class="round_bottom_right"></div>
            <div class="main_text">
				<a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story"><h4 class="title">Shannon's Story</h4></a>
                <p>Working with children was a passion of Shannon's for years. A previous "Big Sister", Shannon searched for volunteer opportunities in her new city of Pittsburgh when she moved there in 2008.</p>
            </div><!-- end main_text -->
            <a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story"class="more">more...</a>   
        </div><!--end: story2_s -->
        
        <div style="display: none;" id="opp3_s">
            <div id="img_opp3" class="round_bottom_right"></div>
            <div class="main_text">
				<a href="http://www.urbanministry.org/kerries-story"><h4 class="title">Kerrie's Story</h4></a>
                <p>&rdquo;Your website was a great help, I will be graduating college next year and wanted some opportunities to put on my resume and give me some much needed experience.&ldquo;</p>
			</div><!-- end main_text -->
			<a href="http://www.urbanministry.org/kerries-story"class="more">more...</a>
        </div><!-- end: story3_s-->

        <div style="display: none;" id="opp4_s">
			<div id="img_opp4" class="round_bottom_right"></div>
            <div class="main_text">
				<a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story">
                <h4 class="title">Christina's Story</h4></a>
                <p>Christina Anderson was unemployed after 2008 layoffs at a prominent national bank. As an HR professional, Christina knew what to do during her season of job searching and uncertainty; she volunteered.</p>
            </div><!-- end main_text -->
            <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story"class="more">more...</a>
        </div><!-- end of opp4_s -->
		<div class="specialmessage">
			<a href="<%=aszPortal%>/login.jsp"><h1 class="signupnow">Sign Up Now For Free!</h1></a>
		</div> <!--end of div#specialmessage-->
	</div><!--end: div#stories_box.c-->
</div> <!--end: div.container_stories_box-->


  <!-- ==========================   ORGANIZATION STORIES    ==============================================================-->
 <div class="container_org_stories_box" style="margin-bottom:10px;">
 
<div id="org_stories_box" class="c">
      <div id="org_stories_header">
        <h3 class="title"><a href="/recruitvolunteers.jsp">Organizations</a></h3>
        <ul id="org_numbers">
         <li id="org1" class="tab active_tab">1</li>
         <li id="org2" class="tab">2</li>
         <li id="org3" class="tab">3</li>
         <li id="org4" class="tab">4</li>
        </ul>
    </div><!-- end stories_header -->
        
        
        <div style="" id="org1_s">
            <div id="img_org1" class="round_bottom_right"></div>
            <div class="main_text">
               <a href="http://www.christianvolunteering.org/volunteer/hospice-volunteer-4.jsp">
               <h4 class="title">Sojourn Care</h4></a>
                <p>Sojourn Care equips volunteers to visit and love those most in need. The organization&prime;s volunteer coordinator offers his thanks: &quot;Thank you very much for providing this service!&quot;
            </div><!-- end volunteerinfo -->
            <a href="http://www.christianvolunteering.org/volunteer/hospice-volunteer-4.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org2_s">
            <div id="img_org2" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="http://www.christianvolunteering.org/volunteer/summer-enrichment-volunteer.jsp">
                <h4 class="title">Technology Playground</h4></a>
                <p>&quot;Christie was a college student that volunteered and she was 
fantastic,&quot; says the director of Technology Playground. &quot;What surprised me most was that she gave more of her time than she needed...just because.&quot;
            </div><!-- end volunteerinfo -->
             <a href="http://www.christianvolunteering.org/volunteer/summer-enrichment-volunteer.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org3_s">
            <div id="img_org3" class="round_bottom_right"></div>
            <div class="main_text">
             <a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp">
               <h4 class="title">Denver Rescue Mission</h4></a>
                <p>Denver Rescue Mission posted two opportunities on ChristianVolunteering.org to volunteer in their soup kitchen and to serve as a summer intern.  Since 2007, they have received 115 volunteer matches.
            </div><!-- end volunteerinfo -->
            <a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org4_s">
            <div id="img_org4" class="round_bottom_right"></div>
            <div class="main_text">
            	<a href="http://www.christianvolunteering.org/org/emmanuel-gospel-center.jsp">
                <h4 class="title">Emmanuel Gospel Center & Black Ministerial Alliance</h4></a>
                <p>In the past 4 years, ChristianVolunteering.org provided 27 full-time AmeriCorps members to BMA's after school sites and 16.5 full-time members to EGC.
            </div><!-- end volunteerinfo -->
            <a href="http://www.christianvolunteering.org/org/emmanuel-gospel-center.jsp"class="more">more...</a></p>
        </div><!--end #org4_s--> 
        
        <div class="specialmessage">
  <a href="<%=aszPortal%>/recruitvolunteers.jsp"><h1 class="signupnow">Recruit Volunteers</h1></a></div> <!--end of div#specialmessage-->
         
  </div><!-- end : .org_stories_box .c  -->
</div> <!--end: div.container_stories_box-->  

    <!-- ==========================   TRAINING BOX    ==============================================================-->
<div class="container_church_stories_box"  style="margin-bottom:10px;"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="<%=aszPortal%>/training.jsp">Training</a></h3>
       <!-- <ul id="church_numbers">
         <li id="church1" class="tab active_tab">1</li>
         <li id="church2" class="tab">2</li>
         <li id="church3" class="tab">3</li>
         <li id="church4" class="tab">4</li> 
        </ul> -->
    </div><!-- end stories_header -->
        
        <div id="church1_s">
            <div id="img_church1" class="round_bottom_right"></div>
            <div class="main_text">           
           
               <p><a href="<%=request.getContextPath()%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles</a>, <a href="<%=request.getContextPath()%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
		Links</a>, <a href="<%=request.getContextPath()%>/register.do?method=showBookRecommendations" title="Book Recommendations">Books</a>,  
		<a href="<%=request.getContextPath()%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a></p>	 	
		<p>		<a href="http://www.christianvolunteering.org/files/Volunteer_Management_TMC_Training.ppt">Non-Profit Volunteer Management Training</a>, 
		<a href="http://www.christianvolunteering.org/files/vol_orientation_general_customizable.ppt" target="_new" title="Customizable Volunteer Orientation for Urban Non-Profits and volunteer managers">
		Urban Non-Profit Volunteer Orientation: Customizable</a>, 							
		<a href="http://www.christianvolunteering.org/files/vol_orientation_general_audience.ppt" title="Urban Service and Volunteer Volunteer Orientation Powerpoint">
		Volunteer Orientation: How to Find a Volunteer Placement and Serve Well</a><br><br> 
</p>					
                
            </div><!-- end volunteerinfo -->
            
             <a href="<%=aszPortal%>/training.jsp" class="more">more...</a></p>
        </div><!--end #org4_s--> 
        
        <div class="specialmessage">
  <a href="<%=aszPortal%>/training.jsp"><h1 class="signupnow">Training Resources</h1></a></div> <!--end of div#specialmessage-->
            
				
        
        
          
  </div>
</div> <!--end: div.container_stories_box-->     
</div>


  
<div class="cleardiv"></div>
<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
