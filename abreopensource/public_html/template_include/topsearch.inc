<style>
#searchtop{
font-size:11px;
}

/* Customizations of searchtop */
#topsearch{
width:320px;
padding-right: 20px;
}

#topsearch form{
	
	border:solid 1px #959185; 
	-moz-border-radius-bottomleft: 5px;
	-moz-border-radius-bottomright: 5px;
	-moz-border-radius-topleft: 0px;
	-moz-border-radius-topright:5px;
}
#topsearch_nav li.active {
	/* background: #D6D7C4; -light green*/
	background: #D6D7C4; /*yellow*/
	/*border: 1px solid #959185; */
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
}
#topsearch_nav li{
background-color: #E7E7E7;
-moz-border-radius-topleft: 5px;
-moz-border-radius-topright: 5px;
}
/* end of customizations */

</style>

<link href="http://www.christianvolunteering.org/template_include/topsearch_styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/topsearch_scripts.js"></script>

<div id="topsearch">

<ul id="topsearch_nav">
<li id="opp" class="active"><a href="javascript:topopp_search();" name="method" value="processOppSearchAll">Volunteer Activities</a></li>
	<li id="cont"><a href="javascript:umcont_search();" name="method" value="allsearch">Content</a></li>
	<li id="org"><a href="javascript:toporg_search();" name="method" value="processOrgSearchAll">Organizations</a></li>
</ul>

<!-- Volunteer Search -->
<div id="cvoppsearch" style="display:inline">

<form name="searchForm" method="get" action="/oppsrch.do">

<input type="hidden" name="voltype" value=""><input type="hidden" name="distance" value="City"><input type="hidden" name="method" value="processOppSearchAll"><input type="text" name="postalcode" maxlength="11" id="postalcode" class="textinputwhite" value="Postal Code" onFocus="clearText(this)"> <input type="submit" name="Submit" value="Search">

<a href="/advancedsearch.jsp">Advanced Search</a>

</form>

</div>
<!-- end:Volunteer Search -->

<!-- urbanministry search -->

<div id="searchumcontent" style="display:none">

<!--begin Google Custom Search -->
<form name="searchForm" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form">

<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
<input type="text" maxlength="128" class="textinputwhite" name="query" id="edit-query"  value="" />
<input type="submit" name="op" id="edit-sa" value="Search" />
<input type="hidden" name="form_token" id="edit-google-cse-results-searchbox-form-form-token" value="f1596786f0691ac334237131d60e5654"  />
<input type="hidden" name="form_id" id="edit-google-cse-results-searchbox-form" value="google_cse_results_searchbox_form"  />


<a href="http://www.urbanministry.org/faceted_search">Advanced Search</a>

</form>
<div id="search-results"><noscript><a href="http://www.google.com/cse?q=&cx=017405804136166701815:nc9jskbtk8y&cof=FORID:0">View the results at Google</a>, or enable JavaScript to view them here.</noscript></div>

    <!--<script type="text/javascript">
    //<![CDATA[
      var googleSearchIframeName = "google-cse-results";
      var googleSearchFormName = "google-cse-results-searchbox-form";
      var googleSearchFrameWidth = 600;
      var googleSearchFrameborder = 0;
      var googleSearchDomain = "google.com";
      var googleSearchPath = "/cse";
    //]]>
    </script>-->
    <script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script>      
	<!-- end Google Custom Search-->

</div>




<!-- Organization Search -->
<div id="cvorgsearch" style="display:none">

<form name="searchForm" method="get" action="/oppsrch.do">

	<input type="hidden" name="voltype" value=""><input type="hidden" name="distance" value="City"><input type="hidden" name="method" value="processOrgSearchAll">
	<input type="text" name="postalcode" maxlength="11" id="postalcode" class="textinputwhite" value="Postal Code" onFocus="clearText(this)"> <input type="submit" name="Submit" value="Search">
	

	<a href="/advancedsearch.jsp">Advanced Search</a>

</form>

</div>
<!-- end: Organization Search -->


</div>

<!-- chrisvol - oppsearch / orgsearch -->


