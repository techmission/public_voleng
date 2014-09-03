<!-- start JSP information login required -->
<%@ include file="/template_include/topjsploginreq1.inc" %>
<!-- end JSP information login required  -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<bean:define id="org" name="org" type="com.abrecorp.opensource.dataobj.OrganizationInfoDTO"/>

<%
boolean bNatlAssoc = false;

if(aszPortal.equals("/voleng")) portal="";
else if(session.getAttribute(tempPortal+"_type") != null ) if(session.getAttribute(tempPortal+"_type").toString().length() > 0) {
	portal = "";
	bNatlAssoc=true;
	aszOrgOrChurch="Association";
}

if (aszSubdomain.equalsIgnoreCase("www.christianvolunteering.org")){
        aszSubdomain = "ChristianVolunteering.org";
}else if (aszSubdomain.equalsIgnoreCase("www.ivolunteering.org")){
        aszSubdomain = "iVolunteering.org";
}
boolean portalMethod=false;

String aszMemberType = org.getORGMembertype();
if(aszMemberType.length()<1){
	aszMemberType = aCurrentUserObj.getUSPSiteUseType();
}
boolean bFaith=false;
if(aszHostID.equalsIgnoreCase("volengchurch") ||aszMemberType.equalsIgnoreCase("Church") ){
	bFaith=true;
	aszOrgOrChurch="Church";
	aszOrgOrChurchOpp="Ministry";
}
boolean bOrg=false;
if(aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
	bOrg=true;
}

String aszAdjective = "organizational";
if(aszOrgOrChurch.equalsIgnoreCase("Church")) aszAdjective = "church";

%>

<form action="<%=aszPortal%>/org.do<%if(aszPortal.length()>0){%>?method=processManagePortal<%}%>" name="orgForm"  method="post">
<input type="hidden" name="method" value="processManagePortal" />
<input type="hidden" name="orgnid" value="<%=org.getORGNID()%>" />
<input type="hidden" name="urlalias"  />
<input type="hidden" name="requesttype" value="<%=org.getRequestType()%>" />

<%
int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, 
		vidIntlVols=342;
int iTakesIntlVolsTID=22119, iDoesNotTakeIntlVolsTID=22120;
int iTemp=0;
String aszTakesIntlVolsTID=iTakesIntlVolsTID + "";
String aszDoesNotTakeIntlVolsTID=iDoesNotTakeIntlVolsTID + "";

// is this page being accessed as a site administrator or regular user?
boolean bAdminRole=false;
if(	aCurrentUserObj.getUserUID()==org.getSiteAdministratorUID()	&&
	aCurrentUserObj.getUSPAuthTokens().equals("siteadmin")
){
	if((aCurrentUserObj.getUserUID() > 0) ){
		bAdminRole=true;
	}
}

String aszPortalSectionDisplay="display:none;";
if(aszHostID.equalsIgnoreCase("volengchurch")	||
	aszHostID.equalsIgnoreCase("voleng1")		||
	aszHostID.equalsIgnoreCase("default")
){
	aszPortalSectionDisplay="display:inline;";
}

String aszHeaderTags = org.getPortalHeaderTags();
if(aszHeaderTags.length()<1){
	aszHeaderTags="<title>"+org.getORGOrgName()+" ChristianVolunteering.org: Matching Volunteers with Urban Ministries, Short Term Missions Trips, Church, Community Service & Virtual Online Volunteering Opportunities</title>";
}

String tempDom="www.ChristianVolunteering.org";
if(aszHostID.equalsIgnoreCase("volengchurch")){ 
	tempDom="www.ChurchVolunteering.org"; 
} 
%>


<script language="javascript">
 
function checkDirectoryNameFormat(dir){
	var element=dir.value;
	var subSection=element.substring(4,0);
	if( subSection=='http' || subSection=='www.' ){
		alert('This is a directory path added to our normal domain to create a portal site for you.  Please do not enter a full Web URL Address.');
		$('input:text[name=portalname]').val('<%=org.getPortalNameOrig()%>');
	}
}

 
function toggle_advanced_layout(){
	if (document.getElementById('advanced_layout').style.display=='none'){
		document.getElementById('advanced_layout').style.display='block';
		document.getElementById('iframe_banner').style.display='none';
		document.getElementById('advanced_layout_collapse').className = "collapsible";
	}else{
		document.getElementById('advanced_layout').style.display='none';
		document.getElementById('iframe_banner').style.display='block';
		document.getElementById('advanced_layout_collapse').className = "collapsed";
	}
}
 
