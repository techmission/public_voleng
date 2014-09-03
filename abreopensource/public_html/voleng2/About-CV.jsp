<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

<title>ChristianVolunteering.org: About ChristianVolunteering.org for Urban Ministries, Short Term Urban Missions, and Virtual Volunteers</title>

<% } else { %>
<% } %>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>

<!-- end navigation information -->


<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">About ChristianVolunteering.org</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">About ChristianVolunteering.org</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">About ChristianVolunteering.org</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	 about christianvolunteering.org </div> 
</div>
<% } %>

       
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
	<img src="/imgs/volunteering.jpg" width="417" height="227" alt="Christian people connected"/></td>
    <td valign="top" class="intsplash"><h1>
	Our Vision </h1>
	<h1>
	<span style="font-weight: 400; font-style: italic">To Match Hundreds of 
	Thousands of Volunteers with Urban Ministries and Short Term Urban Missions 
	Opportunities</span></h1>
      </td>
  </tr>
</table>

		<div id="body">	 
	 <table class="searchtoolfull" cellspacing="0" cellpadding="2" border="0">
        <tbody>
        
        <tr>
			<td>
			
      <table style="padding-top:10px;" border="0" width="100%" id="table1">
					<tr>
						<td><h1>What We Do</h1>
			ChristianVolunteering.org is a program of <a href="http://www.techmission.org">TechMission</a> that matches volunteers to volunteer 
			opportunities and helps organizations recruit volunteers through 
			online volunteer matching.&nbsp; In many ways 
			ChristianVolunteering.org is like job matching websites like 
			Monster.com except that we match volunteers to organizations with 
			volunteer opportunities.&nbsp;&nbsp; Individuals can find 
			opportunities as a local volunteer, virtual volunteer (volunteering 
			from home), short term missionary, or intern at organizations across 
			the US and internationally.&nbsp; Organizations can post volunteer 
			opportunities to recruit volunteers online.&nbsp; ChristianVolunteering.org and UrbanMinistry.org are both programs of TechMission, and share a common login, volunteer opportunity and organization listings.
      </td>
       <td>
						<img style="border:1px solid #999999;" alt="ChristianVolunteering.org Volunteer Opportunities by Service Areas" src="http://www.urbanministry.org/files/images/cv.org%20volunteer%20opps.jpg">
						</td>
            </tr>
				</table>
				
			<hr>
			<h1>The Need for ChristianVolunteering.org </h1>
			</td>         
			</tr>
			<tr>
			 <td valign="top" colspan="2">
       <img border="0" src="http://christianvolunteering.org/images/churchvolunteering.jpg" width="321" style="margin-left:10px; float:right; border:1px solid #999999;" alt="breakdown of Christian volunteers by type of main organization">
				<h2>1. 
				Christian Volunteering is One of the Largest Resources in the 
				World</h2>
				<p>
				Christian Volunteers make up the largest pool of volunteers in 
				the United States.&nbsp; According to the United States 
				Department of Labor, faith-based volunteers are the largest pool 
				of volunteers, representing over 34.8% of volunteers.&nbsp; 
				This combined with the number of Christian volunteers in the 
				world make Christian Volunteers the largest volunteer community 
				in the world. <br>
				</p>
				<p>&nbsp;</p>
        <p>&nbsp;</p>
				<h2>2. Christian Volunteering is incredibly valuable.</h2>
				<table border="0" width="100%" id="table1">
					<tr>
						<td>Faith Based Volunteers Donated Time Valued at $51.8 
						billion each year in the United States alone, According 
						to the Corporation for National and Community Service.&nbsp; 
						That is almost as much as the US Department of 
						Education Budget and one-sixth of the entire US Federal 
						Government non-military discretionary spending.&nbsp; 
						Based on this, globally the value of volunteer is likely 
						to be at least in the hundreds of billions of dollars</td>
						<td width="192">
						<h1 align="center">Value of Faith-Based Volunteer Time = $51.8 billion</h1>
						</td>
					</tr>
				</table>
				<h2>3. 
				There is great potential for Christian Volunteering to Expand 
				Ministry Beyond the Walls of the Church Building</h2>
        <img border="0" src="http://christianvolunteering.org/images/churchvolunteering3.jpg" width="321" style="margin-left:10px; float:right; border:1px solid #999999;" alt="percent of Christian volunteers serving outside community">
				<p>According to Lester Salamon of the Institute for Policy 
				Studies of Johns Hopkins University, only 7-15% of volunteering 
				through churches helps the larger community. This means that 
				only $3.6 to $7.8 billion of this value goes to serve those 
				outside of the church. Increasing the number of Christian 
				volunteers serving in low income communities by 10% would 
				represent over $5.2 billion in increased resources available to 
				those communities through donated time.&nbsp; Last year the 
				leading secular Internet volunteer matching service (VolunteerMatch) 
				placed over 475,000 volunteers in 37,000 nonprofit organizations 
				with the volunteer time valued at $232 million. They also report 
				that organizations using online volunteer matching had over 50% 
				of their volunteers come from online sources. The problem is 
				that Christian organizations do not want to use secular 
				volunteer matching services, and Volunteer Match reports that 
				only 1.8% of their posts are from faith-based organizations. 
				ChristianVolunteering.org makes it easier for volunteers to 
				serve outside the church walls, greatly expanding the 
				effectiveness of volunteers and increasing the resources 
				available to low-income communities.</p>
				
				
				<h2>4. Online Volunteer Matching Can Greatly Increase the Number 
				and Value of Christian Volunteers.</h2>
				<p>Online Volunteer Matching greatly expands the market for 
				volunteering in the same way that eBay expanded the market for 
				auctions and selling &quot;stuff.&quot;&nbsp; Before eBay, individuals had 
				to search at hundreds of garage sales, collectors conventions, 
				flea markets and pawn shops to find a particular item.&nbsp; Now 
				individuals can buy and sell almost anything online on eBay 
				because it is much easier to make a match to very specific 
				items.&nbsp; Online volunteer matching also makes it much easier 
				to find a volunteer match by providing one source where people 
				can find opportunities.&nbsp; A doctor or dentist can quickly 
				search all the nonprofit organization needing their skills in 
				their city through online search.&nbsp; This means that a lawyer 
				who gets paid $200/hour for practicing law, may be underutilized 
				as a volunteer if he is only able to volunteer to stack chairs 
				for the church. Online volunteer matching increases the chances 
				of individuals finding opportunities that match their particular 
				skills.&nbsp; This both increases the value of the volunteering 
				to organizations and increases the number of volunteers by 
				finding matches better suited for particular interests.</p>
				<h2>5. Virtual Volunteering enables new 
				volunteer opportunities by enabling people to volunteer at a 
				distance from their home. </h2>
				<p>A Web designer in San Francisco could volunteer to provide a 
				website to an urban ministry in Kansas City. Some of the many 
				potential virtual volunteer areas include opportunities for 
				accounting, graphics designing, computer programming, grant 
				writing, language translation, legal support, prayer and online 
				tutoring.&nbsp; This has the potential of providing billions of 
				dollars of volunteer time to less-developed countries and 
				low-income communities where it is needed the most. </p>
	 
                <h2>6. Faith-Based Organizations are one of the best ways to resource Black, Latino and Indigenous communities</h2>
                <p>A study by the Greenlining Institute in 2006 showed  that only 3.6% of funding from foundations went to nonprofits let by people of  color. This is despite the fact that people of color make up 52.4% of poverty  in the United States, but only 16.5% of nonprofits are led by people of  color. We have documented in detail many  of the systemic reasons for this in our online Webcast at <a href="http://www.urbanministry.org/fundingbias">http://www.urbanministry.org/fundingbias</a>. One of the most significant reasons for this bias is a  lack of strategies of funders that specifically target Black and Latino leaders  in faith-based organizations. Our data shows that over two-thirds of Black and  Latino nonprofit leaders are in faith-based organizations (FBOs), and that faith-based organizations have twice the number of Black and Latino leaders than secular organizations. These groups  are almost being entirely missed in the strategies of many online nonprofit  capacity building initiatives. Volunteer Match currently only shows that 1.8% of their volunteer opportunities are faith-based (from their annual report), althought faith-based volunteers make up 34.8% of volunteers. ChristianVolunteering.org is the largest online directory of faith-based volunteer opportunities, enabling these communities to be more effectively reached.</p>
                <p>. </p></td>
        </tr>
      </table>			
			
</div>
</div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>

<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->