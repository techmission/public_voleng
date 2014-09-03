(function ($) {
AjaxSolr.SearchWithAutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
  beforeRequest: function () {
//    $(this.target).html($('<img/>').attr('src', 'http://www.christianvolunteering.org/imgs/Processing.gif'));
  },
  afterRequest: function () {
    var q_params=$('#keyword_search').text();
    var q=$('#query').val('');
    var q_alt=$('#queryLoc').val('');
    var q_alternate=$('#srchmethod').val();
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var d_default= $('#d_default').text();
    var self = this;
//console.log('Manager.store.values is: ' + Manager.store.values('fq'));
	var params_set=false;
	var search_params=false;
	var search_geo_loc=false;
    var fq=$('#fq_search').text();
	if(fq === undefined || fq == 'undefined'){
		fq='';
	}
	var search_data = '';
	var postal_data = '';
	var distance = '';
	
	if (!window.console) console = {log: function() {}};
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	  
	var keywords_params = [];
	var variables = window.location.hash.split('&');
	if (fq.length > 1) {
		// Variables present in hash
		params_set=true;
	}
   	
//     	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
//     	self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
	

	if(q_alternate=='foundation' || q_alternate=='Foundation' || q_alternate=='foundation AND NOT faith_i:21998'){
//	if(variables.indexOf('organization AND NOT faith_i:21998'){
//console.log('Foundation  self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.addByValue('fq', 'content_type:organization');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
		self.manager.store.removeByValue('fq', '-org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', 'org_member_type:Foundation');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='City Vision Internship' || q_alternate=='CityVision AND NOT faith_i:21998' || q_alternate=='CityVision AND NOT faith_i:21998'){
//console.log(' resume self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.addByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='"City Vision Intern Applicant"' || q_alternate=='City Vision Intern Applicant' || q_alternate=='"City Vision Intern Applicant" AND NOT faith_i:21998'){
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:organization');

    	self.manager.store.addByValue('fq', 'content_type:resume');
    	self.manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"');
    	self.manager.store.addByValue('fq', 'screened:[1 TO 2]');

//    	self.manager.store.addByValue('fq', 'intern_length:"One Year or Multi-year Internship"');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='resume' || q_alternate=='resume AND NOT faith_i:21998'){
//console.log(' resume self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.addByValue('fq', 'content_type:resume');
    	self.manager.store.addByValue('fq', 'full_user:true');
    	self.manager.store.addByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='organization' || q_alternate=='organization AND NOT faith_i:21998'){
//console.log('organization self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.addByValue('fq', 'content_type:organization');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
//console.log('organization search');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='job' || q_alternate=='job AND NOT faith_i:21998'){
//console.log('job self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
	   	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.addByValue('fq', 'content_type:job');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='Short-term Missions / Volunteer Internship' || q_alternate=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
//console.log('stm self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
    	self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:job');
//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='Local Volunteering (in person)' || q_alternate=='Local Volunteering (in person) AND NOT faith_i:21998'){
//console.log('local self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:job');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else if(q_alternate=='Virtual Volunteering (from home)' || q_alternate=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
//console.log('virtual self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
//    	self.manager.store.addByValue('fq', 'content_type:organization');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:job');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}else{
//console.log('ELSE self.manager.store.values is: ' + self.manager.store.values('fq'));
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:organization');
    	self.manager.store.addByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:resume');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:job');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
	}

	var hash_params = 'q=*:*&rows=0&facet=true&facet.field=position_type&facet.field=service_areas&facet.field=skills&facet.field=great_for&facet.field=frequency&facet.field=benefits_offered&facet.field=trip_length&facet.field=country_tax&facet.field=city_tax&facet.field=region&facet.field=province_tax&facet.field=denom_affil&facet.field=org_affil&facet.field=id&facet.field=source&facet.field=country&facet.limit=100&facet.mincount=1&f.keyword.facet.limit=-1&json.nl=map';
	q_params=$('#keyword_search').text();
	if(q_params.length > 0){
//console.log('q_params is: ' + q_params);
		params_set=true;
		hash_params += '&' + q_params;
	}
//    $('#search_solr_params').click(function(e, facet) {
    $('#search_solr_params').click(function(e, facet) {
		if (self.manager.proxyUrl) {
			initialize(function(){
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
//console.log('Autocomplete SearchParametersWidget.js line 176 - call for the autofills; jQuery.post'+self.manager.proxyUrl+' query hash_params: '+hash_params);
				jQuery.post(self.manager.proxyUrl, { query: hash_params }, callback, 'json'); // activates the autofills
			});
		}
		else {
//console.log('Autocomplete SearchParametersWidget.js line 148');
		  jQuery.getJSON(this.manager.solrUrl + 'select?' + hash_params + '&wt=json&json.wrf=?', {}, callback);
		}
		
		var facet_data;
		if (variables.length > 0) {
			// Variables present in hash
			for (i = 0; i < variables.length; i++) {
				params_set=true;
				var parse_data = variables[i];
				if(i==0){
					var hash_index = parse_data.indexOf("#");
					if(hash_index != -1){
						parse_data = parse_data.substring(1,parse_data.length);
					}
				}
//console.log('parse_data #' +i+ ' ' +parse_data);				
				var keyValuePair = parse_data.split('=');
console.log('keyValuePair is ' +keyValuePair);				

				if (keyValuePair[0] == 'fq') {
					var geo_filter = parse_data.split(':');
					facet_data = unescape(geo_filter[1]);
					if(facet_data.indexOf('geofilt') != -1){
//console.log('line');						
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
					postal_data = facet_data;
					//search_data += '&location_data=' + facet_data;
				}else if (keyValuePair[0] == 'distance') {
					facet_data = unescape(keyValuePair[1]);
					$('#distance').text(facet_data);
					distance = facet_data;
					search_data += '&location=distance:' + facet_data;
				}else if (keyValuePair[0] == 'content_type') {
					facet_data = unescape(keyValuePair[1]);
//console.log('facet_data is '+facet_data);					
					if(facet_data=='organization'){
				    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
				    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
				    	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
				    	self.manager.store.addByValue('fq', 'content_type:organization');
					}else if(facet_data.indexOf('City Vision Intern Applicant') != -1){
						self.manager.store.addByValue('q', '*:*');
						self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
						self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
						self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
						self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
						self.manager.store.removeByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'content_type:job');
						self.manager.store.removeByValue('fq', 'full_user:true');
						self.manager.store.removeByValue('fq', 'status:1');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				
						self.manager.store.addByValue('fq', 'content_type:resume');
						self.manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"');
						self.manager.store.addByValue('fq', 'screened:[1 TO 2]');
				
				//    	self.manager.store.addByValue('fq', 'intern_length:"One Year or Multi-year Internship"');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
					
					}else if(facet_data=='City Vision' || facet_data=='City Vision Internship'){
				    	self.manager.store.addByValue('fq', 'content_type:opportunity');
				    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
				    	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
				    	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
					}else if(facet_data=='job'){
				    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
				    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
				    	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
				    	self.manager.store.addByValue('fq', 'content_type:job');
					}else if(facet_data=='resume'){
				    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
				    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
				    	self.manager.store.addByValue('fq', 'content_type:resume');
				    	self.manager.store.addByValue('fq', 'full_user:true');
    	self.manager.store.addByValue('fq', 'status:1');
					}else if(facet_data=='Foundation'){
				    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
				    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
				    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
				    	self.manager.store.addByValue('fq', 'org_member_type:Foundation');
					}else if(facet_data=='opportunity'){
						self.manager.store.addByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
					    self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
					}
				}else if (keyValuePair[0] == 'organization_name') {
					facet_data = unescape(keyValuePair[1]);
//console.log('facet_data is: '+facet_data);					
					if(facet_data=='City Vision' || facet_data=='City Vision Internship'){
						self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.addByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
					    self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
					    self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
					}
					search_params=true;
//					self.manager.store.addByValue('fq', 'organization_name:' + facet_data);
					search_data += '&keywords=organization_name:' + facet_data;
				}else if (keyValuePair[0] == 'position_type') {
					facet_data = unescape(keyValuePair[1]);
//console.log('facet_data is: '+facet_data);					
					if(facet_data=='Local Volunteering (in person)'){
						self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.addByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
					    self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
					}else if(facet_data=='Short-term Missions / Volunteer Internship'){
						self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.addByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
					    self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
					}else if(facet_data=='Virtual Volunteering (from home)'){
						self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
						self.manager.store.addByValue('fq', 'content_type:opportunity');
						self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
						self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
						self.manager.store.removeByValue('fq', 'content_type:organization');
				    	self.manager.store.removeByValue('fq', 'content_type:job');
				    	self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
					    self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
					}
					search_params=true;
//					self.manager.store.addByValue('fq', 'position_type:' + facet_data);
					search_data += '&keywords=position_type:' + facet_data;
				}else if (keyValuePair[0] == 'org_member_type') {
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
					self.manager.store.removeByValue('fq', 'content_type:opportunity');
					self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
					self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
					self.manager.store.addByValue('fq', 'content_type:organization');
				    self.manager.store.removeByValue('fq', 'content_type:job');
				    self.manager.store.removeByValue('fq', 'content_type:resume');
				    	self.manager.store.removeByValue('fq', 'full_user:true');
				    self.manager.store.addByValue('fq', 'org_member_type:Foundation');
					search_params=true;
					search_data += '&keywords=org_member_type:' + facet_data;
				}else if (keyValuePair[0] == 'service_areas') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'service_areas:' + facet_data);
					search_data += '&keywords=service_areas:' + facet_data;
				}else if (keyValuePair[0] == 'intern_type') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'intern_type:' + facet_data);
					search_data += '&keywords=intern_type:' + facet_data;
				}else if (keyValuePair[0] == 'pos_pref') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'pos_pref:' + facet_data);
					search_data += '&keywords=pos_pref:' + facet_data;
				}else if (keyValuePair[0] == 'has_bachelors') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'has_bachelors:' + facet_data);
					search_data += '&keywords=has_bachelors:' + facet_data;
				}else if (keyValuePair[0] == 'credits_range') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'credits_range:' + facet_data);
					search_data += '&keywords=credits_range:' + facet_data;
				}else if (keyValuePair[0] == 'gender') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'gender:' + facet_data);
					search_data += '&keywords=gender:' + facet_data;
				}else if (keyValuePair[0] == 'intern_length') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'intern_length:' + facet_data);
					search_data += '&keywords=intern_length:' + facet_data;
				}else if (keyValuePair[0] == 'title') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'title:' + facet_data);
					search_data += '&keywords=title:' + facet_data;
				}else if (keyValuePair[0] == 'age_range') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'age_range:' + facet_data);
					search_data += '&keywords=age_range:' + facet_data;
				}else if (keyValuePair[0] == 'org_name') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'org_name:' + facet_data);
					search_data += '&keywords=org_name:' + facet_data;
				}else if (keyValuePair[0] == 'organization_namee') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'organization_name:' + facet_data);
					search_data += '&keywords=organization_name:' + facet_data;
				}else if (keyValuePair[0] == 'denom_affil') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'denom_affil:' + facet_data);
					search_data += '&keywords=denom_affil:' + facet_data;
				}else if (keyValuePair[0] == 'skills') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'skills:' + facet_data);
					search_data += '&keywords=skills:' + facet_data;
				}else if (keyValuePair[0] == 'keywords') {
					// will have to be able to handle multiple input here; should be OR'ed, rather than the default AND
					facet_data = unescape(keyValuePair[1]);
					keywords_params.push(facet_data);
					//self.manager.store.addByValue('fq', 'keywords:' + facet_data);
					search_data += '&keywords=keyword:' + facet_data;
				}else if (keyValuePair[0] == 'location') {
					facet_data = unescape(keyValuePair[1]);
					search_params=true;
					self.manager.store.addByValue('fq', 'location:' + facet_data);
					search_data += '&location=location:' + facet_data;
				}else{
					facet_data = unescape(keyValuePair[1]);
					if(facet_data.indexOf('geofilt') != -1){
						search_geo_loc=true;
					}
					if(facet_data === undefined || facet_data == 'undefined'){
					}else{
						search_params=true;
						self.manager.store.addByValue('fq', keyValuePair[0] + ':' + facet_data);
						//search_data += '&fq=' + keyValuePair[0] + ':' + facet_data;
					}
				}
			}

			var keywords_search_string = '';
			if(keywords_params.length>0){
				keywords_search_string = keywords_params.join(' OR ');
				if(keywords_params.length>1){
					keywords_search_string = '(' + keywords_search_string + ')';
				}
				keywords_search_string = 'keywords:' + keywords_search_string + '';
				search_params=true;
				self.manager.store.addByValue('fq', keywords_search_string);
			}
			
			params_set=false;
			if(search_geo_loc==true){
				initialize(function(){
					if(postal_data.length>0){
						$('#postal').text(postal_data);		
					}
					if(distance.length>0){
						$('#d_default').text(distance);	
						// make sure it's the option selected in the dropdown
					    $("#d option[value='"+distance+"']").attr('selected', 'selected');
					}
					var geo_lat= $('#geo_lat').text();
					var geo_long= $('#geo_long').text();
					var d_default= $('#d_default').text();
					search_params=true;
					var new_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
					$('#geofilt_facet').text(new_geo_search);
//console.log('line');						
					self.manager.store.addByValue('fq', new_geo_search);
					if(search_params==true){
//console.log('Autocomplete SearchParametersWidget.js line 163');
						self.manager.doRequest(0);
					}
				});
			}else{
				if(search_params==true){
//console.log('Autocomplete SearchParametersWidget.js line 169');
					self.manager.doRequest(0);
				}
			}
			var search_data_index = search_data.indexOf("&");
			if(search_data_index != -1){
				search_data = search_data.substring(1,search_data.length);
			}
			call_google_analytics(search_data);

		}
    });
	
	
	var params;
