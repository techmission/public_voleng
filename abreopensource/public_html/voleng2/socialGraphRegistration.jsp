<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>

<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Christian Social Graph</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Sitemap</span>
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
<span style="float: left;">Sitemap </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; sitemap </div>

</div>
<% } %>


<div id="body">

<% 
  String userType = (String) request.getAttribute("userType"); 
  OrganizationInfoDTO org = (OrganizationInfoDTO) request.getAttribute("org");
  
  String IAgreeDataUsageChecked = "";
  if(org.getSocialGraphIAgreeDataUsage() > 0) 
  	IAgreeDataUsageChecked = "CHECKED";
  
  String IAgreeCachingChecked = "";
  if(org.getSocialGraphIAgreeCaching() > 0)
  	IAgreeCachingChecked = "CHECKED";
	
  String IAgreeLogoChecked = "";
  if(org.getSocialGraphIAgreeLogo() > 0)
  	IAgreeLogoChecked = "CHECKED";
  
  String IAgreeFinalChecked = "";
  if(org.getSocialGraphIAgreeFinal() > 0)
  	IAgreeFinalChecked = "CHECKED";
%>

<% if(userType.equals("notLoggedIn") || userType.equals("notOrgAdmin")) { %>
  <span style="color:#FF0000;"><br/>You must first create an organization account on ChristianVolunteering.org and complete your organization profile. Once that is complete, then revisit this page once you are  logged in.</span>
<% } else if(userType.equals("alreadySigned")) { %>
 <br/>
 Thank you for applying for an API key for ChristianVolunteering.org.s Christian Social Graph.  Someone should be contacting you in the next few weeks about the status of your application. If your application is approved, then you should receive your API key by E-mail.  If no one contacts you in the next few weeks, you can send an request on the status of your API application to <a href="mailto:info@christianvolunteering.org">info@christianvolunteering.org</a>. 
<% } else { %>
  <h1>Apply for ChristianVolunteering.org’s Christian Social Graph API Key</h1>

  <b>Please Digitally sign the contract below.</b>

  <form name="social_graph_registration" method="post" action="<%=aszPortal%>/org.do">
  
    <div>
	  <font color="red">
	  	<%=org.getErrorMsg()%>
	  </font>
	</div>
  
    <input name="method" type="hidden" value="processSocialGraphRegistration" />

  <div id="content-header">

      	    
      	          	      <h2 class="title">Christian Social Graph API Terms of Service</h1>
      	    						
      	          	    
      	     

      	    	
      	  </div> <!-- /#content-header -->

      	<div id="content-area"> <!-- CONTENT AREA -->

	<div class="node node-type-internal_page" id="node-440528">
		<div class="node-inner">
	
	  	<div class="meta">
	  			  	</div>	  	

		<div class="content">
	  	  <p>THE TERMS AND CONDITIONS SET FORTH BELOW (THE "TERMS") GOVERN YOUR PARTICIPATION AS A PUBLISHER ("PUBLISHER") IN THE TECHMISSION, INC. ("TECHMISSION") PUBLISHER PROGRAM, WHICH INCLUDES USE OF THE TECHMISSION CHRISTIAN SOCIAL GRAPH HOSTED TECHMISSION CHRISTIAN SOCIAL GRAPH API AND THE TECHMISSION EMBEDABLE OR REBRANDABLE SEARCH. THESE TERMS ARE LEGALLY BINDING ON YOU. IF YOU DO NOT AGREE WITH ANY OF THE TERMS, DO NOT USE CHRISTIAN SOCIAL GRAPH. YOUR USE OF CHRISTIAN SOCIAL GRAPH SHALL BE DEEMED TO BE YOUR AGREEMENT TO ABIDE BY EACH OF THE TERMS SET FORTH BELOW.</p>

<p>TECHMISSION SHALL HAVE ABSOLUTE DISCRETION AS TO WHETHER OR NOT IT ACCEPTS A CHRISTIAN SOCIAL GRAPH PARTICIPANT. IF YOU FAIL TO COMPLY WITH THE TERMS, TECHMISSION RESERVES THE RIGHT TO DISABLE YOUR ACCOUNT AND DISCONTINUE YOUR CHRISTIAN SOCIAL GRAPH PARTICIPATION. YOU MAY NOT BE ELIGIBLE FOR FURTHER CHRISTIAN SOCIAL GRAPH PARTICIPATION.</p>

<p>YOU AGREE THAT TECHMISSION MAY MAKE CHANGES TO CHRISTIAN SOCIAL GRAPH AND/OR REVISE THE TERMS AT ANY TIME WITHOUT NOTICE. TECHMISSION WILL NOTIFY YOU OF SUCH REVISIONS BY POSTING AN UPDATED VERSION OF THE TERMS ON THE TECHMISSION WEBSITE (THE "SITE"). YOU ARE RESPONSIBLE FOR REGULARLY REVIEWING THE TERMS. YOUR CONTINUED USE OF CHRISTIAN SOCIAL GRAPH SHALL CONSTITUTE YOUR CONSENT TO SUCH CHANGES. You can review the most current version of the Terms of Service online at any time at <a href="http://www.christianvolunteering.org/apitos.jsp">http://www.christianvolunteering.org/apitos.jsp</a></p>

<p>PUBLISHER CONTENT AND CONDUCT</p>

<p>In order to use Christian Social Graph, you must first register for an account on ChristianVolunteering.org. All such provided information must be correct and current. You agree to comply with specifications provided by TechMission from time to time to enable proper delivery, display, tracking and reporting of Christian Social Graph features in connection with your Christian Social Graph page(s), including without limitation by not modifying the code or programming provided to you by TechMission in any way.</p>

<p>You agree to prominently display the link to your Christian Social Graph page on your website and you will make best efforts to promote the listings in your Christian Social Graph page including regularly referring to the listings in your blog and/or website content. Your Christian Social Graph page(s) should be easy for users to navigate, and may not change user preferences, redirect users to unwanted websites, initiate downloads, include malware or contain pop-ups or pop-unders that interfere with site navigation.</p>

<p>You agree not to use Christian Social Graph or other TechMission provided content on any websites that contain:</p>

<ul><li>Information or language that is abusive, defamatory, discriminatory, hateful, obscene, vulgar, sexually-orientated, threatening or otherwise objectionable;</li><li>Content that can be reasonably construed to be offensive and/or illicit, examples of which may pertain to gambling, drugs, alcohol, firearms and/or tobacco;</li><li>Attempts to obtain information or other unsolicited or unwanted web activity, including, but not limited to, pyramid schemes, phishing attempts and spamming;</li><li>Any other content that is in violation of any applicable law or regulation, including, without limitation, the infringement of any legal rights of others or violates the privacy, publicity rights or trademark rights of any other person.</li></ul>

<p>The API may be used in connection with a software application or website according to the terms listed in this agreement. In order to use the API, you must obtain an API key from this form, and TechMission may block requests with an invalid key. </p>

<p><strong>PUBLICITY AND CHRISTIANVOLUNTEERING.ORG DESIGNATION</strong></p>

<p>You agree that TechMission may include reference to you as a user of Christian Social Graph in presentations, marketing materials, press releases and other parts of the Site. All pages using Christian Social Graph shall be labeled with the appropriate ChristianVolunteering.org attribution and you agree not to remove or alter any such pre-assigned designations. Additional appropriate designations are specified below, and you agree not to remove or alter such designations. The trademarks, service marks, brands, names, logos and designs of TechMission or its partners ("Trademarks") used on the Site are the property of TechMission or their respective owners. Unless expressly authorized in writing by TechMission, you may not use such Trademarks for any purpose, and nothing contained on the Site grants by implication, waiver, estoppel or otherwise, any right to use such Trademarks.</p>

<p>The goal and indent of the Christian Social Graph is to maximize the availability of data of interest to Christians in order to better connect the Body of Christ and impact the world. One major incentive for data providers to share data is to drive traffic to their websites (similar to RSS newsfeeds) and to match more volunteers. As such, the data provided is intended to be used similar to RSS feeds in that it must always include a prominent link to go to the original source of the data as described below.  </p>

<p><strong>TERMINATION</strong></p>

<p>Either you or TechMission may terminate your use of Christian Social Graph at any time and for any reason. Upon termination of your use of Christian Social Graph, you will immediately remove all Christian Social Graph information and insertion codes from your page(s). Inactive accounts will be closed automatically by TechMission.</p>

<p><strong>CONFIDENTIALITY</strong></p>

<p>You shall not disclose TechMission Confidential Information without TechMission's prior written consent. "TechMission Confidential Information" includes without limitation: (i) all TechMission software, technology, programming, specifications, materials, guidelines and documentation relating to Christian Social Graph; (ii) click-through rates or other performance statistics relating to Christian Social Graph provided to you by TechMission; (iii) any information designated in writing by TechMission as "Confidential" or an equivalent designation and (iv) any other information you know or reasonably should know is considered confidential by TechMission.</p>

<p><strong>DISCLAIMER OF WARRANTIES AND LIMITATION OF LIABILITY</strong></p>

<p>TECHMISSION'S PROVISION OF THE CHRISTIAN SOCIAL GRAPH PROGRAM IS PROVIDED TO YOU WITHOUT ANY WARRANTY OF ANY KIND AND ON AN "AS IS" AND "WHERE-IS" BASIS. ALL CONDITIONS, REPRESENTATIONS AND WARRANTIES, WHETHER EXPRESS, IMPLIED, STATUTORY OR OTHERWISE, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT OF THIRD PARTY RIGHTS, ARE HEREBY DISCLAIMED.</p>

<p>TECHMISSION MAKES NO WARRANTY THAT (i) THE CHRISTIAN SOCIAL GRAPH SERVICE WILL MEET YOUR REQUIREMENTS; (ii) THE CHRISTIAN SOCIAL GRAPH SERVICE WILL BE UNINTERRUPTED, TIMELY, SECURE OR ERROR-FREE; (iii) THE RESULTS THAT MAY BE OBTAINED FROM THE USE OF THE CHRISTIAN SOCIAL GRAPH SERVICE WILL BE ACCURATE OR RELIABLE AND (iv) ANY ERRORS IN THE CHRISTIAN SOCIAL GRAPH SERVICE WILL BE CORRECTED.</p>

<p>TECHMISSION SHALL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, CONSEQUENTIAL, SPECIAL, INCIDENTAL OR OTHER DAMAGES INCURRED BY YOU FROM USE OF THE CHRISTIAN SOCIAL GRAPH SERVICE, WHETHER BASED IN CONTRACT (INCLUDING FUNDAMENTAL BREACH), TORT (INCLUDING NEGLIGENCE) OR OTHERWISE. IN NO EVENT SHALL TECHMISSION'S AGGREGATE LIABILITY EXCEED THE AMOUNT OF FEES THEN DUE TO YOU AS DETERMINED PURSUANT TO THESE TERMS.</p>

<p><strong>INDEMNIFICATION</strong></p>

<p>You agree to defend, indemnify and hold harmless TechMission and its affiliates, representatives, partners, agents and employees from and against any and all liabilities, claims, costs and expenses, including attorneys' fees, that arise out of or in connection with your use of the Christian Social Graph service or breach of these Terms.</p>

<p><strong>GENERAL</strong></p>

<p>TechMission controls and operates the Christian Social Graph service from its headquarters in the United States and makes no representation that Christian Social Graph is appropriate or will be available for use in locations other than the United States. If you use Christian Social Graph from outside the United States, you are entirely responsible for compliance with applicable local laws. These Terms will be governed by Massachusetts law and controlling United States federal law, without regard to the choice or conflicts of law provisions of any jurisdiction. You shall bring all disputes, actions, claims, or causes of action related to these Terms or in connection with the Christian Social Graph service only in the federal and state courts located in Santa Clara county, California. If any provision of these Terms is held by a court of competent jurisdiction to be contrary to law, then such provision(s) shall be construed, as nearly as possible, to reflect the intentions of the parties, with all other provisions remaining in full force and effect. The failure of TechMission to enforce any right or provision in these Terms shall not constitute a waiver of such right or provision unless acknowledged and agreed to by TechMission in writing. No joint venture, partnership, employment or agency relationship exists between you and TechMission. These Terms comprise the entire agreement between you and TechMission and supersede all prior or contemporaneous negotiations, discussions or agreements, if any, between the parties regarding the subject matter contained herein.</p>

<p>For additional information, you may contact us at:</p>

<p>TechMission, Inc.<br />48 Pleasant Street<br />Dorchester, MA 02125</p>

<hr/>
    <br/>

    <div>
		<b>Please explain how would you like to use the Christian Social Graph API? What websites would it the data be on?</b>
    </div> 
	<textarea cols="100" rows="5" name="social_graph_explanation"><%=org.getSocialGraphExplanation()%></textarea>
	
	<br/><br/>

<p><input name="social_graph_I_agree_data_usage" type="checkbox" value="1" <%=IAgreeDataUsageChecked%> /><b>I agree that my organization will not use the data provided through this API:</b></p>

<ul><li>For mailing lists</li><li>To re-sell the data, although it may be used programmatically in commercial websites and applications;</li><li>For any reason that violates the overall goals of the Christian Social Graph or Mission of TechMission.</li></ul>

<p><input name="social_graph_I_agree_caching" type="checkbox" value="1" <%=IAgreeCachingChecked%> /><b>I agree to the following terms of caching of data provided by this API:</b></p>

<ul><li>Data provided by ChristianVolunteering.org may only be cached for up to 1 week.  This is to ensure that the data represented by partners is as up to date as possible.</li><li>Some partners may only allow for data caching for 24 hours.  Other partners may have additional requirements such as including javascript code or their own branding references.  Where possible TechMission will work to make it possible that you can just use our API, but in some cases you may be required to develop separate API agreements with outside partners. </li></ul>

<p><input name="social_graph_I_agree_logo" type="checkbox" value="1" <%=IAgreeLogoChecked%>/><b>I agree to display the ChristianVolunteering.org logo on pages using data provided through this API as following:</b></p>

<ul><li>Search results listings should include a small ChristianVolunteering.org logo by each listing as shown below.</li></ul>

<p><img alt="http://www.christianvolunteering.org/imgs/christianvolunteering-mini.png" src="http://www.christianvolunteering.org/imgs/christianvolunteering-mini.png" /></p>

<ul><li>Detail results pages should include a Powered by ChristianVolunteering.org logo as shown below.</li></ul>

<p><a href="http://www.christianvolunteering.org/"><img alt="Powered by ChristianVolunteering" src="http://www.christianvolunteering.org/imgs/cv_logo_powered_by.gif" style="border-width:0pt;border-style:solid;float:left;" /></a><br /> </p>

<div style="clear:both;"></div>

<ul><li>There should be a prominent button or link on listings that links to the original source. Example text includes “Volunteer Now,” “Find Out More,” or “See Full Listing on ChristianVolunteering.org”.</li></ul>

<p> </p>	  	</div> </div></div></div>

	<p><input name="social_graph_I_agree_final" type="checkbox" value="1" <%=IAgreeFinalChecked%> /><b>I certify that I have the legal authority to sign legal documents for my organization. I agree to the above terms and conditions.</b></p>

	<div>
      <b>Digitally Signed by</b> 
	</div>
	<input name="social_graph_signature" type="text" size=35 value="<%=org.getSocialGraphSignature()%>" /><b> &nbsp; <%=DateFormat.getDateInstance(DateFormat.LONG).format(new Date())%></b>

    <br/><br/>

    <input type="submit" value="Apply for API Key" />
  </form>
<% } %>           

</div>

</div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
