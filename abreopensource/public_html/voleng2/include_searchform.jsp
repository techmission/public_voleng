<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->
<html xmlns="http://www.w3.org/1999/xhtml" <% if(bSkipBanner==true){ %>style="overflow: auto;"<% } %>>

<%
int iTempEmbed=0;
int vidServiceEmbed=32, vidPosFreqEmbed=268;

int iSiteChrisVolTIDEmbed = 25133, iSiteChurchVolTIDEmbed = 25135, iSiteiVolTIDEmbed = 25134;
int iSiteIDEmbed=iSiteChrisVolTIDEmbed;

if(aszHostID.equalsIgnoreCase("volengchurch")){
	iSiteIDEmbed=iSiteChurchVolTIDEmbed;
}else if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
	iSiteIDEmbed=iSiteiVolTIDEmbed;
}

ArrayList aServiceListEmbed = new  ArrayList ( 2 );
ArrayList aServiceSiteChrisVolListEmbed = new  ArrayList ( 2 );
ArrayList aServiceSiteChurchVolListEmbed = new  ArrayList ( 2 );
ArrayList aPosFreqListEmbed = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodesEmbed = new ApplicationCodesBRLO( request );
aCodesEmbed.getTaxonomyCodeList( aServiceListEmbed, vidServiceEmbed );
//aCodesEmbed.getTaxonomyCodeListByRelated( aServiceListEmbed, vidServiceEmbed, iSiteIDEmbed );
aCodesEmbed.getTaxonomyCodeListByRelated( aServiceSiteChrisVolListEmbed, vidServiceEmbed, iSiteChrisVolTIDEmbed );
aCodesEmbed.getTaxonomyCodeListByRelated( aServiceSiteChurchVolListEmbed, vidServiceEmbed, iSiteChurchVolTIDEmbed );
aCodesEmbed.getTaxonomyCodeList( aPosFreqListEmbed, vidPosFreqEmbed );

String aszPortalLinkEmbed = aszPortal;
if(aszHostID.equalsIgnoreCase("volengchurch")){
	if(aszPortal.startsWith("/voleng") && aszPortal.length()>7){
		aszPortalLinkEmbed = aszPortal.substring(7,aszPortal.length());
	}	
}

if(aszSubdomain.equalsIgnoreCase("ChurchVolunteering.org")){
	aszSubdomain="www."+aszSubdomain;
}
%>

<img src="http://www.churchvolunteering.org/imgs/logo-churchvolunteering.gif"></img>

<form name="homesearch" method="get" action="http://<%=aszSubdomain%><%=aszPortalLinkEmbed%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<table style="font-size:12px; margin-left: 5px;">
	<tr>
		 <td align="right"><strong>Location </strong></td> 
         <td align="left" colspan="2">
			<select id="hqoroffsite_mychurch_embed" name="hqoroffsite" size="1" class="smalldropdown" style="width: 175px;" onchange="update_serviceareas_embed('serviceareaMyChurch_embed')"> 
				<option value="" selected="selected"></option> 
				<option value="hq">Church Campus</option> 
				<option value="offsite">Local Missions - Off-site</option> 
				<option value="offsite_intl">International Missions</option> 
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">
			<strong>Service Area</strong>
		</td>
		<td colspan="2">
			<SELECT id="serviceareaMyChurch_embed" name="servicearea1" size="1" class="smalldropdown" > 
				<option value="" selected="selected"></option> 
				<%
					for(int index=0; index < aServiceListEmbed.size(); index++){
						iTempEmbed = 0;
						aszTemp = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceListEmbed.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTempEmbed ) out.print(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</td>
	</tr>
	<tr>
		 <td align="right"><strong>Frequency </strong></td> 
         <td align="left" colspan="2">
			<select id="frequency" name="frequency" size="1" class="smalldropdown" style="width: 175px;"> 
				<option value="" selected="selected"></option> 
				<option value="8119">One-time Position</option> 
				<option value="8120">Ongoing Position</option> 
				<option value="25200">Short Term Service/Seasonal</option> 
				<% /*
					for(int index=0; index < aPosFreqList.size(); index++){
						iTempEmbed = 0;
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPosFreqList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTempEmbed ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					} */
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td>
			<input name="imageField" id="submit" type="submit" value="Search" style=" width: 100px; height: 25px; font-weight:bold;"/>
		</td>
		<td align="left"></td>
	</tr>
</table>
</form>
<script type="text/javascript">
function update_serviceareas_embed(newlist){
	var location = document.getElementById('hqoroffsite_mychurch_embed');
	var addressSet = location.options[location.selectedIndex].value;
	if(addressSet=="offsite" || addressSet=="offsite_intl" || addressSet=="noaddress"){
		var servElem = document.getElementById('oppservicelistchrisvol_embed'); 
	}else{// if (addressSet=="hq" || addressSet=="address"){
		var servElem = document.getElementById('oppservicelistchurchvol_embed'); 
	}
	var newElem = document.getElementById(newlist);
	var tmpValue = '';
	var tmpText = '';
	
	newElem.options.length = 0;
	for(var i=0; i<servElem.options.length; i++) {
		tmpValue = servElem.options[i].value;
		tmpText = servElem.options[i].text;

		newElem.options[newElem.options.length] = new Option(tmpText,tmpValue); 
	}
}
</script>

<div id="diffserviceareas_embed" style="display:none;">
		<SELECT id="oppservicelistchrisvol_embed"> 
			<option value=""></option>
			<%
				for(int index=0; index < aServiceSiteChrisVolListEmbed.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceSiteChrisVolListEmbed.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</SELECT>
		<SELECT id="oppservicelistchurchvol_embed"> 
			<option value=""></option>
			<%
				for(int index=0; index < aServiceSiteChurchVolListEmbed.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceSiteChurchVolListEmbed.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</SELECT>
</div>
