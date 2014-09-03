package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Base Struts Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

// Struts MVC objects
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.upload.FormFile;

import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.servlet.BaseServletABRE;
import com.abrecorp.opensource.voleng.brlayer.EmailBRLO;
import com.abrecorp.opensource.voleng.brlayer.IndividualsBRLO;
import com.abrecorp.opensource.voleng.brlayer.OrganizationBRLO;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class ActionHelper extends DispatchAction {

	/**
	* Constructor 
	*/
    public ActionHelper(){}

    /**
	* get search option data from form
	*
	public int getSearchRequestForm(DynaActionForm oFrm, SearchParms aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;


    	return 0;
    }
    // end-of method getSearchRequestForm   */

	/**
	* set dynamic form data Int 
	*/
	public void setFormDataInt(DynaActionForm oFrm, String field, int value){
		if(null == oFrm) return;
		if(null == field) return;
    	try {
    		oFrm.set(field, new Integer( value ) );
    	} catch (Exception e){
    		return ;
    	}
		return ;
	}
	// end-of method setFormDataInt()

	/**
	* set dynamic form data Int Array
	*/
	public void setFormDataIntArray(DynaActionForm oFrm, String field, int[] values){
		if(null == oFrm) return;
		if(null == field) return;
		if(null == values) return;//values=null;
		Integer[] a_iFieldTemp = new Integer[values.length];
    	for(int i=0; i<values.length; i++){
    		a_iFieldTemp[i]=new Integer(values[i]);
    	}
		if(values.length==0) {
			values=null;
			//return;
		}
    	try {
    		oFrm.set(field, a_iFieldTemp );
    	} catch (Exception e){
    		return ;
    	}
		return ;
	}
	// end-of method setFormDataIntArray()

    /**
	* get data from dynamic form as double
	*/
	public int getFormDataInt(DynaActionForm oFrm, String field){
		if(null == oFrm) return 0;
		if(null == field) return 0;
		String aszTemp = field.trim();
		if(aszTemp.length() < 1) return 0;
		Integer aInteger=null;
		int iInt=0;
		try {
			aInteger = (Integer)oFrm.get(field) ;
			iInt = aInteger.intValue();
    	} catch (Exception e){
    		aInteger=null;
    	}
		if(null == aInteger) return 0;
		return iInt;
	}
	// end-of method getFormDataInt()


	/**
	* set dynamic form data double 
	*/
	public void setFormDataDouble(DynaActionForm oFrm, String field, double value){
		if(null == oFrm) return;
		if(null == field) return;
    	try {
    		oFrm.set(field, new Float( value ) );
    	} catch (Exception e){
    		return ;
    	}
		return ;
	}
	// end-of method setFormDataDouble()

    /**
	* get data from dynamic form as double
	*/
	public double getFormDataDouble(DynaActionForm oFrm, String field){
		if(null == oFrm) return 0.0;
		if(null == field) return 0.0;
		String aszTemp = field.trim();
		if(aszTemp.length() < 1) return 0.0;
		Float aFloat=null;
		double dDouble=0.0;
		try {
			aFloat = (Float)oFrm.get(field) ;
			dDouble = aFloat.doubleValue();
    	} catch (Exception e){
    		aFloat=null;
    	}
		if(null == aFloat) return 0.0;
		return dDouble;
	}
	// end-of method getFormDataDouble()

    /**
	* get data from dynamic form
	*/
	public String getFormData(DynaActionForm oFrm, String field){
		if(null == oFrm) return "";
		if(null == field) return "";
		String aszTemp = field.trim();
		String aszFieldTemp = null;
		if(aszTemp.length() < 1) return "";
		try {
			aszFieldTemp = (String)oFrm.get(field) ;
			//the following code is so that we can enforce orgnumvol, but allow value=0
			if(aszTemp.equalsIgnoreCase("orgnumvol") && aszFieldTemp.equalsIgnoreCase("")){
				aszTemp = "-1";
			}
			else{
				aszTemp = aszFieldTemp;
			}
    	} catch (Exception e){
    		aszTemp=null;
    	}
		if(null == aszTemp) return "";
		
	    
	    byte[] incomingBytes = aszTemp.getBytes();
	    final CharBuffer windowsEncoded = windowsCharset.decode(ByteBuffer.wrap(incomingBytes)); 
	    final byte[] utfEncoded         = utfCharset.encode(windowsEncoded).array();
	    String aszTemp2 = new String(utfEncoded);

		
		return aszTemp.trim();
	}
	// end-of method getFormData()


    /**
	* get data from dynamic form
	*/
	public String getFormDataMSWord(DynaActionForm oFrm, String field){
		if(null == oFrm) return "";
		if(null == field) return "";
		String aszTemp = field.trim();
		String aszFieldTemp = null;
		if(aszTemp.length() < 1) return "";
		try {
			aszFieldTemp = (String)oFrm.get(field) ;
			//the following code is so that we can enforce orgnumvol, but allow value=0
			if(aszTemp.equalsIgnoreCase("orgnumvol") && aszFieldTemp.equalsIgnoreCase("")){
				aszTemp = "-1";
			}
			else{
				aszTemp = aszFieldTemp;
			}
    	} catch (Exception e){
    		aszTemp=null;
    	}
		if(null == aszTemp) return "";
		
	    
	    byte[] incomingBytes = aszTemp.getBytes();
	    final CharBuffer windowsEncoded = windowsCharset.decode(ByteBuffer.wrap(incomingBytes)); 
	    final byte[] utfEncoded         = utfCharset.encode(windowsEncoded).array();
	    aszTemp = new String(utfEncoded);

		
		return aszTemp.trim();
	}
	// end-of method getFormData()

    /**
	* get boolean data from dynamic form
	*/
	public boolean getFormDataBoolean(DynaActionForm oFrm, String field){
		if(null == oFrm) return false;
		boolean fieldTemp=false;//String aszFieldTemp="";
		try {
			//aszFieldTemp = (String)oFrm.get(field) ;
			fieldTemp = ((Boolean)oFrm.get(field)).booleanValue();
    	} catch (Exception e){
    		fieldTemp=false;
    	}
		return fieldTemp;
	}
	// end-of method getFormData()

    /**
	* get array of ints data from dynamic form
	*/
	public int[] getFormDataIntArray(DynaActionForm oFrm, String field){
		if(null == oFrm) return null;
		if(null == field) return null;
		Integer[] a_iTemp = new Integer[255];
		int[] a_iFieldTemp = new int[0];
		int iArraySize=0, iTmp=0;
		try {
			a_iTemp = (Integer[])oFrm.get(field) ;
			for(int i=0; i < a_iTemp.length; i++){
				iTmp=a_iTemp[i].intValue();
				iArraySize++;
				if(iTmp < 1){
					break;
				}
			}
    	} catch (Exception e){
    		a_iTemp=null;
    	}
    	a_iFieldTemp = new int[iArraySize];
    	for(int i=0; i<iArraySize; i++){
    		a_iFieldTemp[i]=a_iTemp[i].intValue();
    	}
		if(null == a_iFieldTemp) return null;
		return a_iFieldTemp;
	}
	// end-of method getFormData()

	public String[] getFormDataStringArray(DynaActionForm oFrm,	String field) {
		if(null == oFrm) return null;
		if(null == field) return null;
		String[] a_aszTemp = new String[255];
		String[] a_aszFieldTemp = new String[0];
		int iArraySize=0;
		String aszTmp="";
		try {
			a_aszTemp = (String[])oFrm.get(field) ;
			for(int i=0; i < a_aszTemp.length; i++){
				aszTmp=a_aszTemp[i];
				iArraySize++;
				if(aszTmp.length() < 1){
					break;
				}
			}
    	} catch (Exception e){
    		a_aszTemp=null;
    	}
		a_aszFieldTemp = new String[iArraySize];
    	for(int i=0; i<iArraySize; i++){
    		a_aszFieldTemp[i]=a_aszTemp[i];
    	}
		if(null == a_aszFieldTemp) return null;
		return a_aszFieldTemp;
	}

	/**
	* set dynamic form data
	*/
	public void setFormData(DynaActionForm oFrm, String field, String value){
		if(null == oFrm) return;
		if(null == field) return;
		String aszTemp=value;
		if(null == aszTemp) aszTemp="";
    	try {
    		oFrm.set(field, aszTemp );
    	} catch (Exception e){
    		return ;
    	}
		return ;
	}
	// end-of method setFormData()

	/**
	* set dynamic form data
	*/
	public void setFormDataFile(DynaActionForm oFrm, String field, FormFile value){
		if(null == oFrm) return;
		if(null == field) return;
		FormFile temp=value;
		if(null == temp) temp=null;
    	try {
    		oFrm.set(field, temp );
    	} catch (Exception e){
    		return ;
    	}
		return ;
	}
	// end-of method setFormData()


	/**
	* @author Amit Gupta
	* @Web http://www.roseindia.net
	* @Email struts@roseindia.net
	*/

	/**
	 * Form bean for Struts File Upload.
	 *
	*/
	public class StrutsUploadAndSaveForm extends ActionForm
	{
	  private FormFile theFile;

	  /**
	   * @return Returns the theFile.
	   */
	  public FormFile getTheFile() {
	    return theFile;
	  }
	  /**
	   * @param theFile The FormFile to set.
	   */
	  public void setTheFile(FormFile theFile) {
	    this.theFile = theFile;
	  }
	} 
	  /**
	  * Covert String to Integer 
	  */
		public int convertToInt(String number){
			Integer intObj=null;
	    int iTemp=0;
			if(null == number) return 0;
			String asTemp = number.trim();
			if(asTemp.length() < 1) return 0;
	      asTemp = getNumbersOnly( number );
			try {
				intObj = Integer.valueOf(asTemp);
				iTemp=intObj.intValue();
			} catch (NumberFormatException  ex){
				iTemp=0;
			}
			return iTemp;
		}
	  // end-of convertToInt()


		/**
		* remove spaces / commas / $  from string
	  * allow negative value as -2345.00 and (234.55)
		*/
		public String getNumbersOnly(String aszIn){
			String asTemp=null;
			char aChar;
			StringBuffer aBuff=null;
			int iLen=0,iIndex=0;
			if(null == aszIn) return "";
			asTemp = aszIn.trim();
			iLen = asTemp.length();
			if(iLen < 1) return "";
			aBuff = new StringBuffer(iLen + 2);
	    if( asTemp.charAt(0) == '(' ){
	      if(asTemp.charAt( iLen-1 ) == ')' ){
	        aBuff.append('-');
	      }
	    }
	    if( asTemp.charAt(0) == '-' ){
	      aBuff.append('-');
	    }
			for(iIndex=0; iIndex < iLen; iIndex++){
				aChar = asTemp.charAt(iIndex);
				switch(aChar){
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '.':
					aBuff.append(aChar);
					break;	
				}
			}
			return aBuff.toString();
		}
	  // end-of getNumbersOnly()
	
	public Location getLocation(HttpServletRequest request) throws IOException {
		if(request==null)	return null;
		if(request.getSession()==null) return null;
		if(request.getHeader("X-FORWARDED-FOR")==null) return null;
		String[] userAgentsToIgnore = {
		  "Bot", "Spider", "Crawler", "Yahoo"
		};
		for(String agent : userAgentsToIgnore) {
			String[] cases = {
		        agent, agent.toLowerCase()
			};
			for(String s : cases) {
				if(request.getHeader("user-agent")==null){
					return null;
				}
				if(request.getHeader("user-agent").contains(s))
					return null;
			}
		}
		
		Location loc = null;
//		if(request.getSession().getAttribute("location") == null)	return null;
		loc = (Location) request.getSession().getAttribute("location");
		if(loc != null) return loc;
		
		LookupService lookupService = getGeoIPLookupService(request);
		loc = lookupService.getLocation(request.getHeader("X-FORWARDED-FOR"));		
		request.getSession().setAttribute("location", loc);		
		return loc;
	}
	
	private LookupService geoIPLookupService = null;
	private LookupService getGeoIPLookupService(HttpServletRequest request) throws IOException {
		if(geoIPLookupService == null && request.getHeader("host")!=null) {
			if(request.getHeader("host").contains(":7001"))
				geoIPLookupService = new LookupService("C:\\GeoLiteCity.dat", LookupService.GEOIP_MEMORY_CACHE );
		    else
		        geoIPLookupService = new LookupService("/usr/local/share/GeoIP/GeoLiteCity.dat", LookupService.GEOIP_MEMORY_CACHE );
		}
		return geoIPLookupService;
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
        ArrayList aOrgList = new ArrayList();
        ArrayList aOppList = new  ArrayList ( 2 );
        ArrayList aListAdmin = new ArrayList();
        ArrayList aOppListAdmin = new  ArrayList ( 2 );
        ArrayList aListContact = new ArrayList();
        ArrayList aOppListContact = new  ArrayList ( 2 );

        allocatedIndBRLO( httpServletRequest );
        allocatedOrgBRLO( httpServletRequest );
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
     	String aszPortal = "", aszFileLocation = "", aszPortalHeaderTags="", aszPortalHeader="", aszPortalCSS="", aszPortalFooter="",
     		aszPortalOrgName = "", aszRequestType="";
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
	                	if(aszRequestType.length()>0){
	                     	session.setAttribute(aszPortal + "_type", aszRequestType);
	                	}
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
        httpServletRequest.setAttribute("location", getLocation(httpServletRequest));
      }
      catch(IOException e) {
        System.out.println("Error looking up location:");
        e.printStackTrace();
      }
    	if(httpServletRequest.getParameter("portal") != null ) if(httpServletRequest.getParameter("portal").length() > 0) aszPortal = httpServletRequest.getParameter("portal");
    	if(session.getAttribute(aszPortal+"_nid") != null ) if(session.getAttribute(aszPortal+"_nid").toString().length() > 0) aszPortalNID = session.getAttribute(aszPortal+"_nid").toString();
      if(session.getAttribute(aszPortal+"_uid") != null ) if(session.getAttribute(aszPortal+"_uid").toString().length() > 0) aszPortalUID = session.getAttribute(aszPortal+"_uid").toString();
    	if(session.getAttribute(aszPortal+"_type") != null ) if(session.getAttribute(aszPortal+"_type").toString().length() > 0) aszPortalRequestType = session.getAttribute(aszPortal+"_type").toString();
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

	final Charset windowsCharset = Charset.forName("windows-1252");
	final Charset utfCharset = Charset.forName("UTF-16");

    private BaseServletABRE m_BaseServletABREObj = new BaseServletABRE();
	private OrganizationBRLO m_OrgBRLOObj=null;
	private IndividualsBRLO m_IndBRLOObj=null;
	private EmailBRLO m_EmailBRLOObj=null;
	protected String aszPortal="", aszPortalNID="", aszPortalRequestType="", aszPortalUID="";
	protected HttpSession session = null;
	protected PersonInfoDTO aCurrentUserObj = new PersonInfoDTO();
	protected String aszLoggedInStatus = ""; 

}
