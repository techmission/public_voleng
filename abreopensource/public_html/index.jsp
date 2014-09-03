<%@ include file="/template_include/topjspnologin1.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta property="fb:page_id" content="156406630684" />
<meta property="fb:app_id" content="156406434372399" />
<META name="y_key" content="514703534306932c" />



<%
String index_temp = "";
try{
	if(request.getParameter("search") != null){
		if(request.getParameter("search").length()>0){
			index_temp = request.getParameter("search");
		}
	}
}catch(NullPointerException e){
}catch(Exception ex){
}

if(aszLandingPage.length() < 1){
	if(request.getAttribute("landingpage") != null){
		try{
			aszLandingPage = request.getAttribute("landingpage").toString();
		}catch(Exception e){}
	}
}

 if( aszPortal.equals("/cityvision") ){ 
 response.sendRedirect("http://www.christianvolunteering.org/");
// response.sendRedirect("http://www.christianvolunteering.org/cityvision/oppsrch.do?method=processSearch&fq=intern_type:%22City%20Vision%20Internship%22#fq=intern_type:%22City%20Vision%20Internship%22&fq=content_type:opportunity");
} 
%>

<% if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase("volunteerintechnologyinmissions")){%>
	<jsp:include page="/included/landing_technology.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase("foundationgrants")){%>
	<jsp:include page="/included/landing_foundation_grants_directory.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase("medicalmissionsvolunteering")){%>
	<jsp:include page="/included/landing_medical.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase("volunteerinachristianorphanage")){%>
	<jsp:include page="/included/landing_orphanage.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "christiangapyear" )){ %>
	<jsp:include page="/included/landing_christian_gap_year.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "stm" )){ %>
	<jsp:include page="/included/landing_short_term_missions.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "virtual" )){ %>
	<jsp:include page="/included/landing_virtual_vol.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "disasterrelief" )){ %>
	<jsp:include page="/included/landing_disaster_relief.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "disasterreliefhomepage" )){ %>
	<jsp:include page="/included/landing_disaster_relief_homepage.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "hurricanesandy" )){ %>
	<jsp:include page="/included/landing_hurricane_sandy.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "family" )){ %>
	<jsp:include page="/included/landing_family_volunteering.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if((aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "catholic" )) || aszHostID.equalsIgnoreCase("volengcatholic")){ %>
	<jsp:include page="/included/landing_catholic.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if((aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "christianjobs" )) || aszHostID.equalsIgnoreCase("volengchrisjobs")){ %>
	<jsp:include page="/included/landing_christian_jobs_directory.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase("chicago")){%>
	<jsp:include page="/included/portal_landing_chicago.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "denver" )){ %>
	<jsp:include page="/included/portal_landing_denver.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "indy" )){ %>
	<jsp:include page="/included/portal_landing_indianapolis.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "losangeles" )){ %>
	<jsp:include page="/included/portal_landing_losangeles.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "miami" )){ %>
	<jsp:include page="/included/portal_landing_miami.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "newengland" )){ %>
	<jsp:include page="/included/portal_landing_newengland.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "newyork" )){ %>
	<jsp:include page="/included/portal_landing_newyork.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "seattle" )){ %>
	<jsp:include page="/included/portal_landing_seattle.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszLandingPage.length() > 0 && aszLandingPage.equalsIgnoreCase( "twincities" )){ %>
	<jsp:include page="/included/portal_landing_twincities.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% }else if( aszPortal.equals("/cityvision") ){ %>
        <jsp:include page="/included/index_cvintern.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% }else if( aszPortalTemplate=="basic"){ %>
        <jsp:include page="/index_basic.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% }else if(aszHostID.equalsIgnoreCase( "volengagrm" )){ %>
	<jsp:include page="/agrm/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenghlic" )){ %>
	<jsp:include page="/hlic/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengyouthpartners" )){ %>
	<jsp:include page="/youthpartnersnet/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if( (aszHostID.equalsIgnoreCase( "volengboston" )) ||  (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ %>
	<jsp:include page="/newengland/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengchicago" )){ %>
	<jsp:include page="/chicago/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengchurch" )){ %>
        <jsp:include page="/churchvolunteering/index.jsp" flush="false">
                <jsp:param name="a" value="" />
        </jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )){ %>
	<jsp:include page="/denver/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )){ %>
	<jsp:include page="/indy/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )){ %>
	<jsp:include page="/losangeles/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )){ %>
	<jsp:include page="/miami/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )){ %>
	<jsp:include page="/newyork/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengseattle" )){ %>
	<jsp:include page="/seattle/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>
<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" )){ %>
	<jsp:include page="/twincities/index.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>

<% } else { %>
	<jsp:include page="/index_include.jsp" flush="false">
		<jsp:param name="a" value="" />
	</jsp:include>

<% } %>

