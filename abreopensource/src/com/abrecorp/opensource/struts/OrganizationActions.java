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

// Struts MVC objects
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import org.apache.struts.upload.FormFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import java.io.*;

// data transfer objects
import com.abrecorp.opensource.base.ABREAppServerDef;
import com.abrecorp.opensource.dataobj.*;
// Business Rules Layer objects
import com.abrecorp.opensource.voleng.brlayer.*;

import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class OrganizationActions extends DispatchAction {
	
	/**
	* Constructor 
	*/
	public OrganizationActions() {}
    
	public ActionForward showSocialGraphRegistration(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	getPortalInfo( httpServletRequest, httpServletResponse);
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
        allocatedIndBRLO( httpServletRequest );
		setUserType(aCurrentUserObj, httpServletRequest);
		httpServletRequest.setAttribute("org", new OrganizationInfoDTO());
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
		return actionMapping.findForward("socialGraphRegistration");
	}

    /**
    * showApiAccess page
    */
    public ActionForward showApiAccess(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
      	getPortalInfo( httpServletRequest, httpServletResponse);
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
        allocatedIndBRLO( httpServletRequest );
    	return actionMapping.findForward( "apiaccess" );
    }
    // end-of method showApiAccess()
	
	public ActionForward processSocialGraphRegistration(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      	getPortalInfo( httpServletRequest, httpServletResponse);
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
        allocatedIndBRLO( httpServletRequest );
		OrganizationInfoDTO org = new OrganizationInfoDTO();
		switch(m_OrgBRLOObj.getSocialGraphEligibility(aCurrentUserObj)) {
		case ELIGIBLE:
			this.m_OrgActHelp.getSocialGraphDataFromForm((DynaActionForm) actionForm, org);
			m_OrgBRLOObj.signSocialGraphContract(aCurrentUserObj, org);
			break;
		}
		setUserType(aCurrentUserObj, httpServletRequest);
		httpServletRequest.setAttribute("org", org);
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
		return actionMapping.findForward("socialGraphRegistration");
	}
	
	private void setUserType(PersonInfoDTO user, HttpServletRequest httpServletRequest) {
		String userType = "";
		if(user == null || user.getUserUID() <= 0) {
			userType = "notLoggedIn";
		}
		else { 
		    switch(m_OrgBRLOObj.getSocialGraphEligibility(user)) {
		    	case ALREADY_SIGNED: 
		    		userType = "alreadySigned";
		    		break;
		    	case NOT_ORG_ADMIN:
		    		userType = "notOrgAdmin";
		    		break;
		    	case ELIGIBLE:
		    		userType = "eligible";
		    		break;
		    }
		}
		httpServletRequest.setAttribute("userType", userType);
	}
	
	/*
     * show pasteforms
     */
	public ActionForward showPasteForms(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
	{
      	getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
     	return actionMapping.findForward( "pasteforms" );
	}
    // end-of method showPasteForms()
	
    /*
     * show pasteformsadv
     */
    public ActionForward showPasteFormsAdv(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
      	getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
      	return actionMapping.findForward( "pasteformsadv" );
    }
    // end-of method showPasteFormsAdv()

  	
  	/*
  	* show organization Getting Started page
  	*/
  	public ActionForward showOrgStart(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
  	{
      	getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
  	    return actionMapping.findForward( "nonpstart22" );
  	}
  	// end-of method showOrgStart()
	
    /*
     * show create organization page 1
     */
    public ActionForward showCreateOrgStep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
      	getPortalInfo( httpServletRequest, httpServletResponse);
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
        AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        getLoggedInStatus(httpServletRequest, httpServletResponse);
		if(aszLoggedInStatus.equals("showlogin")){
	    	return actionMapping.findForward( "showlogin" );
		}else if(aszLoggedInStatus.equals("processCookieLogin")){
	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
        allocatedOrgBRLO( httpServletRequest );
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			if(null != aSessDat){
				aSessDat.setTokenKey(null);
				aSessDat.setOrgNID(null);
				aSessDat.setOppNID(null);
				aSessDat.setSubdomain(null);
				aSessDat.setSiteEmail(null);
				aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEORG );
			}
			httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        int  iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
		}else if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
			if(null != aSessDat){
				aSessDat.setTokenKey(null);
				aSessDat.setOrgNID(null);
				aSessDat.setOppNID(null);
				aSessDat.setSubdomain(null);
				aSessDat.setSiteEmail(null);
			}
  			String aszGoalGA = "";
  			try{
  				aszGoalGA=httpServletRequest.getAttribute("newuser").toString();
  				//request.removeAttribute("newuser");
  			}catch(NullPointerException e){
  				aszGoalGA = "";
  			}
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
            return actionMapping.findForward( "createnonpstep1" ); 		}
		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
			return actionMapping.findForward( "showlogin" );
		}else{
            return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
    }
    // end-of method showCreateOrgStep1()

    /*
     * process create organization page 1
     */
    public ActionForward processcreatenonpstep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    	if(aszPortal.length()>0){
    		if(aszPortalNID.length()==0){
    			// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
    		}
    	}
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
        getLoggedInStatus(httpServletRequest, httpServletResponse);
      	if(aszLoggedInStatus.equals("showlogin")){
      	   	return actionMapping.findForward( "showlogin" );
      	}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	}
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     		if(null != aSessDat){
     			aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEORG );
     		}
			httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
		}else if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
	       	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	       		return actionMapping.findForward( "showlogin" );
	       	}else{
	            return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	       	}
        }
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj);
        if(0 != iRetCode){
        	loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
            return actionMapping.findForward( "createnonpstep1" );
        }
        allocatedOrgBRLO( httpServletRequest );
        // check if organization exist
        iRetCode = m_OrgBRLOObj.IsOrgNameZipCountryInSystem( aOrgInfoObj );
        if(-22 == iRetCode){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
            return actionMapping.findForward( "createnonpstep1" );
        }
        if(0 == iRetCode){
        	m_BaseHelp.setFormData(oFrm,"errormsg", "Sorry, organization name " + aOrgInfoObj.getORGOrgName() + " in " + 
           	aOrgInfoObj.getORGAddrCountryName() + ", " + 
           	aOrgInfoObj.getORGAddrPostalcode() + " already exists in system. " );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
            return actionMapping.findForward( "createnonpstep1" );
        }
     	httpServletRequest.setAttribute("org", aOrgInfoObj);

        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
        return actionMapping.findForward( "createnonpstep2" );
     }
     // end-of method processcreatenonpstep1()

 
	/*
	 * * process create organization page 2 - ali - 2006-10-11
	 * */
    public ActionForward processcreateorg(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0, iRetCode2=0;
       	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
        boolean showPortalInfo=false;
        if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
  			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
  			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
  			httpServletRequest.getHeader("host").contains("churchvol.org")	||
  			httpServletRequest.getHeader("host").contains("http://www.chrisvol.org")	||
  			httpServletRequest.getHeader("host").contains("http://chrisvol.org")	||
  			httpServletRequest.getHeader("host").contains("http://cv.org")
        ) {
  	  		showPortalInfo=true;
        }
        AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
  		if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEORG );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
  		}
  		if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
  			if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
  				return actionMapping.findForward( "showlogin" );
  			}else{
  				return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  			}
  		}
  		OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
  		iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj);
  		OrganizationDetailsInfoDTO aOrgDetInfoObj = new OrganizationDetailsInfoDTO();
  		iRetCode2 = m_OrgActHelp.getOrgDetailDataFromForm1(oFrm, aOrgDetInfoObj);
  		allocatedIndBRLO( httpServletRequest );
  		AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
  		aHeadObj.setPortal(aszPortal);
  		if(bNatlAssoc==true){
  			// lookup the tid of the orgaffil associated with the given National Association
  			iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
  		}
       
  		// ???????? what is setORGOrgAffil2TID, etc used for, then????
  		if(aOrgInfoObj.getORGAffiliation1TID()<1)	aOrgInfoObj.setORGAffiliation1TID(aHeadObj.getPortalOrgAffilTID());
  		else if(aOrgInfoObj.getORGAffiliation2TID()<1)	aOrgInfoObj.setORGAffiliation2TID(aHeadObj.getPortalOrgAffilTID());
  		else if(aOrgInfoObj.getORGAffiliation3TID()<1)	aOrgInfoObj.setORGAffiliation3TID(aHeadObj.getPortalOrgAffilTID());
  		else if(aOrgInfoObj.getORGAffiliation4TID()<1)	aOrgInfoObj.setORGAffiliation4TID(aHeadObj.getPortalOrgAffilTID());
  		else aOrgInfoObj.setORGAffiliation5TID(aHeadObj.getPortalOrgAffilTID());

  		allocatedOrgBRLO( httpServletRequest );
       
     	if(0 != iRetCode){
     		loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
     		return actionMapping.findForward( "createnonpstep2" );
     	}
	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
	   		// this has been entered through a portal
	   		aOrgInfoObj.setCurrentDomain("churchvol");
	  	}

	   	boolean bFaith=false;
	   	if(httpServletRequest.getHeader("host").contains("churchvol") ||aOrgInfoObj.getORGMembertype().equalsIgnoreCase("Church") ){
	   		bFaith=true;
	   	}
	   	iRetCode = m_OrgBRLOObj.IsOrgInfoInSystem( aOrgInfoObj );
	   	if(-22 == iRetCode){
	   		m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );
	   		loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
	   		return processcreatenonpstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse) ;
	   	}
	   	if(0 == iRetCode){
	   		m_BaseHelp.setFormData(oFrm,"errormsg", "Sorry, an organization with this name and location is already in the system" );
	   		httpServletRequest.setAttribute("org", aOrgInfoObj);
	   		loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
	   		return actionMapping.findForward( "createnonpstep2" );
	   	}
	   	aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // set the uid to allow ownership on drupal site, as well
	   	httpServletRequest.setAttribute("org", aOrgInfoObj);
	   	httpServletRequest.setAttribute("det", aOrgDetInfoObj);

	   	// generate URL Alias for the Organization:
	   	aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getORGOrgName());
	   	iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "urlalias" ) ;
	   	// convert to Organization format for organizations
	   	aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getPathAutoOrgPattern()+aOrgInfoObj.getORGUrlAlias());
	   	// check to see if this URL exists in the system... if so, keep trying to append -# to the url alias until there is no record found for it.
	   	iRetCode = m_OrgBRLOObj.IsURLAliasInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
    
	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
	   		// this has been entered through a portal
	   		aOrgInfoObj.setCurrentDomain("churchvol");
	  	}
	   	iRetCode = m_OrgBRLOObj.createOrganization( aOrgInfoObj );
	   	m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
	   	if(aszPortal.length()>0){
           // user created the portal; this user should also be updated to be associated with that portal; may use portal tax for emails, etc
    	   aCurrentUserObj.setPortal(aOrgInfoObj.getPortalTID());
           iRetCode = m_IndBRLOObj.updateIndividualHead( aCurrentUserObj, aszSiteVersion );
    	   
    	   // user owns the org; they can favorite orgs and opps
           int iFavoriteUID = aOrgInfoObj.getORGUID();
           iRetCode = m_OrgActHelp.getOrgFavoritesFromForm(oFrm, aOrgInfoObj);
           iRetCode = m_OrgBRLOObj.favoriteOrgs( aOrgInfoObj );
	   	}
	   	// if the number served or number of volunteers = 0, set equal to what was
	   	// entered in the normal org profile
	   	if(0 == aOrgDetInfoObj.getDETNumServed())	aOrgDetInfoObj.setDETNumServed(aOrgInfoObj.getORGNumServed());        	 
	   	if(0 == aOrgDetInfoObj.getDETNumVolOrg())	aOrgDetInfoObj.setDETNumVolOrg(aOrgInfoObj.getORGNumVolOrg());
	   	if(aOrgDetInfoObj.getDETFedTaxId().equalsIgnoreCase(""))	aOrgDetInfoObj.setDETFedTaxId(aOrgInfoObj.getORGFedTaxId1(), aOrgInfoObj.getORGFedTaxId2());

	   	m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );
     	if(0 != iRetCode){
     		loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
     		return actionMapping.findForward( "createnonpstep2" );
     	}
     	String aszOrgNid=aOrgInfoObj.getORGNID()+"";
     	session.setAttribute("orgmanagementnid", aszOrgNid);
		if(aOrgInfoObj.getPortalNameModified().length() > 0){
			if(bFaith==true){
				httpServletRequest.setAttribute("redirectpage", AppSessionDTO.TOKEN_REDIR_CHURCH_PORTAL_OPPORTUNITIES);
			}
		    return actionMapping.findForward( "mappingpage" );
		}
		aSessDat.setOrgNID( aszOrgNid );
		return showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    }
    // end-of method processcreateorg()

    /*
     * show manage national affiliation/association page
     */
    public ActionForward showAssocManagement(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
        DynaActionForm oFrm = (DynaActionForm)actionForm;
    	boolean bNatlAssoc = false;
    	if(aszPortalRequestType.equals("natlassoc")){
    		bNatlAssoc=true;
    	}else{
    		m_BaseHelp.setFormData(oFrm,"errormsg", "The Current Portal does not exist as a National Association. Please proceed to manage through http://www.christianvolunteering.org/orgmanagement.jsp" );
         	return actionMapping.findForward( "noaccess" );
    	}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
	    if(aszPortal.length()>0){
	    	if(aszPortalNID.length()==0){
	    		return actionMapping.findForward("404");
	        }
	    }
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        if(iOrgNid < 1){
       		aszOrgNid = "" + aSessDat.getOrgNID();
        }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  			  if(null != aSessDat){
  				  aSessDat.setOrgNID( aszOrgNid );
  				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
  			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
        }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                 return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
        }
        
        int iPortalNID = m_BaseHelp.convertToInt( aszPortalNID );
        int[] iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray();
        // see if portalNID is in this array; if not, then the user cannot manage the national Association.
        boolean bIsNatlAssocManager = false;
        for(int i=0; i<iNatlAssocNIDs.length; i++){
        	if(iNatlAssocNIDs[i]==iPortalNID)	bIsNatlAssocManager=true;
        }
        
        // if is not natl assoc manager, then the user does not have access
        if(bIsNatlAssocManager==false){
	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this association,\r\nor the association does not exist" );
	          	return actionMapping.findForward( "noaccess" );
        }
        
        // Organization list for user
        allocatedOrgBRLO( httpServletRequest );
        ArrayList aAssocOrgList = new ArrayList();
        ArrayList aList = new ArrayList();
        ArrayList aListAdmin = new ArrayList();
        ArrayList aListContact = new ArrayList();

        if(iPortalNID>0){
            iRetCode = m_OrgBRLOObj.getOrgListForAssociation( aAssocOrgList, iPortalNID, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC); 
        }
        
        int iNatlAssocNID = iPortalNID; //aCurrentUserObj.getNatlAssociationNID();
        if(iNatlAssocNID>0){
//            iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aCurrentUserObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC); 
        }
        if(iRetCode!=0){
        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        }

  	    OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
//        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // This may be an error check that the user MUST HAVE ownership of the said organization
        // instead, we really just want to make sure the current owner is able to manage the association, but this is already handled in the above code
        // in line ~542 where it checks for bIsNatlAssocManager; if the user is NOT bIsNatlAssocManager, then it already won't load, anyway...
        // will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
       	iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );

        aOrgInfoObj.setRequestType(aszPortalRequestType);
        httpServletRequest.setAttribute("org", aOrgInfoObj);
        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        httpServletRequest.setAttribute( "orglist", aList );
        httpServletRequest.setAttribute( "assocorglist", aAssocOrgList );

        // need to load National Affiliation name and tid, as well as Organization Affiliation name so we can display this info too on assocmanagement
        String aszNationalAssociationFull="";
        String aszNatlAssoc = aCurrentUserObj.getNatlAssociation();
        String[] a_aszNationalAssocationPortals = aCurrentUserObj.getUSPNatlAssociationPortalsArray();
        for(int i=0; i<a_aszNationalAssocationPortals.length; i++){
        	aszNationalAssociationFull = a_aszNationalAssocationPortals[i];
        	int iIndexOfSemicolon = aszNationalAssociationFull.indexOf(";");
        	if(iIndexOfSemicolon>-1 && iIndexOfSemicolon<aszNationalAssociationFull.length()+1){
	        	String aszNationalAssociationPortal = aszNationalAssociationFull.substring(0,iIndexOfSemicolon);
	        	String aszNationalAssociationTitle = aszNationalAssociationFull.substring(iIndexOfSemicolon+1);
	        	if(aszNationalAssociationPortal.equalsIgnoreCase(aszPortal)){
	        		aszNatlAssoc = aszNationalAssociationTitle;
	        	}
        	}
        }
        httpServletRequest.setAttribute( "natlassocname", aszNatlAssoc );
        httpServletRequest.setAttribute( "natlassocnid", iPortalNID );

      	if(null != aSessDat){
         	aSessDat.setTokenKey(null);
         	aSessDat.setOrgNID(null);
         	aSessDat.setOppNID(null);
         	aSessDat.setSubdomain(null);
         	aSessDat.setSiteEmail(null);
     	}
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
      	return actionMapping.findForward( "assocmanagement" );
    }
    // end-of method showAssocManagement()

    public ActionForward showIndAcctSum2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    public ActionForward showOrgSumm1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    /*
     * show manage organization page
     */
    public ActionForward showOrgManagement(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    // end-of method showOrgManagement()
        
       	/*
         * show showOrgManageListings organization page
         */
         public ActionForward showOrgManageListings(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
 			boolean bNatlAssoc = false;
 			if(aszPortalRequestType.equals("natlassoc")){
 				bNatlAssoc=true;
 			}
 			// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
 	        if(aszPortal.length()>0){
 	        	if(aszPortalNID.length()==0){
 	        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
 	    			return actionMapping.findForward("404");
 	        	}
 	        }
 	        boolean bOrgContactOnly = false;
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
            boolean bByContact = false;
            String aszReqTypeSessionVar =  "";
            try{
            	aszReqTypeSessionVar =  session.getAttribute("requesttype").toString();
            }catch(Exception e){}
            String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
            if(aszReqType.contains("ByContact")){
           	 bByContact = true;
            }else if(aszReqTypeSessionVar.contains("ByContact")){
           	 bByContact = true;
            }
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
             boolean bAdminRole=false;
             String aszRole = m_BaseHelp.getFormData(oFrm,"role");
             if(! (aszRole==null || aszRole.equals(null))){
            	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
              	bAdminRole=true;
            	 }else{
            		 aszRole="";
            	 }
             }
             String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
             int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
             if(iOrgNid < 1){
            	 iOrgNid = aSessDat.getOrgNID();
             }

     		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( iOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }
             
             allocatedOrgBRLO( httpServletRequest );

             loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);

             if(iOrgNid==0){
   	    	    Iterator<OrganizationInfoDTO> itr_aList = aOrgList.iterator();
   	    	    if(itr_aList.hasNext()){
   	  	            OrganizationInfoDTO orgElement_aList=new OrganizationInfoDTO();
   	    	    	orgElement_aList = itr_aList.next();
   	    	    	iOrgNid = orgElement_aList.getORGNID();
   	    	    }
             }
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( iOrgNid );
             aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
             allocatedOrgBRLO( httpServletRequest );
             

             int iNatlAssocNID = 0;
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
             
             // if b_AssocManager is true, then it should by default load the orgnid that's associated with that natl assoc instead - ??? not sure 2014-01-22
             
             // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
             if(aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            	aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
             ){
            	 aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
             }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//           	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              	 int iTid = aOrgInfoObj.getORGAffiliation1TID();
              	 if(iTid > 0){
              		 aOrgInfoObj.setORGNID( iOrgNid );
              		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              	 }
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }
             if(iRetCode == -111){
            	 if(b_AssocManager==false &&
            			 (! (aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN) && aszRole.equals(PersonInfoDTO.AUTH_ADMIN)))	
            	){
            		 // if it failed, try to load via contact
            		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                     if(iRetCode==0){// they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	 bOrgContactOnly=true;
                     }else if(iRetCode == -111){
     	             	if(bAdminRole==true){
     	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
     	     	          	return actionMapping.findForward( "noaccess" );
     	             	}else{
     	             		// org did not exist
     	                 	return actionMapping.findForward( "404" );
     	             	}
                      }else{ // they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	  bOrgContactOnly=true;
                      }
            	 }else{
	            	 iOrgNid=aCurrentUserObj.getORGNID();
	                 aOrgInfoObj.setORGNID( iOrgNid );
	                 // try loading again, this time with an orgnid that DOES load for the user            	 
	                 if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
	                    	aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
	                 ){
	                	 aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
	                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
	                 }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	                  	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//               	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                  	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                  	 int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                  	 if(iTid > 0){
	                  		 aOrgInfoObj.setORGNID( iOrgNid );
	                  		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                  	 }
	                 }else{
	                	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	                 }
	                 if(iRetCode == -111){
		             	if(bAdminRole==true){
		     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
		     	          	return actionMapping.findForward( "noaccess" );
		             	}else{
		             		// org did not exist
		                 	return actionMapping.findForward( "404" );
		             	}
	                 }
            	 }
             }
             
             httpServletRequest.setAttribute( "org", aOrgInfoObj );

         	if(null != aSessDat){
            	aSessDat.setTokenKey(null);
            	aSessDat.setOrgNID(null);
            	aSessDat.setOppNID(null);
            	aSessDat.setSubdomain(null);
            	aSessDat.setSiteEmail(null);
        	}
/*            
            ArrayList aUserList = new ArrayList();
            iRetCode = m_OrgBRLOObj.getOrgContactList( aUserList, aOrgInfoObj.getORGNID(), "org" );
            //         iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
            httpServletRequest.setAttribute( "userlist", aUserList );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
*/
            ArrayList aList = new  ArrayList ( 2 );
            if(bOrgContactOnly==false || aszReqTypeSessionVar.contains("ByContact")){
            	iRetCode = m_OrgBRLOObj.getOppListForOrg( aList, aOrgInfoObj.getORGNID(), OrganizationInfoDTO.LOADBY_ORG_ORDER_EXPIRATION );// might want to make sure that the admin/contact arrays get set
            }else{
                boolean b_inList=false;
                int iNID=0, iNIDincluded=0;
                ArrayList aOppListContact = new  ArrayList ( 2 );
                iRetCode = m_OrgBRLOObj.getOppListForContact( aOppListContact, aCurrentUserObj.getUserUID() );
          	    Iterator<OrgOpportunityInfoDTO> itrOppContact = aOppListContact.iterator();
            	    while (itrOppContact.hasNext()) {
            	    	b_inList=false;
            	    	OrgOpportunityInfoDTO oppElement = itrOppContact.next();
            	    	iNID = oppElement.getOPPNID();
            	    	if(oppElement!=null){
            	    		OrgOpportunityInfoDTO oppElement_aOppList=new OrgOpportunityInfoDTO();
            	    	    Iterator<OrgOpportunityInfoDTO> itr_aOppList = aList.iterator();
            	    	    while(itr_aOppList.hasNext()){
            	    	    	oppElement_aOppList = itr_aOppList.next();
            	    	    	iNIDincluded = oppElement_aOppList.getOPPNID();
            	    	    	if(iNID==iNIDincluded){
            	    	    		b_inList=true;
            	    	    	}
            	    	    }
            	    	    if(b_inList==false && oppElement.getORGNID()==iOrgNid){
            	    	    	aList.add(oppElement);
            	    	    }
            	    	}
            	    }
            	
            }
            httpServletRequest.setAttribute( "opplist", aList ); // over-ride the opplist from loadOrgsOppsLists with the list that orders by expiration
            
             return actionMapping.findForward( "orgmanagelistings" );
         }
         // end-of method showOrgManageListings()

         /*
        * show organization view page (managing org)
        */
        public ActionForward shownonpvieworg(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	httpServletRequest.setAttribute("orgBRLO", m_OrgBRLOObj);
        	int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
	        boolean bOrgContactOnly = false;
         	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
            String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            if(iOrgNid < 1){
         		aszOrgNid = "" + aSessDat.getOrgNID();
             	if(iOrgNid < 1){
    	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	             	return actionMapping.findForward( "showlogin" );
    	         	}else{
    	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	         	}
             	}
            }
    		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			  if(null != aSessDat){
    				  aSessDat.setOrgNID( aszOrgNid );
    				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWORG );
    			  }
    				if(aCurrentUserObj.getUserProfileNID() < 1){ 
    					// this user is an old drupal user-only; need to take through partial account creation process
    					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    					//		as well as an insert into the rails db
    					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    				}
    		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
    	         	return actionMapping.findForward("mappingpage");
    		 }
            if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                	return actionMapping.findForward( "showlogin" );
            	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
            }
            boolean bAdminRole=false;
            String aszRole = m_BaseHelp.getFormData(oFrm,"role");
            if(! (aszRole==null || aszRole.equals(null))){
           	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
             	bAdminRole=true;
           	 }else{
           		 aszRole="";
           	 }
            }
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( aszOrgNid );
             aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
             allocatedOrgBRLO( httpServletRequest );

             int iNatlAssocNID = 0;
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
             
             // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
             if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
             ){
           	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
             }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//           	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
               int iTid = aOrgInfoObj.getORGAffiliation1TID();
               if(iTid > 0){
              	 aOrgInfoObj.setORGNID( iOrgNid );
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
               }
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }
             if(iRetCode == -111){
            	 if(b_AssocManager==false &&
            			 (! (aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN) && aszRole.equals(PersonInfoDTO.AUTH_ADMIN)))	
            	){
            		 // if it failed, try to load via contact
            		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                     if(iRetCode==0){// they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	 bOrgContactOnly=true;
                     }else if(iRetCode == -111){
     	             	if(bAdminRole==true){
     	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
     	     	          	return actionMapping.findForward( "noaccess" );
     	             	}else{
     	             		// org did not exist
     	                 	return actionMapping.findForward( "404" );
     	             	}
                      }else{ // they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	  bOrgContactOnly=true;
                      }
            	 }else{
	             	if(bAdminRole==true){
	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	     	          	return actionMapping.findForward( "noaccess" );
	             	}else{
	             		// org did not exist
	                 	return actionMapping.findForward( "404" );
	             	}
            	 }
             }
        	httpServletRequest.setAttribute("org", aOrgInfoObj);
            m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        	if(null != aSessDat){
            	aSessDat.setTokenKey(null);
            	aSessDat.setOrgNID(null);
            	aSessDat.setOppNID(null);
            	aSessDat.setSubdomain(null);
            	aSessDat.setSiteEmail(null);
        	}

            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( "viewnonpstep11" );
        }
        // end-of method shownonpvieworg()

        
       	/*
         * show showOrgShareOnWebsite organization page
         */
         public ActionForward showOrgShareOnWebsite(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
 			boolean bNatlAssoc = false;
 			if(aszPortalRequestType.equals("natlassoc")){
 				bNatlAssoc=true;
 			}
 			// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
 	        if(aszPortal.length()>0)
 	        	if(aszPortalNID.length()==0)
 	    			return actionMapping.findForward("404");
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
             boolean bAdminRole=false;
             String aszRole = m_BaseHelp.getFormData(oFrm,"role");
             if(! (aszRole==null || aszRole.equals(null))){
            	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN))
            		 bAdminRole=true;
            	 else
            		 aszRole="";
             }
             String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
             int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
             if(iOrgNid < 1)
          		aszOrgNid = "" + aSessDat.getOrgNID();

     		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( aszOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login")  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	else
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             }
             
             allocatedOrgBRLO( httpServletRequest );
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( aszOrgNid );
             aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
             allocatedOrgBRLO( httpServletRequest );

             int iNatlAssocNID = 0;
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
             
             // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
             if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
             ){
           	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
             }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//           	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
               int iTid = aOrgInfoObj.getORGAffiliation1TID();
               if(iTid > 0){
              	 aOrgInfoObj.setORGNID( iOrgNid );
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
               }
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }
  	        boolean bOrgContactOnly = false;
             if(iRetCode == -111){
            	 if(b_AssocManager==false &&
            			 (! (aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN) && aszRole.equals(PersonInfoDTO.AUTH_ADMIN)))	
            	){
            		 // if it failed, try to load via contact
            		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                     if(iRetCode==0){// they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	 bOrgContactOnly=true;
                     }else if(iRetCode == -111){
     	             	if(bAdminRole==true){
     	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
     	     	          	return actionMapping.findForward( "noaccess" );
     	             	}else{
     	             		// org did not exist
     	                 	return actionMapping.findForward( "404" );
     	             	}
                      }else{ // they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	  bOrgContactOnly=true;
                      }
            	 }
             }
            	 ArrayList aList = new  ArrayList ( 2 );
                 if(bOrgContactOnly==false){
                 	iRetCode = m_OrgBRLOObj.getOppListForOrg( aList, aOrgInfoObj.getORGNID(), OrganizationInfoDTO.LOADBY_ORG_ORDER_EXPIRATION );// might want to make sure that the admin/contact arrays get set
                 }else{
                     boolean b_inList=false;
                     int iNID=0, iNIDincluded=0;
                     ArrayList aOppListContact = new  ArrayList ( 2 );
                     iRetCode = m_OrgBRLOObj.getOppListForContact( aOppListContact, aCurrentUserObj.getUserUID() );
               	    Iterator<OrgOpportunityInfoDTO> itrOppContact = aOppListContact.iterator();
                 	    while (itrOppContact.hasNext()) {
                 	    	b_inList=false;
                 	    	OrgOpportunityInfoDTO oppElement = itrOppContact.next();
                 	    	iNID = oppElement.getOPPNID();
                 	    	if(oppElement!=null){
                 	    		OrgOpportunityInfoDTO oppElement_aOppList=new OrgOpportunityInfoDTO();
                 	    	    Iterator<OrgOpportunityInfoDTO> itr_aOppList = aList.iterator();
                 	    	    while(itr_aOppList.hasNext()){
                 	    	    	oppElement_aOppList = itr_aOppList.next();
                 	    	    	iNIDincluded = oppElement_aOppList.getOPPNID();
                 	    	    	if(iNID==iNIDincluded){
                 	    	    		b_inList=true;
                 	    	    	}
                 	    	    }
                 	    	    if(b_inList==false && oppElement.getORGNID()==iOrgNid){
                 	    	    	aList.add(oppElement);
                 	    	    }
                 	    	}
                 	    }
                 	
                 }
             httpServletRequest.setAttribute( "org", aOrgInfoObj );
             httpServletRequest.setAttribute( "opplist", aList );

         	if(null != aSessDat){
            	aSessDat.setTokenKey(null);
            	aSessDat.setOrgNID(null);
            	aSessDat.setOppNID(null);
            	aSessDat.setSubdomain(null);
            	aSessDat.setSiteEmail(null);
        	}
            
            ArrayList aUserList = new ArrayList();
            iRetCode = m_OrgBRLOObj.getOrgContactList( aUserList, aOrgInfoObj.getORGNID(), "org" );
            //         iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
            httpServletRequest.setAttribute( "userlist", aUserList );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );

            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( "shareonwebsite" );
         }
         // end-of method showOrgShareOnWebsite()

        /*
         * show organization edit page 1
         */
         public ActionForward shownonpeditstep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
             String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
             int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
             if(iOrgNid < 1){
          		aszOrgNid = "" + aSessDat.getOrgNID();
              	if(iOrgNid < 1){
     	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
     	             	return actionMapping.findForward( "showlogin" );
     	         	}else{
     	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	         	}
              	}
             }
     		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( aszOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }
             boolean bAdminRole=false;
             String aszRole = m_BaseHelp.getFormData(oFrm,"role");
             if(! (aszRole==null || aszRole.equals(null))){
            	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
              	bAdminRole=true;
            	 }else{
            		 aszRole="";
            	 }
             }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              

              int iNatlAssocNID = 0;
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
              
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );
              // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
            	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
                	 // first, load the National Association, to get the OrgAffil tid that it manages
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
                 if(iTid > 0){
                	 aOrgInfoObj.setORGNID( iOrgNid );
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                 }
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }

              if(iRetCode == -111){
             	 if(b_AssocManager==false &&
            			 (! (aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN) && aszRole.equals(PersonInfoDTO.AUTH_ADMIN)))	
            	){
            		 // if it failed, try to load via contact
            		 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                     if(iRetCode==0){// they successfully loaded the org, but only by the uid as an ORG CONTACT
                    	 return shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                     }else if(iRetCode == -111){
     	             	if(bAdminRole==true){
     	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
     	     	          	return actionMapping.findForward( "noaccess" );
     	             	}else{
     	             		// org did not exist
     	                 	return actionMapping.findForward( "404" );
     	             	}
                      }else{ // they successfully loaded the org, but only by the uid as an ORG CONTACT
                     	 return shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                      }
            	 }else{
	             	if(bAdminRole==true){
	     	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	     	          	return actionMapping.findForward( "noaccess" );
	             	}else{
	             		// org did not exist
	                 	return actionMapping.findForward( "404" );
	             	}
            	 }
              }
         	httpServletRequest.setAttribute("org", aOrgInfoObj);
             m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );

             String aszReqType = httpServletRequest.getParameter("reqtype");
             if(null == aszReqType) aszReqType="";
             if(aszReqType.equalsIgnoreCase("edit")){
             	if(null != aSessDat){
                 	aSessDat.setTokenKey(null);
                 	aSessDat.setOrgNID(null);
                 	aSessDat.setOppNID(null);
                 	aSessDat.setSubdomain(null);
                 	aSessDat.setSiteEmail(null);
             	}

             }
             loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
             return actionMapping.findForward( "editnonpstep1" );
         }
         // end-of method shownonpeditstep1(

         
     	/*
         * process EDIT organization page 1
         */
         public ActionForward processEditOrg(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0,iRetCode2=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }

        	  boolean showPortalInfo=false;
        	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
        			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
        			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
        			httpServletRequest.getHeader("host").contains("churchvol.org")	||
          			httpServletRequest.getHeader("host").contains("http://www.chrisvol.org")	||
          			httpServletRequest.getHeader("host").contains("http://chrisvol.org")	||
        			httpServletRequest.getHeader("host").contains("http://cv.org")
        	  ) {
        	  	showPortalInfo=true;
        	  }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
             String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
          	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          	if(iOrgNid < 1){
          		aszOrgNid = "" + aSessDat.getOrgNID();
              	if(iOrgNid < 1){
     	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
     	             	return actionMapping.findForward( "showlogin" );
     	         	}else{
     	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	         	}
              	}
          	}
     		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( aszOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }

       	  boolean bAdminRole=false;
     	  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
     	  if(! (aszRole==null || aszRole.equals(null))){
     		  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
     			  bAdminRole=true;
     		  }else{
     			  aszRole="";
     		  }
     	  }
           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           aOrgInfoObj.setORGNID( aszOrgNid );
           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
           allocatedOrgBRLO( httpServletRequest );

           int iNatlAssocNID = 0;
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
           
           // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
           if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
         		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
           ){
         	  aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
           }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
          	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
           int iTid = aOrgInfoObj.getORGAffiliation1TID();
           if(iTid > 0){
          	 aOrgInfoObj.setORGNID( iOrgNid );
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
           }
         }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }

           if(iRetCode == -111){
         	  if(bAdminRole==true || b_AssocManager==true){
         		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
         		  return actionMapping.findForward( "noaccess" );
         	  }else{
         		  // org did not exist
         		  return actionMapping.findForward( "404" );
         	  }
           }
             if(0 != iRetCode){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
         	}
         	httpServletRequest.setAttribute("org", aOrgInfoObj);
         	int iType=0;
         	if(bNatlAssoc==true){
         		iType=OrganizationInfoDTO.LOADBY_NATL_ASSOC;
         	}
             iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj, iType);

          	aOrgInfoObj.setORGUrlAliasOrig(aOrgInfoObj.getORGUrlAlias());
          	// generate URL Alias for the Organization:
          	aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getORGOrgName());
             iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "urlalias" ) ;
             // convert to Organization format for organizations
             aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getPathAutoOrgPattern()+aOrgInfoObj.getORGUrlAlias());
             String atmp = aOrgInfoObj.getORGUrlAlias();
             if(aOrgInfoObj.getORGUrlAlias().equalsIgnoreCase(aOrgInfoObj.getORGUrlAliasOrig())){
            	 // no change needs to be made; don't edit the url alias for this org
             	aOrgInfoObj.setORGUrlAlias("");
             }else{
                 // check to see if this URL exists in the system... if so, keep trying to append -# to the url alias until there is no record found for it.
                 iRetCode = m_OrgBRLOObj.IsURLAliasInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
             }

             //String 
             aszPortal = "";
     		if(httpServletRequest.getParameter("portal") != null){
     			aszPortal = httpServletRequest.getParameter("portal");
     		}
     		if(aszPortal.length()>0){
     	        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "portalpath" ) ;
     	        if(! aOrgInfoObj.getPortalNameModified().equals(aOrgInfoObj.getPortalNameOrig())){
     	            iRetCode = m_OrgBRLOObj.IsPortalNameInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
     	        }
     	        if(aOrgInfoObj.getPortalNameModified().length()<1){
     	        	aOrgInfoObj.setPortalNameModified(aOrgInfoObj.getPortalNameOrig());
     	        }
     			if(session.getAttribute(aszPortal + "_banner") != null){
     				// always reads in the original value, b/c the form post does not use the portal... - ???
     				String aszTempBanner = session.getAttribute(aszPortal + "_banner").toString(); 
     				if(aszTempBanner.length()<1 || aOrgInfoObj.getPortalBannerURL().equals(aszTempBanner)){
     					if(session.getAttribute("fileName")!= null){
     						aszTempBanner = session.getAttribute("fileName").toString();
     					}
     				}
     				if(! aszTempBanner.equals(aOrgInfoObj.getPortalBannerURL())){
     					aOrgInfoObj.setPortalBannerURL(aszTempBanner);
     					//aOrgInfoObj.setPortalBannerURL(aszPortal + "_banner");
     				}
     			}else if(session.getAttribute("fileName")!= null){
     	        	if(session.getAttribute("fileName").toString().length()>0){
     	        		aOrgInfoObj.setPortalBannerURL(session.getAttribute("fileName").toString());
     	        	}
     			}
     		}        
     	     // initial case of not having a portal set yet; need to still grab the banner image url from the session to write to db.  
     	     // since portal doesn't exist, can't look for attribute of portal+_banner
     		 // need to look instead at  session.setAttribute("fileName",fileName);
     		else if(showPortalInfo==true && aOrgInfoObj.getPortalNameModified().length()>0){
     	     	if(session.getAttribute("fileName")!= null){
     		        	if(session.getAttribute("fileName").toString().length()>0){
     		        		aOrgInfoObj.setPortalBannerURL(session.getAttribute("fileName").toString());
     		        	}
     	     	}
     			aszPortal = aOrgInfoObj.getPortalNameModified();
     	        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "portalpath" ) ;
     	        if(! aOrgInfoObj.getPortalNameModified().equals(aOrgInfoObj.getPortalNameOrig())){
     	            iRetCode = m_OrgBRLOObj.IsPortalNameInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
     	        }
     	     }

             
       	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
         		// this has been entered through a portal
       	   		aOrgInfoObj.setCurrentDomain("churchvol");
     	  	}
             iRetCode = m_OrgBRLOObj.updateOrganization( aOrgInfoObj );
             
      		if(aszPortal.length()>0 && bNatlAssoc==false && iRetCode==0){
             	iRetCode = m_OrgActHelp.getPortalDataFromForm1(oFrm, aOrgInfoObj);
                iRetCode = m_OrgBRLOObj.updatePortal( aOrgInfoObj );
                // clear session attributes for portal style, etc
                 session.removeAttribute(aszPortal + "_header_tags");
                 session.removeAttribute(aszPortal + "_header");
                 session.removeAttribute(aszPortal + "_css");
                 session.removeAttribute(aszPortal + "_footer");
      	       if(aszPortal.length()>0){
      	           String aszOppNIDs = "";
      	           // user owns the org; they can favorite orgs and opps
      	           int iFavoriteUID = aOrgInfoObj.getORGUID();
      	           iRetCode = m_OrgActHelp.getOrgFavoritesFromForm(oFrm, aOrgInfoObj);
      	           iRetCode = m_OrgBRLOObj.favoriteOrgs( aOrgInfoObj );
      	           // prompted user to ask if this, as well... if so, then proceed through all of these child opps
      	           if(aOrgInfoObj.getFavoriteChildOpps() == true){
      		           	// do select of all published child opps of given orgs (nids in array), and load these oppnids in the opp array
      		           	iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
      		           	aOrgInfoObj.setORGFavoritedOPPNIDsArray(aOrgInfoObj.getORGChildOPPNIDsArray());
      		           	iRetCode = m_OrgBRLOObj.favoriteOpps( aOrgInfoObj );
      	           }  
      	       }
             }
             m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );
             if(0 != iRetCode){
 	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "editnonpstep1" );
             }
             session.setAttribute("orgmanagementnid", aszOrgNid);
         	if(bAdminRole==true){
             	return showAdminOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
         	}else{
         		if(bNatlAssoc==true){
         			httpServletRequest.setAttribute("redirectpage", "portalassocmanagement");
         		     httpServletRequest.setAttribute("redirectportal", aszPortal);
     				return actionMapping.findForward( "mappingpage" );
         		}
         		
         		if(aOrgInfoObj.getPortalNameModified().length() > 0){
         			//*******************might have to return a mapping page instead, if in a portal.  the mapping page will then handle the potentially modified portal name
         			httpServletRequest.setAttribute("redirectpage", AppSessionDTO.TOKEN_REDIR_PORTAL_ORGMANAGEMENT);
         		     httpServletRequest.setAttribute("redirectportal", aOrgInfoObj.getPortalNameModified());
         			// need to somehow pass portal Name as well
     				return actionMapping.findForward( "mappingpage" );
         		}
         		
             	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
         	}
         }
         // end-of method processEditOrg()
         
         
         
      	/*
          * delete non profit
          */
          public ActionForward deleteNonProfit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
          	int iRetCode=0, iRetCode2=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
           	
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
              String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
              int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
              if(iOrgNid < 1){
           		aszOrgNid = "" + aSessDat.getOrgNID();
               	if(iOrgNid < 1){
      	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
      	             	return actionMapping.findForward( "showlogin" );
      	         	}else{
     	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	         	}
               	}
              }
      		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
      			  if(null != aSessDat){
      				  aSessDat.setOrgNID( aszOrgNid );
      				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
      			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
      		 }
              if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
              	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                  	return actionMapping.findForward( "showlogin" );
              	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              	}
              }
          	int iUID = aCurrentUserObj.getUserUID();
     	   	  boolean bAdminRole=false;
     		  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
     		  if(! (aszRole==null || aszRole.equals(null))){
     			  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
     				  bAdminRole=true;
     			  }else{
     				  aszRole="";
     			  }
     		  }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              OrganizationDetailsInfoDTO aOrgDetailsInfoObj = new OrganizationDetailsInfoDTO();
              aOrgDetailsInfoObj.setDETOrgNID( aszOrgNid );
               allocatedOrgBRLO( httpServletRequest );

               int iNatlAssocNID = 0;
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
               
               // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
               if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
             		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
             	  aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
               }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
                 if(iTid > 0){
                	 aOrgInfoObj.setORGNID( iOrgNid );
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                 }
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }

               if(iRetCode == -111){
             	  if(bAdminRole==true){
             		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
             		  return actionMapping.findForward( "noaccess" );
             	  }else{
             		  // org did not exist
             		  return actionMapping.findForward( "404" );
             	  }
               }
              httpServletRequest.setAttribute( "org", aOrgInfoObj );
              // check if login is owner of non-profit
              ArrayList aOppList = new  ArrayList ( 2 );
              m_OrgBRLOObj.getOppListForOrg( aOppList, iOrgNid );
              if(aOppList.size() > 0){
              	aOrgInfoObj.appendErrorMsg("You must delete all opportunities before deleting your organization");
                  m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
  	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "editnonpstep1" );
              }
              // delete organization detail
              iRetCode = m_OrgBRLOObj.deleteOrgDetail( aCurrentUserObj, aOrgDetailsInfoObj );
              if(0 != iRetCode){
              	aOrgInfoObj.appendErrorMsg("error deleting non profit");
                  m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
  	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "editnonpstep1" );
          	}
              // delete organization
              iRetCode = m_OrgBRLOObj.deleteNonProfit( aCurrentUserObj, aOrgInfoObj );
              if(0 != iRetCode){
              	aOrgInfoObj.appendErrorMsg("error deleting non profit");
                  m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
  	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "editnonpstep1" );
          	}
//              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationDetailsInfoDTO.LOADBY_ORGNID );
              httpServletRequest.setAttribute( "org", aOrgInfoObj );
              
              allocatedIndBRLO( httpServletRequest );
              iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
              allocatedOrgBRLO( httpServletRequest );
              ArrayList aList = new ArrayList();
              iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aCurrentUserObj.getUserUID()); 
              httpServletRequest.setAttribute( "orglist", aList );

              // get individual data from web form 
              iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aCurrentUserObj);
              if(aCurrentUserObj.getUserUID() < 1){ // to catch the case where a user's uid might have been cleared to 0 b/c it's not in the form above
              	aCurrentUserObj.setUserUID(iUID);
              }
              if(iRetCode != 0){
                	m_BaseHelp.setFormData(oFrm,"errormsg", aCurrentUserObj.getErrorMsg() );
              	return actionMapping.findForward( "showregistration" );
              }
              
              iRetCode = m_IndBRLOObj.loadUserByOption( aCurrentUserObj ,aCurrentUserObj.LOADBY_UID, aszSiteVersion);//re-load the user again to get an updated orgnid that does exist
              iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aCurrentUserObj);
              
              aSessDat.setOrgNID(aCurrentUserObj.getORGNID());
          	m_BaseHelp.setFormData(oFrm, "orgnid",""+aCurrentUserObj.getORGNID() );
              // forward to summary screen
          	if(bAdminRole==true){
             	return showAdminOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
         	}
         	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
          }
          // end-of method deleteNonProfit()

          
       	/*
           * showCVInternManage 
           */
           public ActionForward showCVInternManage(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
           	int iRetCode=0,iRetCode2=0;
            	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
      		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
              		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
          			return actionMapping.findForward("404");
              	}
              }

          	  boolean showPortalInfo=false;
          	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
          			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
          			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
          			httpServletRequest.getHeader("host").contains("churchvol.org")	||
            			httpServletRequest.getHeader("host").contains("http://www.chrisvol.org")	||
            			httpServletRequest.getHeader("host").contains("http://chrisvol.org")	||
          			httpServletRequest.getHeader("host").contains("http://cv.org")
          	  ) {
          	  	showPortalInfo=true;
          	  }
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	DynaActionForm oFrm = (DynaActionForm)actionForm;
             	getLoggedInStatus(httpServletRequest, httpServletResponse);
          		if(aszLoggedInStatus.equals("showlogin")){
          	    	return actionMapping.findForward( "showlogin" );
          		}else if(aszLoggedInStatus.equals("processCookieLogin")){
          	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		}
            //   String aszNatlAssocNID = m_BaseHelp.getFormData(oFrm,"nid");
           	int iNatlAssocNID = 434466;
         //  	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            	int iOrgNid = 434466;
            	if(iOrgNid < 1){
            		//aszOrgNid = "" + aSessDat.getOrgNID();
                	if(iOrgNid < 1){
       	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
       	             	return actionMapping.findForward( "showlogin" );
       	         	}else{
       	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	         	}
                	}
            	}
       		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
       			  if(null != aSessDat){
       				  aSessDat.setOrgNID( iOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
       			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
       		 }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
               }

         	  boolean bAdminRole=false;
       	  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
       	  if(! (aszRole==null || aszRole.equals(null))){
       		  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
       			  bAdminRole=true;
       		  }else{
       			  aszRole="";
       		  }
       	  }
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( iOrgNid );
             aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
             allocatedOrgBRLO( httpServletRequest );

             iNatlAssocNID = 0;
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
             
             // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
             // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
             if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
             ){
           	  aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
             }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//           	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
           }

             if(iRetCode == -111){
           	  if(bAdminRole==true){
           		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
           		  return actionMapping.findForward( "noaccess" );
           	  }else{
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
           	  }
             }
             if(0 != iRetCode){
          		  // org did not exist
          		  m_BaseHelp.setFormData(oFrm,"errormsg", "the current portal does not exist as a national association" );
          		  return actionMapping.findForward( "noaccess" );
         	}

           	
                	return actionMapping.findForward( "cvinternmanage" ); 
           }
           // end-of method showCVInternManage()
           
         
          
          
       	/*
           * process processRemoveAssociation organization 
           */
           public ActionForward processRemoveAssociation(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
           	int iRetCode=0,iRetCode2=0;
            	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
      		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
              		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
          			return actionMapping.findForward("404");
              	}
              }

          	  boolean showPortalInfo=false;
          	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
          			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
          			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
          			httpServletRequest.getHeader("host").contains("churchvol.org")	||
            			httpServletRequest.getHeader("host").contains("http://www.chrisvol.org")	||
            			httpServletRequest.getHeader("host").contains("http://chrisvol.org")	||
          			httpServletRequest.getHeader("host").contains("http://cv.org")
          	  ) {
          	  	showPortalInfo=true;
          	  }
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	DynaActionForm oFrm = (DynaActionForm)actionForm;
             	getLoggedInStatus(httpServletRequest, httpServletResponse);
          		if(aszLoggedInStatus.equals("showlogin")){
          	    	return actionMapping.findForward( "showlogin" );
          		}else if(aszLoggedInStatus.equals("processCookieLogin")){
          	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		}
               String aszNatlAssocNID = m_BaseHelp.getFormData(oFrm,"nid");
           	int iNatlAssocNID = m_BaseHelp.convertToInt( aszNatlAssocNID );
           	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            	if(iOrgNid < 1){
            		aszOrgNid = "" + aSessDat.getOrgNID();
                	if(iOrgNid < 1){
       	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
       	             	return actionMapping.findForward( "showlogin" );
       	         	}else{
       	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	         	}
                	}
            	}
       		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
       			  if(null != aSessDat){
       				  aSessDat.setOrgNID( aszOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
       			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
       		 }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
               }

         	  boolean bAdminRole=false;
       	  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
       	  if(! (aszRole==null || aszRole.equals(null))){
       		  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
       			  bAdminRole=true;
       		  }else{
       			  aszRole="";
       		  }
       	  }
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( aszOrgNid );
             aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
             allocatedOrgBRLO( httpServletRequest );

             iNatlAssocNID = 0;
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
             
             // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
             // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
             if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
             ){
           	  aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
             }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//           	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
               int iTid = aOrgInfoObj.getORGAffiliation1TID();
               if(iTid > 0){
              	 aOrgInfoObj.setORGNID( iOrgNid );
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
               }
/*lhlkjh
}else if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
             	 aOrgInfoObj.setNatlAssociation(aCurrentUserObj.getNatlAssociation());
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC );
*/
               }else{
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           }

             if(iRetCode == -111){
           	  if(bAdminRole==true){
           		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
           		  return actionMapping.findForward( "noaccess" );
           	  }else{
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
                  if(0 != iRetCode){
               		  // org did not exist
               		  m_BaseHelp.setFormData(oFrm,"errormsg", "the current portal does not exist as a national association" );
               		  return actionMapping.findForward( "noaccess" );
              	}
           	  }
             }
               if(0 != iRetCode){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
           	}
           	httpServletRequest.setAttribute("org", aOrgInfoObj);
           	
               // JUST update the orgaffil data
               // load all the child opps 7/7/2011
               // make sure that none of the child opps have the org affil applied to them, either
               ArrayList aList = new  ArrayList ( 2 );
               iRetCode = m_OrgBRLOObj.getOppListForOrg( aList, aOrgInfoObj.getORGNID(), OrganizationInfoDTO.LOADBY_ORG_ORDER_EXPIRATION );// might want to make sure that the admin/contact arrays get set
               iRetCode = m_OrgBRLOObj.removeAssociation( aOrgInfoObj, iNatlAssocNID, aList );
                	return showAssocManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
           }
           // end-of method processRemoveAssociation()
           
         
          /*
           * show organization showportalmanage
           */
          public ActionForward showPortalManage(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
        	  int iRetCode=0;
            	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
              // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
              	}
              }
              DynaActionForm oFrm = (DynaActionForm)actionForm;
              AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
              String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
              int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
              int iNatlAssocNID = 0;
              if(bNatlAssoc==true){
            	  //m_BaseHelp.convertToInt( aszPortalNID )
            	  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
              }
              if(iNatlAssocNID < 1 && iOrgNid < 1){
            	  aszOrgNid = "" + aSessDat.getOrgNID();
            	  iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            	  if(iNatlAssocNID < 1 && iOrgNid < 1){
            		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            			  return actionMapping.findForward( "showlogin" );
            		  }else{
            			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            		  }
            	  }
              }
              
              if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
            	  if(null != aSessDat){
            		  aSessDat.setOrgNID( aszOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
       			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
              }
              if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            		  return actionMapping.findForward( "showlogin" );
            	  }else{
            		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	  }
              }
              boolean bAdminRole=false;
              String aszRole = m_BaseHelp.getFormData(oFrm,"role");
              if(! (aszRole==null || aszRole.equals(null))){
            	  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            		  bAdminRole=true;
            	  }else{
            		  aszRole="";
            	  }
              }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
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
              
              
              
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
              		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
            	  aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() );
            	  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//              	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
            	 // will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );
             }else{
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             }

              if(iRetCode == -111){
            	  if(bAdminRole==true){
            		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
            		  return actionMapping.findForward( "noaccess" );
            	  }else{
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
                      if(0 != iRetCode){
                      	return actionMapping.findForward( "404" );
                  	}
            	  }
              }
              aOrgInfoObj.setRequestType(aszPortalRequestType);
              httpServletRequest.setAttribute("org", aOrgInfoObj);
              m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
              if(null != aSessDat){
            	  aSessDat.setTokenKey(null);
            	  aSessDat.setOrgNID(null);
            	  aSessDat.setOppNID(null);
            	  aSessDat.setSubdomain(null);
            	  aSessDat.setSiteEmail(null);
              }
	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "portalmanage" );
          }
          // end-of method showportalmanage

          
       	/*
       	 * * processManagePortal processPortalManage
         */
        public ActionForward processManagePortal(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	int iRetCode=0,iRetCode2=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
      		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
          	if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
              	}
          	}
          	boolean showPortalInfo=false;
          	String aszHeaderHost = httpServletRequest.getHeader("host");
          	if(	aszHeaderHost.contains("churchvolunteering.org") 	|| 
          		aszHeaderHost.equalsIgnoreCase("www.christianvolunteering.org")	|| 
          		aszHeaderHost.equalsIgnoreCase("christianvolunteering.org")	||
          		aszHeaderHost.contains("churchvol.org")	||
          		aszHeaderHost.contains("http://www.chrisvol.org")	||
          		aszHeaderHost.contains("http://chrisvol.org")	||
          		aszHeaderHost.contains("http://cv.org")
          	) {
          		showPortalInfo=true;
          	}
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
           	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
           	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            int iNatlAssocNID = 0;
            if(bNatlAssoc==true){
          	  iNatlAssocNID = iOrgNid;
            }
            if(iNatlAssocNID < 1 && iOrgNid < 1){
          	  aszOrgNid = "" + aSessDat.getOrgNID();
          	  iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          	  if(iNatlAssocNID < 1 && iOrgNid < 1){
          		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          			  return actionMapping.findForward( "showlogin" );
          		  }else{
          			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		  }
          	  }
            }
           	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
           		if(null != aSessDat){
       				  aSessDat.setOrgNID( aszOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
           		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
           	}
           	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
           	}
           	boolean bAdminRole=false;
           	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           	if(! (aszRole==null || aszRole.equals(null))){
       		  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
       			  bAdminRole=true;
       		  }else{
       			  aszRole="";
       		  }
           	}
           	OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           	aOrgInfoObj.setORGNID( aszOrgNid );

            // if natl association, then the OrgNid should be that of the correct national association
            if(bNatlAssoc==true){
            	aOrgInfoObj.setORGNID( iNatlAssocNID );
            }
            boolean b_AssocManager = false;
            int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
            for(int i=0; i<a_iNatlAssocNIDs.length; i++){
          	  if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
            }
            
           	
           	aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
           	allocatedOrgBRLO( httpServletRequest );
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
            	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
              	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           	 // will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion );
         }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }

            if(iRetCode == -111){
           	  if(bAdminRole==true){
           		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
           		  return actionMapping.findForward( "noaccess" );
           	  }else{
           		  // org did not exist
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
                  if(0 != iRetCode){
                  	return actionMapping.findForward( "404" );
              	}
           	  }
            }
            if(0 != iRetCode){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
            }
           	httpServletRequest.setAttribute("org", aOrgInfoObj);
           	if(bNatlAssoc==true){
               	iRetCode = m_OrgActHelp.getPortalDataFromForm1(oFrm, aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC);
           	}else{
               	iRetCode = m_OrgActHelp.getPortalDataFromForm1(oFrm, aOrgInfoObj);
           	}

             aszPortal = "";
       		if(httpServletRequest.getParameter("portal") != null){
       			aszPortal = httpServletRequest.getParameter("portal");
       		}
       		if(aszPortal.length()>0){

       	        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "portalpath" ) ;
       	        if(! aOrgInfoObj.getPortalNameModified().equals(aOrgInfoObj.getPortalNameOrig())){
       	            iRetCode = m_OrgBRLOObj.IsPortalNameInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
       	        }
       	        if(aOrgInfoObj.getPortalNameModified().length()<1){
       	        	aOrgInfoObj.setPortalNameModified(aOrgInfoObj.getPortalNameOrig());
       	        }
       			if(session.getAttribute(aszPortal + "_banner") != null){
       				if(! session.getAttribute(aszPortal + "_banner").equals(aOrgInfoObj.getPortalBannerURL())){
       					aOrgInfoObj.setPortalBannerURL(session.getAttribute(aszPortal + "_banner").toString());
       					//aOrgInfoObj.setPortalBannerURL(aszPortal + "_banner");
       				}
       			}else if(session.getAttribute("fileName")!= null){
       	        	if(session.getAttribute("fileName").toString().length()>0){
       	        		aOrgInfoObj.setPortalBannerURL(session.getAttribute("fileName").toString());
       	        	}
       			}
       		}        
       	     // initial case of not having a portal set yet; need to still grab the banner image url from the session to write to db.  
       	     // since portal doesn't exist, can't look for attribute of portal+_banner
       		 // need to look instead at  session.setAttribute("fileName",fileName);
       		else if(showPortalInfo==true && aOrgInfoObj.getPortalNameModified().length()>0){
       	     	if(session.getAttribute("fileName")!= null){
       		        	if(session.getAttribute("fileName").toString().length()>0){
       		        		aOrgInfoObj.setPortalBannerURL(session.getAttribute("fileName").toString());
       		        	}
       	     	}
       			aszPortal = aOrgInfoObj.getPortalNameModified();
       	        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "portalpath" ) ;
       	        if(! aOrgInfoObj.getPortalNameModified().equals(aOrgInfoObj.getPortalNameOrig())){
       	            iRetCode = m_OrgBRLOObj.IsPortalNameInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
       	        }
       	     }
               
         	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
           		// this has been entered through a portal
         	   		aOrgInfoObj.setCurrentDomain("churchvol");
         	   	}
         	   	if(bNatlAssoc==true){
                    iRetCode = m_OrgBRLOObj.updatePortal( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC );
         	   	}else{
                    iRetCode = m_OrgBRLOObj.updatePortal( aOrgInfoObj );
         	   	}
               // clear session attributes for portal style, etc
                session.removeAttribute(aszPortal + "_header_tags");
                session.removeAttribute(aszPortal + "_header");
                session.removeAttribute(aszPortal + "_css");
                session.removeAttribute(aszPortal + "_footer");
        		if(aszPortal.length()>0){
        	       if(aszPortal.length()>0){
        	    	   // why is this done - ??????????????
        	    	   if(bNatlAssoc==false){ // natl assoc doesn't have favorites; it has orgs tagged with associated org affil
	        	           String aszOppNIDs = "";
	        	           // user owns the org; they can favorite orgs and opps
	        	           int iFavoriteUID = aOrgInfoObj.getORGUID();
	        	           iRetCode = m_OrgActHelp.getOrgFavoritesFromForm(oFrm, aOrgInfoObj);
	        	           iRetCode = m_OrgBRLOObj.favoriteOrgs( aOrgInfoObj );
	        	           // do select of all published child opps of given orgs (nids in array), and load these oppnids in the opp array
	        		       iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
	        		       aOrgInfoObj.setORGFavoritedOPPNIDsArray(aOrgInfoObj.getORGChildOPPNIDsArray());
	        		       iRetCode = m_OrgBRLOObj.favoriteOpps( aOrgInfoObj );
        	    	   }
        	       }
               }
               m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );
               if(0 != iRetCode && 1062 != iRetCode){
                   loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                   return actionMapping.findForward( "portalmanage" );
               }
               session.setAttribute("orgmanagementnid", aszOrgNid);
           	if(bAdminRole==true){
               	return showAdminOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
           	}else{
           		if(aOrgInfoObj.getPortalNameModified().length() > 0){// really should only be if the portal name changed
           			//*******************might have to return a mapping page instead, if in a portal.  the mapping page will then handle the potentially modified portal name
           			if(bNatlAssoc==false){
           				httpServletRequest.setAttribute("redirectpage", AppSessionDTO.TOKEN_REDIR_PORTAL_ORGMANAGEMENT);
           			}else{
           				httpServletRequest.setAttribute("redirectpage", "portalassocmanagement");
           			}
           		     httpServletRequest.setAttribute("redirectportal", aOrgInfoObj.getPortalNameModified());
           			// need to somehow pass portal Name as well
       				return actionMapping.findForward( "mappingpage" );
           		}
           		
               	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
           	}
           }
           // end-of method processManagePortal()
         
         
      	/*
           * process create organization detail page 3 - ali - 2006-10-11
           */
           public ActionForward processcreateorgdet(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
          	 int iRetCode=0, iRetCode2=0;
           	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	 DynaActionForm oFrm = (DynaActionForm)actionForm;
          	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEORG );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
               }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
               }

               //need to load the organization...
               OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
               iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj);

               int iNatlAssocNID = 0;
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
               
           if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
            	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//         	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	             int iTid = aOrgInfoObj.getORGAffiliation1TID();
	             if(iTid > 0){
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	             }
               }else{
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
               }
               if(-111 == iRetCode){ // opportunity did not exist, at least publicly
               	return actionMapping.findForward( "404" );
               } 
               if(0 != iRetCode){
   	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              	 return actionMapping.findForward( "createnonpstep3" );
               }
               
               OrganizationDetailsInfoDTO aOrgDetInfoObj = new OrganizationDetailsInfoDTO();
               iRetCode2 = m_OrgActHelp.getOrgDetailDataFromForm1(oFrm, aOrgDetInfoObj);
               if(0 != iRetCode2){
   	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              	 return actionMapping.findForward( "createnonpstep3" );
               }
               allocatedOrgBRLO( httpServletRequest );
               
               //set some fields for org_details
               aOrgDetInfoObj.setDETUID(aCurrentUserObj.getUserUID());

               // set equal to what was entered in the normal org profile
               //use the existing info in form, but set these for any changes to orginfo
               if(aOrgDetInfoObj.getDETFedTaxId().length() > 1){
             	  aOrgInfoObj.setORGFedTaxId( aOrgDetInfoObj.getDETFedTaxId1(), aOrgDetInfoObj.getDETFedTaxId2() );
               }
               aOrgInfoObj.setORGNumVolOrg( m_BaseHelp.getFormData(oFrm,"orgnumvol") );
               aOrgInfoObj.setORGNumServed( m_BaseHelp.getFormData(oFrm,"orgnumserved") );
               aOrgDetInfoObj.setDETNumServed(aOrgInfoObj.getORGNumServed());
               aOrgDetInfoObj.setDETNumVolOrg(aOrgInfoObj.getORGNumVolOrg());
               //aOrgDetInfoObj.setDETFedTaxId(aOrgInfoObj.getORGFedTaxId());
               // if any of the above 3 are changed in this form, change it in orginfo as well
               
       	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
         		// this has been entered through a portal
       	   		aOrgInfoObj.setCurrentDomain("churchvol");
     	  	}

               
               iRetCode = m_OrgBRLOObj.updateOrganization( aOrgInfoObj );

               httpServletRequest.setAttribute("org", aOrgInfoObj);
               httpServletRequest.setAttribute("det", aOrgDetInfoObj);
               iRetCode = m_OrgBRLOObj.createOrganizationDet( aOrgInfoObj, aOrgDetInfoObj );
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               m_BaseHelp.setFormData(oFrm,"errormsg", aOrgInfoObj.getErrorMsg() );

               
               //httpServletRequest.setAttribute("organization", aOrgInfoObj);
              	String aszOrgNid=aOrgInfoObj.getORGNID()+"";
               session.setAttribute("orgmanagementnid", aszOrgNid);
           	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
           }
           // end-of method processcreateorgdet()
           
           
           /*
            * show organization edit page 2 - details - 2006-10-11 - ali
            */
            public ActionForward shownonpeditstep2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
            {
           	 int iRetCode=0, iRetCode2=0;
             	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	 DynaActionForm oFrm = (DynaActionForm)actionForm;
              	getLoggedInStatus(httpServletRequest, httpServletResponse);
          		if(aszLoggedInStatus.equals("showlogin")){
          	    	return actionMapping.findForward( "showlogin" );
          		}else if(aszLoggedInStatus.equals("processCookieLogin")){
          	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		}
                String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
                int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
                if(iOrgNid < 1){
             		aszOrgNid = "" + aSessDat.getOrgNID();
                 	if(iOrgNid < 1){
        	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        	             	return actionMapping.findForward( "showlogin" );
        	         	}else{
       	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	         	}
                 	}
                }
        		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        			  if(null != aSessDat){
        				  aSessDat.setOrgNID( aszOrgNid );
        				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
        			  }
        				if(aCurrentUserObj.getUserProfileNID() < 1){ 
        					// this user is an old drupal user-only; need to take through partial account creation process
        					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
        					//		as well as an insert into the rails db
        					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
        			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
        				}
        		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
        	         	return actionMapping.findForward("mappingpage");
        		 }
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
                	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
                }

                boolean bAdminRole=false;
                String aszRole = m_BaseHelp.getFormData(oFrm,"role");
                if(! (aszRole==null || aszRole.equals(null))){
               	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
                 	bAdminRole=true;
               	 }else{
               		 aszRole="";
               	 }
                }
                 OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                 aOrgInfoObj.setORGNID( aszOrgNid );
                 aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
                 allocatedOrgBRLO( httpServletRequest );

                 int iNatlAssocNID = 0;
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
                 
                 // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
                 if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
               		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                 ){
               	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                 }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                  	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//               	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                   int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                   if(iTid > 0){
	                  	 aOrgInfoObj.setORGNID( iOrgNid );
	                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                   }
                 }else{
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                 }

                 if(iRetCode == -111){
                 	if(bAdminRole==true){
         	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
         	          	return actionMapping.findForward( "noaccess" );
                 	}else{
                 		// org did not exist
                     	return actionMapping.findForward( "404" );
                 	}
                 }
                httpServletRequest.setAttribute("org", aOrgInfoObj);
                m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );

                OrganizationDetailsInfoDTO aOrgDetInfoObj = new OrganizationDetailsInfoDTO();
                aOrgDetInfoObj.setDETOrgNID( aszOrgNid );
                httpServletRequest.setAttribute("det", aOrgDetInfoObj);
                iRetCode2 = m_OrgBRLOObj.loadOrganizationDetFromDB( aOrgDetInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
                
                // if the org_details is not created yet for this organization, go to create
                if(0 != iRetCode2){
    	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                    return actionMapping.findForward( "createnonpstep3" );
                }
                
                // if the number served or number of volunteers = 0, set equal to what was
                // entered in the normal org profile
                if(0 == aOrgDetInfoObj.getDETNumServed()){
                    aOrgDetInfoObj.setDETNumServed(aOrgInfoObj.getORGNumServed());        	 
                }
                if(0 == aOrgDetInfoObj.getDETNumVolOrg()){
               	 aOrgDetInfoObj.setDETNumVolOrg(aOrgInfoObj.getORGNumVolOrg());
                }
                m_OrgActHelp.fillOrgDetailDataEditForm( oFrm, aOrgDetInfoObj );

	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                String aszReqType = httpServletRequest.getParameter("reqtype");
                if(null == aszReqType) aszReqType="";
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "editnonpstep2" );
            }
            // end-of method shownonpeditstep2() - org details   
           
           
       	/*
            * process EDIT organization page 2 - org details
            */
            public ActionForward processEditOrgDet(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
            {
           	 int iRetCode=0,iRetCode2=0;
             	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
           	 AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	 DynaActionForm oFrm = (DynaActionForm)actionForm;
          	getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
                String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
                int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
                if(iOrgNid < 1){
             		aszOrgNid = "" + aSessDat.getOrgNID();
                 	if(iOrgNid < 1){
        	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        	             	return actionMapping.findForward( "showlogin" );
        	         	}else{
        	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	         	}
                 	}
                }

        		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        			  if(null != aSessDat){
        				  aSessDat.setOrgNID( aszOrgNid );
        				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
        			  }
        				if(aCurrentUserObj.getUserProfileNID() < 1){ 
        					// this user is an old drupal user-only; need to take through partial account creation process
        					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
        					//		as well as an insert into the rails db
        					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
        			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
        				}
        		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
        	         	return actionMapping.findForward("mappingpage");
        		 }
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
                	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
                }
                
                //organization
                OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                aOrgInfoObj.setORGNID( aszOrgNid );
                aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
                allocatedOrgBRLO( httpServletRequest );

                int iNatlAssocNID = 0;
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
                
             if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              int iTid = aOrgInfoObj.getORGAffiliation1TID();
              if(iTid > 0){
             	 aOrgInfoObj.setORGNID( iOrgNid );
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              }
                }else{
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                }
                if(0 != iRetCode){
                	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                       return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
                }
                httpServletRequest.setAttribute("org", aOrgInfoObj);

                //use the existing info in form, but set these for any changes to orginfo
                aOrgInfoObj.setORGFedTaxId( m_BaseHelp.getFormData(oFrm,"ein_1"), m_BaseHelp.getFormData(oFrm,"ein_2") );
                aOrgInfoObj.setORGNumVolOrg( m_BaseHelp.getFormData(oFrm,"orgnumvol") );
                aOrgInfoObj.setORGNumServed( m_BaseHelp.getFormData(oFrm,"orgnumserved") );
                
                //org_details
                OrganizationDetailsInfoDTO aOrgDetInfoObj = new OrganizationDetailsInfoDTO();
                allocatedOrgBRLO( httpServletRequest );
                aOrgDetInfoObj.setDETOrgNID( aszOrgNid );
                iRetCode2 = m_OrgBRLOObj.loadOrganizationDetFromDB( aOrgDetInfoObj, OrganizationDetailsInfoDTO.LOADBY_ORGNID );
            	
                httpServletRequest.setAttribute("det", aOrgDetInfoObj);
                iRetCode2 = m_OrgActHelp.getOrgDetailDataFromForm1(oFrm, aOrgDetInfoObj);
                if(0 != iRetCode2){
    	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
       			return actionMapping.findForward( "editnonpstep2" );
                }

                // set equal to what was entered in the normal org profile
                aOrgDetInfoObj.setDETNumServed(aOrgInfoObj.getORGNumServed());
                aOrgDetInfoObj.setDETNumVolOrg(aOrgInfoObj.getORGNumVolOrg());
                if(aOrgDetInfoObj.getDETFedTaxId().equalsIgnoreCase("")){
                 	aOrgDetInfoObj.setDETFedTaxId(aOrgInfoObj.getORGFedTaxId1(), aOrgInfoObj.getORGFedTaxId2());
                 }
                // if any of the above 3 are changed in this form, change it in orginfo as well
                iRetCode = m_OrgBRLOObj.updateOrganization( aOrgInfoObj );

                iRetCode = m_OrgBRLOObj.updateOrganizationDet( aOrgDetInfoObj );
                m_BaseHelp.setFormData(oFrm,"errormsg", aOrgDetInfoObj.getErrorMsg() );

                if(0 != iRetCode){
    	            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                    return actionMapping.findForward( "editnonpstep2" );
                }

                //httpServletRequest.setAttribute("organization", aOrgInfoObj);
                session.setAttribute("orgmanagementnid", aszOrgNid);
            	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
            }
            // end-of method processEditOrgDet()

         
         /*
         * show organization view page 1
         */
         public ActionForward showOrganization(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
        	 return showOrganiz(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         }
         // end-of method shownonpstep1()
         
         /*
          * show organization view page 1
          */
          public ActionForward showOrganiz(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
        	httpServletRequest.setAttribute("orgBRLO", m_OrgBRLOObj);
           	getPortalInfo( httpServletRequest, httpServletResponse);
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
          	int iRetCode=0, iRetCode2=0;
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
             String aszOrgNid = httpServletRequest.getParameter("orgnid");
             OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
             aOrgInfoObj.setORGNID( aszOrgNid );
             String aszUrlAlias = httpServletRequest.getParameter("urlalias");
             aOrgInfoObj.setORGUrlAlias( aszUrlAlias );
              allocatedOrgBRLO( httpServletRequest );
     	      aOrgInfoObj.setPortalUID( aszPortalUID);
     	      
     	         
              int iNatlAssocNID = 0;
              String aszRequestType =  m_BaseHelp.getFormData(oFrm,"requesttype");
              boolean b_isFeed = false;
              String aszURLAliasFormat = "org/";
              boolean feed =  m_BaseHelp.getFormDataBoolean(oFrm,"feed");
              aOrgInfoObj.setRequestType(aszRequestType);
      		boolean bNatlAssoc = false;
      		if(aszPortalRequestType.equals("natlassoc")){
      			bNatlAssoc=true;
                  boolean b_AssocManager = false;
                  if(bNatlAssoc==true){
                 	 iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
                  }
                  aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
                  aOrgInfoObj.setRequestType(aszPortalRequestType);
      		}
              if(aszUrlAlias == null && feed==false){
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszURLAliasFormat, aszSiteVersion );
              }else if(feed==true){
                  b_isFeed=true;
                  aszURLAliasFormat = "orgs/";
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS, aszURLAliasFormat, aszSiteVersion );
              }else{
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_URL_ALIAS, aszURLAliasFormat, aszSiteVersion );
              }
              iRetCode2=iRetCode; // iRetCode2 will be used to determine whether to load no results found or not for the published organizations
          	httpServletRequest.setAttribute("org", aOrgInfoObj);
              m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
              if(-111 == iRetCode2){ // organization did not exist, at least publicly
                	return actionMapping.findForward( "404" );
            	}
              if(iRetCode < 0){ // organization did not exist, at least publicly
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
                  if(0 != iRetCode){
                  	return actionMapping.findForward( "404" );
              	}
            	}
              return actionMapping.findForward( "orgpubliclisting" );
          }
          // end-of method shownonpstep1()

    //=== START opportunity section  ===>
    //=== START opportunity section  ===>
    //=== START opportunity section  ===>

    /*
    * show organization add opportunity step1
    */
    public ActionForward showOrgAddOpp1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
//System.out.println(" triggered showOrgAddOpp1   " );	        	
    	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
	// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    if(aszPortal.length()>0){
    	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
    	}
    }
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );

     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        boolean bByContact = false;
        String aszReqTypeSessionVar =  "";
        try{
        	aszReqTypeSessionVar =  session.getAttribute("requesttype").toString();
//System.out.println("   get attribute bByContact   " );	        	
        }catch(Exception e){}
        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
        if(aszReqType.contains("ByContact")){
       	 bByContact = true;
        }else if(aszReqTypeSessionVar.contains("ByContact")){
       	 bByContact = true;
        }
//System.out.println("   bByContact   " +bByContact);	        	
        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
//System.out.println("   iOrgNid   " +iOrgNid);	        	
        if(iOrgNid < 1){
            if(session.getAttribute("orgmanagementnid")!=null){
            	aszOrgNid=session.getAttribute("orgmanagementnid").toString();
            	if(aszOrgNid.length()>0){
            		try{
            			iOrgNid = Integer.parseInt(aszOrgNid);
            		}catch (Exception e){
            			
            		}
            	}
            }
         	if(iOrgNid < 1){
         		aszOrgNid = "" + aSessDat.getOrgNID();
	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	             	return actionMapping.findForward( "showlogin" );
	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
         	}
        }
//System.out.println("   iOrgNid 2  " +iOrgNid);	        	
		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEOPPORT );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
     	
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
         OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
         aOrgInfoObj.setORGNID( aszOrgNid );
         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
         allocatedOrgBRLO( httpServletRequest );

         int iNatlAssocNID = 0;
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
         
//System.out.println("   before loading org  " +iOrgNid);	        	
         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
         if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
         ){
       	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
         }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
          	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
           int iTid = aOrgInfoObj.getORGAffiliation1TID();
           if(iTid > 0){
          	 aOrgInfoObj.setORGNID( iOrgNid );
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
           }
         }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }

         if(iRetCode == -111){
// System.out.println("   failed  loading org  " +iOrgNid);	        	
          	if(bByContact == true){
              	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
//System.out.println("   before loading org by contact " +iOrgNid);	        	
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
        	}
          	else         	if(bAdminRole==true){
 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	          	return actionMapping.findForward( "noaccess" );
         	}else{
         		// org did not exist
             	return actionMapping.findForward( "404" );
         	}
         }
        httpServletRequest.setAttribute( "org", aOrgInfoObj );
        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        // for duplicate opportunities; re-load the opportunity information as entered (if there is an error)
        // --> was legacy id's - ??? is this updated correctly ???
        String aszOppNid =  m_BaseHelp.getFormData(oFrm,"oppnid");
    	int iOppNid = m_BaseHelp.convertToInt( aszOppNid );
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        httpServletRequest.setAttribute( "opp", aOpportObj );
        aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
       	 aOpportObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
        }
		if(aOpportObj.getSiteAdministratorUID() > 1){
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion,httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}else if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion,httpServletRequest.getSession().getServletContext().getRealPath("/") );
	     }else{
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}
        if(iRetCode==-111){
       	 if(bByContact == true){
           	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
       		 iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"", OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            }
        }
        String aszPositionType =  m_BaseHelp.getFormData(oFrm,"opppositiontypetid");
        aOpportObj.setOPPPositionTypeTID( aszPositionType );

//  System.out.println("   before loading org contact data " +iOrgNid);	        	
  if(bByContact==false){
        // load data for organization contact person
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
        allocatedIndBRLO( httpServletRequest );
        iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
        m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

    	if(null != aSessDat){
        	aSessDat.setTokenKey(null);
        	aSessDat.setOrgNID(null);
        	aSessDat.setOppNID(null);
        	aSessDat.setSubdomain(null);
        	aSessDat.setSiteEmail(null);
    	}
//    	  System.out.println("   before loading org's opps lists, etc for contact data; maybe we'll just skip this " +iOrgNid);	        	
        
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        ArrayList aList = new ArrayList();
        iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
        //         iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
        httpServletRequest.setAttribute( "userlist", aList );
        iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
        iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );

        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
  }else{
	  httpServletRequest.setAttribute("userlist", new ArrayList());
  }

	String requestType = "";
	if( httpServletRequest.getSession().getAttribute("requesttype") != null){
		requestType = (String) httpServletRequest.getSession().getAttribute("requesttype");
	}
        
        boolean shortform = m_BaseHelp.getFormDataBoolean(oFrm, "shortform");
        if(!shortform) {
     		if(bByContact==true) 
     			shortform = true;
     	}
if(aszReqType.equals("cvintern"))		return actionMapping.findForward("nonpaddlistingcvintern");


        return actionMapping.findForward( shortform ? "nonpaddlistingshort" : "nonpaddlisting" );
    }
    // end-of method showOrgAddOpp1()
    
    
 	/*
      * show duplicate opportunity
      */
      public ActionForward showOpportunityDuplic(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	return showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      // end-of method showOpportunityDuplic()
      /*
   	 * add brand new contact for organiztion
   	 */
      public ActionForward addBrandNewDisasterReliefOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0;
    	  getPortalInfo( httpServletRequest, httpServletResponse);
    	  boolean bNatlAssoc = false;
    	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
    	  String aszOrgNid = ""+iDisasterReliefOrgNID;
    	  aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
    	  OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
          aOrgInfoObj.setORGNID( aszOrgNid );
          aSessDat.setOrgNID(aszOrgNid);
          
          allocatedIndBRLO( httpServletRequest );
          allocatedOrgBRLO( httpServletRequest );
          // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
          // load data for organization contact person
          //get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
          iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aCurrentUserObj);
          String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
          String aszNameFirst = m_BaseHelp.getFormData(oFrm,"firstname");
          String aszNameLast = m_BaseHelp.getFormData(oFrm,"lastname");
          aCurrentUserObj.setUSPNameFirst(aszNameFirst);
          aCurrentUserObj.setUSPNameLast(aszNameLast);
          
          // for the contact person, write the "init" field for the new user as the current user's email address
          aCurrentUserObj.setUSPEmail2Addr(aszEmailAddress);
          String mailkey = "newOrgContactUserAccnt";
          OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
          
          // create username from email address - strip out the @whatever. check if the username already exists; if so, add nonce to end
          int index_atsign=aszEmailAddress.indexOf("@");
          String aszUsername ="";
          if(index_atsign>0){
        	  aszUsername=aszEmailAddress.substring(0,index_atsign);
        	  aCurrentUserObj.setUSPUsername(aszUsername);
          }
          iRetCode = m_IndBRLOObj.isUsernameInSystem(aCurrentUserObj);
          if(iRetCode==0){ // username already exists; add a random nonce
        	  Random r1 = new Random();
        	  String nonce = Long.toString(Math.abs(r1.nextLong()), 4).substring(0,4);  
        	  aCurrentUserObj.setUSPUsername(aszUsername+nonce);
          }
          aCurrentUserObj.setPasswordConfirm(aCurrentUserObj.getUSPPassword());
          String aszSiteVersion="default";
          if( httpServletRequest.getHeader("host").contains(":7001" ) ){
        	  aszSiteVersion="development";
          }else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
        	  aszSiteVersion="staging";
          }
          aCurrentUserObj.setORGNID(iDisasterReliefOrgNID);
          //iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, "", mailkey, aszSiteVersion);
          iRetCode = m_IndBRLOObj.createAccount1( aCurrentUserObj, aszSiteVersion, "short" );
System.out.println("iRetCode from createAccount1 is: "+iRetCode);          
          aCurrentUserObj.setORGNID(iDisasterReliefOrgNID);
          aCurrentUserObj.setUSPSiteUseType("Organization");
          aCurrentUserObj.setORGNID(iDisasterReliefOrgNID);
      	  iRetCode = m_IndBRLOObj.updateIndividualHead(  aCurrentUserObj, aszSiteVersion);
System.out.println("iRetCode from updateIndividualHead is: "+iRetCode);          
      	  if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
      		  // the user was correctly loaded and now exists
      		  iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
System.out.println("iRetCode from loadChildOpps is: "+iRetCode);          
      		  iRetCode = m_OrgBRLOObj.insertAdditionalOrgContact( aOrgInfoObj , aCurrentUserObj ); // really only needs a uid to get inserted.
System.out.println("iRetCode from insertAdditionalOrgContact is: "+iRetCode);          
      	  }else{
          	  String aszErr;
          	  if(iRetCode == -1){
          		  aszErr="The email address already exists.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r";
          	  }else if(iRetCode == -111){
          		  if(aCurrentUserObj.getErrorMsg().equalsIgnoreCase("Error on saving the user.")){
          			  // there was an error with the XML-RPC call - most likely a duplicate Username
          			  aszErr="There was an error on saving this user. " +
          			  	"Make sure the email address or username does not already exist in our system.";
          		  }else{
          			  // there was an error with the XML-RPC call
          			  aszErr="There was an error on saving this user: " + aCurrentUserObj.getErrorMsg() ;
          		  }
          	  }else if(iRetCode == 333){
          		  if(aCurrentUserObj.getErrorMsg().equalsIgnoreCase("Error on saving the user ")){
          			  // there was an error with the XML-RPC call - most likely a duplicate Username
          			  aszErr="There was an error on saving this user. " +
  		             		"Make sure the email address or username does not already exist in our system.";
          		  }else{
          			  // there was an error with the XML-RPC call
          			  aszErr="There was an error on saving this user: \"" +
          					aCurrentUserObj.getErrorMsg() +
  		             			"\" (Make sure the email address or username does not already exist in our system.)";
          		  }
          	  }else if(iRetCode == 444){
          		  // there was an error with the XML-RPC call
          		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r" +
                			"(The error reported was: " + aCurrentUserObj.getErrorMsg() + ")";
          	  }else if(iRetCode == 555){
          		  // there was an error with the XML-RPC call
          		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section\n\r" +
                			"(The error reported was: " + aCurrentUserObj.getErrorMsg() + ")";
          	  }else{
          		  // the email address could not be found in our database
          		  aszErr="The email address " + aszEmailAddress + " could not be found in our database. Try creating a new account for this email below in the \"New Administrator\" section\n\r";
          		  if(aCurrentUserObj.getErrorMsg().length()>5){
          			  aszErr=aszErr + "(The error reported was: " + aCurrentUserObj.getErrorMsg() + ")";
          		  }
          	  }
          	  m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
          	  httpServletRequest.setAttribute("org", aOrgInfoObj);
          	  httpServletRequest.setAttribute("redirectpage","landingdisasterreliefhomepage") ;
          	  return actionMapping.findForward("mappingpage");
      	  }
      	  session.setAttribute("orgmanagementnid", aszOrgNid);
      	  session.setAttribute("requesttype", "ByContact");
      	  httpServletRequest.setAttribute("org", aOrgInfoObj);
      	  m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
      	  
      	  // administrator list for organization
      	  allocatedOrgBRLO( httpServletRequest );
      	  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);// not sure which user??
      	  ArrayList aList = new ArrayList();
      	  iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
      	  httpServletRequest.setAttribute( "userlist", aList );
      	  httpServletRequest.setAttribute( "org", aOrgInfoObj );
      	  
      	  iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
      	  iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
      	  aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORTDISASTER);
      	  
  		  iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
  		aCurrentUserObj.setUSPPasswordInternal(aCurrentUserObj.getUSPPassword());
      	  iRetCode = m_IndBRLOObj.loginUser( aCurrentUserObj, aszSiteVersion );
      	  int iRetCode3=setChrisVolAuthCookieOn(httpServletRequest,httpServletResponse,aCurrentUserObj,"login");
      	  
      	  if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
      		  iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
      		  return processCreateOpportstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	  }
      	  httpServletRequest.setAttribute("redirectpage","landingdisasterreliefhomepage") ;
      	  return actionMapping.findForward("mappingpage");
      }
      // end-of method addBrandNewDisasterReliefOrgContact()

      /*
    * process create organization opportunity page 1
    */
    public ActionForward processCreateOpportstep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	
      	getPortalInfo( httpServletRequest, httpServletResponse);
    	int iRetCode=0;
    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	
    	String aszToken = aSessDat.getTokenKey();
    	
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	  boolean showPortalInfo=false;
    	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
    			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
    			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
    			httpServletRequest.getHeader("host").contains("churchvol.org:7001")	||
      			httpServletRequest.getHeader("host").contains("chrisvol.org:7001")	||
    			httpServletRequest.getHeader("host").contains("cv.org:7001")
    	  ) {
    	  	showPortalInfo=true;
    	  }
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	boolean shortform = m_BaseHelp.getFormDataBoolean(oFrm, "shortform");
    	
    	
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        if(iOrgNid < 1){
     		aszOrgNid = "" + aSessDat.getOrgNID();
         	if(iOrgNid < 1){
	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	             	return actionMapping.findForward( "showlogin" );
	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
         	}
        }
        if(iOrgNid==iDisasterReliefOrgNID){
        	aszToken=AppSessionDTO.TOKEN_CREATEOPPORTDISASTER;
        }
		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEOPPORT );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
        boolean bByContact = false;
        String aszReqTypeSessionVar =  "";
        try{
        	aszReqTypeSessionVar =  session.getAttribute("requesttype").toString();
        }catch(Exception e){}
        
        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
        if(aszReqType.contains("ByContact")){
       	 bByContact = true;
        }else if(aszReqTypeSessionVar.contains("ByContact")){
       	 bByContact = true;
        }
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
     	if(!shortform) {
     		String requestType = (String) httpServletRequest.getSession().getAttribute("requesttype");
     		if(bByContact == true) 
     			shortform = true;
     	}
    	String addListingAction = shortform ? "nonpaddlistingshort" : "nonpaddlisting";
        boolean b_isJob = false;
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        allocatedOrgBRLO( httpServletRequest );


        int iNatlAssocNID = 0;
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
        
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
      		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
      	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
         	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//      	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
          int iTid = aOrgInfoObj.getORGAffiliation1TID();
          if(iTid > 0){
         	 aOrgInfoObj.setORGNID( iOrgNid );
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
          }
	     }else{
	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            if(iRetCode==-111){
            	if(bByContact == true){
                  	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            	}
            }
        }
        if(iRetCode == -111){
          	if(bByContact == true){
             	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
          	}
         	else if(bAdminRole==true){
	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	          	return actionMapping.findForward( "noaccess" );
        	}else{
        		// org did not exist
            	return actionMapping.findForward( "404" );
        	}
        }
        httpServletRequest.setAttribute( "org", aOrgInfoObj );
        ArrayList aList = new ArrayList();
        if(0 != iRetCode){
            m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );
        }
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        httpServletRequest.setAttribute( "opp", aOpportObj );
        m_OrgActHelp.getOrgOppFromForm1( oFrm, aOpportObj );
        
        // make sure that if it's a CityVision Internship, the Resume is Required and the correct Questionnaire is Set
        if(aOpportObj.getOPPInternType().equals(aszCityVisionInternship)){
        	aOpportObj.setQuestionnaireType("online");
        	aOpportObj.setQuestionnaireId(iCVCInternQuestionnaireID);
        	aOpportObj.setResumeRequired(1);
        }

        String aszAddr1="", aszCity="", aszState="", aszCountry="", aszPostalCode="";
        if(httpServletRequest.getParameter("addr1") != null ){
        	aszAddr1=httpServletRequest.getParameter("addr1");
        	aOpportObj.setOPPAddrLine1(aszAddr1);
        }
        if(httpServletRequest.getParameter("city") != null ){
        	aszCity=httpServletRequest.getParameter("city");
        	aOpportObj.setOPPAddrCity(aszCity);
        }
        if(httpServletRequest.getParameter("state") != null ){
        	aszState=httpServletRequest.getParameter("state");
        	aOpportObj.setOPPAddrStateprov(aszState);
        }
        if(httpServletRequest.getParameter("postalcode") != null ){
        	aszPostalCode=httpServletRequest.getParameter("postalcode");
        	aOpportObj.setOPPAddrPostalcode(aszPostalCode);
        }
        if(httpServletRequest.getParameter("country") != null ){
        	aszCountry=httpServletRequest.getParameter("country");
        	aOpportObj.setOPPAddrCountryName(aszCountry);
        }
        
        if(aOpportObj.getOPPPositionTypeTID()==iJobTID){
        	b_isJob=true;
        }
        aOpportObj.setErrorMsg(""); // clear any previous error message so that any new errors we have get entered with a clean start

        aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // set the uid to allow ownership on drupal site, as well
     	
     	
     	
     	// generate URL Alias for the Opportunity (pass the organization and just feed in the opp title so that we don't have to have 2 very similar methods)
     	aOrgInfoObj.setORGUrlAlias(aOpportObj.getOPPTitle());
        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "urlalias" ) ;
        // convert to Organization format for organizations
        if(b_isJob==false){
        	aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getPathAutoOppPattern()+aOrgInfoObj.getORGUrlAlias());
        }else{
            aOrgInfoObj.setORGUrlAlias("job/"+aOrgInfoObj.getORGUrlAlias());
        }
        // check to see if this URL exists in the system... if so, keep trying to append -# to the url alias until there is no record found for it.
        iRetCode = m_OrgBRLOObj.IsURLAliasInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
        // set the opportunity URL alias to the URL alias returned by the IsURLAliasInSystem function, which takes the ORG obj instead of OPP obj
        aOpportObj.setOPPUrlAlias(aOrgInfoObj.getORGUrlAlias());
     	
     	//if(true==true)             return actionMapping.findForward( "createnonpstep1" );
        // store whether this was entered through a portal; if so, it will be "favorited" later, so as to handle it through the portal
        //String 
        aszPortal = "";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
                if( session.getAttribute(aszPortal + "_nid") != null	){
                    if( session.getAttribute(aszPortal + "_nid").toString().length()>0	){
                        if( session.getAttribute(aszPortal + "_uid") != null	){
                            if( session.getAttribute(aszPortal + "_uid").toString().length()>0	){
                            	try{
                        			int iPortalUID = Integer.parseInt(session.getAttribute(aszPortal + "_uid").toString());
                        			if(iPortalUID==aOpportObj.getOPPUID()){
                	                	if(showPortalInfo==true){
            	                			// this has been entered through a portal
                	                		aOpportObj.setPortal(1);
                	                	}
                        			}
                            	}catch (Exception e){}
                            }
                        }
                    }
                }
            }
        }

        // load data for organization contact person
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        aContactPersonObj.setUserUID( aOpportObj.getOPPUID() ); // ? - initially set to current user for Primary Contact; can be modified later
        // what about "duplicate" opps?  should maybe take an input for this or something
        allocatedIndBRLO( httpServletRequest );
        iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
        m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

		if(
					httpServletRequest.getHeader("host").contains("hurricanesandy") || 
					aOpportObj.getORGNID()!=iDisasterReliefOrgNID
		){
		}else{
			aOpportObj.setOPPUID( aOrgInfoObj.getORGUID() ); // allows creates the new opportunity with the primary owner of the parent org
		}
		iRetCode = m_OrgBRLOObj.createOpportunity( aCurrentUserObj, aOrgInfoObj, aOpportObj, aszToken, aszSiteVersion, getServlet().getServletContext().getRealPath("/"));


        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
        
        if(iRetCode==-22){
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            httpServletRequest.setAttribute( "userlist", aList );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );

        }

        boolean bPrimaryContactChanged=false, bContactsChanged=false, bEmailContactsChanged=false;
        // check to see if any of the Contact data on the given opportunity has changed...
        int iInitUID = aOpportObj.getOPPPrimaryPersonUID();
        int iModifiedUID = aOpportObj.getOPPPrimaryPersonUIDModified();
        if(iModifiedUID != iInitUID){
        	// primary person has changed.  this will, in addition to being updated in the DB, trigger an email notice about change of Primary Contact for said opp
        	bPrimaryContactChanged=true;
        }
        if(! aOpportObj.getOPPContactUIDsModifiedArray().equals(aOpportObj.getOPPContactUIDsArray())){
        	bContactsChanged=true;
        }
        if(! aOpportObj.getOPPContactUIDsRcvEmailModifiedArray().equals(aOpportObj.getOPPContactUIDsRcvEmailArray())){
        	bEmailContactsChanged=true;
        }
        ArrayList aListIdsContactsChanged = null;
        ArrayList aListIdsEmailContactsChanged = null;
        ArrayList aListIdsAndEmailNotifyFlag = null;
        if(bContactsChanged==true || bEmailContactsChanged==true ||bPrimaryContactChanged==true ){ 
        	// need to update contacts for opportunity
// admins don't necessarily have to be written to the usermail table; not unless they are "email" contacts
            // get arrays of uids that show the differences - should prob be done in BRL, but will need to trigger the emailNotify for each user changed
        	aListIdsContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsArray(),aOpportObj.getOPPContactUIDsModifiedArray());
        	aListIdsEmailContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsRcvEmailArray(),aOpportObj.getOPPContactUIDsRcvEmailModifiedArray());

        	aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged,iInitUID,iModifiedUID);
        	//aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged);
        	
        	iRetCode=m_OrgBRLOObj.updateOPPContacts(aListIdsAndEmailNotifyFlag, aOpportObj);
        	
            for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
                int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
                int iUID=iIdAndFlag[0];
                int iFlag=iIdAndFlag[1];
                
                if(iUID>0){
	                // load the contact info for that user
	                aContactPersonObj = new PersonInfoDTO();
	                aContactPersonObj.setUserUID( iUID );
	                iRetCode = m_IndBRLOObj.loadUserContactData( aContactPersonObj );        
	
	                // then trigger the emailNotify Message(s) for that flag
	                if(iRetCode==0){
	                	// run db update on the org's usermail records
	
	                	// if an email should be triggered, trigger it (for now, skip primary contact changes for this part)
	                	if(iFlag!=iNewPrimaryContact && iFlag !=iNoLongerPrimaryContact){
	                    	aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
	                        // ******************!!!!!!!!!!!!!!! add a method to email that they are now A. Receiving, emails now ************
	                        //iRetCode = m_IndBRLOObj.notifyNewAdmin( httpServletRequest, aContactPersonObj, aOrgInfoObj, iIsVolunteerContact );
	                        int iEmailUseCase = iFlag;
	                        if(iRetCode==-100){
		                       	 // The user did not receive any email notification, b/c there was no changed information on the user
	                        	iRetCode=0;
		                    }else if(iRetCode==333){
		                       	 // The user did not receive any email notification, b/c there was an error in the send email services hook
	                        	iRetCode=0;
		                    }
	                	}
	                }
                }
            }   
        }
        if(0 != iRetCode){
            m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
        	m_BaseHelp.setFormData(oFrm, "orgnid",aszOrgNid );
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        	return actionMapping.findForward( addListingAction );
        }
        // this is where it differs from normal create - maybe this should be in create and a certain flag says do this...else not
        if (aOpportObj.getOPPType().equalsIgnoreCase("duplicate")){
        	//load the information for opportunity
        	aOpportObj.setOPPNID("");
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );	
        }

    	if(aszToken==AppSessionDTO.TOKEN_CREATEOPPORTDISASTER){
        	  httpServletRequest.setAttribute("redirectpage","landingdisasterreliefhomepage") ;
          	  return actionMapping.findForward("mappingpage");
//			return showHome(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	}
        session.setAttribute("orgmanagementnid", aszOrgNid);
        
        if(0 == iRetCode && aOpportObj.getQuestionnaireType().equals("online") && aOpportObj.getQuestionnaireId() == -1){
        	String aszRailsNewQuestionnaire = aszRailsPrefixPath + "cor~voleng~organizations~" + aOrgInfoObj.getORGNID() + "~questionnaires~new";
        	httpServletRequest.setAttribute("redirect", aszRailsNewQuestionnaire);
        	return actionMapping.findForward("mappingpage");
        }
        
    	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    }
    // end-of method processCreateOpportstep1()
    /*
    * process create organization processCreateOpportShortStep2
    */
    public ActionForward processCreateOpportShortStep2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
      	getPortalInfo( httpServletRequest, httpServletResponse);
    	int iRetCode=0;
    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	
    	String aszToken = aSessDat.getTokenKey();
    	
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	  boolean showPortalInfo=false;
    	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
    			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
    			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
    			httpServletRequest.getHeader("host").contains("churchvol.org:7001")	||
      			httpServletRequest.getHeader("host").contains("chrisvol.org:7001")	||
    			httpServletRequest.getHeader("host").contains("cv.org:7001")
    	  ) {
    	  	showPortalInfo=true;
    	  }
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	boolean shortform = true;//m_BaseHelp.getFormDataBoolean(oFrm, "shortform");
    	
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        if(iOrgNid < 1){
     		aszOrgNid = "" + aSessDat.getOrgNID();
         	if(iOrgNid < 1){
	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	             	return actionMapping.findForward( "showlogin" );
	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
         	}
        }
		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_CREATEOPPORT );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
        boolean bByContact = false;
        String aszReqTypeSessionVar =  "";
        try{
        	aszReqTypeSessionVar =  session.getAttribute("requesttype").toString();
        }catch(Exception e){}
        
        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
        if(aszReqType.contains("ByContact")){
       	 bByContact = true;
        }else if(aszReqTypeSessionVar.contains("ByContact")){
       	 bByContact = true;
        }
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
     	if(!shortform) {
     		String requestType = (String) httpServletRequest.getSession().getAttribute("requesttype");
     		if(bByContact == true) 
     			shortform = true;
     	}
    	String addListingAction = shortform ? "nonpaddlistingshort" : "nonpaddlisting";
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        allocatedOrgBRLO( httpServletRequest );

        int iNatlAssocNID = 0;
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
        
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
      		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
      	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
         	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
          int iTid = aOrgInfoObj.getORGAffiliation1TID();
          if(iTid > 0){
         	 aOrgInfoObj.setORGNID( iOrgNid );
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
          }
	     }else{
	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            if(iRetCode==-111){
            	if(bByContact == true){
                  	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            	}
            }
        }
        if(iRetCode == -111){
          	if(bByContact == true){
             	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
          	}
         	else if(bAdminRole==true){
	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	          	return actionMapping.findForward( "noaccess" );
        	}else{
            	return actionMapping.findForward( "404" );// org did not exist
        	}
        }
        httpServletRequest.setAttribute( "org", aOrgInfoObj );
        ArrayList aList = new ArrayList();
        if(0 != iRetCode){
            m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );
        }
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        httpServletRequest.setAttribute( "opp", aOpportObj );
        m_OrgActHelp.getOrgOppFromForm1( oFrm, aOpportObj );
        aOpportObj.setErrorMsg(""); // clear any previous error message so that any new errors we have get entered with a clean start
        aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // set the uid to allow ownership on drupal site, as well
     	
        // store whether this was entered through a portal; if so, it will be "favorited" later, so as to handle it through the portal
        aszPortal = "";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
                if( session.getAttribute(aszPortal + "_nid") != null	){
                    if( session.getAttribute(aszPortal + "_nid").toString().length()>0	){
                        if( session.getAttribute(aszPortal + "_uid") != null	){
                            if( session.getAttribute(aszPortal + "_uid").toString().length()>0	){
                            	try{
                        			int iPortalUID = Integer.parseInt(session.getAttribute(aszPortal + "_uid").toString());
                        			if(iPortalUID==aOpportObj.getOPPUID()){
                	                	if(showPortalInfo==true){
            	                			// this has been entered through a portal
                	                		aOpportObj.setPortal(1);
                	                	}
                        			}
                            	}catch (Exception e){}
                            }
                        }
                    }
                }
            }
        }

        // load data for organization contact person
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        aContactPersonObj.setUserUID( aOpportObj.getOPPUID() ); // ? - initially set to current user for Primary Contact; can be modified later
        // what about "duplicate" opps?  should maybe take an input for this or something
        allocatedIndBRLO( httpServletRequest );
        iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
        m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

		if(
			httpServletRequest.getHeader("host").contains("hurricanesandy") || 
			aOpportObj.getORGNID()!=iDisasterReliefOrgNID
		){
		}else{
			aOpportObj.setOPPUID( aOrgInfoObj.getORGUID() ); // allows creates the new opportunity with the primary owner of the parent org
		}
		
        iRetCode = m_OrgBRLOObj.editOpportunity( aCurrentUserObj, aOrgInfoObj, aOpportObj, aszToken, getServlet().getServletContext().getRealPath("/"));

        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
        
        if(iRetCode==-22){
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            httpServletRequest.setAttribute( "userlist", aList );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );

        }

        if(0 != iRetCode){
            
            m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
        	m_BaseHelp.setFormData(oFrm, "orgnid",aszOrgNid );
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        	return actionMapping.findForward( addListingAction );
        }
        // this is where it differs from normal create - maybe this should be in create and a certain flag says do this...else not
        if (aOpportObj.getOPPType().equalsIgnoreCase("duplicate")){
        	//load the information for opportunity
        	aOpportObj.setOPPNID("");
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            httpServletRequest.setAttribute( "userlist", aList );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( addListingAction );	
        }

    	if(aszToken==AppSessionDTO.TOKEN_CREATEOPPORTDISASTER){
			httpServletRequest.setAttribute("landingpage","landingdisasterreliefhomepage") ;
    	}
			return null;//showHome(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    // end-of method processCreateOpportDistasterStep2()
   
	/*
     * show edit opportunity step1
     */
     public ActionForward showOpportunityEdit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	 int iRetCode=0;
       	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	 
    	 AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	 DynaActionForm oFrm = (DynaActionForm)actionForm;
      	getLoggedInStatus(httpServletRequest, httpServletResponse);
   		if(aszLoggedInStatus.equals("showlogin")){
   	    	return actionMapping.findForward( "showlogin" );
   		}else if(aszLoggedInStatus.equals("processCookieLogin")){
   	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   		}
         boolean bByContact = false;
         String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
         if(aszReqType.contains("ByContact")){
        	 bByContact = true;
         }
         
         String aszOppNid = m_BaseHelp.getFormData(oFrm,"oppnid");
         int iOppNid = m_BaseHelp.convertToInt( aszOppNid );

         String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
         int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
         if(iOrgNid < 1){
      		aszOrgNid = "" + aSessDat.getOrgNID();
          	if(iOrgNid < 1 ){//&& bByContact == false){
 	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
 	             	return actionMapping.findForward( "showlogin" );
 	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	         	}
          	}
         }
         
         if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setOppNID( aszOppNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
       	 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
       		 return actionMapping.findForward( "showlogin" );
       	 }else{
       		 return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	 }
        }
     	
         boolean bAdminRole=false;
         String aszRole = m_BaseHelp.getFormData(oFrm,"role");
         if(! (aszRole==null || aszRole.equals(null))){
        	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
          	bAdminRole=true;
        	 }else{
        		 aszRole="";
        	 }
         }
         OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
         aOrgInfoObj.setORGNID( aszOrgNid );
         aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
         allocatedOrgBRLO( httpServletRequest );

         int iNatlAssocNID = 0;
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
         
         // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
         if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
         ){
       	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
         }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
          	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//       	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
           int iTid = aOrgInfoObj.getORGAffiliation1TID();
           if(iTid > 0){
          	 aOrgInfoObj.setORGNID( iOrgNid );
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
           }
	     }else{
	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
             if(iRetCode==-111){
             	if(bByContact == true){
                   	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
             	}
             }
         }
         if(iRetCode == -111){
         	if(bAdminRole==true){
 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	          	return actionMapping.findForward( "noaccess" );
         	}else{
         		// org did not exist
             	return actionMapping.findForward( "404" );
         	}
         }

         OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
         aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
         if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
         		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
         ){
        	 aOpportObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
         }
 		if(aOpportObj.getSiteAdministratorUID() > 1){
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}else if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
	     }else{
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}

         if(iRetCode==-111){
        	 if(bByContact == true){
            	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
        		 iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"", OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
             }
         }
         if(0 != iRetCode){
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
     	}
         m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
         
         
         ArrayList aList = new ArrayList();
         iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOpportObj );
         //         iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
         httpServletRequest.setAttribute( "userlist", aList );
         iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
         iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );

         httpServletRequest.setAttribute( "org", aOrgInfoObj );
         httpServletRequest.setAttribute( "opp", aOpportObj );
         m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
         // load data for organization contact person
         PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
         aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
         allocatedIndBRLO( httpServletRequest );
         iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
         m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

         loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
         
         String aszCVIntern =  "";
         if(httpServletRequest.getParameter("cvintern") != null){
        	 aszCVIntern = httpServletRequest.getParameter("cvintern").toString();
         }

         if(aszCVIntern.equals("true"))
             return actionMapping.findForward( "nonpeditlistingcvintern" );

         if(aszReqType.equalsIgnoreCase("edit") || aszReqType.equalsIgnoreCase("editByContact")){
             return actionMapping.findForward( "nonpeditlisting" );
         }
         return actionMapping.findForward( "nonpviewlisting" );
     }
     // end-of method showOpportunityEdit()

     
	/*
    * process EDIT opportunity page 1
    */
    public ActionForward processEditOpp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        boolean bByContact = false;
        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
        if(aszReqType.contains("ByContact")){
        	bByContact = true;
        }
        
        
        String aszOppNid = m_BaseHelp.getFormData(oFrm,"oppnid");
        int iOppNid = m_BaseHelp.convertToInt( aszOppNid );

        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        if(iOrgNid < 1){
     		aszOrgNid = "" + aSessDat.getOrgNID();
         	if(iOrgNid < 1 ){//&& bByContact == false){
	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	             	return actionMapping.findForward( "showlogin" );
	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
         	}
        }
		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setOppNID( aszOppNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE editing access of the said opportunity/org association
        allocatedOrgBRLO( httpServletRequest );

        int iNatlAssocNID = 0;
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
        
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
      		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
      	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
         	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//      	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
          int iTid = aOrgInfoObj.getORGAffiliation1TID();
          if(iTid > 0){
         	 aOrgInfoObj.setORGNID( iOrgNid );
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
          }
	     }else{
	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	         if(iRetCode==-111){
	         	if(bByContact == true){
	               	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
	                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	         	}
	         }
        }
        if(iRetCode == -111){
        	if(bAdminRole==true){
	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	          	return actionMapping.findForward( "noaccess" );
        	}else{
        		// org did not exist
            	return actionMapping.findForward( "404" );
        	}
        }
        if(0 != iRetCode){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
    	}

        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE editing access of the said opportunity/org association
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
       	 aOpportObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
        }
		if(aOpportObj.getSiteAdministratorUID() > 1){
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}else if(aCurrentUserObj.getNatlAssociationNID()>0){
			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
	     }else{
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}

        if(iRetCode==-111){
       	 if(bByContact == true){
           	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
       		 iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            }
        }
        if(0 != iRetCode){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
    	}
        iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
        iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
        httpServletRequest.setAttribute( "opp", aOpportObj );

        // only load the values from the form if it was a normal edit; i.e. not a direct "just renew" edit
        String aszOppExpiredRenew = httpServletRequest.getParameter("oppexpiredrenew");
        
        if (! (aszOppExpiredRenew.equalsIgnoreCase("renew")) ){
        	aOpportObj.setOPPUrlAliasOrig(aOpportObj.getOPPUrlAlias());
        	m_OrgActHelp.getOrgOppFromForm1( oFrm, aOpportObj );
        }
        boolean b_isJob = false;
        if(aOpportObj.getOPPPositionTypeTID()==iJobTID){
        	b_isJob=true;
        }
        
        // ** BEGIN url generating section
        // the url generating method takes the organizationDTO object, not the opp;
        // so that we don't have to write another method, here we just set the DTO methods for the org based on what was read in from the opp in the form
        aOrgInfoObj.setORGUrlAlias(aOpportObj.getOPPUrlAlias());
        aOrgInfoObj.setORGUrlAliasOrig(aOrgInfoObj.getORGUrlAlias());
     	// generate URL Alias for the Organization:
     	aOrgInfoObj.setORGUrlAlias(aOpportObj.getOPPTitle());
        iRetCode = m_OrgBRLOObj.generateURLAlias( aOrgInfoObj, "urlalias" ) ;
        // convert to Organization format for organizations
        if(b_isJob==false){
        	aOrgInfoObj.setORGUrlAlias(aOrgInfoObj.getPathAutoOppPattern()+aOrgInfoObj.getORGUrlAlias());
        }else{
            aOrgInfoObj.setORGUrlAlias("job/"+aOrgInfoObj.getORGUrlAlias());
        }
        String atmp = aOrgInfoObj.getORGUrlAlias();
        if(aOrgInfoObj.getORGUrlAlias().equalsIgnoreCase(aOrgInfoObj.getORGUrlAliasOrig())){ // clear so there is no update
        	aOrgInfoObj.setORGUrlAlias("");
        }else{
            // check to see if this URL exists in the system... if so, keep trying to append -# to the url alias until there is no record found for it.
            iRetCode = m_OrgBRLOObj.IsURLAliasInSystem( aOrgInfoObj, aOrgInfoObj.getORGNID() ) ;
        }
        // we have now done all the necessary calculations for updating the URL alias; set them as the URL aliases for the opp DTO again now before doing update
        if(aOrgInfoObj.getORGUrlAlias().length()>1 && (! aOrgInfoObj.getORGUrlAlias().contains(" "))){
	        aOpportObj.setOPPUrlAlias(aOrgInfoObj.getORGUrlAlias());
	        //aOpportObj.setOPPUrlAliasOrig(aOrgInfoObj.getORGUrlAliasOrig());
        }
        // ** END url generating section
        
        aOpportObj.setErrorMsg(""); // clear error message of any previously set error messages so that we start with a clean slate for reporting
        iRetCode = m_OrgBRLOObj.editOpportunity( aCurrentUserObj, aOrgInfoObj, aOpportObj, aszSiteVersion, getServlet().getServletContext().getRealPath("/") );
        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );

        
        ArrayList aList = new ArrayList();
        if(iRetCode==-22){
            httpServletRequest.setAttribute( "org", aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(), "org" );
            httpServletRequest.setAttribute( "userlist", aList );
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            
            return actionMapping.findForward( "nonpeditlisting" );

        }
        
        boolean bPrimaryContactChanged=false, bContactsChanged=false, bEmailContactsChanged=false;
        // check to see if any of the Contact data on the given opportunity has changed...
        int iInitUID = aOpportObj.getOPPPrimaryPersonUID();
        int iModifiedUID = aOpportObj.getOPPPrimaryPersonUIDModified();
        if(iModifiedUID != iInitUID){
        	// primary person has changed.  this will, in addition to being updated in the DB, trigger an email notice about change of Primary Contact for said opp
        	bPrimaryContactChanged=true;
        }
        if(! aOpportObj.getOPPContactUIDsModifiedArray().equals(aOpportObj.getOPPContactUIDsArray())){
        	bContactsChanged=true;
        }
        if(! aOpportObj.getOPPContactUIDsRcvEmailModifiedArray().equals(aOpportObj.getOPPContactUIDsRcvEmailArray())){
        	bEmailContactsChanged=true;
        }
        ArrayList aListIdsContactsChanged = null;
        ArrayList aListIdsEmailContactsChanged = null;
        ArrayList aListIdsAndEmailNotifyFlag = null;
        if(bContactsChanged==true || bEmailContactsChanged==true ||bPrimaryContactChanged==true ){ 
        	// need to update contacts for opportunity
// admins don't necessarily have to be written to the usermail table; not unless they are "email" contacts
            // get arrays of uids that show the differences - should prob be done in BRL, but will need to trigger the emailNotify for each user changed
        	aListIdsContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsArray(),aOpportObj.getOPPContactUIDsModifiedArray());
        	aListIdsEmailContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsRcvEmailArray(),aOpportObj.getOPPContactUIDsRcvEmailModifiedArray());

        	aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged,iInitUID,iModifiedUID);
        	//aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged);
        	
        	iRetCode=m_OrgBRLOObj.updateOPPContacts(aListIdsAndEmailNotifyFlag, aOpportObj);
        	
       	  String aszSubdomain = "";
     	  if(httpServletRequest.getParameter("subdomain") != null )
    		  aszSubdomain = httpServletRequest.getParameter("subdomain");
     	  else
     		  aszSubdomain = aOpportObj.getOPPSubdom();
     	  if(aszSubdomain.length()<1)
     		  aszSubdomain = aOpportObj.getOPPSubdom();
            for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
                int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
                int iUID=iIdAndFlag[0];
                int iFlag=iIdAndFlag[1];
                
                // load the contact info for that user
                PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
                aContactPersonObj.setUserUID( iUID );
                iRetCode = m_IndBRLOObj.loadUserContactData( aContactPersonObj );
                aContactPersonObj.setUSPSubdom(aszSubdomain);

                // then trigger the emailNotify Message(s) for that flag
                if(iRetCode==0){
                	// run db update on the org's usermail records

                	// if an email should be triggered, trigger it (for now, skip primary contact changes for this part)
                	if(iFlag!=iNewPrimaryContact && iFlag !=iNoLongerPrimaryContact){
                    	aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
                        // ******************!!!!!!!!!!!!!!! add a method to email that they are now A. Receiving, emails now ************
                        //iRetCode = m_IndBRLOObj.notifyNewAdmin( httpServletRequest, aContactPersonObj, aOrgInfoObj, iIsVolunteerContact );
                        int iEmailUseCase = iFlag;
                        iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, iEmailUseCase, aszPortal );
                        if(iRetCode==-100){
                       	 // The user did not receive any email notification, b/c there was no changed information on the user
                        }
                	}
                }
            }   
            // if getEmailNotifyFlag was called w/o primary contact, but that was changed, then do that part
        
        }
        /*
         * END email trigger section
         */

        // if iRetCode==333, just means the emails or xmlrpc stuff didn't complete fully.... maybe ignore for now??
        httpServletRequest.setAttribute( "org", aOrgInfoObj );

        if(0 == iRetCode || 333==iRetCode){
            session.setAttribute("orgmanagementnid", aszOrgNid);
            if(aOpportObj.getQuestionnaireType().equals("online") && aOpportObj.getQuestionnaireId() == -1){
            	String aszRailsNewQuestionnaire = aszRailsPrefixPath + "cor~voleng~organizations~" + aOrgInfoObj.getORGNID() + "~questionnaires~new";
            	httpServletRequest.setAttribute("redirect", aszRailsNewQuestionnaire);
            	return actionMapping.findForward("mappingpage");
            }else if(bAdminRole==true){
            	return showAdminOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
        	}else{
            	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
        	}
        }
        iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOpportObj );
        //        iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
        httpServletRequest.setAttribute( "userlist", aList );
        
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);

        return actionMapping.findForward( "nonpeditlisting" );
    }
    // end-of method processEditOpp()

	/*
    * delete opportunity
    */
    public ActionForward deleteOpportunity(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
	// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    if(aszPortal.length()>0){
    	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
    	}
    }
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        if(iOrgNid < 1){
     		aszOrgNid = "" + aSessDat.getOrgNID();
         	if(iOrgNid < 1){
	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	             	return actionMapping.findForward( "showlogin" );
	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         	}
         	}
        }
        String aszOppNid = m_BaseHelp.getFormData(oFrm,"oppnid");
        int iOppNid = m_BaseHelp.convertToInt( aszOppNid );
		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			  if(null != aSessDat){
				  aSessDat.setOrgNID( aszOrgNid );
				  aSessDat.setOppNID( aszOppNid );
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
			  }
				if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
		 }
        if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
        String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        allocatedOrgBRLO( httpServletRequest );

        int iNatlAssocNID = 0;
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
        
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
      		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
      	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
         	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//      	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
          int iTid = aOrgInfoObj.getORGAffiliation1TID();
          if(iTid > 0){
         	 aOrgInfoObj.setORGNID( iOrgNid );
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
          }
	     }else{
	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        }
        if(iRetCode == -111){
        	if(bAdminRole==true){
	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
	          	return actionMapping.findForward( "noaccess" );
        	}else{
        		// org did not exist
            	return actionMapping.findForward( "404" );
        	}
        }
        httpServletRequest.setAttribute( "org", aOrgInfoObj );
        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        httpServletRequest.setAttribute( "opp", aOpportObj );
        aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
        if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
        ){
       	 aOpportObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
        }
		if(aOpportObj.getSiteAdministratorUID() > 1){
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}else if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
	     }else{
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}

        if(0 != iRetCode){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
    	}
        iRetCode = m_OrgBRLOObj.deleteOpportunity( aCurrentUserObj, aOrgInfoObj, aOpportObj );
        if(0 != iRetCode){
            m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        	return actionMapping.findForward( "nonpeditlisting" );
    	}
        m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );

        //httpServletRequest.setAttribute("organization", aOrgInfoObj);
        session.setAttribute("orgmanagementnid", aszOrgNid);
    	if(bAdminRole==true){
        	return showAdminOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    	}
    	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    }
    // end-of method deleteOpportunity()


 	/*
      * show Preview opportunity
      */
      public ActionForward showOpportunityPreview(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
	boolean bNatlAssoc = false;
	if(aszPortalRequestType.equals("natlassoc")){
		bNatlAssoc=true;
	}
	// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    if(aszPortal.length()>0){
    	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
    	}
    }
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        String aszOrgNID = null;
        if(httpServletRequest.getParameter("orgnid") != null ){
            aszOrgNID = httpServletRequest.getParameter("orgnid");
        }else{
        	if(null != aSessDat){
        		aszOrgNID = "" + aSessDat.getOrgNID();
        	}
        }
        String aszOppNID = null;
        if(httpServletRequest.getParameter("oppnid") != null ){
            aszOppNID = httpServletRequest.getParameter("oppnid");
        }else{
        	if(null != aSessDat){
        		aszOppNID = "" + aSessDat.getOppNID();
        	}
        }
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
          String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
          int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          if(iOrgNid < 1){
       		aszOrgNid = "" + aSessDat.getOrgNID();
           	if(iOrgNid < 1){
  	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
  	             	return actionMapping.findForward( "showlogin" );
  	         	}else{
	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	         	}
           	}
          }
          String aszOppNid = m_BaseHelp.getFormData(oFrm,"oppnid");
          int iOppNid = m_BaseHelp.convertToInt( aszOppNid );
  		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  			  if(null != aSessDat){
  				  aSessDat.setOrgNID( aszOrgNid );
  				  aSessDat.setOppNID( aszOppNid );
  				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
  			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
  		 }
          if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
          }
      	int iOrgNID = m_BaseHelp.convertToInt( aszOrgNID );
      	if(iOrgNID < 1){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
      	}
      	int iOppNID = m_BaseHelp.convertToInt( aszOppNID );
      	if(iOppNID < 1){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
      	}
          OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
          aOrgInfoObj.setORGNID( aszOrgNID );
          aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
          allocatedOrgBRLO( httpServletRequest );

          int iNatlAssocNID = 0;
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
          
	      if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	       	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//    	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	        int iTid = aOrgInfoObj.getORGAffiliation1TID();
	        if(iTid > 0){
	       	 aOrgInfoObj.setORGNID( iOrgNid );
	            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	        }
 	     }else{
 	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
 	     }
          httpServletRequest.setAttribute( "org", aOrgInfoObj );
          m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
          OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
          httpServletRequest.setAttribute( "opp", aOpportObj );
          aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
  		if(aOpportObj.getSiteAdministratorUID() > 1){
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}else if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
	     }else{
	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
		}

          if(0 != iRetCode){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	}
      	}
          m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
          // load data for organization contact person
          PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
          aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
          allocatedIndBRLO( httpServletRequest );
          iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
          m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );
      	if(null != aSessDat){
        	aSessDat.setTokenKey(null);
        	aSessDat.setOrgNID(null);
        	aSessDat.setOppNID(null);
        	aSessDat.setSubdomain(null);
        	aSessDat.setSiteEmail(null);
    	}
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
          return actionMapping.findForward( "nonpviewlisting" );
      }
      // end-of method showOpportunityPreview()

     public ActionForward showOpportunity(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        return showOpport(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }

    /*
     * show opportunity public listing
     */
     public ActionForward showOpport(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
      	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		int iPortalNID = m_BaseHelp.convertToInt(aszPortalNID);
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
     	int iRetCode=0;
     	
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
        String aszOppNID =  m_BaseHelp.getFormData(oFrm,"oppnid");
     	int iOppNID = m_BaseHelp.convertToInt( aszOppNID );
        String aszUrlAlias =  m_BaseHelp.getFormData(oFrm,"urlalias");
        boolean feed =  m_BaseHelp.getFormDataBoolean(oFrm,"feed");

        allocatedIndBRLO( httpServletRequest );
        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
        aHeadObj.setPortal(aszPortal);
        if(bNatlAssoc == true){
            iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
            if(aHeadObj.getPortalOrgAffilTID()>0){
//           	 aSParm.setOrgAffil1TID(aHeadObj.getPortalOrgAffilTID());
            }
        }
    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        
         OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
         httpServletRequest.setAttribute( "opp", aOpportObj );
         OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
         httpServletRequest.setAttribute( "org", aOrgInfoObj );
         
         String aszRequestType =  m_BaseHelp.getFormData(oFrm,"requesttype");
         aOpportObj.setRequestType(aszRequestType);
      	
         String aszURLAliasFormat = "volunteer/"; // aOrgInfoObj.getPathAutoOppPattern();
         boolean b_isJob = false;
         if(aszRequestType.equals("jobs")){
             feed=true;
             b_isJob=true;
             aszURLAliasFormat = "jobs/";
          }else if(aszRequestType.equals("job")){
             b_isJob=true;
             aszURLAliasFormat = "job/";
           }else if(feed==true){
               aszURLAliasFormat = "volunteers/";
//         	  aszURLAliasFormat = "volunteer/feed/";
           }
         
         if(aszRequestType.equals("volunteers")){
             feed=true;
             aszURLAliasFormat = "volunteers/";
          }
         
         if(bNatlAssoc==true){
        	 aOpportObj.setRequestType("natlassoc");
        	 aOrgInfoObj.setRequestType("natlassoc");
         }
         
         allocatedOrgBRLO( httpServletRequest );
	        aOpportObj.setPortalUID( aszPortalUID);
       	 aOrgInfoObj.setPortalUID( aszPortalUID);
	        
	        String aszOppNumber =  m_BaseHelp.getFormData(oFrm,"oppnumber"); // for any old references indexed by google
	     	int iOppURLID = m_BaseHelp.convertToInt( aszOppNumber );
    
      	if(aszUrlAlias.length()<1 && iOppURLID>0){
            iRetCode = m_OrgBRLOObj.loadOpportunityByURLID( aOpportObj, iOppURLID, 1, aszSiteVersion ); //pass 1 as the last paramater b/c we only want public listings
/* ******************************************************************************************************
      	}else if(b_isFeed==true && feed==true){
      		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, aszUrlAlias.length() > 0 ? OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS : OrganizationInfoDTO.LOADBY_NID_FEED, aszURLAliasFormat ); //pass 1 as the last paramater b/c we only want public listings
*/
      	}else if(feed==true && iPortalNID!=iMeetTheNeedNID){
      		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, aszUrlAlias.length() > 0 ? OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS : OrganizationInfoDTO.LOADBY_NID_FEED, aszURLAliasFormat, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); //pass 1 as the last paramater b/c we only want public listings
//      		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, OrganizationInfoDTO.LOADBY_URL_ALIAS_FEED, aszURLAliasFormat ); //pass 1 as the last paramater b/c we only want public listings
      	}else if(aszUrlAlias.length()<1){
            iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszURLAliasFormat, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
      	}else{
      		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, OrganizationInfoDTO.LOADBY_URL_ALIAS, aszURLAliasFormat, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); //pass 1 as the last paramater b/c we only want public listings
      	}

          if(-111 == iRetCode){ // opportunity did not exist, at least publicly
         	return actionMapping.findForward( "404" );
      	} else if(-1==iRetCode || -999==iRetCode){ // error
         	return actionMapping.findForward( "404" );
     	}
         m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );

         // load data for organization contact person
         PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
         aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
         allocatedIndBRLO( httpServletRequest );
         iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
         m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

         httpServletRequest.setAttribute( "opp", aOpportObj );
         httpServletRequest.setAttribute( "org", aOrgInfoObj );
         
         if(aOpportObj.getORGNID()<1){
          	return actionMapping.findForward( "opppubliclisting" );
         }
         aOrgInfoObj.setORGNID( aOpportObj.getORGNID() );
         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion );
         if(0 != iRetCode){
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
             if(0 != iRetCode){
             	return actionMapping.findForward( "opppubliclisting" );
         	}
     	}
         if(httpServletRequest.getAttribute("error")!=null){
        	 aOrgInfoObj.appendErrorMsg(httpServletRequest.getAttribute("error").toString());
         }

         return actionMapping.findForward( "opppubliclisting" );
     }
     // end-of method showOpportunity()

    //=== END manage opportunity section  ===>
    //=== END manage opportunity section  ===>
    //=== END manage opportunity section  ===>

      
      //=== BEGIN iwanttohelp email section ==>
      //=== BEGIN iwanttohelp email section ==>
      //=== BEGIN iwanttohelp email section ==>

      public ActionForward processIVolHelp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	AppSessionDTO aSessDat=null;
      	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	if(null == aSessDat)		return showOpport( actionMapping, actionForm, httpServletRequest, httpServletResponse );
      	return processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }

      public ActionForward processPartnerHelp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	AppSessionDTO aSessDat=null;
      	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
      	if(null == aSessDat)		return showOpport( actionMapping, actionForm, httpServletRequest, httpServletResponse );
      	aSessDat.setTokenKey( AppSessionDTO.TOKEN_PARTNERHELP );
      	return processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }

      
      /*
      * process I Want to  Button for a given opportunity
      * 
      */
      public ActionForward processIWantToHelp1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    	  // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    	  if(aszPortal.length()>0){
    		  if(aszPortalNID.length()==0){
          		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
      			return actionMapping.findForward("404");
    		  }
    	  }

          AppSessionDTO aSessDat=null;
          aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          if(null == aSessDat){
        	  return actionMapping.findForward( "404" );
          }
    	  
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          String aszOppNID =  m_BaseHelp.getFormData(oFrm,"oppnid");
          String aszOrgNID =  m_BaseHelp.getFormData(oFrm,"orgnid");
          
          allocatedIndBRLO( httpServletRequest );
          AppCodeInfoDTO aSessObj = new AppCodeInfoDTO();
          if(bNatlAssoc==true){
             iRetCode = m_IndBRLOObj.getPortalInfo( aSessObj );
             if(aSessObj.getPortalOrgAffilTID()>0){
//            	 aSParm.setOrgAffil1TID(aHeadObj.getPortalOrgAffilTID());
             }
          }

//       	getLoggedInStatus(httpServletRequest, httpServletResponse);
System.out.println("line 4875 - before useragent stuff ");
          String aszUserAgent=httpServletRequest.getHeader("user-agent");
       	String aszReferrer=httpServletRequest.getHeader("referer");
       	String aszRemoteIP=httpServletRequest.getHeader("X-Forwarded-For");
   
         	PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
          if(true == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
             	aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          }

       	int iOppNID = m_BaseHelp.convertToInt( aszOppNID );
          int iOrgNID = m_BaseHelp.convertToInt( aszOrgNID );
          if(iOppNID < 1){
        	  if(null != aSessDat)		iOppNID = aSessDat.getOppNID();
        	  else						return actionMapping.findForward( "404" );
          }
          if(iOrgNID < 1){
        	  if(null != aSessDat)		iOrgNID = aSessDat.getOrgNID();
          }
          OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
          allocatedOrgBRLO( httpServletRequest );
          
          aOpportObj.setPortalUID( aszPortalUID);
          
          OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();

          if(bNatlAssoc==true){
          	 aOpportObj.setRequestType("natlassoc");
          	aOrgInfoObj.setRequestType("natlassoc");
          }
          iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show ONLY published
          
          if(0 != iRetCode){
        	  return actionMapping.findForward( "404" ); 
          }
          m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
          aOrgInfoObj.setORGNID( aOpportObj.getORGNID() );
          aOrgInfoObj.setPortalUID( aszPortalUID);
          aOrgInfoObj.setNatlAssociationNID(aszPortalNID);

          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, aszSiteVersion ); 
          if(0 != iRetCode){
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT, aszSiteVersion );
        	  return showOpport(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          }
          
          aOpportObj.setORGPartner(aOrgInfoObj.getORGPartner());
          aOpportObj.setORGPartner2(aOrgInfoObj.getORGPartner2());
          aOpportObj.setORGPartner3(aOrgInfoObj.getORGPartner3());
          aOpportObj.setORGPartner4(aOrgInfoObj.getORGPartner4());
          aOpportObj.setORGPartner5(aOrgInfoObj.getORGPartner5());
          
          
          // ali - 2006.08.14 - should maybe be redone
          // actual setting of email values.           
          EmailInfoDTO aHeadObj = new EmailInfoDTO();
          // load the form information from the IWantToVolunteer Email
          iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aHeadObj, false);
          
          PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
          aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
          allocatedIndBRLO( httpServletRequest );
          iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion ); // if(iRetCode==-222)//somehow the user doesn't have a uprofile, but the email address should still be loaded
          m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

          // contact person info
          if(aHeadObj.getEmailContactFN().length() < 2)
        	  aHeadObj.setEmailContactFN( aContactPersonObj.getUSPNameFirst() );
          if(aHeadObj.getEmailContactLN().length() < 2)
        	  aHeadObj.setEmailContactLN( aContactPersonObj.getUSPNameLast() );
          if(aHeadObj.getEmailToUser().length() < 2)
        	  aHeadObj.setEmailToUser(aContactPersonObj.getUSPEmail1Addr()); // used in the EmailActions.sendEmail in the case where loading OppContactList doesn't work
          
          // org, opp info to list
          if(aHeadObj.getEmailOppNID() < 1)
        	  aHeadObj.setEmailOppNID( aOpportObj.getOPPNID());
          if(aHeadObj.getEmailOrgNID() < 1)
        	  aHeadObj.setEmailOrgNID(aOrgInfoObj.getORGNID());
          if(aHeadObj.getEmailFromUser().length() < 2)
        	  aHeadObj.setEmailFromUser(aCurrentUserObj.getUSPEmail1Addr());
          if(aHeadObj.getEmailVolFN().length() < 2)
        	  aHeadObj.setEmailVolFN(aCurrentUserObj.getUSPNameFirst());
          if(aHeadObj.getEmailVolLN().length() < 2)
        	  aHeadObj.setEmailVolLN(aCurrentUserObj.getUSPNameLast());
          if(aHeadObj.getEmailVolPhone1().length() < 2)
        	  aHeadObj.setEmailVolPhone1(aCurrentUserObj.getUSPPhone1());
          if(aHeadObj.getEmailFromUser().length() < 2)
        	  aHeadObj.setEmailFromUser(aCurrentUserObj.getUSPEmail1Addr());
          if(aHeadObj.getEmailVolUID() < 1)
        	  aHeadObj.setEmailVolUID(aCurrentUserObj.getUserUID());

      	aHeadObj.setEmailVolUID(aCurrentUserObj.getUserUID());
        
        String aszStatus=" opp_nid "+iOppNID+"; org_nid "+iOrgNID+"; current_uid "+aCurrentUserObj.getUserUID(); 

          
          
          if(aHeadObj.getEmailOrgName().length() < 1)
        	  aHeadObj.setEmailOrgName(aOpportObj.getOPPOrgName());
          if(aHeadObj.getEmailOppName().length() < 1)
        	  aHeadObj.setEmailOppName(aOpportObj.getOPPTitle());
          
          aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );

          aHeadObj.setEmailSentStatus(aszStatus);
          aHeadObj.setEmailUserAgent(aszUserAgent);
          aHeadObj.setEmailReferrer(aszReferrer);
          aHeadObj.setEmailRemoteIP(aszRemoteIP);
          
          httpServletRequest.setAttribute( "opp", aOpportObj );
          httpServletRequest.setAttribute( "org", aOrgInfoObj );
          // if this is a bot, just forward the user along to reload the listing; don't bring them to application & don't log them
  		if(httpServletRequest.getHeader("user-agent").contains("bot")){
        	return actionMapping.findForward( "opppubliclisting" );
    	}

     		//first get the email_id from the application_inits table to make sure this doesn't insert another one from cookielogin reloading
  		iRetCode = m_IndBRLOObj.getEmailId( aHeadObj, 1 );
  		if(iRetCode != 0 || 
  				(aOpportObj.isCityVisionInternship() || aOpportObj.getORGNID()==73734 || aOpportObj.isCityVisionInternshipLegacy()) 
  				
  		){
  			iRetCode = m_IndBRLOObj.logApplication( aHeadObj ); 
  		}
  		
        boolean b_loadLegacyInternship = false;
        boolean b_loadInternship = false;
  		String aszRequestType =  m_BaseHelp.getFormData(oFrm,"requesttype");
  		if(aOpportObj.isCityVisionInternship()){
  			if(aszRequestType.equals("cvintern")){
  			b_loadInternship = true;
  			}else{
  	  			b_loadLegacyInternship = true;
  			}
  		}else if(aOpportObj.getORGNID()==73734
        		  || aOpportObj.isCityVisionInternshipLegacy()
        		  ){
  			b_loadLegacyInternship = true;
  		}

  		
          /* Redirect to city vision internship application if the opportunity is with city vision */
          if(b_loadInternship
        		  || b_loadLegacyInternship
          ) {
        	  if(b_loadLegacyInternship){
/*
        		  httpServletRequest.setAttribute("redirectpage","city_vision_internship_application_legacy");
		        	aHeadObj.setEmailSentStatus("city_vision_internship_application "+ aOpportObj.isCityVisionInternship() + 
        			" org nid is "+ aOpportObj.getORGNID() );
*/
	              	httpServletRequest.setAttribute("redirectpage","city_vision_internship_application+org_nid="+aOpportObj.getORGNID()+"+opp_nid="+aOpportObj.getOPPNID());
	            	aHeadObj.setEmailSentStatus("city_vision_internship_application_new "+ aOpportObj.isCityVisionInternship() + 
	            			" org nid is "+ aOpportObj.getORGNID() );
        	  
        	  
        	  }else{
              	httpServletRequest.setAttribute("redirectpage","city_vision_internship_application+org_nid="+aOpportObj.getORGNID()+"+opp_nid="+aOpportObj.getOPPNID());
            	aHeadObj.setEmailSentStatus("city_vision_internship_application_new "+ aOpportObj.isCityVisionInternship() + 
            			" org nid is "+ aOpportObj.getORGNID() );
        	  }
        	aHeadObj.setEmailVolUID(aCurrentUserObj.getUserUID());
        	iRetCode = m_IndBRLOObj.createEmail( aHeadObj, -1 );
        	
        	aszStatus="iWantToHelp - cv internship application " + aOpportObj.getOPPNID();
      		iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	
        	return actionMapping.findForward("mappingpage");
          }

			aszStatus="	iWantToHelp -  application for opp nid " + aOpportObj.getOPPNID()+";      aCurrentUserObj.getUserUID() is "+aCurrentUserObj.getUserUID();
			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	
	        String aszDomain = "";
			aszDomain = m_IndBRLOObj.getDomainName( httpServletRequest);
	        Cookie cookie = new Cookie ( "redirectApply", aszOppNID );
			cookie.setPath("/");
			cookie.setDomain(aszDomain);
	        cookie.setMaxAge(600);

          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	aSessDat.setOrgNID( iOrgNID );
          	aSessDat.setOppNID( iOppNID );
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IVOLHELPSIGNIN );
          	}else if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN );
          	}else{
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IWANTTOHELP );
          	}
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	        httpServletResponse.addCookie(cookie);
              	return actionMapping.findForward( "showlogin" );
          	}else{
                  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
          
          // access data on the current user so we can check their country code, etc
//          PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
/* says it's dead code
          if(null == aCurrentUserObj) {

  			aszStatus=" aCurrentUserObj is null; try to do cookie login";
  			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	
        	  
          	aSessDat.setOrgNID( iOrgNID );
          	aSessDat.setOppNID( iOppNID );
           	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IVOLHELPSIGNIN );
          	}else if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN );
          	}else{
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IWANTTOHELP );
          	}
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	        httpServletResponse.addCookie(cookie);
              	return actionMapping.findForward( "showlogin" );
          	}else{

      			aszStatus="  calling cookielogin";
      			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	
  		
                  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
*/
			aszStatus="  aCurrentUserObj is not null;  uid "+aCurrentUserObj.getUserUID();
			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	

          if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
          	aSessDat.setOrgNID( iOrgNID );
          	aSessDat.setOppNID( iOppNID );
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IVOLHELPSIGNIN );
          	}else if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN );
          	}else{
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IWANTTOHELP );
          	}
			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
          }
          
          if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	aSessDat.setOrgNID( iOrgNID );
          	aSessDat.setOppNID( iOppNID );
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IVOLHELPSIGNIN );
          	}else if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP )){
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN );
          	}else{
          		aSessDat.setTokenKey( AppSessionDTO.TOKEN_IWANTTOHELP );
          	}

			aszStatus="  NOT USER_LOGINOK;  uid "+aCurrentUserObj.getUserUID();
			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	

          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	        httpServletResponse.addCookie(cookie);
              	return actionMapping.findForward( "showlogin" );
          	}else{
                  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
          

          // if the organization says that the volunteer
          // 		"Must Be Resident of Country or Already Have Appropriate Visa" - pass the Organization & the Current User
          // and this is not the case, catch it and display that error message
          iRetCode = m_OrgBRLOObj.checkIfORGTakesIntlVols( aOrgInfoObj, aOpportObj, aCurrentUserObj ); 
          if(999 == iRetCode ){

  			aszStatus="        triggered does not take international vols;  uid "+aCurrentUserObj.getUserUID();
  			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	

          	aOrgInfoObj.appendErrorMsg("<FONT color=\"red\" ><center><b><br><pre style=\"font-family:Arial,Helvetica,Verdana,sans-serif\">\nSorry, this Organization does not take volunteers or applicants from outside its country\n</pre></b></center></FONT>");
          	return actionMapping.findForward( "opppubliclisting" );
      	}
          // load data for OPPORTUNITY contact person
          
          m_BaseHelp.setFormData(oFrm,"orgnid", "" + aOrgInfoObj.getORGNID() );
          m_BaseHelp.setFormData(oFrm,"oppnid", "" + aOpportObj.getOPPNID() );
          m_BaseHelp.setFormData(oFrm,"voluid", "" + aCurrentUserObj.getUserUID() );
          m_BaseHelp.setFormData(oFrm,"volrailsid", "" + aCurrentUserObj.getUserRailsID() );
          iRetCode = m_OrgBRLOObj.prepForSendEmailForOpportunity( aCurrentUserObj, aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion ); // gets the Firstname, LAstname, etc of the Contact USer
          if(iRetCode!=0){ // something failed; either the org/opp email address, or the vol email address is missing.  what to do in this case...???
        	  // first, try loading just the um_user
        	  //	table data, so that we can get the email address regardless of whether the user has a profile or not
              iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.SIMPLE_LOADBY_UID, aszSiteVersion );
              iRetCode = m_OrgBRLOObj.prepForSendEmailForOpportunity( aCurrentUserObj, aContactPersonObj, PersonInfoDTO.SIMPLE_LOADBY_UID, aszSiteVersion );
              if(iRetCode!=0){
            	  // try loading the owner of the organizational node, if the owner of the opp nid failed.  
            	  aContactPersonObj.setUserUID( aOrgInfoObj.getORGUID() );
                  iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
                  iRetCode = m_OrgBRLOObj.prepForSendEmailForOpportunity( aCurrentUserObj, aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion ); 
                  if(iRetCode!=0){ 
                      iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.SIMPLE_LOADBY_UID, aszSiteVersion );
                      iRetCode = m_OrgBRLOObj.prepForSendEmailForOpportunity( aCurrentUserObj, aContactPersonObj, PersonInfoDTO.SIMPLE_LOADBY_UID, aszSiteVersion ); 
                  }
              }        	  
          }
          m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );

          // check first to see if the user has already hit his/her limit on number of emails they can volunteer with (originally set to 10/month)
          ArrayList aList = new ArrayList();
          int iType=EmailInfoDTO.LOADBY_VOLUNTEER_MAX;
          aHeadObj.setEmailMax(iMaxEmailsNumber);
          aHeadObj.setEmailMaxInterval(aszMaxEmailsInterval);
          iRetCode = m_OrgBRLOObj.getEmailList( aList, aHeadObj, iType );
          httpServletRequest.setAttribute( "opp", aOpportObj );
          httpServletRequest.setAttribute( "org", aOrgInfoObj );
          httpServletRequest.setAttribute("emailinfo", aHeadObj);
          if(iRetCode != 0 && iRetCode != -1 ){

  			aszStatus="      5107  triggered maximum emails for user;  uid "+aCurrentUserObj.getUserUID();
  			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	

          
        	  return actionMapping.findForward( "maxemails" );
          }
          int maxNumber=10;
          int numberSentLastMonth=aList.size();
          if(numberSentLastMonth >= maxNumber){
        	  // clear out the email for signin process
	          aSessDat=null;
	          aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	          // clear the Token Key for this session for the next login
	          if(aSessDat.getTokenKey() != null && aSessDat.getTokenKey().length()>1)
	        	  aSessDat.setTokenKey(null);
	          if(aSessDat.getOrgNID() > 1)
	        	  aSessDat.setOrgNID(null);
	          if(aSessDat.getOppNID() > 1)
	        	  aSessDat.setOppNID(null);
	          if(aSessDat.getSubdomain() != null && aSessDat.getSubdomain().length() > 1)
	        	  aSessDat.setSubdomain(null);
	          if(aSessDat.getSiteEmail() != null && aSessDat.getSiteEmail().length() > 1)
	        	  aSessDat.setSiteEmail(null);

				aszStatus="       5127 triggered maximum emails for user;  uid "+aCurrentUserObj.getUserUID();
				iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	

	          return actionMapping.findForward( "maxemails" );
          }
          
          // redirect the user to the rails app, instead

		  String aszRailsApply = aszRailsPrefixPath + aszRailsApplyPositionPath + iOppNID + "~apply";
		  httpServletRequest.setAttribute("redirect", aszRailsApply);

			aszStatus="        open apply page;  uid "+aCurrentUserObj.getUserUID();
			iRetCode = m_IndBRLOObj.updateLogApplication( aHeadObj );
System.out.println(aszStatus);        	


	 	  return actionMapping.findForward("mappingpage");			  
//          return actionMapping.findForward( "oppemailsent" );
      }
      // end-of method processIWantToHelp1()
      
      
      /*
      * process I Want to  Button for a given opportunity
      * 
      */
      public ActionForward processIWantToHelpExternal(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    	  // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    	  if(aszPortal.length()>0){
    		  if(aszPortalNID.length()==0){
      			return actionMapping.findForward("404");
    		  }
    	  }

          AppSessionDTO aSessDat=null;
          aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          if(null == aSessDat){
        	  return actionMapping.findForward( "404" );
          }
    	  
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          String aszOppNID =  m_BaseHelp.getFormData(oFrm,"oppnid");
          String aszSubdomain =  m_BaseHelp.getFormData(oFrm,"subdomain");
          String aszSiteEmail =  m_BaseHelp.getFormData(oFrm,"siteemail");

          String aszID = "";
          boolean b_isJob = false;
          boolean b_isFeed = true;
          String aszUrlAlias =  m_BaseHelp.getFormData(oFrm,"urlalias");
          if(httpServletRequest.getParameter("id") != null ){
        	  b_isJob=true;
        	  aszID=httpServletRequest.getParameter("id");
          }
          String aszSource = "";
          String aszRequestType = "volunteers";
          if(httpServletRequest.getParameter("source") != null ){
        	  aszSource = httpServletRequest.getParameter("source");
           	  if(aszUrlAlias.startsWith("jobs/")){
           		  b_isJob=true;
           		  aszRequestType="jobs";
        	  }
          }
          String aszUrlAliasFormat = aszRequestType + "/";
        
          
          if(aszOppNID.length()==0 && aszID.length()>0){
        	  aszOppNID=aszID;
          }
          allocatedIndBRLO( httpServletRequest );
          String aszEmailMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
          String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
          if(aszSubdomain.length()<1)	aszSubdomain=aszDomMain;
          if(aszSiteEmail.length()<1)	aszSiteEmail=aszEmailMain;
          
          int iOppNID = m_BaseHelp.convertToInt( aszOppNID );
          if(iOppNID < 1){
        	  if(null != aSessDat)		iOppNID = aSessDat.getOppNID();
        	  else						return actionMapping.findForward( "404" );
          }
          OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
          allocatedOrgBRLO( httpServletRequest );

          int iUrlAliasLength = aszUrlAlias.length();
          if(iUrlAliasLength > 5 && b_isJob==true){ //    jobs/youth-programs-girl-guard-leader-2
        	  aszUrlAlias=aszUrlAlias.substring(5, iUrlAliasLength);        	  
          }else if(iUrlAliasLength > 11 && b_isJob==false){ //    volunteers/youth-programs-girl-guard-leader-2
        	  aszUrlAlias=aszUrlAlias.substring(11, iUrlAliasLength);        	  
          }

          aOpportObj.setPortalUID( aszPortalUID);
          aOpportObj.setOPPNID(iOppNID);
          aOpportObj.setRequestType(aszRequestType);
          if(b_isFeed == true){ // looks at urbmi5_data tbl_feeds
        	  iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS, aszUrlAliasFormat, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); 
          }else{
        	  iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, OrganizationInfoDTO.LOADBY_NID_FEED, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); 
          }
//          iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 1, aszUrlAlias, OrganizationInfoDTO.LOADBY_URL_ALIAS_FEED ); 
          if(0 != iRetCode){
        	  return actionMapping.findForward( "404" ); 
          }
          String aszIWantToHelpURL =  aOpportObj.getOPPReferralURL();

          // access data on the current user so we can check their country code, etc
          getLoggedInStatus(httpServletRequest, httpServletResponse);

          //PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );

          OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
          m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
          aOrgInfoObj.setORGNID( aOpportObj.getORGNID() );
          aOrgInfoObj.setPortalUID( aszPortalUID);

         // load data for OPPORTUNITY contact person
          PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
          aContactPersonObj.setUserUID( aOpportObj.getOPPUID() );
          allocatedIndBRLO( httpServletRequest );

          // actual setting of email values.           
          EmailInfoDTO aHeadObj = new EmailInfoDTO();
          // load the form information from the IWantToVolunteer Email
          iRetCode = m_IndActHelp.getEmailDataFromForm(oFrm, aHeadObj, false);
          
          // contact person info
          if(aHeadObj.getEmailContactFN().length() < 2)
        	  aHeadObj.setEmailContactFN( aContactPersonObj.getUSPNameFirst() );
          if(aHeadObj.getEmailContactLN().length() < 2)
        	  aHeadObj.setEmailContactLN( aContactPersonObj.getUSPNameLast() );
          if(aHeadObj.getEmailToUser().length() < 2)
        	  aHeadObj.setEmailToUser(aContactPersonObj.getUSPEmail1Addr()); // used in the EmailActions.sendEmail in the case where loading OppContactList doesn't work
          
          // org, opp info to list
          if(aHeadObj.getEmailOppNID() < 1)
        	  aHeadObj.setEmailOppNID( aOpportObj.getOPPNID());
          if(aHeadObj.getEmailOrgNID() < 1)
        	  aHeadObj.setEmailOrgNID(aOrgInfoObj.getORGNID());
          if(aHeadObj.getEmailVolUID() < 1)
        	  aHeadObj.setEmailVolUID(aCurrentUserObj.getUserUID());
          if(aHeadObj.getEmailOppName().length() < 2)
        	  aHeadObj.setEmailOppName(aOpportObj.getOPPTitle());
          if(aHeadObj.getEmailOrgName().length() < 2){
        	  aHeadObj.setEmailOrgName(aOrgInfoObj.getORGOrgName());
	          if(aHeadObj.getEmailOrgName().length() < 2)
	        	  aHeadObj.setEmailOrgName(aOpportObj.getORGOrgName());
          }
          if(aHeadObj.getEmailOrgAddr1().length() < 2)
        	  aHeadObj.setEmailOrgAddr1(aOrgInfoObj.getORGAddrLine1());
          if(aHeadObj.getEmailOrgCity().length() < 2)
        	  aHeadObj.setEmailOrgCity(aOrgInfoObj.getORGAddrCity());
          if(aHeadObj.getEmailOrgState().length() < 2)
        	  aHeadObj.setEmailOrgState(aOrgInfoObj.getORGAddrStateprov());
          if(aHeadObj.getEmailOrgProv().length() < 2)
        	  aHeadObj.setEmailOrgProv(aOrgInfoObj.getORGAddrOtherProvince());
          if(aHeadObj.getEmailOrgCountry().length() < 2)
        	  aHeadObj.setEmailOrgCountry(aOrgInfoObj.getORGAddrCountryName());
          if(aHeadObj.getEmailOrgPhone().length() < 2)
        	  aHeadObj.setEmailOrgPhone(aOrgInfoObj.getORGOrgPhone1());
          if(aHeadObj.getEmailOrgWeb().length() < 2)
        	  aHeadObj.setEmailOrgWeb(aOrgInfoObj.getORGWebpage());
          if(aHeadObj.getEmailSTMReferencesText().length() < 2)
        	  aHeadObj.setEmailSTMReferencesText(aOpportObj.getOPPSTMReferences());
          if(aHeadObj.getEmailOppBkgrnd().length() < 1){
        	  String aszTemp=aOpportObj.getOPPBackgroundCheck();
        	  if (aszTemp.equalsIgnoreCase("Yes"))
        		  aHeadObj.setEmailOppBkgrnd("This organization requires a background check.");
        	  else 
        		  aHeadObj.setEmailOppBkgrnd("");
          }
          if(aHeadObj.getEmailOppReference().length() < 1){
        	  String aszTemp=aOpportObj.getOPPReferenceReq();
        	  if (aszTemp.equalsIgnoreCase("Yes"))
        		  aHeadObj.setEmailOppReference("This organization requires references.");
        	  else
        		  aHeadObj.setEmailOppReference("");
          }
          if(aHeadObj.getEmailFromUser().length() < 2)
        	  aHeadObj.setEmailFromUser(aCurrentUserObj.getUSPEmail1Addr());
          if(aHeadObj.getEmailBodyTextRes().length() < 2)
//        	  aHeadObj.setEmailBodyTextRes(aCurrentUserObj.getUSPVolResume());
          if(aHeadObj.getEmailResumeFilePath().length() < 2)
        	  aHeadObj.setEmailResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
          //String DUPLICATE 
          if(aHeadObj.getEmailVolUID() < 1)
        	  aHeadObj.setEmailVolUID(aCurrentUserObj.getUserUID());
          if(aHeadObj.getEmailVolFN().length() < 2)
        	  aHeadObj.setEmailVolFN(aCurrentUserObj.getUSPNameFirst());
          if(aHeadObj.getEmailVolLN().length() < 2)
        	  aHeadObj.setEmailVolLN(aCurrentUserObj.getUSPNameLast());
          if(aHeadObj.getEmailVolPhone1().length() < 2)
        	  aHeadObj.setEmailVolPhone1(aCurrentUserObj.getUSPPhone1());
          if(aHeadObj.getEmailVolPhone1Type().length() < 2)
        	  aHeadObj.setEmailVolPhone1Type(aCurrentUserObj.getUSPPhone1Type());
          if(aHeadObj.getEmailVolPhone2().length() < 2 && aCurrentUserObj.getUSPPhone2().length() > 2)
        	  aHeadObj.setEmailVolPhone2(aCurrentUserObj.getUSPPhone2());
          if(aHeadObj.getEmailVolPhone2Type().length() < 2)
        	  aHeadObj.setEmailVolPhone2Type(aCurrentUserObj.getUSPPhone2Type());
          if( 	(aSessDat.getTokenKey().equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELP )) ||
      			(aSessDat.getTokenKey().equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELP )) 
      		){
        	  aHeadObj.setEmailVolChris("");
          }else{
        	  if(aHeadObj.getEmailVolChris().length() < 2){
        		  String aszTemp=aCurrentUserObj.getUSPChristianP();
        		  if ( (aszTemp.equalsIgnoreCase("Y")) || (aszTemp.equalsIgnoreCase("Yes")) )
        			  aHeadObj.setEmailVolChris("Is this person a Christian?   Yes");
        		  else if ( (aszTemp.equalsIgnoreCase("N")) || (aszTemp.equalsIgnoreCase("No")) )
        			  aHeadObj.setEmailVolChris("Is this person a Christian?   No");
        		  else 
        			  aHeadObj.setEmailVolChris("This person has not indicated their faith.");
        	  }
          }

        	if(aHeadObj.getEmailRailsSkills().length() < 2 && aCurrentUserObj.getUserRailsSkills().length() > 2)
          		aHeadObj.setEmailRailsSkills(aCurrentUserObj.getUserRailsSkills());
        int index=0;
  		int[] a_iTmp= new int[3];
      	// filter out volunteer skills that are clearly faith-based from the ivolunteering email
      	int iVolSkills1TID=0, iVolSkills2TID=0, iVolSkills3TID=0;
      	if(aHeadObj.getEmailVolSkills().length() < 2){
      		aHeadObj.setEmailVolSkills(aCurrentUserObj.getUSPVolSkills());
      		iVolSkills1TID = aCurrentUserObj.getUSPVolSkills1TID();
          	String aszVolSkills = aHeadObj.getEmailVolSkills(); 
          	
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
          		if(aszVolSkills.equalsIgnoreCase("Musician / Worship Leader") ||
          				iVolSkills1TID==4745){
          			aHeadObj.setEmailVolSkills("Musician");
          			a_iTmp[index]=4745;
          			index++;
          		}else if( (aszVolSkills.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
          				(aszVolSkills.equalsIgnoreCase("Preacher / Minister")) ||
          				(aszVolSkills.equalsIgnoreCase("Deaf Minister")) ||
          				(iVolSkills1TID==4748) ||
          				(iVolSkills1TID==4749) ||
          				(iVolSkills1TID==8122)){
          			aHeadObj.setEmailVolSkills("");
          		}else{
          			if(iVolSkills1TID>1){
          				a_iTmp[index]=iVolSkills1TID;
              			index++;
          			}
          		}
          	}else{
      			if(iVolSkills1TID>1){
      				a_iTmp[index]=iVolSkills1TID;
          			index++;
      			}
          	}
      	}            	
      	if(aHeadObj.getEmailVolSkills2().length() < 2){
      		aHeadObj.setEmailVolSkills2(aCurrentUserObj.getUSPVolSkills2());
      		iVolSkills2TID = aCurrentUserObj.getUSPVolSkills2TID();
          	String aszVolSkills2 = aHeadObj.getEmailVolSkills2(); 
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
          		if(aszVolSkills2.equalsIgnoreCase("Musician / Worship Leader") ||
          				iVolSkills2TID==4745){
          			aHeadObj.setEmailVolSkills2("Musician");
          			a_iTmp[index]=4745;
          			index++;
          		}else if( (aszVolSkills2.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
          				(aszVolSkills2.equalsIgnoreCase("Preacher / Minister")) ||
          				(aszVolSkills2.equalsIgnoreCase("Deaf Minister")) ||
          				(iVolSkills2TID==4748) ||
          				(iVolSkills2TID==4749) ||
          				(iVolSkills2TID==8122)){
          			aHeadObj.setEmailVolSkills2("");
          		}else{
          			if(iVolSkills2TID>1){
          				a_iTmp[index]=iVolSkills2TID;
              			index++;
          			}
          		}
          	}else{
      			if(iVolSkills2TID>1){
      				a_iTmp[index]=iVolSkills2TID;
          			index++;
      			}
          	}
      	}
      	if(aHeadObj.getEmailVolSkills3().length() < 2){
      		aHeadObj.setEmailVolSkills3(aCurrentUserObj.getUSPVolSkills3());
      		iVolSkills3TID = aCurrentUserObj.getUSPVolSkills3TID();
          	String aszVolSkills3 = aHeadObj.getEmailVolSkills3(); 
          	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){
          		if(aszVolSkills3.equalsIgnoreCase("Musician / Worship Leader") ||
          				iVolSkills3TID==4745){
          			aHeadObj.setEmailVolSkills3("Musician");
          			a_iTmp[index]=4745;
          			index++;
          		}else if( (aszVolSkills3.equalsIgnoreCase("Prayer Minister / Intercessor")) ||
          				(aszVolSkills3.equalsIgnoreCase("Preacher / Minister")) ||
          				(aszVolSkills3.equalsIgnoreCase("Deaf Minister")) ||
          				(iVolSkills3TID==4748) ||
          				(iVolSkills3TID==4749) ||
          				(iVolSkills3TID==8122)){
          			aHeadObj.setEmailVolSkills3("");
          		}else{
          			if(iVolSkills3TID>1){
          				a_iTmp[index]=iVolSkills3TID;
              			index++;
          			}
          		}
          	}else{
      			if(iVolSkills3TID>1){
      				a_iTmp[index]=iVolSkills3TID;
          			index++;
      			}
          	}
      	}
      	ArrayList askillsList = new  ArrayList ( 2 );
      	int[] a_iContainer= new int[1];
      	iRetCode = m_IndBRLOObj.getTaxonomyCodeList(askillsList, vidSkill);
  		// add Skills Array
      	//	still need to screen for ivolunteering tags, though
  		String aszSkillTypes = "";
      	if ( aCurrentUserObj.getUSPSkillTypesArray()!=null ){
  			if ( aCurrentUserObj.getUSPSkillTypesArray().length > 0 ){
  				a_iContainer= new int[50];
  				a_iContainer = aCurrentUserObj.getUSPSkillTypesArray();
  				int iTemp = 0;
  				if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){ 
  					for(int i=0; i < askillsList.size(); i++){
  						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(i);
  						if(null == aAppCode) continue;
  						String aszDisplay = aAppCode.getAPCDisplay();
  						int iSubType = aAppCode.getAPCIdSubType();
  						if (iSubType == 4748 ||
  							iSubType == 4749){
  						}else if (iSubType == 4745){
  							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  								iTemp = a_iContainer[indexArray]; 
  								if(iTemp==iSubType) aszSkillTypes += "Musician\n         ";
  							}
  						}else if (iSubType == 8122){
  							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  								iTemp = a_iContainer[indexArray];
  								if(iTemp==iSubType) aszSkillTypes +="Deaf Outreach\n         ";
  							}
  						}else{
  							for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  								iTemp = a_iContainer[indexArray];
  								if(iTemp==iSubType) aszSkillTypes += aAppCode.getAPCDisplay() + "\n         ";
  							}
  						}
  						iTemp++;
  					}
  				} else { 
  					for(int i=0; i < askillsList.size(); i++){
  						AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(i);
  						if(null == aAppCode) continue;
  						int iSubType = aAppCode.getAPCIdSubType();
  						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  							iTemp = a_iContainer[indexArray];
  							if(iTemp==iSubType) aszSkillTypes += aAppCode.getAPCDisplay() + "\n         ";
  						}
  					}
  				} 					
  				if(aszSkillTypes.endsWith(", ")){
  					aszSkillTypes = aszSkillTypes.substring(0, aszSkillTypes.length()-2);
  				}
  				aHeadObj.setEmailVolSkillTypes(aszSkillTypes);
  			}
  		}else{
  			a_iContainer= a_iTmp;
  			int iTemp = 0;
  			if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELPSIGNIN )){ 
  				for(int i=0; i < askillsList.size(); i++){
  					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(i);
  					if(null == aAppCode) continue;
  					String aszDisplay = aAppCode.getAPCDisplay();
  					int iSubType = aAppCode.getAPCIdSubType();
  					if (iSubType == 4748 ||
  						iSubType == 4749){
  					}else if (iSubType == 4745){
  						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  							iTemp = a_iContainer[indexArray];
  							if(iTemp==iSubType) aszSkillTypes += "Musician\n         ";
  						}
  					}else if (iSubType == 8122){
  						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  							iTemp = a_iContainer[indexArray];
  							if(iTemp==iSubType) aszSkillTypes +="Deaf Outreach\n         ";
  						}
  					}else{
  						for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  							iTemp = a_iContainer[indexArray];
  							if(iTemp==iSubType) aszSkillTypes += aAppCode.getAPCDisplay() + "\n         ";
  						}
  					}
  					iTemp++;
  				}
  			} else { 
  				for(int i=0; i < askillsList.size(); i++){
  					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)askillsList.get(i);
  					if(null == aAppCode) continue;
  					int iSubType = aAppCode.getAPCIdSubType();
  					for(int indexArray=0; indexArray < a_iContainer.length; indexArray++){
  						iTemp = a_iContainer[indexArray];
  						if(iTemp==iSubType) aszSkillTypes += aAppCode.getAPCDisplay() + "\n         ";
  					}
  				}
  			} 	
  			aHeadObj.setEmailVolSkillTypes(aszSkillTypes);
  		}
          aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          
          if(b_isJob==true){
        	  aHeadObj.setEmailSentStatus("(feeds - " + aszSource + ": " + aszIWantToHelpURL + ")");
          }else{
        	  aHeadObj.setEmailSentStatus("(feeds - " + aszSource + ": " + aszIWantToHelpURL + ")");
          }
          iRetCode=1;
          String aszUserAgent="";
          if(aHeadObj.getEmailVolUID()!=0){// if the user is logged in and not anonymous, just make sure we don't double-count the referrals.
              allocatedEmailBRLO( httpServletRequest );
        	  iRetCode = m_EmailBRLOObj.loadEmail( aHeadObj ); // if this equals 1, then it's already been recorded as a referral match in our sys for this user
          }else{
        	  aszUserAgent=httpServletRequest.getHeader("user-agent");
        	  if(	aszUserAgent.contains("chrome")	||
          			aszUserAgent.contains("firefox")	||
        			aszUserAgent.contains("Mozilla")	||
        			aszUserAgent.contains("Firefox")	||
        			aszUserAgent.contains("Chrome")	||
        			aszUserAgent.contains("Chrome")	||
        			aszUserAgent.contains("Safari")	
        	  ){
        		  iRetCode=0;
        	  }
          }
          if(iRetCode != 1){ // this user has ***NOT*** already applied to this position  |OR| is anonymous - what about not wanting to track robots, though?
        	  //add email to db
    			iRetCode = m_IndBRLOObj.logApplication( aHeadObj ); 
        	  iRetCode = m_IndBRLOObj.createEmail( aHeadObj, -1 );
          }
          
        httpServletRequest.setAttribute("redirectpage","feeds: " + aszIWantToHelpURL);
          
          return actionMapping.findForward( "mappingpage" );
      }
      // end-of method processIWantToHelpExternal()
      
      //=== END iwanttohelp email section
      //=== END iwanttohelp email section
      //=== END iwanttohelp email section

      
      //=== BEGIN portal , etc section
      //=== BEGIN portal , etc section
      //=== BEGIN portal , etc section
 	
  	/*
  	* show showPortalBannerPostContainer page
  	*/
  	public ActionForward showPortalBannerPostContainer(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
  	{
      	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
     	getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}
        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
  	    return actionMapping.findForward( "portalbannerpost" );
  	}
  	// end-of method showPortalBannerPostContainer()
  	
  	/*
  	* show showPortalBannerPost page
  	*/
  	public ActionForward showPortalBannerPost(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
  	{
      	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
     	String portal="";
         if(httpServletRequest.getParameter("portal") != null ){
         	portal=httpServletRequest.getParameter("portal");
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
  	          	m_BaseHelp.setFormData(oFrm,"portal", portal );
         }
  	    return actionMapping.findForward( "fileuploadpage" );
  	}
  	// end-of method showPortalBannerPost()
  	
      //=== BEGIN portal favorites, etc section
      //=== BEGIN portal favorites, etc section
      //=== BEGIN portal favorites, etc section

      /*
       * portalFavoriteOppsList
       */
      public ActionForward portalFavoriteOppsList(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0,iRetCode2=0;
       	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
       	getLoggedInStatus(httpServletRequest, httpServletResponse);
    		if(aszLoggedInStatus.equals("showlogin")){
    	    	return actionMapping.findForward( "showlogin" );
    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		}
    	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    	  }
    	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
    		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    			  return actionMapping.findForward( "showlogin" );
    		  }else{
    			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		  }
    	  }
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
         OrganizationInfoDTO aPortalOrgInfoObj = new OrganizationInfoDTO();
         aPortalOrgInfoObj.setORGNID( aszPortalNID );
         aPortalOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
         allocatedOrgBRLO( httpServletRequest );
      	
 	  	  int iNatlAssocNID = 0;
 	  	  if(bNatlAssoc==true){
 	  		  //m_BaseHelp.convertToInt( aszPortalNID )
 	  		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
 	  	  }
 	  	  // if natl association, then the OrgNid should be that of the correct national association
 	  	  boolean b_AssocManager = false;
 	  	  allocatedOrgBRLO( httpServletRequest );
 	  	  if(bNatlAssoc==true){
 	  		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);
 	  		  // if natl association, then the OrgNid should be that of the correct national association
 	  		  aPortalOrgInfoObj.setORGNID( iNatlAssocNID );
 	  		  int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
 	  		  for(int i=0; i<a_iNatlAssocNIDs.length; i++){
 	  			  if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
 	  		  }
 	  	  }
         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
         if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
         ){
        	 aPortalOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
         }else if(b_AssocManager==true){
        	 aPortalOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
          int iTid = aPortalOrgInfoObj.getORGAffiliation1TID();
          if(iTid > 0){
        	  aPortalOrgInfoObj.setORGNID( aszPortalNID );
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
          }
     }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }
         if(iRetCode == -111){
         	if(bAdminRole==true){
 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	          	return actionMapping.findForward( "noaccess" );
         	}else{
         		// org did not exist
             	return actionMapping.findForward( "404" );
         	}
         }
         
         
        if(0 != iRetCode){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
    	}
        
        String aszOppNIDs = "";
        // user owns the org; they can favorite orgs and opps
        int iFavoriteUID = aPortalOrgInfoObj.getORGUID();
        
        iRetCode = m_OrgActHelp.getOrgFavoritesFromForm(oFrm, aPortalOrgInfoObj);
        
		if(b_AssocManager==true){
			// clear the orgNIDS so that those don't change at all
			aPortalOrgInfoObj.setORGFavoritedORGNIDsArray(new int[0]);
			aPortalOrgInfoObj.setORGPrevFavoritedORGNIDsArray(new int[0]);
			
			aPortalOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() );
			aPortalOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
			// will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
			int iOrgAffilTID=aPortalOrgInfoObj.getORGAffiliation1TID();
	    	iRetCode = m_OrgBRLOObj.setNatlAssocOrgsAndOpps( aPortalOrgInfoObj, iOrgAffilTID );
		}else{
			iRetCode = m_OrgBRLOObj.favoriteOpps( aPortalOrgInfoObj );
		}
        
        
        // if this was set through the "next step", then it should map the user to the create opportunity page, not re-load the opps page
        if(aPortalOrgInfoObj.getRequestType().length() > 1){
 			httpServletRequest.setAttribute("redirectpage", "nextstepportalcreation");
		    httpServletRequest.setAttribute("redirectportal", aPortalOrgInfoObj.getRequestType());
			return actionMapping.findForward( "mappingpage" );
        }
        
        // go through the list of node id's of opps and mark them as favorited - will be a method in brlo layer

        //return actionMapping.findForward( "orgmanagement" );// don't know what page to forward to....? probably same list that gets pre-checked for the ones they've already checked off
        return m_BaseSrchAction.showPortalOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     }
     // end-of method addNewContact()

      /*
       * showPortalOpps
       */
      public ActionForward showPortalOpps(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
       	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
		  return m_BaseSrchAction.showPortalOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
		// end-of method showPortalOpps()

      /*
       * portalFavoriteOrgsList
       */
      public ActionForward portalFavoriteOrgsList(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0, iOrgAffilTID=0;
       	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
    	  getLoggedInStatus(httpServletRequest, httpServletResponse);
    	  if(aszLoggedInStatus.equals("showlogin")){
    		  return actionMapping.findForward( "showlogin" );
    	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
    		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	  }
    	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    	  }
    	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
    		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    			  return actionMapping.findForward( "showlogin" );
    		  }else{
    			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		  }
    	  }
        boolean bAdminRole=false;
        String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        if(! (aszRole==null || aszRole.equals(null))){
       	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
         	bAdminRole=true;
       	 }else{
       		 aszRole="";
       	 }
        }
         OrganizationInfoDTO aPortalOrgInfoObj = new OrganizationInfoDTO();
         aPortalOrgInfoObj.setORGNID( aszPortalNID );
         aPortalOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
         allocatedOrgBRLO( httpServletRequest );
         allocatedIndBRLO( httpServletRequest );
     	
	  	  int iNatlAssocNID = 0;
	  	  if(bNatlAssoc==true){
	  		  //m_BaseHelp.convertToInt( aszPortalNID )
	  		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);//aCurrentUserObj.getNatlAssociationNID();
	  	  }
	  	  // if natl association, then the OrgNid should be that of the correct national association
	  	  boolean b_AssocManager = false;
	  	  allocatedOrgBRLO( httpServletRequest );
	  	  if(bNatlAssoc==true){
	  		  iNatlAssocNID = m_BaseHelp.convertToInt(aszPortalNID);
	  		  // if natl association, then the OrgNid should be that of the correct national association
	  		  aPortalOrgInfoObj.setORGNID( iNatlAssocNID );
	  		  int[] a_iNatlAssocNIDs = aCurrentUserObj.getUSPNatlAssociationNIDsArray(); 
	  		  for(int i=0; i<a_iNatlAssocNIDs.length; i++){
	  			  if(a_iNatlAssocNIDs[i] == iNatlAssocNID) b_AssocManager=true;
	  		  }
	  	  }
         
         // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
         if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
       		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
         ){
        	 aPortalOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
         }else if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
        	 aPortalOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	          iOrgAffilTID = aPortalOrgInfoObj.getORGAffiliation1TID();
	          if(iOrgAffilTID > 0){
	        	  aPortalOrgInfoObj.setORGNID( aszPortalNID );
//	              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL );
	          }
	     }else{
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         }
         if(iRetCode == -111){
         	if(bAdminRole==true){
 	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
 	          	return actionMapping.findForward( "noaccess" );
         	}else{
         		// org did not exist
             	return actionMapping.findForward( "404" );
         	}
         }
         
         
        if(0 != iRetCode){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
    	}
        
        String aszOppNIDs = "";
        // user owns the org; they can favorite orgs and opps
        int iFavoriteUID = aPortalOrgInfoObj.getORGUID();
        
        iRetCode = m_OrgActHelp.getOrgFavoritesFromForm(oFrm, aPortalOrgInfoObj);

		if(b_AssocManager==true){
//				aPortalOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() );
//				aPortalOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
				// will in reality be loading a NationalAssociation rather than an Organization - for mananging portal
//				iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aPortalOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC );
//				int iOrgAffilTID=aPortalOrgInfoObj.getORGAffiliation1TID(); - the load of this natlassoc was already done earlier in this method
 	    	    iRetCode = m_OrgBRLOObj.setNatlAssocOrgsAndOpps( aPortalOrgInfoObj, iOrgAffilTID );
        }else{
            iRetCode = m_OrgBRLOObj.favoriteOrgs( aPortalOrgInfoObj );
            
            // prompted user to ask if this, as well... if so, then proceed through all of these child opps
            if(aPortalOrgInfoObj.getFavoriteChildOpps() == true){
            	// do select of all published child opps of given orgs (nids in array), and load these oppnids in the opp array
            	iRetCode = m_OrgBRLOObj.loadChildOpps( aPortalOrgInfoObj );
            	
            	aPortalOrgInfoObj.setORGFavoritedOPPNIDsArray(aPortalOrgInfoObj.getORGChildOPPNIDsArray());
            
            	iRetCode = m_OrgBRLOObj.favoriteOpps( aPortalOrgInfoObj );
            }

        }

        
        // go through the list of node id's of opps and mark them as favorited - will be a method in brlo layer

        //return actionMapping.findForward( "orgmanagement" );// don't know what page to forward to....? probably same list that gets pre-checked for the ones they've already checked off
        
        return m_BaseSrchAction.showPortalOpps(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
     // end-of method portalFavoriteOrgsList()
      //=== END portal favorites, etc section
      //=== END portal favorites, etc section
      //=== END portal favorites, etc section

      
      
      //=== BEGIN OppContacts section
      //=== BEGIN OppContacts section
      //=== BEGIN OppContacts section


      /*
       * show Opportunity contacts list page
       */
       public ActionForward showOppContacts(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	   DynaActionForm oFrm = (DynaActionForm)actionForm;
    	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	   String aszOrgNid = null;
    	   if(httpServletRequest.getParameter("orgnid") != null )
    		   aszOrgNid = httpServletRequest.getParameter("orgnid");
    	   else
    		   if(null != aSessDat)
    			   aszOrgNid = "" + aSessDat.getOrgNID();

    	   String aszOppNid = null;
    	   int iOppNid=0;
    	   if(httpServletRequest.getParameter("oppnid") != null )
    		   aszOppNid = httpServletRequest.getParameter("oppnid");
    	   else
    		   if(null != aSessDat)
    			   aszOppNid = "" + aSessDat.getOppNID();
    	   try{
    		   iOppNid=Integer.parseInt(aszOppNid);
    	   }catch (Exception e){}
    	   
     	  getLoggedInStatus(httpServletRequest, httpServletResponse);
     	  if(aszLoggedInStatus.equals("showlogin")){
     		  return actionMapping.findForward( "showlogin" );
     	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
     		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	  }
    	   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    		   if(null != aSessDat){
    			   aSessDat.setOrgNID( aszOrgNid );
    			   aSessDat.setOppNID( aszOppNid );
    			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWOPPCONTACTS );
    		   }
   			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    	   }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
        		   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	   }
           }
           
           boolean bAdminRole=false;
           String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           if(! (aszRole==null || aszRole.equals(null))){
        	   if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        		   bAdminRole=true;
        	   }else{
        		   aszRole="";
        	   }
           }
           boolean bByContact = false;
           String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
           if(aszReqType.contains("ByContact")){
          	 bByContact = true;
           }
           OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
           aOpportObj.setORGNID( aszOrgNid );
           aOpportObj.setOPPNID( aszOppNid );
           aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
           allocatedOrgBRLO( httpServletRequest );
           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           aOrgInfoObj.setORGNID( aszOrgNid );
           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

           int iNatlAssocNID = 0;
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
           
          if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
           ){
        	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
    	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
           }else{
	           if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	            	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//         	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	             int iTid = aOrgInfoObj.getORGAffiliation1TID();
	             if(iTid > 0){
	            	 aOrgInfoObj.setORGNID( aszOrgNid );
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	             }
       	     }else{
       	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
       	     }
        	   if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
       			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
       	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
       	     }else{
       	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
       		}
           }
           if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
        		if(bByContact == true){
        			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
        			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
        			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
        		}
        		else              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
            	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
               }else{
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
               }
               if(iRetCode == -111){
            	   if(bAdminRole==true){
            		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
            		   return actionMapping.findForward( "noaccess" );
            	   }else{
            		   return actionMapping.findForward( "404" );
            	   }
               }
           }
           httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           
           // contact/administrator list for organization
           ArrayList aList = new ArrayList();
           iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
           httpServletRequest.setAttribute( "userlist", aList );
           httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
           
           httpServletRequest.setAttribute("opp", aOpportObj);
           iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
           iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
           if(null != aSessDat){
        	   aSessDat.setTokenKey(null);
        	   aSessDat.setOrgNID(null);
        	   aSessDat.setOppNID(null);
        	   aSessDat.setSubdomain(null);
        	   aSessDat.setSiteEmail(null);
           }
           loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
           return actionMapping.findForward( "oppcontactmanage" );
       }
       // end-of method showOppContacts contacts
      
       
       /*
       * show add contact for organization view page (managing org)
       */
       public ActionForward showAddOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	   
    	   DynaActionForm oFrm = (DynaActionForm)actionForm;
    	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	   String aszErr = m_BaseHelp.getFormData(oFrm,"errormsg");
           String aszOrgNid = null;
           if(httpServletRequest.getParameter("orgnid") != null ){
               aszOrgNid = httpServletRequest.getParameter("orgnid");
           }else{
        	   if(null != aSessDat){
        		   aszOrgNid = "" + aSessDat.getOrgNID();
        	   }
           }
    	   String aszOppNid = null;
    	   int iOppNid=0;
    	   if(httpServletRequest.getParameter("oppnid") != null ){
    		   aszOppNid = httpServletRequest.getParameter("oppnid");
    	   }else{
    		   if(null != aSessDat){
    			   aszOppNid = "" + aSessDat.getOppNID();
    		   }
    	   }
    	   try{
    		   iOppNid=Integer.parseInt(aszOppNid);
    	   }catch (Exception e){}
      	  getLoggedInStatus(httpServletRequest, httpServletResponse);
      	  if(aszLoggedInStatus.equals("showlogin")){
      		  return actionMapping.findForward( "showlogin" );
      	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
      		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	  }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
   			  if(null != aSessDat){
   				  aSessDat.setOrgNID( aszOrgNid );
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
   			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
           }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	   }
           }
           
           boolean bAdminRole=false;
           String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           if(! (aszRole==null || aszRole.equals(null))){
          	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            	bAdminRole=true;
          	 }else{
          		 aszRole="";
          	 }
           }
           boolean bByContact = false;
           String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
           if(aszReqType.contains("ByContact")){
          	 bByContact = true;
           }

           OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
           aOpportObj.setORGNID( aszOrgNid );
           aOpportObj.setOPPNID( aszOppNid );
           aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
           allocatedOrgBRLO( httpServletRequest );
           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           aOrgInfoObj.setORGNID( aszOrgNid );
           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

           int iNatlAssocNID = 0;
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
           

           if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
           ){
        	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
    	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
           }else{
		           if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
		            	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
		//         	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
		             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
		             int iTid = aOrgInfoObj.getORGAffiliation1TID();
		             if(iTid > 0){
		            	 aOrgInfoObj.setORGNID( aszOrgNid );
		                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
		             }
         	     }else{
         	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
         	     }
               if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
       			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
       	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
       	     }else{
       	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
       		}
           }
           if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
        		if(bByContact == true){
        			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
        			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, aszSiteVersion );
        			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2, "", OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
        		}
        		else              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
            	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
               }else{
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
               }
               if(iRetCode == -111){
            	   if(bAdminRole==true){
            		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
            		   return actionMapping.findForward( "noaccess" );
            	   }else{
            		   return actionMapping.findForward( "404" );
            	   }
               }
           }
           httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           
           // contact/administrator list for organization
           allocatedOrgBRLO( httpServletRequest );
           ArrayList aList = new ArrayList();
           iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
           httpServletRequest.setAttribute( "userlist", aList );
           httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
           
           httpServletRequest.setAttribute("opp", aOpportObj);
           aOrgInfoObj.appendErrorMsg(aszErr);

           loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
           return actionMapping.findForward( "oppcontactadd" );
       }
       // end-of method showAddOppContact()
       
       
       /*
        * add new contact for opportunity
        */
        public ActionForward addNewOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	int iRetCode=0,iRetCode2=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			//httpServletRequest.setAttribute("redirectpage","noportalexists");
        			//return actionMapping.findForward("mappingpage");
        			return actionMapping.findForward("404");
            	}
            }
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
        	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        	if(iOrgNid < 1){
        		aszOrgNid = "" + aSessDat.getOrgNID();
            	if(iOrgNid < 1){
	   	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	   	             	return actionMapping.findForward( "showlogin" );
	   	         	}else{
	   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	   	         	}
            	}
        	}
     	   String aszOppNid = null;
    	   int iOppNid=0;
    	   if(httpServletRequest.getParameter("oppnid") != null ){
    		   aszOppNid = httpServletRequest.getParameter("oppnid");
    	   }else{
    		   if(null != aSessDat){
    			   aszOppNid = "" + aSessDat.getOppNID();
    		   }
    	   }
    	   try{
    		   iOppNid=Integer.parseInt(aszOppNid);
    	   }catch (Exception e){}
           if(null == aCurrentUserObj) {
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	   }
           }
        	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        		if(null != aSessDat){
        			aSessDat.setOrgNID( aszOrgNid );
        			aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
        		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        	}
        	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        			return actionMapping.findForward( "showlogin" );
        		}else{
        			return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            		//return actionMapping.findForward( "loginstatus" );
        		}
        	}
        	String aszIsVolunteerContact = m_BaseHelp.getFormData(oFrm,"isemailcontact");
        	int iIsVolunteerContact = m_BaseHelp.convertToInt( aszIsVolunteerContact );

        	boolean bAdminRole=false;
        	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        	if(! (aszRole==null || aszRole.equals(null))){
        		if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        			bAdminRole=true;
        		}else{
        			aszRole="";
        		}
        	}
            boolean bByContact = false;
            String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
            if(aszReqType.contains("ByContact")){
           	 bByContact = true;
            }
            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
            aOpportObj.setORGNID( aszOrgNid );
            aOpportObj.setOPPNID( aszOppNid );
            aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
            allocatedOrgBRLO( httpServletRequest );

            int iNatlAssocNID = 0;
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
            

            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
         		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
         	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
    	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
            }else{
	            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	              int iTid = aOrgInfoObj.getORGAffiliation1TID();
	              if(iTid > 0){
	             	 aOrgInfoObj.setORGNID( iOrgNid );
	                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
                if(aCurrentUserObj.getNatlAssociationNID()>0){
        			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
        	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
        	     }else{
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
        		}
            }
            if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
            	if(bByContact == true){
            		// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
            		iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            	}
            	else               if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
             		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                ){
             	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                }else{
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                }
                if(iRetCode == -111){
             	   if(bAdminRole==true){
             		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
             		   return actionMapping.findForward( "noaccess" );
             	   }else{
             		   return actionMapping.findForward( "404" );
             	   }
                }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
    	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
    	          	return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
                	return actionMapping.findForward( "404" );
            	}
            }
           if(0 != iRetCode){
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                   //return actionMapping.findForward( "loginstatus" );
        	   }
           }
           
           // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
           // load data for organization contact person
           PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
           String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email");
           aContactPersonObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email") );
           //aContactPersonObj.setUSPEmail2Addr( aCurrentUserObj.getUSPEmail1Addr() ); // set the secondary email address as the current user (used in email)
           allocatedIndBRLO( httpServletRequest );
           iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_EMAIL, aszSiteVersion ); //(in reality, this is by email address)
           aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));

     	  String aszSubdomain = "";
     	  if(httpServletRequest.getParameter("subdomain") != null )
    		  aszSubdomain = httpServletRequest.getParameter("subdomain");
     	  else
     		  aszSubdomain = aOpportObj.getOPPSubdom();
     	  if(aszSubdomain.length()<1)
     		  aszSubdomain = aOpportObj.getOPPSubdom();
           if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
           	// the user was correctly loaded and exists
               iRetCode = m_OrgBRLOObj.insertAdditionalOppContact( aOpportObj , aContactPersonObj, iIsVolunteerContact ); // really only needs a uid to get inserted.
           }else{
	           	// the email address could not be found in our database
	           	String aszErr="The email address " + aszEmailAddress + " could not be found in our database.";
//	           	aOpportObj.setErrorMsg(aszErr);
              	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
            	httpServletRequest.setAttribute("org", aOrgInfoObj);
            	httpServletRequest.setAttribute("opp", aOpportObj);
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "oppcontactadd" );
//            	return showAddOppContact(actionMapping, actionForm, httpServletRequest, httpServletResponse);         	
           }

           int iEmailUseCase = 0;
           if(iIsVolunteerContact==1){
        	   iEmailUseCase=iNowEmailAndContact;
           }else{
        	   iEmailUseCase=iNowIsOppContact;
           }

           aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
           aContactPersonObj.setUSPSubdom(aszSubdomain);//(aContactPersonObj.getUSPSubdom());
           iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, iEmailUseCase, aszPortal ); //(in reality, this is by email address)

           session.setAttribute("orgmanagementnid", aszOrgNid);

       	httpServletRequest.setAttribute("org", aOrgInfoObj);
        m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
    	httpServletRequest.setAttribute("opp", aOpportObj);
        m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
           
           // contact/administrator list for organization
           allocatedOrgBRLO( httpServletRequest );
           ArrayList aList = new ArrayList();
           iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
           httpServletRequest.setAttribute( "userlist", aList );
        	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);

        	if(bAdminRole==true){
        		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
        	}

            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
           return actionMapping.findForward( "oppcontactmanage" );
        }
        // end-of method addNewOppContact()
        

     	/*
          * add brand new contact for opportunity
          */
          public ActionForward addBrandNewOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
        	  int iRetCode=0,iRetCode2=0;
           	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
        	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	  DynaActionForm oFrm = (DynaActionForm)actionForm;
        	  String aszOrgNid = null;
         	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
        	  aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        	  int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        	  if(iOrgNid < 1){
        		  aszOrgNid = "" + aSessDat.getOrgNID();
        		  if(iOrgNid < 1){
        			  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        				  return actionMapping.findForward( "showlogin" );
        			  }else{
        				  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        			  }
        		  }
        	  }
        	   String aszOppNid = null;
        	   int iOppNid=0;
        	   if(httpServletRequest.getParameter("oppnid") != null ){
        		   aszOppNid = httpServletRequest.getParameter("oppnid");
        	   }else{
        		   if(null != aSessDat){
        			   aszOppNid = "" + aSessDat.getOppNID();
        		   }
        	   }
        	   try{
        		   iOppNid=Integer.parseInt(aszOppNid);
        	   }catch (Exception e){}
        	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			  if(null != aSessDat){
    				  aSessDat.setOrgNID( aszOrgNid );
    				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
    			  }
    				if(aCurrentUserObj.getUserProfileNID() < 1){ 
    					// this user is an old drupal user-only; need to take through partial account creation process
    					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    					//		as well as an insert into the rails db
    					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    				}
    		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
    	         	return actionMapping.findForward("mappingpage");
        	  }
        	  if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        		  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        			  return actionMapping.findForward( "showlogin" );
        		  }else{
        			  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        		  }
        	  }
          	String aszIsVolunteerContact = m_BaseHelp.getFormData(oFrm,"isemailcontact");
        	int iIsVolunteerContact = m_BaseHelp.convertToInt( aszIsVolunteerContact );
        	  
        	  boolean bAdminRole=false;
        	  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        	  if(! (aszRole==null || aszRole.equals(null))){
        		  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        			  bAdminRole=true;
        		  }else{
        			  aszRole="";
        		  }
        	  }
              boolean bByContact = false;
              String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
              if(aszReqType.contains("ByContact")){
             	 bByContact = true;
              }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
              aOpportObj.setORGNID( aszOrgNid );
              aOpportObj.setOPPNID( aszOppNid );
              aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
              allocatedOrgBRLO( httpServletRequest );


              int iNatlAssocNID = 0;
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
              
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
           		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
           	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
              }else{
                  //iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
	              if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//            	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                if(iTid > 0){
	               	 aOrgInfoObj.setORGNID( iOrgNid );
	                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                }
          	     }else{
          	    	if(aCurrentUserObj.getNatlAssociationNID()>0){
          				aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
          		         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
          		     }else{
          		        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
          			}
          	     }
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
              }
              if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
            		if(bByContact == true){
            			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
            			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            		}
            		else                 if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
               		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                  ){
               	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                  }else{
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                  }
                  if(iRetCode == -111){
               	   if(bAdminRole==true){
               		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
               		   return actionMapping.findForward( "noaccess" );
               	   }else{
               		   return actionMapping.findForward( "404" );
               	   }
                  }
              }
              if(iRetCode == -111){
            	  if(bAdminRole==true){
            		  m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
            		  return actionMapping.findForward( "noaccess" );
            	  }else{
            		  // org did not exist
            		  return actionMapping.findForward( "404" );
            	  }
              }
              if(0 != iRetCode){
            	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            		  return actionMapping.findForward( "showlogin" );
            	  }else{
            		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	  }
              }
              
              // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
              // load data for organization contact person
              PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
              // get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
              iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
              
              String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
              allocatedIndBRLO( httpServletRequest );
              
              iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
             // for the contact person, write the "init" field for the new user as the current user's email address
              aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
          	String mailkey = "newOppUserAccnt";

            iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, aszPortal, mailkey, aszSiteVersion);
            
      	  String aszErr="";
            if(iRetCode==-1){
            	// user may already exist.
      		  aszErr="The email address already exists.  Please try adding the user again as a contact through the \"Existing User\" section.\n\r";
        	  m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
//           	aOpportObj.setErrorMsg(aszErr);
      	  httpServletRequest.setAttribute("org", aOrgInfoObj);
        	httpServletRequest.setAttribute("opp", aOpportObj);
          loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            }
            // need to write the additional fields to the user, too....
            
            if(aContactPersonObj.getUserUID()>0){//iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
            	  // the user was correctly loaded and exists
  //                iRetCode = m_OrgBRLOObj.insertAdditionalOppContact( aOpportObj , aContactPersonObj, iIsVolunteerContact ); // really only needs a uid to get inserted.
              }else{
            	  if(iRetCode == -1){
            		  aszErr="The email address already exists.  Please try adding the user again as a contact through the \"Existing User\" section.\n\r";
            	  }else if(iRetCode == -111){
            		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user.")){
            			  // there was an error with the XML-RPC call - most likely a duplicate Username
            			  aszErr="There was an error on saving this user. \"" +
            			  	"\"Make sure the email address or username does not already exist in our system.";
            		  }else{
            			  // there was an error with the XML-RPC call
            			  aszErr="There was an error on saving this user: " +
    		             			aContactPersonObj.getErrorMsg() ;
            		  }
            	  }else if(iRetCode == 333){
            		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user ")){
            			  // there was an error with the XML-RPC call - most likely a duplicate Username
            			  aszErr="There was an error on saving this user. " +
    		             		"Make sure the email address or username does not already exist in our system.";
            		  }else{
            			  // there was an error with the XML-RPC call
            			  aszErr="There was an error on saving this user: \"" +
    		             			aContactPersonObj.getErrorMsg() +
    		             			"\" (Make sure the email address or username does not already exist in our system.)";
            		  }
            	  }else if(iRetCode == 444 || iRetCode == 555){
            		  // there was an error with the XML-RPC call
            		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as a contact through the \"Existing User\" section\n\r" +
                  			"(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
            	  }else{
            		  if(aContactPersonObj.getErrorMsg().length()>5){
            			  aszErr="(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
            		  }
            	  }
            	  m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
//  	           	aOpportObj.setErrorMsg(aszErr);
            	  httpServletRequest.setAttribute("org", aOrgInfoObj);
              	httpServletRequest.setAttribute("opp", aOpportObj);
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "oppcontactadd" );
//				return showAddOppContact(actionMapping, actionForm, httpServletRequest, httpServletResponse);         	
              }
              
            if(aContactPersonObj.getUserUID()>0){//iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
          	  // the user was correctly loaded and exists
                iRetCode = m_OrgBRLOObj.insertAdditionalOppContact( aOpportObj , aContactPersonObj, iIsVolunteerContact ); // really only needs a uid to get inserted.
            }
                session.setAttribute("orgmanagementnid", aszOrgNid);
              httpServletRequest.setAttribute("org", aOrgInfoObj);
              m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
          	httpServletRequest.setAttribute("opp", aOpportObj);
            m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
              
              // contact/administrator list for organization
              allocatedOrgBRLO( httpServletRequest );
              ArrayList aList = new ArrayList();
              iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
              httpServletRequest.setAttribute( "userlist", aList );
              httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
              
              if(bAdminRole==true){
            	  m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
              }
              iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
              iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "oppcontactmanage" );
          }
          // end-of method addBrandNewOppContact()
          

          /*
           * show Opportunity contacts list page that includes list of all Org contacts, as well
           */
           public ActionForward showOppOrgContacts(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
        	   int iRetCode=0;
             	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
        	   DynaActionForm oFrm = (DynaActionForm)actionForm;
        	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	   String aszOrgNid = null;
        	   if(httpServletRequest.getParameter("orgnid") != null )
        		   aszOrgNid = httpServletRequest.getParameter("orgnid");
        	   else
        		   if(null != aSessDat)
        			   aszOrgNid = "" + aSessDat.getOrgNID();

        	   String aszOppNid = null;
        	   int iOppNid=0;
        	   if(httpServletRequest.getParameter("oppnid") != null )
        		   aszOppNid = httpServletRequest.getParameter("oppnid");
        	   else
        		   if(null != aSessDat)
        			   aszOppNid = "" + aSessDat.getOppNID();
        	   try{
        		   iOppNid=Integer.parseInt(aszOppNid);
        	   }catch (Exception e){}
        	   
          	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
        	   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        		   if(null != aSessDat){
        			   aSessDat.setOrgNID( aszOrgNid );
        			   aSessDat.setOppNID( aszOppNid );
        			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWOPPCONTACTS );
        		   }
       			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        	   }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            		   return actionMapping.findForward( "showlogin" );
            	   }else{
            		   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	   }
               }
               
               boolean bAdminRole=false;
               String aszRole = m_BaseHelp.getFormData(oFrm,"role");
               if(! (aszRole==null || aszRole.equals(null))){
            	   if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            		   bAdminRole=true;
            	   }else{
            		   aszRole="";
            	   }
               }
               boolean bByContact = false;
               String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
               if(aszReqType.contains("ByContact")){
              	 bByContact = true;
               }
               OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
               aOpportObj.setORGNID( aszOrgNid );
               aOpportObj.setOPPNID( aszOppNid );
               aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
               allocatedOrgBRLO( httpServletRequest );
               OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
               aOrgInfoObj.setORGNID( aszOrgNid );
               aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

               int iNatlAssocNID = 0;
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
               
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
            	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
               }else{
	               if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                 if(iTid > 0){
	                	 aOrgInfoObj.setORGNID( aszOrgNid );
	                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                 }
           	     }else{
           	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           	     }
                   if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
           			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
           	     }else{
           	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
           		}
               }
               if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
            		if(bByContact == true){
            			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
            			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            		}
            		else              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                   ){
                	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                   }else{
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                   }
                   if(iRetCode == -111){
                	   if(bAdminRole==true){
                		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
                		   return actionMapping.findForward( "noaccess" );
                	   }else{
                		   return actionMapping.findForward( "404" );
                	   }
                   }
               }
               httpServletRequest.setAttribute("org", aOrgInfoObj);
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               
               // contact/administrator list for organization
               ArrayList aList = new ArrayList();
               iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOpportObj );
               httpServletRequest.setAttribute( "userlist", aList );
               httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
               
               httpServletRequest.setAttribute("opp", aOpportObj);
               iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
               iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
               if(null != aSessDat){
            	   aSessDat.setTokenKey(null);
            	   aSessDat.setOrgNID(null);
            	   aSessDat.setOppNID(null);
            	   aSessDat.setSubdomain(null);
            	   aSessDat.setSiteEmail(null);
               }
               loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "opporgcontactmanage" );
           }
           // end-of method showOppOrgContacts contacts
          
          
      	/*
          * processEditOppContacts
          */
          public ActionForward processEditOppContacts(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
          	int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
            boolean bByContact = false;
            String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
            if(aszReqType.contains("ByContact")){
            	bByContact = true;
            }
            
            String aszOppNid = m_BaseHelp.getFormData(oFrm,"oppnid");
            int iOppNid = m_BaseHelp.convertToInt( aszOppNid );

            String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            if(iOrgNid < 1){
         		aszOrgNid = "" + aSessDat.getOrgNID();
             	if(iOrgNid < 1 ){//&& bByContact == false){
    	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	             	return actionMapping.findForward( "showlogin" );
    	         	}else{
    	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	         	}
             	}
            }
    		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			  if(null != aSessDat){
    				  aSessDat.setOrgNID( aszOrgNid );
    				  aSessDat.setOppNID( aszOppNid );
    				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
    			  }
    				if(aCurrentUserObj.getUserProfileNID() < 1){ 
    					// this user is an old drupal user-only; need to take through partial account creation process
    					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    					//		as well as an insert into the rails db
    					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    				}
    		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
    	         	return actionMapping.findForward("mappingpage");
    		 }
            if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                	return actionMapping.findForward( "showlogin" );
            	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
            }
            boolean bAdminRole=false;
            String aszRole = m_BaseHelp.getFormData(oFrm,"role");
            if(! (aszRole==null || aszRole.equals(null))){
           	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
             	bAdminRole=true;
           	 }else{
           		 aszRole="";
           	 }
            }
            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE editing access of the said opportunity/org association

            int iNatlAssocNID = 0;
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
            
            allocatedOrgBRLO( httpServletRequest );
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
          		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
          	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else{
	            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	              int iTid = aOrgInfoObj.getORGAffiliation1TID();
	              if(iTid > 0){
	             	 aOrgInfoObj.setORGNID( iOrgNid );
	                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
    	         if(iRetCode==-111){
    	         	if(bByContact == true){
    	               	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
    	                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
    	         	}
    	         }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
    	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
    	          	return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
                	return actionMapping.findForward( "404" );
            	}
            }
            if(0 != iRetCode){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                	return actionMapping.findForward( "showlogin" );
            	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
        	}

            OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
            m_OrgActHelp.getOrgOppFromForm1( oFrm, aOpportObj );
            aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE editing access of the said opportunity/org association
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
           	 aOpportObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
            }
    		if(aOpportObj.getSiteAdministratorUID() > 1){
    	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
    		}else{
    			if(aCurrentUserObj.getNatlAssociationNID()>0){
    				aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
    		         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
    		     }else{
    		        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
    			}
    		}
            if(iRetCode==-111){
           	 if(bByContact == true){
               	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
           		 iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
                }
            }
            if(0 != iRetCode){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                	return actionMapping.findForward( "showlogin" );
            	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
        	}
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            httpServletRequest.setAttribute( "opp", aOpportObj );
            
            boolean bPrimaryContactChanged=false, bContactsChanged=false, bEmailContactsChanged=false;
            // check to see if any of the Contact data on the given opportunity has changed...
            int iInitUID = aOpportObj.getOPPPrimaryPersonUID();
            int iModifiedUID = aOpportObj.getOPPPrimaryPersonUIDModified();
            if(iModifiedUID != iInitUID){
            	// primary person has changed.  this will, in addition to being updated in the DB, trigger an email notice about change of Primary Contact for said opp
            	bPrimaryContactChanged=true;
            }
            if(! aOpportObj.getOPPContactUIDsModifiedArray().equals(aOpportObj.getOPPContactUIDsArray())){
            	bContactsChanged=true;
            }
            if(! aOpportObj.getOPPContactUIDsRcvEmailModifiedArray().equals(aOpportObj.getOPPContactUIDsRcvEmailArray())){
            	bEmailContactsChanged=true;
            }

            ArrayList aListIdsContactsChanged = null;
            ArrayList aListIdsEmailContactsChanged = null;
            ArrayList aListIdsAndEmailNotifyFlag = null;
       	  String aszSubdomain = "";
     	  if(httpServletRequest.getParameter("subdomain") != null )
    		  aszSubdomain = httpServletRequest.getParameter("subdomain");
     	  else
     		  aszSubdomain = aOpportObj.getOPPSubdom();
     	  if(aszSubdomain.length()<1)
     		  aszSubdomain = aOpportObj.getOPPSubdom();
            if(bContactsChanged==true || bEmailContactsChanged==true ||bPrimaryContactChanged==true ){ 
            	// need to update contacts for opportunity
//     admins don't necessarily have to be written to the usermail table; not unless they are "email" contacts
                // get arrays of uids that show the differences - should prob be done in BRL, but will need to trigger the emailNotify for each user changed
            	aListIdsContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsArray(),aOpportObj.getOPPContactUIDsModifiedArray());
            	aListIdsEmailContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOpportObj.getOPPContactUIDsRcvEmailArray(),aOpportObj.getOPPContactUIDsRcvEmailModifiedArray());

            	aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged,iInitUID,iModifiedUID);
            	//aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListIdsEmailContactsChanged);
            	
            	iRetCode=m_OrgBRLOObj.updateOPPContacts(aListIdsAndEmailNotifyFlag, aOpportObj);
            	
                for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
                    int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
                    int iUID=iIdAndFlag[0];
                    int iFlag=iIdAndFlag[1];
                    
                    // load the contact info for that user
                    PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
                    aContactPersonObj.setUserUID(iUID);//( aCurrentUserObj.getUserUID() );
                    iRetCode = m_IndBRLOObj.loadUserContactData( aContactPersonObj );        
                    aContactPersonObj.setUSPSubdom(aszSubdomain);
                    // then trigger the emailNotify Message(s) for that flag
                    if(iRetCode==0){
                    	// run db update on the org's usermail records

                    	// if an email should be triggered, trigger it (for now, skip primary contact changes for this part)
                    	if(iFlag!=iNewPrimaryContact && iFlag !=iNoLongerPrimaryContact){
                        	aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
                            // ******************!!!!!!!!!!!!!!! add a method to email that they are now A. Receiving, emails now ************
                            //iRetCode = m_IndBRLOObj.notifyNewAdmin( httpServletRequest, aContactPersonObj, aOrgInfoObj, iIsVolunteerContact );
                            int iEmailUseCase = iFlag;
                            iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, iEmailUseCase, aszPortal );
                            if(iRetCode==-100){
                           	 // The user did not receive any email notification, b/c there was no changed information on the user
                            }
                    	}
                    }
                }   
            }
            ArrayList aList = new ArrayList();
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOpportObj );
            //        iRetCode = m_OrgBRLOObj.getOppContactList( aList, iOppNid );
            httpServletRequest.setAttribute( "userlist", aList );

                      
              httpServletRequest.setAttribute( "org", aOrgInfoObj );
              httpServletRequest.setAttribute( "opp", aOpportObj );

              iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
              iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "opporgcontactmanage" );
          }
          // end-of method processEditOppContacts()

          
         	/*
         	 * * reset a contact for opportunity - whether they receive emails or not
         	 */
            public ActionForward resetOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
            {
          	  int iRetCode=0,iRetCode2=0;
            	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
          	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          	  DynaActionForm oFrm = (DynaActionForm)actionForm;
         	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
          	  String aszOrgNid = null;
          	  if(httpServletRequest.getParameter("orgnid") != null )
          		  aszOrgNid = httpServletRequest.getParameter("orgnid");
          	  else
          		  if(null != aSessDat)
          			  aszOrgNid = "" + aSessDat.getOrgNID();
          	  
          	  String aszOppNid = null;
          	  int iOppNid=0;
          	  if(httpServletRequest.getParameter("oppnid") != null )
          		  aszOppNid = httpServletRequest.getParameter("oppnid");
          	  else
          		  if(null != aSessDat)
          			  aszOppNid = "" + aSessDat.getOppNID();
          	  try{
          		   iOppNid=Integer.parseInt(aszOppNid);
          	  }catch (Exception e){}
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
              	  if(null != aSessDat){
        				  aSessDat.setOrgNID( aszOrgNid );
        				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
        			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
                }
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
              	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              		  return actionMapping.findForward( "showlogin" );
              	  }else{
                        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              	  }
                }
                boolean bAdminRole=false;
                String aszRole = m_BaseHelp.getFormData(oFrm,"role");
                if(! (aszRole==null || aszRole.equals(null))){
              	  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
              		  bAdminRole=true;
              	  }else{
              		  aszRole="";
              	  }
                }
                boolean bByContact = false;
                String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
                if(aszReqType.contains("ByContact")){
               	 bByContact = true;
                }
          	  String aszSubdomain = "";
        	  if(httpServletRequest.getParameter("subdomain") != null )
        		  aszSubdomain = httpServletRequest.getParameter("subdomain");
                OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
                aOpportObj.setORGNID( aszOrgNid );
                aOpportObj.setOPPNID( aszOppNid );
                aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
                allocatedOrgBRLO( httpServletRequest );
                OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                aOrgInfoObj.setORGNID( aszOrgNid );
                aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

                int iNatlAssocNID = 0;
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
                
                if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
              		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                ){
              	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
                }else{
	                if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	                 	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//              	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                  int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                  if(iTid > 0){
	                 	 aOrgInfoObj.setORGNID( aszOrgNid );
	                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                  }
            	     }else{
            	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            	     }
                    if(aCurrentUserObj.getNatlAssociationNID()>0){
            			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
            	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
            	     }else{
            	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
            		}
                }
                
                if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
                	if(bByContact == true){
                		// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                		iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                		iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
                	}
                	else                    if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                  		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                     ){
                  	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                     }else{
                         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                     }
                     if(iRetCode == -111){
                  	   if(bAdminRole==true){
                  		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
                  		   return actionMapping.findForward( "noaccess" );
                  	   }else{
                  		   return actionMapping.findForward( "404" );
                  	   }
                     }
                }
           	  if(aszSubdomain.length()<1)
         		  aszSubdomain = aOpportObj.getOPPSubdom();
                aOrgInfoObj.setORGSubdom(aszSubdomain);
                aOpportObj.setOPPSubdom(aszSubdomain);
                httpServletRequest.setAttribute("org", aOrgInfoObj);
                m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                if(0 != iRetCode){
              	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              		  return actionMapping.findForward( "showlogin" );
              	  }else{
                        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              	  }
                }
                
                // user owns the org; they can manage another user's access on the organization
                // load data for organization contact person
                PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
                aContactPersonObj.setUserUID( httpServletRequest.getParameter("uid") );
                
                allocatedIndBRLO( httpServletRequest );
                iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );//EMAIL ); //(in reality, this is by email address)
                aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
                
                String aszIsVolunteerContact = m_BaseHelp.getFormData(oFrm,"isemailcontact");
                int iIsVolunteerContact = m_BaseHelp.convertToInt( aszIsVolunteerContact );
                aContactPersonObj.setIsVolunteerContact(iIsVolunteerContact); // this is what the value WILL be
                 if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
                 	// the user was correctly loaded and exists
                     iRetCode = m_OrgBRLOObj.resetOppContact( aOpportObj , aContactPersonObj, iIsVolunteerContact ); // really only needs a uid to get inserted.
                 }else{
                 	// the email address could not be found in our database
                 	String aszErr="The user could not be found in our database.";
                    	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
                  	httpServletRequest.setAttribute("org", aOrgInfoObj);
                 	return showOppContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);         	
                 }
                 
                 /*
                  * START email trigger section
                  */
                 aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
                 aContactPersonObj.setUSPSubdom(aszSubdomain);//(aContactPersonObj.getUSPSubdom());
                 if(iRetCode==-444){ // the user's email status has not changed, so we don't want to trigger an email 
                 }else if(iRetCode==0){
                     // ******************!!!!!!!!!!!!!!! add a method to email that they are now A. Receiving, emails now ************
                     //iRetCode = m_IndBRLOObj.notifyNewAdmin( httpServletRequest, aContactPersonObj, aOrgInfoObj, iIsVolunteerContact );
                     int iEmailUseCase = iNowGetsEmails;
                     iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj,aOpportObj, iEmailUseCase,aszPortal );
                     if(iRetCode==-100){
                    	 // The user did not receive any email notification, b/c there was no changed information on the user
                     }
                 }else if(iRetCode==1){
                     // B. Not receiving emails now ************
                     //iRetCode = m_IndBRLOObj.notifyNewAdmin( httpServletRequest, aContactPersonObj, aOrgInfoObj, iIsVolunteerContact );
                      int iEmailUseCase = iNoLongerGetsEmails;
                     iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, iEmailUseCase,aszPortal );
                     if(iRetCode==-100){
                    	 // The user did not receive any email notification, b/c there was no changed information on the user
                     }
                 }
                 /*
                  * END email trigger section
                  */
              	 httpServletRequest.setAttribute("org", aOrgInfoObj);
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 // contact/administrator list for organization
                 ArrayList aList = new ArrayList();
                 iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
                 httpServletRequest.setAttribute( "userlist", aList );
                 httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
                 
                 httpServletRequest.setAttribute("opp", aOpportObj);
                 iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                 iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
   
              	if(bAdminRole==true){
             		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
             	}

                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "oppcontactmanage" );
              }
              // end-of method resetOppContact()
            
           	/*
           	 * * reset a Primary Contact for opportunity - whether they receive emails or not
           	 */
              public ActionForward setOppPrimaryContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
              {
            	  int iRetCode=0,iRetCode2=0;
                	getPortalInfo( httpServletRequest, httpServletResponse);
            		boolean bNatlAssoc = false;
            		if(aszPortalRequestType.equals("natlassoc")){
            			bNatlAssoc=true;
            		}
            	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	  DynaActionForm oFrm = (DynaActionForm)actionForm;
             	  getLoggedInStatus(httpServletRequest, httpServletResponse);
             	  if(aszLoggedInStatus.equals("showlogin")){
             		  return actionMapping.findForward( "showlogin" );
             	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
             		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	  }
            	  String aszOrgNid = null;
            	  if(httpServletRequest.getParameter("orgnid") != null )
            		  aszOrgNid = httpServletRequest.getParameter("orgnid");
            	  else
            		  if(null != aSessDat)
            			  aszOrgNid = "" + aSessDat.getOrgNID();
            	  
            	  String aszOppNid = null;
            	  int iOppNid=0;
            	  if(httpServletRequest.getParameter("oppnid") != null )
            		  aszOppNid = httpServletRequest.getParameter("oppnid");
            	  else
            		  if(null != aSessDat)
            			  aszOppNid = "" + aSessDat.getOppNID();
            	  try{
            		   iOppNid=Integer.parseInt(aszOppNid);
            	  }catch (Exception e){}
            	  String aszSubdomain = null;
            	  if(httpServletRequest.getParameter("subdomain") != null )
            		  aszSubdomain = httpServletRequest.getParameter("subdomain");
                  if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
                	  if(null != aSessDat){
          				  aSessDat.setOrgNID( aszOrgNid );
          				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
          			  }
          			if(aCurrentUserObj.getUserProfileNID() < 1){ 
        				// this user is an old drupal user-only; need to take through partial account creation process
        				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
        				//		as well as an insert into the rails db
        				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
        		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
        			}
        	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
            		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                 	return actionMapping.findForward("mappingpage");
                  }
                  if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
                	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                		  return actionMapping.findForward( "showlogin" );
                	  }else{
                          return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                	  }
                  }
                  boolean bAdminRole=false;
                  String aszRole = m_BaseHelp.getFormData(oFrm,"role");
                  if(! (aszRole==null || aszRole.equals(null))){
                	  if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
                		  bAdminRole=true;
                	  }else{
                		  aszRole="";
                	  }
                  }
                  boolean bByContact = false;
                  String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
                  if(aszReqType.contains("ByContact")){
                 	 bByContact = true;
                  }
                  OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
                  aOpportObj.setORGNID( aszOrgNid );
                  aOpportObj.setOPPNID( aszOppNid );
                  aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
                  allocatedOrgBRLO( httpServletRequest );
                  OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                  aOrgInfoObj.setORGNID( aszOrgNid );
                  aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

                  int iNatlAssocNID = 0;
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
                  
                  if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                  ){
                	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
                  }else{
	                  if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	                   	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//                	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                    int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                    if(iTid > 0){
	                   	 aOrgInfoObj.setORGNID( aszOrgNid );
	                        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                    }
              	     }else{
              	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
              	     }
                      if(aCurrentUserObj.getNatlAssociationNID()>0){
              			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
              	     }else{
              	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
              		}
                  }
                  if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
                		if(bByContact == true){
                			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
                		}
                		else                      if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                    		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                       ){
                    	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                       }else{
                           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                       }
                       if(iRetCode == -111){
                    	   if(bAdminRole==true){
                    		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this opportunity,\r\nor the opportunity does not exist" );
                    		   return actionMapping.findForward( "noaccess" );
                    	   }else{
                    		   return actionMapping.findForward( "404" );
                    	   }
                       }
                  }
                  aOrgInfoObj.setORGSubdom(aszSubdomain);
                  aOpportObj.setOPPSubdom(aszSubdomain);
                  httpServletRequest.setAttribute("org", aOrgInfoObj);
                  m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                  if(0 != iRetCode){
                	  if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                		  return actionMapping.findForward( "showlogin" );
                	  }else{
                          return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                	  }
                  }
                  
                  // user owns the org; they can manage another user's access on the organization
                  // load data for organization contact person
                  PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
                  aContactPersonObj.setUserUID( httpServletRequest.getParameter("uid") );
                  
                  allocatedIndBRLO( httpServletRequest );
                  iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );//EMAIL ); //(in reality, this is by email address)
                  aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
                  
                  String aszIsVolunteerContact = m_BaseHelp.getFormData(oFrm,"isemailcontact");
                  int iIsVolunteerContact = m_BaseHelp.convertToInt( aszIsVolunteerContact );
                  aContactPersonObj.setIsVolunteerContact(iIsVolunteerContact); // this is what the value WILL be

                  // contact/administrator list for organization
                  ArrayList aList = new ArrayList();

                  if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
                   	// the user was correctly loaded and exists
                       iRetCode = m_OrgBRLOObj.resetOppPrimaryContact( aOpportObj , aContactPersonObj, iIsVolunteerContact ); // really only needs a uid to get inserted.
                   }else{
                   	// the email address could not be found in our database
                   	String aszErr="The user could not be found in our database.";
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
//                    	httpServletRequest.setAttribute("org", aOrgInfoObj);
//                    	return showOppContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);         	
                        iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
                        httpServletRequest.setAttribute( "userlist", aList );
                        httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
                   	 httpServletRequest.setAttribute("org", aOrgInfoObj);
                        httpServletRequest.setAttribute("opp", aOpportObj);
                        iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                        iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
                        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                        return actionMapping.findForward( "oppcontactmanage" );
                   }
                   
                   /*
                    * START email trigger section
                    */
                  aContactPersonObj.setUSPEmail1Addr(aContactPersonObj.getUSPEmail1Addr());
                  aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
                  aContactPersonObj.setUSPSubdom(aszSubdomain);//(aContactPersonObj.getUSPSubdom());
                   if(iRetCode==-444){ // the user's email status has not changed, so we don't want to trigger an email 
                   }else if(iRetCode==0){
                       // ******************!!!!!!!!!!!!!!! add a method to email that they are now A. Receiving, emails now ************
                       int iEmailUseCase = iNewPrimaryContact;
                       iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj,aOpportObj, iEmailUseCase,aszPortal );
                       if(iRetCode==-100){
                      	 // The user did not receive any email notification, b/c there was no changed information on the user
                       }
                   }else if(iRetCode==1){
                       // B. Not receiving emails now ************
                        int iEmailUseCase = iNoLongerPrimaryContact;
                       iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj,aOpportObj, iEmailUseCase,aszPortal );
                       if(iRetCode==-100){
                      	 // The user did not receive any email notification, b/c there was no changed information on the user
                       }
                   }
                   /*
                    * END email trigger section
                    */
                   m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                   // contact/administrator list for organization
//                   ArrayList aList = new ArrayList();
                   iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
                   httpServletRequest.setAttribute( "userlist", aList );
                   httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
              	 httpServletRequest.setAttribute("org", aOrgInfoObj);
                   httpServletRequest.setAttribute("opp", aOpportObj);
                   iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                   iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
     
                	if(bAdminRole==true){
               		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
               	}

                    loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                   return actionMapping.findForward( "oppcontactmanage" );
                }
                // end-of method setOppPrimaryContact()
          
          /*
           * show opportunity delete contact page
           */
           public ActionForward showRemoveOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
           	int iRetCode=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	DynaActionForm oFrm = (DynaActionForm)actionForm;
           	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
        	   String aszOrgNid = null;
        	   if(httpServletRequest.getParameter("orgnid") != null ){
        		   aszOrgNid = httpServletRequest.getParameter("orgnid");
        	   }else{
        		   if(null != aSessDat){
        			   aszOrgNid = "" + aSessDat.getOrgNID();
        		   }
        	   }
        	   String aszOppNid = null;
        	   int iOppNid=0;
        	   if(httpServletRequest.getParameter("oppnid") != null ){
        		   aszOppNid = httpServletRequest.getParameter("oppnid");
        	   }else{
        		   if(null != aSessDat){
        			   aszOppNid = "" + aSessDat.getOppNID();
        		   }
        	   }
        	   try{
        		   iOppNid=Integer.parseInt(aszOppNid);
        	   }catch (Exception e){}
        	   String aszUID = null;
        	   int iUID=0;
        	   if(httpServletRequest.getParameter("uid") != null ){
        		   aszUID = httpServletRequest.getParameter("uid");
        	   }
        	   try{
        		   iUID=Integer.parseInt(aszUID);
        	   }catch (Exception e){}
                 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
       			  if(null != aSessDat){
       				  aSessDat.setOrgNID( aszOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
       			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
       		 }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
               }
               boolean bAdminRole=false;
               String aszRole = m_BaseHelp.getFormData(oFrm,"role");
               if(! (aszRole==null || aszRole.equals(null))){
              	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
                	bAdminRole=true;
              	 }else{
              		 aszRole="";
              	 }
               }
               boolean bByContact = false;
               String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
               if(aszReqType.contains("ByContact")){
              	 bByContact = true;
               }
               OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
               aOpportObj.setORGNID( aszOrgNid );
               aOpportObj.setOPPNID( aszOppNid );
               aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
               allocatedOrgBRLO( httpServletRequest );
               OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
               aOrgInfoObj.setORGNID( aszOrgNid );
               aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

               int iNatlAssocNID = 0;
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
               
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
            	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
               }else{
	               if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
	                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
	//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
	                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
	                 if(iTid > 0){
	                	 aOrgInfoObj.setORGNID( aszOrgNid );
	                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
	                 }
           	     }else{
           	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           	     }
                   if(aCurrentUserObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
           			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
           	     }else{
           	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
           		}
               }
               if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
            		if(bByContact == true){
            			// check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
            			iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
            			iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
            		}
            		else                  if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                   ){
                	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                   }else{
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                   }
                   if(iRetCode == -111){
                	   if(bAdminRole==true){
                		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
                		   return actionMapping.findForward( "noaccess" );
                	   }else{
                		   return actionMapping.findForward( "404" );
                	   }
                   }
               }
               httpServletRequest.setAttribute("opp", aOpportObj);
               httpServletRequest.setAttribute("org", aOrgInfoObj);
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               int iRetCode2=0;
              	
               
               //iUID = aCurrentUserObj.getUserUID();
                String aszContactUID=  httpServletRequest.getParameter("uid") ;
             // load data for organization contact person
           	int iContactUID = m_BaseHelp.convertToInt( aszContactUID );
             PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
             aContactPersonObj.setUserUID( iContactUID );
             allocatedIndBRLO( httpServletRequest );
             iRetCode2 = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
             aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
             m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );
             httpServletRequest.setAttribute("userprofile", aContactPersonObj);
//             m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );

             loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "oppcontactremove" );
           }
           // end-of method showRemoveOppContact
           
       	/*
           * delete processRemoveOppContact
           */
           public ActionForward processRemoveOppContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
        	   int iRetCode=0;
             	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			//httpServletRequest.setAttribute("redirectpage","noportalexists");
        			//return actionMapping.findForward("mappingpage");
        			return actionMapping.findForward("404");
            	}
            }
        	   DynaActionForm oFrm = (DynaActionForm)actionForm;
        	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	   String aszOrgNid = null;
        	   if(httpServletRequest.getParameter("orgnid") != null ){
        		   aszOrgNid = httpServletRequest.getParameter("orgnid");
        	   }else{
        		   if(null != aSessDat){
        			   aszOrgNid = "" + aSessDat.getOrgNID();
        		   }
        	   }
        	   String aszOppNid = null;
        	   int iOppNid=0;
        	   if(httpServletRequest.getParameter("oppnid") != null ){
        		   aszOppNid = httpServletRequest.getParameter("oppnid");
        	   }else{
        		   if(null != aSessDat){
        			   aszOppNid = "" + aSessDat.getOppNID();
        		   }
        	   }
        	   try{
        		   iOppNid=Integer.parseInt(aszOppNid);
        	   }catch (Exception e){}
        	   
        	   if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        		   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        			   return actionMapping.findForward( "showlogin" );
        		   }else{
        			   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        		   }
        	   }
               getLoggedInStatus(httpServletRequest, httpServletResponse);
//PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        	   if(null == aCurrentUserObj) {
        		   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        			   return actionMapping.findForward( "showlogin" );
        		   }else{
        			   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        		   }
        	   }
        	   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        		   if(null != aSessDat){
        			   aSessDat.setOrgNID( aszOrgNid );
        			   aSessDat.setOppNID( aszOppNid );
        			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWOPPCONTACTS );
        		   }
       			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        	   }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            		   return actionMapping.findForward( "showlogin" );
            	   }else{
            		   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	   }
               }
               
               boolean bAdminRole=false;
               String aszRole = m_BaseHelp.getFormData(oFrm,"role");
               if(! (aszRole==null || aszRole.equals(null))){
            	   if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            		   bAdminRole=true;
            	   }else{
            		   aszRole="";
            	   }
               }
               
               OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
               aOpportObj.setORGNID( aszOrgNid );
               aOpportObj.setOPPNID( aszOppNid );
               aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all
               allocatedOrgBRLO( httpServletRequest );
               OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
               aOrgInfoObj.setORGNID( aszOrgNid );
               aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said ****OPPORTUNITY*** or org in order to manage at all

               int iNatlAssocNID = 0;
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
               
               boolean bByContact = false;
               String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
               if(aszReqType.contains("ByContact")){
              	 bByContact = true;
               }
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
            	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
        	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
               }else{
               if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
                 if(iTid > 0){
                	 aOrgInfoObj.setORGNID( aszOrgNid );
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                 }
           	     }else{
           	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           	     }
                   if(aCurrentUserObj.getNatlAssociationNID()>0){
           			aOpportObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
           	         iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_NATL_ASSOC, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") );
           	     }else{
           	        iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 0,"", OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublished
           		}
               }
               if(iRetCode == -111){ // if failed on management of said opportunity, see if this user has ORG management rights
               	if(bByContact == true){
               	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
                  		 iRetCode = m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNid, 2,"",OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ); // listings, published and unpublished by CONTACT
         	}
               	else                  if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
                		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                   ){
                	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                   }else{
                       iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
                   }
                   if(iRetCode == -111){
                	   if(bAdminRole==true){
                		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
                		   return actionMapping.findForward( "noaccess" );
                	   }else{
                		   return actionMapping.findForward( "404" );
                	   }
                   }
               }
               httpServletRequest.setAttribute("org", aOrgInfoObj);
               httpServletRequest.setAttribute("opp", aOpportObj);
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               int iRetCode2=0;
              	int iUID = aCurrentUserObj.getUserUID();
                String aszContactUID=  httpServletRequest.getParameter("uid") ;
             // load data for organization contact person
           	int iContactUID = m_BaseHelp.convertToInt( aszContactUID );
             PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
             aContactPersonObj.setUserUID( iContactUID );
             allocatedIndBRLO( httpServletRequest );
             iRetCode2 = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
             aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
             m_OrgActHelp.fillContactDataIntoForm( oFrm, aContactPersonObj );
//             m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );

             if(-1 == iRetCode2){
             	aOrgInfoObj.appendErrorMsg("error loading opportunity contact");
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "oppcontactremove" );
             }
             // delete organizational contact
             iRetCode = m_OrgBRLOObj.deleteOppContact( aContactPersonObj, aOpportObj );
             if(0 != iRetCode){
             	aOrgInfoObj.appendErrorMsg("error removing organizational contact");
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "oppcontactremove" );
             }
             
        	  String aszSubdomain = "";
         	  if(httpServletRequest.getParameter("subdomain") != null )
        		  aszSubdomain = httpServletRequest.getParameter("subdomain");
         	  else
         		  aszSubdomain = aOpportObj.getOPPSubdom();
             // trigger emails to notify current user and removed user of the removal
             int iEmailUseCase = iNoLongerIsOppContact;
             aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
             aContactPersonObj.setUSPSubdom(aszSubdomain);//(aContactPersonObj.getUSPSubdom());
             iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj,aOpportObj, iEmailUseCase,aszPortal ); //(in reality, this is by email address)

             allocatedIndBRLO( httpServletRequest );
             iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
             allocatedOrgBRLO( httpServletRequest );

             // get individual data from web form 
             iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aCurrentUserObj);
             if(aCurrentUserObj.getUserUID() < 1){ // to catch the case where a user's uid might have been cleared to 0 b/c it's not in the form above
            	 aCurrentUserObj.setUserUID(iUID);
             }
             if(iRetCode != 0){
               	m_BaseHelp.setFormData(oFrm,"errormsg", aCurrentUserObj.getErrorMsg() );
               	return actionMapping.findForward( "showregistration" );
             }
             iRetCode = m_IndBRLOObj.loadUserByOption( aCurrentUserObj ,aCurrentUserObj.LOADBY_UID, aszSiteVersion);
             iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aCurrentUserObj);
              
          	// if the CURRENT USER is the one who has been removed, then the user should just be forwarded to generic orgmanagement
          	if(aCurrentUserObj.getUserUID()==iContactUID){
          		return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}

          	session.setAttribute("orgmanagementnid", aszOrgNid);
          	httpServletRequest.setAttribute("org", aOrgInfoObj);
             m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
             
             // contact/administrator list for organization
             allocatedOrgBRLO( httpServletRequest );

          	if(bAdminRole==true){
          		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
          	}
            // contact/administrator list for organization
            ArrayList aList = new ArrayList();
            iRetCode = m_OrgBRLOObj.getOppContactList( aList, aOpportObj.getOPPNID() );
            httpServletRequest.setAttribute( "userlist", aList );
            httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
            
            httpServletRequest.setAttribute("opp", aOpportObj);
            if(null != aSessDat){
         	   aSessDat.setTokenKey(null);
         	   aSessDat.setOrgNID(null);
         	   aSessDat.setOppNID(null);
         	   aSessDat.setSubdomain(null);
         	   aSessDat.setSiteEmail(null);
            }
            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
            iRetCode = m_OrgBRLOObj.setOppContactListArray( aOpportObj );
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
            return actionMapping.findForward( "oppcontactmanage" );
          
           }
           // end-of method processRemoveOppContact()
      //=== END OppContacts section
      //=== END OppContacts section
      //=== END OppContacts section
          
          // === BEGIN OrgContacts section
          // === BEGIN OrgContacts section
          // === BEGIN OrgContacts section
          

         	/*
              * show organization contacts list page
              */
            public  ActionForward showOrgContacts(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
            {
           	   int iRetCode=0;
             	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
         	   if(aszPortal.length()>0){
         		   if(aszPortalNID.length()==0){
               		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
           			return actionMapping.findForward("404");
         		   }
         	   }
         	   DynaActionForm oFrm = (DynaActionForm)actionForm;
         	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	   String aszOrgNid = null;
         	   if(httpServletRequest.getParameter("orgnid") != null ){
         		   aszOrgNid = httpServletRequest.getParameter("orgnid");
         	   }else{
         		   if(null != aSessDat){
         			   aszOrgNid = "" + aSessDat.getOrgNID();
         		   }
         	   }
         	   
          	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
         	   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
         		   if(null != aSessDat){
         			   aSessDat.setOrgNID( aszOrgNid );
         			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
         		   }
       			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
         	   }
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             		   return actionMapping.findForward( "showlogin" );
             	   }else{
             		   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	   }
                }
                
                boolean bAdminRole=false;
                String aszRole = m_BaseHelp.getFormData(oFrm,"role");
                if(! (aszRole==null || aszRole.equals(null))){
             	   if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
             		   bAdminRole=true;
             	   }else{
             		   aszRole="";
             	   }
                }
                
                OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                aOrgInfoObj.setORGNID( aszOrgNid );
                aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
                allocatedOrgBRLO( httpServletRequest );

                int iNatlAssocNID = 0;
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
                
                if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
             		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                ){
             	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                }else{
               if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                 	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//              	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                  int iTid = aOrgInfoObj.getORGAffiliation1TID();
                  if(iTid > 0){
                 	 aOrgInfoObj.setORGNID( aszOrgNid );
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                  }
            	     }else{
            	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            	     }
                }
                if(iRetCode == -111){
             	   if(bAdminRole==true){
             		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
             		   return actionMapping.findForward( "noaccess" );
             	   }else{
             		   return actionMapping.findForward( "404" );
             	   }
                }
                httpServletRequest.setAttribute("org", aOrgInfoObj);
                m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                
                // contact/administrator list for organization
                allocatedOrgBRLO( httpServletRequest );
                ArrayList aList = new ArrayList();
                //iRetCode = m_OrgBRLOObj.getOrganizationContactList_LEGACY( aList, aOrgInfoObj.getORGNID() );
                iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                httpServletRequest.setAttribute( "userlist", aList );
                httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
                
                if(null != aSessDat){
             	   aSessDat.setTokenKey(null);
             	   aSessDat.setOrgNID(null);
             	   aSessDat.setOppNID(null);
             	   aSessDat.setSubdomain(null);
             	   aSessDat.setSiteEmail(null);
                }
                iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "orgadmincontactmanage" );
//                return actionMapping.findForward( "orgcontactmanage" );
            }
            // end-of method showOrg contacts
            
          	/*
              * processEditOrgContactAdmins
              */
              public ActionForward processEditOrgContactAdmins(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
              {
              	int iRetCode=0;
              	getPortalInfo( httpServletRequest, httpServletResponse);
        		boolean bNatlAssoc = false;
        		if(aszPortalRequestType.equals("natlassoc")){
        			bNatlAssoc=true;
        		}
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
                if(aszPortal.length()>0){
                	if(aszPortalNID.length()==0){
                		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            			return actionMapping.findForward("404");
                	}
                }
            	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
             	DynaActionForm oFrm = (DynaActionForm)actionForm;
           	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
                boolean bByContact = false;
                String aszReqType =  m_BaseHelp.getFormData(oFrm,"requesttype");
                if(aszReqType.contains("ByContact")){
                	bByContact = true;
                }

                String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
                int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
                if(iOrgNid < 1){
             		aszOrgNid = "" + aSessDat.getOrgNID();
                 	if(iOrgNid < 1 ){//&& bByContact == false){
        	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        	             	return actionMapping.findForward( "showlogin" );
        	         	}else{
        	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	         	}
                 	}
                }
        		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        			  if(null != aSessDat){
        				  aSessDat.setOrgNID( aszOrgNid );
        				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGVIEWOPP );
        			  }
        				if(aCurrentUserObj.getUserProfileNID() < 1){ 
        					// this user is an old drupal user-only; need to take through partial account creation process
        					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
        					//		as well as an insert into the rails db
        					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
        			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
        				}
        		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
        	         	return actionMapping.findForward("mappingpage");
        		 }
                if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
                	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                    	return actionMapping.findForward( "showlogin" );
                	}else{
                        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                	}
                }
                boolean bAdminRole=false;
                String aszRole = m_BaseHelp.getFormData(oFrm,"role");
                if(! (aszRole==null || aszRole.equals(null))){
               	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
                 	bAdminRole=true;
               	 }else{
               		 aszRole="";
               	 }
                }
                OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                iRetCode = m_OrgActHelp.getOrgDataFromForm1(oFrm, aOrgInfoObj);
                aOrgInfoObj.setORGNID( aszOrgNid );
                aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE editing access of the said opportunity/org association
                allocatedOrgBRLO( httpServletRequest );

                int iNatlAssocNID = 0;
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
                
                // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
                if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
              		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                ){
              	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                }else{
                if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                 	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//              	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                  int iTid = aOrgInfoObj.getORGAffiliation1TID();
                  if(iTid > 0){
                 	 aOrgInfoObj.setORGNID( iOrgNid );
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                  }
           	     }else{
           	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           	     }
        	         if(iRetCode==-111){
        	         	if(bByContact == true){
        	               	 // check that the user has access rights to edit/manage this opportunity, if done through contact and not through org admin
        	                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
        	         	}
        	         }
                }
                if(iRetCode == -111){
                	if(bAdminRole==true){
        	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
        	          	return actionMapping.findForward( "noaccess" );
                	}else{
                		// org did not exist
                    	return actionMapping.findForward( "404" );
                	}
                }
                if(0 != iRetCode){
                	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                    	return actionMapping.findForward( "showlogin" );
                	}else{
                        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
                	}
            	}
                iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                
                boolean bContactsChanged=false,bAdminsChanged=false;
                // check to see if any of the Contact data on the given opportunity has changed...
                if(! aOrgInfoObj.getORGAdminUIDsModifiedArray().equals(aOrgInfoObj.getORGAdminUIDsArray())){
                	bAdminsChanged=true;
                }
                if(! aOrgInfoObj.getORGContactUIDsModifiedArray().equals(aOrgInfoObj.getORGContactUIDsArray())){
                	bContactsChanged=true;
                }
                ArrayList aListTmp = new ArrayList(2);

        		ArrayList aListIdsContactsChanged = null;
                ArrayList aListIdsAdminsChanged = null;
                ArrayList aListIdsAndEmailNotifyFlag = null;
                
                ArrayList aContactOppList = new ArrayList();
                httpServletRequest.setAttribute( "contactopplist", aContactOppList );
                ArrayList aContactNotRemovedList = new ArrayList();
                httpServletRequest.setAttribute( "contactnotremovedlist", aContactNotRemovedList );
                
           	  String aszSubdomain = "";
         	  if(httpServletRequest.getParameter("subdomain") != null )
        		  aszSubdomain = httpServletRequest.getParameter("subdomain");
         	  else
         		  aszSubdomain = aOrgInfoObj.getORGSubdom();
         	  if(aszSubdomain.length()<1)
         		  aszSubdomain = aOrgInfoObj.getORGSubdom();
              if(bContactsChanged==true || bAdminsChanged==true){ 
                	// need to update contacts for opportunity
//         admins don't necessarily have to be written to the usermail table; not unless they are "email" contacts
                    // get arrays of uids that show the differences - should prob be done in BRL, but will need to trigger the emailNotify for each user changed
                	aListIdsContactsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOrgInfoObj.getORGContactUIDsArray(),aOrgInfoObj.getORGContactUIDsModifiedArray());
                	aListIdsAdminsChanged = m_OrgBRLOObj.getIntArrayListDifferences(aOrgInfoObj.getORGAdminUIDsArray(),aOrgInfoObj.getORGAdminUIDsModifiedArray());

                	aListIdsAndEmailNotifyFlag=m_OrgBRLOObj.getEmailNotifyFlag(aListIdsContactsChanged,aListTmp,aListIdsAdminsChanged,0,0);//,iInitUID,iModifiedUID);
                	
                	iRetCode=m_OrgBRLOObj.updateORGAdmins(aListIdsAndEmailNotifyFlag, aOrgInfoObj);
                	iRetCode=m_OrgBRLOObj.updateORGContacts(aListIdsAndEmailNotifyFlag, aOrgInfoObj);

                	if(iRetCode!=0){
	                	aOrgInfoObj.appendErrorMsg("Some of these user(s) are listed as contacts of opportunities, " +
	        					"and have therefore not been removed as Organizational Contacts");
	                	//                			uid-" + iUID +" opp_nid-"+iTmp);
	                	// iterate through getOrgContactRemoveSkipped
	                	for(int i=0; i<aOrgInfoObj.getOrgContactRemoveSkipped().size(); i++){
	                		ArrayList<Object> aListUIDOppNIDsPair = (ArrayList)aOrgInfoObj.getOrgContactRemoveSkipped().get(i);
	                		if(aListUIDOppNIDsPair.size()>0){
	                			int iSkippedUID = 0;
	                			try{
	                				iSkippedUID = ((Number)aListUIDOppNIDsPair.get(0)).intValue();
	        	                	PersonInfoDTO aContactNotRemoved = new PersonInfoDTO();
	        	                	ArrayList aTempContactOppList = new ArrayList();
/*	                				
	                				// get all the OPP nids for this user
	                				ArrayList<Object> aListOppNIDs = (ArrayList)aListUIDOppNIDsPair.get(1);
	                				if(aListOppNIDs.size()>0){
	                					int[] a_iOppNIDs = new int[aListOppNIDs.size()];
	                					for(int j=0; j<aListOppNIDs.size(); j++){
	                						int iSkippedUIDOppNID = 0;
	                						iSkippedUIDOppNID = ((Number)aListOppNIDs.get(j)).intValue();
	                						a_iOppNIDs[j]=iSkippedUIDOppNID;
	                					}
	                				}
*/	                				
	                				aContactNotRemoved.setUserUID(iSkippedUID);
	        	                    iRetCode = m_IndBRLOObj.loadUserByOption( aContactNotRemoved, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
	        	                    aContactNotRemovedList.add(aContactNotRemoved);
	        	                    iRetCode = m_OrgBRLOObj.getOppListForContact( aTempContactOppList, iSkippedUID, aOrgInfoObj.getORGNID() );
	        	                    aContactOppList.addAll(aTempContactOppList);
	                			}catch(Exception e){}
	                		}
	                	}
	                    httpServletRequest.setAttribute( "contactnotremovedlist", aContactNotRemovedList );
	                    httpServletRequest.setAttribute( "contactopplist", aContactOppList );
	                	
                	}

                    for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
                        int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
                        int iUID=iIdAndFlag[0];
                        int iFlag=iIdAndFlag[1];
                        
                        // load the contact info for that user
                        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
                        aContactPersonObj.setUserUID(iUID);
                        iRetCode = m_IndBRLOObj.loadUserContactData( aContactPersonObj );        
                        aContactPersonObj.setUSPSubdom(aszSubdomain);
                        // then trigger the emailNotify Message(s) for that flag
                        if(iRetCode==0){
                        	// run db update on the org's usermail records

                        	// if an email should be triggered, - only for set as admin - trigger it
                        	if(iFlag==iAddedAdmin || iFlag ==iRemovedAdmin){ //if(iFlag!=iNewPrimaryContact && iFlag !=iNoLongerPrimaryContact){
                            	aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
                                int iEmailUseCase = iFlag;
                                iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, iEmailUseCase, aszPortal );
                                if(iRetCode==-100){
                               	 // The user did not receive any email notification, b/c there was no changed information on the user
                                }
                        	}
                        }
                    }   
                }
                ArrayList aList = new ArrayList();
                iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                httpServletRequest.setAttribute( "userlist", aList );

                          
                  httpServletRequest.setAttribute( "org", aOrgInfoObj );

                  iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                  iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                  loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "orgadmincontactmanage" );
//                return actionMapping.findForward( "orgcontactmanage" );
              }
              // end-of method processEditOrgContactAdmins()

          /*
           * show organization delete contact page
           */
           public ActionForward showRemoveOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
           	int iRetCode=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	DynaActionForm oFrm = (DynaActionForm)actionForm;
           	String aszOrgNid=null;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
              	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
              	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
              	if(iOrgNid < 1){
              		aszOrgNid = "" + aSessDat.getOrgNID();
                  	if(iOrgNid < 1){
         	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
         	             	return actionMapping.findForward( "showlogin" );
         	         	}else{
         	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	         	}
                  	}
              	}
                 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
       			  if(null != aSessDat){
       				  aSessDat.setOrgNID( aszOrgNid );
       				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
       			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
       		 }
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
               	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                   	return actionMapping.findForward( "showlogin" );
               	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
               	}
               }
               boolean bAdminRole=false;
               String aszRole = m_BaseHelp.getFormData(oFrm,"role");
               if(! (aszRole==null || aszRole.equals(null))){
              	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
                	bAdminRole=true;
              	 }else{
              		 aszRole="";
              	 }
               }
                OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
                aOrgInfoObj.setORGNID( aszOrgNid );
                aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
                allocatedOrgBRLO( httpServletRequest );

                int iNatlAssocNID = 0;
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
                
                // iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
                // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
                if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
              		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
                ){
              	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
                }else{
                if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                 	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//              	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                  int iTid = aOrgInfoObj.getORGAffiliation1TID();
                  if(iTid > 0){
                 	 aOrgInfoObj.setORGNID( iOrgNid );
                      iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                  }
            	     }else{
            	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            	     }
                }
                if(iRetCode == -111){
                	if(bAdminRole==true){
        	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
        	          	return actionMapping.findForward( "noaccess" );
                	}else{
                		// org did not exist
                    	return actionMapping.findForward( "404" );
                	}
                }
               httpServletRequest.setAttribute("org", aOrgInfoObj);
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               
               // contact/administrator for organization
               PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
               aContactPersonObj.setUserUID( httpServletRequest.getParameter("uid") );
               allocatedIndBRLO( httpServletRequest );
               iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
               m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );
               httpServletRequest.setAttribute("userprofile", aContactPersonObj);


            	if(bAdminRole==true){
          		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
          	}

                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "orgcontactremove" );
           }
           // end-of method showOrgDelete contacts
           
       	/*
           * delete OrgContact
           */
           public ActionForward processRemoveOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
           {
           	int iRetCode=0, iRetCode2=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            	DynaActionForm oFrm = (DynaActionForm)actionForm;
           	String aszOrgNid=null;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
            	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
          	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          	if(iOrgNid < 1){
          		aszOrgNid = "" + aSessDat.getOrgNID();
              	if(iOrgNid < 1){
     	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
     	             	return actionMapping.findForward( "showlogin" );
     	         	}else{
     	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
     	         	}
              	}
          	}
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( aszOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }
           	int iUID = aCurrentUserObj.getUserUID();
            String aszContactUID=  httpServletRequest.getParameter("uid") ;
            String aszContactFN=  httpServletRequest.getParameter("first") ;
            String aszContactLN=  httpServletRequest.getParameter("last") ;
             boolean bAdminRole=false;
             String aszRole = m_BaseHelp.getFormData(oFrm,"role");
             if(! (aszRole==null || aszRole.equals(null))){
            	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
              	bAdminRole=true;
            	 }else{
            		 aszRole="";
            	 }
             }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );

              int iNatlAssocNID = 0;
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
              
              // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
            	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else{
              if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//            	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                int iTid = aOrgInfoObj.getORGAffiliation1TID();
                if(iTid > 0){
               	 aOrgInfoObj.setORGNID( iOrgNid );
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                }
          	     }else{
          	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
          	     }
              }
              // load data for organization contact person
             	int iContactUID = m_BaseHelp.convertToInt( aszContactUID );
               PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
               aContactPersonObj.setUserUID( iContactUID );
               allocatedIndBRLO( httpServletRequest );
               iRetCode2 = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
              httpServletRequest.setAttribute("userprofile", aContactPersonObj);
              if(iRetCode == -111){
              	if(bAdminRole==true){
      	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
      	          	return actionMapping.findForward( "noaccess" );
              	}else{
              		// org did not exist
                  	return actionMapping.findForward( "404" );
              	}
              }
             httpServletRequest.setAttribute( "org", aOrgInfoObj );
             // make sure the user is not attempting to delete the sole contact/administrator of an organization
             ArrayList aContactList = new  ArrayList ( 2 );
             m_OrgBRLOObj.getOrgContactList( aContactList, iOrgNid, "org" );//??????? should this be passing null or "org"?
             if(aContactList.size() < 2){
             	aOrgInfoObj.appendErrorMsg("You must have more than one contact for your organization in order to remove a contact.");
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "orgcontactremove" );
             }
             
             aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
             m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );

             if(-1 == iRetCode2){
             	aOrgInfoObj.appendErrorMsg("error loading organizational contact");
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "orgcontactremove" );
             }
             ArrayList aList = new ArrayList();
             ArrayList aContactOppList = new ArrayList();
             httpServletRequest.setAttribute( "contactopplist", aContactOppList );
             // should make sure that this "Contact" is not currently aContactOppList for any opportunities; if so, then they need to be removed from the opps, first
             iRetCode = m_OrgBRLOObj.setOppsForOrgContactListArray( aOrgInfoObj, iContactUID );
             if(0 != iRetCode){
            	 aOrgInfoObj.appendErrorMsg(aszContactFN + " " + aszContactLN + " is currently listed as a contact on some of your Opportunities.\r\n");
            	 aOrgInfoObj.appendErrorMsg("Please remove " + aszContactFN + " " + aszContactLN + " from the Opportunities before proceeding.");
            	 // list of oppnids is in aOrgInfoObj.getOppNIDsArray()
            	 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 httpServletRequest.setAttribute("org", aOrgInfoObj);
                 iRetCode = m_OrgBRLOObj.getOppListForContact( aContactOppList, iContactUID, aOrgInfoObj.getORGNID() );
                 httpServletRequest.setAttribute( "contactopplist", aContactOppList );
                 
                 iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                 iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                 httpServletRequest.setAttribute( "userlist", aList );
//                 ArrayList aList = new ArrayList();
                 iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                 httpServletRequest.setAttribute( "userlist", aList );

                           
                   httpServletRequest.setAttribute( "org", aOrgInfoObj );

                   iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                   iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                   loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                   return actionMapping.findForward( "orgadmincontactmanage" );
 //            	 return actionMapping.findForward( "orgcontactmanage" );
              }
             // delete organizational contact
             iRetCode = m_OrgBRLOObj.deleteOrgContact( aContactPersonObj, aOrgInfoObj );
             if(0 != iRetCode){
             	aOrgInfoObj.appendErrorMsg("error removing organizational contact");
                 m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                 loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                 return actionMapping.findForward( "orgcontactremove" );
             }
             
        	  String aszSubdomain = "";
         	  if(httpServletRequest.getParameter("subdomain") != null )
        		  aszSubdomain = httpServletRequest.getParameter("subdomain");
         	  else
         		  aszSubdomain = aOrgInfoObj.getORGSubdom();
            // trigger emails to notify current user and removed user of the removal
             int iEmailUseCase = iRemovedAdmin;
             aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
             aContactPersonObj.setUSPSubdom(aszSubdomain);//aContactPersonObj.getUSPSubdom());
             iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, iEmailUseCase,aszPortal ); //(in reality, this is by email address)

             allocatedIndBRLO( httpServletRequest );
             iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
             allocatedOrgBRLO( httpServletRequest );
             // get individual data from web form 
             iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aCurrentUserObj);
             if(aCurrentUserObj.getUserUID() < 1){ // to catch the case where a user's uid might have been cleared to 0 b/c it's not in the form above
            	 aCurrentUserObj.setUserUID(iUID);
             }
             if(iRetCode != 0){
               	m_BaseHelp.setFormData(oFrm,"errormsg", aCurrentUserObj.getErrorMsg() );
               	return actionMapping.findForward( "showregistration" );
             }
             iRetCode = m_IndBRLOObj.loadUserByOption( aCurrentUserObj ,aCurrentUserObj.LOADBY_UID, aszSiteVersion);
             iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aCurrentUserObj);
              
          	// if the CURRENT USER is the one who has been removed, then the user should just be forwarded to generic orgmanagement
          	if(aCurrentUserObj.getUserUID()==iContactUID){
          		return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}

          	session.setAttribute("orgmanagementnid", aszOrgNid);
          	httpServletRequest.setAttribute("org", aOrgInfoObj);
             m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
             
             // contact/administrator list for organization
             allocatedOrgBRLO( httpServletRequest );
             iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
             httpServletRequest.setAttribute( "userlist", aList );
             httpServletRequest.setAttribute("userprofile", aCurrentUserObj);

          	if(bAdminRole==true){
          		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
          	}
//            iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
//            ArrayList aList = new ArrayList();
//            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
//            httpServletRequest.setAttribute( "userlist", aList );

                      
              httpServletRequest.setAttribute( "org", aOrgInfoObj );

              iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
              iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "orgadmincontactmanage" );
//             return actionMapping.findForward( "orgcontactmanage" );
           }
           // end-of method processDeleteOrgContact()
          
           // === END OrgContacts section
           // === END OrgContacts section

      
      
      //=== BEGIN OrgAdmins section
      //=== BEGIN OrgAdmins section
      //=== BEGIN OrgAdmins section

      /*
       * show organization Administrators list page
       */
       public ActionForward showOrgAdmins(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   int iRetCode=0;
         	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
    	   DynaActionForm oFrm = (DynaActionForm)actionForm;
    	   AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	   String aszOrgNid = null;
    	   if(httpServletRequest.getParameter("orgnid") != null ){
    		   aszOrgNid = httpServletRequest.getParameter("orgnid");
    	   }else{
    		   if(null != aSessDat){
    			   aszOrgNid = "" + aSessDat.getOrgNID();
    		   }
    	   }
      	  getLoggedInStatus(httpServletRequest, httpServletResponse);
      	  if(aszLoggedInStatus.equals("showlogin")){
      		  return actionMapping.findForward( "showlogin" );
      	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
      		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	  }
    	   if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    		   if(null != aSessDat){
    			   aSessDat.setOrgNID( aszOrgNid );
    			   aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWORGADMINS );
    		   }
   			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    	   }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
        		   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	   }
           }
           
           boolean bAdminRole=false;
           String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           if(! (aszRole==null || aszRole.equals(null))){
        	   if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        		   bAdminRole=true;
        	   }else{
        		   aszRole="";
        	   }
           }
           
           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           aOrgInfoObj.setORGNID( aszOrgNid );
           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization in order to manage at all
           allocatedOrgBRLO( httpServletRequest );

           int iNatlAssocNID = 0;
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
           
           if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
        		   aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
           ){
        	   aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
               iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
           }else{
           if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
            	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//         	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
             int iTid = aOrgInfoObj.getORGAffiliation1TID();
             if(iTid > 0){
            	 aOrgInfoObj.setORGNID( aszOrgNid );
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
             }
       	     }else{
       	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
       	     }
           }
           if(iRetCode == -111){
        	   if(bAdminRole==true){
        		   m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
        		   return actionMapping.findForward( "noaccess" );
        	   }else{
        		   return actionMapping.findForward( "404" );
        	   }
           }
           httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           
           // contact/administrator list for organization
           allocatedOrgBRLO( httpServletRequest );
//           ArrayList aList = new ArrayList();
//           iRetCode = m_OrgBRLOObj.getOrgAdminList( aList, aOrgInfoObj.getORGNID() );
//           httpServletRequest.setAttribute( "userlist", aList );
           httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
           
           if(null != aSessDat){
        	   aSessDat.setTokenKey(null);
        	   aSessDat.setOrgNID(null);
        	   aSessDat.setOppNID(null);
        	   aSessDat.setSubdomain(null);
        	   aSessDat.setSiteEmail(null);
           }
           ArrayList aList = new ArrayList();
           iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
           httpServletRequest.setAttribute( "userlist", aList );

                     
             httpServletRequest.setAttribute( "org", aOrgInfoObj );

             iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
             iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
             loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
             return actionMapping.findForward( "orgadmincontactmanage" );
//           return actionMapping.findForward( "orgadminmanage" );
       }
       // end-of method showOrgAdmins contacts

      
       
       /*
       * show add administrators for organization view page (managing org)
       */
       public ActionForward showAddOrgAdmin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	int iRetCode=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	String aszErr = m_BaseHelp.getFormData(oFrm,"errormsg");
           String aszOrgNid = null;
           if(httpServletRequest.getParameter("orgnid") != null ){
               aszOrgNid = httpServletRequest.getParameter("orgnid");
           }else{
           	if(null != aSessDat){
           		aszOrgNid = "" + aSessDat.getOrgNID();
           	}
           }
      	  getLoggedInStatus(httpServletRequest, httpServletResponse);
      	  if(aszLoggedInStatus.equals("showlogin")){
      		  return actionMapping.findForward( "showlogin" );
      	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
      		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      	  }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
   			  if(null != aSessDat){
   				  aSessDat.setOrgNID( aszOrgNid );
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
   			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
   		 }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "showlogin" );
           	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           	}
           }

           boolean bAdminRole=false;
           String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           if(! (aszRole==null || aszRole.equals(null))){
          	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            	bAdminRole=true;
          	 }else{
          		 aszRole="";
          	 }
           }
            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            allocatedOrgBRLO( httpServletRequest );

            int iNatlAssocNID = 0;
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
            
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
          		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
          	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else{
            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              int iTid = aOrgInfoObj.getORGAffiliation1TID();
              if(iTid > 0){
             	 aOrgInfoObj.setORGNID( aszOrgNid );
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
    	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
    	          	return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
                	return actionMapping.findForward( "404" );
            	}
            }
           aOrgInfoObj.appendErrorMsg(aszErr);
       	httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
           return actionMapping.findForward( "orgadminadd" );
       }
       // end-of method showAddOrgAdmin()
       
       /*
        * add new contact for organiztion
        */
        public ActionForward addNewOrgAdmin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	int iRetCode=0,iRetCode2=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
        	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        	if(iOrgNid < 1){
        		aszOrgNid = "" + aSessDat.getOrgNID();
            	if(iOrgNid < 1){
	   	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	   	             	return actionMapping.findForward( "showlogin" );
	   	         	}else{
	   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	   	         	}
            	}
        	}
        	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
        		if(null != aSessDat){
        			aSessDat.setOrgNID( aszOrgNid );
        			aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
        		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        	}
        	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        			return actionMapping.findForward( "showlogin" );
        		}else{
        			return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        		}
        	}
        	boolean bAdminRole=false;
        	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        	if(! (aszRole==null || aszRole.equals(null))){
        		if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        			bAdminRole=true;
        		}else{
        			aszRole="";
        		}
        	}
            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            allocatedOrgBRLO( httpServletRequest );

            int iNatlAssocNID = 0;
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
            

            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
          		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
          	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else{
            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              int iTid = aOrgInfoObj.getORGAffiliation1TID();
              if(iTid > 0){
             	 aOrgInfoObj.setORGNID( iOrgNid );
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
    	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
    	          	return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
                	return actionMapping.findForward( "404" );
            	}
            }
           if(0 != iRetCode){
        	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        		   return actionMapping.findForward( "showlogin" );
        	   }else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	   }
           }
           
           // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
           // load data for organization contact person
           PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
           String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email");
           aContactPersonObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email") );
           aContactPersonObj.setUSPEmail2Addr( aCurrentUserObj.getUSPEmail1Addr() ); // set the secondary email address as the current user (used in email)
           allocatedIndBRLO( httpServletRequest );
           iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_EMAIL, aszSiteVersion ); //(in reality, this is by email address)
           aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));

           if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
           	// the user was correctly loaded and exists
           		iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
        	   iRetCode = m_OrgBRLOObj.insertAdditionalOrgAdmin( aOrgInfoObj , aContactPersonObj ); // really only needs a uid to get inserted.
           }else{
	           	// the email address could not be found in our database
	           	String aszErr="The email address " + aszEmailAddress + " could not be found in our database.";
              	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
            	httpServletRequest.setAttribute("org", aOrgInfoObj);
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "orgadminadd" );
//            	return showAddOrgAdmin(actionMapping, actionForm, httpServletRequest, httpServletResponse);         	
           }

     	  String aszSubdomain = "";
     	  if(httpServletRequest.getParameter("subdomain") != null )
    		  aszSubdomain = httpServletRequest.getParameter("subdomain");
     	  else
     		  aszSubdomain = aOrgInfoObj.getORGSubdom();
     	  if(aszSubdomain.length()<1)
     		  aszSubdomain = aOrgInfoObj.getORGSubdom();
//           String aszEmailUseCase = "addedContact";
           int iEmailUseCase = iAddedAdmin;
           //aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
           //iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aszEmailUseCase ); //(in reality, this is by email address)
           aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
           aContactPersonObj.setUSPSubdom(aszSubdomain);//aContactPersonObj.getUSPSubdom());
           iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, iEmailUseCase,aszPortal ); //(in reality, this is by email address)

           session.setAttribute("orgmanagementnid", aszOrgNid);

        	httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           
           // contact/administrator list for organization
           allocatedOrgBRLO( httpServletRequest );
//           ArrayList aList = new ArrayList();
//           iRetCode = m_OrgBRLOObj.getOrgAdminList( aList, aOrgInfoObj.getORGNID() );
//           httpServletRequest.setAttribute( "userlist", aList );
        	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);

        	if(bAdminRole==true){
        		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
        	}

                      
              httpServletRequest.setAttribute( "org", aOrgInfoObj );

              // in the db layer, should also be referencing insertAdditionalOrgAdmin from loadChildOpps called above
              //	set/getORGChildOPPNIDsArray()
              iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj ); 

              iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );

              ArrayList aList = new ArrayList();
              iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
              httpServletRequest.setAttribute( "userlist", aList );

              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "orgadmincontactmanage" );
//           return actionMapping.findForward( "orgadminmanage" );
        }
        // end-of method addNewOrgAdmin()

        /*
     	 * add brand new administrator for organiztion
     	 */
        public ActionForward addBrandNewOrgAdmin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
        	int iRetCode=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
            if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
            	}
            }
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
         	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	String aszOrgNid = null;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
         	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
         	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
         	if(iOrgNid < 1){
         		aszOrgNid = "" + aSessDat.getOrgNID();
         		if(iOrgNid < 1){
         			if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
         				return actionMapping.findForward( "showlogin" );
         			}else{
         				return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         			}
         		}
         	}
         	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
         		if(null != aSessDat){
         			aSessDat.setOrgNID( aszOrgNid );
         			aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
         		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
         	}
         	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
         		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
         			return actionMapping.findForward( "showlogin" );
         		}else{
         			return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		}
         	}
         	
         	boolean bAdminRole=false;
        	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
        	if(! (aszRole==null || aszRole.equals(null))){
        		if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
        			bAdminRole=true;
        		}else{
        			aszRole="";
        		}
        	}
        	OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            allocatedOrgBRLO( httpServletRequest );

            int iNatlAssocNID = 0;
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
            
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
            	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() );	
            	iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else{
            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              int iTid = aOrgInfoObj.getORGAffiliation1TID();
              if(iTid > 0){
             	 aOrgInfoObj.setORGNID( iOrgNid );
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
            		m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
            		return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
            		return actionMapping.findForward( "404" );
            	}
            }
            if(0 != iRetCode){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            		return actionMapping.findForward( "showlogin" );
            	}else{
            		return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            	}
            }
            
            // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
            // load data for organization contact person
            PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
            //get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
            iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
            String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
            allocatedIndBRLO( httpServletRequest );
              
            // for the contact person, write the "init" field for the new user as the current user's email address
            aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
        	String mailkey = "newOrgUserAccnt";
            OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();

            iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, aszPortal, mailkey, aszSiteVersion);
 System.out.println("done with addNewUserServices; iRedCode is "+iRetCode);
 			if(iRetCode==-111){
            	  m_BaseHelp.setFormData(oFrm,"errormsg", aContactPersonObj.getErrorMsg() );
            	  httpServletRequest.setAttribute("org", aOrgInfoObj);
                  loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "orgadminadd" );
            	  
              }
              if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
            	  // the user was correctly loaded and exists
             		iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
            	  iRetCode = m_OrgBRLOObj.insertAdditionalOrgAdmin( aOrgInfoObj , aContactPersonObj ); // really only needs a uid to get inserted.
              }else{
            	  String aszErr;
            	  if(iRetCode == -1){
            		  aszErr="The email address already exists.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r";
            	  }else if(iRetCode == -111){
            		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user.")){
            			  // there was an error with the XML-RPC call - most likely a duplicate Username
            			  aszErr="There was an error on saving this user. \"" +
            			  	"\"Make sure the email address or username does not already exist in our system.";
            		  }else{
            			  // there was an error with the XML-RPC call
            			  aszErr="There was an error on saving this user: " + aContactPersonObj.getErrorMsg() ;
            		  }
            	  }else if(iRetCode == 333){
            		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user ")){
            			  // there was an error with the XML-RPC call - most likely a duplicate Username
            			  aszErr="There was an error on saving this user. " +
    		             		"Make sure the email address or username does not already exist in our system.";
            		  }else{
            			  // there was an error with the XML-RPC call
            			  aszErr="There was an error on saving this user: \"" +
    		             			aContactPersonObj.getErrorMsg() +
    		             			"\" (Make sure the email address or username does not already exist in our system.)";
            		  }
            	  }else if(iRetCode == 444){
            		  // there was an error with the XML-RPC call
            		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r" +
                  			"(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
            	  }else if(iRetCode == 555){
            		  // there was an error with the XML-RPC call
            		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section\n\r" +
                  			"(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
            	  }else{
            		  // the email address could not be found in our database
            		  aszErr="The email address " + aszEmailAddress + " could not be found in our database. Try creating a new account for this email below in the \"New Administrator\" section\n\r";
            		  if(aContactPersonObj.getErrorMsg().length()>5){
            			  aszErr=aszErr + "(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
            		  }
            	  }
            	  m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
            	  httpServletRequest.setAttribute("org", aOrgInfoObj);
                  loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "orgadminadd" );
//            	  return showAddOrgAdmin(actionMapping, actionForm, httpServletRequest, httpServletResponse);  
              }
              session.setAttribute("orgmanagementnid", aszOrgNid);
              httpServletRequest.setAttribute("org", aOrgInfoObj);
              m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
              
              // administrator list for organization
              allocatedOrgBRLO( httpServletRequest );
//              ArrayList aList = new ArrayList();
//              iRetCode = m_OrgBRLOObj.getOrgAdminList( aList, aOrgInfoObj.getORGNID() );
//              httpServletRequest.setAttribute( "userlist", aList );
              httpServletRequest.setAttribute("userprofile", aContactPersonObj);// not sure which user??
              
              if(bAdminRole==true){
            	  m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
              }
              ArrayList aList = new ArrayList();
              iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
              httpServletRequest.setAttribute( "userlist", aList );

                        
                httpServletRequest.setAttribute( "org", aOrgInfoObj );

                iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "orgadmincontactmanage" );
//              return actionMapping.findForward( "orgadminmanage" );
          }
          // end-of method addBrandNewOrgAdmin()
        
        /*
         * show organization delete admin page
         */
         public ActionForward showRemoveOrgAdmin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0;
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	String aszOrgNid=null;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
            	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            	if(iOrgNid < 1){
            		aszOrgNid = "" + aSessDat.getOrgNID();
                	if(iOrgNid < 1){
       	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
       	             	return actionMapping.findForward( "showlogin" );
       	         	}else{
       	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	         	}
                	}
            	}
               if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
     			  if(null != aSessDat){
     				  aSessDat.setOrgNID( aszOrgNid );
     				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
     			  }
     				if(aCurrentUserObj.getUserProfileNID() < 1){ 
     					// this user is an old drupal user-only; need to take through partial account creation process
     					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
     					//		as well as an insert into the rails db
     					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
     			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
     				}
     		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
     	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	         	return actionMapping.findForward("mappingpage");
     		 }
             if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "showlogin" );
             	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }
             boolean bAdminRole=false;
             String aszRole = m_BaseHelp.getFormData(oFrm,"role");
             if(! (aszRole==null || aszRole.equals(null))){
            	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
              	bAdminRole=true;
            	 }else{
            		 aszRole="";
            	 }
             }
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );

              int iNatlAssocNID = 0;
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
              
              // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
            	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else{
              if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//            	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                int iTid = aOrgInfoObj.getORGAffiliation1TID();
                if(iTid > 0){
               	 aOrgInfoObj.setORGNID( iOrgNid );
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                }
          	     }else{
          	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
          	     }
              }
              if(iRetCode == -111){
              	if(bAdminRole==true){
      	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
      	          	return actionMapping.findForward( "noaccess" );
              	}else{
              		// org did not exist
                  	return actionMapping.findForward( "404" );
              	}
              }
             httpServletRequest.setAttribute("org", aOrgInfoObj);
             m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
             
             // contact/administrator for organization
             PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
             aContactPersonObj.setUserUID( httpServletRequest.getParameter("uid") );
             allocatedIndBRLO( httpServletRequest );
             iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
             m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );
             httpServletRequest.setAttribute("userprofile", aContactPersonObj);


          	if(bAdminRole==true){
        		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
        	}

            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
             return actionMapping.findForward( "orgadminremove" );
         }
         // end-of method showRemoveOrgAdmin
         
     	/*
         * delete OrgAdmin
         */
         public ActionForward processRemoveOrgAdmin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	int iRetCode=0, iRetCode2=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
		if(aszPortalRequestType.equals("natlassoc")){
			bNatlAssoc=true;
		}
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	String aszOrgNid=null;
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
          	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
        	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
        	if(iOrgNid < 1){
        		aszOrgNid = "" + aSessDat.getOrgNID();
            	if(iOrgNid < 1){
   	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
   	             	return actionMapping.findForward( "showlogin" );
   	         	}else{
   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   	         	}
            	}
        	}
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
   			  if(null != aSessDat){
   				  aSessDat.setOrgNID( aszOrgNid );
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
   			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
   		 }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "showlogin" );
           	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           	}
           }
         	int iUID = aCurrentUserObj.getUserUID();
           String aszContactUID=  httpServletRequest.getParameter("uid") ;
           boolean bAdminRole=false;
           String aszRole = m_BaseHelp.getFormData(oFrm,"role");
           if(! (aszRole==null || aszRole.equals(null))){
          	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
            	bAdminRole=true;
          	 }else{
          		 aszRole="";
          	 }
           }
            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            allocatedOrgBRLO( httpServletRequest );

            int iNatlAssocNID = 0;
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
            
            // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
            if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
          		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
            ){
          	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
            }else{
            if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
             	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//          	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
              iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
              int iTid = aOrgInfoObj.getORGAffiliation1TID();
              if(iTid > 0){
             	 aOrgInfoObj.setORGNID( iOrgNid );
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
              }
        	     }else{
        	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
        	     }
            }
            if(iRetCode == -111){
            	if(bAdminRole==true){
    	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
    	          	return actionMapping.findForward( "noaccess" );
            	}else{
            		// org did not exist
                	return actionMapping.findForward( "404" );
            	}
            }
           httpServletRequest.setAttribute( "org", aOrgInfoObj );
           // make sure the user is not attempting to delete the sole contact/administrator of an organization
           ArrayList aAdminList = new ArrayList (2);
           m_OrgBRLOObj.getOrgAdminList( aAdminList, iOrgNid);
           // load data for organization contact person
        	int iContactUID = m_BaseHelp.convertToInt( aszContactUID );
          PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
          aContactPersonObj.setUserUID( iContactUID );
          allocatedIndBRLO( httpServletRequest );
          iRetCode2 = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
          aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));
          m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );
          httpServletRequest.setAttribute("userprofile", aContactPersonObj);
           if(aAdminList.size()<2){
           	aOrgInfoObj.appendErrorMsg("You must have more than one contact for your organization in order to remove a administrator.");
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "orgadminremove" );
           }
           

           if(-1 == iRetCode2){
           	aOrgInfoObj.appendErrorMsg("error loading organizational contact");
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "orgadminremove" );
           }
           // delete organizational contact
           iRetCode = m_OrgBRLOObj.deleteOrgAdmin( aContactPersonObj, aOrgInfoObj );
           if(0 != iRetCode){
           	aOrgInfoObj.appendErrorMsg("error removing organizational contact");
               m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
               loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
               return actionMapping.findForward( "orgadminremove" );
           }
           
     	  String aszSubdomain = "";
     	  if(httpServletRequest.getParameter("subdomain") != null )
    		  aszSubdomain = httpServletRequest.getParameter("subdomain");
     	  else
     		  aszSubdomain = aOrgInfoObj.getORGSubdom();
     	  if(aszSubdomain.length()<1)
     		  aszSubdomain = aOrgInfoObj.getORGSubdom();
           // trigger emails to notify current user and removed user of the removal
           int iEmailUseCase = iRemovedAdmin;
           aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
           aContactPersonObj.setUSPSubdom(aszSubdomain);//aContactPersonObj.getUSPSubdom());
           iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, iEmailUseCase,aszPortal ); //(in reality, this is by email address)

           allocatedIndBRLO( httpServletRequest );
           iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aCurrentUserObj );
           allocatedOrgBRLO( httpServletRequest );
           /*
           ArrayList aList = new ArrayList();
           iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aCurrentUserObj.getUserUID() );
           httpServletRequest.setAttribute( "orglist", aList );
           */

           // get individual data from web form 
           iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aCurrentUserObj);
           if(aCurrentUserObj.getUserUID() < 1){ // to catch the case where a user's uid might have been cleared to 0 b/c it's not in the form above
        	   aCurrentUserObj.setUserUID(iUID);
           }
           if(iRetCode != 0){
             	m_BaseHelp.setFormData(oFrm,"errormsg", aCurrentUserObj.getErrorMsg() );
             	return actionMapping.findForward( "createaccount1" );
           }
           iRetCode = m_IndBRLOObj.loadUserByOption( aCurrentUserObj ,aCurrentUserObj.LOADBY_UID, aszSiteVersion);
           iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aCurrentUserObj);
            
        	// if the CURRENT USER is the one who has been removed, then the user should just be forwarded to generic orgmanagement
        	if(aCurrentUserObj.getUserUID()==iContactUID){
        		return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}

        	session.setAttribute("orgmanagementnid", aszOrgNid);
        	httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
           
           // contact/administrator list for organization
           allocatedOrgBRLO( httpServletRequest );
//           ArrayList aList = new ArrayList();
//           iRetCode = m_OrgBRLOObj.getOrgAdminList( aList, aOrgInfoObj.getORGNID() );
//           httpServletRequest.setAttribute( "userlist", aList );
        	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);

        	if(bAdminRole==true){
        		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
        	}

            ArrayList aList = new ArrayList();
            iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
            httpServletRequest.setAttribute( "userlist", aList );

                      
              httpServletRequest.setAttribute( "org", aOrgInfoObj );

              iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
              iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "orgadmincontactmanage" );
//           return actionMapping.findForward( "orgadminmanage" );
         }
         // end-of method processRemoveOrgAdmin()
      //=== END OrgAdmins section
      //=== END OrgAdmins section
      //=== END OrgAdmins section
         /*
          * show add contact for organization view page (managing org)
          */
          public ActionForward showAddOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
          	int iRetCode=0;
           	getPortalInfo( httpServletRequest, httpServletResponse);
   		boolean bNatlAssoc = false;
   		if(aszPortalRequestType.equals("natlassoc")){
   			bNatlAssoc=true;
   		}
   		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
           if(aszPortal.length()>0){
           	if(aszPortalNID.length()==0){
           		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
       			return actionMapping.findForward("404");
           	}
           }
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
          	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	String aszErr = m_BaseHelp.getFormData(oFrm,"errormsg");
              String aszOrgNid = null;
              if(httpServletRequest.getParameter("orgnid") != null ){
                  aszOrgNid = httpServletRequest.getParameter("orgnid");
              }else{
              	if(null != aSessDat){
              		aszOrgNid = "" + aSessDat.getOrgNID();
              	}
              }
         	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
              if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
      			  if(null != aSessDat){
      				  aSessDat.setOrgNID( aszOrgNid );
      				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
      			  }
      			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
      		 }
              if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
              	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                  	return actionMapping.findForward( "showlogin" );
              	}else{
                      return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              	}
              }

              boolean bAdminRole=false;
              String aszRole = m_BaseHelp.getFormData(oFrm,"role");
              if(! (aszRole==null || aszRole.equals(null))){
             	 if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
               	bAdminRole=true;
             	 }else{
             		 aszRole="";
             	 }
              }
               OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
               aOrgInfoObj.setORGNID( aszOrgNid );
               aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
               allocatedOrgBRLO( httpServletRequest );

               int iNatlAssocNID = 0;
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
               
               // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
               if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
             		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
               ){
             	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                   iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
               }else{
               if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
                	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//             	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                 int iTid = aOrgInfoObj.getORGAffiliation1TID();
                 if(iTid > 0){
                	 aOrgInfoObj.setORGNID( aszOrgNid );
                     iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                 }
           	     }else{
           	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           	     }
               }
               if(iRetCode == -111){
               	if(bAdminRole==true){
       	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
       	          	return actionMapping.findForward( "noaccess" );
               	}else{
               		// org did not exist
                   	return actionMapping.findForward( "404" );
               	}
               }
              aOrgInfoObj.appendErrorMsg(aszErr);
          	httpServletRequest.setAttribute("org", aOrgInfoObj);
              m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
              loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
              return actionMapping.findForward( "orgcontactadd" );
          }
          // end-of method showAddOrgContact()
         /*
          * add new contact for organiztion
          */
          public ActionForward addNewOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
          	int iRetCode=0,iRetCode2=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
      		boolean bNatlAssoc = false;
      		if(aszPortalRequestType.equals("natlassoc")){
      			bNatlAssoc=true;
      		}
      		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
              		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
          			return actionMapping.findForward("404");
              	}
              }
          	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
         	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
          	String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
          	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          	if(iOrgNid < 1){
          		aszOrgNid = "" + aSessDat.getOrgNID();
              	if(iOrgNid < 1){
  	   	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
  	   	             	return actionMapping.findForward( "showlogin" );
  	   	         	}else{
  	   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	   	         	}
              	}
          	}
          	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
          		if(null != aSessDat){
          			aSessDat.setOrgNID( aszOrgNid );
          			aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
          		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
          	}
          	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          			return actionMapping.findForward( "showlogin" );
          		}else{
          			return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		}
          	}
          	boolean bAdminRole=false;
          	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
          	if(! (aszRole==null || aszRole.equals(null))){
          		if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
          			bAdminRole=true;
          		}else{
          			aszRole="";
          		}
          	}
              OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );

              int iNatlAssocNID = 0;
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
              

              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
            		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
            	  	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() ); 
                  iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else{
              if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//            	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                int iTid = aOrgInfoObj.getORGAffiliation1TID();
                if(iTid > 0){
               	 aOrgInfoObj.setORGNID( iOrgNid );
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                }
          	     }else{
          	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
          	     }
              }
              if(iRetCode == -111){
              	if(bAdminRole==true){
      	          	m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
      	          	return actionMapping.findForward( "noaccess" );
              	}else{
              		// org did not exist
                  	return actionMapping.findForward( "404" );
              	}
              }
             if(0 != iRetCode){
          	   if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          		   return actionMapping.findForward( "showlogin" );
          	   }else{
                     return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	   }
             }
             
             // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
             // load data for organization contact person
             PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
             String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email");
             aContactPersonObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email") );
             aContactPersonObj.setUSPEmail2Addr( aCurrentUserObj.getUSPEmail1Addr() ); // set the secondary email address as the current user (used in email)
             allocatedIndBRLO( httpServletRequest );
             iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_EMAIL, aszSiteVersion ); //(in reality, this is by email address)
             aContactPersonObj.setUSPSubdom(m_BaseHelp.getFormData(oFrm,"subdomain"));

             if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
             	// the user was correctly loaded and exists
             		iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
          	   iRetCode = m_OrgBRLOObj.insertAdditionalOrgContact( aOrgInfoObj , aContactPersonObj ); // really only needs a uid to get inserted.
             }else{
  	           	// the email address could not be found in our database
  	           	String aszErr="The email address " + aszEmailAddress + " could not be found in our database.";
                	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
              	httpServletRequest.setAttribute("org", aOrgInfoObj);
                  loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "orgcontactadd" );
             }
             if(iRetCode == -1){
   	           	// the email address could not be found in our database
   	           	String aszErr="There was an error adding the email address " + aszEmailAddress + " as an Organizational Contact.";
                 	m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
               	httpServletRequest.setAttribute("org", aOrgInfoObj);
                   loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                   return actionMapping.findForward( "orgcontactadd" );
             }
             String aszSubdomain = aOrgInfoObj.getORGSubdom();
             if(httpServletRequest.getParameter("subdomain") != null )
            	 if(httpServletRequest.getParameter("subdomain").length()>0 )
            		 aszSubdomain = httpServletRequest.getParameter("subdomain");
             int iEmailUseCase = iNowIsOrgContact;
             //aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
             //iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, aszEmailUseCase ); //(in reality, this is by email address)
             aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
             aContactPersonObj.setUSPSubdom(aszSubdomain);//aContactPersonObj.getUSPSubdom());
             
             
             // *************** might not trigger the email to notify about a contact for an organization....
             if (iRetCode==0){
//            	 iRetCode = m_IndBRLOObj.emailNotify( httpServletRequest, aContactPersonObj, aOrgInfoObj, iEmailUseCase,aszPortal ); //(in reality, this is by email address)
             }
             session.setAttribute("orgmanagementnid", aszOrgNid);

          	httpServletRequest.setAttribute("org", aOrgInfoObj);
             m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
             
             // contact/administrator list for organization
             allocatedOrgBRLO( httpServletRequest );
//             ArrayList aList = new ArrayList();
//             iRetCode = m_OrgBRLOObj.getOrgAdminList( aList, aOrgInfoObj.getORGNID() );
//             httpServletRequest.setAttribute( "userlist", aList );
          	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);

          	if(bAdminRole==true){
          		m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
          	}

                        
                httpServletRequest.setAttribute( "org", aOrgInfoObj );

                // in the db layer, should also be referencing insertAdditionalOrgAdmin from loadChildOpps called above
                //	set/getORGChildOPPNIDsArray()
                iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj ); 

                iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );

                ArrayList aList = new ArrayList();
                iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                httpServletRequest.setAttribute( "userlist", aList );

                loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                return actionMapping.findForward( "orgadmincontactmanage" );
//             return actionMapping.findForward( "orgadminmanage" );
          }
          // end-of method addNewOrgContact()

          /*
       	 * add brand new contact for organiztion
       	 */
          public ActionForward addBrandNewOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
          {
          	int iRetCode=0;
          	getPortalInfo( httpServletRequest, httpServletResponse);
      		boolean bNatlAssoc = false;
      		if(aszPortalRequestType.equals("natlassoc")){
      			bNatlAssoc=true;
      		}
      		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
              if(aszPortal.length()>0){
              	if(aszPortalNID.length()==0){
              		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
          			return actionMapping.findForward("404");
              	}
              }
           	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
           	DynaActionForm oFrm = (DynaActionForm)actionForm;
           	String aszOrgNid = null;
         	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
           	aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
           	int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
           	if(iOrgNid < 1){
           		aszOrgNid = "" + aSessDat.getOrgNID();
           		if(iOrgNid < 1){
           			if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
           				return actionMapping.findForward( "showlogin" );
           			}else{
           				return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           			}
           		}
           	}
           	if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
           		if(null != aSessDat){
           			aSessDat.setOrgNID( aszOrgNid );
           			aSessDat.setTokenKey( AppSessionDTO.TOKEN_SHOWCONTACTS );
           		}
    			if(aCurrentUserObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
           	}
           	if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
           		if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
           			return actionMapping.findForward( "showlogin" );
           		}else{
           			return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           		}
           	}
           	
           	boolean bAdminRole=false;
          	String aszRole = m_BaseHelp.getFormData(oFrm,"role");
          	if(! (aszRole==null || aszRole.equals(null))){
          		if(aszRole.equals(PersonInfoDTO.AUTH_ADMIN)){
          			bAdminRole=true;
          		}else{
          			aszRole="";
          		}
          	}
          	OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
              aOrgInfoObj.setORGNID( aszOrgNid );
              aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
              allocatedOrgBRLO( httpServletRequest );

              int iNatlAssocNID = 0;
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
              
              // if the user is accessing as site admin, check that current user MUST HAVE site administrative rights
              if(	aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)	&&
              		aszRole.equals(PersonInfoDTO.AUTH_ADMIN)
              ){
              	aOrgInfoObj.setSiteAdministratorUID( aCurrentUserObj.getUserUID() );	
              	iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_SITEADMIN, aszSiteVersion );
              }else{
              if(b_AssocManager==true){//aCurrentUserObj.getNatlAssociationNID()>0 && aCurrentUserObj.getNatlAssociationNID()==iNatlAssocNID){
               	 aOrgInfoObj.setNatlAssociationNID(iNatlAssocNID);
//            	 aOrgInfoObj.setNatlAssociationNID(aCurrentUserObj.getNatlAssociationNID());
                iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC, aszSiteVersion);//LOADBY_NATL_ASSOC );
                int iTid = aOrgInfoObj.getORGAffiliation1TID();
                if(iTid > 0){
               	 aOrgInfoObj.setORGNID( iOrgNid );
                    iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL, aszSiteVersion );
                }
          	     }else{
          	         iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
          	     }
              }
              if(iRetCode == -111){
              	if(bAdminRole==true){
              		m_BaseHelp.setFormData(oFrm,"errormsg", "you are not authorized to manage this organization,\r\nor the organization does not exist" );
              		return actionMapping.findForward( "noaccess" );
              	}else{
              		// org did not exist
              		return actionMapping.findForward( "404" );
              	}
              }
              if(0 != iRetCode){
              	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              		return actionMapping.findForward( "showlogin" );
              	}else{
              		return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
              	}
              }
              
              // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
              // load data for organization contact person
              PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
              //get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
              iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
              String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
              allocatedIndBRLO( httpServletRequest );
                
              // for the contact person, write the "init" field for the new user as the current user's email address
              aContactPersonObj.setUSPEmail2Addr(aCurrentUserObj.getUSPEmail1Addr());
          	String mailkey = "newOrgContactUserAccnt";
              OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();

              iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, aszPortal, mailkey, aszSiteVersion);
                if(iRetCode==-111){
              	  m_BaseHelp.setFormData(oFrm,"errormsg", aContactPersonObj.getErrorMsg() );
              	  httpServletRequest.setAttribute("org", aOrgInfoObj);
                    loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                    return actionMapping.findForward( "orgcontactadd" );
              	  
                }
                if(iRetCode == 0 || iRetCode == -222 || iRetCode == -555){
              	  // the user was correctly loaded and exists
               		iRetCode = m_OrgBRLOObj.loadChildOpps( aOrgInfoObj );
              	  iRetCode = m_OrgBRLOObj.insertAdditionalOrgContact( aOrgInfoObj , aContactPersonObj ); // really only needs a uid to get inserted.
                }else{
              	  String aszErr;
              	  if(iRetCode == -1){
              		  aszErr="The email address already exists.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r";
              	  }else if(iRetCode == -111){
              		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user.")){
              			  // there was an error with the XML-RPC call - most likely a duplicate Username
              			  aszErr="There was an error on saving this user. \"" +
              			  	"\"Make sure the email address or username does not already exist in our system.";
              		  }else{
              			  // there was an error with the XML-RPC call
              			  aszErr="There was an error on saving this user: " + aContactPersonObj.getErrorMsg() ;
              		  }
              	  }else if(iRetCode == 333){
              		  if(aContactPersonObj.getErrorMsg().equalsIgnoreCase("Error on saving the user ")){
              			  // there was an error with the XML-RPC call - most likely a duplicate Username
              			  aszErr="There was an error on saving this user. " +
      		             		"Make sure the email address or username does not already exist in our system.";
              		  }else{
              			  // there was an error with the XML-RPC call
              			  aszErr="There was an error on saving this user: \"" +
      		             			aContactPersonObj.getErrorMsg() +
      		             			"\" (Make sure the email address or username does not already exist in our system.)";
              		  }
              	  }else if(iRetCode == 444){
              		  // there was an error with the XML-RPC call
              		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section.\n\r" +
                    			"(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
              	  }else if(iRetCode == 555){
              		  // there was an error with the XML-RPC call
              		  aszErr="The user was saved to our system, but we did not catch all the information.  Please try adding the user again as an administrator through the \"Existing User\" section\n\r" +
                    			"(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
              	  }else{
              		  // the email address could not be found in our database
              		  aszErr="The email address " + aszEmailAddress + " could not be found in our database. Try creating a new account for this email below in the \"New Administrator\" section\n\r";
              		  if(aContactPersonObj.getErrorMsg().length()>5){
              			  aszErr=aszErr + "(The error reported was: " + aContactPersonObj.getErrorMsg() + ")";
              		  }
              	  }
              	  m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
              	  httpServletRequest.setAttribute("org", aOrgInfoObj);
                    loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                    return actionMapping.findForward( "orgcontactadd" );
//              	  return showAddOrgAdmin(actionMapping, actionForm, httpServletRequest, httpServletResponse);  
                }
                if(iRetCode!=0){
                	  m_BaseHelp.setFormData(oFrm,"errormsg", "There was an error adding this user as an Organizational Contact" );
                  	  httpServletRequest.setAttribute("org", aOrgInfoObj);
                        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                        return actionMapping.findForward( "orgcontactadd" );
                }
                session.setAttribute("orgmanagementnid", aszOrgNid);
                httpServletRequest.setAttribute("org", aOrgInfoObj);
                m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
                
                // administrator list for organization
                allocatedOrgBRLO( httpServletRequest );
                httpServletRequest.setAttribute("userprofile", aContactPersonObj);// not sure which user??
                
                if(bAdminRole==true){
              	  m_BaseHelp.setFormData(oFrm,"role", PersonInfoDTO.AUTH_ADMIN );
                }
                ArrayList aList = new ArrayList();
                iRetCode = m_OrgBRLOObj.getOrgContactList( aList, aOrgInfoObj.getORGNID(),"org" );
                httpServletRequest.setAttribute( "userlist", aList );

                          
                  httpServletRequest.setAttribute( "org", aOrgInfoObj );

                  iRetCode = m_OrgBRLOObj.setOrgAdminListArray( aOrgInfoObj );
                  iRetCode = m_OrgBRLOObj.setOrgContactListArray( aOrgInfoObj );
                  loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
                  return actionMapping.findForward( "orgadmincontactmanage" );
//                return actionMapping.findForward( "orgadminmanage" );
            }
            // end-of method addBrandNewOrgContact()
          

      
      //=== BEGIN Legacy OrgContacts section
      //=== BEGIN Legacy OrgContacts section
      //=== BEGIN Legacy OrgContacts section
        

      //=== END Legacy OrgContacts section
      //=== END Legacy OrgContacts section
      //=== END Legacy OrgContacts section

 /*
  * legacy methods
  */   

      /*
      * show organization view page 1 LEGACY still want to load, b/c i want the actual URL to change to the new format in the user's address bar
      */
      public ActionForward shownonpstep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
        	getPortalInfo( httpServletRequest, httpServletResponse);
    		boolean bNatlAssoc = false;
    		if(aszPortalRequestType.equals("natlassoc")){
    			bNatlAssoc=true;
    		}
	      // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
	      if(aszPortal.length()>0){
	    	  if(aszPortalNID.length()==0){
	        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
	    			return actionMapping.findForward("404");
	    	  }
	      }
	      int iRetCode=0, iRetCode2=0;
	      DynaActionForm oFrm = (DynaActionForm)actionForm;
          String aszOrgNumber = httpServletRequest.getParameter("orgnumber");
          OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
          aOrgInfoObj.setORGOrgNumber( aszOrgNumber );
          aOrgInfoObj.setORGurlID( aszOrgNumber );
          allocatedOrgBRLO( httpServletRequest );
          iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGURL_ID_W_ALIAS, aszSiteVersion );//LOADBY_ORGNUMBER_PUBLIC );
          iRetCode2=iRetCode; // iRetCode2 will be used to determine whether to load no results found or not for the published organizations
          httpServletRequest.setAttribute("org", aOrgInfoObj);
          m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );
/*
          // load data for organization contact person
          PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
          //aContactPersonObj.setUSPPersonNumber( aOrgInfoObj.getORGPrimaryPerson() );
          aContactPersonObj.setUserUID( aOrgInfoObj.getORGUID() );
          allocatedIndBRLO( httpServletRequest );
          iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID );
          m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );
*/
          if(-111 == iRetCode2){ // organization did not exist, at least publicly
          	return actionMapping.findForward( "404" );
          }
          return actionMapping.findForward( "viewnonpstep21" );
      }
      // end-of method shownonpstep1()

      
      //show opportunity public listing with legacy id's
       public ActionForward showopp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
        	getPortalInfo( httpServletRequest, httpServletResponse);
         	// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        	if(aszPortal.length()>0){
        		if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			return actionMapping.findForward("404");
        		}
        	}
        	int iRetCode=0;
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        	allocatedOrgBRLO( httpServletRequest );
  	        String aszOppNumber =  m_BaseHelp.getFormData(oFrm,"oppnumber"); // for any old references indexed by google
  	     	int iOppURLID = m_BaseHelp.convertToInt( aszOppNumber );
  	     	iRetCode = m_OrgBRLOObj.loadOpportunityByURLID( aOpportObj, iOppURLID, 1, aszSiteVersion ); //pass 1 as the last paramater b/c we only want public listings
  	     	if(-111 == iRetCode){ // opportunity did not exist, at least publicly
  	  	     	iRetCode = m_OrgBRLOObj.loadOpportunityByURLID( aOpportObj, iOppURLID, 2, aszSiteVersion ); //pass 1 as the last paramater b/c want to try by nid now
  	  	     	if(-111 == iRetCode){ // opportunity did not exist, at least publicly
  	  	     		return actionMapping.findForward( "404" );
  	  	     	}
  	     	}
  	     	m_OrgActHelp.putOppDataIntoForm( oFrm, aOpportObj );
        	httpServletRequest.setAttribute( "opp", aOpportObj );
  	     	return actionMapping.findForward( "nonpviewlisting1" );
       }
       // end-of method showOpportunity()
      
      /*
       * end legacy
       */
      
      
      //=== START admin section ==>
      
  	/*
       * show manage organization page
       */
       public ActionForward showAdminOrgManagement(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   int iRetCode=0;
        	getPortalInfo( httpServletRequest, httpServletResponse);
          if(aszPortal.length()>0){
          	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
          	}
          }
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
        	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
       	  getLoggedInStatus(httpServletRequest, httpServletResponse);
       	  if(aszLoggedInStatus.equals("showlogin")){
       		  return actionMapping.findForward( "showlogin" );
       	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
       		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
       	  }
           String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
           int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
           if(iOrgNid < 1){
        		aszOrgNid = "" + aSessDat.getOrgNID();
           }

	   		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
	   			  if(null != aSessDat){
	   				  aSessDat.setOrgNID( aszOrgNid );
	   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
	   			  }
	  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
					// this user is an old drupal user-only; need to take through partial account creation process
					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
					//		as well as an insert into the rails db
					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
				}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	         	return actionMapping.findForward("mappingpage");
	   		 }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
	           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	               	return actionMapping.findForward( "showlogin" );
	           	}else{
	                  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	           	}
           }
           
           if(aCurrentUserObj.getUSPAuthTokens().equalsIgnoreCase(PersonInfoDTO.AUTH_ADMIN)){
	           // Organization list for user
	           allocatedOrgBRLO( httpServletRequest );
	           ArrayList aList = new ArrayList();
	           //iRetCode = m_OrgBRLOObj.getOrgListForMemebr( aList, aCurrentUserObj.getUserUID() );
	           iRetCode = m_OrgBRLOObj.getOrgListAdmin( aList, iOrgNid );
	           httpServletRequest.setAttribute( "orglist", aList );
	           
	           allocatedOrgBRLO( httpServletRequest );
	           ArrayList aOppList = new  ArrayList ( 2 );
	           //iRetCode = m_OrgBRLOObj.getOppListForMember( aOppList, aCurrentUserObj.getUserUID() );
	 	           iRetCode = m_OrgBRLOObj.getOppListSiteAdmin( aOppList, iOrgNid );
	           httpServletRequest.setAttribute( "opplist", aOppList );
	
	           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
	           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	           String orgAttribute="org";
	
	           //String aszOrgNid = httpServletRequest.getParameter("orgnid");
	           //httpServletRequest.setAttribute("organization", aOrgInfoObj);
	           session.setAttribute("orgmanagementnid", aszOrgNid);
	
	           if(! (aszOrgNid == null) ){
	               if( aszOrgNid.length() > 1 ){
	  	             OrganizationInfoDTO aOrgInfoObject = new OrganizationInfoDTO();
	  	             aOrgInfoObject.setORGNID(aszOrgNid);
	  	             aOrgInfoObject.setORGUID(aCurrentUserObj.getUserUID());
	  	             iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObject, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	  	             //httpServletRequest.removeAttribute("organization");
	  	             //httpServletRequest.setAttribute("organization", aOrgInfoObject);
	  	             session.setAttribute("orgmanagementnid", aszOrgNid);
	
	               }
	           }
	       	if(null != aSessDat){
	          	aSessDat.setTokenKey(null);
	          	aSessDat.setOrgNID(null);
	          	aSessDat.setOppNID(null);
	          	aSessDat.setSubdomain(null);
	          	aSessDat.setSiteEmail(null);
	      	}
	        loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
	           return actionMapping.findForward( "adminorgmanagement" );
           }else{
        	   return actionMapping.findForward( "notauthorized" );
           }
       }
       // end-of method showAdminOrgManagement()


       /*
       * show organization edit page 1
       */
       public ActionForward showAdminnonpeditstep1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	int iRetCode=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
       	}
       }
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   	  getLoggedInStatus(httpServletRequest, httpServletResponse);
   	  if(aszLoggedInStatus.equals("showlogin")){
   		  return actionMapping.findForward( "showlogin" );
   	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
   		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   	  }
           String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
           int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
           if(iOrgNid < 1){
        		aszOrgNid = "" + aSessDat.getOrgNID();
            	if(iOrgNid < 1){
   	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
   	             	return actionMapping.findForward( "showlogin" );
   	         	}else{
   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
   	         	}
            	}
           }
   		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
   			  if(null != aSessDat){
   				  aSessDat.setOrgNID( aszOrgNid );
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
   			  }
  			if(aCurrentUserObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
   		 }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
           	//aSessDat.setOrgNID(aszOrgNid);
           	//aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "showlogin" );
           	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            		//return actionMapping.findForward( "loginstatus" );
           	}
           }
           OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
           aOrgInfoObj.setORGNID( aszOrgNid );
           aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
           allocatedOrgBRLO( httpServletRequest );
           iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
           if(-111 == iRetCode){ // opportunity did not exist, at least publicly
           	return actionMapping.findForward( "404" );
       	} 
       	httpServletRequest.setAttribute("org", aOrgInfoObj);
           m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );

           // load data for organization contact person
           PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
           aContactPersonObj.setUserUID( aOrgInfoObj.getORGUID() );
           allocatedIndBRLO( httpServletRequest );
           iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
           m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );

           String aszReqType = httpServletRequest.getParameter("reqtype");
           if(null == aszReqType) aszReqType="";
           if(aszReqType.equalsIgnoreCase("edit")){
           	if(null != aSessDat){
               	aSessDat.setTokenKey(null);
               	aSessDat.setOrgNID(null);
               	aSessDat.setOppNID(null);
               	aSessDat.setSubdomain(null);
               	aSessDat.setSiteEmail(null);
           	}
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
               return actionMapping.findForward( "editnonpstep1" );
           }
           
       	if(null != aSessDat){
           	aSessDat.setTokenKey(null);
           	aSessDat.setOrgNID(null);
           	aSessDat.setOppNID(null);
           	aSessDat.setSubdomain(null);
           	aSessDat.setSiteEmail(null);
       	}
           return shownonpeditstep2(actionMapping, actionForm, httpServletRequest, httpServletResponse);    
       }
       // end-of method showAdminnonpeditstep1(
       

       
       /*
        * show organization edit page 2 - details - 2006-10-11 - ali
        */
        public ActionForward showAdminnonpeditstep2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
        {
       	 int iRetCode=0, iRetCode2=0;
      	getPortalInfo( httpServletRequest, httpServletResponse);
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
       	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	 DynaActionForm oFrm = (DynaActionForm)actionForm;
        	  getLoggedInStatus(httpServletRequest, httpServletResponse);
         	  if(aszLoggedInStatus.equals("showlogin")){
         		  return actionMapping.findForward( "showlogin" );
         	  }else if(aszLoggedInStatus.equals("processCookieLogin")){
         		  return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	  }
            String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
            int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
            if(iOrgNid < 1){
         		aszOrgNid = "" + aSessDat.getOrgNID();
             	if(iOrgNid < 1){
    	         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
    	             	return actionMapping.findForward( "showlogin" );
    	         	}else{
   	                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	         	}
             	}
            }
    		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    			  if(null != aSessDat){
    				  aSessDat.setOrgNID( aszOrgNid );
    				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITORG );
    			  }
    				if(aCurrentUserObj.getUserProfileNID() < 1){ 
    					// this user is an old drupal user-only; need to take through partial account creation process
    					// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    					//		as well as an insert into the rails db
    					// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    			        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aCurrentUserObj, aszSiteVersion );
    				}
    		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	    		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
    	         	return actionMapping.findForward("mappingpage");
    		 }
            if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
            	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "showlogin" );
           	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
            		//return actionMapping.findForward( "loginstatus" );
           	}
            }

            OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
            aOrgInfoObj.setORGNID( aszOrgNid );
            aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
            allocatedOrgBRLO( httpServletRequest );
            iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
            if(-111 == iRetCode){ // opportunity did not exist, at least publicly
            	return actionMapping.findForward( "404" );
        	} 
            httpServletRequest.setAttribute("org", aOrgInfoObj);
            m_OrgActHelp.fillOrgDataEditForm( oFrm, aOrgInfoObj );

            OrganizationDetailsInfoDTO aOrgDetInfoObj = new OrganizationDetailsInfoDTO();
            aOrgDetInfoObj.setDETOrgNID( aszOrgNid );
            httpServletRequest.setAttribute("det", aOrgDetInfoObj);
            iRetCode2 = m_OrgBRLOObj.loadOrganizationDetFromDB( aOrgDetInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
            
            // if the org_details is not created yet for this organization, go to create
            if(0 != iRetCode2){
                return actionMapping.findForward( "createnonpstep3" );
            }
            
            // if the number served or number of volunteers = 0, set equal to what was
            // entered in the normal org profile
            if(0 == aOrgDetInfoObj.getDETNumServed()){
                aOrgDetInfoObj.setDETNumServed(aOrgInfoObj.getORGNumServed());        	 
            }
            if(0 == aOrgDetInfoObj.getDETNumVolOrg()){
           	 aOrgDetInfoObj.setDETNumVolOrg(aOrgInfoObj.getORGNumVolOrg());
            }
            m_OrgActHelp.fillOrgDetailDataEditForm( oFrm, aOrgDetInfoObj );

            // load data for organization contact person
            PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
            aContactPersonObj.setUserUID( aOrgInfoObj.getORGUID() );
            allocatedIndBRLO( httpServletRequest );
            iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
            m_OrgActHelp.fillOrgContactDataIntoForm( oFrm, aContactPersonObj );
            
            String aszReqType = httpServletRequest.getParameter("reqtype");
            if(null == aszReqType) aszReqType="";
            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
            return actionMapping.findForward( "editnonpstep2" );
        }
        // end-of method showAdminnonpeditstep2() - org details   
      
 

// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
// 	 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         		
     /**
     * process cookie login
     */
     public ActionForward processCookieLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
System.out.println("processCookieLogin called");    	 
     	int iRetCode=0, iRetCode2=0 ;
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	getPortalInfo( httpServletRequest, httpServletResponse);
       if(aszPortal.length()>0){
       	if(aszPortalNID.length()==0){
    		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
			return actionMapping.findForward("404");
       	}
       }
 		String aszIPAddress = httpServletRequest.getRemoteAddr();

         allocatedIndBRLO( httpServletRequest );
      	
         // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
         // so we'll just forward along to the login page
         boolean b_mobile=false;
         String temp=httpServletRequest.getHeader("host");
         if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
  			httpServletRequest.getHeader("host").contains("http://m.chrisvol.org")	||
  			httpServletRequest.getHeader("host").contains("http://m.chrisvol.org")	||
         	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
         	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
         	httpServletRequest.getHeader("host").contains("m.ivol.org")	
         ){
             b_mobile=true;
         }
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
System.out.println(" chrisvolAuth cookie found");	         		
	//         		int itmp = PersonInfoDTO.COOKIE_USER;
	//         		iRetCode = m_IndBRLOObj.validateSignatureCookie( authCookieValue, aszIPAddress, itmp );
	//     			int indexForUID = authCookieValue.lastIndexOf(".");
	//     	 		String aszUID = authCookieValue.substring(indexForUID+1);
	//     	 		aIndivObj.setUserUID(aszUID);
	     	 		aIndivObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	     	 		aIndivObj.setSessionIP(aszIPAddress);
	     	 		aIndivObj.setSessionValue(authCookieValue);
	     	 		// does this session id exist in the sessions table in the db? and with the given IP address?
	     	 		iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aIndivObj );
	         		if( iRetCode == 0 ){
	         			// login the user
	         	        iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
	         	        if( (iRetCode != 0) && (iRetCode != -222) ){
	         	          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
	                    	if(b_mobile==true){
	                          	return actionMapping.findForward( "showlogin" );
	                       	}else{
	                       		return actionMapping.findForward( "loginstatus" );
	                       	}
	         	        }
	         	        iRetCode2=iRetCode;
	         	        // test if this is a full user or not; could have signed up through FB app, or create account process and still be partial
				     	AppSessionDTO aSessDat=null;
				       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
				        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
	         	        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
	         	            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
	         	        }else{
	         	            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
	         	        }
	         	        int iRetCode3=iRetCode;
	         	        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	         	        	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
	         	        	if(session.getAttribute("FB_User_ID")!=null){
	         	       		 if(session.getAttribute("FB_User_ID").toString().length()>1){
	         	       			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
	         	       		 }
	         	             if(session.getAttribute("facebookapikey")==null ){
	         	          		aIndivObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	         	             }else if (session.getAttribute("facebooksecretkey")==null){
	         	          		aIndivObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	         	             }else{
	         	             	String aszFBapikey = session.getAttribute("facebookapikey").toString();
	         	             	if(aszFBapikey.length()>1){
	         	             		aIndivObj.setFBapikey(aszFBapikey);
	         	             	}else{
	         	             		aIndivObj.appendErrorMsg(" \nNo facebook api key was passed \n");
	         	             	}
	         	             	String aszFBsecretkey = session.getAttribute("facebooksecretkey").toString();
	         	             	if(aszFBsecretkey.length()>1){
	         	             		aIndivObj.setFBsecretkey(aszFBsecretkey);
	         	             	}else{
	         	             		aIndivObj.appendErrorMsg(" \nNo facebook secret key was passed \n");
	         	             	}
	         	             	boolean validateFBapikey = false;
	         	             	if(aszFBapikey.length()>0 || aszFBsecretkey.length()>0){
	         	             		validateFBapikey=m_IndBRLOObj.validateFBapikey(aszFBapikey, aszFBsecretkey, aIndivObj);
	         	             		if(validateFBapikey==false){
	         	             			aIndivObj.appendErrorMsg("'"+aIndivObj.getFBapikey()+"' is an invalid facebook application api key. ");
	         	                    	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
	         	             			return actionMapping.findForward( "personalityministryarea2" );
	         	             		}
	         	             	}else{
	         	             		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
	         	                	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
	         	             		return actionMapping.findForward( "personalityministryarea2" );
	         	             	}
	         	             }
	         	       	 	}
	         	        	if(iRetCode2!=-222){
	         	                iRetCode = m_IndBRLOObj.updateIndividualHead( aIndivObj, aszSiteVersion );
	         	            }
	         	        }
	         	        
	         	        aIndivObj.processTokens();
	         	    	if(aIndivObj.getProvider().length()>0){
	         	    		session.setAttribute("socialize", aIndivObj.getProvider());
	         	    	}
	         	        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
	         		        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
System.out.println(" line 11148 - currentuser set");	         		        
	         		        allocatedOrgBRLO( httpServletRequest );
	          		    	if(null != aSessDat){
	         		        	int iOppNID = aSessDat.getOppNID();
	         		        	int iOrgNID = aSessDat.getOrgNID();
	         		        	String aszToken = aSessDat.getTokenKey();
	         		        	aSessDat.setTokenKey(null);
	         		        	if( 
	         		        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IWANTTOHELP ) && iOppNID > 0) ||
	         		        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN ) && iOppNID > 0) ||
	         		        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELPSIGNIN ) && iOppNID > 0)
	         		        		){
	         	                	return processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEORG ) ) {
	         		        		return showCreateOrgStep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_SHOWCONTACTS ) && iOrgNID > 0 ){
	         		        		return showOrgContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITORG ) && iOrgNID > 0 ){
	         		        		return shownonpeditstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEOPPORT ) && iOrgNID > 0 ){
	         		        		return showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWORG ) && iOrgNID > 0 ){
	         		        		return shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGMANAGEMENT ) ){
	         		        		return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWOPP ) && iOrgNID > 0 && iOppNID > 0){
	         		        		return showOpportunityPreview(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	         		        	}
	         	        	}
	         	    	}
	         	        
	         	        if(iRetCode2 != -222){
	         	        	// if the user is coming through facebook, we will still have their information, but we need to ask the user for more information and forward them to drupalaccount page
	         		        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	         		        	session.setAttribute("usecase", "volunteer");
	         		        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	         		        	if(aIndivObj.getUSPPersonality() == ""){
	         		               loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj);
	         		        		return actionMapping.findForward( "myministryopps" );
	         		        	}
	         		        	else {
	         		        		return actionMapping.findForward( "personalityresults" );
	         		        	}
	         		        }
	         	        }
	         	        
	         	        ArrayList aList = new ArrayList();
	         	        if(iRetCode2 != -222){
	         		        iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aIndivObj.getUserUID()); 
	         		        httpServletRequest.setAttribute( "orglist", aList );
	         	        }
	
	         	        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
	         	        //if the user has an account in drupal, but we do not have all the fields that are required for voleng
	         	        //if(iRetCode2 == -222){
	         	        if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL ){	
	         	          	m_BaseHelp.setFormData(oFrm,"username", aIndivObj.getUSPUsername() );
	         	          	m_BaseHelp.setFormData(oFrm,"email1addr", aIndivObj.getUSPEmail1Addr() );
	         	          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
	         	          	m_BaseHelp.setFormData(oFrm,"password1", aIndivObj.getUSPPassword() );
	         	          	m_BaseHelp.setFormData(oFrm,"password2", aIndivObj.getUSPPassword() );
	         	          	m_BaseHelp.setFormData(oFrm,"internalusertypecomment", aIndivObj.getUSPInternalUserTypeComment() );
	         	        	m_BaseHelp.setFormData(oFrm,"upnid", "" + aIndivObj.getUserProfileNID() );
	         	        	m_BaseHelp.setFormData(oFrm,"upvid", "" + aIndivObj.getUserProfileVID() );
	         	        	m_BaseHelp.setFormData(oFrm,"uplid", "" + aIndivObj.getUserProfileLID() );
	         	        	m_BaseHelp.setFormData(oFrm,"uid", "" + aIndivObj.getUserUID() );
	         	         	m_BaseHelp.setFormData(oFrm,"internalcomment", "drupal"  );
	
	         	        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	         	            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
                	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
                	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                         	return actionMapping.findForward("mappingpage");
                    //                       	return actionMapping.findForward( "createaccount2" );
	         	       }
	         	        
	         	        session.setAttribute("usecase", "organization");
	         	        //httpServletRequest.setAttribute("organization", aOrgInfoObj);
	         	        String aszOrgNid="";
	         	        session.setAttribute("orgmanagementnid", aszOrgNid);
	         	        return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
	         		}else{
	//               	return actionMapping.findForward( "loginstatus" );
	         		}
	         	} 
	         }
     	}
         
     	if(b_mobile==true){
          	return actionMapping.findForward( "showlogin" );
       	}else{
System.out.println(" returns loginstatus ");       		
       		return actionMapping.findForward( "loginstatus" );
       	}
     }
     // end-of method processLogin()

     public int setChrisVolAuthCookieOn(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,PersonInfoDTO aIndivObj, String aszUseCase){
 	   		String aszIPAddress = httpServletRequest.getRemoteAddr();
 			String aszDomain = "";
 			String aszDom = httpServletRequest.getRequestURI();
 			aszDomain = m_IndBRLOObj.getDomainName( httpServletRequest );

 			int now = m_IndBRLOObj.getUnixTimestampNow();
 			int expireTime = 24 * 60 * 60 * 30;//30 days in seconds
 			//expireTime += now;
 	        if(aIndivObj.getAuthPass().equals( PersonInfoDTO.AUTH_ADMIN_PWD )){
 	        	expireTime = 20 * 60;//20 minutes in seconds
 	        }
 			String cookieValue = "";
 	        allocatedIndBRLO( httpServletRequest );
 			cookieValue = m_IndBRLOObj.createSignature(aIndivObj.getUserUID(), aIndivObj.getUserUIDString(), aszIPAddress, PersonInfoDTO.COOKIE_USER);
 			int iRetCode=0,iRetCodeCookie=0;
 			cookieValue = "";
 			aIndivObj.setSessionIP(aszIPAddress);
 			iRetCodeCookie = m_IndBRLOObj.setSessionValue(aIndivObj);
 			if(iRetCodeCookie == 0){
 				cookieValue = aIndivObj.getSessionValue();
 			}
 			Cookie cookie = new Cookie ("chrisvolAuth",cookieValue);
 			cookie.setDomain(aszDomain);
 			cookie.setMaxAge(expireTime);
 			if(aszUseCase.equalsIgnoreCase("login")){
 		        // test if this is a full user or not; could have signed up through FB app, or create account process and still be partial
		     	AppSessionDTO aSessDat=null;
		       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
		        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
 		        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
 		            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
 		        }else{
 		            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
 		            if(iRetCodeCookie == 0){
 		            	httpServletResponse.addCookie(cookie);
 		            	aIndivObj.setCookieAuthorize( PersonInfoDTO.COOKIE_USER );
 		            	iRetCodeCookie = m_IndBRLOObj.setSessionIDInSystem(aIndivObj);
 		            }
 		        }
 		        return iRetCode;
 			}else{

 					if(iRetCodeCookie == 0){
 						httpServletResponse.addCookie(cookie);
 						aIndivObj.setCookieAuthorize( PersonInfoDTO.COOKIE_USER );
 						iRetCodeCookie = m_IndBRLOObj.setSessionIDInSystem(aIndivObj);
 					}
 			}
 			return 0;
     }
     
 	/*
 	* show home page
 	*/
 	public ActionForward showHome(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	getPortalInfo( httpServletRequest, httpServletResponse);
     	String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
     	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
     	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
     	if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
     	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
         // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
 		if(aszPortal.length()>0){
         	if(aszPortalNID.length()==0){
         		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
     			return actionMapping.findForward("404");
         	}
         }
 		
         SearchParms aSParm = new SearchParms();
         httpServletRequest.setAttribute( "sparm", aSParm );
         allocatedOppSrchBRLO( httpServletRequest );
         ArrayList aList = new ArrayList(2);
         httpServletRequest.setAttribute( "alist", aList );
         ArrayList facetList = new ArrayList(2);
         httpServletRequest.setAttribute( "facetlist", facetList );

 		String content_type = "content_type:opportunity";
 		String aszLandingPage = "";
 		int iRetCode = -1;
         if(httpServletRequest.getParameter("landingpage") != null){
 			if(httpServletRequest.getParameter("landingpage").length() > 0){
 				iRetCode = 0;
 				aszLandingPage = httpServletRequest.getParameter("landingpage");
 			}
         }
         if(aszLandingPage.length()<1){
             if(httpServletRequest.getAttribute("landingpage") != null){
     			iRetCode = 0;
     				try{
     					aszLandingPage = httpServletRequest.getAttribute("landingpage").toString();
     				}catch(Exception e){}
     				httpServletRequest.removeAttribute("landingpage");
     			
             }       	
         }
         if(aszLandingPage.length() > 0){
 		    boolean bNatlAssoc = false;
 		    if(aszPortalRequestType.equals("natlassoc")){
 		    	bNatlAssoc=true;
 		    }
 				
 		    aSParm.setSearchMethod("showLandingPageFacets");
 		    DynaActionForm oFrm = (DynaActionForm)actionForm;
 		    aSParm.setFilterQueryArray( m_BaseHelp.getFormDataStringArray(oFrm,"fq"));
 		    allocatedIndBRLO( httpServletRequest );
 		    AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
 		    aHeadObj.setPortal(aszPortal);
 		    if(bNatlAssoc==false) aSParm.setPortalUID( aszPortalUID);
 		    	if(bNatlAssoc==true){
 		        // lookup the tid of the orgaffil associated with the given National Association
 		    		iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );
 		        }
 		    	iRetCode=-2;
 		           if(aszLandingPage.equals("christianjobs")){
// 		        	   content_type = "content_type:job";
 		           }else if(aszLandingPage.equals("stm") || aszLandingPage.equalsIgnoreCase( "christiangapyear" )){
 		        	   aSParm.setFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\"");
 		           }else if (aszLandingPage.equalsIgnoreCase( "virtual" )){ 
 		        	   aSParm.setFilterQuery("position_type:\"Virtual Volunteering (from home)\"");
 		           }else if (aszLandingPage.equalsIgnoreCase( "family" )){ 
 		        	   aSParm.setFilterQuery("great_for:\"Great for Families\"");
 		           }else if (aszLandingPage.equalsIgnoreCase( "disasterrelief" )){ 
 		        	   aSParm.setFilterQuery("service_areas:\"Disaster / Global Relief\"");
 		           }else if (aszLandingPage.equalsIgnoreCase( "disasterreliefhomepage" )){ 
 		        	   aSParm.setFilterQuery("service_areas:\"Disaster / Global Relief\"");
 		           }else if (aszLandingPage.equalsIgnoreCase( "catholic" ) || httpServletRequest.getHeader("host").contains("catholicvolunteering")){
 		        	   aSParm.setFilterQuery("denom_affil:\"Catholic\"");
 		           }else if (aszLandingPage.equalsIgnoreCase("medicalmissionsvolunteering")){
 		        	   aSParm.setFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\" AND service_areas:\"Health and Medicine\"");
 		           }else if (aszLandingPage.equalsIgnoreCase("volunteerinachristianorphanage")){
 		        	   aSParm.setFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\" AND service_areas:\"Orphanage\"");
 		           }else if (aszLandingPage.equalsIgnoreCase("volunteerintechnologyinmissions")){
 		        	   aSParm.setFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\" AND service_areas:(\"Computers / Technology\" OR \"Tech: Graphics/Web/IT Ministry\")");
 /*
 		        	   String[] aszTemp = new String[1];
 		        	   aszTemp[0] = "position_type:\"Short-term Missions / Volunteer Internship\" AND (service_areas:\"Computers and Technology\" OR \"Tech: Graphics/Web/IT Ministry\")";
 		        	   aSParm.setFilterQueryArray(aszTemp);
  */
 		           }

 		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj);
 		        
 		        ArrayList facetOrgList = new ArrayList(2);
 		        httpServletRequest.setAttribute( "facetorglist", facetOrgList );
 				content_type = "content_type:organization";
 		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetOrgList, aSParm, aHeadObj, content_type);
 		        
 		        ArrayList facetJobList = new ArrayList(2);
 		        httpServletRequest.setAttribute( "facetjoblist", facetJobList );
 				content_type = "content_type:job";
 		        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetJobList, aSParm, aHeadObj, content_type);
 			
 		}
 		
         return actionMapping.findForward( "home" );
     }
     // end-of method showHome()
     
     
     
     
     
     
     
     
     
	  /*
     * show portal opportunity search for them to select favorites from 
     * oppsrch.do?method=showOppSearch1
     *//*
    public ActionForward showPortalOpps(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
   	 
   	 getPortalInfo( httpServletRequest, httpServletResponse, session);
   	 
   	 SearchParms aSParm = new SearchParms();
   	 httpServletRequest.setAttribute( "sparm", aSParm );
   	 int iRetCode=0;
   	 String aszOrgNid="", aszPortal="";
   	 if(httpServletRequest.getParameter("portal") != null ){
   		 if(httpServletRequest.getParameter("portal").length() > 0){
   			 aszPortal = httpServletRequest.getParameter("portal");
   			 if( session.getAttribute(aszPortal + "_nid") != null ){
   				 aszOrgNid = session.getAttribute(aszPortal + "_nid").toString();
   			 }
   		 }
   	 }
   	 if(aszOrgNid.length()<1){
   		 return showFullSearch( actionMapping,  actionForm,  httpServletRequest,  httpServletResponse);
   		 //return  actionMapping.findForward( "showoppsearchall" );
   	 }

   	 DynaActionForm oFrm = (DynaActionForm)actionForm;
   	 
   	 AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   	 if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
   		 return showFullSearch( actionMapping,  actionForm,  httpServletRequest,  httpServletResponse);
   		 //return  actionMapping.findForward( "showoppsearchall" );
   	 }
   	 PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
   	 if(null == aIndivObj) {
   		 return showFullSearch( actionMapping,  actionForm,  httpServletRequest,  httpServletResponse);
   	 }
   	 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  		 }else if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
   		 return showFullSearch( actionMapping,  actionForm,  httpServletRequest,  httpServletResponse);
  		 }
   	 
        allocatedOrgBRLO( httpServletRequest );
        OrganizationInfoDTO aOrgInfoObj = new OrganizationInfoDTO();
        aOrgInfoObj.setORGNID( aszOrgNid );
        aOrgInfoObj.setORGUID( aIndivObj.getUserUID() ); // check that the user MUST HAVE ownership/ adminsitrative rights of the said organization
        iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObj, OrganizationInfoDTO.LOADBY_ORGNID );
        if(iRetCode != 0){// then this user doesn't have administrative access over this org/church
   		 return  actionMapping.findForward( "showoppsearchall" );
        }
        
        httpServletRequest.setAttribute("org", aOrgInfoObj);
   	 
   	 return actionMapping.findForward( "portalopps" );
    }
    
     
     */

  	public ActionForward downloadQuestionnaireFile(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      	getPortalInfo( httpServletRequest, httpServletResponse);
        allocatedOrgBRLO( httpServletRequest );   
        OrgOpportunityInfoDTO opp = new OrgOpportunityInfoDTO();
       
        String oppnidStr = httpServletRequest.getParameter("oppnid");
        if(oppnidStr == null)
        	return actionMapping.findForward("404");
        		
        int oppnid = Integer.parseInt(oppnidStr);
        if(oppnid <= 0)
        	return actionMapping.findForward("404");
              
        if((m_OrgBRLOObj.loadOpportunity( opp, oppnid, 0,"", OrganizationInfoDTO.LOADBY_OPPNID, aszSiteVersion, httpServletRequest.getSession().getServletContext().getRealPath("/") ) != 0) ||
        opp.getQuestionnaireServerFile() == null)
        	return actionMapping.findForward("404");
  		
  		File file = opp.getQuestionnaireServerFile();
  		if(file == null) 
  			return actionMapping.findForward("404");
		
  		String[] tokens = file.getName().split("\\.");
  		String extension = tokens[tokens.length-1];
  		
        return downloadFile(actionMapping, actionForm, httpServletRequest, httpServletResponse, file, "application." + extension );
    }
  	
  	public ActionForward downloadRequiredDocumentFile(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      	getPortalInfo( httpServletRequest, httpServletResponse);
        allocatedOrgBRLO( httpServletRequest );   
        RequiredDocumentDTO doc = new RequiredDocumentDTO();
       
        String docnidStr = httpServletRequest.getParameter("docnid");
        if(docnidStr == null)
        	return actionMapping.findForward("404");
        		
        int docnid = Integer.parseInt(docnidStr);
        if(docnid <= 0)
        	return actionMapping.findForward("404");
              
        if(m_OrgBRLOObj.loadRequiredDocumentByNid(doc, docnid) != 0)
        	return actionMapping.findForward("404");
  		
  		File file = new File(
  			new File(httpServletRequest.getSession().getServletContext().getRealPath("/")).getParent() + File.separator +
  			"files" + File.separator +
  			"opportunity_documents" + File.separator +
  			doc.getOppNid() + File.separator +
  			"other" + File.separator +
  			doc.getNid() + "." + doc.getExtension()
  		);
  		
        return downloadFile(actionMapping, actionForm, httpServletRequest, httpServletResponse, file, doc.getName() + "." + doc.getExtension());
    }

    //====== START Private Methods ===>
    //====== START Private Methods ===>
    //====== START Private Methods ===>
  	
  	private ActionForward downloadFile(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, File file, String outputFilename) {
		httpServletResponse.setContentType((new MimetypesFileTypeMap()).getContentType(file));
		httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + outputFilename);
		httpServletResponse.setContentLength((int) file.length());
		try {
			OutputStream out = httpServletResponse.getOutputStream();
		    FileInputStream in  = new FileInputStream(file);
		    int _byte;
		    while((_byte = in.read()) != -1) {
		    	out.write(_byte);
		    }
		}
		catch(FileNotFoundException e) {
		    return actionMapping.findForward("500");
		}
		catch(IOException e) {
		    return actionMapping.findForward("500");
		}
		return null;
  	}
  	
  	
     /*
      * show organization Remove contact page
      */
      public void getFile(OrganizationInfoDTO oOrgObj, ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  String error = "";
    	  try{
        	  m_UploadForm = (StrutsUploadAndSaveForm)actionForm;
    	  }catch(Exception e){
    		  error = "there is an error";
    	  }
/*
    	  StrutsUploadAndSaveForm myForm = (StrutsUploadAndSaveForm)actionForm;
        // Process the FormFile
        FormFile myFile = myForm.getTheFile();
        String contentType = myFile.getContentType();
    //Get the file name
        String fileName    = myFile.getFileName();
        //int fileSize       = myFile.getFileSize();
        byte[] fileData    = myFile.getFileData();
    //Get the servers upload directory real path name
    String filePath = getServlet().getServletContext().getRealPath("/") +"banners";
    // Save file on the server 
    if(!fileName.equals("")){  
    	if(		contentType.equals("image/jpeg")||
    			contentType.equals("image/gif")	||
    			contentType.equals("image/png")	
    	){
            System.out.println("Server path:" +filePath);
            //Create file
            File fileToCreate = new File(filePath, fileName);
            //If file does not exists create file                      
            if(!fileToCreate.exists()){
              FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
              fileOutStream.write(myFile.getFileData());
              fileOutStream.flush();
              fileOutStream.close();
            }  

    	}

    }
    //Set file name to the request object
    request.setAttribute("fileName",fileName);
      
*/
    }
      /**
       * get logged in status of user; if not, set a value to return the user to either login screen or cookielogin
       */
      protected void getLoggedInStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

    	  aszLoggedInStatus = "";
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          		aszLoggedInStatus="showlogin";
          		return;
          	}else{
          		aszLoggedInStatus="processCookieLogin"  ;
          		return;
          	}
          }
       	 aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aCurrentUserObj) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          		aszLoggedInStatus="showlogin";
          		return;
          	}else{
          		aszLoggedInStatus="processCookieLogin"  ;
          		return;
          	}
          }
      }
      /**
       * get org list & opp list for the current user so we can load it in the organizational left sidebar
       */
      protected void loadOrgsOppsLists(HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj){
    	  loadOrgsOppsLists(httpServletRequest, aIndivObj, false);
      }
      protected void loadOrgsOppsLists(HttpServletRequest httpServletRequest, PersonInfoDTO aIndivObj, boolean bNatlAssoc){

    	  int iRetCode = 0;
    	  
          // Organization list for user
          aOrgList = new ArrayList();
          aOppList = new  ArrayList ( 2 );
          ArrayList aListAdmin = new ArrayList();
          ArrayList aOppListAdmin = new  ArrayList ( 2 );
          ArrayList aListContact = new ArrayList();
          ArrayList aOppListContact = new  ArrayList ( 2 );

          if(aIndivObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
              iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC); 
              iRetCode = m_OrgBRLOObj.getOppListForAdmin( aOppListAdmin, aIndivObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC );
          }
          iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID()); 
          iRetCode = m_OrgBRLOObj.getOppListForAdmin( aOppListAdmin, aIndivObj.getUserUID() );
          
          iRetCode = m_OrgBRLOObj.getOrgListForContact( aListContact, aIndivObj.getUserUID()); 
          iRetCode = m_OrgBRLOObj.getOppListForContact( aOppListContact, aIndivObj.getUserUID() );
          
          boolean b_inList=false;int iNID=0,iNIDincluded=0;
          OrganizationInfoDTO orgElement=new OrganizationInfoDTO();
          OrgOpportunityInfoDTO oppElement=new OrgOpportunityInfoDTO();
  	    Iterator<OrganizationInfoDTO> itrAdmin = aListAdmin.iterator();
  	    while (itrAdmin.hasNext()) {
  	    	b_inList=false;
  	    	orgElement = itrAdmin.next();
  	    	iNID = orgElement.getORGNID();
  	    	if(orgElement!=null){
  	            OrganizationInfoDTO orgElement_aList=new OrganizationInfoDTO();
  	    	    Iterator<OrganizationInfoDTO> itr_aList = aOrgList.iterator();
  	    	    while(itr_aList.hasNext()){
  	    	    	orgElement_aList = itr_aList.next();
  	    	    	iNIDincluded = orgElement_aList.getORGNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aOrgList.add(orgElement);
  	    	    }
  	    	}
  	    }
  	    Iterator<OrganizationInfoDTO> itrContact = aListContact.iterator();
  	    while (itrContact.hasNext()) {
  	    	b_inList=false;
  	    	orgElement = itrContact.next();
  	    	iNID = orgElement.getORGNID();
  	    	if(orgElement!=null){
  	            OrganizationInfoDTO orgElement_aList=new OrganizationInfoDTO();
  	    	    Iterator<OrganizationInfoDTO> itr_aList = aOrgList.iterator();
  	    	    while(itr_aList.hasNext()){
  	    	    	orgElement_aList = itr_aList.next();
  	    	    	iNIDincluded = orgElement_aList.getORGNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aOrgList.add(orgElement);
  	    	    }
  	    	}
  	    }
  	    Iterator<OrgOpportunityInfoDTO> itrOppAdmin = aOppListAdmin.iterator();
  	    while (itrOppAdmin.hasNext()) {
  	    	b_inList=false;
  	    	oppElement = itrOppAdmin.next();
  	    	iNID = oppElement.getOPPNID();
  	    	if(oppElement!=null){
  	    		OrgOpportunityInfoDTO oppElement_aOppList=new OrgOpportunityInfoDTO();
  	    	    Iterator<OrgOpportunityInfoDTO> itr_aOppList = aOppList.iterator();
  	    	    while(itr_aOppList.hasNext()){
  	    	    	oppElement_aOppList = itr_aOppList.next();
  	    	    	iNIDincluded = oppElement_aOppList.getOPPNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aOppList.add(oppElement);
  	    	    }
  	    	}
  	    }
  	    Iterator<OrgOpportunityInfoDTO> itrOppContact = aOppListContact.iterator();
  	    while (itrOppContact.hasNext()) {
  	    	b_inList=false;
  	    	oppElement = itrOppContact.next();
  	    	iNID = oppElement.getOPPNID();
  	    	if(oppElement!=null){
  	    		OrgOpportunityInfoDTO oppElement_aOppList=new OrgOpportunityInfoDTO();
  	    	    Iterator<OrgOpportunityInfoDTO> itr_aOppList = aOppList.iterator();
  	    	    while(itr_aOppList.hasNext()){
  	    	    	oppElement_aOppList = itr_aOppList.next();
  	    	    	iNIDincluded = oppElement_aOppList.getOPPNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aOppList.add(oppElement);
  	    	    }
  	    	}
  	    }
          httpServletRequest.setAttribute( "orglist", aOrgList );
          httpServletRequest.setAttribute( "opplist", aOppList );

      }
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
	* allocate business rule layes object for email 
	*/
	private void allocatedEmailBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_EmailBRLOObj){
			m_EmailBRLOObj = new EmailBRLO();
			m_EmailBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedEmailBRLO()


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
	* allocate StrutsUploadAndSaveForm
	*/
	private void allocatedStrutsUploadAndSaveForm( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_UploadForm){
			m_UploadForm = new StrutsUploadAndSaveForm();
			m_UploadForm.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedStrutsUploadAndSaveForm()
  	
    //====== END Private Methods ===>
    //====== END Private Methods ===>
    //====== END Private Methods ===>

	//====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>

  	private String aszRailsPrefixPath = "";
  	private static final String aszRailsEditPath = "profiles~mine~edit";
  	private static final String aszRailsEditBasicPath = "profiles~mine~edit_basic";
	private static final String aszRailsAccountCreatePath = "profiles~mine~create_basic";
	private static final String aszRailsAccountCreatePageLEGACY = "cor~voleng~profiles~mine~create_basic";
	private static final String aszRailsApplyPositionPath = "cor~voleng~positions~";
	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private ActionHelper m_BaseHelp = new ActionHelper();
	//private RegisterActions m_BaseRegAction = new RegisterActions();
	private searchOpportunitiesAction m_BaseSrchAction = new searchOpportunitiesAction();
	private IndividualActionHelper m_IndActHelp = new IndividualActionHelper();
	private OrganizationActionHelper m_OrgActHelp = new OrganizationActionHelper();
	private OrganizationBRLO m_OrgBRLOObj=null;
	private IndividualsBRLO m_IndBRLOObj=null;
	private EmailBRLO m_EmailBRLOObj=null;
	private searchOpportunitiesBLO m_SearchBRLOObj=null;
	private StrutsUploadAndSaveForm m_UploadForm = new StrutsUploadAndSaveForm();

	private int iMeetTheNeedNID = 67307;
	public String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	private HttpSession session = null;
	private PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
	private String aszLoggedInStatus = ""; 
	private String aszSiteVersion = null;
    private ArrayList aOrgList = new ArrayList();
    private ArrayList aOppList = new  ArrayList ( 2 );
	private static final int iHurrSandyOrgNID = 494934;
	private static final int iDisasterReliefOrgNID = 511070;

	private static final int iCVCInternQuestionnaireID = 7;
	private static final String aszCityVisionInternship = "City Vision Internship";
	
	private static final int vidSkill=31;
	private static final int iRemoved=1, iAdded=2, iNowGetsEmails=3, iNoLongerGetsEmails=4, iNowIsOppContact=5, iNoLongerIsOppContact=6, 
		iNowEmailAndContact=7, iNoLongerEmailOrContact=8, iNewPrimaryContact=9, iNoLongerPrimaryContact=10, iNowIsORGContact=11, iNoLongerIsORGContact=12,
		iRemovedAdmin=21, iAddedAdmin=22, iNowIsOrgContact=32; 
	private static final int iNowIsOrgContactLegacy = 80, iNoLongerOrgContact=81, iNowGetsEmailsLegacy=82, iNoLongerGetsEmailsLegacy=83;
	private static final String aszMaxEmailsInterval="1 MONTH";
	private static final int iMaxEmailsNumber = 10;
	private static final int iJobTID = 33389;
}


