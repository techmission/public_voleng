package com.abrecorp.opensource.struts;

/**
* Created on 2006-03-29
* Individual Data
* Struts Actions HELPER Class MVC
* @author David Marcillo
*/

//Struts MVC objects
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

// data transfer objects
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.base.*;

class IndividualActionHelper extends DispatchAction {

	private ActionHelper m_BaseHelp = new ActionHelper();

	/**
	* Constructor 
	*/
    public IndividualActionHelper(){}


    /**
	* fill indiviudal data into form
	*/
	public int fillIndividualDataForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
		String aszTemp=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "username", aHeadObj.getUSPUsername() );

    	m_BaseHelp.setFormData(oFrm,"personid", "" + aHeadObj.getUSPPersonNumber() );
    	m_BaseHelp.setFormData(oFrm,"newsletter", "" + aHeadObj.getNewsletter() );
    	m_BaseHelp.setFormData(oFrm,"upnid", "" + aHeadObj.getUserProfileNID() );
    	m_BaseHelp.setFormData(oFrm,"upvid", "" + aHeadObj.getUserProfileVID() );
    	m_BaseHelp.setFormData(oFrm,"uplid", "" + aHeadObj.getUserProfileLID() );
    	m_BaseHelp.setFormData(oFrm,"uid", "" + aHeadObj.getUserUID() );
		m_BaseHelp.setFormData(oFrm,"personcodekey", aHeadObj.getUSPPercodekey() );
		m_BaseHelp.setFormData(oFrm,"passphraseone", aHeadObj.getUSPPassPhrase1() );
		m_BaseHelp.setFormData(oFrm,"passphrasetwo", aHeadObj.getUSPPassPhrase2() );
		aszTemp = aDateObj.format( aHeadObj.getUSPLastloginDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"lastlogindate", aszTemp );
		aszTemp = aDateObj.format( aHeadObj.getUSPCreateDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"createdate", aszTemp );
		m_BaseHelp.setFormData(oFrm,"createdby", "" + aHeadObj.getUSPCreateId() );
		aszTemp = aDateObj.format( aHeadObj.getUSPUpdateDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"updatedate", aszTemp );
		m_BaseHelp.setFormData(oFrm,"updateby", "" + aHeadObj.getUSPUpdateId() );
		m_BaseHelp.setFormData(oFrm,"agreeflag1", aHeadObj.getUSPAgree1Fg() );
		m_BaseHelp.setFormData(oFrm,"agreeflag2", aHeadObj.getUSPAgree2Fg() );
		m_BaseHelp.setFormData(oFrm,"namefirst", aHeadObj.getUSPNameFirst() );
		m_BaseHelp.setFormData(oFrm,"namelast", aHeadObj.getUSPNameLast() );
		m_BaseHelp.setFormData(oFrm,"othernumber", aHeadObj.getUSPOtherId() );
		m_BaseHelp.setFormData(oFrm,"email1addr", aHeadObj.getUSPEmail1Addr() );
		aszTemp = aDateObj.format( aHeadObj.getUSPEmail1verifyDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"email1verifydate", aszTemp );
		m_BaseHelp.setFormData(oFrm,"email1verifyflag", aHeadObj.getUSPEmail1verifyFg() );
		m_BaseHelp.setFormData(oFrm,"email2addr", aHeadObj.getUSPEmail2Addr() );
		m_BaseHelp.setFormData(oFrm,"phone1", aHeadObj.getUSPPhone1() );
		m_BaseHelp.setFormData(oFrm,"phone2", aHeadObj.getUSPPhone2() );
		m_BaseHelp.setFormData(oFrm,"fax1", aHeadObj.getUSPFax1() );
		m_BaseHelp.setFormData(oFrm,"addrline1", aHeadObj.getUSPAddrLine1() );
		m_BaseHelp.setFormData(oFrm,"addrline2", aHeadObj.getUSPAddrLine2() );
		m_BaseHelp.setFormData(oFrm,"addrline3", aHeadObj.getUSPAddrLine3() );
		m_BaseHelp.setFormData(oFrm,"addrcity", aHeadObj.getUSPAddrCity() );
		m_BaseHelp.setFormData(oFrm,"addrstateprov", aHeadObj.getUSPAddrStateprov() );
		m_BaseHelp.setFormData(oFrm,"addrpostalcode", aHeadObj.getUSPAddrPostalcode() );
		m_BaseHelp.setFormData(oFrm,"addrcountryname", aHeadObj.getUSPAddrCountryName() );
		m_BaseHelp.setFormData(oFrm,"birthyear", "" + aHeadObj.getBirthYear() );
		m_BaseHelp.setFormData(oFrm,"personwebpage", aHeadObj.getUSPWebpage() );
		m_BaseHelp.setFormData(oFrm,"primarylanguage", aHeadObj.getUSPPrimaryLanguage() );
		m_BaseHelp.setFormData(oFrm,"ethnicity", aHeadObj.getUSPEthnicity() );
		m_BaseHelp.setFormData(oFrm,"authtokens", aHeadObj.getUSPAuthTokens() );
		m_BaseHelp.setFormData(oFrm,"configtokens", aHeadObj.getUSPConfigTokens() );
		m_BaseHelp.setFormData(oFrm,"personcomment", aHeadObj.getUSPUserComment() );
		m_BaseHelp.setFormData(oFrm,"personinternalcomment", aHeadObj.getUSPInternalComment() );
		m_BaseHelp.setFormData(oFrm,"internalusertypecomment", aHeadObj.getUSPInternalUserTypeComment() );
		m_BaseHelp.setFormData(oFrm,"internaltaclitecomment", aHeadObj.getUSPInternalTacLiteComment() );
		m_BaseHelp.setFormData(oFrm,"volresume", aHeadObj.getUSPVolResume() );
		m_BaseHelp.setFormData(oFrm,"indivtitle", aHeadObj.getUSPTitle() );
		m_BaseHelp.setFormData(oFrm,"volskills", aHeadObj.getUSPVolSkills() );
		m_BaseHelp.setFormData(oFrm,"volskills2", aHeadObj.getUSPVolSkills2() );
		m_BaseHelp.setFormData(oFrm,"volskills3", aHeadObj.getUSPVolSkills3() );
		m_BaseHelp.setFormData(oFrm,"volskills1tid", "" + aHeadObj.getUSPVolSkills1TID() );
		m_BaseHelp.setFormData(oFrm,"volskills2tid", "" + aHeadObj.getUSPVolSkills2TID() );
		m_BaseHelp.setFormData(oFrm,"volskills3tid", "" + aHeadObj.getUSPVolSkills3TID() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea1", aHeadObj.getUSPVolInterestArea1() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea2", aHeadObj.getUSPVolInterestArea2() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea3", aHeadObj.getUSPVolInterestArea3() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea1tid", "" + aHeadObj.getUSPVolInterestArea1TID() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea2tid", "" + aHeadObj.getUSPVolInterestArea2TID() );
		m_BaseHelp.setFormData(oFrm,"volinterestarea3tid", "" + aHeadObj.getUSPVolInterestArea3TID() );
		m_BaseHelp.setFormData(oFrm,"vollang1", aHeadObj.getUSPVolLang1() );
		m_BaseHelp.setFormData(oFrm,"vollang2", aHeadObj.getUSPVolLang2() );
		m_BaseHelp.setFormData(oFrm,"vollang3", aHeadObj.getUSPVolLang3() );
		m_BaseHelp.setFormData(oFrm,"vollang1tid", "" + aHeadObj.getUSPVolLang1TID() );
		m_BaseHelp.setFormData(oFrm,"vollang2tid", "" + aHeadObj.getUSPVolLang2TID() );
		m_BaseHelp.setFormData(oFrm,"vollang3tid", "" + aHeadObj.getUSPVolLang3TID() );
		m_BaseHelp.setFormData(oFrm,"indivdenomaffil", aHeadObj.getUSPDenomAffilP() );
		m_BaseHelp.setFormData(oFrm,"indivdenomaffiltid", "" + aHeadObj.getUSPDenomAffilTID() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil", aHeadObj.getUSPOtherAffilP() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil1tid", "" + aHeadObj.getUSPOtherAffil1TID() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil2", aHeadObj.getUSPOtherAffil2() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil2tid", "" + aHeadObj.getUSPOtherAffil2TID() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil3", aHeadObj.getUSPOtherAffil3() );
		m_BaseHelp.setFormData(oFrm,"indivotheraffil3tid", "" + aHeadObj.getUSPOtherAffil3TID() );
		m_BaseHelp.setFormData(oFrm,"volavail", aHeadObj.getUSPVolAvail() );
		m_BaseHelp.setFormData(oFrm,"volavailtid", "" + aHeadObj.getUSPVolAvailTID() );
		m_BaseHelp.setFormData(oFrm,"volboard", aHeadObj.getUSPVolBoard() );
		m_BaseHelp.setFormData(oFrm,"volboardtid", "" + aHeadObj.getUSPVolBoardTID() );
		m_BaseHelp.setFormData(oFrm,"volvirt", aHeadObj.getUSPVolVirt() );
		m_BaseHelp.setFormData(oFrm,"volvirttid", "" + aHeadObj.getUSPVolVirtTID() );
		m_BaseHelp.setFormData(oFrm,"localvoltid", "" + aHeadObj.getUSPLocalVolTID() );
		m_BaseHelp.setFormData(oFrm,"groupfamilytid", "" + aHeadObj.getUSPGroupFamilyTID() );
		m_BaseHelp.setFormData(oFrm,"intrntid", "" + aHeadObj.getUSPIntrnTID() );
		m_BaseHelp.setFormData(oFrm,"sumintrntid", "" + aHeadObj.getUSPSumIntrnTID() );
		m_BaseHelp.setFormData(oFrm,"workstudytid", "" + aHeadObj.getUSPWorkStudyTID() );
		m_BaseHelp.setFormData(oFrm,"jobstid", "" + aHeadObj.getUSPJobsTID() );
		m_BaseHelp.setFormData(oFrm,"conferencetid", "" + aHeadObj.getUSPConferenceTID() );
		m_BaseHelp.setFormData(oFrm,"consultingtid", "" + aHeadObj.getUSPConsultingTID() );
		m_BaseHelp.setFormData(oFrm,"socjustgrpstid", "" + aHeadObj.getUSPSocJustGrpsTID() );
		m_BaseHelp.setFormData(oFrm,"localorgstid", "" + aHeadObj.getUSPLocalOrgsTID() );
		m_BaseHelp.setFormData(oFrm,"indivrace", aHeadObj.getUSPRaceP() );
		m_BaseHelp.setFormData(oFrm,"indivagerange", aHeadObj.getUSPAgeRangeP() );
		m_BaseHelp.setFormData(oFrm,"indivchristian", aHeadObj.getUSPChristianP() );
		m_BaseHelp.setFormData(oFrm,"attendchurch", aHeadObj.getUSPAttendChurchP() );
		m_BaseHelp.setFormData(oFrm,"addrprov", aHeadObj.getUSPAddrOtherProvince() );
		m_BaseHelp.setFormData(oFrm,"", aHeadObj.getUSPSiteUseType() );
		m_BaseHelp.setFormData(oFrm,"volunteer", aHeadObj.getUSPVolunteer() );
		m_BaseHelp.setFormDataInt(oFrm, "internshipinterest", aHeadObj.getInternshipInterest() ? 1 : 0);
		m_BaseHelp.setFormData(oFrm,"volunteertid", "" + aHeadObj.getUSPVolunteerTID() );
		m_BaseHelp.setFormData(oFrm,"subdomain", aHeadObj.getUSPSubdom() );
		m_BaseHelp.setFormData(oFrm,"timezone", "" + aHeadObj.getUSPTimezone() );
		m_BaseHelp.setFormData(oFrm,"personalitytype", aHeadObj.getUSPPersonality() );
		m_BaseHelp.setFormData(oFrm,"personalitytypetid", "" + aHeadObj.getUSPPersonalityTID() );
		m_BaseHelp.setFormData(oFrm,"provider", aHeadObj.getProvider() );
		m_BaseHelp.setFormData(oFrm,"signature", aHeadObj.getSignature() );
		m_BaseHelp.setFormData(oFrm,"timestamp", aHeadObj.getTimestamp() );
		m_BaseHelp.setFormData(oFrm,"personalityei", aHeadObj.getUSPPersonalityEI() );
		m_BaseHelp.setFormData(oFrm,"personalitysn", aHeadObj.getUSPPersonalitySN() );
		m_BaseHelp.setFormData(oFrm,"personalityft", aHeadObj.getUSPPersonalityFT() );
		m_BaseHelp.setFormData(oFrm,"personalityjp", aHeadObj.getUSPPersonalityJP() );
		m_BaseHelp.setFormDataIntArray(oFrm,"languagesarray", aHeadObj.getUSPLanguagesArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"skilltypesarray", aHeadObj.getUSPSkillTypesArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"serviceareasarray", aHeadObj.getUSPServiceAreasArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"lookingforarray", aHeadObj.getUSPLookingForArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"spiritualdevarray", aHeadObj.getUSPSpiritualDevArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"ministryareascausearray", aHeadObj.getUSPMinistryAreasArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"globalissuesarray", aHeadObj.getUSPGlobalIssuesArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"organizationaldevarray", aHeadObj.getUSPOrganizationalDevArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"reconciliationarray", aHeadObj.getUSPReconciliationArray() );
		m_BaseHelp.setFormData(oFrm,"skilltypes", "" + aHeadObj.getUSPSkillTypes() );
		m_BaseHelp.setFormData(oFrm,"serviceareas", "" + aHeadObj.getUSPServiceAreas() );
		m_BaseHelp.setFormData(oFrm,"lookingfor", "" + aHeadObj.getUSPLookingFor() );
		m_BaseHelp.setFormData(oFrm,"spiritualdev", "" + aHeadObj.getUSPSpiritualDev() );
		m_BaseHelp.setFormData(oFrm,"ministryareascause", "" + aHeadObj.getUSPMinistryAreasCause() );
		m_BaseHelp.setFormData(oFrm,"globalissues", "" + aHeadObj.getUSPGlobalIssues() );
		m_BaseHelp.setFormData(oFrm,"organizationaldev", "" + aHeadObj.getUSPOrganizationalDev() );
		m_BaseHelp.setFormData(oFrm,"reconciliation", "" + aHeadObj.getUSPReconciliation() );
		m_BaseHelp.setFormData(oFrm,"otherskills", "" + aHeadObj.getUSPOtherSkills() );
		m_BaseHelp.setFormData(oFrm,"otherpassions", "" + aHeadObj.getUSPOtherPassions() );
		m_BaseHelp.setFormData(oFrm,"otherlearninginterests", "" + aHeadObj.getUSPOtherLearningInterests() );
		m_BaseHelp.setFormData(oFrm,"subscribe", "" + aHeadObj.getUSPSubscribe() );
    	return 0;
    }
    // end-of method fillIndividualDataForm

    /**
	* get indiviudal data from registration form
	*/
	public int getIndividualDataFromForm(int iType, DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	
		aHeadObj.setUSPOrgCreation( m_BaseHelp.getFormData(oFrm,"orgcreation") );
		aHeadObj.setNewsletter( m_BaseHelp.getFormData(oFrm,"newsletter") );
		if(aHeadObj.getUSPOrgCreation().equalsIgnoreCase("Yes")){
			aHeadObj.setUSPSiteUseType( m_BaseHelp.getFormData(oFrm,"siteusetype") );
			return 0;
		}
    	if(iType == 110 ){// new create account page 1 fields
    		String tmp=m_BaseHelp.getFormData(oFrm,"uid");
    		aHeadObj.setImportExternalProfile( m_BaseHelp.getFormDataInt(oFrm, "import_external_profile") );
        	aHeadObj.setUSPUsername( m_BaseHelp.getFormData(oFrm,"username") );
        	aHeadObj.setUSPPassword( m_BaseHelp.getFormData(oFrm,"password1") );
        	aHeadObj.setPasswordConfirm( m_BaseHelp.getFormData(oFrm,"password2") );
    		aHeadObj.setUSPAgree1Fg( m_BaseHelp.getFormData(oFrm,"agreeflag1") );
    		//aHeadObj.setUSPAgree2Fg( m_BaseHelp.getFormData(oFrm,"agreeflag2") ); //--> combined into #1
    		aHeadObj.setUSPAgreeWVFg( m_BaseHelp.getFormData(oFrm,"agreeflagworldvision") );
        	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
    		aHeadObj.setUSPSiteUseType( m_BaseHelp.getFormData(oFrm,"siteusetype") );
    		aHeadObj.setUSPVolunteer( m_BaseHelp.getFormData(oFrm,"volunteer") );
    		aHeadObj.setUSPVolunteerTID( m_BaseHelp.getFormData(oFrm,"volunteertid") );
    		aHeadObj.setUSPSubdom( m_BaseHelp.getFormData(oFrm,"subdomain") );
    		aHeadObj.setProvider( m_BaseHelp.getFormData(oFrm,"provider") );
    		aHeadObj.setSignature( m_BaseHelp.getFormData(oFrm,"signature") );
    		aHeadObj.setTimestamp( m_BaseHelp.getFormData(oFrm,"timestamp") );
    		aHeadObj.setFacebookUID( m_BaseHelp.getFormData(oFrm,"facebookuid") );
    		aHeadObj.setUSPNameFirst( m_BaseHelp.getFormData(oFrm,"namefirst") );
    		aHeadObj.setUSPNameLast( m_BaseHelp.getFormData(oFrm,"namelast") );
    		aHeadObj.setUserUID( m_BaseHelp.getFormData(oFrm,"uid") );
    		aHeadObj.setUserUIDString( m_BaseHelp.getFormData(oFrm,"uid") );
    		aHeadObj.setUSPInternalComment( m_BaseHelp.getFormData(oFrm,"personinternalcomment") );
    		aHeadObj.setUSPAddrPostalcode( m_BaseHelp.getFormData(oFrm,"addrpostalcode") );
    		aHeadObj.setUSPAddrCountryName( m_BaseHelp.getFormData(oFrm,"addrcountryname") );
    		aHeadObj.setBirthYear( m_BaseHelp.getFormData(oFrm,"birthyear") );
    		aHeadObj.setMapToPage( m_BaseHelp.getFormData(oFrm,"maptopage") );
    		aHeadObj.setMappingToPage( m_BaseHelp.getFormData(oFrm,"mappingtopage") );
   		return 0;
    	}

    	if(iType == 101 ){
        	aHeadObj.setUSPUsername( m_BaseHelp.getFormData(oFrm,"username") );
        	aHeadObj.setUSPPassword( m_BaseHelp.getFormData(oFrm,"password1") );
        	aHeadObj.setPasswordConfirm( m_BaseHelp.getFormData(oFrm,"password2") );
    		aHeadObj.setUSPAgree1Fg( m_BaseHelp.getFormData(oFrm,"agreeflag1") );
    		aHeadObj.setUSPAgree2Fg( m_BaseHelp.getFormData(oFrm,"agreeflag2") );
    		aHeadObj.setUSPAgreeWVFg( m_BaseHelp.getFormData(oFrm,"agreeflagworldvision") );
        	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
    		aHeadObj.setMapToPage( m_BaseHelp.getFormData(oFrm,"maptopage") );
    		aHeadObj.setMappingToPage( m_BaseHelp.getFormData(oFrm,"mappingtopage") );
    	}
    	if(iType == 121 ){
        	aHeadObj.setUSPUsername( m_BaseHelp.getFormData(oFrm,"username") );
        	aHeadObj.setUSPPassword( m_BaseHelp.getFormData(oFrm,"password1") );
        	aHeadObj.setPasswordConfirm( m_BaseHelp.getFormData(oFrm,"password2") );
    		aHeadObj.setUSPAgree1Fg( m_BaseHelp.getFormData(oFrm,"agreeflag1") );
    		aHeadObj.setUSPAgree2Fg( m_BaseHelp.getFormData(oFrm,"agreeflag2") );
    		aHeadObj.setUSPAgreeWVFg( m_BaseHelp.getFormData(oFrm,"agreeflagworldvision") );
        	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
    		aHeadObj.setMapToPage( m_BaseHelp.getFormData(oFrm,"maptopage") );
    		aHeadObj.setMappingToPage( m_BaseHelp.getFormData(oFrm,"mappingtopage") );
    		aHeadObj.setUSPNameFirst( m_BaseHelp.getFormData(oFrm,"namefirst") );
    		aHeadObj.setUSPNameLast( m_BaseHelp.getFormData(oFrm,"namelast") );
    	}
    	if(iType == 303){
    		aHeadObj.setUSPAgree1Fg( m_BaseHelp.getFormData(oFrm,"agreeflag1") );
        	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
        	aHeadObj.setUSPEmail2Addr( m_BaseHelp.getFormData(oFrm,"email2addr") );
    		aHeadObj.setMapToPage( m_BaseHelp.getFormData(oFrm,"maptopage") );
    		aHeadObj.setMappingToPage( m_BaseHelp.getFormData(oFrm,"mappingtopage") );
    	}
    	//if(iType == 202 ){
		aHeadObj.setUSPPersonNumber( m_BaseHelp.getFormData(oFrm,"personid") );
		aHeadObj.setInternshipInterest( m_BaseHelp.getFormDataInt(oFrm, "internshipinterest") == 0 ? false : true );
		aHeadObj.setMapToPage( m_BaseHelp.getFormData(oFrm,"maptopage") );
		aHeadObj.setMappingToPage( m_BaseHelp.getFormData(oFrm,"mappingtopage") );
    	aHeadObj.setUSPAddrCountryName( m_BaseHelp.getFormData(oFrm,"country") );
		aHeadObj.setBirthYear( m_BaseHelp.getFormData(oFrm,"birthyear") );
    	aHeadObj.setUSPPhone1( m_BaseHelp.getFormData(oFrm,"phone1") );
    	aHeadObj.setUSPAddrPostalcode( m_BaseHelp.getFormData(oFrm,"postalcode") );
		aHeadObj.setUserProfileNID( m_BaseHelp.getFormData(oFrm,"upnid") );
		aHeadObj.setUserProfileVID( m_BaseHelp.getFormData(oFrm,"upvid") );
		aHeadObj.setUserProfileLID( m_BaseHelp.getFormData(oFrm,"uplid") );
		aHeadObj.setUserUID( m_BaseHelp.getFormData(oFrm,"uid") );
		aHeadObj.setUserUIDString( m_BaseHelp.getFormData(oFrm,"uid") );
		aHeadObj.setUSPPassPhrase1( m_BaseHelp.getFormData(oFrm,"passphraseone") );
		aHeadObj.setUSPPassPhrase2( m_BaseHelp.getFormData(oFrm,"passphrasetwo") );
		aHeadObj.setUSPNameFirst( m_BaseHelp.getFormData(oFrm,"namefirst") );
		aHeadObj.setUSPNameLast( m_BaseHelp.getFormData(oFrm,"namelast") );
		aHeadObj.setUSPOtherId( m_BaseHelp.getFormData(oFrm,"othernumber") );
		aHeadObj.setUSPEmail2Addr( m_BaseHelp.getFormData(oFrm,"email2addr") );
		aHeadObj.setUSPPhone1( m_BaseHelp.getFormData(oFrm,"phone1") );
		aHeadObj.setUSPPhone2( m_BaseHelp.getFormData(oFrm,"phone2") );
		aHeadObj.setUSPFax1( m_BaseHelp.getFormData(oFrm,"fax1") );
		aHeadObj.setUSPAddrLine1( m_BaseHelp.getFormData(oFrm,"addrline1") );
		aHeadObj.setUSPAddrLine2( m_BaseHelp.getFormData(oFrm,"addrline2") );
		aHeadObj.setUSPAddrLine3( m_BaseHelp.getFormData(oFrm,"addrline3") );
		aHeadObj.setUSPAddrCity( m_BaseHelp.getFormData(oFrm,"addrcity") );
		aHeadObj.setUSPAddrStateprov( m_BaseHelp.getFormData(oFrm,"addrstateprov") );
		aHeadObj.setUSPAddrPostalcode( m_BaseHelp.getFormData(oFrm,"addrpostalcode") );
		aHeadObj.setUSPAddrCountryName( m_BaseHelp.getFormData(oFrm,"addrcountryname") );
		aHeadObj.setUSPWebpage( m_BaseHelp.getFormData(oFrm,"personwebpage") );
		aHeadObj.setUSPPrimaryLanguage( m_BaseHelp.getFormData(oFrm,"primarylanguage") );
		aHeadObj.setUSPEthnicity( m_BaseHelp.getFormData(oFrm,"ethnicity") );
		aHeadObj.setUSPAuthTokens( m_BaseHelp.getFormData(oFrm,"authtokens") );
		aHeadObj.setUSPConfigTokens( m_BaseHelp.getFormData(oFrm,"configtokens") );
		aHeadObj.setUSPUserComment( m_BaseHelp.getFormData(oFrm,"personcomment") );
		aHeadObj.setUSPInternalComment( m_BaseHelp.getFormData(oFrm,"personinternalcomment") );
		aHeadObj.setUSPInternalUserTypeComment( m_BaseHelp.getFormData(oFrm,"internalusertypecomment") ); // rather than set in the form, should just be passed & not written over
		aHeadObj.setUSPInternalTacLiteComment( m_BaseHelp.getFormData(oFrm,"internaltaclitecomment") ); // the whole point is to be able to set this in jsp and not have to compile in; might be vulnerable, though
		aHeadObj.setUSPVolResume( m_BaseHelp.getFormData(oFrm,"volresume") );
		aHeadObj.setUSPTitle( m_BaseHelp.getFormData(oFrm,"indivtitle") );
		aHeadObj.setUSPVolSkills( m_BaseHelp.getFormData(oFrm,"volskills") );
		aHeadObj.setUSPVolSkills2( m_BaseHelp.getFormData(oFrm,"volskills2") );
		aHeadObj.setUSPVolSkills3( m_BaseHelp.getFormData(oFrm,"volskills3") );
		aHeadObj.setUSPVolSkills1TID( m_BaseHelp.getFormData(oFrm,"volskills1tid") );
		aHeadObj.setUSPVolSkills2TID( m_BaseHelp.getFormData(oFrm,"volskills2tid") );
		aHeadObj.setUSPVolSkills3TID( m_BaseHelp.getFormData(oFrm,"volskills3tid") );
		aHeadObj.setUSPVolInterestArea1( m_BaseHelp.getFormData(oFrm,"volinterestarea1") );
		aHeadObj.setUSPVolInterestArea2( m_BaseHelp.getFormData(oFrm,"volinterestarea2") );
		aHeadObj.setUSPVolInterestArea3( m_BaseHelp.getFormData(oFrm,"volinterestarea3") );
		aHeadObj.setUSPVolInterestArea1TID( m_BaseHelp.getFormData(oFrm,"volinterestarea1tid") );
		aHeadObj.setUSPVolInterestArea2TID( m_BaseHelp.getFormData(oFrm,"volinterestarea2tid") );
		aHeadObj.setUSPVolInterestArea3TID( m_BaseHelp.getFormData(oFrm,"volinterestarea3tid") );
		aHeadObj.setUSPVolLang1( m_BaseHelp.getFormData(oFrm,"vollang1") );
		aHeadObj.setUSPVolLang2( m_BaseHelp.getFormData(oFrm,"vollang2") );
		aHeadObj.setUSPVolLang3( m_BaseHelp.getFormData(oFrm,"vollang3") );
		aHeadObj.setUSPVolLang1TID( m_BaseHelp.getFormData(oFrm,"vollang1tid") );
		aHeadObj.setUSPVolLang2TID( m_BaseHelp.getFormData(oFrm,"vollang2tid") );
		aHeadObj.setUSPVolLang3TID( m_BaseHelp.getFormData(oFrm,"vollang3tid") );
		aHeadObj.setUSPDenomAffilP( m_BaseHelp.getFormData(oFrm,"indivdenomaffil") );
		aHeadObj.setUSPDenomAffilTID( m_BaseHelp.getFormData(oFrm,"indivdenomaffiltid") );
		aHeadObj.setUSPOtherAffilP( m_BaseHelp.getFormData(oFrm,"indivotheraffil") );
		aHeadObj.setUSPOtherAffil1TID( m_BaseHelp.getFormData(oFrm,"indivotheraffil1tid") );
		aHeadObj.setUSPOtherAffil2( m_BaseHelp.getFormData(oFrm,"indivotheraffil2") );
		aHeadObj.setUSPOtherAffil2TID( m_BaseHelp.getFormData(oFrm,"indivotheraffil2tid") );
		aHeadObj.setUSPOtherAffil3( m_BaseHelp.getFormData(oFrm,"indivotheraffil3") );
		aHeadObj.setUSPOtherAffil3TID( m_BaseHelp.getFormData(oFrm,"indivotheraffil3tid") );
		aHeadObj.setUSPVolAvail( m_BaseHelp.getFormData(oFrm,"volavail") );
		aHeadObj.setUSPVolAvailTID( m_BaseHelp.getFormData(oFrm,"volavailtid") );
		aHeadObj.setUSPVolBoard( m_BaseHelp.getFormData(oFrm,"volboard") );
		aHeadObj.setUSPVolBoardTID( m_BaseHelp.getFormData(oFrm,"volboardtid") );
		aHeadObj.setUSPVolVirt( m_BaseHelp.getFormData(oFrm,"volvirt") );
		aHeadObj.setUSPVolVirtTID( m_BaseHelp.getFormData(oFrm,"volvirttid") );
		aHeadObj.setUSPLocalVolTID( m_BaseHelp.getFormData(oFrm,"localvoltid") );
		aHeadObj.setUSPGroupFamilyTID( m_BaseHelp.getFormData(oFrm,"groupfamilytid") );
		aHeadObj.setUSPIntrnTID( m_BaseHelp.getFormData(oFrm,"intrntid") );
		aHeadObj.setUSPSumIntrnTID( m_BaseHelp.getFormData(oFrm,"sumintrntid") );
		aHeadObj.setUSPWorkStudyTID( m_BaseHelp.getFormData(oFrm,"workstudytid") );
		aHeadObj.setUSPJobsTID( m_BaseHelp.getFormData(oFrm,"jobstid") );
		aHeadObj.setUSPConferenceTID( m_BaseHelp.getFormData(oFrm,"conferencetid") );
		aHeadObj.setUSPConsultingTID( m_BaseHelp.getFormData(oFrm,"consultingtid") );
		aHeadObj.setUSPSocJustGrpsTID( m_BaseHelp.getFormData(oFrm,"socjustgrpstid") );
		aHeadObj.setUSPLocalOrgsTID( m_BaseHelp.getFormData(oFrm,"localorgstid") );
		aHeadObj.setUSPRaceP( m_BaseHelp.getFormData(oFrm,"indivrace") );
		aHeadObj.setUSPAgeRangeP( m_BaseHelp.getFormData(oFrm,"indivagerange") );
		aHeadObj.setUSPChristianP( m_BaseHelp.getFormData(oFrm,"indivchristian") );
		aHeadObj.setUSPAttendChurchP( m_BaseHelp.getFormData(oFrm,"attendchurch") );
		aHeadObj.setUSPAddrOtherProvince( m_BaseHelp.getFormData(oFrm,"addrprov") );
		aHeadObj.setUSPSiteUseType( m_BaseHelp.getFormData(oFrm,"siteusetype") );
		aHeadObj.setUSPVolunteer( m_BaseHelp.getFormData(oFrm,"volunteer") );
		aHeadObj.setUSPVolunteerTID( m_BaseHelp.getFormData(oFrm,"volunteertid") );
		aHeadObj.setUSPSubdom( m_BaseHelp.getFormData(oFrm,"subdomain") );
		aHeadObj.setUSPTimezone( m_BaseHelp.getFormData(oFrm,"timezone") );
		aHeadObj.setUSPPersonality( m_BaseHelp.getFormData(oFrm,"personalitytype") );
		aHeadObj.setUSPPersonalityTID( m_BaseHelp.getFormData(oFrm,"personalitytypetid") );
		aHeadObj.setProvider( m_BaseHelp.getFormData(oFrm,"provider") );
		aHeadObj.setSignature( m_BaseHelp.getFormData(oFrm,"signature") );
		aHeadObj.setTimestamp( m_BaseHelp.getFormData(oFrm,"timestamp") );
		aHeadObj.setUSPPersonalityEI( m_BaseHelp.getFormData(oFrm,"personalityei") );
		aHeadObj.setUSPPersonalitySN( m_BaseHelp.getFormData(oFrm,"personalitysn") );
		aHeadObj.setUSPPersonalityFT( m_BaseHelp.getFormData(oFrm,"personalityft") );
		aHeadObj.setUSPPersonalityJP( m_BaseHelp.getFormData(oFrm,"personalityjp") );
		aHeadObj.setUSPLanguagesArray( m_BaseHelp.getFormDataIntArray(oFrm,"languagesarray"));
		aHeadObj.setUSPSkillTypesArray( m_BaseHelp.getFormDataIntArray(oFrm,"skilltypesarray"));
		aHeadObj.setUSPLookingForArray( m_BaseHelp.getFormDataIntArray(oFrm,"lookingforarray"));
		aHeadObj.setUSPServiceAreasArray( m_BaseHelp.getFormDataIntArray(oFrm,"serviceareasarray"));
		aHeadObj.setUSPSpiritualDevArray( m_BaseHelp.getFormDataIntArray(oFrm,"spiritualdevarray"));
		aHeadObj.setUSPMinistryAreasArray( m_BaseHelp.getFormDataIntArray(oFrm,"ministryareascausearray"));
		aHeadObj.setUSPGlobalIssuesArray( m_BaseHelp.getFormDataIntArray(oFrm,"globalissuesarray"));
		aHeadObj.setUSPOrganizationalDevArray( m_BaseHelp.getFormDataIntArray(oFrm,"organizationaldevarray"));
		aHeadObj.setUSPReconciliationArray( m_BaseHelp.getFormDataIntArray(oFrm,"reconciliationarray"));
		aHeadObj.setUSPSkillTypes( m_BaseHelp.getFormData(oFrm,"skilltypes"));
		aHeadObj.setUSPLookingFor( m_BaseHelp.getFormData(oFrm,"lookingfor"));
		aHeadObj.setUSPServiceAreas( m_BaseHelp.getFormData(oFrm,"serviceareas"));
		aHeadObj.setUSPSpiritualDev( m_BaseHelp.getFormData(oFrm,"spiritualdev"));
		aHeadObj.setUSPMinistryAreasCause( m_BaseHelp.getFormData(oFrm,"ministryareascause"));
		aHeadObj.setUSPGlobalIssues( m_BaseHelp.getFormData(oFrm,"globalissues"));
		aHeadObj.setUSPOrganizationalDev( m_BaseHelp.getFormData(oFrm,"organizationaldev"));
		aHeadObj.setUSPReconciliation( m_BaseHelp.getFormData(oFrm,"reconciliation"));
		aHeadObj.setUSPOtherSkills( m_BaseHelp.getFormData(oFrm,"otherskills"));
		aHeadObj.setUSPOtherPassions( m_BaseHelp.getFormData(oFrm,"otherpassions"));
		aHeadObj.setUSPOtherLearningInterests( m_BaseHelp.getFormData(oFrm,"otherlearninginterests"));
		aHeadObj.setUSPSubscribe( m_BaseHelp.getFormData(oFrm,"subscribe"));
   	return 0;
    }
    // end-of method getIndividualDataFromForm

    /**
	* get indiviudal Username data from registration form
	*/
	public int getIndividualUsernameDataFromForm(int iType, DynaActionForm oFrm, PersonInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;

    	aHeadObj.setUSPInternalComment(aHeadObj.getUSPUsername());
    	
    	if(null == oFrm) return -1;
    	if(iType == 101 ){
        	aHeadObj.setUSPUsername( m_BaseHelp.getFormData(oFrm,"username") );
        	// check to see that the email value is different than in the DB
        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase(aHeadObj.getUSPUsername())){

        		return 0;
        	}
    		aszTemp = m_BaseHelp.getFormData(oFrm,"USPEmail1verifyDt");
    		aTempDateObj = aDateObj.convertToDate( aszTemp );
    		if(null != aTempDateObj){
    			aHeadObj.setUSPEmail1verifyDt( aTempDateObj );
    		} else {
    			aHeadObj.setUSPEmail1verifyDt( null );
    		}
    		aHeadObj.setUSPEmail1verifyFg( m_BaseHelp.getFormData(oFrm,"USPEmail1verifyFg") );
    	}
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setUSPBirthDt( aTempDateObj );
		} else {
			aHeadObj.setUSPBirthDt( null );
		}
    	return 0;
    }
    // end-of method getIndividualUsernameDataFromForm

    /**
	* get indiviudal EMAIL data from registration form
	*/
	public int getIndividualEmailDataFromForm(int iType, DynaActionForm oFrm, PersonInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;

    	aHeadObj.setUSPInternalComment(aHeadObj.getUSPEmail1Addr());
    	
    	if(null == oFrm) return -1;
    	if(iType == 101 ){
        	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
        	// check to see that the email value is different than in the DB
        	if(aHeadObj.getUSPInternalComment().equalsIgnoreCase(aHeadObj.getUSPEmail1Addr())){

        		return 0;
        	}
    		aszTemp = m_BaseHelp.getFormData(oFrm,"USPEmail1verifyDt");
    		aTempDateObj = aDateObj.convertToDate( aszTemp );
    		if(null != aTempDateObj){
    			aHeadObj.setUSPEmail1verifyDt( aTempDateObj );
    		} else {
    			aHeadObj.setUSPEmail1verifyDt( null );
    		}
    		aHeadObj.setUSPEmail1verifyFg( m_BaseHelp.getFormData(oFrm,"USPEmail1verifyFg") );
    	}
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setUSPBirthDt( aTempDateObj );
		} else {
			aHeadObj.setUSPBirthDt( null );
		}
    	return 0;
    }
    // end-of method getIndividualEmailDataFromForm

