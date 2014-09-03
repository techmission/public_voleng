var Manager;

(function ($) {
  $(function () {
	var myhostname = ''+document.location.hostname;	
	var proxyUrl = '/search/proxy.php';
	if(myhostname==='staging-christianvolunteering.org' || myhostname==='chrisvol.org'){
      proxyUrl= 'http://staging-christianvolunteering.org/search/proxy.php';
	}
    Manager = new AjaxSolr.Manager({
      proxyUrl: proxyUrl
    });

	if (typeof console == "undefined") {
		this.console = {log: function() {}};
	}else if (typeof console === undefined) {
		this.console = {log: function() {}};
	}
	var findKey = $('#srchmethod').val();
//console.log('findKey is: ' + findKey);
	
	var site = $('div#subdomain').text();
	var hashURL_impl =  '' + window.location.hash;

//console.log('hashURL_impl is: ' + hashURL_impl);
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
//console.log('has geotext');						
						$('#geofilt_facet').text(facet_data);
					}else{
						search_data += '&q=' + facet_data;
					}
//					self.manager.store.addByValue('fq', facet_data);
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
	var hash_cv_impl = hashURL_impl.indexOf('City Vision');
	var hash_cvintern = hashURL_impl.indexOf('fq=cvintern_applicant:"City Vision Intern Applicant"');
	var hash_resume_impl = hashURL_impl.indexOf("content_type:resume");
	if(content_type_div.indexOf('cvintern_applicant:"City Vision Intern Applicant"') != -1){
		hash_cv_impl = -1;
		hash_cvintern = 0;
	}else if(content_type_div.indexOf("content_type:resume") != -1){
		hash_resume_impl = 0;
	}
	
	$('#srchmethod').val('resume');
	$('#contenttype_title').text('Resume');
	$('#contenttype_heading').text('City Vision Internship Applicants');

	Manager.setStore(new AjaxSolr.ParameterHashStore()); 
	Manager.store.exposed = ['fq','start']; 

	Manager.init();
//console.log('net_assets is: ' + $('#net_assets').html());
	var sortKey = $('#sortkey').val();
	findKey = $('#srchmethod').val();
//console.log('findKey is: ' + findKey);
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));
	if($('#geofilt')!=null){
		if($('#geofilt').text().length > 0){
			if(	$('#geofilt').text().indexOf('{!geofilt')==0){
				Manager.store.addByValue('fq',  $('#geofilt').text());
			}
		}
	}
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));

//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
//console.log('findkey is '+findKey);
//console.log(('sortKey is '+sortKey);
//console.log(('$(#srchmethod).val() is '+$('#srchmethod').val());

	Manager.store.addByValue('q', '*:*');//chrisvol.org - includes secular + faith segmented, but not faith integrated\
   	Manager.store.removeByValue('fq', 'content_type:opportunity');
   	Manager.store.removeByValue('fq', 'status:1');
   	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
   	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
   	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
   	Manager.store.removeByValue('fq', 'content_type:job');
	Manager.store.removeByValue('fq', '-org_member_type:Foundation');
	Manager.store.removeByValue('fq', '-org_member_type:"Foundation"');
   	Manager.store.removeByValue('fq', 'content_type:organization');
	Manager.store.removeByValue('fq', 'org_member_type:Foundation'); // nmioght need ""????????????

   	Manager.store.addByValue('fq', 'content_type:resume');
   	Manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"');
	
	// if Manager.store.values includes placed, don't add it
	var temp_store = "" + Manager.store.values('fq');
console.log('temp_store Manager.store.values is '+temp_store);	
	if(temp_store.indexOf('intern_length') > -1){
	}else{
   	Manager.store.addByValue('fq', 'intern_length:"One Year or Multi-year Internship"');
	}
	if(temp_store.indexOf('cvintern_placed') > -1){
	}else{
	   	Manager.store.addByValue('fq', 'cvintern_placed:0');
	}
	if(temp_store.indexOf('screened') > -1){
	}else{
	   	Manager.store.addByValue('fq', 'screened:[1 TO 2]');
	}
	
	
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	// not sure whether to clear out the content_type or not; basically, if no content type is set, i'd like to do opportunities, but otherwise, i want org to be able to be set
	
	
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
//console.log('317  net_assets is: ' + $('#net_assets').html());
	loadWidgets();
//console.log('319  net_assets is: ' + $('#net_assets').html());
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	
	
	Manager.addWidget(new AjaxSolr.DistanceSearchWidget({
	  id: 'distancesearch',
	  target: '#distance',
	  lat: '#lat',
	  long: '#long',
	  d: '#d',
	  field: 'd'
	}));

//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	Manager.addWidget(new AjaxSolr.CurrentSearchWidget({
	  id: 'currentsearch',
	  target_alt: '#removeall',
	  target: '#selection'
	}));

//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	Manager.addWidget(new AjaxSolr.SortByWidget({
	  id: 'sortbysearch',
	  target: '#sort'
	}));

//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
//if($('#srchmethod').val().indexOf("City Vision Intern Applicant") == -1){	
	Manager.addWidget(new AjaxSolr.PagerWidget({
	  id: 'pager',
	  target: '.pager',
	  prevLabel: '&lt;',
	  nextLabel: '&gt;',
	  innerWindow: 1,
	  renderHeader: function (perPage, offset, total) {
		$('.pager-header').html($('<span/>').text('  displaying ' + Math.min(total, offset + 1) + ' to ' + Math.min(total, offset + perPage) + ' of ' + total));
	  }
	}));
