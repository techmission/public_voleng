<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ include file="/template_include/facebookapi_keys.inc" %>

<%
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
%>

<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<%

String aszShortenedURLAlias = "";
if( opp.getOPPUrlAlias() != null){
	aszShortenedURLAlias = opp.getOPPUrlAlias();
	int iURLAlias = aszShortenedURLAlias.length();
	if( iURLAlias > 10 ){
		try{
			aszShortenedURLAlias = aszShortenedURLAlias.substring( 10 );//, iURLAlias );
			if(request.getParameter("redirected") != null){
			}else{
				// program in forwarding if the opportunity is Faith-Filled
				if(	(opp.getOPPFaithSpecTID()==21998 || (! opp.getOPPAddrCountryName().equalsIgnoreCase("us") ) ) && 
					(! aszHostID.equalsIgnoreCase("volengchurch")) 
				){ 
					response.setStatus(301);
					response.setHeader( "Location", "http://m.churchvolunteering.org/org.do?method=showOpport&urlalias=" + aszShortenedURLAlias + "&redirected=true" );		
					response.setHeader( "Connection", "close" );
				}
			}
		}catch(IndexOutOfBoundsException ex){
		}
	}
}

%> 


<%
	String aszTemp1= opp.getOPPAddrCountryName();
	String aszTemp2 = opp.getOPPAddrStateprov();
%>
<!--/node/<%=opp.getOPPNID()%>-->

<% if(aszHostID.equalsIgnoreCase( "voleng1" ) || aszHostID.equalsIgnoreCase( "volengchurch" )){ %>
<title>Volunteer in <%=opp.getOPPTitle()%>, <%=org.getORGOrgName()%><% if (opp.getOPPAddrCity().length()>1){	out.print(", ");}%><%=opp.getOPPAddrCity()%><% if ( (opp.getOPPAddrCountryName().length()>1) || (opp.getOPPAddrStateprov().length()>1) ){	out.print(", ");}%>
<%
	if(aszTemp1.equalsIgnoreCase("US")){
		if(null != aStateList){
			for(int index=0; index < aStateList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCSPStateCode();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
				out.print("" + aAppCode.getCSPStateName() );}
			}
		}
	} else {
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
				out.print("" + aAppCode.getCTRPrintableName() + "");}
			}
		}
	}
%><% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>: Christian Volunteer Opportunity<% }else if(aszHostID.equalsIgnoreCase( "volengchurch" )){%>: Church Volunteer Opportunity<% } %>

</title>

<% } else { } %>

<meta name="description" content="I saw this volunteer opportunity, and wanted to see if you might be interested in volunteering together sometime: <%=opp.getOPPTitle()%>
, <%=org.getORGOrgName()%>.  Let me know if you would be up for this.">

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<%
	int iTemp = 0;
	long iTime = 0;

PersonInfoDTO aCurrentUserObj = UserSessionBean.getCurrentUser( request);

JSONObject obj = new JSONObject();
obj.put("pic", "http://www.christianvolunteering.org/imgs/results-img.gif");
obj.put("name", "");

String aszName = obj.get("name")+"";
if(aszName.length()<1){
	aszName=aCurrentUserObj.getUSPNameFirst() + " " +aCurrentUserObj.getUSPNameLast();
}
if(aszName.length()<3){
	aszName = "I am interested in volunteering"; 
}else{
	aszName += " wants to volunteer";
}

String currentURL=aszSubdomain+"/"+opp.getOPPUrlAlias()+".jsp";

