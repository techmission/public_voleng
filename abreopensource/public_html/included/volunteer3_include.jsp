<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Advanced Organization Search</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->





<%

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( afiliationList, 163 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getAppCodeList( aProgramList, 172 );
aCodes.getAppCodeList( apartnersList, 167 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszLocalAffil = "display:none;";
if(aszHostID.equalsIgnoreCase( "volengboston" )){ 
  aszLocalAffil = "display:inline;";
}

%>

</head>



<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">organization search</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<html:form action="/oppsrch.do" method="get" focus="postalcode" >
<html:hidden property="method" value="processOrgSearchAll" />
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">organization search</span><img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/oppsrch.do?method=showSearch">volunteer</a> &gt; 
	search for organizations  </div>
</div>
<% } %>

<div id="body">
  

     <div align="left">
	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 width="504" height="595" id="table1" >
		<!-- MSTableType="layout" -->
		<tr>
          <TD class=set>
			Choose One only &nbsp <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
			</TD>
          <TD valign="middle" colspan="2">
			<dl>
				<dt><html:radio styleClass="radio" value="Short-term Missions / Volunteer Internship" property="voltype" onclick="javascript:document.getElementById('virtualaddr').style.display='inline';" /> Short-term Missions / Volunteer Internship</dt>
				<dt><html:radio styleClass="radio" value="" property="voltype" onclick="javascript:document.getElementById('virtualaddr').style.display='inline';" /> All Volunteer Positions</dt>
			</dl>
	</TD>
    <TD></TD>
					<td height="83">&nbsp;</td>
					</tr>
					
		<tr>
          <TD colspan=4>
          <table cellspacing=0 cellpadding=0><tr><td width="135">
			Postal Code</TD>
          <TD colspan="2">
				<html:text property="postalcode" styleId="postalcode" size="5" maxlength="5" styleClass="textinputwhite"/></div></TD>
					<td></td>
      		      		
					<td height="25">&nbsp;</td>
                	</tr>
		<tr>
          <TD>
			Distance</TD>
          <TD colspan="2">
          		<SELECT id="distance" name="distance" class="smalldropdown" > 
        <option value="5">5 miles (8K)</option>

        <option value="20">20 miles (32K)</option>

        <option value="City" selected="selected">City</option>
        <option value="Region">Region</option>
        <option value="Country">Country</option>
        <option value="Virtual">Virtual</option>
<!--                <OPTION value=county>County</OPTION> -->
 <!--               <OPTION value=msa>Metro Area</OPTION> -->
               </SELECT>
               
         </TD>
                	<td></td>
					<td height="25">&nbsp;</td>
                	</td></tr></table></td>
			</tr>
		<tr>
                <TD valign="top">
				Select Program<br>Type <a href="<%=aszPortal%>/register.do?method=showHelp" onClick="return popup(this, 'help')">help</A>
				</TD>
                <TD valign="top" colspan="2">
				<SELECT id="category1" name="category1" size="1" class="smalldropdown" > 

				<option value="" selected="selected"></option> 
				<%
				for(int index=0; index < aProgramList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
					if(null == aAppCode) continue;
					out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
				%>

				</SELECT> 
                </TD>
      		     		
			<td></td>
			<td height="29">&nbsp;</td>
		</tr>
		<tr>
			<TD colspan=3 ><HR></TD>
			<td></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<TD valign="top">Organization<br>Name</TD>
	        <TD colspan="2">
	            <html:text property="orgname" styleId="orgname" size="30" maxlength="200" styleClass="textinputwhite"/>
	        </TD>
			<td colspan=2></td>
			<td height="26">&nbsp;</td>
		</tr>
		<tr>
			<TD colspan=3 ></TD>
			<td></td>
			<td height="25">&nbsp;</td>
			</tr>
			<td colspan=2></td>
			</tr>
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
        <TD colspan="2">
        	<SELECT id="state" name="state" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
				<%
				for(int index=0; index < aStateList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
					if(null == aAppCode) continue;
					out.println(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
					out.println(" > &nbsp; " + aAppCode.getCSPStateName() + " &nbsp; </option> ");
				}
				%>
              </SELECT>&nbsp; &nbsp;Other <html:text property="prov" size="10" styleClass="textinputwhite"/>
              
        </TD>
			<td></td>
			<td height="37">&nbsp;</td>
		</tr>
		<tr>
        <TD>
		Country</TD>
        <TD colspan="2">
        	<SELECT id="country" name="country" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
					<%
					aszTemp= "";
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
              </SELECT>
        </TD>
			<td></td>
			<td height="37">&nbsp;</td>
		</tr>

		<tr>
        <TD>
		Region</TD>
        <TD colspan="2">
        	<SELECT id="region" name="region" class="smalldropdown"> 
        		<OPTION value=""></OPTION> 
				<%
				for(int index=0; index < aRegionList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
					if(null == aAppCode) continue;
					out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
					out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
				}
				%>
              </SELECT>
		</TD>
			<td></td>
			<td height="37">&nbsp;</td>
		</tr>
		
		
		<tr>
          <TD class=set valign="top">
			Denominational<br>Affiliation</TD>
          <TD valign="middle" colspan="2">
          <select id="affiliation" name="affiliation" class="smalldropdown" >

			<%
			for(int index=0; index < afiliationList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
				if(null == aAppCode) continue;
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>

		    </select>
		    </TD>
			<td></td>
			<td height="59">&nbsp;</td>
			</tr>
		<!--<tr>
                <TD valign="top" colspan=3>
                  Do you prefer that the organization require a creed or 
					statement of faith from volunteers? </TD>
				<td></td>
			<td>&nbsp;</td>

		</tr>
		<tr><td></td>
                <TD>
                  <select name="requiredcodetype" id="requiredcodetype" class="smalldropdown" >
					<option selected value="">No Preference</option>
					<option value="No">No</option>
					<option value="Yes">Yes</option>
				 </select>
				<td width="114">&nbsp;</td>
			<td></td>
			<td>&nbsp;</td>
			</tr>
		<tr>
                <TD valign="top">
                  Creed/ Christian Belief</TD>
            <TD colspan="3" valign="middle">
            <select name="requiredcodedesc" id="requiredcodedesc" class="smalldropdown" >
			<OPTION selected value="">No Preference</OPTION>
			<%
			for(int index=0; index < acreedList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)acreedList.get(index);
				if(null == aAppCode) continue;
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>

			</select>
			</TD>
			<td height="59">&nbsp;</td>
		</tr>-->
		<tr>
          <TD>
			Affiliations</TD>
              <TD valign="top">
			<SELECT id="partner" name="partner" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation2').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
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
	<div id="affiliation2" style="display: none;">

			<SELECT id="partner2" name="partner2" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation3').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation3" style="display: none;">

			<SELECT id="partner3" name="partner3" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation4').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation4" style="display: none;">

			<SELECT id="partner4" name="partner4" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affiliation5').style.display='inline';"> 
			<option value=""></option>
			<%
			aszTemp = "";
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			
			}
			%>	
			</SELECT>
	</div></td></tr>
	<tr>
			<TD></td><td>
	<div id="affiliation5" style="display: none;">

			<SELECT id="partner5" name="partner5" class="smalldropdown" style="margin-top: 5px;"  > 
			<option value=""></option>
			<%
			aszTemp = "";
			for(int index=0; index < apartnersList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getAPCDisplay();
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
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
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
					%>
				</select></td><td height=45></td></tr>
</table></div>				
	</td></tr>



	
	
	<tr><td>Sort results<br>alphabetically by: </td><td>
	<SELECT id="searchkey1" name="searchkey1" class="smalldropdown" > 
        <option value=""></option>
        <option value="organization">Organization Name</option>
        <option value="city">City</option>
        <option value="state">State (US & Canada)</option>
        <option value="prov">Province (outside US & Canada)</option>
        <option value="country">Country</option>
        <option value="denomaffil">Denominational Affiliation</option>
        <option value="affil">Affiliation</option>
    </SELECT>
</td></tr>

		<tr>
			<td width="105">&nbsp;</td>
          <TD colspan="2"><input type="submit" name="Submit" value="Submit">
        </TD>
			<td width="4"></td>
			<td height="45" width="28">&nbsp;</td>
		</tr>
      </table>

</div>

</html:form>

      <P>
      <BR>&nbsp;</P></div>

    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->