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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//data transfer objects
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.base.*;
import com.maxmind.geoip.LookupService;

class OrganizationActionHelper extends DispatchAction {

	private ActionHelper m_BaseHelp = new ActionHelper();

	/**
	* Constructor 
	* This is a modification
	*/
    public OrganizationActionHelper(){}
    
    /**
	* put opportunity data into form
	*/
	public int putOppDataIntoForm(DynaActionForm oFrm, OrgOpportunityInfoDTO aHeadObj){
		String aszTemp=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "errormsg", aHeadObj.getErrorMsg() );

    	m_BaseHelp.setFormData(oFrm, "oppnid",""+aHeadObj.getOPPNID() );
    	m_BaseHelp.setFormData(oFrm, "oppnumber",""+aHeadObj.getOPPOppNumber() );
    	m_BaseHelp.setFormData(oFrm, "opptitle", aHeadObj.getOPPTitle() );
    	m_BaseHelp.setFormData(oFrm, "urlalias", aHeadObj.getOPPUrlAlias() );
//    	m_BaseHelp.setFormData(oFrm, "feed", aHeadObj.getFromFeeds() );
    	m_BaseHelp.setFormData(oFrm, "oppdesc", aHeadObj.getOPPDescription() );
    	m_BaseHelp.setFormData(oFrm, "opprequirements", aHeadObj.getOPPRequirements() );
    	m_BaseHelp.setFormData(oFrm, "oppreqcodetype", aHeadObj.getOPPRequiredCodeType() );
    	m_BaseHelp.setFormData(oFrm, "oppreqcodename", aHeadObj.getOPPRequiredCodeDesc() );
    	m_BaseHelp.setFormData(oFrm, "oppmission", aHeadObj.getOPPMission() );

    	m_BaseHelp.setFormData(oFrm, "oppskills", aHeadObj.getOPPSkills() );
    	m_BaseHelp.setFormData(oFrm, "oppskills2", aHeadObj.getOPPSkills2() );
    	m_BaseHelp.setFormData(oFrm, "oppskills3", aHeadObj.getOPPSkills3() );
    	m_BaseHelp.setFormData(oFrm, "oppskills1tid",""+aHeadObj.getOPPSkills1TID() );
    	m_BaseHelp.setFormData(oFrm, "oppskills2tid",""+aHeadObj.getOPPSkills2TID() );
    	m_BaseHelp.setFormData(oFrm, "oppskills3tid",""+aHeadObj.getOPPSkills3TID() );

    	m_BaseHelp.setFormData(oFrm, "hqoroffsite", aHeadObj.getOPPHQorOffSite() );
    	m_BaseHelp.setFormData(oFrm, "privateopp",""+aHeadObj.getOPPPrivate() );

    	m_BaseHelp.setFormData(oFrm,"orgnid", "" + aHeadObj.getORGNID() );
    	m_BaseHelp.setFormData(oFrm,"orgnumber", "" + aHeadObj.getOPPOrgNumber() );
		m_BaseHelp.setFormData(oFrm,"prognumber", "" + aHeadObj.getOPPProgNumber() );
		m_BaseHelp.setFormData(oFrm,"oppcodekey", aHeadObj.getOPPOppcodekey() );

		m_BaseHelp.setFormData(oFrm,"oppstatus", aHeadObj.getOPPStatus() );
    	m_BaseHelp.setFormData(oFrm, "opppositiontypetid",""+aHeadObj.getOPPPositionTypeTID() );

		m_BaseHelp.setFormData(oFrm,"opptype", aHeadObj.getOPPType() );
		m_BaseHelp.setFormData(oFrm,"language", aHeadObj.getOPPLanguages() );
    	m_BaseHelp.setFormData(oFrm, "opplanguagetid",""+aHeadObj.getOPPLanguageTID() );
		m_BaseHelp.setFormData(oFrm,"volsneeded",""+ aHeadObj.getOPPVolsNeeded() );
		m_BaseHelp.setFormData(oFrm,"hoursrequired",""+ aHeadObj.getOPPCommitHourly() );
		m_BaseHelp.setFormData(oFrm,"hourstype", aHeadObj.getOPPCommitType() );
		m_BaseHelp.setFormData(oFrm,"oppnumvol",""+ aHeadObj.getOPPNumVolOpp() );

		m_BaseHelp.setFormData(oFrm,"oppfrequency",""+ aHeadObj.getOPPFrequency() );
		m_BaseHelp.setFormData(oFrm,"oppfreq",""+ aHeadObj.getOPPFreq() );
    	m_BaseHelp.setFormData(oFrm, "oppfrequencytid",""+aHeadObj.getOPPFrequencyTID() );

		m_BaseHelp.setFormData(oFrm,"oppshortterm",""+ aHeadObj.getOPPShortTerm() );
		m_BaseHelp.setFormData(oFrm,"backgroundcheck", aHeadObj.getOPPBackgroundCheck() );
		m_BaseHelp.setFormData(oFrm,"addr1", aHeadObj.getOPPAddrLine1() );
		m_BaseHelp.setFormData(oFrm,"addr2", aHeadObj.getOPPAddrLine2() );
		m_BaseHelp.setFormData(oFrm,"addr3", aHeadObj.getOPPAddrLine3() );
		m_BaseHelp.setFormData(oFrm,"city", aHeadObj.getOPPAddrCity() );
		m_BaseHelp.setFormData(oFrm,"state", aHeadObj.getOPPAddrStateprov() );
		m_BaseHelp.setFormData(oFrm,"prov", aHeadObj.getOPPAddrOthrprov() );
		m_BaseHelp.setFormData(oFrm,"postalcode", aHeadObj.getOPPAddrPostalcode() );
		m_BaseHelp.setFormData(oFrm,"country", aHeadObj.getOPPAddrCountryName() );
		m_BaseHelp.setFormData(oFrm,"county", aHeadObj.getOPPAddrCounty() );
		m_BaseHelp.setFormData(oFrm,"groupmin", "" + aHeadObj.getOPPGroupMin() );
		m_BaseHelp.setFormData(oFrm,"groupmax", "" + aHeadObj.getOPPGroupMax() );
		m_BaseHelp.setFormData(oFrm,"oppcostold", aHeadObj.getOPPCost() );

		m_BaseHelp.setFormData(oFrm,"compensation", aHeadObj.getOPPCompensation() );
//		m_BaseHelp.setFormData(oFrm,"stipend", aHeadObj.getOPPStipend() );
//    	m_BaseHelp.setFormData(oFrm, "oppstipendtid",""+aHeadObj.getOPPStipendTID() );

		m_BaseHelp.setFormData(oFrm,"stmreferences", aHeadObj.getOPPSTMReferences() );
		m_BaseHelp.setFormData(oFrm,"amntpd", aHeadObj.getOPPAmntPd() );
		m_BaseHelp.setFormData(oFrm,"costinclude", aHeadObj.getOPPCostInclude() );
		m_BaseHelp.setFormData(oFrm,"additionaldetail", aHeadObj.getOPPAddDetails() );
		m_BaseHelp.setFormData(oFrm,"appdeadlineold", aHeadObj.getOPPAppDeadline() );

		m_BaseHelp.setFormData(oFrm,"duration", aHeadObj.getOPPDuration() );
		m_BaseHelp.setFormData(oFrm,"triplength", aHeadObj.getOPPTripLength() );
    	m_BaseHelp.setFormData(oFrm, "opptriplengthtid",""+aHeadObj.getOPPTripLengthTID() );

    	
		m_BaseHelp.setFormDataIntArray(oFrm,"benefitstidsarray", aHeadObj.getBenefitsArray() );
    	
    	
    	m_BaseHelp.setFormData(oFrm, "roomboardtid",""+aHeadObj.getOPPRoomBoardTID() );
		m_BaseHelp.setFormData(oFrm,"stipend", aHeadObj.getOPPStipend() );
    	m_BaseHelp.setFormData(oFrm, "stipendtid",""+aHeadObj.getOPPStipendTID() );
    	m_BaseHelp.setFormData(oFrm, "medinsurtid",""+aHeadObj.getOPPMedInsurTID() );
    	m_BaseHelp.setFormData(oFrm, "transporttid",""+aHeadObj.getOPPTransportTID() );
    	m_BaseHelp.setFormData(oFrm, "americorpstid",""+aHeadObj.getOPPAmeriCorpsTID() );

//    	m_BaseHelp.setFormData(oFrm, "opproomboardtid",""+aHeadObj.getOPPRoomBoardTID() );
    	m_BaseHelp.setFormData(oFrm, "oppworkstudytid",""+aHeadObj.getOPPWorkStudyTID() );
    	
		m_BaseHelp.setFormData(oFrm,"region", aHeadObj.getOPPRegion() );
		m_BaseHelp.setFormData(oFrm,"referencereq", aHeadObj.getOPPReferenceReq() );
		m_BaseHelp.setFormData(oFrm,"oppcost", "" + aHeadObj.getOPPCostUsd() );
		m_BaseHelp.setFormData(oFrm,"oppcostterm", aHeadObj.getOPPCostTerm() );
		m_BaseHelp.setFormData(oFrm,"subdomain", aHeadObj.getOPPSubdom() );
		m_BaseHelp.setFormData(oFrm,"oppexpiredrenew", "" + aHeadObj.getOPPExpiredRenew() );

    	// now taxonomy; no longer concatenated string - DRUPALIZATION of opportunities - ajm - 2008-05-08
		m_BaseHelp.setFormDataIntArray(oFrm,"serviceareatidsarray", aHeadObj.getServiceAreasArray() );
    	
    	
    	// now taxonomy; no longer concatenated string - DRUPALIZATION of opportunities - ajm - 2008-05-08
    	m_BaseHelp.setFormData(oFrm, "focusarea1", aHeadObj.getOPPFocusAreas() );
    	m_BaseHelp.setFormData(oFrm, "focusarea2", aHeadObj.getOPPFocusAreas2() );
    	m_BaseHelp.setFormData(oFrm, "focusarea3", aHeadObj.getOPPFocusAreas3() );
    	m_BaseHelp.setFormData(oFrm, "focusarea4", aHeadObj.getOPPFocusAreas4() );
    	m_BaseHelp.setFormData(oFrm, "focusarea5", aHeadObj.getOPPFocusAreas5() );

    	m_BaseHelp.setFormData(oFrm, "greatfor1tid",""+aHeadObj.getOPPGreatFor1TID() );
    	m_BaseHelp.setFormData(oFrm, "greatfor2tid",""+aHeadObj.getOPPGreatFor2TID() );
    	m_BaseHelp.setFormData(oFrm, "greatfor3tid",""+aHeadObj.getOPPGreatFor3TID() );
    	m_BaseHelp.setFormData(oFrm, "greatfor4tid",""+aHeadObj.getOPPGreatFor4TID() );
    	m_BaseHelp.setFormData(oFrm, "greatfor5tid",""+aHeadObj.getOPPGreatFor5TID() );
    	
    	
    	
		m_BaseHelp.setFormData(oFrm,"datereq", aHeadObj.getOPPDaterequired() );
    	m_BaseHelp.setFormData(oFrm, "daterequiredtid",""+aHeadObj.getOPPDaterequiredTID() );
		//m_BaseHelp.setFormData(oFrm,"startdate",""+ aHeadObj.getOPPStartDt() );
		//m_BaseHelp.setFormData(oFrm,"enddate",""+ aHeadObj.getOPPEndDt() );
		//m_BaseHelp.setFormData(oFrm,"appdeadline",""+ aHeadObj.getOPPApplicDeadline() );
		//m_BaseHelp.setFormData(oFrm,"createdate",""+ aHeadObj.getOPPCreateDt() );
		//m_BaseHelp.setFormData(oFrm,"updatedate",""+ aHeadObj.getOPPUpdateDt() );
		
		/**/
		aszTemp = aDateObj.format( aHeadObj.getOPPStartDt(),ABREDate.SHORT2,ABREDate.NO_TIME);
		m_BaseHelp.setFormData(oFrm,"startdate", aszTemp );
		aszTemp = aDateObj.format( aHeadObj.getOPPEndDt(),ABREDate.SHORT2,ABREDate.NO_TIME);
		m_BaseHelp.setFormData(oFrm,"enddate", aszTemp );

		// application deadline
		aszTemp = aDateObj.format( aHeadObj.getOPPApplicDeadline(),ABREDate.SHORT2,ABREDate.NO_TIME);
		m_BaseHelp.setFormData(oFrm,"appdeadline", aszTemp );
		/**/
		m_BaseHelp.setFormData(oFrm,"contactpersonnumber", "" + aHeadObj.getOPPPrimaryPersonUID() );
		/**/
		aszTemp = aDateObj.format( aHeadObj.getOPPCreateDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"createdate", aszTemp );
		/**/
		m_BaseHelp.setFormData(oFrm,"createuser", "" + aHeadObj.getOPPCreateId() );
		/**/
		aszTemp = aDateObj.format( aHeadObj.getOPPUpdateDt(),ABREDate.SHORT2,ABREDate.SHORT);
		m_BaseHelp.setFormData(oFrm,"updatedate", aszTemp );
		/**/
		m_BaseHelp.setFormData(oFrm,"updateuser", "" + aHeadObj.getOPPUpdateId() );

		m_BaseHelp.setFormData(oFrm,"interntype", aHeadObj.getOPPInternType() );
		
		m_BaseHelp.setFormData(oFrm, "questionnaire_type", aHeadObj.getQuestionnaireType());
        m_BaseHelp.setFormDataInt(oFrm, "questionnaire_id", aHeadObj.getQuestionnaireId());
        m_BaseHelp.setFormDataInt(oFrm, "resume_required", aHeadObj.getResumeRequired());
        m_BaseHelp.setFormDataInt(oFrm, "cover_letter_required", aHeadObj.getCoverLetterRequired());
        m_BaseHelp.setFormDataFile(oFrm, "questionnaire_file", aHeadObj.getQuestionnaireClientFile());
//        oFrm.set("questionnaire_file", aHeadObj.getQuestionnaireClientFile());
        
