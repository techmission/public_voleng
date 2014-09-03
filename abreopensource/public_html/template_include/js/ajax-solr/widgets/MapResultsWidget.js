(function ($) {

AjaxSolr.MapResultsWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
	  
    $(this.target).empty();
    var offset = parseInt(this.manager.response.responseHeader.params && this.manager.response.responseHeader.params.start || 0);
//console.log('this.manager.store. is '+this.manager.store.string());	
var portal_url = $('#portal_name').html();
if(portal_url.length>0)	portal_url += '/';
	var count=0;
	var title='';
	var org_name='';
	var url_alias='';
	var url_alias_w_portal='';
	var content_type = '';
	var lat=0.0;
	var long=0.0;
	var location = '';
	var locations = [];
	var markers = [];
    for (var i = 0, l = this.manager.response.response.docs.length; i < l; i++) {
	  count++;
	  if(count > 20)	break;
	  location = '';
      var doc = this.manager.response.response.docs[i];
		  title = doc.title;
		  org_name = doc.org_name;
		  lat = doc.latlng_0_coordinate;
		  long = doc.latlng_1_coordinate;
		  url_alias = doc.url_alias;
		  url_alias_w_portal = portal_url + url_alias;
		  content_type = doc.content_type;
/*
console.log('title: '+title);		  
console.log('org_name: '+org_name);		  
console.log('lat: '+lat);		  
console.log('long: '+long);		  
console.log('url_alias: '+doc.url_alias);		  
*/
  var doc_city = '';
  var doc_country = '';
  var doc_geo_tax = '';
  if(doc.geo_tax !== undefined) doc_geo_tax = doc.geo_tax;
  if(doc.city !== undefined) doc_city = doc.city;
  if(doc.country !== undefined) doc_country = doc.country;
  var doc_city_tax = '';
  if(doc.city_tax !== undefined) doc_city_tax = doc.city_tax;
  var username = '';
  if(doc.username !== undefined) username = doc.username;
  var applic_nid = '';
  if(doc.applic_nid !== undefined) applic_nid = doc.applic_nid;
  var src = '';
  if(doc.source !== undefined) src = doc.source;

  if(doc.position_type == 'Virtual Volunteering (from home)'){
	  location = doc.position_type;
  }else{
	  if(doc.street!== undefined && doc.content_type != 'resume'){
		  if(doc.street.length>0){
		  	location +=  doc.street + '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		  }
	  }
//console.log('location: '+location);		  
	  if(doc_geo_tax.length>0){
		  location += doc_geo_tax;
	  }else if(doc_city.length>0){
		  location += doc_city;
	  }else if(doc_city_tax.length>0){
		  location += doc_city_tax;
	  }
	  if((doc_country=='US' || doc_country=='us') &&  location!=doc_city_tax){
		  if(doc.province_tax!== undefined){
			  if(doc.province_tax.length>0){
			  	location +=  ', ' + doc.province_tax;
			  }
		  }else if(doc.country_tax!== undefined){
			  if(doc.country_tax.length>0){
			  	location +=  ', ' + doc.country_tax;
			  }
		  }
	  }else{
		  if(doc.country_tax!== undefined){
			  if(doc.country_tax.length>0){
			  	location +=  ', ' + doc.country_tax;
			  }
		  }
	  }
  }
//console.log('71  location: '+location);		  
		  
	  if(lat>0.0 || long>0.0 || location.length>0){
//console.log('location: '+location);		  
		  // append the title, lat, long to the locations []
		  var location_data = [];
			  location_data.push(lat);
			  location_data.push(long);
			  location_data.push(i+1);
			  location_data.push(title);
			  location_data.push(url_alias_w_portal);
			  location_data.push(org_name);
			  location_data.push(location);
			  location_data.push(content_type);
			  location_data.push(username);
			  location_data.push(applic_nid);
			  location_data.push(src);
		  locations.push(location_data);
	  }
    }
	
	var orig_lat = 0.0; var orig_long = 0.0;
	if( locations[0] !== undefined )	{
		if( locations[0][0] !== undefined )	orig_lat = locations[0][0];
		if( locations[0][1] !== undefined )	orig_long = locations[0][1];
	}
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 10,
      center: new google.maps.LatLng(orig_lat, orig_long),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;
//console.log('locations: '+locations);		  


    var perPage = parseInt(this.manager.response.responseHeader.params && this.manager.response.responseHeader.params.rows || 20);
    var offset = parseInt(this.manager.response.responseHeader.params && this.manager.response.responseHeader.params.start || 0);
    var total = parseInt(this.manager.response.response.numFound);

    // Normalize the offset to a multiple of perPage.
    offset = offset - offset % perPage;

    this.currentPage = Math.ceil((offset + 1) / perPage);
    this.totalPages = Math.ceil(total / perPage);


    for (i = 0; i < locations.length; i++) { 
	  var geoLatLng = null;
	  
	  // geocode for locations without lat/long
	  if(!(locations[i][0]<0.0 || locations[i][0]>0.0 || locations[i][1]<0.0 || locations[i][1]>0.0)){
		  if(locations[i][6].length>0){
/*			  
			  // get latitude & longitude from other location data - geocode
			  geocoder.geocode( { 'address': location}, function(results, status) {
			  if (status == google.maps.GeocoderStatus.OK) {
				geoLatLng = results[0].geometry.location;
			  } else {
console.log("Geocode was not successful for the following reason: " + status);
			  }
			});
*/			  
		  }
	  }
	
	 var content_heading = '';
	 if($('#contenttype_heading1')!=null){
	 	content_heading=$('#contenttype_heading1').text();
	 }
      if(locations[i][0]<0.0 || locations[i][0]>0.0 || locations[i][1]<0.0 || locations[i][1]>0.0 || geoLatLng != null){
//console.log('if(locations[i][0]>0.0');		  
		var tmp_i=offset+locations[i][2];
		if(locations[i][0]==90 && locations[i][1]==0){}else{
		  if(locations[i][0]<0.0 || locations[i][0]>0.0 || locations[i][1]<0.0 || locations[i][1]>0.0){
			  marker = new google.maps.Marker({
				position: new google.maps.LatLng(locations[i][0], locations[i][1]),
				map: map,
				icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+tmp_i+'|FF776B|000000'
			  });//locations[i][2]
		  }else if(geoLatLng != null){
			  marker = new google.maps.Marker({
				position: new google.maps.LatLng(geoLatLng),
				map: map,
				icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+tmp_i+'|FF776B|000000'
			  });//locations[i][2]
		  }
		  markers.push(marker);
//console.log('line 62: ');		  
		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				var infowindow_content='';
				var tmp_i=offset+locations[i][2];//offset+i+1;//
				var tmp_title=locations[i][3];
				var tmp_url_alias=locations[i][4];
				var tmp_org_name=locations[i][5];
				var tmp_location=locations[i][6];
				var tmp_content_type=locations[i][7];
				var tmp_username=locations[i][8];
				var tmp_applic_nid=locations[i][9];
				var tmp_src=locations[i][10];
/*
console.log('tmp_i: '+tmp_i);		  
console.log('tmp_title: '+tmp_title);		  
console.log('tmp_url_alias: '+tmp_url_alias);		  
console.log('tmp_org_name: '+tmp_org_name);		  
console.log('tmp_location: '+tmp_location);		  
console.log('tmp_username: '+tmp_username);		  
*/				
				if(tmp_content_type == 'organization'){
					infowindow_content=
						tmp_i + '. <a class="org_link" href="/' + tmp_url_alias + '.jsp">' + tmp_title + '</a>' + 
						'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
				}else if(content_heading == 'Internship Candidates' && tmp_applic_nid!=''){
					if( tmp_applic_nid!==undefined){
						if( tmp_applic_nid!=''){
							if(tmp_src == 'SalesForce'){
								infowindow_content=
									tmp_i + '. <a class="org_link" href="/cityvision/email.do?method=loadOneApplication&nid=' + tmp_applic_nid + '&src=sf">' + tmp_title + '</a>' + 
									'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
							}else{
								infowindow_content=
									tmp_i + '. <a class="org_link" href="/cityvision/email.do?method=loadOneApplication&nid=' + tmp_applic_nid + '">' + tmp_title + '</a>' + 
									'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
							}
						}
					}else{
						infowindow_content=
							tmp_i + '. <a class="org_link" href="#">' + tmp_title + '</a>' + 
							'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
					}
				}else if(tmp_content_type == 'resume'){
					if( tmp_username!==undefined){
						infowindow_content=
							tmp_i + '. <a class="org_link" href="/profiles/' + tmp_username + '">' + tmp_title + '</a>' + 
							'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
					}else{
						infowindow_content=
							tmp_i + '. <a class="org_link" href="#">' + tmp_title + '</a>' + 
							'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_location;
					}
				}else{
					infowindow_content=
						tmp_i + '. <a class="org_link" href="/' + tmp_url_alias + '.jsp">' + tmp_title + '</a>' +
						'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + tmp_org_name + 
						'<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + 
						tmp_location;
				}
			  infowindow.setContent(infowindow_content);
			  infowindow.open(map, marker);
			}
		  })(marker, i));
		}
	  }
