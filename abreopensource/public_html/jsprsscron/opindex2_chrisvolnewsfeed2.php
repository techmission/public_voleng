<?php 
  require_once("snoopyclass.inc");
$snoopy = new Snoopy;

//fetchtext(URL), This behaves exactly like fetch()except that it only returns the 
// text from the page stripping out html tags and other irrelevant data

$snoopy->fetch("http://www.christianvolunteering.org/chrisvolnewsfeeds2.php");
//print $snoopy->results;
//instantiated snoopy.class.php into snoopy.class.inc  
//made into abstract class to include

$data = $snoopy->results;
//print $data;

$myFile = "chrisvolnewsfeeds.htm";
// $myFile = "chrisvolnewsfeeds.htm";
$fh = fopen($myFile, 'w') or die("can't open file");

fwrite($fh, $data);
fclose($fh);

?>