    	return 0;
    }
    // end-of method putOppDataIntoForm

	public void getSocialGraphDataFromForm(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj) {
		aHeadObj.setSocialGraphIAgreeDataUsage(m_BaseHelp.getFormDataInt(oFrm, "social_graph_I_agree_data_usage"));
		aHeadObj.setSocialGraphIAgreeCaching(m_BaseHelp.getFormDataInt(oFrm, "social_graph_I_agree_caching"));
		aHeadObj.setSocialGraphIAgreeLogo(m_BaseHelp.getFormDataInt(oFrm, "social_graph_I_agree_logo"));
		aHeadObj.setSocialGraphIAgreeFinal(m_BaseHelp.getFormDataInt(oFrm, "social_graph_I_agree_final"));
		aHeadObj.setSocialGraphExplanation(m_BaseHelp.getFormData(oFrm, "social_graph_explanation"));
		aHeadObj.setSocialGraphSignature(m_BaseHelp.getFormData(oFrm, "social_graph_signature"));
	}
	
    /**
	* get opportunity data from add form
	*/
	public int getOrgOppFromForm1(DynaActionForm oFrm, OrgOpportunityInfoDTO aHeadObj){
		String aszTemp=null;
		Date aTempDateObj=null;
		ABREDate aDateObj = new ABREDate();
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	aHeadObj.setOPPTitle( m_BaseHelp.getFormData(oFrm,"opptitle") );
    	
    	//stick the following in a condition; if there is a form element of name urlalias and it's not blank, make it that; otherwise, make it the orgname
    	aHeadObj.setOPPUrlAlias( m_BaseHelp.getFormData(oFrm,"urlalias") );
		aHeadObj.setFromFeeds( m_BaseHelp.getFormDataBoolean(oFrm,"feed"));
    	if(aHeadObj.getOPPUrlAlias().length()<1){
        	aHeadObj.setOPPUrlAlias( m_BaseHelp.getFormData(oFrm,"opptitle") );
    	}
    	
    	aHeadObj.setRequestType( m_BaseHelp.getFormData(oFrm,"requesttype") );

    	aHeadObj.setOPPDescription( m_BaseHelp.getFormData(oFrm,"oppdesc") );
    	aHeadObj.setOPPRequirements( m_BaseHelp.getFormData(oFrm,"opprequirements") );
    	aHeadObj.setOPPRequiredCodeType( m_BaseHelp.getFormData(oFrm,"oppreqcodetype") );
    	aHeadObj.setOPPRequiredCodeDesc( m_BaseHelp.getFormData(oFrm,"oppreqcodename") );
    	aHeadObj.setOPPMission( m_BaseHelp.getFormData(oFrm,"oppmission") );

    	aHeadObj.setOPPSkills( m_BaseHelp.getFormData(oFrm,"oppskills") );
    	aHeadObj.setOPPSkills2( m_BaseHelp.getFormData(oFrm,"oppskills2") );
    	aHeadObj.setOPPSkills3( m_BaseHelp.getFormData(oFrm,"oppskills3") );
    	aHeadObj.setOPPSkills1TID( m_BaseHelp.getFormData(oFrm,"oppskills1tid") );
    	aHeadObj.setOPPSkills2TID( m_BaseHelp.getFormData(oFrm,"oppskills2tid") );
    	aHeadObj.setOPPSkills3TID( m_BaseHelp.getFormData(oFrm,"oppskills3tid") );

    	aHeadObj.setOPPHQorOffSite( m_BaseHelp.getFormData(oFrm,"hqoroffsite") );
    	aHeadObj.setOPPPrivate( m_BaseHelp.getFormData(oFrm,"privateopp") );

    	aHeadObj.setOPPProgNumber( m_BaseHelp.getFormData(oFrm,"prognumber") );

		aHeadObj.setOPPStatus( m_BaseHelp.getFormData(oFrm,"oppstatus") );
		aHeadObj.setOPPPositionTypeTID( m_BaseHelp.getFormData(oFrm,"opppositiontypetid") );

		aHeadObj.setOPPType( m_BaseHelp.getFormData(oFrm,"opptype") );
		aHeadObj.setOPPVolsNeeded( m_BaseHelp.getFormData(oFrm,"volsneeded") );
		aHeadObj.setOPPCommitHourly( m_BaseHelp.getFormData(oFrm,"hoursrequired") );
		aHeadObj.setOPPCommitType( m_BaseHelp.getFormData(oFrm,"hourstype") );
		aHeadObj.setOPPNumVolOpp( m_BaseHelp.getFormData(oFrm,"oppnumvol") );

		aHeadObj.setOPPFrequency( m_BaseHelp.getFormData(oFrm,"oppfrequency") );
		aHeadObj.setOPPFreq( m_BaseHelp.getFormData(oFrm,"oppfreq") );
		aHeadObj.setOPPFrequencyTID( m_BaseHelp.getFormData(oFrm,"oppfrequencytid") );

		aHeadObj.setOPPShortTerm( m_BaseHelp.getFormData(oFrm,"oppshortterm") );
		aHeadObj.setOPPBackgroundCheck( m_BaseHelp.getFormData(oFrm,"backgroundcheck") );

		aHeadObj.setOPPLanguages( m_BaseHelp.getFormData(oFrm,"language") );
		aHeadObj.setOPPLanguageTID( m_BaseHelp.getFormData(oFrm,"opplanguagetid") );

		aHeadObj.setOPPAddrLine1( m_BaseHelp.getFormData(oFrm,"addr1") );
    	aHeadObj.setOPPAddrLine2( m_BaseHelp.getFormData(oFrm,"addr2") );
		aHeadObj.setOPPAddrLine3( m_BaseHelp.getFormData(oFrm,"addr3") );
    	aHeadObj.setOPPAddrCity( m_BaseHelp.getFormData(oFrm,"city") );
    	aHeadObj.setOPPAddrStateprov( m_BaseHelp.getFormData(oFrm,"state") );
		aHeadObj.setOPPAddrOthrprov( m_BaseHelp.getFormData(oFrm,"prov") );
    	aHeadObj.setOPPAddrPostalcode( m_BaseHelp.getFormData(oFrm,"postalcode") );
    	aHeadObj.setOPPAddrCountryName( m_BaseHelp.getFormData(oFrm,"country") );
		aHeadObj.setOPPAddrCounty( m_BaseHelp.getFormData(oFrm,"county") );
		aHeadObj.setOPPGroupMin( m_BaseHelp.getFormData(oFrm,"groupmin") );
		aHeadObj.setOPPGroupMax( m_BaseHelp.getFormData(oFrm,"groupmax") );
		aHeadObj.setOPPCost( m_BaseHelp.getFormData(oFrm,"oppcostold") );

		aHeadObj.setOPPCompensation( m_BaseHelp.getFormData(oFrm,"compensation") );
//		aHeadObj.setOPPStipend( m_BaseHelp.getFormData(oFrm,"stipend") );
//		aHeadObj.setOPPStipendTID( m_BaseHelp.getFormData(oFrm,"oppstipendtid") );

		aHeadObj.setOPPSTMReferences( m_BaseHelp.getFormData(oFrm,"stmreferences") );
		aHeadObj.setOPPAmntPd( m_BaseHelp.getFormData(oFrm,"amntpd") );
		aHeadObj.setOPPCostInclude( m_BaseHelp.getFormData(oFrm,"costinclude") );
		aHeadObj.setOPPAddDetails( m_BaseHelp.getFormData(oFrm,"additionaldetail") );
		aHeadObj.setOPPAppDeadline( m_BaseHelp.getFormData(oFrm,"appdeadlineold") );

		aHeadObj.setOPPDuration( m_BaseHelp.getFormData(oFrm,"duration") );
		aHeadObj.setOPPTripLength( m_BaseHelp.getFormData(oFrm,"triplength") );
		aHeadObj.setOPPTripLengthTID( m_BaseHelp.getFormData(oFrm,"opptriplengthtid") );

		aHeadObj.setBenefitsArray( m_BaseHelp.getFormDataIntArray(oFrm,"benefitstidsarray"));

		aHeadObj.setOPPRoomBoardTID( m_BaseHelp.getFormData(oFrm,"roomboardtid") );
		aHeadObj.setOPPStipend( m_BaseHelp.getFormData(oFrm,"stipend") );
		aHeadObj.setOPPStipendTID( m_BaseHelp.getFormData(oFrm,"stipendtid") );
		aHeadObj.setOPPMedInsurTID( m_BaseHelp.getFormData(oFrm,"medinsurtid") );
		aHeadObj.setOPPTransportTID( m_BaseHelp.getFormData(oFrm,"transporttid") );
		aHeadObj.setOPPAmeriCorpsTID( m_BaseHelp.getFormData(oFrm,"americorpstid") );

//		aHeadObj.setOPPRoomBoardTID( m_BaseHelp.getFormData(oFrm,"opproomboardtid") );
		aHeadObj.setOPPWorkStudyTID( m_BaseHelp.getFormData(oFrm,"oppworkstudytid") );
		
		aHeadObj.setOPPRegion( m_BaseHelp.getFormData(oFrm,"region") );
		aHeadObj.setOPPReferenceReq( m_BaseHelp.getFormData(oFrm,"referencereq") );
		aHeadObj.setOPPCostUsd( m_BaseHelp.getFormData(oFrm,"oppcost") );
		aHeadObj.setOPPCostTerm( m_BaseHelp.getFormData(oFrm,"oppcostterm") );
		aHeadObj.setOPPSubdom( m_BaseHelp.getFormData(oFrm,"subdomain") );
		aHeadObj.setOPPSiteEmail( m_BaseHelp.getFormData(oFrm,"siteemail") );
		aHeadObj.setOPPExpiredRenew( m_BaseHelp.getFormData(oFrm,"oppexpiredrenew") );

		aHeadObj.setOPPDaterequired( m_BaseHelp.getFormData(oFrm,"datereq") );
		aHeadObj.setOPPDaterequiredTID( m_BaseHelp.getFormData(oFrm,"daterequiredtid") );
		
		aHeadObj.setOPPStartDtMonth( m_BaseHelp.getFormData(oFrm,"startDtMonth") );
		aHeadObj.setOPPStartDtDate( m_BaseHelp.getFormData(oFrm,"startDtDate") );
		aHeadObj.setOPPStartDtYear( m_BaseHelp.getFormData(oFrm,"startDtYear") );


		long lTime=0; int iTime=0;
		aszTemp="";
		aszTemp = m_BaseHelp.getFormData(oFrm,"startdate");
		if(aszTemp.length()<1){
			if(	aHeadObj.getOPPStartDtMonth().length() > 0 ||
					aHeadObj.getOPPStartDtDate().length() > 0 ||
					aHeadObj.getOPPStartDtYear().length() > 0
			){
				if(	aHeadObj.getOPPStartDtMonth().length() > 0){
					aszTemp += aHeadObj.getOPPStartDtMonth() + "/";
				}else{
					aszTemp+="01/";
				}
				if(	aHeadObj.getOPPStartDtDate().length() > 0){
					aszTemp += aHeadObj.getOPPStartDtDate() + "/";
				}else{
					aszTemp+="01/";
				}
				if(	aHeadObj.getOPPStartDtYear().length() > 0){
					aszTemp += aHeadObj.getOPPStartDtYear();
				}else{
					aszTemp+="2011";
				}
			}else{
				aszTemp="";
			}
		}
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setOPPStartDt( aTempDateObj );
		} else {
			aHeadObj.setOPPStartDt( null );
		}

		aHeadObj.setOPPEndDtMonth( m_BaseHelp.getFormData(oFrm,"endDtMonth") );
		aHeadObj.setOPPEndDtDate( m_BaseHelp.getFormData(oFrm,"endDtDate") );
		aHeadObj.setOPPEndDtYear( m_BaseHelp.getFormData(oFrm,"endDtYear") );

		aszTemp="";
		aszTemp = m_BaseHelp.getFormData(oFrm,"enddate");
		if(aszTemp.length()<1){
			if(	aHeadObj.getOPPEndDtMonth().length() > 0 ||
					aHeadObj.getOPPEndDtDate().length() > 0 ||
					aHeadObj.getOPPEndDtYear().length() > 0
			){
				if(	aHeadObj.getOPPEndDtMonth().length() > 0){
					aszTemp += aHeadObj.getOPPEndDtMonth() + "/";
				}else{
					aszTemp+="01/";
				}
				if(	aHeadObj.getOPPEndDtDate().length() > 0){
					aszTemp += aHeadObj.getOPPEndDtDate() + "/";
				}else{
					aszTemp+="01/";
				}
				if(	aHeadObj.getOPPEndDtYear().length() > 0){
					aszTemp += aHeadObj.getOPPEndDtYear();
				}else{
					aszTemp+="2011";
				}
			}else{
				aszTemp="";
			}
		}
		
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setOPPEndDt( aTempDateObj );
		} else {
			aHeadObj.setOPPEndDt( null );
		}

		
		
		aszTemp = m_BaseHelp.getFormData(oFrm,"appdeadline");
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setOPPApplicDeadline( aTempDateObj );
		} else {
			aHeadObj.setOPPApplicDeadline( null );
		}

		// added with DRUPALIZATION of opportunities; no longer string concatenation - ajm - 2008-05-08
		aHeadObj.setServiceAreasArray( m_BaseHelp.getFormDataIntArray(oFrm,"serviceareatidsarray"));
			
		aHeadObj.setOPPFocusAreas( m_BaseHelp.getFormData(oFrm,"focusarea1") );
		aHeadObj.setOPPFocusAreas2( m_BaseHelp.getFormData(oFrm,"focusarea2") );
		aHeadObj.setOPPFocusAreas3( m_BaseHelp.getFormData(oFrm,"focusarea3") );
		aHeadObj.setOPPFocusAreas4( m_BaseHelp.getFormData(oFrm,"focusarea4") );
		aHeadObj.setOPPFocusAreas5( m_BaseHelp.getFormData(oFrm,"focusarea5") );
		
		aHeadObj.setOPPGreatFor1TID( m_BaseHelp.getFormData(oFrm,"greatfor1tid") );
		aHeadObj.setOPPGreatFor2TID( m_BaseHelp.getFormData(oFrm,"greatfor2tid") );
		aHeadObj.setOPPGreatFor3TID( m_BaseHelp.getFormData(oFrm,"greatfor3tid") );
		aHeadObj.setOPPGreatFor4TID( m_BaseHelp.getFormData(oFrm,"greatfor4tid") );
		aHeadObj.setOPPGreatFor5TID( m_BaseHelp.getFormData(oFrm,"greatfor5tid") );
	
		aHeadObj.setOPPPrimaryPersonUIDModified( m_BaseHelp.getFormData(oFrm,"oppprimarycontactuid") );
//		aHeadObj.setOPPContactUIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppcontactuidsarray"));
		aHeadObj.setOPPContactUIDsModifiedArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppcontactuidsarray"));
