<?php

error_reporting(E_ERROR | E_PARSE);


$html = new DOMDocument();
$html->loadHTML(utf8_decode(file_get_contents('http://www2.guidestar.org/PartnerReport.aspx?Partner=networkforgood&ein=' . $_GET['ein'])));

$base = $html->createElement('base');
$base->setAttribute('href', 'http://www2.guidestar.org/');

$head = $html->getElementsByTagName('head')->item(0);
$head->insertBefore($base, $head->firstChild);

echo $html->saveHTML();

?>
