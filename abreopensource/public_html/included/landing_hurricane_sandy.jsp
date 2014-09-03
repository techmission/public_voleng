<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hurricane Sandy Disaster Relief: ChristianVolunteering.org</title>
<meta keywords="Hurricane Sandy, disaster relief, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, community, service, TechMission, CCDA, Salvation Army, World Vision" />
<meta description="Search Hurricane Sandy disaster relief volunteer opportunities! Find opportunities in urban ministries, Christian internships, and church volunteering." />

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<!-- end navigation information -->


<%

String aszLandingParamsURL = "sa/Hurricane_Sandy";
String aszLandingParamsHash = "fq=service_areas:%22Hurricane Sandy%22";


String search_postal_s = maxmind_postal;
if(! maxmind_countryCode.equalsIgnoreCase("US") )	search_postal_s=maxmind_countryName;
try{
	if(request.getParameter("postal_code") != null){
		if(request.getParameter("postal_code").length()>0){
			search_postal_s = request.getParameter("postal_code");
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}
String search_location=search_postal_s;
%>


<!-- custom JS for the homepage - TODO: put this in a file -->
<script type="text/javascript">
// Events for hiding/showing stories on homepage.
function hideStoriesOpp() {
	$('#opp1_s').hide(); $('#opp1').removeClass('active_tab');
	$('#opp2_s').hide(); $('#opp2').removeClass('active_tab');
	$('#opp3_s').hide(); $('#opp3').removeClass('active_tab');
	$('#opp4_s').hide(); $('#opp4').removeClass('active_tab');
}

function hideStoriesOrg() {
	$('#org1_s').hide(); $('#org1').removeClass('active_tab');
	$('#org2_s').hide(); $('#org2').removeClass('active_tab');
	$('#org3_s').hide(); $('#org3').removeClass('active_tab');
	$('#org4_s').hide(); $('#org4').removeClass('active_tab');
}

function hideStoriesChurch() {
	$('#church1_s').hide(); $('#church1').removeClass('active_tab');
	$('#church2_s').hide(); $('#church2').removeClass('active_tab');
	$('#church3_s').hide(); $('#church3').removeClass('active_tab');
	$('#church4_s').hide(); $('#church4').removeClass('active_tab');
}

$(document).ready(function() {
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
	
	$(".round_bottom_right").corner("round 50px br"); /*this is a Rounded Bottom Right corner*/
	
	$(".round_bottom").corner("bottom"); /*this is a Rounded Bottom corner*/
	$(".only_top_right").corner("round tr").parent().css('padding', '4px').corner("round tr 10px"); /*this is a Rounded Top Right Only corner*/
	$(".bottom_right").corner("round br").parent().css('padding', '4px').corner("round br 10px"); /*this is a Rounded Top Right Only corner*/
	
	$(".n").corner("notch 14px").parent().css('padding', '4px').corner("notch 4px"); /* this is notched corners*/

	$('input#queryLoc').val("<%=maxmind_postal%> (zip code)");
	$('input#query').val("Hurricane Sandy - (Service Area)");
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Hurricane Sandy Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Hurricane Sandy Organizations');
	
});
</script>


<% 
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38;

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
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );


int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}

ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getTaxonomyCodeListByRelated( aServiceList, vidService, iSiteID );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );


String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );

//maxmind_postal="";
%>

<style>
#banner {
  border-bottom: 1px solid #496F37;
}
#welcomebox h1.image_text {
    font-family: Gill Sans, Arial Narrow,Arial,Helvetica,sans-serif;
    font-size: 26px;
    text-align: right;
    color: #FFFFFF;
    text-shadow: 0.1em 0.1em #333333;
}
#welcomebox.landingpage #home_solr_search {
    margin-top: 45px;
}
</style>
<!-- BEGIN MAINCONTENT -->
<div id="welcomebox" class="wide landingpage search_results">
	<div class="image" style="float:left;"> <!-- img can have maximum width of about 485px; -->
