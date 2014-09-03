package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Organization Related Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

//Struts MVC objects
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.gargoylesoftware.htmlunit.ObjectInstantiationException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

//data transfer objects
import com.abrecorp.opensource.dataobj.*;
//Business Rules Layer objects
import com.abrecorp.opensource.voleng.brlayer.*;

import com.abrecorp.opensource.servlet.BaseServletABRE;

public class searchOpportunitiesAction extends DispatchAction {

	/**
	* Constructor 
	*/
	public searchOpportunitiesAction() {}

    //=== START Virtual Search ===>
    //=== START Virtual Search ===>
    //=== START Virtual Search ===>

    public ActionForward showSearchURLTranslator(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	DynaActionForm ofrm = (DynaActionForm) actionForm;
    	SearchURLTranslatorDTO aHeadObj = new SearchURLTranslatorDTO();
    	httpServletRequest.setAttribute("searchURLTranslator", aHeadObj);
    	SearchURLTranslatorActionHelper helper = new SearchURLTranslatorActionHelper();
    	helper.setFormData(ofrm, aHeadObj);
    	return actionMapping.findForward("searchurltranslator");
    }
    
    public ActionForward processSearchURLTranslator(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	DynaActionForm ofrm = (DynaActionForm) actionForm;
    	SearchURLTranslatorDTO aHeadObj = new SearchURLTranslatorDTO();
    	httpServletRequest.setAttribute("searchURLTranslator", aHeadObj);
    	SearchURLTranslatorActionHelper helper = new SearchURLTranslatorActionHelper();
    	helper.getFormData(ofrm, aHeadObj);
    	aHeadObj.translate();
    	helper.setFormData(ofrm, aHeadObj);
    	return actionMapping.findForward("searchurltranslator");
    }
	
