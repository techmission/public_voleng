<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>

<title>Christian Volunteer Network: Volunteer Search Form on Your Website</title>

<% } else { %>
<% } %>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<%
String aszBoston = "display:none";
if ( (aszHostID.equalsIgnoreCase("volengboston")) || (aszHostID.equalsIgnoreCase("volengnewengland")) ){
	aszBoston = "display:inline";
}
%>


</head>

<%

int iTemp=0, vidService=32;
ArrayList aServiceList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getTaxonomyCodeList( aServiceList, vidService );

%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">ChristianVolunteering.org on Your Site</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
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
<span style="float: left;">ChristianVolunteering.org on Your Site</span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/org.do?method=showPasteForms">christianvolunteering.org on your site</div>
</div>
<% } %>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="/imgs/pic/blowing.jpg" width="245" height="204" ></td>
    <td valign="top" class="intsplash"><h1>
	ChristianVolunteering.org on your website!</h1>
      <p LANG="en-US" STYLE="margin-bottom: 0in">Below is the code allowing you 
		to put the search forms for ChristianVolunteering.org on your website. 
		Now volunteers can find volunteer opportunities like yours from your own 
		website! Simply cut and paste this code onto your website.</p>
	</td>
  </tr>
</table>
</div>
 <div id="body">
       <a><p>Which search form would you like?</p></a>
     <!--<p><a href="#widget_new" title="put Christian Social Action Widget on your website">Christian Social Action Widget (recommended)</a> &nbsp; </p> --> 
	 <p><a href="#widget" title="put christian volunteer search on your website">WidgetBox search form (recommended)</a> &nbsp; </p>  
	 <p><a href= "#short_form_white" title="put christian volunteer search on your website">Short form with white background</a> &nbsp</p>      
	 <p><a href= "#short_form" title="put christian volunteer search on your website">Short form with transparent background</a> &nbsp</p>
       <p><a href= "#medium_form" title="put christian volunteer search on your website">Medium form with transparent background</a>&nbsp</p>

       <p><a href= "#medium_form_white" title="put christian volunteer search on your website">Medium form with white background</a>&nbsp</p>
<div id="bostonlinks" style="<%=aszBoston%>">
	<p><a href= "#boston_browse" title="put boston christian volunteer search on your website">Boston form with browsing options</a>
</div>
       	<p><a href="<%=aszPortal%>/org.do?method=showPasteFormsAdv#advanced_form_white" title="put christian volunteer search on your website">Advanced form with white background</a>&nbsp</p> 
       
       <!-- <a href= "#advanced form">Advanced form</a> -->
       	<br>
<hr width="100%" />
<!--<a name="widget_new"><h3>Social Action Widget</h3></a>
<br /><hr width="100%" />
       <script type="text/javascript" src="http://widgetserver.com/syndication/subscriber/InsertWidget.js?appId=2d0971ef-cc91-4660-9efc-4facd8d043c7"></script><noscript>Get the <a href="http://www.widgetbox.com/widget/christianvolunteering-search">Christian Social Action Widget</a> widget and many other <a href="http://www.widgetbox.com/">great free widgets</a> at <a href="http://www.widgetbox.com">Widgetbox</a>!</noscript>
       
		<br>
<hr width="100%" /> --><br />
<a name="widget"></a><h3>WidgetBox search form</h3><br /><hr width="100%" />
<script type="text/javascript" src="http://widgetserver.com/syndication/subscriber/InsertPanel.js?panelId=fbec9e2d-8cfe-4bd2-aa03-d8ada8abe8de"></script><noscript>Get great free widgets at <a href="http://www.widgetbox.com">Widgetbox</a>!</noscript><img style="visibility:hidden;width:0px;height:0px;" border="0" width="0" height="0" src="http://runtime.widgetbox.com/syndication/track/fbec9e2d-8cfe-4bd2-aa03-d8ada8abe8de.gif" />
<p><strong>in body: </strong></p>
<p>&lt;script type=&quot;text/javascript&quot; src=&quot;http://widgetserver.com/syndication/subscriber/InsertPanel.js?panelId=fbec9e2d-8cfe-4bd2-aa03-d8ada8abe8de&quot;&gt;&lt;/script&gt;</p>
<p>&lt;noscript&gt;Get great free widgets at &lt;a href=&quot;http://www.widgetbox.com&quot;&gt;Widgetbox&lt;/a&gt;!&lt;/noscript&gt;&lt;img style=&quot;visibility:hidden;width:0px;height:0px;&quot; border=&quot;0&quot; width=&quot;0&quot; height=&quot;0&quot; src=&quot;http://runtime.widgetbox.com/syndication/track/fbec9e2d-8cfe-4bd2-aa03-d8ada8abe8de.gif&quot; /&gt;</p>
<p><strong>widget url:</strong></p>
<p><a href="http://www.widgetbox.com/widget/christian-volunteering-small">http://www.widgetbox.com/widget/christian-volunteering-small</a></p><br />

