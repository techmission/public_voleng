<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1-nocache.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->
<style type="text/css">
* {
//margin:0; 
//padding:0;
font-family:Arial,Helvetica,sans-serif;
font-size: 11px;
}
#fbbody {
  width: 760px;
}
h4{margin-bottom:5px;}
#maincontent.sidebarless{width: 100%; backround-image:none; border:none;}
#contentwrapper {background-image:none; background-color:#FFF; width:950px;}
#pagebanner {//width:950px;}
.main_text {
padding:10px;
font-size:11px;
}
#header #login table {width:700px;}
#header #login p, #header #login td {//margin-top:55px; //padding-left:85px;}
#sidebar_left{
border:5px solid #E7E8B6; 
float:left;
margin:0px 10px 20px 20px;
//margin:0px;
//padding:0px 0px 5px 0px;
width:240px;
height:420px;
}
#sidebar_left h3 {
font-size:18px;
margin:0px 20px 0px;
//margin:10px;
font-weight:bold;
float:left;
}
#sidebar_left h4 {
font-size:12px;
color:#000;
float:right;
margin:10px 20px;
}
.sidebar {float:left;}
#quote {//padding-top:270px; //float:left;}
#steps-box {
background:url("http://christianvolunteering.org/imgs/home-titlebar.gif") no-repeat scroll 0 0 #E7E8B6;
float:left;
min-height:235px;
margin-bottom:10px;
//margin-left:10px;
width:478px;
}
#video-player {//margin-left:10px;}
#steps-box h3 {padding-left:10px; margin-top:7px;}
#steps-box ol {width:255px; //width:265px; padding-top:0px; //padding:15px 10px 0 0; //float:right;}
#steps-box b {font-size:12px;}
a { color:#000000; font-weight:inherit;}
#volunteerheader ul {margin-left:220px;}
#footer {padding:0px;}
</style>

<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->


<!-- BEGIN MAINCONTENTs -->
<div id="body">
<br><br>
<div id="sidebar_left">
<a href="<%=aszPortal%>/personalitytest.jsp"><img align="left" style="padding:15px 20px; //margin:15px 20px;" border="none" src="http://christianvolunteering.org/imgs/my-briggs-img.gif"/></a>
<div id="quote"></div>
<h3>"Your calling is where your deepest joy and the world's greatest need meet."</h3> 
<h4>- Frederick Buechner</h4>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
<p><a target="_blank" href="http://www.christianvolunteering.org/"><img style="padding:0px; float:left;" border="none" src="<%=aszPortal%>/imgs/powered-by.gif"/></a></p>


</div>



<div id="steps-box">
<h3>How It Works</h3><br>
	<a href="http://facebook.christianvolunteering.org/personalitytest.jsp"><img style="margin-top:6px; float:right;" border="none" src="<%=aszPortal%>/imgs/homepage-img.gif"/></a>
	<ol>
		<li><p>Take a Free Myers Briggs <b>Personality Test</b></p></li>
		<li><p>Discover potential <b>ministry areas & skills that fit your personality</b></p></li>
		<li><p><b>Browse over 5,000+ volunteer opportunities that fit your personality type and interests</b> and click to be connected to them</p></li>
		<li><p><b>Get trained in your ministry passions</b> through our library of <b>75,000+</b> sermons, workshops and articles <a href="<%=aszPortal%>/personalitytest.jsp"><img style="padding-top:5px; //padding-top:0px; float:right;" border="none" src="http://christianvolunteering.org/imgs/starttest-button.gif"/></a></p></li>
	</ol>
</div>

<div id="video-player">
	<object width="470" height="283"><param name="movie" value="http://www.youtube.com/v/vOD3riYJUgg&hl=en_US&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/vOD3riYJUgg&hl=en_US&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="470" height="283"></embed></object>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div></div>
<!-- start footer information -->


<%@ include file="/template/footer.inc" %><!-- end footer information -->
