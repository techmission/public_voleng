package com.abrecorp.opensource.voleng.brlayer;

/**
* Created 2006-03-29
* Business Rules Layer Object BRLO
* For Indovidual Processing
* @author David Marcillo
*/

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.json.JSONException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.DOMException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
//import org.json.JSONObject;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;


import java.util.Iterator;
/* 
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
*/

import com.abrecorp.opensource.appcodes.ApplicationCodesObj;
import com.abrecorp.opensource.base.ABREAppServerDef;
import com.abrecorp.opensource.base.ABREBaseBRLO;
import com.abrecorp.opensource.base.ABREDBConnection;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.AppSessionDTO;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.individual.PersonObj;
import com.abrecorp.opensource.javamail.ABREJavaSendMail;
import com.abrecorp.opensource.javamail.EmailObj;

public class IndividualsBRLO extends ABREBaseBRLO {

	/**
	* Constructor
	*/
	public IndividualsBRLO() {}

	public String getResumeFileName(PersonInfoDTO user) {
		return "UID="+user.getUserUID()+"_Name="+user.getUSPNameFirst()+"_"+user.getUSPNameLast();
	}
	
	public int indexAndTagResume(File resume, HttpServletRequest httpServletRequest, PersonInfoDTO user, ApplicationCodesBRLO appCodesBRLO) {
    	if(null == user) return -2;
    	if(null == httpServletRequest) return -2;
    	if(null == appCodesBRLO) return -2;

		//this will be unique Id used by Solr to index the file contents.
		String solrId = user.getUSPUsername() + "_UID-" + user.getUserUID() + "_UPnid-" + user.getUserProfileNID();
		try {
			appCodesBRLO.indexFilesSolrCell(resume.getAbsolutePath(), solrId, user, httpServletRequest);
		} catch (IOException e) {
			return -1;
		} catch (SolrServerException e) {
			return -2;
		}
							  
		// tag the userprofile with resume data
		int[] a_iData = user.getUSPServiceAreasArray();
		String[] a_aszData = user.getUSPServiceAreasStringArray();
		appCodesBRLO.tagResumeData(user, a_iData, a_aszData);

		// a_iData has all the TIDs to update in the DB, 
		//aCurrentUserObj.setUSPServiceAreasArray(a_iData);
		// run db query update to update the value of the resume in the dbm, as well as tags
		this.updateResume(user);
		  
		// and a_aszData has all the terms to update in the solr index
		//aCurrentUserObj.setUSPServiceAreasStringArray(a_aszData);
		  
		if(resume.getAbsolutePath().startsWith("C:\\")){
			try {
				appCodesBRLO.indexFilesSolrCell(resume.getAbsolutePath(), solrId, user, httpServletRequest);
			} catch (IOException e) {
				return -3;
			} catch (SolrServerException e) {
				return -4;
			}
//									  m_ApplBRLOObj.indexFilesSolrAltCell(filePath + "\\" + fileName, solrId, aCurrentUserObj, httpServletRequest);
		  }else{
			  try {
				appCodesBRLO.indexFilesSolrCell(resume.getAbsolutePath(), solrId, user, httpServletRequest);
			} catch (IOException e) {
				return -5;
			} catch (SolrServerException e) {
				return -6;
			}
			  try {
				appCodesBRLO.indexFilesSolrAltCell(resume.getAbsolutePath(), solrId, user, httpServletRequest);
			} catch (IOException e) {
				return -7;
			} catch (SolrServerException e) {
				return -8;
			}								  
		  }
		  return 0;
	}
	
	/**
	* test
	*/
/*	
    public void test() throws Exception {//String[] args
        String tempString="";
        String tempString2="";
        String tempString3="";
        String tempString4="";
        tempString+="";

        //URL url  = new URL("http://viralpatel.net/blogs/feed");
        URL url = new URL("http://www.allforgood.org/api/volopps?key=christianvolunteering&output=rss&vol_loc=Kansas%20City,MO&vol_dist=500&num=10&q=-detailurl:volunteermatch%20AND%20-detailurl:christianvolunteering%20AND%20-detailurl:churchvolunteering%20AND%20%28christian%20OR%20jesus%20OR%20catholic%20OR%20ministry%29");
//        URL url = new URL("http://www.allforgood.org/api/volopps?key=christianvolunteering&output=rss&vol_loc=Kansas%20City,MO&vol_dist=500&num=10&q=-detailurl:volunteermatch%20AND%20-detailurl:christianvolunteering%20AND%20-detailurl:churchvolunteering%20AND%20%28christian%20OR%20jesus%20OR%20catholic%20OR%20ministry%29");
        XmlReader reader = null;
     
        try {
     
          reader = new XmlReader(url);
          SyndFeed feed = new SyndFeedInput().build(reader);
          System.out.println("Feed Title: "+ feed.getTitle());
          tempString+="Feed Title: "+ feed.getTitle();

     
	         for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
	            SyndEntry entry = (SyndEntry) i.next();
	            System.out.println(entry.getTitle());
	            tempString2+=entry.getTitle()+"; ";
           }
         } finally {
            if (reader != null){
            	reader.close();
            }
        }
        tempString+="";

        URL url2 = new URL("http://www.urbanministry.org/afg-feed-full.xml");
        XmlReader reader2 = null;
     
        try {
     
          reader2 = new XmlReader(url2);
          //AFGSyndFeed feed = new SyndFeedInput().build(reader2);
          SyndFeed feed = new SyndFeedInput().build(reader2);
          System.out.println("Feed Title: "+ feed.getTitle());
          tempString3+="Feed Title: "+ feed.getTitle();

     
	         for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
	            SyndEntry entry = (SyndEntry) i.next();
	            System.out.println(entry.getTitle());
	            tempString4+=entry.getTitle()+"; ";
           }
         } finally {
            if (reader2 != null){
            	reader2.close();
            }
        }
        
        tempString3+="";
        
        
        tempString4+="";
    }
	
	// end-of method test()
*/
	//=== START send email Section ===>
	//=== START send email Section ===>
	//=== START send email Section ===>

	
	/**
	* check email address format
	*/
    public int checkEmailFormat( String emailin ){
		int iRetCode=0;
        if(null == emailin) return -1;
        emailin = emailin.trim();
        if(emailin.length() < 5) return -1;
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.checkEmailFormat( emailin );
		return iRetCode;
	}
	// end-of method checkEmailFormat()

	/**
	* send email message
	*/
	public int sendEmailMSG( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		if(null == aHeadObj){
			return -1;
		}
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.sendEmailMessage( aHeadObj );
		aHeadObj.appendErrorMsg( aEmailSendObj.getAllMessages() );
		return iRetCode;
	}
	// end-of method sendEmailMSG()

	//=== END send email Section ===>
	//=== END send email Section ===>
	//=== END send email Section ===>


    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>


