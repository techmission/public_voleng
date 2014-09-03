<!-- start embed_options -->
<%
String aszPortalLinkEmbed = aszPortal;
if(aszHostID.equalsIgnoreCase("volengchurch")){
	if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
		aszPortalLinkEmbed = aszPortal.substring(7,aszPortal.length());
	}	
}

if(aszSubdomain.equalsIgnoreCase("ChurchVolunteering.org")){
	aszSubdomain="www."+aszSubdomain;
}

String aszShowListings = "/oppsrch.do?method=showPortalListings";
if(aszSubdomain.contains("ChurchVol")){
	aszShowListings = "/oppsrch.do?method=showChurchListings";
}
String aszSearchPage = "/oppsrch.do?method=showIncludeSearch";
String aszDimensions = "WIDTH=\"650\" HEIGHT=\"400\"";
String aszWidth = "width=\"650\"";
String aszHeight = "height=\"400\"";
String aszSearchDimensions = "WIDTH=\"400\" HEIGHT=\"225\"";
String aszSearchWidth = "width=\"400\"";
String aszSearchHeight = "height=\"225\"";
%>

<script type="text/javascript">

function toggle_embedcontent(){
	if (document.getElementById('embed_div').style.display=='none'){
		document.getElementById('embed_div').style.display='block';
		document.getElementById('embedcollapse').className = "collapsible";
	}else{
		document.getElementById('embed_div').style.display='none';
		document.getElementById('embedcollapse').className = "collapsed";
	}
}

</script>

<style>
#embedcollapse.collapsed .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-collapsed.png") no-repeat scroll 155px 50% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
#embedcollapse.collapsible .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-expanded.png") no-repeat scroll 155px 65% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}
</style>

<div id="embed_div" style="display:none;">
<p style="font-size:11px; font-style:italic;">Paste your <%=aszOrgOrChurch.toLowerCase()%>'s service opportunity listings right into your own webpage or blog by including the code below (like embedding YouTube videos). You may adjust the width, height and scrollbar options to your fit once you have pasted the above code into your site.</p>
<br>
<b>Would you like to: </b>
	<select id="embedselect" name="embedselect" size="1" class="smalldropdown" style="width: 175px;" onchange="update_embed(this)"> 
		<option value="embed_listurl" selected="selected">Link to URL of Your Listings</option> 
<% if( ! (aszCurrentURL.contains("showChurchListings") || aszCurrentURL.contains("showPortalListings"))){ %>
		<option value="embed_current">Embed Current Page</option> 
<% } %>
		<option value="embed_list">Embed All Your Listings</option> 
		<option value="embed_preview">Embed a Preview of Your Listings</option> 
		<option value="embed_searchbox">Embed a Search Form on Your Site</option> 
	</select>
<br><br>

<div id="listurl">
<span class="label_embeds">URL:&nbsp;</span>
<input type="text" name="organizationlistings" size="90" styleClass="textinputwhite" value="http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/volunteerlistings.jsp"/>
</div>

<script type="text/javascript">
// Created by: Matt Murphy | http://www.matts411.com/
// This script downloaded from www.JavaScriptBank.com

