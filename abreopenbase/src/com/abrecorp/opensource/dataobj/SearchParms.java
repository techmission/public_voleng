package com.abrecorp.opensource.dataobj;

/**
* Search Parameters Object
*/

import java.io.*;

public class SearchParms  extends BaseInfoObj implements Serializable, Cloneable {

	public static final int LATEST_LOCAL=101;
	public static final int LATEST_VIRTUAL=102;
	public static final int LATEST_GRP_FAMILY=103;
	public static final int LATEST_STM=104;
	public static final int LATEST_SUM_INTRN=105;
	public static final int LATEST_INTRN=106;
	public static final int LATEST_WORK_STUDY=107;
	public static final int LATEST_BOARD=108;
	public static final int LATEST_LOCAL_ORGS=109;
	public static final int LATEST_TRAINING=110;

	public static final int PORTAL_OWN_LIST=201;
	public static final int PORTAL_OTHERFAV_LIST=202;


	/**
	* Constructor
	*/
    public SearchParms(){}

	/**
	* query string (store in db)
	*/
    private String m_aszSearchQueryString=null;
	public void setSearchQueryString(String temp){
		if(temp == null){
			m_aszSearchQueryString=null;
			return ;
		}
		m_aszSearchQueryString=temp.trim();
	}
	public String getSearchQueryString(){
		if(null == m_aszSearchQueryString) return "";
		return m_aszSearchQueryString;
	}

	/**
	* ReferringPage string (store in db)
	*/
    private String m_aszReferringPage=null;
	public void setReferringPage(String temp){
		if(temp == null){
			m_aszReferringPage=null;
			return ;
		}
		m_aszReferringPage=temp.trim();
	}
	public String getReferringPage(){
		if(null == m_aszReferringPage) return "";
		return m_aszReferringPage;
	}