<hr width="100%"> <a name= "short_form_white"></a><h3>Short Form - White</h3><br /><hr width="100%">


<form name="searchForm4" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
<table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;" bgcolor="#FFFFFF">
<tr><td colspan=2>
			<A HREF="http://<%=aszSubdomain%>">
			<img src= "http://www.christianvolunteering.org/imgs/logo_final_ad300.gif" width="300" height="61" border="0"/></A>


</td></tr>
<tr>
<td width="65" align="right">Postal Code</td>
<td><input style="width:180px;" type="text" name="postalcode" maxlength="5" /></td>
</tr>
<tr>
<td align="right">service area</td>
<td>
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
		</SELECT>
	<input type="image" name="imageField" src="/imgs/search_arrow.gif" width="19" height="19" border="0" align="absmiddle" />
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><a href="http://<%=aszSubdomain%>/advancedsearch.jsp">advanced search</a></td>
</tr>
</table>
</form>


<p><strong>in body: </strong></p>		
		<p>
		
			&lt;form name=&quot;searchForm3&quot; method=&quot;get&quot; action=&quot;http://<%=aszSubdomain%>/oppsrch.do&quot;&gt;

<br>
		&lt;input type=&quot;hidden&quot; name=&quot;method&quot; value=&quot;processOppSearchAll&quot;&gt;
		<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<br>&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;1069&quot;&gt;
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<br>&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;11545&quot;&gt;
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<br>&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;717&quot;&gt;
<% } %>

		
		<br>
		&lt;input type=&quot;hidden&quot; name=&quot;distance&quot; value=&quot;City&quot;&gt;<br><br>
		&lt;table width=&quot;300&quot; border=&quot;0&quot; cellpadding=&quot;0&quot; cellspacing=&quot;5&quot; 
		style=&quot;font-size: 11px;&quot; bgcolor=&quot;#FFFFFF&quot;&gt;<br>
		&lt;tr&gt;&lt;td colspan=&quot;2&quot;&gt;
		
			&lt;A HREF=&quot;http://<%=aszSubdomain%>&quot;&gt;&lt;img src= &quot;http://www.christianvolunteering.org/imgs/logo_final_ad300.gif&quot; 
			width=&quot;300&quot; height=&quot;61&quot; border=&quot;0&quot;/&gt;&lt;/A&gt;
		
		&lt;/td&gt&lt;/tr&gt;<br>
		&lt;tr&gt;<br>
		&lt;td width=&quot;65&quot; align=&quot;right&quot;&gt;Postal Code&lt;/td&gt;<br>
		&lt;td&gt;&lt;input style=&quot;width:180px;&quot; type=&quot;text&quot; name=&quot;postalcode&quot; maxlength=&quot;5&quot; 
		/&gt;&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;tr&gt;<br>
		&lt;td align=&quot;right&quot;&gt;service area&lt;/td&gt;<br>
		&lt;td&gt;<br>
		&lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot;style=&quot;width: 215px; //width:207px; z-index: 0;&quot;&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
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
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Disabilities Outreach&lt;/option&gt <br>");
							}else if (iSubType == 4774){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Food Service / Hunger&lt;/option&gt <br>");
							}else if (iSubType == 4782){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Prison Outreach&lt;/option&gt ");
							}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
							}
						}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
						}
					}
				%>
&lt;/SELECT&gt;<br />
<br>
		
		
		<br>
		&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;tr&gt;<br>
		&lt;td&gt;&amp;nbsp;&lt;/td&gt;<br>
		&lt;td&gt;
		
			&lt;input type=&quot;image&quot; name=&quot;imageField&quot; src=&quot;http://www.ChristianVolunteering.org/imgs/search_arrow.gif&quot; 
			width=&quot;19&quot; height=&quot;19&quot; border=&quot;0&quot; align=&quot;absmiddle&quot; /&gt;
		
		<br>
		&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;/table&gt;<br>
		&lt;/form&gt;<br>
&nbsp;</p>

		<br>

<hr width=100%>	 
<a name= "short_form"></a><h3>Short form - clear</h3><br>
		<hr width=100%>	


		
		
&nbsp;

			<A HREF="http://<%=aszSubdomain%>">
			<img border="0" src="/imgs/logo_final_ad_clr300.gif" width="288" height="58"></A>



<form name="searchForm3" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="postype" value="">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
<table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">
<!-- MSTableType="layout" -->
<tr>
<td width="65" align="right">Postal Code</td>
<td><input style="width:180px;" type="text" name="postalcode" maxlength="5" /></td>
</tr>
<tr>
<td align="right">service area</td>
<td>
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
		</SELECT>

