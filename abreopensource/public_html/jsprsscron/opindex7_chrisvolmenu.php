<?php 
  require_once("snoopyclass.inc");
$snoopy = new Snoopy;

//fetchtext(URL), This behaves exactly like fetch()except that it only returns the 
// text from the page stripping out html tags and other irrelevant data

$snoopy->fetch("http://www.urbanministry.org/chrisvol_menus.php");

//print $snoopy->results;
//instantiated snoopy.class.php into snoopy.class.inc  
//made into abstract class to include

$data = $snoopy->results;

//print $data;

$myFile = "chrisvol_menus.htm";

$fh = fopen($myFile, 'w') or die("can't open file");

fwrite($fh, $data);
fclose($fh);

?>


<?php

/* rdsmith 2008.02.04   1746EST  */

        include('class.search_replace.inc'); 

        $sr = new search_replace('%3F', '?', array('chrisvol_menus.htm'));
        $sr->set_search_function('quick');
        $sr->do_search();

        $sr2 = new search_replace('%3D', '=', array('chrisvol_menus.htm'));
        $sr2->set_search_function('quick');
        $sr2->do_search();
		
		/* ead 08.11.08 - to fix issues w/absolute vs. relative paths */
		$sr3 = new search_replace('http://www.christianvolunteering.org', '', array('chrisvol_menus.htm'));
        $sr3->set_search_function('quick');
        $sr3->do_search();  
		
		$sr4 = new search_replace('/churchvolunteer.jsp', 'http://www.christianvolunteering.org/churchvolunteer.jsp', array('chrisvol_menus.htm'));
        $sr4->set_search_function('quick');
        $sr4->do_search();  
?>