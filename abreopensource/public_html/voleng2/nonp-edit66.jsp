<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->
<!-- Google Code for ChristianVolunteering.org Registered Organization Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "y02aCMTdiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=y02aCMTdiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>



<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>
<bean:define id="det" name="det" type="com.abrecorp.opensource.dataobj.OrganizationDetailsInfoDTO"/>


<%
ArrayList aPercentageList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getAppCodeListID( aPercentageList, 173 );

int number;

String aszUS="display: none;";
String aszOrgCountry = org.getORGAddrCountryName();
if(aszOrgCountry.equalsIgnoreCase("US") ){
   aszUS="display: inline;";
}
%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	  <span id="title">Edit Organization Demographics</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<html:form action="/org.do" focus="addr1" >
<html:hidden property="method" value="processEditOrgDet" />
<html:hidden property="requesttype" value="edit" />
<html:hidden property="orgnid" />
<html:hidden property="orgname" />
<html:hidden property="numserved" value="orgnumserved"/>
<html:hidden property="numvolorg" value="orgnumvol" />

<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Edit Organization Demographics</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; <a href="<%=aszPortal%>/orgmanagement.jsp">organization management</a> &gt; <A href="<%=aszPortal%>/org.do?method=showOrgSumm1&orgnid=<bean:write name='orgForm' property='orgnid' />" >
	administer this organization</A> &gt; organization demographics  </div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

  <div id="body">
      	<div align="left">

  <img src="/img/blank.gif" width="415" height="1">
  <BR>
  
<br>
<h3>&nbsp;Organizational Details</h3>
<br>

<bean:write name='orgForm' property='errormsg' />


	<table border=1 cellpadding=4 cellspacing=0>

            <tr>
            <td colspan=2 valign="top">Does your church/ministry have formal nonprofit
            <br>status (501)(c)(3) (if in the USA)?</TD>
            <TD colspan=2>
				<html:radio styleClass="check" value="Y" property="is501c3" /> Yes 
			    &nbsp; &nbsp; 
			    <html:radio styleClass="check" value="N" property="is501c3" /> No
            </td>
            </tr>
			<tr>
                <TD colspan=2>Nonprofit Identification Number:  <span class="criticaltext">*</span></TD>
                <TD><html:text property="detfedtaxid" size="30" styleClass="textinputwhite"/><!--orgfedtaxid--></TD>
          </tr>
            </table>

<br>
<h3>&nbsp;Organizational Outcomes</h3>
<br>
<table border=1 cellpadding=4 cellspacing=0>
	<tr>
		<td>Estimated Size of Annual Organization Budget: <br>(enter dollar amount in USD; do not use $ or , )</td>
		<td><html:text property="orgbudget" size="30" styleClass="textinputwhite"/></td>
	</tr>
	<tr>
		<td>Total Number of Individuals Served Each Year</td>
		<td><html:text property="orgnumserved" size="5" styleClass="textinputwhite"/><!--numserved--></td>
	</tr>
	<tr>
		<td>Number of Staff</td>
		<td><html:text property="numstafforg" size="5" styleClass="textinputwhite"/></td>
	</tr>
	<tr>
		<td>Number of Volunteers Each Year</td>
		<td><html:text property="orgnumvol" size="5" styleClass="textinputwhite"/><!--numvolorg--></td>
	</tr>
</table>

<br><br>
<h3>&nbsp;Demographics of Program Participants</h3>
<br>
<table border=0 cellpadding=4 cellspacing=0>
	<tr><td valign="top">
	<table border=1 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan=2><b>Primary Language of Communities Being Served:</b></td>
		</tr>
		<tr>
			<td>Arabic</td>
			<td>
			<select id="langrabic" name="langrabic" class="smalldropdown">
				<%
					number = det.getDETLanguageArabic();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Chinese (Mandarin)</td>
			<td>
			<select id="langchinese" name="langchinese" class="smalldropdown">
				<%
					number = det.getDETLanguageChinese();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>English</td>
			<td>
			<select id="langenglish" name="langenglish" class="smalldropdown">
				<%
					number = det.getDETLanguageEnglish();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>French</td>
			<td>
			<select id="langfrench" name="langfrench" class="smalldropdown">
				<%
					number = det.getDETLanguageFrench();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Hindi</td>
			<td>
			<select id="langhindi" name="langhindi" class="smalldropdown">
				<%
					number = det.getDETLanguageHindi();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Portuguese</td>
			<td>
			<select id="langportuguese" name="langportuguese" class="smalldropdown">
				<%
					number = det.getDETLanguagePortuguese();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Spanish</td>
			<td>
			<select id="langspanish" name="langspanish" class="smalldropdown">
				<%
					number = det.getDETLanguageSpanish();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Other:&nbsp;<html:text property="langotheratxt" size="5" styleClass="textinputwhite"/></td>
			<td>
			<select id="langothera" name="langothera" class="smalldropdown">
				<%
					number = det.getDETLanguageOtherA();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Other:&nbsp;<html:text property="langotherbtxt" size="5" styleClass="textinputwhite"/></td>
			<td>
			<select id="langotherb" name="langotherb" class="smalldropdown">
				<%
					number = det.getDETLanguageOtherB();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
	</table>
	</td>
	<td></td>
	<td>

