function toggle_display(facet_field){
/*
	var default_class="filter";
	var handle_class=" handle";
	var landingpage_class=" landingpage";
	var collapse_class=" collapse";
	var expanded_class=" expanded";
	var e = document.getElementById(facet_field); 
	if(e.style.display=="none"){
		e.style.display="block";
		e.parentNode.className=default_class+handle_class+expanded_class+landingpage_class;
	}else{
		e.style.display="none";
		e.parentNode.className=default_class+handle_class+collapse_class+landingpage_class;
	}
*/	
}
function toggle_facet(facet_field){
	$('#midbox1_content').height('auto');
	$('#midbox2_content').height('auto');
	$('#midbox3_content').height('auto');
	var default_class="filter";
	var handle_class=" handle";
	var landingpage_class=" landingpage";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
	var root_facet_element = document.getElementById(id); 
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class+landingpage_class;
		
		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
				if(list_element.style == undefined){
				}else{
					var init_class = list_element.className;
					if(init_class=='filter collapsed'){
						list_element.style.display='inline-block';
						list_element.className = default_class+expanded_class;
					}
				}
			}
		}
	}
	var more_link = 'more_link_'+facet_field;
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
//console.log('1 more_link_element '+more_link_element);
	}else if(more_link_element==null){
//console.log('2 more_link_element '+more_link_element);
	}else{
//console.log('3 more_link_element '+more_link_element);
		more_link_element.style.display='none';
		more_link_element.className = collapsed_class;
	}
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

function adjust_height(){
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
