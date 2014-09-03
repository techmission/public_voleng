<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<HEAD>
<meta name="robots" content="index, nofollow">
</HEAD>
<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getAppCodeList( aServiceList, 161 );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


%>


<!-- BEGIN MAINCONTENT -->

<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>


<div id="welcomebox" class="wide">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/boston-main-pic.jpg" alt="Become a Christian volunteer today! Search to find opportunities in urban ministry, short term missions, and church volunteering."  /></div>
<div style="float:left; margin: 10px 0px 5px 10px; width: 300px;">
<p><h1>New England Christian Volunteering</h1></p>
<form id="homesearch" NAME="homesearch" method="get" action="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&region=USA+New+England&Submit=Submit">
<input type="hidden" name="method" value="processOppSearchTop">
  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;"><!--  z-index: -1; -->
    <tr>
      <td width="55" align="right"><strong>Postal Code</strong></td>
      <td>
      <input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></td>
      <td><strong>Distance</strong></td>
      <td><select id="distance" name="distance" style="width:108px;"><!--  z-index: -1; -->

        <option value="5">5 Miles (8K)</option>

        <option value="20">20 Miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
      </select></td>
    </tr>
    
    <tr>
      <td align="right"><strong>Service<br />
        Area</strong></td>
      <td colspan="3">
		<SELECT id="category1" name="category1" style="width: 233px; //width:207px; z-index: 0;">
				<option value="" selected="selected">Select Categories</option> 
				<%
				for(int index=0; index < aServiceList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
					if(null == aAppCode) continue;
					out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
				%>
		</SELECT></td>
      </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3"><input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/button_home_search.gif" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" border="0" width="59" height="24" />
      </a></td>
    </tr>
  </table>
</form>
<br>

<b><a href="<%=request.getContextPath()%>/oppsrch.do?method=showOppSearchAll" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>


</div>
<div class="cleardiv"></div>
</div>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>



<div class="cleardiv"></div>

<div id="midbox1">

 <A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/boston_volunteerbox_photo-wide.gif" alt="Here you can find volunteer opportunities in urban ministry and short term missions."width="312" height="168" border="0"/><br clear="all"  />
 </A>


 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
  <br>
<h2><p>Browse by Type</p></h2>

	<p><A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&Submit=Submit" title="Find Boston Christian Virtual Volunteer Opportunities ">
	Virtual Volunteering</A> <br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&region=USA+New+England&focusare4=Yes&Submit=Submit" title="Find Boston Christian Group Volunteer Opportunities here">
	Group Volunteering</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&region=USA+New+England&Submit=Submit" title="Find Boston Christian Short-term Urban Missions Volunteer Opportunities here">
	Short Term Mission Trips</A><br>
	<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=Short-term+Missions+%2F+Volunteer+Internship&region=USA+New+England&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Boston Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></p>
	<h2><p>	Browse by Location</p></h2>
	<p><A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=NH&Submit=Submit" title="Find Christian Volunteering in New Hampshire">
	New Hampshire</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=CT&Submit=Submit">
	Connecticut</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=RI&Submit=Submit">
	Rhode Island</A><br>	
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=MA&Submit=Submit" title="Find Christian Volunteering in Massachusetts">
	Massachusetts</A><br>
	<!--<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=VT&Submit=Submit">
	Vermont</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&state=ME&Submit=Submit">
	Maine</A><br>-->
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&postalcode=02108&distance=City&Submit=Submit" title="Find Boston Christian Volunteering">
	Boston</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&postalcode=01109&distance=City&Submit=Submit" title="Find Christian Volunteering in Worcester, Massachusetts">
	Springfield / Worcester</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&postalcode=01109&distance=City&Submit=Submit" title="Find Christian Volunteering on the North Shore of Massachusetts">
	North Shore</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&postalcode=01109&distance=City&Submit=Submit" title="Find Christian Volunteering on the North Shore of Massachusetts">
	South Shore</A><br>
	<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&postalcode=02138&distance=5&Submit=Submit" title="Find Christian Volunteering in Cambridge, Massachusetts">
	Cambridge</A><br>
	

  </div>

<img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>




<div id="midbox2">

<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/boston_orgbox_photo-wide.gif" alt="Organizations can recruit Christian volunteers here." width="316" height="168" alt="picture of church" border="0"/><br clear="all" /></a>



  <div id="midbox2_content">
<br>
  <h2><p>
Browse by Service Area</p></h2>
		 <!--<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Addiction+and+Recovery&imageField.x=26&imageField.y=15">
		Addiction and Recovery</a> -->
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Administrative&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in administrative service area">
		Administrative</a></p>
	
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Children+and+Youth&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in children and youth service area">
		Children and Youth</a></p>
	
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Community+Development&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in community development service area"/>Community Development</a></p>
		<!--
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Computers+and+Technology&imageField.x=26&imageField.y=3">Computers and Technology </a>
		-->
			
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Education+and+Literacy&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in education and literacy service area">
	Education and Literacy </a></p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Employment+Training&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in employment training service area">
		Employment Training </a></p>

		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Food+Ministry+%2F+Hunger&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in food ministry/hunger service area">
		Food Ministry / Hunger </a></p>
	
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Homelessness+and+Housing&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in homelessness and housing service area">
		Homelessness and Housing</a></p>
	
	
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&category1=Immigrants+and+Refugees&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in immigrants and refugees service area">
		Immigrants and Refugees </a></p>

		<!--<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Teaching&imageField.x=41&imageField.y=9">
		Teaching </a>
		
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Tutoring&imageField.x=30&imageField.y=9">
		Tutoring </a>
		
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Visitation+%2F+Friendship">
		Visitation / Friendship </a>
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchTop&category1=Women%27s+Ministry">
		Women's Ministry </a>	-->
	



	  <br />
	  <br />
	</div>

	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>



<div id="midbox3">

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/boston_trainingbox_photo-wide.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="309" height="168" border="0"/><br clear="all" /></a>

  
  <div id="midbox3_content">
	<br>
	 <h2><p>Browse by Skills / Career</p></h2>
	  	
		<p>
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Administrator+%2F+Office+Skills&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in administrator or office skills">
		Administrator / Office Skills </a></p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Artist+%28Performing%2FCreative%29&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in artist skills">
		Artist (Performing / Creative) </a> </p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Computer+%2F+Tech+Support&affiliation=&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in computer or tech support skills">
		Computer / Tech Support </a> </p>
		<p>	<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Computer+Trainer&affiliation=&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in computer trainer skills">
		Computer Trainer </a> </p>
		<!--<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Construction+%2F+Maintenance&region=USA+New+England&Submit=Submit">
		Construction / Maintenance</a> 
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Financial+Services+%2F+Advisor&region=USA+New+England&Submit=Submit">
		Financial Services / Advisor </a> -->
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Fundraiser&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in fundraiser skills">Fundraiser </a> </p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Grant+%2F+Proposal+Writer&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in grant or proposal writer skills">
		Grant / Proposal Writer </a> </p>
		<!--<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Language+Tutor&region=USA+New+England&Submit=Submit">
		Language Tutor</a> 
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Photographer&region=USA+New+England&Submit=Submit">Photographer</a> 
			<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Public+Relations&region=USA+New+England&Submit=Submit">Public Relations</a> 
		<a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Public+Relations&region=USA+New+England&Submit=Submit">Scientist / Mathematician</a> -->
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Teacher+%2F+Trainer&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a> </p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearch1&voltype=Both&skills1=Web+%2F+Graphics+Designer&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a> </p>
		<p><a href="<%=request.getContextPath()%>/oppsrch.do?method=processOppSearchAll&voltype=&skills1=Youth+Worker+%2F+Childcare&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a> </p>	</div>

  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>


</p>

<div id="endingbox">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities"/></div>
<div style="float:left; margin: 5px 20px; width: 575px;height:223px">
  <p>
  
  <h2><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches">
	Organizations</a></h2>  </p>
	
	<p><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">
	Register to Recruit Christian Volunteers</a></p>
	<p>
Training: <a href="<%=request.getContextPath()%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles</a>, <a href="<%=request.getContextPath()%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
	Links</a>, <a href="<%=request.getContextPath()%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a>, 
	<a href="<%=request.getContextPath()%>/bookrecommendations.jsp" alt="Get Training for Volunteer Managers" title="Urban Ministry Books">
	Book Recommendations</a>, 
      
      <a href="<%=request.getContextPath()%>/virtualresources.jsp">Virtual Volunteering Resources</a>, 
      <a href="<%=request.getContextPath()%>/staffrecruitment.jsp">Christian Staff Recruitment</a>,
            <a href="<%=request.getContextPath()%>/workstudy.jsp">Recruiting Work-Study Students</a>, 
     <a href="<%=request.getContextPath()%>/stmresources.jsp"> Short-Term Missions Training </a>, 
      <a href="<%=request.getContextPath()%>/devotionals.jsp">Devotionals</a>,
	<a href="<%=request.getContextPath()%>/spiritualgifts.jsp">Spiritual Gifts</a>, 
      <a href="<%=request.getContextPath()%>/calling.jsp">Christian Calling and Vocation</a>,  
       <a href="http://www.techmission.org/training/conference_materials.php">Technology Conference Materials</a>, 
      <a href="http://www.urbanministry.org">Urban Ministry </a>,
       <a href="http://www.safefamilies.org/presentations.php">Online Safety </a>

	
	Christian Nonprofit Volunteer Management Training
	<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html" alt="Get Training for Volunteer Managers" title="Get Training for Volunteer Managers">Webcast</a>&nbsp 
	<a href="http://www.christianvolunteering.org/files/Volunteer_Management_online_version.ppt" alt="Get Training for Volunteer Managers" title="Get Training for Volunteer Managers">Powerpoint</a><br>
	Christian Volunteer Orientation <a href="http://www.christianvolunteering.org/files/Volunteer_Orientation_Faith_2007/Presentation_Files/index.html" alt="Get Training for Volunteer Managers" title="Training for Volunteers">
	Webcast</a>&nbsp <a href="http://www.christianvolunteering.org/files/vol_orientation_2007_faith_NoAudio.ppt" alt="Get Training for Volunteer Managers" title="Training for Volunteers - easily customizable">Powerpoint</a><br>
Emmanuel Gospel Center's CityServe Newsletter Archives: 
<a href="<%=request.getContextPath()%>/CityServeWinter05.jsp">Winter 2005</a>, 
<a href="<%=request.getContextPath()%>/CityServeJun06.jsp">May/June 2006</a>, 
<a href="<%=request.getContextPath()%>/CityServeAug06.jsp">July/August 2006</a>, 
<a href="<%=request.getContextPath()%>/CityServeOct06.jsp">September/October 2006</a>, and 
<a href="<%=request.getContextPath()%>/CityServeDec06.jsp">November/December 2006</a></p>
</div>


<div style="margin: 5px 20px 5px 20px;" align="left">
<h2><p>ChristianVolunteering.org in the news:</p></h2>

<p><a href="http://headlines.agapepress.org/archive/1/92007c.asp" title="TechMission Launches Online Christian Volunteer Opportunity Directory">TechMission Launches Online Christian Volunteer Opportunity Directory</a><br>
	<a href="http://www.christiannewswire.com/news/646341804.html" title="PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays">PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays</a><br>
	<a href="http://www.christiannewswire.com/news/453011207.html" title="PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers">PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers</a><br>
	<a href="http://www.wdcmedia.com/newsArticle.php?ID=2102" title="Taking Christian Volunteerism to the HighTech Level">Taking Christian Volunteerism to the HighTech Level</a><br>
	<a href="http://www.ydr.com/religion/ci_4515784">NEW WEB SITE: Find Christian Volunteer opportunities</a><br>
	<a href="http://www.breakingchristiannews.com/articles/display_art.html?ID=3151" title="New Website a Great Tool for Christian Volunteers and Organizations">New Website a Great Tool for Christian Volunteers and Organizations</a></p><%@ include file="/jsprsscron/chrisvolnewsfeeds3.php" %></div>

<!--<div class="cleardiv"></div>-->
</div>

<% if( isFirefox ){ %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_1220.gif" width="21" height="1223" /></div>
<% } else if( isMSIE ){ %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_1220.gif" width="21" height="1262" /></div>
<% } else { %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_1220.gif" width="21" height="1190" /></div>
<% } %>
  
<div class="cleardiv"></div>
<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->