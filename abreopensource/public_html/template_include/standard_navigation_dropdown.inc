<!-- Begin Menus -->

<div id="volunteermenu" style="position:absolute; left:141px; top:35px; height:162px; z-index:1; visibility:hidden;"> 
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('volunteermenu'); stopTime();MM_swapImage('volunteer','','http://www.christianvolunteering.org/imgs/nav_volunteer_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/login.jsp" onMouseOver="hidelayer('regionalmenu')">Login</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/individualregistration.jsp" onMouseOver="hidelayer('regionalmenu')">Create Account </a></td>
    </tr>
      <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/register.do?method=showIndEditProfile1" onMouseOver="hidelayer('regionalmenu')">Edit Account </a></td>
    </tr>
       <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" onMouseOver="hidelayer('regionalmenu')">Manage Account </a></td>
    </tr>
       <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/virtualvolunteer.jsp" onMouseOver="hidelayer('regionalmenu')">Virtual Volunteers</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/shorttermmissions.jsp" onMouseOver="hidelayer('regionalmenu')">Short-term Urban Missions</a></td>
    </tr>

<% if(
	(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
	(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
	(aszHostID.equalsIgnoreCase( "volengindy" )) || 
	(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
	(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewengland" )) || 
	(aszHostID.equalsIgnoreCase( "volengboston" )) ||
	(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
	(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
	(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
	){ %>
    <tr> 
      <td bgcolor="white"><a href="http://www.christianvolunteering.org" >National Volunteers</a></td>
    </tr>
	
<% } else { %>
    <tr> 
      <td bgcolor="white">
      
      <a href="http://www.christianvolunteering.org/"  onMouseover="hideAll(); showlayer('regionalmenu'); stopTime();" onMouseout="hidelayer('regionalmenu');startTime();">Regional Volunteers</a>
      
      
      
<div id="regionalmenu" style="position:absolute; left:172px; top:105px; height:35px; width:200px; z-index:0; visibility:hidden;"> 
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('regionalmenu'); stopTime();MM_swapImage('volunteer','','http://www.christianvolunteering.org/imgs/nav_volunteer_on.gif',1);" onMouseOut="hideall();startTime();MM_swapImgRestore()">
    <tr>
      <td bgcolor="white">
      <a href="http://chicago.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Chicago Volunteers</a>
      </td>
    </tr>
    <tr>
      <td bgcolor="white">
      <a href="http://denver.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Denver Volunteers</a>
      </td>
    </tr>
    <tr> 
      <td bgcolor="white">
      <a href="http://indy.christianvolunteering.org/" onmouseover="showlayer('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Indianapolis Volunteers</a>
      </td>
    </tr>
    <tr>
      <td bgcolor="white">
      <a href="http://losangeles.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Los Angeles Volunteers</a>
      </td>
    </tr>
    <tr>
      <td bgcolor="white">
      <a href="http://miami.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Miami Volunteers</a>
      </td>
     </tr>
    <tr> 
      <td bgcolor="white">
      <a href="http://newengland.christianvolunteering.org/" onmouseover="showlayer('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">New England Volunteers</a>
    </tr>
     <tr>
      <td bgcolor="white">
      <a href="http://newyork.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">New York Volunteers</a>
      </td>
     </tr>
     <tr>
      <td bgcolor="white">
      <a href="http://seattle.christianvolunteering.org/" onmouseover="showlayer ('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Seattle Volunteers</a>
      </td>
     </tr>
    <tr> 
      <td bgcolor="white">
      <a href="http://twincities.christianvolunteering.org/" onmouseover="showlayer('regionalmenu'); stopTime();" onmouseout="hidelayer('regionalmenu'); startTime();">Twin Cities Volunteers</a>
      </td>
    </tr>
  
  </table>
</div>
      
      
      </td>
    </tr>
 <% } %>       

  </table>
</div>

<div id="nonprofitsmenu" style="position:absolute; left:231px; top:35px; height:162px; z-index:1; visibility:hidden;"> 

  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('nonprofitsmenu'); stopTime();MM_swapImage('nonprofit','','http://www.christianvolunteering.org/imgs/nav_nonprofits_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/login.jsp">Login</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/recruitvolunteers.jsp">Getting Started</a></td>
    </tr>
    <tr>
    <td><a href="<%=request.getContextPath()%>/individualregistration.jsp">Create Individual Account</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/org.do?method=showCreateOrgStep1">Create Organizational Account</a></td>

    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1">Manage Account</a></td>
    </tr>
	 <tr> 
      <td><a href="http://www.urbanministry.org/member-benefits">Premium Member Benefits</a></td>
    </tr>
	
  </table>
</div>

<div id="trainingmenu" style="position:absolute; left:445px; top:35px; height:162px; z-index:2; visibility:hidden;"> 

  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('trainingmenu'); stopTime();MM_swapImage('training','','http://www.christianvolunteering.org/imgs/nav_training_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore()">

<!-- start specific cityserve links with training -->
<% if(
		(aszHostID.equalsIgnoreCase( "volengboston" )) ||
		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		
) { %>

    <tr> 
      <td><a href="<%=request.getContextPath()%>/volunteerstandards.jsp">Volunteer Standards of Excellence </a></td>
    </tr>
        <tr> 
      <td><a href="<%=request.getContextPath()%>/organizationstandards.jsp">Organization Standards of Excellence </a></td>
    </tr>
     <tr> 
      <td><a href="<%=request.getContextPath()%>/leaderstandards.jsp">Volunteer Leaders Standards of Excellence </a></td>
    </tr>

<% } else { %>

    <tr> 
      <td><a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions">Volunteer Management Wiki</a></td>
    </tr>
     <tr> 
      <td><a href="http://www.urbanministry.org/wiki/webcast-workshops-volunteers">Volunteer Training</a></td>
    </tr>    
     <tr> 
      <td><a href="http://www.urbanministry.org/wiki/volunteer-management-presentation-webcasts">Volunteer Management Training</a></td>
    </tr>    
    <tr> 
      <td><a href="http://www.urbanministry.org/taxonomy/term/92,120">Book Recommendations</a></td>
    </tr>
    <tr> 
      <td><a href="http://www.urbanministry.org/wiki/personality-vocation-and-calling">Christian Calling and Vocation</a></td>
    </tr>
    <tr> 
      <td><a href="http://www.urbanministry.org/spiritual_gifts">Spiritual Gifts</a></td>
    </tr>            
<% } %>    
    
   <tr> 
      <td><a href="http://www.urbanministry.org">UrbanMinistry.org</a></td>
    </tr>
   
  </table>
	
	</div>

<div id="aboutmenu" style="position:absolute; left:522px; top:35px; height:162px; z-index:1; visibility:hidden;">
  <table border="0" clas="menu" cellpadding="0" cellspacing="0" id="menu" onMouseOver="hideAll(); showlayer('aboutmenu'); stopTime();MM_swapImage('about','','http://www.christianvolunteering.org/imgs/nav_about_on.gif',1);" onMouseOut="startTime();MM_swapImgRestore();">
    <tr> 
      <td><a href="<%=request.getContextPath()%>/about.jsp">About ChristianVolunteering.org</a></td>
    </tr>
    <tr> 
      <td><a href="http://www.urbanministry.org/about-techmission-inc">About TechMission</a></td>
    </tr>
    <tr> 
      <td><a href="<%=request.getContextPath()%>/FAQs.jsp">Frequently Asked Questions</a></td>
    </tr>
        <tr> 
      <td bgcolor="white"><a href="http://www.urbanministry.org/staff">Staff</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="http://www.urbanministry.org/board">Board</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="<%=request.getContextPath()%>/contact.jsp">Contact Us</a></td>
    </tr>
    
<!-- start specific cityserve links with about -->
<% if(
		(aszHostID.equalsIgnoreCase( "volengboston" )) ||
		(aszHostID.equalsIgnoreCase( "volengcityserve" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" ))		
) { %>
    <tr> 
      <td bgcolor="white"><a href="http://aes-egc.org/cityserve/">About CityServe</a></td>
    </tr>
    <tr> 
      <td bgcolor="white"><a href="http://www.visionnewengland.com">About Vision New England</a></td>
    </tr>    
<% } %>
    
  </table>
</div>
