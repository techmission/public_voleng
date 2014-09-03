<%
String aszSourcePath = request.getContextPath();
if(aszRemoteHost.contains(":7001")){
	aszSourcePath = "http://chrisvol.org";
}else if(aszRemoteHost.contains("staging-christianvolunteering.org")){
	aszSourcePath = "http://staging-christianvolunteering.org";
}
%>
<!-- 		aszRemoteHost is <%=aszRemoteHost%>  aszPath is <%=aszPath%>      -->

<% if(b_includeAjaxSolr){ %>
<!-- add in IE-specific fixes for facet CSS -->
<!--[if IE]>
	<link href="<%=aszSourcePath%>/template_include/css/format_boxes_ie.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/Core.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/AbstractManager.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/managers/Manager.jquery.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/Parameter.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/ParameterStore.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/ParameterHashStore.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/AbstractWidget.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/core/AbstractFacetWidget.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/jquery/ajaxsolr.theme.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/jquery/jquery.livequery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/jquery/jquery.autocomplete.css" media="screen" />
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/jquery/jquery.autocomplete.js"></script>

<%@ page import="java.io.File" %>
<% 
String aszThemeFile = "impl/searchopps.theme.js";
String aszInitController = "impl/SimpleSearchSetup.js";
String aszResultsWidget = "widgets/ResultWidget.js";
String aszMapResultsWidget = "widgets/MapResultsWidget.js"; 
String aszFacetsWidget = "widgets/FacetsSearchFormWidget.js"; 
String aszTabsWidget = "widgets/TabsWidget.js"; 
String aszDistanceSearchWidget = "widgets/DistanceSearchFormWidget.js"; 
String aszAutocompleteWidget = "widgets/AutocompleteSearchFormWidget.js"; 
String aszCurrentSearchWidget = "widgets/CurrentSearchWidget.js"; 
String aszSortByWidget = "widgets/SortByWidget.js"; 

if(aszPath.equalsIgnoreCase("/voleng2/search_results.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/search_results_embed.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/application_list.jsp") ||
	aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")
){
	if( aszPath.equalsIgnoreCase("/voleng2/application_list.jsp") ){
		aszInitController = "impl/SearchResultsSetupApplicants.js";
		aszThemeFile = "impl/searchinterns.theme.js";
	}else{
		aszInitController = "impl/SearchResultsSetup.js";
	}
	aszResultsWidget = "widgets/ResultWidget.js";
	aszMapResultsWidget = "widgets/MapResultsWidget.js";
	
	if(aszPath.equalsIgnoreCase("/voleng2/search_results.jsp") ||
		aszPath.equalsIgnoreCase("/voleng2/search_results_embed.jsp") ||
		aszPath.equalsIgnoreCase("/voleng2/application_list.jsp")
	){
		aszFacetsWidget = "widgets/FacetsWidget.js"; 
		aszDistanceSearchWidget = "widgets/DistanceSearchWidget.js"; 
		aszAutocompleteWidget = "widgets/AutocompleteSearchWidget.js";
	} 
}else if( aszPath.equalsIgnoreCase("/index.jsp") &&
			(
				aszRemoteHost.contains("hurricanesandy") 			||
				aszLandingPage.equals("disasterreliefhomepage")	||
				aszLandingPage.equals("stm")					||
				aszLandingPage.equals("foundationgrants")		||
				aszLandingPage.equals("christianjobs")			||
				aszLandingPage.equals("christiangapyear")	
			)
){ 
	aszInitController = "impl/HomepageSearchResultsSetup.js";
	aszResultsWidget = "widgets/ResultWidget.js";
	aszMapResultsWidget = "widgets/MapResultsWidget.js";
	aszFacetsWidget = "widgets/FacetsSearchFormWidget.js"; 
	aszDistanceSearchWidget = "widgets/DistanceSearchWidget.js"; 
	aszAutocompleteWidget = "widgets/AutocompleteSearchFormWidget.js"; 
}else if(aszPath.equalsIgnoreCase("/index.jsp") && aszLandingPage.length() > 0){ 
		aszInitController = "impl/LandingPageSetup.js";
}else if(aszPath.equalsIgnoreCase("/voleng2/zipsitemap.jsp")){ 
	aszInitController = "impl/ZIPSitemapSetup.js";
}else if(
	aszPath.equalsIgnoreCase("/voleng2/solr_directory.jsp")	||
	aszPath.equalsIgnoreCase("/voleng2/solr_org_directory.jsp")
){ 
	aszInitController = "impl/DirectorySetup.js";
}else if(aszPath.equalsIgnoreCase("/voleng2/search.jsp")){
	aszInitController = "impl/SimpleSearchSetup.js";
	aszFacetsWidget = "widgets/FacetsSearchFormWidget.js"; 
}	

if(
	aszLandingPage.equals("foundationgrants")	&&
	aszPath.equalsIgnoreCase("/index.jsp")
){
	aszFacetsWidget = "widgets/FacetsWidget.js"; 
}

File filesearchoppstheme = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszThemeFile); 
String aszVersionSearchOppstheme = "" + filesearchoppstheme.lastModified();

