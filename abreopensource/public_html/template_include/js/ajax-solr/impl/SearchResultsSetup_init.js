var Manager;

(function ($) {

  $(function () {
    Manager = new AjaxSolr.Manager({
      proxyUrl: '/search/proxy.php'
    });

	if (typeof console == "undefined") {
		this.console = {log: function() {}};
	}
	
	var site = $('div#subdomain').text();
	var hashURL_impl =  '' + window.location.hash;
	var hash_org_impl = hashURL_impl.indexOf("content_type:organization");
	var hash_opp_impl = hashURL_impl.indexOf("content_type:opportunity");
	var hash_stm_impl = hashURL_impl.indexOf("Short-term Missions / Volunteer Internship");
	if(site == 'volengchurch'){
		if(hash_org_impl != -1){
			$('#srchmethod').val('organization');
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Organizations');
		}else{
			if(hash_stm_impl != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
			}else{
				$('#srchmethod').val('opportunity');
			}
			$('#contenttype_title').text('Opportunities');
			$('#contenttype_heading').text('Organization Opportunities');
		}
	}else{
		if(hash_org_impl != -1){
			$('#srchmethod').val('organization AND NOT faith_i:21998');
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Organizations');
		}else{
			if(hash_stm_impl != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship AND NOT faith_i:21998');
			}else{
				$('#srchmethod').val('opportunity AND NOT faith_i:21998');
			}
			$('#contenttype_title').text('Opportunities');
			$('#contenttype_heading').text('Organization Opportunities');
		}
	}
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

	
	
	Manager.setStore(new AjaxSolr.ParameterHashStore()); 
	Manager.store.exposed = ['fq','start']; 
//Manager.store.exposed = ['fq','q','start']; 


	Manager.init();
	var sortKey = $('#sortkey').val();
	var findKey = $('#srchmethod').val();
	if(findKey=='organization' || findKey=='organization AND NOT faith_i:21998'){
    	Manager.store.removeByValue('q', 'content_type:opportunity');
    	Manager.store.removeByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');
	    Manager.store.addByValue('q', '(content_type:organization AND NOT faith_i:21998)');
	}else{
    	Manager.store.addByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');//chrisvol.org - includes secular + faith segmented, but not faith integrated
	}
	
	Manager.addWidget(new AjaxSolr.PagerWidget({
	  id: 'pager',
	  target: '#pager',
	  prevLabel: '&lt;',
	  nextLabel: '&gt;',
	  innerWindow: 1,
	  renderHeader: function (perPage, offset, total) {
		$('#pager-header').html($('<span/>').text('displaying ' + Math.min(total, offset + 1) + ' to ' + Math.min(total, offset + perPage) + ' of ' + total));
	  }
	}));
	
	var fields = [ 'position_type', 'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax' ];
	for (var i = 0, l = fields.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}

	var field_tabs = [ 'content_type', 'position_type' ];
	for (var i = 0, l = field_tabs.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.TabsWidget({
		id: field_tabs[i],
		target: '#' + field_tabs[i],
		field: field_tabs[i]
	  }));
	}

	Manager.store.addByValue('rows', 100);
    Manager.store.addByValue('sort', sortKey);

	var params = {
	  facet: true,
	  'facet.field': [ 'position_type', 'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'city_tax' ],
	  'facet.limit': 100,
	  'facet.mincount': 1,
	  'f.keyword.facet.limit': 50,
	  'f.country.facet.limit': -1,
	  'json.nl': 'map'
	};
	for (var name in params) {
	  Manager.store.addByValue(name, params[name]);
	}

console.log('SearchResultsSetup.js doRequest line 143');
    Manager.doRequest();

  });
  
  
  
var loadWidgets = function(response){
	autocompleteSearch();
	//paramsSearch();
}
var paramsSearch = function (response){  
	console.log('paramsSearch function');
	Manager.addWidget(new AjaxSolr.SearchFromParamsWidget({
	  id: 'textparams',
	  target: '#params',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'country_tax', 'province_tax', 'region', 'city_tax', 'postal_code' ]
	}));
	autocompleteSearch();
}
var autocompleteSearch = function (response){
//	console.log('autocompleteSearch function');
	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'city_tax', 'province_tax', 'region'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'content_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));
	showResults();
}
var showResults = function (response){
	  if (!window.console) console = {log: function() {}};
	console.log('showResults function');
	Manager.addWidget(new AjaxSolr.ResultWidget({
	  id: 'result',
	  target: '#docs'
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
