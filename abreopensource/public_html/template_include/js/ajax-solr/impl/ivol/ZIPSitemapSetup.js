var Manager;

(function ($) {

  $(function () {
    Manager = new AjaxSolr.Manager({
      proxyUrl: '/search/proxy.php'
//      proxyUrl: 'http://www.christianvolunteering.org/search/proxy.php'
    });
	
	
	var fields = [ 'position_type', 'postal_code' ,'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'org_member_type' ];
	for (var i = 0, l = fields.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}

	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'org_member_type' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'province_tax', 'region', 'city_tax', 'postal_code'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'content_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));

	var sortKey = $('#sortkey').val();
	var findKey = $('#srchmethod').val();
	Manager.init();
	if(findKey=='organization'){
    	Manager.store.removeByValue('q', 'content_type:opportunity');
    	Manager.store.removeByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');
	    Manager.store.addByValue('q', 'content_type:'+findKey);
	}else{
    	Manager.store.addByValue('q', '(content_type:opportunity AND NOT faith_i:21998)');//chrisvol.org - includes secular + faith segmented, but not faith integrated
	}

//  for (var facet in this.manager.response.facet_counts.facet_fields[this.field]) {
    var urlParam = $('#keyword_search').text();
	if(urlParam.length > 0){
		$('#keyword_search').text(''); // clear for next time
		Manager.store.addByValue( 'q', urlParam );
		Manager.store.addByValue( 'fq', urlParam );
	}
//  }
	
    Manager.store.addByValue('rows', 100);
    Manager.store.addByValue('sort', sortKey);

var params = {
  facet: true,
  'facet.field': [ 'position_type', 'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'postal_code', 'org_member_type' ],
  'facet.limit': 100,
  'facet.mincount': 1,
  'f.keyword.facet.limit': 50,
  'f.country.facet.limit': -1,
  'json.nl': 'map'
};
for (var name in params) {
  Manager.store.addByValue(name, params[name]);
}



    Manager.doRequest();



  });
$.fn.showIf = function (condition) {
  if (condition) {
    return this.show();
  }
  else {
    return this.hide();
  }
}

})(jQuery);