function display_additionalinfocontent(){
	document.getElementById('advanced_layout').style.display='block';
	document.getElementById('advanced_layout_collapse').className = "collapsible";
}

function header_tags_clicked()
{ 
document.getElementById('portalheadertagsdiv').style.display='inline';
document.getElementById('portalheaderdiv').style.display='none';
document.getElementById('portalcssdiv').style.display='none';
document.getElementById('portalfooterdiv').style.display='none';

document.getElementById('portalheadertagslink').className = "active";
document.getElementById('portalheadertxtlink').className = "";
document.getElementById('portalcsslink').className = "";
document.getElementById('portalfooterlink').className = "";
}
function header_txt_clicked()
{ 
document.getElementById('portalheadertagsdiv').style.display='none';
document.getElementById('portalheaderdiv').style.display='inline';
document.getElementById('portalcssdiv').style.display='none';
document.getElementById('portalfooterdiv').style.display='none';

document.getElementById('portalheadertagslink').className = "";
document.getElementById('portalheadertxtlink').className = "active";
document.getElementById('portalcsslink').className = "";
document.getElementById('portalfooterlink').className = "";
}
function css_clicked()
{ 
document.getElementById('portalheadertagsdiv').style.display='none';
document.getElementById('portalheaderdiv').style.display='none';
document.getElementById('portalcssdiv').style.display='inline';
document.getElementById('portalfooterdiv').style.display='none';

document.getElementById('portalheadertagslink').className = "";
document.getElementById('portalheadertxtlink').className = "";
document.getElementById('portalcsslink').className = "active";
document.getElementById('portalfooterlink').className = "";
}
function footer_clicked()
{ 
document.getElementById('portalheadertagsdiv').style.display='none';
document.getElementById('portalheaderdiv').style.display='none';
document.getElementById('portalcssdiv').style.display='none';
document.getElementById('portalfooterdiv').style.display='inline';

document.getElementById('portalheadertagslink').className = "";
document.getElementById('portalheadertxtlink').className = "";
document.getElementById('portalcsslink').className = "";
document.getElementById('portalfooterlink').className = "active";
}

</script>


<style>
.portal-bold{
	font-weight:bold;
}
.portal #all_tabs{
	height:26px;
	padding:0 0 5px;
}
.portal #all_tabs a {
    padding: 0 5px;
}

#advanced_layout_collapse.collapsed .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-collapsed.png") no-repeat scroll 5px 50% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
    visibility: visible;
}
#advanced_layout_collapse.collapsible .fieldset-legend {
    background: url("http://www.urbanministry.org/misc/menu-expanded.png") no-repeat scroll 5px 65% transparent;
    color: #728DD4;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    margin-top: 10px;
    border-bottom: 1px solid #728DD4;
    padding-bottom: 3px;
    cursor:pointer;
}

<% if( !(aszNarrowTheme.equalsIgnoreCase("true"))  ) { %>
#form{
	margin-left: 20px;
}
<% } %>

#form .left-column-wide{
	float: left;
	padding: 3px;
	width: 255px;
	text-align:right;
}
#form .left-column{
	float: left;
	padding: 3px;
	width: auto;
	line-height:2em;
	text-align:right;
}
#form .right-column{
	float: left;
	padding: 3px;
}
#form .span-columns{
	padding: 0px;
}
#location #form .left-column{
	line-height:1em;
}
#location h2, #orgchurchinfo h2, #additionalinfo h2 {
	border-bottom: 1px solid #728DD4; 
	padding-bottom:3px;
}
.criticaltext {padding:3px;}

