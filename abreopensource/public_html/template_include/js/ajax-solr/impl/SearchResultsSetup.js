var Manager;

(function ($) {

  $(function () {
//console.log('net_assets is: ' + $('#net_assets').html());
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
	var hash_org_impl = hashURL_impl.indexOf("content_type:organization");
	var hash_job_impl = hashURL_impl.indexOf("content_type:job");
	var hash_cv_impl = hashURL_impl.indexOf('City Vision');
	var hash_cvintern = hashURL_impl.indexOf('fq=cvintern_applicant:"City Vision Intern Applicant"');
	var hash_resume_impl = hashURL_impl.indexOf("content_type:resume");
//console.log('content_type_div is '+content_type_div)	
	if(content_type_div.indexOf("content_type:organization") != -1){
		hash_org_impl = 0;
	}
	if(content_type_div.indexOf("content_type:job") != -1){
		hash_job_impl = 0;
	}
	if(content_type_div.indexOf('cvintern_applicant:"City Vision Intern Applicant"') != -1){
		hash_cv_impl = -1;
		hash_cvintern = 0;
//console.log('triggered line 87');
	}else
	if(content_type_div.indexOf("content_type:resume") != -1){
		hash_resume_impl = 0;
	}
	var hash_opp_impl = hashURL_impl.indexOf("content_type:opportunity");
	var hash_stm_impl = hashURL_impl.indexOf("Short-term Missions / Volunteer Internship");
	var hash_local_impl = hashURL_impl.indexOf("Local Volunteering (in person)");
	var hash_virtual_impl = hashURL_impl.indexOf("Virtual Volunteering (from home)");
	var hash_fdn_impl = hashURL_impl.indexOf("fq=org_member_type:Foundation");
	
//console.log('hash_cvintern is '+hash_cvintern)	
	if(hash_fdn_impl != -1){
			$('#srchmethod').val('Foundation');
			$('#contenttype_title').text('Foundations');
			$('#contenttype_heading').text('Foundations');
	}else{
	// add foundation
	
	if(hash_fdn_impl != -1){
			$('#srchmethod').val('Foundation');
			$('#contenttype_title').text('Foundations');
			$('#contenttype_heading').text('Foundations');
		}else if(hash_org_impl != -1){
			$('#srchmethod').val('organization');
			$('#contenttype_title').text('Organization');
			$('#contenttype_heading').text('Volunteer Organizations');
		}else if(hash_cv_impl != -1){
			$('#srchmethod').val('City Vision');
			$('#contenttype_title').text('Featured Christian Internships');
			$('#contenttype_heading').text('Featured Internships Directory');
		}else if(hash_job_impl != -1){
			$('#srchmethod').val('job');
			$('#contenttype_title').text('Job');
			$('#contenttype_heading').text('Christian Job Postings');
		}else if(hash_cvintern != -1){
			$('#srchmethod').val('"City Vision Intern Applicant"');
			$('#contenttype_title').text('Internship Candidates');
			$('#contenttype_heading').text('Internship Candidates');
		}else if(hash_resume_impl != -1){
			$('#srchmethod').val('resume');
			$('#contenttype_title').text('Resume');
			$('#contenttype_heading').text('Volunteer Directory');
		}else {
			if(hash_stm_impl != -1){
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
			}else{
				$('#srchmethod').val('opportunity');
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
//console.log('findKey  is: ' + findKey);

	if(findKey=='Foundation' || findKey=='foundation' || findKey=='foundation AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'content_type:resume');
    	Manager.store.removeByValue('fq', 'status:1');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:job');
		Manager.store.removeByValue('fq', '-org_member_type:Foundation');
		Manager.store.removeByValue('fq', '-org_member_type:"Foundation"');
    	Manager.store.addByValue('fq', 'content_type:organization');
//    	Manager.store.addByValue('fq', '-hidden_source:irs_parachurch');
		Manager.store.addByValue('fq', 'org_member_type:Foundation'); // nmioght need ""????????????
		$("#facet_num_opps").hide();
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	}else if(findKey=='City Vision Intern Applicant' || findKey=='"City Vision Intern Applicant"'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.addByValue('fq', 'content_type:resume');
    	Manager.store.removeByValue('fq', 'status:1');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:job');
		Manager.store.removeByValue('fq', '-org_member_type:Foundation');
		Manager.store.removeByValue('fq', '-org_member_type:"Foundation"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
    	Manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"');
//    	Manager.store.addByValue('fq', '-hidden_source:irs_parachurch');
		Manager.store.removeByValue('fq', 'org_member_type:Foundation'); // nmioght need ""????????????
		$("#facet_num_opps").hide();
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	}else if(findKey=='City Vision' || findKey=='City Vision' || findKey=='CityVision AND NOT faith_i:21998' || findKey=='City Vision Internship' || findKey=='City Vision Internship AND NOT faith_i:21998'){
    	Manager.store.addByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'content_type:resume');
    	Manager.store.removeByValue('fq', 'status:1');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:job');
		Manager.store.removeByValue('fq', '-org_member_type:Foundation');
		Manager.store.removeByValue('fq', '-org_member_type:"Foundation"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
    	Manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//    	Manager.store.addByValue('fq', '-hidden_source:irs_parachurch');
		Manager.store.removeByValue('fq', 'org_member_type:Foundation'); // nmioght need ""????????????
		$("#facet_num_opps").hide();
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
	}else if(findKey=='organization' || findKey=='organization AND NOT faith_i:21998'){

		$("#facet_num_opps").show();
		
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'content_type:resume');
    	Manager.store.removeByValue('fq', 'status:1');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:job');
    	Manager.store.addByValue('fq', 'content_type:organization');
		Manager.store.removeByValue('fq', 'org_member_type:Foundation');
		Manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
    	Manager.store.addByValue('fq', '-org_member_type:Foundation');
//console.log('self.manager.store.values is: ' + Manager.store.values('fq'));
//    	Manager.store.addByValue('fq', '-hidden_source:irs_parachurch');
		if(hash_local_impl != -1){
	    	Manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
		}else if(hash_virtual_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		}else if(hash_stm_impl != -1){
    		Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		}
	}else if(findKey=='job' || findKey=='job AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'content_type:resume');
    	Manager.store.removeByValue('fq', 'status:1');
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
	}else if(findKey=='resume' || findKey=='resume AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'content_type:opportunity');
    	Manager.store.removeByValue('fq', 'content_type:job');
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
    	Manager.store.removeByValue('fq', 'content_type:organization');
    	Manager.store.addByValue('fq', 'content_type:resume');
    	Manager.store.addByValue('fq', 'status:1');
	}else if(findKey=='Short-term Missions / Volunteer Internship' || findKey=='Short-term Missions / Volunteer Internship AND NOT faith_i:21998'){
    	Manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		if(hash_org_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
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
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.addByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}
	}else if(findKey=='Virtual Volunteering (from home)' || findKey=='Virtual Volunteering (from home) AND NOT faith_i:21998'){
    	Manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
    	Manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
    	Manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		if(hash_org_impl != -1){
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.addByValue('fq', 'content_type:organization');
		}else if(hash_job_impl != -1){
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.removeByValue('fq', 'content_type:opportunity');
	    	Manager.store.addByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}else{
	    	Manager.store.removeByValue('fq', 'content_type:resume');
	    	Manager.store.removeByValue('fq', 'status:1');
    		Manager.store.addByValue('fq', 'content_type:opportunity');
	    	Manager.store.removeByValue('fq', 'content_type:job');
	    	Manager.store.removeByValue('fq', 'content_type:organization');
		}
	}else if(findKey=='opportunity' || findKey=='opportunity AND NOT faith_i:21998'){
	   	Manager.store.removeByValue('fq', 'content_type:resume');
	   	Manager.store.removeByValue('fq', 'status:1');
	   	Manager.store.removeByValue('fq', 'content_type:job');
	   	Manager.store.removeByValue('fq', 'content_type:organization');
		Manager.store.addByValue('fq', 'content_type:opportunity');
	}else{
	   	Manager.store.removeByValue('fq', 'content_type:resume');
	   	Manager.store.removeByValue('fq', 'status:1');
	   	Manager.store.removeByValue('fq', 'content_type:job');
	   	Manager.store.removeByValue('fq', 'content_type:organization');
		Manager.store.addByValue('fq', 'content_type:opportunity');
	}
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

	Manager.addWidget(new AjaxSolr.CurrentSearchWidget({
	  id: 'currentsearch',
	  target_alt: '#removeall',
	  target: '#selection'
	}));

	Manager.addWidget(new AjaxSolr.SortByWidget({
	  id: 'sortbysearch',
	  target: '#sort'
	}));

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
	var field_tabs = [  'organization_name', 'content_type', 'position_type' ];
	for (var i = 0, l = field_tabs.length; i < l; i++) {
//console.log('*** tabswidget target: #'+field_tabs[i]);
	  Manager.addWidget(new AjaxSolr.TabsWidget({
		id: field_tabs[i],
		target: '#' + field_tabs[i],
		field: field_tabs[i]
	  }));
	}
//console.log('#srchmethod).text() is '+$('#srchmethod').val());	
	if($('#opps_content').length>0){
//console.log('opps_content - rows 100 '+$('#opps_content').length);
		Manager.store.addByValue('rows', 100);
	}else{// if($('#srchmethod').val().indexOf("City Vision Intern Applicant") == -1){
//console.log('NOT opps_content - rows 20 '+$('#opps_content').length);
		Manager.store.addByValue('rows', 20);
	}
    Manager.store.addByValue('sort', sortKey);

	var params = {
	  facet: true,
	  'facet.field': [ 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'city_tax', 'position_type', 'content_type', 'city_tax', 'primary_opp_type' , 'org_member_type', 'title', 'org_name', 'looking_for', 'total_giving', 'net_assets', 'assets', 'income', 'expenditures', 'geo_tax', 'organization_name', 'education_level', 'foundation_type', 'foundation_location'],
	  'facet.limit': 100,
	  'facet.mincount': 1,
	  'f.keyword.facet.limit': 50,
	  'f.country.facet.limit': -1,
	  'json.nl': 'map'
	};
	for (var name in params) {
	  Manager.store.addByValue(name, params[name]);
	}

	$('#num_opps').click(function() {
//console.log('379    self.manager.store.values is: ' + Manager.store.values('fq'));
   		Manager.store.removeByValue('fq', 'org_member_type:Foundation');
   		Manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
//console.log('382    self.manager.store.values is: ' + Manager.store.values('fq'));
   		Manager.store.addByValue('fq', 'num_opps:[1 TO *]');
//console.log('384    self.manager.store.values is: ' + Manager.store.values('fq'));
	
		// iterate through the fq's.  Set this as the new #link href for num_opps instead of what was there before
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
		$("#num_opps").attr("href", new_hashURL);
	});

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

	$('#all_user').click(function() {
//console.log('382    self.manager.store.values is: ' + Manager.store.values('fq'));
   		Manager.store.removeByValue('fq', 'full_user:true');
   		Manager.store.addByValue('fq', 'status:1');
//console.log('384    self.manager.store.values is: ' + Manager.store.values('fq'));
	
		// iterate through the fq's.  Set this as the new #link href for all_user instead of what was there before
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
		$("#all_user").attr("href", new_hashURL);
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
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search', 'country_tax', 'province_tax', 'region', 'city_tax', 'postal_code', 'city_tax', 'primary_opp_type', 'org_member_type', 'title', 'org_name', 'looking_for', 'education_level', 'net_assets', 'foundation_type', 'foundation_location' ]
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
	  fields: [ 'service_areas', 'great_for', 'benefits_offered', 'trip_length', 'service_areas_search', 'skills_search', 'benefits_offered_search' , 'title', 'org_name', 'looking_for', 'education_level'],
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
	fields = [ 'position_type', 'service_areas', 'great_for', 'frequency', 'benefits_offered', 'trip_length', 'country_tax', 'region', 'province_tax', 'denom_affil', 'org_affil', 'id', 'source', 'country', 'city_tax', 'primary_opp_type' , 'org_member_type', 'title', 'org_name', 'looking_for', 'total_giving', 'net_assets', 'assets', 'income', 'expenditures', 'org_member_type', 'geo_tax', 'education_level', 'foundation_type', 'foundation_location'];
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