	/**
	* delete Opportunity record
	*/
	public int deleteEmail( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("deleteEmail");
    	EmailObj aEmailObj = new EmailObj();
    	pConn = getDBConn();
    	iRetCode = aEmailObj.deleteEmailFromDB(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method deleteEmail()


    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getTaxonomyCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	EmailObj aEmailObj = new EmailObj();
		pConn = getDBConn();
		iRetCode = aEmailObj.getTaxonomyCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	/**
	* logApplication record
	*/
	public int logApplication( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
//    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("logApplication");
    	EmailObj aEmailObj = new EmailObj();

    	int iNextID = getNextUniqueID( ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
    	if( iNextID < 1 ){
    		setErr("FILE IndividualsBRLO, logApplication :/n     error getting unique id for " + ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
    		ErrorsToLog();
    		aHeadObj.appendErrorMsg(" database is not available at this time, please try later ");
    		return -1;
    	}
    	aHeadObj.setEmailId( iNextID );
    	pConn = getDBConn();
    	iRetCode = aEmailObj.logApplication(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}	
	/**
	* updateLogApplication record
	*/
	public int updateLogApplication( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
//    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateLogApplication");
    	EmailObj aEmailObj = new EmailObj();


    	if(aHeadObj.getEmailId()<1){
    		return -1;
    	}
    	pConn = getDBConn();
    	iRetCode = aEmailObj.updateLogApplication(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}	
	/**
	* getEmailId record
	*/
	public int getEmailId( EmailInfoDTO aHeadObj, int iMinutes ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("getEmailId");
    	EmailObj aEmailObj = new EmailObj();

    	if(aHeadObj.getEmailOppNID()<1){
    		return -5;
    	}
    	
    	//***************************************** NEED TO FIND THE EMAIL ID FIRST *************************

    	pConn = getDBConn();
    	iRetCode = aEmailObj.getEmailId(pConn, aHeadObj, iMinutes );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method getEmailId()
	
	/**
	* create Email record
	*/
	public int createEmail( EmailInfoDTO aHeadObj ){
		return createEmail(aHeadObj,0);
	}

	public int createEmail( EmailInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
//    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("createEmail");
    	EmailObj aEmailObj = new EmailObj();

    	if(aHeadObj.getEmailSentStatus().startsWith("(through external feeds:")){
    		// doesn't have to be a valid email address b/c it's not sending through our system; we are just tracking it
    	}else if(aHeadObj.getEmailSentStatus().startsWith("(feeds:")){
    		// doesn't have to be a valid email address b/c it's not sending through our system; we are just tracking it
    	}else{
	    	// validate email address format
	    	aszTemp = aHeadObj.getEmailFromUser();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("valid email address or username required  ");
//	    		return -1;
	    	}else{
		    	iRetCode = checkEmailFormat( aszTemp );
		    	if(0 != iRetCode){
		    		aHeadObj.appendErrorMsg("email format error");
//		    		return -1;
		    	}
	    	}
    	}
    	
    	// couldn't find an ID, so it sets a new one
    	if(iType!=0 || aHeadObj.getEmailId()<1){
	    	int iNextID = getNextUniqueID( ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
	    	if( iNextID < 1 ){
	    		setErr("FILE IndividualsBRLO, createEmail :/n     error getting unique id for " + ABREAppServerDef.UNIQUEID_ATTRIBUTE_OBJECTID );
	    		ErrorsToLog();
	    		aHeadObj.appendErrorMsg(" database is not available at this time, please try later ");
	    		return -1;
	    	}
	    	aHeadObj.setEmailId( iNextID );
    	}
    	pConn = getDBConn();
    	iRetCode = aEmailObj.createEmail(pConn, aHeadObj );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method createEmail()
	/**
	* updateEmail Email record
	*/
	public int updateEmail( EmailInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		int temp=0;
		ABREDBConnection pConn=null;
//    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateEmail");
    	EmailObj aEmailObj = new EmailObj();

    	if(aHeadObj.getEmailSentStatus().startsWith("(through external feeds:")){
    		// doesn't have to be a valid email address b/c it's not sending through our system; we are just tracking it
    	}else if(aHeadObj.getEmailSentStatus().startsWith("(feeds:")){
    		// doesn't have to be a valid email address b/c it's not sending through our system; we are just tracking it
    	}else{
	    	// validate email address format
	    	aszTemp = aHeadObj.getEmailFromUser();
	    	if(aszTemp.length() < 3){
	    		aHeadObj.appendErrorMsg("valid email address or username required  ");
	    		return -1;
	    	}else{
		    	iRetCode = checkEmailFormat( aszTemp );
		    	if(0 != iRetCode){
		    		aHeadObj.appendErrorMsg("email format error");
//		    		return -1;
		    	}
	    	}
    	}

    	pConn = getDBConn();
    	iRetCode = aEmailObj.updateEmail(pConn, aHeadObj, iType );
    	if(null != pConn) pConn.free();

    	return iRetCode;
	}
	// end-of method updateEmail()

    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>



	/**
	* deleteSessionIDFromSystem this session (grabbed from cookie) exist in the sessions table?
	*/
	public int deleteSessionIDFromSystem( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("deleteSessionIDFromSystem");

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
		iRetCode = aPersonObj.deleteSessionIDFromSystem(pConn, aHeadObj);
		
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method deleteSessionIDFromSystem()

	/**
	* does this session (grabbed from cookie) exist in the sessions table?
	*/
	public int IsSessionIDInSystem( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("IsSessionIDInSystem");

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	// first test to see if session value exists in db with given IP address and not expired (checks its within 30 days)
		iRetCode = aPersonObj.IsSessionIDInSystem(pConn, aHeadObj);
		
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method IsSessionIDInSystem()

	/**
	* setSessionIDInSystem
	*/
	public int setSessionIDInSystem( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	boolean bMinOK=true;
    	int iType=0;
    	int iUID=0;

    	MethodInit("setSessionIDInSystem");
    	if(aHeadObj.getCookieAuthorize() == PersonInfoDTO.COOKIE_USER){
        	iUID = aHeadObj.getUserUID();
        	if(iUID < 1){
    	    	//aHeadObj.appendErrorMsg(" A User Id Must be Provided to Log in through this method ");
    	    	bMinOK=false;
        	}
        	if(false == bMinOK) return -1;
        	iType = PersonInfoDTO.LOADBY_UID;
        }
     	
    	if(false == bMinOK) return -1;
    	// does user id exist
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	
		iRetCode = aPersonObj.updateLogin(pConn, aHeadObj, iType);
		
    	if(null != pConn) pConn.free();

    	return iRetCode;
    }
    // end-of method setSessionIDInSystem()
	

	/**
	* login a user
	*/
	public int loginUser( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	if(null == aszSiteVersion) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	int iType=0;
    	String aszTimestamp, aszSignature;
    	String aszUID = "";
    	int iUID=0;

    	MethodInit("loginUser");

		String aszPhoneSupport = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_PHONE_SUPPORT);
		String aszSiteOrgName = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
		String aszEmailMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
		String aszEmailSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
		String aszEmailFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_EMAIL);
		String aszDomFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN);
		String aszDomFaithShort = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN_SHORT);
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
		String aszDomSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
		String aszDomSecondaryShort = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
     	String aszAdminPassword = this.getSitePropertyValue(ABREAppServerDef.ADMINISTRATIVE_PASSWORD);
     	String aszInternalPwd = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_INTERNAL_PWD); 
     	String aszPwd = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_PWD); 
		String aszCookieDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_MAIN_DOMAIN);
		String aszTestDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_TEST_MAIN_DOMAIN);
		String aszPartner1Dom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_PARTNER1_DOMAIN);
//System.out.println("aHeadObj.getUSPInternalComment() is "+aHeadObj.getUSPInternalComment());

        if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebookapp") ){
        	//if(aHeadObj.getFacebookUID() < 1){
        	if(aHeadObj.getFacebookUID().length() < 1){ 
        		aHeadObj.appendErrorMsg("facebook user id must be passed for this to successfully log in user");
        		bMinOK=false;
        	}else{
        		aszTimestamp=aHeadObj.getTimestamp();
        		String aszFBapikey=aHeadObj.getFBapikey();
        		String aszFBsecretkey=aHeadObj.getFBsecretkey();
        		iType = PersonInfoDTO.LOADBY_FACEBOOK_UID;
        		// validate that the timestamp from facebook is within 2 min or within 1hr+-2min
        		boolean validatedTimestamp = false;
        		if(aszTimestamp.length()>0){
        			validatedTimestamp=validateUnixTimestamp(aszTimestamp, 2, aHeadObj); // within 2 min
            		if(validatedTimestamp==false){
System.out.println("facebook timestamp has expired.  ");
                		aHeadObj.appendErrorMsg("facebook timestamp has expired. ");
            			return -1;
            		}
        		}else{
System.out.println("facebook timestamp was not correctly set. ");
            		aHeadObj.appendErrorMsg("facebook timestamp was not correctly set. ");
        			return -1;
        		}
        		boolean validateFBapikey = false;
        		if(aszFBapikey.length()>0 || aszFBsecretkey.length()>0){
        			validateFBapikey=validateFBapikey(aszFBapikey, aszFBsecretkey, aHeadObj);
            		if(validateFBapikey==false){
            			aHeadObj.appendErrorMsg("'"+aHeadObj.getFBapikey()+"' is an invalid facebook application api key.");// it should be '"+this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_SECONDARY_APIKEY)+"'");
            			return -1;
            		}
        		}else{
        			aHeadObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
        			return -1;
        		}
        	}
        } else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebookConnect") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkFacebookAccount")){
        	//if(aHeadObj.getFacebookUID() < 1){
        	if(aHeadObj.getFacebookUID().length() < 1){ 
        		aHeadObj.appendErrorMsg("facebook user id must be passed for this to successfully log in user");
        		bMinOK=false;
        	}else{
        		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkFacebookAccount")){
//System.out.println("LOADBY_EMAIL ");
        			iType = PersonInfoDTO.LOADBY_EMAIL;
        		} else {
//System.out.println("LOADBY_FACEBOOK_UID ");
        			iType = PersonInfoDTO.LOADBY_FACEBOOK_UID;
        		}
        	}
        } else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkedinConnect") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkLinkedinAccount")) {
        	if(aHeadObj.getLinkedInId().length() < 1){ 
        		aHeadObj.appendErrorMsg("linkedin user id must be passed for this to successfully log in user");
        		bMinOK=false;
        	}else{
        		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkLinkedinAccount")){
//System.out.println("LOADBY_EMAIL ");
        			iType = PersonInfoDTO.LOADBY_EMAIL;
        		} else {
//System.out.println("LOADBY_FACEBOOK_UID ");
        			iType = PersonInfoDTO.LOADBY_LINKEDIN_ID;
        		}
        	}
        }else if(aHeadObj.getCookieAuthorize() == PersonInfoDTO.COOKIE_USER){
        	iUID = aHeadObj.getUserUID();
        	if(iUID < 1){
    	    	//aHeadObj.appendErrorMsg(" A User Id Must be Provided to Log in through this method ");
    	    	bMinOK=false;
        	}
        	if(false == bMinOK) return -1;
        	iType = PersonInfoDTO.LOADBY_UID;
        }else{
        	boolean bUsernameOrEmail=false;
        	aszTemp = aHeadObj.getUSPEmail1Addr();
        	if(aszTemp.length() < 5){
//        		aHeadObj.appendErrorMsg("valid email address or username required  ");
//        		bMinOK=false;
        	}else{
            	iRetCode = checkEmailFormat( aszTemp );
            	if(0 != iRetCode){
            		aHeadObj.setUSPUsername(aszTemp);
            		bUsernameOrEmail=true;
            	}else{
            		bUsernameOrEmail=true;
            	}
        	}
        	if(bUsernameOrEmail==false){
        		aHeadObj.appendErrorMsg("valid email address or username required  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPPassword();
        	if(aszTemp.length() < 3){
        		if(! aHeadObj.getUSPPasswordInternal().equalsIgnoreCase(aszInternalPwd)){
    	    		aHeadObj.appendErrorMsg("password required  ");
    	    		bMinOK=false;
        		}
        	}else if(aHeadObj.getUSPPassword().equalsIgnoreCase(aszPwd)){
        		iType = PersonInfoDTO.LOADBY_UID;
        	}
        	if(aszAdminPassword.length()==8){
            	if(aHeadObj.getUSPPassword().equals(aszAdminPassword)){
	        		aHeadObj.setAuthPass( PersonInfoDTO.AUTH_ADMIN_PWD );
	        		iType = PersonInfoDTO.LOADBY_AUTH_ADMIN_PWD;
            	}
        	}
        }
     	
     	
//System.out.println("logging in user " + aHeadObj.getUSPEmail1Addr() + "  with bMinOK " + bMinOK );
    	if(false == bMinOK) {
//System.out.println("       ERROR in user " + aHeadObj.getErrorMsg() );
    		return -1;
    	}
    	// does user id exist
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
//System.out.println("logging in user " + aHeadObj.getUSPEmail1Addr() + "  with aszRailsDB " + aszRailsDB );
		iRetCode = aPersonObj.loginUser(pConn, aHeadObj, iType, aszRailsDB);
		
    	if(null != pConn) pConn.free();
		if(aHeadObj.getUSPAccountStatus()==0){
			aHeadObj.appendErrorMsg("Your account has been restricted.  If you believe this has been done in error, please contact us.");
			return -999;
		}

/*setErr("this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB) " + this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB) + 
		" success with this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB) " + this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB) +
		" success with this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB) " + this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB) );
ErrorsToLog();
		setErr("logged in user " + aHeadObj.getUSPEmail1Addr() + " success with aszRailsDB " + aszRailsDB );
		ErrorsToLog();
*/
		return iRetCode;
    }
    // end-of method loginUser()
	
	/**
	* login a user
	*/
	public int userLoggedIn( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	if(null == aszSiteVersion) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	MethodInit("loginUser");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		//aHeadObj.appendErrorMsg("valid email address required  ");
    		//bMinOK=false;
    	}
    	iTemp = aHeadObj.getUserUID();
    	if(iTemp < 1){
    		// drupal user is not logged in - has an anonymous uid - can return and just say they were not logged in yet
    		return 777;
    	}
    	if(false == bMinOK) return -1;
    	// does user id exist
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	String aszRailsDB = "";
    	if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	iRetCode = aPersonObj.userLoggedIn(pConn, aHeadObj, aszRailsDB);
    	if(null != pConn) pConn.free();
   	
		if(aHeadObj.getUSPAccountStatus()==0){
			aHeadObj.appendErrorMsg("Your account has been restricted.  If you believe this has been done in error, please contact us.");
			return -999;
		}
    	return iRetCode;
	}
	// end-of method loginUser()

    /**
	* loadUserContactData from database
	*/
	public int loadUserContactData( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	MethodInit("loadUserContactData");
    	if(null == aHeadObj) return -2;
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.loadUserContactData( pConn, aHeadObj );
    	if(null != pConn) pConn.free();
		return iRetCode;
	}
    // end-of method loadUserContactData()

    /**
	* load user from database
	*/
	public int loadUserByOption( PersonInfoDTO aHeadObj, int iType, String aszSiteVersion ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	MethodInit("loadUserByOption");
    	if(null == aHeadObj) return -2;
    	if(null == aszSiteVersion) return -2;
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development") || aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging") || aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else if(! aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	iRetCode = aPersonObj.loadUserByOption( pConn, aHeadObj, iType, aszRailsDB);
    	if(null != pConn) pConn.free();
		return iRetCode;
	}
    // end-of method loadUserByOption()

	public int getQuestionnaireInstanceId(PersonInfoDTO person, OrgOpportunityInfoDTO opp, String aszSiteVersion) {
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	MethodInit("getQuestionnaireInstanceId");
    	if(null == person || null == opp || null == aszSiteVersion) return -2;
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development") || aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging") || aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else if(! aszSiteVersion.equals(this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB))){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	iRetCode = aPersonObj.getQuestionnaireInstanceId( pConn, person, opp, aszRailsDB);
    	if(null != pConn) pConn.free();
		return iRetCode;
	}
	
	/**
	* calculate personality type
	*/
    public int calculatePersonalityType( PersonInfoDTO aHeadObj ){
    	int iRetCode=0, iTemp=0;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	MethodInit("calculatePersonalityType");
		String aszPersonalityType = "";
		
		//Calculation
    	if(aHeadObj.getUSPPersonalityE() > aHeadObj.getUSPPersonalityI()){
    		aszPersonalityType += "E";
    	}
    	else if (aHeadObj.getUSPPersonalityE() != aHeadObj.getUSPPersonalityI()){
    		aszPersonalityType += "I";
    	}
    	
    	if(aHeadObj.getUSPPersonalityS() > aHeadObj.getUSPPersonalityN()){
    		aszPersonalityType += "S";
    	}
    	else if (aHeadObj.getUSPPersonalityS() != aHeadObj.getUSPPersonalityN()){
    		aszPersonalityType += "N";
    	}
    	
    	if(aHeadObj.getUSPPersonalityF() > aHeadObj.getUSPPersonalityT()){
    		aszPersonalityType += "F";
    	}
    	else if (aHeadObj.getUSPPersonalityF() != aHeadObj.getUSPPersonalityT()){
    		aszPersonalityType += "T";
    	}
    	
    	if(aHeadObj.getUSPPersonalityJ() > aHeadObj.getUSPPersonalityP()){
    		aszPersonalityType += "J";
    	}
    	else if (aHeadObj.getUSPPersonalityJ() != aHeadObj.getUSPPersonalityP()){
    		aszPersonalityType += "P";
    	}
    	
    	ABREDBConnection pConn=null;

		pConn = getDBConn();
		AppCodeInfoDTO aAppCodeObj = new AppCodeInfoDTO();
		
		//If there is not a personalityTID set, use the personality type to figure out the tid
		int iPersonalityTID = aHeadObj.getUSPPersonalityTID();
		
    	if(aHeadObj.getUSPPersonalityTID() == 0){
    	aHeadObj.setUSPPersonality(aszPersonalityType);
    
    	aCodes.getTaxonomyTID( pConn, aAppCodeObj, aszPersonalityType, iPersonalityTypeVid );
    	
    	aHeadObj.setUSPPersonalityTID( aAppCodeObj.getAPCIdSubType());
    	}
    	else { //If there is a personalityTID, use it to set the personalityType
    		aCodes.getTaxonomyName( pConn, aAppCodeObj, iPersonalityTID, iPersonalityTypeVid);
    		
    		aHeadObj.setUSPPersonality(aAppCodeObj.getAPCDisplay());
    	}
    	return iRetCode;

	}
	// end-of method calculatePersonalityType()

    
	/**
	* calculate personality type facets 
	* Currently, these facets are being calculated and stored, but not shown or used
	*/
    public int calculatePersonalityFacets( PersonInfoDTO aHeadObj ){
    	int iRetCode=0, iTemp=0;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	MethodInit("calculatePersonalityFacets");
		String aszPersonalityFacetEI = "";
		String aszPersonalityFacetSN = "";
		String aszPersonalityFacetFT = "";
		String aszPersonalityFacetJP = "";
		
		//Calculation
		// Each facet will be a positive number or a negative number
		// If the number is positive it represents a strength towards E, S, F, or J
		// If it is negative it represents a strength towards I, N, T, or P
		// For example, if aszPersonalityFacetEI is 5 it means the user answered 5 more questions
		// as E than I.  If aszPersonaltiyFacetEI is -5 the user answered 5 more questions
		// as I than E.
    	
    	aszPersonalityFacetEI = Integer.toString(aHeadObj.getUSPPersonalityE() - aHeadObj.getUSPPersonalityI());
    	aszPersonalityFacetSN = Integer.toString(aHeadObj.getUSPPersonalityS() - aHeadObj.getUSPPersonalityN());
    	aszPersonalityFacetFT = Integer.toString(aHeadObj.getUSPPersonalityF() - aHeadObj.getUSPPersonalityT());
    	aszPersonalityFacetJP = Integer.toString(aHeadObj.getUSPPersonalityJ() - aHeadObj.getUSPPersonalityP());
    	
    	aHeadObj.setUSPPersonalityEI(aszPersonalityFacetEI);
    	aHeadObj.setUSPPersonalitySN(aszPersonalityFacetSN);
    	aHeadObj.setUSPPersonalityFT(aszPersonalityFacetFT);
    	aHeadObj.setUSPPersonalityJP(aszPersonalityFacetJP);
   	
    	return iRetCode;

	}
	// end-of method calculatePersonalityType()
	
	/**
	* validateCookieLoginPwd
	*/
    public int validateCookieLoginPwd( PersonInfoDTO aHeadObj ){
    	int iRetCode=0, iTemp=0;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	MethodInit("validateCookieLoginPwd");
    	aHeadObj.setUSPPasswordInternal(this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_INTERNAL_PWD));
    	if(aHeadObj.getUSPPasswordInternal().equalsIgnoreCase(this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_INTERNAL_PWD))){
    		aHeadObj.setUSPPassword(this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_PWD));
    	}
   	
    	return iRetCode;

	}
	// end-of method validateCookieLoginPwd()
	/**
	* setCookieLoginPwd
	*/
    public int setCookieLoginPwd( PersonInfoDTO aHeadObj ){
    	int iRetCode=0, iTemp=0;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null ;
    	boolean bMinOK=true;
    	MethodInit("setCookieLoginPwd");
    	aHeadObj.setUSPPasswordInternal(this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_INTERNAL_PWD));
    	if(aHeadObj.getUSPPasswordInternal().equalsIgnoreCase(this.getSitePropertyValue(ABREAppServerDef.DRUPAL_COOKIE_LOGIN_INTERNAL_PWD))){
    		aHeadObj.setUSPPassword(aHeadObj.getUSPPasswordInternal());
    	}
   	
    	return iRetCode;

	}
	// end-of method setCookieLoginPwd()
	
    /**
	* update indiviudal head record
	*/
	public int updateIndividualPortal( PersonInfoDTO aHeadObj ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualPortal");
    	iTemp = aHeadObj.getPortal();
    	if(iTemp < 2){
    		// didn't update the portal
    		return -1;
    	}
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualPortal(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* update indiviudal head record
	*/
	public int updateIndividualHead( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHead");
    	if(aHeadObj.getORGNID()!=iDisasterReliefOrgNID){
	    	iTemp = aHeadObj.getBirthYear();
	    	if(iTemp > 0){
	    		if(iTemp<1900 || iTemp>2020){
	        		aHeadObj.appendErrorMsg("- Please Enter a Valid 4-digit Birth Year \n");
	        		bMinOK=false;
	    		}
	    	}
	    	aszTemp = aHeadObj.getUSPNameFirst();
	    	if(aszTemp.length() < 2){
	    		aHeadObj.appendErrorMsg("- First Name Required \n");
	    		bMinOK=false;
	    	}
	    	aszTemp = aHeadObj.getUSPNameLast();
	    	if(aszTemp.length() < 2){
	    		aHeadObj.appendErrorMsg("- Last Name Required \n");
	    		bMinOK=false;
	    	}
    	}
    	aszTemp = aHeadObj.getUSPSiteUseType();
    	iTemp = aHeadObj.getUSPVolunteerTID();
    	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
    		aHeadObj.appendErrorMsg("- You must indicate whether you are using this site as a volunteer, organization, or church. \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}
        	aszTemp = aHeadObj.getUSPPhone1();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("Phone Number Required,  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("Country Required,  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.equalsIgnoreCase("us")){
	        	aszTemp = aHeadObj.getUSPAddrPostalcode();
	        	if(aszTemp.length() < 2){
	        		aHeadObj.appendErrorMsg("Postal Code Required if in the United States,  ");
	        		bMinOK=false;
	        	}
        	}
    	}
    	iTemp= aHeadObj.getUSPVolunteerTID();
    	if(iTemp == iVolDirectorytid){
    		int iCount=0;
    		iTemp = aHeadObj.getUSPLocalVolTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPGroupFamilyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolBoardTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolVirtTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSumIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPWorkStudyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPJobsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConferenceTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConsultingTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSocJustGrpsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingFor().length()>1){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingForArray().length>1){
    			iCount++;
    		}
    		if(iCount < 1){
        		aHeadObj.appendErrorMsg("\n- With a Public Listing You Must Select What You Are Looking For  \n");
        		bMinOK=false;
    		}
    	}
    	///*
    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
    		aHeadObj.setUSPLocalVolTID(-1);
    		aHeadObj.setUSPGroupFamilyTID(-1);
    		aHeadObj.setUSPVolBoardTID(-1);
    		aHeadObj.setUSPVolVirtTID(-1);
    		aHeadObj.setUSPIntrnTID(-1);
    		aHeadObj.setUSPSumIntrnTID(-1);
    		aHeadObj.setUSPWorkStudyTID(-1);
    		aHeadObj.setUSPJobsTID(-1);
    		aHeadObj.setUSPConferenceTID(-1);
    		aHeadObj.setUSPConsultingTID(-1);
    		aHeadObj.setUSPSocJustGrpsTID(-1);
    	}
    	//*/
    	
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
			aHeadObj.setUSPVolSkills("");
			aHeadObj.setUSPVolSkills1TID(-1);
			aHeadObj.setUSPVolSkills2("");
			aHeadObj.setUSPVolSkills2TID(-1);
			aHeadObj.setUSPVolSkills3("");
			aHeadObj.setUSPVolSkills3TID(-1);
			aHeadObj.setUSPVolInterestArea1("");
			aHeadObj.setUSPVolInterestArea1TID(-1);
			aHeadObj.setUSPVolInterestArea2("");
			aHeadObj.setUSPVolInterestArea2TID(-1);
			aHeadObj.setUSPVolInterestArea3("");
			aHeadObj.setUSPVolInterestArea3TID(-1);
		}
		
    	if(false == bMinOK) return -1;
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
       	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	iRetCode = aPersonObj.updateIndividualHead(pConn, aHeadObj, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHead()
	
    /**
	* update indiviudal head record - email
	*/
	public int updateIndividualHeadEmail( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadEmail");

    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase(aHeadObj.getUSPEmail1Addr())){
    		return 0;
    	}

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;

    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("Email format error");
    		return -1;
    	}

    	// first check if email address exists in database
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	if(iRetCode == 0 ){
    		aHeadObj.appendErrorMsg("Email address is already in system ");
    		return -1;
    	}

    	//PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadEmail(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadEmail()
	
    /**
	* update indiviudal head record - password
	*/
	public int updateIndividualHeadPwd( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPwd");

    	aszTemp = aHeadObj.getUSPPassword();
    	if(aHeadObj.getUSPUserComment().equalsIgnoreCase(aszTemp)
    			|| aHeadObj.getUSPUserComment().equalsIgnoreCase("")){
    		aHeadObj.setUSPPassword(aHeadObj.getUSPUserComment());

    		return 0;
    	}
    	if (aHeadObj.getPasswordConfirm().equalsIgnoreCase(aszTemp)){
        	PersonObj aPersonObj = new PersonObj();
        	pConn = getDBConn();
        	iRetCode = aPersonObj.updateIndividualHeadPwd(pConn, aHeadObj);
        	if(null != pConn) pConn.free();
        	return iRetCode;
    	}else{
    		aHeadObj.appendErrorMsg("Passwords don't match");

    	}
    	return -1;	
	}
    // end-of method updateIndividualHeadPwd()

	
    /**
	* update indiviudal head record
	*/
	public int updateIndividualHeadUserName( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null, aszOrigUsername=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadUserName");
    	aszOrigUsername = aHeadObj.getUSPInternalComment();
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Username required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;
    	// does user id exist
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();

    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase(aHeadObj.getUSPUsername())){
    		return 0;
    	}

    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
        	// check if user id (username) is already used in the system (drupal database) 
        	if(	aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
        		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
        		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
        	){
		    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
		    	if(null != pConn) pConn.free();
		    	if(iRetCode == 0 ){
		    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n ");
		    		if(aszOrigUsername.length()>1){
			    		aHeadObj.setUSPUsername(aszOrigUsername); // set the username back to the original; not the one from the user form
		    		}
		    		return -1;
		    	}
        	}
    	}
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadUserName(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadUserName()

	 /**
	* update indiviudal's facebook user id
	*/
	public int updateIndividualHeadFacebookConnection( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadFacebookUID");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadFacebookConnection(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadPersonality()
	
	 /**
	* update indiviudal's personality data
	*/
	public int updateIndividualHeadPersonality( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPersonality");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadPersonality(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadPersonality()
	
	 /**
	* update indiviudal's ministry areas data
	*/
	public int updateIndividualHeadInterestsSkills( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPersonality");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadInterestsSkills(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadPersonality()
	
	 /**
	* update indiviudal's learning interests data for the Training Tab
	*/
	public int updateIndividualHeadLearningInterests( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPersonality");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadLearningInterests(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadPersonality()
	
	 /**
	* update select data for the individual depending on the page that called the function
	*/
	public int updateIndividualHeadPersonalitySelectFields( PersonInfoDTO aHeadObj, String personalityPage ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPersonalitySelectFields");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadPersonalitySelectFields(pConn, aHeadObj,personalityPage);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadPersonality()
	
	/**
	* update indiviudal's personality data
	*/
	public int updateIndividualHeadMinistryAreasTest( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadMinistryAreasTest");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadMinistryAreasTest(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadMinistryAreasTest()
	
	 /**
	* update indiviudal's facebook published status
	*/
	public int updateIndividualHeadPersonalityPublished( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadPersonalityPublished");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadPersonalityPublished(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateIndividualHeadEmail()
    
    /**
	* register a new indiviudal
	*/
	public int registerNewIndividual( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("registerNewIndividual");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}

		//if (aHeadObj.getUSPInternalUserTypeComment().length() < 1){
			if (aHeadObj.getUSPUsername().length() < 1){
				// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
				aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
			}
		//}

		aszPassVerify = aHeadObj.getPasswordConfirm();
    	if(aszPassVerify.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Verification Required \n");
    		bMinOK=false;
    	}
    	aszPassword = aHeadObj.getUSPPassword();
    	if(aszPassword.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Required \n");
    		bMinOK=false;
    	}
    	iTemp = aHeadObj.getBirthYear();
    	if(iTemp > 0){
    		if(iTemp<1900 || iTemp>2020){
        		aHeadObj.appendErrorMsg("- Please Enter a Valid 4-digit Birth Year \n");
        		bMinOK=false;
    		}
    	}
    	aszTemp = aHeadObj.getUSPNameFirst();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- First Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPNameLast();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Last Name Required  \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Username Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Email Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPSiteUseType();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must indicate whether you are using this site as a volunteer, organization, or church. \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}
        	aszTemp = aHeadObj.getUSPPhone1();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Phone Number Required \n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("Country Required,  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.equalsIgnoreCase("us")){
	        	aszTemp = aHeadObj.getUSPAddrPostalcode();
	        	if(aszTemp.length() < 2){
	        		aHeadObj.appendErrorMsg("Postal Code Required if in the United States,  ");
	        		bMinOK=false;
	        	}
        	}
    	}
    	iTemp= aHeadObj.getUSPVolunteerTID();
    	if(iTemp == iVolDirectorytid && ( ! aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook"))){
    		int iCount=0;
    		iTemp = aHeadObj.getUSPLocalVolTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPGroupFamilyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolBoardTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolVirtTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSumIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPWorkStudyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPJobsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConferenceTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConsultingTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSocJustGrpsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingFor().length()>1){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingForArray().length>1){
    			iCount++;
    		}
    		if(iCount < 1){
        		aHeadObj.appendErrorMsg("- With a Public Listing, You Must Select What You Are Looking For \n");
        		bMinOK=false;
    		}
/* LEGACY - Gigya Socialize     		
    	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
        	String aszTimestamp, aszSignature;
        	String aszUID="";
        	int iUID=0;
        	aszSignature = aHeadObj.getSignature();
        	if(aszSignature.length() < 3){
        		aHeadObj.appendErrorMsg(" A Signature Must be Provided from Gigya Socialize  ");
        		bMinOK=false;
        	}
        	aszTimestamp = aHeadObj.getTimestamp();
        	if(aszTimestamp.length() < 3){
        		aHeadObj.appendErrorMsg(" A Timestamp Must be Provided from Gigya Socialize ");
    	    	bMinOK=false;
        	}
        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
            	iUID = aHeadObj.getUserUID();
            	if(iUID < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize")){
            	aszUID = aHeadObj.getUserUIDString();
            	if(aszUID.length() < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}
        	if(false == bMinOK) return -1;

        	iRetCode = validateSignature(aszTimestamp, iUID, aszUID, aszSignature, PersonInfoDTO.GIGYA_USER);
        	
        	if(iRetCode < 0){
            	if(iRetCode == -555){
        	    	aHeadObj.appendErrorMsg(" The Link to External Account Failed b/c Too Much Time Has Passed. Please try again ");
            	}else{
        	    	aHeadObj.appendErrorMsg(" The Signature Provided Was Not Correctly Validated ");
            	}
    	    	return -1;
        	}
*/        	
    	}

    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPAgree2Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must be 16 years or older or have your parents' permission to use this site \n");
    		bMinOK=false;
    	}
    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase(aszDomWorldVision)){
        	aszTemp = aHeadObj.getUSPAgreeWVFg();
        	if(aszTemp.length() < 1){
        		aHeadObj.appendErrorMsg("- You must Accept the WorldVision Terms & Conditions \n");
        		bMinOK=false;
        	}
    	}
        if(false == aszPassword.equalsIgnoreCase(aszPassVerify)){
    		aHeadObj.appendErrorMsg("- Passwords don't match. \n");
    		bMinOK=false;
        }
    	if(false == bMinOK){
    		return -1;
    	}
    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("- Email format error\n");
    		return -1;
    	}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false && aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") == false){
        	if(	aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
            	){
	        	// check if email address is already used in the system (drupal database) 
	    		iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
	        	if(null != pConn) pConn.free();
	        	if(iRetCode == 0 ){
	        		aHeadObj.appendErrorMsg("\nEmail address " + aHeadObj.getUSPEmail1Addr() + " already exists.\n\n " +
	    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com, please log in with that email address\n");
	        		return -1;
	        	} 
        	}
    	}
    	
    	// if a username is not already set, create one by default using FirstnameLastname
		if ((aHeadObj.getUSPInternalUserTypeComment().length() < 1) || (aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase("facebook"))){
			if (aHeadObj.getUSPUsername().length() < 1){
				// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
				aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
			}
		}

    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false){
        	// check if user id (username) is already used in the system (drupal database) 
	    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
	    	if(null != pConn) pConn.free();
	    	if(iRetCode == 0 ){
	    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n " +
	    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com, please log in with that email address\r\n\r\n" +
	    				"Otherwise, try adding a middle initial to your first name\r\n\r\n");
	    		return -1;
	    	}
    	//}}
		    
        	String aszRailsDB = "";
    	    if(aszSiteVersion.equals("development")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
    	    }else if(aszSiteVersion.equals("staging")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
    	    }else{
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
    	    }
    	pConn = getDBConn();
    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, PersonInfoDTO.REGISTER_USER, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method registerNewIndividual()

	 /**
	* register a new individual from the Facebook application
	* This creates a partial user record for the Facebook app
	*/
	public int registerNewFacebookAppIndividual( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("registerNewFacebookAppIndividual");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}

		//if (aHeadObj.getUSPInternalUserTypeComment().length() < 1){
			if (aHeadObj.getUSPUsername().length() < 1){
				// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
				aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
			}
		//}

		aszPassVerify = aHeadObj.getPasswordConfirm();
    	if(aszPassVerify.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Verification Required \n");
    		bMinOK=false;
    	}
    	aszPassword = aHeadObj.getUSPPassword();
    	if(aszPassword.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPNameFirst();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- First Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPNameLast();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Last Name Required  \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Username Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Email Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPSiteUseType();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must indicate whether you are using this site as a volunteer, organization, or church. \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    		/*
    		aHeadObj.setUSPGroupFamilyTID(0);
    		aHeadObj.setUSPVolBoardTID(0);
    		aHeadObj.setUSPVolVirtTID(0);
    		aHeadObj.setUSPIntrnTID(0);
    		aHeadObj.setUSPSumIntrnTID(0);
    		aHeadObj.setUSPWorkStudyTID(0);
    		aHeadObj.setUSPJobsTID(0);
    		aHeadObj.setUSPConferenceTID(0);
    		aHeadObj.setUSPConsultingTID(0);
    		aHeadObj.setUSPSocJustGrpsTID(0);
    		*/
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        		/*
        		aHeadObj.setUSPGroupFamilyTID(0);
        		aHeadObj.setUSPVolBoardTID(0);
        		aHeadObj.setUSPVolVirtTID(0);
        		aHeadObj.setUSPIntrnTID(0);
        		aHeadObj.setUSPSumIntrnTID(0);
        		aHeadObj.setUSPWorkStudyTID(0);
        		aHeadObj.setUSPJobsTID(0);
        		aHeadObj.setUSPConferenceTID(0);
        		aHeadObj.setUSPConsultingTID(0);
        		aHeadObj.setUSPSocJustGrpsTID(0);
        		*/
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        		
        		/*aHeadObj.setUSPGroupFamilyTID(0);
        		aHeadObj.setUSPVolBoardTID(0);
        		aHeadObj.setUSPVolVirtTID(0);
        		aHeadObj.setUSPIntrnTID(0);
        		aHeadObj.setUSPSumIntrnTID(0);
        		aHeadObj.setUSPWorkStudyTID(0);
        		aHeadObj.setUSPJobsTID(0);
        		aHeadObj.setUSPConferenceTID(0);
        		aHeadObj.setUSPConsultingTID(0);
        		aHeadObj.setUSPSocJustGrpsTID(0);*/
        		
        	}
        	/*aszTemp = aHeadObj.getUSPPhone1();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Phone Number Required \n");
        		bMinOK=false;
        	}*/
        	aszTemp = aHeadObj.getUSPAddrPostalcode();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Postal Code Required \n");
        		bMinOK=false;
        	}
        	/*aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Country Required \n");
        		bMinOK=false;
        	}*/
    	}
    	/*iTemp= aHeadObj.getUSPVolunteerTID();
    	if(iTemp == iVolDirectorytid && ( ! aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook"))){
    		int iCount=0;
    		iTemp = aHeadObj.getUSPLocalVolTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPGroupFamilyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolBoardTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPVolVirtTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSumIntrnTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPWorkStudyTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPJobsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConferenceTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPConsultingTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		iTemp = aHeadObj.getUSPSocJustGrpsTID();
    		if(iTemp > 0){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingFor().length()>1){
    			iCount++;
    		}
    		if(aHeadObj.getUSPLookingForArray().length>1){
    			iCount++;
    		}
    		if(iCount < 1){
        		aHeadObj.appendErrorMsg("- With a Public Listing, You Must Select What You Are Looking For \n");
        		bMinOK=false;
    		}
    	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
        	String aszTimestamp, aszSignature;
        	String aszUID="";
        	int iUID=0;
        	aszSignature = aHeadObj.getSignature();
        	if(aszSignature.length() < 3){
        		aHeadObj.appendErrorMsg(" A Signature Must be Provided from Gigya Socialize  ");
        		bMinOK=false;
        	}
        	aszTimestamp = aHeadObj.getTimestamp();
        	if(aszTimestamp.length() < 3){
        		aHeadObj.appendErrorMsg(" A Timestamp Must be Provided from Gigya Socialize ");
    	    	bMinOK=false;
        	}
        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
            	iUID = aHeadObj.getUserUID();
            	if(iUID < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize")){
            	aszUID = aHeadObj.getUserUIDString();
            	if(aszUID.length() < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}
        	if(false == bMinOK) return -1;

        	iRetCode = validateSignature(aszTimestamp, iUID, aszUID, aszSignature, PersonInfoDTO.GIGYA_USER);
        	
        	if(iRetCode < 0){
            	if(iRetCode == -555){
        	    	aHeadObj.appendErrorMsg(" The Link to External Account Failed b/c Too Much Time Has Passed. Please try again ");
            	}else{
        	    	aHeadObj.appendErrorMsg(" The Signature Provided Was Not Correctly Validated ");
            	}
    	    	return -1;
        	}
    	}*/

    	/*aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPAgree2Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must be 16 years or older or have your parents' permission to use this site \n");
    		bMinOK=false;
    	}*/
    	
    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}else{
    		aHeadObj.setUSPAgree2Fg("Yes");
    	}
    	
    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase(aszDomWorldVision)){
        	aszTemp = aHeadObj.getUSPAgreeWVFg();
        	if(aszTemp.length() < 1){
        		aHeadObj.appendErrorMsg("- You must Accept the WorldVision Terms & Conditions \n");
        		bMinOK=false;
        	}
    	}
        if(false == aszPassword.equalsIgnoreCase(aszPassVerify)){
    		aHeadObj.appendErrorMsg("- Passwords don't match. \n");
    		bMinOK=false;
        }
    	if(false == bMinOK){
    		return -1;
    	}
    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("- Email format error\n");
    		return -1;
    	}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false && aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") == false){
        	if(	aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
            	){
	        	// check if email address is already used in the system (drupal database) 
	    		iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
	        	if(null != pConn) pConn.free();
	        	if(iRetCode == 0 ){
	        		aHeadObj.appendErrorMsg("\nEmail address " + aHeadObj.getUSPEmail1Addr() + " already exists.\n\n " +
	    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com, please log in with that email address\n");
	        		return -1;
	        	} 
        	}
    	}
    	
    	// if a username is not already set, create one by default using FirstnameLastname
		if ((aHeadObj.getUSPInternalUserTypeComment().length() < 1) || (aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase("facebook"))){
			if (aHeadObj.getUSPUsername().length() < 1){
				// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
				aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
			}
		}

    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false){
        	// check if user id (username) is already used in the system (drupal database) 
	    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
	    	if(null != pConn) pConn.free();
	    	if(iRetCode == 0 ){
	    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n " +
	    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com, please log in with that email address\r\n\r\n" +
	    				"Otherwise, try adding a middle initial to your first name\r\n\r\n");
	    		return -1;
	    	}
    	//}}
		    
        	String aszRailsDB = "";
    	    if(aszSiteVersion.equals("development")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
    	    }else if(aszSiteVersion.equals("staging")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
    	    }else{
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
    	    }
    	
    	pConn = getDBConn();
    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, PersonInfoDTO.CREATE_USER_FBAPP, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method registerNewIndividual()
	
    /**
	* createAccount1
	*/
	public int createAccount1( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		return createAccount1( aHeadObj, aszSiteVersion, "");
	}
	public int createAccount1( PersonInfoDTO aHeadObj, String aszSiteVersion, String aszAccountType ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("createAccount1");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}
    	aszPassword = aHeadObj.getUSPPassword();
    	if(aszPassword.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Required \n");
    		bMinOK=false;
    	}
		aszPassVerify = aHeadObj.getPasswordConfirm();
    	if(aszPassVerify.length() < 3){
    		aHeadObj.appendErrorMsg("- Password Verification Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Username Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Email Required \n");
    		bMinOK=false;
    	}
    	if(aszAccountType.equals("short")){
    		aHeadObj.setUSPSiteUseType(aszOrganizationUser);
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    		aHeadObj.setUSPAgree1Fg("Yes");
    	}
    	aszTemp = aHeadObj.getUSPNameFirst();
	    if(aszTemp.length() < 2){
	    	aHeadObj.appendErrorMsg("- First Name Required \n");
	    	bMinOK=false;
	    }
	    aszTemp = aHeadObj.getUSPNameLast();
	    if(aszTemp.length() < 2){
	    	aHeadObj.appendErrorMsg("- Last Name Required  \n");
	    	bMinOK=false;
	    }
		if (aHeadObj.getUSPUsername().length() < 1){
			// in case there is no username, generate one as FirstLast
			aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
		}
    	
    	aszTemp = aHeadObj.getUSPSiteUseType();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must indicate whether you are using this site as a volunteer, organization, or church. \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}
    	}

    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}else{
    		aHeadObj.setUSPAgree2Fg("Yes");
    	}
    	/*
    	aszTemp = aHeadObj.getUSPAgree2Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- You must be 16 years or older or have your parents' permission to use this site \n");
    		bMinOK=false;
    	}
    	*/
    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase(aszDomWorldVision)){
        	aszTemp = aHeadObj.getUSPAgreeWVFg();
        	if(aszTemp.length() < 1){
        		aHeadObj.appendErrorMsg("- You must Accept the WorldVision Terms & Conditions \n");
        		bMinOK=false;
        	}
    	}
        if(false == aszPassword.equalsIgnoreCase(aszPassVerify)){
    		aHeadObj.appendErrorMsg("- Passwords don't match. \n");
    		bMinOK=false;
        }
    	if(false == bMinOK){
    		return -1;
    	}
    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("- Email format error\n");
    		return -1;
    	}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false && aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") == false){
        	if(	aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
        	){
	        	// check if email address is already used in the system (drupal database) 
	    		iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
	        	if(null != pConn) pConn.free();
	        	if(iRetCode == 0 ){
	        		aHeadObj.appendErrorMsg("\nEmail address " + aHeadObj.getUSPEmail1Addr() + " already exists.\n\n" +
	    				"If you have an account with any of our partner sites:\r\nUrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com,\r\nplease log in with that email address\n");
	        		return -1;
	        	} 
        	}
    	}

    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false){
        	// check if user id (username) is already used in the system (drupal database) 
	    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
	    	if(null != pConn) pConn.free();
	    	iTemp=0;
	    	String aszInitUsername=aHeadObj.getUSPUsername();
	    	while(iRetCode==0){
	    		aHeadObj.setUSPUsername(aszInitUsername+"-"+iTemp);
	    		iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
	    		iTemp++;
	    	}
	    	if(iRetCode == 0 ){
	    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n" +
	    				"If you have an account with any of our partner sites:\r\nUrbanMinistry.org, CCDA.org, or " +
	    				"Volunteer.Gospel.com,\r\nplease log in with that email address\r\n\r\n" +
	    				"Otherwise, try adding a middle initial to your first name\r\n\r\n");
	    		return -1;
	    	}
    	//}}
	    
        	String aszRailsDB = "";
    	    if(aszSiteVersion.equals("development")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
    	    }else if(aszSiteVersion.equals("staging")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
    	    }else{
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
    	    }
