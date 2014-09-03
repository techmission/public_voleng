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

<% String aszGoalPage = "/login.jsp"; %>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>

<% 
session.putValue ("MyIdentifier1","");  // Initialize Value into session Object
        String aszRedirect = "";
        try{
        	if(request.getParameter("redirect") !=null){
        		aszRedirect=request.getParameter("redirect").toString();
        	}else{
        		if(aszReferer != null){
        			if(aszReferer.length()>0){
        				//out.print("referer is "+aszReferer);
        				if(aszReferer.contains("/orgs/")){
        					aszRedirect=aszReferer.substring(aszReferer.indexOf("/orgs"));
            				//out.print(" aszRedirect is "+aszRedirect);
        				}else  if(aszReferer.contains("/org/")){
        					aszRedirect=aszReferer.substring(aszReferer.indexOf("/org"));
            				//out.print(" aszRedirect is "+aszRedirect);
        				}

        			}
        		}
        	}
        }catch(NullPointerException e){}
String aszCreateAccountLink = "individualregistration.jsp";
String aszRedirectApply = "";
if(aszRedirect.endsWith("-apply")){
	aszRedirectApply = "&redirect="+aszRedirect;
//	aszCreateAccountLink = "register.do?method=showCreateAccount1" + aszRedirectApply;
}


%>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Log In</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Log In </span>
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
 <div style="display:none;">
 redirect is <%=aszRedirect %>
 <%
 %>
 </div>
      <br/> 
      	<div id="page_base">
 <h2 style="margin-bottom: 0px;">Login or Register</h2>
         <hr color="#CCCCCC" size="1" style="margin-top: 3px;">
          <p><b>If you do not yet have an account you can 
			<a href="<%=aszPortal%>/<%=aszCreateAccountLink%>" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Here</a>.&nbsp;</b>
          If you have already registered with <%=aszSubdomain%>, enter your email and password below. </p>
    
	
    <div id="login_page_block">
<form method="post" name="individualForm" action="<%=aszPortal%>/register.do<%if(aszPortal.length()>0){%>?method=processLogin<%}%>" >
<input type="hidden" name="method" value="processLogin" />
<input type="hidden" name="redirect" value="<%=aszRedirect%>"/>
	
    <div style="float:left;">
    <p align="right"><b>Email</b>
    <input type="text" name="email1addr" size="25" maxlength="50" styleClass="textinputwhite" />
	<p align="right">Password
	<input type="password" name="password1" size="25" maxlength="50"  redisplay="false" styleClass="textinputwhite" />
     </div> 
      
     <div style="clear:both"></div>   


<% 
String aszMethod = "";
if(request.getParameter("method")!=null){
	if(request.getParameter("method").length()>0){
		aszMethod=request.getParameter("method");
	}
}

if(
	aszHostID.equalsIgnoreCase("volengivol") ||
	aszHostID.equalsIgnoreCase("volengchurch") ||
	aszHostID.equalsIgnoreCase("voleng1") ||
	aszHostID.equalsIgnoreCase("volenggospelcom") ||
	aszHostID.equalsIgnoreCase("volenggospel")  ||
	aszHostID.equalsIgnoreCase("volengmobile") 
	
){
 %>
    	<p id="oauth_connectors">
          <input class=submit type=submit value="Log in" style="float:left; margin-top:0px;" />
          <% if(! aszMethod.equals("showCreateApplication")){ %>
          <a href="<%=request.getContextPath()%>/register.do?method=authorizeLinkedinConnectClick<%=aszRedirectApply%>"><img src="http://www.christianvolunteering.org/imgs/linkedin_login_button.png" /></a>
          <a href="<%=request.getContextPath()%>/register.do?method=authorizeFacebookConnectClick<%=aszRedirectApply%>"><img src="http://www.christianvolunteering.org/imgs/facebook_login_button.png" /></a>
          <% } %> 
       </p>
       <br></br>
<% }else{ %>
<center>
<INPUT class=submit type=submit value="Log in" style="margin-top:0px;">
</center>
<br /><br />
<% } %>
</form>	
<html:form action="/register.do" >
        <FONT color="red"><center><bean:write name="individualForm" property="errormsg" /></center></FONT>
</html:form>
      </div>	
	

<br/><b>NOTE:</b> 
<% if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
			This section of the RoundTripMissions site uses a separate account on our partner site ChristianVolunteering.org, which requires a separate login.
			<br />
<% } %>
If you have an account with 
<a href="http://www.urbanministry.org">UrbanMinistry.org</a>
<% if(aszHostID.equalsIgnoreCase( "volengccda" )){ %>
 
<% } %>, please log in with the email and password you have provided on that account.  &nbsp;
	
	        Need <a href="http://www.urbanministry.org/help">help</a>?  &nbsp;
	        Forgot your 
<a href= "http://www.urbanministry.org/user/password">password</A>? 
<!--			Other <a href="mailto:<%=aszEmailHost%>">login problems</A>?
	        Email <a href="mailto:<%=aszEmailHost%>"><%=aszSubdomain%></a>  -->
<br>	

          <div class="volbox_wrapper">
          <div class="volbox">
            <h2 align="center"><a href="<%=aszPortal%>/<%=aszCreateAccountLink%>" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Volunteers</a></h2>
               <p>Register for free! You can:</p>
								<ul>
    							<li>Search volunteer opportunities</li>
   					 			<li>Sign up for e-mail alerts</li>

                  <li>Invite your friends to volunteer</li>
                  </ul>
                                <p align="center"><b><a href="<%=aszPortal%>/<%=aszCreateAccountLink%>" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Now!</a>&nbsp;</b><br />

            
          </div>
          </div>


           <div class="npbox_wrapper">
           <div class="npbox">
            <h2 align="center"><a href="<%=aszPortal%>/<%=aszCreateAccountLink%>" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Nonprofits</a></h2>
            <p>Register for free! Who can register?</p>
<ul>
    							<li>Nonprofits</li>

   					 			<li>Churches</li>
                                <li>Parachurch Ministries</li>
                  </ul>

            <p align="center">
            <b><a href="<%=aszPortal%>/<%=aszCreateAccountLink%>" title="Register Here to Recruit Christian Volunteers, Short Term Urban Missionaries, or Virtual Volunteers">Register Now!</a><a href="http://www.volunteermatch.org/post/register/np/overview.jsp" title="Nonprofit Registration" ></a></b>

            <br />
            [<a style="font-size:.8em; color:#000000;" href="<%=aszPortal%>/recruitvolunteers.jsp">Learn More</a>]            </p>
            <p></p>
          </div>
          </div>

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
