   <div align="left">
     <br>
<html:form action="/oppsrch.do" method="get"  >
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<html:hidden property="requiredcodetype" value="No" />
<% } %>


<%
int iTemp=0;

// taxonomy vocabulary id's (vid)
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269;

// non-dropdown taxonomy term id's (tid)

// vidVolInfo=33
int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;

// vidPosType=34
int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797, iBoth=100;

// vidMemberType=42
int iChurch=5244, iChrisNonProfit=5245, iNonProfitNonChris=5246;

// vidWorkStudy=264										&workstudy=8104
int iNoWorkStudy=8103, iYesWorkStudy=8104;

// vidRoomBoard=265										&roomboard=iYesRoomBoard8106
int iNoRoomBoard=8105, iYesRoomBoard=8106;

// vidStipend=266
int iNoStipend=8107, iYesStipend=8108;

// vidPosFreq=268
int iOneTime=8119, iOngoing=8120;

// vidSchedDate=269
int iNoDate=8132, iRecurringDate=8133, iYearDate=8134;


String aszGroupTID = "" + iGroup, aszFamilyTID = "" + iFamily, aszKidTID = "" + iKid, aszTeenTID = "" + iTeen, aszSeniorTID = "" + iSenior;
String aszLocalTID = "" + iLocal, aszVirtualTID = "" + iVirtual, aszShortTermTID = "" + iShortTerm, aszWorkStudyTID = "" + iWorkStudy, 
	aszBothTID = "" + iBoth;
String aszChurchTID = "" + iChurch, aszChrisNonProfitTID = "" + iChrisNonProfit, aszNonProfitNonChrisTID = "" + iNonProfitNonChris;
String aszNoWorkStudyTID = "" + iNoWorkStudy, aszYesWorkStudyTID = "" + iYesWorkStudy;
String aszNoRoomBoardTID = "" + iNoRoomBoard, aszYesRoomBoardTID = "" + iYesRoomBoard;
String aszNoStipendTID = "" + iNoStipend, aszYesStipendTID = "" + iYesStipend;
String aszOneTimeTID = "" + iOneTime, aszOngoingTID = "" + iOngoing;
String aszNoDateTID = "" + iNoDate, aszRecurringDateTID = "" + iRecurringDate, aszYearDateTID = "" + iYearDate;

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList aLanguageList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( aServiceList, vidService );
aCodes.getTaxonomyCodeList( aProgramList, vidProgramType );
aCodes.getTaxonomyCodeList( afiliationList, vidDenomAffil );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getTaxonomyCodeList( apartnersList, vidOrgAffil );
aCodes.getTaxonomyCodeList( askillsList, vidSkill );
aCodes.getTaxonomyCodeList( aLanguageList, vidLangSpoken );
aCodes.getTaxonomyWeightCodeList( aDurationList, vidTripLength );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;
%>

	<TABLE class="outer" cellSpacing=0 cellPadding=2 border=0 width="89%"  >
	<tr>
<% if( (aszHostID.equalsIgnoreCase("voleng1")) ||
 		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengchurch" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
		(aszHostID.equalsIgnoreCase("volengivol")) 
) { %>
	<td width=15%></td>
<% }else{ %>
	<td></td>
<% } %>
	<td>
<span class= "criticaltext">*</span><span style="font-weight: bold"> Please complete the required field below.</span>
	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 width="88%"  >
		<!-- MSTableType="layout" -->
		<tr><td colspan=5>
		<br>
      <fieldset style="width: 400px; height: 75px" >
      <table>
          <tr><TD class=set>
			Select One <span class= "criticaltext">*</span>	</TD>
          <TD valign="middle" colspan="2">
         
			<input type="radio" name="method" checked value="processOppSearchAll" onclick="opp_search()"> Search Opportunities &nbsp;&nbsp;<br>
			<input type="radio" name="method" value="processOrgSearchAll" onclick="org_search()"> Search Organizations<br>