//console.log('this.manager.store. is '+this.manager.store.string());	
    }
	
	var autocenter = function() {
		google.maps.event.trigger(map, 'resize');
		  //console.log('autocenter');
		var notPermissibleLatLng = new google.maps.LatLng(90,0);
		//  Create a new viewpoint bound
		var bounds = new google.maps.LatLngBounds();
		//  Go through each...
		$.each(markers, function (index, marker) {
			  var markerPosition = marker.getPosition();
			  if(! bounds.contains(markerPosition) ){
				  if(! markerPosition.equals(notPermissibleLatLng)){
					  bounds.extend(markerPosition);
				  }
			  }
		});
		map.fitBounds(bounds);
	}
	

	$("#map_link").click(function() {
	  $("#map_container").show(function() {
		  google.maps.event.trigger(map, 'resize');
		  var notPermissibleLatLng = new google.maps.LatLng(90,0);
		  //  Create a new viewpoint bound
		  var bounds = new google.maps.LatLngBounds();
		  //  Go through each...
		  $.each(markers, function (index, marker) {
			  var markerPosition = marker.getPosition();
			  if(! bounds.contains(markerPosition) ){
				  if(! markerPosition.equals(notPermissibleLatLng)){
//					  console.log('markerPosition: '+markerPosition);
					  bounds.extend(markerPosition);
				  }
			  }
		  });
		  //  Fit these bounds to the map
		  map.fitBounds(bounds);
	  });
	});
  }
});


})(jQuery);
