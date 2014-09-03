<?php
/*
Google Earth wants text/plain or application/vnd.google-earth.kml+xml so this script is a proxy that will pass on all parameters to solr and return the results with the right headers
Set up a bunch of defaults like the number of points you want and the default bounding box, which is basically the whole world here (I think)
*/
$rows=100;
$start=0;

if(is_numeric($_GET["start"])){
 $start=$_GET["start"];
}
if(is_numeric($_GET["rows"])){
 $rows=$_GET["rows"];
}
$lat0=-90;
if(isset($_GET["lat0"])){
 $lat0=$_GET["lat0"];
}
$lat1=90;
if(isset($_GET["lat1"])){
 $lat1=$_GET["lat1"];
}
$lon0=-180;
if(isset($_GET["lon0"])){
 $lon0=$_GET["lon0"];
}
$lon1=180;
if(isset($_GET["lon1"])){
 $lon1=$_GET["lon1"];
}
if(isset($_GET["q"])){
 $text="text:".$_GET["q"]."+AND+";
}

$solrbaseurl = "http://server2.techmission.org:8080/solr/select/?";
$url=$solrbaseurl."q=".$text."longitude:[$lon0+TO+$lon1]+AND+latitude:[$lat0+TO+$lat1]&wt=xslt&tr=kml.xsl&start=".$start."&rows=".$rows;
$s = file_get_contents($url);
header('Content-Type: application/vnd.google-earth.kml+xml');
echo $s;
?>
