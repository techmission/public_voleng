<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Virtual Volunteer and Online Volunteering Opportunities: ChristianVolunteering.org</title>

<% } else { %>
<% } %>



<!-- start wide header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end wide header information -->


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
int iTemp=0;

// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38,
		vidTripLength=263, vidRoomBoard=265, vidPosFreq=268, vidSchedDate=269, vidBenefits=286;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

// vidMemberType=42
int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264										&workstudy=8104
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;

// vidBenefits=286
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszBothTID = "" + iBoth;
String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;


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

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
//aCodes.getTaxonomyCodeList( aServiceList, vidService );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );


String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;


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

<div id="welcomebox" class="shorttermmissionspage">
<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/VolunteerNYourPJs4CVPage.jpg" alt="Search hundreds of virtual volunteering and online volunteer opportunities"  /></div>
<div style="float:left; margin: 0px 0px 5px 10px; width: 285px;">
  <form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
    <table width="485" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
      <tr>
        <td><div style="float:left; margin: 0px 0px 5px 10px; width: 285px;">
          <h1>Christian <span style="cursor: help;" title="volunteering remotely online from your home or office" onmouseover='this.style.cursor="help";'>Virtual Volunteering</span></h1>
          <input type="hidden" name="method" value="processOppSearchAll" />
          <table width="285" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
            <tr>
              <td align="right"><strong>Skills</strong></td>
              <td colspan="2">
			          <select id="skills1id" name="skills1id" class="smalldropdown" >
	<option value="" selected></option>
			<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			%>

				</SELECT>

			  </td>
            </tr>
            <tr>
              <td align="right"><strong>Service<br />
                Area</strong></td>
              <td colspan="1">
			  <SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" > 
				<option value="" selected="selected"></option> 
               <%
					for(int index=0; index < aServiceList.size(); index++){
						iTemp = 0;
						aszTemp = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>

				</SELECT>
</td>
              <td style="white-space:nowrap;"><input type="checkbox" styleclass="check" value="4761" id="servicearea2" name="servicearea2"/>
                <strong>Board Member Opportunities</strong> <br/>
                <input type="checkbox" styleclass="check" value="4795" id="virt_vol" name="postype" onclick="toggle(this, 'country', 'postalcode')" checked />
                <strong>Virtual Volunteer Opportunities</strong></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td colspan="1"><input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/></td>
              <td style="white-space:nowrap;">
              </td>
             
            </tr>
          </table>
        </div>          <b><a href="http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4761 " title="browse all opportunities for board members"></a></b></td>
      </tr>
        
    </table>
 <div style="padding-left:230px; white-space:nowrap;"><b><a href="http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4795" title="browse all virtual volunteering opportunities by skill set">Browse Virtual Volunteering by Skill</a></b> <br/>
                <b><a href="http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4761 " title="browse all opportunities for board members">Browse All Board Positions</a></b></div>  
</form>
</div>
<div class="cleardiv"></div>
</div>
<div id="welcomebox_end_img" style="float:left;">
<% if (
                (aszHostID.equalsIgnoreCase("volengurbmin")) ||
                (aszHostID.equalsIgnoreCase("volengcityreaching")) ||
                (aszHostID.equalsIgnoreCase( "volengmissionamerica" )) ||
                (aszHostID.equalsIgnoreCase("volengsaddleback"))
) { %>
<img src="<%=aszPortal%>/imgs/welcomebox_right_transbeige.gif" width="17" height="234" />
<% }else{ %>
<img src="<%=aszPortal%>/imgs/welcomebox_right_trans.gif" width="17" height="234" />
<% } %>
</div>




<div class="cleardiv"></div>






<div id="midbox1">


<% if( aszNarrowTheme=="true" ) { %>

 <A HREF="<%=aszPortal%>/advancedsearch.jsp"><img src="<%=aszPortal%>/imgs/volunteerbox_photo.gif" alt="Here you can find volunteer opportunities in urban ministry and short term missions."width="250" height="158" border="0"/><br clear="all"  /></A>
<% } else { %>
	<img src="<%=aszPortal%>/imgs/volunteerbox_top-wide.gif" width="312" height="12" />
	<div id="midbox1_photo">
<A HREF="<%=aszPortal%>/advancedsearch.jsp"><img src="<%=aszPortal%>/imgs/work-from-home.jpg" alt="Search Christian volunteer opportunities in urban ministry and short term missions."width="295" height="156" border="0"/><br clear="all"  /></A>
	</div><!-- end: midbox1_photo-->
<% } %>

 <!--  <br clear="all"  /> -->
  <div id="midbox1_content">
  <br>
<h2><p><b>What is Virtual Volunteering and Online Volunteering?</b></p></h2>

	<p>Have you ever wanted to volunteer without having to travel? Do you have professional or technical 
	skills that you want to use in volunteering? Virtual volunteering allows you to do this by volunteering 
	online from 
	your home or office. 
	<p> Look around this page to find an opportunity using your skills, interests, and passions.</p>
	<p><a href="http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit">View All Virtual Volunteering Opportunities</a></p>

<!--	<p>&nbsp;</p>
	<p>&nbsp;</p>

	<h4 align="right"><p> So you can stay at home....</p></h4>-->


				  </div>
				  
<% if( aszNarrowTheme=="true" ) { %>
	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom.gif" width="250" height="12" /></div>
<% } else { %>
	  <img src="<%=aszPortal%>/imgs/volunteerbox_bottom-wide.gif" width="312" height="12" /></div>
<% } %>
<div id="midbox2">


<% if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
<a href="<%=aszPortal%>/recruitvolunteers.jsp"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="245" height="158" alt="picture of church" border="0"/></a>
<% } else  if( aszNarrowTheme=="true" ) { %>
<a href="<%=aszPortal%>/recruitvolunteers.jsp"><img src="<%=aszPortal%>/imgs/organizationbox_photo.gif" alt="Organizations can recruit Christian volunteers here." width="253" height="158" alt="picture of church" border="0"/></a>
<% } else { %>

<img src="<%=aszPortal%>/imgs/organizationbox_top-wide.gif" width="315" height="12" />
<div id="midbox2_photo">
<a href="<%=aszPortal%>/recruitvolunteers.jsp"><img src="<%=aszPortal%>/imgs/work-from-home2.jpg" alt="Organizations can recruit Christian volunteers here." width="295" height="156" alt="picture of church" border="0" /><br clear="all" /></a>
	</div><!-- end: midbox2_photo-->
<% } %>


  <div id="midbox2_content">
