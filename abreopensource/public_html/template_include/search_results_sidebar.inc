
<script language="javascript">

$('#menu_sidebar').click(function(){
//console.log('triggered');
	$('#sidebar').show();
});
function toggle_menu_sidebar(){
	var sidebar = document.getElementById("sidebar"); 
	var menu_sidebar = document.getElementById("menu_sidebar"); 
	if($('#sidebar').is(":hidden") ){
		$('#sidebar').show();
	}else{
		$('#sidebar').hide();
	}
}


function toggle_display(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapse_class=" collapse";
	var expanded_class=" expanded";
	var e = document.getElementById(facet_field); 
	if(e.style.display=="none"){
		e.style.display="block";
		e.parentNode.className=default_class+handle_class+expanded_class;
	}else{
		e.style.display="none";
		e.parentNode.className=default_class+handle_class+collapse_class;
	}
}
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
	var root_facet_element = document.getElementById(id); 
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class;

		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
				if(list_element.style == undefined){
				}else{
					var init_class = list_element.className;
					if(init_class=='filter collapsed'){
						list_element.style.display="block";
						list_element.className = default_class+expanded_class;
					}
				}
			}
		}
	}
	var more_link = 'more_link_'+facet_field;
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display='none'
		more_link_element.className = collapsed_class;
	}
}

function toggle_financial_data_results () {
//console.log('toggle financial data triggered; $("#total_giving li").size() is '+$("#total_giving li").size()+' $("#net_assets li").size() is '+$("#net_assets li").size()+' $("#assets li").size() is '+$("#assets li").size()+' $("#income li").size() is '+$("#income li").size()+' $("#expenditures li").size() is '+$("#expenditures li").size());		
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";
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
			$('#net_assets').show();
			$('#facet_net_assets').show();
			$('#facet_net_assets').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#assets li").size() < 1){
			$('#assets').hide();
			$('#facet_assets').hide();
			$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#assets').show();
			$('#facet_assets').show();
			$('#facet_assets').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#income li").size() < 1){
			$('#income').hide();
			$('#facet_income').hide();
			$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#income').show();
			$('#facet_income').show();
			$('#facet_income').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#expenditures li").size() < 1){
			$('#expenditures').hide();
			$('#facet_expenditures').hide();
			$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#expenditures').show();
			$('#facet_expenditures').show();
			$('#facet_expenditures').removeClass(collapsed_class).addClass(expanded_class);
		}
	}

