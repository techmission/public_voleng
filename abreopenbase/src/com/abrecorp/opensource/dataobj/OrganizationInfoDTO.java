package com.abrecorp.opensource.dataobj;

/**
* Code Generated Data Transfer Object DTO Class
* For Table organizationinfo
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	// managing cases:
	public static final int LOADBY_MANAGE_NATL_ASSOC=101;
	public static final int LOADBY_ORGNID_MANAGE=102;
	public static final int LOADBY_ORG_ORDER_EXPIRATION=103;
	public static final int LOADBY_UID=104;
	public static final int LOADBY_UID_ADMIN=105;
	public static final int LOADBY_UID_CONTACT=106;
	public static final int LOADBY_SITEADMIN=107;
	public static final int LOADBY_NATL_ASSOC=108;// is an admin of org with provided natl affil
	public static final int LOADBY_NATL_ASSOC_ORG_AFFIL=109;
	
	// public search cases:
	public static final int LOADBY_ORGNUMBER=201;
	public static final int LOADBY_ORGNUMBER_PUBLIC=202;
	public static final int LOADBY_ORGNID=203;
	public static final int LOADBY_ORGNID_PUBLIC=204;
	public static final int LOADBY_ORGNID_EMAIL=205;
	public static final int LOADBY_OPPNID = 214;
	public static final int LOADBY_URL_ALIAS=206;
	public static final int LOADBY_ORGURL_ID_W_ALIAS=207;
	public static final int LOADBY_ORGNID_PUBLIC_W_ALIAS=208;
	public static final int LOADBY_OPPCONTACTS=209;
	public static final int LOADBY_NID_FEED=210;
	public static final int LOADBY_URL_ALIAS_FEED=211;
	public static final int LOADBY_NID_JOBS_FEED=212;
	public static final int LOADBY_URL_ALIAS_FEEDS=213;
	public static final int LOADBY_URL_ALIAS_FDN=213;
	
	public static final int LOADBY_ORGNID_PUBLIC_NO_CONTACT=219;

	public static final int LOADBY_OPPURL_ID_W_ALIAS=221;
	public static final int LOADBY_OPP_URL_ALIAS=222;

	// search types
	public static final int SEARCH_TYPE_TOPMOST=301;
	// set search types for sorted search results
	public static final int SEARCH_TYPE_ADVANCE_TYPE=303;
	public static final int SEARCH_TYPE_ADVANCE_POSTAL=304;
	public static final int SEARCH_TYPE_ADVANCE_ORGNAME=309;
	public static final int SEARCH_TYPE_ADVANCE_CITY=314;
	public static final int SEARCH_TYPE_ADVANCE_STATE=315;
	public static final int SEARCH_TYPE_ADVANCE_PROV=316;
	public static final int SEARCH_TYPE_ADVANCE_COUNTRY=317;
	public static final int SEARCH_TYPE_ADVANCE_DENOMAFFIL=318;
	public static final int SEARCH_TYPE_ADVANCE_AFFIL1=319;
	public static final int SEARCH_TYPE_ADVANCE_OPPNAME=320;
	public static final int SEARCH_TYPE_ADVANCE_OPPNUMVOL=322;
	public static final int SEARCH_TYPE_ADVANCE_STMDURATION=323;
	public static final int SEARCH_TYPE_ADVANCE_STMCOST=324;
	public static final int SEARCH_TYPE_ADVANCE_UPDATEDT=325;
	public static final int SEARCH_TYPE_ADVANCE_DISTANCE=326;
	public static final int SEARCH_TYPE_ADVANCE_POPULARITY=327;
	public static final int SEARCH_TYPE_ADVANCE_NUM_VOL_ORG=328;

	public static final int SEARCH_TYPE_INCL_FAVORITES=340;

	public static final int SEARCH_TYPE_TOPMOST_IVOL=351;

	/**
	* constructor
	*/
	public OrganizationInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			OrganizationInfoDTO el = (OrganizationInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}
	
	private int m_socialGraphIAgreeDataUsage = 0;
	public int getSocialGraphIAgreeDataUsage() {
		return m_socialGraphIAgreeDataUsage;
	}
	public void setSocialGraphIAgreeDataUsage(int val) {
		m_socialGraphIAgreeDataUsage = val;
	}
	
	private int m_socialGraphIAgreeCaching = 0;
	public int getSocialGraphIAgreeCaching() {
		return m_socialGraphIAgreeCaching;
	}
	public void setSocialGraphIAgreeCaching(int val) {
		m_socialGraphIAgreeCaching = val;
	}
	
	private int m_socialGraphIAgreeLogo = 0;
	public int getSocialGraphIAgreeLogo() {
		return m_socialGraphIAgreeLogo;
	}
	public void setSocialGraphIAgreeLogo(int val) {
		m_socialGraphIAgreeLogo = val;
	}
	
	private int m_socialGraphIAgreeFinal = 0;
	public int getSocialGraphIAgreeFinal() {
		return m_socialGraphIAgreeFinal;
	}
	public void setSocialGraphIAgreeFinal(int val) {
		m_socialGraphIAgreeFinal = val;
	}
	
	private String m_socialGraphExplanation = "";
	public String getSocialGraphExplanation() {
		return m_socialGraphExplanation;
	}
	public void setSocialGraphExplanation(String val) {
		m_socialGraphExplanation = val;
	}
	
	private String m_socialGraphSignature = "";
	public String getSocialGraphSignature() {
		return m_socialGraphSignature;
	}
	public void setSocialGraphSignature(String val) {
		m_socialGraphSignature = val;
	}
	
	
	//=== START Foundation Fields ===>
	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===<


	
	private String m_aszGeoFocus = null;
	public void setGeoFocus(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszGeoFocus = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszGeoFocus = value;
			return;
		}
		m_aszGeoFocus = value.substring(0,iLen);
	}
	public String getGeoFocus(){
		if(m_aszGeoFocus == null) return "";
		return m_aszGeoFocus;
	}
	
	private String m_aszFundingLimitations = null;
	public void setFundingLimitations(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszFundingLimitations = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszFundingLimitations = value;
			return;
		}
		m_aszFundingLimitations = value.substring(0,iLen);
	}
	public String getFundingLimitations(){
		if(m_aszFundingLimitations == null) return "";
		return m_aszFundingLimitations;
	}
	
	private String m_aszTypeOfFunder = null;
	public void setTypeOfFunder(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszTypeOfFunder = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszTypeOfFunder = value;
			return;
		}
		m_aszTypeOfFunder = value.substring(0,iLen);
	}
	public String getTypeOfFunder(){
		if(m_aszTypeOfFunder == null) return "";
		return m_aszTypeOfFunder;
	}
	
	private int m_iYearFounded = 0;
	public void setYearFounded(int number){
		m_iYearFounded = number;
	}
	public void setYearFounded(String number){
		m_iYearFounded = convertToInt(number);
		return;
	}
	public int getYearFounded(){
		return m_iYearFounded;
	}
	
	private String m_aszAssets = null;
	public void setAssets(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszAssets = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszAssets = value;
			return;
		}
		m_aszAssets = value.substring(0,iLen);
	}
	public String getAssets(){
		if(m_aszAssets == null) return "";
		return m_aszAssets;
	}
	
	private String m_aszIncome = null;
	public void setIncome(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszIncome = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszIncome = value;
			return;
		}
		m_aszIncome = value.substring(0,iLen);
	}
	public String getIncome(){
		if(m_aszIncome == null) return "";
		return m_aszIncome;
	}
	
	private String m_aszExpenditures = null;
	public void setExpenditures(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszExpenditures = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszExpenditures = value;
			return;
		}
		m_aszExpenditures = value.substring(0,iLen);
	}
	public String getExpenditures(){
		if(m_aszExpenditures == null) return "";
		return m_aszExpenditures;
	}
	
	private String m_aszTotalGiving = null;
	public void setTotalGiving(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszTotalGiving = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszTotalGiving = value;
			return;
		}
		m_aszTotalGiving = value.substring(0,iLen);
	}
	public String getTotalGiving(){
		if(m_aszTotalGiving == null) return "";
		return m_aszTotalGiving;
	}
	
	private String m_aszRevenue = null;
	public void setRevenue(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszRevenue = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszRevenue = value;
			return;
		}
		m_aszRevenue = value.substring(0,iLen);
	}
	public String getRevenue(){
		if(m_aszRevenue == null) return "";
		return m_aszRevenue;
	}
	
	private String m_aszNTEEMajorCategory = null;
	public void setNTEEMajorCat(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszNTEEMajorCategory = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszNTEEMajorCategory = value;
			return;
		}
		m_aszNTEEMajorCategory = value.substring(0,iLen);
	}
	public String getNTEEMajorCat(){
		if(m_aszNTEEMajorCategory == null) return "";
		return m_aszNTEEMajorCategory;
	}
	
	private String m_aszNTEEMinorCategory = null;
	public void setNTEEMinorCat(String value){
		int iLen=255;
		if(value == null) value="";
		value = value.trim();
		if(value.length() < 1){
			m_aszNTEEMinorCategory = null;
			return;
		}
		if(value.length() < iLen + 1){
			m_aszNTEEMinorCategory = value;
			return;
		}
		m_aszNTEEMinorCategory = value.substring(0,iLen);
	}
	public String getNTEEMinorCat(){
		if(m_aszNTEEMinorCategory == null) return "";
		return m_aszNTEEMinorCategory;
	}
	

	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===
	//=== END   Foundation Fields ===

	
	
	
	
	
	//=== BEGIN   IRS Parachurch data Fields ===
	//=== BEGIN   IRS Parachurch data Fields ===
	//=== BEGIN   IRS Parachurch data Fields ===

	
	//=== END   IRS Parachurch data Fields ===
	//=== END   IRS Parachurch data Fields ===
	//=== END   IRS Parachurch data Fields ===
	
	
	
	
	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table organizationinfo 
	//===> Start Code Generated Methods For Table organizationinfo 
	//===> Start Code Generated Methods For Table organizationinfo 

	/**
	 * city vision site interest
	 */
	private boolean m_siteInterest;
	public boolean getSiteInterest() {
		if(!(this.getORGAddrCountryName().equalsIgnoreCase("united states") 
		       ||
		     this.getORGAddrCountryName().equalsIgnoreCase("us")
		       ||
		     this.getORGAddrCountryName().equalsIgnoreCase("canada")
		       ||
		     this.getORGAddrCountryName().equalsIgnoreCase("ca")))
			return false;
		return m_siteInterest;
	}
	public void setSiteInterest(boolean siteInterest) {
		m_siteInterest = siteInterest;
	}
	public void setSiteInterest(int siteInterest) {
		m_siteInterest = siteInterest == 0 ? false : true;
	}

	/**
	** orgurl id
	**/
	private int m_iORGurlID=0;
	public void setORGurlID(int number){
		m_iORGurlID = number;
	}
	public void setORGurlID(String number){
		m_iORGurlID = convertToInt(number);
		return;
	}
	public int getORGurlID(){
		return m_iORGurlID;
	}

	/**
	** org_number type LONG() in table organizationinfo 
	**/
	private int m_iORGOrgNumber=0;
	public void setORGOrgNumber(int number){
		m_iORGOrgNumber = number;
	}
	public void setORGOrgNumber(String number){
		m_iORGOrgNumber = convertToInt(number);
		return;
	}
	public int getORGOrgNumber(){
		return m_iORGOrgNumber;
	}

	/**
	** organization nid type LONG() in  
	*  table um_node, um_content_type_organization and um_node_revisions 
	**/
	private int m_iORGNID=0;
	public void setORGNID(int number){
		m_iORGNID = number;
	}
	public void setORGNID(String number){
		m_iORGNID = convertToInt(number);
		return;
	}
	public int getORGNID(){
		return m_iORGNID;
	}

	/**
	** organization vid type LONG() in  
	*  table um_node, um_content_type_organizationy and um_node_revisions 
	**/
	private int m_iORGVID=0;
	public void setORGVID(int number){
		m_iORGVID = number;
	}
	public void setORGVID(String number){
		m_iORGVID = convertToInt(number);
		return;
	}
	public int getORGVID(){
		return m_iORGVID;
	}

	/**
	** organization lid type LONG() in  
	*  table um_node, um_content_type_organizationy and um_node_revisions 
	**/
	private int m_iORGLID=0;
	public void setORGLID(int number){
		m_iORGLID = number;
	}
	public void setORGLID(String number){
		m_iORGLID = convertToInt(number);
		return;
	}
	public int getORGLID(){
		return m_iORGLID;
	}

	/**
	** organization number of opportunities on our site 
	**/
	private int m_iORGNumOpps=0;
	public void setORGNumOpps(int number){
		m_iORGNumOpps = number;
	}
	public void setORGNumOpps(String number){
		m_iORGNumOpps = convertToInt(number);
		return;
	}
	public int getORGNumOpps(){
		return m_iORGNumOpps;
	}

	/**
	** import source 
	**/
	private String m_aszSource=null;
	public void setSource(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSource = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSource = aszTemp;
			return;
		}
		m_aszSource = aszTemp.substring(0,iLen);
	}
	public String getSource(){
		if(m_aszSource == null) return "";
		return m_aszSource;
	}

	/**
	** City Vision Internship approved site (when value= City Vision)
	**/
	private String m_aszCVInternSiteApproved=null;
	public void setCVInternSiteApproved(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCVInternSiteApproved = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCVInternSiteApproved = aszTemp;
			return;
		}
		m_aszCVInternSiteApproved = aszTemp.substring(0,iLen);
	}
	public String getCVInternSiteApproved(){
		if(m_aszCVInternSiteApproved == null) return "";
		return m_aszCVInternSiteApproved;
	}
	
	/**
	** organization number of opportunities m_iORGNumOppsPublished on our site 
	**/
	private int m_iORGNumOppsPublished=0;
	public void setORGNumOppsPublished(int number){
		m_iORGNumOppsPublished = number;
	}
	public void setORGNumOppsPublished(String number){
		m_iORGNumOppsPublished = convertToInt(number);
		return;
	}
	public int getORGNumOppsPublished(){
		return m_iORGNumOppsPublished;
	}

	/**
	** organization moderate type LONG() in  
	*  table um_node, whether org is in moderation queue or not 
	**/
	private int m_iORGModerate=0;
	public void setORGModerate(int number){
		m_iORGModerate = number;
	}
	public void setORGModerate(String number){
		m_iORGModerate = convertToInt(number);
		return;
	}
	public int getORGModerate(){
		return m_iORGModerate;
	}

	/**
	** organization status - says whether published or not - type LONG() in  
	*  table um_node, whether org is in moderation queue or not 
	**/
	private int m_iORGPublished=0;
	public void setORGPublished(int number){
		m_iORGPublished = number;
	}
	public void setORGPublished(String number){
		m_iORGPublished = convertToInt(number);
		return;
	}
	public int getORGPublished(){
		return m_iORGPublished;
	}


	/**
	** org tm member
	**/
	private int m_iORGMember=0;
	public void setORGMember(int number){
		m_iORGMember = number;
	}
	public void setORGMember(String number){
		m_iORGMember = convertToInt(number);
		return;
	}
	public int getORGMember(){
		return m_iORGMember;
	}

	/**
	** orgcodekey type CHAR(20) in table organizationinfo 
	**/
	private String m_aszORGOrgcodekey=null;
	public void setORGOrgcodekey(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOrgcodekey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOrgcodekey = aszTemp;
			return;
		}
		m_aszORGOrgcodekey = aszTemp.substring(0,iLen);
	}
	public String getORGOrgcodekey(){
		if(m_aszORGOrgcodekey == null) return "";
		return m_aszORGOrgcodekey;
	}



	/**
	*  request type
	*/
	private String m_aszRequestType=null;
	public void setRequestType(String key){
		if(null == key){
			m_aszRequestType=null;
			return ;
		}
		m_aszRequestType=key.trim();
	}
	public String getRequestType(){
		if(null == m_aszRequestType) return "";
		return m_aszRequestType;
	}

	/**
	** primary_person type LONG() in table organizationinfo 
	**/
	private int m_iORGPrimaryPerson=0;
	public void setORGPrimaryPerson(int number){
		m_iORGPrimaryPerson = number;
	}
	public void setORGPrimaryPerson(String number){
		m_iORGPrimaryPerson = convertToInt(number);
		return;
	}
	public int getORGPrimaryPerson(){
		return m_iORGPrimaryPerson;
	}



	/**
	** primary person uid type LONG() in table 
	*  - allows for ownership in the drupal urbanministry.org system
	*  table um_node and um_node_revisions 
	**/
	private int m_iORGUID=0;
	public void setORGUID(int number){
		m_iORGUID = number;
	}
	public void setORGUID(String number){
		m_iORGUID = convertToInt(number);
		return;
	}
	public int getORGUID(){
		return m_iORGUID;
	}


	/**
	** loaded by 
	**/
	private int m_iLoadByMethod=0;
	public void setLoadByMethod(int number){
		m_iLoadByMethod = number;
	}
	public void setLoadByMethod(String number){
		m_iLoadByMethod = convertToInt(number);
		return;
	}
	public int getLoadByMethod(){
		return m_iLoadByMethod;
	}


	/*
	 * aListOrgContactRemoveSkipped
	 */
	private ArrayList<Object> aListOrgContactRemoveSkipped=new ArrayList<Object>(2);
	public void setOrgContactRemoveSkipped(ArrayList<Object> values){
		if(values.size()>0){
			aListOrgContactRemoveSkipped = values;
		}
		return;
	}
	public ArrayList<Object> getOrgContactRemoveSkipped(){
		if(aListOrgContactRemoveSkipped == null) {
			aListOrgContactRemoveSkipped=new ArrayList<Object>(2);
			return aListOrgContactRemoveSkipped;
		}
		return aListOrgContactRemoveSkipped;
	}


	/*
	 * a_iOppNIDsArray
	 */
	private int[] a_iOppNIDsArray=null;
	public void setOppNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOppNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOppNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOppNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getOppNIDsArray(){
		if(a_iOppNIDsArray == null) {
			a_iOppNIDsArray=new int[0];
			return a_iOppNIDsArray;
		}
		return a_iOppNIDsArray;
	}

	
	/*
	 * a_iORGAdminUIDsArray
	 */
	private int[] a_iORGAdminUIDsArray=null;
	public void setORGAdminUIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iORGAdminUIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGAdminUIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGAdminUIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getORGAdminUIDsArray(){
		if(a_iORGAdminUIDsArray == null) {
			a_iORGAdminUIDsArray=new int[0];
			return a_iORGAdminUIDsArray;
		}
		return a_iORGAdminUIDsArray;
	}

	/*
	 * a_iORGAdminUIDsModifiedArray
	 */
	private int[] a_iORGAdminUIDsModifiedArray=null;
	public void setORGAdminUIDsModifiedArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iORGAdminUIDsModifiedArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGAdminUIDsModifiedArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGAdminUIDsModifiedArray = a_iTemp;
			return;
		}
	}
	public int[] getORGAdminUIDsModifiedArray(){
		if(a_iORGAdminUIDsModifiedArray == null) {
			a_iORGAdminUIDsModifiedArray=new int[0];
			return a_iORGAdminUIDsModifiedArray;
		}
		return a_iORGAdminUIDsModifiedArray;
	}


	/*
	 * a_iORGContactUIDsArray
	 */
	private int[] a_iORGContactUIDsArray=null;
	public void setORGContactUIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGContactUIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGContactUIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGContactUIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getORGContactUIDsArray(){
		if(a_iORGContactUIDsArray == null) {
			a_iORGContactUIDsArray=new int[0];
			return a_iORGContactUIDsArray;
		}
		return a_iORGContactUIDsArray;
	}

	/*
	 * a_iORGContactUIDsModifiedArray
	 */
	private int[] a_iORGContactUIDsModifiedArray=null;
	public void setORGContactUIDsModifiedArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGContactUIDsModifiedArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGContactUIDsModifiedArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGContactUIDsModifiedArray = a_iTemp;
			return;
		}
	}
	public int[] getORGContactUIDsModifiedArray(){
		if(a_iORGContactUIDsModifiedArray == null) {
			a_iORGContactUIDsModifiedArray=new int[0];
			return a_iORGContactUIDsModifiedArray;
		}
		return a_iORGContactUIDsModifiedArray;
	}
	/**
	** setSiteAdministratorUID
	**/
	private int m_iSiteAdministratorUID=0;
	public void setSiteAdministratorUID(int number){
		m_iSiteAdministratorUID = number;
	}
	public void setSiteAdministratorUID(String number){
		m_iSiteAdministratorUID = convertToInt(number);
		return;
	}
	public int getSiteAdministratorUID(){
		return m_iSiteAdministratorUID;
	}

	
	/*
	 * array of org nid's favorited by this org
	 */
	private int[] a_iORGFavoritedORGNIDs=null;
	public void setORGFavoritedORGNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGFavoritedORGNIDs = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGFavoritedORGNIDs = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGFavoritedORGNIDs = a_iTemp;
			return;
		}
	}
	public int[] getORGFavoritedORGNIDsArray(){
		if(a_iORGFavoritedORGNIDs == null) {
			a_iORGFavoritedORGNIDs=new int[0];
			return a_iORGFavoritedORGNIDs;
		}
		return a_iORGFavoritedORGNIDs;
	}
	
	/*
	 * array of opp nid's favorited by this org
	 */
	private int[] a_iORGFavoritedOPPNIDs=null;
	public void setORGFavoritedOPPNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGFavoritedOPPNIDs = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGFavoritedOPPNIDs = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGFavoritedOPPNIDs = a_iTemp;
			return;
		}
	}
	public int[] getORGFavoritedOPPNIDsArray(){
		if(a_iORGFavoritedOPPNIDs == null) {
			a_iORGFavoritedOPPNIDs=new int[0];
			return a_iORGFavoritedOPPNIDs;
		}
		return a_iORGFavoritedOPPNIDs;
	}
	
	/*
	 * array of org nid's previously favorited by this org
	 */
	private int[] a_iORGPrevFavoritedORGNIDs=null;
	public void setORGPrevFavoritedORGNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGPrevFavoritedORGNIDs = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGPrevFavoritedORGNIDs = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGPrevFavoritedORGNIDs = a_iTemp;
			return;
		}
	}
	public int[] getORGPrevFavoritedORGNIDsArray(){
		if(a_iORGPrevFavoritedORGNIDs == null) {
			a_iORGPrevFavoritedORGNIDs=new int[0];
			return a_iORGPrevFavoritedORGNIDs;
		}
		return a_iORGPrevFavoritedORGNIDs;
	}
	
	/*
	 * array of opp nid's previously favorited by this org
	 */
	private int[] a_iORGPrevFavoritedOPPNIDs=null;
	public void setORGPrevFavoritedOPPNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGPrevFavoritedOPPNIDs = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGPrevFavoritedOPPNIDs = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGPrevFavoritedOPPNIDs = a_iTemp;
			return;
		}
	}
	public int[] getORGPrevFavoritedOPPNIDsArray(){
		if(a_iORGPrevFavoritedOPPNIDs == null) {
			a_iORGPrevFavoritedOPPNIDs=new int[0];
			return a_iORGPrevFavoritedOPPNIDs;
		}
		return a_iORGPrevFavoritedOPPNIDs;
	}
	
	
	
	/*
	 * array of opp nid's favorited by this org
	 */
	private int[] a_iORGFavoritedOPPNIDsFromFeed=null;
	public void setORGFavoritedOPPNIDsFromFeedArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGFavoritedOPPNIDsFromFeed = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGFavoritedOPPNIDsFromFeed = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGFavoritedOPPNIDsFromFeed = a_iTemp;
			return;
		}
	}
	public int[] getORGFavoritedOPPNIDsFromFeedArray(){
		if(a_iORGFavoritedOPPNIDsFromFeed == null) {
			a_iORGFavoritedOPPNIDsFromFeed=new int[0];
			return a_iORGFavoritedOPPNIDsFromFeed;
		}
		return a_iORGFavoritedOPPNIDsFromFeed;
	}
	
	
	/*
	 * array of opp nid's previously favorited by this org
	 */
	private int[] a_iORGPrevFavoritedOPPNIDsFromFeed=null;
	public void setORGPrevFavoritedOPPNIDsFromFeedArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGPrevFavoritedOPPNIDsFromFeed = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGPrevFavoritedOPPNIDsFromFeed = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGPrevFavoritedOPPNIDsFromFeed = a_iTemp;
			return;
		}
	}
	public int[] getORGPrevFavoritedOPPNIDsFromFeedArray(){
		if(a_iORGPrevFavoritedOPPNIDsFromFeed == null) {
			a_iORGPrevFavoritedOPPNIDsFromFeed=new int[0];
			return a_iORGPrevFavoritedOPPNIDsFromFeed;
		}
		return a_iORGPrevFavoritedOPPNIDsFromFeed;
	}
	
	/*
	 * array of child opp nid's of this org
	 */
	private int[] a_iORGChildOPPNIDs=null;
	public void setORGChildOPPNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iORGChildOPPNIDs = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGChildOPPNIDs = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGChildOPPNIDs = a_iTemp;
			return;
		}
	}
	public int[] getORGChildOPPNIDsArray(){
		if(a_iORGChildOPPNIDs == null) {
			a_iORGChildOPPNIDs=new int[0];
			return a_iORGChildOPPNIDs;
		}
		return a_iORGChildOPPNIDs;
	}
	
	/**
	** CurrentDomain 
	**/
	private String m_aszCurrentDomain=null;
	public void setCurrentDomain(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCurrentDomain = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCurrentDomain = aszTemp;
			return;
		}
		m_aszCurrentDomain = aszTemp.substring(0,iLen);
	}
	public String getCurrentDomain(){
		if(m_aszCurrentDomain == null) return "";
		return m_aszCurrentDomain;
	}


	/**
	 * would you like to favorite all child opportunities of the given organization?
	 */
	private boolean bFavoriteChildOpps=false;
	public void setFavoriteChildOpps(boolean value){
		if(value==true){
			bFavoriteChildOpps = value;
		}
		return;
	}
	public boolean getFavoriteChildOpps(){
		return bFavoriteChildOpps;
	}

	
	/**
	** membership_status type VARCHAR(50) in table organizationinfo 
	**/
	private String m_aszORGMembershipStatus=null;
	public void setORGMembershipStatus(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGMembershipStatus = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGMembershipStatus = aszTemp;
			return;
		}
		m_aszORGMembershipStatus = aszTemp.substring(0,iLen);
	}
	public String getORGMembershipStatus(){
		if(m_aszORGMembershipStatus == null) return "";
		return m_aszORGMembershipStatus;
	}


	/**
	** membership_type type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGMembershipType=null;
	public void setORGMembershipType(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGMembershipType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGMembershipType = aszTemp;
			return;
		}
		m_aszORGMembershipType = aszTemp.substring(0,iLen);
	}
	public String getORGMembershipType(){
		if(m_aszORGMembershipType == null) return "";
		return m_aszORGMembershipType;
	}


	/**
	** membership_dt type DATETIME() in table organizationinfo 
	**/
	private java.util.Date m_azdORGMembershipDt=null;
	public void setORGMembershipDt(java.util.Date value){
		if(value == null){
			m_azdORGMembershipDt = null;
			return;
		}
		m_azdORGMembershipDt = value;
	}
	public java.util.Date getORGMembershipDt(){
		if(m_azdORGMembershipDt == null) return null;
		return m_azdORGMembershipDt;
	}


	/**
	** create_dt type DATETIME() in table organizationinfo 
	**/
	private java.util.Date m_azdORGCreateDt=null;
	public void setORGCreateDt(java.util.Date value){
		if(value == null){
			m_azdORGCreateDt = null;
			return;
		}
		m_azdORGCreateDt = value;
	}
	public java.util.Date getORGCreateDt(){
		if(m_azdORGCreateDt == null) return null;
		return m_azdORGCreateDt;
	}

	// ORGCreate_dt type LONG() in table drupal um_node
	
	private int m_iORGCreateDtNum=0;
	public void setORGCreateDtNum(int number){
		m_iORGCreateDtNum = number;
	}
	public void setORGCreateDtNum(String number){
		m_iORGCreateDtNum = convertToInt(number);
		return;
	}
	public int getORGCreateDtNum(){
		return m_iORGCreateDtNum;
	}


	/**
	** create_id type LONG() in table organizationinfo 
	**/
	private int m_iORGCreateId=0;
	public void setORGCreateId(int number){
		m_iORGCreateId = number;
	}
	public void setORGCreateId(String number){
		m_iORGCreateId = convertToInt(number);
		return;
	}
	public int getORGCreateId(){
		return m_iORGCreateId;
	}


	/**
	** update_dt type DATETIME() in table organizationinfo 
	**/
	private java.util.Date m_azdORGUpdateDt=null;
	public void setORGUpdateDt(java.util.Date value){
		if(value == null){
			m_azdORGUpdateDt = null;
			return;
		}
		m_azdORGUpdateDt = value;
	}
	public java.util.Date getORGUpdateDt(){
		if(m_azdORGUpdateDt == null) return null;
		return m_azdORGUpdateDt;
	}

	// update_dt type LONG() in table drupal um_node
	
	private int m_iORGUpdateDtNum=0;
	public void setORGUpdateDtNum(int number){
		m_iORGUpdateDtNum = number;
	}
	public void setORGUpdateDtNum(String number){
		m_iORGUpdateDtNum = convertToInt(number);
		return;
	}
	public int getORGUpdateDtNum(){
		return m_iORGUpdateDtNum;
	}


	/**
	** update_id type LONG() in table organizationinfo 
	**/
	private int m_iORGUpdateId=0;
	public void setORGUpdateId(int number){
		m_iORGUpdateId = number;
	}
	public void setORGUpdateId(String number){
		m_iORGUpdateId = convertToInt(number);
		return;
	}
	public int getORGUpdateId(){
		return m_iORGUpdateId;
	}


	/**
	** org_name type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGOrgName=null;
	public void setORGOrgName(String value){
		int iLen=128;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOrgName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOrgName = aszTemp;
			return;
		}
		m_aszORGOrgName = aszTemp.substring(0,iLen);
	}
	public String getORGOrgName(){
		if(m_aszORGOrgName == null) return "";
		return m_aszORGOrgName;
	}


	/**
	** alternate_name type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAlternateName=null;
	public void setORGAlternateName(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAlternateName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAlternateName = aszTemp;
			return;
		}
		m_aszORGAlternateName = aszTemp.substring(0,iLen);
	}
	public String getORGAlternateName(){
		if(m_aszORGAlternateName == null) return "";
		return m_aszORGAlternateName;
	}


	/**
	** org_phone1 type VARCHAR(20) in table organizationinfo 
	**/
	private String m_aszORGOrgPhone1=null;
	public void setORGOrgPhone1(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOrgPhone1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOrgPhone1 = aszTemp;
			return;
		}
		m_aszORGOrgPhone1 = aszTemp.substring(0,iLen);
	}
	public String getORGOrgPhone1(){
		if(m_aszORGOrgPhone1 == null) return "";
		return m_aszORGOrgPhone1;
	}


	/**
	** org_phone2 type VARCHAR(20) in table organizationinfo 
	**/
	private String m_aszORGOrgPhone2=null;
	public void setORGOrgPhone2(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOrgPhone2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOrgPhone2 = aszTemp;
			return;
		}
		m_aszORGOrgPhone2 = aszTemp.substring(0,iLen);
	}
	public String getORGOrgPhone2(){
		if(m_aszORGOrgPhone2 == null) return "";
		return m_aszORGOrgPhone2;
	}


	/**
	** fax1 type VARCHAR(20) in table organizationinfo 
	**/
	private String m_aszORGFax1=null;
	public void setORGFax1(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGFax1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGFax1 = aszTemp;
			return;
		}
		m_aszORGFax1 = aszTemp.substring(0,iLen);
	}
	public String getORGFax1(){
		if(m_aszORGFax1 == null) return "";
		return m_aszORGFax1;
	}


	/**
	** fax2 type VARCHAR(20) in table organizationinfo 
	**/
	private String m_aszORGFax2=null;
	public void setORGFax2(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGFax2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGFax2 = aszTemp;
			return;
		}
		m_aszORGFax2 = aszTemp.substring(0,iLen);
	}
	public String getORGFax2(){
		if(m_aszORGFax2 == null) return "";
		return m_aszORGFax2;
	}


	/**
	** addr_line1 type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrLine1=null;
	public void setORGAddrLine1(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrLine1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrLine1 = aszTemp;
			return;
		}
		m_aszORGAddrLine1 = aszTemp.substring(0,iLen);
	}
	public String getORGAddrLine1(){
		if(m_aszORGAddrLine1 == null) return "";
		return m_aszORGAddrLine1;
	}


	/**
	** addr_line2 type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrLine2=null;
	public void setORGAddrLine2(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrLine2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrLine2 = aszTemp;
			return;
		}
		m_aszORGAddrLine2 = aszTemp.substring(0,iLen);
	}
	public String getORGAddrLine2(){
		if(m_aszORGAddrLine2 == null) return "";
		return m_aszORGAddrLine2;
	}


	/**
	** addr_line3 type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrLine3=null;
	public void setORGAddrLine3(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrLine3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrLine3 = aszTemp;
			return;
		}
		m_aszORGAddrLine3 = aszTemp.substring(0,iLen);
	}
	public String getORGAddrLine3(){
		if(m_aszORGAddrLine3 == null) return "";
		return m_aszORGAddrLine3;
	}


	/**
	** addr_city type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrCity=null;
	public void setORGAddrCity(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrCity = aszTemp;
			return;
		}
		m_aszORGAddrCity = aszTemp.substring(0,iLen);
	}
	public String getORGAddrCity(){
		if(m_aszORGAddrCity == null) return "";
		return m_aszORGAddrCity;
	}


	/**
	** addr_stateprov type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrStateprov=null;
	public void setORGAddrStateprov(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrStateprov = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrStateprov = aszTemp;
			return;
		}
		m_aszORGAddrStateprov = aszTemp.substring(0,iLen);
	}
	public String getORGAddrStateprov(){
		if(m_aszORGAddrStateprov == null) return "";
		return m_aszORGAddrStateprov;
	}


	/**
	** addr_postalcode type VARCHAR(40) in table organizationinfo 
	**/
	private String m_aszORGAddrPostalcode=null;
	public void setORGAddrPostalcode(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrPostalcode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrPostalcode = aszTemp;
			return;
		}
		m_aszORGAddrPostalcode = aszTemp.substring(0,iLen);
	}
	public String getORGAddrPostalcode(){
		if(m_aszORGAddrPostalcode == null) return "";
		return m_aszORGAddrPostalcode;
	}


	/**
	** addr_country_name type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGAddrCountryName=null;
	public void setORGAddrCountryName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrCountryName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrCountryName = aszTemp;
			return;
		}
		m_aszORGAddrCountryName = aszTemp.substring(0,iLen);
	}
	public String getORGAddrCountryName(){
		if(m_aszORGAddrCountryName == null) return "";
		return m_aszORGAddrCountryName;
	}


	/**
	** addr_county type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrCounty=null;
	public void setORGAddrCounty(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrCounty = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrCounty = aszTemp;
			return;
		}
		m_aszORGAddrCounty = aszTemp.substring(0,iLen);
	}
	public String getORGAddrCounty(){
		if(m_aszORGAddrCounty == null) return "";
		return m_aszORGAddrCounty;
	}


	/**
	* Latitude
	*/
    private float fLatitude=0;
	public void setLatitude(int number){
		fLatitude = number;
		return;
	}
	public void setLatitude(long number){
		fLatitude = number;
		return;
	}
	public void setLatitude(String number){
		try{
			fLatitude = new Float(number);
		}catch(NumberFormatException ex){
		}catch(Exception e){
		}
		return;
	}
	public float getLatitude(){
		return fLatitude;
	}


	/**
	* Longitude
	*/
    private float fLongitude=0;
	public void setLongitude(int number){
		fLongitude = number;
		return;
	}
	public void setLongitude(long number){
		fLongitude = number;
		return;
	}
	public void setLongitude(String number){
		try{
			fLongitude = new Float(number);
		}catch(NumberFormatException ex){
		}catch(Exception e){
		}
		return;
	}
	public float getLongitude(){
		return fLongitude;
	}


	/**
	** webpage type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGWebpage=null;
	public void setORGWebpage(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGWebpage = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGWebpage = aszTemp;
			return;
		}
		m_aszORGWebpage = aszTemp.substring(0,iLen);
	}
	public String getORGWebpage(){
		if(m_aszORGWebpage == null) return "";
		return m_aszORGWebpage;
	}


	/**
	** does this org have an opportunity Listings web URL for organization type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGHasListings=null;
	public void setORGHasListings(String value){
		int iLen=5;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGHasListings = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGHasListings = aszTemp;
			return;
		}
		m_aszORGHasListings = aszTemp.substring(0,iLen);
	}
	public String getORGHasListings(){
		if(m_aszORGHasListings == null) return "";
		return m_aszORGHasListings;
	}

	/**
	** Listings web URL for organization type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGListingsURL=null;
	public void setORGListingsURL(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGListingsURL = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGListingsURL = aszTemp;
			return;
		}
		m_aszORGListingsURL = aszTemp.substring(0,iLen);
	}
	public String getORGListingsURL(){
		if(m_aszORGListingsURL == null) return "";
		return m_aszORGListingsURL;
	}

	/**
	** donation web URL for organization type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGDonateURL=null;
	public void setORGDonateURL(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGDonateURL = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGDonateURL = aszTemp;
			return;
		}
		m_aszORGDonateURL = aszTemp.substring(0,iLen);
	}
	public String getORGDonateURL(){
		if(m_aszORGDonateURL == null) return "";
		return m_aszORGDonateURL;
	}


	/**
	** fed_tax_id type VARCHAR(20) in table organizationinfo 
	**/
	private String m_aszORGFedTaxId1 = null;
	private String m_aszORGFedTaxId2 = null;
	public void setORGFedTaxId(String piece1, String piece2){
      m_aszORGFedTaxId1 = piece1;
      m_aszORGFedTaxId2 = piece2;
	}
	public String getORGFedTaxId(){
		if(m_aszORGFedTaxId2 == null || m_aszORGFedTaxId2.length() == 0) return m_aszORGFedTaxId1;
		return m_aszORGFedTaxId1 + "-" + m_aszORGFedTaxId2;
	}
	public String getORGFedTaxId1(){
		return m_aszORGFedTaxId1;
	}
	public String getORGFedTaxId2(){
		return m_aszORGFedTaxId2;
	}
	public boolean getIsIRSCertified() {
		if(!(this.getORGAddrCountryName().equalsIgnoreCase("us")
		       ||
		     this.getORGAddrCountryName().equalsIgnoreCase("united states")))
			return false;
		if(this.getORGFedTaxId2() == null || this.getORGFedTaxId2().isEmpty()) {
			if(this.getORGFedTaxId1() == null || this.getORGFedTaxId1().length() != 10) return false;
			for(int i = 0; i < this.getORGFedTaxId1().length(); i++) {
				char c = this.getORGFedTaxId1().charAt(i);
				if(i == 2 && c != '-') return false;
				if(!Character.isDigit(c)) return false;
			}
			return true;
		}
		else {
			if(this.getORGFedTaxId1() == null 
			|| this.getORGFedTaxId1().length() != 2 
			|| this.getORGFedTaxId2().length() != 7)
				return false;
			for(int i = 0; i < this.getORGFedTaxId1().length(); i++)
				if(!Character.isDigit(this.getORGFedTaxId1().charAt(i))) 
					return false;
			for(int i = 0; i < this.getORGFedTaxId2().length(); i++) 
				if(!Character.isDigit(this.getORGFedTaxId2().charAt(i))) return false;
			return true;
		}
	}


	/**
	** other_id type VARCHAR(40) in table organizationinfo 
	**/
	private String m_aszORGOtherId=null;
	public void setORGOtherId(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOtherId = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOtherId = aszTemp;
			return;
		}
		m_aszORGOtherId = aszTemp.substring(0,iLen);
	}
	public String getORGOtherId(){
		if(m_aszORGOtherId == null) return "";
		return m_aszORGOtherId;
	}


	/**
	** services_offered type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGServicesOffered=null;
	public void setORGServicesOffered(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGServicesOffered = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGServicesOffered = aszTemp;
			return;
		}
		m_aszORGServicesOffered = aszTemp.substring(0,iLen);
	}
	public String getORGServicesOffered(){
		if(m_aszORGServicesOffered == null) return "";
		return m_aszORGServicesOffered;
	}


	
	/**
	** entered through a portal?? type INT
	**/
	private int m_iPortal=0;
	public void setPortal(int number){
		m_iPortal = number;
	}
	public void setPortal(String number){
		m_iPortal = convertToInt(number);
		return;
	}
	public int getPortal(){
		return m_iPortal;
	}
	
	/**
	** entered through a portal?? store the uid for the portal - loads favorites, etc. type INT
	**/
	private int m_iPortalUID=0;
	public void setPortalUID(int number){
		m_iPortalUID = number;
	}
	public void setPortalUID(String number){
		m_iPortalUID = convertToInt(number);
		return;
	}
	public int getPortalUID(){
		return m_iPortalUID;
	}

	/**
	** is this a portal?
	**/
	private String m_aszUsePortal="No";
	public void setUsePortal(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUsePortal = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUsePortal = aszTemp;
			return;
		}
		m_aszUsePortal = aszTemp.substring(0,iLen);
	}
	public String getUsePortal(){
		if(m_aszUsePortal == null) return "";
		return m_aszUsePortal;
	}

	/**
	** portal banner URL
	**/
	private String m_aszPortalBannerURL=null;
	public void setPortalBannerURL(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalBannerURL = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalBannerURL = aszTemp;
			return;
		}
		m_aszPortalBannerURL = aszTemp.substring(0,iLen);
	}
	public String getPortalBannerURL(){
		if(m_aszPortalBannerURL == null) return "";
		return m_aszPortalBannerURL;
	}

	/**
	** portal m_aszPortalHeaderTags html
	**/
	private String m_aszPortalHeaderTags=null;
	public void setPortalHeaderTags(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalHeaderTags = null;
			return;
		}
		// strip out any jsp tags from input
		m_aszPortalHeaderTags = aszTemp;
	}
	public String getPortalHeaderTags(){
		if(m_aszPortalHeaderTags == null) return "";
		return m_aszPortalHeaderTags;
	}
	/**
	** portal Header html
	**/
	private String m_aszPortalHeader=null;
	public void setPortalHeader(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalHeader = null;
			return;
		}
		// strip out any jsp tags from input
		m_aszPortalHeader = aszTemp;
	}
	public String getPortalHeader(){
		if(m_aszPortalHeader == null) return "";
		return m_aszPortalHeader;
	}

	/**
	** portal getPortalHeader html
	**/
	private String m_aszPortalFooter=null;
	public void setPortalFooter(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalFooter = null;
			return;
		}
		// strip out any jsp tags from input
		m_aszPortalFooter = aszTemp;
	}
	public String getPortalFooter(){
		if(m_aszPortalFooter == null) return "";
		return m_aszPortalFooter;
	}

	/**
	** portal CSS html
	**/
	private String m_aszPortalCSS=null;
	public void setPortalCSS(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalCSS = null;
			return;
		}
		// strip out any jsp tags from input
		m_aszPortalCSS = aszTemp;
	}
	public String getPortalCSS(){
		if(m_aszPortalCSS == null) return "";
		return m_aszPortalCSS;
	}


	/**
	** portal term name
	**/
	private String m_aszPortalName=null;
	public void setPortalName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalName = aszTemp;
			return;
		}
		m_aszPortalName = aszTemp.substring(0,iLen);
	}
	public String getPortalName(){
		if(m_aszPortalName == null) return "";
		return m_aszPortalName;
	}

	/**
	** portal term name Orig
	**/
	private String m_aszPortalNameOrig=null;
	public void setPortalNameOrig(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalNameOrig = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalNameOrig = aszTemp;
			return;
		}
		m_aszPortalNameOrig = aszTemp.substring(0,iLen);
	}
	public String getPortalNameOrig(){
		if(m_aszPortalNameOrig == null) return "";
		return m_aszPortalNameOrig;
	}

	/**
	** portal term name Modified
	**/
	private String m_aszPortalNameModified=null;
	public void setPortalNameModified(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalNameModified = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalNameModified = aszTemp;
			return;
		}
		m_aszPortalNameModified = aszTemp.substring(0,iLen);
	}
	public String getPortalNameModified(){
		if(m_aszPortalNameModified == null) return "";
		return m_aszPortalNameModified;
	}

	/**
	** portal term tid
	**/
	private int m_iPortalTID=-1;
	public void setPortalTID(int number){
		m_iPortalTID = number;
	}
	public void setPortalTID(String number){
		m_iPortalTID = convertToInt(number);
		return;
	}
	public int getPortalTID(){
		return m_iPortalTID;
	}



	/**
	** affiliation type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGAffiliation=null;
	public void setORGAffiliation(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAffiliation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAffiliation = aszTemp;
			return;
		}
		m_aszORGAffiliation = aszTemp.substring(0,iLen);
	}
	public String getORGAffiliation(){
		if(m_aszORGAffiliation == null) return "";
		return m_aszORGAffiliation;
	}


	/**
	** National Association nid (grants access rights to corresponding Org Affil)
	**/
	private int m_iNatlAssociationNID=-1;
	public void setNatlAssociationNID(int number){
		m_iNatlAssociationNID = number;
	}
	public void setNatlAssociationNID(String number){
		m_iNatlAssociationNID = convertToInt(number);
		return;
	}
	public int getNatlAssociationNID(){
		return m_iNatlAssociationNID;
	}

	/**
	** NATIONAL association (gives access rights on corresponding OrgAffil) in table organizationinfo 
	**/
	private String m_aszNatlAssociation=null;
	public void setNatlAssociation(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszNatlAssociation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszNatlAssociation = aszTemp;
			return;
		}
		m_aszNatlAssociation = aszTemp.substring(0,iLen);
	}
	public String getNatlAssociation(){
		if(m_aszNatlAssociation == null) return "";
		return m_aszNatlAssociation;
	}

	
	/*
	 * multi-select a_iOrgAffiliationsArray
	 */
	private int[] a_iOrgAffiliationsArray=null;
	public void setOrgAffiliationsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOrgAffiliationsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOrgAffiliationsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOrgAffiliationsArray = a_iTemp;
			return;
		}
	}
	public int[] getOrgAffiliationsArray(){
		if(a_iOrgAffiliationsArray == null) {
			a_iOrgAffiliationsArray=new int[0];
			return a_iOrgAffiliationsArray;
		}
		return a_iOrgAffiliationsArray;
	}
	/**
	 ** OrgAffiliations comma seperated string of TIDs
	 */
	private String m_aszOrgAffiliations=null;
	public void setOrgAffiliations(String value){
		int iLen=1024;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOrgAffiliations = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOrgAffiliations = aszTemp;
			return;
		}
		m_aszOrgAffiliations = aszTemp.substring(0,iLen);
	}
	public String getOrgAffiliations(){
		if(m_aszOrgAffiliations == null) return "";
		return m_aszOrgAffiliations;
	}
	
	/*
	 * multi-select a_iDenominationalAffilsArray
	 */
	private int[] a_iDenominationalAffilsArray=null;
	public void setDenominationalAffilsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iDenominationalAffilsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iDenominationalAffilsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iDenominationalAffilsArray = a_iTemp;
			return;
		}
	}
	public int[] getDenominationalAffilsArray(){
		if(a_iDenominationalAffilsArray == null) {
			a_iDenominationalAffilsArray=new int[0];
			return a_iDenominationalAffilsArray;
		}
		return a_iDenominationalAffilsArray;
	}
	/**
	 ** m_aszDenominationalAffils comma seperated string of TIDs
	 */
	private String m_aszDenominationalAffils=null;
	public void setDenominationalAffils(String value){
		int iLen=1024;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDenominationalAffils = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDenominationalAffils = aszTemp;
			return;
		}
		m_aszDenominationalAffils = aszTemp.substring(0,iLen);
	}
	public String getDenominationalAffils(){
		if(m_aszDenominationalAffils == null) return "";
		return m_aszDenominationalAffils;
	}

	
	
	
	/*
	 * multi-select a_iOrgAffiliationsPublicArray
	 */
	private int[] a_iOrgAffiliationsPublicArray=null;
	public void setOrgAffiliationsPublicArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOrgAffiliationsPublicArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOrgAffiliationsPublicArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOrgAffiliationsPublicArray = a_iTemp;
			return;
		}
	}
	public int[] getOrgAffiliationsPublicArray(){
		if(a_iOrgAffiliationsPublicArray == null) {
			a_iOrgAffiliationsPublicArray=new int[0];
			return a_iOrgAffiliationsPublicArray;
		}
		return a_iOrgAffiliationsPublicArray;
	}
	/**
	 ** m_aszOrgAffiliationsPublic comma seperated string of TIDs
	 */
	private String m_aszOrgAffiliationsPublic=null;
	public void setOrgAffiliationsPublic(String value){
		int iLen=1024;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOrgAffiliationsPublic = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOrgAffiliationsPublic = aszTemp;
			return;
		}
		m_aszOrgAffiliationsPublic = aszTemp.substring(0,iLen);
	}
	public String getOrgAffiliationsPublic(){
		if(m_aszOrgAffiliationsPublic == null) return "";
		return m_aszOrgAffiliationsPublic;
	}
	
	/*
	 * multi-select a_iDenominationalAffilsPublicArray
	 */
	private int[] a_iDenomAffilsPublicArray=null;
	public void setDenomAffilsPublicArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iDenomAffilsPublicArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iDenomAffilsPublicArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iDenomAffilsPublicArray = a_iTemp;
			return;
		}
	}
	public int[] getDenomAffilsPublicArray(){
		if(a_iDenomAffilsPublicArray == null) {
			a_iDenomAffilsPublicArray=new int[0];
			return a_iDenomAffilsPublicArray;
		}
		return a_iDenomAffilsPublicArray;
	}
	/**
	 ** m_aszDenomAffilsPublic comma seperated string of TIDs
	 */
	private String m_aszDenomAffilsPublic=null;
	public void setDenomAffilsPublic(String value){
		int iLen=1024;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDenomAffilsPublic = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDenomAffilsPublic = aszTemp;
			return;
		}
		m_aszDenomAffilsPublic = aszTemp.substring(0,iLen);
	}
	public String getDenomAffilsPublic(){
		if(m_aszDenomAffilsPublic == null) return "";
		return m_aszDenomAffilsPublic;
	}

	
	
	
	
	
	
	/**
	** m_aszORGDenomAffil type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGDenomAffil=null;
	public void setORGDenomAffil(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGDenomAffil = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGDenomAffil = aszTemp;
			return;
		}
		m_aszORGDenomAffil = aszTemp.substring(0,iLen);
	}
	public String getORGDenomAffil(){
		if(m_aszORGDenomAffil == null) return "";
		return m_aszORGDenomAffil;
	}

	/**
	** Org Denom Affiliation 1 tid type LONG()
	**/
	private int m_iORGDenomAffiliationTID=-1;
	public void setORGDenomAffiliationTID(int number){
		m_iORGDenomAffiliationTID = number;
	}
	public void setORGDenomAffiliationTID(String number){
		m_iORGDenomAffiliationTID = convertToInt(number);
		return;
	}
	public int getORGDenomAffiliationTID(){
		return m_iORGDenomAffiliationTID;
	}

	/**
	** partner type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGPartner=null;
	public void setORGPartner(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGPartner = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGPartner = aszTemp;
			return;
		}
		m_aszORGPartner = aszTemp.substring(0,iLen);
	}
	public String getORGPartner(){
		if(m_aszORGPartner == null) return "";
		return m_aszORGPartner;
	}



	/**
	** partner2 type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGPartner2=null;
	public void setORGPartner2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGPartner2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGPartner2 = aszTemp;
			return;
		}
		m_aszORGPartner2 = aszTemp.substring(0,iLen);
	}
	public String getORGPartner2(){
		if(m_aszORGPartner2 == null) return "";
		return m_aszORGPartner2;
	}


	/**
	** partner3 type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGPartner3=null;
	public void setORGPartner3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGPartner3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGPartner3 = aszTemp;
			return;
		}
		m_aszORGPartner3 = aszTemp.substring(0,iLen);
	}
	public String getORGPartner3(){
		if(m_aszORGPartner3 == null) return "";
		return m_aszORGPartner3;
	}


	/**
	** partner4 type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGPartner4=null;
	public void setORGPartner4(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGPartner4 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGPartner4 = aszTemp;
			return;
		}
		m_aszORGPartner4 = aszTemp.substring(0,iLen);
	}
	public String getORGPartner4(){
		if(m_aszORGPartner4 == null) return "";
		return m_aszORGPartner4;
	}


	/**
	** partner5 type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGPartner5=null;
	public void setORGPartner5(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGPartner5 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGPartner5 = aszTemp;
			return;
		}
		m_aszORGPartner5 = aszTemp.substring(0,iLen);
	}
	public String getORGPartner5(){
		if(m_aszORGPartner5 == null) return "";
		return m_aszORGPartner5;
	}



	/**
	** Organizational Affiliation 1 tid type LONG()
	**/
	private int m_iORGAffiliation1TID=-1;
	public void setORGAffiliation1TID(int number){
		m_iORGAffiliation1TID = number;
	}
	public void setORGAffiliation1TID(String number){
		m_iORGAffiliation1TID = convertToInt(number);
		return;
	}
	public int getORGAffiliation1TID(){
		return m_iORGAffiliation1TID;
	}


	/**
	** organizational Affiliation 2 tid type LONG()
	**/
	private int m_iORGAffiliation2TID=-1;
	public void setORGAffiliation2TID(int number){
		m_iORGAffiliation2TID = number;
	}
	public void setORGAffiliation2TID(String number){
		m_iORGAffiliation2TID = convertToInt(number);
		return;
	}
	public int getORGAffiliation2TID(){
		return m_iORGAffiliation2TID;
	}


	/**
	** Partner Affiliation 3 tid type LONG()
	**/
	private int m_iORGAffiliation3TID=-1;
	public void setORGAffiliation3TID(int number){
		m_iORGAffiliation3TID = number;
	}
	public void setORGAffiliation3TID(String number){
		m_iORGAffiliation3TID = convertToInt(number);
		return;
	}
	public int getORGAffiliation3TID(){
		return m_iORGAffiliation3TID;
	}


	/**
	** Partner Affiliation 4 tid type LONG()
	**/
	private int m_iORGAffiliation4TID=-1;
	public void setORGAffiliation4TID(int number){
		m_iORGAffiliation4TID = number;
	}
	public void setORGAffiliation4TID(String number){
		m_iORGAffiliation4TID = convertToInt(number);
		return;
	}
	public int getORGAffiliation4TID(){
		return m_iORGAffiliation4TID;
	}


	/**
	** Partner Affiliation 5 tid type LONG()
	**/
	private int m_iORGAffiliation5TID=-1;
	public void setORGAffiliation5TID(int number){
		m_iORGAffiliation5TID = number;
	}
	public void setORGAffiliation5TID(String number){
		m_iORGAffiliation5TID = convertToInt(number);
		return;
	}
	public int getORGAffiliation5TID(){
		return m_iORGAffiliation5TID;
	}


	/**
	** Org Affil 1 tid type LONG()
	**/
	private int m_iORGOrgAffil1TID=-1;
	public void setORGOrgAffil1TID(int number){
		m_iORGOrgAffil1TID = number;
	}
	public void setORGOrgAffil1TID(String number){
		m_iORGOrgAffil1TID = convertToInt(number);
		return;
	}
	public int getORGOrgAffil1TID(){
		return m_iORGOrgAffil1TID;
	}

	/**
	** Org Affil 2 tid type LONG()
	**/
	private int m_iORGOrgAffil2TID=-1;
	public void setORGOrgAffil2TID(int number){
		m_iORGOrgAffil2TID = number;
	}
	public void setORGOrgAffil2TID(String number){
		m_iORGOrgAffil2TID = convertToInt(number);
		return;
	}
	public int getORGOrgAffil2TID(){
		return m_iORGOrgAffil2TID;
	}

	/**
	** Org Affil 1 tid type LONG()
	**/
	private int m_iORGOrgAffil3TID=-1;
	public void setORGOrgAffil3TID(int number){
		m_iORGOrgAffil3TID = number;
	}
	public void setORGOrgAffil3TID(String number){
		m_iORGOrgAffil3TID = convertToInt(number);
		return;
	}
	public int getORGOrgAffil3TID(){
		return m_iORGOrgAffil3TID;
	}

	/**
	** Org Affil 4 tid type LONG()
	**/
	private int m_iORGOrgAffil4TID=-1;
	public void setORGOrgAffil4TID(int number){
		m_iORGOrgAffil4TID = number;
	}
	public void setORGOrgAffil4TID(String number){
		m_iORGOrgAffil4TID = convertToInt(number);
		return;
	}
	public int getORGOrgAffil4TID(){
		return m_iORGOrgAffil4TID;
	}

	/**
	** Org Affil 5 tid type LONG()
	**/
	private int m_iORGOrgAffil5TID=-1;
	public void setORGOrgAffil5TID(int number){
		m_iORGOrgAffil5TID = number;
	}
	public void setORGOrgAffil5TID(String number){
		m_iORGOrgAffil5TID = convertToInt(number);
		return;
	}
	public int getORGOrgAffil5TID(){
		return m_iORGOrgAffil5TID;
	}
	

	/**
	** org_comment type TEXT() in table organizationinfo 
	**/
	private String m_aszORGOrgComment=null;

	public void setORGOrgComment(String value){
		if(null == value){
			m_aszORGOrgComment = null;
			return;
		}
		m_aszORGOrgComment = value.trim();
	}
	public String getORGOrgComment(){
		if(m_aszORGOrgComment == null) return "";
		return m_aszORGOrgComment;
	}


	/**
	** internal_comment type TEXT() in table organizationinfo 
	**/
	private String m_aszORGInternalComment=null;

	public void setORGInternalComment(String value){
		if(null == value){
			m_aszORGInternalComment = null;
			return;
		}
		m_aszORGInternalComment = value.trim();
	}
	public String getORGInternalComment(){
		if(m_aszORGInternalComment == null) return "";
		return m_aszORGInternalComment;
	}


	/**
	** url_alias type TEXT() 
	**/
	private String m_aszORGUrlAlias=null;

	public void setORGUrlAlias(String value){
		if(null == value){
			m_aszORGUrlAlias = null;
			return;
		}
		m_aszORGUrlAlias = value.trim();
	}
	public String getORGUrlAlias(){
		if(m_aszORGUrlAlias == null) return "";
		return m_aszORGUrlAlias;
	}

	/**
	** url_alias original type TEXT() 
	**/
	private String m_aszORGUrlAliasOrig=null;

	public void setORGUrlAliasOrig(String value){
		if(null == value){
			m_aszORGUrlAliasOrig = null;
			return;
		}
		m_aszORGUrlAliasOrig = value.trim();
	}
	public String getORGUrlAliasOrig(){
		if(m_aszORGUrlAliasOrig == null) return "";
		return m_aszORGUrlAliasOrig;
	}
	

	/**
	** mission_statement type TEXT() in table organizationinfo 
	**/
	private String m_aszORGMissionStatement=null;

	public void setORGMissionStatement(String value){
		if(null == value){
			m_aszORGMissionStatement = null;
			return;
		}
		m_aszORGMissionStatement = value.trim();
	}
	public String getORGMissionStatement(){
		if(m_aszORGMissionStatement == null) return "";
		return m_aszORGMissionStatement;
	}


	/**
	** org_description type TEXT() in table organizationinfo 
	**/
	private String m_aszORGOrgDescription=null;

	public void setORGOrgDescription(String value){
		if(null == value){
			m_aszORGOrgDescription = null;
			return;
		}
		m_aszORGOrgDescription = value.trim();
	}
	public String getORGOrgDescription(){
		if(m_aszORGOrgDescription == null) return "";
		return m_aszORGOrgDescription;
	}


	/**
	** teaser type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszORGDescTeaser=null;
	public void setORGDescTeaser(String value){
		int iLen=225;
		String aszTemp = value.replaceAll("\\<[^>]*>","");
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGDescTeaser = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGDescTeaser = aszTemp;
			return;
		}
		m_aszORGDescTeaser = aszTemp.substring(0,iLen);
	}
	public String getORGDescTeaser(){
		if(m_aszORGDescTeaser == null) return "";
		return m_aszORGDescTeaser;
	}

	/**
	** teaser type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszORGMissStmntTeaser=null;
	public void setORGMissStmntTeaser(String value){
		int iLen=225;
		String aszTemp = value.replaceAll("\\<[^>]*>","");
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGMissStmntTeaser = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGMissStmntTeaser = aszTemp;
			return;
		}
		m_aszORGMissStmntTeaser = aszTemp.substring(0,iLen);
	}
	public String getORGMissStmntTeaser(){
		if(m_aszORGMissStmntTeaser == null) return "";
		return m_aszORGMissStmntTeaser;
	}

	/**
	** popularity type LONG() in table organizationinfo 
	**/
	private int m_iORGPopularity=-1;
	public void setORGPopularity(int number){
		m_iORGPopularity = number;
	}
	public void setORGPopularity(String number){
		m_iORGPopularity = convertToInt(number);
		return;
	}
	public int getORGPopularity(){
		return m_iORGPopularity;
	}



	/**
	** ORGNumOppsOrg type LONG() in table organizationinfo 
	**/
	private int m_iORGNumOppsOrg=-1;
	public void setORGNumOppsOrg(int number){
		m_iORGNumOppsOrg = number;
	}
	public void setORGNumOppsOrg(String number){
		m_iORGNumOppsOrg = convertToInt(number);
		return;
	}
	public int getORGNumOppsOrg(){
		return m_iORGNumOppsOrg;
	}

	/**
	** num_vol_org type LONG() in table organizationinfo 
	**/
	private int m_iORGNumVolOrg=-1;
	public void setORGNumVolOrg(int number){
		m_iORGNumVolOrg = number;
	}
	public void setORGNumVolOrg(String number){
		m_iORGNumVolOrg = convertToInt(number);
		return;
	}
	public int getORGNumVolOrg(){
		return m_iORGNumVolOrg;
	}

	/**
	** num_vol_orgIntl type LONG() in table organizationinfo 
	**/
	private int m_iORGNumVolOrgIntl=-1;
	public void setORGNumVolOrgIntl(int number){
		m_iORGNumVolOrgIntl = number;
	}
	public void setORGNumVolOrgIntl(String number){
		m_iORGNumVolOrgIntl = convertToInt(number);
		return;
	}
	public int getORGNumVolOrgIntl(){
		return m_iORGNumVolOrgIntl;
	}


	/**
	** num_served type LONG() in table organizationinfo 
	**/
	private int m_iORGNumServed=0;
	public void setORGNumServed(int number){
		m_iORGNumServed = number;
	}
	public void setORGNumServed(String number){
		m_iORGNumServed = convertToInt(number);
		return;
	}
	public int getORGNumServed(){
		return m_iORGNumServed;
	}


	/**
	** formal_train type - no longer a char(1) with the drupalized code
	**/
	private String m_aszORGFormalTrain=null;
	public void setORGFormalTrain(String value){
		if(null == value){
			m_aszORGFormalTrain = null;
			return;
		}
		m_aszORGFormalTrain = value.trim();
	}
	public String getORGFormalTrain(){
		if(m_aszORGFormalTrain == null) return "";
		return m_aszORGFormalTrain;
	}


	/**
	** org_stmt_faith type TEXT() in table organizationinfo 
	**/
	private String m_aszORGOrgStmtFaith=null;

	public void setORGOrgStmtFaith(String value){
		if(null == value){
			m_aszORGOrgStmtFaith = null;
			return;
		}
		m_aszORGOrgStmtFaith = value.trim();
	}
	public String getORGOrgStmtFaith(){
		if(m_aszORGOrgStmtFaith == null) return "";
		return m_aszORGOrgStmtFaith;
	}

	
	/**
	** membertype type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGMembertype=null;
	public void setORGMembertype(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGMembertype = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGMembertype = aszTemp;
			return;
		}
		m_aszORGMembertype = aszTemp.substring(0,iLen);
	}
	public String getORGMembertype(){
		if(m_aszORGMembertype == null) return "";
		return m_aszORGMembertype;
	}
	
	/**
	** ORG Membertype tid type LONG()
	**/
	private int m_iORGMembertypeTID=-1;
	public void setORGMembertypeTID(int number){
		m_iORGMembertypeTID = number;
	}
	public void setORGMembertypeTID(String number){
		m_iORGMembertypeTID = convertToInt(number);
		return;
	}
	public int getORGMembertypeTID(){
		return m_iORGMembertypeTID;
	}


	/**
	** onethird_p type - no longer a char(1) with the drupalized code
	**/
	private String m_aszORGOnethirdP=null;
	public void setORGOnethirdP(String value){
		if(null == value){
			m_aszORGOnethirdP = null;
			return;
		}
		m_aszORGOnethirdP = value.trim();
	}
	public String getORGOnethirdP(){
		if(m_aszORGOnethirdP == null) return "";
		return m_aszORGOnethirdP;
	}


	/**
	** is_501c3_p type CHAR(1) in table organizationinfo 
	**/
	private String m_aszORGIs501c3P=null;
	public void setORGIs501c3P(String value){
		int iLen=1;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGIs501c3P = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGIs501c3P = aszTemp;
			return;
		}
		m_aszORGIs501c3P = aszTemp.substring(0,iLen);
	}
	public String getORGIs501c3P(){
		if(m_aszORGIs501c3P == null) return "";
		return m_aszORGIs501c3P;
	}


	/**
	** program_types type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGProgramTypes=null;
	public void setORGProgramTypes(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGProgramTypes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGProgramTypes = aszTemp;
			return;
		}
		m_aszORGProgramTypes = aszTemp.substring(0,iLen);
	}
	public String getORGProgramTypes(){
		if(m_aszORGProgramTypes == null) return "";
		return m_aszORGProgramTypes;
	}


	/**
	** ORGProgramTypes 1 tid type LONG()
	**/
	private int m_iORGProgramTypes1TID=-1;
	public void setORGProgramTypes1TID(int number){
		m_iORGProgramTypes1TID = number;
	}
	public void setORGProgramTypes1TID(String number){
		m_iORGProgramTypes1TID = convertToInt(number);
		return;
	}
	public int getORGProgramTypes1TID(){
		return m_iORGProgramTypes1TID;
	}


	/**
	** volunteer_opportunity_p type CHAR(1) in table organizationinfo 
	**/
	private String m_aszORGVolunteerOpportunityP=null;
	public void setORGVolunteerOpportunityP(String value){
		int iLen=1;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGVolunteerOpportunityP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGVolunteerOpportunityP = aszTemp;
			return;
		}
		m_aszORGVolunteerOpportunityP = aszTemp.substring(0,iLen);
	}
	public String getORGVolunteerOpportunityP(){
		if(m_aszORGVolunteerOpportunityP == null) return "";
		return m_aszORGVolunteerOpportunityP;
	}


	/**
	** org_contact_email type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGOrgContactEmail=null;
	public void setORGOrgContactEmail(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOrgContactEmail = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOrgContactEmail = aszTemp;
			return;
		}
		m_aszORGOrgContactEmail = aszTemp.substring(0,iLen);
	}
	public String getORGOrgContactEmail(){
		if(m_aszORGOrgContactEmail == null) return "";
		return m_aszORGOrgContactEmail;
	}


	/**
	** organization_budget type LONG() in table organizationinfo 
	**/
	private int m_iORGOrganizationBudget=0;
	public void setORGOrganizationBudget(int number){
		m_iORGOrganizationBudget = number;
	}
	public void setORGOrganizationBudget(String number){
		m_iORGOrganizationBudget = convertToInt(number);
		return;
	}
	public int getORGOrganizationBudget(){
		return m_iORGOrganizationBudget;
	}


	/**
	** num_staff_org type INT() in table organizationinfo 
	**/
	private int m_iORGNumStaffOrg=0;
	public void setORGNumStaffOrg(int number){
		m_iORGNumStaffOrg = number;
	}
	public void setORGNumStaffOrg(String number){
		m_iORGNumStaffOrg = convertToInt(number);
		return;
	}
	public int getORGNumStaffOrg(){
		return m_iORGNumStaffOrg;
	}


	/**
	** language_arabic type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageArabic=0;
	public void setORGLanguageArabic(int number){
		m_iORGLanguageArabic = number;
	}
	public void setORGLanguageArabic(String number){
		m_iORGLanguageArabic = convertToInt(number);
		return;
	}
	public int getORGLanguageArabic(){
		return m_iORGLanguageArabic;
	}


	/**
	** language_chinese type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageChinese=0;
	public void setORGLanguageChinese(int number){
		m_iORGLanguageChinese = number;
	}
	public void setORGLanguageChinese(String number){
		m_iORGLanguageChinese = convertToInt(number);
		return;
	}
	public int getORGLanguageChinese(){
		return m_iORGLanguageChinese;
	}


	/**
	** language_english type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageEnglish=0;
	public void setORGLanguageEnglish(int number){
		m_iORGLanguageEnglish = number;
	}
	public void setORGLanguageEnglish(String number){
		m_iORGLanguageEnglish = convertToInt(number);
		return;
	}
	public int getORGLanguageEnglish(){
		return m_iORGLanguageEnglish;
	}


	/**
	** language_french type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageFrench=0;
	public void setORGLanguageFrench(int number){
		m_iORGLanguageFrench = number;
	}
	public void setORGLanguageFrench(String number){
		m_iORGLanguageFrench = convertToInt(number);
		return;
	}
	public int getORGLanguageFrench(){
		return m_iORGLanguageFrench;
	}


	/**
	** language_hindi type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageHindi=0;
	public void setORGLanguageHindi(int number){
		m_iORGLanguageHindi = number;
	}
	public void setORGLanguageHindi(String number){
		m_iORGLanguageHindi = convertToInt(number);
		return;
	}
	public int getORGLanguageHindi(){
		return m_iORGLanguageHindi;
	}


	/**
	** language_portuguese type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguagePortuguese=0;
	public void setORGLanguagePortuguese(int number){
		m_iORGLanguagePortuguese = number;
	}
	public void setORGLanguagePortuguese(String number){
		m_iORGLanguagePortuguese = convertToInt(number);
		return;
	}
	public int getORGLanguagePortuguese(){
		return m_iORGLanguagePortuguese;
	}


	/**
	** language_spanish type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageSpanish=0;
	public void setORGLanguageSpanish(int number){
		m_iORGLanguageSpanish = number;
	}
	public void setORGLanguageSpanish(String number){
		m_iORGLanguageSpanish = convertToInt(number);
		return;
	}
	public int getORGLanguageSpanish(){
		return m_iORGLanguageSpanish;
	}


	/**
	** language_other_a_text type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGLanguageOtherAText=null;
	public void setORGLanguageOtherAText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGLanguageOtherAText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGLanguageOtherAText = aszTemp;
			return;
		}
		m_aszORGLanguageOtherAText = aszTemp.substring(0,iLen);
	}
	public String getORGLanguageOtherAText(){
		if(m_aszORGLanguageOtherAText == null) return "";
		return m_aszORGLanguageOtherAText;
	}


	/**
	** language_other_a type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageOtherA=0;
	public void setORGLanguageOtherA(int number){
		m_iORGLanguageOtherA = number;
	}
	public void setORGLanguageOtherA(String number){
		m_iORGLanguageOtherA = convertToInt(number);
		return;
	}
	public int getORGLanguageOtherA(){
		return m_iORGLanguageOtherA;
	}


	/**
	** language_other_b_text type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGLanguageOtherBText=null;
	public void setORGLanguageOtherBText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGLanguageOtherBText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGLanguageOtherBText = aszTemp;
			return;
		}
		m_aszORGLanguageOtherBText = aszTemp.substring(0,iLen);
	}
	public String getORGLanguageOtherBText(){
		if(m_aszORGLanguageOtherBText == null) return "";
		return m_aszORGLanguageOtherBText;
	}


	/**
	** language_other_b type TINY() in table organizationinfo 
	**/
	private int m_iORGLanguageOtherB=0;
	public void setORGLanguageOtherB(int number){
		m_iORGLanguageOtherB = number;
	}
	public void setORGLanguageOtherB(String number){
		m_iORGLanguageOtherB = convertToInt(number);
		return;
	}
	public int getORGLanguageOtherB(){
		return m_iORGLanguageOtherB;
	}


	/**
	** demo_low_income type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoLowIncome=0;
	public void setORGDemoLowIncome(int number){
		m_iORGDemoLowIncome = number;
	}
	public void setORGDemoLowIncome(String number){
		m_iORGDemoLowIncome = convertToInt(number);
		return;
	}
	public int getORGDemoLowIncome(){
		return m_iORGDemoLowIncome;
	}


	/**
	** demo_homeless type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoHomeless=0;
	public void setORGDemoHomeless(int number){
		m_iORGDemoHomeless = number;
	}
	public void setORGDemoHomeless(String number){
		m_iORGDemoHomeless = convertToInt(number);
		return;
	}
	public int getORGDemoHomeless(){
		return m_iORGDemoHomeless;
	}


	/**
	** demo_disability type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoDisability=0;
	public void setORGDemoDisability(int number){
		m_iORGDemoDisability = number;
	}
	public void setORGDemoDisability(String number){
		m_iORGDemoDisability = convertToInt(number);
		return;
	}
	public int getORGDemoDisability(){
		return m_iORGDemoDisability;
	}


	/**
	** demo_urban type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoUrban=0;
	public void setORGDemoUrban(int number){
		m_iORGDemoUrban = number;
	}
	public void setORGDemoUrban(String number){
		m_iORGDemoUrban = convertToInt(number);
		return;
	}
	public int getORGDemoUrban(){
		return m_iORGDemoUrban;
	}


	/**
	** demo_rural type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoRural=0;
	public void setORGDemoRural(int number){
		m_iORGDemoRural = number;
	}
	public void setORGDemoRural(String number){
		m_iORGDemoRural = convertToInt(number);
		return;
	}
	public int getORGDemoRural(){
		return m_iORGDemoRural;
	}


	/**
	** demo_youth type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoYouth=0;
	public void setORGDemoYouth(int number){
		m_iORGDemoYouth = number;
	}
	public void setORGDemoYouth(String number){
		m_iORGDemoYouth = convertToInt(number);
		return;
	}
	public int getORGDemoYouth(){
		return m_iORGDemoYouth;
	}


	/**
	** demo_adults type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoAdults=0;
	public void setORGDemoAdults(int number){
		m_iORGDemoAdults = number;
	}
	public void setORGDemoAdults(String number){
		m_iORGDemoAdults = convertToInt(number);
		return;
	}
	public int getORGDemoAdults(){
		return m_iORGDemoAdults;
	}


	/**
	** demo_seniors type TINY() in table organizationinfo 
	**/
	private int m_iORGDemoSeniors=0;
	public void setORGDemoSeniors(int number){
		m_iORGDemoSeniors = number;
	}
	public void setORGDemoSeniors(String number){
		m_iORGDemoSeniors = convertToInt(number);
		return;
	}
	public int getORGDemoSeniors(){
		return m_iORGDemoSeniors;
	}


	/**
	** gender_male type TINY() in table organizationinfo 
	**/
	private int m_iORGGenderMale=0;
	public void setORGGenderMale(int number){
		m_iORGGenderMale = number;
	}
	public void setORGGenderMale(String number){
		m_iORGGenderMale = convertToInt(number);
		return;
	}
	public int getORGGenderMale(){
		return m_iORGGenderMale;
	}


	/**
	** gender_female type TINY() in table organizationinfo 
	**/
	private int m_iORGGenderFemale=0;
	public void setORGGenderFemale(int number){
		m_iORGGenderFemale = number;
	}
	public void setORGGenderFemale(String number){
		m_iORGGenderFemale = convertToInt(number);
		return;
	}
	public int getORGGenderFemale(){
		return m_iORGGenderFemale;
	}


	/**
	** tech_participants type INT() in table organizationinfo 
	**/
	private int m_iORGTechParticipants=0;
	public void setORGTechParticipants(int number){
		m_iORGTechParticipants = number;
	}
	public void setORGTechParticipants(String number){
		m_iORGTechParticipants = convertToInt(number);
		return;
	}
	public int getORGTechParticipants(){
		return m_iORGTechParticipants;
	}


	/**
	** tech_computers type INT() in table organizationinfo 
	**/
	private int m_iORGTechComputers=0;
	public void setORGTechComputers(int number){
		m_iORGTechComputers = number;
	}
	public void setORGTechComputers(String number){
		m_iORGTechComputers = convertToInt(number);
		return;
	}
	public int getORGTechComputers(){
		return m_iORGTechComputers;
	}


	/**
	** tech_donate type LONG() in table organizationinfo 
	**/
	private int m_iORGTechDonate=0;
	public void setORGTechDonate(int number){
		m_iORGTechDonate = number;
	}
	public void setORGTechDonate(String number){
		m_iORGTechDonate = convertToInt(number);
		return;
	}
	public int getORGTechDonate(){
		return m_iORGTechDonate;
	}


	/**
	** outcome_completion type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeCompletion=0;
	public void setORGOutcomeCompletion(int number){
		m_iORGOutcomeCompletion = number;
	}
	public void setORGOutcomeCompletion(String number){
		m_iORGOutcomeCompletion = convertToInt(number);
		return;
	}
	public int getORGOutcomeCompletion(){
		return m_iORGOutcomeCompletion;
	}


	/**
	** outcome_certification type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeCertification=0;
	public void setORGOutcomeCertification(int number){
		m_iORGOutcomeCertification = number;
	}
	public void setORGOutcomeCertification(String number){
		m_iORGOutcomeCertification = convertToInt(number);
		return;
	}
	public int getORGOutcomeCertification(){
		return m_iORGOutcomeCertification;
	}


	/**
	** outcome_college type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeCollege=0;
	public void setORGOutcomeCollege(int number){
		m_iORGOutcomeCollege = number;
	}
	public void setORGOutcomeCollege(String number){
		m_iORGOutcomeCollege = convertToInt(number);
		return;
	}
	public int getORGOutcomeCollege(){
		return m_iORGOutcomeCollege;
	}


	/**
	** outcome_job type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeJob=0;
	public void setORGOutcomeJob(int number){
		m_iORGOutcomeJob = number;
	}
	public void setORGOutcomeJob(String number){
		m_iORGOutcomeJob = convertToInt(number);
		return;
	}
	public int getORGOutcomeJob(){
		return m_iORGOutcomeJob;
	}


	/**
	** outcome_ged type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeGed=0;
	public void setORGOutcomeGed(int number){
		m_iORGOutcomeGed = number;
	}
	public void setORGOutcomeGed(String number){
		m_iORGOutcomeGed = convertToInt(number);
		return;
	}
	public int getORGOutcomeGed(){
		return m_iORGOutcomeGed;
	}


	/**
	** outcome_discipleship type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeDiscipleship=0;
	public void setORGOutcomeDiscipleship(int number){
		m_iORGOutcomeDiscipleship = number;
	}
	public void setORGOutcomeDiscipleship(String number){
		m_iORGOutcomeDiscipleship = convertToInt(number);
		return;
	}
	public int getORGOutcomeDiscipleship(){
		return m_iORGOutcomeDiscipleship;
	}


	/**
	** outcome_believer type INT() in table organizationinfo 
	**/
	private int m_iORGOutcomeBeliever=0;
	public void setORGOutcomeBeliever(int number){
		m_iORGOutcomeBeliever = number;
	}
	public void setORGOutcomeBeliever(String number){
		m_iORGOutcomeBeliever = convertToInt(number);
		return;
	}
	public int getORGOutcomeBeliever(){
		return m_iORGOutcomeBeliever;
	}


	/**
	** race_aborna type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceAborna=0;
	public void setORGRaceAborna(int number){
		m_iORGRaceAborna = number;
	}
	public void setORGRaceAborna(String number){
		m_iORGRaceAborna = convertToInt(number);
		return;
	}
	public int getORGRaceAborna(){
		return m_iORGRaceAborna;
	}


	/**
	** race_arab type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceArab=0;
	public void setORGRaceArab(int number){
		m_iORGRaceArab = number;
	}
	public void setORGRaceArab(String number){
		m_iORGRaceArab = convertToInt(number);
		return;
	}
	public int getORGRaceArab(){
		return m_iORGRaceArab;
	}


	/**
	** race_asian type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceAsian=0;
	public void setORGRaceAsian(int number){
		m_iORGRaceAsian = number;
	}
	public void setORGRaceAsian(String number){
		m_iORGRaceAsian = convertToInt(number);
		return;
	}
	public int getORGRaceAsian(){
		return m_iORGRaceAsian;
	}


	/**
	** race_black type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceBlack=0;
	public void setORGRaceBlack(int number){
		m_iORGRaceBlack = number;
	}
	public void setORGRaceBlack(String number){
		m_iORGRaceBlack = convertToInt(number);
		return;
	}
	public int getORGRaceBlack(){
		return m_iORGRaceBlack;
	}


	/**
	** race_caucasian type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceCaucasian=0;
	public void setORGRaceCaucasian(int number){
		m_iORGRaceCaucasian = number;
	}
	public void setORGRaceCaucasian(String number){
		m_iORGRaceCaucasian = convertToInt(number);
		return;
	}
	public int getORGRaceCaucasian(){
		return m_iORGRaceCaucasian;
	}


	/**
	** race_latino type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceLatino=0;
	public void setORGRaceLatino(int number){
		m_iORGRaceLatino = number;
	}
	public void setORGRaceLatino(String number){
		m_iORGRaceLatino = convertToInt(number);
		return;
	}
	public int getORGRaceLatino(){
		return m_iORGRaceLatino;
	}


	/**
	** race_other_text type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGRaceOtherText=null;
	public void setORGRaceOtherText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGRaceOtherText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGRaceOtherText = aszTemp;
			return;
		}
		m_aszORGRaceOtherText = aszTemp.substring(0,iLen);
	}
	public String getORGRaceOtherText(){
		if(m_aszORGRaceOtherText == null) return "";
		return m_aszORGRaceOtherText;
	}


	/**
	** race_other type TINY() in table organizationinfo 
	**/
	private int m_iORGRaceOther=0;
	public void setORGRaceOther(int number){
		m_iORGRaceOther = number;
	}
	public void setORGRaceOther(String number){
		m_iORGRaceOther = convertToInt(number);
		return;
	}
	public int getORGRaceOther(){
		return m_iORGRaceOther;
	}
	
	
	/**
	** race_pacific_islander type TINY() in table organizationinfo 
	**/
	private int m_iORGRacePacificIslander=0;
	public void setORGRacePacificIslander(int number){
		m_iORGRacePacificIslander = number;
	}
	public void setORGRacePacificIslander(String number){
		m_iORGRacePacificIslander = convertToInt(number);
		return;
	}
	public int getORGRacePacificIslander(){
		return m_iORGRacePacificIslander;
	}


	/**
	** addr_other_province type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGAddrOtherProvince=null;
	public void setORGAddrOtherProvince(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGAddrOtherProvince = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGAddrOtherProvince = aszTemp;
			return;
		}
		m_aszORGAddrOtherProvince = aszTemp.substring(0,iLen);
	}
	public String getORGAddrOtherProvince(){
		if(m_aszORGAddrOtherProvince == null) return "";
		return m_aszORGAddrOtherProvince;
	}



	/**
	** local_affil type VARCHAR(255) in table organizationinfo 
	**/
	private String m_aszORGLocalAffil=null;
	public void setORGLocalAffil(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGLocalAffil = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGLocalAffil = aszTemp;
			return;
		}
		m_aszORGLocalAffil = aszTemp.substring(0,iLen);
	}
	public String getORGLocalAffil(){
		if(m_aszORGLocalAffil == null) return "";
		return m_aszORGLocalAffil;
	}



	/**
	** subdom type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGSubdom=null;
	public void setORGSubdom(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGSubdom = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGSubdom = aszTemp;
			return;
		}
		m_aszORGSubdom = aszTemp.substring(0,iLen);
	}
	public String getORGSubdom(){
		if(m_aszORGSubdom == null) return "";
		return m_aszORGSubdom;
	}
	

	/**
	** org TakesIntnlVol type - Does your organization take volunteers from outside its country and assist in traveling to your country? 
	*	two values. "Assists with getting travel visa" or "Must Be Resident of Country or Already Have Appropriate Visa"
	**/
	private int m_iORGTakesIntlVolsTID=-1;
	public void setORGTakesIntlVolsTID(int number){
		m_iORGTakesIntlVolsTID = number;
	}
	public void setORGTakesIntlVolsTID(String number){
		m_iORGTakesIntlVolsTID = convertToInt(number);
		return;
	}
	public int getORGTakesIntlVolsTID(){
		return m_iORGTakesIntlVolsTID;
	}
	/**
	** org TakesIntnlVol type VARCHAR(100)  
	**/
	private String m_aszORGTakesIntlVols=null;
	public void setORGTakesIntlVols(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGTakesIntlVols = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGTakesIntlVols = aszTemp;
			return;
		}
		m_aszORGTakesIntlVols = aszTemp.substring(0,iLen);
	}
	public String getORGTakesIntlVols(){
		if(m_aszORGTakesIntlVols == null) return "";
		return m_aszORGTakesIntlVols;
	}
	
	private String m_aszCity=null;
	public void setCityName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCity = aszTemp;
			return;
		}
		m_aszCity = aszTemp.substring(0,iLen);
	}
	public String getCityName(){
		if(m_aszCity == null) return "";
		return m_aszCity;
	}
	
	
	/**
	** City taxonomy
	**/
	private int m_iCityTID=-1;
	public void setCityTID(int number){
		m_iCityTID = number;
	}
	public void setCityTID(String number){
		m_iCityTID = convertToInt(number);
		return;
	}
	public int getCityTID(){
		return m_iCityTID;
	}
	
	
	private String m_aszState=null;
	public void setStateName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszState = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszState = aszTemp;
			return;
		}
		m_aszState = aszTemp.substring(0,iLen);
	}
	public String getStateName(){
		if(m_aszState == null) return "";
		return m_aszState;
	}

	
	/**
	** State taxonomy
	**/
	private int m_iStateTID=-1;
	public void setStateTID(int number){
		m_iStateTID = number;
	}
	public void setStateTID(String number){
		m_iStateTID = convertToInt(number);
		return;
	}
	public int getStateTID(){
		return m_iStateTID;
	}
	
	
	private String m_aszCountry=null;
	public void setCountryName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCountry = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCountry = aszTemp;
			return;
		}
		m_aszCountry = aszTemp.substring(0,iLen);
	}
	public String getCountryName(){
		if(m_aszCountry == null) return "";
		return m_aszCountry;
	}


	/**
	** Country taxonomy
	**/
	private int m_iCountryTID=-1;
	public void setCountryTID(int number){
		m_iCountryTID = number;
	}
	public void setCountryTID(String number){
		m_iCountryTID = convertToInt(number);
		return;
	}
	public int getCountryTID(){
		return m_iCountryTID;
	}
	
	
	private String m_aszSubRegion=null;
	public void setSubRegionName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSubRegion = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSubRegion = aszTemp;
			return;
		}
		m_aszSubRegion = aszTemp.substring(0,iLen);
	}
	public String getSubRegionName(){
		if(m_aszSubRegion == null) return "";
		return m_aszSubRegion;
	}


	/**
	** SubRegion taxonomy
	**/
	private int m_iSubRegionTID=-1;
	public void setSubRegionTID(int number){
		m_iSubRegionTID = number;
	}
	public void setSubRegionTID(String number){
		m_iSubRegionTID = convertToInt(number);
		return;
	}
	public int getSubRegionTID(){
		return m_iSubRegionTID;
	}
	
	
	private String m_aszRegion=null;
	public void setRegionName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszRegion = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszRegion = aszTemp;
			return;
		}
		m_aszRegion = aszTemp.substring(0,iLen);
	}
	public String getRegionName(){
		if(m_aszRegion == null) return "";
		return m_aszRegion;
	}


	/**
	** Region taxonomy
	**/
	private int m_iRegionTID=-1;
	public void setRegionTID(int number){
		m_iRegionTID = number;
	}
	public void setRegionTID(String number){
		m_iRegionTID = convertToInt(number);
		return;
	}
	public int getRegionTID(){
		return m_iRegionTID;
	}


	
	//===========> End Code Generated Methods For Table organizationinfo 
	//===========> End Code Generated Methods For Table organizationinfo 
	//===========> End Code Generated Methods For Table organizationinfo 

	
	//===========> BEGIN - store data related to child opportunities
	//===========> BEGIN - store data related to child opportunities
	//===========> BEGIN - store data related to child opportunities

	/*
	 * Primary Types of Volunteer Opportunities: local, national, foreign, virtual (multiselect); taxonomy (vid=349) 
	 */
	private int[] a_iTypesOfOppsArray=null;
	public void setTypesOfOppsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iTypesOfOppsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iTypesOfOppsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iTypesOfOppsArray = a_iTemp;
			return;
		}
	}
	public int[] getTypesOfOppsArray(){
		if(a_iTypesOfOppsArray == null) {
			a_iTypesOfOppsArray=new int[0];
			return a_iTypesOfOppsArray;
		}
		return a_iTypesOfOppsArray;
	}
	/*
	 * array of position types 
	 */
	private int[] a_iPositionTypesArray=null;
	public void setPositionTypesArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iPositionTypesArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iPositionTypesArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iPositionTypesArray = a_iTemp;
			return;
		}
	}
	public int[] getPositionTypesArray(){
		if(a_iPositionTypesArray == null) {
			a_iPositionTypesArray=new int[0];
			return a_iPositionTypesArray;
		}
		return a_iPositionTypesArray;
	}
	/*
	 * array of service areas 
	 */
	private int[] a_iServiceAreasArray=null;
	public void setServiceAreasArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iServiceAreasArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iServiceAreasArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iServiceAreasArray = a_iTemp;
			return;
		}
	}
	public int[] getServiceAreasArray(){
		if(a_iServiceAreasArray == null) {
			a_iServiceAreasArray=new int[0];
			return a_iServiceAreasArray;
		}
		return a_iServiceAreasArray;
	}
	/*
	 * array of skills 
	 */
	private int[] a_iSkillsArray=null;
	public void setSkillsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iSkillsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iSkillsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iSkillsArray = a_iTemp;
			return;
		}
	}
	public int[] getSkillsArray(){
		if(a_iSkillsArray == null) {
			a_iSkillsArray=new int[0];
			return a_iSkillsArray;
		}
		return a_iSkillsArray;
	}
	/*
	 * array of great for 
	 */
	private int[] a_iGreatForArray=null;
	public void setGreatForArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iGreatForArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iGreatForArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iGreatForArray = a_iTemp;
			return;
		}
	}
	public int[] getGreatForArray(){
		if(a_iGreatForArray == null) {
			a_iGreatForArray=new int[0];
			return a_iGreatForArray;
		}
		return a_iGreatForArray;
	}
	/*
	 * array of length of trip 
	 */
	private int[] a_iTripLengthArray=null;
	public void setTripLengthArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iTripLengthArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iTripLengthArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iTripLengthArray = a_iTemp;
			return;
		}
	}
	public int[] getTripLengthArray(){
		if(a_iTripLengthArray == null) {
			a_iTripLengthArray=new int[0];
			return a_iTripLengthArray;
		}
		return a_iTripLengthArray;
	}

	/**
	** OPPPositionType(s)
	**/
	private String m_aszOPPPositionType=null;
	public void setOPPPositionType(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPPositionType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPPositionType = aszTemp;
			return;
		}
		m_aszOPPPositionType = aszTemp.substring(0,iLen);
	}
	public String getOPPPositionType(){
		if(m_aszOPPPositionType == null) return "";
		return m_aszOPPPositionType;
	}

	/*
	 * multi-select OPPPositionTypes
	 */
	private int[] a_iOPPPositionTypesArray=null;
	public void setOPPPositionTypesArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOPPPositionTypesArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPPositionTypesArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPPositionTypesArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPPositionTypesArray(){
		if(a_iOPPPositionTypesArray == null) {
			a_iOPPPositionTypesArray=new int[0];
			return a_iOPPPositionTypesArray;
		}
		return a_iOPPPositionTypesArray;
	}

	
	
	
	/**
	** categories type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCategories=null;
	public void setOPPCategories(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCategories = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCategories = aszTemp;
			return;
		}
		m_aszOPPCategories = aszTemp.substring(0,iLen);
	}
	public String getOPPCategories(){
		if(m_aszOPPCategories == null) return "";
		return m_aszOPPCategories;
	}


	/**
	** categories type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCategories2=null;
	public void setOPPCategories2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCategories2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCategories2 = aszTemp;
			return;
		}
		m_aszOPPCategories2 = aszTemp.substring(0,iLen);
	}
	public String getOPPCategories2(){
		if(m_aszOPPCategories2 == null) return "";
		return m_aszOPPCategories2;
	}


	/**
	** categories3 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCategories3=null;
	public void setOPPCategories3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCategories3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCategories3 = aszTemp;
			return;
		}
		m_aszOPPCategories3 = aszTemp.substring(0,iLen);
	}
	public String getOPPCategories3(){
		if(m_aszOPPCategories3 == null) return "";
		return m_aszOPPCategories3;
	}

	/**
	** ServiceArea tid 1 type LONG()
	**/
	private int m_iOPPServiceArea1TID=-1;
	public void setOPPServiceArea1TID(int number){
		m_iOPPServiceArea1TID = number;
	}
	public void setOPPServiceArea1TID(String number){
		m_iOPPServiceArea1TID = convertToInt(number);
		return;
	}
	public int getOPPServiceArea1TID(){
		return m_iOPPServiceArea1TID;
	}

	/**
	** Service Area tid 2 type LONG()
	**/
	private int m_iOPPServiceArea2TID=-1;
	public void setOPPServiceArea2TID(int number){
		m_iOPPServiceArea2TID = number;
	}
	public void setOPPServiceArea2TID(String number){
		m_iOPPServiceArea2TID = convertToInt(number);
		return;
	}
	public int getOPPServiceArea2TID(){
		return m_iOPPServiceArea2TID;
	}

	/**
	** Service Area tid 3 type LONG()
	**/
	private int m_iOPPServiceArea3TID=-1;
	public void setOPPServiceArea3TID(int number){
		m_iOPPServiceArea3TID = number;
	}
	public void setOPPServiceArea3TID(String number){
		m_iOPPServiceArea3TID = convertToInt(number);
		return;
	}
	public int getOPPServiceArea3TID(){
		return m_iOPPServiceArea3TID;
	}


	/**
	 ** serviceareas comma seperated string of TIDs
	 */
	private String m_aszServiceAreas=null;
	public void setServiceAreas(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszServiceAreas = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszServiceAreas = aszTemp;
			return;
		}
		m_aszServiceAreas = aszTemp.substring(0,iLen);
	}
	public String getServiceAreas(){
		if(m_aszServiceAreas == null) return "";
		return m_aszServiceAreas;
	}
	
	
	
	

	/**
	** skills type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPSkills=null;
	public void setOPPSkills(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSkills = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSkills = aszTemp;
			return;
		}
		m_aszOPPSkills = aszTemp.substring(0,iLen);
	}
	public String getOPPSkills(){
		if(m_aszOPPSkills == null) return "";
		return m_aszOPPSkills;
	}

	/**
	** skills2 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPSkills2=null;
	public void setOPPSkills2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSkills2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSkills2 = aszTemp;
			return;
		}
		m_aszOPPSkills2 = aszTemp.substring(0,iLen);
	}
	public String getOPPSkills2(){
		if(m_aszOPPSkills2 == null) return "";
		return m_aszOPPSkills2;
	}


	/**
	** skills3 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPSkills3=null;
	public void setOPPSkills3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSkills3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSkills3 = aszTemp;
			return;
		}
		m_aszOPPSkills3 = aszTemp.substring(0,iLen);
	}
	public String getOPPSkills3(){
		if(m_aszOPPSkills3 == null) return "";
		return m_aszOPPSkills3;
	}


	/**
	** Skills 1 tid type LONG()
	**/
	private int m_iOPPSkills1TID=-1;
	public void setOPPSkills1TID(int number){
		m_iOPPSkills1TID = number;
	}
	public void setOPPSkills1TID(String number){
		m_iOPPSkills1TID = convertToInt(number);
		return;
	}
	public int getOPPSkills1TID(){
		return m_iOPPSkills1TID;
	}

	/**
	** Skills 2 tid type LONG()
	**/
	private int m_iOPPSkills2TID=-1;
	public void setOPPSkills2TID(int number){
		m_iOPPSkills2TID = number;
	}
	public void setOPPSkills2TID(String number){
		m_iOPPSkills2TID = convertToInt(number);
		return;
	}
	public int getOPPSkills2TID(){
		return m_iOPPSkills2TID;
	}


	/**
	** Skills 3 tid type LONG()
	**/
	private int m_iOPPSkills3TID=-1;
	public void setOPPSkills3TID(int number){
		m_iOPPSkills3TID = number;
	}
	public void setOPPSkills3TID(String number){
		m_iOPPSkills3TID = convertToInt(number);
		return;
	}
	public int getOPPSkills3TID(){
		return m_iOPPSkills3TID;
	}

	
	/**
	 ** skilltypes comma seperated string of TIDs
	 */
	private String m_aszSkillTypes=null;
	public void setSkillTypes(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSkillTypes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSkillTypes = aszTemp;
			return;
		}
		m_aszSkillTypes = aszTemp.substring(0,iLen);
	}
	public String getSkillTypes(){
		if(m_aszSkillTypes == null) return "";
		return m_aszSkillTypes;
	}
	

	//===========> END - store data related to child opportunities
	//===========> END - store data related to child opportunities
	//===========> END - store data related to child opportunities

	/**
	** usermail - does the given user Volunteer Contact receive Vol Contact emails for this Organization? 0=no/1=yes
	**/
	private int m_iIsVolunteerContact=0;
	public void setIsVolunteerContact(int number){
		m_iIsVolunteerContact = number;
	}
	public void setIsVolunteerContact(String number){
		m_iIsVolunteerContact = convertToInt(number);
		return;
	}
	public int getIsVolunteerContact(){
		return m_iIsVolunteerContact;
	}

	
	


	/**
	** nubmer of m_iCVInternInterest in this org
	**/
	private int m_iCVInternInterest=0;
	public void setCVInternInterest(int number){
		m_iCVInternInterest = number;
	}
	public void setCVInternInterest(String number){
		m_iCVInternInterest = convertToInt(number);
		return;
	}
	public int getCVInternInterest(){
		return m_iCVInternInterest;
	}

	
	
	
	//===========> Start Code for PathAuto Ignore Words, Punctuation, etc
	//===========> Start Code for PathAuto Ignore Words, Punctuation, etc
	//===========> Start Code for PathAuto Ignore Words, Punctuation, etc
	/**
	** PathAuto Ignore Words; type VARCHAR(500) 
	**/
	private String m_aszPathAutoIgnoreWords=null;
	public void setPathAutoIgnoreWords(String value){
		int iLen=500;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoIgnoreWords = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoIgnoreWords = aszTemp;
			return;
		}
		m_aszPathAutoIgnoreWords = aszTemp.substring(0,iLen);
	}
	public String getPathAutoIgnoreWords(){
		if(m_aszPathAutoIgnoreWords == null) return "";
		return m_aszPathAutoIgnoreWords;
	}

	/**
	** PathAuto Separator; type VARCHAR(10) 
	**/
	private String m_aszPathAutoSeparator=null;
	public void setPathAutoSeparator(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoSeparator = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoSeparator = aszTemp;
			return;
		}
		m_aszPathAutoSeparator = aszTemp.substring(0,iLen);
	}
	public String getPathAutoSeparator(){
		if(m_aszPathAutoSeparator == null) return "";
		return m_aszPathAutoSeparator;
	}

	/**
	** PathAuto PathAutoOrgPattern; type VARCHAR(50) 
	**/
	private String m_aszPathAutoOrgPattern=null;
	public void setPathAutoOrgPattern(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoOrgPattern = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoOrgPattern = aszTemp;
			return;
		}
		m_aszPathAutoOrgPattern = aszTemp.substring(0,iLen);
	}
	public String getPathAutoOrgPattern(){
		if(m_aszPathAutoOrgPattern == null) return "";
		return m_aszPathAutoOrgPattern;
	}

	/**
	** PathAuto PathAutoOppPattern; type VARCHAR(50) 
	**/
	private String m_aszPathAutoOppPattern=null;
	public void setPathAutoOppPattern(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoOppPattern = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoOppPattern = aszTemp;
			return;
		}
		m_aszPathAutoOppPattern = aszTemp.substring(0,iLen);
	}
	public String getPathAutoOppPattern(){
		if(m_aszPathAutoOppPattern == null) return "";
		return m_aszPathAutoOppPattern;
	}

	/**
	** PathAuto Punctuation; type VARCHAR(100) 
	**/
	private String m_aszPathAutoPunctuation=null;
	public void setPathAutoPunctuation(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPunctuation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPunctuation = aszTemp;
			return;
		}
		m_aszPathAutoPunctuation = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPunctuation(){
		if(m_aszPathAutoPunctuation == null) return "";
		return m_aszPathAutoPunctuation;
	}
	/**
	** PathAuto Punctuation Value; type VARCHAR(100) 
	**/
	private String m_aszPathAutoPunctuationValue=null;
	public void setPathAutoPunctuationValue(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPunctuationValue = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPunctuationValue = aszTemp;
			return;
		}
		m_aszPathAutoPunctuationValue = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPunctuationValue(){
		if(m_aszPathAutoPunctuationValue == null) return "";
		return m_aszPathAutoPunctuationValue;
	}

	

	/**
	** PathAuto Punctuation Ampersand & ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncAmp=null;
	public void setPathAutoPuncAmp(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncAmp = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncAmp = aszTemp;
			return;
		}
		m_aszPathAutoPuncAmp = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncAmp(){
		if(m_aszPathAutoPuncAmp == null) return "";
		return m_aszPathAutoPuncAmp;
	}
	
	/**
	** PathAuto Punctuation Asterisk; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncAstrsk=null;
	public void setPathAutoPuncAstrsk(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncAstrsk = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncAstrsk = aszTemp;
			return;
		}
		m_aszPathAutoPuncAstrsk = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncAstrsk(){
		if(m_aszPathAutoPuncAstrsk == null) return "";
		return m_aszPathAutoPuncAstrsk;
	}
	
	/**
	** PathAuto Punctuation At @ ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncAt=null;
	public void setPathAutoPuncAt(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncAt = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncAt = aszTemp;
			return;
		}
		m_aszPathAutoPuncAt = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncAt(){
		if(m_aszPathAutoPuncAt == null) return "";
		return m_aszPathAutoPuncAt;
	}
	
	/**
	** PathAuto Punctuation Back Tick ` ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncBckTck=null;
	public void setPathAutoPuncBckTck(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncBckTck = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncBckTck = aszTemp;
			return;
		}
		m_aszPathAutoPuncBckTck = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncBckTck(){
		if(m_aszPathAutoPuncBckTck == null) return "";
		return m_aszPathAutoPuncBckTck;
	}
	
	/**
	** PathAuto Punctuation Black Slash \ ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncBckSlsh=null;
	public void setPathAutoPuncBckSlsh(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncBckSlsh = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncBckSlsh = aszTemp;
			return;
		}
		m_aszPathAutoPuncBckSlsh = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncBckSlsh(){
		if(m_aszPathAutoPuncBckSlsh == null) return "";
		return m_aszPathAutoPuncBckSlsh;
	}
	
	/**
	** PathAuto Punctuation Caret ^ ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncCaret=null;
	public void setPathAutoPuncCaret(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncCaret = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncCaret = aszTemp;
			return;
		}
		m_aszPathAutoPuncCaret = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncCaret(){
		if(m_aszPathAutoPuncCaret == null) return "";
		return m_aszPathAutoPuncCaret;
	}
	
	/**
	** PathAuto Punctuation Colon : ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncColon=null;
	public void setPathAutoPuncColon(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncColon = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncColon = aszTemp;
			return;
		}
		m_aszPathAutoPuncColon = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncColon(){
		if(m_aszPathAutoPuncColon == null) return "";
		return m_aszPathAutoPuncColon;
	}
	
	/**
	** PathAuto Punctuation Comma , ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncComma=null;
	public void setPathAutoPuncComma(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncComma = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncComma = aszTemp;
			return;
		}
		m_aszPathAutoPuncComma = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncComma(){
		if(m_aszPathAutoPuncComma == null) return "";
		return m_aszPathAutoPuncComma;
	}
	
	/**
	** PathAuto Punctuation Dollar Sign; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncDolrSign=null;
	public void setPathAutoPuncDolrSign(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncDolrSign = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncDolrSign = aszTemp;
			return;
		}
		m_aszPathAutoPuncDolrSign = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncDolrSign(){
		if(m_aszPathAutoPuncDolrSign == null) return "";
		return m_aszPathAutoPuncDolrSign;
	}
	
	/**
	** PathAuto Punctuation Double Quotes " ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncDblQuotes=null;
	public void setPathAutoPuncDblQuotes(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncDblQuotes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncDblQuotes = aszTemp;
			return;
		}
		m_aszPathAutoPuncDblQuotes = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncDblQuotes(){
		if(m_aszPathAutoPuncDblQuotes == null) return "";
		return m_aszPathAutoPuncDblQuotes;
	}
	
	/**
	** PathAuto Punctuation Equal = ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncEqual=null;
	public void setPathAutoPuncEqual(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncEqual = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncEqual = aszTemp;
			return;
		}
		m_aszPathAutoPuncEqual = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncEqual(){
		if(m_aszPathAutoPuncEqual == null) return "";
		return m_aszPathAutoPuncEqual;
	}
	
	/**
	** PathAuto Punctuation Exclamation ! ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncExclam=null;
	public void setPathAutoPuncExclam(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncExclam = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncExclam = aszTemp;
			return;
		}
		m_aszPathAutoPuncExclam = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncExclam(){
		if(m_aszPathAutoPuncExclam == null) return "";
		return m_aszPathAutoPuncExclam;
	}
	
	/**
	** PathAuto Punctuation Greater Than > ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncGrtr=null;
	public void setPathAutoPuncGrtr(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncGrtr = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncGrtr = aszTemp;
			return;
		}
		m_aszPathAutoPuncGrtr = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncGrtr(){
		if(m_aszPathAutoPuncGrtr == null) return "";
		return m_aszPathAutoPuncGrtr;
	}
	
	/**
	** PathAuto Punctuation Hash #; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncHash=null;
	public void setPathAutoPuncHash(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncHash = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncHash = aszTemp;
			return;
		}
		m_aszPathAutoPuncHash = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncHash(){
		if(m_aszPathAutoPuncHash == null) return "";
		return m_aszPathAutoPuncHash;
	}
	
	/**
	** PathAuto Punctuation Hyphen - ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncHyphen=null;
	public void setPathAutoPuncHyphen(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncHyphen = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncHyphen = aszTemp;
			return;
		}
		m_aszPathAutoPuncHyphen = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncHyphen(){
		if(m_aszPathAutoPuncHyphen == null) return "";
		return m_aszPathAutoPuncHyphen;
	}
	
	/**
	** PathAuto Punctuation Left Curly Bracket { ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncLftCrly=null;
	public void setPathAutoPuncLftCrly(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncLftCrly = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncLftCrly = aszTemp;
			return;
		}
		m_aszPathAutoPuncLftCrly = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncLftCrly(){
		if(m_aszPathAutoPuncLftCrly == null) return "";
		return m_aszPathAutoPuncLftCrly;
	}
	
	/**
	** PathAuto Punctuation Left Parenthesis ) ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncLftParen=null;
	public void setPathAutoPuncLftParen(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncLftParen = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncLftParen = aszTemp;
			return;
		}
		m_aszPathAutoPuncLftParen = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncLftParen(){
		if(m_aszPathAutoPuncLftParen == null) return "";
		return m_aszPathAutoPuncLftParen;
	}
	
	/**
	** PathAuto Punctuation Left Square ] ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncLftSq=null;
	public void setPathAutoPuncLftSq(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncLftSq = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncLftSq = aszTemp;
			return;
		}
		m_aszPathAutoPuncLftSq = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncLftSq(){
		if(m_aszPathAutoPuncLftSq == null) return "";
		return m_aszPathAutoPuncLftSq;
	}
	
	/**
	** PathAuto Punctuation Less Than  ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncLess=null;
	public void setPathAutoPuncLess(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncLess = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncLess = aszTemp;
			return;
		}
		m_aszPathAutoPuncLess = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncLess(){
		if(m_aszPathAutoPuncLess == null) return "";
		return m_aszPathAutoPuncLess;
	}
	
	/**
	** PathAuto Punctuation Percent % ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncPerc=null;
	public void setPathAutoPuncPerc(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncPerc = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncPerc = aszTemp;
			return;
		}
		m_aszPathAutoPuncPerc = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncPerc(){
		if(m_aszPathAutoPuncPerc == null) return "";
		return m_aszPathAutoPuncPerc;
	}
	
	/**
	** PathAuto Punctuation Period . ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncPeriod=null;
	public void setPathAutoPuncPeriod(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncPeriod = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncPeriod = aszTemp;
			return;
		}
		m_aszPathAutoPuncPeriod = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncPeriod(){
		if(m_aszPathAutoPuncPeriod == null) return "";
		return m_aszPathAutoPuncPeriod;
	}
	
	/**
	** PathAuto Punctuation Pipe | ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncPipe=null;
	public void setPathAutoPuncPipe(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncPipe = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncPipe = aszTemp;
			return;
		}
		m_aszPathAutoPuncPipe = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncPipe(){
		if(m_aszPathAutoPuncPipe == null) return "";
		return m_aszPathAutoPuncPipe;
	}
	
	/**
	** PathAuto Punctuation Plus + ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncPlus=null;
	public void setPathAutoPuncPlus(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncPlus = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncPlus = aszTemp;
			return;
		}
		m_aszPathAutoPuncPlus = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncPlus(){
		if(m_aszPathAutoPuncPlus == null) return "";
		return m_aszPathAutoPuncPlus;
	}
	
	/**
	** PathAuto Punctuation Question Mark ? ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncQuest=null;
	public void setPathAutoPuncQuest(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncQuest = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncQuest = aszTemp;
			return;
		}
		m_aszPathAutoPuncQuest = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncQuest(){
		if(m_aszPathAutoPuncQuest == null) return "";
		return m_aszPathAutoPuncQuest;
	}
	
	/**
	** PathAuto Punctuation Single Quote ' ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncSingleQut=null;
	public void setPathAutoPuncSingleQut(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncSingleQut = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncSingleQut = aszTemp;
			return;
		}
		m_aszPathAutoPuncSingleQut = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncSingleQut(){
		if(m_aszPathAutoPuncSingleQut == null) return "";
		return m_aszPathAutoPuncSingleQut;
	}
	
	/**
	** PathAuto Punctuation Right Curly Bracket { ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncRtCurly=null;
	public void setPathAutoPuncRtCurly(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncRtCurly = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncRtCurly = aszTemp;
			return;
		}
		m_aszPathAutoPuncRtCurly = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncRtCurly(){
		if(m_aszPathAutoPuncRtCurly == null) return "";
		return m_aszPathAutoPuncRtCurly;
	}
	
	/**
	** PathAuto Punctuation Right Paranthesis ( ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncRtParen=null;
	public void setPathAutoPuncRtParen(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncRtParen = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncRtParen = aszTemp;
			return;
		}
		m_aszPathAutoPuncRtParen = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncRtParen(){
		if(m_aszPathAutoPuncRtParen == null) return "";
		return m_aszPathAutoPuncRtParen;
	}
	
	/**
	** PathAuto Punctuation Right Square Bracket [; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncRtSq=null;
	public void setPathAutoPuncRtSq(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncRtSq = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncRtSq = aszTemp;
			return;
		}
		m_aszPathAutoPuncRtSq = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncRtSq(){
		if(m_aszPathAutoPuncRtSq == null) return "";
		return m_aszPathAutoPuncRtSq;
	}
	
	/**
	** PathAuto Punctuation Semi-colon ; ; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncSemiColon=null;
	public void setPathAutoPuncSemiColon(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncSemiColon = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncSemiColon = aszTemp;
			return;
		}
		m_aszPathAutoPuncSemiColon = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncSemiColon(){
		if(m_aszPathAutoPuncSemiColon == null) return "";
		return m_aszPathAutoPuncSemiColon;
	}
	
	/**
	** PathAuto Punctuation Tilde ~; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncTilde=null;
	public void setPathAutoPuncTilde(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncTilde = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncTilde = aszTemp;
			return;
		}
		m_aszPathAutoPuncTilde = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncTilde(){
		if(m_aszPathAutoPuncTilde == null) return "";
		return m_aszPathAutoPuncTilde;
	}
	
	/**
	** PathAuto Punctuation UnderScore _; type VARCHAR(15) 
	**/
	private String m_aszPathAutoPuncUnderScore=null;
	public void setPathAutoPuncUnderScore(String value){
		int iLen=15;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPathAutoPuncUnderScore = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPathAutoPuncUnderScore = aszTemp;
			return;
		}
		m_aszPathAutoPuncUnderScore = aszTemp.substring(0,iLen);
	}
	public String getPathAutoPuncUnderScore(){
		if(m_aszPathAutoPuncUnderScore == null) return "";
		return m_aszPathAutoPuncUnderScore;
	}
	
	private List<QuestionnaireDTO> m_aszQuestionnaires = null;
	public void setQuestionnaires(List<QuestionnaireDTO> questionnaires) {
		m_aszQuestionnaires = questionnaires;
	}
	public List<QuestionnaireDTO> getQuestionnaires() {
		return m_aszQuestionnaires;
	}

}
