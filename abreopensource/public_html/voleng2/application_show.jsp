<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<bean:define id="applicinfo" name="applicinfo" type="com.abrecorp.opensource.dataobj.ApplicationInfoDTO"/>

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
String aszOrgNID="" + applicinfo.getORGNID();
String aszVolUID="" + applicinfo.getUID();

String aszNID="" + applicinfo.getNID();
int iTemp=0;
String[] a_aszTemp = new String[1];

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



java.util.Date dDOB = applicinfo.getDOBDt();
java.text.DateFormat sdf = new java.text.SimpleDateFormat("M/d/yyyy");
String aszDOB = "";
if(dDOB != null){
	aszDOB = sdf.format(dDOB);  
}

String aszSrc = "";
if(request.getParameter("src")!=null){
	aszSrc = request.getParameter("src");
}
%>

<style>
div.oneFieldWrapper {
	overflow: hidden;
}
div#maincontent {
    width: 915px;
}
</style>

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
<div id="maincontent" class="search_results showApplication">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="showApplication">
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
<div class="content" >


<!-- FORM: HEAD SECTION -->
<h1><%=applicinfo.getNameFirst() %>   <%=applicinfo.getNameLast() %></h1>


<!-- FORM: BODY SECTION -->
<div id="tfa_Resume-D" class="oneField    ">
	<div id="tfa_Resume-DW" class="oneFieldWrapper ui-draggable">
<!--  aszSrc is <%=aszSrc%> AND res file is  <%=applicinfo.getResumeFilePath()%>  -->
<% if(aszSrc.equals("sf") && applicinfo.getResumeFilePath().length() > 0){ %>
	<a style="float:left;" href="<%=request.getContextPath() %>/fileDownload.do?resume=true&nid=<%=applicinfo.getNID() %>&src=sf" class="volunteer button" src="../template/image/help.gif">
		download resum&eacute;
	</a> 
<% }else if(applicinfo.getResumeFilePath().length() > 0){ %>
	<a style="float:left;" href="<%=request.getContextPath() %>/fileDownload.do?resume=true&uid=<%=applicinfo.getUID() %>" class="volunteer button" src="../template/image/help.gif">
		download resum&eacute;
	</a> 
<% } %>

<% if(applicinfo.getUsername().length()>0 && !(aszSrc.equals("sf"))){ %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a style="float:left;" href="http://www.christianvolunteering.org/profiles/<%=applicinfo.getUsername() %>" target="_new" class="volunteer button" src="../template/image/help.gif">
	resum&eacute; profile
</a>
<% } %>
	</div>
</div>

<div id="firstname-D" class="oneField   labelsLeftAligned ">
	<label id="firstname-L" for="firstname" class="label preField reqMark">Name</label>
	<div class="providedResponse">
		<%=applicinfo.getNameFirst() %> <%=applicinfo.getNameLast() %>
	</div>
</div>
<div id="phone-D" class="oneField   labelsLeftAligned ">
	<label id="phone-L" for="phone" class="label preField reqMark">Phone Number</label>
	<div class="providedResponse">
		<%=applicinfo.getPhone() %>
	</div>
</div>
<div id="email-D" class="oneField   labelsLeftAligned ">
	<label id="email-L" for="email" class="label preField reqMark">Email</label>
	<div class="providedResponse">
		<%=applicinfo.getEmailAddr() %>
	</div>
</div>
<div id="testimony-D" class="oneField    ">
	<div id="testimony-DW" class="oneFieldWrapper ui-draggable">
		<div id="testimony-L" class="label preField reqMark">
				Please share your testimony as a Christian: how you came to know God and your story with God. (Take the time to answer thoroughly.)
		</div>
		<br>
		<div class="providedResponse">
		<%=applicinfo.getTestimony() %>
		</div>
	</div>
</div>
		<div id="internreason-D" class="oneField    ">
			<div id="internreason-DW" class="oneFieldWrapper ui-draggable">
				<div id="internreason-L" class="label preField reqMark">
						Why are you interested in this program and these positions specifically? (Take the time to answer thoroughly.)
				</div>
				<br>
				<div class="providedResponse">
		<%=applicinfo.getInternReason() %>
				</div>
			</div>
		</div>
