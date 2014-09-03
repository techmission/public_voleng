package com.abrecorp.opensource.dataobj;

/**
* Code Generated DataStore Class
* For Table application_codes
*/

import java.io.*;

import com.abrecorp.opensource.base.ABRESimpleBase;

public class AppCodeInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	public static final int GET_BY_DISPLAYID=101;
	public static final int GET_BY_TYPEID=202;
	public static final int GET_BY_SORTID=303;

	/**
	* constructor
	*/
	public AppCodeInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			AppCodeInfoDTO el = (AppCodeInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}

	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table application_codes 
	//===> Start Code Generated Methods For Table application_codes 
	//===> Start Code Generated Methods For Table application_codes 


	/**
	** id_type type LONG() in table application_codes 
	**/
	private int m_iAPCIdType=0;
	public void setAPCIdType(int number){
		m_iAPCIdType = number;
	}
	public void setAPCIdType(String number){
		m_iAPCIdType = convertToInt(number);
		return;
	}
	public int getAPCIdType(){
		return m_iAPCIdType;
	}


	/**
	** id_sub_type type LONG() in table application_codes 
	**/
	private int m_iAPCIdSubType=0;
	public void setAPCIdSubType(int number){
		m_iAPCIdSubType = number;
	}
	public void setAPCIdSubType(String number){
		m_iAPCIdSubType = convertToInt(number);
		return;
	}
	public int getAPCIdSubType(){
		return m_iAPCIdSubType;
	}


	/**
	** id_sort type LONG() in table application_codes 
	**/
	private int m_iAPCIdSort=0;
	public void setAPCIdSort(int number){
		m_iAPCIdSort = number;
	}
	public void setAPCIdSort(String number){
		m_iAPCIdSort = convertToInt(number);
		return;
	}
	public int getAPCIdSort(){
		return m_iAPCIdSort;
	}
	
	/**
	** keycode type CHAR(30) in table application_codes 
	**/
	private String m_aszAPCKeycode=null;
	public void setAPCKeycode(String value){
		int iLen=30;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAPCKeycode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAPCKeycode = aszTemp;
			return;
		}
		m_aszAPCKeycode = aszTemp.substring(0,iLen);
	}
	public String getAPCKeycode(){
		if(m_aszAPCKeycode == null) return "";
		return m_aszAPCKeycode;
	}


	/**
	** display type CHAR(100) in table application_codes 
	**/
	private String m_aszAPCDisplay=null;
	public void setAPCDisplay(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAPCDisplay = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAPCDisplay = aszTemp;
			return;
		}
		m_aszAPCDisplay = aszTemp.substring(0,iLen);
	}
	public String getAPCDisplay(){
		if(m_aszAPCDisplay == null) return "";
		return m_aszAPCDisplay;
	}


	/**
	** description type VARCHAR(255) in table application_codes 
	**/
	private String m_aszAPCDescription=null;
	public void setAPCDescription(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAPCDescription = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAPCDescription = aszTemp;
			return;
		}
		m_aszAPCDescription = aszTemp.substring(0,iLen);
	}
	public String getAPCDescription(){
		if(m_aszAPCDescription == null) return "";
		return m_aszAPCDescription;
	}


	/**
	** functional_area type VARCHAR(255) in table application_codes 
	**/
	private String m_aszAPCFunctionalArea=null;
	public void setAPCFunctionalArea(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAPCFunctionalArea = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAPCFunctionalArea = aszTemp;
			return;
		}
		m_aszAPCFunctionalArea = aszTemp.substring(0,iLen);
	}
	public String getAPCFunctionalArea(){
		if(m_aszAPCFunctionalArea == null) return "";
		return m_aszAPCFunctionalArea;
	}


	//===========> End Code Generated Methods For Table application_codes 
	//===========> End Code Generated Methods For Table application_codes 
	//===========> End Code Generated Methods For Table application_codes 

	/**
	** URLsrc 
	**/
	private String m_aszURLsrc=null;
	public void setURLsrc(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszURLsrc = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszURLsrc = aszTemp;
			return;
		}
		m_aszURLsrc = aszTemp.substring(0,iLen);
	}
	public String getURLsrc(){
		if(m_aszURLsrc == null) return "";
		return m_aszURLsrc;
	}

	/**
	** URLdst 
	**/
	private String m_aszURLdst=null;
	public void setURLdst(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszURLdst = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszURLdst = aszTemp;
			return;
		}
		m_aszURLdst = aszTemp.substring(0,iLen);
	}
	public String getURLdst(){
		if(m_aszURLdst == null) return "";
		return m_aszURLdst;
	}


	/**
	* POSTAL
	*/
    private String m_aszPostal=null;
	public void setPostal(String date){
		if(date == null){
			m_aszPostal=null;
			return ;
		}
		m_aszPostal=date.trim();
	}
	public String getPostal(){
		if(null == m_aszPostal) return "";
		return m_aszPostal;
	}

	/**
	* Latitude
	*/
    private String m_aszLatitude=null;
	public void setLatitude(String str){
		if(str == null){
			m_aszLatitude="";
			return ;
		}
		m_aszLatitude=str.trim();
	}
	public void setLatitude(Double dbl){
		if(dbl == null || dbl == 0.0){
			m_aszLatitude="";
			return ;
		}
		m_aszLatitude=""+dbl;
	}
	public String getLatitude(){
		if(null == m_aszLatitude) return "";
		return m_aszLatitude;
	}

	/**
	* Longitude 
	*/
    private String m_aszLongitude=null;
	public void setLongitude(String str){
		if(str == null){
			m_aszLongitude="";
			return ;
		}
		m_aszLongitude=str.trim();
	}
	public void setLongitude(Double dbl){
		if(dbl == null || dbl == 0.0){
			m_aszLongitude="";
			return ;
		}
		m_aszLongitude=""+dbl;
	}
	public String getLongitude(){
		if(null == m_aszLongitude) return "";
		return m_aszLongitude;
	}
	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table country_stateprovince 
	//===> Start Code Generated Methods For Table country_stateprovince 
	//===> Start Code Generated Methods For Table country_stateprovince 


	/**
	** state_code type CHAR(2) in table country_stateprovince 
	**/
	private String m_aszCSPStateCode=null;
	public void setCSPStateCode(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCSPStateCode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCSPStateCode = aszTemp;
			return;
		}
		m_aszCSPStateCode = aszTemp.substring(0,iLen);
	}
	public String getCSPStateCode(){
		if(m_aszCSPStateCode == null) return "";
		return m_aszCSPStateCode;
	}


	/**
	** state_name type VARCHAR(255) in table country_stateprovince 
	**/
	private String m_aszCSPStateName=null;
	public void setCSPStateName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCSPStateName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCSPStateName = aszTemp;
			return;
		}
		m_aszCSPStateName = aszTemp.substring(0,iLen);
	}
	public String getCSPStateName(){
		if(m_aszCSPStateName == null) return "";
		return m_aszCSPStateName;
	}


	/**
	** country_code type CHAR(3) in table country_stateprovince 
	**/
	private String m_aszCSPCountryCode=null;
	public void setCSPCountryCode(String value){
		int iLen=3;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCSPCountryCode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCSPCountryCode = aszTemp;
			return;
		}
		m_aszCSPCountryCode = aszTemp.substring(0,iLen);
	}
	public String getCSPCountryCode(){
		if(m_aszCSPCountryCode == null) return "";
		return m_aszCSPCountryCode;
	}



	/**
	** region type VARCHAR(80) in table country_stateprovince 
	**/
	private String m_aszCSPRegion=null;
	public void setCSPRegion(String value){
		int iLen=80;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCSPRegion = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCSPRegion = aszTemp;
			return;
		}
		m_aszCSPRegion = aszTemp.substring(0,iLen);
	}
	public String getCSPRegion(){
		if(m_aszCSPRegion == null) return "";
		return m_aszCSPRegion;
	}



	//===========> End Code Generated Methods For Table country_stateprovince 
	//===========> End Code Generated Methods For Table country_stateprovince 
	//===========> End Code Generated Methods For Table country_stateprovince 


	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table country 
	//===> Start Code Generated Methods For Table country 
	//===> Start Code Generated Methods For Table country 


	/**
	** iso type CHAR(2) in table country 
	**/
	private String m_aszCTRIso=null;
	public void setCTRIso(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCTRIso = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCTRIso = aszTemp;
			return;
		}
		m_aszCTRIso = aszTemp.substring(0,iLen);
	}
	public String getCTRIso(){
		if(m_aszCTRIso == null) return "";
		return m_aszCTRIso;
	}


	/**
	** name type VARCHAR(80) in table country 
	**/
	private String m_aszCTRName=null;
	public void setCTRName(String value){
		int iLen=80;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCTRName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCTRName = aszTemp;
			return;
		}
		m_aszCTRName = aszTemp.substring(0,iLen);
	}
	public String getCTRName(){
		if(m_aszCTRName == null) return "";
		return m_aszCTRName;
	}


	/**
	** printable_name type VARCHAR(80) in table country 
	**/
	private String m_aszCTRPrintableName=null;
	public void setCTRPrintableName(String value){
		int iLen=80;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCTRPrintableName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCTRPrintableName = aszTemp;
			return;
		}
		m_aszCTRPrintableName = aszTemp.substring(0,iLen);
	}
	public String getCTRPrintableName(){
		if(m_aszCTRPrintableName == null) return "";
		return m_aszCTRPrintableName;
	}


	/**
	** iso3 type CHAR(3) in table country 
	**/
	private String m_aszCTRIso3=null;
	public void setCTRIso3(String value){
		int iLen=3;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCTRIso3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCTRIso3 = aszTemp;
			return;
		}
		m_aszCTRIso3 = aszTemp.substring(0,iLen);
	}
	public String getCTRIso3(){
		if(m_aszCTRIso3 == null) return "";
		return m_aszCTRIso3;
	}


	/**
	** numcode type SHORT() in table country 
	**/
	private int m_iCTRNumcode=0;
	public void setCTRNumcode(int number){
		m_iCTRNumcode = number;
	}
	public void setCTRNumcode(String number){
		m_iCTRNumcode = convertToInt(number);
		return;
	}
	public int getCTRNumcode(){
		return m_iCTRNumcode;
	}


	/**
	** region type VARCHAR(80) in table country 
	**/
	private String m_aszCTRRegion=null;
	public void setCTRRegion(String value){
		int iLen=80;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCTRRegion = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCTRRegion = aszTemp;
			return;
		}
		m_aszCTRRegion = aszTemp.substring(0,iLen);
	}
	public String getCTRRegion(){
		if(m_aszCTRRegion == null) return "";
		return m_aszCTRRegion;
	}


	//===========> End Code Generated Methods For Table country 
	//===========> End Code Generated Methods For Table country 
	//===========> End Code Generated Methods For Table country 


	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table zip_codes 
	//===> Start Code Generated Methods For Table zip_codes 
	//===> Start Code Generated Methods For Table zip_codes 


	/**
	** zip type VARCHAR(5) in table zip_codes 
	**/
	private String m_aszZIPZip=null;
	public void setZIPZip(String value){
		int iLen=5;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPZip = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPZip = aszTemp;
			return;
		}
		m_aszZIPZip = aszTemp.substring(0,iLen);
	}
	public String getZIPZip(){
		if(m_aszZIPZip == null) return "";
		return m_aszZIPZip;
	}


	/**
	** state type CHAR(2) in table zip_codes 
	**/
	private String m_aszZIPState=null;
	public void setZIPState(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPState = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPState = aszTemp;
			return;
		}
		m_aszZIPState = aszTemp.substring(0,iLen);
	}
	public String getZIPState(){
		if(m_aszZIPState == null) return "";
		return m_aszZIPState;
	}


	/**
	** latitude type VARCHAR(10) in table zip_codes 
	**/
	private String m_aszZIPLatitude=null;
	public void setZIPLatitude(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPLatitude = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPLatitude = aszTemp;
			return;
		}
		m_aszZIPLatitude = aszTemp.substring(0,iLen);
	}
	public String getZIPLatitude(){
		if(m_aszZIPLatitude == null) return "";
		return m_aszZIPLatitude;
	}


	/**
	** longitude type VARCHAR(10) in table zip_codes 
	**/
	private String m_aszZIPLongitude=null;
	public void setZIPLongitude(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPLongitude = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPLongitude = aszTemp;
			return;
		}
		m_aszZIPLongitude = aszTemp.substring(0,iLen);
	}
	public String getZIPLongitude(){
		if(m_aszZIPLongitude == null) return "";
		return m_aszZIPLongitude;
	}


	/**
	** city type VARCHAR(50) in table zip_codes 
	**/
	private String m_aszZIPCity=null;
	public void setZIPCity(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPCity = aszTemp;
			return;
		}
		m_aszZIPCity = aszTemp.substring(0,iLen);
	}
	public String getZIPCity(){
		if(m_aszZIPCity == null) return "";
		return m_aszZIPCity;
	}


	/**
	** full_state type VARCHAR(50) in table zip_codes 
	**/
	private String m_aszZIPFullState=null;
	public void setZIPFullState(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszZIPFullState = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszZIPFullState = aszTemp;
			return;
		}
		m_aszZIPFullState = aszTemp.substring(0,iLen);
	}
	public String getZIPFullState(){
		if(m_aszZIPFullState == null) return "";
		return m_aszZIPFullState;
	}


	//===========> End Code Generated Methods For Table zip_codes 
	//===========> End Code Generated Methods For Table zip_codes 
	//===========> End Code Generated Methods For Table zip_codes 



	//===========> Start Code for Portal management 
	//===========> Start Code for Portal management 
	//===========> Start Code for Portal management 
	//===========> Start Code for Portal management 
	/**
	** Portal 
	**/
	private String m_aszPortal=null;
	public void setPortal(String value){
		int iLen=200;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortal = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortal = aszTemp;
			return;
		}
		m_aszPortal = aszTemp.substring(0,iLen);
	}
	public String getPortal(){
		if(m_aszPortal == null) return "";
		return m_aszPortal;
	}

	/**
	** Portal benner
	**/
	private String m_aszPortalBanner=null;
	public void setPortalBanner(String value){
		int iLen=200;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalBanner = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalBanner = aszTemp;
			return;
		}
		m_aszPortalBanner = aszTemp.substring(0,iLen);
	}
	public String getPortalBanner(){
		if(m_aszPortalBanner == null) return "";
		return m_aszPortalBanner;
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
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceBeginJSPTag(aszTemp);
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceCloseJSPTag(aszTemp);
		m_aszPortalHeader = aszTemp;
	}
	public String getPortalHeader(){
		if(m_aszPortalHeader == null) return "";
		return m_aszPortalHeader;
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
		aszTemp=ABRESimpleBase.replaceBeginJSPTag(aszTemp);
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceCloseJSPTag(aszTemp);
		m_aszPortalHeaderTags = aszTemp;
	}
	public String getPortalHeaderTags(){
		if(m_aszPortalHeaderTags == null) return "";
		return m_aszPortalHeaderTags;
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
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceBeginJSPTag(aszTemp);
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceCloseJSPTag(aszTemp);
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
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceBeginJSPTag(aszTemp);
		aszTemp=com.abrecorp.opensource.base.ABRESimpleBase.replaceCloseJSPTag(aszTemp);
		m_aszPortalCSS = aszTemp;
	}
	public String getPortalCSS(){
		if(m_aszPortalCSS == null) return "";
		return m_aszPortalCSS;
	}


	/**
	** Portal OrgName
	**/
	private String m_aszPortalOrgName=null;
	public void setPortalOrgName(String value){
		int iLen=200;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalOrgName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalOrgName = aszTemp;
			return;
		}
		m_aszPortalOrgName = aszTemp.substring(0,iLen);
	}
	public String getPortalOrgName(){
		if(m_aszPortalOrgName == null) return "";
		return m_aszPortalOrgName;
	}

	/*
	 * array of org nid's removed from "favorited" by this natl assoc (has org affil)
	 */
	private int[] iRemoveFavOrgNids=null;
	public void setRemoveFavORGNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		iRemoveFavOrgNids = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			iRemoveFavOrgNids = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			iRemoveFavOrgNids = a_iTemp;
			return;
		}
	}
	public int[] getRemoveFavORGNIDsArray(){
		if(iRemoveFavOrgNids == null) {
			iRemoveFavOrgNids=new int[0];
			return iRemoveFavOrgNids;
		}
		return iRemoveFavOrgNids;
	}
	/*
	 * array of org nid's added to "favorited" by this natl assoc (has org affil)
	 */
	private int[] iAddFavOrgNids=null;
	public void setAddFavORGNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		iAddFavOrgNids = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			iAddFavOrgNids = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			iAddFavOrgNids = a_iTemp;
			return;
		}
	}
	public int[] getAddFavORGNIDsArray(){
		if(iAddFavOrgNids == null) {
			iAddFavOrgNids=new int[0];
			return iAddFavOrgNids;
		}
		return iAddFavOrgNids;
	}

	/*
	 * array of opp nid's removed from "favorited" by this natl assoc (has org affil)
	 */
	private int[] iRemoveFavOppNids=null;
	public void setRemoveFavOPPNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		iRemoveFavOppNids = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			iRemoveFavOppNids = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			iRemoveFavOppNids = a_iTemp;
			return;
		}
	}
	public int[] getRemoveFavOPPNIDsArray(){
		if(iRemoveFavOppNids == null) {
			iRemoveFavOppNids=new int[0];
			return iRemoveFavOppNids;
		}
		return iRemoveFavOppNids;
	}
	/*
	 * array of opp nid's added to "favorited" by this natl assoc (has org affil)
	 */
	private int[] iAddFavOppNids=null;
	public void setAddFavOPPNIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		iAddFavOppNids = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			iAddFavOppNids = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			iAddFavOppNids = a_iTemp;
			return;
		}
	}
	public int[] getAddFavOPPNIDsArray(){
		if(iAddFavOppNids == null) {
			iAddFavOppNids=new int[0];
			return iAddFavOppNids;
		}
		return iAddFavOppNids;
	}

	/**
	** Portal OrgAffil
	**/
	private String m_aszPortalOrgAffil=null;
	public void setPortalOrgAffil(String value){
		int iLen=200;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPortalOrgAffil = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPortalOrgAffil = aszTemp;
			return;
		}
		m_aszPortalOrgAffil = aszTemp.substring(0,iLen);
	}
	public String getPortalOrgAffil(){
		if(m_aszPortalOrgAffil == null) return "";
		return m_aszPortalOrgAffil;
	}
	/**
	** setPortalOrgAffilTID
	**/
	private int m_iPortalOrgAffilTID=0;
	public void setPortalOrgAffilTID(int number){
		m_iPortalOrgAffilTID = number;
	}
	public void setPortalOrgAffilTID(String number){
		m_iPortalOrgAffilTID = convertToInt(number);
		return;
	}
	public int getPortalOrgAffilTID(){
		return m_iPortalOrgAffilTID;
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


	//===========> End Code for Portal management 
	//===========> End Code for Portal management 
	//===========> End Code for Portal management 

	
	
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
	

}
/* End Of Code Generated DataStore Class AppCodeInfoDTO */
