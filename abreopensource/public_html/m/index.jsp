<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->





<!-- on the new location -->




<!-- Vimeo Universal Embed Code API JS - for pausing the slideshow when the video is played -->
<script type="text/javascript" src="http://a.vimeocdn.com/js/froogaloop2.min.js"></script>

<!-- custom JS for the homepage - TODO: put this in a file -->
<script type="text/javascript">
// Events for hiding/showing stories on homepage.
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
	
	/* Image Reel code below, taken from http://www.sohtanaka.com/web-design/automatic-image-slider-w-css-jquery/ */
	
	// Show the paging and activate its first link
	$(".paging").show();
	$(".paging a:first").addClass("active");
	
	
	var imageWidth = $(".window").width();
	var imageSum = $(".image_reel img").size();
	var imageReelWidth = imageWidth * imageSum;
	
	// Adjust the image reel to its new size
	$(".image_reel").css({'width' : imageReelWidth});

	// Paging  and Slider Function
	rotate = function(){
		var triggerID = $active.attr("rel") - 1; 
		var image_reelPosition = triggerID * imageWidth; 
	
		$(".paging a").removeClass('active');
		$active.addClass('active'); 
	
		//Slider Animation
		$(".image_reel").animate({
			left: -image_reelPosition
		}, 500 );
	
	}; 

	// Rotation  and Timing Event
	rotateSwitch = function(){
		play = setInterval(function(){
			$active = $('.paging a.active').next(); 
			if ( $active.length === 0) { 
				$active = $('.paging a:first'); 
			}
			rotate(); 
		}, 10000); 
	};

	rotateSwitch();
	
	// Stop the rotation when the user hovers over something.
	// Use hoverIntent to ignore very quick hovers. 
	// TODO: Figure out a way to get this to work together with the Vimeo code.
	/* $(".image_reel").hoverIntent(function() {
		clearInterval(play);
	}, function() {
		rotateSwitch(); 
	}); */
	
	// Bind an event listener to the Vimeo player's ready event, 
	// as per http://stackoverflow.com/questions/5999357/jquery-and-vimeo-froogaloop-api
	// Code based on http://labs.funkhausdesign.com/examples/vimeo/froogaloop2-api-basics.html
	$('iframe.vimeo-player').each(function(){
    $f(this).addEvent('ready', ready);
  });
	
	// Bind the event listeners when the player is ready.
	// These handle either stopping the rotation while the video is playing,
	// or restarting it when the video is finished.
	function ready(playerID){
    $f(playerID).addEvent('play', video_playing);
		$f(playerID).addEvent('pause', video_stopped);
		$f(playerID).addEvent('finish', video_stopped);
  }
	
	function video_playing(playerID){
	  //alert('Video playing!');
    //$(".image_reel").hide(); // This worked so it's targeting the right page.
		clearInterval(play);
  }
	
	function video_stopped(playerID){
	  rotateSwitch();
  }

	// Move forward when the paging links are clicked.
	$(".paging a").click(function() {
		$active = $(this); 
	
		clearInterval(play);
		rotate(); 
		rotateSwitch(); 
		return false; 
	});
  
	// TODO: determine what this snippet was supposed to do. It doesn't actually work.
	/* $('.image_reel').click(function(){
	   $(this).cycle('id').cycle('pause');
	}); */


	
});
</script>
<script><!--scrolling news script-->
/* Variables, go nuts changing those! */
	// initial position 
	var dn_startpos=0; 			
	// end position (long list = higher number) 
	var dn_endpos=-410; 			
	// Speed of scroller higher number = slower scroller 
	var dn_speed=70;				
	// ID of the news box
	var dn_newsID='news';			
	// class to add when JS is available
	var dn_classAdd='hasJS';		
	// Message to stop scroller
	var dn_stopMessage='';	
	// ID of the generated paragraph
	var dn_paraID='DOMnewsstopper';
</script>


<script type="text/javascript" src="/template_include/js/scrolling_news.js"></script>



<% 
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38;

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList aLanguageList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );


int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
//aCodes.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


%>


