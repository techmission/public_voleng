<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% 
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getAppCodeList( aServiceList, 161 );
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">organization search</span>
	<%//@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
		<span style="float: left;">organization search</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">volunteer</a> &gt; opportunities search results  </div>
	</div>
<% } %>


<div id="body">
 	<center><h3>  
 	  <br>
 	  <fieldset style="width: 508px; height: 275px">
 	  <br><br>
      <i>We're sorry, there are no results for your search.</i>
      <br><br><hr width=75%><br>
      Please try another search below:
      <br><br>

			<form id="homesearch" NAME="homesearch" method="post" action="<%=aszPortal%>/oppsrch.do">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
<input type="hidden" name="requiredcodetype" value="No">
<% } %>
			<input type="hidden" name="method" value="processOrgSearchAll">
			  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
			    <tr>
			      <td width="55" align="right"><strong>Postal Code</strong></td>
			      <td><input name="postalcode" type="text" id="postalcode" style="width:60px;" /></td>
			      <td><strong>Distance</strong></td>
			      <td><select id="distance" name="distance" style="width:80px; z-index: -1">
        <option value="5">5 miles (8K)</option>

        <option value="20">20 miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
			      </select></td>
			    </tr>
			    
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><input type="image" name="imageField" src="http://www.christianvolunteering.org/imgs/button_search_clr.gif" border="0" width="59" height="24" />
			      </td>
			    </tr>
			  </table>
			</form>
     
      
      
      
      
      
      <br>
      </fieldset><br><br>
    </h3>
    </center>
</div>

</div>

<!-- start sidebar information -->
		</form>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
