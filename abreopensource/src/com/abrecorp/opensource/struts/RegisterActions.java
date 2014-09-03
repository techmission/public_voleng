package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Individual Registration Related Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

// Struts MVC objects
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.scribe.model.Token;

import com.abrecorp.opensource.base.ABREAppServerDef;
import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.AppSessionDTO;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.abrecorp.opensource.voleng.brlayer.ApplicationCodesBRLO;
import com.abrecorp.opensource.voleng.brlayer.IndividualsBRLO;
import com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO;
import com.abrecorp.opensource.voleng.brlayer.ZipmapBRLO;
import com.abrecorp.opensource.voleng.brlayer.searchOpportunitiesBLO;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import java.util.List;

public class RegisterActions extends DispatchAction {

	/**
	* Constructor 
	*/
	public RegisterActions() {}

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
		    	if (aszLandingPage.equalsIgnoreCase( "foundationgrants" )){ 
		        	   aSParm.setFilterQuery("org_member_type:\"Foundation\"");
				        iRetCode = m_SearchBRLOObj.solrSearch(httpServletRequest, aList, facetList, aSParm, aHeadObj, "content_type:organization");
		    	}else{
	        	   if(aszLandingPage.equals("christianjobs")){
//		        	   content_type = "content_type:job";
		           }else if(aszLandingPage.equals("stm") || aszLandingPage.equalsIgnoreCase( "christiangapyear" )){
		        	   aSParm.setFilterQuery("position_type:\"Short-term Missions / Volunteer Internship\"");
		           }else if (aszLandingPage.equalsIgnoreCase( "virtual" )){ 
		        	   aSParm.setFilterQuery("position_type:\"Virtual Volunteering (from home)\"");
		           }else if (aszLandingPage.equalsIgnoreCase( "catholic" )){ 
		        	   aSParm.setFilterQuery("denom_affil:\"Catholic\"");
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
		    	}
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

    public ActionForward showSyndicationPartners(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "syndication_partners" );
    }
	
    /**
    * show showForwardHome page
    */
    public ActionForward showForwardHome(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "forwardhome" );
    }
    // end-of method showForwardHome()


    /**
     ************************** test pages
     */

    /**
    * show TestAuth page
    */
    public ActionForward showTestAnon(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
        allocatedIndBRLO( httpServletRequest );

//        m_IndBRLOObj.test(  );

    	return actionMapping.findForward( "testanon" );
    }
    // end-of method showTestAnon()

    /**
    * show TestAuth page
    */
    public ActionForward showTestAuth(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "testauth" );
    }
    // end-of method showTestAuth()
    
    /**
    * show login test page
    */
    public ActionForward showloginTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showlogintest" );
    }
    // end-of method showloginTest()
    
    /**
    * load drupal sesison page - assigns drupal session if not already logged in on drupal
    */
    public ActionForward showDrupalSession(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showdrupalsession" );
    }
    // end-of method showDrupalSession()
    

    /*
	* show group volunteering page
	*/
    public ActionForward showGroupVolunteering(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showgroupvolunteering" );
    }
    // end-of method showGroupVolunteering()
    
	/*
	* show Business As A Mission Volunteering page
	*/
    public ActionForward showBusinessVolunteering(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showbusinessvolunteering" );
    }
    // end-of method showBusinessVolunteering()
    
	/*
	* show Disaster Relief Volunteering missions page
	*/
    public ActionForward showDisasterVolunteering(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showdisastervolunteering" );
    }
    // end-of method showDisasterVolunteering()
    
	/*
	* show Internships page
	*/
    public ActionForward showInternships(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showinternships" );
    }
    // end-of method showInternships()
    
    
	/*
	* show about christianvolunteering.org page
	*/
    public ActionForward showAboutCV(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "aboutcv" );
    }
    // end-of method showAboutCV()
    
    
	/*
	* show volunteer start christianvolunteering.org page
	*/
    public ActionForward showVolunteerStart(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "volstart" );
    }
    // end-of method showVolunteerStart()
    
    
	/*
	* show FAQs page
	*/
    public ActionForward showFAQs(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "faqs" );
    }
    // end-of method showAboutCV()
    
	/*
	* show sitemap page
	*/
    public ActionForward showSitemap(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "sitemap" );
    }
    // end-of method showAboutCV()
    
	/*
	* show sitemap page
	*/
    public ActionForward showZipmap(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	ZipmapBRLO brlo = new ZipmapBRLO();
    	brlo.setBaseAppRef( m_BaseServletABREObj.getAppServer( httpServletRequest ) );
    	ArrayList<String[]> zipCodes = brlo.getZipCodes();
    	httpServletRequest.setAttribute("zipCodes", zipCodes);
    	
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "zipmap" );
    }
    // end-of method showZipmap()
    
	/*
	* show showZipmap page
	*/
    public ActionForward showZIPSitemap(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	/*
    	ZipmapBRLO brlo = new ZipmapBRLO();
    	brlo.setBaseAppRef( m_BaseServletABREObj.getAppServer( httpServletRequest ) );
    	List<String> zipCodes = brlo.getZipCodes();
    	httpServletRequest.setAttribute("zipCodes", zipCodes);
    	*/
    	httpServletRequest.setAttribute("zipCodes", "");
    	
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "zipsitemap" );
    }
    // end-of method showAboutCV()

	/*
	* show city-sitemap page
	*/
    public ActionForward showCitySitemap(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "citysitemap" );
    }
    // end-of method showCitySiteMap()

	/*
	* show help page
	*/
    public ActionForward showHelp(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "help" );
    }
    // end-of method showHelp()
    
	/*
	* show Terms & Conditions page
	*/
    public ActionForward showTermsAndConditions(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "termsandconditions" );
    }
    // end-of method showTermsAndConditions()
    
    
	/*
	* show Book Recommendations page
	*/
    public ActionForward showBookRecommendations(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "bookrecommendations" );
    }
    // end-of method showBookRecommendations()
    
    
	/*
	* show links page
	*/
    public ActionForward showLinks(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "links" );
    }
    // end-of method showLinks()
    
    
	/*
	* show presentations
	*/
    public ActionForward showPresentations(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "presentations" );
    }
    // end-of method showPresentations()
    
	/*
	* show standards of excellence for host page
	*/
    public ActionForward showStandardsHost(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "standardshost" );
    }
    // end-of method showStandardsHost()
    
	/*
	* show standards of excellence for leaders page
	*/
    public ActionForward showStandardsLeaders(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "standardsleaders" );
    }
    // end-of method showStandardsLeaders()
    
	/*
	* show standards of excellence for volunteer page
	*/
    public ActionForward showStandardsVol(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "standardsvol" );
    }
    // end-of method showStandardsVol()
    
	/*
	* show Calling page
	*/
    public ActionForward showCalling(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "calling" );
    }
    // end-of method showCalling()
    
	/*
	* show Spiritual Gifts page
	*/
    public ActionForward showSpiritualGifts(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "spiritualgifts" );
    }
    // end-of method showSpiritualGifts()
    
	/*
	* show Biblical page
	*/
    public ActionForward showBiblical(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "biblical" );
    }
    // end-of method showBookRecommendations()
    
	/*
	* show Devotionals on Volunteering page
	*/
    public ActionForward showDevotionals(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "devotionals" );
    }
    // end-of method showBookRecommendations()
    
	/*
	* show technology links page
	*/
    public ActionForward showTechLinks(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "techlinks" );
    }
    // end-of method showBookRecommendations()
    
	/*
	* show STM Resources page
	*/
    public ActionForward showSTMResources(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "stmresources" );
    }
    // end-of method showSTMResources()
    
	/*
	* show virtual Resources page
	*/
    public ActionForward showVirtualResources(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "virtualresources" );
    }
    // end-of method showVirtualResources()
    
	/*
	* show Contact page
	*/
    public ActionForward showContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "contact" );
    }
    // end-of method showContact()
    
	/*
	* show Press Release page
	*/
    public ActionForward showPressRelease(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "pressrelease" );
    }
    // end-of method showPressRelease()
    
	/*
	* show Article links page
	*/
    public ActionForward showArticle(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "article" );
    }
    // end-of method showArticle()
    
	/*
	* show Training page
	*/
    public ActionForward showTraining(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "training" );
    }
    // end-of method showTraining()
    
	/*
	* show TMC Training page
	*/
    public ActionForward showTMCTraining(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "tmctraining" );
    }
    // end-of method showTMCTraining()
    
	/*
	* show staff recruitment page
	*/
    public ActionForward showStaffRecruitment(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "staffrecruitment" );
    }
    // end-of method showStaffRecruitment()
    
    
	/*
	* show work study page
	*/
    public ActionForward showWorkStudy(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "workstudy" );
    }
    // end-of method showWorkStudy()
    
	/*
	* show short term missions page
	*/
    public ActionForward showShortTermMissions(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showstmissions" );
    }
    // end-of method showShortTermMissions()
    
    
	/*
	* show christian gap year page
	*/
    public ActionForward showChristianGapYear(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showchristiangapyear" );
    }
    // end-of method showShortTermMissions()
    
    
	/*
	* show church volunteer page
	*/
    public ActionForward showChurchVolunteer(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showchurchvol" );
    }
    // end-of method showChurchVolunteer()
    
    
	/*
	* show church instructions page
	*/
    public ActionForward showChurchInstructions(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showchurchinstructions" );
    }
    // end-of method showChurchInstructions()
    
    
	/*
	* show christians in technology instructions page
	*/
    public ActionForward showChrisTechInstructions(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showchristechinstructions" );
    }
    // end-of method showChrisTechInstructions()
    
    
	/*
	* show christians in technology page
	*/
    public ActionForward showChrisTech(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showchristech" );
    }
    // end-of method showChrisTech()
    
    
	/*
	* show christians in technology page
	*/
    public ActionForward showTech(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showtech" );
    }
    // end-of method showTech()
    
    
	/*
	* show Training Webcast
	*/
    public ActionForward showTrainingWebcast(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "trainingwebcast" );
    }
    // end-of method showTrainingWebcast()
 
	/*
	* show volunteer stories page
	*/
    public ActionForward showVolStories(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "volstories" );
    }
    // end-of method showVolStories()

     
	/*
	* show abs pages
	*/  
    public ActionForward showABSBibleOutreach(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "absbibleoutreach" );
    }
    // end-of method showABSBibleOutreach()
    public ActionForward showABSBibleSunday(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "absbiblesunday" );
    }
    // end-of method showABSBibleSunday()
    public ActionForward showABSExecVol(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "absexecvol" );
    }
    // end-of method showABSExecVol()
    public ActionForward showABSTrvlEngagementCtr(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "abstrvlengagementctr" );
    }
    // end-of method showABSTrvlEngagementCtr()

    
	/*
	* show cityserve news pages
	*/  
    public ActionForward showCityServeNewsWinter05(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservenewswinter05" );
    }
    // end-of method showCityServeNewsWinter05()
    
    public ActionForward showCityServeNewsJun06(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservenewsjun06" );
    }
    // end-of method showCityServeNewsJun06()

    public ActionForward showCityServeNewsAug06(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservenewsaug06" );
    }
    // end-of method showCityServeNewsAug06()
    
    public ActionForward showCityServeNewsOct06(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservenewsoct06" );
    }
    // end-of method showCityServeNewsOct06()

    public ActionForward showCityServeNewsDec06(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservenewsdec06" );
    }
    // end-of method showCityServeNewsDec06()
    
    
    /*
	* show cityserve training pages
	*/
    public ActionForward showCityServeTrain1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservetrain1" );
    }
    // end-of method showCityServeTrain1()

    public ActionForward showCityServeTrain2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservetrain2" );
    }
    // end-of method showCityServeTrain2()

    public ActionForward showCityServeTrain3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "cityservetrain3" );
    }
    // end-of method showCityServeTrain3()

    
    // start-of method showUrbMinNewAccnt()
    public ActionForward showUrbMinNewAccnt(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "urbmincreateaccnt" );
    }

    // start-of method showSources()
    public ActionForward showSources(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "sources" );
    }

    // start-of method showFacebookLogin()
    public ActionForward showFacebookLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	String aszTemp=null;
    	aszTemp="facebooklogin";
    	return actionMapping.findForward( "facebooklogin" );
    }

	/*
	* show facebookauthredirect page
	*/
    public ActionForward showFacebookAuthRedirect(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {    	
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
        return actionMapping.findForward( "facebookauthredirect" );
    }
    // end-of method showHome()


    // start-of method showPersonalityTypeSummaries()
    public ActionForward showPersonalityTypeSummaries(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "personalitytypesummaries" );
    }

    // start-of method showPersonalityEffectsMinistry()
    public ActionForward showPersonalityEffectsMinistry(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "personalityeffectsministry" );
    }

    // start-of method showPersonalityFourPreferences()
    public ActionForward showPersonalityFourPreferences(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "personalityfourpreferences" );
    }

    /**
    * show login page
    */
    public ActionForward showlogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {     
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	return actionMapping.findForward( "showlogin" );
    }
    // end-of method showlogin()

    public ActionForward showIndReg1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
      	return showCreateAccount1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * show showCreateAccount1 
     */
     public ActionForward showCreateAccount1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
     	AppSessionDTO aSessDat=null;
     	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null == aSessDat){
    	}else{
         	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )	||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP ) ||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IWANTTOHELP )
         	){
         		aIndivObj.setUSPVolOrOpportunity("Volunteer");
        	}
    	}
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	
		if(aSessDat.getTokenKey() == AppSessionDTO.TOKEN_CVINTERNAPPLIC){
	     	return actionMapping.findForward( "cvinterncreateaccount" );
		}
     	return actionMapping.findForward( "createaccount1" );
     }
     // end-of method showCreateAccount1()

     /**
      * show member benefits
      */
     public ActionForward showMemberBenefits(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
     	return actionMapping.findForward( "memberbenefits" );
     }

     /**
      * show showFacebookSplash - IS THIS USED???
      */
      public ActionForward showFacebookSplash(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
        	getPortalInfo( httpServletRequest, httpServletResponse);
        	String aszPortal="", aszPortalNID="";
            if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
            if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
            // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    		if(aszPortal.length()>0){
            	if(aszPortalNID.length()==0){
            		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        			//httpServletRequest.setAttribute("redirectpage","noportalexists");
        			//return actionMapping.findForward("mappingpage");
        			return actionMapping.findForward("404");
            	}
            }
      	return actionMapping.findForward( "facebooksplash" );
      }
      // end-of method showFacebookSplash()

  	
  	/*
  	* show showResumePost page
  	*/
  	public ActionForward showResumePost(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
  	{
      	getPortalInfo( httpServletRequest, httpServletResponse);
      	String aszPortal="", aszPortalNID="";
          if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
          if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
              getLoggedInStatus(httpServletRequest, httpServletResponse);
        		if(aszLoggedInStatus.equals("showlogin")){
        	    	return actionMapping.findForward( "showlogin" );
        		}else if(aszLoggedInStatus.equals("processCookieLogin")){
        	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        		}
          }
  	   PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
  	   if(null == aIndivObj) {
  		   //aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITINDIV );
  	          getLoggedInStatus(httpServletRequest, httpServletResponse);
  	    		if(aszLoggedInStatus.equals("showlogin")){
  	    	    	return actionMapping.findForward( "showlogin" );
  	    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  	    		}
  	   }
  	   httpServletRequest.setAttribute("userprofile", aIndivObj);
 	    	return actionMapping.findForward( "fileuploadpage" );
  	}
  	// end-of method showResumePost()
	
	/*
	* show processDeleteResume page
	*/
	public ActionForward processDeleteResume(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
	{
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
            getLoggedInStatus(httpServletRequest, httpServletResponse);
      		if(aszLoggedInStatus.equals("showlogin")){
      	    	return actionMapping.findForward( "showlogin" );
      		}else if(aszLoggedInStatus.equals("processCookieLogin")){
      	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      		}
        }
        getLoggedInStatus(httpServletRequest, httpServletResponse);
        //PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
	   if(null == aCurrentUserObj) {
		   //aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITINDIV );
	          getLoggedInStatus(httpServletRequest, httpServletResponse);
	    		if(aszLoggedInStatus.equals("showlogin")){
	    	    	return actionMapping.findForward( "showlogin" );
	    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
	    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	    		}
	   }
	   httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
	   
		  int iUID = aCurrentUserObj.getUserUID();
		  int iUPnid = aCurrentUserObj.getUserProfileNID();
		  String aszUsername = aCurrentUserObj.getUSPUsername();
	   // actual delete of resume post.
		  allocatedIndBRLO( httpServletRequest );
		  allocatedApplBRLO( httpServletRequest );
		  //this will be unique Id used by Solr to index the file contents.
		  String solrId = aszUsername + "_UID-" + iUID + "_UPnid-" + iUPnid;
		  m_IndBRLOObj.removeResume( httpServletRequest, aCurrentUserObj );
		  m_ApplBRLOObj.removeFileFromSolrIndex(solrId, aCurrentUserObj, httpServletRequest);
		  m_IndBRLOObj.updateResume(aCurrentUserObj);

		  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
		  String aszTemp = httpServletRequest.getHeader("host");
		  String aszRailsEdit = aszRailsPrefixPath + aszRailsEditPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEdit);
	 	  return actionMapping.findForward("mappingpage");			  
//	    	return showResumePost( actionMapping, actionForm, httpServletRequest, httpServletResponse );
	}
	// end-of method processDeleteResume()
  	
  	/*
  	* show showResume page
  	*/
  	public ActionForward showResume(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
  	{
  		int iRetCode=0;
  		String aszUID="";
  		getPortalInfo( httpServletRequest, httpServletResponse);
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
  		getLoggedInStatus(httpServletRequest, httpServletResponse);
  		if(aszLoggedInStatus.equals("showlogin")){
  	    	return actionMapping.findForward( "showlogin" );
  		}else if(aszLoggedInStatus.equals("processCookieLogin")){
  	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
  		}

        allocatedIndBRLO( httpServletRequest );
        if(httpServletRequest.getParameter("uid")!=null){
        	aszUID=(String)httpServletRequest.getParameter("uid");
        }
        int iUID = m_BaseHelp.convertToInt( aszUID );
        getLoggedInStatus(httpServletRequest, httpServletResponse);

        //PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
      	if (	aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
      			aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)	||
      			aCurrentUserObj.getUSPSiteUseType().equalsIgnoreCase("Both")			||
      			iUID == aCurrentUserObj.getUserUID() 
      			|| true == true
      	){ // normally would test that they are an org-only user first to give them access
          	PersonInfoDTO aIndivObj = new PersonInfoDTO();
          	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	aIndivObj.setUserUID(aszUID);
            iRetCode = m_IndBRLOObj.loadUserByOption( aIndivObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
            
            return actionMapping.findForward( "filedownloadpage" );
  		}

		m_BaseHelp.setFormData(oFrm,"errormsg", "you must be an organizational user to download this resume" );
		return actionMapping.findForward( "noaccess" );
   	}
  	// end-of method showResume()
  	

      /**
      * show showCreateAccount2 page
      */
      public ActionForward showCreateAccount2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
       	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
      }
      // end-of method showCreateAccount2()


      /**
      * showMappingPage - determine forwarding to an external URL, if necessary
      */
      public ActionForward showMappingPage(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0 ;
       	getPortalInfo( httpServletRequest, httpServletResponse);
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          PersonInfoDTO aIndivObj = new PersonInfoDTO();
          iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aIndivObj);
          
          if (aIndivObj.getMappingToPage().length() > 0){
        	  if (aIndivObj.getMappingToPage().equalsIgnoreCase("fbapp")){
        		  if(httpServletRequest.getHeader("host").contains("ivolunteering.org") || httpServletRequest.getHeader("host").contains("ivol.org")){
        			  httpServletResponse.sendRedirect("http://facebook.ivolunteering.org/register.do?method=showPersonalityTest");
        		  }else{
        			  httpServletResponse.sendRedirect("http://facebook.christianvolunteering.org/register.do?method=showPersonalityTest");
        		  }
        	  }else if (aIndivObj.getMappingToPage().equalsIgnoreCase("urbmin")){
        		  httpServletResponse.sendRedirect("http://www.urbanministry.org");
        	  }
          }
          return actionMapping.findForward("mappingpage");
      }
      

      /**
      * showMappingPage - determine forwarding to an external URL, if necessary
      */
      public ActionForward showMapToPage(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
    	  int iRetCode=0 ;
       	getPortalInfo( httpServletRequest, httpServletResponse);
    	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          PersonInfoDTO aIndivObj = new PersonInfoDTO();
          iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aIndivObj);
          return actionMapping.findForward("mappingpage");
      }
      
      
      /**
      * show showCreateAccount3 page
      */
      public ActionForward showUpdateAccount3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
       	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
       if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
       	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
        getLoggedInStatus(httpServletRequest, httpServletResponse);
		if(aszLoggedInStatus.equals("showlogin")){
	    	return actionMapping.findForward( "showlogin" );
		}else if(aszLoggedInStatus.equals("processCookieLogin")){
	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		}
       }
	   PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
	   if(null == aIndivObj) {
		   //aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITINDIV );
		   if (session.getAttribute ("drupalsession")=="login") return actionMapping.findForward( "showlogin" );
          return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	   }
	   httpServletRequest.setAttribute("userprofile", aIndivObj);
	   allocatedIndBRLO( httpServletRequest );


        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
        }else{
            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
        }
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);

      	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
         	session.setAttribute("usecase", "organization");
          	String aszOrgNid="";
          	session.setAttribute("orgmanagementnid", aszOrgNid);
          	aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
          	iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
     	}