//end2006-09-12 -ali

//	2006-09-12 -ali
    /**
	* get indiviudal Password data from registration form
	*/
	public int getIndividualPwdDataFromForm(int iType, DynaActionForm oFrm, PersonInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;

    	// temporarily store current password value in usercomment; could do internal commnet
    	aHeadObj.setUSPUserComment(aHeadObj.getUSPPassword());
    	
    	if(null == oFrm) return -1;
    	if(iType == 101 ){
        	if (m_BaseHelp.getFormData(oFrm,"password1").equalsIgnoreCase("")){
        	}else{
        		aHeadObj.setUSPPassword( m_BaseHelp.getFormData(oFrm,"password1") );
            	// check to see that the pwd value is different than in the DB
            	if(aHeadObj.getUSPUserComment().equalsIgnoreCase(aHeadObj.getUSPPassword())){
            		aHeadObj.setUSPPassword(aHeadObj.getUSPUserComment());

            		return 0;
            	}
            	aHeadObj.setPasswordConfirm( m_BaseHelp.getFormData(oFrm,"password2") );

        	}
    	}
    	return 0;
    }
    // end-of method getIndividualPwdDataFromForm

//end2006-09-12 -ali
	
    /**
	* get personality data from personality forms
	*/
	public int getPersonalityDataFromForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj, int param){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate(); 
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	// load the information for the user's personality test
		//aHeadObj.setUserUID( m_BaseHelp.getFormData(oFrm,"uid") );
    	
    	aHeadObj.setUSPPersonalityPageNo( m_BaseHelp.getFormData(oFrm,"personalitypageno") );
    	aHeadObj.setUSPPersonalityI( m_BaseHelp.getFormData(oFrm,"personalitytypei") );
    	aHeadObj.setUSPPersonalityE( m_BaseHelp.getFormData(oFrm,"personalitytypee") );
    	aHeadObj.setUSPPersonalityS( m_BaseHelp.getFormData(oFrm,"personalitytypes") );
		aHeadObj.setUSPPersonalityN( m_BaseHelp.getFormData(oFrm,"personalitytypen") );
		aHeadObj.setUSPPersonalityF( m_BaseHelp.getFormData(oFrm,"personalitytypef") );
		aHeadObj.setUSPPersonalityT( m_BaseHelp.getFormData(oFrm,"personalitytypet") );
		aHeadObj.setUSPPersonalityJ( m_BaseHelp.getFormData(oFrm,"personalitytypej") );
		aHeadObj.setUSPPersonalityP( m_BaseHelp.getFormData(oFrm,"personalitytypep") );
		aHeadObj.setUSPPersonalityEI( m_BaseHelp.getFormData(oFrm,"personalityei") );
		aHeadObj.setUSPPersonalitySN( m_BaseHelp.getFormData(oFrm,"personalitysn") );
		aHeadObj.setUSPPersonalityFT( m_BaseHelp.getFormData(oFrm,"personalityft") );
		aHeadObj.setUSPPersonalityJP( m_BaseHelp.getFormData(oFrm,"personalityjp") );
		
		// Look for the facebookuid from the form and set it
		if(m_BaseHelp.getFormData(oFrm,"facebookuid").length()>0){
			aHeadObj.setFacebookUID( m_BaseHelp.getFormData(oFrm, "facebookuid"));
		}
		
		// for the possiblility of a user who already has a personality type returning to a page like ministry areas, make sure we don't 
		// clear their existing personality type unless they enter soemthing else
		if(m_BaseHelp.getFormData(oFrm,"personalitytype").length()>0 || m_BaseHelp.getFormData(oFrm,"personalitytypetid").length()>0){
			aHeadObj.setUSPPersonality( m_BaseHelp.getFormData(oFrm,"personalitytype") );
			aHeadObj.setUSPPersonalityTID( m_BaseHelp.getFormData(oFrm,"personalitytypetid") );
		}
    	
    	if(param == 0){   		
    		aHeadObj.setUSPServiceAreas( m_BaseHelp.getFormData(oFrm,"serviceareas") );
    		aHeadObj.setUSPSkillTypes( m_BaseHelp.getFormData(oFrm,"skilltypes") );
    		aHeadObj.setUSPLookingFor( m_BaseHelp.getFormData(oFrm,"lookingfor") );
    		aHeadObj.setUSPSpiritualDev( m_BaseHelp.getFormData(oFrm,"spiritualdev") );
    		aHeadObj.setUSPMinistryAreasCause( m_BaseHelp.getFormData(oFrm,"ministryareascause") );
    		aHeadObj.setUSPGlobalIssues( m_BaseHelp.getFormData(oFrm,"globalissues") );
    		aHeadObj.setUSPOrganizationalDev( m_BaseHelp.getFormData(oFrm,"organizationaldev") );
    		aHeadObj.setUSPReconciliation( m_BaseHelp.getFormData(oFrm,"reconciliation") );
    		aHeadObj.setUSPOtherSkills( m_BaseHelp.getFormData(oFrm,"otherskills") );
    		aHeadObj.setUSPOtherPassions( m_BaseHelp.getFormData(oFrm,"otherpassions") );
    		aHeadObj.setUSPOtherLearningInterests( m_BaseHelp.getFormData(oFrm,"otherlearninginterests") );
    	}
    	
    	if(param == 2){ // processFacebookCreateAccount
    		aHeadObj.setUSPServiceAreas( m_BaseHelp.getFormData(oFrm,"serviceareas") );
    		aHeadObj.setUSPSkillTypes( m_BaseHelp.getFormData(oFrm,"skilltypes") );
    		aHeadObj.setUSPLookingFor( m_BaseHelp.getFormData(oFrm,"lookingfor") );
    		aHeadObj.setUSPOtherSkills( m_BaseHelp.getFormData(oFrm,"otherskills") );
    		aHeadObj.setUSPOtherPassions( m_BaseHelp.getFormData(oFrm,"otherpassions") );
    		//if(m_BaseHelp.getFormData(oFrm,"facebookuid").length()>0){
    		//	aHeadObj.setFacebookUID( m_BaseHelp.getFormData(oFrm, "facebookuid"));
    		//}
    	}
    	
    	if(param == 3){
    		aHeadObj.setUSPSpiritualDev( m_BaseHelp.getFormData(oFrm,"spiritualdev") );
    		aHeadObj.setUSPMinistryAreasCause( m_BaseHelp.getFormData(oFrm,"ministryareascause") );
    		aHeadObj.setUSPGlobalIssues( m_BaseHelp.getFormData(oFrm,"globalissues") );
    		aHeadObj.setUSPOrganizationalDev( m_BaseHelp.getFormData(oFrm,"organizationaldev") );
    		aHeadObj.setUSPReconciliation( m_BaseHelp.getFormData(oFrm,"reconciliation") );
    		aHeadObj.setUSPOtherLearningInterests( m_BaseHelp.getFormData(oFrm,"otherlearninginterests") );
    	}
    	
		return 0;
    }
    // end-of method getPersonalityDataFromForm
	
    /**
	* fill personality test data into form
	*/
	public int fillPersonalityDataForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
		String aszTemp=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "username", aHeadObj.getUSPUsername() );
    	m_BaseHelp.setFormData(oFrm,"uid", "" + aHeadObj.getUserUID() );
		m_BaseHelp.setFormData(oFrm,"personalitypageno", "" + aHeadObj.getUSPPersonalityPageNo() );
		m_BaseHelp.setFormData(oFrm,"personalitytypei", "" + aHeadObj.getUSPPersonalityI() );
		m_BaseHelp.setFormData(oFrm,"personalitytypee", "" + aHeadObj.getUSPPersonalityE() );
		m_BaseHelp.setFormData(oFrm,"personalitytypes", "" + aHeadObj.getUSPPersonalityS() );
		m_BaseHelp.setFormData(oFrm,"personalitytypen", "" + aHeadObj.getUSPPersonalityN() );
		m_BaseHelp.setFormData(oFrm,"personalitytypef", "" + aHeadObj.getUSPPersonalityF() );
		m_BaseHelp.setFormData(oFrm,"personalitytypet", "" + aHeadObj.getUSPPersonalityT() );
		m_BaseHelp.setFormData(oFrm,"personalitytypej", "" + aHeadObj.getUSPPersonalityJ() );
		m_BaseHelp.setFormData(oFrm,"personalitytypep", "" + aHeadObj.getUSPPersonalityP() );
		m_BaseHelp.setFormData(oFrm,"personalityei", "" + aHeadObj.getUSPPersonalityEI() );
		m_BaseHelp.setFormData(oFrm,"personalitysn", "" + aHeadObj.getUSPPersonalitySN() );
		m_BaseHelp.setFormData(oFrm,"personalityft", "" + aHeadObj.getUSPPersonalityFT() );
		m_BaseHelp.setFormData(oFrm,"personalityjp", "" + aHeadObj.getUSPPersonalityJP() );
		m_BaseHelp.setFormData(oFrm,"personalitytype", "" + aHeadObj.getUSPPersonality() );
		m_BaseHelp.setFormData(oFrm,"personalitytypetid", "" + aHeadObj.getUSPPersonalityTID() );
		m_BaseHelp.setFormData(oFrm,"serviceareas", "" + aHeadObj.getUSPServiceAreas() );
		m_BaseHelp.setFormData(oFrm,"skilltypes", "" + aHeadObj.getUSPSkillTypes() );
		m_BaseHelp.setFormData(oFrm,"lookingfor", "" + aHeadObj.getUSPLookingFor() );
		m_BaseHelp.setFormData(oFrm,"spiritualdev", "" + aHeadObj.getUSPSpiritualDev() );
		m_BaseHelp.setFormData(oFrm,"ministryareascause", "" + aHeadObj.getUSPMinistryAreasCause() );
		m_BaseHelp.setFormData(oFrm,"globalissues", "" + aHeadObj.getUSPGlobalIssues() );
		m_BaseHelp.setFormData(oFrm,"organizationaldev", "" + aHeadObj.getUSPOrganizationalDev() );
		m_BaseHelp.setFormData(oFrm,"reconciliation", "" + aHeadObj.getUSPReconciliation() );
		m_BaseHelp.setFormData(oFrm,"otherskills", "" + aHeadObj.getUSPOtherSkills() );
		m_BaseHelp.setFormData(oFrm,"otherpassions", "" + aHeadObj.getUSPOtherPassions() );
		m_BaseHelp.setFormData(oFrm,"otherlearninginterests", "" + aHeadObj.getUSPOtherLearningInterests() );
		m_BaseHelp.setFormData(oFrm,"facebookuid", "" + aHeadObj.getFacebookUID() );
		return 0;
    }
    // end-of method fillPersonalityDataForm
	
	
	
    /**
	* fill Email data into form
	*/
	public int fillEmailDataForm(DynaActionForm oFrm, EmailInfoDTO aHeadObj){
		String aszTemp=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	m_BaseHelp.setFormData( oFrm, "contactfn" , aHeadObj.getEmailContactFN() ); 
    	m_BaseHelp.setFormData( oFrm, "contactln" , aHeadObj.getEmailContactLN() ); 
    	m_BaseHelp.setFormData( oFrm, "touser" , aHeadObj.getEmailToUser() ); 
    	m_BaseHelp.setFormData( oFrm, "siteemail" , aHeadObj.getEmailSiteEmail() ); 
    	m_BaseHelp.setFormData( oFrm, "subdomain" , aHeadObj.getEmailSubdom() ); 
    	m_BaseHelp.setFormData( oFrm, "oppname" , aHeadObj.getEmailOppName() );
    	m_BaseHelp.setFormData( oFrm, "oppid" , "" + aHeadObj.getEmailOppId() ); 
    	m_BaseHelp.setFormData( oFrm, "orgid" , "" + aHeadObj.getEmailOrgId() ); 
    	m_BaseHelp.setFormData( oFrm, "orgname" , aHeadObj.getEmailOrgName() ); 
    	m_BaseHelp.setFormData( oFrm, "orgaddr1" , aHeadObj.getEmailOrgAddr1() ); 
    	m_BaseHelp.setFormData( oFrm, "orgcity" , aHeadObj.getEmailOrgCity() ); 
    	m_BaseHelp.setFormData( oFrm, "orgstate" , aHeadObj.getEmailOrgState() ); 
    	m_BaseHelp.setFormData( oFrm, "orgprov" , aHeadObj.getEmailOrgProv() ); 
    	m_BaseHelp.setFormData( oFrm, "orgcountry" , aHeadObj.getEmailOrgCountry() ); 
    	m_BaseHelp.setFormData( oFrm, "orgphone" , aHeadObj.getEmailOrgPhone() ); 
    	m_BaseHelp.setFormData( oFrm, "orgweb" , aHeadObj.getEmailOrgWeb() ); 
    	m_BaseHelp.setFormData( oFrm, "orgemail" , aHeadObj.getEmailOrg() ); 
    	m_BaseHelp.setFormData( oFrm, "fromuser" , aHeadObj.getEmailFromUser() ); 
    	m_BaseHelp.setFormData( oFrm, "emailsubject" , aHeadObj.getEmailSubjectText() ); 
    	m_BaseHelp.setFormData( oFrm, "emailbodytxtintro" , aHeadObj.getEmailBodyTextIntro() ); 
    	m_BaseHelp.setFormData( oFrm, "emailbodytxt" , aHeadObj.getEmailBodyText() ); 
    	m_BaseHelp.setFormData( oFrm, "emailbodytxtclosing" , aHeadObj.getEmailBodyTextClosing() ); 
    	m_BaseHelp.setFormData( oFrm, "emailbodytxtres" , aHeadObj.getEmailBodyTextRes() ); 
    	m_BaseHelp.setFormData(oFrm, "emailresfilepath", aHeadObj.getEmailResumeFilePath() );
    	m_BaseHelp.setFormData( oFrm, "volid" , "" + aHeadObj.getEmailVolunteerId() ); 
    	m_BaseHelp.setFormData( oFrm, "volfn" , aHeadObj.getEmailVolFN() ); 
    	m_BaseHelp.setFormData( oFrm, "volln" , aHeadObj.getEmailVolLN() ); 
    	m_BaseHelp.setFormData( oFrm, "volphone" , aHeadObj.getEmailVolPhone1() );     	
    	m_BaseHelp.setFormData( oFrm, "volchris" , aHeadObj.getEmailVolChris() ); 
    	m_BaseHelp.setFormData( oFrm, "volskills" , aHeadObj.getEmailVolSkills() ); 
    	m_BaseHelp.setFormData( oFrm, "volskills2" , aHeadObj.getEmailVolSkills2() ); 
    	m_BaseHelp.setFormData( oFrm, "volskills3" , aHeadObj.getEmailVolSkills3() ); 
    	m_BaseHelp.setFormData( oFrm, "stmreferences" , aHeadObj.getEmailSTMReferencesText() );
    	
    	return 0;
    }
    // end-of method fillEmailDataForm
    /**
	* get Email data from registration form
	*/
	public int getEmailDataFromForm(DynaActionForm oFrm, EmailInfoDTO aHeadObj, boolean includingFiles){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		//
    	aHeadObj.setEmailId( m_BaseHelp.getFormData(oFrm,"emailid") );
		aHeadObj.setEmailOppNID( m_BaseHelp.getFormData(oFrm,"oppnid") );
		aHeadObj.setEmailOrgNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		aHeadObj.setEmailContactFN( m_BaseHelp.getFormData(oFrm,"contactfn") );
		aHeadObj.setEmailContactLN( m_BaseHelp.getFormData(oFrm,"contactln") );
		aHeadObj.setEmailFromUser( m_BaseHelp.getFormData(oFrm,"fromuser") );
		aHeadObj.setEmailToUser( m_BaseHelp.getFormData(oFrm,"touser") );
		aHeadObj.setEmailBodyText( m_BaseHelp.getFormData(oFrm,"emailbodytxt") );
		if(includingFiles) {
			aHeadObj.setResumeFile((FormFile)oFrm.get("resume_file"));
			aHeadObj.setCoverLetterFile((FormFile)oFrm.get("cover_letter_file"));
			aHeadObj.setApplicationFile((FormFile)oFrm.get("application_file"));
			aHeadObj.setRequiredDocumentFiles(new HashMap<Integer, FormFile>());
			for(int i = 1; i <= 3; i++) {
				Integer j = (Integer) oFrm.get("required_document_nid_"+i);
				if(j != null) {
					aHeadObj.getRequiredDocumentFiles().put(
						j, (FormFile) oFrm.get("required_document_file_"+i)
					);
				}
			}
		}
		return 0;
    }
    // end-of method getEmailDataFromForm

    /**
	* get Email data from registration form
	*/
	public int getApplicDataFromForm(DynaActionForm oFrm, ApplicationInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		
		aHeadObj.setOPPNID( m_BaseHelp.getFormData(oFrm,"oppnid") );
		aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		aHeadObj.setNID( m_BaseHelp.getFormData(oFrm,"nid") );
    	aHeadObj.setVID( m_BaseHelp.getFormData(oFrm,"vid") );
		aHeadObj.setUID( m_BaseHelp.getFormData(oFrm,"voluid") );
		aHeadObj.setUPNID( m_BaseHelp.getFormData(oFrm,"volnid") );
		aHeadObj.setVolNID( m_BaseHelp.getFormData(oFrm,"volnid") );
		aHeadObj.setSource( m_BaseHelp.getFormData(oFrm,"src") );
		

		aHeadObj.setORGNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgnids"));
		aHeadObj.setOPPNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppnids"));
		

		aHeadObj.setNameFirst( m_BaseHelp.getFormData(oFrm,"firstname") );
		aHeadObj.setNameLast( m_BaseHelp.getFormData(oFrm,"lastname") );
		aHeadObj.setEmailAddr( m_BaseHelp.getFormData(oFrm,"email") );
		aHeadObj.setPhone( m_BaseHelp.getFormData(oFrm,"phone") );
		aHeadObj.setSourceTID( m_BaseHelp.getFormData(oFrm,"sourcetid") );
		aHeadObj.setInternLengthTID( m_BaseHelp.getFormData(oFrm,"internlengthtid") );
		aHeadObj.setInternTypeTID( m_BaseHelp.getFormData(oFrm,"interntypetid") );
		aHeadObj.setInternTypeTIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"interntypetids") );
		aHeadObj.setCVDegreeCareerGoals( m_BaseHelp.getFormData(oFrm,"cvdegree_carreergoals") );
		aHeadObj.setChristian( m_BaseHelp.getFormData(oFrm,"ischris") );
		aHeadObj.setAgeRequirement( m_BaseHelp.getFormData(oFrm,"agerequirement") );
		aHeadObj.setDiploma( m_BaseHelp.getFormData(oFrm,"diploma") );
		aHeadObj.setCitizenTID( m_BaseHelp.getFormData(oFrm,"citizentid") );		
		aHeadObj.setTimeCommitAbility( m_BaseHelp.getFormData(oFrm,"timecommitability") );
		aHeadObj.setBachelorsAttending( m_BaseHelp.getFormData(oFrm,"attendingbachelors") );
		aHeadObj.setLastYrActiveHS( m_BaseHelp.getFormData(oFrm,"lastyrhighschool") );
		aHeadObj.setGender( m_BaseHelp.getFormData(oFrm,"gender") );
		aHeadObj.setHasBachelors( m_BaseHelp.getFormData(oFrm,"hasbachelors") );
		aHeadObj.setCredits( m_BaseHelp.getFormData(oFrm,"credits") );
		return 0;
    }
    // end-of method getEmailDataFromForm
	

    /**
	* get Email data from registration form
	*/
	public int getApplicDataFromForm2(DynaActionForm oFrm, ApplicationInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		
    	aHeadObj.setNID( m_BaseHelp.getFormData(oFrm,"nid") );
    	aHeadObj.setVID( m_BaseHelp.getFormData(oFrm,"vid") );
		aHeadObj.setOPPNID( m_BaseHelp.getFormData(oFrm,"oppnid") );
		aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		aHeadObj.setUID( m_BaseHelp.getFormData(oFrm,"voluid") );
		aHeadObj.setVolNID( m_BaseHelp.getFormData(oFrm,"volnid") );
		aHeadObj.setSource( m_BaseHelp.getFormData(oFrm,"src") );

		aHeadObj.setORGNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgnids"));
		aHeadObj.setOPPNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppnids"));
		

		aHeadObj.setTestimony( m_BaseHelp.getFormData(oFrm,"testimony") );
		aHeadObj.setGeoPref( m_BaseHelp.getFormData(oFrm,"geopref") );
		aHeadObj.setAddrLine1( m_BaseHelp.getFormData(oFrm,"addr") );
		aHeadObj.setAddrCity( m_BaseHelp.getFormData(oFrm,"city") );
		aHeadObj.setAddrStateprov( m_BaseHelp.getFormData(oFrm,"prov") );
		aHeadObj.setAddrPostalcode( m_BaseHelp.getFormData(oFrm,"postal") );
		aHeadObj.setAddrCountryName( m_BaseHelp.getFormData(oFrm,"country") );
		return 0;
    }
    // end-of method getEmailDataFromForm2
	

    /**
	* get Email data from registration form
	*/
	public int getApplicDataFromForm3(DynaActionForm oFrm, ApplicationInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		
		aHeadObj.setNID( m_BaseHelp.getFormData(oFrm,"nid") );
    	aHeadObj.setVID( m_BaseHelp.getFormData(oFrm,"vid") );
		aHeadObj.setOPPNID( m_BaseHelp.getFormData(oFrm,"oppnid") );
		aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		aHeadObj.setUID( m_BaseHelp.getFormData(oFrm,"voluid") );
		aHeadObj.setSource( m_BaseHelp.getFormData(oFrm,"src") );

		aHeadObj.setORGNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgnids"));
		aHeadObj.setOPPNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppnids"));
		
		aHeadObj.setPosPrefTID( m_BaseHelp.getFormData(oFrm,"pospreftid") );
		aHeadObj.setSkillsTID( m_BaseHelp.getFormData(oFrm,"skillstid") );
		aHeadObj.setInternReason( m_BaseHelp.getFormData(oFrm,"internreason") );
		aHeadObj.setWorkEnvironTID( m_BaseHelp.getFormData(oFrm,"workenvirontid") );
		aHeadObj.setPopulPrefTID( m_BaseHelp.getFormData(oFrm,"poppreftid") );
		aHeadObj.setLang( m_BaseHelp.getFormData(oFrm,"lang") );
		aHeadObj.setChurch( m_BaseHelp.getFormData(oFrm,"church") );
		aHeadObj.setDegreeProgTID( m_BaseHelp.getFormData(oFrm,"degreeprogtid") );
		aHeadObj.setMajor( m_BaseHelp.getFormData(oFrm,"major") );
		aHeadObj.setCareerGoals( m_BaseHelp.getFormData(oFrm,"careergoals") );
		aHeadObj.setHrlyCommit( m_BaseHelp.getFormData(oFrm,"hrlycommit") );
		aHeadObj.setLivableStipend( m_BaseHelp.getFormData(oFrm,"livablestipend") );
		aHeadObj.setLivableStipendExpl( m_BaseHelp.getFormData(oFrm,"livablestipendexpl") );
		aHeadObj.setCrimRecord( m_BaseHelp.getFormData(oFrm,"crimrecord") );
		aHeadObj.setCrimDescrip( m_BaseHelp.getFormData(oFrm,"crimdescrip") );
		aHeadObj.setHousing( m_BaseHelp.getFormData(oFrm,"housing") );
		aHeadObj.setServiceSite( m_BaseHelp.getFormData(oFrm,"servicesite") );
		aHeadObj.setLocPrefInfo( m_BaseHelp.getFormData(oFrm,"locpref") );
		aHeadObj.setStartTime( m_BaseHelp.getFormData(oFrm,"starttime") );
		aHeadObj.setForwardResume( m_BaseHelp.getFormData(oFrm,"forwardresume") );
		aHeadObj.setWebcam( m_BaseHelp.getFormData(oFrm,"webcam") );
		aHeadObj.setSkype( m_BaseHelp.getFormData(oFrm,"skype") );
		aHeadObj.setInternLength( m_BaseHelp.getFormData(oFrm,"internlength") );
		aHeadObj.setPastoralRef( m_BaseHelp.getFormData(oFrm,"pastoralref") );
		aHeadObj.setPastoralRefChurch( m_BaseHelp.getFormData(oFrm,"pastoralrefchurch") );
		aHeadObj.setPastoralRefPhone( m_BaseHelp.getFormData(oFrm,"pastoralrefphone") );
		aHeadObj.setPastoralRefEmail( m_BaseHelp.getFormData(oFrm,"pastoralrefemail") );
		aHeadObj.setProfRef( m_BaseHelp.getFormData(oFrm,"profref") );
		aHeadObj.setProfRefOrg( m_BaseHelp.getFormData(oFrm,"profreforg") );
		aHeadObj.setProfRefPhone( m_BaseHelp.getFormData(oFrm,"profrefphone") );
		aHeadObj.setProfRefEmail( m_BaseHelp.getFormData(oFrm,"profrefemail") );
		aHeadObj.setDOBDt( m_BaseHelp.getFormData(oFrm,"dob") );
		return 0;
    }
    // end-of method getEmailDataFromForm3
	

	
    /**
	* get data from login form
	*/
	public int getLoginDataFromForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
    	aHeadObj.setUSPPassword( m_BaseHelp.getFormData(oFrm,"password1") );
		aHeadObj.setUSPInternalComment( m_BaseHelp.getFormData(oFrm,"personinternalcomment") );
    	aHeadObj.setSignature( m_BaseHelp.getFormData(oFrm,"signature") );
    	aHeadObj.setTimestamp( m_BaseHelp.getFormData(oFrm,"timestamp") );
		aHeadObj.setProvider( m_BaseHelp.getFormData(oFrm,"provider") );
    	aHeadObj.setUserUID( m_BaseHelp.getFormData(oFrm,"uid") );
    	aHeadObj.setUserUIDString( m_BaseHelp.getFormData(oFrm,"uid") );
    	aHeadObj.setFacebookUID( m_BaseHelp.getFormData(oFrm, "facebookuid"));
    	aHeadObj.setImportExternalProfile( m_BaseHelp.getFormDataInt(oFrm, "import_external_profile") );

    	return 0;
    }
    // end-of method getLoginDataFromForm
	
	
    /**
	* get data from logged in post on um.org
	*/
	public int getLoginUserDataFromForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	aHeadObj.setUSPEmail1Addr( m_BaseHelp.getFormData(oFrm,"email1addr") );
    	aHeadObj.setUserUID( m_BaseHelp.getFormData(oFrm,"uid") );
		aHeadObj.setUSPInternalComment( m_BaseHelp.getFormData(oFrm,"personinternalcomment") );

    	return 0;
    }
    // end-of method getLoginDataFromForm

	
    /**
	* get data from forgot password form
	*/
	public int getForgotPasswordDataFromForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	aHeadObj.setUSPUsername( m_BaseHelp.getFormData(oFrm,"username") );

    	return 0;
    }
    // end-of method getLoginDataFromForm

	
	

    /**
	* get indiviudal data from registration form
	*/
	public int getVolEmailDataFromForm(DynaActionForm oFrm, EmailInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	// contact person info
		aHeadObj.setEmailContactFN( m_BaseHelp.getFormData(oFrm, "contactfn" ) ); 
		aHeadObj.setEmailContactLN( m_BaseHelp.getFormData( oFrm, "contactln" ) ); 
    	// org, opp info to list
		aHeadObj.setEmailToUser(  m_BaseHelp.getFormData( oFrm, "touser" ) ); 
		aHeadObj.setEmailOppNID( m_BaseHelp.getFormData( oFrm, "oppnid" ) ); 
		aHeadObj.setEmailOrgNID( m_BaseHelp.getFormData( oFrm, "orgnid" )); 
    	aHeadObj.setEmailVolUID( m_BaseHelp.getFormData( oFrm, "voluid" )); 
		aHeadObj.setEmailVolRailsID( m_BaseHelp.getFormData(oFrm,"volrailsid") );
    	aHeadObj.setEmailOppName(  m_BaseHelp.getFormData( oFrm, "oppname" )); 
    	aHeadObj.setEmailOrgName( m_BaseHelp.getFormData( oFrm, "orgname" )); 
    	aHeadObj.setEmailOrgAddr1( m_BaseHelp.getFormData( oFrm, "orgaddr1" )); 
    	aHeadObj.setEmailOrgCity( m_BaseHelp.getFormData( oFrm, "orgcity" )); 
    	aHeadObj.setEmailOrgState( m_BaseHelp.getFormData( oFrm, "orgstate" )); 
    	aHeadObj.setEmailOrgProv( m_BaseHelp.getFormData( oFrm, "orgprov" )); 
    	aHeadObj.setEmailOrgCountry( m_BaseHelp.getFormData( oFrm, "orgcountry" )); 
    	aHeadObj.setEmailOrgPhone( m_BaseHelp.getFormData( oFrm, "orgphone" )); 
    	aHeadObj.setEmailOrgWeb( m_BaseHelp.getFormData( oFrm, "orgweb" )); 
    	
    	aHeadObj.setEmailOppBkgrnd( m_BaseHelp.getFormData( oFrm, "oppbkgrnd" )); 
    	aHeadObj.setEmailOppReference( m_BaseHelp.getFormData( oFrm, "oppreference" )); 

    	// email content - volunteer info
    	aHeadObj.setEmailFromUser( m_BaseHelp.getFormData( oFrm, "fromuser" )); 
    	aHeadObj.setEmailBodyTextRes( m_BaseHelp.getFormData( oFrm, "emailbodytxtres" )); 
		aHeadObj.setEmailResumeFilePath( m_BaseHelp.getFormData(oFrm,"emailresfilepath") );
    	//String DUPLICATE 
    	aHeadObj.setEmailVolUID( m_BaseHelp.getFormData( oFrm, "voluid" )); 
    	aHeadObj.setEmailVolFN( m_BaseHelp.getFormData( oFrm, "volfn" )); 
    	aHeadObj.setEmailVolLN( m_BaseHelp.getFormData( oFrm, "volln" )); 
    	aHeadObj.setEmailVolPhone1( m_BaseHelp.getFormData( oFrm, "volphone" )); 
   	return 0;
    }
    // end-of method getVolEmailDataFromForm



}