$(document).ready(function() {
//console.log('document ready');
//console.log('aszFQSearch is <%=aszFQSearch%>');
<% if(aszContentType.contains("org")  && (!  aszFQSearch.contains("&org_member_type:Foundation")  )){ %>
	$('#facet_num_opps').show();
<% } %>	
<% 
String contentSearch = aszKeywordSearch;
%>
//console.log('contentSearch is <%=contentSearch%>');
<%
if(aszContentType.length()>0) {
	if(aszContentType.contains("opportunity")){
		if(! contentSearch.contains("content_type")){
			contentSearch += "&";//+aszContentType;
		}
	}else if(aszContentType.contains("organization")){
		if(! contentSearch.contains("content_type")){
			contentSearch += "&";//+aszContentType;
		}
	}
%>
//console.log('contentSearch is <%=contentSearch%>');
<%
}
if(aszPortal.contains("cityvision")){
%>
//	window.location.replace(window.location.href.replace('#', '') + '#<%=contentSearch%>');
<% } %>




<% if(aszPortal.contains("cityvision")){ %>
	window.location.replace(window.location.href.replace('#', '#<%=contentSearch%>'));
<% } %>
	

<% if(aszFormName.equals("cleanurl")){ %>
//	window.location.replace(window.location.href + '#<%=aszKeywordSearch%>');
<% } %>
	var filter_class = 'filter';
	var handle_class = ' handle';
	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';
	
	var filter_data = $('#filter_data_heading').text();
	var contenttype_data = $('#contenttype_heading').text();
	var service_areas_element = document.getElementById('service_areas'); 
	var group_size_element = document.getElementById('group_size'); 
	var benefits_element = document.getElementById('benefits_offered'); 
	var length_of_trip_element = document.getElementById('trip_length'); 
	var frequency_element = document.getElementById('frequency'); 
	var advanced_element = document.getElementById('advanced_facets'); 
	var country_element = document.getElementById('country_tax'); 
	var city_element = document.getElementById('city_tax'); 
	var region_element = document.getElementById('region'); 
	var denom_affil_element = document.getElementById('denom_affil'); 
	var affil_element = document.getElementById('org_affil'); 
	var member_type_element = document.getElementById('org_member_type'); 
	var primary_opp_type_element = document.getElementById('primary_opp_type'); 
	var great_for_element = document.getElementById('great_for'); 
	var looking_for_element = document.getElementById('looking_for'); 
	var third_party_element = document.getElementById('source'); 
	var total_giving_element = document.getElementById('total_giving'); 
	var net_assets_element = document.getElementById('net_assets'); 
	var assets_element = document.getElementById('assets'); 
	var income_element = document.getElementById('income'); 
	var expenditures_element = document.getElementById('expenditures'); 
		
	var facet_service_areas_element = document.getElementById('facet_service_areas'); 
	var facet_group_size_element = document.getElementById('facet_group_size'); 
	var facet_great_for_element = document.getElementById('facet_great_for'); 
	var facet_benefits_offered_element = document.getElementById('facet_benefits_offered'); 
	var facet_length_of_trip_element = document.getElementById('facet_trip_length'); 
	var facet_frequency_element = document.getElementById('facet_frequency'); 
	var facet_looking_for_element = document.getElementById('facet_looking_for'); 
	var facet_advanced_element = document.getElementById('facet_adv'); 
	var facet_country_element = document.getElementById('facet_country_tax'); 
	var facet_city_element = document.getElementById('facet_city_tax'); 
	var facet_region_element = document.getElementById('facet_region'); 
	var facet_denom_affil_element = document.getElementById('facet_denom_affil'); 
	var facet_affil_element = document.getElementById('facet_org_affil'); 
	var facet_member_type_element = document.getElementById('facet_org_member_type'); 
	var facet_primary_opp_type_element = document.getElementById('facet_primary_opp_type'); 
	var facet_third_party_element = document.getElementById('facet_afg'); 
	var facet_province_element = document.getElementById('facet_province_tax'); 
	var facet_city_element = document.getElementById('facet_city_tax'); 
	var facet_total_giving_element = document.getElementById('facet_total_giving'); 
	var facet_net_assets_element = document.getElementById('facet_net_assets'); 
	var facet_assets_element = document.getElementById('facet_assets'); 
	var facet_income_element = document.getElementById('facet_income'); 
	var facet_expenditures_element = document.getElementById('facet_expenditures'); 

	var hashURL = '' + window.location.hash;
//console.log('hashURL is: ' + hashURL);
	var hash_org = hashURL.indexOf("content_type:organization");
	var hash_foundation = hashURL.indexOf("org_member_type:Foundation");
	var hash_not_foundation = hashURL.indexOf("-org_member_type:Foundation");
	var hash_job = hashURL.indexOf("content_type:job");
	var hash_resume = hashURL.indexOf("content_type:resume");
	var hash_opp = hashURL.indexOf("content_type:opportunity");
	var hash_cv = hashURL.indexOf("City Vision");
	var hash_stm = hashURL.indexOf("Short-term Missions / Volunteer Internship");
	var hash_virt = hashURL.indexOf("Virtual Volunteering (from home)");

	
	$('#service_areas_label').text('Service Areas');
	
			
	$('#education_level').hide();
	$('#facet_education_level').hide();
	$('#facet_education_level').removeClass(expanded_class).addClass(collapsed_class);

	
//console.log('hash_org is: ' + hash_org + '; hash_opp is: ' + hash_opp + '; hash_stm is: ' + hash_stm + '; hash_virt is: ' + hash_virt);
	if(hash_foundation != -1 && hash_not_foundation==-1){
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#organization').removeClass('active');
		$('#job').removeClass('active');
		$('#resume').removeClass('active');
		$('#foundation').addClass('active');
			
		$('#srchmethod').val('Foundation');
		if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
			$('#filter_data_heading').text(filter_data);
		}
		
		
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Foundation');
		$('#contenttype_heading').text('Foundations');
		$('#service_areas_label').text('Funding Interests:');
		$('#facet_afg').hide();
							
		$('#simplyhired').hide();
		service_areas_element.style.display='block';
		facet_service_areas_element.className=filter_class+handle_class+expanded_class;
		
		facet_great_for_element.style.display='none';  
		facet_benefits_offered_element.style.display='none'; 
		facet_length_of_trip_element.style.display='none'; 

		group_size_element.style.display='none';
		facet_group_size_element.style.display='none';
		facet_group_size_element.className=filter_class+handle_class+collapsed_class;
		frequency_element.style.display='none';
		facet_frequency_element.style.display='none';
		facet_frequency_element.className=filter_class+handle_class+collapsed_class;
		benefits_element.style.display='none';
		facet_benefits_offered_element.style.display='none';
		facet_benefits_offered_element.className=filter_class+handle_class+collapsed_class;
		third_party_element.style.display='none';
//		facet_third_party_element.style.display='none';
		facet_third_party_element.className=filter_class+handle_class+collapsed_class;
				
		primary_opp_type_element.style.display='none';
		facet_primary_opp_type_element.style.display='none';
		facet_primary_opp_type_element.className=filter_class+handle_class+collapsed_class;

		advanced_element.style.display='block';
		facet_advanced_element.className=filter_class+handle_class+expanded_class;
		
		$('#affil').hide();
		$('#facet_org_affil').removeClass(expanded_class).addClass(collapsed_class);
		$('#affil').hide();
		$('#facet_denom_affil').removeClass(expanded_class).addClass(collapsed_class);
		
		member_type_element.style.display='none';
		facet_member_type_element.className=filter_class+handle_class+collapsed_class;
		country_element.style.display='none';
		facet_country_element.className=filter_class+handle_class+collapsed_class;
		region_element.style.display='block';
		facet_region_element.className=filter_class+handle_class+expanded_class;
		primary_opp_type_element.style.display='none';
		facet_primary_opp_type_element.className=filter_class+handle_class+collapsed_class;


		toggle_financial_data_results();
		
		$('#facet_region').hide();
		
		$('#city_tax').hide();
		$('#facet_city_tax').hide();
		$('#facet_city_tax').removeClass(expanded_class).addClass(collapsed_class);
		$('#country_tax').hide();
		$('#facet_country_tax').hide();
		$('#facet_country_tax').removeClass(expanded_class).addClass(collapsed_class);
		$('#province_tax').hide();
		$('#facet_province_tax').hide();
		$('#facet_province_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#geo_tax').show();
		$('#facet_geo_tax').show();
		$('#facet_geo_tax').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#foundation_type').show();
		$('#facet_foundation_type').show();
		$('#facet_foundation_type').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#foundation_location').show();
		$('#facet_foundation_location').show();
		$('#facet_foundation_location').removeClass(collapsed_class).addClass(expanded_class);
		

	}else if(hash_org != -1){
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#organization').addClass('active');
		$('#job').removeClass('active');
		$('#resume').removeClass('active');
		$('#foundation').removeClass('active');
			
		$('#srchmethod').val('organization');
		if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
			$('#filter_data_heading').text(filter_data);
		}
		
		
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Organization');
		$('#contenttype_heading').text('Organizations');
				
		$('#simplyhired').hide();


		toggle_financial_data_results();

		service_areas_element.style.display='none';
		facet_service_areas_element.className=filter_class+handle_class+collapsed_class;
		group_size_element.style.display='none';
		facet_group_size_element.style.display='none';
		facet_group_size_element.className=filter_class+handle_class+collapsed_class;
		frequency_element.style.display='none';
		facet_frequency_element.style.display='none';
		facet_frequency_element.className=filter_class+handle_class+collapsed_class;
		benefits_element.style.display='none';
		facet_benefits_offered_element.style.display='none';
		facet_benefits_offered_element.className=filter_class+handle_class+collapsed_class;
		third_party_element.style.display='none';
//		facet_third_party_element.style.display='none';
		facet_third_party_element.className=filter_class+handle_class+collapsed_class;
				
		primary_opp_type_element.style.display='block';
		facet_primary_opp_type_element.style.display='block';
		facet_primary_opp_type_element.className=filter_class+handle_class+expanded_class;

		advanced_element.style.display='block';
		facet_advanced_element.className=filter_class+handle_class+expanded_class;
		affil_element.style.display='block';
		facet_affil_element.className=filter_class+handle_class+expanded_class;
		denom_affil_element.style.display='block';
		facet_denom_affil_element.className=filter_class+handle_class+expanded_class;
		member_type_element.style.display='block';
		facet_member_type_element.className=filter_class+handle_class+expanded_class;
		country_element.style.display='block';
		facet_country_element.className=filter_class+handle_class+expanded_class;
		region_element.style.display='block';
		facet_region_element.className=filter_class+handle_class+expanded_class;
		primary_opp_type_element.style.display='block';
		facet_primary_opp_type_element.className=filter_class+handle_class+expanded_class;
	}else if(hash_job != -1){
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#organization').removeClass('active');
		$('#job').addClass('active');
		$('#resume').removeClass('active');
		$('#foundation').removeClass('active');
			
		$('#srchmethod').val('job');
		if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
			$('#filter_data_heading').text(filter_data);
		}
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Job');
		$('#contenttype_heading').text('Job Postings');

		$('#service_areas_label').text('Job Categories');
			

		total_giving_element.style.display='none';
		facet_total_giving_element.className=filter_class+handle_class+collapsed_class;
		net_assets_element.style.display='none';
		facet_net_assets_element.className=filter_class+handle_class+collapsed_class;
		assets_element.style.display='none';
		facet_assets_element.className=filter_class+handle_class+collapsed_class;
		income_element.style.display='none';
		facet_income_element.className=filter_class+handle_class+collapsed_class;
		expenditures_element.style.display='none';
		facet_expenditures_element.className=filter_class+handle_class+collapsed_class;

		facet_great_for_element.style.display='none';  
		facet_group_size_element.style.display='none'; 
		facet_benefits_offered_element.style.display='none'; 
		facet_length_of_trip_element.style.display='none'; 
		facet_frequency_element.style.display='none'; 

		facet_member_type_element.style.display='none';
		advanced_element.style.display='block';
		facet_advanced_element.className=filter_class+handle_class+expanded_class;
		$('#simplyhired').show();
	}else if(hash_resume != -1){
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#organization').removeClass('active');
		$('#job').removeClass('active');
		$('#resume').addClass('active');
		$('#foundation').removeClass('active');
			

		total_giving_element.style.display='none';
		facet_total_giving_element.className=filter_class+handle_class+collapsed_class;
		net_assets_element.style.display='none';
		facet_net_assets_element.className=filter_class+handle_class+collapsed_class;
		assets_element.style.display='none';
		facet_assets_element.className=filter_class+handle_class+collapsed_class;
		income_element.style.display='none';
		facet_income_element.className=filter_class+handle_class+collapsed_class;
		expenditures_element.style.display='none';
		facet_expenditures_element.className=filter_class+handle_class+collapsed_class;

		$('#srchmethod').val('resume');
		if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
			$('#filter_data_heading').text(filter_data);
		}
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Resume');
		$('#contenttype_heading').text('Resumes');
				
		$('#simplyhired').hide();
		group_size_element.style.display='none';
		facet_group_size_element.style.display='none';
		facet_group_size_element.className=filter_class+handle_class+collapsed_class;
		frequency_element.style.display='none';
		facet_frequency_element.style.display='none';
		facet_frequency_element.className=filter_class+handle_class+collapsed_class;

		great_for_element.style.display='none';
		facet_great_for_element.style.display='none';
		facet_great_for_element.className=filter_class+handle_class+collapsed_class;

		length_of_trip_element.style.display='none';
		facet_length_of_trip_element.style.display='none';
		facet_length_of_trip_element.className=filter_class+handle_class+collapsed_class;

		benefits_element.style.display='none';
		benefits_element.style.display='none';
		facet_benefits_offered_element.style.display='none';
		facet_benefits_offered_element.className=filter_class+handle_class+collapsed_class;
		region_element.style.display='none';
		region_element.style.display='none';
		facet_region_element.style.display='none';
		facet_region_element.className=filter_class+handle_class+collapsed_class;
		primary_opp_type_element.style.display='none';
		facet_primary_opp_type_element.style.display='none';
		facet_primary_opp_type_element.className=filter_class+handle_class+expanded_class;
		third_party_element.style.display='none';
		third_party_element.style.display='none';
		facet_third_party_element.style.display='none';
		affil_element.style.display='none';
		facet_affil_element.style.display='none';
		facet_affil_element.className=filter_class+handle_class+expanded_class;
		denom_affil_element.style.display='none';
		facet_denom_affil_element.style.display='none';
		facet_denom_affil_element.className=filter_class+handle_class+expanded_class;
		member_type_element.style.display='none';
		facet_member_type_element.style.display='none';
		facet_member_type_element.className=filter_class+handle_class+expanded_class;
				
		service_areas_element.style.display='block';
		facet_service_areas_element.className=filter_class+handle_class+expanded_class;

		looking_for_element.style.display='block';
		facet_looking_for_element.style.display='block';
		facet_looking_for_element.className=filter_class+handle_class+expanded_class;
		
		$('#education_level').show();
		$('#facet_education_level').show();
		$('#facet_education_level').removeClass(collapsed_class).addClass(expanded_class);
		
		city_element.style.display='block';
		facet_city_element.className=filter_class+handle_class+expanded_class;
		
		advanced_element.style.display='block';
		facet_advanced_element.className=filter_class+handle_class+expanded_class;
		country_element.style.display='block';
		facet_country_element.className=filter_class+handle_class+expanded_class;
	}else{

		total_giving_element.style.display='none';
		facet_total_giving_element.className=filter_class+handle_class+collapsed_class;
		net_assets_element.style.display='none';
		facet_net_assets_element.className=filter_class+handle_class+collapsed_class;
		assets_element.style.display='none';
		facet_assets_element.className=filter_class+handle_class+collapsed_class;
		income_element.style.display='none';
		facet_income_element.className=filter_class+handle_class+collapsed_class;
		expenditures_element.style.display='none';
		facet_expenditures_element.className=filter_class+handle_class+collapsed_class;

		$('#foundation').removeClass('active');
		if(hash_stm != -1){
			$('#resume').removeClass('active');
			$('#srchmethod').val('Short-term Missions / Volunteer Internship ');
				
			$('#simplyhired').hide();
			if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
				$('#filter_data_heading').text(filter_data);
			}
			//$('#contenttype_heading').text(contenttype_data);
//			$('#contenttype_heading').text('Short-term Missions / Volunteer Internship Volunteer Opportunities');
			benefits_element.style.display='block';
			facet_benefits_offered_element.className=filter_class+handle_class+expanded_class;
			length_of_trip_element.style.display='block';
			facet_length_of_trip_element.className=filter_class+handle_class+expanded_class;
			country_element.style.display='block';
			facet_country_element.className=filter_class+handle_class+expanded_class;
			region_element.style.display='block';
			facet_region_element.className=filter_class+handle_class+expanded_class;
		}else if(hash_virt != -1){
			$('#resume').removeClass('active');				
			$('#simplyhired').hide();
			$('#srchmethod').val('Virtual Volunteering (from home)');
			if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
				$('#filter_data_heading').text(filter_data);
			}
			//$('#contenttype_heading').text(contenttype_data);
