<!-- top search -->

<html><head>

<script language="javascript">

function topopp_search()

{

document.getElementById('searchumcontent').style.display='none';

document.getElementById('cvoppsearch').style.display='inline';

document.getElementById('cvorgsearch').style.display='none';

document.getElementById('searchuserjoint').style.display='none';

document.getElementById('cont').style.backgroundColor = "";

document.getElementById('user').style.backgroundColor = "";

document.getElementById('opp').style.backgroundColor = "#EEEEEE";

document.getElementById('org').style.backgroundColor = "";

}

function toporg_search()

{

document.getElementById('searchumcontent').style.display='none';

document.getElementById('cvoppsearch').style.display='none';

document.getElementById('cvorgsearch').style.display='inline';

document.getElementById('searchuserjoint').style.display='none';

document.getElementById('cont').style.backgroundColor = "";

document.getElementById('user').style.backgroundColor = "";

document.getElementById('opp').style.backgroundColor = "";

document.getElementById('org').style.backgroundColor = "#EEEEEE";

}

function umcont_search()

{

document.getElementById('searchumcontent').style.display='inline';

document.getElementById('cvoppsearch').style.display='none';

document.getElementById('cvorgsearch').style.display='none';

document.getElementById('searchuserjoint').style.display='none';

document.getElementById('cont').style.backgroundColor = "#EEEEEE";

document.getElementById('user').style.backgroundColor = "";

document.getElementById('opp').style.backgroundColor = "";

document.getElementById('org').style.backgroundColor = "";

}

function umuser_search()

{

document.getElementById('searchumcontent').style.display='none';

document.getElementById('cvoppsearch').style.display='none';

document.getElementById('cvorgsearch').style.display='none';

document.getElementById('searchuserjoint').style.display='inline';

document.getElementById('cont').style.backgroundColor = "";

document.getElementById('user').style.backgroundColor = "#EEEEEE";

document.getElementById('opp').style.backgroundColor = "";

document.getElementById('org').style.backgroundColor = "";

}

function clearText(thefield){

if (thefield.defaultValue==thefield.value)

thefield.value = "";

}

</script></head>



<body >



<div id="topsearch">



<table>

<td id="opp" style="background: #eeeeee">&nbsp;<a style="cursor:pointer; text-decoration:none; color:#000000; font-weight: normal" onClick="topopp_search()" name="method" value="processOppSearchAll">Volunteer Opportunities</a>&nbsp;</td><td> |</td>

&nbsp;<td id="cont">&nbsp;<a style="cursor:pointer; text-decoration:none; color:#000000; font-weight: normal" onClick="umcont_search()" name="method" value="allsearch">Content</a>&nbsp;</td><td> | </td>

&nbsp;<td id="user">&nbsp;<a style="cursor:pointer; text-decoration:none; color:#000000; font-weight: normal" onClick="umuser_search()" name="method" value="usersearch">Users</a>&nbsp;</td><td> |</td>

&nbsp;<td id="org">&nbsp;<a style="cursor:pointer; text-decoration:none; color:#000000; font-weight: normal" onClick="toporg_search()" name="method" value="processOrgSearchAll">Organizations</a>&nbsp;</td>

</table>



<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvoppsearch" style="display:inline">

<form name="searchForm" method="get" action="http://www.christianvolunteering.org/oppsrch.do">

<input type="hidden" name="voltype" value=""><input type="hidden" name="distance" value="City"><input type="hidden" name="method" value="processOppSearchAll"><input type="text" name="postalcode" maxlength="11" size="60" id="postalcode" class="textinputwhite" value="Postal Code" onFocus="clearText(this)"> <input type="submit" name="Submit" value="Search">

<br><a href="http://www.christianvolunteering.org/advancedsearch.jsp" style="font-size:10px; font-weight:normal">Advanced Search</a></form>

</div>



<div id="cvorgsearch" style="display:none">

<form name="searchForm" method="get" action="http://www.christianvolunteering.org/oppsrch.do">

<input type="hidden" name="voltype" value=""><input type="hidden" name="distance" value="City"><input type="hidden" name="method" value="processOrgSearchAll"><input type="text" name="postalcode" maxlength="11" size="60" id="postalcode" class="textinputwhite" value="Postal Code" onFocus="clearText(this)"> <input type="submit" name="Submit" value="Search">

<br><a href="http://www.christianvolunteering.org/advancedsearch.jsp" style="font-size:10px; font-weight:normal">Advanced Search</a>&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:10px; font-weight:normal">Powered by ChristianVolunteering.org</font></form>

</div>



<!-- urbanministry search -->

<div id="searchumcontent" style="display:none">

<form action="http://www.urbanministry.org/search/node"  method="post" id="search-form" class="search-form">

<input type="text" maxlength="255" name="keys" id="edit-keys"  size="60" value="" class="form-text" /> <input type="submit" name="op" id="edit-submit" value="Search"  class="form-submit" /><input type="hidden" name="form_id" id="edit-search-form" value="search_form"  />

<br><a href="/search/node" style="font-size:10px; font-weight:normal">Advanced Search</a></form>

</div>



<div id="searchuserjoint" style="display:none">

<form action="http://www.urbanministry.org/search/user"  method="post" id="search-form" class="search-form">

<input type="text" maxlength="255" name="keys" id="edit-keys"  size="60" value="" class="form-text" /> <input type="submit" name="op" id="edit-submit" value="Search"  class="form-submit" /><input type="hidden" name="form_id" id="edit-search-form" value="search_form"  />

<br><a href="/search/user" style="font-size:10px; font-weight:normal">Advanced Search</a></form>

</div>



</div>

</body></html>