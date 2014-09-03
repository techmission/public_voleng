<!-- start JSP information -->
<%@ include file="/template/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="template/um_header_home.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/empty_navigation.inc" %>
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





<!-- BEGIN MAINCONTENTs -->
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip-wide.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>

<div id="welcomebox">
<div style="float:left;">
<img src="http://www.christianvolunteering.org/imgs/welcomebox_photo.jpg" alt="Christian Volunteering: Search to find opportunities in urban ministry, short term missions, and church volunteering." 
	width="417" 
height="234" />
</div>
	<div style="float:left; margin: 10px 0px 5px 10px; width: 300px;">

<% if (aszHostID.equalsIgnoreCase( "voleng1" )){ %>
<p>
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch">
<img src="http://www.christianvolunteering.org/imgs/welcomebox_title..gif" alt="search christian volunteer opportunities" width="248" height="21" hspace="0" vspace="0" border="0"/></A></p>
<% } else { %>
<p>
<table><tr><td>
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch">
<img src="http://www.christianvolunteering.org/imgs/welcomebox_title..gif" alt="search Christian volunteer church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" width="248" height="21" hspace="0" vspace="0" border="0"/></A></td>
<td valign="top">
<b><a href="<%=request.getContextPath()%>/login.jsp" title="Login to your account">login</a></b></td></tr></table>
</p>

<% } %>
<form id="homesearch" NAME="homesearch" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="voltype" value="">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="partner" value="Salvation Army">
<% } %>
  <table 
  
  <% if(
  	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
width="400"
<% } else { %>
width="300"
<% } %>

border="0" cellpadding="0" cellspacing="5" style="font-size: 12px; z-index: -1;">
    <tr>
      <td width="75" align="right"><strong>Postal Code</strong></td>
      <td>
      <input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></td>
      <td><strong>Distance</strong></td>
      <td><select id="distance" name="distance" style="width:108px; z-index: -1;">

        <option value="5">5 Miles (8K)</option>

        <option value="20">20 Miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
      </select></td>
    </tr>
    
    <tr>
      <td align="right"><strong>Service Area</strong></td>
      <td colspan="3">
		<SELECT id="category1" name="category1" style="width: 233px; z-index: 0;">
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
      <td colspan="3"><input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="http://www.christianvolunteering.org/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" />
      </a></td>
    </tr>
  </table>
</form>


<b><a href="<%=request.getContextPath()%>/advancedsearch.jsp" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>
<h2><a href="<%=request.getContextPath()%>/shorttermmissions.jsp">search short term urban missions</a></h2>
</div>
<div class="cleardiv"></div>
</div>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>


<div class="cleardiv"></div>

<div id="midbox1">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" )){ %>
  <A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Here you can find Christian volunteer opportunities in urban ministry and short term missions." width="250" height="158" border="0"/><br clear="all"  /></A>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_photo-wide.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="313" height="158" border="0"/><br clear="all"  /></A>
