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
	
	var findKey = $('#srchmethod').val();
  
	var landingpagequery = $('#fq_search').text();
	//console.log('landingpagequery is: ' + landingpagequery);
	
	// following is what differs from other pages; allows handling of multiple targets of the same taxonomy
	var field_div_variant = '';
	var fields = [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type', 'org_member_type' ];
	//console.log('landingpagequery is: ' + landingpagequery);
	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'org_member_type' ],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'city_tax', 'province_tax', 'region'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'content_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));

	//console.log('landingpagequery is: ' + landingpagequery);
	//console.log('srchmethod is: ' + $('#srchmethod').val());
	var sortKey = $('#sortkey').val();
	Manager.init();
	var sortKey = $('#sortkey').val();
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));
    Manager.store.addByValue('q', 'NOT faith_i:21998');//chrisvol.org - includes secular + faith segmented, but not faith integrated
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
// * /

//  for (var facet in this.manager.response.facet_counts.facet_fields[this.field]) {
    var urlParam = $('#keyword_search').text();
	if(urlParam.length > 0){
		$('#keyword_search').text(''); // clear for next time
//console.log( 'q '+ urlParam );
		if(urlParam=='q=position_type:"Short-term Missions / Volunteer Internship"'){
			$('#srchmethod').val('Short-term Missions / Volunteer Internship');
		}else if(urlParam=='q=city_tax:"Chicago, IL"'){
			$('#queryLoc').val('Chicago, IL');
		}
		Manager.store.addByValue( 'q', urlParam );
		Manager.store.addByValue( 'fq', urlParam );
	}
	
	if(landingpagequery.length > 0){
		$('#keyword_search').text(''); // clear for next time
		if(landingpagequery=='position_type:"Short-term Missions / Volunteer Internship"'){
			$('#srchmethod').val('Short-term Missions / Volunteer Internship');
		}else if(landingpagequery=='city_tax:"Chicago, IL"'){
			$('#queryLoc').val('Chicago, IL');
		}
		Manager.store.addByValue( 'q', landingpagequery );
		Manager.store.addByValue( 'fq', landingpagequery );
	}
	
    Manager.store.addByValue('rows', 100);
    Manager.store.addByValue('sort', sortKey);

var params = {
  facet: true,
  'facet.field': [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type', 'org_member_type' ],
  'facet.limit': 100,
  'facet.mincount': 1,
  'f.keyword.facet.limit': 50,
  'f.country.facet.limit': -1,
  'json.nl': 'map'
};
for (var name in params) {
  Manager.store.addByValue(name, params[name]);
}


	//console.log('srchmethod is: ' + $('#srchmethod').val());

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