/*      	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("gigyaSocialize") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("linkGigyaSocialize")){
         	session.setAttribute("socialize", aIndivObj.getProvider());
         	// if the user has been successful at 1st step of account creation, and is an organizational user, forward them to the orgmanagment page
          	// **?????????????? when do we ask them to subscribe to newsletter, then??????????????
         	if (aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)){ //if they are an Org-only user, then the uprofile does not go in the directory
             	return actionMapping.findForward( "socializedashboard" );
         	}
         	return actionMapping.findForward( "socializecreateaccount2" );
      	}
*/         
     	// if the user has been successful at 1st step of account creation, and is an organizational user, forward them to the orgmanagment page
      	// **?????????????? when do we ask them to subscribe to newsletter, then??????????????
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
     		return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
     	}
     	
     	// still a partial user; we still need some required info from user before they go to the third page
     	if(aIndivObj.getUserIsLoggedIntoSystem()== PersonInfoDTO.USER_LOGIN_PARTIAL ){
  		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
  		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
  	 	  return actionMapping.findForward("mappingpage");			  
//        	return actionMapping.findForward( "createaccount2" );
     	}
		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
//     	return actionMapping.findForward( "createaccount3" );
      }
      // end-of method showUpdateAccount3()

      /**
       * show showManageSubscription page
       */
       public ActionForward showManageSubscription(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
    	   int iRetCode=0 ;
        	getPortalInfo( httpServletRequest, httpServletResponse);
        	String aszPortal="", aszPortalNID="";
            if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
            if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
            if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
            	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
                getLoggedInStatus(httpServletRequest, httpServletResponse);
          		if(aszLoggedInStatus.equals("showlogin")){
          	    	return actionMapping.findForward( "showlogin" );
          		}else if(aszLoggedInStatus.equals("processCookieLogin")){
          	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          		}
            }
    	   PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
    	   if(null == aIndivObj) {
    		   //aSessDat.setTokenKey( AppSessionDTO.TOKEN_EDITINDIV );
    	          getLoggedInStatus(httpServletRequest, httpServletResponse);
    	    		if(aszLoggedInStatus.equals("showlogin")){
    	    	    	return actionMapping.findForward( "showlogin" );
    	    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	    		}
    	   }
    	   httpServletRequest.setAttribute("userprofile", aIndivObj);
    	   allocatedIndBRLO( httpServletRequest );
    	   
	        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
    	   if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
    		   aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
    	   }else{
    		   aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
    	   }
    	   iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
    	   httpServletRequest.setAttribute("userprofile", aIndivObj);
    	   
        	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
         			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
         	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		   session.setAttribute("usecase", "organization");
    		   String aszOrgNid="";
    		   session.setAttribute("orgmanagementnid", aszOrgNid);
    		   aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
    		   iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
    	   }
    	   
    	   return actionMapping.findForward( "managesubscription" );
       }
       // end-of method showManageSubscription()

    /**
    * show individual account summary page one
    */
    public ActionForward showIndAcctSum1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aIndivObj) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
        allocatedOrgBRLO( httpServletRequest );

     	String aszOrgNid="";
        if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
        	session.setAttribute("usecase", "volunteer");
        	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Both")){
        	session.setAttribute("usecase", "volorg");
    	   	if(httpServletRequest.getHeader("host").contains("churchvol")){
             	session.setAttribute("orgmanagementnid", aszOrgNid);
             	return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//            	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    	   	}else{
            	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	   	}
        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
        	session.setAttribute("usecase", "organization");
         	session.setAttribute("orgmanagementnid", aszOrgNid);
        	return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
        }
    	return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    // end-of method showIndAcctSum1()

    /**
    * show individual account summary page two
    */
    public ActionForward showIndAcctSum2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return showIndAcctSum1( actionMapping, actionForm, httpServletRequest, httpServletResponse );
    }
    // end-of method showIndAcctSum2()

    /**
     * show Volunteer Dashboard account summary page one
     */
     public ActionForward showVolunteerDashboard(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
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
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
         	if (session.getAttribute ("drupalsession") != null &&
         		((String)session.getAttribute("drupalsession")).equals("login")){  // Storing Value into session Object
         		return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
      	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
  		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
	         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
	      	// NEW - redirect user to mapping page for use of rails app for later account creation stages
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
			if(aIndivObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
			}
	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	      	return actionMapping.findForward("mappingpage");
	 //			 return actionMapping.findForward( "createaccount2" );
		 }
         if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
         	aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	}
         }

         // Organization list for user
         allocatedOrgBRLO( httpServletRequest );
         ArrayList aList = new ArrayList();
         ArrayList aListAdmin = new ArrayList();
         ArrayList aListContact = new ArrayList();

         if(aIndivObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
             iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC); 
         }
         iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID()); 
         iRetCode = m_OrgBRLOObj.getOrgListForContact( aListContact, aIndivObj.getUserUID()); 
         
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
 	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
 	    	    while(itr_aList.hasNext()){
 	    	    	orgElement_aList = itr_aList.next();
 	    	    	iNIDincluded = orgElement_aList.getORGNID();
 	    	    	if(iNID==iNIDincluded){
 	    	    		b_inList=true;
 	    	    	}
 	    	    }
 	    	    if(b_inList==false){
 	    	    	aList.add(orgElement);
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
 	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
 	    	    while(itr_aList.hasNext()){
 	    	    	orgElement_aList = itr_aList.next();
 	    	    	iNIDincluded = orgElement_aList.getORGNID();
 	    	    	if(iNID==iNIDincluded){
 	    	    		b_inList=true;
 	    	    	}
 	    	    }
 	    	    if(b_inList==false){
 	    	    	aList.add(orgElement);
 	    	    }
 	    	}
 	    }
         httpServletRequest.setAttribute( "orglist", aList );
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         if(null != aSessDat){
         	aSessDat.setTokenKey(null);
         	aSessDat.setOrgNID(null);
         	aSessDat.setOppNID(null);
         	aSessDat.setSubdomain(null);
         	aSessDat.setSiteEmail(null);
         }
         
         return actionMapping.findForward( "volunteerdashboard" );
     }
     // end-of method showVolunteerDashboard()    

    /**
    * show drupal login page
    */
    public ActionForward showDrupalLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        	if (! (session.getAttribute ("drupalsession") == null)){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aIndivObj) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
 		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			 /*
 			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
			 */
		     httpServletRequest.setAttribute("userprofile", aIndivObj);

 		 
	            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
	        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	        	return actionMapping.findForward( "drupallogin" );
 		 }else
        if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
        	return actionMapping.findForward( "drupallogin" );
        }
    	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        	return actionMapping.findForward( "showlogin" );
    	}else{
            return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    	}
    }
    // end-of method showIndEditProfile1()
    
    
    
    /**
    * show page for a Volunteer Account to change to an Organizational Account (or, more likely, a "Both" Account
    */
    public ActionForward showCreateOrgFromVol(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aIndivObj) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
        	}
        }
 		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
	         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
	      	// NEW - redirect user to mapping page for use of rails app for later account creation stages
 			if(aIndivObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
			}
		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
		 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
	      	return actionMapping.findForward("mappingpage");
	 //			 return actionMapping.findForward( "createaccount2" );
		 }
        if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
        	return actionMapping.findForward( "switchorgaccnt" );
        }
    	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
        	return actionMapping.findForward( "showlogin" );
    	}else{
        	return actionMapping.findForward( "showlogin" );
    	}
    }
    // end-of method showCreateOrgFromVol()

    
    /* start-of method processFacebookCreateAccount
     * Method for creating accounts in the Facebook Application
     * Creates temporary "PARTIAL" accounts with a bogus email if they haven't given an email
     */
    
    public ActionForward processFacebookCreateAccount(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
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
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
        // get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
        iRetCode = m_IndActHelp.getIndividualDataFromForm( 110, oFrm, aIndivObj);
        iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 2);
        
        if( aIndivObj.getFacebookUID().length() > 0){
        	session.setAttribute("FB_User_ID", aIndivObj.getFacebookUID());
        }
    	
        String aszPath=httpServletRequest.getServletPath();
     	if( ( //if this method is not being called from the drupal installation cookie_login module's cookie_drupal.php file, then it should not log in the user
     			aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp") 
     	)){
            iRetCode = m_IndBRLOObj.validateCookieLoginPwd(aIndivObj);
     	}
     	
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
             	// NEW - redirect user to mapping page for use of rails app for later account creation stages
    			if(aIndivObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
    			}
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        //            	return actionMapping.findForward( "createaccount2" );
            	//            	return actionMapping.findForward( "drupalaccount" );
          	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
            	return actionMapping.findForward( "personalityministryarea" );
          	}
        	return actionMapping.findForward( "createaccount1" );
        }
        allocatedIndBRLO( httpServletRequest );
        
        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
      		if(session.getAttribute("FB_User_ID")!=null){
          		 if(session.getAttribute("FB_User_ID").toString().length()>1){
          			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
          		 }
      		}
            if(session.getAttribute("facebookapikey")==null || session.getAttribute("facebooksecretkey")==null){
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
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                     	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                     	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                         	return actionMapping.findForward( "personalityministryarea" );
                      	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
                         	// NEW - redirect user to mapping page for use of rails app for later account creation stages
                			if(aIndivObj.getUserProfileNID() < 1){ 
                				// this user is an old drupal user-only; need to take through partial account creation process
                				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
                				//		as well as an insert into the rails db
                				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
                		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
                			}
                	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
                	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                         	return actionMapping.findForward("mappingpage");
                    //                       	return actionMapping.findForward( "createaccount2" );
                        	//                        	return actionMapping.findForward( "drupalaccount" );
                      	}
                        return actionMapping.findForward( "createaccount1" );
            		}
            	}else{
            		if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                    	return actionMapping.findForward( "personalityministryarea" );
                  	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                     	// NEW - redirect user to mapping page for use of rails app for later account creation stages
            			if(aIndivObj.getUserProfileNID() < 1){ 
            				// this user is an old drupal user-only; need to take through partial account creation process
            				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
            				//		as well as an insert into the rails db
            				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
            		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
            			}
            	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
            	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                     	return actionMapping.findForward("mappingpage");
                //                    	return actionMapping.findForward( "createaccount2" );
                    	//                    	return actionMapping.findForward( "drupalaccount" );
                 	}
                	httpServletRequest.setAttribute("userprofile", aIndivObj);
            		return actionMapping.findForward( "createaccount1" );
            	}
            }
      	}
        
//		generate an email address if one wasn't passed
        //generated emails will have the form "facebooktemp[Facebook_UID]@urbanministry.org"
        //what if the Facebook UID wasn't passed????
        if((( aIndivObj.getUSPEmail1Addr().length() == 0) || (aIndivObj.getUSPEmail1Addr() == null)) && (aIndivObj.getFacebookUID().length() > 0)){
        	String tempEmail = "facebooktemp" + aIndivObj.getFacebookUID() + "@urbanministry.org";
        	aIndivObj.setUSPEmail1Addr(tempEmail);
        }
        
        
        
       
//		generate a random password for the account (should this be seperated out to a seperate function????)
        String charset = "!0123456789-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // Password is generated by picking 15 random numbers between 0 and the length
        // of charset, then grabbing the character at that position in charset.
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 15; i++){
        	int pos = rand.nextInt(charset.length());
        	sb.append(charset.charAt(pos));
        }
        
        //Since we auto-generated this password, we need to set the PasswordConfirm field as well
        String tempPassword = sb.toString();
        aIndivObj.setUSPPassword(tempPassword);
        aIndivObj.setPasswordConfirm(tempPassword);
        
    

        String aszSiteVersion="default";
		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
			){
			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
			aszSiteVersion="staging";
	}
        
        
//      register new individual
      	aIndivObj.setErrorMsg("");// clear out any previous error messages
        iRetCode = m_IndBRLOObj.registerNewFacebookAppIndividual( aIndivObj, aszSiteVersion );
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
            	httpServletRequest.setAttribute("userprofile", aIndivObj);
              	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             	// NEW - redirect user to mapping page for use of rails app for later account creation stages
    	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
    	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        //            	return actionMapping.findForward( "createaccount2" );
            	//            	return actionMapping.findForward( "drupalaccount" );
          	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
              	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
            	return actionMapping.findForward( "personalityministryarea" );
          	}
        	return actionMapping.findForward( "personalityministryarea" );
        }
        aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );

        // ************** make sure the internal comment is preserved!!!!!!!!! ********
        
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);

    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
        	session.setAttribute("usecase", "organization");
         	String aszOrgNid="";
         	session.setAttribute("orgmanagementnid", aszOrgNid);
         	aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
         	iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
    	}
     	
        
    	// if the user has been successful at 1st step of account creation, and is an organizational user, forward them to the orgmanagment page
     	// **?????????????? when do we ask them to subscribe to newsletter, then??????????????
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
     		return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
    	}
    	return actionMapping.findForward( "personalityresults" );
    
    }

    /**
    * process processCreateAccountCVIntern page
    */
    public ActionForward processCreateAccountCVIntern(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
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
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
        
        // get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
        iRetCode = m_IndActHelp.getIndividualDataFromForm( 110, oFrm, aIndivObj);
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
        
        String aszUserAgent="HTTP_USER_AGENT = " + httpServletRequest.getHeader("user-agent");
     	String aszReferer="HTTP_REFERER = "+httpServletRequest.getHeader("referer");
     	String aszRemoteIP="REMOTE_ADDR = "+httpServletRequest.getHeader("X-Forwarded-For");
     	aIndivObj.setSysInfo(aszUserAgent + "/n/r" + aszReferer + "/n/r" + aszRemoteIP);
     	aIndivObj.setIP(httpServletRequest.getHeader("X-Forwarded-For"));
     	
     	String aszPath=httpServletRequest.getServletPath();
     	if( ( //if this method is not being called from the drupal installation cookie_login module's cookie_drupal.php file, then it should not log in the user
     			aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp") 
     	)){
            iRetCode = m_IndBRLOObj.validateCookieLoginPwd(aIndivObj);
     	}
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){

          		// NEW - redirect user to mapping page for use of rails app for later account creation stages
    	 		httpServletRequest.setAttribute("redirect", AppSessionDTO.TOKEN_CVINTERNAPPLIC);
             	return actionMapping.findForward("mappingpage");
          	}
        	return actionMapping.findForward( "cvinterncreateaccount" );
        }
        allocatedIndBRLO( httpServletRequest );

        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
        aHeadObj.setPortal(aszPortal);
        if(bNatlAssoc==true){
      	  // lookup the tid of the orgaffil associated with the given National Association
            iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );

        }
        
        if(aIndivObj.getUSPOtherAffil1TID()<1)	aIndivObj.setUSPOtherAffil1TID(aHeadObj.getPortalOrgAffilTID());
        else if(aIndivObj.getUSPOtherAffil2TID()<1)	aIndivObj.setUSPOtherAffil2TID(aHeadObj.getPortalOrgAffilTID());
        else aIndivObj.setUSPOtherAffil3TID(aHeadObj.getPortalOrgAffilTID());

        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
      		if(session.getAttribute("FB_User_ID")!=null){
          		 if(session.getAttribute("FB_User_ID").toString().length()>1){
          			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
          		 }
      		}
            if(session.getAttribute("facebookapikey")==null || session.getAttribute("facebooksecretkey")==null){
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
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
            			if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                      	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
                	 		httpServletRequest.setAttribute("redirect", AppSessionDTO.TOKEN_CVINTERNAPPLIC);
                         	return actionMapping.findForward("mappingpage");
                      	}
                        return actionMapping.findForward( "cvinterncreateaccount" );
            		}
            	}else{
            		if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                    	return actionMapping.findForward( "personalityministryarea2" );
                  	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
            	 		httpServletRequest.setAttribute("redirect", AppSessionDTO.TOKEN_CVINTERNAPPLIC);
                     	return actionMapping.findForward("mappingpage");
                 	}
                	httpServletRequest.setAttribute("userprofile", aIndivObj);
            		return actionMapping.findForward( "cvinterncreateaccount" );
            	}
            }
      	}
        
        // register new individual
      	aIndivObj.setErrorMsg("");// clear out any previous error messages

		// if user is coming through urbanministry account creation process, then give them no role
		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
	      	aIndivObj.setUserRoleID(-1);
		}else{
	      	aIndivObj.setUserRoleID(7);
		}
		
        // store whether this was entered through a portal; if so, it will be "favorited" later, so as to handle it through the portal
        //String 
        aszPortal = "";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
                if( session.getAttribute(aszPortal + "_nid") != null	){
                    if( session.getAttribute(aszPortal + "_nid").toString().length()>0	){
                    	try{
                			int iPortalNID = Integer.parseInt(session.getAttribute(aszPortal + "_nid").toString());
	                	   	//if(httpServletRequest.getHeader("host").contains("churchvol")){
	            	    		// this has been entered through a portal
	                	   		aIndivObj.setPortal(iPortalNID);
	                	  	//}
                    	}catch(Exception e){}
                    }
                }
            }
        }

        String aszSiteVersion="default";
		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
			){
			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
				aszSiteVersion="staging";
		}
        iRetCode = m_IndBRLOObj.createAccount1( aIndivObj, aszSiteVersion);
System.out.println("RegisterActions line 2642; after createAccount1");        
        iRetCode = m_IndBRLOObj.createAccount2( aIndivObj, PersonInfoDTO.CREATE_USER_CVINTERNAPPLIC, aszSiteVersion);