System.out.println("BRLO createAccount1 before insert");
    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, PersonInfoDTO.CREATE_USER_PT1, aszRailsDB);
System.out.println("BRLO createAccount1 after insert");
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method createAccount1()
    /**
	* upgradePlainDrupalUser
	*/
	public int upgradePlainDrupalUser( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("createAccount1");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}
    		aHeadObj.setUSPSiteUseType(aszOrganizationUser);
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    		aHeadObj.setUSPAgree1Fg("Yes");

    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}else{
    		aHeadObj.setUSPAgree2Fg("Yes");
    	}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	// check to see if the user is a registered user in drupal database who's creating a voleng account
	    
        	String aszRailsDB = "";
    	    if(aszSiteVersion.equals("development")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
    	    }else if(aszSiteVersion.equals("staging")){
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
    	    }else{
    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
    	    }

    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, PersonInfoDTO.MIGRATE_DRUPAL_USER, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method upgradePlainDrupalUser()

	
	
    /**
	* createAccount2 - LEGACY - should be replaced by chrisvol_on_rails app
	*/
	public int createAccount2( PersonInfoDTO aHeadObj, int iType, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("registerNewIndividual");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
		if(iType==PersonInfoDTO.CREATE_USER_CVINTERNAPPLIC){
			
		}else{
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}
    	iTemp = aHeadObj.getBirthYear();
    	if(iTemp > 0){
    		if(iTemp<1900 || iTemp>2020){
        		aHeadObj.appendErrorMsg("- Please Enter a Valid 4-digit Birth Year \n");
        		bMinOK=false;
    		}
    	}
    	aszTemp = aHeadObj.getUSPNameFirst();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- First Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPNameLast();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Last Name Required  \n");
    		bMinOK=false;
    	}
		if (aHeadObj.getUSPUsername().length() < 1){
			// in case there is no username, generate one as FirstLast
			aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
		}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Username Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Email Required \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}
        	aszTemp = aHeadObj.getUSPPhone1();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Phone Number Required \n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("Country Required,  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.equalsIgnoreCase("us")){
	        	aszTemp = aHeadObj.getUSPAddrPostalcode();
	        	if(aszTemp.length() < 2){
	        		aHeadObj.appendErrorMsg("Postal Code Required if in the United States,  ");
	        		bMinOK=false;
	        	}
        	}
    	}
    	iTemp= aHeadObj.getUSPVolunteerTID();
    	if(iTemp == iVolDirectorytid && ( ! aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook"))){
    		int iCount=0;
    		if(aHeadObj.getUSPLocalVolTID() > 0) iCount++;
    		if(aHeadObj.getUSPGroupFamilyTID() > 0) iCount++;
    		if(aHeadObj.getUSPVolBoardTID() > 0) iCount++;
    		if(aHeadObj.getUSPVolVirtTID() > 0) iCount++;
    		if(aHeadObj.getUSPIntrnTID() > 0) iCount++;
    		if(aHeadObj.getUSPSumIntrnTID() > 0) iCount++;
    		if(aHeadObj.getUSPWorkStudyTID() > 0) iCount++;
    		if(aHeadObj.getUSPJobsTID() > 0) iCount++;
    		if(aHeadObj.getUSPConferenceTID() > 0) iCount++;
    		if(aHeadObj.getUSPConsultingTID() > 0) iCount++;
    		if(aHeadObj.getUSPSocJustGrpsTID() > 0) iCount++;
    		if(aHeadObj.getUSPLookingFor().length()>1) iCount++;
    		if(aHeadObj.getUSPLookingForArray().length>1) iCount++;
    		if(iCount < 1){
        		aHeadObj.appendErrorMsg("- With a Public Listing, You Must Select What You Are Looking For \n");
        		bMinOK=false;
    		}
    	}

    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}else{
    		aHeadObj.setUSPAgree2Fg("Yes");
    	}
    	if(false == bMinOK){
    		return -1;
    	}

    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("- Email format error\n");
    		return -1;
    	}
		}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
		
    	switch (iType){
			case PersonInfoDTO.CREATE_USER_PT2: // normal flow of account creation through main site
				
				break;
			case PersonInfoDTO.CREATE_USER_CVINTERNAPPLIC: // normal flow of account creation through main site
				
				break;
			case PersonInfoDTO.CREATE_USER_FB: // flow of account creation, required fields, etc, when done through fb app
		    	// check to see if the user is a registered user in drupal database who's creating a voleng account
		    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false && aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") == false){
		        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
		            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
		            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
		        	){
			        	// check if email address is already used in the system (drupal database) 
			    		iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
			        	if(null != pConn) pConn.free();
			        	if(iRetCode == 0 ){
			        		aHeadObj.appendErrorMsg("\nEmail address " + aHeadObj.getUSPEmail1Addr() + " already exists.\n\n " +
			    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
			    				"Volunteer.Gospel.com, please log in with that email address\n");
			        		return -1;
			        	} 
		        	}
		    	}
		    	
		    	// if a username is not already set, create one by default using FirstnameLastname
				if (aHeadObj.getUSPInternalUserTypeComment().length() < 1){
					if (aHeadObj.getUSPUsername().length() < 1){
						// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
						aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
					}
				}

		    	pConn = getDBConn();
		    	// check to see if the user is a registered user in drupal database who's creating a voleng account
		    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
		    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false){
		        	// check if user id (username) is already used in the system (drupal database) 
			    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
			    	if(null != pConn) pConn.free();
			    	if(iRetCode == 0 ){
			    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n " +
			    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
			    				"Volunteer.Gospel.com, please log in with that email address\r\n\r\n" +
			    				"Otherwise, try adding a middle initial to your first name\r\n\r\n");
			    		return -1;
			    	}
		    	//}}
	
		        break;
			default:
				break;
		}
    	
	    
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	
    	pConn = getDBConn();
    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, iType, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method createAccount2() - LEGACY - should be replaced by chrisvol_on_rails app


    /**
	* updateCVInternApplicant
	*/
	public int updateCVInternApplicant( PersonInfoDTO aHeadObj, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateCVInternApplicant");

    	aszTemp = aHeadObj.getUSPVolunteer();
        iTemp = aHeadObj.getUSPVolunteerTID();
        if(iTemp == iVolDirectorytid){
        	aHeadObj.setUSPVolunteer("Yes");
        	aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        }else{
        	aHeadObj.setUSPVolunteer("No");
        	aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        }
     	
		
    	if(false == bMinOK) return -1;
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	
   	iRetCode = aPersonObj.updateIndividualHead(pConn, aHeadObj, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateCVInternApplicant()
	
	
    /**
	* updateAccount3 - LEGACY - should be replaced by chrisvol_on_rails app
	*/
	public int updateAccount3( PersonInfoDTO aHeadObj, int iType, String aszSiteVersion ){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateAccount3");
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
    	
    	// if, for some reason, the subdomain did not correctly get set through the form (ie the DTO method is blank), set it to the default domain.
    	if (aHeadObj.getUSPSubdom().length()<1){
    		aHeadObj.setUSPSubdom(aszDomMain);
    	}
    	aszTemp = aHeadObj.getUSPNameFirst();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- First Name Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPNameLast();
    	if(aszTemp.length() < 2){
    		aHeadObj.appendErrorMsg("- Last Name Required  \n");
    		bMinOK=false;
    	}
		if (aHeadObj.getUSPUsername().length() < 1){
			// in case there is no username, generate one as FirstLast
			aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
		}
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Username Required \n");
    		bMinOK=false;
    	}
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("- Email Required \n");
    		bMinOK=false;
    	}
    	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
     	if (	aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		aHeadObj.setUSPVolunteer("No");
    		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
    	}else{  
        	aszTemp = aHeadObj.getUSPVolunteer();
        	iTemp = aHeadObj.getUSPVolunteerTID();
        	if(aszTemp.length() < 1 && (!(iTemp > 0 )) ){
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}else if(iTemp == iVolDirectorytid){
        		aHeadObj.setUSPVolunteer("Yes");
        		aHeadObj.setUSPVolunteerTID(iVolDirectorytid);
        	}else{
        		aHeadObj.setUSPVolunteer("No");
        		aHeadObj.setUSPVolunteerTID(iVolNoDirectorytid);
        	}
        	aszTemp = aHeadObj.getUSPPhone1();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("- Phone Number Required \n");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.length() < 2){
        		aHeadObj.appendErrorMsg("Country Required,  ");
        		bMinOK=false;
        	}
        	aszTemp = aHeadObj.getUSPAddrCountryName();
        	if(aszTemp.equalsIgnoreCase("us")){
	        	aszTemp = aHeadObj.getUSPAddrPostalcode();
	        	if(aszTemp.length() < 2){
	        		aHeadObj.appendErrorMsg("Postal Code Required if in the United States,  ");
	        		bMinOK=false;
	        	}
        	}
    	}
    	iTemp= aHeadObj.getUSPVolunteerTID();
    	if(iTemp == iVolDirectorytid && ( ! aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook"))){
    		int iCount=0;
    		if(aHeadObj.getUSPLocalVolTID() > 0) iCount++;
    		if(aHeadObj.getUSPGroupFamilyTID() > 0) iCount++;
    		if(aHeadObj.getUSPVolBoardTID() > 0) iCount++;
    		if(aHeadObj.getUSPVolVirtTID() > 0) iCount++;
    		if(aHeadObj.getUSPIntrnTID() > 0) iCount++;
    		if(aHeadObj.getUSPSumIntrnTID() > 0) iCount++;
    		if(aHeadObj.getUSPWorkStudyTID() > 0) iCount++;
    		if(aHeadObj.getUSPJobsTID() > 0) iCount++;
    		if(aHeadObj.getUSPConferenceTID() > 0) iCount++;
    		if(aHeadObj.getUSPConsultingTID() > 0) iCount++;
    		if(aHeadObj.getUSPSocJustGrpsTID() > 0) iCount++;
    		if(aHeadObj.getUSPLookingFor().length()>1) iCount++;
    		if(aHeadObj.getUSPLookingForArray().length>1) iCount++;
    		if(iCount < 1){
        		aHeadObj.appendErrorMsg("- With a Public Listing, You Must Select What You Are Looking For \n");
        		bMinOK=false;
    		}
    	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
        	String aszTimestamp, aszSignature;
        	String aszUID="";
        	int iUID=0;
        	aszSignature = aHeadObj.getSignature();
        	if(aszSignature.length() < 3){
        		aHeadObj.appendErrorMsg(" A Signature Must be Provided from Gigya Socialize  ");
        		bMinOK=false;
        	}
        	aszTimestamp = aHeadObj.getTimestamp();
        	if(aszTimestamp.length() < 3){
        		aHeadObj.appendErrorMsg(" A Timestamp Must be Provided from Gigya Socialize ");
    	    	bMinOK=false;
        	}
        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize")){
            	iUID = aHeadObj.getUserUID();
            	if(iUID < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}else if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize")){
            	aszUID = aHeadObj.getUserUIDString();
            	if(aszUID.length() < 1){
        	    	aHeadObj.appendErrorMsg(" A User ID Must be Provided from Gigya Socialize to Create an Account through this Method ");
        	    	bMinOK=false;
            	}
        	}
        	if(false == bMinOK) return -1;

        	iRetCode = validateSignature(aszTimestamp, iUID, aszUID, aszSignature, PersonInfoDTO.GIGYA_USER);
        	
        	if(iRetCode < 0){
            	if(iRetCode == -555){
        	    	aHeadObj.appendErrorMsg(" The Link to External Account Failed b/c Too Much Time Has Passed. Please try again ");
            	}else{
        	    	aHeadObj.appendErrorMsg(" The Signature Provided Was Not Correctly Validated ");
            	}
    	    	return -1;
        	}
    	}

    	aszTemp = aHeadObj.getUSPAgree1Fg();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("- Terms & Conditions required \n");
    		bMinOK=false;
    	}else{
    		aHeadObj.setUSPAgree2Fg("Yes");
    	}
    	if(false == bMinOK){
    		return -1;
    	}

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();

    	// validate email address format
    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	iRetCode = checkEmailFormat( aszTemp );
    	if(0 != iRetCode){
    		aHeadObj.appendErrorMsg("- Email format error\n");
    		return -1;
    	}
    	
    	switch (iType){
			case PersonInfoDTO.CREATE_USER_PT2: // normal flow of account creation through main site
				
				break;
			case PersonInfoDTO.CREATE_USER_FB: // flow of account creation, required fields, etc, when done through fb app
		    	// check to see if the user is a registered user in drupal database who's creating a voleng account
		    	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false && aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") == false){
		        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false ||
		            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-opp-contact") == false ||
		            		aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-administrator") == false
		        			){
			        	// check if email address is already used in the system (drupal database) 
			    		iRetCode = aPersonObj.IsEmailInSystem(pConn, aHeadObj);
			        	if(null != pConn) pConn.free();
			        	if(iRetCode == 0 ){
			        		aHeadObj.appendErrorMsg("\nEmail address " + aHeadObj.getUSPEmail1Addr() + " already exists.\n\n " +
			    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
			    				"Volunteer.Gospel.com, please log in with that email address\n");
			        		return -1;
			        	} 
		        	}
		    	}
		    	
		    	// if a username is not already set, create one by default using FirstnameLastname
				if (aHeadObj.getUSPInternalUserTypeComment().length() < 1){
					if (aHeadObj.getUSPUsername().length() < 1){
						// for initial creation, if the user is not yet a drupal user (and therefore having a username already), set username for drupal to FirstLast
						aHeadObj.setUSPUsername(aHeadObj.getUSPNameFirst() + aHeadObj.getUSPNameLast()); 
					}
				}

		    	pConn = getDBConn();
		    	// check to see if the user is a registered user in drupal database who's creating a voleng account
		    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupal") == false){
		    	//if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("added-org-contact") == false){
		        	// check if user id (username) is already used in the system (drupal database) 
			    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
			    	if(null != pConn) pConn.free();
			    	if(iRetCode == 0 ){
			    		aHeadObj.appendErrorMsg("\r\n\r\nUsername " + aHeadObj.getUSPUsername() + " already exists.\r\n\r\n " +
			    				"If you have an account with any of our partner sites: UrbanMinistry.org, CCDA.org, or " +
			    				"Volunteer.Gospel.com, please log in with that email address\r\n\r\n" +
			    				"Otherwise, try adding a middle initial to your first name\r\n\r\n");
			    		return -1;
			    	}
		    	//}}
	
		        break;
			default:
				break;
		}
	    
    	String aszRailsDB = "";
	    if(aszSiteVersion.equals("development")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    }else if(aszSiteVersion.equals("staging")){
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    }else{
	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    }
    	
    	pConn = getDBConn();
    	iRetCode = aPersonObj.insertIndividualHead(pConn, aHeadObj, iType, aszRailsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateAccount3() - LEGACY - should be replaced by chrisvol_on_rails app

	
	public int testFullUser( PersonInfoDTO aIndivObj ){
		return testFullUser(aIndivObj,null);
	}
	public int testFullUser( PersonInfoDTO aIndivObj, String aszToken ){
		String aszTemp="";
		int iTemp=0;
		
		
		if(aszToken!=null){
			if(aszToken.equals(AppSessionDTO.TOKEN_CVINTERNAPPLIC)){
				return PersonInfoDTO.USER_LOGINOK;
			}
		}
		
		
		if(
				(
						aIndivObj.getUSPEmail1Addr().startsWith("facebooktemp") && 
						aIndivObj.getUSPEmail1Addr().endsWith("@urbanministry.org")
				)																		|| 
				aIndivObj.getUSPNameFirst().length() < 1   								||
				aIndivObj.getUSPNameLast().length() < 1 								||
				false == true
		){
			return PersonInfoDTO.USER_LOGIN_PARTIAL;
		}
    	if ( ! (aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser) ) &&
    			! (aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser) ) 
    	){ 
        	if(
            		aIndivObj.getUSPPhone1().length() < 1			||
            		aIndivObj.getUSPAddrCountryName().length() < 1
            	){
            		return PersonInfoDTO.USER_LOGIN_PARTIAL;
            	}
        	if(
            		aIndivObj.getUSPAddrPostalcode().length() < 1	&&
            		aIndivObj.getUSPAddrCountryName().equalsIgnoreCase("us") 
            	){
            		return PersonInfoDTO.USER_LOGIN_PARTIAL;
            	}
		}
		return PersonInfoDTO.USER_LOGINOK;
	}
	
	
    /**
	* removeResume
	*/
	public int removeResume( HttpServletRequest httpServletRequest, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		if(null == aHeadObj) return -1;
    	MethodInit("removeResume");
		  
		String aszFileNameToDelete = aHeadObj.getUSPResumeFilePath();
		try{
			File file = new File(aszFileNameToDelete);
			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation failed");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
    	return iRetCode;
    }
    // end-of method updateResume()
	
    /**
	* updateResume
	*/
	public int updateResume( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("updateResume");
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateResume(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method updateResume()
	
    /**
	* linkResume
	*/
	public int linkResume( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	MethodInit("linkResume");
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.linkResume(pConn, aHeadObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method linkResume()

	
    /**
	* fill out I Want to Help email from form, etc
	*/
	public int helpEmail( EmailInfoDTO aHeadObj, PersonInfoDTO aIndivObj,OrgOpportunityInfoDTO aOpportObj, OrganizationInfoDTO aOrgInfoObj, PersonInfoDTO aContactPersonObj, String aszToken ){
		int iRetCode=0, iTemp=0;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	MethodInit("registerNewIndividual");

    	// contact person info
    	if(aHeadObj.getEmailContactFN().length() < 2){
    		aHeadObj.setEmailContactFN( aContactPersonObj.getUSPNameFirst() );
    	}
    	if(aHeadObj.getEmailContactLN().length() < 2){
    		aHeadObj.setEmailContactLN( aContactPersonObj.getUSPNameLast() );
    	}
    	// org, opp info to list
    	if(aHeadObj.getEmailToUser().length() < 2){
    		//aszTo=aOrgInfoObj.getORGOrgContactEmail();
    		aHeadObj.setEmailToUser(aContactPersonObj.getUSPEmail1Addr());
    	}
    	if(aHeadObj.getEmailOppNID() < 1){
    		aHeadObj.setEmailOppNID( aOpportObj.getOPPNID());
    	}
    	if(aHeadObj.getEmailOrgNID() < 1){
    		aHeadObj.setEmailOrgNID(aOrgInfoObj.getORGNID());
    	}
    	if(aHeadObj.getEmailVolUID() < 1){
    		aHeadObj.setEmailVolUID(aIndivObj.getUserUID());
    	}
    	if(aHeadObj.getEmailOppName().length() < 2){
    		aHeadObj.setEmailOppName(aOpportObj.getOPPTitle());
    	}
    	if(aHeadObj.getEmailOrgName().length() < 2){
    		aHeadObj.setEmailOrgName(aOrgInfoObj.getORGOrgName());
    	}
    	if(aHeadObj.getEmailOrgAddr1().length() < 2){
    		aHeadObj.setEmailOrgAddr1(aOrgInfoObj.getORGAddrLine1());
    	}
    	if(aHeadObj.getEmailOrgCity().length() < 2){
    		aHeadObj.setEmailOrgCity(aOrgInfoObj.getORGAddrCity());
    	}
    	if(aHeadObj.getEmailOrgState().length() < 2){
    		aHeadObj.setEmailOrgState(aOrgInfoObj.getORGAddrStateprov());
    	}
    	if(aHeadObj.getEmailOrgProv().length() < 2){
    		aHeadObj.setEmailOrgProv(aOrgInfoObj.getORGAddrOtherProvince());
    	}
    	if(aHeadObj.getEmailOrgCountry().length() < 2){
    		aHeadObj.setEmailOrgCountry(aOrgInfoObj.getORGAddrCountryName());
    	}
    	if(aHeadObj.getEmailOrgPhone().length() < 2){
    		aHeadObj.setEmailOrgPhone(aOrgInfoObj.getORGOrgPhone1());
    	}
    	if(aHeadObj.getEmailOrgWeb().length() < 2){
    		aHeadObj.setEmailOrgWeb(aOrgInfoObj.getORGWebpage());
    	}
    	if(aHeadObj.getEmailSTMReferencesText().length() < 2){
    		aHeadObj.setEmailSTMReferencesText(aOpportObj.getOPPSTMReferences());
    	}
    	if(aHeadObj.getEmailOppBkgrnd().length() < 1){
    		aszTemp=aOpportObj.getOPPBackgroundCheck();
    		if (aszTemp.equalsIgnoreCase("Yes")){
    			aHeadObj.setEmailOppBkgrnd("This organization requires a background check.");
    		}
    		else {
    			aHeadObj.setEmailOppBkgrnd("");
    		}
    	}
    	if(aHeadObj.getEmailOppReference().length() < 1){
    		aszTemp=aOpportObj.getOPPReferenceReq();
    		if (aszTemp.equalsIgnoreCase("Yes")){
    			aHeadObj.setEmailOppReference("This organization requires references.");
    		}
    		else {
    			aHeadObj.setEmailOppReference("");
    		}
    	}
    	if(aHeadObj.getEmailFromUser().length() < 2)
    		aHeadObj.setEmailFromUser(aIndivObj.getUSPEmail1Addr());
    	if(aHeadObj.getEmailBodyTextRes().length() < 2)
    		aHeadObj.setEmailBodyTextRes(aIndivObj.getUSPVolResume());
        if(aHeadObj.getEmailResumeFilePath().length() < 2)
      	  aHeadObj.setEmailResumeFilePath(aIndivObj.getUSPResumeFilePath());
    	//String DUPLICATE 
    	if(aHeadObj.getEmailVolUID() < 1)
    		aHeadObj.setEmailVolUID(aIndivObj.getUserUID());
    	if(aHeadObj.getEmailVolFN().length() < 2)
    		aHeadObj.setEmailVolFN(aIndivObj.getUSPNameFirst());
    	if(aHeadObj.getEmailVolLN().length() < 2)
    		aHeadObj.setEmailVolLN(aIndivObj.getUSPNameLast());
        if(aHeadObj.getEmailVolPhone1().length() < 2)
      	  aHeadObj.setEmailVolPhone1(aIndivObj.getUSPPhone1());
        if(aHeadObj.getEmailVolPhone1Type().length() < 2)
      	  aHeadObj.setEmailVolPhone1Type(aIndivObj.getUSPPhone1Type());
        if(aHeadObj.getEmailVolPhone2().length() < 2 && aIndivObj.getUSPPhone2().length() > 2)
      	  aHeadObj.setEmailVolPhone2(aIndivObj.getUSPPhone2());
        if(aHeadObj.getEmailVolPhone2Type().length() < 2)
      	  aHeadObj.setEmailVolPhone2Type(aIndivObj.getUSPPhone2Type());
    	if( 	(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELPSIGNIN )) ||
    			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN )) 
    	){
    		aHeadObj.setEmailVolChris("");
    	}else{
        	if(aHeadObj.getEmailVolChris().length() < 2){
        		aszTemp=aIndivObj.getUSPChristianP();
        		if ( (aszTemp.equalsIgnoreCase("Y")) || (aszTemp.equalsIgnoreCase("Yes")) ){
        			aHeadObj.setEmailVolChris("Is this person a Christian?   Yes");
        		}
        		else if ( (aszTemp.equalsIgnoreCase("N")) || (aszTemp.equalsIgnoreCase("No")) ){
        			aHeadObj.setEmailVolChris("Is this person a Christian?   No");
        		}
        		else {
        			aHeadObj.setEmailVolChris("This person has not indicated their faith.");
        		}
        	}
    	}
      	if(aHeadObj.getEmailRailsSkills().length() < 2 && aIndivObj.getUserRailsSkills().length() > 2)
      		aHeadObj.setEmailRailsSkills(aIndivObj.getUserRailsSkills());
    	// filter out volunteer skills that are clearly faith-based from the ivolunteering email
    	int iVolSkills1TID=0, iVolSkills2TID=0, iVolSkills3TID=0;
    	if(aHeadObj.getEmailVolSkills().length() < 2){
    		aHeadObj.setEmailVolSkills(aIndivObj.getUSPVolSkills());
    		iVolSkills1TID = aIndivObj.getUSPVolSkills1TID();
        	String aszVolSkills = aHeadObj.getEmailVolSkills(); 
        	if(aszToken.equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
        		if(aszVolSkills.equalsIgnoreCase("Musician / Worship Leader") ||
        				iVolSkills1TID==968){
        			aHeadObj.setEmailVolSkills("Musician");
        		}else if( (aszVolSkills.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
        				(aszVolSkills.equalsIgnoreCase("Preacher / Minister")) ||
        				(aszVolSkills.equalsIgnoreCase("Deaf Minister")) ||
        				(iVolSkills1TID==971) ||
        				(iVolSkills1TID==972) ||
        				(iVolSkills1TID==8124)){
        			aHeadObj.setEmailVolSkills("");
        		}
        	}
    	}            	
    	if(aHeadObj.getEmailVolSkills2().length() < 2){
    		aHeadObj.setEmailVolSkills2(aIndivObj.getUSPVolSkills2());
    		iVolSkills2TID = aIndivObj.getUSPVolSkills2TID();
        	String aszVolSkills2 = aHeadObj.getEmailVolSkills2(); 
        	if(aszToken.equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
        		if(aszVolSkills2.equalsIgnoreCase("Musician / Worship Leader") ||
        				iVolSkills2TID==968){
        			aHeadObj.setEmailVolSkills2("Musician");
        		}else if( (aszVolSkills2.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
        				(aszVolSkills2.equalsIgnoreCase("Preacher / Minister")) ||
        				(aszVolSkills2.equalsIgnoreCase("Deaf Minister")) ||
        				(iVolSkills2TID==971) ||
        				(iVolSkills2TID==972) ||
        				(iVolSkills2TID==8124)){
        			aHeadObj.setEmailVolSkills2("");
        		}
        	}
    	}
    	if(aHeadObj.getEmailVolSkills3().length() < 2){
    		aHeadObj.setEmailVolSkills3(aIndivObj.getUSPVolSkills3());
    		iVolSkills3TID = aIndivObj.getUSPVolSkills3TID();
        	String aszVolSkills3 = aHeadObj.getEmailVolSkills3(); 
        	if(aszToken.equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
        		if(aszVolSkills3.equalsIgnoreCase("Musician / Worship Leader") ||
        				iVolSkills3TID==968){
        			aHeadObj.setEmailVolSkills3("Musician");
        		}else if( (aszVolSkills3.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
        				(aszVolSkills3.equalsIgnoreCase("Preacher / Minister")) ||
        				(aszVolSkills3.equalsIgnoreCase("Deaf Minister")) ||
        				(iVolSkills3TID==971) ||
        				(iVolSkills3TID==972) ||
        				(iVolSkills3TID==8124)){
        			aHeadObj.setEmailVolSkills3("");
        		}
        	}
    	}

    	return iRetCode;
    }
    // end-of method ()
	
	

    /**
	* get portal info, such as node id and banner url
	*/
	public int getPortalInfo( AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
		pConn = getDBConn();
    	ApplicationCodesObj aAppCodesObj = new ApplicationCodesObj();
    	//PersonObj aPersonObj = new PersonObj();
		iRetCode = aAppCodesObj.getPortalInfo(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
	/**
	 * validateFBapikey
	 */
	public boolean validateFBapikey(String aszAPIkey, String aszSecretKey, PersonInfoDTO aHeadObj){
		String aszFacebookMainAPIkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_MAIN_APIKEY);
		String aszFacebookMainSecretkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_MAIN_SECRETKEY);
		String aszFacebookSecondaryAPIkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_SECONDARY_APIKEY);
		String aszFacebookSecondarySecretkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_SECONDARY_SECRETKEY);
		String aszFacebookSandboxAPIkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_SANDBOX_APIKEY);
		String aszFacebookSandboxSecretkey = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_SANDBOX_SECRETKEY);
		boolean validapikey=false;
		if(aszAPIkey.equalsIgnoreCase(aszFacebookMainAPIkey) || aszAPIkey.equalsIgnoreCase(aszFacebookSecondaryAPIkey)
				 || aszAPIkey.equalsIgnoreCase(aszFacebookSandboxAPIkey)){
			if(aszSecretKey.equalsIgnoreCase(aszFacebookMainSecretkey) || aszSecretKey.equalsIgnoreCase(aszFacebookSecondarySecretkey)
					 || aszSecretKey.equalsIgnoreCase(aszFacebookSandboxSecretkey)){
				validapikey=true;
			}
		}
		//aHeadObj.appendErrorMsg("\n The api key passed was: " + aszAPIkey+".  \nThe valid api keys are : "+aszFacebookSecondaryAPIkey+" or : "+aszFacebookMainAPIkey+" \n");
		return validapikey;
	}
	
	/**
	 * validate the unix timestamp
	 */
	public boolean validateUnixTimestamp(String aszTimestamp, int iTimeLimit, PersonInfoDTO aHeadObj){
		int remoteUnixTime=0;
		if(aszTimestamp.length()>0){
			remoteUnixTime=Float.valueOf(aszTimestamp.trim()).intValue();
		}
		Date today ; 
		boolean withinTimeLimit=false;
		today = new java.util.Date();
		long localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
	    long diff=java.lang.Math.abs(localUnixTime - remoteUnixTime);
		iTimeLimit=iTimeLimit*60; // # of minutes called in as a param * 60 sec = ###seconds
		    
	    // causes issues for changes in Daylight Savings Time, b/c we are using an old jdk; the .jsp validation works, but this .java validation is an hour off		    
	    // test to see if within iTimeLimit
		if(diff > iTimeLimit){ 
			// if greater than iTimeLimit, then test to see if it's 60 minutes+/-iTimeLimit off; 
			//		this accounts for a possible hour adjustment where DST has not been updated to new North American schedule ~2009
			if( !((3600-iTimeLimit) < diff && diff < (3600+iTimeLimit)) ){ // 55 min < diff < 65 min (or 58 min < diff < 62 min)
				withinTimeLimit=false;
				return withinTimeLimit;
			}else{
				// timestamps are roughly 1 hour off, give or take 5 min.  for now, this counts as validated (DST)
				withinTimeLimit=true;
			}
		}else{
			// the timestamps are within iTimeLimit minutes; time suggested by remote app to validate against
			withinTimeLimit=true;
		}
		if(withinTimeLimit==false){
			aHeadObj.appendErrorMsg(" The Remote timestamp passed was: " + remoteUnixTime+".  The local timestamp was: "+localUnixTime+"; the time limit was set to: "+iTimeLimit+", but the difference being: "+diff+". ");
		}

		return withinTimeLimit;
	}
	
	/**
	 * validate the timestamp
	 */
	public boolean validateTimestamp(String aszTimestamp, int iTimeLimit){
		String str_date;
		//1274126852.3922
		//	first, verify that the timestamp is within 20 min 240,000*10 milliseconds of the GMT timestamp now() - format YYYY-MM-DD HH:MM:SS
		str_date=aszTimestamp;
		DateFormat formatter ; 
		Date date, today ; 
		long remoteUnixTime, localUnixTime;
		boolean withinTimeLimit=false;
		today = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try {    
		    date = (Date)formatter.parse(str_date);
		    remoteUnixTime = date.getTime();
			//out.println("passed:"+socializeUnixTime); 
			localUnixTime = today.getTime() + 18000000; // adjust for GMT comparison
			//out.println("local:" + localUnixTime); 
		    java.sql.Timestamp timeStampDate = new Timestamp(today.getTime());
		    long diff=java.lang.Math.abs(localUnixTime - remoteUnixTime);
		    iTimeLimit=iTimeLimit*1000*60; // 300,000 or 120,000
		    
		    
		    // causes issues for changes in Daylight Savings Time, b/c we are using an old jdk; the .jsp validation works, but this .java validation is an hour off
		    
		    // test to see if within 5 min
			if(diff > iTimeLimit){ 
				// if greater than 5 minutes, then test to see if it's 55-65 minutes off; 
				//		this accounts for a possible hour adjustment where DST has not been updated to new North American schedule ~2009
				if( !((3600000-iTimeLimit) < diff && diff < (3600000+iTimeLimit)) ){ // 55 min < diff < 65 min (or 58 min < diff < 62 min)
					withinTimeLimit=false;
					return withinTimeLimit;
				}else{
					// timestamps are roughly 1 hour off, give or take 5 min.  for now, this counts as validated (DST)
					withinTimeLimit=true;
				}
			}else{
				// the timestamps are within 5 minutes; time suggested by gigya to validate against
				withinTimeLimit=true;
			}
		}
		catch (ParseException e){
			//out.println("Exception :"+e);    
		}    
		return withinTimeLimit;
	}

	/**
	 * validate the signature - LEGACY
	 * 
	 * no longer use gigya socialize
	 * cookie login chrisvolAuth is now handled by md5 hashes
	 */
	public int validateSignature(String aszTimestamp, int iUID, String aszUID, String aszSignature, int iType){
		String secretKey = "";
		switch (iType){
			case PersonInfoDTO.GIGYA_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_GIGYA_SOCIALIZE_SECRETKEY);
				break;
			case PersonInfoDTO.COOKIE_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
			default :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
		}
		
		
		boolean validatedTimestamp = true;
		if (iType==PersonInfoDTO.GIGYA_USER){
			validatedTimestamp = validateTimestamp(aszTimestamp, 5); // within 5 min
		}
		if(validatedTimestamp==false){
			return -1;
		}
		
		/**
		 * calculate a local signature, using the private SecretKey set on our gigya socialize account
		 * compare this local signature to the signature that was passed in the parameters in order to verify gigya socialize
		 */		
		String mySignature = "";
		byte[] binaryKey = null;
		// baseString = timestamp + "_" + UID
		String baseString;
		if(iUID>0){
			baseString = aszTimestamp + "_" + iUID;
		}else{
			baseString = aszTimestamp + "_" + aszUID;
		}
		// convert your secretKey from its BASE64 encoding to a binary array; secret key can be retrieved in your gigya partner account
		// binaryKey = ConvertFromBase64(secretKey);
/*		try {
			BASE64Decoder decoder = new BASE64Decoder();
			binaryKey = decoder.decodeBuffer(secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
		binaryKey = new Base64(true).decode(secretKey.getBytes());
		try {
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(binaryKey, HMAC_SHA1_ALGORITHM);//.getBytes(), HMAC_SHA1_ALGORITHM);
				
			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
				
			// compute the hmac on input data bytes
			// binarySignature = hmacsha1(binaryKey, baseString)
			byte[] binarySignature = mac.doFinal(baseString.getBytes());
			
			// base64-encode the hmac
			// mySignature = ConvertToBase64(binarySignature);
/*			try {
				BASE64Encoder encoder = new BASE64Encoder();
				mySignature = encoder.encodeBuffer(binarySignature);
			} catch (Exception e) {
				e.printStackTrace();
			}
*/			
			Base64 encoder = new Base64();
			mySignature = new String(encoder.encode(binarySignature));
		} catch (Exception e) {
			e.printStackTrace();
//			throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
		}
		// trim the return carriage that for some reason is getting added to the end of mySignature
		mySignature=mySignature.trim();
		if(aszSignature.equals(mySignature)){ // case-sensitive String comparison testing
			return 0;
		}else{
			return -1;
		}
	}
	
	
	/**
	 * get the domain name
	 */
	public String getDomainName( HttpServletRequest httpServletRequest ){
		int iType=0;
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_MAIN_DOMAIN);
		String aszSecondaryMain = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECONDARY_DOMAIN);
		String aszFaithDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_FAITH_DOMAIN);
		String aszSecFaithDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SEC_FAITH_DOMAIN);
		String aszTestDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_TEST_MAIN_DOMAIN);
		String aszTestSecondaryDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_TEST_SECONDARY_DOMAIN);
		String aszTestFaithDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_TEST_FAITH_DOMAIN);
		String aszTestSecFaithDom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_TEST_SEC_FAITH_DOMAIN);
		String aszPartner1Dom = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_PARTNER1_DOMAIN);
		String aszStagingMain = this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_STAGING_MAIN_DOMAIN);

		String aszHost = "christianvolunteering.org";