//			$('#contenttype_heading').text('Virtual Volunteer Opportunities');
			facet_country_element.style.display='none';
			facet_region_element.style.display='none';
			facet_province_element.style.display='none';
			facet_city_element.style.display='none';
			facet_great_for_element.style.display='none';  
			facet_group_size_element.style.display='none'; 
			facet_benefits_offered_element.style.display='none'; 
			facet_length_of_trip_element.style.display='none'; 
			facet_frequency_element.style.display='none'; 
		}else if(hash_cv != -1){
//console.log('hash_cv')		;	
			$('#local').removeClass('active');				
			$('#virtual').removeClass('active');				
			$('#stm').removeClass('active');				
			$('#organization').removeClass('active');				
			$('#job').removeClass('active');				
			$('#resume').removeClass('active');				
			$('#Foundation').removeClass('active');				
			$('#CityVision').addClass('active');				
			$('#simplyhired').hide();
			$('#srchmethod').val('City Vision');
			if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
				$('#filter_data_heading').text(filter_data);
			}
//            $('#facet_benefits_offered').remove().insertBefore('#facet_org_member_type');
//            $('#facet_trip_length').remove().insertBefore('#facet_org_member_type');
            $('#facet_frequency').remove().insertBefore('#facet_org_member_type');
            $('#facet_great_for').remove().insertBefore('#facet_org_member_type');
            $('#facet_region').remove().insertBefore('#facet_org_member_type');
            $('#facet_city_tax').remove().insertBefore('#facet_country_tax');
            $('#facet_province_tax').remove().insertBefore('#facet_country_tax');
		
			$('#city_tax').show();
			$('#facet_city_tax').addClass('filter handle expanded');
			$('#province_tax').show();
			$('#facet_province_tax').addClass('filter handle expanded');
			$('#country_tax').show();
			$('#facet_country_tax').addClass('filter handle expanded');
			$('#benefits_offered').show();
			$('#facet_benefits_offered').addClass('filter handle expanded');
		}else{
			$('#resume').removeClass('active');
			$('#srchmethod').val('opportunity');
			if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
				$('#filter_data_heading').text(filter_data);
			}
			//$('#contenttype_heading').text(contenttype_data);
			$('#contenttype_heading').text('Volunteer Opportunities');
		}
	}
	$('#results_type').text($('#srchmethod').val());
