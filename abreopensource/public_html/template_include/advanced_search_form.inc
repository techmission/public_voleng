
<% if(aszPath.equalsIgnoreCase("/voleng2/fullsearch.jsp")){}else{ %>
<script type="text/javascript"> 

function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 

document.onkeypress = stopRKey; 

</script>
<% } %>

<form name="searchForm" action="<%=aszPortal%>/oppsrch.do" method="get"  >
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
<input type="hidden" name="requiredcodetype" value="No" />
<% } %>
<% if(aszSubmit.equals("display: none;")){ %>
<input type="hidden" name="requesttype" id="requesttype" value="myResultsAdminSelect">
<% } %>
<input type="hidden" value="advsrchAdv" name="formname" id="formname"/>


  <div class="left-column" style="font-weight:bold; //height:41px;">
		Select One
	</div>
	<div class="right-column">
		<input type="radio" name="method" id="methodAdvOpp" checked value="processOppSearchAll" onclick="opp_search()">
		&nbsp;Search Opportunities &nbsp;&nbsp;<br>
		<input type="radio" name="method" id="methodAdvOrg" value="processOrgSearchAll" onclick="orgadv_search()">
		&nbsp;Search Organizations<br>
<% if ( 	(aszHostID.equalsIgnoreCase("voleng1"))	||  
			(aszHostID.equalsIgnoreCase("volengexample"))	||  
			(aszHostID.equalsIgnoreCase("default")) 
) { %>
		<input type="radio" name="method" id="methodAdvAll" value="allsearch" onclick="all_search()">
		&nbsp;Search All of ChristianVolunteering.org
<% } %>
	</div>
		<div class="cleardiv"></div>	
  
  
  <div id="posTypeAdv" style="clear:both;">
  <br>
		
		<div class="left-column" style="font-weight:bold; //height:45px;">
		Position Type
		</div>
		<div class="right-column">
			<input type="radio" styleClass="radio" value="<%=aszLocalTID%>" name="postype" id="postypeAdvLocal" onclick="clicked_local()" /> Local Volunteering (in person) 
			
			<input type="radio" styleClass="radio" value="<%=aszVirtualTID%>" name="postype" id="postypeAdvVirtual" onclick="clicked_virtual()" /> Virtual Volunteering (location flexible)
			<br>
			<input type="radio" styleClass="radio" value="<%=aszShortTermTID%>" name="postype" id="postypeAdvSTM" onclick="clicked_missions()" /> Short-term <% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>Missions <% } %>/ Volunteer Internship
      
      <input type="radio" styleClass="radio" value="" name="postype" id="postypeAdvAll" onclick="clicked_all()" /> All Volunteer Positions
 
			</div>
			<div class="cleardiv"></div> <br>
			
      
			<div id="missionsAdv" style="display:none">
		<div class="form-item">
    <div class="left-column" style="//height:85px;">
			<label>Benefits Desired </label>
      	</div>
		<div class="right-column">
			<div class="form-checkboxes form-checkboxes-scroll">
				<div id="edit-taxonomy-286-11546-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11546">
						<input id="roomboardAdv" name="roomboard" type="checkbox" value="<%=aszRoomBoardTID%>" />
						Room & Board &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11547-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11547">
						<input id="stipendAdv" name="stipend" type="checkbox" value="<%=aszStipendTID%>" />
						Stipend &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11548-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11548">
						<input id="medinsurAdv" name="medinsur" type="checkbox" value="<%=aszMedInsurTID%>" />
						Medical Insurance &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11549-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11549">
						<input id="transportAdv" name="transport" type="checkbox" value="<%=aszTransportTID%>" />
						Transportation &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
				</div>
				<div id="edit-taxonomy-286-11550-wrapper" class="form-item">
					<label class="option" for="edit-taxonomy-286-11550">
						<input id="americorpsAdv" name="americorps" type="checkbox" value="<%=aszAmeriCorpsTID%>" />
						AmeriCorps &nbsp;&nbsp;&nbsp;&nbsp;
					</label>
			</div>
		</div>
	</div>           
			</div><!-- end missionsAdv -->
			
			
		</div>
		<div class="cleardiv"></div>			
	</div><!-- end posTypeAdv -->
       
	<div id="missionsDurationAdv" style="display:none;">
		<div class="left-column">
			Duration<!-- (length of trip taxonomy) -->
		</div>
		<div class="right-column">
			<SELECT id="durationAdv" name="duration" class="smalldropdown" class="smalldropdown" style="margin-top: 5px;" >
				<option value=""></option>
				<%
					aszTemp="";
					for(int index=0; index < aDurationList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aDurationList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</SELECT>
		</div>
	<div class="cleardiv"></div>
  </div><!-- end missionsDurationAdv -->
  
       
	<div id="groupAdv" style="display:none;">
		<div class="left-column">
			Minimum Group Size
		</div>
		<div class="right-column">
			<input type="text" name="mingroup" id="mingroupAdv" size="5" maxlength="5" styleClass="textinputwhite"/>
		</div>
    <div class="cleardiv"></div>
    
		<div class="left-column">  
			Maximum Group Size
		</div>
		<div class="right-column">
			<input type="text" name="maxgroup" id="maxgroupAdv" size="5" maxlength="5" styleClass="textinputwhite"/>
		</div>   
	<div class="cleardiv"></div>
	</div><!-- end groupAdv --> 
		
    
    
    
		
  <div id="notall" style="clear:both;">

  <div  id="serviceAreaSectionRow1" style="//padding:3px 0;">
  <div class="left-column">
		Service Areas <a href="<%=request.getContextPath()%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
	</div>
	<div class="right-column">
		<SELECT id="servicearea1Adv" name="servicearea1" size="1" class="smalldropdown" onchange="javascript:document.getElementById('serviceAreaSectionRow2').style.display='inline';"> 
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
					if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" selected=selected ");
						out.println(" >Visitation/Friendship</option> ");
					}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" selected=selected ");
						out.println(" >Children and Youth</option> ");
					}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" selected=selected ");
						out.println(" >Disabilities Outreach</option> ");
					}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" selected=selected ");
						out.println(" >Food Service / Hunger</option> ");
					}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp) out.print(" selected=selected ");
						out.println(" >Prison Outreach</option> ");
					}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
			%>
		</SELECT>
	</div>
	<div class="cleardiv"></div>
	
	<div id="serviceAreaSectionRow2" style="display:none;">	
		<div class="left-column">
		</div>
		<div class="right-column">

			<SELECT id="servicearea2Adv" name="servicearea2" class="smalldropdown" onchange="javascript:document.getElementById('serviceAreaSectionRow3').style.display='inline';" > 
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Disabilities Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
					%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>		
	</div><!-- end serviceAreaSectionRow2 -->
	
	<div id="serviceAreaSectionRow3" style="display:none;">	
		<div class="left-column">
		</div>
		<div class="right-column">
			<SELECT id="servicearea3Adv" name="servicearea3" class="smalldropdown" > 
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
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4788){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Visitation/Friendship</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4763){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Children and Youth</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4767){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Disabilities Outreach</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4774){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4782){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp) out.print(" selected=selected ");
							out.println(" >Prison Outreach</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end serviceAreaSectionRow3 -->
     </div>
 <!-- end div  id="serviceAreaSectionRow1"-->
 
  
  <div id="skillSectionRowAdv1" style="//padding:3px 0;">
		<div class="left-column">
			Skills Needed
		 </div>
		 <div class="right-column">
		 	<SELECT id="skills1idAdv" name="skills1id" class="smalldropdown" onchange="javascript:document.getElementById('skillSectionRowAdv2').style.display='inline';">
				<option value="" selected></option>
				<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && (
							iSubType == 4748 ||
							iSubType == 4749)){
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
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	
	<div id="skillSectionRowAdv2" style="display:none">
		<div class="left-column">
		</div>
		<div class="right-column">
			<SELECT id="skills2idAdv" name="skills2id" class="smalldropdown" onchange="javascript:document.getElementById('skillSectionRowAdv3').style.display='inline';">
				<option value="" selected></option>
				<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && (
							iSubType == 4748 ||
							iSubType == 4749)){
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end skillSectionRowAdv2 -->
	
	<div id="skillSectionRowAdv3" style="display:none">
		<div class="left-column">
		</div>
		<div class="right-column">
			<SELECT id="skills3idAdv" name="skills3id" class="smalldropdown" >
				<option value="" selected></option>
				<%
					for(int index=0; index < askillsList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						if (aszSecondaryHost.equalsIgnoreCase("volengivol") && (
							iSubType == 4748 ||
							iSubType == 4749)){
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 4745){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Musician</option> ");
						}else if (aszSecondaryHost.equalsIgnoreCase("volengivol") && iSubType == 8122){
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >Deaf Services</option> ");
						}else{
							out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
							if(iTid == iTemp ) out.print(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end skillSectionRowAdv3 -->
 	</div><!-- end skillSectionRowAdv1 -->
 
  
  

  <div id="postalcountryAdv" style="//padding:3px 0;">
	<div class="left-column">
		Postal Code &amp; Country
	</div>
	<div class="right-column">
		<div style="float:left; margin-right:5px;" id="postalsectionAdv">
			<input type="text" name="postalcode" id="postalcodeAdv" styleId="postalcode" size="5" maxlength="5" styleClass="textinputwhite"/>
		</div>
		<SELECT id="countryAdv" name="country" class="smalldropdown" style="z-index: 0; width:220px; float:left;" onchange="selected_disabled(this, 'postalcodeAdv')">
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
							out.print(" selected=selected ");
							out.print( "onClick=\"javascript:document.getElementById('postalsectionAdv').style.display='inline';javascript:document.getElementById('dist').style.display='inline';\" ");
						} else {
							out.print( "onClick=\"javascript:document.getElementById('postalsectionAdv').style.display='none';javascript:document.getElementById('dist').style.display='none';\" ");
						}
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
			%>
		</SELECT>
	</div>
    <div class="cleardiv"></div>
  </div><!-- end div  id="postalcountryAdv"-->
  
  
  <div id="address2Adv" style="//padding:3px 0;">
		<div class="left-column">
			City
		</div>
		<div class="right-column">
			<input type="text" name="city" id="cityAdv" styleId="city" size="15" maxlength="50" styleClass"textinputwhite"/>
		
			&nbsp;&nbsp;&nbsp;State
			<SELECT id="stateAdv" name="state" class="smalldropdown"> 
				<OPTION value=""></OPTION> 
				<%
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
						out.println(" > " + aAppCode.getCSPStateName() + " </option> ");
					}
				%>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end address2Adv -->

	
  
      
	<div id="programtype1Adv" style="display:none;" style="//padding:3px 0;">
		<div class="left-column">
			Select Program Type <a href="<%=request.getContextPath()%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</a>
		</div>
		<div class="right-column">
			<SELECT id="programtypetidAdv" name="programtypetid" size="1" class="smalldropdown" > 
				<option value="" selected="selected"></option> 
				<%
					for(int index=0; index < aProgramList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
						if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if(aszSecondaryHost.equalsIgnoreCase("volengivol")) {
								if (iSubType == 5239 || iSubType == 25188){
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
		<div class="cleardiv"></div>
	</div><!-- end programtype1Adv -->
      
	<div id="regionsectionAdv" style="//margin:3px 0 3px 0;">
		<div class="left-column">
			Region
		</div>
		<div class="right-column">
        	<SELECT id="regiontidAdv" name="regiontid" class="smalldropdown"> 
	       		<OPTION value=""></OPTION> 
				<%
					for(int index=0; index < aRegionList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
					%>
			</SELECT>
        </div>
        <div class="cleardiv"></div>
	</div><!-- end regionsectionAdv -->
			
	<div id="dist" style="//padding:3px 0;">	
		<div class="left-column">
			Distance
		</div>
		<div class="right-column">
			<SELECT id="distanceAdv" name="distance" class="smalldropdown" > 
				<option value="5">5 miles (8K)</option>
			    <option value="20">20 miles (32K)</option>
			    <option value="City" selected="selected">City</option>
			    <option value="Region">Region</option>
			    <option value="Country">Country</option>
			    <option value="Virtual">Virtual</option>
			</SELECT>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end dist -->
       
	<div id="greatForAdv" style="//padding:3px 0;">	
		<div class="left-column">
			Great for
		</div>
		<div class="right-column">
			<input type="checkbox" styleClass="check" value="<%=aszGroupTID%>" name="greatforgroup" id="greatforgroupAdv" onclick="javascript:document.getElementById('groupAdv').style.display='inline';"/>
			&nbsp;Group 
			<input type="checkbox" styleClass="check" value="<%=aszFamilyTID%>" name="greatforfamily" id="greatforfamilyAdv"/>
			&nbsp;Family
			<input type="checkbox" styleClass="check" value="<%=aszKidTID%>" name="greatforkid" id="greatforkidAdv"/>
			&nbsp;Kid
			<input type="checkbox" styleClass="check" value="<%=aszTeenTID%>" name="greatforteen" id="greatforteenAdv"/>
			&nbsp;Teen 
			<input type="checkbox" styleClass="check" value="<%=aszSeniorTID%>" name="greatforsenior" id="greatforseniorAdv"/>
			&nbsp;Senior
      <input type="checkbox"styleClass="radio" value="<%=aszYesWorkStudyTID%>" name="postype" id="postypeAdvWS" />
      &nbsp;Work Study Students 
	    </div>
		<div class="cleardiv"></div>
	</div><!-- end greatForAdv -->
       
	<div id="posFreqAdv" style="//padding:3px 0;">	
		<div class="left-column">
			Position Frequency
		</div>
		<div class="right-column">
		<!--
			<input type="radio" styleClass="radio" value="<%=""+iOneTime%>" name="frequency" id="frequencyAdvOneTime"/>
			&nbsp;One-time
			<input type="radio" styleClass="radio" value="<%=""+iOngoing%>" name="frequency" id="frequencyAdvOngoing"/>
			&nbsp;Ongoing
		-->
			<select id="frequencyAdv" name="frequency" size="1" class="smalldropdown" style="width: 175px;"> 
				<option value="" selected="selected"></option> 
				<%
					for(int index=0; index < aPosFreqList.size(); index++){
						iTemp = 0;
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPosFreqList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						int iSubType = aAppCode.getAPCIdSubType();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
		</div>
		<div class="cleardiv"></div>
	</div><!-- end posFreqAdv -->
       
	
  
	<div>	
		<div class="left-column">
		</div>
		<div class="right-column">
			
		</div>
		<div class="cleardiv"></div>
	</div>
	<div>	
		<div class="left-column">
			<input type="checkbox" CHECKED value="searchfeeds" name="source" id="source"/>
		</div>
		<div class="right-column" style="width:384px; padding-top:6px; //padding-top:-4px;">
			Search opportunities from third-party partners' sites <img src="http://www.christianvolunteering.org/imgs/afg-blue_100.png" style="margin:-18px 0 0; padding:0 0 0 5px; float:right; //float:none;" alt="AllForGood" width="100"/>
		</div>
		<div class="cleardiv"></div>
	</div>
  
  	

<% if(bAssoc==true){ %>
	<div>	
		<div class="left-column">
			<input type="checkbox" CHECKED value="true" name="assoconly" id="assoconly"/>
		</div>
		<div class="right-column" style="width:384px; padding-top:6px; //padding-top:-4px;">
			Search only this Association
		</div>
		<div class="cleardiv"></div>
	</div>
<% } %>



 <div id="orginfo" style="//padding:3px 0;">
  <br>
  <h2>Organizational Information</h2>

	<div class="left-column">
  Organization Name
	</div>
	<div class="right-column">
	<input type="text" name="orgname" id="orgnameAdv" styleId="orgname" size="30" maxlength="200" styleClass="textinputwhite"/>
	</div>
  </div>
	<div class="cleardiv"></div>
           
<% // start section of not showing affiliations for non-faith versions of the site %>
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<div class="left-column">
		Denominational Affiliation
	</div>
    <div class="right-column">
		<SELECT id="denomaffiltidAdv" name="denomaffiltid" class="smalldropdown" >
			<option value="" selected></option>
			<%
			if(aszHostID.equalsIgnoreCase("volengcatholic")){
				iTemp=992;			
			} else {
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
	    </SELECT>
    </div>
	<div class="cleardiv"></div>
     
	<div class="left-column">
		Affiliations
	 </div>
     <div class="right-column">
		<SELECT id="orgaffil1tidAdv" name="orgaffil1tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliationRowAdv2').style.display='inline';" >
			<option value=""></option>
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
			if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
				iTemp=1069;			
			} else if(aszHostID.equalsIgnoreCase("volengagrm")){
				iTemp=717;			
			} else {
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
	</div>
	<div class="cleardiv"></div>
   
	<div id="affiliationRowAdv2" 
	<% if(	aszHostID.equalsIgnoreCase("volengsalvationarmy")	||
			aszHostID.equalsIgnoreCase("volengagrm")
	){ %>
		style="display:inline"
	<% } else { %>
		style="display:none"
	<% } %>
	>
		<div class="left-column">
	    </div>
		<div class="right-column">
			<SELECT id="orgaffil2tidAdv" name="orgaffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliationRowAdv3').style.display='inline';"> 
				<option value=""></option>
				<%
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
		</div>
 		<div class="cleardiv"></div>
   	</div><!--div id="affiliationRowAdv2" -->
	
	<div id="affiliationRowAdv3" style="display:none; //padding:3px 0;">
		<div class="left-column">
		</div>
		<div class="right-column">
			<SELECT id="orgaffil3tidAdv" name="orgaffil3tid" class="smalldropdown" style="margin-top: 5px;" > 
				<option value=""></option>
				<%
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
		</div>
		<div class="cleardiv"></div>
   	</div><!--div id="affiliationRowAdv3" -->
<% } //end section of not showing affiliations for non-faith versions of the site %>

<div id="sortstm" style="display:none; //padding:5px 0;">
	<div class="left-column">
		Sort results by:
	</div>
	<div class="right-column">
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
	<div class="cleardiv"></div>
</div>	<!-- end div id sort -->

<div id="sortopps" style="//padding:5px 0;">
	<div class="left-column">
		Sort results by:
	</div>
	<div class="right-column">
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
	<div class="cleardiv"></div>
</div>	<!-- end div id sort -->

<div id="sortorgs" style="display:none; //padding:5px 0;">
	<div class="left-column">
		Sort results by:
	</div>
	<div class="right-column">
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
    <div class="cleardiv"></div>
</div>	<!-- end div id sortorgs -->

	<div class="left-column">
		<DIV class=clear style="HEIGHT: 10px"></DIV>
	</div>
	<div class="right-column">
		<input type="submit" name="Submit" value="Submit" style="<%=aszSubmit%>" >
	</div>
	<div class="cleardiv"></div>
</div><!-- end div notall -->
</form>


<div class="cleardiv"></div>


<div class="left-column" style="//height:20px;"> 
</div>
	<div class="right-column">
<div id="googleSearchAll" style="display:none; padding: 100px;" >
	<div class="span-columns"> 
<span style="font-weight: bold"> Search All of ChristianVolunteering.org:</span>
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
	</div><!-- end div class="span-columns"-->
	<div class="cleardiv"></div>
</div>
</div>




<script language="javascript">
	function opp_search(){
		document.getElementById('notall').style.display='inline';
		document.getElementById('serviceAreaSectionRow1').style.display='inline';
		document.getElementById('programtype1Adv').style.display='none';
		document.getElementById('greatForAdv').style.display='inline';
		document.getElementById('posFreqAdv').style.display='inline';
		document.getElementById('posTypeAdv').style.display='inline';
		document.getElementById('skillSectionRowAdv1').style.display='inline';
		

		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortorgs').style.display='none';
		document.getElementById('googleSearchAll').style.display='none';
	}
	function orgadv_search(){
		document.getElementById('notall').style.display='inline';
		document.getElementById('postalcountryAdv').style.display='inline';
		document.getElementById('serviceAreaSectionRow1').style.display='none';
		document.getElementById('programtype1Adv').style.display='inline';
		document.getElementById('regionsectionAdv').style.display='inline';
		document.getElementById('greatForAdv').style.display='none';
		document.getElementById('posFreqAdv').style.display='none';
		document.getElementById('posTypeAdv').style.display='none';
		document.getElementById('missionsAdv').style.display='none';
		document.getElementById('missionsDurationAdv').style.display='none';
		document.getElementById('groupAdv').style.display='none';
		document.getElementById('address2Adv').style.display='inline';
		document.getElementById('skillSectionRowAdv1').style.display='none';
		
		document.getElementById('sortstm').style.display='none';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='inline';
		document.getElementById('googleSearchAll').style.display='none';
	}
	function all_search(){
		document.getElementById('notall').style.display='none';
		document.getElementById('googleSearchAll').style.display='inline';
	}

	function clicked_local(){
		document.getElementById('postalcodeAdv').disabled = false;
		document.getElementById('postalcountryAdv').style.display='inline';
		document.getElementById('regionsectionAdv').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missionsAdv').style.display='none';
		document.getElementById('missionsDurationAdv').style.display='none';
		document.getElementById('groupAdv').style.display='none';
		document.getElementById('address2Adv').style.display='inline';
		
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='none';
	}
	function clicked_virtual(){
		document.getElementById('postalcodeAdv').disabled = true;
		document.getElementById('postalcountryAdv').style.display='none';
		document.getElementById('regionsectionAdv').style.display='none';
		document.getElementById('dist').style.display='none';
		document.getElementById('missionsAdv').style.display='none';
		document.getElementById('missionsDurationAdv').style.display='none';
		document.getElementById('groupAdv').style.display='none';
		document.getElementById('address2Adv').style.display='none';
		
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='none';
	}
	function clicked_missions(){
		document.getElementById('postalcodeAdv').disabled = false;
		document.getElementById('postalcountryAdv').style.display='inline';
		document.getElementById('regionsectionAdv').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missionsAdv').style.display='inline';
		document.getElementById('missionsDurationAdv').style.display='table-row';
		document.getElementById('groupAdv').style.display='inline';
		document.getElementById('address2Adv').style.display='inline';
		
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='none';
		
	    document.getElementById('countryAdv').value = '';
	}
	function clicked_all(){
		document.getElementById('postalcodeAdv').disabled = false;
		document.getElementById('postalcountryAdv').style.display='inline';
		document.getElementById('regionsectionAdv').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missionsRowAdv').style.display='inline';
		document.getElementById('missionsDurationAdv').style.display='inline';
		document.getElementById('groupAdv').style.display='inline';
		document.getElementById('address2Adv').style.display='inline';
		
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='none';
	}
	function clicked_both(){
		document.getElementById('postalcodeAdv').disabled = false;
		document.getElementById('postalcountryAdv').style.display='inline';
		document.getElementById('regionsectionAdv').style.display='inline';
		document.getElementById('dist').style.display='inline';
		document.getElementById('missionsRowAdv').style.display='none';
		document.getElementById('missionsDurationAdv').style.display='none';
		document.getElementById('groupAdv').style.display='none';
		document.getElementById('address2Adv').style.display='inline';
		
		document.getElementById('sortstm').style.display='inline';
		document.getElementById('sortopps').style.display='none';
		document.getElementById('sortorgs').style.display='none';
	}


</script>
