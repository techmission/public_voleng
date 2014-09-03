<!-- start file cv_footer.inc -->
<!-- aszHostID is <%=aszHostID%> -->
<% if(  (aszHostID.equalsIgnoreCase("voleng1")) ||
		(aszHostID.equalsIgnoreCase("default")) ||
		(aszHostID.equalsIgnoreCase("volengdefault")) ||
		(aszHostID.equalsIgnoreCase("volengcatholic")) ||
                (aszHostID.equalsIgnoreCase("volengchurch")) 
){ %>
	<%@ include file="/template_include/footer_chrisvol.inc" %>
<% } else { %>

	<!-- this file includes the TechMission portion of footer - all partner footers should include this file as well -->
	<br>
	
	<!-- Buttn Facebook -->
	<script>function fbs_click() {u=location.href;t=document.title;window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(u)+'&t='+encodeURIComponent(t),'sharer','toolbar=0,status=0,width=626,height=436');return false;}</script>
	
	<style> 
	html .fb_share_button {
	display:inline-block; 
	padding:1px 20px 0 5px; 
	height:15px; 
	width:40px; 
	border:1px solid #3B5998; 
	background:#fff url(http://static.ak.facebook.com/images/share/facebook_share_icon.gif?12:26981) no-repeat top right;
	color:#123470!important;
	} 
	html .fb_share_button:hover { 
	color:#fff!important; 
	border-color:#295582; 
	background:#3b5998 url(http://static.ak.facebook.com/images/share/facebook_share_icon.gif?12:26981) no-repeat top right;
	text-decoration:none; 
	} 
	</style> 
	<a href="http://www.facebook.com/share.php?u=<url>" class="fb_share_button" onclick="return fbs_click()" target="_blank" style="text-decoration:none;">Share</a>
	<!--/ Buttn Facebook -->
	
	 <span style="font-size: 14px;"><a href="mailto:info@christianvolunteering.org">Email us</a> for support.</span>
	<br>
	<a href="http://www.urbanministry.org/terms">privacy</a> | 
	<a href="<%=aszPortal%>/contact.jsp" title="Contact Christian Volunteering">contact</a> | 
	<a href="mailto:feedback@christianvolunteering.org">feedback</a> | 
        <a href="http://www.urbanministry.org/help">help</a> |
	<a href="<%=aszPortal%>/sitemap.jsp">sitemap</a> | <a href="http://www.christianvolunteering.org" title="Christian Volunteer Directory: Volunteering for Urban Ministries and Short Term Urban Missions Opportunities">ChristianVolunteering.org</a> | 
	<a href="http://www.techmission.org" title="Technology and Urban Ministry, Christian Community Computer Centers Addressing the Digital Divide">TechMission.org</a> | 
	<a href="http://www.safefamilies.org" title="Free Parental Controls & Internet Filtering Software We-Blocker Download - Online Safety Workshops - Christian Accountability, Pornography and Sex Addiction Recovery"> SafeFamilies.org</a> | 
	<a href="http://www.urbanministry.org" title="UrbanMinistry.org: Urban ministry MP3s, talks, podcast, sermons, workshops, presentations, Webcasts, training, on racism, racial reconciliation, ethnic identity, classism, sexism, grants and church resources"> UrbanMinistry.org</a> | <a href="http://www.cityvision.edu" title="Online Urban Ministry Courses in Theology, Missions, Accounting, CFRE Fundraising, Management, Evangelism and Counseling">CityVision.edu</a><br />
	<br />

<% } %>



<!-- end file cv_footer.inc -->

