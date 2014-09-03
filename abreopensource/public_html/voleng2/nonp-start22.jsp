<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<% if(	(aszHostID.equalsIgnoreCase( "volengfia" )) ||
		(aszHostID.equalsIgnoreCase( "volengmissionamerica" ))
){ // these templates get screwed up with this new page; include the old start page for vols
%>
	<jsp:include page="/voleng2/nonp-start22_partner.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(	(aszSecondaryHost.equalsIgnoreCase( "volengivol" ))
){ // use faith-free old file
%>
	<jsp:include page="/ivol/nonp-start22.jsp" flush="false">
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
h4{margin-bottom:5px;}
#contentwrapper {background-image:none; background-color:#FFF; width:950px;}
form#initialregistration {//padding-top:10px;}
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

<script>
/*
	DOMnews 1.0 
	homepage: http://www.onlinetools.org/tools/domnews/
	released 11.07.05
*/

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

	/* Initialise scroller when window loads */
	window.onload=function()
	{
		// check for DOM
		if(!document.getElementById || !document.createTextNode){return;}
		initDOMnews();
		// add more functions as needed
	}
	/* stop scroller when window is closed */
	window.onunload=function()
	{
		clearInterval(dn_interval);
	}

/*
	This is the functional bit, do not press any buttons or flick any switches
	without knowing what you are doing!
*/

	var dn_scrollpos=dn_startpos;
	/* Initialise scroller */
	function initDOMnews()
	{
		var n=document.getElementById(dn_newsID);
		if(!n){return;}
		n.className=dn_classAdd;
		dn_interval=setInterval('scrollDOMnews()',dn_speed);
		var newa=document.createElement('a');
		var newp=document.createElement('p');
		newp.setAttribute('id',dn_paraID);
		newa.href='#';
		newa.appendChild(document.createTextNode(dn_stopMessage));
		newa.onclick=stopDOMnews;
		newp.appendChild(newa);
		n.parentNode.insertBefore(newp,n.nextSibling);
		n.onmouseover=function()
		{		
			clearInterval(dn_interval);
		}
		n.onmouseout=function()
		{
			dn_interval=setInterval('scrollDOMnews()',dn_speed);
		}
	}

	function stopDOMnews()
	{
		clearInterval(dn_interval);
		var n=document.getElementById('news');
		n.className='';
		n.parentNode.removeChild(n.nextSibling);
		return false;
	}
	function scrollDOMnews()
	{
		var n=document.getElementById(dn_newsID).getElementsByTagName('ul')[0];
		n.style.top=dn_scrollpos+'px';	
		if(dn_scrollpos==dn_endpos){dn_scrollpos=dn_startpos;}
		dn_scrollpos--;	
	}

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
	  <span id="title">Register to Recruit Volunteers</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Register to Recruit Volunteers</span>
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

<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
<center>
<% } %>

  <a href="<%=aszPortal%>/org.do?method=showCreateOrgStep1"><img src="http://christianvolunteering.org/imgs/Volunteering_scheme-sample.gif" align="left"></a>

<!-- ==========================  SIDEBAR  ==========================================================================-->  
<div id="sidebar_box" class="white">
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

 <!-- =========================  ACCOUNT CREATION BOX  ================================================================-->
<div id="account_box">
      <div id="account_header">
            <h3>Create A Free Account</h3>
    </div>
    <div class="box_content">

<form id="initialRegistration" name="initialRegistration" method="POST" action="<%=aszPortal%>/register.do"?>
<input type="hidden" name="method" value="showCreateAccount1">
<input onfocus="this.value=''" type="text" name="email1addr" size="30" value="e-mail address" styleClass="textinputwhite" />
<br>
<td>
  <p>
    <input type="image" alt="Get Started" src="http://christianvolunteering.org/imgs/getstartedbutton.gif" title="Sign Up For A Free Account" name="imageField"/>
 </td>
<a href="<%=aszPortal%>/individualregistration.jsp"><h1 class="signupnow">Sign Up Now For Free!</h1></a>
      </form>
    </div>
  </div>
    
  <!-- ==========================   VOLUNTEER STORIES    ==============================================================-->
