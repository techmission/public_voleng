<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<bean:define id="emailinfo" name="emailinfo" type="com.abrecorp.opensource.dataobj.EmailInfoDTO"/>

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

String aszSubject = aszSubdomain + " Referral: " + emailinfo.getEmailOppName();
%>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">I Want to Volunteer!</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">I Want to Volunteer!</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/search.jsp">search</a> 
			&gt;<a href="http://www.christianvolunteering.org" onclick="window.history.back(); return false;"> volunteer opportunity </a>&gt; i want to volunteer!   </div>
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
<html:form action="/email.do?volunteer"  >
<html:hidden property="method" value="showEmail" />
<html:hidden property="subdom" value="<%=aszSubdomain%>" />
<html:hidden property="siteemail" value="<%=aszEmailHost%>" />

					<h3 align= "right">
						<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a> ]</font>
					</h3>
				
	<h2>Sign up to Volunteer</h2>
	Fill out this form to email <%=emailinfo.getEmailOrgName()%>. They will receive your contact information and any message you include. They should contact you soon about volunteering.
	<TABLE cellSpacing=2 cellPadding=2 align=center border=0>
    <TBODY>
    <TR>
    	<td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="80" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="40" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
    </TR>


    

<tr><td height=10 colspan=6></td></tr>
	<TR>
        <td valign="middle" align="right"><b> First Name: </b></td>
        <td valign="top" align="left" colspan=5>
			<html:hidden property="volfn" value="<%=emailinfo.getEmailVolFN()%>" />
            <%=emailinfo.getEmailVolFN()%>
        </td>
	</TR>
	<TR>
        <td valign="middle" align="right"><b> Last Name: </b></td>
        <td valign="top" align="left" colspan=5>
            <html:hidden property="volln" value="<%=emailinfo.getEmailVolLN()%>" />
            <%=emailinfo.getEmailVolLN()%>
        </td>
	</TR>
		<TR>
        <td valign="middle" align="right"><b> Phone: </b></td>
        <td valign="top" align="left" colspan=5>
            <html:hidden property="volphone" value="<%=emailinfo.getEmailVolPh()%>" />
            <%=emailinfo.getEmailVolPh()%>
        </td>
	</TR>
		<TR>
        <td valign="middle" align="right"><b> Your Email: </b><span class="criticaltext">*</span></td>
        <td valign="top" align="left" colspan=5>
            <INPUT type="text" name="fromuser" size=40 value="<%=emailinfo.getEmailFromUser()%>"  style=" border: 1px solid #7f9db9;"/>
        </td>
	</TR>
	

	<TR>
        <td valign="middle" align="right"><b> Subject: </b></td>
        <td valign="top" align="left" colspan=5>
             <html:hidden property="emailsubject" value="<%=aszSubject%>" />
            <%=aszSubject%>
        </td>
	</TR>
	<TR>
        <td valign="top" align="right"><b> Message: </b></td>
        <td valign="top" align="left" colspan=5>
            <TEXTAREA name="emailbodytxt" cols=60 rows=15><%=emailinfo.getEmailBodyText()%></TEXTAREA>
             <html:hidden property="emailbodytxtintro" value="<%=emailinfo.getEmailBodyTextIntro()%>" />
             <html:hidden property="emailbodytxtclosing" value="<%=emailinfo.getEmailBodyTextClosing()%>" />

             <!-- volunteer information -->
             <html:hidden property="emailbodytxtres" value="<%=emailinfo.getEmailBodyTextRes()%>" />
             <html:hidden property="volfn" value="<%=emailinfo.getEmailVolFN()%>" />
             <html:hidden property="volln" value="<%=emailinfo.getEmailVolLN()%>" />
             <html:hidden property="volphone" value="<%=emailinfo.getEmailVolPh()%>" />
             <html:hidden property="volchris" value="<%=emailinfo.getEmailVolChris()%>" />
             <html:hidden property="volskillypes" value="<%=emailinfo.getEmailVolSkillTypes()%>" />
             <html:hidden property="volskills" value="<%=emailinfo.getEmailVolSkills()%>" />
             <html:hidden property="volskills2" value="<%=emailinfo.getEmailVolSkills2()%>" />
             <html:hidden property="volskills3" value="<%=emailinfo.getEmailVolSkills3()%>" />
             <html:hidden property="stmreferences" value="<%=emailinfo.getEmailSTMReferencesText()%>" />

             
        </td>
	</TR>
	
	<!-- organizational contact information -->
	<TR>
        <td valign="middle" align="right"><A class="black10"> </A></td>
        <td valign="top" align="left" colspan=5>
            <html:hidden property="touser" value="<%=emailinfo.getEmailToUser()%>" />
             <html:hidden property="contactfn" value="<%=emailinfo.getEmailContactFN()%>" />
             <html:hidden property="contactln" value="<%=emailinfo.getEmailContactLN()%>" />
             <html:hidden property="oppname" value="<%=emailinfo.getEmailOppName()%>" />
             <html:hidden property="orgname" value="<%=emailinfo.getEmailOrgName()%>" />
             <html:hidden property="orgaddr1" value="<%=emailinfo.getEmailOrgAddr1()%>" />
             <html:hidden property="orgcity" value="<%=emailinfo.getEmailOrgCity()%>" />
             <html:hidden property="orgstate" value="<%=emailinfo.getEmailOrgState()%>" />
             <html:hidden property="orgprov" value="<%=emailinfo.getEmailOrgProv()%>" />
             <html:hidden property="orgcountry" value="<%=emailinfo.getEmailOrgCountry()%>" />
             <html:hidden property="orgphone" value="<%=emailinfo.getEmailOrgPhone()%>" />
             <html:hidden property="orgweb" value="<%=emailinfo.getEmailOrgWeb()%>" />
             <html:hidden property="oppbkgrnd" value="<%=emailinfo.getEmailOppBkgrnd()%>" />
             <html:hidden property="oppreference" value="<%=emailinfo.getEmailOppReference()%>" />
        </td>
	</TR>
	
	
	<TR>
<td>            <html:hidden property="employment" value="Y" />
             <html:hidden property="oppnid" value="<%="" + emailinfo.getEmailOppNID()%>" />
             <html:hidden property="orgnid" value="<%="" + emailinfo.getEmailOrgNID()%>" />
             <html:hidden property="voluid" value="<%="" + emailinfo.getEmailVolUID()%>" />
             <html:hidden property="oppid" value="<%="" + emailinfo.getEmailOppId()%>" />
             <html:hidden property="orgid" value="<%="" + emailinfo.getEmailOrgId()%>" />
             <html:hidden property="volid" value="<%="" + emailinfo.getEmailVolunteerId()%>" />

        </td>
	</TR>
	<TR>
		<TD>&nbsp;</td>
		<TD colspan=5>
		
			<A HREF="javascript:void(0);" onclick="return SubmitPost()"><img border="0" src="http://christianvolunteering.org/imgs/button_send.gif" width="59" height="24"></A>
			</TD>
	</TR>
	<TR>
	    <td valign="top" colspan=6>
			<!--
			<%=emailinfo.getErrorMsg()%>
			-->
	    </td>
	</TR>
      <TR><TD colspan=6 height=20></td></tr>
      <TR><TD colspan=6><b>NOTE:</b> If you have provided a volunteer resume, this will be sent to the organization as well as any information provided above.</td></tr>
      <tr><td height=10></td></tr>
	</TBODY>
      </TABLE>
</html:form>

</div>
</div>

      <P>
      <BR>&nbsp;</P></div>

  <%@ include file="/template_include/gigya_linkAccounts.inc" %>
  

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
