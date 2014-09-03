<script language="javascript">
/*
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
console.log('id '+id);
	var root_facet_element = document.getElementById(id); 
	var class_name = root_facet_element.className;
	if(class_name.indexOf(default_class) !== -1){
		root_facet_element.className=default_class+expanded_class;
		
		for(i=0; i<root_facet_element.childNodes.length; i++){
			var tagcloud_div=root_facet_element.childNodes[i];
			for(j=0; j<tagcloud_div.childNodes.length; j++){
				var list_element=tagcloud_div.childNodes[j];
				if(list_element.style == undefined){
				}else{
					var init_class = list_element.className;
					if(init_class=='filter collapsed'){
						list_element.style.display="inline";
						list_element.className = default_class+expanded_class;
					}
				}
			}
		}
	}
	var more_link = 'more_link_'+facet_field;
console.log('more_link '+more_link);
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display="none;"
		more_link_element.className = collapsed_class;
	}

console.log('testttt '+more_link);
	var midbox1_height = $('#midbox1_content').height();
	var midbox2_height = $('#midbox2_content').height();
	var midbox3_height = $('#midbox3_content').height();
	var midbox_overall_height = midbox1_height;
	if( midbox2_height > midbox_overall_height) midbox_overall_height = midbox2_height;
	if( midbox3_height > midbox_overall_height) midbox_overall_height = midbox3_height;
console.log('midbox_overall_height '+midbox_overall_height);
	$('#midbox1_content').height(midbox_overall_height);
	$('#midbox2_content').height(midbox_overall_height);
	$('#midbox3_content').height(midbox_overall_height);
}
*/
</script>
<div id="facet_links_crawler" style="display:none;">
<%
int iNumDisplay = 10;
int iCountDisplay = 0;

String aszDisplayClass = "";

String aszFQParams = "";
String aszFQParamsURL = "";

String aszFQParamValue = "";
String aszFQParamFacet = "";
String aszFQParamFacetJSP = "";


boolean bLocationFacet = false;
try{
	if(request.getParameter("fq") != null){
		String aszFQParamFacetURL = "";
		String aszFQParamValueURL = "";
		String[] a_aszFQParams = new String[255];
		a_aszFQParams = request.getParameterValues("fq");
		for(int i = 0; i < a_aszFQParams.length; i++){
			aszFQParamValue = a_aszFQParams[i];
			if(aszFQParamValue.contains(":")){
				int iTmp = aszFQParamValue.indexOf(":");
				if(aszFQParamValue.length()>iTmp+1){
					aszFQParamFacet = aszFQParamValue.substring(0,iTmp);
						aszFQParamFacetURL = aszFQParamFacet;
						aszFQParamValueURL = aszFQParamValue.replaceAll(" ","_");
						aszFQParamValueURL = aszFQParamValue.replaceAll("/","|");
						aszFQParamValueURL = aszFQParamValue.replaceAll("&","~");
						aszFQParamValueURL = aszFQParamValueURL.replaceAll("\\.", ";");
						if(aszFQParamValueURL.length() > 1){
							if(aszFQParamValueURL.endsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(0,aszFQParamValueURL.length()-1);
							if(aszFQParamValueURL.startsWith("\"")) aszFQParamValueURL=aszFQParamValueURL.substring(1);
							if(! aszFQParamFacet.equals("content_type")){
								aszFQParamsURL+="/"+aszFQParamFacetURL+"/"+aszFQParamValueURL;
							}
						}

				}	
			}
		}
	}
}catch(NullPointerException e){
}

out.print("aszFQParams is "+aszFQParams);
if(aszFQParams.endsWith("&")){
	aszFQParams = aszFQParams.substring(0,aszFQParams.length()-1);
}
if(aszFQParams.length() < 2){
	aszFQParams = "";
}
aszFQParamsURL = aszFQParamsURL.replaceAll("\"","&quot;");
//if(aszFQParamsURL.length()>0)	aszFQParamsURL = aszFQParamsURL.substring(0,aszFQParamsURL.length()-1);
if(aszFQParamsURL.endsWith("&")){
	aszFQParamsURL = aszFQParamsURL.substring(0,aszFQParams.length()-1);
}
if(aszFQParamsURL.startsWith("&")){
	aszFQParamsURL = aszFQParamsURL.substring(1);
}
%>
		<logic:notEmpty name="facetlist" >
			<logic:iterate id="facets" name="facetlist" type="String[]">
			<!-- Facet: <%=facets[0]%> ;   <%//=aszFQParams%><br> -->
			<%
				// if FQParams includes data like "denom affil", "primary opp type", "natl affil", then we should automatically apply &fq/q=content_type:organization;
				//		otherwise &fq/q=content_type:volunteer_opportunity
						String aszFacets0 = facets[0];
						
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
								}else if(aszFacets0.equals("postal_code")){
									aszFacets0 = "zip";
								}else if(aszFacets0.equals("content_type")){
									aszFacets0 = "ctp";
								}
						
						String aszFacets1 = facets[1].replaceAll(" ","_");
						aszFacets1 = aszFacets1.replaceAll("/","|");
						aszFacets1 = aszFacets1.replaceAll("&", "~");
						aszFacets1 = aszFacets1.replaceAll("\\.", ";");
						
						String aszTempURLParams = "s/" + aszLandingParamsURL;
						if (aszTempURLParams.length() < 1) aszTempURLParams = aszFQParamsURL;
//			<a href="< %=aszPortal% >/< %=aszTempURLParams% >/< %=aszFacets0% >/< %=aszFacets1% >/results.jsp#< %=aszFQParams% >fq=< %=facets[0]% >:&quot;< %=facets[1]% >&quot;">< %=facets[1]% > (< %=facets[2]% >)</a>
//			<br>
			%>
			</logic:iterate>
		</logic:notEmpty>

</div>