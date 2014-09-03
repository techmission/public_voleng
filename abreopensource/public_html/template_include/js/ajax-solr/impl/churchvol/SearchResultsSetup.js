var Manager;

(function ($) {

  $(function () {
    Manager = new AjaxSolr.Manager({
      proxyUrl: '/search/proxy.php'
    });

	if (typeof console == "undefined") {
		this.console = {log: function() {}};
	}else if (typeof console === undefined) {
		this.console = {log: function() {}};
	}
	var findKey = $('#srchmethod').val();
	
	var site = $('div#subdomain').text();
	var hashURL_impl =  '' + window.location.hash;


		if (hashURL_impl.length > 0) {
			// Variables present in hash
			var variables = hashURL_impl.split('=');
			for (i = 0; i < variables.length; i++) {
				params_set=true;
				var parse_data = hashURL_impl[i];
				var hash_index = '';
				if(parse_data === undefined){
				}else{
					if(i==0){
						hash_index = parse_data.indexOf("#");
						if(hash_index != -1){
							parse_data = parse_data.substring(1,parse_data.length);
						}
					}
//console.log('parse_data #' +i+ ' ' +parse_data);				
					var keyValuePair = parse_data.split('=');
					if (keyValuePair[0] == 'fq') {
						var geo_filter = parse_data.split(':');
						facet_data = unescape(geo_filter[1]);
						if(facet_data.indexOf('geofilt') != -1){
							$('#geofilt_facet').text(facet_data);
						}else{
							search_data += '&q=' + facet_data;
						}
//						self.manager.store.addByValue('fq', facet_data);
					}else if (keyValuePair[0] == 'postal_code') {
						facet_data = unescape(keyValuePair[1]);
						search_geo_loc=true;
						try{
							facet_data = facet_data.substring(0,5);
						}catch(e){}
						$('#postal').text(facet_data);
//console.log('facet_data #' +i+ ' ' +facet_data);				
						postal_data = facet_data;
					//search_data += '&location_data=' + facet_data;
					}
				}
			}
		}

	var content_type_div = $('#content_type_search').text();
	var hash_org_impl = hashURL_impl.indexOf("content_type:organization");
	var hash_job_impl = hashURL_impl.indexOf("content_type:job");
	if(content_type_div.indexOf("content_type:organization") != -1){
		hash_org_impl = 0;
	}
	if(content_type_div.indexOf("content_type:job") != -1){
		hash_job_impl = 0;
	}
	var hash_opp_impl = hashURL_impl.indexOf("content_type:opportunity");
	var hash_stm_impl = hashURL_impl.indexOf("Short-term Missions / Volunteer Internship");
	var hash_local_impl = hashURL_impl.indexOf("Local Volunteering (in person)");
	var hash_virtual_impl = hashURL_impl.indexOf("Virtual Volunteering (from home)");
	if(site == 'volengchurch'){
		if(hash_org_impl != -1){
			$('#srchmethod').val('organization');
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Organizations');
		}else if(hash_job_impl != -1){
			$('#srchmethod').val('job');
			$('#contenttype_title').text('Job');
			$('#contenttype_heading').text('Christian Job Postings');
		}else{
			if(hash_stm_impl != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
			}else{
				$('#srchmethod').val('opportunity');
			}
			$('#contenttype_title').text('Opportunities');
			$('#contenttype_heading').text('Opportunities');
		}
	}else{
		if(hash_org_impl != -1){
			$('#srchmethod').val('organization');
//			$('#srchmethod').val('organization AND NOT faith_i:21998');
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Volunteer Organizations');
		}else if(hash_job_impl != -1){
			$('#srchmethod').val('job');
//			$('#srchmethod').val('organization AND NOT faith_i:21998');
			$('#contenttype_title').text('Job');
			$('#contenttype_heading').text('Christian Job Postings');
		}else{
			if(hash_stm_impl != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
//				$('#srchmethod').val('Short-term Missions / Volunteer Internship AND NOT faith_i:21998');
			}else{
				$('#srchmethod').val('opportunity');
//				$('#srchmethod').val('opportunity AND NOT faith_i:21998');
			}
			$('#contenttype_title').text('Opportunities');
			$('#contenttype_heading').text('Volunteer Opportunities');
		}
	}

	
	Manager.setStore(new AjaxSolr.ParameterHashStore()); 
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));
	Manager.store.exposed = ['fq','start']; 
//Manager.store.exposed = ['fq','q','start']; 


	Manager.init();
	var sortKey = $('#sortkey').val();
	findKey = $('#srchmethod').val();
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));
//console.log(('self.manager.store.values is: ' + Manager.store.values('fq'));
//console.log(('findkey is '+findKey);
//console.log(('sortKey is '+sortKey);
//console.log(('$(#srchmethod).val() is '+$('#srchmethod').val());








//	Manager.store.addByValue('q', 'NOT faith_i:21998');//chrisvol.org - includes secular + faith segmented, but not faith integrated\
	Manager.store.addByValue('q', '*:*');//chrisvol.org - includes secular + faith segmented, but not faith integrated\
	




	
