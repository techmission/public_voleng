(function ($) {
AjaxSolr.FacetsWidget = AjaxSolr.AbstractFacetWidget.extend({


afterRequest: function () {
  if (this.manager.response.facet_counts.facet_fields[this.field] === undefined) {
    return;
  }

	var numTypesInFacet = 10;
	if($('#num_types_in_facet') != null){
		numTypesInFacet = $('#num_types_in_facet').text();
		if(numTypesInFacet < 1){
			numTypesInFacet = 10;
		}
	}
	var pagetype = '';
	if($('#page_type') != null){
		pagetype = $('#page_type').text();
		if(pagetype.length < 1){
			pagetype = '';
		}
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
    if(facet.length>1 && facet != '$0' && facet != 'Unknown'){
	    objectedItems.push({ facet: facet, count: count });
	}
  }
  objectedItems.sort(function (a, b) {
    return b.count < a.count ? -1 : 1;
  });

  var self = this;
  $(this.target).empty();
  var moreResults = '';
  //var index = facet_value.indexOf(' ');
  var filterContentType = '';
	var extra_search_param = '';
	if($('#fq_search').text() != null){
		if($('#fq_search').text().length > 0){
			if($('#fq_search').text().indexOf('content_type:opportunity') == -1){
				extra_search_param += '&fq=' + $('#fq_search').text();
			}else{
				filterContentType = '&fq=' + $('#fq_search').text();
			}
		}
	}
	if($('#fq_search_2').text() != null){
		if($('#fq_search_2').text().length > 0){
			if($('#fq_search_2').text().indexOf('content_type:opportunity') == -1){
				extra_search_param += '&fq=' + $('#fq_search_2').text();
			}else{
				filterContentType = '&fq=' + $('#fq_search_2').text();
			}
		}
	}
	if($('#fq_search_3').text() != null){
		if($('#fq_search_3').text().length > 0){
			if($('#fq_search_3').text().indexOf('content_type:opportunity') == -1){
				extra_search_param += '&fq=' + $('#fq_search_3').text();
			}else{
				filterContentType = '&fq=' + $('#fq_search_3').text();
			}
		}
	}
	if($('#fq_search_4').text() != null){
		if($('#fq_search_4').text().length > 0){
			if($('#fq_search_4').text().indexOf('content_type:opportunity') == -1){
				extra_search_param += '&fq=' + $('#fq_search_4').text();
			}else{
				filterContentType = '&fq=' + $('#fq_search_4').text();
			}
		}
	}
    extra_search_param = extra_search_param.replace(/"/g,'&quot;');
	//console.log('extra_search_param is ' + extra_search_param);
//console.log('filterContentType is '+filterContentType);				
	filterContentType='&fq=opportunity';
  for (var i = 0, l = objectedItems.length; i < l; i++) {
    var facet = objectedItems[i].facet;
    var count = objectedItems[i].count;
	var field_name = '' + [this.field];
//console.log('field_name is '+field_name);
	var myClass = $('#'+field_name).attr("class");
//console.log(myClass);

	var count_term = count;
	var facet_term = facet;
	
	var facet_string = facet_term.replace(/ /g,"%20");
	var new_URL_string = '';
	var jsp_facet_string = facet_string;
	var jsp_URL_string = '';
//if([this.field]=='benefits_offered') console.log(	'facet_string is '+facet_string);
	if(facet_string.indexOf(' & ')!=-1 || facet_string.indexOf('%20&%20')!=-1){ // handle special cases with & - just ignore on the JSP end; convert for the hash end
		facet_string = facet_string.replace(/&/g,"%26");
		jsp_facet_string = '';
		new_URL_string = 'fq=' + [this.field] + ':&quot;' + facet_string + '&quot;';
		jsp_URL_string = '';
	}else{
		new_URL_string = 'fq=' + [this.field] + ':&quot;' + facet_string + '&quot;';
		jsp_URL_string = new_URL_string;
	}
	
	new_URL_string+=extra_search_param;
	jsp_URL_string+=extra_search_param;
	
	if(myClass===undefined){
		new_URL_string += filterContentType;
		jsp_URL_string+=filterContentType;
//console.log('new_URL_string is '+new_URL_string);				
	}else if(myClass.length > 0){
		if(myClass.indexOf('org') != -1){
			new_URL_string += '&fq=content_type:organization';
			jsp_URL_string += '&fq=content_type:organization';
		}else{
			new_URL_string += '&fq=content_type:opportunity';
			jsp_URL_string += '&fq=content_type:opportunity';
		}
	}else{
		new_URL_string += filterContentType;
		jsp_URL_string += filterContentType;
//console.log('new_URL_string is '+new_URL_string);				
	}
//	new_URL_string='/oppsrch.do?method=processSearch&'+jsp_URL_string+'#'+new_URL_string;
	new_URL_string='/oppsrch.do?method=processSearch&'+jsp_URL_string+'#'+new_URL_string;
//console.log('new_URL_string is '+new_URL_string);
	// replace all html tags for display of teasers to not affect layout of page
	facet_term = facet_term.replace("Great for ", '');
	
	if(pagetype == 'landingpage'){
		if(facet_term=="AllForGood"){
			$(this.target).append( $('<span class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
		}else if(facet_term=="ChristianVolunteering.org"){
		}else if(l>numTypesInFacet && i==l-1){
			$(this.target).append( $('<span class="filter collapsed" id="more_link" style="display:none;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
		}else if(i>=numTypesInFacet && l>numTypesInFacet){
			$(this.target).append( $('<span class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
		}else{
			$(this.target).append( $('<span class="filter expanded"/>').html(AjaxSolr.theme('facet_landingpage_link', facet_term, count_term, new_URL_string, i, l)));
		}
		if(l>numTypesInFacet && i==l-1){
			$(this.target).append( $('<span class="filter expanded" id="more_link_facet_'+[this.field]+'" style="display:inline;"/>').html(AjaxSolr.theme('facet_more_landingpage_link', [this.field]) ));
	//		$(this.target).append( AjaxSolr.theme('facet_more_link', [this.field]) );
		}
	}else{
		if(facet_term=="AllForGood"){
			$(this.target).append( $('<li class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
		}else if(facet_term=="ChristianVolunteering.org" || facet_term=="MissionFinder.org" || facet_term=="helpingoverseasdirectory.org" || facet_term=="Foundation"){
		}else if(l>numTypesInFacet && i==l-1){
			$(this.target).append( $('<li class="filter collapsed" id="more_link" style="display:none;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
		}else if(i>=numTypesInFacet && l>numTypesInFacet){
			$(this.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
		}else{
			$(this.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_form_link', facet_term, count_term, new_URL_string)));
		}
		if(l>numTypesInFacet && i==l-1){
			$(this.target).append( $('<li class="filter expanded" id="more_link_facet_'+[this.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [this.field]) ));
	//		$(this.target).append( AjaxSolr.theme('facet_more_link', [this.field]) );
		}
	}
  }
function adjust_midbox_height(){
	if($('#midbox1_content')!=null){
		$('#midbox1_content').height('auto');
		$('#midbox2_content').height('auto');
		$('#midbox3_content').height('auto');
		var midbox1_height = $('#midbox1_content').height();
		var midbox2_height = $('#midbox2_content').height();
		var midbox3_height = $('#midbox3_content').height();
		var midbox_overall_height = midbox1_height;
		if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
		if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
		$('#midbox1_content').height(midbox_overall_height);
		$('#midbox2_content').height(midbox_overall_height);
		$('#midbox3_content').height(midbox_overall_height);
	}
}
	if(pagetype == 'landingpage'){
		adjust_midbox_height();
	}
}

});


})(jQuery);