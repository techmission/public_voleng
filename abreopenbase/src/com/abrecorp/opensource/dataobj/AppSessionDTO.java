package com.abrecorp.opensource.dataobj;

/**
* To Hold Session Data for User Interface Navigation
*/

import java.io.Serializable;

public class AppSessionDTO extends BaseInfoObj implements Serializable, Cloneable {

	public static final String TOKEN_IWANTTOHELP="IWANTTOHELP";
	public static final String TOKEN_IVOLHELP="IVOLHELP";
	public static final String TOKEN_IVOLHELPSIGNIN="IVOLTOHELPSIGNIN";
	public static final String TOKEN_IVOLFORGOTPWD="IVOLFORGOTPWD";
	public static final String TOKEN_PARTNERHELP="PARTNERHELP";
	public static final String TOKEN_PARTNERHELPSIGNIN="PARTNERHELPSIGNIN";
	public static final String TOKEN_PARTNERFORGOTPWD="PARTNERFORGOTPWD";

	public static final String TOKEN_EDITINDIV="EDITINDIVACCNT";
	public static final String TOKEN_VOLDASHBOARD="VOLUNTEERDASHBOARD";
	public static final String TOKEN_ORGFROMVOL="ORGFROMVOL";
	public static final String TOKEN_CREATEORG="CREATEORG";
	public static final String TOKEN_SHOWCONTACTS="SHOWCONTACTS";
	public static final String TOKEN_ADDORGCONTACT="ADDORGCONTACT";
	public static final String TOKEN_EDITORG="EDITORG";
	public static final String TOKEN_ORGMNGMNT="ORGMANAGEMENT";
	public static final String TOKEN_CREATEOPPORT="CREATEOPPORT";
	public static final String TOKEN_CREATEOPPORTDISASTER="CREATEOPPORTDISASTER";
	public static final String TOKEN_ORGVIEWORG="ORGVIEWORG";
	public static final String TOKEN_ORGVIEWOPP="ORGVIEWOPP";
	public static final String TOKEN_ORGMANAGEMENT="ORGMANAGEMENT";
	public static final String TOKEN_ASSOCMANAGEMENT="ASSOCMANAGEMENT";

	public static final String TOKEN_SHOWOPPCONTACTS="SHOWOPPCONTACTS";
	public static final String TOKEN_ADDOPPCONTACT="ADDOPPCONTACT";
	public static final String TOKEN_SHOWORGADMINS="SHOWORGADMINS";
	public static final String TOKEN_ADDORGADMIN="ADDORGADMIN";
	
	public static final String TOKEN_CVINTERNAPPLIC="CVINTERNAPPLIC";
	public static final String TOKEN_CVINTERNAPPLIC2="CVINTERNAPPLIC2";
	public static final String TOKEN_CVINTERNAPPLIC3="CVINTERNAPPLIC3";
	public static final String TOKEN_RESUME_POST="CVINTERNRESUMEPOST";
	public static final String TOKEN_CVINTERN_NATLASSOC_MANAGE="CVINTERNNATLASSOCMANAGE";
	public static final String TOKEN_CVINTERN_DQ="CVINTERNDQ";

	public static final String TOKEN_DOWNLOADRESUME="DOWNLOADRESUME";
	public static final String TOKEN_UPLOADRESUME="UPLOADRESUME";

	public static final String TOKEN_SRCHINTERNAPPLICANTS="SRCHINTERNAPPLICANTS";

	public static final String TOKEN_REDIRURBMINDASH="urbmindashboard";
	public static final String TOKEN_REDIR_PORTAL_OPPORTUNITIES="portalselectopportunities";
	public static final String TOKEN_REDIR_CHURCH_PORTAL_OPPORTUNITIES="church_portalselectopportunities";
	public static final String TOKEN_REDIR_PORTAL_ORGMANAGEMENT="portalorgmanagement";
	/**
	* Constructor
	*/
    public AppSessionDTO(){}

    /**
    * public clone method
    */
    public Object clone(){
        try{
        	AppSessionDTO e1 = (AppSessionDTO) super.clone();
            return e1;
        } catch (CloneNotSupportedException exp){
            return null;
        }
    }


	/**
	** Token Key used for processing type 
	**/
	private String m_aszTokenKey=null;
	public void setTokenKey(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszTokenKey = null;
			return;
		}
		m_aszTokenKey = aszTemp.trim();
	}
	public String getTokenKey(){
		if(m_aszTokenKey == null) return "";
		return m_aszTokenKey;
	}


    //===== START Opportunity related saved data ===>
    //===== START Opportunity related saved data ===>
    //===== START Opportunity related saved data ===>

    /**
	* opportunity nid number
	*/
	private int m_iOppNID=0;
	public void setOppNID(int type){
		m_iOppNID=type;
	}
	public void setOppNID(String number){
		m_iOppNID = convertToInt(number);
	}
	public int getOppNID(){
		return m_iOppNID;
	}

    /**
	* organization nid number
	*/
	private int m_iOrgNID=0;
	public void setOrgNID(int type){
		m_iOrgNID=type;
	}
	public void setOrgNID(String number){
		m_iOrgNID = convertToInt(number);
	}
	public int getOrgNID(){
		return m_iOrgNID;
	}

    /**
	* m_iUID number
	*/
	private int m_iUID=0;
	public void setUID(int type){
		m_iUID=type;
	}
	public void setUID(String number){
		m_iUID = convertToInt(number);
	}
	public int getUID(){
		return m_iUID;
	}


    /**
	* opportunity id number
	*/
	private int m_iOppIdNum=0;
	public void setOppIdNum(int type){
		m_iOppIdNum=type;
	}
	public void setOppIdNum(String number){
		m_iOppIdNum = convertToInt(number);
	}
	public int getOppIdNum(){
		return m_iOppIdNum;
	}

    /**
	* organization id number
	*/
	private int m_iOrgIdNum=0;
	public void setOrgIdNum(int type){
		m_iOrgIdNum=type;
	}
	public void setOrgIdNum(String number){
		m_iOrgIdNum = convertToInt(number);
	}
	public int getOrgIdNum(){
		return m_iOrgIdNum;
	}

	

	/**
	** Subdomain used for processing type 
	**/
	private String m_aszSubdomain=null;
	public void setSubdomain(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSubdomain = null;
			return;
		}
		m_aszSubdomain = aszTemp.trim();
	}
	public String getSubdomain(){
		if(m_aszSubdomain == null) return "";
		return m_aszSubdomain;
	}

	/**
	** SiteEmail used for processing type 
	**/
	private String m_aszSiteEmail=null;
	public void setSiteEmail(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszSiteEmail = null;
			return;
		}
		m_aszSiteEmail = aszTemp.trim();
	}
	public String getSiteEmail(){
		if(m_aszSiteEmail == null) return "";
		return m_aszSiteEmail;
	}

    //===== END Opportunity related saved data ===>
    //===== END Opportunity related saved data ===>
    //===== END Opportunity related saved data ===>

}