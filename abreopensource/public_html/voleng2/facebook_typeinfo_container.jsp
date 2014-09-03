<!-- start JSP information login required -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information login required  -->


<%//@ include file="/template_include/facebookapi_keys.inc" %>


<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<div id="fb-root"></div>
 <script src="http://connect.facebook.net/en_US/all.js"></script>
 <script>
   FB.init({
     appId  : '<%=appid%>',
     status : true, // check login status
     cookie : true, // enable cookies to allow the server to access the session
     xfbml  : true,  // parse XFBML
	 oauth  : true
   });
   
   window.fbAsyncInit = function(){
   
      FB.Canvas.setAutoResize(true);
	  
	}
	</script>
	



<% String umdst = "",umdstf = "",umdstwiki = "";
if(request.getParameter("umdst") != null){
	umdst=request.getParameter("umdst");
}
if(request.getParameter("umdstf") != null){
	umdstf=request.getParameter("umdstf");
}
if(request.getParameter("umdstwiki") != null){
	umdstwiki=request.getParameter("umdstwiki");
}
String umDestination=umdst;
if(umdstwiki.length()>0){
	umDestination="wiki/"+umDestination;
}
if(umdstf.length()>0){
	umDestination="f/"+umDestination;
}
/*
out.print(umdst);
out.print(umdstf);
out.print(umdstwiki);
*/
%>

<script type="text/javascript" src="http://www.christianvolunteering.org/template_include/js/ajax.js"></script>


<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->



<script type="text/javascript">
// everything is wrapped in the XD function to reduce namespace collisions
var XD = function(){

    var interval_id,
    last_hash,
    cache_bust = 1,
    attached_callback,
    window = this;

    return {
        postMessage : function(message, target_url, target) {
            if (!target_url) {
                return;
            }
            target = target || parent;  // default to parent
            if (window['postMessage']) {
                // the browser supports window.postMessage, so call it with a targetOrigin
                // set appropriately, based on the target_url parameter.
                target['postMessage'](message, target_url.replace( /([^:]+:\/\/[^\/]+).*/, '$1'));
            } else if (target_url) {
                // the browser does not support window.postMessage, so use the window.location.hash fragment hack
                target.location = target_url.replace(/#.*$/, '') + '#' + (+new Date) + (cache_bust++) + '&' + message;
            }
        },
        receiveMessage : function(callback, source_origin) {
            // browser supports window.postMessage
            if (window['postMessage']) {
                // bind the callback to the actual event associated with window.postMessage
                if (callback) {
                    attached_callback = function(e) {
                        if ((typeof source_origin === 'string' && e.origin !== source_origin)
                        || (Object.prototype.toString.call(source_origin) === "[object Function]" && source_origin(e.origin) === !1)) {
                             return !1;
                         }
                         callback(e);
                     };
                 }
                 if (window['addEventListener']) {
                     window[callback ? 'addEventListener' : 'removeEventListener']('message', attached_callback, !1);
                 } else {
                     window[callback ? 'attachEvent' : 'detachEvent']('onmessage', attached_callback);
                 }
             } else {
                 // a polling loop is started & callback is called whenever the location.hash changes
                 interval_id && clearInterval(interval_id);
                 interval_id = null;
                 if (callback) {
                     interval_id = setInterval(function() {
                         var hash = document.location.hash,
                         re = /^#?\d+&/;
                         if (hash !== last_hash && re.test(hash)) {
                             last_hash = hash;
                             callback({data: hash.replace(re, '')});
                         }
                     }, 100);
                 }
             }
         }
    };
}();
</script>

<script type="text/javascript">
function resizeIframe(message) {
  var h = message.data;
	if ( !isNaN( h ) && h > 0 ) {
	  // Adds some extra padding.
	  h = parseInt(h) + 30;
		// Sets the iframe height
    $('#iframe-inner').attr('height', h);
  }
	// Goes up to top of page.
	//FB.CanvasClient.scrollTo(0,0);
}
$(function(){
  // Creates the iframe for the personality type info, setting src to this page's URL hash.

	var src = 'http://www.urbanministry.org/<%=umDestination%>?fb-theme=1#' + encodeURIComponent( document.location.href );

  $( '<iframe src="' + src + '" width="720" height="1600" id="iframe-inner" scrolling="no" frameborder="0"><\/iframe>' ).appendTo( '#iframe' );  
	// Listens for message from training content iframe, so it can resize.
  XD.receiveMessage(function(e) { resizeIframe(e); }, 'http://www.urbanministry.org' );
});
</script>
<div id="iframe"></div>

<!-- start sidebar information -->
<%@ include file="/template/sidebar.inc" %><!-- end sidebar information -->
<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->

