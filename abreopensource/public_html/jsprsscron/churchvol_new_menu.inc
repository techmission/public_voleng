<% 
String aszMenuOrgLink = aszPortal+"/org.do?method=shownonpeditstep1&reqtype=edit&orgnid="+iLoggedInUserOrgNID;
if(iLoggedInUserOrgNID < 1){
	aszMenuOrgLink = aszPortal+"/org.do?method=showCreateOrgStep1"; 
}
String aszMenuContactsLink = "/org.do?method=showOrgAdmins&orgnid="+iLoggedInUserOrgNID;
String aszMenuOppLink = aszPortal+"/org.do?method=showOrgManageListings&orgnid="+iLoggedInUserOrgNID;
if(iLoggedInUserOrgNID < 1){
	aszMenuOppLink = aszPortal+"/org.do?method=showCreateOrgStep1"; 
}else if(aCurrentUserObjLoggedIn.getOrgOppNIDsArray().length==0){
	aszMenuOppLink = aszPortal+"/org.do?method=showOrgAddOpp1&orgnid="+iLoggedInUserOrgNID;
} 
//iLoggedInUserOppNID=iLoggedInUserOppNID;
%>

<div id="primary-nav-wrap">
	<ul id="primary-nav">	
		<li id="item-browse-content" class="level-1"><a href="<%=aszPortal%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>

			<ul id="secondary-nav-1">
				<li id="item-short-term-missions" class="level-2"><a href="<%=aszPortal%>/shorttermmissions.jsp">Short Term Missions</a></li>
				<li id="item-virtual-volunteering" class="level-2"><a href="<%=aszPortal%>/virtualvolunteer.jsp">Virtual Volunteering</a></li>
				<li id="item-browse-opportunities" class="level-2"><a href="<%=aszPortal%>/directory.jsp">Browse Opportunities</a></li>
				<li id="item-browse-organizations" class="level-2"><a href="<%=aszPortal%>/directoryorgs.jsp">Browse Organizations</a></li>
			</ul>

		</li>	
		<li id="item-volunteers" class="level-1">
<% if(false == LoginBean.IsSessionLoggedIn( request )){ %>
			<a href="<%=aszPortal%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>
<% }else{ %>
			<a href="<%=aszPortal%>/volunteerdashboard.jsp" class="level-1"><span>Volunteers</span></a>
<% } %>
	
			<ul id="secondary-nav-2">
				<li id="item-short-term-missions" class="level-2"><a href="<%=aszPortal%>/shorttermmissions.jsp">Short Term Missions</a></li>
				<li id="item-virtual-volunteering" class="level-2"><a href="<%=aszPortal%>/virtualvolunteer.jsp">Virtual Volunteering</a></li>
				<li id="item-find-jobs" class="level-2"><a href="/christianjobs.jsp">Find Jobs</a></li>
				<li class="level-2"><a href="http://www.urbanministry.org/mycity" class="level-3">My City</a></li>
			</ul>
		</li>	
		
		<li id="item-organizations" class="level-1">
<% if(false == LoginBean.IsSessionLoggedIn( request )){ %>
			<a href="<%=aszPortal%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>
<% }else{ %>
			<a href="<%=aszMenuOppLink%>" class="level-1"><span>Organizations</span></a>
<% } %>
		
			<ul id="secondary-nav-3">
<% if(false == LoginBean.IsSessionLoggedIn( request )){ %>
				<li id="item-recruit-volunteers" class="level-2"><a href="<%=aszPortal%>/recruitvolunteers.jsp">Recruit Volunteers</a></li>
				<li id="item-recruit-staff" class="level-2"><a href="<%=aszPortal%>/orgmanagement.jsp">Recruit Staff</a></li>
				<li id="item-get-grants" class="level-2"><a href="http://www.urbanministry.org/grants">Get Grants</a></li>
				<li id="item-get-nonprofit-discounts" class="level-2"><a href="http://www.urbanministry.org/member-benefits">Get Nonprofit Discounts</a></li>
				<li id="item-nonprofit-college-courses" class="level-2"><a href="http://www.cityvision.edu">Nonprofit College Courses</a></li>
<% }else{ %>
				<li id="item-manage-organization" class="level-2"><a href="<%=aszMenuOrgLink%>">My Organizations</a></li>
				<li id="item-manage-opportunity" class="level-2"><a href="<%=aszMenuOppLink%>">My Opportunities</a></li>
				<li id="item-manage-contacts" class="level-2"><a href="<%=aszMenuContactsLink%>">My Contacts</a></li>
<% if(aszPortal.length()>0){ %>
				<li id="item-share" class="level-2"><a href="<%=aszPortal%>/org.do?method=showPortalManage">Share on Website</a></li>
<% }else{ %>
				<li id="item-share" class="level-2"><a href="<%=aszPortal%>/org.do?method=showOrgShareOnWebsite&orgnid=<%=iLoggedInUserOrgNID%>#embed_listurl">Share on Website</a></li>
<% }} %>					
			</ul>
		</li>	
		
		<li id="item-churches" class="level-1">