<% } else { %>
<A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_photo.gif" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="250" height="158" border="0"/></A> 
<% } %>

 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
	  <p><A HREF="<%=request.getContextPath()%>/search.jsp"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_title.gif" border="0" alt="Register Christian Volunteer Opportunities or Short Term Missions" width="90" height="27" title="Christian volunteers,church volunteers, short term urban missionaries, virtual volunteers, and urban ministry volunteers start here"/></A></p>
	  <p>Register to create a volunteer account, <b>search for Christian volunteer 
		service opportunities online</b> or visit our training resources for volunteers in 
		<b>urban ministries, short-term missions, churches and Christian nonprofit 
		organizations</b>.
	      <br />
	     <p> <a href="<%=request.getContextPath()%>/individualregistration.jsp" alt="Sign up Here for Christian Volunteer Opportunities" title="Register to find Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">
		Register</a> to
			<a href="<%=request.getContextPath()%>/advancedsearch.jsp" alt="Discover Christian Volunteer Opportunities" title="Discover search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">
		Search</a> for Christian Volunteer Opportunities</p>
		<p>Interested in <a href="<%=request.getContextPath()%>/virtualvolunteer.jsp" title="Discover Christian volunteer,church volunteer, virtual volunteer, and urban ministry service opportunities">
		Virtual Volunteering, </a><a href="<%=request.getContextPath()%>/shorttermmissions.jsp" alt="Discover Christian Virtual Volunteer Opportunities" title="Discover Christian volunteer,church volunteer, short term urban missions, and urban ministry service opportunities">
		Short Term Urban Missions,</a> or <a href="http://newengland.christianvolunteering.org/" alt="Discover New England and Boston Christian Volunteer Opportunities">New England Volunteering?</a></p></p>

		<!--<p><a href="<%=request.getContextPath()%>/login.jsp" alt="login" title="login">
		Login</a> or 
		<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" alt="Manage Personal Account" title="Manage Personal Christian Volunteer and Church Volunteer Account">
		Manage Your Account</a></p>-->
			<p><a href="<%=request.getContextPath()%>/volunteerlistings.jsp" alt="View All Christian Volunteer Listings" title="View All Christian Volunteer, Short Term Urban Missions, Virtual Volunteer, and Urban Ministry Service Listings">
			Volunteer Listing Sitemap</a></p>
			<p><a href="<%=request.getContextPath()%>/organizationlistings.jsp" alt="View All Christian Volunteer Listings" title="View All Christian Volunteer, Short Term Urban Missions, Virtual Volunteer, and Urban Ministry Service Listings">
			Organization Sitemap</a></p>

	  </div>
	  
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>
<% } %>



<div id="midbox2">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_photo_uywi.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" border="0"/></a><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><br clear="all" />
</a>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo-wide.gif" alt="Organizations can recruit Christian volunteers here." width="316" height="168" alt="picture of church" border="0" /><br clear="all" /></a>
<% } else { %>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" alt="picture of church" border="0"/><br clear="all" /></a><% } %>


  <div id="midbox2_content">
	<p><A HREF="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	<img src="http://www.christianvolunteering.org/imgs/organizationbox_title.gif" border="0" alt="nonprofit organizations, urban ministries, short term urban missions organizations, and churches" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches" width="116" height="27" /></A></p>
	<p>Create an account for your organization to post Christian volunteer opportunities and 
	<b>recruit volunteers online</b>. You can also find 
	volunteer management training and urban ministry training. <p>To register your 
	organization you'll need to first <a href="<%=request.getContextPath()%>/individualregistration.jsp" title="create Christian volunteer or church volunteer acccount">create an individual account.</a></p> 
	<p>Then you will be able to <a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" alt="Create Christian Volunteer Recruitment Account" title="Create Christian Volunteer Recruitment Account">
	register your organization to recruit volunteers</a>.</p>
	<p><a href="<%=request.getContextPath()%>/login.jsp" alt="login" title="login" >
	Login</a> or 
	<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" alt="Manage Organizational Account" title="Manage urban ministry, church, or short term urban ministry account">
	Manage Your Account</a></p>

	<p><A href="<%=request.getContextPath()%>/searchform.jsp" alt="Christian Volunteers can search from your website!" title="Volunteers can search from your website!">Put our 
	Christian Volunteer Search form on your website</A></p>

	  <br />
	  <br />
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>
<% } %>


<div id="midbox3">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
 <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_photo_uywi.gif" alt="Volunteer Management Training and Christian Volunteer Orientation" width="248" height="158" border="0"/><br clear="all" /></a>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/trainingbox_photo-wide.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="316" height="158" border="0"/><br clear="all" /></a>
<% } else { %>
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/trainingbox_photo.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="248" height="158" border="0"/><br clear="all" /></a>
<% } %>
  
  <div id="midbox3_content">
	<p><a href="<%=request.getContextPath()%>/presentations.jsp">
	<img src="http://www.christianvolunteering.org/imgs/trainingbox_title.gif" border="0" alt="Christian volunteer training" title="Christian Volunteer Recruitment Training, Church Volunteer Management Training, Volunteer Orientation" width="64" height="27" /></a></p>

	
	<p>
