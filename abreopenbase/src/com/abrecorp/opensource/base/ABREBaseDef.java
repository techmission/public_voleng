package com.abrecorp.opensource.base;

/**
* Title:        Constant Definistions Files
* Description:  Common Constants throught the base classes
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

public final class ABREBaseDef {

    // application context web JSP Application Server object Key
    public static final String WEBAPPSERVERKEY = "ABREAPPSERVER";
    // session context for session manager object
    public static final String WEBSESSIONKEY = "ABRESESSSERVER";

    // name of web.xml parameter for application property file
    public final static String APPLICATIONSITEPROPERTYFILE="applicationsitepropertyfile";
    public final static String APPLICATIONPROPERTYFILE="applicationpropertyfile";
    public final static String CONFIGFOLDERNAME="configfoldername";

    // Folder Name and Location for attachements
    public final static String ATTACH_FILE1_LOCATION="web.attach.folder";

    // email definitions
    public final static String SMTP_HOST="mail.smtp.host";
    public final static String SMTP_AUTHUSER="mail.smtp.authuser";
    public final static String SMTP_AUTHPASSWORD="mail.smtp.authpassword";
    public final static String SMTP_DEFAULTFROM="mail.smtp.defaultfrom";
    public final static String SMTP_SYSTEMADMIN="mail.smtp.systemadmin";

    // public final static String SMTP_DEFAULTTO="mail.smtp.defaultto";
    // public final static String SMTP_MSGHEAD="mail.smtp.messagehead";
    // public final static String SMTP_MAXBODYMSGSIZE="mail.smtp.maxbodymsgsize";

    // Search Index location
    public final static String SEARCHINDEX_LOCATION="app.searchindex.location";
    // Search Index Folder base for opportunities
    public final static String SEARCHINDEX_PROGRAMS_BASE="app.searchindex.prog.base";
    // Search Index Folder base for organizations
    public final static String SEARCHINDEX_SESSIONS_BASE="app.searchindex.session.base";
    // Search Index Folder base for members
    public final static String SEARCHINDEX_MEMBERS_BASE="app.searchindex.member.base";


}