	/**
	* UserAgent string (store in db)
	*/
    private String m_aszUserAgent=null;
	public void setUserAgent(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUserAgent = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUserAgent = aszTemp;
			return;
		}
		m_aszUserAgent = aszTemp.substring(0,iLen);
	}
	public String getUserAgent(){
		if(m_aszUserAgent == null) return "";
		return m_aszUserAgent;
	}

	/**
	* subdomain string (store in db)
	*/
    private String m_aszSubdomain=null;
	public void setSubdomain(String temp){
		if(temp == null){
			m_aszSubdomain=null;
			return ;
		}
		m_aszSubdomain=temp.trim();
	}
	public String getSubdomain(){
		if(null == m_aszSubdomain) return "";
		return m_aszSubdomain;
	}

	/**
	* method name (store in db)
	*/
    private String m_aszSearchMethod=null;
	public void setSearchMethod(String temp){
		if(temp == null){
			m_aszSearchMethod=null;
			return ;
		}
		m_aszSearchMethod=temp.trim();
	}
	public String getSearchMethod(){
		if(null == m_aszSearchMethod) return "";
		return m_aszSearchMethod;
	}

	/**
	*  latest
	*/
	private boolean m_bSearchLatest=false;
	public void setSearchLatest(boolean type){
		m_bSearchLatest=type;
	}
	public boolean getSearchLatest(){
		return m_bSearchLatest;
	}

	/**
	* search order type
	*/
	private int m_iRelatedTerm=0;
	public void setRelatedTerm(int type){
		m_iRelatedTerm=type;
	}
	public void setRelatedTerm(String number){
		m_iRelatedTerm = convertToInt(number);
	}
	public int getRelatedTerm(){
		return m_iRelatedTerm;
	}


	/**
	* search order type
	*/
	private int m_iSearchOrderNumber=0;
	public void setSearchOrder(int type){
		m_iSearchOrderNumber=type;
	}
	public void setSearchOrder(String number){
		m_iSearchOrderNumber = convertToInt(number);
	}
	public int getSearchOrder(){
		return m_iSearchOrderNumber;
	}

	/**
	* SearchResultsCount
	*/
	private int m_iSearchResultsCount=150;
	public void setSearchResultsCount(int type){
		m_iSearchResultsCount=type;
	}
	public void setSearchResultsCount(String number){
		m_iSearchResultsCount = convertToInt(number);
	}
	public int getSearchResultsCount(){
		return m_iSearchResultsCount;
	}

	/**
	* set search max rows
	*/
	private int m_iMaxSearchResultRows=150;
	public void setmaxSearchResultRows(int type){
		m_iMaxSearchResultRows=type;
	}
	public void setmaxSearchResultRows(String number){
		m_iMaxSearchResultRows = convertToInt(number);
	}
	public int getmaxSearchResultRows(){
		return m_iMaxSearchResultRows;
	}

	/**
	** setPortalNID
	**/
	private int m_iPortalNID=0;
	public void setPortalNID(int number){
		m_iPortalNID = number;
	}
	public void setPortalNID(String number){
		m_iPortalNID = convertToInt(number);
		return;
	}
	public int getPortalNID(){
		return m_iPortalNID;
	}

	/**
	** m_iPortalUID
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
	** m_iPortalAdminUID
	**/
	private int m_iPortalAdminUID=0;
	public void setPortalAdminUID(int number){
		m_iPortalAdminUID = number;
	}
	public void setPortalAdminUID(String number){
		m_iPortalAdminUID = convertToInt(number);
		return;
	}
	public int getPortalAdminUID(){
		return m_iPortalAdminUID;
	}
	

	/**
	** searching for a list of all portals? type boolean
	**/
	private boolean b_portal=false;
	public void setSearchPortals(boolean portal){
		b_portal = portal;
		return;
	}
	public void setSearchPortals(String portal){
		if(portal.equals("true")){
			b_portal=true;
		}else{
			b_portal=false;
		}
		return;
	}
	public boolean getSearchPortals(){
		return b_portal;
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
	* search type
	*/
	private int m_iSearchTypeNumber=0;
	public void setSearchType(int type){
		m_iSearchTypeNumber=type;
	}
	public void setSearchType(String number){
		m_iSearchTypeNumber = convertToInt(number);
	}
	public int getSearchType(){
		return m_iSearchTypeNumber;
	}

	/**
	* search key value
	*/
	private String m_SearchKeyValue=null;
	public void setSearchKey(String key){
		if(null == key){
			m_SearchKeyValue=null;
			return ;
		}
		m_SearchKeyValue=key.trim();
	}
	public String getSearchKey(){
		if(null == m_SearchKeyValue) return "";
		return m_SearchKeyValue;
	}

	/**
	* search key second value
	*/
	private String m_SearchKey2Value=null;
	public void setSearchKey2(String key){
		if(null == key){
			m_SearchKey2Value=null;
			return ;
		}
		m_SearchKey2Value=key.trim();
	}
	public String getSearchKey2(){
		if(null == m_SearchKey2Value) return "";
		return m_SearchKey2Value;
	}

	/**
	* search key third value
	*/
	private String m_SearchKey3Value=null;
	public void setSearchKey3(String key){
		if(null == key){
			m_SearchKey3Value=null;
			return ;
		}
		m_SearchKey3Value=key.trim();
	}
	public String getSearchKey3(){
		if(null == m_SearchKey3Value) return "";
		return m_SearchKey3Value;
	}

	/**
	* search request type
	*/
	private String m_SearchRequestType=null;
	public void setSearchRequestType(String key){
		if(null == key){
			m_SearchRequestType=null;
			return ;
		}
		m_SearchRequestType=key.trim();
	}
	public String getSearchRequestType(){
		if(null == m_SearchRequestType) return "";
		return m_SearchRequestType;
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
	** source that the opportunity came through - varchar;
	*  for now, will just be "feeds" or "" - not stored in db
	*  but may update later to show the actual source from the db
	*  if we start gathering diverse data for that
	**/
	private String m_aszSource=null;
	public void setSource(String value){
		int iLen=100;
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
	private String m_aszSearchForm=null;
	public void setSearchForm(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSearchForm = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSearchForm = aszTemp;
			return;
		}
		m_aszSearchForm = aszTemp.substring(0,iLen);
	}
	public String getSearchForm(){
		if(m_aszSearchForm == null) return "";
		return m_aszSearchForm;
	}
	private String m_aszOPPReferralURL=null;
	public void setOPPReferralURL(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPReferralURL = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPReferralURL = aszTemp;
			return;
		}
		m_aszOPPReferralURL = aszTemp.substring(0,iLen);
	}
	public String getOPPReferralURL(){
		if(m_aszOPPReferralURL == null) return "";
		return m_aszOPPReferralURL;
	}

	/**
	* Category
	*/
    private String m_Category=null;
	public void setCategory(String date){
		if(date == null){
			m_Category=null;
			return ;
		}
		m_Category=date.trim();
	}
	public String getCategory(){
		if(null == m_Category) return "";
		return m_Category;
	}

	/**
	* Category two
	*/
    private String m_Category2=null;
	public void setCategory2(String date){
		if(date == null){
			m_Category2=null;
			return ;
		}
		m_Category2=date.trim();
	}
	public String getCategory2(){
		if(null == m_Category2) return "";
		return m_Category2;
	}

	/**
	* Category three
	*/
    private String m_Category3=null;
	public void setCategory3(String date){
		if(date == null){
			m_Category3=null;
			return ;
		}
		m_Category3=date.trim();
	}
	public String getCategory3(){
		if(null == m_Category3) return "";
		return m_Category3;
	}

	/**
	* Program Type
	*/
    private String m_ProgramType=null;
	public void setProgramType(String date){
		if(date == null){
			m_ProgramType=null;
			return ;
		}
		m_ProgramType=date.trim();
	}
	public String getProgramType(){
		if(null == m_ProgramType) return "";
		return m_ProgramType;
	}
	
	/**
	** Program Type tid type LONG()
	**/
	private int m_iProgramTypeTID=-1;
	public void setProgramTypeTID(int number){
		m_iProgramTypeTID = number;
	}
	public void setProgramTypeTID(String number){
		m_iProgramTypeTID = convertToInt(number);
		return;
	}
	public int getProgramTypeTID(){
		return m_iProgramTypeTID;
	}

	
	/**
	* Focus Area 1
	*/
    private String m_FocusArea=null;
	public void setFocusArea(String date){
		if(date == null){
			m_FocusArea=null;
			return ;
		}
		m_FocusArea=date.trim();
	}
	public String getFocusArea(){
		if(null == m_FocusArea) return "";
		return m_FocusArea;
	}

	/**
	* FocusArea two
	*/
    private String m_FocusArea2=null;
	public void setFocusArea2(String date){
		if(date == null){
			m_FocusArea2=null;
			return ;
		}
		m_FocusArea2=date.trim();
	}
	public String getFocusArea2(){
		if(null == m_FocusArea2) return "";
		return m_FocusArea2;
	}

	/**
	* FocusArea three
	*/
    private String m_FocusArea3=null;
	public void setFocusArea3(String date){
		if(date == null){
			m_FocusArea3=null;
			return ;
		}
		m_FocusArea3=date.trim();
	}
	public String getFocusArea3(){
		if(null == m_FocusArea3) return "";
		return m_FocusArea3;
	}

	/**
	* FocusArea four
	*/
    private String m_FocusArea4=null;
	public void setFocusArea4(String date){
		if(date == null){
			m_FocusArea4=null;
			return ;
		}
		m_FocusArea4=date.trim();
	}
	public String getFocusArea4(){
		if(null == m_FocusArea4) return "";
		return m_FocusArea4;
	}

	/**
	* Focus Area five
	*/
    private String m_FocusArea5=null;
	public void setFocusArea5(String date){
		if(date == null){
			m_FocusArea5=null;
			return ;
		}
		m_FocusArea5=date.trim();
	}
	public String getFocusArea5(){
		if(null == m_FocusArea5) return "";
		return m_FocusArea5;
	}

	
	/**
	** Focus Areas - Great For 1 tid type LONG()
	**/
	private int m_iGreatFor1TID=-1;
	public void setGreatFor1TID(int number){
		m_iGreatFor1TID = number;
	}
	public void setGreatFor1TID(String number){
		m_iGreatFor1TID = convertToInt(number);
		return;
	}
	public int getGreatFor1TID(){
		return m_iGreatFor1TID;
	}

	/**
	** Focus Areas - Great For 2 tid type LONG()
	**/
	private int m_iGreatFor2TID=-1;
	public void setGreatFor2TID(int number){
		m_iGreatFor2TID = number;
	}
	public void setGreatFor2TID(String number){
		m_iGreatFor2TID = convertToInt(number);
		return;
	}
	public int getGreatFor2TID(){
		return m_iGreatFor2TID;
	}

	/**
	** Focus Areas - Great For 3 tid type LONG()
	**/
	private int m_iGreatFor3TID=-1;
	public void setGreatFor3TID(int number){
		m_iGreatFor3TID = number;
	}
	public void setGreatFor3TID(String number){
		m_iGreatFor3TID = convertToInt(number);
		return;
	}
	public int getGreatFor3TID(){
		return m_iGreatFor3TID;
	}

	/**
	** Focus Areas - Great For 4 tid type LONG()
	**/
	private int m_iGreatFor4TID=-1;
	public void setGreatFor4TID(int number){
		m_iGreatFor4TID = number;
	}
	public void setGreatFor4TID(String number){
		m_iGreatFor4TID = convertToInt(number);
		return;
	}
	public int getGreatFor4TID(){
		return m_iGreatFor4TID;
	}

	/**
	** Focus Areas - Great For 5 tid type LONG()
	**/
	private int m_iGreatFor5TID=-1;
	public void setGreatFor5TID(int number){
		m_iGreatFor5TID = number;
	}
	public void setGreatFor5TID(String number){
		m_iGreatFor5TID = convertToInt(number);
		return;
	}
	public int getGreatFor5TID(){
		return m_iGreatFor5TID;
	}

	
	/**
	** Focus Areas - Great For 1:Kid tid type LONG()
	**/
	private int m_iGreatForKidTID=-1;
	public void setGreatForKidTID(int number){
		m_iGreatForKidTID = number;
	}
	public void setGreatForKidTID(String number){
		m_iGreatForKidTID = convertToInt(number);
		return;
	}
	public int getGreatForKidTID(){
		return m_iGreatForKidTID;
	}

	/**
	** Focus Areas - Great For 2:Senior tid type LONG()
	**/
	private int m_iGreatForSeniorTID=-1;
	public void setGreatForSeniorTID(int number){
		m_iGreatForSeniorTID = number;
	}
	public void setGreatForSeniorTID(String number){
		m_iGreatForSeniorTID = convertToInt(number);
		return;
	}
	public int getGreatForSeniorTID(){
		return m_iGreatForSeniorTID;
	}

	/**
	** Focus Areas - Great For 3:Teen tid type LONG()
	**/
	private int m_iGreatForTeenTID=-1;
	public void setGreatForTeenTID(int number){
		m_iGreatForTeenTID = number;
	}
	public void setGreatForTeenTID(String number){
		m_iGreatForTeenTID = convertToInt(number);
		return;
	}
	public int getGreatForTeenTID(){
		return m_iGreatForTeenTID;
	}

	/**
	** Focus Areas - Great For 4:Group tid type LONG()
	**/
	private int m_iGreatForGroupTID=-1;
	public void setGreatForGroupTID(int number){
		m_iGreatForGroupTID = number;
	}
	public void setGreatForGroupTID(String number){
		m_iGreatForGroupTID = convertToInt(number);
		return;
	}
	public int getGreatForGroupTID(){
		return m_iGreatForGroupTID;
	}

	/**
	** Focus Areas - Great For 5:Family tid type LONG()
	**/
	private int m_iGreatForFamilyTID=-1;
	public void setGreatForFamilyTID(int number){
		m_iGreatForFamilyTID = number;
	}
	public void setGreatForFamilyTID(String number){
		m_iGreatForFamilyTID = convertToInt(number);
		return;
	}
	public int getGreatForFamilyTID(){
		return m_iGreatForFamilyTID;
	}
	
	
	
	/**
	** length of trip type VARCHAR(100)  
	**/
	private String m_aszOPPTripLength=null;
	public void setOPPTripLength(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPTripLength = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPTripLength = aszTemp;
			return;
		}
		m_aszOPPTripLength = aszTemp.substring(0,iLen);
	}
	public String getOPPTripLength(){
		if(m_aszOPPTripLength == null) return "";
		return m_aszOPPTripLength;
	}
	
	/**
	** Length of Trip type LONG()
	**/
	private int m_iOPPTripLengthTID=-1;
	public void setOPPTripLengthTID(int number){
		m_iOPPTripLengthTID = number;
	}
	public void setOPPTripLengthTID(String number){
		m_iOPPTripLengthTID = convertToInt(number);
		return;
	}
	public int getOPPTripLengthTID(){
		return m_iOPPTripLengthTID;
	}


	/**
	** WorkStudy - would you consider paying a work study college student.... type TEXT() in taxonomy
	**/
	private String m_aszOPPWorkStudy=null;

	public void setOPPWorkStudy(String value){
		if(null == value){
			m_aszOPPWorkStudy = null;
			return;
		}
		m_aszOPPWorkStudy = value.trim();
	}
	public String getOPPWorkStudy(){
		if(m_aszOPPWorkStudy == null) return "";
		return m_aszOPPWorkStudy;
	}
	
	
	/**
	** Work Study type LONG()
	**/
	private int m_iOPPWorkStudyTID=-1;
	public void setOPPWorkStudyTID(int number){
		m_iOPPWorkStudyTID = number;
	}
	public void setOPPWorkStudyTID(String number){
		m_iOPPWorkStudyTID = convertToInt(number);
		return;
	}
	public int getOPPWorkStudyTID(){
		return m_iOPPWorkStudyTID;
	}

	/**
	** (short term missions) are Room Board included? type TEXT() in taxonomy
	**/
	private String m_aszOPPRoomBoard=null;

	public void setOPPRoomBoard(String value){
		if(null == value){
			m_aszOPPRoomBoard = null;
			return;
		}
		m_aszOPPRoomBoard = value.trim();
	}
	public String getOPPRoomBoard(){
		if(m_aszOPPRoomBoard == null) return "";
		return m_aszOPPRoomBoard;
	}
	
	/**
	** Room and Board type LONG()
	**/
	private int m_iOPPRoomBoardTID=-1;
	public void setOPPRoomBoardTID(int number){
		m_iOPPRoomBoardTID = number;
	}
	public void setOPPRoomBoardTID(String number){
		m_iOPPRoomBoardTID = convertToInt(number);
		return;
	}
	public int getOPPRoomBoardTID(){
		return m_iOPPRoomBoardTID;
	}

	/**
	** (short term missions) is  Stipend included? type TEXT() in taxonomy
	**/
	private String m_aszOPPStipend=null;

	public void setOPPStipend(String value){
		if(null == value){
			m_aszOPPStipend = null;
			return;
		}
		m_aszOPPStipend = value.trim();
	}
	public String getOPPStipend(){
		if(m_aszOPPStipend == null) return "";
		return m_aszOPPStipend;
	}
	
	/**
	** Stipend type LONG()
	**/
	private int m_iOPPStipendTID=-1;
	public void setOPPStipendTID(int number){
		m_iOPPStipendTID = number;
	}
	public void setOPPStipendTID(String number){
		m_iOPPStipendTID = convertToInt(number);
		return;
	}
	public int getOPPStipendTID(){
		return m_iOPPStipendTID;
	}


	/**
	** (short term missions) are MedInsur included? type TEXT() in taxonomy
	**/
	private String m_aszOPPMedInsur=null;

	public void setOPPMedInsur(String value){
		if(null == value){
			m_aszOPPMedInsur = null;
			return;
		}
		m_aszOPPMedInsur = value.trim();
	}
	public String getOPPMedInsur(){
		if(m_aszOPPMedInsur == null) return "";
		return m_aszOPPMedInsur;
	}
	
	/**
	** Medical Insurance type LONG()
	**/
	private int m_iOPPMedInsurTID=-1;
	public void setOPPMedInsurTID(int number){
		m_iOPPMedInsurTID = number;
	}
	public void setOPPMedInsurTID(String number){
		m_iOPPMedInsurTID = convertToInt(number);
		return;
	}
	public int getOPPMedInsurTID(){
		return m_iOPPMedInsurTID;
	}


	/**
	** (short term missions) is Transportation included? type TEXT() in taxonomy
	**/
	private String m_aszOPPTransport=null;

	public void setOPPTransport(String value){
		if(null == value){
			m_aszOPPTransport = null;
			return;
		}
		m_aszOPPTransport = value.trim();
	}
	public String getOPPTransport(){
		if(m_aszOPPTransport == null) return "";
		return m_aszOPPTransport;
	}
	
	/**
	** Transport type LONG()
	**/
	private int m_iOPPTransportTID=-1;
	public void setOPPTransportTID(int number){
		m_iOPPTransportTID = number;
	}
	public void setOPPTransportTID(String number){
		m_iOPPTransportTID = convertToInt(number);
		return;
	}
	public int getOPPTransportTID(){
		return m_iOPPTransportTID;
	}

	/**
	** (short term missions) is AmeriCorps included? type TEXT() in taxonomy
	**/
	private String m_aszOPPAmeriCorps=null;

	public void setOPPAmeriCorps(String value){
		if(null == value){
			m_aszOPPAmeriCorps = null;
			return;
		}
		m_aszOPPAmeriCorps = value.trim();
	}
	public String getOPPAmeriCorps(){
		if(m_aszOPPAmeriCorps == null) return "";
		return m_aszOPPAmeriCorps;
	}
	
	/**
	** AmeriCorps type LONG()
	**/
	private int m_iOPPAmeriCorpsTID=-1;
	public void setOPPAmeriCorpsTID(int number){
		m_iOPPAmeriCorpsTID = number;
	}
	public void setOPPAmeriCorpsTID(String number){
		m_iOPPAmeriCorpsTID = convertToInt(number);
		return;
	}
	public int getOPPAmeriCorpsTID(){
		return m_iOPPAmeriCorpsTID;
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
	* Cursor Key used to get next set of items in returned call
	*/
    private String m_Cursor1Key=null;
	public void setCursor1Key(String date){
		if(date == null){
			m_Cursor1Key=null;
			return ;
		}
		m_Cursor1Key=date.trim();
	}
	public String getCursor1Key(){
		if(null == m_Cursor1Key) return "";
		return m_Cursor1Key;
	}

	/**
	* m_aszGeoFilter
	*/
    private String m_aszGeoFilter=null;
	public void setGeoFilter(String date){
		if(date == null){
			m_aszGeoFilter=null;
			return ;
		}
		m_aszGeoFilter=date.trim();
	}
	public String getGeoFilter(){
		if(null == m_aszGeoFilter) return "";
		return m_aszGeoFilter;
	}


	/**
	* Postal Code
	*/
    private String m_aszPostalCode=null;
	public void setPostalCode(String date){
		if(date == null){
			m_aszPostalCode=null;
			return ;
		}
		m_aszPostalCode=date.trim();
	}
	public String getPostalCode(){
		if(null == m_aszPostalCode) return "";
		return m_aszPostalCode;
	}

	/**
	* m_aszZIP
	*/
    private String m_aszZIP=null;
	public void setZIP(String date){
		if(date == null){
			m_aszZIP=null;
			return ;
		}
		m_aszZIP=date.trim();
	}
	public String getZIP(){
		if(null == m_aszZIP) return "";
		return m_aszZIP;
	}

	/**
	* NOT LOCAL Postal Code (will be used to EXCLUDE)
	*/
    private String m_aszNotLclPostalCode=null;
	public void setNotLclPostalCode(String date){
		if(date == null){
			m_aszNotLclPostalCode=null;
			return ;
		}
		m_aszNotLclPostalCode=date.trim();
	}
	public String getNotLclPostalCode(){
		if(null == m_aszNotLclPostalCode) return "";
		return m_aszNotLclPostalCode;
	}
	
	/**
	* Keyword
	*/
    private String m_aszKeyWord=null;
	public void setKeyWord(String date){
		if(date == null){
			m_aszKeyWord=null;
			return ;
		}
		m_aszKeyWord=date.trim();
	}
	public String getKeyWord(){
		if(null == m_aszKeyWord) return "";
		return m_aszKeyWord;
	}

	
	/*
	 * array of keywords
	 */
	private String[] a_aszKeywordsArray=null;
	public void setKeywordsArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszKeywordsArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszKeywordsArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszKeywordsArray = a_aszTemp;
			return;
		}
	}
	public String[] getKeywordsArray(){
		if(a_aszKeywordsArray == null) {
			a_aszKeywordsArray=new String[0];
			return a_aszKeywordsArray;
		}
		return a_aszKeywordsArray;
	}
	/**
	* Skills1
	*/
    private String m_aszSkills1=null;
	public void setSkills1(String date){
		if(date == null){
			m_aszSkills1=null;
			return ;
		}
		m_aszSkills1=date.trim();
	}
	public String getSkills1(){
		if(null == m_aszSkills1) return "";
		return m_aszSkills1;
	}

	/**
	* Skills2
	*/
    private String m_aszSkills2=null;
	public void setSkills2(String date){
		if(date == null){
			m_aszSkills2=null;
			return ;
		}
		m_aszSkills2=date.trim();
	}
	public String getSkills2(){
		if(null == m_aszSkills2) return "";
		return m_aszSkills2;
	}

	/**
	* Skills3
	*/
    private String m_aszSkills3=null;
	public void setSkills3(String date){
		if(date == null){
			m_aszSkills3=null;
			return ;
		}
		m_aszSkills3=date.trim();
	}
	public String getSkills3(){
		if(null == m_aszSkills3) return "";
		return m_aszSkills3;
	}
	
	/**
	* Opportunity Number of Volunteers
	*/
    private String m_aszNumVolOpp=null;
	public void setNumVolOpp(String date){
		if(date == null){
			m_aszNumVolOpp=null;
			return ;
		}
		m_aszNumVolOpp=date.trim();
	}
	public String getNumVolOpp(){
		if(null == m_aszNumVolOpp) return "";
		return m_aszNumVolOpp;
	}	

	/**
	** frequency type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFreq=null;
	public void setOPPFreq(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFreq = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFreq = aszTemp;
			return;
		}
		m_aszOPPFreq = aszTemp.substring(0,iLen);
	}
	public String getOPPFreq(){
		if(m_aszOPPFreq == null) return "";
		return m_aszOPPFreq;
	}
	/**
	* Frequency
	*/
    private String m_aszFrequency=null;
	public void setFrequency(String date){
		if(date == null){
			m_aszFrequency=null;
			return ;
		}
		m_aszFrequency=date.trim();
	}
	public String getFrequency(){
		if(null == m_aszFrequency) return "";
		return m_aszFrequency;
	}


	/**
	** FrequencyTID 
	**/
	private int m_iOPPFrequencyTID=0;
	public void setOPPFrequencyTID(int number){
		m_iOPPFrequencyTID = number;
	}
	public void setOPPFrequencyTID(String number){
		m_iOPPFrequencyTID = convertToInt(number);
		return;
	}
	public int getOPPFrequencyTID(){
		return m_iOPPFrequencyTID;
	}


	/**
	* Required Code - Y or N or No Preference
	*/
    private String m_aszReqCode=null;
	public void setReqCode(String date){
		if(date == null){
			m_aszReqCode=null;
			return ;
		}
		m_aszReqCode=date.trim();
	}
	public String getReqCode(){
		if(null == m_aszReqCode) return "";
		return m_aszReqCode;
	}

	/**
	* Code Description
	*/
    private String m_aszCodeDesc=null;
	public void setCodeDesc(String date){
		if(date == null){
			m_aszCodeDesc=null;
			return ;
		}
		m_aszCodeDesc=date.trim();
	}
	public String getCodeDesc(){
		if(null == m_aszCodeDesc) return "";
		return m_aszCodeDesc;
	}

	
	/**
	* ShortTerm
	*/
    private String m_aszShortTerm=null;
	public void setShortTerm(String date){
		if(date == null){
			m_aszShortTerm=null;
			return ;
		}
		m_aszShortTerm=date.trim();
	}
	public String getShortTerm(){
		if(null == m_aszShortTerm) return "";
		return m_aszShortTerm;
	}

	/**
	* City
	*/
    private String m_aszCity=null;
	public void setCity(String date){
		if(date == null){
			m_aszCity=null;
			return ;
		}
		m_aszCity=date.trim();
	}
	public String getCity(){
		if(null == m_aszCity) return "";
		return m_aszCity;
	}

	/**
	* State
	*/
    private String m_aszState=null;
	public void setState(String date){
		if(date == null){
			m_aszState=null;
			return ;
		}
		m_aszState=date.trim();
	}
	public String getState(){
		if(null == m_aszState) return "";
		return m_aszState;
	}

	/**
	* Other Province
	*/
    private String m_aszOthrProv=null;
	public void setOthrProv(String date){
		if(date == null){
			m_aszOthrProv=null;
			return ;
		}
		m_aszOthrProv=date.trim();
	}
	public String getOthrProv(){
		if(null == m_aszOthrProv) return "";
		return m_aszOthrProv;
	}
	
	/**
	** faith tid type LONG()
	**/
	private int m_iFaithTID=-1;
	public void setFaithTID(int number){
		m_iFaithTID = number;
	}
	public void setFaithTID(String number){
		m_iFaithTID = convertToInt(number);
		return;
	}
	public int getFaithTID(){
		return m_iFaithTID;
	}


	/**
	** is this site NOT in the US?
	**/
	private boolean b_notUS=false;

	public void setNotUS(String value){
		if(null == value){
			b_notUS = false;
			return;
		}
		if(value.equalsIgnoreCase("true")){
			b_notUS=true;
		}else{
			b_notUS=false;
		}
		return;
	}
	public void setNotUS(Boolean value){
		b_notUS=value.booleanValue();
		return;
	}
	public void setNotUS(boolean value){
		b_notUS=value;
		return;
	}
	public boolean getNotUS(){
		return b_notUS;
	}

	/**
	** is this a search for ONLY the Association? 
	**/
	private boolean b_assocOnly=false;

	public void setAssocOnly(String value){
		if(null == value){
			b_assocOnly = false;
			return;
		}
		if(value.equalsIgnoreCase("true")){
			b_assocOnly=true;
		}else{
			b_assocOnly=false;
		}
		return;
	}
	public void setAssocOnly(Boolean value){
		b_assocOnly=value.booleanValue();
		return;
	}
	public void setAssocOnly(boolean value){
		b_assocOnly=value;
		return;
	}
	public boolean getAssocOnly(){
		return b_assocOnly;
	}

	/**
	** is this a search for ONLY the Association? default should be TRUE
	**/
	/*
	private boolean b_assocOnly=true;

	public void setAssocOnly(String value){
		if(null == value){
			b_assocOnly = true;
			return;
		}
		if(value.equalsIgnoreCase("false")){
			b_assocOnly=false;
		}else{
			b_assocOnly=true;
		}
		return;
	}
	public void setAssocOnly(Boolean value){
		b_assocOnly=value.booleanValue();
		return;
	}
	public void setAssocOnly(boolean value){
		b_assocOnly=value;
		return;
	}
	public boolean getAssocOnly(){
		return b_assocOnly;
	}
	*/

	/**
	** for portals - is location at HeadQuarters, or Off-Site?
	**/
	private String m_aszOPPHQorOffSite=null;

	public void setOPPHQorOffSite(String value){
		if(null == value){
			m_aszOPPHQorOffSite = null;
			return;
		}
		m_aszOPPHQorOffSite = value.trim();
	}
	public String getOPPHQorOffSite(){
		if(m_aszOPPHQorOffSite == null) return "";
		return m_aszOPPHQorOffSite;
	}
	
	/**
	* Denominational Affiliation
	*/
    private String m_aszDenomAffil=null;
	public void setDenomAffil(String date){
		if(date == null){
			m_aszDenomAffil=null;
			return ;
		}
		m_aszDenomAffil=date.trim();
	}
	public String getDenomAffil(){
		if(null == m_aszDenomAffil) return "";
		return m_aszDenomAffil;
	}
	
	/**
	** Denominational Affiliation tid type LONG()
	**/
	private int m_iDenomAffilTID=-1;
	public void setDenomAffilTID(int number){
		m_iDenomAffilTID = number;
	}
	public void setDenomAffilTID(String number){
		m_iDenomAffilTID = convertToInt(number);
		return;
	}
	public int getDenomAffilTID(){
		return m_iDenomAffilTID;
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
	* Partner
	*/
    private String m_aszPartner=null;
	public void setPartner(String date){
		if(date == null){
			m_aszPartner=null;
			return ;
		}
		m_aszPartner=date.trim();
	}
	public String getPartner(){
		if(null == m_aszPartner) return "";
		return m_aszPartner;
	}
	
	/**
	* Partner2
	*/
    private String m_aszPartner2=null;
	public void setPartner2(String date){
		if(date == null){
			m_aszPartner2=null;
			return ;
		}
		m_aszPartner2=date.trim();
	}
	public String getPartner2(){
		if(null == m_aszPartner2) return "";
		return m_aszPartner2;
	}

	/**
	* Partner3
	*/
    private String m_aszPartner3=null;
	public void setPartner3(String date){
		if(date == null){
			m_aszPartner3=null;
			return ;
		}
		m_aszPartner3=date.trim();
	}
	public String getPartner3(){
		if(null == m_aszPartner3) return "";
		return m_aszPartner3;
	}

	/**
	* Partner4
	*/
    private String m_aszPartner4=null;
	public void setPartner4(String date){
		if(date == null){
			m_aszPartner4=null;
			return ;
		}
		m_aszPartner4=date.trim();
	}
	public String getPartner4(){
		if(null == m_aszPartner4) return "";
		return m_aszPartner4;
	}	

	/**
	* Partner5
	*/
    private String m_aszPartner5=null;
	public void setPartner5(String date){
		if(date == null){
			m_aszPartner5=null;
			return ;
		}
		m_aszPartner5=date.trim();
	}
	public String getPartner5(){
		if(null == m_aszPartner5) return "";
		return m_aszPartner5;
	}	
	

	/**
	** generic Org Affil Taxonomy search TIDS
	**/
	private String m_aszOrgAffilTIDs=null;
	public void setOrgAffilTIDs(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOrgAffilTIDs = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOrgAffilTIDs = aszTemp;
			return;
		}
		m_aszOrgAffilTIDs = aszTemp.substring(0,iLen);
	}
	public String getOrgAffilTIDs(){
		if(m_aszOrgAffilTIDs == null) return "";
		return m_aszOrgAffilTIDs;
	}
	/**
	** generic Org Affil Taxonomy search words
	**/
	private String m_aszOrgAffils=null;
	public void setOrgAffils(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOrgAffils = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOrgAffils = aszTemp;
			return;
		}
		m_aszOrgAffils = aszTemp.substring(0,iLen);
	}
	public String getOrgAffils(){
		if(m_aszOrgAffils == null) return "";
		return m_aszOrgAffils;
	}
	/**
	** Org Affil 1 tid type LONG()
	**/
	private int m_iOrgAffil1TID=-1;
	public void setOrgAffil1TID(int number){
		m_iOrgAffil1TID = number;
	}
	public void setOrgAffil1TID(String number){
		m_iOrgAffil1TID = convertToInt(number);
		return;
	}
	public int getOrgAffil1TID(){
		return m_iOrgAffil1TID;
	}

	/**
	** Org Affil 2 tid type LONG()
	**/
	private int m_iOrgAffil2TID=-1;
	public void setOrgAffil2TID(int number){
		m_iOrgAffil2TID = number;
	}
	public void setOrgAffil2TID(String number){
		m_iOrgAffil2TID = convertToInt(number);
		return;
	}
	public int getOrgAffil2TID(){
		return m_iOrgAffil2TID;
	}

	/**
	** Org Affil 3 tid type LONG()
	**/
	private int m_iOrgAffil3TID=-1;
	public void setOrgAffil3TID(int number){
		m_iOrgAffil3TID = number;
	}
	public void setOrgAffil3TID(String number){
		m_iOrgAffil3TID = convertToInt(number);
		return;
	}
	public int getOrgAffil3TID(){
		return m_iOrgAffil3TID;
	}

	/**
	** Org Affil 4 tid type LONG()
	**/
	private int m_iOrgAffil4TID=-1;
	public void setOrgAffil4TID(int number){
		m_iOrgAffil4TID = number;
	}
	public void setOrgAffil4TID(String number){
		m_iOrgAffil4TID = convertToInt(number);
		return;
	}
	public int getOrgAffil4TID(){
		return m_iOrgAffil4TID;
	}

	/**
	** Org Affil 5 tid type LONG()
	**/
	private int m_iOrgAffil5TID=-1;
	public void setOrgAffil5TID(int number){
		m_iOrgAffil5TID = number;
	}
	public void setOrgAffil5TID(String number){
		m_iOrgAffil5TID = convertToInt(number);
		return;
	}
	public int getOrgAffil5TID(){
		return m_iOrgAffil5TID;
	}
	
	/**
	* Organization Number of Volunteers
	*/
    private String m_aszNumVolOrg=null;
	public void setNumVolOrg(String date){
		if(date == null){
			m_aszNumVolOrg=null;
			return ;
		}
		m_aszNumVolOrg=date.trim();
	}
	public String getNumVolOrg(){
		if(null == m_aszNumVolOrg) return "";
		return m_aszNumVolOrg;
	}	

	/**
	* Organization - Number Served
	*/
    private String m_aszNumServed=null;
	public void setNumServed(String date){
		if(date == null){
			m_aszNumServed=null;
			return ;
		}
		m_aszNumServed=date.trim();
	}
	public String getNumServed(){
		if(null == m_aszNumServed) return "";
		return m_aszNumServed;
	}
	
	/**
	* Formal Training
	*/
    private String m_aszFormalTrain=null;
	public void setFormalTrain(String date){
		if(date == null){
			m_aszFormalTrain=null;
			return ;
		}
		m_aszFormalTrain=date.trim();
	}
	public String getFormalTrain(){
		if(null == m_aszFormalTrain) return "";
		return m_aszFormalTrain;
	}

	/**
	* Organizational Statement of Faith
	*/
    private String m_aszOrgStmtFaith=null;
	public void setOrgStmtFaith(String date){
		if(date == null){
			m_aszOrgStmtFaith=null;
			return ;
		}
		m_aszOrgStmtFaith=date.trim();
	}
	public String getOrgStmtFaith(){
		if(null == m_aszOrgStmtFaith) return "";
		return m_aszOrgStmtFaith;
	}

	
	/**
	* local affiliation for org (1st case - Boston)
	*/
    private String m_aszLocalAffil=null;
	public void setLocalAffil(String date){
		if(date == null){
			m_aszLocalAffil=null;
			return ;
		}
		m_aszLocalAffil=date.trim();
	}
	public String getLocalAffil(){
		if(null == m_aszLocalAffil) return "";
		return m_aszLocalAffil;
	}
	
	/**
	* OrgName
	*/
    private String m_aszOrgName=null;
	public void setOrgName(String date){
		if(date == null){
			m_aszOrgName=null;
			return ;
		}
		m_aszOrgName=date.trim();
	}
	public String getOrgName(){
		if(null == m_aszOrgName) return "";
		return m_aszOrgName;
	}

	/**
	* OrgNumber
	*/
	private int m_iOrgNumber=0;
	public void setOrgNumber(int type){
		m_iOrgNumber=type;
	}
	public void setOrgNumber(String number){
		m_iOrgNumber = convertToInt(number);
	}
	public int getOrgNumber(){
		return m_iOrgNumber;
	}

	/**
	* Region
	*/
    private String m_aszRegion=null;
	public void setRegion(String date){
		if(date == null){
			m_aszRegion=null;
			return ;
		}
		m_aszRegion=date.trim();
	}
	public String getRegion(){
		if(null == m_aszRegion) return "";
		return m_aszRegion;
	}

	/**
	** Region type LONG()
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

	/**
	* Country
	*/
    private String m_aszCountry=null;
	public void setCountry(String date){
		if(date == null){
			m_aszCountry=null;
			return ;
		}
		m_aszCountry=date.trim();
	}
	public String getCountry(){
		if(null == m_aszCountry) return "";
		return m_aszCountry;
	}

	/**
	* NotCountry
	*/
    private String m_aszNotCountry=null;
	public void setNotCountry(String value){
		if(value == null){
			m_aszNotCountry=null;
			return ;
		}
		m_aszNotCountry=value.trim();
	}
	public String getNotCountry(){
		if(null == m_aszNotCountry) return "";
		return m_aszNotCountry;
	}

	/**
	* Input Longitude 1 - either input by iphone/user/etc, or calculated given the postal code (or whatever else maps to it)
	*/
    private String m_aszInputLongitude1=null;
	public void setInputLongitude1(String date){
		if(date == null){
			m_aszInputLongitude1=null;
			return ;
		}
		m_aszInputLongitude1=date.trim();
	}
	public String getInputLongitude1(){
		if(null == m_aszInputLongitude1) return "";
		return m_aszInputLongitude1;
	}

	/**
	* Input Latitude 1 - either input by iphone/user/etc, or calculated given the postal code (or whatever else maps to it)
	*/
    private String m_aszInputLatitude1=null;
	public void setInputLatitude1(String date){
		if(date == null){
			m_aszInputLatitude1=null;
			return ;
		}
		m_aszInputLatitude1=date.trim();
	}
	public String getInputLatitude1(){
		if(null == m_aszInputLatitude1) return "";
		return m_aszInputLatitude1;
	}

	
	

	/**
	* Input NOT Longitude 1 - either input by iphone/user/etc, or calculated given the postal code (or whatever else maps to it)
	*/
    private String m_aszNotLclSearchLongitude1=null;
	public void setNotLclSearchLongitude1(String date){
		if(date == null){
			m_aszNotLclSearchLongitude1=null;
			return ;
		}
		m_aszNotLclSearchLongitude1=date.trim();
	}
	public String getNotLclSearchLongitude1(){
		if(null == m_aszNotLclSearchLongitude1) return "";
		return m_aszNotLclSearchLongitude1;
	}

	/**
	* Input NOT Latitude 1 - either input by iphone/user/etc, or calculated given the postal code (or whatever else maps to it)
	*/
    private String m_aszNotLclSearchLatitude1=null;
	public void setNotLclSearchLatitude1(String date){
		if(date == null){
			m_aszNotLclSearchLatitude1=null;
			return ;
		}
		m_aszNotLclSearchLatitude1=date.trim();
	}
	public String getNotLclSearchLatitude1(){
		if(null == m_aszNotLclSearchLatitude1) return "";
		return m_aszNotLclSearchLatitude1;
	}


	/**
	* Distance
	*/
    private String m_aszDistance=null;
	public void setDistance(String input){
		if(input == null){
			m_aszDistance=null;
			return ;
		}
		m_aszDistance=input.trim();
	}
	public String getDistance(){
		if(null == m_aszDistance) return "";
		return m_aszDistance;
	}

	/**
	* Distance searched
	*/
    private boolean m_bDistanceSearched=false;
	public void setDistanceSearched(boolean input){
		if(input = true){
			m_bDistanceSearched=true;
			return ;
		}
		m_bDistanceSearched=m_bDistanceSearched;
	}
	public boolean getDistanceSearched(){
		return m_bDistanceSearched;
	}
	
	
	

	/**
	* search Longitude 1
	*/
    private String m_aszSearchLongitude1=null;
	public void setSearchLongitude1(String date){
		if(date == null){
			m_aszSearchLongitude1=null;
			return ;
		}
		m_aszSearchLongitude1=date.trim();
	}
	public String getSearchLongitude1(){
		if(null == m_aszSearchLongitude1) return "";
		return m_aszSearchLongitude1;
	}

	/**
	* search Latitude 1
	*/
    private String m_aszSearchLatitude1=null;
	public void setSearchLatitude1(String date){
		if(date == null){
			m_aszSearchLatitude1=null;
			return ;
		}
		m_aszSearchLatitude1=date.trim();
	}
	public String getSearchLatitude1(){
		if(null == m_aszSearchLatitude1) return "";
		return m_aszSearchLatitude1;
	}

	/**
	* search Latitude 2
	*/
    private String m_aszSearchLatitude2=null;
	public void setSearchLatitude2(String date){
		if(date == null){
			m_aszSearchLatitude2=null;
			return ;
		}
		m_aszSearchLatitude2=date.trim();
	}
	public String getSearchLatitude2(){
		if(null == m_aszSearchLatitude2) return "";
		return m_aszSearchLatitude2;
	}
	/**
	* search Longitude 2
	*/
    private String m_aszSearchLongitude2=null;
	public void setSearchLongitude2(String date){
		if(date == null){
			m_aszSearchLongitude2=null;
			return ;
		}
		m_aszSearchLongitude2=date.trim();
	}
	public String getSearchLongitude2(){
		if(null == m_aszSearchLongitude2) return "";
		return m_aszSearchLongitude2;
	}


	/**
	* Month
	*/
    private int m_iMonth=0;
	public void setMonth(String number){
		m_iMonth = convertToInt(number);
	}
	public void setMonth(int id){
		m_iMonth=id;
	}
	public int getMonth(){
		return m_iMonth;
	}

	/**
	* Day
	*/
    private int m_iDay=0;
	public void setDay(String number){
		m_iDay = convertToInt(number);
	}
	public void setDay(int id){
		m_iDay=id;
	}
	public int getDay(){
		return m_iDay;
	}

	/**
	* Year
	*/
    private int m_iYear=0;
	public void setYear(String number){
		m_iYear = convertToInt(number);
	}
	public void setYear(int id){
		m_iYear=id;
	}
	public int getYear(){
		return m_iYear;
	}

	/**
	* Julian Date
	*/
    private int m_iJulianDate=0;
	public void setJulianDate(String number){
		m_iJulianDate = convertToInt(number);
	}
	public void setJulianDate(int id){
		m_iJulianDate=id;
	}
	public int getJulianDate(){
		return m_iJulianDate;
	}
	
	/**
	 ** serviceareas type String: comma delimited string of tids
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
	 ** skilltypes type String: comma delimited string of tids
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
	
	/**
	 ** causetopics type String: comma delimited string of tids
	 */
	private String m_aszCauseTopics=null;
	public void setCauseTopics(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCauseTopics = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCauseTopics = aszTemp;
			return;
		}
		m_aszCauseTopics = aszTemp.substring(0,iLen);
	}
	public String getCauseTopics(){
		if(m_aszCauseTopics == null) return "";
		return m_aszCauseTopics;
	}

	//	START short-term missions field searches - opportunity
	//	START short-term missions field searches - opportunity
	//	START short-term missions field searches - opportunity

	/**
	* short-term missions field - length of trip - 2006-12-4 - ali
	*/
    private String m_aszDuration=null;
	public void setDuration(String date){
		if(date == null){
			m_aszDuration=null;
			return ;
		}
		m_aszDuration=date.trim();
	}
	public String getDuration(){
		if(null == m_aszDuration) return "";
		return m_aszDuration;
	}

	/**
	* short-term missions field - minimum group size - 2007-01-11
	*/
	private int m_iMinSize=0;
	public void setMinSize(int type){
		m_iMinSize=type;
	}
	public void setMinSize(String number){
		m_iMinSize = convertToInt(number);
	}
	public int getMinSize(){
		return m_iMinSize;
	}

	/**
	* short-term missions field - maximum group size - 2007-01-11
	*/
	private int m_iMaxSize=0;
	public void setMaxSize(int type){
		m_iMaxSize=type;
	}
	public void setMaxSize(String number){
		m_iMaxSize = convertToInt(number);
	}
	public int getMaxSize(){
		return m_iMaxSize;
	}

	

	//==== Need To Remove The Foolowing Methods 

	/**
	* Status Number
	*/
    private int m_iStatus=0;
	public void setStatusNumber(String number){
		m_iStatus = convertToInt(number);
	}
	public void setStatusNumber(int id){
		m_iStatus=id;
	}
	public int getStatusNumber(){
		return m_iStatus;
	}


	/**
	* system internal user Site Number
	*/
	private int m_iUserSiteNumber=-1;
	public void setUserSiteNumber(String number){
		m_iUserSiteNumber = convertToInt(number);
	}
	public void setUserSiteNumber(int number){
		m_iUserSiteNumber=number;
	}
	public int getUserSiteNumber(){
		return m_iUserSiteNumber;
	}


	/**
	* system internal Person ID
	*/
	private int m_iPersonNumber=-1;
	public void setPersonNumber(String number){
		m_iPersonNumber = convertToInt(number);
	}
	public void setPersonNumber(int number){
		m_iPersonNumber=number;
	}
	public int getPersonNumber(){
		return m_iPersonNumber;
	}

	/**
	* system internal user ID
	*/
	private int m_iUid=-1;
	public void setUID(String number){
		m_iUid = convertToInt(number);
	}
	public void setUID(int number){
		m_iUid=number;
	}
	public int getUID(){
		return m_iUid;
	}

	/**
	* system internal node ID
	*/
	private int m_iNid=-1;
	public void setNID(String number){
		m_iNid = convertToInt(number);
	}
	public void setNID(int number){
		m_iNid=number;
	}
	public int getNID(){
		return m_iNid;
	}

	/**
	* system internal OPP node ID
	*/
	private int m_iOPPNid=-1;
	public void setOPPNID(String number){
		m_iOPPNid = convertToInt(number);
	}
	public void setOPPNID(int number){
		m_iOPPNid=number;
	}
	public int getOPPNID(){
		return m_iOPPNid;
	}

	/**
	* system internal org node ID
	*/
	private int m_iORGNid=-1;
	public void setORGNID(String number){
		m_iORGNid = convertToInt(number);
	}
	public void setORGNID(int number){
		m_iORGNid=number;
	}
	public int getORGNID(){
		return m_iORGNid;
	}

	//=== START Opportunity Advance Search Options ==>
	//=== START Opportunity Advance Search Options ==>
	//=== START Opportunity Advance Search Options ==>

	/**
	* Opportunity Volunteer Type
	* values of : local, virtual, job, internship
	*/
    private String m_aszOppVolType=null;
	public void setOppVolType(String date){
		if(date == null){
			 m_aszOppVolType=null;
			return ;
		}
		 m_aszOppVolType=date.trim();
	}
	public String getOppVolType(){
		if(null ==  m_aszOppVolType) return "";
		return  m_aszOppVolType;
	}


	/**
	** Position type LONG()
	**/
	private int m_iOPPPositionTypeTID=-1;
	public void setOPPPositionTypeTID(int number){
		m_iOPPPositionTypeTID = number;
	}
	public void setOPPPositionTypeTID(String number){
		m_iOPPPositionTypeTID = convertToInt(number);
		return;
	}
	public int getOPPPositionTypeTID(){
		return m_iOPPPositionTypeTID;
	}

	
	
	/*
	 * multi-select Looking For
	 */
	private int[] a_iUSPLookingForArray=null;
	public void setUSPLookingForArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPLookingForArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPLookingForArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPLookingForArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPLookingForArray(){
		if(a_iUSPLookingForArray == null) {
			a_iUSPLookingForArray=new int[0];
			return a_iUSPLookingForArray;
		}
		return a_iUSPLookingForArray;
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
	** SearchedFeeds
	**/
	private int m_iSearchedFeeds=0;
	public void setSearchedFeeds(int number){
		m_iSearchedFeeds = number;
	}
	public void setSearchedFeeds(String number){
		m_iSearchedFeeds = convertToInt(number);
		return;
	}
	public int getSearchedFeeds(){
		return m_iSearchedFeeds;
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

	/**
	** NOT LOCAL City taxonomy
	**/
	private int m_iNotLclCityTID=-1;
	public void setNotLclCityTID(int number){
		m_iNotLclCityTID = number;
	}
	public void setNotLclCityTID(String number){
		m_iNotLclCityTID = convertToInt(number);
		return;
	}
	public int getNotLclCityTID(){
		return m_iNotLclCityTID;
	}


	/**
	** NOT LOCALCountry taxonomy
	**/
	private int m_iNotLclCntryTID=-1;
	public void setNotLclCntryTID(int number){
		m_iNotLclCntryTID = number;
	}
	public void setNotLclCntryTID(String number){
		m_iNotLclCntryTID = convertToInt(number);
		return;
	}
	public int getNotLclCntryTID(){
		return m_iNotLclCntryTID;
	}

	
	/**
	** filtering query (like facets) search
	**/
	private String m_aszFilterQuery=null;
	public void setFilterQuery(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszFilterQuery = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszFilterQuery = aszTemp;
			return;
		}
		m_aszFilterQuery = aszTemp.substring(0,iLen);
	}
	public String getFilterQuery(){
		if(m_aszFilterQuery == null) return "";
		return m_aszFilterQuery;
	}
	
	/**
	** filtering query (like facets) search ARRAY
	**/
	private String[] a_aszFilterQueryArray=null;
	public void setFilterQueryArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszFilterQueryArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszFilterQueryArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszFilterQueryArray = a_aszTemp;
			return;
		}
	}
	public String[] getFilterQueryArray(){
		if(a_aszFilterQueryArray == null) {
			a_aszFilterQueryArray=new String[0];
			return a_aszFilterQueryArray;
		}
		return a_aszFilterQueryArray;
	}
	
	/**
	** Looking For TIDs search
	**/
	private String m_aszLookingForTIDs=null;
	public void setLookingForTIDs(String value){
		int iLen=150;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszLookingForTIDs = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszLookingForTIDs = aszTemp;
			return;
		}
		m_aszLookingForTIDs = aszTemp.substring(0,iLen);
	}
	public String getLookingForTIDs(){
		if(m_aszLookingForTIDs == null) return "";
		return m_aszLookingForTIDs;
	}
	
	/**
	** generic Taxonomy search
	**/
	private String m_aszTaxonomyTIDs=null;
	public void setTaxonomyTIDs(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszTaxonomyTIDs = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszTaxonomyTIDs = aszTemp;
			return;
		}
		m_aszTaxonomyTIDs = aszTemp.substring(0,iLen);
	}
	public String getTaxonomyTIDs(){
		if(m_aszTaxonomyTIDs == null) return "";
		return m_aszTaxonomyTIDs;
	}
	/**
	** generic ServiceAreas Taxonomy search
	**/
	private String m_aszServiceAreasTIDs=null;
	public void setServiceAreasTIDs(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszServiceAreasTIDs = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszServiceAreasTIDs = aszTemp;
			return;
		}
		m_aszServiceAreasTIDs = aszTemp.substring(0,iLen);
	}
	public String getServiceAreasTIDs(){
		if(m_aszServiceAreasTIDs == null) return "";
		return m_aszServiceAreasTIDs;
	}
	/**
	** generic Skills Taxonomy search
	**/
	private String m_aszSkillsTIDs=null;
	public void setSkillsTIDs(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSkillsTIDs = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSkillsTIDs = aszTemp;
			return;
		}
		m_aszSkillsTIDs = aszTemp.substring(0,iLen);
	}
	public String getSkillsTIDs(){
		if(m_aszSkillsTIDs == null) return "";
		return m_aszSkillsTIDs;
	}


	/**
	** m_aszPreviewSearch
	**/
	private String m_aszPreviewSearch=null;
	public void setPreviewSearch(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPreviewSearch = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPreviewSearch = aszTemp;
			return;
		}
		m_aszPreviewSearch = aszTemp.substring(0,iLen);
	}
	public String getPreviewSearch(){
		if(m_aszPreviewSearch == null) return "";
		return m_aszPreviewSearch;
	}

	/**
	** m_aszPreviewSearchShortened
	**/
	private String m_aszPreviewSearchShortened=null;
	public void setPreviewSearchShortened(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPreviewSearchShortened = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPreviewSearchShortened = aszTemp;
			return;
		}
		m_aszPreviewSearchShortened = aszTemp.substring(0,iLen);
	}
	public String getPreviewSearchShortened(){
		if(m_aszPreviewSearchShortened == null) return "";
		return m_aszPreviewSearchShortened;
	}

	/**
	** m_aszBackgroundColor
	**/
	private String m_aszBackgroundColor=null;
	public void setBackgroundColor(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszBackgroundColor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszBackgroundColor = aszTemp;
			return;
		}
		m_aszBackgroundColor = aszTemp.substring(0,iLen);
	}
	public String getBackgroundColor(){
		if(m_aszBackgroundColor == null) return "";
		return m_aszBackgroundColor;
	}

	/**
	** BorderColor
	**/
	private String m_aszBorderColor=null;
	public void setBorderColor(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszBorderColor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszBorderColor = aszTemp;
			return;
		}
		m_aszBorderColor = aszTemp.substring(0,iLen);
	}
	public String getBorderColor(){
		if(m_aszBorderColor == null) return "";
		return m_aszBorderColor;
	}

	/**
	** LinkTextColor
	**/
	private String m_aszLinkTextColor=null;
	public void setLinkTextColor(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszLinkTextColor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszLinkTextColor = aszTemp;
			return;
		}
		m_aszLinkTextColor = aszTemp.substring(0,iLen);
	}
	public String getLinkTextColor(){
		if(m_aszLinkTextColor == null) return "";
		return m_aszLinkTextColor;
	}

	/**
	** m_aszTextColor
	**/
	private String m_aszTextColor=null;
	public void setTextColor(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszTextColor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszTextColor = aszTemp;
			return;
		}
		m_aszTextColor = aszTemp.substring(0,iLen);
	}
	public String getTextColor(){
		if(m_aszTextColor == null) return "";
		return m_aszTextColor;
	}

	
	//=== END   Opportunity Advance Search Options ==>
	//=== END   Opportunity Advance Search Options ==>
	//=== END   Opportunity Advance Search Options ==>

	//=======================================> Private Variables
    //=======================================> Private Variables
    //=======================================> Private Variables


	/**
	* search SQL value
	*/
	private String m_SearchSQLStatement=null;
	public void setSearchSQLStatement(String key){
		if(null == key){
			m_SearchSQLStatement=null;
			return ;
		}
		m_SearchSQLStatement=key.trim();
	}
	public String getSearchSQLStatement(){
		if(null == m_SearchSQLStatement) return "";
		return m_SearchSQLStatement;
	}



	/**
	* search SQL FROM value
	*/
	private String m_SearchFromSQLStatement=null;
	public void setSearchFromSQLStatement(String key){
		if(null == key){
			m_SearchFromSQLStatement=null;
			return ;
		}
		m_SearchFromSQLStatement=key.trim();
	}
	public String getSearchFromSQLStatement(){
		if(null == m_SearchFromSQLStatement) return "";
		return m_SearchFromSQLStatement;
	}



	/**
	* search SQL WHERE value
	*/
	private String m_SearchWhereSQLStatement=null;
	public void setSearchWhereSQLStatement(String key){
		if(null == key){
			m_SearchWhereSQLStatement=null;
			return ;
		}
		m_SearchWhereSQLStatement=key.trim();
	}
	public String getSearchWhereSQLStatement(){
		if(null == m_SearchWhereSQLStatement) return "";
		return m_SearchWhereSQLStatement;
	}



	/**
	* search SQL value for normal taxonomy, but using the materialized view for the node
	*/
	private String m_SearchSQLStmnt=null;
	public void setSearchSQLStmnt(String key){
		if(null == key){
			m_SearchSQLStmnt=null;
			return ;
		}
		m_SearchSQLStmnt=key.trim();
	}
	public String getSearchSQLStmnt(){
		if(null == m_SearchSQLStmnt) return "";
		return m_SearchSQLStmnt;
	}



	/**
	* search SQL FROM value for normal taxonomy, but using the materialized view for the node
	*/
	private String m_SearchFromSQLStmnt=null;
	public void setSearchFromSQLStmnt(String key){
		if(null == key){
			m_SearchFromSQLStmnt=null;
			return ;
		}
		m_SearchFromSQLStmnt=key.trim();
	}
	public String getSearchFromSQLStmnt(){
		if(null == m_SearchFromSQLStmnt) return "";
		return m_SearchFromSQLStmnt;
	}



	/**
	* search SQL WHERE value for normal taxonomy, but using the materialized view for the node
	*/
	private String m_SearchWhereSQLStmnt=null;
	public void setSearchWhereSQLStmnt(String key){
		if(null == key){
			m_SearchWhereSQLStmnt=null;
			return ;
		}
		m_SearchWhereSQLStmnt=key.trim();
	}
	public String getSearchWhereSQLStmnt(){
		if(null == m_SearchWhereSQLStmnt) return "";
		return m_SearchWhereSQLStmnt;
	}

	/**
	* search SQL value for general taxonomy simulated materialized view
	*/
	private String m_SearchSQLStmntGen=null;
	public void setSearchSQLStmntGen(String key){
		if(null == key){
			m_SearchSQLStmntGen=null;
			return ;
		}
		m_SearchSQLStmntGen=key.trim();
	}
	public String getSearchSQLStmntGen(){
		if(null == m_SearchSQLStmntGen) return "";
		return m_SearchSQLStmntGen;
	}



	/**
	* search SQL FROM value for general taxonomy simulated materialized view
	*/
	private String m_SearchFromSQLStmntGen=null;
	public void setSearchFromSQLStmntGen(String key){
		if(null == key){
			m_SearchFromSQLStmntGen=null;
			return ;
		}
		m_SearchFromSQLStmntGen=key.trim();
	}
	public String getSearchFromSQLStmntGen(){
		if(null == m_SearchFromSQLStmntGen) return "";
		return m_SearchFromSQLStmntGen;
	}



	/**
	* search SQL WHERE value for general taxonomy simulated materialized view
	*/
	private String m_SearchWhereSQLStmntGen=null;
	public void setSearchWhereSQLStmntGen(String key){
		if(null == key){
			m_SearchWhereSQLStmntGen=null;
			return ;
		}
		m_SearchWhereSQLStmntGen=key.trim();
	}
	public String getSearchWhereSQLStmntGen(){
		if(null == m_SearchWhereSQLStmntGen) return "";
		return m_SearchWhereSQLStmntGen;
	}

    private String m_aszCVIntern=null;
	public void setCVIntern(String date){
		if(date == null){
			m_aszCVIntern=null;
			return ;
		}
		m_aszCVIntern=date.trim();
	}
	public String getCVIntern(){
		if(null ==  m_aszCVIntern) return "";
		return  m_aszCVIntern;
	}



}