//console.log('872 net_assets is: ' + $('#net_assets').html());
});

$('a#stm').click(function(){
	var service_areas_element = document.getElementById('service_areas'); 
	var benefits_element = document.getElementById('benefits_offered'); 
	var length_of_trip_element = document.getElementById('trip_length'); 
	var advanced_element = document.getElementById('advanced_facets'); 
	var country_element = document.getElementById('country_tax'); 
	var region_element = document.getElementById('region'); 
	var denom_affil_element = document.getElementById('denom_affil'); 
	var affil_element = document.getElementById('org_affil'); 
	var member_type_element = document.getElementById('org_member_type'); 
	var primary_opp_type_element = document.getElementById('primary_opp_type'); 
	var looking_for_element = document.getElementById('looking_for'); 
		
	var facet_looking_for_element = document.getElementById('facet_looking_for'); 
	var facet_service_areas_element = document.getElementById('facet_service_areas'); 
	var facet_benefits_offered_element = document.getElementById('facet_benefits_offered'); 
	var facet_length_of_trip_element = document.getElementById('facet_trip_length'); 
	var facet_advanced_element = document.getElementById('facet_adv'); 
	var facet_country_element = document.getElementById('facet_country_tax'); 
	var facet_region_element = document.getElementById('facet_region'); 
	var facet_denom_affil_element = document.getElementById('facet_denom_affil'); 
	var facet_affil_element = document.getElementById('facet_org_affil'); 
	var facet_member_type_element = document.getElementById('org_member_type'); 
	var facet_primary_opp_type_element = document.getElementById('facet_primary_opp_type'); 
				$('#srchmethod').val('Short-term Missions / Volunteer Internship');
				if(filter_data.indexOf('-org_member_type') == -1 && filter_data.indexOf('hidden_source') == -1){
					$('#filter_data_heading').text(filter_data);
				}
			//$('#contenttype_heading').text(contenttype_data);
//				$('#contenttype_heading').text('Short-term Missions / Volunteer Internship');
				benefits_element.style.display='block';
				facet_benefits_offered_element.className=filter_class+handle_class+expanded_class;
				length_of_trip_element.style.display='block';
				facet_length_of_trip_element.className=filter_class+handle_class+expanded_class;
				country_element.style.display='block';
				facet_country_element.className=filter_class+handle_class+expanded_class;
				region_element.style.display='block';
				facet_region_element.className=filter_class+handle_class+expanded_class;
				
			$('#simplyhired').hide();
});  
$('a#job').click(function(){
	$('#simplyhired').show();
});  

