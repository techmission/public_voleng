<%@ include file="/template_include/topjspnologin1.inc" %>

<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

<%

boolean narrow=true; 
boolean mobile=true; 
%>

<style>
.better-select div.form-checkboxes-scroll {
  border: 1px solid #666;
  max-height: 95px;
  max-width: 400px;
  overflow: auto;
  word-wrap: break-word;
}
.better-select div.form-checkboxes-noscroll {
  border: 1px solid #666;
  overflow: hidden;
  word-wrap: break-word;
}
ul.columns {float:left; width:auto;}
ul.columns li {padding-left:0px; //list-style: square;}
ul.columns li a{ color:#003366; text-decoration:none;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
.profile_box{
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
	padding:5px;
/*	float:left;*/
        font-family: arial,helvitica,sans-serif;
/*	margin: 0 10px 10px 60px;*/
	height:575px; 
}
.profile_box a, .fb_box a{ color:#003366; text-decoration:none; line-height:2em;}
.profile_box a:hover, .fb_box a:hover {text-decoration:underline;}
.fb_box {
	text-align:left;
	background-color:#FFF;
	border:3px solid #93B6F0;
        font-family: arial,helvitica,sans-serif;
	height:90px; 
	padding:5px;
/*	margin: 0 10px 10px 100px;*/
/*	align: right;*/
/*	//height:270px; float:left;*/
}
.fb_box h2 {text-align:center;}
.fb_box ol {width:210px;}
h3{margin:0px;}
#fb-login-li{padding:10px;margin-left:30px;}
#fb-login-li a{line-height:1.3em;}

a.add-new-buttn { 
background-color:#83A2F4;
border:1px solid #4D73CF;
color:#FFFFFF;
font-weight:bold;
padding:3px;
width:140px;
text-decoration:none;
}
a.add-new-buttn:hover {
background-color:#4D73CF;
text-decoration:none;
color:#FFF;
}

</style>


<noscript>
Javascript is disabled.  This form will not work for you.<br><br>
Please enable Javascript in your browser
</noscript>

<script language="javascript">
function new_account() {
	document.forms["individualForm"].submit();	
} 

<% /*
function submitBoth() {
	new_account();
}
*/%>
</script>


<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		vidVolSkill=31, vidVolInterestArea=32, 
		vidState=52, vidCountry=261, vidVolVirt=49,
		vidRegion=38, vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332;
int iTemp=0;
int iDisasterRlfTID = 21632,iLocalVolTID = 17254,
		iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
		iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,
		iSocJustGrpsTID = 17266, iLocalOrgsTID = 21853;
int[] a_iContainer= new int[1];

ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList ainterestareaList = new  ArrayList ( 2 );
ArrayList alangList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList avolavailList = new  ArrayList ( 2 );
ArrayList aLookingForList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getTaxonomyCodeList( askillsList, vidVolSkill );
aCodes.getTaxonomyCodeList( ainterestareaList, vidVolInterestArea );
aCodes.getTaxonomyCodeList( alangList, vidVolLang, 303 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getTaxonomyCodeList( afiliationList, vidVolDenom );
aCodes.getTaxonomyCodeList( apartnersList, vidVolOrgAffil );
aCodes.getTaxonomyCodeList( avolavailList, vidVolAvail );
aCodes.getTaxonomyCodeList( aLookingForList, vidLookingFor, 303 );


int iCCDA=1188;
int iWorldVision=1228;
int iHLIC=1209;
int iSalvArmy=1219;
int iAGRM=1179;
int iYPN=1232;
int iABS=11545;


session.putValue ("MyIdentifier1","");  // Initialize Value into session Object

String aszPublic="display:none;";

int iVolDirectorytid = 8864;
String aszVolDirectorytid="" + iVolDirectorytid;

// I am looking for... options:

String aszLookingForVID="" + vidLookingFor;

String aszLocalVolTID="" + iLocalVolTID;
String aszGroupFamilyTID="" + iGroupFamilyTID;
String aszVolBoardTID="" + iVolBoardTID;
String aszVolVirtTID="" + iVolVirtTID;
String aszIntrnTID="" + iIntrnTID;
String aszSumIntrnTID="" + iSumIntrnTID;
String aszWorkStudyTID="" + iWorkStudyTID;
String aszJobsTID="" + iJobsTID;
String aszConferenceTID="" + iConferenceTID;
String aszConsultingTID="" + iConsultingTID;
String aszSocJustGrpsTID="" + iSocJustGrpsTID;
String aszLocalOrgsTID="" + iLocalOrgsTID;
// end I am looking for... options

session.putValue ("usertype","");  
session.putValue ("taclite","");  
session.putValue ("upnid","");
session.putValue ("upvid","");
session.putValue ("uplid","");
session.putValue ("uid","");

String aszSubmitDisplay="display:inline;";

if(
		(aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengroundtrip")) 
){ 
	aszSubmitDisplay="display:none;";
} 

%>


<script language="javascript">

<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
function newsletter() {
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if(document.getElementById("newsletter").checked){
		document.getElementById("edit-email").value = document.getElementById("email1addr").value;
		document.getElementById("edit-groups").value = document.getElementById("newsletter").value;
		document.forms["newsletterUMusers"].submit();
		//document.forms["civicrm-subscribe-form"].submit();
		//document.getElementById("civicrm-subscribe-form").submit();
	}
} 
<% } %>
	function clicked_vol(){
//		document.getElementById('volunteertid').checked=true;
		document.getElementById('newsletter').value="38";
	}
	function clicked_both(){
//		document.getElementById('volunteertid').checked=true;
		document.getElementById('newsletter').value="35,38";
	}
	function clicked_org(){
//		document.getElementById('volunteertid').checked=false;
		document.getElementById('newsletter').value="35";
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
	}
function submitBoth() {
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
		setTimeout("newsletter()", 0);
		
		// test to see if the user has checked off that they want to subscribe to the main newsletter
		if((document.getElementById("newsletter").checked) ){
			document.getElementById('containerdiv').style.display='none';
			document.getElementById('processing').style.display='inline';
			newsletter();
			setTimeout("new_account()", 10000);
		}else{
			new_account();
		}
<% }else{ %>
	new_account();
<% } %>
}

$(document).ready(function() {
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	$('input[name=newsletter]').attr('checked', true); 
<% } %>
	var siteUse = $('input:radio[name=siteusetype]:checked').val();
	if (siteUse=="Volunteer"){
		clicked_vol();
	}else if (siteUse=="Both"){
		clicked_both();
	}else if (siteUse=="Organization"){
		clicked_org();
	}else{
		clicked_vol();
		$('input:radio[name=siteusetype]').filter('[value=Volunteer]').attr('checked', true);
	}
 });  

</script>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Create Account</span>
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
<span style="float: left;">Create Account</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; registration</div>
</div>
<% } %>
      	
          <div align="left">

	<div id="body">
<% // for google analytics tracking: %>
	<% String aszGoalPage = "/create/individual"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>
<div id="containerdiv">
<center>
			<h3>Please complete the required fields below to create an account.</h3>
<br></center>
<!--
<img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/fb_connect_89x21.gif"><br>
<img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/facebook-image4.jpg"><br>
<img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/facebook-image3.png"><br>
<img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/facebook-image2.jpg"><br>
<img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/facebook-image1.jpg">
-->
<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>
 <div style="clear:both;"></div>
<div class="profile_box">
<center><h2>Create Account</h2></center>
<html:form action="/register.do" focus="username" target="_self">
<html:hidden property="method" value="processCreateAccount1" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment"/>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>


<table width="97%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
	<tr>
		<TD width=130><b>Username</b> <span class="criticaltext">*</span></TD></tr><tr>
		<TD height="23"><html:text property="username" styleId="username" size="30" styleClass="textinputwhite"/></TD>
		<td width=75></td>
	</tr>
	<tr>
		<TD ><b>Email</b> <span class="criticaltext">*</span></TD></tr><tr>
		<TD height="23"><html:text property="email1addr" styleId="email1addr" size="30" styleClass="textinputwhite"/></TD>
	</tr>
	<tr>
		<TD ><b>New Password</b> <span class="criticaltext">*</span> </TD></tr><tr>
		<TD height="23" colspan="2">
			<html:password property="password1" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
		<TD><b>Confirm Password</b> <span class="criticaltext">*</span> </TD></tr><tr>
		<TD height="23" colspan="2">
			<html:password property="password2" size="30" styleClass="textinputwhite" redisplay="false" />
		</TD>
	</tr>
	<tr>
        <TD ><b>First name</b> <span class="criticaltext">*</span></TD></tr><tr>
        <TD  height="23">
        	<html:text property="namefirst" styleId="namefirst" size="30" styleClass="textinputwhite"/>
        </TD>
	</tr>
	<tr>
		<TD ><b>Last name</b> <span class="criticaltext">*</span></TD></tr><tr>
		<TD  height="23">
			<html:text property="namelast" styleId="namelast" size="30" styleClass="textinputwhite"/>
		</TD>
	</tr>
	<tr>
		<td colspan=3>		
			<br>
			<b>Are you using this site as a volunteer,<br />an organization, or both?</b> <span class="criticaltext">*</span><br>           	
			<html:radio styleClass="radio" value="Volunteer" property="siteusetype" onclick="clicked_vol()" /> Volunteer / Individual<br />
			<html:radio styleClass="radio" value="Organization" property="siteusetype" onclick="clicked_org()" /> Organization &nbsp;
			<html:radio styleClass="radio" value="Both" property="siteusetype" onclick="clicked_both()" /> Both 
		</td>
	</tr>      	
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<tr><td height=10></td></tr>
	<tr><TD colspan=3>
		<b>Newsletters</b><br>
		<table>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="38" id="newsletter" name="newsletter"/>
		</td><td colspan=3>
	        Subscribe me to the<br />ChristianVolunteering.org newsletter
        </TD>
    </tr>
</table></td></tr>
<% } %>
	<tr>
		<td colspan=3>		
			<b>Terms & Conditions</b><br>
			<table>
				<tr>
					<td>
						<html:checkbox styleClass="check" value="Yes" property="agreeflag1"/>
					</td>
					<td>
						I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a><br />and I am at least 16 years old or have<br />my parent's permission to use this site.<span class="criticaltext">*</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<div id="newsletterForms" style="display:none;">


