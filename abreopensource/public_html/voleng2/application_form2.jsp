<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<bean:define id="applicinfo" name="applicinfo" type="com.abrecorp.opensource.dataobj.ApplicationInfoDTO"/>

<script language="javascript">
function SubmitPost() {
  document.emailForm.submit() ;
  return false;
}
</script>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->


<%

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}


String aszOppNID="" + applicinfo.getOPPNID();
String aszVolUID="" + applicinfo.getUID();
String aszVolNID="" + applicinfo.getVolNID();
String aszNID="" + applicinfo.getNID();
String aszVID="" + applicinfo.getVID();
String aszLID="" + applicinfo.getLID();



int vidSource=361, vidInternshipLength=362, vidInternshipType=363, vidCitizenStatus=364, vidInternPosType=365,
vidSpecialSkills=366, vidWorkEnvironment=367, vidWorkPopulationPref=368, vidCVDegreeProgram=369;

ArrayList aSourceList = new  ArrayList ( 2 );
ArrayList aPosPrefList = new  ArrayList ( 2 );
ArrayList aSkillsList = new  ArrayList ( 2 );
ArrayList aWorkEnvironList = new  ArrayList ( 2 );
ArrayList aPopPrefList = new  ArrayList ( 2 );
ArrayList aCVDegreeProgList = new  ArrayList ( 2 );
ArrayList aCitizenList = new  ArrayList ( 2 );
ArrayList aInternTypeList = new ArrayList ( 2 );
ArrayList aInternLengthList = new ArrayList ( 2 );

ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 201 );

aCodes.getTaxonomyWeightCodeList( aSourceList, vidSource );
aCodes.getTaxonomyWeightCodeList( aCitizenList, vidCitizenStatus );
aCodes.getTaxonomyWeightCodeList( aInternTypeList, vidInternshipType );
aCodes.getTaxonomyWeightCodeList( aPosPrefList, vidInternPosType );
aCodes.getTaxonomyWeightCodeList( aSkillsList, vidSpecialSkills );
aCodes.getTaxonomyWeightCodeList( aWorkEnvironList, vidWorkEnvironment );
aCodes.getTaxonomyWeightCodeList( aPopPrefList, vidWorkPopulationPref );
aCodes.getTaxonomyWeightCodeList( aCVDegreeProgList, vidCVDegreeProgram );
aCodes.getTaxonomyWeightCodeList( aInternLengthList, vidInternshipLength );


%>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">City Vision Internship Application - Step 2</span>
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
<span style="float: left;">Application</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">search</a> 
	&gt;<a href="http://www.christianvolunteering.org" onclick="window.history.back(); return false;"> volunteer opportunity </a>&gt; city vision internship application   </div>
</div>
<% } %>



<% // for google analytics tracking: %>
	<% String aszGoalPage = "/volunteer/email"; %>
	<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
	<% //@include file="/template/footer_google_analytics.inc"%>
	<% //div id="body" onLoad=urchinTracker("/funnel_G3/step2.html") %>
<% // : end of for google analytics tracking %>
		<div id="body">
	<div align="left">
<br>
	<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="emailForm" property="errormsg" /></pre></FONT>
<form action="<%=aszPortal%>/email.do?method=processCreateApplication2" name="emailForm" method="post" >
<input type="hidden" name="method" value="processCreateApplication2" />
<input type="hidden" name="subdom" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteemail" value="<%=aszEmailHost%>" />

             <!-- volunteer information -->
             <input type="hidden" name="oppnid" value="<%=aszOppNID%>" />
             
<%
int[] a_iOrgNIDs = applicinfo.getORGNIDsArray();
for(int i=0; i<a_iOrgNIDs.length; i++){
%>             
             <input type="hidden" name="orgnids" value="<%=a_iOrgNIDs[i]%>" />
<% 
}
%>             
             <input type="hidden" name="voluid" value="<%=aszVolUID%>" />
             <input type="hidden" name="nid" value="<%=aszNID%>" />
             <input type="hidden" name="vid" value="<%=aszVID%>" />
             <input type="hidden" name="lid" value="<%=aszLID%>" />
             <input type="hidden" name="volnid" value="<%=aszVolNID%>" />
             

	<h3 align= "right">
		<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a> ]</font>
	</h3>
				
	<h2>City Vision Internship Application Form - Page 2</h2>
                		
		<div class="content" >
		  <!-- FORM: HEAD SECTION -->


<!-- FORM: BODY SECTION -->



