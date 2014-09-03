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

<style>
#oppsearch {
    background-color: #DCE4FA;
    border-bottom: 1px solid #003366;
    border-top: 1px solid #003366;
    float: left;
    margin: 0;
    text-align: left;
    width: auto;
}
ul.search input.watermark {
    font-size: 12px;
    height: auto;
    width: 120px;
    color: gray;
    font-style: italic;
    padding-left: 3px;
}
input.watermark {
    width: 120px;
}
#search_solr {
	float: left;
    font-size: 12px;
	margin-right: 3px;
}
#queryLoc{
	float: right;
}
</style>

<script>
$(document).ready(function() {	
	// Apply rounded corners (look at http://www.malsup.com/jquery/corner/)
	$(".c").corner("round 6px").parent().css('padding', '4px').corner("round 10px"); /* this is a rounded corner all around with border*/
});
</script>
<!-- BEGIN MAINCONTENTs -->
<img style="margin:8px 8px 5px;" src="http://www.christianvolunteering.org/imgs/church_topbar.png" />

<div id="steps">

<div class="step1" style="float:left; padding: 10px 10px 20px 10px; width:217px;">

<table width="217" height="263" background="/imgs/step1cv.png" cellpadding="5" cellspacing="0"> <tr> <td valign="top" height="5">
<a href="<%=aszPortal%>/individualregistration.jsp"> 
<p style="margin:30px 0 15px 25px; color:#83A2F4; font-size:20px;">Create Account</p>
</a> 
<form id="initialRegistration" method="get" name="initialRegistration" action="http://www.christianvolunteering.org<%=aszPortal%>/register.do"?>
<input type="hidden" name="method" value="showCreateAccount1">
<input type="hidden" name="source" value="churchvolunteer">
<input type="text" style="width: 130px; margin:0 0 15px 25px;" onblur="if(this.value==''){this.value='Church Name';}" onfocus="if(this.value=='Church Name'){this.value='';}" value="Church Name" class="fld watermark" id="username" name="username">
<input type="submit" style="width: 70px; margin:0 0 40px 25px;" value="Submit" id="submit" name="submit">
</form>
<a href="http://www.christianvolunteering.org/<%=aszPortal%>/individualregistration.jsp">
<p style="margin:10px 0 0 55px; color:#4A4A4A; font-size:17px;">Create Free Church Account</p>
</a>
</td>
 </tr> </table>
</div>

<div class="step2" style="float:left; padding: 10px 10px 20px 10px;">
<a href="<%=aszPortal%>/individualregistration.jsp"><table width="217" height="263" background="/imgs/step2cv.png" cellpadding="5" cellspacing="0"> <tr> <td valign="bottom"> <p style="margin:10px 0 0 55px; color:#4A4A4A; font-size:17px;">Add Ministry Opportunities from Your Church and Other Ministries</p>  
 </td> </tr> </table></a>
</div>

<div class="step3" style="float:left; padding: 10px 10px 20px 10px;">
<a href="<%=aszPortal%>/individualregistration.jsp"><table width="217" height="263" background="/imgs/step3cv.png" cellpadding="5" cellspacing="0"> <tr> <td valign="bottom"> <p style="margin:0px 0 15px 55px; color:#4A4A4A; font-size:17px;">Embed Volunteer Directory in Your Church Website</p>  
 </td> </tr> </table></a>
</div>

<div class="step4" style="float:left; padding: 10px 10px 20px 10px;">
<a href="<%=aszPortal%>/individualregistration.jsp"><table width="217" height="263" background="/imgs/step4cv.png" cellpadding="5" cellspacing="0"> <tr> <td valign="bottom"> <p style="margin:0px 0 15px 55px; color:#4A4A4A; font-size:17px;">Volunteers Sign Up for Opportunities on Your Website</p>  
 </td> </tr> </table></a>
</div>

</div>

<div class="cleardiv"></div>

<div style="margin: 0px 15px 0px 15px;">

<hr />

<h2>Learn More</h2>

<div style="float:left; padding: 4px; margin: 0px 3px 10px 0px; width:425px; background-color: #83A2F4;" id="__ss_13018580"> 
<iframe src="http://www.slideshare.net/slideshow/embed_code/13018580" width="425" height="355" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>

<div style="float:left; padding: 4px; margin: 0px 0px 10px 3px; width:473px; background-color: #83A2F4;">
<object id="scPlayer"  width="473" height="355" type="application/x-shockwave-flash" data="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/scplayer.swf" ><param name="movie" value="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/scplayer.swf" /><param name="quality" value="high" /><param name="bgcolor" value="#FFFFFF" /><param name="flashVars" value="thumb=http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/FirstFrame1.jpg&containerwidth=983&containerheight=737&autohide=true&autostart=false&loop=false&showendscreen=true&showsearch=false&showstartscreen=true&tocdoc=left&xmp=sc.xmp&content=http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/How%20to%20Create%20a%20Church%20Community%20Volunteering%20Website%20Using%20ChristianVolunteering.org.mp4&blurover=false" /><param name="allowFullScreen" value="true" /><param name="scale" value="showall" /><param name="allowScriptAccess" value="always" /><param name="base" value="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/" /><iframe type="text/html" frameborder="0" scrolling="no" style="overflow:hidden;" src="http://www.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/embed" height="737" width="983" ></iframe></object>
</div>

<div class="cleardiv"></div>

