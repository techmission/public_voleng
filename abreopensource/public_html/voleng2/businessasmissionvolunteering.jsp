<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Virtual Volunteer Opportunities</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidTripLength=263, vidRoomBoard=265, vidPosFreq=268, vidSchedDate=269, vidBenefits=286;


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
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );
aCodes.getAppCodeList( aLocalAffilList, 175 );

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

<% if(aszHostID.equalsIgnoreCase( "volengivol" )){ %>
<% } else { %>




<!-- BEGIN MAINCONTENT -->
<% if(aszHostID.equalsIgnoreCase( "volengagrm" ) ){ %>
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip_clr.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>
<% } else { %>
<div style="clear: both; background:url('http://www.christianvolunteering.org/imgs/home_shadowstrip.gif') no-repeat; height: 2px; font-size: 0px">&nbsp;</div>
<% } %>

<div id="welcomebox">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/pic/guy_with_laptop_small.jpg" alt="Become a Christian volunteer today! Search to find opportunities in urban ministry, short term missions, and church volunteering."  /></div>
<div style="float:left; margin: 10px 0px 5px 10px; width: 300px;">
<p>
<h1>Business and Mission: Volunteering Using Professional Skills
<input type="hidden" name="method" value="processOppSearchTop">
  </h1>
<form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&Submit=Submit">
<table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;"><!--  z-index: -1; -->
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
      <td colspan="3"><input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/button_home_search.gif" title="search Christian volunteer,church volunteer, virtual volunteer, and urban ministry service opportunities" border="0" width="59" height="24" />
      </a> 
      &nbsp &nbsp &nbsp &nbsp<b><a href="<%=aszPortal%>/oppsrch.do?method=showOppSearchAll" title="search Christian volunteer,church volunteer, virtual volunteer, and urban ministry service opportunities">advanced
	search</a></b>
</td>
    </tr>
  </table>
</form>

</div>
<div class="cleardiv"></div>
</div>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans.gif" width="17" height="234" /></div>



<div class="cleardiv"></div>






<div id="midbox1">

 <A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch"><img src="http://www.christianvolunteering.org/imgs/volunteerbox_photo.gif" alt="Here you can find volunteer opportunities in urban ministry and short term missions."width="250" height="158" border="0"/><br clear="all"  /></A>


 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
  <br>
<h2><p>What is Virtual Volunteering?</p></h2>

	<p>Have you ever wanted to volunteer without having to travel? Do you have professional or technical 
	skills that you want to use in volunteering? Virtual volunteering allows you to do this by volunteering from 
	your home or office.</p>
	<p><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&voltype=Virtual&Submit=Submit">View All Virtual Volunteering Opportunities</a></p>
	<!--<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>

	<h4 align="right"><p> So you can stay at home....</p></h4>-->


				  </div>

	  <img src="http://www.christianvolunteering.org/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>



<div id="midbox2">

<a href="<%=aszPortal%>/org.do?method=showOrgStart"><img src="http://www.christianvolunteering.org/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" alt="picture of church" border="0"/><br clear="all" /></a>



  <div id="midbox2_content">
<br>
  <h2><p>
Browse by Service Area</p></h2>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Computers+and+Technology&Submit=Submit" title="Find Virtual and Online Christian Computers and Tech Volunteering">
		Computers and Technology</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Community+Development&Submit=Submit" title="Find Virtual and Online Christian Community Development Volunteering"/>
		Community Development</a></p>
	
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Disabilities+Ministry&Submit=Submit" title="Find Virtual and Online Christian Disabilities Ministry Volunteering">
		Disabilities Ministry </a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Education+and+Literacy&Submit=Submit" title="Find Virtual and Online Christian Education and Literacy Volunteering">
		Education and Literacy </a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Evangelism&Submit=Submit" title="Find Virtual and Online Christian Evangelism Volunteering">
		Evangelism</a></p>
	
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Homelessness+and+Housing&Submit=Submit" title="Find Virtual and Online Christian Homelessness and Housing Volunteering">
		Homelessnessness and Housing</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&category1=Visitation+%2F+Friendship&Submit=Submit" title="Find Virtual and Online Christian Visitation and Friendship Volunteering">
		Visitation and Friendship</a></p>

	   <!-- <h4><p>...while volunteering for an organization across town ... </p></h4>-->

	  <br />
	  <br />
	</div>

	  <img src="http://www.christianvolunteering.org/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>




<div id="midbox3">

<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="http://www.christianvolunteering.org/imgs/trainingbox_photo.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="248" height="158" border="0"/><br clear="all" /></a>

  
  <div id="midbox3_content">
	<br>
	 <h2><p>Browse by Skills / Career</p></h2>
	  	
		<p>
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Administrator+%2F+Office+Skills&Submit=Submit" title="Find Virtual and Online Christian Administrator / Office Volunteering">
		Administrator / Office Skills, </a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Computer+%2F+Tech+Support&Submit=Submit" title="Find Virtual and Online Christian Computer / Tech Support Volunteering">
		Computer / Tech Support, </a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Computer+Programmer&Submit=Submit" title="Find Virtual and Online Christian Computer Programmer Volunteering">
		Computer Programmer,</a> &nbsp 
 		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Grant+%2F+Proposal+Writer&Submit=Submit" title="Find Virtual and Online Christian Grant /Proposal Writer Volunteering">
		Grant /Proposal Writer,</a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Language+Translator+%2F+Interpreter&Submit=Submit" title="Find Virtual and Online Christian Language Translator / Interpreter Volunteering">
		Language Translator / Interpreter,</a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Prayer+Minister+%2F+Intercessor&Submit=Submit" title="Find Virtual and Online Christian Prayer Minister / Intercessor Volunteering">
		Prayer Minister / Intercessor,</a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtual&skills1=Public+Relations&Submit=Submit" title="Find Virtual and Online Christian Public Relations Volunteering">
		Public Relations,</a> &nbsp 
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&voltype=Virtualskills1=Web+%2F+Graphics+Designer&Submit=Submit" title="Find Virtual and Online Christian Web / Graphics Design Volunteering">
		Web / Graphics Design</a> </p>
	<!--	<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<h4><p>...or on the other side of the globe.</p></h4>-->

	</div>	
  <img src="http://www.christianvolunteering.org/imgs/trainingbox_bottom.gif" width="248" height="12" /></div>


</p>


<div id="endingbox">
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities"/></div>
<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">

  <p> Look around this page to find an opportunity using your skills, interests, and passions.
  <h2><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches">
	Organizations</a></h2>  </p>
	
	<p>Create an account for your organization to post Christian volunteer opportunities and recruit volunteers online. </p> 
	<p><a href="<%=aszPortal%>/org.do?method=showOrgStart" title="Create Volunteer Recruitment Account">
	Register to Recruit Volunteers</a></p>
	<p><a href="<%=aszPortal%>/register.do?method=showlogin">
	Login</a></p>
<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers and Volunteers</a></p>	

</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div style="margin: 5px 20px 5px 20px;" align="left">
<h2><p>ChristianVolunteering.org in the news:</p></h2>

<p><a href="http://www.wdcmedia.com/newsArticle.php?ID=2102">Taking Christian Volunteerism to the HighTech Level</a></p>
<p><a href="http://www.ydr.com/religion/ci_4515784">NEW WEB SITE: Find Volunteer opportunities</a></p>
<p><a href="http://www.breakingchristiannews.com/articles/display_art.html?ID=3151">New Website a Great Tool for Christian Volunteers and Organizations</a></p>
<%@ include file="/jsprsscron/virtualvolunteeringrssfeeds.htm" %></div>

<!--<div class="cleardiv"></div>-->
</div>






<% if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="17" height="197" /></div>
<% } else if(aszHostID.equalsIgnoreCase( "voleng1" ) ) { %>
   <% if( isFirefox ){ %>
	<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_750.gif" width="21" height="754" /></div>
   <% } else if( isMSIE ){ %>
	<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_750.gif" width="21" height="782" /></div>
   <% } else { %>
	<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_650.gif" width="21" height="655" /></div>
<% } %>
<% } else { %>
<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/endingbox_right_clr.gif" width="21" height="197" /></div>
<% } %>

  
<div class="cleardiv"></div>


<% } %><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->