<!-- BEGIN MAINCONTENT -->
<% if(! aszHostID.equalsIgnoreCase("volengmobile") ){ %>
<div class="main_view">
    <div class="window">
        <div class="image_reel">
   <a href="#"><img src="http://www.christianvolunteering.org/imgs/rotimg_1small.png" alt="Search over 10,000 Christian missions trips and volunteer opportunities: ChristianVolunteering.org. Find opportunities in orphanages, medical missions, urban ministry, Christian internships, and church volunteering." /></a>
   <a href="#"><img src="http://www.christianvolunteering.org/imgs/about-cv-org.png" alt="Search over 10,000 Christian missions trips and volunteer opportunities: ChristianVolunteering.org. Find opportunities in orphanages, medical missions, urban ministry, Christian internships, and church volunteering." /></a>
   <a href="#"><iframe class="vimeo-player" id="player1" src="http://player.vimeo.com/video/27592525?title=0&byline=0&portrait=0&api=1&player_id=player1" width="455" height="230" frameborder="0"></iframe><img style="display:none;" src=""/></a>
  <a class="new-slide" href="http://www.cityvisioninternships.org"><img src="http://www.christianvolunteering.org/imgs/UM_Internship_Photo_v3.png" alt="City Vision Internships: One year internships in Christian ministries with college tuition or stipend" /></a>
        </div>
    </div>
    <div class="paging">
        <a href="#" rel="1">1</a>
        <a href="#" rel="2">2</a>
        <a href="#" rel="3">3</a>
        <a href="#" rel="4">4</a>
    </div>
</div><!--end: div.main_view-->
<% } %>

<div id="container_welcomebox">
<div id="welcomebox" class="bottom_right">


<!-- 3-tab search: Volopps, Orgs, STM -->
<%
        if( 	aszHostID.equalsIgnoreCase( "voleng1" )     ||
                aszHostID.equalsIgnoreCase( "default" )     ||
                aszHostID.equalsIgnoreCase( "volengcatholic" )     ||
                aszHostID.equalsIgnoreCase( "volengmobile" )     ||
                aszHostID.equalsIgnoreCase( "default" )     ||
                aszHostID.equalsIgnoreCase( "default" )     ||
                aszHostID.equalsIgnoreCase( "default" )
        ){
//(index_temp.equals("solr")){
%>
<%@ include file="/template_include/home_solr_search.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_jointsearch-minified.inc" %>
<% } %>
</div>
</div><!--end of container_welcomebox-->

<div style="clear:both;"></div>

