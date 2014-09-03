<% if(aszPortal.length()<1) { %>
<script language="javascript">
function toggle_menu_sidebar(){
	var sidebar = document.getElementById("sidebar"); 
	var menu_sidebar = document.getElementById("menu_sidebar"); 
	if($('#sidebar').is(":hidden") ){
		$('#sidebar').show();
	}else{
		$('#sidebar').hide();
	}
}

</script>

<div id="menu_sidebar" onclick="toggle_menu_sidebar()">
<img src="http://www.christianvolunteering.org/imgs/mobile-menu-button.png" alt="menu" border="0" width="30px;" />
</div>

<div id="sidebar" class="search_results">
	<div id="sidebar_content">

	<h3 style="padding-top:5px;">Search By:</h3>


	<div class="cleardiv"></div>
		
	<li id="facet_num_opps" style="display:none;">
		<div id="num_opps" class="tagcloud toggle" style="display:block;"><span class="label">Only Orgs with Opportunities</span></div>
	</li>

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
		<br clear="all"/><br clear="all"/>
	</li>
		
	<!--li class="filter handle collapse" id="facet_position_type">
		<a class="handle" onClick="toggle_display('position_type')"><span class="i bullet">&nbsp;</span> <span class="label">Position Type:</span></a>
		<div id="position_type" class="tagcloud toggle" style="display:none;"></div>
	</li-->
		
	<li class="filter handle expanded" id="facet_service_areas">
		<a class="handle" onClick="toggle_display('service_areas')"><span class="i bullet">&nbsp;</span> <span class="label">Service Area:</span></a>
		<div id="service_areas" class="tagcloud toggle" style="display:block;"></div>
	</li>
		
	<li class="filter handle collapse" id="facet_great_for">
		<a class="handle" onClick="toggle_display('great_for')"><span class="i bullet">&nbsp;</span> <span class="label">Great for:</span></a>
		<div id="great_for" class="tagcloud toggle" style="display:none;"></div>
	</li>		
		
	<li class="filter handle collapse" id="facet_frequency">
		<a class="handle" onClick="toggle_display('frequency')"><span class="i bullet">&nbsp;</span> <span class="label">Frequency:</span></a>
		<div id="frequency" class="tagcloud toggle" style="display:none;"></div>
	</li>		
		
<!-- expand if STM, though -->
	<li class="filter handle collapse" id="facet_benefits_offered">
		<a class="handle" onClick="toggle_display('benefits_offered')"><span class="i bullet">&nbsp;</span> <span class="label">Benefits:</span></a>
		<div id="benefits_offered" class="tagcloud toggle" style="display:none;"></div>
	</li>
		
<!-- expand if STM, though -->
	<li class="filter handle collapse" id="facet_trip_length">
		<a class="handle" onClick="toggle_display('trip_length')"><span class="i bullet">&nbsp;</span> <span class="label">Length of Trip:</span></a>
		<div id="trip_length" class="tagcloud toggle" style="display:none;"></div>
	</li>
		
	<li class="filter handle collapse" id="facet_region">
		<a class="handle" onClick="toggle_display('region')"><span class="i bullet">&nbsp;</span> <span class="label">Region:</span></a>
		<div id="region" class="tagcloud toggle" style="display:none;"></div>
	</li>		
		
			<li class="filter handle collapse" id="facet_country_tax">
				<a class="handle" onClick="toggle_display('country_tax')"><span class="i bullet">&nbsp;</span> <span class="label">Country:</span></a>
				<div id="country_tax" class="tagcloud toggle" style="display:none;"></div>
			</li>		
		
<!-- expand if STM, though -->
	<li class="filter handle collapse" id="facet_adv">
		<a class="handle" onClick="toggle_display('advanced_facets')"><span class="i bullet">&nbsp;</span> <span class="label">Advanced</span></a>
		<ul id="advanced_facets" class="filters" style="display:none;">
			<li class="filter"><hr class="sidebar_hr"> </li>
				
			<li class="filter handle collapse" id="facet_org_member_type">
				<a class="handle" onClick="toggle_display('org_member_type')"><span class="i bullet">&nbsp;</span> <span class="label">Organization Type:</span></a>
				<div id="org_member_type" class="tagcloud toggle" style="display:none;"></div>
			</li>

<!-- expand if org is selected, though -->
			<li class="filter handle collapse" id="facet_denom_affil">
				<a class="handle" onClick="toggle_display('denom_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Denomination:</span></a>
				<div id="denom_affil" class="tagcloud toggle" style="display:none;"></div>
			</li>
				
<!-- expand if org is selected, though -->
			<li class="filter handle collapse" id="facet_org_affil">
				<a class="handle" onClick="toggle_display('org_affil')"><span class="i bullet">&nbsp;</span> <span class="label">Organizational Affiliation:</span></a>
				<div id="org_affil" class="tagcloud toggle" style="display:none;"></div>
			</li> 
		
			<li class="filter handle collapse" id="facet_province_tax">
				<a class="handle" onClick="toggle_display('province_tax')"><span class="i bullet">&nbsp;</span> <span class="label">State/Province:</span></a>
				<div id="province_tax" class="tagcloud toggle" style="display:none;"></div>
			</li>		
<!-- 			
			<li class="filter handle collapse" id="facet_group_size"><! -- will somehow have to handle range -- >
				<a class="handle" onClick="toggle_display('group_size')"><span class="i bullet">&nbsp;</span> <span class="label">Group Size:</span></a>
				<div id="group_size" class="tagcloud toggle" style="display:none;">
					<a href="/oppsrch.do?method=processSolrSearchOpp#(group_min:[2 TO 5] OR group_max:[2 TO 5])" />2-5
					<a href="/oppsrch.do?method=processSolrSearchOpp#(group_min:[5 TO 10] OR group_max:[5 TO 10])" />5-10
					<a href="/oppsrch.do?method=processSolrSearchOpp#(group_min:[10 TO 20] OR group_max:[10 TO 20])" />10-20
					<a href="/oppsrch.do?method=processSolrSearchOpp#(group_min:[20 TO *] OR group_max:[20 TO *])" />20+
				</div>
			</li>		
-->
			<li class="filter handle collapse" id="facet_afg">
				<a class="handle" onClick="toggle_display('source')"><span class="i bullet">&nbsp;</span> <span class="label">Search from Third Party Sites:</span></a>
				<div id="source" class="tagcloud toggle" style="display:none;"></div>
			</li>		
		</ul>
	</li>
</ul>


<!--
      <h3>By Country</h3>
      <div id="countries"></div>
      <div id="preview"></div>

      <h3>By Date</h3>
      <div id="calendar"></div>
-->
      <div class="clear"></div>

	</div>
</div>
<!-- end sidebar.inc -->

<script language="javascript">
function toggle_display(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapse_class=" collapse";
	var expanded_class=" expanded";
	var e = document.getElementById(facet_field); 
	if(e.id=="more_link"){
		e.style.display="none";
	}else if(e.style.display=="none"){
		e.style.display="block";
		e.parentNode.className=default_class+handle_class+expanded_class;
	}else{
		e.style.display="none";
		e.parentNode.className=default_class+handle_class+collapse_class;
	}
/*
	if(e.className.indexOf(expanded_class)){
		e.className=default_class+toggle_class+' '+collapsed_class;
	}else{
		e.className=default_class+toggle_class+' '+expanded_class;
	}
*/
}
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
	var root_facet_element = document.getElementById(id); 
	if(root_facet_element != null){
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class;
		
		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
//console.log('list_element.id is ' + list_element.id);				
				if(list_element.id=="more_link"){
					list_element.style.display="none";
				}else if(list_element.style == undefined){
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
	}
	var more_link = 'more_link_'+facet_field;
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display="none;"
		more_link_element.className = collapsed_class;
	}
}
</script>
<% } %>