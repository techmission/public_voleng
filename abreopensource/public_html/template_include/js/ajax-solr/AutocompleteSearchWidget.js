(function ($) {
AjaxSolr.SearchWithAutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var q=$('#query').val('');
    var q_alt=$('#queryLoc').val('');
    var q_alternate=$('#srchmethod').val('');
	var geo_lat= $('#geo_lat').text();
	var geo_long= $('#geo_long').text();
	var d_default= $('#d_default').text();
    var self = this;
    // unautocomplete() below will unbind the keydown handler.
    $('#query').unbind().bind('keydown', function(e) {
      if (self.requestSent === false && e.which == 13) {
        var value = $(this).val();
        if( e.which == 13){
			$('#search_solr').click() ;
		}else if (value && self.add(value)) {
console.log('AutocompleteSearchWidget.js doRequest line 18');			
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
console.log('AutocompleteSearchWidget.js doRequest line 29');
          self.manager.doRequest(0);
        }
      }
    });
	
    var callback = function (response) {
      var list = [];
      var list_alt = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
        for (var facet in response.facet_counts.facet_fields[field]) {
			var field_name_reworded = field;
			var search_field_index = field_name_reworded.indexOf("_search");
			if(search_field_index != -1){
				field_name_reworded = '(keyword)';
			}else if(field=='service_areas'){
				field_name_reworded = '(Service Area)'
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
			}else if(field_alt=='metro_tax' || field=='city_tax'){
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
			self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value));
		}
      });
      $('#queryLoc').unautocomplete().autocomplete(list_alt, {
        formatItem: function(facet_alt) {
          return facet_alt.text;
        }
      }).result(function(e, facet_alt) {
        self.requestSent = true;
		if(facet_alt.field=='postal_code'){
console.log('facet_alt.field=postal_code');			
			self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
			$('div#postal').text(facet_alt.value);
			initialize(function(){
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
			});
		}else{
			
			if(facet_alt.field=='location'){
console.log('facet_alt line 131');			
				var b_isNotANumber = isNaN($('div#postal').text(facet_alt.value));
				if(b_isNotANumber==true){
					// is text, then	
					self.manager.store.addByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
				}else{
					self.manager.store.removeByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value));
console.log('facet_alt line 137');			
					$('div#postal').text(facet_alt.value);
					initialize(function(){
						geo_lat= $('#geo_lat').text();
						geo_long= $('#geo_long').text();
						d_default= $('#d_default').text();
						self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					});
				}
			}
			
			
		}
      });
   } // end callback

	var params;
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
// the following would make the counts reported in the autocomplete reflect the counts for that specific IP's geo location filter, rather than the total counts
//	params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}'); // makes autocomplete break w postal code
console.log('AutocompleteSearchWidget.js line 176');
console.log('jQuery.post'+self.manager.proxyUrl+' query: '+params.join('&'));
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
console.log(values)
		// clear filter parameters initially from previous searches
		
		
		if(query.length > 0 && already_searched==false 
		   && query != 'Service Areas, Skills' && query != 'Keyword' && query != 'Keywords (Service Areas, Skills)' && query != 'Country, Postal Code' && query != 'Country%2C%20Postal%20Code'){
			if(field=='keywords'){
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
					self.manager.store.removeByValue('fq', field + ':' + query);
					if(variables.length > 1){
						self.manager.store.addByValue('fq', field + ':(' + facet_values + ')');
					}else{
						self.manager.store.addByValue('fq', field + ':"' + facet_values + '"');
					}
				}
			}else{
				self.manager.store.addByValue('fq', field + ':"' + query + '"');
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
		   && query_alt != 'Country, Postal Code' && query_alt != 'Country%2C%20Postal%20Code'){
			var b_isNotANumber = isNaN(query_alt);
			if(b_isNotANumber==true){
				// is text, then	
				if(query_alt.length > 0){
					self.manager.store.addByValue('fq', field_alt + ':"' + query_alt + '"');
				}
			}else{
				self.manager.store.removeByValue('fq', field_alt + ':' + query_alt);
console.log('postal is ' + query_alt);			
console.log('REMOVE {!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');			
					self.manager.store.removeByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
				$('div#postal').text(query_alt); // $('div#postal').text(facet_alt.value);
				initialize(function(){
					geo_lat= $('#geo_lat').text();
					geo_long= $('#geo_long').text();
					d_default= $('#d_default').text();
console.log('ADD    {!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');			
					self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
				});
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
/*									 
		var fields = self.fields;
		var already_searched = false;
        var values = [];
		values = self.manager.store.values('fq');
		for(i=0; i < values.length; i++){
			var value = values[i];
			if(value.indexOf(query) != -1){
				already_searched=true;
			}
		}
		var conditions = '';
		var conditions_exist = false;
		if(already_searched === false && query != 'Service Areas, Skills'){
			for (var i = 0; i < self.fields.length; i++) {
				conditions_exist=true;
				conditions += self.fields[i] + ":" + query + " OR ";
			}
			if(conditions_exist==true){
				conditions = conditions.substring(0, conditions.length-4); // get rid of the trailing " OR "
				conditions = '(' + conditions + ')';
				self.manager.store.addByValue('fq', conditions);
			}
		}
*/		
									 
		initialize(function(){
			geo_lat= $('#geo_lat').text();
			geo_long= $('#geo_long').text();
			d_default= $('#d_default').text();
			params.push('fq={!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
console.log('AutocompleteSearchWidget.js doRequest line 322');
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
  }
});

})(jQuery);
