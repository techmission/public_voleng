package com.abrecorp.opensource.dataobj;

/**
* Code Generated Data Transfer Object DTO Class
* For Table org_details
*/

import java.io.*;


public class OrganizationDetailsInfoDTO extends BaseInfoObj implements Serializable, Cloneable {

	public static final int LOADBY_ORGNUMBER=101;
	public static final int LOADBY_PRIMARYPERSON=102;
	public static final int LOADBY_ORGNID=111;

	// serach types
	public static final int SEARCH_TYPE_TOPMOST=201;
	public static final int SEARCH_TYPE_ADVANCE1=202;


	/**
	* constructor
	*/
	public OrganizationDetailsInfoDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			OrganizationDetailsInfoDTO el = (OrganizationDetailsInfoDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}


	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===>
	//=== START Manually Added Fields ===<



	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===
	//=== END   Manually Added Fields ===

	//**** Start Code Generated Methods Do Not Modify *********************
	//===> Start Code Generated Methods For Table org_details 
	//===> Start Code Generated Methods For Table org_details 
	//===> Start Code Generated Methods For Table org_details 


	/**
	** org_number type LONG() in table org_details 
	**/
	private int m_iDETOrgNumber=0;
	public void setDETOrgNumber(int number){
		m_iDETOrgNumber = number;
	}
	public void setDETOrgNumber(String number){
		m_iDETOrgNumber = convertToInt(number);
		return;
	}
	public int getDETOrgNumber(){
		return m_iDETOrgNumber;
	}


	/**
	** org_nid type LONG() in table org_details 
	**/
	private int m_iDETOrgNid=0;
	public void setDETOrgNID(int number){
		m_iDETOrgNid = number;
	}
	public void setDETOrgNID(String number){
		m_iDETOrgNid = convertToInt(number);
		return;
	}
	public int getDETOrgNID(){
		return m_iDETOrgNid;
	}


	/**
	** org_UID type LONG() in table org_details 
	**/
	private int m_iDETUID=0;
	public void setDETUID(int number){
		m_iDETUID = number;
	}
	public void setDETUID(String number){
		m_iDETUID = convertToInt(number);
		return;
	}
	public int getDETUID(){
		return m_iDETUID;
	}


