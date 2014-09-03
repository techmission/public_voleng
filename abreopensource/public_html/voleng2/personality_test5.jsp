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
<input type="hidden" name="method" id="method" value="showPersonalityTest6" />
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
	<td class="set">61.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="1"/> 
	          You work best by talking things through
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="1"/>
	          You work best by working in a quiet environment
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">62.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="2"/> 
	          You work at a steady pace and have stamina
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="2"/>
	          You have bursts of energy rather than stamina
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">63.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="3"/> 
	          When facing criticism you take it personally and are motivated by appreciation
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="3"/>
	          When facing criticism you don&#39;t take it personally
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">64.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="4"/> 
	          You like to plan and organize your world
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="4"/>
	          You are open to new options and last-minute changes 
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">65.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="5"/> 
	          You are stimulated by the people, spoken words, and life of action
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="5"/>
	          You are stimulated by solitude, contemplation, and imagination
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">66.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="6"/> 
	          You primarily use your five senses to understand words, actions, and situations around you
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="6"/>
	          You prefer to depend upon your insights and intuitions to understand underlying relationships between things
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">67.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="7"/>
	          You always try to discover new ways for the old systems
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="7"/>
	          You rigidly follow rules, regulation, and policy parameters
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">68.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="8"/> 
	          You plan ahead to avoid last minute stresses
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="8"/>
	          You are energized by last minute pressures
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">69.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="9"/> 
	          You prefer to be social
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="9"/>
	          You prefer privacy
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">70.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="10"/> 
	          You prefer to focus on details and facts
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="10"/>
	          You prefer to develop a big picture and ignore details and trivialities
          </dt>
        </dl>
    </td>
</tr>

<tr>
	<td class="set">71.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="11"/> 
	          You have difficulty appearing business-like
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="11"/>
	          You always appear business-like
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">72.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="j" name="12"/> 
	          You are well structured and organized
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="p" name="12"/>
	          You have a flexible approach toward life
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">73.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="e" name="13"/> 
	          You feel pleasure in attending social gatherings
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="i" name="13"/>
	          You avoid social gatherings.  If you have no choice, you hurry to return to solitude	
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">74.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="s" name="14"/> 
	          You focus your energies on what is
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="n" name="14"/>
	          You focus your energies on what ought to be 
          </dt>
        </dl>
    </td>
</tr>
  
<tr>
	<td class="set">75.</td>
	<td valign="middle" colspan="2">
		<dl>
          <dt>
	          <input type="radio" class="radio" onclick="" value="f" name="15"/> 
	          You can be convinced by moving emotions
          </dt>
          <dt>
	          <input type="radio" class="radio" onclick="" value="t" name="15"/>
	          You can be convinced logically
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