#progressbar {7px 0;}
#progressbar div{
    font-size: 14px;
    font-weight: bold;
		height:1.2em;
}
#progressbar td{
    font-size: 14px;
    font-weight: bold;
}
.accountboxon{
	background-color: #003366;
    font-size: 14px;
}
.accountboxoff{
	background-color: #999999;
    font-size: 14px;
}
.accounton{
	color: #003366;
}
.accountoff{
	color: #999999;
}
#breadcrumbs {margin-top:5px;}
</style>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <% aszEmptySearch="true";%>
  <div id="oppsearch">
	<% if(bAdminRole==true){ %>
	  <span id="title">Edit <%=aszOrgOrChurch%> Directory for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
	  <span id="title">Edit <%=aszOrgOrChurch%> Directory</span>
	<% } %>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent"<% if(b_includeLeftSidebar==true){ %> class="left-sidebar-org"<% } %> >

<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
	<% if(bAdminRole==true){ %>
  <span style="float: left;">Edit <%=aszOrgOrChurch%> Directory for <bean:write name='orgForm' property='orgname' /></span>
	<% }else{ %>
  <span style="float: left;">Edit <%=aszOrgOrChurch%> Directory</span>
	<% } %>
<% } %>
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; 
<% if(bNatlAssoc==false){ %>
	<a href="<%=aszPortal%>/orgmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> management</a> &gt; 
	<% if(bAdminRole==true){ %>
		<A href="<%=aszPortal%>/org.do?method=showAdminOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" ><%=aszOrgOrChurch.toLowerCase()%> management </a> &gt; 
	<% }else{ %>
	<A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" > <A href="<%=aszPortal%>/org.do?method=showOrgManagement&orgnid=<bean:write name='orgForm' property='orgnid' />" >manage this <%=aszOrgOrChurch.toLowerCase()%> </a> &gt;  
	<% } %>
<% }else{ %>
	<a href="<%=aszPortal%>/associationmanagement.jsp"><%=aszOrgOrChurch.toLowerCase()%> management</a> &gt; 
<% } %>
edit <%=aszOrgOrChurch.toLowerCase()%> directory</div>
<% if(aszNoSearchNav=="true"){ %>
</div>
<% } %>

<% if(b_includeLeftSidebar==true){ %>
	<%@ include file="/template_include/left_sidebar_org.inc" %>
<% } %>

  <div id="body">
<br>
<div id="form">

	<FONT color="red"><pre style="font-family:Arial,Helvetica,Verdana,sans-serif"><bean:write name="orgForm" property="errormsg" /></pre></FONT>
	
	
	<h2><%=org.getORGOrgName()%>
	</h2>
	<br clear="all" />

<div id="ask_portal_info span-columns">
<% if(aszPortal.length()<1){ %>

<div style="float:left; padding: 4px; margin: 0px 0px 10px 55px; width:473px; background-color: #83A2F4;">
<object id="scPlayer"  width="473" height="355" type="application/x-shockwave-flash" data="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/scplayer.swf" ><param name="movie" value="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/scplayer.swf" /><param name="quality" value="high" /><param name="bgcolor" value="#FFFFFF" /><param name="flashVars" value="thumb=http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/FirstFrame1.jpg&containerwidth=983&containerheight=737&autohide=true&autostart=false&loop=false&showendscreen=true&showsearch=false&showstartscreen=true&tocdoc=left&xmp=sc.xmp&content=http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/How%20to%20Create%20a%20Church%20Community%20Volunteering%20Website%20Using%20ChristianVolunteering.org.mp4&blurover=false" /><param name="allowFullScreen" value="true" /><param name="scale" value="showall" /><param name="allowScriptAccess" value="always" /><param name="base" value="http://content.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/" /><iframe type="text/html" frameborder="0" scrolling="no" style="overflow:hidden;" src="http://www.screencast.com/users/techmission/folders/Default/media/fb1953df-c954-4191-9930-d3c80f615c8a/embed" height="737" width="983" ></iframe></object>
</div>
<br clear="all" />
<br clear="all" />

<% } %>
	<div class="portal-bold span-columns" id="directory_div">
		Enter a name for your Volunteer Directory Portal on ChristianVolunteering branded for your <%=aszOrgOrChurch%>:
	</div>
	<br clear="all" />
</div>

<div id="portal_info">