<input type="image" name="imageField0" src="/imgs/search_arrow.gif" width="19" height="19" border="0" align="absmiddle" />


</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><a href="http://<%=aszSubdomain%>/advancedsearch.jsp">advanced search</a></td>
</tr>
</table>
</form>


		

		<p><b>in body: </b></p>
		
		<p>
			&lt;A HREF=&quot;http://<%=aszSubdomain%>&quot;&gt;&lt;img border=&quot;0&quot; src=&quot;http://www.ChristianVolunteering.org/imgs/logo_final_ad_clr300.gif&quot; 
			width=&quot;288&quot; height=&quot;58&quot;&gt;&lt;/A&gt;
<br /><br />
			&lt;form name=&quot;searchForm3&quot; method=&quot;get&quot; action=&quot;http://<%=aszSubdomain%>/oppsrch.do&quot;&gt;
<br>
		&lt;input type=&quot;hidden&quot; name=&quot;method&quot; value=&quot;processOppSearchAll&quot;&gt;
		<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<br />&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;1069&quot;&gt;
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<br />&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;11545&quot;&gt;
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<br />&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;717&quot;&gt;
<% } %>

		
		<br>
		&lt;input type=&quot;hidden&quot; name=&quot;distance&quot; value=&quot;City&quot;&gt;<br><br />
		&lt;table width=&quot;300&quot; border=&quot;0&quot; cellpadding=&quot;0&quot; cellspacing=&quot;5&quot; 
		style=&quot;font-size: 11px;&quot; bgcolor=&quot;#FFFFFF&quot;&gt;<br>
		&lt;tr&gt;<br>
		&lt;td width=&quot;65&quot; align=&quot;right&quot;&gt;Postal Code&lt;/td&gt;<br>
		&lt;td&gt;&lt;input style=&quot;width:180px;&quot; type=&quot;text&quot; name=&quot;postalcode&quot; maxlength=&quot;5&quot; 
		/&gt;&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;tr&gt;<br>
		&lt;td align=&quot;right&quot;&gt;service area&lt;/td&gt;<br>
		&lt;td&gt;<br>
		&lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot;style=&quot;width: 215px; //width:207px; z-index: 0;&quot;&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
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
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Disabilities Outreach&lt;/option&gt <br>");
							}else if (iSubType == 4774){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Food Service / Hunger&lt;/option&gt <br>");
							}else if (iSubType == 4782){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Prison Outreach&lt;/option&gt ");
							}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
							}
						}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
						}
					}
				%>
&lt;/SELECT&gt;<br />
<br>
		
		
		
		<br>
		&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;tr&gt;<br>
		&lt;td&gt;&amp;nbsp;&lt;/td&gt;<br>
		&lt;td&gt;
		
			&lt;input type=&quot;image&quot; name=&quot;imageField&quot; src=&quot;http://www.ChristianVolunteering.org/imgs/search_arrow.gif&quot; 
			width=&quot;19&quot; height=&quot;19&quot; border=&quot;0&quot; align=&quot;absmiddle&quot; /&gt;
		
		
		<br>
		&lt;/td&gt;<br>
		&lt;/tr&gt;<br>
		&lt;/table&gt;<br>
		&lt;/form&gt;<br>
&nbsp;</p>


		<hr width="100%"> <a name= "medium_form"></a><h3>Medium Form</h3><br><hr width="100%">
		<br>
		
<A HREF="http://<%=aszSubdomain%>"><img src= "http://www.ChristianVolunteering.org/imgs/logo_final_ad_clr400.gif" width="400" height="81" border="0"/> </A>
		
				<form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="postype" value="">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
			  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
<tr><td colspan=4></td>
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
			      <td align="right"><strong>Service<br />
			        Area</strong></td>
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
				</SELECT>
</td>
		        </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><input type="image" name="imageField" src="/imgs/button_search_clr.gif" border="0" width="59" height="24" />
			      </td>
			    </tr>
			  </table>
			</form>
	
		
	

		<p><b>in body: </b></p>
		
		<p>
	
			&lt;A HREF=&quot;http://<%=aszSubdomain%>&quot;&gt;&lt;img src= &quot;http://www.ChristianVolunteering.org/imgs/logo_final_ad_clr400.gif&quot; 
			width=&quot;400&quot; height=&quot;81&quot; border=&quot;0&quot;/&gt; &lt;/A&gt;
