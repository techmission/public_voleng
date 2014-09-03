<!-- start JSP information -->

<%@ include file="/template_include/topjspnologin1.inc" %>

<!-- end JSP information -->



<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>



<title>Short-Term Missions Search & Christian Urban Internships: ChristianVolunteering.org</title>



<% } else { %>

<% } %>



<% if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>

<% } else { %>



<% /*if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) || 
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" ))||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
        (aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ */%>



<!-- start wide header information -->

<%@ include file="/template/home_header.inc" %>

<!-- end wide header information -->



<% //} else { %>



<!-- start standard header information -->

<%//@ include file="/template/virtual_home_header.inc" - commented out b/c it made the page too big and crashed it %>

<!-- end standard header information -->



<% //} %>



<!-- start navigation information -->

<%@ include file="/template/navigation.inc" %>

<!-- end navigation information -->



<% 
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aServiceList, 161 );
aCodes.getAppCodeList( aProgramList, 172 );
aCodes.getAppCodeList( afiliationList, 163 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getAppCodeList( apartnersList, 167 );
aCodes.getAppCodeList( askillsList, 169 );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );


String ua = request.getHeader( "User-Agent" );

boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );

boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );

response.setHeader( "Vary", "User-Agent" );







%>











<!-- BEGIN MAINCONTENT -->

<% if(aszHostID.equalsIgnoreCase( "volengagrm" ) ){ %>
</head>







<div style="clear: both; background:url('<%=aszPortal%>/imgs/home_shadowstrip_clr.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>

<% } else { %>

<div style="clear: both; background:url('<%=aszPortal%>/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>

<% } %>



<div id="welcomebox">

<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/walking.jpg" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering."  
<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" ))|| (aszHostID.equalsIgnoreCase( "volenghlic" )) ){ %>
	width="399" 
<% } else { %>
	width="417" 
<% } %>
height="234" />
</div>

<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase("volengurbmin"))
){ %>
<%@ include file="/template_include/home_jointsearch.inc" %>
<% }else{ %>
<%@ include file="/template_include/home_search.inc" %>
<% } %>
</div>


<div style="float:left;"><img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>





<div class="cleardiv"></div>



<div id="midbox1">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

  <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Search thousands of short term urban missions trips." width="250" height="158" border="0"/><br clear="all"  /></A>

<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteerbox_photo-wide.gif" alt="Search thousands of short term missions trips."width="313" height="158" border="0"/><br clear="all"  /></A>

<% } else { %>

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="<%=aszPortal%>/imgs/volunteerbox_photo.gif" alt="Search thousands of short term missions trips."width="250" height="158" border="0"/><br clear="all"  /></A>

<% } %>



 <!--  <br clear="all"  /> -->

  <div id="midbox1_content" style="height: 400px;">

  <br>

	 <h2 class="head"><p>Summer Ministry Internships</p></h2>
	 
	 <h2><p>Service Area</p></h2>

	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&partner=Association+of+Gospel+Rescue+Missions+(AGRM)&Submit=Submit" title="Rescue Mission Summer Ministry Internships">Rescue Missions</a> <br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4763&Submit=Submit" title="Children and Youth Work Summer Ministry Internships">Children &amp; Youth</a><br/>

	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4765&Submit=Submit" title="Community Development Summer Ministry Internships">Community Development</a><br/>

	 <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4775&Submit=Submit" title="Health and Medicine Summer Ministry Internships">Health &amp; Medicine</a><br/>
	 
	 <a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&servicearea1=4785&Submit=Submit" title="Teaching Summer Ministry Internships">Teaching</a></p>

	 <h2><p>Location</p></h2>

	 	<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+East&Submit=Submit">

		East Coast U.S. </a><br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+West&Submit=Submit">

		West Coast U.S. </a><br> 

		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+South&Submit=Submit">

		Southern U.S. </a> <br>

			

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+Central&Submit=Submit">

		Central U.S. </a> </p>

	<h2><p>Benefits Offered</p></h2>
	
	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&roomboard=8106&Submit=Submit">Room &amp; Board</a><br/>
	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&stipend=8108&Submit=Submit">Stipend</a></p>
		

							  </div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>

<% } else { %>

	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>

<% } %>







<div id="midbox2">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/uywi/organizationbox_photo_uywi.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" border="0"/><br clear="all" /></a>

<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="245" height="158" alt="picture of church" border="0"/></a>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>



<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo-wide.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="316" height="168" alt="picture of church" border="0" /></a>

<% } else { %>

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Search over 1,000 short-term missions trips and volunteer opportunities! Find opportunities in urban missions, Christian internships, and church volunteering." width="253" height="158" alt="picture of church" border="0"/></a>

<% } %>







  <div id="midbox2_content" style="height: 390px;">
<br/>
<h2 class="head">
  <p>