if(
	aszHostID.equalsIgnoreCase("volengexample") || 
	aszHostID.equalsIgnoreCase( "volenggospel" ) ||
	aszHostID.equalsIgnoreCase( "volenggospelcom" ) ||
	aszHostID.equalsIgnoreCase( "volengchurch" ) ||
	aszRemoteHost.contains("christianvolunteering.org")
){
%>

<!-- example using the OLD Facebook Javascript SDK: -->
<!--script type="text/javascript" src="http://static.ak.connect.facebook.com/js/api_lib/v0.4/FeatureLoader.js.php?v2"></script>

<script type="text/javascript">
function publish() {
	var attachment = {
	     'name':'Come join me in volunteering in the opportunity \'\'<%=opp.getOPPTitle()%>\'\' for  <%=org.getORGOrgName()%>',
	     'href':'http://<%=currentURL%>',
	     'caption':'{*actor*} wants to volunteer for the opportunity \'\'<%=opp.getOPPTitle()%>\'\' listed by <%=org.getORGOrgName()%>!  Click below to find out more about this opportunity!',
	     'media':[{
	       'type':'image',
	       'src':'http://www.christianvolunteering.org/imgs/chrisvol_logo_fb.jpg',
	       'href':'http://<%=currentURL%>'
	     }]
	};
	var action_links = [{'text':'View the opportunity','href':'http://<%=aszSubdomain%>/org.do?method=showOpport&oppnid=<%=opp.getOPPNID()%>'}];
	FB_RequireFeatures(["Connect"], function() {
	    FB.init('<%=tempapikey%>', '<%=aszPortal%>/xd_receiver.htm');
		
	    FB.ensureInit(function() {
			var fbstatus="";
			FB.Connect.get_status().waitUntilReady(function(status) {
				switch(status) {
					case FB.ConnectState.connected:
						fbstatus="connected";
//						alert(fbstatus);
						break;
					case FB.ConnectState.appNotAuthorized:
						fbstatus="appNotAuthorized";
//						alert(fbstatus);
						break;
					case FB.ConnectState.userNotLoggedIn:
						fbstatus="userNotLoggedIn";
//						alert(fbstatus);
						break;
				}
			});
	
//			alert(fbstatus);
			if(fbstatus+"" !="userNotLoggedIn"){
	
				FB.Connect.streamPublish('', attachment, action_links, null, 'Add a personalized message', volunteer_clicked);
/*			
	    FB.ensureInit(function() {
//	    	FB.Connect.streamPublish('', attachment, action_links, null, 'Add a personalized message', volunteer_clicked);
			//FB.Connect.showAddSectionButton('profile', document.getElementById('profileAddDiv'));
	    });
*/			
			}else{
				volunteer_clicked();
			}
	    });
	});
}
</script-->
 	
	
<!--script type="text/javascript" src="http://connect.facebook.net/en_US/all.js"></script-->
	
<div id="fb-root"></div>
<script type="text/javascript">
window.fbAsyncInit = function() {//onload = function() {//
  FB.init({appId: '<%=tempappid%>', status: true, cookie: true,
           xfbml: true});
  fbApiInitialized = true;
afterInit();
//alert('hi - line 172 (fbAsyncInit called correctly)');
};
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
function fbEnsureInit(callback) {
    if (!window.fbApiInitialized) {
        setTimeout(function() { fbEnsureInit(callback); }, 50);
    } else {
        if (callback) { callback(); }
    }
}
function afterInit(){
	return;
}  
function publish() {
//alert('function called'); 
var browserName=navigator.userAgent; 
//alert(browserName);
//alert(browserName.indexOf("Chrome"));
if(browserName.indexOf("Chrome") != -1
	&& false==true
){
//alert(browserName.indexOf("Chrome"));
	volunteer_clicked();
}else{
//alert('hi!');
//    FB.init({appId: '<%=tempappid%>', status: true, cookie: true, xfbml: true});
//afterInit();
//alert('hi again!');
	FB.getLoginStatus(function(response) {
	// response.status=unknown (NOT logged in user), notConnected (for logged in user who has NOT authenticated the app), connected		(for logged in user who HAS authenticated the app)
	//alert('loginstatus called'); 
//	alert(response.status); 
	  if (response.status != "unknown") {
		// logged in and connected user, someone you know
		FB.ui(
		   {
//			 display: 'popup',
			 method: 'stream.publish',
			 attachment: {
			   	name: 'Come join me in volunteering in the opportunity \'\'<%=opp.getOPPTitle()%>\'\' for  <%=org.getORGOrgName()%>',
			   	caption: '{*actor*} wants to volunteer for the opportunity \'\'<%=opp.getOPPTitle()%>\'\'',
			   	href: 'http://<%=currentURL%>',
				 media:[{
				   'type':'image',
				   'src':'http://www.christianvolunteering.org/imgs/chrisvol_logo_fb.jpg',
				   'href':'http://<%=currentURL%>'
				 }]
			 },
			 action_links: [
			   {'text':'View the opportunity','href':'http://<%=aszMobileSubdomain%>/org.do?method=showOpport&oppnid=<%=opp.getOPPNID()%>'}
			 ],
			 user_message_prompt: 'Add a personalized message'
		   },
		   function(response) {
			 if (response && response.post_id) {
//			   alert('Post was published.');
				volunteer_clicked();
			 } else {
//			   alert('Post was not published.');
				volunteer_clicked();
			 }
		  }
		);
	  } else {
		// no user session available, someone you dont know
		volunteer_clicked();
	  }
	});
//	volunteer_clicked();
  }
}
</script>

<% }else{ %>
<script type="text/javascript">
function publish() {
	volunteer_clicked();
}
</script>
<% } %>    


