<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

<%//@ include file="/template_include/facebookapi_keys.inc" %>
<%@page import="com.google.code.facebookapi.FacebookWebappHelper"%>

<% /*if(aszRemoteHost.equalsIgnoreCase( "facebook.christianvolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org" ) || aszRemoteHost.equalsIgnoreCase( "facebook.ivolunteering.org:8008" )){ 
		
		FacebookWebappHelper facebookHelp = FacebookWebappHelper.newInstanceJson(request, response, appapikey, appsecret );

	if(facebookHelp.requireLogin("")) return;
}*/
%>

<% boolean needsLoginIFrame = false; %>

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
<a href="<%=request.getContextPath()%>/advancedsearch.jsp" class="level-1"><span>Search</span></a>&nbsp;&nbsp;|&nbsp;	
<a href="<%=request.getContextPath()%>/volunteergettingstarted.jsp" class="level-1"><span>Volunteers</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/recruitvolunteers.jsp" class="level-1"><span>Organizations</span></a>&nbsp;&nbsp;|&nbsp;
<a href="http://www.urbanministry.org/redirect-home" class="level-1"><span>My City</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/about.jsp" class="level-1"><span>About</span></a>&nbsp;&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/register.do?method=showIndAcctSum1" class="level-1"><span>My Account</span></a>

</div>
<br>
<% } %>
<!-- end navigation information -->
</div>

<div id="pagebanner">
<span style="float: left;">Personality Test</span>
</div>

<br />
<div id="body">
<div id="form">
<% out.print("<!-- Heather modified -->"); %>

<html:form method="post" action="/register.do">
<html:hidden property="method" value="showPersonalityMinistryAreasTest" />
<!--input type="hidden" name="uid" value="<%="" + userprofile.getUserUID()%>" /-->
<html:hidden property="personalitypageno"  />
<html:hidden property="personalitytypei"  />
<html:hidden property="personalitytypee"  />
<html:hidden property="personalitytypes"  />
<html:hidden property="personalitytypen"  />
<html:hidden property="personalitytypef" />
<html:hidden property="personalitytypet"  />
<html:hidden property="personalitytypej" />
<html:hidden property="personalitytypep"  />
<html:hidden property="personalitytype" />
<html:hidden property="personalitytypetid"  />
<html:hidden property="numberofquestions" value="13" />
<html:hidden property="personinternalcomment" value="facebook" />


<table>
<tr>
	<td class="set">40.<b> You are the kind of person who </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="1"/> 
	          needs to have things a certain way
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="1"/>
	          does it any old way
          </dt>
        <br>
    </td>
</tr>
<tr>
	<td class="set">41.<b>When you get done with an assignment: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="2"/> 
	          you feel like showing it to someone
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="2"/>
	         you like to keep it to yourself
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">42.<b>Things would be better if people were: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
	
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="3"/> 
	         more realistic
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="3"/>
	          more imaginative
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">43.<b>Would you say you are more concerned: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="4"/> 
	          with being appreciated by others 
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="4"/>
	         with achieving something important
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">44.<b>It is better that people: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="5"/> 
	          know what they want
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="5"/>
	          keep an open-mind
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">45.<b>Friday night after a long week you usually: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="6"/> 
	          feel like going to a party or going out
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="6"/>
	          feel like renting a movie or relaxing
          </dt>
        <br>
    </td>
</tr>
<tr>
	<td class="set">46.<b>When you do a job, it&#39;s usually your approach to: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="7"/> 
	          start from the beginning, and go step-by-step
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="7"/>
	         start anywhere, and figure it out as you go
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">47.<b>When you tell a story, you mostly talk about: </b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="8"/> 
	          how the people involved were affected
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="8"/>
	         what went on in general
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">48.<b>You feel most comfortable when things are:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="9"/> 
	         planned and you know what to expect
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="9"/>
	          unplanned and flexible
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">49.<b>Most people describe you as more:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="10"/> 
	          energetic and talkative
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="10"/>
	         calm and a good listener
          </dt>
        <br>
    </td>
</tr>

<tr>
	<td class="set">50.<b>Which do you find more compelling?</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
		
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="11"/> 
	          a proven practice that has been shown to work
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="11"/>
	         a sound theory that makes perfect sense
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">51.<b>You feel more comfortable responding to others&#39;</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
	
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="12"/> 
	         feelings and values
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="12"/>
	         thoughts and ideas
          </dt>
        <br>
    </td>
</tr>
  