//}
//console.log('#srchmethod).text() is '+$('#srchmethod').val());	
	Manager.store.addByValue('rows', 50);
    Manager.store.addByValue('sort', sortKey);

	var params = {
	  facet: true,
	  'facet.field': [ 'intern_type', 'degree_prog', 'pos_pref', 'has_bachelors', 'credits_range', 'age_range', 'country_tax', 'gender', 'city_tax', 'geo_pref', 'intern_length', 'work_environ'],
	  'facet.limit': 100,
	  'facet.mincount': 1,
	  'f.keyword.facet.limit': 50,
	  'f.country.facet.limit': -1,
	  'json.nl': 'map'
	};
	for (var name in params) {
	  Manager.store.addByValue(name, params[name]);
	}

	$('#full_user').click(function() {
//console.log('382    self.manager.store.values is: ' + Manager.store.values('fq'));
   		Manager.store.addByValue('fq', 'full_user:true');
   		Manager.store.addByValue('fq', 'status:1');
//console.log('384    self.manager.store.values is: ' + Manager.store.values('fq'));
	
		// iterate through the fq's.  Set this as the new #link href for full_user instead of what was there before
		var facets = Manager.store.values('fq');
		var new_hashURL = '#';
		for (var x=0; x<facets.length; x++){
			var facet_string = '' + facets[x];
			if(facet_string.length>0)	{
				if(x>0)	new_hashURL += '&';
				new_hashURL += 'fq='+facet_string;
			}
		}
//console.log('new_hashURL is '+new_hashURL);	
		$("#full_user").attr("href", new_hashURL);
	});
	
//console.log('442  net_assets is: ' + $('#net_assets').html());
//console.log('SearchResultsSetup.js doRequest line 387');
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
    Manager.doRequest();
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));

//console.log('448  net_assets is: ' + $('#net_assets').html());
//alert($('#net_assets').html());
  });
  
  
  
var loadWidgets = function(response){
//console.log('this.manager.store.values is: ' + Manager.store.values('fq'));
	autocompleteSearch();
	//paramsSearch();
}
var paramsSearch = function (response){  
	//console.log(('paramsSearch function');
	Manager.addWidget(new AjaxSolr.SearchFromParamsWidget({
	  id: 'textparams',
	  target: '#params',
	  fields: [ 'keywords','degree_prog', 'intern_type', 'pos_pref', 'has_bachelors', 'credits_range', 'age_range', 'country_tax', 'region', 'province_tax', 'gender', 'org_affil', 'source', 'city_tax', 'position_type', 'content_type', 'city_tax', 'title', 'org_name', 'geo_tax', 'organization_name', 'intern_length', 'geo_pref', 'work_environ' ]
	}));
	autocompleteSearch();
}
var autocompleteSearch = function (response){
//	console.log('autocompleteSearch function');
//console.log('this.manager.store.values is: ' + Manager.store.values('fq'));
	Manager.addWidget(new AjaxSolr.SearchWithAutocompleteWidget({
	  id: 'text',
	  target: '#search',
	  field: 'keywords',
	  fields: [ 'keywords','degree_prog', 'intern_type', 'pos_pref', 'has_bachelors', 'credits_range', 'age_range',  'gender', 'org_affil', 'source',  'position_type', 'content_type', 'title', 'org_name',  'organization_name', 'intern_length', 'geo_pref', 'work_environ', 'country_tax', 'city_tax'],
	  target_alt: '#search_location',
	  field_alt: 'location',
	  fields_alt: [ 'country_tax', 'city_tax', 'province_tax', 'region'], //, 'postal_code' ],
	  target_alternate: '#srchmethod',
	  field_alternate: 'position_type',
	  fields_alternate: [ 'content_type', 'position_type' ]
	}));
//console.log('this.manager.store.values is: ' + Manager.store.values('fq'));
	showResults();
}
var showResults = function (response){
	  if (!window.console) console = {log: function() {}};
	//console.log(('showResults function');
//console.log('this.manager.store.values is: ' + Manager.store.values('fq'));
	Manager.addWidget(new AjaxSolr.ResultWidget({
	  id: 'result',
	  target: '#docs'
	}));	

	Manager.addWidget(new AjaxSolr.MapResultsWidget({
	  id: 'countries',
	  target: '#countries',
	  field: 'country'
	}));
	showFacets();
}
var showFacets = function (response){
//console.log('show facets');	  
	
	var fields = [];
//console.log('findKey is '+findKey);	
	var findKey = $('#srchmethod').val();
	fields = [ 'degree_prog', 'intern_type', 'pos_pref', 'has_bachelors', 'credits_range', 'age_range', 'country_tax',  'gender', 'org_affil', 'source', 'city_tax', 'geo_tax', 'intern_length', 'geo_pref', 'work_environ'];
	for (var i = 0, l = fields.length; i < l; i++) {
//console.log('fields[i] is '+fields[i]);	
	  Manager.addWidget(new AjaxSolr.FacetsWidget({
		id: fields[i],
		target: '#' + fields[i],
		field: fields[i]
	  }));
	}
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