<%

ABREDate aDateObj = new ABREDate();

String aszOppNID = "" + opp.getOPPNID();
String aszOrgNID = "" + opp.getORGNID();

	String aszDescription;
	String aszDescriptionTeaser;
	String aszStatement;
	String aszRequirements;
	String aszAddDetail;
	String aszSTMReference;
	String aszCostPymntIncludes;

// separate out categories for outputting
String	aszCat1 = opp.getOPPCategories();
String	aszCat2 = opp.getOPPCategories2();
String	aszCat3 = opp.getOPPCategories3();
int iServiceArea1 = opp.getOPPServiceArea1TID();
int iServiceArea2 = opp.getOPPServiceArea2TID();
int iServiceArea3 = opp.getOPPServiceArea3TID();

// use to allow ,&nbsp; between for output
String aszSkills1=opp.getOPPSkills();
String aszSkills2=opp.getOPPSkills2();
String aszSkills3=opp.getOPPSkills3();
int iSkills1 = opp.getOPPSkills1TID();
int iSkills2 = opp.getOPPSkills2TID();
int iSkills3 = opp.getOPPSkills3TID();

String aszVirtual="display:inline;";
String aszOppStatus = opp.getOPPStatus();
int iOppStatus = opp.getOPPPositionTypeTID();
if(iOppStatus==4795){
   aszVirtual="display: none;";
}

String aszHrly="display:inherit;";
double aszOppHrly = opp.getOPPCommitHourly();
if(aszOppHrly ==0.0){
	aszHrly="display: none;";
}

String aszNumPosition="display:inherit;";
int aszOppNumPosition = opp.getOPPVolsNeeded();
if(aszOppNumPosition==0){
	aszNumPosition="display: none;";
}

String aszNumVol="display:inherit;";
int aszOppNumVol = opp.getOPPNumVolOpp();
if(aszOppNumVol==0){
	aszNumVol="display: none;";
}

String aszReqBelief="display: none;";
String aszOrgBelief = opp.getOPPRequiredCodeType();
if(aszOrgBelief.equalsIgnoreCase("Yes")){
   aszReqBelief="display:inherit;";
}

String aszStatementFaith="display: none;";
String aszOrgStatement = opp.getOPPRequiredCodeDesc();
if(aszOrgStatement.equalsIgnoreCase("Organizational Statement of Faith")){
   aszStatementFaith="display:inherit;";
}

String aszAddDetailSect="display: none;";
String aszAddDetails = opp.getOPPAddDetails();
if(aszAddDetails.length()>0){
   aszAddDetailSect="display:inherit;";
}

String aszSTMReferenceSect="display: none;";
String aszSTMReferences = opp.getOPPSTMReferences();
if(aszSTMReferences.length()>0){
   aszSTMReferenceSect="display:inherit;";
}

String aszLanguageSection="display:inherit;";
String aszLanguage = opp.getOPPLanguages();
if(aszLanguage.equalsIgnoreCase("")){
   aszLanguageSection="display: none;";
}

String aszShortTermSect="display: none;";
String aszShortTerm = opp.getOPPStatus();
if(aszShortTerm.equalsIgnoreCase("Short-term Missions / Volunteer Internship")){
   aszShortTermSect="display:inherit;";
}

String aszTempCost="display: none;";
double iTempCost=opp.getOPPCostUsd();
if(iTempCost > 0.0){
   aszTempCost="display:inherit;";
}

String aszAmntPaidSect="display: none;";
String aszAmntPaid=opp.getOPPAmntPd();
if(aszAmntPaid.length() > 0){
   aszAmntPaidSect="display:inherit;";
}