Work Study Opportunities</p>
</h2>


		<p><strong>What is Work Study?
        </strong><br />
        Work study is a  financial aid program for college students in the USA. If you qualify for work-study financial aid, you may elect to do your work study in the community rather than on campus.<br/>
</p>
	  <br />

	  <br />
	  

  </div>

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

	  <img src="<%=aszPortal%>/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>

<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

	

<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>

<% } else { %>

	  <img src="<%=aszPortal%>/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>

<% } %>





<div id="midbox3">

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

 <img src="<%=aszPortal%>/imgs/uywi/trainingbox_photo_uywi.gif" alt="Short Term Missions Training and Orientation" width="248" height="158" border="0"/><br clear="all" /></a>

<% } else  if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">

<img src="<%=aszPortal%>/imgs/trainingbox_photo-wide.gif" alt="Short Term Missions Training and Orientation"width="316" height="168" border="0"/><br clear="all" /></a>

<% } else { %>

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">

<img src="<%=aszPortal%>/imgs/trainingbox_photo.gif" alt="Short Term Missions Training and Orientation"width="248" height="158" border="0"/><br clear="all" /></a>

<% } %>

  

  <div id="midbox3_content" style="height: 390px;">
<br/>
	<h2 class="head"><p>One to Three Year<br/> Ministry Internships</p></h2>
	
	<h2><p>Length<p></h2>
	
	<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=one+year&searchkey1=&Submit=Submit">One year</a><br />
		
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=1+to+2+years&searchkey1=&Submit=Submit">

		One to two years </a> <br />
		
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=2+to+3+years&searchkey1=&Submit=Submit">

		Two to three years </a></p>
		
		<!--<h2><p>Location</p></h2>

	 	<p>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+East&Submit=Submit">

		East Coast U.S. </a><br>

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+West&Submit=Submit">

		West Coast U.S. </a><br> 

		

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+South&Submit=Submit">

		Southern U.S. </a> <br>

			

		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&duration=summer&region=USA+Central&Submit=Submit">

		Central U.S. </a> </p>-->
	
	<h2><p>Benefits Offered</p></h2>

<p><a href="http://www.urbanministry.org/volunteer/mapshortterm?filter0[]=8106">Room and Board<br/>

<a href="http://www.urbanministry.org/volunteer/mapshortterm?filter1[]=8108">Stipend</a></p>

	


  </div>

	

	

<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>

  </div>



<% } else if( (aszHostID.equalsIgnoreCase("voleng1")) 

	|| (aszHostID.equalsIgnoreCase("volengcatholic")) 
	|| (aszHostID.equalsIgnoreCase("volenggospel")) 

	|| (aszHostID.equalsIgnoreCase("volengnewengland")) 

	|| (aszHostID.equalsIgnoreCase("volengboston"))

	|| (aszHostID.equalsIgnoreCase("volengivol")) ) { %>

  <img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>



  <% } else { %>

    <img src="<%=aszPortal%>/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>



    <% } %>



</p>



<div id="endingbox">

<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities" /></div>

<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">

  <p>

  

  <h2><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="urban ministries, short term urban missions organizations, and churches">

	Organizations</a></h2>  </p>

	

	<p>Directory of thousands of <strong>short term missions</strong> and volunteer opportunities. Create an account for your organization to post Christian <strong>short term missions </strong>and internship opportunities to recruit volunteers online. </p> 

	<p><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">

	Register to Recruit Volunteers</a></p>

	<p><a href="<%=aszPortal%>/register.do?method=showlogin" title="login">

	Login</a></p>

<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers and Volunteers</a></p>	



</div>

<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<div style="margin: 5px 20px 5px 20px;" align="left">
<h2>Short Term Missions Training Resources</h2>
<p><a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/Presentation_Files/index.html">How to Find a Volunteer Placement and Serve Well</a> (<a href="http://www.christianvolunteering.org/files/Volunteer_Orientation/Presentation_Files/index.html">Webcast</a>) (<a href="http://www.christianvolunteering.org/files/vol_orientation_online.ppt">PPT</a>)</p>
<p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A120">Short Term Missions Books/Media</a></p>
<p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A116">Short Term Missions Videos</a></p>
<p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A117">Short Term Missions Documents</a></p>
<p><a href="http://www.urbanministry.org/short_term_missions_search/results/taxonomy%3A113">Short Term Missions Bible Studies</a></p>
<p><a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions">Short Term Missions Wiki Encyclopedia</a></p>
<p><a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling">Personality, Vocation, and Calling</a></p>
<p><a href="http://www.urbanministry.org/spiritual_gifts">Spiritual Gifts Resources</a></p>


</div>

	

<% } else { %>

<% } %>

<!--<div class="cleardiv"></div>-->



<!--<div class="cleardiv"></div>-->

</div>



<% if(aszHostID.equalsIgnoreCase( "voleng1" ) ){ %>






<% } else if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="17" height="197" /></div>

<% } else { %>

<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>

<% } %>





  

<div class="cleardiv"></div>



<% } %>



<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->
