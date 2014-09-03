<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Devotionals for Volunteers and Volunteer Managers</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Devotionals</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>  
  <div id="oppsearch">
	  <span id="title">Devotionals</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>



<div id="result">

<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>


<% if( b_includeLeftSidebar==true ){ %>
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
<span style="float: left;">Devotionals</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; <a href="<%=aszPortal%>/training.jsp">training</a> &gt; devotionals </div> 
</div>
<% } %>
    
		<div id="body">	  

<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>

<p><h3>For Volunteers</h3></p>
<br>
<p><ul><li><a href="http://www.christianitytoday.com/men/features/caring.html">Just for Men</a></li>	
<li><a href="http://www.rbc.org/odb/odb-02-01-03.shtml">True Greatness (From Our Daily Bread) </a></li>	
<li><a href="http://www.rbc.org/odb/odb-03-24-04.shtml">The Feet of Judas (From Our Daily Bread) </a></li>	
<li><a href="http://www.rbc.org/odb/odb-04-18-06.shtml">Glad Service (From Our Daily Bread) </a></li>	
<li><a href="http://www.rbc.org/utmost/index.php?day=04&month=03">Is This True of Me? (From My Utmost For His Highest)</a></li>	
<li><a href="http://www.rbc.org/utmost/index.php?day=13&month=12">Intercessory Prayer (From My Utmost For His Highest)</a></li>	
<li><a href="http://www.rbc.org/utmost/index.php?day=25&month=02">The Destitution of Service (From My Utmost For His Highest)</a></li>	
<li><a href="http://www.rbc.org/utmost/index.php?day=29&month=09">The Awareness of the Call (From My Utmost For His Highest)</a></li>	
<li><a href="http://www.crosswalk.com/faith/1420712.html">Why We're Here: The Call to Serve</a></li>
<li><a href="http://www.crosswalk.com/faith/1339549.html">Use Your Spiritual Gifts</a></li>
</ul></p>	
<p><h3>Volunteer Managers</h3></p>	
<br>
<p><ul><li><a href="http://www.christianitytoday.com/bcl/areas/shepherding/articles/le-scan-030210.html">Mentoring that Makes a Difference</a></li>	
<li><a href="http://www.christianitytoday.com/bcl/areas/churchministries/articles/060403.html">The 7 Myths of Volunteerism</a></li>
<li><a href="http://www.pastors.com/RWMT/?id=240&artid=8229&expand=1">Pastors who lead the way learn to delegate</a></li>
<li><a href="http://www.pastors.com/RWMT/?id=235&artid=7864&expand=1">Focus on raising the commitment of your leadership</a></li>
<li><a href="http://www.pastors.com/RWMT/?id=234&artid=5916&expand=1">What to do about conflict</a></li>



</ul></p>	
	
<% } %>	

	
</div>
</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
