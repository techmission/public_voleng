<?php 

// script for loading various RSS feeds and other items 
// written by Robert D. Smith, ~2007

// load a class to pull data
require_once("snoopyclass.inc");
$snoopy = new Snoopy;

$snoopy->fetch("http://www.christianvolunteering.org/chrisvolnewsfeeds2.php");
//print $snoopy->results;

$data = $snoopy->results;
//print $data;

$myFile = "chrisvolnewsfeeds3.php";
// $myFile = "chrisvolnewsfeeds.htm";
$fh = fopen($myFile, 'w') or die("can't open file");

fwrite($fh, $data);
fclose($fh);

require_once("opindex2_chrisvolnewsfeed2.php");
require_once("opindex3_chrisvolnewsfeed2top.php");
require_once("opindex4_chrisvolnewsfeed.php");
require_once("opindex5_virtualvolfeed.php");
require_once("opindex6_volunteeringview.php");

?>