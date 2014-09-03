(function ($) {
AjaxSolr.SearchWithAutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
	  if (!window.console) console = {log: function() {}};
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('query is: ' + $('#query').val());
	//console.log('srchmethod is: ' + $('#srchmethod').val());
	var searched_location = false;
	var searched_geofilter = false;																			
    var q=$('#query').val();//('');
    var q_alt=$('#queryLoc').val();//('');
    var q_alternate=$('#srchmethod').val();//('');
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var d_default= $('#d_default').text();
	var new_URL = [];;
    var self = this;
	var subdomain = $('#subdomain').text();
	var search_postal_code = '';
	//console.log(subdomain);
//console.log('srchmethod is: ' + $('#srchmethod').val());

    $('#queryLoc').click(function(e) {
//console.log('line 682 triggered');
          $('#geo_lat').text('');
          $('#geo_long').text('');
//console.log('queryLoc text '+$('#queryLoc').text());
          $('#queryLoc').text('');
//console.log('queryLoc text '+$('#queryLoc').text());
//console.log('queryLoc val '+$('#queryLoc').val());
          $('#queryLoc').val('');
//console.log('queryLoc val '+$('#queryLoc').val());
//console.log('line 682 triggered');
          $('#queryLoc').addClass('active_watermark');
          $('#location').text('');
          $('#location').val('');
          $('#postal').text('');
          $('#postal').val('');
    });



	
    // unautocomplete() below will unbind the keydown handler.
    $('#query').unbind().bind('keydown', function(e) {
      if (self.requestSent === false && e.which == 13) {
        var value = $(this).val();
        if( e.which == 13){
			$('#search_solr').click() ;
		}else if (value && self.add(value)) {
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
          self.manager.doRequest(0);
        }
      }
    });
	
    var URL_hash_params = [ '' ];
    var params = [ 'q=content_type:opportunity&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	
	if($('#srchmethod').val() == 'Foundation'){
    	params = [ 'q=content_type:organization&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
		URL_hash_params = [ 'q=content_type:organization' ];
	}
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('query is: ' + $('#query').val());

	var callback = function (response) {
	//console.log('srchmethod is: ' + $('#srchmethod').val());
      var list = [];
      var list_alt = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
        var field_name = field;
        for (var facet in response.facet_counts.facet_fields[field]) {
			var field_name_reworded = '(keyword)';//field;
			var search_field_index = field_name_reworded.indexOf("_search");
			if(search_field_index != -1){
				field_name_reworded = '(keyword)';
			}else if(field=='service_areas'){
				field_name_reworded = '(Service Area)'
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
			}else if(field=='looking_for'){
				field_name_reworded = '(Looking For)'
			}else if(field=='position_type'){
				field_name_reworded = '(Position Type)'
			}else if(field=='benefits_offered'){
				field_name_reworded = '(Benefits Offered)'
			}else if(field=='skills'){
				field_name_reworded = '(Skill)'
			}else{
				field_name_reworded = '(keyword)';
			}
          list.push({
            field: field_name,
            value: facet,
            text: facet + ' - ' + field_name_reworded
          });
        }
      }
      for (var i = 0; i < self.fields_alt.length; i++) {
        var field_alt = self.fields_alt[i];
        for (var facet_alt in response.facet_counts.facet_fields[field_alt]) {
			if(field_alt=='postal_code'){
			  list_alt.push({
				field: field_alt,
				value: facet_alt,
				text: facet_alt
			  });
			}else{
			  list_alt.push({
				field: field_alt,
				value: facet_alt,
				text: facet_alt 
			  });
			}
        }
      }
	  // add "Virtual" as an option in location
	  list_alt.push({
		field: 'position_type',
		value: 'Virtual Volunteering (from home)',
		text: 'Virtual'
	  });
      self.requestSent = false;
      $('#query').unautocomplete().autocomplete(list, {
        formatItem: function(facet) {
          return facet.text;
        }
      }).result(function(e, facet) {
        self.requestSent = true;
		if(facet.field==''){
			var facet_data = facet.value;
			var variables = facet_data.split(',');
			var facet_value;
			if (variables.length > 1) {
				// Variables present in hash
				for (i = 1; i < variables.length; i++) {
					var facet_value = variables[i];
					var index_keyword = facet_value.indexOf('Keyword (Service Areas');
					var index_keyword2 = facet_value.indexOf('Skills)');
					if(index_keyword==-1 && index_keyword2!=0){
						var index = facet_value.indexOf(' '); // handle a space after a comma
						if(index == 0){
							facet_value = facet_value.substring(1);
						}
						if (self.manager.store.addByValue('fq', 'keywords:' + facet_value)) {
							params.push('fq=keywords:' + facet_value);
							URL_hash_params.push('fq=keywords:' + facet_value);
						}
					}
				}
			}
			self.manager.store.removeByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))
		}else if(facet.field=='keywords'){
			var facet_data = facet.value;
			var variables = facet_data.split(',');
			var facet_value;
			if (variables.length > 1) {
				// Variables present in hash
				for (i = 1; i < variables.length; i++) {
					var facet_value = variables[i];
					var index_keyword = facet_value.indexOf('Keyword (Service Areas');
					var index_keyword2 = facet_value.indexOf('Skills)');
					if(index_keyword==-1 && index_keyword2!=0){
						var index = facet_value.indexOf(' '); // handle a space after a comma
						if(index == 0){
							facet_value = facet_value.substring(1);
						}
						if (self.manager.store.addByValue('fq', facet.field + ':' + facet_value)) {
							params.push('fq=' + facet.field + ':' + facet_value);
							URL_hash_params.push('fq=' + facet.field + ':' + facet_value);
						}
					}
				}
			}
			self.manager.store.removeByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))
		}else{
			var index_keyword = facet.value.indexOf('Keyword (Service Areas'); 
			var index_keyword2 = facet.value.indexOf('Skills)');
			if(index_keyword==-1 && index_keyword2!=0){
				if (self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))) {
					params.push('fq=' + facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value));
					URL_hash_params.push('fq=' + facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value));
				}
			}
		}
      });