<tr>
	<td class="set">52.<b>When it comes to daily tasks, you find yourself:</b></td>
</tr>
<tr>
	<td valign="middle" colspan="2">
	
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="13"/>
	          finding a system for doing them that you use consistently
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="13"/>
	         using a variety of strategies that depend on the situation
          </dt>
        <br>
    </td>
</tr>

  
</table>


<jsp:useBean id="UserSessionBean" scope="session" class="com.abrecorp.opensource.servlet.UserSessionBean" />
<%
if( false == UserSessionBean.IsSessionLoggedIn( request ) ){	
   	// not logged in
	needsLoginIFrame = true;
}else{
	PersonInfoDTO aCurrentUserObj = UserSessionBean.getCurrentUser( request);	
	if(null == aCurrentUserObj){	
    	// not logged in
		needsLoginIFrame = true;
	}else{
		if(aCurrentUserObj.getUserUID()>0){
			// user is logged in
			needsLoginIFrame = false;
		}else{
			// user is not in
			needsLoginIFrame = true;
		}
//out.print(needsLoginIFrame);
	}
} 
//out.print(needsLoginIFrame);
//needsLoginIFrame=true;// comment out eventually


if(	needsLoginIFrame == false ){
%>

<% }else{ %>
	Would you like to link to an existing account? You may use your account from iVolunteering.org, ChristianVolunteering.org, or UrbanMinistry.org. 
	<input name="linkaccount" value="Yes" type="checkbox" onClick="handleLink()"/>
	<div id="linkexistingaccount" style="display:none">
	<table>
		<tr>
        		<TD width=120><b>Email</b> <span class="criticaltext">*</span></TD>
       			<TD height="23"><html:text property="email1addr" styleId="email1addr" size="25" styleClass="textinputwhite"/></TD>
		</tr>
		<tr>
                <TD width=120><b>Password</b> <span class="criticaltext">*</span> </TD>
                <TD height="23">
					<html:password property="password1" size="25" styleClass="textinputwhite" redisplay="false" />
                </TD>
 		</tr>
	</table>
	
	<button type="button" value="Submit" name="Submit" onClick="submitIt()">Submit</button>
	</div>
	<% } %>
	<br />
	<div id="noaccount" name="noaccount">  
  <button type="button" value="Submit" name="Submit" onClick="tallyScores()">Submit</button>
</div>

  </html:form>

</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>
</div>
<script type="text/javascript">
function submitIt(){
	//alert(document.forms["individualForm"].elements["method"].value);
	//alert(<%=session.getAttribute("FB_User_ID")%>);
	
	tallyScores();
}

function handleLink() {
	//alert("handleLink");
	if(document.individualForm.linkaccount.checked == true){
		//alert("checked");
		updateAccount();
	}
	else{
		//alert("not checked");
		noAccount();
	}
}

function updateAccount() {
	document.getElementById('linkexistingaccount').style.display='inline';
	document.forms["individualForm"].elements["method"].value = "processLogin";	
	document.forms.individualForm.action='<%=request.getContextPath()%>/register.do';
	document.getElementById('noaccount').style.display='none';
	//document.getElementById('password1').style.display='inline';
	//document.getElementById('body').style.display='none';
	//document.getElementById('processing').style.display='inline';
	//document.getElementById('individualForm').style.display='none';
	//document.getElementById('registerTitle').style.display='none';
	//document.getElementById('submitButton').style.display='none';
	//document.forms["individualForm"].elements["email1addr"].value = document.forms["loginForm"].elements["email1"].value;
	//document.forms["individualForm"].elements["password1"].value = document.forms["loginForm"].elements["pass"].value;
	//document.forms["individualForm"].submit();
	//alert(document.forms["individualForm"].elements["method"].value);
	//alert(document.forms["individualForm"].elements["personalitytype"].value);
	
	
}

function noAccount(){
	document.getElementById('linkexistingaccount').style.display='none';
	document.forms["individualForm"].elements["method"].value = "showPersonalityMinistryAreasTest";	
	document.forms.individualForm.action='<%=request.getContextPath()%>/register.do';
	document.getElementById('noaccount').style.display='inline';
	//document.getElementById('password1').style.display='none';
}
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/template_include/js/process_personality.js"></script>
<% // for google analytics tracking: %>
<% 
String aszGoalPage;
aszGoalPage = "/personalitytest/4";
%>
<%@include file="/template_include/footer_google_analytics_goal_pages.inc"%>
<% // : end of for google analytics tracking %>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

