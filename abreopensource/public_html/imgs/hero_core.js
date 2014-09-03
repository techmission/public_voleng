/**
* Home page Hero spot animator
*/

var HeroCore = {
    current_slide : '',
    slides        : [],
    
    slide_start_css : {
        'herocoreemotive-a' : { top : '0px', left : '0px', opacity : 1 },   // same as in CSS
        'herocoretitle-a'   : { top : '0px', left : '0px', opacity : 0 },   // mostly, same as in CSS
        'herocoresidebar-a' : { opacity : 1 }
    },
    
    slide_remove_animation : {
        'herocoreemotive-a' : {
            params   : { left : '-840px', opacity : 0 },
            duration : 650                                                      // slightly longer cuz the dom removal code is attached to the end of this animation
        },
        'herocoretitle-a'   : {
            params   : { left : '-840px', opacity : 0 },
            duration : 600
        },
        'herocoresidebar-a' : {
            params   : { opacity : 0 },
            duration : 600
        }
    },
    
    auto_slide       : true,
    user_interacting : false,
    auto_slide_delay : 10
};

/*
* initialize(e)
*/
HeroCore.initialize = function(e) {
    
    HeroCore.iepngfix();
    
    $('#herocore').hover( HeroCore.handle_hero_root_over, HeroCore.handle_hero_root_out );
    
    var slide_containers = $('#herocore .herocoreslide-a').get();
    var labels           = $('#herocore #herocorecontrollabels .herocorecontrollabel-a').get();
    var control_buttons  = $('#herocore #herocorecontrols .herocoreselectorbutton-a').get();
    
    // make sure there are as many control buttons as there are emotives
    if (slide_containers.length > control_buttons.length) {
        while(control_buttons.length < slide_containers.length)
            control_buttons.push(HeroCore.add_control_button());
    }
    else if (slide_containers.length < control_buttons.length) {
        while (control_buttons.length > slide_containers.length) {
            var cb = control_buttons.pop();
            $(cb).remove();
        }
    }
    
    control_buttons = control_buttons.reverse();
    
    HeroCore.slides = [];
    
    // combine elements into single object for easy access
    // if some elements are missing, then the object won't be created
    for (var i = 0; i < slide_containers.length; i++) {
        var emotives         = $('.herocoreemotive-a', slide_containers[i]).get();
        var titles           = $('.herocoretitle-a', slide_containers[i]).get();
        var sidebars         = $('.herocoresidebar-a', slide_containers[i]).get();
        
        var slide = {
            index           : i,
            slide_body      : slide_containers[i],
            emotive         : emotives[0],
            title           : titles[0] ? titles[0] : '',
            sidebar         : sidebars[0] ? sidebars[0] : '',
            label           : labels[i] ? labels[i] : '',
            control_button  : control_buttons[i]
        };
        
        // control button remembers index
        $(slide.control_button).data('index', i);
        
        // assign control button action
        $(slide.control_button).click(HeroCore.handle_control_button_click);
        $(slide.control_button).hover(HeroCore.handle_control_button_over, HeroCore.handle_control_button_out);
        
        HeroCore.slides.push(slide);
    }
    
    // if none, stop here and do nothing
    if (!HeroCore.slides.length) return;
    
    // choose random first one
    HeroCore.start_random_start();
    
    // remove all but the chosen from the dom
    for (var i = 0; i < HeroCore.slides.length; i++) {
        // remove all but the chosen one from the dom
        
        if (i != HeroCore.current_slide.index) {
            $(HeroCore.slides[i].slide_body).remove();
            
            if (HeroCore.slides[i].label)
                $(HeroCore.slides[i].label).remove();
        }
        
        // unhide
        $(HeroCore.slides[i].label).show();
    }
    
    $(HeroCore.current_slide.control_button).addClass('selected');
    
    $(HeroCore.current_slide.slide_body).css({ visibility : 'visible' });
    
    HeroCore.show_label(HeroCore.current_slide.label);
    
    // if only one, don't animate, and hide controls
    if (HeroCore.slides.length == 1)
        $('#herocorecontrols').hide();
    else
        HeroCore.auto_slideshow_begin();
}

