<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->


<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>

<title>ChristianVolunteering.org: Login to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers</title>
<link rel="canonical" href="http://www.christianvolunteering.org/login.jsp" />

<% } else { %>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style>
#fb-login-li{padding:10px; //padding:15px 0 10px 80px; //width:320px; margin-left:70px; //margin-left:0; margin-top:5px;}
#fb-login-li a{line-height:1.3em;}
</style>
<% 
session.putValue ("MyIdentifier1","");  // Initialize Value into session Object
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Post a Need</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Post a Need </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>


<div id="chrisvolTopPartnerMenu">

<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">Log In </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; log in  </div>
</div>
<% } %>

 <div id="body">
      <br/> 
      	<div id="page_base">
 <h2 style="margin-bottom: 0px;">Register</h2>
         <hr color="#CCCCCC" size="1" style="margin-top: 3px;">
          <p>
          Register here for an account to post Disaster Relief Volunteer Opportunities for Hurricane Sandy. </p>


<html:form action="/register.do" >
        <FONT color="red"><center><bean:write name="individualForm" property="errormsg" /></center></FONT>
</html:form>
    
	
    <div style="width:330px; //width:220px; padding-left:180px; //padding-left:0px; //margin-left:150px;">
    
    
    
    
<form action="<%=aszPortal%>/register.do" name="individualForm" method="post">
<input type="hidden" value="addBrandNewHurrSandyOrgContact" name="method">
<input type="hidden" value="www.HurricaneSandyVolunteer.org" name="subdomain">
<input type="hidden" value="Organization" name="siteusetype">
<input type="hidden" value="494934" name="orgnid">
	
<input type="hidden" value="8865" name="volunteertid">


            <!--nested table -->
            <table cellspacing="0" cellpadding="0" border="0" align="center" width="80%" id="splash">
              <!-- MSTableType="layout" -->
				
			  <tbody><tr>
                <td width="130">Username <span class="criticaltext">*</span></td>
                <td>
                <input type="text" styleclass="textinputwhite" size="30" styleid="username" name="username">
                </td>
					<td height="23"></td>
				</tr>
				<tr>
        		<td>Email Address <span class="criticaltext">*</span></td>
       			<td height="23"><input type="text" styleclass="textinputwhite" size="35" styleid="email1addr" name="email1addr"></td>
       			</tr>
				<tr>
                <td>New Password <span class="criticaltext">*</span> </td>
                <td height="23" colspan="2">
					<input type="password" redisplay="false" styleclass="textinputwhite" size="25" name="password1">
                </td>
             		</tr>
				<tr>
                <td>Confirm Password <span class="criticaltext">*</span> </td>
                <td height="23" colspan="2">
					<input type="password" redisplay="false" styleclass="textinputwhite" size="25" name="password2">
                </td>
					</tr>



				<tr>
                <td>First name <span class="criticaltext">*</span></td>
                <td height="23"><input type="text" styleclass="textinputwhite" size="25" styleid="namefirst" name="namefirst"></td>
				</tr>
				<tr>
                <td>Last name <span class="criticaltext">*</span></td>
                <td height="23"><input type="text" styleclass="textinputwhite" size="25" styleid="namelast" name="namelast"></td>
              		</tr>
              	

<tr><td colspan="3">
<table cellspacing="0" cellpadding="0" border="0" width="540" id="splash">




<tbody><tr>
              		<td width="40">&nbsp;</td>
              		<td width="90">&nbsp;</td>
                <td height="75">
                  <div style="HEIGHT: 10px" class="clear"></div>
                  <input type="hidden" name="submit"> <input type="submit" value="Continue" class="submit"> 
                 </td>
             		</tr>
				<tr>
                <td colspan="4">&nbsp;<span class="criticaltext">*</span>  Required Item</td>
              		<td height="21"></td>
				</tr>

            </tbody></table>
</td></tr>
</tbody></table>

</form>	



      </div>	
	
<br>	
</div>

    

    </div>
    </div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