<% if ( (aszHostID.equalsIgnoreCase("voleng1")) ||  (aszHostID.equalsIgnoreCase("default")) 
) { %>
			<input type="radio" name="method" value="allsearch" onclick="all_search()"> Search All of ChristianVolunteering.org
<% } %>
	</TD></tr></table>
      </fieldset><br><br></td>
    <TD></TD>
					</tr>
</table>

<div id="notall">

	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 width="88%"  id="table1" >
			
		<tr>
                <TD valign="top" ><!--width="100"-->
	<div id="servicearealabel">
				Select Service Areas <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
	<div id="programtypelabel" style="display:none;">
				Select Program Type <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
	</div>
				</TD>
                <TD valign="top" colspan="2">
                
                
	<div id="servicearea"><p>
				<SELECT id="servicearea1" name="servicearea1" size="1" class="smalldropdown" onchange="javascript:document.getElementById('serviceareadisplay2').style.display='inline';"> 

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
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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

				</SELECT></p>
				<div id="serviceareadisplay2" style="display:none;"><p>
				<SELECT id="servicearea2" name="servicearea2" class="smalldropdown" onchange="javascript:document.getElementById('serviceareadisplay3').style.display='inline';" > 

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
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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

				</SELECT></p></div>
                <div id="serviceareadisplay3" style="display:none;"><p>
				<SELECT id="servicearea3" name="servicearea3" class="smalldropdown" > 

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
							}else if (iSubType == 4767){
								out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								if(iTid == iTemp) out.print(" selected=selected ");
								out.println(" >Disabilities Outreach</option> ");
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

				</SELECT></p></div>
	</div>
	<div id="programtype1" style="display:none;">
				<SELECT id="programtypetid" name="programtypetid" size="1" class="smalldropdown" > 

				<option value="" selected="selected"></option> 
				<%
				for(int index=0; index < aProgramList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
					if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if(aszSecondaryHost.equalsIgnoreCase("volengivol")) {
							if (iSubType == 5239){
							}else{
								out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
						}else{
							out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
				}
				%>

				</SELECT> 
	</div>
                </TD>
			<td></td>
			<td height="28">&nbsp;</td>
			</tr>


		<tr>
        <TD>
		<div id="regionlabel">Region</div></TD>
        <TD colspan="2">
        	<div id="regionsection"><SELECT id="region" name="region" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
				<%
				for(int index=0; index < aRegionList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
					if(null == aAppCode) continue;
					out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
				%>
              </SELECT></div>
		</TD>
			<td height="26">&nbsp;</td>
		</tr>


		<tr>
        <TD>
		<div id="countrylabel">Country</div></TD>
        <TD colspan="2">
        	<div id="countrysection"><SELECT id="country" name="country" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
					<%
					aszTemp= "US";
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
					//out.print(" selected=selected ");
					out.print( "onClick=\"javascript:document.getElementById('postallabel').style.display='inline';javascript:document.getElementById('postal').style.display='inline';javascript:document.getElementById('distlabel').style.display='inline';javascript:document.getElementById('dist').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabel').style.display='none';javascript:document.getElementById('postal').style.display='none';javascript:document.getElementById('distlabel').style.display='none';javascript:document.getElementById('dist').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
              </SELECT></div>
        </TD>
			<td height="26">&nbsp;</td>
		</tr>
<tr><td > <!--width="110"-->
			<div id="postallabel">Postal Code</div></TD>
          <TD colspan="2">
				<div id="postal"><html:text property="postalcode" styleId="postalcode" size="5" maxlength="5" styleClass="textinputwhite"/></div></TD>
					<td></td>
      		      		
					<td height="25">&nbsp;</td>
                	</tr>
		<tr>
          <TD><div id="distlabel">
			Distance</div></TD>
          <TD colspan="2"><div id="dist">
          		<SELECT id="distance" name="distance" class="smalldropdown" > 
        <option value="5">5 miles (8K)</option>

        <option value="20">20 miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
               </SELECT></div>
               
         </TD>
                	<td></td>
					<td height="25">&nbsp;</td>
                	</td></tr>

		<tr>
        <TD width="100">
        <div id="grouplabel">
		I am a</div></TD>
        <TD colspan="2">
        <div id="group">
            <html:checkbox styleClass="check" value="<%=aszGroupTID%>" property="greatfor4" onclick="javascript:document.getElementById('group').style.display='inline';"/>
            &nbsp;Group 
            <html:checkbox styleClass="check" value="<%=aszFamilyTID%>" property="greatfor5"/>
        	&nbsp;Family
            <html:checkbox styleClass="check" value="<%=aszKidTID%>" property="greatfor1"/>
        	&nbsp;Kid
            <html:checkbox styleClass="check" value="<%=aszTeenTID%>" property="greatfor3"/>
            &nbsp;Teen 
            <html:checkbox styleClass="check" value="<%=aszSeniorTID%>" property="greatfor2"/>
            &nbsp;Senior 
        </div>
        </TD>
			<td></td>
			<td height="29">&nbsp;</td>
			</tr>


		<tr>
			<TD colspan=3 ><HR></TD>
			<td></td>
			<td height="25">&nbsp;</td>

		<tr>
          <TD colspan=5>
<div id="opptype" >

	<table >
		<tr>
          <TD class=set>
			Choose One <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
			</TD>
          <TD valign="middle" colspan="2">
			<dl>
                                <dt><html:radio styleClass="radio" value="<%=aszLocalTID%>" property="postype" onclick="clicked_local()" /> Local Volunteering (in
                                person)</dt>
                                <dt><html:radio styleClass="radio" value="<%=aszVirtualTID%>" property="postype" onclick="clicked_virtual()" />
                                Virtual Volunteering (location flexible)</dt>
<% //if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
                                <dt><html:radio styleClass="radio" value="<%=aszShortTermTID%>" property="postype" onclick="clicked_missions()" /> Short-term <% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>Missions <% } %>/ Volunteer Internship</dt>
<% //} %>                                
                                <div id="missions" style="display:none; ">
<table><tr>
<td width=25></td><td>
<html:checkbox styleClass="radio" value="<%=aszYesStipendTID%>" property="stipend" />Search only for internships that provide a Stipend 
</td></tr><tr>
<td></td><td>
<html:checkbox styleClass="radio" value="<%=aszYesRoomBoardTID%>" property="roomboard" />Search only for internships that provide Room & Board
</td></tr></table>
</div>
                                <dt><html:radio styleClass="radio" value="" property="postype" onclick="clicked_all()" /> All Volunteer Positions</dt>
                                <br/>
          						<dt><html:checkbox styleClass="radio" value="<%=aszYesWorkStudyTID%>" property="workstudy" />Search for Work Study Volunteer Opportunities</dt></dl>
  </td>
  </tr>
	</table>
</div>	
</td></tr>				
		<tr>
          <TD colspan=4>
      <div id="missions-duration" style="display:none">
      <table cellspacing=0 cellpadding=2 >

		<tr>
          <TD >
			Duration</TD>
          <TD colspan="2">
<select id="duration" name="duration" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
        	<option value=""></option>
        	<%
			aszTemp="";
			for(int index=0; index < aDurationList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			%>
		</select>
				</td>
      		      		
					<td height="29">&nbsp;</td>
                	</tr>
<tr>
			<td height="29">Minimum Group Size</td>
			<td><html:text property="mingroup" size="5" maxlength="5" styleClass="textinputwhite"/></td>
			<td>&nbsp;&nbsp;Maximum Group Size</td>
			<td><html:text property="maxgroup" size="5" maxlength="5" styleClass="textinputwhite"/></td>
			
  		     		
					
                	</tr>
        </table>
        </div>
        </td></tr>
					
		<tr>
          <TD colspan=4>
          <div id="virtualaddr">
          <table cellspacing=0 cellpadding=0>
          
          <!--postal code and distance -->                	
 <tr>
			<td height="15">&nbsp;</td>
</tr>
			
		<tr><td colspan=5>	
      <fieldset style="width: 400px; height: 75px" align="center">
			<table>
		<tr>
        <TD>
		City</TD>
        <TD colspan="2"><html:text property="city" styleId="city" size="15" maxlength="50" styleClass="textinputwhite"/></TD>
			<td></td>
			<td height="30">&nbsp;</td>
			</tr>
		<tr>
        <TD>
		State</TD>
        <TD >
        	<SELECT id="state" name="state" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
				<%
				for(int index=0; index < aStateList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
					if(null == aAppCode) continue;
					out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
					out.println(" > " + aAppCode.getCSPStateName() + " </option> ");
				}
				%>
              </SELECT></td><td><!--Other html:text property="prov" size="10" styleClass="textinputwhite"/-->
              
        </TD>
			<td></td>
			<td height="37">&nbsp;</td>
		</tr>


<!-- country and region -->

		</table></fieldset>
		</td></tr>
		
<tr>
			<td height="15">&nbsp;</td>
</tr>
                	</table></div></td>
			</tr>
			

<!-- service area and program types -->
		<tr>
			<TD colspan=3 ><HR></TD>
			<td height="25">&nbsp;</td>
			</tr>
		<tr>
	        <TD>
			Organization<br>Name</TD>
	        <TD colspan="2">
	            <html:text property="orgname" styleId="orgname" size="30" maxlength="200" styleClass="textinputwhite"/>
	        </TD>
			<td height="26">&nbsp;</td>
		</tr>

		<tr>
        <TD valign="top">
		<div id="skillsgrouplabel">Skills Needed</div></TD>
        <TD TD valign="top" colspan="2">
	<div id="skillsgroup"><p>
        <select id="skills1id" name="skills1id" class="smalldropdown" onchange="javascript:document.getElementById('skill2').style.display='inline';">
	<option value="" selected></option>
			<%
			if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (iSubType == 4748 ||
							iSubType == 4749){
						}else if (iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
			}else{
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			}
			%>

				</SELECT></p>
				<div id="skill2" style="display:none;"><p>
        <select id="skills2id" name="skills2id" class="smalldropdown" onchange="javascript:document.getElementById('skill3').style.display='inline';">
	<option value="" selected></option>
			<%
			if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (iSubType == 4748 ||
							iSubType == 4749){
						}else if (iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
			}else{
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			}
			%>

				</SELECT></p></div>
                <div id="skill3" style="display:none;"><p>
        <select id="skills3id" name="skills3id" class="smalldropdown" >
	<option value="" selected></option>
			<%
			if (aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (iSubType == 4748 ||
							iSubType == 4749){
						}else if (iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
			}else{
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
			}
			%>

				</SELECT></p></div>
	</div>
</TD>
       </TR>

<!-- kid, group, seniors,...-->
<!-- group size -->					

</td></tr>

<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

		<tr>
          <TD class=set valign="top">
			Denominational<br>Affiliation</TD>
          <TD valign="top" colspan="2">
          <select id="denomaffiltid" name="denomaffiltid" class="smalldropdown" >
	<option value="" selected></option>
			<%
			if(aszHostID.equalsIgnoreCase("volengcatholic")){
				aszTemp="Catholic";
				iTemp=992;			
			} else {
				aszTemp="";
				iTemp=0;
			}
			for(int index=0; index < afiliationList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid==iTemp) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");

			}
			%>

		    </select>
		    </TD>
			<td height="26">&nbsp;</td>
			</tr>
		<tr>
          <TD>
			Affiliations</TD>
              <TD valign="top">
			<SELECT id="orgaffil1tid" name="orgaffil1tid" class="smalldropdown" style="margin-top: 5px;" 
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  			
			){ %>
				onchange="javascript:document.getElementById('affiliation2').style.display='inline';"
			<% } %>
			> 
			<option value=""></option>
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
			if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
				aszTemp="Salvation Army";
				iTemp=1069;			
			} else if(aszHostID.equalsIgnoreCase("volengagrm")){
				aszTemp="Association of Gospel Rescue Missions (AGRM)";			
				iTemp=717;			
			} else {
				aszTemp="";
				iTemp=0;
			}
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			
			}
			%>
		</SELECT>
		</td>
	</tr>
	<tr><td></td>
	<td>
	</td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation2" 
			<% // hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
					(aszHostID.equalsIgnoreCase("volengagrm")==false)  			
			){ %>
				style="display: none;"
			<% } %>
			> 

			<SELECT id="orgaffil2tid" name="orgaffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation3').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation3" style="display: none;">

			<SELECT id="orgaffil3tid" name="orgaffil3tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation4').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation4" style="display: none;">

			<SELECT id="orgaffil4tid" name="orgaffil4tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation5').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation5" style="display: none;">

			<SELECT id="orgaffil5tid" name="orgaffil5tid" class="smalldropdown" style="margin-top: 5px;"  > 
			<option value=""></option>
			<%
			aszTemp = "";
			iTemp=0;
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				int iTid = aAppCode.getAPCIdSubType();
				out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iTid == iTemp ) out.print(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr><td colspan=4>
<div style="<%=aszLocalAffil%>" >
	<table border="0" cellpadding="0" cellspacing="0" >
					
	<tr><TD width=110  height="30">
	Local Affiliation:</td><td>
			<select id="localaffil" name="localaffil" class="smalldropdown">
			<option value=""></option>

					<%
					aszTemp = "";
					for(int index=0; index < aLocalAffilList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLocalAffilList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
					%>
				</select></td><td height=45></td></tr>
</table></div>				
	</td></tr>

<% } //end section of not showing affiliations for non-faith versions of the site %>
	
<% if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
<tr><td height=29></td></tr>
<% } %>
	
	
	<tr><td>Sort results by: </td><td>
	<div id="sortstm" style="display:inline;">
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
        <option value="stmdur">Duration (Short-term Missions Only)</option>
        <option value="stmcost">Cost (Short-term Missions Only)</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity  (Annually)</option>
    </SELECT>
    </div>
	<div id="sortopps" style="display:none;">
	<SELECT id="searchkey2" name="searchkey2" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="opportunity">Opportunity Title</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
        <option value="oppnumvol">Number of Volunteers / Opportunity  (Annually)</option>
    </SELECT>
    </div>
	<div id="sortorgs" style="display:none;">
	<SELECT id="searchkey3" name="searchkey3" class="smalldropdown" > 
        <option value=""></option>
		<option value="distance">Distance</option>
        <option value="organization">Organization Name</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
