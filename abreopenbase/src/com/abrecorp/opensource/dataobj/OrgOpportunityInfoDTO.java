package com.abrecorp.opensource.dataobj;

/**
* Code Generated Data Transfer Object DTO Class
* For Table organizationinfo
*/

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts.upload.FormFile;


public class OrgOpportunityInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	/**
	* constructor
	*/
	public OrgOpportunityInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			OrgOpportunityInfoDTO el = (OrgOpportunityInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}


	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table org_opportunitiesinfo 
	//===> Start Code Generated Methods For Table org_opportunitiesinfo 
	//===> Start Code Generated Methods For Table org_opportunitiesinfo 


	/**
	** oppurl id 
	**/
	private int m_iOPPurlID=0;
	public void setOPPurlID(int number){
		m_iOPPurlID = number;
	}
	public void setOPPurlID(String number){
		m_iOPPurlID = convertToInt(number);
		return;
	}
	public int getOPPurlID(){
		return m_iOPPurlID;
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
	** source that the opportunity came through - varchar;
	*  for now, will just be "feeds" or "" - not stored in db
	*  but may update later to show the actual source from the db
	*  if we start gathering diverse data for that
	**/
	private String m_aszOPPSource=null;
	public void setOPPSource(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSource = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSource = aszTemp;
			return;
		}
		m_aszOPPSource = aszTemp.substring(0,iLen);
	}
	public String getOPPSource(){
		if(m_aszOPPSource == null) return "";
		return m_aszOPPSource;
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
	** opp_number type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPOppNumber=0;
	public void setOPPOppNumber(int number){
		m_iOPPOppNumber = number;
	}
	public void setOPPOppNumber(String number){
		m_iOPPOppNumber = convertToInt(number);
		return;
	}
	public int getOPPOppNumber(){
		return m_iOPPOppNumber;
	}

	/**
	** opportunity's organization owner's nid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iOrgNID=0;
	public void setORGNID(int number){
		m_iOrgNID = number;
	}
	public void setORGNID(String number){
		m_iOrgNID = convertToInt(number);
		return;
	}
	public int getORGNID(){
		return m_iOrgNID;
	}

	/**
	** opportunity nid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
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


	/**
	** opportunity vid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iOPPVID=0;
	public void setOPPVID(int number){
		m_iOPPVID = number;
	}
	public void setOPPVID(String number){
		m_iOPPVID = convertToInt(number);
		return;
	}
	public int getOPPVID(){
		return m_iOPPVID;
	}


	/**
	** opportunity lid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iOPPLID=0;
	public void setOPPLID(int number){
		m_iOPPLID = number;
	}
	public void setOPPLID(String number){
		m_iOPPLID = convertToInt(number);
		return;
	}
	public int getOPPLID(){
		return m_iOPPLID;
	}

	
	/**
	** org_number type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPOrgNumber=0;
	public void setOPPOrgNumber(int number){
		m_iOPPOrgNumber = number;
	}
	public void setOPPOrgNumber(String number){
		m_iOPPOrgNumber = convertToInt(number);
		return;
	}
	public int getOPPOrgNumber(){
		return m_iOPPOrgNumber;
	}

	/**
	** opportunity moderate type LONG() in  
	*  table um_node, whether org is in moderation queue or not 
	**/
	private int m_iOPPModerate=0;
	public void setOPPModerate(int number){
		m_iOPPModerate = number;
	}
	public void setOPPModerate(String number){
		m_iOPPModerate = convertToInt(number);
		return;
	}
	public int getOPPModerate(){
		return m_iOPPModerate;
	}

	

	/**
	** opportunity status, whether something is published or not - type LONG() in  
	*  table um_node, whether org is in moderation queue or not 
	**/
	private int m_iOPPPublished=0;
	public void setOPPPublished(int number){
		m_iOPPPublished = number;
	}
	public void setOPPPublished(String number){
		m_iOPPPublished = convertToInt(number);
		return;
	}
	public int getOPPPublished(){
		return m_iOPPPublished;
	}


	/**
	** opportunity expiration, whether something is expired or not - type LONG() in  
	*  table um_auto_expire, whether opp is inactive or not 
	**/
	private int m_iOPPExpiredRenew=0;
	public void setOPPExpiredRenew(int number){
		m_iOPPExpiredRenew = number;
	}
	public void setOPPExpiredRenew(String number){
		m_iOPPExpiredRenew = convertToInt(number);
		return;
	}
	public int getOPPExpiredRenew(){
		return m_iOPPExpiredRenew;
	}


	/**
	** opportunity expiration date, whether something is expired or not - type LONG() in  
	*  table um_auto_expire, whether opp is inactive or not 
	**/
	private int m_iOPPExpirationDt=0;
	public void setOPPExpirationDt(int number){
		m_iOPPExpirationDt = number;
	}
	public void setOPPExpirationDt(String number){
		m_iOPPExpirationDt = convertToInt(number);
		return;
	}
	public int getOPPExpirationDt(){
		return m_iOPPExpirationDt;
	}
	/**
	** opportunity expiration date - time between expire and now -, whether something is expired or not - type LONG() in  
	*  table um_auto_expire, whether opp is inactive or not 
	**/
	private int m_iOPPExpirationTime=0;
	public void setOPPExpirationTime(int number){
		m_iOPPExpirationTime = number;
	}
	public void setOPPExpirationTime(String number){
		m_iOPPExpirationTime = convertToInt(number);
		return;
	}
	public int getOPPExpirationTime(){
		return m_iOPPExpirationTime;
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
	** prog_number type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPProgNumber=0;
	public void setOPPProgNumber(int number){
		m_iOPPProgNumber = number;
	}
	public void setOPPProgNumber(String number){
		m_iOPPProgNumber = convertToInt(number);
		return;
	}
	public int getOPPProgNumber(){
		return m_iOPPProgNumber;
	}


	/**
	** oppcodekey type CHAR(20) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPOppcodekey=null;
	public void setOPPOppcodekey(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPOppcodekey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPOppcodekey = aszTemp;
			return;
		}
		m_aszOPPOppcodekey = aszTemp.substring(0,iLen);
	}
	public String getOPPOppcodekey(){
		if(m_aszOPPOppcodekey == null) return "";
		return m_aszOPPOppcodekey;
	}


	/**
	** status type VARCHAR(50) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPStatus=null;
	public void setOPPStatus(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPStatus = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPStatus = aszTemp;
			return;
		}
		m_aszOPPStatus = aszTemp.substring(0,iLen);
	}
	public String getOPPStatus(){
		if(m_aszOPPStatus == null) return "";
		return m_aszOPPStatus;
	}


	/**
	** status type VARCHAR(50) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPStatus2=null;
	public void setOPPStatus2(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPStatus2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPStatus2 = aszTemp;
			return;
		}
		m_aszOPPStatus2 = aszTemp.substring(0,iLen);
	}
	public String getOPPStatus2(){
		if(m_aszOPPStatus2 == null) return "";
		return m_aszOPPStatus2;
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
	** FaithSpecTID  tid type LONG()
	**/
	private int m_iOPPFaithSpecTID=-1;
	public void setOPPFaithSpecTID(int number){
		m_iOPPFaithSpecTID = number;
	}
	public void setOPPFaithSpecTID(String number){
		m_iOPPFaithSpecTID = convertToInt(number);
		return;
	}
	public int getOPPFaithSpecTID(){
		return m_iOPPFaithSpecTID;
	}
	/**
	** PositionType  tid type LONG()
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

	/**
	** PositionType 2 tid type LONG()
	**/
	private int m_iOPPPositionType2TID=-1;
	public void setOPPPositionType2TID(int number){
		m_iOPPPositionType2TID = number;
	}
	public void setOPPPositionType2TID(String number){
		m_iOPPPositionType2TID = convertToInt(number);
		return;
	}
	public int getOPPPositionType2TID(){
		return m_iOPPPositionType2TID;
	}


	/**
	** type type VARCHAR(50) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPType=null;
	public void setOPPType(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPType = aszTemp;
			return;
		}
		m_aszOPPType = aszTemp.substring(0,iLen);
	}
	public String getOPPType(){
		if(m_aszOPPType == null) return "";
		return m_aszOPPType;
	}


	/**
	** title type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPTitle=null;
	public void setOPPTitle(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPTitle = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPTitle = aszTemp;
			return;
		}
		m_aszOPPTitle = aszTemp.substring(0,iLen);
	}
	public String getOPPTitle(){
		if(m_aszOPPTitle == null) return "";
		return m_aszOPPTitle;
	}


	/**
	** primary_person type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPPrimaryPersonUID=0;
	public void setOPPPrimaryPersonUID(int number){
		m_iOPPPrimaryPersonUID = number;
	}
	public void setOPPPrimaryPersonUID(String number){
		m_iOPPPrimaryPersonUID = convertToInt(number);
		return;
	}
	public int getOPPPrimaryPersonUID(){
		return m_iOPPPrimaryPersonUID;
	}

	/**
	** primary_person type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPPrimaryPersonUIDModified=0;
	public void setOPPPrimaryPersonUIDModified(int number){
		m_iOPPPrimaryPersonUIDModified = number;
	}
	public void setOPPPrimaryPersonUIDModified(String number){
		m_iOPPPrimaryPersonUIDModified = convertToInt(number);
		return;
	}
	public int getOPPPrimaryPersonUIDModified(){
		return m_iOPPPrimaryPersonUIDModified;
	}


	/**
	** primary person uid type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPUID=0;
	public void setOPPUID(int number){
		m_iOPPUID = number;
	}
	public void setOPPUID(String number){
		m_iOPPUID = convertToInt(number);
		return;
	}
	public int getOPPUID(){
		return m_iOPPUID;
	}

	/*
	 * a_iOPPContactUIDsArray
	 */
	private int[] a_iOPPContactUIDsArray=null;
	public void setOPPContactUIDsArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iOPPContactUIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPContactUIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPContactUIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPContactUIDsArray(){
		if(a_iOPPContactUIDsArray == null) {
			a_iOPPContactUIDsArray=new int[0];
			return a_iOPPContactUIDsArray;
		}
		return a_iOPPContactUIDsArray;
	}

	/*
	 * a_iOPPContactUIDsModifiedArray
	 */
	private int[] a_iOPPContactUIDsModifiedArray=null;
	public void setOPPContactUIDsModifiedArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iOPPContactUIDsModifiedArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPContactUIDsModifiedArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPContactUIDsModifiedArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPContactUIDsModifiedArray(){
		if(a_iOPPContactUIDsModifiedArray == null) {
			a_iOPPContactUIDsModifiedArray=new int[0];
			return a_iOPPContactUIDsModifiedArray;
		}
		return a_iOPPContactUIDsModifiedArray;
	}

	/*
	 * a_iOPPContactUIDsRcvEmailArray
	 */
	private int[] a_iOPPContactUIDsRcvEmailArray=null;
	public void setOPPContactUIDsRcvEmailArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iOPPContactUIDsRcvEmailArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPContactUIDsRcvEmailArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPContactUIDsRcvEmailArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPContactUIDsRcvEmailArray(){
		if(a_iOPPContactUIDsRcvEmailArray == null) {
			a_iOPPContactUIDsRcvEmailArray=new int[0];
			return a_iOPPContactUIDsRcvEmailArray;
		}
		return a_iOPPContactUIDsRcvEmailArray;
	}

	/*
	 * a_iOPPContactUIDsRcvEmailModifiedArray
	 */
	private int[] a_iOPPContactUIDsRcvEmailModifiedArray=null;
	public void setOPPContactUIDsRcvEmailModifiedArray(int[] values){
		int iLen=1000;
		int[] a_iTemp = values;
		a_iOPPContactUIDsRcvEmailModifiedArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPContactUIDsRcvEmailModifiedArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPContactUIDsRcvEmailModifiedArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPContactUIDsRcvEmailModifiedArray(){
		if(a_iOPPContactUIDsRcvEmailModifiedArray == null) {
			a_iOPPContactUIDsRcvEmailModifiedArray=new int[0];
			return a_iOPPContactUIDsRcvEmailModifiedArray;
		}
		return a_iOPPContactUIDsRcvEmailModifiedArray;
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


	/**
	** vols_needed type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPVolsNeeded=0;
	public void setOPPVolsNeeded(int number){
		m_iOPPVolsNeeded = number;
	}
	public void setOPPVolsNeeded(String number){
		m_iOPPVolsNeeded = convertToInt(number);
		return;
	}
	public int getOPPVolsNeeded(){
		return m_iOPPVolsNeeded;
	}


	/**
	** distance from search point - in miles, i think 
	**/
	private double m_dOPPDistance=0.0;
	public void setOPPDistance(double number){
		m_dOPPDistance = number;
	}
	public void setOPPDistance(String number){
		m_dOPPDistance = convertToDouble(number);
		return;
	}
	public double getOPPDistance(){
		return m_dOPPDistance;
	}

	/**
	** commit_hourly type FLOAT() in table org_opportunitiesinfo 
	**/
	private double m_dOPPCommitHourly=0.0;
	public void setOPPCommitHourly(double number){
		m_dOPPCommitHourly = number;
	}
	public void setOPPCommitHourly(String number){
		m_dOPPCommitHourly = convertToDouble(number);
		return;
	}
	public double getOPPCommitHourly(){
		return m_dOPPCommitHourly;
	}


	/**
	** commit_type type VARCHAR(20) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCommitType=null;
	public void setOPPCommitType(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCommitType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCommitType = aszTemp;
			return;
		}
		m_aszOPPCommitType = aszTemp.substring(0,iLen);
	}
	public String getOPPCommitType(){
		if(m_aszOPPCommitType == null) return "";
		return m_aszOPPCommitType;
	}


	/**
	** addr_line1 type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrLine1=null;
	public void setOPPAddrLine1(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrLine1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrLine1 = aszTemp;
			return;
		}
		m_aszOPPAddrLine1 = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrLine1(){
		if(m_aszOPPAddrLine1 == null) return "";
		return m_aszOPPAddrLine1;
	}


	/**
	** addr_line2 type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrLine2=null;
	public void setOPPAddrLine2(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrLine2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrLine2 = aszTemp;
			return;
		}
		m_aszOPPAddrLine2 = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrLine2(){
		if(m_aszOPPAddrLine2 == null) return "";
		return m_aszOPPAddrLine2;
	}


	/**
	** addr_line3 type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrLine3=null;
	public void setOPPAddrLine3(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrLine3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrLine3 = aszTemp;
			return;
		}
		m_aszOPPAddrLine3 = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrLine3(){
		if(m_aszOPPAddrLine3 == null) return "";
		return m_aszOPPAddrLine3;
	}


	/**
	** addr_city type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrCity=null;
	public void setOPPAddrCity(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrCity = aszTemp;
			return;
		}
		m_aszOPPAddrCity = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrCity(){
		if(m_aszOPPAddrCity == null) return "";
		return m_aszOPPAddrCity;
	}


	/**
	** addr_stateprov type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrStateprov=null;
	public void setOPPAddrStateprov(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrStateprov = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrStateprov = aszTemp;
			return;
		}
		m_aszOPPAddrStateprov = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrStateprov(){
		if(m_aszOPPAddrStateprov == null) return "";
		return m_aszOPPAddrStateprov;
	}


	/**
	** addr_postalcode type VARCHAR(40) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrPostalcode=null;
	public void setOPPAddrPostalcode(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrPostalcode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrPostalcode = aszTemp;
			return;
		}
		m_aszOPPAddrPostalcode = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrPostalcode(){
		if(m_aszOPPAddrPostalcode == null) return "";
		return m_aszOPPAddrPostalcode;
	}


	/**
	** addr_country_name type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrCountryName=null;
	public void setOPPAddrCountryName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrCountryName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrCountryName = aszTemp;
			return;
		}
		m_aszOPPAddrCountryName = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrCountryName(){
		if(m_aszOPPAddrCountryName == null) return "";
		return m_aszOPPAddrCountryName;
	}


	/**
	** addr_county type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrCounty=null;
	public void setOPPAddrCounty(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrCounty = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrCounty = aszTemp;
			return;
		}
		m_aszOPPAddrCounty = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrCounty(){
		if(m_aszOPPAddrCounty == null) return "";
		return m_aszOPPAddrCounty;
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
		if(number==null) return;
		int length=number.length();
		if(length==0) return;
		fLatitude = new Float(number);
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
		if(number==null) return;
		int length=number.length();
		if(length==0) return;
		fLongitude = new Float(number);
		return;
	}
	public float getLongitude(){
		return fLongitude;
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
	** Benefit(s) offered 
	**/
	private String m_aszOPPBenefits=null;
	public void setOPPBenefits(String value){
		int iLen=500;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPBenefits = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPBenefits = aszTemp;
			return;
		}
		m_aszOPPBenefits = aszTemp.substring(0,iLen);
	}
	public String getOPPBenefits(){
		if(m_aszOPPBenefits == null) return "";
		return m_aszOPPBenefits;
	}


	/**
	** m_aszHollandCodes 
	**/
	private String m_aszHollandCodes=null;
	public void setHollandCodes(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszHollandCodes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszHollandCodes = aszTemp;
			return;
		}
		m_aszHollandCodes = aszTemp.substring(0,iLen);
	}
	public String getHollandCodes(){
		if(m_aszHollandCodes == null) return "";
		return m_aszHollandCodes;
	}

	/**
	** Great For... (1 or more) 
	**/
	private String m_aszOPPGreatForAreas=null;
	public void setOPPGreatForAreas(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPGreatForAreas = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPGreatForAreas = aszTemp;
			return;
		}
		m_aszOPPGreatForAreas = aszTemp.substring(0,iLen);
	}
	public String getOPPGreatForAreas(){
		if(m_aszOPPGreatForAreas == null) return "";
		return m_aszOPPGreatForAreas;
	}

	/**
	** focus_areas type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFocusAreas=null;
	public void setOPPFocusAreas(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFocusAreas = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFocusAreas = aszTemp;
			return;
		}
		m_aszOPPFocusAreas = aszTemp.substring(0,iLen);
	}
	public String getOPPFocusAreas(){
		if(m_aszOPPFocusAreas == null) return "";
		return m_aszOPPFocusAreas;
	}


	/**
	** focus_areas2 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFocusAreas2=null;
	public void setOPPFocusAreas2(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFocusAreas2 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFocusAreas2 = aszTemp;
			return;
		}
		m_aszOPPFocusAreas2 = aszTemp.substring(0,iLen);
	}
	public String getOPPFocusAreas2(){
		if(m_aszOPPFocusAreas2 == null) return "";
		return m_aszOPPFocusAreas2;
	}


	/**
	** focus_areas3 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFocusAreas3=null;
	public void setOPPFocusAreas3(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFocusAreas3 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFocusAreas3 = aszTemp;
			return;
		}
		m_aszOPPFocusAreas3 = aszTemp.substring(0,iLen);
	}
	public String getOPPFocusAreas3(){
		if(m_aszOPPFocusAreas3 == null) return "";
		return m_aszOPPFocusAreas3;
	}


	/**
	** focus_areas 4 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFocusAreas4=null;
	public void setOPPFocusAreas4(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFocusAreas4 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFocusAreas4 = aszTemp;
			return;
		}
		m_aszOPPFocusAreas4 = aszTemp.substring(0,iLen);
	}
	public String getOPPFocusAreas4(){
		if(m_aszOPPFocusAreas4 == null) return "";
		return m_aszOPPFocusAreas4;
	}


	/**
	** focus_areas5 type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFocusAreas5=null;
	public void setOPPFocusAreas5(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFocusAreas5 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFocusAreas5 = aszTemp;
			return;
		}
		m_aszOPPFocusAreas5 = aszTemp.substring(0,iLen);
	}
	public String getOPPFocusAreas5(){
		if(m_aszOPPFocusAreas5 == null) return "";
		return m_aszOPPFocusAreas5;
	}

	/**
	** Focus Areas - Great For 1 tid type LONG()
	**/
	private int m_iOPPGreatFor1TID=-1;
	public void setOPPGreatFor1TID(int number){
		m_iOPPGreatFor1TID = number;
	}
	public void setOPPGreatFor1TID(String number){
		m_iOPPGreatFor1TID = convertToInt(number);
		return;
	}
	public int getOPPGreatFor1TID(){
		return m_iOPPGreatFor1TID;
	}

	/**
	** Focus Areas - Great For 2 tid type LONG()
	**/
	private int m_iOPPGreatFor2TID=-1;
	public void setOPPGreatFor2TID(int number){
		m_iOPPGreatFor2TID = number;
	}
	public void setOPPGreatFor2TID(String number){
		m_iOPPGreatFor2TID = convertToInt(number);
		return;
	}
	public int getOPPGreatFor2TID(){
		return m_iOPPGreatFor2TID;
	}

	/**
	** Focus Areas - Great For 3 tid type LONG()
	**/
	private int m_iOPPGreatFor3TID=-1;
	public void setOPPGreatFor3TID(int number){
		m_iOPPGreatFor3TID = number;
	}
	public void setOPPGreatFor3TID(String number){
		m_iOPPGreatFor3TID = convertToInt(number);
		return;
	}
	public int getOPPGreatFor3TID(){
		return m_iOPPGreatFor3TID;
	}

	/**
	** Focus Areas - Great For 4 tid type LONG()
	**/
	private int m_iOPPGreatFor4TID=-1;
	public void setOPPGreatFor4TID(int number){
		m_iOPPGreatFor4TID = number;
	}
	public void setOPPGreatFor4TID(String number){
		m_iOPPGreatFor4TID = convertToInt(number);
		return;
	}
	public int getOPPGreatFor4TID(){
		return m_iOPPGreatFor4TID;
	}

	/**
	** Focus Areas - Great For 5 tid type LONG()
	**/
	private int m_iOPPGreatFor5TID=-1;
	public void setOPPGreatFor5TID(int number){
		m_iOPPGreatFor5TID = number;
	}
	public void setOPPGreatFor5TID(String number){
		m_iOPPGreatFor5TID = convertToInt(number);
		return;
	}
	public int getOPPGreatFor5TID(){
		return m_iOPPGreatFor5TID;
	}

	/**
	** languages type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPLanguages=null;
	public void setOPPLanguages(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPLanguages = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPLanguages = aszTemp;
			return;
		}
		m_aszOPPLanguages = aszTemp.substring(0,iLen);
	}
	public String getOPPLanguages(){
		if(m_aszOPPLanguages == null) return "";
		return m_aszOPPLanguages;
	}


	/**
	** Language  tid type LONG()
	**/
	private int m_iOPPLanguageTID=-1;
	public void setOPPLanguageTID(int number){
		m_iOPPLanguageTID = number;
	}
	public void setOPPLanguageTID(String number){
		m_iOPPLanguageTID = convertToInt(number);
		return;
	}
	public int getOPPLanguageTID(){
		return m_iOPPLanguageTID;
	}

	/**
	** partners type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPPartners=null;
	public void setOPPPartners(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPPartners = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPPartners = aszTemp;
			return;
		}
		m_aszOPPPartners = aszTemp.substring(0,iLen);
	}
	public String getOPPPartners(){
		if(m_aszOPPPartners == null) return "";
		return m_aszOPPPartners;
	}


	/**
	** daterequired type VARCHAR(5) in table um_content_type_volunteer_opportunity
	**/
	private String m_aszOPPDaterequired=null;
	public void setOPPDaterequired(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPDaterequired = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPDaterequired = aszTemp;
			return;
		}
		m_aszOPPDaterequired = aszTemp.substring(0,iLen);
	}
	public String getOPPDaterequired(){
		if(m_aszOPPDaterequired == null) return "";
		return m_aszOPPDaterequired;
	}

	// date required tid - is this on a specific date or not? - taxonomy so that easily editable dropdown values	
	private int m_iOPPDaterequiredTID=0;
	public void setOPPDaterequiredTID(int number){
		m_iOPPDaterequiredTID = number;
	}
	public void setOPPDaterequiredTID(String number){
		m_iOPPDaterequiredTID = convertToInt(number);
		return;
	}
	public int getOPPDaterequiredTID(){
		return m_iOPPDaterequiredTID;
	}


	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszStartDtMonth=null;
	public void setOPPStartDtMonth(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszStartDtMonth = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszStartDtMonth = aszTemp;
			return;
		}
		m_aszStartDtMonth = aszTemp.substring(0,iLen);
	}
	public String getOPPStartDtMonth(){
		if(m_aszStartDtMonth == null) return "";
		return m_aszStartDtMonth;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszStartDtDate=null;
	public void setOPPStartDtDate(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszStartDtDate = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszStartDtDate = aszTemp;
			return;
		}
		m_aszStartDtDate = aszTemp.substring(0,iLen);
	}
	public String getOPPStartDtDate(){
		if(m_aszStartDtDate == null) return "";
		return m_aszStartDtDate;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszStartDtYear=null;
	public void setOPPStartDtYear(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszStartDtYear = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszStartDtYear = aszTemp;
			return;
		}
		m_aszStartDtYear = aszTemp.substring(0,iLen);
	}
	public String getOPPStartDtYear(){
		if(m_aszStartDtYear == null) return "";
		return m_aszStartDtYear;
	}



	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszEndDtMonth=null;
	public void setOPPEndDtMonth(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEndDtMonth = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEndDtMonth = aszTemp;
			return;
		}
		m_aszEndDtMonth = aszTemp.substring(0,iLen);
	}
	public String getOPPEndDtMonth(){
		if(m_aszEndDtMonth == null) return "";
		return m_aszEndDtMonth;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszEndDtDate=null;
	public void setOPPEndDtDate(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEndDtDate = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEndDtDate = aszTemp;
			return;
		}
		m_aszEndDtDate = aszTemp.substring(0,iLen);
	}
	public String getOPPEndDtDate(){
		if(m_aszEndDtDate == null) return "";
		return m_aszEndDtDate;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszEndDtYear=null;
	public void setOPPEndDtYear(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEndDtYear = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEndDtYear = aszTemp;
			return;
		}
		m_aszEndDtYear = aszTemp.substring(0,iLen);
	}
	public String getOPPEndDtYear(){
		if(m_aszEndDtYear == null) return "";
		return m_aszEndDtYear;
	}


	


	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszApplicDeadlnDtMonth=null;
	public void setOPPApplicDeadlnDtMonth(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszApplicDeadlnDtMonth = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszApplicDeadlnDtMonth = aszTemp;
			return;
		}
		m_aszApplicDeadlnDtMonth = aszTemp.substring(0,iLen);
	}
	public String getOPPApplicDeadlnDtMonth(){
		if(m_aszApplicDeadlnDtMonth == null) return "";
		return m_aszApplicDeadlnDtMonth;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszApplicDeadlnDtDate=null;
	public void setOPPApplicDeadlnDtDate(String value){
		int iLen=2;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszApplicDeadlnDtDate = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszApplicDeadlnDtDate = aszTemp;
			return;
		}
		m_aszApplicDeadlnDtDate = aszTemp.substring(0,iLen);
	}
	public String getOPPApplicDeadlnDtDate(){
		if(m_aszApplicDeadlnDtDate == null) return "";
		return m_aszApplicDeadlnDtDate;
	}
	/**
	** parsed out date - use to construct actual date
	**/
	private String m_aszApplicDeadlnDtYear=null;
	public void setOPPApplicDeadlnDtYear(String value){
		int iLen=4;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszApplicDeadlnDtYear = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszApplicDeadlnDtYear = aszTemp;
			return;
		}
		m_aszApplicDeadlnDtYear = aszTemp.substring(0,iLen);
	}
	public String getOPPApplicDeadlnDtYear(){
		if(m_aszApplicDeadlnDtYear == null) return "";
		return m_aszApplicDeadlnDtYear;
	}

	// start_dt type LONG() in table drupal um_content_type_volunteer_opportunity 
	
	private int m_iOPPStartDtNum=0;
	public void setOPPStartDtNum(int number){
		m_iOPPStartDtNum = number;
	}
	public void setOPPStartDtNum(String number){
		m_iOPPStartDtNum = convertToInt(number);
		return;
	}
	public int getOPPStartDtNum(){
		return m_iOPPStartDtNum;
	}


	// end_dt type LONG() in table drupal um_content_type_volunteer_opportunity 
	
	private int m_iOPPEndDtNum=0;
	public void setOPPEndDtNum(int number){
		m_iOPPEndDtNum = number;
	}
	public void setOPPEndDtNum(String number){
		m_iOPPEndDtNum = convertToInt(number);
		return;
	}
	public int getOPPEndDtNum(){
		return m_iOPPEndDtNum;
	}


	// create_dt type LONG() in table drupal um_node
	
	private int m_iOPPCreateDtNum=0;
	public void setOPPCreateDtNum(int number){
		m_iOPPCreateDtNum = number;
	}
	public void setOPPCreateDtNum(String number){
		m_iOPPCreateDtNum = convertToInt(number);
		return;
	}
	public int getOPPCreateDtNum(){
		return m_iOPPCreateDtNum;
	}


	
	// start_dt type DATETIME() in table org_opportunitiesinfo 
	
	private java.util.Date m_azdOPPStartDt=null;
	public void setOPPStartDt(java.util.Date value){
		if(value == null){
			m_azdOPPStartDt = null;
			return;
		}
		m_azdOPPStartDt = value;
	}
	public java.util.Date getOPPStartDt(){
		if(m_azdOPPStartDt == null) return null;
		return m_azdOPPStartDt;
	}


	//end_dt type DATETIME() in table org_opportunitiesinfo 
	
	private java.util.Date m_azdOPPEndDt=null;
	public void setOPPEndDt(java.util.Date value){
		if(value == null){
			m_azdOPPEndDt = null;
			return;
		}
		m_azdOPPEndDt = value;
	}
	public java.util.Date getOPPEndDt(){
		if(m_azdOPPEndDt == null) return null;
		return m_azdOPPEndDt;
	}


	// create_dt type DATETIME() in table org_opportunitiesinfo 
	
	private java.util.Date m_azdOPPCreateDt=null;
	public void setOPPCreateDt(java.util.Date value){
		if(value == null){
			m_azdOPPCreateDt = null;
			return;
		}
		m_azdOPPCreateDt = value;
	}
	public java.util.Date getOPPCreateDt(){
		if(m_azdOPPCreateDt == null) return null;
		return m_azdOPPCreateDt;
	}



	
	/**
	** create_id type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPCreateId=0;
	public void setOPPCreateId(int number){
		m_iOPPCreateId = number;
	}
	public void setOPPCreateId(String number){
		m_iOPPCreateId = convertToInt(number);
		return;
	}
	public int getOPPCreateId(){
		return m_iOPPCreateId;
	}


	// update_dt type LONG() in table drupal um_node
	
	private int m_iOPPUpdateDtNum=0;
	public void setOPPUpdateDtNum(int number){
		m_iOPPUpdateDtNum = number;
	}
	public void setOPPUpdateDtNum(String number){
		m_iOPPUpdateDtNum = convertToInt(number);
		return;
	}
	public int getOPPUpdateDtNum(){
		return m_iOPPUpdateDtNum;
	}



	/**
	** update_dt_num  
	**/
	private java.util.Date m_azdOPPUpdateDt=null;
	public void setOPPUpdateDt(java.util.Date value){
		if(value == null){
			m_azdOPPUpdateDt = null;
			return;
		}
		m_azdOPPUpdateDt = value;
	}
	public java.util.Date getOPPUpdateDt(){
		if(m_azdOPPUpdateDt == null) return null;
		return m_azdOPPUpdateDt;
	}


	/**
	** update_id type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPUpdateId=0;
	public void setOPPUpdateId(int number){
		m_iOPPUpdateId = number;
	}
	public void setOPPUpdateId(String number){
		m_iOPPUpdateId = convertToInt(number);
		return;
	}
	public int getOPPUpdateId(){
		return m_iOPPUpdateId;
	}


	/**
	** required_code_type type VARCHAR(10) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPRequiredCodeType=null;
	public void setOPPRequiredCodeType(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPRequiredCodeType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPRequiredCodeType = aszTemp;
			return;
		}
		m_aszOPPRequiredCodeType = aszTemp.substring(0,iLen);
	}
	public String getOPPRequiredCodeType(){
		if(m_aszOPPRequiredCodeType == null) return "";
		return m_aszOPPRequiredCodeType;
	}


	/**
	** required_code_desc type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPRequiredCodeDesc=null;
	public void setOPPRequiredCodeDesc(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPRequiredCodeDesc = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPRequiredCodeDesc = aszTemp;
			return;
		}
		m_aszOPPRequiredCodeDesc = aszTemp.substring(0,iLen);
	}
	public String getOPPRequiredCodeDesc(){
		if(m_aszOPPRequiredCodeDesc == null) return "";
		return m_aszOPPRequiredCodeDesc;
	}



	/**
	** teaser type VARCHAR(255) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPDescTeaser=null;
	public void setOPPDescTeaser(String value){
		int iLen=225;
		String aszTemp = value.replaceAll("\\<[^>]*>","");
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPDescTeaser = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPDescTeaser = aszTemp;
			return;
		}
		m_aszOPPDescTeaser = aszTemp.substring(0,iLen);
	}
	public String getOPPDescTeaser(){
		if(m_aszOPPDescTeaser == null) return "";
		return m_aszOPPDescTeaser;
	}


	/**
	** description type TEXT() in table org_opportunitiesinfo 
	**/
	private String m_aszOPPDescription=null;

	public void setOPPDescription(String value){
		if(null == value){
			m_aszOPPDescription = null;
			return;
		}
		m_aszOPPDescription = value.trim();
	}
	public String getOPPDescription(){
		if(m_aszOPPDescription == null) return "";
		return m_aszOPPDescription;
	}


	/**
	** mission type TEXT() in table org_opportunitiesinfo 
	**/
	private String m_aszOPPMission=null;

	public void setOPPMission(String value){
		if(null == value){
			m_aszOPPMission = null;
			return;
		}
		m_aszOPPMission = value.trim();
	}
	public String getOPPMission(){
		if(m_aszOPPMission == null) return "";
		return m_aszOPPMission;
	}


	/**
	** requirements type TEXT() in table org_opportunitiesinfo 
	**/
	private String m_aszOPPRequirements=null;

	public void setOPPRequirements(String value){
		if(null == value){
			m_aszOPPRequirements = null;
			return;
		}
		m_aszOPPRequirements = value.trim();
	}
	public String getOPPRequirements(){
		if(m_aszOPPRequirements == null) return "";
		return m_aszOPPRequirements;
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
	** popularity type LONG() in table opportunity cck 
	**/
	private int m_iOPPPopularity=-1;
	public void setOPPPopularity(int number){
		m_iOPPPopularity = number;
	}
	public void setOPPPopularity(String number){
		m_iOPPPopularity = convertToInt(number);
		return;
	}
	public int getOPPPopularity(){
		return m_iOPPPopularity;
	}

	/**
	** num_vol_opp type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPNumVolOpp=0;
	public void setOPPNumVolOpp(int number){
		m_iOPPNumVolOpp = number;
	}
	public void setOPPNumVolOpp(String number){
		m_iOPPNumVolOpp = convertToInt(number);
		return;
	}
	public int getOPPNumVolOpp(){
		return m_iOPPNumVolOpp;
	}
	
	/**
	** url_alias type TEXT() 
	**/
	private String m_aszOPPUrlAlias=null;

	public void setOPPUrlAlias(String value){
		if(null == value){
			m_aszOPPUrlAlias = null;
			return;
		}
		m_aszOPPUrlAlias = value.trim();
	}
	public String getOPPUrlAlias(){
		if(m_aszOPPUrlAlias == null) return "";
		return m_aszOPPUrlAlias;
	}

	/**
	** url_alias original type TEXT() 
	**/
	private String m_aszOPPUrlAliasOrig=null;

	public void setOPPUrlAliasOrig(String value){
		if(null == value){
			m_aszOPPUrlAliasOrig = null;
			return;
		}
		m_aszOPPUrlAliasOrig = value.trim();
	}
	public String getOPPUrlAliasOrig(){
		if(m_aszOPPUrlAliasOrig == null) return "";
		return m_aszOPPUrlAliasOrig;
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
	 * this opportunity is being loaded throught he db of feeds
	 */
	private boolean bFromFeeds=false;
	public void setFromFeeds(boolean value){
		if(value==true){
			bFromFeeds = value;
		}
		return;
	}
	public boolean getFromFeeds(){
		return bFromFeeds;
	}

	/**
	** frequency type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPFrequency=null;
	public void setOPPFrequency(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPFrequency = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPFrequency = aszTemp;
			return;
		}
		m_aszOPPFrequency = aszTemp.substring(0,iLen);
	}
	public String getOPPFrequency(){
		if(m_aszOPPFrequency == null) return "";
		return m_aszOPPFrequency;
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
	** Private or public opportunity? type INT
	**/
	private int m_iOPPPrivate=-1;
	public void setOPPPrivate(int number){
		m_iOPPPrivate = number;
	}
	public void setOPPPrivate(String number){
		m_iOPPPrivate = convertToInt(number);
		return;
	}
	public int getOPPPrivate(){
		return m_iOPPPrivate;
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
	** short_term type VARCHAR(10) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPShortTerm=null;
	public void setOPPShortTerm(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPShortTerm = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPShortTerm = aszTemp;
			return;
		}
		m_aszOPPShortTerm = aszTemp.substring(0,iLen);
	}
	public String getOPPShortTerm(){
		if(m_aszOPPShortTerm == null) return "";
		return m_aszOPPShortTerm;
	}

	
	
	/**
	** background_check type CHAR(10) in table org_opportunitiesinfo
	*  field_bg_check_value in um_content_type_volunteer_opportunity 
	**/
	private String m_aszOPPBackgroundCheck=null;
	public void setOPPBackgroundCheck(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPBackgroundCheck = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPBackgroundCheck = aszTemp;
			return;
		}
		m_aszOPPBackgroundCheck = aszTemp.substring(0,iLen);
	}
	public String getOPPBackgroundCheck(){
		if(m_aszOPPBackgroundCheck == null) return "";
		return m_aszOPPBackgroundCheck;
	}
	

	/**
	** addr_othrprov type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddrOthrprov=null;
	public void setOPPAddrOthrprov(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAddrOthrprov = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAddrOthrprov = aszTemp;
			return;
		}
		m_aszOPPAddrOthrprov = aszTemp.substring(0,iLen);
	}
	public String getOPPAddrOthrprov(){
		if(m_aszOPPAddrOthrprov == null) return "";
		return m_aszOPPAddrOthrprov;
	}

	/**
	** group_min type INT() in table org_opportunitiesinfo 
	**/
	private int m_iOPPGroupMin=0;
	public void setOPPGroupMin(int number){
		m_iOPPGroupMin = number;
	}
	public void setOPPGroupMin(String number){
		m_iOPPGroupMin = convertToInt(number);
		return;
	}
	public int getOPPGroupMin(){
		return m_iOPPGroupMin;
	}


	/**
	** group_max type INT() in table org_opportunitiesinfo 
	**/
	private int m_iOPPGroupMax=0;
	public void setOPPGroupMax(int number){
		m_iOPPGroupMax = number;
	}
	public void setOPPGroupMax(String number){
		m_iOPPGroupMax = convertToInt(number);
		return;
	}
	public int getOPPGroupMax(){
		return m_iOPPGroupMax;
	}


	/**
	** cost type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCost=null;
	public void setOPPCost(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCost = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCost = aszTemp;
			return;
		}
		m_aszOPPCost = aszTemp.substring(0,iLen);
	}
	public String getOPPCost(){
		if(m_aszOPPCost == null) return "";
		return m_aszOPPCost;
	}


	/**
	** compensation type VARCHAR(10)
	**/
	private String m_aszOPPCompensation=null;
	public void setOPPCompensation(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCompensation = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCompensation = aszTemp;
			return;
		}
		m_aszOPPCompensation = aszTemp.substring(0,iLen);
	}
	public String getOPPCompensation(){
		if(m_aszOPPCompensation == null) return "";
		return m_aszOPPCompensation;
	}


	/**
	** STM references from churches and individuals type TEXT() opportunities
	**/
	private String m_aszOPPSTMReferences=null;

	public void setOPPSTMReferences(String value){
		if(null == value){
			m_aszOPPSTMReferences = null;
			return;
		}
		m_aszOPPSTMReferences = value.trim();
	}
	public String getOPPSTMReferences(){
		if(m_aszOPPSTMReferences == null) return "";
		return m_aszOPPSTMReferences;
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
	** stipend type VARCHAR(10)
	**/
	private String m_aszOPPStipend=null;
	public void setOPPStipend(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPStipend = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPStipend = aszTemp;
			return;
		}
		m_aszOPPStipend = aszTemp.substring(0,iLen);
	}
	public String getOPPStipend(){
		if(m_aszOPPStipend == null) return "";
		return m_aszOPPStipend;
	}

	
	/**
	** Stipend compensation type LONG()
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
	** amnt_pd type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAmntPd=null;
	public void setOPPAmntPd(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAmntPd = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAmntPd = aszTemp;
			return;
		}
		m_aszOPPAmntPd = aszTemp.substring(0,iLen);
	}
	public String getOPPAmntPd(){
		if(m_aszOPPAmntPd == null) return "";
		return m_aszOPPAmntPd;
	}


	/**
	** cost_include type TEXT() in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCostInclude=null;

	public void setOPPCostInclude(String value){
		if(null == value){
			m_aszOPPCostInclude = null;
			return;
		}
		m_aszOPPCostInclude = value.trim();
	}
	public String getOPPCostInclude(){
		if(m_aszOPPCostInclude == null) return "";
		return m_aszOPPCostInclude;
	}


	/**
	** add_details type TEXT() in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAddDetails=null;

	public void setOPPAddDetails(String value){
		if(null == value){
			m_aszOPPAddDetails = null;
			return;
		}
		m_aszOPPAddDetails = value.trim();
	}
	public String getOPPAddDetails(){
		if(m_aszOPPAddDetails == null) return "";
		return m_aszOPPAddDetails;
	}


	/**
	** app_deadline type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPAppDeadline=null;
	public void setOPPAppDeadline(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPAppDeadline = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPAppDeadline = aszTemp;
			return;
		}
		m_aszOPPAppDeadline = aszTemp.substring(0,iLen);
	}
	public String getOPPAppDeadline(){
		if(m_aszOPPAppDeadline == null) return "";
		return m_aszOPPAppDeadline;
	}


	/**
	** duration type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPDuration=null;
	public void setOPPDuration(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPDuration = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPDuration = aszTemp;
			return;
		}
		m_aszOPPDuration = aszTemp.substring(0,iLen);
	}
	public String getOPPDuration(){
		if(m_aszOPPDuration == null) return "";
		return m_aszOPPDuration;
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
	** region type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPRegion=null;
	public void setOPPRegion(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPRegion = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPRegion = aszTemp;
			return;
		}
		m_aszOPPRegion = aszTemp.substring(0,iLen);
	}
	public String getOPPRegion(){
		if(m_aszOPPRegion == null) return "";
		return m_aszOPPRegion;
	}


	/**
	** reference_req type CHAR(10) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPReferenceReq=null;
	public void setOPPReferenceReq(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPReferenceReq = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPReferenceReq = aszTemp;
			return;
		}
		m_aszOPPReferenceReq = aszTemp.substring(0,iLen);
	}
	public String getOPPReferenceReq(){
		if(m_aszOPPReferenceReq == null) return "";
		return m_aszOPPReferenceReq;
	}



	/**
	** cost_usd type FLOAT() in table org_opportunitiesinfo 
	**/
	private double m_dOPPCostUsd=0.0;
	public void setOPPCostUsd(double number){
		m_dOPPCostUsd = number;
	}
	public void setOPPCostUsd(String number){
		m_dOPPCostUsd = convertToDouble(number);
		return;
	}
	public double getOPPCostUsd(){
		return m_dOPPCostUsd;
	}


	/**
	** cost_term type VARCHAR(20) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPCostTerm=null;
	public void setOPPCostTerm(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPCostTerm = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPCostTerm = aszTemp;
			return;
		}
		m_aszOPPCostTerm = aszTemp.substring(0,iLen);
	}
	public String getOPPCostTerm(){
		if(m_aszOPPCostTerm == null) return "";
		return m_aszOPPCostTerm;
	}


	
	// applic_deadline type DATETIME() in table org_opportunitiesinfo 
	
	private java.util.Date m_azdOPPApplicDeadline=null;
	public void setOPPApplicDeadline(java.util.Date value){
		if(value == null){
			m_azdOPPApplicDeadline = null;
			return;
		}
		m_azdOPPApplicDeadline = value;
	}
	public java.util.Date getOPPApplicDeadline(){
		if(m_azdOPPApplicDeadline == null) return null;
		return m_azdOPPApplicDeadline;
	}
	  
	/**
	** applic_deadline type LONG() in table org_opportunitiesinfo 
	**/
	private int m_iOPPApplicDeadlineNum=0;
	public void setOPPApplicDeadlineNum(int number){
		m_iOPPApplicDeadlineNum = number;
	}
	public void setOPPApplicDeadlineNum(String number){
		m_iOPPApplicDeadlineNum = convertToInt(number);
		return;
	}
	public int getOPPApplicDeadlineNum(){
		return m_iOPPApplicDeadlineNum;
	}

	
	
	/**
	** subdom type VARCHAR(100) in table org_opportunitiesinfo 
	**/
	private String m_aszOPPSubdom=null;
	public void setOPPSubdom(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSubdom = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSubdom = aszTemp;
			return;
		}
		m_aszOPPSubdom = aszTemp.substring(0,iLen);
	}
	public String getOPPSubdom(){
		if(m_aszOPPSubdom == null) return "";
		return m_aszOPPSubdom;
	}

	
	
	/**
	** Site email address VARCHAR(100)  
	**/
	private String m_aszOPPSiteEmail=null;
	public void setOPPSiteEmail(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPSiteEmail = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPSiteEmail = aszTemp;
			return;
		}
		m_aszOPPSiteEmail = aszTemp.substring(0,iLen);
	}
	public String getOPPSiteEmail(){
		if(m_aszOPPSiteEmail == null) return "";
		return m_aszOPPSiteEmail;
	}


	
	
	/**
	** Contact(s) email address(es), for listing purposes  
	**/
	private String m_aszOPPContactEmails=null;
	public void setOPPContactEmails(String value){
		int iLen=500;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPContactEmails = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPContactEmails = aszTemp;
			return;
		}
		m_aszOPPContactEmails = aszTemp.substring(0,iLen);
	}
	public String getOPPContactEmails(){
		if(m_aszOPPContactEmails == null) return "";
		return m_aszOPPContactEmails;
	}


	/**
	** internal_comment type TEXT() in table 
	**/
	private String m_aszOPPInternalComment=null;

	public void setOPPInternalComment(String value){
		if(null == value){
			m_aszOPPInternalComment = null;
			return;
		}
		m_aszOPPInternalComment = value.trim();
	}
	public String getOPPInternalComment(){
		if(m_aszOPPInternalComment == null) return "";
		return m_aszOPPInternalComment;
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
	** org_name type VARCHAR(100) for opportunity 
	**/
	private String m_aszOPPOrgName=null;
	public void setOPPOrgName(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPOrgName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPOrgName = aszTemp;
			return;
		}
		m_aszOPPOrgName = aszTemp.substring(0,iLen);
	}
	public String getOPPOrgName(){
		if(m_aszOPPOrgName == null) return "";
		return m_aszOPPOrgName;
	}



	//===========> End Code Generated Methods For Table org_opportunitiesinfo 
	//===========> End Code Generated Methods For Table org_opportunitiesinfo 
	//===========> End Code Generated Methods For Table org_opportunitiesinfo 

	//===========> START Code copied from object OrganizationInfoDTO for search  

	/**
	** org_name type VARCHAR(100) in table organizationinfo 
	**/
	private String m_aszORGOrgName=null;
	public void setORGOrgName(String value){
		int iLen=100;
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
	** onethird_p type CHAR(10) in table organizationinfo 
	*	serve the poor
	**/
	private String m_aszORGOnethirdP=null;
	public void setORGOnethirdP(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszORGOnethirdP = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszORGOnethirdP = aszTemp;
			return;
		}
		m_aszORGOnethirdP = aszTemp.substring(0,iLen);
	}
	public String getORGOnethirdP(){
		if(m_aszORGOnethirdP == null) return "";
		return m_aszORGOnethirdP;
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

	/**
	** organizational affiliations 
	**/
	private String m_aszOrgAffils=null;
	public void setOrgAffils(String value){
		int iLen=1000;
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
	** denominational affiliations
	**/
	private String m_aszDenomAffils=null;
	public void setDenomAffils(String value){
		int iLen=1000;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDenomAffils = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDenomAffils = aszTemp;
			return;
		}
		m_aszDenomAffils = aszTemp.substring(0,iLen);
	}
	public String getDenomAffils(){
		if(m_aszDenomAffils == null) return "";
		return m_aszDenomAffils;
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
	** Org Affil 1 tid type LONG()
	**/
	private int m_iOPPOrgAffil1TID=-1;
	public void setOPPOrgAffil1TID(int number){
		m_iOPPOrgAffil1TID = number;
	}
	public void setOPPOrgAffil1TID(String number){
		m_iOPPOrgAffil1TID = convertToInt(number);
		return;
	}
	public int getOPPOrgAffil1TID(){
		return m_iOPPOrgAffil1TID;
	}

	/**
	** Org Affil 2 tid type LONG()
	**/
	private int m_iOPPOrgAffil2TID=-1;
	public void setOPPOrgAffil2TID(int number){
		m_iOPPOrgAffil2TID = number;
	}
	public void setOPPOrgAffil2TID(String number){
		m_iOPPOrgAffil2TID = convertToInt(number);
		return;
	}
	public int getOPPOrgAffil2TID(){
		return m_iOPPOrgAffil2TID;
	}

	/**
	** Org Affil 1 tid type LONG()
	**/
	private int m_iOPPOrgAffil3TID=-1;
	public void setOPPOrgAffil3TID(int number){
		m_iOPPOrgAffil3TID = number;
	}
	public void setOPPOrgAffil3TID(String number){
		m_iOPPOrgAffil3TID = convertToInt(number);
		return;
	}
	public int getOPPOrgAffil3TID(){
		return m_iOPPOrgAffil3TID;
	}

	/**
	** Org Affil 4 tid type LONG()
	**/
	private int m_iOPPOrgAffil4TID=-1;
	public void setOPPOrgAffil4TID(int number){
		m_iOPPOrgAffil4TID = number;
	}
	public void setOPPOrgAffil4TID(String number){
		m_iOPPOrgAffil4TID = convertToInt(number);
		return;
	}
	public int getOPPOrgAffil4TID(){
		return m_iOPPOrgAffil4TID;
	}

	/**
	** Org Affil 5 tid type LONG()
	**/
	private int m_iOPPOrgAffil5TID=-1;
	public void setOPPOrgAffil5TID(int number){
		m_iOPPOrgAffil5TID = number;
	}
	public void setOPPOrgAffil5TID(String number){
		m_iOPPOrgAffil5TID = convertToInt(number);
		return;
	}
	public int getOPPOrgAffil5TID(){
		return m_iOPPOrgAffil5TID;
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

	
	
	/*
	 * multi-select Benefits
	 */
	private int[] a_iBenefitsArray=null;
	public void setBenefitsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iBenefitsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iBenefitsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iBenefitsArray = a_iTemp;
			return;
		}
	}
	public int[] getBenefitsArray(){
		if(a_iBenefitsArray == null) {
			a_iBenefitsArray=new int[0];
			return a_iBenefitsArray;
		}
		return a_iBenefitsArray;
	}
	
	/*
	 * multi-select Service Areas
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
	
	/**
	 ** causetopics comma seperated string of TIDs
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
	** internship type for opportunity - currently only response is City Vision Internship (blank or "City Vision Internship")
	**/
	private String m_aszOPPPInternType=null;
	public void setOPPInternType(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszOPPPInternType = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszOPPPInternType = aszTemp;
			return;
		}
		m_aszOPPPInternType = aszTemp.substring(0,iLen);
	}
	public String getOPPInternType(){
		if(m_aszOPPPInternType == null) return "";
		return m_aszOPPPInternType;
	}
	
	

	private FormFile m_questionnaireClientFile = null;
    public void setQuestionnaireClientFile(FormFile f) {
    	if(f==null) m_questionnaireClientFile = null;
    	m_questionnaireClientFile = f;
    }
    public FormFile getQuestionnaireClientFile() {
    	return m_questionnaireClientFile;
    }
	
	private File m_questionnaireServerFile = null;
	public void setQuestionnaireServerFile(File f) {
		m_questionnaireServerFile = f;
	}
	public File getQuestionnaireServerFile() {
		return m_questionnaireServerFile;
	}
	
	public String getQuestionnaireServerFileExtension() {
		if(getQuestionnaireServerFile() == null) return null;
		String[] tokens = getQuestionnaireServerFile().getName().split("\\.");
		return tokens[tokens.length-1];
	}
	
	public String getQuestionnairePublicPath() {
		if(getQuestionnaireServerFile() == null) return null;
		return "/opportunities/" + getOPPNID() + "/application";
	}
	
	private String m_questionnaireType;
	public void setQuestionnaireType(String s) {
		m_questionnaireType = s;
	}
	public String getQuestionnaireType() {
		return m_questionnaireType;
	}
	
	private int m_questionnaireId;
	public void setQuestionnaireId(int id) {
		m_questionnaireId = id;
	}
	public int getQuestionnaireId() {
		return m_questionnaireId;
	}
	
	private int m_opportunitiesQuestionnaireId;
	public void setOpportunitiesQuestionnaireId(int id) {
		m_opportunitiesQuestionnaireId = id;
	}
	public int getOpportunitiesQuestionnaireId() {
		return m_opportunitiesQuestionnaireId;
	}
	
	private int m_resumeRequired;
	public void setResumeRequired(int i) {
		m_resumeRequired = i;
	}
	public int getResumeRequired() {
		return m_resumeRequired;
	}
	
	private int m_coverLetterRequired;
	public void setCoverLetterRequired(int i) {
		m_coverLetterRequired = i;
	}
	public int getCoverLetterRequired() {
		return m_coverLetterRequired;
	}
	
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
	
	public boolean isCityVisionInternship() {
		return this.getOPPInternType().equals(CITY_VISION_COLLEGE);
	}

	public boolean isCityVisionInternshipLegacy() {
		String CITY_VISION_COLLEGE_LEGACY = "City Vision College";
		return this.getORGPartner().equals(CITY_VISION_COLLEGE_LEGACY)
			|| this.getORGPartner2().equals(CITY_VISION_COLLEGE_LEGACY)
			|| this.getORGPartner3().equals(CITY_VISION_COLLEGE_LEGACY)
			|| this.getORGPartner4().equals(CITY_VISION_COLLEGE_LEGACY)
			|| this.getORGPartner5().equals(CITY_VISION_COLLEGE_LEGACY);
	}

	private String searchPageLogoURL=null;
	public void setOPPSearchPageLogoURL(String dbString) {
		this.searchPageLogoURL = dbString;
	}
	public String getOPPSearchPageLogoURL() {
		return this.searchPageLogoURL;
	}
	
	private String detailPageLogoURL=null;
	public void setOPPDetailPageLogoURL(String dbString) {
		this.detailPageLogoURL = dbString;
	}
	public String getOPPDetailPageLogoURL() {
		return this.detailPageLogoURL;
	}

	private String sourceURL=null;
	public void setOPPSourceURL(String dbString) {
		this.sourceURL = dbString;
	}
	
	public String getOPPSourceURL() {
		return this.sourceURL;
	}
	
	
	
	
	
	
	
	//===========> END Code copied from object OrganizationInfoDTO for search  

	private List<RequiredDocumentDTO> m_requiredDocuments;
	
	public void setRequiredDocuments(List<RequiredDocumentDTO> docs) {
		m_requiredDocuments = docs;
	}
	
	public List<RequiredDocumentDTO> getRequiredDocuments() {
		return m_requiredDocuments;
	}
	
	private List<Integer> m_requiredDocumentToRemoveNids = new LinkedList<Integer>();
	
	public void setRequiredDocumentToRemoveNids(List<Integer> nids) {
		m_requiredDocumentToRemoveNids = nids;
	}
	
	public List<Integer> getRequiredDocumentToRemoveNids() {
		return m_requiredDocumentToRemoveNids;
	}
	
	private List<RequiredDocumentDTO> m_requiredDocumentsToAdd = new LinkedList<RequiredDocumentDTO>();
	
	public void setRequiredDocumentsToAdd(List<RequiredDocumentDTO> docs) {
		m_requiredDocumentsToAdd = docs;
	}
	
	public List<RequiredDocumentDTO> getRequiredDocumentsToAdd() {
		return m_requiredDocumentsToAdd;
	}
	
	public List<RequiredDocumentDTO> getRequiredDocumentsToShowOnForm() {
		List<RequiredDocumentDTO> docs = getRequiredDocuments();
		for(int nid : getRequiredDocumentToRemoveNids()) {
			for(int i = 0; i < docs.size(); i++) {
				if(docs.get(i).getNid() == nid)
					docs.remove(i);
			}
		}
		return docs;
	}
	private static String CITY_VISION_COLLEGE = "City Vision Internship";

}
/* End Of Code Generated DataStore Class OrgOpportunityInfoDTO */
