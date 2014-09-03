<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<style type="text/css">
li{
	padding: 3px;
}
</style>

</head>

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Register to Recruit Volunteers</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Register to Recruit Volunteers</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="sidebarless">
&nbsp;
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<div id="body">
	<h1>Administrative links to manage City Vision Internship portal</h1>
    <ol>
	    <li>
	    	<!--a href="<%=request.getContextPath() %>/cityvision/internlistings.jsp" target="_blank"-->
	    	<a href="<%=request.getContextPath() %>/cityvision/oppsrch.do?method=processSearch&cvintern=true&fq=content_type:resume" target="_blank">
	    		Applicant List for Site Directors
	    	</a>
	    </li>
	    <li>
	    	<a href="<%=request.getContextPath() %>/cityvision/associationmanagement.jsp" target="_blank">
	    		Active Sites
	    	</a>
	    </li>
	    
	    <li>
	    	<a href="http://www.urbanministry.org/admin/review/cvc-orgs" target="_blank">
	    		Approve a New City Vision Internship Site
	    	</a>
	    </li>
	    
	    <li>
	    	<a href="http://www.urbanministry.org/admin/review/cvcintern-unscreened" target="_blank">
	    		Screen Intern Applicants
	    	</a>
	    </li>
	    <li>
	    	<a href="http://www.urbanministry.org/admin/review/cvcintern-screened" target="_blank">
	    		Approved Intern Applicants
	    	</a>
	    </li>
	    <li>
	    	<a href="http://www.urbanministry.org/admin/review/cvcintern-placed" target="_blank">
	    		Active Interns
	    	</a>
	    </li>
	    <li>
	    	<a href="http://www.urbanministry.org/admin/review/cvcintern-leads" target="_blank">
	    		Partial Leads
	    	</a>
	    </li>
	    
	    <li>
	    	To update an existing Organization on our system to be a City Vision Internship Site:
	    	<ol>
	    		<li>
	    			Log in to UrbanMinistry.org as a TechMission Administrator and go to the Organization profile in UrbanMinistry.org
	    			<ul>
	    				<li>
	    					You can do this by either going to www.urbanministry.org/node/[org_nid]/edit, if you already have the org_nid
	    				</li>
	    				<li>
	    					Or take the URL of the Org Profile in ChristianVolunteering.org, replace UrbanMinistry.org as the domain instead, remove the "/cityvision", if it exists, and remove the ".jsp" at the end of the URL. Then proceed to click "Edit"
	    				</li>
	    			</ul>
	    		</li>
	    		<li>
	    			Select "City Vision" as the value for "City Vision Site Approved" (under Organization Name on the edit from in UrbanMinistry.org)
	    		</li>
	    		<li>
	    			Choose "City Vision Internship" as an Organizational Affiliation (if this has not already been done)
	    		</li>
	    	</ol>
	    </li>
	    <li>
	    	To edit an existing opportunity (belonging to an approved City Vision Internship Site) & change it to a City Vision Internship:
	    	<ol>
	    		<li>
	    			Log in to ChristianVolunteering.org as a City Vision Internship Administrator and go to 
	    			<a href="<%=request.getContextPath() %>/cityvision/associationmanagement.jsp" target="_blank">Association Management</a>
	    		</li>
	    		<li>
	    			Choose the Site/Organization from the list, and click "Manage Opportunities"
	    		</li>
	    		<li>
	    			Find the Opportunity you'd like to change, and click "Edit Opportunity"
	    		</li>
	    		<li>
	    			Edit the Opportunity and click the checkbox to mark it as a "City Vision Internship"
	    		</li>
	    	</ol>
	    </li>
	    <li>
	    	To manage automated Follow-Up email system, go to <a target="_blank" href="http://www.urbanministry.org/admin/review/cvintern_applicant_emails ">Manage Emails</a>
	    </li>
	    <li>
	    	To test/view the application forms, use the following links:
	    	<ol>
			    <li>
			    	<a href="<%=request.getContextPath() %>/cityvision/email.do?method=showCreateApplication" target="_blank">
			    		Create/Update Application Form - Page 1
			    	</a>
			    </li>
			    <li>
			    	<a href="<%=request.getContextPath() %>/cityvision/email.do?method=showCreateApplication2" target="_blank">
			    		Create/Update Application Form - Page 2
			    	</a>
			    </li>
			    <li>
			    	<a href="<%=request.getContextPath() %>/cityvision/email.do?method=showCreateApplication3" target="_blank">
			    		Create/Update Application Form - Page 3
			    	</a>
			    </li>
	    	
	    	</ol>
	    </li>
    </ol>
</div>

<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>


<!-- start sidebar information -->
<% //@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
