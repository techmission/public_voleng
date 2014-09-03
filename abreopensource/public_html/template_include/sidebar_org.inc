<h5><a href="<%=aszPortal%>/recruitvolunteers.jsp" title="Recruit Volunteers">ORGANIZATIONS</a></h5>
   <ul class="navbullet">
   <li><a href="<%=aszPortal%>/recruitvolunteers.jsp ">Recruit Volunteers</a></li>
    <li><a href="http://www.urbanministry.org/recruit-staff">Recruit Staff</a></li>
	<li><a href="http://www.urbanministry.org/grants">Get Grants</a></li>
    <li><a href="http://www.urbanministry.org/member-benefits">Nonprofit Discounts</a></li>
    <li><a href="http://www.cityvision.edu">Nonprofit College Courses</a></li>
 </ul>       
   
<% if(!(
	(aszSecondaryHost.equalsIgnoreCase( "volengivol" )) ||
        (aszHostID.equalsIgnoreCase( "volengabs" ))
)){ %>

  
<h5><a href="http://www.churchvolunteering.org" title="Church Volunteering">CHURCHES</a></h5> 

  <br>

<% } %>
