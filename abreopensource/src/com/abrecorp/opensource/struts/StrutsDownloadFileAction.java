package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Base Struts Actions
* Description:  User Interface Actions
* @author Amit Gupta
* @Web http://www.roseindia.net
* @Email struts@roseindia.net
*/

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.*;

import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.AppSessionDTO;
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.abrecorp.opensource.voleng.brlayer.ApplicationCodesBRLO;
import com.abrecorp.opensource.voleng.brlayer.EmailBRLO;
import com.abrecorp.opensource.voleng.brlayer.IndividualsBRLO;
import com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


import java.io.File;
import java.io.IOException;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;

/**
 * Struts File Upload Action Form.
 *
*/
public class StrutsDownloadFileAction extends Action
{
	   @Override
	public ActionForward execute(
	  ActionMapping actionMapping,
	  ActionForm actionForm,
	  HttpServletRequest httpServletRequest,
	  HttpServletResponse httpServletResponse) throws Exception{
		
		String aszPortal = "", error="", filePath="", fileType="";
		int iUID=0;
		boolean b_resume=false;
		int iNID=0;
		String aszSFID="", aszAttachmentType="";;
		boolean b_sf_resume=false;
		boolean b_salesforce_resume=false;

		if(httpServletRequest.getParameter("uid")!=null){
			iUID = m_BaseHelp.convertToInt(httpServletRequest.getParameter("uid"));
		}
		if(httpServletRequest.getParameter("nid")!=null){
			iNID = m_BaseHelp.convertToInt(httpServletRequest.getParameter("nid"));
			if(httpServletRequest.getParameter("src")!=null){
				if(httpServletRequest.getParameter("src").length()>0){
					if(httpServletRequest.getParameter("src").equals("sf")){
						b_sf_resume = true;
					}
				}
			}
			if(httpServletRequest.getParameter("attachmenttype")!=null){
				if(httpServletRequest.getParameter("attachmenttype").length()>0){
					aszAttachmentType = httpServletRequest.getParameter("attachmenttype");
				}
			}
		}
		
      	getPortalInfo( httpServletRequest, httpServletResponse);
		// make sure current user is logged in

        aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
 System.out.println("98 StrutsDownload - aCurrentUserObj UID is "+aCurrentUserObj.getUserUID()+";  email is "+aCurrentUserObj.getUSPEmail1Addr());			

        if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGINOK){
		}else if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
			httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
System.out.println("103 StrutsDownload - PersonInfoDTO.USER_LOGIN_PARTIAL");			
			String aszRailsAccountCreatePage = aszRailsPrefixPath + aszRailsAccountCreatePath;
			httpServletRequest.setAttribute("redirect",aszRailsAccountCreatePage);
				return actionMapping.findForward("mappingpage");
		}else{
	       	getLoggedInStatus(httpServletRequest, httpServletResponse);
			if(aszLoggedInStatus.equals("showlogin")){
    	    	return actionMapping.findForward( "showlogin" );
    		}else if(aszLoggedInStatus.equals("processCookieLogin")){
    	        return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    		}
         	AppSessionDTO aSessDat = m_BaseServletABREObj.getSessionNavData( httpServletRequest );
  		 if(aCurrentUserObj.getUserIsLoggedIntoSystem() == PersonInfoDTO.USER_LOGIN_PARTIAL){
   			  if(null != aSessDat){
   				  aSessDat.setUID( iUID );
   				  aSessDat.setTokenKey( AppSessionDTO.TOKEN_DOWNLOADRESUME );
   			  }
   		     	httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
   		 }
           if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK){
           	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
               	return actionMapping.findForward( "showlogin" );
           	}else{
                   return processCookieLogin(actionMapping, actionForm, httpServletRequest, httpServletResponse);
           	}
           }
		}

        allocatedIndBRLO( httpServletRequest );
        allocatedApplBRLO( httpServletRequest );
        allocatedEmailBRLO( httpServletRequest );

        ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
    	EmailInfoDTO aEmailObj = new EmailInfoDTO();
		
        PersonInfoDTO aIndivObj = new PersonInfoDTO();
        
        PersonInfoDTO aContactPersonObj = new PersonInfoDTO();
        aContactPersonObj.setUserUID( iUID );
        String aszSiteVersion="default";
  		if(	httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" )){
  			aszSiteVersion="development";
  		}
        int iRetCode = 0;
