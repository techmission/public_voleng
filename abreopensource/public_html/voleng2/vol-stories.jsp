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
	  <span id="title">stories </span>
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
<span style="float: left;">stories </span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	stories  </div>
</div>
<% } %>

 <div id="body" align="left">
        <img src="http://www.christianvolunteering.org/techmimg/blank.gif" width="415" height="10"><BR>

           <h2>Mayowa's Story</h2><p>
			Mayowa Ogunbayo, a student getting his MS in Telecommunications and Systems Management 
			at Northeastern University, first contacted TechMission in August 
			2005. He was placed as a volunteer at The Boston Project Ministries 
			in Dorchester, MA and has been serving ever since. Here is what he 
			has to say about his experience.</p>
    <IMG alt="Volunteer Group" src="http://www.christianvolunteering.org/imgs/pic/threeyouth600.jpg" align= "absmiddle" width="214" height="175">
        <p>&quot;I actually started out seeking a place where my skill set as an 
				experienced computer techie could be useful, whilst getting an 
				environment that most suits my disposition. This is one time in 
				my life I could say I got all I asked for and more.</p>
				<p>
				From the first interview call through my being accepted at Boston Project 
				Ministries, I was treated like I belong. That was the winner for 
				me. My experience with the kids at the home work center [and 
				with the technical and handyman jobs] has been the most fun I 
				have had in my life. The smiles of gratitude [from the kids] for 
				the simplest thing done is highly rewarding (their playful 
				pranks notwithstanding).</p>
				<p>
				You just can&rsquo;t help but love them all.</p>
				<p>
				&nbsp;I&rsquo;m really proud to be part of this inspired group of people. I can only 
				shout out .....GO TECHMISSION&nbsp; GO!!!&quot;</p>
				<p>
				&nbsp;Mayowa Ogunbayo</p>
				<h2>More stories to come ....</h2>
		
		<h2><a href="<%=aszPortal%>/register.do?method=showIndReg1">Click Here to register as a volunteer! </a></h2>

</div>

     </div>
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->