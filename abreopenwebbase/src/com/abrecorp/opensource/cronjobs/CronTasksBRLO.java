package com.abrecorp.opensource.cronjobs;

/**
* Created 2006-09-20
* Business Rules Layer Object BRLO
* For Indovidual Processing
* @author David Marcillo   Ali McCracken
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.WordUtils;
import org.apache.struts.upload.FormFile;
import org.quartz.utils.DBConnectionManager;

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.javamail.ABREJavaSendMail;
import com.abrecorp.opensource.javamail.EmailObj;
import com.abrecorp.opensource.organization.OrganizationObj;
import com.abrecorp.opensource.application.ApplicationObj;

public class CronTasksBRLO extends ABREBaseBRLO {

	/**
	* Constructor
	*/
	public CronTasksBRLO() {}

	
    
    public int loadEmailMsg(EmailInfoDTO aEmailInfoObj, int iType){
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		// load email template for email to org - nid=546506
       	EmailObj aEmailObj = new EmailObj();
       	pConn = getCronDBConn();
//       	try{
  //         	pConn = (ABREDBConnection) DBConnectionManager.getInstance().getConnection("urbmi5_drupal");
       		
    //   	}catch(Exception e){
      // 		System.out.println("EXCEPTION "+e);
       	//}
		
System.out.println(" ********* CRON OUTPUT cron output - line 176; initiated DB Connection");  
if(pConn == null){
	System.out.println(" ERROR: pConn is NULL!!!!!!!!!!!!!!!!");
}else{
	System.out.println(" pConn is NOT null");
}    	
       	iRetCode = aEmailObj.loadApplicEmail(pConn, aEmailInfoObj, iType);
		if(null != pConn) pConn.free();
		return iRetCode;
    }
    
    public int loadApplicList(ArrayList<ApplicationInfoDTO> aList, int iType){
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		ApplicationObj aApplicObj = new ApplicationObj();
       	pConn = getCronDBConn();
if(pConn == null){
	System.out.println(" ERROR: pConn is NULL!!!!!!!!!!!!!!!!");
}else{
	System.out.println(" pConn is NOT null");
}
       	iRetCode = aApplicObj.getApplicationsList(pConn, aList, 0, iType);
       	if(null != pConn) pConn.free();
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
    
    public int loadOneApplication( ApplicationInfoDTO aApplicInfoObj, int iApplicNID){
        
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		
   		ApplicationObj aApplicObj = new ApplicationObj();
       	pConn = getCronDBConn();
    	iRetCode = aApplicObj.loadOneApplication(pConn, aApplicInfoObj, iApplicNID, 0, ApplicationInfoDTO.LOADBY_APPROVED_SITE );
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
    
    public int loadApplicEmailFlag( EmailInfoDTO aEmailInfoObj, int iApplicNID, int iOrgNID, int iType, int iEmailFlagID){
        
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		
   		EmailObj aEmailObj = new EmailObj();
       	pConn = getCronDBConn();
       	iRetCode = aEmailObj.loadApplicEmailFlag(pConn, aEmailInfoObj, iApplicNID, iOrgNID, 
       			iType, iEmailFlagID );
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
    public int insertIntoApplicEmailFlag( ApplicationInfoDTO aApplicInfoObj, OrganizationInfoDTO aOrgInfoObj, int iNID){
        
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		
   		EmailObj aEmailObj = new EmailObj();
       	pConn = getCronDBConn();
        iRetCode = aEmailObj.insertApplicEmailFlag(pConn, aApplicInfoObj, aOrgInfoObj, iNID );
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
	
    
    public int getApplicMatchesList(ArrayList<ApplicationInfoDTO> aList){
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		ApplicationObj aApplicObj = new ApplicationObj();
       	pConn = getCronDBConn();
       	iRetCode = aApplicObj.getApplicMatchesList( pConn,  aList);
       	if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
	
    
    public int insertApplicMatch(ApplicationInfoDTO aApplicantObj){
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		ApplicationObj aApplicObj = new ApplicationObj();
       	pConn = getCronDBConn();
       	iRetCode = aApplicObj.insertApplicMatch( pConn,  aApplicantObj);
       	if(null != pConn) pConn.free();
		
		return iRetCode;
    }
    
    
	
    public String getApplicMatchSource( int iOrgNID, int iApplicNID ){
        String aszSource=null;
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		ApplicationObj aApplicObj = new ApplicationObj();
       	pConn = getCronDBConn();
       	aszSource = aApplicObj.getApplicMatchSource(  pConn,  iOrgNID,  iApplicNID );
		if(null != pConn) pConn.free();
		
		return aszSource;
    }
	
	
    public int loadOrg( OrganizationInfoDTO aOrgInfoObj){
        
   		int iRetCode=0;
   		ABREDBConnection pConn=null;
   		
   		OrganizationObj aOrganizationObj = new OrganizationObj();
       	pConn = getCronDBConn();
    	iRetCode = aOrganizationObj.loadOrganizationFromDB(pConn, aOrgInfoObj, "", OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC, "/org" );
		if(null != pConn) pConn.free();
		
		return iRetCode;
    }
 
	
    /**
	* get contact list for an org for email
	*/
	public int getOrgContactList( ArrayList aList, int iOrgNID){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iOrgNID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setNID( iOrgNID );
    	aSrchParmObj.setSearchType( OrganizationInfoDTO.LOADBY_UID );
    	OrganizationObj aOrganizationObj = new OrganizationObj();
    	pConn = getCronDBConn();
    	iRetCode = aOrganizationObj.getOrgContactList(pConn, aList, aSrchParmObj, "orgmailing" );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getOrgContactList()
	
    

	/**
	* send email message
	*/
	public int sendEmailMSG( EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		if(null == aHeadObj){
			return -1;
		}
		ABREJavaSendMail aEmailSendObj = new ABREJavaSendMail();
		iRetCode = aEmailSendObj.sendEmailMessage( aHeadObj, EmailInfoDTO.TOKEN_HTML );
		aHeadObj.appendErrorMsg( aEmailSendObj.getAllMessages() );
		return iRetCode;
	}
	// end-of method sendEmailMSG()
	

    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>
    //=== END Table emailinfo ===>


	private static int iOrgApplicMatchEmailNID = 546506, iOrgApplicMatchRecommendEmailNID = 547238, iApplicConfirmEmailNID = 544402;
}
