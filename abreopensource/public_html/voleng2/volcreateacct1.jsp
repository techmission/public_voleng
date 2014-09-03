<%@ include file="/template_include/topjspnologin1.inc" %>

<%@ include file="/template/header.inc" %>
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<%@ include file="/template/navigation.inc" %>

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
</style>

<SCRIPT TYPE="text/javascript">
<!--
function popup(mylink, windowname)
{
if (! window.focus)return true;
var href;
if (typeof(mylink) == 'string')
   href=mylink;
else
   href=mylink.href;
window.open(href, windowname, 'width=500,height=400,scrollbars=yes');
return false;
}
//-->

</SCRIPT>


<noscript>
Javascript is disabled.  This form will not work for you.<br><br>
Please enable Javascript in your browser<!-- <% //or click <a href="%=request.getContextPath()>/register.do?method=showVolCreateNonJS">here</a> to proceed.%>-->
</noscript>

<script language="javascript">

<% if( aszHostID.equalsIgnoreCase("volengworldvision") ){ %>
function newsletterPartner() {
	// test to see if the user has checked off that they want to subscribe to the partner newsletter
	//if(document.getElementByName(individualForm).elements["partnernewsletter"].checked){
	if(document.getElementById("partnernewsletter").checked){
		document.getElementById("email").value = document.getElementById("email1addr").value;
		document.getElementById("NAME_FIRST").value = document.getElementById("namefirst").value;
		document.getElementById("NAME_LAST").value = document.getElementById("namelast").value;
		document.getElementById("txtADDR_1").value = document.getElementById("addrline1").value;
		document.getElementById("txtADDR_CITY").value = document.getElementById("addrcity").value;
		document.getElementById("txtADDR_ZIP").value = document.getElementById("addrpostalcode").value;
		document.getElementById("cboADDR_STATE_CD").value = document.getElementById("addrstateprov").value;
		document.getElementById("cboADDR_COUNTRY_NAME").value = document.getElementById("addrcountryname").value;
		document.forms["newsLetterSignUp"].submit();
	}
}
<% } else if( aszHostID.equalsIgnoreCase("volengroundtrip") ){ %>
function newsletterPartner() {
	// test to see if the user has checked off that they want to subscribe to the partner newsletter
	if(document.getElementById("partnernewsletter").checked){
		document.getElementById("email").value = document.forms["individualForm"].elements["email1addr"].value;
		document.document.forms["newsletterRoundTrip"].submit();
	}
}
<% } %>
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
function newsletter() {
	// test to see if the user has checked off that they want to subscribe to the main newsletter
	if(document.getElementById("newsletter").checked){
		document.getElementById("email").value = document.getElementById("email1addr").value;
		document.getElementById("groups").value = document.getElementById("newsletter").value;
		document.forms["newsletterUMusers"].submit();
	}
} 
<% } %>
function new_account() {
	document.forms["individualForm"].submit();	
} 

function submitBoth() {
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<% if( (aszHostID.equalsIgnoreCase("volengworldvision") ) || (aszHostID.equalsIgnoreCase("volengroundtrip") ) ){ %>
		setTimeout("newsletterPartner()", 0);
	<% } %>
		setTimeout("newsletter()", 0);
		
		// test to see if the user has checked off that they want to subscribe to the main newsletter
		if((document.getElementById("newsletter").checked) 
	<% if( 
		(aszHostID.equalsIgnoreCase("volengworldvision") ) || 
		(aszHostID.equalsIgnoreCase("volengroundtrip") ) 
	){ %>
			|| (document.getElementById("partnernewsletter").checked)
	<% } %>
		){
			document.getElementById('form1').style.display='none';
			document.getElementById('processing').style.display='inline';
			
			setTimeout("new_account()", 10000);
		}else{
			new_account();
		}
<% }else{ %>
	new_account();
<% } %>
}
</script>


