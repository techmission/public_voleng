(function ($) {


AjaxSolr.theme.prototype.result = function (item_count, item, snippet) {
	

var tm_member = item.tm_member_i;
var source = item.source;
var member_class='listing';
var iPopularity = item.popularity;
var popularity_img_filename = '';
if(source == 'AllForGood'){
	popularity_img_filename="afg-blue_60.png";
}else if(iPopularity > 110){
	popularity_img_filename="star-5.gif";
}else if(iPopularity > 80){
	popularity_img_filename="star-4.gif";
}else if(iPopularity > 50){
	popularity_img_filename="star-3.gif";
}else if(iPopularity > 25){
	popularity_img_filename="star-2.gif";
}else if(iPopularity > 0){
	popularity_img_filename="star-1.gif";
}else{
	popularity_img_filename="star-0.gif";
}
if(tm_member == 1){
	member_class='member listing';
}

  var output = 
	'<div class="' + member_class + '">' + 
		item_count  + 
		'. <a href="http://www.christianvolunteering.org/' + item.url_alias + '.jsp" class="opp_link" >' + 
		item.title + '</a>&nbsp;&nbsp;' + 
		'<img src="http://www.christianvolunteering.org/imgs/' + popularity_img_filename + '"/>&nbsp;&nbsp;' + 
		'<a class="org_link" href="http://www.christianvolunteering.org/' + item.org_url_alias + '.jsp">' + item.org_name + '</a><br>';
//  snippet = snippet.replace(/<.*?>/g, '');
  output += snippet + '</div>';


//output = '';

  return output;
};

//		String aszTemp = value.replaceAll("\\<[^>]*>","");


AjaxSolr.theme.prototype.snippet = function (doc) {
  var output = '';
  var stm_details = '';
  var scheduled_dt = '';
  var date_print = '';
  var location = '';
  var last_updated_parse = '';
  var update_dt = doc.last_updated_dt;
  var description = doc.description;
  var benefits_offered = doc.benefits_offered;
  var duration = doc.trip_length;
  var frequency_i = doc.frequency_i;
  var frequency = doc.frequency;
  var scheduled_i = doc.scheduled_i;
  var start_dt = doc.start_date_dt;
  var end_dt = doc.end_date_dt;
  if(benefits_offered===undefined){
  }else if(benefits_offered.length>0){
	stm_details += " <b>Benefits Offered:</b>&nbsp;" + benefits_offered + "&nbsp;";
  }
  if(duration===undefined){
  }else if(duration.length>0){
	stm_details += " <b>Duration:</b>&nbsp;" + duration + "&nbsp;";
  }
	if(stm_details.length>1){
		stm_details = "<br>" + stm_details.substring(1);
	}
  if(doc.position_type == 'Virtual Volunteering (from home)'){
	  location = doc.position_type;
  }else{
	  if(doc.country=='US' || doc.country=='us'){
		  if(doc.city.length>0){
			  location = doc.city + ', ';
		  }
		  location += doc.province_tax;
	  }else{
		  location = doc.country_tax;
	  }
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
					}else if(frequency.indexOf("One-time")!=-1){
						date_print = " <b>" + frequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
					}else if(frequency_i > 0){
						date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>Frequency:</b> " + frequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
				}
			}else if(frequency_i > 0){
				date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>Frequency:</b> " + frequency +"&nbsp;&nbsp;&nbsp;&nbsp;";
			}else if(frequency.indexOf("One-time")!=-1){
				date_print = "&nbsp;&nbsp;&nbsp;&nbsp;<b>" + frequency +"</b>&nbsp;&nbsp;&nbsp;&nbsp;";
			}
		}
	}
	if(update_dt === undefined){
	}else{
	  last_updated_parse = update_dt.split('-');
	  if (last_updated_parse.length > 1) {
		  var year=last_updated_parse[0];
		  var month=last_updated_parse[1];
		  var remaining=last_updated_parse[2];
		  var remaining_parse = remaining.split('T');
		  var calendar_date = remaining_parse[0];
		  update_dt = month + '/' + calendar_date + '/' + year;
	  }
	}
  // replace all html tags for display of teasers to not affect layout of page
  description = description.replace(/<.*?>/g, '');
  if (description.length > 200) {
    output += '<div class="teaser">' + description.substring(0, 200) + '...';
    output += '<span style="display:none;">' + description.substring(200);
//    output += '</span> <a href="#" class="more more_link">more</a></div>';
    output += '</span> <a href="http://www.christianvolunteering.org/' + doc.url_alias + '.jsp" class="more_link">more ></a></div>';
  }
  else {
    output += '<div class="teaser">' + description + ' <a href="http://www.christianvolunteering.org/' + doc.url_alias + '.jsp" class="more_link">more ></a></div>';
  }
  output += '<div class="opp-info"><b>Service Areas:</b> ' + doc.service_areas + stm_details + ' <br>';
  output += '<b>Location:</b> ' + location + date_print + ' <b>Date Last Updated:</b> ' + update_dt + ' ';
  output += '</div><hr>';

  return output;
};


AjaxSolr.theme.prototype.tag = function (value, weight, handler) {
  return $('<a href="#" class="tagcloud_item"/>').text(value).addClass('tagcloud_size_' + weight).click(handler);
};

AjaxSolr.theme.prototype.distance_dropdown = function (facet_term, count_term, i,l,field, handler) {
//  return $('<a href="#"/>').text(value+' ('+count+')').click(handler);
  var output = facet_term+' &#40;'+count_term+'&#41;';
	if ( facet_term == "AllForGood" ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
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
//  return $('<a href="#"/>').text(value+' ('+count+')').click(handler);
  var output = facet_term+' &#40;'+count_term+'&#41;';
	if ( facet_term == "AllForGood" ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
	}
	return $('<a href="' + string_URL + '"/>').html(output).click(handler);
};

AjaxSolr.theme.prototype.facet_link = function (facet_term, count_term, i,l,field, handler) {
//  return $('<a href="#"/>').text(value+' ('+count+')').click(handler);
  var output = facet_term+' &#40;'+count_term+'&#41;';
	if ( facet_term == "AllForGood" ){
		output='<img src="http://www.christianvolunteering.org/imgs/afg-blue_60.png">'+' &#40;'+count_term+'&#41;';
	}else if ( i >= 10 ) {
//		output = '<span class="collapsed" name="facet_'+field+'">' + output;
//	    return $('<a href="#" class="collapsed"/>').html(output).click(handler);
	}else{
//		output = output + '<br />';
//		return $('<a href="#"/>').html(output).click(handler);
	}
		return $('<a href="#'+field+'='+facet_term+'"/>').html(output).click(handler);
};
AjaxSolr.theme.prototype.facet_more_link = function (field) {
  return $('<a href="#'+field+'" style="display:block" onclick="toggle_facet(\'facet_'+ field +'\'); return false;"/>').html('See more &gt;&gt;');
};

AjaxSolr.theme.prototype.no_items_found = function () {
  return '';
};

})(jQuery);