<% } %>
		<option value="updatedt">Date Last Updated</option>
        <option value="numvolorg">Number of Volunteers / Organization (Annually)</option>
    </SELECT>
    </div>
</td></tr>
<table>
		<tr>
			<td width="105">&nbsp;</td>
          <TD colspan="2">
          
	<input type="submit" name="Submit" value="Submit">
</TD>
			<td width="4"></td>
			<td height="45" width="28">&nbsp;</td>
		</tr>
      </table>
</td></tr></table>
</div>
</html:form>



<div id="googleSearchAll" style="display:none">
	<TABLE class="outer" cellSpacing=0 cellPadding=2 border=0 width="89%"  >
	<tr>
<% if( (aszHostID.equalsIgnoreCase("voleng1")) ||
 		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) ||
		(aszHostID.equalsIgnoreCase("volengivol")) 
){%>
	<td width=15%></td>
<% }else{ %>
	<td></td>
<% } %>
	<td>
<span style="font-weight: bold"> Search All of ChristianVolunteering.org:</span>
<br>
<br>
<!-- Google CSE Search Box Begins -->
  <form id="searchbox_017405804136166701815:w_nbsie_qt4" action="http://www.christianvolunteering.org/googleresults.jsp">
    <input type="hidden" name="cx" value="017405804136166701815:w_nbsie_qt4" />
    <input type="hidden" name="cof" value="FORID:11" />
    <input name="q" type="text" size="40" />
    <input type="submit" name="sa" value="Search" />
    <!--img src="http://google.com/coop/images/google_custom_search_smnarg.gif" alt="Google Custom Search" /-->
  </form>
<!-- Google CSE Search Box Ends -->

	</TD>
</tr></td></table>
</div>