<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		//vidWorkStudy=264, 
		vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
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

	function clicked_vol(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
		document.forms["individualForm"].elements["newsletter"].value="38";
	}
	function clicked_both(){
		document.getElementById('volun').style.display='inline';
		document.getElementById('volunteertid').checked=true;
		document.getElementById('publicprofile').style.display='inline';
//		document.getElementById('localvoltid').checked=true;
//		document.getElementById('volvirttid').checked=true;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=true;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=true;
		//document.getElementById('profilephoto').style.display='inline';
		document.forms["individualForm"].elements["newsletter"].value="35,38";
	}
	function clicked_org(){
		document.getElementById('volun').style.display='none';
		document.getElementById('volunteertid').checked=false;
		document.getElementById('publicprofile').style.display='none';
//		document.getElementById('localvoltid').checked=false;
//		document.getElementById('volvirttid').checked=false;
		document.getElementById('lookingforarray[<%=aszLocalVolTID%>]').checked=false;
		document.getElementById('lookingforarray[<%=aszVolVirtTID%>]').checked=false;
		//document.getElementById('profilephoto').style.display='none';
		document.forms["individualForm"].elements["newsletter"].value="35";
	}
	function clicked_public(box){
		var vis = (box.checked) ? "block" : "none";
		//document.getElementById('publicprofile').style.display=vis;
	}