//		aHeadObj.setOPPContactUIDsRcvEmailArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppcontactuidsrcvemailarray"));
		aHeadObj.setOPPContactUIDsRcvEmailModifiedArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppcontactuidsrcvemailarray"));

		aHeadObj.setOPPInternType( m_BaseHelp.getFormData(oFrm,"interntype") );
		
		aHeadObj.setQuestionnaireId(m_BaseHelp.getFormDataInt(oFrm, "questionnaire_id"));
		aHeadObj.setQuestionnaireType(m_BaseHelp.getFormData(oFrm, "questionnaire_type"));
		aHeadObj.setQuestionnaireClientFile((FormFile)oFrm.get("questionnaire_file"));
	    aHeadObj.setResumeRequired(m_BaseHelp.getFormDataInt(oFrm, "resume_required"));
		aHeadObj.setCoverLetterRequired(m_BaseHelp.getFormDataInt(oFrm, "cover_letter_required"));
		
		aHeadObj.setRequiredDocumentsToAdd(new LinkedList<RequiredDocumentDTO>());
		for(int i = 1; i <= 3; i++) {
			FormFile file = (FormFile) oFrm.get("required_document_to_add_file_" + i);
			String name = (String) oFrm.get("required_document_to_add_name_" + i);
			if((file == null || file.getFileName().length() <= 0) && (name == null || name.length() <= 0)) continue;
			RequiredDocumentDTO doc = new RequiredDocumentDTO();
			doc.setClientFile(file);
			
			// FIXME: set extension based on file.getContentType()
			String[] tokens = file.getFileName().split("\\.");
			doc.setExtension(tokens[tokens.length-1]);
			
			doc.setName(name);
			aHeadObj.getRequiredDocumentsToAdd().add(doc);
		}
		
		aHeadObj.setRequiredDocumentToRemoveNids(Arrays.asList((Integer[])oFrm.get("required_document_to_remove_nids")));
		
    	return 0;
    }
    // end-of method getOrgOppFromForm1()


    /**
	* get org favorites data
	*/
	public int getOrgFavoritesFromForm(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	
		aHeadObj.setRequestType( m_BaseHelp.getFormData(oFrm,"requesttype"));
		aHeadObj.setORGPrevFavoritedOPPNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"favoritedoppnids"));
		aHeadObj.setORGPrevFavoritedORGNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"favoritedorgnids"));
		aHeadObj.setORGFavoritedOPPNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppnids"));
		aHeadObj.setORGFavoritedORGNIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgnids"));
		aHeadObj.setFavoriteChildOpps( m_BaseHelp.getFormDataBoolean(oFrm,"favoritechildopps"));

		aHeadObj.setORGPrevFavoritedOPPNIDsFromFeedArray( m_BaseHelp.getFormDataIntArray(oFrm,"favoritedoppnidsfromfeed"));
		aHeadObj.setORGFavoritedOPPNIDsFromFeedArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppnidsfromfeed"));

		return 0;
	}
	
    /**
	* fill opportunity contact person data into form
	*/
	public int fillContactDataIntoForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "orgprimaryperson", "" + aHeadObj.getUSPPersonNumber() );
    	m_BaseHelp.setFormData(oFrm, "contactfirstname", aHeadObj.getUSPNameFirst() );
    	m_BaseHelp.setFormData(oFrm, "contactlastname", aHeadObj.getUSPNameLast() );
    	m_BaseHelp.setFormData(oFrm, "contactemail", aHeadObj.getUSPEmail1Addr() );
    	m_BaseHelp.setFormData(oFrm, "orguid", "" + aHeadObj.getUserUID() );
    	
    	return 0;
    }
    // end-of method org contact data
	
    /**
	* fill organization contact person data into form
	*/
	public int fillOrgContactDataIntoForm(DynaActionForm oFrm, PersonInfoDTO aHeadObj){
    	return fillContactDataIntoForm(oFrm, aHeadObj);
    }
    // end-of method org contact data


	/**
	* fill organization data into form
	*/
	public int fillOrgDataEditForm(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj){
		String aszTemp=null;
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "errormsg", aHeadObj.getErrorMsg() );
    	m_BaseHelp.setFormData(oFrm, "orgnid",""+aHeadObj.getORGNID() );
    	m_BaseHelp.setFormData(oFrm, "orgnumber",""+aHeadObj.getORGOrgNumber() );
    	m_BaseHelp.setFormData(oFrm, "orgname", aHeadObj.getORGOrgName() );
    	m_BaseHelp.setFormData(oFrm, "urlalias", aHeadObj.getORGUrlAlias() );
    	m_BaseHelp.setFormData(oFrm, "addr1", aHeadObj.getORGAddrLine1() );
    	m_BaseHelp.setFormData(oFrm, "addr2", aHeadObj.getORGAddrLine2() );
		m_BaseHelp.setFormData(oFrm,"addr3", aHeadObj.getORGAddrLine3() );
    	m_BaseHelp.setFormData(oFrm, "city", aHeadObj.getORGAddrCity() );
    	m_BaseHelp.setFormData(oFrm, "state", aHeadObj.getORGAddrStateprov() );
    	m_BaseHelp.setFormData(oFrm, "prov", aHeadObj.getORGAddrOtherProvince() );
		m_BaseHelp.setFormData(oFrm,"postalcode", aHeadObj.getORGAddrPostalcode() );
		m_BaseHelp.setFormData(oFrm,"country", aHeadObj.getORGAddrCountryName() );
		m_BaseHelp.setFormData(oFrm,"county", aHeadObj.getORGAddrCounty() );
		m_BaseHelp.setFormData(oFrm,"orgcodekey", aHeadObj.getORGOrgcodekey() );
		m_BaseHelp.setFormData(oFrm,"orgprimaryperson", "" + aHeadObj.getORGPrimaryPerson() );
		m_BaseHelp.setFormData(oFrm,"orgmemberstatus", aHeadObj.getORGMembershipStatus() );
		m_BaseHelp.setFormData(oFrm,"orgmembertype", aHeadObj.getORGMembershipType() );
		m_BaseHelp.setFormData(oFrm,"phone1", aHeadObj.getORGOrgPhone1() );
		m_BaseHelp.setFormData(oFrm,"phone2", aHeadObj.getORGOrgPhone2() );
		m_BaseHelp.setFormData(oFrm,"fax1", aHeadObj.getORGFax1() );
		m_BaseHelp.setFormData(oFrm,"fax2", aHeadObj.getORGFax2() );
		m_BaseHelp.setFormData(oFrm,"orgwebpage", aHeadObj.getORGWebpage() );
		m_BaseHelp.setFormData(oFrm,"orgdonateurl", aHeadObj.getORGDonateURL() );
		m_BaseHelp.setFormData(oFrm,"orgfedtaxid", aHeadObj.getORGFedTaxId() );
		m_BaseHelp.setFormData(oFrm,"orgotherid", aHeadObj.getORGOtherId() );
		m_BaseHelp.setFormData(oFrm,"orgcomment", aHeadObj.getORGOrgComment() );
		m_BaseHelp.setFormData(oFrm,"orginternalcomment", aHeadObj.getORGInternalComment() );
		m_BaseHelp.setFormData(oFrm,"orgmissionstatement", aHeadObj.getORGMissionStatement() );
		m_BaseHelp.setFormData(oFrm,"orgdescription", aHeadObj.getORGOrgDescription() );
		m_BaseHelp.setFormData(oFrm,"orgnumvol",""+aHeadObj.getORGNumVolOrg() );
		m_BaseHelp.setFormData(oFrm,"orgnumvolintl",""+aHeadObj.getORGNumVolOrgIntl() );
		m_BaseHelp.setFormData(oFrm,"haslistings",aHeadObj.getORGHasListings() );
		m_BaseHelp.setFormData(oFrm,"listingsurl",aHeadObj.getORGListingsURL() );
		m_BaseHelp.setFormData(oFrm,"numopps",""+aHeadObj.getORGNumOppsOrg() );
		m_BaseHelp.setFormDataIntArray(oFrm,"positiontypetidsarray", aHeadObj.getPositionTypesArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"serviceareatidsarray", aHeadObj.getServiceAreasArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"skilltidsarray", aHeadObj.getSkillsArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"greatfortidsarray", aHeadObj.getGreatForArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"triplengthtidsarray", aHeadObj.getTripLengthArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"typesofoppstidsarray", aHeadObj.getTypesOfOppsArray() );
