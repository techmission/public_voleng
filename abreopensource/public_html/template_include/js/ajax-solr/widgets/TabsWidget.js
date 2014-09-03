(function ($) {
AjaxSolr.TabsWidget = AjaxSolr.AbstractFacetWidget.extend({

afterRequest: function () {
	if (this.manager.response.facet_counts.facet_fields[this.field] === undefined) {
    	return;
	}
	if (!window.console) console = {log: function() {}};
	
	var title = document.title;
	if(title==null){
		title='ChristianVolunteering.org';
	}else if(title===undefined){
		title='ChristianVolunteering.org';
	}else if(title=='undefined'){
		title='ChristianVolunteering.org';
	}
	
	var filter_org = false;
	var filter_opp = false;
	var filter_job = false;
	var filter_virtual = false;
	var filter_stm = false;
	var filter_cv = false;
	var filter_resume = false;
	var filter_fdn = false;
	
	var source='tabs';
	
	var hashURL = '' + window.location.hash;
	if(hashURL.indexOf('fq=org_member_type:Foundation') != -1 && filter_stm==false && filter_virtual==false){
		filter_fdn = true;
	}else if(hashURL.indexOf('job') != -1 && filter_stm==false && filter_virtual==false){
		filter_job = true;
	}else if(hashURL.indexOf('content_type:organization') != -1 && filter_stm==false && filter_virtual==false && filter_cv==false){
		filter_org = true;
	}else if(hashURL.indexOf('Short-term Missions / Volunteer Internship') != -1 && filter_virtual==false && filter_org==false && filter_job==false && filter_org==false && filter_cv==false){
		filter_stm = true;
	}else if(hashURL.indexOf('Virtual Volunteering (from home)') != -1 && filter_stm==false && filter_org==false && filter_job==false && filter_cv==false){
		filter_virtual = true;
	}else if(hashURL.indexOf('resume') != -1 && filter_resume==false){
		filter_resume = true;
	}else if((hashURL.indexOf('City Vision') != -1  || hashURL.indexOf('CityVision') != -1 ) && filter_stm==false && filter_virtual==false && filter_job==false && filter_org==false && filter_resume==false){
		filter_cv = true;
//console.log('filter cv is '+filter_cv);		
	}

	var maxCount = 0;
	var objectedItems = [];
	var objectedItemsCopy = [];
	for (var facet in this.manager.response.facet_counts.facet_fields[this.field]) {
		var count = parseInt(this.manager.response.facet_counts.facet_fields[this.field][facet]);
		if (count > maxCount) {
			maxCount = count;
		}
		objectedItems.push({ facet: facet, count: count });
		//console.log('facet: '+facet +', count: '+ count	);
	}
//var tmpparam1 = $('#position_type').text();
//var tmpparam2 = $('#content_type').text();
//var tmpparam3 = $('#organization_name').text();
//console.log('********   position_written is |'+tmpparam1.length+'| ; ********   content_type is |'+tmpparam2.length+'| ;********   organization_name is |'+tmpparam3.length+'| *******'); 

	var self = this;
//console.log('this.manager.store.values(fq);  '+ self.manager.store.values('fq'));
//console.log($(this.target));
	$(this.target).empty();
// tmpparam1 = $('#position_type').text();
// tmpparam2 = $('#content_type').text();
// tmpparam3 = $('#organization_name').text();
//console.log('    ********   position_written is |'+tmpparam1.length+'| ; ********   content_type is |'+tmpparam2.length+'| ;********   organization_name is |'+tmpparam3.length+'| *******')//console.log('target is '+target);
	var moreResults = '';
	var init_values = '';
	var filter_values = this.manager.store.values('fq'); // array []
	for (var i = 0, l = filter_values.length; i < l; i++) {
		var val = ''+filter_values[i];
//console.log('val is '+val);		
		if(val.indexOf('geofilt') != -1){
			$('#geofilt_facet').text(val);
		}else{
			if(init_values.indexOf(filter_values[i]) == -1){
				init_values += '&fq=' + filter_values[i];
			}
		}
	}
	
	// need to avoid writing twice');
	var position_written = $('#position_type').text();
	var content_written = $('#content_type').text();
	var organization_written = $('#organization_name').text();
	var organization_written2 = $('#organization_name').text().indexOf('Internships');
	var member_written = 0;
	if($('#member_type') != null){
	if($('#member_type').html() != null){
		$('#member_type').html().indexOf('org_member_type=Foundation');
	}
	}
	
	if( filter_org == false && filter_virtual == false && filter_stm == false && filter_job==false && filter_resume==false  && filter_fdn==false  && filter_cv==false){
		filter_opp = true;
	}
	//console.log('filter_org is: ' + filter_org + '; filter_opp is: ' + filter_opp + '; filter_stm is: ' + filter_stm + '; filter_virtual is: ' + filter_virtual); 
	
	
//console.log('position_written is |'+position_written+'|  length is '+position_written.length); 
	if(position_written.length < 1){
//console.log('position written is empty'); 
		$(this.target).append( $('<div id="position_type_local" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_opp, 'Local Volunteering (in person)',  self.clickHandlerAlt('Local Volunteering (in person)',init_values,source, 'Local Volunteering (in person) '+title))));
		$(this.target).append( $('<div id="position_type_virtual" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_virtual, 'Virtual Volunteering (from home)',  self.clickHandlerAlt('Virtual Volunteering (from home)',init_values,source, 'Virtual Volunteering (from home) '+title))));
		$(this.target).append( $('<div id="position_type_stm" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_stm, 'Short-term Missions / Volunteer Internship',  self.clickHandlerAlt('Short-term Missions / Volunteer Internship',init_values,source, 'Short-term Missions / Volunteer Internship '+title))));
	}
	
	
//console.log('organization_written is |'+organization_written+'|  length is '+organization_written.length); 
	if(organization_written.length < 1){
//console.log('organization_written written is empty'); 
		$(this.target).append( $('<div id="organization_name_cv_tab" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_cv, 'CityVision',  self.clickHandlerAlt('City Vision',init_values,source, title+' Internships'))));
	}
	
	
//console.log('content_written is |'+content_written+'|  length is '+content_written.length); 
	if(content_written.length < 1){
//console.log('content_written written is empty'); 
		$(this.target).append( $('<div id="content_type_job_tab" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_job, 'job',  self.clickHandlerAlt('job',init_values,source, title+' Jobs'))));
		$(this.target).append( $('<div id="content_type_tab" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_org, 'organization',  self.clickHandlerAlt('organization',init_values,source, title+' Organizations'))));
		$(this.target).append( $('<div id="content_type_res_tab" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_resume, 'resume',  self.clickHandlerAlt('resume',init_values,source, title+' Resum&eacute;s'))));
	}
	if(member_written == -1){
//console.log('member_written written is empty'); 
		$('#member_type').html('');
		$('#member_type').append( $('<div id="member_type_fdn_tab" class="results-left" />').html(AjaxSolr.theme('tab_link', filter_fdn, 'Foundation',  self.clickHandlerAlt('Foundation',init_values,source, title+' Foundations'))));
	}
}

});

})(jQuery);