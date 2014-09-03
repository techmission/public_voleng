
$(document).ready(function() {

    // pick a random ad
    var ads = $("#ad-pos-tre>.ad-pos-tre-ad").get();
    
    if (!ads.length)
        return;
    
    if (ads.length == 1) {
        $(ads[0]).show();
        return;
    }
    
    var ndx = parseInt(Math.random(12) * 100) % ads.length;
    
    $(ads[ndx]).show();
});
