<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information --><head>

<% aszPortalTemplate="basic"; %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->



<SCRIPT TYPE="text/javascript">
<!--
function popup(mylink, windowname)
{
if (! window.focus)return true;
var href;
if (typeof("http://www.christianvolunteering.org/orglistings1335.jsp") == 'string')
   href="http://www.christianvolunteering.org/orglistings1335.jsp";
else
   href="http://www.christianvolunteering.org/orglistings1335.jsp".href;
window.open(href, windowname, 'width=400,height=200,scrollbars=yes');
return false;
}
//-->
</SCRIPT>
<style>
#body {
font-family:Arial, Helvetica, sans-serif;
}
#maincontent a{
color:#556B9F;
font-weight: bold;
}

</style>

<title>Christian Volunteer Network: Christian Volunteer Opportunity Search Results</title>


<bean:define id="searchinfo" name="searchinfo" type="com.abrecorp.opensource.dataobj.SearchParms"/>


<!-- BEGIN MAINCONTENT -->
</head>




<div id="maincontent">
<%
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );

String aszOrgNamePrint = "";
String aszServiceAreasPrint = "";
String aszOppTitlePrint = "";

int iCounter=0;
%>

<div id="body">

<br>
      <TABLE class="searchtoolfull" cellSpacing=0 cellPadding=3 border=0 id="table1" width="540" style="font-size:12px;"> 
        <!-- MSTableType="layout" -->
		<!-- width="543"  > -->
        <tr>
        <td></td><td></td>
          <TD>
			<b>Title</b></TD><td></td>
          <!--TD>
			<b>Organization</b></TD><td></td-->
          <TD>
			<b>City, State/Country</b></TD><td></td>
          <TD>
			<b>Service Areas</b></TD>
        </tr>
        <TR>
        	<TD colspan=9><HR></TD>
        </TR>
        
		<logic:notEmpty name="alist" >
			<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
			<TR>
				<td valign="top"><%iCounter++;%><%=iCounter%></td><td></td>
				<TD colspan=1 valign="top" align="left" width=150><A href="http://<%=aszSubdomain%>/<%=org.getOPPUrlAlias()%>.jsp" target="_blank">
				
				<%
					aszOppTitlePrint = org.formatForPrintSemi(org.getOPPTitle(),20);
				%>

				<%=aszOppTitlePrint%>
				
				</A></td><td></td>
				<!--TD valign="top">
				
				<%
					aszOrgNamePrint = org.formatForPrint(org.getORGOrgName(),5);
				%>

				<%=aszOrgNamePrint%>
				
				</TD><td></td-->
				<TD valign="top">
				
					<%=org.getOPPAddrCity()%><%
					String aszCity= org.getOPPAddrCity();
					String aszTemp1= org.getOPPAddrCountryName();
					String aszTemp2 = org.getOPPAddrStateprov();
				
					if(aszTemp1.equalsIgnoreCase("US")){
						if(null != aStateList){
							for(int index=0; index < aStateList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getCSPStateCode();
								if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
									if(aszCity.equalsIgnoreCase( "" ) ) {
										out.println(aAppCode.getCSPStateName() );
									}else {
										out.println(", " + aAppCode.getCSPStateName() );
									}
								}

							}
						}
					} else {
						if(null != aCountryList){
							for(int index=0; index < aCountryList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getCTRIso();
								if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
									if(aszCity.equalsIgnoreCase( "" ) ) {
										out.println(aAppCode.getCTRPrintableName() + "");
									}else {
										out.println(", " + aAppCode.getCTRPrintableName() + "");
									}
								}
							}
						}
					}
				%>
				
				</TD><td></td>
				<TD valign="top">
				
				<%
					aszServiceAreasPrint = org.formatForPrintSemi(org.getOPPCategories(),10);
				%>

				<%=aszServiceAreasPrint%>
				
				</TD>
			</tr>
			<TR><TD colspan=9><HR></TD></TR>
			</logic:iterate>
		</logic:notEmpty>
			<TR><TD colspan=9 align=center>Opportunities provided by <a href="http://www.christianvolunteering.org" target="_blank" title="Christian Volunteer Network for Urban Ministries and Short Term Missions Opportunities">ChristianVolunteering.org</a>
</TD></TR>
</table>

<br><br>

</div>


<div align=center>



</div>


</div>