$(document).ready(function() {
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	$('input[name=newsletter]').attr('checked', true); 
<% if(
		(aszHostID.equalsIgnoreCase("volengworldvision")) ||
		(aszHostID.equalsIgnoreCase("volengroundtrip"))
){ %>
	$('input[name=partnernewsletter]').attr('checked', true); 
<% } %>
<% } %>
	var siteUse = $('input:radio[name=voloropp]:checked').val();
	if (siteUse=="Volunteer"){
		clicked_vol();
	}else if (siteUse=="Both"){
		clicked_both();
	}else if (siteUse=="Organization"){
		clicked_org();
	}else{
		clicked_vol();
		$('input:radio[name=voloropp]').filter('[value=Volunteer]').attr('checked', true);
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
			<h2>Please complete the required fields below to create an account.</H2>
<br>
<b>PLEASE NOTE:</b> Creating an account on <a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a> will also create an 
account for you on <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

<% if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>
			<p>This section of the RoundTripMissions site uses a separate account on our partner site ChristianVolunteering.org, which requires a separate login. An account is required either to connect with any service opportunity or to post any service opportunity.</p>
<% } else { %>
			<p>If you are the contact for an organization, fill out this form. 
			After clicking &quot;continue&quot; you will be brought to your personal 
			account summary where you can add your organization's information and volunteer opportunities.</p>
<% } %>

					<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="individualForm" property="errormsg" /></pre></FONT>
				<br>

<div id="form1">
<html:form action="/register.do?indivaccnt" focus="username" target="_self">
<html:hidden property="method" value="processRegistration" />
<html:hidden property="subdomain" value="<%=aszSubdomain%>" />
<html:hidden property="personinternalcomment"/>

<script type="text/javascript">

var d = new Date()
var gmtHours = d.getTimezoneOffset()*(-60);
document.write("<input type=\"hidden\" id=\"timezone\" name=\"timezone\"  value=\" " + gmtHours + " \">");

</script>

            <!--nested table -->
	<table width="80%" border="0" cellpadding="0" cellspacing="0" id="splash" align="center" >
              <!-- MSTableType="layout" -->
				
		<tr>
                <TD width=130><b>Username</b> <span class="criticaltext">*</span></TD>
                <TD><html:text property="username" styleId="username" size="30" styleClass="textinputwhite"/></TD>
				<td height="23"></td>
		</tr>
		<tr>
        		<TD ><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><html:text property="email1addr" styleId="email1addr" size="35" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD ><b>New Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<html:password property="password1" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
		</tr>
		<tr>
                <TD ><b>Confirm Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23" colspan="2">
					<html:password property="password2" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
		</tr>
		<tr>
                <TD ><b>First name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><html:text property="namefirst" styleId="namefirst" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD ><b>Last name</b> <span class="criticaltext">*</span></TD>
                <TD  height="23"><html:text property="namelast" styleId="namelast" size="25" styleClass="textinputwhite"/></TD>
		</tr>
              	
<tr><td colspan=3>				
				
<br>
<b>Are you using this site as a volunteer, an organization, or both?</b> <span class="criticaltext">*</span><br>           	
<html:radio styleClass="radio" value="Volunteer" property="voloropp" onclick="clicked_vol()" /> Volunteer &nbsp;
<html:radio styleClass="radio" value="Organization" property="voloropp" onclick="clicked_org()" /> Organization &nbsp;
<html:radio styleClass="radio" value="Both" property="voloropp" onclick="clicked_both()" /> Both 
            	
<br><br>
</td></tr>

<tr><td colspan=3>				
<div id="volun" style="display: none;">

<table border=0 cellpadding=2 cellspacing=2>        	
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="<%=aszVolDirectorytid%>" name="volunteertid" id="volunteertid" onclick="clicked_public(this)"/></td>
				<td colspan=2>Please post my user profile in your <span style="color: rgb(47, 85, 131); cursor: help;" title="Other users may contact me, including organizations needing volunteers for potential volunteer opportunities" onmouseover="this.style.cursor="help";">public directory</span>.</td>
			</tr>
			<tr>
				<td height="30" valign="top">
				<input type="checkbox" styleClass="check" value="1" name="subscribe" id="subscribe" onclick="clicked_public(this)" /></td>
				<td colspan=2>Send me weekly updates of new opportunities matching my <% if(aszSecondaryHost.equalsIgnoreCase("voelngivol")){%>Service<%}else{%>Ministry<%}%> Calling Profile in the following areas:</td>
			</tr>

</table>
<br>


<div id="publicprofile" style="<%=aszPublic%>">
<div id="better-select-edit-taxonomy-<%=aszLookingForVID%>" class="better-select">
		<label>I am Looking for: <span class="criticaltext">*</span> </label>
		<div class="form-checkboxes form-checkboxes-noscroll">
<table ><tr><td>
              <%
					a_iContainer= new int[100];
					iTemp = 0;
//					a_iContainer = userprofile.getUSPLookingForArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < aLookingForList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if(iSubType!=iDisasterRlfTID && iSubType!=iConferenceTID && iSubType!=iConsultingTID){
								out.println(" <div id=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"lookingforarray\" id=\"lookingforarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
/*								
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/								
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==4)	out.println("</td><td>");
							}
						}
					} else { 
						for(int index=0; index < aLookingForList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aLookingForList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							if(iSubType!=iDisasterRlfTID && iSubType!=iConferenceTID && iSubType!=iConsultingTID){
								out.println(" <div id=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\"> " );
								out.println("	<label class=\"option\" for=\"edit-taxonomy-"+ aszLookingForVID+"-"+ aAppCode.getAPCIdSubType() + "\" > " );
								out.print("	<input type=\"checkbox\"  name=\"lookingforarray\" id=\"lookingforarray[" + aAppCode.getAPCIdSubType() + "]\" value=\"" + aAppCode.getAPCIdSubType() + "\" " );
/*								
								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/								
								out.println(" /> ");
								out.println(aAppCode.getAPCDisplay());
								out.println("	</label>");
								out.println("</div>");
								iTemp++;
								if(index==4)	out.println("</td><td>");
							}
						}
					} 					
				%>
</td></tr></table></div>
<br>
</div>

<div id="address">
Address:
<div  style="border: 1px solid #666; margin: 10;">
<table>
				<tr>
                <TD width=120>
				<b>Phone</b> <span class="criticaltext">*</span>
				</TD>
                <TD ><html:text property="phone1" styleId="phone1" size="25" styleClass="textinputwhite"/></TD>
					<td height="23"></td>
				</tr>
		<tr>
        <TD>Street</TD>
        <TD height="23"><html:text property="addrline1" styleId="addrline1" size="25" styleClass="textinputwhite"/></TD>
		</tr>

		<tr>
        <TD>City</TD>
        <TD height="23"><html:text property="addrcity" styleId="addrcity" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		
		<tr>
<TD>State/Province</TD>
<TD height="23">
                <SELECT id="addrstateprov" name="addrstateprov" class="smalldropdown"> 
					<option value=""></option>
					<%
					if(null != aStateList){
						for(int index=0; index < aStateList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCSPStateCode();
							out.println(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
						}
					}
					%>
              </SELECT><!--&nbsp; Other Province html:text property="addrprov" styleId="addrprov" size="12" styleClass="textinputwhite"/-->
</TD>
</tr>
		<tr>
                <TD><b>Postal Code</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=2 height="23"><html:text property="addrpostalcode" styleId="addrpostalcode" size="12" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD><b>Country</b> <span class="criticaltext">*</span> </TD>
                <TD colspan=4>
                	<SELECT id="addrcountryname" name="addrcountryname" class="smalldropdown" > 
					<option value=""></option>
					<%
					if(null != aCountryList){
						for(int index=0; index < aCountryList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
							if(null == aAppCode) continue;
							String aszOptRqCode = aAppCode.getCTRIso();
							out.println(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
							if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
							out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
						}
					}
					%>
                    </SELECT>
                </TD>
              		<td height="27"></td>
		</tr>
</table>
</div>
<br>
</div>

<table border=0>        	
			<tr>
				<td colspan=2>
				Volunteer Availability</td>
				<td colspan=2 height=30>
		    <select class="smalldropdown" id="volavailtid" name="volavailtid" style="margin-top: 5px;" >
               <option value=""></option>
               <%
					iTemp = 0;
					for(int index=0; index < avolavailList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)avolavailList.get(index);
						if(null == aAppCode) continue;
						int iTid = aAppCode.getAPCIdSubType();
						String aszDisplay = aAppCode.getAPCDisplay();
						out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				%>
			</select>
				
				</td>
			</tr>
</table>

    <div id="better-select-edit-taxonomy-<%out.print(vidVolSkill);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Personal Skills: </b></label>
		    <!--html:select property="skilltypesarray" multiple="true" size=5 -->
		    <!--select name="skilltypesarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[50];
					iTemp = 0;
//					a_iContainer = userprofile.getUSPSkillTypesArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4748 ||
								iSubType == 4749){
							}else if (iSubType == 4745){
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Musician</label> ");
								out.println(" </div> ");
							}else if (iSubType == 8122){
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Deaf Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
								out.println(" </div> ");
							}
							iTemp++;
						}
					} else { 
						for(int index=0; index < askillsList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolSkill+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"skilltypesarray\" name=\"skilltypesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
					} 					
				%>
			</div>
			</div>
			</div>
			<!--/select-->
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidVolInterestArea);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Volunteer Interest Area : </b></label>
		    <!--html:select property="serviceareasarray" multiple="true" size=5 -->
		    <!--select name="serviceareasarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[50];
					iTemp = 0;
//					a_iContainer = userprofile.getUSPServiceAreasArray();
					if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ 
						for(int index=0; index < ainterestareaList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
							if(null == aAppCode) continue;
							String aszDisplay = aAppCode.getAPCDisplay();
							int iSubType = aAppCode.getAPCIdSubType();
							if (iSubType == 4760 || // Bible Study
								iSubType == 4764 || // Church Planting
								iSubType == 4772 || // Evangelism
								iSubType == 4773 || // Family / Adults Ministry
								iSubType == 4783 || // Single Parents/Crisis Pregnancy
								iSubType == 4787 || // Vacation Bible School
								iSubType == 4789 || // Women's Ministry
								iSubType == 7341 || // Christian/Catholic Schools
								iSubType == 7342 ){ // Religious Education
							}else if (iSubType == 4767){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Disabilities Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 6843){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Senior / Elderly Outreach</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4774){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Food Service / Hunger</label> ");
								out.println(" </div> ");
							}else if (iSubType == 4782){
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >Prison Outreach</label> ");
								out.println(" </div> ");
							}else{
								out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
								out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
								out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
								out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
								out.println(" </div> ");
							}
							iTemp++;
						}
					} else { 
						for(int index=0; index < ainterestareaList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)ainterestareaList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolInterestArea+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"serviceareasarray\" name=\"serviceareasarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/
							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
					} 					
				%>
			</div>
			</div>
			</div>
			<!--/html:select-->
<br>
    <div id="better-select-edit-taxonomy-<%out.print(vidVolLang);%>" class="better-select betterfixed">
    <div class="form-item">
	<label><b>Languages Spoken:  </b></label>
		    <!--html:select property="languagesarray" multiple="true" size=5 -->
		    <!--select name="languagesarray" multiple="true" size=5 width=100-->
		    <div class="form-checkboxes form-checkboxes-scroll">
               <%
					a_iContainer= new int[255];
					iTemp = 0;
//					a_iContainer = userprofile.getUSPLanguagesArray();
						for(int index=0; index < alangList.size(); index++){
							AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)alangList.get(index);
							if(null == aAppCode) continue;
							int iSubType = aAppCode.getAPCIdSubType();
							String aszDisplay = aAppCode.getAPCDisplay();
							out.println(" <div id=\"edit-taxonomy-"+vidVolLang+"-" + aAppCode.getAPCIdSubType() + "-wrapper\" class=\"form-item\" > " );
							out.println("		<label class=\"option\" for=\"edit-taxonomy-"+vidVolLang+"-" + aAppCode.getAPCIdSubType() + "\" > " );
							out.println("			<input type=\"checkbox\" id=\"languagesarray\" name=\"languagesarray\" value=" + aAppCode.getAPCIdSubType() + " class=\"form-checkbox form-checkboxes-scroll\" " );
/*								for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
									iTemp = a_iContainer[indexArray];
									if(iTemp==iSubType) out.print(" checked ");
								}
*/							out.println(" >" + aAppCode.getAPCDisplay() + "</label> ");
							out.println(" </div> ");
							iTemp++;
						}
				%>
			</div>
			</div>
			</div>
			<!--/html:select-->
			<br>
			<br>
<label><b>
Personal Volunteer Resum&eacute;  (PUBLICLY VISIBLE) <A href="javascript: alert('Enter your personal resum&eacute;  in the box to the right. When you indicate interest in a volunteer opportunity, we will e-mail your resum&egrave to the organization for you. Organizations will also be able to search resum&eacute; s to find the volunteers that they are looking for.')">[What's This?]</A>
</b></label>
			<html:textarea rows="20" cols="70" property="volresume"/>

<table border=0>        	
<% if( !(aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>

	<tr><td colspan=4 height="10"></td></tr>

			<tr><td colspan=2>
					Are You a Christian?</td><td>
					<html:radio styleClass="check" value="Yes" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='inline';" /> Yes
					<html:radio styleClass="check" value="No" property="indivchristian" onclick="javascript:document.getElementById('faith').style.display='none';" /> No
			</td></tr>
		<tr><td colspan=3>		
		<div id="faith"  style="display: none;">
		<br>
			<table width="519" border="0" cellpadding="0" cellspacing="0" id="splash">
			<tr>
				<td height="30" colspan=2>Do You Attend Church Regularly?</td>
				<td>

						<html:radio styleClass="check" value="Yes" property="attendchurch"/> Yes
						&nbsp; &nbsp; 
						<html:radio styleClass="check" value="No" property="attendchurch"/> No
				</td>
			</tr>
			<tr>
				<td height="30">Denominational Affiliation</td>
				<td colspan=2>
						<select id="indivdenomaffiltid" name="indivdenomaffiltid" class="smalldropdown">
							<option value=""></option>
							<%
								iTemp = 0;
								for(int index=0; index < afiliationList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)afiliationList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
									if(iTid == iTemp ) out.print(" selected=selected ");
									out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
								}
							%>
						</select>
				</td>
			</tr>
			
			<tr>
				<td height="30">Other Affiliation</td>
				<td colspan=2>
						<SELECT id="indivotheraffil1tid" name="indivotheraffil1tid" class="smalldropdown" style="margin-top: 5px;"
			<% // set to change only if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) &&
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengabs")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 			
			){ 
			%>
				onchange="javascript:document.getElementById('affil2').style.display='inline';"
			 <% } %>
			> 
			<option value=""></option>
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
			if(aszHostID.equalsIgnoreCase("volengccda")){
				iTemp = iCCDA;
				aszTemp="Christian Community Development Association (CCDA)";
			} else if(aszHostID.equalsIgnoreCase("volengfia")){
				iTemp = iWorldVision;
				aszTemp="World Vision";			
			} else if(aszHostID.equalsIgnoreCase("volenghlic")){
				iTemp = iHLIC;
				aszTemp="Here's Life Inner City";			
			} else if(aszHostID.equalsIgnoreCase("volengsalvationarmy")){
				iTemp = iSalvArmy;
				aszTemp="Salvation Army";			
			} else if(aszHostID.equalsIgnoreCase("volengagrm")){
				iTemp = iAGRM;
				aszTemp="Association of Gospel Rescue Missions (AGRM)";			
			} else if(aszHostID.equalsIgnoreCase("volengabs")){
				iTemp = iABS;
				aszTemp="American Bible Society";			
			} else if(aszHostID.equalsIgnoreCase("volengyouthpartners")){
				iTemp = iYPN;
				aszTemp="YouthPartnersNET";			
			} else {
				iTemp = 0;
			}
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 22118 ||
										iSubType == 778 ||
										iSubType == 8059 ||
										iSubType == 1222){
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
				<td></td><td colspan=2>
					<div id="affil2" 
			<% // hide if not on ccda, putyourfaithinaction, hlic, salvationarmy, youthpartnersnet;
			   // if on specialized partner, show 2nd div by default, b/c first will be set to partner
			if((aszHostID.equalsIgnoreCase("volengccda")==false) &&
					(aszHostID.equalsIgnoreCase("volengfia")==false) &&
					(aszHostID.equalsIgnoreCase("volenghlic")==false) &&
					(aszHostID.equalsIgnoreCase("volengsalvationarmy")==false) && 
					(aszHostID.equalsIgnoreCase("volengagrm")==false) &&
					(aszHostID.equalsIgnoreCase("volengabs")==false) &&
					(aszHostID.equalsIgnoreCase("volengyouthpartners")==false) 			
			){ %>
				style="display: none;"
			<% } %>
			> 
						<SELECT id="indivotheraffil2tid" name="indivotheraffil2tid" class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil3').style.display='inline';"/>
			<option value=""></option>
			
			<% // set values for customized partner (subdomain) to be first choice for organizational affiliation
								iTemp = 0;
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 8059 ||
										iSubType == 1222){
									}else{
										out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
										if(iTid == iTemp ) out.print(" selected=selected ");
										out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
									}
								}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td><td colspan=2>
					<div id="affil3" style="display: none;">
						<SELECT id="indivotheraffil3tid" name="indivotheraffil3tid" class="smalldropdown" style="margin-top: 5px;" />
						<%//onchange="javascript:document.getElementById('affil4').style.display='inline';"%> 
							<option value=""></option>
							<%
								iTemp = 0;
								for(int index=0; index < apartnersList.size(); index++){
									AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
									if(null == aAppCode) continue;
									int iTid = aAppCode.getAPCIdSubType();
									String aszDisplay = aAppCode.getAPCDisplay();
									int iSubType = aAppCode.getAPCIdSubType();
									if (iSubType == 8059 ||
										iSubType == 1222){
									}else{
										out.print(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
										if(iTid == iTemp ) out.print(" selected=selected ");
										out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
									}
								}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td><td colspan=2>
					<div id="affil4" style="display: none;">
						<SELECT class="smalldropdown" style="margin-top: 5px;" onchange="javascript:document.getElementById('affil5').style.display='inline';"> 
							<option value=""></option>
							<%
							aszTemp = "";
							for(int index=0; index < apartnersList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getAPCDisplay();
								out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
								if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
			<tr>
				<td></td><td colspan=2>
					<div id="affil5" style="display: none;">
						<SELECT class="smalldropdown" style="margin-top: 5px;"> 
							<option value=""></option>
							<%
							aszTemp = "";
							for(int index=0; index < apartnersList.size(); index++){
								AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)apartnersList.get(index);
								if(null == aAppCode) continue;
								String aszOptRqCode = aAppCode.getAPCDisplay();
								out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
								if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ) out.println(" selected=selected ");
								out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
							}
							%>
						</SELECT>
					</div>
				</td>
			</tr>
		</table>
	</div>
