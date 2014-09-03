
var PulsePoint = {
    /*
    * Closed state information should be the same as in the CSS. (these are CSS properties)
    *   Key's are the ID of the panel that the closed-state is associated with. (the animation initiator)
    *   Values are arrays of CSS value objects that represent animation end states (key frames)
    *
    *  so:
    *     'panelthatwasclicked' : ARRAY(   ARRAY( 'animateThisElement', 'CSS_Endpoint_Object', 'milliseconds') ... )
    */
    state_closed : {
        /** animation key frames for closing pulsepointpanelone */
        pulsepointpanelone : [
            [ '#pulsepointpanelone',   { left : 0,        width : '163px' }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '163px',  width : '155px' }, 200 ],
            [ '#pulsepointpanelthree', { left : '331px',  width : '163px' }, 200 ],
            [ '#pulsepointpanelfour',  { left : '494px',  width : '136px' }, 200 ]
        ],
        
        /** animation key frames for closing pulsepointpaneltwo */
        pulsepointpaneltwo : [
            [ '#pulsepointpanelone',   { left : 0,        width : '163px'                                     }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '163px',  width : '155px', 'backgroundPosition' : '-163px 0' }, 200 ],   // backgroundPosition matches CSS PulsePoint.ppopenbutton AND pre_open_animation_css
            [ '#pulsepointpanelthree', { left : '331px',  width : '163px'                                     }, 200 ],
            [ '#pulsepointpanelfour',  { left : '494px',  width : '136px'                                     }, 200 ]
        ],
        
        /** animation key frames for closing pulsepointpanelthree */
        pulsepointpanelthree : [
            [ '#pulsepointpanelone',   { left : 0,        width : '163px'                                     }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '163px',  width : '155px'                                     }, 200 ],
            [ '#pulsepointpanelthree', { left : '331px',  width : '163px', 'backgroundPosition' : '-331px 0' }, 200 ],   // backgroundPosition matches CSS PulsePoint.ppopenbutton AND pre_open_animation_css
            [ '#pulsepointpanelfour',  { left : '494px',  width : '136px'                                     }, 200 ]
        ],
        
        /** animation key frames for closing pulsepointpanelfour */
        pulsepointpanelfour : [
            [ '#pulsepointpanelone',   { left : 0,        width : '163px'                                     }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '163px',  width : '155px'                                     }, 200 ],
            [ '#pulsepointpanelthree', { left : '331px',  width : '163px'                                     }, 200 ],
            [ '#pulsepointpanelfour',  { left : '494px',  width : '136px', 'backgroundPosition' : '-494px 0' }, 200 ]    // backgroundPosition matches CSS PulsePoint.ppopenbutton AND pre_open_animation_css
        ]
        
        /** it looks like the above are going to be all the same all the time... someone's dumb (not hiroki) */
    },
    
    /*
    * Open state (not in CSS, tho the "left" property _is_ based on width of siblink panels)
    */
    state_open : {
        /** animation key frames for opening pulsepointpanelone */
        pulsepointpanelone : [
            [ '#pulsepointpanelone',   { left : 0,        width : '630px' }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '631px',  width : '155px' }, 200 ],
            [ '#pulsepointpanelthree', { left : '786px',  width : '163px' }, 200 ],
            [ '#pulsepointpanelfour',  { left : '949px',  width : '136px' }, 200 ]
        ],
        
        /** animation key frames for opening pulsepointpaneltwo */
        pulsepointpaneltwo : [
            [ '#pulsepointpanelone',   { left : '-177px', width : '163px'                                 }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : 0,        width : '630px', 'backgroundPosition' : '0px 0' }, 200 ],   // the px is important
            [ '#pulsepointpanelthree', { left : '631px',  width : '163px'                                 }, 200 ],
            [ '#pulsepointpanelfour',  { left : '794px',  width : '136px'                                 }, 200 ]
        ],
        
        /** animation key frames for opening pulsepointpanelthree */
        pulsepointpanelthree : [
            [ '#pulsepointpanelone',   { left : '-332px', width : '163px'                                 }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '-156px', width : '155px'                                 }, 200 ],
            [ '#pulsepointpanelthree', { left : 0,        width : '630px', 'backgroundPosition' : '0px 0' }, 200 ],   // the px is important
            [ '#pulsepointpanelfour',  { left : '631px',  width : '136px'                                 }, 200 ]
        ],
        
        /** animation key frames for opening pulsepointpanefour */
        pulsepointpanelfour : [
            [ '#pulsepointpanelone',   { left : '-495px', width : '163px'                                 }, 200 ],   // all of these widths are the same as in the CSS
            [ '#pulsepointpaneltwo',   { left : '-319px', width : '155px'                                 }, 200 ],
            [ '#pulsepointpanelthree', { left : '-164px', width : '163px'                                 }, 200 ],
            [ '#pulsepointpanelfour',  { left : 0,        width : '630px', 'backgroundPosition' : '0px 0' }, 200 ]    // the px is important
        ]
    },
    
    pre_open_animation_css : {
        pulsepointpanelone   : { 'background-image' : 'url(images/pulse_point_selected_bg.png)', 'background-position' : '0px 0'    },    // the px is important
        pulsepointpaneltwo   : { 'background-image' : 'url(images/pulse_point_selected_bg.png)', 'background-position' : '-163px 0'  },
        pulsepointpanelthree : { 'background-image' : 'url(images/pulse_point_selected_bg.png)', 'background-position' : '-331px 0' },
        pulsepointpanelfour  : { 'background-image' : 'url(images/pulse_point_selected_bg.png)', 'background-position' : '-494px 0' }
    },
    
    post_close_animation_css : {
        pulsepointpanelone   : { 'background-image' : 'none' },
        pulsepointpaneltwo   : { 'background-image' : 'none' },
        pulsepointpanelthree : { 'background-image' : 'none' },
        pulsepointpanelfour  : { 'background-image' : 'none' }
    }
};


