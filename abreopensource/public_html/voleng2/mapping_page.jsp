<jsp:useBean id="LoginBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />

<%
String portal="";
String aszRedirectAttribute = "";
String aszRedirecting = "";
String aszRedirect = "";

String aszCompleteRedirectURL="";

String aszHostDomain = request.getHeader("host");
int iHurrSandyOrgNID = 494934;

try{
	aszRedirect=request.getAttribute("redirect").toString();
	request.removeAttribute("redirect");
}catch(NullPointerException e){}

try{
	aszRedirectAttribute=request.getAttribute("redirectpage").toString();
	request.removeAttribute("redirectpage");
}catch(NullPointerException e){
}

try{
	aszRedirecting = request.getParameter("maptopage");
}catch(NullPointerException e2){
	aszRedirecting = "";
}

try{
	portal = request.getAttribute("redirectportal").toString();
	request.removeAttribute("redirectportal");
}catch(NullPointerException e3){
	portal = "";
}

String aszGoalGA = "";
String additionalURL="";


if(aszRedirect==null)	aszRedirect = "";
if(aszRedirectAttribute==null)	aszRedirectAttribute = "";
if(aszRedirecting==null)			aszRedirecting = "";
if(portal==null)					portal = "";


out.print("aszRedirectAttribute: "+aszRedirectAttribute+"<BR>");
out.print("aszRedirecting: "+aszRedirecting+"<BR>");
out.print("aszRedirect: "+aszRedirect+"<BR>");
out.print("portal: "+portal+"<BR>");

if(aszRedirectAttribute.length() > 0){
	if(aszRedirectAttribute.equals("CVINTERNAPPLIC")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/cityvision/email.do?method=showCreateApplication";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication";
		}
	}else if(aszRedirectAttribute.equals("CVINTERNAPPLIC2")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/cityvision/email.do?method=showCreateApplication2";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication2";
		}
	}else if(aszRedirectAttribute.equals("CVINTERNAPPLIC3")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/cityvision/email.do?method=showCreateApplication3";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication3";
		}
	}else if(aszRedirectAttribute.equals("CVINTERNRESUMEPOST")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/cityvision/email.do?method=showResumePost";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showResumePost";
		}
	}else if(aszRedirectAttribute.equals("CVINTERNNATLASSOCMANAGE")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/cityvision/associationmanagement.jsp";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/associationmanagement.jsp";
		}
	}else if(aszRedirectAttribute.equals("CVINTERNDQ")){
		aszCompleteRedirectURL="http://www.cityvisioninternships.org/ineligible-city-vision-internship";
		
	}else if(aszRedirectAttribute.equals("cvinternsite")){
		if(request.getHeader("host").contains("chrisvol.org") ){
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/orgmanagement.jsp";
		}else{
			aszCompleteRedirectURL="http://www.christianvolunteering.org/orgmanagement.jsp";
		}
	}else if(aszRedirectAttribute.equals("city_vision_internship_application_legacy")) {
        //out.print("http://www.cityvisioninternships.org/intern/apply");
  	  aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication";
    }else if(aszRedirectAttribute.equals("city_vision_internship_application")) {
  	  aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication";;
    }else if(aszRedirectAttribute.contains("city_vision_internship_application+org_nid=")){
    	String aszOrgNID = "";
    	String aszOppNID = "";
    	int iOrgNIDPosition=43;
    	int iOppNIDPosition=0;
    	iOppNIDPosition = aszRedirectAttribute.indexOf("+opp_nid=");
    	aszOrgNID = aszRedirectAttribute.substring(iOrgNIDPosition, iOppNIDPosition);
    	iOppNIDPosition += 9;
    	aszOppNID = aszRedirectAttribute.substring(iOppNIDPosition, aszRedirectAttribute.length()-1);
    	  aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication&org_nid="+aszOrgNID+"&opp_nid="+aszOppNID;
    	
    }
}

