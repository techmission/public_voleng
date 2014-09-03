<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header_socialize.inc" %>
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
ArrayList aServiceList = new  ArrayList ( 2 );
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
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


%>


<!-- BEGIN MAINCONTENTs -->
<div id="loginDiv"></div>  
    <script type="text/javascript">  
         gigya.services.socialize.showLoginUI(conf, {   
            headerText: "Please login using one of the following providers:",  
           width: 280, redirectURL: '/gigya_login.jsp', 
            containerID: "loginDiv" });  
     </script>
<div id="login-links"><a href="#" id="facebook-login">Login with Facebook</a></div>		   

<div id="container_welcomebox">
<div id="welcomebox" class="bottom_right">
<div id="welcomebox_photo">
<img src="<%=aszPortal%>/imgs/CVChristmas1.jpg" alt="ChristianVolunteering.org matches volunteers to volunteer opportunities similar to how job sites like Monster.com work, but for volunteering." width="200" />
</div>

<%@ include file="/template_include/home_jointsearch-minified.inc" %>
<!-- anya temp search -->
</div>
</div><!--end of container_welcomebox-->


<div id="container_about">
<div id="about_us" class="c">
		<h3 class="title">About ChristianVolunteering.org</h3> 
        
        <br />ChristianVolunteering.org is a free directory with
		<ul>
		<li>5,680 volunteer opportunities 
		<li>9,945 organizations
		<li>Providing 10,343 volunteer matches since 2008
		</ul>
       <p> ChristianVolunteering.org and UrbanMinistry.org are programs of TechMission, Inc. Our national partners represent over <span class="numbers">20,000 local ministries</span> that serve over <span class="numbers">30 million under-resourced individuals </span>annually.
		</p></div><!--end about_us.c-->
</div><!--end: div#container-->



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
                <a href="http://www.urbanministry.org/tm/shastas-story-working-urban-youth-not-only-my-calling-my-passion-well"><h4 class="title">Shasta's Story</h4></a>
                <p>Shasta is spending one year serving as a TechMission Corps intern at Breakthrough Urban Ministries in Chicago. Her experience has helped her to decide "working with urban youth is not only my calling but my passion as well."

              
            </div><!-- end main_text -->
             <a href="http://www.urbanministry.org/tm/shastas-story-working-urban-youth-not-only-my-calling-my-passion-well"class="more">more...</a></p>
        </div><!-- end: story1_s-->
        
        
<div style="display: none;" id="opp2_s">
            <div id="img_opp2" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="http://www.urbanministry.org/tale-two-volunteers-willy-and-sharons-story">
                <h4 class="title">Willy and Sharon's Story</h4></a>
                <p>A couple from Singapore connected with a community center in Uganda through a website based in America — ready to change the lives of hundreds of people in Africa.
            </div><!-- end: main_text -->
            <a href="http://www.urbanministry.org/tale-two-volunteers-willy-and-sharons-story"class="more">more...</a></p>
        </div><!--end: story2_s -->
        
        
        <div style="display: none;" id="opp3_s">
            <div id="img_opp3" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story">
                <h4 class="title">Christina's Story</h4></a>
                <p>Christina Anderson was unemployed after 2008 layoffs at a prominent national bank. As an HR professional, Christina knew what to do during her season of job searching and uncertainty; she volunteered.
            </div><!-- end main_text -->
            <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story"class="more">more...</a></p>
        </div><!-- end: story3_s-->
        
        
        <div style="display: none;" id="opp4_s">
                <div id="img_opp4" class="round_bottom_right"></div>
            <div class="main_text">
                 <a href="http://www.urbanministry.org/node/122296">
                 <h4 class="title">Alicia's Story</h4></a>
                <p>After connecting to the Family and Children Faith Coalition online using ChristianVolunteering.org, Alicia decided to take a break from the internet and meet physical needs of children in Miami, FL.
            </div><!-- end of main_text -->
            <a href="http://www.urbanministry.org/node/122296"class="more">more...</a></p>
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
        
        <div style="" id="org1_s">
            <div id="img_org1" class="round_bottom_right"></div>
            <div class="main_text">
               <a href="http://www.urbanministry.org/christianvolunteeringorg-right-all-kinds-ministries">
               <h4 class="title">Info-Nepal</h4></a>
                <p>Info-Nepal has placed 6 volunteers at orphanages through ChristianVolunteering.org. Volunteers help by sharing their knowledge and skills, teach English to children, and share God&prime;s words with the villagers.
                
            </div><!-- end volunteerinfo -->
            <a href="http://www.urbanministry.org/christianvolunteeringorg-right-all-kinds-ministries"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org2_s">
            <div id="img_org2" class="round_bottom_right"></div>
            <div class="main_text">
                <h4 class="title">Regina Rescue Mission</h4>
                <p>"We were blessed by young Bible College graduates with a heart for missions, who commit to spending one year to exercise their gifts and talents in the inner city. [through City Vision College]."
               
            </div><!-- end volunteerinfo -->
             <a href="<%=aszPortal%>"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org3_s">
            <div id="img_org3" class="round_bottom_right"></div>
            <div class="main_text">
                <h4 class="title">Emmanuel Gospel Center & Black Ministerial Alliance</h4>
                <p>In the past 4 years, ChristianVolunteering.org provided 27 full-time AmeriCorps members to BMA's after school sites and 16.5 full-time members to EGC.
                
            </div><!-- end volunteerinfo -->
            <a href="<%=aszPortal%>"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="org4_s">
            <div id="img_org4" class="round_bottom_right"></div>
            <div class="main_text">
            	<a href="http://www.urbanministry.org/christianvolunteeringorg-right-all-kinds-ministries">
                <h4 class="title">NeedHim Ministries</h4></a>
                <p>Virtual volunteering organizations also benefit from ChristianVolunteering.org. NeedHim Ministries has hundreds of volunteers around the country telling people about eternal life through Jesus.
                
                </div><!-- end of main_text -->
                <a href="http://www.urbanministry.org/christianvolunteeringorg-right-all-kinds-ministries"class="more">more...</a></p>
        </div><!--end #org4_s--> 
        <div class="specialmessage">
  <a href="<%=aszPortal%>/recruitvolunteers.jsp"><h1 class="signupnow">Recruit Volunteers</h1></a></div> <!--end of div#specialmessage-->
         
  </div><!-- end : .org_stories_box .c  -->
