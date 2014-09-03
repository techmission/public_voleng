<!-- start sidebar.inc -->

<div id="sidebar" class="search_results">
	<div id="sidebar_content">
		
		<br>
				<logic:notEmpty name="facetlist" >
				
			<logic:iterate id="facets" name="facetlist" type="String[]">
			<!-- Facet: <%=facets[0]%> <br> -->
			<a href="<%=aszPortal%>/oppsrch.do<%=aszSearchURL%>&fq=<%=facets[0]%>:&quot;<%=facets[1]%>&quot"><%=facets[1]%> (<%=facets[2]%>)</a>
			<br>
			
			</logic:iterate>
		</logic:notEmpty>
		
		<hr class="sidebar_hr"> 
		
		
		<hr class="sidebar_hr"> 
	</div>
</div>
<!-- end sidebar.inc -->