<div style="position: relative; background: url(http://www.christianvolunteering.org/imgs/sandy-sm3.jpg); height: 240px; width: 417px;" >

<div style="position: absolute; bottom: 0; left: 0.5em; width: 400px; font-weight: bold; color: #fff;">
<h1 class="image_text" title="Hurricane Sandy opportunities (Image by charliekwalker - http://www.flickr.com/photos/charliekwalker/8134991453)">Find Hurricane Sandy<br />Volunteer Opportunities</h1>
</div>
</div>
	</div>
	<div style="margin:10px 0px 10px 450px;">
<% if(false == LoginBean.IsSessionLoggedIn( request )) { %>	
<h2 style="text-transform: none;">Create Account to Post Needs!</h2>
<form action="<%=aszPortal%>/register.do" name="individualForm" method="post">
<input type="hidden" value="addBrandNewHurrSandyOrgContact" name="method">
<input type="hidden" value="www.HurricaneSandyVolunteer.org" name="subdomain">
<input type="hidden" value="Organization" name="siteusetype">
<input type="hidden" value="494934" name="orgnid">
	
<input type="hidden" value="8865" name="volunteertid">


            <!--nested table -->
            <table cellspacing="0" cellpadding="0" border="0" align="center" width="80%" id="splash" style="font-size: 12px;">
              <!-- MSTableType="layout" -->
				
			  <tbody>
				<tr>
        		<td>Email Address</td>
       			<td height="23"><input type="text" styleclass="textinputwhite" size="35" styleid="email1addr" name="email1addr"></td>
       			</tr>
				<tr>
                <td>New Password</td>
                <td height="23" colspan="2">
					<input type="password" redisplay="false" styleclass="textinputwhite" size="25" name="password1">
                </td>
             	</tr>
				<tr>
                <td>First name <span class="criticaltext">*</span></td>
                <td height="23"><input type="text" styleclass="textinputwhite" size="25" styleid="namefirst" name="namefirst"></td>
				</tr>
				<tr>
                <td>Last name <span class="criticaltext">*</span></td>
                <td height="23"><input type="text" styleclass="textinputwhite" size="25" styleid="namelast" name="namelast"></td>
              	</tr>


				<tr>
                <td colspan=2 height="23"></td>
              	</tr>
				<tr>
                <td height="23"><input name="submit" type="submit" value="Continue" class="submit"></td>
              	</tr>
              	
</tbody></table>

</form>	


<% } else { %>

<style>
  .form-row {
    padding: 3px;
  }
  
  h2 {
    padding: 0px;
    margin: 0px;
  }
</style>

<h2>Post a Need</h2>

<form action="<%= aszPortal %>/org.do?method=processCreateOpportstep1" focus="opptitle" name="orgForm" method="post">
<input type="hidden" name="method" value="processCreateOpportstep1" />
<input type="hidden" name="orgnid" value="494934" />
<input type="hidden" name="role" />
<input type="hidden" name="requesttype" value="ByContact" />
<input type="hidden" name="subdomain" value="<%= aszSubdomain %>" />
<input type="hidden" name="urlalias"  />
<input type="hidden" name="opppositiontypeid" value="4794" />
<input type="hidden" name="opppositiontypetid" value="4794" />
<input type="hidden" name="oppreqcodetype" value="No" />
<input type="hidden" name="shortform" value="true" />
<input type="hidden" name="oppreqcodename" value="" />
<input type="hidden" name="serviceareatidsarray" value="35949" />
<input type="hidden" name="country" value="us" />

<div>
		
    <div class="form-row" style="padding-top: 0px; margin-top: 0px;">
		One Line Summary of Need or Project
    </div> 
	<div>
		<input type="text" name="opptitle" size="40" styleClass="textinputwhite" value=""/>
	</div>
	<div class="form-row">
		Detailed Description, Requirements & Contact Information
	</div>
	<div>
<textarea name="oppdesc" rows="3" cols="78" styleClass="textinputwhite"/></textarea>
	</div>
	
	<div>

	<div>
		<div class="form-row">
			Address <input type="text" name="addr1" size="33" styleClass="textinputwhite" value="" />
			City <input type="text" name="city" size="20" styleClass="textinputwhite" value="" />
		</div>
		
		<div class="form-row"> 
			State
			<SELECT id="state" name="state" class="smalldropdown"> 
				<OPTION value=""></OPTION> 
				<% 
				
				if(null != aStateList){
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
						out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
					}
				} 
				%>
			</SELECT>
			Postal Code
			<input type="text" name="postalcode" size="5" styleClass="textinputwhite" value="" />
                        <input type="submit" value="post"/>
		</div>
		
	</div><!-- close id="virtualaddr" -->
  </div><!-- close  id="location"-->	
	
	</div><!-- end id form -->

