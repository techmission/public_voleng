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
	  <span id="title">American Bible Society - Executive Volunteers</span>
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
<span style="float: left;">American Bible Society - Executive Volunteers</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/register.do?method=showHome">home</a></div> 
</div>
<% } %>
    
		<div id="body">	  

    <!-- /#content-header -->
    <div id="content-area">
      <!-- CONTENT AREA -->
      <div id="node-151871">
        <div>
          <div> </div>
          <div>
            <p><img src="http://www.christianvolunteering.org/imgs/abs4.gif" alt="Traveling Engagement Center Image" title="Traveling Engagement Center Image" width="144" align="left" border="0" height="207" hspace="5"></p>
            <h3>EXECUTIVE VOLUNTEERS</h3>
            <p>Have you achieved success in your life only to find that something  is missing? If so, you may be ready to begin the most exciting journey  of your life -- the journey from success to significance.</p>
            <p>Two-thirds of Christian industry leaders say they are not actively  integrating faith with their vocation. The American Bible Society will  launch a series of initiatives to gather these leaders together for  fellowship, encouragement and engagement in exercising their faith and  influence in every sector of society.</p>
            <p>In collaboration with Half-time (HT) Ventures, American Bible Society will host a workshop on February 23 -- 24 in New York City.</p>
            <p>The HT Venture workshop will help you and other high-capacity  Christians discover creative ways to channel your talents toward  Kingdom-significance. The tools, exercises, collaborative work and peer  feedback will give you clarity as you plan your "second half" of life.</p>
            <p>The HT Venture, an invitation-only event, will provide an  opportunity to explore God
            &prime;s plan for your time, talent and treasure in  order to walk away with an initial &quot;Life II&quot; plan.</p>
            <p>If you are looking for a way to contribute your expertise toward  fulfilling the Great Commission, visit <a href="http://volunteer.americanbible.org">volunteer.americanbible.org</a> and let us know. We&prime;ll be looking to hear from you.</p>
            <p>Or, if you&prime;re interested in these specific opportunities, please just click on the link below.</p>
            <p><a href="http://abs.christianvolunteering.org/org/opp151708.jsp">Exec. Strategy Advisor</a></p>
            <p><a href="http://abs.christianvolunteering.org/org/opp151711.jsp">Marketing Advisor</a></p>
            <p><a href="http://abs.christianvolunteering.org/org/opp180360.jsp">Finance Advisor</a></p>
            <p><a href="http://abs.christianvolunteering.org/org/opp180365.jsp">Film and Media Advisor</a></p>
            <p><a href="http://abs.christianvolunteering.org/org/opp180362.jsp">Publishing Advisor</a></p>
            <p><a href="http://abs.christianvolunteering.org/org/opp180361.jsp">Operations Advisor</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->