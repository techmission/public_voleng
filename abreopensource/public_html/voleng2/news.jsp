<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<% if(aszHostID.equalsIgnoreCase( "voleng1" )){ %>

<title>Christian Volunteer Network: Register to Recruit Volunteers</title>

<% } else { %>
<% } %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszSecondaryHost.equalsIgnoreCase( "volengivol" )){ %>
<% } else { %>

<% if(aszNoSearchNav=="true"){ %>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">news</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">

<span style="float: left;">organization</span><img  style="float:right" src="http://www.christianvolunteering.org/imgs/int_page_banner_arrow.gif" width="50" height="38" />
<div id="breadcrumbs"><a href="<%=aszPortal%>/register.do?method=showHome">home</a> &gt; <a href="<%=aszPortal%>/org.do?method=showOrgStart">organization</a> &gt; getting started  </div>
</div>
<% } %>

<div id="bodysplash">
<table id="bodysplash_tb" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td><IMG alt="Volunteer Group" src="http://www.christianvolunteering.org/imgs/pic/blowing.jpg" width="245" height="204" ></td>
    <td valign="top" style="padding: 15px; background:#7693DC url(http://www.christianvolunteering.org/imgs/int_splash_patt.gif) repeat-x"><h1>
	Short-Term Missions and Volunteer Recruitment
	News</h1>
      <p LANG="en-US" STYLE="margin-bottom: 0in">Curious about what's going on 
		in the short-term missions world? Want to hear what people are talking 
		about? Check out the latest news on it. You can also find the latest 
		volunteer recruitment news.</p>
	</td>
  </tr>
</table>
</div>
       <div id="body">
			 
			  <TABLE class="searchtoolfull" cellSpacing=0 cellPadding=2 border=0 >
        <TBODY>
        <TR>
          <TD valign=top colspan=2>
<p><h3>ChristianVolunteering.org in the News:</h3></p>
<ul>
<li><a href="http://www.wdcmedia.com/newsArticle.php?ID=2102">Taking Christian Volunteerism to the HighTech Level</a></li>            
<li><a href="http://www.ydr.com/religion/ci_4515784">NEW WEB SITE: Find Volunteer opportunities</a></li>
<li><a href="http://www.breakingchristiannews.com/articles/display_art.html?ID=3151">New Website a Great Tool for Christian Volunteers and Organizations</a></li></ul> 
          
          <!--   Added 2006.10.30   rdsmith***  begin  RSS Feeds  -->
   <div align="left">   <%@ include file="/chrisvolnewsfeeds3.php" %> </div>
<!--   Added 2006.10.30   rdsmith***  end  RSS Feeds  -->


<!--   Added 2006.10.12   rdsmith***  start JSP RSS Feeds

try {      
  URL feed = new URL("file:/http://search.msn.com/results.asp?q=pornography%20addiction+site%3amsnbc.msn.com&format=rss&FORM=RSRE");
  ChannelFormat format = FormatDetector.getFormat(feed);
  ChannelParserCollection parsers = 
                          ChannelParserCollection.getInstance();
                       
  ChannelParserIF parser = 
    parsers.getParser(format, feed);
	
  parser.setBuilder(new ChannelBuilder());
  ChannelIF channel = parser.parse();
  
  for (Iterator iter = channel.getItems().iterator(); 
                                     iter.hasNext();) {
    ItemIF item = (ItemIF)iter.next();
    System.out.println(item.getTitle());
  }
} catch (MalformedURLException mue) {
  mue.printStackTrace();
} catch (UnsupportedFormatException ufe) {
  ufe.printStackTrace();
} catch (ParseException pe) {
  pe.printStackTrace();
}

Added 2006.10.12   rdsmith***  end  JSP RSS Feeds  -->

          </TD>
        </TR>
      </table>
</div>
</div>
<% } %>
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information --><!-- start footer information --><%@ include file="/template/footer.inc" %><!-- end footer information -->
