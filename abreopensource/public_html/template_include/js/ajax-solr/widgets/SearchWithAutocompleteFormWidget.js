(function ($) {
AjaxSolr.SearchWithAutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var q=$('#query').val('');
    var q_alt=$('#queryLoc').val('');
    var q_alternate=$('#srchmethod').val('');
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var d_default= $('#d_default').text();
	var new_URL = [];;
    var self = this;
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

	var callback = function (response) {
      var list = [];
      var list_alt = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
        var field_name = field;
        for (var facet in response.facet_counts.facet_fields[field]) {
			var field_name_reworded = field;
			var search_field_index = field.indexOf("_search");
			if(search_field_index != -1){
				field_name = 'keywords';
				field_name_reworded = '(keyword)';
			}else if(field=='service_areas'){
				field_name_reworded = '(Service Area)'
			}else if(field=='skills'){
				field_name_reworded = '(Skill)'
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

      self.requestSent = false;
      $('#query').unautocomplete().autocomplete(list, {
        formatItem: function(facet) {
          return facet.text;
        }
      }).result(function(e, facet) {
        self.requestSent = true;
		if(facet.field=='keywords'){
			var facet_data = facet.value;
			var variables = facet_data.split(',');
			var facet_value;
			if (variables.length > 1) {
				// Variables present in hash
				for (i = 1; i < variables.length; i++) {
					var facet_value = variables[i];
					var index = facet_value.indexOf(' '); // handle a space after a comma
					if(index == 0){
						facet_value = facet_value.substring(1);
					}
					if (self.manager.store.addByValue('fq', facet.field + ':' + facet_value)) {
						params.push('fq=' + facet.field + ':' + facet_value);
						URL_hash_params.push(facet.field + '=' + facet_value);
					}
				}
			}
			self.manager.store.removeByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))
		}else{
			if (self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))) {
				params.push('fq=' + facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value));
				URL_hash_params.push(facet.field + '=' + AjaxSolr.Parameter.escapeValue(facet.value));
			}
		}
      });
      $('#queryLoc').unautocomplete().autocomplete(list_alt, {
        formatItem: function(facet_alt) {
          return facet_alt.text;
        }
      }).result(function(e, facet_alt) {
        self.requestSent = true;
		if(facet_alt.field=='postal_code'){
			self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
			$('div#postal').text(facet_alt.value);
			$('div#location').text(facet_alt.value);
			initialize(function(){
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
				params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
				URL_hash_params.push('fq=:{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						URL_hash_params.push('postal_code=' + facet_alt.value + '');
			});
		}else{
			var b_isNotANumber = isNaN(facet_alt.value);
			if(b_isNotANumber==true){
				if (self.manager.store.addByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value))) {
					if(facet_alt.field!='postal_code'){
						params.push('fq=' + facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
						URL_hash_params.push(facet_alt.field + '=' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
					}
				}
			}else{
//alert('call postal');				
				self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
				$('div#postal').text(facet_alt.value);
				$('div#location').text(facet_alt.value);
				initialize(function(){
					geo_lat= $('#geo_lat').text();
					geo_long= $('#geo_long').text();
					d_default= $('#d_default').text();
					self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					URL_hash_params.push('fq=:{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						URL_hash_params.push('postal_code=' + facet_alt.value + '');
				});
			}
		}
      });
   } // end callback

	if(q_alternate=='organization'){
    	params = [ 'q=content_type:organization&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}else{
		params = [ 'q=content_type:opportunity&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
	}
    for (var i = 0; i < this.fields.length; i++) {
      params.push('facet.field=' + this.fields[i]);
    }
    for (var i = 0; i < this.fields_alt.length; i++) {
      params.push('facet.field=' + this.fields_alt[i]);
    }
	
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
		var field = self.field;
		var query = $('#query').val();
		var index = query.indexOf(' - ');
		if(index != -1){
			query = query.substring(0, index); // get the subquery, without the name of the facet field, etc, if given
		}
		var already_searched = false;
        var values = [];
		values = self.manager.store.values('fq');
		for(i=0; i < values.length; i++){
			var value = values[i];
			if(value.indexOf(query) != -1){
				already_searched=true;
			}
		}
		if(query.length > 0 && already_searched==false 
		   && query != 'Service Areas, Skills' && query != 'Keyword' && query != 'Keyword (Service Areas, Skills)' && query != 'Country, Postal Code' && query != 'Country%2C%20Postal%20Code'){
			if(field=='keywords'){
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
						URL_hash_params.push(field + '=' + facet_value);
					}
				}
				self.manager.store.removeByValue('fq', field + ':' + query);
			}else{
				self.manager.store.addByValue('fq', field + ':"' + query + '"');
				URL_hash_params.push(field + '="' + query + '"');
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
		for(i=0; i < values_alt.length; i++){
			var value = values_alt[i];
			if(value.indexOf(query_alt) != -1){
				already_searched_alt=true;
			}
		}
		if(query_alt.length > 0 && already_searched_alt==false 
		   && query_alt != 'Service Areas, Skills' && query != 'Keyword' && query_alt != 'Keyword (Service Areas, Skills)' && query_alt != 'Country, Postal Code' && query_alt != 'Country%2C%20Postal%20Code'){
				var b_isNotANumber = isNaN(query_alt);
				if(b_isNotANumber==true){
					// is text, then	
					if(query_alt.length > 0){
						self.manager.store.addByValue('fq', field_alt + ':"' + query_alt + '"');
						URL_hash_params.push(field_alt + '="' + query_alt + '"');
					}
				}else{
					self.manager.store.removeByValue('fq', field_alt + ':' + query_alt);
					$('div#postal').text(query_alt); // $('div#postal').text(facet_alt.value);
					$('div#location').text(query_alt);
						geo_lat= $('#geo_lat').text();
						geo_long= $('#geo_long').text();
						d_default= $('#d_default').text();
						self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						URL_hash_params.push('fq=:{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
						URL_hash_params.push('postal_code=' + query_alt + '');
				}
		}
		
		var query_alternate = $('#srchmethod').val();		
		if(query_alternate=='organization'){
			self.manager.store.removeByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('q', '*:*');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.addByValue('fq', 'content_type:organization');
		}else if(query_alternate=='Short-term Missions / Volunteer Internship'){
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
		}else{
			self.manager.store.removeByValue('q', '*:*');
			self.manager.store.addByValue('q', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.addByValue('fq', 'content_type:opportunity');
		}
		
		initialize(function(){
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
			params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
			URL_hash_params.push('fq=:{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');

console.log('searchform doRequest');			
			self.manager.doRequest(0);
		});
 	
	// need to parse the following string and set as params to pass in the URL, etc: facet=true&q=content_type%3Aopportunity&rows=100&facet.field=position_type&facet.field=service_areas&facet.field=skills&facet.field=great_for&facet.field=frequency&facet.field=benefits_offered&facet.field=trip_length&facet.field=country_tax&facet.field=region&facet.field=province_tax&facet.field=denom_affil&facet.field=org_affil&facet.field=id&facet.field=source&facet.field=country&facet.limit=100&facet.mincount=1&f.keyword.facet.limit=50&f.country.facet.limit=-1&json.nl=map
//	var new_URL_string = URL_hash_params.join('#');
	var new_URL_hash_string = URL_hash_params.join('&');
	var hash_length=new_URL_hash_string.length;
	if(hash_length>0){
		new_URL_hash_string = '#' + new_URL_hash_string.substring(1,hash_length);
	}
	new_URL_hash_string='oppsrch.do?method=processSolrSearchOpp'+new_URL_hash_string;
//	alert(new_URL_string);
	window.location = new_URL_string;
   });

    $('#query').val('Keyword (Service Areas, Skills)');
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
  }
});

})(jQuery);
