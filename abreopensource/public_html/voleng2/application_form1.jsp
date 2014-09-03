<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<bean:define id="applicinfo" name="applicinfo" type="com.abrecorp.opensource.dataobj.ApplicationInfoDTO"/>
<bean:define id="opp" name="opp" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO"/>
<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

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


<%

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
	aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
	aszSubdomain = "iVolunteering.org";
}

int iTemp = 0;

String aszOppNID="" + applicinfo.getOPPNID();
String aszVolUID="" + applicinfo.getUID();
String aszVolNID="" + applicinfo.getVolNID();
String aszNID="" + applicinfo.getNID();
String aszVID="" + applicinfo.getVID();

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

%>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">City Vision Internship Application - Step 1</span>
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
<form action="<%=aszPortal%>/email.do?method=processCreateApplication1" name="emailForm" id="emailForm" method="post" >
<input type="hidden" name="method" value="processCreateApplication1" />
<input type="hidden" name="subdom" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteemail" value="<%=aszEmailHost%>" />

             <!-- volunteer information -->
             <input type="hidden" name="oppnid" value="<%=aszOppNID%>" />
             
<%
String aszAdditionalOrgNID = "";
int iAdditionalOrgNID = 0;
if(request.getParameter("orgnids")!=null){
	try{
		aszAdditionalOrgNID = request.getParameter("orgnids");
		iAdditionalOrgNID = Integer.parseInt(aszAdditionalOrgNID);
%>
             <input type="hidden" name="orgnids" value="<%=aszAdditionalOrgNID%>" />
<%		
	}catch(Exception e){
		
	}
}
int[] a_iOrgNIDs = applicinfo.getORGNIDsArray();
for(int i=0; i<a_iOrgNIDs.length; i++){
	if(a_iOrgNIDs[i] != iAdditionalOrgNID)	{
%>             
             <input type="hidden" name="orgnids" value="<%=a_iOrgNIDs[i]%>" />
<% 
	}
}
%>             
             
             <input type="hidden" name="voluid" value="<%=aszVolUID%>" />
             <input type="hidden" name="nid" value="<%=aszNID%>" />
             <input type="hidden" name="vid" value="<%=aszVID%>" />
             <input type="hidden" name="volnid" value="<%=aszVolNID%>" />
             

	<h3 align= "right">
		<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a> ]</font>
	</h3>
				
	<h2>City Vision Internship Application Form</h2>

                		
		<div class="content" >
		  <p>Fill out the form below to receive an email with more information about the City Vision College internship opportunity.</p>
		  <p>When you submit the form, you will be taken to the full application form. We highly recommend you fill it out now if you are interested in an internship position.</p>
		  <!-- FORM: HEAD SECTION -->


<!-- FORM: BODY SECTION -->

<div id="firstname-D" class="oneField   labelsLeftAligned ">
	<div id="firstname-L" for="firstname" class="label preField reqMark">First Name</div>
	<br />
	<div class="inputWrapper">
		<input type="text" id="firstname" name="firstname" value="<%=applicinfo.getNameFirst() %>" size="40" placeholder="" class="required">
	</div>
</div>
<div id="lastname-D" class="oneField   labelsLeftAligned ">
	<div id="lastname-L" for="lastname" class="label preField reqMark">Last Name</div>
	<br />
	<div class="inputWrapper">
		<input type="text" id="lastname" name="lastname" value="<%=applicinfo.getNameLast() %>" size="50" placeholder="" class="required">
	</div>
</div>
<input type="hidden" id="tfa_Company" name="tfa_Company" value="Individual" class="">
<div id="email-D" class="oneField   labelsLeftAligned ">
	<div id="email-L" for="email" class="label preField reqMark">Email</div>
	<br />
	<div class="inputWrapper">
		<input type="text" id="email" name="email" value="<%=applicinfo.getEmailAddr() %>" size="50" placeholder="" class="validate-email required">
	</div>