/*
* add_control_button()
*/
HeroCore.add_control_button = function() {
    return $('#herocorecontrols').append('<a class="herocoreselectorbutton-a" href="#"></a>').get();
}

/*
* start_random_start()
*/
HeroCore.start_random_start = function() {

    if (HeroCore.slides.length == 1) {
        HeroCore.current_slide = HeroCore.slides[0];
        return;
    }
    
    var slides_starting_out_visible = [];
    
    for (var i = 0; i < HeroCore.slides.length; i++) {
        if ($(HeroCore.slides[i].slide_body).css('visibility') != 'hidden')
            slides_starting_out_visible.push(HeroCore.slides[i]);
    }
    
    if (slides_starting_out_visible.length == 1) {
        HeroCore.current_slide = HeroCore.slides[slides_starting_out_visible[0].index];
        return;
    }
    
    var slides_array = slides_starting_out_visible.length ? slides_starting_out_visible : HeroCore.slides;
    
    // choose a random slide to on which start
    var rand_index = parseInt(Math.random() * 1000) % slides_array.length;
    
    HeroCore.current_slide = slides_array[rand_index];
}

/*
* show_slide(index)
*/
HeroCore.show_slide = function(new_slide_index) {
    var current_slide = HeroCore.current_slide;
    
    if (current_slide && (current_slide.index == new_slide_index))
        return;
    
    var new_slide = HeroCore.slides[new_slide_index];
    
    if (!new_slide)
        return;
    
    if (HeroCore.animating)
        return;
    
    /** toggle globals **/
    HeroCore.previous_slide = current_slide;
    HeroCore.current_slide  = new_slide;
    HeroCore.animating      = true;
    
    // put new one under current one
    $(current_slide.slide_body).before(new_slide.slide_body);
    
    $(HeroCore.current_slide.slide_body).css({ visibility : 'visible' });
    
    // set elements' starting CSS so it's visible
    $(new_slide.emotive).css(HeroCore.slide_start_css['herocoreemotive-a']);
    
    if (new_slide.title)
        $(new_slide.title).css(HeroCore.slide_start_css['herocoretitle-a']);
    
    if (new_slide.sidebar)
        $(new_slide.sidebar).css(HeroCore.slide_start_css['herocoresidebar-a']);
    
    /**
    * Begin go-away animations
    */
    // go away
    $(current_slide.emotive).animate(
        HeroCore.slide_remove_animation['herocoreemotive-a'].params,
        HeroCore.slide_remove_animation['herocoreemotive-a'].duration,
        'linear',
        HeroCore._finish_showing_new_slide
    );
    
    if (current_slide.title)
        $(current_slide.title).animate(
            HeroCore.slide_remove_animation['herocoretitle-a'].params,
            HeroCore.slide_remove_animation['herocoretitle-a'].duration
        );
    
    if (current_slide.sidebar)
        $(current_slide.sidebar).animate(
            HeroCore.slide_remove_animation['herocoresidebar-a'].params,
            HeroCore.slide_remove_animation['herocoresidebar-a'].duration
        );
    
    // change label
    HeroCore.show_label(new_slide.label);
    
    // change active selector
    for (var i = 0; i < HeroCore.slides.length; i++)
        $(HeroCore.slides[i].control_button).removeClass('selected');
    
    $(new_slide.control_button).addClass('selected');
}

/*
* _finish_showing_new_slide()
*/
HeroCore._finish_showing_new_slide = function(new_slide_index) {
    if (HeroCore.previous_slide) {
        $(HeroCore.previous_slide.slide_body).remove();
        HeroCore.previous_slide = '';
    }
    
    // fade in title
    if (HeroCore.current_slide.title)
        $(HeroCore.current_slide.title).animate({ opacity : 1 }, 300, HeroCore.handle_slide_title_fadein_fisnish);
}

