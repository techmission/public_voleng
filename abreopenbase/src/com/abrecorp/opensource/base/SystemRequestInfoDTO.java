package com.abrecorp.opensource.base;

/**
* System Index Data Object, Request, Config, etc
*/

import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class SystemRequestInfoDTO extends ABRESimpleBase implements Serializable, Cloneable {

	   public final static int GET_SYSTEM_WIDE_LOCKS_BY_DATE=100;

	    /**
	    * constructor
	    */
	    public SystemRequestInfoDTO(){}


	    /**
	    * public clone method
	    */
	    public Object clone(){
	        try{
	        	SystemRequestInfoDTO e1 = (SystemRequestInfoDTO) super.clone();
	            return e1;
	        } catch (CloneNotSupportedException exp){
	            return null;
	        }
	    }







		//**** Start Code Generated Methods Do Not Modify *********************
		//===> Start Code Generated Methods For Table systemconfiginfo 
		//===> Start Code Generated Methods For Table systemconfiginfo 
		//===> Start Code Generated Methods For Table systemconfiginfo 


		/**
		** config_number type LONG() in table systemconfiginfo 
		**/
		private int m_iCONFConfigNumber=0;
		public void setCONFConfigNumber(int number){
			m_iCONFConfigNumber = number;
		}
		public void setCONFConfigNumber(String number){
			m_iCONFConfigNumber = convertToInt(number);
			return;
		}
		public int getCONFConfigNumber(){
			return m_iCONFConfigNumber;
		}


		/**
		** config_code type CHAR(50) in table systemconfiginfo 
		**/
		private String m_aszCONFConfigCode=null;
		public void setCONFConfigCode(String value){
			int iLen=50;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFConfigCode = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFConfigCode = aszTemp;
				return;
			}
			m_aszCONFConfigCode = aszTemp.substring(0,iLen);
		}
		public String getCONFConfigCode(){
			if(m_aszCONFConfigCode == null) return "";
			return m_aszCONFConfigCode;
		}


		/**
		** config_type type VARCHAR(15) in table systemconfiginfo 
		**/
		private String m_aszCONFConfigType=null;
		public void setCONFConfigType(String value){
			int iLen=15;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFConfigType = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFConfigType = aszTemp;
				return;
			}
			m_aszCONFConfigType = aszTemp.substring(0,iLen);
		}
		public String getCONFConfigType(){
			if(m_aszCONFConfigType == null) return "";
			return m_aszCONFConfigType;
		}


		/**
		** active_flag type CHAR(1) in table systemconfiginfo 
		**/
		private String m_aszCONFActiveFlag=null;
		public void setCONFActiveFlag(String value){
			int iLen=1;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFActiveFlag = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFActiveFlag = aszTemp;
				return;
			}
			m_aszCONFActiveFlag = aszTemp.substring(0,iLen);
		}
		public String getCONFActiveFlag(){
			if(m_aszCONFActiveFlag == null) return "";
			return m_aszCONFActiveFlag;
		}


		/**
		** create_dt type DATETIME() in table systemconfiginfo 
		**/
		private java.util.Date m_azdCONFCreateDt=null;
		public void setCONFCreateDt(java.util.Date value){
			if(value == null){
				m_azdCONFCreateDt = null;
				return;
			}
			m_azdCONFCreateDt = value;
		}
		public java.util.Date getCONFCreateDt(){
			if(m_azdCONFCreateDt == null) return null;
			return m_azdCONFCreateDt;
		}


		/**
		** update_dt type DATETIME() in table systemconfiginfo 
		**/
		private java.util.Date m_azdCONFUpdateDt=null;
		public void setCONFUpdateDt(java.util.Date value){
			if(value == null){
				m_azdCONFUpdateDt = null;
				return;
			}
			m_azdCONFUpdateDt = value;
		}
		public java.util.Date getCONFUpdateDt(){
			if(m_azdCONFUpdateDt == null) return null;
			return m_azdCONFUpdateDt;
		}


		/**
		** create_id type LONG() in table systemconfiginfo 
		**/
		private int m_iCONFCreateId=0;
		public void setCONFCreateId(int number){
			m_iCONFCreateId = number;
		}
		public void setCONFCreateId(String number){
			m_iCONFCreateId = convertToInt(number);
			return;
		}
		public int getCONFCreateId(){
			return m_iCONFCreateId;
		}


		/**
		** update_id type LONG() in table systemconfiginfo 
		**/
		private int m_iCONFUpdateId=0;
		public void setCONFUpdateId(int number){
			m_iCONFUpdateId = number;
		}
		public void setCONFUpdateId(String number){
			m_iCONFUpdateId = convertToInt(number);
			return;
		}
		public int getCONFUpdateId(){
			return m_iCONFUpdateId;
		}


		/**
		** interval_num type LONG() in table systemconfiginfo 
		**/
		private int m_iCONFIntervalNum=0;
		public void setCONFIntervalNum(int number){
			m_iCONFIntervalNum = number;
		}
		public void setCONFIntervalNum(String number){
			m_iCONFIntervalNum = convertToInt(number);
			return;
		}
		public int getCONFIntervalNum(){
			return m_iCONFIntervalNum;
		}


		/**
		** interval_type type CHAR(1) in table systemconfiginfo 
		**/
		private String m_aszCONFIntervalType=null;
		public void setCONFIntervalType(String value){
			int iLen=1;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFIntervalType = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFIntervalType = aszTemp;
				return;
			}
			m_aszCONFIntervalType = aszTemp.substring(0,iLen);
		}
		public String getCONFIntervalType(){
			if(m_aszCONFIntervalType == null) return "";
			return m_aszCONFIntervalType;
		}


		/**
		** lastrun_dt type DATETIME() in table systemconfiginfo 
		**/
		private java.util.Date m_azdCONFLastrunDt=null;
		public void setCONFLastrunDt(java.util.Date value){
			if(value == null){
				m_azdCONFLastrunDt = null;
				return;
			}
			m_azdCONFLastrunDt = value;
		}
		public java.util.Date getCONFLastrunDt(){
			if(m_azdCONFLastrunDt == null) return null;
			return m_azdCONFLastrunDt;
		}


		/**
		** run_status type VARCHAR(15) in table systemconfiginfo 
		**/
		private String m_aszCONFRunStatus=null;
		public void setCONFRunStatus(String value){
			int iLen=15;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFRunStatus = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFRunStatus = aszTemp;
				return;
			}
			m_aszCONFRunStatus = aszTemp.substring(0,iLen);
		}
		public String getCONFRunStatus(){
			if(m_aszCONFRunStatus == null) return "";
			return m_aszCONFRunStatus;
		}


		/**
		** class_name type VARCHAR(255) in table systemconfiginfo 
		**/
		private String m_aszCONFClassName=null;
		public void setCONFClassName(String value){
			int iLen=255;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFClassName = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFClassName = aszTemp;
				return;
			}
			m_aszCONFClassName = aszTemp.substring(0,iLen);
		}
		public String getCONFClassName(){
			if(m_aszCONFClassName == null) return "";
			return m_aszCONFClassName;
		}


		/**
		** location type VARCHAR(255) in table systemconfiginfo 
		**/
		private String m_aszCONFLocation=null;
		public void setCONFLocation(String value){
			int iLen=255;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFLocation = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFLocation = aszTemp;
				return;
			}
			m_aszCONFLocation = aszTemp.substring(0,iLen);
		}
		public String getCONFLocation(){
			if(m_aszCONFLocation == null) return "";
			return m_aszCONFLocation;
		}


		/**
		** value type VARCHAR(255) in table systemconfiginfo 
		**/
		private String m_aszCONFValue=null;
		public void setCONFValue(String value){
			int iLen=255;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszCONFValue = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszCONFValue = aszTemp;
				return;
			}
			m_aszCONFValue = aszTemp.substring(0,iLen);
		}
		public String getCONFValue(){
			if(m_aszCONFValue == null) return "";
			return m_aszCONFValue;
		}


		/**
		** tokens type TEXT() in table systemconfiginfo 
		**/
		private String m_aszCONFTokens=null;

		public void setCONFTokens(String value){
			if(null == value){
				m_aszCONFTokens = null;
				return;
			}
			m_aszCONFTokens = value.trim();
		}
		public String getCONFTokens(){
			if(m_aszCONFTokens == null) return "";
			return m_aszCONFTokens;
		}


		//===========> End Code Generated Methods For Table systemconfiginfo 
		//===========> End Code Generated Methods For Table systemconfiginfo 
		//===========> End Code Generated Methods For Table systemconfiginfo 






		//**** Start Code Generated Methods Do Not Modify *********************
		//===> Start Code Generated Methods For Table lockcodekey 
		//===> Start Code Generated Methods For Table lockcodekey 
		//===> Start Code Generated Methods For Table lockcodekey 


		/**
		** lock_number type LONG() in table lockcodekey 
		**/
		private int m_iLOKLockNumber=0;
		public void setLOKLockNumber(int number){
			m_iLOKLockNumber = number;
		}
		public void setLOKLockNumber(String number){
			m_iLOKLockNumber = convertToInt(number);
			return;
		}
		public int getLOKLockNumber(){
			return m_iLOKLockNumber;
		}


		/**
		** codekey type VARCHAR(255) in table lockcodekey 
		**/
		private String m_aszLOKCodekey=null;
		public void setLOKCodekey(String value){
			int iLen=255;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszLOKCodekey = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszLOKCodekey = aszTemp;
				return;
			}
			m_aszLOKCodekey = aszTemp.substring(0,iLen);
		}
		public String getLOKCodekey(){
			if(m_aszLOKCodekey == null) return "";
			return m_aszLOKCodekey;
		}


		/**
		** create_dt type DATETIME() in table lockcodekey 
		**/
		private java.util.Date m_azdLOKCreateDt=null;
		public void setLOKCreateDt(java.util.Date value){
			if(value == null){
				m_azdLOKCreateDt = null;
				return;
			}
			m_azdLOKCreateDt = value;
		}
		public java.util.Date getLOKCreateDt(){
			if(m_azdLOKCreateDt == null) return null;
			return m_azdLOKCreateDt;
		}


		/**
		** status type LONG() in table lockcodekey 
		**/
		private int m_iLOKStatus=0;
		public void setLOKStatus(int number){
			m_iLOKStatus = number;
		}
		public void setLOKStatus(String number){
			m_iLOKStatus = convertToInt(number);
			return;
		}
		public int getLOKStatus(){
			return m_iLOKStatus;
		}


		/**
		** processid type VARCHAR(255) in table lockcodekey 
		**/
		private String m_aszLOKProcessid=null;
		public void setLOKProcessid(String value){
			int iLen=255;
			String aszTemp = value;
			if(aszTemp == null) aszTemp="";
			aszTemp = aszTemp.trim();
			if(aszTemp.length() < 1){
				m_aszLOKProcessid = null;
				return;
			}
			if(aszTemp.length() < iLen + 1){
				m_aszLOKProcessid = aszTemp;
				return;
			}
			m_aszLOKProcessid = aszTemp.substring(0,iLen);
		}
		public String getLOKProcessid(){
			if(m_aszLOKProcessid == null) return "";
			return m_aszLOKProcessid;
		}


		//===========> End Code Generated Methods For Table lockcodekey 
		//===========> End Code Generated Methods For Table lockcodekey 
		//===========> End Code Generated Methods For Table lockcodekey 




}