if(aszRedirect.length()>0){
	if(aszRedirect.startsWith("/orgs")){
		out.print(aszRedirect);
if(aszRedirect.indexOf("/")!=0)	aszRedirect="/"+aszRedirect;
		if(request.getHeader("host").contains("churchvol.org") ){
			out.print("http://chrisvol.org:7001/voleng"+aszRedirect);
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng"+aszRedirect;
		}else if(request.getHeader("host").contains("chrisvol.org") ){
			out.print("http://chrisvol.org:7001/voleng"+aszRedirect);
	   		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng"+aszRedirect;
		}else if(request.getHeader("host").contains("staging-") ){
			aszRedirect=aszRedirect.replaceFirst("/voleng", "");
			out.print("http://staging-christianvolunteering.org"+aszRedirect);
	   		aszCompleteRedirectURL="http://staging-christianvolunteering.org"+aszRedirect;
		}else{
			aszRedirect=aszRedirect.replaceFirst("/voleng", "");
			out.print("http://www.christianvolunteering.org"+aszRedirect);
	   		aszCompleteRedirectURL="http://www.christianvolunteering.org"+aszRedirect;
		}
	}else {
		if(aszRedirect.startsWith("cor")){
			try{
				aszRedirect = aszRedirect.substring(3);
			}catch(Exception e){}
		}
		if(aszRedirect.endsWith("-apply")){
			if(! aszRedirect.contains("position")){
				aszRedirect = "-positions-"+aszRedirect;
			}
			aszRedirect = aszRedirect.replaceAll("-","/");
		}
		aszRedirect = aszRedirect.replaceAll("~","/");
if(aszRedirect.indexOf("/")!=0)	aszRedirect="/"+aszRedirect;	
		if(request.getHeader("host").contains("localhost") ){
			out.println("http://localhost:3000"+aszRedirect);
			aszCompleteRedirectURL="http://localhost:3000"+aszRedirect;
		}else if(request.getHeader("host").contains("churchvol.org") ){
			out.println("http://chrisvol.org:3000"+aszRedirect);
			aszCompleteRedirectURL="http://chrisvol.org:3000"+aszRedirect;
		}else if(request.getHeader("host").contains("chrisvol.org") ){
			out.println("http://chrisvol.org:3000"+aszRedirect);
			aszCompleteRedirectURL="http://chrisvol.org:3000"+aszRedirect;
		}else if(request.getHeader("host").contains("staging-") ){
			aszRedirect=aszRedirect.replaceFirst("/voleng", "");
			out.println("http://staging-christianvolunteering.org"+aszRedirect);
		   	aszCompleteRedirectURL="http://staging-christianvolunteering.org"+aszRedirect;
		}else{
			aszRedirect=aszRedirect.replaceFirst("/voleng", "");
			out.println("http://www.christianvolunteering.org"+aszRedirect);
			aszCompleteRedirectURL="http://www.christianvolunteering.org"+aszRedirect;
		}
	}
}else
if(aszRedirecting.length() > 0){
	if(aszRedirecting.equalsIgnoreCase("fbapp")){
		if(request.getHeader("host").contains("ivolunteering.org") || request.getHeader("host").contains("ivol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/worldchanger/register.do?method=showPersonalityTest";
       	}else if(request.getHeader("host").contains("ivol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/worldchanger/register.do?method=showPersonalityTest";
       	}else if(request.getHeader("host").contains("chrisvol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/fycsandbox/register.do?method=showPersonalityTest";
       	}else{
       		aszCompleteRedirectURL="http://apps.facebook.com/find-your-calling/register.do?method=showPersonalityTest";
		}
	}else if(aszRedirecting.equalsIgnoreCase("urbmindashboard")){
		if(request.getHeader("host").contains("chrisvol.org") ){
       		aszCompleteRedirectURL="http://www.um.org/start";
		}else{
       		aszCompleteRedirectURL="http://www.urbanministry.org/start";
      		}
	}else if(aszRedirecting.equalsIgnoreCase("paidmembership")){
		if(request.getHeader("host").contains("chrisvol.org") ){
			if(	true == LoginBean.IsSessionLoggedIn( request )	){
				aszCompleteRedirectURL="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&id=4";
			}else{ %>
				<jsp:forward page="/register.do?method=showCreateAccount1" />
			<% }
		}else{
			if(	true == LoginBean.IsSessionLoggedIn( request )	){
				aszCompleteRedirectURL="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&id=4";
			}else{ %>
				<jsp:forward page="/register.do?method=showCreateAccount1" />
			<% }
   		}
	}else if(aszRedirecting.equalsIgnoreCase("urbmin")){
		if(request.getHeader("host").contains("chrisvol.org") ){
       		aszCompleteRedirectURL="http://www.um.org";
		}else{
       		aszCompleteRedirectURL="http://www.urbanministry.org";
   		}
	}
}

if(aszRedirectAttribute.length() > 0){
    if(aszRedirectAttribute.equals("hurricanesandy")) {
        out.print(aszHostDomain + "/org.do?method=showOrgAddOpp1");
	  	  aszCompleteRedirectURL= "http://" + aszHostDomain + "/org.do?method=showOrgAddOpp1&orgnid=" + iHurrSandyOrgNID;
	}else if(aszRedirectAttribute.equalsIgnoreCase("landingdisasterreliefhomepage")){
		if(request.getHeader("host").contains("churchvol.org") ){
       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/disasterreliefvolunteer.jsp");
       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/disasterreliefvolunteer.jsp";
   		}else if(request.getHeader("host").contains("chrisvol.org") ){
       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/disasterreliefvolunteer.jsp");
       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/disasterreliefvolunteer.jsp";
   		}else if(request.getHeader("host").contains("staging-christianvolunteering.org") ){
       		out.print("staging-christianvolunteering.org/"+portal+"/disasterreliefvolunteer.jsp");
       		aszCompleteRedirectURL="http://staging-christianvolunteering.org/"+portal+"/disasterreliefvolunteer.jsp";
		}else{
       		out.print("http://www.christianvolunteering.org/"+portal+"/disasterreliefvolunteer.jsp");
       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/disasterreliefvolunteer.jsp";
   		}
  	}else     if(aszRedirectAttribute.equals("city_vision_internship_application")) {
        out.print("http://www.cityvisioninternships.org/intern/apply");
  	  aszCompleteRedirectURL="http://www.christianvolunteering.org/cityvision/email.do?method=showCreateApplication";
      }
  	else if(aszRedirectAttribute.startsWith("feeds: ")){
  		if(aszRedirectAttribute.length()>7){
  			aszRedirectAttribute=aszRedirectAttribute.substring(7,aszRedirectAttribute.length());
//         		out.print(aszRedirectAttribute);
         		aszCompleteRedirectURL=aszRedirectAttribute;
  		}else{
         		aszCompleteRedirectURL="http://www.christianvolunteering.org";
  		}
  	}else if(aszRedirectAttribute.equalsIgnoreCase("fbapp")){
		if(request.getHeader("host").contains("ivolunteering.org") || request.getHeader("host").contains("ivol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/worldchanger/register.do?method=showPersonalityTest";
       	}else if(request.getHeader("host").contains("ivol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/worldchanger/register.do?method=showPersonalityTest";
       	}else if(request.getHeader("host").contains("chrisvol.org")){
       		aszCompleteRedirectURL="http://apps.facebook.com/fycsandbox/register.do?method=showPersonalityTest";
       	}else{
       		aszCompleteRedirectURL="http://apps.facebook.com/find-your-calling/register.do?method=showPersonalityTest";
		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("noportalexists") ){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/";
			}else{
//	       		out.print("http://www.churchvolunteering.org/");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/404.jsp";
       		}
	}else if(aszHostDomain.contains("hurricanesandyvolunteer")){
   		out.print("http://" + aszHostDomain + "/");
  		aszCompleteRedirectURL="http://" + aszHostDomain + "/";
	}else if(aszRedirectAttribute.equalsIgnoreCase("portalassocmanagement") && portal.length()>0){
		if(request.getHeader("host").contains("churchvol.org") ){
       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/associationmanagement.jsp");
       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/associationmanagement.jsp";
   		}else if(request.getHeader("host").contains("chrisvol.org") ){
       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/associationmanagement.jsp");
       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/associationmanagement.jsp";
		}else{
       		out.print("http://www.christianvolunteering.org/"+portal+"/associationmanagement.jsp");
       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/associationmanagement.jsp";
   		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("portalorgmanagement") && portal.length()>0){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/orgmanagement.jsp";
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp";
			}else if(request.getHeader("host").contains("ivolunteering.org") ){
	       		out.print("http://www.ivolunteering.org/"+portal+"/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://www.ivolunteering.org/"+portal+"/orgmanagement.jsp";
			}else{
	       		out.print("http://www.christianvolunteering.org/"+portal+"/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/orgmanagement.jsp";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("portalselectopportunities") && portal.length()>0){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/churchopportunities.jsp");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/churchopportunities.jsp";
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp...portalopportunities.jsp");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp";
			}else{
	       		out.print("http://www.christianvolunteering.org/"+portal+"/orgmanagement.jsp..portalopportunities.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/orgmanagement.jsp";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("church_portalselectopportunities") && portal.length()>0){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/churchopportunities.jsp");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/churchopportunities.jsp";
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp...portalopportunities.jsp");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/orgmanagement.jsp";
       		}else{
	       		out.print("http://www.christianvolunteering.org/"+portal+"/churchopportunities.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/churchopportunities.jsp";
	       	}
	}else if(aszRedirectAttribute.equalsIgnoreCase("volunteerportalsite") && portal.length()>0){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/volunteerlistings.jsp");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/volunteerlistings.jsp";
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/volunteerlistings.jsp");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/volunteerlistings.jsp";
			}else if(request.getHeader("host").contains("ivolunteering.org") ){
	       		out.print("http://www.ivolunteering.org/"+portal+"/volunteerlistings.jsp");
	       		aszCompleteRedirectURL="http://www.ivolunteering.org/"+portal+"/volunteerlistings.jsp";
			}else{
	       		out.print("http://www.christianvolunteering.org/"+portal+"/volunteerlistings.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/volunteerlistings.jsp";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("volunteerportalsite")){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/volunteerdashboard.jsp");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/volunteerdashboard.jsp";
			}else if(request.getHeader("host").contains("ivolunteering.org") ){
	       		out.print("http://www.ivolunteering.org/volunteerdashboard.jsp");
	       		aszCompleteRedirectURL="http://www.ivolunteering.org/volunteerdashboard.jsp";
			}else{
	       		out.print("http://www.christianvolunteering.org/volunteerdashboard.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/volunteerdashboard.jsp";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("portalorgmanagement")){ // not necessarily in a portal
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/orgmanagement.jsp";
			}else{
	       		out.print("http://www.churchvolunteering.org/orgmanagement.jsp");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/orgmanagement.jsp";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("nextstepportalcreation")){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/"+portal);
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal;
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal);
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal;
			}else{
	       		out.print("http://www.christianvolunteering.org/"+portal);
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal;
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("createnonpstep1")){
			if(request.getHeader("host").contains("churchvol.org") ){
	       		out.print("http://www.churchvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1");
	       		aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1";
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
	       		out.print("http://chrisvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1");
	       		aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1";
			}else{
	       		out.print("http://www.christianvolunteering.org/"+portal+"/org.do?method=showCreateOrgStep1");
	       		aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/org.do?method=showCreateOrgStep1";
       		}
	}else if(aszRedirectAttribute.equalsIgnoreCase("urbmindashboard")){
		if(request.getHeader("host").contains("chrisvol.org") ){
       		aszCompleteRedirectURL="http://www.um.org/start";
		}else{
       		aszCompleteRedirectURL="http://www.urbanministry.org/start";
      	}
	}else if(aszRedirectAttribute.equalsIgnoreCase("paidmembership")){
		if(request.getHeader("host").contains("chrisvol.org") ){
			aszCompleteRedirectURL="http://www.urbanministry.org/start";
		}else{
			aszCompleteRedirectURL="http://www.urbanministry.org/start";
       	}
	}else if(aszRedirectAttribute.equalsIgnoreCase("urbmin")){
		if(request.getHeader("host").contains("chrisvol.org") ){
			aszCompleteRedirectURL="http://www.um.org";
		}else{
			aszCompleteRedirectURL="http://www.urbanministry.org";
       	}
	}else if(aszRedirectAttribute.equalsIgnoreCase("orgmanagement") ){ //   /org.do?method=showCreateOrgStep1       /orgmanagement.jsp  
    	//		httpServletRequest.setAttribute("newuser","/confirm/individual");
		try{
			aszGoalGA=request.getAttribute("newuser").toString();
			//request.removeAttribute("newuser");
			additionalURL="&newuser="+aszGoalGA;
		}catch(NullPointerException e){
			aszGoalGA = "";
		}
out.print("google analytics: "+request.getContextPath()+"/org.do?method=showCreateOrgStep1"+additionalURL);

		String aszUsername="";
		try{
			aszUsername=request.getAttribute("username").toString();
			request.removeAttribute("username");
		}catch(NullPointerException e){
			aszUsername = "";
		}
		if(request.getHeader("host").contains("churchvol.org") || request.getHeader("host").contains("churchvolunteering.org") ){	
			// pre-populate the org based on the username entered ("church name")
			if(aszUsername.length()>0){
				additionalURL="&orgname="+aszUsername;
			}
		}
		
			if(request.getHeader("host").contains("churchvol.org") ){
				out.print("http://www.churchvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL);
				aszCompleteRedirectURL="http://www.churchvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL;
       		}else if(request.getHeader("host").contains("chrisvol.org") ){
				out.print("http://chrisvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL);
				aszCompleteRedirectURL="http://chrisvol.org:7001/voleng/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL;
			}else{
				out.print("http://www.christianvolunteering.org/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL);
				aszCompleteRedirectURL="http://www.christianvolunteering.org/"+portal+"/org.do?method=showCreateOrgStep1"+additionalURL;
       		}
	}
}

out.print(aszCompleteRedirectURL);
response.sendRedirect(aszCompleteRedirectURL);
%>
<p>If you see this error page, please copy the text of this page and email it to us at support@christianvolunteering.org to let us know</p>
<p>Thank you!</p>