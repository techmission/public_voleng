package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Base Struts Actions
* Description:  User Interface Actions
* @author Amit Gupta
* @Web http://www.roseindia.net
* @Email struts@roseindia.net
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.*;

import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.abrecorp.opensource.voleng.brlayer.ApplicationCodesBRLO;
import com.abrecorp.opensource.voleng.brlayer.EmailBRLO;
import com.abrecorp.opensource.voleng.brlayer.IndividualsBRLO;
import com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO;

import java.io.*;
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
public class StrutsUploadAndSaveAction extends DispatchAction
{
	public ActionForward execute(
	  ActionMapping actionMapping,
	  ActionForm actionForm,
	  HttpServletRequest httpServletRequest,
	  HttpServletResponse httpServletResponse) throws Exception{
		
	String aszPortal = "", error="", filePath="", fileType="";
	  boolean b_resume=false;
	  boolean showPortalInfo=false;

    	getPortalInfo( httpServletRequest, httpServletResponse);
System.out.println("67 in execute upload");
      if(false == m_BaseServletABREObj.IsSessionLoggedIn( httpServletRequest )) {
      	if (session.getAttribute ("drupalsession")=="login"){  // Storing Value into session Object
          	return actionMapping.findForward( "showlogin" );
      	}
      }
      PersonInfoDTO aCurrentUserObj = m_BaseServletABREObj.getCurrentUser( httpServletRequest );
      if(aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGINOK &&
    		  aCurrentUserObj.getUserIsLoggedIntoSystem() != PersonInfoDTO.USER_LOGIN_PARTIAL
    ){
        	return actionMapping.findForward( "showlogin" );
      }

System.out.println("80 in execute upload");
      StrutsUploadAndSaveForm myForm = (StrutsUploadAndSaveForm)actionForm;
	  if(httpServletRequest.getParameter("fileType")!=null){
		  if(httpServletRequest.getParameter("fileType").equals("resume")){
			  b_resume = true;
		  }
	  }else if(httpServletRequest.getParameter("portal")!=null){
		  aszPortal = httpServletRequest.getParameter("portal");
	  }
System.out.println("87 in execute upload");
	  if(	httpServletRequest.getHeader("host").contains("churchvolunteering.org") 	|| 
			  httpServletRequest.getHeader("host").equalsIgnoreCase("www.christianvolunteering.org")	|| 
			  httpServletRequest.getHeader("host").equalsIgnoreCase("christianvolunteering.org")	||
			  httpServletRequest.getHeader("host").contains("churchvol.org")	||
			  httpServletRequest.getHeader("host").contains("http://chrisvol.org")	||
			  httpServletRequest.getHeader("host").contains("http://www.chrisvol.org")	||
			  httpServletRequest.getHeader("host").contains("http://cv.org")
	  ) {
	  	showPortalInfo=true;
	  }
	  if( b_resume==true || aszPortal.length()!=0	|| showPortalInfo==true){ // what about Brand new cases, where they are initially setting up the portal name?  In this case, one wouldn't exist
		  if( aszPortal.length()!=0){
			  if( aszPortal.equals("/voleng")){
				  aszPortal="";
			  }
			  if(aszPortal.startsWith("/voleng")){
				  aszPortal = aszPortal.substring(6, aszPortal.length());
			  }
		  }
		  // Process the FormFile
		  FormFile myFile = myForm.getTheFile();
		  if(myFile!=null){
			  String fileName    = myFile.getFileName();
			  String contentType = myFile.getContentType();
			  String aszFileExtension = "";
			  int fileSize       = myFile.getFileSize(); // 151KB, for example, is 153964
			  if(fileSize > 512000 && b_resume==false){// greater than 500 KB
				  error="Image file size is too large";
			  }else if(fileSize > 1024000){// greater than 1 MB
				  error="Image file size is too large";
			  }
			  //byte[] fileData    = myFile.getFileData();
			  //Get the servers upload directory real path name
			  if( b_resume==false && (imageMimeTypes.contains(contentType)) ){
				  fileType="image";
			  }else if(	fileMimeTypes.contains(contentType) ){
				  fileType="resume";
				  aszFileExtension = ".doc";
			  }
			  // adjust the name of the file we're saving according to its contentType
			  if(contentType.equals("application/pdf")){
				  aszFileExtension=".pdf";
			  }else if(contentType.equals("application/msword")){
				  aszFileExtension=".doc";
			  }else if(contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
				  aszFileExtension=".docx";
			  }else if(contentType.equals("text/plain")){
				  aszFileExtension=".txt";
			  }else if(contentType.equals("application/rtf")){
				  aszFileExtension=".rtf";
			  }else if(contentType.equals("text/rtf")){
				  aszFileExtension=".rtf";
			  }
			  
			   
			  allocatedIndBRLO( httpServletRequest );
			  String aszRailsEditBasic = m_IndBRLOObj.getRailsSitePrefix( httpServletRequest ) + aszRailsEditBasicPath;
			  String aszRailsEdit = m_IndBRLOObj.getRailsSitePrefix( httpServletRequest ) + aszRailsEditPath;
			  
			  
			  
			  if(fileType.length()<1){
				  error="This file is not a valid file type";
				  if(fileType.equals("image")){
					  return actionMapping.findForward("failure");
				  }else{
				  	   httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
				  	   return actionMapping.findForward("filefailure");
				  }
			  }
			  String aszServletContext = getServlet().getServletContext()+"";
			  String aszRealPath = getServlet().getServletContext().getRealPath("/");
			  
			  String aszParentPath = aszRealPath;
			  if(b_resume==false){
				  if(getServlet().getServletContext().equals("/voleng")){
					  filePath = aszRealPath +"imgs\banners";
				  }else{
					  filePath = aszRealPath +"imgs/banners";
				  }
			  }else{
				  // save in the parent directory; don't want the file accessible to the public
				  if(aszParentPath.endsWith("public_html/")){
					  aszParentPath = aszParentPath.substring(0, aszParentPath.indexOf("public_html/") );
					  filePath = aszParentPath +"files/resumes";
				  }else if(aszParentPath.endsWith("public_html\\")){
					  aszParentPath = aszParentPath.substring(0, aszParentPath.indexOf("public_html\\") );
					  filePath = aszParentPath +"files\\resumes";
				  }
			  }
			  // Save file on the server 
			  InputStream fileInputStream    = null;
			  FileOutputStream fileOutStream = null;
			  if(!fileName.equals("")  && fileType.length()>0){  
				  //Create file
				  File fileToCreate = new File(filePath, fileName);
				  allocatedApplBRLO( httpServletRequest );
				  
				  try{
					  if(!fileToCreate.exists()){ // make sure this file (or filename) doesn't already exist to get overwritten
						  if(fileType.equals("resume") && b_resume==true){
							  fileInputStream    = null;
							  fileOutStream = null;
							  try{
								  // first, insert into uprofile the filename, etc
								  int iUID = aCurrentUserObj.getUserUID();
								  int iUPnid = aCurrentUserObj.getUserProfileNID();
								  String aszUsername = aCurrentUserObj.getUSPUsername();
								  
								  int iIndexFileExtension = 0;
								  int iFileNameLength = fileName.length();
								  if(fileName.endsWith(".docx")){
									  iIndexFileExtension = fileName.indexOf(".docx");
								  }else if(fileName.endsWith(".doc")){
									  iIndexFileExtension = fileName.indexOf(".doc");
								  }else if(fileName.endsWith(".pdf")){
									  iIndexFileExtension = fileName.indexOf(".pdf");
								  }else if(fileName.endsWith(".odt")){
									  iIndexFileExtension = fileName.indexOf(".odt");
								  }
								  if(iIndexFileExtension < iFileNameLength)
									  aszFileExtension = fileName.substring(iIndexFileExtension, iFileNameLength);
								  
								  if(aszFileExtension.length()>0){
									  fileName="UID="+iUID+"_Name="+aCurrentUserObj.getUSPNameFirst()+"_"+aCurrentUserObj.getUSPNameLast()+aszFileExtension;
									  fileToCreate = new File(filePath, fileName);
								}
								  aCurrentUserObj.setUSPResumeFilePath(filePath + "/" + fileName);
								  // link the resume - store the resume file location on the userprofile
								  int iRetCode = m_IndBRLOObj.linkResume(aCurrentUserObj);
								  // second, write the file
								  if(iRetCode==0){
									  fileOutStream = new FileOutputStream(fileToCreate);
									  byte[] fileData = myFile.getFileData();
									  fileOutStream.write(fileData);
	/*								  
									  fileInputStream = new FileInputStream(filePath + "/" + fileName);
									  DataInputStream in = new DataInputStream(fileInputStream);
	*/								  
									  //this will be unique Id used by Solr to index the file contents.
									  String solrId = aszUsername + "_UID-" + iUID + "_UPnid-" + iUPnid;
									  if(aszParentPath.startsWith("C:\\")){
//										  m_ApplBRLOObj.indexFilesSolrCell(filePath + "\\" + fileName, solrId, aCurrentUserObj, httpServletRequest);
									  }else{
										  m_ApplBRLOObj.indexFilesSolrCell(filePath + "/" + fileName, solrId, aCurrentUserObj, httpServletRequest);
									  }
	System.out.println("line 232 - finished indexFilesSolrCell #1");								  
									  // tag the userprofile with resume data
									  int[] a_iData = aCurrentUserObj.getUSPServiceAreasArray();
									  String[] a_aszData = aCurrentUserObj.getUSPServiceAreasStringArray();
									  m_ApplBRLOObj.tagResumeData(aCurrentUserObj, a_iData, a_aszData);
	
									  // a_iData has all the TIDs to update in the DB, 
									  //aCurrentUserObj.setUSPServiceAreasArray(a_iData);
									  // run db query update to update the value of the resume in the dbm, as well as tags
									  m_IndBRLOObj.updateResume(aCurrentUserObj);
									  
									  // and a_aszData has all the terms to update in the solr index
									  //aCurrentUserObj.setUSPServiceAreasStringArray(a_aszData);
									  
									  if(aszParentPath.startsWith("C:\\")){
//										  m_ApplBRLOObj.indexFilesSolrCell(filePath + "\\" + fileName, solrId, aCurrentUserObj, httpServletRequest);
	//									  m_ApplBRLOObj.indexFilesSolrAltCell(filePath + "\\" + fileName, solrId, aCurrentUserObj, httpServletRequest);
									  }else{
										  m_ApplBRLOObj.indexFilesSolrCell(filePath + "/" + fileName, solrId, aCurrentUserObj, httpServletRequest);
	System.out.println("line 251 - finished indexFilesSolrCell #1");								  
										  m_ApplBRLOObj.indexFilesSolrAltCell(filePath + "/" + fileName, solrId, aCurrentUserObj, httpServletRequest);
	System.out.println("line 253 - finished indexFilesSolrCell #1");								  
									  }
									  
	
								  }
							  }catch(Exception e){
								  // error writing the file
								  fileName="";
								  error="Error while writing the file";
								  System.out.println(e.toString());
									  
								  if(fileInputStream != null){
									  fileInputStream.close();
								  }
	
								  if(fileOutStream != null){
									  fileOutStream.flush();
									  fileOutStream.close();
								  }
								  // catch - if there was a failure writing the file, clear the filename from the uprofile
								  aCurrentUserObj.setUSPResumeFilePath("");
								  m_IndBRLOObj.linkResume(aCurrentUserObj);
							  }
						  }else if(fileType.equals("image")){ // validate image by getting image size, etc...
							  fileInputStream    = myFile.getInputStream();
							  Image imageToCreate = ImageIO.read(fileInputStream);
							  if(imageToCreate!=null){
								  fileInputStream.close();
								  fileOutStream = new FileOutputStream(fileToCreate);
								  try{
									  fileOutStream.write(myFile.getFileData());
								  }catch(Exception e){
									  // error writing the file
									  fileName="";
									  error="Error while writing the file";
									  fileOutStream.flush();
									  fileOutStream.close();
								  }
								  if(fileInputStream != null){
									  fileInputStream.close();
								  }
	
								  if(fileOutStream != null){
									  fileOutStream.flush();
									  fileOutStream.close();
								  }
							  }else{
								  error="File does not appear to be a valid image file";
								  fileName="";
							  }
						  }
					  }else{
							  // a file of this name already exists
							  error="A file of this name already appears to exist";
							  // ************************   what about the same user updating an image logo????
							  fileName="";
						  }
					  }catch(IOException e){
						  // is not a legitimate image file!! don't do the write
						  error="File does not appear to be a valid file";
						  fileName="";
					  }
				  }else{
					  if(fileType.equals("image")){
						  error="The uploaded file must be an image file";
					  }else if(fileType.equals("resume")){
						  error="The uploaded file must be a .pdf, .doc or .docx file";
					  }
					  fileName="";
				  }
			  
			  if(fileInputStream != null){
				  fileInputStream.close();
			  }
	
			  if(fileOutStream != null){
				  fileOutStream.flush();
				  fileOutStream.close();
			  }
			  if(fileName.length()==0){
				  if(fileType.equals("image")){
					  return actionMapping.findForward("failure");
				  }else{
				  	   httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
				  	   return actionMapping.findForward("filefailure");
				  }
			  }else{
				  if(fileType.equals("image")){
					  if(aszPortal.length()>0){
						  session.setAttribute(aszPortal + "_banner", fileName);
					  }
				  }
				  //Set file name to the httpServletRequest object
				  session.setAttribute("fileName",fileName);
			  }
		    
		      ApplicationInfoDTO aApplicObj = new ApplicationInfoDTO();
			  httpServletRequest.setAttribute("applicinfo", aApplicObj);

	          if(fileType.equals("image")){
				  return actionMapping.findForward("imagesuccess");
			  }else{
				  // test to see if the user is going through cv intern application process first; if so, it returns null (if possible)
				  // if source is "cvinternapplic"
				  boolean b_cvInternApplic = false;
				  String aszUID = "";
				  try{
					  if(httpServletRequest.getParameter("source")!=null){
						  if(httpServletRequest.getParameter("source").length()>1){
							  if(httpServletRequest.getParameter("source").equals("cvinternapplic")){
								  b_cvInternApplic=true;
							  }
						  }
					  }
				  }catch(Exception e){}
				  
				  if(b_cvInternApplic){
						  try{
							  if(httpServletRequest.getParameter("uid")!=null){
								  if(httpServletRequest.getParameter("uid").length()>1){
									  aszUID = httpServletRequest.getParameter("uid");
								  }
							  }
						  }catch(Exception e){}
						  int iUID = m_BaseHelp.convertToInt( aszUID );
			            allocatedEmailBRLO( httpServletRequest );
		      	        int iRetCode = m_EmailBRLOObj.loadApplication(aApplicObj, 0, aCurrentUserObj.getUserUID(), ApplicationInfoDTO.LOADBY_APPLICANT_USER);
		      	        
		      	        // update screened value on application to have value of 0 rather than of -8 now
		      			int iInitScreenedValue=aApplicObj.getScreened();
						if(iInitScreenedValue==-8){
System.out.println("StrutsUploadAndSaveAction - 391 - screened WAS -8; now set to 0 iInitScreenedValue is "+iInitScreenedValue);		
							aApplicObj.setScreened(0);
							aApplicObj.setResumeFilePath(aCurrentUserObj.getUSPResumeFilePath());
						}
System.out.println("StrutsUploadAndSaveAction - 394 - aApplicantObj.getScreened() is "+aApplicObj.getScreened());		
		          		iRetCode = m_EmailBRLOObj.updateApplication( aApplicObj, 3 );

		      	        
		      	        if(iRetCode==0)
		      	        	return actionMapping.findForward( "applicsent" );
				  }
				  
				  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
				  String aszTemp = httpServletRequest.getHeader("host");
				  httpServletRequest.setAttribute("redirect", aszRailsEdit);
			 	  return actionMapping.findForward("mappingpage");			  
	//			  return actionMapping.findForward("filesuccess");			  
			  }
		  }else{
			  if(fileType.equals("image")){
				  return actionMapping.findForward("failure");
			  }else{
			  	   httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
			  	   return actionMapping.findForward("filefailure");
			  }
		  }
	  }
	  httpServletRequest.setAttribute("userprofile", aCurrentUserObj);
 	   return actionMapping.findForward("filefailure");

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
   }
     

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
	private ActionHelper m_BaseHelp = new ActionHelper();
	private EmailBRLO m_EmailBRLOObj = null;//new EmailBRLO();
 	
 	// end-of method allocatedEmailBRLO()
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
	private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private EmailActions m_BaseEmailAction = new EmailActions();

	private IndividualsBRLO m_IndBRLOObj=null;
	private ApplicationCodesBRLO m_ApplBRLOObj=null;
  	private static final String aszRailsEditPath = "profiles~mine~edit";
	private static final String aszRailsEditBasicPath = "profiles~mine~edit_basic";
	public String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	private HttpSession session = null;
	private String aszSiteVersion = null;
	private PersonInfoDTO aCurrentUserObj = null;
	private String aszLoggedInStatus = ""; 

}