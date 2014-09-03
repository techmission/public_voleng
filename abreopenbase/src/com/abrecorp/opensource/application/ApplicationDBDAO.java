package com.abrecorp.opensource.application;

/**
* Code Generated DataStore Class
* For Table organizationinfo
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
//import com.abrecorp.opensource.servlet.BaseServletABRE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ApplicationDBDAO extends ABREBase {

	/**
	** Constructor
	*/
    public ApplicationDBDAO(){}



    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>

    /**
	* insert a row into table org_opportunitiesinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertApplicationIntoDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj ){
		int iRetCode=0, iDeltaMax=0, index=0;
		int[] a_iContainer= new int[1];
		int iTmpSize = 0;
		String aszSQLdrupal101 = "";
//System.out.println("inside DBDAO create opp");
//System.out.println("aApplicantObj.getOPPUID()  is "+aApplicantObj.getOPPUID() );		
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1;
		int iOrgVID=-1;
		String Qry1=null;
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		// get the org's vid - will be needed for entry into new um_content_field_volorg_opp_reference table
		int iInitScreenedValue=aApplicantObj.getScreened();
//System.out.println("ApplicationDBDAO - 63 - iInitScreenedValue is "+iInitScreenedValue);		
		if( iInitScreenedValue < 0 && iInitScreenedValue > -6){ // don't edit the values of users who are explicitly NOT approved
		}else{
			if(iInitScreenedValue < 1){
				aApplicantObj.setScreened(-10);
				iInitScreenedValue=0;
			}
			if(aApplicantObj.getTestimony().length()>0){
				aApplicantObj.setScreened(-9);
				if(aApplicantObj.getProfRefEmail().length()>0){
					aApplicantObj.setScreened(-8);
					if(aApplicantObj.getResumeFilePath().length()>0){
						aApplicantObj.setScreened(iInitScreenedValue);
					}
				}
			}
		}
//System.out.println("ApplicationDBDAO - 79 - aApplicantObj.getScreened() is "+aApplicantObj.getScreened());		

		/*
		 * add to node_revisions
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format,log) " +
				"VALUES(?,?,?,?,UNIX_TIMESTAMP({fn now()}),0,'' ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aApplicantObj.getTitle()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aApplicantObj.getTitle()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		
		//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
		Qry1 = "SELECT LAST_INSERT_ID() ";
		iRetCode=pConn.PrepQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExePrepQry();//RunQry(Qry1); fixed to use ExePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
//System.out.println("Before    LAST_INSERT_ID() - line 126");		
			ivid = pConn.getDBInt("LAST_INSERT_ID()");
//System.out.println("After    LAST_INSERT_ID() - line 128");		
			aApplicantObj.setVID(ivid);
		} else {
			itid=-1;
		}
		iRetCode=-1;
		
		
		// add to um_node
		// will be put in moderation by default, unless it is through the portal sites, and is set to private; ie not published, and not moderated
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate, title, uid, created, changed) " +
			"VALUES(" + ivid + ",'cvintern_applicant',0,0,0,?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		
		
		//	*****  Grab the last auto-incremented ID and save it as the nid for this node *****************
		Qry1 = "SELECT LAST_INSERT_ID() ";
		iRetCode=pConn.PrepQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExePrepQry();//RunQry(Qry1); - fixed to use ExePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
			inid = pConn.getDBInt("LAST_INSERT_ID()");
			aApplicantObj.setNID(inid);
		} else {
			itid=-1;
		}
		iRetCode=-1;
		
		/*
		 * add correct nid to node_revisions
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node_revisions SET nid=? " +
				"WHERE vid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
//System.out.println("  createopp   nid "+inid+"  vid "+ivid);
//		*********update um_node_revisions set nid=inid where vid=ivid **********************

		// add to um_node_access
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
				"VALUES("+ inid +", 0, 'all', 1, 0, 0) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;


		// add to um_node_comment_statistics
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
				"VALUES("+ inid +", UNIX_TIMESTAMP({fn now()}), ?,0) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		
		
		
		
		/*
		 * add to location
		 */
		// 2008-10-27 - update to location drupal module required change of code b/c db structure was altered
		
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location(street, city, province, postal_code, " +
				"country, source, is_primary) " +
				"VALUES(?,?,?,?,?,'0','0' ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrLine1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrStateprov() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrPostalcode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrCountryName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		//	*****  Grab the last auto-incremented ID and save it as the lid for this location/node *****************
		Qry1 = "SELECT LAST_INSERT_ID() ";
		iRetCode=pConn.PrepQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("LAST_INSERT_ID()");
		} else {
			itid=-1;
		}
		iRetCode=-1;

		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_instance(lid,nid, vid, genid) " +
				"VALUES(" + ilid +"," + inid +","  + ivid +",'' ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		// add to um_content_type_volunteer_opportunity
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_cvintern_applicant " +
				"(vid,nid,field_cvintern_apply_dt_value ," +
				"field_cvintern_first_name_value,field_cvintern_last_name_value,field_cvintern_email_value,field_cvintern_phone_value," +
				"field_cvintern_chris_value,field_cvintern_18_value,field_cvintern_diploma_value," +
				"field_cvintern_length_commitment_value,field_cvintern_bachelor_attendin_value,field_cvintern_testimony_value," +
				"field_cvintern_geo_pref_value,field_cvintern_interest_reason_value,field_cvintern_language_value," +
				"field_cvintern_church_value,field_cvintern_major_pref_value,field_cvintern_has_bachelor_value," +
				"field_cvintern_credits_value,field_cvintern_career_goals_value,field_cvintern_hourly_commitment_value," +
				"field_cvintern_livable_stipend_value,field_cvintern_livable_stip_expl_value," +
				"field_cvintern_criminal_record_value,field_cvintern_criminal_desc_value," +
				"field_cvintern_housing_value,field_cvintern_service_site_value,field_cvintern_location_pref_value," +
				"field_cvintern_start_time_value," +
				"field_cvintern_forward_resume_value,field_cvintern_webcam_value,field_cvintern_skype_value," +
				"field_cvintern_intern_length_value,field_pastoral_ref_name_value,field_pastoral_ref_church_value," +
				"field_pastoral_ref_phone_value, " + // stmreferences, orgnid, urlid
				"field_pastoral_ref_email_value,field_prof_ref_name_value,field_prof_ref_org_value, " + 
				"field_prof_ref_phone_value, field_prof_ref_email_value, " +
				"field_cvintern_location_lid," +
				"field_cvintern_gender_value,field_cvintern_dob_value," +
				"field_last_active_highschool_value,field_cvdegree_carreergoals_value" +
				",field_cvintern_screened_value,field_ineligibility_reason_value" +
				",field_cvintern_resume_filepath_value,field_cvintern_drupal_uprofile_url) " + 
				"VALUES("+ ivid +"," + inid + ",{fn now()}" +
				",?,?,?,?" + // field_cvintern_first_name_value,field_cvintern_last_name_value,field_cvintern_email_value,field_cvintern_phone_value
				",?,?,?" +// field_cvintern_chris_value,field_cvintern_18_value,field_cvintern_diploma_value
				",?,?,?" + //  field_cvintern_length_commitment_value,field_cvintern_bachelor_attendin_value,field_cvintern_testimony_value,
				",?,?,?," +// field_cvintern_geo_pref_value,field_cvintern_interest_reason_value,field_cvintern_language_value 
				"?,?,?," + // field_cvintern_church_value,field_cvintern_major_pref_value,field_cvintern_has_bachelor_value
				"?,?,?," + // field_cvintern_credits_value,field_cvintern_career_goals_value,field_cvintern_hourly_commitment_value
				"?,?," + 	// field_cvintern_livable_stipend_value,field_cvintern_livable_stip_expl_value,
				"?,?," + // field_cvintern_criminal_record_value,field_cvintern_criminal_desc_value
				"?,?,?," + // field_cvintern_housing_value,field_cvintern_service_site_value,field_cvintern_location_pref_value,
				"?," + // field_cvintern_start_time_value
				"?,?,?," + // field_cvintern_forward_resume_value,field_cvintern_webcam_value,field_cvintern_skype_value,
				"?,?,?," + // field_cvintern_intern_length_value,field_pastoral_ref_name_value,field_pastoral_ref_church_value,
				"?," + // field_pastoral_ref_phone_value, 
				"?,?,?," + // field_pastoral_ref_email_value,field_prof_ref_name_value,field_prof_ref_org_value, 
				"?,?," + // field_prof_ref_phone_value, field_prof_ref_email_value,
				"'"+ilid+"'," + // field_cvintern_location_lid
				"?,?," + // field_cvintern_gender_value,field_cvintern_dob_value
				"?,?"+ // field_last_active_highschool_value,field_cvdegree_carreergoals_value
				",?,?" + //field_cvintern_screened_value, field_ineligibility_reason_value
				",?,?)"; //field_cvintern_resume_filepath_value,field_cvintern_drupal_uprofile_url
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aApplicantObj.getNameFirst()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getEmailAddr() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getChristian() ); // Number of Volunteers Who Have Served in This Position in the Past Year
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAgeRequirement() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getDiploma() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTimeCommitAbility() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getBachelorsAttending() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTestimony() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getGeoPref() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getInternReason() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLang() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getChurch() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getMajor() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHasBachelors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getCredits() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCareerGoals() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHrlyCommit() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLivableStipend() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLivableStipendExpl() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCrimRecord() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCrimDescrip() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHousing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getServiceSite() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLocPrefInfo() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getStartTime() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getForwardResume() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getWebcam() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getSkype() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getInternLength() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRef() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefChurch() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRef() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getGender() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aApplicantObj.getDOBDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getLastYrActiveHS() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCVDegreeCareerGoals() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getScreened() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getIneligibilityReason() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getResumeFilePath() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getUserProfileLink() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		
		/**
		 * START taxonomy section
		 */

		// add vidCitizenStatus
		if ( aApplicantObj.getCitizenTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getCitizenTID() + 
				" AND vid = " + vidCitizenStatus + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setCitizenTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add getDegreeProgTID
		if ( aApplicantObj.getDegreeProgTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getDegreeProgTID() + 
				" AND vid = " + vidCVDegreeProgram + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setDegreeProgTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add getInternLengthTID
		if ( aApplicantObj.getInternLengthTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getInternLengthTID() + 
				" AND vid = " + vidInternshipLength + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setInternLengthTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// add getPosPrefTID
		if ( aApplicantObj.getPosPrefTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getPosPrefTID() + 
				" AND vid = " + vidInternPosType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setPosPrefTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add getSkillsTID
		if ( aApplicantObj.getSkillsTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getSkillsTID() + 
				" AND vid = " + vidSpecialSkills + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setSkillsTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add getSourceTID
		if ( aApplicantObj.getSourceTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getSourceTID() + 
				" AND vid = " + vidSource + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setSourceTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add getPopulPrefTID
		if ( aApplicantObj.getPopulPrefTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getPopulPrefTID() + 
				" AND vid = " + vidWorkPopulationPref + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setPopulPrefTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add getInternTypeTID
		
		// iterate through all the getInternTypeTIDsArray and add them in
		a_iContainer = aApplicantObj.getInternTypeTIDsArray(); 
		iTmpSize = a_iContainer.length; 
		for(int i=0; i< iTmpSize; i++){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  a_iContainer[i] + 
				" AND vid = " + vidInternshipType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setInternTypeTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			itid=-1;
		}
		// add getOPPWorkEnvironTID
		if ( aApplicantObj.getWorkEnvironTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getWorkEnvironTID() + 
				" AND vid = " + vidWorkEnvironment + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aApplicantObj.setWorkEnvironTID(itid);
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
//				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		return iRetCode;
	}
	// end-of method insertApplicationIntoDB()

    /**
	* update opportunity record in table org_opportunitiesinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int editApplicationInDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj ){
		int iRetCode=0, index=0;
		String aszSQLdrupal101, Qry1;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		int itid=-1;
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		int iInitScreenedValue=aApplicantObj.getScreened();

//System.out.println("Applicant UID "+aApplicantObj.getUID()+"; iInitScreenedValue "+iInitScreenedValue);
//System.out.println("ApplicationDBDAO - 1148 - iInitScreenedValue is "+iInitScreenedValue);		
		if( iInitScreenedValue < 0 && iInitScreenedValue > -6){ // don't edit the values of users who are explicitly NOT approved
		}else{
			if(iInitScreenedValue < 1){
				aApplicantObj.setScreened(-10);
				iInitScreenedValue=0;
			}
			if(aApplicantObj.getTestimony().length()>0){
				aApplicantObj.setScreened(-9);
				if(aApplicantObj.getProfRefEmail().length()>0){
					aApplicantObj.setScreened(-8);
					if(aApplicantObj.getResumeFilePath().length()>0){
						aApplicantObj.setScreened(iInitScreenedValue);
					}
				}
			}
		}
//System.out.println("ApplicationDBDAO - 1165 - iInitScreenedValue  aApplicantObj.getScreened() is "+aApplicantObj.getScreened());		

		int inid=aApplicantObj.getNID(), ivid=aApplicantObj.getVID(), ilid=aApplicantObj.getLID();
		/*
		 * edit to location - has to preceed others, just like taxonomy, b/c RunQry can't be used after PrepQuery - 2008-10-28
		 */
		// grab the lid from um_location_instance to delete from um_location
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "location SET street=?, city=?, province=?, postal_code=?, " +
					"country=? WHERE lid = " + ilid;
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrLine1() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrCity() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrStateprov() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrPostalcode() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aApplicantObj.getAddrCountryName() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_cvintern_applicant" +
				" SET field_cvintern_first_name_value=?,field_cvintern_last_name_value=?,field_cvintern_email_value=?,field_cvintern_phone_value=?," +
				"field_cvintern_chris_value=?,field_cvintern_18_value=?,field_cvintern_diploma_value=?," +
				"field_cvintern_length_commitment_value=?,field_cvintern_bachelor_attendin_value=?,field_cvintern_testimony_value=?," +
				"field_cvintern_geo_pref_value=?,field_cvintern_interest_reason_value=?,field_cvintern_language_value=?," +
				"field_cvintern_church_value=?,field_cvintern_major_pref_value=?,field_cvintern_has_bachelor_value=?," +
				"field_cvintern_credits_value=?,field_cvintern_career_goals_value=?,field_cvintern_hourly_commitment_value=?," +
				"field_cvintern_livable_stipend_value=?,field_cvintern_livable_stip_expl_value=?," +
				"field_cvintern_criminal_record_value=?,field_cvintern_criminal_desc_value=?," +
				"field_cvintern_housing_value=?,field_cvintern_service_site_value=?,field_cvintern_location_pref_value=?," +
				"field_cvintern_start_time_value=?," +
				"field_cvintern_forward_resume_value=?,field_cvintern_webcam_value=?,field_cvintern_skype_value=?," +
				"field_cvintern_intern_length_value=?,field_pastoral_ref_name_value=?,field_pastoral_ref_church_value=?," +
				"field_pastoral_ref_phone_value=?, " +
				"field_pastoral_ref_email_value=?,field_prof_ref_name_value=?,field_prof_ref_org_value=?, " + 
				"field_prof_ref_phone_value=?, field_prof_ref_email_value=?, " +
				"field_cvintern_gender_value=?,field_cvintern_dob_value=?," +
				"field_last_active_highschool_value=?,field_cvdegree_carreergoals_value=?, field_cvintern_screened_value=?," +
				"field_ineligibility_reason_value=?,field_cvintern_apply_dt_value ={fn now()}" + 
				" ,field_cvintern_resume_filepath_value=?,field_cvintern_drupal_uprofile_url=? " + 
				" WHERE vid=?"; 
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aApplicantObj.getNameFirst()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getEmailAddr() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getChristian() ); // Number of Volunteers Who Have Served in This Position in the Past Year
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getAgeRequirement() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getDiploma() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTimeCommitAbility() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getBachelorsAttending() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getTestimony() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getGeoPref() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getInternReason() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLang() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getChurch() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getMajor() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHasBachelors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getCredits() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCareerGoals() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHrlyCommit() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLivableStipend() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLivableStipendExpl() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCrimRecord() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCrimDescrip() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getHousing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getServiceSite() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getLocPrefInfo() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getStartTime() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getForwardResume() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getWebcam() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getSkype() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getInternLength() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRef() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefChurch() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getPastoralRefEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRef() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getProfRefEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getGender() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aApplicantObj.getDOBDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getLastYrActiveHS() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getCVDegreeCareerGoals() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getScreened() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getIneligibilityReason() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getResumeFilePath() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getUserProfileLink() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
System.out.println("appDBDAO EDIT 1565 - resume filepath: "+aApplicantObj.getResumeFilePath()+";   profile link: "+aApplicantObj.getUserProfileLink());		
		
		/**
		 * START taxonomy section
		 * START taxonomy section
		 * START taxonomy section
		 */
		/**
		// delete occurrences of the taxonomy and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
		// !!!!!!!!!!!!!!!!!!! potentially NOT SAFE - b/c this would delete ALL, it would have to join term_data, too, to grab the vocabulary id
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + aApplicantObj.getNID();
		
/**
		// delete occurrences of this node with the taxonomy within the opportunity vocabularies
		// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + aApplicantObj.getOPPNID() +" AND ( vid = " + 
			vidPosType + " OR vid = " + vidService + " OR vid = " + vidSkill + " OR vid = " + vidVolInfo + " OR vid = " +
			vidDenomAffil + " OR vid = " + vidOrgAffil + " OR vid = " + vidMemberType + " OR vid = " + vidProgramType + " OR vid = " + 
			vidLangSpoken + " OR vid = " + vidWorkStudy + " OR vid = " + vidTripLength + " OR vid = " + vidBenefits + " OR vid= " +
			vidPosFreq + " OR vid = " + vidSchedDate + " ) ";
* /
		
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
*/
		
		if(inid>0){
			// 1st, delete all records in um_content_field_cvintern_org and um_content_field_cvintern_org_source_flag for given nid
			int iTmpSize = 0;
			String aszSQL101 = "";
			// clear all the org_nids and re-write them
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "content_field_cvintern_org WHERE nid = " + aApplicantObj.getNID() +" AND " +
					"vid = " + aApplicantObj.getVID();
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "content_field_cvintern_org_source_flag WHERE nid = " + aApplicantObj.getNID() +" AND " +
					"vid = " + aApplicantObj.getVID();
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			// iterate through all the ORGNIDS and add them in
			a_iContainer = aApplicantObj.getORGNIDsArray(); 
			iTmpSize = a_iContainer.length; 
			for(int i=0; i< iTmpSize; i++){
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_cvintern_org(vid, nid, delta, field_cvintern_org_nid) " +
						"VALUES(" + aApplicantObj.getVID() + "," + aApplicantObj.getNID() + "," + i + "," + a_iContainer[i] + " ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate!!!!!!!!!!!!
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}

				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_cvintern_org_source_flag(vid, nid, delta, field_cvintern_org_nid, source) " +
						"VALUES(" + aApplicantObj.getVID() + "," + aApplicantObj.getNID() + "," + i + "," + a_iContainer[i] + ", 'REQUESTED' ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate!!!!!!!!!!!!
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			
			}

			iTmpSize = 0;
			aszSQL101 = "";
			// clear all the opp_nids and re-write them
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "content_field_cvintern_opp WHERE nid = " + aApplicantObj.getNID() +" AND " +
					"vid = " + aApplicantObj.getVID();
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			// iterate through all the OPPNIDS and add them in
			a_iContainer = aApplicantObj.getOPPNIDsArray(); 
			iTmpSize = a_iContainer.length; 
			for(int i=0; i< iTmpSize; i++){
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_cvintern_opp(vid, nid, delta, field_cvintern_opp_nid) " +
						"VALUES(" + aApplicantObj.getVID() + "," + aApplicantObj.getNID() + "," + i + "," + a_iContainer[i] + " ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate!!!!!!!!!!!!
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			
			
			
			
			
			/**
			 * START taxonomy section
			 */
			aszSQL101 = "";
			// add vidCitizenStatus
			if ( aApplicantObj.getCitizenTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidCitizenStatus + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getCitizenTID() + 
					" AND vid = " + vidCitizenStatus + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setCitizenTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();// make sure duplicates don't break the system
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
				}
				itid=-1;
			}
	
			// add getDegreeProgTID
			if ( aApplicantObj.getDegreeProgTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidCVDegreeProgram + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getDegreeProgTID() + 
					" AND vid = " + vidCVDegreeProgram + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setDegreeProgTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
				}
				itid=-1;
			}
			// add getInternLengthTID
			if ( aApplicantObj.getInternLengthTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidInternshipLength + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getInternLengthTID() + 
					" AND vid = " + vidInternshipLength + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setInternLengthTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
			
			// add getPosPrefTID
			if ( aApplicantObj.getPosPrefTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidInternPosType + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getPosPrefTID() + 
					" AND vid = " + vidInternPosType + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setPosPrefTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
			// add getSkillsTID
			if ( aApplicantObj.getSkillsTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidSpecialSkills + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getSkillsTID() + 
					" AND vid = " + vidSpecialSkills + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setSkillsTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
			// add getSourceTID
			if ( aApplicantObj.getSourceTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
					vidSource + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getSourceTID() + 
					" AND vid = " + vidSource + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setSourceTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
			// add getPopulPrefTID
			if ( aApplicantObj.getPopulPrefTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidWorkPopulationPref + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getPopulPrefTID() + 
					" AND vid = " + vidWorkPopulationPref + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setPopulPrefTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
			// add getInternTypeTID
			
			// iterate through all the getInternTypeTIDsArray and add them in
			a_iContainer = aApplicantObj.getInternTypeTIDsArray(); 
			iTmpSize = a_iContainer.length; 
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
					"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
					vidInternshipType + " ) ";
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}

			for(int i=0; i< iTmpSize; i++){
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  a_iContainer[i] + 
					" AND vid = " + vidInternshipType + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setInternTypeTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();
					if(0 != iRetCode && 1062!=iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
				}
				itid=-1;
			}
			// add getOPPWorkEnvironTID
			if ( aApplicantObj.getWorkEnvironTID() > 0 ){
				// delete occurrences of this node with the taxonomy within the opportunity vocabularies
				// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
				aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + inid +" AND  tid IN " +
						"(SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid = " + 
						vidWorkEnvironment + " ) ";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(Qry1); gives null pointer exception -  2009-04-15 - fixed to use ExecutePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aApplicantObj.getWorkEnvironTID() + 
					" AND vid = " + vidWorkEnvironment + " ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-04-09 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
		            ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("tid");
					aApplicantObj.setWorkEnvironTID(itid);
					// add to um_term_node
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + itid + "," + ivid + " )";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-04-09 - fixed to use prepqry's so it doesn't crash after other prepqry's
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
	//				iRetCode=-1;
				} else {
					itid=-1;
					// The option the user chose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
			}
		}

		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		return iRetCode;
	}
	// end-of method editApplicaitonInDB()


    /**
	* delete application record
	*/
	public int deleteApplicationInDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj ){
		int iRetCode=0;
		String Qry1;
		int ilid=-1;
		//int inid=-1, ivid=-1;
		MethodInit("deleteApplicationInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aApplicantObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		/**
		 * *************** START taxonomy section ***********************
		 */
		// delete ALL occurrences of the taxonomy for the given node
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + aApplicantObj.getNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 */

		aszSQL101="DELETE FROM " + aszDrupalDB + "location " +
				"WHERE lid = " + aApplicantObj.getLID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_node
		aszSQL101="DELETE FROM " + aszDrupalDB + "node " +
				"WHERE nid = " + aApplicantObj.getNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from node_revisions
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "node_revisions " +
				"WHERE nid = "+ aApplicantObj.getNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from cck
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_type_cvintern_applicant " +
				"WHERE nid = "+ aApplicantObj.getNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method deleteApplicationInDB()

	/**
	* select a list of applications from drupal tables
	*/
	public int getApplicationsDBList(ABREDBConnection pConn, ArrayList aListObj, int iType, int iIdNum, boolean feeds){
		MethodInit("getApplicationsDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
		int iRetCode=0, iTemp=0;
		String mainDB=aszDrupalDB;
		String aszSQL2 = "SELECT cckApp.vid,cckApp.nid, n.title, n.uid, n.created, FROM_UNIXTIME(n.created) applic_date, " +
				"field_cvintern_first_name_value,field_cvintern_last_name_value,field_cvintern_email_value,field_cvintern_phone_value," +
				"field_cvintern_chris_value,field_cvintern_18_value,field_cvintern_diploma_value," +
				"field_cvintern_length_commitment_value,field_cvintern_bachelor_attendin_value,field_cvintern_testimony_value," +
				"field_cvintern_geo_pref_value,field_cvintern_interest_reason_value,field_cvintern_language_value," +
				"field_cvintern_church_value,field_cvintern_major_pref_value,field_cvintern_has_bachelor_value," +
				"field_cvintern_credits_value,field_cvintern_career_goals_value,field_cvintern_hourly_commitment_value," +
				"field_cvintern_livable_stipend_value,field_cvintern_livable_stip_expl_value," +
				"field_cvintern_criminal_record_value,field_cvintern_criminal_desc_value," +
				"field_cvintern_housing_value,field_cvintern_service_site_value,field_cvintern_location_pref_value," +
				"field_cvintern_start_time_value," +
				"field_cvintern_forward_resume_value,field_cvintern_webcam_value,field_cvintern_skype_value," +
				"field_cvintern_intern_length_value,field_pastoral_ref_name_value,field_pastoral_ref_church_value," +
				"field_pastoral_ref_phone_value, " +
				"field_pastoral_ref_email_value,field_prof_ref_name_value,field_prof_ref_org_value, " + 
				"field_prof_ref_phone_value, field_prof_ref_email_value, " +
				"field_cvintern_location_lid, " +
				"field_last_active_highschool_value,field_cvdegree_carreergoals_value,  " +
				"field_cvintern_gender_value,field_cvintern_dob_value,field_cvintern_screened_value,field_ineligibility_reason_value" +
				",loc.street, loc.city, loc.province, loc.postal_code, loc.country " +
				" FROM  " + mainDB + "node n, " + mainDB + "content_type_cvintern_applicant cckApp, " + 
				mainDB + "location loc " +
				" WHERE n.nid=cckApp.nid " +
				" AND cckApp.field_cvintern_location_lid=loc.lid ";

        switch( iType ){
    		case ApplicationInfoDTO.LOADBY_ORG :
//				aszSQL2 += " AND field_cvintern_org_nid_nid=" + iIdNum;
	        	break;
    		case ApplicationInfoDTO.LOADBY_OPP :
//				aszSQL2 += " AND field_cvintern_opp_nid_nid=" + iIdNum;
	        	break;
    		case ApplicationInfoDTO.LOADBY_APPLICANT_USER :
				aszSQL2 += " AND n.uid=" + iIdNum;
	        	break;
    		case ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER_APPLICANT :
				aszSQL2 += " AND field_cvintern_screened_value>0 ";
	        	break;
    		case ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER :
    			aszSQL2 = "SELECT cckApp.vid,cckApp.nid, n.title, n.uid, n.created, FROM_UNIXTIME(n.created) applic_date, " +
    					"field_cvintern_first_name_value,field_cvintern_last_name_value,field_cvintern_email_value,field_cvintern_phone_value," +
    					"field_cvintern_chris_value,field_cvintern_18_value,field_cvintern_diploma_value," +
    					"field_cvintern_length_commitment_value,field_cvintern_bachelor_attendin_value,field_cvintern_testimony_value," +
    					"field_cvintern_geo_pref_value,field_cvintern_interest_reason_value,field_cvintern_language_value," +
    					"field_cvintern_church_value,field_cvintern_major_pref_value,field_cvintern_has_bachelor_value," +
    					"field_cvintern_credits_value,field_cvintern_career_goals_value,field_cvintern_hourly_commitment_value," +
    					"field_cvintern_livable_stipend_value,field_cvintern_livable_stip_expl_value," +
    					"field_cvintern_criminal_record_value,field_cvintern_criminal_desc_value," +
    					"field_cvintern_housing_value,field_cvintern_service_site_value,field_cvintern_location_pref_value," +
    					"field_cvintern_start_time_value," +
    					"field_cvintern_forward_resume_value,field_cvintern_webcam_value,field_cvintern_skype_value," +
    					"field_cvintern_intern_length_value,field_pastoral_ref_name_value,field_pastoral_ref_church_value," +
    					"field_pastoral_ref_phone_value, " +
    					"field_pastoral_ref_email_value,field_prof_ref_name_value,field_prof_ref_org_value, " + 
    					"field_prof_ref_phone_value, field_prof_ref_email_value, " +
    					"field_cvintern_location_lid, " +
    					"field_last_active_highschool_value,field_cvdegree_carreergoals_value,  " +
    					"field_cvintern_gender_value,field_cvintern_dob_value,field_cvintern_screened_value,field_ineligibility_reason_value" +
    					", '' as city, '' as province, '' as country, '' as street, '' as postal_code " +
    					"FROM  " + mainDB + "node n, " + mainDB + "content_type_cvintern_applicant cckApp " +
    					" WHERE n.nid=cckApp.nid ";

// NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    	
    			// NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    			
    			// NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    			
    			// NOTE  NEED TO NOT JOIN location table, b/c that might not exist			
    			// NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    			
    			// NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    			
//NOTE  ::::::::::::::::::::::::::::::::::::::::::::::::::::    			
    			
				aszSQL2 += " AND field_cvintern_is_placed_value!=1 ";
				System.out.println("LOADBY_EMAIL_SCHEDULER - checks that intern hasn't been placed");				
				System.out.println("aszSQL2   "+aszSQL2);				
	        	break;
	        default:
				setErr("request type not supported");
//System.out.println("request type not supported");
	            return -1;
	    }
        
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-2;
		while(pConn.getNextResult()){
            iRetCode=0;
			ApplicationInfoDTO aApplicantObj = new ApplicationInfoDTO();
			aApplicantObj.setNID(pConn.getDBInt("nid"));
			aApplicantObj.setVID(pConn.getDBInt("vid"));
			aApplicantObj.setUID(pConn.getDBInt("uid"));
			aApplicantObj.setCreateDtNum(pConn.getDBInt("created"));
			aApplicantObj.setCreateDt(pConn.getDBDate("applic_date"));
			aApplicantObj.setTitle(pConn.getDBString("title"));
			aApplicantObj.setNameFirst(pConn.getDBString("field_cvintern_first_name_value"));
			aApplicantObj.setNameLast(pConn.getDBString("field_cvintern_last_name_value"));
			aApplicantObj.setEmailAddr(pConn.getDBString("field_cvintern_email_value"));
			aApplicantObj.setPhone(pConn.getDBString("field_cvintern_phone_value"));
			aApplicantObj.setChristian(pConn.getDBString("field_cvintern_chris_value"));
			aApplicantObj.setAgeRequirement(pConn.getDBString("field_cvintern_18_value"));
			aApplicantObj.setDiploma(pConn.getDBString("field_cvintern_diploma_value"));
			aApplicantObj.setTimeCommitAbility(pConn.getDBString("field_cvintern_length_commitment_value"));
			aApplicantObj.setBachelorsAttending(pConn.getDBString("field_cvintern_bachelor_attendin_value"));
			aApplicantObj.setTestimony(pConn.getDBString("field_cvintern_testimony_value"));
			aApplicantObj.setGeoPref(pConn.getDBString("field_cvintern_geo_pref_value"));
			aApplicantObj.setInternReason(pConn.getDBString("field_cvintern_interest_reason_value"));
			aApplicantObj.setLang(pConn.getDBString("field_cvintern_language_value"));
			aApplicantObj.setChurch(pConn.getDBString("field_cvintern_church_value"));
			aApplicantObj.setMajor(pConn.getDBString("field_cvintern_major_pref_value"));
			aApplicantObj.setHasBachelors(pConn.getDBString("field_cvintern_has_bachelor_value"));
			aApplicantObj.setCredits(pConn.getDBString("field_cvintern_credits_value"));
			aApplicantObj.setCareerGoals(pConn.getDBString("field_cvintern_career_goals_value"));
			aApplicantObj.setHrlyCommit(pConn.getDBString("field_cvintern_hourly_commitment_value"));
			aApplicantObj.setLivableStipend(pConn.getDBString("field_cvintern_livable_stipend_value"));
			aApplicantObj.setLivableStipendExpl(pConn.getDBString("field_cvintern_livable_stip_expl_value"));
			aApplicantObj.setCrimRecord(pConn.getDBString("field_cvintern_criminal_record_value"));
			aApplicantObj.setCrimDescrip(pConn.getDBString("field_cvintern_criminal_desc_value"));
			aApplicantObj.setHousing(pConn.getDBString("field_cvintern_housing_value"));
			aApplicantObj.setServiceSite(pConn.getDBString("field_cvintern_service_site_value"));
			aApplicantObj.setLocPrefInfo(pConn.getDBString("field_cvintern_location_pref_value"));
			aApplicantObj.setStartTime(pConn.getDBString("field_cvintern_start_time_value"));
			aApplicantObj.setForwardResume(pConn.getDBString("field_cvintern_forward_resume_value"));
			aApplicantObj.setWebcam(pConn.getDBString("field_cvintern_webcam_value"));
			aApplicantObj.setSkype(pConn.getDBString("field_cvintern_skype_value"));
			aApplicantObj.setInternLength(pConn.getDBString("field_cvintern_intern_length_value"));
			aApplicantObj.setPastoralRef(pConn.getDBString("field_pastoral_ref_name_value"));
			aApplicantObj.setPastoralRefChurch(pConn.getDBString("field_pastoral_ref_church_value"));
			aApplicantObj.setPastoralRefPhone(pConn.getDBString("field_pastoral_ref_phone_value"));
			aApplicantObj.setPastoralRefEmail(pConn.getDBString("field_pastoral_ref_email_value"));
			aApplicantObj.setProfRef(pConn.getDBString("field_prof_ref_name_value"));
			aApplicantObj.setProfRefOrg(pConn.getDBString("field_prof_ref_org_value"));
			aApplicantObj.setProfRefPhone(pConn.getDBString("field_prof_ref_phone_value"));
			aApplicantObj.setProfRefEmail(pConn.getDBString("field_prof_ref_email_value"));
			aApplicantObj.setGender(pConn.getDBString("field_cvintern_gender_value"));
			aApplicantObj.setDOBDt(pConn.getDBDate("field_cvintern_dob_value"));
			aApplicantObj.setLastYrActiveHS(pConn.getDBString("field_last_active_highschool_value"));
			aApplicantObj.setCVDegreeCareerGoals(pConn.getDBString("field_cvdegree_carreergoals_value"));
			aApplicantObj.setIneligibilityReason(pConn.getDBString("field_ineligibility_reason_value"));
			aApplicantObj.setScreened(pConn.getDBInt("field_cvintern_screened_value"));
			if(iType==ApplicationInfoDTO.LOADBY_EMAIL_SCHEDULER){
				aApplicantObj.setAddrLine1(pConn.getDBString("street"));
				aApplicantObj.setAddrCity(pConn.getDBString("city"));
				aApplicantObj.setAddrStateprov(pConn.getDBString("province"));
				aApplicantObj.setAddrPostalcode(pConn.getDBString("postal_code"));
				aApplicantObj.setAddrCountryName(pConn.getDBString("country"));
			}
			
			// NOTE::::::::::::    DO we need any taxonomy information for this select list????
			aListObj.add(aApplicantObj);
		}
		return iRetCode;
	}
	// end-of method getApplicationsDBList()

	/**
	* load one opportunities from table org_opportunitiesinfo
	*/
	public int loadOneApplicationFromDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj, int iIdNum, int iKeyId, int iType ){
		int iRetCode=0;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		String aszTemp="", aszNID="",aszSQLtemp ="";
        MethodInit("loadOneApplicationFromDB");
		if(null == pConn) return -1;
		if(null == aApplicantObj) return -1;
		boolean feed=false, b_natlassoc=false;
		String mainDB=aszDrupalDB;
		String aszSQL101 = "SELECT cckApp.vid,cckApp.nid, n.title, n.uid, n.created, field_cvintern_apply_dt_value  applic_date, " +
				"field_cvintern_first_name_value,field_cvintern_last_name_value,field_cvintern_email_value,field_cvintern_phone_value," +
				"field_cvintern_chris_value,field_cvintern_18_value,field_cvintern_diploma_value," +
				"field_cvintern_length_commitment_value,field_cvintern_bachelor_attendin_value,field_cvintern_testimony_value," +
				"field_cvintern_geo_pref_value,field_cvintern_interest_reason_value,field_cvintern_language_value," +
				"field_cvintern_church_value,field_cvintern_major_pref_value,field_cvintern_has_bachelor_value," +
				"field_cvintern_credits_value,field_cvintern_career_goals_value,field_cvintern_hourly_commitment_value," +
				"field_cvintern_livable_stipend_value,field_cvintern_livable_stip_expl_value," +
				"field_cvintern_criminal_record_value,field_cvintern_criminal_desc_value," +
				"field_cvintern_housing_value,field_cvintern_service_site_value,field_cvintern_location_pref_value," +
				"field_cvintern_start_time_value," +
				"field_cvintern_forward_resume_value,field_cvintern_webcam_value,field_cvintern_skype_value," +
				"field_cvintern_intern_length_value,field_pastoral_ref_name_value,field_pastoral_ref_church_value," +
				"field_pastoral_ref_phone_value, " +
				"field_pastoral_ref_email_value,field_prof_ref_name_value,field_prof_ref_org_value, " + 
				"field_prof_ref_phone_value, field_prof_ref_email_value, " +
				"field_cvintern_location_lid," +
				"field_cvintern_gender_value,field_cvintern_dob_value,field_cvintern_screened_value," +
				"field_ineligibility_reason_value,li.lid, " +
				"field_last_active_highschool_value,field_cvdegree_carreergoals_value, field_screening_interv_notes_value " +
				",field_cvintern_resume_filepath_value,field_cvintern_drupal_uprofile_url " + 
				",loc.street, loc.city, loc.province, loc.postal_code, loc.country " +
				" FROM  " + mainDB + "node n, " + mainDB + "content_type_cvintern_applicant cckApp, " + 
				mainDB + "location_instance li, " + 	mainDB + "location loc " +
				" WHERE n.nid=cckApp.nid " +
				" AND li.nid=cckApp.nid AND li.lid=loc.lid ";
        switch( iType ){
			case ApplicationInfoDTO.LOADBY_APPROVED_SITE :
				aszSQL101 += " AND n.nid=" + iIdNum ;
	        	break;
    		case ApplicationInfoDTO.LOADBY_ORG :
//    			aszSQL101 += " AND n.nid=" + iIdNum + " AND field_cvintern_org_nid_nid=" + iKeyId;
	        	break;
    		case ApplicationInfoDTO.LOADBY_OPP :
//    			aszSQL101 += " AND n.nid=" + iIdNum + " AND field_cvintern_opp_nid_nid=" + iKeyId;
	        	break;
    		case ApplicationInfoDTO.LOADBY_APPLICANT_USER :
    			if(iIdNum>0){
    				aszSQL101 += " AND n.nid=" + iIdNum + " AND n.uid=" + iKeyId;
//System.out.println("ApplicationDBDAO 2448 - aszSQL101 is " + aszSQL101) ;   				
    			}else{
    				aszSQL101 += "  AND n.uid=" + iKeyId;
//System.out.println("ApplicationDBDAO 2451 - aszSQL101 is " + aszSQL101) ;   				
    			}
	        	break;
	        default:
				setErr("request type not supported");
	            return -1;
	    }

		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
//System.out.println("ApplicationDBDAO line 2491 aszSQL101 is "+aszSQL101);		
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-111; // no results

		
		if(pConn.getNextResult()){
            iRetCode=0;
			aApplicantObj.setNID(pConn.getDBInt("nid"));
			aApplicantObj.setVID(pConn.getDBInt("vid"));
			aApplicantObj.setUID(pConn.getDBInt("uid"));
			aApplicantObj.setCreateDtNum(pConn.getDBInt("created"));
			aApplicantObj.setCreateDt(pConn.getDBDate("applic_date"));
			aApplicantObj.setApplyDt(pConn.getDBDate("applic_date"));
			aApplicantObj.setTitle(pConn.getDBString("title"));
			aApplicantObj.setNameFirst(pConn.getDBString("field_cvintern_first_name_value"));
			aApplicantObj.setNameLast(pConn.getDBString("field_cvintern_last_name_value"));
			aApplicantObj.setEmailAddr(pConn.getDBString("field_cvintern_email_value"));
			aApplicantObj.setPhone(pConn.getDBString("field_cvintern_phone_value"));
			aApplicantObj.setChristian(pConn.getDBString("field_cvintern_chris_value"));
			aApplicantObj.setAgeRequirement(pConn.getDBString("field_cvintern_18_value"));
			aApplicantObj.setDiploma(pConn.getDBString("field_cvintern_diploma_value"));
			aApplicantObj.setTimeCommitAbility(pConn.getDBString("field_cvintern_length_commitment_value"));
			aApplicantObj.setBachelorsAttending(pConn.getDBString("field_cvintern_bachelor_attendin_value"));
			aApplicantObj.setTestimony(pConn.getDBString("field_cvintern_testimony_value"));
			aApplicantObj.setGeoPref(pConn.getDBString("field_cvintern_geo_pref_value"));
			aApplicantObj.setInternReason(pConn.getDBString("field_cvintern_interest_reason_value"));
			aApplicantObj.setLang(pConn.getDBString("field_cvintern_language_value"));
			aApplicantObj.setChurch(pConn.getDBString("field_cvintern_church_value"));
			aApplicantObj.setMajor(pConn.getDBString("field_cvintern_major_pref_value"));
			aApplicantObj.setHasBachelors(pConn.getDBString("field_cvintern_has_bachelor_value"));
			aApplicantObj.setCredits(pConn.getDBString("field_cvintern_credits_value"));
			aApplicantObj.setCareerGoals(pConn.getDBString("field_cvintern_career_goals_value"));
			aApplicantObj.setHrlyCommit(pConn.getDBString("field_cvintern_hourly_commitment_value"));
			aApplicantObj.setLivableStipend(pConn.getDBString("field_cvintern_livable_stipend_value"));
			aApplicantObj.setLivableStipendExpl(pConn.getDBString("field_cvintern_livable_stip_expl_value"));
			aApplicantObj.setCrimRecord(pConn.getDBString("field_cvintern_criminal_record_value"));
			aApplicantObj.setCrimDescrip(pConn.getDBString("field_cvintern_criminal_desc_value"));
			aApplicantObj.setHousing(pConn.getDBString("field_cvintern_housing_value"));
			aApplicantObj.setServiceSite(pConn.getDBString("field_cvintern_service_site_value"));
			aApplicantObj.setLocPrefInfo(pConn.getDBString("field_cvintern_location_pref_value"));
			aApplicantObj.setStartTime(pConn.getDBString("field_cvintern_start_time_value"));
			aApplicantObj.setForwardResume(pConn.getDBString("field_cvintern_forward_resume_value"));
			aApplicantObj.setWebcam(pConn.getDBString("field_cvintern_webcam_value"));
			aApplicantObj.setSkype(pConn.getDBString("field_cvintern_skype_value"));
			aApplicantObj.setInternLength(pConn.getDBString("field_cvintern_intern_length_value"));
			
			aApplicantObj.setScreened(pConn.getDBInt("field_cvintern_screened_value"));

			aApplicantObj.setIneligibilityReason(pConn.getDBString("field_ineligibility_reason_value"));
			
			aApplicantObj.setResumeFilePath(pConn.getDBString("field_cvintern_resume_filepath_value"));
			aApplicantObj.setUserProfileLink(pConn.getDBString("field_cvintern_drupal_uprofile_url"));
			
			aApplicantObj.setPastoralRef(pConn.getDBString("field_pastoral_ref_name_value"));
			aApplicantObj.setPastoralRefChurch(pConn.getDBString("field_pastoral_ref_church_value"));
			aApplicantObj.setPastoralRefPhone(pConn.getDBString("field_pastoral_ref_phone_value"));
			aApplicantObj.setPastoralRefEmail(pConn.getDBString("field_pastoral_ref_email_value"));
			aApplicantObj.setProfRef(pConn.getDBString("field_prof_ref_name_value"));
			aApplicantObj.setProfRefOrg(pConn.getDBString("field_prof_ref_org_value"));
			aApplicantObj.setProfRefPhone(pConn.getDBString("field_prof_ref_phone_value"));
			aApplicantObj.setProfRefEmail(pConn.getDBString("field_prof_ref_email_value"));
			aApplicantObj.setGender(pConn.getDBString("field_cvintern_gender_value"));
			aApplicantObj.setDOBDt(pConn.getDBDate("field_cvintern_dob_value"));
			aApplicantObj.setLastYrActiveHS(pConn.getDBString("field_last_active_highschool_value"));
			aApplicantObj.setCVDegreeCareerGoals(pConn.getDBString("field_cvdegree_carreergoals_value"));
			aApplicantObj.setScreeningIntervNotes(pConn.getDBString("field_screening_interv_notes_value"));
			aApplicantObj.setLID(pConn.getDBInt("field_cvintern_location_lid"));
			aApplicantObj.setAddrLine1(pConn.getDBString("street"));
			aApplicantObj.setAddrCity(pConn.getDBString("city"));
			aApplicantObj.setAddrStateprov(pConn.getDBString("province"));
			aApplicantObj.setAddrPostalcode(pConn.getDBString("postal_code"));
			aApplicantObj.setAddrCountryName(pConn.getDBString("country"));
		}


		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************


		if(aApplicantObj.getNID()>0){

			
			/** Orgs Interested In */
			aszSQL101 = " SELECT   field_cvintern_org_nid , title  FROM " + 
				aszDrupalDB + "content_field_cvintern_org o, " + aszDrupalDB + "node n " +
				"WHERE o.field_cvintern_org_nid=n.nid AND o.nid=" + aApplicantObj.getNID() + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			index=0;
			a_iTemp= new int[50];
			while(pConn.getNextResult() && index < 50){
	            iRetCode=0;
	            iTemp=pConn.getDBInt("field_cvintern_org_nid");
	            aszTemp=pConn.getDBString("title");
				a_aszTemp[index] = aszTemp;
				a_iTemp[index]=iTemp;
				index++;
			}
			a_iContainer= new int[index];//index-1];//???
			for(int i=0; i<index; i++){
				a_iContainer[i]=a_iTemp[i];
			}
			if(a_iContainer.length>0) aApplicantObj.setORGNIDsArray(a_iContainer);
			
			a_aszContainer= new String[index];
			for(int i=0; i<index; i++){
				a_aszContainer[i]=a_aszTemp[i];
			}
			if(a_aszContainer.length>0) aApplicantObj.setORGNamesArray(a_aszContainer);

			
			/** Opps Interested In */
			aszSQL101 = " SELECT   field_cvintern_opp_nid , title  FROM " + 
				aszDrupalDB + "content_field_cvintern_opp o, " + aszDrupalDB + "node n " +
				"WHERE o.field_cvintern_opp_nid=n.nid AND o.nid=" + aApplicantObj.getNID() + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			index=0;
			a_iTemp= new int[50];
			while(pConn.getNextResult() && index < 50){
	            iRetCode=0;
	            iTemp=pConn.getDBInt("field_cvintern_opp_nid");
	            aszTemp=pConn.getDBString("title");
				a_aszTemp[index] = aszTemp;
				a_iTemp[index]=iTemp;
				index++;
			}
			a_iContainer= new int[index];//index-1];//???
			for(int i=0; i<index; i++){
				a_iContainer[i]=a_iTemp[i];
			}
			if(a_iContainer.length>0) aApplicantObj.setOPPNIDsArray(a_iContainer);
			
			a_aszContainer= new String[index];
			for(int i=0; i<index; i++){
				a_aszContainer[i]=a_aszTemp[i];
			}
			if(a_aszContainer.length>0) aApplicantObj.setOPPTitlesArray(a_aszContainer);

			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidCitizenStatus + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setCitizenTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidCitizenStatus + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setCitizenTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidCVDegreeProgram + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setDegreeProgTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidInternshipLength + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setInternLengthTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidInternPosType + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setPosPrefTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidSpecialSkills + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setSkillsTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidSource + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setSourceTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidWorkPopulationPref + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setPopulPrefTID(pConn.getDBInt("tid"));
			}
	
			// 
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
					mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
					"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidInternshipType + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			//iRetCode=-1;
			index=0;
			a_iTemp= new int[50];
			while(pConn.getNextResult() && index < 50){
	            iRetCode=0;
	            iTemp=pConn.getDBInt("tid");
	            aszTemp=pConn.getDBString("name");
				a_iTemp[index]=iTemp;
				a_aszTemp[index] = aszTemp;
				index++;
			}
			a_iContainer= new int[index];//index-1];//???
			for(int i=0; i<index; i++){
				a_iContainer[i]=a_iTemp[i];
			}
			if(a_iContainer.length>0) aApplicantObj.setInternTypeTIDsArray(a_iContainer);
			a_aszContainer= new String[index];
			for(int i=0; i<index; i++){
				a_aszContainer[i]=a_aszTemp[i];
			}
			if(a_aszContainer.length>0) aApplicantObj.setInternTypesArray(a_aszContainer);
			
			iRetCode=0;
	
			// 
			aszSQL101=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid  FROM " + 
				mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
				"WHERE nr.nid=" + aApplicantObj.getNID() + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidWorkEnvironment + " " ;
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			if(pConn.getNextResult()){
	            iRetCode=0;
				aApplicantObj.setWorkEnvironTID(pConn.getDBInt("tid"));
			}
		}

		// ===============================================
		// ========== END Organizational Data ============
		// ===============================================
		/**
		 * END TAXONOMY SECTION
		 */
//System.out.println("iRetCode is "+iRetCode);		
		return iRetCode;
	}
	// end-of method loadOneApplicationFromDB()


	/**
	* load one opportunities from table org_opportunitiesinfo
	*/
	public int loadOneApplicationFromFeedsDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj, int iIdNum ){
		int iRetCode=0;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		String aszTemp="", aszNID="",aszSQLtemp ="";
        MethodInit("loadOneApplicationFromFeedsDB");
		if(null == pConn) return -1;
		if(null == aApplicantObj) return -1;
		boolean feed=false, b_natlassoc=false;
		String mainDB=aszDrupalDB;
		String aszSQL101 = "SELECT sfid, applic_vid,applic_nid, email,applic_created,applic_apply_dt,applic_first_name,applic_last_name,title," +
				"applic_loc_street,applic_loc_city,applic_loc_province,applic_loc_postal_code,applic_loc_country,phone," +
				"is_christian,age_requirement,diploma_status,commitment_length,attending_bachelors,testimony,geo_pref,interest_reason,language," +
				"attends_church,major,has_bachelors,credits,career_goals,hrly_commit,livable_stipend,livable_stipend_expl,crim_record,crim_desc," +
				"housing,service_site,loc_pref,start_time,webcam,skype,intern_length_field,pastoral_ref,pastoral_ref_church,pastoral_ref_phone,pastoral_ref_email," +
				"prof_ref,prof_ref_org,prof_ref_phone,prof_ref_email,gender,dob,last_active_school,degree_career_goals," +
				"source,source_tids,intern_length,intern_length_tid,citizen,citizen_tid, intern_type,intern_type_tid,pos_pref,pos_pref_tid," +
				"special_skills,special_skills_tid, work_environ,work_environ_tid,work_pop_pref,work_pop_pref_tid,cvc_degree_prog,cvc_degree_prog_tid," +
				"applic_longitude, applic_latitude,forward_resume,appl_lid," +
				"applic_changed,sites_req,cvintern_is_placed,cvintern_screened,cvcintern_applicant," +
				"ad_source,ad_campaign,ad_keywords,referer_url,status,res_file," +
				"age_range,credits_range" +
			" FROM  " + aszSocialGraphDB + "tbl_applic_resume_joined n " +
			" WHERE  applic_nid=" + iIdNum ;

		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-111; // no results

		
		if(pConn.getNextResult()){
            iRetCode=0;

//			aApplicantObj.setSource(pConn.getDBString("sfid"));
			
			aApplicantObj.setNID(pConn.getDBInt("applic_nid"));
			aApplicantObj.setVID(pConn.getDBInt("applic_vid"));
			aApplicantObj.setCreateDtNum(pConn.getDBInt("applic_created"));
			aApplicantObj.setCreateDt(pConn.getDBDate("applic_apply_dt"));
			aApplicantObj.setApplyDt(pConn.getDBDate("applic_apply_dt"));
			aApplicantObj.setTitle(pConn.getDBString("title"));
			aApplicantObj.setNameFirst(pConn.getDBString("applic_first_name"));
			aApplicantObj.setNameLast(pConn.getDBString("applic_last_name"));
			aApplicantObj.setEmailAddr(pConn.getDBString("email"));
			aApplicantObj.setPhone(pConn.getDBString("phone"));
			aApplicantObj.setChristian(pConn.getDBString("is_christian"));
			aApplicantObj.setAgeRequirement(pConn.getDBString("age_requirement"));
			aApplicantObj.setDiploma(pConn.getDBString("diploma_status"));
			aApplicantObj.setTimeCommitAbility(pConn.getDBString("commitment_length"));
			aApplicantObj.setBachelorsAttending(pConn.getDBString("attending_bachelors"));
			aApplicantObj.setTestimony(pConn.getDBString("testimony"));
			aApplicantObj.setGeoPref(pConn.getDBString("geo_pref"));
			aApplicantObj.setInternReason(pConn.getDBString("interest_reason"));
			aApplicantObj.setLang(pConn.getDBString("language"));
			aApplicantObj.setChurch(pConn.getDBString("attends_church"));
			aApplicantObj.setMajor(pConn.getDBString("major"));
			aApplicantObj.setHasBachelors(pConn.getDBString("has_bachelors"));
			aApplicantObj.setCredits(pConn.getDBString("credits"));
			aApplicantObj.setCareerGoals(pConn.getDBString("career_goals"));
			aApplicantObj.setHrlyCommit(pConn.getDBString("hrly_commit"));
			aApplicantObj.setLivableStipend(pConn.getDBString("livable_stipend"));
			aApplicantObj.setLivableStipendExpl(pConn.getDBString("livable_stipend_expl"));
			aApplicantObj.setCrimRecord(pConn.getDBString("crim_record"));
			aApplicantObj.setCrimDescrip(pConn.getDBString("crim_desc"));
			aApplicantObj.setHousing(pConn.getDBString("housing"));
			aApplicantObj.setServiceSite(pConn.getDBString("service_site"));
			aApplicantObj.setLocPrefInfo(pConn.getDBString("loc_pref"));
			aApplicantObj.setStartTime(pConn.getDBString("start_time"));
			aApplicantObj.setForwardResume(pConn.getDBString("forward_resume"));
			aApplicantObj.setWebcam(pConn.getDBString("webcam"));
			aApplicantObj.setSkype(pConn.getDBString("skype"));
			aApplicantObj.setInternLength(pConn.getDBString("intern_length_field"));
			aApplicantObj.setPastoralRef(pConn.getDBString("pastoral_ref"));
			aApplicantObj.setPastoralRefChurch(pConn.getDBString("pastoral_ref_church"));
			aApplicantObj.setPastoralRefPhone(pConn.getDBString("pastoral_ref_phone"));
			aApplicantObj.setPastoralRefEmail(pConn.getDBString("pastoral_ref_email"));
			aApplicantObj.setProfRef(pConn.getDBString("prof_ref"));
			aApplicantObj.setProfRefOrg(pConn.getDBString("prof_ref_org"));
			aApplicantObj.setProfRefPhone(pConn.getDBString("prof_ref_phone"));
			aApplicantObj.setProfRefEmail(pConn.getDBString("prof_ref_email"));
			aApplicantObj.setGender(pConn.getDBString("gender"));
			aApplicantObj.setDOBDt(pConn.getDBDate("dob"));
			aApplicantObj.setLastYrActiveHS(pConn.getDBString("last_active_school"));
			aApplicantObj.setCVDegreeCareerGoals(pConn.getDBString("degree_career_goals"));
			aApplicantObj.setLID(pConn.getDBInt("appl_lid"));
			aApplicantObj.setAddrLine1(pConn.getDBString("applic_loc_street"));
			aApplicantObj.setAddrCity(pConn.getDBString("applic_loc_city"));
			aApplicantObj.setAddrStateprov(pConn.getDBString("applic_loc_province"));
			aApplicantObj.setAddrPostalcode(pConn.getDBString("applic_loc_postal_code"));
			aApplicantObj.setAddrCountryName(pConn.getDBString("applic_loc_country"));
			
			
			aApplicantObj.setScreened(pConn.getDBInt("cvintern_screened"));
			
			aApplicantObj.setSFID(pConn.getDBString("sfid"));
			aApplicantObj.setResumeFilePath(pConn.getDBString("res_file"));
			
			aApplicantObj.setCitizenTID(pConn.getDBInt("citizen_tid"));
			aApplicantObj.setDegreeProgTID(pConn.getDBInt("cvc_degree_prog_tid"));
			aApplicantObj.setInternLengthTID(pConn.getDBInt("intern_length_tid"));
			aApplicantObj.setPosPrefTID(pConn.getDBInt("pos_pref_tid"));
			aApplicantObj.setSkillsTID(pConn.getDBInt("special_skills_tid"));
			aApplicantObj.setSourceTID(pConn.getDBInt("source_tids"));
			aApplicantObj.setPopulPrefTID(pConn.getDBInt("work_pop_pref_tid"));
			aApplicantObj.setWorkEnvironTID(pConn.getDBInt("work_environ_tid"));
			

			a_iContainer = new int[1];
			a_iContainer[0] = pConn.getDBInt("intern_type_tid");
			if(a_iContainer.length>0) aApplicantObj.setInternTypeTIDsArray(a_iContainer);
			a_aszContainer = new String[1];
			a_aszContainer[0] = pConn.getDBString("intern_type");
			if(a_aszContainer.length>0) aApplicantObj.setInternTypesArray(a_aszContainer);
		}

		return iRetCode;
	}
	// end-of method loadOneApplicationFromFeedsDB()



	public int getApplicMatchesList(ABREDBConnection pConn, ArrayList<ApplicationInfoDTO> aListObj){
		MethodInit("getApplicMatchesList");
		if(null == pConn) return -1;
		if(null == aListObj) return -3;
		int iRetCode=0;
		String aszSQL2 = "SELECT nid, field_cvintern_org_nid, vid, delta " +
				" FROM  " + aszDrupalDB + "content_field_cvintern_org ";
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-2;
		while(pConn.getNextResult()){
            iRetCode=0;
			ApplicationInfoDTO aApplicantObj = new ApplicationInfoDTO();
			aApplicantObj.setNID(pConn.getDBInt("nid"));
			aApplicantObj.setVID(pConn.getDBInt("vid"));
			aApplicantObj.setORGDelta(pConn.getDBInt("delta"));
			aApplicantObj.setORGNID(pConn.getDBInt("field_cvintern_org_nid"));
			aListObj.add(aApplicantObj);
		}
		return iRetCode;
	}
	// end-of method getApplicMatchesList()

	public int insertApplicMatchIntoDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicantObj ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		
		String aszSQL2 = "SELECT nid, field_cvintern_org_nid, vid, delta " +
				" FROM  " + aszDrupalDB + "content_field_cvintern_org_source_flag WHERE nid= "+aApplicantObj.getNID();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-2;
		int iMaxDelta=0,iCount=0;
		while(pConn.getNextResult()){
			iCount++;
			int iDelta = pConn.getDBInt("delta");
			if(iDelta>iMaxDelta){
				iMaxDelta=iDelta;
			}
		}
		if(iCount==0){
			iMaxDelta=0;
		}else{
			iMaxDelta++;
		}
		aApplicantObj.setORGDelta(iMaxDelta);

		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_cvintern_org_source_flag(nid,vid, delta,field_cvintern_org_nid,source ) " +
				"VALUES(?,?,?,?,? ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getORGDelta() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicantObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aApplicantObj.getSource() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return iRetCode;
	}

	/**
	* get Source of orgnid-applicnid pair
	*/
	public String getApplicMatchSource( ABREDBConnection pConn, int iOrgNID, int iApplicNID ){
		String aszSource=null;
		int iRetCode=0;
        MethodInit("getApplicMatchSource");
		if(null == pConn){
			setErr("null database connection");
			return null;
		}
		if(1 > iOrgNID){
			setErr("no iOrgNID entered");
			return null;
		}
		if(1 > iApplicNID){
			setErr("no iApplicNID entered");
			return null;
		}
		String aszSQL101 = " SELECT nid, field_cvintern_org_nid, source " +
			" FROM " + aszDrupalDB + "content_field_cvintern_org_source_flag  WHERE field_cvintern_org_nid = " + iOrgNID + " and nid = " + iApplicNID + " ";
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return null;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return null;
		}
		iRetCode=0;
		if(pConn.getNextResult()){
			aszSource=pConn.getDBString("source");
		}

		return aszSource;
	}
	// end-of method getMatchSource(ABREDBConnection, 

	



    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>


	/**
	* get PortalName of format
	*/
	public int PortalName( ABREDBConnection pConn, int iTid, int iNID ){
		int iRetCode=0, iIDType=0;
        MethodInit("PortalName");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(1 > iNID){
			setErr("no nid entered");
			return -1;
		}
		if(1 > iTid){
			setErr("no tid entered");
			return -1;
		}
		String aszSQL101 = " SELECT nid, tid, vid " +
			" FROM " + aszDrupalDB + "term_node WHERE tid = " + iTid + " and nid = " + iNID + " ";
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=0;
		if(pConn.getNextResult()){
			iIDType=pConn.getDBInt("nid");
		}
		if(iNID > 0 && iNID == iIDType){
			// it already exists, but only with the given node
            iRetCode=777; // exists, but only in referenct to the current node; no conflict
		}else{
			iRetCode=-1;
		}
		return iRetCode;
	}
	// end-of method PortalName(ABREDBConnection, OrganizationInfoDTO)()

	
	
	
	// indicate the database if necessary
	//private String aszDrupalDB = "urbmi5_drupal.um_";
	private static final String aszDrupalDB = "um_";
	private static final String aszDrupalDBname = "";
	private static final String aszDataDB = "urbmi5_data.";
	private static final String aszFeedsDB = "techmi5_drufeeds.";
	private static final String aszVolengDB = "techmi5_voleng.";
	private static final String aszSocialGraphDB = "techmi5_socgraph.";
	private static final String aszFeedsTable = "tbl_feeds";
	//private String aszVolengDB = "";
	private static final int vidSource=361, vidInternshipLength=362, vidInternshipType=363, vidCitizenStatus=364, vidInternPosType=365,
			vidSpecialSkills=366, vidWorkEnvironment=367, vidWorkPopulationPref=368, vidCVDegreeProgram=369;
	String aszDomMain = "www.christianvolunteering.org";//this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
	
}