/*
	if(findKey=='organization' || findKey=='organization AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
//	    Manager.store.addByValue('fq', 'content_type:organization');
	    Manager.store.removeByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');
	    Manager.store.addByValue('q', '(content_type:organization AND NOT faith_i:21998)');
	}else{
    	Manager.store.removeByValue('fq', 'content_type:organization');
	    Manager.store.removeByValue('q', '(content_type:organization AND NOT faith_i:21998)');
	    Manager.store.addByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');
	}
	
*/
// *
	if(findKey=='organization' || findKey=='organization AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:job');
    	Manager.store.addByValue('fq', 'content_type:organization');
		if(hash_local_impl != -1){
	    	Manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
		}else if(hash_virtual_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		}else if(hash_stm_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		}
	}else if(findKey=='job' || findKey=='job AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
    	Manager.store.addByValue('fq', 'content_type:job');
		if(hash_local_impl != -1){
	    	Manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
		}else if(hash_virtual_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		}else if(hash_stm_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		}
	}else if(findKey=='Short-term Missions / Volunteer Internship' || findKey=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
    	Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		if(hash_org_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
    		Manager.store.addByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}
	}else if(findKey=='Local Volunteering (in person)' || findKey=='Local Volunteering (in person) AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
		if(hash_org_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
    		Manager.store.addByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}
	}else if(findKey=='Virtual Volunteering (from home)' || findKey=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		if(hash_org_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
    		Manager.store.addByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}
	}else if(findKey=='opportunity' || findKey=='opportunity AND NOT faith_i:21998'){
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		Manager.store.addByValue('fq', 'content_type:opportunity');
/*
		Manager.store.addByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
*/
	}else{
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		Manager.store.addByValue('fq', 'content_type:opportunity');
	}
	// not sure whether to clear out the content_type or not; basically, if no content type is set, i'd like to do opportunities, but otherwise, i want org to be able to be set
	
// * /
	
//console.log(('self.manager.store.values is: ' + Manager.store.values('fq'));
	loadWidgets();
	Manager.addWidget(new AjaxSolr.DistanceSearchWidget({
	  id: 'distancesearch',
	  target: '#distance',
	  lat: '#lat',
	  long: '#long',
	  d: '#d',
	  field: 'd'
	}));

	Manager.addWidget(new AjaxSolr.CurrentSearchWidget({
	  id: 'currentsearch',
	  target_alt: '#removeall',
	  target: '#selection'
	}));

	Manager.addWidget(new AjaxSolr.SortByWidget({
	  id: 'sortbysearch',
	  target: '#sort'
	}));

	
	Manager.addWidget(new AjaxSolr.PagerWidget({
	  id: 'pager',
	  target: '#pager',
	  prevLabel: '&lt;',
	  nextLabel: '&gt;',
	  innerWindow: 1,
	  renderHeader: function (perPage, offset, total) {
		$('#pager-header').html($('<span/>').text('  displaying ' + Math.min(total, offset + 1) + ' to ' + Math.min(total, offset + perPage) + ' of ' + total));
	  }
	}));
	
	var fields = [];
	if(findKey=='organization'){
		fields = [ 'primary_opp_type', 'position_type', 'service_areas', 'great_for', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type', 'org_member_type' ];
	}else if(findKey=='job'){
		fields = [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type', 'org_member_type' ];
	}else{
		fields = [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type', 'org_member_type' ];
	}
	for (var i = 0, l = fields.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}

	var field_tabs = [ 'position_type', 'content_type' ];
	for (var i = 0, l = field_tabs.length; i < l; i++) {
//console.log('tabswidget '+field_tabs[i]);
	  Manager.addWidget(new AjaxSolr.TabsWidget({
		id: field_tabs[i],
		target: '#' + field_tabs[i],
		field: field_tabs[i]
	  }));
	}

	Manager.store.addByValue('rows', 20);
    Manager.store.addByValue('sort', sortKey);

	var params = {
	  facet: true,
	  'facet.field': [ 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'city_tax', 'position_type', 'content_type', 'city_tax', 'primary_opp_type', 'org_member_type' ],
	  'facet.limit': 100,
	  'facet.mincount': 1,
	  'f.keyword.facet.limit': 50,
	  'f.country.facet.limit': -1,
	  'json.nl': 'map'
	};
	for (var name in params) {
	  Manager.store.addByValue(name, params[name]);
	}

//console.log('SearchResultsSetup.js doRequest line 181');
//console.log(('self.manager.store.values is: ' + Manager.store.values('fq'));
    Manager.doRequest();

  });
  
  
  
var loadWidgets = function(response){
	autocompleteSearch();
	//paramsSearch();
}
var paramsSearch = function (response){  
	//console.log(('paramsSearch function');
	Manager.addWidget(new AjaxSolr.SearchFromParamsWidget({
	  id: 'textparams',
	  target: '#params',
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'country_tax', 'province_tax', 'region', 'city_tax', 'postal_code', 'city_tax', 'primary_opp_type', 'org_member_type' ]
	}));
	autocompleteSearch();
}
var autocompleteSearch = function (response){
//	console.log('autocompleteSearch function');
	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'org_member_type' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'city_tax', 'province_tax', 'region'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'position_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));
	showResults();
}
var showResults = function (response){
	  if (!window.console) console = {log: function() {}};
	//console.log(('showResults function');
//console.log(('self.manager.store.values is: ' + Manager.store.values('fq'));
	Manager.addWidget(new AjaxSolr.ResultWidget({
	  id: 'result',
	  target: '#docs'
	}));	

	Manager.addWidget(new AjaxSolr.MapResultsWidget({
	  id: 'countries',
	  target: '#countries',
	  field: 'country'
	}));
}




$.fn.showIf = function (condition) {
  if (condition) {
    return this.show();
  }
  else {
    return this.hide();
  }
}

})(jQuery);