	/*
	* show full search page
	*/
    public ActionForward showSolrSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "search" );
    }
    // end-of method showFullSearch()


	
	/*
	* show showApplicationSearch search page
	*/
    public ActionForward showApplicationSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "applicsearch" );
    }
    // end-of method showFullSearch()



	/*
	* show full search page
	*/
    public ActionForward showFullSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showfullsearch" );
    }
    // end-of method showFullSearch()


	/*
	* show search page
	*/
    public ActionForward showSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showsearch" );
    }
    // end-of method showSearch()


	/*
	* show showCVInternSearch page
	*/
    public ActionForward showCVInternSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
       boolean b_cvInternSiteSearch = false;   
		String aszPortalSearch = "", aszPortalSearchOrg = "", aszPortalSearchOpps = "";
   	   if(httpServletRequest.getAttribute("organization_name") != null){
   		   if(httpServletRequest.getAttribute("organization_name").toString().length()>0){
	    		   if(httpServletRequest.getAttribute("organization_name").equals("City Vision")){
					   aszPortalSearchOrg="screened:\"City Vision\"";
					   aszPortalSearchOpps="intern_type:\"City Vision Internship\"";
	    		   }
   		   }
   	   }
   	   if(aszPortal.equals("cityvision")){
			   aszPortalSearchOrg="screened:\"City Vision\"";
			   aszPortalSearchOpps="intern_type:\"City Vision Internship\"";
   	   }

   	   // b_cvInternSiteSearch is true if a parameter is submitted.  then it has to test that the user has correct access rights (org is cvintern_site_approved="City Vision")
	   // need to now test access rights for the given user
        getLoggedInStatus(httpServletRequest, httpServletResponse);
		if(aszLoggedInStatus.equals("showlogin")){
			return actionMapping.findForward( "showlogin" );
		}else if(aszLoggedInStatus.contains("processCookieLogin")){
	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	    }
	    int iRetCode=-1;
	    allocatedOrgBRLO( httpServletRequest );
	    allocatedApplicCodesBRLO( httpServletRequest );
	    if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
	    	// check to see if the current user has access rights on any cvintern_approved orgs
	    	ArrayList aCVCSitesList = new ArrayList( 2);
	    	m_AppCodesBRLOObj.getCVInternOrgSitesList(aCVCSitesList);
	    	// org_nids is a_cvinternOrgsList.
	    					
	    	for(int index=0; index < aCVCSitesList.size(); index++){
	    		AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVCSitesList.get(index);
	    		if(null == aAppCode) continue;
	    		int iOptRqCode = aAppCode.getAPCIdSubType();
	    		int iOrgNIDTmp = aAppCode.getAPCIdSubType();
	    		// try to load the iOrgNIDTmp by admin or contact and if it succeeds, then it can show the search results.
	    	    OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	    	    aOrgInfoObj.setORGNID( iOrgNIDTmp );
	    	    aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	    	    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	    	    if(iRetCode == -111){
	    	       	 // if it failed, try to load via contact
	    	       	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	    	    }
	    	    if(iRetCode==0){
	    	       	 b_cvInternSiteSearch = true; 
	    	       	 break;
	    	    }
   		   }
   	   }
   	   if(b_cvInternSiteSearch==true)
   		   return actionMapping.findForward( "applicationsearch" );
    	return actionMapping.findForward( "showsearch" );
    }
    // end-of method showCVInternSearch()

	/*
	* show search page
	*/
    public ActionForward showSearchPortal(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showsearchportal" );
    }
    // end-of method showSearch()

	/*
	* show include search page (used for portals iframe, etc)
	*/
    public ActionForward showIncludeSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showincludesearch" );
    }
    // end-of method showSearch()
    
    /*
    * show virtual opportunity search 
    */
    public ActionForward showVirSearch1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showvirtsearch" );
    }
    // end-of method showVirSearch1()


    //=== END Virtual Search ===>
    //=== END Virtual Search ===>
    //=== END Virtual Search ===>

	//=== START Search For organizations ===>
    //=== START Search For organizations ===>
    //=== START Search For organizations ===>

	/*
    * show organization search 
    */
    public ActionForward showOrgSearch1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
        SearchParms aSParm = new SearchParms();
        httpServletRequest.setAttribute( "sparm", aSParm );
    	return actionMapping.findForward( "showorgsearch" );
    }
    // end-of method showOrgSearch1()


    //=== END Search For organizations ===>
    //=== END Search For organizations ===>
    //=== END Search For organizations ===>

	//=== START Search For Volunteer Opportunities ===>
    //=== START Search For Volunteer Opportunities ===>
    //=== START Search For Volunteer Opportunities ===>

	/*
    * show volunteer opportunity search 
    * oppsrch.do?method=showOppSearch1
    */
    public ActionForward showOppSearch1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	return actionMapping.findForward( "showfullsearch" );
    }
    // end-of method showOppSearch1()

	/*
    * process top most opportunity search 
    */
    public ActionForward processOppSearchTop(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
//     	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse );
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
    	// clear session email token
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null != aSessDat){
        	aSessDat.setOrgIdNum( 0 );
        	aSessDat.setTokenKey( null );
    	}
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
        SearchParms aSParm = new SearchParms();
    	int iRetCode=0;
    	aSParm.setSearchMethod("processOppSearchTop");
       	if(httpServletRequest.getQueryString() != null){
       		aSParm.setSearchQueryString(httpServletRequest.getQueryString());
       	}
       	aSParm.setSubdomain(httpServletRequest.getHeader("host"));
       	aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
       	aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
        m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
		             	
		             	aSParm.setPortalNID( aszPortalNID);
		    	        if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);


        if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
        	aSParm.setPostalCode("");
        }
        aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_TOPMOST  );
        aSParm.setmaxSearchResultRows(250);
        allocatedOppSrchBRLO( httpServletRequest );
        ArrayList aList = new ArrayList(2);
        httpServletRequest.setAttribute( "alist", aList );
        iRetCode = m_SearchBRLOObj.searchForOpportunitiesInDB( aList, aSParm);
      	// set bean up so that sort can be done from results page, too
        httpServletRequest.setAttribute("searchinfo", aSParm);
        if(0 != iRetCode){
        	return actionMapping.findForward( "404" );
        }
    	return actionMapping.findForward( "oppsearchresult" );
    }
    // end-of method processOppSearchTop()

    /*
    * process volunteer advance opportunity search 
    */
    public ActionForward processOppSearch1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        return processOppSearchAll(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    // end-of method processOppSearch1()

    
 	/*
      * show volunteer opportunity search 
      * oppsrch.do?method=showOppSearch1
      */
     public ActionForward showOppSearchAll(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
        	//getPortalInfo( httpServletRequest, httpServletResponse);
    	getPortalInfo( httpServletRequest, httpServletResponse );
      	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
      	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
      	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showfullsearch" );
     }
 	/*
 	 * TEMPORARY!!!!!!!!!!!
      * show volunteer opportunity search 
      * oppsrch.do?method=showOppSearch1
      * 
      * cover grounds for old calls - DELETE this once the links have been fixed
      */
     public ActionForward showOppSearchTemp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
        	//getPortalInfo( httpServletRequest, httpServletResponse);
    	getPortalInfo( httpServletRequest, httpServletResponse );
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
            }
        }
        if(session.getAttribute(aszPortal+"_nid") != null ){
            if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
            	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            }
        }
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showfullsearch" );
     }
     // end-of method showOppSearchAll()
          
     
 	  /*
      * show portal opportunity search for them to select favorites from 
      * oppsrch.do?method=showOppSearch1
      */
     public ActionForward showPortalOpps(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
//    	 HttpSession session=httpServletRequest.getSession();
        	//getPortalInfo( httpServletRequest, httpServletResponse);
    	getPortalInfo( httpServletRequest, httpServletResponse );
      	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
      	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
      	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
      		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    			  return actionMapping.findForward( "showlogin" );
    		  }else{
    			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		  }
        	}
        }
    	 
    	 SearchParms aSParm = new SearchParms();
    	 httpServletRequest.setAttribute( "sparm", aSParm );
    	 int iRetCode=0;

    	 DynaActionForm oFrm = (DynaActionForm)actionForm;
    	 
    	 AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	 if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
         		return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	}
    	 }
    	 aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
    	 
         allocatedOrgBRLO( httpServletRequest );
         OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
         aOrgInfoObj.setORGNID( aszPortalNID );

         int iNatlAssocNID = 0;
         if(bNatlAssoc==true){
       	  //m_BaseHelp.convertToInt( aszPortalNID )
       	  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
         }
         // if natl association, then the OrgNid should be that of the correct national association
         boolean b_AssocManager = false;
         if(bNatlAssoc==true){
        	 iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
             // if natl association, then the OrgNid should be that of the correct national association
             aOrgInfoObj.setORGNID( iNatlAssocNID );
             int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
             for(int i=0; i<a_iNatlAssocNIDs.length; i++){
            	 if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
             }
         }

    	 aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership/ adminsitrative rights of the said organization
         //*** need to fix permissions here for Managers of Natl Assoc to work in those cases
    	 if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
       	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//      	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
    	 // will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );
         }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }
         /*
         if(iRetCode != 0){// then this user doesn't have administrative access over this org/church
 	    	return actionMapping.findForward( "showfullsearch" );
         }
         */
         httpServletRequest.setAttribute("org", aOrgInfoObj);
    	 
         m_BaseHelp.loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
   	 return actionMapping.findForward( "portalopps" );
     }
     
 	  /*
      * show volunteer opportunity search 
      * oppsrch.do?method=showOppSearch1
      */
     public ActionForward showMyMinistryOpps(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
//       	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
     	HttpSession session=httpServletRequest.getSession();
      	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
      	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
      	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
        DynaActionForm oFrm = (DynaActionForm)actionForm;
        String showhome = "false";
        showhome=m_BaseHelp.getFormData( oFrm, "showhome" );
         	
        AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
           	//aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
           		return actionMapping.findForward( "personalityoppredirect" );
           	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           	}
        }
        aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aCurrentUserObj) {
           	//aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "personalityoppredirect" );
           	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           	}
        }
    	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			 /*
     			 if(null != aSessDat){
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
   			 }
   		     httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
   			 return actionMapping.findForward( "createaccount2" );
   			 */
   		 }else if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	//aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                	return actionMapping.findForward( "personalityoppredirect" );
            	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
           }
         	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
         	SearchParms aSParm = new SearchParms();
         	httpServletRequest.setAttribute( "sparm", aSParm );
         
         	if(showhome!=null){
            	if(showhome.equalsIgnoreCase("true") && (aCurrentUserObj.getUSPPersonality().length()<1 && aCurrentUserObj.getUSPPersonalityTID()<1) ){
                	return actionMapping.findForward( "home" );
            	}
            }
     	return actionMapping.findForward( "myministryopps" );
     }

    /* 
     * process volunteer advance opportunity search 
     */
     public ActionForward processOppSearchAll(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
//     	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
     	getPortalInfo( httpServletRequest, httpServletResponse);
     	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
     	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
     	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
     	if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
     	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
     	boolean bNatlAssoc = false;
     	if(aszPortalRequestType.equals("natlassoc")){
     		bNatlAssoc=true;
     	}
     	
     	if(aszPortal.equals("cityvision")){
     		httpServletRequest.setAttribute("organization_name", "City Vision");
     		return processSolrSearch(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	}
	
        String aszRequestType = "";
        if(!(httpServletRequest.getParameter("requesttype")==null)){
            aszRequestType = httpServletRequest.getParameter("requesttype");
        }
		aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
     	// clear session email token
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	if(null != aSessDat){
         	aSessDat.setOrgIdNum( 0 );
         	aSessDat.setTokenKey( null );
     	}
		if(aszRequestType.equalsIgnoreCase("myResultsAdminSelect")){ 
			// make sure current user is logged in; else call cookielogin
			 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
				  if(null != aSessDat){
					  aSessDat.setOrgNID( aszPortalNID );
					  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
				  }
				  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
				  return actionMapping.findForward( "createaccount2" );
			 }
	        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
	        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	            	return actionMapping.findForward( "showlogin" );
	        	}else{
	               return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}
	        }
		}
		

	       int iNatlAssocNID = 0;
	       if(bNatlAssoc==true){
	     	  //m_BaseHelp.convertToInt( aszPortalNID )
	     	  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
	       }
		// if natl association, then the OrgNid should be that of the correct national association
		boolean b_AssocManager = false;
		allocatedOrgBRLO( httpServletRequest );
		OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
		if(bNatlAssoc==true){
			iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);
			// if natl association, then the OrgNid should be that of the correct national association
			aOrgInfoObj.setORGNID( iNatlAssocNID );
			int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
			for(int i=0; i<a_iNatlAssocNIDs.length; i++){
				if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
			}
		}

	
	
       int iPortalNID=0;
       if(aszPortalNID.length()>0){
    	   try{
    		   iPortalNID = Integer.parseInt(aszPortalNID);
    	   }catch(Exception e){
    	   }
       }

       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
       	}
       }
       
       boolean feeds=true;
       boolean b_pasteForm = false;
       String aszPasteForm = "", aszOrgNID="";
       int iOrgNID=0;
       if(session.getAttribute("pasteform")!=null){
           aszPasteForm = session.getAttribute("pasteform").toString();
           if(session.getAttribute("pasteform").toString().equals("true")){
          	 b_pasteForm=true;
           }
           session.removeAttribute("pasteform");
       }
       boolean b_externalListing = false;
       if(session.getAttribute("externallistings")!=null){
           aszPasteForm = session.getAttribute("externallistings").toString();
           if(session.getAttribute("externallistings").toString().equals("true")){
        	   b_externalListing=true;
           }
           session.removeAttribute("externallistings");
       }
       if(session.getAttribute("orgnid")!=null){
    	   aszOrgNID = session.getAttribute("orgnid").toString();
           if(aszOrgNID.length()>0){
        	   try{
        		   iOrgNID = Integer.parseInt(aszOrgNID);
        	   }catch(Exception e){
        	   }
           }
           session.removeAttribute("orgnid");
       }
   
     	int iRetCode=0;

      	DynaActionForm oFrm = (DynaActionForm)actionForm;
         SearchParms aSParm = new SearchParms();
     	aSParm.setSearchMethod("processOppSearchAll");
       	if(httpServletRequest.getQueryString() != null){
       		aSParm.setSearchQueryString(httpServletRequest.getQueryString());
       	}
       	aSParm.setSubdomain(httpServletRequest.getHeader("host"));
       	aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
       	aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
         m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
         if(aSParm.getSearchForm().equalsIgnoreCase("advancedsearch")){
        	 if(! (aSParm.getSource().equalsIgnoreCase("feeds") || aSParm.getSource().equalsIgnoreCase("searchfeeds") )){
        		 aSParm.setSource("nofeeds");
        	 }
         }
         if(aSParm.getSource().equalsIgnoreCase("nofeeds")){
        	 feeds=false;
         }
         if(iOrgNID>0){
        	 aSParm.setNID(iOrgNID);
         }
			             	
         aSParm.setPortalName(aszPortal);
	        if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);
	        if(aSParm.getPortalNID()==0 && iPortalNID>0){
	        	aSParm.setPortalNID(iPortalNID);
	        }

         if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
         	aSParm.setPostalCode("");
         }
         aSParm.setSearchRequestType( aszRequestType );
         if(	aszRequestType.equalsIgnoreCase("myResultsTab") 		|| 
        		aszRequestType.equalsIgnoreCase("myResultsLatestTab") 		|| 
         		aszRequestType.equalsIgnoreCase("myResultsAdminSelect")
         ){
             if(!(httpServletRequest.getParameter("serviceareas")==null)){
            	 aSParm.setServiceAreasTIDs( httpServletRequest.getParameter("serviceareas"));
             }
             if(!(httpServletRequest.getParameter("skilltypes")==null)){
                 aSParm.setSkillsTIDs( httpServletRequest.getParameter("skilltypes"));
             }
         }
         // if administrator, don't limit to already favorited, b/c you're actually in the use case of selecting favorites
         if(	aszRequestType.equalsIgnoreCase("myResultsAdminSelect")   ){
          	aSParm.setPortalAdminUID(aszPortalUID);
          	aSParm.setPortalNID(0);
         	aSParm.setPortalUID(0);
         	aSParm.setPortal(777); // administrator through portal
         }

         // m_BaseHelp.getSearchRequestForm(oFrm,aSParm);
         httpServletRequest.setAttribute( "sparm", aSParm );
         allocatedOppSrchBRLO( httpServletRequest );
         ArrayList aList = new ArrayList(2);
         httpServletRequest.setAttribute( "alist", aList );
                  
         // get the kind of search/sort; ie search by...
         String aszSort="";
         if (aSParm.getSearchKey().length() > 2){
       	  aszSort=aSParm.getSearchKey();
         } else if (aSParm.getSearchKey2().length() > 2){
       	  aszSort=aSParm.getSearchKey2();
         } else if (aSParm.getSearchKey3().length() > 2){
       	  aszSort=aSParm.getSearchKey3();
         }

         if(	aszRequestType.equalsIgnoreCase("myResultsAdminSelect")    ){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES  );
         }
         else
         
         if (aszSort.equalsIgnoreCase("organization")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_ORGNAME  );
         }else if (aszSort.equalsIgnoreCase("postal")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POSTAL  );
         }else if (aszSort.equalsIgnoreCase("type")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_TYPE  );
         }else if (aszSort.equalsIgnoreCase("city")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_CITY  );
         }else if (aszSort.equalsIgnoreCase("state")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STATE  );
         }else if (aszSort.equalsIgnoreCase("prov")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_PROV  );
         }else if (aszSort.equalsIgnoreCase("country")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_COUNTRY  );
         }else if (aszSort.equalsIgnoreCase("denomaffil")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DENOMAFFIL  );
         }else if (aszSort.equalsIgnoreCase("affil")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_AFFIL1  );
         }else if (aszSort.equalsIgnoreCase("opportunity")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNAME  );
         }else if (aszSort.equalsIgnoreCase("oppnumvol")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNUMVOL  );
         }else if (aszSort.equalsIgnoreCase("stmdur")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STMDURATION  );
         }else if (aszSort.equalsIgnoreCase("stmcost")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STMCOST  );
         }else if (aszSort.equalsIgnoreCase("updatedt")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_UPDATEDT  );             
         }else if (aszSort.equalsIgnoreCase("distance")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DISTANCE  );             
         }else if (aszSort.equalsIgnoreCase("numvolorg")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_NUM_VOL_ORG  );             
         }else if (aszSort.equalsIgnoreCase("popularity")){
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY  );             
         }else{
             aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY  );        	 
         }
         int iMaxSearchResultsRows=10000;
         String aszSearchResultsNumber = aSParm.getPreviewSearch();
         int i_searchResultsNumber=10000;
         try{
             i_searchResultsNumber = Integer.parseInt(aszSearchResultsNumber);
         }catch(Exception e){
         }
         if(i_searchResultsNumber > 0 && i_searchResultsNumber < 101){
        	 iMaxSearchResultsRows=i_searchResultsNumber;
        	 aSParm.setPreviewSearchShortened("true");
         }
         aSParm.setmaxSearchResultRows(iMaxSearchResultsRows);
         
         
         // take care of old Region links...
         if(aSParm.getRegion().equalsIgnoreCase("Asia")){
        	 aSParm.setRegionTID(4947);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Asia Pacific and Oceania")){
        	 aSParm.setRegionTID(4949);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Canada")){
        	 aSParm.setRegionTID(8418);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Caribbean")){
        	 aSParm.setRegionTID(4944);
        	 aSParm.setRegionTID(4945);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Europe")){
        	 aSParm.setRegionTID(4946);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Latin America")){
        	 aSParm.setRegionTID(4944);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("Middle East")){
        	 aSParm.setRegionTID(4950);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("USA Central")){
        	 aSParm.setRegionTID(8418);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("USA East")){
        	 aSParm.setRegionTID(8418);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("USA South")){
        	 aSParm.setRegionTID(8418);
        	 aSParm.setRegion("");
         }else if(aSParm.getRegion().equalsIgnoreCase("USA West")){
        	 aSParm.setRegionTID(8418);
        	 aSParm.setRegion("");
         }
         
         int iSetLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 0);
         int iSetNOTLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 1);
         
         
         allocatedIndBRLO( httpServletRequest );
         AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
         aHeadObj.setPortal(aszPortal);
         if(! aszRequestType.startsWith("myResults")){
        	 b_AssocManager=false; // if they're not trying to pull it up through managing, then don't narrow it
         }
         if(bNatlAssoc==true && b_AssocManager==false){ // ONLY narrow if NOT MANAGING
       	  // lookup the tid of the orgaffil associated with the given National Association
             iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
             aSParm.setPortalUID(aHeadObj.getPortalUID());
             if(aHeadObj.getPortalOrgAffilTID()>0){
            	 int i =aHeadObj.getPortalOrgAffilTID(); 
            	 aSParm.setOrgAffil1TID(aHeadObj.getPortalOrgAffilTID());
             }
             aSParm.setAssocOnly(true);
             aSParm.setRequestType("natlassoc");
//             aSParm.setSearchType( OrganizationInfoDTO.LOADBY_NATL_ASSOC  );        	 
             
         }
        
                  
		int index=0, iRetCode2=0, iRetCode3=0;
		iRetCode=-2;
        
        // set bean up so that sort can be done from results page, too
        httpServletRequest.setAttribute("searchinfo", aSParm);

        if(! aszRequestType.equalsIgnoreCase("myResultsLatestTab")){
	       	 if(aSParm.getPortalNID()>0						&& bNatlAssoc==false){// added condition about Natl Assoc later 2012-01-25
	       		 // special case for meet the need to show all results (though not those from our feeds db)
	       		 if(aSParm.getPortalNID()==iMeetTheNeedNID){
		    		 iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aHeadObj);
	       		 }
	    		 // may want to load the list multiple times for portals - get portal opps first, then get portal's other favorites
	    		 iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aSParm.PORTAL_OWN_LIST, false, aHeadObj);
	    		 iRetCode2 = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aSParm.PORTAL_OTHERFAV_LIST, false, aHeadObj);
	    		 iRetCode3 = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aSParm.PORTAL_OTHERFAV_LIST, false, true, aHeadObj);//????? don't know if this is necessary
	    		 if(iRetCode==0 || iRetCode2==0 || iRetCode3==0){
	    			 iRetCode=0;
	    		 }