/*
* show_label(label_dom)
*/
HeroCore.show_label = function(label_dom) {
    var current_label = $('#herocorecontrollabels .herocorecontrollabel-a').get();
    
    current_label = current_label[0];
    
    if (current_label == label_dom)
        return;
    else {
        if (!label_dom)
            label_dom = '';
        
        $(current_label).replaceWith(label_dom);
        $(label_dom).show();
    }
}


/*
* iepngfix(label_dom)
*/
HeroCore.iepngfix = function() {

    if (!jQuery.browser.msie) return;
    
    // for all ie's
    var imgs = $('#herocore img').get();

    for(var i=0; i < imgs.length; i++) {
        var img = imgs[i];
        
        var imgName = img.src.toUpperCase();
        
        if (imgName.substring(imgName.length-3, imgName.length) == "PNG") {
            var png_src = img.src;
            var width   = $(img).width();
            var height  = $(img).height();
            
            img.src = 'images/clear.gif';
            
            $(img).css({
                'width'  : width,
                'height' : height,
                "filter" : "progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\"" + png_src + "\", sizingMethod='scale')"
            });
        }
    }
}

/**
* Auto Slideshow Related Functions
*/

/*
* auto_slideshow_begin()
*/
HeroCore.auto_slideshow_begin = function() {
    if (!HeroCore.auto_slide)
        return;
    
    // already started? don't start twice
    if (HeroCore.auto_slide_interval)
        return;
    
    if (HeroCore.auto_slide_delay <= 0) HeroCore.auto_slide_delay = 4;
    
    HeroCore.auto_slide_interval = setInterval(HeroCore.auto_slideshow_show_next_slide, (HeroCore.auto_slide_delay * 1000));
}

/*
* auto_slideshow_show_next_slide()
*/
HeroCore.auto_slideshow_show_next_slide = function() {
    if (!HeroCore.auto_slide)
        return;
    
    // go slowly. don't clobber other running animation
    if (HeroCore.animating)
        return;
    
    // don't move if the user is trying to click something
    if (HeroCore.user_interacting)
        return;
    
    var next_index = (HeroCore.current_slide.index + 1) % HeroCore.slides.length;
    
    // should never happen, but make sure we're not trying to go to the same slide
    // (that would mean there is only one slide, and the slideshow would never have been started)
    if (next_index == HeroCore.current_slide_index)
        return;
    
    HeroCore.show_slide(next_index);
}

/*
* auto_slideshow_end()
*/
HeroCore.auto_slideshow_end = function() {
    HeroCore.auto_slide = false;
    
    clearInterval(HeroCore.auto_slide_interval);
    
    HeroCore.auto_slide_interval = false;
}


/**
* Handlers
*/

/*
* handle_control_button_click(e)
*/
HeroCore.handle_control_button_click = function(e) {
    var index = $(this).data('index');
    
    HeroCore.auto_slideshow_end();
    
    HeroCore.show_slide(index);
    
    return false;
}


/*
* handle_control_button_click(e)
*/
HeroCore.handle_control_button_over = function(e) {
    var index = $(this).data('index');
    
    var hovered_slide = HeroCore.slides[index];
    
    if (hovered_slide)
        HeroCore.show_label(hovered_slide.label);
}

/*
* handle_control_button_click(e)
*/
HeroCore.handle_control_button_out = function(e) {
    if (HeroCore.current_slide)
        HeroCore.show_label(HeroCore.current_slide.label);
}

/*
* handle_slide_title_fadein_fisnish()
*/
HeroCore.handle_slide_title_fadein_fisnish = function() {
    HeroCore.animating = false;
}

/*
* handle_hero_root_over(e)
*/
HeroCore.handle_hero_root_over = function(e) {
    HeroCore.user_interacting = true;
}

/*
* handle_hero_root_out(e)
*/
HeroCore.handle_hero_root_out = function(e) {
    HeroCore.user_interacting = false;
}

    
/**
* call initialize() when document is loaded
*/
$(document).ready(HeroCore.initialize);