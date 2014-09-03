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
<link href="http://www.urbanministry.org/sites/all/modules/betterselect/betterselect.css" rel="stylesheet" type="text/css" />
<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->

<style>
.better-select div.form-item {
    margin-left: 0px;
    max-width: 950px;
    padding-left:1.5em;
    text-indent:-1.5em;
}
div.two_column {
    -moz-column-count: 2;
    -moz-column-gap: 5px;
}
.better-select div.form-checkboxes-scroll {
    max-width: 950px;
    width: 700px;
}    
</style>
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
int iTemp=0;

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

ArrayList aCVCSitesList = new ArrayList ( 2 );

ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
ArrayList aServiceList = new  ArrayList ( 2 );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );

aCodes.getTaxonomyWeightCodeList( aSourceList, vidSource );
aCodes.getTaxonomyWeightCodeList( aCitizenList, vidCitizenStatus );
aCodes.getTaxonomyWeightCodeList( aInternTypeList, vidInternshipType );
aCodes.getTaxonomyWeightCodeList( aPosPrefList, vidInternPosType );
aCodes.getTaxonomyWeightCodeList( aSkillsList, vidSpecialSkills );
aCodes.getTaxonomyWeightCodeList( aWorkEnvironList, vidWorkEnvironment );
aCodes.getTaxonomyWeightCodeList( aPopPrefList, vidWorkPopulationPref );
aCodes.getTaxonomyWeightCodeList( aCVDegreeProgList, vidCVDegreeProgram );
aCodes.getTaxonomyWeightCodeList( aInternLengthList, vidInternshipLength );

aCodes.getCVInternOrgSitesList( aCVCSitesList );

java.util.Date dDOB = applicinfo.getDOBDt();
java.text.DateFormat sdf = new java.text.SimpleDateFormat("M/d/yyyy");
String aszDOB = "";
if(dDOB != null){
	aszDOB = sdf.format(dDOB);  
}
int[] a_iInternTypes = applicinfo.getInternTypeTIDsArray();
boolean b_undergradTuition = false, b_paid_intern=false, b_bakke = false, b_mstsm = false;
int iUndergradTuition = 38793, iPaidIntern = 38794, iBakke=38831, iTechFellow=38832;
int iAddictionStudies = 38814, iNonprofitMgmnt=38815, iUrbanMissions=38816, iMSTSM=38830, 
	iDTLBakke=39073, iDMinBakke=39074, iMBABakke=39075, iMSCEBakke=39076, iMGULBakke=39077;
for(int index=0; index < a_iInternTypes.length; index++){
	if(a_iInternTypes[index]==iUndergradTuition)	
		b_undergradTuition=true;
	if(a_iInternTypes[index]==iPaidIntern)			
		b_paid_intern=true;
	if(a_iInternTypes[index]==iBakke)				
		b_bakke=true;
	if(a_iInternTypes[index]==iTechFellow)				
		b_mstsm=true;
}

%>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">City Vision Internship Application - Step 3</span>
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
<form method="post" action="<%=aszPortal%>/email.do?method=processCreateApplication3" name="emailForm" method="post" >

<input type="hidden" name="method" value="processCreateApplication3" />
<input type="hidden" name="subdom" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteemail" value="<%=aszEmailHost%>" />

             <!-- volunteer information -->
             <input type="hidden" name="nid" value="<%=aszNID%>" />
             <input type="hidden" name="vid" value="<%=aszVID%>" />
             <input type="hidden" name="oppnid" value="<%=aszOppNID%>" />
             
            
             <input type="hidden" name="voluid" value="<%=aszVolUID%>" />
             <input type="hidden" name="volnid" value="<%=aszVolNID%>" />

	<h3 align= "right">
		<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a> ]</font>
	</h3>
	<h2>City Vision Internship Application Form - Page 3</h2>

<div class="wfPage " id="wfPgIndex-2">
	<div class="section pageSection ui-draggable" id="tfa_5073136175949">

<div class="htmlSection ui-draggable" id="tfa_9755162050350">
	<div class="htmlContent" id="tfa_9755162050350-HTML">
		<p>Take the time to answer the following questions carefully. Note that all questions with a red asterisk are required.</p>
	</div>
</div>

		<div id="pospreftid-D" class="oneField    ">
			<div id="pospreftid-DW" class="oneFieldWrapper ui-draggable">
				<div id="pospreftid-L" class="label preField reqMark">
						What type of position do you prefer?
				</div>

				<div class="inputWrapper">
					<select id="pospreftid" name="pospreftid" class="required">
						<option value="">Please select...</option>
					
