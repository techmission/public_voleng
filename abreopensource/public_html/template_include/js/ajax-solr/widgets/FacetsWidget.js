(function ($) {
AjaxSolr.FacetsWidget = AjaxSolr.AbstractFacetWidget.extend({

afterRequest: function () {
  if (this.manager.response.facet_counts.facet_fields[this.field] === undefined) {
    return;
  }
  if (!window.console) console = {log: function() {}};
  var title = document.title;
// console.log('(line 10) title is: '+title);
//console.log('this.manager.store.values is: ' + this.manager.store.values('fq'));	  
  var self = this;
//console.log('facets widget - self.manager.store.values is: ' + self.manager.store.values('fq'));	  
  var source = 'facets';
  var maxCount = 0;
  var b_hasFinancialData = false;
  var b_isFoundation = false;
  var objectedItems = [];
  var objectedItemsCopy = [];
  for (var facet in self.manager.response.facet_counts.facet_fields[self.field]) {
	var weight=-1;
    var count = parseInt(self.manager.response.facet_counts.facet_fields[self.field][facet]);

if(self.field != 'keywords' && self.field != 'city_tax' && self.field != 'title' && self.field != 'org_name' && self.field != 'country_tax' && self.field != 'region' && self.field != 'organization_name' && self.field != 'province_tax'){
//	console.log('self.field facet and count: '+self.field + '  '+facet+' ' +count);
}

    if (count > maxCount) {
      maxCount = count;
    }
    if(facet.length>1 && facet != '$0' && facet != 'Unknown' && facet != 'Strongly prefer the following locations'){
		if(self.field=='geo_tax')	{
			b_isFoundation=true;
		}
		if(self.field=='total_giving' || self.field=='assets' || self.field=='net_assets' || self.field=='income' || self.field=='expenditures')	{
			b_hasFinancialData=true;
			switch(facet){
				case '$1-$25,000':
					weight=0;
					break;
				case '$25,000-$100,000':
					weight=10;
					break;
				case '$100,000-$250,000':
					weight=20;
					break;
				case '$250,000-$1,000,000':
					weight=30;
					break;
				case '$1,000,000-$3,000,000':
					weight=40;
					break;
				case '$3,000,000-$10,000,000':
					weight=50;
					break;
				case '$10,000,00-$50,000,000':
					weight=60;
					break;
				case '$50,000,000-$250,000,000':
					weight=70;
					break;
				case '$250,000,000-$1,000,000,000':
					weight=80;
					break;
				case '> $1,000,000,000':
					weight=90;
					break;
				default:
					break;
			}
//console.log('self.field facet and count: '+self.field + '  '+facet+' ' +count);
			objectedItems.push({ facet: facet, count: count, weight: weight });
		}else if(self.field=='education_level')	{
			b_hasFinancialData=true;
			switch(facet){
				case 'High School Diploma or Equivalent':
					weight=40;
					break;
				case 'Associate\'s Degree':
					weight=50;
					break;
				case 'Bachelor\'s Degree':
					weight=60;
					break;
				case 'Master\'s Degree':
					weight=70;
					break;
				case 'Doctorate':
					weight=90;
					break;
				default:
					break;
			}
//console.log('self.field facet and count: '+self.field + '  '+value_display+' ' +count);
// not sure where value_display is supposed to be defined or set????			objectedItems.push({ facet: value_display, count: count, weight: weight });
	    	objectedItems.push({ facet: facet, count: count, weight: weight });
		}else{
	    	objectedItems.push({ facet: facet, count: count, weight: weight });
		}
	}
  }
  
  // ****************************************    I think this is where it sorts which of the values gets listed at top - highest count right now for all
  objectedItems.sort(function (a, b) {
//console.log(a);
//console.log('a weight is '+a.weight);
	  if(a.weight>0 || b.weight>0){
		  return b.weight < a.weight ? -1 : 1;
	  }else{
		  return b.count < a.count ? -1 : 1;
	  }
  });

  $(self.target).empty();
  var moreResults = '';
  var init_values = '';
  var filter_values = self.manager.store.values('fq'); // array []
  var shows_internships = false;
  for (var i = 0, l = filter_values.length; i < l; i++) {
	  var val = ''+filter_values[i];
	  if(val.indexOf('City Vision') != -1){
		  shows_internships=true;
	  }
	if(val.indexOf('geofilt') != -1){
	}else{
		if(init_values.indexOf(filter_values[i]) == -1){
		  init_values += '&fq=' + filter_values[i];
		}
	}
  }
//console.log('init_values is: ' + init_values);
  var facets_for_title = '';
  for (var i = 0, l = objectedItems.length; i < l; i++) {
    var facet = objectedItems[i].facet;
    var count = objectedItems[i].count;
if(self.field == 'degree_prog'){
//	console.log('objectedItems facet is: ' + facet);
}
	var count_term = count;
	var facet_term = facet;
	
	facets_for_title += facet_term;
	// replace all html tags for display of teasers to not affect layout of page
	facet_term = facet_term.replace("Great for ", '');
	facet_term = facet_term.replace(" (Grant Writing, Student Services, Graphic Design, Web Development, Online Content)", '');
	facet_term = facet_term.replace("Strongly prefer working directly serving people, likely unhappy with only office work", 'Strong People');
	facet_term = facet_term.replace("Slightly prefer working directly serving people, would consider office work", 'Slight People');
	facet_term = facet_term.replace("Strongly prefer office work, likely unhappy with only serving people directly", 'Strong Office');
	facet_term = facet_term.replace("Slightly prefer office work, but would consider only serving people directly", 'Slight Office');
	facet_term = facet_term.replace("Does not matter", 'Anything');
	facet_term = facet_term.replace("Bakke Internship (requires Bachelor's degree or being a senior about to graduate)", 'Bakke Internship');
	facet_term = facet_term.replace("Technology and Ministry Internship (requires Bachelor's degree or being a senior about to graduate with a background in a technology)", 'Technology and Ministry Internship');
	
	if(shows_internships==true){
//console.log('shows internships; self.field='+self.field+';   l='+l+';   i='+i);			
		if(self.field == 'city_tax'){
//console.log('city tax; l='+l+';   i='+i);			
			if(l>15 && i==l-1){
				$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}else if(i>=15 && l>15){
				$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}else{
				$(self.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}
			
			if(l>15 && i==l-1){
				$(self.target).append( $('<li class="filter expanded" id="more_link_facet_'+[self.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [self.field]) ));
			}
		}else{
			if(l>10 && i==l-1){
				$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}else if(i>=10 && l>10){
				$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}else{
				$(self.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
			}
			
			if(l>10 && i==l-1){
				$(self.target).append( $('<li class="filter expanded" id="more_link_facet_'+[self.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [self.field]) ));
			}
		}
	}else{
		
		if(facet_term=='AllForGood' || facet_term=='AllForGood'){
			$(self.target).append( $('<li class="filter expanded" style="display:block;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
		}else if(facet_term=="ChristianVolunteering.org" || facet_term=="MissionFinder.org" || facet_term=="helpingoverseasdirectory.org" || facet_term=="Foundation"){
		}else if(l>10 && i==l-1){
			$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
		}else if(i>=10 && l>10){
			$(self.target).append( $('<li class="filter collapsed" style="display:none;"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
		}else{
			$(self.target).append( $('<li class="filter expanded"/>').html(AjaxSolr.theme('facet_link', facet_term, count_term,i,l,[self.field], self.clickHandlerAlt(facet,init_values,source,facets_for_title))));
		}
		if(l>10 && i==l-1){
			$(self.target).append( $('<li class="filter expanded" id="more_link_facet_'+[self.field]+'" style="display:block;"/>').html(AjaxSolr.theme('facet_more_link', [self.field]) ));
		}
	}
  }
  
//  	  title = facet_value + facets_for_title

	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';
  if(b_hasFinancialData==true) {
//console.log('toggle financial data triggered; $("#total_giving li").size() is '+$("#total_giving li").size()+' $("#net_assets li").size() is '+$("#net_assets li").size()+' $("#assets li").size() is '+$("#assets li").size()+' $("#income li").size() is '+$("#income li").size()+' $("#expenditures li").size() is '+$("#expenditures li").size() );		
		if($("#total_giving li").size() < 1){
			$('#total_giving').hide();
			$('#facet_total_giving').hide();
			$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#total_giving').show();
			$('#facet_total_giving').show();
			$('#facet_total_giving').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#net_assets li").size() < 1){
			$('#net_assets').hide();
			$('#facet_net_assets').hide();
			$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#net_assets').hide();
			$('#facet_net_assets').show();
			$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		}
		if($("#assets li").size() < 1){
			$('#assets').hide();
			$('#facet_assets').hide();
			$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#assets').hide();
			$('#facet_assets').show();
			$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		}
		if($("#income li").size() < 1){
			$('#income').hide();
			$('#facet_income').hide();
			$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#income').hide();
			$('#facet_income').show();
			$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		}
		if($("#expenditures li").size() < 1){
			$('#expenditures').hide();
			$('#facet_expenditures').hide();
			$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#expenditures').hide();
			$('#facet_expenditures').show();
			$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		}

  }
  if(b_isFoundation==true){
//console.log(' ;                    $("#geo_tax li").size() is '+$("#geo_tax li").size());
		if($("#geo_tax li").size() < 1){
			$('#geo_tax').hide();
			$('#facet_geo_tax').hide();
			$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);

			$('#city_tax').hide();
			$('#facet_city_tax').show();
			$('#facet_city_tax').removeClass(expanded_class).addClass(collapsed_class);
			$('#country_tax').show();
			$('#facet_country_tax').show();
			$('#facet_country_tax').removeClass(collapsed_class).addClass(expanded_class);
			$('#province_tax').show();
			$('#facet_province_tax').show();
			$('#facet_province_tax').removeClass(collapsed_class).addClass(expanded_class);
		}else{
			$('#geo_tax').show();
			$('#facet_geo_tax').show();
			$('#facet_geo_tax').removeClass(collapsed_class).addClass(expanded_class);
			
			$('#city_tax').hide();
			$('#facet_city_tax').hide();
			$('#facet_city_tax').removeClass(expanded_class).addClass(collapsed_class);
			$('#country_tax').hide();
			$('#facet_country_tax').hide();
			$('#facet_country_tax').removeClass(expanded_class).addClass(collapsed_class);
			$('#province_tax').hide();
			$('#facet_province_tax').hide();
			$('#facet_province_tax').removeClass(expanded_class).addClass(collapsed_class);
			
		}

	}
//console.log('this.manager.store.values is: ' + this.manager.store.values('fq'));	  

}



});

})(jQuery);