<% if(false == LoginBean.IsSessionLoggedIn( request )){ %>
			<a href="http://www.churchvolunteering.org" class="level-1"><span>Churches</span></a>	
<% }else{ %>
			<a href="<%=aszPortal%>/orgmanagement.jsp" class="level-1"><span>Churches</span></a>
<% } %>
		</li>

		
		<li id="item-internships" class="level-1"><a href="http://www.cityvision.edu/urbanministryinternships" class="level-1"><span>Internships</span></a>

		<li id="item-jobs" class="level-1"><a href="<%=aszPortal%>/christianjobs.jsp" class="level-1"><span>Jobs</span></a>
			<ul id="secondary-nav-3">
				<li id="item-manage-account" class="level-2"><a href="<%=aszPortal%>/individualregistration.jsp">Create Account</a></li>
<% if(aCurrentUserObjLoggedIn.getORGNID() > 0){ %>					
				<li id="item-login" class="level-2"><a href="<%=aszPortal%>/org.do?method=showOrgAddOpp1&orgnid=<%=aCurrentUserObjLoggedIn.getORGNID()%>&opppositiontypetid=33389">Post Jobs</a></li>
<% }else{ %>
				<li id="item-login" class="level-2"><a href="<%=aszPortal%>/orgmanagement.jsp">Post Jobs</a></li>
<% } %>					
			</ul>
		</li>

		<li id="item-about" class="level-1"><a href="/about.jsp" class="level-1"><span>About</span></a>
			<ul id="secondary-nav-4">
				<li id="item-about-techmission" class="level-2"><a href="http://www.techmission.org">About TechMission</a></li>
				<li id="item-programs" class="level-2"><a href="http://www.techmission.org/cms/tm/strategy">Programs</a></li>
				<li id="item-our-vision" class="level-2"><a href="http://www.techmission.org/cms/tm/opportunity">Our Vision</a></li>
				<li id="item-annual-report" class="level-2"><a href="http://www.urbanministry.org/Annual-Report">Annual Report</a></li>
				<li id="item-donate" class="level-2"><a href="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&amp;id=13">Donate</a></li>
				<li id="item-contact-us" class="level-2"><a href="http://www.christianvolunteering.org/contact.jsp">Contact Us</a></li>
			</ul>
		</li>


<% if(aCurrentUserObjLoggedIn.getORGNID()>0){ %>
		<li id="item-my-account" class="level-1"><a href="<%=aszPortal%>/orgmanagement.jsp" class="level-1"><span>My Account</span></a>	
<% }else{ %>
		<li id="item-my-account" class="level-1"><a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>	
<% } %>
			<ul id="secondary-nav-5">
<% if(false == LoginBean.IsSessionLoggedIn( request )){ %>
				<li id="item-manage-account" class="level-2"><a href="<%=aszPortal%>/individualregistration.jsp">Create Account</a></li>
				<li id="item-login" class="level-2"><a href="<%=aszPortal%>/login.jsp">Login</a></li>
				<li id="item-reset-password" class="level-2"><a href="http://www.urbanministry.org/user/password">Reset Password</a></li>
				<li id="item-help" class="level-2"><a href="mailto:info@christianvolunteering.org">Help</a></li>
<% }else{ %>
				<li id="item-manage-account" class="level-2"><a href="<%=aszPortal%>/volunteerdashboard.jsp">Account Home</a></li>
				<li id="item-manage-personal" class="level-2"><a href="/profiles/mine/edit">Personal Account</a></li>
				<li id="item-manage-organization" class="level-2"><a href="<%=aszPortal%>/orgmanagement.jsp">Manage Organization</a></li>
				<li id="item-reset-password" class="level-2"><a href="http://www.urbanministry.org/user/password">Reset Password</a></li>
				<li id="item-help" class="level-2"><a href="mailto:info@christianvolunteering.org">Help</a></li>
<% } %>
			</ul>
		</li>
	</ul>
</div>
<div class="clearing"></div>