<div id="vol_stories_box">
      <div id="vol_stories_header">
        <h3>Organization Stories</h3>
        <ul id="numbers">
         <li id="story1" class="tab active_tab">1</li>
         <li id="story2" class="tab">2</li>
         <li id="story3" class="tab">3</li>
         <li id="story4" class="tab">4</li>
        </ul>
    </div><!-- end stories_header -->
        
        <div style="" id="story1_s">
            <img src="http://christianvolunteering.org/imgs/sojurn_care.gif" class="imgleft" alt=""/>
            <div class="main_text">
            <a href="http://www.christianvolunteering.org/volunteer/hospice-volunteer-4.jsp">
                <h4>Sojourn Care</h4></a>
                <p>Sojourn Care equips volunteers to visit and love those most in need. The organization's volunteer coordinator offers his thanks: &quot;Thank you very much for providing this service! The referrals I was given through ChristianVolunteering.org have all been of very high quality!&quot;</p>
                <p><strong><a href="http://www.christianvolunteering.org/volunteer/hospice-volunteer-4.jsp">more...</a>
                </strong></p>
            </div><!-- end volunteerinfo -->
        </div>
        
        <div style="display: none;" id="story2_s">
            <img src="http://christianvolunteering.org/imgs/tech_play.gif" class="imgleft" alt=""/>
            <div class="main_text">
            <a href="http://www.christianvolunteering.org/volunteer/summer-enrichment-volunteer.jsp">
                <h4>Christie at Technology Playground</h4></a>
                <p>&quot;Christie was a wonderful college student that volunteered with us and she was 
fantastic,&quot; says the director of Technology Playground. &quot;What surprised me the most was that she gave more of her time than she needed...just because.&quot;
</p>
                <p><strong><a href="http://www.christianvolunteering.org/volunteer/summer-enrichment-volunteer.jsp">more...</a></strong></p>
            </div><!-- end volunteerinfo -->
        </div>
        
        <div style="display: none;" id="story3_s">
            <img src="http://christianvolunteering.org/imgs/soup_kitchen.gif" class="imgleft" alt=""/>
            <div class="main_text">
           <a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp">
                <h4>Denver Rescue Mission</h4></a>
                <p>Denver Rescue Mission posted two opportunities on ChristianVolunteering.org to volunteer in their soup kitchen and to serve as a summer intern.  Since 2007, they have received 115 volunteer matches, many of which went on to volunteer.</p>
                <p><strong><a href="http://www.christianvolunteering.org/org/denver-rescue-mission.jsp">more...</a>
                </strong></p>
            </div><!-- end volunteerinfo -->
        </div>
        
        <div style="display: none;" id="story4_s">
            <img src="http://christianvolunteering.org/imgs/bma_org.gif" class="imgleft" alt=""/>
            <div class="main_text">
             <a href="http://www.christianvolunteering.org/org/emmanuel-gospel-center.jsp">
                <h4>Boston's Black Ministerial Alliance & Emmanuel Gospel Center</h4></a>
                <p>In the past 4 years, ChristianVolunteering.org&prime;s TMC Internship program provided 27 full-time AmeriCorps members to BMA and their after school sites and 16.5 full-time AmeriCorps members to EGC.</p>
                <p><strong><a href="http://www.christianvolunteering.org/org/emmanuel-gospel-center.jsp">more...</a></strong></p>
            </div><!-- end volunteerinfo -->
        </div>        
  </div>
   <div id="partner_box">
       <div id="partner_header">
       <h3>Our Partners</h3>
       </div>
       <center><a href="http://www.techmission.org/cms/tm/partners"><img src="http://christianvolunteering.org/imgs/Parners_Rotating_Block.gif"></a></center>
  </div> 

<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>
<% if(aszNarrowTheme.equalsIgnoreCase("true")){ %>
</center>
<% } %>


<!-- start sidebar information -->
<% //@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

<% } %>
