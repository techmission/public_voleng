<?php 
// edited ead 5.13.2011 - snoopy no longer works, replacing with curl, which is simpler

// created a simple function to do a curl request
require_once("curl_page.inc");

// request the volunteer resources view, just textual (no styles or scripts)
$results = curl_page("http://www.urbanministry.org/opportunities?textual=1&nocache=1");
// show what the results are - for debugging purposes
//print $results;

// save the results to a file
$myFile = "volunteering_view.htm";
$fh = fopen($myFile, 'w') or die("can't open file");

fwrite($fh, $results);
fclose($fh);

/* perform necessary find and replace - rdsmith 2008.02.04   1746EST  */

        include('class.search_replace.inc'); 

        $sr = new search_replace('<a href="/', '<a href="http://www.urbanministry.org/', array('volunteering_view.htm'));
        $sr->set_search_function('quick');
        $sr->do_search();


        $sr = new search_replace('src="http://www.urbanministry.org/files/images', 'src="/files/images', array('volunteering_view.htm'));
        $sr->set_search_function('quick');
        $sr->do_search();

  


        $sr = new search_replace('src="http://www.christianvolunteering.org/imgs', 'src="/imgs', array('volunteering_view.htm'));
        $sr->set_search_function('quick');
        $sr->do_search();