System.out.println("RegisterActions line 2644; after createAccount2 with CREATE_USER_CVINTERNAPPLIC");        
        
        IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getConnectLogicByInternalComment(
            aIndivObj.getUSPInternalComment(), 
            httpServletRequest.getHeader("host"), 
            getBaseURL(httpServletRequest), 
            aszSiteVersion
        );
        httpServletRequest.setAttribute("connectLogic", connectLogic);

        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
             	// NEW - redirect user to mapping page for use of rails app for later account creation stages
    			if(aIndivObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
    			}
    	 		httpServletRequest.setAttribute("redirect", AppSessionDTO.TOKEN_CVINTERNAPPLIC);
             	return actionMapping.findForward("mappingpage");
          	}else if(connectLogic != null){
System.out.println("triggered connectLogic != null; not sure what the case is here");          		
             	return actionMapping.findForward(connectLogic.getActionMapping());//??????????????????
          	}
        	return actionMapping.findForward( "cvinterncreateaccount" );
        }
        

        if(connectLogic != null) {
        	Token accessToken = (Token) httpServletRequest.getSession().getAttribute(connectLogic.getAccessTokenSessionKey());
            connectLogic.setAccessToken(aIndivObj, accessToken);
            connectLogic.setAccessSecret(aIndivObj, accessToken);
            connectLogic.setID(aIndivObj, connectLogic.getID(accessToken));
            iRetCode = connectLogic.linkUser(aIndivObj);
            if(iRetCode != 0){
        		aIndivObj.appendErrorMsg(" \nError linking Account");
        	}
        }
        
        aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
        // ************** make sure the internal comment is preserved!!!!!!!!! ********
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);

    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
        	session.setAttribute("usecase", "organization");
         	String aszOrgNid="";
         	session.setAttribute("orgmanagementnid", aszOrgNid);
    	}
     	aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
     	iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );

     	// NEW - redirect user to mapping page for use of rails app for later account creation stages
		setChrisVolAuthCookieOn(httpServletRequest, httpServletResponse, aIndivObj,"rails");
 		httpServletRequest.setAttribute("redirectpage", AppSessionDTO.TOKEN_CVINTERNAPPLIC);
     	return actionMapping.findForward("mappingpage");
    }
    // end-of method processCreateAccountCVIntern()

    /**
    * process processCreateAccount1 page
    */
    public ActionForward processCreateAccount1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
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
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
        
        // get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
        iRetCode = m_IndActHelp.getIndividualDataFromForm( 110, oFrm, aIndivObj);
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	
        String aszRedirect = "";
        try{
	      	if(httpServletRequest.getParameter("redirect") != null ){
	      		if(httpServletRequest.getParameter("redirect").toString().length() > 0) 
	      			aszRedirect = httpServletRequest.getParameter("redirect").toString();
	      	}else if(session.getAttribute("redirect") != null ){
	      		if(session.getAttribute("redirect").toString().length() > 0) {
	      			aszRedirect = session.getAttribute("redirect").toString();
System.out.println("session.removeAttribute(\"redirect\")");
	      			session.removeAttribute("redirect");
	      		}
	      	}
        }catch(NullPointerException e){
        	System.out.println("null");
        }
     	
        /*
        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
		if(aszRedirect.length()>0){
			aszRedirect=aszRedirect.replaceAll("/",  "~");
			if(aszRedirect.endsWith("-apply")){
				aszRedirect=aszRedirect.replaceAll("-",  "~");
				aszRailsAccountCreatePage += "&redirect=positions~" + aszRedirect;
			}else{
				aszRailsAccountCreatePage += "&redirect=" + aszRedirect;
			}
		}
		// */
        
        
        
        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
        if(aszRedirect.length()>0){
            aszRedirect=aszRedirect.replaceAll("/",  "~");
            aszRailsAccountCreatePage += aszRedirect;
        }
        
        
        
        String aszUserAgent="HTTP_USER_AGENT = " + httpServletRequest.getHeader("user-agent");
     	String aszReferer="HTTP_REFERER = "+httpServletRequest.getHeader("referer");
     	String aszRemoteIP="REMOTE_ADDR = "+httpServletRequest.getHeader("X-Forwarded-For");
     	aIndivObj.setSysInfo(aszUserAgent + "/n/r" + aszReferer + "/n/r" + aszRemoteIP);
     	aIndivObj.setIP(httpServletRequest.getHeader("X-Forwarded-For"));
     	
     	String aszPath=httpServletRequest.getServletPath();
     	if( ( //if this method is not being called from the drupal installation cookie_login module's cookie_drupal.php file, then it should not log in the user
     			aszPath.equalsIgnoreCase("/voleng2/drupal_setup.jsp") 
     	)){
            iRetCode = m_IndBRLOObj.validateCookieLoginPwd(aIndivObj);
     	}
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){

          		

    			
String 	aszRedirectTmp=aszRailsAccountCreatePage;
if(aszRedirectTmp.startsWith("cor")){
	try{
		aszRedirectTmp = aszRedirectTmp.substring(3);
	}catch(Exception e){}
}
if(aszRedirectTmp.endsWith("-apply")){
	if(! aszRedirectTmp.contains("position")){
		aszRedirectTmp = "-positions-"+aszRedirectTmp;
	}
	aszRedirectTmp = aszRedirectTmp.replaceAll("-","/");
}
aszRedirectTmp = aszRedirectTmp.replaceAll("~","/");
aszRedirectTmp=aszRedirectTmp.replaceFirst("/voleng", "");
System.out.println("");
System.out.println("		2646	aszRedirectTmp redirects user to:    http://www.christianvolunteering.org"+aszRedirectTmp);
System.out.println("");
	    			
	    			
	    			
          		
          		
          		// NEW - redirect user to mapping page for use of rails app for later account creation stages
    	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        //            	return actionMapping.findForward( "createaccount2" );
            	//            	return actionMapping.findForward( "drupalaccount" );
          	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
            	return actionMapping.findForward( "personalityministryarea2" );
          	}
        	return actionMapping.findForward( "createaccount1" );
        }
        allocatedIndBRLO( httpServletRequest );

        AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
        aHeadObj.setPortal(aszPortal);
        if(bNatlAssoc==true){
      	  // lookup the tid of the orgaffil associated with the given National Association
            iRetCode = m_IndBRLOObj.getPortalInfo( aHeadObj );

        }
        
        if(aIndivObj.getUSPOtherAffil1TID()<1)	aIndivObj.setUSPOtherAffil1TID(aHeadObj.getPortalOrgAffilTID());
        else if(aIndivObj.getUSPOtherAffil2TID()<1)	aIndivObj.setUSPOtherAffil2TID(aHeadObj.getPortalOrgAffilTID());
        else aIndivObj.setUSPOtherAffil3TID(aHeadObj.getPortalOrgAffilTID());

        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
      		if(session.getAttribute("FB_User_ID")!=null){
          		 if(session.getAttribute("FB_User_ID").toString().length()>1){
          			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
          		 }
      		}
            if(session.getAttribute("facebookapikey")==null || session.getAttribute("facebooksecretkey")==null){
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
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
            			if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                        	return actionMapping.findForward( "personalityministryarea2" );
                      	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){

                      		

                			
String 	aszRedirectTmp=aszRailsAccountCreatePage;
if(aszRedirectTmp.startsWith("cor")){
	try{
		aszRedirectTmp = aszRedirectTmp.substring(3);
	}catch(Exception e){}
}
if(aszRedirectTmp.endsWith("-apply")){
	if(! aszRedirectTmp.contains("position")){
		aszRedirectTmp = "-positions-"+aszRedirectTmp;
	}
	aszRedirectTmp = aszRedirectTmp.replaceAll("-","/");
}
aszRedirectTmp = aszRedirectTmp.replaceAll("~","/");
aszRedirectTmp=aszRedirectTmp.replaceFirst("/voleng", "");
System.out.println("");
System.out.println("		2726	aszRedirectTmp redirects user to:    http://www.christianvolunteering.org"+aszRedirectTmp);
System.out.println("");
	    			
	    			
	    			
          		
          		

                      		// NEW - redirect user to mapping page for use of rails app for later account creation stages
                	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                         	return actionMapping.findForward("mappingpage");
                    //                        	return actionMapping.findForward( "createaccount2" );
                        	//                        	return actionMapping.findForward( "drupalaccount" );
                      	}
                        return actionMapping.findForward( "createaccount1" );
            		}
            	}else{
            		if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
                		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                    	return actionMapping.findForward( "personalityministryarea2" );
                  	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
                    	httpServletRequest.setAttribute("userprofile", aIndivObj);
                      	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );

                  		

            			
String 	aszRedirectTmp=aszRailsAccountCreatePage;
if(aszRedirectTmp.startsWith("cor")){
	try{
		aszRedirectTmp = aszRedirectTmp.substring(3);
	}catch(Exception e){}
}
if(aszRedirectTmp.endsWith("-apply")){
	if(! aszRedirectTmp.contains("position")){
		aszRedirectTmp = "-positions-"+aszRedirectTmp;
	}
	aszRedirectTmp = aszRedirectTmp.replaceAll("-","/");
}
aszRedirectTmp = aszRedirectTmp.replaceAll("~","/");
aszRedirectTmp=aszRedirectTmp.replaceFirst("/voleng", "");
System.out.println("");
System.out.println("		2770	aszRedirectTmp redirects user to:    http://www.christianvolunteering.org"+aszRedirectTmp);
System.out.println("");
	    			
	    			
	    			
          		
          		

                      	// NEW - redirect user to mapping page for use of rails app for later account creation stages
            	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
                     	return actionMapping.findForward("mappingpage");
                //                    	return actionMapping.findForward( "createaccount2" );
                    	//                    	return actionMapping.findForward( "drupalaccount" );
                 	}
                	httpServletRequest.setAttribute("userprofile", aIndivObj);
            		return actionMapping.findForward( "createaccount1" );
            	}
            }
      	}
        
        // register new individual
      	aIndivObj.setErrorMsg("");// clear out any previous error messages

		// if user is coming through urbanministry account creation process, then give them no role
		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
	      	aIndivObj.setUserRoleID(-1);
		}else{
	      	aIndivObj.setUserRoleID(7);
		}
		
        // store whether this was entered through a portal; if so, it will be "favorited" later, so as to handle it through the portal
        //String 
        aszPortal = "";
        if(httpServletRequest.getParameter("portal") != null ){
            if(httpServletRequest.getParameter("portal").length() > 0){
                aszPortal = httpServletRequest.getParameter("portal");
                if( session.getAttribute(aszPortal + "_nid") != null	){
                    if( session.getAttribute(aszPortal + "_nid").toString().length()>0	){
                    	try{
                			int iPortalNID = Integer.parseInt(session.getAttribute(aszPortal + "_nid").toString());
	                	   	//if(httpServletRequest.getHeader("host").contains("churchvol")){
	            	    		// this has been entered through a portal
	                	   		aIndivObj.setPortal(iPortalNID);
	                	  	//}
                    	}catch(Exception e){}
                    }
                }
            }
        }

        String aszSiteVersion="default";
		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
			){
			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
				aszSiteVersion="staging";
		}
		
System.out.println("line 2765 - just before createAccount1");
        iRetCode = m_IndBRLOObj.createAccount1( aIndivObj, aszSiteVersion);
        

        IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getConnectLogicByInternalComment(
            aIndivObj.getUSPInternalComment(), 
            httpServletRequest.getHeader("host"), 
            getBaseURL(httpServletRequest), 
            aszSiteVersion
        );
        httpServletRequest.setAttribute("connectLogic", connectLogic);

        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupal") || aIndivObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
             	// NEW - redirect user to mapping page for use of rails app for later account creation stages
    			if(aIndivObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
    			}

          		

    			
String 	aszRedirectTmp=aszRailsAccountCreatePage;
if(aszRedirectTmp.startsWith("cor")){
	try{
		aszRedirectTmp = aszRedirectTmp.substring(3);
	}catch(Exception e){}
}
if(aszRedirectTmp.endsWith("-apply")){
	if(! aszRedirectTmp.contains("position")){
		aszRedirectTmp = "-positions-"+aszRedirectTmp;
	}
	aszRedirectTmp = aszRedirectTmp.replaceAll("-","/");
}
aszRedirectTmp = aszRedirectTmp.replaceAll("~","/");
aszRedirectTmp=aszRedirectTmp.replaceFirst("/voleng", "");
System.out.println("");
System.out.println("		2892	aszRedirectTmp redirects user to:    http://www.christianvolunteering.org"+aszRedirectTmp);
System.out.println("");
	    			
	    			
	    			
          		
          		

    			httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
             	return actionMapping.findForward("mappingpage");
        //            	return actionMapping.findForward( "createaccount2" );
            	//            	return actionMapping.findForward( "drupalaccount" );
          	}else if(connectLogic != null){
             	return actionMapping.findForward(connectLogic.getActionMapping());//??????????????????
//          		return actionMapping.findForward("facebookconnectcreateaccount");
        	}else if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
            	return actionMapping.findForward( "personalityministryarea2" );
          	}
        	return actionMapping.findForward( "createaccount1" );
        }
        

        if(connectLogic != null) {
        	Token accessToken = (Token) httpServletRequest.getSession().getAttribute(connectLogic.getAccessTokenSessionKey());
            connectLogic.setAccessToken(aIndivObj, accessToken);
            connectLogic.setAccessSecret(aIndivObj, accessToken);
            connectLogic.setID(aIndivObj, connectLogic.getID(accessToken));
            iRetCode = connectLogic.linkUser(aIndivObj);
            if(iRetCode != 0){
        		aIndivObj.appendErrorMsg(" \nError linking Account");
        	}
        }
        
        aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
        // ************** make sure the internal comment is preserved!!!!!!!!! ********
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);

    	AppSessionDTO aSessDat=null;
    	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
        	session.setAttribute("usecase", "organization");
         	String aszOrgNid="";
         	session.setAttribute("orgmanagementnid", aszOrgNid);
         	aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
         	iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
    	}
     	if(connectLogic != null){
     		// if the user has been successful at 1st step of account creation, and is an organizational user, forward them to the orgmanagment page
         	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
         			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
         	){ //if they are an Org-only user, then the uprofile does not go in the directory
         		httpServletRequest.setAttribute("redirectpage","orgmanagement");
        		httpServletRequest.setAttribute("newuser","confirm");
        		httpServletRequest.setAttribute("username",aIndivObj.getUSPUsername());
    			return actionMapping.findForward("mappingpage");
     		}
         	// NEW - redirect user to mapping page for use of rails app for later account creation stages
         	setChrisVolAuthCookieOn(httpServletRequest, httpServletResponse, aIndivObj,"rails");
	 		httpServletRequest.setAttribute("redirect", aIndivObj.getImportExternalProfile() > 0 ? connectLogic.getRailsImportPath(httpServletRequest) : aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    //     		return actionMapping.findForward("createaccount2");
     	}
    	// if the user has been successful at 1st step of account creation, and is an organizational user, forward them to the orgmanagment page
     	// **?????????????? when do we ask them to subscribe to newsletter, then??????????????
     	if (	aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)
     	){ //if they are an Org-only user, then the uprofile does not go in the directory
    		
    		setChrisVolAuthCookieOn(httpServletRequest, httpServletResponse, aIndivObj,"createaccount1");
    		
    		// if user is coming through urbanministry account creation process, and is an org, then map them to the memberbenefits page on chrisvol
    		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
    				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
    			return actionMapping.findForward( "memberbenefits" );
    		}else{
    			httpServletRequest.setAttribute("redirectportal", aszPortal);
    			httpServletRequest.setAttribute("redirectpage","orgmanagement");
    			httpServletRequest.setAttribute("newuser","confirm");
    			httpServletRequest.setAttribute("username",aIndivObj.getUSPUsername());
    			return actionMapping.findForward("mappingpage");
    		}
    	}
     	
     	// NEW - redirect user to mapping page for use of rails app for later account creation stages
		setChrisVolAuthCookieOn(httpServletRequest, httpServletResponse, aIndivObj,"rails");
 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
     	return actionMapping.findForward("mappingpage");
//    	return actionMapping.findForward( "createaccount2" );
    }
    // end-of method processCreateAccount1()

    /**
    * process processCreateAccount2 page
    */
    public ActionForward processCreateAccount2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);

		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
    }
    // end-of method processCreateAccount2()


    /**
    * process processCreateAccount3 page
    */
    public ActionForward processUpdateAccount3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
	 }
    // end-of method processUpdateAccount3()


    /**
    * show individual edit profile data page one
    */
    public ActionForward showIndEditProfileDrupal(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
    }
    // end-of method showIndEditProfile1()

    /**
     * process individual registration page FOR drupal site
     */
     public ActionForward processRegistrationUrbMin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
     	int iRetCode=0 ;
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
         // get individual data from web form
         iRetCode = m_IndActHelp.getIndividualDataFromForm( 101, oFrm, aIndivObj);
         if(iRetCode != 0){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
         	return actionMapping.findForward( "urbmincreateaccnt" );
         }
         allocatedIndBRLO( httpServletRequest );
         // register new individual

         String aszSiteVersion="default";
 		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
 				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
 				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
 			){
 			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
			aszSiteVersion="staging";
	}
         iRetCode = m_IndBRLOObj.registerNewIndividual( aIndivObj, aszSiteVersion );
         if(iRetCode != 0){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
         	return actionMapping.findForward( "urbmincreateaccnt" );
         }
         aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
         iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
         allocatedOrgBRLO( httpServletRequest );
         ArrayList aList = new ArrayList();
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);

         // if this account was created b/c a user had requested "I Want to Volunteer!", re-route the user to that opportunity listing again
     	 AppSessionDTO aSessDat=null;
         aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	  if(null != aSessDat){
 	        	int iOppNID = aSessDat.getOppNID();
 	        	int iOrgNID = aSessDat.getOrgNID();
 	        	String aszToken = aSessDat.getTokenKey();
 	        	if( 
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IWANTTOHELP ) && iOppNID > 0) ||
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN ) && iOppNID > 0) ||
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELPSIGNIN ) && iOppNID > 0)
 	        		){
 	        		return m_BaseOrgAction.processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}
         }

		  String aszRailsEditBasic = aszRailsPrefixPath + aszRailsEditBasicPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEditBasic);
	 	  return actionMapping.findForward("mappingpage");			  
//     	return actionMapping.findForward( "urbminnewaccnt" );
     }
     // end-of method processRegistrationUrbMin()
     
     /**
     * show individual edit profile data page one
     */
     public ActionForward showIndEditProfile1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	
    	
		  String aszRailsEdit = aszRailsPrefixPath + aszRailsEditPath;
		  httpServletRequest.setAttribute("redirect", aszRailsEdit);
	 	  return actionMapping.findForward("mappingpage");			  
     }
     // end-of method showIndEditProfile1()


    /**
     * process individual profile edit page
     */
     public ActionForward processIndProfEdit1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
    	  AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
        	getPortalInfo( httpServletRequest, httpServletResponse);
  		  String aszRailsEdit = aszRailsPrefixPath + aszRailsEditPath;
  		  httpServletRequest.setAttribute("redirect", aszRailsEdit);
  	 	  return actionMapping.findForward("mappingpage");			  
     }
     // end-of method processIndProfEdit1()
     
     
    /**
    * process logout
    */
    public ActionForward logout(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
		
		
     	session.removeAttribute("UserSessionBean");  // there should no longer be a UserSessionBean, since the user's session has logged out
     	session.removeAttribute("usecase");  // when the user logs out, the use case gets lost; this really should only be set for auth. users
     	session.removeAttribute("FB_session_key");
     	session.removeAttribute("FB_session_key_expire");
     	session.removeAttribute("FB_User_ID");
     	session.removeAttribute("FB_Timestamp");
     	session.removeAttribute("facebookapikey");
     	session.removeAttribute("facebooksecretkey");
     	session.removeAttribute("fileName");
     	
   		session.removeAttribute("firstTimeThrough");
   		session.removeAttribute("fullSiteInit");
   		session.removeAttribute("fullSite");

     	session.removeAttribute("redirect");
System.out.println("session.removeAttribute(\"redirect\") - logout");
     	/*
     	session.removeAttribute("drupalsession");  // Storing Value into session Object
     	session.removeAttribute("socialize"); 
     	*/
     	// look into why these were just set to clear rather than removed.  might want to switch out for commented lines above and remove
     	session.setAttribute("drupalsession","");  // Storing Value into session Object
//     	session.setAttribute("socialize",""); 

        allocatedIndBRLO( httpServletRequest );
        
		String aszIPAddress = httpServletRequest.getRemoteAddr();
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
	    	 		aIndivObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	    	 		aIndivObj.setSessionIP(aszIPAddress);
	    	 		aIndivObj.setSessionValue(authCookieValue);
	    	 		// does this session id exist in the sessions table in the db? and with the given IP address?
// commented out b/c a logout should just delete all chrisvolAuth cookies 
//	    	 		iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aIndivObj );
//	        		if( iRetCode == 0 ){
	        			// delete the session
	        			m_IndBRLOObj.deleteSessionIDFromSystem( aIndivObj );
//	        		}
	        	}
	        }
	        String aszDomain = "";
			aszDomain = m_IndBRLOObj.getDomainName( httpServletRequest);
	        Cookie cookie = new Cookie ("chrisvolAuth","");
	        cookie.setDomain(aszDomain);
			cookie.setPath("/");
	        cookie.setMaxAge(0);
	        httpServletResponse.addCookie(cookie);
	
	        aIndivObj = new PersonInfoDTO();
	     	iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
	    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	    	if(null != aSessDat){
	        	aSessDat.setTokenKey(null);
	        	aSessDat.setOrgNID(null);
	        	aSessDat.setOppNID(null);
	        	aSessDat.setSubdomain(null);
	        	aSessDat.setSiteEmail(null);
	    	}
	    	aSessDat = new AppSessionDTO();
        }
     	
     	session.invalidate();
     	session = httpServletRequest.getSession();
     	
     	aCurrentUserObj=null;

//    	return actionMapping.findForward( "logout" );
     	return showHome(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    // end-of method logout()

    

    /**
     * show showCreateAccountHurricaneSandy 
     */
     public ActionForward showCreateAccountHurrSandy(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
     	AppSessionDTO aSessDat=null;
     	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null == aSessDat){
    	}else{
         	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )	||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP ) ||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IWANTTOHELP )
         	){
         		aIndivObj.setUSPVolOrOpportunity("Volunteer");
        	}
    	}
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	return actionMapping.findForward( "hurrsandycontactadd" );
     }
     // end-of method showCreateAccountHurrSandy()
    // automatically create an account associated with Hurricane Sandy Disaster Relief
    // acts as a CONTACT as far as privileges - should be able to ADD opps, but not edit other opps

    /*
 	 * add brand new contact for organiztion
 	 */
    public ActionForward addBrandNewHurrSandyOrgContact(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0;
    	getPortalInfo( httpServletRequest, httpServletResponse);
		boolean bNatlAssoc = false;
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	String aszOrgNid = ""+iHurrSandyOrgNID;
     	 aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
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
        
        aSessDat.setOrgNID(aszOrgNid);
        
//        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        allocatedOrgBRLO( httpServletRequest );
        // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
        // load data for organization contact person
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        //get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
        iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
        String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
        allocatedIndBRLO( httpServletRequest );
          
        // for the contact person, write the "init" field for the new user as the current user's email address
        aContactPersonObj.setUSPEmail2Addr(aszEmailAddress);
    	String mailkey = "newOrgContactUserAccnt";
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        
    	Random r1 = new Random();  
    	String nonce = Long.toString(Math.abs(r1.nextLong()), 4).substring(0,4);  
        aContactPersonObj.setUSPUsername(aContactPersonObj.getUSPNameFirst() + aContactPersonObj.getUSPNameLast()+nonce);
        aContactPersonObj.setPasswordConfirm(aContactPersonObj.getUSPPassword());
        

        String aszSiteVersion="default";
		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
			){
			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
			aszSiteVersion="staging";
	}
        iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, "", mailkey, aszSiteVersion);