System.out.println(" IndBRLO aszHost is "+aszHost);		
		if(httpServletRequest != null){
System.out.println(" IndBRLO httpServletRequest is not null");		
			if(httpServletRequest.getHeader("host") != null){
				aszHost = httpServletRequest.getHeader("host");
System.out.println(" IndBRLO httpServletRequest.getheader host  is not null  "+aszHost);		
			}
		}
		
		if ( aszHost.contains("chrisvol.org") 	){
			return aszTestDom;
		}else if ( aszHost.contains("cathlicvol.org:7001") ){
			return aszTestSecFaithDom;
		}else if ( aszHost.contains("staging-christianvolunteering.org") ){
			return aszStagingMain;
		}else if ( aszHost.contains("catholicvolunteering.org") ){
			return aszSecFaithDom;
		}else if ( aszHost.contains("volunteer.gospel.com") || aszHost.contains("gospel.christianvolunteering.org") ){
			return aszPartner1Dom;
		}else{
			return aszDomMain;
		}
/*
		switch (iType){
		case PersonInfoDTO.MAIN_DOMAIN:
			return aszDomMain;
		case PersonInfoDTO.TEST_DOMAIN:
			return aszTestDom;
		case PersonInfoDTO.PARTNER1_DOMAIN:
			return aszPartner1Dom;
		default:
			return aszDomMain;
		}
*/	
	}
	/**
	 * create now's unix timestamp
	 */
	public int getUnixTimestampNow(){
		Date today ; 
		today = new java.util.Date();
		long localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
		return (int)localUnixTime;
	}
	/**
	 * create the signature - LEGACY (at least regarding setting chrisvolAuth cookie
	 * 
	 * deprecated 2013-09-12 (already replaced at least 2 years prior; just not removed from code);
	 * 
	 * replaced by md5 hash - setSessionValue,   hashString( UniqIDString(aHeadObj) );  
	 */
	public String createSignature(int iUID, String aszUID, String aszIPAddress, int iType){
		String secretKey = "";
    	Random r1 = new Random();  
    	String nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
    	nonce = nonce.substring(0,9);// 10 digit nonce
		switch (iType){
			case PersonInfoDTO.GIGYA_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_GIGYA_SOCIALIZE_SECRETKEY);
				break;
			case PersonInfoDTO.COOKIE_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
			default :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
		}
		
		
		/**
		 * calculate a local signature, using the private SecretKey set on our gigya socialize account
		 * compare this local signature to the signature that was passed in the parameters in order to verify gigya socialize
		 */		
		String mySignature = "";
		byte[] binaryKey = null;
		// baseString = timestamp + "_" + UID
		// baseString will be the nonce + user id
		String baseString;
		if(iUID>0){
			//baseString = aszTimestamp + "_" + iUID;
			baseString = nonce + aszIPAddress + iUID;
baseString = aszIPAddress + 43;
		}else{
			//baseString = aszTimestamp + "_" + aszUID;
			baseString = nonce + aszIPAddress + aszUID;
baseString = aszIPAddress + 43;
		}
		
		// convert your secretKey from its BASE64 encoding to a binary array; secret key can be retrieved in your gigya partner account
		// binaryKey = ConvertFromBase64(secretKey);
