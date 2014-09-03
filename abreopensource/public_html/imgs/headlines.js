

var Headlines = {
    currently_open_synopsis_containers : [],
    
    currently_open_synopsis       : '',
    currently_open_synopsis_title : '',
    
    // for holding
    synopsis_doms : [],
    title_doms    : [],
    
    open_story_height : '175'
};


/*
* initialize()
*/
Headlines.initialize = function() {
    var headline_elements   = $('#headlines .headline').get();
    Headlines.title_doms    = $('#headlines .headlinetitlescontainer h3').get();
    Headlines.synopsis_doms = $('#headlines .headlinesynopsis').get();
    
    // loop through headline sections
    for (var i = 0; i < headline_elements.length; i++) {
        
        var title_doms    = $('.headlinetitlescontainer h3', headline_elements[i]).get();
        var synopsis_doms = $('.headlinesynopsis', headline_elements[i]).get();
        
        var thumbnail_links = $('.headlinethumbnailcontainer a', headline_elements[i]).get();
        
        // uses disove transitions, so remove from dom tree
        $('.headlinetitlescontainer h3', headline_elements[i]).remove();
        $('.headlinesynopsis', headline_elements[i]).remove();
        
        // loop thru thumbnail in the headline section
        for (var j = 0; j < thumbnail_links.length; j++) {
            
            $(thumbnail_links[j]).bind('click', {
                index         : j,
                title_dom     : title_doms[j],
                synopsis_dom  : synopsis_doms[j],
                headline_dom  : headline_elements[i]
            }, Headlines.handle_thumb_click);
            
            $(thumbnail_links[j]).bind('mouseover', {
                index         : j,
                title_dom     : title_doms[j],
                synopsis_dom  : synopsis_doms[j],
                headline_dom  : headline_elements[i]
            }, Headlines.handle_thumb_enter);
            
            $(thumbnail_links[j]).bind('mouseout', {
                index         : j,
                title_dom     : title_doms[j],
                synopsis_dom  : synopsis_doms[j],
                headline_dom  : headline_elements[i]
            }, Headlines.handle_thumb_leave);
        }
    }
};

/*
* synopsis_is_open(synopsis_dom)
*/
Headlines.synopsis_is_open = function(synopsis_dom) {
    
    return Headlines.currently_open_synopsis == synopsis_dom;
};

/*
* synopsis_container_is_open(synopsis_dom)
*/
Headlines.synopsis_container_is_open = function(synopsis_container_dom) {
    
    return jQuery.inArray(synopsis_container_dom, Headlines.currently_open_synopsis_containers) >= 0;
};

/*
* open_synopsis(dom)
*/
Headlines.open_synopsis = function(synopsis_dom, synopsis_container_dom) {

    // must have an element
    if (!synopsis_dom)
        return false;
    
    // in line for later
    if ('opening' == $(synopsis_dom).data('state'))
        return false;
    
    if (Headlines.synopsis_is_open(synopsis_dom))
        return false;
    
    // stop sibbling animations, in case
    $('.headlinesynopsis', synopsis_container_dom).not(synopsis_dom).stop(1);
    $('.headlinesynopsis', synopsis_container_dom).not(synopsis_dom).data('state', 'open');
    
    $(synopsis_dom).remove();
    
    $(synopsis_container_dom).append(synopsis_dom);
    
    // jQuery remove() removes all even handlers, so have to readd event handlers everytime the elements are readded to the DOM
    $('.headlineclosebutton', synopsis_dom).click(Headlines.handle_closebutton_click);
    
    if (Headlines.synopsis_container_is_open(synopsis_container_dom)) {
    
        if ('closing' == $(synopsis_container_dom).data('state'))
            return false;
        
        $(synopsis_dom).data('state', 'opening');
        
        Headlines.currently_open_synopsis = synopsis_dom;
        
        // synopsis container is open. there is another synopsis open. fade this one in.
        $(synopsis_dom).css({ opacity : 0, display : 'block' });
        $(synopsis_dom).animate({ opacity : 1 }, 300, Headlines.handle_open_synopsis_finish);
    }
    else {
    
        // make sure this synopsis is the only one in the container
        $('.headlinesynopsis', synopsis_container_dom).not(synopsis_dom).remove();
        
        Headlines.close_all_open_synopsis_containers();
        
        // this synopsis' container is closed. open the synopsis
        $(synopsis_dom).css({ display : 'block', opacity : 1 });
        
        if (Headlines.open_synopsis_container(synopsis_container_dom)) {
        
            Headlines.currently_open_synopsis = synopsis_dom;
            $(synopsis_dom).data('state', 'open');
        }
        else {
            $(synopsis_dom).data('state', 'closed');
            return false;
        }
    }
    
    return true;
};