<input type="hidden" name="method" value="processCreateApplication2" />
<input type="hidden" name="subdom" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteemail" value="<%=aszEmailHost%>" />

             <!-- volunteer information -->

<div class="wfCurrentPage" id="wfPgIndex-1">

<div class="section pageSection ui-draggable" id="tfa_5073135125463">
<div class="htmlSection ui-draggable" id="tfa_9755162050350">
	<div class="htmlContent" id="tfa_9755162050350-HTML">
		<p>Take the time to answer the following questions carefully. Note that all questions with a red asterisk are required.</p>
	</div>
</div>


<fieldset id="tfa_NameAddress" class="section ui-draggable">
	<legend id="tfa_NameAddress-L">
		<div class="sectionEditableTitle" id="tfa_NameAddress-LTXT">Address</div>
	</legend>
	<div id="addr-D" class="oneField   labelsLeftAligned">
		<div id="addr-DW" class="oneFieldWrapper ui-draggable">
			<div id="addr-L" class="label preField reqMark">
				Street Address
			</div>
			<div class="inputWrapper">
				<input type="text"  id="addr" name="addr" value="<%=applicinfo.getAddrLine1() %>" size="40" placeholder="" class="required">
			</div>
		</div>
	</div>
	<div id="city-D" class="oneField   labelsLeftAligned">
		<div id="city-DW" class="oneFieldWrapper ui-draggable">
			<div id="city-L" class="label preField reqMark">
					City
			</div>
			<div class="inputWrapper">
				<input type="text"  id="city" name="city" value="<%=applicinfo.getAddrCity() %>" size="40" placeholder="" class="required">
			</div>
		</div>
	</div>
	<div id="tfa_1727426114964" class="section inline group ui-draggable">

		<div id="prov-D" class="oneField    ">
			<div id="prov-DW" class="oneFieldWrapper ui-draggable">
				<div id="prov-L" class="label preField reqMark">
						State/Province
				</div>
			<div class="inputWrapper">
			<select id="prov" name="prov" class="smalldropdown required"> 
				<option value=""></option> 
				<%
				String aszState = applicinfo.getAddrStateprov();
				if(null != aStateList){
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						out.print(" <option value=\"" + aAppCode.getCSPStateCode() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszState ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getCSPStateName() + "</option> ");
					}
				}
				%>
			</select>
				</div>
			</div>
		</div>
		<div id="postal-D" class="oneField">
			<div id="postal-DW" class="oneFieldWrapper ui-draggable">
				<div id="postal-L" class="label preField reqMark">
					Zipcode
				</div>
				<div class="inputWrapper">
					<input type="text"  id="postal" name="postal" value="<%=applicinfo.getAddrPostalcode() %>" size="28" placeholder="" class="required">
				</div>
			</div>
		</div>
	</div>
	<div id="country-D" class="oneField   labelsLeftAligned ">
		<div id="country-DW" class="oneFieldWrapper ui-draggable">
			<div id="country-L" class="label preField reqMark">
					Country
			</div>
			<div class="inputWrapper">
			<select name="country" id="country"  default="us" class="smalldropdown required">
				<option value=""></option>
				<%
				String aszCountry = applicinfo.getAddrCountryName();
				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
						if(aszOptRqCode.equalsIgnoreCase( aszCountry ) ) out.print(" selected=selected ");
						out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
					}
				}
			  %>
			</select>
			</div>
		</div>
	</div>
</fieldset>




<div id="testimony-D" class="oneField    ">
	<div id="testimony-DW" class="oneFieldWrapper ui-draggable">
		<div id="testimony-L" class="label preField reqMark">
			Please share your testimony as a Christian: how you came to know God and your story with God. (Take the time to answer thoroughly in 200-500 words.)
		</div>
		<br>
		<div class="inputWrapper">
<textarea  cols="132" rows="28" id="testimony" name="testimony" class="validate-custom ^[\s\S]{200,10000}$ required"><%=applicinfo.getTestimony() %></textarea>
		</div>
	</div>
