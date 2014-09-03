<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<style type="text/css">
* {//margin:0; //padding:0;}
h4{margin-bottom:5px;}
#maincontent.sidebarless{width: 100%; backround-image:none; border:none;}
#contentwrapper {background-image:none; background-color:#FFF; width:760px;}
#pagebanner {//width:950px;}
h3 {font-size:16px; color:#293C5F; padding-bottom:5px;}
div#content1 table {max-width:680px;}
table p {margin: 0 5px 2px;}
table h3 {font-size:13px; padding:0 5px 3px;}
#links {text-align:center; padding:10px 0;}
a { color:#000000; font-weight:inherit;}
</style>

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
    
    
            <a style="float: right;" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities near your city" href="<%=aszPortal%>/myministryopportunities.jsp"><img align="right" style="padding:0px 0 5px;" border="none" src="<%=aszPortal%>/imgs/ministry-oppor-button.gif"/></a>   
            
		<div id="body">	  
          <h3 align="center">CHARACTERISTICS OF EACH OF THE 16 MINISTRY TYPES</h3>
          <table border="1" cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td width="8%" valign="top"><p align="center"><strong>&nbsp;</strong></p></td>
                <td width="42%" colspan="2" valign="center" style="background:#E5E7B5;"><p align="center">
                <strong>Sensing Types</strong> </p></td>
                <td width="39%" colspan="2" valign="center" style="background:#E5E7B5;"><p align="center">
                <strong>Intuitive Types</strong></p></td>
                <td width="9%" valign="top"><p align="center"><strong>&nbsp;</strong></p></td>
              </tr>
              <tr>
                <td width="8%" rowspan="2" style="background:#E5E7B5;"><p>&nbsp;</p>
                    <p align="center"><strong>Introverts</strong></p></td>
                <td width="21%" valign="top"><h3>ISTJ 
                  (males 16%, females 7%)</h3>
<p>Quiet and sensible with a strong sense of duty. Logical, practical and great with numbers and factual information. Very well organized and always dependable. Well-prepared and takes responsibility. Values tradition and a well ordered environment. </p></td>
                <td width="20%" valign="top"><h3>ISFJ (males 8%, females 19%)</h3>
                    <p>Responsible, considerate and generally quiet. Seeks harmony in their relationships and endeavors.&nbsp;Genuine concern for others. Handles responsibilities with dilligence. Loyal, devoted and attentive to detail. Respects the feelings of those around them. </p></td>
                <td width="19%" valign="top"><h3>INFJ (males 2%, females 2%)</h3>
                    <p>Principled, value-driven and hard working. Sensitive and genuinely concerned with the common good. Deep and often mysterious. Gifted in understanding complex issues. Quiet and abstract. Clear visioned and faithful to the ministries and causes they serve. </p></td>
                <td width="20%" valign="top"><h3>INTJ (males 3%, females 1%)</h3>
                    <p>Analytical and original with the ability to be great leaders. Logical, organized and efficient planners who tend to be dedicated and reliable. Not likely to value emotional responses. Seeks relationships with rational individuals with similar theoretical viewpoints.  </p></td>
                <td width="9%" style="background:#E5E7B5;"><p align="center"><strong>Judgers</strong></p></td>
              </tr>
              <tr>
                <td width="21%" valign="top"><h3>ISTP (males 8%, females 3%)</h3>
                <p>Quiet, analytical thinkers who thrive in technical or mechanical ministries. Carries out the important actions. Always seeks functional approaches. Insight shows through humor and wit that brings light-hearted air to a situation. An implementer that is ready to act. </p>
                </td>
                <td width="20%" valign="top"><h3>ISFP (males 8%, females 10%)</h3>
                    <p>Peaceful, pleasant, creative and easygoing. Perceptive and aware of others and their feelings. Gifted listeners with strong ability to retain facts and details. Action oriented with a strong sense of values. Strong artistic ability. Works well when unrestricted. Hardworkers drawn to serving others. </p></td>
                <td width="19%" valign="top"><h3>INFP (males 4%, females 5%)</h3>
                    <p>Sociable, passionate, and insightful. Reflective and reserved, constantly processing information. Gifted in language and writing. Constantly trying to make a positive impact on the world around them. Sets high standards for their ministry work. </p></td>
                <td width="20%" valign="top"><h3>INTP (males 4%, females 2%)</h3>
                    <p>Thoughtful and analytical. Interested in how systems work. Approaches problems using logic and reason. Fascinated by ideas and principles, tremendous respect for education. Does not work well within rigid systems. Seeks out challenges. </p></td>
                <td width="9%" rowspan="2" style="background:#E5E7B5;"><p align="center"><strong>Perceivers</strong></p></td>
              </tr>
              <tr>
                <td width="8%" rowspan="2" valign="top" style="background:#E5E7B5;"><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                  <p>&nbsp;</p><p>&nbsp;</p>
                <p align="center"><strong>Extroverts</strong></p></td>
                <td width="21%" valign="top"><h3>ESTP (males 6%, females 3%)</h3>
                    <p>Action oriented doers. Lives in the moment, gifted at improvisation. Openminded and pleasant to be around. Enjoys dealing with things both tangible and practical. Excels when serving in a fast paced environment. Value oriented with a strong sense of right and wrong. </p></td>
                <td width="20%" valign="top"><h3>ESFP (males 7%, females 10%)</h3>
                    <p>Creative, outgoing, seeks enjoyment from life. Qucik to take a hands-on approach toa problem. Loves the company of others and known for generosity and tactful nature. Makes decisions based on common sense, usually disliking extensive planning and complex theories. Dislikes structures and routines. </p></td>
                <td width="19%" valign="top"><h3>ENFP (males 7%, females 10%)</h3>
                    <p>Keenly perseptive, enthusiastic, creative and bright. Anticipates the needs of others and brings charisma to a ministry. Achievers who find success from their focus and detailed approach. Strong values and principles and gifted in rallying people behind a cause. </p></td>
                <td width="20%" valign="top"><h3>ENTP (males 4%, females 2%)</h3>
                    <p>Quick, imaginative, resourceful with a wide set of skills. Never afraid to tackle large scale and often complex problems. Energetic with the ability to motivate people around them. Great at understanding how systems work and using it to their advantage. </p></td>
              </tr>
              <tr>
                <td width="21%" valign="top"><h3>ESTJ (males 11%, females 6%)</h3>
                    <p>Practical, realistic, and matter-of-fact with a natural head for business or mechanics. Mostly interested in the practical rather than the theoretical. Skilled at organizing and running activities. Responsible and realistic with natural leadership abilities.</p>
                </td>
                <td width="20%" valign="top"><h3>ESFJ (males 8%, females 16%)</h3>
                    <p>Caring, determined, and value-driven. Expresses genuine interest in the well being of others, taking pleasure in their happiness. Most comfortable in orderly environments. Trusts in the literal and concrete and values facts and experiences. Gifted in bringing out the best in people. </p></td>
                <td width="19%" valign="top"><h3>ENFJ (males 2%, females 3%)</h3>
                    <p>Perceptive, friendly, and generous. Known for their focus on others and their likeable nature. Gifted in assessing a situation and meeting its needs. Great listeners and gifted at counseling and facilitating. Enthusiastic, understanding and appreciative of others. </p></td>
                <td width="20%" valign="top"><h3>ENTJ (males 3%, females 1%)</h3>
                    <p>Strong-willed, analytical, confident and natural born leaders. Excels in areas that require strong reasoning abilities. Are fiercely independent and enjoy taking charge of a situation. Can often overwhelm others with their desire to order to world based on their vision. </p></td>
                <td width="9%" style="background:#E5E7B5;"><p align="center"><strong>Judgers</strong></p></td>
              </tr>
              <tr>
                <td width="8%" valign="top"><p align="center"><strong>&nbsp;</strong></p></td>
                <td width="21%" valign="top" style="background:#E5E7B5;"><p align="center">
                <strong>Thinkers</strong></p></td>
                <td width="40%" colspan="2" valign="top" style="background:#E5E7B5;"><p align="center">
                <strong>Feelers</strong></p></td>
                <td width="20%" valign="top" style="background:#E5E7B5;"><p align="center">
                <strong>Thinkers</strong></p></td>
                <td width="9%"><h3 align="center">&nbsp;</h3></td>
              </tr>
            </tbody>
          </table>
  
</div>  
            <p>&nbsp;</p>
          <p align="center">From I. Briggs-Myers &amp; M.&nbsp;McCaulley&nbsp;(1998)&nbsp;<em>Manual: a Guide to the Development and Use of the Myers-Briggs Type Indicator.</em>&nbsp;Consulting Psychologists Press</p>
	
 
 <div id="links">
	<a href="<%=aszPortal%>/typesummaries.jsp">Types</a> | 
	<a href="<%=aszPortal%>/effectsministry.jsp">Effects</a> | 
	<a href="<%=aszPortal%>/fourpreferences.jsp">Preferences</a>
</div>
           
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->