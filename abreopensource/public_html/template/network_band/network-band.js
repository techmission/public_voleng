// JavaScript Document

function toggleStickyMenu(submenu) {
	//alert(document.getElementById(submenu).style.display);
	
	if (document.getElementById(submenu).style.display == "none") {
		document.getElementById(submenu).style.display="block";
	}
	else {
		document.getElementById(submenu).style.display="none";
	}
}