</form>














<% } %>

</div>
	<%//@ include file="/template_include/home_solr_search.inc" %>
</div>

<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans-sm.gif" width="17" height="240" /></div>


<div class="cleardiv"></div>

<div style="margin-top:10px;">

<!-- ==========================        ==============================================================-->
 <div class="container_stories_box">
 
<div id="stories_box" class="c">
      <div id="stories_header">
       <h3 class="title">Upload a Spreadsheet of Needs</h3>
    </div><!-- end stories_header -->


	<div style="" id="opp1_s">
		<div id="img_opp1" style="background-image:url('http://www.christianvolunteering.org/imgs/spreadsheet_icon.png')" class="round_bottom_right"></div><!-- Spreadsheet Icon is part of the Oxygen GNU/GPL icon set -->
		<div class="main_text">
                <p>To upload a spreadsheet of needs,
                <a href="https://docs.google.com/a/techmission.org/spreadsheet/ccc?key=0AqFVmw4IVrQUdHNjSl9HUUpFaE9CRndYeWN6M04zRnc#gid=0">cut and paste in Google Spreadsheet</a> or <a href="http://www.urbanministry.org/import-opps">Upload an Excel Spreadsheet</a>. (To upload, you must first create an account above.)
                </p>
		</div><!-- end: main_text -->
	</div><!-- end: story1_s-->
        
     
        </div><!--end: div#stories_box.c-->
  
</div> <!--end: div.container_stories_box-->


  <!-- ==========================       ==============================================================-->
 <div class="container_org_stories_box">
 
<div id="org_stories_box" class="c">
      <div id="org_stories_header">
	<% if(true == LoginBean.IsSessionLoggedIn( request )) { %>
        <h3 class="title"><a href="http://www.hurricanesandyvolunteer.org/org.do?method=showOrgAddOpp1&orgnid=494934&shortform=true&requesttype=">Post a Need</a></h3>
	<% } else { %> 
        <h3 class="title"><a href="http://www.hurricanesandyvolunteer.org/register.do?method=showlogin">Post a Need</a></h3>
	<% } %>
    </div><!-- end stories_header -->
        
    <script>
      function redirect_to_login() {
    	  alert("You must be logged in to post a need. You will be redirected to the login page.");
    	  window.location = 'http://www.hurricanesandyvolunteer.org/register.do?method=showlogin';
      }
    </script>
        
	<div style="" id="org1_s">
		<div id="img_org1" class="round_bottom_right"></div>
		<div class="main_text">
			<% if(true == LoginBean.IsSessionLoggedIn( request )) { %>
			    <p>You may post needs <a href="http://www.hurricanesandyvolunteer.org/org.do?method=showOrgAddOpp1&orgnid=494934&shortform=true&requesttype=">here</a>.</p>
			<% } else { %> 
				<p><span style="color: red;">You must either <a href="http://www.hurricanesandyvolunteer.org/register.do?method=showlogin">log in</a> or create an account via the form at the top of this page before you can post a need.</span></p>
			<% } %>
		</div><!-- end volunteerinfo -->
	</div>
        
         
  </div><!-- end : .org_stories_box .c  -->
