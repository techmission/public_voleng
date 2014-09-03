<script language="javascript">
function toggle_facet(facet_field){
	var default_class="filter";
	var handle_class=" handle";
	var collapsed_class=" collapsed";
	var expanded_class=" expanded";

	var id = facet_field;
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
	var more_link_element = document.getElementById(more_link); 
	if(more_link_element===undefined){
	}else{
		more_link_element.style.display="none;"
		more_link_element.className = collapsed_class;
	}
}
</script>
<div id="facet_links_crawler" style="display:none;">
<%
int iNumDisplay = 10;
int iCountDisplay = 0;

String aszDisplayClass = "";

String aszFQParams = "";
String aszKeywordSearch = "";
try{
	if(request.getParameter("fq") != null){
		String[] a_aszFQParams = new String[255];
		a_aszFQParams = request.getParameterValues("fq");
		for(int i = 0; i < a_aszFQParams.length; i++){
			String aszFQParam = a_aszFQParams[i].replaceAll("&","%26");
			aszKeywordSearch+="fq="+aszFQParam + "&";
		}
	}
}catch(NullPointerException e){
}
aszFQParams = aszKeywordSearch.replaceAll("\"","&quot;");
if(aszFQParams.length()>0)	aszFQParams = aszFQParams.substring(0,aszFQParams.length()-1);
%>
		<logic:notEmpty name="facetlist" >
			<logic:iterate id="facets" name="facetlist" type="String[]">
			<!-- Facet: <%=facets[0]%> ;   <%//=aszFQParams%><br> -->
			<%
				// if FQParams includes data like "denom affil", "primary opp type", "natl affil", then we should automatically apply &fq/q=content_type:organization;
				//		otherwise &fq/q=content_type:volunteer_opportunity
			%>
			<a href="<%=aszPortal%>/oppsrch.do?method=processSearch&<%=aszFQParams%>&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;#<%=aszFQParams%>&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot;"><%=facets[1]%> (<%=facets[2]%>)</a>
			<br>
			</logic:iterate>
		</logic:notEmpty>

</div>