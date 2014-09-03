<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Register to Recruit Volunteers</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent"><div id="pagebanner">

<span style="float: left;">register to recruit volunteers</span><img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/org.do?method=showOrgStart">organization</a> &gt; getting started  </div>
</div>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="/imgs/pic/blowing.jpg" width="245" height="204" 
    <% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ) { %>
    title="Register Christian volunteer, church volunteer, urban ministry service, mission trips, and virtual volunteer opportunities online"
    <% } %>
     ></td>
    <td valign="top" class="intsplash"><h1>
	Register with ChristianVolunteering.org</h1>
      <p LANG="en-US" STYLE="margin-bottom: 0in">Let us help you find 
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ) { %>Christian<% } %> volunteers to come beside you in 
      transforming urban communities. Post your volunteer needs online by completing our quick and easy registration 
      process. Join other organizations which are benefiting from dedicated volunteers. Register today to begin!</p>
	</td>
  </tr>
</table>
</div>
       <div id="body">
			 
			  <TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 >
        <TBODY>
        <TR>
          
          <TD valign="top">
         
      
            <dl>

			</dl>
            </A>
          </TD>
        </TR>
        <TR>
          <TD valign=top colspan=2>
            <dl>
				<dt>
            Becoming a <%=aszSubdomain%> member is not only a great idea; it takes less than 15 minutes. 
            Registration is available free for all 
<% if(! (aszSecondaryHost.equalsIgnoreCase("volengivol")) ) { %>Churches, ministries, <% } %>nonprofit and tax-exempt charitable 
            organizations and includes the following steps:</dt>
			</dl>
			</TD>
        </TR>
        <TR>
          <TD valign=top colspan=2>
            <h2>Simple Steps to Register</h2>
           	<dl>
				<dt>Follow these steps and you'll have volunteers in no time!</dt>
			</dl>
            <div align="left">
				<ol>
					<li>Login or create a personal account. </li>
					<li>Enter organization name, zip code, and country.</li>
					<li>Enter organization contact information and description.</li>
					<li>Begin listing your 
				volunteering opportunities. </li>
				</ol></div>
            <dl>
				<dt>It's that easy!</dt>
			</dl>
			<form method="POST" action="<%=aszPortal%>/org.do?method=showCreateOrgStep1">
				<input type="submit" value="Register" name="B3" title="Register here to recruit Christian volunteers, church volunteers, short term urban missionaries, and urban ministry volunteers">
			</form>
          </TD>
        </TR>
      </table>
</div>
</div>


<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
