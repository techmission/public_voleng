(function ($) {

// For a CurrentSearchWidget that supports the q parameter, see:
// https://github.com/evolvingweb/ajax-solr/blob/gh-pages/examples/reuters/widgets/CurrentSearchWidget.q.js
AjaxSolr.CurrentSearchWidget = AjaxSolr.AbstractWidget.extend({
  afterRequest: function () {
    var self = this;
    var links_array = [];
	var links_string = '';	
	var list_removeall = [];
    var fq = this.manager.store.values('fq');
	var postal_included = false;
	if (!window.console) console = {log: function() {}};
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));

//console.log('fq is '+ fq);	
	var fq_string = ' '+fq;				
	if(fq_string.indexOf('resume') != -1 && fq_string.indexOf('full_user') == -1 && fq_string.indexOf('Applicant') == -1){
			links_array.push($('<a href="#"/>').text('All Users   (x)').click(self.removeFacet(fq[i])));
			links_string = links_string + 'All Users   (x) ';
	//console.log(' 38:   links links_string is '+links_string);
			$('#facet_full_user').show();
			$('#facet_all_user').hide();
	}
	for (var i = 0, l = fq.length; i < l; i++) {
//console.log('links_array is '+ links_array);					
		// parse the field & the actual term separately so that we only show the term (and not the field) to the user
		var filter_query=fq[i];
		var filter=''+filter_query;
		
		if(filter.indexOf('org_member_type') != -1 || filter.indexOf('hidden_source') != -1)	{
/*
		}else if(fq_string.indexOf('resume') != -1 && fq_string.indexOf('full_user') == -1){
				links_array.push($('<a href="#"/>').text('All Users   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'All Users   (x) ';
//console.log(' 38:   links links_string is '+links_string);
				$('#facet_full_user').show();
				$('#facet_all_user').hide();
*/				
		}else {
//console.log('filter is '+ filter);					
			var colon_index = filter.indexOf(':');
			var field_name=filter.substr(0,colon_index);
			var term=filter.substr(colon_index+1);
			var postal = $('div#postal').text();			
			var geo_index = filter.indexOf("{!geofilt pt="); // don't display the distance geo filter here for removal   {!geofilt pt=42.369904,-71.2353 sfield=latlng d=40.2336} (x)

			if(field_name=='org_member_type'){
			}else if(field_name=='hidden_source'){
			}else if(field_name=='status'){
			}else if(field_name=='cvintern_applicant'){
			}else if(field_name=='screened'){
			}else if(field_name=='cvintern_placed'){

			}else if(field_name=='pos_pref' && fq[i].indexOf('Support Work (Grant Writing, Student Services, Graphic Design, Web Development, Online Content)') != -1){
				links_array.push($('<a href="#"/>').text('Support Work   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Support Work   (x) ';
			}else if(field_name=='work_environ' && fq[i].indexOf('Strongly prefer working directly serving people, likely unhappy with only office work') != -1){
				links_array.push($('<a href="#"/>').text('Strong People   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Strong People   (x) ';
			}else if(field_name=='work_environ' && fq[i].indexOf('Slightly prefer working directly serving people, would consider office work') != -1){
				links_array.push($('<a href="#"/>').text('Slight People   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Slight People   (x) ';
			}else if(field_name=='work_environ' && fq[i].indexOf('Slightly prefer office work, but would consider only serving people directly') != -1){
				links_array.push($('<a href="#"/>').text('Slight Office   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Slight Office   (x) ';
			}else if(field_name=='work_environ' && fq[i].indexOf('Strongly prefer office work, likely unhappy with only serving people directly') != -1){
				links_array.push($('<a href="#"/>').text('Strong Office   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Strong Office   (x) ';
			}else if(field_name=='work_environ' && fq[i].indexOf('Does not matter') != -1){
				links_array.push($('<a href="#"/>').text('Any Work Environment   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Any Work Environment   (x) ';

			}else if(field_name=='num_opps'){
				links_array.push($('<a href="#"/>').text('Only Orgs with Opportunities   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Only Orgs with Opportunities   (x) ';
//console.log(' 38:   links links_string is '+links_string);
				$('#facet_num_opps').hide();
			}else if(field_name=='full_user'){
				links_array.push($('<a href="#"/>').text('Only Users with Resumes   (x)').click(self.removeFacet(fq[i])));
				links_string = links_string + 'Only Users with Resumes   (x) ';
//console.log(' 38:   links links_string is '+links_string);
				$('#facet_full_user').hide();
				$('#facet_all_user').show();
			}else if(geo_index!=-1){
//console.log('get postal from geofilt');					
				if(postal_included==true){
					self.removeFacet(fq[i]);
					self.manager.store.removeByValue('fq', fq[i]);
				}else{
					postal_included=true;
					$('div#postal').html();//term);
					initialize();
					self.removeFacet(fq[i]); // remove the actual location:##### or postal_code:#### from search
					// make sure this element has not already been included (avoid duplicate items on search widget)
					links_array.push($('<a href="#"/>').text(postal + '   (x)').click(self.removeFacet(fq[i])));
					links_string = links_string + postal + '   (x) ';
//console.log(' 47:   links links_string is '+links_string);
				}
			}else{
				if(field_name=='postal_code'){
//console.log('get postal');					
					try{
						term = term.substring(0,5);
					}catch(e){}
					$('div#postal').html(term);
					initialize();
					self.removeFacet(fq[i]); // remove the actual location:##### or postal_code:#### from search
				}else if(field_name=='location'){
					var b_isNotANumber = isNaN(term);
					if(b_isNotANumber==true && links_string.indexOf(term)==-1){
						// is text, then	
						links_array.push($('<a href="#"/>').text(term + '   (x)').click(self.removeFacet(fq[i])));
						links_string = links_string + term + '   (x) ';
//console.log(' 63:   links links_string is '+links_string);
					}else{
//console.log('get postal (location)');					
						try{
							term = term.substring(0,5);
						}catch(e){}
						$('div#postal').html(term);
						initialize();
						self.removeFacet(fq[i]); // remove the actual location:##### or postal_code:#### from search
					}
				}else if(field_name != 'content_type' && field_name != 'position_type' && field_name != 'org_member_type' && field_name != 'hidden_source' && field_name.length>0){
				// make sure this element has not already been included (avoid duplicate items on search widget)
					if(links_string.indexOf(term)==-1){ 
							links_array.push($('<a href="#"/>').text(term + '   (x)').click(self.removeFacet(fq[i])));
							links_string = links_string + postal + '   (x) ';
//console.log(' 84:   links links_string is '+links_string);
//console.log('field_name is '+field_name);
					}
				}
			}
		}
	}
//console.log(' links links_string is '+links_string);
//console.log(' links_array  is '+links_array.join('  \n  '));

	for(var i=0; i<links_array.length; i++){
//	console.log('i '+i+' is    '+links_array[i]);
	}
	
    if (links_array.length > 1) {
      list_removeall.unshift($('<a href="#"/>').text('remove all').click(function () {
		// first get the content type; we don't want to remove the content type; should default to opportunity
		var content_type = 'organization';
		var extra_terms = '';
		var fq=' FQ: ' +self.manager.store.values('fq');
		if( fq.indexOf('content_type:organization')== -1 && fq.indexOf('content_type:job')== -1 && fq.indexOf('content_type:resume')== -1 ){
			content_type = 'opportunity';
		}
		if(fq.indexOf('City Vision Intern Applicant') != -1){
			content_type = 'resume';
			extra_terms = 'cvi';
		}
		if(content_type == 'organization'){
			$('facet_num_opps').show();	
		}
        self.manager.store.remove('fq');
		self.manager.store.addByValue('fq', 'content_type:' + content_type);
		if(extra_terms == 'cvi'){
			self.manager.store.addByValue('fq', 'cvintern_applicant:"City Vision Intern Applicant"' );
			self.manager.store.addByValue('fq', 'screened:[1 TO 2]' );
		}else if(content_type == 'organization'){
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
			if($('#Foundation').hasClass('active')){
				self.manager.store.removeByValue('fq', '-org_member_type:Foundation' );
				self.manager.store.addByValue('fq', 'org_member_type:Foundation' );
			}else{
				self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
				self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
				self.manager.store.addByValue('fq', '-org_member_type:Foundation' );
			}
//console.log('self.manager.store.values is: ' + self.manager.store.values('fq'));
//			self.manager.store.addByValue('fq', '-hidden_source:irs_parachurch' );
		}
//console.log('CurrentSearchWidget.js doRequest line 65');
        self.manager.doRequest(0);
        return false;
      }));
    }
    if (links_array.length) {
		if (list_removeall.length>0) {
			$('#filter_label').addClass('left');
		  AjaxSolr.theme('list_removeall', this.target_alt, list_removeall);
		}
      AjaxSolr.theme('list_items', this.target, links_array);
    }
    else {
      $(this.target).html('<div></div>');
    }
  },

  removeFacet: function (facet) {
    var self = this;
//console.log('93 this.manager.store.values(fq);  '+ self.manager.store.values('fq'));
    return function () {
      if (self.manager.store.removeByValue('fq', facet)) {
//console.log('96 CurrentSearchWidget.js doRequest line 115; facet is '+facet);
//console.log('97 this.manager.store.values(fq);  '+ self.manager.store.values('fq'));
		var facet_string = '' + facet + '';
//console.log('facet_string is '+facet_string);
         if(facet_string.indexOf('num_opps')!=-1){
//console.log('facet_num_opps    facet_string is '+facet_string);
			$('#facet_num_opps').show();	
		}else if(facet_string.indexOf('geofilt') != -1){
//console.log('99 has geofilt in it  '+ self.manager.store.values('fq'));
			$('#geofilt_facet').text('');
			$('div#postal').text('');
		}
		self.manager.doRequest(0);
      }
//console.log('this.manager.store.values(fq);  '+ self.manager.store.values('fq'));
      return false;
    };
  }
});

})(jQuery);
