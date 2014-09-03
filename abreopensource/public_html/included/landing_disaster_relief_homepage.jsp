<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Disaster Relief homepage: ChristianVolunteering.org</title>
<meta keywords="disaster relief, christian volunteer, short term missions, missions trips, Christian, church, Christian volunteering, Christian volunteer, church volunteer, community, service, TechMission, CCDA, Salvation Army, World Vision" />
<meta description="Search disaster relief volunteer opportunities! Find opportunities in urban ministries, Christian internships, and church volunteering." />

<!-- start header information -->
<%@ include file="/template/home_header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<!-- end navigation information -->


<%

String aszLandingParamsURL = "sa/Disaster_Relief";
String aszLandingParamsHash = "fq=service_areas:%22Disaster Relief%22";


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

int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, vidRegion=38;

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getTaxonomyParentCodeList( aRegionList, vidRegion );

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;
if(aszHostID.equalsIgnoreCase("volengchurch"))				iSiteID=iSiteChurchVolTID;
else if(aszSecondaryHost.equalsIgnoreCase("volengivol"))	iSiteID=iSiteiVolTID;

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
String step1FormDisplay = "display:inline;";
String step2FormDisplay = "display:none;";
%>


<!-- custom JS for the homepage - TODO: put this in a file -->
<script type="text/javascript">

$(document).ready(function() {
	$('input#queryLoc').val("<%=maxmind_postal%> (zip code)");
	$('input#query').val("Disaster Relief - (Service Area)");
	
	$('#search_input').find('a:first').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/opp/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:opportunity');
	$('#search_input').find('a:first').html('Browse Disaster Relief Opportunities');
	
	$('#search_input').find('a:last').attr('href', '/s/<%=aszLandingParamsURL%>/ctp/org/results.jsp#<%=aszLandingParamsHash%>&fq=content_type:organization');
	$('#search_input').find('a:last').html('Browse Disaster Relief Organizations');
	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
	
	$(".round_bottom_right").corner("round 50px br"); /*this is a Rounded Bottom Right corner*/
	
	$(".round_bottom").corner("bottom"); /*this is a Rounded Bottom corner*/
	$(".only_top_right").corner("round tr").parent().css('padding', '4px').corner("round tr 10px"); /*this is a Rounded Top Right Only corner*/
	$(".bottom_right").corner("round br").parent().css('padding', '4px').corner("round br 10px"); /*this is a Rounded Top Right Only corner*/
	
	$(".n").corner("notch 14px").parent().css('padding', '4px').corner("notch 4px"); /* this is notched corners*/

});
function postOpportunity1(){
	$('#formStep1').hide();
	$('#formStep2').show();
}
function postOpportunity2(){
	$('#orgFormPost').submit();
//	$('#formStep2').hide();
//	$('#formStep1').show();
	
}
</script>


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
<h1 class="image_text" title="(Image by charliekwalker - http://www.flickr.com/photos/charliekwalker/8134991453)">Find Disaster Relief<br />Volunteer Opportunities</h1>
</div>
</div>
	</div>

<style>
.form-row-label{
	float:left;
}
.form-row-input{
	float:left;
	padding-left: 2px;
}
</style>
	<div style="margin:10px 0px 10px 430px;">
