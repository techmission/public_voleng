(function ($) {

AjaxSolr.SortByWidget = AjaxSolr.AbstractFacetWidget.extend({
  afterRequest: function () {
    var self = this;
	
    $('#sortkey').change(function(e, facet) {
      self.requestSent = true;
		var sortKey = $('#sortkey').val();
	  
	  self.manager.store.addByValue('sort', sortKey);
      self.manager.doRequest(0);
		
    });
  }
});

})(jQuery);
