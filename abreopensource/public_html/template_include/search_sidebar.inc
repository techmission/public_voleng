<!-- start sidebar.inc -->
<%
String aszParamFacet = "";
String aszParamFacetJSP = "";
%>

<script language="javascript">
function toggle_menu_sidebar(){
	var sidebar = document.getElementById("sidebar"); 
	var menu_sidebar = document.getElementById("menu_sidebar"); 
	if($('#sidebar').is(":hidden") ){
		$('#sidebar').show();
	}else{
		$('#sidebar').hide();
	}
</script>
<div id="menu_sidebar" onclick="toggle_menu_sidebar()">
<img src="http://www.christianvolunteering.org/imgs/mobile-menu-button.png" alt="menu" border="0"/>
</div>

<div id="sidebar" class="search_results">
	<div id="sidebar_content">
		
		<br>
		<logic:notEmpty name="facetlist" >
			<logic:iterate id="facets" name="facetlist" type="String[]">
			<!-- Facet: <%=facets[0]%> ;   <%=aszFQParams%><br> -->
				<% 
					aszParamFacet = facets[1];
					aszParamFacetJSP = aszParamFacet;
					if(aszParamFacet.contains("geo")){ // geographical info
						aszParamFacet = aszParamFacet.replaceAll("&","%26");
						aszParamFacet = "&fq=" + facets[0] + ":&quot;" + aszParamFacet + "&quot;";
						aszParamFacetJSP = "";
					} else {

						String aszFacets0 = facets[0];

						String aszFacets1 = aszParamFacetJSP.replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");

						if(aszFacets0.equals("location_data")){
							aszFacets0 = "loc";
						}else if(aszFacets0.equals("city_tax")){
							aszFacets0 = "ct";
						}else if(aszFacets0.equals("country_tax")){
							aszFacets0 = "ctr";
						}else if(aszFacets0.equals("province_tax")){
							aszFacets0 = "st";
						}else if(aszFacets0.equals("region")){
							aszFacets0 = "r";
						}else if(aszFacets0.equals("org_affil")){
							aszFacets0 = "na";
						}else if(aszFacets0.equals("service_areas")){
							aszFacets0 = "sa";
						}else if(aszFacets0.equals("skills")){
							aszFacets0 = "sk";
						}else if(aszFacets0.equals("great_for")){
							aszFacets0 = "gf";
						}else if(aszFacets0.equals("frequency")){
							aszFacets0 = "f";
						}else if(aszFacets0.equals("benefits_offered")){
							aszFacets0 = "b";
						}else if(aszFacets0.equals("trip_length")){
							aszFacets0 = "tl";
						}else if(aszFacets0.equals("denom_affil")){
							aszFacets0 = "d";
						}else if(aszFacets0.equals("primary_opp_type")){
							aszFacets0 = "pot";
						}else if(aszFacets0.equals("position_type")){
							aszFacets0 = "pt";
						}else if(aszFacets0.equals("looking_for")){
							aszFacets0 = "lf";
						}

						aszParamFacetJSP = "/" + aszFacets0 + "/" + aszFacets1 + "";
						aszParamFacet = aszParamFacet.replaceAll("&","%26");
					}
					aszParamFacet = "fq=" + facets[0] + ":&quot;" + aszParamFacet + "&quot;";
					out.print("<!-- aszParamFacet is "+aszParamFacet+" -->");


						String aszHashSearchInc = aszFQParams;
						if(aszHashSearchInc.length() > 0) aszHashSearchInc += "&";
						aszHashSearchInc += aszParamFacet;
				%> 
				<a href="/s<%=aszParamFacetJSP%><%=aszFQParamsURL%>/results.jsp#<%=aszHashSearchInc%>"><%=facets[1]%> (<%=facets[2]%>)</a>
				<br>
			</logic:iterate>
		</logic:notEmpty>
		
		<hr class="sidebar_hr"> 
	</div>
</div>
<!-- end sidebar.inc -->
