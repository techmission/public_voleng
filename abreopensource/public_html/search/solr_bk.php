<?php

/**
 * @file
 * Implements a Solr proxy.
 *
 * Currently requires json_decode which is bundled with PHP >= 5.2.0.
 *
 * You must download the SolrPhpClient and store it in the same directory as this file.
 *
 *   http://code.google.com/p/solr-php-client/
 */

require_once('/home/chrisvol/public_html/template_include/db_solr.inc');

require_once(dirname(__FILE__) . '/SolrPhpClient/Apache/Solr/Service.php');
require_once(dirname(__FILE__) . '/SolrPhpClient/Apache/Solr/HttpTransport/Curl.php');

solr_proxy_main();

/**
 * Executes the Solr query and returns the JSON response.
 */
function solr_proxy_main() {
    $params = array();
	global $userdata;

    // The names of Solr parameters that may be specified multiple times.
    $multivalue_keys = array('bf', 'bq', 'facet.date', 'facet.date.other', 'facet.field', 'facet.query', 'fq', 'pf', 'qf');

    foreach ($_GET as $key => $value) {
      if (in_array($key, $multivalue_keys)) {
        $params[$key][] = $value;
      }
      elseif ($key == 'q') {
        $keys = $value;
      }
      else {
        $params[$key] = $value;
      }
    }

    $hour = (time() / (60 * 60)) % 24;
    $app = (($hour / 4) % 2) ? '/solr' : '/solralt';
    $transportInstance = new Apache_Solr_HttpTransport_Curl(array(CURLOPT_USERPWD => $userdata, CURLOPT_HTTPAUTH => CURLAUTH_ANY));
    $solr = new Apache_Solr_Service('localhost', 8080, $app, $transportInstance);    

    var_dump($params);

    try {
      if(!isset($params['start'])) {
        $params['start'] = 0;
      }
      $response = $solr->search($keys, $params['start'], $params['rows'], $params);
    }
    catch (Exception $e) {
      die($e->__toString());
    }
    print $response->getRawResponse();
}
?>