<% if(false == LoginBean.IsSessionLoggedIn( request )) { %>	

	<!-- h2 style="text-transform: none; margin-bottom: 2px; margin-top: -7px;">Create Account to Post Needs</h2-->
	<h2 style="text-transform: none; margin-bottom: 2px; margin-top: -5px;">Post Disaster Relief Needs or Volunteer Opportunities</h2>
	
	<form action="<%=aszPortal%>/org.do" name="orgForm" id="orgFormPost" method="post">
		<input type="hidden" value="addBrandNewDisasterReliefOrgContact" name="method">
		<input type="hidden" value="Organization" name="siteusetype">
		<input type="hidden" value="8865" name="volunteertid">
		<input type="hidden" name="orgnid" value="511070" />
		<input type="hidden" name="role" />
		<input type="hidden" name="requesttype" value="ByContact" />
		<input type="hidden" name="subdomain" value="<%= aszSubdomain %>" />
		<input type="hidden" name="urlalias"  />
		<input type="hidden" name="opppositiontypeid" value="4794" />
		<input type="hidden" name="opppositiontypetid" value="4794" />
		<input type="hidden" name="oppreqcodetype" value="No" />
		<input type="hidden" name="shortform" value="true" />
		<input type="hidden" name="oppreqcodename" value="" />
		<input type="hidden" name="serviceareatidsarray" value="37590" />
		<input type="hidden" name="country" value="us" />
<div id="formStep1" style="<%= step1FormDisplay %>">
<table>
	<tr>
		<td class="right-adjust">		
			Email Address
		</td>
		<td>
			<input type="text" styleclass="textinputwhite" size="25" styleid="email1addr" name="email1addr">
		</td>
		<td class="right-adjust">
			New Password
		</td>
		<td>
			<input type="password" redisplay="false" styleclass="textinputwhite" size="25" name="password1">
		</td>
	</tr>
	<tr>
		<td  colspan="4">
			One Line Summary of Need or Project
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="text" name="opptitle" size="93" styleClass="textinputwhite" value=""/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			Detailed Description, Requirements & Contact Information
		</td>
	</tr>
	<tr>
		<td colspan="4">
<textarea name="oppdesc" rows="3" cols="93" styleClass="textinputwhite"/></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4">
     <br />
     <div style="float: left;">
     	
     	<button type="button" id="submitButton" onclick="postOpportunity1()">Continue</button>
     	<!-- input name="submit" type="submit" value="Continue" class="submit"-->
		
		
		     <a href="https://docs.google.com/a/techmission.org/spreadsheet/ccc?key=0AqFVmw4IVrQUdHNjSl9HUUpFaE9CRndYeWN6M04zRnc#gid=0">Or Cut and Paste Needs and Volunteer Opportunities in Google Spreadsheet</a>
	
     </div>
			
		</td>
	</tr>
</table>
</div>
<div id="formStep2" style="<%= step2FormDisplay %>">
<table>
	<tr>
		<td class="right-adjust">		
			First Name
		</td>
		<td>
			<input type="text" name="firstname" size="33" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			Last Name
		</td>
		<td>
			<input type="text" name="lastname" size="33" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			Address
		</td>
		<td>
			<input type="text" name="addr1" size="33" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			City
		</td>
		<td>
			<input type="text" name="city" size="20" styleClass="textinputwhite" value="" />
			&nbsp;&nbsp;Postal Code&nbsp;&nbsp;
			<input type="text" name="postalcode" size="5" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			State
		</td>
		<td>
			<select id="state" name="state" class="smalldropdown"> 
				<option value=""></option> 
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
			</select>
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			Country
		</td>
		<td>
			<select id="country" name="country" class="smalldropdown"> 
				<option value=""></option> 
				<% 
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				} 
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="4">
     <br />
     <div style="float: left;">
     	
     	<button type="button" id="submitButton" onclick="postOpportunity2()">Continue</button>
     	<!-- input name="submit" type="submit" value="Continue" class="submit"-->
		
		
		     <a href="https://docs.google.com/a/techmission.org/spreadsheet/ccc?key=0AqFVmw4IVrQUdHNjSl9HUUpFaE9CRndYeWN6M04zRnc#gid=0">Or Cut and Paste Needs and Volunteer Opportunities in Google Spreadsheet</a>
	
     </div>
			
		</td>
	</tr>
</table>
</div>
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

<h2 style="text-transform: none;">Post Disaster Relief Needs or Volunteer Opportunities</h2>

<form action="<%= aszPortal %>/org.do?method=processCreateOpportstep1" focus="opptitle" name="orgForm" id="orgFormPost" method="post">
<input type="hidden" name="method" value="processCreateOpportstep1" />
<input type="hidden" name="orgnid" value="511070" />
<input type="hidden" name="role" />
<input type="hidden" name="requesttype" value="ByContact" />
<input type="hidden" name="subdomain" value="<%= aszSubdomain %>" />
<input type="hidden" name="urlalias"  />
<input type="hidden" name="opppositiontypeid" value="4794" />
<input type="hidden" name="opppositiontypetid" value="4794" />
<input type="hidden" name="oppreqcodetype" value="No" />
<input type="hidden" name="shortform" value="true" />
<input type="hidden" name="oppreqcodename" value="" />
<input type="hidden" name="serviceareatidsarray" value="37590" />
<input type="hidden" name="country" value="us" />