</td></tr>

<% } %>
</table></div></td></tr>

<tr><td colspan=3>
<table width="540" border="0" cellpadding="0" cellspacing="0" id="splash" >
<% if (! (	aszSecondaryHost.equalsIgnoreCase("volengivol")) ){ %>
	<tr><td height=10></td></tr>
	<tr><TD height="20" colspan=4><b>Newsletters</b></TD></tr>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="38" id="newsletter" name="newsletter"/>
		</td><td colspan=3>
	        Subscribe me to the ChristianVolunteering.org newsletter
        </TD>
    </tr>
<% if(aszHostID.equalsIgnoreCase("volengworldvision")) { %>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="worldvision" id="partnernewsletter" name="partnernewsletter"/>
		</td><td colspan=3>
	        Subscribe me to the World Vision newsletter
        </TD>
     </tr>
<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")) { %>
	<tr>     
        <TD height="30" >
			<input type="checkbox" value="roundtrip" id="partnernewsletter" name="partnernewsletter"/>
		</td><td colspan=3>
	        Subscribe me to Christianity Today International's Round Trip newsletter.
            By checking, you will sign up for the free Round Trip Missions newsletter--helping church leaders make the most of short-term missions.
        </TD>
    </tr>
<% } %>
<% } %>
		<tr>
			<TD height="20" colspan=4><b>Terms & Conditions</b></TD>
        </tr>
		<tr>     
	        <TD height="30" >
			<html:checkbox styleClass="check" value="Yes" property="agreeflag1"/>
			</td><td colspan=3>
	        I agree with the <a href="http://www.urbanministry.org/terms" onClick="return popup(this, 'help')">Terms & Conditions</a>.<span class="criticaltext">*</span>
	        </TD>
        </tr>
        <tr>
        	<TD height="30" >
	        	<html:checkbox styleClass="check" value="Yes" property="agreeflag2"/>
        	</td><td colspan=3>
	        	I am at least 16 years old or have my parent's permission to use 
	        	<a href="http://<%=aszSubdomain%>"><%=aszSubdomain%></a>.<span class="criticaltext">*</span>
        	</TD>
        </tr>