System.out.println("StrutsDownload line 154; iNID is: "+iNID+";  aszAttachmentType is: "+aszAttachmentType);        
        if(iNID>0){// && b_sf_resume){ // load the application from the feeds db to retrieve the resume file name & location; first test the user has access to this
System.out.println(" line 156 - inid > 0");
        	if(aszAttachmentType.equals("cover_letter")){
System.out.println(" line 158 - aszAttachmentType is cover_letter");
            	aEmailObj.setEmailId(iNID);
            	// first load the email to get the opp_nid;
          		 iRetCode = m_EmailBRLOObj.loadEmail( aEmailObj, 1 ); // load by email id rather than by org or volunteer
System.out.println("   line 162; iRetCode is "+iRetCode+" and email opp nid is "+aEmailObj.getEmailOppNID());           		 
           		 if(iRetCode!=1 || aEmailObj.getEmailOppNID()<1){
                   	return actionMapping.findForward( "noaccess" );
           		 }
           		 allocatedOrgBRLO( httpServletRequest );
           		 // confirm that the current user has contact access to aEmailObj.setEmailOppNID(iOppNID);
           		 int iOppNID = aEmailObj.getEmailOppNID();
            	// then, try to load with the current user access
           		 OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
           		 aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
System.out.println("170 - strutsdownload - just before load opp w permissions;");           		 
System.out.println("    iOppNID is "+iOppNID+";  OrganizationInfoDTO.LOADBY_UID_CONTACT;  ");
System.out.println("      httpServletRequest.getSession().getServletContext().getRealPath is: " + httpServletRequest.getSession().getServletContext().getRealPath("/"));           		 
           		 int iRetCode2 = 
           				 m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 0,"", 
           						 OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, 
           						 httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublisheed
           		 if(iRetCode2 != 0){
                   	return actionMapping.findForward( "noaccess" );
           		 }
           		 // then proceed to download the file - or at least say the name/format/location of the file
           		 filePath = "/home/chrisvol/files/filled_out_opportunity_documents/"+iNID+"/"+aEmailObj.getCoverLetterFileName();

            }else if(aszAttachmentType.equals("application")){
            	aEmailObj.setEmailId(iNID);
            	// first load the email to get the opp_nid;
          		 iRetCode = m_EmailBRLOObj.loadEmail( aEmailObj, 1 ); // load by email id rather than by org or volunteer
           		 
           		 if(iRetCode!=1 || aEmailObj.getEmailOppNID()<1){
                   	return actionMapping.findForward( "noaccess" );
           		 }
           		 allocatedOrgBRLO( httpServletRequest );
           		 // confirm that the current user has contact access to aEmailObj.setEmailOppNID(iOppNID);
           		 int iOppNID = aEmailObj.getEmailOppNID();
            	// then, try to load with the current user access
           		 OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
           		 aOpportObj.setOPPUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
           		 int iRetCode2 = 
           				 m_OrgBRLOObj.loadOpportunity( aOpportObj, iOppNID, 0,"", 
           						 OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion, 
           						 httpServletRequest.getSession().getServletContext().getRealPath("/") ); // show all listings, published and unpublisheed
           		 if(iRetCode2 != 0){
                   	return actionMapping.findForward( "noaccess" );
           		 }
           		 // then proceed to download the file - or at least say the name/format/location of the file
           		 filePath = "/home/chrisvol/files/filled_out_opportunity_documents/"+iNID+"/"+aEmailObj.getApplicationFileName();

            }else{
	        	aApplicObj.setNID(iNID);
	   	        int iIdNum = aApplicObj.getNID();
	   	        int iKeyId = 0;
		  		aApplicObj.setSource("sf");
	        	boolean b_cvInternSiteApproved = false;
				// check to see if the current user has access rights on any cvintern_approved orgs
				ArrayList aCVCSitesList = new ArrayList( 2);
		        allocatedOrgBRLO( httpServletRequest );
		        m_ApplBRLOObj.getCVInternOrgSitesList(aCVCSitesList);
				// org_nids is a_cvinternOrgsList.
				for(int index=0; index < aCVCSitesList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCVCSitesList.get(index);
					if(null == aAppCode) continue;
					int iOptRqCode = aAppCode.getAPCIdSubType();
					int iOrgNIDTmp = aAppCode.getAPCIdSubType();
					// try to load the iOrgNIDTmp by admin or contact and if it succeeds, then it can show the search results.
			        OrganizationInfoDTO aOrgInfoObjTmp = new OrganizationInfoDTO();
			        aOrgInfoObjTmp.setORGNID( iOrgNIDTmp );
		             aOrgInfoObjTmp.setORGUID( aCurrentUserObj.getUserUID() ); // check that the user MUST HAVE ownership of the said organization
	                 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObjTmp, OrganizationInfoDTO.LOADBY_ORGNID, aszSiteVersion );
	                 if(iRetCode == -111){
	                	 // if it failed, try to load via contact
	                	 iRetCode = m_OrgBRLOObj.loadOrganizationFromDB( aOrgInfoObjTmp, OrganizationInfoDTO.LOADBY_UID_CONTACT, aszSiteVersion );
	                 }
	                 if(iRetCode==0){
	                	 b_cvInternSiteApproved = true; 
	                	 break;
	                 }
	
				}
	
				//initially try to load as an org admin
				if(b_cvInternSiteApproved){
					aApplicObj.setUID(0);
					iRetCode = m_EmailBRLOObj.loadApplication(  aApplicObj, iIdNum, iKeyId, ApplicationInfoDTO.LOADBY_APPROVED_SITE );
					filePath = aApplicObj.getResumeFilePath();
System.out.println("186 download file filePath is "+filePath);		
					aszSFID = aApplicObj.getSFID();
					b_salesforce_resume=true;
					
				}
            }
        }else{
	        iRetCode = m_IndBRLOObj.loadUserByOption( aContactPersonObj, PersonInfoDTO.LOADBY_UID, aszSiteVersion );
			filePath = aContactPersonObj.getUSPResumeFilePath();
System.out.println("194 download file filePath is "+filePath);		
        }
		String fileName = filePath;
		int iLength = filePath.length();
		int indexOfFirst = 0;
