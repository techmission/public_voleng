<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<% if(	(aszHostID.equalsIgnoreCase( "volengfia" )) ||
		(aszHostID.equalsIgnoreCase( "volengmissionamerica" ))
){ // these templates get screwed up with this new page; include the old start page for vols
%>
	<jsp:include page="/voleng2/vol-start11.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(	(aszSecondaryHost.equalsIgnoreCase( "volengivol" ))
){ // use faith-free old file
%>
	<jsp:include page="/ivol/vol-start11.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else { %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style type="text/css">
* {//margin:0; //padding:0;}
h4{margin-bottom:5px;}
#contentwrapper{background-image:none; background-color:#FFF; width:950px;}
form#initialregistration {//padding-top:25px;}
#sidebar_box a {text-decoration:none;}
</style>

<script type="text/javascript">
function hideStories() {
	$('#story1_s').hide(); $('#story1').removeClass('active_tab');
	$('#story2_s').hide(); $('#story2').removeClass('active_tab');
	$('#story3_s').hide(); $('#story3').removeClass('active_tab');
	$('#story4_s').hide(); $('#story4').removeClass('active_tab');
}

$(document).ready(function() {
	// hide/show stories based on clicked tab
	$('#story1').click(function() { hideStories(); $('#story1').addClass('active_tab'); $('#story1_s').show(); });
	$('#story2').click(function() { hideStories(); $('#story2').addClass('active_tab'); $('#story2_s').show(); });
	$('#story3').click(function() { hideStories(); $('#story3').addClass('active_tab'); $('#story3_s').show(); });
	$('#story4').click(function() { hideStories(); $('#story4').addClass('active_tab'); $('#story4_s').show(); });
});
</script>

</head>

<% 
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getAppCodeList( aServiceList, 161 );
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Getting Started: How It Works</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Getting Started: How It Works</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="sidebarless">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>




  <a href="<%=aszPortal%>/advancedsearch.jsp"><img src="http://christianvolunteering.org/imgs/Volunteering_scheme-1.gif" align="left"></a>


<!-- ==========================  SIDEBAR  ==========================================================================-->  
<div id="sidebar_box" class="background">
          <h4>Where People Are Volunteering</h4>
          	<div class="main_text2">
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=717&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">2,454</span> at Rescue Missions sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&duration=&mingroup=&maxgroup=&servicearea1=&servicearea2=&servicearea3=&skills1id=&skills2id=&skills3id=&postalcode=&country=us&city=&state=&programtypetid=&regiontid=&distance=City&frequency=&orgname=salvation+army&denomaffiltid=&orgaffil1tid=&orgaffil2tid=&orgaffil3tid=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">993</span> at Salvation Army sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=4&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">795</span> at CCDA sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1041&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">558</span> at Catholic Charities sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1062&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">271</span> at Here's Life Inner City sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1060&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">280</span> at Habitat for Humanity sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1075&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">136</span> at World Vision sites</a><br />
            
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&servicearea1=&servicearea2=&servicearea3=&programtypetid=&regiontid=&country=&postalcode=&distance=City&postype=&duration=&mingroup=&maxgroup=&city=&state=&orgname=&skills1id=&skills2id=&skills3id=&denomaffiltid=&orgaffil1tid=1050&orgaffil2tid=&orgaffil3tid=&orgaffil4tid=&orgaffil5tid=&localaffil=&searchkey1=&searchkey2=&searchkey3=&Submit=Submit"><span class="numbers">68</span> at Compassion International sites</a>
            </div>
            
            <h4>Volunteer Opportunity Locations</h4>
             
             <div class="main_text2">
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=us&imageField=Search"><span class="numbers">4,567</span> in United States</a><br />
         <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=ke&imageField=Search"><span class="numbers">158</span> in Kenya</a><br />
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=ug&imageField=Search"><span class="numbers">129</span> in Uganda</a><br />
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=gh&imageField=Search"><span class="numbers">89</span> in Ghana</a><br />
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=in&imageField=Search"><span class="numbers">63</span> in India</a><br />
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=uk&imageField=Search"><span class="numbers">72</span> in United Kingdom</a><br />
             <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=ca&imageField=Search"><span class="numbers">53</span> in Canada</a><br />
            <a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&distance=City&voltype=&requesttype=urlalias&postalcode=&servicearea1=&skills1id=&country=ec&imageField=Search"><span class="numbers">50</span> in Ecuador</a><br />
						<a href="http://www.christianvolunteering.org/advancedsearch.jsp"><span class="numbers">597</span> in all other countries</a>
</div>
    
  </div>
    
    <!-- ========================== ACCOUNT CREATION BOX =================================================================-->
<div id="account_box">
      <div id="account_header">
       <h3>Create A Free Account</h3>
       </div>
<div class="box_content">

<form id="initialRegistration" name="initialRegistration" method="POST" action="<%=aszPortal%>/register.do"?>
<input type="hidden" name="method" value="showCreateAccount1">
<input onfocus="this.value=''" type="text" name="email1addr" size="30" value="e-mail address" styleClass="textinputwhite">
<br>
<td>
  <p>
    <input type="image" alt="Get Started" src="http://christianvolunteering.org/imgs/getstartedbutton.gif" title="Sign Up For A Free Account" name="imageField"/>
 </td>
<a href="<%=aszPortal%>/individualregistration.jsp"><h1 class="signupnow">Sign Up Now For Free!</h1></a>
            </form>
</div><!--end: div.box_content-->
  </div><!--end: div#account_box-->


    
  <!-- ==========================  VOLUNTEER STORIES    ==============================================================-->
<div id="vol_stories_box">
      <div id="vol_stories_header">
        <h3>Volunteer Stories</h3>
        <ul id="numbers">
         <li id="story1" class="tab active_tab">1</li>
         <li id="story2" class="tab">2</li>
         <li id="story3" class="tab">3</li>
         <li id="story4" class="tab">4</li>
        </ul>
    </div><!-- end stories_header -->
        
        <div style="" id="story1_s">
            <img src="http://christianvolunteering.org/imgs/Kevin_Maples.jpg" class="imgleft" alt=""/>
            <div class="main_text">
                <a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story">
                <h4>Not a Doctor or a Lawyer: Kevin's Story</h4></a>
                <p>Kevin Maples never expected to help organizations rescue children from sexual abuse, provide jobs for women, or eradicate poverty when he searched www.ChristianVolunteering.org for a volunteer opportunity.<br/></p>
                <p><strong><a href="http://www.urbanministry.org/i%E2%80%99m-not-doctor-engineer%E2%80%A6kevin%E2%80%99s-story">more...</a>
                </strong></p>
            </div><!-- end main_text -->
        </div><!-- end: story1_s-->
        
<div style="display: none;" id="story2_s">
              <img src="http://christianvolunteering.org/imgs/shannon-pool.gif" class="imgleft" alt=""/>
            <div class="main_text">
                  <a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story">
                <h4>Shannon's Story</h4></a>
                <p>Working with children was a passion of Shannon's for years. A previous "Big Sister", Shannon searched for volunteer opportunities in her new city of Pittsburgh when she moved there in 2008. </p>
                <p><strong><a href="http://www.urbanministry.org/learning-love-kids-not-%E2%80%9Ctolerate%E2%80%9D-them-shannon%E2%80%99s-story">more...</a></strong></p>
            </div><!-- end: main_text -->
        </div><!--end: story2_s -->
        
        <div style="display: none;" id="story3_s">
                <img src="http://christianvolunteering.org/imgs/kerrie.gif" class="imgleft" alt=""/>
            <div class="main_text">
          <a href="http://www.urbanministry.org/kerries-story">
                <h4>Kerrie's Experiences in Volunteerism</h4></a>
                <p>&rdquo;Your website was a great help, I will be graduating college next year and wanted some opportunities to put on my resume and give me some much needed experience.&ldquo;<br/></p>
                <p><strong><a href="http://www.urbanministry.org/kerries-story">more...</a>
                </strong></p>
            </div><!-- end main_text -->
        </div><!-- end: story3_s-->
        
        <div style="display: none;" id="story4_s">
         <img src="http://christianvolunteering.org/imgs/Christina.gif" class="imgleft" alt=""/>
            <div class="main_text">
            <a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story">
                <h4>Christina's Story</h4></a>
                <p>Christina Anderson was unemployed after 2008 layoffs at a prominent national bank. As an HR professional, Christina knew what to do during her season of job searching and uncertainty; she volunteered.</p>
                <p><strong><a href="http://www.urbanministry.org/resume-enhancement-only-beginning-christinas-story">more...</a></strong></p>
            </div><!-- end of main_text -->
        </div>
        
  </div>
 

  <!-- =======================   CHARTS    ==================================================================-->
   
  <a href="<%=aszPortal%>/advancedsearch.jsp"><img src="http://christianvolunteering.org/imgs/words.gif" border="0" style="float:left; padding:0; margin:0;" /></a>
  

<!-- =======================   CHARTS    ==================================================================-->
<div id="partner_box">
       <div id="partner_header">
       <h3>Our Partners</h3>
       </div>
       <center><a href="http://www.techmission.org/cms/tm/partners"><img src="http://christianvolunteering.org/imgs/Parners_Rotating_Block.gif"></a></center>
  </div>
<!-- =======================  END OF STORIES, PLACES, OCCUPATIONS   ====================================================-->

  
</div><!-- End of div#maincontent.sidebarless-->

<!-- start sidebar information -->
<!--@ include file="/template/sidebar.inc"%> -->
<!-- end sidebar information -->
<!-- start footer information -->
<!-- <%/*@ include file="/template/footer.inc"*/ %> --><!-- end footer information -->
<%@ include file="/template/footer.inc"%>

<% } %>
