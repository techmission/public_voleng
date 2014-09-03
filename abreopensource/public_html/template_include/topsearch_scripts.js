function topopp_search() {
	document.getElementById('searchumcontent').style.display='none';
	document.getElementById('cvoppsearch').style.display='inline';
	document.getElementById('cvorgsearch').style.display='none';
	
	document.getElementById('cont').className = "";
	document.getElementById('org').className = "";
	document.getElementById('opp').className='active';
}

function umcont_search() {
	document.getElementById('searchumcontent').style.display='inline';
	document.getElementById('cvoppsearch').style.display='none';
	document.getElementById('cvorgsearch').style.display='none';
	
	document.getElementById('cont').className = "active";
	document.getElementById('opp').className = "";
	document.getElementById('org').className = "";
}

function toporg_search() {
	document.getElementById('searchumcontent').style.display='none';
	document.getElementById('cvoppsearch').style.display='none';
	document.getElementById('cvorgsearch').style.display='inline';
	
	document.getElementById('cont').className = "";
	document.getElementById('opp').className = "";
	document.getElementById('org').className='active';
}




function clearText(thefield){
	if (thefield.defaultValue==thefield.value)
	thefield.value = "";
}

