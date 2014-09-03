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

define('DB_USER', 'DB_USER');
define('DB_PASS', 'DB_PASS');
define('DB_DATABASE', 'DB_DATABASE');

solr_proxy_main();

/**
 * Executes the Solr query and returns the JSON response.
 */
function solr_proxy_main() {
    global $userdata;

    if(!$_GET['apikey']) exit_early(400, 'API key is required.');

    mysql_connect('192.168.0.1', DB_USER, DB_PASS) or exit_early(500, "Unable to select database");
    mysql_select_db(DB_DATABASE) or exit_early(500, "Unable to select database");

    $query = 'SELECT 1 FROM tbl_solr_api_keys WHERE apikey=\'' . $_GET['apikey'] . '\' AND banned=\'0\' LIMIT 1;'; 
    $result = mysql_query($query);
    if(!$result) exit_early(500, 'database query failed');
    mysql_num_rows($result) or exit_early(401, 'API key is invalid.');

    $params = array_intersect_key($_GET, array_fill_keys(array('qt', 'wt', 'tr', 'echoHandler', 'echoParams', 'q', 'sort', 'start', 'rows', 'pageDoc', 'pageScore', 'fq', 'fl', 'glob', 'debugQuery', 'debug', 'explainOther', 'debug.explain.structured defType', 'timeAllowed', 'mitHeader', 'facet', 'facet.query', 'facet.field', 'facet.prefix', 'facet.sort', 'facet.limit', 'facet.offset', 'facet.mincount', 'facet.missing', 'facet.method', 'facet.enum.cache.minDf', 'facet.date', 'facet.date.start', 'facet.date.end', 'facet.date.gap', 'facet.date.hardend', 'facet.date.other', 'facet.date.include', 'facet.range facet.range.start', 'facet.range.end', 'facet.range.gap', 'facet.range.hardend', 'facet.range.other', 'facet.range.include', 'facet.pivot', 'facet.pivot.mincount', 'facet.zeros', 'hl hl.q hl.fl', 'hl.snippets hl.fragsize', 'hl.mergeContiguous', 'hl.requireFieldMatch', 'hl.maxAnalyzedChars', 'hl.alternateField', 'hl.maxAlternateFieldLength hl.formatter', 'hl.simple.pre/hl.simple.post', 'hl.fragmenter', 'hl.fragListBuilder', 'hl.fragmentsBuilder', 'hl.boundaryScanner', 'hl.bs.maxScan', 'hl.bs.chars', 'hl.bs.type', 'hl.bs.language', 'hl.bs.country', 'hl.useFastVectorHighlighter', 'hl.usePhraseHighlighter', 'hl.highlightMultiTerm', 'hl.regex.slop', 'hl.regex.pattern', 'hl.regex.maxAnalyzedChars', 'mlt.fl mlt.fl', 'mlt.mindf', 'mlt.minwl', 'mlt.maxwl', 'mlt.maxqt', 'mlt.maxntp', 'mlt.maxntp mlt.qf', 'mlt mlt.count'), 0));
/*    
    switch($params['wt']) {
      default:
      case 'xslt':
      case 'xml':
        $content_type = 'text/xml';
        break;
      case 'csv':
        $content_type = 'text/csv';
        break;
      case 'json':
        $content_type = 'application/json';
        break;
      case 'rss':
        $content_type = 'application/rss+xml';
        break;
    }
    header('Content-type: ' . $content_type); 
*/
    if($params['wt'] == 'rss') {
      $params['wt'] = 'xslt';
      $params['tr'] = 'rss.xsl';
    }
    if($params['wt'] == 'kml') {
      $params['wt'] = 'xslt';
      $params['tr'] = 'kml.xsl';
    }

    $chrisvol_clause = 'source:ChristianVolunteering.org'; 
    if($params['q']) {
      if(!is_array($params['q'])) $params['q'] = array($params['q']); 
      foreach($params['q'] as $k => $q) {
        $params['q'][$k] = '(' .  $params['q'][$k] . ') AND ' . $chrisvol_clause; 
      }
    }
    else {
      $params['q'] = $chrisvol_clause;
    }

    $query = array();
    foreach($params as $k => $v) {
      if(!is_array($v)) $v = array($v);
      foreach($v as $_v)
        $query[] = $k . '=' . urlencode($_v);
    }
    $query = implode('&', $query);

    $hour = (time() / (60 * 60)) % 24;
    $instance = (($hour / 4) % 2) ? '/solr' : '/solralt';

    $url = 'http://server2.techmission.org:8080' . $instance . '/select/?' . $query;

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url); 
    curl_setopt($ch, CURLOPT_USERPWD, $userdata);
    curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_ANY);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
    $content = curl_exec($ch);
   
    if($params['wt'] == 'json')
      $content_type = 'application/json';
    else
      $content_type = curl_getinfo($ch, CURLINFO_CONTENT_TYPE);
 
    header('Content-type: ' . $content_type);
    header('HTTP/1.1 ' . curl_getinfo($ch, CURLINFO_HTTP_CODE));
    echo $content;
}

function exit_early($response_code, $message) {
  header('HTTP/1.1 ' . $response_code);
  die($message);
}
?>