//		m_BaseHelp.setFormData(oFrm,"typesofoppsarray",""+aHeadObj.getTypesOfOppsArray() ); // ????????????????????????????????????????
		m_BaseHelp.setFormData(oFrm,"orgnumserved",""+aHeadObj.getORGNumServed() );
		m_BaseHelp.setFormData(oFrm,"orgformaltrain", aHeadObj.getORGFormalTrain() );
		m_BaseHelp.setFormData(oFrm,"orgstmtfaith", aHeadObj.getORGOrgStmtFaith() );
		m_BaseHelp.setFormData(oFrm,"orgchrismembertype", aHeadObj.getORGMembertype() );
		m_BaseHelp.setFormData(oFrm,"onethirdpov", aHeadObj.getORGOnethirdP() );
		m_BaseHelp.setFormData(oFrm,"is501c3p", aHeadObj.getORGIs501c3P() );
		m_BaseHelp.setFormData(oFrm,"programtypes", aHeadObj.getORGProgramTypes() );
		m_BaseHelp.setFormData(oFrm,"orgprogramtype1tid",""+aHeadObj.getORGProgramTypes1TID() );
		m_BaseHelp.setFormData(oFrm,"orgvolunteeropp", aHeadObj.getORGVolunteerOpportunityP() );
		m_BaseHelp.setFormData(oFrm,"orgemail", aHeadObj.getORGOrgContactEmail() );
		m_BaseHelp.setFormData(oFrm,"orgbudgetp", "" + aHeadObj.getORGOrganizationBudget() );
		m_BaseHelp.setFormData(oFrm,"numstafforgp", "" + aHeadObj.getORGNumStaffOrg() );
		m_BaseHelp.setFormData(oFrm,"orglangarabic", "" + aHeadObj.getORGLanguageArabic() );
		m_BaseHelp.setFormData(oFrm,"orglangchinese", "" + aHeadObj.getORGLanguageChinese() );
		m_BaseHelp.setFormData(oFrm,"orglangenglish", "" + aHeadObj.getORGLanguageEnglish() );
		m_BaseHelp.setFormData(oFrm,"orglangfrench", "" + aHeadObj.getORGLanguageFrench() );
		m_BaseHelp.setFormData(oFrm,"orglanghindi", "" + aHeadObj.getORGLanguageHindi() );
		m_BaseHelp.setFormData(oFrm,"orglangportuguese", "" + aHeadObj.getORGLanguagePortuguese() );
		m_BaseHelp.setFormData(oFrm,"orglangspanish", "" + aHeadObj.getORGLanguageSpanish() );
		m_BaseHelp.setFormData(oFrm,"orglangotheratxt", aHeadObj.getORGLanguageOtherAText() );
		m_BaseHelp.setFormData(oFrm,"orglangothera", "" + aHeadObj.getORGLanguageOtherA() );
		m_BaseHelp.setFormData(oFrm,"orglangotherbtxt", aHeadObj.getORGLanguageOtherBText() );
		m_BaseHelp.setFormData(oFrm,"orglangotherb", "" + aHeadObj.getORGLanguageOtherB() );
		m_BaseHelp.setFormData(oFrm,"demolowincomep", "" + aHeadObj.getORGDemoLowIncome() );
		m_BaseHelp.setFormData(oFrm,"demohomelessp", "" + aHeadObj.getORGDemoHomeless() );
		m_BaseHelp.setFormData(oFrm,"demodisabilityp", "" + aHeadObj.getORGDemoDisability() );
		m_BaseHelp.setFormData(oFrm,"demourbanp", "" + aHeadObj.getORGDemoUrban() );
		m_BaseHelp.setFormData(oFrm,"demoruralp", "" + aHeadObj.getORGDemoRural() );
		m_BaseHelp.setFormData(oFrm,"demoyouthp", "" + aHeadObj.getORGDemoYouth() );
		m_BaseHelp.setFormData(oFrm,"demoadultsp", "" + aHeadObj.getORGDemoAdults() );
		m_BaseHelp.setFormData(oFrm,"demoseniorsp", "" + aHeadObj.getORGDemoSeniors() );
		m_BaseHelp.setFormData(oFrm,"orggendermalep", "" + aHeadObj.getORGGenderMale() );
		m_BaseHelp.setFormData(oFrm,"orggenderfemalep", "" + aHeadObj.getORGGenderFemale() );
		m_BaseHelp.setFormData(oFrm,"numparticipantsp", "" + aHeadObj.getORGTechParticipants() );
		m_BaseHelp.setFormData(oFrm,"numcomputersp", "" + aHeadObj.getORGTechComputers() );
		m_BaseHelp.setFormData(oFrm,"numdonatep", "" + aHeadObj.getORGTechDonate() );
		m_BaseHelp.setFormData(oFrm,"techoutcomecomplet", "" + aHeadObj.getORGOutcomeCompletion() );
		m_BaseHelp.setFormData(oFrm,"techoutcomecert", "" + aHeadObj.getORGOutcomeCertification() );
		m_BaseHelp.setFormData(oFrm,"techoutcomecollege", "" + aHeadObj.getORGOutcomeCollege() );
		m_BaseHelp.setFormData(oFrm,"techoutcomejob", "" + aHeadObj.getORGOutcomeJob() );
		m_BaseHelp.setFormData(oFrm,"techoutcomeged", "" + aHeadObj.getORGOutcomeGed() );
		m_BaseHelp.setFormData(oFrm,"techoutcomediscipleship", "" + aHeadObj.getORGOutcomeDiscipleship() );
		m_BaseHelp.setFormData(oFrm,"techoutcomebeliever", "" + aHeadObj.getORGOutcomeBeliever() );
		m_BaseHelp.setFormData(oFrm,"orgraceaborna", "" + aHeadObj.getORGRaceAborna() );
		m_BaseHelp.setFormData(oFrm,"orgracearab", "" + aHeadObj.getORGRaceArab() );
		m_BaseHelp.setFormData(oFrm,"orgraceasian", "" + aHeadObj.getORGRaceAsian() );
		m_BaseHelp.setFormData(oFrm,"orgraceblack", "" + aHeadObj.getORGRaceBlack() );
		m_BaseHelp.setFormData(oFrm,"orgracecaucasian", "" + aHeadObj.getORGRaceCaucasian() );
		m_BaseHelp.setFormData(oFrm,"orgracelatino", "" + aHeadObj.getORGRaceLatino() );
		m_BaseHelp.setFormData(oFrm,"orgraceothertxt", aHeadObj.getORGRaceOtherText() );
		m_BaseHelp.setFormData(oFrm,"orgraceother", "" + aHeadObj.getORGRaceOther() );
		m_BaseHelp.setFormData(oFrm,"orgracepacificislander", "" + aHeadObj.getORGRacePacificIslander() );
		m_BaseHelp.setFormData(oFrm,"prov", aHeadObj.getORGAddrOtherProvince() );
		m_BaseHelp.setFormData(oFrm,"localaffil", aHeadObj.getORGLocalAffil() );
		m_BaseHelp.setFormData(oFrm,"subdomain", aHeadObj.getORGSubdom() );
		m_BaseHelp.setFormDataInt(oFrm,"site_interest", aHeadObj.getSiteInterest() ? 1 : 0);
		m_BaseHelp.setFormData(oFrm,"intlvols",""+aHeadObj.getORGTakesIntlVolsTID() );
		aszTemp = aHeadObj.getDataByPosition(aHeadObj.getORGServicesOffered(),';',1);
		m_BaseHelp.setFormData(oFrm,"orgservices1", aszTemp );
		aszTemp = aHeadObj.getDataByPosition(aHeadObj.getORGServicesOffered(),';',2);
		m_BaseHelp.setFormData(oFrm,"orgservices2", aszTemp );
		aszTemp = aHeadObj.getDataByPosition(aHeadObj.getORGServicesOffered(),';',3);
		m_BaseHelp.setFormData(oFrm,"orgservices3", aszTemp );

		m_BaseHelp.setFormData(oFrm,"portalbanner", aHeadObj.getPortalBannerURL() );
		
		m_BaseHelp.setFormData(oFrm,"portalheadertags", aHeadObj.getPortalHeaderTags() );
		m_BaseHelp.setFormData(oFrm,"portalheader", aHeadObj.getPortalHeader() );
		m_BaseHelp.setFormData(oFrm,"portalcss", aHeadObj.getPortalCSS() );
		m_BaseHelp.setFormData(oFrm,"portalfooter", aHeadObj.getPortalFooter() );
		m_BaseHelp.setFormData(oFrm,"hasportal", aHeadObj.getUsePortal() );
		
		String aszPortalName = "";
		int i=aszPortalName.length();
		aszPortalName = aHeadObj.getPortalNameModified();
		if(aHeadObj.getPortalNameModified().length()<1){
			aszPortalName = aHeadObj.getPortalNameOrig();
		}else{
			aszPortalName = aHeadObj.getPortalNameModified();
		}
			
		//m_BaseHelp.setFormData(oFrm,"portalname", aHeadObj.getPortalName() );
		m_BaseHelp.setFormData(oFrm,"portalname", aszPortalName );
		m_BaseHelp.setFormData(oFrm,"portaltid",""+aHeadObj.getPortalTID() );

		
		m_BaseHelp.setFormDataIntArray(oFrm,"orgaffiltidsarray", aHeadObj.getOrgAffiliationsArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"publicorgaffiltidsarray", aHeadObj.getOrgAffiliationsPublicArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"denomtidsarray", aHeadObj.getDenominationalAffilsArray() );
		m_BaseHelp.setFormDataIntArray(oFrm,"publicdenomtidsarray", aHeadObj.getDenomAffilsPublicArray() );
		
		m_BaseHelp.setFormData(oFrm,"orgpartner", aHeadObj.getORGPartner() );
		m_BaseHelp.setFormData(oFrm,"orgpartner2", aHeadObj.getORGPartner2() );
		m_BaseHelp.setFormData(oFrm,"orgpartner3", aHeadObj.getORGPartner3() );
		m_BaseHelp.setFormData(oFrm,"orgpartner4", aHeadObj.getORGPartner4() );
		m_BaseHelp.setFormData(oFrm,"orgpartner5", aHeadObj.getORGPartner5() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation1tid",""+aHeadObj.getORGAffiliation1TID() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation2tid",""+aHeadObj.getORGAffiliation2TID() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation3tid",""+aHeadObj.getORGAffiliation3TID() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation4tid",""+aHeadObj.getORGAffiliation4TID() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation5tid",""+aHeadObj.getORGAffiliation5TID() );
		m_BaseHelp.setFormData(oFrm,"orgaffiliation", aHeadObj.getORGAffiliation() );
		m_BaseHelp.setFormData(oFrm,"orgdenominationtid",""+aHeadObj.getORGDenomAffiliationTID() );

		return 0;
    }
    // end-of method fillOrgDataEditForm

    /**
	* get organization data from registration form
	*/
	public int getOrgDataFromForm1(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj){
		int iType=0;
		return getOrgDataFromForm1(oFrm, aHeadObj, iType);
	}
	public int getOrgDataFromForm1(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj, int iType){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		String aszTemp=null;
    	aHeadObj.setORGOrgName( m_BaseHelp.getFormData(oFrm,"orgname") );
    	
    	//stick the following in a condition; if there is a form element of name urlalias and it's not blank, make it that; otherwise, make it the orgname
    	aHeadObj.setORGUrlAlias( m_BaseHelp.getFormData(oFrm,"urlalias") );
    	if(aHeadObj.getORGUrlAlias().length()<1){
        	aHeadObj.setORGUrlAlias( m_BaseHelp.getFormData(oFrm,"orgname") );
    	}
    	
    	aHeadObj.setRequestType( m_BaseHelp.getFormData(oFrm,"requesttype") );
    	
    	aHeadObj.setSiteInterest(m_BaseHelp.getFormDataInt(oFrm, "site_interest"));
    	aHeadObj.setORGUID( m_BaseHelp.getFormData(oFrm,"orguid") );
    	aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
    	aHeadObj.setORGOrgNumber( m_BaseHelp.getFormData(oFrm,"orgnumber") );
    	aHeadObj.setORGAddrLine1( m_BaseHelp.getFormData(oFrm,"addr1") );
    	aHeadObj.setORGAddrLine2( m_BaseHelp.getFormData(oFrm,"addr2") );
		aHeadObj.setORGAddrLine3( m_BaseHelp.getFormData(oFrm,"addr3") );
    	aHeadObj.setORGAddrCity( m_BaseHelp.getFormData(oFrm,"city") );
    	aHeadObj.setORGAddrStateprov( m_BaseHelp.getFormData(oFrm,"state") );
    	aHeadObj.setORGAddrPostalcode( m_BaseHelp.getFormData(oFrm,"postalcode") );
    	aHeadObj.setORGAddrCountryName( m_BaseHelp.getFormData(oFrm,"country") );
		aHeadObj.setORGAddrCounty( m_BaseHelp.getFormData(oFrm,"county") );
		aHeadObj.setORGOrgPhone1( m_BaseHelp.getFormData(oFrm,"phone1") );
		aHeadObj.setORGOrgPhone2( m_BaseHelp.getFormData(oFrm,"phone2") );
		aHeadObj.setORGFax1( m_BaseHelp.getFormData(oFrm,"fax1") );
		aHeadObj.setORGFax2( m_BaseHelp.getFormData(oFrm,"fax2") );
		aHeadObj.setORGWebpage( m_BaseHelp.getFormData(oFrm,"orgwebpage") );
		aHeadObj.setORGDonateURL( m_BaseHelp.getFormData(oFrm,"orgdonateurl") );
		aHeadObj.setORGFedTaxId( m_BaseHelp.getFormData(oFrm, "ein_1"), m_BaseHelp.getFormData(oFrm, "ein_2") );
		aHeadObj.setORGOtherId( m_BaseHelp.getFormData(oFrm,"orgotherid") );
		aHeadObj.setORGOrgComment( m_BaseHelp.getFormData(oFrm,"orgcomment") );
		aHeadObj.setORGInternalComment( m_BaseHelp.getFormData(oFrm,"orginternalcomment") );
		aHeadObj.setORGMissionStatement( m_BaseHelp.getFormData(oFrm,"orgmissionstatement") );
		aHeadObj.setORGOrgDescription( m_BaseHelp.getFormData(oFrm,"orgdescription") );
		aHeadObj.setORGNumVolOrg( m_BaseHelp.getFormData(oFrm,"orgnumvol") );
		aHeadObj.setORGNumVolOrgIntl( m_BaseHelp.getFormData(oFrm,"orgnumvolintl") );
		aHeadObj.setORGHasListings( m_BaseHelp.getFormData(oFrm,"haslistings") );
		aHeadObj.setORGListingsURL( m_BaseHelp.getFormData(oFrm,"listingsurl") );
		aHeadObj.setORGNumOppsOrg( m_BaseHelp.getFormData(oFrm,"numopps") );
		aHeadObj.setPositionTypesArray( m_BaseHelp.getFormDataIntArray(oFrm,"positiontypetidsarray"));
		aHeadObj.setServiceAreasArray( m_BaseHelp.getFormDataIntArray(oFrm,"serviceareatidsarray"));
		aHeadObj.setSkillsArray( m_BaseHelp.getFormDataIntArray(oFrm,"skilltidsarray"));
		aHeadObj.setGreatForArray( m_BaseHelp.getFormDataIntArray(oFrm,"greatfortidsarray"));
		aHeadObj.setTripLengthArray( m_BaseHelp.getFormDataIntArray(oFrm,"triplengthtidsarray"));
		aHeadObj.setTypesOfOppsArray( m_BaseHelp.getFormDataIntArray(oFrm,"typesofoppstidsarray"));
		aHeadObj.setORGNumServed( m_BaseHelp.getFormData(oFrm,"orgnumserved") );
		aHeadObj.setORGFormalTrain( m_BaseHelp.getFormData(oFrm,"orgformaltrain") );
		aHeadObj.setORGOrgStmtFaith( m_BaseHelp.getFormData(oFrm,"orgstmtfaith") );
		aHeadObj.setORGMembertype( m_BaseHelp.getFormData(oFrm,"orgchrismembertype") );
		aHeadObj.setORGOnethirdP( m_BaseHelp.getFormData(oFrm,"onethirdpov") );
		aHeadObj.setORGIs501c3P( m_BaseHelp.getFormData(oFrm,"is501c3p") );
		aHeadObj.setORGProgramTypes( m_BaseHelp.getFormData(oFrm,"programtypes") );
		aHeadObj.setORGProgramTypes1TID( m_BaseHelp.getFormData(oFrm,"orgprogramtype1tid") );
		aHeadObj.setORGVolunteerOpportunityP( m_BaseHelp.getFormData(oFrm,"orgvolunteeropp") );
		aHeadObj.setORGOrgContactEmail( m_BaseHelp.getFormData(oFrm,"orgemail") );
		aHeadObj.setORGOrganizationBudget( m_BaseHelp.getFormData(oFrm,"orgbudgetp") );
		aHeadObj.setORGNumStaffOrg( m_BaseHelp.getFormData(oFrm,"numstafforgp") );
		aHeadObj.setORGLanguageArabic( m_BaseHelp.getFormData(oFrm,"orglangarabic") );
		aHeadObj.setORGLanguageChinese( m_BaseHelp.getFormData(oFrm,"orglangchinese") );
		aHeadObj.setORGLanguageEnglish( m_BaseHelp.getFormData(oFrm,"orglangenglish") );
		aHeadObj.setORGLanguageFrench( m_BaseHelp.getFormData(oFrm,"orglangfrench") );
		aHeadObj.setORGLanguageHindi( m_BaseHelp.getFormData(oFrm,"orglanghindi") );
		aHeadObj.setORGLanguagePortuguese( m_BaseHelp.getFormData(oFrm,"orglangportuguese") );
		aHeadObj.setORGLanguageSpanish( m_BaseHelp.getFormData(oFrm,"orglangspanish") );
		aHeadObj.setORGLanguageOtherAText( m_BaseHelp.getFormData(oFrm,"orglangotheratxt") );
		aHeadObj.setORGLanguageOtherA( m_BaseHelp.getFormData(oFrm,"orglangothera") );
		aHeadObj.setORGLanguageOtherBText( m_BaseHelp.getFormData(oFrm,"orglangotherbtxt") );
		aHeadObj.setORGLanguageOtherB( m_BaseHelp.getFormData(oFrm,"orglangotherb") );
		aHeadObj.setORGDemoLowIncome( m_BaseHelp.getFormData(oFrm,"demolowincomep") );
		aHeadObj.setORGDemoHomeless( m_BaseHelp.getFormData(oFrm,"demohomelessp") );
		aHeadObj.setORGDemoDisability( m_BaseHelp.getFormData(oFrm,"demodisabilityp") );
		aHeadObj.setORGDemoUrban( m_BaseHelp.getFormData(oFrm,"demourbanp") );
		aHeadObj.setORGDemoRural( m_BaseHelp.getFormData(oFrm,"demoruralp") );
		aHeadObj.setORGDemoYouth( m_BaseHelp.getFormData(oFrm,"demoyouthp") );
		aHeadObj.setORGDemoAdults( m_BaseHelp.getFormData(oFrm,"demoadultsp") );
		aHeadObj.setORGDemoSeniors( m_BaseHelp.getFormData(oFrm,"demoseniorsp") );
		aHeadObj.setORGGenderMale( m_BaseHelp.getFormData(oFrm,"orggendermalep") );
		aHeadObj.setORGGenderFemale( m_BaseHelp.getFormData(oFrm,"orggenderfemalep") );
		aHeadObj.setORGTechParticipants( m_BaseHelp.getFormData(oFrm,"numparticipantsp") );
		aHeadObj.setORGTechComputers( m_BaseHelp.getFormData(oFrm,"numcomputersp") );
		aHeadObj.setORGTechDonate( m_BaseHelp.getFormData(oFrm,"numdonatep") );
		aHeadObj.setORGOutcomeCompletion( m_BaseHelp.getFormData(oFrm,"techoutcomecomplet") );
		aHeadObj.setORGOutcomeCertification( m_BaseHelp.getFormData(oFrm,"techoutcomecert") );
		aHeadObj.setORGOutcomeCollege( m_BaseHelp.getFormData(oFrm,"techoutcomecollege") );
		aHeadObj.setORGOutcomeJob( m_BaseHelp.getFormData(oFrm,"techoutcomejob") );
		aHeadObj.setORGOutcomeGed( m_BaseHelp.getFormData(oFrm,"techoutcomeged") );
		aHeadObj.setORGOutcomeDiscipleship( m_BaseHelp.getFormData(oFrm,"techoutcomediscipleship") );
		aHeadObj.setORGOutcomeBeliever( m_BaseHelp.getFormData(oFrm,"techoutcomebeliever") );
		aHeadObj.setORGRaceAborna( m_BaseHelp.getFormData(oFrm,"orgraceaborna") );
		aHeadObj.setORGRaceArab( m_BaseHelp.getFormData(oFrm,"orgracearab") );
		aHeadObj.setORGRaceAsian( m_BaseHelp.getFormData(oFrm,"orgraceasian") );
		aHeadObj.setORGRaceBlack( m_BaseHelp.getFormData(oFrm,"orgraceblack") );
		aHeadObj.setORGRaceCaucasian( m_BaseHelp.getFormData(oFrm,"orgracecaucasian") );
		aHeadObj.setORGRaceLatino( m_BaseHelp.getFormData(oFrm,"orgracelatino") );
		aHeadObj.setORGRaceOtherText( m_BaseHelp.getFormData(oFrm,"orgraceothertxt") );
		aHeadObj.setORGRaceOther( m_BaseHelp.getFormData(oFrm,"orgraceother") );
		aHeadObj.setORGRacePacificIslander( m_BaseHelp.getFormData(oFrm,"orgracepacificislander") );
		aHeadObj.setORGAddrOtherProvince( m_BaseHelp.getFormData(oFrm,"prov") );
		aHeadObj.setORGLocalAffil( m_BaseHelp.getFormData(oFrm,"localaffil") );
		aHeadObj.setORGSubdom( m_BaseHelp.getFormData(oFrm,"subdomain") );
		aHeadObj.setORGTakesIntlVolsTID( m_BaseHelp.getFormData(oFrm,"intlvols") );
		
		if(iType==0){
//			aHeadObj.setPortalBannerURL( m_BaseHelp.getFormData(oFrm,"porterbanner") );
//			aHeadObj.setPortalName( m_BaseHelp.getFormData(oFrm,"portalname") );
			aHeadObj.setPortalNameModified( m_BaseHelp.getFormData(oFrm,"portalname") );
			aHeadObj.setPortalHeader( m_BaseHelp.getFormData(oFrm,"portalheader") );
			aHeadObj.setPortalHeaderTags( m_BaseHelp.getFormData(oFrm,"portalheadertags") );
			aHeadObj.setPortalCSS( m_BaseHelp.getFormData(oFrm,"portalcss") );
			aHeadObj.setPortalFooter( m_BaseHelp.getFormData(oFrm,"portalfooter") );
//			aHeadObj.setPortalTID( m_BaseHelp.getFormData(oFrm,"portaltid") );
		}
		aHeadObj.setUsePortal( m_BaseHelp.getFormData(oFrm,"hasportal") );
		
		String aszServices="";
		aszTemp = m_BaseHelp.getFormData(oFrm,"orgservices1");
		if(aszTemp.length() > 2){
			aszServices = aszServices + aszTemp + ";" ;
		}
		aszTemp = m_BaseHelp.getFormData(oFrm,"orgservices2");
		if(aszTemp.length() > 2){
			aszServices = aszServices + aszTemp + ";" ;
		}
		aszTemp = m_BaseHelp.getFormData(oFrm,"orgservices3");
		if(aszTemp.length() > 2){
			aszServices = aszServices + aszTemp + ";" ;
		}
		aHeadObj.setORGServicesOffered( aszServices );

		
//		aHeadObj.setORGAdminUIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgadminuidsmodifiedarray"));
		aHeadObj.setORGAdminUIDsModifiedArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgadminuidsarray"));
//		aHeadObj.setORGContactUIDsArray( m_BaseHelp.getFormDataIntArray(oFrm,"oppcontactuidsarray"));
		aHeadObj.setORGContactUIDsModifiedArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgcontactuidsarray"));
		
		aHeadObj.setOrgAffiliationsArray( m_BaseHelp.getFormDataIntArray(oFrm,"orgaffiltidsarray"));
		aHeadObj.setOrgAffiliationsPublicArray( m_BaseHelp.getFormDataIntArray(oFrm,"publicorgaffiltidsarray"));
		aHeadObj.setDenominationalAffilsArray( m_BaseHelp.getFormDataIntArray(oFrm,"denomtidsarray"));
		aHeadObj.setDenomAffilsPublicArray( m_BaseHelp.getFormDataIntArray(oFrm,"publicdenomtidsarray"));

		aHeadObj.setORGPartner( m_BaseHelp.getFormData(oFrm,"orgpartner") );
		aHeadObj.setORGPartner2( m_BaseHelp.getFormData(oFrm,"orgpartner2") );
		aHeadObj.setORGPartner3( m_BaseHelp.getFormData(oFrm,"orgpartner3") );
		aHeadObj.setORGPartner4( m_BaseHelp.getFormData(oFrm,"orgpartner4") );
		aHeadObj.setORGPartner5( m_BaseHelp.getFormData(oFrm,"orgpartner5") );
		aHeadObj.setORGAffiliation1TID( m_BaseHelp.getFormData(oFrm,"orgaffiliation1tid") );
		aHeadObj.setORGAffiliation2TID( m_BaseHelp.getFormData(oFrm,"orgaffiliation2tid") );
		aHeadObj.setORGAffiliation3TID( m_BaseHelp.getFormData(oFrm,"orgaffiliation3tid") );
		aHeadObj.setORGAffiliation4TID( m_BaseHelp.getFormData(oFrm,"orgaffiliation4tid") );
		aHeadObj.setORGAffiliation5TID( m_BaseHelp.getFormData(oFrm,"orgaffiliation5tid") );
		aHeadObj.setORGAffiliation( m_BaseHelp.getFormData(oFrm,"orgaffiliation") );
		aHeadObj.setORGDenomAffiliationTID( m_BaseHelp.getFormData(oFrm,"orgdenominationtid") );
		
		return 0;
    }
    // end-of method getOrgDataFromForm1()


    /**
	* get organization data from registration form getPortalDataFromForm1
	*/
	public int getPortalDataFromForm1(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj){
		int iType=0;
		return getPortalDataFromForm1( oFrm,  aHeadObj, iType);
	}
	public int getPortalDataFromForm1(DynaActionForm oFrm, OrganizationInfoDTO aHeadObj, int iType){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		String aszTemp=null;
		if(iType != OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
	    	aHeadObj.setORGUID( m_BaseHelp.getFormData(oFrm,"orguid") );
	    	aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		}
		
//		aHeadObj.setPortalBannerURL( m_BaseHelp.getFormData(oFrm,"porterbanner") );
//		aHeadObj.setPortalName( m_BaseHelp.getFormData(oFrm,"portalname") );
		aHeadObj.setPortalNameModified( m_BaseHelp.getFormData(oFrm,"portalname") );
		aHeadObj.setPortalHeaderTags( m_BaseHelp.getFormData(oFrm,"portalheadertags") );
		aHeadObj.setPortalHeader( m_BaseHelp.getFormData(oFrm,"portalheader") );
		aHeadObj.setPortalCSS( m_BaseHelp.getFormData(oFrm,"portalcss") );
		aHeadObj.setPortalFooter( m_BaseHelp.getFormData(oFrm,"portalfooter") );
//		aHeadObj.setPortalTID( m_BaseHelp.getFormData(oFrm,"portaltid") );
		aHeadObj.setUsePortal( m_BaseHelp.getFormData(oFrm,"hasportal") );
		
		return 0;
    }
    // end-of method getPortalDataFromForm1()


    //=== START Table org_details ===>
    //=== START Table org_details ===>
    //=== START Table org_details ===>
	
	/**
	* fill organization data into form
	*/
	public int fillOrgDetailDataEditForm(DynaActionForm oFrm, OrganizationDetailsInfoDTO aHeadObj){
		String aszTemp=null;
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "errormsg", aHeadObj.getErrorMsg() );
		m_BaseHelp.setFormData(oFrm,"detorgnumber", "" + aHeadObj.getDETOrgNumber() );
		m_BaseHelp.setFormData(oFrm,"detorgcodekey", aHeadObj.getDETOrgcodekey() );
		m_BaseHelp.setFormData(oFrm,"detprimaryperson", "" + aHeadObj.getDETPrimaryPerson() );
		m_BaseHelp.setFormData(oFrm,"detupdateid", "" + aHeadObj.getDETUpdateId() );
		m_BaseHelp.setFormData(oFrm,"detfedtaxid", aHeadObj.getDETFedTaxId() );
		m_BaseHelp.setFormData(oFrm,"orgnid", "" + aHeadObj.getDETOrgNID() );
		//m_BaseHelp.setFormData(oFrm,"orgfedtaxid", aHeadObj.getDETFedTaxId() );
		m_BaseHelp.setFormData(oFrm,"numvolorg", "" + aHeadObj.getDETNumVolOrg() );
		m_BaseHelp.setFormData(oFrm,"numserved", "" + aHeadObj.getDETNumServed() );
		m_BaseHelp.setFormData(oFrm,"is501c3", aHeadObj.getDETIs501c3P() );
		m_BaseHelp.setFormData(oFrm,"orgbudget", "" + aHeadObj.getDETOrganizationBudget() );
		m_BaseHelp.setFormData(oFrm,"numstafforg", "" + aHeadObj.getDETNumStaffOrg() );
		m_BaseHelp.setFormData(oFrm,"langrabic", "" + aHeadObj.getDETLanguageArabic() );
		m_BaseHelp.setFormData(oFrm,"langchinese", "" + aHeadObj.getDETLanguageChinese() );
		m_BaseHelp.setFormData(oFrm,"langenglish", "" + aHeadObj.getDETLanguageEnglish() );
		m_BaseHelp.setFormData(oFrm,"langfrench", "" + aHeadObj.getDETLanguageFrench() );
		m_BaseHelp.setFormData(oFrm,"langhindi", "" + aHeadObj.getDETLanguageHindi() );
		m_BaseHelp.setFormData(oFrm,"langportuguese", "" + aHeadObj.getDETLanguagePortuguese() );
		m_BaseHelp.setFormData(oFrm,"langspanish", "" + aHeadObj.getDETLanguageSpanish() );
		m_BaseHelp.setFormData(oFrm,"langotheratxt", aHeadObj.getDETLanguageOtherAText() );
		m_BaseHelp.setFormData(oFrm,"langothera", "" + aHeadObj.getDETLanguageOtherA() );
		m_BaseHelp.setFormData(oFrm,"langotherbtxt", aHeadObj.getDETLanguageOtherBText() );
		m_BaseHelp.setFormData(oFrm,"langotherb", "" + aHeadObj.getDETLanguageOtherB() );
		m_BaseHelp.setFormData(oFrm,"raceasian", "" + aHeadObj.getDETRaceAsian() );
		m_BaseHelp.setFormData(oFrm,"raceblack", "" + aHeadObj.getDETRaceBlack() );
		m_BaseHelp.setFormData(oFrm,"racecaucasian", "" + aHeadObj.getDETRaceCaucasian() );
		m_BaseHelp.setFormData(oFrm,"racelatino", "" + aHeadObj.getDETRaceLatino() );
		m_BaseHelp.setFormData(oFrm,"racepacificislander", "" + aHeadObj.getDETRacePacificIslander() );
		m_BaseHelp.setFormData(oFrm,"racenativeamerican", "" + aHeadObj.getDETRaceNativeAmerican() );
		m_BaseHelp.setFormData(oFrm,"raceothertext", aHeadObj.getDETRaceOtherText() );
		m_BaseHelp.setFormData(oFrm,"raceother", "" + aHeadObj.getDETRaceOther() );
		m_BaseHelp.setFormData(oFrm,"demolowincome", "" + aHeadObj.getDETDemoLowIncome() );
		m_BaseHelp.setFormData(oFrm,"demohomeless", "" + aHeadObj.getDETDemoHomeless() );
		m_BaseHelp.setFormData(oFrm,"demodisability", "" + aHeadObj.getDETDemoDisability() );
		m_BaseHelp.setFormData(oFrm,"demourban", "" + aHeadObj.getDETDemoUrban() );
		m_BaseHelp.setFormData(oFrm,"demorural", "" + aHeadObj.getDETDemoRural() );
		m_BaseHelp.setFormData(oFrm,"demoyouth", "" + aHeadObj.getDETDemoYouth() );
		m_BaseHelp.setFormData(oFrm,"demoadults", "" + aHeadObj.getDETDemoAdults() );
		m_BaseHelp.setFormData(oFrm,"demoseniors", "" + aHeadObj.getDETDemoSeniors() );
		m_BaseHelp.setFormData(oFrm,"orggendermale", "" + aHeadObj.getDETGenderMale() );
		m_BaseHelp.setFormData(oFrm,"orggenderfemale", "" + aHeadObj.getDETGenderFemale() );
		m_BaseHelp.setFormData(oFrm,"progtypcompctr", "" + aHeadObj.getDETProgtypeCompCenter() );
		m_BaseHelp.setFormData(oFrm,"progtypedemploy", "" + aHeadObj.getDETProgtypeEdEmploy() );
		m_BaseHelp.setFormData(oFrm,"progtypfood", "" + aHeadObj.getDETProgtypeFood() );
		m_BaseHelp.setFormData(oFrm,"progtyphealth", "" + aHeadObj.getDETProgtypeHealth() );
		m_BaseHelp.setFormData(oFrm,"progtyphomeless", "" + aHeadObj.getDETProgtypeHomeless() );
		m_BaseHelp.setFormData(oFrm,"progtyphousing", "" + aHeadObj.getDETProgtypeHousing() );
		m_BaseHelp.setFormData(oFrm,"progtypimmigration", "" + aHeadObj.getDETProgtypeImmigration() );
		m_BaseHelp.setFormData(oFrm,"progtypother", "" + aHeadObj.getDETProgtypeOther() );
		m_BaseHelp.setFormData(oFrm,"progtyprehab", "" + aHeadObj.getDETProgtypeRehab() );
		m_BaseHelp.setFormData(oFrm,"progtypseniors", "" + aHeadObj.getDETProgtypeSeniors() );
		m_BaseHelp.setFormData(oFrm,"progtypspiritdevel", "" + aHeadObj.getDETProgtypeSpiritDevel() );
		m_BaseHelp.setFormData(oFrm,"progtypyouth", "" + aHeadObj.getDETProgtypeYouth() );
		m_BaseHelp.setFormData(oFrm,"affilagrm", "" + aHeadObj.getDETAffilAgrm() );
		m_BaseHelp.setFormData(oFrm,"affilbilgramevas", "" + aHeadObj.getDETAffilBilgramevas() );
		m_BaseHelp.setFormData(oFrm,"affilbreadworld", "" + aHeadObj.getDETAffilBreadworld() );
		m_BaseHelp.setFormData(oFrm,"affilcallren", "" + aHeadObj.getDETAffilCallren() );
		m_BaseHelp.setFormData(oFrm,"affilcampcrus", "" + aHeadObj.getDETAffilCampcrus() );
		m_BaseHelp.setFormData(oFrm,"affilcathcharities", "" + aHeadObj.getDETAffilCathcharities() );
		m_BaseHelp.setFormData(oFrm,"affilcathcharitiesUs", "" + aHeadObj.getDETAffilCathcharitiesUs() );
		m_BaseHelp.setFormData(oFrm,"affilcathmedmiss", "" + aHeadObj.getDETAffilCathmedmiss() );
		m_BaseHelp.setFormData(oFrm,"affilcathrelserv", "" + aHeadObj.getDETAffilCathrelserv() );
		m_BaseHelp.setFormData(oFrm,"affilchrisaid", "" + aHeadObj.getDETAffilChrisaid() );
		m_BaseHelp.setFormData(oFrm,"affilchrischildfnd", "" + aHeadObj.getDETAffilChrischildfnd() );
		m_BaseHelp.setFormData(oFrm,"affilccda", "" + aHeadObj.getDETAffilCcda() );
		m_BaseHelp.setFormData(oFrm,"affilchrislegsoc", "" + aHeadObj.getDETAffilChrislegsoc() );
		m_BaseHelp.setFormData(oFrm,"affilchrisrefna", "" + aHeadObj.getDETAffilChrisrefna() );
		m_BaseHelp.setFormData(oFrm,"affilctcnet", "" + aHeadObj.getDETAffilCtcnet() );
		m_BaseHelp.setFormData(oFrm,"affilcompassionint", "" + aHeadObj.getDETAffilCompassionint() );
		m_BaseHelp.setFormData(oFrm,"affilcompassionworks", "" + aHeadObj.getDETAffilCompassionworks() );
		m_BaseHelp.setFormData(oFrm,"affilcoopbapfel", "" + aHeadObj.getDETAffilCoopbapfel() );
		m_BaseHelp.setFormData(oFrm,"affildevos", "" + aHeadObj.getDETAffilDevos() );
		m_BaseHelp.setFormData(oFrm,"affilevassocpromed", "" + aHeadObj.getDETAffilEvassocpromed() );
		m_BaseHelp.setFormData(oFrm,"affilevcovchurch", "" + aHeadObj.getDETAffilEvcovchurch() );
		m_BaseHelp.setFormData(oFrm,"affilevluthchamer", "" + aHeadObj.getDETAffilEvluthchamer() );
		m_BaseHelp.setFormData(oFrm,"affilevsocialaction", "" + aHeadObj.getDETAffilEvsocialaction() );
		m_BaseHelp.setFormData(oFrm,"affilfeedchild", "" + aHeadObj.getDETAffilFeedchild() );
		m_BaseHelp.setFormData(oFrm,"affilfocusonfam", "" + aHeadObj.getDETAffilFocusonfam() );
		m_BaseHelp.setFormData(oFrm,"affilfoodforpoor", "" + aHeadObj.getDETAffilFoodforpoor() );
		m_BaseHelp.setFormData(oFrm,"affilhabitathum", "" + aHeadObj.getDETAffilHabitathum() );
		m_BaseHelp.setFormData(oFrm,"affilhandsonnet", "" + aHeadObj.getDETAffilHandsonnet() );
		m_BaseHelp.setFormData(oFrm,"affilhereslifeinncity", "" + aHeadObj.getDETAffilHereslifeinncity() );
		m_BaseHelp.setFormData(oFrm,"affilhud", "" + aHeadObj.getDETAffilHud() );
		m_BaseHelp.setFormData(oFrm,"affilleaderfoundamer", "" + aHeadObj.getDETAffilLeaderfoundamer() );
		m_BaseHelp.setFormData(oFrm,"affilmapint", "" + aHeadObj.getDETAffilMapint() );
		m_BaseHelp.setFormData(oFrm,"affilmennoncentral", "" + aHeadObj.getDETAffilMennoncentral() );
		m_BaseHelp.setFormData(oFrm,"affilreformedamer", "" + aHeadObj.getDETAffilReformedamer() );
		m_BaseHelp.setFormData(oFrm,"affilsalvarmy", "" + aHeadObj.getDETAffilSalvarmy() );
		m_BaseHelp.setFormData(oFrm,"affilsamaritanpurse", "" + aHeadObj.getDETAffilSamaritanpurse() );
		m_BaseHelp.setFormData(oFrm,"affilchrismissa", "" + aHeadObj.getDETAffilChrismissa() );
		m_BaseHelp.setFormData(oFrm,"affiluywi", "" + aHeadObj.getDETAffilUywi() );
		m_BaseHelp.setFormData(oFrm,"affilvolamer", "" + aHeadObj.getDETAffilVolamer() );
		m_BaseHelp.setFormData(oFrm,"affilworldv", "" + aHeadObj.getDETAffilWorldv() );
		m_BaseHelp.setFormData(oFrm,"affilwycliffebib", "" + aHeadObj.getDETAffilWycliffebib() );
		m_BaseHelp.setFormData(oFrm,"affilymcausa", "" + aHeadObj.getDETAffilYmcausa() );
		m_BaseHelp.setFormData(oFrm,"affilymcasus", "" + aHeadObj.getDETAffilYmcasus() );
		m_BaseHelp.setFormData(oFrm,"affilyounglife", "" + aHeadObj.getDETAffilYounglife() );
		m_BaseHelp.setFormData(oFrm,"numparticipants", "" + aHeadObj.getDETTechParticipants() );
		m_BaseHelp.setFormData(oFrm,"numcomputers", "" + aHeadObj.getDETTechComputers() );
		m_BaseHelp.setFormData(oFrm,"numdonate", "" + aHeadObj.getDETTechDonate() );
		m_BaseHelp.setFormData(oFrm,"outcomecompl", "" + aHeadObj.getDETOutcomeCompletion() );
		m_BaseHelp.setFormData(oFrm,"outcomecert", "" + aHeadObj.getDETOutcomeCertification() );
		m_BaseHelp.setFormData(oFrm,"outcomecoll", "" + aHeadObj.getDETOutcomeCollege() );
		m_BaseHelp.setFormData(oFrm,"outcomejob", "" + aHeadObj.getDETOutcomeJob() );
		m_BaseHelp.setFormData(oFrm,"outcomeged", "" + aHeadObj.getDETOutcomeGed() );
		m_BaseHelp.setFormData(oFrm,"outcomediscipleship", "" + aHeadObj.getDETOutcomeDiscipleship() );
		m_BaseHelp.setFormData(oFrm,"outcomebeliever", "" + aHeadObj.getDETOutcomeBeliever() );
    	return 0;
    }
    // end-of method fillOrgDataEditForm

    /**
	* get organization data from registration form
	*/
	public int getOrgDetailDataFromForm1(DynaActionForm oFrm, OrganizationDetailsInfoDTO aHeadObj){
		int iRetCode=0;
		String aszTemp=null;
		/*
		Date aTempDateObj=null;
		DateUtil aDateObj = new DateUtil();
		*/
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		//aHeadObj.setDETOrgNumber( m_BaseHelp.getFormData(oFrm,"detorgnumber") );
		//aHeadObj.setDETOrgcodekey( m_BaseHelp.getFormData(oFrm,"detorgcodekey") );
		//aHeadObj.setDETPrimaryPerson( m_BaseHelp.getFormData(oFrm,"detprimaryperson") );
		/*
		aszTemp = m_BaseHelp.getFormData(oFrm,"DETUpdateDt");
		aTempDateObj = aDateObj.convertToDate( aszTemp );
		if(null != aTempDateObj){
			aHeadObj.setDETUpdateDt( aTempDateObj );
		} else {
			aHeadObj.setDETUpdateDt( null );
		}
		*/
		//aHeadObj.setDETUpdateId( m_BaseHelp.getFormData(oFrm,"detupdateid") );
		aHeadObj.setDETFedTaxId( m_BaseHelp.getFormData(oFrm, "ein_1"), m_BaseHelp.getFormData(oFrm, "ein_2") );
		aHeadObj.setDETNumVolOrg( m_BaseHelp.getFormData(oFrm,"numvolorg") );
		aHeadObj.setDETNumServed( m_BaseHelp.getFormData(oFrm,"numserved") );
		aHeadObj.setDETIs501c3P( m_BaseHelp.getFormData(oFrm,"is501c3") );
		aHeadObj.setDETOrganizationBudget( m_BaseHelp.getFormData(oFrm,"orgbudget") );
		aHeadObj.setDETNumStaffOrg( m_BaseHelp.getFormData(oFrm,"numstafforg") );
		aHeadObj.setDETLanguageArabic( m_BaseHelp.getFormData(oFrm,"langrabic") );
		aHeadObj.setDETLanguageChinese( m_BaseHelp.getFormData(oFrm,"langchinese") );
		aHeadObj.setDETLanguageEnglish( m_BaseHelp.getFormData(oFrm,"langenglish") );
		aHeadObj.setDETLanguageFrench( m_BaseHelp.getFormData(oFrm,"langfrench") );
		aHeadObj.setDETLanguageHindi( m_BaseHelp.getFormData(oFrm,"langhindi") );
		aHeadObj.setDETLanguagePortuguese( m_BaseHelp.getFormData(oFrm,"langportuguese") );
		aHeadObj.setDETLanguageSpanish( m_BaseHelp.getFormData(oFrm,"langspanish") );
		aHeadObj.setDETLanguageOtherAText( m_BaseHelp.getFormData(oFrm,"langotheratxt") );
		aHeadObj.setDETLanguageOtherA( m_BaseHelp.getFormData(oFrm,"langothera") );
		aHeadObj.setDETLanguageOtherBText( m_BaseHelp.getFormData(oFrm,"langotherbtxt") );
		aHeadObj.setDETLanguageOtherB( m_BaseHelp.getFormData(oFrm,"langotherb") );
		aHeadObj.setDETRaceAsian( m_BaseHelp.getFormData(oFrm,"raceasian") );
		aHeadObj.setDETRaceBlack( m_BaseHelp.getFormData(oFrm,"raceblack") );
		aHeadObj.setDETRaceCaucasian( m_BaseHelp.getFormData(oFrm,"racecaucasian") );
		aHeadObj.setDETRaceLatino( m_BaseHelp.getFormData(oFrm,"racelatino") );
		aHeadObj.setDETRacePacificIslander( m_BaseHelp.getFormData(oFrm,"racepacificislander") );
		aHeadObj.setDETRaceNativeAmerican( m_BaseHelp.getFormData(oFrm,"racenativeamerican") );
		aHeadObj.setDETRaceOtherText( m_BaseHelp.getFormData(oFrm,"raceothertext") );
		aHeadObj.setDETRaceOther( m_BaseHelp.getFormData(oFrm,"raceother") );
		aHeadObj.setDETDemoLowIncome( m_BaseHelp.getFormData(oFrm,"demolowincome") );
		aHeadObj.setDETDemoHomeless( m_BaseHelp.getFormData(oFrm,"demohomeless") );
		aHeadObj.setDETDemoDisability( m_BaseHelp.getFormData(oFrm,"demodisability") );
		aHeadObj.setDETDemoUrban( m_BaseHelp.getFormData(oFrm,"demourban") );
		aHeadObj.setDETDemoRural( m_BaseHelp.getFormData(oFrm,"demorural") );
		aHeadObj.setDETDemoYouth( m_BaseHelp.getFormData(oFrm,"demoyouth") );
		aHeadObj.setDETDemoAdults( m_BaseHelp.getFormData(oFrm,"demoadults") );
		aHeadObj.setDETDemoSeniors( m_BaseHelp.getFormData(oFrm,"demoseniors") );
		aHeadObj.setDETGenderMale( m_BaseHelp.getFormData(oFrm,"orggendermale") );
		aHeadObj.setDETGenderFemale( m_BaseHelp.getFormData(oFrm,"orggenderfemale") );
		aHeadObj.setDETProgtypeCompCenter( m_BaseHelp.getFormData(oFrm,"progtypcompctr") );
		aHeadObj.setDETProgtypeEdEmploy( m_BaseHelp.getFormData(oFrm,"progtypedemploy") );
		aHeadObj.setDETProgtypeFood( m_BaseHelp.getFormData(oFrm,"progtypfood") );
		aHeadObj.setDETProgtypeHealth( m_BaseHelp.getFormData(oFrm,"progtyphealth") );
		aHeadObj.setDETProgtypeHomeless( m_BaseHelp.getFormData(oFrm,"progtyphomeless") );
		aHeadObj.setDETProgtypeHousing( m_BaseHelp.getFormData(oFrm,"progtyphousing") );
		aHeadObj.setDETProgtypeImmigration( m_BaseHelp.getFormData(oFrm,"progtypimmigration") );
		aHeadObj.setDETProgtypeOther( m_BaseHelp.getFormData(oFrm,"progtypother") );
		aHeadObj.setDETProgtypeRehab( m_BaseHelp.getFormData(oFrm,"progtyprehab") );
		aHeadObj.setDETProgtypeSeniors( m_BaseHelp.getFormData(oFrm,"progtypseniors") );
		aHeadObj.setDETProgtypeSpiritDevel( m_BaseHelp.getFormData(oFrm,"progtypspiritdevel") );
		aHeadObj.setDETProgtypeYouth( m_BaseHelp.getFormData(oFrm,"progtypyouth") );
		aHeadObj.setDETAffilAgrm( m_BaseHelp.getFormData(oFrm,"affilagrm") );
		aHeadObj.setDETAffilBilgramevas( m_BaseHelp.getFormData(oFrm,"affilbilgramevas") );
		aHeadObj.setDETAffilBreadworld( m_BaseHelp.getFormData(oFrm,"affilbreadworld") );
		aHeadObj.setDETAffilCallren( m_BaseHelp.getFormData(oFrm,"affilcallren") );
		aHeadObj.setDETAffilCampcrus( m_BaseHelp.getFormData(oFrm,"affilcampcrus") );
		aHeadObj.setDETAffilCathcharities( m_BaseHelp.getFormData(oFrm,"affilcathcharities") );
		aHeadObj.setDETAffilCathcharitiesUs( m_BaseHelp.getFormData(oFrm,"affilcathcharitiesUs") );
		aHeadObj.setDETAffilCathmedmiss( m_BaseHelp.getFormData(oFrm,"affilcathmedmiss") );
		aHeadObj.setDETAffilCathrelserv( m_BaseHelp.getFormData(oFrm,"affilcathrelserv") );
		aHeadObj.setDETAffilChrisaid( m_BaseHelp.getFormData(oFrm,"affilchrisaid") );
		aHeadObj.setDETAffilChrischildfnd( m_BaseHelp.getFormData(oFrm,"affilchrischildfnd") );
		aHeadObj.setDETAffilCcda( m_BaseHelp.getFormData(oFrm,"affilccda") );
		aHeadObj.setDETAffilChrislegsoc( m_BaseHelp.getFormData(oFrm,"affilchrislegsoc") );
		aHeadObj.setDETAffilChrisrefna( m_BaseHelp.getFormData(oFrm,"affilchrisrefna") );
		aHeadObj.setDETAffilCtcnet( m_BaseHelp.getFormData(oFrm,"affilctcnet") );
		aHeadObj.setDETAffilCompassionint( m_BaseHelp.getFormData(oFrm,"affilcompassionint") );
		aHeadObj.setDETAffilCompassionworks( m_BaseHelp.getFormData(oFrm,"affilcompassionworks") );
		aHeadObj.setDETAffilCoopbapfel( m_BaseHelp.getFormData(oFrm,"affilcoopbapfel") );
		aHeadObj.setDETAffilDevos( m_BaseHelp.getFormData(oFrm,"affildevos") );
		aHeadObj.setDETAffilEvassocpromed( m_BaseHelp.getFormData(oFrm,"affilevassocpromed") );
		aHeadObj.setDETAffilEvcovchurch( m_BaseHelp.getFormData(oFrm,"affilevcovchurch") );
		aHeadObj.setDETAffilEvluthchamer( m_BaseHelp.getFormData(oFrm,"affilevluthchamer") );
		aHeadObj.setDETAffilEvsocialaction( m_BaseHelp.getFormData(oFrm,"affilevsocialaction") );
		aHeadObj.setDETAffilFeedchild( m_BaseHelp.getFormData(oFrm,"affilfeedchild") );
		aHeadObj.setDETAffilFocusonfam( m_BaseHelp.getFormData(oFrm,"affilfocusonfam") );
		aHeadObj.setDETAffilFoodforpoor( m_BaseHelp.getFormData(oFrm,"affilfoodforpoor") );
		aHeadObj.setDETAffilHabitathum( m_BaseHelp.getFormData(oFrm,"affilhabitathum") );
		aHeadObj.setDETAffilHandsonnet( m_BaseHelp.getFormData(oFrm,"affilhandsonnet") );
		aHeadObj.setDETAffilHereslifeinncity( m_BaseHelp.getFormData(oFrm,"affilhereslifeinncity") );
		aHeadObj.setDETAffilHud( m_BaseHelp.getFormData(oFrm,"affilhud") );
		aHeadObj.setDETAffilLeaderfoundamer( m_BaseHelp.getFormData(oFrm,"affilleaderfoundamer") );
		aHeadObj.setDETAffilMapint( m_BaseHelp.getFormData(oFrm,"affilmapint") );
		aHeadObj.setDETAffilMennoncentral( m_BaseHelp.getFormData(oFrm,"affilmennoncentral") );
		aHeadObj.setDETAffilReformedamer( m_BaseHelp.getFormData(oFrm,"affilreformedamer") );
		aHeadObj.setDETAffilSalvarmy( m_BaseHelp.getFormData(oFrm,"affilsalvarmy") );
		aHeadObj.setDETAffilSamaritanpurse( m_BaseHelp.getFormData(oFrm,"affilsamaritanpurse") );
		aHeadObj.setDETAffilChrismissa( m_BaseHelp.getFormData(oFrm,"affilchrismissa") );
		aHeadObj.setDETAffilUywi( m_BaseHelp.getFormData(oFrm,"affiluywi") );
		aHeadObj.setDETAffilVolamer( m_BaseHelp.getFormData(oFrm,"affilvolamer") );
		aHeadObj.setDETAffilWorldv( m_BaseHelp.getFormData(oFrm,"affilworldv") );
		aHeadObj.setDETAffilWycliffebib( m_BaseHelp.getFormData(oFrm,"affilwycliffebib") );
		aHeadObj.setDETAffilYmcausa( m_BaseHelp.getFormData(oFrm,"affilymcausa") );
		aHeadObj.setDETAffilYmcasus( m_BaseHelp.getFormData(oFrm,"affilymcasus") );
		aHeadObj.setDETAffilYounglife( m_BaseHelp.getFormData(oFrm,"affilyounglife") );
		aHeadObj.setDETTechParticipants( m_BaseHelp.getFormData(oFrm,"numparticipants") );
		aHeadObj.setDETTechComputers( m_BaseHelp.getFormData(oFrm,"numcomputers") );
		aHeadObj.setDETTechDonate( m_BaseHelp.getFormData(oFrm,"numdonate") );
		aHeadObj.setDETOutcomeCompletion( m_BaseHelp.getFormData(oFrm,"outcomecompl") );
		aHeadObj.setDETOutcomeCertification( m_BaseHelp.getFormData(oFrm,"outcomecert") );
		aHeadObj.setDETOutcomeCollege( m_BaseHelp.getFormData(oFrm,"outcomecoll") );
		aHeadObj.setDETOutcomeJob( m_BaseHelp.getFormData(oFrm,"outcomejob") );
		aHeadObj.setDETOutcomeGed( m_BaseHelp.getFormData(oFrm,"outcomeged") );
		aHeadObj.setDETOutcomeDiscipleship( m_BaseHelp.getFormData(oFrm,"outcomediscipleship") );
		aHeadObj.setDETOutcomeBeliever( m_BaseHelp.getFormData(oFrm,"outcomebeliever") );
    	return 0;
    }
    // end-of method getOrgDataFromForm1()

    //=== END Table org_details ===>
    //=== END Table org_details ===>
    //=== END Table org_details ===>
	
	
    /**
	* get opportunity search options from advance search form
	*/
	public int getOppSearchOptFromForm1(DynaActionForm oFrm, SearchParms aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	aHeadObj.setOPPPositionTypeTID( m_BaseHelp.getFormData(oFrm,"postype") );
		aHeadObj.setOPPPositionTypesArray( m_BaseHelp.getFormDataIntArray(oFrm,"postypesarray"));
		aHeadObj.setUSPLookingForArray( m_BaseHelp.getFormDataIntArray(oFrm,"lookingforarray"));
    	aHeadObj.setCVIntern( m_BaseHelp.getFormData(oFrm,"cvintern"));
    	aHeadObj.setSearchPortals( m_BaseHelp.getFormData(oFrm,"portallist"));
    	aHeadObj.setSearchRequestType(m_BaseHelp.getFormData(oFrm,"requesttype"));
    	aHeadObj.setSearchType( m_BaseHelp.getFormData(oFrm,"searchtype"));
    	aHeadObj.setSearchOrder( m_BaseHelp.getFormData(oFrm,"sortorder"));
    	//aHeadObj.setFilterQuery( m_BaseHelp.getFormData(oFrm,"fq"));
		aHeadObj.setFilterQueryArray( m_BaseHelp.getFormDataStringArray(oFrm,"fq"));
    	aHeadObj.setPostalCode( m_BaseHelp.getFormData(oFrm,"postalcode"));
    	aHeadObj.setZIP( m_BaseHelp.getFormData(oFrm,"zip"));
    	aHeadObj.setDistance( m_BaseHelp.getFormData(oFrm,"distance"));
    	aHeadObj.setSearchLatitude1( m_BaseHelp.getFormData(oFrm,"latitude"));
    	aHeadObj.setSearchLongitude1( m_BaseHelp.getFormData(oFrm,"longitude"));
		aHeadObj.setOPPServiceArea1TID( m_BaseHelp.getFormData(oFrm,"servicearea1") );
		aHeadObj.setOPPServiceArea2TID( m_BaseHelp.getFormData(oFrm,"servicearea2") );
		aHeadObj.setOPPServiceArea3TID( m_BaseHelp.getFormData(oFrm,"servicearea3") );
    	aHeadObj.setOPPSkills1TID( m_BaseHelp.getFormData(oFrm,"skills1id") );
    	aHeadObj.setOPPSkills2TID( m_BaseHelp.getFormData(oFrm,"skills2id") );
    	aHeadObj.setOPPSkills3TID( m_BaseHelp.getFormData(oFrm,"skills3id") );
    	aHeadObj.setGreatForKidTID( m_BaseHelp.getFormData(oFrm,"greatfor1"));
    	aHeadObj.setGreatForSeniorTID( m_BaseHelp.getFormData(oFrm,"greatfor2"));
    	aHeadObj.setGreatForTeenTID( m_BaseHelp.getFormData(oFrm,"greatfor3"));
    	aHeadObj.setGreatForGroupTID( m_BaseHelp.getFormData(oFrm,"greatfor4"));
    	aHeadObj.setGreatForFamilyTID( m_BaseHelp.getFormData(oFrm,"greatfor5"));
    	aHeadObj.setGreatForKidTID( m_BaseHelp.getFormData(oFrm,"greatforkid"));
    	aHeadObj.setGreatForSeniorTID( m_BaseHelp.getFormData(oFrm,"greatforsenior"));
    	aHeadObj.setGreatForTeenTID( m_BaseHelp.getFormData(oFrm,"greatforteen"));
    	aHeadObj.setGreatForGroupTID( m_BaseHelp.getFormData(oFrm,"greatforgroup"));
    	aHeadObj.setGreatForFamilyTID( m_BaseHelp.getFormData(oFrm,"greatforfamily"));
    	aHeadObj.setOPPFrequencyTID( m_BaseHelp.getFormData(oFrm,"frequency"));
    	aHeadObj.setShortTerm( m_BaseHelp.getFormData(oFrm,"shortterm"));
    	aHeadObj.setCity( m_BaseHelp.getFormData(oFrm,"city"));
    	aHeadObj.setState( m_BaseHelp.getFormData(oFrm,"state"));
    	aHeadObj.setOthrProv( m_BaseHelp.getFormData(oFrm,"prov"));
    	aHeadObj.setRegionTID( m_BaseHelp.getFormData(oFrm,"regiontid"));
    	aHeadObj.setCountry( m_BaseHelp.getFormData(oFrm,"country"));
    	aHeadObj.setOrgName( m_BaseHelp.getFormData(oFrm,"orgname"));
    	aHeadObj.setMinSize( m_BaseHelp.getFormData(oFrm,"mingroup"));
    	aHeadObj.setMaxSize( m_BaseHelp.getFormData(oFrm,"maxgroup"));

    	aHeadObj.setDuration( m_BaseHelp.getFormData(oFrm,"duration"));
    	aHeadObj.setOPPTripLength( m_BaseHelp.getFormData(oFrm,"duration"));
		aHeadObj.setOPPTripLengthTID( m_BaseHelp.getFormData(oFrm,"duration") );

		aHeadObj.setOPPRoomBoardTID( m_BaseHelp.getFormData(oFrm,"roomboard") );
		aHeadObj.setOPPStipendTID( m_BaseHelp.getFormData(oFrm,"stipend") );
		aHeadObj.setOPPMedInsurTID( m_BaseHelp.getFormData(oFrm,"medinsur") );
		aHeadObj.setOPPTransportTID( m_BaseHelp.getFormData(oFrm,"transport") );
		aHeadObj.setOPPAmeriCorpsTID( m_BaseHelp.getFormData(oFrm,"americorps") );

		
		aHeadObj.setOPPWorkStudyTID( m_BaseHelp.getFormData(oFrm,"workstudy") );
		aHeadObj.setServiceAreas( m_BaseHelp.getFormData(oFrm, "serviceareas") );
		aHeadObj.setSkillTypes( m_BaseHelp.getFormData(oFrm, "skilltypes") );
		aHeadObj.setCauseTopics( m_BaseHelp.getFormData(oFrm, "causetopics"));
		aHeadObj.setUSPLookingForArray( m_BaseHelp.getFormDataIntArray(oFrm,"lookingforarray"));
		aHeadObj.setLookingForTIDs( m_BaseHelp.getFormData(oFrm,"lookingfor"));
		if(aHeadObj.getLookingForTIDs().endsWith(",")){
			aHeadObj.setLookingForTIDs(aHeadObj.getLookingForTIDs().substring(0,aHeadObj.getLookingForTIDs().length()-1));
		}
		aHeadObj.setCityTID( m_BaseHelp.getFormData(oFrm, "citytid"));
		aHeadObj.setCountryTID( m_BaseHelp.getFormData(oFrm, "countrytid"));

    	aHeadObj.setProgramTypeTID( m_BaseHelp.getFormData(oFrm,"programtypetid"));
    	aHeadObj.setDenomAffilTID( m_BaseHelp.getFormData(oFrm,"denomaffiltid"));
    	aHeadObj.setOrgAffil1TID( m_BaseHelp.getFormData(oFrm,"orgaffil1tid"));
    	aHeadObj.setOrgAffil2TID( m_BaseHelp.getFormData(oFrm,"orgaffil2tid"));
    	aHeadObj.setOrgAffil3TID( m_BaseHelp.getFormData(oFrm,"orgaffil3tid"));
    	aHeadObj.setOrgAffil4TID( m_BaseHelp.getFormData(oFrm,"orgaffil4tid"));
    	aHeadObj.setOrgAffil5TID( m_BaseHelp.getFormData(oFrm,"orgaffil5tid"));

    	aHeadObj.setNotLclPostalCode( m_BaseHelp.getFormData(oFrm,"notlocalpostal"));
		aHeadObj.setNotLclCityTID( m_BaseHelp.getFormData(oFrm, "notlocalcitytid"));
		aHeadObj.setNotLclCntryTID( m_BaseHelp.getFormData(oFrm, "notlocalcntrytid"));
    	aHeadObj.setNotCountry( m_BaseHelp.getFormData(oFrm,"notcountry"));

		aHeadObj.setFaithTID( m_BaseHelp.getFormData(oFrm, "faithtid"));
		aHeadObj.setNotUS( m_BaseHelp.getFormDataBoolean(oFrm, "notus"));
		aHeadObj.setOPPHQorOffSite( m_BaseHelp.getFormData(oFrm, "hqoroffsite"));

		aHeadObj.setAssocOnly( m_BaseHelp.getFormDataBoolean(oFrm, "assoconly"));

    	aHeadObj.setNID( m_BaseHelp.getFormData(oFrm,"orgnid"));
    	aHeadObj.setORGNID( m_BaseHelp.getFormData(oFrm,"orgnid"));
    	aHeadObj.setOrgNumber( m_BaseHelp.getFormData(oFrm,"orgnid"));
//    	aHeadObj.setKeyWord( m_BaseHelp.getFormData(oFrm,"keyword"));
		aHeadObj.setKeywordsArray( m_BaseHelp.getFormDataStringArray(oFrm,"keyword"));

    	String aszMaxRows = m_BaseHelp.getFormData(oFrm,"maxrows");
    	if(aszMaxRows.length() >1){
        	aHeadObj.setmaxSearchResultRows( aszMaxRows );
    	}
    	aHeadObj.setOppVolType( m_BaseHelp.getFormData(oFrm,"voltype") );
    	aHeadObj.setSearchKey( m_BaseHelp.getFormData(oFrm,"searchkey1"));
    	aHeadObj.setSearchKey2( m_BaseHelp.getFormData(oFrm,"searchkey2"));
    	aHeadObj.setSearchKey3( m_BaseHelp.getFormData(oFrm,"searchkey3"));
    	aHeadObj.setProgramType( m_BaseHelp.getFormData(oFrm,"programtype"));
    	aHeadObj.setCategory( m_BaseHelp.getFormData(oFrm,"category1"));
    	aHeadObj.setCategory2( m_BaseHelp.getFormData(oFrm,"category2"));
    	aHeadObj.setCategory3( m_BaseHelp.getFormData(oFrm,"category3"));
    	aHeadObj.setSkills1( m_BaseHelp.getFormData(oFrm,"skills1"));
    	aHeadObj.setSkills2( m_BaseHelp.getFormData(oFrm,"skills2"));
    	aHeadObj.setSkills3( m_BaseHelp.getFormData(oFrm,"skills3"));
    	aHeadObj.setORGMembertypeTID( m_BaseHelp.getFormData(oFrm,"membertypetid"));
    	aHeadObj.setORGMembertype( m_BaseHelp.getFormData(oFrm,"membertype"));
    	aHeadObj.setDenomAffil( m_BaseHelp.getFormData(oFrm,"affiliation"));
    	aHeadObj.setPartner( m_BaseHelp.getFormData(oFrm,"partner"));
    	aHeadObj.setPartner2( m_BaseHelp.getFormData(oFrm,"partner2"));
    	aHeadObj.setPartner3( m_BaseHelp.getFormData(oFrm,"partner3"));
    	aHeadObj.setPartner4( m_BaseHelp.getFormData(oFrm,"partner4"));
    	aHeadObj.setPartner5( m_BaseHelp.getFormData(oFrm,"partner5"));
    	aHeadObj.setNumVolOpp( m_BaseHelp.getFormData(oFrm,"oppnumvol"));
    	aHeadObj.setNumVolOrg( m_BaseHelp.getFormData(oFrm,"orgnumvol"));
    	aHeadObj.setNumServed( m_BaseHelp.getFormData(oFrm,"numserved"));
    	aHeadObj.setFormalTrain( m_BaseHelp.getFormData(oFrm,"formaltrain"));
    	aHeadObj.setOrgStmtFaith( m_BaseHelp.getFormData(oFrm,"orgstmtfaith"));
    	aHeadObj.setRegion( m_BaseHelp.getFormData(oFrm,"region"));
    	aHeadObj.setReqCode( m_BaseHelp.getFormData(oFrm,"requiredcodetype"));
    	aHeadObj.setCodeDesc( m_BaseHelp.getFormData(oFrm,"requiredcodedesc"));
    	aHeadObj.setFocusArea( m_BaseHelp.getFormData(oFrm,"focusare1"));
    	aHeadObj.setFocusArea2( m_BaseHelp.getFormData(oFrm,"focusare2"));
    	aHeadObj.setFocusArea3( m_BaseHelp.getFormData(oFrm,"focusare3"));
    	aHeadObj.setFocusArea4( m_BaseHelp.getFormData(oFrm,"focusare4"));
    	aHeadObj.setFocusArea5( m_BaseHelp.getFormData(oFrm,"focusare5"));
    	aHeadObj.setLocalAffil( m_BaseHelp.getFormData(oFrm,"localaffil"));

    	aHeadObj.setPreviewSearch( m_BaseHelp.getFormData(oFrm, "preview") );
		aHeadObj.setBackgroundColor( m_BaseHelp.getFormData(oFrm, "bckgrnd") );
		aHeadObj.setBorderColor( m_BaseHelp.getFormData(oFrm, "brdr"));
		aHeadObj.setLinkTextColor( m_BaseHelp.getFormData(oFrm,"atxt"));
		aHeadObj.setTextColor( m_BaseHelp.getFormData(oFrm,"txt"));

    	aHeadObj.setSearchForm( m_BaseHelp.getFormData(oFrm,"formname"));
    	aHeadObj.setSource( m_BaseHelp.getFormData(oFrm,"source"));
    	
    	return 0;
    }
    // end-of method getOppSearchOptFromForm1()
	
	
    /**
	* fill email data into form - added by ali - 2006-09-08 --- may be temporary
	*/
	public int fillEmailDataIntoForm(DynaActionForm oFrm, EmailInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "errormsg", aHeadObj.getErrorMsg() );
    	m_BaseHelp.setFormData(oFrm, "orgnid", "" + aHeadObj.getEmailOrgNID() );
    	m_BaseHelp.setFormData(oFrm, "oppnid", "" + aHeadObj.getEmailOppNID() );
    	m_BaseHelp.setFormData(oFrm, "voluid", "" + aHeadObj.getEmailVolUID() );
    	m_BaseHelp.setFormData(oFrm, "volrailsid", "" + aHeadObj.getEmailVolRailsID() );
    	m_BaseHelp.setFormData(oFrm, "subdom", aHeadObj.getEmailSubdom() );
    	m_BaseHelp.setFormData(oFrm, "emailmsgtype", "" + aHeadObj.getEmailMessageType() );
    	m_BaseHelp.setFormData(oFrm, "smtpauthreq", "" + aHeadObj.getSMTPAuthRequired() );
    	m_BaseHelp.setFormData(oFrm, "emaildebug", "" + aHeadObj.getEmailDebug() );
    	m_BaseHelp.setFormData(oFrm, "smtpserver", aHeadObj.getEmailSMTPServerName() );
    	m_BaseHelp.setFormData(oFrm, "stmpuserpwd", aHeadObj.getEmailFromUserPassword() );
    	m_BaseHelp.setFormData(oFrm, "stmpuser", aHeadObj.getSMTPUserName() );
    	m_BaseHelp.setFormData(oFrm, "emailbodytxt", aHeadObj.getEmailBodyText() );
    	m_BaseHelp.setFormData(oFrm, "emailbodytxtres", aHeadObj.getEmailBodyTextRes() );
    	m_BaseHelp.setFormData(oFrm, "emailresfilepath", aHeadObj.getEmailResumeFilePath() );
    	m_BaseHelp.setFormData(oFrm, "volfn", aHeadObj.getEmailVolFN() );
    	m_BaseHelp.setFormData(oFrm, "volln", aHeadObj.getEmailVolLN() );
    	m_BaseHelp.setFormData(oFrm, "volphone", aHeadObj.getEmailVolPhone1() );
    	m_BaseHelp.setFormData(oFrm, "volchris", aHeadObj.getEmailVolChris() );
    	m_BaseHelp.setFormData(oFrm, "volskills", aHeadObj.getEmailVolSkills() );
    	m_BaseHelp.setFormData(oFrm, "emailsubject", aHeadObj.getEmailSubjectText() );
    	m_BaseHelp.setFormData(oFrm, "fromuser", aHeadObj.getEmailFromUser() );
    	m_BaseHelp.setFormData(oFrm, "touser", aHeadObj.getEmailToUser() );
    	m_BaseHelp.setFormData(oFrm, "contactfn", aHeadObj.getEmailContactFN() );
    	m_BaseHelp.setFormData(oFrm, "contactln", aHeadObj.getEmailContactLN() );
    	m_BaseHelp.setFormData(oFrm, "oppname", aHeadObj.getEmailOppName() );
    	m_BaseHelp.setFormData(oFrm, "orgname", aHeadObj.getEmailOrgName() );
    	m_BaseHelp.setFormData(oFrm, "orgaddr1", aHeadObj.getEmailOrgAddr1() );
    	m_BaseHelp.setFormData(oFrm, "orgcity", aHeadObj.getEmailOrgCity() );
    	m_BaseHelp.setFormData(oFrm, "orgstate", aHeadObj.getEmailOrgState() );
    	m_BaseHelp.setFormData(oFrm, "orgprov", aHeadObj.getEmailOrgProv() );
    	m_BaseHelp.setFormData(oFrm, "orgcountry", aHeadObj.getEmailOrgCountry() );
    	m_BaseHelp.setFormData(oFrm, "orgphone", aHeadObj.getEmailOrgPhone() );
    	m_BaseHelp.setFormData(oFrm, "orgweb", aHeadObj.getEmailOrgWeb() );
    	m_BaseHelp.setFormData(oFrm, "emailattachfile", aHeadObj.getEmailAttachFileName() );
    	return 0;
    }
    // end-of method fillEmailDataIntoForm
	
	
    /**
	* get email data from i want to help form
	*/
	public int getEmailDataFromForm1(DynaActionForm oFrm, EmailInfoDTO aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
		String aszTemp=null;
    	aHeadObj.setEmailMessageType( m_BaseHelp.getFormData(oFrm,"emailmsgtype") );
		//aHeadObj.setSMTPAuthRequired( m_BaseHelp.getFormData(oFrm,"smtpauthreq") );
		//aHeadObj.setEmailDebug( m_BaseHelp.getFormData(oFrm,"emaildebug") );
		aHeadObj.setEmailOrgNID( m_BaseHelp.getFormData(oFrm,"orgnid") );
		aHeadObj.setEmailOppNID( m_BaseHelp.getFormData(oFrm,"oppnid") );
		aHeadObj.setEmailVolUID( m_BaseHelp.getFormData(oFrm,"voluid") );
		aHeadObj.setEmailVolRailsID( m_BaseHelp.getFormData(oFrm,"volrailsid") );
		aHeadObj.setEmailSubdom( m_BaseHelp.getFormData(oFrm,"subdom") );
		aHeadObj.setEmailSMTPServerName( m_BaseHelp.getFormData(oFrm,"smtpserver") );
		aHeadObj.setEmailFromUserPassword( m_BaseHelp.getFormData(oFrm,"stmpuserpwd") );
		aHeadObj.setSMTPUserName( m_BaseHelp.getFormData(oFrm,"stmpuser") );
		aHeadObj.setEmailBodyText( m_BaseHelp.getFormData(oFrm,"emailbodytxt") );
		aHeadObj.setEmailBodyTextRes( m_BaseHelp.getFormData(oFrm,"emailbodytxtres") );
		aHeadObj.setEmailResumeFilePath( m_BaseHelp.getFormData(oFrm,"emailresfilepath") );
		aHeadObj.setEmailVolFN( m_BaseHelp.getFormData(oFrm,"volfn") );
		aHeadObj.setEmailVolLN( m_BaseHelp.getFormData(oFrm,"volln") );
		aHeadObj.setEmailVolPhone1( m_BaseHelp.getFormData(oFrm,"volphone") );
		aHeadObj.setEmailVolChris( m_BaseHelp.getFormData(oFrm,"volchris") );
		aHeadObj.setEmailVolSkills( m_BaseHelp.getFormData(oFrm,"volskills") );
		aHeadObj.setEmailSubjectText( m_BaseHelp.getFormData(oFrm,"emailsubject") );
		aHeadObj.setEmailFromUser( m_BaseHelp.getFormData(oFrm,"fromuser") );
		aHeadObj.setEmailToUser( m_BaseHelp.getFormData(oFrm,"touser") );
		aHeadObj.setEmailContactFN( m_BaseHelp.getFormData(oFrm,"contactfn") );
		aHeadObj.setEmailContactLN( m_BaseHelp.getFormData(oFrm,"contactln") );
		aHeadObj.setEmailOppName( m_BaseHelp.getFormData(oFrm,"oppname") );
		aHeadObj.setEmailOrgName( m_BaseHelp.getFormData(oFrm,"orgname") );
		aHeadObj.setEmailOrgAddr1( m_BaseHelp.getFormData(oFrm,"orgaddr1") );
		aHeadObj.setEmailOrgCity( m_BaseHelp.getFormData(oFrm,"orgcity") );
		aHeadObj.setEmailOrgState( m_BaseHelp.getFormData(oFrm,"orgstate") );
		aHeadObj.setEmailOrgProv( m_BaseHelp.getFormData(oFrm,"orgprov") );
		aHeadObj.setEmailOrgCountry( m_BaseHelp.getFormData(oFrm,"orgcountry") );
		aHeadObj.setEmailOrgPhone( m_BaseHelp.getFormData(oFrm,"orgphone") );
		aHeadObj.setEmailOrgWeb( m_BaseHelp.getFormData(oFrm,"orgweb") );
		aHeadObj.setEmailSTMReferencesText( m_BaseHelp.getFormData(oFrm,"stmreferences") );
		aHeadObj.setEmailAttachFileName( m_BaseHelp.getFormData(oFrm,"emailattachfile") );
    	return 0;
    }
    // end-of method getEmailDataFromForm


    /**
	* get indiviudal data from registration form
	*/
	public int getVolEmailDataFromForm(DynaActionForm oFrm, EmailInfoDTO aHeadObj){
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
    	aHeadObj.setEmailResumeFilePath( m_BaseHelp.getFormData( oFrm, "emailresfilepath" )); 
    	//String DUPLICATE 
    	aHeadObj.setEmailVolUID( m_BaseHelp.getFormData( oFrm, "voluid" )); 
    	aHeadObj.setEmailVolFN( m_BaseHelp.getFormData( oFrm, "volfn" )); 
    	aHeadObj.setEmailVolLN( m_BaseHelp.getFormData( oFrm, "volln" )); 
    	aHeadObj.setEmailVolPhone1( m_BaseHelp.getFormData( oFrm, "volphone" )); 
   	return 0;
    }
    // end-of method getVolEmailDataFromForm

	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
}