</div> <!--end: div.container_stories_box-->  
    <!-- ==========================   CHURCH STORIES    ==============================================================-->
<div class="container_church_stories_box"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="http://www.churchvolunteering.org/">Churches</a></h3>
        <ul id="church_numbers">
         <li id="church1" class="tab active_tab">1</li>
         <li id="church2" class="tab">2</li>
         <li id="church3" class="tab">3</li>
         <li id="church4" class="tab">4</li>
        </ul>
    </div><!-- end stories_header -->
        
        <div style="" id="church1_s">
            <div id="img_church1" class="round_bottom_right"></div>
            <div class="main_text">
                <a href="<%=aszPortal%>/advancedsearch.jsp"><h4 class="title">Find Church Group Volunteer Activities</h4></a>
           
                <p>Want to plan a Day of Service for your church or find service outreach activities for church groups?
                
            </div><!-- end volunteerinfo -->
            <a href="<%=aszPortal%>/advancedsearch.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="church2_s">
            <div id="img_church2" class="round_bottom_right"></div>
            <div class="main_text">
                <a href="http://www.churchvolunteering.org/">
                <h4 class="title">Plan a Service Outreach Campaign</h4></a>
                <p>Search our directory of thousands of sermon outlines, Bible studies and small group discussion materials and our church volunteer toolkit.
                
            </div><!-- end volunteerinfo -->
            <a href="http://www.churchvolunteering.org/"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="church3_s">
            <div id="img_church3" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="<%=aszPortal%>/recruitvolunteers.jsp">
                <h4 class="title">Create a Volunteer Directory</h4></a>
                <p>Cut and paste our volunteer opportunities into a printed directory for your church service day. Add service opportunities on your church website.
                
            </div><!-- end volunteerinfo -->
            <a href="<%=aszPortal%>/recruitvolunteers.jsp"class="more">more...</a></p>
        </div>
        
        
        <div style="display: none;" id="church4_s">
            <div id="img_church4" class="round_bottom_right"></div>
            <div class="main_text">
            <a href="http://www.bostonvineyard.org/getinvolved/neighborhoodreach/volunteer/">
                <h4 class="title">Boston Vineyard</h4></a>
                <p>The Boston Vineyard listed their volunteer opportunities of their church serving the community on ChristianVolunteering.org and is now recruiting volunteers through their website.
                
            </div><!-- end volunteerinfo -->
            <a href="http://www.bostonvineyard.org/getinvolved/neighborhoodreach/volunteer/"class="more">more...</a></p>
        </div>  
        
        <div class="specialmessage">
  <a href="<%=aszPortal%>/recruitvolunteers.jsp"><h1 class="signupnow">Church Resources</h1></a></div> <!--end of div#specialmessage-->      
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

		<div id="sidebar_box">
	 	<h4>Who Uses ChristianVolunteering.org</h4>
        <div id="news">
              	<ul>
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=717&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Rescue Missions: <span class="numbers">596 sites</span></a></li> 
                
              	<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=4&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">CCDA Members: <span class="numbers">319 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1060&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Habitat for Humanity: <span class="numbers">53 sites</span></a></li> 
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1041&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Catholic Charities: <span class="numbers">37 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1075&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">World Vision: <span class="numbers">32 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1057&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Feed the Children: <span class="numbers">28 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=8&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Youth Partners Net: <span class="numbers">26 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1062&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Here's Life Inner City: <span class="numbers">23 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1078&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Young Life: <span class="numbers">20 sites</span></a></li>
                
           	 	<li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1050&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Compassion International: <span class="numbers">18 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1037&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Billy Graham Association: <span class="numbers">18 sites</span></a></li>  
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1045&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Christian Aid: <span class="numbers">18 sites</span></a></li>  
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1059&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Food for the Poor: <span class="numbers">18 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1073&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Volunteers of America: <span class="numbers">15 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=3040&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Evangelicals for Social Action: <span class="numbers">12 sites</span></a></li>  
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1070&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Samaritans Purse: <span class="numbers">11 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1064&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Leadership Foundations: <span class="numbers">10 sites</span></a></li>
                
                <li><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=5&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit">Urban Youth Workers Institute</a></li>
                </ul>
                </div>
  </div>

	
      
		</div><!--end about_us1.c-->
</div><!--end: div#container1-->

<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