<div id="unitedstates" style="<%=aszUS%>">
	<table border=1 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan=2><b>Predominant ethnic group(s) <br>served:</b></td>
		</tr>
		<tr>
			<td>Black/African American</td>
			<td>
			<select id="raceblack" name="raceblack" class="smalldropdown">
				<%
					number = det.getDETRaceBlack();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
			</td>
		</tr>
		<tr>
			<td>Asian</td>
			<td>
			<select id="raceasian" name="raceasian" class="smalldropdown">
				<%
					number = det.getDETRaceAsian() ;
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
			</select>
			</td>
		</tr>
		<tr>
			<td>Caucasian/European Origin</td>
			<td>
			<select id="racecaucasian" name="racecaucasian" class="smalldropdown">
				<%
					number = det.getDETRaceCaucasian();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Latino</td>
			<td>
			<select id="racelatino" name="racelatino" class="smalldropdown">
				<%
					number = det.getDETRaceLatino();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Native American</td>
			<td>
			<select id="racenativeamerican" name="racenativeamerican" class="smalldropdown">
				<%
					number = det.getDETRaceNativeAmerican();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Pacific Islander</td>
			<td>
				<select id="racepacificislander" name="racepacificislander" class="smalldropdown">
				<%
					number = det.getDETRacePacificIslander();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Other:&nbsp;<html:text property="raceothertext" size="5" styleClass="textinputwhite"/></td>
			<td>
				<select id="raceother" name="raceother" class="smalldropdown">
				<%
					number = det.getDETRaceOther();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
	</table>
</div>	
	</td>
	</tr>
<tr><td colspan=2 height=10></td></tr>
	<tr><td valign="top" rowspan="2">
	<table border=1 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan=2><b>Other Community Demographics:</b></td>
		</tr>
		<tr>
			<td>Low Income</td>
			<td>
			<select id="demolowincome" name="demolowincome" class="smalldropdown">
				<%
					number = det.getDETDemoLowIncome();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Homelessness</td>
			<td>
			<select id="demoHomeless" name="demoHomeless" class="smalldropdown">
				<%
					number = det.getDETDemoHomeless();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Individuals with <br>Disabilities</td>
			<td>
			<select id="demodisability" name="demodisability" class="smalldropdown">
				<%
					number = det.getDETDemoDisability();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Urban</td>
			<td>
			<select id="demourban" name="demourban" class="smalldropdown">
				<%
					number = det.getDETDemoUrban();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Rural</td>
			<td>
			<select id="demorural" name="demorural" class="smalldropdown">
				<%
					number = det.getDETDemoRural();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
	</table>
	</td>
	<td></td>
	<td>
	<table border=1 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan=2><b>Ages of Participants:</b></td>
		</tr>
		<tr>
			<td>Youth (12-18)</td>
			<td>
			<select id="demoyouth" name="demoyouth" class="smalldropdown">
				<%
					number = det.getDETDemoYouth();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Adults (19-59)</td>
			<td>
			<select id="demoadults" name="demoadults" class="smalldropdown">
				<%
					number = det.getDETDemoAdults();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Individuals with <br>Seniors (60+)</td>
			<td>
			<select id="demoseniors" name="demoseniors" class="smalldropdown">
				<%
					number = det.getDETDemoSeniors();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	<tr>
	<td></td><td>
	<table border=1 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan=2><b>Gender of Participants:</b></td>
		</tr>
		<tr>
			<td>Male</td>
			<td>
			<select id="orggendermale" name="orggendermale" class="smalldropdown">
				<%
					number = det.getDETGenderMale();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Female</td>
			<td>
			<select id="orggenderfemale" name="orggenderfemale" class="smalldropdown">
				<%
					number = det.getDETGenderFemale();
					aszTemp = "" + number;
					for(int index=0; index < aPercentageList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPercentageList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "%</option> ");
					}
				%>
				</select>
			</td>
		</tr>
	</table>
	</td></tr>
</table>
<br><br>
<br><br>

<!-- remove tech stuff -->

<table cellpadding="2" cellspacing="2" >
	<tr>
			<td width="93" height="44">&nbsp;</td>
		    <TD>

		      		<INPUT class=submit type=submit value=Continue>
			</TD>
		</tr>
		<tr>
		  <TD height="25" colspan=2 ><span class="criticaltext">*</span>  Required Item</td>
		  </tr>
	</TABLE>
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