<div id="tfa_6922573433997-D" class="oneField    "><!--  vid 362 --> 
	<label id="tfa_6922573433997-L" for="tfa_6922573433997" class="label preField reqMark">Are you interested in a one year internship or a summer internship?</label>
	<br>
	<div class="providedResponse">
		<span id="tfa_6922573433997" class="choices vertical required">
<%					
			iTemp = applicinfo.getInternLengthTID();
			for(int index=0; index < aInternLengthList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aInternLengthList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
	<br>
		</span>
	</div>
</div>
<div id="interntypetid-D" class="oneField    "> <!--  vid 363 --> 
	<label id="interntypetid-L" for="interntypetid" class="label preField reqMark">What type of internship are you interested in? </label>
	<br>
	<div class="providedResponse">
<%					
a_aszTemp = applicinfo.getInternTypesArray();
for(int i=0; i<a_aszTemp.length; i++){
	if(i>0)	out.print(", ");
	out.print(a_aszTemp[i]);
}
%>			
	</div>
</div>
<% if(applicinfo.getUID()==aCurrentUserObjLoggedIn.getUserUID()){ %>	
	<div id="dob-D" class="oneField    ">
		<div id="dob-DW" class="oneFieldWrapper ui-draggable">
			<div id="dob-L" class="label preField reqMark">
					What is your date of birth?
			</div>
			<div class="providedResponse">
			
<%=aszDOB %>
			</div>
		</div>
	</div>
<% }else{ %>
	<div id="dob-D" class="oneField    ">
		<div id="dob-DW" class="oneFieldWrapper ui-draggable">
			<div id="dob-L" class="label preField reqMark">
					Applicant's Age:
			</div>
			<div class="providedResponse">
			
<%
java.util.Date now = new java.util.Date();
int iMonthNow = now.getMonth()+1;
int iYearNow = now.getYear()+1900;
int iMonthDOB = 0;
int iYearDOB = 0;
if(dDOB!=null){
	iMonthDOB = dDOB.getMonth()+1;
	iYearDOB = dDOB.getYear()+1900;
	
	int age = iYearNow - iYearDOB;
	
	if(iMonthDOB > iMonthNow){
		age--;
	}else if(iMonthDOB == iMonthNow){
		int iDayNow = now.getDate();
		int iDayDOB = dDOB.getDate();
		if(iDayDOB > iDayNow){
			age--;
		}
	}
	out.print(" "+age);
}else{
	out.print(" n/a");
}
%>
			</div>
		</div>
	</div>
<% } %>	
		<div id="gender-D" class="oneField    ">
			<div id="lang-DW" class="oneFieldWrapper ui-draggable">
				<div id="lang-L" class="label preField ">
						Are you male or female?
				</div>
				<div class="providedResponse">
		<%=applicinfo.getGender() %>
				</div>
			</div>
		</div>
		<div id="hasbachelors-D" class="oneField    ">
			<div id="hasbachelors-DW" class="oneFieldWrapper ui-draggable">
				<div id="hasbachelors-L" class="label preField reqMark">
						Do you currently have a bachelor's degree?
				</div>
				<div class="providedResponse">
					<span id="hasbachelors" class="choices  required">
		<%=applicinfo.getHasBachelors() %>
					</span>
				</div>
			</div>
		</div>
	<div id="credits-D" class="oneField  offstate-a  ">
		<div id="credits-DW" class="oneFieldWrapper ui-draggable">
			<div id="credits-L" class="label preField reqMark">
					About how many college credits have you earned?
			</div>
			<div class="providedResponse">
		<%=" "+applicinfo.getCredits() %>
			</div>
		</div>
	</div>
		<div id="workenvirontid-D" class="oneField    ">
			<div id="workenvirontid-DW" class="oneFieldWrapper ui-draggable">
				<div id="workenvirontid-L" class="label preField reqMark">
						What type of work environment do you prefer?
				</div>
				<div class="providedResponse">
					
<%					
			iTemp = applicinfo.getWorkEnvironTID();
			for(int index=0; index < aWorkEnvironList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aWorkEnvironList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
				</div>
			</div>
		</div>
		<div id="pospreftid-D" class="oneField    ">
			<div id="pospreftid-DW" class="oneFieldWrapper ui-draggable">
				<div id="pospreftid-L" class="label preField reqMark">
						What type of position do you prefer?
				</div>
				
				<div class="providedResponse">
					
<%					
			iTemp = applicinfo.getPosPrefTID();
			for(int index=0; index < aPosPrefList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPosPrefList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
				</div>
			</div>
		</div>
















<br><hr><br>



<fieldset id="tfa_NameAddress" class="section ui-draggable">
	<legend id="tfa_NameAddress-L">
		<div class="sectionEditableTitle" id="tfa_NameAddress-LTXT">Address</div>
	</legend>
	<div id="addr-D" class="oneField   labelsLeftAligned">
		<div id="addr-DW" class="oneFieldWrapper ui-draggable">
			<div id="addr-L" class="label preField reqMark">
					Street Address
			</div>
			<div class="providedResponse">
		<%=applicinfo.getAddrLine1() %>
			</div>
		</div>
	</div>
	<div id="city-D" class="oneField   labelsLeftAligned">
		<div id="city-DW" class="oneFieldWrapper ui-draggable">
			<div id="city-L" class="label preField reqMark">
					City
			</div>
			<div class="providedResponse">
		<%=applicinfo.getAddrCity() %>
			</div>
		</div>
	</div>
	<div id="tfa_1727426114964" class="section inline group ui-draggable">

		<div id="prov-D" class="oneField    ">
			<div id="prov-DW" class="oneFieldWrapper ui-draggable">
				<div id="prov-L" class="label preField reqMark">
						State/Province
				</div>
			<div class="providedResponse">
				<%
				String aszState = applicinfo.getAddrStateprov();

				if(null != aStateList){
					for(int index=0; index < aStateList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCSPStateCode();
						if(aszOptRqCode.equalsIgnoreCase( aszState ) ) out.print(aAppCode.getCSPStateName());
					}
				}
				%>
				</div>
			</div>
		</div>
		<div id="postal-D" class="oneField">
			<div id="postal-DW" class="oneFieldWrapper ui-draggable">
				<div id="postal-L" class="label preField reqMark">
					Zipcode
				</div>
				<div class="providedResponse">
		<%=applicinfo.getAddrPostalcode() %>
				</div>
			</div>
		</div>
	</div>
	<div id="country-D" class="oneField   labelsLeftAligned ">
		<div id="country-DW" class="oneFieldWrapper ui-draggable">
			<div id="country-L" class="label preField reqMark">
					Country
			</div>
			<div class="providedResponse">
				<%
				String aszCountry=applicinfo.getAddrCountryName() ;

				if(null != aCountryList){
					for(int index=0; index < aCountryList.size(); index++){
						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
						if(null == aAppCode) continue;
						String aszOptRqCode = aAppCode.getCTRIso();
						if(aszOptRqCode.equalsIgnoreCase( aszCountry ) ) out.print(aAppCode.getCTRPrintableName());
					}
				}
			  %>
			</div>
		</div>
	</div>
</fieldset>


<div id="geopref-D" class="oneField    ">
	<div id="geopref-DW" class="oneFieldWrapper ui-draggable">
		<div id="geopref-L" class="label preField reqMark">
				Do you have a particular geographic preference?
		</div>
		<div class="providedResponse">
		<%=applicinfo.getGeoPref() %>
		</div>
	</div>
</div>



<br><hr><br>





<div id="sourcetid-D" class="oneField    "> <!--  vid 361 --> 
	<label id="sourcetid-L" for="sourcetid" class="label preField reqMark">How did you hear about City Vision Internships?</label>
	<br>
	<div class="providedResponse">
<%					
			iTemp = applicinfo.getSourceTID();
			for(int index=0; index < aSourceList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSourceList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
	</div>
</div>
<div id="cvdegree_carreergoals-D" class="oneField  offstate-c  "> <!--  *******************************************NEW FIELD *************************************-->
	<label id="cvdegree_carreergoals-L" for="cvdegree_carreergoals" class="label preField ">
	<span>
		The free undergraduate tuition program is for students who would like to complete their degree with City Vision College in a Bachelor's in Addiction Studies, Nonprofit Management or Missions. If you are selected as an intern and accept the position, does completing your degree in one of these areas with City Vision fit into your career goals?
	</span>
	</label>
	<br>
	<div class="providedResponse">
		<span id="tfa_6922573434024" class="choices vertical ">
		<%=applicinfo.getCVDegreeCareerGoals() %>
		</span>
	</div>
</div>


<% if(applicinfo.getUID()==aCurrentUserObjLoggedIn.getUserUID()){ %>	
<fieldset id="tfa_InternshipRequir" class="section">
	<legend id="tfa_InternshipRequir-L">Internship Requirements</legend>
	<div class="htmlSection" id="tfa_6922573433991">
		<div class="htmlContent" id="tfa_6922573433991-HTML">
			<p>To be an intern with City Vision College, you must meet all of the requirements below.</p>
		</div>
	</div>
	<div id="tfa_AreyouaChristian-D" class="oneField    ">
		<label id="ischris-L" for="ischris" class="label preField reqMark">Are you a Christian?</label>
		<br>
		<div class="providedResponse">
			<span id="tfa_AreyouaChristian" class="choices  required">
		<%=applicinfo.getChristian() %>
			</span>
		</div>
	</div>
	<div id="agerequirement-D" class="oneField    ">
		<label id="agerequirement-L" for="agerequirement" class="label preField reqMark">Are you at least 18 years old?</label>
		<br>
		<div class="providedResponse">
			<span id="tfa_Areyouatleast18y" class="choices  required">
		<%=applicinfo.getAgeRequirement() %>
			</span>
		</div>
	</div>
	<div id="diploma-D" class="oneField    ">
		<label id="diploma-L" for="diploma" class="label preField reqMark">Do you have a high school diploma or equivalent, such as a GED?</label>
		<br>
		<div class="providedResponse">
		<%=applicinfo.getDiploma() %>
		</div>
	</div>
	<div id="citizentid-D" class="oneField    ">
		<label id="citizentid-L" for="citizentid" class="label preField reqMark">What is your citizenship status?</label>
		<br>
		<div class="providedResponse">
<%					
			iTemp = applicinfo.getCitizenTID();     
			for(int index=0; index < aCitizenList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCitizenList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
		</div>
	</div>
	<div id="timecommitability-D" class="oneField    ">
		<label id="timecommitability-L" for="timecommitability" class="label preField reqMark">Are you able to commit to the full length of the internship?</label>
		<br>
		<div class="providedResponse">
			<span id="tfa_Areyouabletomake" class="choices  required">
		<%=applicinfo.getTimeCommitAbility() %>
			</span>
		</div>
	</div>
	<div id="attendingbachelors-D" class="oneField    ">
		<label id="attendingbachelors-L" for="attendingbachelors" class="label preField reqMark">If you do not have a bachelor's degree, have you been active in school in the past 5 years (either attending high school or completing a course in an accredited college)?</label>
		<br>
		<div class="providedResponse">
			<span id="tfa_Areyouactivelypu" class="choices  required">
		<%=applicinfo.getBachelorsAttending() %>
			</span>
		</div>
	</div>
	<div id="lastyrhighschool-D" class="oneField  offstate-a offstate-b  "> <!-- ****************** NEW FIELD ******************** -->
		<label id="lastyrhighschool-L" for="lastyrhighschool" class="label preField reqMark">What is the last year you were active in school?</label>
		<br>
		<div class="providedResponse">
			<%=applicinfo.getLastYrActiveHS() %>
		</div>
	</div>


</fieldset>
<% } %>





		<div id="skillstid-D" class="oneField    ">
			<div id="skillstid-DW" class="oneFieldWrapper ui-draggable">
				<div id="skillstid-L" class="label preField ">
						Do you have any special experience or skills that might be useful in supporting ministries?
				</div>
				<div class="providedResponse">
					
<%					
			iTemp = applicinfo.getSkillsTID();
			for(int index=0; index < aSkillsList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aSkillsList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
				</div>
			</div>
		</div>
		<div id="poppreftid-D" class="oneField  offstate-c offstate-d  ">
			<div id="poppreftid-DW" class="oneFieldWrapper ui-draggable">
				<div id="poppreftid-L" class="label preField ">
						Do you have a strong preference for working with either youth or adults?
				</div>
				<div class="providedResponse">
					
<%					
			iTemp = applicinfo.getPopulPrefTID();
			for(int index=0; index < aPopPrefList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aPopPrefList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
				</div>
			</div>
		</div>
		<div id="lang-D" class="oneField    ">
			<div id="lang-DW" class="oneFieldWrapper ui-draggable">
				<div id="lang-L" class="label preField ">
						If you speak any languages besides English, please list them and describe your level of competence with each.
				</div>
				<div class="providedResponse">
		<%=applicinfo.getLang() %>
				</div>
			</div>
		</div>
		<div id="church-D" class="oneField    ">
			<div id="church-DW" class="oneFieldWrapper ui-draggable">
				<div id="church-L" class="label preField reqMark">
						Are you actively attending a local church?
				</div>
				<div class="providedResponse">
					<span id="church" class="choices  required">
		<%=applicinfo.getChurch() %>
					</span>
				</div>
			</div>
		</div>
		<div id="degreeprogtid-D" class="oneField    ">
			<div id="degreeprogtid-DW" class="oneFieldWrapper ui-draggable">
				<div id="degreeprogtid-L" class="label preField ">
						In which City Vision degree program are you interested?
				</div>
				<div class="providedResponse">
<%					
			iTemp = applicinfo.getDegreeProgTID();
			for(int index=0; index < aCVDegreeProgList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVDegreeProgList.get(index);
				if(null == aAppCode) continue;
				int iOptRqCode = aAppCode.getAPCIdSubType();
				if(iOptRqCode== iTemp )  out.println( aAppCode.getAPCDisplay());
			}
%>			
				</div>
			</div>
		</div>
		<div id="major-D" class="oneField    ">
			<div id="major-DW" class="oneFieldWrapper ui-draggable">
				<div id="major-L" class="label preField reqMark">
						If you didn't do a City Vision internship, in what degree program would you like to major in college?
				</div>
				<div class="providedResponse">
		<%=applicinfo.getMajor() %>
				</div>
			</div>
		</div>

	<div id="careergoals-D" class="oneField    ">
		<div id="careergoals-DW" class="oneFieldWrapper ui-draggable">
			<div id="careergoals-L" class="label preField reqMark">
					What are your career goals and how do you see this internship and degree program fitting in with those goals?
			</div>
			<div class="providedResponse">
		<%=applicinfo.getCareerGoals() %>
			</div>
		</div>
	</div>
	<div id="hrlycommit-D" class="oneField    ">
		<div id="hrlycommit-DW" class="oneFieldWrapper ui-draggable">
			<div id="hrlycommit-L" class="label preField reqMark">
					This is a 30 to 35 hour/week position. Interns without a Bachelors will also be required at least an additional 15-20 hours of studying per week. Are you able to make this type of commitment and are you able to work approximately 10am to 6pm, Monday through Friday?
			</div>
			<div class="providedResponse">
				<span id="hrlycommit" class="choices  required">
		<%=applicinfo.getHrlyCommit() %>
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
			<div class="providedResponse">
				<span id="livablestipend" class="choices  required">
		<%=applicinfo.getLivableStipend() %>
				</span>
			</div>
		</div>
	</div>
	<div id="livablestipendexpl-D" class="oneField    ">
		<div id="livablestipendexpl-DW" class="oneFieldWrapper ui-draggable">
			<div id="livablestipendexpl-L" class="label preField reqMark">
					If you answered "Yes" to the previous question, please explain how this amount will be sustainable for you. Otherwise, state the circumstances that cause this to be a hardship.
			</div>
			<div class="providedResponse">
		<%=applicinfo.getLivableStipendExpl() %>
			</div>
		</div>
	</div>
	<div id="crimrecord-D" class="oneField    ">
		<div id="crimrecord-DW" class="oneFieldWrapper ui-draggable">
			<div id="crimrecord-L" class="label preField reqMark">
					Have you ever been convicted of a crime?
			</div>
			<div class="providedResponse">
				<span id="crimrecord" class="choices  required">
		<%=applicinfo.getCrimRecord() %>
				</span>
			</div>
		</div>
	</div>
	<div id="crimdescrip-D" class="oneField  offstate-e  ">
		<div id="crimdescrip-DW" class="oneFieldWrapper ui-draggable">
			<div id="crimdescrip-L" class="label preField ">
					Please describe the circumstances regarding your criminal conviction:
			</div>
			<div class="providedResponse">
		<%=applicinfo.getCrimDescrip() %>
			</div>
		</div>
	</div>
	<div id="housing-D" class="oneField    ">
		<div id="housing-DW" class="oneFieldWrapper ui-draggable">
			<div id="housing-L" class="label preField reqMark">
					Do you need housing and will you accept housing on site at a Christian homeless shelter or other ministry housing?
			</div>
			<div class="providedResponse">
				<span id="housing" class="choices  required">
		<%=applicinfo.getHousing() %>
				</span>
			</div>
		</div>
	</div>
<% if(applicinfo.getUID()==aCurrentUserObjLoggedIn.getUserUID()){ %>		
	<div id="servicesite-D" class="oneField    ">
		<div id="servicesite-DW" class="oneFieldWrapper ui-draggable">
			<div id="servicesite-L" class="label preField ">
					Are you applying with a particular service site (one of our partnering churches, ministries, or nonprofit organizations) in mind? If so, which one(s)?
			</div>
			<div class="providedResponse">
		<%
		a_aszTemp = applicinfo.getORGNamesArray();
		for(int i=0; i<a_aszTemp.length; i++){
			if(i>0)	out.print(", ");
			out.print(a_aszTemp[i]);
		}
		%>
			</div>
		</div>
	</div>
<% }else{ %>
<%
		int[] a_iTemp = applicinfo.getORGNIDsArray();
		a_aszTemp = applicinfo.getORGNamesArray();
		for(int i=0; i<a_aszTemp.length; i++){
			try{
				int iTmpOrgNID = a_iTemp[i];
				if(iTmpOrgNID>0){
					int[] a_iUserOrgNIDsArray = aCurrentUserObjLoggedIn.getOrgNIDsArray();
					for(int j=0; j<a_iUserOrgNIDsArray.length; j++){
						if(a_iUserOrgNIDsArray[j]==iTmpOrgNID){
%>
	<div id="servicesite-D" class="oneField    ">
		<div id="servicesite-DW" class="oneFieldWrapper ui-draggable">
			<div id="servicesite-L" class="label preField ">
					Are you applying with a particular service site (one of our partnering churches, ministries, or nonprofit organizations) in mind? If so, which one(s)?
			</div>
			<div class="providedResponse">
<%
			out.print(a_aszTemp[i]);
%>
			</div>
		</div>
	</div>
<%							
						}
					}
				}
			}catch(Exception ex){
				
			}
		}
%>
<% } %>	
	<div id="locpref-D" class="oneField    ">
		<div id="locpref-DW" class="oneFieldWrapper ui-draggable">
			<div id="locpref-L" class="label preField ">
					Do you have any further information to share about your location preference?
			</div>
			<div class="providedResponse">
		<%=applicinfo.getLocPrefInfo() %>
			</div>
		</div>
	</div>
	<div id="starttime-D" class="oneField    ">
		<div id="starttime-DW" class="oneFieldWrapper ui-draggable">
			<div id="starttime-L" class="label preField reqMark">
					When are you available to start?
			</div>
			<div class="providedResponse">
		<%=applicinfo.getStartTime() %>
			</div>
		</div>
	</div>
	<div id="forwardresume-D" class="oneField    ">
		<div id="forwardresume-DW" class="oneFieldWrapper ui-draggable">
			<div id="forwardresume-L" class="label preField reqMark">
					If we are unable to hire a good candidate, we sometimes forward his/her resume to another comparable organization. Are you comfortable with us doing that?
			</div>
			<div class="providedResponse">
				<span id="forwardresume" class="choices  required">
		<%=applicinfo.getForwardResume() %>
			</div>
		</div>
	</div>
	<div id="webcam-D" class="oneField    ">
		<div id="webcam-DW" class="oneFieldWrapper ui-draggable">
			<div id="webcam-L" class="label preField reqMark">
					Do you have access to a webcam (so that we could see you if we were to interview you by phone)?
			</div>
			<div class="providedResponse">
				<span id="webcam" class="choices  required">
		<%=applicinfo.getWebcam() %>
				</span>
			</div>
		</div>
	</div>
	<div id="skype-D" class="oneField  offstate-b  ">
		<div id="skype-DW" class="oneFieldWrapper ui-draggable">
			<div id="skype-L" class="label preField ">
					Your Skype Name (if you have one)
			</div>
			<div class="providedResponse">
		<%=applicinfo.getSkype() %>
			</div>
		</div>
	</div>
	
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
		<div class="htmlSection ui-draggable" id="tfa_9755162050359">
			<div class="htmlContent" id="tfa_9755162050359-HTML">
				<p>Be sure to contact the people you listed as references and let them know that we will be sending them a reference form by E-mail.</p>
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
					<div class="providedResponse">
						<%=applicinfo.getPastoralRef() %>
					</div>
				</div>
			</div>
			<div id="pastoralrefchurch-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefchurch-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefchurch-L" class="label preField reqMark">
							Church/Ministry
					</div>
					<div class="providedResponse">
						<%=applicinfo.getPastoralRefChurch() %>
					</div>
				</div>
			</div>
			<div id="pastoralrefphone-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefphone-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefphone-L" class="label preField reqMark">
							Phone
					</div>
					<div class="providedResponse">
						<%=applicinfo.getPastoralRefPhone() %>
					</div>
				</div>
			</div>
			<div id="pastoralrefemail-D" class="oneField   labelsLeftAligned ">
				<div id="pastoralrefemail-DW" class="oneFieldWrapper ui-draggable">
					<div id="pastoralrefemail-L" class="label preField reqMark">
							Email
					</div>
					<div class="providedResponse">
						<%=applicinfo.getPastoralRefEmail() %>
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
					<div class="providedResponse">
						<%=applicinfo.getProfRef() %>
					</div>
				</div>
			</div>
			<div id="profreforg-D" class="oneField   labelsLeftAligned ">
				<div id="profreforg-DW" class="oneFieldWrapper ui-draggable">
					<div id="profreforg-L" class="label preField reqMark">
							Organization
					</div>
					<div class="providedResponse">
						<%=applicinfo.getProfRefOrg() %>
					</div>
				</div>
			</div>
			<div id="profrefphone-D" class="oneField   labelsLeftAligned ">
				<div id="profrefphone-DW" class="oneFieldWrapper ui-draggable">
					<div id="profrefphone-L" class="label preField reqMark">
							Phone
					</div>
					<div class="providedResponse">
						<%=applicinfo.getProfRefPhone() %>
					</div>
				</div>
			</div>
			<div id="profrefemail-D" class="oneField   labelsLeftAligned ">
				<div id="profrefemail-DW" class="oneFieldWrapper ui-draggable">
					<div id="profrefemail-L" class="label preField reqMark">
							Email
					</div>
					<div class="providedResponse">
						<%=applicinfo.getProfRefEmail() %>
					</div>
				</div>
			</div>
		</fieldset>
	</fieldset>
	

</div>
</div>
</div><!-- end div id="body" -->

</div><!-- end div id="maincontent" -->


<script type="text/javascript">
$(document).ready(function(){
<% if(applicinfo.getLastYrActiveHS() < 1900){ %> 
	$('#lastyrhighschool-D').hide();
<% } %>	
<% if(applicinfo.getCredits() < 1900){ %> 
	$('#credits-D').hide();
<% } %>	
<% if(applicinfo.getCVDegreeCareerGoals().length() < 1){ %> 
$('#cvdegree_carreergoals-D').hide();
<% } %>	
<% if(applicinfo.getCrimDescrip().length() < 1){ %> 
$('#crimdescrip-D').hide();
<% } %>	
<% if(applicinfo.getSkype().length() < 1){ %> 
$('#skype-D').hide();
<% } %>	




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

