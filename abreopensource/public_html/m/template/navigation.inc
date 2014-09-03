<!-- start file keem_navigation.inc -->

<!--
<div id="navigation" class="home">
<div id="navigation" class="home">
<img src="<%=request.getContextPath()%>/imgs/nav_spacer.gif" width="11" height="36" /><a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/imgs/nav_home.gif" alt="home" name="home" border="0" id="home" onmouseover="MM_swapImage('home','','<%=request.getContextPath()%>/imgs/nav_home_on.gif',1)" onmouseout="MM_swapImgRestore()" width="66" height="36" /></a><a href="<%=request.getContextPath()%>/oppsrch.do?method=showSearch"><img src="<%=request.getContextPath()%>/imgs/nav_search.gif" alt="search" name="searchimg" width="64" height="36" border="0" id="searchimg" onmouseover="MM_swapImage('searchimg','','<%=request.getContextPath()%>/imgs/nav_search_on.gif',1)" onmouseout="MM_swapImgRestore()" /></a>
<a href="<%=request.getContextPath()%>/register.do?method=showIndReg1" onMouseover="hideAll(); showlayer('volunteermenu'); stopTime();" onMouseout="startTime();"><img src="<%=request.getContextPath()%>/imgs/nav_volunteer.gif" alt="volunteer" name="volunteer" width="90" height="36" border="0" id="volunteer" onmouseover="MM_swapImage('volunteer','','<%=request.getContextPath()%>/imgs/nav_volunteer_on.gif',1)" onmouseout="MM_swapImgRestore()" /></a>
<a href="<%=request.getContextPath()%>/org.do?method=showOrgStart" onMouseover="hideAll(); showlayer('nonprofitsmenu'); stopTime();" onMouseout="startTime();"><img src="<%=request.getContextPath()%>/imgs/nav_nonprofits.gif" alt="nonprofits" name="nonprofit" width="112" height="36" border="0" id="nonprofit" onmouseover="MM_swapImage('nonprofit','','<%=request.getContextPath()%>/imgs/nav_nonprofits_on.gif',1)" onmouseout="MM_swapImgRestore()" /></a>
<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" onMouseover="hideAll();"><img src="<%=request.getContextPath()%>/imgs/nav_myaccount.gif" alt="my account" name="myaccount" width="102" height="36" border="0" id="myaccount" onmouseover="MM_swapImage('myaccount','','<%=request.getContextPath()%>/imgs/nav_myaccount_on.gif',1);" onmouseout="MM_swapImgRestore();" /></a>
<a href="http://www.techmission.org/webcasts/volunteer/Volunteer_Management_files/Default.htm" onMouseover="hideAll(); showlayer('trainingmenu'); stopTime();" onMouseout="startTime();"><img src="<%=request.getContextPath()%>/imgs/nav_training.gif" alt="training" name="training" width="77" height="36" border="0" id="training" onmouseover="MM_swapImage('training','','<%=request.getContextPath()%>/imgs/nav_training_on.gif',1);" onmouseout="MM_swapImgRestore();" /></a>
<a href="<%=request.getContextPath()%>/register.do?method=showAboutCV" onmouseover="hideAll(); showlayer('aboutmenu'); stopTime();" onmouseout="startTime();"><img src="<%=request.getContextPath()%>/imgs/nav_about.gif" alt="about" name="about" width="63" height="36" border="0" id="about" onmouseover="MM_swapImage('about','','<%=request.getContextPath()%>/imgs/nav_about_on.gif',1);" onmouseout="MM_swapImgRestore();" /></a>
-->
<!-- Begin Menus -->
<!--
<div id="volunteermenu" style="position:absolute; left:141px; top:35px; height:162px; z-index:1; visibility:hidden;"> 
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('volunteermenu'); stopTime();MM_swapImage('volunteer','','/imgs/nav_volunteer_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/register.do?method=showlogin">Login</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/register.do?method=showIndReg1">Create Account </a></td>

    </tr>
      <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/register.do?method=showIndEditProfile1">Edit Account </a></td>
    </tr>
       <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">Manage Account </a></td>
    </tr>
   </table>
</div>
<div id="nonprofitsmenu" style="position:absolute; left:231px; top:35px; height:162px; z-index:1; visibility:hidden;"> 
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('nonprofitsmenu'); stopTime();MM_swapImage('nonprofit','','/imgs/nav_nonprofits_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/register.do?method=showlogin">Login</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/org.do?method=showOrgStart">Getting Started</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/org.do?method=showCreateOrgStep1">Create Account</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">Manage Account</a></td>
    </tr>
  </table>
</div>
<div id="trainingmenu" style="position:absolute; left:445px; top:35px; height:162px; z-index:1; visibility:hidden;"> 
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('trainingmenu'); stopTime();MM_swapImage('training','','<%=request.getContextPath()%>/imgs/nav_training_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">
    <tr> 
      <td><a href="http://www.techmission.org/training/book_recommend.php">Book Recommendations</a></td>
    </tr>
    <tr> 
      <td><a href="http://www.techmission.org/training/conference_materials.php">Technology Conference Materials</a></td>
    </tr>
      <tr> 
      <td><a href="http://www.techmission.org/webcasts/volunteer/Volunteer_Management_files/Default.htm">Volunteer Management Training </a></td>
    </tr>
       <tr> 
      <td><a href="http://www.techmission.org/training/urban_ministry.php">Urban Ministry </a></td>
    </tr>
          <tr> 
      <td><a href="http://www.techmission.org/training/videos.php">Videos</a></td>
    </tr>
      <tr> 
      <td><a href="http://www.techmission.org/training/webcasts.php">Webcasts</a></td>
    </tr>
       <tr> 
      <td><a href="http://www.safefamilies.org/presentations.php">Online Safety </a></td>
    </tr>
  </table>
	</div>
<div id="aboutmenu" style="position:absolute; left:522px; top:35px; height:162px; z-index:1; visibility:hidden;">
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('aboutmenu'); stopTime();MM_swapImage('about','','<%=request.getContextPath()%>/imgs/nav_about_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore();">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/register.do?method=showAboutCV">About ChristianVolunteering.org</a></td>
    </tr>
    <tr> 
      <td><a href="http://www.techmission.org/about/about.php">About TechMission</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/FAQs.jsp">Frequently Asked Questions</a></td>
    </tr>
        <tr> 
      <td bgcolor="white"><a href="http://www.techmission.org/about/staff.php">Staff </a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="http://www.techmission.org/about/board.php">Board</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="http://www.techmission.org/about/contact.php">Contact Us</a></td>
    </tr>
  </table>
</div>
-->
<!-- End Menus -->
<!--
</div>
-->


<!-- end file keem_navigation.inc -->
