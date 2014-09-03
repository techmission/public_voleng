<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<style type="text/css">
* {//margin:0; //padding:0;}
h4{margin-bottom:5px;}
#maincontent.sidebarless{width: 100%; backround-image:none; border:none; padding-bottom:0px;}
#contentwrapper {background-image:none; background-color:#FFF; width:760px;}
#pagebanner {//width:950px;}
h3 {
font-size:16px; 
color:#293C5F; 
text-align:center; 
padding-bottom:5px;
}
h4 {
font-size:13px; 
padding:3px; 
text-align:center; 
margin:0px; 
color:#293C5F;
}
div#content1 ul {padding-left:0px;}
div#content1 table {max-width:700px;}
table p {margin: 0 10px;}
#links {text-align:center; padding:10px 0;}
a { color:#000000; font-weight:inherit;}
</style>

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>

<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Sources</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> 
	
</div>
<% } %>
    
            
       <a style="float: right;" title="browse Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities near your city" href="<%=aszPortal%>/myministryopportunities.jsp"><img align="right" style="padding:0px 0 5px;" border="none" src="<%=aszPortal%>/imgs/ministry-oppor-button.gif"/></a>
    
		<div id="body">	 

         
<ul>
  <table border="1" cellspacing="0" cellpadding="0" width="700">
    <tbody>
      <tr>
        <td width="700" colspan="3" valign="top" style="background:#E5E7B5;">
        <h3> Effects of Each Preference In Ministry Situations</h3></td>
      </tr>
      <tr>
        <td width="350" valign="top"><p align="center"><h4>Extraverts</h4></p>
            <p>Like variety and action </p>
          <p>Are often good at greeting people</p>
          <p>Are sometimes impatient with long slow jobs</p>
          <p>Are interested in how others do their jobs</p>
          <p>Often enjoy talking on the phone</p>
          <p>Often act quickly, sometimes without thinking</p>
          <p>Like to have people around in the working environment</p>
          <p>May prefer to communicate by talking rather than writing</p>
          <p>Learns best from doing </p>
          <p>Willing to offer opinion</p>
          <p>&nbsp; </p></td>
        <td width="1" valign="top"><p>&nbsp;</p></td>
        <td width="350" valign="top"><p align="center"><h4>Introverts</h4></p>
            <p>Like quiet for concentration</p>
          <p>Have trouble remembering names and faces</p>
          <p>Can work on one project for a long time</p>
          <p>Are interested in the idea behind the jobs.</p>
          <p>Dislike telephone interruptions<br />Think before they act, sometimes without acting</p>
          <p>Work alone contentedly</p>
          <p>May prefer communications to be in writing</p>
          <p>Like to learn a new task by talking it through with someone </p>
          <p>Seems hard to understand </p>
          <p>&nbsp;</p></td>
      </tr>
      <tr>
        <td width="350" valign="top"><p align="center"><h4>Sensing types</h4></p>
            <p>Are aware of the uniqueness of each event </p>
          <p>Focus on what works now </p>
          <p>Like an established way of doing things </p>
          <p>Enjoy applying what they have already learned </p>
          <p>Work steadily, with a realistic idea of how long it will take </p>
          <p>Usually reach a conclusion step by step</p>
          <p>Are not often inspired, and may not trust the inspiration&nbsp;when they are</p>
          <p>Are careful about the facts</p>
          <p>May be good at precise work</p>
          <p>Can oversimplify a task</p>
          <p>Accept current reality as a given to work with </p>
          <p>&nbsp;</p></td>
        <td width="1" valign="top"><p>&nbsp;</p></td>
        <td width="350" valign="top"><p align="center"><h4>Intuitive types</h4></p>
            <p>Are aware of new challenges and possibilities </p>
          <p>Focus on how things could be improved </p>
          <p>Dislike doing the same thing repeatedly </p>
          <p>Enjoy learning new skills </p>
          <p>Work in bursts of energy powered by enthusiasm, with slack&nbsp;periods in between</p>
          <p>May leap to a conclusion quickly</p>
          <p>Follow their inspirations and hunches</p>
          <p>May get their facts a bit wrong</p>
          <p>Dislike taking time for precision</p>
          <p>Can &ldquo;overcomplexify&rdquo; a task</p>
          <p>Ask why things are as they are</p>
          <p>&nbsp;</p></td>
      </tr>
      <tr>
        <td width="350" valign="top"><p align="center"><h4>Thinking types</h4></p>
            <p>Are good at putting things in logical order </p>
          <p>Respond more to people&rsquo;s ideas than their feelings </p>
          <p>Anticipate or predict logical outcomes of choices </p>
          <p>Need to be treated fairly </p>
          <p>Tend to be firm and tough-minded</p>
          <p>Are able to reprimand or fire people when necessary</p>
          <p>May hurt people&rsquo;s feelings without knowing it</p>
          <p>Have a talent for analyzing a problem or situation </p>
          <p>Make decisions based on rational thought </p>
          <p>&nbsp;</p></td>
        <td width="1" valign="top"><p>&nbsp;</p></td>
        <td width="350" valign="top"><p align="center"><h4>Feeling types</h4></p>
            <p>Like harmony and will work to make it happen </p>
          <p>Respond to people&rsquo;s values as much as to their thoughts </p>
          <p>Are good at seeing the effects of choices on people </p>
          <p>Need occasional praise.</p>
          <p>Tend to be sympathetic </p>
          <p>Dislike telling people unpleasant things </p>
          <p>Enjoy pleasing people </p>
          <p>Take an interest in the person behind the job or idea </p>
          <p>Make decisions based on their heart </p>
          <p>&nbsp;</p></td>
      </tr>
      <tr>
        <td width="350" valign="top"><p align="center"><h4>Judging types</h4></p>
            <p>Work best when they can plan their work and follow the plan</p>
          <p>Like to get things settled and finished</p>
          <p>May decide things too quickly</p>
          <p>Have very set opinion </p>
          <p>May dislike interrupting one project for a more urgent one&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p>May start too many projects, having difficulty in finishing them</p>
          <p>Tend to be satisfied once they reach a judgment on a thing, situation, or person</p>
          <p>Want only the essentials needed to begin their work</p>
          <p>Schedule projects so that each step gets done on time</p>
          <p>Use lists as agendas for action</p>
          <p>&nbsp;</p></td>
        <td width="1" valign="top"><p>&nbsp;</p></td>
        <td width="350" valign="top"><p align="center"><h4>Perceptive types</h4></p>
            <p>Do not mind leaving things open for last-minute changes</p>
          <p>Adapt well to changing situations</p>
          <p>May have trouble making decisions</p>
          <p>Are flexible, adaptable and tolerant </p>
          <p>May postpone unpleasant jobs</p>
          <p>Want to know all about a new job</p>
          <p>Get a lot accomplished at the last minute under deadline<br />
            pressure</p>
          <p>Use lists as reminders of all the things they have to do</p></td>
      </tr>
    </tbody>
  </table>
</ul>
    
</div>

 <div id="links">
	<a href="<%=aszPortal%>/typesummaries.jsp">Types</a> | 
	<a href="<%=aszPortal%>/effectsministry.jsp">Effects</a> | 
	<a href="<%=aszPortal%>/fourpreferences.jsp">Preferences</a>
</div>

</div>

<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->