<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">ZIP Code Sitemap</span>
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

 

  <h1>Volunteer Opportunities by Zip Code</h1>
<% 
String distance = "40.2336";
// /oppsrch.do?method=processSearch&postal_code=02453&fq=content_type:opportunity&postal_code=02453&fq={!geofilt%20pt=42.3624504,-71.25772330000001%20sfield=latlng%20d=40.2336}#fq=content_type:opportunity&fq={!geofilt%20pt=42.3624504,-71.25772330000001%20sfield=latlng%20d=40.2336}

  	if(request.getAttribute("zipCodes")!=null){
  		java.util.ArrayList element = (java.util.ArrayList<String[]>)request.getAttribute("zipCodes");
  		int iSizeArray = element.size();
  		// iterate through element; for each, set zipcode, latitude, & longitude
  		for (int i=0; i < iSizeArray; i++) {
  			String[] entry = (String[])element.get(i);
  			String zipCode = entry[0];
  			String latitude = entry[1];
  			String longitude = entry[2];
//out.println("<br>zipCode is: "+zipCode+";  latitude is: "+latitude+";  longitude is: "+longitude);
%>
    <a href="<%=request.getContextPath()%>/zip/<%=zipCode%>.jsp#fq=content_type:opportunity&fq={!geofilt%20pt=<%=latitude%>,<%=longitude%>%20sfield=latlng%20d=<%=distance%>}"><%=zipCode%></a>&nbsp;&nbsp;&nbsp;&nbsp;
    <!--    &fq=content_type:opportunity&postal_code=<%=zipCode%>&fq={!geofilt%20pt=<%=latitude%>,<%=longitude%>%20sfield=latlng%20d=<%=distance%>}   -->
    <!--a href="<%=request.getContextPath()%>/oppsrch.do?method=processSearch&postal_code=<%=zipCode%>&fq=content_type:opportunity&postal_code=<%=zipCode%>&fq={!geofilt%20pt=<%=latitude%>,<%=longitude%>%20sfield=latlng%20d=<%=distance%>}#fq=content_type:opportunity&fq={!geofilt%20pt=<%=latitude%>,<%=longitude%>%20sfield=latlng%20d=<%=distance%>}"><%=zipCode%></a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<% 
  		}
	} 
%>

            

</div>

</div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->