<br>
  <h2><p>
Browse by Service Area</p></h2>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&skills1id=&servicearea1=4759&postype=4795&imageField=Search" title="Find Virtual and Online Christian Administrative Volunteering">
		Administrative</a></p>
    
    <p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4766&Submit=Submit" title="Find Virtual and Online Christian Computers and Tech Volunteering">
		Computers and Technology</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4765&Submit=Submit" title="Find Virtual and Online Christian Community Development Volunteering"/>
		Community Development</a></p>
	
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4767&Submit=Submit" title="Find Virtual and Online Christian Disabilities Ministry Volunteering">
		Disabilities Ministry </a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4768&Submit=Submit" title="Find Virtual and Online Christian Education and Literacy Volunteering">
		Education and Literacy </a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4772&Submit=Submit" title="Find Virtual and Online Christian Evangelism Volunteering">
		Evangelism</a></p>
	
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4776&Submit=Submit" title="Find Virtual and Online Christian Homelessness and Housing Volunteering">
		Homelessness and Housing</a></p>
    
    <p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&skills1id=&servicearea1=4778&postype=4795&imageField=Search" title="Find Virtual and Online Christian Justice and Legal Volunteering">
		Justice and Legal</a></p>

		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&skills1id=&servicearea1=4782&postype=4795&imageField=Search" title="Find Virtual and Online Christian Prison Ministry Volunteering">
		Prison Ministry</a></p>
    
    <p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&servicearea1=4789&redirected=true" title="Find Virtual and Online Christian Women's Ministry Volunteering">
		Women's Ministry</a></p>
		
		<!--	<br>
 <h4><p>...while volunteering for an organization across town ... </p></h4>-->


	  
	</div>