<% if(! aszHostID.equalsIgnoreCase("volengmobile") ){ %>

<!-- ==========================   VOLUNTEER STORIES    ==============================================================-->
 <div class="container_stories_box">
 
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
            <div class="main_text">
              <a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story">
                <h4 class="title">Kevin's Story</h4></a>
                <p>Kevin Maples never expected to help organizations rescue children from sexual abuse, provide jobs for women, or eradicate poverty when he searched ChristianVolunteering.org for a volunteer opportunity. 
                </div><!-- end: main_text -->
                <a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story"class="more">more...</a></p>  
        </div><!-- end: story1_s-->
        
        
<div style="display: none;" id="opp2_s">
            <div id="img_opp2" class="round_bottom_right"></div>
            <div class="main_text">
                 <a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story"><h4 class="title">Shannon's Story</h4></a>
                <p>Working with children was a passion of Shannon's for years. A previous "Big Sister", Shannon searched for volunteer opportunities in her new city of Pittsburgh when she moved there in 2008.
                </div><!-- end main_text -->
             <a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story"class="more">more...</a></p>   
        </div><!--end: story2_s -->
        
        
        <div style="display: none;" id="opp3_s">
            <div id="img_opp3" class="round_bottom_right"></div>
            <div class="main_text">
          <a href="http://www.urbanministry.org/kerries-story"><h4 class="title">Kerrie's Story</h4></a>
                <p>&rdquo;Your website was a great help, I will be graduating college next year and wanted some opportunities to put on my resume and give me some much needed experience.&ldquo;
                </div><!-- end main_text -->
             <a href="http://www.urbanministry.org/kerries-story"class="more">more...</a></p>
        </div><!-- end: story3_s-->
        
        
        <div style="display: none;" id="opp4_s">
                <div id="img_opp4" class="round_bottom_right"></div>
            <div class="main_text">
                     <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story">
                <h4 class="title">Christina's Story</h4></a>
                <p>Christina Anderson was unemployed after 2008 layoffs at a prominent national bank. As an HR professional, Christina knew what to do during her season of job searching and uncertainty; she volunteered.
            </div><!-- end main_text -->
            <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story"class="more">more...</a></p>
        </div><!-- end of opp4_s -->
  <div class="specialmessage">
  <a href="<%=aszPortal%>/login.jsp"><h1 class="signupnow">Sign Up Now For Free!</h1></a></div> <!--end of div#specialmessage-->
     
        </div><!--end: div#stories_box.c-->
  
</div> <!--end: div.container_stories_box-->


  <!-- ==========================   ORGANIZATION STORIES    ==============================================================-->
 <div class="container_org_stories_box">
 
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
        
        
        <div style="display: none;" id="org3_s">
            <div id="img_org1" class="round_bottom_right"></div>
            <div class="main_text">
               <a href="http://www.christianvolunteering.org/org/sojourn-care.jsp">
               <h4 class="title">Sojourn Care</h4></a>
                <p>Sojourn Care equips volunteers to visit and love those most in need. The organization&prime;s volunteer coordinator offers his thanks: &quot;Thank you very much for providing this service!&quot;
            </div><!-- end volunteerinfo -->
            <a href="http://www.christianvolunteering.org/org/sojourn-care.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org4_s">
            <div id="img_org2" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="http://www.christianvolunteering.org/">
                <h4 class="title">Technology Playground</h4></a>
                <p>&quot;Christie was a college student that volunteered and she was 
fantastic,&quot; says the director of Technology Playground. &quot;What surprised me most was that she gave more of her time than she needed...just because.&quot;
            </div><!-- end volunteerinfo -->
             <a href="http://www.christianvolunteering.org/"class="more">more...</a></p>
        </div>
        
        
        <div style="" id="org1_s">
            <div id="img_org3" class="round_bottom_right"></div>
            <div class="main_text">
             <a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp">
               <h4 class="title">Denver Rescue Mission</h4></a>
                <p>Denver Rescue Mission posted two opportunities on ChristianVolunteering.org to volunteer in their soup kitchen and to serve as a summer intern.  Since 2007, they have received 115 volunteer matches.
            </div><!-- end volunteerinfo -->
            <a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org2_s">
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
    <!-- ==========================   CHURCH STORIES    ==============================================================-->
<div class="container_church_stories_box"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="http://www.cityvisioninternships.org/">City Vision Internships</a></h3>
    </div><!-- end stories_header -->
        
        <div style="" id="church1_s">
            <div id="img_church1" class="round_bottom_right"></div>
            <div class="main_text">
                <ul><li>One year and summer internships</li>
                <li>Free room, board, and tuition in City Vision College</li>
                <li>Small stipend for applicants with a Bachelor's degree</li></ul>
            </div><!-- end volunteerinfo -->
        </div>
        
       <br/> 
        <div class="specialmessage">
  <a href="http://www.cityvisioninternships.org/"><h1 class="signupnow">FIND OUT MORE</h1></a></div> <!--end of div#specialmessage-->      
  </div>
</div> <!--end: div.container_stories_box-->     

<!--========================================================ENDING BOX==========================================================================-->
<div class="container_endingbox">
<div id="endingbox" class="only_top_right">

	<h1>Christian Volunteering Resources</h1>
	<%@ include file="/jsprsscron/volunteering_view.htm" %>
<!--<div class="cleardiv"></div>-->
</div>


  
<div class="cleardiv"></div>
</div><!-- end of container_endingbox -->

<div id="container_partners">
<div id="partners" class="c">

 <h3 class="title"> ChristianVolunteering Partners </h3>
        <br/>
        <center>
        <img src="/imgs/Parners_Rotating_Block.gif"/>
        </center>

		<div id="sidebar_box" class="white home">
	 	<h4>Who Uses ChristianVolunteering.org</h4>
        <div id="news">
              	<ul>
                <li>1,500+ churches from 50+ denominations</li> 
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&duration=&mingroup=&maxgroup=&servicearea1=&servicearea2=&servicearea3=&skills1id=&skills2id=&skills3id=&postalcode=&country=us&city=&state=&programtypetid=&regiontid=&distance=City&frequency=&orgname=salvation+army&denomaffiltid=&orgaffil1tid=&orgaffil2tid=&orgaffil3tid=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Salvation Army: <span class="numbers">353 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=717&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Rescue Missions: <span class="numbers">248 sites</span></a></li> 
                
              	<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=4&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">CCDA Members: <span class="numbers">245 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1060&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Habitat for Humanity: <span class="numbers">73 sites</span></a></li> 
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1041&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Catholic Charities: <span class="numbers">53 sites</span></a></li>
                                                           
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1078&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Young Life: <span class="numbers">46 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1057&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Feed the Children: <span class="numbers">45 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1075&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">World Vision: <span class="numbers">43 sites</span></a></li>
                                    
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1045&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Christian Aid: <span class="numbers">39 sites</span></a></li> 
                            
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1037&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Billy Graham Association: <span class="numbers">36 sites</span></a></li>
                                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1059&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Food for the Poor: <span class="numbers">31 sites</span></a></li>
                                    
           	 	<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1050&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Compassion International: <span class="numbers">31 sites</span></a></li>
                    
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1062&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Here's Life Inner City: <span class="numbers">27 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=8&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Youth Partners Net: <span class="numbers">26 sites</span></a></li>   
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1073&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Volunteers of America: <span class="numbers">25 sites</span></a></li>
                                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1070&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Samaritans Purse: <span class="numbers">19 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1064&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Leadership Foundations: <span class="numbers">13 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=3040&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Evangelicals for Social Action: <span class="numbers">12 sites</span></a></li>  
                </ul>
                </div>
  </div>

	
      
		</div><!--end about_us1.c-->
</div><!--end: div#container1-->
<% } %>

<!-- start footer information -->
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->


