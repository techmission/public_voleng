package com.abrecorp.opensource.dataobj;

/**
* Code Generated DataStore Class
* For Table userprofileinfo
*/

import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;


public class PersonInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	//public static final int AUTH_ADMIN=3;
	public static final String AUTH_ADMIN="siteadmin";
	public static final String AUTH_ADMIN_PWD="masterpassword";

	public static final int LOADBY_USERNAME=101;
	public static final int LOADBY_PERSONNUMBER=102;
	public static final int LOADBY_EMAILADDR1=103;
	public static final int LOADBY_UID=110;
	public static final int LOADBY_PRIMARY_UID=111;
	public static final int SIMPLE_LOADBY_UID=112;
	public static final int LOADBY_EMAIL=115;
	public static final int LOADBY_UIDEMAIL=120;
	public static final int LOADBY_UID_GIGYA=121;
	public static final int LOADBY_EMAIL_AS_CONTACT=125;

	public static final int LOADBY_FACEBOOK_UID=130;
	public static final int LOADBY_LINKEDIN_ID = 131;
	
	public static final int GET_UID_LOADBY_EMAILPWD=150;

	public static final int LOADBY_AUTH_ADMIN_PWD=177;

	public static final int REGISTER_USER=200;
	public static final int CREATE_USER_PT1=201;
	public static final int CREATE_USER_PT2=202;
	public static final int CREATE_USER_CVINTERNAPPLIC=204;
	public static final int CREATE_USER_ORG_CONTACT=203;
	public static final int CREATE_USER_FB=210;
	public static final int CREATE_USER_FBAPP=211;
	public static final int MIGRATE_DRUPAL_USER=220;

	public static final int COOKIE_USER=300;
	public static final int GIGYA_USER=301;

	public static final int MAIN_DOMAIN=400;
	public static final int TEST_DOMAIN=401;
	public static final int PARTNER1_DOMAIN=402;

	public static final int USER_LOGINOK=919;
	public static final int USER_LOGIN_PARTIAL=920;
	

	/**
	* constructor
	*/
	public PersonInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			PersonInfoDTO el = (PersonInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}

	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===<

	/**
	** User Is Logged into system
	**/
	private int m_iUserIsLoggedIntoSystem=0;
	public void setUserIsLoggedIntoSystem(int number) {
		m_iUserIsLoggedIntoSystem = number;
	}
	public void setUserIsLoggedIntoSystem(String number){
		m_iUserIsLoggedIntoSystem = convertToInt(number);
		return;
	}
	public int getUserIsLoggedIntoSystem(){
		return m_iUserIsLoggedIntoSystem;
	}


	/**
	** DRUPAL User Id (uid)
	**/
	private int m_iUID=0;
	public void setUserUID(int number){
		m_iUID = number;
	}
	public void setUserUID(String number){
		m_iUID = convertToInt(number);
		return;
	}
	public int getUserUID(){
		return m_iUID;
	}
	private String m_aszUID=null;
	public void setUserUIDString(String number){
		m_aszUID = number;
		return;
	}
	public String getUserUIDString(){
		return m_aszUID;
	}

	/**
	** Rails User profile Id (uid)
	**/
	private int m_iRailsID=0;
	public void setUserRailsID(int number){
		m_iRailsID = number;
	}
	public void setUserRailsID(String number){
		m_iRailsID = convertToInt(number);
		return;
	}
	public int getUserRailsID(){
		return m_iRailsID;
	}
	private String m_aszRailsID=null;
	public void setUserRailsIDString(String number){
		m_aszRailsID = number;
		return;
	}
	public String getUserRailsIDString(){
		return m_aszRailsID;
	}

	/**
	** DRUPAL Userprofile node Id (nid)
	**/
	private int m_iUPNID=0;
	public void setUserProfileNID(int number){
		m_iUPNID = number;
	}
	public void setUserProfileNID(String number){
		m_iUPNID = convertToInt(number);
		return;
	}
	public int getUserProfileNID(){
		return m_iUPNID;
	}

	/**
	** DRUPAL user role
	**/
	private int m_iUserRoleID=0;
	public void setUserRoleID(int number){
		m_iUserRoleID = number;
	}
	public void setUserRoleID(String number){
		m_iUserRoleID = convertToInt(number);
		return;
	}
	public int getUserRoleID(){
		return m_iUserRoleID;
	}

	/**
	** DRUPAL Userprofile version Id (vid)
	**/
	private int m_iUPVID=0;
	public void setUserProfileVID(int number){
		m_iUPVID = number;
	}
	public void setUserProfileVID(String number){
		m_iUPVID = convertToInt(number);
		return;
	}
	public int getUserProfileVID(){
		return m_iUPVID;
	}

	/**
	** DRUPAL Userprofile location Id (lid)
	**/
	private int m_iUPLID=0;
	public void setUserProfileLID(int number){
		m_iUPLID = number;
	}
	public void setUserProfileLID(String number){
		m_iUPLID = convertToInt(number);
		return;
	}
	public int getUserProfileLID(){
		return m_iUPLID;
	}
	

	/*
	 * a_iOrgNIDsArray
	 */
	private int[] a_iOrgNIDsArray=null;
	public void setOrgNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOrgNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOrgNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOrgNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getOrgNIDsArray(){
		if(a_iOrgNIDsArray == null) {
			a_iOrgNIDsArray=new int[0];
			return a_iOrgNIDsArray;
		}
		return a_iOrgNIDsArray;
	}
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



	/*
	 * a_iOrgOppNIDsArray
	 */
	private int[] a_iOrgOppNIDsArray=null;
	public void setOrgOppNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOrgOppNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOrgOppNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOrgOppNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getOrgOppNIDsArray(){
		if(a_iOrgOppNIDsArray == null) {
			a_iOrgOppNIDsArray=new int[0];
			return a_iOrgOppNIDsArray;
		}
		return a_iOrgOppNIDsArray;
	}
	private int m_iOPPNID=0;
	public void setOPPNID(int number){
		m_iOPPNID = number;
	}
	public void setOPPNID(String number){
		m_iOPPNID = convertToInt(number);
		return;
	}
	public int getOPPNID(){
		return m_iOPPNID;
	}


	/*
	 * a_iUserOppNIDsArray
	 */
	private int[] a_iUserOppNIDsArray=null;
	public void setUserOppNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUserOppNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUserOppNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUserOppNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getUserOppNIDsArray(){
		if(a_iUserOppNIDsArray == null) {
			a_iUserOppNIDsArray=new int[0];
			return a_iUserOppNIDsArray;
		}
		return a_iUserOppNIDsArray;
	}

	/**
	** SessionValue to use for cookie & db instert
	**/
	private String m_aszSessionValue=null;
	public void setSessionValue(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSessionValue = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSessionValue = aszTemp;
			return;
		}
		m_aszSessionValue = aszTemp.substring(0,iLen);
	}
	public String getSessionValue(){
		if(m_aszSessionValue== null) return "";
		return m_aszSessionValue;
	}

	/**
	** SessionIP to use for cookie & db instert
	**/
	private String m_aszSessionIP=null;
	public void setSessionIP(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSessionIP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSessionIP = aszTemp;
			return;
		}
		m_aszSessionIP = aszTemp.substring(0,iLen);
	}
	public String getSessionIP(){
		if(m_aszSessionIP== null) return "";
		return m_aszSessionIP;
	}

	/**
	** SessionTimestamp to use for cookie & db instert
	**/
	private int m_iSessionTimestamp=0;
	public void setSessionTimestamp(int number){
		m_iSessionTimestamp = number;
	}
	public void setSessionTimestamp(String number){
		m_iSessionTimestamp = convertToInt(number);
		return;
	}
	public int getSessionTimestamp(){
		return m_iSessionTimestamp;
	}
	

	/**
	** MapToPage to set flag for page redirection in the case that the user might get taken to an external URL
	**/
	private String m_aszMapToPage=null;
	public void setMapToPage(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszMapToPage = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszMapToPage = aszTemp;
			return;
		}
		m_aszMapToPage = aszTemp.substring(0,iLen);
	}
	public String getMapToPage(){
		if(m_aszMapToPage== null) return "";
		return m_aszMapToPage;
	}


	/**
	** MappingToPage to set flag for page redirection in the case that the user might get taken to an external URL
	**/
	private String m_aszMappingToPage=null;
	public void setMappingToPage(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszMappingToPage = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszMappingToPage = aszTemp;
			return;
		}
		m_aszMappingToPage = aszTemp.substring(0,iLen);
	}
	public String getMappingToPage(){
		if(m_aszMappingToPage== null) return "";
		return m_aszMappingToPage;
	}


	/**
	** password type VARCHAR(50) in table userprofileinfo 
	**/
	private String m_aszPasswordConfirm=null;
	public void setPasswordConfirm(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPasswordConfirm = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPasswordConfirm = aszTemp;
			return;
		}
		m_aszPasswordConfirm = aszTemp.substring(0,iLen);
	}
	public String getPasswordConfirm(){
		if(m_aszPasswordConfirm == null) return "";
		return m_aszPasswordConfirm;
	}


	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===

	//=== START User Athorization Section ===
	//=== START User Athorization Section ===
	//=== START User Athorization Section ===
	

	/**
	** Autorized Password
	**/
	private String m_aszAuthPass=null;
	public void setAuthPass(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAuthPass = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAuthPass = aszTemp;
			return;
		}
		m_aszAuthPass = aszTemp.substring(0,iLen);
	}
	public String getAuthPass(){
		if(m_aszAuthPass == null) return "";
		return m_aszAuthPass;
	}

	/**
	* process token text into hash tables
	*/
    public void processTokens(){
        if(null != m_aszUSPAuthTokens){
            buildAuthorizationAccess();
        }
        return;
    }

	/**
	* get system authorization values
	*/
    public Enumeration getSystemAuthList(){
        if(null == m_UserAuthorizationHashtable) return null;
        return m_UserAuthorizationHashtable.elements();
    }


	/**
	* add user The authority token
	*/
    public int addAuth(String LookupKey){
        String aszTemp=null;
		if(null == LookupKey) return 1;
        aszTemp=LookupKey.trim();
		if(aszTemp.length() < 2) return 1;
		if(null == m_aszUSPAuthTokens){
            m_aszUSPAuthTokens=aszTemp + ";";
            buildAuthorizationAccess();
            return 0;
        }
		if(null == m_UserAuthorizationHashtable){
            m_UserAuthorizationHashtable = new Hashtable(50);
        }
        m_UserAuthorizationHashtable.put(aszTemp,aszTemp);
        return resetAuthString();
    }

	/**
	* remove user The authority token
	*/
    public int remAuth(String LookupKey){
		if(null == LookupKey) return 1;
		if(LookupKey.length() < 2) return 1;
		if(null == m_UserAuthorizationHashtable) return 1;
		if(m_UserAuthorizationHashtable.isEmpty()) return 1;
        if(m_UserAuthorizationHashtable.containsKey(LookupKey)) {
            m_UserAuthorizationHashtable.remove(LookupKey);
            return resetAuthString();
        }
        return 1;
    }

	/**
	* copy hash table to string token
	*/
    private int resetAuthString(){
		StringBuffer aStringBuff=null;
        String aszTemp=null;
		if(null == m_UserAuthorizationHashtable) return 1;
		if(m_UserAuthorizationHashtable.isEmpty()){
            m_aszUSPAuthTokens=null;
            return 0;
        }
        Enumeration aEnum = m_UserAuthorizationHashtable.elements();
		if(null == aEnum) return 1;
		aStringBuff = new StringBuffer();
        while(aEnum.hasMoreElements()){
            aszTemp = (String)aEnum.nextElement() + ";";
			aStringBuff.append(aszTemp);
        }
        m_aszUSPAuthTokens = aStringBuff.toString();
        return 0;
    }

	/**
	* Is The authority token present in user token ?
	*/
    public boolean IsAuthAccessTo(String LookupKey){
		if(null == LookupKey) return false;
		if(LookupKey.length() < 2) return false;
		if(null == m_UserAuthorizationHashtable) return false;
		if(m_UserAuthorizationHashtable.isEmpty()) return false;
        if(m_UserAuthorizationHashtable.containsKey(LookupKey)) {
            // aszTemp = (String)m_UserAuthorizationHashtable.get(LookupKey);
            return true;
        }
        return false;
    }

	/**
	* build the access hash table for system authorization
	*/
    private void buildAuthorizationAccess(){
        int iIndex=0,iPosition=0;
        String aszSubString=null;
        if(null == m_aszUSPAuthTokens) return;
        if(null != m_UserAuthorizationHashtable) return;
        m_UserAuthorizationHashtable = new Hashtable(50);
        while(true){
            iIndex = m_aszUSPAuthTokens.indexOf(';',iPosition);
            if(iIndex < 0) break;
            aszSubString = m_aszUSPAuthTokens.substring(iPosition,iIndex);
            m_UserAuthorizationHashtable.put(aszSubString,aszSubString);
            iPosition=iIndex+1;
            iIndex=-1;
        }
        return;
    }

    private Hashtable m_UserAuthorizationHashtable=null;

	//=== END   User Athorization Section ===
	//=== END   User Athorization Section ===
	//=== END   User Athorization Section ===


	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table userprofileinfo 
	//===> Start Code Generated Methods For Table userprofileinfo 
	//===> Start Code Generated Methods For Table userprofileinfo 


	/**
	** person_number type LONG() in table userprofileinfo 
	**/
	private int m_iUSPPersonNumber=0;
	public void setUSPPersonNumber(int number){
		m_iUSPPersonNumber = number;
	}
	public void setUSPPersonNumber(String number){
		m_iUSPPersonNumber = convertToInt(number);
		return;
	}
	public int getUSPPersonNumber(){
		return m_iUSPPersonNumber;
	}


	/**
	** percodekey type CHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPPercodekey=null;
	public void setUSPPercodekey(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPercodekey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPercodekey = aszTemp;
			return;
		}
		m_aszUSPPercodekey = aszTemp.substring(0,iLen);
	}
	public String getUSPPercodekey(){
		if(m_aszUSPPercodekey == null) return "";
		return m_aszUSPPercodekey;
	}

	

	/**
	** accoutn status - whether this user's account is restricted (0) or not (1) 
	**/
	private int m_iUSPAccountStatus=-1;
	public void setUSPAccountStatus(int number){
		m_iUSPAccountStatus = number;
	}
	public void setUSPAccountStatus(String number){
		m_iUSPAccountStatus = convertToInt(number);
		return;
	}
	public int getUSPAccountStatus(){
		return m_iUSPAccountStatus;
	}


	/**
	** account_status type VARCHAR(50) in table userprofileinfo 
	private String m_aszUSPAccountStatus=null;
	public void setUSPAccountStatus(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAccountStatus = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAccountStatus = aszTemp;
			return;
		}
		m_aszUSPAccountStatus = aszTemp.substring(0,iLen);
	}
	public String getUSPAccountStatus(){
		if(m_aszUSPAccountStatus == null) return "";
		return m_aszUSPAccountStatus;
	}
	**/


	/**
	** account_type type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAccountType=null;
	public void setUSPAccountType(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAccountType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAccountType = aszTemp;
			return;
		}
		m_aszUSPAccountType = aszTemp.substring(0,iLen);
	}
	public String getUSPAccountType(){
		if(m_aszUSPAccountType == null) return "";
		return m_aszUSPAccountType;
	}


	/**
	** username type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPUsername=null;
	public void setUSPUsername(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPUsername = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPUsername = aszTemp;
			return;
		}
		m_aszUSPUsername = aszTemp.substring(0,iLen);
	}
	public String getUSPUsername(){
		if(m_aszUSPUsername == null) return "";
		return m_aszUSPUsername;
	}


	/**
	** password type VARCHAR(50) in table userprofileinfo 
	**/
	private String m_aszUSPPassword=null;
	public void setUSPPassword(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPassword = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPassword = aszTemp;
			return;
		}
		m_aszUSPPassword = aszTemp.substring(0,iLen);
	}
	public String getUSPPassword(){
		if(m_aszUSPPassword == null) return "";
		return m_aszUSPPassword;
	}


	/**
	** password Internal type VARCHAR(50) in table userprofileinfo 
	**/
	private String m_aszUSPPasswordInternal=null;
	public void setUSPPasswordInternal(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPasswordInternal = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPasswordInternal = aszTemp;
			return;
		}
		m_aszUSPPasswordInternal = aszTemp.substring(0,iLen);
	}
	public String getUSPPasswordInternal(){
		if(m_aszUSPPasswordInternal == null) return "";
		return m_aszUSPPasswordInternal;
	}


	/**
	** pass_phrase1 type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPPassPhrase1=null;
	public void setUSPPassPhrase1(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPassPhrase1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPassPhrase1 = aszTemp;
			return;
		}
		m_aszUSPPassPhrase1 = aszTemp.substring(0,iLen);
	}
	public String getUSPPassPhrase1(){
		if(m_aszUSPPassPhrase1 == null) return "";
		return m_aszUSPPassPhrase1;
	}


	/**
	** pass_phrase2 type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPPassPhrase2=null;
	public void setUSPPassPhrase2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPassPhrase2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPassPhrase2 = aszTemp;
			return;
		}
		m_aszUSPPassPhrase2 = aszTemp.substring(0,iLen);
	}
	public String getUSPPassPhrase2(){
		if(m_aszUSPPassPhrase2 == null) return "";
		return m_aszUSPPassPhrase2;
	}


	/**
	** lastlogin_dt type DATETIME() in table userprofileinfo 
	**/
	private java.util.Date m_azdUSPLastloginDt=null;
	public void setUSPLastloginDt(java.util.Date value){
		if(value == null){
			m_azdUSPLastloginDt = null;
			return;
		}
		m_azdUSPLastloginDt = value;
	}
	public java.util.Date getUSPLastloginDt(){
		if(m_azdUSPLastloginDt == null) return null;
		return m_azdUSPLastloginDt;
	}


	/**
	** create_dt type DATETIME() in table userprofileinfo 
	**/
	private java.util.Date m_azdUSPCreateDt=null;
	public void setUSPCreateDt(java.util.Date value){
		if(value == null){
			m_azdUSPCreateDt = null;
			return;
		}
		m_azdUSPCreateDt = value;
	}
	public java.util.Date getUSPCreateDt(){
		if(m_azdUSPCreateDt == null) return null;
		return m_azdUSPCreateDt;
	}


	/**
	** create_id type LONG() in table userprofileinfo 
	**/
	private int m_iUSPCreateId=0;
	public void setUSPCreateId(int number){
		m_iUSPCreateId = number;
	}
	public void setUSPCreateId(String number){
		m_iUSPCreateId = convertToInt(number);
		return;
	}
	public int getUSPCreateId(){
		return m_iUSPCreateId;
	}


	/**
	** update_dt type DATETIME() in table userprofileinfo 
	**/
	private java.util.Date m_azdUSPUpdateDt=null;
	public void setUSPUpdateDt(java.util.Date value){
		if(value == null){
			m_azdUSPUpdateDt = null;
			return;
		}
		m_azdUSPUpdateDt = value;
	}
	public java.util.Date getUSPUpdateDt(){
		if(m_azdUSPUpdateDt == null) return null;
		return m_azdUSPUpdateDt;
	}


	/**
	** update_id type LONG() in table userprofileinfo 
	**/
	private int m_iUSPUpdateId=0;
	public void setUSPUpdateId(int number){
		m_iUSPUpdateId = number;
	}
	public void setUSPUpdateId(String number){
		m_iUSPUpdateId = convertToInt(number);
		return;
	}
	public int getUSPUpdateId(){
		return m_iUSPUpdateId;
	}


	/**
	** terms & conditions 
	**/
	private String m_aszUSPAgree1Fg=null;
	public void setUSPAgree1Fg(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAgree1Fg = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAgree1Fg = aszTemp;
			return;
		}
		m_aszUSPAgree1Fg = aszTemp.substring(0,iLen);
	}
	public String getUSPAgree1Fg(){
		if(m_aszUSPAgree1Fg == null) return "";
		return m_aszUSPAgree1Fg;
	}


	/**
	** agree2_fg - 16 or older - store as Yes in drupal uprofile
	**/
	private String m_aszUSPAgree2Fg=null;
	public void setUSPAgree2Fg(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAgree2Fg = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAgree2Fg = aszTemp;
			return;
		}
		m_aszUSPAgree2Fg = aszTemp.substring(0,iLen);
	}
	public String getUSPAgree2Fg(){
		if(m_aszUSPAgree2Fg == null) return "";
		return m_aszUSPAgree2Fg;
	}

	/**
	** terms & conditions for WorldVision partnership
	**/
	private String m_aszUSPAgreeWVFg=null;
	public void setUSPAgreeWVFg(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAgreeWVFg = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAgreeWVFg = aszTemp;
			return;
		}
		m_aszUSPAgree1Fg = aszTemp.substring(0,iLen);
	}
	public String getUSPAgreeWVFg(){
		if(m_aszUSPAgreeWVFg == null) return "";
		return m_aszUSPAgreeWVFg;
	}


	/**
	** gender type VARCHAR(10) in table userprofileinfo 
	**/
	private String m_aszUSPGender=null;
	public void setUSPGender(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPGender = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPGender = aszTemp;
			return;
		}
		m_aszUSPGender = aszTemp.substring(0,iLen);
	}
	public String getUSPGender(){
		if(m_aszUSPGender == null) return "";
		return m_aszUSPGender;
	}


	/**
	** name_prefix type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszUSPNamePrefix=null;
	public void setUSPNamePrefix(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPNamePrefix = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPNamePrefix = aszTemp;
			return;
		}
		m_aszUSPNamePrefix = aszTemp.substring(0,iLen);
	}
	public String getUSPNamePrefix(){
		if(m_aszUSPNamePrefix == null) return "";
		return m_aszUSPNamePrefix;
	}


	/**
	** name_first type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPNameFirst=null;
	public void setUSPNameFirst(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPNameFirst = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPNameFirst = aszTemp;
			return;
		}
		m_aszUSPNameFirst = aszTemp.substring(0,iLen);
	}
	public String getUSPNameFirst(){
		if(m_aszUSPNameFirst == null) return "";
		return m_aszUSPNameFirst;
	}


	/**
	** name_middle type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPNameMiddle=null;
	public void setUSPNameMiddle(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPNameMiddle = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPNameMiddle = aszTemp;
			return;
		}
		m_aszUSPNameMiddle = aszTemp.substring(0,iLen);
	}
	public String getUSPNameMiddle(){
		if(m_aszUSPNameMiddle == null) return "";
		return m_aszUSPNameMiddle;
	}


	/**
	** name_last type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPNameLast=null;
	public void setUSPNameLast(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPNameLast = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPNameLast = aszTemp;
			return;
		}
		m_aszUSPNameLast = aszTemp.substring(0,iLen);
	}
	public String getUSPNameLast(){
		if(m_aszUSPNameLast == null) return "";
		return m_aszUSPNameLast;
	}


	/**
	** name_postfix type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszUSPNamePostfix=null;
	public void setUSPNamePostfix(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPNamePostfix = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPNamePostfix = aszTemp;
			return;
		}
		m_aszUSPNamePostfix = aszTemp.substring(0,iLen);
	}
	public String getUSPNamePostfix(){
		if(m_aszUSPNamePostfix == null) return "";
		return m_aszUSPNamePostfix;
	}


	/**
	** ss_id type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPSsId=null;
	public void setUSPSsId(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPSsId = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPSsId = aszTemp;
			return;
		}
		m_aszUSPSsId = aszTemp.substring(0,iLen);
	}
	public String getUSPSsId(){
		if(m_aszUSPSsId == null) return "";
		return m_aszUSPSsId;
	}


	/**
	** other_id type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszUSPOtherId=null;
	public void setUSPOtherId(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherId = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherId = aszTemp;
			return;
		}
		m_aszUSPOtherId = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherId(){
		if(m_aszUSPOtherId == null) return "";
		return m_aszUSPOtherId;
	}


	/**
	** birth_dt type DATETIME() in table userprofileinfo 
	**/
	private java.util.Date m_azdUSPBirthDt=null;
	public void setUSPBirthDt(java.util.Date value){
		if(value == null){
			m_azdUSPBirthDt = null;
			return;
		}
		m_azdUSPBirthDt = value;
	}
	public java.util.Date getUSPBirthDt(){
		if(m_azdUSPBirthDt == null) return null;
		return m_azdUSPBirthDt;
	}


	/**
	** Birth Year
	**/
	private int m_iBirthYear;
	public void setBirthYear(int number){
		m_iBirthYear = number;
	}
	public void setBirthYear(String number){
		m_iBirthYear = convertToInt(number);
		return;
	}
	public int getBirthYear(){
		return m_iBirthYear;
	}

	/**
	** email1_addr type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPEmail1Addr=null;
	public void setUSPEmail1Addr(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEmail1Addr = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEmail1Addr = aszTemp;
			return;
		}
		m_aszUSPEmail1Addr = aszTemp.substring(0,iLen);
	}
	public String getUSPEmail1Addr(){
		if(m_aszUSPEmail1Addr == null) return "";
		return m_aszUSPEmail1Addr;
	}


	/**
	** email1verify_dt type DATETIME() in table userprofileinfo 
	**/
	private java.util.Date m_azdUSPEmail1verifyDt=null;
	public void setUSPEmail1verifyDt(java.util.Date value){
		if(value == null){
			m_azdUSPEmail1verifyDt = null;
			return;
		}
		m_azdUSPEmail1verifyDt = value;
	}
	public java.util.Date getUSPEmail1verifyDt(){
		if(m_azdUSPEmail1verifyDt == null) return null;
		return m_azdUSPEmail1verifyDt;
	}


	/**
	** email1verify_fg type CHAR(1) in table userprofileinfo 
	**/
	private String m_aszUSPEmail1verifyFg=null;
	public void setUSPEmail1verifyFg(String value){
		int iLen=1;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEmail1verifyFg = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEmail1verifyFg = aszTemp;
			return;
		}
		m_aszUSPEmail1verifyFg = aszTemp.substring(0,iLen);
	}
	public String getUSPEmail1verifyFg(){
		if(m_aszUSPEmail1verifyFg == null) return "";
		return m_aszUSPEmail1verifyFg;
	}


	/**
	** email2_addr type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPEmail2Addr=null;
	public void setUSPEmail2Addr(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEmail2Addr = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEmail2Addr = aszTemp;
			return;
		}
		m_aszUSPEmail2Addr = aszTemp.substring(0,iLen);
	}
	public String getUSPEmail2Addr(){
		if(m_aszUSPEmail2Addr == null) return "";
		return m_aszUSPEmail2Addr;
	}


	/**
	** phone1 type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPPhone1=null;
	public void setUSPPhone1(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPhone1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPhone1 = aszTemp;
			return;
		}
		m_aszUSPPhone1 = aszTemp.substring(0,iLen);
	}
	public String getUSPPhone1(){
		if(m_aszUSPPhone1 == null) return "";
		return m_aszUSPPhone1;
	}

	private String m_aszUSPPhone1Type=null;
	public void setUSPPhone1Type(String aszPhoneType) {
		int iLen=20;
		String aszTemp = aszPhoneType;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPhone1Type = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPhone1Type = aszTemp;
			return;
		}
		m_aszUSPPhone1Type = aszTemp.substring(0,iLen);
	}
	public String getUSPPhone1Type(){
		if(m_aszUSPPhone1Type == null) return "";
		return m_aszUSPPhone1Type;
	}


	/**
	** phone2 type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPPhone2=null;
	public void setUSPPhone2(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPhone2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPhone2 = aszTemp;
			return;
		}
		m_aszUSPPhone2 = aszTemp.substring(0,iLen);
	}
	public String getUSPPhone2(){
		if(m_aszUSPPhone2 == null) return "";
		return m_aszUSPPhone2;
	}

	private String m_aszUSPPhone2Type=null;
	public void setUSPPhone2Type(String aszPhoneType) {
		int iLen=20;
		String aszTemp = aszPhoneType;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPhone2Type = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPhone2Type = aszTemp;
			return;
		}
		m_aszUSPPhone2Type = aszTemp.substring(0,iLen);
	}
	public String getUSPPhone2Type(){
		if(m_aszUSPPhone2Type == null) return "";
		return m_aszUSPPhone2Type;
	}


	/**
	** phone_mobile type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPPhoneMobile=null;
	public void setUSPPhoneMobile(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPhoneMobile = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPhoneMobile = aszTemp;
			return;
		}
		m_aszUSPPhoneMobile = aszTemp.substring(0,iLen);
	}
	public String getUSPPhoneMobile(){
		if(m_aszUSPPhoneMobile == null) return "";
		return m_aszUSPPhoneMobile;
	}

	/**
	* Email m_EmailRailsSkills
	*/
	private String[][] m_UserRailsPhones=null;
	public void setUserRailsPhonesArray(String[][] values){
		int iLen=255;
		String[][] a_aszTemp = values;
		m_UserRailsPhones = new String[a_aszTemp.length][];
		if(a_aszTemp.length < 1){
			m_UserRailsPhones = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			m_UserRailsPhones = a_aszTemp;
			return;
		}
	}
	public String[][] getUserRailsPhonesArray(){
		if(m_UserRailsPhones == null) {
			m_UserRailsPhones=new String[0][0];
			return m_UserRailsPhones;
		}
		return m_UserRailsPhones;
	}
	/**
	* User m_UserRailsSkills
	*/
	private String m_UserRailsSkills=null;
	public void setUserRailsSkills(String values){
		int iLen=255;
		String a_aszTemp = values;
		m_UserRailsSkills = values;
		if(a_aszTemp.length() < 1){
			m_UserRailsSkills = null;
			return;
		}
		if(a_aszTemp.length() < iLen + 1){
			m_UserRailsSkills = a_aszTemp;
			return;
		}
	}
	public String getUserRailsSkills(){
		if(m_UserRailsSkills == null) return "";
		return m_UserRailsSkills;
	}
	private String[] m_UserRailsSkillsArray=null;
	public void setUserRailsSkillsArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		m_UserRailsSkillsArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			m_UserRailsSkillsArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			m_UserRailsSkillsArray = a_aszTemp;
			return;
		}
	}
	public String[] getUserRailsSkillsArray(){
		if(m_UserRailsSkillsArray == null) {
			m_UserRailsSkillsArray=new String[0];
			return m_UserRailsSkillsArray;
		}
		return m_UserRailsSkillsArray;
	}

	/**
	** fax1 type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPFax1=null;
	public void setUSPFax1(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPFax1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPFax1 = aszTemp;
			return;
		}
		m_aszUSPFax1 = aszTemp.substring(0,iLen);
	}
	public String getUSPFax1(){
		if(m_aszUSPFax1 == null) return "";
		return m_aszUSPFax1;
	}


	/**
	** fax2 type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszUSPFax2=null;
	public void setUSPFax2(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPFax2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPFax2 = aszTemp;
			return;
		}
		m_aszUSPFax2 = aszTemp.substring(0,iLen);
	}
	public String getUSPFax2(){
		if(m_aszUSPFax2 == null) return "";
		return m_aszUSPFax2;
	}


	/**
	** addr_line1 type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrLine1=null;
	public void setUSPAddrLine1(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrLine1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrLine1 = aszTemp;
			return;
		}
		m_aszUSPAddrLine1 = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrLine1(){
		if(m_aszUSPAddrLine1 == null) return "";
		return m_aszUSPAddrLine1;
	}


	/**
	** addr_line2 type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrLine2=null;
	public void setUSPAddrLine2(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrLine2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrLine2 = aszTemp;
			return;
		}
		m_aszUSPAddrLine2 = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrLine2(){
		if(m_aszUSPAddrLine2 == null) return "";
		return m_aszUSPAddrLine2;
	}


	/**
	** addr_line3 type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrLine3=null;
	public void setUSPAddrLine3(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrLine3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrLine3 = aszTemp;
			return;
		}
		m_aszUSPAddrLine3 = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrLine3(){
		if(m_aszUSPAddrLine3 == null) return "";
		return m_aszUSPAddrLine3;
	}


	/**
	** addr_city type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrCity=null;
	public void setUSPAddrCity(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrCity = aszTemp;
			return;
		}
		m_aszUSPAddrCity = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrCity(){
		if(m_aszUSPAddrCity == null) return "";
		return m_aszUSPAddrCity;
	}


	/**
	** addr_stateprov type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrStateprov=null;
	public void setUSPAddrStateprov(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrStateprov = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrStateprov = aszTemp;
			return;
		}
		m_aszUSPAddrStateprov = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrStateprov(){
		if(m_aszUSPAddrStateprov == null) return "";
		return m_aszUSPAddrStateprov;
	}


	/**
	** addr_postalcode type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszUSPAddrPostalcode=null;
	public void setUSPAddrPostalcode(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrPostalcode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrPostalcode = aszTemp;
			return;
		}
		m_aszUSPAddrPostalcode = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrPostalcode(){
		if(m_aszUSPAddrPostalcode == null) return "";
		return m_aszUSPAddrPostalcode;
	}


	/**
	** addr_country_name type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPAddrCountryName=null;
	public void setUSPAddrCountryName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrCountryName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrCountryName = aszTemp;
			return;
		}
		m_aszUSPAddrCountryName = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrCountryName(){
		if(m_aszUSPAddrCountryName == null) return "";
		return m_aszUSPAddrCountryName;
	}


	/**
	** addr_county type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrCounty=null;
	public void setUSPAddrCounty(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrCounty = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrCounty = aszTemp;
			return;
		}
		m_aszUSPAddrCounty = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrCounty(){
		if(m_aszUSPAddrCounty == null) return "";
		return m_aszUSPAddrCounty;
	}


	/**
	** webpage type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPWebpage=null;
	public void setUSPWebpage(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPWebpage = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPWebpage = aszTemp;
			return;
		}
		m_aszUSPWebpage = aszTemp.substring(0,iLen);
	}
	public String getUSPWebpage(){
		if(m_aszUSPWebpage == null) return "";
		return m_aszUSPWebpage;
	}


	/**
	** primary_language type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPPrimaryLanguage=null;
	public void setUSPPrimaryLanguage(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPrimaryLanguage = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPrimaryLanguage = aszTemp;
			return;
		}
		m_aszUSPPrimaryLanguage = aszTemp.substring(0,iLen);
	}
	public String getUSPPrimaryLanguage(){
		if(m_aszUSPPrimaryLanguage == null) return "";
		return m_aszUSPPrimaryLanguage;
	}


	/**
	** ethnicity type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPEthnicity=null;
	public void setUSPEthnicity(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEthnicity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEthnicity = aszTemp;
			return;
		}
		m_aszUSPEthnicity = aszTemp.substring(0,iLen);
	}
	public String getUSPEthnicity(){
		if(m_aszUSPEthnicity == null) return "";
		return m_aszUSPEthnicity;
	}


	/**
	** employment type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPEmployment=null;
	public void setUSPEmployment(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEmployment = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEmployment = aszTemp;
			return;
		}
		m_aszUSPEmployment = aszTemp.substring(0,iLen);
	}
	public String getUSPEmployment(){
		if(m_aszUSPEmployment == null) return "";
		return m_aszUSPEmployment;
	}


	/**
	** education type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUSPEducation=null;
	public void setUSPEducation(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPEducation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPEducation = aszTemp;
			return;
		}
		m_aszUSPEducation = aszTemp.substring(0,iLen);
	}
	public String getUSPEducation(){
		if(m_aszUSPEducation == null) return "";
		return m_aszUSPEducation;
	}


	/**
	** cookie authorized user
	**/
	private int m_iCookieAuthorize=-1;
	public void setCookieAuthorize(int number){
		m_iCookieAuthorize = number;
	}
	public void setUSPCookieAuthorize(String number){
		m_iCookieAuthorize = convertToInt(number);
		return;
	}
	public int getCookieAuthorize(){
		return m_iCookieAuthorize;
	}

	/**
	** auth_tokens type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPAuthTokens=null;

	public void setUSPAuthTokens(String value){
		if(null == value){
			m_aszUSPAuthTokens = null;
			return;
		}
		m_aszUSPAuthTokens = value.trim();
	}
	public String getUSPAuthTokens(){
		if(m_aszUSPAuthTokens == null) return "";
		return m_aszUSPAuthTokens;
	}


	/**
	** config_tokens type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPConfigTokens=null;

	public void setUSPConfigTokens(String value){
		if(null == value){
			m_aszUSPConfigTokens = null;
			return;
		}
		m_aszUSPConfigTokens = value.trim();
	}
	public String getUSPConfigTokens(){
		if(m_aszUSPConfigTokens == null) return "";
		return m_aszUSPConfigTokens;
	}


	/**
	** user_comment type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPUserComment=null;

	public void setUSPUserComment(String value){
		if(null == value){
			m_aszUSPUserComment = null;
			return;
		}
		m_aszUSPUserComment = value.trim();
	}
	public String getUSPUserComment(){
		if(m_aszUSPUserComment == null) return "";
		return m_aszUSPUserComment;
	}


	/**
	** internal_comment1 type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPInternalComment1=null;

	public void setUSPInternalComment1(String value){
		if(null == value){
			m_aszUSPInternalComment1 = null;
			return;
		}
		m_aszUSPInternalComment1 = value.trim();
	}
	public String getUSPInternalComment1(){
		if(m_aszUSPInternalComment1 == null) return "";
		return m_aszUSPInternalComment1;
	}


	/**
	** internal_comment2 type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPInternalComment2=null;

	public void setUSPInternalComment2(String value){
		if(null == value){
			m_aszUSPInternalComment2 = null;
			return;
		}
		m_aszUSPInternalComment2 = value.trim();
	}
	public String getUSPInternalComment2(){
		if(m_aszUSPInternalComment2 == null) return "";
		return m_aszUSPInternalComment2;
	}


	/**
	** internal_comment type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPInternalComment=null;

	public void setUSPInternalComment(String value){
		if(null == value){
			m_aszUSPInternalComment = null;
			return;
		}
		m_aszUSPInternalComment = value.trim();
	}
	public String getUSPInternalComment(){
		if(m_aszUSPInternalComment == null) return "";
		return m_aszUSPInternalComment;
	}


	/**
	** internal_usertype_comment - does this user have an entry in any of the following tables?:
	*		users, content_type_uprofile, location, location_phone
	**/
	private String m_aszUSPInternalUserTypeComment=null;

	public void setUSPInternalUserTypeComment(String value){
		if(null == value){
			m_aszUSPInternalUserTypeComment = null;
			return;
		}
		m_aszUSPInternalUserTypeComment = value.trim();
	}
	public String getUSPInternalUserTypeComment(){
		if(m_aszUSPInternalUserTypeComment == null) return "";
		return m_aszUSPInternalUserTypeComment;
	}

	/**
	** internal_tac_lite_comment - 
	*		this will be a string passed through input tag that will have all the tid's of the voleng'vid's that we do NOT want to delete
	*		such as Organizational Affiliation - as of writing, Taxonomy Access Control Lite restrictions apply to TechMission Member and CCDA Member
	**/
	private String m_aszUSPInternalTacLiteComment=null;

	public void setUSPInternalTacLiteComment(String value){
		if(null == value){
			m_aszUSPInternalTacLiteComment = null;
			return;
		}
		m_aszUSPInternalTacLiteComment = value.trim();
	}
	public String getUSPInternalTacLiteComment(){
		if(m_aszUSPInternalTacLiteComment == null) return "";
		return m_aszUSPInternalTacLiteComment;
	}


	/**
	** m_aszUSPResumeFilePath type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPResumeFilePath=null;

	public void setUSPResumeFilePath(String value){
		if(null == value){
			m_aszUSPResumeFilePath = null;
			return;
		}
		m_aszUSPResumeFilePath = value.trim();
	}
	public String getUSPResumeFilePath(){
		if(m_aszUSPResumeFilePath == null) return "";
		return m_aszUSPResumeFilePath;
	}

	/**
	** vol_resume type TEXT() in table userprofileinfo 
	**/
	private String m_aszUSPVolResume=null;
	public void setUSPVolResume(String value){
		if(null == value){
			m_aszUSPVolResume = null;
			return;
		}
		m_aszUSPVolResume = value.trim();
	}
	public String getUSPVolResume(){
		if(m_aszUSPVolResume == null) return "";
		return m_aszUSPVolResume;
	}


	/**
	** title type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPTitle=null;
	public void setUSPTitle(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPTitle = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPTitle = aszTemp;
			return;
		}
		m_aszUSPTitle = aszTemp.substring(0,iLen);
	}
	public String getUSPTitle(){
		if(m_aszUSPTitle == null) return "";
		return m_aszUSPTitle;
	}


	/**
	** vol_skills type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolSkills=null;
	public void setUSPVolSkills(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolSkills = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolSkills = aszTemp;
			return;
		}
		m_aszUSPVolSkills = aszTemp.substring(0,iLen);
	}
	public String getUSPVolSkills(){
		if(m_aszUSPVolSkills == null) return "";
		return m_aszUSPVolSkills;
	}

	/**
	** vol_skills2 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolSkills2=null;
	public void setUSPVolSkills2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolSkills2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolSkills2 = aszTemp;
			return;
		}
		m_aszUSPVolSkills2 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolSkills2(){
		if(m_aszUSPVolSkills2 == null) return "";
		return m_aszUSPVolSkills2;
	}

	/**
	** vol_skills3 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolSkills3=null;
	public void setUSPVolSkills3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolSkills3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolSkills3 = aszTemp;
			return;
		}
		m_aszUSPVolSkills3 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolSkills3(){
		if(m_aszUSPVolSkills3 == null) return "";
		return m_aszUSPVolSkills3;
	}

	/**
	** Skills 1 taxonomy tid type LONG()
	**/
	private int m_iUSPVolSkills1TID=-1;
	public void setUSPVolSkills1TID(int number){
		m_iUSPVolSkills1TID = number;
	}
	public void setUSPVolSkills1TID(String number){
		m_iUSPVolSkills1TID = convertToInt(number);
		return;
	}
	public int getUSPVolSkills1TID(){
		return m_iUSPVolSkills1TID;
	}

	/**
	** Skills 2 taxonomy tid type LONG()
	**/
	private int m_iUSPVolSkills2TID=-1;
	public void setUSPVolSkills2TID(int number){
		m_iUSPVolSkills2TID = number;
	}
	public void setUSPVolSkills2TID(String number){
		m_iUSPVolSkills2TID = convertToInt(number);
		return;
	}
	public int getUSPVolSkills2TID(){
		return m_iUSPVolSkills2TID;
	}

	/**
	** Skills 3 taxonomy tid type LONG()
	**/
	private int m_iUSPVolSkills3TID=-1;
	public void setUSPVolSkills3TID(int number){
		m_iUSPVolSkills3TID = number;
	}
	public void setUSPVolSkills3TID(String number){
		m_iUSPVolSkills3TID = convertToInt(number);
		return;
	}
	public int getUSPVolSkills3TID(){
		return m_iUSPVolSkills3TID;
	}

	
	
	/**
	** InterestArea type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolInterestArea1=null;
	public void setUSPVolInterestArea1(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolInterestArea1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolInterestArea1 = aszTemp;
			return;
		}
		m_aszUSPVolInterestArea1 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolInterestArea1(){
		if(m_aszUSPVolInterestArea1 == null) return "";
		return m_aszUSPVolInterestArea1;
	}

	/**
	** InterestArea2 type VARCHAR(255) taxonomy
	**/
	private String m_aszUSPVolInterestArea2=null;
	public void setUSPVolInterestArea2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolInterestArea2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolInterestArea2 = aszTemp;
			return;
		}
		m_aszUSPVolInterestArea2 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolInterestArea2(){
		if(m_aszUSPVolInterestArea2 == null) return "";
		return m_aszUSPVolInterestArea2;
	}

	/**
	** InterestArea3 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolInterestArea3=null;
	public void setUSPVolInterestArea3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolInterestArea3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolInterestArea3 = aszTemp;
			return;
		}
		m_aszUSPVolInterestArea3 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolInterestArea3(){
		if(m_aszUSPVolInterestArea3 == null) return "";
		return m_aszUSPVolInterestArea3;
	}

	/**
	** InterestArea 1 taxonomy tid type LONG()
	**/
	private int m_iUSPVolInterestArea1TID=-1;
	public void setUSPVolInterestArea1TID(int number){
		m_iUSPVolInterestArea1TID = number;
	}
	public void setUSPVolInterestArea1TID(String number){
		m_iUSPVolInterestArea1TID = convertToInt(number);
		return;
	}
	public int getUSPVolInterestArea1TID(){
		return m_iUSPVolInterestArea1TID;
	}

	/**
	** InterestArea 2 taxonomy tid type LONG()
	**/
	private int m_iUSPVolInterestArea2TID=-1;
	public void setUSPVolInterestArea2TID(int number){
		m_iUSPVolInterestArea2TID = number;
	}
	public void setUSPVolInterestArea2TID(String number){
		m_iUSPVolInterestArea2TID = convertToInt(number);
		return;
	}
	public int getUSPVolInterestArea2TID(){
		return m_iUSPVolInterestArea2TID;
	}

	/**
	** InterestArea 3 taxonomy tid type LONG()
	**/
	private int m_iUSPVolInterestArea3TID=-1;
	public void setUSPVolInterestArea3TID(int number){
		m_iUSPVolInterestArea3TID = number;
	}
	public void setUSPVolInterestArea3TID(String number){
		m_iUSPVolInterestArea3TID = convertToInt(number);
		return;
	}
	public int getUSPVolInterestArea3TID(){
		return m_iUSPVolInterestArea3TID;
	}
	
	
	/**
	** Languages Spoken1 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolLang1=null;
	public void setUSPVolLang1(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolLang1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolLang1 = aszTemp;
			return;
		}
		m_aszUSPVolLang1 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolLang1(){
		if(m_aszUSPVolLang1 == null) return "";
		return m_aszUSPVolLang1;
	}

	/**
	** Languages Spoken 1 taxonomy tid type LONG()
	**/
	private int m_iUSPVolLang1TID=-1;
	public void setUSPVolLang1TID(int number){
		m_iUSPVolLang1TID = number;
	}
	public void setUSPVolLang1TID(String number){
		m_iUSPVolLang1TID = convertToInt(number);
		return;
	}
	public int getUSPVolLang1TID(){
		return m_iUSPVolLang1TID;
	}

	/**
	** Languages Spoken2 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolLang2=null;
	public void setUSPVolLang2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolLang2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolLang2 = aszTemp;
			return;
		}
		m_aszUSPVolLang2 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolLang2(){
		if(m_aszUSPVolLang2 == null) return "";
		return m_aszUSPVolLang2;
	}

	/**
	** Languages Spoken 2 taxonomy tid type LONG()
	**/
	private int m_iUSPVolLang2TID=-1;
	public void setUSPVolLang2TID(int number){
		m_iUSPVolLang2TID = number;
	}
	public void setUSPVolLang2TID(String number){
		m_iUSPVolLang2TID = convertToInt(number);
		return;
	}
	public int getUSPVolLang2TID(){
		return m_iUSPVolLang2TID;
	}

	
	/**
	** Languages Spoken3 type VARCHAR(255) taxonomy 
	**/
	private String m_aszUSPVolLang3=null;
	public void setUSPVolLang3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolLang3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolLang3 = aszTemp;
			return;
		}
		m_aszUSPVolLang3 = aszTemp.substring(0,iLen);
	}
	public String getUSPVolLang3(){
		if(m_aszUSPVolLang3 == null) return "";
		return m_aszUSPVolLang3;
	}

	/**
	** Languages Spoken 3 taxonomy tid type LONG()
	**/
	private int m_iUSPVolLang3TID=-1;
	public void setUSPVolLang3TID(int number){
		m_iUSPVolLang3TID = number;
	}
	public void setUSPVolLang3TID(String number){
		m_iUSPVolLang3TID = convertToInt(number);
		return;
	}
	public int getUSPVolLang3TID(){
		return m_iUSPVolLang3TID;
	}

	

	/**
	** denom_affil_p type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPDenomAffilP=null;
	public void setUSPDenomAffilP(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPDenomAffilP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPDenomAffilP = aszTemp;
			return;
		}
		m_aszUSPDenomAffilP = aszTemp.substring(0,iLen);
	}
	public String getUSPDenomAffilP(){
		if(m_aszUSPDenomAffilP == null) return "";
		return m_aszUSPDenomAffilP;
	}

	/**
	** Denom Affil taxonomy tid type LONG()
	**/
	private int m_iUSPDenomAffilTID=-1;
	public void setUSPDenomAffilTID(int number){
		m_iUSPDenomAffilTID = number;
	}
	public void setUSPDenomAffilTID(String number){
		m_iUSPDenomAffilTID = convertToInt(number);
		return;
	}
	public int getUSPDenomAffilTID(){
		return m_iUSPDenomAffilTID;
	}


	/**
	** other_affil_p (Org Affiliation Person) 1 type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPOtherAffilP=null;
	public void setUSPOtherAffilP(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherAffilP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherAffilP = aszTemp;
			return;
		}
		m_aszUSPOtherAffilP = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherAffilP(){
		if(m_aszUSPOtherAffilP == null) return "";
		return m_aszUSPOtherAffilP;
	}

	/**
	** Other Affil 1 taxonomy tid type LONG()
	**/
	private int m_iUSPOtherAffil1TID=-1;
	public void setUSPOtherAffil1TID(int number){
		m_iUSPOtherAffil1TID = number;
	}
	public void setUSPOtherAffil1TID(String number){
		m_iUSPOtherAffil1TID = convertToInt(number);
		return;
	}
	public int getUSPOtherAffil1TID(){
		return m_iUSPOtherAffil1TID;
	}

	
	/*
	 * get array of nat'l associations this user has
	 */
	private int[] a_iNatlAssociationNIDsArray=null;
	public void setUSPNatlAssociationNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iNatlAssociationNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iNatlAssociationNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iNatlAssociationNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPNatlAssociationNIDsArray(){
		if(a_iNatlAssociationNIDsArray == null) {
			a_iNatlAssociationNIDsArray=new int[0];
			return a_iNatlAssociationNIDsArray;
		}
		return a_iNatlAssociationNIDsArray;
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

	
	/*
	 * Array of all NATIONAL association (gives access rights on corresponding OrgAffil) in table organizationinfo
	 */
	private String[] a_aszNatlAssociationsArray=null;
	public void setUSPNatlAssociationsArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszNatlAssociationsArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszNatlAssociationsArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszNatlAssociationsArray = a_aszTemp;
			return;
		}
	}
	public String[] getUSPNatlAssociationsArray(){
		if(a_aszNatlAssociationsArray == null) {
			a_aszNatlAssociationsArray=new String[0];
			return a_aszNatlAssociationsArray;
		}
		return a_aszNatlAssociationsArray;
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
	 * Array of all NATIONAL association portals & their Association names - semi-colon de-limited
	 *  (gives access rights on corresponding OrgAffil) in table organizationinfo
	 */
	private String[] a_aszNatlAssociationPortalsArray=null;
	public void setUSPNatlAssociationPortalsArray(String[] values){
		int iLen=512;
		String[] a_aszTemp = values;
		a_aszNatlAssociationPortalsArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszNatlAssociationPortalsArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszNatlAssociationPortalsArray = a_aszTemp;
			return;
		}
	}
	public String[] getUSPNatlAssociationPortalsArray(){
		if(a_aszNatlAssociationPortalsArray == null) {
			a_aszNatlAssociationPortalsArray=new String[0];
			return a_aszNatlAssociationPortalsArray;
		}
		return a_aszNatlAssociationPortalsArray;
	}
	/**
	** NATIONAL association portal (gives access rights on corresponding OrgAffil) in table organizationinfo 
	**/
	private String m_aszNatlAssociationPortal=null;
	public void setNatlAssociationPortal(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszNatlAssociationPortal = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszNatlAssociationPortal = aszTemp;
			return;
		}
		m_aszNatlAssociationPortal = aszTemp.substring(0,iLen);
	}
	public String getNatlAssociationPortal(){
		if(m_aszNatlAssociationPortal == null) return "";
		return m_aszNatlAssociationPortal;
	}

	/**
	** (Org Affiliation Person) 2 type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPOtherAffil2=null;
	public void setUSPOtherAffil2(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherAffil2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherAffil2 = aszTemp;
			return;
		}
		m_aszUSPOtherAffil2 = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherAffil2(){
		if(m_aszUSPOtherAffil2 == null) return "";
		return m_aszUSPOtherAffil2;
	}

	/**
	** Other Affil 2 taxonomy tid type LONG()
	**/
	private int m_iUSPOtherAffil2TID=-1;
	public void setUSPOtherAffil2TID(int number){
		m_iUSPOtherAffil2TID = number;
	}
	public void setUSPOtherAffil2TID(String number){
		m_iUSPOtherAffil2TID = convertToInt(number);
		return;
	}
	public int getUSPOtherAffil2TID(){
		return m_iUSPOtherAffil2TID;
	}

	/**
	** (Org Affiliation Person) 3 type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPOtherAffil3=null;
	public void setUSPOtherAffil3(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherAffil3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherAffil3 = aszTemp;
			return;
		}
		m_aszUSPOtherAffil3 = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherAffil3(){
		if(m_aszUSPOtherAffil3 == null) return "";
		return m_aszUSPOtherAffil3;
	}

	/**
	** Other Affil 3 taxonomy tid type LONG()
	**/
	private int m_iUSPOtherAffil3TID=-1;
	public void setUSPOtherAffil3TID(int number){
		m_iUSPOtherAffil3TID = number;
	}
	public void setUSPOtherAffil3TID(String number){
		m_iUSPOtherAffil3TID = convertToInt(number);
		return;
	}
	public int getUSPOtherAffil3TID(){
		return m_iUSPOtherAffil3TID;
	}


	/**
	** Volunteer Availability type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPVolAvail=null;
	public void setUSPVolAvail(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolAvail = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolAvail = aszTemp;
			return;
		}
		m_aszUSPVolAvail = aszTemp.substring(0,iLen);
	}
	public String getUSPVolAvail(){
		if(m_aszUSPVolAvail == null) return "";
		return m_aszUSPVolAvail;
	}

	/**
	** Volunteer Availability taxonomy tid type LONG()
	**/
	private int m_iUSPVolAvailTID=-1;
	public void setUSPVolAvailTID(int number){
		m_iUSPVolAvailTID = number;
	}
	public void setUSPVolAvailTID(String number){
		m_iUSPVolAvailTID = convertToInt(number);
		return;
	}
	public int getUSPVolAvailTID(){
		return m_iUSPVolAvailTID;
	}

	/**
	** Volunteer Board type VARCHAR(100)  taxonomy 
	**/
	private String m_aszUSPVolBoard=null;
	public void setUSPVolBoard(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolBoard = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolBoard = aszTemp;
			return;
		}
		m_aszUSPVolBoard = aszTemp.substring(0,iLen);
	}
	public String getUSPVolBoard(){
		if(m_aszUSPVolBoard == null) return "";
		return m_aszUSPVolBoard;
	}

	/**
	** Volunteer Board taxonomy tid type LONG()
	**/
	private int m_iUSPVolBoardTID=-1;
	public void setUSPVolBoardTID(int number){
		m_iUSPVolBoardTID = number;
	}
	public void setUSPVolBoardTID(String number){
		m_iUSPVolBoardTID = convertToInt(number);
		return;
	}
	public int getUSPVolBoardTID(){
		return m_iUSPVolBoardTID;
	}
	
	/**
	** Volunteer Virtual type VARCHAR(100) taxonomy 
	**/
	private String m_aszUSPVolVirt=null;
	public void setUSPVolVirt(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolVirt = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolVirt = aszTemp;
			return;
		}
		m_aszUSPVolBoard = aszTemp.substring(0,iLen);
	}
	public String getUSPVolVirt(){
		if(m_aszUSPVolVirt == null) return "";
		return m_aszUSPVolVirt;
	}

	/**
	** Virtual Volunteer taxonomy tid type LONG() 
	**/
	private int m_iUSPVolVirtTID=-1;
	public void setUSPVolVirtTID(int number){
		m_iUSPVolVirtTID = number;
	}
	public void setUSPVolVirtTID(String number){
		m_iUSPVolVirtTID = convertToInt(number);
		return;
	}
	public int getUSPVolVirtTID(){
		return m_iUSPVolVirtTID;
	}

	/**
	** LocalVol taxonomy tid type LONG() 
	**/
	private int m_iUSPLocalVolTID=-1;
	public void setUSPLocalVolTID(int number){
		m_iUSPLocalVolTID = number;
	}
	public void setUSPLocalVolTID(String number){
		m_iUSPLocalVolTID = convertToInt(number);
		return;
	}
	public int getUSPLocalVolTID(){
		return m_iUSPLocalVolTID;
	}

	/**
	** GroupFamily taxonomy tid type LONG() 
	**/
	private int m_iUSPGroupFamilyTID=-1;
	public void setUSPGroupFamilyTID(int number){
		m_iUSPGroupFamilyTID = number;
	}
	public void setUSPGroupFamilyTID(String number){
		m_iUSPGroupFamilyTID = convertToInt(number);
		return;
	}
	public int getUSPGroupFamilyTID(){
		return m_iUSPGroupFamilyTID;
	}

	/**
	** Internship taxonomy tid type LONG() 
	**/
	private int m_iUSPIntrnTID=-1;
	public void setUSPIntrnTID(int number){
		m_iUSPIntrnTID = number;
	}
	public void setUSPIntrnTID(String number){
		m_iUSPIntrnTID = convertToInt(number);
		return;
	}
	public int getUSPIntrnTID(){
		return m_iUSPIntrnTID;
	}

	/**
	** Summer internship taxonomy tid type LONG() 
	**/
	private int m_iUSPSumIntrnTID=-1;
	public void setUSPSumIntrnTID(int number){
		m_iUSPSumIntrnTID = number;
	}
	public void setUSPSumIntrnTID(String number){
		m_iUSPSumIntrnTID = convertToInt(number);
		return;
	}
	public int getUSPSumIntrnTID(){
		return m_iUSPSumIntrnTID;
	}

	/**
	** WorkStudy taxonomy tid type LONG() 
	**/
	private int m_iUSPWorkStudyTID=-1;
	public void setUSPWorkStudyTID(int number){
		m_iUSPWorkStudyTID = number;
	}
	public void setUSPWorkStudyTID(String number){
		m_iUSPWorkStudyTID = convertToInt(number);
		return;
	}
	public int getUSPWorkStudyTID(){
		return m_iUSPWorkStudyTID;
	}

	/**
	** Jobs taxonomy tid type LONG() 
	**/
	private int m_iUSPJobsTID=-1;
	public void setUSPJobsTID(int number){
		m_iUSPJobsTID = number;
	}
	public void setUSPJobsTID(String number){
		m_iUSPJobsTID = convertToInt(number);
		return;
	}
	public int getUSPJobsTID(){
		return m_iUSPJobsTID;
	}

	/**
	** Conference taxonomy tid type LONG() 
	**/
	private int m_iUSPConferenceTID=-1;
	public void setUSPConferenceTID(int number){
		m_iUSPConferenceTID = number;
	}
	public void setUSPConferenceTID(String number){
		m_iUSPConferenceTID = convertToInt(number);
		return;
	}
	public int getUSPConferenceTID(){
		return m_iUSPConferenceTID;
	}

	/**
	** Consulting taxonomy tid type LONG() 
	**/
	private int m_iUSPConsultingTID=-1;
	public void setUSPConsultingTID(int number){
		m_iUSPConsultingTID = number;
	}
	public void setUSPConsultingTID(String number){
		m_iUSPConsultingTID = convertToInt(number);
		return;
	}
	public int getUSPConsultingTID(){
		return m_iUSPConsultingTID;
	}

	/**
	** Local Social Justice Groups & Christians taxonomy tid type LONG() 
	**/
	private int m_iUSPSocJustGrpsTID=-1;
	public void setUSPSocJustGrpsTID(int number){
		m_iUSPSocJustGrpsTID = number;
	}
	public void setUSPSocJustGrpsTID(String number){
		m_iUSPSocJustGrpsTID = convertToInt(number);
		return;
	}
	public int getUSPSocJustGrpsTID(){
		return m_iUSPSocJustGrpsTID;
	}

	/**
	** interested in Local Organizations taxonomy tid type LONG() 
	**/
	private int m_iUSPLocalOrgsTID=-1;
	public void setUSPLocalOrgsTID(int number){
		m_iUSPLocalOrgsTID = number;
	}
	public void setUSPLocalOrgsTID(String number){
		m_iUSPLocalOrgsTID = convertToInt(number);
		return;
	}
	public int getUSPLocalOrgsTID(){
		return m_iUSPLocalOrgsTID;
	}
	
	
	/**
	** race_p type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPRaceP=null;
	public void setUSPRaceP(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPRaceP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPRaceP = aszTemp;
			return;
		}
		m_aszUSPRaceP = aszTemp.substring(0,iLen);
	}
	public String getUSPRaceP(){
		if(m_aszUSPRaceP == null) return "";
		return m_aszUSPRaceP;
	}


	/**
	** age_range_p type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAgeRangeP=null;
	public void setUSPAgeRangeP(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAgeRangeP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAgeRangeP = aszTemp;
			return;
		}
		m_aszUSPAgeRangeP = aszTemp.substring(0,iLen);
	}
	public String getUSPAgeRangeP(){
		if(m_aszUSPAgeRangeP == null) return "";
		return m_aszUSPAgeRangeP;
	}


	/**
	** is the individual Christian? Yes/No - uprofile
	**/
	private String m_aszUSPChristianP=null;
	public void setUSPChristianP(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPChristianP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPChristianP = aszTemp;
			return;
		}
		m_aszUSPChristianP = aszTemp.substring(0,iLen);
	}
	public String getUSPChristianP(){
		if(m_aszUSPChristianP == null) return "";
		return m_aszUSPChristianP;
	}


	/**
	** Does the individual attend church? Yes/No - uprofile 
	**/
	private String m_aszUSPAttendChurchP=null;
	public void setUSPAttendChurchP(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAttendChurchP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAttendChurchP = aszTemp;
			return;
		}
		m_aszUSPAttendChurchP = aszTemp.substring(0,iLen);
	}
	public String getUSPAttendChurchP(){
		if(m_aszUSPAttendChurchP == null) return "";
		return m_aszUSPAttendChurchP;
	}

	


	/**
	** addr_other_province type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPAddrOtherProvince=null;
	public void setUSPAddrOtherProvince(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPAddrOtherProvince = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPAddrOtherProvince = aszTemp;
			return;
		}
		m_aszUSPAddrOtherProvince = aszTemp.substring(0,iLen);
	}
	public String getUSPAddrOtherProvince(){
		if(m_aszUSPAddrOtherProvince == null) return "";
		return m_aszUSPAddrOtherProvince;
	}


	/**
	** site use type - swich from volunteer to both vol&org --> will be used to map user to createorg page 
	**/
	private String m_aszUSPOrgCreation=null;
	public void setUSPOrgCreation(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOrgCreation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOrgCreation = aszTemp;
			return;
		}
		m_aszUSPOrgCreation = aszTemp.substring(0,iLen);
	}
	public String getUSPOrgCreation(){
		if(m_aszUSPOrgCreation == null) return "";
		return m_aszUSPOrgCreation;
	}


	/**
	** site use type - volunteer, organization, or both? - uprofile LEGACY
	**/
	private String m_aszUSPVolOrOpportunity=null;
	public void setUSPVolOrOpportunity(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolOrOpportunity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolOrOpportunity = aszTemp;
			return;
		}
		m_aszUSPVolOrOpportunity = aszTemp.substring(0,iLen);
	}
	public String getUSPVolOrOpportunity(){
		if(m_aszUSPVolOrOpportunity == null) return "";
		return m_aszUSPVolOrOpportunity;
	}
	/**
	** site use type - volunteer, organization, or church,...? - uprofile 
	**/
	private String m_aszUSPSiteUseType=null;
	public void setUSPSiteUseType(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPSiteUseType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPSiteUseType = aszTemp;
			return;
		}
		m_aszUSPSiteUseType = aszTemp.substring(0,iLen);
	}
	public String getUSPSiteUseType(){
		if(m_aszUSPSiteUseType == null) return "";
		return m_aszUSPSiteUseType;
	}


	/**
	** are you interested in volunteering? - uprofile taxonomy
	**/
	private String m_aszUSPVolunteer=null;
	public void setUSPVolunteer(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPVolunteer = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPVolunteer = aszTemp;
			return;
		}
		m_aszUSPVolunteer = aszTemp.substring(0,iLen);
	}
	public String getUSPVolunteer(){
		if(m_aszUSPVolunteer == null) return "";
		return m_aszUSPVolunteer;
	}

	/**
	** whether the user is interested in a city vision internship
	**/
	private boolean m_internshipInterest;
	public void setInternshipInterest(boolean interest){
		m_internshipInterest = interest;
	}
	public void setInternshipInterest(String number){
		m_internshipInterest = convertToInt(number) == 0 ? false : true;
	}
	public boolean getInternshipInterest(){
		return m_internshipInterest;
	}
	
	/**
	** Volunteer Directory Listing taxonomy tid type LONG()
	**/
	private int m_iUSPVolunteerTID=-1;
	public void setUSPVolunteerTID(int number){
		m_iUSPVolunteerTID = number;
	}
	public void setUSPVolunteerTID(String number){
		m_iUSPVolunteerTID = convertToInt(number);
		return;
	}
	public int getUSPVolunteerTID(){
		return m_iUSPVolunteerTID;
	}

	/**
	** subdom type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszUSPSubdom=null;
	public void setUSPSubdom(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPSubdom = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPSubdom = aszTemp;
			return;
		}
		m_aszUSPSubdom = aszTemp.substring(0,iLen);
	}
	public String getUSPSubdom(){
		if(m_aszUSPSubdom == null) return "";
		return m_aszUSPSubdom;
	}


	/**
	** store whether user is subscribed to listings or not 
	**/
	private int m_iUSPSubscribe=0;
	public void setUSPSubscribe(int number){
		m_iUSPSubscribe = number;
	}
	public void setUSPSubscribe(String number){
		m_iUSPSubscribe = convertToInt(number);
		return;
	}
	public int getUSPSubscribe(){
		return m_iUSPSubscribe;
	}
	

	/**
	** timezone type LONG() in table drupal.user 
	**/
	/**
	** person_number type LONG() in table userprofileinfo 
	**/
	private int m_iUSPTimezone=0;
	public void setUSPTimezone(int number){
		m_iUSPTimezone = number;
	}
	public void setUSPTimezone(String number){
		m_iUSPTimezone = convertToInt(number);
		return;
	}
	public int getUSPTimezone(){
		return m_iUSPTimezone;
	}

	/**
	** personality test page number
	**/
	private int m_iPersonalityPageNo=0;
	public void setUSPPersonalityPageNo(int number){
		m_iPersonalityPageNo = number;
	}
	public void setUSPPersonalityPageNo(String number){
		m_iPersonalityPageNo = convertToInt(number);
		return;
	}
	public int getUSPPersonalityPageNo(){
		return m_iPersonalityPageNo;
	}
	
	/**
	** personality test type Introvert
	**/
	private int m_iPersonalityI=0;
	public void setUSPPersonalityI(int number){
		m_iPersonalityI = number;
	}
	public void setUSPPersonalityI(String number){
		m_iPersonalityI = convertToInt(number);
		return;
	}
	public int getUSPPersonalityI(){
		return m_iPersonalityI;
	}

	/**
	** personality test type Extrovert
	**/
	private int m_iPersonalityE=0;
	public void setUSPPersonalityE(int number){
		m_iPersonalityE = number;
	}
	public void setUSPPersonalityE(String number){
		m_iPersonalityE = convertToInt(number);
		return;
	}
	public int getUSPPersonalityE(){
		return m_iPersonalityE;
	}

	/**
	** personality test type Sensing
	**/
	private int m_iPersonalityS=0;
	public void setUSPPersonalityS(int number){
		m_iPersonalityS = number;
	}
	public void setUSPPersonalityS(String number){
		m_iPersonalityS = convertToInt(number);
		return;
	}
	public int getUSPPersonalityS(){
		return m_iPersonalityS;
	}

	/**
	** personality test type Intuition (N)
	**/
	private int m_iPersonalityN=0;
	public void setUSPPersonalityN(int number){
		m_iPersonalityN = number;
	}
	public void setUSPPersonalityN(String number){
		m_iPersonalityN = convertToInt(number);
		return;
	}
	public int getUSPPersonalityN(){
		return m_iPersonalityN;
	}
	
	/**
	** personality test type Feeling (F)
	**/
	private int m_iPersonalityF=0;
	public void setUSPPersonalityF(int number){
		m_iPersonalityF = number;
	}
	public void setUSPPersonalityF(String number){
		m_iPersonalityF = convertToInt(number);
		return;
	}
	public int getUSPPersonalityF(){
		return m_iPersonalityF;
	}
	
	/**
	** personality test type Thinking (T)
	**/
	private int m_iPersonalityT=0;
	public void setUSPPersonalityT(int number){
		m_iPersonalityT = number;
	}
	public void setUSPPersonalityT(String number){
		m_iPersonalityT = convertToInt(number);
		return;
	}
	public int getUSPPersonalityT(){
		return m_iPersonalityT;
	}
	
	/**
	** personality test type Judging (J)
	**/
	private int m_iPersonalityJ=0;
	public void setUSPPersonalityJ(int number){
		m_iPersonalityJ = number;
	}
	public void setUSPPersonalityJ(String number){
		m_iPersonalityJ = convertToInt(number);
		return;
	}
	public int getUSPPersonalityJ(){
		return m_iPersonalityJ;
	}
	
	/**
	** personality test type Perceiving (P)
	**/
	private int m_iPersonalityP=0;
	public void setUSPPersonalityP(int number){
		m_iPersonalityP = number;
	}
	public void setUSPPersonalityP(String number){
		m_iPersonalityP = convertToInt(number);
		return;
	}
	public int getUSPPersonalityP(){
		return m_iPersonalityP;
	}

	/**
	** personality type
	**/
	private int m_iPersonalityType=0;
	public void setUSPPersonalityTID(int number){
		m_iPersonalityType = number;
	}
	public void setUSPPersonalityTID(String number){
		m_iPersonalityType = convertToInt(number);
		return;
	}
	public int getUSPPersonalityTID(){
		return m_iPersonalityType;
	}
	
	private String m_aszUSPPersonality=null;
	public void setUSPPersonality(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPersonality = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPersonality = aszTemp;
			return;
		}
		m_aszUSPPersonality = aszTemp.substring(0,iLen);
	}
	public String getUSPPersonality(){
		if(m_aszUSPPersonality == null) return "";
		return m_aszUSPPersonality;
	}
	
	private String m_aszUSPPersonalityEI=null;
	public void setUSPPersonalityEI(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPersonalityEI = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPersonalityEI = aszTemp;
			return;
		}
		m_aszUSPPersonalityEI = aszTemp.substring(0,iLen);
	}
	public String getUSPPersonalityEI(){
		if(m_aszUSPPersonalityEI == null) return "";
		return m_aszUSPPersonalityEI;
	}
	
	private String m_aszUSPPersonalitySN=null;
	public void setUSPPersonalitySN(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPersonalitySN = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPersonalitySN = aszTemp;
			return;
		}
		m_aszUSPPersonalitySN = aszTemp.substring(0,iLen);
	}
	public String getUSPPersonalitySN(){
		if(m_aszUSPPersonalitySN == null) return "";
		return m_aszUSPPersonalitySN;
	}
	
	private String m_aszUSPPersonalityFT=null;
	public void setUSPPersonalityFT(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPersonalityFT = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPersonalityFT = aszTemp;
			return;
		}
		m_aszUSPPersonalityFT = aszTemp.substring(0,iLen);
	}
	public String getUSPPersonalityFT(){
		if(m_aszUSPPersonalityFT == null) return "";
		return m_aszUSPPersonalityFT;
	}
	
	private String m_aszUSPPersonalityJP=null;
	public void setUSPPersonalityJP(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPPersonalityJP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPPersonalityJP = aszTemp;
			return;
		}
		m_aszUSPPersonalityJP = aszTemp.substring(0,iLen);
	}
	public String getUSPPersonalityJP(){
		if(m_aszUSPPersonalityJP == null) return "";
		return m_aszUSPPersonalityJP;
	}

	/**
	 *  flag for whether a user has published their personality to facebook
	 */
	private int m_iPersonalityPublished=0;
	public void setPersonalityPublished(int number){
		m_iPersonalityPublished = number;
	}
	public void setPersonalityPublished(String number){
		m_iPersonalityPublished = convertToInt(number);
		return;
	}
	public int getPersonalityPublished(){
		return m_iPersonalityPublished;
	}

	/**
	** facebook user_id for a user who has authenticated facebook app previously 
	**/
	/*
	private long m_iFacebookUID=0;
	public void setFacebookUID(int number){
		m_iFacebookUID = number;
	}
	public void setFacebookUID(String number){
		m_iFacebookUID = convertToLong(number);
		return;
	}
	public long getFacebookUID(){
		return m_iFacebookUID;
	}
	*/
	private String m_aszFacebookUID=null;
	public void setFacebookUID(String value){
		int iLen=64;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszFacebookUID = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszFacebookUID = aszTemp;
			return;
		}
		m_aszFacebookUID = aszTemp.substring(0,iLen);
	}
	public String getFacebookUID(){
		if(m_aszFacebookUID == null) return "";
		return m_aszFacebookUID;
	}
	
	private String m_aszLinkedInId=null;
	public void setLinkedInId(String value){
		int iLen=64;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszLinkedInId = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszLinkedInId = aszTemp;
			return;
		}
		m_aszLinkedInId = aszTemp.substring(0,iLen);
	}
	public String getLinkedInId(){
		if(m_aszLinkedInId == null) return "";
		return m_aszLinkedInId;
	}
	
	private String m_aszLinkedInAccessToken=null;
	public void setLinkedInAccessToken(String value) {
		m_aszLinkedInAccessToken=value;
	}
	public String getLinkedInAccessToken() {
		return m_aszLinkedInAccessToken;
	}
	
	private String m_aszLinkedInAccessSecret=null;
	public void setLinkedInAccessSecret(String value) {
		m_aszLinkedInAccessSecret=value;
	}
	public String getLinkedInAccessSecret() {
		return m_aszLinkedInAccessSecret;
	}
	
	/**
	** FBsecretkey 
	**/
	private String m_aszFBsecretkey=null;
	public void setFBsecretkey(String value){
		int iLen=32;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszFBsecretkey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszFBsecretkey = aszTemp;
			return;
		}
		m_aszFBsecretkey = aszTemp.substring(0,iLen);
	}
	public String getFBsecretkey(){
		if(m_aszFBsecretkey == null) return "";
		return m_aszFBsecretkey;
	}
	
	/**
	** FBapikey 
	**/
	private String m_aszFBapikey=null;
	public void setFBapikey(String value){
		int iLen=32;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszFBapikey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszFBapikey = aszTemp;
			return;
		}
		m_aszFBapikey = aszTemp.substring(0,iLen);
	}
	public String getFBapikey(){
		if(m_aszFBapikey == null) return "";
		return m_aszFBapikey;
	}
	
	/**
	** gigya socialize Provider that was passed
	**/
	private String m_aszProvider=null;
	public void setProvider(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszProvider = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProvider = aszTemp;
			return;
		}
		m_aszProvider = aszTemp.substring(0,iLen);
	}
	public String getProvider(){
		if(m_aszProvider == null) return "";
		return m_aszProvider;
	}

	/**
	** gigya socialize Signature that was passed
	**/
	private String m_aszSignature=null;
	public void setSignature(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSignature = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSignature = aszTemp;
			return;
		}
		m_aszSignature = aszTemp.substring(0,iLen);
	}
	public String getSignature(){
		if(m_aszSignature == null) return "";
		return m_aszSignature;
	}

	/**
	** gigya socialize Timestamp that was passed
	**/
	private String m_aszTimestamp=null;
	public void setTimestamp(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszTimestamp = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszTimestamp = aszTemp;
			return;
		}
		m_aszTimestamp = aszTemp.substring(0,iLen);
	}
	public String getTimestamp(){
		if(m_aszTimestamp == null) return "";
		return m_aszTimestamp;
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
	private String m_aszCurrentPortalName=null;
	public void setCurrentPortalName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCurrentPortalName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCurrentPortalName = aszTemp;
			return;
		}
		m_aszCurrentPortalName = aszTemp.substring(0,iLen);
	}
	public String getCurrentPortalName(){
		if(m_aszCurrentPortalName == null) return "";
		return m_aszCurrentPortalName;
	}
	
	/*
	 * multi-select Languages
	 */
	private int[] a_iUSPLanguagesArray=null;
	public void setUSPLanguagesArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPLanguagesArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPLanguagesArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPLanguagesArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPLanguagesArray(){
		if(a_iUSPLanguagesArray == null) {
			a_iUSPLanguagesArray=new int[0];
			return a_iUSPLanguagesArray;
		}
		return a_iUSPLanguagesArray;
	}
	
	private String m_aszUSPServiceAreas=null;
	public void setUSPServiceAreas(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPServiceAreas = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPServiceAreas = aszTemp;
			return;
		}
		m_aszUSPServiceAreas = aszTemp.substring(0,iLen);
	}
	public String getUSPServiceAreas(){
		if(m_aszUSPServiceAreas == null) return "";
		return m_aszUSPServiceAreas;
	}
	
	/*
	 * multi-select Service Areas
	 */
	private int[] a_iUSPServiceAreasArray=null;
	public void setUSPServiceAreasArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPServiceAreasArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPServiceAreasArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPServiceAreasArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPServiceAreasArray(){
		if(a_iUSPServiceAreasArray == null) {
			a_iUSPServiceAreasArray=new int[0];
			return a_iUSPServiceAreasArray;
		}
		return a_iUSPServiceAreasArray;
	}
	
	/*
	 * Array of all ServiceAreas by word in table organizationinfo
	 */
	private String[] a_aszUSPServiceAreasStringArray=null;
	public void setUSPServiceAreasStringArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszUSPServiceAreasStringArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszUSPServiceAreasStringArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszUSPServiceAreasStringArray = a_aszTemp;
			return;
		}
	}
	public String[] getUSPServiceAreasStringArray(){
		if(a_aszUSPServiceAreasStringArray == null) {
			a_aszUSPServiceAreasStringArray=new String[0];
			return a_aszUSPServiceAreasStringArray;
		}
		return a_aszUSPServiceAreasStringArray;
	}
	
	/*
	 * multi-selecta_iUSPAutoTaggingArray
	 */
	private int[][] a_objUSPAutoTaggingArray=null;
	public void setUSPAutoTaggingArray(int[][] values){
		int iLen=255;
		int[][] a_objTemp = values;
		a_iUSPServiceAreasArray = new int[a_objTemp.length];
		if(a_objTemp.length < 1){
			a_objUSPAutoTaggingArray = null;
			return;
		}
		if(a_objTemp.length < iLen + 1){
			a_objUSPAutoTaggingArray = a_objTemp;
			return;
		}
	}
	public int[][] getUSPAutoTaggingArray(){
		if(a_objUSPAutoTaggingArray == null) {
			a_objUSPAutoTaggingArray=new int[0][];
			return a_objUSPAutoTaggingArray;
		}
		return a_objUSPAutoTaggingArray;
	}
	
	private String m_aszUSPSkillTypes=null;
	public void setUSPSkillTypes(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPSkillTypes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPSkillTypes = aszTemp;
			return;
		}
		m_aszUSPSkillTypes = aszTemp.substring(0,iLen);
	}
	public String getUSPSkillTypes(){
		if(m_aszUSPSkillTypes == null) return "";
		return m_aszUSPSkillTypes;
	}
	
	/*
	 * multi-select Skills
	 */
	private int[] a_iUSPSkillTypesArray=null;
	public void setUSPSkillTypesArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPSkillTypesArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPSkillTypesArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPSkillTypesArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPSkillTypesArray(){
		if(a_iUSPSkillTypesArray == null) {
			a_iUSPSkillTypesArray=new int[0];
			return a_iUSPSkillTypesArray;
		}
		return a_iUSPSkillTypesArray;
	}
	
	private String m_aszUSPLookingFor=null;
	public void setUSPLookingFor(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPLookingFor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPLookingFor = aszTemp;
			return;
		}
		m_aszUSPLookingFor = aszTemp.substring(0,iLen);
	}
	public String getUSPLookingFor(){
		if(m_aszUSPLookingFor == null) return "";
		return m_aszUSPLookingFor;
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
	
	private String m_aszUSPSpiritualDev=null;
	public void setUSPSpiritualDev(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPSpiritualDev = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPSpiritualDev = aszTemp;
			return;
		}
		m_aszUSPSpiritualDev = aszTemp.substring(0,iLen);
	}
	public String getUSPSpiritualDev(){
		if(m_aszUSPSpiritualDev == null) return "";
		return m_aszUSPSpiritualDev;
	}
	
	/*
	 * multi-select Spiritual Development
	 */
	private int[] a_iUSPSpiritualDevArray=null;
	public void setUSPSpiritualDevArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPSpiritualDevArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPSpiritualDevArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPSpiritualDevArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPSpiritualDevArray(){
		if(a_iUSPSpiritualDevArray == null) {
			a_iUSPSpiritualDevArray=new int[0];
			return a_iUSPSpiritualDevArray;
		}
		return a_iUSPSpiritualDevArray;
	}
	

	private String m_aszUSPMinistryAreasCause=null;
	public void setUSPMinistryAreasCause(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPMinistryAreasCause = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPMinistryAreasCause = aszTemp;
			return;
		}
		m_aszUSPMinistryAreasCause = aszTemp.substring(0,iLen);
	}
	public String getUSPMinistryAreasCause(){
		if(m_aszUSPMinistryAreasCause == null) return "";
		return m_aszUSPMinistryAreasCause;
	}
	
	/*
	 * multi-select Ministry Areas
	 */
	private int[] a_iUSPMinistryAreasArray=null;
	public void setUSPMinistryAreasArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPMinistryAreasArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPMinistryAreasArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPMinistryAreasArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPMinistryAreasArray(){
		if(a_iUSPMinistryAreasArray == null) {
			a_iUSPMinistryAreasArray=new int[0];
			return a_iUSPMinistryAreasArray;
		}
		return a_iUSPMinistryAreasArray;
	}
	
	
	private String m_aszUSPGlobalIssues=null;
	public void setUSPGlobalIssues(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPGlobalIssues = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPGlobalIssues = aszTemp;
			return;
		}
		m_aszUSPGlobalIssues = aszTemp.substring(0,iLen);
	}
	public String getUSPGlobalIssues(){
		if(m_aszUSPGlobalIssues == null) return "";
		return m_aszUSPGlobalIssues;
	}
	
	/*
	 * multi-select Global Issues
	 */
	private int[] a_iUSPGlobalIssuesArray=null;
	public void setUSPGlobalIssuesArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPGlobalIssuesArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPGlobalIssuesArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPGlobalIssuesArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPGlobalIssuesArray(){
		if(a_iUSPGlobalIssuesArray == null) {
			a_iUSPGlobalIssuesArray=new int[0];
			return a_iUSPGlobalIssuesArray;
		}
		return a_iUSPGlobalIssuesArray;
	}
	
	
	private String m_aszUSPOrganizationalDev=null;
	public void setUSPOrganizationalDev(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOrganizationalDev = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOrganizationalDev = aszTemp;
			return;
		}
		m_aszUSPOrganizationalDev = aszTemp.substring(0,iLen);
	}
	public String getUSPOrganizationalDev(){
		if(m_aszUSPOrganizationalDev == null) return "";
		return m_aszUSPOrganizationalDev;
	}
	
	/*
	 * multi-select Organizational Development
	 */
	private int[] a_iUSPOrganizationalDevArray=null;
	public void setUSPOrganizationalDevArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPOrganizationalDevArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPOrganizationalDevArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPOrganizationalDevArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPOrganizationalDevArray(){
		if(a_iUSPOrganizationalDevArray == null) {
			a_iUSPOrganizationalDevArray=new int[0];
			return a_iUSPOrganizationalDevArray;
		}
		return a_iUSPOrganizationalDevArray;
	}
	
	
	private String m_aszUSPReconciliation=null;
	public void setUSPReconciliation(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPReconciliation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPReconciliation = aszTemp;
			return;
		}
		m_aszUSPReconciliation = aszTemp.substring(0,iLen);
	}
	public String getUSPReconciliation(){
		if(m_aszUSPReconciliation == null) return "";
		return m_aszUSPReconciliation;
	}
	
	/*
	 * multi-select Reconciliation & Culture
	 */
	private int[] a_iUSPReconciliationArray=null;
	public void setUSPReconciliationArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iUSPReconciliationArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iUSPReconciliationArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iUSPReconciliationArray = a_iTemp;
			return;
		}
	}
	public int[] getUSPReconciliationArray(){
		if(a_iUSPReconciliationArray == null) {
			a_iUSPReconciliationArray=new int[0];
			return a_iUSPReconciliationArray;
		}
		return a_iUSPReconciliationArray;
	}
	
	
	private String m_aszUSPOtherLearningInterests=null;
	public void setUSPOtherLearningInterests(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherLearningInterests = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherLearningInterests = aszTemp;
			return;
		}
		m_aszUSPOtherLearningInterests = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherLearningInterests(){
		if(m_aszUSPOtherLearningInterests == null) return "";
		return m_aszUSPOtherLearningInterests;
	}
	
	private String m_aszUSPOtherPassions=null;
	public void setUSPOtherPassions(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherPassions = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherPassions = aszTemp;
			return;
		}
		m_aszUSPOtherPassions = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherPassions(){
		if(m_aszUSPOtherPassions == null) return "";
		return m_aszUSPOtherPassions;
	}
	
	private String m_aszUSPOtherSkills=null;
	public void setUSPOtherSkills(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUSPOtherSkills = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUSPOtherSkills = aszTemp;
			return;
		}
		m_aszUSPOtherSkills = aszTemp.substring(0,iLen);
	}
	public String getUSPOtherSkills(){
		if(m_aszUSPOtherSkills == null) return "";
		return m_aszUSPOtherSkills;
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



	//===========> End Code Generated Methods For Table userprofileinfo 
	//===========> End Code Generated Methods For Table userprofileinfo 
	//===========> End Code Generated Methods For Table userprofileinfo 


	/**
	** usermail - m_iIsParentOrgAdmin for opp. 0=no/1=yes
	**/
	private int m_iIsParentOrgAdmin=0;
	public void setIsParentOrgAdmin(int number){
		m_iIsParentOrgAdmin = number;
	}
	public void setIsParentOrgAdmin(String number){
		m_iIsParentOrgAdmin = convertToInt(number);
		return;
	}
	public int getIsParentOrgAdmin(){
		return m_iIsParentOrgAdmin;
	}

	/**
	** usermail - does the given user have access to EDIT the given OPPORTUNITY? 0=no/1=yes
	**/
	private int m_iIsOPPContact=0;
	public void setIsOPPContact(int number){
		m_iIsOPPContact = number;
	}
	public void setIsOPPContact(String number){
		m_iIsOPPContact = convertToInt(number);
		return;
	}
	public int getIsOPPContact(){
		return m_iIsOPPContact;
	}

	/**
	** usermail - m_iIsPrimaryVolunteerContact for opp. 0=no/1=yes
	**/
	private int m_iIsPrimaryVolunteerContact=0;
	public void setIsPrimaryVolunteerContact(int number){
		m_iIsPrimaryVolunteerContact = number;
	}
	public void setIsPrimaryVolunteerContact(String number){
		m_iIsPrimaryVolunteerContact = convertToInt(number);
		return;
	}
	public int getIsPrimaryVolunteerContact(){
		return m_iIsPrimaryVolunteerContact;
	}

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
	** newsletter - used for phplist subsription
	**/
	private int m_iNewsletter=0;
	public void setNewsletter(int number){
		m_iNewsletter = number;
	}
	public void setNewsletter(String number){
		m_iNewsletter = convertToInt(number);
		return;
	}
	public int getNewsletter(){
		return m_iNewsletter;
	}


	/**
	** system info - used for phplist subscription - includes user agent, referer, remote address
	**/
	private String m_aszSysInfo=null;

	public void setSysInfo(String value){
		if(null == value){
			m_aszSysInfo = null;
			return;
		}
		m_aszSysInfo = value.trim();
	}
	public String getSysInfo(){
		if(m_aszSysInfo == null) return "";
		return m_aszSysInfo;
	}


	/**
	** IP address - used for phplist subscription
	**/
	private String m_aszIP=null;
	public void setIP(String value){
		int iLen=25;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszIP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszIP = aszTemp;
			return;
		}
		m_aszIP = aszTemp.substring(0,iLen);
	}
	public String getIP(){
		if(m_aszIP == null) return "";
		return m_aszIP;
	}


	/**
	** 
	**/
	private String m_facebookAccessToken=null;
	public void setFacebookAccessToken(String value){
		m_facebookAccessToken = value;
	}
	public String getFacebookAccessToken() {
		return m_facebookAccessToken;
	}
	
	private int m_importExternalProfile=1;
	public void setImportExternalProfile(int value) {
		m_importExternalProfile = value;
	}
	public int getImportExternalProfile() {
		return m_importExternalProfile;
	}
}