<% 
String aszDirectoryName = aszPortal;
try{
	if(aszDirectoryName.length() < 1){
		aszDirectoryName = org.getPortalNameOrig();
	}else if(aszDirectoryName.startsWith("/voleng")){
		aszDirectoryName = aszDirectoryName.substring(8);
	}else if(aszDirectoryName.startsWith("/")){
		aszDirectoryName = aszDirectoryName.substring(1);
	}
}catch(IndexOutOfBoundsException e){
	aszDirectoryName = org.getPortalNameOrig();
}catch(Exception ex){
	aszDirectoryName = org.getPortalNameOrig();
}
%>
	<div class="left-column">
		Directory Name for your Portal:
	</div>
	<div class="right-column">
		<%=tempDom%>/<input type="text" name="portalname"  value="<%=aszDirectoryName%>" onblur="checkDirectoryNameFormat(this)"/> 
	</div>
	<br clear="all" />
<br />

	
<div id="iframe_banner" style="display:inline;">
	<div>
		<iframe id="content" style="WIDTH: 500; HEIGHT: 175" name="content" src="<%=aszPortal%>/org.do?method=showPortalBannerPost" frameborder="0" width="500" scrolling="no" height="175"></iframe>
	</div>
	<br clear="all" />
</div>	

		<div id="advanced_layout_collapse" onclick="toggle_advanced_layout()" class=" collapsed">
			<legend class="collapse-processed">
				<span class="fieldset-legend">&nbsp;&nbsp;&nbsp;&nbsp;Or, use our advanced layout settings</span>
			</legend>
		</div>
	
<div id="advanced_layout" style="display:none;">
<br>
<div id="all_tabs" class="tabs1">
	<div id="portalheadertagslink" class="" style="display:none;">
		<a class="tab-1" href="#portalheadertagsdiv" onclick="header_tags_clicked()"><span>Header Tags HTML</span></a>
	</div>
	<div id="portalheadertxtlink" class="active">
		<a class="tab-1" href="#portalheaderdiv" onclick="header_txt_clicked()"><span>Header/Banner HTML text</span></a>
	</div>
	<div id="portalcsslink" class="">
		<a class="tab-1" href="#portalcssdiv" onclick="css_clicked()"><span>CSS</span></a>
	</div>
	<div id="portalfooterlink" class="">
		<a class="tab-1" href="#portalfooterdiv" onclick="footer_clicked()"><span>Footer</span></a>
	</div>
</div>
<hr size="2" color="#4d4d4d" style=" margin-top: -7px; //margin-top:-13px; margin-left:0; margin-bottom:10px; width:600px;">


	<div id="portalheadertagsdiv" style="display:none;">
<br>
Edit information that shows in your HTML header, such as the 
<a href="http://www.w3.org/TR/html40/struct/global.html#edef-TITLE" style="text-decoration:none;">&lt;title&gt;</a> tag here
<br><br><br>
<textarea name="portalheadertags" rows="10" cols="100" styleClass="textinputwhite"/><%=aszHeaderTags%></textarea>
	</div>
	
	<div id="portalheaderdiv">
Enter the HTML for the header/banner you would like to show on your portal:<br>
<ul><li>If left blank, we will by default show the banner logo image you uploaded<br>(or name of your Organization), and our search form</li></ul>
<textarea name="portalheader" rows="10" cols="100" styleClass="textinputwhite"/><%=org.getPortalHeader()%></textarea>
	</div>
	
	<div id="portalcssdiv" style="display:none;">
<br>
Enter any custom CSS you would like for your portal site here<br>(we have provided you with a few of our basic element names)
<br><br>
<textarea name="portalcss" rows="10" cols="100" styleClass="textinputwhite"/><%=org.getPortalCSS()%></textarea>
	</div>
	
	<div id="portalfooterdiv" style="display:none;">
<br>
Enter information (generally links, etc) you would like to have in your portal's footer here
<br><br><br>
<textarea name="portalfooter" rows="10" cols="100" styleClass="textinputwhite"/><%=org.getPortalFooter()%></textarea>
	</div>


</div><!-- end div advanced_layout -->



	
</div>	
<br>
	<div class="left-column-wide">
	</div>
	<div class="right-column">
		<INPUT class=submit type=submit value=Continue>
	</div>

</div> <!-- end form div -->
</div> <!-- end body div -->
</div> <!-- end maincontent div -->

</form>


<!-- start sidebar information -->
<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->
<%@ include file="/template_include/google_adwords_orgpage.inc" %>
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