//console.log('URL_hash_params is '+URL_hash_params);  
//console.log('queryLoc is: ' + $('#queryLoc').val());
      $('#queryLoc').unautocomplete().autocomplete(list_alt, {
        formatItem: function(facet_alt) {
          return facet_alt.text;
        }
      }).result(function(e, facet_alt) {
        self.requestSent = true;
		if(facet_alt.field=='postal_code'){
			self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
			$('div#postal').text(facet_alt.value);
//console.log('postal is: ' + $('div#postal').text());
//console.log('location is: ' + $('div#location').text());
			$('div#location').text(facet_alt.value);
//console.log('location is: ' + $('div#location').text());
			initialize(function(){
//console.log('initialize triggered');
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				if(geo_lat.length > 0){
					self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					if(searched_geofilter==false){
//console.log('push geo');
						URL_hash_params.push('postal_code=' + facet_alt.value + '');
//						URL_hash_params.push('fq=~|!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '|~');
						URL_hash_params.push('fq={geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
//console.log('URL_hash_params is '+URL_hash_params);
					}
					searched_geofilter=true;
				}
			});
		}else{
//console.log('location is: ' + $('div#location').text());
			var b_isNotANumber = isNaN(facet_alt.value);
			if(b_isNotANumber==true){
				if (self.manager.store.addByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value))) {
					if(facet_alt.field!='postal_code'){
//console.log('location is: ' + $('div#location').text());
//console.log('fq=' + facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
						params.push('fq=' + facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
						URL_hash_params.push('fq=' + facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
						searched_location=true;
//console.log('URL_hash_params is '+URL_hash_params);
					}
				}
			}else{
				self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
			$('div#postal').text(facet_alt.value);
//console.log('postal is: ' + $('div#postal').text());
//console.log('location is: ' + $('div#location').text());
				$('div#location').text(facet_alt.value);
//console.log('location is: ' + $('div#location').text());
				initialize(function(){
					geo_lat= $('#geo_lat').text();
					geo_long= $('#geo_long').text();
					d_default= $('#d_default').text();
					if(geo_lat.length > 0){
//console.log('push geo');
						self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						if(searched_geofilter==false){
							URL_hash_params.push('postal_code=' + facet_alt.value + '');
//							URL_hash_params.push('fq=~|!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '|~');
							URL_hash_params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
							searched_location=true;
//console.log('URL_hash_params is '+URL_hash_params);
						}
						searched_geofilter=true;
					}
				});
			}
		}
      });
   } // end callback