//System.out.println("registeractions after addNewUserServices");
		if(iRetCode==-111 || iRetCode==-1){
        	  m_BaseHelp.setFormData(oFrm,"errormsg", aContactPersonObj.getErrorMsg() );
        	  httpServletRequest.setAttribute("org", aOrgInfoObj);
	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
	              return actionMapping.findForward( "hurrsandycontactadd" );
//              return actionMapping.findForward( "orgcontactadd" );
        	  
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
        	  
        	  aContactPersonObj.setUSPSiteUseType("Organization");
              iRetCode = m_IndBRLOObj.updateIndividualHead(  aContactPersonObj, aszSiteVersion);

              m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
        	  httpServletRequest.setAttribute("org", aOrgInfoObj);
	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
              return actionMapping.findForward( "hurrsandycontactadd" );
          }

          if(iRetCode!=0){
          	  m_BaseHelp.setFormData(oFrm,"errormsg", "There was an error adding this user as an Organizational Contact" );
            	  httpServletRequest.setAttribute("org", aOrgInfoObj);
  	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
                  return actionMapping.findForward( "hurrsandycontactadd" );
          }
    	  HttpSession session=httpServletRequest.getSession();
          session.setAttribute("orgmanagementnid", aszOrgNid);
          session.setAttribute("requesttype", "ByContact");
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
//            loadOrgsOppsLists( httpServletRequest,  aCurrentUserObj,  bNatlAssoc);
        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
			
			
            return processLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      // end-of method addBrandNewHurrSandyOrgContact()
    


    

    /**
     * show showCreateAccountDisasterRelief 
     */
     public ActionForward showCreateAccountDisasterRelief(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			return actionMapping.findForward("404");
        	}
        }
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
     	AppSessionDTO aSessDat=null;
     	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null == aSessDat){
    	}else{
         	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLHELP )	||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_PARTNERHELP ) ||
         			aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IWANTTOHELP )
         	){
         		aIndivObj.setUSPVolOrOpportunity("Volunteer");
        	}
    	}
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	return actionMapping.findForward( "hurrsandycontactadd" );
     }
     // end-of method showCreateAccountDisasterRelief()
    // automatically create an account associated with Hurricane Sandy Disaster Relief
    // acts as a CONTACT as far as privileges - should be able to ADD opps, but not edit other opps

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
        
        aSessDat.setOrgNID(aszOrgNid);
        
//        aOrgInfoObj.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
        allocatedOrgBRLO( httpServletRequest );
        // user owns the org; they can add another NEW or PRE-EXISTING user as an owner
        // load data for organization contact person
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        //get individual data from web form - things like id's and usertypes will just have to be hidden inputs again
        iRetCode = m_IndActHelp.getIndividualDataFromForm( 121, oFrm, aContactPersonObj);
        String aszEmailAddress = m_BaseHelp.getFormData(oFrm,"email1addr");
        allocatedIndBRLO( httpServletRequest );
          
        // for the contact person, write the "init" field for the new user as the current user's email address
        aContactPersonObj.setUSPEmail2Addr(aszEmailAddress);
    	String mailkey = "newOrgContactUserAccnt";
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
        
        // create username from email address - strip out the @whatever. check if the username already exists; if so, add nonce to end
        int index_atsign=aszEmailAddress.indexOf("@");
        String aszUsername ="";
        if(index_atsign>0){
        	aszUsername=aszEmailAddress.substring(0,index_atsign);
        	aContactPersonObj.setUSPUsername(aszUsername);
        }
        iRetCode = m_IndBRLOObj.isUsernameInSystem(aContactPersonObj);
        if(iRetCode==0){ // username already exists; add a nonce
        	Random r1 = new Random();  
        	String nonce = Long.toString(Math.abs(r1.nextLong()), 4).substring(0,4);  
            aContactPersonObj.setUSPUsername(aszUsername+nonce);
        }
        
        String aszPassword = aContactPersonObj.getUSPPassword();
        aContactPersonObj.setPasswordConfirm(aszPassword);
        

        String aszSiteVersion="default";
		if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
			){
			aszSiteVersion="development";
		}else if(	httpServletRequest.getHeader("host").contains("staging-" )  ){
			aszSiteVersion="staging";
	}
        iRetCode = m_IndBRLOObj.addNewUserServices( httpServletRequest, aContactPersonObj, aOrgInfoObj, aOpportObj, "", mailkey, aszSiteVersion);
//System.out.println("registeractions after addNewUserServices");
		if(iRetCode==-111 || iRetCode==-1){
        	  m_BaseHelp.setFormData(oFrm,"errormsg", aContactPersonObj.getErrorMsg() );
        	  httpServletRequest.setAttribute("org", aOrgInfoObj);
	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
	              return actionMapping.findForward( "hurrsandycontactadd" );
//              return actionMapping.findForward( "orgcontactadd" );
        	  
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
        	  
        	  aContactPersonObj.setUSPSiteUseType("Organization");
              iRetCode = m_IndBRLOObj.updateIndividualHead(  aContactPersonObj, aszSiteVersion);

              m_BaseHelp.setFormData(oFrm,"errormsg", aszErr );
        	  httpServletRequest.setAttribute("org", aOrgInfoObj);
	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
              return actionMapping.findForward( "hurrsandycontactadd" );
          }

          if(iRetCode!=0){
          	  m_BaseHelp.setFormData(oFrm,"errormsg", "There was an error adding this user as an Organizational Contact" );
            	  httpServletRequest.setAttribute("org", aOrgInfoObj);
  	        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
                  return actionMapping.findForward( "hurrsandycontactadd" );
          }
    	  HttpSession session=httpServletRequest.getSession();
          session.setAttribute("orgmanagementnid", aszOrgNid);
          session.setAttribute("requesttype", "ByContact");
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
//        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORT);
        	aSessDat.setTokenKey(AppSessionDTO.TOKEN_CREATEOPPORTDISASTER);
			
			
            return processLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
      }
      // end-of method addBrandNewDisasterReliefOrgContact()
    


    /**
    * process login page
    */
    public ActionForward processLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
//System.out.println("processLogin ");	        	
    	int iRetCode=0, iRetCode2=0 ;
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
     	boolean bPortalSite = false;
     	if(		httpServletRequest.getHeader("host").contains("churchvol.org")	||
     			httpServletRequest.getHeader("host").contains("churchvolunteering.org")
     	){
     		bPortalSite=true;
     	}
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
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
		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
        if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
     	session.setAttribute("drupalsession","");  // Storing Value into session Object
        // get individual data from web form
        String aszRedirect = "";
        try{
	      	if(httpServletRequest.getParameter("redirect") != null ){
	      		if(httpServletRequest.getParameter("redirect").toString().length() > 0) 
	      			aszRedirect = httpServletRequest.getParameter("redirect").toString();
	      	}else if(session.getAttribute("redirect") != null ){
	      		if(session.getAttribute("redirect").toString().length() > 0) {
	      			aszRedirect = session.getAttribute("redirect").toString();
System.out.println("session.removeAttribute(\"redirect\") - processLogin");
	      			session.removeAttribute("redirect");
	      		}
	      	}
        }catch(NullPointerException e){
        	System.out.println("null redirect");
        }
     	iRetCode = m_IndActHelp.getLoginDataFromForm(oFrm, aIndivObj);

        aIndivObj.setUSPPasswordInternal(aIndivObj.getUSPPassword());
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	return actionMapping.findForward( "showregistration" );
        }

        allocatedIndBRLO( httpServletRequest );
        
        IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getConnectLogicByInternalComment(
            aIndivObj.getUSPInternalComment(), 
            httpServletRequest.getHeader("host"), 
            getBaseURL(httpServletRequest), 
            aszSiteVersion
        );
        if(connectLogic != null) {
        	Token accessToken = (Token) httpServletRequest.getSession().getAttribute(connectLogic.getAccessTokenSessionKey());
        	connectLogic.setAccessToken(aIndivObj, accessToken);
        	connectLogic.setAccessSecret(aIndivObj, accessToken);
        	connectLogic.setID(aIndivObj, connectLogic.getID(accessToken));
        }
        
        // register new individual
        iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
        
//System.out.println("       ERROR in user " + aIndivObj.getErrorMsg() );
//System.out.println("user logged in with aszSiteVersion "+aszSiteVersion +"  iRetCode "+iRetCode);   
        
    	httpServletRequest.setAttribute("userprofile", aIndivObj);
        if( (iRetCode != 0) && (iRetCode != -222) ){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
            	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
            	return actionMapping.findForward( "personalityministryarea2" );
          	}else{
          		return actionMapping.findForward( "showlogin" );
          	}
        }
        iRetCode2=iRetCode;
 
        
        
        
        
        
        
        
        
        
        
        /*
         *  logout the previous user	; clear all the attributes and the session	
        * logout the previous user	; clear all the attributes and the session	
        * logout the previous user	; clear all the attributes and the session
        * /
     	session.removeAttribute("UserSessionBean"); 
     	session.removeAttribute("usecase");
     	session.removeAttribute("FB_session_key");
     	session.removeAttribute("FB_session_key_expire");
     	session.removeAttribute("FB_User_ID");
     	session.removeAttribute("FB_Timestamp");
     	session.removeAttribute("facebookapikey");
     	session.removeAttribute("facebooksecretkey");
     	session.removeAttribute("fileName");     	
   		session.removeAttribute("firstTimeThrough");
   		session.removeAttribute("fullSiteInit");
   		session.removeAttribute("fullSite");
     	session.removeAttribute("redirect");
     	session.setAttribute("drupalsession",""); 
System.out.println("session.removeAttribute(\"redirect\") - logout FROM processLogin method");
        allocatedIndBRLO( httpServletRequest );
		String aszIPAddress = httpServletRequest.getRemoteAddr();
        String authCookieValue="";
        Cookie[] cookies = null;
System.out.println("line 3913 - before get cookies call");        
        if(httpServletRequest.getCookies()!=null)
        	cookies = httpServletRequest.getCookies();
        String cookieName="chrisvolAuth";
System.out.println("line 3917 - before if cookies not null");        
        if(cookies != null){
System.out.println("line 3918 - cookies is not null");        
	        for(int i=0; i<cookies.length; i++) {
System.out.println("line 3920 - cookies; i is "+i);
	        	Cookie cookie = cookies[i];
	        	if (cookieName.equals(cookie.getName()))
	        		authCookieValue = cookie.getValue();
	        }
System.out.println("line 3925 - done iterating through cookies");	        
	        
			try{
			if(authCookieValue != null){
	        	if(authCookieValue.length()>0){
System.out.println("3929 - authCookie has a value");	
System.out.println("       before setCookieAuthorize;  PersonInfoDTO.COOKIE_USER is "+PersonInfoDTO.COOKIE_USER);
	        		aCurrentUserObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
System.out.println("3931 - after set cookie authorize PersonInfoDTO.COOKIE_USER");	    	 		
	    	 		aCurrentUserObj.setSessionIP(aszIPAddress);
System.out.println("3933 - aszIPAddress is "+aszIPAddress);	    	 		
	    	 		aCurrentUserObj.setSessionValue(authCookieValue);
System.out.println("3928 - before deleteSessionIDFromSystem");	    	 		
	    	 		m_IndBRLOObj.deleteSessionIDFromSystem( aCurrentUserObj );
System.out.println("3930 - after deleteSessionIDFromSystem");	    	 		
	        	}
	        }
			}catch(java.lang.NullPointerException ex){
				System.out.println("line 3943 - error ex is "+ex);
			}catch(Exception e){
				System.out.println("line 3945 - error e is "+e);
			}
System.out.println("line 3947 - before getting DomainName");			
	        String aszDomain = "";
			aszDomain = m_IndBRLOObj.getDomainName( httpServletRequest);
System.out.println("about to set chrisvolAuth cookie");			
	        Cookie cookie = new Cookie ("chrisvolAuth","");
	        cookie.setDomain(aszDomain);
			cookie.setPath("/");
	        cookie.setMaxAge(0);
	        httpServletResponse.addCookie(cookie);	
	    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	    	if(null != aSessDat){
	        	aSessDat.setTokenKey(null);
	        	aSessDat.setOrgNID(null);
	        	aSessDat.setOppNID(null);
	        	aSessDat.setSubdomain(null);
	        	aSessDat.setSiteEmail(null);
	    	}
	    	aSessDat = new AppSessionDTO();
        }
System.out.println("before session.invalidate()");
		session.invalidate();
System.out.println("after session.invalidate()");
     	session = httpServletRequest.getSession();
     	aCurrentUserObj=null;//aIndivObj;

     	/ *
     	 * finish logging out previous session, if one existed
     	 */
     	
     	
     	
     	
     	
     	
     	
     	
     	
     	
     	
     	
     	
     	
System.out.println("before setChrisVolAuthCookieOn");        
        
        int iRetCode3=setChrisVolAuthCookieOn(httpServletRequest,httpServletResponse,aIndivObj,"login");
        
System.out.println("before connectLogin.linkUser ");
        
        if(connectLogic != null) {
        	iRetCode = connectLogic.linkUser(aIndivObj);
        	if(iRetCode != 0){
        		aIndivObj.appendErrorMsg(" \nError linking Account");
System.out.println(" \nError linking Account");
        	}
        }
        
        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
        	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
            if(aIndivObj.getUSPPersonality() == ""){
                iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
                iRetCode = m_IndBRLOObj.calculatePersonalityFacets(aIndivObj);
            }
        	if( aIndivObj.getFacebookUID().length() > 0){
            	session.setAttribute("FB_User_ID", aIndivObj.getFacebookUID());
            }
        	String aszTemp = session.getAttribute("FB_User_ID").toString();
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
             			return actionMapping.findForward( "personalitytest4" );
             		}
             	}else{
             		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
                	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
             		return actionMapping.findForward( "personalitytest4" );
             	}
             }
       	 	}
        	if(iRetCode2!=-222){
        		// if user is coming through urbanministry account creation process, then give them no role
        		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
        				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
        	      	aIndivObj.setUserRoleID(-1);
        		}else{
        	      	aIndivObj.setUserRoleID(7);
        		}
                iRetCode = m_IndBRLOObj.updateIndividualHead( aIndivObj, aszSiteVersion );
            }
        }
        
        aIndivObj.processTokens();
        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
	        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
	        allocatedOrgBRLO( httpServletRequest );
	    	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	    	if(null != aSessDat){
	    		if(aszRedirect.length()>0){

	    			
String 	aszRedirectTmp=aszRedirect;
if(aszRedirectTmp.startsWith("cor")){
	try{
		aszRedirectTmp = aszRedirectTmp.substring(3);
	}catch(Exception e){}
}
if(aszRedirectTmp.endsWith("-apply")){
	if(! aszRedirectTmp.contains("position")){
		aszRedirectTmp = "-positions-"+aszRedirectTmp;
	}
	aszRedirectTmp = aszRedirectTmp.replaceAll("-","/");
}
aszRedirectTmp = aszRedirectTmp.replaceAll("~","/");
aszRedirectTmp=aszRedirectTmp.replaceFirst("/voleng", "");
System.out.println("");
System.out.println("		4572	aszRedirectTmp redirects user to:    http://www.christianvolunteering.org"+aszRedirectTmp);
System.out.println("");
	    			
	    			
	    			
	            	httpServletRequest.setAttribute("redirect", aszRedirect);
	            	return actionMapping.findForward( "mappingpage" );
	    		}
	    		//int iOppNumber = aSessDat.getOrgIdNum();
	        	int iOppNID = aSessDat.getOppNID();
	        	int iOrgNID = aSessDat.getOrgNID();
	        	String aszToken = aSessDat.getTokenKey();
System.out.println("aszToken is "+aszToken+" and iOppNID == " +iOrgNID);	        	
	        	if( 
	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IWANTTOHELP ) && iOppNID > 0) ||
	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN ) && iOppNID > 0) ||
	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELPSIGNIN ) && iOppNID > 0)
	        		){
                	return m_BaseOrgAction.processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEOPPORT ) && iOrgNID > 0 ){
//System.out.println("correctly triggered condition   " +iOrgNID);	
	        		if(iOrgNID==iDisasterReliefOrgNID){
						if(aIndivObj.getNatlAssociationPortal().length()>0) {
							aszPortal=aIndivObj.getNatlAssociationPortal();
						}
						httpServletRequest.setAttribute("usecase", "organization");
						httpServletRequest.setAttribute("redirectpage","disasterreliefhomepage");
						return actionMapping.findForward( "mappingpage" );
					}else if(iOrgNID==iHurrSandyOrgNID){
						if(aIndivObj.getNatlAssociationPortal().length()>0) {
							aszPortal=aIndivObj.getNatlAssociationPortal();
						}
						httpServletRequest.setAttribute("usecase", "organization");
						httpServletRequest.setAttribute("redirectpage","hurricanesandy");
						return actionMapping.findForward( "mappingpage" );
					}
	        		return m_BaseOrgAction.showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITINDIV ) ){
	        		return showIndEditProfile1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEOPPORTDISASTER ) ){
	        		httpServletRequest.setAttribute("landingpage","landingdisasterreliefhomepage") ;
	        		return m_BaseOrgAction.processCreateOpportstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        		
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_VOLDASHBOARD ) ){
	        		return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CVINTERNAPPLIC ) ) {
	            	httpServletRequest.setAttribute("redirectportal", aszPortal);
	            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERNAPPLIC);
	            	return actionMapping.findForward( "mappingpage" );
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CVINTERNAPPLIC2 ) ) {
	            	httpServletRequest.setAttribute("redirectportal", aszPortal);
	            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERNAPPLIC2);
	            	return actionMapping.findForward( "mappingpage" );
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CVINTERNAPPLIC3 ) ) {
	            	httpServletRequest.setAttribute("redirectportal", aszPortal);
	            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERNAPPLIC3);
	            	return actionMapping.findForward( "mappingpage" );
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_RESUME_POST ) ) {
	            	httpServletRequest.setAttribute("redirectportal", aszPortal);
	            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_RESUME_POST);
	            	return actionMapping.findForward( "mappingpage" );
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CVINTERN_NATLASSOC_MANAGE ) ) {
	            	httpServletRequest.setAttribute("redirectportal", aszPortal);
	            	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERN_NATLASSOC_MANAGE);
	            	return actionMapping.findForward( "mappingpage" );
