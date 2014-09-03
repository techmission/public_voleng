// $Id$

/**
 * Baseclass for all facet widgets.
 *
 * @class AbstractFacetWidget
 * @augments AjaxSolr.AbstractWidget
 */
AjaxSolr.AbstractFacetWidget = AjaxSolr.AbstractWidget.extend(
  /** @lends AjaxSolr.AbstractFacetWidget.prototype */
  {
  /**
   * The field to facet on.
   *
   * @field
   * @public
   * @type String
   */
  field: null,

  /**
   * Set to <tt>false</tt> to force a single "fq" parameter for this widget.
   *
   * @field
   * @public
   * @type Boolean
   * @default true
   */
  multivalue: true,

  init: function () {
    this.initStore();
  },

  /**
   * Add facet parameters to the parameter store.
   */
  initStore: function () {
    /* http://wiki.apache.org/solr/SimpleFacetParameters */
    var parameters = [
      'facet.prefix',
      'facet.sort',
      'facet.limit',
      'facet.offset',
      'facet.mincount',
      'facet.missing',
      'facet.method',
      'facet.enum.cache.minDf'
    ];

    this.manager.store.addByValue('facet', true);

    // Set facet.field, facet.date or facet.range to truthy values to add
    // related per-field parameters to the parameter store.
    if (this['facet.field'] !== undefined) {
      this.manager.store.addByValue('facet.field', this.field);
    }
    else if (this['facet.date'] !== undefined) {
      this.manager.store.addByValue('facet.date', this.field);
      parameters = parameters.concat([
        'facet.date.start',
        'facet.date.end',
        'facet.date.gap',
        'facet.date.hardend',
        'facet.date.other',
        'facet.date.include'
      ]);
    }
    else if (this['facet.range'] !== undefined) {
      this.manager.store.addByValue('facet.range', this.field);
      parameters = parameters.concat([
        'facet.range.start',
        'facet.range.end',
        'facet.range.gap',
        'facet.range.hardend',
        'facet.range.other',
        'facet.range.include'
      ]);
    }

    for (var i = 0, l = parameters.length; i < l; i++) {
      if (this[parameters[i]] !== undefined) {
        this.manager.store.addByValue('f.' + this.field + '.' + parameters[i], this[parameters[i]]);
      }
    }
  },

  /**
   * @returns {Boolean} Whether any filter queries have been set using this
   *   widget's facet field.
   */
  isEmpty: function () {
    return !this.manager.store.find('fq', new RegExp('^-?' + this.field + ':'));
  },

  /**
   * Sets the filter query.
   *
   * @returns {Boolean} Whether the selection changed.
   */
  set: function (value) {
    return this.changeSelection(function () {
      var a = this.manager.store.removeByValue('fq', new RegExp('^-?' + this.field + ':')),
          b = this.manager.store.addByValue('fq', this.fq(value));
      return a || b;
    });
  },

  /**
   * Adds a filter query.
   *
   * @returns {Boolean} Whether a filter query was added.
   */
  add: function (value) {
    return this.changeSelection(function () {
      return this.manager.store.addByValue('fq', this.fq(value));
    });
  },

  /**
   * Removes a filter query.
   *
   * @returns {Boolean} Whether a filter query was removed.
   */
  remove: function (value) {
    return this.changeSelection(function () {
      return this.manager.store.removeByValue('fq', this.fq(value));
    });
  },

  /**
   * Removes all filter queries using the widget's facet field.
   *
   * @returns {Boolean} Whether a filter query was removed.
   */
  clear: function () {
    return this.changeSelection(function () {
      return this.manager.store.removeByValue('fq', new RegExp('^-?' + this.field + ':'));
    });
  },

  /**
   * Helper for selection functions.
   *
   * @param {Function} Selection function to call.
   * @returns {Boolean} Whether the selection changed.
   */
  changeSelection: function (func) {
    changed = func.apply(this);
    if (changed) {
      this.afterChangeSelection();
    }
    return changed;
  },

  /**
   * An abstract hook for child implementations.
   *
   * <p>This method is executed after the filter queries change.</p>
   */
  afterChangeSelection: function () {},

  /**
   * One of "facet.field", "facet.date" or "facet.range" must be set on the
   * widget in order to determine where the facet counts are stored.
   *
   * @returns {Array} An array of objects with the properties <tt>facet</tt> and
   * <tt>count</tt>, e.g <tt>{ facet: 'facet', count: 1 }</tt>.
   */
  getFacetCounts: function () {
    var property;
    if (this['facet.field'] !== undefined) {
      property = 'facet_fields';
    }
    else if (this['facet.date'] !== undefined) {
      property = 'facet_dates';
    }
    else if (this['facet.range'] !== undefined) {
      property = 'facet_ranges';
    }
    if (property !== undefined) {
      switch (this.manager.store.get('json.nl').val()) {
        case 'map':
          return this.getFacetCountsMap(property);
        case 'arrarr':
          return this.getFacetCountsArrarr(property);
        default:
          return this.getFacetCountsFlat(property);
      }
    }
    throw 'Cannot get facet counts unless one of the following properties is set to "true" on widget "' + this.id + '": "facet.field", "facet.date", or "facet.range".';
  },

  /**
   * Used if the facet counts are represented as a JSON object.
   *
   * @param {String} property "facet_fields", "facet_dates", or "facet_ranges".
   * @returns {Array} An array of objects with the properties <tt>facet</tt> and
   * <tt>count</tt>, e.g <tt>{ facet: 'facet', count: 1 }</tt>.
   */
  getFacetCountsMap: function (property) {
    var counts = [];
    for (var facet in this.manager.response.facet_counts[property][this.field]) {
      counts.push({
        facet: facet,
        count: parseInt(this.manager.response.facet_counts[property][this.field][facet])
      });
    }
    return counts;
  },

  /**
   * Used if the facet counts are represented as an array of two-element arrays.
   *
   * @param {String} property "facet_fields", "facet_dates", or "facet_ranges".
   * @returns {Array} An array of objects with the properties <tt>facet</tt> and
   * <tt>count</tt>, e.g <tt>{ facet: 'facet', count: 1 }</tt>.
   */
  getFacetCountsArrarr: function (property) {
    var counts = [];
    for (var i = 0, l = this.manager.response.facet_counts[property][this.field].length; i < l; i++) {
      counts.push({
        facet: this.manager.response.facet_counts[property][this.field][i][0],
        count: parseInt(this.manager.response.facet_counts[property][this.field][i][1])
      });
    }
    return counts;
  },

  /**
   * Used if the facet counts are represented as a flat array.
   *
   * @param {String} property "facet_fields", "facet_dates", or "facet_ranges".
   * @returns {Array} An array of objects with the properties <tt>facet</tt> and
   * <tt>count</tt>, e.g <tt>{ facet: 'facet', count: 1 }</tt>.
   */
  getFacetCountsFlat: function (property) {
    var counts = [];
    for (var i = 0, l = this.manager.response.facet_counts[property][this.field].length; i < l; i += 2) {
      counts.push({
        facet: this.manager.response.facet_counts[property][this.field][i],
        count: parseInt(this.manager.response.facet_counts[property][this.field][i+1])
      });
    }
    return counts;
  },

  /**
   * @param {String} value The value.
   * @returns {Function} Sends a request to Solr if it successfully adds a
   *   filter query with the given value.
   */
  clickHandler: function (value) {
    var self = this, meth = this.multivalue ? 'add' : 'set';
    return function () {
      if (self[meth].call(self, value)) {
		  // probably want to somehow store this event as a google analytics search, too; look at ALL fq params
			call_google_analytics('fq=fq:'+value);
		  
		  
        self.manager.doRequest(0);
      }
      return false;
    }
  },

  /**
   * @param {String} value The value.
   * @returns {Function} Sends a request to Solr if it successfully removes a
   *   filter query with the given value.
   */
  unclickHandler: function (value) {
    var self = this;
    return function () {
      if (self.remove(value)) {
        self.manager.doRequest(0);
      }
      return false;
    }
  },

  /**
   * @param {String} value The value.
   * @returns {Function} Sends a request to Solr if it successfully adds a
   *   filter query with the given value.
   */
  clickHandlerAlt: function (value, init_values, source, title) {

    var self = this, meth = this.multivalue ? 'add' : 'set';
	var site = $('#subdomain').text();
	var geofilt_facet = $('#geofilt_facet').text();
//console.log('geofilt_facet is '+geofilt_facet);
	if(geofilt_facet.indexOf('geofilt pt=') != -1){
		geofilt_facet = '';
	}
	var hashURL =  '' + window.location.hash;

//console.log('source is '+source);
//console.log('value is '+value);

	var filter_class = 'filter';
	var handle_class = ' handle';
	var expanded_class = ' expanded';
	var collapsed_class = ' collapse';
	
	var filter_data = $('#filter_data_heading').text();
	var contenttype_data = $('#contenttype_heading').text();
	var title_suffix = 'Christian Volunteer and Short Term Missions Opportunities: ChristianVolunteering.org';
	var heading_suffix = 'Volunteer Opportunities';
			
	var islocalSearch = false; 
	var isVirtualSearch = false; 
	var isSTMSearch = false; 
	var isJobsSearch = false; 
	var isOrganizationSearch = false; 
	var isCVSearch = false; 
	var isFoundationSearch = false; 
	var isResumeSearch = false; 

	var portal_url = '';
	if($('#portal_name').length > 0)	portal_url = $('#portal_name').html();
	if(portal_url.length>0)	portal_url += '/';

	var leftSidebarLocal = function(self) {
//console.log('portal name is '+portal_url);
//console.log('**** INSIDE           leftSidebarLocal');	
		$('#content_type_search').text('content_type:opportunity');	
		$('#results_type').text('opportunity');	
	
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');

		$('#local').addClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');

		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#primary_opp_type').hide();

		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
//console.log(' leftsidebarLocal  manager store '+self.manager.store.values('fq'));
//('adding opp and local is: '+value);
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', 'content_type:opportunity');
		self.manager.store.addByValue('fq', geofilt_facet);
		self.manager.store.addByValue('fq', 'position_type:"Local Volunteering (in person)"');
//console.log('manager store '+self.manager.store.values('fq'));

		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
		$('#srchmethod').val('Local Volunteering (in person)');
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();	
		$('#simplyhired').hide();		

		$('#group_size').show();
		$('#facet_group_size').show();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#frequency').hide();
		$('#facet_frequency').show();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#benefits_offered').show();
		$('#facet_benefits_offered').show();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#great_for').show();
		$('#facet_great_for').show();
		$('#facet_great_for').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#trip_length').show(); 
		$('#facet_trip_length').show();
		$('#facet_trip_length').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#region').show();
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		

		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();

		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
	}

	var leftSidebarSTM = function(self) {
//console.log('**** INSIDE           leftSidebarSTM');	
		$('#content_type_search').text('content_type:opportunity');	
		$('#results_type').text('opportunity');	
		title_suffix = 'Christian Volunteer and Short Term Missions Organizations: ChristianVolunteering.org';
		heading_suffix = 'Short-Term Missions Opportunities';
	
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');

		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').addClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');
	
		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
//console.log('manager store '+self.manager.store.values('fq'));
		self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		self.manager.store.addByValue('fq', geofilt_facet);
		self.manager.store.addByValue('fq', 'content_type:opportunity');

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
//(self.manager.store.values('fq'));
//console.log('manager store '+self.manager.store.values('fq'));
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();
		$('#srchmethod').val('Short-term Missions / Volunteer Internship');
		$('#facet_num_opps').hide();		
		$('#simplyhired').hide();		

		$('#contenttype_heading').text('STM');
		$('#benefits_offered').show();
		$('#trip_length').show();
		$('#country_tax').show();
		$('#region').show();
		$('#facet_benefits_offered').addClass('filter handle expanded');
		$('#facet_trip_length').addClass('filter handle expanded');
		$('#facet_country_tax').addClass('filter handle expanded');
		$('#facet_region').addClass('filter handle expanded');

		$('#group_size').show();
		$('#facet_group_size').show();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#frequency').show();
		$('#facet_frequency').show();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#benefits_offered').show();
		$('#facet_benefits_offered').show();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#region').show();
		
		$('#great_for').show();
		$('#facet_great_for').show();
		$('#facet_great_for').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#trip_length').show(); 
		$('#facet_trip_length').show();
		$('#facet_trip_length').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
}


	var leftSidebarInternships = function(self) {
//console.log('**** INSIDE           leftSidebarInternships');	
		$('#content_type_search').text('content_type:opportunity');	
		$('#results_type').text('opportunity');	
		title_suffix = 'Christian Volunteer and Short Term Missions Organizations: ChristianVolunteering.org';
		heading_suffix = 'Internship Opportunities';
	
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');

		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').addClass('active');
//console.log('**** INSIDE          ** made CityVision active');	
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');
	
		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
//console.log('manager store '+self.manager.store.values('fq'));
		self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
		self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
		self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', geofilt_facet);
		self.manager.store.addByValue('fq', 'content_type:opportunity');

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
//(self.manager.store.values('fq'));
//console.log('manager store '+self.manager.store.values('fq'));
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();
		$('#srchmethod').val('City Vision');
		$('#facet_num_opps').hide();		
		$('#simplyhired').hide();		

		$('#contenttype_heading').text('Internships');
		$('#benefits_offered').show();
		$('#trip_length').show();
		$('#country_tax').show();
		$('#region').show();
		$('#facet_benefits_offered').addClass('filter handle expanded');
		$('#facet_trip_length').addClass('filter handle expanded');
		$('#facet_country_tax').addClass('filter handle expanded');
		$('#facet_region').addClass('filter handle expanded');

		$('#group_size').show();
		$('#facet_group_size').show();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#frequency').show();
		$('#facet_frequency').show();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#benefits_offered').show();
		$('#facet_benefits_offered').show();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#region').show();
		
		$('#great_for').show();
		$('#facet_great_for').show();
		$('#facet_great_for').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#trip_length').show(); 
		$('#facet_trip_length').show();
		$('#facet_trip_length').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
		
//        $('#facet_benefits_offered').remove().insertBefore('#facet_org_member_type');
//        $('#facet_trip_length').remove().insertBefore('#facet_org_member_type');
        $('#facet_frequency').remove().insertBefore('#facet_org_member_type');
        $('#facet_great_for').remove().insertBefore('#facet_org_member_type');
        $('#facet_region').remove().insertBefore('#facet_org_member_type');
        $('#facet_city_tax').remove().insertBefore('#facet_country_tax');
        $('#facet_province_tax').remove().insertBefore('#facet_country_tax');
		
		$('#city_tax').show();
		$('#facet_city_tax').addClass('filter handle expanded');
		$('#province_tax').show();
		$('#facet_province_tax').addClass('filter handle expanded');
		$('#country_tax').show();
		$('#facet_country_tax').addClass('filter handle expanded');
		$('#benefits_offered').show();
		$('#facet_benefits_offered').addClass('filter handle expanded');
}

	var leftSidebarVirtual = function(self) {
//console.log('**** INSIDE           leftSidebarVirtual');	
		$('#content_type_search').text('content_type:opportunity');	
		$('#results_type').text('opportunity');	
		title_suffix = 'Christian Volunteer Virtual Opportunities: ChristianVolunteering.org';
		heading_suffix = 'Virtual Volunteer Opportunities';
	
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');

		$('#local').removeClass('active');
		$('#virtual').addClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');
	
		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
//console.log('leftsidebarVirtual  manager store '+self.manager.store.values('fq'));
//('adding opp and virtual is: '+value);
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
		self.manager.store.addByValue('fq', 'content_type:opportunity');

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
					
		$('#srchmethod').val('Virtual Volunteering (from home)');
		$('#simplyhired').hide();
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();	
		$('#facet_great_for').hide();  
		$('#facet_group_size').hide(); 
		$('#facet_benefits_offered').hide(); 
		$('#facet_trip_length').hide(); 
		$('#facet_frequency').hide(); 
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
	}

	var leftSidebarOrganizations = function(self) {
//console.log('**** INSIDE           leftSidebarOrganizations');	
		$('#content_type_search').text('content_type:organization');	
		$('#results_type').text('organization');	
		title_suffix = 'Christian Volunteer and Short Term Missions Organizations: ChristianVolunteering.org';
		heading_suffix = 'Organizations';
	
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');

		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').addClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');

		// parachurches may have this data, so need to first test if they're empty or not, and show them if not
//console.log('about to trigger toggle financial data');		
		toggle_financial_data(self);

//('adding organization is: '+value);
		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.addByValue('fq', 'content_type:organization');
		self.manager.store.removeByValue('fq', 'content_type:opportunity');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
		self.manager.store.addByValue('fq', geofilt_facet);
//console.log('leftsidebarOrganizations   manager store '+self.manager.store.values('fq'));

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
		
		$('#srchmethod').val('organization');
		$('#facet_num_opps').show();		
		$('#simplyhired').hide();		
		
		$('#service_areas').hide();
		$('#facet_service_areas').removeClass(expanded_class).addClass(collapsed_class);
		$('#group_size').hide();
		$('#facet_group_size').hide();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		$('#frequency').hide();
		$('#facet_frequency').hide();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		$('#benefits_offered').hide();
		$('#facet_benefits_offered').hide();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		$('#facet_great_for').hide();
		$('#facet_trip_length').hide();
		$('#source').hide();
		$('#facet_afg').hide();
		$('#facet_afg').removeClass(expanded_class).addClass(collapsed_class);
					
		$('#facet_num_opps').show();
		$('#primary_opp_type').show();
		$('#facet_primary_opp_type').show();
		$('#facet_primary_opp_type').removeClass(collapsed_class).addClass(expanded_class);
	
		$('#advanced_facets').show();
		$('#facet_adv').removeClass(collapsed_class).addClass(expanded_class);
		$('#affil').show();
		$('#facet_org_affil').removeClass(collapsed_class).addClass(expanded_class);
		$('#affil').show();
		$('#facet_denom_affil').removeClass(collapsed_class).addClass(expanded_class);
		$('#org_member_type').show();
		$('#facet_org_member_type').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#country_tax').show();
		$('#facet_country_tax').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#region').show();
		$('#facet_region').removeClass(collapsed_class).addClass(expanded_class);
				
//console.log('manager store '+self.manager.store.values('fq'));
//console.log('line 444 value is '+value);
//console.log('manager store '+self.manager.store.values('fq'));
	
		$('#total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
//console.log('leftsidebarOrganizations   manager store '+self.manager.store.values('fq'));
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
	}

	var leftSidebarFoundations = function(self) {
//console.log('**** INSIDE           leftSidebarFoundations');	
		$('#content_type_search').text('content_type:organization');	
		$('#results_type').text('organization');	
		
		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').addClass('active');

		title_suffix = 'Christian Volunteer and Short Term Missions Foundations: ChristianVolunteering.org';
		heading_suffix = 'Foundations';
//console.log('867  adding Foundation is: '+value);
		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', '-org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'content_type:opportunity');
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		self.manager.store.addByValue('fq', 'content_type:organization');
		self.manager.store.addByValue('fq', 'org_member_type:Foundation');
		self.manager.store.addByValue('fq', geofilt_facet);

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
//	console.log('leftSidebarFoundations  manager store '+self.manager.store.values('fq'));
						
		$('#srchmethod').val('Foundation');	
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();	
		$('#simplyhired').hide();	
						
		//$('#contenttype_heading').text(contenttype_data);
		$('#contenttype_title').text('Foundations');
		$('#contenttype_heading').text('Foundations');
		$('#service_areas_label').text('Funding Interests:');
		$('#facet_afg').hide();
		$('#facet_region').hide();
		$('#facet_city_tax').hide();
		$('#facet_country_tax').hide();
//		$('#facet_country_tax_label').text('Geographic Scope Country:');
		$('#facet_province_tax_label').text('Geographic Scope State:');
//	console.log(' service_areas_label is: ' + $('#service_areas_label').text() );
//console.log(' contenttype_heading is: ' + $('#contenttype_heading').text() );
											
		$('#service_areas').show();
		$('#facet_service_areas').removeClass(collapsed_class).addClass(expanded_class);
							
		$('#facet_great_for').hide();  
		$('#facet_benefits_offered').hide(); 
		$('#facet_trip_length').hide(); 
					
		$('#group_size').hide();
		$('#facet_group_size').hide();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		$('#frequency').hide();
		$('#facet_frequency').hide();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		$('#benefits_offered').hide();
		$('#facet_benefits_offered').hide();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		$('#source').hide();
		$('#facet_afg').removeClass(expanded_class).addClass(collapsed_class);
									
		$('#primary_opp_type').hide();
		$('#facet_primary_opp_type').removeClass(expanded_class).addClass(collapsed_class);
					
		$('#advanced_facets').show();
		$('#facet_adv').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#affil').hide();
		$('#facet_org_affil').removeClass(expanded_class).addClass(collapsed_class);
		$('#denom_affil').hide();
		$('#facet_denom_affil').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#org_member_type').hide();
		$('#facet_org_member_type').removeClass(expanded_class).addClass(collapsed_class);
		$('#country_tax').show();

		$('#facet_country_tax').removeClass(expanded_class).addClass(collapsed_class);
		$('#region').hide();
		$('#facet_region').removeClass(collapsed_class).addClass(expanded_class);
					
//console.log('about to trigger toggle financial data');		
		toggle_financial_data(self);
		
		$('#geo_tax').show();
		$('#facet_geo_tax').show();
		$('#facet_geo_tax').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#foundation_type').show();
		$('#facet_foundation_type').show();
		$('#facet_foundation_type').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#foundation_location').show();
		$('#facet_foundation_location').show();
		$('#facet_foundation_location').removeClass(collapsed_class).addClass(expanded_class);

		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
	}

	var leftSidebarJobs = function(self) {
//console.log('**** INSIDE           leftSidebarJobs');	
		$('#content_type_search').text('content_type:job');	
		$('#results_type').text('job');	 //??? should this be job?
		title_suffix = 'Christian Jobs: ChristianVolunteering.org';
		heading_suffix = 'Jobs';

		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').addClass('active');
		$('#organization').removeClass('active');
		$('#resume').removeClass('active');
		$('#Foundation').removeClass('active');

		self.manager.store.removeByValue('fq', 'full_user:true');
		self.manager.store.removeByValue('fq', 'status:1');
		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
	//console.log('job class active; local inactive');			
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		$('#service_areas_label').text('Job Categories:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');
		self.manager.store.addByValue('fq', 'content_type:job');
		self.manager.store.addByValue('fq', geofilt_facet);

//console.log('manager store '+self.manager.store.values('fq'));
		if((portal_url=='cityvisioncollege/' || portal_url=='cityvision/'))	self.manager.store.addByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('manager store '+self.manager.store.values('fq'));
					
//console.log('leftSidebarJobs   manager store '+self.manager.store.values('fq'));
		
		$('#srchmethod').val('job');
		$('#facet_num_opps').hide();	
		$('#facet_primary_opp_type').hide();	
		$('#simplyhired').show();
	
		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#org_member_type').hide();
		$('#facet_org_member_type').removeClass(expanded_class).addClass(collapsed_class);
	
		$('#advanced_facets').show();
		$('#facet_adv').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').hide();
		$('#looking_for').hide();
			
		$('#education_level').hide();
		$('#facet_education_level').hide();
		$('#facet_full_user').hide();
		$('#facet_all_user').hide();
				
//console.log('manager store '+self.manager.store.values('fq'));
	}

	var leftSidebarResumes = function(self) {
//console.log('**** INSIDE           leftSidebarResumes');	
		$('#content_type_search').text('content_type:resume');	
		$('#results_type').text('resume');	
		title_suffix = 'Christian Volunteer Resumes: ChristianVolunteering.org';
		heading_suffix = 'Resumes';

		$('#local').removeClass('active');
		$('#virtual').removeClass('active');
		$('#stm').removeClass('active');
		$('#CityVision').removeClass('active');
		$('#job').removeClass('active');
		$('#organization').removeClass('active');
		$('#resume').addClass('active');
		$('#Foundation').removeClass('active');

		self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'org_member_type:"Foundation"');
		self.manager.store.addByValue('fq', '-org_member_type:Foundation');
		self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
//console.log('leftsidebarResume  manager store '+self.manager.store.values('fq'));
				
		$('#service_areas_label').text('Resume Categories:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');
		self.manager.store.addByValue('fq', 'content_type:resume');
		self.manager.store.addByValue('fq', 'full_user:true');
		self.manager.store.addByValue('fq', 'status:1');
		self.manager.store.addByValue('fq', geofilt_facet);
		
		$('#srchmethod').val('resume');
		$('#facet_num_opps').hide();	
		$('#simplyhired').hide();
	
		$('#total_giving').hide();
		$('#facet_total_giving').hide();
		$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		$('#net_assets').hide();
		$('#facet_net_assets').hide();
		$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#assets').hide();
		$('#facet_assets').hide();
		$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		$('#income').hide();
		$('#facet_income').hide();
		$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		$('#expenditures').hide();
		$('#facet_expenditures').hide();
		$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
			
		$('#group_size').hide();
		$('#facet_group_size').hide();
		$('#facet_group_size').removeClass(expanded_class).addClass(collapsed_class);
		$('#frequency').hide();
		$('#facet_frequency').hide();
		$('#facet_frequency').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#great_for').hide();
		$('#facet_great_for').hide();
		$('#facet_great_for').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#trip_length').hide();
		$('#facet_trip_length').hide();
		$('#facet_trip_length').removeClass(expanded_class).addClass(collapsed_class);

		$('#benefits_offered').hide();
		$('#facet_benefits_offered').hide();
		$('#facet_benefits_offered').removeClass(expanded_class).addClass(collapsed_class);
		$('#region').hide();
		$('#facet_region').show();
		$('#facet_region').removeClass(expanded_class).addClass(collapsed_class);
		$('#facet_primary_opp_type').hide();	
		$('#primary_opp_type').hide();
		$('#facet_primary_opp_type').removeClass(expanded_class).addClass(collapsed_class);
		$('#source').hide();
		$('#facet_afg').hide();
		$('#affil').hide();
		$('#facet_org_affil').hide();
		$('#facet_org_affil').removeClass(expanded_class).addClass(collapsed_class);
		$('#denom_affil').hide();
		$('#facet_denom_affil').show();
		$('#facet_denom_affil').removeClass(collapsed_class).addClass(expanded_class);
		$('#org_member_type').hide();
		$('#facet_org_member_type').hide();
		$('#facet_org_member_type').removeClass(expanded_class).addClass(collapsed_class);
					
		$('#service_areas').show();
		$('#facet_service_areas').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#geo_tax').hide();
		$('#facet_geo_tax').hide();
		$('#facet_geo_tax').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_type').hide();
		$('#facet_foundation_type').hide();
		$('#facet_foundation_type').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#foundation_location').hide();
		$('#facet_foundation_location').hide();
		$('#facet_foundation_location').removeClass(expanded_class).addClass(collapsed_class);
		
		$('#facet_looking_for').show();
		$('#looking_for').show();
		$('#facet_looking_for').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#education_level').show();
		$('#facet_education_level').show();
		$('#facet_education_level').removeClass(collapsed_class).addClass(expanded_class);
				
		$('#city_tax').show();
		$('#facet_city_tax').removeClass(collapsed_class).addClass(expanded_class);
				
		$('#advanced_facets').show();
		$('#facet_adv').removeClass(collapsed_class).addClass(expanded_class);
		
		$('#country_tax').show();
		$('#facet_country_tax').removeClass(collapsed_class).addClass(expanded_class);

		$('#facet_primary_opp_type').hide();
//console.log('manager store '+self.manager.store.values('fq'));
	}

	var toggle_financial_data = function(self) {
//console.log('toggle financial data triggered; $("#total_giving li").size() is '+$("#total_giving li").size()+' $("#net_assets li").size() is '+$("#net_assets li").size()+' $("#assets li").size() is '+$("#assets li").size()+' $("#income li").size() is '+$("#income li").size()+' $("#expenditures li").size() is '+$("#expenditures li").size());		
		if($("#total_giving li").size() < 1){
			$('#total_giving').hide();
			$('#facet_total_giving').hide();
			$('#facet_total_giving').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#total_giving').show();
			$('#facet_total_giving').show();
			$('#facet_total_giving').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#net_assets li").size() < 1){
			$('#net_assets').hide();
			$('#facet_net_assets').hide();
			$('#facet_net_assets').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#net_assets').show();
			$('#facet_net_assets').show();
			$('#facet_net_assets').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#assets li").size() < 1){
			$('#assets').hide();
			$('#facet_assets').hide();
			$('#facet_assets').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#assets').show();
			$('#facet_assets').show();
			$('#facet_assets').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#income li").size() < 1){
			$('#income').hide();
			$('#facet_income').hide();
			$('#facet_income').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#income').show();
			$('#facet_income').show();
			$('#facet_income').removeClass(collapsed_class).addClass(expanded_class);
		}
		if($("#expenditures li").size() < 1){
			$('#expenditures').hide();
			$('#facet_expenditures').hide();
			$('#facet_expenditures').removeClass(expanded_class).addClass(collapsed_class);
		}else{
			$('#expenditures').show();
			$('#facet_expenditures').show();
			$('#facet_expenditures').removeClass(collapsed_class).addClass(expanded_class);
		}
	}