//console.log('q_alternate is: ' + q_alternate);
//console.log('params is: ' + params);
	if(q_alternate=='Foundation'){
    	params = [ 'q=content_type:organization&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='organization'){
    	params = [ 'q=content_type:organization&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='job'){
    	params = [ 'q=content_type:job&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else if(q_alternate=='resume'){
    	params = [ 'q=content_type:resume&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else{
		params = [ 'q=content_type:opportunity&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}
    for (var i = 0; i < this.fields.length; i++) {
      params.push('facet.field=' + this.fields[i]);
//console.log('298  facet.field IS ' + this.fields[i]);
    }
    for (var i = 0; i < this.fields_alt.length; i++) {
      params.push('facet.field=' + this.fields_alt[i]);
    }
//console.log('params is: ' + params);
	
//console.log('q_alternate is: ' + q_alternate);
    if (this.manager.proxyUrl) {
		initialize(function(){
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
			jQuery.post(self.manager.proxyUrl, { query: params.join('&') }, callback, 'json');
		});
    }
    else {
      jQuery.getJSON(this.manager.solrUrl + 'select?' + params.join('&') + '&wt=json&json.wrf=?', {}, callback);
    }
	
    $('#search_solr').click(function(e, facet) {
		if( $('#queryLoc').val().indexOf('Country, Postal Code') != -1 || $('#queryLoc').val().length < 1 ){
		}else if( $('#queryLoc').val().indexOf(' (or Country)') != -1 ){
			var indexCountry = $('#queryLoc').val().indexOf(' (or Country)');
			var loc = $('#queryLoc').val();
			loc = loc.substr(0,indexCountry);
			$('#location').text(loc);
		}else if( $('#queryLoc').val().indexOf(' (zip code)') != -1 ){
//console.log('triggered');			
			var indexZIPCode = $('#queryLoc').val().indexOf(' (zip code)');
			var loc = $('#queryLoc').val();
			loc = loc.substr(0,indexZIPCode);
			$('#location').text(loc);
		}else{
			$('#location').text($('#queryLoc').val());
		}
//console.log('location is: ' + $('div#location').text());
		var query_alternate = $('#srchmethod').val();		
// *									 
//console.log('302 $(\'#query\').val() is '+$('#query').val());
		// if the user checked one of the checkboxes, add it to the URL params
		if($('#query').val().indexOf('Great for Families') != -1){
			URL_hash_params.push('fq=great_for:"Great for Families"');
		}else if($('#query').val().indexOf('Orphanage') != -1){
			URL_hash_params.push('fq=service_areas:"Orphanage"');
		}else if($('#query').val().indexOf('Computers and Technology') != -1){
			URL_hash_params.push('fq=service_areas:"Computers and Technology"');
		}else if($('#query').val().indexOf('Health and Medicine') != -1){
			URL_hash_params.push('fq=service_areas:"Health and Medicine"');
		}else if($('#query').val().indexOf('Disaster / Global Relief') != -1){
			URL_hash_params.push('fq=service_areas:"Disaster / Global Relief"');
		}else if($('#query').val().indexOf('Disaster Relief') != -1){
			URL_hash_params.push('fq=service_areas:"Disaster Relief"');
		}else if($('#query').val().indexOf('Hurricane Sandy') != -1){
			URL_hash_params.push('fq=service_areas:"Hurricane Sandy"');
		}
		if($('#position_type_virt').attr('checked')){
			URL_hash_params.push('fq=position_type:"Virtual Volunteering (from home)"');
			$('#queryLoc').val('');
		}
		if($('#position_type_stm').attr('checked')){
			URL_hash_params.push('fq=position_type:"Short-term Missions / Volunteer Internship"');
		}
		if($('#content_type_job').attr('checked')){
			URL_hash_params.push('fq=content_type:job');
		}
		if($('#content_type_resume').attr('checked')){
			URL_hash_params.push('fq=content_type:resume');
			URL_hash_params.push('fq=full_user:true');
		}
		if($('#content_type_org').attr('checked')){
			URL_hash_params.push('fq=content_type:organization');
		}
//*/		
		if($('#denom_affil_catholic').attr('checked')){
			URL_hash_params.push('fq=denom_affil:Catholic');
		}
//console.log('URL_hash_params is '+URL_hash_params);
		
		var field = self.field;
		var query = $('#query').val();
		var index = query.indexOf(' - ');
		if(index != -1){
			query = query.substring(0, index); // get the subquery, without the name of the facet field, etc, if given
		}
//console.log('query is '+query);
		var already_searched = false;
        var values = [];
		values = self.manager.store.values('fq');
//console.log('values is '+values);
		for(i=0; i < values.length; i++){
			var value = values[i];
//console.log('value is '+value);
			if(value.indexOf(query) != -1){
				already_searched=true;
			}
		}
/*
console.log('URL_hash_params is '+URL_hash_params);//
console.log('query is '+query);
console.log('URL_hash_params.indexOf(query) is '+URL_hash_params.indexOf(query));
console.log('URL_hash_params.indexOf(query) is '+URL_hash_params.indexOf(query));
console.log(',fq=service_areas:"Disaster / Global Relief".indexOf(\'Disaster / Global Relief\') is '+',fq=service_areas:"Disaster / Global Relief"'.indexOf('Disaster / Global Relief'));
*/
//console.log('361 URL_hash_params is ||'+URL_hash_params + '||');//
//console.log('362 query is ||'+query + '||');//
//console.log('363 already_searched is '+already_searched);//
		if( URL_hash_params.length > 1){
//console.log('363 URL_hash_params.length is '+URL_hash_params.length);//
			if("'"+URL_hash_params+"'".indexOf("'"+query+"'") != -1){
				already_searched=true;
			}
		}
//console.log('367 already_searched is '+already_searched);//
		if(query.length > 0 && already_searched==false 
		   && query != 'Service Areas, Skills' && query != 'Keyword' && query != 'Keywords (Service Areas, Skills)' && query != 'Country, Postal Code' && query != 'Country%2C%20Postal%20Code' && query != 'Funding Interests, Keywords' && query_alt != 'Location (Country, Zipcode)' && query_alt != 'Location%20(Country,%20Zipcode)' ){
//console.log('370 field is '+field);//
			if(field==''){
				var facet_data = query;
				var variables = facet_data.split(',');
				var facet_value;
				if (variables.length > 0) {
					// Variables present in hash
					for (i = 0; i < variables.length; i++) {
						var facet_value = variables[i];
						var index = facet_value.indexOf(' '); // handle a space after a comma
						if(index == 0){
							facet_value = facet_value.substring(1);
						}
						self.manager.store.addByValue('fq', field + ':' + facet_value);
						var index_keyword = facet_value.indexOf('Keyword (Service Areas'); 
						var index_keyword2 = facet_value.indexOf('Skills)');
//console.log('facet_value is '+facet_value);
//console.log('index_keyword2 is '+index_keyword2);
						if(index_keyword==-1 && index_keyword2!=0){
							URL_hash_params.push('fq=keywords:' + facet_value);
//console.log('URL_hash_params is '+URL_hash_params);
						}
					}
				}
				self.manager.store.removeByValue('fq', 'keywords:' + query);
			}else if(field=='keywords'){
				var facet_data = query;
				var variables = facet_data.split(',');
				var facet_value;
				if (variables.length > 0) {
					// Variables present in hash
					for (i = 0; i < variables.length; i++) {
						var facet_value = variables[i];
						var index = facet_value.indexOf(' '); // handle a space after a comma
						if(index == 0){
							facet_value = facet_value.substring(1);
						}
						self.manager.store.addByValue('fq', field + ':' + facet_value);
						var index_keyword = facet_value.indexOf('Keyword (Service Areas'); 
						var index_keyword2 = facet_value.indexOf('Skills)');
//console.log('facet_value is '+facet_value);
//console.log('index_keyword2 is '+index_keyword2);
						if(index_keyword==-1 && index_keyword2!=0){
							URL_hash_params.push('fq=' + field + ':' + facet_value);
//console.log('URL_hash_params is '+URL_hash_params);
						}
					}
				}
				self.manager.store.removeByValue('fq', field + ':' + query);
			}else{
				self.manager.store.addByValue('fq', field + ':"' + query + '"');
				var index_keyword = query.indexOf('Keyword (Service Areas'); 
				var index_keyword2 = query.indexOf('Skills)');
				if(index_keyword==-1 && index_keyword2!=0){
					URL_hash_params.push('fq=' + field + ':"' + query + '"');
//console.log('URL_hash_params is '+URL_hash_params);
				}
				if( field == 'location' || field == 'city_tax' || field == 'country_tax' || field == 'region' || field == 'province_tax' || field == 'city' || field == 'country' ){
					searched_location = true;
				}
			}
		}
		
		var field_alt = self.field_alt; // 'location'; 
		var query_alt = $('#queryLoc').val();
//console.log('URL_hash_params is '+URL_hash_params);//
//console.log('queryLoc is: ' + $('#queryLoc').val());
		var index_alt = query_alt.indexOf(' - ');
		if(index_alt != -1){
			query_alt = query_alt.substring(0, index_alt); // get the subquery, without the name of the facet field, etc, if given
		}
		var already_searched_alt = false;
        var values_alt = [];
		values_alt = self.manager.store.values('fq');
		for(i=0; i < values_alt.length; i++){
			var value = values_alt[i];
			if(value.indexOf(query_alt) != -1){
				already_searched_alt=true;
			}
		}
//console.log('URL_hash_params is '+URL_hash_params);
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('location is: ' + $('div#location').text());
//console.log('query_alt.length is: ' + query_alt.length);
		if(query_alt.length > 0 && already_searched_alt==false 
		   && query_alt != 'Service Areas, Skills' && query != 'Keyword' && query_alt != 'Keywords (Service Areas, Skills)' && query_alt != 'Country, Postal Code' && query_alt != 'Country%2C%20Postal%20Code' && query != 'Funding Interests, Keywords' && query_alt != 'Location (Country, Zipcode)' && query_alt != 'Location%20(Country,%20Zipcode)' ){
				if( query_alt.indexOf(' (zip code)') != -1 ){
//console.log('triggered');			
					var indexZIPCodeAlt = query_alt.indexOf(' (zip code)');
					var locAlt = query_alt;
					locAlt = locAlt.substr(0,indexZIPCodeAlt);
					query_alt=locAlt;
				}else if( query_alt.indexOf(' (or Country)') != -1 ){
					var indexCountryAlt = query_alt.indexOf(' (or Country)');
//		console.log('indexCountryAlt is: '+indexCountryAlt);			
					var locAlt = query_alt;
//		console.log('locAlt is: '+locAlt);			
					locAlt = locAlt.substr(0,indexCountryAlt);
//		console.log('locAlt is: '+locAlt);			
					query_alt=locAlt;
				}
				var b_isNotANumber = isNaN(query_alt);
//console.log('location is: ' + $('div#location').text());
				if(b_isNotANumber==true){
					// is text, then	
					if(query_alt.length > 0){
						self.manager.store.addByValue('fq', field_alt + ':"' + query_alt + '"');
						URL_hash_params.push('fq=' + field_alt + ':"' + query_alt + '"');
						searched_location = true;
//console.log('URL_hash_params is '+URL_hash_params);
//console.log('location is: ' + $('div#location').text());
					}
				}else{
					self.manager.store.removeByValue('fq', field_alt + ':' + query_alt);
					if(field_alt != 'postal_code'){
//console.log('query_alt postal is: ' + query_alt);						
						$('div#postal').text(query_alt); // $('div#postal').text(facet_alt.value);
						$('div#location').text(query_alt);
						initialize(function(){
							geo_lat= $('#geo_lat').text();
							geo_long= $('#geo_long').text();
							d_default= $('#d_default').text();
	//console.log(geo_lat + ' ' + geo_long);						
				if(geo_lat.length > 0){
//console.log('push geo');
							self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
							if(searched_geofilter==false){
	//console.log('searched_geofilter==false 312');
								URL_hash_params.push('postal_code=' + query_alt + '');
//								URL_hash_params.push('fq=~|!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '|~');
								URL_hash_params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
								searched_location = true;
//console.log('URL_hash_params is '+URL_hash_params);
							}
							searched_geofilter=true;
				}
					});
					}
				}
		}
		
//console.log('URL_hash_params is '+URL_hash_params);
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('location is: ' + $('div#location').text());
		if(query_alternate=='Foundation'){
			self.manager.store.removeByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
			self.manager.store.addByValue('fq', 'org_member_type:Foundation');
			URL_hash_params.push('fq=content_type:organization&fq=org_member_type:Foundation');//&fq=-hidden_source:irs_parachurch');
		}else if(query_alternate=='organization'){
			self.manager.store.removeByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
			self.manager.store.addByValue('fq', '-org_member_type:Foundation');
			URL_hash_params.push('fq=content_type:organization&fq=-org_member_type:Foundation');//&fq=-hidden_source:irs_parachurch');
		}else if(query_alternate=='City Vision' || query_alternate=='City Vision Internship'){
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.addByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
			URL_hash_params.push('fq=intern_type:"City Vision Internship"');
		}else if(query_alternate=='job'){
			self.manager.store.removeByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'content_type:job');
			URL_hash_params.push('fq=content_type:job');
		}else if(query_alternate=='resume'){
			self.manager.store.removeByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.addByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'full_user:true');
			URL_hash_params.push('fq=content_type:resume');
			URL_hash_params.push('fq=full_user:true');
		}else if(query_alternate=='Short-term Missions / Volunteer Internship'){
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');

			self.manager.store.removeByValue('fq', 'content_type:resume');
			URL_hash_params.push('fq=content_type:opportunity');
			URL_hash_params.push('fq=position_type:"Short-term Missions / Volunteer Internship"');
		}else if(query_alternate=='Virtual Volunteering (from home)'){
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			URL_hash_params.push('fq=content_type:opportunity');
			URL_hash_params.push('fq=position_type:"Virtual Volunteering (from home)"');
		}else{
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.addByValue('fq', 'content_type:opportunity');
			URL_hash_params.push('fq=content_type:opportunity');
		}
//console.log('URL_hash_params is '+URL_hash_params);
//console.log('this.manager.store.values is: ' + this.manager.store.values('fq'));
//console.log('URL_hash_params is '+URL_hash_params);
//console.log('new_URL_hash_string is '+new_URL_hash_string);
	
		initialize(function(){
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('location is: ' + $('div#location').text());
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
			var URL_hash_string = URL_hash_params.join('&');
//console.log('URL_hash_string is '+URL_hash_string);
			var virtual_search_index = URL_hash_string.indexOf('Virtual Volunteering');
//console.log('virtual search index is: ' + virtual_search_index);			


//console.log('location is: ' + $('div#location').text());
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log(' is NaN queryLoc is: ' + isNaN($('#queryLoc').val()));
			if(!$('#queryLoc').val()==''){
				if(isNaN($('#queryLoc').val())==false){// || $('#queryLoc').val() == 'Country, Postal Code' || $('#queryLoc').val() == 'Country%2C%20Postal%20Code'){
					if(geo_lat.length > 0 && virtual_search_index == -1){
	//console.log('push geo');
						params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						if(searched_geofilter==false){
//						URL_hash_params.push('fq=~|!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '|~');
							URL_hash_params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
//console.log('URL_hash_params is '+URL_hash_params);
						}
						searched_geofilter=true;
					}
				}else if($('div#location').text().length>0 && searched_location == false){
//console.log('location is: ' + $('div#location').text());
					URL_hash_params.push('fq=location:"' + $('div#location').text() + '"' );
//console.log('URL_hash_params is '+URL_hash_params);
				}
			}


			
//console.log('URL_hash_params is '+URL_hash_params);
			search_postal_code = $('#queryLoc').val();
//console.log('search_postal_code is '+search_postal_code);
			b_isNotANumber = isNaN(search_postal_code);
			if(b_isNotANumber == true){
				search_postal_code = '';
			}else{
				search_postal_code = '&postal_code='+search_postal_code;
			}
//console.log('search_postal_code is '+search_postal_code);
			var new_URL_hash_string = URL_hash_params.join('&');
			var hash_length=new_URL_hash_string.length;
			if(hash_length>0){
				if($('#srchmethod').val() == 'Foundation'){
					new_URL_hash_string = '&' + new_URL_hash_string.substring(0,hash_length) + '#' + new_URL_hash_string.substring(0,hash_length);
				}else{
					new_URL_hash_string = '&' + new_URL_hash_string.substring(1,hash_length) + '#' + new_URL_hash_string.substring(1,hash_length);
				}
			}
			if(subdomain.indexOf('ChurchVolunteering.org')!=-1){
				new_URL_hash_string='http://www.churchvolunteering.org/oppsrch.do?method=processSearch'+search_postal_code+new_URL_hash_string;
			}else{
				new_URL_hash_string='/oppsrch.do?method=processSearch'+search_postal_code+new_URL_hash_string;
			}
	
//console.log('new_URL_hash_string is '+new_URL_hash_string);
	var portal_url = '';
	if($('#portal_name').length > 0)	portal_url = $('#portal_name').html();
//console.log('portal_url is '+portal_url);
	if(portal_url.length>0)	new_URL_hash_string = '/'+portal_url + new_URL_hash_string;
	if(portal_url.indexOf('cityvision') != -1)	new_URL_hash_string = new_URL_hash_string + '&fq=intern_type:%22City%20Vision%20Internship%22';

//console.log('URL_hash_params is '+URL_hash_params);
console.log('new_URL_hash_string is '+new_URL_hash_string);
			self.manager.doRequest(0);
//			if($('#srchmethod').val() == 'Foundation'){}else{
				window.location = new_URL_hash_string;
//			}
		});
   });

//console.log('query is: ' + $('#query').val());
	if($('#query').val().length < 1){
    	$('#query').val('Keywords (Service Areas, Skills)');
	}
//console.log('queryLoc is: ' + $('#queryLoc').val());
	var queryLoc = $('#queryLoc').val();
	if( queryLoc.indexOf("Country, Postal Code") != -1 || queryLoc.length < 1 ){
		$('#queryLoc').val($('#location').text());
	}else{
		$('#location').text(queryLoc);
	}
//console.log('location is: ' + $('div#location').text());
//console.log('queryLoc is: ' + $('#queryLoc').val());
//console.log('query is: ' + $('#query').val());
    $('#oppsearch').addClass('solr');
    $('#query').click(function(e) {
          $('#query').val('');
          $('#query').addClass('active_watermark');
    });
    $('#queryLoc').click(function(e) {
//console.log('line 682 triggered');
          $('#geo_lat').text('');
          $('#geo_long').text('');
//console.log('queryLoc text '+$('#queryLoc').text());
          $('#queryLoc').text('');
//console.log('queryLoc text '+$('#queryLoc').text());
//console.log('queryLoc val '+$('#queryLoc').val());
          $('#queryLoc').val('');
//console.log('queryLoc val '+$('#queryLoc').val());
//console.log('line 682 triggered');
          $('#queryLoc').addClass('active_watermark');
          $('#location').text('');
          $('#location').val('');
          $('#postal').text('');
          $('#postal').val('');
    });
  }
});

})(jQuery);
