<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>
<title>Christian Volunteer Network: Sitemap</title>
<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Search URL Translator</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Sitemap </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; sitemap </div>

</div>
<% } %>



 <div id="body">

 <head>

                    <meta name="keywords" content="community, computer, urban, ministry, training, technology, vulnerable communities, vunerable lives, at risk youth, low income, poor, online safety, protecting kids online, urban ministry internship, paid internship">

                    <meta name="description" content="Explore our wide range of resources in Technology and ministry">

                </head>


  <% SearchURLTranslatorDTO searchURLTranslator = (SearchURLTranslatorDTO) request.getAttribute("searchURLTranslator"); %>

<style>
<% if( !(aszNarrowTheme.equalsIgnoreCase("true"))  ) { %>
#form{
	margin-left: 20px;
}
<% } %>

#form .left-column-wide{
	float: left;
	padding: 3px;
	//padding:0;
	width: 100px;
	text-align:right;
}
#form .left-column{
	float: left;
	padding: 3px;
	//padding:0;
	width: 100px;
	line-height:2em;
	text-align:right;
}
#form .right-column{
	float: left;
	//float:none;
	padding: 3px;
	//padding:0;
}
#form .span-columns{
	padding: 10px;
/*	float: left; */
}
#location #form .left-column{
	line-height:1em;
}
</style>
  
  <h1>Translate ChristianVolunteering.org search URL into API Request</h1>
  
  <form id="searchForm" name="searchForm" method="POST" action="<%=aszPortal%>/oppsrch.do"?>
    <input type="hidden" name="method" value="processSearchURLTranslator"/>
    <div id="form">
	  <FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><ul><%=searchURLTranslator.getErrorMsg()%></ul></pre></FONT>
	  
      <div class="span-columns">
    	<ol>
    		<li>Perform a Search on ChristianVolunteering.org and paste in the URL</li>
    		<li>Enter your API key (to obtain an API key, register at <a href="<%=aszPortal%>/apitos.jsp">http://www.christianvolunteering.org/apitos.jsp)</a></li>
    		<li>Select the format you prefer for retrieving data</li>
    	</ol>
	  </div>
	  <br clear="all" />

      <div class="left-column">
    	<b>URL:</b> 
      </div>
	  <div class="right-column">
	    <input type="text" name="url" value="<%=searchURLTranslator.getURL()%>"/>
	  </div>
	  <br clear="all" />
	  
	  <div class="left-column">
	    <b>API Key:</b>
	  </div>
	  <div class="right-column">
	    <input type="text" name="apikey" value="<%=searchURLTranslator.getAPIKey()%>"/>
	  </div>
	  <br clear="all" />
	  
	  <div class="left-column">
	    <b>Output Format:</b>
	  </div>
	  <div class="right-column"> 
	    <select name="outputFormat">
	      <option value=""></option>
	      <% 
            for(String format : new String[] {"json", "xml", "rss", "csv", "kml"})
		    out.write(
		      "<option value=\"" + format + "\"" + 
	  		  (format.equals(searchURLTranslator.getOutputFormat()) ? " selected=true" : "") + ">" + 
		 	  format + 
			  "</option>");
          %>
	    </select>
	  </div>
	  <br clear="both" />
	
	  <div class="left-column">
	    <input type="submit" value="translate"/>
	  </div>
	  <br clear="both" />
	
	  <div class="left-column">
	    <a style="font-weight:bold" href="<%=aszPortal%>/apitos.jsp">Terms of Service</a>
	  </div>
	  <br clear="both" />
	  
	  <% if(!searchURLTranslator.getTranslatedURL().isEmpty()) { %>
	    <div class="left-column">
	      <b>API Request: </b>
	    </div>
	    <div class="right-column">
		  <% if(!searchURLTranslator.getTranslatedURL().isEmpty()) {%>
	        <a href="<%= searchURLTranslator.getTranslatedURL() %>"><%= searchURLTranslator.getTranslatedURL() %></a>
		  <% } %>
	    </div>
	    <br clear="all" />
	  <% } %>
	</div>
  </form>

            

            </div>

</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>

<!-- end sidebar information -->

<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->