<% if( aszNarrowTheme=="true" ) { %>
	  <img src="<%=aszPortal%>/imgs/organizationbox_bottom.gif" width="254" height="12" /></div>
<% } else { %>
<img src="<%=aszPortal%>/imgs/organizationbox_bottom-wide.gif" width="315" height="12" /></div>
<% } %>


<div id="midbox3">
<% if( aszNarrowTheme=="true" ) { %>
<a href="http://www.techmission.org/webcasts/VolunteerManagementTraining/start.html">
<img src="<%=aszPortal%>/imgs/trainingbox_photo.gif" alt="Volunteer Management Training and Christian Volunteer Orientation"width="248" height="158" border="0"/><br clear="all" /></a>
<% } else { %>
<img src="<%=aszPortal%>/imgs/trainingbox_top-wide.gif" width="309" height="12" />
<div id="midbox3_photo">
<a href="http://www.churchvolunteering.org">
<img src="<%=aszPortal%>/imgs/313251515_9d6929f671_b.jpg" alt="Church Volunteer Resources and Search"width="290" height="156" border="0"/><br clear="all" /></a>
</div><!-- end: midbox3_photo-->
<% } %>
  
  <div id="midbox3_content">
	<br>
	 <h2><p>Browse by Skills / Career</p></h2>
	  	
		<p>
		<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4720&Submit=Submit" title="Find Virtual and Online Christian Administrator / Office Volunteering">
		Administrator / Office Skills </a></p>
    <p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&skills1id=4727&servicearea1=&postype=4795&imageField=Search" title="Find Virtual and Online Christian Administrator / Office Volunteering">
		Business / Marketing Specialist </a></p>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4729&Submit=Submit" title="Find Virtual and Online Christian Computer / Tech Support Volunteering">
		Computer / Tech Support </a> </p>
		<p>	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4729&Submit=Submit" title="Find Virtual and Online Christian Computer Programmer Volunteering">
		Computer Programmer</a> </p>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4741&Submit=Submit" title="Find Virtual and Online Christian Grant /Proposal Writer Volunteering">
		Grant /Proposal Writer</a> </p>
		<p>	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4742&Submit=Submit" title="Find Virtual and Online Christian Language Translator / Interpreter Volunteering">
		Language Translator / Interpreter</a> </p>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4748&Submit=Submit" title="Find Virtual and Online Christian Prayer Minister / Intercessor Volunteering">
		Prayer Minister / Intercessor</a> </p>
		<p><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4750&Submit=Submit" title="Find Virtual and Online Christian Public Relations Volunteering">
		Public Relations</a> </p>
		<p>	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&skills1id=4755&servicearea1=&imageField=Search" title="Find Virtual and Online Christian Video Production Volunteering">
		Video Production</a> </p>
    <p>	<a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&skills1id=4756&Submit=Submit" title="Find Virtual and Online Christian Web / Graphic Design Volunteering">
		Web / Graphic Design</a> </p>
		<!--<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<h4><p>...or on the other side of the globe.</p></h4>-->

	</div>	
  
<% if( aszNarrowTheme=="true" ) { %>
    <img src="<%=aszPortal%>/imgs/trainingbox_bottom.gif" width="248" height="12" />
  <% } else { %>
  <img src="<%=aszPortal%>/imgs/trainingbox_bottom-wide.gif" width="309" height="12" />
    <% } %>
  
  
  
  </div>


</p>


