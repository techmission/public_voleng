// JavaScript Document

/*
	DOMnews 1.0 
	homepage: http://www.onlinetools.org/tools/domnews/
	released 11.07.05
*/

/* Variables, ALREADY included in the page for easy access */
	// initial position 
	//var dn_startpos=0; 	
	// end position (long list = higher number) 
	//var dn_endpos=-410; 			
	// Speed of scroller higher number = slower scroller 
	//var dn_speed=70;				
	// ID of the news box
	//var dn_newsID='news';			
	// class to add when JS is available
	//var dn_classAdd='hasJS';		
	// Message to stop scroller
	////var dn_stopMessage='';	
	// ID of the generated paragraph
	//var dn_paraID='DOMnewsstopper';



	/* Initialise scroller when window loads */
	window.onload=function()
	{
		// check for DOM
		if(!document.getElementById || !document.createTextNode){return;}
		initDOMnews();
		// add more functions as needed
	}
	/* stop scroller when window is closed */
	window.onunload=function()
	{
		clearInterval(dn_interval);
	}

/*
	This is the functional bit, do not press any buttons or flick any switches
	without knowing what you are doing!
*/

	var dn_scrollpos=dn_startpos;
	/* Initialise scroller */
	function initDOMnews()
	{
		var n=document.getElementById(dn_newsID);
		if(!n){return;}
		n.className=dn_classAdd;
		dn_interval=setInterval('scrollDOMnews()',dn_speed);
		var newa=document.createElement('a');
		var newp=document.createElement('p');
		newp.setAttribute('id',dn_paraID);
		newa.href='#';
		newa.appendChild(document.createTextNode(dn_stopMessage));
		newa.onclick=stopDOMnews;
		newp.appendChild(newa);
		n.parentNode.insertBefore(newp,n.nextSibling);
		n.onmouseover=function()
		{		
			clearInterval(dn_interval);
		}
		n.onmouseout=function()
		{
			dn_interval=setInterval('scrollDOMnews()',dn_speed);
		}
	}

	function stopDOMnews()
	{
		clearInterval(dn_interval);
		var n=document.getElementById('news');
		n.className='';
		n.parentNode.removeChild(n.nextSibling);
		return false;
	}
	function scrollDOMnews()
	{
		var n=document.getElementById(dn_newsID).getElementsByTagName('ul')[0];
		n.style.top=dn_scrollpos+'px';	
		if(dn_scrollpos==dn_endpos){dn_scrollpos=dn_startpos;}
		dn_scrollpos--;	
	}
	