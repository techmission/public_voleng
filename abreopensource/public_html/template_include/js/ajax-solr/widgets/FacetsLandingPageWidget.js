(function ($) {
AjaxSolr.FacetsWidget = AjaxSolr.AbstractFacetWidget.extend({


afterRequest: function () {
  if (this.manager.response.facet_counts.facet_fields[this.field] === undefined) {
    return;
  }

	//console.log('srchmethod is: ' + $('#srchmethod').val());
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
  
	var extra_search_param = '';
	if($('#fq_search').text() != null){
		if($('#fq_search').text().length > 0){
			extra_search_param += '&fq=' + $('#fq_search').text();
		}
	}
	if($('#fq_search_2').text() != null){
		if($('#fq_search_2').text().length > 0){
			extra_search_param += '&fq=' + $('#fq_search_2').text();
		}
	}
	if($('#fq_search_3').text() != null){
		if($('#fq_search_3').text().length > 0){
			extra_search_param += '&fq=' + $('#fq_search_3').text();
		}
	}
	if($('#fq_search_4').text() != null){
		if($('#fq_search_4').text().length > 0){
			extra_search_param += '&fq=' + $('#fq_search_4').text();
		}
	}
    extra_search_param = extra_search_param.replace(/"/g,'&quot;');
	//console.log('extra_search_param is ' + extra_search_param);
	
  for (var i = 0, l = objectedItems.length; i < l; i++) {
    var facet = objectedItems[i].facet;
    var count = objectedItems[i].count;

	var count_term = count;
	var facet_term = facet;
	
	var facet_string = facet_term.replace(/ /g,"%20");
//	var new_URL_string = "fq=" + [this.field] + ':&quot;' + facet_string + '&quot;';
//	var new_URL_string = [this.field] + '=&quot;' + facet_string + '&quot;';
	var new_URL_string = 'fq=' + [this.field] + ':&quot;' + facet_string + '&quot;';
	new_URL_string='/oppsrch.do?method=processSolrSearch#'+new_URL_string+extra_search_param;
	
	// replace all html tags for display of teasers to not affect layout of page
	facet_term = facet_term.replace("Great for ", '');
	if(facet_term=="AllForGood"){
		$(this.target).append( $('<span class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
	}else if(facet_term=="ChristianVolunteering.org"){
	}else if(l>5 && i==l-1){
		$(this.target).append( $('<span class="filter collapsed" id="more_link" style="display:none;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
	}else if(i>=5 && l>5){
		$(this.target).append( $('<span class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
	}else{
		$(this.target).append( $('<span class="filter expanded"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
	}
	if(l>5 && i==l-1){
		$(this.target).append( $('<span class="filter expanded" id="more_link_facet_'+[this.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [this.field]) ));
//		$(this.target).append( AjaxSolr.theme('facet_more_link', [this.field]) );
	}
/*
	if(facet_term=="AllForGood"){
		$(this.target).append( $('<li class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}else if(facet_term=="ChristianVolunteering.org"){
	}else if(l>5 && i==l-1){
		$(this.target).append( $('<li class="filter collapsed" id="more_link" style="display:none;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}else if(i>=5 && l>10){
		$(this.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}else{
		$(this.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
	}
	if(l>5 && i==l-1){
		$(this.target).append( $('<li class="filter expanded" id="more_link_facet_'+[this.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [this.field]) ));
//		$(this.target).append( AjaxSolr.theme('facet_more_link', [this.field]) );
	}
*/
  }
}

});


})(jQuery);