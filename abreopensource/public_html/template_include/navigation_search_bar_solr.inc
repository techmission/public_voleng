<div style="display:none;">
	<div id="portal_name"><%=aszPortalName %></div>
</div>
<%
out.println("<!-- maxmind_postal is "+maxmind_postal+" -->");
String search_postal2 = maxmind_postal;
if(! maxmind_countryCode.equalsIgnoreCase("US") )	search_postal2=maxmind_countryName;
try{
	if(request.getParameter("postal_code") != null){
		if(request.getParameter("postal_code").length()>0){
			search_postal2 = request.getParameter("postal_code");
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}
//String search_location2=search_postal2;
%>
<script language="javascript">
function clearLocationNav(){
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
	
	setSearch();
	
}
</script>
<table><tr><td>
    <span id="label">Find:</span>
      <!--span id="search_help">(press ESC to close suggestions)</span-->
<% if(bSkipBanner==false){ %>
</td><td>
<% } %>
        <select class="dropdown srchmethod" name="srchmethod" id="srchmethod" onChange="clearLocationNav()">
					 <option value="opportunity" >Volunteer Opportunities</option> 
					 <option value="organization">Organizations</option> 
					 <option value="Short-term Missions / Volunteer Internship">Short-term Missions</option> 
					 <option value="Virtual Volunteering (from home)">Virtual Volunteering</option>
					 <option value="City Vision">Internships</option>
					 <option value="job">Jobs</option>
					 <option value="resume">Resum&eacute;s</option>
					 <option value="Foundation">Foundations</option>
		</select>
</td><td>
      <ul id="searchform" class="search">
        <input type="text" id="query" name="query" value="Keyword (Service Areas, Skills)"  class="fld watermark tags"/>
      </ul>
</td>
<% if(bSkipBanner==true || aszHostID.equalsIgnoreCase("volengmobile") ){ %>
</tr><tr>
<td></td>

<% } %>
<td>
      <ul id="search_location" class="search">
        <input type="text_location" id="queryLoc" name="queryLoc" value="<%=search_postal2%> (or Country)"  class="fld watermark location"/><!-- Country, Postal Code    <%=search_postal2%>-->
      </ul>
</td><td>
<input type="submit" name="Submit" value="Search" id="search_solr">
</td></tr></table>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=<%=aszGoogleMapsKey%>&sensor=false&libraries=geometry"></script>
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
function setSearch() {
	var searchmethodvalue = '' + $('#srchmethod').val();
	$('#results_type').text(searchmethodvalue);
}
</script>
<%
String search_postal = maxmind_postal;
out.println("<!-- search_postal is "+search_postal+" -->");
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

String aszResultsType = "opportunity";
if(aszPath.equalsIgnoreCase("/voleng2/solr_org_directory.jsp"))	aszResultsType = "organization";
%>
<div id="results_type" style="display:none;"><%=aszResultsType%></div>
<div id="location_data" style="display:none;">
	<div id="postal"><%=search_postal%></div>
	<div id="lat"><%=maxmind_latitude%></div>
	<div id="long"><%=maxmind_longitude%></div>
	<div id="d_default">40.2336</div>
	<div id="geo_lat"></div>
	<div id="geo_long"></div>
	<div id="temp"><%=queryentered%></div>
</div>

