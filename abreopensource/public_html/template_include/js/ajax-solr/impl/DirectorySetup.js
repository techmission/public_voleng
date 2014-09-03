var Manager;

(function ($) {

  $(function () {
	var myhostname = ''+document.location.hostname;	
//console.log('hostname is: ' + myhostname);
	var proxyUrl = '/search/proxy.php';
	if(myhostname==='staging-christianvolunteering.org' || myhostname==='chrisvol.org'){
      proxyUrl= 'http://staging-christianvolunteering.org/search/proxy.php';
	}
//console.log('proxyUrl is: ' + proxyUrl);
    Manager = new AjaxSolr.Manager({
      proxyUrl: proxyUrl
    });
	
	var results_type=$('#results_type').text();

	var fields = [];
	if(results_type=='organization'){
		$('#srchmethod').val('organization');
		fields = [ 'primary_opp_type', 'service_areas', 'great_for', 'trip_length', 'country_tax', 'region', 'province_tax', 'city_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'org_member_type' ];
	}else{
		fields = [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'city_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'org_member_type' ];
	}
/*	
	for (var i = 0, l = fields.length; i < l; i++) {
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}
*/
	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#oppsearch',
	  field: 'keywords',
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'org_member_type' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'province_tax', 'region', 'city_tax'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'content_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));

	Manager.init();
	var sortKey = $('#sortkey').val();
	var findKey = $('#srchmethod').val();
	if(results_type=='organization') findKey=results_type;
	
    Manager.store.addByValue('q', 'NOT faith_i:21998');//chrisvol.org - includes secular + faith segmented, but not faith integrated
	if(findKey=='organization' || findKey=='organization AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.addByValue('fq', 'content_type:organization');
	}else if(findKey=='Short-term Missions / Volunteer Internship' || findKey=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
	}else if(findKey=='Local Volunteering (in person)' || findKey=='Local Volunteering (in person) AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
	}else if(findKey=='Virtual Volunteering (from home)' || findKey=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
	}else if(findKey=='opportunity' || findKey=='opportunity AND NOT faith_i:21998'){
/*
		Manager.store.addByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
*/
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
  'facet.field': [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'org_member_type' ],
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
