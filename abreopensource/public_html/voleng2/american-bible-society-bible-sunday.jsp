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
	  <span id="title">American Bible Society - Bible Sunday</span>
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
<span style="float: left;">American Bible Society - Bible Sunday</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=request.getContextPath()%>/register.do?method=showHome">home</a></div> 
</div>
<% } %>
    
		<div id="body">	  

<!-- /#content-header -->
<div id="content-area">
  <!-- CONTENT AREA -->
  <div id="node-151834">
    <div>
      <div> </div>
      <div>
        <p><img title="Bible Sunday Image" alt="Bible Sunday Image" src="http://www.christianvolunteering.org/imgs/abs3.gif" width="144" align="left" border="0" height="207" hspace="5" /></p>
        <h3>BIBLE SUNDAY</h3>
        <p>We are revitalizing and reenergizing one of the oldest ABS  ministries -- Bible Sunday -- and we need your volunteer help in this  crucial effort to help churches re-engage with the Word of God! For  more information on Bible Sunday visit <a href="http://biblesunday.americanbible.org/">BibleSunday.Americanbible.org</a>.</p>
        <p>We are looking for key volunteers to recruit new churches for this  exciting ministry and to work with their local churches as a Bible  Sunday host.</p>
        <p>One of our main goals is to supplement and enhance what is being  done in local churches by coming alongside the church and providing  Bible engagement tools as it disciples its members. The role of local  congregation host, or captain, is crucial but also fun and gratifying  at the same time. And we would give you the training and tools you need  to help your church renew its passion for the Word of God. Among the  tasks we are calling on these captains to perform are:</p>
        <p>Become the point of contact at your church as we seek to engage  people with the Word. You would be the person to whom we would mail  materials, and you would be responsible for putting up the posters,  distributing the bulletin inserts and other materials and answering  questions from members of your church.</p>
        <p>Recruit at least 10 of your fellow church members to sign up to  commit to read the Bible at least 4 days a week and make them part of  the outreach team.</p>
        <p>Build a list of like-minded Bible Advocates/Activists. We are adding  to our list of those interested in receiving regular email updates  about the ABS&prime;s scripture engagement efforts.</p>
        <p>Organize local Bible study or reading groups to serve as a friendly  and creative way to reach out to other members of your church, family  or neighborhood and promote reading of the Bible. We believe in letting  the Bible speak for itself -- it will &quot;not return void.&quot; It is  life-changing for those who read it regularly, with a huge social  impact. Send a &quot;letter to the editor&quot; of your local newspaper, talking  about the research that shows Bible reading is good for you -- it makes  you healthier and more stable and less prone to destructive behaviors.</p>
        <p>And coming soon, we will unveil a Bible search &quot;widget&quot; you can add  to your website or facebook page and serve as a &quot;virtual engagement  activist&quot; using social media. This is also a way for you to let others  know about the wonderful tools ABS has available -- at no charge -- to  help people access the Scripture, things like daily email Bible  readings and podcasts.</p>
        <p>For more information on Bible Sunday visit <a href="http://biblesunday.americanbible.org/">BibleSunday.Americanbible.org</a>.</p>
		      </div>
	      </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->