	/**
	** orgcodekey type CHAR(20) in table org_details 
	**/
	private String m_aszDETOrgcodekey=null;
	public void setDETOrgcodekey(String value){
		int iLen=20;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDETOrgcodekey = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDETOrgcodekey = aszTemp;
			return;
		}
		m_aszDETOrgcodekey = aszTemp.substring(0,iLen);
	}
	public String getDETOrgcodekey(){
		if(m_aszDETOrgcodekey == null) return "";
		return m_aszDETOrgcodekey;
	}


	/**
	** primary_person type LONG() in table org_details 
	**/
	private int m_iDETPrimaryPerson=0;
	public void setDETPrimaryPerson(int number){
		m_iDETPrimaryPerson = number;
	}
	public void setDETPrimaryPerson(String number){
		m_iDETPrimaryPerson = convertToInt(number);
		return;
	}
	public int getDETPrimaryPerson(){
		return m_iDETPrimaryPerson;
	}


	/**
	** update_dt type DATETIME() in table org_details 
	**/
	private java.util.Date m_azdDETUpdateDt=null;
	public void setDETUpdateDt(java.util.Date value){
		if(value == null){
			m_azdDETUpdateDt = null;
			return;
		}
		m_azdDETUpdateDt = value;
	}
	public java.util.Date getDETUpdateDt(){
		if(m_azdDETUpdateDt == null) return null;
		return m_azdDETUpdateDt;
	}


	/**
	** update_id type LONG() in table org_details 
	**/
	private int m_iDETUpdateId=0;
	public void setDETUpdateId(int number){
		m_iDETUpdateId = number;
	}
	public void setDETUpdateId(String number){
		m_iDETUpdateId = convertToInt(number);
		return;
	}
	public int getDETUpdateId(){
		return m_iDETUpdateId;
	}


	/**
	** fed_tax_id type VARCHAR(20) in table org_details 
	**/
	private String m_aszDETFedTaxId1=null;
	private String m_aszDETFedTaxId2=null;
	public void setDETFedTaxId(String piece1, String piece2){
		m_aszDETFedTaxId1 = piece1;
		m_aszDETFedTaxId2 = piece2;
	}
	public String getDETFedTaxId(){
		if(m_aszDETFedTaxId2 == null || m_aszDETFedTaxId2.length() == 0) return m_aszDETFedTaxId1;
		return m_aszDETFedTaxId1 + "-" + m_aszDETFedTaxId2;
	}
	public String getDETFedTaxId1(){
		return m_aszDETFedTaxId1;
	}
	public String getDETFedTaxId2(){
		return m_aszDETFedTaxId2;
	}


	/**
	** num_vol_org type LONG() in table org_details 
	**/
	private int m_iDETNumVolOrg=0;
	public void setDETNumVolOrg(int number){
		m_iDETNumVolOrg = number;
	}
	public void setDETNumVolOrg(String number){
		m_iDETNumVolOrg = convertToInt(number);
		return;
	}
	public int getDETNumVolOrg(){
		return m_iDETNumVolOrg;
	}


	/**
	** num_served type LONG() in table org_details 
	**/
	private int m_iDETNumServed=0;
	public void setDETNumServed(int number){
		m_iDETNumServed = number;
	}
	public void setDETNumServed(String number){
		m_iDETNumServed = convertToInt(number);
		return;
	}
	public int getDETNumServed(){
		return m_iDETNumServed;
	}


	/**
	** is_501c3_p type CHAR(1) in table org_details 
	**/
	private String m_aszDETIs501c3P=null;
	public void setDETIs501c3P(String value){
		int iLen=1;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDETIs501c3P = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDETIs501c3P = aszTemp;
			return;
		}
		m_aszDETIs501c3P = aszTemp.substring(0,iLen);
	}
	public String getDETIs501c3P(){
		if(m_aszDETIs501c3P == null) return "";
		return m_aszDETIs501c3P;
	}


	/**
	** organization_budget type LONG() in table org_details 
	**/
	private int m_iDETOrganizationBudget=0;
	public void setDETOrganizationBudget(int number){
		m_iDETOrganizationBudget = number;
	}
	public void setDETOrganizationBudget(String number){
		m_iDETOrganizationBudget = convertToInt(number);
		return;
	}
	public int getDETOrganizationBudget(){
		return m_iDETOrganizationBudget;
	}


	/**
	** num_staff_org type INT() in table org_details 
	**/
	private int m_iDETNumStaffOrg=0;
	public void setDETNumStaffOrg(int number){
		m_iDETNumStaffOrg = number;
	}
	public void setDETNumStaffOrg(String number){
		m_iDETNumStaffOrg = convertToInt(number);
		return;
	}
	public int getDETNumStaffOrg(){
		return m_iDETNumStaffOrg;
	}


	/**
	** language_arabic type TINY() in table org_details 
	**/
	private int m_iDETLanguageArabic=0;
	public void setDETLanguageArabic(int number){
		m_iDETLanguageArabic = number;
	}
	public void setDETLanguageArabic(String number){
		m_iDETLanguageArabic = convertToInt(number);
		return;
	}
	public int getDETLanguageArabic(){
		return m_iDETLanguageArabic;
	}


	/**
	** language_chinese type TINY() in table org_details 
	**/
	private int m_iDETLanguageChinese=0;
	public void setDETLanguageChinese(int number){
		m_iDETLanguageChinese = number;
	}
	public void setDETLanguageChinese(String number){
		m_iDETLanguageChinese = convertToInt(number);
		return;
	}
	public int getDETLanguageChinese(){
		return m_iDETLanguageChinese;
	}


	/**
	** language_english type TINY() in table org_details 
	**/
	private int m_iDETLanguageEnglish=0;
	public void setDETLanguageEnglish(int number){
		m_iDETLanguageEnglish = number;
	}
	public void setDETLanguageEnglish(String number){
		m_iDETLanguageEnglish = convertToInt(number);
		return;
	}
	public int getDETLanguageEnglish(){
		return m_iDETLanguageEnglish;
	}


	/**
	** language_french type TINY() in table org_details 
	**/
	private int m_iDETLanguageFrench=0;
	public void setDETLanguageFrench(int number){
		m_iDETLanguageFrench = number;
	}
	public void setDETLanguageFrench(String number){
		m_iDETLanguageFrench = convertToInt(number);
		return;
	}
	public int getDETLanguageFrench(){
		return m_iDETLanguageFrench;
	}


	/**
	** language_hindi type TINY() in table org_details 
	**/
	private int m_iDETLanguageHindi=0;
	public void setDETLanguageHindi(int number){
		m_iDETLanguageHindi = number;
	}
	public void setDETLanguageHindi(String number){
		m_iDETLanguageHindi = convertToInt(number);
		return;
	}
	public int getDETLanguageHindi(){
		return m_iDETLanguageHindi;
	}


	/**
	** language_portuguese type TINY() in table org_details 
	**/
	private int m_iDETLanguagePortuguese=0;
	public void setDETLanguagePortuguese(int number){
		m_iDETLanguagePortuguese = number;
	}
	public void setDETLanguagePortuguese(String number){
		m_iDETLanguagePortuguese = convertToInt(number);
		return;
	}
	public int getDETLanguagePortuguese(){
		return m_iDETLanguagePortuguese;
	}


	/**
	** language_spanish type TINY() in table org_details 
	**/
	private int m_iDETLanguageSpanish=0;
	public void setDETLanguageSpanish(int number){
		m_iDETLanguageSpanish = number;
	}
	public void setDETLanguageSpanish(String number){
		m_iDETLanguageSpanish = convertToInt(number);
		return;
	}
	public int getDETLanguageSpanish(){
		return m_iDETLanguageSpanish;
	}


	/**
	** language_other_a_text type VARCHAR(100) in table org_details 
	**/
	private String m_aszDETLanguageOtherAText=null;
	public void setDETLanguageOtherAText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDETLanguageOtherAText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDETLanguageOtherAText = aszTemp;
			return;
		}
		m_aszDETLanguageOtherAText = aszTemp.substring(0,iLen);
	}
	public String getDETLanguageOtherAText(){
		if(m_aszDETLanguageOtherAText == null) return "";
		return m_aszDETLanguageOtherAText;
	}


	/**
	** language_other_a type TINY() in table org_details 
	**/
	private int m_iDETLanguageOtherA=0;
	public void setDETLanguageOtherA(int number){
		m_iDETLanguageOtherA = number;
	}
	public void setDETLanguageOtherA(String number){
		m_iDETLanguageOtherA = convertToInt(number);
		return;
	}
	public int getDETLanguageOtherA(){
		return m_iDETLanguageOtherA;
	}


	/**
	** language_other_b_text type VARCHAR(100) in table org_details 
	**/
	private String m_aszDETLanguageOtherBText=null;
	public void setDETLanguageOtherBText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDETLanguageOtherBText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDETLanguageOtherBText = aszTemp;
			return;
		}
		m_aszDETLanguageOtherBText = aszTemp.substring(0,iLen);
	}
	public String getDETLanguageOtherBText(){
		if(m_aszDETLanguageOtherBText == null) return "";
		return m_aszDETLanguageOtherBText;
	}


	/**
	** language_other_b type TINY() in table org_details 
	**/
	private int m_iDETLanguageOtherB=0;
	public void setDETLanguageOtherB(int number){
		m_iDETLanguageOtherB = number;
	}
	public void setDETLanguageOtherB(String number){
		m_iDETLanguageOtherB = convertToInt(number);
		return;
	}
	public int getDETLanguageOtherB(){
		return m_iDETLanguageOtherB;
	}


	/**
	** race_asian type TINY() in table org_details 
	**/
	private int m_iDETRaceAsian=0;
	public void setDETRaceAsian(int number){
		m_iDETRaceAsian = number;
	}
	public void setDETRaceAsian(String number){
		m_iDETRaceAsian = convertToInt(number);
		return;
	}
	public int getDETRaceAsian(){
		return m_iDETRaceAsian;
	}


	/**
	** race_black type TINY() in table org_details 
	**/
	private int m_iDETRaceBlack=0;
	public void setDETRaceBlack(int number){
		m_iDETRaceBlack = number;
	}
	public void setDETRaceBlack(String number){
		m_iDETRaceBlack = convertToInt(number);
		return;
	}
	public int getDETRaceBlack(){
		return m_iDETRaceBlack;
	}


	/**
	** race_caucasian type TINY() in table org_details 
	**/
	private int m_iDETRaceCaucasian=0;
	public void setDETRaceCaucasian(int number){
		m_iDETRaceCaucasian = number;
	}
	public void setDETRaceCaucasian(String number){
		m_iDETRaceCaucasian = convertToInt(number);
		return;
	}
	public int getDETRaceCaucasian(){
		return m_iDETRaceCaucasian;
	}


	/**
	** race_latino type TINY() in table org_details 
	**/
	private int m_iDETRaceLatino=0;
	public void setDETRaceLatino(int number){
		m_iDETRaceLatino = number;
	}
	public void setDETRaceLatino(String number){
		m_iDETRaceLatino = convertToInt(number);
		return;
	}
	public int getDETRaceLatino(){
		return m_iDETRaceLatino;
	}


	/**
	** race_pacific_islander type TINY() in table org_details 
	**/
	private int m_iDETRacePacificIslander=0;
	public void setDETRacePacificIslander(int number){
		m_iDETRacePacificIslander = number;
	}
	public void setDETRacePacificIslander(String number){
		m_iDETRacePacificIslander = convertToInt(number);
		return;
	}
	public int getDETRacePacificIslander(){
		return m_iDETRacePacificIslander;
	}


	/**
	** race_native_american type TINY() in table org_details 
	**/
	private int m_iDETRaceNativeAmerican=0;
	public void setDETRaceNativeAmerican(int number){
		m_iDETRaceNativeAmerican = number;
	}
	public void setDETRaceNativeAmerican(String number){
		m_iDETRaceNativeAmerican = convertToInt(number);
		return;
	}
	public int getDETRaceNativeAmerican(){
		return m_iDETRaceNativeAmerican;
	}


	/**
	** race_other_text type VARCHAR(100) in table org_details 
	**/
	private String m_aszDETRaceOtherText=null;
	public void setDETRaceOtherText(String value){
		int iLen=100;
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszDETRaceOtherText = null;
			return;
		}
		if(aszTemp.length() < iLen + 1){
			m_aszDETRaceOtherText = aszTemp;
			return;
		}
		m_aszDETRaceOtherText = aszTemp.substring(0,iLen);
	}
	public String getDETRaceOtherText(){
		if(m_aszDETRaceOtherText == null) return "";
		return m_aszDETRaceOtherText;
	}


	/**
	** race_other type TINY() in table org_details 
	**/
	private int m_iDETRaceOther=0;
	public void setDETRaceOther(int number){
		m_iDETRaceOther = number;
	}
	public void setDETRaceOther(String number){
		m_iDETRaceOther = convertToInt(number);
		return;
	}
	public int getDETRaceOther(){
		return m_iDETRaceOther;
	}


	/**
	** demo_low_income type TINY() in table org_details 
	**/
	private int m_iDETDemoLowIncome=0;
	public void setDETDemoLowIncome(int number){
		m_iDETDemoLowIncome = number;
	}
	public void setDETDemoLowIncome(String number){
		m_iDETDemoLowIncome = convertToInt(number);
		return;
	}
	public int getDETDemoLowIncome(){
		return m_iDETDemoLowIncome;
	}


	/**
	** demo_homeless type TINY() in table org_details 
	**/
	private int m_iDETDemoHomeless=0;
	public void setDETDemoHomeless(int number){
		m_iDETDemoHomeless = number;
	}
	public void setDETDemoHomeless(String number){
		m_iDETDemoHomeless = convertToInt(number);
		return;
	}
	public int getDETDemoHomeless(){
		return m_iDETDemoHomeless;
	}


	/**
	** demo_disability type TINY() in table org_details 
	**/
	private int m_iDETDemoDisability=0;
	public void setDETDemoDisability(int number){
		m_iDETDemoDisability = number;
	}
	public void setDETDemoDisability(String number){
		m_iDETDemoDisability = convertToInt(number);
		return;
	}
	public int getDETDemoDisability(){
		return m_iDETDemoDisability;
	}


	/**
	** demo_urban type TINY() in table org_details 
	**/
	private int m_iDETDemoUrban=0;
	public void setDETDemoUrban(int number){
		m_iDETDemoUrban = number;
	}
	public void setDETDemoUrban(String number){
		m_iDETDemoUrban = convertToInt(number);
		return;
	}
	public int getDETDemoUrban(){
		return m_iDETDemoUrban;
	}


	/**
	** demo_rural type TINY() in table org_details 
	**/
	private int m_iDETDemoRural=0;
	public void setDETDemoRural(int number){
		m_iDETDemoRural = number;
	}
	public void setDETDemoRural(String number){
		m_iDETDemoRural = convertToInt(number);
		return;
	}
	public int getDETDemoRural(){
		return m_iDETDemoRural;
	}


	/**
	** demo_youth type TINY() in table org_details 
	**/
	private int m_iDETDemoYouth=0;
	public void setDETDemoYouth(int number){
		m_iDETDemoYouth = number;
	}
	public void setDETDemoYouth(String number){
		m_iDETDemoYouth = convertToInt(number);
		return;
	}
	public int getDETDemoYouth(){
		return m_iDETDemoYouth;
	}


	/**
	** demo_adults type TINY() in table org_details 
	**/
	private int m_iDETDemoAdults=0;
	public void setDETDemoAdults(int number){
		m_iDETDemoAdults = number;
	}
	public void setDETDemoAdults(String number){
		m_iDETDemoAdults = convertToInt(number);
		return;
	}
	public int getDETDemoAdults(){
		return m_iDETDemoAdults;
	}


	/**
	** demo_seniors type TINY() in table org_details 
	**/
	private int m_iDETDemoSeniors=0;
	public void setDETDemoSeniors(int number){
		m_iDETDemoSeniors = number;
	}
	public void setDETDemoSeniors(String number){
		m_iDETDemoSeniors = convertToInt(number);
		return;
	}
	public int getDETDemoSeniors(){
		return m_iDETDemoSeniors;
	}


	/**
	** gender_male type TINY() in table org_details 
	**/
	private int m_iDETGenderMale=0;
	public void setDETGenderMale(int number){
		m_iDETGenderMale = number;
	}
	public void setDETGenderMale(String number){
		m_iDETGenderMale = convertToInt(number);
		return;
	}
	public int getDETGenderMale(){
		return m_iDETGenderMale;
	}


	/**
	** gender_female type TINY() in table org_details 
	**/
	private int m_iDETGenderFemale=0;
	public void setDETGenderFemale(int number){
		m_iDETGenderFemale = number;
	}
	public void setDETGenderFemale(String number){
		m_iDETGenderFemale = convertToInt(number);
		return;
	}
	public int getDETGenderFemale(){
		return m_iDETGenderFemale;
	}


	/**
	** progtype_comp_center type TINY() in table org_details 
	**/
	private int m_iDETProgtypeCompCenter=0;
	public void setDETProgtypeCompCenter(int number){
		m_iDETProgtypeCompCenter = number;
	}
	public void setDETProgtypeCompCenter(String number){
		m_iDETProgtypeCompCenter = convertToInt(number);
		return;
	}
	public int getDETProgtypeCompCenter(){
		return m_iDETProgtypeCompCenter;
	}


	/**
	** progtype_ed_employ type TINY() in table org_details 
	**/
	private int m_iDETProgtypeEdEmploy=0;
	public void setDETProgtypeEdEmploy(int number){
		m_iDETProgtypeEdEmploy = number;
	}
	public void setDETProgtypeEdEmploy(String number){
		m_iDETProgtypeEdEmploy = convertToInt(number);
		return;
	}
	public int getDETProgtypeEdEmploy(){
		return m_iDETProgtypeEdEmploy;
	}


	/**
	** progtype_food type TINY() in table org_details 
	**/
	private int m_iDETProgtypeFood=0;
	public void setDETProgtypeFood(int number){
		m_iDETProgtypeFood = number;
	}
	public void setDETProgtypeFood(String number){
		m_iDETProgtypeFood = convertToInt(number);
		return;
	}
	public int getDETProgtypeFood(){
		return m_iDETProgtypeFood;
	}


	/**
	** progtype_health type TINY() in table org_details 
	**/
	private int m_iDETProgtypeHealth=0;
	public void setDETProgtypeHealth(int number){
		m_iDETProgtypeHealth = number;
	}
	public void setDETProgtypeHealth(String number){
		m_iDETProgtypeHealth = convertToInt(number);
		return;
	}
	public int getDETProgtypeHealth(){
		return m_iDETProgtypeHealth;
	}


	/**
	** progtype_homeless type TINY() in table org_details 
	**/
	private int m_iDETProgtypeHomeless=0;
	public void setDETProgtypeHomeless(int number){
		m_iDETProgtypeHomeless = number;
	}
	public void setDETProgtypeHomeless(String number){
		m_iDETProgtypeHomeless = convertToInt(number);
		return;
	}
	public int getDETProgtypeHomeless(){
		return m_iDETProgtypeHomeless;
	}


	/**
	** progtype_housing type TINY() in table org_details 
	**/
	private int m_iDETProgtypeHousing=0;
	public void setDETProgtypeHousing(int number){
		m_iDETProgtypeHousing = number;
	}
	public void setDETProgtypeHousing(String number){
		m_iDETProgtypeHousing = convertToInt(number);
		return;
	}
	public int getDETProgtypeHousing(){
		return m_iDETProgtypeHousing;
	}


	/**
	** progtype_immigration type TINY() in table org_details 
	**/
	private int m_iDETProgtypeImmigration=0;
	public void setDETProgtypeImmigration(int number){
		m_iDETProgtypeImmigration = number;
	}
	public void setDETProgtypeImmigration(String number){
		m_iDETProgtypeImmigration = convertToInt(number);
		return;
	}
	public int getDETProgtypeImmigration(){
		return m_iDETProgtypeImmigration;
	}


	/**
	** progtype_other type TINY() in table org_details 
	**/
	private int m_iDETProgtypeOther=0;
	public void setDETProgtypeOther(int number){
		m_iDETProgtypeOther = number;
	}
	public void setDETProgtypeOther(String number){
		m_iDETProgtypeOther = convertToInt(number);
		return;
	}
	public int getDETProgtypeOther(){
		return m_iDETProgtypeOther;
	}


	/**
	** progtype_rehab type TINY() in table org_details 
	**/
	private int m_iDETProgtypeRehab=0;
	public void setDETProgtypeRehab(int number){
		m_iDETProgtypeRehab = number;
	}
	public void setDETProgtypeRehab(String number){
		m_iDETProgtypeRehab = convertToInt(number);
		return;
	}
	public int getDETProgtypeRehab(){
		return m_iDETProgtypeRehab;
	}


	/**
	** progtype_seniors type TINY() in table org_details 
	**/
	private int m_iDETProgtypeSeniors=0;
	public void setDETProgtypeSeniors(int number){
		m_iDETProgtypeSeniors = number;
	}
	public void setDETProgtypeSeniors(String number){
		m_iDETProgtypeSeniors = convertToInt(number);
		return;
	}
	public int getDETProgtypeSeniors(){
		return m_iDETProgtypeSeniors;
	}


	/**
	** progtype_spirit_devel type TINY() in table org_details 
	**/
	private int m_iDETProgtypeSpiritDevel=0;
	public void setDETProgtypeSpiritDevel(int number){
		m_iDETProgtypeSpiritDevel = number;
	}
	public void setDETProgtypeSpiritDevel(String number){
		m_iDETProgtypeSpiritDevel = convertToInt(number);
		return;
	}
	public int getDETProgtypeSpiritDevel(){
		return m_iDETProgtypeSpiritDevel;
	}


	/**
	** progtype_youth type TINY() in table org_details 
	**/
	private int m_iDETProgtypeYouth=0;
	public void setDETProgtypeYouth(int number){
		m_iDETProgtypeYouth = number;
	}
	public void setDETProgtypeYouth(String number){
		m_iDETProgtypeYouth = convertToInt(number);
		return;
	}
	public int getDETProgtypeYouth(){
		return m_iDETProgtypeYouth;
	}


	/**
	** affil_agrm type TINY() in table org_details 
	**/
	private int m_iDETAffilAgrm=0;
	public void setDETAffilAgrm(int number){
		m_iDETAffilAgrm = number;
	}
	public void setDETAffilAgrm(String number){
		m_iDETAffilAgrm = convertToInt(number);
		return;
	}
	public int getDETAffilAgrm(){
		return m_iDETAffilAgrm;
	}


	/**
	** affil_bilgramevas type TINY() in table org_details 
	**/
	private int m_iDETAffilBilgramevas=0;
	public void setDETAffilBilgramevas(int number){
		m_iDETAffilBilgramevas = number;
	}
	public void setDETAffilBilgramevas(String number){
		m_iDETAffilBilgramevas = convertToInt(number);
		return;
	}
	public int getDETAffilBilgramevas(){
		return m_iDETAffilBilgramevas;
	}


	/**
	** affil_breadworld type TINY() in table org_details 
	**/
	private int m_iDETAffilBreadworld=0;
	public void setDETAffilBreadworld(int number){
		m_iDETAffilBreadworld = number;
	}
	public void setDETAffilBreadworld(String number){
		m_iDETAffilBreadworld = convertToInt(number);
		return;
	}
	public int getDETAffilBreadworld(){
		return m_iDETAffilBreadworld;
	}


	/**
	** affil_callren type TINY() in table org_details 
	**/
	private int m_iDETAffilCallren=0;
	public void setDETAffilCallren(int number){
		m_iDETAffilCallren = number;
	}
	public void setDETAffilCallren(String number){
		m_iDETAffilCallren = convertToInt(number);
		return;
	}
	public int getDETAffilCallren(){
		return m_iDETAffilCallren;
	}


	/**
	** affil_campcrus type TINY() in table org_details 
	**/
	private int m_iDETAffilCampcrus=0;
	public void setDETAffilCampcrus(int number){
		m_iDETAffilCampcrus = number;
	}
	public void setDETAffilCampcrus(String number){
		m_iDETAffilCampcrus = convertToInt(number);
		return;
	}
	public int getDETAffilCampcrus(){
		return m_iDETAffilCampcrus;
	}


	/**
	** affil_cathcharities type TINY() in table org_details 
	**/
	private int m_iDETAffilCathcharities=0;
	public void setDETAffilCathcharities(int number){
		m_iDETAffilCathcharities = number;
	}
	public void setDETAffilCathcharities(String number){
		m_iDETAffilCathcharities = convertToInt(number);
		return;
	}
	public int getDETAffilCathcharities(){
		return m_iDETAffilCathcharities;
	}


	/**
	** affil_cathcharities_US type TINY() in table org_details 
	**/
	private int m_iDETAffilCathcharitiesUs=0;
	public void setDETAffilCathcharitiesUs(int number){
		m_iDETAffilCathcharitiesUs = number;
	}
	public void setDETAffilCathcharitiesUs(String number){
		m_iDETAffilCathcharitiesUs = convertToInt(number);
		return;
	}
	public int getDETAffilCathcharitiesUs(){
		return m_iDETAffilCathcharitiesUs;
	}


	/**
	** affil_cathmedmiss type TINY() in table org_details 
	**/
	private int m_iDETAffilCathmedmiss=0;
	public void setDETAffilCathmedmiss(int number){
		m_iDETAffilCathmedmiss = number;
	}
	public void setDETAffilCathmedmiss(String number){
		m_iDETAffilCathmedmiss = convertToInt(number);
		return;
	}
	public int getDETAffilCathmedmiss(){
		return m_iDETAffilCathmedmiss;
	}


	/**
	** affil_cathrelserv type TINY() in table org_details 
	**/
	private int m_iDETAffilCathrelserv=0;
	public void setDETAffilCathrelserv(int number){
		m_iDETAffilCathrelserv = number;
	}
	public void setDETAffilCathrelserv(String number){
		m_iDETAffilCathrelserv = convertToInt(number);
		return;
	}
	public int getDETAffilCathrelserv(){
		return m_iDETAffilCathrelserv;
	}


	/**
	** affil_chrisaid type TINY() in table org_details 
	**/
	private int m_iDETAffilChrisaid=0;
	public void setDETAffilChrisaid(int number){
		m_iDETAffilChrisaid = number;
	}
	public void setDETAffilChrisaid(String number){
		m_iDETAffilChrisaid = convertToInt(number);
		return;
	}
	public int getDETAffilChrisaid(){
		return m_iDETAffilChrisaid;
	}


	/**
	** affil_chrischildfnd type TINY() in table org_details 
	**/
	private int m_iDETAffilChrischildfnd=0;
	public void setDETAffilChrischildfnd(int number){
		m_iDETAffilChrischildfnd = number;
	}
	public void setDETAffilChrischildfnd(String number){
		m_iDETAffilChrischildfnd = convertToInt(number);
		return;
	}
	public int getDETAffilChrischildfnd(){
		return m_iDETAffilChrischildfnd;
	}


	/**
	** affil_ccda type TINY() in table org_details 
	**/
	private int m_iDETAffilCcda=0;
	public void setDETAffilCcda(int number){
		m_iDETAffilCcda = number;
	}
	public void setDETAffilCcda(String number){
		m_iDETAffilCcda = convertToInt(number);
		return;
	}
	public int getDETAffilCcda(){
		return m_iDETAffilCcda;
	}


	/**
	** affil_chrislegsoc type TINY() in table org_details 
	**/
	private int m_iDETAffilChrislegsoc=0;
	public void setDETAffilChrislegsoc(int number){
		m_iDETAffilChrislegsoc = number;
	}
	public void setDETAffilChrislegsoc(String number){
		m_iDETAffilChrislegsoc = convertToInt(number);
		return;
	}
	public int getDETAffilChrislegsoc(){
		return m_iDETAffilChrislegsoc;
	}


	/**
	** affil_chrisrefna type TINY() in table org_details 
	**/
	private int m_iDETAffilChrisrefna=0;
	public void setDETAffilChrisrefna(int number){
		m_iDETAffilChrisrefna = number;
	}
	public void setDETAffilChrisrefna(String number){
		m_iDETAffilChrisrefna = convertToInt(number);
		return;
	}
	public int getDETAffilChrisrefna(){
		return m_iDETAffilChrisrefna;
	}


	/**
	** affil_ctcnet type TINY() in table org_details 
	**/
	private int m_iDETAffilCtcnet=0;
	public void setDETAffilCtcnet(int number){
		m_iDETAffilCtcnet = number;
	}
	public void setDETAffilCtcnet(String number){
		m_iDETAffilCtcnet = convertToInt(number);
		return;
	}
	public int getDETAffilCtcnet(){
		return m_iDETAffilCtcnet;
	}


	/**
	** affil_compassionint type TINY() in table org_details 
	**/
	private int m_iDETAffilCompassionint=0;
	public void setDETAffilCompassionint(int number){
		m_iDETAffilCompassionint = number;
	}
	public void setDETAffilCompassionint(String number){
		m_iDETAffilCompassionint = convertToInt(number);
		return;
	}
	public int getDETAffilCompassionint(){
		return m_iDETAffilCompassionint;
	}


	/**
	** affil_compassionworks type TINY() in table org_details 
	**/
	private int m_iDETAffilCompassionworks=0;
	public void setDETAffilCompassionworks(int number){
		m_iDETAffilCompassionworks = number;
	}
	public void setDETAffilCompassionworks(String number){
		m_iDETAffilCompassionworks = convertToInt(number);
		return;
	}
	public int getDETAffilCompassionworks(){
		return m_iDETAffilCompassionworks;
	}


	/**
	** affil_coopbapfel type TINY() in table org_details 
	**/
	private int m_iDETAffilCoopbapfel=0;
	public void setDETAffilCoopbapfel(int number){
		m_iDETAffilCoopbapfel = number;
	}
	public void setDETAffilCoopbapfel(String number){
		m_iDETAffilCoopbapfel = convertToInt(number);
		return;
	}
	public int getDETAffilCoopbapfel(){
		return m_iDETAffilCoopbapfel;
	}


	/**
	** affil_devos type TINY() in table org_details 
	**/
	private int m_iDETAffilDevos=0;
	public void setDETAffilDevos(int number){
		m_iDETAffilDevos = number;
	}
	public void setDETAffilDevos(String number){
		m_iDETAffilDevos = convertToInt(number);
		return;
	}
	public int getDETAffilDevos(){
		return m_iDETAffilDevos;
	}


	/**
	** affil_evassocpromed type TINY() in table org_details 
	**/
	private int m_iDETAffilEvassocpromed=0;
	public void setDETAffilEvassocpromed(int number){
		m_iDETAffilEvassocpromed = number;
	}
	public void setDETAffilEvassocpromed(String number){
		m_iDETAffilEvassocpromed = convertToInt(number);
		return;
	}
	public int getDETAffilEvassocpromed(){
		return m_iDETAffilEvassocpromed;
	}


	/**
	** affil_evcovchurch type TINY() in table org_details 
	**/
	private int m_iDETAffilEvcovchurch=0;
	public void setDETAffilEvcovchurch(int number){
		m_iDETAffilEvcovchurch = number;
	}
	public void setDETAffilEvcovchurch(String number){
		m_iDETAffilEvcovchurch = convertToInt(number);
		return;
	}
	public int getDETAffilEvcovchurch(){
		return m_iDETAffilEvcovchurch;
	}


	/**
	** affil_evluthchamer type TINY() in table org_details 
	**/
	private int m_iDETAffilEvluthchamer=0;
	public void setDETAffilEvluthchamer(int number){
		m_iDETAffilEvluthchamer = number;
	}
	public void setDETAffilEvluthchamer(String number){
		m_iDETAffilEvluthchamer = convertToInt(number);
		return;
	}
	public int getDETAffilEvluthchamer(){
		return m_iDETAffilEvluthchamer;
	}


	/**
	** affil_evsocialaction type TINY() in table org_details 
	**/
	private int m_iDETAffilEvsocialaction=0;
	public void setDETAffilEvsocialaction(int number){
		m_iDETAffilEvsocialaction = number;
	}
	public void setDETAffilEvsocialaction(String number){
		m_iDETAffilEvsocialaction = convertToInt(number);
		return;
	}
	public int getDETAffilEvsocialaction(){
		return m_iDETAffilEvsocialaction;
	}


	/**
	** affil_feedchild type TINY() in table org_details 
	**/
	private int m_iDETAffilFeedchild=0;
	public void setDETAffilFeedchild(int number){
		m_iDETAffilFeedchild = number;
	}
	public void setDETAffilFeedchild(String number){
		m_iDETAffilFeedchild = convertToInt(number);
		return;
	}
	public int getDETAffilFeedchild(){
		return m_iDETAffilFeedchild;
	}


	/**
	** affil_focusonfam type TINY() in table org_details 
	**/
	private int m_iDETAffilFocusonfam=0;
	public void setDETAffilFocusonfam(int number){
		m_iDETAffilFocusonfam = number;
	}
	public void setDETAffilFocusonfam(String number){
		m_iDETAffilFocusonfam = convertToInt(number);
		return;
	}
	public int getDETAffilFocusonfam(){
		return m_iDETAffilFocusonfam;
	}


	/**
	** affil_foodforpoor type TINY() in table org_details 
	**/
	private int m_iDETAffilFoodforpoor=0;
	public void setDETAffilFoodforpoor(int number){
		m_iDETAffilFoodforpoor = number;
	}
	public void setDETAffilFoodforpoor(String number){
		m_iDETAffilFoodforpoor = convertToInt(number);
		return;
	}
	public int getDETAffilFoodforpoor(){
		return m_iDETAffilFoodforpoor;
	}


	/**
	** affil_habitathum type TINY() in table org_details 
	**/
	private int m_iDETAffilHabitathum=0;
	public void setDETAffilHabitathum(int number){
		m_iDETAffilHabitathum = number;
	}
	public void setDETAffilHabitathum(String number){
		m_iDETAffilHabitathum = convertToInt(number);
		return;
	}
	public int getDETAffilHabitathum(){
		return m_iDETAffilHabitathum;
	}


	/**
	** affil_handsonnet type TINY() in table org_details 
	**/
	private int m_iDETAffilHandsonnet=0;
	public void setDETAffilHandsonnet(int number){
		m_iDETAffilHandsonnet = number;
	}
	public void setDETAffilHandsonnet(String number){
		m_iDETAffilHandsonnet = convertToInt(number);
		return;
	}
	public int getDETAffilHandsonnet(){
		return m_iDETAffilHandsonnet;
	}


	/**
	** affil_hereslifeinncity type TINY() in table org_details 
	**/
	private int m_iDETAffilHereslifeinncity=0;
	public void setDETAffilHereslifeinncity(int number){
		m_iDETAffilHereslifeinncity = number;
	}
	public void setDETAffilHereslifeinncity(String number){
		m_iDETAffilHereslifeinncity = convertToInt(number);
		return;
	}
	public int getDETAffilHereslifeinncity(){
		return m_iDETAffilHereslifeinncity;
	}


	/**
	** affil_hud type TINY() in table org_details 
	**/
	private int m_iDETAffilHud=0;
	public void setDETAffilHud(int number){
		m_iDETAffilHud = number;
	}
	public void setDETAffilHud(String number){
		m_iDETAffilHud = convertToInt(number);
		return;
	}
	public int getDETAffilHud(){
		return m_iDETAffilHud;
	}


	/**
	** affil_leaderfoundamer type TINY() in table org_details 
	**/
	private int m_iDETAffilLeaderfoundamer=0;
	public void setDETAffilLeaderfoundamer(int number){
		m_iDETAffilLeaderfoundamer = number;
	}
	public void setDETAffilLeaderfoundamer(String number){
		m_iDETAffilLeaderfoundamer = convertToInt(number);
		return;
	}
	public int getDETAffilLeaderfoundamer(){
		return m_iDETAffilLeaderfoundamer;
	}


	/**
	** affil_mapint type TINY() in table org_details 
	**/
	private int m_iDETAffilMapint=0;
	public void setDETAffilMapint(int number){
		m_iDETAffilMapint = number;
	}
	public void setDETAffilMapint(String number){
		m_iDETAffilMapint = convertToInt(number);
		return;
	}
	public int getDETAffilMapint(){
		return m_iDETAffilMapint;
	}


	/**
	** affil_mennoncentral type TINY() in table org_details 
	**/
	private int m_iDETAffilMennoncentral=0;
	public void setDETAffilMennoncentral(int number){
		m_iDETAffilMennoncentral = number;
	}
	public void setDETAffilMennoncentral(String number){
		m_iDETAffilMennoncentral = convertToInt(number);
		return;
	}
	public int getDETAffilMennoncentral(){
		return m_iDETAffilMennoncentral;
	}


	/**
	** affil_reformedamer type TINY() in table org_details 
	**/
	private int m_iDETAffilReformedamer=0;
	public void setDETAffilReformedamer(int number){
		m_iDETAffilReformedamer = number;
	}
	public void setDETAffilReformedamer(String number){
		m_iDETAffilReformedamer = convertToInt(number);
		return;
	}
	public int getDETAffilReformedamer(){
		return m_iDETAffilReformedamer;
	}


	/**
	** affil_salvarmy type TINY() in table org_details 
	**/
	private int m_iDETAffilSalvarmy=0;
	public void setDETAffilSalvarmy(int number){
		m_iDETAffilSalvarmy = number;
	}
	public void setDETAffilSalvarmy(String number){
		m_iDETAffilSalvarmy = convertToInt(number);
		return;
	}
	public int getDETAffilSalvarmy(){
		return m_iDETAffilSalvarmy;
	}


	/**
	** affil_samaritanpurse type TINY() in table org_details 
	**/
	private int m_iDETAffilSamaritanpurse=0;
	public void setDETAffilSamaritanpurse(int number){
		m_iDETAffilSamaritanpurse = number;
	}
	public void setDETAffilSamaritanpurse(String number){
		m_iDETAffilSamaritanpurse = convertToInt(number);
		return;
	}
	public int getDETAffilSamaritanpurse(){
		return m_iDETAffilSamaritanpurse;
	}


	/**
	** affil_chrismissa type TINY() in table org_details 
	**/
	private int m_iDETAffilChrismissa=0;
	public void setDETAffilChrismissa(int number){
		m_iDETAffilChrismissa = number;
	}
	public void setDETAffilChrismissa(String number){
		m_iDETAffilChrismissa = convertToInt(number);
		return;
	}
	public int getDETAffilChrismissa(){
		return m_iDETAffilChrismissa;
	}


	/**
	** affil_uywi type TINY() in table org_details 
	**/
	private int m_iDETAffilUywi=0;
	public void setDETAffilUywi(int number){
		m_iDETAffilUywi = number;
	}
	public void setDETAffilUywi(String number){
		m_iDETAffilUywi = convertToInt(number);
		return;
	}
	public int getDETAffilUywi(){
		return m_iDETAffilUywi;
	}


	/**
	** affil_volamer type TINY() in table org_details 
	**/
	private int m_iDETAffilVolamer=0;
	public void setDETAffilVolamer(int number){
		m_iDETAffilVolamer = number;
	}
	public void setDETAffilVolamer(String number){
		m_iDETAffilVolamer = convertToInt(number);
		return;
	}
	public int getDETAffilVolamer(){
		return m_iDETAffilVolamer;
	}


	/**
	** affil_worldv type TINY() in table org_details 
	**/
	private int m_iDETAffilWorldv=0;
	public void setDETAffilWorldv(int number){
		m_iDETAffilWorldv = number;
	}
	public void setDETAffilWorldv(String number){
		m_iDETAffilWorldv = convertToInt(number);
		return;
	}
	public int getDETAffilWorldv(){
		return m_iDETAffilWorldv;
	}


	/**
	** affil_wycliffebib type TINY() in table org_details 
	**/
	private int m_iDETAffilWycliffebib=0;
	public void setDETAffilWycliffebib(int number){
		m_iDETAffilWycliffebib = number;
	}
	public void setDETAffilWycliffebib(String number){
		m_iDETAffilWycliffebib = convertToInt(number);
		return;
	}
	public int getDETAffilWycliffebib(){
		return m_iDETAffilWycliffebib;
	}


	/**
	** affil_ymcausa type TINY() in table org_details 
	**/
	private int m_iDETAffilYmcausa=0;
	public void setDETAffilYmcausa(int number){
		m_iDETAffilYmcausa = number;
	}
	public void setDETAffilYmcausa(String number){
		m_iDETAffilYmcausa = convertToInt(number);
		return;
	}
	public int getDETAffilYmcausa(){
		return m_iDETAffilYmcausa;
	}


	/**
	** affil_ymcasus type TINY() in table org_details 
	**/
	private int m_iDETAffilYmcasus=0;
	public void setDETAffilYmcasus(int number){
		m_iDETAffilYmcasus = number;
	}
	public void setDETAffilYmcasus(String number){
		m_iDETAffilYmcasus = convertToInt(number);
		return;
	}
	public int getDETAffilYmcasus(){
		return m_iDETAffilYmcasus;
	}


	/**
	** affil_younglife type TINY() in table org_details 
	**/
	private int m_iDETAffilYounglife=0;
	public void setDETAffilYounglife(int number){
		m_iDETAffilYounglife = number;
	}
	public void setDETAffilYounglife(String number){
		m_iDETAffilYounglife = convertToInt(number);
		return;
	}
	public int getDETAffilYounglife(){
		return m_iDETAffilYounglife;
	}


	/**
	** tech_participants type INT() in table org_details 
	**/
	private int m_iDETTechParticipants=0;
	public void setDETTechParticipants(int number){
		m_iDETTechParticipants = number;
	}
	public void setDETTechParticipants(String number){
		m_iDETTechParticipants = convertToInt(number);
		return;
	}
	public int getDETTechParticipants(){
		return m_iDETTechParticipants;
	}


	/**
	** tech_computers type INT() in table org_details 
	**/
	private int m_iDETTechComputers=0;
	public void setDETTechComputers(int number){
		m_iDETTechComputers = number;
	}
	public void setDETTechComputers(String number){
		m_iDETTechComputers = convertToInt(number);
		return;
	}
	public int getDETTechComputers(){
		return m_iDETTechComputers;
	}


	/**
	** tech_donate type LONG() in table org_details 
	**/
	private int m_iDETTechDonate=0;
	public void setDETTechDonate(int number){
		m_iDETTechDonate = number;
	}
	public void setDETTechDonate(String number){
		m_iDETTechDonate = convertToInt(number);
		return;
	}
	public int getDETTechDonate(){
		return m_iDETTechDonate;
	}


	/**
	** outcome_completion type INT() in table org_details 
	**/
	private int m_iDETOutcomeCompletion=0;
	public void setDETOutcomeCompletion(int number){
		m_iDETOutcomeCompletion = number;
	}
	public void setDETOutcomeCompletion(String number){
		m_iDETOutcomeCompletion = convertToInt(number);
		return;
	}
	public int getDETOutcomeCompletion(){
		return m_iDETOutcomeCompletion;
	}


	/**
	** outcome_certification type INT() in table org_details 
	**/
	private int m_iDETOutcomeCertification=0;
	public void setDETOutcomeCertification(int number){
		m_iDETOutcomeCertification = number;
	}
	public void setDETOutcomeCertification(String number){
		m_iDETOutcomeCertification = convertToInt(number);
		return;
	}
	public int getDETOutcomeCertification(){
		return m_iDETOutcomeCertification;
	}


	/**
	** outcome_college type INT() in table org_details 
	**/
	private int m_iDETOutcomeCollege=0;
	public void setDETOutcomeCollege(int number){
		m_iDETOutcomeCollege = number;
	}
	public void setDETOutcomeCollege(String number){
		m_iDETOutcomeCollege = convertToInt(number);
		return;
	}
	public int getDETOutcomeCollege(){
		return m_iDETOutcomeCollege;
	}


	/**
	** outcome_job type INT() in table org_details 
	**/
	private int m_iDETOutcomeJob=0;
	public void setDETOutcomeJob(int number){
		m_iDETOutcomeJob = number;
	}
	public void setDETOutcomeJob(String number){
		m_iDETOutcomeJob = convertToInt(number);
		return;
	}
	public int getDETOutcomeJob(){
		return m_iDETOutcomeJob;
	}


	/**
	** outcome_ged type INT() in table org_details 
	**/
	private int m_iDETOutcomeGed=0;
	public void setDETOutcomeGed(int number){
		m_iDETOutcomeGed = number;
	}
	public void setDETOutcomeGed(String number){
		m_iDETOutcomeGed = convertToInt(number);
		return;
	}
	public int getDETOutcomeGed(){
		return m_iDETOutcomeGed;
	}


	/**
	** outcome_discipleship type INT() in table org_details 
	**/
	private int m_iDETOutcomeDiscipleship=0;
	public void setDETOutcomeDiscipleship(int number){
		m_iDETOutcomeDiscipleship = number;
	}
	public void setDETOutcomeDiscipleship(String number){
		m_iDETOutcomeDiscipleship = convertToInt(number);
		return;
	}
	public int getDETOutcomeDiscipleship(){
		return m_iDETOutcomeDiscipleship;
	}


	/**
	** outcome_believer type INT() in table org_details 
	**/
	private int m_iDETOutcomeBeliever=0;
	public void setDETOutcomeBeliever(int number){
		m_iDETOutcomeBeliever = number;
	}
	public void setDETOutcomeBeliever(String number){
		m_iDETOutcomeBeliever = convertToInt(number);
		return;
	}
	public int getDETOutcomeBeliever(){
		return m_iDETOutcomeBeliever;
	}


	//===========> End Code Generated Methods For Table org_details 
	//===========> End Code Generated Methods For Table org_details 
	//===========> End Code Generated Methods For Table org_details 

}