//	       	 }else if(aSParm.getPortalNID()>0		&& bNatlAssoc==true){// added condition about Natl Assoc later 2012-01-25
//	    		 // may want to load the list multiple times for portals - get portal opps first, then get portal's other favorites
//	       		 iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, OrganizationInfoDTO.LOADBY_NATL_ASSOC, false, false, aHeadObj);
	    	 }else{
	    		 iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aHeadObj);
	    		 String aszReqCode = aSParm.getReqCode().toUpperCase();
	    		 if (! aszReqCode.equalsIgnoreCase("NO") && (b_AssocManager==false)){
	    			 iRetCode2 = m_SearchBRLOObj.advanceSearchOpportunities( aList, aSParm, aSParm.getSearchType(), false, feeds, aHeadObj);
	    		 }
	    		 if(iRetCode==0 || iRetCode2==0){
	    			 iRetCode=0;
	    		 }
	    	 }

	         // first test to see if this should be forwarding the user to the my_results tabbed page results rather than the main results page
	         if(!(aszRequestType==null)){
	        	if(	aszRequestType.equalsIgnoreCase("myResultsTab") 		||               		
	        		aszRequestType.equalsIgnoreCase("myResultsAdminSelect")              
	        	){
	        		if(0 != iRetCode){
	        			return actionMapping.findForward( "myresultstabnone" );
	        		}
	        		if(	aszRequestType.equalsIgnoreCase("myResultsAdminSelect")    ){
	        			aOrgInfoObj.setORGNID( aszOrgNID );
	        			
	        	        ArrayList aAssocOrgList = new ArrayList();
	        	        httpServletRequest.setAttribute( "assocorglist", aAssocOrgList );
	         			if(b_AssocManager==true){
	        				aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() );
	        				aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	        				// will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
	        				iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );
	        	            iRetCode = m_OrgBRLOObj.getOrgListForAssociation( aAssocOrgList, iNatlAssocNID, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC); 

	        				int[] i_aOppNIDs = new int[iMaxSearchResultsRows];
	        				int count=0;
	        	            // iterate through the aAssocOrgList for org nids, and add to the OppList for org for each of those orgs
	           	    	    Iterator<OrganizationInfoDTO> itr_aAssocOrgList = aAssocOrgList.iterator();
	           	    	    while(itr_aAssocOrgList.hasNext()){
	           	  	            OrganizationInfoDTO orgElement_aAssocOrgList=new OrganizationInfoDTO();
	           	    	    	orgElement_aAssocOrgList = itr_aAssocOrgList.next();
	           	    	    	int iAssocOrgNID = orgElement_aAssocOrgList.getORGNID();
	           	    	    	if(iAssocOrgNID>0){
		                	        ArrayList aAssocOrgOppList = new ArrayList();
		            				iRetCode = m_OrgBRLOObj.getOppListForOrg( aAssocOrgOppList, iAssocOrgNID, OrganizationInfoDTO.LOADBY_ORGNID_MANAGE);
		            				Iterator<OrgOpportunityInfoDTO> itr_aAssocOrgOppList = aAssocOrgOppList.iterator();
		               	    	    while(itr_aAssocOrgOppList.hasNext()){
		               	    	    	OrgOpportunityInfoDTO orgElement_aAssocOrgOppList=new OrgOpportunityInfoDTO();
		               	  	            orgElement_aAssocOrgOppList = itr_aAssocOrgOppList.next();
		               	    	    	int iAssocOrgOppNID = orgElement_aAssocOrgOppList.getOPPNID();
		               	    	    	if(iAssocOrgOppNID>0){
			               	  	            i_aOppNIDs[count] = orgElement_aAssocOrgOppList.getOPPNID();
			               	  	            count++;
		               	    	    	}
		               	    	    }
	           	    	    	}
	           	    	    }
	           	    	    int[] i_aOppFavoritedNIDs = new int[count];
	           	    	    for(int i=0; i<count; i++){
	           	    	    	try{
	           	    	    		i_aOppFavoritedNIDs[i] = i_aOppNIDs[i];
	           	    	    	}catch(ArrayIndexOutOfBoundsException err){}
	           	    	    }
	           	    	    aOrgInfoObj.setORGFavoritedOPPNIDsArray(i_aOppFavoritedNIDs);
	        			}else{
	        				aOrgInfoObj.setORGUID( aszPortalUID );
	        				iRetCode = m_OrgBRLOObj.loadFavoriteOpps( aOrgInfoObj, false );// from normal db
	        				iRetCode2 = m_OrgBRLOObj.loadFavoriteOpps( aOrgInfoObj, true );// from feeds
	        			}
	        			httpServletRequest.setAttribute("orgportal", aOrgInfoObj);
	        			return actionMapping.findForward( "portalresultstab" );
	        		}else{
	        			return actionMapping.findForward( "myresultstab" );
	        		}
	        	}else if(aszRequestType.equalsIgnoreCase("myResultsLatestTab")){
		             if(0 != iRetCode){
			             return actionMapping.findForward( "myresultstabnone" );
			         }
			         return actionMapping.findForward( "myresultslatesttab" );
	             }

	         }
	         
        }else{
        	if ( !(aSParm.getUSPLookingForArray()==null && aSParm.getLookingForTIDs()==null) ){
    			if ( aSParm.getUSPLookingForArray().length > 0 ||  aSParm.getLookingForTIDs().length() > 0 ){

    				String tempLookingFor = aSParm.getLookingForTIDs();
    					if(tempLookingFor.contains(","+iLocalVolTID+",") || 
    							tempLookingFor.equals(""+iLocalVolTID+"") || 
    							tempLookingFor.startsWith(""+iLocalVolTID+",") || 
    							tempLookingFor.endsWith(","+iLocalVolTID+"")){//aSParm.LATEST_LOCAL){
    						aSParm.setOPPPositionTypeTID(iLocal);
    			            ArrayList aListLatestLocal = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestlocal", aListLatestLocal );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestLocal, aSParm, aSParm.LATEST_LOCAL, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}
    					if(tempLookingFor.contains(","+iVolVirtTID+",") || 
    							tempLookingFor.equals(""+iVolVirtTID+"") || 
    							tempLookingFor.startsWith(""+iVolVirtTID+",") || 
    							tempLookingFor.endsWith(","+iVolVirtTID+"")){//aSParm.LATEST_VIRTUAL){
    						aSParm.setOPPPositionTypeTID(iVirtual);
    			            ArrayList aListLatestVirtual = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestvirtual", aListLatestVirtual );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestVirtual, aSParm, aSParm.LATEST_VIRTUAL, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}
    					if(tempLookingFor.contains(","+iGroupFamilyTID+",") || 
    							tempLookingFor.equals(""+iGroupFamilyTID+"") || 
    							tempLookingFor.startsWith(""+iGroupFamilyTID+",") || 
    							tempLookingFor.endsWith(","+iGroupFamilyTID+"")){//aSParm.LATEST_GRP_FAMILY){
    						aSParm.setGreatForGroupTID(iGroup);
    						aSParm.setGreatForFamilyTID(iFamily);
    			            ArrayList aListLatestGrpFam = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestgrpfam", aListLatestGrpFam );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestGrpFam, aSParm, aSParm.LATEST_GRP_FAMILY, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setGreatForGroupTID(0);
    						aSParm.setGreatForFamilyTID(0);
    						aSParm.setOPPPositionTypeTID(0);
    					}
    					if(tempLookingFor.contains(","+1+",") || 
    							tempLookingFor.equals(""+1+"") || 
    							tempLookingFor.startsWith(""+1+",") || 
    							tempLookingFor.endsWith(","+1+"")){//aSParm.LATEST_STM){???????????????????????????????????????
    						aSParm.setOPPPositionTypeTID(iShortTerm);
    			            ArrayList aListLatestSTM = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlateststm", aListLatestSTM );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestSTM, aSParm, aSParm.LATEST_STM, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}

    					if(tempLookingFor.contains(","+iSumIntrnTID+",") || 
    							tempLookingFor.equals(""+iSumIntrnTID+"") || 
    							tempLookingFor.startsWith(""+iSumIntrnTID+",") || 
    							tempLookingFor.endsWith(","+iSumIntrnTID+"")){//aSParm.LATEST_SUM_INTRN){
    						aSParm.setOPPPositionTypeTID(iShortTerm);
    			            ArrayList aListLatestSumIntrn = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestsumintrn", aListLatestSumIntrn );
    			             //iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestSumIntrn, aSParm, aSParm.LATEST_SUM_INTRN);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}
    					if(tempLookingFor.contains(","+iIntrnTID+",") || 
    							tempLookingFor.equals(""+iIntrnTID+"") || 
    							tempLookingFor.startsWith(""+iIntrnTID+",") || 
    							tempLookingFor.endsWith(","+iIntrnTID+"")){//aSParm.LATEST_INTRN){
    						aSParm.setOPPPositionTypeTID(iShortTerm);
    			            ArrayList aListLatestIntrn = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestintrn", aListLatestIntrn );
    			             //iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestIntrn, aSParm, aSParm.LATEST_INTRN);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}
    					
    					if(tempLookingFor.contains(","+iSumIntrnTID+",") || 
    							tempLookingFor.equals(""+iSumIntrnTID+"") || 
    							tempLookingFor.startsWith(""+iSumIntrnTID+",") || 
    							tempLookingFor.endsWith(","+iSumIntrnTID+"") || 
    							tempLookingFor.contains(","+iIntrnTID+",") || 
    	    					tempLookingFor.equals(""+iIntrnTID+"") || 
    	    					tempLookingFor.startsWith(""+iIntrnTID+",") || 
    	    					tempLookingFor.endsWith(","+iIntrnTID+"")){//aSParm.LATEST_INTRN){
    						aSParm.setOPPPositionTypeTID(iShortTerm);
    			            ArrayList aListLatestSTM = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlateststm", aListLatestSTM );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestSTM, aSParm, aSParm.LATEST_STM, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPPositionTypeTID(0);
    					}
    					if(tempLookingFor.contains(","+1+",") || 
    							tempLookingFor.equals(""+1+"") || 
    							tempLookingFor.startsWith(""+1+",") || 
    							tempLookingFor.endsWith(","+1+"")){//aSParm.LATEST_WORK_STUDY){???????????????????????
    						aSParm.setOPPWorkStudyTID(iWorkStudy);
    			            ArrayList aListLatestWorkStudy = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestworkstudy", aListLatestWorkStudy );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestWorkStudy, aSParm, aSParm.LATEST_WORK_STUDY, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
     						aSParm.setOPPWorkStudyTID(0);
    					}
    					if(tempLookingFor.contains(","+iServiceAreaBoardTID+",") || 
    							tempLookingFor.equals(""+iServiceAreaBoardTID+"") || 
    							tempLookingFor.startsWith(""+iServiceAreaBoardTID+",") || 
    							tempLookingFor.endsWith(","+iServiceAreaBoardTID+"")){//aSParm.LATEST_BOARD){
    						aSParm.setServiceAreasTIDs(""+iOppBoard);
    						aSParm.setSkillsTIDs(null); //??? for Board members, should it have their skills, or no?
    			            ArrayList aListLatestBoard = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestboard", aListLatestBoard );
    			             iRetCode = m_SearchBRLOObj.advanceSearchOpportunities( aListLatestBoard, aSParm, aSParm.LATEST_BOARD, feeds, aHeadObj);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
    					}
    					if(tempLookingFor.contains(","+iLocalOrgsTID+",") || 
    							tempLookingFor.equals(""+iLocalOrgsTID+"") || 
    							tempLookingFor.startsWith(""+iLocalOrgsTID+",") || 
    							tempLookingFor.endsWith(","+iLocalOrgsTID+"")){//aSParm.LATEST_LOCAL){
    			            ArrayList aListLatestLocalOrgs = new ArrayList(2);
    			            httpServletRequest.setAttribute( "alistlatestlocalorgs", aListLatestLocalOrgs );
    			    		aSParm.setSearchLatest(true);
    			             iRetCode = m_SearchBRLOObj.advanceSearchOrganizations( aListLatestLocalOrgs, aSParm, aHeadObj, feeds);//, aSParm.LATEST_LOCAL_ORGS);
    			             if(iRetCode==0 || iRetCode2==0){
        			             iRetCode2= 0;
    			            	 iRetCode=0;
    			             }
    					}
    					if(tempLookingFor.contains(","+1+",") || 
    							tempLookingFor.equals(""+1+"") || 
    							tempLookingFor.startsWith(""+1+",") || 
    							tempLookingFor.endsWith(","+1+"")){//aSParm.LATEST_TRAINING){???????????????????????
    						//aSParm.setOPPPositionTypeTID(iLocal);
	   			             if(iRetCode==0 || iRetCode2==0){
	    			             iRetCode2= 0;
				            	 iRetCode=0;
				             }
    					}
    			}
    		}		
            
         }

         // first test to see if this should be forwarding the user to the my_results tabbed page results rather than the main results page
         if(!(aszRequestType==null)){
        	if(	!(aszRequestType.equalsIgnoreCase("myResultsTab") 		||               		
        		aszRequestType.equalsIgnoreCase("myResultsAdminSelect") 
        		) && aszRequestType.equalsIgnoreCase("myResultsLatestTab")){
	             if(0 != iRetCode){
		             return actionMapping.findForward( "myresultstabnone" );
		         }
		         return actionMapping.findForward( "myresultslatesttab" );
             }
         }
         
         if(0 != iRetCode){
        	 if(b_pasteForm==true){
             	return actionMapping.findForward( "oppsearchnoresultpaste" );
        	 }
         	return actionMapping.findForward( "oppsearchnoresult" );
         }
    	 if(b_pasteForm==true){
             return actionMapping.findForward( "oppsearchresultpaste" );
     	 }
    	 if(b_externalListing==true){
             return actionMapping.findForward( "externallistings" );
     	 }
         return actionMapping.findForward( "oppsearchresult" );
     }
     // end-of method processOppSearchAll()
        
     /* 
      * process volunteer advance opportunity search - outside pages will use this
      * to include our results table
      */
      public ActionForward processOppSearchAllPaste(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
//       	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse);
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
       
       String aszOrgNID="";
       if(httpServletRequest.getParameter("orgnid") != null ){
           if(httpServletRequest.getParameter("orgnid").length() > 0){
        	   aszOrgNID = httpServletRequest.getParameter("orgnid");
           }
       }
       
       session.setAttribute("pasteform","true");
       session.setAttribute("orgnid", aszOrgNID);

       return processOppSearchAll(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      // end-of method processOppSearchAllPaste()
     
     
      /* 
       * output opp search results as js that will be included as a script
      */
      public ActionForward showChurchListings(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  return showPortalListings(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      public ActionForward showPortalListings(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  getPortalInfo( httpServletRequest, httpServletResponse);
    	  String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
       
       String aszOrgNID="";
       if(httpServletRequest.getParameter("orgnid") != null ){
           if(httpServletRequest.getParameter("orgnid").length() > 0){
        	   aszOrgNID = httpServletRequest.getParameter("orgnid");
           }
       }
       
       session.setAttribute("externallistings","true");
       session.setAttribute("orgnid", aszOrgNID);

       return processOppSearchAll(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      // end-of method processOppSearchAllPaste()
     
    
    //=== END   Search For Volunteer Opportunities ===>
    //=== END   Search For Volunteer Opportunities ===>
    //=== END   Search For Volunteer Opportunities ===>


     
     //=== START   Search For Organizations ===>
     //=== START   Search For Organizations ===>
     //=== START   Search For Organizations ===>
     
     /* 
      * process volunteer advance opportunity search - ali - 2006-11-27
      */
      public ActionForward processOrgSearchAll(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  getPortalInfo( httpServletRequest, httpServletResponse);
    	  String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
    	  if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
    	  if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    	  if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
    	  if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
    	  boolean bNatlAssoc = false;
    	  if(aszPortalRequestType.equals("natlassoc")){
    		  bNatlAssoc=true;
    	  }
    	  String aszRequestType = "";
    	  if(!(httpServletRequest.getParameter("requesttype")==null)){
    		  aszRequestType = httpServletRequest.getParameter("requesttype");
    	  }
    	  aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
    	  // clear session email token
    	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	  if(null != aSessDat){
    		  aSessDat.setOrgIdNum( 0 );
    		  aSessDat.setTokenKey( null );
    	  }
    	  if(aszRequestType.equalsIgnoreCase("myResultsAdminSelect")){ 
    		  // make sure current user is logged in; else call cookielogin
    		  if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			  if(null != aSessDat){
    				  aSessDat.setOrgNID( aszPortalNID );
    				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
    			  }
    			  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
    			  return actionMapping.findForward( "createaccount2" );
    		  }
    		  if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
    			  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    				  return actionMapping.findForward( "showlogin" );
    			  }else{
    				  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    			  }
    		  }
    	  }

    	  int iNatlAssocNID = 0;
    	  if(bNatlAssoc==true){
    		  //m_BaseHelp.convertToInt( aszPortalNID )
    		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
    	  }
    	  // if natl association, then the OrgNid should be that of the correct national association
    	  boolean b_AssocManager = false;
    	  allocatedOrgBRLO( httpServletRequest );
    	  OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
    	  if(bNatlAssoc==true){
    		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);
    		  // if natl association, then the OrgNid should be that of the correct national association
    		  aOrgInfoObj.setORGNID( iNatlAssocNID );
    		  int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
    		  for(int i=0; i<a_iNatlAssocNIDs.length; i++){
    			  if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
    		  }
    	  }

    	  if(aszPortal.length()>0){
    		  if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			  return actionMapping.findForward("404");
    		  }
    	  }
    	  int iRetCode=0;
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          SearchParms aSParm = new SearchParms();
          aSParm.setSearchMethod("processOrgSearchAll");
          if(httpServletRequest.getQueryString() != null){
        	  aSParm.setSearchQueryString(httpServletRequest.getQueryString());
          }
          aSParm.setSubdomain(httpServletRequest.getHeader("host"));
          aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
          aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
          m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
          aSParm.setPortalNID( aszPortalNID);
          if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);
          if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
        	  aSParm.setPostalCode("");
          }
          // m_BaseHelp.getSearchRequestForm(oFrm,aSParm);
          httpServletRequest.setAttribute( "sparm", aSParm );
          allocatedOppSrchBRLO( httpServletRequest );
          ArrayList aList = new ArrayList(2);
          httpServletRequest.setAttribute( "alist", aList );
          
          // get the kind of search/sort; ie search by...
          String aszSort="";
          if (aSParm.getSearchKey().length() > 2){
        	  aszSort=aSParm.getSearchKey();
          } else if (aSParm.getSearchKey2().length() > 2){
        	  aszSort=aSParm.getSearchKey2();
          } else if (aSParm.getSearchKey3().length() > 2){
        	  aszSort=aSParm.getSearchKey3();
          }
       
          aSParm.setSearchRequestType( aszRequestType );
          if(	aszRequestType.equalsIgnoreCase("myResultsAdminSelect")   && b_AssocManager==false){ // ONLY  if NOT MANAGING Natl Assoc portal
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES  );
          }
          else
          
          if (aszSort.equalsIgnoreCase("organization")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_ORGNAME  );
          }else if (aszSort.equalsIgnoreCase("postal")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POSTAL  );
          }else if (aszSort.equalsIgnoreCase("type")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_TYPE  );
          }else if (aszSort.equalsIgnoreCase("city")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_CITY  );
          }else if (aszSort.equalsIgnoreCase("state")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STATE  );
          }else if (aszSort.equalsIgnoreCase("prov")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_PROV  );
          }else if (aszSort.equalsIgnoreCase("country")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_COUNTRY  );
          }else if (aszSort.equalsIgnoreCase("denomaffil")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DENOMAFFIL  );
          }else if (aszSort.equalsIgnoreCase("affil")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_AFFIL1  );
          }else if (aszSort.equalsIgnoreCase("opportunity")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNAME  );
          }else if (aszSort.equalsIgnoreCase("updatedt")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_UPDATEDT  );             
          }else if (aszSort.equalsIgnoreCase("distance")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DISTANCE  );             
          }else if (aszSort.equalsIgnoreCase("numvolorg")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_NUM_VOL_ORG  );             
          }else if (aszSort.equalsIgnoreCase("popularity")){
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY  );             
          }else{
              aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY  );        	 
          }
          int iMaxSearchResultsRows=13000;
          aSParm.setmaxSearchResultRows(iMaxSearchResultsRows);

          // if administrator, don't limit to already favorited, b/c you're actually in the use case of selecting favorites
          if(aszRequestType.equalsIgnoreCase("myResultsAdminSelect") && b_AssocManager==false){ // ONLY narrow if NOT MANAGING natl assoc
            	aSParm.setPortalAdminUID(aszPortalUID);
              	aSParm.setPortalNID(0);
             	aSParm.setPortalUID(0);
             	aSParm.setPortal(777); // administrator through portal
          }
          allocatedIndBRLO( httpServletRequest );
          AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
          aHeadObj.setPortal(aszPortal);
          if(bNatlAssoc==true && b_AssocManager==false){ // ONLY narrow if NOT MANAGING
        	  // lookup the tid of the orgaffil associated with the given National Association
              iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
              aSParm.setPortalUID(aHeadObj.getPortalUID());
              if(aHeadObj.getPortalOrgAffilTID()>0){
             	 aSParm.setOrgAffil1TID(aHeadObj.getPortalOrgAffilTID());
              }
              aSParm.setRequestType("natlassoc");
          }

          boolean feeds=true;
          if(aSParm.getSource().equalsIgnoreCase("nofeeds")){
         	 feeds=false;
          }
          
          int iRetCode2=0;
          iRetCode = m_SearchBRLOObj.advanceSearchOrganizations( aList, aSParm, aHeadObj, false);
          if(feeds){ // also search the feeds table of orgs
        	  iRetCode2 = m_SearchBRLOObj.advanceSearchOrganizations( aList, aSParm, aHeadObj, feeds);
          }
  		 if(iRetCode==0 || iRetCode2==0){
  			 iRetCode=0;
  		 }
          // set bean up so that sort can be done from results page, too
          httpServletRequest.setAttribute("searchinfo", aSParm);
          // first test to see if this should be forwarding the user to the my_results tabbed page results rather than the main results page
          if(!(aszRequestType==null)){
              if(	aszRequestType.equalsIgnoreCase("myResultsTab") 		|| 
                		aszRequestType.equalsIgnoreCase("myResultsAdminSelect")
                ){
 	             if(0 != iRetCode){
 	              	return actionMapping.findForward( "myresultstaborgnone" );
 	             }
 	             if(	aszRequestType.equalsIgnoreCase("myResultsAdminSelect")    ){
 	            	 allocatedOrgBRLO( httpServletRequest );
	                 aOrgInfoObj.setORGNID( aszPortalNID );
	                 aOrgInfoObj.setORGUID( aszPortalUID );
	                 aOrgInfoObj.setNatlAssociationNID( aszPortalNID );
                	 httpServletRequest.setAttribute("orgportal", aOrgInfoObj);
	                 if(bNatlAssoc==true){
//		                  iRetCode = m_OrgBRLOObj.loadFavoriteOrgs( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC );
	                	 aOrgInfoObj.setORGNID( aszPortalNID );
	                	 ArrayList aAssocOrgList = new ArrayList();
	                	 httpServletRequest.setAttribute( "assocorglist", aAssocOrgList );
	                	 if(b_AssocManager==true){
	                		 aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() );
	                		 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	                		 // will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
	                		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );
	                		 iRetCode = m_OrgBRLOObj.getOrgListForAssociation( aAssocOrgList, iNatlAssocNID, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC); 

	                		 int[] i_aOrgNIDs = new int[iMaxSearchResultsRows];
	                		 int count=0;
	                		 // iterate through the aAssocOrgList for org nids, and add to the OppList for org for each of those orgs
	                		 Iterator<OrganizationInfoDTO> itr_aAssocOrgList = aAssocOrgList.iterator();
	                		 while(itr_aAssocOrgList.hasNext()){
	                			 OrganizationInfoDTO orgElement_aAssocOrgList=new OrganizationInfoDTO();
	                			 orgElement_aAssocOrgList = itr_aAssocOrgList.next();
	                			 int iAssocOrgNID = orgElement_aAssocOrgList.getORGNID();
	                			 if(iAssocOrgNID>0){
	                				 i_aOrgNIDs[count] = orgElement_aAssocOrgList.getORGNID();
            						 count++;
	                			 }
	                		 }
	                		 int[] i_aOrgFavoritedNIDs = new int[count];
	                		 for(int i=0; i<count; i++){
	                			 try{
	                				 i_aOrgFavoritedNIDs[i] = i_aOrgNIDs[i];
	                			 }catch(ArrayIndexOutOfBoundsException err){}
	                		 }
	                		 aOrgInfoObj.setORGFavoritedORGNIDsArray(i_aOrgFavoritedNIDs);
	                	 }else{
	                		 iRetCode = m_OrgBRLOObj.loadFavoriteOrgs( aOrgInfoObj );
	                	 }

	                	 return actionMapping.findForward( "portalresultstaborg" );
	                 }else{
	                	 return actionMapping.findForward( "portalresultstaborg" );
//	                	 return actionMapping.findForward( "myresultstaborg" );
	                 }
 	             }else{
                	 return actionMapping.findForward( "myresultstaborg" );
 	             }
              }
          }
          if(0 != iRetCode){
        	  return actionMapping.findForward( "orgnoresults" );
          }
          return actionMapping.findForward( "orgsearchresult" );
      }
      // end-of method processOrgSearchAll()
         
     
     
      //=== END   Search For Organizations ===>
      //=== END   Search For Organizations ===>
      //=== END   Search For Organizations ===>

     
     
     
     //====== START Search For Non-Christian Volunteer Opportunities ===>
     //====== START Search For Non-Christian Volunteer Opportunities ===>
     //====== START Search For Non-Christian Volunteer Opportunities ===>
     
 	/*
      * process top most opportunity search - non-Christian
      */
      public ActionForward processOppSearchTopIVol(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
//       	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
    	  int iRetCode=0;
   	getPortalInfo( httpServletRequest, httpServletResponse);
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
       allocatedIndBRLO( httpServletRequest );
       AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
       aHeadObj.setPortal(aszPortal);
       if(bNatlAssoc==true){
     	  // lookup the tid of the orgaffil associated with the given National Association
           iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );

       }
      
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
      	// clear session email token
      	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	if(null != aSessDat){
          	aSessDat.setOrgIdNum( 0 );
          	aSessDat.setTokenKey( null );
      	}
          SearchParms aSParm = new SearchParms();
         	aSParm.setSearchMethod("processOppSearchTopIVol");
           	if(httpServletRequest.getQueryString() != null){
           		aSParm.setSearchQueryString(httpServletRequest.getQueryString());
           	}
           	aSParm.setSubdomain(httpServletRequest.getHeader("host"));
           	aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
           	aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
          
	        aSParm.setPortalNID( aszPortalNID);
	        if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);

      	String aszPostalCode = (String)httpServletRequest.getParameter("postalcode");
      	String aszCategory1 = (String)httpServletRequest.getParameter("category1");
      	String aszDistance1 = (String)httpServletRequest.getParameter("distance");
          aSParm.setPostalCode( aszPostalCode );
          aSParm.setCategory( aszCategory1 );
          aSParm.setDistance( aszDistance1 );
          // set Required ChristianCode/Belief to NO for ivolunteering results
          aSParm.setReqCode( "No" );
          aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_TOPMOST_IVOL  );
          aSParm.setmaxSearchResultRows(250);
          allocatedOppSrchBRLO( httpServletRequest );
          ArrayList aList = new ArrayList(2);
          httpServletRequest.setAttribute( "alist", aList );
          iRetCode = m_SearchBRLOObj.advanceSearchOpportunitiesIVol( aList, aSParm, aHeadObj);
        	// set bean up so that sort can be done from results page, too
          httpServletRequest.setAttribute("searchinfo", aSParm);
          if(0 != iRetCode){
          	return actionMapping.findForward( "404" );
          }
      	return actionMapping.findForward( "oppsearchresult" );
      }
      // end-of method processOppSearchTopIVol()

      /*
      * process volunteer advance opportunity search - non-christian
      */
      public ActionForward processOppSearch1IVol(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
//       	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
    	  int iRetCode=0;
   	getPortalInfo( httpServletRequest, httpServletResponse);
  	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
  	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
  	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
  	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
       allocatedIndBRLO( httpServletRequest );
       AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
       aHeadObj.setPortal(aszPortal);
       if(bNatlAssoc==true){
     	  // lookup the tid of the orgaffil associated with the given National Association
           iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );

       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
      	// clear session email token
      	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	if(null != aSessDat){
          	aSessDat.setOrgIdNum( 0 );
          	aSessDat.setTokenKey( null );
      	}
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
          SearchParms aSParm = new SearchParms();
       	aSParm.setSearchMethod("processOppSearch1IVol");
       	if(httpServletRequest.getQueryString() != null){
       		aSParm.setSearchQueryString(httpServletRequest.getQueryString());
       	}
       	aSParm.setSubdomain(httpServletRequest.getHeader("host"));
       	aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
       	aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
          m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
          
	       aSParm.setPortalNID( aszPortalNID);
	        if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);

          if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
          	aSParm.setPostalCode("");
          }
          // m_BaseHelp.getSearchRequestForm(oFrm,aSParm);
          httpServletRequest.setAttribute( "sparm", aSParm );
          allocatedOppSrchBRLO( httpServletRequest );
          ArrayList aList = new ArrayList(2);
          httpServletRequest.setAttribute( "alist", aList );
          aSParm.setSearchType( OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY  );
          aSParm.setmaxSearchResultRows(2000);
      	// set bean up so that sort can be done from results page, too
          httpServletRequest.setAttribute("searchinfo", aSParm);
          iRetCode = m_SearchBRLOObj.advanceSearchOpportunitiesIVol( aList, aSParm, aHeadObj);
          if(0 != iRetCode){
          	return actionMapping.findForward( "404" );
          }
          return actionMapping.findForward( "oppsearchresult" );
      }
      // end-of method processOppSearch1IVol()
     

      /* 
       * process solr opportunity search 
       */
      public ActionForward processSolrSearchOpp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
   	   return processSearch(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      public ActionForward processSolrSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
   	   return processSearch(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
       public ActionForward processSearch(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
    	   
    	   getPortalInfo( httpServletRequest, httpServletResponse);
    	   if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
    	   if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
    	   if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
    	   if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
    	   
         aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );

    	   boolean bNatlAssoc = false;
    	   if(aszPortalRequestType.equals("natlassoc")){
    		   bNatlAssoc=true;
    	   }
    	   int iPortalNID=0;
    	   if(aszPortalNID.length()>0){
    		   try{
    			   iPortalNID = Integer.parseInt(aszPortalNID);
    		   }catch(Exception e){
    		   }
    	   }
    	   if(aszPortal.length()>0){
    		   if(aszPortalNID.length()==0){
    			   return actionMapping.findForward("404");
    		   }
    	   }

    	   boolean b_CVInternSrch = false;
    	   if(httpServletRequest.getParameter("cvintern") != null){
    		   if(httpServletRequest.getParameter("cvintern").toString().length()>0){
	    		   if(httpServletRequest.getParameter("cvintern").toString().equals("true")){
	    			   b_CVInternSrch = true;
	    		   }
    		   }
    	   }
    	   if(b_CVInternSrch == false){
        	   if(httpServletRequest.getAttribute("cvintern") != null){
        		   if(httpServletRequest.getAttribute("cvintern").toString().length()>0){
    	    		   if(httpServletRequest.getAttribute("cvintern").toString().equals("true")){
    	    			   b_CVInternSrch = true;
    	    		   }
        		   }
        	   }
    	   }
    	   String aszOrgNID="";
    	   int iOrgNID=0;
    	   if(session.getAttribute("orgnid")!=null){
    		   aszOrgNID = session.getAttribute("orgnid").toString();
    		   if(aszOrgNID.length()>0){
    			   try{iOrgNID = Integer.parseInt(aszOrgNID);
    			   }catch(Exception e){}
    		   }
    		   session.removeAttribute("orgnid");
    	   }
    	   // clear session email token
    	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	   if(null != aSessDat){
    		   aSessDat.setOrgIdNum( 0 );
    		   aSessDat.setTokenKey( null );
    	   }
    	   int iRetCode=0;
    	   DynaActionForm oFrm = (DynaActionForm)actionForm;
           SearchParms aSParm = new SearchParms();
           aSParm.setSearchMethod("processSearch");
           if(httpServletRequest.getQueryString() != null){
        	   aSParm.setSearchQueryString(httpServletRequest.getQueryString());
           }
           aSParm.setSubdomain(httpServletRequest.getHeader("host"));
           aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
           aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
           m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
           if(aSParm.getSearchForm().equalsIgnoreCase("advancedsearch")){
          	 if(! (aSParm.getSource().equalsIgnoreCase("feeds") || aSParm.getSource().equalsIgnoreCase("searchfeeds") )){
          		 aSParm.setSource("nofeeds");
          	 }
           }
           if(iOrgNID>0){
          	 aSParm.setNID(iOrgNID);
           }
           if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);
           if(aSParm.getPortalNID()==0 && iPortalNID>0){
        	   aSParm.setPortalNID(iPortalNID);
           }
           if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
           	aSParm.setPostalCode("");
           }
           String aszEmbed = "";
           if(!(httpServletRequest.getParameter("embed")==null)){
               aszEmbed = httpServletRequest.getParameter("embed");
           }
           
           String aszRequestType = "";
           if(!(httpServletRequest.getParameter("requesttype")==null)){
               aszRequestType = httpServletRequest.getParameter("requesttype");
           }
           aSParm.setSearchRequestType( aszRequestType );

           String aszFormat="";
           if(!(httpServletRequest.getParameter("format")==null)){
        	   aszFormat = httpServletRequest.getParameter("format");
           }

           // m_BaseHelp.getSearchRequestForm(oFrm,aSParm);
           httpServletRequest.setAttribute( "sparm", aSParm );
           allocatedOppSrchBRLO( httpServletRequest );
           ArrayList aList = new ArrayList(2);
           httpServletRequest.setAttribute( "alist", aList );
           ArrayList facetList = new ArrayList(2);
           httpServletRequest.setAttribute( "facetlist", facetList );
                    
           // get the kind of search/sort; ie search by...
           String aszSort="";
           if (aSParm.getSearchKey().length() > 2){
         	  aszSort=aSParm.getSearchKey();
           }

           int iMaxSearchResultsRows=10000;
           String aszSearchResultsNumber = aSParm.getPreviewSearch();
           int i_searchResultsNumber=10000;
           try{
               i_searchResultsNumber = Integer.parseInt(aszSearchResultsNumber);
           }catch(Exception e){
           }
           if(i_searchResultsNumber > 0 && i_searchResultsNumber < 101){
          	 iMaxSearchResultsRows=i_searchResultsNumber;
          	 aSParm.setPreviewSearchShortened("true");
           }
           aSParm.setmaxSearchResultRows(iMaxSearchResultsRows);
           // take care of old Region links...
           if(aSParm.getRegion().equalsIgnoreCase("Asia")){
          	 aSParm.setRegionTID(4947);
          	 aSParm.setRegion("");
           }
           
           int iSetLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 0);
           int iSetNOTLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 1);
           
           allocatedIndBRLO( httpServletRequest );
           AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
           aHeadObj.setPortal(aszPortal);
           if(bNatlAssoc==true){
         	  // lookup the tid of the orgaffil associated with the given National Association
               iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
           }
           iRetCode=-2;
           
           // set bean up so that sort can be done from results page, too
           httpServletRequest.setAttribute("searchinfo", aSParm);
           if(aszFormat.equals("orgdirectory")){
		        ArrayList facetOrgList = new ArrayList(2);
		        httpServletRequest.setAttribute( "facetorglist", facetOrgList );
				String content_type = "content_type:organization";
		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetOrgList, aSParm, aHeadObj, content_type);
		        return actionMapping.findForward( "orgdirectory" );
           }else if(aszFormat.equals("interndirectory")){
		        ArrayList facetInternList = new ArrayList(2);
		        httpServletRequest.setAttribute( "facetinternlist", facetInternList );
				String content_type = "content_type:intern";
		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetInternList, aSParm, aHeadObj, content_type);
		        return actionMapping.findForward( "interndirectory" );
          }

           
			String content_type = "content_type:opportunity";
			String[] aszFQ = aSParm.getFilterQueryArray();
			String[] aszFQupdated = aszFQ;
	    	   
			// if aszFQ includes Ciy Vision Intern Applicant, then b_CVInternSrch = true
			for(int i=0; i<aszFQ.length; i++){
				if(aszFQ[i]!=null){
					if(aszFQ[i].contains("City Vision Intern Applicant")){
						b_CVInternSrch = true;
						break;
					}
				}
			}
	    	boolean b_cvInternSiteSearch = false, b_portalSearch = false;
			String aszPortalSearch = "", aszPortalSearchOrg = "", aszPortalSearchOpps = "";
	    	   if(httpServletRequest.getAttribute("organization_name") != null){
	    		   if(httpServletRequest.getAttribute("organization_name").toString().length()>0){
		    		   if(httpServletRequest.getAttribute("organization_name").equals("City Vision")){
		    			   b_portalSearch=true;
						   aszPortalSearchOrg="screened:\"City Vision\"";
						   aszPortalSearchOpps="intern_type:\"City Vision Internship\"";
		    		   }
	    		   }
	    	   }
	    	   if(httpServletRequest.getAttribute("intern_type") != null){
	    		   if(httpServletRequest.getAttribute("intern_type").toString().length()>0){
		    		   if(httpServletRequest.getAttribute("intern_type").equals("City Vision Internship")){
		    			   b_portalSearch=true;
						   aszPortalSearchOrg="screened:\"City Vision\"";
						   aszPortalSearchOpps="intern_type:\"City Vision Internship\"";
		    		   }
	    		   }
	    	   }
	    	   if(aszPortal.contains("cityvision")){
    			   b_portalSearch=true;
				   aszPortalSearchOrg="screened:\"City Vision\"";
				   aszPortalSearchOpps="intern_type:\"City Vision Internship\"";
	    	   }

	    	   // b_cvInternSiteSearch is true if a parameter is submitted.  then it has to test that the user has correct access rights (org is cvintern_site_approved="City Vision")
	    	   if(b_CVInternSrch == true){
System.out.println("2037  searchOpportunitiesAction.java;   b_CVInternSrch is "+b_CVInternSrch+"; aCurrentUserObj.getUserIsLoggedIntoSystem() is  "+aCurrentUserObj.getUserIsLoggedIntoSystem()+"; aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());	    		   
	    		   // need to now test access rights for the given user
	    		   getLoggedInStatus(httpServletRequest, httpServletResponse);
System.out.println("2040  searchOpportunitiesAction.java;   aszLoggedInStatus is |"+aszLoggedInStatus+"|;  aCurrentUserObj.getUserIsLoggedIntoSystem() is  "+aCurrentUserObj.getUserIsLoggedIntoSystem()+"; aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());	    		   
if(null == aCurrentUserObj) {
	System.out.println("2042  searchOpportunitiesAction.java;  aCurrentUserObj is  null ; aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());	    		   
}else{
	System.out.println("2044  searchOpportunitiesAction.java;  aCurrentUserObj is not null  ; aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());	    		   
}
	    	   if(aszLoggedInStatus.equals("showlogin")){
	    			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SRCHINTERNAPPLICANTS );
	    			   return actionMapping.findForward( "showlogin" );
	    		   }else if(aszLoggedInStatus.contains("processCookieLogin")){
	    			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SRCHINTERNAPPLICANTS );
		        		httpServletRequest.setAttribute("cvintern","true") ;
	    			   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	    		   }
System.out.println("2048;   after login stuff, aCurrentUserObj.getUserIsLoggedIntoSystem() is  "+aCurrentUserObj.getUserIsLoggedIntoSystem() +"; aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());	    		   
	    		   
	    		   allocatedOrgBRLO( httpServletRequest );
	    		   allocatedApplicCodesBRLO( httpServletRequest );
	    		   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
	    			   if(null != aSessDat){
	    				   aSessDat.setTokenKey(null);
	    				   aSessDat.setOrgNID(null);
	    				   aSessDat.setOppNID(null);
	    				   aSessDat.setSubdomain(null);
	    				   aSessDat.setSiteEmail(null);
	    			   }
System.out.println("2060;   USER_LOGINOK  ");	    		   
	    			   
						int iUID = aCurrentUserObj.getUserUID();
	    			   // check to see if the current user has access rights on any cvintern_approved orgs
	    			   ArrayList aCVCSitesList = new ArrayList( 2);
	    			   m_AppCodesBRLOObj.getCVInternOrgSitesList(aCVCSitesList);
	    			   // org_nids is a_cvinternOrgsList.
System.out.println("line 2066 aCVCSitesList.size() is "+ aCVCSitesList.size());	    			   
	    			   for(int index=0; index < aCVCSitesList.size(); index++){
	    				   AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVCSitesList.get(index);
	    				   if(null == aAppCode) continue;
	    				   int iOptRqCode = aAppCode.getAPCIdSubType();
	    				   int iOrgNIDTmp = aAppCode.getAPCIdSubType();
System.out.println("line 2100 - trying to load org nid "+iOrgNIDTmp+";  attempting to load with user "+iUID);	    				   
	    				   // try to load the iOrgNIDTmp by admin or contact and if it succeeds, then it can show the search results.
	    				   OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	    				   aOrgInfoObj.setORGNID( iOrgNIDTmp );
	    				   aOrgInfoObj.setORGUID( iUID ); // check that the user MUST HAVE ownership of the said organization
	    				   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	    				   if(iRetCode == -111){
	    					   // if it failed, try to load via contact
//System.out.println("2079 error loading Org directly; load by contact instead uid: "+aCurrentUserObj.getUserUID());	    					   
	    					   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	    				   }else{
System.out.println("2082 successfully loaded first time through");	    					   
	    				   }
	    				   if(iRetCode==0){
	    					   b_cvInternSiteSearch = true;
System.out.println("Line 2086;   b_cvInternSiteSearch is "+b_cvInternSiteSearch);	    		   
	    					   break;
	    				   }
	    			   }
	    		   }
	    	   }
	    	   
			if(aszPortalSearch.length() > 0 || b_portalSearch==true){
				aszFQupdated = new String[aszFQ.length+1];
			}
		    for(int i=0; i<aszFQ.length; i++){
		    	String aszFilter = aszFQ[i];
				if(aszFilter.contains("content_type:organization") || aszFilter.contains("ctp:\"org\"")){
					content_type = "content_type:organization";
					aszPortalSearch = aszPortalSearchOrg;
				}else if(aszFilter.contains("content_type:job") || aszFilter.contains("ctp:\"job\"")){
					content_type = "content_type:job";
				}else if(aszFilter.contains("content_type:resume") || aszFilter.contains("ctp:\"resume\"") || aszFilter.contains("ctp:\"res\"")){
					content_type = "content_type:resume";
				}else if(aszFilter.contains("content_type:opportunity") || aszFilter.contains("ctp:\"opp\"")){
					aszPortalSearch = aszPortalSearchOpps;
				}
				if(aszPortalSearch.length() > 0 || b_portalSearch==true){
					aszFQupdated[i] = aszFQ[i];
				}
		    }
			if(aszPortalSearch.length() > 0){
				aszFQupdated[aszFQ.length] = aszPortalSearch;
			}
			if(b_cvInternSiteSearch==true){
				try{
					int aszFQtmpSize = aszFQupdated.length;
					String[] aszFQtmp = new String[aszFQtmpSize + 3];//2];
					
					int i=0;
					for(i=0; i<aszFQtmpSize; i++){
						if(aszFQupdated[i] != null)	{
							aszFQtmp[i] = aszFQupdated[i];
						}
					}
					aszFQtmp[i] = "cvintern_applicant:\"City Vision Intern Applicant\"";
					
					aszFQtmp[i+1] = "screened:[1 TO 2]";//eventually should be [1 TO 2]
					aszFQtmp[i+2] = "cvintern_placed:0";
					
		
					aszFQupdated = aszFQtmp;
				}catch(Exception e){
					System.out.println("error");
				}
			}
		    aSParm.setFilterQueryArray(aszFQupdated);
		    
           iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj, content_type, aszFormat);
           if(aszFormat.equals("directory")){
               return actionMapping.findForward( "directory" );
           }else if(aszFormat.equals("zip")){
               return actionMapping.findForward( "zipdirectory" );
           }else if(aszFormat.equals("crawlable")){
        	   if(aszEmbed.equals("embed"))		return actionMapping.findForward( "searchresultembed" );
               return actionMapping.findForward( "searchresult" );
           }else if(0 != iRetCode){
        	   if(aszEmbed.equals("embed"))		return actionMapping.findForward( "noresultembed" );
        	   return actionMapping.findForward( "404" );
           }
    	   if(aszEmbed.equals("embed"))		return actionMapping.findForward( "searchresultembed" );
    	   if(b_cvInternSiteSearch == true){
System.out.println("Line 2151;   b_cvInternSiteSearch is "+b_cvInternSiteSearch);	    		   
    		   return actionMapping.findForward( "applicationlist" );
    	   }
    	   if(b_CVInternSrch == true && b_cvInternSiteSearch == false)	{
System.out.println("Line 2155;   b_CVInternSrch is "+b_CVInternSrch+";   b_cvInternSiteSearch is "+b_cvInternSiteSearch);	    		   

    		   return actionMapping.findForward( "noaccess" );
    	   }
    	   

           return actionMapping.findForward( "searchresult" );
      }// end-of method processSearch()
       
       
       

