<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<SCRIPT TYPE="text/javascript">
<!--
window.focus();
//-->
</SCRIPT>




<!-- BEGIN MAINCONTENT -->

<div id="maincontent">

       <div id="body">
			
			  <TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 >
        <TBODY>
       
        
       
			<tr>
			 <TD valign=top colspan=2>
	     	<p>
			
			<b><a name="local_volunteering">Local Volunteering: </b></a>The volunteer must leave their home and 
			go to the organization. </span></p>
			<p>
			
			<b><a name="mission_statement">Mission Statement:</a></b> Here you can enter your organization's 
			mission statement. This will give volunteers an initial sense of 
			what you believe and value.</span></p>
			<p>
			<b><a name="organization_description">Organization Description:</a></b> Enter information about what your 
			organization does. You may want to list hours of operation, the 
			population that you serve, your organization's history, or other 
			relevant information. </span></p>

			<p>			
			<b><a name="opportunity_description">Opportunity Description:</a></b><i> </i>
			Enter information about what the volunteer will be doing. Include a 
			list of duties, 
			responsibilities, benefits to volunteers, and any other relevant information. Consider 
			including directions to your location.</span></p>

<% if(!(aszSecondaryHost.equalsIgnoreCase( "volengivol" ))){ %>
			<p><b><a name="faith">Organizational Statement of Faith:</a></b><i> </i>
			This is a list of Christian beliefs that your organization has.
			</span></p>
<% } %>

			<p>
			<b><a name="organization_partners">Organization Partners:</a></b><i> </i>Please select if you are 
			affiliated with any partners on the drop down list.<i> &nbsp;</i></span></p>
			
			<p><b><a name="skills">Personal Skills:</a></b><i> </i>
			Enter here skills that (1) you have some competency in and (2) that you would like to use in volunteering.
			</span></p>

			<p><b><a name="program_types">Program Types:</a></b><i> </i>
			Enter here the primary program that your organization runs and needs volunteers for. 
			</span></p>

			<p>
			<b><a name="required">Requirements for Volunteers:</a></b><i> </i>Enter any 
			requirements you have for volunteers. Here you can also inform the volunteer of any screenings 
			(such as background check or interview) or orientations that they 
			will be required to complete.<i> </i></span>If volunteers need to 
			bring special equipment, such as specific clothing or tools, include 
			that as well.</p>

			<p>		
			<b><a name="service">Service Areas:</a></b> Volunteers can search for opportunities by 
			service areas. These describe the issues or causes that your 
			opportunity serves. </span></p>

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
			<p><b><a name="short-term_missions">Short-Term Volunteer Work / Volunteer Internship:</a></b> The individual or 
			volunteer group will usually serve for a short but concentrated 
			amount of time. Often people will travel long distances to these 
			opportunities. </span></p>
<% }else{ %>
			<p><b><a name="short-term_missions">Short-Term Missions / Volunteer Internship:</a></b> The individual or 
			volunteer group will usually serve for a short but concentrated 
			amount of time. Often people will travel long distances to these 
			opportunities. </span></p>
<% } %>

			<p><b><a name="virtual_volunteer">Virtual Volunteer:</a></b> Volunteer from 
			home, your work, or wherever is convenient from you. Especially 
			relevant for technical and professional volunteering such as web design, accounting, research, 
			grant-writing, or public relations. </span>All of these can be done 
			via phone, email, fax, or online. </p>

			</TD>
        </TR>
      </table>
</div>
</div>