<br /><br />
			&lt;form id=&quot;homesearch&quot; method=&quot;get&quot; action=&quot;http://<%=aszSubdomain%>/oppsrch.do&quot;&gt;
		
	<br>
	&lt;input type=&quot;hidden&quot; name=&quot;method&quot; value=&quot;processOppSearchAll&quot;&gt;<br>
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;1069&quot;&gt;<br>
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;11545&quot;&gt;<br>
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;717&quot;&gt;<br>
<% } %>
		&lt;input type=&quot;hidden&quot; name=&quot;distance&quot; value=&quot;City&quot;&gt;<br><br />
	&lt;table width=&quot;300&quot; border=&quot;0&quot; cellpadding=&quot;0&quot; cellspacing=&quot;5&quot; 
	style=&quot;font-size: 12px;&quot; height=&quot;97&quot; id=&quot;table3&quot;&gt;<br>
	&lt;tr&gt;<br>
	&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Postal Code&lt;/strong&gt;&lt;/td&gt;<br>
	&lt;td width=&quot;71&quot;&gt;&lt;input name=&quot;postalcode&quot; type=&quot;text&quot; id=&quot;postalcode&quot; 
	style=&quot;width:60px;&quot; /&gt;&lt;/td&gt;<br>
	&lt;td width=&quot;55&quot;&gt;&lt;strong&gt;Distance&lt;/strong&gt;&lt;/td&gt;<br>
	&lt;td height=&quot;22&quot; width=&quot;94&quot;&gt;&lt;select id=&quot;distance&quot; name=&quot;distance&quot; 
	style=&quot;width:80px; z-index: -1&quot;&gt;<br>
	&lt;option value=&quot;5 miles (8K)&quot;&gt;5 miles (8K)&lt;/option&gt;<br>
	&lt;option value=&quot;20 miles (32K)&quot;&gt;20 miles (32K)&lt;/option&gt;<br>
	&lt;option value=&quot;City&quot; selected=&quot;selected&quot;&gt;City&lt;/option&gt;<br>
	&lt;option value=&quot;Region&quot;&gt;Region&lt;/option&gt;<br>
	&lt;option value=&quot;Country&quot;&gt;Country&lt;/option&gt;<br>
	&lt;option value=&quot;Virtual&quot;&gt;Virtual&lt;/option&gt;<br>
	&lt;/select&gt;&lt;/td&gt;<br>
	&lt;/tr&gt;<br>
	&lt;tr&gt;<br>
	&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Service&lt;br /&gt;<br>
	Area&lt;/strong&gt;&lt;/td&gt;<br>
	&lt;td colspan=&quot;3&quot; height=&quot;30&quot;&gt;<br>
	&lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot; style=&quot;width: 215px; //width:207px; z-index: 0;&quot;&gt;	<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
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
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Disabilities Outreach&lt;/option&gt <br>");
							}else if (iSubType == 4774){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Food Service / Hunger&lt;/option&gt <br>");
							}else if (iSubType == 4782){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Prison Outreach&lt;/option&gt ");
							}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
							}
						}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
						}
					}
				%>
&lt;/SELECT&gt;<br />
<br>
	&lt;/td&gt;<br>
	&lt;/tr&gt;<br>
	&lt;tr&gt;<br>
	&lt;td width=&quot;55&quot;&gt;&amp;nbsp;&lt;/td&gt;<br>
	&lt;td colspan=&quot;3&quot; height=&quot;25&quot;&gt;
	
			&lt;input type=&quot;image&quot; name=&quot;imageField&quot; src=&quot;http://www.christianvolunteering.org/imgs/button_search_clr.gif&quot; 
			border=&quot;0&quot; width=&quot;65&quot; height=&quot;26&quot; /&gt;
		
	<br>
	&lt;/a&gt;&lt;/td&gt;<br>
	&lt;/tr&gt;<br>
	&lt;/table&gt;<br>
	&lt;/form&gt; <br>
	<br>
</p>

<hr width="100%"> <a name= "medium_form_white"></a><h3>Medium Form - White</h3><br><hr width="100%">
		

				<form id="homesearch2" NAME="homesearch2" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="postype" value="">
<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	<input type="hidden" name="orgaffil1tid" value="1069">
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
<input type="hidden" name="orgaffil1tid" value="11545">
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
<input type="hidden" name="orgaffil1tid" value="717">
<% } %>
			  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;"  bgcolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF">
<tr><td colspan=4>
			<A HREF="http://<%=aszSubdomain%>">
				<img src= "http://www.ChristianVolunteering.org/imgs/logo_final_ad400.gif" width="400" height="81" border="0"/></A>


</td></tr>			    
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
			      <td align="right"><strong>Service<br />
			        Area</strong></td>
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
				</SELECT>
</td>
		        </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><input type="image" name="imageField" src="/imgs/button_search_clr.gif" border="0" width="59" height="24" />
			      </td>
			    </tr>
			  </table>
			</form>

			<p><b>&nbsp;in body : </b></p>
