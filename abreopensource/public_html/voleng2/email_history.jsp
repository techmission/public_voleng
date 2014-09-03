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

<style>
ul.columns {float:left; width:auto;}
ul.columns li {padding-left:0px; //list-style: square;}
ul.columns li a{ color:#003366; text-decoration:none;}
ul.columns li a:hover, a:hover { text-decoration:underline;}
ul.NoBullet {
  list-style-type: none}
.profile_box{width: 200px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; height:270px; padding:10px; float:left; margin: 0 20px 20px 10px;}
.profile_box a, .box a{ color:#003366; text-decoration:none; line-height:2em;}
.profile_box a:hover, .box a:hover {text-decoration:underline;}
.box {width: 675px; text-align:left; background-color:#FFF; border:3px solid #93B6F0; min-height:270px; //height:270px; padding:10px; float:left;}
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

</style>


<% out.println("<!--"+request.getQueryString()+"-->"); %>

<% int iTmp=0; %>


<%
String aszUID="" + aCurrentUserObj.getUserUID();
String aszUseType=request.getParameter("listtype");
String aszVolPhone="";
String aszOrgEmail="";
String aszVolEmail="";
%>



<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Sitemap</span>
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
<span style="float: left;">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/volunteerdashboard.jsp">account home</a></div>
</div>
<% } %>

<!-- personality type is: <%=aCurrentUserObj.getUSPPersonality()%> <%=aCurrentUserObj.getUSPPersonalityTID()%>-->
 <div id="body">
 
 
<br><br>
<div>
	<div style="float: left; padding-left: 5px; text-align: center;">

      <div class="box">	
		 <h2 align="center">Manage Emails</h2>
<% /*if(aszUseType==null){ %>
	<tr><th>Organization</th><th>Organization Email</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement")){ %>      
	<tr><th>Volunteer Name</th><th>Volunteer Email</th><th>Phone</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else if(aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
	<tr><th>Volunteer Name</th><th>Volunteer Email</th><th>Phone</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else{ %>      
	<tr><th>Organization</th><th>Organization Email</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }*/ %>
	<logic:iterate id="email" name="emaillist" type="com.abrecorp.opensource.dataobj.EmailInfoDTO">
<%
iTmp = email.getEmailId();
aszVolPhone=email.getEmailVolPhone1();
aszOrgEmail=email.getEmailToUser();
if(aszOrgEmail!=null){
	if(aszOrgEmail.endsWith(",")){
		aszOrgEmail=aszOrgEmail.substring(0,aszOrgEmail.length()-1);
	}
}
aszVolEmail=email.getEmailFromUser();
if(aszVolEmail!=null){
	if(aszVolEmail.endsWith(",")){
		aszVolEmail=aszVolEmail.substring(0,aszVolEmail.length()-1);
	}
}


if(! (iTmp>1) ){ 
	if(aszUseType.equalsIgnoreCase("orgmanagement")){ 
%>
	<tr><td colspan=4 align="center">It appears no one has inquired about your opportunities yet</td></tr>
<%
	}else if(aszUseType.equalsIgnoreCase("oppmanagement")){ 
%>
	<tr><td colspan=4 align="center">It appears no one has inquired about this opportunity yet</td></tr>
<%
	}else{ 
%>
	<tr><td colspan=4 align="center">It appears you have not inquired about any opportunities yet</td></tr>
<%
	}
}
%>


<ul class="NoBullet">
<% if(aszUseType==null){ %>
	<li><A style="line-height:1em;" href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></li>
	<ul class="NoBullet">
		<li><A style="line-height:1em;" href="<%=aszPortal%>/org.do?method=showOrganization&orgnid=<%=email.getEmailOrgNID()%>"><%=email.getEmailOrgName()%></A></li>
		<li>Date Contacted: <%out.print(email.getEmailCreateDt());%></li>
		<li>Email: <%=aszOrgEmail%></li>
	</ul>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement")){ %>      
	<li><A style="line-height:1em;" href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></li>
	<ul class="NoBullet">
		<li><A style="line-height:1em;" href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></li>
		<li>Date Contacted: <%out.print(email.getEmailCreateDt());%></li>
		<li>Volunteer's Email Address: <%=aszVolEmail%></li>
		<li>Volunteer's Phone Number: <%=aszVolPhone%></li>
	</ul>
<% }else if(aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
	<li><A style="line-height:1em;" href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></li>
	<ul class="NoBullet">
		<li><A style="line-height:1em;" href="<%=aszPortal%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></li>
		<li>Date Contacted: <%out.print(email.getEmailCreateDt());%></li>
		<li>Volunteer's Email Address: <%=aszVolEmail%></li>
		<li>Volunteer's Phone Number: <%=aszVolPhone%></li>
	</ul>
<% } %>
</ul>
<hr>
    </logic:iterate>
     
 <div style="clear:both;"></div>
    
     
     
          
</div>
</div>
         


</div>

   
    </div>
</div>

<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->

<% if(iTmp > 0){ %>
<% if(aszUseType==null){ %>
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
<% }else{ %>
<!-- Google Code for ChristianVolunteering.org Registered Organization Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1002898796;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "y02aCMTdiQMQ7Iqc3gM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1002898796/?value=0&amp;label=y02aCMTdiQMQ7Iqc3gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
<% } %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