//console.log('q_alternate is '+q_alternate);	
	if(q_alternate=='Foundation'){
    	params = [ 'q=content_type:organization&org_member_type:Foundation&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='organization'){
    	params = [ 'q=content_type:organization&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='organization AND NOT faith_i:21998'){
    	params = [ 'q=(content_type:organization AND NOT faith_i:21998)&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='job'){
    	params = [ 'q=content_type:job&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='job AND NOT faith_i:21998'){
    	params = [ 'q=(content_type:job AND NOT faith_i:21998)&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='City Vision Intern Applicant'){
    	params = [ 'q=(content_type:resume AND cvintern_applicant:"City Vision Intern Applicant" AND screened:[1 TO 2])&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='resume'){
    	params = [ 'q=(content_type:resume AND status:1)&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='resume AND NOT faith_i:21998'){
    	params = [ 'q=(content_type:resume AND status:1 AND NOT faith_i:21998)&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='opportunity AND NOT faith_i:21998'){
    	params = [ 'q=(content_type:opportunity AND NOT faith_i:21998)&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else{
		params = [ 'q=content_type:opportunity&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}
//console.log('params is '+params);	
/*
	var params;
	}else if(q_alternate=='organization'){
    	params = [ 'q=NOT faith_i:21998&fq=content_type:organization' ];
	}else if(q_alternate=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
    	params = [ 'q=NOT faith_i:21998&fq=position_type:"Short-term Missions / Volunteer Internship"' ];
	}else if(q_alternate=='Local Volunteering (in person) AND NOT faith_i:21998'){
    	params = [ 'q=NOT faith_i:21998&fq=position_type:"Local Volunteering (in person)"' ];
	}else if(q_alternate=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
    	params = [ 'q=NOT faith_i:21998&fq=position_type:"Virtual Volunteering (from home)"' ];
	}else if(q_alternate=='opportunity AND NOT faith_i:21998'){
    	params = [ 'q=NOT faith_i:21998&fq=content_type:opportunity' ];

	}else if(q_alternate=='organization'){
    	params = [ 'q=*:*&fq=content_type:organization' ];
	}else if(q_alternate=='Short-term Missions / Volunteer Internship'){
    	params = [ 'q=*:*&fq=position_type:"Short-term Missions / Volunteer Internship"' ];
	}else if(q_alternate=='Local Volunteering (in person)'){
    	params = [ 'q=*:*&fq=position_type:"Local Volunteering (in person)"' ];
	}else if(q_alternate=='Virtual Volunteering (from home)'){
    	params = [ 'q=*:*&fq=position_type:"Virtual Volunteering (from home)"' ];
	}else{
		params = [ 'q=*:*&fq=content_type:opportunity' ];
	}
	params.push('&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map');
*/
//console.log(params);
    for (var i = 0; i < this.fields.length; i++) {
      params.push('facet.field=' + this.fields[i]);
    }
    for (var i = 0; i < this.fields_alt.length; i++) {
      params.push('facet.field=' + this.fields_alt[i]);
    }
	
    // unautocomplete() below will unbind the keydown handler.
    $('#query').unbind().bind('keydown', function(e) {
      if (self.requestSent === false && e.which == 13) {
        var value = $(this).val();
        if( e.which == 13){
			$('#search_solr').click() ;
		}else if (value && self.add(value)) {
//console.log('AutocompleteSearchWidget.js doRequest line 386');			
          self.manager.doRequest(0);
        }
      }
    });
    $('#queryLoc').unbind().bind('keydown', function(e) {
      if (self.requestSent === false && e.which == 13) {
        var value = $(this).val();
		if( e.which == 13){
			$('#search_solr').click() ;
		}else if (value && self.add(value)) {
//console.log('AutocompleteSearchWidget.js doRequest line 397');
          self.manager.doRequest(0);
        }
      }
    });
	
    var callback = function (response) {
      var list = [];
      var list_alt = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
//console.log('660  field is '+field);		
        for (var facet in response.facet_counts.facet_fields[field]) {
			var field_name_reworded = '(keyword)';//field;
			var search_field_index = field_name_reworded.indexOf("_search");
			if(search_field_index != -1){
				field_name_reworded = '(keyword)';
			}else if(field=='service_areas'){
				field_name_reworded = '(Service Area)'
			}else if(field=='intern_type'){
				field_name_reworded = '(Internship Type)'
			}else if(field=='pos_pref'){
				field_name_reworded = '(Position Preference)'
			}else if(field=='has_bachelors'){
				field_name_reworded = '(Has Bachelor\'s)'
			}else if(field=='credits_range'){
				field_name_reworded = '(Credits Range)'
			}else if(field=='age_range'){
				field_name_reworded = '(Age Range)'
			}else if(field=='gender'){
				field_name_reworded = '(Gender)'
			}else if(field=='intern_length'){
				field_name_reworded = '(Internship Length)'
			}else if(field=='title'){
				field_name_reworded = '(Title)'
			}else if(field=='great_for'){
				field_name_reworded = '(keyword)'
			}else if(field=='org_affil'){
				field_name_reworded = '(Organizational Affiliation)'
			}else if(field=='program_type'){
				field_name_reworded = '(Program Type)'
			}else if(field=='denom_affil'){
				field_name_reworded = '(Denominational Affiliation)'
			}else if(field=='primary_opp_type'){
				field_name_reworded = '(Position Type)'
			}else if(field=='position_type'){
				field_name_reworded = '(Position Type)'
			}else if(field=='benefits_offered'){
				field_name_reworded = '(Benefits Offered)'
			}else if(field=='skills'){
				field_name_reworded = '(Skill)'
			}
          list.push({
            field: field,
            value: facet,
            text: facet + ' - ' + field_name_reworded
          });
        }
      }
      for (var i = 0; i < self.fields_alt.length; i++) {
        var field_alt = self.fields_alt[i];
        for (var facet_alt in response.facet_counts.facet_fields[field_alt]) {
			var field_name_reworded = field_alt;
			if(field_alt=='province_tax'){
				field_name_reworded = '(state//province)';
			}else if(field_alt=='city_tax'){
				field_name_reworded = '(city)'
			}else if(field_alt=='country_tax'){
				field_name_reworded = '(country)'
			}else if(field_alt=='postal_code'){
				field_name_reworded = '(postal code)'
			}else{
				field_name_reworded = '(location)';	
			}
			  list_alt.push({
				field: field_alt,
				value: facet_alt,
				text: facet_alt + ' - ' + field_name_reworded
			  });
        }
      }

      self.requestSent = false;
      $('#query').unautocomplete().autocomplete(list, {
        formatItem: function(facet) {
          return facet.text;
        }
      }).result(function(e, facet) {
        self.requestSent = true;
//console.log('facet.field 469 '+ facet.field);			
		if(facet.field=='keywords'){
			var facet_data = facet.value;
			var variables = facet_data.split(',');
			var facet_values='';
			if (variables.length > 1) {
				for (i = 0; i < variables.length; i++) {
					var facet_value = variables[i];
					var index = facet_value.indexOf(' '); // handle a space after a comma
					if(index == 0){
						facet_value = facet_value.substring(1);
					}
					if(facet_value === undefined){
					}else{
						facet_values += facet_value;
						if(i < variables.length-1){
							facet_values += ' OR ';
						}
					}
				}
				self.manager.store.removeByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))
				self.manager.store.addByValue('fq', facet.field + ':(' + facet_values + ')');
			}
		}else{
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value));
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		}
      });
      $('#queryLoc').unautocomplete().autocomplete(list_alt, {
        formatItem: function(facet_alt) {
          return facet_alt.text;
        }
      }).result(function(e, facet_alt) {
        self.requestSent = true;
		if(facet_alt.field=='postal_code'){
//console.log('facet_alt.field=postal_code line 502');			
			self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
			$('div#postal').text(facet_alt.value);
			initialize(function(){
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				var new_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
				$('#geofilt_facet').text(new_geo_search);
//console.log('line');						
				self.manager.store.addByValue('fq', new_geo_search);
			});
		}else{
			
			if(facet_alt.field=='location'){
//console.log('facet_alt line 516');			
				var b_isNotANumber = isNaN($('div#postal').text(facet_alt.value));
				if(b_isNotANumber==true){
					// is text, then	
					self.manager.store.addByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
				}else{
					self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
//console.log('facet_alt line  523');			
					$('div#postal').text(facet_alt.value);
					initialize(function(){
						geo_lat= $('#geo_lat').text();
						geo_long= $('#geo_long').text();
						d_default= $('#d_default').text();
						var new_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
						$('#geofilt_facet').text(new_geo_search);
//console.log('line');						
						self.manager.store.addByValue('fq', new_geo_search);
					});
				}
			}
			
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			
		}
      });
   } // end callback

    if (this.manager.proxyUrl) {
		initialize(function(){
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
// the following would make the counts reported in the autocomplete reflect the counts for that specific IP's geo location filter, rather than the total counts
//	params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}'); // makes autocomplete break w postal code
			if(search_data.length<1){
				call_google_analytics('fq=all');
			}
//console.log('AutocompleteSearchWidget.js line 760');
//console.log('jQuery.post'+self.manager.proxyUrl+' query: '+params.join('&'));
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			jQuery.post(self.manager.proxyUrl, { query: params.join('&') }, callback, 'json');
		});
    }
    else {
      jQuery.getJSON(this.manager.solrUrl + 'select?' + params.join('&') + '&wt=json&json.wrf=?', {}, callback);
    }
	
    $('#search_solr').click(function(e, facet) {
self.manager.store.remove('fq');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
self.manager.store.addByValue('fq', 'content_type:opportunity');
//console.log('self.manager.store.values 2 is: ' + self.manager.store.values('fq'));
		var new_filters=[];							 
		var srchmethod=$('#srchmethod').val();
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		if(srchmethod=='Foundation AND NOT faith_i:21998'){
	//	if(variables.indexOf('organization AND NOT faith_i:21998'){
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.addByValue('fq', 'content_type:organization');
			self.manager.store.addByValue('fq', 'org_member_type:Foundation');
			new_filters.push('content_type:organization');
			new_filters.push('org_member_type:Foundation');
	}else if(srchmethod=='"City Vision Intern Applicant"' || srchmethod=='City Vision Intern Applicant' || srchmethod=='"City Vision Intern Applicant" AND NOT faith_i:21998'){
    	self.manager.store.addByValue('q', '*:*');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
    	self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
    	self.manager.store.removeByValue('fq', 'content_type:job');
    	self.manager.store.removeByValue('fq', 'full_user:true');
    	self.manager.store.removeByValue('fq', 'status:1');
    	self.manager.store.removeByValue('fq', 'content_type:organization');

    	self.manager.store.addByValue('fq', 'content_type:resume');
    	self.manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"');
    	self.manager.store.addByValue('fq', 'screened:[1 TO 2]');
			new_filters.push('content_type:resume');
			new_filters.push('cvintern_applicant:"City Vision Intern Applicant"');
			new_filters.push('screened:[1 TO 2]');
			if(new_filters.indexOf('cvintern_placed') > -1){
			}else{
				new_filters.push('cvintern_placed:0');
			}

//    	self.manager.store.addByValue('fq', 'intern_length:"One Year or Multi-year Internship"');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		}else if(srchmethod=='organization AND NOT faith_i:21998'){
	//	if(variables.indexOf('organization AND NOT faith_i:21998'){
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.addByValue('fq', 'content_type:organization');
			new_filters.push('content_type:organization');
		}else if(srchmethod=='job AND NOT faith_i:21998'){
	//	if(variables.indexOf('organization AND NOT faith_i:21998'){
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.addByValue('fq', 'content_type:job');
			new_filters.push('content_type:job');
		}else if(srchmethod=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
	//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('position_type:"Short-term Missions / Volunteer Internship"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='Local Volunteering (in person) AND NOT faith_i:21998'){
	//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('position_type:"Local Volunteering (in person)"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
	//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('position_type:"Virtual Volunteering (from home)"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='opportunity AND NOT faith_i:21998'){
			self.manager.store.addByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='resume AND NOT faith_i:21998'){
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.addByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'full_user:true');
			
			new_filters.push('content_type:resume');
			new_filters.push('full_user:true');
	
		}else if(srchmethod=='resume'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.addByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.addByValue('fq', 'full_user:true');
			
			new_filters.push('content_type:resume');
			new_filters.push('full_user:true');
		}else if(srchmethod=='City Vision'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.addByValue('fq', 'content_type:opportunity');
			self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('intern_type:"City Vision Internship"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='Foundation'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.addByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.addByValue('fq', 'org_member_type:Foundation');
			new_filters.push('org_member_type:Foundation');
			new_filters.push('content_type:organization');
		}else if(srchmethod=='organization'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.addByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('content_type:organization');
		}else if(srchmethod=='job'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('content_type:job');
		}else if(srchmethod=='Short-term Missions / Volunteer Internship'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
	//		self.manager.store.removeByValue('fq', 'content_type:job');
	//		self.manager.store.removeByValue('fq', 'content_type:organization');
	//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			new_filters.push('position_type:"Short-term Missions / Volunteer Internship"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='Local Volunteering (in person)'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
	//		self.manager.store.removeByValue('fq', 'content_type:job');
	//		self.manager.store.removeByValue('fq', 'content_type:organization');
	//    	self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			new_filters.push('position_type:"Local Volunteering (in person)"');
			new_filters.push('content_type:opportunity');
		}else if(srchmethod=='Virtual Volunteering (from home)'){
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
	//		self.manager.store.removeByValue('fq', 'content_type:job');
	//    	self.manager.store.addByValue('fq', 'content_type:organization');
	//		self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			new_filters.push('position_type:"Virtual Volunteering (from home)"');
			new_filters.push('content_type:opportunity');
		}else{
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'full_user:true');
			self.manager.store.addByValue('fq', 'content_type:opportunity');
			new_filters.push('content_type:opportunity');
		}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
										 
		var field = self.field;
		var fields = self.fields;
		var query = $('#query').val();
//console.log('field is: ' + field);
//console.log('query is: ' + query);
		var already_searched = false;
        var values = [];
		// still want to somehow store the location, though; just clear for the ones that start with keywords, etc
		var location_filter = '';
		var store_filters = self.manager.store.values('fq');
		var sub_filter = ''+self.manager.store.values('fq');
//console.log('sub_filter is: '+sub_filter);		
		// {!geofilt pt=42.2826027,-71.06760500000001 sfield=latlng d=40.2336}
		var geofilt_index = sub_filter.indexOf('{!geofilt');
		var location_index = sub_filter.indexOf('location');
		var country_index = sub_filter.indexOf('country_tax');
		var city_index = sub_filter.indexOf('city_tax');
		var region_index = sub_filter.indexOf('region');
		var location_substring = '';
		var location_index_end = '';
		if(geofilt_index != -1){
			location_substring = sub_filter.substring(geofilt_index);
		}else if(location_index != -1){
			location_substring = sub_filter.substring(location_index);
		}else if(country_index != -1){
			location_substring = sub_filter.substring(country_index);
		}else if(city_index != -1){
			location_substring = sub_filter.substring(city_index);
		}
		if(location_substring.length > 0){
			if(geofilt_index != -1){
				location_index_end = location_substring.indexOf('}')+1;
			}else{
				location_index_end = location_substring.indexOf('",')+1;
				if(location_index_end==0){
					location_index_end = location_substring.length;
				}
			}
			location_filter = location_substring.substring(0,location_index_end);
		}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
//console.log('location filter line 612: '+location_filter);	
		// clear filter parameters initially from previous searches
//console.log('sub_filter is: '+sub_filter);		
		self.manager.store.remove('fq');
//console.log('sub_filter is: '+''+self.manager.store.values('fq'));		
		if(location_filter.length>0){
			self.manager.store.addByValue('fq', location_filter);
		}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		
		for(i=0; i < new_filters.length; i++){
			self.manager.store.addByValue('fq', new_filters[i]);
		}
		values = self.manager.store.values('fq');
		for(i=0; i < values.length; i++){
			var value = values[i];
			if(value.indexOf(query) != -1){
				already_searched=true;
			}else{
			}
		}
//console.log(values)
		
		
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
//console.log('field is: ' + field);
//console.log('query is: ' + query);
		if(query.length > 0 && already_searched==false 
		   && query != 'Service Areas, Skills' && query != 'Service Areas' && query != 'Keyword' && query != 'Keywords (Service Areas, Skills)' && query != 'Country, Postal Code' && query != 'Country%2C%20Postal%20Code'){
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			if(field=='keywords'){
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
					// iterate through all the fields first to see if any of them contain this facet value; otherwise, fall back to a keyword search
//console.log('field is '+ field);	// doi reverse of  409				
//console.log('fields is '+ fields);	// doi reverse of  409				
//console.log('query is '+ query);
//console.log('query.indexOf(\'(Service Area)\') is '+ query.indexOf('(Service Area)'));

			if(query.indexOf('(keyword)') > -1){
				field = 'keywords'
			}else if(query.indexOf('(Service Area)') > -1){
				field = 'service_areas'
			}else if(query.indexOf('(Organizational Affiliation)') > -1){
				field = 'org_affil'
			}else if(query.indexOf('(Intern Type)')){
				field = 'intern_type'
			}else if(query.indexOf('(Position Preference)')){
				field = 'pos_pref'
			}else if(query.indexOf('(Has Bachelor\'s)')){
				field = 'has_bachelors'
			}else if(query.indexOf('(Credits Range)')){
				field = 'credits_range'
			}else if(query.indexOf('(Age Range)')){
				field = 'age_range'
			}else if(query.indexOf('(Gender)')){
				field = 'gender'
			}else if(query.indexOf('(Title)')){
				field = 'title'
			}else if(query.indexOf('(Intern Length)')){
				field = 'intern_length'
			}else if(query.indexOf('(Program Type)')){
				field = 'program_type'
			}else if(query.indexOf('(Denominational Affiliation)') > -1){
				field = 'denom_affil'
			}else if(query.indexOf('(Position Type)')){
				field = 'position_type'
			}else if(query.indexOf('(Benefits Offered)') > -1){
				field = 'benefits_offered'
			}

//console.log('field is '+ field);	// doi reverse of  660				

		var index = query.indexOf(' - ');
		if(index != -1){
			query = query.substring(0, index); // get the subquery, without the name of the facet field, etc, if given
		}

				var facet_data = query;
				var variables = facet_data.split(',');
				var facet_values='';
				if (variables.length > 0) {
					for (i = 0; i < variables.length; i++) {
						var facet_value = variables[i];
						var index = facet_value.indexOf(' '); // handle a space after a comma
						if(index == 0){
							facet_value = facet_value.substring(1);
						}
						if(facet_value === undefined){
						}else{
							facet_values += facet_value;
							if(i < variables.length-1){
								facet_values += ' OR ';
							}
						}
					}
					
					
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
					self.manager.store.removeByValue('fq', field + ':' + query);
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
					if(variables.length > 1){
						self.manager.store.addByValue('fq', field + ':(' + facet_values + ')');
					}else{
						self.manager.store.addByValue('fq', field + ':"' + facet_values + '"');
					}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
				}
			}else{
				self.manager.store.addByValue('fq', field + ':"' + query + '"');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			}
		}
		
		var field_alt = self.field_alt; // 'location'; 
		var query_alt = $('#queryLoc').val();
		var index_alt = query_alt.indexOf(' - ');
		if(index_alt != -1){
			query_alt = query_alt.substring(0, index_alt); // get the subquery, without the name of the facet field, etc, if given
		}
		var already_searched_alt = false;
        var values_alt = [];
		values_alt = self.manager.store.values('fq');
//console.log('values_alt is: ' + values_alt);
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		for(i=0; i < values_alt.length; i++){
			var value = values_alt[i];
			if(value.indexOf(query_alt) != -1){
				already_searched_alt=true;
			}
		}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
		if(query_alt.length > 0 && already_searched_alt==false 
		   && query_alt != 'Country, Postal Code' && query_alt != 'Country%2C%20Postal%20Code'){
			var b_isNotANumber = isNaN(query_alt);
			if(b_isNotANumber==true){
				// is text, then	
				if(query_alt.length > 0){
					self.manager.store.addByValue('fq', field_alt + ':"' + query_alt + '"');
				}
			}else{
				self.manager.store.removeByValue('fq', field_alt + ':' + query_alt);
//console.log('line 475: postal is ' + query_alt);			
					var old_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
					$('#geofilt_facet').text();
//console.log('line 936: REMOVE '+old_geo_search);			
					self.manager.store.removeByValue('fq', old_geo_search);
				$('div#postal').text(query_alt); // $('div#postal').text(facet_alt.value);
				initialize(function(){
					geo_lat= $('#geo_lat').text();
					geo_long= $('#geo_long').text();
					d_default= $('#d_default').text();
					var new_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
					$('#geofilt_facet').text(new_geo_search);
//console.log('line 945: ADD    '+new_geo_search);			
					self.manager.store.addByValue('fq', new_geo_search);
				});
			}
		}
		initialize(function(){
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
			var new_geo_search = '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}';
			$('#geofilt_facet').text(new_geo_search);
//console.log('line');						
			params.push('fq='+new_geo_search);
//console.log('AutocompleteSearchWidget.js doRequest line 538; gets called exponentially on subsequent searches');
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
          self.manager.doRequest(0);
		});
    });

    $('#query').val('Keywords (Service Areas, Skills)');
    $('#queryLoc').val('Country, Postal Code');
    $('#oppsearch').addClass('solr');
    $('#query').click(function(e) {
          $('#query').val('');
          $('#query').addClass('active_watermark');
    });
    $('#queryLoc').click(function(e) {
          $('#queryLoc').val('');
          $('#queryLoc').addClass('active_watermark');
    });
	if(params_set==true){
		$('#keyword_search').text(''); // clear for further filtering purpuses
		$('#fq_search').text(''); // clear for further filtering purpuses
		$('#search_solr_params').click();
	}
	
  }
});

})(jQuery);