// ************************************************************************************************************************************************
//	        		return m_BaseEmailAction.showCreateApplication(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEORG ) ) {
	        		return m_BaseOrgAction.showCreateOrgStep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_SHOWCONTACTS ) && iOrgNID > 0 ){
	        		return m_BaseOrgAction.showOrgContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITORG ) && iOrgNID > 0 ){
	        		return m_BaseOrgAction.shownonpeditstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWORG ) && iOrgNID > 0 ){
	        		return m_BaseOrgAction.shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGMANAGEMENT ) ){
	        		return m_BaseOrgAction.showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ASSOCMANAGEMENT ) ){
	        		return m_BaseOrgAction.showAssocManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWOPP ) && iOrgNID > 0 && iOppNID > 0){
	        		return m_BaseOrgAction.showOpportunityPreview(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_SRCHINTERNAPPLICANTS ) ){
	        		httpServletRequest.setAttribute("cvintern","true") ;
	        		return m_BaseOppSrchAction.processSearch(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	        	}
        	}
    	}
        
        if(iRetCode2 != -222){
        	// if the user is coming through facebook, we will still have their information, but we need to ask the user for more information and forward them to drupalaccount page
	        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
	        	session.setAttribute("usecase", "volunteer");
	        	httpServletRequest.setAttribute("userprofile", aIndivObj);
	        	if(aIndivObj.getUSPPersonality() == ""){
	        		return actionMapping.findForward( "myministryopps" );
	        	}else {
	        		return actionMapping.findForward( "personalityresults" );
	        	}
	        }
        }
        
        // if the user is able to manage the cityvision Internships National Association portal, take them to that dashboard
        boolean b_cvintern_natl_assoc_manager = false;
        boolean b_CVCInternshipSiteApproved = false;

        ArrayList aList = new ArrayList();
        if(iRetCode2 != -222){
            ArrayList aListAdmin = new ArrayList();
            ArrayList aListContact = new ArrayList();

            if(bNatlAssoc==true){
                iRetCode = m_OrgBRLOObj.getOrgListForContact( aListContact, aIndivObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC); 
            }else{
                iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID()); 
                iRetCode = m_OrgBRLOObj.getOrgListForContact( aListContact, aIndivObj.getUserUID()); 
            }
            
            boolean b_inList=false;int iNID=0,iNIDincluded=0;
            OrganizationInfoDTO orgElement=new OrganizationInfoDTO();
            OrgOpportunityInfoDTO oppElement=new OrgOpportunityInfoDTO();
    	    Iterator<OrganizationInfoDTO> itrAdmin = aListAdmin.iterator();
    	    while (itrAdmin.hasNext()) {
    	    	b_inList=false;
    	    	orgElement = itrAdmin.next();
    	    	iNID = orgElement.getORGNID();
    	        // b_CVCInternshipSiteApproved if the site has been approved as a city vision host site
    	        if(orgElement.getCVInternSiteApproved().equals("City Vision")){
    	        	b_CVCInternshipSiteApproved = true;
    	        }
    	        if(iNID == iCityVisionNID){	
    	        	b_cvintern_natl_assoc_manager=true;
    	        }
    	    	if(orgElement!=null){
    	            OrganizationInfoDTO orgElement_aList=new OrganizationInfoDTO();
    	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
    	    	    while(itr_aList.hasNext()){
    	    	    	orgElement_aList = itr_aList.next();
    	    	    	iNIDincluded = orgElement_aList.getORGNID();
    	    	    	if(iNID==iNIDincluded){
    	    	    		b_inList=true;
    	    	    	}
    	    	    }
    	    	    if(b_inList==false){
    	    	    	aList.add(orgElement);
    	    	    }
    	    	}
    	    }
    	    Iterator<OrganizationInfoDTO> itrContact = aListContact.iterator();
    	    while (itrContact.hasNext()) {
    	    	b_inList=false;
    	    	orgElement = itrContact.next();
    	        // b_CVCInternshipSiteApproved if the site has been approved as a city vision host site
    	        if(orgElement.getCVInternSiteApproved().equals("City Vision")){
    	        	b_CVCInternshipSiteApproved = true;
    	        }
    	    	iNID = orgElement.getORGNID();
    	    	if(orgElement!=null){
    	            OrganizationInfoDTO orgElement_aList=new OrganizationInfoDTO();
    	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
    	    	    while(itr_aList.hasNext()){
    	    	    	orgElement_aList = itr_aList.next();
    	    	    	iNIDincluded = orgElement_aList.getORGNID();
    	    	    	if(iNID==iNIDincluded){
    	    	    		b_inList=true;
    	    	    	}
    	    	    }
    	    	    if(b_inList==false){
    	    	    	aList.add(orgElement);
    	    	    }
    	    	}
    	    }
	        // test aList to see if any entries have a portalname
	        if(aList.size()>0 && bNatlAssoc==false){	
				if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
					httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" )					||
					httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org") 		||
					httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" )			||
					httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org") 	||
					httpServletRequest.getHeader("host").contains("churchvolunteering.org")  
				){
		        	aszPortal=m_OrgBRLOObj.doesPortalExistForOrgs( aList );
				}
	        }
	        // if any of the orgs has a portal, set up to redirect to that portal
	        if(aList.size()>0 && aszPortal.length()>0){
	        	bPortalSite = true;
	        }
	        
        }
        if (aIndivObj.getUSPVolunteer().length() < 1 && aIndivObj.getUSPVolunteerTID() < 1 
        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser) )
        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser) )
        ){
        	aIndivObj.setUSPVolunteerTID(iVolDirectorytid); // if the user is an indiv or both, by default, have this public listing checked Yes
        }  

        if(connectLogic != null && aIndivObj.getImportExternalProfile() > 0 && aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")) {
        	httpServletRequest.setAttribute("redirect", connectLogic.getRailsImportPath(httpServletRequest));
        	return actionMapping.findForward( "mappingpage" );
        }
        if( iRetCode3 == PersonInfoDTO.USER_LOGINOK && b_cvintern_natl_assoc_manager ) {
	    	httpServletRequest.setAttribute("redirectportal", aszPortal);
	    	httpServletRequest.setAttribute("redirectpage",AppSessionDTO.TOKEN_CVINTERN_NATLASSOC_MANAGE);
	    	return actionMapping.findForward( "mappingpage" );
        }
        
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
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

         	/*
            iRetCode = m_IndBRLOObj.testFullUser( aIndivObj );//already called on line 4318
            if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
                aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
            }else{
                aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
            }
            */
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         	// NEW - redirect user to mapping page for use of rails app for later account creation stages
			if(aIndivObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
			}
	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
         	return actionMapping.findForward("mappingpage");
    //        	return actionMapping.findForward( "createaccount2" );
       }
        
        httpServletRequest.setAttribute( "orglist", aList );
        if(	bPortalSite == true || aIndivObj.getNatlAssociationNID()>0 || b_CVCInternshipSiteApproved){
        	if(b_CVCInternshipSiteApproved){
        		// redirect user to the orgmanagement page, but in the /cityvision/ portal
            	httpServletRequest.setAttribute("redirectpage","cvinternsite");
            	return actionMapping.findForward( "mappingpage" );
            }
        	httpServletRequest.setAttribute("redirectportal", aszPortal);
            if(aIndivObj.getNatlAssociationNID()>0 ){//&& bNatlAssoc==true){ // see first if they have a natl affiliation, and if it loads properly, etc; else...
            	if(aIndivObj.getNatlAssociationPortal().length()>0) {
            		aszPortal=aIndivObj.getNatlAssociationPortal();
            	}
            	httpServletRequest.setAttribute("usecase", "organization");
            	httpServletRequest.setAttribute("redirectportal", aszPortal);
            	httpServletRequest.setAttribute("redirectpage","portalassocmanagement");
            	return actionMapping.findForward( "mappingpage" );
            }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
            	httpServletRequest.setAttribute("usecase", "volunteer");
   		     	httpServletRequest.setAttribute("redirectpage", "volunteerportalsite");
            	return actionMapping.findForward( "mappingpage" );
            }else{
            	httpServletRequest.setAttribute("usecase", "organization");
            	httpServletRequest.setAttribute("redirectpage","portalorgmanagement");
            	return actionMapping.findForward( "mappingpage" );
            }
        }
        
        if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
        	session.setAttribute("usecase", "volunteer");
        	httpServletRequest.setAttribute("redirectpage","volunteerportalsite");
        	return actionMapping.findForward( "mappingpage" );
//        	return actionMapping.findForward( "volunteerdashboard" );
        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Both")){
        	session.setAttribute("usecase", "volorg");
        	httpServletRequest.setAttribute("redirectpage","volunteerportalsite");
        	return actionMapping.findForward( "mappingpage" );