</script>

<%@include file="/template_include/footer_google_analytics_search_javascript.inc"%>


<style>
.head{
	color: #003366;
    font-size: 1em;
    font-weight: normal;
    padding: 5px 0;
	margin-bottom: 10px;
}
#contentwrapper{
	background:url("http://www.christianvolunteering.org/imgs/bodycontent_patt_wide-950.gif") repeat-y scroll right top #FFFFFF;
}
.collapsed{
	visibility:hidden;
}
.expanded{
	visibility:visible;
}
.filter li {
    font-size: 1em;
    line-height: 1.3em;
    list-style: none outside none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    position: relative;
    white-space: nowrap;
}
</style>

<div id="result">

<!-- start sidebar information -->
<!-- start sidebar.inc -->
<div id="menu_sidebar" onclick="toggle_menu_sidebar()">
<img src="http://www.christianvolunteering.org/imgs/mobile-menu-button.png" alt="menu" border="0" width="30px;" />
</div>


<div id="sidebar" class="search_results">
	<div id="sidebar_content">
		<br>
	<h3 id="filter_label">Filters Applied</h3><ul id="removeall"></ul>
	<ul id="selection"></ul>

	<div class="cleardiv"></div>
<ul class="filters" id="filters">		
	<li id="facet_dist">
		<div id="label" style="float:left">Distance:</div>
		<div id="dist" class="tagcloud toggle" style="float:left; display:block;">
				<SELECT id="d" name="d" class="smalldropdown" > 
					<option value="1.609344">exact location</option>
					<option value="8.04672">within 5 miles</option>
					<option value="16.09344">within 10 miles</option>
					<option value="24.14016">within 15 miles</option>
					<option value="40.2336" selected="selected">within 25 miles</option>
					<option value="80.4672">within 50 miles</option>
					<option value="160.9344">within 100 miles</option>
					<option value="Virtual">virtual</option>
				</SELECT>
		</div><!-- end dist -->
		<br clear="all"/>
	</li>
		
		<hr class="sidebar_hr"> 
	<li id="facet_sort">
		<div id="sortby" style="float:left">Sort:</div>
		<div id="sort" class="tagcloud toggle" style="float:left; display:block;">
        
        
        
        
<% if(aszContentType.contains("resume")){ %>
	<SELECT id="sortkey" name="sortkey" class="smalldropdown"> 
		<option value="last_updated_dt desc, tm_member_i desc, popularity desc" >Date Last Updated</option>
	<% //if( searchinfo.getPostalCode().length()>0 || aszLatitude.length() > 0 || aszLatitude.length() > 0){ %>
		<option value="geodist(latlng ,<%=aszLatitude%>,<%=aszLongitude%>) asc, tm_member_i desc, popularity desc" id="distance_sort" style="display:none;">Distance</option>
	<% //} %>
        <option value="title_literal asc, tm_member_i desc, popularity desc" >Name</option>
        <option value="city_tax asc, tm_member_i desc, popularity desc" >City</option>
        <option value="province_tax asc, tm_member_i desc, popularity desc" >State (US & Canada)</option>
        <option value="country_tax asc, tm_member_i desc, popularity desc" >Country</option>
    </SELECT>
<% }else{ %>
	<SELECT id="sortkey" name="sortkey" class="smalldropdown"> 
        <option value="tm_member_i desc, popularity desc">Popularity</option>
	<% if(aszContentType.contains("organization")){ %>
        <option value="revenue desc">Revenue</option>
	<% } %>
		<option value="geodist(latlng ,<%=aszLatitude%>,<%=aszLongitude%>) asc, tm_member_i desc, popularity desc" id="distance_sort" style="display:none;">Distance</option>
        <option value="organization_name asc, tm_member_i desc, popularity desc" >Organization Name</option>
        <option value="opportunity_title asc, tm_member_i desc, popularity desc" >Opportunity Title</option>
        <option value="city_tax asc, tm_member_i desc, popularity desc" >City</option>
        <option value="province_tax asc, tm_member_i desc, popularity desc" >State (US & Canada)</option>
        <option value="country_tax asc, tm_member_i desc, popularity desc" >Country</option>
		<option value="last_updated_dt desc, tm_member_i desc, popularity desc" >Date Last Updated</option>
        <option value="num_volunteers_org desc, tm_member_i desc, popularity desc" ># Volunteers / Organization</option>
        <option value="num_volunteers desc, tm_member_i desc, popularity desc" ># Volunteers / Opportunity</option>
    </SELECT>
<% } %>

		</div>
