<%//@ include file="/template/topjspnologin1.inc" %>



<%
/**
ArrayList aStateList = new  ArrayList ( 2 );
ArrayList aCountryList = new  ArrayList ( 2 );
ArrayList aServiceList = new  ArrayList ( 2 );
ArrayList aProgramList = new  ArrayList ( 2 );
ArrayList askillsList = new  ArrayList ( 2 );
ArrayList afiliationList = new  ArrayList ( 2 );
ArrayList acreedList = new  ArrayList ( 2 );
ArrayList apartnersList = new  ArrayList ( 2 );
ArrayList aDurationList = new  ArrayList ( 2 );
ArrayList aLocalAffilList = new  ArrayList ( 2 );
ArrayList aRegionList = new  ArrayList ( 2 );
ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO( request );
aCodes.getStateInList( aStateList, 101 );
aCodes.getCountryList( aCountryList, 101 );
aCodes.getAppCodeList( aServiceList, 161 );
aCodes.getAppCodeList( aProgramList, 172 );
aCodes.getAppCodeList( afiliationList, 163 );
aCodes.getAppCodeList( acreedList, 165 );
aCodes.getAppCodeList( apartnersList, 167 );
aCodes.getAppCodeList( askillsList, 169 );
aCodes.getAppCodeListID( aDurationList, 174 );
aCodes.getAppCodeList( aLocalAffilList, 175 );
aCodes.getAppCodeList( aRegionList, 176 );

String aszLocalAffil = "display:none;";
if((aszHostID.equalsIgnoreCase( "volengboston" )) || (aszHostID.equalsIgnoreCase( "volengnewengland" ))){ 
  aszLocalAffil = "display:inline;";
}

String search=null;

**/
%>

<script language="javascript">

function topopp_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='inline';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "active";
document.getElementById('org').className = "";
document.getElementById('stm').className = "";
}

function toporg_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "";
document.getElementById('org').className= "active";
document.getElementById('stm').className = "";
}


function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}

</script>

<style>

a#opp, a#org, a#stm, a#cont {
cursor:pointer; 
text-decoration:none; 
color:#000000; 
font-size:1em;
//font-size:12px; 
font-weight: normal;
background:transparent url(http://www.urbanministry.org/themes/urbanministry/tabs-off.png) no-repeat; 
border-right: 2px solid #B7884E; 
padding:3px 5px 0;
font-weight: bold;
color:#66332B;
}
a#opp.active, a#org.active, a#stm.active, a#cont.active {
background:transparent url(http://www.urbanministry.org/themes/urbanministry/tabs-off.png) no-repeat; 
border-right: 2px solid #B7884E;
font-weight: bold;
}
</style>