String aszRegionSect="display: none;";
String aszRegion=opp.getOPPRegion();
if(aszRegion.length() > 0){
   aszRegionSect="display:inherit;";
}

String aszGroup="display: none;";
String aszOppFocusArea1 = opp.getOPPFocusAreas();
String aszOppFocusArea2 = opp.getOPPFocusAreas2();
String aszOppFocusArea3 = opp.getOPPFocusAreas3();
String aszOppFocusArea4 = opp.getOPPFocusAreas4();
String aszOppFocusArea5 = opp.getOPPFocusAreas5();
int iOppFocusArea1 = opp.getOPPGreatFor1TID();
int iOppFocusArea2 = opp.getOPPGreatFor2TID();
int iOppFocusArea3 = opp.getOPPGreatFor3TID();
int iOppFocusArea4 = opp.getOPPGreatFor4TID();
int iOppFocusArea5 = opp.getOPPGreatFor5TID();
if( 
	(iOppFocusArea1 == 4793) ||
	(iOppFocusArea2 == 4793) ||
	(iOppFocusArea3 == 4793) ||
	(iOppFocusArea4 == 4793) ||
	(iOppFocusArea5 == 4793) 
){
   aszGroup="display:inherit;";
}
String	aszDescr = opp.getOPPDescription();

if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ // don't show faith-related fields or taxonomy values
   aszReqBelief="display: none;";
   aszStatementFaith="display: none;";
   aszShortTermSect="display: none;";
   aszTempCost="display: none;";

	// don't output faith related Service areas or Skills for ivolunteering; re-word or make blank
	if (iServiceArea1 == 4760 ||
		iServiceArea1 == 4764 ||
		iServiceArea1 == 4772 ||
		iServiceArea1 == 4773 ||
		iServiceArea1 == 4783 ||
		iServiceArea1 == 4787 ||
		iServiceArea1 == 4789 ||
		iServiceArea1 == 7341 ||
		iServiceArea1 == 7342){
			aszCat1="";
	}else if (iServiceArea1 == 4767){
		aszCat1="Disabilities Service";
	}else if (iServiceArea1 == 4774){
		aszCat1="Food Service / Hunger";
	}else if (iServiceArea1 == 4782){
		aszCat1="Prison Service";
	}
	
	if (iServiceArea2 == 4760 ||
		iServiceArea2 == 4764 ||
		iServiceArea2 == 4772 ||
		iServiceArea2 == 4773 ||
		iServiceArea2 == 4783 ||
		iServiceArea2 == 4787 ||
		iServiceArea2 == 4789 ||
		iServiceArea2 == 7341 ||
		iServiceArea2 == 7342){
			aszCat2="";
	}else if (iServiceArea2 == 4767){
		aszCat2="Disabilities Service";
	}else if (iServiceArea2 == 4774){
		aszCat2="Food Service / Hunger";
	}else if (iServiceArea2 == 4782){
		aszCat2="Prison Service";
	}
	
	if (iServiceArea3 == 4760 ||
		iServiceArea3 == 4764 ||
		iServiceArea3 == 4772 ||
		iServiceArea3 == 4773 ||
		iServiceArea3 == 4783 ||
		iServiceArea3 == 4787 ||
		iServiceArea3 == 4789 ||
		iServiceArea3 == 7341 ||
		iServiceArea3 == 7342){
			aszCat3="";
	}else if (iServiceArea3 == 4767){
		aszCat3="Disabilities Service";
	}else if (iServiceArea3 == 4774){
		aszCat3="Food Service / Hunger";
	}else if (iServiceArea3 == 4782){
		aszCat3="Prison Service";
	}
	
	if (iSkills1 == 4748 ||
		iSkills1 == 4749){
	}else if (iSkills1 == 4745){
		aszSkills1="Musician";
	}
	if (iSkills2 == 4748 ||
		iSkills2 == 4749){
	}else if (iSkills2 == 4745){
		aszSkills2="Musician";
	}
	if (iSkills3 == 4748 ||
		iSkills3 == 4749){
	}else if (iSkills3 == 4745){
		aszSkills3="Musician";
	}
}

%>

