<!-- start JSP information -->
<%@ include file="/template/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/home_navigation.inc" %>
<!-- end navigation information -->

<% 
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getAppCodeList( aServiceList, 161 );
%>

<!-- BEGIN MAINCONTENT -->
<% if(aszHostID.equalsIgnoreCase( "volengagrm" ) ){ %>
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip_clr.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>
<% } else { %>
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>
<% } %>

<div id="welcomebox">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_photo.jpg" alt="Become a Christian volunteer today! Search to find opportunities in urban ministry, short term missions, and church volunteering." width="417" height="234" /></div>
<div style="float:left; margin: 10px 0px 5px 10px; width: 300px; z-index: -1;">
<p>
<img src="http://www.christianvolunteering.org/imgs/welcomebox_title..gif" alt="search volunteer opportunities" width="248" height="21" hspace="0" vspace="0" /></p>
<form id="homesearch" NAME="homesearch" method="post" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchTop">
  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
    <tr>
      <td width="55" align="right"><strong>Postal Code</strong></td>
      <td>
      <input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></td>
      <td><strong>Distance</strong></td>
      <td><select id="distance" name="distance" style="width:108px; z-index: -1">
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
      <td colspan="3"><input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/button_home_search.gif" border="0" width="59" height="24" />
      </a></td>
    </tr>
  </table>
</form>
<br>

<b><a href="<%=request.getContextPath()%>/oppsrch.do?method=showOppSearchTemp">advanced
search</a></b>


</div>
<div class="cleardiv"></div>
</div>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>


<div class="cleardiv"></div>

<div id="midbox1">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
  <A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_photo_uywi.gif" alt="Here you can find volunteer opportunities in urban ministry and short term missions." width="250" height="158" border="0"/><br clear="all"  /></A>
<% } else { %>
 <A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_photo.gif" alt="Here you can find volunteer opportunities in urban ministry and short term missions."width="250" height="158" border="0"/><br clear="all"  /></A>
<% } %>

 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
	  <p><A HREF="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_title.gif" border="0" alt="volunteer" width="90" height="27" /></A></p>
	  <p>Register to create a volunteer account, search for volunteer 
		opportunities online or visit our training resources for volunteers in 
		urban ministries, short-term missions, churches and Christian nonprofit 
		organizations. <br />
	      <br />
	      <a href="<%=request.getContextPath()%>/oppsrch.do?method=showSearch">
		Register to Find Christian Volunteer Opportunities</a></p>
	  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/volunteerbox_bottom_uywi.gif" width="250" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>
<% } %>



<div id="midbox2">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_photo_uywi.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" border="0"/><br clear="all" /></a>
<% } else { %>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" alt="picture of church" border="0"/><br clear="all" /></a>
<% } %>


  <div id="midbox2_content">
	<p><A HREF="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	<img src="http://www.christianvolunteering.org/imgs/organizationbox_title.gif" border="0" alt="nonprofit organizations, ministries and churches" width="116" height="27" /></A></p>
	<p>Create an your organization account to post volunteer opportunities and recruit 
	volunteers online. </p>
	<p><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart">
	Register to Recruit Volunteers</a><br />
	  <br />

	<A href="<%=request.getContextPath()%>/org.do?method=showPasteForms">Put our search form on your website</A>

	  <br />
	  <br />
	</p>
	</div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
	  <img src="http://www.christianvolunteering.org/imgs/uywi/organizationbox_bottom_uywi.gif" width="254" height="12" /></div>
<% } else { %>
	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>
<% } %>


<div id="midbox3">
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
 <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_photo_uywi.gif" alt="Volunteer Management Training and Christian Volunteer Orientation" width="248" height="158" border="0"/><br clear="all" /></a>
<% } else { %>
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/trainingbox_photo.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="248" height="158" border="0"/><br clear="all" /></a>
<% } %>
  
  <div id="midbox3_content">
	<p><a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
	<img src="http://www.christianvolunteering.org/imgs/trainingbox_title.gif" border="0" alt="volunteer training" width="64" height="27" /></a></p>
	<p>Take a look at the resources we have to learn more about how to make a 
	difference in your community through volunteering.&nbsp; </p>
	
	<p><a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">Volunteer Management Training 
	for Christian Nonprofits </a></p>
	
	<p>	
	<a href="http://www.christianvolunteering.org/voleng2/Volunteer_Orientation/start.html">
	Christian Volunteer Orientation Training</a> </p>	

  </div>
<% if(aszHostID.equalsIgnoreCase( "volenguywi" ) || aszHostID.equalsIgnoreCase( "volengworldvision" ) ){ %>
  <img src="http://www.christianvolunteering.org/imgs/uywi/trainingbox_bottom_uywi.gif" width="248" height="12" /></div>
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
  <p> <a href= "<%=request.getContextPath()%>/register.do?method=showVolStories">Read volunteer testimonies!</a><br />
  </p>
  <!--   Added 2006.11.07  1719EST   rdsmith***  begin  JSP Cron  -->
   <div style="margin: 5px 20px 5px 20px;" align="left"><%@ include file="../jsprsscronjobs/chrisvolnewsfeeds3.php" %></div>
<!--   Added 2006.11.07  1719EST   rdsmith***  end    JSP Cron  -->

  <!--
  <p><a href="#"><img src="http://www.christianvolunteering.org/imgs/button_sharestory.gif" alt="share your story" width="109" height="22" border="0" /></a></p>
  -->
</div>
<!--<div class="cleardiv"></div>-->
</div>
<% if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="17" height="197" /></div>
<% } else { %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>
<% } %>

  
<div class="cleardiv"></div>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-768097-3";
urchinTracker();
</script>

<!--   Added 2006.10.30   rdsmith***  begin  RSS Feeds  -->
   <div align="center">   <%@ include file="/chrisvolnewsfeeds3.php" %> </div>
<!--    Added 2006.10.30   rdsmith***  end  RSS Feeds  -->


<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->