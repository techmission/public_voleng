<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

</head>

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<style>
ul.columns {float:left; width:auto;}
ul.columns li {padding-left:0px; //list-style: square;}
ul.columns li a{ color:#003366; text-decoration:none;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
ul.NoBullet {
  list-style-type: none}
.profile_box{width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; height:270px; padding:10px; float:left; margin: 0 20px 20px 10px;}
.profile_box a, .box a{ color:#003366; text-decoration:none;}// line-height:2em;}
.profile_box a:hover, .box a:hover {text-decoration:underline;}
.box {min-width: 675px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; min-height:270px; //height:270px; padding:10px; float:left;}
.find_box {padding: 10px; border: 3px solid #93B6F0; float:left; text-align:left; width:480px;}
h3{margin:0px;}
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
.notice { 
font-size:20px;
color:#83A2F4;
font-weight:bold;
padding:3px;
width:140px;
text-decoration:none;
}

</style>


<% out.println("<!--"+request.getQueryString()+"-->"); %>

<% int iTmp=0, iCount=0; %>


<%
String aszUID="" + aCurrentUserObj.getUserUID();
String aszUseType="";
if(request.getParameter("listtype")!=null)	aszUseType=request.getParameter("listtype");
String aszVolPhone="";
String aszOrgEmail="";
String aszVolEmail="";

aszEmptySearch="true";


String aszPageTitle="";
if(org.getORGNID()>0){
	aszPageTitle="Volunteer Referrals for "+org.getORGOrgName()+":";
}else{
	aszPageTitle="Referral History for "+aCurrentUserObj.getUSPNameFirst()+" "+aCurrentUserObj.getUSPNameLast()+":";
}

%>


<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title"><%=aszPageTitle%></span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<!-- BEGIN MAINCONTENT -->
<% if(aszUseType.equalsIgnoreCase("orgmanagement") || aszUseType.equalsIgnoreCase("oppmanagement")){ %>
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >
<% }else{ %>
<div id="maincontent">
<% } %>

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;"><%=aszPageTitle%></span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/volunteerdashboard.jsp">account home</a></div>
</div>
<% } %>

<% if(aszUseType.equalsIgnoreCase("orgmanagement") || aszUseType.equalsIgnoreCase("oppmanagement")){ %>
	<% if(b_includeLeftSidebar==true){ %>
		<%@ include file="/template_include/left_sidebar_org.inc" %>
	<% } %>
<% } %>
 <div id="body">
 
 
<br><br>
<div>
	<div style="float: left; padding-left: 5px; text-align: center;">

      <div class="box">	
<style>
ul.table{
	padding:10px 0 0;
	margin-left: 0px;
	list-style:none;
}
ul.table li{
	float:left;
	margin-right: 5px;
}
ul.table li.first{
	width:15px;
}
ul.table li.second{
	width:200px;
}
ul.table li.third{
	width:200px;
}
<% if(aszUseType.equalsIgnoreCase("orgmanagement") || aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
ul.table li.second{
	width:110px;
}
ul.table li.third{
	width:170px;
}
<% } %>
ul.table li.fourth{
	width:60px;
}
ul.table li.fifth{
	width:150px;
	word-wrap: break-word;
}
ul.table li.sixth{
	width:80px;
}
ul.table li.seventh{
	width:70px;
}
</style>

<ul id="headings" class="table">
<% if(aszUseType.equals("")){ %>
		<li class="first">&nbsp;</li>
		<li class="second"><strong>Name</strong></li>
		<li class="third"><strong>Organization</strong></li>
		<li class="fourth"><strong>Referral Date</strong></li>
		<li class="fifth"><strong>Email</strong></li>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement") || aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
		<li class="first">&nbsp;</li>
		<li class="second"><strong>Name</strong></li>
		<li class="third"><strong>Position of Interest</strong></li>
		<li class="fourth"><strong>Referral Date</strong></li>
		<li class="fifth"><strong>Email</strong></li>
		<li class="sixth"><strong>Phone</strong></li>
		<li class="seventh"><strong>Attachments</strong></li>
<% } %>
</ul>
<br clear="all" />

	<logic:iterate id="email" name="emaillist" type="com.abrecorp.opensource.dataobj.EmailInfoDTO">
<%
iCount ++;
iTmp = email.getEmailId();
aszVolPhone=email.getEmailVolPhone1();
aszOrgEmail=email.getEmailToUser();

int iVolUID=email.getEmailVolUID();

if(aszOrgEmail!=null)	if(aszOrgEmail.endsWith(","))	aszOrgEmail=aszOrgEmail.substring(0,aszOrgEmail.length()-1);
aszVolEmail=email.getEmailFromUser();
if(aszVolEmail!=null)	if(aszVolEmail.endsWith(","))	aszVolEmail=aszVolEmail.substring(0,aszVolEmail.length()-1);

String aszSentDate = new java.text.SimpleDateFormat("M/dd/yyyy").format(email.getEmailCreateDt());
// lookup the username based on the uid
String aszUsername = "";
String aszUserProfileURL = "";
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
if(iVolUID > 0){
	aszUsername = aCodes.getUsernameByUID(iVolUID);
	aszUserProfileURL = request.getContextPath() + "/profiles/" + aszUsername;
}
String aszAttachment = "";
String aszCoverLetter = email.getCoverLetterFileName();
String aszApplicaiton = email.getApplicationFileName();
int iNID = email.getEmailId();
if(aszCoverLetter.length()>0){
	aszAttachment = "<a href=\""+request.getContextPath()+"/fileDownload.do?attachmenttype=cover_letter&nid="+iNID+"\">Cover Letter</a>";
	if(aszApplicaiton.length()>0){
		aszAttachment += " <a href=\""+request.getContextPath()+"/fileDownload.do?attachmenttype=application&nid="+iNID+"\">Application</a>";
	}
}else if(aszApplicaiton.length()>0){
	aszAttachment = "<a href=\""+request.getContextPath()+"/fileDownload.do?attachmenttype=application&nid="+iNID+"\">Application</a>";
}

%>

<ul id="entry" class="table">
<% if(aszUseType.equals("")){ %>
	<li class="first"><%out.print(iCount);%>. </li>
	<li class="second"><a href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></a>&nbsp;</li>
	<li class="third"><a href="<%=aszPortal%>/org.do?method=showOrganization&orgnid=<%=email.getEmailOrgNID()%>"><%=email.getEmailOrgName()%></a>&nbsp;</li>
	<li class="fourth"><%=aszSentDate%></li>
	<li class="fifth"><a href="mailto:<%=aszOrgEmail%>"><%=aszOrgEmail%></a>&nbsp;</li>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement")){ %>      
	<li class="first"><%out.print(iCount);%>. </td>
	<% if(aszUsername.length()>0){ %>
	<li class="second"><a href="<%=aszUserProfileURL%>"><%=email.getEmailVolFN()%>&nbsp;<%=email.getEmailVolLN()%></a>&nbsp;</li>
	<% }else{ %>
	<li class="second"><a href="#"><%=email.getEmailVolFN()%>&nbsp;<%=email.getEmailVolLN()%></a>&nbsp;</li>
	<% } %>
	<!-- li class="second"><a href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></li -->
	<li class="third"><a href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></a>&nbsp;</li>
	<li class="fourth"><%=aszSentDate%>&nbsp;</li>
	<li class="fifth"><a href="mailto:<%=aszVolEmail%>"><%=aszVolEmail%></a>&nbsp;</li>
	<li class="sixth"><%=aszVolPhone%>&nbsp;</li>
	<li class="seventh"><%=aszAttachment%></li>
<% }else if(aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
	<li class="first"><%out.print(iCount);%>. </li>
	<% if(aszUsername.length()>0){ %>
	<li class="second"><a href="<%=aszUserProfileURL%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a>&nbsp;</li>
	<% }else{ %>
	<li class="second"><a href="#"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a>&nbsp;</li>
	<% } %>
	<!--  li class="second"><a href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></li-->
	<li class="third"><a href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></a>&nbsp;</li>
	<li class="fourth"><%=aszSentDate%>&nbsp;</li>
	<li class="fifth"><a href="mailto:<%=aszVolEmail%>"><%=aszVolEmail%></a>&nbsp;</li>
	<li class="sixth"><%=aszVolPhone%>&nbsp;</li>
	<li class="seventh"><%=aszAttachment%></li>
<% } %>
</ul>
<br clear="all" />
    </logic:iterate>

     
 <div style="clear:both;"></div>
    
<% if(! (iTmp > 0)){ %>
<font class="notice">
<center><br><br>
<%
	if(aszUseType.equals("")){ 
%>
	It appears you have not inquired about any opportunities yet
<%
	}else if(aszUseType.equalsIgnoreCase("orgmanagement")){ 
%>
	It appears no one has inquired about your opportunities yet
<%
	}else if(aszUseType.equalsIgnoreCase("oppmanagement")){ 
%>
	It appears no one has inquired about this opportunity yet
<%
	}else{ 
%>
	It appears you have not inquired about any opportunities yet
<%
	}
%>
</center>
</font>
<% } %>
     
     
          
</div>
</div>
         


</div>

   
    </div>
</div>

<!-- start sidebar information -->
<% if(aszUseType.equalsIgnoreCase("orgmanagement") || aszUseType.equalsIgnoreCase("oppmanagement")){ %>
	<% if( b_includeLeftSidebar==false ){ %>
	<%@ include file="/template/sidebar.inc" %>
	<% } %>
	<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<% }else if(aszUseType.equals("")){ %>
	<%@ include file="/template/sidebar.inc" %>
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
<% } %>

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