<%
aszSTMReference = "References from people who have done Short Term Missions with this organization before are available upon request. Submit \"I Want to Volunteer!\" to receive more information.";
	aszDescription = opp.formatForPrint(opp.getOPPDescription(),35);
	aszStatement = org.formatForPrint(org.getORGOrgStmtFaith(),35);
	aszRequirements = opp.formatForPrint(opp.getOPPRequirements(),35);
	aszAddDetail = opp.formatForPrint(opp.getOPPAddDetails(),35);
	aszSTMReference = opp.formatForPrint(aszSTMReference,35);
	aszCostPymntIncludes = opp.formatForPrint(opp.getOPPCostInclude(),35);


aszDescriptionTeaser = aszDescription;
if(aszDescriptionTeaser.length()>150){
	aszDescriptionTeaser = aszDescriptionTeaser.substring(0,35) + "...";
}


%>

<style>
label {
	font-weight:bold;
}
</style>
</head>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Volunteer Opportunity</span>
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
<div id="pagebanner" class="shorter">
	
		<span style="float: left;">Volunteer Opportunity</span>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/oppsrch.do?method=showSearch">search</a> 
			&gt; volunteer opportunity  </div>
	</div>
<% } %>




<div align="left">
	<div id="body" class="opp_listing">
<%=org.getErrorMsg()%>








<h1><%=opp.getOPPTitle()%></h1>



<div class="listingdetail" style="float:left;">
	<div class="info">
  <div class="details">
		<div id="org_and_address" class="opp_info">
			<label class="listingDetail org_name">
				<A  href="http://<%=aszMobileSubdomain%>/org/org<%=org.getORGurlID()%>.jsp"><%=org.getORGOrgName()%></A>
			</label>
			
			<br>
			<div id="address" style="<%=aszVirtual%>">
				<%=opp.getOPPAddrLine1()%>
				<% 
				if (opp.getOPPAddrCity().length()>1){
					out.print("<br />"+opp.getOPPAddrCity());
				}
				if (	(
						opp.getOPPAddrCountryName().length()>1 || 
						opp.getOPPAddrStateprov().length()>1
					) && opp.getOPPAddrCity().length()>1
				){
					out.print(", ");
				}
				aszTemp= opp.getOPPAddrStateprov();
				if(null != aStateList){
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
							out.println("" + aAppCode.getCSPStateName() + "");
						}
					}
				}
				out.println("&nbsp;&nbsp;&nbsp;"+opp.getOPPAddrPostalcode());
				aszTemp= opp.getOPPAddrCountryName();
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) {
							out.println("" + aAppCode.getCTRPrintableName() + "");
						}
					}
				}
				%>
			</div>

		</div>
		
    <div class="opp_info"><!-- align="right"-->
		<label class="floating">Location:</label>
		<% 
			if (opp.getOPPStatus2().length()>1){
				out.print("Local or Virtual Volunteering ");
			}else{
				if (aszSecondaryHost.equalsIgnoreCase("volengivol") && opp.getOPPPositionTypeTID()== 4796) {
					out.print("Short-term Service / Volunteer Internship");
				}else{
					out.print(opp.getOPPStatus());
				}
			}
		%>
		</div>
		
    <div class="opp_info">
		<% 
			if (aszOppFocusArea1.length()>0) { 
				out.print("<label class=\"listingDetail floating\">Great For:</label>"+aszOppFocusArea1.substring(9,aszOppFocusArea1.length()));
				if (aszOppFocusArea2.length()>0) { 
					out.print(", ");
					out.print(aszOppFocusArea2.substring(9,aszOppFocusArea2.length()));
					if (aszOppFocusArea3.length()>0) {  
						out.print(", ");
						out.print(aszOppFocusArea3.substring(9,aszOppFocusArea3.length()));
						if (aszOppFocusArea4.length()>0) { 
							out.print(", ");
							out.print(aszOppFocusArea4.substring(9,aszOppFocusArea4.length()));
							if (aszOppFocusArea5.length()>0) {  
								out.print(", ");
								out.print(aszOppFocusArea5.substring(9,aszOppFocusArea5.length()));
							}
						}
					}
				}
			}
		%>
		</div>
		
		
	<% 
	// output service areas with a ,&nbsp; in between, but not if there is only one category 
	if(aszCat1.length()>1 || aszCat2.length()>1 || aszCat3.length()>1){
		out.print("<div class=\"opp_info\"><label class=\"listingDetail floating\">Service Areas:  </label>"+aszCat1);
	}
		if ( (aszCat1.equalsIgnoreCase(""))||(aszCat2.equalsIgnoreCase("")) ){
		} else { 
			out.print(",&nbsp;");
		}
		out.print(aszCat2);
		if ( (aszCat2.equalsIgnoreCase(""))||(aszCat3.equalsIgnoreCase("")) ){
		} else { 
			out.print(",&nbsp;");
		}
		out.print(aszCat3);
	if(aszCat1.length()>1 || aszCat2.length()>1 || aszCat3.length()>1){
		out.print("</div>");
	}
	%>
		
		
	<% 
	// output skills with a ,&nbsp; in between, but not if there is only one skill 				
	if(aszSkills1.length()>1 ||aszSkills2.length()>1||aszSkills3.length()>1){
		out.print("<div class=\"opp_info\"><label class=\"listingDetail floating\">Skills:  </label>"+aszSkills1);
	}
		if ( (aszSkills1.equalsIgnoreCase(""))||(aszSkills2.equalsIgnoreCase("")) ){
		} else { 
			out.print(",&nbsp;");
		}
		out.print(aszSkills2);
		if ( (aszSkills2.equalsIgnoreCase(""))||(aszSkills3.equalsIgnoreCase("")) ){
		} else { 
			out.print(",&nbsp;");
		}
		out.print(aszSkills3);
	if(aszSkills1.length()>1 ||aszSkills2.length()>1||aszSkills3.length()>1){
		out.print("</div>");
	}
	%>
    
    <div class="opp_info" style="<%=aszHrly%> ">
		<label class="listingDetail floating" style="<%=aszHrly%>">Hours:</label>
		<div style="<%=aszHrly%>">
			<%=opp.getOPPCommitHourly()%> per: <%=opp.getOPPCommitType()%>
		</div>
    </div>
		
    <div class="opp_info" style="<%=aszNumPosition%> ">
		<label class="listingDetail floating" style="<%=aszNumPosition%>"># of Positions:</label>
		<div style="<%=aszNumPosition%>">
			<%=opp.getOPPVolsNeeded()%>			
		</div>
	</div>
    
    <!--div class="opp_info" style="<%=aszNumVol%> ">
		<label class="listingDetail floating" style="<%=aszNumVol%>">
			Number of Volunteers in this<br> Position in the Past Year:
		</label>
		<div style="<%=aszNumVol%>">
			<%=opp.getOPPNumVolOpp()%>			
		</div>	
    </div-->
		
    <div class="opp_info">
		<label class="listingDetail floating">
			Date(s):
		</label><!-- one time or ongoing position -->
		<%=opp.getOPPFreq()%>
	</div>
    
    <div class="opp_info">
		<label class="listingDetail">
			Description
		</label>
		<br />
		<%=aszDescriptionTeaser%>	
    </div>
		</div>

		<HR width="100%">      
