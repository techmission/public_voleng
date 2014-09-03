<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %><head>
<title>Christian Volunteer Network: Search for Christian Volunteer Opportunities</title>

<% } else { %>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<% 
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getAppCodeList( aServiceList, 161 );
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">volunteer</span>
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
<span style="float: left;">volunteer</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/oppsrch.do?method=showSearch">volunteer</a> &gt; getting started  </div>
</div>
<% } %>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="/imgs/volunteer_photo.gif" width="294" height="231" alt="Find short term mission trips, youth mission trips, junior high mission trips, and adult mission trips"/></td>
    <td valign="top" class="intsplash">
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
    <h1>Let's Get Started</h1>
	Get into action by volunteering through <%=aszSubdomain%>. We can help you find opportunities to 
	create a website, tutor kids, feed the hungry, do short term volunteer work, 
	and much more! You can serve as a local volunteer, virtual volunteer, 
	volunteer intern, or short term volunteer. Sign up to begin making a difference 
	in the lives of others.
<% }else{ %>
    <h1>Christian volunteers and short term urban mission trips</h1>
	Put your faith into action by volunteering through <%=aszSubdomain%>. We can help you find opportunities to 
	create a website, tutor kids, feed the hungry, do a short term mission trip, and much more! You can serve as a local 
	volunteer, virtual volunteer, volunteer intern, or on a short term missions trip. Sign up to begin making a difference 
	in the lives of others.
<% } %>
      </td>
  </tr>
</table>
</div>

<div id="body">
       
	<TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 id="table2" height="433" > <!-- width="540" -->
        <tr>
          <TD width="328"><img src="/imgs/blank.gif" width="245" height="1"></TD>
          	<td height="5" width="214"></td>
		</tr>
		<tr>
          <TD valign=top colspan=2 height="428">
            <h3>Simple Steps to Start Volunteering</h3> <br>
           	<dl>
				<dt>Follow these steps and you'll be volunteering in no time!</dt>
			</dl>
            <ul>
				<li>
				<div align="left">
					Begin your 
					search below by entering 
            your Postal Code and/or interest area.</div>
				</li>
				<li>
				<div align="left">
					Find a listing that 
				interests you and click on its title for additional details. 
				</div>
				</li>
				<li>
				<div align="left">
					When you find an opportunity that you want to take part in, 
					click "I want to volunteer" (you must be <a href="<%=aszPortal%>/individualregistration.jsp" title="Register to find Christian Volunteer Opportunities">registered</a> to use this feature). 
				<li>
				<div align="left">	
An email will be sent to the organization with your contact information (and resume, if you submitted one). 
</div>
				</li>
<li>
				<div align="left">	

<a href="<%=aszPortal%>/individualregistration.jsp" 
	title="Register to find <% if(!(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>Christian <% } %>Volunteer Opportunities">Register</a> as a <%=aszSubdomain%> member to contact organizations and receive future benefits.  
</div>
				</li>
			</ul>
            <dl>
				<dt>It's that easy!</dt>
			</dl>

			<form id="homesearch" NAME="homesearch" method="get" action="<%=aszPortal%>/oppsrch.do">
			<input type="hidden" name="method" value="processOppSearchAll">
			<input type="hidden" name="voltype" value="">
		<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %>
			<input type="hidden" name="requiredcodetype" value="No">
		<% } %>
			<% if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){ %>
				<input type="hidden" name="partner" value="Salvation Army">
			<% } %>
			  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 12px;">
			    <tr>
			      <td width="55" align="right"><strong>Postal Code</strong></td>
			      <td><input name="postalcode" type="text" id="postalcode" style="width:60px;" /></td>
			      <td><strong>Distance</strong></td>
			      <td><select id="distance" name="distance" style="width:80px;"><!--  z-index: -1 -->
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
					<SELECT id="category1" name="category1" style="width: 215px;> <!-- //width:207px; z-index: 0;"-->
							<option value="" selected="selected">Select Categories</option> 
							<%
				 if(aszSecondaryHost.equalsIgnoreCase("volengivol")) {
					for(int index=0; index < aServiceList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getAPCDisplay();
						if ((aszOptRqCode.equalsIgnoreCase("Bible Study")) || 
							(aszOptRqCode.equalsIgnoreCase("Church Planting")) ||
							(aszOptRqCode.equalsIgnoreCase("Evangelism")) ||
							(aszOptRqCode.equalsIgnoreCase("Family / Adults Ministry")) ||
							(aszOptRqCode.equalsIgnoreCase("Single Parents / Crisis Pregnancy")) ||
							(aszOptRqCode.equalsIgnoreCase("Vacation Bible School")) ||
							(aszOptRqCode.equalsIgnoreCase("Women's Ministry"))){
						}else if (aszOptRqCode.equalsIgnoreCase("Disabilities Ministry")){
							out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >Disabilities Service</option> ");
						}else if (aszOptRqCode.equalsIgnoreCase("Food Ministry / Hunger")){
							out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >Food Service / Hunger</option> ");
						}else if (aszOptRqCode.equalsIgnoreCase("Prison Ministry")){
							out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >Prison Service</option> ");
						}else{
							out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
						}
					}
				} else {
					for(int index=0; index < aServiceList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
						if(null == aAppCode) continue;
						out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
							%>
					</SELECT></td>
			      </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><input type="image" name="imageField" src="/imgs/button_search_clr.gif" border="0" width="59" height="24" title="Search for Christian Volunteer Opportunties"/>
			      </td>
			    </tr>
			  </table>
			</form>


				<!--webbot bot="SaveResults" U-File="../_private/form_results.csv" S-Format="TEXT/CSV" S-Label-Fields="TRUE" startspan --><input TYPE="hidden" NAME="VTI-GROUP" VALUE="0"><!--webbot bot="SaveResults" i-checksum="43374" endspan -->
          </TD>
        </tr>
      </table>
</div>
</div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
