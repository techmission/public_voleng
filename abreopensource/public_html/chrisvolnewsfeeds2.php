<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Christian Volunteering News Feeds</title>
</head>

<body>
<H2>Short Term Missions News:</H2>

<p class="MsoNormal"><font face="Times New Roman">
<?php
require_once("magpie/rss_fetch.inc");


// Compile array of feeds
$feeds = array(
"http://news.google.com/news?q=short+term+missions&output=RSS&ned=us&lang=en",
"http://search.msn.com/results.asp?q=short+term+missions+site%3amsnbc.msn.com&format=rss&FORM=RSRE"); 
//"http://news.search.yahoo.com/news/rss?p=short+term+missions&c=&eo=UTF-8",
// commented out b/c feed was causing errors on our site pages

// Iterate through each feed 
foreach ($feeds as $feed) {


   //Retrieve the feed
   $rss = fetch_rss($feed);


   //Format the feed for the browser
      //H2 Heading (<H2> </H2>) for each title
   $feedTitle = $rss->channel['title'];
   echo "";
   
// Limit Total Headlines Displayed to Eight per Feed
      $rss->items = array_slice($rss->items, 0, 8);
   foreach ($rss->items as $item) { 
   
      $link = $item['link'];
      $title = $item['title'];
      $description = isset($item['description']) ? $item['description'].
                     "" : "";
                     
if (strlen($title) >= 87)
{
	$title = substr($title,0,86)."...";
}
      echo "<li><a href=\"$link\">$title</a></li><br />";
   }
   echo "";
 }
?>
</font></p>

<H2>Volunteer Recruitment News</H2>

<p class="MsoNormal"><font face="Times New Roman">
<?php
require_once("magpie/rss_fetch.inc");


// Compile array of feeds
$feeds = array(
"http://news.google.com/news?q=volunteer+recruitment&output=RSS&ned=us&lang=en",
"http://search.msn.com/results.asp?q=volunteer+recruitment+site%3amsnbc.msn.com&format=rss&FORM=RSRE"); 
//"http://news.search.yahoo.com/news/rss?p=volunteer+recruitment&c=&eo=UTF-8",
// commented out b/c feed was causing errors in pages

// Iterate through each feed 
foreach ($feeds as $feed) {


   //Retrieve the feed
   $rss = fetch_rss($feed);


   //Format the feed for the browser
      //H2 Heading (<H2> </H2>) for each title
   $feedTitle = $rss->channel['title'];
   echo "";
   
// Limit Total Headlines Displayed to Eight per Feed
      $rss->items = array_slice($rss->items, 0, 8);
   foreach ($rss->items as $item) { 
   
      $link = $item['link'];
      $title = $item['title'];
      $description = isset($item['description']) ? $item['description'].
                     "" : "";
                     
if (strlen($title) >= 87)
{
	$title = substr($title,0,86)."...";
}
      echo "<li><a href=\"$link\">$title</a></li><br />";
   }
   echo "";
 }
?>
</font></p>



</body>

</html>