/*		try {
			BASE64Decoder decoder = new BASE64Decoder();
			binaryKey = decoder.decodeBuffer(secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
//System.out.println("inside createSignature "+baseString);		
		binaryKey = new Base64(true).decode(secretKey.getBytes());
		try {
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(binaryKey, HMAC_SHA1_ALGORITHM);//.getBytes(), HMAC_SHA1_ALGORITHM);
				
			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
				
			// compute the hmac on input data bytes
			// binarySignature = hmacsha1(binaryKey, baseString)
			byte[] binarySignature = mac.doFinal(baseString.getBytes());
			
			// base64-encode the hmac
			// mySignature = ConvertToBase64(binarySignature);
			/*
 			try {
				BASE64Encoder encoder = new BASE64Encoder();
				mySignature = encoder.encodeBuffer(binarySignature);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 */			
			Base64 encoder = new Base64();
			mySignature = new String(encoder.encode(binarySignature));

		} catch (Exception e) {
			e.printStackTrace();
			//			throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
		}
		// trim the return carriage that for some reason is getting added to the end of mySignature
		mySignature=mySignature.trim();
		/*
		if(aszSignature.equals(mySignature)){ // case-sensitive String comparison testing
			return 0;
		}else{
			return -1;
		}
		*/
		
//		mySignature = nonce + mySignature;
		
		return mySignature;
	}
	/**
	 * validate the signature
	 */
	public int validateSignatureCookie(String aszSignature, String aszIPAddress, int iType){
		int iRetCode=0;
		String secretKey = "";
		int indexForUID = aszSignature.lastIndexOf(".");
 		String aszUID = aszSignature.substring(indexForUID+1);

    	String nonce = aszSignature.substring(0,9);// 10 digit nonce
		switch (iType){
			case PersonInfoDTO.GIGYA_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_GIGYA_SOCIALIZE_SECRETKEY);
				break;
			case PersonInfoDTO.COOKIE_USER :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
			default :
				secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
				break;
		}
		
		
		/**
		 * calculate a local signature, using the private SecretKey set on our gigya socialize account
		 * compare this local signature to the signature that was passed in the parameters in order to verify gigya socialize
		 */		
		String mySignature = "";
		byte[] binaryKey = null;
		// baseString = timestamp + "_" + UID
		// baseString will be the nonce + user id
		String baseString;
		baseString = nonce + aszIPAddress + aszUID;
		
		// convert your secretKey from its BASE64 encoding to a binary array; secret key can be retrieved in your gigya partner account
		// binaryKey = ConvertFromBase64(secretKey);
/*		try {
			BASE64Decoder decoder = new BASE64Decoder();
			binaryKey = decoder.decodeBuffer(secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
		binaryKey = new Base64(true).decode(secretKey.getBytes());
		try {
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(binaryKey, HMAC_SHA1_ALGORITHM);//.getBytes(), HMAC_SHA1_ALGORITHM);
				
			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
				
			// compute the hmac on input data bytes
			// binarySignature = hmacsha1(binaryKey, baseString)
			byte[] binarySignature = mac.doFinal(baseString.getBytes());
			
			// base64-encode the hmac
			// mySignature = ConvertToBase64(binarySignature);
/*			try {
				BASE64Encoder encoder = new BASE64Encoder();
				mySignature = encoder.encodeBuffer(binarySignature);
			} catch (Exception e) {
				e.printStackTrace();
			}
*/			
			Base64 encoder = new Base64();
			mySignature = new String(encoder.encode(binarySignature));

		} catch (Exception e) {
			e.printStackTrace();
//			throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
		}
		// trim the return carriage that for some reason is getting added to the end of mySignature
		mySignature=mySignature.trim();
		mySignature = nonce + mySignature + "." + aszUID;
		
		if(aszSignature.equals(mySignature)){ // case-sensitive String comparison testing
			iRetCode = 0;
		}else{
			iRetCode = -1;
		}
		return iRetCode;
	}

	// VALIDATE new encryption method for md5 value for chrisvolAuth cookie - user login
	public int validateSessionValue( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSessionValue=aHeadObj.getSessionValue();
		
		// first use case is if it already exists => validation
		if(aszSessionValue.length()>0){
			if(aszSessionValue.indexOf("-") == 4){
				String random = aszSessionValue.substring(0,4);
				aszSessionValue = hashString( UniqIDString(aHeadObj, random) );  // SEEMS FINE w/o needing to parse it out; might need to do aszSessionValue = aszSessionValue.substring(4);
				if(aHeadObj.getSessionValue().equals(aszSessionValue)){
					return 0;
				}else{
					return -1;
				}
			}
		}
		if(aszSessionValue.length() > 1){
			iRetCode=0;
		}else{
			iRetCode=-1;
		}
		
		return iRetCode;
	}

	// new encryption method for md5 value for chrisvolAuth cookie - user login
	public int setSessionValue( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSessionValue=hashString( UniqIDString(aHeadObj) );
		if(aszSessionValue.length() > 1){
			aHeadObj.setSessionValue(aszSessionValue);
			iRetCode=0;
		}else{
			iRetCode=-1;
		}
		
		return iRetCode;
	}
	
    /**
     * @param str
     * @return md5 byte[16]
     */
    public byte[] hash(String str) {
	    MessageDigest mHasher;
    	ReentrantLock opLock = new ReentrantLock();
        opLock.lock();
        mHasher = UniqId();
        try {
        	byte[] bt = mHasher.digest(str.getBytes());
        	if (null == bt || bt.length != 16) {
        		throw new IllegalArgumentException("md5 need");
        	}
        	return bt;
        }finally {
                opLock.unlock();
        }
    }
    /**
     * @param str
     * @return md5 string
     */
    public String hashString(String input) {
    	String str = input;
    	String randomKey = "";
    	// get the random number to put into the cookie value - like a public key that's variable
    	if(str.contains("-")){
        	int iIndexDash = str.indexOf('-',0);
        	randomKey = str.substring(0,iIndexDash);
    	}
        byte[] bt = hash(str);
        
        String encryptedValue = randomKey + "-" + bytes2string(bt);
        
        return encryptedValue;
    }
    /**
     * @param bt
     * @return f1d2
     */
    public String bytes2string(byte[] bt) {
    	int    l = bt.length;
    	char[] out = new char[l << 1];
    	char[]       digits = {
    	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    	    };
    	for (int i = 0, j = 0; i < l; i++) {
    		out[j++]     = digits[(0xF0 & bt[i]) >>> 4];
    		out[j++]     = digits[0x0F & bt[i]];
    	}
    	return new String(out);
    }
	public String UniqIDString( PersonInfoDTO aHeadObj ){// String hostAddr ){
	    Random        random  = new SecureRandom();
	    StringBuffer sb = new StringBuffer();
	    sb.append(random.nextInt(8999) + 1000);
	    String aszRandomKey = sb.toString();
	    return UniqIDString( aHeadObj, aszRandomKey );
	}
	public String UniqIDString( PersonInfoDTO aHeadObj, String random ){// String hostAddr ){
		String secretKey=this.getSitePropertyValue(ABREAppServerDef.APP_COOKIE_SECRETKEY);
	    String        hostAddr;
		long time=0;
		//aszTimestamp = String.valueOf(System.currentTimeMillis());
		time = System.currentTimeMillis();
		time=time/1000;
		int iTimestamp = (int) time;

        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostAddr = addr.getHostAddress();
            hostAddr = aHeadObj.getSessionIP();
        } catch (IOException e) {
        	//"[UniqID] Get HostAddr Error", e);
            hostAddr = ""+iTimestamp;
        }
        if (null == hostAddr || hostAddr.trim().length() == 0 ) {//|| "127.0.0.1".equals(hostAddr)) {
                hostAddr = String.valueOf(System.currentTimeMillis());
        }else{
            //hostAddr += String.valueOf(System.currentTimeMillis());
        }
		//Date today = new java.util.Date();
		//long t = today.getTime(); 
        StringBuffer sb = new StringBuffer();
//sb.append(String.valueOf(System.currentTimeMillis()));//t);
        sb.append(random);
        sb.append("-");
        sb.append(aHeadObj.getUserUID());//t);
        sb.append("-");
//sb.append(random.nextInt(8999) + 1000);
        sb.append(secretKey);
        sb.append("-");
        sb.append(hostAddr);
//sb.append("-");
//sb.append(Thread.currentThread().hashCode());
        aHeadObj.setSessionIP( hostAddr );
        aHeadObj.setSessionTimestamp( iTimestamp );
        return sb.toString();
    }

	public MessageDigest UniqId() {
	    MessageDigest mHasher;
        try {
            mHasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nex) {
            // [UniqID]new MD5 Hasher error
        	mHasher = null;
        }
        return mHasher;
	}

	public int addNewUserOrg( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, String aszPortal, String aszSiteVersion){
    	String mailkey = "newOrgContactAccnt";
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        return addNewUserServices( httpServletRequest, aIndivObj, aOrgInfoObj, aOpportObj, aszPortal, mailkey, aszSiteVersion);
	}

	public int addNewUserOpp( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, OrgOpportunityInfoDTO aOpportObj, String aszPortal, String aszSiteVersion){
    	String mailkey = "newOppUserAccnt";
        return addNewUserServices( httpServletRequest, aIndivObj, aOrgInfoObj, aOpportObj, aszPortal, mailkey, aszSiteVersion);
	}
	
	public int addNewUserServices( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, OrgOpportunityInfoDTO aOpportObj, String aszPortal, String mailkey, String aszSiteVersion){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
		String hash = null;
		String aszPhoneSupport = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_PHONE_SUPPORT);
		String aszSiteOrgName = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
		String aszEmailMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
		String aszEmailSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
		String aszEmailFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_EMAIL);
		String aszDomFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN);
		String aszDomFaithShort = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN_SHORT);
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
		String aszDomMainShort = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
		String aszDomSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
		String aszDomSecondaryShort = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
		String aszServicesXMLRPCpublicURL = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_XMLRPC_PUBLIC_URL);
		String aszServicesXMLRPCtestingURL = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_XMLRPC_TESTING_URL);
		String WS_API_KEY = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_APIKEY);
		String WS_DOMAIN = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_DOMAIN);
		String WS_TESTING_API_KEY = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_TESTING_APIKEY);
		String WS_TESTING_DOMAIN = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_TESTING_DOMAIN);
		String WS_Services_Username = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_USERNAME);
		String WS_Services_Password = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_PASSWORD);
		
		String aszEmailAddress=aIndivObj.getUSPEmail1Addr();
		aIndivObj.setErrorMsg("");
		
		if(aIndivObj.getUSPSubdom().length() < 2){
			aIndivObj.setUSPSubdom(aszDomMain);
		}
		String aszSubdomain = aIndivObj.getUSPSubdom();
		if(aszPortal.length()>0){
			aszSubdomain += "/" + aszPortal;
		}
    	String aszFromEmail=null;
    	if(aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomSecondaryShort) ||
    			aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomSecondary)){
    		aszFromEmail = aszEmailSecondary;
    	}else if(aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomFaithShort) ||
    			aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomFaith)){
    		aszFromEmail = aszEmailFaith;
    	}else{

    		aszFromEmail = aszEmailMain;
    	}
		
    	// validate email address format
    	boolean bMinOK=true;
    	String aszPassword, aszPassVerify;
    	iRetCode = checkEmailFormat( aIndivObj.getUSPEmail1Addr() );
    	if(0 != iRetCode){
    		aIndivObj.appendErrorMsg("Email format error, ");
    		return -1;
    	}
		aszPassVerify = aIndivObj.getPasswordConfirm();
    	if(aszPassVerify.length() < 3){
    		aIndivObj.appendErrorMsg("Password Verification Required, ");
    		bMinOK=false;
    	}
    	aszPassword = aIndivObj.getUSPPassword();
    	if(aszPassword.length() < 3){
    		aIndivObj.appendErrorMsg("Password Required,  ");
    		bMinOK=false;
    	}
        if(false == aszPassword.equalsIgnoreCase(aszPassVerify)){
        	aIndivObj.appendErrorMsg("Passwords don't match.\n  ");
    		bMinOK=false;
        }
    	if(false == bMinOK){
    		return -111;
    	}

    	// first check if email address exists in database
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.IsEmailInSystem(pConn, aIndivObj);
    	if(null != pConn) pConn.free();
    	if(iRetCode == 0 ){
    		aIndivObj.appendErrorMsg("Email address is already in system ");
    		return -1;
    	}
    	iRetCode = 0;
		
    	String WS_URL = "";
		if ( httpServletRequest.getHeader("host").contains(":7001") 
				|| httpServletRequest.getHeader("host").contains(":8080")  
				|| httpServletRequest.getHeader("host").contains("chrisvol.org") 
				|| httpServletRequest.getHeader("host").contains("cv.org") 
		){
			WS_URL = aszServicesXMLRPCtestingURL;
			WS_API_KEY = WS_TESTING_API_KEY;
			WS_DOMAIN = WS_TESTING_DOMAIN;

		}else{
			WS_URL = aszServicesXMLRPCpublicURL;
		}

		Vector vParams = new Vector();
    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

//		setErr("XmlRpcClientConfigImpl success" );
//		ErrorsToLog();
		
		try {
	        Date date = new Date();
	    	Random r1 = new Random();  
	    	String nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);

	        config.setServerURL(new URL(WS_URL));
			XmlRpcClient client = new XmlRpcClient();
//			setErr("XmlRpcClient success" );
//			ErrorsToLog();
			client.setConfig(config); 
//			setErr("XmlRpcClient setConfig success" );
//			ErrorsToLog();

			Object oConnect = client.execute(WS_ACTION_CONNECT, vParams); // xmlrpc exception: 1
			HashMap hm = (HashMap)oConnect;
			String sessid = (String)hm.get(WS_PARAM_SESSID);

	        int iTimeStamp = (int) (date.getTime() * .001);
	        String timestamp = iTimeStamp + ""; 

	        // BEGIN login Services User
	    	StringBuilder sbUserLogin = new StringBuilder();
	        sbUserLogin.append(timestamp);
	        sbUserLogin.append(";");
	        sbUserLogin.append(WS_DOMAIN);
	        sbUserLogin.append(";");
	        sbUserLogin.append(nonce);
	        sbUserLogin.append(";");
	        sbUserLogin.append(WS_ACTION_LOGIN);
	        hash = hmacSHA256(sbUserLogin.toString(),WS_API_KEY.getBytes());
			
	        vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(WS_Services_Username);
			vParams.add(WS_Services_Password);
			
			Object oUserLogin = client.execute(WS_ACTION_LOGIN, vParams);
			hm = (HashMap)oUserLogin;
			sessid = (String)hm.get(WS_PARAM_SESSID);
			// END login Services

			// BEGIN user.save Service
			// nonce and timestamp may have expired during user.login - set new nonce and timestamp for this user.save call
	        date = new Date();
	    	r1 = new Random();  
	    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);
	        iTimeStamp = (int) (date.getTime() * .001);
	        timestamp = iTimeStamp + ""; 

//	        JSONObject userJSONObject=new JSONObject();
	        // update with newer version of Services; rather than taking userobject as a struct, it now takes it as an array.
	        // actually, still has option of JSON in the dropdown, so should be fine; looks like they added case where it could take comma separated input
	        net.sf.json.JSONObject userObject=new net.sf.json.JSONObject();
	        try{
	        
	        	userObject.put("name",aIndivObj.getUSPUsername());
	        	userObject.put("pass",aIndivObj.getUSPPassword()); 
	        	userObject.put("mail",aIndivObj.getUSPEmail1Addr());
	        	userObject.put("init",aIndivObj.getUSPEmail2Addr());// this will equal the CURRENT user's email address; this user created the other one
			//userObject.put("roles",7); //--> commented out 2009-11-18 - broke with new pathauto upgrade; should be added as an array with authenticate
	        	userObject.put("status",1+"");
	        	userObject.put("timezone",aIndivObj.getUSPTimezone()+"");
	        }
	        catch (Exception e2){
	        	//TO DO ... HANDLE THE EXCEPTION!!!!
	        	aIndivObj.appendErrorMsg(" Generic Exception e: "+e2);
	        }
	        
	        String aszUserObject = "name,"+aIndivObj.getUSPUsername()+",pass,"+aIndivObj.getUSPPassword()+",mail,"+aIndivObj.getUSPEmail1Addr()+
	        	",init,"+aIndivObj.getUSPEmail2Addr()+",status,1,timezone,"+aIndivObj.getUSPTimezone();

			StringBuilder sbUser = new StringBuilder();
		    sbUser.append(timestamp);
		    sbUser.append(";");
		    sbUser.append(WS_DOMAIN);
		    sbUser.append(";");
		    sbUser.append(nonce);
		    sbUser.append(";");
		    sbUser.append(WS_ACTION_USER_SAVE);
		    hash = hmacSHA256(sbUser.toString(),WS_API_KEY.getBytes());
				
		    vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(userObject);
			// current error locally: Failed to parse server's response: Expected methodResponse element, got {http://www.w3.org/1999/xhtml}html
			//There was an error on saving this user: "Failed to parse server's response: Expected methodResponse element, 
			// got {http://www.w3.org/1999/xhtml}html" (Make sure the email address or username does not already exist in our system.)
			Object oUserSave = client.execute(WS_ACTION_USER_SAVE, vParams); 

			String asz_oUserSave=oUserSave.toString();
			int i_oUserSave = 0;
			try{
				i_oUserSave = Integer.parseInt(asz_oUserSave);
				aIndivObj.setUserUID(i_oUserSave);
			}catch(Exception e){
	        	iRetCode = aPersonObj.getUIDFromEmail(pConn, aIndivObj);
			}

			

