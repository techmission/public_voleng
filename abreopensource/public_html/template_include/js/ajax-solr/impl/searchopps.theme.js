(function ($) {

AjaxSolr.theme.prototype.result = function (item_count, item, snippet) {
var tm_member = '';
if(item.tm_member_i !== undefined)	tm_member = item.tm_member_i;
var source = '';
if(item.source !== undefined)	source = item.source;
var member_class='listing';
var iPopularity = '';
if(item.popularity !== undefined)	iPopularity = item.popularity;
var popularity_img_filename = '';
var popularity_link = '';
var small_logo = source.replace(/\ /g, '_').toLowerCase();
var index_dot = index_dot = source.indexOf('.');
if(index_dot > 0){
	small_logo = small_logo.substring(0,index_dot);
}
if(small_logo.length>0){
	small_logo = 'images/feed_source_logos/small/' + small_logo + '.png';
}

if(source == 'All For Good' || source == 'AllForGood'){
	popularity_img_filename="imgs/afg-blue_60.png";
}else if(source == 'simplyhired' || source == 'SimplyHired'){
	$('#simplyhired').show();
	popularity_link = '<span style="font-size:20px; color:green;">*</span>';
}else if(source == 'Oscar.org.uk'){
	popularity_img_filename = "imgs/blank.gif";
}else if(source.indexOf("olunteering.org")==-1 && small_logo.length>0 && 
		!( source=='Oscar.org.uk' || source=='SimplyHired')
	){
	popularity_img_filename = small_logo ;
}else if(iPopularity > 60){
	popularity_img_filename="imgs/star-5.gif";
}else if(iPopularity > 40){
	popularity_img_filename="imgs/star-4.gif";
}else if(iPopularity > 30){
	popularity_img_filename="imgs/star-3.gif";
}else if(iPopularity > 10){
	popularity_img_filename="imgs/star-2.gif";
}else if(iPopularity > 0){
	popularity_img_filename="imgs/star-1.gif";
}else if(item.content_type!='resume' && item.org_member_type!='Foundation'){
	popularity_img_filename="";//"imgs/star-0.gif";
}

if(item.content_type=='organization'){
	popularity_link='';
	popularity_img_filename='';
}

if(tm_member == 1){
	member_class='member listing';
}
//console.log('preview item.org_member_type is '+item.org_member_type);
var org_link = '';
var portal_url = $('#portal_name').html();
if(portal_url.length>0)	portal_url += '/';
var url_alias_w_portal = portal_url + item.url_alias;
var org_url_alias_w_portal = portal_url + item.org_url_alias;

  if(item.content_type!='organization' && item.content_type!='resume'){
	  if(item.org_url_alias===undefined){
		  if(item.org_name===undefined){}else{
	  		org_link = '<a class="org_link" href="/' + url_alias_w_portal + '.jsp">' + item.org_name + '</a>';
		  }
	  }else{
	  	org_link = '<a class="org_link" href="/' + org_url_alias_w_portal + '.jsp">' + item.org_name + '</a>';
	  }
  }
  var output = ''
  if(item.content_type=='resume'){
	  output=
		'<div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="/profiles/' + item.username + '" class="opp_link" >' + 
			item.title + '</a>&nbsp;&nbsp;' + 
			org_link + '<br>';
	output += snippet + '</div>';
  }else if(popularity_link.length > 0){
	  output=
		'<div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="/' + url_alias_w_portal + '.jsp" class="opp_link">' + 
			item.title + '</a>' + popularity_link + '&nbsp;&nbsp;' + org_link + '<br>';
	  output += snippet + '</div>';
  }else if(popularity_img_filename.length < 2){
	  output=
		'<div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="/' + url_alias_w_portal + '.jsp" class="opp_link" >' + 
			item.title + '</a>&nbsp;&nbsp;' + 
			'&nbsp;&nbsp;' + org_link + '<br>';
	output += snippet + '</div>';
  }else if(source == 'AllForGood' || source == 'Meet The Need'){
	  output=
		'<div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="/' + url_alias_w_portal + '.jsp" class="opp_link" >' + 
			item.title + '</a>&nbsp;&nbsp;' + 
			'<img src="http://www.christianvolunteering.org/' + popularity_img_filename + '" />&nbsp;&nbsp;' + org_link + '<br>';
	output += snippet + '</div>';
  }else {
	  output=
		'<div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="/' + url_alias_w_portal + '.jsp" class="opp_link" >' + 
			item.title + '</a>&nbsp;&nbsp;' + 
			'<img src="http://www.christianvolunteering.org/' + popularity_img_filename + '" title="Popularity Based on Automated Formula"/>&nbsp;&nbsp;' + org_link + '<br>';
	output += snippet + '</div>';
  }
  return output;
};

AjaxSolr.theme.prototype.snippet = function (doc) {
  var output = '';
  var stm_details = '';
  var other_details = '';
  var scheduled_dt = '';
  var date_print = '';
  var location = '';
  var last_updated_parse = '';
  var service_areas = '';
  if(doc.service_areas !== undefined) service_areas = doc.service_areas;
  
  var description = '';
  if(doc.description !== undefined) description = doc.description;
  // make it blank, though, for Foundations
  if(doc.org_member_type=='Foundation') description = '';
  
//console.log('description is ' + description);  
  var update_dt = doc.last_updated_dt;
  var benefits_offered = '';
  if(doc.benefits_offered !== undefined) benefits_offered = doc.benefits_offered;
  var education_level = '';
  if(doc.education_level !== undefined) education_level = ' ' + doc.education_level;
  
  if(education_level.indexOf('Doctorate') != -1){
	  education_level='Doctorate';
  }else if(education_level.indexOf('Master') != -1){
	  education_level='Master\'s Degree';
  }else if(education_level.indexOf('Bachelor') != -1){
	  education_level='Bachelor\'s Degree';
  }else if(education_level.indexOf('Associate') != -1){
	  education_level='Associate\'s Degree';
  }else if(education_level.indexOf('High School') != -1){
	  education_level='High School Diploma or Equivalent';
  }else{
	  education_level='';
  }
  var duration = doc.trip_length;
  var frequency_i = '';
  if(doc.frequency_i !== undefined) frequency_i = doc.frequency_i;
  var frequency = '';
  if(doc.frequency !== undefined) frequency = doc.frequency;
  var scheduled_i = '';
  if(doc.scheduled_i !== undefined) scheduled_i = doc.scheduled_i;
  var start_dt = '';
  if(doc.start_date_dt !== undefined) start_dt = doc.start_date_dt;
  var end_dt = '';
  if(doc.end_date_dt !== undefined) end_dt = doc.end_date_dt;
  
  var revenue_display ='';
//  if(doc.revenue_display !== undefined) revenue_display = doc.revenue_display;
  var revenue ='';
  if(doc.revenue !== undefined) revenue = doc.revenue;
  
  var total_giving_display ='';
//  if(doc.total_giving_display !== undefined) total_giving_display = doc.total_giving_display;
  var total_giving ='';
  if(doc.total_giving_display !== undefined) {
	  if(doc.total_giving_dollars != '$0') {
		  total_giving = doc.total_giving_display;
	  }
  }
  
  var foundation_type ='';
  if(doc.foundation_type !== undefined) {
	  if(doc.foundation_type.length>0) {
		  foundation_type = doc.foundation_type;
	  }
  }
  
  var program_type = '';
  if(doc.program_type !== undefined) program_type = doc.program_type;
  var primary_opp_type = '';
  if(doc.primary_opp_type !== undefined) primary_opp_type = doc.primary_opp_type;
  var denom_affil = '';
  if(doc.denom_affil !== undefined) denom_affil = doc.denom_affil;
  var org_affil = '';
  if(doc.org_affil !== undefined) org_affil = doc.org_affil;
  var doc_city = '';
  if(doc.city !== undefined) doc_city = doc.city;
  var doc_city_tax = '';
  if(doc.city_tax !== undefined) doc_city_tax = doc.city_tax;
  var doc_country = '';
  if(doc.country !== undefined) doc_country = doc.country;
  var looking_for = '';
  if(doc.looking_for !== undefined) looking_for = doc.looking_for;
  if(doc.position_type == 'Virtual Volunteering (from home)'){
	  location = doc.position_type;
  }else if(doc.org_member_type=='Foundation'){
	  if(doc_city_tax.length>0){
		  location = doc_city_tax;
	  }
	  if(doc.province_tax!== undefined){
		  if(doc.province_tax.length>0){
			  if(location.length>0) location +=  ', ' ;
			  var province_tax = ''+doc.province_tax;
			  province_tax = province_tax.replace(/\,/g, ', ');
			location +=  province_tax;
		  }
	  }
	  if(doc.country_tax!== undefined){
		  if(doc.country_tax.length>0){
			  if(location.length>0) location +=  ', ' ;
			location +=  doc.country_tax;
		  }
	  }
	  
	  
  }else{
	  if(doc_country=='US' || doc_country=='us'){
		  if(doc_city.length>0 && doc.org_member_type!='Foundation'){
			  location = doc_city;
		  }else if(doc_city_tax.length>0){
			  location = doc_city_tax;
		  }
		  if(doc.province_tax!== undefined && location!=doc_city_tax){
			  if(doc.province_tax.length>0){
			  	location +=  ', ' + doc.province_tax;
			  }
		  }else if(doc.country_tax!== undefined){
			  if(doc.country_tax.length>0){
			  	location +=  ', ' + doc.country_tax;
			  }
		  }
	  }else{
		  if(doc_city.length>0 && doc.org_member_type!='Foundation'){
			  location = doc_city ;
		  }else if(doc_city_tax.length>0){
			  location = doc_city_tax;
		  }
		  if(doc.country_tax!== undefined){
			  if(doc.country_tax.length>0){
				  if(location.length>0) location +=  ', ' ;
			  	location +=  doc.country_tax;
			  }
		  }
	  }
  }
	if(update_dt === undefined){
		update_dt = '';
	}else{
	  last_updated_parse = update_dt.split('-');
	  if (last_updated_parse.length > 1) {
		  var year=last_updated_parse[0];
		  var month=last_updated_parse[1];
		  var remaining=last_updated_parse[2];
		  var remaining_parse = remaining.split('T');
		  var calendar_date = remaining_parse[0];
		  if(year=='1970' || year=='1969'){
			  update_dt = '';
		  }else{
			  update_dt = month + '/' + calendar_date + '/' + year;
		  }
	  }
	}
	service_areas=''+service_areas;
	service_areas = service_areas.replace("Indirectly Serving People","");
	service_areas = service_areas.replace("Directly Serving People","");
	service_areas = service_areas.replace("Social Services","");
	service_areas = service_areas.replace(/\,\,/g, ', ');
	service_areas = service_areas.replace(/\,/g, ', ');
  if(benefits_offered===undefined){
  }else if(benefits_offered.length>0){
	  benefits_offered=''+benefits_offered;
	  benefits_offered = benefits_offered.replace(/\,/g, ',&nbsp;');
	stm_details += " <b>Benefits Offered:</b>&nbsp;" + benefits_offered + "&nbsp;";
  }
  if(duration===undefined){
  }else if(duration.length>0){
	stm_details += " <b>Duration:</b>&nbsp;" + duration + "&nbsp;";
  }
	if(stm_details.length>1){
		stm_details = "<br>" + stm_details.substring(1);
	}
	
	if(scheduled_i === undefined){
	}else{
		if(scheduled_i==8132 || scheduled_i==8133 || scheduled_i==8134){
			var start_dt_parse = '';
			if(start_dt === undefined){
			}else if(start_dt.length>0){
			  start_dt_parse = start_dt.split('-');
			  if (start_dt_parse.length > 1) {
				  var year=start_dt_parse[0];
				  var month=start_dt_parse[1];
				  var remaining=start_dt_parse[2];
				  var remaining_parse = remaining.split('T');
				  var calendar_date = remaining_parse[0];
				  start_dt = month + '/' + calendar_date + '/' + year;
				  scheduled_dt = start_dt;
				  scheduled_dt_short = month + '/' + calendar_date;
			  }
			}
			var end_dt_parse = '';
			if(end_dt === undefined){
			}else if(end_dt.length>0){
			  end_dt_parse = end_dt.split('-');
			  if (end_dt_parse.length > 1) {
				  var year=end_dt_parse[0];
				  var month=end_dt_parse[1];
				  var remaining=end_dt_parse[2];
				  var remaining_parse = remaining.split('T');
				  var calendar_date = remaining_parse[0];
				  end_dt = month + '/' + calendar_date + '/' + year;
				  if(start_dt!=end_dt){
					  scheduled_dt+=' - '+end_dt;
				  }
			  }
			}
			if(scheduled_i==8133 || scheduled_i==8134){
				if(frequency===undefined){
				}else if(frequency.length>0){
					if(scheduled_dt.length>0){
						date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>" + frequency;
						if(scheduled_i==8133){// every year
							date_print += " on: </b> " + scheduled_dt_short + ", annually&nbsp;&nbsp;";
						}else if(scheduled_i==8134){// just the one time/year
							date_print += " on: </b> " + scheduled_dt + "&nbsp;&nbsp;";
						}else{
							date_print += "</b>&nbsp&nbsp";
						}
					}else if(frequency.indexOf('One-time')!=-1){
						date_print = " <b>" + frequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
					}else if(frequency_i > 0){
						date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>Frequency:</b> " + frequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
				}
			}else if(frequency===undefined){
			}else if(frequency.length>0){
				if(frequency.indexOf('One-time')!=-1){
					date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>" + frequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
				}else{
					date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>Frequency:</b> " + frequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
				}
			}
		}
	}
	var other_details_count = 0;
	if(primary_opp_type===undefined){
	}else if(primary_opp_type.length>0){
//		primary_opp_type = primary_opp_type.replace(/\,/g, ',&nbsp;');
		other_details += "<b>Primary Types of Opportunities:</b>&nbsp;" + primary_opp_type + "&nbsp;";
		other_details_count++;
	}
	if(program_type===undefined){
	}else if(program_type.length>0){
		if( other_details.length>1 )	other_details += '<br />';
		other_details += "<b>Program Type:</b>&nbsp;" + program_type + "&nbsp;";
		other_details_count++;
	}
	if(org_affil===undefined){
	}else if(org_affil.length>1 && doc.content_type=='organization'){
//		org_affil = org_affil.replace(/\,/g, ',&nbsp;');
		if( other_details.length>1 )	other_details += '<br />';
		other_details += "<b>Organizational Affiliations:</b>&nbsp;" + org_affil + "&nbsp;";
		other_details_count++;
	}
	if(denom_affil===undefined){
	}else if(denom_affil.length>1 && doc.content_type=='organization'){
//		org_affil = org_affil.replace(/\,/g, ',&nbsp;');
		if( other_details.length>1 )	other_details += '<br />';
		other_details += "<b>Denominational Affiliations:</b>&nbsp;" + denom_affil + "&nbsp;";
		other_details_count++;
	}
	if(looking_for===undefined){
	}else if(looking_for.length>0 && doc.content_type=='resume'){
		looking_for=''+looking_for;
		looking_for = looking_for.replace(/\,/g, ',&nbsp;');
		if( other_details.length>1 )	other_details += '<br />';
		other_details += "<b>Looking For:</b>&nbsp;" + looking_for + "&nbsp;";
		other_details_count++;
	}
	if(education_level===undefined){
	}else if(education_level.length>0 && doc.content_type=='resume'){
		if( other_details.length>1 )	other_details += '<br />';
		other_details += "<b>Highest Level of Education:</b>&nbsp;" + education_level + "&nbsp;";
		other_details_count++;
	}
	
	// revenue - if not already with a $, then convert it
	if(revenue>0){
			var DecimalSeparator = Number("1.2").toLocaleString().substr(1,1);

			var AmountWithCommas = revenue.toLocaleString();
			var arParts = String(AmountWithCommas).split(DecimalSeparator);
			var intPart = arParts[0];
			var decPart = (arParts.length > 1 ? arParts[1] : '');
			decPart = (decPart + '00').substr(0,2);
			
			revenue_display = ' <b>Revenue:</b> $' + intPart + DecimalSeparator + decPart;
	}
	
	// revenue - if not already with a $, then convert it
	if(total_giving.length>0){
			total_giving_display = ' <b>Total Giving:</b> ' + total_giving;
	}


  // replace all html tags for display of teasers to not affect layout of page
  description = description.replace(/<.*?>/g, '');
  if (description.length > 200) {
    output += '<div class="teaser">' + description.substring(0, 200) + '...';
    output += '<span style="display:none;">' + description.substring(200);
    output += '</span>';
	if(doc.url_alias !== undefined)	output += ' <a href="' + doc.url_alias + '.jsp" class="more_link">more ></a>';
	output += '</div>';
  }
  else {
    output += '<div class="teaser">' + description;
	if(doc.url_alias !== undefined)	output += ' <a href="' + doc.url_alias + '.jsp" class="more_link">more ></a>';
	output += '</div>';
  }
  output += '<div class="opp-info">';
  if( doc.org_member_type=='Foundation' )		output += '<b>Funding Interests:</b> ' + service_areas +'<br />';
  else if( doc.content_type!='resume' )	output += '<b>Service Areas:</b> ' + service_areas +'<br />';
  if(other_details.length>0 || stm_details.length>0){
  	output += other_details + stm_details + ' <br>';
  }
  if(foundation_type.length>0 && foundation_type != ''){
  	output += '<b>Type of Foundation:</b> ' + foundation_type + ' <br>';
  }
  if(location.length > 1){
	  if( doc.org_member_type=='Foundation' )	output += '<b>Geographic Scope:</b> ' + location;
	  else output += '<b>Location:</b> ' + location;
  }
  if(revenue_display.length > 1){
	  output += revenue_display;
  }
  if(total_giving_display.length > 1){
	  output += total_giving_display;
  }
  if(date_print.length > 1)	output += date_print;
  if(year=='1970' || year=='1969'){
  }else{
	  output += ' <b>Date Last Updated:</b> ' + update_dt + ' ';
  }
  output += '</div><hr>';

  return output;
};


AjaxSolr.theme.prototype.tag = function (value, weight, handler) {
  return $('<a href="#" class="tagcloud_item"/>').text(value).addClass('tagcloud_size_' + weight).click(handler);
};

AjaxSolr.theme.prototype.distance_dropdown = function (facet_term, count_term, i,l,field, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
//console.log('facet_term is '+facet_term);  
	if ( facet_term == 'AllForGood' || facet_term == 'All For Good' ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
	}else if ( facet_term == 'MeetTheNeed' || facet_term == 'Meet The Need' ){
		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/meet_the_need.png">'+' &#40;'+count_term+'&#41;';
//	}else if ( facet_term == 'SimplyHired' || facet_term == 'Simply Hired' ){
//		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/simply_hired.png">'+' &#40;'+count_term+'&#41;';
	  }else if ( 
	  	facet_term.indexOf('bil_import') != -1				||
		facet_term.indexOf('Theological Colleges') != -1 	|| 
		facet_term.indexOf('MissionFinder.org') != -1 		|| 
		facet_term.indexOf('helpingoverseasdirectory.org') != -1 
	  ){
		return;
	}else if ( i >= 10 ) {
//		output = '<span class="collapsed" name="facet_'+field+'">' + output;
//	    return $('<a href="#" class="collapsed"/>').html(output).click(handler);
	}else{
//		output = output + '<br />';
//		return $('<a href="#"/>').html(output).click(handler);
	}
		return $('<a href="#"/>').html(output).click(handler);
};
AjaxSolr.theme.prototype.facet_more_link = function (field) {
  return $('<a href="#'+field+'" style="display:block" onclick="toggle_facet(\'facet_'+ field +'\'); return false;"/>').html('See more &gt;&gt;');
};


AjaxSolr.theme.prototype.facet_form_link = function (facet_term, count_term, string_URL, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
//console.log('facet_term is '+facet_term);  
	if ( facet_term == 'AllForGood' || facet_term == 'All For Good' ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
//	}else if ( facet_term == 'SimplyHired' || facet_term == 'Simply Hired' ){
//		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/simply_hired.png" alt="SimplyHired">'+' &#40;'+count_term+'&#41;';
	}else if ( facet_term == 'MeetTheNeed' || facet_term == 'Meet The Need' ){
		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/meet_the_need.png">'+' &#40;'+count_term+'&#41;';
	}else if ( facet_term == 'Interserve.org' ){
		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/interserve.png">'+' &#40;'+count_term+'&#41;';
	  }else if ( 
	  	facet_term.indexOf('bil_import') != -1				||
		facet_term.indexOf('Theological Colleges') != -1 	|| 
		facet_term.indexOf('MissionFinder.org') != -1 		|| 
		facet_term.indexOf('helpingoverseasdirectory.org') != -1 
	  ){
		return;
	  }
	
	return $('<a href="' + string_URL + '"/>').html(output).click(handler);
};

AjaxSolr.theme.prototype.facet_landingpage_link = function (facet_term, count_term, string_URL, i, l, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
  if(l-1>i && i!=4){
	  output += ',&nbsp;';
  }else{
	  output += '&nbsp;';
  }
	if ( facet_term == 'AllForGood' || facet_term == 'All For Good' ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
	}else if ( facet_term == 'MeetTheNeed' || facet_term == 'Meet The Need' ){
		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/meet_the_need.png">'+' &#40;'+count_term+'&#41;';
	}else if ( facet_term == 'Interserve.org' ){
		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/interserve.png">'+' &#40;'+count_term+'&#41;';
//	}else if ( facet_term == 'SimplyHired' || facet_term == 'Simply Hired' ){
//		output='<img src="http://www.christianvolunteering.org/images/feed_source_logos/small/simply_hired.png">'+' &#40;'+count_term+'&#41;';
	  }else if ( 
	  	facet_term.indexOf('bil_import') != -1				||
		facet_term.indexOf('Theological Colleges') != -1 	|| 
		facet_term.indexOf('MissionFinder.org') != -1 		|| 
		facet_term.indexOf('helpingoverseasdirectory.org') != -1 
	  ){
		return;
	  }
	return $('<a href="' + string_URL + '"/>').html(output).click(handler);
};
AjaxSolr.theme.prototype.facet_more_landingpage_link = function (field) {
  return $('<a href="#'+field+'" style="display:inline" onclick="toggle_facet(\'facet_'+ field +'\'); return false;"/>').html('See more &gt;&gt;');
};

AjaxSolr.theme.prototype.facet_link = function (facet_term, count_term, i,l,field, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
  if(field == 'source'){
	  var small_logo = facet_term.replace(/\ /g, '_').toLowerCase();
	  var index_dot = 0;
	  index_dot = small_logo.indexOf('.');
	  if(index_dot > 0){
		  small_logo = small_logo.substring(0,index_dot);
	  }
	  if(small_logo.length > 0 && !( facet_term == 'SimplyHired' || facet_term == 'Simply Hired' )){
		  small_logo = 'http://www.christianvolunteering.org/images/feed_source_logos/small/' + small_logo + '.png';
	  }
	  if ( facet_term == 'AllForGood' || facet_term == 'All For Good' ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png" alt="' + facet_term + '" /> &#40;'+count_term+'&#41;';
	  }else if ( 
		facet_term == 'helpingoverseasdirectory.org' 	|| 
		facet_term == 'MissionFinder.org' 
	  ){
		output= facet_term + ' &#40;'+count_term+'&#41;';
	  }else if ( small_logo.length > 37 && 
		!( facet_term=='Oscar.org.uk' || facet_term=='Theological Colleges')//|| facet_term=='SimplyHired' 
	  ){
		output='<img src="' + small_logo + '" alt="' + facet_term + '" title="' + facet_term + '" /> &#40;'+count_term+'&#41;';
	  }else if ( 
	  	facet_term.indexOf('bil_import') != -1				||
		facet_term.indexOf('Theological Colleges') != -1 	|| 
		facet_term.indexOf('MissionFinder.org') != -1 		|| 
		facet_term.indexOf('helpingoverseasdirectory.org') != -1 
	  ){
		return;
	  }
  }else if ( field == 'city_tax' && i >= 15) {
//		output = '<span class="collapsed" name="facet_'+field+'">' + output;
//	    return $('<a href="#" class="collapsed"/>').html(output).click(handler);
  }else if ( i >= 10 ) {
//		output = '<span class="collapsed" name="facet_'+field+'">' + output;
//	    return $('<a href="#" class="collapsed"/>').html(output).click(handler);
}else{
//		output = output + '<br />';
//		return $('<a href="#"/>').html(output).click(handler);
  }
//console.log('field is '+field);	
if(
!(	  	facet_term == '$0' || facet_term == 'Unknown')
){
		return $('<a href="#'+field+'='+facet_term+'"/>').html(output).click(handler);
}
};
AjaxSolr.theme.prototype.facet_more_link = function (field) {
  return $('<a href="#'+field+'" style="display:block" onclick="toggle_facet(\'facet_'+ field +'\'); return false;" />').html('See more &gt;&gt;');
};

AjaxSolr.theme.prototype.tab_link_init = function (facet_term, count_term, field, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
  var id_name = facet_term;
  if(facet_term=='Foundation'){
	  output='Foundations';
  }else if(facet_term=='organization'){
	  output='Organizations';
  }else if(facet_term=='job'){
	  output='Jobs';
  }else if(facet_term=='resume'){
	  output='Resumes';
  }else if(facet_term=='Local Volunteering (in person)'){
	  id_name='local';
	  output ='Local Volunteering &#40;'+count_term+'&#41;';
  }else if(facet_term=='Virtual Volunteering (from home)'){
	  id_name='virtual';
	  output ='Virtual Volunteering';// &#40;'+count_term+'&#41;';
  }else if(facet_term=='Short-term Missions / Volunteer Internship'){
	  id_name='stm';
	  output ='Short-term Missions &#40;'+count_term+'&#41;';
  }
  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" onclick="clicked_'+id_name+'"/>').html(output).click(handler).click('click_local()');
};

AjaxSolr.theme.prototype.tab_link = function (filter_flag, facet_term, handler) {
  var output = facet_term;
  var id_name = facet_term;
  var onclick_called = 'clicked_local()';
//console.log('CityVision class is '+$('#CityVision').attr("class"));
//console.log('organization class is '+$('#organization').attr("class"));
//console.log('                     facet term is '+facet_term+';   filter_flag term is '+filter_flag);
  if(facet_term=='Foundation'){
	  field='org_member_type';
	  output='Foundations';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='organization'){
	  field='content_type';
	  output='Organizations';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='CityVision'){
	  field='intern_type';
	  output='Internships';
	  if(filter_flag == true){
		  return $('<a href="#fq='+field+':&quot;City Vision Internship&quot;" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }else{
		  return $('<a href="#fq='+field+':&quot;City Vision Internship&quot;" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='%22City%20Vision%22'){
	  field='intern_type';
	  output='Internships';
	  if(filter_flag == true){
		  return $('<a href="#fq='+field+':&quot;City Vision Internship&quot;" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }else{
		  return $('<a href="#fq='+field+':&quot;City Vision Internship&quot;" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='City Vision' || facet_term=='City Vision Internship'){
	  field='intern_type';
	  output='Internships';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='job'){
	  field='content_type';
	  output='Jobs';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='resume'){
	  field='content_type';
	  output='Resumes';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='Local Volunteering (in person)'){
	  field='position_type';
	  id_name='local';
	  output ='Local Volunteering';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='Virtual Volunteering (from home)'){
	  field='position_type';
	  id_name='virtual';
	  output ='Virtual';// &#40;'+count_term+'&#41;';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else if(facet_term=='Short-term Missions / Volunteer Internship'){
	  field='position_type';
	  id_name='stm';
	  output ='Short-term Missions';
	  if(filter_flag == true){
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class="active"/>').html(output).click(handler);
	  }else{
		  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'" class=""/>').html(output).click(handler);
	  }
  }else{
	  return $('<a href="#'+field+'='+facet_term+'" id="'+id_name+'"/>').html(output).click(handler);
  }
};

AjaxSolr.theme.prototype.no_items_found = function () {
  return '';
};

})(jQuery);