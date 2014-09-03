package com.abrecorp.opensource.dataobj;

/**
* Code Generated Data Transfer Object DTO Class
* For Table 
*/

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ApplicationInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	public static final int LOADBY_APPROVED_SITE=100;
	public static final int LOADBY_ORG=101;
	public static final int LOADBY_OPP=102;
	public static final int LOADBY_APPLICANT_USER=103;
	
	public static final int LOADBY_EMAIL_SCHEDULER = 110;
	public static final int LOADBY_EMAIL_SCHEDULER_APPLICANT = 111;
	public static final int LOADBY_EMAIL_APPLICNID = 115;
	public static final int LOADBY_EMAIL_APPLICORGNID = 116;

	/**
	* constructor
	*/
	public ApplicationInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			ApplicationInfoDTO el = (ApplicationInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}


	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===<


	/**
	** applicant's nid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iUPNID=0;
	public void setUPNID(int number){
		m_iUPNID = number;
	}
	public void setUPNID(String number){
		m_iUPNID = convertToInt(number);
		return;
	}
	public int getUPNID(){
		return m_iUPNID;
	}

	/**
	** applicant's m_iLID type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iLID=0;
	public void setLID(int number){
		m_iLID = number;
	}
	public void setLID(String number){
		m_iLID = convertToInt(number);
		return;
	}
	public int getLID(){
		return m_iLID;
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
	** m_iOrgDelta.. 
	**/
	private int m_iOrgDelta=0;
	public void setORGDelta(int number){
		m_iOrgDelta = number;
	}
	public void setORGDelta(String number){
		m_iOrgDelta = convertToInt(number);
		return;
	}
	public int getORGDelta(){
		return m_iOrgDelta;
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


	/*
	 * multi-select ORGNIDs
	 */
	private int[] a_iORGNIDsArray=null;
	public void setORGNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iORGNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iORGNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iORGNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getORGNIDsArray(){
		if(a_iORGNIDsArray == null) {
			a_iORGNIDsArray=new int[0];
			return a_iORGNIDsArray;
		}
		return a_iORGNIDsArray;
	}



	/*
	 * multi-select a_iOPPNIDsArray
	 */
	private int[] a_iOPPNIDsArray=null;
	public void setOPPNIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iOPPNIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iOPPNIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iOPPNIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getOPPNIDsArray(){
		if(a_iOPPNIDsArray == null) {
			a_iOPPNIDsArray=new int[0];
			return a_iOPPNIDsArray;
		}
		return a_iOPPNIDsArray;
	}


	/*
	 * multi-select org names
	 */
	private String[] a_aszORGNamesArray=null;
	public void setORGNamesArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszORGNamesArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszORGNamesArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszORGNamesArray = a_aszTemp;
			return;
		}
	}
	public String[] getORGNamesArray(){
		if(a_aszORGNamesArray == null) {
			a_aszORGNamesArray=new String[0];
			return a_aszORGNamesArray;
		}
		return a_aszORGNamesArray;
	}

	/*
	 * multi-select opp titles
	 */
	private String[] a_aszOPPTitlesArray=null;
	public void setOPPTitlesArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszOPPTitlesArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszOPPTitlesArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszOPPTitlesArray = a_aszTemp;
			return;
		}
	}
	public String[] getTitlesArray(){
		if(a_aszOPPTitlesArray == null) {
			a_aszOPPTitlesArray=new String[0];
			return a_aszOPPTitlesArray;
		}
		return a_aszOPPTitlesArray;
	}


	/**
	** m_dLastUpdated
	**/
	private Date m_dLastUpdated=null;
	public void setLastUpdatedDt(Date date){
		m_dLastUpdated = date;
	}
	public Date getLastUpdatedDt(){
		return m_dLastUpdated;
	}

	/**
	** volunteer nid type LONG() in table um_content_type_volunteer_opportunity, um_node, ... 
	**/
	private int m_iVolNID=0;
	public void setVolNID(int number){
		m_iVolNID = number;
	}
	public void setVolNID(String number){
		m_iVolNID = convertToInt(number);
		return;
	}
	public int getVolNID(){
		return m_iVolNID;
	}



	/**
	** 
	**/
	private int m_iNID=0;
	public void setNID(int number){
		m_iNID = number;
	}
	public void setNID(String number){
		m_iNID = convertToInt(number);
		return;
	}
	public int getNID(){
		return m_iNID;
	}

	/**
	** 
	**/
	private int m_iVID=0;
	public void setVID(int number){
		m_iVID = number;
	}
	public void setVID(String number){
		m_iVID = convertToInt(number);
		return;
	}
	public int getVID(){
		return m_iVID;
	}



	/**
	** primary_person type LONG() m_iicantUID
	**/
	private int m_iUID=0;
	public void setUID(int number){
		m_iUID = number;
	}
	public void setUID(String number){
		m_iUID = convertToInt(number);
		return;
	}
	public int getUID(){
		return m_iUID;
	}


	// create_dt type LONG() in table drupal um_node
	
	private int m_iCreateDtNum=0;
	public void setCreateDtNum(int number){
		m_iCreateDtNum = number;
	}
	public void setCreateDtNum(String number){
		m_iCreateDtNum = convertToInt(number);
		return;
	}
	public int getCreateDtNum(){
		return m_iCreateDtNum;
	}


	// create_dt type DATETIME() in table org_opportunitiesinfo 
	
	private java.util.Date m_azdCreateDt=null;
	public void setCreateDt(java.util.Date value){
		if(value == null){
			m_azdCreateDt = null;
			return;
		}
		m_azdCreateDt = value;
	}
	public java.util.Date getCreateDt(){
		if(m_azdCreateDt == null) return null;
		return m_azdCreateDt;
	}



	/**
	** title 
	**/
	private String m_aszTitle=null;
	public void setTitle(String value){
		int iLen=200;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszTitle = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszTitle = aszTemp;
			return;
		}
		m_aszTitle = aszTemp.substring(0,iLen);
	}
	public String getTitle(){
		if(m_aszTitle == null) return "";
		return m_aszTitle;
	}


	/**
	** name_first type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszNameFirst=null;
	public void setNameFirst(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszNameFirst = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszNameFirst = aszTemp;
			return;
		}
		m_aszNameFirst = aszTemp.substring(0,iLen);
	}
	public String getNameFirst(){
		if(m_aszNameFirst == null) return "";
		return m_aszNameFirst;
	}


	/**
	** name_last type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszNameLast=null;
	public void setNameLast(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszNameLast = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszNameLast = aszTemp;
			return;
		}
		m_aszNameLast = aszTemp.substring(0,iLen);
	}
	public String getNameLast(){
		if(m_aszNameLast == null) return "";
		return m_aszNameLast;
	}


	/**
	** email1_addr type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszEmailAddr=null;
	public void setEmailAddr(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszEmailAddr = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszEmailAddr = aszTemp;
			return;
		}
		m_aszEmailAddr = aszTemp.substring(0,iLen);
	}
	public String getEmailAddr(){
		if(m_aszEmailAddr == null) return "";
		return m_aszEmailAddr;
	}


	/**
	** phone1 type VARCHAR(20) in table userprofileinfo 
	**/
	private String m_aszPhone=null;
	public void setPhone(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPhone = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPhone = aszTemp;
			return;
		}
		m_aszPhone = aszTemp.substring(0,iLen);
	}
	public String getPhone(){
		if(m_aszPhone == null) return "";
		return m_aszPhone;
	}


	/**
	** is the individual Christian? Yes/No - uprofile
	**/
	private String m_aszChristian=null;
	public void setChristian(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszChristian = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszChristian = aszTemp;
			return;
		}
		m_aszChristian = aszTemp.substring(0,iLen);
	}
	public String getChristian(){
		if(m_aszChristian == null) return "";
		return m_aszChristian;
	}

	/**
	** is the individual 18 or older? Yes/No - uprofile
	**/
	private String m_aszAgeRequirement=null;
	public void setAgeRequirement(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAgeRequirement = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAgeRequirement = aszTemp;
			return;
		}
		m_aszAgeRequirement = aszTemp.substring(0,iLen);
	}
	public String getAgeRequirement(){
		if(m_aszAgeRequirement == null) return "";
		return m_aszAgeRequirement;
	}

	/**
	** Do you have a high school diploma or equivalent, such as a GED?
	**/
	private String m_aszDiploma=null;
	public void setDiploma(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszDiploma = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDiploma = aszTemp;
			return;
		}
		m_aszDiploma = aszTemp.substring(0,iLen);
	}
	public String getDiploma(){
		if(m_aszDiploma == null) return "";
		return m_aszDiploma;
	}


	/**
	**Are you able to commit to the full length of the internship?
	**/
	private String m_aszTimeCommitAbility=null;
	public void setTimeCommitAbility(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszTimeCommitAbility = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszTimeCommitAbility = aszTemp;
			return;
		}
		m_aszTimeCommitAbility = aszTemp.substring(0,iLen);
	}
	public String getTimeCommitAbility(){
		if(m_aszTimeCommitAbility == null) return "";
		return m_aszTimeCommitAbility;
	}


	/**
	** If you do not have a bachelor's degree, have you been active in school in the past 5 years (either attending high school or comp
	**/
	private String m_aszBachelorsAttending=null;
	public void setBachelorsAttending(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszBachelorsAttending = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszBachelorsAttending = aszTemp;
			return;
		}
		m_aszBachelorsAttending = aszTemp.substring(0,iLen);
	}
	public String getBachelorsAttending(){
		if(m_aszBachelorsAttending == null) return "";
		return m_aszBachelorsAttending;
	}


	/**
	** Please share your testimony as a Christian: how you came to know God and your story with God. (Take the time to answer thoroughly
	**/
	private String m_aszTestimony=null;
	public void setTestimony(String value){
		if(null == value){
			m_aszTestimony = null;
			return;
		}
		m_aszTestimony = value.trim();
	}
	public String getTestimony(){
		if(m_aszTestimony == null) return "";
		return m_aszTestimony;
	}


	/**
	** Do you have a particular geographic preference?
	**/
	private String m_aszGeoPref=null;
	public void setGeoPref(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszGeoPref = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszGeoPref = aszTemp;
			return;
		}
		m_aszGeoPref = aszTemp.substring(0,iLen);
	}
	public String getGeoPref(){
		if(m_aszGeoPref == null) return "";
		return m_aszGeoPref;
	}


	/**
	** Why are you interested in this program and these positions specifically? (Take the time to answer thoroughly.)
	**/
	private String m_aszInternReason=null;
	public void setInternReason(String value){
		if(null == value){
			m_aszInternReason = null;
			return;
		}
		m_aszInternReason = value.trim();
	}
	public String getInternReason(){
		if(m_aszInternReason == null) return "";
		return m_aszInternReason;
	}


	/**
	** If you speak any languages besides English, please list them and describe your level of competence with each.
	**/
	private String m_aszLang=null;
	public void setLang(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszLang = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszLang = aszTemp;
			return;
		}
		m_aszLang = aszTemp.substring(0,iLen);
	}
	public String getLang(){
		if(m_aszLang == null) return "";
		return m_aszLang;
	}


	/**
	** Are you actively attending a local church?
	**/
	private String m_aszChurch=null;
	public void setChurch(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszChurch = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszChurch = aszTemp;
			return;
		}
		m_aszChurch = aszTemp.substring(0,iLen);
	}
	public String getChurch(){
		if(m_aszChurch == null) return "";
		return m_aszChurch;
	}


	/**
	** If you didn't do a City Vision internship, in what degree program would you like to major in college?
	**/
	private String m_aszMajor=null;
	public void setMajor(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszMajor = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszMajor = aszTemp;
			return;
		}
		m_aszMajor = aszTemp.substring(0,iLen);
	}
	public String getMajor(){
		if(m_aszMajor == null) return "";
		return m_aszMajor;
	}


	/**
	** Do you currently have a bachelor's degree?
	**/
	private String m_aszHasBachelors=null;
	public void setHasBachelors(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszHasBachelors = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszHasBachelors = aszTemp;
			return;
		}
		m_aszHasBachelors = aszTemp.substring(0,iLen);
	}
	public String getHasBachelors(){
		if(m_aszHasBachelors == null) return "";
		return m_aszHasBachelors;
	}


	/**
	** About how many college credits have you earned?
	**/
	private int m_iCredits=0;
	public void setCredits(int number){
		m_iCredits = number;
	}
	public void setCredits(String number){
		m_iCredits = convertToInt(number);
		return;
	}
	public int getCredits(){
		return m_iCredits;
	}

	/**
	** Credits range (for display purposes)
	**/
	private String m_aszCreditsRange=null;
	public void setCreditsRange(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszCreditsRange = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCreditsRange = aszTemp;
			return;
		}
		m_aszCreditsRange = aszTemp.substring(0,iLen);
	}
	public String getCreditsRange(){
		if(m_aszCreditsRange == null) return "";
		return m_aszCreditsRange;
	}


	/**
	** What are your career goals and how do you see this internship and degree program fitting in with those goals?
	**/
	private String m_aszCareerGoals=null;
	public void setCareerGoals(String value){
		if(null == value){
			m_aszCareerGoals = null;
			return;
		}
		m_aszCareerGoals = value.trim();
	}
	public String getCareerGoals(){
		if(m_aszCareerGoals == null) return "";
		return m_aszCareerGoals;
	}


	/**
	** This is a 30 to 35 hour/week position. Interns without a Bachelors will also be required at least an additional 15-20 hours of s...
	**/
	private String m_aszHrlyCommit=null;
	public void setHrlyCommit(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszHrlyCommit = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszHrlyCommit = aszTemp;
			return;
		}
		m_aszHrlyCommit = aszTemp.substring(0,iLen);
	}
	public String getHrlyCommit(){
		if(m_aszHrlyCommit == null) return "";
		return m_aszHrlyCommit;
	}


	/**
	** Can you really live on the amount paid in this internship? Qualifying students may also receive Pell grants, student loans, or o
	**/
	private String m_aszLivableStipend=null;
	public void setLivableStipend(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszLivableStipend = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszLivableStipend = aszTemp;
			return;
		}
		m_aszLivableStipend = aszTemp.substring(0,iLen);
	}
	public String getLivableStipend(){
		if(m_aszLivableStipend == null) return "";
		return m_aszLivableStipend;
	}


	/**
	** If you answered "Yes" to the previous question, please explain how this amount will be sustainable for you. Otherwise, state the
	**/
	private String m_aszLivableStipendExpl=null;
	public void setLivableStipendExpl(String value){
		if(null == value){
			m_aszLivableStipendExpl = null;
			return;
		}
		m_aszLivableStipendExpl = value.trim();
	}
	public String getLivableStipendExpl(){
		if(m_aszLivableStipendExpl == null) return "";
		return m_aszLivableStipendExpl;
	}


	/**
	** Have you ever been convicted of a crime?
	**/
	private String m_aszCrimRecord=null;
	public void setCrimRecord(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszCrimRecord = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCrimRecord = aszTemp;
			return;
		}
		m_aszCrimRecord = aszTemp.substring(0,iLen);
	}
	public String getCrimRecord(){
		if(m_aszCrimRecord == null) return "";
		return m_aszCrimRecord;
	}


	/**
	** Please describe the circumstances regarding your criminal conviction:
	**/
	private String m_aszCrimDescrip=null;
	public void setCrimDescrip(String value){
		if(null == value){
			m_aszCrimDescrip = null;
			return;
		}
		m_aszCrimDescrip = value.trim();
	}
	public String getCrimDescrip(){
		if(m_aszCrimDescrip == null) return "";
		return m_aszCrimDescrip;
	}



	/**
	** Do you need housing and will you accept housing on site at a Christian homeless shelter or other ministry housing?
	**/
	private String m_aszHousing=null;
	public void setHousing(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszHousing = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszHousing = aszTemp;
			return;
		}
		m_aszHousing = aszTemp.substring(0,iLen);
	}
	public String getHousing(){
		if(m_aszHousing == null) return "";
		return m_aszHousing;
	}



	/**
	**Are you ying with a particular service site (one of our partnering churches, ministries, or nonprofit organizations) in mind
	**/
	private String m_aszServiceSite=null;
	public void setServiceSite(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszServiceSite = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszServiceSite = aszTemp;
			return;
		}
		m_aszServiceSite = aszTemp.substring(0,iLen);
	}
	public String getServiceSite(){
		if(m_aszServiceSite == null) return "";
		return m_aszServiceSite;
	}



	/**
	** Do you have any further information to share about your location preference?
	**/
	private String m_aszLocPrefInfo=null;
	public void setLocPrefInfo(String value){
		if(null == value){
			m_aszLocPrefInfo = null;
			return;
		}
		m_aszLocPrefInfo = value.trim();
	}
	public String getLocPrefInfo(){
		if(m_aszLocPrefInfo == null) return "";
		return m_aszLocPrefInfo;
	}



	/**
	** When are you available to start?
	**/
	private String m_aszStartTime=null;
	public void setStartTime(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszStartTime = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszStartTime = aszTemp;
			return;
		}
		m_aszStartTime = aszTemp.substring(0,iLen);
	}
	public String getStartTime(){
		if(m_aszStartTime == null) return "";
		return m_aszStartTime;
	}



	/**
	** If we are unable to hire a good candidate, we sometimes forward his/her resume to another comparable organization. Are you comfo
	**/
	private String m_aszForwardResume=null;
	public void setForwardResume(String value){
		int iLen=512;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszForwardResume = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszForwardResume = aszTemp;
			return;
		}
		m_aszForwardResume = aszTemp.substring(0,iLen);
	}
	public String getForwardResume(){
		if(m_aszForwardResume == null) return "";
		return m_aszForwardResume;
	}



	/**
	** Do you have access to a webcam (so that we could see you if we were to interview you by phone)?
	**/
	private String m_aszWebcam=null;
	public void setWebcam(String value){
		int iLen=10;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszWebcam = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszWebcam = aszTemp;
			return;
		}
		m_aszWebcam = aszTemp.substring(0,iLen);
	}
	public String getWebcam(){
		if(m_aszWebcam == null) return "";
		return m_aszWebcam;
	}



	/**
	** Your Skype Name (if you have one)
	**/
	private String m_aszSkype=null;
	public void setSkype(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszSkype = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSkype = aszTemp;
			return;
		}
		m_aszSkype = aszTemp.substring(0,iLen);
	}
	public String getSkype(){
		if(m_aszSkype == null) return "";
		return m_aszSkype;
	}



	/**
	** Are you interested in a one year internship or a summer internship?
	**/
	private String m_aszInternLength=null;
	public void setInternLength(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszInternLength = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszInternLength = aszTemp;
			return;
		}
		m_aszInternLength = aszTemp.substring(0,iLen);
	}
	public String getInternLength(){
		if(m_aszInternLength == null) return "";
		return m_aszInternLength;
	}



	/**
	** Pastoral Reference Name
	**/
	private String m_aszPastoralRef=null;
	public void setPastoralRef(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszPastoralRef = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPastoralRef = aszTemp;
			return;
		}
		m_aszPastoralRef = aszTemp.substring(0,iLen);
	}
	public String getPastoralRef(){
		if(m_aszPastoralRef == null) return "";
		return m_aszPastoralRef;
	}



	/**
	** Pastoral Reference Church/Ministry
	**/
	private String m_aszPastoralRefChurch=null;
	public void setPastoralRefChurch(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszPastoralRefChurch = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPastoralRefChurch = aszTemp;
			return;
		}
		m_aszPastoralRefChurch = aszTemp.substring(0,iLen);
	}
	public String getPastoralRefChurch(){
		if(m_aszPastoralRefChurch == null) return "";
		return m_aszPastoralRefChurch;
	}



	/**
	** Pastoral Reference Phone
	**/
	private String m_aszPastoralRefPhone=null;
	public void setPastoralRefPhone(String value){
		int iLen=25;
		String aszTemp = value;
		String PastoralRefPhone = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszPastoralRefPhone = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPastoralRefPhone = aszTemp;
			return;
		}
		m_aszPastoralRefPhone = aszTemp.substring(0,iLen);
	}
	public String getPastoralRefPhone(){
		if(m_aszPastoralRefPhone == null) return "";
		return m_aszPastoralRefPhone;
	}



	/**
	** Pastoral Reference Email
	**/
	private String m_aszPastoralRefEmail=null;
	public void setPastoralRefEmail(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszPastoralRefEmail = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPastoralRefEmail = aszTemp;
			return;
		}
		m_aszPastoralRefEmail = aszTemp.substring(0,iLen);
	}
	public String getPastoralRefEmail(){
		if(m_aszPastoralRefEmail == null) return "";
		return m_aszPastoralRefEmail;
	}



	/**
	** Professional Reference Name
	**/
	private String m_aszProfRef=null;
	public void setProfRef(String value){
		int iLen=25;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszProfRef = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProfRef = aszTemp;
			return;
		}
		m_aszProfRef = aszTemp.substring(0,iLen);
	}
	public String getProfRef(){
		if(m_aszProfRef == null) return "";
		return m_aszProfRef;
	}



	/**
	** Professional Reference Organization
	**/
	private String m_aszProfRefOrg=null;
	public void setProfRefOrg(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszProfRefOrg = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProfRefOrg = aszTemp;
			return;
		}
		m_aszProfRefOrg = aszTemp.substring(0,iLen);
	}
	public String getProfRefOrg(){
		if(m_aszProfRefOrg == null) return "";
		return m_aszProfRefOrg;
	}



	/**
	** Professional Reference Phone
	**/
	private String m_aszProfRefPhone=null;
	public void setProfRefPhone(String value){
		int iLen=25;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszProfRefPhone = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProfRefPhone = aszTemp;
			return;
		}
		m_aszProfRefPhone = aszTemp.substring(0,iLen);
	}
	public String getProfRefPhone(){
		if(m_aszProfRefPhone == null) return "";
		return m_aszProfRefPhone;
	}



	/** Professional Reference Email
	**/
	private String m_aszProfRefEmail=null;
	public void setProfRefEmail(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszProfRefEmail = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProfRefEmail = aszTemp;
			return;
		}
		m_aszProfRefEmail = aszTemp.substring(0,iLen);
	}
	public String getProfRefEmail(){
		if(m_aszProfRefEmail == null) return "";
		return m_aszProfRefEmail;
	}



	/**
	** Are you male or female?
	**/
	private String m_aszGender=null;
	public void setGender(String value){
		int iLen=25;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszGender = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszGender = aszTemp;
			return;
		}
		m_aszGender = aszTemp.substring(0,iLen);
	}
	public String getGender(){
		if(m_aszGender == null) return "";
		return m_aszGender;
	}

	// type DATETIME() in table m_azdApplyDt 
	
	private java.util.Date m_azdApplyDt=null;
	public void setApplyDt(String value){
		if(value == null){
			m_azdApplyDt = null;
			return;
		}
		Date date = null;
		try{
			date = new SimpleDateFormat("M/d/yyyy", Locale.ENGLISH).parse(value);
		}catch(Exception e){
		}

		m_azdApplyDt = date;
	}
	public void setApplyDt(java.util.Date value){
		if(value == null){
			m_azdApplyDt = null;
			return;
		}
		m_azdApplyDt = value;
	}
	public java.util.Date getApplyDt(){
		if(m_azdApplyDt == null) return null;
		return m_azdApplyDt;
	}

	// type DATETIME() in table m_azdDOBDt 
	
	private java.util.Date m_azdDOBDt=null;
	public void setDOBDt(String value){
		if(value == null){
			m_azdDOBDt = null;
			return;
		}
		Date date = null;
		try{
			date = new SimpleDateFormat("M/d/yyyy", Locale.ENGLISH).parse(value);
		}catch(Exception e){
		}

		m_azdDOBDt = date;
	}
	public void setDOBDt(java.util.Date value){
		if(value == null){
			m_azdDOBDt = null;
			return;
		}
		m_azdDOBDt = value;
	}
	public java.util.Date getDOBDt(){
		if(m_azdDOBDt == null) return null;
		return m_azdDOBDt;
	}


	/**
	** Age range (for display purposes)
	**/
	private String m_aszAgeRange=null;
	public void setAgeRange(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszAgeRange = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAgeRange = aszTemp;
			return;
		}
		m_aszAgeRange = aszTemp.substring(0,iLen);
	}
	public String getAgeRange(){
		if(m_aszAgeRange == null) return "";
		return m_aszAgeRange;
	}


	/**
	** CVInternApplic (for display purposes)
	**/
	private String m_aszCVInternApplic=null;
	public void setCVInternApplic(String value){
		int iLen=50;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszCVInternApplic = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCVInternApplic = aszTemp;
			return;
		}
		m_aszCVInternApplic = aszTemp.substring(0,iLen);
	}
	public String getCVInternApplic(){
		if(m_aszCVInternApplic == null) return "";
		return m_aszCVInternApplic;
	}


	/**
	** m_aszCityTax type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszCityTax=null;
	public void setCityTax(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCityTax = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCityTax = aszTemp;
			return;
		}
		m_aszCityTax = aszTemp.substring(0,iLen);
	}
	public String getCityTax(){
		if(m_aszCityTax == null) return "";
		return m_aszCityTax;
	}


	/**
	** m_aszProvTax type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszProvTax=null;
	public void setProvTax(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszProvTax = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszProvTax = aszTemp;
			return;
		}
		m_aszProvTax = aszTemp.substring(0,iLen);
	}
	public String getProvTax(){
		if(m_aszProvTax == null) return "";
		return m_aszProvTax;
	}


	/**
	** m_aszCountryTax type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszCountryTax=null;
	public void setCountryTax(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCountryTax = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCountryTax = aszTemp;
			return;
		}
		m_aszCountryTax = aszTemp.substring(0,iLen);
	}
	public String getCountryTax(){
		if(m_aszCountryTax == null) return "";
		return m_aszCountryTax;
	}


	/**
	** addr_line1 type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszAddrLine1=null;
	public void setAddrLine1(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAddrLine1 = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAddrLine1 = aszTemp;
			return;
		}
		m_aszAddrLine1 = aszTemp.substring(0,iLen);
	}
	public String getAddrLine1(){
		if(m_aszAddrLine1 == null) return "";
		return m_aszAddrLine1;
	}



	/**
	** addr_city type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszAddrCity=null;
	public void setAddrCity(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAddrCity = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAddrCity = aszTemp;
			return;
		}
		m_aszAddrCity = aszTemp.substring(0,iLen);
	}
	public String getAddrCity(){
		if(m_aszAddrCity == null) return "";
		return m_aszAddrCity;
	}


	/**
	** addr_stateprov type VARCHAR(100) in table userprofileinfo 
	**/
	private String m_aszAddrStateprov=null;
	public void setAddrStateprov(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAddrStateprov = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAddrStateprov = aszTemp;
			return;
		}
		m_aszAddrStateprov = aszTemp.substring(0,iLen);
	}
	public String getAddrStateprov(){
		if(m_aszAddrStateprov == null) return "";
		return m_aszAddrStateprov;
	}


	/**
	** addr_postalcode type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszAddrPostalcode=null;
	public void setAddrPostalcode(String value){
		int iLen=40;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAddrPostalcode = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAddrPostalcode = aszTemp;
			return;
		}
		m_aszAddrPostalcode = aszTemp.substring(0,iLen);
	}
	public String getAddrPostalcode(){
		if(m_aszAddrPostalcode == null) return "";
		return m_aszAddrPostalcode;
	}


	/**
	** addr_country_name type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszAddrCountryName=null;
	public void setAddrCountryName(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszAddrCountryName = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszAddrCountryName = aszTemp;
			return;
		}
		m_aszAddrCountryName = aszTemp.substring(0,iLen);
	}
	public String getAddrCountryName(){
		if(m_aszAddrCountryName == null) return "";
		return m_aszAddrCountryName;
	}


	/**
	** m_aszIneligibilityReason type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszIneligibilityReason=null;
	public void setIneligibilityReason(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszIneligibilityReason = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszIneligibilityReason = aszTemp;
			return;
		}
		m_aszIneligibilityReason = aszTemp.substring(0,iLen);
	}
	public String getIneligibilityReason(){
		if(m_aszIneligibilityReason == null) return "";
		return m_aszIneligibilityReason;
	}


	/**
	** m_aszCVDegreeCareerGoals type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszCVDegreeCareerGoals=null;
	public void setCVDegreeCareerGoals(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszCVDegreeCareerGoals = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszCVDegreeCareerGoals = aszTemp;
			return;
		}
		m_aszCVDegreeCareerGoals = aszTemp.substring(0,iLen);
	}
	public String getCVDegreeCareerGoals(){
		if(m_aszCVDegreeCareerGoals == null) return "";
		return m_aszCVDegreeCareerGoals;
	}


	/**
	** m_aszLastYrActiveHS type VARCHAR(255) in table userprofileinfo 
	**/
	private int m_iLastYrActiveHS=0;
	public void setLastYrActiveHS(int number){
		m_iLastYrActiveHS = number;
	}
	public void setLastYrActiveHS(String number){
		m_iLastYrActiveHS = convertToInt(number);
		return;
	}
	public int getLastYrActiveHS(){
		return m_iLastYrActiveHS;
	}



	//=== END    Fields ===
	//=== END    Fields ===
	//=== END    Fields ===

	
	
	//=== BEGIN    Taxonomy ===
	//=== BEGIN    Taxonomy ===
	//=== BEGIN    Taxonomy ===


	/**
	** City Vision Intern Citizenship Status
	**/
	private int m_iCitizenTID=-1;
	public void setCitizenTID(int number){
		m_iCitizenTID = number;
	}
	public void setCitizenTID(String number){
		m_iCitizenTID = convertToInt(number);
		return;
	}
	public int getCitizenTID(){
		return m_iCitizenTID;
	}


	/**
	** City Vision Intern Degree Program
	**/
	private int m_iDegreeProgTID=-1;
	public void setDegreeProgTID(int number){
		m_iDegreeProgTID = number;
	}
	public void setDegreeProgTID(String number){
		m_iDegreeProgTID = convertToInt(number);
		return;
	}
	public int getDegreeProgTID(){
		return m_iDegreeProgTID;
	}


	/**
	** City Vision Intern Length of Internship
	**/
	private int m_iInternLengthTID=-1;
	public void setInternLengthTID(int number){
		m_iInternLengthTID = number;
	}
	public void setInternLengthTID(String number){
		m_iInternLengthTID = convertToInt(number);
		return;
	}
	public int getInternLengthTID(){
		return m_iInternLengthTID;
	}


	/**
	** City Vision Intern Position Preference
	**/
	private int m_iPosPrefTID=-1;
	public void setPosPrefTID(int number){
		m_iPosPrefTID = number;
	}
	public void setPosPrefTID(String number){
		m_iPosPrefTID = convertToInt(number);
		return;
	}
	public int getPosPrefTID(){
		return m_iPosPrefTID;
	}

	/**
	** m_aszPosPref for display purposes 
	**/
	private String m_aszPosPref=null;
	public void setPosPref(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszPosPref = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszPosPref = aszTemp;
			return;
		}
		m_aszPosPref = aszTemp.substring(0,iLen);
	}
	public String getPosPref(){
		if(m_aszPosPref == null) return "";
		return m_aszPosPref;
	}


	/**
	** City Vision Intern Skills
	**/
	private int m_iSkillsTID=-1;
	public void setSkillsTID(int number){
		m_iSkillsTID = number;
	}
	public void setSkillsTID(String number){
		m_iSkillsTID = convertToInt(number);
		return;
	}
	public int getSkillsTID(){
		return m_iSkillsTID;
	}


	/**
	** City Vision Intern Source
	**/
	private int m_iSourceTID=-1;
	public void setSourceTID(int number){
		m_iSourceTID = number;
	}
	public void setSourceTID(String number){
		m_iSourceTID = convertToInt(number);
		return;
	}
	public int getSourceTID(){
		return m_iSourceTID;
	}


	/**
	** City Vision Intern Strong Preference on Population
	**/
	private int m_iPopulPrefTID=-1;
	public void setPopulPrefTID(int number){
		m_iPopulPrefTID = number;
	}
	public void setPopulPrefTID(String number){
		m_iPopulPrefTID = convertToInt(number);
		return;
	}
	public int getPopulPrefTID(){
		return m_iPopulPrefTID;
	}


	/**
	** City Vision Intern Type
	**/
	private int m_iInternTypeTID=-1;
	public void setInternTypeTID(int number){
		m_iInternTypeTID = number;
	}
	public void setInternTypeTID(String number){
		m_iInternTypeTID = convertToInt(number);
		return;
	}
	public int getInternTypeTID(){
		return m_iInternTypeTID;
	}

	/**
	** City Vision Intern Type
	**/

	private int[] a_iInternTypeTIDsArray=null;
	public void setInternTypeTIDsArray(int[] values){
		int iLen=255;
		int[] a_iTemp = values;
		a_iInternTypeTIDsArray = new int[a_iTemp.length];
		if(a_iTemp.length < 1){
			a_iInternTypeTIDsArray = null;
			return;
		}
		if(a_iTemp.length < iLen + 1){
			a_iInternTypeTIDsArray = a_iTemp;
			return;
		}
	}
	public int[] getInternTypeTIDsArray(){
		if(a_iInternTypeTIDsArray == null) {
			a_iInternTypeTIDsArray=new int[0];
			return a_iInternTypeTIDsArray;
		}
		return a_iInternTypeTIDsArray;
	}


	/**
	** addr_postalcode type VARCHAR(40) in table userprofileinfo 
	**/
	private String m_aszInternTypes=null;
	public void setInternTypes(String value){
		int iLen=250;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszInternTypes = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszInternTypes = aszTemp;
			return;
		}
		m_aszInternTypes = aszTemp.substring(0,iLen);
	}
	public String getInternTypes(){
		if(m_aszInternTypes == null) return "";
		return m_aszInternTypes;
	}



	/**
	** source type VARCHAR(40) in table userprofileinfo (like sf for salesforce) 
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




	private String[] a_aszInternTypesArray=null;
	public void setInternTypesArray(String[] values){
		int iLen=255;
		String[] a_aszTemp = values;
		a_aszInternTypesArray = new String[a_aszTemp.length];
		if(a_aszTemp.length < 1){
			a_aszInternTypesArray = null;
			return;
		}
		if(a_aszTemp.length < iLen + 1){
			a_aszInternTypesArray = a_aszTemp;
			return;
		}
	}
	public String[] getInternTypesArray(){
		if(a_aszInternTypesArray == null) {
			a_aszInternTypesArray=new String[0];
			return a_aszInternTypesArray;
		}
		return a_aszInternTypesArray;
	}



	/**
	** City Vision Intern Work Environment
	**/
	private int m_iWorkEnvironTID=-1;
	public void setWorkEnvironTID(int number){
		m_iWorkEnvironTID = number;
	}
	public void setWorkEnvironTID(String number){
		m_iWorkEnvironTID = convertToInt(number);
		return;
	}
	public int getWorkEnvironTID(){
		return m_iWorkEnvironTID;
	}
	
	
	
	
	//=== END    Taxonomy ===
	//=== END    Taxonomy ===
	//=== END    Taxonomy ===

	/**
	** salesforce ID
	**/
	private String m_aszSFID=null;
	public void setSFID(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length()<1){
			m_aszSFID = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszSFID = aszTemp;
			return;
		}
		m_aszSFID = aszTemp.substring(0,iLen);
	}
	public String getSFID(){
		if(m_aszSFID == null) return "";
		return m_aszSFID;
	}

	/**
	** m_aszResumeFilePath type TEXT() in table  
	**/
	private String m_aszResumeFilePath=null;

	public void setResumeFilePath(String value){
		if(null == value){
			m_aszResumeFilePath = null;
			return;
		}
		m_aszResumeFilePath = value.trim();
	}
	public String getResumeFilePath(){
		if(m_aszResumeFilePath == null) return "";
		return m_aszResumeFilePath;
	}

	/**
	** m_aszDrupalUserProfile type TEXT() in table  
	**/
	private String m_aszUserProfileLink=null;

	public void setUserProfileLink(String value){
		if(null == value){
			m_aszUserProfileLink = null;
			return;
		}
		m_aszUserProfileLink = value.trim();
	}
	public String getUserProfileLink(){
		if(m_aszUserProfileLink == null) return "";
		return m_aszUserProfileLink;
	}

	/**
	** City Vision Intern m_iScreened
	**/
	private int m_iScreened=-10; // incomplete applications are given -10 as value for screened
	public void setScreened(int number){
		m_iScreened = number;
	}
	public void setScreened(String number){
		m_iScreened = convertToInt(number);
		return;
	}
	public int getScreened(){
		return m_iScreened;
	}
	

	/**
	** City Vision Internships Coordinator Interview Notes (visible to all Site Directors)
	**/
	private String m_aszScreeningIntervNotes=null;
	public void setScreeningIntervNotes(String value){
		if(null == value){
			m_aszScreeningIntervNotes = null;
			return;
		}
		m_aszScreeningIntervNotes = value.trim();
	}
	public String getScreeningIntervNotes(){
		if(m_aszScreeningIntervNotes == null) return "";
		return m_aszScreeningIntervNotes;
	}

	/**
	** username type VARCHAR(255) in table userprofileinfo 
	**/
	private String m_aszUsername=null;
	public void setUsername(String value){
		int iLen=255;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszUsername = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszUsername = aszTemp;
			return;
		}
		m_aszUsername = aszTemp.substring(0,iLen);
	}
	public String getUsername(){
		if(m_aszUsername == null) return "";
		return m_aszUsername;
	}


}