function autoSelect(selectTarget) {
  		if(selectTarget.tagName == 'TEXTAREA' || (selectTarget.tagName == "INPUT" && selectTarget.type == "text")) {
  			 selectTarget.select();
  		} else if(window.getSelection) { // FF, Safari, Opera
   			var sel = window.getSelection();
   			var range = document.createRange();
   			range.selectNode(selectTarget.firstChild);
   			sel.removeAllRanges();
   			sel.addRange(range);
  		} else { // IE
   			document.selection.empty();
   			var range = document.body.createTextRange();
   			range.moveToElementText(selectTarget);
   			range.select();
  		}
}
function update_embed(embed_object){

	var page='<%=aszCurrentURL%>';
//	var dimensions='<%=aszDimensions%>';
	var embed_choice = embed_object.options[embed_object.selectedIndex].value;
	var sortbyembed= document.getElementById('sortby_embed');
	var sortby_choice_embed= sortbyembed.options[sortbyembed.selectedIndex].value;
	var preview= document.getElementById('preview').value;
	var bckgrnd= document.getElementById('bckgrnd').value;
	var brdr= document.getElementById('brdr').value;
	var atxt= document.getElementById('atxt').value;
	var width= document.getElementById('width').value;
	var height= document.getElementById('height').value;
	var txt= document.getElementById('txt').value;
	if(bckgrnd.charAt(0)=='#'){
		bckgrnd=bckgrnd.substring(1,bckgrnd.length);
	}
	if(brdr.charAt(0)=='#'){
		brdr=brdr.substring(1,brdr.length);
	}
	if(atxt.charAt(0)=='#'){
		atxt=atxt.substring(1,atxt.length);
	}
	if(txt.charAt(0)=='#'){
		txt=txt.substring(1,txt.length);
	}

	switch(embed_choice){
		case 'embed_preview':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display='block';
			document.getElementById('preview_row').style.display="table-row";
			document.getElementById("sort_by_row").style.display = 'table-row';
			page='<%=aszShowListings%>';
			width='<%=aszWidth%>';
			height='<%=aszHeight%>';
			break;
		case 'embed_current':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display='block';
			document.getElementById('preview_row').style.display="none";
<% if( aszCurrentURL.contains("oppsrch.do?method=proccess") || aszCurrentURL.contains("showChurchListings") ){ %>
			document.getElementById("sort_by_row").style.display = 'table-row';
<% }else{ %>
			document.getElementById("sort_by_row").style.display = 'none';
<% } %>
			page='<%=aszCurrentURL%>';
			width='<%=aszWidth%>';
			height='<%=aszHeight%>';
			preview='';
			break;
		case 'embed_list':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('div_iframe').style.display='block';
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'table-row';
			page='<%=aszShowListings%>';
			width='<%=aszWidth%>';
			height='<%=aszHeight%>';
			preview='';
			break;
		case 'embed_listurl':
			document.getElementById('listurl').style.display="inline";
			document.getElementById('iframe_list').style.display="none";
			document.getElementById('div_iframe').style.display='none';
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'none';
			page='<%=aszShowListings%>';
			width='<%=aszWidth%>';
			height='<%=aszHeight%>';
			preview='';
			break;
		case 'embed_searchbox':
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display='block';
			document.getElementById('div_iframe').style.display='block';
			document.getElementById('preview_row').style.display="none";
			document.getElementById("sort_by_row").style.display = 'none';

			page='<%=aszSearchPage%>';
			width='<%=aszSearchWidth%>';
			height='<%=aszSearchHeight%>';
			preview='';
			break;
		default:
			document.getElementById('listurl').style.display="none";
			document.getElementById('iframe_list').style.display="inline";
			document.getElementById('preview_row').style.display="none";
<% if( aszCurrentURL.contains("oppsrch.do?method=proccess") || aszCurrentURL.contains("showChurchListings") ){ %>
			document.getElementById("sort_by_row").style.display = 'table-row';
<% }else{ %>
			document.getElementById("sort_by_row").style.display = 'none';
<% } %>
			page='<%=aszCurrentURL%>';
			width='<%=aszWidth%>';
			height='<%=aszHeight%>';
			preview='';
			break;
	}
	var iframeinput = "<iframe id=\"content\" width=\""+ width +"\" height=\""+ height +"\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>"+ page +"&skipbanner=skipbanner&preview=" + preview + 
			"&searchkey1="+sortby_choice_embed + "&bckgrnd=" + bckgrnd +
			"&brdr=" + brdr + "&atxt=" + atxt + "&txt=" + txt + "\"  style=\"overflow: auto;\"></iframe>";
	$('#orglistings').val(iframeinput);
	autoSelect(document.getElementById('orglistings'));
}
function changeIframeText(){
	var sortbyembed= document.getElementById('sortby_embed');
	var sortby_choice_embed= sortbyembed.options[sortbyembed.selectedIndex].value;
	var preview= document.getElementById('preview').value;
	var bckgrnd= document.getElementById('bckgrnd').value;
	var brdr= document.getElementById('brdr').value;
	var atxt= document.getElementById('atxt').value;
	var txt= document.getElementById('txt').value;
	var width= document.getElementById('width').value;
	var height= document.getElementById('height').value;
	if(bckgrnd.charAt(0)=='#'){
		bckgrnd=bckgrnd.substring(1,bckgrnd.length);
	}
	if(brdr.charAt(0)=='#'){
		brdr=brdr.substring(1,brdr.length);
	}
	if(atxt.charAt(0)=='#'){
		atxt=atxt.substring(1,atxt.length);
	}
	if(txt.charAt(0)=='#'){
		txt=txt.substring(1,txt.length);
	}

	var iframeinput = "<iframe id=\"content\" width=\""+width+"\" height=\""+height+"\" name=\"content\" frameborder=\"0\" scrolling=\"yes\" " + 
			"src=\"http://<%=aszSubdomain%><%=aszPortalLinkEmbed%><%=aszCurrentURL%>&skipbanner=skipbanner&preview=" + preview + 
			"&searchkey1="+sortby_choice_embed + "&bckgrnd=" + bckgrnd +
			"&brdr=" + brdr + "&atxt=" + atxt + "&txt=" + txt + "\"  style=\"overflow: auto;\"></iframe>";
	document.getElementById('orglistings').value = iframeinput;
}
function showPreviewIframe(){
	var iframeinput= document.getElementById('orglistings').value;
	document.getElementById('iframe_preview').innerHTML = iframeinput;
}
function show_embed_div(){
	document.getElementById("embed_div").style.display = 'inline';
}
</script>

