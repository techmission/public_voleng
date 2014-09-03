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
<span style="float: left;">Sources</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	
</div>
<% } %>
    
		<div id="body">	  
<ul>
	<li>Questions and materials licensed from Paragon Educational Consulting </li>
	<li>Myers-Briggs type Descriptions from Wikipedia and Paragon Educational Consulting</li>
	<li>MBTI&#174; and MYERS-BRIGGS TYPE INDICATOR&#174; are registered trademarks and MYERS-BRIGGS&#8482; is a trademark of Consulting Psychologists Press, Inc., the publisher of the MBTI instrument. TechMission is not affiliated with and is not a licensee of Consulting Psychologists Press, Inc.</li>
	
</ul>

<div id="references">	
	<h3>References</h3>
		<ul>
		<li>Barron, B., & Tieger, P. D. (2007). <a target="_blank" href= "http://www.urbanministry.org/books/do-what-discover-perfect-career-through-secrets-personality-type">Do What You Are: Discover the Perfect Career for You Through the Secrets of Personality Type (4 Rev Upd ed.).</a> London: Little, Brown and Company.</li>

		<li>Harbaugh, G. L. (1990). <a target="_blank" href="http://www.urbanministry.org/books/gods-gifted-people">God&prime;s Gifted People (Expanded ed.).</a> Minneapolis: Augsburg Fortress Publishers.</li>

		<li>Michael, C. P., & Norrisey, M. C. (1991). <a target="_blank" href="http://www.urbanministry.org/books/prayer-and-temperament-different-prayer-forms-different-personality-types">Prayer and Temperament: Different Prayer Forms for Different Personality Types (Revised ed.).</a> Charlottesville: Open Door.</li>

		<li>The Personality Page. (n.d.). The Personality Page. Retrieved January 20, 2010, from <a target="_blank" href="http://www.personalitypage.com/">www.personalitypage.com</a></li>

		<li>site, a. t. (n.d.). MyPersonality.info - Personality Types and Multiple Intelligences Tests & Information. MyPersonality.info - Personality Types and Multiple Intelligences Tests & Information. Retrieved March 12, 2010, from <a target="_blank" href="http://www.mypersonality.info/">http://www.mypersonality.info</a></li>
</ul>
</div>	
    
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->