<p>			
			&lt;form id=&quot;homesearch&quot; method=&quot;get&quot; action=&quot;http://<%=aszSubdomain%>/oppsrch.do&quot;&gt;
		
			<br>
			&lt;input type=&quot;hidden&quot; name=&quot;method&quot; value=&quot;processOppSearchAll&quot;&gt;<br>
			&lt;input type=&quot;hidden&quot; name=&quot;distance&quot; value=&quot;City&quot;&gt;<br>
	<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")) { %>
	&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;1069&quot;&gt;<br>
<% }else if (aszHostID.equalsIgnoreCase( "volengabs" )) { %>
&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;11545&quot;&gt;<br>
<% }else if (aszHostID.equalsIgnoreCase( "volengagrm" )) { %>
&lt;input type=&quot;hidden&quot; name=&quot;orgaffil1tid&quot; value=&quot;717&quot;&gt;<br>
<% } %>
			&lt;table width=&quot;300&quot; cellpadding=&quot;0&quot; cellspacing=&quot;0&quot; style=&quot;font-size: 
			12px;&quot; height=&quot;97&quot; id=&quot;table3&quot; bgcolor=&quot;#FFFFFF&quot; bordercolorlight=&quot;#FFFFFF&quot; 
			bordercolordark=&quot;#FFFFFF&quot;&gt;<br>
			&lt;tr&gt; &lt;td colspan=&quot;4&quot;&gt; 
			
			&lt;A HREF=&quot;http://<%=aszSubdomain%>&quot;&gt;&lt;img src= 
			&quot;http://www.ChristianVolunteering.org/imgs/logo_final_ad400.gif&quot; 
			width=&quot;400&quot; height=&quot;81&quot; border=&quot;0&quot;/&gt;&lt;/A&gt;
		
		&lt;/td&gt&lt;/tr&gt;<br>
			&lt;tr&gt;<br>
			&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Postal Code&lt;/strong&gt;&lt;/td&gt;<br>
			&lt;td width=&quot;78&quot;&gt;&lt;input name=&quot;postalcode&quot; type=&quot;text&quot; id=&quot;postalcode&quot; 
			style=&quot;width:60px;&quot; /&gt;&lt;/td&gt;<br>
			&lt;td width=&quot;60&quot;&gt;&lt;strong&gt;Distance&lt;/strong&gt;&lt;/td&gt;<br>
			&lt;td height=&quot;26&quot; width=&quot;103&quot;&gt;&lt;select id=&quot;distance&quot; 
			name=&quot;distance&quot; style=&quot;width:80px; z-index: -1&quot;&gt;<br>
			&lt;option value=&quot;5 miles (8K)&quot;&gt;5 miles (8K)&lt;/option&gt;<br>
			&lt;option value=&quot;20 miles (32K)&quot; selected=&quot;selected&quot;&gt;20 miles (32K)&lt;/option&gt;<br>
			&lt;option value=&quot;City&quot; selected=&quot;selected&quot;&gt;City&lt;/option&gt;<br>
			&lt;option value=&quot;Region&quot;&gt;Region&lt;/option&gt;<br>
			&lt;option value=&quot;Country&quot;&gt;Country&lt;/option&gt;<br>
			&lt;option value=&quot;Virtual&quot;&gt;Virtual&lt;/option&gt;<br>
			&lt;/select&gt;&lt;/td&gt;<br>
			&lt;/tr&gt;<br>
			&lt;tr&gt;<br>
			&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Service&lt;br /&gt;<br>
			Area&lt;/strong&gt;&lt;/td&gt;<br>
			&lt;td colspan=&quot;3&quot; height=&quot;36&quot;&gt;<br>
			<br>
			&lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot;style=&quot;width: 215px; //width:207px; z-index: 0;&quot;&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
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
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Disabilities Outreach&lt;/option&gt <br>");
							}else if (iSubType == 4774){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Food Service / Hunger&lt;/option&gt <br>");
							}else if (iSubType == 4782){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Prison Outreach&lt;/option&gt ");
							}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
							}
						}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
						}
					}
				%>
&lt;/SELECT&gt;<br />
<p>&lt;/td&gt;<br>
  &lt;/tr&gt;<br>
  &lt;tr&gt;<br>
  &lt;td width=&quot;59&quot;&gt;&amp;nbsp;&lt;/td&gt;<br>
  &lt;td colspan=&quot;3&quot; height=&quot;35&quot;&gt;
		  
		  &lt;input type=&quot;image&quot; name=&quot;imageField&quot; 
		  src=&quot;http://www.christianvolunteering.org/imgs/button_search_clr.gif&quot; 
		  border=&quot;0&quot; width=&quot;65&quot; height=&quot;26&quot; /&gt;
		  
		  <br>
  &lt;/a&gt;&lt;/td&gt;<br>
  &lt;/tr&gt;<br>
  &lt;/table&gt;<br>
  &lt;/form&gt; 
</p>		  
        <div id="boston" style="<%=aszBoston%>">