<div id="buttons">
<%@include file="/template_include/footer_google_analytics.inc"%>

<% String aszGoalPage="volunteer/opp"; %>

<script language="javascript">
function volunteer_clicked(){//function volunteer_clicked(post_id){
	var oppTracker = _gat._getTracker("UA-768097-3");
	oppTracker._initData();
	oppTracker._trackPageview("<%=aszGoalPage%>");
	<% if( aszHostID.equalsIgnoreCase( "volengivol" ) ){ %>
	window.location="<%=aszPortal%>/org.do?volunteer&method=processIVolHelp&subdomain=<%=aszSubdomain%>&siteemail=<%=aszEmailHost%>&orgnid=<%=aszOrgNID%>&oppnid=<%=aszOppNID%>&requesttype=edit";
<% }else{ %>
window.location="<%=aszPortal%>/org.do?volunteer&method=processIWantToHelp1&subdomain=<%=aszSubdomain%>&siteemail=<%=aszEmailHost%>&orgnid=<%=aszOrgNID%>&oppnid=<%=aszOppNID%>&requesttype=edit";
<% } %>
}
</script>			                
<html:form action="/org.do?volunteer" method="get" >
<% if( aszSecondaryHost.equalsIgnoreCase( "volengivol" ) ){ %>
	<html:hidden property="method" value="processIVolHelp" />
<% }else{ %>
	<html:hidden property="method" value="processIWantToHelp1" />
<% } %>
	<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
	<html:hidden property="siteemail" value="<%=aszEmailHost%>" />
	<html:hidden property="orgnid" value="<%=aszOrgNID%>" />
	<html:hidden property="oppnid" value="<%=aszOppNID%>" />
	<html:hidden property="requesttype" value="edit" />
			
