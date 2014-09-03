(function ($) {

AjaxSolr.DistanceSearchWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var self = this;
	//console.log('srchmethod is: ' + $('#srchmethod').val());
	
	var postal= $('#postal').text();
	var lat= $('#lat').text();
	var long= $('#long').text();
	var d= $('#d').val();
	var d_init= $('#d').val();
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var other_params = '';

    var URL_hash_params = [ '' ];

	//: ' + $('#srchmethod').val());
    $('#d').change(function(e, facet) {
	  d= $('#d').val();
      self.requestSent = true;
	  
	  geo_lat= $('#geo_lat').text();
	  geo_long= $('#geo_long').text();
	  
	  var new_URL_string = '';

	  if(d=='Virtual'){
			new_URL_string='/pt/Virtual_Volunteering_(from_home)';
			URL_hash_params.push('position_type="Virtual Volunteering (from home)"');
	  }else{
		if(geo_lat.length>0){
//			new_URL_string='/distance='+d;
			other_params = '&distance='+d;
			URL_hash_params.push('postal_code=' + postal + '');
			URL_hash_params.push('distance=' + d + '');
			URL_hash_params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d + '}');
		}else{
//			new_URL_string='/distance='+d;
			other_params = '&distance='+d;
			URL_hash_params.push('postal_code=' + postal + '');
			URL_hash_params.push('distance=' + d + '');
			URL_hash_params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d + '}');
		}
	  }
//		var new_URL_hash_string = URL_hash_params.join('#');
	var new_URL_hash_string = URL_hash_params.join('&');
	var hash_length=new_URL_hash_string.length;
	if(hash_length>0){
		new_URL_hash_string = '#' + new_URL_hash_string.substring(1,hash_length);
	}
//		new_URL_hash_string='/oppsrch.do?method=processSearch'+other_params+new_URL_hash_string;
		new_URL_hash_string='/s'+new_URL_string+'/results.jsp'+new_URL_hash_string+other_params;
		window.location = new_URL_hash_string;
    });
	//: ' + $('#srchmethod').val());
  }
});

})(jQuery);
