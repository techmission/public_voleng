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
    };
//console.log('string is: ' + string);

    if (this.proxyUrl) {
      jQuery.post(this.proxyUrl, { query: string }, handler, 'json');
    }
    else {
      jQuery.getJSON(this.solrUrl + servlet + '?' + string + '&json.nl&wt=json&json.wrf=?', {}, handler);
    }
  }
});