/*
* initialize()
*/
PulsePoint.initialize = function() {
    $('#pulsepointpanelcontainer .ppopenbutton').click(PulsePoint.handle_ppopenbutton_click);
    $('#pulsepointpanelcontainer .ppopenbutton').hover(PulsePoint.handle_ppopenbutton_mouseover, PulsePoint.handle_ppopenbutton_mouseout);
    $('#pulsepointpanelcontainer .ppclosebutton').click(PulsePoint.handle_ppclosebutton_click);
}

/*
* handle_ppopenbutton_click(event)
*/
PulsePoint.handle_ppopenbutton_click = function(event) {
    
    var panel    = this.parentNode.parentNode;  // relies on structure, so don't change. or change if you change.
    var panel_id = panel.id;
    
    var target_state;
    
    var opening = false;
    
    // className "open" has no CSS style associated
    if ('open' == panel.className) {
        // closing
        opening = false;
        target_state = PulsePoint.state_closed[panel_id];
    }
    else {
        // opening
        panel.className = 'open';
        opening = true;
        target_state = PulsePoint.state_open[panel_id];
        
        if (PulsePoint.pre_open_animation_css[panel_id])
            $(panel).css(PulsePoint.pre_open_animation_css[panel_id])
    }
    
    if (target_state) {
    
        $('#pulsepointpanelcontainer').attr('className', 'animating');
        
        for (var i = 0; i < target_state.length; i++) {
            var id        = target_state[i][0];
            var css_state = target_state[i][1];
            var duration  = target_state[i][2];
            var callback  = (!opening && (id == '#' + panel_id)) ? PulsePoint.handle_panel_close_animation_finish : '';
            
            if (callback)
                $(id).animate(css_state, duration, 'linear', callback);
            else
                $(id).animate(css_state, duration, 'linear');
        }
    }
    
    return false;
}

/*
* handle_panel_close_animation_finish()
*/
PulsePoint.handle_panel_close_animation_finish = function() {
    this.className = 'closed';
    
    if (PulsePoint.post_close_animation_css[this.id])
        $(this).css(PulsePoint.post_close_animation_css[this.id]);
    
    
    $('#pulsepointpanelcontainer').attr('class', 'notanimating');
}

/*
* handle_ppclosebutton_click(event)
*/
PulsePoint.handle_ppclosebutton_click = function(event) {
    var panel    = this.parentNode.parentNode;  // relies on structure, so don't change. or change if you change.
    var panel_id = panel.id;
    
    var target_state;
    
    // className "open" has no CSS style associated
    if ('open' == panel.className)
        // closing
        target_state = PulsePoint.state_closed[panel_id];
    else
        return true; // impossible! should never see the close button when closed
    
    if (target_state) {
        for (var i = 0; i < target_state.length; i++) {
            var id        = target_state[i][0];
            var css_state = target_state[i][1];
            var duration  = target_state[i][2];
            var callback  = PulsePoint.handle_panel_close_animation_finish;
            
            $(id).animate(css_state, duration, 'linear', PulsePoint.handle_panel_close_animation_finish);
        }
    }
    
    return false;
}



/**
* call initialize() when document is loaded
*/
$(document).ready(PulsePoint.initialize);