//        	return actionMapping.findForward( "volunteerdashboard" ); 
        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Organization") || aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Church")){
        	session.setAttribute("usecase", "organization");
         	String aszOrgNid="";
         	session.setAttribute("orgmanagementnid", aszOrgNid);
         	return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
        }
        
        return actionMapping.findForward( "volunteerdashboard" );
    }
    // end-of method processLogin()
 
    
    /**
    * process cookie login
    */
    public ActionForward processCookieLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0, iRetCode2=0 ;
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	boolean bPortalSite = false;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
		String aszIPAddress = httpServletRequest.getRemoteAddr();

        allocatedIndBRLO( httpServletRequest );
     	
        // in the mobile phone, it causes issues to load loginstatus and try to detect the cookies from the hidden iframe, 
        // so we'll just forward along to the login page
        boolean b_mobile=false;
        if(	httpServletRequest.getHeader("host").equalsIgnoreCase("m.christianvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.churchvolunteering.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivolunteering.org")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.cv.org:7001")	||
        	httpServletRequest.getHeader("host").equalsIgnoreCase("m.chrisvol.org:7001")	||
        	httpServletRequest.getHeader("host").contains("m.churchvol.org")	||
        	httpServletRequest.getHeader("host").contains("m.ivol.org")	
        ){
            b_mobile=true;
        }
        String authCookieValue=null;
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieName="chrisvolAuth";
        if(cookies != null){
	        for(int i=0; i<cookies.length; i++) {
	        	Cookie cookie = cookies[i];
	        	if (cookieName.equals(cookie.getName())){
	        		authCookieValue = cookie.getValue();
	        		break;
	        	}
	        }
	        if(authCookieValue != null){
	        	if(authCookieValue.length()>0){
	    	 		aIndivObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	    	 		aIndivObj.setSessionIP(aszIPAddress);
	    	 		aIndivObj.setSessionValue(authCookieValue);
		        	// does this session id exist in the sessions table in the db? and with the given IP address?
		    	 	iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aIndivObj );
		    	 	if(iRetCode ==0){	
		    	 		// test validation with the UID in the table, the secret key, and a random 4 digit number provided in the cookie value
		    	 		iRetCode = m_IndBRLOObj.validateSessionValue( aIndivObj );
		    	 		
		        		if( iRetCode == 0){
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
		                	httpServletRequest.setAttribute("userprofile", aIndivObj);
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
		        	        		// if user is coming through urbanministry account creation process, then give them no role
		        	        		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
		        	        				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
		        	        	      	aIndivObj.setUserRoleID(-1);
		        	        		}else{
		        	        	      	aIndivObj.setUserRoleID(7);
		        	        		}
		        	                iRetCode = m_IndBRLOObj.updateIndividualHead( aIndivObj, aszSiteVersion );
		        	            }
		        	        }
		        	        
		        	        aIndivObj.processTokens();
		        	        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
		        		        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
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
		        	                	return m_BaseOrgAction.processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITINDIV ) ){
		        		        		return showIndEditProfile1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_VOLDASHBOARD ) ){
		        		        		return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEORG ) ) {
		        		        		return m_BaseOrgAction.showCreateOrgStep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_SHOWCONTACTS ) && iOrgNID > 0 ){
		        		        		return m_BaseOrgAction.showOrgContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITORG ) && iOrgNID > 0 ){
		        		        		return m_BaseOrgAction.shownonpeditstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEOPPORT ) && iOrgNID > 0 ){
		        		        		return m_BaseOrgAction.showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWORG ) && iOrgNID > 0 ){
		        		        		return m_BaseOrgAction.shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGMANAGEMENT ) ){
		        		        		return m_BaseOrgAction.showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWOPP ) && iOrgNID > 0 && iOppNID > 0){
		        		        		return m_BaseOrgAction.showOpportunityPreview(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		        	}
		        	        	}
		        	    	}
		        	        
		        	        if(iRetCode2 != -222){
		        	        	// if the user is coming through facebook, we will still have their information, but we need to ask the user for more information and forward them to drupalaccount page
		        		        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
		        		        	session.setAttribute("usecase", "volunteer");
		        		        	httpServletRequest.setAttribute("userprofile", aIndivObj);
		        		        	if(aIndivObj.getUSPPersonality() == ""){
		        		        		return actionMapping.findForward( "myministryopps" );
		        		        	}
		        		        	else {
		        		        		return actionMapping.findForward( "personalityresults" );
		        		        	}
		        		        }
		        	        }
		        	        
		        	        ArrayList aList = new ArrayList();
		        	        if(iRetCode2 != -222){
		        	            ArrayList aListAdmin = new ArrayList();
		        	            ArrayList aListContact = new ArrayList();
	
		        	            iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID()); 
		        	            iRetCode = m_OrgBRLOObj.getOrgListForContact( aListContact, aIndivObj.getUserUID()); 
		        	            
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
		        	    	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
		        	    	    	    while(itr_aList.hasNext()){
		        	    	    	    	orgElement_aList = itr_aList.next();
		        	    	    	    	iNIDincluded = orgElement_aList.getORGNID();
		        	    	    	    	if(iNID==iNIDincluded){
		        	    	    	    		b_inList=true;
		        	    	    	    	}
		        	    	    	    }
		        	    	    	    if(b_inList==false){
		        	    	    	    	aList.add(orgElement);
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
		        	    	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
		        	    	    	    while(itr_aList.hasNext()){
		        	    	    	    	orgElement_aList = itr_aList.next();
		        	    	    	    	iNIDincluded = orgElement_aList.getORGNID();
		        	    	    	    	if(iNID==iNIDincluded){
		        	    	    	    		b_inList=true;
		        	    	    	    	}
		        	    	    	    }
		        	    	    	    if(b_inList==false){
		        	    	    	    	aList.add(orgElement);
		        	    	    	    }
		        	    	    	}
		        	    	    }
		        		        // test aList to see if any entries have a portalname
		        		        if(aList.size()>0){	
		        					if(httpServletRequest.getHeader("host").contains("churchvol.org") ||httpServletRequest.getHeader("host").contains("churchvolunteering.org") ){
		        			        	aszPortal=m_OrgBRLOObj.doesPortalExistForOrgs( aList );
		        					}
		        		        }
		        		        // if any of the orgs has a portal, set up to redirect to that portal
		        		        if(aList.size()>0 && aszPortal.length()>0){
		        		        	bPortalSite = true;
		        		        }
		        		        
		        		        if(	bPortalSite == true ){
		        		        	httpServletRequest.setAttribute("redirectportal", aszPortal);
		        		            if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
		        		            	httpServletRequest.setAttribute("usecase", "volunteer");
		        		   		     	httpServletRequest.setAttribute("redirectpage", "volunteerportalsite");
		        		            	return actionMapping.findForward( "mappingpage" );
		        		            }else{
		        		            	httpServletRequest.setAttribute("usecase", "organization");
		        		            	httpServletRequest.setAttribute("redirectpage","portalorgmanagement");
		        		            	return actionMapping.findForward( "mappingpage" );
		        		            }
		        		        }
		        	        }
	        	            httpServletRequest.setAttribute( "orglist", aList );
		        	        if (aIndivObj.getUSPVolunteer().length() < 1 && aIndivObj.getUSPVolunteerTID() < 1 
		        	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser) )
		        	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser) )
		        	        ){
		        	        	aIndivObj.setUSPVolunteerTID(iVolDirectorytid); // if the user is an indiv or both, by default, have this public listing checked Yes
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
		        	         	// NEW - redirect user to mapping page for use of rails app for later account creation stages
		            			if(aIndivObj.getUserProfileNID() < 1){ 
		            				// this user is an old drupal user-only; need to take through partial account creation process
		            				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
		            				//		as well as an insert into the rails db
		            				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		            		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
		            			}
		        		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
		        		 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
		        	         	return actionMapping.findForward("mappingpage");
		        	    //	        	        	return actionMapping.findForward( "createaccount2" );
		        	       }
		        	        
		        	        if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
		        	        	session.setAttribute("usecase", "volunteer");
		        	        	return actionMapping.findForward( "volunteerdashboard" );
		        	        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Both")){
		        	        	session.setAttribute("usecase", "volorg");
		        	        	return actionMapping.findForward( "volunteerdashboard" ); 
		        	        }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
		        	        	session.setAttribute("usecase", "organization");
		        	            //httpServletRequest.setAttribute("organization", aOrgInfoObj);
		        	         	String aszOrgNid="";
		        	         	session.setAttribute("orgmanagementnid", aszOrgNid);
		        	         	return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//	        	        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
		        	        }
		        	        return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		        		}
		    	 	}
	        	}         			
	    	}
        }
    	if(b_mobile==true){
          	return actionMapping.findForward( "showlogin" );
       	}else{
       		return actionMapping.findForward( "loginstatus" );
       	}
    }
    // end-of method processLogin()
    
    
    
    
    
    
    
    
    
    
    
    /**
     * process login page
     * This is a test version of processLogin for Facebook Spec 1.11, and is NOT currently live code
     */
     public ActionForward processLoginTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0, iRetCode2=0 ;
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
      	session.setAttribute("drupalsession","");  // Storing Value into session Object
         // get individual data from web form
      	iRetCode = m_IndActHelp.getLoginDataFromForm(oFrm, aIndivObj);

         aIndivObj.setUSPPasswordInternal(aIndivObj.getUSPPassword());
      	httpServletRequest.setAttribute("userprofile", aIndivObj);
         if(iRetCode != 0){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
         	return actionMapping.findForward( "createaccount1" );
         }

         allocatedIndBRLO( httpServletRequest );
         // register new individual
         iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
         if( (iRetCode != 0) && (iRetCode != -222) ){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
           	if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
             	httpServletRequest.setAttribute("userprofile", aIndivObj);
             	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
             	return actionMapping.findForward( "personalitytest4" );
           	}else{
           		return actionMapping.findForward( "showlogin" );
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
         	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 1);
         	
         	if(aIndivObj.getUSPPersonality() == ""){
         		iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
         		iRetCode = m_IndBRLOObj.calculatePersonalityFacets(aIndivObj);
         	}
         	
         	String aszTemp = session.getAttribute("FB_User_ID").toString();
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
              			return actionMapping.findForward( "personalitytest4" );
              		}
              	}else{
              		aIndivObj.appendErrorMsg("facebook api key and/or secret key was not correctly set. ");
                 	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
              		return actionMapping.findForward( "personalitytest4" );
              	}
              }
        	 	}
         	if(iRetCode2!=-222){
        		// if user is coming through urbanministry account creation process, then give them no role
        		if ( httpServletRequest.getHeader("host").contains("urbanministry.christianvolunteering.org")  
        				||  httpServletRequest.getHeader("host").contains("um.cv.org")  ){
        	      	aIndivObj.setUserRoleID(-1);
        		}else{
        	      	aIndivObj.setUserRoleID(7);
        		}
                 iRetCode = m_IndBRLOObj.updateIndividualHead( aIndivObj, aszSiteVersion );
             }
         }
         
         aIndivObj.processTokens();
         if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
 	        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
 	        allocatedOrgBRLO( httpServletRequest );
 	    	boolean tmp = m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest );
 	    	if(null != aSessDat){
  	        	int iOppNID = aSessDat.getOppNID();
 	        	int iOrgNID = aSessDat.getOrgNID();
 	        	String aszToken = aSessDat.getTokenKey();
 	        	if( 
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IWANTTOHELP ) && iOppNID > 0) ||
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_PARTNERHELPSIGNIN ) && iOppNID > 0) ||
 	        			(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_IVOLHELPSIGNIN ) && iOppNID > 0)
 	        		){
                 	return m_BaseOrgAction.processIWantToHelp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITINDIV ) ){
 	        		return showIndEditProfile1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_VOLDASHBOARD ) ){
 	        		return showVolunteerDashboard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEORG ) ) {
 	        		return m_BaseOrgAction.showCreateOrgStep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_SHOWCONTACTS ) && iOrgNID > 0 ){
 	        		return m_BaseOrgAction.showOrgContacts(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_EDITORG ) && iOrgNID > 0 ){
 	        		return m_BaseOrgAction.shownonpeditstep1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_CREATEOPPORT ) && iOrgNID > 0 ){
 	        		return m_BaseOrgAction.showOrgAddOpp1(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWORG ) && iOrgNID > 0 ){
 	        		return m_BaseOrgAction.shownonpvieworg(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGMANAGEMENT ) ){
 	        		return m_BaseOrgAction.showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}else if(aszToken.equalsIgnoreCase( AppSessionDTO.TOKEN_ORGVIEWOPP ) && iOrgNID > 0 && iOppNID > 0){
 	        		return m_BaseOrgAction.showOpportunityPreview(actionMapping, actionForm, httpServletRequest, httpServletResponse);
 	        	}
         	}
     	}
         
         if(iRetCode2 != -222){
         	// if the user is coming through facebook, we will still have their information, but we need to ask the user for more information and forward them to drupalaccount page
 	        if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
 	        	session.setAttribute("usecase", "volunteer");
 	        	httpServletRequest.setAttribute("userprofile", aIndivObj);
 	        	if(aIndivObj.getUSPPersonality() == ""){
 	        		return actionMapping.findForward( "myministryopps" );
 	        	}
 	        	else {
 	        		return actionMapping.findForward( "personalityresults" );
 	        	}
 	        }
         }
         
         ArrayList aList = new ArrayList();
         if(iRetCode2 != -222){
 	        iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aIndivObj.getUserUID()); // 2009-07-20 - will eventually be uid-ized
 	        httpServletRequest.setAttribute( "orglist", aList );
         }
         String aszTemptmep=aIndivObj.getUSPPassword();
	        if (aIndivObj.getUSPVolunteer().length() < 1 && aIndivObj.getUSPVolunteerTID() < 1 
	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser) )
	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser) )
	        ){
	        	aIndivObj.setUSPVolunteerTID(iVolDirectorytid); // if the user is an indiv or both, by default, have this public listing checked Yes
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
          	// NEW - redirect user to mapping page for use of rails app for later account creation stages
 			if(aIndivObj.getUserProfileNID() < 1){ 
				// this user is an old drupal user-only; need to take through partial account creation process
				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
				//		as well as an insert into the rails db
				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
			}
 	        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
 	 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
          	return actionMapping.findForward("mappingpage");
     //         	return actionMapping.findForward( "createaccount2" );
        }
         
         if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
         	session.setAttribute("usecase", "volunteer");
         	return actionMapping.findForward( "volunteerdashboard" );
         }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Both")){
         	session.setAttribute("usecase", "volorg");
         	return actionMapping.findForward( "volunteerdashboard" ); 
         }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
         	session.setAttribute("usecase", "organization");
          	String aszOrgNid="";
          	session.setAttribute("orgmanagementnid", aszOrgNid);
          	return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//        	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
         }
         return actionMapping.findForward( "volunteerdashboard" );
     }
     // end-of method processLogin()
    
 	/*
      * show manage national affiliation/association page
      */
      public ActionForward showAssocManagement(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
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
			// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
	        if(aszPortal.length()>0){
	        	if(aszPortalNID.length()==0){
	    			return actionMapping.findForward("404");
	        	}
	        }
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                 return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
       	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aIndivObj) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                 return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
          String aszOrgNid = m_BaseHelp.getFormData(oFrm,"orgnid");
          int iOrgNid = m_BaseHelp.convertToInt( aszOrgNid );
          if(iOrgNid < 1){
       		aszOrgNid = "" + aSessDat.getOrgNID();
          }

  		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
  			  if(null != aSessDat){
  				  aSessDat.setOrgNID( aszOrgNid );
  				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_ORGMANAGEMENT );
  			  }
  		     	httpServletRequest.setAttribute("userprofile", aIndivObj);
  		     	// NEW - redirect user to mapping page for use of rails app for later account creation stages
    			if(aIndivObj.getUserProfileNID() < 1){ 
    				// this user is an old drupal user-only; need to take through partial account creation process
    				// 1. go into the db and create the necessary table inserts to create a um_content_type_uprofile record,
    				//		as well as an insert into the rails db
    				// 2. THEN proceed to RAILS app create_basic, where they have to enter more data
    		        iRetCode = m_IndBRLOObj.upgradePlainDrupalUser( aIndivObj, aszSiteVersion );
    			}
  		        String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
  		 		httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
  		     	return actionMapping.findForward("mappingpage");
  		//  			  return actionMapping.findForward( "createaccount2" );
  		 }
          if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                 return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
          }
          
          // Organization list for user
          allocatedOrgBRLO( httpServletRequest );
          ArrayList aList = new ArrayList();
          ArrayList aListAdmin = new ArrayList();
          ArrayList aListContact = new ArrayList();

          if(aIndivObj.getNatlAssociationNID()>0 && bNatlAssoc==true){
              iRetCode = m_OrgBRLOObj.getOrgListForMember( aListAdmin, aIndivObj.getUserUID(), OrganizationInfoDTO.LOADBY_NATL_ASSOC); 
          }
          if(iRetCode!=0){
        	  return m_BaseOrgAction.showOrgManageListings(actionMapping, actionForm, httpServletRequest, httpServletResponse); 
//          	return showOrgManagement(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          }
          
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
  	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
  	    	    while(itr_aList.hasNext()){
  	    	    	orgElement_aList = itr_aList.next();
  	    	    	iNIDincluded = orgElement_aList.getORGNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aList.add(orgElement);
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
  	    	    Iterator<OrganizationInfoDTO> itr_aList = aList.iterator();
  	    	    while(itr_aList.hasNext()){
  	    	    	orgElement_aList = itr_aList.next();
  	    	    	iNIDincluded = orgElement_aList.getORGNID();
  	    	    	if(iNID==iNIDincluded){
  	    	    		b_inList=true;
  	    	    	}
  	    	    }
  	    	    if(b_inList==false){
  	    	    	aList.add(orgElement);
  	    	    }
  	    	}
  	    }
          httpServletRequest.setAttribute( "orglist", aList );

          
      	if(null != aSessDat){
         	aSessDat.setTokenKey(null);
         	aSessDat.setOrgNID(null);
         	aSessDat.setOppNID(null);
         	aSessDat.setSubdomain(null);
         	aSessDat.setSiteEmail(null);
     	}
          return actionMapping.findForward( "assocmanagement" );
      }
      // end-of method showAssocManagement()
     

    /**
     * detect if user is logged in
     */
     public ActionForward loggedIn(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	String referer=httpServletRequest.getHeader("referer");
     	// avoid infinite loop of continually loading anon; if & once anon sets the session attribute to mark that the user needs to log in, that should suffice
    	getPortalInfo( httpServletRequest, httpServletResponse);
    	String aszPortal="", aszPortalNID="";
        if(httpServletRequest.getParameter("portal") != null )	if(httpServletRequest.getParameter("portal").length() > 0)	aszPortal = httpServletRequest.getParameter("portal");
        if(session.getAttribute(aszPortal+"_nid") != null )	if(session.getAttribute(aszPortal+"_nid").toString().length() > 0)	aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
        // return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
		if(aszPortal.length()>0){
        	if(aszPortalNID.length()==0){
        		// return to mapping page and then redirect the user to just the main domain; this portal doesn't seem to exist
    			//httpServletRequest.setAttribute("redirectpage","noportalexists");
    			//return actionMapping.findForward("mappingpage");
    			return actionMapping.findForward("404");
        	}
        }
    	if (session.getAttribute ("drupalsession")=="login"){  
    		session.setAttribute ("drupalsession", "");
        	return actionMapping.findForward( "showlogin" );
    	}

     	String refererPage=referer;//"";
     	if(refererPage == null){ // to avoid NullPointerException, in case the getHeader has no referer, by default forward to the login page
     		return actionMapping.findForward( "anon" );
     	}
     	if( !( //if this method is not being called from the drupal installation cookie_login module's cookie_drupal.php file, then it should not log in the user
     			referer.equalsIgnoreCase("http://www.urbanministry.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("http://www.urbanministryjobs.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("http://www.um.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("http://www.um.org/um/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("https://www.urbanministry.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("https://www.urbanministryjobs.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("https://www.um.org/um/sites/all/modules/custom/cookie_login/cookie_drupal.php") ||
     			referer.equalsIgnoreCase("https://www.um.org/sites/all/modules/custom/cookie_login/cookie_drupal.php") 
     			)){
     		return actionMapping.findForward( "anon" );
     	}else{
     		refererPage="cookie_drupal.php";
     	}
     	int iRetCode=0, iRetCode2=0 ;
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
         // get individual data from web form
         iRetCode = m_IndActHelp.getLoginUserDataFromForm(oFrm, aIndivObj);
         if(aIndivObj.getUserUID()<1){
        	 return actionMapping.findForward("anon");
         }
         allocatedIndBRLO( httpServletRequest );
         iRetCode = m_IndBRLOObj.userLoggedIn( aIndivObj, aszSiteVersion );
         if( (iRetCode != 0) && (iRetCode == 777) ){ 
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
         	return actionMapping.findForward( "anon" );
         }
         
    	 if (refererPage.equalsIgnoreCase("cookie_drupal.php") && 
    			 aIndivObj.getUSPPassword().length() < 3 
    	 ) {
             iRetCode = m_IndBRLOObj.validateCookieLoginPwd(aIndivObj);
             if( iRetCode != 0){ 
                	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
              	return actionMapping.findForward( "anon" );
              }
    	 }
        
         iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
         if( (iRetCode != 0) && (iRetCode == 777) ){ //777=user was not already logged in w a uid from drupal site
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
         	return actionMapping.findForward( "anon" );
         }
         
         iRetCode2=iRetCode;
         aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
         aIndivObj.processTokens();
         if( iRetCode != -222 ){
 	        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
 	        allocatedOrgBRLO( httpServletRequest );
     	}
	        if (aIndivObj.getUSPVolunteer().length() < 1 && aIndivObj.getUSPVolunteerTID() < 1 
	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser) )
	        		&& ( ! aIndivObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser) )
	        ){
	        	aIndivObj.setUSPVolunteerTID(iVolDirectorytid); // if the user is an indiv or both, by default, have this public listing checked Yes
	        }
         //if the user has an account in drupal, but we do not have all the fields that are required for voleng
         if(iRetCode2 == -222){
           	m_BaseHelp.setFormData(oFrm,"username", aIndivObj.getUSPUsername() );
           	m_BaseHelp.setFormData(oFrm,"email1addr", aIndivObj.getUSPEmail1Addr() );
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
           	m_BaseHelp.setFormData(oFrm,"password1", aIndivObj.getUSPPasswordInternal() );
           	m_BaseHelp.setFormData(oFrm,"password2", aIndivObj.getUSPPasswordInternal() );
           	m_BaseHelp.setFormData(oFrm,"internalusertypecomment", aIndivObj.getUSPInternalUserTypeComment() );
         	m_BaseHelp.setFormData(oFrm,"upnid", "" + aIndivObj.getUserProfileNID() );
         	m_BaseHelp.setFormData(oFrm,"upvid", "" + aIndivObj.getUserProfileVID() );
         	m_BaseHelp.setFormData(oFrm,"uplid", "" + aIndivObj.getUserProfileLID() );
         	m_BaseHelp.setFormData(oFrm,"uid", "" + aIndivObj.getUserUID() );
         	aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
         	httpServletRequest.setAttribute("userprofile", aIndivObj);
           	
         	// set this actual user to be logged in in the session as well
 	        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
        }
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
     	return actionMapping.findForward( "auth" );
     }
     // end-of method loggedIn()

     

     
     /** 
      * show Facebook Training Page (iframe to UrbanMinistry)
      */
     public ActionForward showFacebookTrainingContent(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
      	  int iRetCode=0 ;
      	  HttpSession session=httpServletRequest.getSession();
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
      	  PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
         /*
          	 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
 			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
			 return actionMapping.findForward( "createaccount2" );
		 }
		 */
         if((aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK) && (aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGIN_PARTIAL)){
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          
         // forward to summary screen
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         allocatedOrgBRLO( httpServletRequest );
         ArrayList aList = new ArrayList();
         
         if(aIndivObj.getUSPPersonality() != "" || aIndivObj.getUSPPersonalityTID() > 0){
       	  return actionMapping.findForward( "facebooktrainingcontent" );
         }
         else {
       	  return actionMapping.findForward( "personalityoppredirect");
         }
         
      }
     // end-of method showFacebookTrainingContent()
     
     /** 
      * show Facebook Training Page (iframe to UrbanMinistry)
      * This is a test version of this function - Currently live on FYCSandbox but not FYC
      */
     public ActionForward showFacebookTrainingContentTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
      	  int iRetCode=0 ;
      	  HttpSession session=httpServletRequest.getSession();
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
      	  PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
        if(		(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK) 			&& 
        		(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGIN_PARTIAL)
        ){
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "personalityoppredirect" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	  DynaActionForm oFrm = (DynaActionForm)actionForm;
          
         // forward to summary screen
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         allocatedOrgBRLO( httpServletRequest );
         
       	  return actionMapping.findForward( "facebooktrainingcontent" );
      }
     // end-of method showFacebookTrainingContent()
     
     /** 
      * show Facebook personality type container (iframe to UrbanMinistry)
      * This is being used to tie in articles about Myers-Briggs on UrbanMinistry to the app
      */
     public ActionForward showFacebookTypeInfoContainer(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	 String umdst="";
    	 if(httpServletRequest.getParameter("umdst") != null){
    			umdst=httpServletRequest.getParameter("umdst");
    	}
    	 return actionMapping.findForward( "facebooktypeinfocontainer"); 
     }
     // end-of method showFacebookTypeInfoContainer()
     
     /**
      * show PersonalityTest Landing page
      */
     public ActionForward showPersonalityTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 , iRetCode2=0 ;
      	HttpSession session=httpServletRequest.getSession();
      	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	String retaketest = "false";
      	retaketest=m_BaseHelp.getFormData( oFrm, "retaketest" );
          
      	
 		// make sure that when they go to personalitytest, that they are logged in if the authentication cookie exists
		String aszIPAddress = httpServletRequest.getRemoteAddr();
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
	    	 		aIndivObj.setCookieAuthorize(PersonInfoDTO.COOKIE_USER);
	    	 		aIndivObj.setSessionIP(aszIPAddress);
	    	 		aIndivObj.setSessionValue(authCookieValue);
	    	 		// does this session id exist in the sessions table in the db? and with the given IP address?
	    	 		iRetCode = m_IndBRLOObj.IsSessionIDInSystem( aIndivObj );
	        		if( iRetCode == 0 ){
	        			// login the user
	        	        iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
	        	        if( (iRetCode != 0) && (iRetCode != -222) ){
	        	        }else{
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
		        	        aIndivObj.processTokens();
		        	        if( iRetCode3 == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode3 == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
		        		        iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
		        	    	}
	        	        }
	        		}
	        	}         			
	    	}
        }

      	
         // forward to summary screen
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         ArrayList aList = new ArrayList();
     	
         // retaketest acts as a flag to tell whether the user should be taken to the personality
         //  test or redirected to the ministry areas page.  If retaketest is true, we forward
         //  to the test, otherwise we look at the personality type.
         if(retaketest!=null){
         	if(retaketest.equalsIgnoreCase("true")){
             	return actionMapping.findForward( "personalitytest" );
         	}
         }
         //if we have a personality type, show the ministry area page, otherwise show the test
     	httpServletRequest.setAttribute("userprofile", aIndivObj);
         if(aIndivObj.getUSPPersonality().length()<1){
         	return actionMapping.findForward("personalitytest");
         }else{
         	return actionMapping.findForward( "personalityministryarea" );
         }
     }
     // end-of method showPersonalityTest()
      
     /**
      * show PersonalityTest2 page (processes Personaly Test page 1 first)    processPersonalityTestPage1
      */
     public ActionForward showPersonalityTest2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
     	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
         
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest" );
          }
          allocatedIndBRLO( httpServletRequest );

          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

       	return actionMapping.findForward( "personalitytest2" );
     }
     // end-of method showPersonalityTest2()
       
     /**
      * show PersonalityTest page3 page (processes Personaly Test page 2 first)    processPersonalityTestPage2
      */
     public ActionForward showPersonalityTest3(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = new PersonInfoDTO();

         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest2" );
          }
          allocatedIndBRLO( httpServletRequest );

          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalitytest3" );
     }
     // end-of method showPersonalityTest3()
        
     /**
      * show PersonalityTest page4 page (processes Personaly Test page 3 first)    processPersonalityTestPage3
      */
     public ActionForward showPersonalityTest4(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest3" );
          }
          allocatedIndBRLO( httpServletRequest );

          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalitytest4" );
     }
     // end-of method showPersonalityTest4()
         
     /**
      * show PersonalityTest page5 page (processes Personaly Test page 4 first)    processPersonalityTestPage4
      *  This function is not currently being used, as there are only 4 pages in the test now
      */
     public ActionForward showPersonalityTest5(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
     	PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest4" );
          }
          allocatedIndBRLO( httpServletRequest );

          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalitytest5" );
     }
     // end-of method showPersonalityTest5()
          
     /**
      * show PersonalityTest page6 page (processes Personaly Test page 5 first)    processPersonalityTestPage5
      * This function is not currently being used, as there are only 4 pages in the test now
      */
     public ActionForward showPersonalityTest6(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = new PersonInfoDTO();
         
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest5" );
          }
          allocatedIndBRLO( httpServletRequest );

          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalitytest6" );
     }
     // end-of method showPersonalityTest6()

     /**
      * show Personality Ministry Areas page (processes Personaly Test page 4 first)    processPersonalityTestPage4
      */
     public ActionForward showPersonalityMinistryAreas(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
     	if(null == aIndivObj){
     		aIndivObj = new PersonInfoDTO();
     	}
     	
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 1);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest4" );
          }
          allocatedIndBRLO( httpServletRequest );

          // Calculate personality type
          if(aIndivObj.getUSPPersonality() == ""){
          iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
          iRetCode = m_IndBRLOObj.calculatePersonalityFacets(aIndivObj);
          }
          
          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalityministryarea" );
     }
     // end-of method showPersonalityMinistryAreas()
     
     /**
      * show Personality Ministry Areas page (processes Personaly Test page 4 first)    processPersonalityTestPage4
      *  This function is used for the Facebook workflow from the 1.11 Spec, and is not currently live code
      */
     public ActionForward showPersonalityMinistryAreasTest(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
                  
     	if(null == aIndivObj){
     		aIndivObj = new PersonInfoDTO();
     	}
     	
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 1);
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalitytest4" );
          }
          allocatedIndBRLO( httpServletRequest );

          // Calculate personality type
          if(aIndivObj.getUSPPersonality() == ""){
          iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
          iRetCode = m_IndBRLOObj.calculatePersonalityFacets(aIndivObj);
          }
          
          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

     	return actionMapping.findForward( "personalityministryarea" );
     }
     // end-of method showPersonalityMinistryAreas()
     
     /**
      * show the Personality Ministry Areas 2 page 
      *  This function is used for the Facebook workflow from the 1.11 Spec, and is not currently live code
      */
     public ActionForward showPersonalityMinistryAreas2(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         
     	if(null == aIndivObj){
     		aIndivObj = new PersonInfoDTO();
     	}
     	
        httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	String aszSkipFirstPage = "1";
      	aszSkipFirstPage=m_BaseHelp.getFormData( oFrm, "page" );
      	if(aszSkipFirstPage.equals("2")){
             // load the subsequent personality test page
             iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
             allocatedOrgBRLO( httpServletRequest );
             return actionMapping.findForward( "personalityministryarea2" );
      	}

          // get personalitytest data from web form
          iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 2);
          //check for required data - the user should indicate at least 1 area he/she is looking for
          if(aIndivObj.getUSPLookingFor().length()<1){
             aIndivObj.setErrorMsg("You must indicate at least one type of ministry service area.\n");	
         	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             return actionMapping.findForward( "personalityministryarea" );
          }
          
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalityministryarea" );
          }
          allocatedIndBRLO( httpServletRequest );

          // Calculate personality type
          iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
          
          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

          return actionMapping.findForward( "personalityministryarea2" );
     }
     // end-of method showPersonalityMinistryAreas2()
     
     /**
      * show the Personality Ministry Areas page 
      * This function is used for Facebook Workflow from 1.11 Spec, and is NOT currently live code
      */
     public ActionForward showPersonalityMinistryAreas2Test(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	int iRetCode=0 ;
//     	HttpSession session=httpServletRequest.getSession();
    	//getPortalInfo( httpServletRequest, httpServletResponse);
      	HttpSession session=httpServletRequest.getSession();
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         
     	if(null == aIndivObj){
     		aIndivObj = new PersonInfoDTO();
     	}
     	
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	String aszSkipFirstPage = "1";
      	aszSkipFirstPage=m_BaseHelp.getFormData( oFrm, "page" );
      	if(aszSkipFirstPage.equals("2")){
             // load the subsequent personality test page
             iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
             allocatedOrgBRLO( httpServletRequest );

             return actionMapping.findForward( "personalityministryarea2" );
      	}
      	
      		//for the new workflow, we don't need to get the data from the form on this page
      		// since it is now seperate.  We also don't need to calculate the personality at this step.
      	
          // get personalitytest data from web form
          //iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 2);
          //check for required data - the user should indicate at least 1 area he/she is looking for
         /* if(aIndivObj.getUSPLookingFor().length()<1){
             aIndivObj.setErrorMsg("You must indicate at least one type of ministry service area.\n");	
         	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             return actionMapping.findForward( "personalityministryarea" );
          } */
          
          if(iRetCode != 0){ // there really shouldn't be an error with this, though...
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
          	return actionMapping.findForward( "personalityministryarea" );
          }
          allocatedIndBRLO( httpServletRequest );

          // Calculate personality type
          //iRetCode = m_IndBRLOObj.calculatePersonalityType(aIndivObj);
          
          // load the subsequent personality test page
          iRetCode = m_IndActHelp.fillPersonalityDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );

          return actionMapping.findForward( "personalityministryarea2" );
     }
     // end-of method showPersonalityMinistryAreas2()
     

     
     /**
      * process Personality Results (update user to add the personality type information)
      */
      public ActionForward processPersonalityResults(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	 int iRetCode=0 ;
//      	HttpSession session=httpServletRequest.getSession();
     	//getPortalInfo( httpServletRequest, httpServletResponse);
       	HttpSession session=httpServletRequest.getSession();
         if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
         	}
         }
      	 PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
         if(null == aIndivObj) {
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
          	}
         }
 		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
 		 }else
         if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
         	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
             	return actionMapping.findForward( "showlogin" );
         	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         	}
         }
         httpServletRequest.setAttribute("userprofile", aIndivObj);
      	 DynaActionForm oFrm = (DynaActionForm)actionForm;
      	
         // get individual's personality type data from the form
         iRetCode = m_IndActHelp.getPersonalityDataFromForm( oFrm, aIndivObj, 0);
         if(iRetCode != 0){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
  		  String aszRailsEdit = aszRailsPrefixPath + aszRailsEditPath;
  		  httpServletRequest.setAttribute("redirect", aszRailsEdit);
  	 	  return actionMapping.findForward("mappingpage");			  
//         	return actionMapping.findForward( "volacctedit1" );
         }
         allocatedIndBRLO( httpServletRequest );

         
         // update the user's personality data
         iRetCode = m_IndBRLOObj.updateIndividualHeadPersonality( aIndivObj );
         if(iRetCode != 0){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );

          	return actionMapping.findForward( "personalityministryarea2" );
         }
          
          
         // forward to summary screen
         iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
         allocatedOrgBRLO( httpServletRequest );
         ArrayList aList = new ArrayList();

      	 return actionMapping.findForward( "personalityresults" );
      }
      // end-of method processPersonalityResults()     
     
      /**
       * process Personality Publish (update the user profile's personality_published attribute)
       * This function is used to set the flag that determines whether we should ask the user to publish their results
       */
       public ActionForward processPersonalityPublish(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	 int iRetCode=0 ;
//      	HttpSession session=httpServletRequest.getSession();
     	//getPortalInfo( httpServletRequest, httpServletResponse);
       	HttpSession session=httpServletRequest.getSession();
/*     	getPortalInfo( httpServletRequest, httpServletResponse, session);
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
     			httpServletRequest.setAttribute("redirectpage","noportalexists");
     			return actionMapping.findForward("mappingpage");
         	}
         }*/
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
       	 PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aIndivObj) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
  		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
 			 /*
  			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
			 return actionMapping.findForward( "createaccount2" );
			 */
		 }else
          if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "showlogin" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
          httpServletRequest.setAttribute("userprofile", aIndivObj);
       	 DynaActionForm oFrm = (DynaActionForm)actionForm;
       	
          //update the personality_published field in the user profile
          allocatedIndBRLO( httpServletRequest );
          iRetCode = m_IndBRLOObj.updateIndividualHeadPersonalityPublished( aIndivObj );
          if(iRetCode != 0){
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
           	return actionMapping.findForward( "personalityresults" );
          }
           
           
          // forward to summary screen
          iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );
          ArrayList aList = new ArrayList();

       	 return actionMapping.findForward( "personalityresults" );
       }
       // end-of method processPersonalityResults()
     
      
      /**
       * show Personality Results (show the personality type information)
       */
       public ActionForward showPersonalityResults(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	  int iRetCode=0 ;
//       	HttpSession session=httpServletRequest.getSession();
      	//getPortalInfo( httpServletRequest, httpServletResponse);
        	HttpSession session=httpServletRequest.getSession();
/*      	getPortalInfo( httpServletRequest, httpServletResponse, session);
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
      			httpServletRequest.setAttribute("redirectpage","noportalexists");
      			return actionMapping.findForward("mappingpage");
          	}
          }*/
          if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "personalityredirect" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
       	  PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          if(null == aIndivObj) {
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "personalityredirect" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
   		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
 			 /*
  			 if(null != aSessDat){
				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_VOLDASHBOARD );
			 }
		     httpServletRequest.setAttribute("userprofile", aIndivObj);
			 return actionMapping.findForward( "createaccount2" );
			 */
		 }else
          if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
          	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
              	return actionMapping.findForward( "personalityredirect" );
          	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
          	}
          }
          httpServletRequest.setAttribute("userprofile", aIndivObj);
       	  DynaActionForm oFrm = (DynaActionForm)actionForm;
           
          // forward to summary screen
          iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
          allocatedOrgBRLO( httpServletRequest );
          ArrayList aList = new ArrayList();
          
          // If the user has a personality type set, show the results, otherwise redirect them
          if(aIndivObj.getUSPPersonality() != "" || aIndivObj.getUSPPersonalityTID() > 0){
        	  return actionMapping.findForward( "personalityresults" );
          }
          else {
        	  return actionMapping.findForward( "personalityredirect");
          }
          
       }
       // end-of method showPersonalityResults()     
      
      
     
     /**
      * FB user is already logged into our application, but re-taking the test (should not have to log in again on personality ministry areas 2)
      * 
      */
      public ActionForward updatePersonalityAccount(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	 int iRetCode=0, iRetCode2=0 ;
       	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
       	DynaActionForm oFrm = (DynaActionForm)actionForm;
      	HttpSession session=httpServletRequest.getSession();
          allocatedIndBRLO( httpServletRequest );
      	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 0);
         	 if(session.getAttribute("FB_User_ID")!=null){
         		 if(session.getAttribute("FB_User_ID").toString().length()>1){
         			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
         		 }
         	 }
         	 if(aIndivObj.getFacebookUID().length()<0){
         	    	aIndivObj.setFacebookUID( m_BaseHelp.getFormData( oFrm, "facebookuid" ));
         	 }
	 
          // attempt to update returning facebook app user
          aIndivObj.setUSPInternalComment( "facebook" );
          iRetCode = m_IndBRLOObj.updateIndividualHeadMinistryAreasTest( aIndivObj );//updateIndividualHead( aIndivObj );
      	httpServletRequest.setAttribute("userprofile", aIndivObj);
          if(iRetCode != 0){ // there was an error on update
             	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
               	return actionMapping.findForward( "personalityministryarea2" );
          }
          if(aIndivObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
          	session.setAttribute("usecase", "volunteer");
          	if(aIndivObj.getUSPPersonality() == ""){
          		return actionMapping.findForward( "personalityresults" );
          	}
          	else {
          		return actionMapping.findForward( "personalityresults" );
          	}
          }
        	return actionMapping.findForward( "personalityministryarea2" );
      }
      // end-of method updatePersonalityAccount()
      
        
        /**
         * Update only selected parts of the user profile depending on which page the call to this function
         *  comes from.  This is used in the Facebook application where we don't want to overwrite data.
         * 
         */
         public ActionForward updatePersonalitySelectFields(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
         {
         	 int iRetCode=0, iRetCode2=0 ;
         	 
         	 
          	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
          	HttpSession session=httpServletRequest.getSession();
             allocatedIndBRLO( httpServletRequest );
             
             // The personalityPage variable determines what page made the call to this function
             String personalityPage = "";
             personalityPage =m_BaseHelp.getFormData( oFrm, "personalitypage" );
             
             // Get the personality data from the form, using the appropriate parameter to get only the data
             // that exists on the page that called the function.
         	 if(personalityPage.equalsIgnoreCase("facebooktrainingcontent") || personalityPage.equalsIgnoreCase("ministryareas2")){
         	iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 3);
         	 }
         	 else if(personalityPage.equalsIgnoreCase("personalityministryarea") || personalityPage.equalsIgnoreCase("ministryopps")) {
         		iRetCode = m_IndActHelp.getPersonalityDataFromForm(oFrm, aIndivObj, 2);
         	 }
         	 
         	httpServletRequest.setAttribute("userprofile", aIndivObj);
            	

            	 if(session.getAttribute("FB_User_ID")!=null){
            		 if(session.getAttribute("FB_User_ID").toString().length()>1){
            			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
            		 }
            	 }
            	
            	 if(aIndivObj.getFacebookUID().length()<0){
            	    	aIndivObj.setFacebookUID( m_BaseHelp.getFormData( oFrm, "facebookuid" ));
            	 }
            	
   	 
             // attempt to update returning facebook app user
             aIndivObj.setUSPInternalComment( "facebook" );
             
            iRetCode = m_IndBRLOObj.updateIndividualHeadPersonalitySelectFields( aIndivObj, personalityPage );
        	httpServletRequest.setAttribute("userprofile", aIndivObj);
             
             if(iRetCode != 0){ // there was an error on update
                	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
                	//Must use the personalityPage to determine what page to load in the case of an error
                	if(personalityPage.equalsIgnoreCase("facebooktrainingcontent")){
                		return actionMapping.findForward( "facebooktrainingcontent" );
                    }
                    else if(personalityPage.equalsIgnoreCase("personalityministryarea")){
                    	return actionMapping.findForward( "personalityministryarea" );
                    }
                    else if(personalityPage.equalsIgnoreCase("personalityministryarea2")){
                    	return actionMapping.findForward("personalityministryarea2");
                    }
                    else if(personalityPage.equalsIgnoreCase("personalityministryopps")){
                    	return actionMapping.findForward("personalityministryopps");
                    }
                    else {
                    	return actionMapping.findForward("personalityministryarea");
                    }
                  	  	
             }
             
         	/*if(customizelink!=null){
                	if(customizelink.equalsIgnoreCase("true")  ){
                    	return actionMapping.findForward( "personalityresults" );
                	}
             }*/
         	
         	// Forward to the appropriate page based on the calling page
         	if(personalityPage.equalsIgnoreCase("facebooktrainingcontent")){
        		return actionMapping.findForward( "facebooktrainingcontent" );
            }
            else if(personalityPage.equalsIgnoreCase("personalityministryarea")) {
            	return actionMapping.findForward( "personalityresults" );
            }
            else if(personalityPage.equalsIgnoreCase("personalityministryarea2")){
            	return actionMapping.findForward("personalityresults");
            }
            else{ //if(personalityPage.equalsIgnoreCase("personalityministryopps")){
            	return actionMapping.findForward("personalityministryopps");
            }

         }
         // end-of method updatePersonalityInterestsSkills()
      
      /**
       * login a returning FB app user
       * - only loaded page-forwarding wise in a hidden iframe, so it doesn't necessarily matter where it maps the user to, regardless of success or not.
       */
       public ActionForward loginFBUser(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
       {
       	 int iRetCode=0, iRetCode2=0 ;
           PersonInfoDTO aIndivObj = new PersonInfoDTO();
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
          	HttpSession session=httpServletRequest.getSession();
           allocatedIndBRLO( httpServletRequest );
           if(session.getAttribute("facebookapikey")==null || session.getAttribute("facebooksecretkey")==null){
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
          	 if(session.getAttribute("FB_User_ID")!=null){
          		 if(session.getAttribute("FB_User_ID").toString().length()>1){
          			 aIndivObj.setFacebookUID(session.getAttribute("FB_User_ID").toString());
          		 }
          	 }
          	 //if(aIndivObj.getFacebookUID()<0){
          	 if(aIndivObj.getFacebookUID().length()<0){
          	    	aIndivObj.setFacebookUID( m_BaseHelp.getFormData( oFrm, "facebookuid" ));
          	 }
   			if(session.getAttribute("FB_Timestamp") != null ){
   				aIndivObj.setTimestamp(session.getAttribute("FB_Timestamp").toString());  // set init timestamp as a local session var for auth returning users
  			}
  		    if(aIndivObj.getTimestamp().length()<0){
  		 	    	aIndivObj.setTimestamp( m_BaseHelp.getFormData( oFrm, "timestamp" ));
  		 	 }
           }
           
           // attempt to log in returning facebook app user
           aIndivObj.setUSPInternalComment( "facebookapp" );
           iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
           
           if( (iRetCode != 0) && (iRetCode != -222) ){
             	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
           	return actionMapping.findForward( "placeholder" );
           }
           iRetCode2=iRetCode;

           // condition to check whether user is only partial, and in that case set to USER_LOGIN_PARTIAL instead
	     	AppSessionDTO aSessDat=null;
	       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
	        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
           if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
               aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
           }else{
               aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
           }
               
           aIndivObj.processTokens();
       	 iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
               
           httpServletRequest.setAttribute("userprofile", aIndivObj);
           if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
             	session.setAttribute("usecase", "volunteer");
           }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Both")){
             	session.setAttribute("usecase", "volorg");
           }else if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Organization")){
             	session.setAttribute("usecase", "organization");
           }
         	 return actionMapping.findForward( "placeholder" );
       }
       // end-of method loginFBUser()
       
      public ActionForward authorizeFacebookConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
