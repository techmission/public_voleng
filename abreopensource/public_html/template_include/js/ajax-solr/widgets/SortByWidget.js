(function ($) {

AjaxSolr.SortByWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var self = this;
    var fq = this.manager.store.values('fq');
	var location_sort = false;
	if (!window.console) console = {log: function() {}};
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));

	for (var i = 0, l = fq.length; i < l; i++) {
		// parse the field & the actual term separately so that we only show the term (and not the field) to the user
		var filter_query=fq[i];
		var filter=''+filter_query;
		var colon_index = filter.indexOf(':');
		var field_name=filter.substr(0,colon_index);
		var latlong='';
		var geo_index = filter.indexOf("{!geofilt pt="); // don't display the distance geo filter here for removal   {!geofilt pt=42.369904,-71.2353 sfield=latlng d=40.2336} (x)
		if( geo_index != -1){
			location_sort=true;
			$('#distance_sort').show();
			begin_index = geo_index+13; // 13 characters in "{!geofilt pt="
			var filter_data= filter.substr(begin_index);
			var end_index=filter_data.indexOf(" sfield="); 
			latlong=filter_data.substr(0,end_index);
			$('#distance_sort').val('geodist(latlng ,' + latlong + ') asc, tm_member_i desc, popularity desc');
		}
    }

	
    $('#sortkey').change(function(e, facet) {
      self.requestSent = true;
		var sortKey = $('#sortkey').val();
	  
	  self.manager.store.addByValue('sort', sortKey);
//console.log('SortByWidget.js doRequest line 12');			
      self.manager.doRequest(0);
		
    });
  }
});

})(jQuery);