System.out.println("195 download file fileName is "+fileName);		
		if(b_salesforce_resume){
			if(filePath.indexOf("SFID=")>0){
				indexOfFirst = filePath.indexOf("SFID=");
			}
			if(iLength > indexOfFirst)
				fileName=filePath.substring(indexOfFirst, iLength);
        }else if(aszAttachmentType.equals("application")){
			if(filePath.indexOf("application")>0){
				indexOfFirst = filePath.indexOf("application");
			}
			if(iLength > indexOfFirst)
				fileName=filePath.substring(indexOfFirst, iLength);
        }else if(aszAttachmentType.equals("cover_letter")){
			if(filePath.indexOf("cover_letter")>0){
				indexOfFirst = filePath.indexOf("cover_letter");
			}
			if(iLength > indexOfFirst)
				fileName=filePath.substring(indexOfFirst, iLength);
		}else{
			if(filePath.indexOf("_Name=")>0){
				indexOfFirst = filePath.indexOf("_Name=")+6;
System.out.println("205 download file indexOfFirst is "+indexOfFirst);		
			}
			if(iLength > indexOfFirst)
				fileName=filePath.substring(indexOfFirst, iLength);
		}
System.out.println("195 download file fileName is "+fileName);		
		//return an application file instead of html page; escape with double quotes for chrome
		String aszHeaderContentDispositionData = 
			  "attachment;filename=\""+fileName+"\"";