System.out.println("services user save done. now need to write to db.");
			
			// will have to call a DB query to add further information for uprofile (including first and last names)
	    	//PersonObj 
	    	//aPersonObj = new PersonObj();
	    	//pConn = getDBConn();
	    	//if(null != pConn) pConn.free();
	    	if(aIndivObj.getUserUID() > 0){
	        	//pConn = getDBConn();
	        	if(mailkey.equals("newOppUserAccnt")){
		        	aIndivObj.setUSPInternalUserTypeComment("added-opp-contact");
	        	}else if(mailkey.equals("newOrgUserAccnt")){
		        	aIndivObj.setUSPInternalUserTypeComment("added-org-administrator");
	        	}else{
		        	aIndivObj.setUSPInternalUserTypeComment("added-org-contact");
	        	}
	        	// some fields are required only for Volunteer or Both (not if only using site as an Organization)
	        	aIndivObj.setUSPVolOrOpportunity(aszOrganizationUser);
	        	aIndivObj.setUSPVolunteer("No");
	    	    
	        	String aszRailsDB = "";
	    	    if(aszSiteVersion.equals("development")){
	    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
	    	    }else if(aszSiteVersion.equals("staging")){
	    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
	    	    }else{
	    	    	aszRailsDB = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
	    	    }
System.out.println("calling insertIndividualHead with CREATE_USER_ORG_CONTACT");
	        	iRetCode = aPersonObj.insertIndividualHead(pConn, aIndivObj, PersonInfoDTO.CREATE_USER_ORG_CONTACT, aszRailsDB);
System.out.println("done with  insertIndividualHead with CREATE_USER_ORG_CONTACT; iRetCode is "+iRetCode);
	        	if(null != pConn) pConn.free();
	    	}
			// END services user.save
				
				/*
				// got various errors:
				// 1st, "Public Volunteer Profile Listing field is required.
				//		Are you using this site as an Individual/Volunteer, an Organization, or both? field is required.
				//		Location field is required."
				// added Public Volunteer Profile Listing, but don't know how to add Location field (separate module - I think as an array)
				//
				// current error following that: "Failed to parse server's response: Expected methodResponse element, got br"
	    		try{
			    	// add uprofile node through xml-rpc services call
					JSONObject nodeObject=new JSONObject();
					nodeObject.put("title",aIndivObj.getUSPUsername());
					nodeObject.put("type","uprofile"); 
					nodeObject.put("field_site_use_type","Organization"); 
					//nodeObject.put("Public Volunteer Profile Listing",aIndivObj.getUSPVolunteerTID());
					//nodeObject.put("edit-taxonomy-279",aIndivObj.getUSPVolunteerTID()); 
					nodeObject.put("field_first_name",aIndivObj.getUSPNameFirst()); 
					nodeObject.put("field_last_name",aIndivObj.getUSPNameLast()); 
					nodeObject.put("uid",i_oUserSave);
	
					iTimeStamp = (int) (date.getTime() * .001);
			        timestamp = iTimeStamp + ""; // still might need some kind of timezone adjustment for live, i think - unless both can be Central
	
			    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
			    	nonce = nonce.substring(0,9);
	
			    	StringBuilder sbNode = new StringBuilder();
			        sbNode.append(timestamp);
			        sbNode.append(";");
			        sbNode.append(WS_DOMAIN);
			        sbNode.append(";");
			        sbNode.append(nonce);
			        sbNode.append(";");
			        sbNode.append(WS_ACTION_NODE_SAVE);
			        hash = hmacSHA256(sbNode.toString(),WS_API_KEY.getBytes());
					
			        vParams.clear();
					vParams.add(hash);
					vParams.add(WS_DOMAIN);
					vParams.add(timestamp);
					vParams.add(nonce);
					vParams.add(sessid);
					vParams.add(nodeObject);
					Object oNodeSave = client.execute(WS_ACTION_NODE_SAVE, vParams);

	    		} catch (XmlRpcException e) {
	    			String sMessage = e.getMessage();
	    			aIndivObj.setErrorMsg(sMessage);
	    			e.printStackTrace();
	    			return 555;
	    		}
	    	*/
			/*
    		} catch (XmlRpcException e) {
    			String sMessage = e.getMessage();
    			aIndivObj.setErrorMsg(sMessage);
    			e.printStackTrace();
    			return 444;
    		}
			*/

	    	String aszContactSubject = "", aszContactBody="";
			// BEGIN system.mail Service
        	if(mailkey.equals("newOppUserAccnt")){
    	    	aszContactSubject = "New Contact Account on " + aszSubdomain; 
    	    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
    	    		"Welcome to " + aszSubdomain + ".  An account was recently created for you by a user on our system with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail2Addr() + " as a Volunteer Contact for the following Opportunity: \n\n" + aOpportObj.getOPPTitle() +
    	    		", owned by the Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nYour Username and Password are as follows: " +
    	    		"\n\n  Username:      " + aIndivObj.getUSPUsername() + "\n  Email Address: " + aIndivObj.getUSPEmail1Addr() + "\n  Password:      " + aIndivObj.getUSPPassword() +
    	    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
    	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
        	}else if(mailkey.equals("newOrgUserAccnt")){
    	    	aszContactSubject = "New Administrator Account on " + aszSubdomain;
    	    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
    	    		"Welcome to " + aszSubdomain + ".  An account was recently created for you by a user on our system with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail2Addr() + " to administer the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nYour Username and Password are as follows: " +
    	    		"\n\n  Username:      " + aIndivObj.getUSPUsername() + "\n  Email Address: " + aIndivObj.getUSPEmail1Addr() + "\n  Password:      " + aIndivObj.getUSPPassword() +
    	    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
    	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
        	}else if(mailkey.equals("newOrgContactUserAccnt")){
    	    	aszContactSubject = "New Contact Account on " + aszSubdomain;
    	    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
    	    		"Welcome to " + aszSubdomain + ".  An account was recently created for you by a user on our system with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail2Addr() + " to be a contact for the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nYour Username and Password are as follows: " +
    	    		"\n\n  Username:      " + aIndivObj.getUSPUsername() + "\n  Email Address: " + aIndivObj.getUSPEmail1Addr() + "\n  Password:      " + aIndivObj.getUSPPassword() +
     	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
        	}else{// LEGACY
    	    	aszContactSubject = "New Contact Account on " + aszSubdomain;
    	    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
    	    		"Welcome to " + aszSubdomain + ".  An account was recently created for you by a user on our system with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail2Addr() + " to be a contact for the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nYour Username and Password are as follows: " +
    	    		"\n\n  Username:      " + aIndivObj.getUSPUsername() + "\n  Email Address: " + aIndivObj.getUSPEmail1Addr() + "\n  Password:      " + aIndivObj.getUSPPassword() +
    	    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
    	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
        	}

	    	String[] ArrayHeaders;
	    	ArrayHeaders = new String[2];
	    	ArrayHeaders[0] = "Cc:";
	    	ArrayHeaders[1] = aIndivObj.getUSPEmail2Addr();

			// nonce and timestamp may have expired during user.save - set new nonce and timestamp for this system.mail call
	        date = new Date();
	    	r1 = new Random();  
	    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);
	        iTimeStamp = (int) (date.getTime() * .001);
	        timestamp = iTimeStamp + ""; 

			StringBuilder sbMailContact = new StringBuilder();
			sbMailContact.append(timestamp);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_DOMAIN);
		    sbMailContact.append(";");
		    sbMailContact.append(nonce);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_ACTION_MAIL);
		    hash = hmacSHA256(sbMailContact.toString(),WS_API_KEY.getBytes());
				
		    vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(mailkey); // UNIQUE key to identify the mail sent, for altering
			vParams.add(aIndivObj.getUSPEmail1Addr());// To: Email address
			vParams.add(aszContactSubject);// Subject of this email
			vParams.add(aszContactBody);// Body of this email
			vParams.add(aszFromEmail);// could be default of info@um.org, or could be info@christianvolunteering.org/info@ivolunteering.org
			vParams.add(ArrayHeaders);// can include the Email2 address Cc to also send a copy of this email to the current user as well as the new user
			Object oMail = client.execute(WS_ACTION_MAIL, vParams);
			String asz_oMail=oMail.toString();
			// END services system.mail
	    	
			// ********* SEND AN EMAIL TO THE CURRENT USER, as well - IF IT IS A DIFFERENT USER
		if(! aIndivObj.getUSPEmail1Addr().equals(aIndivObj.getUSPEmail2Addr()) ){
			// BEGIN system.mail Service
        	if(mailkey.equals("newOppUserAccnt")){
    	    	aszContactSubject = "New Contact on " + aszSubdomain; 
    	    	aszContactBody = "Hello, \n\n" +
    	    		"This email is to notify you that you have recently created a user on our system at " + aszSubdomain + " with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail1Addr() + " as a Volunteer Contact for the following " +
    	    		"Opportunity: \n\n" + aOpportObj.getOPPTitle() +
    	    		", owned by the Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nIf this Email Address should not have editing privileges on the given opportunity, please visit the following page to " +
    	    		"remove them: " + aszSubdomain + "/org.do?method=showOppContacts&orgnid=" + aOrgInfoObj.getORGNID() +"&oppnid=" + aOpportObj.getOPPNID() +
    	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
    	    	mailkey = "youAddedNewOppContact";
        	}else if(mailkey.equals("newOrgUserAccnt")){
    	    	aszContactSubject = "New Administrator on " + aszSubdomain;
    	    	aszContactBody = "Hello, \n\n" +
    	    		"This email is to notify you that you have recently created a user on our system at " + aszSubdomain + " with the " +
    	    		"Email Address: " + aIndivObj.getUSPEmail1Addr() + " and have given it administrative privileges on the following " +
    	    		"Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
    	    		"\n\nIf this Email Address should not have administrative privileges on your organization, please visit the following page to " +
    	    		"remove them: " + aszSubdomain + "/org.do?method=showOrgAdmins&orgnid=" + aOrgInfoObj.getORGNID() +
    	    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
    	    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
    	    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
    	    	mailkey = "youAddedNewOrgAdmin";
        	}else if(mailkey.equals("newOrgContactUserAccnt")){ 
    	    	aszContactSubject = "New Contact on " + aszSubdomain; 
    	    	aszContactBody = "Hello, \n\n" +
		    		"This email is to notify you that you have recently created a user on our system at " + aszSubdomain + " with the " +
		    		"Email Address: " + aIndivObj.getUSPEmail1Addr() + " and now have the option of adding it as an Organizational Contact " +
		    		"for any listings on the following " +
		    		"Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
    	    	mailkey = "youAddedNewOrgContact";
        	}else{ // LEGACY
    	    	aszContactSubject = "New Contact on " + aszSubdomain; 
    	    	aszContactBody = "Hello, \n\n" +
		    		"This email is to notify you that you have recently created a user on our system at " + aszSubdomain + " with the " +
		    		"Email Address: " + aIndivObj.getUSPEmail1Addr() + " and have given it administrative privileges on the following " +
		    		"Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nIf this Email Address should not have administrative privileges on your organization, please visit the following page to " +
		    		"remove them: " + aszSubdomain + "/org.do?method=showOrgContacts&orgnid=" + aOrgInfoObj.getORGNID() +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
    	    	mailkey = "youAddedNewOrgContact";
        	}

			// nonce and timestamp may have expired during user.save - set new nonce and timestamp for this system.mail call
	        date = new Date();
	    	r1 = new Random();  
	    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);
	        iTimeStamp = (int) (date.getTime() * .001);
	        timestamp = iTimeStamp + ""; 

			sbMailContact = new StringBuilder();
			sbMailContact.append(timestamp);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_DOMAIN);
		    sbMailContact.append(";");
		    sbMailContact.append(nonce);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_ACTION_MAIL);
		    hash = hmacSHA256(sbMailContact.toString(),WS_API_KEY.getBytes());
				
		    vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(mailkey); // UNIQUE key to identify the mail sent, for altering
			vParams.add(aIndivObj.getUSPEmail2Addr());// To: Email address
			vParams.add(aszContactSubject);// Subject of this email
			vParams.add(aszContactBody);// Body of this email
			vParams.add(aszFromEmail);// could be default of info@um.org, or could be info@christianvolunteering.org/info@ivolunteering.org
			oMail = client.execute(WS_ACTION_MAIL, vParams);
			asz_oMail=oMail.toString();
			// END services system.mail
			
