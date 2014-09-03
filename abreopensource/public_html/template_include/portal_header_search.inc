<%
int iTemp2=0;
String aszTemp20="";

// taxonomy vocabulary id's (vid)
int vidService2=32;

// taxonomy vocabulary id's (vid)
int vid2Service=32,vid2PosFreq=268;

// vidPosFreq=268
int i2OneTime=8119, i2Ongoing=8120;

String asz2OneTimeTID = "" + i2OneTime, asz2OngoingTID = "" + i2Ongoing;

int iSiteChrisVolTID = 25133, iSiteChurchVolTID = 25135, iSiteiVolTID = 25134;
int iSiteID=iSiteChrisVolTID;


ApplicationCodesBRLO a2Codes = new ApplicationCodesBRLO( request );

ArrayList a2ServiceList = new  ArrayList ( 2 );
//a2Codes.getTaxonomyCodeListByRelated( a2ServiceList, vidService2, iSiteID );
a2Codes.getTaxonomyCodeList( a2ServiceList, vidService2 );
ArrayList a2ServiceSiteChrisVolList = new  ArrayList ( 2 );
ArrayList a2ServiceSiteChurchVolList = new  ArrayList ( 2 );
ArrayList a2ServiceSiteiVolList = new  ArrayList ( 2 );
a2Codes.getTaxonomyCodeListByRelated( a2ServiceSiteChrisVolList, vidService2, iSiteChrisVolTID );

ArrayList a2PosFreqList = new  ArrayList ( 2 );
a2Codes.getTaxonomyCodeList( a2PosFreqList, vid2PosFreq );

%>

<script language="javascript">
function update_serviceareas(newlist){
	var addressSet = $('#hqoroffsite_portalheader option:selected').val();
	if(addressSet=="offsite" || addressSet=="offsite_intl" || addressSet=="noaddress"){
		var servElem = document.getElementById('oppservicelistchrisvol_portalheader'); 
	}else{// if (addressSet=="hq" || addressSet=="address"){
		var servElem = document.getElementById('oppservicelistchurchvol_portalheader'); 
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


<div class="portalsearch">

<form name="portalSearchForm" id="portalSearchForm" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<!--input type="hidden" name="portal" value="<%=aszPortal%>"-->
<table border="0" style="font-size:12px;" cellpadding="0" cellspacing="0">

        <tr>
        <!-- onsite/offsite -->
                 <td align="right"><strong>Location&nbsp; </strong></td>
         <td align="left">
                        <select id="hqoroffsite_portalheader" name="hqoroffsite" size="1" class="smalldropdown" style="width: 175px;"
                                onchange="update_serviceareas('servicearea_portalheader')">
                                <option value="" selected="selected"></option>
                                <option value="hq">Church Campus</option>
                                <option value="offsite">Local Missions - Off-site</option>
                                <option value="offsite_intl">Global Missions</option>
                        </select>
                        <!--
                                <input type="radio" name="hqoroffsite" VALUE="hq" id="hqoroffsite_hq" > Church Campus </INPUT>    &nbsp;&nbsp;
                                <input type="radio" name="hqoroffsite" VALUE="offsite" id="hqoroffsite_offsite" > Off-site</INPUT>
                        -->
                </td>


        </tr>

	<tr>
		<td align="right">
			<strong>Service Area&nbsp; </strong>
		</td>
		<td>
			<SELECT id="servicearea_portalheader" name="servicearea1" size="1" class="smalldropdown" style="width: 175px;"> 
				<option value="" selected="selected"></option> 
				<%
					for(int index2=0; index2 < a2ServiceList.size(); index2++){
						iTemp2 = 0;
						aszTemp20 = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)a2ServiceList.get(index2);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp2 ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</td>


                <td colspan="2">
                        &nbsp;&nbsp;<input name="imageField" id="submit" type="submit" value="Search" style=" width: 55px; height: 25px; font-weight:bold;"/>
                </td>


	</tr>


	<tr>
	<!-- search Frequency -->
		 <td align="right"><strong>Frequency&nbsp; </strong></td> 
         <td align="left">
			<select id="frequency_portalheader" name="frequency" size="1" class="smalldropdown" style="width: 175px;"> 
				<option value="" selected="selected"></option> 
				<%
					for(int index2=0; index2 < a2PosFreqList.size(); index2++){
						iTemp2 = 0;
						aszTemp20 = "";
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)a2PosFreqList.get(index2);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp2 ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
			<!--
    	        <input type="radio" styleClass="radio" value="<%=""+i2OneTime%>" name="frequency" id="frequencyAdvOneTime"/>
        		&nbsp;One-time
            	<input type="radio" styleClass="radio" value="<%=""+i2Ongoing%>" name="frequency" id="frequencyAdvOngoing"/>
	        	&nbsp;Ongoing
	        -->
		</td>
	</tr>


</table>
</form>
</div>
<div id="diffserviceareas_portalheader" style="display:none;">
		<SELECT id="oppservicelistchrisvol_portalheader"> 
			<option value=""></option>
			<%
				for(int index=0; index < a2ServiceSiteChrisVolList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)a2ServiceSiteChrisVolList.get(index);
					if(null == aAppCode) continue;
					int iTid = aAppCode.getAPCIdSubType();
					String aszDisplay = aAppCode.getAPCDisplay();
					int iSubType = aAppCode.getAPCIdSubType();
					out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
			%>
		</SELECT>
		<SELECT id="oppservicelistchurchvol_portalheader"> 
			<option value=""></option>
			<%
				for(int index=0; index < a2ServiceSiteChurchVolList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)a2ServiceSiteChurchVolList.get(index);
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

<!-- end Portal Header search-->
