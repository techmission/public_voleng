package com.abrecorp.opensource.dataobj;

/**
* Email Messages DataStore Class
* Make it simple to send an email text message etc...
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import java.io.*;
import java.util.Map;

import org.apache.struts.upload.FormFile;

public class EmailInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	public static final int SINGLE_USER_TEXT_MESSAGE=101;
	public static final int ATTACH_FILE=102;

	public static final int LOADBY_VOLUNTEER=110;
	public static final int LOADBY_VOLUNTEER_MAX=111;
	public static final int LOADBY_ORGANIZATION=121;
	public static final int LOADBY_OPPORTUNITY=122;
	
	public static final int TOKEN_PLAINTXT=0;
	public static final int TOKEN_HTML=1;

	/**
	* constructor
	*/
	public EmailInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			EmailInfoDTO el = (EmailInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}

	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===<



	//===========> START EMAIL Message Object 
	//===========> START EMAIL Message Object 
	//===========> START EMAIL Message Object 

	/**
	* Email MESSAGE TYPE
	*/
	private int EmailMessageType=SINGLE_USER_TEXT_MESSAGE;
	public void setEmailMessageType(int type){
		EmailMessageType=type;
	}
	public void setEmailMessageType(String number){
		EmailMessageType = convertToInt(number);
	}
	public int getEmailMessageType(){
		return EmailMessageType;
	}

	/**
	* Email SMTP authorization required setting
	*/
	private boolean m_bSMTPAuthRequired=false;
	public void setSMTPAuthRequired(boolean date){
		m_bSMTPAuthRequired=date;
	}
	public boolean getSMTPAuthRequired(){
		return m_bSMTPAuthRequired;
	}

	/**
	* Email debug setting
	*/
	private boolean m_bDebug=false;
	public void setEmailDebug(boolean date){
		m_bDebug=date;
	}
	public boolean getEmailDebug(){
		return m_bDebug;
	}

	/**
	* Email SMTP Server Name
	*/
    private String m_EmailSmtpServerName=null;
	public void setEmailSMTPServerName(String date){
		if(date == null){
			m_EmailSmtpServerName=null;
			return ;
		}
		m_EmailSmtpServerName=date.trim();
	}
	public String getEmailSMTPServerName(){
		if(null == m_EmailSmtpServerName) return "";
		return m_EmailSmtpServerName;
	}

	/**
	* Email SMTP Password
	*/
    private String m_smtpFromUserPassword=null;
	public void setEmailFromUserPassword(String date){
		if(date == null){
			m_smtpFromUserPassword=null;
			return ;
		}
		m_smtpFromUserPassword=date.trim();
	}
	public String getEmailFromUserPassword(){
		if(null == m_smtpFromUserPassword) return "";
		return m_smtpFromUserPassword;
	}

	/**
	* Email SMTP Authenticate UserName
	*/
    private String m_smtpUserName=null;
	public void setSMTPUserName(String date){
		if(date == null){
			m_smtpUserName=null;
			return ;
		}
		m_smtpUserName=date.trim();
	}
	public String getSMTPUserName(){
		if(null == m_smtpUserName) return "";
		return m_smtpUserName;
	}



	/**
	*  UserName receiving email
	*/
    private String m_receivingUserName=null;
	public void setEmailReceivingUserName(String date){
		if(date == null){
			m_receivingUserName=null;
			return ;
		}
		m_receivingUserName=date.trim();
	}
	public String getEmailReceivingUserName(){
		if(null == m_receivingUserName) return "";
		return m_receivingUserName;
	}

	/**
	*  UserName sending email
	*/
    private String m_sendingUserName=null;
	public void setEmailSendingUserName(String date){
		if(date == null){
			m_sendingUserName=null;
			return ;
		}
		m_sendingUserName=date.trim();
	}
	public String getEmailSendingUserName(){
		if(null == m_sendingUserName) return "";
		return m_sendingUserName;
	}



	/**
	* Email Body Text type TEXT() in table emailinfo
	*/
    private String m_EmailBodyMessage=null;
	public void setEmailBodyText(String value){
		if(value == null){
			m_EmailBodyMessage=null;
			return ;
		}
		m_EmailBodyMessage=value.trim();
	}
	public String getEmailBodyText(){
		if(null == m_EmailBodyMessage) return "";
		return m_EmailBodyMessage;
	}

	/**
	* Email Body Text Intro type TEXT() in table emailinfo
	*/
    private String m_EmailBodyMessageIntro=null;
	public void setEmailBodyTextIntro(String value){
		if(value == null){
			m_EmailBodyMessageIntro=null;
			return ;
		}
		m_EmailBodyMessageIntro=value.trim();
	}
	public String getEmailBodyTextIntro(){
		if(null == m_EmailBodyMessageIntro) return "";
		return m_EmailBodyMessageIntro;
	}
	
	
	/**
	* Email Body Text Closing text
	*/
    private String m_EmailBodyMessageClosing=null;
	public void setEmailBodyTextClosing(String value){
		if(value == null){
			m_EmailBodyMessageClosing=null;
			return ;
		}
		m_EmailBodyMessageClosing=value.trim();
	}
	public String getEmailBodyTextClosing(){
		if(null == m_EmailBodyMessageClosing) return "";
		return m_EmailBodyMessageClosing;
	}

	
	/**
	* Email Body Text Resume text
	*/
    private String m_EmailBodyMessageRes=null;
	public void setEmailBodyTextRes(String value){
		if(value == null){
			m_EmailBodyMessageRes=null;
			return ;
		}
		m_EmailBodyMessageRes=value.trim();
	}
	public String getEmailBodyTextRes(){
		if(null == m_EmailBodyMessageRes) return "";
		return m_EmailBodyMessageRes;
	}

	/**
	** m_aszEmailResumeFilePath type TEXT() in table userprofileinfo 
	**/
	private String m_aszEmailResumeFilePath=null;

	public void setEmailResumeFilePath(String value){
		if(null == value){
			m_aszEmailResumeFilePath = null;
			return;
		}
		m_aszEmailResumeFilePath = value.trim();
	}
	public String getEmailResumeFilePath(){
		if(m_aszEmailResumeFilePath == null) return "";
		return m_aszEmailResumeFilePath;
	}

	/**
	* Email EmailVolFN
	*/
    private String m_EmailVolFN=null;
	public void setEmailVolFN(String date){
		if(date == null){
			m_EmailVolFN=null;
			return ;
		}
		m_EmailVolFN=date.trim();
	}
	public String getEmailVolFN(){
		if(null == m_EmailVolFN) return "";
		return m_EmailVolFN;
	}
	/**
	* Email EmailVolLN
	*/
    private String m_EmailVolLN=null;
	public void setEmailVolLN(String date){
		if(date == null){
			m_EmailVolLN=null;
			return ;
		}
		m_EmailVolLN=date.trim();
	}
	public String getEmailVolLN(){
		if(null == m_EmailVolLN) return "";
		return m_EmailVolLN;
	}
	/**
	* Email EmailVolPhone1
	*/
    private String m_EmailVolPhone1=null;
	public void setEmailVolPhone1(String date){
		if(date == null){
			m_EmailVolPhone1=null;
			return ;
		}
		m_EmailVolPhone1=date.trim();
	}
	public String getEmailVolPhone1(){
		if(null == m_EmailVolPhone1) return "";
		return m_EmailVolPhone1;
	}

    private String m_EmailVolPhone2=null;
	public void setEmailVolPhone2(String date){
		if(date == null){
			m_EmailVolPhone2=null;
			return ;
		}
		m_EmailVolPhone2=date.trim();
	}
	public String getEmailVolPhone2(){
		if(null == m_EmailVolPhone2) return "";
		return m_EmailVolPhone2;
	}

	private String m_aszEmailPhone1Type=null;
	public void setEmailVolPhone1Type(String aszPhoneType) {
		int iLen=20;
		String aszTemp = aszPhoneType;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailPhone1Type = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailPhone1Type = aszTemp;
			return;
		}
		m_aszEmailPhone1Type = aszTemp.substring(0,iLen);
	}
	public String getEmailVolPhone1Type(){
		if(m_aszEmailPhone1Type == null) return "";
		return m_aszEmailPhone1Type;
	}
	private String m_aszEmailPhone2Type=null;
	public void setEmailVolPhone2Type(String aszPhoneType) {
		int iLen=20;
		String aszTemp = aszPhoneType;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailPhone2Type = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailPhone2Type = aszTemp;
			return;
		}
		m_aszEmailPhone2Type = aszTemp.substring(0,iLen);
	}
	public String getEmailVolPhone2Type(){
		if(m_aszEmailPhone2Type == null) return "";
		return m_aszEmailPhone2Type;
	}
	/**
	* Email EmailVolChris
	*/
    private String m_EmailVolChris=null;
	public void setEmailVolChris(String date){
		if(date == null){
			m_EmailVolChris=null;
			return ;
		}
		m_EmailVolChris=date.trim();
	}
	public String getEmailVolChris(){
		if(null == m_EmailVolChris) return "";
		return m_EmailVolChris;
	}
	
	/**
	* Email EmailBodyTextVolSkills
	*/
    private String m_EmailVolSkills=null;
	public void setEmailVolSkills(String date){
		if(date == null){
			m_EmailVolSkills=null;
			return ;
		}
		m_EmailVolSkills=date.trim();
	}
	public String getEmailVolSkills(){
		if(null == m_EmailVolSkills) return "";
		return m_EmailVolSkills;
	}
	/**
	* Email EmailBodyTextVolSkills2
	*/
    private String m_EmailVolSkills2=null;
	public void setEmailVolSkills2(String date){
		if(date == null){
			m_EmailVolSkills2=null;
			return ;
		}
		m_EmailVolSkills2=date.trim();
	}
	public String getEmailVolSkills2(){
		if(null == m_EmailVolSkills2) return "";
		return m_EmailVolSkills2;
	}
	/**
	* Email EmailBodyTextVolSkills
	*/
    private String m_EmailVolSkills3=null;
	public void setEmailVolSkills3(String date){
		if(date == null){
			m_EmailVolSkills3=null;
			return ;
		}
		m_EmailVolSkills3=date.trim();
	}
	public String getEmailVolSkills3(){
		if(null == m_EmailVolSkills3) return "";
		return m_EmailVolSkills3;
	}
	/*
	 * multi-select Skills
	 */
	private int[] a_iEmailVolSkillTypesTIDArray=null;
	public void setEmailVolSkillTypesTIDArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iEmailVolSkillTypesTIDArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iEmailVolSkillTypesTIDArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iEmailVolSkillTypesTIDArray = a_iTemp;
			return;
		}
	}
	public int[] getEmailVolSkillTypesTIDArray(){
		if(a_iEmailVolSkillTypesTIDArray == null) {
			a_iEmailVolSkillTypesTIDArray=new int[0];
			return a_iEmailVolSkillTypesTIDArray;
		}
		return a_iEmailVolSkillTypesTIDArray;
	}
	private String[] a_aszEmailVolSkillTypesArray;
	private String m_aszEmailVolSkillTypes=null;
	public void setEmailVolSkillTypes(String value){
		int iLen=1000;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailVolSkillTypes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailVolSkillTypes = aszTemp;
			return;
		}
		m_aszEmailVolSkillTypes = aszTemp.substring(0,iLen);
	}
	public String getEmailVolSkillTypes(){
		if(m_aszEmailVolSkillTypes == null) return "";
		return m_aszEmailVolSkillTypes;
	}
	/**
	* Email m_EmailRailsSkills
	*/
	private String m_EmailRailsSkills=null;
	public void setEmailRailsSkills(String values){
		int iLen=255;
		String a_aszTemp = values;
		m_EmailRailsSkills = values;
		if(a_aszTemp.length() < 1){
			m_EmailRailsSkills = null;
			return;
		}
		if(a_aszTemp.length() < iLen + 1){
			m_EmailRailsSkills = a_aszTemp;
			return;
		}
	}
	public String getEmailRailsSkills(){
		if(m_EmailRailsSkills == null) return "";
		return m_EmailRailsSkills;
	}
	/**
	* Email m_EmailRailsSkills
	*/
	private String[] m_EmailRailsSkillsArray=null;
	public void setEmailRailsSkillsArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		m_EmailRailsSkillsArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			m_EmailRailsSkillsArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			m_EmailRailsSkillsArray = a_aszTemp;
			return;
		}
	}
	public String[] getEmailRailsSkillsArray(){
		if(m_EmailRailsSkillsArray == null) {
			m_EmailRailsSkillsArray=new String[0];
			return m_EmailRailsSkillsArray;
		}
		return m_EmailRailsSkillsArray;
	}

	/**
	* Email Subject Text
	*/
    private String m_EmailSubjectText=null;
	public void setEmailSubjectText(String date){
		if(date == null){
			m_EmailSubjectText=null;
			return ;
		}
		m_EmailSubjectText=date.trim();
	}
	public String getEmailSubjectText(){
		if(null == m_EmailSubjectText) return "";
		return m_EmailSubjectText;
	}


	/**
	* Email To type TEXT() in table emailinfo
	*/
    private String m_smtpToUsers=null;
	public void setEmailToUsers(String value){
		if(value == null){
			m_smtpToUsers=null;
			return ;
		}
		m_smtpToUsers=value.trim();
	}
	public String getEmailToUsers(){
		if(null == m_smtpToUsers) return "";
		return m_smtpToUsers;
	}
	

	/**
	* Email m_smtpBCC type TEXT() in table emailinfo
	*/
    private String m_smtpBCC=null;
	public void setEmailBCCAddress(String value){
		if(value == null){
			m_smtpBCC=null;
			return ;
		}
		m_smtpBCC=value.trim();
	}
	public String getEmailBCCAddress(){
		if(null == m_smtpBCC) return "";
		return m_smtpBCC;
	}
	
	

	/**
	* Email Message From
	*/
    private String m_smtpFromUser=null;
	public void setEmailFromUser(String date){
		if(date == null){
			m_smtpFromUser=null;
			return ;
		}
		m_smtpFromUser=date.trim();
	}
	public String getEmailFromUser(){
		if(null == m_smtpFromUser) return "";
		return m_smtpFromUser;
	}


	/**
	* Email Message From Name
	*/
    private String m_smtpFromUserName=null;
	public void setEmailFromUserName(String date){
		if(date == null){
			m_smtpFromUserName=null;
			return ;
		}
		m_smtpFromUserName=date.trim();
	}
	public String getEmailFromUserName(){
		if(null == m_smtpFromUserName) return "";
		return m_smtpFromUserName;
	}
	/**


	/**
	* Site Email Address
	*/
    private String m_smtpSiteEmail=null;
	public void setEmailSiteEmail(String date){
		if(date == null){
			m_smtpSiteEmail=null;
			return ;
		}
		m_smtpSiteEmail=date.trim();
	}
	public String getEmailSiteEmail(){
		if(null == m_smtpSiteEmail) return "";
		return m_smtpSiteEmail;
	}

	/**
	* Email Message To
	*/
    private String m_smtpToUser=null;
	public void setEmailToUser(String date){
		if(date == null){
			m_smtpToUser=null;
			return ;
		}
		m_smtpToUser=date.trim();
	}
	public String getEmailToUser(){
		if(null == m_smtpToUser) return "";
		return m_smtpToUser;
	}


	/**
	* Email Message Org Contact - First Name
	*/
    private String m_EmailContactFN=null;
	public void setEmailContactFN(String date){
		if(date == null){
			m_EmailContactFN=null;
			return ;
		}
		m_EmailContactFN=date.trim();
	}
	public String getEmailContactFN(){
		if(null == m_EmailContactFN) return "";
		return m_EmailContactFN;
	}


	/**
	* Email Message Org Contact - Last Name
	*/
    private String m_EmailContactLN=null;
	public void setEmailContactLN(String date){
		if(date == null){
			m_EmailContactLN=null;
			return ;
		}
		m_EmailContactLN=date.trim();
	}
	public String getEmailContactLN(){
		if(null == m_EmailContactLN) return "";
		return m_EmailContactLN;
	}


	/**
	* Org Email Address
	*/
    private String m_EmailOrg=null;
	public void setEmailOrg(String date){
		if(date == null){
			m_EmailOrg=null;
			return ;
		}
		m_EmailOrg=date.trim();
	}
	public String getEmailOrg(){
		if(null == m_EmailOrg) return "";
		return m_EmailOrg;
	}


	/**
	* Email Message Opportunity Title
	*/
    private String m_EmailOppName=null;
	public void setEmailOppName(String date){
		if(date == null){
			m_EmailOppName=null;
			return ;
		}
		m_EmailOppName=date.trim();
	}
	public String getEmailOppName(){
		if(null == m_EmailOppName) return "";
		return m_EmailOppName;
	}


	/**
	* Email Message Organization Name
	*/
    private String m_EmailOrgName=null;
	public void setEmailOrgName(String date){
		if(date == null){
			m_EmailOrgName=null;
			return ;
		}
		m_EmailOrgName=date.trim();
	}
	public String getEmailOrgName(){
		if(null == m_EmailOrgName) return "";
		return m_EmailOrgName;
	}


	/**
	* Email Message Organization contact address
	*/
    private String m_EmailOrgAddr1=null;
	public void setEmailOrgAddr1(String date){
		if(date == null){
			m_EmailOrgAddr1=null;
			return ;
		}
		m_EmailOrgAddr1=date.trim();
	}
	public String getEmailOrgAddr1(){
		if(null == m_EmailOrgAddr1) return "";
		return m_EmailOrgAddr1;
	}


	/**
	* Email Message Organization contact city
	*/
    private String m_EmailOrgCity=null;
	public void setEmailOrgCity(String date){
		if(date == null){
			m_EmailOrgCity=null;
			return ;
		}
		m_EmailOrgCity=date.trim();
	}
	public String getEmailOrgCity(){
		if(null == m_EmailOrgCity) return "";
		return m_EmailOrgCity;
	}


	/**
	* Email Message Organization contact State
	*/
    private String m_EmailOrgState=null;
	public void setEmailOrgState(String date){
		if(date == null){
			m_EmailOrgState=null;
			return ;
		}
		m_EmailOrgState=date.trim();
	}
	public String getEmailOrgState(){
		if(null == m_EmailOrgState) return "";
		return m_EmailOrgState;
	}


	/**
	* Email Message Organization contact Other Province
	*/
    private String m_EmailOrgProv=null;
	public void setEmailOrgProv(String date){
		if(date == null){
			m_EmailOrgProv=null;
			return ;
		}
		m_EmailOrgProv=date.trim();
	}
	public String getEmailOrgProv(){
		if(null == m_EmailOrgProv) return "";
		return m_EmailOrgProv;
	}


	/**
	* Email Message Organization contact Country
	*/
    private String m_EmailOrgCountry=null;
	public void setEmailOrgCountry(String date){
		if(date == null){
			m_EmailOrgCountry=null;
			return ;
		}
		m_EmailOrgCountry=date.trim();
	}
	public String getEmailOrgCountry(){
		if(null == m_EmailOrgCountry) return "";
		return m_EmailOrgCountry;
	}


	/**
	* Email Message Organization contact Phone
	*/
    private String m_EmailOrgPhone=null;
	public void setEmailOrgPhone(String date){
		if(date == null){
			m_EmailOrgPhone=null;
			return ;
		}
		m_EmailOrgPhone=date.trim();
	}
	public String getEmailOrgPhone(){
		if(null == m_EmailOrgPhone) return "";
		return m_EmailOrgPhone;
	}


	/**
	* Email Message Organization contact Web
	*/
    private String m_EmailOrgWeb=null;
	public void setEmailOrgWeb(String date){
		if(date == null){
			m_EmailOrgWeb=null;
			return ;
		}
		m_EmailOrgWeb=date.trim();
	}
	public String getEmailOrgWeb(){
		if(null == m_EmailOrgWeb) return "";
		return m_EmailOrgWeb;
	}



	/**
	* Email STM References Text type TEXT() in table emailinfo
	*/
    private String m_EmailSTMReferencesMessage=null;
	public void setEmailSTMReferencesText(String value){
		if(value == null){
			m_EmailSTMReferencesMessage=null;
			return ;
		}
		m_EmailSTMReferencesMessage=value.trim();
	}
	public String getEmailSTMReferencesText(){
		if(null == m_EmailSTMReferencesMessage) return "";
		return m_EmailSTMReferencesMessage;
	}


	/**
	* Email Message Opportunity background check required
	*/
    private String m_EmailOppBkgrnd=null;
	public void setEmailOppBkgrnd(String date){
		if(date == null){
			m_EmailOppBkgrnd=null;
			return ;
		}
		m_EmailOppBkgrnd=date.trim();
	}
	public String getEmailOppBkgrnd(){
		if(null == m_EmailOppBkgrnd) return "";
		return m_EmailOppBkgrnd;
	}


	/**
	* Email Message Opportunity references required
	*/
    private String m_EmailOppReference=null;
	public void setEmailOppReference(String date){
		if(date == null){
			m_EmailOppReference=null;
			return ;
		}
		m_EmailOppReference=date.trim();
	}
	public String getEmailOppReference(){
		if(null == m_EmailOppReference) return "";
		return m_EmailOppReference;
	}


	/**
	* Email Attach File Name
	*/
    private String m_EmailAttachFileName=null;
	public void setEmailAttachFileName(String date){
		if(date == null){
			m_EmailAttachFileName=null;
			return ;
		}
		m_EmailAttachFileName=date.trim();
	}
	public String getEmailAttachFileName(){
		if(null == m_EmailAttachFileName) return "";
		return m_EmailAttachFileName;
	}

	
	/**
	** max number of messages per month for a volunteer 
	**/
	private int m_iEmailMax=0;
	public void setEmailMax(int number){
		m_iEmailMax = number;
	}
	public void setEmailMax(String number){
		m_iEmailMax = convertToInt(number);
		return;
	}
	public int getEmailMax(){
		return m_iEmailMax;
	}

	/**
	* max number interval
	*/
    private String m_EmailMaxInterval=null;
	public void setEmailMaxInterval(String temp){
		if(temp == null){
			m_EmailMaxInterval=null;
			return ;
		}
		m_EmailMaxInterval=temp.trim();
	}
	public String getEmailMaxInterval(){
		if(null == m_EmailMaxInterval) return "";
		return m_EmailMaxInterval;
	}

	
	/**
	** email_id type LONG() in table emailinfo 
	**/
	private int m_iEmailId=0;
	public void setEmailId(int number){
		m_iEmailId = number;
	}
	public void setEmailId(String number){
		m_iEmailId = convertToInt(number);
		return;
	}
	public int getEmailId(){
		return m_iEmailId;
	}


	/**
	** volunteer_id type LONG() in table emailinfo 
	**/
	private int m_iEmailVolunteerId=0;
	public void setEmailVolunteerId(int number){
		m_iEmailVolunteerId = number;
	}
	public void setEmailVolunteerId(String number){
		m_iEmailVolunteerId = convertToInt(number);
		return;
	}
	public int getEmailVolunteerId(){
		return m_iEmailVolunteerId;
	}


	/**
	** org_id type LONG() in table emailinfo 
	**/
	private int m_iEmailOrgId=0;
	public void setEmailOrgId(int number){
		m_iEmailOrgId = number;
	}
	public void setEmailOrgId(String number){
		m_iEmailOrgId = convertToInt(number);
		return;
	}
	public int getEmailOrgId(){
		return m_iEmailOrgId;
	}


	/**
	** opp_id type LONG() in table emailinfo 
	**/
	private int m_iEmailOppId=0;
	public void setEmailOppId(int number){
		m_iEmailOppId = number;
	}
	public void setEmailOppId(String number){
		m_iEmailOppId = convertToInt(number);
		return;
	}
	public int getEmailOppId(){
		return m_iEmailOppId;
	}


	/**
	** volunteer_id type LONG() in table emailinfo 
	**/
	private int m_iEmailVolUID=0;
	public void setEmailVolUID(int number){
		m_iEmailVolUID = number;
	}
	public void setEmailVolUID(String number){
		m_iEmailVolUID = convertToInt(number);
		return;
	}
	public int getEmailVolUID(){
		return m_iEmailVolUID;
	}


	/**
	** m_iEmailVolRailsID type LONG() in table emailinfo 
	**/
	private int m_iEmailVolRailsID=0;
	public void setEmailVolRailsID(int number){
		m_iEmailVolRailsID = number;
	}
	public void setEmailVolRailsID(String number){
		m_iEmailVolRailsID = convertToInt(number);
		return;
	}
	public int getEmailVolRailsID(){
		return m_iEmailVolRailsID;
	}


	/**
	** org_id type LONG() in table emailinfo 
	**/
	private int m_iEmailOrgNID=0;
	public void setEmailOrgNID(int number){
		m_iEmailOrgNID = number;
	}
	public void setEmailOrgNID(String number){
		m_iEmailOrgNID = convertToInt(number);
		return;
	}
	public int getEmailOrgNID(){
		return m_iEmailOrgNID;
	}


	/**
	** opp_id type LONG() in table emailinfo 
	**/
	private int m_iEmailOppNID=0;
	public void setEmailOppNID(int number){
		m_iEmailOppNID = number;
	}
	public void setEmailOppNID(String number){
		m_iEmailOppNID = convertToInt(number);
		return;
	}
	public int getEmailOppNID(){
		return m_iEmailOppNID;
	}


	/**
	** org_url_id type LONG()
	**/
	private int m_iEmailOrgUrlID=0;
	public void setEmailOrgUrlID(int number){
		m_iEmailOrgUrlID = number;
	}
	public void setEmailOrgUrlID(String number){
		m_iEmailOrgUrlID = convertToInt(number);
		return;
	}
	public int getEmailOrgUrlID(){
		return m_iEmailOrgUrlID;
	}


	/**
	** opp_url_id type LONG() 
	**/
	private int m_iEmailOppUrlID=0;
	public void setEmailOppUrlID(int number){
		m_iEmailOppUrlID = number;
	}
	public void setEmailOppUrlID(String number){
		m_iEmailOppUrlID = convertToInt(number);
		return;
	}
	public int getEmailOppUrlID(){
		return m_iEmailOppUrlID;
	}


	/**
	** create_dt type DATETIME() in table organizationinfo 
	**/
	private java.util.Date m_azdEmailCreateDt=null;
	public void setEmailCreateDt(java.util.Date value){
		if(value == null){
			m_azdEmailCreateDt = null;
			return;
		}
		m_azdEmailCreateDt = value;
	}
	public java.util.Date getEmailCreateDt(){
		if(m_azdEmailCreateDt == null) return null;
		return m_azdEmailCreateDt;
	}

	/**
	** sent_dt type DATETIME() in table emailinfo 
	**/
	private java.util.Date m_azdEmailSentDt=null;
	public void setEmailSentDt(java.util.Date value){
		if(value == null){
			m_azdEmailSentDt = null;
			return;
		}
		m_azdEmailSentDt = value;
	}
	public java.util.Date getEmailSentDt(){
		if(m_azdEmailSentDt == null) return null;
		return m_azdEmailSentDt;
	}

	/**
	** sent_status type VARCHAR(255) in table emailinfo 
	**/
	private String m_aszEmailSentStatus=null;
	public void setEmailSentStatus(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailSentStatus = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailSentStatus = aszTemp;
			return;
		}
		m_aszEmailSentStatus = aszTemp.substring(0,iLen);
	}
	public String getEmailSentStatus(){
		if(m_aszEmailSentStatus == null) return "";
		return m_aszEmailSentStatus;
	}

	/**
	** m_aszEmailUserAgent type VARCHAR(255) in table applicaiton_init 
	**/
	private String m_aszEmailUserAgent=null;
	public void setEmailUserAgent(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailUserAgent = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailUserAgent = aszTemp;
			return;
		}
		m_aszEmailUserAgent = aszTemp.substring(0,iLen);
	}
	public String getEmailUserAgent(){
		if(m_aszEmailUserAgent == null) return "";
		return m_aszEmailUserAgent;
	}

	/**
	** m_aszEmailReferrer type VARCHAR(255) in table application_{init 
	**/
	private String m_aszEmailReferrer=null;
	public void setEmailReferrer(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailReferrer = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailReferrer = aszTemp;
			return;
		}
		m_aszEmailReferrer = aszTemp.substring(0,iLen);
	}
	public String getEmailReferrer(){
		if(m_aszEmailReferrer == null) return "";
		return m_aszEmailReferrer;
	}


	/**
	** m_aszEmailRemoteIP type VARCHAR(255) in table application_{init 
	**/
	private String m_aszEmailRemoteIP=null;
	public void setEmailRemoteIP(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailRemoteIP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailRemoteIP = aszTemp;
			return;
		}
		m_aszEmailRemoteIP = aszTemp.substring(0,iLen);
	}
	public String getEmailRemoteIP(){
		if(m_aszEmailRemoteIP == null) return "";
		return m_aszEmailRemoteIP;
	}

	/**
	** subdom type VARCHAR(100) in table emailinfo 
	**/
	private String m_aszEmailSubdom=null;
	public void setEmailSubdom(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailSubdom = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailSubdom = aszTemp;
			return;
		}
		m_aszEmailSentStatus = aszTemp.substring(0,iLen);
	}
	public String getEmailSubdom(){
		if(m_aszEmailSubdom == null) return "";
		return m_aszEmailSubdom;
	}

	
	
	
	
	//===========> END EMAIL Message Object 
	//===========> END EMAIL Message Object 
	//===========> END EMAIL Message Object 

	
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
	** m_aszCoverLetterFileName term name
	**/
	private String m_aszCoverLetterFileName=null;
	public void setCoverLetterFileName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCoverLetterFileName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCoverLetterFileName = aszTemp;
			return;
		}
		m_aszCoverLetterFileName = aszTemp.substring(0,iLen);
	}
	public String getCoverLetterFileName(){
		if(m_aszCoverLetterFileName == null) return "";
		return m_aszCoverLetterFileName;
	}

	/**
	** m_aszApplicationFileName term name
	**/
	private String m_aszApplicationFileName=null;
	public void setApplicationFileName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszApplicationFileName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszApplicationFileName = aszTemp;
			return;
		}
		m_aszApplicationFileName = aszTemp.substring(0,iLen);
	}
	public String getApplicationFileName(){
		if(m_aszApplicationFileName == null) return "";
		return m_aszApplicationFileName;
	}

	/**
	** OtherDocsFileName term name
	**/
	private String m_aszOtherDocsFileName=null;
	public void setOtherDocsFileName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOtherDocsFileName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOtherDocsFileName = aszTemp;
			return;
		}
		m_aszOtherDocsFileName = aszTemp.substring(0,iLen);
	}
	public String getOtherDocsFileName(){
		if(m_aszOtherDocsFileName == null) return "";
		return m_aszOtherDocsFileName;
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
	** m_iNumDaysTrigger type LONG() in table emailinfo 
	**/
	private int m_iNumDaysTrigger=0;
	public void setNumDaysTrigger(int number){
		m_iNumDaysTrigger = number;
	}
	public void setNumDaysTrigger(String number){
		m_iNumDaysTrigger = convertToInt(number);
		return;
	}
	public int getNumDaysTrigger(){
		return m_iNumDaysTrigger;
	}

	
	private FormFile m_resumeFile;
	
	public void setResumeFile(FormFile f) {
		m_resumeFile = f;
	}
	
	public FormFile getResumeFile() {
		return m_resumeFile;
	}
	
	private FormFile m_coverLetterFile;
	
	public void setCoverLetterFile(FormFile f) {
		m_coverLetterFile = f;
	}

	public FormFile getCoverLetterFile() {
		return m_coverLetterFile;
	}
	
	private FormFile m_applicationFile;
	
	public void setApplicationFile(FormFile f) {
		m_applicationFile = f;
	}
	
	public FormFile getApplicationFile() {
		return m_applicationFile;
	}
	
	private Map<Integer, FormFile> m_requiredDocumentFiles;
	
	public void setRequiredDocumentFiles(Map<Integer, FormFile> map) {
		m_requiredDocumentFiles = map;
	}
	
	public Map<Integer, FormFile> getRequiredDocumentFiles() {
		return m_requiredDocumentFiles;
	}

	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===



}