//console.log('value is '+value);
//console.log('init_values is '+init_values);
//console.log('source is '+source);
//console.log('title is '+title);

    return function () {
//console.log('this.manager.store.values(fq);  '+ self.manager.store.values('fq'));
//console.log('source is: '+source);
//console.log('value is: '+value);
//console.log('init_values is: '+init_values);
      if (self[meth].call(self, value, init_values) || (value=='organization' && init_values.indexOf('fq=org_member_type:Foundation')!=-1)) {
//console.log('triggered');
		// probably want to somehow store this event as a google analytics search, too; look at ALL fq params
		call_google_analytics('category="'+value+'"'+init_values);

		if(title==null){
		} else if(title===undefined){
		} else if(title=='undefined'){
		}else{
			document.title =  title;
		}
		
		// hashURL_impl - see if it occurs in the URL; if NOT, remove it. otherwise, keep it
//console.log('source is: '+source);
		if(source == 'tabs'){
			self.manager.store.removeByValue('fq', 'org_member_type:Foundation');
			self.manager.store.removeByValue('fq', 'position_type:Foundation');
			self.manager.store.removeByValue('fq', 'content_type:organization');
			self.manager.store.removeByValue('fq', 'content_type:job');
			self.manager.store.removeByValue('fq', 'content_type:resume');
			self.manager.store.removeByValue('fq', 'position_type:"Short-term Missions / Volunteer Internship"');
			self.manager.store.removeByValue('fq', 'position_type:"Local Volunteering (in person)"');
			self.manager.store.removeByValue('fq', 'position_type:"Virtual Volunteering (from home)"');
			self.manager.store.removeByValue('fq', 'content_type:opportunity');
			self.manager.store.removeByValue('fq', 'intern_type:"City Vision Internship"');
		}

		var fq = self.manager.store.values('fq');
//console.log('manager store '+self.manager.store.values('fq'));
//console.log('manager store '+self.manager.store);
		for (var i = 0, l = fq.length; i < l; i++) {
			var filter_query=fq[i];
			var filter=''+filter_query;
			var geo_index = filter.indexOf("{!geofilt pt=");
			if(geo_index!=-1){
				$('#geofilt_facet').text(''+fq[i]);
				self.manager.store.removeByValue('fq', fq[i]);
			}
		}
		geofilt_facet=$('#geofilt_facet').text();
		
//console.log('line 374 ');
		$('#service_areas_label').text('Service Areas:');
		$('#facet_country_tax_label').text('Country:');
		$('#facet_province_tax_label').text('State/Province:');
//console.log('line 376 ');
//console.log(' ** VALUE is: '+value);
		if(value=='Foundation'){
//console.log(' VALUE trigger leftSidebarFoundations');
			leftSidebarFoundations(self);
			isFoundationSearch = true;

		}else if(value=='City Vision' || value=='CityVision' || value=='City Vision Internship'){
//console.log(' VALUE trigger leftSidebarInternships');
			leftSidebarInternships(self);
							isCVSearch = true; 
		}else if(value=='organization' && isFoundationSearch==false){
//console.log(' VALUE trigger leftSidebarOrganizations');
			leftSidebarOrganizations(self);
//console.log('manager store '+self.manager.store.values('fq'));
		}else if(value=='job'){
			leftSidebarJobs(self);
//console.log('manager store '+self.manager.store.values('fq'));
		}else if(value=='resume'){
//console.log('manager store '+self.manager.store.values('fq'));
			leftSidebarResumes(self);
		}else{
//console.log('  big ELSE - values '+value);

			if(value=='Virtual Volunteering (from home)'){
				leftSidebarVirtual(self)
			}else if(value=='Short-term Missions / Volunteer Internship'){
//console.log('manager store '+self.manager.store.values('fq'));
				leftSidebarSTM(self);
			}else if(value=='Local Volunteering (in person)'){
//console.log('  big ELSE - values '+value);
				leftSidebarLocal(self);
			}else{// if(value=='Local Volunteering (in person)'){

//				leftSidebarLocal(self);

//	console.log('manager store '+self.manager.store.values('fq'));
//console.log('adding facet category is: '+value);
//			self.manager.store.addByValue('fq', 'content_type:opportunity');
				self.manager.store.addByValue('fq', geofilt_facet);
//console.log('manager store '+self.manager.store.values('fq'));
//				$('#srchmethod').val('Local Volunteering (in person)');
			}
			$('#simplyhired').hide();
		}
	//alert('alert');

				 
		var fq_facets = self.manager.store.values('fq');
//console.log('fq_facets     manager store '+self.manager.store.values('fq'));
		var fq_facet_string = '   '+fq_facets;
		var b_hasFoundation = false;
		if(fq_facet_string.indexOf(',org_member_type:Foundation') != -1 || fq_facet_string.indexOf('org_member_type:Foundation') == 0){
			b_hasFoundation=true;
		}
		var title = '';
		var fq_length = fq_facets.length;
//console.log('line 1172				fq_facets is '+fq_facets);
//console.log(fq_length);
		if(fq_length>0){
			// iterate through fq facets
			for(var i=0; i<fq_length; i++){
				var t = fq_facets[i]+'';
//console.log('facet index is '+t);
				if(t.indexOf('geofilt')!=-1){
//console.log('geofilt ');
				}else{
					var j=t.indexOf(':');
//console.log('index of : is '+j);
					if(j!=-1){
					//if(j>t.length){
						var f_facet = t.substring(0,j);
						var f_value = t.substring(j+1);
//console.log('f_facet : f_value value is '+f_facet+' : '+f_value);
						f_value = f_value.replace(/"/g, '');
						if(fq_facet_string==true || (f_value.indexOf('Foundation')!=-1 && f_facet.indexOf('-org_member_type')!=-1 && f_facet.indexOf('position_type')!=-1)){
//console.log(' f_value                             trigger leftSidebarFoundations');
							leftSidebarFoundations(self);
							isFoundationSearch = true; 
						}else if(fq_facet_string==false){
//console.log('f_value is '+f_value);								
						if(f_value.indexOf('organization')!=-1 && isFoundationSearch == false){
//console.log('f_value                              trigger leftSidebarOrgs');
							leftSidebarOrganizations(self);
						}else if(f_value.indexOf('City Vision')!=-1){
//console.log('f_value                              trigger leftSidebarInternships');
							leftSidebarInternships(self);						
							isCVSearch = true; 
						}else if(f_value.indexOf('Short-term Missions / Volunteer Internship')!=-1){
							leftSidebarSTM(self);						
							isSTMSearch = true; 
						}else if(f_value.indexOf('Virtual Volunteer')!=-1){
							leftSidebarVirtual(self);
							isVirtualSearch = true; 
						}else if(f_value.indexOf('opportunity')!=-1 && leftSidebarSTM==false && isVirtualSearch==false){
							leftSidebarLocal(self);
						}else if(f_value.indexOf('job')!=-1){
							leftSidebarJobs(self);
						}else if(f_value.indexOf('resume')!=-1){
							leftSidebarResumes(self);
						}else{
//console.log('t is '+t+'   f_value is '+f_value);							
							if(t.indexOf('-org_member_type') == -1 && t.indexOf('full_user') == -1 && t.indexOf('status') == -1 && t.indexOf('hidden_source') == -1 && t.indexOf('organization') == -1 && leftSidebarSTM==false && isVirtualSearch==false){
								title += f_value + ', ';
							}
						}
					}
					}
				}
			}
//console.log('t is '+t+'    title is '+title);			
			  $('#filter_data_heading').text(title);
//console.log(' contenttype_heading is: ' + $('#contenttype_heading').text() );
			  $('#contenttype_heading').text(heading_suffix);
//console.log(' contenttype_heading is: ' + $('#contenttype_heading').text() );
			  
			  document.title = title + title_suffix;
		}
//console.log('670 manager store '+self.manager.store.values('fq'));
	  
		// haven't been able to figure out what accidentally is writing this - ?? for now, just specifically removing
		self.manager.store.removeByValue('fq', 'position_type:Foundation');
		
//console.log('BEFORE doRequest				manager store self '+self.manager.store.values('fq'));
//console.log('BEFORE doRequest				manager store this '+this.manager.store.values('fq'));
        self.manager.doRequest(0);
		
var q_end = ' '+self.manager.store.values('fq');		
if(	q_end.indexOf('content_type:organization') != -1){
	toggle_financial_data(self);
}
		
		
      }
//console.log(' service_areas_label is: ' + $('#service_areas_label').text() );
      return false;
    }
  },







  /**
   * @param {String} value The value.
   * @returns {Function} Sends a request to Solr if it successfully removes a
   *   filter query with the given value.
   */
  unclickHandlerAlt: function (value, init_values) {
    var self = this;
    return function () {
      if (self.remove(value)) {
        self.manager.doRequest(0);
      }
      return false;
    }
  },

  /**
   * @param {String} value The facet value.
   * @param {Boolean} exclude Whether to exclude this fq parameter value.
   * @returns {String} An fq parameter value.
   */
  fq: function (value, exclude) {
    return (exclude ? '-' : '') + this.field + ':' + AjaxSolr.Parameter.escapeValue(value);
  }
});