//  	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
//  	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
      /**
      * process cookie login
      */
      public ActionForward processCookieLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
System.out.println("2174 in searchOpportunitiesAction - cookielogin called");    	  
      	int iRetCode=0, iRetCode2=0 ;
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
//       	HttpSession session=httpServletRequest.getSession();
       	//getPortalInfo( httpServletRequest, httpServletResponse);
   	getPortalInfo( httpServletRequest, httpServletResponse);
   	String aszPortal="", aszPortalNID="";
       if(httpServletRequest.getParameter("portal") != null ){
           if(httpServletRequest.getParameter("portal").length() > 0){
               aszPortal = httpServletRequest.getParameter("portal");
           }
       }
       if(session.getAttribute(aszPortal+"_nid") != null ){
           if(session.getAttribute(aszPortal+"_nid").toString().length() > 0){
           	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
           }
       }
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			//httpServletRequest.setAttribute("redirectpage","noportalexists");
			//return actionMapping.findForward("mappingpage");
			return actionMapping.findForward("404");
       	}
       }
  		String aszIPAddress = httpServletRequest.getRemoteAddr();
  		String aszCurrentPage =  httpServletRequest.getQueryString();

          allocatedIndBRLO( httpServletRequest );
       	
          String authCookieValue="";
          Cookie[] cookies = httpServletRequest.getCookies();
          String cookieName="chrisvolAuth";
          if(cookies != null){
	          for(int i=0; i<cookies.length; i++) {
	          	Cookie cookie = cookies[i];
	          	if (cookieName.equals(cookie.getName()))
	          		authCookieValue = cookie.getValue();
	          }
	          if(authCookieValue != null){
	          	if(authCookieValue.length()>0){
	//          		int itmp = PersonInfoDTO.COOKIE_USER;
	//          		iRetCode = m_IndBRLOObj.validateSignatureCookie( authCookieValue, aszIPAddress, itmp );
	//      			int indexForUID = authCookieValue.lastIndexOf(".");
	//      	 		String aszUID = authCookieValue.substring(indexForUID+1);
	//      	 		aIndivObj.setUserUID(aszUID);
	          		aCurrentUserObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	          		aCurrentUserObj.setSessionIP(aszIPAddress);
	          		aCurrentUserObj.setSessionValue(authCookieValue);
	      	 		// does this session id exist in the sessions table in the db? and with the given IP address?
	      	 		iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aCurrentUserObj );
	          		if( iRetCode == 0 ){
	          			// login the user
	          	        iRetCode = m_IndBRLOObj.loginUser( aCurrentUserObj, aszSiteVersion );
	          	        if( (iRetCode != 0) && (iRetCode != -222) ){
	          	          	m_BaseHelp.setFormData(oFrm,"errormsg", aCurrentUserObj.getErrorMsg() );
	          	          	return actionMapping.findForward( "loginstatus" );
	          	        }
	          	        iRetCode2=iRetCode;
	          	        // test if this is a full user or not; could have signed up through FB app, or create account process and still be partial
				     	AppSessionDTO aSessDat=null;
				       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
				        iRetCode = m_IndBRLOObj.testFullUser( aCurrentUserObj, aSessDat.getTokenKey() );
	          	        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
	          	        	aCurrentUserObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
	          	        }else{
	          	        	aCurrentUserObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
	          	        }
	          	        int iRetCode3=iRetCode;
	          	        if(aCurrentUserObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	          	        	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aCurrentUserObj, 0);
	          	        	if(session.getAttribute("FB_User_ID")!=null){
	          	       		 if(session.getAttribute("FB_User_ID").toString().length()>1){
	          	       		aCurrentUserObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
	          	       		 }
	          	             if(session.getAttribute("facebookapikey")==null ){
	          	            	aCurrentUserObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	          	             }else if (session.getAttribute("facebooksecretkey")==null){
	          	            	aCurrentUserObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	          	             }else{
	          	             	String aszFBapikey = session.getAttribute("facebookapikey").toString();
	          	             	if(aszFBapikey.length()>1){
	          	             		aCurrentUserObj.setFBapikey(aszFBapikey);
	          	             	}else{
	          	             		aCurrentUserObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	          	             	}
	          	             	String aszFBsecretkey = session.getAttribute("facebooksecretkey").toString();
	          	             	if(aszFBsecretkey.length()>1){
	          	             		aCurrentUserObj.setFBsecretkey(aszFBsecretkey);
	          	             	}else{
	          	             		aCurrentUserObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	          	             	}
	          	             	boolean validateFBapikey = false;
	          	             	if(aszFBapikey.length()>0 || aszFBsecretkey.length()>0){
	          	             		validateFBapikey=m_IndBRLOObj.validateFBapikey(aszFBapikey, aszFBsecretkey, aCurrentUserObj);
	          	             		if(validateFBapikey==false){
	          	             			aCurrentUserObj.appendErrorMsg("'"+aCurrentUserObj.getFBapikey()+"' is an invalid facebook application api key. ");
	          	                    	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aCurrentUserObj, 0);
	          	             			return actionMapping.findForward( "personalityministryarea2" );
	          	             		}
	          	             	}else{
	          	             		aCurrentUserObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
	          	                	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aCurrentUserObj, 0);
	          	             		return actionMapping.findForward( "personalityministryarea2" );
	          	             	}
	          	             }
	          	       	 	}
	          	        	if(iRetCode2!=-222){
	          	                iRetCode = m_IndBRLOObj.updateIndividualHead( aCurrentUserObj, aszSiteVersion );
	          	            }
	          	        }
	          	        
	          	      aCurrentUserObj.processTokens();
	          	    	if(aCurrentUserObj.getProvider().length()>0){
	          	    		session.setAttribute("socialize", aCurrentUserObj.getProvider());
	          	    	}
	          	    	

	          	    	
	          	        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
	          		        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
	          		    	

	          	    	  aszLoggedInStatus = "";
		          	    	
		          	  		if( aszCurrentPage.contains("method=showPortalOpps") ){
		          	  			return showPortalOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		          	  	    }
	           		    	if(null != aSessDat){
	          		        	int iOppNID = aSessDat.getOppNID();
	          		        	int iOrgNID = aSessDat.getOrgNID();
	          		        	String aszToken = aSessDat.getTokenKey();
	          		        	aSessDat.setTokenKey(null);
	          		        	if(aszToken == AppSessionDTO.TOKEN_SRCHINTERNAPPLICANTS){
	          		        		return processSearch(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	          		        	}
	          		        	return showMyMinistryOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		          	  		}else{
		          	  			return actionMapping.findForward( "loginstatus" );
	          		        }
	          	  		}else{
	          	  			session.setAttribute ("drupalsession","login");
	          	  			return actionMapping.findForward( "loginstatus" );
	          	    	}
	          	        