</html:form>
<INPUT class="volunteer" type="button" value="I Want To Help!" src="../template/image/help.gif" onClick="publish()"><!--onClick="volunteer_clicked()"-->			
			

		<!--div class="clear" style="height: 5px;"></div-->
		<div id="navcontainer">
			<a href="http://www.urbanministry.org/node/<%=opp.getOPPNID()%>#comments" class="button">Recommend this Opportunity</a>		
</div>

<div id="navcontainer">
<% if( true == UserSessionBean.IsSessionLoggedIn( request ) ){	%>
		<table><tr><td>
			<form id="favorites" name="favorites" method="post" action="http://www.urbanministry.org/add-favorites">     
			<input type="hidden" id="nid" name="nid" value="<%=opp.getOPPNID()%>">
			<input type="hidden" id="title" name="title" value="<%=opp.getOPPTitle()%>">
			<input type="hidden" id="orgname" name="orgname" value="<%=org.getORGOrgName()%>">
			<input type="hidden" id="type" name="type" value="Volunteer Opportunity">
			<input type="submit" name="submit" value="Add to Favorites" type="button"  /><!--style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;"-->
			</form>  
		</td><td></td><td>
			<form id="abuse" method="post" action="http://www.urbanministry.org/report-abuse">     
			<input type="hidden" id="nid" name="nid" value="<%=opp.getOPPNID()%>">
			<input type="hidden" id="title" name="title" value="<%=opp.getOPPTitle()%>">
			<input type="hidden" id="orgname" name="orgname" value="<%=org.getORGOrgName()%>">
			<input type="hidden" id="type" name="type" value="Volunteer Opportunity">
			<input type="submit" value="Report Abuse" name="submit" type="button"  /><!--style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;"-->
			</form>   
		</td></tr></table>    
<% } else { %>
		<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" class="button"><!--style="background:none;border:0;color:#000;text-decoration:underline;cursor:pointer;font-weight:normal;"-->
		Login to Add to Favorites or Report Abuse</a>
<% } %>
</div>
</div>


		<HR width="100%">      

<div id="textareaformat">

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Full Description</h3>

<%=aszDescription%>

<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Requirements </h3>

<%=aszRequirements%>

</div>


		<div id="languages" style="<%=aszLanguageSection%>">
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Language </h3>
			<%=opp.getOPPLanguages()%>
		</div>

<% if (opp.getOPPWorkStudyTID()== 8104){ %>
		<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> <%=opp.getOPPWorkStudy()%></h3><p></p><br>
<% } %>
	

<% if(! aszHostID.equalsIgnoreCase("volengivol"))
		if(opp.getOPPRequiredCodeType()=="Yes"){  {%>
			<h3>Volunteers for This Position Are Required to be Christian</h3>
			<p></p>
			<br>
			
			<div id="orgBelief" style="<%=aszReqBelief%>">
				<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Required Creed / Christian Belief</h3><br>
				<p><%=opp.getOPPRequiredCodeDesc()%></p>
			<br>
		<% } %>
	
		<div id="orgStatement" style="<%=aszStatementFaith%>">
			<div id="textareaformat"><h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Organizational Statement of Faith</h3><br><%=aszStatement%></div></div>
		</div>
<% } %>

		<div id="group" style="<%=aszGroup%>">
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Group Minimum and Maximum</h3>
			<%=opp.getOPPGroupMin()%> - 
			<%=opp.getOPPGroupMax()%>
		</div>

		<div id="shortTermMissFields" style="<%=aszShortTermSect%>">
			<br>
			<hr>
			<h4>Short Term Mission Trip Details</h4><br><hr>
			
			<div id="stmReference" style="<%=aszSTMReferenceSect%>">
	<div id="textareaformat">
	<%=aszSTMReference%>
	</div>
			</div>

			<div id="addDetails" style="<%=aszAddDetailSect%>">
