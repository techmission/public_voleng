<%//@ include file="/template/topjspnologin1.inc" %>
<%
int iTemp=0;
%>

<%
int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550;

String aszRoomBoardTID= "" + iRoomBoardTID, aszStipendTID= "" + iStipendTID, aszMedInsurTID= "" + iMedInsurTID, 
	aszTransportTID= "" + iTransportTID, aszAmeriCorpsTID= "" + iAmeriCorpsTID;
%>

<script language="javascript">

function localopp_search()
{ 
document.getElementById('cvoppsrch').style.display='inline';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "active";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "";
}

function intrn_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "active";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "";
}

function stm_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "active";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "";
}

function pro_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='inline';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "active";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "";
}

function org_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='inline';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "active";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "";
}



function trn_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='inline';
document.getElementById('cvadvsearch').style.display='none';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "active";
document.getElementById('adv').className = "";
}

function adv_search()
{
document.getElementById('cvoppsrch').style.display='none';
document.getElementById('cvintrnsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('cvprosearch').style.display='none';
document.getElementById('cvorgsrch').style.display='none';
document.getElementById('cvtrnsearch').style.display='none';
document.getElementById('cvadvsearch').style.display='inline';

document.getElementById('opplocal').className = "";
document.getElementById('intrn').className = "";
document.getElementById('stm').className = "";
document.getElementById('pro').className = "";
document.getElementById('organiz').className = "";
document.getElementById('trn').className = "";
document.getElementById('adv').className = "active";
}

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

function toggle(chkbox, item1, item2) {
    //var visSetting = (chkbox.checked) ? "hidden" : "visible";
	var disableSetting = (chkbox.checked) ? "true" : "false";
	if (document.getElementById(item1).disabled == false)
	{
    document.getElementById(item1).disabled = true;
	document.getElementById(item2).disabled = true;
	}
	else {
	document.getElementById(item1).disabled = false;
	document.getElementById(item2).disabled = false;
	}
}
/* toggle based on http://www.java2s.com/Code/JavaScript/Form-Control/ACheckboxandanonClickeventHandler.htm */

</script>

<style>

a#opplocal, a#organiz, a#stm, a#intrn, a#trn, a#adv, a#pro{
cursor:pointer; 
text-decoration:none; 
color:#000000; 
font-size:1em;
//font-size:12px; 
font-weight: normal;
background:transparent url(/imgs/tabs-off.png) no-repeat; 
border-right: 2px solid #5e9048; 
padding:3px 5px 0;
font-weight: bold;
color:#456D34;
}
a#opplocal.active, a#organiz.active, a#stm.active, a#intrn.active, a#trn.active, a#adv.active, a#pro.active {
background:#6A8F59 url(/imgs/tabs-on.png) no-repeat; 
border-right: 2px solid #456D34;
font-weight: bold;
color:#FFF;
}
</style>

	<div style="float:left; margin: 0px 0px 5px 10px; width: 100%;">

	<A style="text-decoration: none" HREF="<%=aszPortal%>/advancedsearch.jsp"><h2>Anya's search</h2></A>

	<div id="vol-opp" style="display:inline">
	<a id="opplocal"  class="active" onClick="localopp_search()" name="method" value="processOppSearchAll">Local Volunteering</a>
	</div>
    
    <div id="internship" style="display:inline">
	<a id="intrn" onClick="intrn_search()" name="method" value="processOppSearchAll">Internship</a>
	</div> 
      
	<div id="short-term" style="display:inline">
	<a id="stm" onClick="stm_search()" name="method" value="processOppSearchAll">Short-term Missions</a>
	</div> 
    
    <div id="vol-pro" style="display:inline">
	<a id="pro" onClick="pro_search()" name="method" value="processOppSearchAll">For Professionals</a>
	</div>
	
	<div id="organizations" style="display:inline">
	<a id="organiz" onClick="org_search()" name="method" value="processOrgSearchAll">Organizations</a>
	</div>


	<div id="content" style="display:inline">
	<a id="trn" onClick="trn_search()" name="method" value="allsearch">Training</a> <!-- used to be umcont_search()-->
	</div> 
	
	<div id="advanced" style="display:inline">
	<a id="adv" onClick="adv_search()" name="method" value="allsearch">Advanced</a>
	</div> 



<hr size="2" color="#456D34" width="100%" style=" margin-top: 0px;" />


