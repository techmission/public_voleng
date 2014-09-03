<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Equipping & Managing Volunteers</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<!-- BEGIN MAINCONTENT -->
<div id="maincontent"><div id="pagebanner">

<span style="float: left;">Work Study</span><img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; work study</div> 
</div>

       
		<div id="body">	 
<h1>Work Study</h1>

<h2>What is work-study?</h2>			
<p>As part of their financial aid packet, college students can earn a certain amount of money by working.
Most of this money is earned through on-campus jobs, but the US Department of Education requires that 7% of all work-study funds 
go to jobs serving in community and faith-based organizations. Schools pay up to 75% of the cost of these positions with 
the nonprofit paying as little as 25%.</p>
<h2>Why hire work-study students?</h2>
<p>You get the committment paid staff member for half or quarter the money!</p>
<h2>How do I get a work-study student?</h2>
<p>1. Recruit. <br> Post a work-study job description on ChristianVolunteering. To do this, post a position description the same way you would
for a volunteer position. Towards the bottom of the page, you are given the choice of marking the position local volunteering, virtual volunteering, 
short term missions, or work-study. Mark it work-study.</p>
<p>2. Have the student find out (from the financial aid office) if they definitely qualify for work study.</p>
<p>3. Interview candidate.</p>
<p>4. Get pay rate from college.<br> 
The school will pay a percentage of the student’s pay (usually between 50-75%) and the 
nonprofit will be required to pay the rest. The school 
will also want to approve the pay rate, although the nonprofit has some flexibility with what they want to  pay.</p>
<p>5. Fill out required paperwork to register with school; its different for each school. </p>
<p>6. Fill out paperwork for position. Both the nonprofit and the student will need to fill this out. See <a href="<%=aszPortal%>/tmctraining.jsp">toolkit</a> (towards bottom of page) for sample forms.</p>
<p>7. You will need to make sure that the student fills out timesheets. She will sign them and fax them to the college.</p>
<p>8. The school will pay the student and bill the nonprofit for the portion of the student’s wages. </p>
<h2>What if we're a faith-based organization?</h2>
<p>Work-study students can work at faith-based organizations. The student can not be paid to engage in faith activities 
(leading prayer, teaching Bible, etc.).
Work-study students can not be hired based on faith. However, you may target your recruiting to faith-based groups.</p>

</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->