<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>
		<tr>
			<TD height="30">
				<html:checkbox styleClass="check" value="Yes" property="agreeflagworldvision"/>
			</td><td colspan=3>
				I agree to the World Vision <a href="http://www.worldvision.org/content.nsf/pages/privacy-policy">privacy policy</a>.<span class="criticaltext">*</span>
			</TD>
		</tr>
<% } %>
<NOSCRIPT>
<tr>
                        <td width="40">&nbsp;</td>
                        <td width="90">&nbsp;</td>
                <TD height="75">
                  <DIV class=clear style="HEIGHT: 10px"></DIV>
                  <INPUT type=hidden name=submit> <INPUT class=submit type=hidden value=Continue>
                 </TD>
                        </tr>

</NOSCRIPT>
				<tr>
                	<TD colspan=4>&nbsp;<span class="criticaltext">*</span>  Required Item</td>
              		<td height="30"></td>
				</tr>

            </TABLE>
</td></tr>
</table>

</html:form>



<div id="newsletterForms" style="display:none;">

<% if(aszHostID.equalsIgnoreCase("volengworldvision")){ %>

<form name="newsLetterSignUp" target="_blank" action="https://webi1.worldvision.org/worldvision/BI_Global/Signup.aspx" method="get" style="margin:0;padding:0;" onsubmit="window.open(this.action,'nlsu','scrolling=0,toolbar=0,width=400,height=400'); return false;" > 
<input type="text" name="email" id="search2" value="e-mail address" onfocus="if(this.value=='e-mail address'){this.value='';}" onblur="if(this.value==''){this.value='e-mail address';}" style="height:15px; font-size:11px;"/> 
	<input type="text" name="NAME_FIRST">
	<input type="text" name="NAME_LAST">
	<input type="text" name="txtADDR_1">
	<input type="text" name="txtADDR_CITY">
	<input type="text" name="txtADDR_ZIP">
	<input type="text" name="cboADDR_STATE_CD">
	<input type="text" name="cboADDR_COUNTRY_NAME">
<input type="image" src="<%=aszPortal%>/imgs/blank.gif" /> 
</form>

<% } else if(aszHostID.equalsIgnoreCase("volengroundtrip")){ %>

<form name="newsletterRoundTrip" action="http://lists.christianitytoday.com/subscribe/subscribe.tml" method="POST" target="_blank">
<input type="text" class="text" name="email" value="your e-mail address" onFocus="this.value='';">
<input type="hidden" name="list" value="roundtripmissions">
<input type="hidden" name="confirm" value="one"><input type="hidden" name="showconfirm" value="F">
<input type="hidden" name="url" value="https://w1.buysub.com/servlet/OrdersGateway?cds_mag_code=TDY&cds_page_id=45824">
<input type="image" name="imageField" src="<%=aszPortal%>/imgs/blank.gif" width="15" height="17" title="Go" alt="Go" style="vertical-align: middle;">
</form>

<% } %>

<form id="civicrm-subscribe-form" name="newsletterUMusers" target="ifr" action="http://www.christianvolunteering.org/cms/user/subscribe?destination=cms/subscribe/newsletters" method="post">
    UrbanMinistry Group: <input type="text" name="groups" id="edit-groups" value="group#">
    <br />
    <label for="edit-email" class="email">Email: </label> 
    <input type="text" class="form-text required" id="edit-email" maxlength="100" name="email" />  
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" />   
    <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" />  
</form>


</div>
<div align=center>
<button type="button" id="submitButton" onclick="submitBoth()">Continue</button>
</div>
<iframe style="display:none;" name="ifr" src="javascript:'&lt;html&gt;&lt;/html&gt;';" height="0" width="0"></iframe>

</div>



<div id="processing" style="display:none;">
<center>
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