<div id="endingbox" style="display:none;">
<div style="float:left;"><img src="<%=aszPortal%>/imgs/pic/book.jpg" width="245" height="193" title="organizations can register Christian volunteer, church volunteer, urban ministry, short term missions, and virtual volunteer opportunities"/></div>
<div style="float:left; margin: 5px 20px 5px 20px; width: 350px;">
  <p>
  
  <h2><a href="<%=aszPortal%>/recruitvolunteers.jsp" title="nonprofit organizations, urban ministries, short term urban missions organizations, and churches">
	Organizations</a></h2>  </p>
	
	<p>Create an account for your organization to post Christian volunteer opportunities and recruit volunteers online. </p> 
	<p><a href="<%=aszPortal%>/recruitvolunteers.jsp" title="Create Volunteer Recruitment Account">
	Register to Recruit Volunteers</a></p>
	<p><a href="<%=aszPortal%>/register.do?method=showlogin">
	Login</a></p>
<p><a href="http://www.christianvolunteering.org/register.do?method=showTraining" title="training for Volunteer Managers, Volunteer Recruitment, Volunteers, Volunteer Orientation">Training for Volunteer Managers and Volunteers</a></p>	

</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br>
<div style="margin: 5px 20px 5px 20px;" align="left">
	<h2><p>ChristianVolunteering.org in the news:</p></h2>
	
	<p>	<a href="http://www.earnedmedia.org/tm0313.htm" title="PRESS RELEASE: Website Enables Virtual Volunteering and Virtual Missionaries">PRESS RELEASE: Website Enables Virtual Volunteering and Virtual Missionaries</a><br>
		<a href="http://www.christiannewswire.com/news/646341804.html" title="PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays">PRESS RELEASE: Website Provides Directory of Hundreds of Christian Volunteer Opportunities for Holidays</a><br>
		<a href="http://www.christiannewswire.com/news/453011207.html" title="PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers">PRESS RELEASE: Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers</a><br>
		<a href="http://www.wdcmedia.com/newsArticle.php?ID=2102" title="Taking Christian Volunteerism to the HighTech Level">Taking Christian Volunteerism to the HighTech Level</a><br>
		<a href="http://www.ydr.com/religion/ci_4515784">NEW WEB SITE: Find Christian Volunteer opportunities</a><br>
		<a href="http://www.breakingchristiannews.com/articles/display_art.html?ID=3151" title="New Website a Great Tool for Christian Volunteers and Organizations">New Website a Great Tool for Christian Volunteers and Organizations</a>
	</p>
	<%@ include file="/jsprsscron/virtualvolunteeringrssfeeds.htm" %>
</div>

<!--<div class="cleardiv"></div>-->
</div>






<% if(aszHostID.equalsIgnoreCase( "volengccda" ) ){ %>
   <% if( isFirefox ){ %>
	<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_750.gif" width="17" height="754" /></div>
   <% } else if( isMSIE ){ %>
	<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_650.gif" width="17" height="740" /></div>
   <% } else { %>
	<div style="float:left;"><img src="<%=aszPortal%>/imgs/endingbox_right_650.gif" width="17" height="655" /></div>
   <% } %>
<% } else if( (aszHostID.equalsIgnoreCase( "voleng1" ) ) 
        || (aszHostID.equalsIgnoreCase("volengcatholic"))
        || (aszHostID.equalsIgnoreCase("volenggospel"))
        || (aszHostID.equalsIgnoreCase("volengurbmin"))
        || (aszHostID.equalsIgnoreCase("volengchurch"))
        || (aszHostID.equalsIgnoreCase("volengfamily"))

        || (aszHostID.equalsIgnoreCase("volengnewengland"))
        || (aszHostID.equalsIgnoreCase("volengboston"))
 ||
	(aszHostID.equalsIgnoreCase( "volenghlic" ) )|| 
	(aszHostID.equalsIgnoreCase( "volengyounglife" ) ) || 
	(aszHostID.equalsIgnoreCase( "volengboston" ) )|| 
        (aszHostID.equalsIgnoreCase("volengworldvision")) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" ) ) 
) { %>
<% } else { %>
<div style="float:left;"></div>
<% } %>

  
<div class="cleardiv"></div>

<!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
