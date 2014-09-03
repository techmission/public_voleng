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


<html:form method="post" action="/register.do">
<html:hidden property="method" value="showPersonalityTest4" />
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
	<td class="set">27.<b> Is it worse to be: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="1"/> 
	          too critical
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="1"/>
	          too emotional 
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">28. <b> Would you rather have things: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="2"/> 
	          finished and decided
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="2"/>
	          open to change 
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">29.<b> When it comes to news at work, you seem: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="3"/> 
	          to find it out quickly
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="3"/>
	          to be one of the last to know
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">30. <b>Are you more likely to trust:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="4"/> 
	          your experiences
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="4"/>
	          your hunches 
          </dt>
        <br>
    </td>
</tr>
<tr>
	<td class="set">31.<b> You prefer leaders who are more:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="5"/> 
	          caring and supportive
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="5"/>
	          knowledgeable and expect a lot
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">32. <b> Is it more your way to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="6"/> 
	          finish one project before you start a new one
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="6"/>
	          have lots of projects going at once
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">33. <b> Which is more true of you--do you: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="7"/> 
	          too often act and talk without thinking much first
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="7"/>
	         spend too much time thinking and not enough doing
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">34. <b>Things would be more fair if people: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="8"/> 
	          would just follow the rules
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="8"/>
	          would just show integrity
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">35.<b>Is it usually easier for you to tell: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="9"/> 
	          how someone else is feeling
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="9"/>
	         what someone else is thinking
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">36. <b>Which is the more useful ability: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="10"/> 
	         to be able to organize and plan
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="10"/>
	          be able to adapt and make do 
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">37.<b>At a party or gathering</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="11"/>
	          you do more of the introducing of others
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="11"/>
	          others introduce you more
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">38.<b>Others have suggested that you too often:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="12"/> 
	          oversimplify a task
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="12"/>
	          overcomplicate a task
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">39.<b>It is more your way to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
	
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="13"/> 
	          usually show what you are feeling
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="13"/>
	          usually not show your feelings
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
aszGoalPage = "/personalitytest/3";
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