<hr width="100%"> <a name= "boston_browse"></a><h3>Boston Form - Browsing options</h3><br><hr width="100%">

<form id="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<A HREF="http://newengland.christianvolunteering.org">
<img border="0" src="/imgs/logo_final_ad_clr300.gif" width="288" height="58"></A>
<table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;" height="109" id="table3">
<!-- MSTableType="layout" -->
<tr>
<td align="right"><strong>Postal Code</strong></td>
<td width="71"><input name="postalcode" type="text" id="postalcode" style="width:60px;" /></td>
<td width="55"><strong>Distance</strong></td>
<td height="30" width="94"><select id="distance" name="distance" style="width:80px; z-index: -1">
<option value="5 miles (8K)">5 miles (8K)</option>
<option value="20 miles (32K)">20 miles (32K)</option>
<option value="City" selected="selected">City</option>
<option value="Region">Region</option>
<option value="Country">Country</option>
<option value="Virtual">Virtual</option>
</select></td>
</tr>
<tr>
<td align="right"><strong>Service<br />
Area</strong></td>
<td colspan="3" height="30">

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

	  </SELECT>
</td>
</tr>
<tr>
<td width="55">&nbsp;</td>
<td colspan="3" height="29"> <input type="image" name="imageField" src="/imgs/button_search_clr.gif" border="0" width="65" height="26" />
</a></td>
</tr>
</table>


<h2><p>Browse by Type</p></h2>

	<p><A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=4795&postalcode=&distance=City&requiredcodedesc=&Submit=Submit">
	Virtual Opportunities</A> <br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=
	City&greatforgroup=479&Submit=Submit">
	Group Opportunities</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit">
	Short Term Missions Opportunities</A></p>
	<h2><p>	Browse by Location</p></h2>
	<p><A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=MA&Submit=Submit">
	Massachusetts</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=NH&Submit=Submit">
	New Hampshire</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=VT&Submit=Submit">
	Vermont</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=CT&Submit=Submit">
	Connecticut</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=ME&Submit=Submit">
	Maine</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&state=RI&Submit=Submit">
	Rhode Island</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&Submit=Submit">
	Boston</A><br>
	<A HREF="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=01109&distance=City&Submit=Submit">
	Springfield / Worcester</A><br>
	
<h2><p>Browse by Service Area</p></h2>

		<p><a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postalcode=02108
	       &distance=City&servicearea1=4759">
		Administrative</a><br>
	
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=
	       &postalcode=02108&distance=City&servicearea1=4763&Submit=Submit">
		Children and Youth</a><br>
	
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postalcode=02108
			&distance=City&servicearea1=4765&imageField.x=4&imageField.y=12">
		Community Development </a><br>
			
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=
			&postalcode=02108&distance=City&servicearea1=4768&Submit=Submit">
			Education and Literacy </a><br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postalcode
			=02108&distance=City&servicearea1=4770">
		Employment Training </a><br>

		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postalcode=
			02108&distance=City&servicearea1=4774">
		Food Ministry / Hunger </a><br>
	
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&
		servicearea1=4776&Submit=Submit">
		Homelessnessness and Housing</a><br>
	
	
		
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=
		&postalcode=02108&distance=City&servicearea1=4777&Submit=Submit">
		Immigrants and Refugees </a></p>
	
<h2><p>Browse by Skills / Career</p></h2>
	  	
		
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=
		02108&distance=City&skills1id=4720&Submit=Submit">
		Administrator / Office Skills </a><br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance
		=City&skills1id=4723&Submit=Submit">
		Artist (Performing / Creative) </a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=
		City&skills1id=4728&Submit=Submit">
		Computer / Tech Support </a> <br>
			<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=
		City&skills1id=4730&Submit=Submit">
		Computer Trainer </a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&skills1id=4740&Submit=Submit">
		Fundraiser </a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance
		=City&skills1id=4741=&Submit=Submit">
		Grant / Proposal Writer </a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&skills1id=4753&Submit=Submit">
		Teacher / Trainer</a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearch1&voltype=Both&postalcode=02108&distance=City&skills1id=4756&Submit=Submit">
		Web / Graphics Designer</a> <br>
		<a href="http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&skills1id=4757&Submit=Submit">
		Youth Worker / Childcare</a> </p>	

<p><b>in body:</b></p>
<p>&lt;form id=&quot;homesearch&quot; method=&quot;get&quot; action=&quot;http://newengland.christianvolunteering.org/oppsrch.do&quot;&gt;<br>
&lt;input type=&quot;hidden&quot; name=&quot;method&quot; value=&quot;processOppSearchAll&quot;&gt;<br>
		&lt;input type=&quot;hidden&quot; name=&quot;distance&quot; value=&quot;City&quot;&gt;<br><br />
