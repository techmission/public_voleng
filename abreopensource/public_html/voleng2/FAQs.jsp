<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Frequently Asked Questions</title>

<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->
<% out.println("<!-- aszNoSearchNav: " + aszNoSearchNav + "; host is: " + aszHostID + " -->");%>
<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">FAQs</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">FAQs</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
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
<span style="float: left;">Frequently Asked Questions</span>
<% if(! (aszPartnerTemplate.equalsIgnoreCase("true")) ){ %>
<img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<% } %>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; FAQs</div> 
</div>
<% } %>
		<div id="body">	 
			
<p><h2>Volunteer FAQs</h2></p>
<p>
<a href="#findopp">How do I find an opportunity?</a> <br>
<a href="#findorg">How do I find an organization?</a><br>
<a href="#contact">How do I contact an organization I'm interested in volunteering with?</a><br> 
<a href="#regvol">How do I register as an volunteer?</a><br>
<a href="#badreg">Why didn't my registration work?</a><br>
<a href="#noreply">What if I contact and organization and don't hear back from them?</a></p>

<p><h2>Organization FAQs</h2></p>
<p>
<a href="#regorg">How do I register as an organization?</a><br>
<a href="#regind">I want to register my organization. Why do I need to register as an individual first?</a><br>
<a href="#badreg">Why didn't my registration work?</a><br>
<a href="#nowwhat">I've registered my organization. Now what?</a><br>
<a href="#opp">How do I list an opportunity?</a><br>
<a href="#interest">How will I know if someone is interested in volunteering with my organization?</a><br> 
<a href="#findorg">How do I find my organization?</a><br>
<a href="#findopp">How do I find the opportunity I listed?</a> <br>
<a href="#duplicate">We have a lot of opportunities. Can we list multiple opportunities at one time?</a> </p>



<p><a name="findopp">How do I find an opportunity?</a><br></p> Go to the <a href="<%=aszPortal%>/advancedsearch.jsp">advanced search</a>. Answer any of the other questions to filter your results, then click "submit".
<p><a name="findorg">How do I find an organization?</a><br> Go to the <a href="<%=aszPortal%>/advancedsearch.jsp">advanced search</a>. At the top
of the page, click "search organizations". Answer any of the other questions to filter your results, then click "submit".</a></p>
<p><a name="contact">How do I contact an organization I'm interested in volunteering with?</a><br> 
To contact an organization you're interested in volunteering with, you must <a href="<%=aszPortal%>/login.jsp">login</a>. Then <a href="<%=aszPortal%>/advancedsearch.jsp">search</a>
for opportunity. Once you've found the opportunity you're interested in, click "I want to volunteer!". This will allow you to fill out a web 
form with a message that will go to the organizational contact. They should contact you shortly.</p>
<a name="regvol">How do I register as an volunteer?</a><br>To register as a volunteer, go to <a href="<%=aszPortal%>/individualregistration.jsp">create account.</a>
Fill out this form and then hit continue.</p> 
<p><a name="noreply">What if I contact an organization and don't hear back from them?</a><br>If you don't hear back from them in a reasonable amount of time, use the contact information you received in your confirmation email to 
call them. If you still can't get in touch with them, email <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %><a href="mailto:info@ivolunteering.org">info@ivolunteering.org</a><% } else { %><a href="mailto:info@christianvolunteering.org">info@christianvolunteering.org</a><% } %>.</p> 
<a name="regorg">How do I register as an organization?</a><br>First, <a href="login.jsp">login</a>. (If you don't have a login, register to get one <a href="<%=aszPortal%>/individualregistration.jsp">here</a>.)
You will be brought to your account management page. Click "add organization" and then fill out the information for your organization. Once you've completed this, don't forget to add volunteer opportunities (See "how do I list an opportunity?).. 
<p><a name="regind">I want to register my organization. Why do I need to register as an individual first?</a><br> a: To create and edit an organizational account, you
must have an individual login. For this reason, everyone must create an individual account. Create one <a href="<%=aszPortal%>/individualregistration.jsp">
here</a>.</p>
<p><a name="badreg">Why didn't my registration work?</a><br> If you tried to register and it didn't work, first go back and make sure that you answer all required questions. Required questions are marked with 
a red asterisk next to the question. If you tried to register and were brought back to the top of the page, look to see if there is any text in red at the top of the page. It may tell you that you need to fill 
in required fields that you missed. If you still can't register, try again in a few minutes. If that doesn't work, email <% if(aszSecondaryHost.equalsIgnoreCase("volengivol")) { %><a href="mailto:info@ivolunteering.org">info@ivolunteering.org</a><% } else { %><a href="mailto:info@christianvolunteering.org">info@christianvolunteering.org</a><% } %>.</p>
<p><a name="nowwhat">I've registered my organization. Now what?</a><br>Don't forget to list volunteer opportunities. Find out how <a href="#opp">here</a></a>.</p>
<p><a name="opp">How do I list an opportunity?</a><br> Login to your account. You will be brought to your account summary where you will see your organization's name; 
click on it. Your organization management page will appear. Now, under "manage opportunities" click "add new 
opportunity". Fill out the information about your opportunity here.</p>
<p><a name="interest">How will I know if someone is interested in volunteering with my organization?</a><br> When a volunteer is interested in an opportunity, they will send you
an email using our automated system. You will recieve the volunteer's contact information in that volunteer so that you can follow up with the person.</p>
<a name="duplicate">We have a lot of opportunities. Can we list multiple opportunities at one time?</a><br>Many organizations ask this if they have similar opportunities that only 
vary by location. Login in to your account and click on your organization's name. Add one opportunity by clicking "add new" and then filling out the form. Now, under "manage opportunities", select the opportunity you just listed and click "duplicate". You will 
see a form to add a new opportunity, with all the information from your first opportunity already filled in. Customize this new opportunity and hit continue; now you've got two 
opportunities listed.</p>
</div>
</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
