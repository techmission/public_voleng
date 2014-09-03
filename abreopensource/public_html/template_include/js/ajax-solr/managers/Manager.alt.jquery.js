// $Id$

/**
 * @see http://wiki.apache.org/solr/SolJSON#JSON_specific_parameters
 * @class Manager
 * @augments AjaxSolr.AbstractManager
 */
AjaxSolr.Manager = AjaxSolr.AbstractManager.extend(
  /** @lends AjaxSolr.Manager.prototype */
  {
  executeRequest: function (servlet, string, handler) {
    var self = this;
    string = string || this.store.string();
    handler = handler || function (data) {
      self.handleResponse(data);
	  submitSearch(self, data);
	  // forward to new page
	  // new page calls handleResponse
    };
    if (this.proxyUrl) {
      jQuery.post(this.proxyUrl, { query: string }, handler, 'json');
//		alert(string);
		// parse out string
		// form new URL (for searches)
		// call a window forward url with hashtags/params/something of the sort to handle the parsed string data
		// facet=true&q=content_type%3Aopportunity&rows=100&facet.field=position_type&facet.field=service_areas&facet.field=skills&facet.field=great_for&facet.field=frequency&facet.field=benefits_offered&facet.field=trip_length&facet.field=country_tax&facet.field=region&facet.field=province_tax&facet.field=denom_affil&facet.field=org_affil&facet.field=id&facet.field=source&facet.field=country&facet.limit=100&facet.mincount=1&f.keyword.facet.limit=50&f.country.facet.limit=-1&json.nl=map
    }
    else {
      jQuery.getJSON(this.solrUrl + servlet + '?' + string + '&json.nl&wt=json&json.wrf=?', {}, handler);
    }
  }
});
