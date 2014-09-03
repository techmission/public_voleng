<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Member Benefits</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/individualregistration.jsp">create account</a> 
	&gt; <a href="<%=aszPortal%>/orgmanagement.jsp">manage organization</a> &gt; paid membership </div> 
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
<span style="float: left;">Member Benefits</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; <a href="<%=aszPortal%>/training.jsp">training</a> &gt; calling </div> 
</div>
<% } %>
    
		<div id="body">	  
<h2><A class="add-new-buttn" href="<%=aszPortal%>/paidmembership.jsp">APPLY NOW</A></h2>
<!--h1 class="rtecenter"><a href="<%=aszPortal%>/paidmembership.jsp"><u><strong>APPLY NOW</strong></u></a></h1-->
<h2>Recruit More Volunteers through Featured Volunteer Listings</h2>
<ul>
    <li>Recruit more volunteers by having your volunteer opportunities featured at the top of volunteer searches. Studies show that only 30% of web users even scroll beyond the first screen, so having your listings featured first on searches could dramatically increase your chances of getting volunteers</li>
</ul>
<h2><strong>Nonprofit Group Buying Discounts</strong></h2>
<ul>
    <li><strong>Discounted Unemployment Insurance: </strong>TechMission has partnered with an organization that provides an nonprofit cooperative as an alternative to paying state unemployment insurance cost.&nbsp; An organization with personnel expenses of $150,000 might save $5,000 in unemployment fees.</li>
</ul>
<ul>
    <li><strong>Group Buying Discounts: </strong>TechMission has a number of  	partnerships with group buying vendors, which provide discounts to hundreds of  	businesses. The average organization can reduce their administrative costs by up to  	20%. This benefit is available only to organizational TechMission members, not  	individuals.<strong> </strong>Some of the vendors and categories include:FedEx (30% discount), &nbsp;OfficeMax (5-20% discount),&nbsp;Home Depot,&nbsp;Insight and Tiger Direct for Computer and  		Equipment Purchases,&nbsp;Lighthouse Underwriters, LLC for D&amp;O Insurance,&nbsp;Mutual of America Life Insurance Company,&nbsp;Automatic Data Processing, Inc for Payroll  		Processing (15% discount),&nbsp;Covenant Eyes for Internet Accountability Software  		for $2.50/month (vs. $6.99),&nbsp;Discounts with over 100 other companies</li>
</ul>
<ul style="margin-top: 0in; margin-bottom: 0.1em;">
    <li><strong>Discounted Gifts in Kind Membership</strong>: TechMission members  	can register with Gifts in Kind International for a reduced rate (as low as $125  	vs. regular price of $250). Gifts In Kind distributes millions of dollars worth  	of newly manufactured product donations and special pricing programs to  	qualified 501(c)(3) non-profit organizations. This benefit is available only to  	organizational TechMission members, not individuals.&nbsp;<b><br />
    </b></li>
</ul>
<h2><A class="add-new-buttn" href="<%=aszPortal%>/paidmembership.jsp">APPLY NOW for only $50/year</A></h2>
<!--h1 class="rtecenter"><strong><u><a href="<%=aszPortal%>/paidmembership.jsp">APPLY NOW for only $50/year</a></u></strong></h1-->
<p>Cost is $50 per year. We accept MasterCard and VISA online and checks (through the mail) as payment.</p>
<p><strong>Have Questions? Want to learn  more about how TechMission Membership can serve you? Contact us:<br />
<br />
Email</strong><strong>: </strong> <a href="mailto:info@techmission.org" class="spamspan">info@techmission.org</a><strong>  <br />
Phone: 617-282-9798 ext. 109</strong><b><u><br />
</u></b></p>
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
