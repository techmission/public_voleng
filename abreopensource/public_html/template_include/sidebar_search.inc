 <h5><a href="<%=aszPortal%>/advancedsearch.jsp">Search Opportunities</a> </h5>
<form name="searchForm3" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="requesttype" value="urlalias">
<input type="hidden" name="method" value="processOppSearchTop">
<input type="hidden" value="feeds" name="source" id="source"/>
<input style="width:55px;" type="text" value="zip code" name="postalcode" maxlength="5" onFocus="clearText(this)" />
 <input name="submit" type="submit" id="submit" value="GO"/> 

</form>