<div id="textareaformat"><h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Additional Detail</h3><br><br><%=aszAddDetail%>
</div>
			</div>
			
			<div id="regionSect" style="<%=aszRegionSect%>">
				<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Region</h3><br>
				<p><%=opp.getOPPRegion()%></p>
			</div>


<% 
if(opp.getOPPRoomBoard().length()>0 || opp.getOPPStipend().length()>0 ||opp.getOPPMedInsur().length()>0 || 
	opp.getOPPTransport().length()>0 || opp.getOPPAmeriCorps().length()>0){
%>
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Benefits Offered:</h3><br>
<ul>

<% 
	if(opp.getOPPRoomBoard().length()>0){
		out.println("<li>" + opp.getOPPRoomBoard());
		out.println("</li>");
	}
	if(opp.getOPPStipend().length()>0){
		out.println("<li>" + opp.getOPPStipend());
		out.println("</li>");
	}
	if(opp.getOPPMedInsur().length()>0){
		out.println("<li>" + opp.getOPPMedInsur());
		out.println("</li>");
	}
	if(opp.getOPPTransport().length()>0){
		out.println("<li>" + opp.getOPPTransport());
		out.println("</li>");
	}
	if(opp.getOPPAmeriCorps().length()>0){
		out.println("<li>" + opp.getOPPAmeriCorps());
		out.println("</li>");
	}
}
out.println("</ul>");
%>


			<div id="paid" style="<%=aszAmntPaidSect%>">
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Amount Paid (if any)</h3><br>
			<p><%=opp.getOPPAmntPd()%></p>
			</div>
			
			<div id="cost" style="<%=aszTempCost%>">
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Cost (per person)</h3><br>
			<p>$<%=opp.getOPPCostUsd()%> <%=opp.getOPPCostTerm()%></p>
			</div>
			
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> This cost includes:</h3><br>
			<p><%=aszCostPymntIncludes%></p>

<%
	iTime = opp.getOPPApplicDeadlineNum();
	if (iTime > 0){
%>
			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Application Deadline (MM/DD/YYYY)</h3><br>
			<p>
<%
		aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
		out.println( aszTemp );
%>
			</p>
<%
	}
%>

			<h3><img border="0" src="../imgs/bullet_4.gif" width="7" height="7"> Length of Trip</h3><br>
			<p><%=opp.getOPPTripLength()%></p>
		</div><!-- close shortTermMissFields -->

		<table>
			<tr>
				<th align=right> Initially Posted:</th>
				<td></td>
				<td>
					<%				
						iTime = opp.getOPPCreateDtNum();
						aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
						out.println(aszTemp);
					%>
				</td>
			</tr>
			<tr>
				<th align=right> Last Updated:</th>
				<td></td>
				<td>
					<%				
						iTime = opp.getOPPUpdateDtNum();
						aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
						out.println(aszTemp);
					%>
				</td>
			</tr>
		</table>
		
		<HR width="100%">
		<%
		aszTemp = opp.getOPPDaterequired();
		iTemp = opp.getOPPDaterequiredTID();
		if(iTemp==8132 || iTemp==8133 || iTemp==8134){
		if(opp.getOPPStartDtNum()> 0 ){
			out.println(aszTemp);
			if(iTemp==8134){			
				iTime = opp.getOPPStartDtNum();
				aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
				out.println(" Start Date: &nbsp; "+ aszTemp +"<BR>");
				iTime = opp.getOPPEndDtNum();
				if (iTime > 0){
					if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
						aszTemp = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (iTime*1000));
						out.println(" End Date: &nbsp;&nbsp; "+ aszTemp +"<BR>");
					}
				}
			}else if(iTemp==8133){			
				iTime = opp.getOPPStartDtNum();
				aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
				out.println(" Start Date: &nbsp; "+ aszTemp +"<BR>");
				iTime = opp.getOPPEndDtNum();
				if (iTime > 0){
					if(opp.getOPPStartDtNum()!=opp.getOPPEndDtNum()){
						aszTemp = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date (iTime*1000));
						out.println(" End Date: &nbsp;&nbsp; "+ aszTemp +"<BR>");
					}
				}
			}
		}
		} else {
			out.println("<br>(this opportunity is not scheduled for a specific date)");
		}
		%>
		<HR width="100%">

	</div>

</div>

</div>

</div>  

    

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %>
<!-- end footer information -->