//	          	        return showMyMinistryOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
	          		}else{
	//                	return actionMapping.findForward( "loginstatus" );
	          		}
	          	}   
	          }
      	}

	  		session.setAttribute ("drupalsession","login");
          return actionMapping.findForward( "loginstatus" );
      }
      // end-of method processLogin()
      
      
      
      
      
      
      
      
      
      
      

      //====== END Search For Non-Christian Volunteer Opportunities ===>
      //====== END Search For Non-Christian Volunteer Opportunities ===>
      //====== END Search For Non-Christian Volunteer Opportunities ===>
     

    //====== START Private Methods ===>
    //====== START Private Methods ===>
    //====== START Private Methods ===>


      /**
       * get portal information for page loading
       *//*
      private void getPortalInfo( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
	       	String aszPortal = "", aszFileLocation = "";
	    	int iRetCode=0, iOrgNID=0, iPortalUID=0;
	      	HttpSession session=httpServletRequest.getSession();
	        if(httpServletRequest.getParameter("portal") != null ){
	            if(httpServletRequest.getParameter("portal").length() > 0){
	                aszPortal = httpServletRequest.getParameter("portal");
	                if(
	                		session.getAttribute(aszPortal + "_banner") == null	||
	                		session.getAttribute(aszPortal + "_nid") == null	||
	                		session.getAttribute(aszPortal + "_uid") == null
	                ){
		                
		                // do a quick db query to get the filename of the banner image for this portal.  query will also get the org(church)/portal result
		            	AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
		            	aHeadObj.setPortal(aszPortal);
		                allocatedIndBRLO( httpServletRequest );
		                iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
		
		                if (iRetCode == 0){
		                	aszFileLocation = aHeadObj.getPortalBanner();
		                	iOrgNID = aHeadObj.getPortalNID();
		                	iPortalUID = aHeadObj.getPortalUID();
		                	if(aszFileLocation.length()>0){
		                     	session.setAttribute(aszPortal + "_banner", aszFileLocation);
		                	}
		                	if(iOrgNID>0){
		                     	session.setAttribute(aszPortal + "_nid", ""+iOrgNID);
		                	}
		                	if(iPortalUID>0){
		                     	session.setAttribute(aszPortal + "_uid", ""+iPortalUID);
		                	}
		             	}
	                }
	            }
	        }
      }*/

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      public ActionForward processSearchTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
   	   HttpSession session=httpServletRequest.getSession();
   	   String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
   	   /*
   	   getPortalInfo( httpServletRequest, httpServletResponse, session);
   	   if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
   	   if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
   	   if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
   	   if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
   	   */
   	   boolean bNatlAssoc = false;
   	   if(aszPortalRequestType.equals("natlassoc")){
   		   bNatlAssoc=true;
   	   }
   	   int iPortalNID=0;
   	   if(aszPortalNID.length()>0){
   		   try{
   			   iPortalNID = Integer.parseInt(aszPortalNID);
   		   }catch(Exception e){
   		   }
   	   }
   	   if(aszPortal.length()>0){
   		   if(aszPortalNID.length()==0){
   			   return actionMapping.findForward("404");
   		   }
   	   }
   	   String aszOrgNID="";
   	   int iOrgNID=0;
   	   if(session.getAttribute("orgnid")!=null){
   		   aszOrgNID = session.getAttribute("orgnid").toString();
   		   if(aszOrgNID.length()>0){
   			   try{iOrgNID = Integer.parseInt(aszOrgNID);
   			   }catch(Exception e){}
   		   }
   		   session.removeAttribute("orgnid");
   	   }
   	   // clear session email token
   	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   	   if(null != aSessDat){
   		   aSessDat.setOrgIdNum( 0 );
   		   aSessDat.setTokenKey( null );
   	   }
   	   int iRetCode=0;
   	   DynaActionForm oFrm = (DynaActionForm)actionForm;
          SearchParms aSParm = new SearchParms();
          aSParm.setSearchMethod("processOppSearchAll");
          if(httpServletRequest.getQueryString() != null){
       	   aSParm.setSearchQueryString(httpServletRequest.getQueryString());
          }
          aSParm.setSubdomain(httpServletRequest.getHeader("host"));
          aSParm.setReferringPage(httpServletRequest.getHeader("referer"));
          aSParm.setUserAgent(httpServletRequest.getHeader("user-agent"));
          m_OrgActHelp.getOppSearchOptFromForm1( oFrm, aSParm );
          if(aSParm.getSearchForm().equalsIgnoreCase("advancedsearch")){
         	 if(! (aSParm.getSource().equalsIgnoreCase("feeds") || aSParm.getSource().equalsIgnoreCase("searchfeeds") )){
         		 aSParm.setSource("nofeeds");
         	 }
          }
          if(iOrgNID>0){
         	 aSParm.setNID(iOrgNID);
          }
          if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);
          if(aSParm.getPortalNID()==0 && iPortalNID>0){
       	   aSParm.setPortalNID(iPortalNID);
          }
          if(aSParm.getPostalCode().contains("Postal") || aSParm.getPostalCode().contains("postal")){
          	aSParm.setPostalCode("");
          }
          String aszRequestType = "";
          if(!(httpServletRequest.getParameter("requesttype")==null)){
              aszRequestType = httpServletRequest.getParameter("requesttype");
          }
          aSParm.setSearchRequestType( aszRequestType );

          String aszFormat="";
          if(!(httpServletRequest.getParameter("format")==null)){
       	   aszFormat = httpServletRequest.getParameter("format");
          }

          // m_BaseHelp.getSearchRequestForm(oFrm,aSParm);
          httpServletRequest.setAttribute( "sparm", aSParm );
          allocatedOppSrchBRLO( httpServletRequest );
          ArrayList aList = new ArrayList(2);
          httpServletRequest.setAttribute( "alist", aList );
          ArrayList facetList = new ArrayList(2);
          httpServletRequest.setAttribute( "facetlist", facetList );
                   
          // get the kind of search/sort; ie search by...
          String aszSort="";
          if (aSParm.getSearchKey().length() > 2){
        	  aszSort=aSParm.getSearchKey();
          }

          int iMaxSearchResultsRows=10000;
          String aszSearchResultsNumber = aSParm.getPreviewSearch();
          int i_searchResultsNumber=10000;
          try{
              i_searchResultsNumber = Integer.parseInt(aszSearchResultsNumber);
          }catch(Exception e){
          }
          if(i_searchResultsNumber > 0 && i_searchResultsNumber < 101){
         	 iMaxSearchResultsRows=i_searchResultsNumber;
         	 aSParm.setPreviewSearchShortened("true");
          }
          aSParm.setmaxSearchResultRows(iMaxSearchResultsRows);
          // take care of old Region links...
          if(aSParm.getRegion().equalsIgnoreCase("Asia")){
         	 aSParm.setRegionTID(4947);
         	 aSParm.setRegion("");
          }
          
          int iSetLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 0);
          int iSetNOTLatLongFromZip = m_SearchBRLOObj.searchForZipCodeLatLongInDB(aSParm, 1);
          
          allocatedIndBRLO( httpServletRequest );
          AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
          aHeadObj.setPortal(aszPortal);
          if(bNatlAssoc==true){
        	  // lookup the tid of the orgaffil associated with the given National Association
              iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
          }
          iRetCode=-2;
          
          // set bean up so that sort can be done from results page, too
          httpServletRequest.setAttribute("searchinfo", aSParm);
          if(aszFormat.equals("orgdirectory")){
		        ArrayList facetOrgList = new ArrayList(2);
		        httpServletRequest.setAttribute( "facetorglist", facetOrgList );
				String content_type = "content_type:organization";
		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetOrgList, aSParm, aHeadObj, content_type);
		        return actionMapping.findForward( "orgdirectory" );
          }
          
			String content_type = "content_type:opportunity";
			String[] aszFQ = aSParm.getFilterQueryArray();
		    for(int i=0; i<aszFQ.length; i++){
		    	String aszFilter = aszFQ[i];
				if(aszFilter.contains("content_type:organization") || aszFilter.contains("ctp:\"org\"")){
					content_type = "content_type:organization";
				}else if(aszFilter.contains("content_type:job") || aszFilter.contains("ctp:\"job\"")){
					content_type = "content_type:job";
				}
		    }
          iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj, content_type, aszFormat);
          if(aszFormat.equals("directory")){
              return actionMapping.findForward( "directory" );
          }else if(aszFormat.equals("zip")){
              return actionMapping.findForward( "zipdirectory" );
          }else if(aszFormat.equals("crawlable")){
              return actionMapping.findForward( "searchresult" );
          }else if(0 != iRetCode){
       	   return actionMapping.findForward( "404" );
          }
          return actionMapping.findForward( "ajaxsolrsearchresult" );
     }// end-of method processSearchTest()
      
      
      

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      /**
       * get portal information for page loading
      * @throws IOException 
       */
       protected void getPortalInfo( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
     	  session=httpServletRequest.getSession();
     	  aszPortal = ""; aszPortalNID="";
     	  String aszFileLocation = "", aszPortalHeaderTags="", aszPortalHeader="", aszPortalCSS="", aszPortalFooter="",
     			  aszPortalOrgName = "", aszRequestType="";
     	  int iRetCode=0, iNID=0, iPortalUID=0;
           aszSiteVersion="default";
           if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
     				httpServletRequest.getHeader("host").contains( ":7001" ) 		
     			){
     			aszSiteVersion="development";
     		}else if(	httpServletRequest.getHeader("host").contains("staging-" ) 
     			){
     			aszSiteVersion="staging";
     		}
     	  if(httpServletRequest.getParameter("portal") != null ){
             if(httpServletRequest.getParameter("portal").length() > 0){
                 aszPortal = httpServletRequest.getParameter("portal");
                 
                 if(
                 		session.getAttribute(aszPortal + "_banner") == null	||
                 		session.getAttribute(aszPortal + "_header_tags") == null	||
                 		session.getAttribute(aszPortal + "_header") == null	||
                 		session.getAttribute(aszPortal + "_css") == null	||
                 		session.getAttribute(aszPortal + "_footer") == null	||
                 		session.getAttribute(aszPortal + "_org_name") == null	||
                 		session.getAttribute(aszPortal + "_nid") == null	||
                 		session.getAttribute(aszPortal + "_uid") == null	||
                 		session.getAttribute(aszPortal + "_type") == null
                 ){
 	                // do a quick db query to get the filename of the banner image for this portal.  query will also get the org(church)/portal result
 	            	AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
 	            	aHeadObj.setPortal(aszPortal);
 	                allocatedIndBRLO( httpServletRequest );
 	                iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
 	
 	                if (iRetCode == 0){
 	                	aszFileLocation = aHeadObj.getPortalBanner();
 	                	aszPortalHeaderTags = aHeadObj.getPortalHeaderTags();
 	                	aszPortalHeader = aHeadObj.getPortalHeader();
 	                	aszPortalCSS = aHeadObj.getPortalCSS();
 	                	aszPortalFooter = aHeadObj.getPortalFooter();
 	                	aszPortalOrgName = aHeadObj.getPortalOrgName();
 	                	iNID = aHeadObj.getPortalNID();
 	                	iPortalUID = aHeadObj.getPortalUID();
 	                	aszRequestType = aHeadObj.getRequestType();
 	                	if(aszFileLocation.length()>0){
 	                     	session.setAttribute(aszPortal + "_banner", aszFileLocation);
 	                	}
 	                	if(aszPortalHeaderTags.length()>0){
 	                     	session.setAttribute(aszPortal + "_header_tags", aszPortalHeaderTags);
 	                	}
 	                	if(aszPortalHeader.length()>0){
 	                     	session.setAttribute(aszPortal + "_header", aszPortalHeader);
 	                	}
 	                	if(aszPortalCSS.length()>0){
 	                     	session.setAttribute(aszPortal + "_css", aszPortalCSS);
 	                	}
 	                	if(aszPortalFooter.length()>0){
 	                     	session.setAttribute(aszPortal + "_footer", aszPortalFooter);
 	                	}
 	                	if(aszPortalOrgName.length()>0){
 	                     	session.setAttribute(aszPortal + "_org_name", aszPortalOrgName);
 	                	}
 	                	if(iNID>0){
 	                     	session.setAttribute(aszPortal + "_nid", ""+iNID);
 	                	}
 	                	if(iPortalUID>0){
 	                     	session.setAttribute(aszPortal + "_uid", ""+iPortalUID);
 	                	}
 	                	//if(aszRequestType.length()>0){
 	                	String aszTempt = aszPortal + "_type: "+aszRequestType;
 	                     	session.setAttribute(aszPortal + "_type", aszRequestType);
 	                	//}
 	                	/*
 	                	else if(aszRequestTypeSubmitted == "natlassoc"){
 	                     	session.setAttribute(aszPortal + "_type", aszRequestTypeSubmitted);
 	                	}
 	                	*/
 	             	}
                 }
             }
         }

         try {
           httpServletRequest.setAttribute("location", m_BaseHelp.getLocation(httpServletRequest));
         }
         catch(IOException e) {
           System.out.println("Error looking up location:");
           e.printStackTrace();
         }
       	aszPortalRequestType = "";
       	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
       	if(aszPortal.length()<1){
 	      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
 	        aszPortalUID = "";
       	}else{
 	      	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
 	        if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
 	      	if(session.getAttribute(aszPortal+"_type") != null ) 
 	      		if(session.getAttribute(aszPortal+"_type").toString().length() > 0) {
 	      			aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
 	      			
 	      		}
       	}
     }

      
      
      
      
      /**
       * get logged in status of user; if not, set a value to return the user to either login screen or cookielogin
       */
      protected void getLoggedInStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
    	  aszLoggedInStatus = "";