<br clear="all"/><br clear="all"/>
</li>
		
<li class="filter expanded" id="facet_num_opps"  style="display:none;">
	<% 
		String aszHashSearchTmp = "fq="+aszFQSearch.replaceAll("&","&fq=") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
		if(aszHashSearchTmp.length() > 0) aszHashSearchTmp += "&";
		if(! (aszHashSearchTmp.contains("content_type") ) )	aszHashSearchTmp += "fq="+aszContentType;
		if(! (aszHashSearchTmp.endsWith("&") ) ) aszHashSearchTmp += "&";
		aszHashSearchTmp += "fq=-org_member_type:Foundation&fq=num_opps:[1 TO *]";
		aszHashSearchTmp = aszHashSearchTmp.replaceAll("fq=content_type:opportunity", "fq=content_type:organization");
	%>
<script language="javascript"    >
//	console.log(' aszHashSearchTmp is: <%=aszHashSearchTmp%>');
</script>
    <div class="head"> 
		<a id="num_opps" href="#<%=aszHashSearchTmp%>">Show Only Orgs with Opportunities</a>
	</div>
</li>
		
<li class="filter expanded" id="facet_full_user"  style="display:none;">
	<% 
		aszHashSearchTmp = "fq="+aszFQSearch.replaceAll("&","&fq=") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
		if(aszHashSearchTmp.length() > 0) aszHashSearchTmp += "&";
		if(! (aszHashSearchTmp.contains("content_type") ) )	aszHashSearchTmp += "fq="+aszContentType;
		if(! (aszHashSearchTmp.endsWith("&") ) ) aszHashSearchTmp += "&";
		aszHashSearchTmp += "fq=full_user:true";
		aszHashSearchTmp = aszHashSearchTmp.replaceAll("fq=content_type:opportunity", "fq=content_type:resume");
	%>
<script language="javascript"    >
//	console.log(' aszHashSearchTmp is: <%=aszHashSearchTmp%>');
</script>
    <div class="head"> 
		<a id="full_user" href="#<%=aszHashSearchTmp%>">Only Users with Resumes</a>
	</div>
</li>
		
<li class="filter expanded" id="facet_all_user"  style="display:none;">
	<% 
		aszHashSearchTmp = "fq="+aszFQSearch.replaceAll("&","&fq=") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
		aszHashSearchTmp = aszHashSearchTmp.replaceAll("fq=full_user:true", "");
		if(aszHashSearchTmp.length() > 0) aszHashSearchTmp += "&";
		if(! (aszHashSearchTmp.contains("content_type") ) )	aszHashSearchTmp += "fq="+aszContentType;
		if( (aszHashSearchTmp.endsWith("&") ) ) aszHashSearchTmp = aszHashSearchTmp.substring(0,aszHashSearchTmp.length()-2);
		aszHashSearchTmp = aszHashSearchTmp.replaceAll("fq=content_type:opportunity", "fq=content_type:resume");
	%>
<script language="javascript"    >
//	console.log(' aszHashSearchTmp is: <%=aszHashSearchTmp%>');
</script>
    <div class="head"> 
		<a id="all_user" href="#<%=aszHashSearchTmp%>">Search All Users</a>
	</div>