/*
* open_synopsis_container(dom)
*/
Headlines.open_synopsis_container = function(synopsis_container_dom) {

    // must have an element
    if (!synopsis_container_dom) return;
    
    // in line for later
    if ('opening' == $(synopsis_container_dom).data('state'))
        return false;
    
    if (Headlines.synopsis_container_is_open(synopsis_container_dom))
        return false;
    
    Headlines.currently_open_synopsis_containers.push(synopsis_container_dom);
    
    $(synopsis_container_dom).data('state', 'opening');
    
    $(synopsis_container_dom).css({ height : '0px', display : 'block' });
    
    $(synopsis_container_dom).animate({ height : Headlines.open_story_height + 'px' }, 200, Headlines.handle_open_synopsis_container_finish);
    
    return true;
};

/*
* close_synopsis_container(dom)
*/
Headlines.close_synopsis_container = function(synopsis_container_dom) {

    // must have an element
    if (!synopsis_container_dom) return;
    
    if (
        ('closing' == $(synopsis_container_dom).data('state')) ||
        ('closed'  == $(synopsis_container_dom).data('state'))
    ) {
        // in line for later
        return;
    }
    
    if ('opening' == $(synopsis_container_dom).data('state'))
        $(synopsis_container_dom).stop(1);
    
    $('.headlinesynopsis', synopsis_container_dom).stop(1);
    $('.headlinesynopsis', synopsis_container_dom).data('state', 'closed');
    
    $(synopsis_container_dom).data('state', 'closing');
    
    $(synopsis_container_dom).animate({ height : '0px' }, 200, Headlines.handle_close_synopsis_container_finish);
};

/*
* close_all_open_synopsis_containers()
*/
Headlines.close_all_open_synopsis_containers = function() {

    for (var i = 0; i < Headlines.currently_open_synopsis_containers.length; i++)
        Headlines.close_synopsis_container(Headlines.currently_open_synopsis_containers[i]);
    
    // there won't be any open synopses
    Headlines.currently_open_synopsis = '';
};

/*
* open_title(title)
*/
Headlines.open_title = function(title_dom, title_container) {
    
    if (!title_dom) return;
    
    if (!!$(title_dom).data('open')) return;
    
    $(title_dom).remove();
    
    $(title_container).append(title_dom);
    
    // open requested title
    $(title_dom).data('open', 1);
    
    $(title_dom).css({ display : 'block', opacity : 0 })
                .animate({ opacity : 1 }, 300, Headlines.open_title_finished);
};

/*
* close_title(title)
*/
Headlines.close_title = function(title_dom) {
    
    if (!title_dom) return;
    
    if (!$(title_dom).data('open')) return;
    
    $(title_dom).data('open', 0);
    
    $(title_dom).animate({ opacity : 0 }, 300);
};

/*
* thumbnail_grow(thumbnail_dom)
*/
Headlines.thumbnail_grow = function(thumbnail_dom) {
    if (!thumbnail_dom)
        return;
    
    $(thumbnail_dom).stop(1);
    
    $(thumbnail_dom).data( 'animation_start_width', $(thumbnail_dom).width());
    
    $(thumbnail_dom).animate({ iwidth : '75px' }, 100);
};

/*
* thumbnail_shrink(thumbnail_dom)
*/
Headlines.thumbnail_shrink = function(thumbnail_dom) {
    if (!thumbnail_dom)
        return;
    
    $(thumbnail_dom).stop(1);
    
    $(thumbnail_dom).data( 'animation_start_width', $(thumbnail_dom).width());
    
    $(thumbnail_dom).animate({ iwidth : '49px' }, 100);
};

/**
* Handlers
*/

/*
* handle_thumb_click(e)
*/
Headlines.handle_thumb_click = function(e) {
    var headline_dom = e.data.headline_dom;
    
    var synopsis            = e.data.synopsis_dom;
    var synopsis_container  = $('.headlinesynopsescontainer', headline_dom).get(0);
    
    var title_container = $('.headlinetitlescontainer', headline_dom).get(0);
    var title           = e.data.title_dom;
    
    if (Headlines.synopsis_is_open(synopsis)) {
        $('.headlinethumbnailcontainer a.selected').removeClass('selected').each(function(){
            Headlines.thumbnail_shrink($('img', this).get(0));
        });
        
        Headlines.currently_open_synopsis_title = '';
        Headlines.close_title(title);
        Headlines.close_all_open_synopsis_containers();
    }
    else {
        if (Headlines.open_synopsis(synopsis, synopsis_container)) {
            $('.headlinethumbnailcontainer a.selected').removeClass('selected').each(function(){
                Headlines.thumbnail_shrink($('img', this).get(0));
            });
            
            var old_title = Headlines.currently_open_synopsis_title;
            Headlines.currently_open_synopsis_title = '';
            
            if (old_title != title)
                Headlines.close_title(old_title);
            
            Headlines.open_title(title, title_container);
            
            Headlines.currently_open_synopsis_title = title;
            
            $(this).addClass('selected');
        }
    }
    
    return false;
};

