(function ($) {
// Internet Explorer doesn't know about the console variable, so it needs to be defined.				
// see http://stackoverflow.com/questions/3326650/console-is-undefined-error-for-internet-explorer
if (!window.console) console = {log: function() {}};					 
AjaxSolr.SearchFromParamsWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var self = this;
	var params_set=false;
    var q=$('#keyword_search').text();
    var fq=$('#fq_search').text();
	
	var postal_data = '';
	  
	var keywords_params = [];

//console.log(window.location.hash);
//	var variables = window.location.hash.split('#');
	var variables = window.location.hash.split('&');
	if (fq.length > 1) {
		// Variables present in hash
		params_set=true;
	}
	  
    var params = 'rows=0&facet=true&facet.field=position_type&facet.field=service_areas&facet.field=skills&facet.field=great_for&facet.field=frequency&facet.field=benefits_offered&facet.field=trip_length&facet.field=country_tax&facet.field=region&facet.field=province_tax&facet.field=denom_affil&facet.field=org_affil&facet.field=id&facet.field=source&facet.field=country&facet.limit=100&facet.mincount=1&f.keyword.facet.limit=-1&json.nl=map';
	if(q.length > 0){
		params_set=true;
		params += '&' + q;
	}
    $('#search_solr_params').click(function(e, facet) {
		if (self.manager.proxyUrl) {
			initialize(function(){
				jQuery.post(self.manager.proxyUrl, { query: params }, callback, 'json');
			});
		}
		else {
		  jQuery.getJSON(this.manager.solrUrl + 'select?' + params + '&wt=json&json.wrf=?', {}, callback);
		}
		
		var facet_data;
		if (variables.length > 0) {
			// Variables present in hash
			for (i = 0; i < variables.length; i++) {
				var parse_data = variables[i];
				if(i==0){
					var hash_index = parse_data.indexOf("#");
					if(hash_index != -1){
						parse_data = parse_data.substring(1,parse_data.length);
					}
				}
//console.log('parse_data #' +i+ ' ' +parse_data);				
				var keyValuePair = parse_data.split('=');
				if (keyValuePair[0] == 'fq') {
					var geo_filter = parse_data.split(':');
					facet_data = unescape(geo_filter[1]);
//					self.manager.store.addByValue('fq', facet_data);
				}else if (keyValuePair[0] == 'postal_code') {
					facet_data = unescape(keyValuePair[1]);
					try{
						facet_data = facet_data.substring(0,5);
					}catch(e){}
					$('#postal').text(facet_data);
					postal_data = facet_data;
				}else if (keyValuePair[0] == 'position_type') {
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', 'position_type:' + facet_data);
				}else if (keyValuePair[0] == 'service_areas') {
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', 'service_areas:' + facet_data);
				}else if (keyValuePair[0] == 'skills') {
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', 'skills:' + facet_data);
				}else if (keyValuePair[0] == 'keywords') {
					// will have to be able to handle multiple input here; should be OR'ed, rather than the default AND
					facet_data = unescape(keyValuePair[1]);
					keywords_params.push(facet_data);
					//self.manager.store.addByValue('fq', 'keywords:' + facet_data);
				}else if (keyValuePair[0] == 'location') {
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', 'location:' + facet_data);
				}else{
					facet_data = unescape(keyValuePair[1]);
					self.manager.store.addByValue('fq', keyValuePair[0] + ':' + facet_data);
				}
			}
			
			var keywords_search_string = '';
			if(keywords_params.length>0){
				keywords_search_string = keywords_params.join(' OR ');
				if(keywords_params.length>1){
					keywords_search_string = '(' + keywords_search_string + ')';
				}
				keywords_search_string = 'keywords:' + keywords_search_string + '';
				self.manager.store.addByValue('fq', keywords_search_string);
			}

			
			params_set=false;
			initialize(function(){
				if(postal_data.length>0){
					$('#postal').text(postal_data);			
				}
				geo_lat= $('#geo_lat').text();
				geo_long= $('#geo_long').text();
				d_default= $('#d_default').text();
				self.manager.store.addByValue('fq', '{!geofilt pt=' + geo_lat + ',' + geo_long + ' sfield=latlng d=' + d_default + '}');
				
console.log('Search PARAMS AutocompleteWidget doRequest');			
				self.manager.doRequest(0);
			});
		}
    });

	if(params_set==true){
		$('#keyword_search').text(''); // clear for further filtering purpuses
		$('#fq_search').text(''); // clear for further filtering purpuses
		$('#search_solr_params').click();
	}
  }
});

})(jQuery);