System.out.println("triggered authorizeFacebookConnectClick");
			
			
			String aszRedirect = "";
			try{
		      	if(httpServletRequest.getParameter("redirect") != null ){
		      		if(httpServletRequest.getParameter("redirect").toString().length() > 0) 
		      			aszRedirect = httpServletRequest.getParameter("redirect").toString();
		      	}else if(session.getAttribute("redirect") != null ){
		      		if(session.getAttribute("redirect").toString().length() > 0) {
		      			aszRedirect = session.getAttribute("redirect").toString();
//		      			session.removeAttribute("redirect");
		      		}
		      	}
			}catch(NullPointerException e){
				System.out.println("null redirect");
			}
			if(aszRedirect.length()>0){
System.out.println("session.setAttribute(\"redirect\") to "+aszRedirect+"- authorizeFacebookConnectClick");
				session.setAttribute("redirect", aszRedirect);
			}

    	  allocatedIndBRLO(httpServletRequest);
    	  IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getFacebookConnectLogic(httpServletRequest.getHeader("host"), getBaseURL(httpServletRequest), aszSiteVersion);
    	  return authorizeExternalConnectClick(actionMapping, actionForm, httpServletRequest, httpServletResponse, connectLogic);
      }
      
      public ActionForward authorizeLinkedinConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	  allocatedIndBRLO(httpServletRequest);
    	  IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getLinkedinConnectLogic(httpServletRequest.getHeader("host"), getBaseURL(httpServletRequest), aszSiteVersion);
    	  return authorizeExternalConnectClick(actionMapping, actionForm, httpServletRequest, httpServletResponse, connectLogic);
      }
      
      private String getBaseURL(HttpServletRequest request) {
    	  return request.getScheme() + "://" + 
 	      request.getServerName() + ":" + 
   	      request.getServerPort() + 
   	      request.getContextPath() + "/";
      }
      
      private ActionForward authorizeExternalConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, IndividualsBRLO.ExternalConnectLogic connectLogic) {
    	  Token requestToken = connectLogic.getRequestToken();
    	  httpServletRequest.getSession().setAttribute(connectLogic.getRequestTokenSessionKey(), requestToken);

          ActionForward actionForward = new ActionForward();
          actionForward.setRedirect(true);
          String s = connectLogic.getOAuthService().getAuthorizationUrl(requestToken);
System.out.println("authExtConnect - connect logic string is "+s);          
          actionForward.setPath(s);
          return actionForward;    	  
      }
      
      public ActionForward processFacebookConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
System.out.println("triggered processFacebookConnectClick");    	
    	  allocatedIndBRLO(httpServletRequest);
    	  IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getFacebookConnectLogic(httpServletRequest.getHeader("host"), getBaseURL(httpServletRequest), aszSiteVersion);
    	  return processExternalConnectClick(actionMapping, actionForm, httpServletRequest, httpServletResponse, connectLogic);
      }
      
      public ActionForward processLinkedinConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