/*
* handle_thumb_enter(e)
*/
Headlines.handle_thumb_enter = function(e) {
    var headline_dom = e.data.headline_dom;
    
    var synopsis            = e.data.synopsis_dom;
    var title_container     = $('.headlinetitlescontainer', headline_dom).get(0);
    var title               = e.data.title_dom;
    var thumbnail_img       = $('img', this).get(0);
    
    // don't show title if open
    if (Headlines.synopsis_is_open(synopsis)) {
        return;
    }
    else if (title) {
        Headlines.open_title(title, title_container)
        Headlines.thumbnail_grow(thumbnail_img);
    }
};

/*
* handle_thumb_leave(e)
*/
Headlines.handle_thumb_leave = function(e) {
    var headline_dom = e.data.headline_dom;
    
    var synopsis            = e.data.synopsis_dom;
    var title_container     = $('.headlinetitlescontainer', headline_dom).get(0);
    var title               = e.data.title_dom;
    var thumbnail_img       = $('img', this).get(0);
    
    if (!Headlines.synopsis_is_open(synopsis)) {
        Headlines.close_title(title)
        Headlines.thumbnail_shrink(thumbnail_img);
    }
};

/*
* handle_closebutton_click()
*/
Headlines.handle_closebutton_click = function(e) {

    var synopsis = this;
    
    // loop backward till the synopsis element
    while (synopsis && !$(synopsis).hasClass('headlinesynopsis'))
        synopsis = $(synopsis).parent().get(0);
    
    // couldn't find this close button's synopsis container, so it has nothing to close
    if (synopsis)
        Headlines.close_all_open_synopsis_containers();
    
    $('.headlinethumbnailcontainer a').removeClass('selected').each(function(){
        Headlines.thumbnail_shrink($('img', this).get(0));
    });
    
    var title = Headlines.currently_open_synopsis_title;
    Headlines.currently_open_synopsis_title = '';
    Headlines.close_title(title);
    
    return false;
};

/*
* open_title_finished()
*/
Headlines.open_title_finished = function() {
    var title_dom           = this;
    var title_container     = $(title_dom).parent().get(0);
    
    if (!title_container)
        return;
    
    $('h3', title_container).not([title_dom, Headlines.currently_open_synopsis_title]).remove();
};

/*
* handle_open_synopsis_finish()
*/
Headlines.handle_open_synopsis_finish = function() {

    if (!Headlines.synopsis_is_open(this))
        Headlines.currently_open_synopsis = this
    
    $(this).data('state', 'open');
    
    var synopsis_container_dom = $(this).parent().get(0);
    
    if (!synopsis_container_dom)
        return;
    
    $('.headlinesynopsis', synopsis_container_dom).not(this).remove();
    $('.headlinesynopsis', synopsis_container_dom).not(this).data('state', 'closed');
};

/*
* handle_open_synopsis_container_finish()
*/
Headlines.handle_open_synopsis_container_finish = function() {

    $(this).data('state', 'open');
    
    return;
    
    // HIROKI DELETE
    $(this).css({height : 'auto' });
    
    if ($(this).data('defined') != 2) {
        $(this).data('defined', 2)
        $(this).data('target_height', $(this).height());
    }
};

/*
* handle_close_synopsis_container_finish()
*/
Headlines.handle_close_synopsis_container_finish = function() {
    
    $(this).data('state', 'closed');
    
    var index = jQuery.inArray(this, Headlines.currently_open_synopsis_containers);
    
    if (index < 0)
        return;
    
    Headlines.currently_open_synopsis_containers.splice(index, 1);
    
    $(this).css({display : 'none'});
};



/**
* jQuery extention
*/
(function(jq){
    
    // cheap jquery plugin
    // inline element width change, because jquery forces elements with 'width' animations to be displayed as block elements. do not want
    jq.fx.step.iwidth = function(fx) {
        var start_iwidth = jq(fx.elem).data('animation_start_width');
        var new_width = parseInt(((fx.end - start_iwidth) * fx.state) + start_iwidth);
        
        jq(fx.elem).css({width : new_width + fx.unit});
        
        var m = parseInt(jq(fx.elem).css('marginTop'));
        var h = jq(fx.elem).height();
        
        if (isNaN(m))
            m = 0;
        
        jq(fx.elem).css({'margin-top' : (35 - h) + 'px'});
    };
})(jQuery);



/**
* call initialize() when document is loaded
*/
$(document).ready(Headlines.initialize);
