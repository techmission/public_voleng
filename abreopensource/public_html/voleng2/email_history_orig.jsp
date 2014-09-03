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
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
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
<span style="float: left;">Welcome, <%=aCurrentUserObj.getUSPNameFirst()%>&nbsp;<%=aCurrentUserObj.getUSPNameLast()%></span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/register.do?method=showHome">home</a> &gt; <a href="<%=request.getContextPath()%>/volunteerdashboard.jsp">account home</a></div>
</div>
<% } %>

<!-- personality type is: <%=aCurrentUserObj.getUSPPersonality()%> <%=aCurrentUserObj.getUSPPersonalityTID()%>-->
 <div id="body">
 
 
<br><br>
<div>
	<div style="float: left; padding-left: 5px; text-align: center;">

      <div class="box">	
		 <h2 align="center">Manage Emails</h2>
<table border=0>
<% if(aszUseType==null){ %>
	<tr><th>Organization</th><th>Organization Email</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement")){ %>      
	<tr><th>Volunteer Name</th><th>Volunteer Email</th><th>Phone</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else if(aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
	<tr><th>Volunteer Name</th><th>Volunteer Email</th><th>Phone</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% }else{ %>      
	<tr><th>Organization</th><th>Organization Email</th><th>Opportunity</th><th>Date of Inquiry</th></tr>
<% } %>
	<logic:iterate id="email" name="emaillist" type="com.abrecorp.opensource.dataobj.EmailInfoDTO">
<%
iTmp = email.getEmailId();
aszVolPhone=email.getEmailVolPh();
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

<tr>
<% if(aszUseType==null){ %>
	<td><A style="line-height:1em;" href="<%=request.getContextPath()%>/org.do?method=showOrganization&orgnid=<%=email.getEmailOrgNID()%>"><%=email.getEmailOrgName()%></A></td>
	<td><%=aszOrgEmail%></td>
	<td><A style="line-height:1em;" href="<%=request.getContextPath()%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></td>
<% }else if(aszUseType.equalsIgnoreCase("orgmanagement")){ %>      
	<td><A style="line-height:1em;" href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></td>
	<td><%=aszVolEmail%></td>
	<td><%=aszVolPhone%></td>
	<td><A style="line-height:1em;" href="<%=request.getContextPath()%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></td>
<% }else if(aszUseType.equalsIgnoreCase("oppmanagement")){ %>      
	<td><A style="line-height:1em;" href="http://www.urbanministry.org/user/<%=email.getEmailVolUID()%>"><%=email.getEmailVolFN()%> <%=email.getEmailVolLN()%></a></td>
	<td><%=aszVolEmail%></td>
	<td><%=aszVolPhone%></td>
	<td><A style="line-height:1em;" href="<%=request.getContextPath()%>/org.do?method=showOpportunity&orgnid=<%=email.getEmailOrgNID()%>&oppnid=<%=email.getEmailOppNID()%>"><%=email.getEmailOppName()%></A></td>
<% } %>
	<td><%out.print(email.getEmailCreateDt());%></td>
</tr>
    </logic:iterate>
		
</table>      

    
 <div style="clear:both;"></div>
    
     
     
          
</div>
</div>
         


</div>

   
    </div>
</div>

<% if(!(
	aszHostID.equalsIgnoreCase("volengivol") ||
	aszHostID.equalsIgnoreCase("volengchurch") ||
	aszHostID.equalsIgnoreCase("volenggospelcom") ||
	aszHostID.equalsIgnoreCase("volenggospel") ||
	aszHostID.equalsIgnoreCase("volengcatholic")
)){ %>
<%@ include file="/template_include/gigya_socialize.inc" %>
<% } %>
<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