System.out.println("triggered processLinkedinConnectClick");
    	  allocatedIndBRLO(httpServletRequest);
    	  IndividualsBRLO.ExternalConnectLogic connectLogic = m_IndBRLOObj.getLinkedinConnectLogic(httpServletRequest.getHeader("host"), getBaseURL(httpServletRequest), aszSiteVersion);
    	  return processExternalConnectClick(actionMapping, actionForm, httpServletRequest, httpServletResponse, connectLogic);
      }
      
      /*
       * process Connect click
       */
       private ActionForward processExternalConnectClick(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, IndividualsBRLO.ExternalConnectLogic connectLogic) throws Exception
       {
System.out.println("inside processExternalConnectClick");    



	
String aszRedirect = "";
try{
  	if(httpServletRequest.getParameter("redirect") != null ){
  		if(httpServletRequest.getParameter("redirect").toString().length() > 0) 
  			aszRedirect = httpServletRequest.getParameter("redirect").toString();
  	}else if(session.getAttribute("redirect") != null ){
  		if(session.getAttribute("redirect").toString().length() > 0) {
  			aszRedirect = session.getAttribute("redirect").toString();
System.out.println("session.removeAttribute(\"redirect\")  - processExternalConnectClick");
  			session.removeAttribute("redirect");
  		}
  	}
}catch(NullPointerException e){
	System.out.println("null");
}
if(aszRedirect.length()>0)	System.out.println("		line 6705 inside processExternalConnectClick    aszRedirect is: "+aszRedirect);

    	   httpServletRequest.setAttribute("connectLogic", connectLogic);
    	   int iRetCode=0;
    	   String facebookUID;
    	   DynaActionForm oFrm = (DynaActionForm)actionForm;
    	   PersonInfoDTO aIndivObj = new PersonInfoDTO();
    	   aIndivObj.setProvider(connectLogic.getProvider());
    	   aIndivObj.setUSPInternalComment(connectLogic.getInternalComment());
           httpServletRequest.setAttribute("userprofile", aIndivObj);
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
             
             allocatedIndBRLO( httpServletRequest );
             
            String subdomainHost = httpServletRequest.getHeader("host");
            
            // Get the facebook user id, and check to see if it exists in um_content_type_uprofile
            //facebookUID = aIndivObj.getFacebookUID();        
            	
            //iRetCode = m_IndBRLOObj.isFacebookUserInSystem(facebookUID);

            
            Token accessToken = null;
            try{
            	accessToken = connectLogic.getAccessToken(
	            	(Token)httpServletRequest.getSession().getAttribute(connectLogic.getRequestTokenSessionKey()), 
	            	httpServletRequest.getParameter(connectLogic.getVerifierKey())
	            );
            }catch(java.lang.IllegalArgumentException ex){
                return showVolunteerDashboard( actionMapping, actionForm, httpServletRequest, httpServletResponse);             	
            }
 
            String id = connectLogic.getID(accessToken);
            
            connectLogic.setID(aIndivObj, id);
            connectLogic.setAccessToken(aIndivObj, accessToken);
            connectLogic.setAccessSecret(aIndivObj, accessToken);
            
            httpServletRequest.getSession().setAttribute(connectLogic.getAccessTokenSessionKey(), accessToken);
            
            // if the user id is not in our system, redirect to the Facebook Connect page
            if(connectLogic.isUserInSystem(id) != 0){
System.out.println("facebookconnectcreateaccount");            	
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             	return actionMapping.findForward("facebookconnectcreateaccount");
            }
            	
            // Load the user via the Facebook UID
            iRetCode = connectLogic.loadUser(aIndivObj);
System.out.println("connectLogic.loadUser(aIndivObj); iRetCode is "+iRetCode);            	
            if(iRetCode != 0){
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             	return actionMapping.findForward("facebookconnectcreateaccount");
            } 
            
            aIndivObj.setUSPInternalComment( connectLogic.getInternalCommentAlreadyConnected() );
            // Login the user
            iRetCode = m_IndBRLOObj.loginUser( aIndivObj, aszSiteVersion );
            if(iRetCode != 0){
            	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
             	return actionMapping.findForward("facebookconnectcreateaccount");
            }
            int iRetCode2=iRetCode; // used later in the churchvol mapping section, etc
            
            iRetCode = setChrisVolAuthCookieOn(httpServletRequest,httpServletResponse,aIndivObj,"login");
            
            aIndivObj.processTokens(); 
            
            if( iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL || iRetCode == PersonInfoDTO.USER_LOGINOK ){//iRetCode != -222 ){
                iRetCode = m_BaseServletABREObj.setCurrentUser( httpServletRequest, aIndivObj );
            }
            

System.out.println("6792  aszRedirect is: '"+aszRedirect+"'   processExternalConnectClick");
    		if(aszRedirect.length()>0){
    			if(aszRedirect.startsWith("/orgs/") || aszRedirect.startsWith("/org/")){
    				aszRedirect+=".jsp";
                	httpServletRequest.setAttribute("redirect", aszRedirect);
                	return actionMapping.findForward( "mappingpage" );
    			}else if(aszRedirect.endsWith("-apply")){
        	        String aszRailsApplyPage = aszRailsPrefixPath + "positions~" + aszRedirect.replaceAll("-", "~");
            		httpServletRequest.setAttribute("redirect",aszRailsApplyPage);
                 	return actionMapping.findForward("mappingpage");
    			}
    		}

    		// if the user came through churchvolunteering and owns an org with a portal, we need to map them to the portal churchmanagement page instead
	        allocatedOrgBRLO( httpServletRequest );

	        boolean showPortalInfo=false;
         	if(		httpServletRequest.getHeader("host").contains("churchvol.org")			||
         			httpServletRequest.getHeader("host").contains("churchvolunteering.org")	||
         			httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org") ||
         			httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org") ||
         		    httpServletRequest.getHeader("host").contains("ivolunteering.org") ||
         		    httpServletRequest.getHeader("host").contains("catholicvolunteering.org")
         	){
	        	showPortalInfo=true;
	        }
         	boolean bPortalSite = false;
         	if(		httpServletRequest.getHeader("host").contains("churchvol.org")	||
         			httpServletRequest.getHeader("host").contains("churchvolunteering.org")
         	){
         		bPortalSite=true;
         	}
            ArrayList aList = new ArrayList();
            if(iRetCode2 != -222){
    	        iRetCode = m_OrgBRLOObj.getOrgListForMember( aList, aIndivObj.getUserUID());
    	        httpServletRequest.setAttribute( "orglist", aList );
    	        // test aList to see if any entries have a portalname
    	        if(aList.size()>0){	
    				if(showPortalInfo==true ){
    		        	aszPortal=m_OrgBRLOObj.doesPortalExistForOrgs( aList );
    				}
    	        }
    	        // if any of the orgs has a portal, set up to redirect to that portal
    	        if(aList.size()>0 && aszPortal.length()>0){
    	        	showPortalInfo = true;
    	        }
    	        
            }
            if(	showPortalInfo == true ){
            	httpServletRequest.setAttribute("redirectportal", aszPortal);
                if(aIndivObj.getUSPSiteUseType().equalsIgnoreCase("Volunteer")){
                	httpServletRequest.setAttribute("usecase", "volunteer");
       		     	httpServletRequest.setAttribute("redirectpage", "volunteerportalsite");
                	return actionMapping.findForward( "mappingpage" );
                }else{
                	httpServletRequest.setAttribute("usecase", "organization");
                	httpServletRequest.setAttribute("redirectpage","portalorgmanagement");
                	return actionMapping.findForward( "mappingpage" );
                }
            }
            

System.out.println("aszRedirect is: '"+aszRedirect+"'   processExternalConnectClick");
    		if(aszRedirect.length()>0){
    			if(aszRedirect.startsWith("/orgs/") || aszRedirect.startsWith("/org/")){
    				aszRedirect+=".jsp";
                	httpServletRequest.setAttribute("redirect", aszRedirect);
                	return actionMapping.findForward( "mappingpage" );
    			}else if(aszRedirect.endsWith("-apply")){
        	        String aszRailsApplyPage = aszRailsPrefixPath + "positions~" + aszRedirect.replaceAll("-", "~");
            		httpServletRequest.setAttribute("redirect",aszRailsApplyPage);
                 	return actionMapping.findForward("mappingpage");
    			}
    		}
            return showVolunteerDashboard( actionMapping, actionForm, httpServletRequest, httpServletResponse); //?? where should we direct them if this is successful???

       }
       

       
  	  /*
       * show volunteer opportunity search 
       * oppsrch.do?method=showOppSearch1
       */
      public ActionForward showMyMinistryOpps(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
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
          	DynaActionForm oFrm = (DynaActionForm)actionForm;
          	String showhome = "false";
          	showhome=m_BaseHelp.getFormData( oFrm, "showhome" );
          	
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
            if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	
             		return actionMapping.findForward( "personalityoppredirect" );
             	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
             }
          	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
            if(null == aIndivObj) {
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "personalityoppredirect" );
             	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
            }
     		 if(aIndivObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
    		 }else
            if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
             	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
                 	return actionMapping.findForward( "personalityoppredirect" );
             	}else{
                    return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
             	}
            }
          	httpServletRequest.setAttribute("userprofile", aIndivObj);
          SearchParms aSParm = new SearchParms();
          httpServletRequest.setAttribute( "sparm", aSParm );
          
          if(showhome!=null){
           	if(showhome.equalsIgnoreCase("true") && (aIndivObj.getUSPPersonality().length()<1 && aIndivObj.getUSPPersonalityTID()<1) ){
               	return actionMapping.findForward( "home" );
           	}
           }
          /*
//        if((aIndivObj.getFacebookUID().length()<1)){// if the user does not have a facebook user id          
          if((aIndivObj.getUSPPersonality().length()<1 && aIndivObj.getUSPPersonalityTID()<1)){// if the user does not have a personality type, we should redirect them to the personality test and have them enter info, including FB user ID
           	return actionMapping.findForward( "personalityoppredirect" );
          }
          */
      	return actionMapping.findForward( "myministryopps" );
      }

     // start-of method showFacebookFriendsTab()
     public ActionForward showFacebookFriendsTab(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
      	  int iRetCode=0 ;
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
         return actionMapping.findForward( "facebookfriendstab" );
          
     }

     // start-of method showFacebookFriendsContainer()
     public ActionForward showFacebookFriendsContainer(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
      	  int iRetCode=0 ;
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
          return actionMapping.findForward( "facebookfriendscontainer" );
          
     }
     
     public ActionForward showFacebookConnectCreateAccount(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	 int iRetCode=0, iRetCode2=0 ;
         PersonInfoDTO aIndivObj = new PersonInfoDTO();
      	DynaActionForm oFrm = (DynaActionForm)actionForm;
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
        
     	return actionMapping.findForward("facebookconnectcreateaccount");//??????????????????
//        return actionMapping.findForward( "facebookconnectcreateaccount" );
     }

    
    //====== START Private Methods ===>
    //====== START Private Methods ===>
    //====== START Private Methods ===>

       /**
        * get portal information for page loading
     * @throws IOException 
        */
       private void getPortalInfo( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) {
     	  session=httpServletRequest.getSession();
	       	String aszPortal = "", aszFileLocation = "", aszPortalHeaderTags="", aszPortalHeader="", aszPortalCSS="", aszPortalFooter="",
       		aszPortalOrgName = "", aszRequestType="";
	          aszSiteVersion="default";
	          if(	httpServletRequest.getHeader("host").contains("churchvol.org")							||
	    				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
	    				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" )  ||
	    				httpServletRequest.getHeader("host").equalsIgnoreCase( "ivol.org:7001" )
	    			){
	    			aszSiteVersion="development";
	    		}else if(	httpServletRequest.getHeader("host").contains("staging-" ) 
	    			){
	    			aszSiteVersion="staging";
	    		}
                allocatedIndBRLO( httpServletRequest );
                aszRailsPrefixPath = m_IndBRLOObj.getRailsSitePrefix(httpServletRequest);
    	int iRetCode=0, iNID=0, iPortalUID=0;
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
	                	if(aszRequestType.length()>0){
	                     	session.setAttribute(aszPortal + "_type", aszRequestType);
	                	}
	             	}
                }
            }
        }
        try {
	          httpServletRequest.setAttribute("location", m_BaseHelp.getLocation(httpServletRequest));
	        }
	        catch(IOException e) {
	          e.printStackTrace();
	        }
       }

       private int setChrisVolAuthCookieOn(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,PersonInfoDTO aIndivObj, String aszUseCase){
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
			//cookieValue = m_IndBRLOObj.createSignature(aIndivObj.getUserUID(), aIndivObj.getUserUIDString(), aszIPAddress, PersonInfoDTO.COOKIE_USER);
			// not sure why wer are no longer using the createSignature, but rather are clearing the cookievalue and setting it to random hash stuff instead
			
			// replace previous HMAC_SHA1 encoding with MD5 encryption		
			int iRetCode=0,iRetCodeCookie=0;
			cookieValue = "";
			aIndivObj.setSessionIP(aszIPAddress);
			iRetCodeCookie = m_IndBRLOObj.setSessionValue(aIndivObj);
			if(iRetCodeCookie == 0){
				cookieValue = aIndivObj.getSessionValue();
			
				Cookie cookie = new Cookie ("chrisvolAuth",cookieValue);
				cookie.setDomain(aszDomain);
				cookie.setMaxAge(expireTime);
				cookie.setPath("/");
				if(aszUseCase.equalsIgnoreCase("login")){
			        // test if this is a full user or not; could have signed up through FB app, or create account process and still be partial
			     	AppSessionDTO aSessDat=null;
			       	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
			        iRetCode = m_IndBRLOObj.testFullUser( aIndivObj, aSessDat.getTokenKey() );
			        if(iRetCode == PersonInfoDTO.USER_LOGIN_PARTIAL){
			            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGIN_PARTIAL );
			        }else{
			            aIndivObj.setUserIsLoggedIntoSystem( PersonInfoDTO.USER_LOGINOK );
			        }
		            if(iRetCodeCookie == 0){
		            	httpServletResponse.addCookie(cookie);
		            	aIndivObj.setCookieAuthorize( PersonInfoDTO.COOKIE_USER );
		            	iRetCodeCookie = m_IndBRLOObj.setSessionIDInSystem(aIndivObj);
		            }
			        return iRetCode;
				}else{
					if(iRetCodeCookie == 0){
						httpServletResponse.addCookie(cookie);
						aIndivObj.setCookieAuthorize( PersonInfoDTO.COOKIE_USER );
						iRetCodeCookie = m_IndBRLOObj.setSessionIDInSystem(aIndivObj);
					}
				}
			}
			return 0;
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
	* allocate business rule layes object for application brlo 
	*/
	private void allocatedApplBRLO( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_ApplBRLOObj){
			m_ApplBRLOObj = new ApplicationCodesBRLO();
			m_ApplBRLOObj.setBaseAppRef( m_BaseServletABREObj.getAppServer( aRequest ) );
		}
	}
	// end-of method allocatedIndBRLO()



	//====== END Private Methods ===>
    //====== END Private Methods ===>
    //====== END Private Methods ===>

	//====== START System ADMIN Methods ===>
	//====== START System ADMIN Methods ===>
	//====== START System ADMIN Methods ===>

	/*
    * show run SQL statements page
    */
    public ActionForward showRunSQLStatements(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
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
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aIndivObj) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
        if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
        if(false == aIndivObj.IsAuthAccessTo(PersonInfoDTO.AUTH_ADMIN)){
            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
        	return actionMapping.findForward( "myacctsumm1" );
        }
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
    	return actionMapping.findForward( "showrunsql" );
    }
    // end-of method showRunSQLStatements()

	/*
    * show system administration page
    */
    public ActionForward showSysAdmin1(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
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
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
        if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
     	PersonInfoDTO aIndivObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
        if(null == aIndivObj) {
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
        if(aIndivObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
        	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
            	return actionMapping.findForward( "showlogin" );
        	}else{
                return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
         		//return actionMapping.findForward( "loginstatus" );
        	}
        }
        if(false == aIndivObj.IsAuthAccessTo(PersonInfoDTO.AUTH_ADMIN)){
            iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
        	return actionMapping.findForward( "myacctsumm1" );
        }
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
    	return actionMapping.findForward( "showsysadmin1" );
    }
    // end-of method showSysAdmin1()


    
    /**
	* show send email page
	*/
	public ActionForward showSendEmail(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
		int iRetCode=0;
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
        allocatedIndBRLO( httpServletRequest );
		EmailInfoDTO aHeadObj = new EmailInfoDTO();
    	DynaActionForm oFrm = (DynaActionForm)actionForm;
    	String aszSMTPServer = m_BaseHelp.getFormData( oFrm, "education" );
    	if(aszSMTPServer.length() < 5){
            aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
    	}
    	String aszSMTPAuthenticate = m_BaseHelp.getFormData( oFrm, "addrline2" );
    	String aszSmtpUserName = m_BaseHelp.getFormData( oFrm, "addrline1" ); 
    	if(aszSmtpUserName.length() < 2){
    		aszSmtpUserName=m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
    	}
    	String aszTo = m_BaseHelp.getFormData( oFrm, "authtokens" ); 
    	if(aszTo.length() < 2){
    		m_IndBRLOObj.getSitePropertyValue("mail.smtp.defaultto");
    	}
    	String aszFrom = m_BaseHelp.getFormData( oFrm, "configtokens" ); 
    	if(aszFrom.length() < 2){
    		aszFrom= m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
    	}
    	String aszFromPassword = m_BaseHelp.getFormData( oFrm, "personcomment" ); 
    	if(aszFromPassword.length() < 2){
    		aszFromPassword= m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
    	}
    	String aszSubject = m_BaseHelp.getFormData( oFrm, "personinternalcomment" ); 
    	if(aszSubject.length() < 2){
    		aszSubject="test email messge subject 1";
    	}
    	String aszMessage = m_BaseHelp.getFormData( oFrm, "volresume" ); 
    	if(aszMessage.length() < 2){
    		aszMessage="test email messge body 1";
    	}
    	httpServletRequest.setAttribute("emailinfo", aHeadObj);

    	aHeadObj.setEmailSMTPServerName( aszSMTPServer );
    	aHeadObj.setSMTPUserName( aszSmtpUserName );
    	aHeadObj.setEmailFromUser( aszFrom );
    	aHeadObj.setEmailFromUserPassword( aszFromPassword );
    	aHeadObj.setEmailToUser( aszTo );
    	aHeadObj.setEmailSubjectText( aszSubject );
    	aHeadObj.setEmailBodyText( aszMessage );
    	if(aszSMTPAuthenticate.equalsIgnoreCase("Y")){
        	aHeadObj.setSMTPAuthRequired( true );
    	} else {
        	aHeadObj.setSMTPAuthRequired( false );
    	}

    	String aszSendEmailOK = m_BaseHelp.getFormData( oFrm, "employment" );
    	if(aszSendEmailOK.equalsIgnoreCase("Y")){
    		aszSendEmailOK="N";
    		iRetCode = m_IndBRLOObj.sendEmailMSG( aHeadObj );
    		if(0 == iRetCode) aHeadObj.appendErrorMsg( "email message sent OK!" );
    	} else {
        	aHeadObj.appendErrorMsg( "To Send email message the Send Email value must be Yes" );
    	}
		return actionMapping.findForward( "emailtestentry" );
    }
    // end-of method showSendEmail()

	//====== END   System ADMIN Methods ===>
	//====== END   System ADMIN Methods ===>
	//====== END   System ADMIN Methods ===>

	
	
	// LEGACY
	
	   
    public ActionForward showForgotPassword(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
   	return actionMapping.findForward( "forgotpassword" );
    }
     public ActionForward processIVolForgotPassword(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
       	HttpSession session=httpServletRequest.getSession();
     	AppSessionDTO aSessDat=null;
   	aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   	aSessDat.setTokenKey( AppSessionDTO.TOKEN_IVOLFORGOTPWD );
   	return processForgotPassword(actionMapping, actionForm, httpServletRequest, httpServletResponse);

     }
    /**
     * processForgotPassword page
     */
     public ActionForward processForgotPassword(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	int iRetCode=0 ;
     	HttpSession session=httpServletRequest.getSession();
       PersonInfoDTO aIndivObj = new PersonInfoDTO();
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
        // get individual data from web form
        iRetCode = m_IndActHelp.getForgotPasswordDataFromForm(oFrm, aIndivObj);
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
        	return actionMapping.findForward( "showregistration" );
        }
        
        allocatedIndBRLO( httpServletRequest );
        aIndivObj.processTokens();
        
        iRetCode = m_IndBRLOObj.loadUserByOption( aIndivObj, PersonInfoDTO.LOADBY_USERNAME, aszSiteVersion );

        if(iRetCode == -1){
           	m_BaseHelp.setFormData(oFrm,"errormsg", aIndivObj.getErrorMsg() );
           	return actionMapping.findForward( "nopwdconfirm" );
         }         
        String aszPassword = aIndivObj.getUSPPassword();

        String aszToken;
        AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
    	if(null != aSessDat){
        	aszToken = aSessDat.getTokenKey();
    	}
    	
    	// fills data into the object
        iRetCode = m_IndActHelp.fillIndividualDataForm(oFrm, aIndivObj);
        
		iRetCode=0;
       allocatedIndBRLO( httpServletRequest );
		EmailInfoDTO aHeadObj = new EmailInfoDTO();
   	oFrm = (DynaActionForm)actionForm;
   	
   	// need to load the form now.
       String aszSMTPServer= m_IndBRLOObj.getSitePropertyValue("mail.smtp.host");
   	String aszSmtpUserName = m_IndBRLOObj.getSitePropertyValue("mail.smtp.from");
   	String aszFromPassword = m_IndBRLOObj.getSitePropertyValue("mail.smtp.userpassword");
   	String aszEmailMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_EMAIL);
   	String aszEmailSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_EMAIL);
   	String aszDomMain = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
   	String aszDomSecondary = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN);
   	String aszDomMainShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN_SHORT);
   	String aszDomSecondaryShort = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SECONDARY_DOMAIN_SHORT);
   	String aszSiteOrgName = m_IndBRLOObj.getSitePropertyValue(ABREAppServerDef.APP_SITE_ORGNAME);
   	
   	String aszFrom = "";
   	String aszSubject = "";
   	if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLFORGOTPWD )){
       	aszFrom = aszEmailSecondary ; 
       	aszSubject = "Password for " + aszDomSecondaryShort; 
   	}else{
       	aszFrom = aszDomMain ; 
       	aszSubject = "Password for " + aszDomMainShort; 
   	}
   	
   	aHeadObj.setEmailSMTPServerName( aszSMTPServer );
   	aHeadObj.setEmailFromUserPassword( aszFromPassword );
   	aHeadObj.setSMTPUserName( aszSmtpUserName );
   	aHeadObj.setEmailFromUser( aszFrom );
   	aHeadObj.setEmailSubjectText( aszSubject );

   	String aszSMTPAuthenticate = "Y";

   	String aszTo =  aIndivObj.getUSPEmail1Addr(); 

   	String aszVolFirstN = aIndivObj.getUSPNameFirst();
   	String aszVolLastN = aIndivObj.getUSPNameLast(); 

       String msgTextAddress = "Dear " + aszVolFirstN + " " + aszVolLastN + ",\n\n";
   	
       String msgTextIntro="";
       if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLFORGOTPWD )){
           msgTextIntro = "Following is your password with " + aszDomSecondaryShort +":";
   	}else{
           msgTextIntro = "Following is your password with " + aszDomMainShort + ":";
   	}
       
       String msgText="";
       if(aSessDat.getTokenKey().equalsIgnoreCase(AppSessionDTO.TOKEN_IVOLFORGOTPWD )){
           msgText = msgTextAddress + msgTextIntro + 
       	"\n\nPassword:  " + aszPassword 
       	+ "\n\n----------------------------------------\n\n" + 
       	"\nThis email has been sent to you through " + aszDomSecondary + 
       	"\nIf you have any questions, please email us at " + aszEmailSecondary + 
       	"\nThank you for using " + aszDomSecondaryShort + ", powered by " + aszSiteOrgName + ".\n" + 
       	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";
   	}else{
           msgText = msgTextAddress + msgTextIntro + 
       	"\n\nPassword:  " + aszPassword 
       	+ "\n\n----------------------------------------\n\n" + 
       	"\nThis email has been sent to you through " + aszDomMain + 
       	"\nIf you have any questions, please email us at " + aszEmailMain + 
       	"\nThank you for using " + aszDomMainShort + ", powered by " + aszSiteOrgName + ".\n" + 
       	"\nSincerely,\nThe " + aszSiteOrgName + " Staff";
   	}

   	httpServletRequest.setAttribute("emailinfo", aHeadObj);

   	aHeadObj.setEmailToUser( aszTo );
   	aHeadObj.setEmailBodyText( msgText );
   	aHeadObj.setEmailVolFN( aszVolFirstN );
   	aHeadObj.setEmailVolLN( aszVolLastN );
       	aHeadObj.setSMTPAuthRequired( true );

       	
  	if(aszSMTPAuthenticate.equalsIgnoreCase("Y")){
       	aHeadObj.setSMTPAuthRequired( true );
   	} else {
       	aHeadObj.setSMTPAuthRequired( false );
   	}

   	String aszSendEmailOK = "Y";//m_BaseHelp.getFormData( oFrm, "employment" );
   	if(aszSendEmailOK.equalsIgnoreCase("Y")){
   		aszSendEmailOK="N";
   		iRetCode = m_IndBRLOObj.sendEmailMSG( aHeadObj );
   		if(0 == iRetCode) aHeadObj.appendErrorMsg( "Your password has been sent." );
   		// 2006-08-17
   		// need to clear out the Email Obj here, rather than store it all
   	} else {
   		aHeadObj.appendErrorMsg("You chose to cancel.");
   	}
   	
   	//aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
   	return actionMapping.findForward( "passwordconfirm" );
   	
    }
    // end-of method processForgotPassword()

     public ActionForward showPasswordConfirm(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
    	return actionMapping.findForward( "passwordconfirm" );
     }
      public ActionForward showNoPwdConfirm(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
     	return actionMapping.findForward( "nopwdconfirm" );//showlogin
      }

      /**
      * process individual registration page - LEGACY, I THINK
      */
      public ActionForward processRegistration(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {
      	return actionMapping.findForward( "createaccount1" );
      }


    /**
     * Update just the personality type and the ministry interests and skills (everything from ministryAreas page)
     * This function can probably be removed since I (Heather) wrote a more generic function (updatePersonalitySelectFields)
     */
     public ActionForward updatePersonalityInterestsSkills(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
     {
     	 int iRetCode=0, iRetCode2=0 ;

       	return actionMapping.findForward( "personalityministryopps" );
     }
     // end-of method updatePersonalityInterestsSkills()
     
     /**
      * Update only the personality type information and the ministry learning interests (from the ministryAreas2 page)
      * This function can probably be removed since I (Heather) wrote a more generic function (updatePersonalitySelectFields)
      */
      public ActionForward updatePersonalityLearningInterests(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
      {

        	return actionMapping.findForward( "facebooktrainingcontent" );
      }
      // end-of method updatePersonalityInterestsSkills()
	
	
	//====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>
   	// NEW - redirect user to mapping page for use of rails app for later account creation stages
//  	private String aszRailsEditBasic = "profiles~mine~edit_basic";
//	private String aszRailsAccountCreatePage = "profiles~mine~create_basic";
  	private String aszRailsPrefixPath = "";
  	private static final String aszRailsEditPath = "profiles~mine~edit";
  	private static final String aszRailsEditBasicPath = "profiles~mine~edit_basic";
	private static final String aszRailsAccountCreatePath = "profiles~mine~create_basic";
	private static final String aszRailsAccountCreatePageLEGACY = "cor~voleng~profiles~mine~create_basic";
//
  	private static final String aszOrganizationUser = "organization";
	private static final String aszChurchUser = "church";
	private static final String aszFullUser = "users,uprofile,location,phone";
	private static final String aszProfileLocationUser = "users,uprofile,location";
	private static final String aszProfileUser = "users,uprofile";
	private static final String aszDrupalUser = "users";
	private static final int iVolDirectorytid = 8864;

	private PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
	private static final int iHurrSandyOrgNID = 494934;
	private static final int iDisasterReliefOrgNID = 511070;
	private static final int iCityVisionNID = 73734;

	private HttpSession session = null;
	private String aszLoggedInStatus = ""; 
	private String aszSiteVersion = null;
	
	//private OrganizationActions m_BaseOrgAction = new OrganizationActions();
	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private OrganizationActions m_BaseOrgAction = new OrganizationActions();
	private EmailActions m_BaseEmailAction = new EmailActions();
	private searchOpportunitiesAction m_BaseOppSrchAction = new searchOpportunitiesAction();
	private ActionHelper m_BaseHelp = new ActionHelper();
	private IndividualActionHelper m_IndActHelp = new IndividualActionHelper();
	private IndividualsBRLO m_IndBRLOObj=null;
	private OrganizationBRLO m_OrgBRLOObj=null;
	private searchOpportunitiesBLO m_SearchBRLOObj=null;
	private ApplicationCodesBRLO m_ApplBRLOObj=null;
	private OrganizationActionHelper m_OrgActHelp = new OrganizationActionHelper();

}
