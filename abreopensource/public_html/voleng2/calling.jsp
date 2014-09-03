<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Christian Calling and Vocation</title>

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
	  <span id="title">Calling</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Calling</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
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
<span style="float: left;">Calling</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; <a href="<%=aszPortal%>/training.jsp">training</a> &gt; calling </div> 
</div>
<% } %>
    
		<div id="body">	  
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>

<p><h2>What is your calling?</h2></p>

<p><a href="#articles"><h3>Articles</h3></a></p><br>
<p><a href="#presentations"><h3>Presentations</h3></a></p>
<br>
<p><h3><a name="articles">Articles</a></h3></p>
<br>
<p><a href="http://www.christianitytoday.com/bcl/areas/stewardship/articles/033005.html">Are You a Good Steward of Your Time?</a></p>
<p><a href="http://www.stempublishing.com/authors/hole/Art/Christian%20Calling.html">What is the Christian Calling?</a></p>
<p><a href="http://www.decapolis.com/faith_/pages/WhatisMyCalling.shtml">What is My Calling?</a></p>
<p><a href="http://www.boundless.org/1999/features/a0000019.html">Career vs. calling</a></p>
<p><a href="http://www.boundless.org/1999/features/A0000020.html">Discovering Your Calling</a></p>
<p><a href="http://www.americancatholic.org/Newsletters/CU/ac0801.asp">Vocation: How is God Calling Me? A Catholic Viewpoint</a></p>
<p><a href="http://www.christianvocations.org/online/cv.nsf/FurtherInfoSection?openform&cat=Articles&">Information on Short-Term Missions</a></p>


<p><h3><a name="presentations">Presentations</a></h3></p>
<table>

<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="555" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="170"><a href="http://www.christianvolunteering.org/files/calling.ppt">
	<img src="http://www.christianvolunteering.org/imgs/pic/calling.jpg" border="0"></a></td>
    <td height="152" width="288"><strong><em>"Recalling the Calling" by Telford Work, Westmont College</em></strong>
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="555" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="170"><a href="http://www.christianvolunteering.org/files/calling-and-vocation.ppt">
	<img src="http://www.christianvolunteering.org/imgs/pic/student_fellowship.jpg" border="0"></a></td>
    <td height="152" width="288"><strong><em>"Calling and Vocation" by Christian Though Student Fellowship</em></strong>
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="555" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="170"><a href="http://www.timshen.truepath.com/bigpic/start.htm">
	<img src="http://www.christianvolunteering.org/imgs/pic/big.jpg" border="0"></a></td>
    <td height="152" width="288"><strong><em>"The Big Picture: Discerning the Will of God" by Tim Gibson</em></strong>
      		</td>
  		</tr>
<tr>
    <td colspan="2" height="43">
	<img src="http://www.christianvolunteering.org/imgs/main_dividerline.gif" width="555" height="3" vspace="20" border="0"></td>
  		</tr>
	<tr>
    <td width="170"><a href="http://www.christianvolunteering.org/files/LifeWorthyPresentation.ppt">
	<img src="http://www.christianvolunteering.org/imgs/pic/worthy.jpg" border="0"></a></td>
    <td height="152" width="288"><strong><em>"Leading a Life Worthy of Our Calling" from the 2006 Northwest Texas Pastor's School & Retreat</em></strong>
      		</td>
  		</tr>

</table>	
	
	
<% } %>

	
</div>
</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