&lt;table width=&quot;300&quot; border=&quot;0&quot; cellpadding=&quot;0&quot; cellspacing=&quot;5&quot; style=&quot;font-size: 
12px;&quot; height=&quot;97&quot; id=&quot;table3&quot;&gt;<br>
&lt;!-- MSTableType=&quot;layout&quot; --&gt;<br>
&lt;tr&gt;<br>
&lt;A HREF=&quot;http://newengland.christianvolunteering.org&quot;&gt;<br>
&lt;img border=&quot;0&quot; src=&quot;http://www.ChristianVolunteering.org/imgs/logo_final_ad_clr300.gif&quot; 
width=&quot;288&quot; height=&quot;58&quot;&gt;&lt;/A&gt;<br>
&nbsp;&lt;/tr&gt;<br>
&lt;tr&gt;<br>
&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Postal Code&lt;/strong&gt;&lt;/td&gt;<br>
&lt;td width=&quot;71&quot;&gt;&lt;input name=&quot;postalcode&quot; type=&quot;text&quot; id=&quot;postalcode&quot; 
style=&quot;width:60px;&quot; /&gt;&lt;/td&gt;<br>
&lt;td width=&quot;55&quot;&gt;&lt;strong&gt;Distance&lt;/strong&gt;&lt;/td&gt;<br>
&lt;td height=&quot;22&quot; width=&quot;94&quot;&gt;&lt;select id=&quot;distance&quot; name=&quot;distance&quot; 
style=&quot;width:80px; z-index: -1&quot;&gt;<br>
&lt;option value=&quot;5 miles (8K)&quot;&gt;5 miles (8K)&lt;/option&gt;<br>
&lt;option value=&quot;20 miles (32K)&quot;&gt;20 miles (32K)&lt;/option&gt;<br>
&lt;option value=&quot;City&quot; selected=&quot;selected&quot;&gt;City&lt;/option&gt;<br>
&lt;option value=&quot;Region&quot;&gt;Region&lt;/option&gt;<br>
&lt;option value=&quot;Country&quot;&gt;Country&lt;/option&gt;<br>
&lt;option value=&quot;Virtual&quot;&gt;Virtual&lt;/option&gt;<br>
&lt;/select&gt;&lt;/td&gt;<br>
&lt;/tr&gt;<br>
&lt;tr&gt;<br>
&lt;td align=&quot;right&quot;&gt;&lt;strong&gt;Service&lt;br /&gt;<br>
Area&lt;/strong&gt;&lt;/td&gt;<br>
&lt;td colspan=&quot;3&quot; height=&quot;30&quot;&gt;<br>
<br>
			&lt;SELECT id=&quot;servicearea1&quot; name=&quot;servicearea1&quot;style=&quot;width: 215px; //width:207px; z-index: 0;&quot;&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;option value=&quot;&quot; selected=&quot;selected&quot;&gt;&lt;/option&gt; <br />
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
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Disabilities Outreach&lt;/option&gt <br>");
							}else if (iSubType == 4774){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Food Service / Hunger&lt;/option&gt <br>");
							}else if (iSubType == 4782){
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;Prison Outreach&lt;/option&gt ");
							}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
							}
						}else{
								out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;option value=&quot;" + aAppCode.getAPCIdSubType() + "&quot; " );
								out.println(" &gt;" + aAppCode.getAPCDisplay() + "&lt;/option&gt <br>");
						}
					}
				%>