</div> <!--end: div.container_stories_box-->  
    <!-- ==========================   Internships    ==============================================================-->
<div class="container_church_stories_box"> 
<div id="church_stories_box" class="c">
      <div id="church_stories_header">
        <h3 class="title"><a href="<%=aszPortal%>/volunteergettingstarted.jsp">Churches &amp; Organizations</a></h3>
    </div><!-- end stories_header -->
        
	<div style="" id="church1_s">
		<div id="img_church1" class="round_bottom_right"></div>
		<div class="main_text" style="height: 165px;">
                <p>Prepare your church using disaster preparedness resources from the <a href="http://www.namb.net/readychurch/">North American Mission Board</a>,&nbsp;
                                <a href="http://www.umcor.org/UMCOR/Programs/Disaster-Response/US-Disaster-Response/US-Disaster-Response">United Methodist</a> and the&nbsp;
                                <a href="https://www.christianemergencynetwork.org/about-cen">Christian Emergency Network</a>.
                        </p><br/>
		<p>
                                Take courses from <a href="http://training.fema.gov/IS/crslist.asp">FEMA</a>,&nbsp;
                                <a href="http://disaster.salvationarmyusa.org/training/?classes">Salvation Army</a>, or&nbsp; the
                                <a href="http://ncdp.crlctraining.org/">National Center for Disaster Preparedness</a>.
                        </p>
                </div><!-- end main_text -->
	</div>
  </div>
</div> <!--end: div.container_stories_box-->     


<div class="cleardiv"></div>

<div id="endingbox" class="landingpage">
<div id="" class="search_results" style="padding:0px 20px;">
<% 
String aszFQParams="fq=service_areas:&quot;Hurricane Sandy&quot;";
String aszFQParamsURL="/sa/Hurricane_Sandy";
String aszOrgNamePrint="";
String aszOppTitlePrint="";
String aszContentType="";
String aszKewordsSpaceSeparated="";
int iCounter=0;
//String DecimalFormat="";
%>

<%//@ include file="/template_include/ajax-solr_parameters.inc" %>
<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>
<div id="params" style="display:none;">
	<div id="subdomain"><%=aszHostID%></div>
	<div id="keyword_search">service_areas:&quot;Hurricane Sandy&quot;</div>
	<div id="fq_search">service_areas:&quot;Hurricane Sandy&quot;</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	aszFQParamsURL is <%=aszFQParamsURL%>
	aszFQParams is <%=aszFQParams%>
</div>

  <div id="hd" class="solr" style="padding: 5px 0px;">
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
<a href="http://www.hurricanesandyvolunteer.org/s/organization_name/Hurricane_Sandy_Volunteering/org_nid/494934/ctp/opp/results.jsp#fq=org_nid:494934&fq=content_type:opportunity">Hurricane Sandy Volunteer Opportunities</a>

<%@ include file="/template_include/search_sidebar.inc" %>
</div><!-- end solr_results div -->


<!--br clear="all"/-->
	<div id="nav">
		<ul id="pager"></ul>  <ul id="pager-header"></ul>  <ul id="map_link" class="volunteer">Map These Results</ul>
	</div>
	<br clear="all">


<div id="map_container" style="display:none;">
	<br />
	<div id="map" style="width: 100%; height: 400px;">
	</div>
	<br /><br />
</div>

	
	<div id="docs" ></div>
	
	
<div id="simplyhired" style="display:none;">
<br />
<span style="font-size:20px; color:green;">*</span> Jobs by <a href="http://www.simplyhired.com/" style="text-decoration:none"><span style="color: rgb(0, 159, 223); font-weight: bold;">Simply</span><span style="color: rgb(163, 204, 64); font-weight: bold;">Hired</span></a>
</div>

	<div class="cleardiv"></div>
</div>
</div>


<!-- start footer information -->
<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
