<?php
/*
 * Created on Apr 2, 2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
 
 /***************************************
** Title........: Search and Replace class
** Filename.....: index.php
** Author.......: Robert D. Smith
** Version......: See script
** Notes........:
** Last changed.: 2007.04.02
** Last change..: 2007.04.02    1423EST
***************************************/

        include('class.search_replace.inc');

/***************************************
** Create the object, set the search
** function and run it.  
**    The lines below search for the word 'test' in the file 'test.txt'
**    and replace it with the word 'Replaced!' 
**
** Then change the
** pattern to find, and re-run the search.
**    The function  $sr->set_find('another'); is called  
**    and the file 'test.txt' is searched for the word 'another'
**    this is then replaced with 'Replaced!'
***************************************/

        $sr = new search_replace('test', 'Replaced!', array('test.txt'));
        $sr->set_search_function('quick');
        $sr->do_search();

        $sr->set_find('another');
        $sr->do_search();

/***************************************
** Some ouput purely for the example.
***************************************/

        header('Content-Type: text/plain');
        echo 'Number of occurences found: '.$sr->get_num_occurences()."\r\n";
        echo 'Error message.............: '.$sr->get_last_error()."\r\n";
  
?>