<hr />
</div>
<div class="cleardiv"></div>

<div class="churchsearch" style="float:left; padding:10px; background:#F7F9FF; margin:5px 10px 10px 25px; height:65px; border:1px solid #ccc;">
<p style="margin:2px; color:#333; font-size:14px; padding-bottom:10px;">Search for Your Church Now</p>
<form id="churchsearch" name="churchsearch"  method="get" action="http://www.christianvolunteering.org<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="portallist" value="true">
<input type="hidden" name="membertypetid" value="5244">
<input type="text" onblur="if(this.value==''){this.value='Postal Code';}" onfocus="if(this.value=='Postal Code'){this.value='';}" value="Postal Code" class="fld watermark" id="postalcode" maxlength="11" name="postalcode">
<input type="submit" value="Search" name="Submit">
</form>
</div>

<div id="emailpastor" style="float:left">
<a href="mailto:paste your pastor's email address here?subject=What%20do%20you%20think%20about%20our%20Church%20using%20ChristianVolunteering.org?&amp;body=I%20recently%20came%20across%20a%20website%20called%20ChristianVolunteering.org.%20%20It%20makes%20it%20easy%20for%20churches%20to%20have%20their%20members%20sign%20up%20for%20volunteer%20opportunities%20on%20the%20church%20website.%20%20I%20thought%20it%20might%20be%20something%20of%20interest%20to%20our%20church.%0A%0ATheir%20model%20is%20that%20the%20church%20leadership%20needs%20to%20be%20the%20one%20to%20make%20the%20call%20of%20whether%20to%20sign%20up.%20%20So%20do%20you%20think%2C%20it%20might%20be%20something%20we%20would%20want%20to%20do%3F%20%20If%20so%2C%20who%20do%20you%20think%20would%20be%20the%20right%20person%20to%20sign%20up%20for%20this%20and%20who%20would%20I%20talk%20to%2C%20to%20get%20a%20link%20from%20our%20church%20website%3F%0A%0AThanks%20for%20your%20consideration!%0A%0A"><img style="padding:15px 15px 10px; float:left;" src="http://www.christianvolunteering.org/imgs/church_bottombar.png" /></a>
</div>

<div class="solr"       style="float:left; padding:10px; background:#F7F9FF; margin:5px 10px 10px 20px; height:65px; border:1px solid #ccc;" id="oppsearch">
<p style="margin:2px; color:#333; font-size:14px; padding-bottom:5px;">Search All Ministry <br /> Opportunities in Your City</p>
<div id="subdomain" style="display:none;">
ChristianVolunteering.org
</div>
      <ul id="searchform" class="search" style="display:none;">
        <input type="text" id="query" name="query" value="Keyword (Service Areas, Skills)"  class="fld watermark tags"/>
      </ul>

      <ul id="search_location" class="search">
        <input type="text_location" id="queryLoc" name="queryLoc" value="Country, Zipcode"  class="fld watermark location"/>
<input type="submit" name="Submit" value="Search" id="search_solr">
      </ul>
</div> 
<div class="cleardiv"></div>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=geometry"></script>
<script language="javascript">
//function callback(){}
var geocoder;
var map;
var marker;
function initialize(callback){
	var variables2 = window.location.hash.split('&');
	var facet_data2;
	if (variables2.length > 1) {
		// Variables present in hash
		for (i = 0; i < variables2.length; i++) {
			var keyValuePair2 = variables2[i].split('=');
			if (keyValuePair2[0] == 'postal_code') {
				facet_data2 = unescape(keyValuePair2[1]);
//console.log(facet_data2);					
					try{
						facet_data2 = facet_data2.substring(0,5);
					}catch(e){}
//console.log(facet_data2);					
				$('div#postal').text(facet_data2);
			}
		}
	}
	//GEOCODER
	geocoder = new google.maps.Geocoder();
	geocoder.geocode({address: $('div#postal').text()},
	function(results_array, status) {
		if(results_array===undefined){ 
		}else if(results_array!=null){ 
			if(results_array[0]===undefined){ 
			}else{
				// Check status and do whatever you want with what you get back
				// in the results_array variable if it is OK.
				var lat = results_array[0].geometry.location.lat();
				var lng = results_array[0].geometry.location.lng();
				$('div#geo_lat').text(lat);
				$('div#geo_long').text(lng);
			}
		}
		try{
			callback();
		}catch(e){
			if (typeof callback == "undefined") {
				this.callback = {func: function() {}};
			}
		}
    });
}
</script>
<%
String search_postal = maxmind_postal;
try{
	if(request.getParameter("postal_code") != null){
		if(request.getParameter("postal_code").length()>0){
			search_postal = request.getParameter("postal_code");
			search_postal = search_postal.substring(0,5);
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}
String queryentered = "";
try{
	if(request.getParameter("query") != null){
		if(request.getParameter("query").length()>0){
			queryentered = request.getParameter("query");
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}
%>
<div id="location_data" style="display:none;">
	<div id="postal"><%=search_postal%></div>
	<div id="lat"><%=maxmind_latitude%></div>
	<div id="long"><%=maxmind_longitude%></div>
	<div id="d_default">40.2336</div>
	<div id="geo_lat"></div>
	<div id="geo_long"></div>
	<div id="temp"><%=queryentered%></div>
</div>


<!-- start footer information -->

<%@ include file="/template/home_footer.inc" %><!-- end footer information -->