<%					
			iTemp = applicinfo.getPosPrefTID();
			for(int index=0; index < aPosPrefList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPosPrefList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(aAppCode.getAPCIdSubType()== iTemp ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
%>			
					</select>
				</div>
			</div>
		</div>
		<div id="skillstid-D" class="oneField    ">
			<div id="skillstid-DW" class="oneFieldWrapper ui-draggable">
				<div id="skillstid-L" class="label preField ">
						Do you have any special experience or skills that might be useful in supporting ministries?
				</div>
				<div class="inputWrapper">
					<select id="skillstid" name="skillstid" class="">
						<option value="">Please select...</option>
					
<%					
			iTemp = applicinfo.getSkillsTID();
			for(int index=0; index < aSkillsList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSkillsList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iOptRqCode == iTemp ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
%>			
					</select>
				</div>
			</div>
		</div>
		<div id="internreason-D" class="oneField    ">
			<div id="internreason-DW" class="oneFieldWrapper ui-draggable">
				<div id="internreason-L" class="label preField reqMark">
						Why are you interested in this program and these positions specifically? (Take the time to answer thoroughly.)
				</div>
				<br>
				<div class="inputWrapper">
<textarea  cols="132" rows="16" id="internreason" name="internreason" class="required"><%=applicinfo.getInternReason() %></textarea>
				</div>
			</div>
		</div>
		<div id="workenvirontid-D" class="oneField    ">
			<div id="workenvirontid-DW" class="oneFieldWrapper ui-draggable">
				<div id="workenvirontid-L" class="label preField reqMark">
						What type of work environment do you prefer?
				</div>
				<div class="inputWrapper">
				<div class="inputWrapper">
					<select id="workenvirontid" name="workenvirontid" class="required" rel=" wfHandled">
						<option value="">Please select...</option>
					
<%					
			iTemp = applicinfo.getWorkEnvironTID();
			for(int index=0; index < aWorkEnvironList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aWorkEnvironList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iOptRqCode == iTemp ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
%>			
					</select>
				</div>
			</div>
		</div>
		<div id="poppreftid-D" class="oneField  offstate-c offstate-d  " style="display:none;">
			<div id="poppreftid-DW" class="oneFieldWrapper ui-draggable">
				<div id="poppreftid-L" class="label preField ">
						Do you have a strong preference for working with either youth or adults?
				</div>
				<div class="inputWrapper">
					<div class="inputWrapper">
						<select id="poppreftid" name="poppreftid" class="">
							<option value="">Please select...</option>
					
<%					
			iTemp = applicinfo.getPopulPrefTID();
			for(int index=0; index < aPopPrefList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPopPrefList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
				if(iOptRqCode == iTemp ) out.println(" selected=selected ");
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
%>			
						</select>
					</div>
				</div>
			</div>
		</div>
		<div id="lang-D" class="oneField    ">
			<div id="lang-DW" class="oneFieldWrapper ui-draggable">
				<div id="lang-L" class="label preField ">
						If you speak any languages besides English, please list them and describe your level of competence with each.
				</div>
				<br>
				<div class="inputWrapper">
<textarea  cols="132" rows="3" id="lang" name="lang" class=""><%=applicinfo.getLang() %></textarea>
				</div>
			</div>
		</div>
		
		
		
		<div id="church-D" class="oneField    ">
			<div id="church-DW" class="oneFieldWrapper ui-draggable">
				<div id="church-L" class="label preField reqMark">
						Are you actively attending a local church?
				</div>
				<br>
				<div class="inputWrapper">
					<span id="church" class="choices  required">
						<span class="oneChoice">
							<input type="radio"  value="Yes" <%= (applicinfo.getChurch().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="tfa_Yes3" name="church">
							<div class="label postField" id="tfa_Yes3-L">
									Yes
							</div>
						</span>
						<span class="oneChoice">
							<input type="radio"  value="No" <%= (applicinfo.getChurch().equals("No") ? "checked=\"checked\"" : "" ) %> id="tfa_No3" name="church">
							<div class="label postField" id="tfa_No3-L">
									No
							</div>
						</span>
					</span>
				</div>
			</div>
		</div>
<%
if(b_paid_intern==true && b_undergradTuition==false && b_bakke==false && b_mstsm==false){
}else{
%>		 
		<div id="degreeprogtid-D" class="oneField    ">
			<div id="degreeprogtid-DW" class="oneFieldWrapper ui-draggable">
				<div id="degreeprogtid-L" class="label preField ">
						In which City Vision degree program are you interested?
				</div>
				<div class="inputWrapper">
					<select id="degreeprogtid" name="degreeprogtid" class="">
						<option value="">Please select...</option>
<!--  depends on response to the internship type  -->					
<%					
			iTemp = applicinfo.getDegreeProgTID();
			for(int index=0; index < aCVDegreeProgList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVDegreeProgList.get(index);
				if(null == aAppCode) continue;
				int iTid = aAppCode.getAPCIdSubType();
				
				if(b_undergradTuition){
					if(iTid==iAddictionStudies || iTid==iNonprofitMgmnt || iTid==iUrbanMissions){
						out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
				if(b_mstsm){
					if(iTid==iMSTSM){
						out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iMSTSM && applicinfo.getInternLengthTID()==38780) out.println(" selected=selected ");
						else if(iTid == iTemp ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
				if(b_bakke){
					if(iTid==iDTLBakke || iTid==iDMinBakke || iTid==iMBABakke || iTid==iMSCEBakke || iTid==iMGULBakke){
						out.println(" <option value=\"" + aAppCode.getAPCIdSubType() + "\" " );
						if(iTid == iTemp ) out.println(" selected=selected ");
						out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
					}
				}
			}
%>			
					</select>
				</div>
			</div>
		</div>
<% 
}
%>		
		<div id="major-D" class="oneField    ">
			<div id="major-DW" class="oneFieldWrapper ui-draggable">
				<div id="major-L" class="label preField reqMark">
						If you didn't do a City Vision internship, in what degree program would you like to major in college?
				</div>
				<br>
				<div class="inputWrapper">
					<input type="text"  id="major" name="major" value="<%=applicinfo.getMajor() %>" size="40" placeholder="" class="required">
				</div>
			</div>
		</div>
	<div id="careergoals-D" class="oneField    ">
		<div id="careergoals-DW" class="oneFieldWrapper ui-draggable">
			<div id="careergoals-L" class="label preField reqMark">
					What are your career goals and how do you see this internship and degree program fitting in with those goals?
			</div>
			<br>
			<div class="inputWrapper">
<textarea  cols="132" rows="6" id="careergoals" name="careergoals" class="required"><%=applicinfo.getCareerGoals() %></textarea>
			</div>
		</div>
	</div>
	<div id="hrlycommit-D" class="oneField    ">
		<div id="hrlycommit-DW" class="oneFieldWrapper ui-draggable">
			<div id="hrlycommit-L" class="label preField reqMark">
					This is a 30 to 35 hour/week position. Interns without a Bachelors will also be required at least an additional 15-20 hours of studying per week. Are you able to make this type of commitment and are you able to work approximately 10am to 6pm, Monday through Friday?
			</div>
			<br>
			<div class="inputWrapper">
				<span id="hrlycommit" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getHrlyCommit().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="hrlycommit_Yes" name="hrlycommit">
						<div class="label postField" id="hrlycommit_Yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice"><input type="radio"  value="No" <%= (applicinfo.getHrlyCommit().equals("No")  ? "checked=\"checked\"" : "" ) %> id="hrlycommit_No" name="hrlycommit">
						<div class="label postField" id="hrlycommit_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="livablestipend-D" class="oneField    ">
		<div id="livablestipend-DW" class="oneFieldWrapper ui-draggable">
			<div id="livablestipend-L" class="label preField reqMark">
					Can you really live on the amount paid in this internship? Qualifying students may also receive Pell grants, student loans, or other government benefits.
			</div>
			<br>
			<div class="inputWrapper">
				<span id="livablestipend" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getLivableStipend().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="livablestipend_Yes" name="livablestipend">
						<div class="label postField" id="livablestipend_Yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice"><input type="radio"  value="No" <%= (applicinfo.getLivableStipend().equals("No")  ? "checked=\"checked\"" : "" ) %> id="livablestipend_No" name="livablestipend">
						<div class="label postField" id="livablestipend_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="livablestipendexpl-D" class="oneField    ">
		<div id="livablestipendexpl-DW" class="oneFieldWrapper ui-draggable">
			<div id="livablestipendexpl-L" class="label preField reqMark">
					If you answered "Yes" to the previous question, please explain how this amount will be sustainable for you. Otherwise, state the circumstances that cause this to be a hardship.
			</div>
			<br>
			<div class="inputWrapper">
<textarea  cols="132" rows="3" id="livablestipendexpl" name="livablestipendexpl" class="required"><%=applicinfo.getLivableStipendExpl() %></textarea>
			</div>
		</div>
	</div>
	<div id="crimrecord-D" class="oneField    ">
		<div id="crimrecord-DW" class="oneFieldWrapper ui-draggable">
			<div id="crimrecord-L" class="label preField reqMark">
					Have you ever been convicted of a crime?
			</div>
			<br>
			<div class="inputWrapper">
				<span id="crimrecord" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getCrimRecord().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="crimrecord_Yes" name="crimrecord" rel=" wfHandled">
						<div class="label postField" id="crimrecord_Yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice">
						<input type="radio"  value="No" <%= (applicinfo.getCrimRecord().equals("No")  ? "checked=\"checked\"" : "" ) %> id="crimrecord_No" name="crimrecord" rel=" wfHandled">
						<div class="label postField" id="crimrecord_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="crimdescrip-D" class="oneField  offstate-e  " style="display:none;">
		<div id="crimdescrip-DW" class="oneFieldWrapper ui-draggable">
			<div id="crimdescrip-L" class="label preField ">
					Please describe the circumstances regarding your criminal conviction:
			</div>
			<br>
			<div class="inputWrapper">
<textarea  cols="132" rows="3"  id="crimdescrip" name="crimdescrip" class=""><%=applicinfo.getCrimDescrip() %></textarea>
			</div>
		</div>
	</div>
	<div id="dob-D" class="oneField    ">
		<div id="dob-DW" class="oneFieldWrapper ui-draggable">
			<div id="dob-L" class="label preField reqMark">
					What is your date of birth?
			</div>
			<br>
			<div class="inputWrapper">
				<input type="text"  id="dob" name="dob" value="<%=aszDOB %>" placeholder="" class="validate-date required">
				<span class="field-hint-inactive" id="dob-H">
						Enter the date in mm/dd/yyyy format (such as 5/7/1982)
				</span>
			</div>
		</div>
	</div>
	<div id="housing-D" class="oneField    ">
		<div id="housing-DW" class="oneFieldWrapper ui-draggable">
			<div id="housing-L" class="label preField reqMark">
					Do you need housing and will you accept housing on site at a Christian homeless shelter or other ministry housing?
			</div>
			<br>
			<div class="inputWrapper">
				<span id="housing" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getHousing().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="housing_Yes" name="housing">
						<div class="label postField" id="housing_Yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice">
						<input type="radio"  value="No" <%= (applicinfo.getHousing().equals("No")  ? "checked=\"checked\"" : "" ) %> id="housing_No" name="housing">
						<div class="label postField" id="housing_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="servicesite-D" class="oneField    ">
		<div id="orgnids-D" class="oneField  offstate-c offstate-d  ">
			<div id="orgnids-DW" class="oneFieldWrapper ui-draggable">
				<div id="orgnids-L" class="label preField ">
					Are you applying with a particular service site (one of our partnering churches, ministries, or nonprofit organizations) in mind? If so, which one(s)?
				</div>
				<br />
				<div class="inputWrapper">
		<div id="better-select-edit-taxonomy-363" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes form-checkboxes-scroll">
			    	<div class="two_column">
<%
int[] a_iORGNIDs = applicinfo.getORGNIDsArray();
for(int index=0; index < aCVCSitesList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVCSitesList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
 %>
<div id="edit-taxonomy-363-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-363-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="orgnids" name="orgnids" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-scroll"
	<%
	for(int indexArray=0; indexArray < a_iORGNIDs.length; indexArray++){
		if(a_iORGNIDs[indexArray]==iSubType) out.print(" checked ");
	}
	%> 

		><%=aAppCode.getAPCDisplay()%>
	</label>
</div>
<%
	iTemp++;
}
%>
					</div>
				</div>
			</div>
		</div>
				</div>
			</div>
		</div>
	</div>
	<div id="locpref-D" class="oneField    ">
		<div id="locpref-DW" class="oneFieldWrapper ui-draggable">
			<div id="locpref-L" class="label preField ">
					Do you have any further information to share about your location preference?
			</div>
			<br>
			<div class="inputWrapper">
<textarea  cols="132" rows="3" id="locpref" name="locpref" class=""><%=applicinfo.getLocPrefInfo() %></textarea>
			</div>
		</div>
	</div>
	<div id="starttime-D" class="oneField    ">
		<div id="starttime-DW" class="oneFieldWrapper ui-draggable">
			<div id="starttime-L" class="label preField reqMark">
					When are you available to start?
			</div>
			<br>
			<div class="inputWrapper">
				<input type="text"  id="starttime" name="starttime" value="<%=applicinfo.getStartTime() %>" size="40" placeholder="" class="required">
			</div>
		</div>
	</div>
	<div id="forwardresume-D" class="oneField    ">
		<div id="forwardresume-DW" class="oneFieldWrapper ui-draggable">
			<div id="forwardresume-L" class="label preField reqMark">
					If we are unable to hire a good candidate, we sometimes forward his/her resume to another comparable organization. Are you comfortable with us doing that?
			</div>
			<br>
			<div class="inputWrapper">
				<span id="forwardresume" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getForwardResume().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="forwardresume_yes" name="forwardresume">
						<div class="label postField" id="forwardresume_yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice">
						<input type="radio"  value="No" <%= (applicinfo.getForwardResume().equals("No")  ? "checked=\"checked\"" : "" ) %>id="forwardresume_No" name="forwardresume">
						<div class="label postField" id="forwardresume_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="webcam-D" class="oneField    ">
		<div id="webcam-DW" class="oneFieldWrapper ui-draggable">
			<div id="webcam-L" class="label preField reqMark">
					Do you have access to a webcam (so that we could see you if we were to interview you by phone)?
			</div>
			<br>
			<div class="inputWrapper">
				<span id="webcam" class="choices  required">
					<span class="oneChoice">
						<input type="radio"  value="Yes" <%= (applicinfo.getWebcam().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id="webcam_Yes" name="webcam" rel=" wfHandled">
						<div class="label postField" id="webcam_Yes-L">
								Yes
						</div>
					</span>
					<span class="oneChoice">
						<input type="radio"  value="No" <%= (applicinfo.getWebcam().equals("No")  ? "checked=\"checked\"" : "" ) %> id="webcam_No" name="webcam" rel=" wfHandled">
						<div class="label postField" id="webcam_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="skype-D" class="oneField  offstate-b  " style="display:none;">
		<div id="skype-DW" class="oneFieldWrapper ui-draggable">
			<div id="skype-L" class="label preField ">
					Your Skype Name (if you have one)
			</div>
			<br>
			<div class="inputWrapper">
				<input type="text"  id="skype" name="skype" value="<%=applicinfo.getSkype() %>" size="50" placeholder="" class="">
			</div>
		</div>
	</div>
	<br />
	<fieldset id="tfa_References" class="section ui-draggable">
		<legend id="tfa_References-L">
			<div class="sectionEditableTitle" id="tfa_References-LTXT">
				References
			</div>
		</legend>
		<div class="htmlSection ui-draggable" id="tfa_9755162050351">
			<div class="htmlContent" id="tfa_9755162050351-HTML">
				<p>Please provide two references below, one from the pastor of your church and one from a previous employer.&nbsp;</p>
			</div>
		</div>
		<fieldset id="tfa_PastoralReferenc" class="section ui-draggable">
			<legend id="tfa_PastoralReferenc-L">
				<div class="sectionEditableTitle" id="tfa_PastoralReferenc-LTXT">
					Pastoral Reference (from your church's pastor)
				</div>
			</legend>
			<div id="pastoralref-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralref-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralref-L" class="label preField reqMark">
							Name
					</div>
					<div class="inputWrapper">
						<input type="text"  id="pastoralref" name="pastoralref" value="<%=applicinfo.getPastoralRef() %>" size="40" placeholder="" class="required">
					</div>
				</div>
			</div>
			<div id="pastoralrefchurch-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefchurch-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefchurch-L" class="label preField reqMark">
							Church/Ministry
					</div>
					<div class="inputWrapper">
						<input type="text"  id="pastoralrefchurch" name="pastoralrefchurch" value="<%=applicinfo.getPastoralRefChurch() %>" size="40" placeholder="" class="required">
					</div>
				</div>
			</div>
			<div id="pastoralrefphone-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefphone-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefphone-L" class="label preField reqMark">
							Phone
					</div>
					<div class="inputWrapper">
						<input type="text"  id="pastoralrefphone" name="pastoralrefphone" value="<%=applicinfo.getPastoralRefPhone() %>" size="40" placeholder="" class="validate-phone required">
						<span class="field-hint-inactive" id="pastoralrefphone-H">
								Example: 555 555 5555
						</span>
					</div>
				</div>
			</div>
			<div id="pastoralrefemail-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefemail-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefemail-L" class="label preField reqMark">
							Email
					</div>
					<div class="inputWrapper">
						<input type="text"  id="pastoralrefemail" name="pastoralrefemail" value="<%=applicinfo.getPastoralRefEmail() %>" size="40" placeholder="" class="validate-email required">
						<span class="field-hint-inactive" id="pastoralrefemail-H">
								Example: email@domain.com
						</span>
					</div>
				</div>
			</div>
		</fieldset>
		
		<fieldset id="tfa_ProfessionalRefe" class="section ui-draggable">
			<legend id="tfa_ProfessionalRefe-L">
				<div class="sectionEditableTitle" id="tfa_ProfessionalRefe-LTXT">
					Professional Reference
				</div>
			</legend>
			<div id="profref-D" class="oneField   labelsLeftAligned ">
				<div id="profref-DW" class="oneFieldWrapper ui-draggable">
					<div id="profref-L" class="label preField reqMark">
							Name
					</div>
					<div class="inputWrapper">
						<input type="text"  id="profref" name="profref" value="<%=applicinfo.getProfRef() %>" size="40" placeholder="" class="required">
					</div>
				</div>
			</div>
			<div id="profreforg-D" class="oneField   labelsLeftAligned ">
				<div id="profreforg-DW" class="oneFieldWrapper ui-draggable">
					<div id="profreforg-L" class="label preField reqMark">
							Organization
					</div>
					<div class="inputWrapper">
						<input type="text"  id="profreforg" name="profreforg" value="<%=applicinfo.getProfRefOrg() %>" size="40" placeholder="" class="required">
					</div>
				</div>
			</div>
			<div id="profrefphone-D" class="oneField   labelsLeftAligned ">
				<div id="profrefphone-DW" class="oneFieldWrapper ui-draggable">
					<div id="profrefphone-L" class="label preField reqMark">
							Phone
					</div>
					<div class="inputWrapper">
						<input type="text"  id="profrefphone" name="profrefphone" value="<%=applicinfo.getProfRefPhone() %>" size="40" placeholder="" class="validate-phone required">
						<span class="field-hint-inactive" id="profrefphone-H">
								Example: 555 555 5555
						</span>
					</div>
				</div>
			</div>
			<div id="profrefemail-D" class="oneField   labelsLeftAligned ">
				<div id="profrefemail-DW" class="oneFieldWrapper ui-draggable">
					<div id="profrefemail-L" class="label preField reqMark">
							Email
					</div>
					<div class="inputWrapper">
						<input type="text"  id="profrefemail" name="profrefemail" value="<%=applicinfo.getProfRefEmail() %>" size="40" placeholder="" class="validate-email required">
						<span class="field-hint-inactive" id="profrefemail-H">
								Example: email@domain.com
						</span>
					</div>
				</div>
			</div>
		</fieldset>
	</fieldset>

</div>

<div style="clear:both"></div>
	<input type="button" class="secondaryAction" value="Cancel" onclick="history.go(-1)">
	<input type="submit" class="primaryAction" value="Next Page">

</form>


</div>
</div>
</div>
</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
<% if(applicinfo.getWorkEnvironTID()==38806 || applicinfo.getWorkEnvironTID()==38807){ %>
	$('#poppreftid-D').hide();
<% } %>
	$('select#workenvirontid').change(function() {
	    var show_poppref = true;
		$('#workenvirontid option:selected').each(function(){  
	    	if( $( this ).val() == 38806 || $( this ).val() == 38807 ){
	    		show_poppref = false;
	    	}
	    });
		if(show_poppref==true){
			$('#poppreftid-D').show();
		}else{
			$('#poppreftid-D').hide();
		}
	});
	
	$('#crimrecord_Yes').click(function() {
		$('#crimdescrip-D').show();
	});
	$('#crimrecord_No').click(function() {
		$('#crimdescrip-D').hide();
	});
	if($('#crimrecord_Yes').is(':checked')){
		$('#crimdescrip-D').show();
	}
	
	$('#webcam_Yes').click(function() {
		$('#skype-D').show();
	});
	$('#webcam_No').click(function() {
		$('#skype-D').hide();
	});
	if($('#webcam_Yes').is(':checked')){
		$('#skype-D').show();
	}

	if($('#workenvirontid').val()>0 && $('#workenvirontid').val()!=38808){
		$('#poppreftid-D').show();
	}
	$('#workenvirontid').change(function(){
		v = $('#workenvirontid').val();
		if(v == 38808){
			$('#poppreftid-D').hide();
		}else{
			$('#poppreftid-D').show();
		}
	});
});
</script>

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