<div id="formStep1" style="<%= step1FormDisplay %>">
		
    <div class="form-row" style="padding-top: 0px; margin-top: 0px;">
		One Line Summary of Need or Project
    </div> 
	<div>
		<input type="text" name="opptitle" size="93" styleClass="textinputwhite" value=""/>
	</div>
	<div class="form-row">
		Detailed Description, Requirements & Contact Information
	</div>
	<div>
<textarea name="oppdesc" rows="3" cols="93" styleClass="textinputwhite"/></textarea>
	</div>
	
     <br />
     <div style="float: left;">
     	     	<button type="button" id="submitButton" onclick="postOpportunity1()">Post</button>
     	<!-- input type="submit" value="post"/ -->
		     <a href="https://docs.google.com/a/techmission.org/spreadsheet/ccc?key=0AqFVmw4IVrQUdHNjSl9HUUpFaE9CRndYeWN6M04zRnc#gid=0">Or Cut and Paste Needs and Volunteer Opportunities in Google Spreadsheet</a>
     </div>
	
</div>
<div id="formStep2" style="<%= step2FormDisplay %>">
<table>
	<tr>
		<td class="right-adjust">		
			Address
		</td>
		<td>
			<input type="text" name="addr1" size="33" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			City
		</td>
		<td>
			<input type="text" name="city" size="20" styleClass="textinputwhite" value="" />
			&nbsp;&nbsp;Postal Code&nbsp;&nbsp;
			<input type="text" name="postalcode" size="5" styleClass="textinputwhite" value="" />
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			State
		</td>
		<td>
			<select id="state" name="state" class="smalldropdown"> 
				<option value=""></option> 
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
			</select>
		</td>
	</tr>
	<tr>
		<td class="right-adjust">		
			Country
		</td>
		<td>
			<select id="country" name="country" class="smalldropdown"> 
				<option value=""></option> 
				<% 
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				} 
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="4">
     <br />
     <div style="float: left;">
     	
     	<button type="button" id="submitButton" onclick="postOpportunity2()">Continue</button>
     	<!-- input name="submit" type="submit" value="Continue" class="submit"-->
		
		
		     <a href="https://docs.google.com/a/techmission.org/spreadsheet/ccc?key=0AqFVmw4IVrQUdHNjSl9HUUpFaE9CRndYeWN6M04zRnc#gid=0">Or Cut and Paste Needs and Volunteer Opportunities in Google Spreadsheet</a>
	
     </div>
			
		</td>
	</tr>
</table>
</div>
</form>





<% } %>

</div>
	<%//@ include file="/template_include/home_solr_search.inc" %>
</div>

<div style="float:left;"><img src="http://www.christianvolunteering.org/imgs/welcomebox_right_trans-sm.gif" width="17" height="240" /></div>


<div class="cleardiv"></div>

<div style="margin-top:10px;">

<!-- ==========================        ==============================================================-->


<div class="cleardiv"></div>

<div id="endingbox" class="landingpage">
<div id="" class="search_results" style="padding:0px 20px;">
<h2 style="margin:12px 0 0">Disaster Response Volunteer Opportunities</h2>
<% 
String aszFQParams="fq=service_areas:&quot;Disaster Relief&quot;";
String aszFQParamsURL="/sa/Disaster_Relief";
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
	<div id="keyword_search">service_areas:&quot;Disaster Relief&quot;</div>
	<div id="fq_search">service_areas:&quot;Disaster Relief&quot;</div>
	<div id="distance_search"></div>
	<div id="d_init"></div>
<input type="submit" name="Submit" value="Search" id="search_solr_params">
	<div id="geofilt_facet"></div>
	<div id="content_type_search"><%=aszContentType%></div>
	<div id="aszFQParamsURL"><%=aszFQParamsURL%></div>
	<div id="aszFQParams"><%=aszFQParams%></div>
</div>

  <div id="hd" class="solr" style="padding: 5px 0px;">
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>


<div id="solr_results" style="display:none;">
<% int iFeedsResults=0; %>
<%//@ include file="/template_include/iterate_solr_results.inc" %>
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
