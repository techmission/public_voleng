<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48;


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
aCodes.getAppCodeList( aRegionList, 176 );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


%>


<!-- BEGIN MAINCONTENTs -->


<div id="welcomebox" class="beige">

<div id="welcomebox_photo" class="beige">
<img src="<%=request.getContextPath()%>/imgs/welcomebox_photo.jpg" alt="Christian Volunteering: Search to find opportunities in urban ministry, short term missions, and church volunteering">
</div>

<%@ include file="/template_include/home_jointsearch_customized.inc" %>
</div>

<div id="welcomebox_end_img" style="float:left;">
<img src="http://www.christianvolunteering.org/urbanministry/imgs_um/welcomebox_right_gray.gif" width="17" height="234" />
</div>


<div class="cleardiv"></div>

<div id="midbox1">
	<img src="<%=request.getContextPath()%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
	<div id="midbox1_photo">
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/volunteers.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->
 
  <div id="midbox1_content">
	   <p><A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_title.gif" border="0" alt="volunteer" width="90" height="27" /></A></p>
	  <p>Register to create a volunteer account or search for volunteer 
		opportunities online in 
		urban service, short-term volunteer work, churches and nonprofit 
		organizations.
	      <br />
     <p><a href="<%=request.getContextPath()%>/individualregistration.jsp" alt="Sign up Here for Volunteer Opportunities" title="Register to find volunteer, short term internships, virtual volunteer, and urban nonprofit service opportunities"> 
	Register</a> to <a href="<%=request.getContextPath()%>/advancedsearch.jsp">Search</a> for Volunteer Opportunities</p>
	<!--<p><a href="<%=request.getContextPath()%>/virtualvolunteer.jsp" title="Discover volunteer,church volunteer, virtual volunteer, and urban ministry service opportunities">
	Interested in Virtual Volunteering, </a><a href="<%=request.getContextPath()%>/shorttermmissions.jsp" alt="Discover Christian Virtual Volunteer Opportunities" title="Discover Christian volunteer,church volunteer, short term urban missions, and urban ministry service opportunities">
	Short Term Urban Missions,</a> or <a href="http://newengland.christianvolunteering.org/" alt="Discover New England and Boston Christian Volunteer Opportunities">New England Volunteering?</a></p></p>-->		
	<p><a href="<%=request.getContextPath()%>/oppsrch.do?requiredcodetype=No&method=processOppSearchAll&voltype=&Submit=Submit" alt="View All Volunteer Listings" title="View All Volunteer, Short Term Internships, Virtual Volunteer, and Urban Nonprofit Service Listings">
	Volunteer Listing Sitemap</a></p>
	<p><a href="<%=request.getContextPath()%>/register.do?method=showlogin">
	Login </a> 
	<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">
	 or Manage Your Account</a></p> 
			
  </div>
	  <img src="<%=request.getContextPath()%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>


<div id="midbox2">
<img src="<%=request.getContextPath()%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
<div id="midbox2_photo">
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="<%=request.getContextPath()%>/imgs/organizationbox.gif" alt="Organizations can recruit Christian volunteers here." width="295" height="156" alt="picture of church" border="0" /><br clear="all" /></a>
	</div><!-- end: midbox2_photo-->

  <div id="midbox2_content">
		<p><A HREF="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	<img src="http://www.christianvolunteering.org/imgs/organizationbox_title.gif" border="0" alt="nonprofit organizations, ministries and churches" width="116" height="27" /></A></p>
	<p>Create an account for your organization to post volunteer opportunities 
	and recruit volunteers online.  </p> 
	<p>To register your 
	organization you'll need to first <a href="<%=request.getContextPath()%>/individualregistration.jsp" title="create volunteer acccount">create an individual account.</a></p> 
	<p>Then you will be able to <a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" alt="Create Volunteer Recruitment Account" title="Create Volunteer Recruitment Account">
	Register to Recruit Volunteers</a></p>
	<p><a href="<%=request.getContextPath()%>/register.do?method=showlogin">
	Login</a>
	<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">
	 or Manage Your Account</a></p>

	<p><A href="<%=request.getContextPath()%>/org.do?method=showPasteForms">Put our 
	Volunteer Search form on your website</A></p>

	  <br />
	  <br />
  </div>
	  <img src="<%=request.getContextPath()%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>

<div id="midbox3">
<img src="<%=request.getContextPath()%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
<div id="midbox3_photo">
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="<%=request.getContextPath()%>/imgs/training_img.gif" alt="Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
</div><!-- end: midbox3_photo-->  

  
  <div id="midbox3_content">
<p>
	<img src="/imgs/training_orig.gif" border="0" alt="volunteer training" width="64" height="27" href="<%=request.getContextPath()%>/presentations.jsp"/></a></p>
	<p>Take a look at the resources we have to learn more about how to make a 
	difference in your community through volunteering.  </p>

	<p><a href="<%=request.getContextPath()%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles</a>, <a href="<%=request.getContextPath()%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
		Links</a>, <a href="<%=request.getContextPath()%>/register.do?method=showBookRecommendations" title="Book Recommendations">Book Recommendations</a>,  
		<a href="<%=request.getContextPath()%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a>, 
		<a href="<%=request.getContextPath()%>/virtualresources.jsp" title="Virtual Volunteer Resources">Virtual Volunteer Resources</a>,
          <a href="<%=request.getContextPath()%>/workstudy.jsp">Recruiting Work-Study Students</a>, and
		<a href="<%=request.getContextPath()%>/files/Templates_Manual_nonprofit.doc" target="_new" title="Collection of templates for Volunteer Management">
		Volunteer Management Manual</a>, <a href="<%=request.getContextPath()%>/tmctraining.jsp">TechMission Corps Training</a></p>	 	
		<p>		<a href="http://www.christianvolunteering.org/files/Volunteer_Management_TMC_Training.ppt">Nonprofit Volunteer Management Training</a>, 
		<a href="http://www.christianvolunteering.org/files/vol_orientation_general_customizable.ppt" target="_new" title="Customizable Volunteer Orientation for Urban Non-Profits and volunteer managers">
		Urban Non-Profit Volunteer Orientation: Customizable</a>, 							
		<a href="http://www.christianvolunteering.org/files/vol_orientation_general_audience.ppt" title="Urban Service and Volunteer Volunteer Orientation Powerpoint">
		Volunteer Orientation: How to Find a Volunteer Placement and Serve Well</a><br><br> 

		Volunteer Orientation: How to Serve Well <a href="http://www.christianvolunteering.org/files/Volunteer_Orientation_2007/Presentation_Files/index.html" title="Volunteer Management Training Webcast for Urban Nonprofits"> Webcast</a>, 
		<a href="http://www.christianvolunteering.org/files/vol_orientation_2007_NoAudio.ppt" title="Urban Service and Volunteer Volunteer Orientation Powerpoint">Powerpoint</a></p>					

	
  </div>
 <img src="<%=request.getContextPath()%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>


<div class="cleardiv"></div>


<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->