//		httpServletResponse.setContentType("application/octet-stream");
//		httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		if(fileName.endsWith(".doc")){
			httpServletResponse.setContentType("application/msword");
		}else if(fileName.endsWith(".pdf")){
			httpServletResponse.setContentType("application/pdf");
		}else if(fileName.endsWith(".docx")){
			httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		}else{
			httpServletResponse.setContentType("application/octet-stream");
		}
		httpServletResponse.setHeader("Content-Disposition",aszHeaderContentDispositionData); 

		if(httpServletRequest.getParameter("resume")!=null){
			b_resume = true;
			if(b_sf_resume){
				b_salesforce_resume=true;
			}
		}
		if(aCurrentUserObj.getUSPVolOrOpportunity().equalsIgnoreCase("Volunteer")
				&& false==true
		){
        	DynaActionForm oFrm = (DynaActionForm)actionForm;
			error = "You must be an organizational user to have access to this download.";
System.out.println("245 - error is "+error);
          	m_BaseHelp.setFormData(oFrm,"errormsg", error );
          	return actionMapping.findForward( "noaccess" );
		}else if( (b_resume==true && iUID>0) || b_salesforce_resume || 
				(iNID>0 && (aszAttachmentType.equals("cover_letter") || aszAttachmentType.equals("application")) )){ 
	        // load data for organization contact person
	        if((iRetCode==0 || iRetCode == -222)
	        		|| (iRetCode==1 &&(iNID>0 && (aszAttachmentType.equals("cover_letter") || aszAttachmentType.equals("application"))))){ // catches user applicants who mistakenly weren't marked for the age requirements terms and conditions
				FileInputStream in = null;
				ServletOutputStream out = null;
				try 
				{
					//Get it from file system
					File file = new File(filePath);
System.out.println("256 Download - filepath is "+filePath);					
					in = new FileInputStream(file);
System.out.println("258 - just ran the FileInputStream");					
					if(fileName.endsWith(".docx") || b_salesforce_resume || 
							(iNID>0 && (aszAttachmentType.equals("cover_letter") || aszAttachmentType.equals("application")) )){
						httpServletResponse.setContentLength((int) file.length());
					}
					out = httpServletResponse.getOutputStream();
System.out.println("263 - just ran the getOutputStream");					
					byte[] outputByte = new byte[16 * 4096];
					//copy binary content to output stream
					while(in.read(outputByte, 0, 4096) != -1){
						out.write(outputByte, 0, 4096);
					}
					if(in != null)	in.close();
					if(out != null){
						out.flush();
						out.close();
					}
System.out.println("274 - done withoutputByte ");					
					
					// track download in db
					if(b_salesforce_resume){
						aIndivObj.setUserUID( aApplicObj.getUID() );
				        aIndivObj.setUSPResumeFilePath(filePath);
				        aIndivObj.setUSPNameFirst(aApplicObj.getNameFirst());
				        aIndivObj.setUSPNameLast(aApplicObj.getNameLast());
				        aIndivObj.setUSPEmail1Addr(aApplicObj.getEmailAddr());
						m_ApplBRLOObj.trackFileDownload(aCurrentUserObj,aIndivObj);
					}else{
						m_ApplBRLOObj.trackFileDownload(aCurrentUserObj,aContactPersonObj);
					}
					
				}catch(Exception e){
					if(in != null)	in.close();
					if(out != null){
						out.flush();
						out.close();
					}
System.out.println("Catch block on file download - e.getMessage() ");
System.out.println(e.getMessage());
					e.printStackTrace();
				}
	        }
		}
		return null;
  }
	

	     /**
	     * process cookie login
	     */
	     public ActionForward processCookieLogin(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
	     {
	     	int iRetCode=0, iRetCode2=0 ;
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
		         	        
		         	       aCurrentUserObj.processTokens();
		         	    	if(aCurrentUserObj.getProvider().length()>0){
		         	    		session.setAttribute("socialize", aCurrentUserObj.getProvider());
		         	    	}
		         		}
		         	} 
		         }
	     	}
	         
       		return actionMapping.findForward( "loginstatus" );
	     }
	     // end-of method processLogin()
	     
	   
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
        if(null == aCurrentUserObj) {
      	  aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
            if(null == aCurrentUserObj) {
          	 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	          		aszLoggedInStatus="showlogin";
	          		return;
	          	}else{
	          		aszLoggedInStatus="processCookieLogin"  ;
	          		return;
	          	}
            }else{
            		aszLoggedInStatus="processCookieLogin"  ;
            		return;
            }
        }else if(aCurrentUserObj.getUserUID() < 1){
      	  aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
            if(null == aCurrentUserObj) {
          	 if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
	          		aszLoggedInStatus="showlogin";
	          		return;
	          	}else{
	          		aszLoggedInStatus="processCookieLogin"  ;
	          		return;
	          	}
            }else{
            		aszLoggedInStatus="processCookieLogin"  ;
            		return;
            }
        }
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
   				httpServletRequest.getHeader("host").equalsIgnoreCase( "cv.org:7001" ) 		||
   				httpServletRequest.getHeader("host").equalsIgnoreCase( "chrisvol.org:7001" ) 
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
                     	session.setAttribute(aszPortal + "_type", aszRequestType);
	             	}
               }
           }
       }
   }

	private static final Set<String> imageMimeTypes = new HashSet<String>(Arrays.asList(
	     new String[] {
    		 "image/jpeg","image/gif","image/png"
	     }
	));

	private static final Set<String> fileMimeTypes = new HashSet<String>(Arrays.asList(
	     new String[] {
    		 "application/pdf","application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document",
             "text/plain","application/rtf","application/x-rtf","text/rtf","text/richtext","application/doc",
             "application/x-soffice","application/vnd.oasis.opendocument.text","application/x-vnd.oasis.opendocument.text",
             "appl/text","application/vnd.msword","application/vnd.ms-word","application/winword","application/word",
             "application/x-msw6","application/x-msword","application/x-pdf","application/acrobat","applications/vnd.pdf",
             "text/pdf","text/x-pdf"
	     }
	));
  	private String aszRailsPrefixPath = "";
	private static final String aszRailsAccountCreatePath = "profiles~mine~create_basic";
	
	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private ActionHelper m_BaseHelp = new ActionHelper();
	private IndividualsBRLO m_IndBRLOObj=null;
	private ApplicationCodesBRLO m_ApplBRLOObj=null;
	private EmailBRLO m_EmailBRLOObj = null;//new EmailBRLO();
	private OrganizationBRLO m_OrgBRLOObj=null;
	public String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	private HttpSession session = null;
	private String aszSiteVersion = null;
	private PersonInfoDTO aCurrentUserObj = null;
	private String aszLoggedInStatus = ""; 

}