<form id="civicrm-subscribe-form" name="newsletterUMusers" target="ifr" action="http://www.christianvolunteering.org/cms/user/subscribe?destination=cms/subscribe/newsletters" method="post">
    UrbanMinistry Group: <input type="text" name="groups" id="edit-groups" value="group#">
    <br />
    <label for="edit-email" class="email">Email: </label> 
    <input type="text" class="form-text required" id="edit-email" maxlength="100" name="email" />  
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" />   
    <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" />  
</form>


</div>
<div style="padding-top: 5px;">
<span class="criticaltext">*</span>  Required Item
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" id="submitButton" onclick="submitBoth()">Continue</button>
</div>
<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>



</div>



<br><div style="clear:both;"></div><br>


<div class="fb_box">	
<h2>Quick Account Creation</h2>
<div id='fb-login-li'></div>
<br><br><br>
<!--
<a href="<%=aszPortal%>/register.do?method=showFacebookSplash"><img alt="Connect with Facebook" src="<%=aszPortal%>/imgs/fb_connect_89x21.gif"></a>
-->
</div>

<div style="clear:both;"></div>
<b>PLEASE NOTE:</b> Creating an account on <a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a> will also create an 
account for you on <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

			<p>If you are the contact for an organization, fill out this form. 
			After clicking &quot;continue&quot; you will be brought to your personal 
			account summary where you can add your organization's information and volunteer opportunities.</p>

</div>
<div id="processing" style="display:none;">
<center>
 <br><br>
<h2>Please wait while we process your registration... </h2>
 <br><br>
<img src="http://www.christianvolunteering.org/imgs/Processing.gif"/>
</center>
</div>
      </div></div></div>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
