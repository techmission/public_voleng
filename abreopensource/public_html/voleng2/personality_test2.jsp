<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<%//@ include file="/template_include/facebookapi_keys.inc" %>


<% /* if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
		
		FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

	if(facebookHelp.requireLogin("")) return;
} */
%>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="userprofile" name="userprofile" type="com.abrecorp.opensource.dataobj.PersonInfoDTO"/>

<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>

<% if(aszRemoteHost.equalsIgnoreCase( "facebook.cv.org:7001" )){ %>
<div id="chrisvol_nav">
<a href="<%=aszPortal%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
<a href="<%=aszPortal%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>&nbsp;&nbsp;|&nbsp;
<a href="http://www.urbanministry.org/redirect-home" class="level-1"><span>My City</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/about.jsp" class="level-1"><span>About</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=aszPortal%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>

</div>
<br>
<% } %>
<!-- end navigation information -->
</div>

<div id="pagebanner">
<span style="float: left;">Personality Test</span>
</div>

<%@ include file="/template_include/personality_test_progress_bar.inc" %>
      
<br />
<div id="body">
<div id="form">
<script type="text/javascript">
window.onload = function() {
	scrollTo(0,0);
}
</script>

<html:form method="post" action="/register.do">
<input type="hidden" name="method" id="method" value="showPersonalityTest3" />
<html:hidden property="personalitypageno"  />
<html:hidden property="personalitytypei"  />
<html:hidden property="personalitytypee"  />
<html:hidden property="personalitytypes"  />
<html:hidden property="personalitytypen"  />
<html:hidden property="personalitytypef" />
<html:hidden property="personalitytypet"  />
<html:hidden property="personalitytypej" />
<html:hidden property="personalitytypep"  />
<html:hidden property="numberofquestions" value="13" />
<table>
<tr>
	<td class="set">14.<b> Are you more interested in: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="s" name="1"/>
	          	what really is
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="n" name="1"/>
        	  	what can be
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">15.<b> When you look at two things, you mostly notice: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
          		<input type="radio" class="radio" onclick="" value="f" name="2"/> 
	          	how they are the same
          </dt>
          <dt>
    	      	<input type="radio" class="radio" onclick="" value="t" name="2"/>
        	  	how they are different
          </dt>
        <br>
    </td>
</tr>
<tr>
	<td class="set">16. <b>When you do a job, you want to know: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="3"/> 
	          only what you need to so you can get started
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="3"/>
	          all that you can about the task
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">17.<b> Most other people seem to see you as: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="4"/> 
	          kind of out-going
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="4"/>
	          kind of shy and reserved
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">18. <b> When it comes to work that is very exact or detailed: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="5"/> 
	          it comes pretty easily to you
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="5"/>
	          you tend to lose interest in it over time
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">19.<b> When your friends disagree, it is more important: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="6"/> 
	          to help them agree and come together
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="6"/>
	          to help them come to the right answer
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">20. <b> When you get up in the morning: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="7"/> 
	          you know pretty much how your day will go
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="7"/>
	          it seems every day is pretty different
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">21. <b>When it comes to using the phone: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="8"/> 
	          you use it a lot and make most of the calls
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="8"/>
	          you use it most when others call you
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">22. <b>When you work on group projects, do you prefer to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="9"/>
	          help make sure the project gets done & works
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="9"/>
	          help come up with the ideas and plans
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">23. <b> Others often describe you as a: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="10"/> 
	          warm-hearted person
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="10"/>
	          cool-headed person 
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">24. <b> Which is more your way:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="11"/> 
	          to "do the right thing"
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="11"/>
	          to "just do it" 
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">25. <b> When you talk to strangers you&#39;ve just met, you: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="12"/> 
	          talk pretty easily and at length
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="12"/>
	         run out of things to say pretty quickly 
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">26. <b> When it comes to work you: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="13"/> 
	          prefer steady effort and a regular routine
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="13"/>
	          work in spurts, really "on" then really "off"
          </dt>
        <br>
    </td>
</tr>
  

  
</table>
  <button type="button" value="Submit" name="Submit" onClick="tallyScores()">Next</button>

  </html:form>

</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>
</div>


<script type="text/javascript" src="<%=aszPortal%>/template_include/js/process_personality.js"></script>


<% // for google analytics tracking: %>
<% 
String aszGoalPage;
aszGoalPage = "/personalitytest/2";
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>


<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