</div>
<div id="phone-D" class="oneField   labelsLeftAligned ">
	<div id="phone-L" for="phone" class="label preField reqMark">Phone Number</div>
	<br />
	<div class="inputWrapper">
		<input type="text" id="phone" name="phone" value="<%=applicinfo.getPhone() %>" size="40" placeholder="" class="validate-phone required">
	</div>
</div>
<div id="sourcetid-D" class="oneField    "> <!--  vid 361 --> 
	<div id="sourcetid-L" for="sourcetid" class="label preField reqMark">How did you hear about City Vision Internships?</div>
	<br>
	<div class="inputWrapper">
		<select id="sourcetid" name="sourcetid" class="required">
			<option value="">Please select...</option>
<%					
			iTemp = applicinfo.getSourceTID();
			for(int index=0; index < aSourceList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSourceList.get(index);
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
<div id="tfa_6922573433997-D" class="oneField    "><!--  vid 362 --> 
	<div id="tfa_6922573433997-L" for="tfa_6922573433997" class="label preField reqMark">Are you interested in a one year internship or a summer internship?</div>
	<br>
	<div class="inputWrapper">
		<span id="tfa_6922573433997" class="choices vertical required">
			<span class="oneChoice">
				<input type="radio" value="38780" <%= (applicinfo.getInternLengthTID()==38780 ? "checked=\"checked\"" : "" ) %> id="internlengthtid" name="internlengthtid">
				<label class="label postField" id="internlengthtid-L" for="internlengthtid">One Year or Multi-year Internship</label>
			</span>
			<span class="oneChoice">
				<input type="radio" value="38781" <%= (applicinfo.getInternLengthTID()==38781 ? "checked=\"checked\"" : "" ) %> id="internlengthtid" name="internlengthtid">
				<label class="label postField" id="internlengthtid-L" for="internlengthtid">Summer Internship</label>
			</span>
		</span>
	</div>
</div>

		<div id="hasbachelors-D" class="oneField    ">
			<div id="hasbachelors-DW" class="oneFieldWrapper ui-draggable">
				<div id="hasbachelors-L" class="label preField reqMark">
						Do you currently have a bachelor's degree?
				</div>
				<br>
				<div class="inputWrapper">
					<span id="hasbachelors" class="choices  required">
						<span class="oneChoice">
							<input type="radio"  value="Yes" <%= (applicinfo.getHasBachelors().equals("Yes")  ? "checked=\"checked\"" : "" ) %> id=hasbachelors_Yes name="hasbachelors" >
							<div class="label postField" id="hasbachelors_Yes-L">
									Yes
							</div>
						</span>
					<span class="oneChoice">
						<input type="radio"  value="No" <%= (applicinfo.getHasBachelors().equals("No")  ? "checked=\"checked\"" : "" ) %> id="hasbachelors_No" name="hasbachelors" >
						<div class="label postField" id="hasbachelors_No-L">
								No
						</div>
					</span>
				</span>
			</div>
		</div>
	</div>
	<div id="credits-D" class="oneField  offstate-a  " style="display:none;"> 
		<div id="credits-DW" class="oneFieldWrapper ui-draggable">
			<div id="credits-L" class="label preField reqMark">
					About how many college credits have you earned?
			</div>
			<br>
			<div class="inputWrapper">
				<input type="text"  id="credits" name="credits" value="<%=applicinfo.getCredits() %>" style="width: 40em" placeholder="" class="validate-integer required">
			</div>
		</div>
	</div>


<div id="interntypetid-D" class="oneField    "> <!--  vid 363 --> 
	<div id="interntypetid-L" for="interntypetid" class="label preField reqMark">What type of internship are you interested in? </div>
	<br>
	<div class="inputWrapper">
		<div id="better-select-edit-taxonomy-363" class="better-select betterfixed">
	    	<div class="form-item">
			    <div class="form-checkboxes form-checkboxes-scroll">
<%
int[] a_iInternTypeTIDs = applicinfo.getInternTypeTIDsArray();
for(int index=0; index < aInternTypeList.size(); index++){
	AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aInternTypeList.get(index);
	if(null == aAppCode) continue;
	String aszDisplay = aAppCode.getAPCDisplay();
	int iSubType = aAppCode.getAPCIdSubType();
 %>
<div id="edit-taxonomy-363-<%=aAppCode.getAPCIdSubType()%>-wrapper" class="form-item" > 
	<label class="option" for="edit-taxonomy-363-<%=aAppCode.getAPCIdSubType()%>" >
		<input type="checkbox" id="interntypetids" name="interntypetids" value=<%=aAppCode.getAPCIdSubType()%> class="form-checkbox form-checkboxes-scroll"
	<%
	for(int indexArray=0; indexArray < a_iInternTypeTIDs.length; indexArray++){
		if(a_iInternTypeTIDs[indexArray]==iSubType) out.print(" checked ");
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
<div id="cvdegree_carreergoals-D" class="oneField  offstate-c  "> <!--  *******************************************NEW FIELD *************************************-->
	<div id="cvdegree_carreergoals-L" for="cvdegree_carreergoals" class="label preField ">
	<span id="docs-internal-guid-351ed5a8-2813-a919-229f-b466011a92cb">
		The free undergraduate tuition program is for students who would like to complete their degree with City Vision College in a Bachelor's in Addiction Studies, Nonprofit Management or Missions. If you are selected as an intern and accept the position, does completing your degree in one of these areas with City Vision fit into your career goals?
	</span>
	</div>
	<br>
	<div class="inputWrapper">
		<span id="tfa_6922573434024" class="choices vertical ">
			<span class="oneChoice"><input type="radio" value="Yes" <%= (applicinfo.getCVDegreeCareerGoals().equals("Yes") ? "checked=\"checked\"" : "" ) %> id="cvdegree_carreergoals_Yes" name="cvdegree_carreergoals">
				<label class="label postField" id="cvdegree_carreergoals_Yes-L" for="cvdegree_carreergoals_Yes">Yes</label>
			</span>
			<span class="oneChoice"><input type="radio" value="No" <%= (applicinfo.getCVDegreeCareerGoals().equals("No") ? "checked=\"checked\"" : "" ) %> id="cvdegree_carreergoal_No" name="cvdegree_carreergoals">
				<label class="label postField" id="cvdegree_carreergoals_No-L" for="cvdegree_carreergoals_No">No</label>
			</span>
		</span>
	</div>
</div>
<div id="gender-D" class="oneField    ">
	<div id="gender-DW" class="oneFieldWrapper ui-draggable">
		<div id="church-L" class="label preField reqMark">
				Are you male or female?
		</div>
		<br>
		<div class="inputWrapper">
			<span id="gender_q" class="choices  required">
				<span class="oneChoice">
					<input type="radio"  value="Male" <%= (applicinfo.getGender().equals("Male")  ? "checked=\"checked\"" : "" ) %> id="gender_M" name="gender">
					<div class="label postField" id="gender_M-L">
							Male
					</div>
				</span>
				<span class="oneChoice">
					<input type="radio"  value="Female" <%= (applicinfo.getGender().equals("Female") ? "checked=\"checked\"" : "" ) %> id="gender_F" name="gender">
					<div class="label postField" id="gender_F-L">
							Female
					</div>
				</span>
			</span>
		</div>
	</div>
</div>
		
<br />
		
<fieldset id="tfa_InternshipRequir" class="section">
	<legend id="tfa_InternshipRequir-L">Internship Requirements</legend>
	<div class="htmlSection" id="tfa_6922573433991">
		<div class="htmlContent" id="tfa_6922573433991-HTML">
			<p>To be an intern with City Vision College, you must meet all of the requirements below.</p>
		</div>
	</div>
	<div id="tfa_AreyouaChristian-D" class="oneField    ">
		<div id="ischris-L" for="ischris" class="label preField reqMark">Are you a Christian?</div>
		<br>
		<div class="inputWrapper">
			<span id="tfa_AreyouaChristian" class="choices  required">
				<span class="oneChoice">
					<input type="radio" value="Yes" <%= (applicinfo.getAgeRequirement().equals("Yes") ? "checked=\"checked\"" : "" ) %> id="ischris_Yes" name="ischris">
					<label class="label postField" id="ischris_Yes-L" for="ischris_Yes">Yes</label>
				</span>
				<span class="oneChoice">
					<input type="radio" value="No" <%= (applicinfo.getChristian().equals("No") ? "checked=\"checked\"" : "" ) %> id="tfa_No" name="ischris">
					<label class="label postField" id="ischris_No-L" for="ischris_No">No</ischris_No>
				</span>
			</span>
		</div>
	</div>
	<div id="agerequirement-D" class="oneField    ">
		<div id="agerequirement-L" for="agerequirement" class="label preField reqMark">Are you at least 18 years old?</div>
		<br>
		<div class="inputWrapper">
			<span id="tfa_Areyouatleast18y" class="choices  required">
				<span class="oneChoice">
				<input type="radio" value="Yes" <%= (applicinfo.getAgeRequirement().equals("Yes") ? "checked=\"checked\"" : "" ) %> id="agerequirement_Yes" name="agerequirement">
					<label class="label postField" id="agerequirement_Yes-L" for="agerequirement_Yes">Yes</label>
				</span>
				<span class="oneChoice">
				<input type="radio" value="No" <%= (applicinfo.getAgeRequirement().equals("No") ? "checked=\"checked\"" : "" ) %> id="agerequirement_No" name="agerequirement">
					<label class="label postField" id="agerequirement_No-L" for="agerequirement_No">No</label>
				</span>
			</span>
		</div>
	</div>
	<div id="diploma-D" class="oneField    ">
		<label id="diploma-L" for="diploma" class="label preField reqMark">Do you have a high school diploma or equivalent, such as a GED?</label>
		<br>
		<div class="inputWrapper">
			<select id="diploma" name="diploma" class="calc-EDUCATION required"><option value="">Please select...</option>
				<option value="High School Diploma" <%= (applicinfo.getDiploma().equals("High School Diploma") ? "selected=\"selected\"" : "" ) %> >High School Diploma</option>
				<option value="GED" <%= (applicinfo.getDiploma().equals("GED") ? "selected=\"selected\"" : "" ) %> >GED</option>
				<option value="Neither" <%= (applicinfo.getDiploma().equals("Neither") ? "selected=\"selected\"" : "" ) %> >Neither</option>
			</select>
		</div>
	</div>
	<div id="citizentid-D" class="oneField    ">
		<div id="citizentid-L" for="citizentid" class="label preField reqMark">What is your citizenship status?</div>
		<br>
		<div class="inputWrapper">
			<select id="tfa_Whatisyourcitize" name="citizentid" class="calc-CITIZENSHIP required">
			<option value="">Please select...</option>
<%					
			iTemp = applicinfo.getCitizenTID();
			for(int index=0; index < aCitizenList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCitizenList.get(index);
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
	<div id="timecommitability-D" class="oneField    ">
		<div id="timecommitability-L" for="timecommitability" class="label preField reqMark">Are you able to commit to the full length of the internship?</div>
		<br>
		<div class="inputWrapper">
			<span id="tfa_Areyouabletomake" class="choices  required">
				<span class="oneChoice">
					<input type="radio" value="Yes" <%= (applicinfo.getTimeCommitAbility().equals("Yes") ? "checked=\"checked\"" : "" ) %> id="timecommitability_Yes" name="timecommitability">
					<label class="label postField" id="timecommitability_Yes-L" for="timecommitability_Yes">Yes</label>
				</span>
				<span class="oneChoice">
					<input type="radio" value="No" <%= (applicinfo.getTimeCommitAbility().equals("No") ? "checked=\"checked\"" : "" ) %> id="timecommitability_No" name="timecommitability">
					<label class="label postField" id="timecommitability_No-L" for="timecommitability_No">No</label>
				</span>
			</span>
		</div>
	</div>
	<div id="attendingbachelors-D" class="oneField    ">
		<div id="attendingbachelors-L" for="attendingbachelors" class="label preField reqMark">If you do not have a bachelor's degree, have you been active in school in the past 5 years (either attending high school or completing a course in an accredited college)?</div>
		<br>
		<div class="inputWrapper">
			<span id="tfa_Areyouactivelypu" class="choices  required">
				<span class="oneChoice">
					<input type="radio" value="Yes" <%= (applicinfo.getBachelorsAttending().equals("Yes") ? "checked=\"checked\"" : "" ) %> id="attendingbachelors_Yes" name="attendingbachelors">
					<label class="label postField" id="attendingbachelors_Yes-L" for="attendingbachelors_Yes">Yes</label>
				</span>
				<span class="oneChoice">
					<input type="radio" value="No" <%= (applicinfo.getBachelorsAttending().equals("No") ? "checked=\"checked\"" : "" ) %> id="attendingbachelors_No" name="attendingbachelors">
					<label class="label postField" id="attendingbachelors_No-L" for="attendingbachelors_No">No</label>
				</span>
				<span class="oneChoice">
					<input type="radio" value="Have Bachelor's" <%= (applicinfo.getBachelorsAttending().equals("Have Bachelor's") ? "checked=\"checked\"" : "" ) %> id="attendingbachelors_Have" name="attendingbachelors">
					<label class="label postField" id="attendingbachelors_Have-L" for="attendingbachelors_Have">Have Bachelor's</label>
				</span>
			</span>
		</div>
	</div>
	<div id="lastyrhighschool-D" style="display:none;" class="oneField  offstate-a offstate-b  "> <!-- ****************** NEW FIELD ******************** -->
		<div id="lastyrhighschool-L" for="lastyrhighschool" class="label preField reqMark">What is the last year you were active in school?</div>
		<br>
		<div class="inputWrapper">
<%
int iLastYrActiveSchool = applicinfo.getLastYrActiveHS();
String aszLastYrActiveSchool = "" + iLastYrActiveSchool;
if(iLastYrActiveSchool<1900)
	aszLastYrActiveSchool = "";
%>		
			<input type="text" id="lastyrhighschool" name="lastyrhighschool" value="<%=aszLastYrActiveSchool %>" size="40" placeholder="" class="validate-custom ^(20[0-1][0-9])$ calc-LASTYEAR required">
		</div>
<script type="text/javascript">
$(document).ready(function(){
	
	$('select#interntypetids').change(function() {
	    var ask_degreecareergoals = false;
		$('#interntypetids option:selected').each(function(){  
	    	if( $( this ).val() == 38793 ){
	    		ask_degreecareergoals = true;
	    	}
	    });
		if(ask_degreecareergoals==true){
			$('#cvdegree_carreergoals-D').show();
		}else{
			$('#cvdegree_carreergoals-D').hide();
		}
	});

	
	$('#hasbachelors_No').click(function() {
		$('#edit-taxonomy-363-38794-wrapper').hide();
		$('#edit-taxonomy-363-38831-wrapper').hide();
		$('#edit-taxonomy-363-38832-wrapper').hide();
		$('#credits-D').show();
		$('#attendingbachelors_No').attr('checked', 'checked');
		$('#lastyrhighschool-D').show();
	});
	$('#hasbachelors_Yes').click(function() {
		$('#edit-taxonomy-363-38794-wrapper').show();
		$('#edit-taxonomy-363-38831-wrapper').show();
		$('#edit-taxonomy-363-38832-wrapper').show();
		$('#credits-D').hide();
		$('#attendingbachelors_Have').attr('checked', 'checked');
		$('#lastyrhighschool-D').hide();
	});
	if($('#hasbachelors_No').is(':checked')){
		$('#edit-taxonomy-363-38794-wrapper').hide();
		$('#edit-taxonomy-363-38831-wrapper').hide();
		$('#edit-taxonomy-363-38832-wrapper').hide();
		$('#credits-D').show();
		$('#attendingbachelors_No').attr('checked', 'checked');
		$('#lastyrhighschool-D').show();
	}else if($('#hasbachelors_Yes').is(':checked')){
		$('#edit-taxonomy-363-38794-wrapper').show();
		$('#edit-taxonomy-363-38831-wrapper').show();
		$('#edit-taxonomy-363-38832-wrapper').show();
		$('#credits-D').hide();
		$('#attendingbachelors_Have').attr('checked', 'checked');
		$('#lastyrhighschool-D').hide();
	}
	
	if($('#attendingbachelors_No').is(':checked')){
		$('#lastyrhighschool-D').show();
	}
	if($('#attendingbachelors_Yes').is(':checked')){
		$('#lastyrhighschool-D').show();
	}
	$('#attendingbachelors_No').click(function() {
		$('#lastyrhighschool-D').show();
	});
	$('#attendingbachelors_Yes').click(function() {
		$('#lastyrhighschool-D').show();
	});
	$('#attendingbachelors_Have').click(function() {
		$('#lastyrhighschool-D').hide();
	});
});
	if(typeof wFORMS != 'undefined') {
		if(wFORMS.behaviors.validation) {
			wFORMS.behaviors.validation.rules['customlastyrhighschool'] =  { 
					selector: '*[id="lastyrhighschool"]', check: 'validateCustom'
					};
			wFORMS.behaviors.validation.messages['customlastyrhighschool'] = 
				"You must have taken a college course 5 or less years ago.";
		}
	}
</script>

	</div>


</fieldset>
<input type="hidden" id="tfa_InternshipEligib" name="tfa_InternshipEligib" value="" class="formula=CHRISTIAN*AGE*EDUCATION*CITIZENSHIP*COMMITMENT*ACTIVE">
<input type="hidden" id="tfa_6922573434031" name="tfa_6922573434031" value="" class="">
<input type="hidden" id="tfa_6922573434032" name="tfa_6922573434032" value="" class="">
<input type="hidden" id="tfa_6922573434033" name="tfa_6922573434033" value="" class="">
<input type="hidden" id="tfa_6922573434034" name="tfa_6922573434034" value="" class="">
<div class="actions" id="tfa_6922573433992-A">
	<input type="button" class="secondaryAction" value="Cancel" onclick="history.go(-1)">
	<input type="submit" class="primaryAction" value="Submit" id="submit">
</div>
<div style="clear:both"></div>
<input type="hidden" value="845-a10c57bdd5e5131cf5b3527d1eb08862" name="tfa_dbCounters" id="tfa_dbCounters" autocomplete="off">
<input type="hidden" value="256609" name="tfa_dbFormId" id="tfa_dbFormId">
<input type="hidden" value="" name="tfa_dbResponseId" id="tfa_dbResponseId">
<input type="hidden" value="41df445fb28ef68e54f1edcf8c11bf95" name="tfa_dbControl" id="tfa_dbControl">
<input type="hidden" value="1388765690" name="tfa_dbTimeStarted" id="tfa_dbTimeStarted" autocomplete="off">
<input type="hidden" value="44" name="tfa_dbVersionId" id="tfa_dbVersionId">

</form>
</div>


 
  

</div>
</div>
</div>


<style>
.better-select div.form-checkboxes-scroll {
    max-width: 800px;
}
.better-select div.form-checkboxes-scroll {
    width: 570px;
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

