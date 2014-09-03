<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->

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
<span style="float: left;">1. Personality Test</span>
<% if(! (aszPartnerTemplate.equalsIgnoreCase("true")) ){ %>
<img  style="float:right" src="/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<% } %>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
	<a href="<%=aszPortal%>/advancedsearch.jsp">search</a> &gt; 
	personality test  </div>
</div>
<br />
<div id="body">
<div id="form">


<form name="individualForm" method="post" action="<%=aszPortal%>/register.do">
<input type="hidden" name="method" id="method" value="processPersonalityTestFinal" />
<!--input type="hidden" name="uid" value="<%="" + userprofile.getUserUID()%>" /-->
<input type="hidden" name="personalitypageno" id="personalitypageno" value="<%="" + userprofile.getUSPPersonalityPageNo()%>" />
<input type="hidden" name="personalitytypei" id="personalitytypei" value="<%="" + userprofile.getUSPPersonalityI()%>" />
<input type="hidden" name="personalitytypee" id="personalitytypee" value="<%="" + userprofile.getUSPPersonalityE()%>" />
<input type="hidden" name="personalitytypes" id="personalitytypes" value="<%="" + userprofile.getUSPPersonalityS()%>" />
<input type="hidden" name="personalitytypen" id="personalitytypen" value="<%="" + userprofile.getUSPPersonalityN()%>" />
<input type="hidden" name="personalitytypef" id="personalitytypef" value="<%="" + userprofile.getUSPPersonalityF()%>" />
<input type="hidden" name="personalitytypet" id="personalitytypet" value="<%="" + userprofile.getUSPPersonalityT()%>" />
<input type="hidden" name="personalitytypej" id="personalitytypej" value="<%="" + userprofile.getUSPPersonalityJ()%>" />
<input type="hidden" name="personalitytypep" id="personalitytypep" value="<%="" + userprofile.getUSPPersonalityP()%>" />

<table>
<tr>
	<td class="set">76.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="1"/> 
	          You are well focused
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="1"/>
	          You find it hard to maintain focus on your pursuits
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">77.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="2"/> 
	          You are quick to make friends.  You have a large circle of acquaintance
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="2"/>
	          You are slow in developing relations.  You have a limited circle of friends but with strong bonds
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">78.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="3"/> 
	          You are fully aware of your five senses and know how to use them
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="3"/>
	         You use your five senses but believe what your intuition guides you
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">79.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="4"/> 
	          You can sacrifice logics for social harmony
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="4"/>
	          You can sacrifice harmony for logical decisions 
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">80.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="5"/> 
	          You keep your options well calculated.  You are decisive by mature
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="5"/>
	         You keep your options open.  You tend to delay your decisions to the last moment for more information
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">81.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="6"/> 
	          You feel comfortable dealing with a number of people at a time
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="6"/>
	          You feel comfortable dealing with people one-by-one
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">82.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="7"/>
	          You are often very alert to your current situation.  While driving you rarely forget your directions
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="7"/>
	          You can show absent mindedness.  While driving, you often forget your directions
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">83.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="8"/> 
	          You are diplomatic and tactful
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="8"/>
	          You are straightforward 
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">84.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="9"/> 
	         You focus on the facts at hand
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="9"/>
	          You always crave for more information
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">85.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="10"/> 
	          You can dominate others in debates very easily.  You can even argue with strangers just for enjoyment
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="10"/>
	          You steer clear of arguments to avoid rift and conflict
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">86.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="11"/> 
	          You can analyze current situations objectively
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="11"/>
	          You can conceptualize future situations subjectively
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">87.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="12"/> 
	          You are generally un-adaptable
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="12"/>
	          You are generally adaptable to new situations
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">88.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="13"/> 
	          You feel no hesitation to express your feelings and emotions
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="13"/>
	          You avoid expressing your feelings and emotions
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">89.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="14"/> 
	          You value practical solutions more than farfetched ideas and novelties
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="14"/>
	          You value new thoughts and ideas without relating them to the practical solutions
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">90.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="15"/> 
	          You love to be definite in your activities and future steps
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="15"/>
	          You love freedom of discipline from your activities
          </dt>
        </dl>
    </td>
</tr>
  
</table>
  <button type="button" value="Submit" name="Submit" onClick="tallyScores()">Submit</button>

  </form>

</div>
<!-- ===========================  END OF MAINCONTENT  ==============================================================-->

</div>
</div>


<script type="text/javascript" src="<%=aszPortal%>/template_include/js/process_personality.js"></script>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc"%>
<!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