<% if ( (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengesa" )) ){ %>
	<div style="float:left; margin: 0px 0px 0px 10px; width: 285px;">
<% } else if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
                (aszHostID.equalsIgnoreCase( "volengchurch" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
                (aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:left; margin: 0px 0px 5px 10px; width: 485px;">
<% } else { %>
	<div style="float:left; margin: 0px 0px 5px 10px; width: 300px;">
<% } %>


<% if (aszHostID.equalsIgnoreCase( "volengcatholic" )){ %>
	<h2>Catholic Volunteering</h2>
<% } else if (aszHostID.equalsIgnoreCase( "volengchicago" )){ %>
	<h2>Chicago Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengdenver" )) { %>
	<h2>Denver Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengindy" )) { %>
	<h2>Indianapolis Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volenglosangeles" )) { %>
	<h2>Los Angeles Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengmiami" )) { %>
	<h2>Miami Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewengland" )) { %>
	<h2>New England Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengnewyork" )) { %>
	<h2>New York Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengseattle" )) { %>
	<h2>Seattle Christian Volunteering</h2>
<% } else if(aszHostID.equalsIgnoreCase( "volengtwincities" )) { %>
	<h2>Twin Cities Christian Volunteering</h2>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengccda" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) 
){ %>
	<table><tr><td>
	<A style="text-decoration: none" HREF="<%=request.getContextPath()%>/search.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else if( 	
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) 
){ %>
	<table height=40 border=0 padding=0><tr><td>
	<A style="text-decoration: none" HREF="<%=request.getContextPath()%>/search.jsp"><h2>search</h2></A></td>
	<td width=325></td><td>
	<b><a href="/login.jsp">login</a></b></td></tr></table>
<% } else { %>
	<A style="text-decoration: none" HREF="<%=request.getContextPath()%>/search.jsp"><h2>search</h2></A>
<% } %>


<% if(aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")){ // show stm first for shorttermmissions page%>
	
	
	<div id="short-term" style="display:inline">
	<a id="stm" class="active" onClick="topstm_search()" name="method" value="processOppSearchAll">Short-term Missions</a>
	</div> 
	
	<div id="vol-opp" style="display:inline">
	<a id="opp" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities</a>
	</div>
	
	<div id="organizations" style="display:inline">
	<a id="org" onClick="toporg_search()" name="method" value="processOrgSearchAll">Organizations</a>
	</div>

<% } else { %>
	<div id ="vol-opp" style="display:inline">
	<a id="opp" class="active" onClick="topopp_search()" name="method" value="processOppSearchAll"> Volunteer Opportunities</a>
	</div>
	
<div id="organizations" style="display:inline">
<a id="org" onClick="toporg_search()" name="method" value="processOrgSearchAll">Organizations</a>
</div>
	
	
<% } %>
<hr size="2" color="#B7884E" width="100%" style=" margin-top: 0px;" />


<!-- chrisvol - oppsearch / orgsearch -->

<div id="cvsearch" style="display:inline">



<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
                (aszHostID.equalsIgnoreCase( "volengchurch" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 20px; width: 465px;">
<% } else { %>
	<div>
<% } %>


<% if(aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")){ // show stm first for shorttermmissions page%>
	<div id="cvoppsearch" style="display:none">
<% } else { %>
	<div id="cvoppsearch" style="display:inline">
<% } %>

<form name="homesearch" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="partner" value="Salvation Army">
<% } %>

<table style="font-size:12px;" border="0"><tr><td align="right">
<strong>Service Area</strong>
</td><td colspan="2">
		<SELECT id="category1" name="category1" size="1" style="z-index: 0;" class="smalldropdown">
			<option value="" selected="selected">Select Categories</option> 
			<%
			for(int index=0; index < aServiceList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
				if(null == aAppCode) continue;
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>
		</SELECT>
</td></tr><tr><td align="right">

<strong>Country</strong></td><td colspan="2">
	<SELECT id="country" name="country" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		aszTemp= "US";
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
					out.print(" selected=selected ");
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postalopp').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postalopp').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr>
		 <tr><td align="right">
<div id="postallabelopp"><strong>Postal Code</strong></div>
      </td><td style="width:80px;"><div id="postalopp"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
</td><td align="left"><strong>for Groups</strong><input type="checkbox" styleClass="check" value="Great For Groups" id="focusare4" name="focusare4" 
<% if((aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) || (aszHostID.equalsIgnoreCase("volengchurch")) ){ 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> /></td>
</tr>
		 <tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs_um/button_search-gray.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>

</div><!-- end cvoppsearch -->





<div id="cvorgsearch" style="display:none">

<form name="homesearch" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOrgSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="">
<% if (aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) { %>
<input type="hidden" name="partner" value="Salvation Army">
<% } %>

<table style="font-size:12px;" border="0"><tr><td align="right">
<strong>Program Type</strong>
</td><td colspan="2">
	<SELECT id="programtype" name="programtype" size="1" style="z-index: 0;" class="smalldropdown">
		<option value="" selected="selected"></option> 
		<%
		for(int index=0; index < aProgramList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aProgramList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
			out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
		}
		%>
	</SELECT> 
</td></tr><tr><td align="right">
<strong>Region</strong>
</td><td colspan="2">
	<SELECT id="region" name="region" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		for(int index=0; index < aRegionList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
			out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
		}
		%>
         </SELECT></td></tr><tr><td colspan=3>
</td></tr>
<tr>
<td align="right"><div id="postallabelorg"><strong>Postal Code</strong></div></td>
<td colspan="2"><div id="postalorg"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div></td>
</tr>
<tr><td align="right">

<strong>Country</strong></td><td colspan="2">
	<SELECT id="country" name="country" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		aszTemp= "US";
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
					out.print(" selected=selected ");
					out.print( "onClick=\"javascript:document.getElementById('postallabelorg').style.display='inline';javascript:document.getElementById('postalorg').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelorg').style.display='none';javascript:document.getElementById('postalorg').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr>
<tr><td align="right">
</td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs_um/button_search-gray.gif" alt="Search" width="59" height="24" border="0" /></a>
</td></tr></table>
</form>
</div><!-- end cvorgsearch -->







<% if(aszPath.equalsIgnoreCase("/voleng2/short_term_missions.jsp")){ // show stm first for shorttermmissions page%>
	<div id="cvstmsearch" style="display:inline">
<% } else { %>
	<div id="cvstmsearch" style="display:none">
<% } %>

<form name="homesearch" method="get" action="<%=request.getContextPath()%>/oppsrch.do">
<input type="hidden" name="method" value="processOppSearchAll">
<input type="hidden" name="distance" value="City">
<input type="hidden" name="voltype" value="Short-term Missions / Volunteer Internship">

<table style="font-size:12px;" border="0"><tr><td align="right">
<strong>Service Area</strong>
</td><td colspan="2">
		<SELECT id="category1" name="category1" size="1" style="z-index: 0;" class="smalldropdown">
			<option value="" selected="selected">Select Categories</option> 
			<%
			for(int index=0; index < aServiceList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aServiceList.get(index);
				if(null == aAppCode) continue;
				out.println(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
				out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
			}
			%>
		</SELECT>
</td></tr><tr><td align="right">
<strong>Region</strong>
</td><td colspan="2">
	<SELECT id="region" name="region" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		for(int index=0; index < aRegionList.size(); index++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aRegionList.get(index);
			if(null == aAppCode) continue;
			out.print(" <option value=\"" + aAppCode.getAPCDisplay() + "\" " );
			out.println(" >" + aAppCode.getAPCDisplay() + "</option> ");
		}
		%>
         </SELECT></td></tr><tr><td align="right">
<div id="postallabelstm"><strong>Postal Code</strong></div>
      </td><td width="100%"><div id="postalstm" style="display: inline;"><input name="postalcode" type="text" id="postalcode" style="width:60px;" size="20" /></div>
	  <div style="display: inline; margin-left: 10px;">
	  <strong>Length of Trip</strong>
	  <select id="duration" name="duration" size="1" style="z-index: 0;" class="smalldropdown">
	  <option value=""></option>
	  <option value="Less than a week">Less than a week</option>
	  <option value="1 to 2 weeks">1 to 2 weeks</option>
	  <option value="3 to 4 weeks">3 to 4 weeks</option>
	  <option value="1 to 2 months">1 to 2 months</option>
	  <option value="summer">Summer</option>
	  <option value="3 to 5 months">3 to 5 months</option>
	  <option value="one year">1 year</option>
	  <option value="1 to 2 years">1 to 2 years</option>
	  </select>
	  </div>
</td>
</tr><tr><td align="right">

<strong>Country</strong></td><td colspan="2">
	<SELECT id="country" name="country" size="1" style="z-index: 0;" class="smalldropdown">
	 <OPTION value=""></OPTION>
		<%
		aszTemp= "US";
		if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				out.print(" <option value=\"" + aAppCode.getCTRIso() + "\" " );
				if(aszOptRqCode.equalsIgnoreCase( aszTemp ) ){
					//out.print(" selected=selected ");
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='inline';javascript:document.getElementById('postalopp').style.display='inline';\" ");
				} else {
					out.print( "onClick=\"javascript:document.getElementById('postallabelopp').style.display='none';javascript:document.getElementById('postalopp').style.display='none';\" ");
				}
				out.println(" >" + aAppCode.getCTRPrintableName() + "</option> ");
			}
		}
		%>
         </SELECT></td></tr><tr><td></td><td>
<input name="imageField" type="image" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities" src="/imgs/button_home_search_beige.gif" alt="Search" width="59" height="24" border="0" /></a>
<div style="margin-top: -20px; margin-left: 60px;">&nbsp;for Groups<input type="checkbox" styleClass="check" value="Great For Groups" id="focusare4" name="focusare4" 
<% if((aszPath.equalsIgnoreCase("/voleng2/church_volunteer.jsp")) || (aszHostID.equalsIgnoreCase("volengchurch")) ){ 
					// make box selected on church volunteer page %>
checked="checked"
<% } else { } %> /> with Room &amp; Board <input type="checkbox" styleClass="check" value="8106" id="roomboard" name="roomboard" /> with Stipend <input type="checkbox" styleClass="check" value="8108" id="stipend" name="stipend" /></span></div></td></tr></table>
</form>
<b><a href="http://www.christianvolunteering.org/shorttermmissions.jsp" title="browse Christian short term urban missions, social service ministry internship volunteer opportunities" style="float: right;">browse opportunities</a></b>
</div><!-- end cvstmsearch -->






<b><a href="<%=request.getContextPath()%>/advancedsearch.jsp" title="search Christian volunteer,church volunteer, short term urban missions, virtual volunteer, and urban ministry service opportunities">advanced
search</a></b>










</div>
<div class="cleardiv"></div>
</div>



<!-- urbanministry search -->

<div id="searchumcontent" style="display:none">



<% if (  (aszHostID.equalsIgnoreCase( "voleng1" )) ||
		(aszHostID.equalsIgnoreCase( "default" )) ||
		(aszHostID.equalsIgnoreCase( "volengcatholic" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospel" )) ||
		(aszHostID.equalsIgnoreCase( "volenggospelcom" )) ||
		(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
		(aszHostID.equalsIgnoreCase( "volengchicago" )) ||
		(aszHostID.equalsIgnoreCase( "volengdenver" )) ||
		(aszHostID.equalsIgnoreCase( "volengindy" )) ||
		(aszHostID.equalsIgnoreCase( "volenglosangeles" )) ||
		(aszHostID.equalsIgnoreCase( "volengmiami" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewengland" )) ||
		(aszHostID.equalsIgnoreCase( "volengnewyork" )) ||
		(aszHostID.equalsIgnoreCase( "volengseattle" )) ||
		(aszHostID.equalsIgnoreCase( "volengtwincities" )) 
){ %>
	<div style="float:center; margin: 0px 0px 5px 20px; width: 350px;">
<% } else { %>
	<div>
<% } %>



Search thousands of articles, sermons and videos<br/>on volunteering, urban ministry, and social justice:
<br/><br/>

<!--begin Google Custom Search -->
<form name="search-form" class="search-form" action="http://www.urbanministry.org/search/google"  accept-charset="UTF-8" method="get" id="google-cse-results-searchbox-form">

<input type="hidden" name="cx" id="edit-cx" value="017405804136166701815:nc9jskbtk8y"  />
<input type="hidden" name="cof" id="edit-cof" value="FORID:11"  />
<input type="text" maxlength="128" class="textinputwhite" style="width:220px;" name="query" id="edit-query"  value="Enter Keyword" onClick="clearText(this)" />
<input type="submit" name="op" id="edit-sa" value="Search" />
<input type="hidden" name="form_token" id="edit-google-cse-results-searchbox-form-form-token" value="f1596786f0691ac334237131d60e5654"  />
<input type="hidden" name="form_id" id="edit-google-cse-results-searchbox-form" value="google_cse_results_searchbox_form"  />
</form>
<div id="search_results"><noscript><a href="http://www.google.com/cse?q=&cx=017405804136166701815:nc9jskbtk8y&cof=FORID:0">View the results at Google</a>, or enable JavaScript to view them here.</noscript></div>
    <script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script>      <!-- end Google Custom Search-->
	
<br/><a href="http://www.urbanministry.org/faceted_search">advanced search</a>&nbsp;|&nbsp;<a href="http://www.urbanministry.org/wiki/encyclopedia-volunteer-management-volunteering-and-short-term-missions">browse articles</a>

<br/><br/>Powered by <a href="http://www.urbanministry.org">UrbanMinistry.org</a>

</form>



</div>


</div>


</div>
