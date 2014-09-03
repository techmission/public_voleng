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

String aszSubject = aszSubdomain + aszPortal + " Referral: " + emailinfo.getEmailOppName();

String aszOppNID="" + emailinfo.getEmailOppNID();
String aszOrgNID="" + emailinfo.getEmailOrgNID();
String aszVolUID="" + emailinfo.getEmailVolUID();
%>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">I Want to Help!</span>
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
<span style="float: left;">I Want to Help!</span>
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
<form action="<%=aszPortal%>/email.do?volunteer<%if(aszPortal.length()>0){%>&method=sendEmail<%}%>" name="emailForm" method="post" >
<input type="hidden" name="method" value="sendEmail" />
<input type="hidden" name="subdom" value="<%=aszSubdomain%>" />
<input type="hidden" name="siteemail" value="<%=aszEmailHost%>" />

             <!-- volunteer information -->
             <input type="hidden" name="oppnid" value="<%=aszOppNID%>" />
             <input type="hidden" name="orgnid" value="<%=aszOrgNID%>" />
             <input type="hidden" name="voluid" value="<%=aszVolUID%>" />

	<h3 align= "right">
		<font class="link"> [ <a href="#" onclick="window.history.back(); return false;">Back</a> ]</font>
	</h3>
				
	<h2>Sign up to Volunteer</h2>
	Fill out this form to email <%=emailinfo.getEmailOrgName()%>. They will receive your contact information and any message you include. They should contact you soon about volunteering.
	<table cellSpacing=2 cellPadding=2 align=center border=0>
    <tbody>
<% if( 	aszMobileSubdomain.length() < 3 ) { %>
    <tr>
    	<td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="80" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="40" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
        <td><img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="60" height="1"></td>
    </tr>
<% } %>

    

<tr><td height=10 colspan=6></td></tr>
	<tr>
        <td valign="middle" align="right"><b> First Name: </b></td>
        <td valign="top" align="left" colspan=5>
            <%=emailinfo.getEmailVolFN()%>
        </td>
	</tr>
	<tr>
        <td valign="middle" align="right"><b> Last Name: </b></td>
        <td valign="top" align="left" colspan=5>
            <%=emailinfo.getEmailVolLN()%>
        </td>
	</tr>
		<tr>
        <td valign="middle" align="right"><b> Phone: </b></td>
        <td valign="top" align="left" colspan=5>
            <%=emailinfo.getEmailVolPhone1()%>
        </td>
	</tr>
		<tr>
        <td valign="middle" align="right"><b> Your Email: </b><span class="criticaltext">*</span></td>
        <td valign="top" align="left" colspan=5>
            <INPUT type="text" name="fromuser" size=40 value="<%=emailinfo.getEmailFromUser()%>"  style=" border: 1px solid #7f9db9;"/>
        </td>
	</tr>
	<tr>
        <td valign="middle" align="right"><b> Subject: </b></td>
        <td valign="top" align="left" colspan=5>
             <input type="hidden" name="emailsubject" value="<%=aszSubject%>" />
            <%=aszSubject%>
        </td>
	</tr>
	</tbody>
      </table>
	
	<br />
	<div style="float:left; padding-left:20px;">
		
		<b>Cover Letter E-mail: </b>
		<br />
<% if( 	aszMobileSubdomain.length() > 3 ) { %>
            <textarea name="emailbodytxt" cols=35 rows=15><%=emailinfo.getEmailBodyText()%></textarea>
<% }else{ %>
            <textarea name="emailbodytxt" cols=130 rows=25><%=emailinfo.getEmailBodyText()%></textarea>
<% } %>
		<br />
		<a HREF="javascript:void(0);" onclick="return SubmitPost()"><img border="0" src="http://christianvolunteering.org/imgs/button_send.gif" width="59" height="24"></a>
	</div>

			<!--
			<%=emailinfo.getErrorMsg()%>
			-->
</form>

</div>
</div>

      <p>
      <br>&nbsp;</p></div>

  

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
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