<div id="iframe_list" style="display:none;">
<table cellpadding="2" cellspacing="0" border="0">

<tr id="preview_row" style="display:none;"><td>Number of Listings: </td>	<td><input type="text" value="" name="preview" id="preview" onchange="changeIframeText()"></td></tr>

<tr>
<td>Background Color: </td>		<td><input type="text" value="#FFFFFF" name="bckgrnd" id="bckgrnd" onchange="changeIframeText()"></td>
<td>Color of border: </td>		<td><input type="text" value="#000000" name="brdr" id="brdr" onchange="changeIframeText()"></td>
</tr>
<tr>
<td>Color of link text: </td>	<td><input type="text" value="#003366" name="atxt" id="atxt" onchange="changeIframeText()"></td>
<td>Color of text: </td>		<td><input type="text" value="black" name="txt" id="txt" onchange="changeIframeText()"></td>
</tr>
<tr>
<td>Width: </td>	<td><input type="text" value="650" name="atxt" id="width" onchange="changeIframeText()"></td>
<td>Height: </td>		<td><input type="text" value="400" name="txt" id="height" onchange="changeIframeText()"></td>
</tr>
<tr id="sort_by_row"><td>Sort Results By: </td>		
<td colspan=3>
	<SELECT id="sortby_embed" name="searchkey1" class="smalldropdown"  onchange="changeIframeText()"> 
        <option value="">Popularity</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
<% if(aszCurrentURL.contains("postalcode") ){ %>
		<option value="distance">Distance</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity (Annually)</option>
    </SELECT>
</td>
</tr>
</table>

<br>
<b>Place the following code into your site:&nbsp;</b>
<div id="iframe_text">
<input onclick="autoSelect(this)" type="text" name="orglistings" id="orglistings" size="90" styleClass="textinputwhite" 
value='<iframe id="content" WIDTH="650" HEIGHT="400" name="content" frameborder="0" scrolling="yes" 
src="http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/<%=aszCurrentURL%>&skipbanner=skipbanner"  style="overflow: auto;"></iframe>'/>
</div>



<br>


<button type="button" id="prviewIframe" onclick="showPreviewIframe()">Preview Embed</button>

<div id="iframe_preview">
</div>



</div>

<div id="search_box" style="display:none;">
<b>Add this search to your site:&nbsp;</b>
<textarea cols="100" rows="10" wrap="off">

</textarea>
</div>
</div>
          