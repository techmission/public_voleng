<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>ChristianVolunteering.org: Login to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers</title>

<% } else { %>

<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<% 
session.putValue ("MyIdentifier1","");  // Initialize Value into session Object
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Log In </span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<html:form action="/register.do" >
<html:hidden property="method" value="processLogin" />

<!-- BEGIN MAINCONTENT -->
<div id="maincontent">

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
 <h2 style="margin-bottom: 0px;">Login or Register</h2>
         <hr color="#CCCCCC" size="1" style="margin-top: 3px;">
          <p><b>If you do not yet have an account you can 
			<a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;</b>
          If you have already registered with <%=aszSubdomain%>, enter your email and password below. </p>
    
	
	
    <div style="width:330px; //width:350px; padding-left:180px;">
    <div style="//width: 300px;  float:left;">
    <p align="right"><b>Email</b>
    <html:text property="email1addr" size="25" maxlength="50" styleClass="textinputwhite" />
	<p align="right">Password
	<html:password property="password1" size="25" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
     </div> 
      
     <div style="clear:both"></div>   

        <FONT color="red"><center><bean:write name="individualForm" property="errormsg" /></center></FONT>

    	<p style="position:absolute; padding:15 0 10 10; margin:0;"><INPUT class=submit type=submit value="Log in" style="float: left; margin-top:0px; //margin-top: 10px;">
       &nbsp;or&nbsp;use&nbsp;</p>
<%@ include file="/template_include/gigya_loginForm.inc" %>

      </div>	
	
	

<br/><b>NOTE:</b> 
<% if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
			This section of the RoundTripMissions site uses a separate account on our partner site ChristianVolunteering.org, which requires a separate login.
			<br />
<% } %>
If you have an account with 
<a href="http://www.urbanministry.org">UrbanMinistry.org</a>
<% if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
 
<% } %>, please log in with the email and password you have provided on that account.		
	
	        Forgot your 
<a href= "http://www.urbanministry.org/user/password">password</A>? 
			Other <a href="mailto:<%=aszEmailHost%>">login problems</A>?
	        Email <a href="mailto:<%=aszEmailHost%>"><%=aszEmailHost%></a> 
<br>	

          <div class="volbox_wrapper">
          <div class="volbox">
            <h2 align="center"><a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Volunteers</a></h2>
               <p>Register for free! You can:</p>
								<ul>
    							<li>Search volunteer opportunities</li>
   					 			<li>Sign up for e-mail alerts</li>

                  <li>Invite your friends to volunteer</li>
                  </ul>
                                <p align="center"><b><a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Now!</a>&nbsp;</b><br />

            
          </div>
          </div>


           <div class="npbox_wrapper">
           <div class="npbox">
            <h2 align="center"><a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Nonprofits</a></h2>
            <p>Register for free! Who can register?</p>
<ul>
    							<li>Nonprofits</li>

   					 			<li>Churches</li>
                                <li>Parachurch Ministries</li>
                  </ul>

            <p align="center">
            <b><a href="<%=aszPortal%>/individualregistration.jsp" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Now!</a><a href="http://www.volunteermatch.org/post/register/np/overview.jsp" title="Nonprofit Registration" ></a></b>

            <br />
            [<a style="font-size:.8em; color:#000000;" href="<%=aszPortal%>/recruitvolunteers.jsp">Learn More</a>]            </p>
            <p></p>
          </div>
          </div>
       

</html:form>

</div>

    

    </div>
    </div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
