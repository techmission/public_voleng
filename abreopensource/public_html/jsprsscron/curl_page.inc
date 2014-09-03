<?php

/* replacement for Snoopy class using curl - ead 5.13.11 */

function curl_page($url) {
  $c = curl_init();
  curl_setopt($c, CURLOPT_RETURNTRANSFER, 1);
  curl_setopt($c, CURLOPT_URL, $url);
  $contents = curl_exec($c);
  curl_close($c);

  if ($contents) {
    return $contents;
  }
  else {
    return FALSE;
  }
}