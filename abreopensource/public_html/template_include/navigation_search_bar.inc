<% if(aszEmptySearch=="true"){ %>
<% }else if(( aszHostID.equalsIgnoreCase("volengchurch") && aszPortal.length()>0 )){ %>
<% }else if(aszMobileSubdomain.length()>3){ %>
<% }else if(aszNoSearchNav=="false"){ %>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/navigation_search.js"></script>

<table><tr><td>
    <span id="label">Find:</span>
</td>
    <td>
        <select class="smalldropdown" name="srchmethod" id="srchmethod" onChange="change_search( this.value )">
			 <option value="processOppSearchAll" >Volunteer Opportunities</option> 
 			 <option value="processOrgSearchAll">Organizations</option> 
			 <option value="allsearch">Content</option> 
		</select>
</td>

</td><td>
<!-- Volunteer Search -->
<div id="cvoppsearch" style="display:inline">

<form name="searchForm" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="postype" value="">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" value="navigationSrchBar" name="formname" id="formname"/>
<input type="text" name="postalcode" maxlength="11" id="postalcode" class="fld watermark" value="Postal Code" onFocus="clearText(this)">
<input type="submit" name="Submit" value="Search">

<a href="<%=aszPortal%>/advancedsearch.jsp">Advanced Search</a>

</form>

</div>
<!-- end:Volunteer Search -->

<!-- urbanministry search -->

<div id="searchumcontent" style="display:none">

<!--begin Google Custom Search -->
<form name="searchForm" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form">
<input type="hidden" value="feeds" name="source" id="source"/>
<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
<input type="text" maxlength="128" class="fld watermark" name="query" id="edit-query"  value="" />
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
</div>
<!-- end Google Custom Search-->

<!-- Organization Search -->
<div id="cvorgsearch" style="display:none">

<form name="searchForm" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" value="feeds" name="source" id="source"/>
<input type="hidden" name="requesttype" value="urlalias">

	<input type="hidden" name="voltype" value=""><input type="hidden" name="distance" value="City"><input type="hidden" name="method" value="processOrgSearchAll">
	<input type="text" name="postalcode" maxlength="11" id="postalcode" class="fld watermark" value="Postal Code" onFocus="clearText(this)"> <input type="submit" name="Submit" value="Search">
	<a href="<%=aszPortal%>/advancedsearch.jsp">Advanced Search</a>

</form>

</div>
<!-- end: Organization Search -->
</div>
</div>

</td></tr></table>
<% } %>