&lt;/SELECT&gt;<br />
&lt;/td&gt;<br>
&lt;/tr&gt;<br>
&lt;tr&gt;<br>
&lt;td width=&quot;55&quot;&gt;&amp;nbsp;&lt;/td&gt;<br>
&lt;td colspan=&quot;3&quot; height=&quot;25&quot;&gt; &lt;input type=&quot;image&quot; name=&quot;imageField&quot; 
src=&quot;http://www.christianvolunteering.org/imgs/button_search_clr.gif&quot; border=&quot;0&quot; 
width=&quot;65&quot; height=&quot;26&quot; /&gt;<br>
&lt;/a&gt;&lt;/td&gt;<br>
&lt;/tr&gt;<br>
&lt;/table&gt;<br>
<br>
<br>
&lt;h2&gt;&lt;p&gt;Browse by Type&lt;/p&gt;&lt;/h2&gt;<br>
<br>
&lt;p&gt;&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
4795&amp;postalcode=&amp;distance=City&amp;category1=&amp; requiredcodedesc=&amp;Submit=Submit&quot;&gt;<br>
Virtual Opportunities&lt;/A&gt; &lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=&amp;<br>
postalcode=02108&amp;distance=City&amp;category1=&amp;focusare4=Yes&amp;prov=&amp;country=&amp;affiliation=&amp;Submit=<br>
Submit&quot;&gt;<br>
Group Opportunities&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
4796&amp;postalcode=&amp;distance=City&amp;category1=&amp;prov=&amp;<br>
country<br>
=&amp;affiliation=&amp;Submit=Submit&quot;&gt;<br>
Short Term Missions Opportunities&lt;/A&gt;&lt;/p&gt;<br>
&lt;h2&gt;&lt;p&gt; Browse by Location&lt;/p&gt;&lt;/h2&gt;<br>
&lt;p&gt;&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=MA&amp;Submit=Submit&quot;&gt;<br>
Massachusetts&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=NH&amp;Submit=Submit&quot;&gt;<br>
New Hampshire&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=VT&amp;Submit=Submit&quot;&gt;<br>
Vermont&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=CT&amp;Submit=Submit&quot;&gt;<br>
Connecticut&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=ME&amp;Submit=Submit&quot;&gt;<br>
Maine&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;state=RI&amp;Submit=Submit&quot;&gt;<br>
Rhode Island&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;Submit=Submit&quot;&gt;<br>
Boston&lt;/A&gt;&lt;br&gt;<br>
&lt;A 
HREF=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=01109&amp;distance=City&amp;Submit=Submit&quot;&gt;<br>
Springfield / Worcester&lt;/A&gt;&lt;br&gt;<br>
<br>
&lt;h2&gt;&lt;p&gt;Browse by Service Area&lt;/p&gt;&lt;/h2&gt;<br>
<br>
&lt;p&gt;&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;<br>
postalcode=02108<br>
&amp;distance=City&amp;category1=Administrative&amp;imageField.x=41&amp;imageField.y=4&quot;&gt;<br>
Administrative&lt;/a&gt;&lt;br&gt;<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;category1=Children+and+Youth&amp;Submit=Submit&quot;&gt;<br>
Children and Youth&lt;/a&gt;&lt;br&gt;<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postalcode<br>
=02108<br>
&amp;distance=City&amp;category1=Community+Development&amp;imageField.x=4&amp;imageField.y=12&quot;&gt;<br>
Community Development &lt;/a&gt;&lt;br&gt;<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;category1=Education+and+Literacy&amp;Submit=Submit&quot;&gt;<br>
Education and Literacy &lt;/a&gt;&lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postalcode<br>
=02108&amp;distance=City&amp;category1=Employment+Training&amp;imageField.x=31&amp;imageField.y=10&quot;&gt;<br>
Employment Training &lt;/a&gt;&lt;br&gt;<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postalcode=<br>
02108&amp;distance=City&amp;category1=Food+Ministry+%2F+Hunger&amp;imageField.x=49&amp;imageField.y=11&quot;&gt;<br>
Food Ministry / Hunger &lt;/a&gt;&lt;br&gt;<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;category1=Homelessness+and+Housing&amp;Submit=Submit&quot;&gt;<br>
Homelessnessness and Housing&lt;/a&gt;&lt;br&gt;<br>
<br>
<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;category1=Immigrants+and+Refugees&amp;Submit=Submit&quot;&gt;<br>
Immigrants and Refugees &lt;/a&gt;&lt;/p&gt;<br>
<br>
&lt;h2&gt;&lt;p&gt;Browse by Skills / Career&lt;/p&gt;&lt;/h2&gt;<br>
<br>
<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Administrator+%2F+Office+Skills&amp;Submit=Submit&quot;&gt;<br>
Administrator / Office Skills &lt;/a&gt;&lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Artist+%28Performing%2FCreative%29&amp;Submit<br>
=Submit&quot;&gt;<br>
Artist (Performing / Creative) &lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;category1=&amp;skills1=Computer+%2F+Tech+Support<br>
&amp;affiliation=&amp;Submit=Submit&quot;&gt;<br>
Computer / Tech Support &lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Computer+Trainer&amp;affiliation=&amp;Submit=Submit&quot;&gt;<br>
Computer Trainer &lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Fundraiser&amp;requiredcodedesc=&amp;Submit=Submit&quot;&gt;<br>
Fundraiser &lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Grant+%2F+Proposal+Writer&amp;Submit=Submit&quot;&gt;<br>
Grant / Proposal Writer &lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Teacher+%2F+Trainer&amp;Submit=Submit&quot;&gt;<br>
Teacher / Trainer&lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearch1&amp;postype=Both<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Web+%2F+Graphics+Designer&amp;Submit=Submit&quot;&gt;<br>
Web / Graphics Designer&lt;/a&gt; &lt;br&gt;<br>
&lt;a 
href=&quot;http://newengland.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&amp;postype=<br>
&amp;postalcode=02108&amp;distance=City&amp;skills1=Youth+Worker+%2F+Childcare&amp;Submit=Submit&quot;&gt;<br>
Youth Worker / Childcare&lt;/a&gt; &lt;/p&gt; <br>
&nbsp;</p>

</div>

</div>
</div>


<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->