(function ($) {
AjaxSolr.SearchPageWithAutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
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
	
    var callback = function (response) {
      var list = [];
      var list_alt = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
        for (var facet in response.facet_counts.facet_fields[field]) {
          list.push({
            field: field,
            value: facet,
            text: facet + ' - ' + field
//            text: facet + ' (' + response.facet_counts.facet_fields[field][facet] + ') - ' + field
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
				text: facet_alt // - ' + field_alt
//				text: facet_alt + ' (' + response.facet_counts.facet_fields[field_alt][facet_alt] + ')'// - ' + field_alt
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
		if (self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))) {
			var new_URL_params = [];;
			new_URL_params.push('fq', facet.field, AjaxSolr.Parameter.escapeValue(facet.value));
			new_URL.push(new_URL_params);
	//          self.manager.doRequest(0);
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
			initialize(function(){
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					var new_URL_params = [];;
					new_URL_params.push('fq', null, '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
					new_URL.push(new_URL_params);
			});
		}else{
			if (self.manager.store.addByValue('fq', facet_alt.field + ':' + AjaxSolr.Parameter.escapeValue(facet_alt.value))) {
				if(facet_alt.field!='postal_code'){
					var new_URL_params = [];;
					new_URL_params.push('fq', facet_alt.field, AjaxSolr.Parameter.escapeValue(facet_alt.value));
					new_URL.push(new_URL_params);
		//          self.manager.doRequest(0);
				}
			}
		}
      });
   } // end callback

    var params = [ 'q=content_type:opportunity&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
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
		   && query != 'Service Areas, Skills' && query != 'Country, Postal Code' && query != 'Country%2C%20Postal%20Code'){
			self.manager.store.addByValue('fq', field + ':"' + query + '"');
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
		   && query_alt != 'Service Areas, Skills' && query_alt != 'Country, Postal Code' && query_alt != 'Country%2C%20Postal%20Code'){
			self.manager.store.addByValue('fq', field_alt + ':"' + query_alt + '"');
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
          self.manager.doRequest(0);
		});
 	
	// need to parse the following string and set as params to pass in the URL, etc: facet=true&q=content_type%3Aopportunity&rows=100&facet.field=position_type&facet.field=service_areas&facet.field=skills&facet.field=great_for&facet.field=frequency&facet.field=benefits_offered&facet.field=trip_length&facet.field=country_tax&facet.field=region&facet.field=province_tax&facet.field=denom_affil&facet.field=org_affil&facet.field=id&facet.field=source&facet.field=country&facet.limit=100&facet.mincount=1&f.keyword.facet.limit=50&f.country.facet.limit=-1&json.nl=map
	var new_URL_string = params.join('&');
	new_URL_string='oppsrch.do?method=processSolrSearchOpp&format=ajax-solr&'+new_URL_string;
	window.location = new_URL_string;
   });

    $('#query').val('Service Areas, Skills');
    $('#queryLoc').val('Country, Postal Code');
    $('#query').click(function(e) {
          $('#query').val('');
    });
    $('#queryLoc').click(function(e) {
          $('#queryLoc').val('');
    });
  }
});

})(jQuery);
