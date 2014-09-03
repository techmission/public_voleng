(function ($) {

AjaxSolr.DistanceSearchWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var self = this;
	  if (!window.console) console = {log: function() {}};
//console.log('run DistanceSearchWidget');		
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	var postal= $('#postal').text();
	var lat= $('#lat').text();
	var long= $('#long').text();
	var d= $('#d').val();
	$('#d_init').text(d);
	var d_init= $('#d_init').val();
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var query_store_string= $('#distance_search').text();
	
    $('#d').change(function(e, facet) {
	  query_store_string= $('#distance_search').text();
	  d= $('#d').val();
	  d_init= $('#d_init').text();
      self.requestSent = true;
	  var fq = self.manager.store.values('fq');
	  for (var i = 0, l = fq.length; i < l; i++) {
		  var filter_query=fq[i];
		  var filter=(filter_query);
		  var colon_index = filter.indexOf(":");
		  for (var i = 0, l = fq.length; i < l; i++) {
			  var field_name=filter.substr(0,colon_index);
			  var term=filter.substr(colon_index+1);
			  if(field_name.length>0 && field_name!='postal_code'){
				  self.manager.store.addByValue('fq', field_name+':'+term);
			  }
		  }
	  }
	geo_lat= $('#geo_lat').text();
	geo_long= $('#geo_long').text();
	
	// first need to remove previous geofilt, if existed
	self.manager.store.removeByValue('fq', 'postal_code:'+postal);
	self.manager.store.removeByValue('fq', 'postal_code%3A'+postal);
	//self.manager.store.remove('fq', 'postal_code',1);
	self.manager.store.removeByValue('fq', '{!geofilt pt=' + lat + ',' + long + ' sfield=latlng d=' + d_init + '}');
	self.manager.store.removeByValue('fq', '{!geofilt pt=' + lat + ',' + long + ' sfield=latlng d=' + d + '}');
	self.manager.store.removeByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_init + '}');
	self.manager.store.removeByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d + '}');
	
	// first check if distance is set to virtual
	if(d=='Virtual'){
		if(geo_lat.length>0){
		  self.manager.store.removeByValue('fq', '{!geofilt pt=' + lat + ',' + long + ' sfield=latlng d=' + d_init + '}');
		}
		self.manager.store.removeByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d + '}');
		self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
	}else{
		if(geo_lat.length>0){
		  self.manager.store.removeByValue('fq', '{!geofilt pt=' + lat + ',' + long + ' sfield=latlng d=' + d_init + '}');
		  self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d + '}');
		}else{
		  self.manager.store.addByValue('fq', '{!geofilt pt=' + lat + ',' + long + ' sfield=latlng d=' + d + '}');
		}
		
	}
//console.log('self.manager.store. is '+self.manager.store.string());	  
	  if(query_store_string!=self.manager.store.string()){
//console.log('query_store_string. is '+query_store_string);	
//console.log('self.manager.store. is '+self.manager.store.string());	
		query_store_string=self.manager.store.string();
		$('#d_init').text(d);
		$('#distance_search').text(query_store_string);
//console.log('DistanceSearchWidget.js doRequest line 64 ');//+$('#distance_search').text());
      	self.manager.doRequest(0);
	  }
    });
  }
});

})(jQuery);