//			setErr("addNewUserServices success" );
			//ErrorsToLog();
		}			
			return iRetCode;

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return 1;
		} catch (XmlRpcException e) {
			String sMessage = e.getMessage();
			aIndivObj.setErrorMsg(sMessage);
System.out.println("XmlRpcException error: "+sMessage);
			e.printStackTrace();
			return 333;
		} catch (NullPointerException e) {
			String sMessage = e.getMessage();
System.out.println("NullPointerException error: "+sMessage);
			aIndivObj.setErrorMsg(sMessage);
			e.printStackTrace();
			return 333;
		} catch (Exception e) {
			String sMessage = e.getMessage();
System.out.println("Exception error: "+sMessage);
			aIndivObj.setErrorMsg(sMessage);
			e.printStackTrace();
			return 333;
		}
	}

	
	
	public int emailNotify( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, int iEmailUseCase, String aszPortal){
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
		return emailNotify(  httpServletRequest,  aIndivObj,  aOrgInfoObj, aOpportObj, iEmailUseCase,  aszPortal);
	}	
	public int emailNotify( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, String aszEmailUseCase, String aszPortal){
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        int iEmailUseCase=0;
        if(aszEmailUseCase.equalsIgnoreCase("addedOrgAdmin")){
        	iEmailUseCase = iAddedAdmin;
        } else if(aszEmailUseCase.equalsIgnoreCase("removedAdministrator")){
        	iEmailUseCase = iRemovedAdmin;
        } else if(aszEmailUseCase.equalsIgnoreCase("receivesVolunteerEmails")){
        	iEmailUseCase = iNowGetsEmailsLegacy;
        } else if(aszEmailUseCase.equalsIgnoreCase("doesNotReceiveVolunteerEmails")){
        	iEmailUseCase = iNoLongerGetsEmailsLegacy;
        } else if(aszEmailUseCase.equalsIgnoreCase("addedContact")){
        	iEmailUseCase = iNowIsOrgContactLegacy;
        } else if(aszEmailUseCase.equalsIgnoreCase("removedAdministrator")){
        	iEmailUseCase = iNoLongerOrgContactLegacy;
        }
		return emailNotify(  httpServletRequest,  aIndivObj,  aOrgInfoObj, aOpportObj, iEmailUseCase,  aszPortal);
	}	
	public int emailNotify( HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, OrganizationInfoDTO aOrgInfoObj, OrgOpportunityInfoDTO aOpportObj, int iEmailUseCase, String aszPortal){
		int iRetCode=0, iTemp=0;
		ABREDBConnection pConn=null;
		String hash = null;
		String aszPhoneSupport = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_PHONE_SUPPORT);
		String aszSiteOrgName = this.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
		String aszEmailMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
		String aszEmailSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
		String aszEmailFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_EMAIL);
		String aszDomFaith = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN);
		String aszDomFaithShort = this.getSitePropertyValue(ABREAppServerDef.APP_FAITH_DOMAIN_SHORT);
		String aszDomMain = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
		String aszDomMainShort = this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
		String aszDomSecondary = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
		String aszDomSecondaryShort = this.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
		String aszServicesXMLRPCpublicURL = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_XMLRPC_PUBLIC_URL);
		String aszServicesXMLRPCtestingURL = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_XMLRPC_TESTING_URL);
		String WS_API_KEY = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_APIKEY);
		String WS_DOMAIN = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_DOMAIN);
		String WS_Services_Username = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_USERNAME);
		String WS_Services_Password = this.getSitePropertyValue(ABREAppServerDef.DRUPAL_SERVICES_PASSWORD);
		
		String aszContactSubject = "Your Account on " + aszDomMainShort;
    	String aszContactBody = "";
    	String aszCurrentUserEmail = aIndivObj.getUSPEmail2Addr();    	
    	String aszContactEmail = aIndivObj.getUSPEmail1Addr();    	

		if(aIndivObj.getUSPSubdom().length() < 2){
			aIndivObj.setUSPSubdom(aszDomMain);
		}
		String aszSubdomain = aIndivObj.getUSPSubdom();
		if(aszPortal.length()>0){
			aszSubdomain += "/" + aszPortal;
		}
		if(aszSubdomain.equals("default")) aszSubdomain="www.ChristianVolunteering.org";
    	String aszFromEmail=null;
    	if(aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomSecondaryShort) ||
    			aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomSecondary)){
    		aszFromEmail = aszEmailSecondary;
    		aszContactSubject = "Your Account on " + aszDomSecondaryShort;
    	}else if(aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomFaithShort) ||
    			aIndivObj.getUSPSubdom().equalsIgnoreCase(aszDomFaith)){
    		aszFromEmail = aszEmailFaith;
    		aszContactSubject = "Your Account on " + aszDomFaithShort;
    	}else{

    		aszFromEmail = aszEmailMain;
    	}

    	// validate email address format
    	boolean bMinOK=true;
    	String aszPassword, aszPassVerify;
    	iRetCode = checkEmailFormat( aIndivObj.getUSPEmail1Addr() );
    	if(0 != iRetCode){
    		aIndivObj.appendErrorMsg("Email format error");
    		return -1;
    	}
    	if(false == bMinOK){
    		return -111;
    	}
		
    	String WS_URL = "";
		if ( httpServletRequest.getHeader("host").contains(":7001") 
				|| httpServletRequest.getHeader("host").contains(":8080")  
				|| httpServletRequest.getHeader("host").contains("chrisvol.org") 
				|| httpServletRequest.getHeader("host").contains("cv.org") 
		){
			WS_URL = aszServicesXMLRPCtestingURL;
		}else{
			WS_URL = aszServicesXMLRPCpublicURL;
		}

		String mailkey=null;
		switch( iEmailUseCase ){
			case iAddedAdmin:
		    	mailkey = "newOrgAdmin";
		    	aszContactSubject = "New Administrator on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be an administrator of the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iRemovedAdmin:
		    	mailkey = "removedOrgAdmin";
		    	aszContactSubject = "Administrator Removal Notice on " + aszSubdomain;
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that you have been removed as an Administrator " +
		    		"and Volunteer Contact by " + aszCurrentUserEmail + " for the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		".\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNowGetsEmails:
		    	mailkey = "newOppEmailContact";
		    	aszContactSubject = "New Volunteer Contact on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be a Volunteer Contact for the following Opportunity: \n\n" + aOpportObj.getOPPTitle() +
		    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\".  You will now receive emails from volunteers " +
		    		"who attempt to connect with you through our site." +
		    		"\n\nTo proceed with managing this opportunity, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNoLongerGetsEmails:
		    	mailkey = "oppEmailContactRemoved";
		    	aszContactSubject = "Account update on " + aszSubdomain;
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that  " + aszCurrentUserEmail + 
		    		" has edited account settings so that " +
		    		"Email Address: " + aszContactEmail + " will no longer be receiving Volunteer Inquiry Emails from this site for the following" +
		    		" Opportunity: \n\n" + aOpportObj.getOPPTitle() + ", owned by the Organization \"" + aOrgInfoObj.getORGOrgName() + "\"" +
		    		".\n\nPlease make sure that this opportunity has a valid contact to respond to volunteer inquiries." +
		    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNowIsOppContact :
		    	mailkey = "newOppContact";
		    	aszContactSubject = "New Contact on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to have access to manage the following Opportunity: \n\n" + aOpportObj.getOPPTitle() +
		    		", owned by Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nTo proceed with managing this opportunity, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNoLongerIsOppContact:
		    	mailkey = "oppContactRemoved";
		    	aszContactSubject = "Account update on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that  " + aszCurrentUserEmail + 
		    		" has edited account settings so that " +
		    		"Email Address: " + aszContactEmail + " will no longer be have access to manage the following" +
		    		" Opportunity: \n\n" + aOpportObj.getOPPTitle() + ", owned by the Organization \"" + aOrgInfoObj.getORGOrgName() + "\"" +
		    		".\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNewPrimaryContact:
		    	mailkey = "newOppPrimaryContact";
		    	aszContactSubject = "New Primary Contact on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be the Primary Volunteer Contact for the following Opportunity: \n\n" + aOpportObj.getOPPTitle() +
		    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\".  You will now receive emails from volunteers " +
		    		"who attempt to connect with you through our site." +
		    		"\n\nTo proceed with managing this opportunity, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNowEmailAndContact :
		    	mailkey = "newOppEmailAndContact";
		    	aszContactSubject = "New Contact on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be a Volunteer Contact for the following Opportunity: \n\n" + aOpportObj.getOPPTitle() +
		    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\".  You will now receive emails from volunteers " +
		    		"who attempt to connect with you through our site." +
		    		"\n\nTo proceed with managing this opportunity, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNoLongerEmailOrContact:
		    	mailkey = "oppEmailAndContactRemoved";
		    	aszContactSubject = "Account update on " + aszSubdomain; 
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that  " + aszCurrentUserEmail + 
		    		" has edited account settings so that " +
		    		"Email Address: " + aszContactEmail + " will no longer be have access to manage or receive emails regarding the following" +
		    		" Opportunity: \n\n" + aOpportObj.getOPPTitle() + ", owned by the Organization \"" + aOrgInfoObj.getORGOrgName() + "\"" +
		    		".\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNowIsOrgContact: 
		    	mailkey = "newOrgContact";
		    	aszContactSubject = "New Contact on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be a contact for the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nTo proceed with managing this organization and its volunteer opportunities, " +
		    		"go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
				break;
		    	
			case iNowIsOrgContactLegacy: //LEGACY
		    	mailkey = "newOrgContact";//** LEGACY
		    	aszContactSubject = "New Contact on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"Welcome to " + aszSubdomain + ".  A user with the Email Address: " + aszCurrentUserEmail + 
		    		" has just added you to be an administrator of the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in with " +
		    		"your Email Address: " + aszContactEmail + "." +
		    		"\n\nIf you forget your password, go to http://www.urbanministry.org/user/password and enter your " +
		    		"Email Address to request a temporary password. " +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
				break;
			case iNowGetsEmailsLegacy:
				mailkey = "receiveVolEmail";
		    	aszContactSubject = "Account update on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that " + aszCurrentUserEmail + 
		    		" has recently added your " +
		    		"Email Address: " + aszContactEmail + " to now be receiving Volunteer Inquiry Emails from this site for " +
		    		"the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() + 
		    		".\n\nIt is very important that you or someone else from your organization " +
		    		"respond to any inquiries from " + aIndivObj.getUSPSubdom() + " in a timely manner." +
		    		"\n\nTo proceed with managing this organization, go to http://" +  aIndivObj.getUSPSubdom() + "/orgmanagement.jsp and log in." +
		    		"\n\nIf this email address should not receive volunteer inquiry emails related to your organization, " +
		    		"please visit the following page to change their setting on Receiving Volunteer Emails: http://" + 
		    		aszSubdomain + "/org.do?method=showOrgContacts&orgnid=" + aOrgInfoObj.getORGNID() + 
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
			case iNoLongerGetsEmailsLegacy:
		    	mailkey = "receiveVolEmail";
		    	aszContactSubject = "Account update on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
		    	aszContactBody = "Hello " + aIndivObj.getUSPNameFirst() + " " + aIndivObj.getUSPNameLast() + ", \n\n" +
		    		"This is an automated email from " + aszSubdomain + " to inform you that  " + aszCurrentUserEmail + 
		    		" has edited account settings so that " +
		    		"Email Address: " + aszContactEmail + " will no longer be receiving Volunteer Inquiry Emails from this site for the following" +
		    		" Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
		    		".\n\nPlease make sure that your organization has a valid contact to respond to volunteer inquiries." +
		    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
		    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
		    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
		    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
		    	break;
	    	default:
	    		break;
		}

		if(aszContactBody.length()<10){
			return -100;
		}
		Vector vParams = new Vector();
    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
	        Date date = new Date();
	    	Random r1 = new Random();  
	    	String nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);

	        config.setServerURL(new URL(WS_URL));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);

			Object oConnect = client.execute(WS_ACTION_CONNECT, vParams); // xmlrpc exception: 1
			HashMap hm = (HashMap)oConnect;
			String sessid = (String)hm.get(WS_PARAM_SESSID);

	        int iTimeStamp = (int) (date.getTime() * .001);
	        String timestamp = iTimeStamp + ""; 

	        // BEGIN login Services User
	    	StringBuilder sbUserLogin = new StringBuilder();
	        sbUserLogin.append(timestamp);
	        sbUserLogin.append(";");
	        sbUserLogin.append(WS_DOMAIN);
	        sbUserLogin.append(";");
	        sbUserLogin.append(nonce);
	        sbUserLogin.append(";");
	        sbUserLogin.append(WS_ACTION_LOGIN);
	        hash = hmacSHA256(sbUserLogin.toString(),WS_API_KEY.getBytes());
			
	        vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(WS_Services_Username);
			vParams.add(WS_Services_Password);
			
			Object oUserLogin = client.execute(WS_ACTION_LOGIN, vParams);
			hm = (HashMap)oUserLogin;
			sessid = (String)hm.get(WS_PARAM_SESSID);
			// END login Services

			// BEGIN system.mail Service
	    	String[] ArrayHeaders;
	    	ArrayHeaders = new String[2];
	    	ArrayHeaders[0] = "Cc:";
	    	ArrayHeaders[1] = aIndivObj.getUSPEmail1Addr();

			// nonce and timestamp may have expired during user.save - set new nonce and timestamp for this system.mail call
	        date = new Date();
	    	r1 = new Random();  
	    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
	    	nonce = nonce.substring(0,9);
	        iTimeStamp = (int) (date.getTime() * .001);
	        timestamp = iTimeStamp + ""; 

			StringBuilder sbMailContact = new StringBuilder();
			sbMailContact.append(timestamp);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_DOMAIN);
		    sbMailContact.append(";");
		    sbMailContact.append(nonce);
		    sbMailContact.append(";");
		    sbMailContact.append(WS_ACTION_MAIL);
		    hash = hmacSHA256(sbMailContact.toString(),WS_API_KEY.getBytes());
				
		    vParams.clear();
			vParams.add(hash);
			vParams.add(WS_DOMAIN);
			vParams.add(timestamp);
			vParams.add(nonce);
			vParams.add(sessid);
			vParams.add(mailkey); // UNIQUE key to identify the mail sent, for altering
			vParams.add(aszContactEmail);// To: Email address - sends to other user that the email is about
			vParams.add(aszContactSubject);// Subject of this email
			vParams.add(aszContactBody);// Body of this email
			vParams.add(aszFromEmail);// could be default of info@um.org, or could be info@christianvolunteering.org/info@ivolunteering.org
			vParams.add(ArrayHeaders);// can include the Email2 address Cc to also send a copy of this email to the current user as well as the new user
			Object oMail = client.execute(WS_ACTION_MAIL, vParams);
			String asz_oMail=oMail.toString();
			// END services system.mail

			// ********* SEND AN EMAIL TO THE CURRENT USER, as well, if a new Contact was just added (?? do we want to also do so regarding Contacts?)
			switch(iEmailUseCase){
				case iAddedAdmin:
			    	mailkey = "youAddedNewOrgContact";
					// BEGIN system.mail Service
			    	aszContactSubject = "New Contact on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
			    	aszContactBody = "Hello, \n\n" +
			    		"This email is to notify you that you have recently added  a user on our system at " + 
			    		aszSubdomain + " with the " +
			    		"Email Address: " + aszContactEmail + " to have administrative privileges on the following Organization: " + aOrgInfoObj.getORGOrgName() +
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iRemovedAdmin:
			    	mailkey = "youRemovedOrgAdmin";
			    	aszContactSubject = "An Administrator Has Been Removed on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that " + aszContactEmail +
			    		" has been removed as an Administrator " +
			    		"and Volunteer Contact for the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() + "\n\nby " + aszCurrentUserEmail +
			    		".\n\nIf this Email Address should not have administrative privileges on your organization or receive volunteer inquiry " +
			    		"emails, please visit the following page to " +
			    		"manage your organization contacts: " + aszSubdomain + "/org.do?method=showOrgContacts&orgnid=" + aOrgInfoObj.getORGNID() +
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNowGetsEmails :
			    	mailkey = "youSetReceiveVolEmail";
			    	aszContactSubject = "New Volunteer Contact on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This email is to notify you that you have recently added  a user on our system at " + 
			    		aszSubdomain + " with the " +
			    		"Email Address: " + aszContactEmail + " to now be receiving Volunteer Inquiry Emails from this site for " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"" +
			    		".\n\nIt is very important that you or someone else from your organization " +
			    		"respond to any inquiries from " + aszSubdomain + " in a timely manner." +
			    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
			    		"\n\nIf this email address should not receive volunteer inquiry emails related to this opportunity, " +
			    		"please visit the following page to change their setting on Receiving Volunteer Emails: http://" + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNoLongerGetsEmails:
			    	mailkey = "youSetNotReceiveVolEmail";
			    	aszContactSubject = "A Volunteer Contact Has Been Removed on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that you have removed " + aszContactEmail +
			    		" as a Volunteer Contact for the following Opportunity: \n\n" + aOpportObj.getOPPTitle() + 
			    		", owned by Organization: \n\n" + aOrgInfoObj.getORGOrgName() + 
			    		".\n\nThis user should still be able to manage the opportunity, however will no longer recevie Volunteer Inquiry Emails regarding " +
			    		"this opportunity." +
			    		"\n\nTo manage any related to this opportunity, " +
			    		"please visit the following page: " + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNowIsOppContact :
			    	mailkey = "youSetOppContact";
			    	aszContactSubject = "New Contact on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This email is to notify you that you have recently added  a user on our system at " + 
			    		aszSubdomain + " with the " +
			    		"Email Address: " + aszContactEmail + " to now be able to manage the profile on this site for " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"." +
			    		"\n\nTo manage any related to this opportunity, " +
			    		"please visit the following page: " + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNoLongerIsOppContact:
			    	mailkey = "youRemovedOppContact";
			    	aszContactSubject = "Account update on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that the " +
			    		"Email Address: " + aszContactEmail + " will no longer be able to manage the profile on this site for " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"." +
			    		"\n\nTo manage any related to this opportunity, " +
			    		"please visit the following page: " + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNewPrimaryContact :
			    	mailkey = "youSetOppPrimaryContact";
			    	aszContactSubject = "Account update on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This email is to notify you that you have recently modified a user on our system at " + 
			    		aszSubdomain + " with the " +
			    		"Email Address: " + aszContactEmail + " to now be the Primary Contact and receive Volunteer Inquiry Emails from this site for " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"" +
			    		".\n\nIt is very important that you or someone else from your organization " +
			    		"respond to any inquiries from " + aszSubdomain + " in a timely manner." +
			    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
			    		"\n\nIf this email address should not receive volunteer inquiry emails related to this opportunity, " +
			    		"please visit the following page to change their setting on Receiving Volunteer Emails: http://" + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNowEmailAndContact :
			    	mailkey = "youSetReceiveEmailAndManage";
			    	aszContactSubject = "Account update on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This email is to notify you that you have recently added  a user on our system at " + 
			    		aszSubdomain + " with the " +
			    		"Email Address: " + aszContactEmail + " to now be receiving Volunteer Inquiry Emails from this site for " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"" +
			    		".\n\nIt is very important that you or someone else from your organization " +
			    		"respond to any inquiries from " + aszSubdomain + " in a timely manner." +
			    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
			    		"\n\nIf this email address should not receive volunteer inquiry emails related to this opportunity, " +
			    		"please visit the following page to change their setting on Receiving Volunteer Emails: http://" + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNoLongerEmailOrContact:
			    	mailkey = "youSetRemoveEmailAndManage";
			    	aszContactSubject = "Account update on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that the " +
			    		"Email Address: " + aszContactEmail + " will no longer be able to manage the Opportunity profile on this site or receive emails regarding " +
			    		"the following Opportunity: " + aOpportObj.getOPPTitle() +
			    		", owned by Organization: \"" + aOrgInfoObj.getORGOrgName() + "\"." +
			    		"\n\nTo manage any related to this opportunity, " +
			    		"please visit the following page: " + 
			    		aszSubdomain + "/org.do?method=showOpportunityEdit&requesttype=edit&orgnid=" + aOrgInfoObj.getORGNID() + "&oppnid=" + aOpportObj.getOPPNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNowGetsEmailsLegacy:
			    	mailkey = "receiveVolEmail";
			    	aszContactSubject = "Account update on " + aszSubdomain;
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that the " +
			    		"Email Address: " + aszContactEmail + " is set to now be receiving Volunteer Inquiry Emails from this site for " +
			    		"the following Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
			    		".\n\nIt is very important that you or someone else from your organization " +
			    		"respond to any inquiries from " + aszSubdomain + " in a timely manner." +
			    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
			    		"\n\nIf this email address should not receive volunteer inquiry emails related to your organization, " +
			    		"please visit the following page to change their setting on Receiving Volunteer Emails: http://" + 
			    		aszSubdomain + "/org.do?method=showOrgContacts&orgnid=" + aOrgInfoObj.getORGNID() + 
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				case iNoLongerGetsEmailsLegacy:
			    	mailkey = "receiveVolEmail";
			    	aszContactSubject = "Account update on " + aszSubdomain; //"Welcome to " + aIndivObj.getUSPSubdom();
			    	aszContactBody = "Hello, \n\n" +
			    		"This is an automated email from " + aszSubdomain + " to inform you that the " +
			    		"Email Address: " + aszContactEmail + " will no longer be receiving Volunteer Inquiry Emails from this site for the following" +
			    		" Organization: \n\n" + aOrgInfoObj.getORGOrgName() +
			    		".\n\nPlease make sure that your organization has a valid contact to respond to volunteer inquiries." +
			    		"\n\nTo proceed with managing this organization, go to http://" +  aszSubdomain + "/orgmanagement.jsp and log in." +
			    		"\n\nThank you for using " + aszSubdomain + ", powered by " + aszSiteOrgName +
			    		"\n\nSincerely,\n\nThe " + aszSiteOrgName + " Staff " +
			    		"\n\nP.S. If you have any questions, problems or need any help, please feel free to email " + aszFromEmail + ".";
			    	break;
				default:
					break;
			}

				// nonce and timestamp may have expired during user.save - set new nonce and timestamp for this system.mail call
		        date = new Date();
		    	r1 = new Random();  
		    	nonce = Long.toString(Math.abs(r1.nextLong()), 10);  
		    	nonce = nonce.substring(0,9);
		        iTimeStamp = (int) (date.getTime() * .001);
		        timestamp = iTimeStamp + ""; 
	
				sbMailContact = new StringBuilder();
				sbMailContact.append(timestamp);
			    sbMailContact.append(";");
			    sbMailContact.append(WS_DOMAIN);
			    sbMailContact.append(";");
			    sbMailContact.append(nonce);
			    sbMailContact.append(";");
			    sbMailContact.append(WS_ACTION_MAIL);
			    hash = hmacSHA256(sbMailContact.toString(),WS_API_KEY.getBytes());
					
			    vParams.clear();
				vParams.add(hash);
				vParams.add(WS_DOMAIN);
				vParams.add(timestamp);
				vParams.add(nonce);
				vParams.add(sessid);
				vParams.add(mailkey); // UNIQUE key to identify the mail sent, for altering
				vParams.add(aszCurrentUserEmail);// To: Email address
				vParams.add(aszContactSubject);// Subject of this email
				vParams.add(aszContactBody);// Body of this email
				vParams.add(aszFromEmail);// could be default of info@um.org, or could be info@christianvolunteering.org/info@ivolunteering.org
				oMail = client.execute(WS_ACTION_MAIL, vParams);
				asz_oMail=oMail.toString();
				// END services system.mail
			
			iRetCode = 0;
	    	return iRetCode;

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return 1;
		} catch (XmlRpcException e) {
			String sMessage = e.getMessage();
			aIndivObj.setErrorMsg(sMessage);
			e.printStackTrace();
			return 333;
		}
	}

	
	
	
	/**
     * As adapted from iTunes U Administrator Guide...
     * http://deimos.apple.com/rsrc/doc/iTunesUAdministratorsGuide/AdministeringSiteAccess/chapter_7_section_9.html
     * 
     * Generate the HMAC-SHA256 signature of a message string, as defined in
     * <A HREF="http://www.ietf.org/rfc/rfc2104.txt">RFC 2104</A>.
     *
     * @param message The string to sign.
     * @param key The bytes of the key to sign it with.
     *
     * @return A hexadecimal representation of the signature.
     */
    public String hmacSHA256(String message, byte[] key) {

        // Start by getting an object to generate SHA-256 hashes with.
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new java.lang.AssertionError(
                    this.getClass().getName()
                    + ".hmacSHA256(): SHA-256 algorithm not found!");
        }

        // Hash the key if necessary to make it fit in a block (see RFC 2104).
        if (key.length > 64) {
            sha256.update(key);
            key = sha256.digest();
            sha256.reset();
        }

        // Pad the key bytes to a block (see RFC 2104).
        byte block[] = new byte[64];
        for (int i = 0; i < key.length; ++i) block[i] = key[i];
        for (int i = key.length; i < block.length; ++i) block[i] = 0;

        // Calculate the inner hash, defined in RFC 2104 as
        // SHA-256(KEY ^ IPAD + MESSAGE)), where IPAD is 64 bytes of 0x36.
        for (int i = 0; i < 64; ++i) block[i] ^= 0x36;
        sha256.update(block);
        try {
            sha256.update(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new java.lang.AssertionError(
                    "hmacSH256(): UTF-8 encoding not supported!");
        }
        byte[] hash = sha256.digest();
        sha256.reset();

        // Calculate the outer hash, defined in RFC 2104 as
        // SHA-256(KEY ^ OPAD + INNER_HASH), where OPAD is 64 bytes of 0x5c.
        for (int i = 0; i < 64; ++i) block[i] ^= (0x36 ^ 0x5c);
        sha256.update(block);
        sha256.update(hash);
        hash = sha256.digest();

        // The outer hash is the message signature...
        // convert its bytes to hexadecimals.
        char[] hexadecimals = new char[hash.length * 2];
        for (int i = 0; i < hash.length; ++i) {
            for (int j = 0; j < 2; ++j) {
                int value = (hash[i] >> (4 - 4 * j)) & 0xf;
                char base = (value < 10) ? ('0') : ('a' - 10);
                hexadecimals[i * 2 + j] = (char)(base + value);
            }
        }

        // Return a hexadecimal string representation of the message signature.
        return new String(hexadecimals);

    }
    
    /*
     * Decode the signed_request parameter from Facebook.  Based on the code here: http://www.hammersoft.de/blog/?p=87
     * Written on 1/25/2011
     * used for the FB APP
     */
    public org.json.JSONObject decodeFacebookSignedRequest(String signed_request, String secret){
    	//String signed_request = "naexpRxk4mMehDc6ldyhIaoUFs8AoVt-kFcs5t5eBXw.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImV4cGlyZXMiOjEyOTYwODY0MDAsImlzc3VlZF9hdCI6MTI5NjA4MjM3MCwib2F1dGhfdG9rZW4iOiIxMzcyOTA5NDYyODI0ODd8Mi4yUGpxS1FDRkhwNXRNUndYMjVvbkV3X18uMzYwMC4xMjk2MDg2NDAwLTEwMDAwMTAxMjE5ODQ3M3xXNGNILTZRTGpUXzE1YlYtWWxtWHFZZVdzeFEiLCJ1c2VyIjp7ImxvY2FsZSI6ImVuX1VTIiwiY291bnRyeSI6InVzIn0sInVzZXJfaWQiOiIxMDAwMDEwMTIxOTg0NzMifQ";

    	int index = signed_request.indexOf('.');
    	byte[] sig = new Base64(true).decode(signed_request.substring(0,index).getBytes());
    	String rawpayload = signed_request.substring(index+1);
    	String payload = new String(new Base64(true).decode(rawpayload));
    	
		org.json.JSONObject jsonObj = new org.json.JSONObject();
		try{

			jsonObj = new org.json.JSONObject(payload);
			// Check to make sure the algorithm is HMAC-SHA256
			String algorithm = jsonObj.getString("algorithm");
			if(!algorithm.equals("HMAC-SHA256")){
				//this is NOT a valid request from Facebook, throw an appropriate error
				throw new java.lang.AssertionError(
	                    this.getClass().getName()
	                    + ".decodeFacebookSignedRequest(): Invalid request, algorithm is not HMAC-SHA256!");
			}
			// Then check the signature
			checkFacebookSignature(sig, rawpayload, secret);
			return jsonObj;
			
		}

		catch(JSONException e){
			String cause = e.getMessage();
			throw new java.lang.AssertionError(
                    this.getClass().getName()
                    + ".decodeFacebookSignedRequest(): JSON Error: " + cause);
		}
		
		//return jsonObj;
    }
    
    public void checkFacebookSignature(byte[] sig, String rawpayload, String secret){
    	//byte [] keyBytes = key.getBytes();
    	//byte [] hmacBytes = hmacSHA256(sig, keyBytes).getBytes();
    	try{
	    	SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
	    	Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
	    	mac.init(secretKeySpec);
	    	byte[] mysig = mac.doFinal(rawpayload.getBytes());
	    	
	    	
	    	if(!Arrays.equals(mysig, sig)){
	    		/* Non-matching signatures, throw some kind of exception */
	    		throw new java.lang.AssertionError(
	                    this.getClass().getName()
	                    + ".decodeFacebookSignedRequest(): Non-matching signatures for Facebook signed_request!");
	    		//throw new Error("Non-matching signatures for Facebook signed_request.");
	    	}
    	} catch (NoSuchAlgorithmException e){
    		//throw new Error("Unknown hash algorithm " + HMAC_SHA256_ALGORITHM, e);
    		throw new java.lang.AssertionError(
                    this.getClass().getName()
                    + ".decodeFacebookSignedRequest(): Unknown hash algorithm!");
    	} catch (InvalidKeyException e){
    		throw new java.lang.AssertionError(
                    this.getClass().getName()
                    + ".decodeFacebookSignedRequest(): Wrong key passed for request!");
    		//throw new Error("Wrong key for " + HMAC_SHA256_ALGORITHM, e);
    	}
    	
    	
    }
    
    public int validateFacebookConnectCookie(HttpServletRequest httpServletRequest, PersonInfoDTO aHeadObj, String subdomainHost){
//    	Get the facebook cookie
    	String aszAppID = "";
    	String aszAppSecret = "";
    	String aszFaithAppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_APPID);
    	String aszSecondaryAppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_APPID);
    	String aszPartner1AppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_APPID);
    	String aszPartner2AppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER2_APPID); // Hurricane Sandy
    	String aszMainAppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_MAIN_APPID);
    	String aszSandboxAppID = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SANDBOX_APPID); 
    	String aszFaithSecret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_SECRETKEY);
    	String aszSecondarySecret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_SECRETKEY);
    	String aszPartner1Secret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_SECRETKEY);
    	String aszPartner2Secret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER2_SECRETKEY);
    	String aszMainSecret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_MAIN_SECRETKEY);
    	String aszSandboxSecret = this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SANDBOX_SECRETKEY);
    	    
    	if(subdomainHost.contains(":7001")){
    		aszAppID = aszSandboxAppID;
    		aszAppSecret = aszSandboxSecret;
    	}else if(subdomainHost.contains("christianvolunteering")){
    		aszAppID = aszMainAppID;
    		aszAppSecret = aszMainSecret;
    	}else if(subdomainHost.contains("gospel")){
    		aszAppID = aszPartner1AppID;
    		aszAppSecret = aszPartner1Secret;
    	}else if(subdomainHost.contains("churchvolunteering")){
    		aszAppID = aszFaithAppID;
    		aszAppSecret = aszFaithSecret;
    	}else if(subdomainHost.contains("hurricanesandy")){
    		aszAppID = aszPartner1AppID;
    		aszAppSecret = aszPartner2Secret;
    	}else if(subdomainHost.contains("ivolunteering")){
    		aszAppID = aszSecondaryAppID;
    		aszAppSecret = aszSecondarySecret;
    	}else {
    		aszAppID = aszMainAppID;
    		aszAppSecret = aszMainSecret;
    	}	

  		String authCookieValue="";
  		String facebookUID = "";
        Cookie[] cookies = httpServletRequest.getCookies();  // why is this only reading www.chrisvol cookies instead of .chrisvol