<a href="<%=request.getContextPath()%>/articles.jsp" title="Volunteer Management Articles,Links, and Presentations">Articles</a>, <a href="<%=request.getContextPath()%>/links.jsp" title="Volunteer Management Articles,Links, and Presentations">
	Links</a>, <a href="<%=request.getContextPath()%>/presentations.jsp" title="Volunteer Management Articles,Links, and Presentations">Presentations</a><br>
	<a href="<%=request.getContextPath()%>/bookrecommendations.jsp" alt="Get Training for Volunteer Managers" title="Urban Ministry Books">
	Book Recommendations</a>, 
      
      <a href="<%=request.getContextPath()%>/virtualresources.jsp">Virtual Volunteering Resources</a><br>
      <a href="<%=request.getContextPath()%>/staffrecruitment.jsp">Christian Staff Recruitment</a>,
            <a href="<%=request.getContextPath()%>/workstudy.jsp">Recruiting Work-Study Students</a><br>
     <a href="<%=request.getContextPath()%>/stmresources.jsp"> Short-Term Missions Training </a>, 
      <a href="<%=request.getContextPath()%>/devotionals.jsp">Devotionals</a>,
	<a href="<%=request.getContextPath()%>/spiritualgifts.jsp">Spiritual Gifts</a>, 
      <a href="<%=request.getContextPath()%>/calling.jsp">Christian Calling and Vocation</a><br> 
       <a href="http://www.techmission.org/training/conference_materials.php">Technology Conference Materials</a>, 
      <a href="http://www.urbanministry.org">Urban Ministry </a>,
       <a href="http://www.safefamilies.org/presentations.php">Online Safety </a>, 
		<a href="<%=request.getContextPath()%>/prolinks.jsp" title="Technology Links">Professional and Ministry Organizations</a></li>

	
	<p>Christian Nonprofit Volunteer Management Training
	<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html" alt="Get Training for Volunteer Managers" title="Get Training for Volunteer Managers">Webcast</a>&nbsp 
	<a href="http://www.christianvolunteering.org/files/Volunteer_Management_online_version.ppt" alt="Get Training for Volunteer Managers" title="Get Training for Volunteer Managers">Powerpoint</a></p>
	<p>Christian Volunteer Orientation <a href="http://www.christianvolunteering.org/files/Volunteer_Orientation_Faith_2007/Presentation_Files/index.html" alt="Get Training for Volunteer Managers" title="Training for Volunteers">
	Webcast</a>&nbsp <a href="http://www.christianvolunteering.org/files/vol_orientation_2007_faith_NoAudio.ppt" alt="Get Training for Volunteer Managers" title="Training for Volunteers - easily customizable">Powerpoint</a></p>
	<p><a href="<%=request.getContextPath()%>/tmctraining.jsp">TechMission Corps Training</a></p>
	
  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
  <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_bottom_uywi.gif" width="248" height="12" /></div>
<% } else if( 
	(aszHostID.equalsIgnoreCase( "volengcityserve" )) || 
	(aszHostID.equalsIgnoreCase( "voleng1" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) 
){ %>
  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom-wide.gif" width="309" height="12" /></div>
<% } else { %>
  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>
<% } %>

<div id="endingbox">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_photo.jpg" width="245" height="193" /></div>
<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">
  <p>
  <A HREF="<%=request.getContextPath()%>/register.do?method=showVolStories"><img src="http://www.christianvolunteering.org/imgs/endingbox_title.gif" alt="read stories of volunteers" width="243" height="24" border=0 /></A>
  
  
  </p>
  <p><i><b>Mayowa always wanted to use his computer skills to help out his neighbors....</b></i></p>
  <p>Read  this and other exciting stories of volunteers just like you doing really wonderful things in their communities.</p>
  <p> <a href= "<%=request.getContextPath()%>/volunteerstories.jsp">Read volunteer testimonies!</a><br />
  </p>
  <!--
  <p><a href="#"><img src="http://www.christianvolunteering.org/imgs/button_sharestory.gif" alt="share your story" width="109" height="22" border="0" /></a></p>
  -->
 

</div>
<!--<div class="cleardiv"></div>-->
</div>

<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>

  
<div class="cleardiv"></div>


<!-- start footer information -->

<%@ include file="template/um_footer.inc" %><!-- end footer information -->
