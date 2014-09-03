(function ($) {

// For an AutocompleteWidget that uses the q parameter, see:
// https://github.com/evolvingweb/ajax-solr/blob/gh-pages/examples/reuters/widgets/AutocompleteWidget.q.js
AjaxSolr.AutocompleteWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
//    $(this.target).find('input').val('');
    $('#query').val('');


    var self = this;
    // unautocomplete() below will unbind the keydown handler.
//    $(this.target).find('input').unbind().bind('keydown', function(e) {
    $('#query').unbind().bind('keydown', function(e) {
//alert('keydown');
      if (self.requestSent === false && e.which == 13) {
//alert('keydown 13');
        var value = $(this).val();
        if (value && self.add(value)) {
          self.manager.doRequest(0);
        }
      }
    });

    var callback = function (response) {
//alert('callback');
      var list = [];
      for (var i = 0; i < self.fields.length; i++) {
        var field = self.fields[i];
        for (var facet in response.facet_counts.facet_fields[field]) {
          list.push({
            field: field,
            value: facet,
            text: facet + ' (' + response.facet_counts.facet_fields[field][facet] + ') - ' + field
          });
        }
      }

      self.requestSent = false;
//      $(self.target).find('input').unautocomplete().autocomplete(list, {
      $('#query').unautocomplete().autocomplete(list, {
        formatItem: function(facet) {
          return facet.text;
        }
      }).result(function(e, facet) {
//alert('requestSent');
        self.requestSent = true;
        if (self.manager.store.addByValue('fq', facet.field + ':' + AjaxSolr.Parameter.escapeValue(facet.value))) {
          self.manager.doRequest(0);
        }
      });
    } // end callback

    var params = [ 'q=*:*&rows=0&facet=true&facet.limit=-1&facet.mincount=1&json.nl=map' ];
    for (var i = 0; i < this.fields.length; i++) {
//alert('facet.field=' + this.fields[i]);
      params.push('facet.field=' + this.fields[i]);
    }
	
    if (this.manager.proxyUrl) {
      jQuery.post(this.manager.proxyUrl, { query: params.join('&') }, callback, 'json');
    }
    else {
      jQuery.getJSON(this.manager.solrUrl + 'select?' + params.join('&') + '&wt=json&json.wrf=?', {}, callback);
    }
	
//    jQuery.getJSON(this.manager.solrUrl + 'select?' + params.join('&') + '&wt=json&json.wrf=?', {}, callback);
  }
});

})(jQuery);