//System.out.println("\r\ncookies cookies.length="+cookies.length);

        String cookieName="fbsr_" + aszAppID;
        if(cookies != null){
//System.out.println("cookies cookieName="+cookieName);
	        for(int i=0; i<cookies.length; i++) {
	        	Cookie cookie = cookies[i];
//System.out.println("\r\ncookies cookie.getName()="+cookie.getName()+"\r\n ");
	        	if (cookieName.equals(cookie.getName())){
	        		authCookieValue = cookie.getValue();
	        		break;
	        	}
	        }
//System.out.println("cookies authCookieValue="+authCookieValue+"\r\n ");
	        if(authCookieValue != null && ! authCookieValue.equals("")){
	        	//validate the cookie from facebook, to make sure it hasn't been tampered with
	        	try{
	        		// Decode the cookie value to replace some problematic encodings, otherwise the hashes won't match
		        	String fbCookieValue = URLDecoder.decode(authCookieValue, "UTF-8");
//System.out.println("cookies fbCookieValue="+fbCookieValue+"\r\n ");
		        	org.json.JSONObject objFBUser = decodeFacebookSignedRequest(fbCookieValue, aszAppSecret);	
		        	
		        	facebookUID = objFBUser.getString("user_id");
//System.out.println("cookies facebookUID="+facebookUID+"\r\n ");
		        	
		        	aHeadObj.setFacebookUID(facebookUID);
		        	return 0;
	        	} catch (UnsupportedEncodingException e){
	        		aHeadObj.appendErrorMsg("\r\n Incorrect encoding for Facebook Login");
//System.out.println("Incorrect encoding for Facebook Login");
	        		return -1;
	        	} catch (JSONException e) {
					// TODO Auto-generated catch block
   		System.out.println("json exception");
					e.printStackTrace();
				}
	           
	        	
	        }
        } else {
        	aHeadObj.appendErrorMsg("\r\n No Facebook Cookie Found.");
//System.out.println("no Facebook Cookie Found="+authCookieValue);
        	return -1;
        }
        return -1;
    }
    
    /* 
     * link Facebook Connect User
     */
    public int linkFacebookConnectUser(PersonInfoDTO aHeadObj){
    	int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("linkFacebookConnectUser");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	
    	aszTemp = aHeadObj.getFacebookUID();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("Facebook UID must be passed, ");
    		bMinOK=false;
    	}
    	
    	if(false == bMinOK) return -1;

    	iRetCode = updateIndividualHeadFacebookConnection(aHeadObj);
    	if(iRetCode != 0){
    		if(iRetCode == 555) aHeadObj.appendErrorMsg("This account is already linked with a different Facebook Account, ");
    		return iRetCode;
    	}
    	

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.linkFacebookConnectUser(pConn, aHeadObj);
    	if(iRetCode != 0){
    		aHeadObj.appendErrorMsg("There was a problem linking your Facebook account.  Please try again.");
    	}
    	if(null != pConn) pConn.free();
    	return iRetCode;		
    }
    
    /* 
     * link Linkedin Connect User
     */
    public int linkLinkedinConnectUser(PersonInfoDTO aHeadObj, String aszSiteVersion){
    	int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("linkLinkedinConnectUser");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	
    	aszTemp = aHeadObj.getLinkedInId();
    	if(aszTemp.length() < 1){
    		aHeadObj.appendErrorMsg("Linkedin UID must be passed, ");
    		bMinOK=false;
    	}
    	
    	if(false == bMinOK) return -1;

    	iRetCode = updateIndividualHeadLinkedinConnection(aHeadObj, getRailsDBBySiteVersion(aszSiteVersion));
    	if(iRetCode != 0){
    		if(iRetCode == 555) aHeadObj.appendErrorMsg("This account is already linked with a different Linkedin Account, ");
    	}
    	return iRetCode;	
    }

	private int updateIndividualHeadLinkedinConnection(PersonInfoDTO aHeadObj, String railsDB) {
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null ;
    	boolean bMinOK=true;
    	MethodInit("updateIndividualHeadLinkedinConnection");

    	aszTemp = aHeadObj.getUSPEmail1Addr();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Email required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;


    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
    	iRetCode = aPersonObj.updateIndividualHeadLinkedinConnection(pConn, aHeadObj, railsDB);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

	/**
	* does this facebook user id (grabbed from cookie) exist in the user profile?
	*/
	public int isFacebookUserInSystem( String facebookUID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == facebookUID) return -1;
    	MethodInit("isFacebookUserInSystem");

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
		iRetCode = aPersonObj.isFacebookUserInSystem(pConn, facebookUID);
		
		if(iRetCode != 0){
			
		}
		
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method isFacebookUserInSystem()
	
	/**
	 * does this linkedin user id exist in the user profile?
	 */
	public int isLinkedInUserInSystem( String linkedInId, String aszSiteVersion ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == linkedInId) return -1;
    	MethodInit("isLinkedInUserInSystem");

    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();
		iRetCode = aPersonObj.isLinkedInUserInSystem(pConn, linkedInId, getRailsDBBySiteVersion(aszSiteVersion));
		
		if(iRetCode != 0){
			
		}
		
    	if(null != pConn) pConn.free();
    	return iRetCode;
    }
	
	private String getRailsDBBySiteVersion(String aszSiteVersion) {
		if(aszSiteVersion.equals("development")){
			return this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_DB);
		}else if(aszSiteVersion.equals("staging")){
			return this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_DB);
		}else{
			return this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_DB);	    
		}
	}
	
    /**
	* isUsernameInSystem
	*/
	public int isUsernameInSystem( PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aHeadObj) return -1;
    	String aszTemp=null, aszPassword=null, aszPassVerify=null, aszOrigUsername=null ;
    	boolean bMinOK=true;
    	MethodInit("isUsernameInSystem");
    	aszOrigUsername = aHeadObj.getUSPInternalComment();
    	aszTemp = aHeadObj.getUSPUsername();
    	if(aszTemp.length() < 3){
    		aHeadObj.appendErrorMsg("Username required,  ");
    		bMinOK=false;
    	}
    	if(false == bMinOK) return -1;
    	// does user id exist
    	PersonObj aPersonObj = new PersonObj();
    	pConn = getDBConn();

    	iRetCode = aPersonObj.IsUserIDInSystem(pConn, aHeadObj);
		if(null != pConn) pConn.free();
    	return iRetCode;
    }
    // end-of method isUsernameInSystem()


	public String getRailsSitePrefix( HttpServletRequest httpServletRequest ){
		String aszRailsSitePrefix = this.getSitePropertyValue(ABREAppServerDef.PUBLIC_RAILS_URL_PREFIX);
		if ( httpServletRequest.getHeader("host").contains("chrisvol.org") 	){
			aszRailsSitePrefix = this.getSitePropertyValue(ABREAppServerDef.DEVEL_RAILS_URL_PREFIX);
		}else if ( httpServletRequest.getHeader("host").contains("staging-") 	){
			aszRailsSitePrefix = this.getSitePropertyValue(ABREAppServerDef.STAGING_RAILS_URL_PREFIX);
		}		
		return aszRailsSitePrefix;
	}
	
	
	public abstract class ExternalConnectLogic {
		protected String host;
		protected String callbackBase;
		protected String siteVersion;
		
		abstract protected Class getAPI();
		abstract protected String getAPIKey();
		abstract protected String getAPISecret();
		abstract protected String getScope();
		abstract protected String getCallbackMethod();
		abstract protected String getResourceURL();
		abstract public String getSessionKeyPrefix();
		abstract public String getVerifierKey();
		abstract public String getID(Token accessToken);
		abstract public int isUserInSystem(String id);
		abstract public int loadUser(PersonInfoDTO person);
		abstract public int linkUser(PersonInfoDTO person);
		abstract public String getProvider();
		abstract public String getInternalComment();
		abstract public String getInternalCommentAlreadyConnected();
		abstract public void setAccessToken(PersonInfoDTO person, String accessToken);
		abstract public void setAccessSecret(PersonInfoDTO person, String accessSecret);
		abstract public void setID(PersonInfoDTO person, String id);
		abstract public String getActionMapping();
		abstract protected String getRailsImportAction();
		
		public final String getRailsImportPath(HttpServletRequest request) {
			return IndividualsBRLO.this.getRailsSitePrefix(request) + "profiles~mine~" + getRailsImportAction();
		}
		
		public String getHumanReadableProvider() {
			return getProvider().substring(0, 1).toUpperCase() + getProvider().substring(1);
		}
		
		public final void setAccessToken(PersonInfoDTO person, Token accessToken) {
			setAccessToken(person, accessToken.getToken());
		}
		
		public final void setAccessSecret(PersonInfoDTO person, Token accessToken) {
			setAccessSecret(person, accessToken.getSecret());
		}
		
		protected final String getResource(Token accessToken) {
		   OAuthRequest request = new OAuthRequest(Verb.GET, getResourceURL());
		   getOAuthService().signRequest(accessToken, request);
		   return request.send().getBody();
		}
		
		public ExternalConnectLogic(String host, String callbackBase, String siteVersion) {
			this.host = host;
			this.callbackBase = callbackBase;
			this.siteVersion = siteVersion;
		}
		
		public final OAuthService getOAuthService() {
			return new ServiceBuilder()
		    .provider(getAPI())
		    .apiKey(getAPIKey())
		    .apiSecret(getAPISecret())
		    .scope(getScope())
		    .callback(getCallback())
		    .build();
		}
		
		public final String getCallback() {
			return callbackBase + "register.do?method=" + getCallbackMethod();
		}
		
		public final Token getAccessToken(Token requestToken, String verifier) {
			 return getOAuthService()
			.getAccessToken(requestToken, new Verifier(verifier));
		}
		
		public Token getRequestToken() {
			return getOAuthService().getRequestToken();
		}
		
		public final String getRequestTokenSessionKey() {
			return getSessionKeyPrefix() + "_connect_request_token";
		}
		
		public final String getAccessTokenSessionKey() {
			return getSessionKeyPrefix() + "_connect_access_token";
		}
	}
	
	public ExternalConnectLogic getFacebookConnectLogic(String host, String callback, String siteVersion) {
		return new ExternalConnectLogic(host, callback, siteVersion) {

			@Override
			protected Class getAPI() {
				return FacebookApi.class;
			}

			@Override
			protected String getAPIKey() {
				return this.getIdAndSecret().get("id");
			}

			@Override
			protected String getAPISecret() {
				return this.getIdAndSecret().get("secret");
			}

			@Override
			protected String getScope() {
				return "user_location,user_work_history,email,user_likes,user_education_history,user_birthday";
			}

			@Override
			protected String getCallbackMethod() {
				return "processFacebookConnectClick";
			}

			@Override
			protected String getResourceURL() {
				return "https://graph.facebook.com/me?fields=id";
			}

			@Override
			public String getID(Token accessToken) {
		        String id = null;
		        try {
					id = new org.json.JSONObject(getResource(accessToken)).getString("id");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        return id;
			}
			
			private HashMap<String, String> getIdAndSecret() {
				System.out.println("~~~~~ HOST: " + this.host);
				final Comparator<String> HOST_EQUALS_IGNORE_CASE = new Comparator<String>() {
					@Override
					public int compare(String host, String key) {
						return host.equalsIgnoreCase(key) ? 0 : 1;
					}
				};
				final Comparator<String> HOST_CONTAINS = new Comparator<String>() {
					@Override
					public int compare(String host, String key) {
						return host.contains(key) ? 0 : 1;
					}
				};
				Object[][] configurations = {
					{HOST_EQUALS_IGNORE_CASE, "volengexample", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_EXAMPLE_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_EXAMPLE_SECRETKEY)},
					{HOST_CONTAINS,           "7001", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SANDBOX_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SANDBOX_SECRETKEY)},
					{HOST_EQUALS_IGNORE_CASE, "volengivol", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_SECRETKEY)},
					{HOST_CONTAINS, "ivolunteering.org", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_SECONDARY_SECRETKEY)},
					{HOST_EQUALS_IGNORE_CASE, "volenggospel", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_SECRETKEY)},
					{HOST_EQUALS_IGNORE_CASE, "volenggospelcom", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER1_SECRETKEY)},
					{HOST_CONTAINS,           "hurricanesandy", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER2_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_PARTNER2_SECRETKEY)},
					{HOST_CONTAINS, "churchvolunteering.org", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_SECRETKEY)},
					{HOST_EQUALS_IGNORE_CASE, "volengchurch", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_FAITH_SECRETKEY)},
					{HOST_CONTAINS, "christianvolunteering.org", 
						IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_MAIN_APPID), IndividualsBRLO.this.getSitePropertyValue(ABREAppServerDef.APP_FACEBOOK_CONNECT_MAIN_SECRETKEY)}			
				};
				Object[] config = null;
				for(Object[] c : configurations) {
					if( ((Comparator<String>) c[0]).compare(this.host, (String)c[1]) == 0 ) {
						config = c;
						break;
					}
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", (String)config[2]);
				map.put("secret", (String)config[3]);
				return map;
			}

			@Override
			public String getSessionKeyPrefix() {
				return "facebook";
			}
			
			@Override
			public Token getRequestToken() {
				return null;
			}

			@Override
			public int isUserInSystem(String id) {
				return IndividualsBRLO.this.isFacebookUserInSystem(id);
			}

			@Override
			public String getVerifierKey() {
				return "code";
			}

			@Override
			public String getProvider() {
				return "facebook";
			}

			@Override
			public String getInternalComment() {
				return "linkFacebookAccount";
			}

			@Override
			public void setAccessToken(PersonInfoDTO person, String accessToken) {
				person.setFacebookAccessToken(accessToken);
				
			}

			@Override
			public void setID(PersonInfoDTO person, String id) {
				person.setFacebookUID(id);
				
			}

			@Override
			public void setAccessSecret(PersonInfoDTO person, String accessSecret) {}

			@Override
			public int linkUser(PersonInfoDTO person) {
				return IndividualsBRLO.this.linkFacebookConnectUser(person);
			}

			@Override
			public int loadUser(PersonInfoDTO person) {
				return IndividualsBRLO.this.loadUserByOption( person, PersonInfoDTO.LOADBY_FACEBOOK_UID, this.siteVersion );
			}

			@Override
			public String getInternalCommentAlreadyConnected() {
				return "facebookConnect";
			}

			@Override
			public String getActionMapping() {
				return "facebookconnectcreateaccount";
			}

			@Override
			protected String getRailsImportAction() {
				return "import_from_facebook";
			}
		};
	}
	
	public ExternalConnectLogic getLinkedinConnectLogic(String host, String callback, String siteVersion) {
		return new ExternalConnectLogic(host, callback, siteVersion) {

			@Override
			protected Class getAPI() {
				return LinkedInApi.class;
			}

			@Override
			protected String getAPIKey() {
				return "7kqd3k53br6p";
			}

			@Override
			protected String getAPISecret() {
				return "DoIQiaAPaQ9nKT2X";
			}

			@Override
			protected String getScope() {
				return "r_basicprofile r_emailaddress r_fullprofile r_contactinfo";
			}

			@Override
			protected String getCallbackMethod() {
				return "processLinkedinConnectClick";
			}

			@Override
			protected String getResourceURL() {
				return "http://api.linkedin.com/v1/people/~:(id)";
			}

			@Override
			public String getID(Token accessToken) {
				String id = null;
				try {
					id = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().
					parse(new InputSource(new StringReader(getResource(accessToken)))).
					getElementsByTagName("id").item(0).getFirstChild().getNodeValue();
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	return id;
			}

			@Override
			public String getSessionKeyPrefix() {
				return "linkedin";
			}
			
			@Override
			public int isUserInSystem(String id) {
				return IndividualsBRLO.this.isLinkedInUserInSystem(id, siteVersion);
			}

			@Override
			public String getVerifierKey() {
				return "oauth_verifier";
			}

			@Override
			public String getProvider() {
				return "linkedin";
			}

			@Override
			public String getInternalComment() {
				return "linkLinkedinAccount";
			}

			@Override
			public void setAccessToken(PersonInfoDTO person, String accessToken) {
				person.setLinkedInAccessToken(accessToken);
				
			}

			@Override
			public void setID(PersonInfoDTO person, String id) {
				person.setLinkedInId(id);
			}

			@Override
			public void setAccessSecret(PersonInfoDTO person, String accessSecret) {
				person.setLinkedInAccessSecret(accessSecret);
				
			}

			@Override
			public int linkUser(PersonInfoDTO person) {
				return IndividualsBRLO.this.linkLinkedinConnectUser(person, siteVersion);
			}

			@Override
			public int loadUser(PersonInfoDTO person) {
				return IndividualsBRLO.this.loadUserByOption( person, PersonInfoDTO.LOADBY_LINKEDIN_ID, this.siteVersion );
			}

			@Override
			public String getInternalCommentAlreadyConnected() {
				return "linkedinConnect";
			}

			@Override
			public String getActionMapping() {
				return "linkedinconnectcreateaccount";
			}

			@Override
			protected String getRailsImportAction() {
				return "import_from_linkedin";
			}
		};
	}
	
	public ExternalConnectLogic getConnectLogicByInternalComment(String internalComment, String host, String callback, String railsDB) {
		if(internalComment == null) return null;
		if(internalComment.equals("linkFacebookAccount")) {
			return getFacebookConnectLogic(host, callback, railsDB);
		} 
		else if(internalComment.equals("linkLinkedinAccount")) {
			return getLinkedinConnectLogic(host, callback, railsDB);
		}
		return null;
	}
	
	
	private String aszDomWorldVision = "worldvision.christianvolunteering.org";
	private String aszDomRoundTrip = "roundtrip.christianvolunteering.org";
	private static final String WS_ACTION_CONNECT = "system.connect";
	private static final String WS_ACTION_MAIL = "system.mail";
	private static final String WS_ACTION_GETUSER = "user.get";
	private static final String WS_ACTION_LOGIN = "user.login";
	private static final String WS_ACTION_LOGOUT = "user.logout";
	private static final String WS_ACTION_USER_SAVE = "user.save";
	private static final String WS_ACTION_NODE_SAVE = "node.save";
	private static final String WS_PARAM_SESSID = "sessid";
	private static final String WS_PARAM_USERNAME = "name";
	private static final String WS_PARAM_UID = "uid";
	private static final String FACEBOOK_CONNECT_APPID = "app.facebook.connect.appid";
	private static final String FACEBOOK_CONNECT_SECRETKEY = "app.facebook.connect.secretkey";
	
	private static final String aszOrganizationUser = "organization";
	private static final String aszChurchUser = "church";
	private ApplicationCodesBRLO aCodes = new ApplicationCodesBRLO();
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final String HMAC_SHA256_ALGORITHM = "HMACSHA256";

	private static final int iVolDirectorytid=8864;
	private static final int iVolNoDirectorytid=8865;
	private static final int iPersonalityTypeVid=336;
	private static final int iHurrSandyOrgNID = 494934;
	private static final int iDisasterReliefOrgNID = 511070;

	private static final int iRemoved=1, iAdded=2, iNowGetsEmails=3, iNoLongerGetsEmails=4, iNowIsOppContact=5, iNoLongerIsOppContact=6, 
		iNowEmailAndContact=7, iNoLongerEmailOrContact=8, iNewPrimaryContact=9, iNoLongerPrimaryContact=10,
		iRemovedAdmin=21, iAddedAdmin=22, iNowIsOrgContact=32; 
	private static final int iNowIsOrgContactLegacy = 80, iNoLongerOrgContactLegacy=81, iNowGetsEmailsLegacy=82, iNoLongerGetsEmailsLegacy=83;
}
