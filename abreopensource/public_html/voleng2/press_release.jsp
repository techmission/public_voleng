<!-- start JSP information --><head>
<style>
<!--
 p.MsoNormal
	{mso-style-parent:"";
	margin-bottom:.0001pt;
	font-size:12.0pt;
	font-family:"Times New Roman";
	margin-left:0in; margin-right:0in; margin-top:0in}
-->
</style>
</head>

<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>



<title>Christian Volunteer Network: In the News</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">News</span>
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
<span style="float: left;">News</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	&gt; news</div> 
</div>
<% } %>

       

		<div id="body">	 
<% if (aszSecondaryHost.equalsIgnoreCase("volengivol") ){ %>
	 <table class="searchtoolfull" cellspacing="0" cellpadding="2" border="0">
        <tbody>
        
        <tr>
			<td>
			<br><h2>Website Launched with the Goal of Matching Hundreds of Thousands of Christian Volunteers</h2> 
			</td>         
			</tr>
			<tr>
			 <td valign="top" colspan="2">
				<p>(Boston, MA) TechMission has launched a free website ChristianVolunteering.org with the goal of matching hundreds of thousands 
				of volunteers to Christian volunteer opportunities in urban ministries and short-term missions. Volunteers just type in their zip 
				code to find a list of Christian volunteer opportunities or search by interest area or skills.  Organizations can also post their 
				opportunities for free.</p>  

				<p>Volunteers can also search for virtual volunteering where they volunteer from home by providing services at a distance.  
				For example, a Web designer in San Francisco could volunteer to provide a website to an urban ministry in Kansas City.  Some of 
				the many potential virtual volunteer areas include opportunities for accounting, graphics designing, computer programming, grant 
				writing, language translation, legal support, prayer and online tutoring.</p>  

				<p>According to the United States Department of Labor, faith-based volunteers are the largest pool of volunteers, representing over 
				34.8% of volunteers in 2005. The challenge is that the majority of these volunteers do not serve 
				&quot;outside the walls of churches,&quot; 
				so this represents an enormous potential that could be directed to areas with the most need.  According to Lester Salamon of the 
				Institute for Policy Studies of Johns Hopkins University, only 7-15% of volunteering through churches helps the larger community. 
				According to the Corporation for National and Community Service, the value of the donated time of faith-based volunteers in 2005 was 
				$51.8 billion dollars, but only $3.6 to $7.8 billion of this value goes to serve those outside of the church. If online volunteer 
				matching were able to provide just a 10% increase in the number of faith-based volunteers serving the larger community, this would 
				represent $5.1 billion in additional resources for those communities. </p>

				<p>While this goal may sound ambitious, last year the leading secular Internet volunteer matching service (VolunteerMatch) placed 
				over 475,000 volunteers in 37,000 nonprofit organizations with the volunteer time valued at $232 million.  They also report that 
				organizations using online volunteer matching had over 50% of their volunteers come from online sources.  The problem is that 
				Christian organizations do not want to use secular volunteer matching services and Volunteer Match reports that only 1.8% of their 
				posts are from faith-based organizations.</p>

				<p>TechMission is a Christian nonprofit social service organization with over 500 member ministries that serve over 50,000 people 
				in at-risk communities to address the digital divide. For more information, contact Andrew Sears at (617) 282-9798 x101 or 
				andrew@techmission.org.</p>

          </td>
        </tr>
      </table>			
<% } %>			
</div>
</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
