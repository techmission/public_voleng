(function ($) {

AjaxSolr.ResultWidget = AjaxSolr.AbstractWidget.extend({
  beforeRequest: function () {
    //$(this.target).html($('<img/>').attr('src', 'http://www.christianvolunteering.org/imgs/Processing.gif'));
  },

  facetLinks: function (facet_field, facet_values) {
    var links = [];
    if (facet_values) {
      for (var i = 0, l = facet_values.length; i < l; i++) {
        links.push(AjaxSolr.theme('facet_link', facet_values[i], this.facetHandler(facet_field, facet_values[i])));
      }
    }
    return links;
  },

  facetHandler: function (facet_field, facet_value) {
    var self = this;
    return function () {
      self.manager.store.remove('fq');
      self.manager.store.addByValue('fq', facet_field + ':' + AjaxSolr.Parameter.escapeValue(facet_value));
      self.manager.doRequest(0);
      return false;
    };
  },

  afterRequest: function () {
    var self = this;
	$('#search_solr').click(function() {
		$('#populate').text('populate');
	})
	if($('#populate').text()=='populate'){
		$('#searchform').hide();
		$('#sort').show();
		$(this.target).empty();
		var offset = parseInt(this.manager.response.responseHeader.params && this.manager.response.responseHeader.params.start || 0);
		var count=0;
		var item_count=0;
		for (var i = 0, l = this.manager.response.response.docs.length; i < l; i++) {
		count++;
		item_count=offset + count;
		  var doc = this.manager.response.response.docs[i];
		  $(this.target).append(AjaxSolr.theme('result', item_count, doc, AjaxSolr.theme('snippet', doc)));
		}	
	}
  },

  init: function () {
    $('a.more').livequery(function () {
      $(this).toggle(function () {
        $(this).parent().find('span').show();
        $(this).text('less');
        return false;
      }, function () {
        $(this).parent().find('span').hide();
        $(this).text('more');
        return false;
      });
    });
  }

});

})(jQuery);