File fileInitController = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszInitController); 
String aszVersionInitController = "" + fileInitController.lastModified();

File fileResultWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszResultsWidget); 
String aszVersionResultWidget = "" + fileResultWidget.lastModified();

File fileMapResultWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszMapResultsWidget); 
String aszVersionMapResultWidget = "" + fileMapResultWidget.lastModified();

File fileFacetsWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszFacetsWidget); 
String aszVersionFacetsWidget = "" + fileFacetsWidget.lastModified();

File fileTabsWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszTabsWidget); 
String aszVersionTabsWidget = "" + fileTabsWidget.lastModified();
	
File fileDistanceSearchWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszDistanceSearchWidget); 
String aszVersionDistanceSearchWidget = "" + fileDistanceSearchWidget.lastModified();
	
File fileAutocompleteSearchWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszAutocompleteWidget); 
String aszVersionAutocompleteSearchWidget = "" + fileAutocompleteSearchWidget.lastModified();

File fileCurrentSearchWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszCurrentSearchWidget); 
String aszVersionCurrentSearchWidget = "" + fileCurrentSearchWidget.lastModified();
		
File fileSortByWidget = new File("/home/chrisvol/public_html/template_include/js/ajax-solr/" + aszSortByWidget); 
String aszVersionSortByWidget = "" + fileSortByWidget.lastModified();
%>

<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszInitController%>?<%=aszVersionInitController%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszAutocompleteWidget%>?<%=aszVersionAutocompleteSearchWidget%>"></script>
<%
	if(aszPath.equalsIgnoreCase("/voleng2/search_results.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/search_results_embed.jsp")	 ||
		aszPath.equalsIgnoreCase("/voleng2/application_list.jsp")||
		aszPath.equalsIgnoreCase("/voleng2/search_results_ajax-solr.jsp") ||
		aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")
	){
%>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszFacetsWidget%>?<%=aszVersionFacetsWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszTabsWidget%>?<%=aszVersionTabsWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszDistanceSearchWidget%>?<%=aszVersionDistanceSearchWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszCurrentSearchWidget%>?<%=aszVersionCurrentSearchWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/widgets/jquery/PagerWidget.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszResultsWidget%>?<%=aszVersionResultWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszMapResultsWidget%>?<%=aszVersionMapResultWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszSortByWidget%>?<%=aszVersionSortByWidget%>"></script>

<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/ajaxsolr.support.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/ajaxsolr.theme.js"></script>
<% 
	}else if( aszPath.equalsIgnoreCase("/index.jsp") &&
			(
				aszRemoteHost.contains("hurricanesandy") 			||
				aszLandingPage.equals("disasterreliefhomepage")	||
				aszLandingPage.equals("stm")					||
				aszLandingPage.equals("foundationgrants")		||
				aszLandingPage.equals("christianjobs")			||
				aszLandingPage.equals("christiangapyear")	
			)
	){ 
%>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszFacetsWidget%>?<%=aszVersionFacetsWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszTabsWidget%>?<%=aszVersionTabsWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszDistanceSearchWidget%>?<%=aszVersionDistanceSearchWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszCurrentSearchWidget%>?<%=aszVersionCurrentSearchWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/widgets/jquery/PagerWidget.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszResultsWidget%>?<%=aszVersionResultWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszMapResultsWidget%>?<%=aszVersionMapResultWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszSortByWidget%>?<%=aszVersionSortByWidget%>"></script>

<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/ajaxsolr.support.js"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/helpers/ajaxsolr.theme.js"></script>
<% 
	}else if(
		aszPath.equalsIgnoreCase("/voleng2/solr_directory.jsp")	||
		aszPath.equalsIgnoreCase("/voleng2/solr_org_directory.jsp")
	){ 
%>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszFacetsWidget%>?<%=aszVersionFacetsWidget%>"></script>
<% 
	}else if(aszPath.equalsIgnoreCase("index.jsp")){ 
		// no facets widget, etc
	}else if(aszPath.equalsIgnoreCase("/index.jsp") && aszLandingPage.length() > 0){ 
	}else{ 
%>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszDistanceSearchWidget%>?<%=aszVersionDistanceSearchWidget%>"></script>
<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszFacetsWidget%>?<%=aszVersionFacetsWidget%>"></script>
<% } %>

<script type="text/javascript" src="<%=aszSourcePath%>/template_include/js/ajax-solr/<%=aszThemeFile%>?<%=aszVersionSearchOppstheme%>"></script>

<% } %>