System.out.println("line 2751 searchopp aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());    	  
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          		aszLoggedInStatus="showlogin";
          		return;
          	}else{
          		aszLoggedInStatus="processCookieLogin 2756"  ;
          		return;
          	}
          }
System.out.println("line 2761 searchopp aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());   
		boolean b_tmp = false;
		if(null == aCurrentUserObj)	b_tmp=true;
		else if(aCurrentUserObj.getUserUID()==0)	b_tmp=true;
          if(b_tmp == true) {
        	  aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
System.out.println("line 2764 searchopp aCurrentUserObj.getUserUID() is  "+aCurrentUserObj.getUserUID());    	  
              if(null == aCurrentUserObj) {
            	 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
  	          		aszLoggedInStatus="showlogin";
  	          		return;
  	          	}else{
  	          		aszLoggedInStatus="processCookieLogin 2767"  ;
  	          		return;
  	          	}
              }else{
              		aszLoggedInStatus="processCookieLogin 2771"  ;
              		return;
              }
          }
      }

      
      
      
      
      
	/**
	* allocate business rule layes object for opportunity search 
	*/
	private void allocatedOppSrchBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_SearchBRLOObj){
			m_SearchBRLOObj = new searchOpportunitiesBLO();
			m_SearchBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedOppSrchBRLO()

	/**
	* allocate business rule layes object for individual 
	*/
	private void allocatedIndBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_IndBRLOObj){
			m_IndBRLOObj = new IndividualsBRLO();
			m_IndBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedIndBRLO()

	/**
	* allocate business rule layes object for organization 
	*/
	private void allocatedOrgBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_OrgBRLOObj){
			m_OrgBRLOObj = new OrganizationBRLO();
			m_OrgBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedOrgBRLO()

	/**
	* allocate business rule layes object for organization 
	*/
	private void allocatedApplicCodesBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_AppCodesBRLOObj){
			m_AppCodesBRLOObj = new ApplicationCodesBRLO();
			m_AppCodesBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedApplicCodesBRLO()

    //====== END Private Methods ===>
    //====== END Private Methods ===>
    //====== END Private Methods ===>

	//====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>

	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private ActionHelper m_BaseHelp = new ActionHelper();
	private IndividualActionHelper m_IndActHelp = new IndividualActionHelper();
	private OrganizationActionHelper m_OrgActHelp = new OrganizationActionHelper();
	private IndividualsBRLO m_IndBRLOObj=null;
	private OrganizationBRLO m_OrgBRLOObj=null;
	private ApplicationCodesBRLO m_AppCodesBRLOObj=null;
	private searchOpportunitiesBLO m_SearchBRLOObj=null;
	private String aszSiteVersion = null;
	public String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	private int iMeetTheNeedNID = 67307;
	private int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;
	private int iLocal=4794, iVirtual=4795, iShortTerm=4796, iWorkStudy=4797;
	private int iOppBoard=4761;
	private int iLocalVolTID = 17254, iGroupFamilyTID = 17255, iVolBoardTID = 17256, iVolVirtTID = 17257, iIntrnTID = 17258, 
				iSumIntrnTID = 17259, iWorkStudyTID = 17260, iJobsTID = 17261, iConferenceTID = 17262, iConsultingTID = 17265,
				iSocJustGrpsTID = 17266,  iLocalOrgsTID = 21853,  iServiceAreaBoardTID=6217;
	private PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
	private String aszLoggedInStatus = ""; 
	private HttpSession session = null;

}