<!-- Local Volunteering search-->
		<div id="cvoppsrch" style="display:inline">
	
			<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
			<input type="hidden" name="method" value="processOppSearchAll">
			<input type="hidden" name="distance" value="City">
			<input type="hidden" name="voltype" value="">
			
			
			</form>
	<br/>
	<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
	Like homepage search but with
		* Skills
		* I am a... checkboxes
		* Work Study Checkbox
		<br/><a href="http://www.urbanministry.org/redirect-home">My City</a>
        <br/><a href="http://www.urbanministry.org/volunteer/search/results/taxonomy%3A4796">Browse Local Volunteer Opportunities</a>
	</div><!-- end cvoppsearch -->
<!-- end Local Volunteering search-->


<!--- Internship Search   -->
	<div id="cvintrnsearch" style="display:none">
	(similar to short term missions search on homepage, but)
	
		* Length of Internship: Summer, 3-5 months, 1 years, 1-2 years
		* Work study checkbox
		* List all benefits
		* Browse: http://www.urbanministry.org/volunteer/search/results/taxonomy%3A4796
	
	</div><!-- end cvintrnsearch -->
<!---  end Internship Search-->


<!--- Short Term Missions Search-->
	<div id="cvstmsearch" style="display:none">
	<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
	<input type="hidden" name="method" value="processOppSearchAll">
	<input type="hidden" name="distance" value="City">
	<input type="hidden" name="voltype" value="Short-term Missions / Volunteer Internship">
	

	</form>
	<b><a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="browse Christian short term urban missions, social service ministry internship volunteer opportunities" style="float: right;">browse opportunities</a></b>
	# Same as on homepage
	# List all benefits (Ali knows)
	</div><!-- end cvstmsearch -->
<!--- end Short Term Missions Search-->

<!-- For Professionals search-->
	<div id="cvprosearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">

</form>
<br/>
<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
(like homepage search, but..)

    * By Skill Dropdown
    * Board member checkbox
    * Virtual checkbox
    * Remove group checkbox
    * Browse Virtual Volunteering by Skill Required: http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4795
    * Browse Board Positions: http://www.urbanministry.org/volunteer/search/facet/taxonomy%3A31/count/taxonomy%3A4761 
</div><!-- end cvprosearch -->
<!-- end For Professionals search-->


<!--- Organization Search-->
<div id="cvorgsrch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">

</form>
<b><a href="http://www.urbanministry.org/org/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse organizations</a></b>
(same as on homepage, but add)

    * Organizational Name
    * Affiliations Dropdown
    * Denominational affiliation dropdown
</div><!-- end cvorgsrch -->
<!--- end Organization Search-->

<!-- Training Materials search-->
	<div id="cvtrnsearch" style="display:none">

<form name="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">

</form>
<br/>
<b><a href="http://www.urbanministry.org/volunteer/search" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" style="float: right;">browse opportunities</a></b>
Browse options?
</div><!-- end cvtrnsearch -->
<!-- end Training Materials search-->

<!--- Advanced Search-->
<div id="cvadvsearch" style="display:none">
<%@ include file="/template_include/home_search7tabs_advanced.jsp" %>

</div><!-- end cvadvsearch -->
<!--- end Advanced Search-->


<!-- urbanministry search -->

<div id="srchumcontent" style="display:none">

<div style="float:center; margin: 0px 0px 5px 20px; width: 100%">




Search thousands of articles, sermons and videos<br/>on volunteering, urban ministry, and social justice:
<br/><br/>

<!--begin Google Custom Search -->
<form name="search-form" class="search-form" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form">

<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
<input type="text" maxlength="128" class="textinputwhite" style="width:220px;" name="query" id="edit-query"  value="Enter Keyword" onClick="clearText(this)" />
<input type="submit" name="op" id="edit-sa" value="Search" />
<input type="hidden" name="form_token" id="edit-google-cse-results-searchbox-form-form-token" value="f1596786f0691ac334237131d60e5654"  />
<input type="hidden" name="form_id" id="edit-google-cse-results-searchbox-form" value="google_cse_results_searchbox_form"  />
</form>
<div id="search_results"><noscript><a href="http://www.google.com/cse?q=&cx=017405804136166701815:nc9jskbtk8y&cof=FORID:0">View the results at Google</a>, or enable JavaScript to view them here.</noscript></div>
    <script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script>      <!-- end Google Custom Search-->
	
<br/><a href="http://www.urbanministry.org/faceted_search">advanced search</a>&nbsp;|&nbsp;<a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions">browse articles</a>

<br/><br/>Powered by <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

</form>



</div>


</div>




<div class="cleardiv"></div>
</div>


