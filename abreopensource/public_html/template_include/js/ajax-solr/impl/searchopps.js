var Manager;

(function ($) {

  $(function () {
    Manager = new AjaxSolr.Manager({
      proxyUrl: 'http://www.christianvolunteering.org/search/proxy.php'
    });
//      solrUrl: 'http://server2.techmission.org:8080/solr/'
//      proxyUrl: 'http://www.christianvolunteering.org/search/proxy.php'
	
	Manager.addWidget(new AjaxSolr.ResultWidget({
	  id: 'result',
	  target: '#docs'
	}));
	
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
	
	var fields = [ 'position_type', 'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country' ];
	for (var i = 0, l = fields.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}

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
	  target: '#selection'
	}));

	Manager.addWidget(new AjaxSolr.SortByWidget({
	  id: 'sortbysearch',
	  target: '#sort'
	}));

/*
Manager.addWidget(new AjaxSolr.TextWidget({
  id: 'text',
  target: '#search',
  field: 'keywords'
}));
*/


	Manager.addWidget(new AjaxSolr.SearchFromParamsWidget({
	  id: 'textparams',
	  target: '#params',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'country_tax', 'province_tax', 'region', 'city_tax', 'postal_code' ]
	}));

	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'province_tax', 'region', 'city_tax'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'content_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));
/*	
	Manager.addWidget(new AjaxSolr.AutocompleteLocationWidget({
	  id: 'text_location',
	  target: '#search_location',
	  field: 'country_tax',//'allText',
	  fields: [ 'country_tax', 'province_tax', 'region' ]
	}));
	Manager.addWidget(new AjaxSolr.AutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',//'allText',
	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search' ]
//	  fields: [ 'service_areas', 'skills', 'great_for', 'benefits_offered', 'frequency', 'trip_length', 'keywords' ]
	}));
*/
/*
Manager.addWidget(new AjaxSolr.CountryCodeWidget({
	  id: 'countries',
	  target: '#countries',
	  field: 'country'
	}));
*/

	var sortKey = $('#sortkey').val();
	var findKey = $('#srchmethod').val();
	Manager.init();
	if(findKey=='organization'){
    	Manager.store.removeByValue('q', 'content_type:opportunity');
	    Manager.store.addByValue('q', 'content_type:'+findKey);
	}else{
    	Manager.store.addByValue('q', 'content_type:opportunity');//'content_type:opportunity'); // 
	}
/*
//  for (var facet in this.manager.response.facet_counts.facet_fields[this.field]) {
    var urlParam = $('#keyword_search').text();
	if(urlParam.length > 0){
		$('#keyword_search').text(''); // clear for next time
		Manager.store.addByValue( 'q', urlParam );
		Manager.store.addByValue( 'fq', urlParam );
	}
//  }
*/	
    Manager.store.addByValue('rows', 100);
    Manager.store.addByValue('sort', sortKey);

var params = {
  facet: true,
  'facet.field': [ 'position_type', 'service_areas', 'skills', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source' ],
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
