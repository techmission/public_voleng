package com.abrecorp.opensource.base;

/**
* Title:        Constant Definitions Files
* Description:  Common Constants throught the base classes
* Copyright:    Copyright (c) 2000-2003
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

public final  class ABREAppServerDef {


    // application context web JSP Application Server object Key
    public static final String WEBAPPSERVERKEY = "ABREAPPSERVER";
    // session context for session manager object
    public static final String WEBSESSIONKEY = "ABRESESSSERVER";

    // name of web.xml parameter for application property file
    public final static String APPLICATIONSITEPROPERTYFILE="applicationsitepropertyfile";
    public final static String APPLICATIONPROPERTYFILE="applicationpropertyfile";
    public final static String CONFIGFOLDERNAME="configfoldername";

    // logger property name
    public final static String LoggerCategoryName="app.logger.catname";
    public final static String LoggerFolder="app.logger.foldername";
    public final static String LoggerFile="app.logger.filename";
    public final static String DEBUGLEVEL="app.logger.debuglevel";

    // Database definitions 
    public final static String DATABASE_DEF_JDBC="app.dbase.jdbcclass";
    public final static String DATABASE_DEF_DBURL="app.dbase.jdbcURL";
    public final static String DATABASE_DEF_DSN="app.dbase.dsn";
    public final static String DATABASE_DEF_USER="app.dbase.user";
    public final static String DATABASE_DEF_PASSWORD="app.dbase.password";
    public final static String DATABASE_DEF_MAXCONNECTIONS="app.dbase.maxconn";
    public final static String DATABASE_DEF_MONITOR_THREAD_ACTIVE="app.dbase.monitorthreadactive";
    public final static String DATABASE_DEF_MONITOR_THREAD_SLEEPTIME="app.dbase.monitorthreadsleeptime";
    public final static String DATABASE_DEF_MONITOR_THREAD_MAXINUSETIME="app.dbase.monitorthreadmaxinusetime";
    public final static String DATABASE_DEF_MONITOR_THREAD_MIN_INACTIVE_CONN="app.dbase.mininactiveconn";
    public final static String DATABASE_DEF_MONITOR_THREAD_MAX_INACTIVE_TIME="app.dbase.maxinactivetime";
    public final static String DATABASE_DEF_LOOP_COUNT_THREAD="app.dbase.loopcounter";
    
    // ChrisvolOnRails app info
    public final static String DEVEL_RAILS_DB="devel.rails.db";
    public final static String STAGING_RAILS_DB="staging.rails.db";
    public final static String PUBLIC_RAILS_DB="public.rails.db";
    public final static String DEVEL_RAILS_URL_PREFIX="devel.rails.url.prefix";
    public final static String STAGING_RAILS_URL_PREFIX="staging.rails.url.prefix";
    public final static String PUBLIC_RAILS_URL_PREFIX="public.rails.url.prefix";
    
    // other app definitions
    public final static String ADMINISTRATIVE_PASSWORD="app.main.administrative.password";
    public final static String JSP_DEFAULT_TIMEOUT_SECONDS="jsp.default.timeout";

    public final static String SOLR_USERNAME="solr.auth.username";
    public final static String SOLR_PASSWORD="solr.auth.password";
    public final static String SOLR_PRIMARY_HOST="solr.primary.host";
    public final static String SOLR_STAGING_HOST="solr.staging.host";
    public final static String SOLR_PRIMARY_URL="solr.primary.url";
    public final static String SOLR_STAGING_URL="solr.staging.url";
    public final static String SOLR_ALT_PRIMARY_URL="solr.alt.primary.url";
    public final static String SOLR_ALT_STAGING_URL="solr.alt.staging.url";

    public final static String DRUPAL_SERVICES_USERNAME="app.drupal.services.username";
    public final static String DRUPAL_SERVICES_PASSWORD="app.drupal.services.password";
    public final static String DRUPAL_SERVICES_DOMAIN="app.drupal.services.domain";
    public final static String DRUPAL_SERVICES_APIKEY="app.drupal.services.apikey";
    public final static String DRUPAL_SERVICES_TESTING_DOMAIN="app.drupal.services.testing.domain";
    public final static String DRUPAL_SERVICES_TESTING_APIKEY="app.drupal.services.testing.apikey";
    public final static String DRUPAL_SERVICES_XMLRPC_PUBLIC_URL="app.drupal.services.xmlrpc.public.url";
    public final static String DRUPAL_SERVICES_XMLRPC_TESTING_URL="app.drupal.services.xmlrpc.testing.url";

    public final static String DRUPAL_COOKIE_LOGIN_INTERNAL_PWD="app.drupal.cookie.login.internal.pwd";
    public final static String DRUPAL_COOKIE_LOGIN_PWD="app.drupal.cookie.login.pwd";

    public final static String APP_GIGYA_SOCIALIZE_SECRETKEY="app.gigya.socialize.secretkey";
    
    public final static String APP_FACEBOOK_CONNECT_MAIN_APPID="app.facebook.connect.main.appid"; 
    public final static String APP_FACEBOOK_CONNECT_SECONDARY_APPID="app.facebook.connect.secondary.appid";
    public final static String APP_FACEBOOK_CONNECT_FAITH_APPID="app.facebook.connect.faith.appid";
    public final static String APP_FACEBOOK_CONNECT_PARTNER1_APPID="app.facebook.connect.partner1.appid";
    public final static String APP_FACEBOOK_CONNECT_PARTNER2_APPID="app.facebook.connect.partner2.appid";
    public final static String APP_FACEBOOK_CONNECT_SANDBOX_APPID="app.facebook.connect.sandbox.appid"; 
    public final static String APP_FACEBOOK_CONNECT_SANDBOX_SECRETKEY="app.facebook.connect.sandbox.secretkey";
    public final static String APP_FACEBOOK_CONNECT_MAIN_SECRETKEY="app.facebook.connect.main.secretkey";
    public final static String APP_FACEBOOK_CONNECT_SECONDARY_SECRETKEY="app.facebook.connect.secondary.secretkey";
    public final static String APP_FACEBOOK_CONNECT_PARTNER1_SECRETKEY="app.facebook.connect.partner1.secretkey";
    public final static String APP_FACEBOOK_CONNECT_PARTNER2_SECRETKEY="app.facebook.connect.partner2.secretkey";
    public final static String APP_FACEBOOK_CONNECT_FAITH_SECRETKEY="app.facebook.connect.faith.secretkey";
    public final static String APP_FACEBOOK_CONNECT_EXAMPLE_APPID="app.facebook.connect.example.appid";
    public final static String APP_FACEBOOK_CONNECT_EXAMPLE_SECRETKEY="app.facebook.connect.example.secretkey";
    
    public final static String APP_FACEBOOK_MAIN_APIKEY="app.facebook.main.apikey";
    public final static String APP_FACEBOOK_MAIN_SECRETKEY="app.facebook.main.secretkey";
    public final static String APP_FACEBOOK_SECONDARY_APIKEY="app.facebook.secondary.apikey";
    public final static String APP_FACEBOOK_SECONDARY_SECRETKEY="app.facebook.secondary.secretkey";
    public final static String APP_FACEBOOK_SANDBOX_APIKEY="app.facebook.sandbox.apikey";
    public final static String APP_FACEBOOK_SANDBOX_SECRETKEY="app.facebook.sandbox.secretkey";

    public final static String APP_MAIN_DOMAIN="app.main.domain";
    public final static String APP_MAIN_EMAIL="app.mail.email";
    public final static String APP_SECONDARY_DOMAIN="app.secondary.domain";
    public final static String APP_SECONDARY_EMAIL="app.secondary.email";
    public final static String APP_MAIN_DOMAIN_SHORT="app.main.domain.short";
    public final static String APP_SECONDARY_DOMAIN_SHORT="app.secondary.domain.short";
    public final static String APP_SITE_ORGNAME="app.site.orgname";
    public final static String APP_SITE_PHONE_SUPPORT="app.site.phone.support";

    public final static String APP_FAITH_DOMAIN="app.faith.domain";
    public final static String APP_FAITH_EMAIL="app.faith.email";
    public final static String APP_FAITH_DOMAIN_SHORT="app.faith.domain.short";

    public final static String APP_COOKIE_SECRETKEY="app.cookie.secretkey";
    public final static String APP_COOKIE_MAIN_DOMAIN="app.cookie.main.domain";
    public final static String APP_COOKIE_SECONDARY_DOMAIN="app.cookie.secondary.domain";
    public final static String APP_COOKIE_FAITH_DOMAIN="app.cookie.faith.domain";
    public final static String APP_COOKIE_SEC_FAITH_DOMAIN="app.cookie.sec.faith.domain";
    public final static String APP_COOKIE_PARTNER1_DOMAIN="app.cookie.partner1.domain";
    public final static String APP_COOKIE_TEST_MAIN_DOMAIN="app.cookie.test.main.domain";
    public final static String APP_COOKIE_TEST_FAITH_DOMAIN="app.cookie.test.faith.domain";
    public final static String APP_COOKIE_TEST_SECONDARY_DOMAIN="app.cookie.test.secondary.domain";
    public final static String APP_COOKIE_TEST_SEC_FAITH_DOMAIN="app.cookie.test.sec.faith.domain";
    public final static String APP_COOKIE_TEST_PARTNER1_DOMAIN="app.cookie.test.partner1.domain";
    public final static String APP_COOKIE_STAGING_MAIN_DOMAIN="app.cookie.staging.main.domain";
    
    public final static String MAIL_SMTP_HOST="mail.smtp.host";
    public final static String MAIL_SMTP_FROM="mail.smtp.from";
    public final static String MAIL_SMTP_USERPASSWORD="mail.smtp.userpassword";

    // unique ID attributes
    public final static String UNIQUEID_ATTRIBUTE_OBJECTID="objectid"; 
    // public final static String UNIQUEID_ATTRIBUTE_PERSON="person"; 
    // public final static String UNIQUEID_ATTRIBUTE_ORGANIZATION="organization";


}