</li>
    

	<li class="filter handle expanded" id="facet_primary_opp_type" style="display:none;">
		<a class="handle" onClick="toggle_display('primary_opp_type')"><span class="i bullet">&nbsp;</span> <span class="label">Oppportunity Types:</span></a>
		<ul id="primary_opp_type" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("primary_opp_type")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_primary_opp_type" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_primary_opp_type'); return false;" style="display:inline" href="#primary_opp_type">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle collapsed" id="facet_foundation_type" style="display:none;">
		<a class="handle" onClick="toggle_display('foundation_type')"><span class="i bullet">&nbsp;</span> <span class="label">Type of Foundation:</span></a>
		<ul id="foundation_type" class="tagcloud toggle" style="display:block;">
		</ul>
	</li>

	<li class="filter handle expanded" id="facet_service_areas">
		<a class="handle" onClick="toggle_display('service_areas')"><span class="i bullet">&nbsp;</span> <span class="label" id="service_areas_label">Service Area:</span></a>
		<ul id="service_areas" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("service_areas") && facets[1].length()>0){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
//out.println("<!-- aszHashSearch="+aszHashSearch+" -->");						
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_service_areas" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_service_areas'); return false;" style="display:inline" href="#service_areas">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_looking_for" style="display:none;">
		<a class="handle" onClick="toggle_display('looking_for')"><span class="i bullet">&nbsp;</span> <span class="label">Looking For:</span></a>
		<ul id="looking_for" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("looking_for")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_looking_for" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_looking_for'); return false;" style="display:inline" href="#looking_for">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle expanded" id="facet_education_level" style="display:none;">
		<a class="handle" onClick="toggle_display('education_level')"><span class="i bullet">&nbsp;</span> <span class="label">Level of Education:</span></a>
		<ul id="education_level" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("education_level")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_looking_for" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_education_level'); return false;" style="display:inline" href="#looking_for">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle collapsed" id="facet_geo_tax" style="display:none;">
		<a class="handle" onClick="toggle_display('geo_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Geographic Focus:</span></a>
		<ul id="geo_tax" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">

				<% 
					if(facets[0].equals("geo_tax")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_geo_tax" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_geo_tax'); return false;" style="display:inline" href="#geo_tax">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

		
		
	<li class="filter handle collapsed" id="facet_foundation_location" style="display:none;">
		<a class="handle" onClick="toggle_display('foundation_location')"><span class="i bullet">&nbsp;</span> <span class="label">Foundation Location:</span></a>
		<ul id="foundation_location" class="tagcloud toggle" style="display:block;">
		</ul>
	</li>

		
	<li class="filter handle collapsed" id="facet_total_giving" style="display:none;">
		<a class="handle" onClick="toggle_display('total_giving')"><span class="i bullet">&nbsp;</span> <span class="label">Total Giving:</span></a>
		<ul id="total_giving" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">

				<% 
					if(facets[0].equals("total_giving")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_total_giving" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_total_giving'); return false;" style="display:inline" href="#total_giving">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle collapsed" id="facet_assets" style="display:none;">
		<a class="handle" onClick="toggle_display('assets')"><span class="i bullet">&nbsp;</span> <span class="label">Assets:</span></a>
		<ul id="assets" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("assets")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_assets" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_assets); return false;" style="display:inline" href="#assets">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle collapsed" id="facet_net_assets" style="display:none;">
		<a class="handle" onClick="toggle_display('net_assets')"><span class="i bullet">&nbsp;</span> <span class="label">Net Assets:</span></a>
		<ul id="net_assets" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("net_assets")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_net_assets" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_net_assets); return false;" style="display:inline" href="#net_assets">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle collapsed" id="facet_income" style="display:none;">
		<a class="handle" onClick="toggle_display('income')"><span class="i bullet">&nbsp;</span> <span class="label">Income:</span></a>
		<ul id="income" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("income")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_income" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_income'); return false;" style="display:inline" href="#income">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>

	<li class="filter handle collapsed" id="facet_expenditures" style="display:none;">
		<a class="handle" onClick="toggle_display('expenditures')"><span class="i bullet">&nbsp;</span> <span class="label">Expenditures:</span></a>
		<ul id="expenditures" class="tagcloud toggle" style="display:block;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("expenditures")){ 
						if(! (facets[1].equalsIgnoreCase("Unknown") || facets[1].equalsIgnoreCase("$0") ) ){
							if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
							else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
							aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
							if(aszHashSearch.length() > 0) aszHashSearch += "&";
							aszHashSearch += "fq="+aszContentType;
							if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
							aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						}
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_expenditures" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_expenditures'); return false;" style="display:inline" href="#expenditures">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>


	<li class="filter handle collapse" id="facet_great_for">
		<a class="handle" onClick="toggle_display('great_for')"><span class="i bullet">&nbsp;</span> <span class="label">Great for:</span></a>
		<ul id="great_for" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("great_for")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_great_for" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_great_for'); return false;" style="display:inline" href="#great_for">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
	<li class="filter handle collapse" id="facet_frequency">
		<a class="handle" onClick="toggle_display('frequency')"><span class="i bullet">&nbsp;</span> <span class="label">Frequency:</span></a>
		<ul id="frequency" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("frequency")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_frequency" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_frequency'); return false;" style="display:inline" href="#frequency">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_benefits_offered">
		<a class="handle" onClick="toggle_display('benefits_offered')"><span class="i bullet">&nbsp;</span> <span class="label">Benefits:</span></a>
		<ul id="benefits_offered" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("benefits_offered")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_benefits_offered" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_benefits_offered'); return false;" style="display:inline" href="#benefits_offered">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_trip_length">
		<a class="handle" onClick="toggle_display('trip_length')"><span class="i bullet">&nbsp;</span> <span class="label">Length of Trip:</span></a>
		<ul id="trip_length" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("trip_length")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_trip_length" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_trip_length'); return false;" style="display:inline" href="#trip_length">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>
		
	<li class="filter handle collapse" id="facet_region">
		<a class="handle" onClick="toggle_display('region')"><span class="i bullet">&nbsp;</span> <span class="label">Region:</span></a>
		<ul id="region" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("region")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_region" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_region'); return false;" style="display:inline" href="#region">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
	<li class="filter handle collapse" id="facet_country_tax">
		<a class="handle" onClick="toggle_display('country_tax')"><span class="i bullet">&nbsp;</span> <span id="facet_country_tax_label" class="label">Country:</span></a>
		<ul id="country_tax" class="tagcloud toggle" style="display:none;">
			<% iCountDisplay=0; %>
			<logic:notEmpty name="facetlist" >
				<logic:iterate id="facets" name="facetlist" type="String[]">
				<% 
					if(facets[0].equals("country_tax")){ 
						if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
						iCountDisplay++;
					} 
				%>
				</logic:iterate>
			</logic:notEmpty>
			<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
					<li id="more_link_facet_country_tax" class="filter expanded" style="display:block;" >
						<a onClick="toggle_facet('facet_country_tax'); return false;" style="display:inline" href="#country_tax">See more &gt;&gt;</a>
					</li>							
			<% } %>
		</ul>
	</li>		
		
<% // expand if STM, though %>
	<li class="filter handle collapse" id="facet_adv">
		<a class="handle" onClick="toggle_display('advanced_facets')"><span class="i bullet">&nbsp;</span> <span class="label">Advanced</span></a>
		<ul id="advanced_facets" class="filters" style="display:none;">
			<li class="filter"><hr class="sidebar_hr"> </li>
				
<!-- expand if org is selected, though -->
			<li class="filter handle collapse" id="facet_org_member_type">
				<a class="handle" onClick="toggle_display('org_member_type')"><span class="i bullet">&nbsp;</span> <span class="label">Organization Type:</span></a>
				<ul id="org_member_type" class="tagcloud toggle" style="display:none;">
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("org_member_type")){ 
								aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
					} 
				%>
						</logic:iterate>
					</logic:notEmpty>

				</ul>
			</li>

			<li class="filter handle collapse" id="facet_denom_affil">
				<a class="handle" onClick="toggle_display('denom_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Denomination:</span></a>
				<ul id="denom_affil" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("denom_affil") && facets[1].length()>0){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_denom_affil" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_denom_affil'); return false;" style="display:inline" href="#denom_affil">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>
				
<% // expand if org is selected, though %>
			<li class="filter handle collapse" id="facet_org_affil">
				<a class="handle" onClick="toggle_display('org_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Organizational Affiliation:</span></a>
				<ul id="org_affil" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("org_affil") && facets[1].length()>0){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_org_affil" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_org_affil'); return false;" style="display:inline" href="#org_affil">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li> 
		
			<li class="filter handle collapse" id="facet_province_tax">
				<a class="handle" onClick="toggle_display('province_tax')"><span class="i bullet">&nbsp;</span> <span id="facet_province_tax_label" class="label">State/Province:</span></a>
				<ul id="province_tax" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("province_tax")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_province_tax" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_province_tax'); return false;" style="display:inline" href="#province_tax">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_city_tax">
				<a class="handle" onClick="toggle_display('city_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Metro Area:</span></a>
				<ul id="city_tax" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% 
							if(facets[0].equals("city_tax")){ 
								if(iCountDisplay < iNumDisplay)	aszDisplayClass = "class=\"filter expanded\" style=\"display:block;\"";
								else	aszDisplayClass = "class=\"filter collapsed\" style=\"display:none;\"";
						aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
						if(aszHashSearch.length() > 0) aszHashSearch += "&";
						aszHashSearch += "fq="+aszContentType;
						if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
						aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
				%>
						<li <%=aszDisplayClass%>>
							<a href="/oppsrch.do?method=processSearch&<%=aszFQParamsURL%>&<%=aszHashSearch%>#<%=aszHashSearch%>"><%=facets[1]%> (<%=facets[2]%>)</a>
						</li>	
				<% 		
								iCountDisplay++;
							} 
						%>
						</logic:iterate>
					</logic:notEmpty>
					<%	if(iCountDisplay > iNumDisplay){ // ouptut more link		%>
							<li id="more_link_facet_city_tax" class="filter expanded" style="display:block;" >
								<a onClick="toggle_facet('facet_city_tax'); return false;" style="display:inline" href="#city_tax">See more &gt;&gt;</a>
							</li>							
					<% } %>
				</ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_group_size" style="display:none;"><!-- will somehow have to handle range -->
				<a class="handle" onClick="toggle_display('group_size')"><span class="i bullet">&nbsp;</span> <span class="label">Group Size:</span></a>
				<ul id="group_size" class="tagcloud toggle" style="display:none;"></ul>
			</li>		
		
			<li class="filter handle collapse" id="facet_afg">
				<a class="handle" onClick="toggle_display('source')"><span class="i bullet">&nbsp;</span> <span class="label">Search from Third Party Sites:</span></a>
				<ul id="source" class="tagcloud toggle" style="display:none;">
					<% iCountDisplay=0; %>
					<logic:notEmpty name="facetlist" >
						<logic:iterate id="facets" name="facetlist" type="String[]">
						<% if(facets[0].equals("source")){ %>
							<%
							String aszSourcePhrase = facets[1];
							if(! aszSourcePhrase.contains("bil_import")){
								if(aszSourcePhrase.equalsIgnoreCase("AllForGood")){
									aszSourcePhrase = "<img src=\"http://www.christianvolunteering.org/imgs/afg-blue_60.png\">";
									String aszFQParamsLink = aszFQParams;
									if(aszFQParams.length()>0){
									if(! (aszFQParams.startsWith("&") )){
										aszFQParamsLink = "&" + aszFQParams;
									}
								}
								aszHashSearch = "fq="+aszFQSearch.replaceAll("&","&fq=").replaceAll("\"","&quot;") ;// aszFQParams; - error - wasn't including geofilt w the Params variable
								if(aszHashSearch.length() > 0) aszHashSearch += "&";
									aszHashSearch += "fq="+aszContentType;
									if(! (aszHashSearch.endsWith("&") ) ) aszHashSearch += "&";
									aszHashSearch += "fq="+facets[0]+":&quot;"+facets[1]+"&quot";
							%>
									<a href="<%=aszPortal%>/oppsrch.do?method=processSearch<%=aszFQParamsLink%>&fq=<%=aszContentType%>&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;#<%=aszHashSearch%>"><%=aszSourcePhrase%> (<%=facets[2]%>)</a>
									<br> 
							<% 
								}
							}
							%>
						<% } %>
						</logic:iterate>
					</logic:notEmpty>
				</ul>
			</li>		
		</ul>
	</li>
</ul>


<!-- 
      <h3>By Date</h3>
      <div id="calendar"></div>
      <br /><br />
      <h3>By Country</h3>
      <div id="countries"></div>
      <div id="preview"></div>
<br /><br /><br /><br /><br />

 -->

      <div class="clear"></div>

	</div>
</div>
