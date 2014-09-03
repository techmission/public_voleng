<!-- start JSP information -->
<%//@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<% 
int iTemp4=0;

// taxonomy vocabulary id's (vid)
int vidServiceID=32;

ApplicationCodesBRLO aCodes3 = new ApplicationCodesBRLO( request );
//ArrayList aServiceList3 = new  ArrayList ( 2 );
//aCodes.getAppCodeList( aServiceList, 161 );
//aCodes3.getTaxonomyCodeList( aServiceList3, vidServiceID );




int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteID=iSiteChurchVolTID;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteID=iSiteiVolTID;
}


ArrayList aServiceList3 = new  ArrayList ( 2 );
aCodes3.getTaxonomyCodeListByRelated( aServiceList3, vidService, iSiteID );
ArrayList aServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList aServiceSiteiVolList = new  ArrayList ( 2 );
//aCodes3.getTaxonomyCodeListByRelated( aServiceSiteChrisVolList, vidService, iSiteChrisVolTID );
//aCodes3.getTaxonomyCodeListByRelated( aServiceSiteChurchVolList, vidService, iSiteChurchVolTID );
//aCodes3.getTaxonomyCodeListByRelated( aServiceSiteiVolList, vidService, iSiteiVolTID );

%>


			<form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
			<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
			<input type="hidden" name="method" value="processOppSearchTopIVol">
			<% }else{ %>
			<input type="hidden" name="method" value="processOppSearchAll">
			<% } %>
                        <input type="hidden" name="voltype" value="">
                        <input type="hidden" name="distance" value="City">
			  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
			    <tr>
			      <td width="55" align="right"><strong>Postal Code</strong></td>
			      <td><input name="postalcode" type="text" id="postalcode" style="width:60px;" /></td>
			      </tr>
			    <tr>
			      <td align="right"><strong>Service<br />
			        Area</strong></td>
			      <td colspan="3">

				<SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" > 

				<option value="" selected="selected"></option> 
		               <%
					for(int index=0; index < aServiceList3.size(); index++){
						iTemp4 = 0;
						aszTemp = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList3.get(index);
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
							}else if (iSubType == 4763){
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
								if(iTid == iTemp4 ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>

				</SELECT>
					</td>
			     
			   
			      <td><input type="image" name="imageField" src="/imgs/search_arrow.gif" border="0" />
			      </td>
			    </tr>
			  </table>
			</form>
