(function ($) {

AjaxSolr.theme.prototype.result = function (item_count, item, snippet) {
var source = '';
if(item.source !== undefined)	source = item.source;
var member_class='listing';
var iPopularity = '';
if(item.popularity !== undefined)	iPopularity = item.popularity;
var popularity_img_filename = '';
var popularity_link = '';
if(item.cvintern_screened==2){
	popularity_img_filename='<img src="http://www.christianvolunteering.org/imgs/star.jpg" width="12px" title="Recommended Candidate Based on Screening Interview"/>';
}
var url_alias_w_portal = "/cityvision/email.do?method=loadOneApplication&nid="+item.applic_nid;
	var source = '';
	if(item.source !== undefined)	source = item.source;
	var res_file = '';
	if(item.res_file !== undefined)	res_file = item.res_file;
	var url_alias_w_portal = "/cityvision/email.do?method=loadOneApplication&nid="+item.applic_nid;
	if(source=='SalesForce'){
		url_alias_w_portal+="&src=sf";
	}
  var output = '';
  var download_link = '';
  
if(source=='SalesForce' && res_file.length>0){
	url_alias_w_portal+="&src=sf";
	download_link = '<a style="color: #FFFFFF; font-weight: bold;" href="/fileDownload.do?resume=true&nid='+item.applic_nid+'&src=sf" class="volunteer button" src="../template/image/help.gif">	download resum&eacute;</a> ';	
}else if(res_file.length>0){
	download_link = '<a style="color: #FFFFFF; font-weight: bold;" href="fileDownload.do?resume=true&uid='+item.uid+'" class="volunteer button" src="../template/image/help.gif">	download resum&eacute;</a> ';	
}

  var output=
		'<tr><td><div class="' + member_class + '">' + 
			item_count  + 
			'. <a href="' + url_alias_w_portal + '" class="opp_link" >' + 
			item.title + '</a>&nbsp;&nbsp;' +  popularity_img_filename + '&nbsp;&nbsp;' + download_link + '<br>';
	output += snippet + '</div></td><td align="right" valign="top">'+item.email+'</td></tr><tr><td colspan="2"><hr></td></tr>';
  return output;
};

AjaxSolr.theme.prototype.snippet = function (doc) {
	var source = '';
	if(doc.source !== undefined)	source = doc.source;
	var url_alias_w_portal = "/cityvision/email.do?method=loadOneApplication&nid="+doc.applic_nid;
	if(source=='SalesForce'){
		url_alias_w_portal+="&src=sf";
	}
  var output = '';
  
  var location = '';
  var update_dt = doc.last_updated_dt;
  var last_updated_parse = '';
  
  output += '<div class="opp-info"  style="margin-top: 10px;">';
  var has_bachelors = '';
  if(doc.has_bachelors !== undefined) {
	  if(doc.has_bachelors == 'Yes'){
		  has_bachelors = "<strong>Has Bachelor's Degree</strong><br>";
	  }
  }
  output += has_bachelors;
  
  var intern_type = '';
  if(doc.intern_type !== undefined) {
	  if(doc.intern_type.length>0){
		  intern_type = "<strong>Intern Type:</strong>&nbsp;" + doc.intern_type + "<br>";
	  }
  }
  output += intern_type;
  
  var pos_pref = '';
  if(doc.pos_pref !== undefined) {
	  if(doc.pos_pref.length>0){
		  pos_pref = "<strong>Position Preference:</strong>&nbsp;" + doc.pos_pref + "<br>";
	  }
  }
  output += pos_pref;

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
  output += "<strong>Location:</strong>&nbsp;"+location + "<br>";;

  var geo_pref = '';
  if(doc.geo_pref !== undefined) {
	  if(doc.geo_pref.length>0){
		  geo_pref = "<strong>Geographic Preference:</strong>&nbsp;" + doc.geo_pref + "<br>";
	  }
  }
  output += geo_pref;

  var intern_length = '';
  if(doc.intern_length !== undefined) {
	  if(doc.intern_length.length>0){
		  intern_length = "<strong>Internship Length:</strong>&nbsp;" + doc.intern_length + "<br>";
	  }
  }
  output += intern_length;
  

  var gender = '';
  if(doc.gender !== undefined) {
	  if(doc.gender.length>0){
		  gender = "<strong>Gender:</strong>&nbsp;" + doc.gender + "&nbsp;&nbsp;&nbsp;";
	  }
  }
  output += gender;

  var age_range = '';
  if(doc.age_range !== undefined) {
	  if(doc.age_range.length>0){
		  age_range = "<strong>Age Range:</strong>&nbsp;" + doc.age_range + "&nbsp;&nbsp;&nbsp;";
	  }
  }
  output += age_range;

  var credits_range = '';
  if(doc.credits_range !== undefined) {
	  if(doc.credits_range.length>0){
		  credits_range = "<strong>Credits Range:</strong>&nbsp;" + doc.credits_range + "<br>";
	  }
  }
  output += credits_range;
  
  
  
//console.log('description is ' + description);  
  // replace all html tags for display of teasers to not affect layout of page
	if(doc.url_alias !== undefined)	output += ' <a href="' + url_alias_w_portal + '" class="more_link">more ></a>';


  if(year=='1970' || year=='1969'){
  }else{
	  output += ' <strong>Date Last Updated:</strong> ' + update_dt + ' ';
  }
  output += '</div>';

  return output;
};


AjaxSolr.theme.prototype.tag = function (value, weight, handler) {
  return $('<a href="#" class="tagcloud_item"/>').text(value).addClass('tagcloud_size_' + weight).click(handler);
};

AjaxSolr.theme.prototype.distance_dropdown = function (facet_term, count_term, i,l,field, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
//console.log('facet_term is '+facet_term);  
	if ( i >= 10 ) {
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
	return $('<a href="' + string_URL + '"/>').html(output).click(handler);
};

AjaxSolr.theme.prototype.facet_landingpage_link = function (facet_term, count_term, string_URL, i, l, handler) {
  var output = facet_term+' &#40;'+count_term+'&#41;';
  if(l-1>i && i!=4){
	  output += ',&nbsp;';
  }else{
	  output += '&nbsp;';
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
  if(facet_term=='job'){
	  output='Jobs';
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
  if(facet_term=='CityVision'){
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