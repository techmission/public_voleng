	function clicked_oppsrch(){
		document.getElementById('searchumcontent').style.display='none';
		document.getElementById('cvoppsearch').style.display='inline';
		document.getElementById('cvorgsearch').style.display='none';
	}
	function clicked_orgsrch(){
		document.getElementById('searchumcontent').style.display='none';
		document.getElementById('cvoppsearch').style.display='none';
		document.getElementById('cvorgsearch').style.display='inline';
	}
	function clicked_contentsrch(){
		document.getElementById('searchumcontent').style.display='inline';
		document.getElementById('cvoppsearch').style.display='none';
		document.getElementById('cvorgsearch').style.display='none';
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
	}
	
	function clearText(thefield){
		if (thefield.defaultValue==thefield.value)
		thefield.value = "";
	}

                                
function change_search(srchMethod2){
	//alert(srchMethod2);
	if (srchMethod2=="processOrgSearchAll"){
		clicked_orgsrch();
	}else if (srchMethod2=="allsearch"){
		clicked_contentsrch();
	}else{
		clicked_oppsrch();
	}

}
