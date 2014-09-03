<%
boolean bAssoc=false;
if(aszPortalType.equals("natlassoc")) bAssoc=true;
int iTemp=0;
%>

<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" )) ){ %>
	<div style="float:left; margin: 10px 0px 5px 10px; width: 285px;">
<% } else { %>
	<div style="float:left; margin: 10px 0px 5px 10px; width: 300px;">
<% } %>

<% if ( (aszHostID.equalsIgnoreCase( "volengurbmin" )) ){ %>
<p>
<A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch">
<img src="/imgs/welcomebox_title..gif" alt="search christian volunteer opportunities" width="248" height="21" hspace="0" vspace="0" border="0"/></A></p>
<% } else { %>

<table><tr>
	  <% if (
                  (aszHostID.equalsIgnoreCase( "volengcityreaching" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengmissionamerica" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengroundtrip" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengsaddleback" ) ) 
	  ) {%>
<td width = 230>
<h2><A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch">volunteer search</A></h2>

<% }else{ %>
<td>
<A HREF="<%=aszPortal%>/oppsrch.do?method=showSearch">
<img src="/imgs/welcomebox_title..gif" alt="search Christian volunteer church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" width="248" height="21" hspace="0" vspace="0" border="0"/></A>
<% } %>
</td>
<td valign="top">
<b><a href="<%=aszPortal%>/login.jsp" title="Login to your account">login</a></b></td></tr></table>


<% } %>
<form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="voltype" value="">
<input type="hidden" name="requesttype" value="urlalias">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="partner" value="Salvation Army">
<% } %>
  <table 
  
  <% if(
  	!(aszNarrowTheme=="true") 
){ %>
width="400"
<% } else if(aszHostID.equalsIgnoreCase("volengesa")) { %>
width="200"
<% } else { %>
width="300"
<% } %>

border="0" cellpadding="0" cellspacing="5" style="font-size: 12px; z-index: -1;">
    <tr>
      <td width="75" align="right"><strong>Postal Code</strong></td>
      <td>
      <input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></td>
      <td><strong>Distance</strong></td>
      <td><select id="distance" name="distance" style="width:108px; z-index: -1;">

        <option value="5">5 Miles (8K)</option>

        <option value="20">20 Miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
      </select></td>
    </tr>
    
    <tr>
      <td align="right"><strong>Service Area</strong></td>
      <td colspan="3">
				<SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" > 
				<option value="" selected="selected"></option> 
               <%
					for(int index=0; index < aServiceList.size(); index++){
						iTemp = 0;
						aszTemp = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							if (iSubType == 4760 ||
								iSubType == 4764 ||
								iSubType == 4772 ||
								iSubType == 4773 ||
								iSubType == 4783 ||
								iSubType == 4787 ||
								iSubType == 4789 ||
								iSubType == 7341 ||
								iSubType == 7342 ){
							}else if (iSubType == 4788){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Visitation/Friendship</option> ");
							}else if (iSubType == 4763){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Children and Youth</option> ");
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
							}else if (iSubType == 6843){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Senior / Elderly Outreach</option> ");
							}else if (iSubType == 4774){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Food Service / Hunger</option> ");
							}else if (iSubType == 4782){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Prison Outreach</option> ");
							}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}else{
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>

				</SELECT>

		</td>
      </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3">
	  <% if (
                  (aszHostID.equalsIgnoreCase( "volengcityreaching" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengmissionamerica" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengroundtrip" ) )  ||
		  (aszHostID.equalsIgnoreCase( "volengsaddleback" ) ) 
	  ) {%>
	  <input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search_beige.gif" alt="Search" width="59" height="24" border="0" />
	  <% }else{ %>
	  <input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search.gif" alt="Search" width="59" height="24" border="0" />
      <% } %>
	  </td>
    </tr>
  </table>
</form>


<b><a href="<%=aszPortal%>/advancedsearch.jsp" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>
<h2><a href="<%=aszPortal%>/shorttermmissions.jsp">search short term missions</a></h2>
</div>
<div class="cleardiv"></div>
