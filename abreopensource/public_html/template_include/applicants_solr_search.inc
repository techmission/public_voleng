<%
String search_postal = maxmind_postal;
if(! maxmind_countryCode.equalsIgnoreCase("US") )	search_postal=maxmind_countryName;

try{
	if(request.getParameter("postal_code") != null){
		if(request.getParameter("postal_code").length()>0){
			search_postal = request.getParameter("postal_code");
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}
String search_location=search_postal;
%>
<script language="javascript">
$('#queryLoc').click(function(){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
});
$('#position_type_virt').click(function(){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
});
$('#position_type_stm').click(function(){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
});

function clicked_virtual(){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
}
function clicked_stm(){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
}

function clearLocation(){
	var v = $('#srchmethod').val();
//	console.log('value is '+v);
	if(
		v == 'Short-term Missions / Volunteer Internship' || v == 'Short-term Missions / Volunteer Internship AND NOT faith_i:21998' ||
		v == 'Virtual Volunteering (from home)' || v == 'Virtual Volunteering (from home) AND NOT faith_i:21998' ||
		v == 'Foundation'  ||
		v == 'City Vision'  ||
		v == 'City Vision Internships'  
	){
		$('#location').val('');
		$('#postal').val('');
		$('#queryLoc').val('');
	}
}
</script>
<%
String aszFindTitle= "Find&nbsp;";
String aszAdditionalClassesTmp="";
String aszAdditionalLink="";
if(aszLandingPage.equals("disasterrelief")){
	aszFindTitle += "Disaster Relief&nbsp;";
	aszAdditionalClassesTmp=" tags-longer";
	aszAdditionalLink = "<a href=\""+aszPortal+"/disasterreliefvolunteer.jsp\">Post Disaster Relief Needs or Volunteer Opportunities</a>";
}
%>
<div id="home_solr_search">
<div id="search_heading"></div>
	<div id="search_input">
		<div>
			<div style="float:left"><h2><%= aszFindTitle %></h2></div>
			<div style="float:left">
				<select class="dropdown home" name="srchmethod" id="srchmethod" onChange="clearLocation()">
					 <option value="opportunity" >Volunteer Opportunities</option> 
					 <option value="organization">Organizations</option> 
					 <option value="Short-term Missions / Volunteer Internship">Short Term Missions / Internships</option> 
					 <option value="Virtual Volunteering (from home)">Virtual Volunteering</option>
					 <option value="City Vision">Internships</option>
					 <option value="job">Jobs</option>
					 <option value="resume">Resum&eacute;s</option>
					 <option value="Foundation">Foundations</option>
				</select>
			</div>
		</div>
		<br clear="all"/>
		<ul id="search_keyword" class="search">
        	<input type="text" id="query" name="query" value="Keywords (Service Areas, Skills)"  class="fld watermark tags<%=aszAdditionalClassesTmp%>"/><br>
			<span class="search_label keywords_label">i.e. children, food ministry, homelessness, accounting, computers, teaching, etc</span>
		</ul>
		<ul id="search_location" class="search" style="float:left;">
        	<input type="text_location" id="queryLoc" name="queryLoc" value="<%=search_location%>"  class="fld watermark location"/><br>
			<span class="search_label location_label">country, metro area, postal code</span>
		</ul>
		
		<br clear="all"/>
		<br clear="all"/>

		<div id="submit_solr_search">
			<input type="submit" name="Submit" value="Search" id="search_solr">
		</div>
		

		<div id="search_checkboxes">
			<div id="browse_links">
				<a href="<%=aszPortal%>/directory.jsp">Browse Applicants</a>
			</div>
			<%= aszAdditionalLink %>
		</div>
	</div>

	<div id="location_data" style="display:none;">
		<div id="location"><%=search_location%></div>
		<div id="postal"><%=search_postal%></div>
		<div id="lat"><%=maxmind_latitude%></div>
		<div id="long"><%=maxmind_longitude%></div>
		<div id="d_default">40.2336</div>
		<div id="geo_lat"></div>
		<div id="geo_long"></div>
	</div>

</div>
<div style="clear:both;"></div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=<%=aszGoogleMapsKey%>&sensor=false&libraries=geometry"></script>
<script language="javascript">
//function callback(){}
var geocoder;
var map;
var marker;
function initialize(callback){


	var postalcode = $('div#postal').text();
//console.log('div#postal text is: '+ postalcode);
	//GEOCODER
	geocoder = new google.maps.Geocoder();
	geocoder.geocode({address: postalcode},
	function(results_array, status) {
		if(results_array!=null){ 
			// Check status and do whatever you want with what you get back
			// in the results_array variable if it is OK.
			var location_object = results_array[0];
			if(location_object!=null){ 			
				var lat = location_object.geometry.location.lat();
				var lng = location_object.geometry.location.lng();
				$('div#geo_lat').text(lat);
				$('div#geo_long').text(lng);
//console.log('div#geo_lat text is: '+$('div#geo_lat').text() +';    '+lat);
//console.log('div#geo_long text is: '+$('div#geo_long').text() +';    '+lng);
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
