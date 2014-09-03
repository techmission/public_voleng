(function ($) {
AjaxSolr.FacetsWidget = AjaxSolr.AbstractFacetWidget.extend({


afterRequest: function () {
  if (this.manager.response.facet_counts.facet_fields[this.field] === undefined) {
    return;
  }

	var results_type=$('#results_type').text();
	if(results_type.length<1) results_type='opportunity';
//console.log('results_type is: '+results_type);
	
  var maxCount = 0;
  var objectedItems = [];
  var objectedItemsCopy = [];
  for (var facet in this.manager.response.facet_counts.facet_fields[this.field]) {
    var count = parseInt(this.manager.response.facet_counts.facet_fields[this.field][facet]);
    if (count > maxCount) {
      maxCount = count;
    }
    objectedItems.push({ facet: facet, count: count });
  }
  objectedItems.sort(function (a, b) {
    return b.count < a.count ? -1 : 1;
  });

  var self = this;
  $(this.target).empty();
  var moreResults = '';
  for (var i = 0, l = objectedItems.length; i < l; i++) {
    var facet = objectedItems[i].facet;
    var count = objectedItems[i].count;

	var count_term = count;
	var facet_term = facet;
	
	var facet_string = facet_term.replace(/ /g,"%20");
	var new_URL_string = '';
	var jsp_facet_string = facet_string;
	var jsp_URL_string = '';
	
	if(facet_string.indexOf(' & ')!=-1 || facet_string.indexOf('%20&%20')!=-1){ // handle special cases with & - just ignore on the JSP end; convert for the hash end
		facet_string = facet_string.replace(/&/g,"%26");
		jsp_facet_string = '';
		new_URL_string = 'fq=' + [this.field] + ':&quot;' + facet_string + '&quot;&amp;fq=content_type:'+results_type;
		jsp_URL_string = 'fq=content_type:'+results_type;
	}else{
		new_URL_string = 'fq=' + [this.field] + ':&quot;' + facet_string + '&quot;&amp;fq=content_type:'+results_type;
		jsp_URL_string = new_URL_string;
	}
//	new_URL_string='oppsrch.do?method=processSolrSearch&' + jsp_URL_string + '#' + new_URL_string;
	new_URL_string='oppsrch.do?method=processSolrSearch&' + jsp_URL_string + '#' + new_URL_string;
	// replace all html tags for display of teasers to not affect layout of page
	facet_term = facet_term.replace("Great for ", '');
	if(facet_term=="AllForGood"){
		$(this.target).append( $('<li class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}else if(facet_term=="ChristianVolunteering.org"){
	}else{
		$(this.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}
  }
}

});


})(jQuery);