</div>
<div id="geopref-D" class="oneField    ">
	<div id="geopref-DW" class="oneFieldWrapper ui-draggable">
		<div id="geopref-L" class="label preField reqMark">
				Do you have a particular geographic preference?
		</div>
		<br>
		<div class="inputWrapper">
			<select id="geopref" name="geopref" class="required">
				<option value="">Please select...</option>
				<option value="Cannot Relocate" <%= (applicinfo.getGeoPref().equals("Cannot Relocate") ? "selected=\"selected\"" : "" ) %> >I cannot move from my current location</option>
				<option value="Willing to relocate to a major city" <%= (applicinfo.getGeoPref().equals("Willing to relocate to a major city") ? "selected=\"selected\"" : "" ) %> >I strongly prefer any major city</option>
				<option value="Willing to relocate anywhere" <%= (applicinfo.getGeoPref().equals("Willing to relocate anywhere") ? "selected=\"selected\"" : "" ) %> >Willing to serve in either a major city or a small town anywhere</option>
			</select>
		</div>
	</div>
</div>

<div id="resume-D" class="oneField    " style="display:none;">
	<div id="resume-DW" class="oneFieldWrapper ui-draggable">
		<div id="resume-L" class="label preField reqMark">
			<input type="button" id="resume" src="http://www.christianvolunteering.org/template/image/help.gif" value="post resumé" class="volunteer volunteer_short">
		</div>
		<br>
	</div>
</div>


</div>


</div>

<br /><br /><br />
		<input type="button" class="secondaryAction" value="Cancel" onclick="history.go(-1)">
	<input type="submit" class="primaryAction" value="Next Page">
	
</form>



<div id="dialog-form" style="width:400px; display:none;">
	<br>
	<div>
		<form action="<%=request.getContextPath() %>/fileUploadAndSave.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="fileType" id="fileType" value="resume" />
			<input type="hidden" name="source" id="source" value="cvinternapplic" />
			<input type="hidden" name="uid" id="uid" value="<%=applicinfo.getUID() %>" />
			<center>
				<div style="padding-bottom:60px;">
					<h2 style="float:left; " >Please Upload your Resum&eacute; here:</h2>
					<div style="text-align:left; float:left; margin-left: 10px; padding-top:6px;">
						<input type="file" name="theFile"/> 
						<br /><br /><br />
						<input class="volunteer" type="submit" value="upload file" id="upload_resume" src="http://www.christianvolunteering.org/template/image/help.gif" >
					</div>
				</div>
				<br clear="all" />
				<br /><br />
				<HR width="100%">							
				<br /><br />
					<h3 style="float:left; padding-top:17px;" >Or download your current one for review here:</h3>
					<a style="float:left; color:#FFFFFF;" id="download_resume" href="<%=request.getContextPath() %>/fileDownload.do?resume=true&uid=<%=applicinfo.getUID() %>" class="volunteer button" src="http://www.christianvolunteering.org/template/image/help.gif">
						download resum&eacute;
					</a> 
					<br />
					<h3 style="float:left; padding-top:17px;" >To delete your resum&eacute;, please click here:</h3>
					<a style="float:left; color:#FFFFFF;" id="delete_resume" href="<%=request.getContextPath() %>/register.do?method=processDeleteResume" class="volunteer button" src="http://www.christianvolunteering.org/template/image/help.gif">
						delete resum&eacute;
					</a> 
			</center>
		</form>
	</div>
</div>
	
</div>
</div>
</div>
</div>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/js/jquery-ui-1.8.4.custom.min.js"></script>

<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.base.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.button.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.dialog.css" rel="stylesheet" />
<link type="text/css" href="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/themes/base/jquery.ui.theme.css" rel="stylesheet" />

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/jquery-1.4.2.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.button.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/jquery-ui-1.8.4/development-bundle/ui/jquery.effects.core.js"></script>
<script type="text/javascript">			
$(function() {
	$('#resume').click(function() {
		$('#dialog-form').dialog('open');
	});
});
$(function() {
	$('#upload_resume').click(function() {
		$('#dialog-form').dialog('close');
	});
});
$(function() {
	$('#download_resume').click(function() {
		$('#dialog-form').dialog('close');
	});
});
$(function() {
	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	$("#dialog").dialog("destroy");
	
	$("#dialog-form").dialog({
		autoOpen: false,
		width: 400,
		modal: true,
		buttons: {
			'cancel': function() {
				$(this).dialog('close');
			}
		},
		close: function() {
			submitBoth();
			allFields.val('').removeClass('ui-state-error');
		}
	});
	$('#submitButton').button().click(function() {
		$('#dialog-form').dialog('open');
	});
});
</script>
<style>
.ui-dialog{
	width:400px;
}
.dialog-form { 
	height: 159; 
}
</style>

<!-- Google Code for ChristianVolunteering.org Registered User Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "9qG9CLzeiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=9qG9CLzeiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
