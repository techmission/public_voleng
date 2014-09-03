package com.abrecorp.opensource.organization;

/**
* Code Generated DataStore Class
* For Table organizationinfo
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationDetailsInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.QuestionnaireDTO;
import com.abrecorp.opensource.dataobj.RequiredDocumentDTO;
import com.abrecorp.opensource.dataobj.SearchParms;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;
//import com.abrecorp.opensource.servlet.BaseServletABRE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class OrganizationDBDAO extends ABREBase {

	/**
	** Constructor
	*/
    public OrganizationDBDAO(){}



    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>
    //=== START Table org_opportunitiesinfo ===>

    /**
	* insert a row into table org_opportunitiesinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertOpportunityIntoDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, String aszRailsDB ){
		int iRetCode=0, iDeltaMax=0, index=0;
		String aszSQLdrupal101 = "";
//System.out.println("inside DBDAO create opp");
//System.out.println("aHeadObj.getOPPUID()  is "+aHeadObj.getOPPUID() );		
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
		
		/*
		 * add to um_content_field_volorg_opp_reference
		 */
		// grab the highest delta for this organization's opportunities (possible it will return no results, so this is taken care of by increasing deltamax
		// grab the delta
		Qry1 = "SELECT vid, nid FROM " + aszDrupalDB + "node WHERE type='organization' AND nid=" + aHeadObj.getORGNID() + " ORDER BY vid";
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			iOrgVID = pConn.getDBInt("vid");
		} 
		if(iOrgVID < 2){
			return -2;
		}

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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPDescription()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPDescription()) );
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
			ivid = pConn.getDBInt("LAST_INSERT_ID()");
			aHeadObj.setOPPVID(ivid);
		} else {
			itid=-1;
		}
		iRetCode=-1;
		
		
		// add to um_node
		// will be put in moderation by default, unless it is through the portal sites, and is set to private; ie not published, and not moderated
		int iModerate = 1;
		if(aHeadObj.getOPPPrivate()==1){
			// unpublished && NOT moderated
			iModerate=0;
		}
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate, title, uid, created, changed) " +
			"VALUES(" + ivid + ",'volunteer_opportunity','0','2','" + iModerate + "',?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPUID() );
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
			aHeadObj.setOPPNID(inid);
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPVID() );
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
				"VALUES("+ inid +", '0', 'all', '1', '0', '0') ";
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

		// add to um_auto_expire - set expiration to be iExpirationTime days from now
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "auto_expire(nid, expire) " +
				"VALUES("+ inid +",(UNIX_TIMESTAMP({fn now()}) + (86400 * " + iExpirationTime + ") ) ) "; // 24 hours * 3600 seconds = 86400
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPUID() );
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
		 * add to um_usermail - this user created the org, so by deafult they will receive the emails
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
			"VALUES(" + aHeadObj.getOPPUID()+ ",'voleng_opp_nid','" + aHeadObj.getOPPNID() + "',0,1," + aHeadObj.getORGNID() + "," + aHeadObj.getOPPNID() + ") ";
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
		// add to um_content_type_volunteer_opportunity
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_volunteer_opportunity" +
				"(vid,nid," +
				"field_volopp_requirements_value,field_volopp_faith_req_value,field_volopp_creed_value,field_volopp_references_value," +
				"field_volopp_num_vol_opp_value,field_volopp_num_served_by_org_value," +
				"field_volopp_hourly_commitmt_value," +
				"field_volopp_commit_per_value,field_volopp_bg_check_value," +
				"field_volopp_start_date_value," +
				"field_volopp_end_date_value,field_volopp_org_link_url,field_volopp_org_link_title," +
				"field_volopp_org_link_attributes," +
				"field_volopp_group_min_value,field_volopp_group_max_value," +
				"field_volopp_cost_pp_value," +
				"field_volopp_vols_needed_value," +
				"field_volopp_stipend_paid_value," +
				"field_volopp_cost_includes_value,field_volopp_details_value,field_volopp_app_deadline_value,field_volopp_cost_timeframe_value," +
				"field_volopp_org_name_value,field_volopp_serve_poor_value,field_volopp_subdomain_value,field_volopp_num_vol_org_value," +
				"field_volopp_volunteer_link_url,field_volopp_volunteer_link_title,field_volopp_volunteer_link_attributes," +
				"field_stm_references_value,field_volopp_org_reference_nid,field_org_nid_value, field_volopp_url_id_value, " + // stmreferences, orgnid, urlid
				"field_volopp_private_value,field_volopp_hq_or_offsite_value,field_volopp_questionnaire_type_value, " + 
				"field_resume_required_value, field_cover_letter_required_value, field_intern_type_value) " + // stmreferences, orgnid, urlid
				"VALUES("+ ivid +"," + inid + 
				",?,?,?,?" + // requirements, faith_requirements, creed, references
				",?,?" +// number_positions, vols_served_past_yr, 
				",?" + //  hrly_commit
				",?,?," +// commit_per, bckgrnd_chck, 
				"UNIX_TIMESTAMP(?)" + // start_dt,
				",UNIX_TIMESTAMP(?)," + // end_dt
				/*
				"?" + // start_dt,
				",?," + // end_dt
				*/
				"'http://"+aszDomMain+"/org/org" + aHeadObj.getORGurlID() + ".jsp'," + // org_url (ORG)
				//"'http://www.christianvolunteering.org/org/org" + aHeadObj.getOPPOrgNumber() + ".jsp'," + // org_url (ORG) --> legacy id URL
				"?" + // organization_link_title, *field_volopp_requirements_format*
				",'N;'" + // field_volopp_org_link_attributes
				",?,?" + //  grp_min, grp_max
				",?" + // cost_per_person
				",?" + // num_vol_needed, 
				",?" + // amnt_paid
				",?,?,UNIX_TIMESTAMP(?),?" + // above_costpymnt_includes, add_details, app_deadline, cost_per_value
				",?,?,?,?," + // field_volopp_org_name_value, field_volopp_serve_poor_value, subdomain, field_volopp_num_vol_org_value
				"'http://"+aszDomMain+"/org.do?method=processIWantToHelp1&orgnid=" + aHeadObj.getORGNID() + "&oppnid=" + aHeadObj.getOPPNID() + "'," +
				"'I Want to Volunteer!','N;',?,?" +
				",?,? " + // private, HQorOffSite 
				",?,?,?,?,?,?) "; 
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPRequirements()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPRequiredCodeType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPRequiredCodeDesc() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPReferenceReq() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNumVolOpp() ); // Number of Volunteers Who Have Served in This Position in the Past Year
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumServed() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDouble( aHeadObj.getOPPCommitHourly() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPCommitType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPBackgroundCheck() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPStartDtNum() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPEndDtNum() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		*/
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPStartDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPEndDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPGroupMin() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPGroupMax() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDouble( aHeadObj.getOPPCostUsd() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPVolsNeeded() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAmntPd() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPCostInclude()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPAddDetails()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPApplicDeadline() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPCostTerm() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOnethirdP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPSTMReferences()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPPrivate() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPHQorOffSite() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getQuestionnaireType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getResumeRequired() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCoverLetterRequired() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPInternType() );
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
		
		
		// grab the highest delta for this organization's opportunities (possible it will return no results, so this is taken care of by increasing deltamax
		// grab the delta
		Qry1 = "SELECT delta FROM " + aszDrupalDB + "content_field_volorg_opp_reference " +
				" WHERE vid=" + iOrgVID +" AND nid=" + aHeadObj.getORGNID() +
				" ORDER BY delta ASC";
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
		int iDelta=-1;
		boolean b_zeroDelta = false;
		int iDeltaLow = 0;
		iDeltaMax=-1;
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			iDelta = pConn.getDBInt("delta");
			if(iDelta == iDeltaLow){
				iDeltaLow++;
			}
			/*
			if(iDelta > iDeltaMax){
				iDeltaMax = iDelta;
			}
			*/
		} 
		/*
		if(iDeltaMax != iDeltaLow){
			iDeltaMax = iDeltaLow;
		}
		iDeltaMax++; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		*/
		iDeltaMax = iDeltaLow;
		/*
		 * add to um_content_field_volorg_opp_reference
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_volorg_opp_reference(vid, nid, delta, field_volorg_opp_reference_nid) " +
				"VALUES(" + iOrgVID + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + aHeadObj.getOPPNID() + " ) ";
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

		
		
		
		/*
		 * ADD a url_alias for this node; need to first test for an alias that does not yet exist
		 */
		// BRLO layer already took care of whitespace and special characters, etc in the URL
		// also has already scanned through and tested against um_variable where name like 'pathauto_ignore_words'
    	// 		--> s:180:"a,an, as,.........."
		// Here, we now just test to make sure that the alias does not yet exist (pathauto/url_alias/path_redirect)
		
		/*
		 * add URL alias
		 */
		if(aHeadObj.getOPPUrlAlias().length()>1){
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "url_alias " +
					"(src,dst) " +
					"VALUES('node/" + inid + "',?)"; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPUrlAlias() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				int ierror = 98769876;
				ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=0;
		}
		
		
		
		/*
		 * add to location
		 */
		// 2008-10-27 - update to location drupal module required change of code b/c db structure was altered
		
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location(street, additional, city, province, postal_code, " +
				"country, source, is_primary) " +
				"VALUES(?,?,?,?,?,?,0,0 ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrLine1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrLine2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrStateprov() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrPostalcode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrCountryName() );
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
			aHeadObj.setOPPLID(ilid);
		} else {
			itid=-1;
		}
		iRetCode=-1;

		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_instance(lid,nid,vid,genid) " +
				"VALUES(" + ilid + "," + inid + "," + ivid + ",'' ) ";
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
		iRetCode=-1;

		/**
		 * START taxonomy section
		 */
//System.out.println(" line 821 - start Taxonomy section in insertOpp");		
//System.out.println(" line 822 - start service areas in insertOpp");		
		// add Service Areas
		// add Service Areas Array
		if ( aHeadObj.getServiceAreasArray()!=null ){
//System.out.println (" service areas NOT null");			
			if ( aHeadObj.getServiceAreasArray().length > 0 ){
//System.out.println(" service areas length greater than 0");				
				index=0;
				int[] tempService = aHeadObj.getServiceAreasArray();
				for (int i=0; i<tempService.length; i++){
					int tempTID = tempService[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidService + " ";
//System.out.println("Qry1 is "+Qry1);					
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
//System.out.println("error on PrepQuery");						
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
//System.out.println("error on ExePrepQry");						
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getOPPNID() + "," + itid + "," + aHeadObj.getOPPVID() + " )";
//System.out.println("  has a value for tid;  aszSQLdrupal101 is  "+aszSQLdrupal101);						
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
						// The option the user choose for the SELECT returned no results
						//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
						//return -1;
					}
					itid=-1;
				}
			}
		}
		
//System.out.println(" line 882 - start focus areas in insertOpp");		
		// add Focus Areas (Kid, Group, Teen, Senior, Family)
		if ( ! aHeadObj.getOPPFocusAreas().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor1TID() + 
				") AND vid = " + vidVolInfo + " ";
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
				aHeadObj.setOPPGreatFor1TID(itid);
				aHeadObj.setOPPFocusAreas(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Focus Areas 2 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas2().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas2() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor2TID() + 
				") AND vid = " + vidVolInfo + " ";
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
				aHeadObj.setOPPGreatFor2TID(itid);
				aHeadObj.setOPPFocusAreas2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Focus Areas 3 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas3().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas3() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor3TID() + 
				") AND vid = " + vidVolInfo + " ";
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
				aHeadObj.setOPPGreatFor3TID(itid);
				aHeadObj.setOPPFocusAreas3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Focus Areas 4 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas4().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor4TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas4() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor4TID() + 
				") AND vid = " + vidVolInfo + " ";
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
				aHeadObj.setOPPGreatFor4TID(itid);
				aHeadObj.setOPPFocusAreas4(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Focus Areas 5 (Kid, Group, Teen, Senior, Family)
		if ( ! aHeadObj.getOPPFocusAreas5().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor5TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas5() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor5TID() + 
				") AND vid = " + vidVolInfo + " ";
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
				aHeadObj.setOPPGreatFor5TID(itid);
				aHeadObj.setOPPFocusAreas5(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		
//System.out.println(" line 1125 - start oppStatus (I think position type) in insertOpp");		
		// add opp Status - Local, Virtual, Short-term Missions, Work Study,...
		if ( ! aHeadObj.getOPPStatus().equalsIgnoreCase("") || aHeadObj.getOPPPositionTypeTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPStatus() ) + 
				"' OR tid=" +  aHeadObj.getOPPPositionTypeTID() + 
				") AND vid = " + vidPosType + " ";
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
				aHeadObj.setOPPPositionTypeTID(itid);
				aHeadObj.setOPPStatus(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ inid +"," + itid + "," + ivid + ",0 )";
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
		}
		itid=-1;
			
		
//System.out.println(" line 1176 - start date required in insertOpp");		
		// Date Required question
		if ( ! aHeadObj.getOPPDaterequired().equalsIgnoreCase("") || aHeadObj.getOPPDaterequiredTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPDaterequired() ) + 
				"' OR tid=" +  aHeadObj.getOPPDaterequiredTID() + 
				") AND vid = " + vidSchedDate + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPDaterequiredTID();
				aHeadObj.setOPPDaterequired(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
//System.out.println(" line 1225 - start languages in insertOpp");		
		// add Languages Spoken
		if ( ! aHeadObj.getOPPLanguages().equalsIgnoreCase("") || aHeadObj.getOPPLanguageTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPLanguages() ) + 
				"' OR tid=" +  aHeadObj.getOPPLanguageTID() + 
				") AND vid = " + vidLangSpoken + " ";
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
				aHeadObj.setOPPLanguageTID(itid);
				aHeadObj.setOPPLanguages(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		
//System.out.println(" line 1276 - start work study in insertOpp");		
		// add Work Study question
		if ( ! aHeadObj.getOPPWorkStudy().equalsIgnoreCase("") || aHeadObj.getOPPWorkStudyTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPWorkStudy() ) + 
				"' OR tid=" +  aHeadObj.getOPPWorkStudyTID() + 
				") AND vid = " + vidWorkStudy + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPWorkStudyTID();
				aHeadObj.setOPPWorkStudy(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				
				// insert into the Benefits Offered Taxonomy as well, if it's set to Yes
				if(itid == iWorkStudyYesTID){
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ inid +"," + iWorkStudyBenefitsTID + "," + ivid + " )";
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
				}
				
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Benefits - Room & Board question
		
		
		
//System.out.println(" Before Benefits Array.   ");		
		// add benefits Array
		if ( aHeadObj.getBenefitsArray()!=null ){
//System.out.println("   Benefits Array  is NOT NULL ");		
			if ( aHeadObj.getBenefitsArray().length > 0 ){
//System.out.println("  Benefits Array size is   "+aHeadObj.getBenefitsArray().length);		
				index=0;
				int[] tempIDs = aHeadObj.getBenefitsArray();
				for (int i=0; i<tempIDs.length; i++){
					int tempTID = tempIDs[index];
					index++;
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidBenefits + " ";
//System.out.println("  Make sure this TID is in the list of vocab Benefits offered Qry1 is "+Qry1);				
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
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getOPPNID() + "," + itid + "," + aHeadObj.getOPPVID() + " )";
//System.out.println("  add Benefits Offered tid "+itid+"  and vid "+aHeadObj.getOPPVID() +"  and nid "+aHeadObj.getOPPNID() );				
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
//System.out.println("                             failed on PrepQuery");							
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
							iRetCode=0;
					}else if(0 != iRetCode){
//System.out.println("                         failed on ExecutePrepQry");						
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=-1;
				}
				itid=-1;
			}
		}
//System.out.println("  finished Benefits Offered");	
		// Length of Trip question
		if ( ! aHeadObj.getOPPTripLength().equalsIgnoreCase("") || aHeadObj.getOPPTripLengthTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPTripLength() ) + 
				"' OR tid=" +  aHeadObj.getOPPTripLengthTID() + 
				") AND vid = " + vidTripLength + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPTripLengthTID();
				aHeadObj.setOPPTripLength(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// One-time or Ongoing opportunity
		if ( ! aHeadObj.getOPPFreq().equalsIgnoreCase("") || aHeadObj.getOPPFrequencyTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFreq() ) + 
				"' OR tid=" +  aHeadObj.getOPPFrequencyTID() + 
				") AND vid = " + vidPosFreq + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPFrequencyTID();
				aHeadObj.setOPPFreq(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// ================================================
		// ======== START Organizational Data =============
		// ================================================
		
		// add Organization's Denominational Affiliation
		if ( ! aHeadObj.getORGAffiliation().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGAffiliation() ) + 
				"' AND vid = " + vidDenomAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners) - for searching ease later
		if ( ! aHeadObj.getORGPartner().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner() ) + 
				"' AND vid = " + vidOrgAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner2().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner2() ) + 
				"' AND vid = " + vidOrgAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner3().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner3() ) + 
				"' AND vid = " + vidOrgAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner4().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner4() ) + 
				"' AND vid = " + vidOrgAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner5().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner5() ) + 
				"' AND vid = " + vidOrgAffil + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organization Member Type
		if ( ! aHeadObj.getORGMembertype().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGMembertype() ) + 
				"' AND vid = " + vidMemberType + " ";
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
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ inid +"," + itid + "," + ivid + ",0 )";
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
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		// ================================================
		// ========== END Organizational Data =============
		// ================================================

		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		 
		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()==1){
			int iCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT IGNORE INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getOPPNID() + "," + aHeadObj.getOPPUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -1;
			}else{
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -1;
				}else{
					iCount=1;
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = " INSERT IGNORE INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
		    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getOPPNID() + ","+iCount+") "; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    			//return -1;
		    		}
				}
			}
		}
		
		if(aHeadObj.getQuestionnaireType() != null &&
		   aHeadObj.getQuestionnaireType().equals("online") &&
		   aHeadObj.getQuestionnaireId() > 0) {
			aszSQLdrupal101 = " INSERT INTO " + aszRailsDB + "opportunities_questionnaires" +
		                      " (opportunity_nid, questionnaire_id, created_at, updated_at)" +
		                      " VALUES (" + aHeadObj.getOPPNID() + "," + aHeadObj.getQuestionnaireId() + ",NOW(),NOW())";
			iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
    		if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
    			ErrorsToLog();
    			return -1;
    		}
		}
		
		return updateRequiredDocs(pConn, aHeadObj);
	}
	// end-of method insertOpportunityIntoDB()

    /**
	* update opportunity record in table org_opportunitiesinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int editOpportunityInDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, String aszRailsDB ){
		int iRetCode=0, index=0;
		String aszSQLdrupal101, Qry1;
		//int inid=-1, ivid=-1, itid=-1, ilid=-1;
		int ilid=-1;
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

		/*
		 * edit to location - has to preceed others, just like taxonomy, b/c RunQry can't be used after PrepQuery - 2008-10-28
		 */
		// grab the lid from um_location_instance to delete from um_location
		Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_instance WHERE vid=" + aHeadObj.getOPPVID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("lid");


			aszSQLdrupal101="UPDATE " + aszDrupalDB + "location SET street=?, additional=?, city=?, province=?, postal_code=?, " +
					"country=? WHERE lid = " + ilid;
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrLine1() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrLine2() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrCity() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrStateprov() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrPostalcode() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAddrCountryName() );
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
		
		} else {
		}
		
		/*
		 * URL alias
		 */
		if(aHeadObj.getOPPUrlAlias().length() > 1){ // if it stayed the same, this was cleared in processEditOpp in OrganizationActions
			if(!(aHeadObj.getOPPUrlAlias() == aHeadObj.getOPPUrlAliasOrig()) ){
				// 1. do an insert into path_redirect of urlaliasorig
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "path_redirect " +
						"(redirect,source,type,last_used,query,fragment) " +
						"VALUES('node/" + aHeadObj.getOPPNID() + "',?,301,UNIX_TIMESTAMP({fn now()}),'','')"; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getOPPUrlAliasOrig() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=0;

				// 2. do an update of url_alias table set dst = urlalias where dst like urlaliasorig
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "url_alias " +
						" SET dst=? " +
						" WHERE src LIKE 'node/" + aHeadObj.getOPPNID() + "' "; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getOPPUrlAlias() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=0;
			}
		}
		

		aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_volunteer_opportunity " +
				"SET " +
				"field_volopp_requirements_value=?,field_volopp_faith_req_value=?,field_volopp_creed_value=?,field_volopp_references_value=?," +
				"field_volopp_num_vol_opp_value=?," +
				"field_volopp_num_served_by_org_value=?," + //ORG might have been edited previously
				"field_volopp_hourly_commitmt_value=?," +
				"field_volopp_commit_per_value=?,field_volopp_bg_check_value=?," +
				"field_volopp_start_date_value=UNIX_TIMESTAMP(?)," +
				"field_volopp_end_date_value=UNIX_TIMESTAMP(?)," +
				"field_volopp_org_link_title=?," +  //ORG name might have been edited previously
				"field_volopp_group_min_value=?,field_volopp_group_max_value=?," +
				"field_volopp_cost_pp_value=?," +
				"field_volopp_vols_needed_value=?," +
				"field_volopp_stipend_paid_value=?," +
				"field_volopp_cost_includes_value=?,field_volopp_details_value=?,field_volopp_app_deadline_value=UNIX_TIMESTAMP(?),field_volopp_cost_timeframe_value=?," +
				"field_volopp_org_name_value=?," + //ORG name might have been edited previously
				"field_volopp_serve_poor_value=?,field_volopp_subdomain_value=?," +
				"field_volopp_num_vol_org_value=?, " + //ORG might have been edited previously
				"field_stm_references_value=?," +
				"field_volopp_private_value=?, field_volopp_hq_or_offsite_value=?, field_volopp_questionnaire_type_value=?, " +
				"field_resume_required_value=?, field_cover_letter_required_value=?, field_intern_type_value=? " +
				"WHERE nid=?"; 
		
		MethodInit("editOpportunityInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPRequirements()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPRequiredCodeType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPRequiredCodeDesc() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPReferenceReq() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNumVolOpp() ); // Number of Volunteers Who Have Served in This Position in the Past Year
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumServed() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDouble( aHeadObj.getOPPCommitHourly() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPCommitType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPBackgroundCheck() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPStartDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPEndDt() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPGroupMin() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPGroupMax() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDouble( aHeadObj.getOPPCostUsd() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPVolsNeeded() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPAmntPd() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPCostInclude()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPAddDetails()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryDateNull( aHeadObj.getOPPApplicDeadline() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPCostTerm() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOnethirdP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPSTMReferences()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPPrivate() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPHQorOffSite() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getQuestionnaireType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getResumeRequired() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getCoverLetterRequired() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPInternType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
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
		
		// add to um_node
		// will be published by default, unless it is through the portal sites, and is set to private; ie not published, and not moderated
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node SET title=?, " +
			" changed=UNIX_TIMESTAMP({fn now()}) ";
		if (aHeadObj.getOPPModerate()==0){
//			aszSQLdrupal101=aszSQLdrupal101 + ", status=1 ";
		}
		if(aHeadObj.getOPPPrivate()==1){
			// unpublished && NOT moderated
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "node SET title=?, status=0, moderate=0," +
				" changed=UNIX_TIMESTAMP({fn now()}) ";
		}
		aszSQLdrupal101=aszSQLdrupal101 +
				" WHERE nid=? AND vid=? ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPVID() );
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
		

		//REPLACE, possibly****************** - update or insert********************* 2008-11-04
		// add to um_auto_expire - set expiration to be iExpirationTime days from now
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "auto_expire SET expire=(UNIX_TIMESTAMP({fn now()}) + (86400 * " + iExpirationTime + ") )," +
				" warned=0, extended = (extended + 1) " + // 24 hours * 3600 seconds = 86400  //should we add to extended or not? is this ever used? for now, add
				" WHERE nid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
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
		 * add to node_revisions
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node_revisions SET " +
				"title=?, body=?, teaser=?, timestamp=UNIX_TIMESTAMP({fn now()}) " +
				"WHERE nid=? AND vid=?"; // in reality, in drupal, the uid would be updated here as well to reflect the CURRENT user, whether primary contact or not
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOPPTitle() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPDescription()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getOPPDescription()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getOPPVID() );
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

		
		/**
		 * START taxonomy section
		 * START taxonomy section
		 * START taxonomy section
		 */
		// delete occurrences of the taxonomy and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
		// !!!!!!!!!!!!!!!!!!! potentially NOT SAFE - b/c this would delete ALL, it would have to join term_data, too, to grab the vocabulary id
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + aHeadObj.getOPPNID();
		
/**
		// delete occurrences of this node with the taxonomy within the opportunity vocabularies
		// and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + aHeadObj.getOPPNID() +" AND ( vid = " + 
			vidPosType + " OR vid = " + vidService + " OR vid = " + vidSkill + " OR vid = " + vidVolInfo + " OR vid = " +
			vidDenomAffil + " OR vid = " + vidOrgAffil + " OR vid = " + vidMemberType + " OR vid = " + vidProgramType + " OR vid = " + 
			vidLangSpoken + " OR vid = " + vidWorkStudy + " OR vid = " + vidTripLength + " OR vid = " + vidBenefits + " OR vid= " +
			vidPosFreq + " OR vid = " + vidSchedDate + " ) ";
*/
		
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
		
		// add Service Areas
		// add Service Areas Array
		if ( aHeadObj.getServiceAreasArray()!=null ){
			if ( aHeadObj.getServiceAreasArray().length > 0 ){
				index=0;
				int[] tempService = aHeadObj.getServiceAreasArray();
				for (int i=0; i<tempService.length; i++){
					int tempTID = tempService[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidService + " ";
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
								"VALUES("+ aHeadObj.getOPPNID() + "," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
						// The option the user choose for the SELECT returned no results
						//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
						//return -1;
					}
					itid=-1;
				}
			}
		}
		
		// add Focus Areas (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor1TID() + 
				") AND vid = " + vidVolInfo + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPGreatFor1TID(itid);
				aHeadObj.setOPPFocusAreas(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
			
		}
		// add Focus Areas 2 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas2().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas2() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor2TID() + 
				") AND vid = " + vidVolInfo + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPGreatFor2TID(itid);
				aHeadObj.setOPPFocusAreas2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
			
		}
		// add Focus Areas 3 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas3().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas3() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor3TID() + 
				") AND vid = " + vidVolInfo + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPGreatFor3TID(itid);
				aHeadObj.setOPPFocusAreas3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
			
		}
		// add Focus Areas 4 (Kid, Group, Teen, Senior)
		if ( ! aHeadObj.getOPPFocusAreas4().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor4TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas4() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor4TID() + 
				") AND vid = " + vidVolInfo + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPGreatFor4TID(itid);
				aHeadObj.setOPPFocusAreas4(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
			
		}
		// add Focus Areas 5 (Kid, Group, Teen, Senior, Family)
		if ( ! aHeadObj.getOPPFocusAreas5().equalsIgnoreCase("") || aHeadObj.getOPPGreatFor5TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFocusAreas5() ) + 
				"' OR tid=" +  aHeadObj.getOPPGreatFor5TID() + 
				") AND vid = " + vidVolInfo + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPGreatFor5TID(itid);
				aHeadObj.setOPPFocusAreas5(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
			
		}

		// add opp Status - Local, Virtual, Short-term Missions, Work Study,...
		if ( ! aHeadObj.getOPPStatus().equalsIgnoreCase("") || aHeadObj.getOPPPositionTypeTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPStatus() ) + 
				"' OR tid=" +  aHeadObj.getOPPPositionTypeTID() + 
				") AND vid = " + vidPosType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPPositionTypeTID(itid);
				aHeadObj.setOPPStatus(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
		}
		itid=-1;
			
		// Date Required question
		if ( ! aHeadObj.getOPPDaterequired().equalsIgnoreCase("") || aHeadObj.getOPPDaterequiredTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPDaterequired() ) + 
				"' OR tid=" +  aHeadObj.getOPPDaterequiredTID() + 
				") AND vid = " + vidSchedDate + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPDaterequiredTID();
				aHeadObj.setOPPDaterequired(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Languages Spoken
		if ( ! aHeadObj.getOPPLanguages().equalsIgnoreCase("") || aHeadObj.getOPPLanguageTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
					replacesinglequote(aHeadObj.getOPPLanguages() ) + 
					"' OR tid=" +  aHeadObj.getOPPLanguageTID() + 
					") AND vid = " + vidLangSpoken + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setOPPLanguageTID(itid);
				aHeadObj.setOPPLanguages(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Work Study question
		if ( ! aHeadObj.getOPPWorkStudy().equalsIgnoreCase("") || aHeadObj.getOPPWorkStudyTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPWorkStudy() ) + 
				"' OR tid=" +  aHeadObj.getOPPWorkStudyTID() + 
				") AND vid = " + vidWorkStudy + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPWorkStudyTID();
				aHeadObj.setOPPWorkStudy(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				
				// insert into the Benefits Offered Taxonomy as well, if it's set to Yes
				if(itid == iWorkStudyYesTID){
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ aHeadObj.getOPPNID() +"," + iWorkStudyBenefitsTID + "," + aHeadObj.getOPPVID() + " )";
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
				}
				
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Benefits - Room & Board question
		
//System.out.println(" edit Benefits Offered Array: ");		
		// add benefits Array
		if ( aHeadObj.getBenefitsArray()!=null ){
			if ( aHeadObj.getBenefitsArray().length > 0 ){
//System.out.println(" Benefits Offered Array size is "+aHeadObj.getBenefitsArray().length);				
				index=0;
				int[] tempIDs = aHeadObj.getBenefitsArray();
				for (int i=0; i<tempIDs.length; i++){
					int tempTID = tempIDs[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidBenefits + " ";
//System.out.println("2964   Qry1 is "+Qry1);					
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
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getOPPNID() + "," + itid + "," + aHeadObj.getOPPVID() + " )";
//System.out.println("2983 aszSQLdrupal101 is "+aszSQLdrupal101);						
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
							iRetCode=0;
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=-1;
				}
				itid=-1;
			}
		}
//System.out.println("3002 done with Benefits Offered");
		// Length of Trip question
		if ( ! aHeadObj.getOPPTripLength().equalsIgnoreCase("") || aHeadObj.getOPPTripLengthTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPTripLength() ) + 
				"' OR tid=" +  aHeadObj.getOPPTripLengthTID() + 
				") AND vid = " + vidTripLength + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPTripLengthTID();
				aHeadObj.setOPPTripLength(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// One-time or Ongoing opportunity
		if ( ! aHeadObj.getOPPFreq().equalsIgnoreCase("") || aHeadObj.getOPPFrequencyTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getOPPFreq() ) + 
				"' OR tid=" +  aHeadObj.getOPPFrequencyTID() + 
				") AND vid = " + vidPosFreq + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = aHeadObj.getOPPFrequencyTID();
				aHeadObj.setOPPFreq(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// ================================================
		// ======== START Organizational Data =============
		// ================================================
		
		// add Organization's Denominational Affiliation
		if ( ! aHeadObj.getORGAffiliation().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGAffiliation() ) + 
				"' AND vid = " + vidDenomAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
			
		}
		
		// add Organizational Affiliation (Partners) - for searching ease later
		if ( ! aHeadObj.getORGPartner().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner() ) + 
				"' AND vid = " + vidOrgAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
			
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner2().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner2() ) + 
				"' AND vid = " + vidOrgAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
			
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner3().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner3() ) + 
				"' AND vid = " + vidOrgAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner4().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner4() ) + 
				"' AND vid = " + vidOrgAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organizational Affiliation (Partners)
		if ( ! aHeadObj.getORGPartner5().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGPartner5() ) + 
				"' AND vid = " + vidOrgAffil + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		
		// add Organization Member Type
		if ( ! aHeadObj.getORGMembertype().equalsIgnoreCase("") ){
			// grab the tid
			Qry1 = "SELECT tid FROM " + aszDrupalDB + "term_data WHERE name='" + 
				replacesinglequote(aHeadObj.getORGMembertype() ) + 
				"' AND vid = " + vidMemberType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ aHeadObj.getOPPNID() +"," + itid + "," + aHeadObj.getOPPVID() + ", UNIX_TIMESTAMP(NOW()) )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
				itid=-1;
		}
		// ================================================
		// ========== END Organizational Data =============
		// ================================================
		

		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		 
		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()==1){
			int iCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getOPPNID() + "," + aHeadObj.getOPPUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -1;
			}else{
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -1;
				}else{
					iCount=1;
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
		    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getOPPNID() + ","+iCount+") "; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    			//return -1;
		    		}
				}
			}
		}
		
		if(aHeadObj.getQuestionnaireId() > 0) {
			boolean questionnaireIdChanged;
			
			Qry1 = "select oq.id " +
			       "from " + aszRailsDB + "opportunities_questionnaires as oq " +
			       "where oq.opportunity_nid = ? " +
			       "order by oq.created_at desc";
			
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			pConn.setPrepQryInt(aHeadObj.getOPPNID());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			
			if(pConn.getNextResult()){
				if(pConn.getDBInt("id") != aHeadObj.getQuestionnaireId())
					questionnaireIdChanged = true;
				else
					questionnaireIdChanged = false;
			}
			else {
				questionnaireIdChanged = true;
			}
				
			if(questionnaireIdChanged) {
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszRailsDB + "opportunities_questionnaires(opportunity_nid, questionnaire_id, created_at, updated_at) " +
							    "VALUES('"+ aHeadObj.getOPPNID() +"','" + aHeadObj.getQuestionnaireId() + "'," + "NOW(), NOW() )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			}
		}
		
		return updateRequiredDocs(pConn, aHeadObj);
	}
	// end-of method editOpportunityInDB()


    /**
	* delete opportunity record in table org_opportunitiesinfo
	*/
	public int deleteOpportunityInDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		String Qry1;
		int ilid=-1;
		//int inid=-1, ivid=-1;
		MethodInit("deleteOpportunityInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
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
				"WHERE nid = " + aHeadObj.getOPPNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		// delete from um_auto_expire
		aszSQL101="DELETE FROM " + aszDrupalDB + "auto_expire " +
				"WHERE nid=" + aHeadObj.getOPPNID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete any related contacts to this opportunity
		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
				"WHERE component='voleng_opp_nid' AND opp_nid=" + aHeadObj.getOPPNID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from references to the parent org node
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_opp_reference " +
				"WHERE field_volorg_opp_reference_nid=" + aHeadObj.getOPPNID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_content_type_volunteer_opportunity
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_type_volunteer_opportunity " +
				"WHERE nid=" + aHeadObj.getOPPNID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_url_alias
		aszSQL101="DELETE FROM " + aszDrupalDB + "url_alias " +
				"WHERE src LIKE 'node/" + aHeadObj.getOPPNID() + "'"; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_location
		// grab the lid from um_location_instance to delete from um_location
		Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_instance WHERE vid=" + aHeadObj.getOPPVID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("lid");
			aszSQL101="DELETE FROM " + aszDrupalDB + "location_instance " +
					"WHERE vid = " + aHeadObj.getOPPVID();
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			aszSQL101="DELETE FROM " + aszDrupalDB + "location " +
					"WHERE lid = " + ilid;
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		} else {
		}
		// delete from um_node
		aszSQL101="DELETE FROM " + aszDrupalDB + "node " +
				"WHERE nid = " + aHeadObj.getOPPNID();
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
				"WHERE nid = "+ aHeadObj.getOPPNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from node_access
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "node_access " +
				"WHERE nid = "+ aHeadObj.getOPPNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from node_comment_statistics
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "node_comment_statistics " +
				"WHERE nid = "+ aHeadObj.getOPPNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from um_flag_content && flag_counts
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "flag_content " +
				"WHERE content_id = "+ aHeadObj.getOPPNID()+" and content_type LIKE 'node' ";
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			//return -1;
		}
		aszSQL101="DELETE FROM " + aszDrupalDB + "flag_counts " +
					" WHERE content_id = "+ aHeadObj.getOPPNID()+" and content_type LIKE 'node'";
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			//return -1;
		}
		return 0;
	}
	// end-of method deleteOpportunityInDB()

	/**
	* select a list of opportunities from drupal tables with opportunity info
	*/
	public int getOpportunityDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, int iType, boolean feeds){
		MethodInit("getOpportunityDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		boolean bManaging=false, bNatlAssoc=false;
		int iRetCode=0, iTemp=0;
		String 	aszSQLManaging="", aszSQLSrchList="", aszPostalCode="", aszDistance="", aszSQLTemp="", 
				aszSQLSelect = "", aszSQLFrom = "", aszSQLWhere = "", 
				aszSQLSelectManaging="", aszSQLFromManaging="", aszSQLWhereManaging="",
				aszSQLWhereEnd="", aszSQLSelectEnd="", aszSQLFromEnd="";
		String aszReqCode="";
		int iServiceArea1 = 0, iServiceArea2 = 0, iServiceArea3 = 0;
		if(aSrchParmObj.getRequestType().equals("natlassoc")){
			bNatlAssoc=true;
		}

		if(
					iType==OrganizationInfoDTO.LOADBY_UID_ADMIN ||
				(	iType==OrganizationInfoDTO.LOADBY_ORGNID_MANAGE	&&	bNatlAssoc==true	)
		){
		}else if(iType!=aSrchParmObj.PORTAL_OTHERFAV_LIST && feeds==false){
			aListObj.clear();
		}
		
		String mainDB=aszDrupalDB;
		if( feeds==true ){
			mainDB=aszSocialGraphDB;
		}

		String aszPublished="\n       AND status=1 \n"; // public listings can ONLY show PUBLISHED
		aszSQLSelect = 
			"SELECT\n"+
			"       opp.nid,		\n"+
			"       opp.vid,		\n"+
			"       opp.uid,			\n"+
			"       title,			\n"+
			"       changed,		\n" +
			"       created,		\n" +
			"       teaser,			\n" +
			"       faith_req,		\n" +
			"       num_vol_opp,	\n" +
			"       num_vol_org,		\n" +
			"       num_served_by_org,	\n" +
			"       start_date,			\n" +
			"       end_date,			\n" +
//			"       opp_url_id,			\n" +
			"       popularity,			\n" +
			"       org_name,			\n" +
			"       org_nid,			\n" +
			"       tm_member,			\n" +
			"       cost_pp,			\n"+
			"       cost_pp,			\n" +
			"       street,				\n" +
			"       city,				\n" +
			"       province,			\n" +
			"       postal_code,		\n" +
			"       country,			\n" +
			"       latitude,			\n" +
			"       longitude,			\n" +
			"       private,			\n" +
			"       hq_or_offsite,			\n" +
			"       fav_content_id,			\n" +
			"       fav_uid,			\n" +
			
			"       GROUP_CONCAT(DISTINCT service_areas)	service_areas,		\n" +
			"		GROUP_CONCAT(DISTINCT skills)			skills,				\n" +
			"		GROUP_CONCAT(DISTINCT position_type)	position_type,		\n" +
			"		GROUP_CONCAT(DISTINCT great_for)		great_for,			\n" +
			"		GROUP_CONCAT(DISTINCT frequency)		frequency,			\n" +
			"		GROUP_CONCAT(DISTINCT trip_length)		trip_length,		\n" +
			"		GROUP_CONCAT(DISTINCT benefits_offered)	benefits_offered,	\n" +
			"		GROUP_CONCAT(DISTINCT work_study)		work_study,			\n" +		
			"		GROUP_CONCAT(DISTINCT region)			region,				\n" +		
			"		GROUP_CONCAT(DISTINCT scheduled)			scheduled,				\n" +		
			"		GROUP_CONCAT(DISTINCT scheduled_tid)		scheduled_tid,			\n" +		
			"       url_alias,			\n" +
			"       org_url_alias,		\n" +
			"       status,				\n" +
			"       faith_tid			\n";
		aszSQLFrom=
			" \nFROM\n" + 
			"       " + aszDataDB + "tbl_opps_tagged opp				\n" ;
		aszSQLWhere=
			" \nWHERE		true=true \n";
		if(feeds==true){
			aszSQLSelect = 
				"SELECT\n"+
				"       sid nid,		\n"+
				"       opp.vid,		\n"+
				"       0 as uid,			\n"+
				"       title,			\n"+
				"       changed,		\n" +
				"       created,		\n" +
				"       short_description teaser,			\n" +
				"       faith_req,		\n" +
				"       num_vol_opp,	\n" +
				"       num_vol_org,		\n" +
				"       num_served_by_org,	\n" +
				"       start_date,			\n" +
				"       end_date,			\n" +
//				"       opp_url_id,			\n" +
				"       popularity,			\n" +
				"       org_name,			\n" +
				"       org_nid,			\n" +
				"       tm_member,			\n" +
				"       cost_pp,			\n" +
				"       location_street street,				\n" +
				"       location_city city,				\n" +
				"       location_province province,			\n" +
				"       location_postal_code postal_code,		\n" +
				"       location_country country,			\n" +
				"       latitude,			\n" +
				"       longitude,			\n" +
				"       private,			\n" +
				"       hq_or_offsite,			\n" +
				"       fav_uid,			\n" +
				"       service_areas,		\n" +
				"		skills,				\n" +
				"		position_type,		\n" +
				"		great_for,			\n" +
				"		frequency,			\n" +
				"		trip_length,		\n" +
				"		benefits_offered,	\n" +
				"		work_study,			\n" +		
				"		region,				\n" +		
				"		scheduled,				\n" +		
//				"		GROUP_CONCAT(DISTINCT scheduled_tid)		scheduled_tid,			\n" +		
				"       url_alias,			\n" +
				"       status,				\n" +
				"       faith_tid			\n";
			aszSQLFrom=
				" \nFROM\n" + 
				"       " + mainDB + "tbl_opportunities opp				\n" ;
		}
		
		aszSQLSelectManaging = 
			"SELECT\n"+
			"       n.nid,	\n"+
			"       n.vid,	\n"+
			"       n.uid,	\n"+
			"       n.title,	\n"+
			"       n.created,	\n"+
			"       n.changed,	\n"+
			"       opp.field_volopp_faith_req_value         faith_req,		\n"+
			"       opp.field_volopp_num_vol_opp_value       num_vol_opp,	\n"+
			"       opp.field_volopp_num_vol_org_value       num_vol_org,	\n"+
			"       opp.field_volopp_num_served_by_org_value num_served_by_org,	\n"+
			"       opp.field_volopp_start_date_value        start_date,		\n"+
			"       opp.field_volopp_end_date_value          end_date,		\n"+
//			"       opp.field_volopp_url_id_value            opp_url_id,		\n"+
			"       opp.field_volopp_popularity_value        popularity,		\n"+
			"       opp.field_volopp_org_name_value          org_name,		\n"+
			"       opp.field_volopp_org_reference_nid       org_nid,		\n"+
			"       opp.field_volopp_cost_pp_value           cost_pp,		\n"+
			"       opp.field_volopp_tm_member_value				 tm_member,		\n"+
			"       00000 AS postal_code " ; 
		aszSQLFromManaging=
			" \nFROM\n" + 
			"       " + aszDrupalDB + "node n,				\n" + 
			"       " + aszDrupalDB + "content_type_volunteer_opportunity opp  	\n";
		aszSQLWhereManaging=
			" \nWHERE\n" +
			"       n.nid=opp.nid		\n"; 
		
		if(aSrchParmObj.getSearchLatest()==true){
			aszSQLWhere += "       AND created > " + lOneWkAgo + " 		\n";
		}
        
        // see if this is going through a portal or not
        int iPortalUID = aSrchParmObj.getPortalUID();
    	String aszPortalUIDQuery = "";
        if(aSrchParmObj.getPortal()==777){// administrator through portal
    	}else if(iType==aSrchParmObj.PORTAL_OWN_LIST){
    		aszPortalUIDQuery=" AND org_uid = " + iPortalUID ;
		}else if(iType==aSrchParmObj.PORTAL_OTHERFAV_LIST){
			aszPortalUIDQuery=" AND org_uid != " + iPortalUID ;
		}	
        if( iPortalUID > 0 && iPortalUID!=iMeetTheNeedUID &&
        		(aSrchParmObj.getSearchType()!=OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES ) &&
        		bNatlAssoc==false && feeds==false
        ){
    		aszPublished="";//" AND status=1 "; might have unpublished private ones that they don't want listed on all versions of the site
    		//******************** need to somehow show this org's opps FIRST in the results list ******************
        	if(aSrchParmObj.getPortal()==777){// administrator through portal
        	}else if(iType==aSrchParmObj.PORTAL_OWN_LIST){
				aszSQLWhereEnd += 
					aszPublished + aszPortalUIDQuery ;
			}else if(iType==aSrchParmObj.PORTAL_OTHERFAV_LIST && iPortalUID>0){
	    		// include field_volopp_private_value,field_volopp_hq_or_offsite_value
	        	aszSQLFromEnd += ", " + mainDB + "flag_content fl ";
	        	aszSQLWhereEnd += " AND opp.nid=fl.content_id AND fl.fid=" + iFlagFavorite + " AND fl.uid=" + iPortalUID + " ";
	        	
				aszSQLWhereEnd += 
					aszPublished + aszPortalUIDQuery ;
			}	
        	// off-site or HQ
        	if(aSrchParmObj.getOPPHQorOffSite().equalsIgnoreCase("hq")){ // HQ
            	aszSQLWhereEnd += " AND (" +
            			" opp.uid=" + iPortalUID + " AND hq_or_offsite LIKE 'hq' " +
            			")\n";
        	}else if(aSrchParmObj.getOPPHQorOffSite().equalsIgnoreCase("offsite")){ // off-site
            	aszSQLWhereEnd += " AND (	opp.uid!=" + iPortalUID + " OR	(" +
            			" opp.uid=" + iPortalUID + " AND (hq_or_offsite  LIKE 'offsite' OR hq_or_offsite IS NULL) " +
            			")	)\n";
        	}else if(aSrchParmObj.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")){ // off-site
            	aszSQLWhereEnd += " AND (	opp.uid!=" + iPortalUID + " OR	(" +
            			" opp.uid=" + iPortalUID + " AND (hq_or_offsite  LIKE 'offsite_intl' OR hq_or_offsite IS NULL) " +
            			")	)\n";
        	}
        }
    	if(aSrchParmObj.getPortal()==777){// administrator through portal
    		aszSQLWhereEnd += " AND org_uid != " + aSrchParmObj.getPortalAdminUID();// org_uid instead of opp.uid
    	}
        
    	boolean b_throughTermDataLeftJoin=false;
    	if(aSrchParmObj.getSearchFromSQLStatement().contains("term_data")){
    		b_throughTermDataLeftJoin=true;
    	}
    		
        

        switch( aSrchParmObj.getSearchType() ){
			case OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES :
	    		if( aSrchParmObj.getPortalAdminUID() > 0 && b_throughTermDataLeftJoin==false){ // AND this is NOT the advanced search form - would cause issues with not left joining with uid
		        	aszSQLWhere = " WHERE ( ( true=true ";
		        	aszSQLWhereEnd += 
	    				aszPublished + ") OR (fav_uid=" + aSrchParmObj.getPortalAdminUID() + "  AND opp.org_uid != " + aSrchParmObj.getPortalAdminUID() + ") ) "+
		    			aszPortalUIDQuery +
		    			" \nGROUP BY\n opp.vid " +
		    			" \nORDER BY\n fav_uid= " + aSrchParmObj.getPortalAdminUID() + " DESC, tm_member DESC, distance ASC, popularity DESC ";
	    		}else{
	    			aszSQLWhereEnd += 
			        	aszPublished +
			        	aszPortalUIDQuery +
				        " \nGROUP BY\n opp.vid " +
				        " \nORDER BY\n tm_member DESC, distance ASC, popularity DESC ";
	    		}
	        	break;
	    	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n tm_member DESC, popularity DESC ";
	        	break;
	    	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_NUM_VOL_ORG:
	        	aszSQLWhereEnd += 
	        		aszPublished +
		        	" \nGROUP\n BY opp.vid " +
		        	" \nORDER\n BY num_vol_org DESC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POSTAL:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n postal_code ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_CITY:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n city ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STATE:
	        	aszSQLWhereEnd += 
			        	"\n       AND (province!='' OR province!=NULL) " + 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n province ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_COUNTRY:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n country ASC ";
	        	break;
            case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DISTANCE:
	        	if(aSrchParmObj.getDistanceSearched()==true){
	        		aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
		            	" \nORDER BY\n distance ASC ";
	        	}else{
	        		aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
		            	" \nORDER BY\n ABS(CONVERT(postal_code,DECIMAL) - CONVERT('" + aSrchParmObj.getPostalCode() + "',DECIMAL)) ASC ";
	        	}
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_ORGNAME:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_name ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNAME:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n title ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNUMVOL:
	        	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n num_served_by_org ASC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STMDURATION: // trip length ********************** maybe should look at weights of terms
        		aszSQLWhereEnd += 
		        	aszPublished +
		        	"\n       AND trip_length NOT NULL \n" + 
			        " \nGROUP BY\n opp.vid " +
			        " \nORDER BY\n trip_length_weight ";
                break;
            case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STMCOST:
            	aszSQLWhereEnd += 
			        	"\n       AND (cost_pp!='' OR cost_pp!=NULL) " +  // ?? shouldn't it include ones that don't have a cost, too
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
		                " \nORDER BY\n cost_pp ASC ";
                break;
            case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_UPDATEDT:
            	aszSQLWhereEnd += 
		        		aszPublished +
			        	" \nGROUP BY\n opp.vid " +
		                " \nORDER BY\n changed DESC ";
                break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_TYPE:
        		aszSQLWhereEnd += 
	        	aszPublished +
		        " \nGROUP BY\n opp.vid " +
		        " \nORDER BY\n position_type_weight ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DENOMAFFIL:
                iTemp = aSrchParmObj.getSearchFromSQLStatement().indexOf("term_node DenomAffil");
                if (iTemp < 0 ){
                	aszSQLTemp = aSrchParmObj.getSearchFromSQLStatement() +
                		",\n       " + aszDrupalDB + "term_node DenomAffil, " + aszDrupalDB + "term_data DenomAffilTermData ";
                	aSrchParmObj.setSearchFromSQLStatement(aszSQLTemp);
                	aszSQLTemp = aSrchParmObj.getSearchWhereSQLStatement() +
                		"\n       AND DenomAffil.vid=opp.vid AND DenomAffil.tid=DenomAffilTermData.tid " +
                		"\n              AND DenomAffilTermData.vid=" +  vidDenomAffil + "  ";
	            	aSrchParmObj.setSearchWhereSQLStatement(aszSQLTemp);
                }
                // 1. for using regular drupal taxonomy tables
                aszSQLWhereEnd += 
		        	aszPublished +
		        	"\n       AND denom_affil NOT NULL \n" + 
			        " \nGROUP BY\n opp.vid " +
			        " \nORDER BY\n DenomAffilTermData.name DESC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_AFFIL1:
                iTemp = aSrchParmObj.getSearchFromSQLStatement().indexOf("term_node OrgAffil");
                if (iTemp < 0 ){
                	aszSQLTemp = aSrchParmObj.getSearchFromSQLStatement() +
                		",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData ";
                	aSrchParmObj.setSearchFromSQLStatement(aszSQLTemp);
                	aszSQLTemp = aSrchParmObj.getSearchWhereSQLStatement() +
                		"\n       AND OrgAffil.vid=opp.vid AND OrgAffil.tid=OrgAffilTermData.tid " +
                		"\n              AND OrgAffilTermData.vid=" +  vidOrgAffil + "  ";
	            	aSrchParmObj.setSearchWhereSQLStatement(aszSQLTemp);
                }
                aszSQLWhereEnd += 
		        	aszPublished +
			        " \nGROUP BY\n opp.vid " +
			        " \nORDER BY\n OrgAffilTermData.name DESC ";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_TOPMOST :
	    		aszReqCode = aSrchParmObj.getReqCode();
	    		int iServiceAreasLength=0, iServiceAreasNumbers=0;
				// generic Service Areas
				String aszServiceAreasTIDs = aSrchParmObj.getServiceAreasTIDs();
				iServiceArea1 = aSrchParmObj.getOPPServiceArea1TID();
				iServiceArea2 = aSrchParmObj.getOPPServiceArea2TID();
				iServiceArea3 = aSrchParmObj.getOPPServiceArea3TID();		
				// ???????????? in this next part, should there be a trailing comma, or no? I don't remember...
				if (iServiceArea1 > 0){
					if (aszServiceAreasTIDs.length() > 1 ){
						if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
					}
					aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea1 ;
				}
				if (iServiceArea2 > 0){
					if (aszServiceAreasTIDs.length() > 1 ){
						if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
					}
					aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea2 ;
				}
				if (iServiceArea3 > 0){
					if (aszServiceAreasTIDs.length() > 1 ){
						if(!(aszServiceAreasTIDs.endsWith(",")))	aszServiceAreasTIDs = aszServiceAreasTIDs + ",";
					}
					aszServiceAreasTIDs = aszServiceAreasTIDs + iServiceArea3 ;
				}
				iServiceAreasLength = aszServiceAreasTIDs.length();
				iServiceAreasNumbers = aszServiceAreasTIDs.replaceAll("[^,]","").length();
				if (iServiceAreasLength > 1){
					aszSQLWhereEnd += " AND (service_areas_tid IN(" + aszServiceAreasTIDs + ")  )\n";
				}
				
    			aszDistance = aSrchParmObj.getDistance();
    			String aszDistanceSearch="";
    			if(	aszDistance.equalsIgnoreCase("Country") || 
    	    			aszDistance.equalsIgnoreCase("Virtual")
    	    	){
    				aszDistanceSearch="";
    	    	}else{
    	    		if(aszDistance.equalsIgnoreCase("5")){
    	    			aszDistanceSearch = "5";
    	    		}else if(aszDistance.equalsIgnoreCase("20")){
    	    			aszDistanceSearch = "20";
    	        	}else if(aszDistance.equalsIgnoreCase("City")){
    	    			aszDistanceSearch = "25";
    	        	}else if(aszDistance.equalsIgnoreCase("Region")){
    	    			aszDistanceSearch = "50";
    	        	}else{
    	    			aszDistanceSearch = "25";
    	        	}
    	    	}
    			
    			String aszLatitude=aSrchParmObj.getSearchLatitude1();//"42.289793";
    			String aszLongitude=aSrchParmObj.getSearchLongitude1();//"-71.057207";
    			
    			if( (aszLatitude.length()>0 || aszLongitude.length()>0) && aszDistanceSearch.length()>0){
    				aSrchParmObj.setDistanceSearched(true);
        			aszSQLSelectEnd=", (3956 * (2 * ASIN(SQRT(POWER(SIN((("+aszLatitude+"-latitude)*0.017453293)/2),2) " +
							"+COS("+aszLatitude+"*0.017453293) *COS(latitude*0.017453293) " +
									"*POWER(SIN((("+aszLongitude+"-longitude)*0.017453293)/2),2))))) AS distance";
		
					aszSQLWhereEnd += "\n       AND " +
							"(3956 * (2 * ASIN(SQRT(" +
							"POWER(SIN((("+aszLatitude+"-latitude)*0.017453293)/2),2) +" +
							"COS("+aszLatitude+"*0.017453293) *" +//"COS(42.289793*0.017453293) *" +
							"COS(latitude*0.017453293) *" +
							"POWER(SIN((("+aszLongitude+"longitude)*0.017453293)/2),2)" +
									")))) <= "+ aszDistanceSearch +" ";//ORDER BY Distance";
    			}else{
    				aszSQLSelectEnd =", -1 AS distance ";
    				aSrchParmObj.setDistanceSearched(false);
    	    		aszPostalCode = aSrchParmObj.getPostalCode();
        			if(aszDistance.equalsIgnoreCase("Country")){
        				iRetCode=0;
        			}else if(aszDistance.equalsIgnoreCase("Virtual")){
        				iRetCode=0;
        			}else {
        	        	if(aszPostalCode.length() > 4){
        	        		aszSQLWhereEnd +=  "\n       AND postal_code IN ('" + aszPostalCode + "') ";
        	        	}else {
        	        		aszSQLWhereEnd +=  "\n       AND postal_code like ('" + aszPostalCode + "%') ";
        	        	}
        			}
    			}
    			if (aszReqCode.equalsIgnoreCase("NO")){
    				aszSQLWhereEnd +=  // include No Faith, or Faith-Based (but not Faith-Filled)
    					" AND (" +
    					"\n       (faith_tid=" + iNoFaithActivity + " OR faith_tid=" + iFaithBased + ") OR  " +
    					"\n       (faith_tid IS NULL AND faith_or_nofaith_tid=" + iNonFaith + " )  OR  " +
    					"\n       (faith_tid IS NULL AND faith_or_nofaith_tid IS NULL AND faith_req LIKE '" + aszReqCode + "')  " +
    					"\n)  \n";
    			}
    			aszSQLWhereEnd +=   
	        		aszPublished +
		        	" \nGROUP BY\n opp.vid " +
		        	" \nORDER BY\n tm_member DESC, popularity DESC ";
	        	break;
	        	
	        // start managing cases:
	        // start managing cases:
	        // start managing cases:
        	case OrganizationInfoDTO.LOADBY_ORG_ORDER_EXPIRATION :
			    // even though this says LOADBY_PRIMARYPERSON, it's really that it's the person viewing their own opportunity
        		if(aSrchParmObj.getNID() < 1){
                	aSrchParmObj.appendErrorMsg("organization id required");
        			return -1;
        		}
        		bManaging=true; // since this is managing, use the old query rather than the new query
        		// may join to expire to see when it is due to expire, so we can show this on the manage listings page
        		aszSQLSelectEnd+=", exp.expire, (exp.expire - (UNIX_TIMESTAMP({fn now()}) ) ) expired " ; // get the difference between expire and now
        		aszSQLFromEnd +=  
        	 		",\n       " + aszDrupalDB + "auto_expire exp "  ; 
        		aszSQLWhereEnd += 
		        	"\n       AND opp.nid=exp.nid " + 
		        	"\n       AND opp.field_volopp_org_reference_nid=" + aSrchParmObj.getNID() + 
		        	" \nGROUP BY\n opp.vid " +
		        	" \nORDER BY\n exp.expire, title, changed";
            	break;
        	case OrganizationInfoDTO.LOADBY_ORGNID_MANAGE :
			    // even though this says LOADBY_PRIMARYPERSON, it's really that it's the person viewing their own opportunity
        		if(aSrchParmObj.getNID() < 1){
                	aSrchParmObj.appendErrorMsg("organization id required");
        			return -1;
        		}
        		bManaging=true; // since this is managing, use the old query rather than the new query
        		// may join to expire to see when it is due to expire, so we can show this on the manage listings page
        		aszSQLSelectEnd+=", exp.expire, (exp.expire - (UNIX_TIMESTAMP({fn now()}) ) ) expired " ; // get the difference between expire and now
        		aszSQLFromEnd +=  
        	 		",\n       " + aszDrupalDB + "auto_expire exp "  ; 
        		aszSQLWhereEnd += 
		        	"\n       AND opp.nid=exp.nid " + 
		        	"\n       AND opp.field_volopp_org_reference_nid=" + aSrchParmObj.getNID() + 
		        	" \nGROUP BY\n opp.vid " +
		        	" \nORDER BY\n title, changed";
            	break;
        	case OrganizationInfoDTO.LOADBY_UID :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLFromEnd +=  
            	 		",\n       " + aszDrupalDB + "content_field_volorg_owner orguser " + 
            	 		",\n       " + aszDrupalDB + "content_field_volorg_opp_reference orgopps " ; 
        		aszSQLWhereEnd += 
			        	"\n       AND orguser.nid=orgopps.nid							" +
			        	"\n       AND orgopps.field_volorg_opp_reference_nid=opp.nid 	" +
			        	"\n       AND orguser.field_volorg_owner_uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
            	break;
            
        	case OrganizationInfoDTO.LOADBY_UID_ADMIN :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLFromEnd +=  
            	 		",\n       " + aszDrupalDB + "content_field_volorg_owner orguser " + 
            	 		",\n       " + aszDrupalDB + "content_field_volorg_opp_reference orgopps " ;
        		aszSQLWhereEnd += 
			        	"\n       AND orguser.nid=orgopps.nid							" +
			        	"\n       AND orgopps.field_volorg_opp_reference_nid=opp.nid 	" +
			        	"\n       AND orguser.field_volorg_owner_uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
            	break;
/*            
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;

        		aszSQLFromEnd +=  
        				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
            	 		",\n       " + aszDrupalDB + "term_relation tr " + 
        				",\n       " + aszDrupalDB + "term_node NatlAffil, " + aszDrupalDB + "term_data NatlAffilTermData " +
            	 		",\n       " + aszDrupalDB + "content_field_volorg_owner orguser " ; // instead of opporg reference, look at opp's orgaffil
        		aszSQLWhereEnd += 
            			"\n       AND OrgAffil.vid=opp.vid AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND tr.tid2=OrgAffil.tid AND tr.tid1=NatlAffil.tid	" +
                		"\n       AND NatlAffil.vid=orguser.vid AND NatlAffil.tid=NatlAffilTermData.tid " +
                		"\n       AND NatlAffilTermData.vid=" +  vidNatlAffil + "  " +
            			"\n       AND NatlAffil.vid=orguser.vid 	" +
			        	"\n       AND orguser.field_volorg_owner_uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
            	break;
*/                
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;

        		aszSQLFromEnd +=  
        				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
        				",\n       " + aszDrupalDB + "term_node OrgAffil2 " +
            	 		",\n       " + aszDrupalDB + "node natlassoc " + 
            	 		",\n       " + aszDrupalDB + "content_field_natlassoc_manager manager " ;
        		aszSQLWhereEnd += 
            			//"\n       AND OrgAffil.nid=opp.field_volopp_org_reference_nid " +
        				"\n       AND OrgAffil.vid=natlassoc.vid " +
            			"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND OrgAffil.tid=OrgAffil2.tid " +
            			"\n       AND OrgAffil2.nid=opp.field_volopp_org_reference_nid " +
                		"\n       AND natlassoc.nid=manager.nid AND natlassoc.type LIKE 'national_association'	" +
			        	"\n       AND manager.field_natlassoc_manager_uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
/*
        		aszSQLFromEnd +=  
        				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
            	 		",\n       " + aszDrupalDB + "term_relation tr " + 
        				",\n       " + aszDrupalDB + "term_node NatlAffil, " + aszDrupalDB + "term_data NatlAffilTermData " +
            	 		",\n       " + aszDrupalDB + "node natlaffiluser " ;
        		aszSQLWhereEnd += 
            			"\n       AND OrgAffil.nid=opp.field_volopp_org_reference_nid AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND tr.tid2=OrgAffil.tid AND tr.tid1=NatlAffil.tid	" +
                		"\n       AND NatlAffil.vid=natlaffiluser.vid AND NatlAffil.tid=NatlAffilTermData.tid " +
                		"\n       AND NatlAffilTermData.vid=" +  vidNatlAffil + "  " +
            			"\n       AND NatlAffil.vid=natlaffiluser.vid 	" +
			        	"\n       AND natlaffiluser.uid=" + aSrchParmObj.getUID() + 
			        	"\n       AND natlaffiluser.type LIKE 'uprofile' " + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
*/
            	break;
            
        	case OrganizationInfoDTO.LOADBY_UID_CONTACT :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		if(aSrchParmObj.getORGNID()>0){
    	    		aszSQLWhereEnd += 
    			        	"\n       AND usermail.org_nid=" + aSrchParmObj.getORGNID() ;
        		}
        		aszSQLFromEnd +=  
	        	 		",\n       " + aszDrupalDB + "usermail usermail " ; 
        		aszSQLSelectEnd+=", exp.expire, (exp.expire - (UNIX_TIMESTAMP({fn now()}) ) ) expired " ; // get the difference between expire and now
        		aszSQLFromEnd +=  ",\n       " + aszDrupalDB + "auto_expire exp "  ;
	    		aszSQLWhereEnd += 
			        	"\n       AND opp.nid=exp.nid " +
			        	"\n       AND usermail.opp_nid=opp.nid 	" +
			        	"\n       AND usermail.uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
	    		break;
	    	
        	case OrganizationInfoDTO.LOADBY_SITEADMIN :
        		if(aSrchParmObj.getNID() < 1){
                	aSrchParmObj.appendErrorMsg("organization nid required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLFromEnd +=  
            	 		",\n       " + aszDrupalDB + "content_field_volorg_opp_reference orgopps " ; 
        		aszSQLWhereEnd += 
			        	"\n       AND orgopps.field_volorg_opp_reference_nid=opp.nid 	" +
			        	"\n       AND orgopps.nid=" + aSrchParmObj.getNID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
            	break;
            default:
    			setErr("request type not supported");
            	aSrchParmObj.appendErrorMsg("type not supported");
                return 1;
        }
        
        String aszSQLSelectEndSrchList = aszSQLSelectEnd + ", private, hq_or_offsite ";
        if(! (
        		aSrchParmObj.getSearchSQLStatement().contains("distance")		||
        		aszSQLSelectEnd.contains("distance") 
        )){
        	aszSQLSelectEndSrchList+=", -1 AS distance ";        	
        }
        if(! (
        		aSrchParmObj.getSearchSQLStatement().contains("faith_tid")		||
        		aszSQLSelectEnd.contains("faith_tid") 
        )){
        	aszSQLSelectEndSrchList+=", -1 AS faith_tid ";        	
        }
        
        aszSQLSrchList = aszSQLSelect + aSrchParmObj.getSearchSQLStatement() + aszSQLSelectEndSrchList + 
        			aszSQLFrom + aSrchParmObj.getSearchFromSQLStatement() + aszSQLFromEnd + 
        			aszSQLWhere + aSrchParmObj.getSearchWhereSQLStatement() + aszSQLWhereEnd;
    	// used for managing purposes, where it doesn't have to join taxonomies, be published, etc
        aszSQLManaging = aszSQLSelectManaging + aSrchParmObj.getSearchSQLStmnt() + aszSQLSelectEnd + 
    				aszSQLFromManaging + aSrchParmObj.getSearchFromSQLStmnt() + aszSQLFromEnd + 
					aszSQLWhereManaging + aSrchParmObj.getSearchWhereSQLStmnt() + aszSQLWhereEnd;
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
        if(bManaging==false){
			iRetCode = pConn.RunQuery(aszSQLSrchList);
		}else{ // if a user might be opening this in a case where it doesn't matter if it's published or not, we want everything to load for them
			aSrchParmObj.setDistanceSearched(false);
			iRetCode = pConn.RunQuery(aszSQLManaging);
		}
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-2;
		while(pConn.getNextResult()){
            iRetCode=0;
			OrgOpportunityInfoDTO aHeadObj = new OrgOpportunityInfoDTO();
//			aHeadObj.setOPPurlID(pConn.getDBInt("opp_url_id"));
			aHeadObj.setOPPTitle(pConn.getDBString("title"));
			aHeadObj.setOPPUID(pConn.getDBString("uid"));
			aHeadObj.setOPPNID(pConn.getDBInt("nid"));
			aHeadObj.setORGNID(pConn.getDBInt("org_nid"));
			aHeadObj.setOPPNumVolOpp(pConn.getDBInt("num_vol_opp")); // Number of Volunteers Who Have Served in This Position in the Past Year
			aHeadObj.setOPPCreateDt(pConn.getDBTimestamp("created"));
			aHeadObj.setOPPUpdateDt(pConn.getDBTimestamp("changed"));
			aHeadObj.setOPPCreateDtNum(pConn.getDBInt("created"));
			aHeadObj.setOPPUpdateDtNum(pConn.getDBInt("changed"));
			aHeadObj.setOPPStartDtNum(pConn.getDBInt("start_date"));
			aHeadObj.setOPPEndDtNum(pConn.getDBInt("end_date"));
			aHeadObj.setOPPRequiredCodeType(pConn.getDBString("faith_req"));
			aHeadObj.setOPPPopularity(pConn.getDBInt("popularity"));
			aHeadObj.setORGOrgName(pConn.getDBString("org_name"));
			aHeadObj.setOPPOrgName(pConn.getDBString("org_name"));
			aHeadObj.setOPPCostUsd(pConn.getDBDouble("cost_pp"));
			aHeadObj.setORGMember(pConn.getDBInt("tm_member"));
			if( aSrchParmObj.getSearchType() == OrganizationInfoDTO.LOADBY_ORG_ORDER_EXPIRATION || aSrchParmObj.getSearchType() == OrganizationInfoDTO.LOADBY_UID_CONTACT){
				aHeadObj.setOPPExpirationDt(pConn.getDBInt("expire"));
				aHeadObj.setOPPExpirationTime(pConn.getDBInt("expired")); // expiration date - today; if negative, then expired
			}
			if( bManaging==false ){
				aHeadObj.setOPPDescTeaser(pConn.getDBString("teaser"));
				aHeadObj.setOPPFaithSpecTID(pConn.getDBInt("faith_tid"));
				aHeadObj.setSkillTypes(pConn.getDBString("skills"));
				aHeadObj.setOPPFreq(pConn.getDBString("frequency"));
				aHeadObj.setOPPTripLength(pConn.getDBString("trip_length"));
				aHeadObj.setOPPPositionType(pConn.getDBString("position_type"));
				aHeadObj.setOPPGreatForAreas(pConn.getDBString("great_for"));
				aHeadObj.setOPPBenefits(pConn.getDBString("benefits_offered"));
				aHeadObj.setOPPWorkStudy(pConn.getDBString("work_study"));
				aHeadObj.setOPPDaterequired(pConn.getDBString("scheduled"));
				aHeadObj.setOPPHQorOffSite(pConn.getDBString("hq_or_offsite"));
				aHeadObj.setOPPPrivate(pConn.getDBInt("private"));
				aHeadObj.setOPPUrlAlias(pConn.getDBString("url_alias"));
				aHeadObj.setOPPAddrLine1(pConn.getDBString("street"));
				aHeadObj.setOPPAddrCity(pConn.getDBString("city"));
				aHeadObj.setOPPAddrStateprov(pConn.getDBString("province"));
				aHeadObj.setOPPAddrPostalcode(pConn.getDBString("postal_code"));
				aHeadObj.setOPPAddrCountryName(pConn.getDBString("country"));
				aHeadObj.setOPPCategories(pConn.getDBString("service_areas"));
				if(feeds==false){
					aHeadObj.setORGUrlAlias(pConn.getDBString("org_url_alias"));
					aHeadObj.setOPPDaterequiredTID(pConn.getDBInt("scheduled_tid"));
				}
			}
			if( aSrchParmObj.getDistanceSearched()==true && bManaging==false ){
				aHeadObj.setOPPDistance(pConn.getDBString("distance"));
			}else{
				aHeadObj.setOPPDistance(-1);
			}
			if(aSrchParmObj.getSearchType()==OrganizationInfoDTO.LOADBY_UID_CONTACT){
				aHeadObj.setRequestType("ByContact");
			}
			if(feeds==true){
				aHeadObj.setOPPSource("feeds");
			}

			if(aSrchParmObj.getUID()>0){
				aHeadObj.setOPPUID(aSrchParmObj.getUID());
			}
			aListObj.add(aHeadObj);
		}
		return iRetCode;
	}
	// end-of method getOpportunityDBList()

	/**
	* load one opportunities from table org_opportunitiesinfo
	*/
	public int loadOneOpportunityFromDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, int iType, int iIdNum, String InString, String aszRailsDB ){
        String aszURLAliasFormat = "volunteer/"; // aOrgInfoObj.getPathAutoOppPattern();
		return loadOneOpportunityFromDB(pConn, aHeadObj, iType, iIdNum, InString, aszURLAliasFormat, aszRailsDB );
	}
	public int loadOneOpportunityFromDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, int iType, int iIdNum, String InString, String aszURLAliasFormat, String aszRailsDB ){
		int iRetCode=0;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String aszSQL2=null ;
		String aszTemp="", aszNID="",aszSQLtemp ="";
        MethodInit("loadOneOpportunityFromDB");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;
		boolean feed=false, b_natlassoc=false;
		String mainDB=aszDrupalDB;
		if( iType== OrganizationInfoDTO.LOADBY_URL_ALIAS_FEED || iType==OrganizationInfoDTO.LOADBY_NID_FEED){
			mainDB=aszFeedsDB;
			feed=true;
		}else if( iType== OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS || iType==OrganizationInfoDTO.LOADBY_NID_JOBS_FEED){
			mainDB=aszDataDB+aszFeedsTable;
			feed=true;
			
		}
		if( aHeadObj.getRequestType().equals("natlassoc")){
			b_natlassoc=true;
		}
		String aszSQL101 = "SELECT SQL_CACHE n.nid node_id, n.vid version_id, n.uid, n.moderate, n.status, n.title, nr.body, nr.teaser, n.created, n.changed, " +
				"cckOpp.field_volopp_requirements_value, " +
				"cckOpp.field_volopp_faith_req_value, cckOpp.field_volopp_creed_value, cckOpp.field_volopp_references_value, " +
				"cckOpp.field_volopp_num_vol_opp_value, cckOpp.field_volopp_num_served_by_org_value, " +
				"cckOpp.field_volopp_hourly_commitmt_value, " +
				"cckOpp.field_volopp_commit_per_value, cckOpp.field_volopp_bg_check_value, " +
				"cckOpp.field_volopp_start_date_value, " +
				"cckOpp.field_volopp_end_date_value, cckOpp.field_volopp_requirements_format, " +
				"cckOpp.field_volopp_org_link_url, cckOpp.field_volopp_org_link_title, cckOpp.field_volopp_volunteer_link_url," +
				"cckOpp.field_volopp_org_link_attributes, " +
				"cckOpp.field_volopp_group_min_value, cckOpp.field_volopp_group_max_value, " +
				"cckOpp.field_volopp_cost_pp_value, " +
				"cckOpp.field_volopp_opp_id_value, " + // --> legacy id 2009-07-20 - but still used b/c URLs are indexed
				"cckOpp.field_volopp_url_id_value, " +
				"cckOpp.field_volopp_vols_needed_value, " +
				"cckOpp.field_volopp_stipend_paid_value, cckOpp.field_volopp_cost_includes_value, " +
				"cckOpp.field_volopp_details_value, cckOpp.field_volopp_app_deadline_value, " +
				"cckOpp.field_volopp_cost_timeframe_value, cckOpp.field_volopp_num_vol_org_value, " +
				"cckOpp.field_volopp_org_name_value, cckOpp.field_volopp_popularity_value, " +
				"cckOpp.field_volopp_org_reference_nid, " + // NEW - nid of owner org
				"cckOpp.field_stm_references_value, " +
				"cckOpp.field_volopp_private_value, cckOpp.field_volopp_hq_or_offsite_value, " +
				"cckOpp.field_resume_required_value, cckOpp.field_cover_letter_required_value, cckOpp.field_intern_type_value " +
				",loc.street, loc.city, loc.province, loc.postal_code, loc.country, loc.latitude, loc.longitude, " +
				"cckOpp.field_volopp_questionnaire_type_value as questionnaire_type, " +
				"IFNULL((SELECT oq.id from " + aszRailsDB + "opportunities_questionnaires as oq WHERE oq.opportunity_nid = n.nid order by oq.created_at desc limit 1), 0) as opportunities_questionnaire_id, " +
				"IFNULL((SELECT oq.questionnaire_id from " + aszRailsDB + "opportunities_questionnaires as oq WHERE oq.opportunity_nid = n.nid order by oq.created_at desc limit 1), 0) as questionnaire_id";
		if( feed==false){
			aszSQL101+=", exp.expire, (exp.expire - (UNIX_TIMESTAMP({fn now()}) ) ) expired "; // get the difference between expire and now
		}
		String aszSQLfrom = " FROM  " + mainDB + "node n, " + mainDB + "node_revisions nr, " + 
			mainDB + "content_type_volunteer_opportunity cckOpp, " + 
			mainDB + "location_instance loc_inst," + mainDB + "location loc ";
		if( feed==false ){
			aszSQLfrom+=", " + 
				mainDB + "auto_expire exp " ;
		}
        String aszSQLwhere = " n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOpp.nid AND nr.vid=cckOpp.vid " +
	        " AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid ";
		if( feed==false ){
			aszSQLwhere+=" AND n.nid=exp.nid " ;
		}
        
        //boolean bOldUsermail = true;
        boolean bOldUsermail = false;
        boolean bJoinsUsermail = false;
        if(bOldUsermail == false && feed==false){
        	bJoinsUsermail=true;
        	aszSQL101 += ", contact.uid contact_uid , contact.primary_record ";
        	aszSQLfrom = aszSQLfrom + " , " + mainDB + "usermail contact ";
    		aszSQLwhere += " AND contact.opp_nid = cckOpp.nid AND contact.primary_record=1 " ;
        }
        
        
        String aszPublished = " and status=1 ";
        // see if this is going through a portal or not
        String aszEndWhere = "";
        int iPortalUID = aHeadObj.getPortalUID();
        if( iPortalUID > 0 && iPortalUID!=iMeetTheNeedUID &&
            	(! aHeadObj.getRequestType().equalsIgnoreCase("myResultsAdminSelect")) && 
             	b_natlassoc==false
        ){
    		aszPublished="";//" AND status=1 "; might have unpublished private ones that they don't want listed on all versions of the site
        }

        switch( iType ){
    		case OrganizationInfoDTO.LOADBY_ORGNUMBER :
	    		if(iIdNum < 1) return -1;
	    		aszSQLwhere = " WHERE cckOpp.field_volopp_url_id_value=" + iIdNum + " AND " + aszSQLwhere ;
	        	break;
    		case OrganizationInfoDTO.LOADBY_ORGNUMBER_PUBLIC :
	    		if(iIdNum < 1) return -1;
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
					aszPublished ;  //only view if published
	        	break;
    		case OrganizationInfoDTO.LOADBY_OPPURL_ID_W_ALIAS :
	    		if(iIdNum < 1) return -1;
	    		aszSQLwhere = " WHERE cckOpp.field_volopp_url_id_value=" + iIdNum + " AND " + aszSQLwhere +
					aszPublished ;  //only view if published
	        	break;
    		case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
    			// is this only ever used by a manager, or 
	    		if(iIdNum < 1) return -1;
	        	if(aHeadObj.getOPPUID() < 1){
	        		aHeadObj.appendErrorMsg("user ID required  ");
	        		return -1;
	        	}
	        	if(aHeadObj.getNatlAssociationNID() < 1){
	        		//aHeadObj.appendErrorMsg("no access  ");
	        		return -1;
	        	}
	        	aszSQLfrom +=  
	    				",\n       " + mainDB + "term_node OrgAffil, " + mainDB + "term_data OrgAffilTermData " +
	    				",\n       " + mainDB + "term_node OrgAffil2 " +
	        	 		",\n       " + mainDB + "node natlassoc " ;
//    	 		",\n       " + aszDrupalDB + "node content_field_natlassoc_manager manager " +
//        		"\n       AND manager.field_natlassoc_manager_uid=" + iCurrentUID + // how to check permissions???
//        		"\n       AND natlassoc.nid=manager.nid  " +
	        	aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
//	        		"\n       AND natlassoc.nid=manager.nid  " +
	        		"\n       AND natlassoc.type LIKE 'national_association'	" +
	    			"\n       AND natlassoc.nid = " + aHeadObj.getNatlAssociationNID() + " " +
//	    			"\n       AND natlassoc.title LIKE '" + aHeadObj.getNatlAssociation() + "' " +
					"\n       AND OrgAffil.vid=natlassoc.vid" +
					"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
					"\n       AND OrgAffil.tid=OrgAffil2.tid " +
					"\n       AND OrgAffil2.nid=cckOpp.field_volopp_org_reference_nid " +
	    			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " + 
		        	" \nGROUP BY\n cckOpp.vid " +
		        	" \nORDER BY\n org_nid, title, changed";
/*
	        	aszSQLfrom = aszSQLfrom + //" , " + mainDB + "content_field_volorg_owner org_owner " +
	        			",\n       " + aszDrupalDB + "term_node OrgAffil, " + mainDB + "term_data OrgAffilTermData " +
            	 		",\n       " + aszDrupalDB + "term_relation tr " + 
        				",\n       " + aszDrupalDB + "term_node NatlAffil, " + mainDB + "term_data NatlAffilTermData "  ;
	        	aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
	        			"\n       AND NatlAffil.tid=" + aHeadObj.getNatlAffiliationTID() + 
        				"\n       AND OrgAffil.nid=cckOpp.field_volopp_org_reference_nid" +
        				"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND tr.tid2=OrgAffil.tid AND tr.tid1=NatlAffil.tid	" +
                		"\n       AND NatlAffil.tid=NatlAffilTermData.tid " +
                		"\n       AND NatlAffilTermData.vid=" +  vidNatlAffil + " " ;
*/	        	
	        	aszSQL2 = aszSQL101 + aszSQLfrom + aszSQLwhere;
	        	break;
    		case OrganizationInfoDTO.LOADBY_ORGNID :
	    		if(iIdNum < 1) return -1;
	    		aszSQLfrom = aszSQLfrom + " , " + mainDB + "content_field_volorg_owner org_owner ";
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
	    			" AND org_owner.nid = cckOpp.field_volopp_org_reference_nid " +
	    			" AND org_owner.field_volorg_owner_uid=" + aHeadObj.getOPPUID() + " ";
	        	break;
    		case OrganizationInfoDTO.LOADBY_OPPNID:
	    		if(iIdNum < 1) return -1;
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere;
    			break;
    		case OrganizationInfoDTO.LOADBY_UID_CONTACT :
	    		if(iIdNum < 1) return -1;
	            aszSQLfrom = aszSQLfrom + " , " + mainDB + "usermail usermail ";
		    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
		    			" AND usermail.opp_nid = cckOpp.nid AND usermail.uid=" + aHeadObj.getOPPUID() + " ";
	        	break;
    		case OrganizationInfoDTO.LOADBY_SITEADMIN :
	    		if(iIdNum < 1) return -1;
	    		aszSQLfrom = aszSQLfrom + " , " + mainDB + "content_field_volorg_owner org_owner ";
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
	    			" AND org_owner.nid = cckOpp.field_volopp_org_reference_nid " ;
	        	break;
    		case OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC :
	    		if(iIdNum < 1) return -1;
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
					aszPublished ;  //only view if published
	        	break;
    		case OrganizationInfoDTO.LOADBY_URL_ALIAS :
	    		if(InString.length() < 1) return -1;
	    		aszTemp=""; aszNID="";
	    		// query for the NID by looking at the url_alias table
	    		aszSQLtemp = " SELECT src FROM " + mainDB + "url_alias alias " + " WHERE alias.dst LIKE '" + aszURLAliasFormat + InString + "'";
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode = pConn.RunQuery(aszSQLtemp);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				//iRetCode=-1;
				if(pConn.getNextResult()){
		            aszTemp = (pConn.getDBString("src"));
		            int iLength = aszTemp.length();
		            aszNID = aszTemp.substring(5,iLength); // - for 'node/'
					try{
						iIdNum = Integer.parseInt(aszNID);
					}catch(NumberFormatException e){
						//return -999;
					}
					aHeadObj.setOPPNID(iIdNum);
				}
	        	iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	    		if(iIdNum < 1){
	    			// wasn't in the url alias table; try the path_redirect table
	        		aszSQLtemp = " SELECT redirect FROM " + mainDB + "path_redirect alias " + " WHERE alias.source LIKE '" + aszURLAliasFormat + InString + "'";
	    			iRetCode=pConn.getDBStmt();
	    			if(0 != iRetCode){
	    				pConn.copyErrObj(getErrObj());
	    				ErrorsToLog();
	    				return -999;
	    			}
	    			iRetCode = pConn.RunQuery(aszSQLtemp);
	    			if(0 != iRetCode){
	    				pConn.copyErrObj(getErrObj());
	    				ErrorsToLog();
	    				return -999;
	    			}
	    			//iRetCode=-1;
	    			if(pConn.getNextResult()){
	    	            aszTemp = (pConn.getDBString("redirect"));
	    	            int iLength = aszTemp.length();
	    	            aszNID = aszTemp.substring(5,iLength);
	    	            try{
	    	            	iIdNum = Integer.parseInt(aszNID);
	    	            }catch(Exception e){
							//return -999;
	    	            }
	    				aHeadObj.setOPPNID(iIdNum);
	    			}
	            	iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	        		if(iIdNum < 1){
	        			return -1;
	        		}
	    		}
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
    				aszPublished ;  //only view if published
	        	break;

    		case OrganizationInfoDTO.LOADBY_URL_ALIAS_FEED :
	    		if(InString.length() < 1) return -1;
	    		aszTemp=""; aszNID="";
	    		// query for the NID by looking at the url_alias table
	    		aszSQLtemp = " SELECT src FROM " + aszFeedsDB + "url_alias alias " + " WHERE alias.dst LIKE '" + aszURLAliasFormat + InString + "'";
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode = pConn.RunQuery(aszSQLtemp);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode=-111;
				if(pConn.getNextResult()){
		            aszTemp = (pConn.getDBString("src"));
		            int iLength = aszTemp.length();
		            aszNID = aszTemp.substring(5,iLength); // - for 'node/'
					iIdNum = Integer.parseInt(aszNID);
					aHeadObj.setOPPNID(iIdNum);
				}
	        	iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	        		if(iIdNum < 1){
	        			return -111;// not found; doesn't exist
	        		}
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
    				aszPublished ;  //only view if published
	        	break;

    		case OrganizationInfoDTO.LOADBY_NID_FEED :
	    		iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	        		if(iIdNum < 1){
	        			return -111;// not found; doesn't exist
	        		}
	    		aszSQLwhere = " WHERE cckOpp.nid=" + iIdNum + " AND " + aszSQLwhere +
    				aszPublished ;  //only view if published
	        	break;
	        default:
				setErr("request type not supported");
	            return -1;
	    }

        String aszSQLFrom_a = aszSQLfrom;
        String aszSQLFrom_b = aszSQLfrom;
        String aszSQLWhere_b = "";
        if( iPortalUID > 0 && iPortalUID!=iMeetTheNeedUID &&
            	(! aHeadObj.getRequestType().equalsIgnoreCase("myResultsAdminSelect")) && 
             	(b_natlassoc==false) && mainDB==aszDrupalDB
        ){
    		// include field_volopp_private_value,field_volopp_hq_or_offsite_value
        	aszSQLFrom_a = aszSQLfrom + ", " + mainDB + "flag_content fl ";
        	aszEndWhere += " AND cckOpp.nid=fl.content_id AND fl.fid=" + iFlagFavorite + " AND fl.uid=" + iPortalUID + " ";

        	aszSQLFrom_b = aszSQLfrom;
    		aszSQLWhere_b = " AND n.uid=" + iPortalUID + " ";
        }
        aszSQL2 = 			aszSQL101 + aszSQLFrom_a + 	aszSQLwhere + 	aszEndWhere;
        String aszSQL_b = 	aszSQL101 + aszSQLFrom_b + 	aszSQLwhere + 	aszSQLWhere_b ;
        
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
		iRetCode=-111; // no results

		
		if(pConn.getNextResult()){
            iRetCode=0;
            //iInNum = pConn.getDBInt("node_id");
			aHeadObj.setOPPNID(pConn.getDBInt("node_id"));
			aHeadObj.setOPPVID(pConn.getDBInt("version_id"));
			aHeadObj.setOPPOppNumber(pConn.getDBInt("field_volopp_opp_id_value")); // --> legacy id 2009-07-20; still used b/c of indexed URLs
			aHeadObj.setOPPModerate(pConn.getDBInt("moderate"));
			aHeadObj.setOPPPublished(pConn.getDBInt("status"));
			aHeadObj.setOPPTitle(pConn.getDBString("title"));
			aHeadObj.setORGNID(pConn.getDBString("field_volopp_org_reference_nid"));			
			aHeadObj.setOPPUID(pConn.getDBString("uid"));
			aHeadObj.setOPPVolsNeeded(pConn.getDBInt("field_volopp_vols_needed_value"));
			aHeadObj.setOPPNumVolOpp(pConn.getDBInt("field_volopp_num_vol_opp_value"));
			aHeadObj.setOPPCommitHourly(pConn.getDBDouble("field_volopp_hourly_commitmt_value"));
			aHeadObj.setOPPCommitType(pConn.getDBString("field_volopp_commit_per_value"));
			aHeadObj.setOPPStartDtNum(pConn.getDBInt("field_volopp_start_date_value"));
			aHeadObj.setOPPStartDt(pConn.getDBTimestamp("field_volopp_start_date_value"));
			aHeadObj.setOPPEndDtNum(pConn.getDBInt("field_volopp_end_date_value"));
			aHeadObj.setOPPEndDt(pConn.getDBTimestamp("field_volopp_end_date_value"));
			aHeadObj.setOPPCreateDtNum(pConn.getDBInt("created"));// added 2009-05-26
			aHeadObj.setOPPUpdateDtNum(pConn.getDBInt("changed"));// added 2009-05-26
			aHeadObj.setOPPRequiredCodeType(pConn.getDBString("field_volopp_faith_req_value"));
			aHeadObj.setOPPRequiredCodeDesc(pConn.getDBString("field_volopp_creed_value"));
			aHeadObj.setOPPDescription(replaceLineBreak(pConn.getDBString("body")));
			aHeadObj.setOPPRequirements(replaceLineBreak(pConn.getDBString("field_volopp_requirements_value")));
			aHeadObj.setORGNumServed(pConn.getDBInt("field_volopp_num_served_by_org_value"));
			aHeadObj.setOPPBackgroundCheck(pConn.getDBString("field_volopp_bg_check_value"));
			aHeadObj.setORGOrgName(pConn.getDBString("field_volopp_org_name_value"));
			aHeadObj.setOPPOrgName(pConn.getDBString("field_volopp_org_name_value"));
			aHeadObj.setOPPGroupMin(pConn.getDBInt("field_volopp_group_min_value"));
			aHeadObj.setOPPGroupMax(pConn.getDBInt("field_volopp_group_max_value"));
			aHeadObj.setOPPPopularity(pConn.getDBInt("field_volopp_popularity_value"));
			aHeadObj.setOPPAmntPd(pConn.getDBString("field_volopp_stipend_paid_value"));
			aHeadObj.setOPPCostInclude(replaceLineBreak(pConn.getDBString("field_volopp_cost_includes_value")));
			aHeadObj.setOPPAddDetails(replaceLineBreak(pConn.getDBString("field_volopp_details_value")));
			aHeadObj.setOPPAppDeadline(pConn.getDBString("field_volopp_app_deadline_value"));
			aHeadObj.setOPPReferenceReq(pConn.getDBString("field_volopp_references_value"));
			aHeadObj.setOPPCostUsd(pConn.getDBDouble("field_volopp_cost_pp_value"));
			aHeadObj.setOPPCostTerm(pConn.getDBString("field_volopp_cost_timeframe_value"));
			aHeadObj.setOPPApplicDeadlineNum(pConn.getDBInt("field_volopp_app_deadline_value"));
			aHeadObj.setOPPHQorOffSite(pConn.getDBString("field_volopp_hq_or_offsite_value"));
			aHeadObj.setOPPPrivate(pConn.getDBInt("field_volopp_private_value"));
			aHeadObj.setOPPAddrLine1(pConn.getDBString("street"));
			aHeadObj.setOPPAddrCity(pConn.getDBString("city"));
			aHeadObj.setOPPAddrStateprov(pConn.getDBString("province"));
			aHeadObj.setOPPAddrPostalcode(pConn.getDBString("postal_code"));
			aHeadObj.setOPPAddrCountryName(pConn.getDBString("country"));
			aHeadObj.setResumeRequired(pConn.getDBInt("field_resume_required_value"));
			aHeadObj.setCoverLetterRequired(pConn.getDBInt("field_cover_letter_required_value"));
			aHeadObj.setLatitude(pConn.getDBString("latitude"));
			aHeadObj.setLongitude(pConn.getDBString("longitude"));
			aHeadObj.setQuestionnaireType(pConn.getDBString("questionnaire_type"));
			if(aHeadObj.getQuestionnaireType().equals("online")) {
			  aHeadObj.setQuestionnaireId(pConn.getDBInt("questionnaire_id"));
			  aHeadObj.setOpportunitiesQuestionnaireId(pConn.getDBInt("opportunities_questionnaire_id"));
			}
			if( feed==false ){
				aHeadObj.setOPPExpirationDt(pConn.getDBInt("expire"));
				aHeadObj.setOPPExpirationTime(pConn.getDBInt("expired")); // expiration date - today; if negative, then expired
			}else{
				aHeadObj.setOPPSource("feeds");
				aHeadObj.setOPPReferralURL(pConn.getDBString("field_volopp_volunteer_link_url"));
			}
			aHeadObj.setOPPSTMReferences(replaceLineBreak(pConn.getDBString("field_stm_references_value")));
			aHeadObj.setOPPDescTeaser(pConn.getDBString("teaser"));
	        if(bOldUsermail == false && bJoinsUsermail == true){
	        	if(pConn.getDBInt("primary_record") == 1){
	        		aHeadObj.setOPPPrimaryPersonUID(pConn.getDBInt("contact_uid"));
	        	}
	        }
			aHeadObj.setOPPInternType(pConn.getDBString("field_intern_type_value"));
		}else{

			iRetCode = pConn.RunQuery(aszSQL_b);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode=-111; // no results

			if(pConn.getNextResult()){
	            iRetCode=0;
	            //iInNum = pConn.getDBInt("node_id");
				aHeadObj.setOPPNID(pConn.getDBInt("node_id"));
				aHeadObj.setOPPVID(pConn.getDBInt("version_id"));
				aHeadObj.setOPPOppNumber(pConn.getDBInt("field_volopp_opp_id_value")); // --> legacy id 2009-07-20; still used b/c of indexed URLs
				aHeadObj.setOPPModerate(pConn.getDBInt("moderate"));
				aHeadObj.setOPPPublished(pConn.getDBInt("status"));
				aHeadObj.setOPPTitle(pConn.getDBString("title"));
				aHeadObj.setORGNID(pConn.getDBString("field_volopp_org_reference_nid"));			
				aHeadObj.setOPPUID(pConn.getDBString("uid"));
				aHeadObj.setOPPVolsNeeded(pConn.getDBInt("field_volopp_vols_needed_value"));
				aHeadObj.setOPPNumVolOpp(pConn.getDBInt("field_volopp_num_vol_opp_value"));
				aHeadObj.setOPPCommitHourly(pConn.getDBDouble("field_volopp_hourly_commitmt_value"));
				aHeadObj.setOPPCommitType(pConn.getDBString("field_volopp_commit_per_value"));
				aHeadObj.setOPPStartDtNum(pConn.getDBInt("field_volopp_start_date_value"));
				aHeadObj.setOPPStartDt(pConn.getDBTimestamp("field_volopp_start_date_value"));
				aHeadObj.setOPPEndDtNum(pConn.getDBInt("field_volopp_end_date_value"));
				aHeadObj.setOPPEndDt(pConn.getDBTimestamp("field_volopp_end_date_value"));
				aHeadObj.setOPPCreateDtNum(pConn.getDBInt("created"));// added 2009-05-26
				aHeadObj.setOPPUpdateDtNum(pConn.getDBInt("changed"));// added 2009-05-26
				aHeadObj.setOPPRequiredCodeType(pConn.getDBString("field_volopp_faith_req_value"));
				aHeadObj.setOPPRequiredCodeDesc(pConn.getDBString("field_volopp_creed_value"));
				aHeadObj.setOPPDescription(replaceLineBreak(pConn.getDBString("body")));
				aHeadObj.setOPPRequirements(replaceLineBreak(pConn.getDBString("field_volopp_requirements_value")));
				aHeadObj.setORGNumServed(pConn.getDBInt("field_volopp_num_served_by_org_value"));
				aHeadObj.setOPPBackgroundCheck(pConn.getDBString("field_volopp_bg_check_value"));
				aHeadObj.setORGOrgName(pConn.getDBString("field_volopp_org_name_value"));
				aHeadObj.setOPPOrgName(pConn.getDBString("field_volopp_org_name_value"));
				aHeadObj.setOPPGroupMin(pConn.getDBInt("field_volopp_group_min_value"));
				aHeadObj.setOPPGroupMax(pConn.getDBInt("field_volopp_group_max_value"));
				aHeadObj.setOPPPopularity(pConn.getDBInt("field_volopp_popularity_value"));
				aHeadObj.setOPPAmntPd(pConn.getDBString("field_volopp_stipend_paid_value"));
				aHeadObj.setOPPCostInclude(replaceLineBreak(pConn.getDBString("field_volopp_cost_includes_value")));
				aHeadObj.setOPPAddDetails(replaceLineBreak(pConn.getDBString("field_volopp_details_value")));
				aHeadObj.setOPPAppDeadline(pConn.getDBString("field_volopp_app_deadline_value"));
				aHeadObj.setOPPReferenceReq(pConn.getDBString("field_volopp_references_value"));
				aHeadObj.setOPPCostUsd(pConn.getDBDouble("field_volopp_cost_pp_value"));
				aHeadObj.setOPPCostTerm(pConn.getDBString("field_volopp_cost_timeframe_value"));
				aHeadObj.setOPPApplicDeadlineNum(pConn.getDBInt("field_volopp_app_deadline_value"));
				aHeadObj.setOPPHQorOffSite(pConn.getDBString("field_volopp_hq_or_offsite_value"));
				aHeadObj.setOPPPrivate(pConn.getDBInt("field_volopp_private_value"));
				aHeadObj.setOPPAddrLine1(pConn.getDBString("street"));
				aHeadObj.setOPPAddrCity(pConn.getDBString("city"));
				aHeadObj.setOPPAddrStateprov(pConn.getDBString("province"));
				aHeadObj.setOPPAddrPostalcode(pConn.getDBString("postal_code"));
				aHeadObj.setOPPAddrCountryName(pConn.getDBString("country"));
				aHeadObj.setLatitude(pConn.getDBString("latitude"));
				aHeadObj.setLongitude(pConn.getDBString("longitude"));
				aHeadObj.setOPPInternType(pConn.getDBString("field_intern_type_value"));
				if( feed==false ){
					aHeadObj.setOPPExpirationDt(pConn.getDBInt("expire"));
					aHeadObj.setOPPExpirationTime(pConn.getDBInt("expired")); // expiration date - today; if negative, then expired
				}else{
					aHeadObj.setOPPSource("feeds");
					aHeadObj.setOPPReferralURL(pConn.getDBString("field_volopp_volunteer_link_url"));
				}
				aHeadObj.setOPPSTMReferences(replaceLineBreak(pConn.getDBString("field_stm_references_value")));
				aHeadObj.setOPPDescTeaser(pConn.getDBString("teaser"));
		        if(bOldUsermail == false && bJoinsUsermail == true){
		        	if(pConn.getDBInt("primary_record") == 1){
		        		aHeadObj.setOPPPrimaryPersonUID(pConn.getDBInt("contact_uid"));
		        	}
		        }
			}else{
				return iRetCode;
			}
		}
		
		// ************ Load URL ALIAS if it exists ********************
		// URL ALIAS for opportunity
		aszSQL2=null ;
		if(aHeadObj.getOPPNID() > 1){
			aszSQL101 = " SELECT SQL_CACHE dst FROM " + 
				mainDB + "url_alias " +
				"WHERE src like 'node/" + aHeadObj.getOPPNID() + "' " ;
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
				aHeadObj.setOPPUrlAlias(pConn.getDBString("dst"));
			}
		}

		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
        boolean bLoadByMethod = false;
		if ( 	(iType==OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNID ) ||
        		(iType==OrganizationInfoDTO.LOADBY_OPPNID) ||
        		(iType==OrganizationInfoDTO.LOADBY_UID_CONTACT ) ||
        		(iType==OrganizationInfoDTO.LOADBY_UID_ADMIN ) ||
        		(iType==OrganizationInfoDTO.LOADBY_OPPCONTACTS ) ||
        		(iType==OrganizationInfoDTO.LOADBY_NATL_ASSOC) ||
        		(iType==OrganizationInfoDTO.LOADBY_SITEADMIN ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNUMBER ) ||
        		(iType==OrganizationInfoDTO.LOADBY_OPPURL_ID_W_ALIAS ) ||
        		(iType==OrganizationInfoDTO.LOADBY_URL_ALIAS ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNUMBER_PUBLIC ) 
        ){
			bLoadByMethod = true;
		}

		// Faith Specifications
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name faith, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidFaithSpec + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPFaithSpecTID(pConn.getDBInt("tid"));
		}

		
		// Service Areas
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name service_areas, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidService + " " ;
   		if(aHeadObj.getOPPVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
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
		//iRetCode=-1;
		index=0;
		a_iTemp= new int[50];
		while(pConn.getNextResult() && index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
            aszTemp=pConn.getDBString("service_areas");
			aHeadObj.setServiceAreas(aHeadObj.getServiceAreas() + iTemp + ",");
			aHeadObj.setOPPCategories(aHeadObj.getOPPCategories() + aszTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setServiceAreasArray(a_iContainer);
		

		// Skills
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidSkill + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPSkills(pConn.getDBString("skills"));
			aHeadObj.setOPPSkills1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setOPPSkills(aHeadObj.getOPPSkills()+", "+pConn.getDBString("skills"));
				
				aHeadObj.setOPPSkills2(pConn.getDBString("skills"));
				aHeadObj.setOPPSkills2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setOPPSkills(aHeadObj.getOPPSkills()+", "+pConn.getDBString("skills"));

					aHeadObj.setOPPSkills3(pConn.getDBString("skills"));	
					aHeadObj.setOPPSkills3TID(pConn.getDBInt("tid"));
				}				
			}
		}

		// Volunteer Information - Great for Kids, Groups, Teens, Seniors
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name focus_areas, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolInfo + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPFocusAreas(pConn.getDBString("focus_areas"));
			aHeadObj.setOPPGreatFor1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setOPPFocusAreas2(pConn.getDBString("focus_areas"));
				aHeadObj.setOPPGreatFor2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setOPPFocusAreas3(pConn.getDBString("focus_areas"));
					aHeadObj.setOPPGreatFor3TID(pConn.getDBInt("tid"));
					if(pConn.getNextResult()){
						aHeadObj.setOPPFocusAreas4(pConn.getDBString("focus_areas"));
						aHeadObj.setOPPGreatFor4TID(pConn.getDBInt("tid"));
						if(pConn.getNextResult()){
							aHeadObj.setOPPFocusAreas5(pConn.getDBString("focus_areas"));
							aHeadObj.setOPPGreatFor5TID(pConn.getDBInt("tid"));
						}				
					}				
				}				
			}
		}

		/** Benefits Offered - Room & Board, Stipend, Medical Insurance, Transportation, AmeriCorps*/

		
		// Benefits Offered
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name benefits, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidBenefits + " " ;
   		if(aHeadObj.getOPPVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
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
		//iRetCode=-1;
		index=0;
		a_iTemp= new int[50];
		while(pConn.getNextResult() && index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
            aszTemp=pConn.getDBString("benefits");
			aHeadObj.setOPPBenefits(aHeadObj.getOPPBenefits() + aszTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setBenefitsArray(a_iContainer);
		
		
		
		// Position Type - Local, Virtual, Work Study, Short-term Missions / Internship....
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name pos_type, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPosType + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPStatus(pConn.getDBString("pos_type"));
			aHeadObj.setOPPPositionTypeTID(pConn.getDBString("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setOPPStatus2(pConn.getDBString("pos_type"));
				aHeadObj.setOPPPositionType2TID(pConn.getDBString("tid"));
			}
		}

		// Load whether this is scheduled for particular date or not 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name sched_date, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidSchedDate + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPDaterequired(pConn.getDBString("sched_date"));
			aHeadObj.setOPPDaterequiredTID(pConn.getDBInt("tid"));
		}

		// Load Language Spoken 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name lang_spoken, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLangSpoken + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPLanguages(pConn.getDBString("lang_spoken"));
			aHeadObj.setOPPLanguageTID(pConn.getDBInt("tid"));
		}


		// Load whether this is a WORK STUDY opportunity 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name work_study, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidWorkStudy + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPWorkStudy(pConn.getDBString("work_study"));
			aHeadObj.setOPPWorkStudyTID(pConn.getDBInt("tid"));
		}

		// Load trip length with opportunity 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name trip_length, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidTripLength + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPTripLength(pConn.getDBString("trip_length"));
			aHeadObj.setOPPTripLengthTID(pConn.getDBInt("tid"));
		}


		// Load Position Duration/Frequency: one time or ongoing opp
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name pos_frequency, d.tid tid FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPosFreq + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setOPPFreq(pConn.getDBString("pos_frequency"));
			aHeadObj.setOPPFrequencyTID(pConn.getDBInt("tid"));
		}
		
		// ===============================================
		// ======== START Organizational Data ============
		// ===============================================
		
		// Organization's Denominational Affiliation
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name denom_affil FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidDenomAffil + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGAffiliation(pConn.getDBString("denom_affil"));
		}
		// Organization's Organizational Affiliation/Partners
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name org_affil FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidOrgAffil + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGPartner(pConn.getDBString("org_affil"));
			if(pConn.getNextResult()){
				aHeadObj.setORGPartner2(pConn.getDBString("org_affil"));
				if(pConn.getNextResult()){
					aHeadObj.setORGPartner3(pConn.getDBString("org_affil"));	
					if(pConn.getNextResult()){
						aHeadObj.setORGPartner4(pConn.getDBString("org_affil"));	
						if(pConn.getNextResult()){
							aHeadObj.setORGPartner5(pConn.getDBString("org_affil"));	
						}				
					}				
				}				
			}
		}
		// Organization's Member Type (Christian Non-Profit, Non-Christian Non-Profit, Church)
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name member_type FROM " + 
			mainDB + "node_revisions nr, " + mainDB + "term_node t, " + mainDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidMemberType + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getOPPNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getOPPVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;// required field from organization - can't force required until we clean up records < Sept 5, 2006, when we started requiring;
		// need to propogate member type
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGMembertype(pConn.getDBString("member_type"));
		}
		// ===============================================
		// ========== END Organizational Data ============
		// ===============================================
		/**
		 * END TAXONOMY SECTION
		 */
		
		// Load Required Documents...
		//
		String query = "SELECT " +
					  "n.nid                          as nid, " +
				      "n.vid                          as vid, " +
				      "n.title                        as name, " +
				      "doc.field_file_extension_value as extension " +
				    "FROM " +
				      aszDrupalDB + "content_type_application_documents as doc " +
				    "INNER JOIN " +
				      aszDrupalDB + "node as n on doc.nid = n.nid " +
				    "WHERE " +
				      "doc.field_opp_nid_value = '" + aHeadObj.getOPPNID() + "'" +
				    " AND n.status = '1'";
		
		iRetCode = pConn.RunQuery(query);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		aHeadObj.setRequiredDocuments(new LinkedList<RequiredDocumentDTO>());
		while(pConn.getNextResult()) {
			RequiredDocumentDTO doc = new RequiredDocumentDTO();
			doc.setNid(pConn.getDBInt("nid"));
			doc.setVid(pConn.getDBInt("vid"));
			doc.setName(pConn.getDBString("name"));
			doc.setExtension(pConn.getDBString("extension"));
			aHeadObj.getRequiredDocuments().add(doc);
		}
		
		return iRetCode;
	}
	// end-of method loadOneOpportunityFromDB()


    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>
    //=== END   Table org_opportunitiesinfo ===>


	/**
	* load one opportunities from table org_opportunitiesinfo
	*/
	public int loadFromFeedsDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, int iType, String aszType, int iIdNum, String InString ){
		// aszDataDB + aszJobsFeedsTable
		int iRetCode=0;
		String aszSQL2=null ;
		String aszTemp="", aszNID="",aszSQLtemp ="";
	    MethodInit("loadFromFeedsDB");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;
	    String aszPublished = " AND status=1 ";
		if(aszType.equalsIgnoreCase("jobs")){
			aszPublished+=" AND position_type LIKE '" + aszJob +"' ";
		}else{
			aszPublished+=" AND position_type NOT LIKE '" + aszJob +"' ";
		}
/*
 * 		String aszSQL101 = "SELECT " + aszFeedsTable + ".id, title, changed, created, org_reference_id, teaser, description, requirements, " +
				" org_name, start_date, end_date, " + aszFeedsTable + ".source, url_alias, full_url_alias, referralurl, i_want_to_help, " +
				" org_url_alias, published, faith_req, faith_tid,  " +
				" street, city, province, postal_code, country, latitude, longitude, " +
				" city_tax, state_tax, country_tax, region1, region2, " +
				" city_tax_tid, state_tax_tid, country_tax_tid, region1_tax_tid, region2_tax_tid, " +
				" popularity, position_type_1, position_type_tid_1, " +
				" categories, great_for, holland_codes, org_affils, denom_affils, frequency, frequency_tid" +
				" work_study, work_study_tid, url as source_url, search_page_logo_url, detail_page_logo_url" +
				" FROM  " + aszDataDB + aszFeedsTable +
				" LEFT OUTER JOIN " + aszDataDB + aszFeedSourceTable +
				" ON " + aszFeedsTable + ".source = " + aszFeedSourceTable + ".name";

 */
		String aszSQL101 = "SELECT " + aszOppsTable + ".id, title, changed, created, org_nid, short_description, description, requirements, " +
				" org_name, start_date, end_date, " + aszOppsTable + ".source, url_alias, referralurl, volunteer_link_url, " + //, full_url_alias
				" org_url_alias, status, faith_req, faith,  " +//, faith_tid
				" location_street, location_city, location_province, location_postal_code, location_country, latitude, longitude, " +
				" region, " +
				" popularity, position_type, " +
				" service_areas, great_for, holland_codes, org_affil, denom, frequency, " +
				" work_study "+//, url as source_url, search_page_logo_url, detail_page_logo_url" +
				" , city_name, province_name, country_name, url as source_url, search_page_logo_url, detail_page_logo_url "+
				" FROM  " + aszSocialGraphDB + aszOppsTable  +
				" LEFT OUTER JOIN " + aszSocialGraphDB + aszFeedSourceTable +
				" ON " + aszSocialGraphDB + aszOppsTable + ".source = " + aszFeedSourceTable + ".name";

/*				
		" LEFT OUTER JOIN " + aszDataDB + aszFeedSourceTable +
				" ON " + aszFeedsTable + ".source = " + aszFeedSourceTable + ".name";
*/
/*
 					 full_url_alias, faith_tid, frequency_tid,work_study_tid, 
, position_type_1, position_type_tid_1
				" region1, region1_tax_tid, region2, region2_tax_tid, " +

				" city_tax, " +
				" city_tax_tid, state_tax_tid, country_tax_tid, " +
*/		
		String aszSQLwhere = " ";	    
	    switch( iType ){
			case OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS :
	    		if(InString.length() < 1) return -1;
	    		aszSQLwhere = " WHERE url_alias LIKE '"+aHeadObj.getRequestType()+"/" + InString + "' " + aszPublished ;  //only view if published
	        	break;
	
			case OrganizationInfoDTO.LOADBY_NID_FEED :
	        	if(iIdNum < 1) return -111;// not found; doesn't exist
	    		aszSQLwhere = " WHERE id=" + iIdNum + aszPublished ;  //only view if published
	        	break;
	        default:
				setErr("request type not supported");
	            return -1;
	    }
	
	
	    aszSQL2 = aszSQL101 + aszSQLwhere;
	    
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
		iRetCode=-111; // no results
		if(pConn.getNextResult()){
	        iRetCode=0;
/*
	        aHeadObj.setOPPFaithSpecTID(pConn.getDBInt("faith_tid"));
			aHeadObj.setCityName(pConn.getDBString("city_tax"));
			aHeadObj.setCityTID(pConn.getDBInt("city_tax_tid"));
			aHeadObj.setStateTID(pConn.getDBInt("state_tax_tid"));
			aHeadObj.setCountryTID(pConn.getDBInt("country_tax_tid"));
			
			aHeadObj.setRegionName(pConn.getDBString("region1"));
			aHeadObj.setRegionTID(pConn.getDBInt("region1_tax_tid"));

			aHeadObj.setOPPStatus(pConn.getDBString("position_type_1"));
			aHeadObj.setOPPPositionTypeTID(pConn.getDBString("position_type_tid_1"));
			aHeadObj.setOPPWorkStudyTID(pConn.getDBInt("work_study_tid"));









*/
	        //iInNum = pConn.getDBInt("node_id");
			aHeadObj.setOPPNID(pConn.getDBInt("id"));
			aHeadObj.setOPPPublished(pConn.getDBInt("status"));

			aHeadObj.setOPPTitle(pConn.getDBString("title"));
			
			aHeadObj.setOPPUpdateDtNum(pConn.getDBInt("changed"));
			aHeadObj.setOPPCreateDtNum(pConn.getDBInt("created"));
			aHeadObj.setOPPDescTeaser(pConn.getDBString("short_description"));
			aHeadObj.setOPPDescription(replaceLineBreak(pConn.getDBString("description")));
			aHeadObj.setOPPRequirements(replaceLineBreak(pConn.getDBString("requirements")));
			aHeadObj.setORGOrgName(pConn.getDBString("org_name"));
			aHeadObj.setOPPOrgName(pConn.getDBString("org_name"));
			aHeadObj.setOPPStartDtNum(pConn.getDBInt("start_date"));
			aHeadObj.setORGNID(pConn.getDBInt("org_nid"));
			aHeadObj.setOPPEndDtNum(pConn.getDBInt("end_date"));
			aHeadObj.setOPPSource(pConn.getDBString("source"));
			aHeadObj.setOPPUrlAlias(pConn.getDBString("url_alias"));
			aHeadObj.setOPPReferralURL(pConn.getDBString("referralurl"));
			aHeadObj.setORGUrlAlias(pConn.getDBString("org_url_alias"));
			
			aHeadObj.setOPPAddrLine1(pConn.getDBString("location_street"));
			aHeadObj.setOPPAddrCity(pConn.getDBString("location_city"));
			aHeadObj.setOPPAddrStateprov(pConn.getDBString("location_province"));
			aHeadObj.setOPPAddrPostalcode(pConn.getDBString("location_postal_code"));
			aHeadObj.setOPPAddrCountryName(pConn.getDBString("location_country"));
			aHeadObj.setLatitude(pConn.getDBString("latitude"));
			aHeadObj.setLongitude(pConn.getDBString("longitude"));
			
			aHeadObj.setStateName(pConn.getDBString("province_name"));
			aHeadObj.setCountryName(pConn.getDBString("country_name"));
			aHeadObj.setRegionName(pConn.getDBString("region"));

			aHeadObj.setOPPPopularity(pConn.getDBInt("popularity"));

			aHeadObj.setOPPStatus(pConn.getDBString("position_type"));
			
			aHeadObj.setOPPCategories(pConn.getDBString("service_areas"));
			aHeadObj.setOPPGreatForAreas(pConn.getDBString("great_for"));
			aHeadObj.setHollandCodes(pConn.getDBString("holland_codes"));
			aHeadObj.setOrgAffils(pConn.getDBString("org_affil"));
			aHeadObj.setDenomAffils(pConn.getDBString("denom"));
			
			aHeadObj.setOPPWorkStudy(pConn.getDBString("work_study"));

			
			aHeadObj.setOPPSourceURL(pConn.getDBString("source_url"));
			aHeadObj.setOPPSearchPageLogoURL(pConn.getDBString("search_page_logo_url"));
			aHeadObj.setOPPDetailPageLogoURL(pConn.getDBString("detail_page_logo_url"));


			
//			aHeadObj.setOPPStartDt(pConn.getDBTimestamp("field_volopp_start_date_value"));
//			aHeadObj.setOPPEndDt(pConn.getDBTimestamp("field_volopp_end_date_value"));
//			aHeadObj.setOPPFullUrlAlias(pConn.getDBString("dst"));
/*
/*
			aHeadObj.setOPPCommitHourly(pConn.getDBDouble("field_volopp_hourly_commitmt_value"));
			aHeadObj.setOPPCommitType(pConn.getDBString("field_volopp_commit_per_value"));
			aHeadObj.setOPPRequiredCodeType(pConn.getDBString("field_volopp_faith_req_value"));
			aHeadObj.setOPPBackgroundCheck(pConn.getDBString("field_volopp_bg_check_value"));
			aHeadObj.setOPPReferenceReq(pConn.getDBString("field_volopp_references_value"));
			aHeadObj.setOPPAppDeadline(pConn.getDBString("field_volopp_app_deadline_value"));
			aHeadObj.setOPPApplicDeadlineNum(pConn.getDBInt("field_volopp_app_deadline_value"));
			aHeadObj.setOPPAddDetails(replaceLineBreak(pConn.getDBString("field_volopp_details_value")));
			aHeadObj.setOPPSTMReferences(replaceLineBreak(pConn.getDBString("field_stm_references_value")));

			aHeadObj.setOPPVolsNeeded(pConn.getDBInt("field_volopp_vols_needed_value"));
			aHeadObj.setOPPNumVolOpp(pConn.getDBInt("field_volopp_num_vol_opp_value"));
			aHeadObj.setOPPRequiredCodeDesc(pConn.getDBString("field_volopp_creed_value"));
			aHeadObj.setORGNumServed(pConn.getDBInt("field_volopp_num_served_by_org_value"));
			aHeadObj.setOPPGroupMin(pConn.getDBInt("field_volopp_group_min_value"));
			aHeadObj.setOPPGroupMax(pConn.getDBInt("field_volopp_group_max_value"));
			aHeadObj.setOPPAmntPd(pConn.getDBString("field_volopp_stipend_paid_value"));
			aHeadObj.setOPPCostInclude(replaceLineBreak(pConn.getDBString("field_volopp_cost_includes_value")));
			aHeadObj.setOPPCostUsd(pConn.getDBDouble("field_volopp_cost_pp_value"));
			aHeadObj.setOPPCostTerm(pConn.getDBString("field_volopp_cost_timeframe_value"));
			aHeadObj.setOPPHQorOffSite(pConn.getDBString("field_volopp_hq_or_offsite_value"));
			aHeadObj.setOPPPrivate(pConn.getDBInt("field_volopp_private_value"));
*/			
				
		}
		return iRetCode;
	}
	// end-of method loadFromFeedsDB()




    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>
    //=== START Table organizationinfo ===>


    /**
	* insert a row into table organizationinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertOrganizationIntDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, index=0;
		String aszSQLdrupal101 = "";
		String aszSQLdrupalTemp = "";
		// "auto-"increment the nid and vid in urbmi5_drupal.um_sequences table - then grab last Id...
		// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1, iDeltaMax=0;
		String Qry1=null;
		MethodInit("insertOrganizationIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
/*	not sure when i commented this section out, but I think it's necessary...*/	
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
/**/		
		/*
		 * add to node_revisions
		 */
		String orgDesc = aHeadObj.getORGOrgDescription();
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format,log) " +
				"VALUES(?,?,?,?,UNIX_TIMESTAMP({fn now()}),0,'' ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgDescription()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgDescription()) );
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
		
		
		//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
		Qry1 = "SELECT LAST_INSERT_ID() ";
		iRetCode=pConn.PrepQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
			ivid = pConn.getDBInt("LAST_INSERT_ID()");
			aHeadObj.setORGVID(ivid);
		} else {
			itid=-1;
		}
		iRetCode=-1;
		
	
		// ???? with the portal project, is there any case where we'd want orgs to be private? ie, status=0, moderate=0?
		// add to um_node
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate, title, uid, created, changed) " +
				"VALUES("+ ivid + 
				", 'organization','0','2','1',?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
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
		iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
			inid = pConn.getDBInt("LAST_INSERT_ID()");
			aHeadObj.setORGNID(inid);
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGVID() );
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
		
		
		
//		*******************************
//		**************       inid=     *****************
//		*******************************
//		*******************************
//		*********update um_node_revisions set nid=inid where vid=ivid **********************
//		*******************************
//		*******************************
//		*******************************
		
		
		
		
		
		// add to um_node_access
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
				"VALUES("+ inid +", '0', 'all', '1', '0', '0') ";
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
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

		// add to um_content_type_volunteer_opportunity
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_organization" +
				"(vid,nid," +
				"field_volorg_mission_statemt_value,field_volorg_faith_statemt_value,field_volorg_email_value," +
				"field_volorg_secondary_phone_value," +
				"field_volorg_serve_poor_value,field_volorg_num_served_value,field_volorg_num_volunteers_value," +
				"field_volorg_num_volunteers_intl_value,field_volorg_num_opps_value," +
				"field_volorg_subdomain_value,field_site_interest_value," +
				"field_volorg_website_url,field_volorg_website_attributes," +
				"field_volorg_has_listings_value ," +
				"field_donate_url_url,field_donate_url_attributes,field_donate_url_title," +
				"field_volorg_website_listings_url ,field_volorg_website_listings_attributes,field_volorg_website_listings_title," +
				"field_volorg_local_affil_value,field_volorg_orientation_value," +
				"field_volorg_ein_value," +
				"field_volorg_opps_list_url," +
				"field_volorg_opps_list_title,field_volorg_opps_list_attributes," +
				"field_civicrm_user_id_value, field_volorg_url_id_value, field_volorg_portal_banner_value) " +
				"VALUES("+ ivid +"," + inid + 
				",?,?,?,?" + // mission statement, faith, email, secondary phone number
				",?,?,?" + // serve poor, num served, num vol
				",?,?,?,?" + // field_volorg_num_volunteers_intl_value, field_volorg_num_opps_value, subdomain, site_interest
				",?,'N;'" + // org website url, website attributes, 
				",?" ; // has_listings field_volorg_has_listings_value 
				if(aHeadObj.getORGDonateURL().length()>1){ // if the user has not entered a donation url, then we don't want to enter in values for title, etc
					aszSQLdrupalTemp = ",?,'N;','Donate to this Organization'" ; // donate url, donate attributes, donate title
				}else{
					aszSQLdrupalTemp = ",?,'',''" ; // donate url, donate attributes, donate title
				}
				if(aHeadObj.getORGHasListings().equalsIgnoreCase("Yes") || aHeadObj.getORGHasListings().equalsIgnoreCase("Y")){ // if the user has not entered a donation url, then we don't want to enter in values for title, etc
					aszSQLdrupalTemp += ",?,'N;','View Listings'" ; // listings url, listings attributes, listings title
				}else{
					aszSQLdrupalTemp += ",?,'',''" ; // listings url, listings attributes, listings title
				}
		aszSQLdrupal101 = aszSQLdrupal101 + aszSQLdrupalTemp +
				",?,?" +
				",?" +
				",'http://"+aszDomMain+"/oppsrch.do?method=processOppSearchAll&voltype=&orgnid=" + aHeadObj.getORGNID() + 
				//",'http://www.christianvolunteering.org/oppsrch.do?method=processOppSearchAll&voltype=&orgnumber=" + aHeadObj.getORGOrgNumber() + --> legacy
				"','View this Organization\\'s Opportunities','N;'" + 
				",?,?,?) "; 
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGMissionStatement()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgStmtFaith()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgContactEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOnethirdP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumServed() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrg() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrgIntl() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumOppsOrg() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getSiteInterest() ? 1 : 0 );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGWebpage() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGHasListings() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGDonateURL() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGListingsURL() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGLocalAffil() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGFormalTrain() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGFedTaxId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getPortalBannerURL() );
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
		 * add to um_content_field_volorg_owner
		 */
		// brand new org, so deltamax=0
		iDeltaMax=0;
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_volorg_owner(vid, nid, delta, field_volorg_owner_uid) " +
				"VALUES(" + aHeadObj.getORGVID() + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + aHeadObj.getORGUID() + " ) ";
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
		
		
		/*
		 * add to um_usermail - this user created the org, so by deafult they will receive the emails
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
			"VALUES(" + aHeadObj.getORGUID()+ ",'voleng_org_nid','" + aHeadObj.getORGNID() + "',0,0," + aHeadObj.getORGNID() + ",0) ";
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

		
		/*
		 * add URL alias
		 */
		if(aHeadObj.getORGUrlAlias().length()>1){
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "url_alias " +
					"(src,dst) " +
					"VALUES('node/" + inid + "',?)"; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGUrlAlias() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				int ierror = 98769876;
				ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=0;
		}
		
		/*
		 * add to location
		 */
		// 2008-10-27 - had to add b/c drupal module upgrade changed db structure
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location(street, additional, city, province, postal_code, " +
				"country, source, is_primary) " +
				"VALUES(?,?,?,?,?,?,0,0 ) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrStateprov() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrPostalcode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCountryName() );
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
		iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get tid From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("LAST_INSERT_ID()");
			aHeadObj.setORGLID(ilid);
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
		iRetCode=-1;
		if(aHeadObj.getORGOrgPhone1().length() > 1){
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_phone " +
					"(lid,phone) " +
					"VALUES(" + ilid + ",?)"; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone1() );
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
			iRetCode=0;
		}
		if(aHeadObj.getORGFax1().length() > 1){ // ------------ <--------- should it only insert in this condition???????
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_fax " +
					"(lid,fax) " +
					"VALUES(" + ilid + ",?)"; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGFax1() );
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
			iRetCode=0;
		}

		
		
		/**
		 * START taxonomy section
		 */
		// add Organizational Program Type
		if ( ! aHeadObj.getORGProgramTypes().equalsIgnoreCase("") || aHeadObj.getORGProgramTypes1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getORGProgramTypes() ) + 
				"' OR tid=" +  aHeadObj.getORGProgramTypes1TID() + 
				") AND vid = " + vidProgramType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGProgramTypes1TID(itid);
				aHeadObj.setORGProgramTypes(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		
		// add Denom Affiliation Array
		if ( aHeadObj.getDenominationalAffilsArray()!=null ){
			if ( aHeadObj.getDenominationalAffilsArray().length > 0 ){
				index=0;
				int[] tempDenomAffil = aHeadObj.getDenominationalAffilsArray();
				for (int i=0; i<tempDenomAffil.length; i++){
					int tempTID = tempDenomAffil[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidDenomAffil + " ";
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ inid + "," + itid + "," + ivid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}
		
		// add getOrgAffiliationsArray Affiliation Array
		if ( aHeadObj.getOrgAffiliationsArray()!=null ){
			if ( aHeadObj.getOrgAffiliationsArray().length > 0 ){
				index=0;
				int[] tempOrgAffil = aHeadObj.getOrgAffiliationsArray();
				for (int i=0; i<tempOrgAffil.length; i++){
					int tempTID = tempOrgAffil[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidOrgAffil + " ";
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ inid + "," + itid + "," + ivid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}
		
		// add Organization Member Type
		if ( ! aHeadObj.getORGMembertype().equalsIgnoreCase("") || aHeadObj.getORGMembertypeTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getORGMembertype() ) + 
				"' OR tid=" +  aHeadObj.getORGMembertypeTID() + 
				") AND vid = " + vidMemberType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGMembertypeTID(itid);
				aHeadObj.setORGMembertype(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// add Whether the organization accepts volunteers from another country
		if ( aHeadObj.getORGTakesIntlVolsTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE " +
					"tid=" +  aHeadObj.getORGTakesIntlVolsTID() + 
				" AND vid = " + vidIntlVols + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGTakesIntlVolsTID(itid);
				aHeadObj.setORGTakesIntlVols(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ inid +"," + itid + "," + ivid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		
		// add portal name taxonomy
		if ( aHeadObj.getPortalNameModified().length()>0 ){
			//first, see if the portal already has a tid
			if(aHeadObj.getPortalTID()>0){
				itid=aHeadObj.getPortalTID();
				// if so, update that term_data.name entry
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "term_data SET name=? " +
						"WHERE tid="+itid+"";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getPortalNameModified() );
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
			}else{
				// if not, create a new entry in term_data with that vid; grab the tid just inserted
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(vid, name, description, weight, language, trid) " +
						"VALUES(" + vidPortalName + ",'" + replaceCarriageReturn(aHeadObj.getPortalNameModified()) + "','',0,'',0 ) ";
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
				
				
				//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
				Qry1 = "SELECT LAST_INSERT_ID() ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("LAST_INSERT_ID()");
					aHeadObj.setPortalTID(itid);
				} else {
					itid=-1;
				}
				iRetCode=-1;
				
			}
			
			
			//   then, create a new entry in term_node with the tid just inserted + the nid, version_id, etc
			// add to um_term_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
					"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
			// need to add code to handle if someone chooses the same skill more than once in the same form
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		} else {
			itid=-1;
			// The option the user chose for the SELECT returned no results
			//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
			//return -1;
		}
		/*
		 * add ARRAY items from multi-select form
		 */
		// add Primary Types of Volunteer Opportunities:
		if ( aHeadObj.getTypesOfOppsArray()!=null ){
			if ( aHeadObj.getTypesOfOppsArray().length > 0 ){
				index=0;
				int[] tempTypesOfOpps = aHeadObj.getTypesOfOppsArray();
				for (int i=0; i<tempTypesOfOpps.length; i++){
					int tempTID = tempTypesOfOpps[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidPrimaryOppTypes + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
						// also populate with the position type of stm for these orgs
						if(itid==iGenSTMtid || itid==iDomSTMtid || itid==iIntlSTMtid){
							// Get tid From ResultSet
							if(pConn.getNextResult()){
								itid = pConn.getDBInt("tid");
								// add to um_term_node
								aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
										"VALUES("+ aHeadObj.getORGNID() +"," + iSTMtid + "," + aHeadObj.getORGVID() + " )";
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
							} else {
								itid=-1;
							}
						}else if(itid==iOrgVirtTID){
							// Get tid From ResultSet
							if(pConn.getNextResult()){
								itid = pConn.getDBInt("tid");
								// add to um_term_node
								aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
										"VALUES("+ aHeadObj.getORGNID() +"," + iOppVirtTID + "," + aHeadObj.getORGVID() + " )";
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
							} else {
								itid=-1;
							}
						}
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add vidPosType
		if ( aHeadObj.getPositionTypesArray()!=null ){
			if ( aHeadObj.getPositionTypesArray().length > 0 ){
				index=0;
				int[] tempPositionTypes = aHeadObj.getPositionTypesArray();
				for (int i=0; i<tempPositionTypes.length; i++){
					int tempTID = tempPositionTypes[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidPosType + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getServiceAreasArray
		if ( aHeadObj.getServiceAreasArray()!=null ){
			if ( aHeadObj.getServiceAreasArray().length > 0 ){
				index=0;
				int[] tempServiceAreas = aHeadObj.getServiceAreasArray();
				for (int i=0; i<tempServiceAreas.length; i++){
					int tempTID = tempServiceAreas[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidService + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getSkillsArray
		if ( aHeadObj.getSkillsArray()!=null ){
			if ( aHeadObj.getSkillsArray().length > 0 ){
				index=0;
				int[] tempSkills = aHeadObj.getSkillsArray();
				for (int i=0; i<tempSkills.length; i++){
					int tempTID = tempSkills[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidSkill + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getGreatForArray
		if ( aHeadObj.getGreatForArray()!=null ){
			if ( aHeadObj.getGreatForArray().length > 0 ){
				index=0;
				int[] tempGreatFor = aHeadObj.getGreatForArray();
				for (int i=0; i<tempGreatFor.length; i++){
					int tempTID = tempGreatFor[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidVolInfo + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getTripLengthArray
		if ( aHeadObj.getTripLengthArray()!=null ){
			if ( aHeadObj.getTripLengthArray().length > 0 ){
				index=0;
				int[] tempTripLength = aHeadObj.getTripLengthArray();
				for (int i=0; i<tempTripLength.length; i++){
					int tempTID = tempTripLength[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidTripLength + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		
		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		 
		// if given a portal name, etc, then make sure this new organization is "favorited" by the main user
		if(aHeadObj.getPortalNameModified().length() > 0){
			int iCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getORGNID() + "," + aHeadObj.getORGUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -1;
			}else{
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -1;
				}else{
					iCount=1;
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
		    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getORGNID() + ","+iCount+") "; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    			//return -1;
		    		}
				}
			}
		}
		
		
		return 0;
	}
	// end-of method insertOrganizationIntDB()

    /**
	* update organization record in table organizationinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int editOrganizationInDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iPh=0, iFx=0, index=0;
		String aszSQLdrupal101, Qry1;
		String aszSQLdrupalTemp="field_donate_url_url=?,";
		//int inid=-1, ivid=-1, itid=-1, ilid=-1;
		int itid=-1, ilid=-1;
		MethodInit("editOrganizationInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}

		/*
		 * add to location  - has to preceed others, just like taxonomy, b/c RunQry can't be used after PrepQuery - 2008-10-28
		 */
		// grab the lid from um_location_instance to delete from um_location
		Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_instance WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("lid");
			
			Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_phone WHERE lid=" + ilid ;
			iRetCode = pConn.RunQry(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get Next Item From ResultSet
			if(pConn.getNextResult()){
				iPh=1;
			}
			Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_fax WHERE lid=" + ilid ;
			iRetCode = pConn.RunQry(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get Next Item From ResultSet
			if(pConn.getNextResult()){
				iFx=1;
			}
			
			
			
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "location SET street=?, additional=?, city=?, province=?, postal_code=?, " +
					"country=? WHERE lid=" + ilid;
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine1() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine2() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCity() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrStateprov() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrPostalcode() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCountryName() );
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

			//if(aHeadObj.getORGOrgPhone1().length() > 1){
				if(iPh==1){ // an instance of phone already exists for this lid
					aszSQLdrupal101="UPDATE " + aszDrupalDB + "location_phone SET " +
							" phone=? WHERE lid=" + ilid ; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone1() );
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
					iRetCode=0;
				}else{ // an instance of phone does not yet exist for this lid
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_phone " +
							"(lid,phone) " +
							"VALUES(" + ilid + ",?)"; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone1() );
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
					iRetCode=0;
				}

			//}
			//if(aHeadObj.getORGFax1().length() > 1){ // ------------ <--------- should it only insert in this condition???????
				if(iFx==1){ // an instance of fax already exists for this lid
					aszSQLdrupal101="UPDATE " + aszDrupalDB + "location_fax SET " +
							"fax=?  WHERE lid=" + ilid ; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getORGFax1() );
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
					iRetCode=0;
				}else{ // an instance of fax does not yet exist for this lid
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_fax " +
							"(lid,fax) " +
							"VALUES(" + ilid + ",?)"; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getORGFax1() );
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
					iRetCode=0;
				}
			//}


		} else {
			/*
			 * add to location
			 */
			// 2008-10-27 - had to add b/c drupal module upgrade changed db structure
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location(street, additional, city, province, postal_code, " +
					"country, source, is_primary) " +
					"VALUES(?,?,?,?,?,?,0,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine1() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrLine2() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCity() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrStateprov() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrPostalcode() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getORGAddrCountryName() );
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
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				ilid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setORGLID(ilid);
			} else {
				itid=-1;
			}
			iRetCode=-1;

			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_instance(lid,nid, vid, genid) " +
					"VALUES(" + ilid +"," + aHeadObj.getORGNID() +","  + aHeadObj.getORGVID() +",'' ) ";
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
			iRetCode=-1;


			if(aHeadObj.getORGOrgPhone1().length() > 1){
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_phone " +
						"(lid,phone) " +
						"VALUES(" + ilid + ",?)"; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone1() );
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
				iRetCode=0;
			}
			if(aHeadObj.getORGFax1().length() > 1){ // ------------ <--------- should it only insert in this condition???????
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_fax " +
						"(lid,fax) " +
						"VALUES(" + ilid + ",?)"; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getORGFax1() );
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
				iRetCode=0;
			}


		}
		
		/*
		 * URL alias
		 */
		if(aHeadObj.getORGUrlAlias().length() > 1){ // if this has not changed, then processEditOrg in OrganizationActions.java already cleared the URLAlias
			if(!(aHeadObj.getORGUrlAlias() == aHeadObj.getORGUrlAliasOrig()) ){
				// 1. do an insert into path_redirect of urlaliasorig
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "path_redirect " +
						"(redirect,source,type,last_used,query,fragment) " +
						"VALUES('node/" + aHeadObj.getORGNID() + "',?,301,UNIX_TIMESTAMP({fn now()}),'','')"; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getORGUrlAliasOrig() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=0;

				// 2. do an update of url_alias table set dst = urlalias where dst like urlaliasorig
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "url_alias " +
						" SET dst=? " +
						" WHERE src LIKE 'node/" + aHeadObj.getORGNID() + "' "; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getORGUrlAlias() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					int ierror = 98769876;
					ierror = ierror;// duplicate key error; maybe just ignore for now and let a cron generate the url alias
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=0;
			}
		}
		
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_organization " +
				"SET " +
				"field_volorg_mission_statemt_value=?,field_volorg_faith_statemt_value=?,field_volorg_email_value=?,field_volorg_secondary_phone_value=?," +
				"field_volorg_serve_poor_value=?,field_volorg_num_served_value=?,field_volorg_num_volunteers_value=?," +
				"field_volorg_num_volunteers_intl_value=?,field_volorg_num_opps_value=?," +
				//"field_volorg_org_id_value=?," + // 2009-07-20 legacy id
				//"field_volorg_user_id_value=?," + // 2009-07-20 legacy id
				"field_volorg_subdomain_value=?," +
				"field_site_interest_value=?," +
				"field_volorg_website_url=?," +
				"field_volorg_has_listings_value=?," ;
		if(aHeadObj.getORGDonateURL().length()>1){ // if the user has not entered a donation url, then we don't want to enter in values for title, etc
			aszSQLdrupalTemp = "field_donate_url_url=?,field_donate_url_attributes='N;',field_donate_url_title='Donate to this Organization'," ; // donate url, donate attributes, donate title
		}else{
			aszSQLdrupalTemp = "field_donate_url_url=?," ; // donate url, donate attributes, donate title
		}
		if(aHeadObj.getORGHasListings().equalsIgnoreCase("Yes") || aHeadObj.getORGHasListings().equalsIgnoreCase("Y")){ // if the user has not entered a donation url, then we don't want to enter in values for title, etc
			aszSQLdrupalTemp += "field_volorg_website_listings_url=?,field_volorg_website_listings_attributes='N;',field_volorg_website_listings_title='View Listings'," ; // listings url, listings attributes, listings title
		}else{
			aszSQLdrupalTemp += "field_volorg_website_listings_url=?," ; // listings url, listings attributes, listings title
		}
		aszSQLdrupal101 = aszSQLdrupal101 + aszSQLdrupalTemp +
				"field_volorg_local_affil_value=?,field_volorg_orientation_value=?," +
				"field_volorg_ein_value=?," +
				"field_volorg_opps_list_url='"+aszDomMain+"/oppsrch.do?method=processOppSearchAll&voltype=&orgnid=" + aHeadObj.getORGNID() + "'," + 
				//"field_volorg_phone_value=?,field_volorg_fax_value=?," +
				"field_civicrm_user_id_value=? " ;
		aszSQLdrupal101+="WHERE nid=?"; 
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGMissionStatement()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgStmtFaith()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgContactEmail() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgPhone2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOnethirdP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumServed() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrg() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumVolOrgIntl() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNumOppsOrg() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getSiteInterest() ? 1 : 0 );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGWebpage() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGHasListings() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGDonateURL() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGListingsURL() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGLocalAffil() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGFormalTrain() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGFedTaxId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
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
		

		// add to um_node
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node SET title=?, " +
				"changed=UNIX_TIMESTAMP({fn now()}) " +
				"WHERE nid=? AND vid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGVID() );
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
		 * add to node_revisions
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node_revisions SET " +
				//"uid=?, " +
				"title=?, body=?, teaser=?, timestamp=UNIX_TIMESTAMP({fn now()}) " +
				"WHERE nid=? AND vid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*iRetCode = pConn.setPrepQryInt( aHeadObj.getORGUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}**/
		iRetCode = pConn.setPrepQryString( aHeadObj.getORGOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgDescription() ));
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getORGOrgDescription()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGVID() );
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
		
		
		/**
		 * START taxonomy section
		 * START taxonomy section
		 * START taxonomy section
		 */

		// delete occurrences of the taxonomy and re-write them on update - handles if one is deleted, in addiiton to handling  new ones
		// !!!!!!!!!!!!!!!!!!! potentially NOT SAFE - b/c this would delete ALL, it would have to join term_data, too, to grab the vocabulary id
		//String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node WHERE nid = " + aHeadObj.getORGNID()		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + aHeadObj.getORGNID() + "   " +
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidDenomAffil + "," + vidOrgAffil + 
				"," + vidMemberType + "," + vidProgramType + "," + vidLangSpoken + 
				"," + vidCauseTopic + "," + vidState + "," + vidCity + 
				"," + vidCountry + "," + vidRegion + "," + vidOrgAffil + 
				"," + vidVolInfo + "," + vidDenomAffil + "," + vidOrgAffil +
				" ) ) ";
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
		
		
		// add Organizational Program Type
		if ( ! aHeadObj.getORGProgramTypes().equalsIgnoreCase("") || aHeadObj.getORGProgramTypes1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getORGProgramTypes() ) + 
				"' OR tid=" +  aHeadObj.getORGProgramTypes1TID() + 
				") AND vid = " + vidProgramType + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGProgramTypes1TID(itid);
				aHeadObj.setORGProgramTypes(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Denom Affiliation Array
		if ( aHeadObj.getDenominationalAffilsArray()!=null ){
			if ( aHeadObj.getDenominationalAffilsArray().length > 0 ){
				index=0;
				int[] tempDenomAffil = aHeadObj.getDenominationalAffilsArray();
				for (int i=0; i<tempDenomAffil.length; i++){
					int tempTID = tempDenomAffil[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
							" AND vid = " + vidDenomAffil + " ";
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() + "," + itid + "," + aHeadObj.getORGVID() + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}
		
		// add getOrgAffiliationsArray Affiliation Array
		if ( aHeadObj.getOrgAffiliationsArray()!=null ){
			if ( aHeadObj.getOrgAffiliationsArray().length > 0 ){
				index=0;
				int[] tempOrgAffil = aHeadObj.getOrgAffiliationsArray();
				for (int i=0; i<tempOrgAffil.length; i++){
					int tempTID = tempOrgAffil[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidOrgAffil + " ";
					iRetCode=pConn.PrepQuery(Qry1);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					// Get tid From ResultSet
					if(pConn.getNextResult()){
						itid = pConn.getDBInt("tid");
						// add to um_term_node
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() + "," + itid + "," + aHeadObj.getORGVID() + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}
		
		// add Organization Member Type
		if ( ! aHeadObj.getORGMembertype().equalsIgnoreCase("") || aHeadObj.getORGMembertypeTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getORGMembertype() ) + 
				"' ) AND vid = " + vidMemberType + " ";
			if(aHeadObj.getORGMembertype().length()<1 && aHeadObj.getORGMembertypeTID()>0){
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getORGMembertype() ) + 
				"' OR tid=" +  aHeadObj.getORGMembertypeTID() + 
				") AND vid = " + vidMemberType + " ";
			}
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGMembertypeTID(itid);
				aHeadObj.setORGMembertype(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Whether the organization accepts volunteers from another country
		if ( aHeadObj.getORGTakesIntlVolsTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE " +
					"tid=" +  aHeadObj.getORGTakesIntlVolsTID() + 
				" AND vid = " + vidIntlVols + " ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aHeadObj.setORGTakesIntlVolsTID(itid);
				aHeadObj.setORGTakesIntlVols(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
				// need to add code to handle if someone chooses the same skill more than once in the same form
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;
				// The option the user chose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		
		/*
		 * add ARRAY items from multi-select form
		 */
		// add Primary Types of Volunteer Opportunities:
		if ( aHeadObj.getTypesOfOppsArray()!=null ){
			if ( aHeadObj.getTypesOfOppsArray().length > 0 ){
				index=0;
				int[] tempTypesOfOpps = aHeadObj.getTypesOfOppsArray();
				for (int i=0; i<tempTypesOfOpps.length; i++){
					int tempTID = tempTypesOfOpps[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidPrimaryOppTypes + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
						// also populate with the position type of stm for these orgs
						if(itid==iGenSTMtid || itid==iDomSTMtid || itid==iIntlSTMtid){
							// Get tid From ResultSet
							if(pConn.getNextResult()){
								itid = pConn.getDBInt("tid");
								// add to um_term_node
								aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
										"VALUES("+ aHeadObj.getORGNID() +"," + iSTMtid + "," + aHeadObj.getORGVID() + " )";
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
							} else {
								itid=-1;
							}
						}
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add vidPosType
		if ( aHeadObj.getPositionTypesArray()!=null ){
			if ( aHeadObj.getPositionTypesArray().length > 0 ){
				index=0;
				int[] tempPositionTypes = aHeadObj.getPositionTypesArray();
				for (int i=0; i<tempPositionTypes.length; i++){
					int tempTID = tempPositionTypes[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidPosType + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getServiceAreasArray
		if ( aHeadObj.getServiceAreasArray()!=null ){
			if ( aHeadObj.getServiceAreasArray().length > 0 ){
				index=0;
				int[] tempServiceAreas = aHeadObj.getServiceAreasArray();
				for (int i=0; i<tempServiceAreas.length; i++){
					int tempTID = tempServiceAreas[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidService + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getSkillsArray
		if ( aHeadObj.getSkillsArray()!=null ){
			if ( aHeadObj.getSkillsArray().length > 0 ){
				index=0;
				int[] tempSkills = aHeadObj.getSkillsArray();
				for (int i=0; i<tempSkills.length; i++){
					int tempTID = tempSkills[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidSkill + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getGreatForArray
		if ( aHeadObj.getGreatForArray()!=null ){
			if ( aHeadObj.getGreatForArray().length > 0 ){
				index=0;
				int[] tempGreatFor = aHeadObj.getGreatForArray();
				for (int i=0; i<tempGreatFor.length; i++){
					int tempTID = tempGreatFor[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidVolInfo + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add getTripLengthArray
		if ( aHeadObj.getTripLengthArray()!=null ){
			if ( aHeadObj.getTripLengthArray().length > 0 ){
				index=0;
				int[] tempTripLength = aHeadObj.getTripLengthArray();
				for (int i=0; i<tempTripLength.length; i++){
					int tempTID = tempTripLength[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidTripLength + " ";
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
						// add to um_term_node
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ aHeadObj.getORGNID() +"," + itid + "," + aHeadObj.getORGVID() + " )";
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
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		
		return 0;
	}
	// end-of method editOrganizationInDB()
	
    /**
	* delete a row in table organizationinfo
	*/
	public int deleteOrganizationFromDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		int ilid=-1;
		String Qry1;
		MethodInit("deleteOrganizationFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
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
				"WHERE nid = " + aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/**
		 * ****************   END TAXONOMY SECTION  **********************
		 */
		
		// delete admin records for this organization
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
				"WHERE nid = " + aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		// delete contact records for this organization
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "usermail " +
				"WHERE org_nid = " + aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		// delete from um_content_type_organization
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_type_organization " +
				"WHERE nid=" + aHeadObj.getORGNID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_url_alias
		aszSQL101="DELETE FROM " + aszDrupalDB + "url_alias " +
				"WHERE src LIKE 'node/" + aHeadObj.getORGNID() + "'"; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_location
		// grab the lid from um_location_instance to delete from um_location
		Qry1 = "SELECT lid FROM " + aszDrupalDB + "location_instance WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pConn.getNextResult()){
			ilid = pConn.getDBInt("lid");
			aszSQL101="DELETE FROM " + aszDrupalDB + "location_instance " +
					"WHERE vid = " + aHeadObj.getORGVID();
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			aszSQL101="DELETE FROM " + aszDrupalDB + "location " +
					"WHERE lid = " + ilid;
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		} else {
		}
		// delete from um_node
		aszSQL101="DELETE FROM " + aszDrupalDB + "node " +
				"WHERE nid = " + aHeadObj.getORGNID();
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
				"WHERE nid = "+ aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from node_access
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "node_access " +
				"WHERE nid = "+ aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from node_comment_statistics
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "node_comment_statistics " +
				"WHERE nid = "+ aHeadObj.getORGNID();
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		 * delete from um_flag_content && flag_counts
		 */
		aszSQL101="DELETE FROM " + aszDrupalDB + "flag_content " +
				"WHERE content_id = "+ aHeadObj.getORGNID()+" and content_type LIKE 'node'";
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			//return -1;
		}
		aszSQL101="DELETE FROM " + aszDrupalDB + "flag_counts " +
					" WHERE content_id = "+ aHeadObj.getORGNID()+" and content_type LIKE 'node'";
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			//return -1;
		}
		return 0;
	}
	// end-of method deleteOrganizationFromDB()


    /**
	* getNatlAssociationInDB
	*/
	public String getNatlAssociationInDB(ABREDBConnection pConn, int iNatlAssocNID ){
		int iRetCode=-1;
		String aszNatlAssoc="";
		MethodInit("getNatlAssociationInDB");
		if(iNatlAssocNID<1){
			return null;
		}
		if(null == pConn){
			setErr("null database connection");
			return null;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return null;
		}
		String aszSQL101 =  " SELECT title " +
				"\n FROM " +
				"\n       " + aszDrupalDB + "node " +
				"\n WHERE " +
				"\n       nid=" + iNatlAssocNID + "  " ;
		// delete from organization
		iRetCode = pConn.RunQuery(aszSQL101);
		if(pConn.getNextResult()){
			iRetCode=0;
			aszNatlAssoc=pConn.getDBString("title");
		}
		return aszNatlAssoc;
	}
	// end-of method getNatlAssociationInDB()


    /**
	* remove org affiliation
	*/
	public int removeAssociationInDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, String aszNatlAssoc, ArrayList aList ){
		int iRetCode=-1, iOrgAffilTID=0;
		MethodInit("removeAssociationInDB");
		if(aszNatlAssoc.length()<1){
			return iRetCode;
		}
		if(null == pConn){
			setErr("null database connection");
			return iRetCode;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		String aszSQL101 =  " SELECT OrgAffil.tid org_affil_tid " +
				"\n FROM " +
				"\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
		 		",\n       " + aszDrupalDB + "node natlassoc " +
				"\n WHERE " +
				"\n       natlassoc.type LIKE 'national_association'	" +
				"\n       AND natlassoc.title LIKE '" + aszNatlAssoc + "' " +
				"\n       AND OrgAffil.vid=natlassoc.vid" +
				"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
				"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " ;
		// delete from organization
		iRetCode = pConn.RunQuery(aszSQL101);
		while(pConn.getNextResult()){
			iRetCode=0;
			iOrgAffilTID=pConn.getDBInt("org_affil_tid");
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
					"WHERE nid = " + aHeadObj.getORGNID() + "   " +
					" AND tid = " + iOrgAffilTID ;
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
			}
			// iterate through any child opps and make sure it's deleted for them, too
			OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();
			if(aList != null){
				Iterator itr = aList.iterator();
				while(itr.hasNext()){
					aOpportObj = (OrgOpportunityInfoDTO) itr.next();
					// delete from opportunities
					aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
							"WHERE nid = " + aOpportObj.getOPPNID() + "   " +
							" AND tid = " + iOrgAffilTID ;
					iRetCode=pConn.RunUpdate(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
				}
			}
		}
		return iRetCode;
	}
	// end-of method removeAssociationInDB()
	

    /**
	* update organization record in table organizationinfo editPortalInDB
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int editPortalInDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0, iPh=0, iFx=0;
		String aszSQLdrupal101="", Qry1="", aszSQLdrupalTemp="";
		int itid=-1, ilid=-1;
		MethodInit("editPortalInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		int iNID=0, iVID=0;
		
        switch( iType ){
	    	case OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC :
	    		iNID=aHeadObj.getNatlAssociationNID();
				Qry1 = "SELECT vid FROM " + aszDrupalDB + "content_type_national_association WHERE nid=" + iNID;
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}
				iRetCode = pConn.ExePrepQry();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
				}
				if(pConn.getNextResult()){
					iVID = pConn.getDBInt("vid");
				} 
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_national_association " +
					"SET " +
					"field_natlassoc_portal_banner_value=?,	field_natlassoc_portal_hdr_tags_value=?,field_natlassoc_portal_hdr_value=?," +
					"field_natlassoc_portal_css_value=?,	field_natlassoc_portal_footer_value=?" +
					" WHERE vid=" + iVID ;
	        	break;
		    default:
				iNID=aHeadObj.getORGNID();
				iVID=aHeadObj.getORGVID();
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_organization " +
					"SET " +
					"field_volorg_portal_banner_value=?,field_volorg_portal_header_tags_value=?,field_volorg_portal_header_value=?," +
					"field_volorg_portal_css_value=?,field_volorg_portal_footer_value=?" +
					" WHERE vid=" + iVID ;
        }
		//if(aHeadObj.getPortalBannerURL().length()>0){
			aszSQLdrupal101=aszSQLdrupal101 ;
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getPortalBannerURL() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getPortalHeaderTags() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getPortalHeader() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getPortalCSS() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getPortalFooter() );
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
		//}
		String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iNID + "   " +
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidPortalName + " ) ) ";
		iRetCode=pConn.PrepQuery(aszSQL101);
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
		
		// add portal name taxonomy
		if ( aHeadObj.getPortalNameModified().length()>0 ){
			//first, see if the portal already has a tid
			if(aHeadObj.getPortalTID()>0){
				itid=aHeadObj.getPortalTID();
				// if so, update that term_data.name entry
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "term_data SET name=? " +
						"WHERE tid="+itid+"";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getPortalNameModified() );
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
			}else{
				// if not, create a new entry in term_data with that vid; grab the tid just inserted
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(vid, name, description, weight, language, trid) " +
						"VALUES(" + vidPortalName + ",'" + replaceCarriageReturn(aHeadObj.getPortalNameModified()) + "','',0,'',0 ) ";
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
				//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
				Qry1 = "SELECT LAST_INSERT_ID() ";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					itid = pConn.getDBInt("LAST_INSERT_ID()");
					aHeadObj.setPortalTID(itid);
				} else {
					itid=-1;
				}
				iRetCode=-1;
			}
			//   then, create a new entry in term_node with the tid just inserted + the nid, version_id, etc
			// add to um_term_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
					"VALUES("+ iNID +"," + itid + "," + iVID + " )";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQLdrupal101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
			// need to add code to handle if someone chooses the same skill more than once in the same form
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		} else {
			itid=-1;
			// The option the user chose for the SELECT returned no results
			//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
			//return -1;
		}
		return 0;
	}
	// end-of method editPortalInDB()
	
	/**
	 * update favorites for updating an org/editing an org's portal (make sure all opps/orgs owned by this org are favorited by the primary user
	 */
	public int setFavoritesPortal(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		MethodInit("setFavoritesPortal");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
    	
    	ArrayList aList = new ArrayList();
		int iRetCode = 0;
		int iNID = 0;
		int iCount=0;
		
		// add the Org NID itself as well, to be sure that it is favorited
		aList.add(aHeadObj.getORGNID());

	    String aszSQLdrupal101 = "SELECT field_volorg_opp_reference_nid opp_nid, delta " +
			" FROM " +  aszDrupalDB + "content_field_volorg_opp_reference " +
			" WHERE nid=" + aHeadObj.getORGNID() + " ";

		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
	        iRetCode=0;
	        iNID = pConn.getDBInt("opp_nid");
	        aList.add(iNID);
		}
		
		// if given a portal name, etc, then make sure this new organization is "favorited" by the main user
		if(aHeadObj.getPortalNameModified().length() > 0){
			// iterate through the array list of opp nids and update them to be favorited
			Iterator<Integer> itrOppNIDs = aList.iterator();
			int iOppNID = 0;
			while (itrOppNIDs.hasNext()) {
				iCount=0;
				iOppNID = itrOppNIDs.next();
				
			    aszSQLdrupal101 = "SELECT count " +
						" FROM " + aszDrupalDB + "flag_counts " +
						" WHERE content_id=" + iOppNID + " ";	
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.RunQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
				if(pConn.getNextResult()){
					iCount = pConn.getDBInt("count");
				}
				// add to flag and add to flag counts
				aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
						" VALUES(" + iFlagFavorite + ",'node'," + iOppNID + "," + aHeadObj.getORGUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}else{
					iRetCode = pConn.ExecutePrepQry();
					if(iRetCode == 1062 ){ // then this is a duplicate; 
						
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}else if(iCount>0){
						iCount++;
						// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
						aszSQLdrupal101 = "UPDATE  " +  aszDrupalDB + "flag_counts SET count="+iCount+"" +
								" WHERE fid=" + iFlagFavorite + " AND content_type='node' AND content_id=" + iOppNID + " "; 
			    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
			    		if(0 != iRetCode){
			    			pConn.copyErrObj(getErrObj());
			    			ErrorsToLog();
			    		}
					}else{
						iCount++;
						// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
						aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
			    				" VALUES (" + iFlagFavorite + ",'node'," + iOppNID + ","+iCount+") "; 
			    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
			    		if(0 != iRetCode){
			    			pConn.copyErrObj(getErrObj());
			    			ErrorsToLog();
			    		}
					}
				}
			}
			
			// favorite the org, too
		    iCount=0;
			aszSQLdrupal101 = "SELECT count " +
					" FROM " + aszDrupalDB + "flag_counts " +
					" WHERE content_id=" + aHeadObj.getORGNID() + " ";	
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.RunQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			if(pConn.getNextResult()){
				iCount = pConn.getDBInt("count");
			}
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getORGNID() + "," + aHeadObj.getORGUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
			}else{
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}else if(iCount>0){
					iCount++;
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = "UPDATE  " +  aszDrupalDB + "flag_counts SET count="+iCount+"" +
							" WHERE fid=" + iFlagFavorite + " AND content_type='node' AND content_id=" + aHeadObj.getORGNID() + " "; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    		}
				}else{
					iCount++;
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
		    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getORGNID() + ","+iCount+") "; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    		}
				}
			}
		}
		return iRetCode;
	}
	
	/**
	* select a list of items from table organizationinfo
	*/
	public int getOrganizationDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, boolean feeds){
		boolean bManaging=false, bNatlAssoc=false;
		int iRetCode=0, iTemp=0, iTemp2=0;
		String aszSQLSrchList="", aszTemp="", aszSQLManaging="",
			aszSQLTemp="", aszSQLSelect = "", aszSQLFrom = "", aszSQLWhere = "", aszSQLSelectManaging="", aszSQLFromManaging="", aszSQLWhereManaging="";
		String aszPublished="\n		AND status=1 		";
		String aszPortal="";
		if( aSrchParmObj.getSearchType()==OrganizationInfoDTO.LOADBY_UID){
		}else if(feeds==false){
			aListObj.clear();
		}
		if(aSrchParmObj.getRequestType().equals("natlassoc")){
			bNatlAssoc=true;
		}
		String mainDB=aszDrupalDB;
		if( feeds==true ){
			mainDB=aszSocialGraphDB;
		}
//System.out.println("getOrganizationDBList - see if followed by missing mission_statement");
		aszSQLSelect = 
			"SELECT org.nid,			\n" +
			"       org.vid,			\n" +
			"       org.uid,			\n" +
			"       title,				\n" +
			"       created,			\n" +
			"       changed,			\n" +
			"       org.description,	\n" +
			"       status,				\n" +
			"       popularity,			\n" +
			"       tm_member,			\n" +
			"       street,				\n" +
			"       city,				\n" +
			"       province,			\n" +
			"       postal_code,		\n" +
			"       country,			\n" +
			"       fav_content_id,			\n" +
			"       fav_uid,			\n" +
			"		'' AS cvintern_site_approved, \n"+
//			"       field_sg_explanation_value, \n" +
//			"       field_sg_signature_value, \n" +
//			"       field_sg_uid_value, \n" +
			
			"       GROUP_CONCAT(DISTINCT program_type)		program_types,		\n" +
			"		GROUP_CONCAT(DISTINCT member_type)		member_type,				\n" +
			"		GROUP_CONCAT(DISTINCT org_affil)		org_affiliations,		\n" +
			"		GROUP_CONCAT(DISTINCT denom)			denom_affil,			\n" +
			"		GROUP_CONCAT(DISTINCT region)			region,				\n" +		
			"       GROUP_CONCAT(DISTINCT portal)			portal,		\n" +

			"       url_alias			\n";
		aszSQLFrom=
			" \nFROM\n" + 
			"       " + aszDataDB + "tbl_orgs_tagged org				\n" ;
		aszSQLWhere=
			" \nWHERE\n" + 
			"       true=true				\n" ;

		aszSQLSelectManaging = 
			"SELECT n.nid,										\n" +
			"       n.vid,										\n" +
			"       n.uid,										\n" +
			"       n.title,									\n" +
			"       n.created,									\n" +
			"       n.changed,									\n" +
			"       n.status,									\n" +
			"       field_sg_explanation_value, \n" +
			"       field_sg_signature_value, \n" +
			"       field_sg_uid_value, \n" +
			"       org.field_volorg_serve_poor_value		serve_poor,			\n" +
			"       org.field_volorg_num_served_value		num_served,			\n" +
			"       org.field_volorg_num_volunteers_value	num_vol,			\n" +
			"       org.field_volorg_local_affil_value		local_affil,		\n" +
			"       org.field_volorg_orientation_value		orientation,		\n" +
			"       org.field_volorg_popularity_value		popularity,			\n" +
			"       org.field_volorg_tm_member_value		tm_member,			\n" +
			"		field_cvintern_site_approved_value		cvintern_site_approved, \n"+
			"       00000 AS postal_code			\n";
		aszSQLFromManaging=
			"FROM   " + aszDrupalDB + "node 						n,				\n" +
			"       " + aszDrupalDB + "content_type_organization 	org 			\n" ;
		aszSQLWhereManaging=
			"WHERE  n.nid = org.nid							\n" +
			"       AND n.vid = org.vid						\n" ; 
//aszSQLselect = aszSQLSelectOld; aszSQLfrom = aszSQLFromOld; aszSQLwhere = aszSQLWhereOld;

		if(feeds==true){
			aszSQLSelect = 
				"SELECT\n"+
				"       sid nid,		\n"+
				"       vid,		\n"+
				"       0 as uid,			\n"+
				"       title,			\n"+
				"       changed,		\n" +
				"       created,		\n" +
				"       description,		\n" +
				"       short_description teaser,			\n" +
				"       num_volunteers num_vol_org,		\n" +
				"       num_served num_served_by_org,	\n" +
				"       popularity,			\n" +
				"       tm_member,			\n" +
				"       location_street 	street,				\n" +
				"       location_city 		city,				\n" +
				"       location_province 	province,			\n" +
				"       location_postal_code postal_code,		\n" +
				"       location_country 	country,			\n" +
				"       location_latitude latitude,			\n" +
				"       location_longitude longitude,			\n" +
				"       fav_uid,			\n" +
				"       service_areas,		\n" +
				"		position_type,		\n" +
				"		primary_opp_type,		\n" +
				"		trip_length,		\n" +
				"		international_volunteer_options,	\n" +
				"		org_affil 			org_affiliations,			\n" +		
				"		denom 				denom_affil,			\n" +		
				"		region,				\n" +		
				"       program_type		program_types,		\n" +
				"		member_type,				\n" +
				"		portal,		\n" +
				"       sg_explanation	field_sg_explanation_value, 	\n" +
				"       sg_signature	field_sg_signature_value, 		\n" +
				"       sg_uid			field_sg_uid_value, 			\n" +
				"       url_alias,			\n" +
				"       status				\n";
			aszSQLFrom=
				" \nFROM\n" + 
				"       " + mainDB + "tbl_organizations				\n" ;
		}

		MethodInit("getOrganizationDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		String aszSQLWhereEnd="", aszSQLSelectEnd="", aszSQLFromEnd="";

		
		if(aSrchParmObj.getSearchLatest()==true){
			aszSQLWhere += "       AND created > " + lOneWkAgo + " 		\n";
			aszSQLWhereManaging += "       AND n.created > " + lOneWkAgo + " 		\n";
		}
		

        
        
        // see if this is going through a portal or not
        boolean bGetPortalList = aSrchParmObj.getSearchPortals();
		int iPortalUID = aSrchParmObj.getPortalUID();
       
        if(bGetPortalList==true){ // will need to join with term_data - prob add portal into the org "materialized views" stuff to grab the data there
        		// 	****	portal field lenght is greater than 0
        	aszSQLWhereEnd += " AND CHAR_LENGTH(portal) > 0 ";
        }else if( iPortalUID > 0 && iPortalUID!=iMeetTheNeedUID &&
            		(aSrchParmObj.getSearchType()!=OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES ) &&
            		bNatlAssoc==false
        ){
    		aszPublished="";//" AND status=1 ";

        	
        	aszSQLFromEnd += ", " + aszDrupalDB + "flag_content fl ";
        	aszSQLWhereEnd += " AND org.nid=fl.content_id AND fl.fid=" + iFlagFavorite + " AND fl.uid=" + iPortalUID + " ";
        }
    	if(aSrchParmObj.getPortal()==777){// administrator through portal
    		aszSQLWhereEnd += " AND org.uid != " + aSrchParmObj.getPortalAdminUID();
    	}
        
        
        
    	boolean b_throughTermDataLeftJoin=false;
    	if(aSrchParmObj.getSearchFromSQLStatement().contains("term_data")){
    		b_throughTermDataLeftJoin=true;
    	}

    	String aszGroupBy = " GROUP BY org.nid ";
    	if(feeds==true)	aszGroupBy = " GROUP BY sid ";
        switch( aSrchParmObj.getSearchType() ){
        	// diff administrating loads
        	case OrganizationInfoDTO.LOADBY_UID : // currently used
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLSelectManaging += ", results.portal ";
        		aszSQLFromEnd += 
    				"LEFT JOIN " +
    				"	(SELECT td.name portal, tn.tid, tn.nid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
    						" WHERE tn.tid=td.tid AND td.vid=343) results " +
    				"	ON results.nid=org.nid" +
	        		",\n       " + aszDrupalDB + "content_field_volorg_owner orguser 		\n"  ; 
	        	aszSQLWhereEnd += 
	        		" AND orguser.nid=org.nid AND field_volorg_owner_uid=" + aSrchParmObj.getUID()  + 
	        		"\n" + aszGroupBy + " 		" + 
		        	"\nORDER BY\n title ASC		";
            	break;
        	case OrganizationInfoDTO.LOADBY_UID_CONTACT : // currently used
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLSelectManaging += ", results.portal ";
        		aszSQLFromEnd += 
    				"LEFT JOIN " +
    				"	(SELECT td.name portal, tn.tid, tn.nid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
    						" WHERE tn.tid=td.tid AND td.vid="+vidPortalName+") results " +
    				"	ON results.nid=org.nid" +
	        		",\n       " + aszDrupalDB + "usermail contact 		\n"  ; 
	        	aszSQLWhereEnd += 
	        		" AND contact.org_nid=org.nid AND contact.uid=" + aSrchParmObj.getUID()  + 
	        		"\n" + aszGroupBy + " 		" + 
		        	"\nORDER BY\n title ASC		";
            	break;
        	case OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC :
        		if(aSrchParmObj.getPortalNID()<1){
        			aSrchParmObj.appendErrorMsg("portal id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLSelectManaging += ", results.portal ";

        		aszSQLFromEnd +=  
        				"LEFT JOIN " +
        				"	(SELECT td.name portal, tn.tid, tn.nid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
        						" WHERE tn.tid=td.tid AND td.vid=343) results " +
        				"	ON results.nid=org.nid" +
	    				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
	    				",\n       " + aszDrupalDB + "term_node OrgAffil2 " +
	        	 		",\n       " + aszDrupalDB + "node natlassoc " ;
	    		aszSQLWhereEnd += 
	    				"\n       AND OrgAffil.vid=natlassoc.vid " +
	        			"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
	        			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
	        			"\n       AND OrgAffil.tid=OrgAffil2.tid " +
	        			"\n       AND OrgAffil2.vid=org.vid " +
	            		"\n       AND natlassoc.nid="+aSrchParmObj.getPortalNID()+" AND natlassoc.type LIKE 'national_association'	" +
			        	" \n" + aszGroupBy + " " + // vid
			        	" \nORDER BY\n title, changed, org.nid ";
            	break;
/*            	
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLFromEnd +=  
        				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
            	 		",\n       " + aszDrupalDB + "term_relation tr " + 
        				",\n       " + aszDrupalDB + "term_node NatlAffil, " + aszDrupalDB + "term_data NatlAffilTermData " +
            	 		",\n       " + aszDrupalDB + "content_field_volorg_owner orguser " ; // instead of opporg reference, look at opp's orgaffil
        		aszSQLWhereEnd += 
            			"\n       AND OrgAffil.vid=org.vid AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND tr.tid2=OrgAffil.tid AND tr.tid1=NatlAffil.tid	" +
                		"\n       AND NatlAffil.vid=orguser.vid AND NatlAffil.tid=NatlAffilTermData.tid " +
                		"\n       AND NatlAffilTermData.vid=" +  vidNatlAffil + "  " +
            			"\n       AND NatlAffil.vid=orguser.vid 	" +
			        	"\n       AND orguser.field_volorg_owner_uid=" + aSrchParmObj.getUID() + 
			        	" \nGROUP BY\n opp.vid " +
			        	" \nORDER BY\n org_nid, title, changed";
            	break;
*/            	
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
        		if(aSrchParmObj.getUID() < 1){
                	aSrchParmObj.appendErrorMsg("user id required");
        			return -1;
        		}
        		bManaging=true;
        		aszSQLSelectManaging += ", results.portal ";

        		aszSQLFromEnd +=  
        				"LEFT JOIN " +
        				"	(SELECT td.name portal, tn.tid, tn.nid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
        						" WHERE tn.tid=td.tid AND td.vid=343) results " +
        				"	ON results.nid=org.nid" +
	    				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
	    				",\n       " + aszDrupalDB + "term_node OrgAffil2 " +
	        	 		",\n       " + aszDrupalDB + "node natlassoc " + 
	        	 		",\n       " + aszDrupalDB + "content_field_natlassoc_manager manager " ;
	    		aszSQLWhereEnd += 
	        			//"\n       AND OrgAffil.nid=opp.field_volopp_org_reference_nid " +
	    				"\n       AND OrgAffil.vid=natlassoc.vid " +
	        			"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
	        			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
	        			"\n       AND OrgAffil.tid=OrgAffil2.tid " +
	        			"\n       AND OrgAffil2.vid=org.vid " +
	            		"\n       AND natlassoc.nid=manager.nid AND natlassoc.type LIKE 'national_association'	" +
			        	"\n       AND manager.field_natlassoc_manager_uid=" + aSrchParmObj.getUID() + 
			        	" \n" + aszGroupBy + " " +//vid
			        	" \nORDER BY\n title, changed, org.nid ";
/*	    		
        		aszSQLFromEnd +=  
        				",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
            	 		",\n       " + aszDrupalDB + "term_relation tr " + 
        				",\n       " + aszDrupalDB + "term_node NatlAffil, " + aszDrupalDB + "term_data NatlAffilTermData " +
            	 		",\n       " + aszDrupalDB + "node natlaffiluser " ;
        		aszSQLWhereEnd += 
            			"\n       AND OrgAffil.vid=org.vid AND OrgAffil.tid=OrgAffilTermData.tid " +
            			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " +
            			"\n       AND tr.tid2=OrgAffil.tid AND tr.tid1=NatlAffil.tid	" +
                		"\n       AND NatlAffil.vid=natlaffiluser.vid AND NatlAffil.tid=NatlAffilTermData.tid " +
                		"\n       AND NatlAffilTermData.vid=" +  vidNatlAffil + "  " +
            			"\n       AND NatlAffil.vid=natlaffiluser.vid 	" +
			        	"\n       AND natlaffiluser.uid=" + aSrchParmObj.getUID() + 
			        	"\n       AND natlaffiluser.type LIKE 'uprofile' " + 
			        	" \nGROUP BY\n org.vid " +
			        	" \nORDER BY\n title, changed, org.nid ";
*/			        	
            	break;
        	case OrganizationInfoDTO.LOADBY_SITEADMIN :
        		if(aSrchParmObj.getNID() < 1){
                	aSrchParmObj.appendErrorMsg("organization node id required");
        			return -1;
        		}
        		bManaging=true;
	        	aszSQLWhereEnd += 
	        		" AND org.nid=" + aSrchParmObj.getNID()  + 
	        		"\n" + aszGroupBy + " 		" + 
		        	"\nORDER BY\n title ASC		";
            	break;
            
            // public load:	
			case OrganizationInfoDTO.SEARCH_TYPE_INCL_FAVORITES :
	    		if( aSrchParmObj.getPortalAdminUID() > 0 && b_throughTermDataLeftJoin==false){ // AND this is NOT the advanced search form - would cause issues with not left joining with uid
		        	aszSQLWhere = " WHERE ( ( true=true ";
		        	aszSQLWhereEnd += 
		        		aszPublished + ") OR (fav_uid=" + aSrchParmObj.getPortalAdminUID() + "  AND org.uid != " + aSrchParmObj.getPortalAdminUID() + ") ) "+
		    			        	" \nGROUP BY\n org.vid " +
		    			        	" \nORDER BY\n fav_uid= " + aSrchParmObj.getPortalAdminUID() + " DESC, tm_member DESC, popularity DESC ";
	
	    		}else{
	        		aszSQLWhereEnd +=  
			        	aszPublished + 
				        "\n" + aszGroupBy + "		" +
				        "\nORDER BY\n tm_member DESC, popularity DESC 		";
	    		}
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POPULARITY:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n tm_member DESC, popularity DESC 		"; 
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_NUM_VOL_ORG:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n num_vol DESC,popularity DESC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_POSTAL:
        		aszSQLWhereEnd +=  
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n postal_code ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_ORGNAME:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n title ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_CITY:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n city ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_STATE:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n province ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_PROV:
        		aszSQLWhereEnd +=  
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n province ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_COUNTRY:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n country ASC 		";
            	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DENOMAFFIL:
        		aszSQLWhereEnd +=  
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n denom ASC, popularity DESC 		";
        		break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_AFFIL1:
        		aszSQLWhereEnd +=  
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n org_affil ASC, popularity DESC 		";
	        	break;
        	case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_OPPNAME:
        		aszSQLWhereEnd += 
        			aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY title ASC 		";
	        	break;
            case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_UPDATEDT:
            	aszSQLWhereEnd +=  
            		aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n changed DESC 		";
                break;
            case OrganizationInfoDTO.SEARCH_TYPE_ADVANCE_DISTANCE:
            	aszSQLWhereEnd += 
            		aszPublished + 
		        	"\n" + aszGroupBy + " 		" +
		        	"\nORDER BY\n ABS(CONVERT(postal_code,DECIMAL) - CONVERT('" + aSrchParmObj.getPostalCode() + "',DECIMAL)) ASC 		";
            	break;
            default:
    			setErr("request type not supported");
            	aSrchParmObj.appendErrorMsg("type not supported");
                return 1;
        }
        aszSQLSrchList = 	aszSQLSelect + aSrchParmObj.getSearchSQLStatement() + aszSQLSelectEnd + 
        			aszSQLFrom + aSrchParmObj.getSearchFromSQLStatement() + aszSQLFromEnd + 
        			aszSQLWhere + aSrchParmObj.getSearchWhereSQLStatement() + aszSQLWhereEnd;
    	aszSQLManaging = aszSQLSelectManaging + aSrchParmObj.getSearchSQLStmnt() + aszSQLSelectEnd + 
    				aszSQLFromManaging + aSrchParmObj.getSearchFromSQLStmnt() + aszSQLFromEnd + 
					aszSQLWhereManaging + aSrchParmObj.getSearchWhereSQLStmnt() + aszSQLWhereEnd;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
        if(bManaging==false){
			iRetCode = pConn.RunQuery(aszSQLSrchList);
		}else{ // if a user might be opening this in a case where it doesn't matter if it's published or not, we want everything to load for them
			iRetCode = pConn.RunQuery(aszSQLManaging);
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            OrganizationInfoDTO aHeadObj = new OrganizationInfoDTO();
			aHeadObj.setORGNID(pConn.getDBInt("nid"));
			aHeadObj.setORGVID(pConn.getDBInt("vid"));
			aHeadObj.setORGUID(pConn.getDBInt("uid"));
			aHeadObj.setORGCreateDt(pConn.getDBTimestamp("created"));
			aHeadObj.setORGUpdateDt(pConn.getDBTimestamp("changed"));
			aHeadObj.setORGCreateDtNum(pConn.getDBInt("created"));
			aHeadObj.setORGUpdateDtNum(pConn.getDBInt("changed"));
			aHeadObj.setORGOrgName(pConn.getDBString("title"));
			aHeadObj.setORGPopularity(pConn.getDBInt("popularity"));
			aHeadObj.setORGMember(pConn.getDBInt("tm_member"));
			if( bManaging==false ){
				aHeadObj.setORGDescTeaser(pConn.getDBString("description"));
//				aHeadObj.setORGMissStmntTeaser(pConn.getDBString("mission_statement"));
				aHeadObj.setORGMembertype(pConn.getDBString("member_type"));
				aHeadObj.setORGDenomAffil(pConn.getDBString("denom_affil"));
				aHeadObj.setORGAffiliation(pConn.getDBString("org_affiliations"));
				aHeadObj.setORGUrlAlias(pConn.getDBString("url_alias"));
				aHeadObj.setORGAddrLine1(pConn.getDBString("street"));
				aHeadObj.setORGAddrCity(pConn.getDBString("city"));
				aHeadObj.setORGAddrStateprov(pConn.getDBString("province"));
				aHeadObj.setORGAddrPostalcode(pConn.getDBString("postal_code"));
				aHeadObj.setORGAddrCountryName(pConn.getDBString("country"));
				aHeadObj.setORGAddrOtherProvince(pConn.getDBString("province"));
				aHeadObj.setORGProgramTypes(pConn.getDBString("program_types"));
			}else{
	            aHeadObj.setCVInternSiteApproved(pConn.getDBString("cvintern_site_approved"));
	            aHeadObj.setSocialGraphExplanation(pConn.getDBString("field_sg_explanation_value"));
	            aHeadObj.setSocialGraphSignature(pConn.getDBString("field_sg_signature_value"));
            }
			aHeadObj.setPortalName(pConn.getDBString("portal"));
			if(aSrchParmObj.getSearchType()==OrganizationInfoDTO.LOADBY_UID_CONTACT){
				aHeadObj.setRequestType("ByContact");
			}
			aListObj.add(aHeadObj);
			
			if(aSrchParmObj.getSearchType() == OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
				if(pConn.getDBString("results.portal").length()>0)
					aszPortal=pConn.getDBString("results.portal");
			}
		}
		aSrchParmObj.setPortalName(aszPortal);
		return iRetCode;
    }
    // end-of method getOrganizationDBList()

    /**
	* load an organization
	*/
	public int loadOrganizationFromDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, String aszRailsDB, int iType ){
		int iRetCode=0, iLid=-1, index=0, iTemp=0, iIdNum = 0;
		String aszSQL2=null, aszTemp=null, aszUserName=null , aszSQLtemp = null,  aszNID="";
		boolean bNatlAssocNode = false, b_natlassoc=false;
		int iAutoTag=0, indexAutoTag=0 ;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		int[] a_iTempPublic= new int[50];
		if( aHeadObj.getRequestType().equals("natlassoc")){
			b_natlassoc=true;
			if(iType != OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
				iType=OrganizationInfoDTO.LOADBY_NATL_ASSOC;
			}
		}
		
		/*
		 * Load Number of cvinterns interested
		 */
		int iNumApplicants=0;
		String aszSQL = 
			"SELECT count(nid) count_interns " + 
		    " FROM " + aszDrupalDB + "content_field_cvintern_org " +
			" WHERE field_cvintern_org_nid = " + aHeadObj.getORGNID() + "";
		
		iRetCode=pConn.getDBStmt();
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
		}
		iRetCode=-111; // no results were found
		while(pConn.getNextResult()){
			iNumApplicants = pConn.getDBInt("count_interns");
		}
		aHeadObj.setCVInternInterest(iNumApplicants);

		
		/*
		 * Load Number opps
		 */
		int iNumOpps=0;
		aszSQL = 
			"SELECT count(field_volorg_opp_reference_nid) num_opps " + 
		    " FROM " + aszDrupalDB + "content_field_volorg_opp_reference " +
			" WHERE nid = " + aHeadObj.getORGNID() + "";
		
		iRetCode=pConn.getDBStmt();
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
//			pConn.copyErrObj(getErrObj());
//			ErrorsToLog();
//			return -1;
		}
		aHeadObj.setQuestionnaires(new ArrayList<QuestionnaireDTO>());
		while(pConn.getNextResult()) {
			iNumOpps = pConn.getDBInt("num_opps");;
		}
		aHeadObj.setORGNumOpps(iNumOpps);
		if(iNumOpps>0){
			aHeadObj.setORGHasListings("yes");
		}else{
			aHeadObj.setORGHasListings("no");
		}

		
		String aszSQLSelectInit =  " SELECT n.nid node_id, n.vid version_id, n.uid, n.moderate, n.status, n.title, nr.body, n.created, n.changed, " +
				"cckOrg.field_volorg_mission_statemt_value, " +
				"cckOrg.field_volorg_description_value, cckOrg.field_volorg_faith_statemt_value, cckOrg.field_volorg_email_value,field_volorg_secondary_phone_value, " +
				"cckOrg.field_volorg_serve_poor_value, cckOrg.field_volorg_num_served_value, cckOrg.field_volorg_num_volunteers_value, " +
				"cckOrg.field_volorg_num_volunteers_value, cckOrg.field_volorg_num_volunteers_intl_value, cckOrg.field_volorg_num_opps_value, " +
				"cckOrg.field_volorg_has_listings_value , " + 
				"cckOrg.field_volorg_url_id_value, " + // legacy, but still needed for ability to load
				"cckOrg.field_volorg_org_id_value, " + // legacy, but still needed for ability to load
				//"cckOrg.field_volorg_user_id_value, " +
				"cckOrg.field_volorg_subdomain_value, " +
				"cckOrg.field_site_interest_value, " +
				"cckOrg.field_volorg_website_url, cckOrg.field_volorg_website_title, cckOrg.field_volorg_local_affil_value, " +
				"cckOrg.field_donate_url_url,cckOrg.field_donate_url_attributes,cckOrg.field_donate_url_title, " +
				"cckOrg.field_volorg_website_listings_url,cckOrg.field_volorg_website_listings_attributes,cckOrg.field_volorg_website_listings_title, " +
				"cckOrg.field_volorg_orientation_value, cckOrg.field_volorg_ein_value, cckOrg.field_volorg_opps_list_url, " +
				"cckOrg.field_volorg_opps_list_title, " +
				//"cckOrg.field_volorg_phone_value, cckOrg.field_volorg_fax_value, " +
				"cckOrg.field_volorg_popularity_value, cckOrg.field_volorg_url_id_value, " +
				"cckOrg.field_volorg_portal_banner_value, cckOrg.field_volorg_portal_header_tags_value, cckOrg.field_volorg_portal_header_value, " +
				"cckOrg.field_volorg_portal_css_value, cckOrg.field_volorg_portal_footer_value, " +
				"cckOrg.field_org_import_source_value, cckOrg.field_cvintern_site_approved_value  " ;
		String aszSQLFromInit =  " FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "node_revisions nr, " + 
				aszDrupalDB + "content_type_organization cckOrg" ;
		String aszSQL101 = aszSQLSelectInit + " , " +
				"loc.street, loc.additional, loc.city, loc.province, loc.postal_code, loc.country, loc.lid, loc.latitude, loc.longitude" ;
		
		String aszSQLlocationPhone = ", p.phone "; //*************************************************************************************
		String aszSQLlocationFax = ", f.fax "; //*****************************************************************************************

		String aszSQLFrom = aszSQLFromInit + ", " + aszDrupalDB + "content_field_volorg_owner contact, " +
		aszDrupalDB + "location_instance loc_inst , " + aszDrupalDB + "location loc " ;

		String aszSQLFromContact = " FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "node_revisions nr, " + 
			aszDrupalDB + "content_type_organization cckOrg, " + aszDrupalDB + "usermail contact, " +
			aszDrupalDB + "location_instance loc_inst , " + aszDrupalDB + "location loc " ;
	
		String aszSQLlocationPhoneFrom = ", " +  aszDrupalDB + "location_phone p "; //**************************************************
		String aszSQLlocationFaxFrom = ", " +  aszDrupalDB + "location_fax f "; // <--***-- this one's a left join...*******************
		// ^^^^^^^^^^^^^^^^^^^^^^^^^ could maybe do like taxonomy????????????? Phone, too - like taxonomy ^^^^^^^^^^^^^^^^^^
		
		String aszPublished = " and n.status=1 ";
        // see if this is going through a portal or not
        String aszEndWhere = "";
        int iPortalUID = aHeadObj.getPortalUID();
        if( iPortalUID > 0 && iPortalUID!=iMeetTheNeedUID &&
            	(! aHeadObj.getRequestType().equalsIgnoreCase("myResultsAdminSelect")) && 
             	b_natlassoc==false
        ){
    		aszPublished="";//" AND status=1 "; might have unpublished private ones that they don't want listed on all versions of the site
        	
    		// include field_volopp_private_value,field_volopp_hq_or_offsite_value
    		aszSQLFrom += ", " + aszDrupalDB + "flag_content fl ";
    		aszSQLFromContact += ", " + aszDrupalDB + "flag_content fl ";
        	aszEndWhere += " AND cckOrg.nid=fl.content_id AND fl.fid=" + iFlagFavorite + " AND fl.uid=" + iPortalUID + " ";
        }

		MethodInit("loadOrganizationFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
        switch( iType ){
	    	case OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_NO_CONTACT :
	    		// basic; doesn't need all the other info; fall back so that there's at least a profile loading
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQLSelectInit + aszSQLFromInit +
	        				" WHERE cckOrg.nid=" + aHeadObj.getORGNID() + " AND n.nid=nr.nid AND n.vid=nr.vid AND " +
	        						" nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid " +
	        						" AND n.status=1 ";
	        	break;
	    	case OrganizationInfoDTO.LOADBY_SITEADMIN :
	    		// administrator; does not have to have any ties to the organization; just have site administrative rights
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1 || aHeadObj.getSiteAdministratorUID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  && administrator uid required");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " + 
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid ";
	        	break;
	    	case OrganizationInfoDTO.LOADBY_UID_CONTACT :
	    		// has site contact rights
	    		aszSQL101 = aszSQL101 + aszSQLFromContact;
	        	if(aHeadObj.getORGNID() < 1 || aHeadObj.getORGUID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  &&  uid required");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.org_nid=n.nid " + 
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid " +
	    			" AND contact.uid=" + aHeadObj.getORGUID() + " ";
	        	break;
	    	case OrganizationInfoDTO.LOADBY_UID :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	    		aHeadObj.getORGNID();
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	if(aHeadObj.getORGUID() < 1){
	        		aHeadObj.appendErrorMsg("user ID required  ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " + 
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid " +
	    			" AND contact.field_volorg_owner_uid=" + aHeadObj.getORGUID() + " ";
    				//" AND n.uid=" + aHeadObj.getORGUID() + " ";
	        	break;
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	    		iIdNum = aHeadObj.getORGNID();
	    		if(aHeadObj.getORGUrlAlias().length() < 1 && iIdNum < 1){
	    			return -1;
	    		}
	        	if(aHeadObj.getNatlAssociationNID() < 1){
	        		//aHeadObj.appendErrorMsg("no access  ");
	        		return -1;
	        	}
	    		aszTemp="";
	    		aszNID="";
	    		if(iIdNum < 1){
		    		// query for the NID by looking at the url_alias table
		    		aszSQLtemp = " SELECT src FROM " + aszDrupalDB + "url_alias alias " + " WHERE alias.dst LIKE 'org/" + aHeadObj.getORGUrlAlias() + "'";
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -999;
					}
					iRetCode = pConn.RunQuery(aszSQLtemp);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -999;
					}
					//iRetCode=-1;
					if(pConn.getNextResult()){
			            aszTemp = (pConn.getDBString("src"));
			            int iLength = aszTemp.length();
//System.out.println("OrgDBDAO 10151 aszTemp (src) is "+aszTemp);			            
			            aszNID = aszTemp.substring(5,iLength);
			            if(iLength>5)	aszNID = aszTemp.substring(5,iLength);
						iIdNum = Integer.parseInt(aszNID);
						aHeadObj.setORGNID(iIdNum);
					}
	    		}
	        	iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	        	aszSQL2 = aszSQL101 + 
	        			",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
	    				",\n       " + aszDrupalDB + "term_node OrgAffil2 " +
	        	 		",\n       " + aszDrupalDB + "node natlassoc " +
        			" WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	        		"\n       AND natlassoc.type LIKE 'national_association'	" +
	    			"\n       AND natlassoc.title LIKE '" + aHeadObj.getNatlAssociation() + "' " +
					"\n       AND OrgAffil.vid=natlassoc.vid" +
					"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
					"\n       AND OrgAffil.tid=OrgAffil2.tid " +
					"\n       AND OrgAffil2.vid=cckOrg.vid " +
	    			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " + 
    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " + 
    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid ";
	        	break;
        	case OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	if(aHeadObj.getORGUID() < 1){
	        		aHeadObj.appendErrorMsg("user ID required  ");
	        		return -1;
	        	}
	        	if(aHeadObj.getORGAffiliation1TID() < 1){
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + 
	        			",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
        			" WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
					"\n       AND OrgAffil.tid=" + aHeadObj.getORGAffiliation1TID() +
					"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
					"\n       AND OrgAffil.vid=cckOrg.vid " +
	    			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " + 
    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " + 
    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid ";
	        	break;
	    	case OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid  ";
	        	break;
	    	case OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC_W_ALIAS :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid AND contact.field_volorg_owner_uid=n.uid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid  " +
	    			aszPublished; // only view if published
	        	break;
	    	case OrganizationInfoDTO.LOADBY_ORGNID_EMAIL :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid AND contact.field_volorg_owner_uid=n.uid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid ";
	        	break;
        	case OrganizationInfoDTO.LOADBY_ORGNUMBER :
        		aszSQL101 = aszSQL101 + aszSQLFrom;
            	if(aHeadObj.getORGOrgNumber() < 1){
            		aHeadObj.appendErrorMsg("organization ID required  ");
            		return -1;
            	}
            	//aszSQL2 = aszSQL101 + "  WHERE cckOrg.field_volorg_org_id_value=" + aHeadObj.getORGOrgNumber() + 
            	aszSQL2 = aszSQL101 + "  WHERE cckOrg.field_volorg_url_id_value=" + aHeadObj.getORGurlID() + 
        			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid " +
	    			" AND contact.field_volorg_owner_uid=" + aHeadObj.getORGUID() + " ";
    				//" AND n.uid=" + aHeadObj.getORGUID() + " ";
            	break;
        	case OrganizationInfoDTO.LOADBY_ORGNUMBER_PUBLIC :
        		aszSQL101 = aszSQL101 + aszSQLFrom;
            	if(aHeadObj.getORGOrgNumber() < 1){
            		aHeadObj.appendErrorMsg("organization ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + "  WHERE cckOrg.field_volorg_url_id_value=" + aHeadObj.getORGurlID() + 
        			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid AND contact.field_volorg_owner_uid=n.uid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid  " +
	    			aszPublished; // only view if published
            	break;
	    	case OrganizationInfoDTO.LOADBY_ORGURL_ID_W_ALIAS :
        		aszSQL101 = aszSQL101 + aszSQLFrom;
            	if(aHeadObj.getORGurlID() < 1){
            		aHeadObj.appendErrorMsg("organization ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + "  WHERE cckOrg.field_volorg_url_id_value=" + aHeadObj.getORGurlID() + 
        			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid AND contact.field_volorg_owner_uid=n.uid " +
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid  " +
	    			aszPublished; // only view if published

	        	break;
	    	case OrganizationInfoDTO.LOADBY_URL_ALIAS :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	    		if(aHeadObj.getORGUrlAlias().length() < 1){
	    			return -1;
	    		}
	    		aszTemp="";
	    		aszNID="";
	    		// query for the NID by looking at the url_alias table
	    		aszSQLtemp = " SELECT src FROM " + aszDrupalDB + "url_alias alias " + " WHERE alias.dst LIKE 'org/" + aHeadObj.getORGUrlAlias() + "'";
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode = pConn.RunQuery(aszSQLtemp);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				//iRetCode=-1;
				iIdNum = 0;
				if(pConn.getNextResult()){
		            aszTemp = (pConn.getDBString("src"));
		            int iLength = aszTemp.length();
//System.out.println("OrgDBDAO 10290 aszTemp (src) is "+aszTemp);			            
		            aszNID = aszTemp.substring(5,iLength);
		            if(iLength>5)	aszNID = aszTemp.substring(5,iLength);
					iIdNum = Integer.parseInt(aszNID);
					aHeadObj.setORGNID(iIdNum);
				}
				if(iIdNum==0){
					// try path_redirect
					aszSQLtemp = " SELECT  redirect FROM " + aszDrupalDB + "path_redirect WHERE source LIKE 'org/" + aHeadObj.getORGUrlAlias() + "'";
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -999;
					}
					iRetCode = pConn.RunQuery(aszSQLtemp);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -999;
					}
					if(pConn.getNextResult()){
			            aszTemp = (pConn.getDBString("redirect"));
			            int iLength = aszTemp.length();
//System.out.println("OrgDBDAO 10314 aszTemp (src) is "+aszTemp);			            
			            aszNID = aszTemp.substring(5,iLength);
			            if(iLength>5)	aszNID = aszTemp.substring(5,iLength);
						iIdNum = Integer.parseInt(aszNID);
						aHeadObj.setORGNID(iIdNum);
					}
					if(iIdNum==0){
						return -1;
					}
				}
	        	iType=OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC;
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid " +
	    			" AND contact.vid=n.vid " +
//	    			" AND contact.field_volorg_owner_uid=n.uid " + // 2012-03-12 commented out and re-written to just grab delta=0 b/c this is no longer as critical to match, as users are more linked to opps now rather than orgs
//					" AND contact.delta=0 " +// 2012-04-04 commented COMPLETELY b/c this is no longer as critical to match, as users are more linked to opps now rather than orgs
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid  " +
	    			aszPublished; // only view if published
	        	break;
	    	case OrganizationInfoDTO.LOADBY_ORGNID :
	    		aszSQL101 = aszSQL101 + aszSQLFrom;
	        	if(aHeadObj.getORGNID() < 1){
	        		aHeadObj.appendErrorMsg("organization node ID required  ");
	        		return -1;
	        	}
	        	aszSQL2 = aszSQL101 + "  WHERE cckOrg.nid=" + aHeadObj.getORGNID() + 
	    			" AND n.nid=nr.nid AND n.vid=nr.vid AND nr.nid=cckOrg.nid AND nr.vid=cckOrg.vid AND contact.vid=n.vid " + 
	    			" AND loc_inst.vid=nr.vid AND loc_inst.lid=loc.lid " +
	    			" AND contact.field_volorg_owner_uid=" + aHeadObj.getORGUID() + " ";
    				//" AND n.uid=" + aHeadObj.getORGUID() + " ";
	        	break;
        	case OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC :
        		/*
	        	if(aHeadObj.getORGUID() < 1){
	        		aHeadObj.appendErrorMsg("user ID required  ");
	        		return -1;
	        	}
	        	*/
	        	if(aHeadObj.getNatlAssociationNID() < 1){
	        		aHeadObj.appendErrorMsg("no National Association provided ");
	        		return -1;
	        	}
	        	bNatlAssocNode=true;
	        	aszSQL2 = "SELECT natlassoc.uid, cckNatlAssoc.field_natlassoc_portal_banner_value,cckNatlAssoc.field_natlassoc_portal_hdr_tags_value," +
	        			"cckNatlAssoc.field_natlassoc_portal_hdr_value,cckNatlAssoc.field_natlassoc_portal_css_value," +
	        			"cckNatlAssoc.field_natlassoc_portal_footer_value " + 
	        		" FROM " +
	        			" \n       " + aszDrupalDB + "node natlassoc " +
	        			",\n       " + aszDrupalDB + "content_type_national_association cckNatlAssoc " +
	        			",\n       " + aszDrupalDB + "term_node OrgAffil, " + aszDrupalDB + "term_data OrgAffilTermData " +
	    				",\n       " + aszDrupalDB + "content_field_natlassoc_manager manager " +
        			" WHERE cckNatlAssoc.nid=" + aHeadObj.getNatlAssociationNID() + 
	        		"\n       AND natlassoc.type LIKE 'national_association'	" +
	    			"\n       AND natlassoc.title LIKE '" + aHeadObj.getNatlAssociation() + "' " +
					"\n       AND OrgAffil.vid=natlassoc.vid" +
					"\n       AND OrgAffil.tid=OrgAffilTermData.tid " +
	    			"\n       AND OrgAffilTermData.vid=" +  vidOrgAffil + "  " + 
	    			"\n       AND natlassoc.nid=cckNatlAssoc.nid AND natlassoc.vid=cckNatlAssoc.vid " +
	    			"\n       AND manager.vid=natlassoc.vid ";
	        	break;

            default:
        		aszSQL101 = aszSQL101 + aszSQLFrom;
        		aHeadObj.appendErrorMsg("organization ID required  ");
                return 1;
        }
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		aszSQL2 += aszEndWhere;
		
		
		
//if(iType==OrganizationInfoDTO.LOADBY_UID_CONTACT )
//System.out.println(aszSQL2);	      		
		
		
		
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-111; // no results were found
		if(pConn.getNextResult()){
            iRetCode=0;
            if(bNatlAssocNode==false){
                iLid=pConn.getDBInt("loc.lid");
    			aHeadObj.setORGNID(pConn.getDBInt("node_id"));
    			aHeadObj.setORGVID(pConn.getDBInt("version_id"));
    			aHeadObj.setORGOrgNumber(pConn.getDBInt("field_volorg_org_id_value"));
    			aHeadObj.setORGurlID(pConn.getDBInt("field_volorg_url_id_value"));
    			aHeadObj.setORGUID(pConn.getDBInt("uid"));
    			aHeadObj.setORGModerate(pConn.getDBInt("moderate"));
    			aHeadObj.setORGPublished(pConn.getDBInt("status"));
    			aHeadObj.setORGCreateDtNum(pConn.getDBInt("created"));
    			aHeadObj.setORGUpdateDtNum(pConn.getDBInt("changed"));
    			aHeadObj.setORGOrgName(pConn.getDBString("title"));
    			aHeadObj.setORGAddrLine1(pConn.getDBString("street"));
    			aHeadObj.setORGAddrLine2(pConn.getDBString("additional"));
    			aHeadObj.setORGAddrCity(pConn.getDBString("city"));
    			aHeadObj.setORGAddrStateprov(pConn.getDBString("province"));
    			aHeadObj.setORGAddrPostalcode(pConn.getDBString("postal_code"));
    			aHeadObj.setORGAddrCountryName(pConn.getDBString("country"));
    			aHeadObj.setLatitude(pConn.getDBString("latitude"));
    			aHeadObj.setLongitude(pConn.getDBString("longitude"));
    			aHeadObj.setORGHasListings(pConn.getDBString("field_volorg_has_listings_value"));
    			aHeadObj.setORGWebpage(pConn.getDBString("field_volorg_website_url"));
    			aHeadObj.setORGDonateURL(pConn.getDBString("field_donate_url_url"));
    			aHeadObj.setORGListingsURL(pConn.getDBString("field_volorg_website_listings_url"));
    			aHeadObj.setSource(pConn.getDBString("field_org_import_source_value"));
    			aHeadObj.setCVInternSiteApproved(pConn.getDBString("field_cvintern_site_approved_value"));
    			String FedTaxId1 = "";
    			String FedTaxId2 = "";
    			String FedTaxId = pConn.getDBString("field_volorg_ein_value");
    			if(aHeadObj.getORGAddrCountryName().toLowerCase().equals("us")) {
    				StringTokenizer tokenizer = new StringTokenizer(FedTaxId, "-");
    				if(tokenizer.hasMoreTokens()) FedTaxId1 = tokenizer.nextToken();
    				if(tokenizer.hasMoreTokens()) FedTaxId2 = tokenizer.nextToken();
    			}
    			else {
    				FedTaxId1 = FedTaxId;
    			}
    			aHeadObj.setORGFedTaxId(FedTaxId1, FedTaxId2);
    			aHeadObj.setORGMissionStatement(replaceLineBreak(pConn.getDBString("field_volorg_mission_statemt_value")));
    			aHeadObj.setORGOrgDescription(replaceLineBreak(pConn.getDBString("body")));
    			aHeadObj.setORGNumVolOrg(pConn.getDBInt("field_volorg_num_volunteers_value"));
    			aHeadObj.setORGNumVolOrgIntl(pConn.getDBInt("field_volorg_num_volunteers_intl_value"));
    			aHeadObj.setORGNumOppsOrg(pConn.getDBInt("field_volorg_num_opps_value"));
    			aHeadObj.setORGNumServed(pConn.getDBInt("field_volorg_num_served_value"));
    			aHeadObj.setORGFormalTrain(pConn.getDBString("field_volorg_orientation_value"));
    			aHeadObj.setORGOrgStmtFaith(replaceLineBreak(pConn.getDBString("field_volorg_faith_statemt_value")));
    			aHeadObj.setORGOnethirdP(pConn.getDBString("field_volorg_serve_poor_value"));
    			aHeadObj.setORGOrgContactEmail(pConn.getDBString("field_volorg_email_value"));
    			aHeadObj.setORGOrgPhone2(pConn.getDBString("field_volorg_secondary_phone_value"));
    			aHeadObj.setORGSubdom(pConn.getDBString("field_volorg_subdomain_value"));
    			aHeadObj.setSiteInterest(pConn.getDBInt("field_site_interest_value"));
    			aHeadObj.setORGLocalAffil(pConn.getDBString("field_volorg_local_affil_value"));
    			aHeadObj.setORGPopularity(pConn.getDBInt("field_volorg_popularity_value"));
    			
    			aHeadObj.setORGDescTeaser(pConn.getDBString("field_volorg_description_value"));
    			aHeadObj.setORGMissStmntTeaser(pConn.getDBString("field_volorg_mission_statemt_value"));

    			aHeadObj.setPortalBannerURL(pConn.getDBString("field_volorg_portal_banner_value"));
    			aHeadObj.setPortalHeaderTags(pConn.getDBString("field_volorg_portal_header_tags_value"));
    			aHeadObj.setPortalHeader(pConn.getDBString("field_volorg_portal_header_value"));
    			aHeadObj.setPortalCSS(pConn.getDBString("field_volorg_portal_css_value"));
    			aHeadObj.setPortalFooter(pConn.getDBString("field_volorg_portal_footer_value"));
            }else{
    			aHeadObj.setORGNID(aHeadObj.getNatlAssociationNID());// for some other methods called, such as does portal exist already?
//    			aHeadObj.setORGUID(pConn.getDBInt("uid"));
    			aHeadObj.setPortalBannerURL(pConn.getDBString("field_natlassoc_portal_banner_value"));
    			aHeadObj.setPortalHeaderTags(pConn.getDBString("field_natlassoc_portal_hdr_tags_value"));
    			aHeadObj.setPortalHeader(pConn.getDBString("field_natlassoc_portal_hdr_value"));
    			aHeadObj.setPortalCSS(pConn.getDBString("field_natlassoc_portal_css_value"));
    			aHeadObj.setPortalFooter(pConn.getDBString("field_natlassoc_portal_footer_value"));
            }
		}
		if(iRetCode==-111){
			return iRetCode;
		}
		int temp = aHeadObj.getORGNumOppsOrg();
		if(aHeadObj.getORGNumOppsOrg()==0){
			// get count of opportunities from the drupal db
    		aszSQL2=" SELECT COUNT(field_volorg_opp_reference_nid) count FROM " + 
        			aszDrupalDB + "content_field_volorg_opp_reference " +
        			" WHERE vid=" + aHeadObj.getORGVID() + " " +
    					" AND nid=" + aHeadObj.getORGNID() ;
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
        		if(pConn.getNextResult()){
        			aHeadObj.setORGNumOppsOrg(pConn.getDBInt("count"));
        		}
		}
        if(bNatlAssocNode==true){
    		// Portal Name (that user chose) 
    		aszSQL2=" SELECT SQL_CACHE  d.name portal_name, d.tid tid FROM " + 
    			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
    			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPortalName + " " +
    					" AND nr.nid=" + aHeadObj.getNatlAssociationNID() + " ";
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
    		if(pConn.getNextResult()){
                iRetCode=0;
    			aHeadObj.setPortalName(pConn.getDBString("portal_name"));
    			aHeadObj.setPortalNameOrig(pConn.getDBString("portal_name"));
    			aHeadObj.setPortalTID(pConn.getDBInt("tid"));
    			aHeadObj.setUsePortal("Yes");
    		}

    		
    		// Org Affiliation that it's associated with 
    		aszSQL2=" SELECT OrgAffilTermData.tid, OrgAffilTermData.name " +
    			"FROM " + aszDrupalDB + "node natlassoc , " + aszDrupalDB + "term_node OrgAffil, " +
    				aszDrupalDB + "term_data OrgAffilTermData, " + aszDrupalDB + "content_field_natlassoc_manager manager " +
    			"WHERE natlassoc.nid=" + aHeadObj.getNatlAssociationNID() + " AND natlassoc.type LIKE 'national_association' " +
    					" AND OrgAffil.vid=natlassoc.vid AND OrgAffil.tid=OrgAffilTermData.tid AND OrgAffilTermData.vid=" + vidOrgAffil +
    					" AND manager.vid=natlassoc.vid AND field_natlassoc_manager_uid=" + aHeadObj.getORGUID() + " ";
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
    		if(pConn.getNextResult()){
                iRetCode=0;
    			aHeadObj.setORGAffiliation1TID(pConn.getDBInt("tid"));
                iRetCode=0;
    		}

    		
    		return iRetCode;
        }
		
		// ************ Load URL ALIAS if it exists ********************
		// URL ALIAS for org
		aszSQL2=null ;
		if(aHeadObj.getORGNID() > 1){
			aszSQL101 = " SELECT SQL_CACHE dst FROM " + 
				aszDrupalDB + "url_alias " +
				"WHERE src like 'node/" + aHeadObj.getORGNID() + "' " ;
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
				aHeadObj.setORGUrlAlias(pConn.getDBString("dst"));
			}
		}
		
		// ************ get count to find out if this organization has listings on our site ********************
		aszSQL2=null ;
		if(aHeadObj.getORGNID() > 1){
			aszSQL101 = " SELECT COUNT(field_volorg_opp_reference_nid) num_opps FROM " + 
				aszDrupalDB + "content_field_volorg_opp_reference " +
				"WHERE nid =" + aHeadObj.getORGNID() + " " ;
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
				aHeadObj.setORGNumOpps(pConn.getDBInt("num_opps"));
			}
		}
		
		// ************ get count to find out if this organization has listings on our site ********************
		aszSQL2=null ;
		if(aHeadObj.getORGNID() > 1){
			aszSQL101 = " SELECT COUNT(field_volorg_opp_reference_nid) num_opps_published FROM " + 
					aszDrupalDB + "content_field_volorg_opp_reference ref, " + aszDrupalDB + "node n " +
				"WHERE ref.nid =" + aHeadObj.getORGNID() + " AND ref.field_volorg_opp_reference_nid=n.nid AND n.status=1" ;
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
				aHeadObj.setORGNumOppsPublished(pConn.getDBInt("num_opps_published"));
			}
		}
		// ************ START DRUPAL LOCATION PHONE/FAX SECTION ********************
		
		// Location Phone
		aszSQL2 = " SELECT phone FROM " + aszDrupalDB + "location_phone where lid=" + iLid;
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
		// iRetCode=-1; // - not required; would indicate that it may be required to have a denomination
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGOrgPhone1(pConn.getDBString("phone"));
		}
		// Location Fax
		aszSQL2 = " SELECT fax FROM " + aszDrupalDB + "location_fax where lid=" + iLid;
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
		// iRetCode=-1; // - not required; would indicate that it may be required to have a denomination
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGFax1(pConn.getDBString("fax"));
		}
		
		
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		
		boolean bLoadByMethod = false; 
        if ( 	(iType==OrganizationInfoDTO.LOADBY_ORGNID_PUBLIC ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNID ) ||
        		(iType==OrganizationInfoDTO.LOADBY_SITEADMIN ) ||
        		(iType==OrganizationInfoDTO.LOADBY_NATL_ASSOC ) ||
        		(iType==OrganizationInfoDTO.LOADBY_UID_CONTACT ) ||
        		(iType==OrganizationInfoDTO.LOADBY_NATL_ASSOC_ORG_AFFIL ) ||
        		(iType==OrganizationInfoDTO.LOADBY_UID ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNUMBER ) ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNUMBER_PUBLIC ) 
        ){
        	bLoadByMethod = true; 
        }
		// Whether the organization accepts volunteers from another country
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name, d.tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidIntlVols + " " ;
        if ( bLoadByMethod == true ||
        		(iType==OrganizationInfoDTO.LOADBY_ORGNID_EMAIL ) ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1; // ONLY REQUIRED >2006-09-05; HAS NOT BEEN PROPOGATED FOR THOSE PRIOR TO THIS DATE!!! indicate that member type may be required
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGTakesIntlVols(pConn.getDBString("name"));
			aHeadObj.setORGTakesIntlVolsTID(pConn.getDBInt("tid"));
		}

		// Denominational Affilation
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name denomination, d.tid tid, t.auto_tag FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidDenomAffil + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		index=0;
		indexAutoTag=0;
		a_iTemp= new int[50];
		a_iTempPublic= new int[50];
		while(pConn.getNextResult() && index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
            aszTemp=pConn.getDBString("denomination");
			aHeadObj.setDenominationalAffils(aHeadObj.getDenominationalAffils() + aszTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
            iAutoTag =pConn.getDBInt("auto_tag");
            if(iAutoTag==0){// then it was user-entered and not generated by our tagging system
    			aHeadObj.setDenomAffilsPublic(aHeadObj.getDenomAffilsPublic() + aszTemp + ",");
    			a_iTempPublic[indexAutoTag]=iTemp;
    			indexAutoTag++;
            }
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setDenominationalAffilsArray(a_iContainer);
		a_iContainer= new int[indexAutoTag];
		for(int i=0; i<indexAutoTag; i++){
			a_iContainer[i]=a_iTempPublic[i];
		}
		if(a_iContainer.length>0) aHeadObj.setDenomAffilsPublicArray(a_iContainer);
		
		if(aHeadObj.getDenominationalAffils().endsWith(",")){
			aHeadObj.setDenominationalAffils(aHeadObj.getDenominationalAffils().substring(0, aHeadObj.getDenominationalAffils().length()-1));
		}
		aHeadObj.setDenominationalAffils(aHeadObj.getDenominationalAffils().replaceAll(",", ", "));
		if(aHeadObj.getDenomAffilsPublic().endsWith(",")){
			aHeadObj.setDenomAffilsPublic(aHeadObj.getDenomAffilsPublic().substring(0, aHeadObj.getDenomAffilsPublic().length()-1));
		}
		aHeadObj.setDenomAffilsPublic(aHeadObj.getDenomAffilsPublic().replaceAll(",", ", "));

		// Organizational Affiliation / Partner section
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name org_affiliation, d.tid tid, t.auto_tag FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidOrgAffil + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		index=0;
		indexAutoTag=0;
		a_iTemp= new int[50];
		a_iTempPublic= new int[50];
		while(pConn.getNextResult() && index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
            aszTemp=pConn.getDBString("org_affiliation");
			aHeadObj.setOrgAffiliations(aHeadObj.getOrgAffiliations() + aszTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
            iAutoTag =pConn.getDBInt("auto_tag");
            if(iAutoTag==0){// then it was user-entered and not generated by our tagging system
    			aHeadObj.setOrgAffiliationsPublic(aHeadObj.getOrgAffiliationsPublic() + aszTemp + ",");
    			a_iTempPublic[indexAutoTag]=iTemp;
    			indexAutoTag++;
            }
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setOrgAffiliationsArray(a_iContainer);
		a_iContainer= new int[indexAutoTag];
		for(int i=0; i<indexAutoTag; i++){
			a_iContainer[i]=a_iTempPublic[i];
		}
		if(a_iContainer.length>0) aHeadObj.setOrgAffiliationsPublicArray(a_iContainer);
		
		if(aHeadObj.getOrgAffiliations().endsWith(",")){
			aHeadObj.setOrgAffiliations(aHeadObj.getOrgAffiliations().substring(0, aHeadObj.getOrgAffiliations().length()-1));
		}
		aHeadObj.setOrgAffiliations(aHeadObj.getOrgAffiliations().replaceAll(",", ", "));
		if(aHeadObj.getOrgAffiliationsPublic().endsWith(",")){
			aHeadObj.setOrgAffiliationsPublic(aHeadObj.getOrgAffiliationsPublic().substring(0, aHeadObj.getOrgAffiliationsPublic().length()-1));
		}
		aHeadObj.setOrgAffiliationsPublic(aHeadObj.getOrgAffiliationsPublic().replaceAll(",", ", "));

		// Program Types
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name program_type, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidProgramType + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGProgramTypes(pConn.getDBString("program_type"));
			aHeadObj.setORGProgramTypes1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				//aHeadObj.setORGProgramTypes2(pConn.getDBString("skills"));
				//aHeadObj.setORGProgramTypes2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					//aHeadObj.setORGProgramTypes3(pConn.getDBString("skills"));	
					//aHeadObj.setORGProgramTypes3TID(pConn.getDBInt("tid"));
				}				
			}
		}

		// Member type: church, non-profit christian, non-profit non-christian
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name member_type, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidMemberType + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		//iRetCode=-1; // ONLY REQUIRED >2006-09-05; HAS NOT BEEN PROPOGATED FOR THOSE PRIOR TO THIS DATE!!! indicate that member type may be required
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setORGMembertype(pConn.getDBString("member_type"));
			aHeadObj.setORGMembertypeTID(pConn.getDBInt("tid"));
		}


		// Portal Name (that user chose) 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name portal_name, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPortalName + " " ;
        if ( bLoadByMethod == true ){
    		if(aHeadObj.getORGNID() < 1){
    			return -111; // there were no results on an nid
    		}
        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
		}else{
			setErr("request type not supported");
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
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setPortalName(pConn.getDBString("portal_name"));
			aHeadObj.setPortalNameOrig(pConn.getDBString("portal_name"));
			aHeadObj.setPortalTID(pConn.getDBInt("tid"));
			aHeadObj.setUsePortal("Yes");
		}

		
		
		// Primary Opportunity Types
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPrimaryOppTypes + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[10];// new int[15];
		while(pConn.getNextResult() && index < 10){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setTypesOfOppsArray(a_iContainer);
		
		// Position Types
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPosType + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[10];
		while(pConn.getNextResult() && index < 10){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setPositionTypesArray(a_iContainer);
		
		// Service Areas of child opps
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidService + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[50];
		while(pConn.getNextResult() & index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setServiceAreasArray(a_iContainer);
		
		// skills of child opps
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidSkill + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult() && index < 50){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setSkillsArray(a_iContainer);
		
		// great for of child opps
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolInfo + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[15];
		while(pConn.getNextResult() && index < 15){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setGreatForArray(a_iContainer);
		
		// trip length of child opps
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidTripLength + " " ;
	        if ( bLoadByMethod == true ){
	    		if(aHeadObj.getORGNID() < 1){
	    			return -111; // there were no results on an nid
	    		}
	        	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
			}else{
				setErr("request type not supported");
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
		index=0;
		a_iTemp= new int[15];
		while(pConn.getNextResult() && index < 15){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setTripLengthArray(a_iContainer);
		
		// ************ END DRUPAL TAXONOMY SECTION ********************
		// ************ END DRUPAL TAXONOMY SECTION ********************
		// ************ END DRUPAL TAXONOMY SECTION ********************

		if(aszRailsDB.length()>0){
			/*
			 * Load Questionnaires
			 */
			String sqlQuestionnaires = 
				"SELECT  questionnaires.id, questionnaires.title " + 
			    " FROM " + aszRailsDB + "questionnaires " +
				" WHERE organization_nid = '" + aHeadObj.getORGNID() + "' OR organization_nid IS NULL";
			
			iRetCode = pConn.RunQry(sqlQuestionnaires);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			aHeadObj.setQuestionnaires(new ArrayList<QuestionnaireDTO>());
			while(pConn.getNextResult()) {
				QuestionnaireDTO questionnaire = new QuestionnaireDTO();
				questionnaire.setID(pConn.getDBInt("id"));
				questionnaire.setTitle(pConn.getDBString("title"));
				questionnaire.setOrganizationNID(aHeadObj.getORGNID());
				aHeadObj.getQuestionnaires().add(questionnaire);
			}
		}
		
		aHeadObj.setLoadByMethod(iType);
		return iRetCode;
	}
    // end-of method loadOrganizationFromDB()


	

    /**
	* load an organization from feeds
	*/
	public int loadOrganizationFromFeedsDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		String aszSQL2=null ;
		String aszTemp="", aszNID="",aszSQLtemp ="";
	    MethodInit("loadFromFeedsDB");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;

	    int index=0, iTemp=0;
		int iAutoTag=0, indexAutoTag=0 ;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		int[] a_iTempPublic= new int[50];
		
		String aszPublished = " and status=1 ";

		MethodInit("loadOrganizationFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
		String aszSQL101 = "SELECT " + aszOrgsTable + ".id, sid nid, uid, title, changed, created, mission_statement, " +
				" short_description, description, statement_of_faith, " +
				aszOrgsTable + ".source, url_alias, status, " +
				" location_street, location_additional, location_city, location_province, location_postal_code, location_country, " +
				" location_latitude latitude, location_longitude longitude, " +
				" region, metro, country_name, province_name, " +
				" popularity, position_type, primary_opp_type, program_type, " +
				" service_areas, great_for, org_affil, denom, " +
				" serves_poor, orientation_req, ein, num_served, num_volunteers, " +
				" email, fax, phone, website_url, subdomain, opps_list_url, donate_url, " +
				" num_volunteers_intl, has_listings, listings_url, " +
				" num_opps, secondary_phone, source, imported_site_interest, site_interest, " +
				" sg_explanation, sg_signature, sg_uid, sg_signature_time, sg_status, sg_email_sent " +
				" portal, faith, international_volunteer_options, member_type, service_areas, skills, great_for, " +
				" status, num_opps, source_guid, " +
				" geographic_focus, funding_limitations, type_of_grantmaker, year_founded," +
				" assets, income, expenditures, total_giving, total_giving_txt, form_990_rev_amt, form_990_rev_amt_txt, ntee_major_category, ntee_minor_category  " +
//				" url as source_url, search_page_logo_url, detail_page_logo_url "+
				" FROM  " + aszSocialGraphDB  + aszOrgsTable  +
				" LEFT OUTER JOIN " + aszSocialGraphDB + aszFeedSourceTable +
				" ON " + aszSocialGraphDB + aszOrgsTable + ".source = " + aszFeedSourceTable + ".name";
		String aszSQLwhere = " ";	    
	    switch( iType ){
			case OrganizationInfoDTO.LOADBY_URL_ALIAS_FEEDS :
	    		aszSQLwhere = 
	    			" WHERE url_alias LIKE '" + aHeadObj.getRequestType() + "/" +
	    					aHeadObj.getORGUrlAlias() + "' " + aszPublished ;  //only view if published
	        	break;
	
	        default:
				setErr("request type not supported");
	            return -1;
	    }
	
	
	    aszSQL2 = aszSQL101 + aszSQLwhere;
	    
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
		iRetCode=-111; // no results were found
		if(pConn.getNextResult()){
            iRetCode=0;
    		aHeadObj.setORGNID(pConn.getDBInt("nid"));
    		aHeadObj.setORGOrgName(pConn.getDBString("title"));
    		aHeadObj.setORGCreateDtNum(pConn.getDBInt("created"));
    		aHeadObj.setORGUpdateDtNum(pConn.getDBInt("changed"));
    		aHeadObj.setORGUID(pConn.getDBInt("uid"));
    		aHeadObj.setORGMissionStatement(replaceLineBreak(pConn.getDBString("mission_statement")));
    		aHeadObj.setORGOrgDescription(replaceLineBreak(pConn.getDBString("description")));
    		aHeadObj.setORGDescTeaser(pConn.getDBString("short_description"));
    		aHeadObj.setORGOrgStmtFaith(replaceLineBreak(pConn.getDBString("statement_of_faith")));
    		aHeadObj.setORGMissStmntTeaser(pConn.getDBString("mission_statement"));

    		aHeadObj.setORGPublished(pConn.getDBInt("status"));
//    		aHeadObj.setORGurlID(pConn.getDBInt("field_volorg_url_id_value"));
//    		aHeadObj.setORGModerate(pConn.getDBInt("moderate"));
    		aHeadObj.setORGAddrLine1(pConn.getDBString("location_street"));
    		aHeadObj.setORGAddrLine2(pConn.getDBString("location_additional"));
    		aHeadObj.setORGAddrCity(pConn.getDBString("location_city"));
    		aHeadObj.setORGAddrStateprov(pConn.getDBString("location_province"));
    		aHeadObj.setORGAddrPostalcode(pConn.getDBString("location_postal_code"));
    		aHeadObj.setORGAddrCountryName(pConn.getDBString("location_country"));
    		aHeadObj.setLatitude(pConn.getDBString("latitude"));
    		aHeadObj.setLongitude(pConn.getDBString("longitude"));
			aHeadObj.setORGOrgPhone1(pConn.getDBString("phone"));
			aHeadObj.setORGFax1(pConn.getDBString("fax"));
    		aHeadObj.setORGPopularity(pConn.getDBInt("popularity"));
    		aHeadObj.setORGFormalTrain(pConn.getDBString("orientation_req"));
    		aHeadObj.setORGOnethirdP(pConn.getDBString("serves_poor"));
			aHeadObj.setORGMembertype(pConn.getDBString("member_type"));
    		
    		String FedTaxId1 = "";
    		String FedTaxId2 = "";
    		String FedTaxId = pConn.getDBString("ein");
    		if(aHeadObj.getORGAddrCountryName().toLowerCase().equals("us")) {
    			StringTokenizer tokenizer = new StringTokenizer(FedTaxId, "-");
    			if(tokenizer.hasMoreTokens()) FedTaxId1 = tokenizer.nextToken();
    			if(tokenizer.hasMoreTokens()) FedTaxId2 = tokenizer.nextToken();
    		}
    		else {
    			FedTaxId1 = FedTaxId;
    		}
    		aHeadObj.setORGFedTaxId(FedTaxId1, FedTaxId2);

    		aHeadObj.setORGNumServed(pConn.getDBInt("num_served"));
    		aHeadObj.setORGNumVolOrg(pConn.getDBInt("num_volunteers"));
    		aHeadObj.setORGHasListings(pConn.getDBString("has_listings"));
    		aHeadObj.setORGWebpage(pConn.getDBString("website_url"));
    		aHeadObj.setORGSubdom(pConn.getDBString("subdomain"));
    		aHeadObj.setORGListingsURL(pConn.getDBString("listings_url"));
    		aHeadObj.setORGDonateURL(pConn.getDBString("donate_url"));
    		aHeadObj.setSource(pConn.getDBString("source"));
    		aHeadObj.setORGNumVolOrgIntl(pConn.getDBInt("num_volunteers_intl"));
    		aHeadObj.setORGNumOppsOrg(pConn.getDBInt("num_opps"));
    		aHeadObj.setORGOrgContactEmail(pConn.getDBString("email"));
    		aHeadObj.setORGOrgPhone2(pConn.getDBString("secondary_phone"));
    		aHeadObj.setSiteInterest(pConn.getDBInt("site_interest"));
    		if(aHeadObj.getSiteInterest()==false){
        		aHeadObj.setSiteInterest(pConn.getDBInt("imported_site_interest"));
    		}
    		aHeadObj.setORGUrlAlias(pConn.getDBString("url_alias"));

    		
			aHeadObj.setDenominationalAffils(pConn.getDBString("denom").replaceAll(",", ", "));
			aHeadObj.setDenomAffilsPublic(pConn.getDBString("denom").replaceAll(",", ", "));
			aHeadObj.setOrgAffiliations(pConn.getDBString("org_affil").replaceAll(",", ", "));
			aHeadObj.setOrgAffiliationsPublic(pConn.getDBString("org_affil").replaceAll(",", ", "));
			aHeadObj.setORGProgramTypes(pConn.getDBString("program_type").replaceAll(",", ", "));

			aHeadObj.setGeoFocus(pConn.getDBString("geographic_focus"));
			String aszServiceAreas = pConn.getDBString("service_areas");
			aHeadObj.setServiceAreas(aszServiceAreas.replaceAll(";", ", "));
			aHeadObj.setFundingLimitations(pConn.getDBString("funding_limitations"));
			aHeadObj.setTypeOfFunder(pConn.getDBString("type_of_grantmaker"));
			aHeadObj.setYearFounded(pConn.getDBInt("year_founded"));
			aHeadObj.setAssets(pConn.getDBString("assets"));
			aHeadObj.setIncome(pConn.getDBString("income"));
			aHeadObj.setExpenditures(pConn.getDBString("expenditures"));
			aHeadObj.setTotalGiving(pConn.getDBString("total_giving_txt"));
			aHeadObj.setRevenue(pConn.getDBString("form_990_rev_amt"));
			aHeadObj.setNTEEMajorCat(pConn.getDBString("ntee_major_category"));
			aHeadObj.setNTEEMinorCat(pConn.getDBString("ntee_minor_category"));
			
			
/*			
    		 aszSQL101 = 
    				" position_type, primary_opp_type,  " +
    				" service_areas, great_for, , , " +
    				" portal, faith, international_volunteer_options, , service_areas, skills, great_for, " +
     				" region, metro, country_name, province_name, " ;

			aszSQL2=null ;
			aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
				aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
				"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolInfo + " " ;
		   aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getORGVID() + " ";
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
			index=0;
			a_iTemp= new int[15];
			while(pConn.getNextResult() && index < 15){
	            iRetCode=0;
	            iTemp=pConn.getDBInt("tid");
				a_iTemp[index]=iTemp;
				index++;
			}
			a_iContainer= new int[index];
			for(int i=0; i<index; i++){
				a_iContainer[i]=a_iTemp[i];
			}
			if(a_iContainer.length>0) aHeadObj.setGreatForArray(a_iContainer);
*/			
		}
		if(iRetCode==-111){
			return iRetCode;
		}

		aHeadObj.setLoadByMethod(iType);
		return iRetCode;
	}
    // end-of method loadOrganizationFromFeedsDB()
	
	/**
	* Does organization exist in table organizationinfo
	* return 0 if = yes; found organization
	*/
	public int IsOrganizationInSystem( ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		String aszSQL=null,aszTemp=null,aszOrgName=null,aszPostalCode=null,aszCountry=null ;
        MethodInit("IsOrganizationInSystem");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;
		switch (iType){
			case 222:
				aszOrgName = aHeadObj.getORGOrgName();
		        if(aszOrgName.length() < 2){
		        	setErr("name required");
		        	return -1;
		        }
		        aszPostalCode = aHeadObj.getORGAddrPostalcode();
		        if(aszPostalCode.length() < 2){
		        	setErr("postal code required");
		        	return -1;
		        }
		        aszCountry = aHeadObj.getORGAddrCountryName();
		        if(aszCountry.length() < 2){
		        	setErr("country required");
		        	return -1;
		        }
		        aszSQL = "SELECT n.title org_name FROM " + aszDrupalDB + "node n, " + 
			        aszDrupalDB + "content_type_organization cckOrg, " + 
			        aszDrupalDB + "location_instance loc_inst , " + aszDrupalDB + "location loc " +
			        " WHERE n.vid = cckOrg.vid AND n.vid = loc_inst.vid AND loc_inst.lid = loc.lid " +
			        " AND n.title='" + replacesinglequote(aszOrgName) + 
			        "' AND loc.postal_code='"  + replacesinglequote(aszPostalCode) + 
			        "' AND loc.country='" + aszCountry +"' "  ;
				break;
			default:
	        	setErr("request not implemented");
				return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
			aszOrgName = pConn.getDBString("org_name") ;
            return 0;
		}
		return -1;
    }
    // end-of method IsOrganizationInSystem()

    /**
	* insert a row into table org_opportunitiesinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertSearchQuery(ABREDBConnection pConn, SearchParms aSrchParmObj, int iType ){
		int iRetCode=0, iDeltaMax=0;
		String aszSQLdrupal101 = "";
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

		// add to um_content_type_volunteer_opportunity
		aszSQLdrupal101="INSERT INTO " + aszVolengDB + "searchinfo" +
				"(date_time,query_string,subdomain,referring_page,search_method,search_form,user_agent," +
				"yielded_results,search_results_count,searched_feeds," +
				"portal_tid, portal," +
				"nid,position_type_tid," +
				"service_areas,servicearea1_tid,servicearea2_tid,servicearea3_tid," +
				"skill_types,skill1_tid,skill2_tid,skill3_tid," +
				"looking_for," +
				"great_for_kid_tid,great_for_senior_tid,great_for_teen_tid,great_for_group_tid,great_for_family_tid," +
				"cause_topics," +
				"postal_code,distance,latitude,longitude,city,state,country," +
				"metro_tid,country_tid,region_tid," +
				"frequency_tid,org_name,group_size_min,group_size_max,trip_length_tid," +
				"room_board_tid,stipend_tid,medical_insurance_tid,transport_tid,americorps_tid,work_study_tid," +
				"program_type_tid,denom_affil_tid,org_affil1_tid,org_affil2_tid,org_affil3_tid,org_affil4_tid,org_affil5_tid) " + 
				"VALUES({fn now()},?,?,?,?,?,?," + iType + ",?,?," +
				"?,?," +
				"?,?," +
				"?,?,?,?," +
				"?,?,?,?," +
				"?," +
				"?,?,?,?,?," +
				"?," +
				"?,?,?,?,?,?,?," +
				"?,?,?," +
				"?,?,?,?,?," +
				"?,?,?,?,?,?," +
				"?,?,?,?,?,?,?) "; 
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSearchQueryString() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSubdomain() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getReferringPage() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSearchMethod() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSearchForm() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getUserAgent() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getSearchResultsCount() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getSearchedFeeds() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getPortal() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getPortalName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPPositionTypeTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getServiceAreasTIDs() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPServiceArea1TID() ); 
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPServiceArea2TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPServiceArea3TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSkillTypes() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPSkills1TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPSkills2TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPSkills3TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getLookingForTIDs() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getGreatForKidTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getGreatForSeniorTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getGreatForTeenTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getGreatForGroupTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getGreatForFamilyTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getCauseTopics() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getPostalCode() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getDistance() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSearchLatitude1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getSearchLongitude1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getState() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getCountry() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getCityTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getCountryTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getRegionTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPFrequencyTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aSrchParmObj.getOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getMinSize() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getMaxSize() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPTripLengthTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPRoomBoardTID());
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPStipendTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPMedInsurTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPTransportTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPAmeriCorpsTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOPPWorkStudyTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getProgramTypeTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getDenomAffilTID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOrgAffil1TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOrgAffil2TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOrgAffil3TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOrgAffil4TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aSrchParmObj.getOrgAffil5TID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
//			return -1;
		}
		iRetCode=-1;
		return 0;
	}
	// end-of method insertOpportunityIntoDB()


	/**
	* LocationTaxonomyDB
	*/
	public int LocationTaxonomyDB(ABREDBConnection pConn, OrganizationInfoDTO aOrgObj ){
		int iRetCode=0,iType=0;
		MethodInit("LocationTaxonomyDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aOrgObj){
			setErr("null input object");
			return -1;
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		
		iType=101;
        OrgOpportunityInfoDTO aOpportObj = new OrgOpportunityInfoDTO();

		return LocationTaxonomyDB(pConn, aOrgObj, aOpportObj, iType );
	}
	/**
	* LocationTaxonomyDB
	*/
	public int LocationTaxonomyDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aOpportObj ){
		int iRetCode=0,iType=0;
		MethodInit("LocationTaxonomyDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aOpportObj){
			setErr("null input object");
			return -1;
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		
		iType=102;
		OrganizationInfoDTO aOrgObj = new OrganizationInfoDTO();

		return LocationTaxonomyDB(pConn, aOrgObj, aOpportObj, iType );
	}
	/**
	* LocationTaxonomyDB
	*/
	public int LocationTaxonomyDB(ABREDBConnection pConn, OrganizationInfoDTO aOrgObj, OrgOpportunityInfoDTO aOpportObj, int iType ){
		int iRetCode=0;
		int iOrgNID=-1, iOrgVID=-1, iOppNID=-1, iOppVID=-1, iNID=-1, iVID=-1, itid=-1, ilid=-1;
		String aszSQL101 = "",Qry1 = "";
		String aszCountry="", aszProvince="", aszPostal="";
		String aszName="", aszRegionName="", aszSubRegionName="", aszCountryName="", aszProvinceName="", aszMetroName="";
		int iRegion=0, iSubRegion=0, iCountry=0, iProvince=0, iMetro=0;

		MethodInit("LocationTaxonomyDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aOrgObj){
			setErr("null input object");
			return -1;
		}
		if(iType==102){// OrgOpportunityInfoDTO
			if(null == aOpportObj){
				setErr("null input object");
				return -1;
			}
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}

	    iOrgNID = aOrgObj.getORGNID();
	    iOrgVID = aOrgObj.getORGVID();
	    iOppNID = aOpportObj.getOPPNID();
	    iOppVID = aOpportObj.getOPPVID();

	    if(iType==101){// OrganizationInfoDTO
	    	iNID=iOrgNID;
	    	iVID=iOrgVID;
		    aszCountry=aOrgObj.getORGAddrCountryName();
		    aszProvince=aOrgObj.getORGAddrStateprov();
		    aszPostal=aOrgObj.getORGAddrPostalcode();
	    }else if(iType==102){// OrgOpportunityInfoDTO
	    	iNID=iOppNID;
	    	iVID=iOppVID;
		    aszCountry=aOpportObj.getOPPAddrCountryName();
		    aszProvince=aOpportObj.getOPPAddrStateprov();
		    aszPostal=aOpportObj.getOPPAddrPostalcode();
	    }

	    iRetCode=0;

//		vidState=52, vidCity=15, vidCountry=261, 

	    if(iType==101 || iType==102){
			// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
			aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
					"WHERE nid = " + iNID + "   " + 
					" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
					"term_data WHERE vid IN (" + vidState + "," + vidCity + "," + vidCountry + "," + vidRegion + ") ) ";
			iRetCode=pConn.PrepQuery(aszSQL101);
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
	
			
			// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
			//	Grab the Metropolitan Area for the user, if the user is in the US
		    if(aszCountry.equalsIgnoreCase("us")){
				Qry1 = "SELECT MetroName  FROM " + aszVolengDB + "ZIPMSAs WHERE ZIP LIKE '" + aszPostal +"' ";
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
					aszMetroName = pConn.getDBString("MetroName");
				} else {
	//				itid=-1;
				}
				iRetCode=0;
				if(aszMetroName.length()>1){
					Qry1 = "SELECT tid, name  FROM " + aszDrupalDB + "term_data WHERE vid=" + vidCity + " AND name LIKE '" + aszMetroName +"' ";
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
						aszName = pConn.getDBString("name");
					    if(iType==101){// OrganizationInfoDTO
						    aOrgObj.setCityTID(itid);
						    aOrgObj.setCityName(aszName);
					    }else if(iType==102){// OrgOpportunityInfoDTO
						    aOpportObj.setCityTID(itid);
						    aOpportObj.setCityName(aszName);
					    }
						// add to um_term_node
						aszSQL101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iNID +"," + itid +  "," + iVID + " )";
						iRetCode=pConn.PrepQuery(aszSQL101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
	//					itid=-1;
					}
					iRetCode=0;
				}
		    }
		    
			//	Grab the Province for the user, if sufficient data is provided
		    if(aszProvince.length()>1){
				Qry1 = "SELECT state_name  FROM " + aszVolengDB + "country_stateprovince WHERE state_code LIKE '" + aszProvince +"' ";
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
					aszProvinceName = pConn.getDBString("state_name");
				} else {
	//					itid=-1;
				}
				iRetCode=0;
				if(aszProvinceName.length()>1){
					Qry1 = "SELECT tid, name  FROM " + aszDrupalDB + "term_data WHERE vid=" + vidState + " AND name LIKE '" + aszProvinceName +"' ";
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
						aszName = pConn.getDBString("name");
					    if(iType==101){// OrganizationInfoDTO
						    aOrgObj.setStateTID(itid);
						    aOrgObj.setStateName(aszName);
					    }else if(iType==102){// OrgOpportunityInfoDTO
						    aOpportObj.setStateTID(itid);
						    aOpportObj.setStateName(aszName);
					    }
						// add to um_term_node
						aszSQL101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iNID +"," + itid +  "," + iVID + " )";
						iRetCode=pConn.PrepQuery(aszSQL101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
	} else {
	//						itid=-1;
					}
					iRetCode=0;
				}
		    }
		    
			//	Grab the Country for the user, if sufficient data is provided
		    if(aszCountry.length()>1){
				Qry1 = "SELECT name, region, subregion  FROM " + aszVolengDB + "country WHERE iso LIKE '" + aszCountry +"' ";
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
					aszCountryName = pConn.getDBString("name");
					aszRegionName = pConn.getDBString("region");
					aszSubRegionName = pConn.getDBString("subregion");
				} else {
	//					itid=-1;
				}
				iRetCode=0;
				if(aszCountryName.length()>1){
					Qry1 = "SELECT tid, name  FROM " + aszDrupalDB + "term_data WHERE vid=" + vidCountry + " AND name LIKE '" + aszCountryName +"' ";
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
						aszName = pConn.getDBString("name");
					    if(iType==101){// OrganizationInfoDTO
						    aOrgObj.setCountryTID(itid);
						    aOrgObj.setCountryName(aszName);
					    }else if(iType==102){// OrgOpportunityInfoDTO
						    aOpportObj.setCountryTID(itid);
						    aOpportObj.setCountryName(aszName);
					    }
						// add to um_term_node
						aszSQL101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iNID +"," + itid +  "," + iVID + " )";
						iRetCode=pConn.PrepQuery(aszSQL101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
					//						itid=-1;
					}
					iRetCode=0;
				}
				if(aszRegionName.length()>1){
					Qry1 = "SELECT tid, name  FROM " + aszDrupalDB + "term_data WHERE vid=" + vidRegion + " AND name LIKE '" + aszRegionName +"' ";
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
						aszName = pConn.getDBString("name");
					    if(iType==101){// OrganizationInfoDTO
						    aOrgObj.setRegionTID(itid);
						    aOrgObj.setRegionName(aszName);
					    }else if(iType==102){// OrgOpportunityInfoDTO
						    aOpportObj.setRegionTID(itid);
						    aOpportObj.setRegionName(aszName);
					    }
						// add to um_term_node
						aszSQL101="INSERT INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iNID +"," + itid +  "," + iVID + " )";
						iRetCode=pConn.PrepQuery(aszSQL101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();//RunUpdate(aszSQL101); 2009-01-20 - fixed to use prepqry's so it doesn't crash after other prepqry's
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=-1;
					} else {
					//						itid=-1;
					}
					iRetCode=0;
				}
		    }
	    }
		return 0;
	}
	// end-of method LocationTaxonomyDB()

	/**
	* searchForZipCodeLatLongInDB
	*/
	public int searchForZipCodeLatLongInDB(ABREDBConnection pConn, SearchParms aSrchParmObj, int iUse){
		int iRetCode=0;
		String Qry1 = "";

		MethodInit("searchForZipCodeLatLongInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input object");
			return -1;
		}

	    iRetCode=-1;// initially set to NO search results... shouldn't matter, though, really
	    if(iUse==0){
			Qry1 = "SELECT latitude, longitude  FROM " + aszDrupalDB + "zipcodes WHERE zip LIKE '" + aSrchParmObj.getPostalCode() +"' ";
	    }else if(iUse==1){
			Qry1 = "SELECT latitude, longitude  FROM " + aszDrupalDB + "zipcodes WHERE zip LIKE '" + aSrchParmObj.getNotLclPostalCode() +"' ";
	    }
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
			iRetCode=0;
		    if(iUse==0){
				aSrchParmObj.setSearchLatitude1(pConn.getDBString("latitude"));
				aSrchParmObj.setSearchLongitude1(pConn.getDBString("longitude"));
		    }else if(iUse==1){
				aSrchParmObj.setNotLclSearchLatitude1(pConn.getDBString("latitude"));
				aSrchParmObj.setNotLclSearchLongitude1(pConn.getDBString("longitude"));
		    }
		} 
		return iRetCode;
	}
	// end-of method LocationTaxonomyDB()

	/**
	* doesPortalExistForOrgs for an organization
	*/
	public String doesPortalExistForOrgs(ABREDBConnection pConn, ArrayList aListObj ){
	    int iRetCode=0, iNID=0;
	    String aszPortal = "";
	    String aszNIDList = "";
	    Iterator<OrganizationInfoDTO> itr = aListObj.iterator();
	    while (itr.hasNext()) {
	    	iNID = itr.next().getORGNID();
	    	if(iNID > 0){
	    		if(aszNIDList.length()>1){
	    			aszNIDList+=","+iNID;
	    		}else{
	    			aszNIDList=iNID+"";
	    		}
	    	}
	    }

	    if(aszNIDList.length()==0){
	    	return "";
	    }
	    String aszPortalQuery = "SELECT d.name, t.tid " +
			" FROM " + aszDrupalDB + "term_data d, " + aszDrupalDB + "term_node t " +
			" WHERE d.vid=" + vidPortalName + " AND d.tid=t.tid AND t.nid IN (" + aszNIDList + ") ";

		if(null == pConn) return aszPortal;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return aszPortal;
		}
		iRetCode = pConn.RunQuery(aszPortalQuery);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return aszPortal;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
	        iRetCode=0;
	        aszPortal = pConn.getDBString("name");
	        if(aszPortal.length() > 0){
		        return aszPortal;
	        }
		}
    	return aszPortal;

	}

	/**
	* loadChildOppsDB for an organization
	*/
	public int loadChildOppsDB(ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0;
		int i=0, iLengthChildOpps=0;
		int[] a_iChildNids = new int[1000];
		String aszSQL2 = " "; 
		MethodInit("loadChildOppsDB");
		if(null == pConn) return -1;
    	if(null == aOrgInfoObj) return -2;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}

		int[] a_iOrgNids = aOrgInfoObj.getORGFavoritedORGNIDsArray();
		int iLengthOrgNids = a_iOrgNids.length;
		String aszOrgNids = "";
		if(aHeadObj.getPortalOrgAffilTID() > 0){
			// 1st, do Removed
			a_iOrgNids = aHeadObj.getRemoveFavORGNIDsArray();
			iLengthOrgNids = a_iOrgNids.length;
			if(iLengthOrgNids<1){
				a_iOrgNids = new int[1];
				a_iOrgNids[0] = aOrgInfoObj.getORGNID();
				aHeadObj.setRemoveFavORGNIDsArray(a_iOrgNids);
				iLengthOrgNids=1;
			}
			aszOrgNids = "";
	    	for(i=0; i < iLengthOrgNids; i++ ){
	    		if(a_iOrgNids[i]==0){
	    			if(aszOrgNids.endsWith(",")){
	    				aszOrgNids=aszOrgNids.substring(0, aszOrgNids.length()-1);
	    			}
	    			break;
	    		}else if( i >= iLengthOrgNids-1 ){
	    			aszOrgNids+=a_iOrgNids[i];
	    			break;
	    		}else{
	    			aszOrgNids+=a_iOrgNids[i]+",";
	    		}
	    	}
	    	i=0;
	    	if(aszOrgNids.length() > 0){
	    		aszSQL2 = " SELECT field_volorg_opp_reference_nid opp_nid " +
						" FROM " + aszDrupalDB + "content_field_volorg_opp_reference ref," + aszDrupalDB + "node opp "+
						" WHERE ref.nid IN (" + aszOrgNids + ") AND ref.field_volorg_opp_reference_nid=opp.nid ";//AND opp.status=1 ";
					
				iRetCode = pConn.RunQuery(aszSQL2);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode=-1;
				while(pConn.getNextResult()){
			        iRetCode=0;
			        int iOppNID = pConn.getDBInt("opp_nid");
			        if(iOppNID > 0){
				        a_iChildNids[i]=iOppNID;
				        i++;
			        }
				}
		    	i=0;
			
				for(i=0; i < a_iChildNids.length; i++ ){
					if( a_iChildNids[i] < 1 ){
						break;
					}else{
						iLengthChildOpps++;
					}
				}
		    	i=0;
				int[] iChildOppNids = new int[iLengthChildOpps];
				for(i=0; i < iLengthChildOpps; i++){
					iChildOppNids[i] = a_iChildNids[i];
				}
		    	i=0;
				// just load the opp nids to delete in the case of natlassoc
				aOrgInfoObj.setORGPrevFavoritedOPPNIDsArray(iChildOppNids);
	    	}
	    	
	    	
			// then, do Added
			a_iOrgNids = aHeadObj.getAddFavORGNIDsArray();
			iLengthOrgNids = a_iOrgNids.length;
			if(iLengthOrgNids<1){
				a_iOrgNids = new int[1];
				a_iOrgNids[0] = aOrgInfoObj.getORGNID();
				aHeadObj.setAddFavORGNIDsArray(a_iOrgNids);
				iLengthOrgNids=1;
			}
			aszOrgNids = "";
	    	for(i=0; i < iLengthOrgNids; i++ ){
	    		if(a_iOrgNids[i]==0){
	    			if(aszOrgNids.endsWith(",")){
	    				aszOrgNids=aszOrgNids.substring(0, aszOrgNids.length()-1);
	    			}
	    			break;
	    		}else if( i >= iLengthOrgNids-1 ){
	    			aszOrgNids+=a_iOrgNids[i];
	    			break;
	    		}else{
	    			aszOrgNids+=a_iOrgNids[i]+",";
	    		}
	    	}
	    	i=0;
	    	if(aszOrgNids.length() > 0){
	    		aszSQL2 = " SELECT field_volorg_opp_reference_nid opp_nid " +
						" FROM " + aszDrupalDB + "content_field_volorg_opp_reference ref," + aszDrupalDB + "node opp "+
						" WHERE ref.nid IN (" + aszOrgNids + ") AND ref.field_volorg_opp_reference_nid=opp.nid ";//AND opp.status=1 ";
					
				iRetCode = pConn.RunQuery(aszSQL2);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode=-1;
				while(pConn.getNextResult()){
			        iRetCode=0;
			        int iOppNID = pConn.getDBInt("opp_nid");
			        if(iOppNID > 0){
				        a_iChildNids[i]=iOppNID;
				        i++;
			        }
				}
		    	i=0;
			
				for(i=0; i < a_iChildNids.length; i++ ){
					if( a_iChildNids[i] < 1 ){
						break;
					}else{
						iLengthChildOpps++;
					}
				}
		    	i=0;
				int[] iChildOppNids = new int[iLengthChildOpps];
				for(i=0; i < iLengthChildOpps; i++){
					iChildOppNids[i] = a_iChildNids[i];
				}
				// just load the opp nids to add in the case of natlassoc
				aOrgInfoObj.setORGFavoritedOPPNIDsArray(iChildOppNids);
	    	}
		}else{

			a_iOrgNids = aOrgInfoObj.getORGFavoritedORGNIDsArray();
			iLengthOrgNids = a_iOrgNids.length;
			if(iLengthOrgNids<1){
				a_iOrgNids = new int[1];
				a_iOrgNids[0] = aOrgInfoObj.getORGNID();
				aOrgInfoObj.setORGFavoritedORGNIDsArray(a_iOrgNids);
				iLengthOrgNids=1;
			}
			aszOrgNids = "";
	    	for(i=0; i < iLengthOrgNids; i++ ){
	    		if( i >= iLengthOrgNids-1 ){
	    			aszOrgNids+=a_iOrgNids[i];
	    			break;
	    		}else{
	    			aszOrgNids+=a_iOrgNids[i]+",";
	    		}
	    	}
	    	i=0;
	    	if(aszOrgNids.length() > 0){
	    		aszSQL2 = " SELECT field_volorg_opp_reference_nid opp_nid " +
						" FROM " + aszDrupalDB + "content_field_volorg_opp_reference ref," + aszDrupalDB + "node opp "+
						" WHERE ref.nid IN (" + aszOrgNids + ") AND ref.field_volorg_opp_reference_nid=opp.nid ";//AND opp.status=1 ";
					
				iRetCode = pConn.RunQuery(aszSQL2);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode=-1;
				while(pConn.getNextResult()){
			        iRetCode=0;
			        int iOppNID = pConn.getDBInt("opp_nid");
			        if(iOppNID > 0){
				        a_iChildNids[i]=iOppNID;
				        i++;
			        }
				}
		    	i=0;
			
				for(i=0; i < a_iChildNids.length; i++ ){
					if( a_iChildNids[i] < 1 ){
						break;
					}else{
						iLengthChildOpps++;
					}
				}
		    	i=0;
				int[] iChildOppNids = new int[iLengthChildOpps];
				for(i=0; i < iLengthChildOpps; i++){
					iChildOppNids[i] = a_iChildNids[i];
				}
		    	i=0;
				aOrgInfoObj.setORGChildOPPNIDsArray(iChildOppNids);
				
	    	}
		}
		return iRetCode;
    }
    // end-of method loadChildOppsDB()





	/**
	* loadFavoriteOppsDB for an organization
	*/
	public int loadFavoriteOppsDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, boolean feeds ){
		int iRetCode=0;
		int i=0, iLengthFavOpps=0;
		int[] iFavOppNidsLoaded = new int[1000];
		String mainDB=aszDrupalDB;
		if( feeds==true ){
//			mainDB=aszFeedsDB;
		}


		String aszSQL2 = " SELECT content_id, fid, fl.uid " +
				" FROM " + mainDB + "flag_content fl, " + mainDB + "node n " +
				" WHERE fl.uid=" + aHeadObj.getORGUID() + " " +
					" AND fid=1 AND content_type LIKE 'node' AND n.type LIKE 'volunteer_opportunity' AND n.nid=fl.content_id " +
					" AND n.uid!=" + aHeadObj.getORGUID() + " " + // don't include the org's own opps/orgs in this list, b/c those will always be favorited
				" ORDER BY content_id "; 
		MethodInit("loadFavoriteOppsDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
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
		iRetCode=-1;
		// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
		while(pConn.getNextResult()){
            iRetCode=0;
            iFavOppNidsLoaded[i]=pConn.getDBInt("content_id");
            i++;
		}
    	i=0;

    	// make additions to favorites
    	for(i=0; i < iFavOppNidsLoaded.length; i++ ){
    		if( iFavOppNidsLoaded[i] < 1 ){
    			break;
    		}else{
    			iLengthFavOpps++;
    		}
    	}
    	i=0;
    	int[] iFavOppNids = new int[iLengthFavOpps];
    	for(i=0; i < iLengthFavOpps; i++){
    		iFavOppNids[i] = iFavOppNidsLoaded[i];
    	}
    	i=0;

		if( feeds==true ){
			aHeadObj.setORGFavoritedOPPNIDsFromFeedArray(iFavOppNids);
		}else{
			aHeadObj.setORGFavoritedOPPNIDsArray(iFavOppNids);
		}
		
		return iRetCode;
    }
    // end-of method loadFavoriteOppsDB()


	/**
	* loadFavoriteOrgsDB for an organization
	*/
	public int loadFavoriteOrgsDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		int i=0, iLengthFavOrgs=0;
		int[] iFavOrgNidsLoaded = new int[1000];
		String aszSQL2 = "";
		MethodInit("loadFavoriteOrgsDB");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -2;

		if(iType==OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
			int iOrgAffilTID=0;
			aszSQL2 = " SELECT tn.tid " +
					" FROM " + aszDrupalDB + "term_data td, " + aszDrupalDB + "term_node tn " +
					" WHERE tn.nid=" + aHeadObj.getNatlAssociationNID() + " " +
						" AND tn.tid=td.tid AND td.vid=" + vidOrgAffil ; 
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
			iRetCode=-1;
			while(pConn.getNextResult()){
		        iRetCode=0;
		        iOrgAffilTID=pConn.getDBInt("tid");
			}
			aszSQL2 = " SELECT org.nid " +
					" FROM " + aszDrupalDB + "content_type_organization org," +
							" " + aszDrupalDB + "term_node tn " +
					" WHERE org.nid=tn.nid AND tn.tid=" + iOrgAffilTID; 
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
			iRetCode=-1;
			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
			while(pConn.getNextResult()){
		        iRetCode=0;
		        iFavOrgNidsLoaded[i]=pConn.getDBInt("nid");
		        i++;
			}
		}else{
			aszSQL2 = " SELECT content_id, fid, fl.uid " +
					" FROM " + aszDrupalDB + "flag_content fl, " + aszDrupalDB + "node n " +
					" WHERE fl.uid=" + aHeadObj.getORGUID() + " " +
						" AND fid=" + iFlagFavorite + " AND content_type LIKE 'node' AND n.type LIKE 'organization' AND n.nid=fl.content_id " +
						" AND n.uid!=" + aHeadObj.getORGUID() + " " + // don't include the org's own opps/orgs in this list, b/c those will always be favorited
					" ORDER BY content_id "; 
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
			iRetCode=-1;
			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
			while(pConn.getNextResult()){
		        iRetCode=0;
		        iFavOrgNidsLoaded[i]=pConn.getDBInt("content_id");
		        i++;
			}
		}
    	i=0;

    	// make additions to favorites
    	for(i=0; i < iFavOrgNidsLoaded.length; i++ ){
    		if( iFavOrgNidsLoaded[i] < 1 ){
    			break;
    		}else{
    			iLengthFavOrgs++;
    		}
    	}
    	i=0;
    	int[] iFavOrgNids = new int[iLengthFavOrgs];
    	for(i=0; i < iLengthFavOrgs; i++){
    		iFavOrgNids[i] = iFavOrgNidsLoaded[i];
    	}
    	i=0;
		aHeadObj.setORGFavoritedORGNIDsArray(iFavOrgNids);
		
		return iRetCode;
    }
    // end-of method loadFavoriteOrgsDB()

	/**
	* favoriteOppsDB for an organization
	*/
	public int favoriteOppsDB(ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, boolean feeds, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0, i=0, iAddIndex=0, iRemoveIndex=0, iAddTemp=0, iRemoveTemp=0;
		String aszSQL2 = "";
		int[] iAddFavOppNids = new int[1000];
		int[] iRemoveFavOppNids = new int[1000];
		int[] iNewFavOppNids;
		int[] iPrevFavOppNids;
		if(feeds==false){
			iNewFavOppNids = aOrgInfoObj.getORGFavoritedOPPNIDsArray();
			iPrevFavOppNids = aOrgInfoObj.getORGPrevFavoritedOPPNIDsArray();
		}else{
			iNewFavOppNids = aOrgInfoObj.getORGFavoritedOPPNIDsFromFeedArray();
			iPrevFavOppNids = aOrgInfoObj.getORGPrevFavoritedOPPNIDsFromFeedArray();
		}
		String mainDB=aszDrupalDB;
		if( feeds==true ){
//			mainDB=aszFeedsDB;
		}
		for(i=0; i < iNewFavOppNids.length; i++){
    		iAddTemp=0;
    		int value = iNewFavOppNids[i];
    		for(int j=0; j < iPrevFavOppNids.length; j++){
    			if(iPrevFavOppNids[j]==value){
    				iAddTemp=-1;
    			}
    		}
    		if(iAddTemp == 0){// exists in New, but not in Previous; add it
    			iAddFavOppNids[iAddIndex]=value;
    			iAddIndex++;
    		}
    	}
    	i=0;
    	for(i=0; i < iPrevFavOppNids.length; i++){
    		iRemoveTemp=0;
    		int value = iPrevFavOppNids[i];
    		for(int j=0; j < iNewFavOppNids.length; j++){
    			if(iNewFavOppNids[j]==value){
    				iRemoveTemp=-1;
    			}
    		}
    		if(iRemoveTemp == 0){// exists in Previous, but not in New; remove it
    			iRemoveFavOppNids[iRemoveIndex]=value;
    			iRemoveIndex++;
    		}
    	}
    	i=0;
    	
    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;

    	if(iType==OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
    		iAddFavOppNids=aOrgInfoObj.getORGFavoritedOPPNIDsArray();
        	// make additions to favorites
        	for(i=0; i < iAddFavOppNids.length; i++ ){
        		if( iAddFavOppNids[i] < 1 ){
        			break;
        		}
        		int iVID=0;
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT vid " +
    				" FROM " + aszDrupalDB + "node  " +
    				" WHERE nid=" + iAddFavOppNids[i] + " ORDER BY vid "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    			}
    			iRetCode=-1;
    			while(pConn.getNextResult()){
    				iVID=pConn.getDBInt("vid");
    			}
        		if(iAddFavOppNids[i]>0){
    		    	aszSQL2 = " INSERT INTO " +  aszDrupalDB + "term_node(vid, nid, tid) " +
    				" VALUES(" + iVID + "," + iAddFavOppNids[i] + "," + aHeadObj.getPortalOrgAffilTID() + ") ";
    	    		
    	    		iRetCode=pConn.PrepQuery(aszSQL2);
    	    		if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
    	    		iRetCode = pConn.ExecutePrepQry();
    	    		if(iRetCode == 1062 ){ // then this is a duplicate; 
    	    			
    	    		}else if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
        		}
        	}
        	i=0;

        	/* do remove later, in a diff use case, b/c diff searches may not yield an "un-check" of something...
        	 */
        	// remove ones no longer in favorites
        	iRemoveFavOppNids=aOrgInfoObj.getORGPrevFavoritedOPPNIDsArray();
        	for(i=0; i < iRemoveFavOppNids.length; i++ ){
        		if( iRemoveFavOppNids[i] < 1 ){
        			break;
        		}
        		aszSQL2 = " DELETE FROM " +  aszDrupalDB + "term_node " +
        			" WHERE nid =" + iRemoveFavOppNids[i] + " " +
        			" AND tid=" + aHeadObj.getPortalOrgAffilTID() + " ";
        		iRetCode=pConn.RunUpdateQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			return -1;
        		}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        	} 	
    	}else{
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
        	// make additions to favorites
        	for(i=0; i < iAddFavOppNids.length; i++ ){
        		if( iAddFavOppNids[i] < 1 ){
        			break;
        		}
        		
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT count " +
    				" FROM " + mainDB + "flag_counts fl " +
    				" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + iAddFavOppNids[i] + " AND content_type LIKE 'node' "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode=-1;
    			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
    			while(pConn.getNextResult()){
    				iInitCount=pConn.getDBInt("count");
    	            //i++;
    			}
    	    	//i=0;

        		if(iAddFavOppNids[i]>0){
    	    		aszSQL2 = " INSERT INTO " +  mainDB + "flag_content(fid, content_type, content_id, uid, timestamp) " +
    	    				" VALUES(" + iFlagFavorite + ",'node'," + iAddFavOppNids[i] + "," + aOrgInfoObj.getORGUID() + ",UNIX_TIMESTAMP({fn now()})) ";
    	    		
    	    		iRetCode=pConn.PrepQuery(aszSQL2);
    	    		if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
    	    		iRetCode = pConn.ExecutePrepQry();
    	    		if(iRetCode == 1062 ){ // then this is a duplicate; 
    	    			
    	    		}else if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}else{
    	    			iChangeNumber++;// successfully added and not a duplicate
    	        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
    	            	iCurrentCount = iInitCount + iChangeNumber;
    	            	if(iCurrentCount<0) iCurrentCount=0;
    	            	if(iCurrentCount==0){
    	            	}else{
    	            		aszSQL2 = " INSERT INTO  " +  mainDB + "flag_counts (fid, content_type, content_id, count) " +
    			    				" VALUES (" + iFlagFavorite + ",'node'," + iAddFavOppNids[i] + ","+iCurrentCount+") " +
    			    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
    			    		iRetCode=pConn.RunUpdateQuery(aszSQL2);
    			    		if(0 != iRetCode){
    			    			pConn.copyErrObj(getErrObj());
    			    			ErrorsToLog();
    			    			return -1;
    			    		}
    	            	}
    	    		}
        		}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        		
        	}
        	i=0;

        	/* do remove later, in a diff use case, b/c diff searches may not yield an "un-check" of something...
        	 */
        	// remove ones no longer in favorites
        	for(i=0; i < iRemoveFavOppNids.length; i++ ){
        		if( iRemoveFavOppNids[i] < 1 ){
        			break;
        		}
        		
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT count " +
    				" FROM " + mainDB + "flag_counts fl " +
    				" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + iRemoveFavOppNids[i] + " AND content_type LIKE 'node' "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode=-1;
    			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
    			while(pConn.getNextResult()){
    				iInitCount=pConn.getDBInt("count");
//    	            i++;
    			}
    	    	//i=0;

        		
        		aszSQL2 = " DELETE FROM " +  mainDB + "flag_content " +
        				" WHERE content_id =" + iRemoveFavOppNids[i] + " " +
        				" AND uid=" + aOrgInfoObj.getORGUID() + " ";
        		iRetCode=pConn.RunUpdateQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			return -1;
        		}
        		// if successful, make the flag counts for that content_id reflect the change; in this case, decrease
        		// (grab the number of records changed by the previous statement)
        		//	*****  Grab the number of rows affected by the delete just executed
            	aszSQL2 = "SELECT ROW_COUNT() ";
        		iRetCode=pConn.getDBStmt();
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			//return -999;
        		}
        		iRetCode = pConn.RunQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        	        ErrorsToLog();
        			//return -1;
        		}
        		// Get tid From ResultSet
        		if(pConn.getNextResult()){
        			iChangeNumber = pConn.getDBInt("ROW_COUNT()");
        		}
        		// if successful, make the flag counts for that content_id reflect the change; in this case, decrease
            	iCurrentCount = iInitCount - iChangeNumber;
            	if(iCurrentCount<0) iCurrentCount=0;
            	if(iCurrentCount==0){
            		if(iInitCount == 1){
            			//delete from flag counts
                		aszSQL2 = " DELETE FROM " +  mainDB + "flag_counts " +
    		    				" WHERE fid=" + iFlagFavorite + " AND content_id=" + iRemoveFavOppNids[i] + " AND content_type LIKE 'node' "; 
    		    		iRetCode=pConn.RunUpdateQuery(aszSQL2);
    		    		if(0 != iRetCode){
    		    			pConn.copyErrObj(getErrObj());
    		    			ErrorsToLog();
    		    			//return -1;
    		    		}

            		}
            	}else{ // update count in flag count
                	// update the flag_counts for this content to include iCurrentCount as the count data
                	aszSQL2="UPDATE " + mainDB + "flag_counts SET count = " + iCurrentCount + 
            			" WHERE fid=" + iFlagFavorite + " AND content_id=" + iRemoveFavOppNids[i] + " AND content_type LIKE 'node' "; 
            		iRetCode=pConn.PrepQuery(aszSQL2);
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
            			ErrorsToLog();
            			//return -1;
            		}
            		iRetCode = pConn.ExecutePrepQry();
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
            			ErrorsToLog();
            			//return -1;
            		}
            	}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        	} 	
    	}
    	/* */
		
		return iRetCode;
    }
    // end-of method favoriteOppsDB()



	/**
	* favoriteOrgsDB for an organization
	*/
	public int favoriteOrgsDB(ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, int iType, AppCodeInfoDTO aHeadObj ){
		int iRetCode=0, i=0, iAddIndex=0, iRemoveIndex=0, iAddTemp=0, iRemoveTemp=0;
		String aszSQL2 = "";
		int[] iAddFavOrgNids = new int[1000];
		int[] iRemoveFavOrgNids = new int[1000];
		int[] iNewFavOrgNids = aOrgInfoObj.getORGFavoritedORGNIDsArray();
		int[] iPrevFavOrgNids = aOrgInfoObj.getORGPrevFavoritedORGNIDsArray();

    	for(i=0; i < iNewFavOrgNids.length; i++){
    		iAddTemp=0;
    		int value = iNewFavOrgNids[i];
    		for(int j=0; j < iPrevFavOrgNids.length; j++){
    			if(iPrevFavOrgNids[j]==value){
    				iAddTemp=-1;
    			}
    		}
    		if(iAddTemp == 0){// exists in New, but not in Previous; add it
    			iAddFavOrgNids[iAddIndex]=value;
    			iAddIndex++;
    		}
    	}
    	i=0;
    	for(i=0; i < iPrevFavOrgNids.length; i++){
    		iRemoveTemp=0;
    		int value = iPrevFavOrgNids[i];
    		for(int j=0; j < iNewFavOrgNids.length; j++){
    			if(iNewFavOrgNids[j]==value){
    				iRemoveTemp=-1;
    			}
    		}
    		if(iRemoveTemp == 0){// exists in Previous, but not in New; remove it
    			iRemoveFavOrgNids[iRemoveIndex]=value;
    			iRemoveIndex++;
    		}
    	}
    	i=0;
    	aHeadObj.setAddFavORGNIDsArray(iAddFavOrgNids);
    	aHeadObj.setRemoveFavORGNIDsArray(iRemoveFavOrgNids);
    	
    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;
    	if(iType==OrganizationInfoDTO.LOADBY_MANAGE_NATL_ASSOC){
        	// make additions to favorites
        	for(i=0; i < iAddFavOrgNids.length; i++ ){
        		if( iAddFavOrgNids[i] < 1 ){
        			break;
        		}
        		int iVID=0;
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT vid " +
    				" FROM " + aszDrupalDB + "node  " +
    				" WHERE nid=" + iAddFavOrgNids[i] + " ORDER BY vid "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    			}
    			iRetCode=-1;
    			while(pConn.getNextResult()){
    				iVID=pConn.getDBInt("vid");
    			}
    			if(iAddFavOrgNids[i]>0){
    		    	aszSQL2 = " INSERT INTO " +  aszDrupalDB + "term_node(vid, nid, tid) " +
    	    				" VALUES(" + iVID + "," + iAddFavOrgNids[i] + "," + aHeadObj.getPortalOrgAffilTID() + ") ";
    	    		iRetCode=pConn.PrepQuery(aszSQL2);
    	    		if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
    	    		iRetCode = pConn.ExecutePrepQry();
    	    		if(iRetCode == 1062 ){ // then this is a duplicate; 
    	    			
    	    		}else if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
    			}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        	}
        	i=0;
        	
        	/* do remove later, in a diff use case, b/c diff searches may not yield an "un-check" of something...
        	 */
        	// remove ones no longer in favorites
        	for(i=0; i < iRemoveFavOrgNids.length; i++ ){
        		if( iRemoveFavOrgNids[i] < 1 ){
        			break;
        		}
        		aszSQL2 = " DELETE FROM " +  aszDrupalDB + "term_node " +
        				" WHERE nid =" + iRemoveFavOrgNids[i] + " " +
        				" AND tid=" + aHeadObj.getPortalOrgAffilTID() + " ";
        		iRetCode=pConn.RunUpdateQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			return -1;
        		}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        	} 	
    	}else{
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
        	// make additions to favorites
        	for(i=0; i < iAddFavOrgNids.length; i++ ){
        		if( iAddFavOrgNids[i] < 1 ){
        			break;
        		}
        		
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT count " +
    				" FROM " + aszDrupalDB + "flag_counts  " +
    				" WHERE fid=" + iFlagFavorite + " AND content_id=" + iAddFavOrgNids[i] + " AND content_type LIKE 'node' "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode=-1;
    			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
    			while(pConn.getNextResult()){
    				iInitCount=pConn.getDBInt("count");
    	            //i++;
    			}
    	    	//i=0;

    			if(iAddFavOrgNids[i]>0){
    		    	aszSQL2 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
    	    				" VALUES(" + iFlagFavorite + ",'node'," + iAddFavOrgNids[i] + "," + aOrgInfoObj.getORGUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";
    	    		
    	    		iRetCode=pConn.PrepQuery(aszSQL2);
    	    		if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}
    	    		iRetCode = pConn.ExecutePrepQry();
    	    		if(iRetCode == 1062 ){ // then this is a duplicate; 
    	    			
    	    		}else if(0 != iRetCode){
    	    			pConn.copyErrObj(getErrObj());
    	    			ErrorsToLog();
    	    			return -1;
    	    		}else{
    	    			iChangeNumber++;// successfully added and not a duplicate
    	        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
    	            	iCurrentCount = iInitCount + iChangeNumber;
    	            	if(iCurrentCount<0) iCurrentCount=0;
    	            	if(iCurrentCount==0){
    	            	}else{
    	            		aszSQL2 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
    			    				" VALUES (" + iFlagFavorite + ",'node'," + iAddFavOrgNids[i] + ","+iCurrentCount+") " +
    			    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
    			    		iRetCode=pConn.RunUpdateQuery(aszSQL2);
    			    		if(0 != iRetCode){
    			    			pConn.copyErrObj(getErrObj());
    			    			ErrorsToLog();
    			    			//return -1;
    			    		}
    	            		
    	            	}
    	    		}
    			}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration

        	}
        	i=0;
        	
        	/* do remove later, in a diff use case, b/c diff searches may not yield an "un-check" of something...
        	 */
        	// remove ones no longer in favorites
        	for(i=0; i < iRemoveFavOrgNids.length; i++ ){
        		if( iRemoveFavOrgNids[i] < 1 ){
        			break;
        		}
        		// get number of counts of the favorites flag for this content id
        		aszSQL2 = " SELECT count " +
    				" FROM " + aszDrupalDB + "flag_counts " +
    				" WHERE fid=" + iFlagFavorite + " AND content_id=" + iRemoveFavOrgNids[i] + " AND content_type LIKE 'node' "; 
    			iRetCode=pConn.getDBStmt();
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode = pConn.RunQuery(aszSQL2);
    			if(0 != iRetCode){
    				pConn.copyErrObj(getErrObj());
    				ErrorsToLog();
    				//return -999;
    			}
    			iRetCode=-1;
    			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
    			while(pConn.getNextResult()){
    				iInitCount=pConn.getDBInt("count");
    	            //i++;
    			}
    	    	//i=0;
        		aszSQL2 = " DELETE FROM " +  aszDrupalDB + "flag_content " +
        				" WHERE content_id =" + iRemoveFavOrgNids[i] + " " +
        				" AND uid=" + aOrgInfoObj.getORGUID() + " ";
        		iRetCode=pConn.RunUpdateQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			return -1;
        		}
        		// if successful, make the flag counts for that content_id reflect the change; in this case, decrease
        		// (grab the number of records changed by the previous statement)
        		//	*****  Grab the number of rows affected by the delete just executed
            	aszSQL2 = "SELECT ROW_COUNT() ";
        		iRetCode=pConn.getDBStmt();
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			//return -999;
        		}
        		iRetCode = pConn.RunQuery(aszSQL2);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        	        ErrorsToLog();
        			//return -1;
        		}
        		// Get tid From ResultSet
        		if(pConn.getNextResult()){
        			iChangeNumber = pConn.getDBInt("ROW_COUNT()");
        		}
        		// if successful, make the flag counts for that content_id reflect the change; in this case, decrease
            	iCurrentCount = iInitCount - iChangeNumber;
            	if(iCurrentCount<0) iCurrentCount=0;
            	if(iCurrentCount==0){
            		if(iInitCount == 1){
            			//delete from flag counts
                		aszSQL2 = " DELETE FROM " +  aszDrupalDB + "flag_counts " +
    		    				" WHERE fid=" + iFlagFavorite + " AND content_id=" + iRemoveFavOrgNids[i] + " AND content_type LIKE 'node' "; 
    		    		iRetCode=pConn.RunUpdateQuery(aszSQL2);
    		    		if(0 != iRetCode){
    		    			pConn.copyErrObj(getErrObj());
    		    			ErrorsToLog();
    		    			//return -1;
    		    		}

            		}
            	}else{ // update count in flag count
                	// update the flag_counts for this content to include iCurrentCount as the count data
                	aszSQL2="UPDATE " + aszDrupalDB + "flag_counts SET count = " + iCurrentCount + 
            			" WHERE fid=" + iFlagFavorite + " AND content_id=" + iRemoveFavOrgNids[i] + " AND content_type LIKE 'node' "; 
            		iRetCode=pConn.PrepQuery(aszSQL2);
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
            			ErrorsToLog();
            			//return -1;
            		}
            		iRetCode = pConn.ExecutePrepQry();
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
            			ErrorsToLog();
            			//return -1;
            		}
            	}
            	iChangeNumber=0;iInitCount=0;iCurrentCount=0;// reset for next iteration
        	} 	
    	}
    	/* */
		
		return iRetCode;
    }
    // end-of method favoriteOrgsDB()



	/**
	* setNatlAssocOrgsAndOppsDB for an organization
	*/
	public int setNatlAssocOrgsAndOppsDB( ABREDBConnection pConn, OrganizationInfoDTO aOrgInfoObj, int iOrgAffilTID, AppCodeInfoDTO aHeadObj){
		int iRetCode=0;
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		// first, delete all org links to orgaffil from orgs & opps
    	int[] iRemoveOrgNids = aHeadObj.getRemoveFavORGNIDsArray();
    	for(int i=0; i<iRemoveOrgNids.length; i++){
    		//iterate through all the remove org nids and get lists of each orgs' opps
   	    	int iOrgNID = iRemoveOrgNids[i];		
			String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
					"WHERE nid = " + iOrgNID + " AND tid =" + iOrgAffilTID;
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
    	}
    	int[] iRemoveOppNids = aHeadObj.getRemoveFavOPPNIDsArray();
    	for(int i=0; i<iRemoveOppNids.length; i++){
    		//iterate through all the remove org nids and get lists of each orgs' opps
   	    	int iOppNID = iRemoveOppNids[i];		
			String aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
					"WHERE nid = " + iOppNID + " AND tid =" + iOrgAffilTID;
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
    	}
    	
    	// then proceed to ADD for the orgs & opps that have been ADDED
    	int[] iAddOrgNids = aHeadObj.getAddFavORGNIDsArray();
    	for(int i=0; i<iAddOrgNids.length; i++){
    		//iterate through all the remove org nids and get lists of each orgs' opps
   	    	int iOrgNID = iAddOrgNids[i];
   	    	int iOrgVID = 0;
   	    	// Grab the VID from um_node for the given NID
			String aszSQL101 = "SELECT vid FROM " + aszDrupalDB + "node " +
					"WHERE nid = " + iOrgNID;
			iRetCode=pConn.PrepQuery(aszSQL101);
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
			if(pConn.getNextResult()){
				iOrgVID = pConn.getDBInt("vid");
			}
			if(iOrgVID>0 && iOrgNID>0 && iOrgAffilTID > 0){
				aszSQL101 = "INSERT INTO " + aszDrupalDB + "term_node (vid, nid, tid) " +
						"VALUES(" + iOrgVID + ", " + iOrgNID + ", " + iOrgAffilTID + ")";
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
			}
    	}

    	int[] iAddOppNids = aHeadObj.getAddFavOPPNIDsArray();
    	for(int i=0; i<iAddOppNids.length; i++){
    		//iterate through all the remove org nids and get lists of each orgs' opps
   	    	int iOppNID = iAddOppNids[i];
   	    	int iOppVID = 0;
   	    	// Grab the VID from um_node for the given NID
			String aszSQL101 = "SELECT vid FROM " + aszDrupalDB + "node " +
					"WHERE nid = " + iOppNID;
			iRetCode=pConn.PrepQuery(aszSQL101);
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
			if(pConn.getNextResult()){
				iOppVID = pConn.getDBInt("vid");
			}
			if(iOppVID>0 && iOppNID>0 && iOrgAffilTID > 0){
				aszSQL101 = "INSERT INTO " + aszDrupalDB + "term_node (vid, nid, tid) " +
						"VALUES(" + iOppVID + ", " + iOppNID + ", " + iOrgAffilTID + ")";
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
			}
    	}

    	return iRetCode;
    }
    // end-of method setNatlAssocOrgsAndOppsDB()

	
	
    /**
	* select a list of administrators for an organization
	*/
	public int setOrgAdminDBListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0, iCount=0, iContactUID=0;
		int[] a_iOrgAdminUIDsTemp = new int[255];
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid " +
			" FROM " + aszDrupalDB + "users u," + aszDrupalDB + "content_field_volorg_owner contact " +
			" WHERE contact.field_volorg_owner_uid =u.uid AND contact.nid=" + aOrgInfoObj.getORGNID() ; 
		MethodInit("setOrgAdminDBListArray");
		if(null == pConn) return -1;
    	if(null == aOrgInfoObj) return -2;
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
             a_iOrgAdminUIDsTemp[iCount]=pConn.getDBInt("contact_uid");
            iCount++;
        }
		int[] a_iOrgAdminUIDs = new int[iCount];
		for(iCount=0; iCount < a_iOrgAdminUIDs.length; iCount++){
			a_iOrgAdminUIDs[iCount]=a_iOrgAdminUIDsTemp[iCount];
		}
		aOrgInfoObj.setORGAdminUIDsArray(a_iOrgAdminUIDs);
		
		return iRetCode;
    }
    // end-of method setOrgAdminDBListArray()



	/**
	* select a list of administrators for an organization
	*/
	public int getOrgAdminDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0, iNoResults1=-1, iNoResults2=-1;
		String aszSQL2=null, aszTemp=null, aszEmail=null ; // may take out join with usermail, b/c this now is ORG ADMINS, not org contacts or OPP CONTACTS
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail " +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile uprofile, " + 
			 aszDrupalDB + "users u," + aszDrupalDB + "content_field_volorg_owner contact " +
			" WHERE contact.nid=" + aSrchParmObj.getNID() + " AND contact.field_volorg_owner_uid =n.uid " +
			" AND n.type='uprofile' AND n.nid=uprofile.nid  AND contact.field_volorg_owner_uid =u.uid" ; // placeholder for conditionals set in BLO layer
		MethodInit("getOrgAdminDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults1=-1;
		while(pConn.getNextResult()){
			iNoResults1=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("contact_uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, u.mail " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "content_field_volorg_owner contact " +
			" WHERE contact.nid=" + aSrchParmObj.getNID() + " AND contact.field_volorg_owner_uid =u.uid  "; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults2=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iNoResults2=0;
	        iContactUID = pConn.getDBInt("contact_uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		if(iNoResults1==0 || iNoResults2==0){
			iRetCode=0;
		}
		
		return iRetCode;
    }
    // end-of method getOrgAdminDBList()

    /**	
	* insert a row into Organization to show ownership of given UID to this VID/NID
	*/
	public int insertAddOrgAdminIntDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		String aszSQLdrupalTemp = "";
		// "auto-"increment the nid and vid in urbmi5_drupal.um_sequences table - then grab last Id...
		// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1;
		String Qry1=null;
		MethodInit("insertAddOrgAdminIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT * FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
			" AND field_volorg_owner_uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			aHeadObj.appendErrorMsg("This user already has administrative privileges on this Organization");
			return 0;
		} 
		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT delta FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		int iDelta=-1;
		int iDeltaMax=-1;
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			iDelta = pConn.getDBInt("delta");
			if(iDeltaMax < iDelta){
				iDeltaMax = iDelta;
			}
		} 
		iDeltaMax++; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		/*
		 * add to um_content_field_volorg_owner
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_volorg_owner(vid, nid, delta, field_volorg_owner_uid) " +
				"VALUES(" + aHeadObj.getORGVID() + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + aIndivObj.getUserUID() + " ) ";
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
		
		/*
		 * add to um_usermail with org_nid association
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
				"VALUES(" + aIndivObj.getUserUID()+ ",'voleng_org_nid','" + aHeadObj.getORGNID() + "',0,0," + aHeadObj.getORGNID() + ",0) ";
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
		
		// get all the child opps affiliated with this org, and insert a row for this user and each of those opps into the usermail table
		// iterate through aHeadObj.getORGChildOPPNIDsArray
		int iOppNID=0;
		int[] a_iOppNIDs = aHeadObj.getORGChildOPPNIDsArray();
		for (int i=0; i<a_iOppNIDs.length;i++){
			iOppNID = a_iOppNIDs[i];
			if(iOppNID>0){
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
						"VALUES(" + aIndivObj.getUserUID()+ ",'voleng_opp_nid','" + iOppNID + "',0,0," + aHeadObj.getORGNID() + "," + iOppNID + ") ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					break;
				}
				iRetCode = pConn.ExecutePrepQry();
			}
		}
		
		return 0;
	}
	// end-of method insertAddOrgAdminIntDB()


    /**
	* updateORGAdminsDB an Opportunity Volunteer Contact record (whether they receive emails or not in the voleng system)
	*/
	public int updateORGAdminsDB(ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		int iTempNodeUID=0, iNewOrgNodeUID=0, iOrgNodeUID=0, iTempNID=0, iOldDelta=0;
		String Qry1, aszSQL101;
		MethodInit("updateORGAdminsDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null organization object");
			return -1;
		}
		if(null == aListIdsAndEmailNotifyFlag){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		//uid,component, mail_id are the primary key
        for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
            int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
            
            if(iUID>0){
	            if(iFlag==iRemovedAdmin){
	        		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
	        		aszSQL101="SELECT uid FROM " + aszDrupalDB + "node WHERE nid=" + aHeadObj.getORGNID();
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
	        		iRetCode=-1;
	        		if(pConn.getNextResult()){
	        			iTempNodeUID = pConn.getDBInt("uid");
	        		}
	        		
	        		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
	        		aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
	        				" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=0 ";
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
	        		iRetCode=-1;
	        		if(pConn.getNextResult()){
	        			iOrgNodeUID = pConn.getDBInt("uid");
	        		}
	        		if(iOrgNodeUID==0)iOrgNodeUID=iTempNodeUID;
	        		if(iTempNodeUID==iUID || iOrgNodeUID==iUID){
	        			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
	        					" WHERE nid=" + aHeadObj.getORGNID() ;
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
	        			iRetCode=-1;
	        			if(pConn.getNextResult()){
	        				iOldDelta = pConn.getDBInt("max_delta");
	        			}
	        			if(iOldDelta==0){// this is the only record; they can't delte it ***********
	        				return -2;
	        			}
	        			aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
	        					" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
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
	        			iRetCode=-1;
	        			if(pConn.getNextResult()){
	        				iNewOrgNodeUID = pConn.getDBInt("uid");
	        			}
	        				
	        			// is Legacy primary contact out-of-sync?
	        			if(iTempNodeUID==iOrgNodeUID){
	        				aszSQL101="UPDATE " + aszDrupalDB + "node " +
	        						" SET uid=" + iNewOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
	        				iRetCode=pConn.RunUpdate(aszSQL101);
	        				if(0 != iRetCode){
	        					pConn.copyErrObj(getErrObj());
	        					ErrorsToLog();
	        					return -1;
	        				}
	        			}else if(iOrgNodeUID!=iUID){ // is out of sync
	        				// should we choose NodeUID or OrgUID to be primary???
	        				aszSQL101="UPDATE " + aszDrupalDB + "node " +
	        						" SET uid=" + iOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
	        				iRetCode=pConn.RunUpdate(aszSQL101);
	        				if(0 != iRetCode){
	        					pConn.copyErrObj(getErrObj());
	        					ErrorsToLog();
	        					return -1;
	        				}
	        			}
	        		}
	        		// make sure there is not a gap in the delta...
	        		
	        		
	        		//delete from um_content_field_volorg_owner
	        		aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
	        				" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iUID; 
	        		iRetCode=pConn.RunUpdate(aszSQL101);
	        		if(0 != iRetCode){
	        			pConn.copyErrObj(getErrObj());
	        			ErrorsToLog();
	        			return -1;
	        		}
    				// update the um_node record and the delta 0 volorg_owner record to this uid
    				aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
    						" SET delta=0 WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iNewOrgNodeUID; 
    				iRetCode=pConn.RunUpdate(aszSQL101);
    				if(0 != iRetCode){
    					pConn.copyErrObj(getErrObj());
    					ErrorsToLog();
    					return -1;
    				}
	            	
	            }else if(iFlag==iAddedAdmin){
            		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
            		// grab the delta
            		Qry1 = "SELECT * FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
            			" AND field_volorg_owner_uid=" + iUID;
            		iRetCode = pConn.RunQry(Qry1);
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
                        ErrorsToLog();
            			return -1;
            		}
            		// Get Next Item From ResultSet
            		while(pConn.getNextResult()){
            			aHeadObj.appendErrorMsg("This user already has administrative privileges on this Organization");
            			return 0;
            		} 
            		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
            		
            		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
            		// grab the delta
            		Qry1 = "SELECT delta FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID();
            		iRetCode = pConn.RunQry(Qry1);
            		if(0 != iRetCode){
            			pConn.copyErrObj(getErrObj());
                        ErrorsToLog();
            			return -1;
            		}
            		int iDelta=-1;
            		int iDeltaMax=-1;
            		// Get Next Item From ResultSet
            		while(pConn.getNextResult()){
            			iDelta = pConn.getDBInt("delta");
            			if(iDeltaMax < iDelta){
            				iDeltaMax = iDelta;
            			}
            		} 
            		iDeltaMax++; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
            		
            		/*
            		 * add to um_content_field_volorg_owner
            		 */
            		aszSQL101="INSERT INTO " + aszDrupalDB + "content_field_volorg_owner(vid, nid, delta, field_volorg_owner_uid) " +
            				"VALUES(" + aHeadObj.getORGVID() + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + iUID + " ) ";
            		iRetCode=pConn.PrepQuery(aszSQL101);
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
            		
            		/*
            		 * add to um_usermail with org_nid association
            		 */
            		aszSQL101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
            				"VALUES(" + iUID+ ",'voleng_org_nid','" + aHeadObj.getORGNID() + "',0,0," + aHeadObj.getORGNID() + ",0) ";
            		iRetCode=pConn.PrepQuery(aszSQL101);
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
            		
            		// get all the child opps affiliated with this org, and insert a row for this user and each of those opps into the usermail table
            		// iterate through aHeadObj.getORGChildOPPNIDsArray
            		int iOppNID=0;
            		int[] a_iOppNIDs = aHeadObj.getORGChildOPPNIDsArray();
            		for (int j=0; j<a_iOppNIDs.length;j++){
            			iOppNID = a_iOppNIDs[j];
            			if(iOppNID>0){
            				aszSQL101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
            						"VALUES(" + iUID+ ",'voleng_opp_nid','" + iOppNID + "',0,0," + aHeadObj.getORGNID() + "," + iOppNID + ") ";
            				iRetCode=pConn.PrepQuery(aszSQL101);
            				if(0 != iRetCode){
            					break;
            				}
            				iRetCode = pConn.ExecutePrepQry();
            			}
            		}
                }
            }
        }
		return iRetCode; 
	}
	// end-of method updateORGAdminsDB()

    /**
	* delete an organizational admin record - 
	*/
	public int deleteOrgAdminFromDB(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		int iTempNodeUID=0, iNewOrgNodeUID=0, iOrgNodeUID=0, iTempNID=0, iOldDelta=0, iNewDelta=0;
		String aszSQL101;
		MethodInit("deleteOrgAdminFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() ;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQL101);
		if(pConn.getNextResult()){
			iOldDelta = pConn.getDBInt("max_delta");
		}
		if(iOldDelta==0){// this is the only record; they can't delte it ***********
			return -2;
		}
		
		// delete from um_usermail for voleng mailing notifications
		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
				" WHERE org_nid=" + aHeadObj.getORGNID() + " AND uid=" + aIndivObj.getUserUID() ; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
		aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=0 ";
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
		iRetCode=-1;
		if(pConn.getNextResult()){
			iOrgNodeUID = pConn.getDBInt("uid");
		}
		if(iOrgNodeUID==0)iOrgNodeUID=iTempNodeUID;
		if(iTempNodeUID==aIndivObj.getUserUID() || iOrgNodeUID==aIndivObj.getUserUID()){
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("max_delta");
			}
			if(iOldDelta==0){// this is the only record; they can't delte it ***********
				return -2;
			}
			aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iNewOrgNodeUID = pConn.getDBInt("uid");
			}
				
			// is Legacy primary contact out-of-sync?
			if(iTempNodeUID==iOrgNodeUID){
				// update the um_node record and the delta 0 volorg_owner record to this uid
				aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
						" SET delta=0 WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iNewOrgNodeUID; 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iNewOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}else if(iOrgNodeUID!=aIndivObj.getUserUID()){ // is out of sync
				// should we choose NodeUID or OrgUID to be primary???
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			// delete from um_content_field_volorg_owner
			aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}else{
			aszSQL101="SELECT delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iNewDelta = pConn.getDBInt("delta");
			}
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("max_delta");
			}
			if(iOldDelta==0){// this is the only record; they can't delte it ***********
				return -2;
			}
			// delete from um_content_field_volorg_owner
			aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
					" SET delta=" + iNewDelta + " WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}

/*		
		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
		aszSQL101="SELECT uid FROM " + aszDrupalDB + "node WHERE nid=" + aHeadObj.getORGNID();
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
		iRetCode=-1;
		if(pConn.getNextResult()){
			iTempNodeUID = pConn.getDBInt("uid");
		}
		
		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
		aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=0 ";
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
		iRetCode=-1;
		if(pConn.getNextResult()){
			iOrgNodeUID = pConn.getDBInt("uid");
		}
		if(iOrgNodeUID==0)iOrgNodeUID=iTempNodeUID;
		if(iTempNodeUID==aIndivObj.getUserUID() || iOrgNodeUID==aIndivObj.getUserUID()){
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("maxDelta");
			}
			if(iOldDelta==0){// this is the only record; they can't delte it ***********
				return -2;
			}
			aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iNewOrgNodeUID = pConn.getDBInt("uid");
			}
				
			// is Legacy primary contact out-of-sync?
			if(iTempNodeUID==iOrgNodeUID){
				// update the um_node record and the delta 0 volorg_owner record to this uid
				aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
						" SET delta=0 WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iNewOrgNodeUID; 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iNewOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}else if(iOrgNodeUID!=aIndivObj.getUserUID()){ // is out of sync
				// should we choose NodeUID or OrgUID to be primary???
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
		}
		//delete from um_content_field_volorg_owner
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
*/		
		return 0;
	}
	// end-of method deleteOrgAdminFromDB()
	

    /**
	* select a list of opportunities this user has (whether they can be deleted from org contacts or not)
	*/
	public int setOppsForOrgContactDBListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj, int iUID){
		int iRetCode=0, iCount=0, iContactUID=0;
		int[] a_iOppNIDsTemp = new int[255];
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT opp_nid " +
			" FROM " + aszDrupalDB + "usermail " + 
			" WHERE uid =" + iUID + " AND org_nid=" + aOrgInfoObj.getORGNID() + " AND opp_nid > 0 "; 
		MethodInit("setOppsForOrgContactDBListArray");
		if(null == pConn) return -1;
    	if(null == aOrgInfoObj) return -2;
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
		iRetCode=0;
		while(pConn.getNextResult()){
            iRetCode=1;
            a_iOppNIDsTemp[iCount]=pConn.getDBInt("opp_nid");
            iCount++;
        }
		int[] a_iOppNIDs = new int[iCount];
		for(iCount=0; iCount < a_iOppNIDs.length; iCount++){
			a_iOppNIDs[iCount]=a_iOppNIDsTemp[iCount];
		}
		aOrgInfoObj.setOppNIDsArray(a_iOppNIDs);
		
		return iRetCode;
    }
    // end-of method setOppsForOrgContactDBListArray()


	/**
	* select a list of administrators for an organization
	*/
	public int setOrgContactDBListArray(ABREDBConnection pConn,  OrganizationInfoDTO aOrgInfoObj){
		int iRetCode=0, iCount=0, iContactUID=0, iEmailCount=0;
		int[] a_iOrgContactUIDsTemp = new int[1000];
		String aszSQL2=null;
		aszSQL2 = " SELECT contact.uid  contact_uid, contact.mail  gets_mail " +
			" FROM " + aszDrupalDB + "users u," + aszDrupalDB + "usermail contact " +
			" WHERE contact.uid =u.uid AND contact.org_nid=" + aOrgInfoObj.getORGNID() + " GROUP BY contact.uid " ; 
		MethodInit("setOrgContactDBListArray");
		if(null == pConn) return -1;
    	if(null == aOrgInfoObj) return -2;
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            a_iOrgContactUIDsTemp[iCount]=pConn.getDBInt("contact_uid");
            iCount++;
        }
		int[] a_iOrgContactUIDs = new int[iCount];
		for(iCount=0; iCount < a_iOrgContactUIDs.length; iCount++){
			a_iOrgContactUIDs[iCount]=a_iOrgContactUIDsTemp[iCount];
		}
		aOrgInfoObj.setORGContactUIDsArray(a_iOrgContactUIDs);
		
		return iRetCode;
    }
    // end-of method setOrgContactDBListArray()


    /**
	* updateORGContactsDB an Opportunity Volunteer Contact record (whether they receive emails or not in the voleng system)
	*/
	public int updateORGContactsDB(ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0,iTmp=0;
		boolean bIsOppContact=false;
		String Qry1, aszSQL101;
		MethodInit("updateORGContactsDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null organization object");
			return -1;
		}
		if(null == aListIdsAndEmailNotifyFlag){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		// make sure the contact isn't listed on any child opps
		
		//uid,component, mail_id are the primary key
        for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
            int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
            bIsOppContact=false;
            if(iUID>0){
            	if(iFlag==iNoLongerIsOppContact){//iNoLongerIsORGContact
	        		// first make sure that this user is not listed as a contact already for one of the Opportunity children of this opp
	        		aszSQL101="SELECT opp_nid FROM " + aszDrupalDB + "usermail WHERE org_nid=" + aHeadObj.getORGNID() + " AND uid=" + iUID ; 
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
	        		iRetCode=-1;
	        		int[] iTmpOppNIDs = new int[100];
	        		int[] iOppNIDs = new int[0];
	        		int iCount=0;
	        		while(pConn.getNextResult()){
	        			iTmp = pConn.getDBInt("opp_nid");
	        			if(iTmp>0){
	        				iTmpOppNIDs[iCount]=iTmp;
		        			iCount++;
		        			if(iCount>100) break;
	        			}
	        		}
	        		int iTmpOppNIDsLength = 0;
	        		for(int j=0; j<iTmpOppNIDs.length; j++){
	        			if(iTmpOppNIDs[j]==0)	break;
	        			iTmpOppNIDsLength++;
	        		}
	        		if(iTmpOppNIDsLength>0){
		        		iOppNIDs = new int[iTmpOppNIDsLength];
		        		for(int j=0; j<iTmpOppNIDsLength; j++){
		        			iOppNIDs[j] = iTmpOppNIDs[j];
		        		}	        		
	        			bIsOppContact=true;
	        		}
	        		if(bIsOppContact==false){
	            		// delete from um_usermail for voleng mailing notifications
	            		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
	            				" WHERE org_nid=" + aHeadObj.getORGNID() + " AND uid=" + iUID ; 
	            		iRetCode=pConn.RunUpdate(aszSQL101);
	            		if(0 != iRetCode){
	            			pConn.copyErrObj(getErrObj());
	            			ErrorsToLog();
	            			return -1;
	            		}
	        		}else{
	        			ArrayList<Object> aContactSkipped = new ArrayList<Object>(2);
	        			aContactSkipped.add(iUID);
	        			aContactSkipped.add(iOppNIDs);
	        			
	        			ArrayList<Object> aContactsSkipped = aHeadObj.getOrgContactRemoveSkipped();
	        			aContactsSkipped.add(aContactSkipped);
	        			aHeadObj.setOrgContactRemoveSkipped(aContactsSkipped);
	        		}
            	}else if(iFlag==iNowIsOppContact){//iNowIsORGContact
                    Qry1="INSERT IGNORE INTO " + aszDrupalDB + "usermail (uid, component, mail_id, opp_nid, org_nid, mail, primary_record) " +
						" VALUES (" + iUID + ", 'voleng_org_nid', '" + aHeadObj.getORGNID() + "',0," + aHeadObj.getORGNID() + ",0,0) ";
					iRetCode=pConn.PrepQuery(Qry1);
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
            	}
            }
        }
		return iRetCode; 
	}
	// end-of method updateORGContactDB()


    /**	
	* insert a row into um_usermail for the org (w 0 for opp_nid) to show contact of given UID to this VID/NID
	*/
	public int insertAddOrgContactIntDB(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		String aszSQLdrupalTemp = "";
		// "auto-"increment the nid and vid in urbmi5_drupal.um_sequences table - then grab last Id...
		// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1;
		int iUID = aIndivObj.getUserUID(), iNID=aHeadObj.getORGNID();
		String Qry1=null;
		MethodInit("insertAddOrgContactIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		Qry1 = "SELECT * FROM " + aszDrupalDB + "usermail WHERE org_nid=" + iNID +
			" AND uid=" + iUID;
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			aHeadObj.appendErrorMsg("This user is already a Contact for this Organization");
			return 0;
		} 
		iRetCode=0; 
		/*
		 * add to usermail
		 */
		aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "usermail (uid, component, mail_id, opp_nid, org_nid, mail, primary_record) " +
			" VALUES (" + iUID + ", 'voleng_org_nid', '" + iNID + "',0," + iNID + ",0,0) ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
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
/*		
		// get all the child opps affiliated with this org, and insert a row for this user and each of those opps into the usermail table
		// iterate through aHeadObj.getORGChildOPPNIDsArray
		int iOppNID=0;
		int[] a_iOppNIDs = aHeadObj.getORGChildOPPNIDsArray();
		for (int i=0; i<a_iOppNIDs.length;i++){
			iOppNID = a_iOppNIDs[i];
			if(iOppNID>0){
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail, primary_record, org_nid, opp_nid) " +
						"VALUES(" + iUID+ ",'voleng_opp_nid','" + iOppNID + "',0,0," + iNID + "," + iOppNID + ") ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					break;
				}
				iRetCode = pConn.ExecutePrepQry();
			}
		}
*/		
		return 0;
	}
	// end-of method insertAddOrgAdminIntDB()

	/**
	* select a list of email contacts for an organization - used for complete drop-down list - org based, etc
	*/
	public int getOrgContactDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj, String aszUseCase){
		int iRetCode=0, iTemp=0, iContactUID=0, iCount=0,iNoResults1=0,iNoResults2=0,iNoResults3=0,iNoResults4=0,iNoResults5=0,iNoResults6=0;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		int[] a_iORGContactUIDsArray= new int[255];
		boolean b_newContactUID = true;
		MethodInit("getOrgContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		if(aszUseCase==null){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				"uprofile.nid, uprofile.field_first_name_value, " +
				" uprofile.field_last_name_value, u.mail, u.uid contact_uid " +
				" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
				aszDrupalDB + "content_type_uprofile uprofile " +
				" WHERE  contact.opp_nid=" + aSrchParmObj.getOPPNID() + 
				" AND contact.uid=u.uid AND contact.component='voleng_opp_nid' AND n.uid=u.uid AND n.type='uprofile' " +
				" AND n.nid=uprofile.nid GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC "; // placeholder for conditionals set in BLO layer
		}else if(aszUseCase.equals("org")){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				"uprofile.nid, uprofile.field_first_name_value, " +
				" uprofile.field_last_name_value, u.mail, u.uid contact_uid " +
				" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
				aszDrupalDB + "content_type_uprofile uprofile " +
				" WHERE  contact.org_nid=" + aSrchParmObj.getNID() + 
				" AND contact.uid=u.uid AND contact.component='voleng_org_nid' AND n.uid=u.uid AND n.type='uprofile' " +
				" AND n.nid=uprofile.nid GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC "; // placeholder for conditionals set in BLO layer
		}else if(aszUseCase.equals("orgmailing")){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				"uprofile.nid, uprofile.field_first_name_value, " +
				" uprofile.field_last_name_value, u.mail, u.uid contact_uid " +
				" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
				aszDrupalDB + "content_type_uprofile uprofile " +
				" WHERE  contact.org_nid=" + aSrchParmObj.getNID() + " AND contact.mail=1 " +
				" AND contact.uid=u.uid " + // AND contact.component='voleng_org_nid'" +
				" AND n.uid=u.uid AND n.type='uprofile' " +
				" AND n.nid=uprofile.nid GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC "; // placeholder for conditionals set in BLO layer
		}else{
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				"uprofile.nid, uprofile.field_first_name_value, " +
				" uprofile.field_last_name_value, u.mail, u.uid contact_uid " +
				" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
				aszDrupalDB + "content_type_uprofile uprofile " +
				" WHERE  contact.opp_nid=" + aSrchParmObj.getOPPNID() + 
				" AND contact.uid=u.uid AND contact.component='voleng_opp_nid' AND n.uid=u.uid AND n.type='uprofile' " +
				" AND n.nid=uprofile.nid GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC "; // placeholder for conditionals set in BLO layer
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults1=-1;
		while(pConn.getNextResult()){
			iNoResults1=0;
			iContactUID = pConn.getDBInt("contact_uid");
	        aszEmail = pConn.getDBString("u.mail");
			a_iORGContactUIDsArray[iCount]=iContactUID;
            iCount++;

	        PersonInfoDTO aHeadObj = new PersonInfoDTO();
			aHeadObj.setUSPEmail1Addr(aszEmail);
			aHeadObj.setUserUID(iContactUID);
			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
			
			aHeadObj.setIsVolunteerContact(pConn.getDBInt("gets_mail"));
			aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("is_primary_contact"));
			
			aListObj.add(aHeadObj);
		}
		
		
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		if(aszUseCase==null){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
			" u.mail, u.uid contact_uid " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact  " +
			" WHERE contact.opp_nid=" + aSrchParmObj.getOPPNID() + 
			" AND contact.uid=u.uid AND contact.component='voleng_opp_nid'"; // placeholder for conditionals set in BLO layer
		}else if(aszUseCase.equals("org")){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				" u.mail, u.uid contact_uid " +
				" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact  " +
				" WHERE contact.org_nid=" + aSrchParmObj.getNID() + 
				" AND contact.uid=u.uid AND contact.component='voleng_org_nid' "; // placeholder for conditionals set in BLO layer
		}else if(aszUseCase.equals("orgmailing")){
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
					" u.mail, u.uid contact_uid " +
					" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact  " +
					" WHERE contact.org_nid=" + aSrchParmObj.getNID() + " AND contact.mail=1 " +
					" AND contact.uid=u.uid ";//AND contact.component='voleng_org_nid' "; // placeholder for conditionals set in BLO layer
		}else{
			aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, contact.mail gets_mail, contact.primary_record is_primary_contact, " +
				" u.mail, u.uid contact_uid " +
				" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact  " +
				" WHERE contact.opp_nid=" + aSrchParmObj.getOPPNID() + 
				" AND contact.uid=u.uid AND contact.component='voleng_opp_nid'"; // placeholder for conditionals set in BLO layer
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults2=-1;
		while(pConn.getNextResult()){
			iNoResults2=0;
			iContactUID = pConn.getDBInt("contact_uid");
	        aszEmail = pConn.getDBString("u.mail");
			b_newContactUID = true;
	        for(iTemp=0;iTemp < a_iORGContactUIDsArray.length; iTemp++){
	        	if(a_iORGContactUIDsArray[iTemp]==(iContactUID)){
	        		b_newContactUID = false;
	        		break;
	        	}
	        }
	        
	        if(b_newContactUID == true){
				a_iORGContactUIDsArray[iCount]=iContactUID;
	            iCount++;
	
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setUserUID(iContactUID);
				
				aHeadObj.setIsVolunteerContact(pConn.getDBInt("gets_mail"));
				aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("is_primary_contact"));

				aListObj.add(aHeadObj);
	        }
		}
		boolean b_executeCode = false;
		if(aszUseCase== null){
			b_executeCode=true;
		}else if(! aszUseCase.equals("orgmailing")){
			b_executeCode = true;
		}
		if(b_executeCode){
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail " +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile uprofile, " + 
			 aszDrupalDB + "users u," + aszDrupalDB + "content_field_volorg_owner contact " +
			" WHERE contact.nid=" + aSrchParmObj.getORGNID() + " AND contact.field_volorg_owner_uid =n.uid " +
			" AND n.type='uprofile' AND n.nid=uprofile.nid  AND contact.field_volorg_owner_uid =u.uid GROUP BY u.uid" ; // placeholder for conditionals set in BLO layer
/*		
		aszSQL2 = " SELECT contact.org_nid org_nid, " +
			"uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, u.uid contact_uid " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  contact.org_nid=" + aSrchParmObj.getORGNID() + 
			" AND contact.uid=u.uid AND contact.component LIKE 'voleng_org_nid' AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC GROUP BY (contact.uid,content.org_nid)"; // placeholder for conditionals set in BLO layer
*/
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults3=-1;
		while(pConn.getNextResult()){
			iNoResults3=0;

            iContactUID = pConn.getDBInt("contact_uid");
			b_newContactUID = true;
	        for(iTemp=0;iTemp < a_iORGContactUIDsArray.length; iTemp++){
	        	if(a_iORGContactUIDsArray[iTemp]==(iContactUID)){
	        		b_newContactUID = false;
	        		break;
	        	}
	        }
	        
	        if(b_newContactUID == true){
				a_iORGContactUIDsArray[iCount]=iContactUID;
	            iCount++;
	
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
				aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aHeadObj.setIsParentOrgAdmin(1);
				
				aListObj.add(aHeadObj);
	        }
		}
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, u.mail " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "content_field_volorg_owner contact " +
			" WHERE contact.nid=" + aSrchParmObj.getNID() + " AND contact.field_volorg_owner_uid =u.uid GROUP BY u.uid "; // placeholder for conditionals set in BLO layer
/*		
		aszSQL2 = " SELECT contact.org_nid org_nid,  " +
			" u.mail, u.uid contact_uid " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact " +
			" WHERE  contact.org_nid=" + aSrchParmObj.getORGNID() + 
			" AND contact.uid=u.uid AND contact.component LIKE 'voleng_org_nid' " +
			" GROUP BY u.uid ORDER BY contact.primary_record DESC, u.uid ASC GROUP BY (contact.uid,content.org_nid)"; // placeholder for conditionals set in BLO layer
*/
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults4=-1;
		while(pConn.getNextResult()){
			iNoResults4=0;
            iContactUID = pConn.getDBInt("contact_uid");
	        aszEmail = pConn.getDBString("u.mail");
			b_newContactUID = true;
            for(iTemp=0;iTemp < a_iORGContactUIDsArray.length; iTemp++){
            	if(a_iORGContactUIDsArray[iTemp]==(iContactUID)){
            		b_newContactUID = false;
            		break;
            	}
            }
            
            if(b_newContactUID == true){
    			a_iORGContactUIDsArray[iCount]=iContactUID;
                iCount++;

		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setIsParentOrgAdmin(1);
				aListObj.add(aHeadObj);
            }
		}
		
		
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, contact.mail, u.uid " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  contact.org_nid=" + aSrchParmObj.getNID() + 
			" AND contact.uid=u.uid AND contact.component LIKE 'voleng_org_nid' AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid GROUP BY u.uid "; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults5=-1;
		while(pConn.getNextResult()){
			iNoResults5=0;
			iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
			b_newContactUID = true;
	        for(iTemp=0;iTemp < a_iORGContactUIDsArray.length; iTemp++){
	        	if(a_iORGContactUIDsArray[iTemp]==(iContactUID)){
	        		b_newContactUID = false;
	        		break;
	        	}
	        }
	        
	        if(b_newContactUID == true){
				a_iORGContactUIDsArray[iCount]=iContactUID;
	            iCount++;
	
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
				aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
	        }
		}
		
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT contact.opp_nid opp_nid, contact.org_nid org_nid, u.mail, contact.mail, u.uid " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail contact  " +
			" WHERE contact.org_nid=" + aSrchParmObj.getNID() + 
			" AND contact.uid=u.uid AND contact.component LIKE 'voleng_org_nid' GROUP BY u.uid"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults6=-1;
		while(pConn.getNextResult()){
			iNoResults6=0;
			iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
			b_newContactUID = true;
	        for(iTemp=0;iTemp < a_iORGContactUIDsArray.length; iTemp++){
	        	if(a_iORGContactUIDsArray[iTemp]==(iContactUID)){
	        		b_newContactUID = false;
	        		break;
	        	}
	        }
	        
	        if(b_newContactUID == true){
				a_iORGContactUIDsArray[iCount]=iContactUID;
	            iCount++;
	
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setUserUID(iContactUID);
				aListObj.add(aHeadObj);
	        }
		}
		}
		if(iNoResults1==0 || iNoResults2==0 || iNoResults3==0 || iNoResults4==0 || iNoResults5==0 || iNoResults6==0){
			iRetCode=0;
		}
		return iRetCode;
    }
    // end-of method getOrgContactDBList()

	

	// end-of method getOrgVolContactDBList()
	
	
	/**
	* delete an organizational contact record
	*/
	public int deleteOrgContactFromDB(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		int iTempNodeUID=0, iNewOrgNodeUID=0, iOrgNodeUID=0, iTempNID=0, iOldDelta=0;
		String aszSQL101;
		MethodInit("deleteOrgContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		// delete from um_usermail for voleng mailing notifications
		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
				" WHERE org_nid=" + aHeadObj.getORGNID() + " AND uid=" + aIndivObj.getUserUID() ; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
/*		
		// first make sure that this is not the uid that owns the actual um_node node record. and is not delta=0
		aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=0 ";
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
		iRetCode=-1;
		if(pConn.getNextResult()){
			iOrgNodeUID = pConn.getDBInt("uid");
		}
		if(iOrgNodeUID==0)iOrgNodeUID=iTempNodeUID;
		if(iTempNodeUID==aIndivObj.getUserUID() || iOrgNodeUID==aIndivObj.getUserUID()){
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("maxDelta");
			}
			if(iOldDelta==0){// this is the only record; they can't delte it ***********
				return -2;
			}
			aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iNewOrgNodeUID = pConn.getDBInt("uid");
			}
				
			// is Legacy primary contact out-of-sync?
			if(iTempNodeUID==iOrgNodeUID){
				// update the um_node record and the delta 0 volorg_owner record to this uid
				aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
						" SET delta=0 WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iNewOrgNodeUID; 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iNewOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}else if(iOrgNodeUID!=aIndivObj.getUserUID()){ // is out of sync
				// should we choose NodeUID or OrgUID to be primary???
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			// delete from um_content_field_volorg_owner
			aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}else{
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("maxDelta");
			}
			aszSQL101="SELECT MAX(delta) max_delta FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() ;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iOldDelta = pConn.getDBInt("maxDelta");
			}
			if(iOldDelta==0){// this is the only record; they can't delte it ***********
				return -2;
			}
			aszSQL101="SELECT field_volorg_owner_uid uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND delta=" + iOldDelta;
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iNewOrgNodeUID = pConn.getDBInt("uid");
			}
				
			// is Legacy primary contact out-of-sync?
			if(iTempNodeUID==iOrgNodeUID){
				// update the um_node record and the delta 0 volorg_owner record to this uid
				aszSQL101="UPDATE " + aszDrupalDB + "content_field_volorg_owner " +
						" SET delta=0 WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + iNewOrgNodeUID; 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iNewOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}else if(iOrgNodeUID!=aIndivObj.getUserUID()){ // is out of sync
				// should we choose NodeUID or OrgUID to be primary???
				aszSQL101="UPDATE " + aszDrupalDB + "node " +
						" SET uid=" + iOrgNodeUID + " WHERE nid=" + aHeadObj.getORGNID(); 
				iRetCode=pConn.RunUpdate(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			// delete from um_content_field_volorg_owner
			aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
					" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
			iRetCode=pConn.RunUpdate(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}

		}
*/
		return 0;
	}



	/**
	* select a list of administrators for an organization
	*/
	public int setOppContactDBListArray(ABREDBConnection pConn,  OrgOpportunityInfoDTO aOpportObj){
		int iRetCode=0, iCount=0, iContactUID=0, iEmailCount=0;
		int[] a_iOppContactUIDsTemp = new int[1000];
		int[] a_iOppContactUIDsRcvEmailTemp = new int[1000];
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT contact.uid  contact_uid, contact.mail  gets_mail " +
			" FROM " + aszDrupalDB + "users u," + aszDrupalDB + "usermail contact " +
			" WHERE contact.uid =u.uid AND contact.opp_nid=" + aOpportObj.getOPPNID() ; 
		MethodInit("setOppContactDBListArray");
		if(null == pConn) return -1;
    	if(null == aOpportObj) return -2;
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            a_iOppContactUIDsTemp[iCount]=pConn.getDBInt("contact_uid");
            if(pConn.getDBInt("gets_mail")==1){
            	a_iOppContactUIDsRcvEmailTemp[iEmailCount]=pConn.getDBInt("contact_uid");
            	iEmailCount++;
            }
            iCount++;
        }
		int[] a_iOppContactUIDs = new int[iCount];
		for(iCount=0; iCount < a_iOppContactUIDs.length; iCount++){
			a_iOppContactUIDs[iCount]=a_iOppContactUIDsTemp[iCount];
		}
		int[] a_iOppContactUIDsRcvEmail = new int[iEmailCount];
		for(iEmailCount=0; iEmailCount < a_iOppContactUIDsRcvEmail.length; iEmailCount++){
			a_iOppContactUIDsRcvEmail[iEmailCount]=a_iOppContactUIDsRcvEmailTemp[iEmailCount];
		}
		aOpportObj.setOPPContactUIDsArray(a_iOppContactUIDs);
		aOpportObj.setOPPContactUIDsRcvEmailArray(a_iOppContactUIDsRcvEmail);
		
		return iRetCode;
    }
    // end-of method setOppContactDBListArray()

	/**
	* select a list of email contacts for an OPPORTUNITY
	*/
	public int getOppContactDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0, iNoResults1=-1, iNoResults2=-1;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT usermail.mail_id org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  usermail.mail_id=" + aSrchParmObj.getNID() + // aSrchParmObj.getNID() should NOW, in this case, be OPPnid, not orgnid
			" AND usermail.uid=u.uid AND usermail.component='voleng_opp_nid' AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid"; 
		MethodInit("getOppVolContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults1=-1;
		while(pConn.getNextResult()){
			iNoResults1=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
			aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			//if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("u.uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			//}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT usermail.mail_id org_nid, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail  " +
			" WHERE usermail.mail_id=" + aSrchParmObj.getNID() + 
			" AND usermail.uid=u.uid AND usermail.component='voleng'";// AND usermail.mail=1"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults2=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iNoResults2=0;
	        iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
				aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		if(iNoResults1==0 || iNoResults2==0){
			iRetCode=0;
		}
		return iRetCode;
    }
    // end-of method getOppContactDBList()


	/**
	* select a list of email contacts for an OPPORTUNITY
	*/
	public int getOppVolContactDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0, iNoResults1=-1, iNoResults2=-1;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT usermail.mail_id org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  usermail.mail_id=" + aSrchParmObj.getNID() + // aSrchParmObj.getNID() should NOW, in this case, be OPPnid, not orgnid
			" AND usermail.mail=1 AND usermail.uid=u.uid AND usermail.component='voleng_opp_nid' AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid"; 
		MethodInit("getOppVolContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults1=-1;
		while(pConn.getNextResult()){
			iNoResults1=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
			aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			//if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("u.uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			//}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT usermail.mail_id org_nid, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail  " +
			" WHERE usermail.mail_id=" + aSrchParmObj.getNID() + 
			" AND usermail.uid=u.uid AND usermail.component='voleng' AND usermail.mail=1"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults2=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iNoResults2=0;
	        iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
				aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		if(iNoResults1==0 || iNoResults2==0){
			iRetCode=0;
		}
		return iRetCode;
    }
    // end-of method getOppVolContactDBList()



	/**
	* select a list of email contacts for an getOrgContactDBList
	*/
	public int getOrgContactDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0, iNoResults1=-1, iNoResults2=-1;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT usermail.mail_id org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  usermail.org_nid=" + aSrchParmObj.getNID() + // aSrchParmObj.getNID() should NOW, in this case, be OPPnid, not orgnid
			" AND usermail.mail=1 AND usermail.uid=u.uid AND usermail.component='voleng_opp_nid' AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid " +
			" GROUP BY u.mail "; 
		MethodInit("getOrgContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iNoResults1=-1;
		while(pConn.getNextResult()){
			iNoResults1=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
			aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			//if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("u.uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			//}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT usermail.mail_id org_nid, u.mail, usermail.mail, u.uid, primary_record " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail  " +
			" WHERE usermail.org_id=" + aSrchParmObj.getNID() + 
			" AND usermail.uid=u.uid AND usermail.mail=1 GROUP BY u.mail"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
//			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
//				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
//			return -999;
		}
		iNoResults2=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iNoResults2=0;
	        iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
				aHeadObj.setIsPrimaryVolunteerContact(pConn.getDBInt("primary_record"));
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		if(iNoResults1==0 || iNoResults2==0){
			iRetCode=0;
		}
		return iRetCode;
    }
    // end-of method getOrgContactDBList()


    /**	
	* insert a row into for Volunteer Contact Opportunity of given UID to this VID/NID
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertAddOppContactIntDB(ABREDBConnection pConn, OrgOpportunityInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		// "auto-"increment the nid and vid in urbmi5_drupal.um_sequences table - then grab last Id...
		// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		int iIsPrimaryRecord=1; // if this is the first record for the given opp, then it should be the primary
		int iInitIsPrimaryRecordUID=0 ;
		String Qry1=null;
		MethodInit("insertAddOppContactIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT * FROM " + aszDrupalDB + "usermail WHERE mail_id=" + aHeadObj.getOPPNID() +
			" AND uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			aHeadObj.appendErrorMsg("This user is already a Volunteer Contact for this Opportunity");
			return 0;
		} 
		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		
		/*
		 * get Primary Contact Record for this Opportunity (where primary_record=1)
		 */
		Qry1 = "SELECT uid FROM " + aszDrupalDB + "usermail WHERE mail_id=" + aHeadObj.getOPPNID() +
			" AND primary_record=1" ;
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		if(pConn.getNextResult()){
			iInitIsPrimaryRecordUID = pConn.getDBInt("uid");
			iIsPrimaryRecord=0; // there is already a primary record; will have to "update" if the new one is supposed to REPLACE AS primary
			if(aIndivObj.getIsPrimaryVolunteerContact()==1){
				iIsPrimaryRecord=1;
			}
		} 
		iRetCode=0; 
		/*
		 * add to um_usermail - whether this user receives emails for this organization or not; when iIsVolunteerContact=1, yes (otherwise, 0=no)
		 * ***************************    NEED TO ADD FLAG for PRIMARY
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail" +
				",opp_nid, org_nid, primary_record) " +
				"VALUES(" + aIndivObj.getUserUID() + ", 'voleng_opp_nid', '" + aHeadObj.getOPPNID() + "'," + iIsVolunteerContact + " " +
						"," + aHeadObj.getOPPNID() + "," + aHeadObj.getORGNID() + "," + iIsPrimaryRecord + ") " ;
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
		
		if(iInitIsPrimaryRecordUID>0 && iIsPrimaryRecord==1 && iInitIsPrimaryRecordUID!=aIndivObj.getUserUID()){
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "usermail SET primary_record=0 " +
					" WHERE uid=" + aIndivObj.getUserUID() + " AND  mail_id LIKE '" + aHeadObj.getOPPNID() + "' " ;
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
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "node SET uid=" + aIndivObj.getUserUID() + " " +
					" WHERE nid=" + aHeadObj.getOPPNID() + " " ;
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
		aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail" +
				",opp_nid, org_nid, primary_record) " +
				"VALUES(" + aIndivObj.getUserUID() + ", 'voleng_org_nid', '" + aHeadObj.getORGNID() + "',0 " +
						",0," + aHeadObj.getORGNID() + ",0) " ;
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExecutePrepQry();
		if(iRetCode == 1062 ){ // then this is a duplicate; 
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
			
		return 0;
	}
	// end-of method insertAddOppContactIntDB()
	

    /**
	* delete an opportunity contact record 
	*/
	public int deleteOppContactFromDB(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		int ilid=-1;
		String Qry1, aszSQL101;
		MethodInit("deleteOppContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		// delete from um_usermail for voleng mailing notifications
		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
				" WHERE mail_id=" + aHeadObj.getOPPNID() + " AND uid=" + aIndivObj.getUserUID() + " AND component='voleng_opp_nid'"; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method deleteOppContactFromDB()
	

    /**
	* Reset an Opportunity Primary Contact record
	*/
	public int resetOppPrimaryContactFromDB(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrgOpportunityInfoDTO aHeadObj, int iIsVolunteerContact ){
		int iRetCode=0;
		int iTemp=0;
		String Qry1, aszSQL101;
		MethodInit("resetOppPrimaryContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		Qry1 = "SELECT uid FROM " + aszDrupalDB + "usermail WHERE opp_nid=" + aHeadObj.getOPPNID() +
			" AND component='voleng_opp_nid' AND primary_record=1";//uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		int iPreviousMailStatus=0;
		iRetCode=1;
		if(pConn.getNextResult()){
			iTemp=pConn.getDBInt("uid");
		}
		if(iTemp>0){
			Qry1="UPDATE " + aszDrupalDB + "usermail SET primary_record=0 " +
				" WHERE uid=" + iTemp + " AND opp_nid= " + aHeadObj.getOPPNID();
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExecutePrepQry();
		}
		iRetCode=0;
		
		Qry1="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail" +
				",opp_nid, org_nid, primary_record) " +
				"VALUES(" + aIndivObj.getUserUID() + ", 'voleng_opp_nid', '" + aHeadObj.getOPPNID() + "',1 " +
						"," + aHeadObj.getOPPNID() + "," + aHeadObj.getORGNID() + ",1) "+
	    				" ON DUPLICATE KEY UPDATE primary_record=1, mail=1" ; 
		iRetCode=pConn.PrepQuery(Qry1);
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
		iRetCode=0;
		Qry1="UPDATE " + aszDrupalDB + "node SET uid=" + aIndivObj.getUserUID() + " " +
				" WHERE nid=" + aHeadObj.getOPPNID() + " " ;
		iRetCode=pConn.PrepQuery(Qry1);
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
				
		return iRetCode;
	}
	// end-of method resetOppContactFromDB()

    /**
	* Reset an Opportunity Volunteer Contact record (whether they receive emails or not in the voleng system)
	*/
	public int resetOppContactFromDB(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrgOpportunityInfoDTO aHeadObj, int iIsVolunteerContact ){
		int iRetCode=0;
		int ilid=-1;
		String Qry1, aszSQL101;
		MethodInit("resetOppContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		Qry1 = "SELECT * FROM " + aszDrupalDB + "usermail WHERE opp_nid=" + aHeadObj.getOPPNID() +
			" AND component='voleng_opp_nid' AND uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		int iPreviousMailStatus=0;
		iRetCode=1;
		while(pConn.getNextResult()){
			iRetCode=0;
			iPreviousMailStatus=pConn.getDBInt("mail");
			if(iPreviousMailStatus==iIsVolunteerContact){
				// the user's contact status is not changing; don't trigger an email or anything
				aHeadObj.appendErrorMsg("This user already has administrative privileges on this Opportunity");
				return -444;
			}
		}
		if(iRetCode==1){
			// new CONTACT; will be doing an insert into the usermail table, rather than an update
			Qry1="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail" +
				",opp_nid, org_nid, primary_record) " +
				"VALUES(" + aIndivObj.getUserUID() + ", 'voleng_opp_nid', '" + aHeadObj.getOPPNID() + "'," + iIsVolunteerContact + " " +
						"," + aHeadObj.getOPPNID() + "," + aHeadObj.getORGNID() + ",0) " ;
			iRetCode=pConn.PrepQuery(Qry1);
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
			
			iRetCode=0;
		}else{
			Qry1="UPDATE " + aszDrupalDB + "usermail SET mail = " + iIsVolunteerContact + 
				" WHERE opp_nid=" + aHeadObj.getOPPNID() + " AND uid=" + aIndivObj.getUserUID(); 
			iRetCode=pConn.PrepQuery(Qry1);
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
			iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		}
		
		return iPreviousMailStatus; // (0= NOW receives emails; 1= NO LONGER receives emails)
	}
	// end-of method resetOppContactFromDB()

    /**
	* updateOPPContactsDB an Opportunity Volunteer Contact record (whether they receive emails or not in the voleng system)
	*/
	public int updateOPPContactsDB(ABREDBConnection pConn, ArrayList aListIdsAndEmailNotifyFlag, OrgOpportunityInfoDTO aHeadObj ){
		int iRetCode=0;
		int iMailValue=0, iPrimaryRecordValue=0;
		String Qry1, aszSQL101;
		MethodInit("updateOPPContactsDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null opportunity object");
			return -1;
		}
		if(null == aListIdsAndEmailNotifyFlag){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		//uid,component, mail_id are the primary key
        for(int i=0; i<aListIdsAndEmailNotifyFlag.size(); i++){ 
        	iMailValue=0;
            int[] iIdAndFlag = (int[])aListIdsAndEmailNotifyFlag.get(i);
            int iUID=iIdAndFlag[0];
            int iFlag=iIdAndFlag[1];
            if(iUID==aHeadObj.getOPPPrimaryPersonUIDModified()){
            	iPrimaryRecordValue=1;
            }else{
            	iPrimaryRecordValue=0;
            }
        	// a. REMOVED
            switch(iFlag){
            	case iNowGetsEmails:
            		iMailValue=1;
            		break;
            	case iNowEmailAndContact :
            		iMailValue=1;
            		break;
            	default:
            		break;
            }
            if(iPrimaryRecordValue==1)iMailValue=iPrimaryRecordValue;
            if(iFlag==iNoLongerIsOppContact){ // set to be removed
        		// delete from um_usermail for voleng mailing notifications
        		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
        				" WHERE mail_id=" + aHeadObj.getOPPNID() + " AND uid=" + iUID + " AND component='voleng_opp_nid'"; 
        		iRetCode=pConn.RunUpdate(aszSQL101);
        		if(0 != iRetCode){
        			pConn.copyErrObj(getErrObj());
        			ErrorsToLog();
        			return -1;
        		}
        		return 0;
            }
            if(iUID>0){
                Qry1="INSERT INTO " + aszDrupalDB + "usermail (uid, component, mail_id, opp_nid, org_nid, mail, primary_record) " +
					" VALUES (" + iUID + ", 'voleng_opp_nid', '" + aHeadObj.getOPPNID() + "'," + aHeadObj.getOPPNID() + "," + aHeadObj.getORGNID() + "," +
						iMailValue + "," + iPrimaryRecordValue + ") " +
					" ON DUPLICATE KEY UPDATE " +
					" primary_record=" + iPrimaryRecordValue  + " , mail =" + iMailValue;
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				iRetCode = pConn.ExecutePrepQry();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				if(iPrimaryRecordValue==1){
					Qry1="UPDATE " + aszDrupalDB + "node SET uid=" + iUID + " " +
							" WHERE nid=" + aHeadObj.getOPPNID() + " " ;
					iRetCode=pConn.PrepQuery(Qry1);
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
            }
        }
		return iRetCode; 
	}
	// end-of method updateOPPContactsDB()


	//=== END   Table organizationinfo ===>
    //=== END   Table organizationinfo ===>
    //=== END   Table organizationinfo ===>



	
    //=== START Table org_details ===>
    //=== START Table org_details ===>
    //=== START Table org_details ===>
    /**
	* insert a row into table org_details
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertOrgDetailsIntDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		String Qry1=null;
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt(); // no matter what I call on pConn, i get nested exception ***************20090730
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		String aszSQL101 = " INSERT INTO " + aszVolengDB + "org_details ( " +
			"org_nid, org_number, orgcodekey, primary_person" +
			", update_dt, update_id" +
			", fed_tax_id, num_vol_org, num_served, is_501c3_p, organization_budget, num_staff_org" +
			", language_arabic, language_chinese, language_english, language_french, language_hindi, language_portuguese" +
			", language_spanish, language_other_a_text, language_other_a, language_other_b_text, language_other_b, race_asian" +
			", race_black, race_caucasian, race_latino, race_pacific_islander, race_native_american, race_other_text" +
			", race_other, demo_low_income, demo_homeless, demo_disability, demo_urban, demo_rural" +
			", demo_youth, demo_adults, demo_seniors, gender_male, gender_female, progtype_comp_center" +
			", progtype_ed_employ, progtype_food, progtype_health, progtype_homeless, progtype_housing, progtype_immigration" +
			", progtype_other, progtype_rehab, progtype_seniors, progtype_spirit_devel, progtype_youth)" +
			" values(?,?,?,?" +
			", {fn now()} ,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?,?" +
			",?,?,?,?,?)" ;
		MethodInit("insertOrgDetailsIntDB");

		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
		}

		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrgNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrgNumber() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETOrgcodekey() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETPrimaryPerson() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETUpdateId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETFedTaxId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumVolOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumServed() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETIs501c3P() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrganizationBudget() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumStaffOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageArabic() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageChinese() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageEnglish() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageFrench() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageHindi() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguagePortuguese() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageSpanish() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETLanguageOtherAText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageOtherA() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETLanguageOtherBText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageOtherB() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceAsian() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceBlack() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceCaucasian() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceLatino() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRacePacificIslander() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceNativeAmerican() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETRaceOtherText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceOther() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoLowIncome() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoHomeless() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoDisability() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoUrban() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoRural() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoYouth() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoAdults() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoSeniors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETGenderMale() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETGenderFemale() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeCompCenter() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeEdEmploy() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeFood() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHealth() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHomeless() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHousing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeImmigration() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeOther() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeRehab() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeSeniors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeSpiritDevel() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeYouth() );
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
		return 0;
	}
	// end-of method insertOrgDetailIntDB()

    /**
	* update organization record in table org_details
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int editOrgDetailInDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE " + aszVolengDB + "org_details SET " +
			"primary_person=?,update_dt={fn now()},update_id=?,fed_tax_id=?" +
			" ,num_vol_org=?,num_served=?,is_501c3_p=?,organization_budget=?,num_staff_org=?,language_arabic=?" +
			" ,language_chinese=?,language_english=?,language_french=?,language_hindi=?,language_portuguese=?,language_spanish=?" +
			" ,language_other_a_text=?,language_other_a=?,language_other_b_text=?,language_other_b=?,race_asian=?,race_black=?" +
			" ,race_caucasian=?,race_latino=?,race_pacific_islander=?,race_native_american=?,race_other_text=?,race_other=?" +
			" ,demo_low_income=?,demo_homeless=?,demo_disability=?,demo_urban=?,demo_rural=?,demo_youth=?" +
			" ,demo_adults=?,demo_seniors=?,gender_male=?,gender_female=?,progtype_comp_center=?,progtype_ed_employ=?" +
			" ,progtype_food=?,progtype_health=?,progtype_homeless=?,progtype_housing=?,progtype_immigration=?,progtype_other=?" +
			" ,progtype_rehab=?,progtype_seniors=?,progtype_spirit_devel=?,progtype_youth=?" +
			" WHERE org_nid=? ";
		MethodInit("editOrgDetailInDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETPrimaryPerson() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETUpdateId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETFedTaxId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumVolOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumServed() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETIs501c3P() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrganizationBudget() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETNumStaffOrg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageArabic() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageChinese() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageEnglish() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageFrench() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageHindi() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguagePortuguese() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageSpanish() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETLanguageOtherAText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageOtherA() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETLanguageOtherBText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETLanguageOtherB() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceAsian() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceBlack() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceCaucasian() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceLatino() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRacePacificIslander() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceNativeAmerican() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getDETRaceOtherText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETRaceOther() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoLowIncome() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoHomeless() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoDisability() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoUrban() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoRural() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoYouth() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoAdults() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETDemoSeniors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETGenderMale() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETGenderFemale() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeCompCenter() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeEdEmploy() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeFood() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHealth() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHomeless() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeHousing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeImmigration() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeOther() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeRehab() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeSeniors() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeSpiritDevel() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETProgtypeYouth() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}

		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrgNID() );
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
		return 0;
	}
	// end-of method editOrgDetailInDB()

	/**
	* select a list of items from table org_details
	
	public int getOrgDetailDBList(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){ // seems like it's never called; eventually may 
		int iRetCode=0;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
			"org_nid,org_number,orgcodekey,primary_person,update_dt,update_id," +
			"fed_tax_id,num_vol_org,num_served,is_501c3_p,organization_budget,num_staff_org," +
			"language_arabic,language_chinese,language_english,language_french,language_hindi,language_portuguese," +
			"language_spanish,language_other_a_text,language_other_a,language_other_b_text,language_other_b,race_asian," +
			"race_black,race_caucasian,race_latino,race_pacific_islander,race_native_american,race_other_text," +
			"race_other,demo_low_income,demo_homeless,demo_disability,demo_urban,demo_rural," +
			"demo_youth,demo_adults,demo_seniors,gender_male,gender_female,progtype_comp_center," +
			"progtype_ed_employ,progtype_food,progtype_health,progtype_homeless,progtype_housing,progtype_immigration," +
			"progtype_other,progtype_rehab,progtype_seniors,progtype_spirit_devel,progtype_youth" +
			" FROM org_details ";
		MethodInit("getOrgDetailDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
        switch( aSrchParmObj.getSearchType() ){                      //****may need to change for new DTO -?
        	case OrganizationInfoDTO.LOADBY_PRIMARYPERSON :
        		if(aSrchParmObj.getPersonNumber() < 1){
                	aSrchParmObj.appendErrorMsg("person number required");
        			return -1;
        		}
            	aszSQL2 = aszSQL101 + " WHERE primary_person=" + aSrchParmObj.getPersonNumber() + " "; // --> LEGACY
            	break;
            default:
    			setErr("request type not supported");
            	aSrchParmObj.appendErrorMsg("type not supported");
                return 1;
        }
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			OrganizationDetailsInfoDTO aHeadObj = new OrganizationDetailsInfoDTO();
			aHeadObj.setDETOrgNumber(pConn.getDBInt("org_number"));
			aHeadObj.setDETOrgcodekey(pConn.getDBString("orgcodekey"));
			aHeadObj.setDETPrimaryPerson(pConn.getDBInt("primary_person"));
			aHeadObj.setDETUpdateDt(pConn.getDBTimestamp("update_dt"));
			aHeadObj.setDETUpdateId(pConn.getDBInt("update_id"));
			aHeadObj.setDETFedTaxId(pConn.getDBString("fed_tax_id"));
			aHeadObj.setDETNumVolOrg(pConn.getDBInt("num_vol_org"));
			aHeadObj.setDETNumServed(pConn.getDBInt("num_served"));
			aHeadObj.setDETIs501c3P(pConn.getDBString("is_501c3_p"));
			aHeadObj.setDETOrganizationBudget(pConn.getDBInt("organization_budget"));
			aHeadObj.setDETNumStaffOrg(pConn.getDBInt("num_staff_org"));
			aHeadObj.setDETLanguageArabic(pConn.getDBInt("language_arabic"));
			aHeadObj.setDETLanguageChinese(pConn.getDBInt("language_chinese"));
			aHeadObj.setDETLanguageEnglish(pConn.getDBInt("language_english"));
			aHeadObj.setDETLanguageFrench(pConn.getDBInt("language_french"));
			aHeadObj.setDETLanguageHindi(pConn.getDBInt("language_hindi"));
			aHeadObj.setDETLanguagePortuguese(pConn.getDBInt("language_portuguese"));
			aHeadObj.setDETLanguageSpanish(pConn.getDBInt("language_spanish"));
			aHeadObj.setDETLanguageOtherAText(pConn.getDBString("language_other_a_text"));
			aHeadObj.setDETLanguageOtherA(pConn.getDBInt("language_other_a"));
			aHeadObj.setDETLanguageOtherBText(pConn.getDBString("language_other_b_text"));
			aHeadObj.setDETLanguageOtherB(pConn.getDBInt("language_other_b"));
			aHeadObj.setDETRaceAsian(pConn.getDBInt("race_asian"));
			aHeadObj.setDETRaceBlack(pConn.getDBInt("race_black"));
			aHeadObj.setDETRaceCaucasian(pConn.getDBInt("race_caucasian"));
			aHeadObj.setDETRaceLatino(pConn.getDBInt("race_latino"));
			aHeadObj.setDETRacePacificIslander(pConn.getDBInt("race_pacific_islander"));
			aHeadObj.setDETRaceNativeAmerican(pConn.getDBInt("race_native_american"));
			aHeadObj.setDETRaceOtherText(pConn.getDBString("race_other_text"));
			aHeadObj.setDETRaceOther(pConn.getDBInt("race_other"));
			aHeadObj.setDETDemoLowIncome(pConn.getDBInt("demo_low_income"));
			aHeadObj.setDETDemoHomeless(pConn.getDBInt("demo_homeless"));
			aHeadObj.setDETDemoDisability(pConn.getDBInt("demo_disability"));
			aHeadObj.setDETDemoUrban(pConn.getDBInt("demo_urban"));
			aHeadObj.setDETDemoRural(pConn.getDBInt("demo_rural"));
			aHeadObj.setDETDemoYouth(pConn.getDBInt("demo_youth"));
			aHeadObj.setDETDemoAdults(pConn.getDBInt("demo_adults"));
			aHeadObj.setDETDemoSeniors(pConn.getDBInt("demo_seniors"));
			aHeadObj.setDETGenderMale(pConn.getDBInt("gender_male"));
			aHeadObj.setDETGenderFemale(pConn.getDBInt("gender_female"));
			aHeadObj.setDETProgtypeCompCenter(pConn.getDBInt("progtype_comp_center"));
			aHeadObj.setDETProgtypeEdEmploy(pConn.getDBInt("progtype_ed_employ"));
			aHeadObj.setDETProgtypeFood(pConn.getDBInt("progtype_food"));
			aHeadObj.setDETProgtypeHealth(pConn.getDBInt("progtype_health"));
			aHeadObj.setDETProgtypeHomeless(pConn.getDBInt("progtype_homeless"));
			aHeadObj.setDETProgtypeHousing(pConn.getDBInt("progtype_housing"));
			aHeadObj.setDETProgtypeImmigration(pConn.getDBInt("progtype_immigration"));
			aHeadObj.setDETProgtypeOther(pConn.getDBInt("progtype_other"));
			aHeadObj.setDETProgtypeRehab(pConn.getDBInt("progtype_rehab"));
			aHeadObj.setDETProgtypeSeniors(pConn.getDBInt("progtype_seniors"));
			aHeadObj.setDETProgtypeSpiritDevel(pConn.getDBInt("progtype_spirit_devel"));
			aHeadObj.setDETProgtypeYouth(pConn.getDBInt("progtype_youth"));
			aListObj.add(aHeadObj);
		}
		return iRetCode;
    }
    // end-of method getOrganizationDBList()

*/


    /**
	* delete a row in table org_details
	*/
	public int deleteOrgDetailsFromDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL101 = " DELETE FROM " + aszVolengDB + "org_details WHERE org_nid=? " ;
		MethodInit("deleteOrgDetailsFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}/*
		if(aHeadObj.getDETOrgNumber() < 1){
			setErr("organization id required");
			return -1;
		}*/
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}

		iRetCode = pConn.setPrepQryInt( aHeadObj.getDETOrgNID() );
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
		return 0;
	}
	// end-of method deleteOrgDetailsFromDB()



    /**
	* load an organization's details
	*/
	public int loadOrgDetailFromDB(ABREDBConnection pConn, OrganizationDetailsInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		String aszSQL2=null, aszTemp=null, aszUserName=null ;
		String aszSQL101 = " SELECT " +
			"org_nid, org_number,orgcodekey,primary_person,update_dt,update_id," +
			"fed_tax_id,num_vol_org,num_served,is_501c3_p,organization_budget,num_staff_org," +
			"language_arabic,language_chinese,language_english,language_french,language_hindi,language_portuguese," +
			"language_spanish,language_other_a_text,language_other_a,language_other_b_text,language_other_b,race_asian," +
			"race_black,race_caucasian,race_latino,race_pacific_islander,race_native_american,race_other_text," +
			"race_other,demo_low_income,demo_homeless,demo_disability,demo_urban,demo_rural," +
			"demo_youth,demo_adults,demo_seniors,gender_male,gender_female,progtype_comp_center," +
			"progtype_ed_employ,progtype_food,progtype_health,progtype_homeless,progtype_housing,progtype_immigration," +
			"progtype_other,progtype_rehab,progtype_seniors,progtype_spirit_devel,progtype_youth,affil_agrm," +
			"affil_bilgramevas,affil_breadworld,affil_callren,affil_campcrus,affil_cathcharities,affil_cathcharities_US," +
			"affil_cathmedmiss,affil_cathrelserv,affil_chrisaid,affil_chrischildfnd,affil_ccda,affil_chrislegsoc," +
			"affil_chrisrefna,affil_ctcnet,affil_compassionint,affil_compassionworks,affil_coopbapfel,affil_devos," +
			"affil_evassocpromed,affil_evcovchurch,affil_evluthchamer,affil_evsocialaction,affil_feedchild,affil_focusonfam," +
			"affil_foodforpoor,affil_habitathum,affil_handsonnet,affil_hereslifeinncity,affil_hud,affil_leaderfoundamer," +
			"affil_mapint,affil_mennoncentral,affil_reformedamer,affil_salvarmy,affil_samaritanpurse,affil_chrismissa," +
			"affil_uywi,affil_volamer,affil_worldv,affil_wycliffebib,affil_ymcausa,affil_ymcasus," +
			"affil_younglife,tech_participants,tech_computers,tech_donate,outcome_completion,outcome_certification," +
			"outcome_college,outcome_job,outcome_ged,outcome_discipleship,outcome_believer" +
			" FROM " + aszVolengDB + "org_details " ;
		MethodInit("loadOrgDetailFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
        switch( iType ){                                          //****look at for new DTO -?
        	case OrganizationInfoDTO.LOADBY_ORGNUMBER :
            	if(aHeadObj.getDETOrgNumber() < 1){
            		aHeadObj.appendErrorMsg("organization ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " WHERE org_number=" + aHeadObj.getDETOrgNumber() + " ";
            	break;
        	case OrganizationInfoDTO.LOADBY_ORGNID :
            	if(aHeadObj.getDETOrgNID() < 1){
            		aHeadObj.appendErrorMsg("organization ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " WHERE org_nid=" + aHeadObj.getDETOrgNID() + " "; // 2009-07-20 added field to org_details: org_nid
            	break;

            default:
        		aHeadObj.appendErrorMsg("organization ID required  ");
    			// setErr("request type not supported");
                return 1;
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
		iRetCode=-1;
		if(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setDETOrgNID(pConn.getDBInt("org_nid"));
			aHeadObj.setDETOrgNumber(pConn.getDBInt("org_number"));
			aHeadObj.setDETOrgcodekey(pConn.getDBString("orgcodekey"));
			aHeadObj.setDETPrimaryPerson(pConn.getDBInt("primary_person"));
			aHeadObj.setDETUpdateDt(pConn.getDBTimestamp("update_dt"));
			aHeadObj.setDETUpdateId(pConn.getDBInt("update_id"));
			aHeadObj.setDETFedTaxId(pConn.getDBString("fed_tax_id"), null);
			aHeadObj.setDETNumVolOrg(pConn.getDBInt("num_vol_org"));
			aHeadObj.setDETNumServed(pConn.getDBInt("num_served"));
			aHeadObj.setDETIs501c3P(pConn.getDBString("is_501c3_p"));
			aHeadObj.setDETOrganizationBudget(pConn.getDBInt("organization_budget"));
			aHeadObj.setDETNumStaffOrg(pConn.getDBInt("num_staff_org"));
			aHeadObj.setDETLanguageArabic(pConn.getDBInt("language_arabic"));
			aHeadObj.setDETLanguageChinese(pConn.getDBInt("language_chinese"));
			aHeadObj.setDETLanguageEnglish(pConn.getDBInt("language_english"));
			aHeadObj.setDETLanguageFrench(pConn.getDBInt("language_french"));
			aHeadObj.setDETLanguageHindi(pConn.getDBInt("language_hindi"));
			aHeadObj.setDETLanguagePortuguese(pConn.getDBInt("language_portuguese"));
			aHeadObj.setDETLanguageSpanish(pConn.getDBInt("language_spanish"));
			aHeadObj.setDETLanguageOtherAText(pConn.getDBString("language_other_a_text"));
			aHeadObj.setDETLanguageOtherA(pConn.getDBInt("language_other_a"));
			aHeadObj.setDETLanguageOtherBText(pConn.getDBString("language_other_b_text"));
			aHeadObj.setDETLanguageOtherB(pConn.getDBInt("language_other_b"));
			aHeadObj.setDETRaceAsian(pConn.getDBInt("race_asian"));
			aHeadObj.setDETRaceBlack(pConn.getDBInt("race_black"));
			aHeadObj.setDETRaceCaucasian(pConn.getDBInt("race_caucasian"));
			aHeadObj.setDETRaceLatino(pConn.getDBInt("race_latino"));
			aHeadObj.setDETRacePacificIslander(pConn.getDBInt("race_pacific_islander"));
			aHeadObj.setDETRaceNativeAmerican(pConn.getDBInt("race_native_american"));
			aHeadObj.setDETRaceOtherText(pConn.getDBString("race_other_text"));
			aHeadObj.setDETRaceOther(pConn.getDBInt("race_other"));
			aHeadObj.setDETDemoLowIncome(pConn.getDBInt("demo_low_income"));
			aHeadObj.setDETDemoHomeless(pConn.getDBInt("demo_homeless"));
			aHeadObj.setDETDemoDisability(pConn.getDBInt("demo_disability"));
			aHeadObj.setDETDemoUrban(pConn.getDBInt("demo_urban"));
			aHeadObj.setDETDemoRural(pConn.getDBInt("demo_rural"));
			aHeadObj.setDETDemoYouth(pConn.getDBInt("demo_youth"));
			aHeadObj.setDETDemoAdults(pConn.getDBInt("demo_adults"));
			aHeadObj.setDETDemoSeniors(pConn.getDBInt("demo_seniors"));
			aHeadObj.setDETGenderMale(pConn.getDBInt("gender_male"));
			aHeadObj.setDETGenderFemale(pConn.getDBInt("gender_female"));
			aHeadObj.setDETProgtypeCompCenter(pConn.getDBInt("progtype_comp_center"));
			aHeadObj.setDETProgtypeEdEmploy(pConn.getDBInt("progtype_ed_employ"));
			aHeadObj.setDETProgtypeFood(pConn.getDBInt("progtype_food"));
			aHeadObj.setDETProgtypeHealth(pConn.getDBInt("progtype_health"));
			aHeadObj.setDETProgtypeHomeless(pConn.getDBInt("progtype_homeless"));
			aHeadObj.setDETProgtypeHousing(pConn.getDBInt("progtype_housing"));
			aHeadObj.setDETProgtypeImmigration(pConn.getDBInt("progtype_immigration"));
			aHeadObj.setDETProgtypeOther(pConn.getDBInt("progtype_other"));
			aHeadObj.setDETProgtypeRehab(pConn.getDBInt("progtype_rehab"));
			aHeadObj.setDETProgtypeSeniors(pConn.getDBInt("progtype_seniors"));
			aHeadObj.setDETProgtypeSpiritDevel(pConn.getDBInt("progtype_spirit_devel"));
			aHeadObj.setDETProgtypeYouth(pConn.getDBInt("progtype_youth"));
			
		}
		return iRetCode;
	}
    // end-of method loadOrganizationFromDB()



	//=== END   Table org_details ===>
    //=== END   Table org_details ===>
    //=== END   Table org_details ===>


	/**
	* get pathauto rules for url alias generation
	*/
	public int getPathAutoURLAliasInfo( ABREDBConnection pConn, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
			" name,value " +
			" FROM " + aszDrupalDB + "variable WHERE name LIKE 'pathauto_ignore_words' OR name LIKE 'pathauto_punctuation_%' " +
			" OR name LIKE 'pathauto_separator' OR name LIKE 'pathauto_node_volunteer_opportunity_pattern' " +
			" OR name LIKE 'pathauto_node_organization_pattern' ";
        MethodInit("getPathAutoURLAliasInfo");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			//AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
            if(pConn.getDBString("name").equalsIgnoreCase("pathauto_separator")){
            	if(pConn.getDBString("value").length() > 0){
            		aHeadObj.setPathAutoSeparator(pConn.getDBString("value"));
            	}else{
            		aHeadObj.setPathAutoSeparator("");
            	}
            
            }else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_ignore_words")){
            	aHeadObj.setPathAutoIgnoreWords(pConn.getDBString("value"));
            	
            }else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_ampersand")){
            	aHeadObj.setPathAutoPuncAmp(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_asterisk")){
            	aHeadObj.setPathAutoPuncAstrsk(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_at")){
            	aHeadObj.setPathAutoPuncAt(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_backtick")){
            	aHeadObj.setPathAutoPuncBckTck(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_back_slash")){
            	aHeadObj.setPathAutoPuncBckSlsh(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_caret")){
            	aHeadObj.setPathAutoPuncCaret(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_colon")){
            	aHeadObj.setPathAutoPuncColon(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_comma")){
            	aHeadObj.setPathAutoPuncComma(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_dollar")){
            	aHeadObj.setPathAutoPuncDolrSign(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_double_quotes")){
            	aHeadObj.setPathAutoPuncDblQuotes(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_equal")){
            	aHeadObj.setPathAutoPuncEqual(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_exclamation")){
            	aHeadObj.setPathAutoPuncExclam(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_greater_than")){
            	aHeadObj.setPathAutoPuncGrtr(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_hash")){
            	aHeadObj.setPathAutoPuncHash(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_hyphen")){
            	aHeadObj.setPathAutoPuncHyphen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_curly")){
            	aHeadObj.setPathAutoPuncLftCrly(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_parenthesis")){
            	aHeadObj.setPathAutoPuncLftParen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_left_square")){
            	aHeadObj.setPathAutoPuncLftSq(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_less_than")){
            	aHeadObj.setPathAutoPuncLess(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_percent")){
            	aHeadObj.setPathAutoPuncPerc(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_period")){
            	aHeadObj.setPathAutoPuncPeriod(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_pipe")){
            	aHeadObj.setPathAutoPuncPipe(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_plus")){
            	aHeadObj.setPathAutoPuncPlus(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_question_mark")){
            	aHeadObj.setPathAutoPuncQuest(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_quotes")){
            	aHeadObj.setPathAutoPuncSingleQut(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_curly")){
            	aHeadObj.setPathAutoPuncRtCurly(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_parenthesis")){
            	aHeadObj.setPathAutoPuncRtParen(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_right_square")){
            	aHeadObj.setPathAutoPuncRtSq(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_semicolon")){
            	aHeadObj.setPathAutoPuncSemiColon(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_tilde")){
            	aHeadObj.setPathAutoPuncTilde(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_punctuation_underscore")){
            	aHeadObj.setPathAutoPuncUnderScore(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_node_organization_pattern")){
            	aHeadObj.setPathAutoOrgPattern(pConn.getDBString("value"));
            	
            } else if(pConn.getDBString("name").equalsIgnoreCase("pathauto_node_volunteer_opportunity_pattern")){
            	aHeadObj.setPathAutoOppPattern(pConn.getDBString("value"));
            }
			//aHeadObj.setPathAutoPunctuation(pConn.getDBString("name"));
			//aHeadObj.setPathAutoPunctuationValue(pConn.getDBString("value"));
			//aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getPathAutoURLAliasInfo(ABREDBConnection, OrganizationInfoDTO)()

	/**
	* get url alias'es of urlalias% format
	*/
	public int checkPathAutoURLAliasExisting( ABREDBConnection pConn, ArrayList aListObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0, iIDType=0;
		String aszSQL101 = " SELECT src,dst " +
			" FROM " + aszDrupalDB + "url_alias WHERE dst LIKE '" + aHeadObj.getORGUrlAlias() + "%' ";
        MethodInit("getPathAutoURLAliasInfo");
		if(null == pConn){
			setErr("null database connection");
			aListObj=null;
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			aListObj=null;
			return -1;
		}
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
			AppCodeInfoDTO aUrlAliasObj = new AppCodeInfoDTO();
			aUrlAliasObj.setURLsrc(pConn.getDBString("src"));
			aUrlAliasObj.setURLdst(pConn.getDBString("dst"));
            
            aListObj.add(aUrlAliasObj);
		}
		
		aszSQL101 = " SELECT source,redirect " +
			" FROM " + aszDrupalDB + "path_redirect WHERE source LIKE '" + aHeadObj.getORGUrlAlias() + "%' ";
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
		iRetCode=-1;
		while(pConn.getNextResult()){
	        iRetCode=0;
			AppCodeInfoDTO aUrlAliasObj = new AppCodeInfoDTO();
			aUrlAliasObj.setURLsrc(pConn.getDBString("redirect"));
			aUrlAliasObj.setURLdst(pConn.getDBString("source"));
	        
	        aListObj.add(aUrlAliasObj);
		}
		return iRetCode;
	}
	// end-of method getPathAutoURLAliasInfo(ABREDBConnection, OrganizationInfoDTO)()


	/**
	* get checkPathAutoPortalNameExisting of format
	*/
	public int checkPathAutoPortalNameExisting( ABREDBConnection pConn, ArrayList aListObj, OrganizationInfoDTO aOrgInfoObj ){
		int iRetCode=0, iIDType=0;
		String aszSQL101 = " SELECT name, tid, vid, weight " +
			" FROM " + aszDrupalDB + "term_data WHERE vid = " + vidPortalName + " ";
        MethodInit("checkPathAutoPortalNameExisting");
		if(null == pConn){
			setErr("null database connection");
			aListObj=null;
			return -1;
		}
		if(null == aOrgInfoObj){
			setErr("null input object");
			aListObj=null;
			return -1;
		}
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            iIDType=pConn.getDBInt("tid");
            
            // check that tid doesn't already just exist in reference to the given nid
            
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			
			aHeadObj.setAPCIdSubType(iIDType);
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
			aListObj.add(aHeadObj);
		}
		return iRetCode;
	}
	// end-of method checkPathAutoPortalNameExisting(ABREDBConnection, OrganizationInfoDTO)()

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

	
	
	
	
	
	

	/**
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 * 				BEGIN LEGACY CONTACT CODE
	 */


    /**	LEGACY
	* insert a row into Organization to show ownership of given UID to this VID/NID
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertAddOrgContactIntDB_LEGACY(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj, int iIsVolunteerContact ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		String aszSQLdrupalTemp = "";
		// "auto-"increment the nid and vid in urbmi5_drupal.um_sequences table - then grab last Id...
		// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1;
		String Qry1=null;
		MethodInit("insertOrganizationIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
/*	not sure when i commented this section out, but I think it's necessary...*/	
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
/**/		
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT * FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
			" AND field_volorg_owner_uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			aHeadObj.appendErrorMsg("This user already has administrative privileges on this Organization");
			return 0;
		} 
		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT delta FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
            ErrorsToLog();
			return -1;
		}
		int iDelta=-1;
		int iDeltaMax=-1;
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			iDelta = pConn.getDBInt("delta");
			if(iDeltaMax < iDelta){
				iDeltaMax = iDelta;
			}
		} 
		iDeltaMax++; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		/*
		 * add to um_content_field_volorg_owner
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_volorg_owner(vid, nid, delta, field_volorg_owner_uid) " +
				"VALUES(" + aHeadObj.getORGVID() + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + aIndivObj.getUserUID() + " ) ";
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
		
		/*
		 * add to um_usermail - whether this user receives emails for this organization or not; when iIsVolunteerContact=1, yes (otherwise, 0=no)
		 */
		aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "usermail(uid, component, mail_id, mail) " +
				"VALUES(" + aIndivObj.getUserUID() + ", 'voleng', " + aHeadObj.getORGNID() + "," + iIsVolunteerContact + " ) ";
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
		
		iRetCode=-1;		
			
		return 0;
	}
	// end-of method insertOrgContactIntDB()
	

    /**
	* delete an organizational contact record - LEGACY
	*/
	public int deleteOrgContactFromDB_LEGACY(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj ){
		int iRetCode=0;
		int ilid=-1;
		String Qry1, aszSQL101;
		MethodInit("deleteOrgContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		// delete from um_usermail for voleng mailing notifications
		aszSQL101="DELETE FROM " + aszDrupalDB + "usermail " +
				" WHERE mail_id=" + aHeadObj.getORGNID() + " AND uid=" + aIndivObj.getUserUID() + " AND component='voleng'"; 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		// delete from um_content_field_volorg_owner
		aszSQL101="DELETE FROM " + aszDrupalDB + "content_field_volorg_owner " +
				" WHERE nid=" + aHeadObj.getORGNID() + " AND field_volorg_owner_uid=" + aIndivObj.getUserUID(); 
		iRetCode=pConn.RunUpdate(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method deleteOrgContactFromDB()
	

    /**
	* Reset an Organizational Volunteer Contact record (whether they receive emails or not in the voleng system)
	*/
	public int resetOrgContactFromDB_LEGACY(ABREDBConnection pConn, PersonInfoDTO aIndivObj, OrganizationInfoDTO aHeadObj, int iIsVolunteerContact ){
		int iRetCode=0;
		int ilid=-1;
		String Qry1, aszSQL101;
		MethodInit("deleteOrgContactFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		
		Qry1 = "SELECT * FROM " + aszDrupalDB + "usermail WHERE mail_id=" + aHeadObj.getORGNID() +
			" AND component='voleng' AND uid=" + aIndivObj.getUserUID();
		iRetCode = pConn.RunQry(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
	        ErrorsToLog();
			return -1;
		}
		// Get Next Item From ResultSet
		int iPreviousMailStatus=0;
		while(pConn.getNextResult()){
			aHeadObj.appendErrorMsg("This user already has administrative privileges on this Organization");
			iPreviousMailStatus=pConn.getDBInt("mail");
			if(iPreviousMailStatus==iIsVolunteerContact){
				// the user's contact status is not changing; don't trigger an email or anything
				return -444;
			}
		} 
		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
	
		/*
		 * add to um_usermail - whether this user receives emails for this organization or not; when iIsVolunteerContact=1, yes (otherwise, 0=no)
		 */
		Qry1="UPDATE " + aszDrupalDB + "usermail SET mail = " + iIsVolunteerContact + 
			" WHERE mail_id=" + aHeadObj.getORGNID() + " AND uid=" + aIndivObj.getUserUID(); 
		iRetCode=pConn.PrepQuery(Qry1);
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
		
		return iPreviousMailStatus; // (0= NOW receives emails; 1= NO LONGER receives emails)
	}
	// end-of method resetOrgContactFromDB()


	/**
	* select a list of administrators for an organization
	*/
	public int getOrganizationContactDBList_LEGACY(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0;
		String aszSQL2=null, aszTemp=null, aszEmail=null ; // may take out join with usermail, b/c this now is ORG ADMINS, not org contacts or OPP CONTACTS
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, usermail.mail " +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile uprofile, " + 
			 aszDrupalDB + "users u," + aszDrupalDB + "content_field_volorg_owner contact," + 
			 aszDrupalDB + "usermail usermail  " +
			" WHERE contact.nid=" + aSrchParmObj.getNID() + " AND contact.field_volorg_owner_uid =n.uid " +
			" AND n.type='uprofile' AND n.nid=uprofile.nid  AND contact.field_volorg_owner_uid =u.uid  " +
			" AND usermail.component='voleng' AND usermail.mail_id=" + aSrchParmObj.getNID() + " AND usermail.uid=u.uid"; // placeholder for conditionals set in BLO layer
		MethodInit("getOrganizationContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("contact_uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid, u.mail, usermail.mail " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "content_field_volorg_owner contact ," + 
			 aszDrupalDB + "usermail usermail  " +
			" WHERE contact.nid=" + aSrchParmObj.getNID() + " AND contact.field_volorg_owner_uid =u.uid  " +
			" AND usermail.component='voleng' AND usermail.mail_id=" + aSrchParmObj.getNID() + " AND usermail.uid=u.uid"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iRetCode=0;
	        iContactUID = pConn.getDBInt("contact_uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		
		
		return iRetCode;
    }
    // end-of method getOrganizationContactDBListt()

	

	/**
	* select a list of email contacts for an organization - used for EMAILS
	*/
	public int getOrgVolContactDBList_LEGACY(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iTemp=0, iContactUID=0;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT usermail.mail_id org_nid, uprofile.nid, uprofile.field_first_name_value, " +
			" uprofile.field_last_name_value, u.mail, usermail.mail, u.uid " +
			" FROM " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail, " + aszDrupalDB + "node n, " + 
			aszDrupalDB + "content_type_uprofile uprofile " +
			" WHERE  usermail.mail_id=" + aSrchParmObj.getNID() + 
			" AND usermail.uid=u.uid AND usermail.component='voleng' AND usermail.mail=1 AND n.uid=u.uid AND n.type='uprofile' " +
			" AND n.nid=uprofile.nid"; // placeholder for conditionals set in BLO layer
		MethodInit("getOrgVolContactDBList");
		if(null == pConn) return -1;
    	if(null == aListObj) return -2;
    	if(null == aSrchParmObj) return -2;
		aListObj.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
            PersonInfoDTO aHeadObj = new PersonInfoDTO();

			aHeadObj.setUSPNameFirst(pConn.getDBString("field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("field_last_name_value"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
			aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
			// if they have a first or last name, add them; otherwise, they will be listed by email address
			//if(aHeadObj.getUSPNameFirst().length() > 1 || aHeadObj.getUSPNameLast().length() > 1){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("u.mail"));
				aHeadObj.setUserUID(pConn.getDBInt("u.uid"));
				aHeadObj.setUserProfileNID(pConn.getDBInt("uprofile.nid"));
				aListObj.add(aHeadObj);
			//}
		}
		// grab emails for the contacts who have not been fully voleng-enized and may not have a first name, last name
		aszSQL2 = " SELECT usermail.mail_id org_nid, u.mail, usermail.mail, u.uid " +
			" FROM  " + aszDrupalDB + "users u, " + aszDrupalDB + "usermail usermail  " +
			" WHERE usermail.mail_id=" + aSrchParmObj.getNID() + 
			" AND usermail.uid=u.uid AND usermail.component='voleng' AND usermail.mail=1"; // placeholder for conditionals set in BLO layer
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(aSrchParmObj.getmaxSearchResultRows() > 0){
			iRetCode = pConn.setMaxRows(aSrchParmObj.getmaxSearchResultRows());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		while(pConn.getNextResult()){
	        int iNewUID=-1;
	        aszTemp=null;
	        iRetCode=0;
	        iContactUID = pConn.getDBInt("u.uid");
	        aszEmail = pConn.getDBString("u.mail");
	        for(int i=0; i < aListObj.size(); i++){
	        	PersonInfoDTO aIndivObj = new PersonInfoDTO();
	        	Object aObj = aListObj.get(i);
	    		aIndivObj = (PersonInfoDTO) aObj;
	        	if(aIndivObj.getUserUID()==iContactUID){
	        		iNewUID=iNewUID++;
	        		aszTemp="already exists";
	        	}
	        }
	        if(aszTemp == null ){
		        PersonInfoDTO aHeadObj = new PersonInfoDTO();
				aHeadObj.setUserUID(iContactUID);
				aHeadObj.setUSPEmail1Addr(aszEmail);
				aHeadObj.setIsVolunteerContact(pConn.getDBString("usermail.mail"));// 0=no; 1=yes
		
				// IF aListObj DOES NOT ALREADY INCLUDE THE GIVEN UID, ADD THIS OBJECT TO THE ARRAY LIST
				aListObj.add(aHeadObj);
	        }
		}
		
		
		return iRetCode;
    }
    // end-of method getOrgVolContactDBList()
	
	/**
	* check that the provided user is a contact for this org -- LEGACY, i think
	*/
	public int checkOrgContactDB_LEGACY(ABREDBConnection pConn, int iOrgNID, int iUID){
		int iRetCode=0, iTemp=0, iContactUID=0;
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT contact.field_volorg_owner_uid  contact_uid, contact.nid org_nid " +
			" FROM " + aszDrupalDB + "content_field_volorg_owner contact  " +
			" WHERE contact.nid=" + iOrgNID + " AND contact.field_volorg_owner_uid =" + iUID; 
		MethodInit("getOrganizationDBList");
		if(null == pConn) return -1;
    	if(0 == iOrgNID) return -2;
    	if(0 == iUID) return -2;
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iRetCode=0;
		}
		return iRetCode;
    }
    // end-of method checkOrgContactDB()


    /**
	* set the "Primary Contact" for this organization by making its uid the owner of the actual node
	*/
	public int setOrgPrimaryContactDB_LEGACY(ABREDBConnection pConn, OrganizationInfoDTO aHeadObj, PersonInfoDTO aIndivObj ){
		int iRetCode=0;
		String aszSQLdrupal101 = "";
		String aszSQLdrupalTemp = "";
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int inid=-1, ivid=-1, itid=-1, ilid=-1;
		// initial primary contact - used later for updating "favorites" flag
		int iInitPrimaryContact = aHeadObj.getORGUID();
		String Qry1=null;
		MethodInit("insertOrganizationIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return -1;
		}
		/*
		 * update the drupal owner of the given organization - PRIMARY CONTACT
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node SET uid=? " +
				"WHERE vid=? AND nid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aIndivObj.getUserUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
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
		/*
		 * node_revisions
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "node_revisions SET uid=? " +
				"WHERE vid=? AND nid=?";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aIndivObj.getUserUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
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

		/* assuming that there is already a different owner for an organization; here, we are adjusting the deltas to set this one as primary */
		// grab the delta
		Qry1 = "SELECT * FROM " + aszDrupalDB + "content_field_volorg_owner WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
			" AND field_volorg_owner_uid=" + aIndivObj.getUserUID();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		// Get Next Item From ResultSet
		int isManager = 0; int iMangerDelta=-1;
		while(pConn.getNextResult()){
			isManager=1;
			iMangerDelta = pConn.getDBInt("delta");
			aHeadObj.appendErrorMsg("This user already has administrative privileges on this Organization");
			//return 0;
		} 
		iRetCode=0; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		
		/* assuming that there is already an owner for an organization; here, we are adding an ADDITIONAL delta */
		// grab the delta
		Qry1 = "SELECT delta, field_volorg_owner_uid FROM " + aszDrupalDB + "content_field_volorg_owner " +
			" WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() 
			// + " AND field_volorg_owner_uid!=" + aIndivObj.getUserUID()
			;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(Qry1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		int iDelta=-1;
		int iDeltaMax=-1;
		int iOwnerUID=-1; 
		// Get Next Item From ResultSet
		while(pConn.getNextResult()){
			iDelta = pConn.getDBInt("delta");
			if(iDelta == 0){
				iOwnerUID = pConn.getDBInt("field_volorg_owner_uid");
			}
			if(iDeltaMax < iDelta){
				iDeltaMax = iDelta;
			}
		} 
		iDeltaMax++; //increase delta by one to insert a new row; PRIMARY KEY = (vid, delta)
		// update the previous delta=0 user to be delta=iDeltaMax
		if(iOwnerUID > 0){
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_field_volorg_owner SET delta= " + iDeltaMax +
					" WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
					" AND field_volorg_owner_uid=" + iOwnerUID;
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
		}
		int iTempDeltaNeedsReset = -1;
		// if the user is already in the administrators, but has a delta other than 0, then update it to now be 0
		if(iMangerDelta > 0){
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_field_volorg_owner SET delta=0 " +
					" WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
					" AND field_volorg_owner_uid=" + aIndivObj.getUserUID();
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
			iTempDeltaNeedsReset = 1;
		}else if(isManager==0){
			/*
			 * add to um_content_field_volorg_owner
			 */
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_field_volorg_owner(vid, nid, delta, field_volorg_owner_uid) " +
					"VALUES(" + aHeadObj.getORGVID() + "," + aHeadObj.getORGNID() + "," + iDeltaMax + "," + aIndivObj.getUserUID() + " ) ";
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
			iTempDeltaNeedsReset = 1;
		}
		// update the previous Primary Contact (who now has deltaMax) to have the delta that the new primary contact used to have
		if(iTempDeltaNeedsReset > 0 && iMangerDelta > 0){
			// update the previous delta=0 user to be delta=iDeltaMax
			if(iOwnerUID > 0){
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_field_volorg_owner SET delta= " + iMangerDelta +
						" WHERE vid=" + aHeadObj.getORGVID() +" AND nid=" + aHeadObj.getORGNID() +
						" AND field_volorg_owner_uid=" + iOwnerUID;
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
			}

		}

		/*
		 * make sure the user is set to receive mailings for the given organization
		 */
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "usermail SET mail=1 " +
				" WHERE mail_id=? AND component='voleng' and uid=? ";
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getORGNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aIndivObj.getUserUID() );
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
		
		int iTemp=0;
		ArrayList aList= new ArrayList();
		String aszSQL2=null, aszTemp=null, aszEmail=null ;
		aszSQL2 = " SELECT opplisting.field_volorg_opp_reference_nid opp_nid " +
			" FROM " + aszDrupalDB + "content_field_volorg_opp_reference opplisting  " +
			" WHERE opplisting.nid=" + aHeadObj.getORGNID(); 
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
		iRetCode=-1;
		while(pConn.getNextResult()){
            iTemp=pConn.getDBInt("opp_nid");
            aszTemp = Integer.toString(iTemp);
            aList.add(aszTemp);
		}
		
		// iterate through aList...
		Iterator iterator = aList.iterator();
		
		// stick in a try/catch block to catch the errors on java.util.NoSuchElementException at java.util.AbstractList$Itr.next
		// - this might be skipping the FIRST item from the iterator and then trying to do last+1
		// why does "iterator.hasNext" not catch????
		while(iterator.hasNext()){
			aszTemp=iterator.next().toString();
	        aszSQLdrupal101 = " UPDATE " + aszDrupalDB + "node SET uid=" + aIndivObj.getUserUID() + " WHERE nid=" + aszTemp;
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
			
	        aszSQLdrupal101 = " UPDATE " + aszDrupalDB + "node_revisions SET uid=" + aIndivObj.getUserUID() + " WHERE nid=" + aszTemp;
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

		}


		// especially if done through a portal, then update all the org's favorites to now be favorites of the new Primary Contact rather than of the old
		if(aHeadObj.getPortal()==1){
			// done through a portal, so the favorites have to be updated
			int iInitCount=0,i=0;
			int[] i_aFavNidsArray = new int[1000];
    		// get number of counts of the favorites flag for this content id
    		aszSQL2 = " SELECT content_id " +
				" FROM " + aszDrupalDB + "flag_content fl " +
				" WHERE fid=" + iFlagFavorite + " AND fl.uid=" + iInitPrimaryContact + " AND content_type LIKE 'node' "; 
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -999;
			}
			iRetCode = pConn.RunQuery(aszSQL2);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -999;
			}
			iRetCode=-1;
			// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
			while(pConn.getNextResult()){
				i_aFavNidsArray[iInitCount]=pConn.getDBInt("content_id");
				iInitCount++;
			}
			int[] i_aFavNids = new int[iInitCount];
			
			for(i=0; i < i_aFavNids.length; i++){
	    		int value = i_aFavNidsArray[i];
	    		i_aFavNids[i]=value;
	    	}
	    	i=0;

	    	int iDuplicateEntry=0;
			for(i=0; i < i_aFavNids.length; i++){
				aszSQLdrupal101 = " UPDATE " + aszDrupalDB + "flag_content SET uid=" + aIndivObj.getUserUID() + " " +
					" WHERE fid=" + iFlagFavorite +
		        	" AND content_type LIKE 'node' and uid="+iInitPrimaryContact +" AND content_id="+i_aFavNids[i];
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -1;
				}else{
					//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
					iRetCode = pConn.ExecutePrepQry();
					if(iRetCode == 1062 ){ // then this is a duplicate; 
						// just remove it from favorites of the initial primary contact
						iDuplicateEntry=1;
						aszSQLdrupal101 = " DELETE FROM " + aszDrupalDB + "flag_content " +
							" WHERE fid=" + iFlagFavorite +
				        	" AND content_type LIKE 'node' and uid="+iInitPrimaryContact +" AND content_id="+i_aFavNids[i];
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -1;
					}
				}
				if(iDuplicateEntry==1){
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -1;
					}else{
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						iRetCode = pConn.ExecutePrepQry();
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							//return -1;
						}
					}
					iDuplicateEntry=0;
				}
				// also update the uid for the record in opps_list so that the search results are reflected immediately
				aszSQLdrupal101 = " UPDATE " + aszDataDB + "tbl_opps_list SET uid=" + aIndivObj.getUserUID() + " " +
					" WHERE uid="+iInitPrimaryContact +" AND nid="+i_aFavNids[i];
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -1;
				}else{
					//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
					iRetCode = pConn.ExecutePrepQry();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -1;
					}
				}

			}
		}
		
		
		iRetCode=-1;
		return 0;

	}
	// end-of method setOrgPrimaryContactDB(()
	

/**
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 * 				END LEGACY CONTACT CODE
 */
	
	
	
	public int signSocialGraphContract(ABREDBConnection pConn, PersonInfoDTO user, OrganizationInfoDTO o, int nid) {
		String query = 
			"update um_content_type_organization " +
			"set field_sg_explanation_value = ?, " +
            "field_sg_signature_value = ?, " +
            "field_sg_uid_value = ?, " +
            "field_sg_signature_time_value = unix_timestamp() " +
            "where um_content_type_organization.nid = ?";
	
		if(pConn.PrepQry(query) != 0) return -1;
		
		if(pConn.setPrepQryString(o.getSocialGraphExplanation()) != 0) return -1;
		if(pConn.setPrepQryString(o.getSocialGraphSignature()) != 0) return -1;
		if(pConn.setPrepQryInt(user.getUserUID()) != 0) return -1;
		if(pConn.setPrepQryInt(nid) != 0) return -1;
		
		return pConn.ExeUpdtPrepQry();
	}	
	
	private int updateRequiredDocs(ABREDBConnection pConn, OrgOpportunityInfoDTO opp) {
		if(opp.getRequiredDocumentsToAdd() != null) {
			for(RequiredDocumentDTO doc : opp.getRequiredDocumentsToAdd()) {
				
				// Add To Node Revisions...
				//
				String query = "INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format,log) " +
							   "VALUES(?,?,'','',UNIX_TIMESTAMP({fn now()}),0,'' ) ";
				int ret = pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				// REVIEW: Is this the right value for uid?
				ret = pConn.setPrepQryInt( opp.getOPPUID() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryString( doc.getName() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.ExecutePrepQry();
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				
				// Get vid of node revision we just inserted...
				//
				int vid;
				query = "SELECT LAST_INSERT_ID() ";
				ret = pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				ret = pConn.ExePrepQry();
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				if(pConn.getNextResult()){
					vid = pConn.getDBInt("LAST_INSERT_ID()");
					doc.setVid(vid);
				}
				else
				{
					return -1;
				}
				
				// add to um_node...
				//
				query = "INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate, title, uid, created, changed) " +
					    "VALUES('" + vid + "','required_document','1','2','0',?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
				ret=pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryString( doc.getName() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( opp.getOPPUID() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.ExecutePrepQry();
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				
				
				//	*****  Grab the last auto-incremented ID and save it as the nid for this node *****************
				int nid;
				query = "SELECT LAST_INSERT_ID() ";
				ret = pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				ret = pConn.ExePrepQry();//RunQry(Qry1); - fixed to use ExePrepQry
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					nid = pConn.getDBInt("LAST_INSERT_ID()");
					doc.setNid(nid);
				} else {
					return -1;
				}
				
				/*
				 * add correct nid to node_revisions
				 */
				query = "UPDATE " + aszDrupalDB + "node_revisions SET nid=? " +
						"WHERE vid=?";
				ret = pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( doc.getNid() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( doc.getVid() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.ExecutePrepQry();
				if(0 != ret) {
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}

				// TODO: do I need to do anything with node_access table
				/*
				// add to um_node_access
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
						"VALUES("+ inid +", '0', 'all', '1', '0', '0') ";
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
				*/
				
				query = "INSERT INTO " + aszDrupalDB + "content_type_application_documents(vid, nid, field_opp_nid_value, field_file_extension_value) " +
					    "VALUES(?,?,?,?)";
				ret = pConn.PrepQuery(query);
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( doc.getVid() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
				    ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( doc.getNid() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryInt( opp.getOPPNID() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.setPrepQryString( doc.getExtension() );
				if(0 != ret){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				ret = pConn.ExecutePrepQry();
				if(0 != ret) {
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
		}
		
		if(opp.getRequiredDocumentToRemoveNids() != null 
		&& opp.getRequiredDocumentToRemoveNids().size() > 0) {
			StringBuilder sb = new StringBuilder();
			String delim = "";
		    for (int i : opp.getRequiredDocumentToRemoveNids()) {
		        sb.append(delim).append(i);
		        delim = ",";
		    }
			String query = "update " + aszDrupalDB + "node set status='0', changed=UNIX_TIMESTAMP(NOW()) where nid IN(" + sb.toString() +")";
			int ret = pConn.PrepQuery(query);
			if(0 != ret){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			ret = pConn.ExecutePrepQry();
			if(0 != ret) {
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}
		
		return 0;
	}
	
	// indicate the database if necessary
	//private String aszDrupalDB = "urbmi5_drupal.um_";
	private static final String aszDrupalDB = "um_";
	private static final String aszDrupalDBname = "";
	private static final String aszDataDB = "urbmi5_data.";
	private static final String aszFeedsDB = "techmi5_drufeeds.";
	private static final String aszVolengDB = "techmi5_voleng.";
	private static final String aszSocialGraphDB = "techmi5_socgraph.";
	private static final String aszOppsTable = "tbl_opportunities";
	private static final String aszOrgsTable = "tbl_organizations";
	private static final String aszFeedsTable = "tbl_feeds";
	private static final String aszFeedSourceTable = "tbl_opportunity_sources";
	//private String aszVolengDB = "";
	private static final int iMeetTheNeedUID = 59185;
//	private static final int iMeetTheNeedNID = 67307;
	private static final int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, vidDenomAffil=19, vidOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48, 
		vidWorkStudy=264, vidTripLength=263, vidPosFreq=268, vidSchedDate=269, vidFaith=281, vidBenefits=286,
		vidCauseTopic = 8,
		vidState=52, vidCity=15, vidCountry=261,// vidSubRegion=261, 
		vidRegion=38, 
		vidIntlVols=342, vidFaithSpec=341, vidPortalName=343, vidPrimaryOppTypes=349;
	private static final int iNonFaith = 9397;
	private static final int iNoFaithActivity = 21996, iFaithBased = 21997, iFaithFilled = 21998;
	// vidBenefits=286
	private static final int iRoomBoardTID=11546, iStipendTID=11547, iMedInsurTID=11548, iTransportTID=11549, iAmeriCorpsTID=11550, iWorkStudyBenefitsTID=33192;
	// vidWorkStudy=264
	private static final int iWorkStudyYesTID=8104, iWorkStudyNoTID=8103;
	private static final int iJob=33389;
	private static final String aszJob="Job";
	// vidVolInfo=33
	private static final int iGroup=4793, iFamily=7536, iKid=4790, iTeen=4791, iSenior=4792;
	// also populate with the position type of stm for these orgs
	private static final int iGenSTMtid=32225, iDomSTMtid=32163, iIntlSTMtid=32164, iSTMtid=4796; 
	private static final int iOrgVirtTID=32165, iOppVirtTID=4795; 

	private static final int iExpirationTime = 370; //number of days for expiration status to be set
	private static final int iWarningTime = 30; //number of days until expiration - warning

	private static final int iOneWk = 1209600;//604800;
	//iOneWk = 1123200;
	private Date today = new java.util.Date();
	private long tmplocalUnixTime = today.getTime(); 
	private long localUnixTime = (today.getTime()/1000); // adjust from ms to sec + adjust for GMT comparison
	private long lOneWkAgo = localUnixTime - (iOneWk); // 7-8 days ago in unix time

	private int iFlagFavorite=1;
	String aszDomMain = "www.christianvolunteering.org";//this.getSitePropertyValue(ABREAppServerDef.APP_MAIN_DOMAIN);
	private static final int iRemoved=1, iAdded=2, iNowGetsEmails=3, iNoLongerGetsEmails=4, iNowIsOppContact=5, iNoLongerIsOppContact=6, 
		iNowEmailAndContact=7, iNoLongerEmailOrContact=8, iNewPrimaryContact=9, iNoLongerPrimaryContact=10, iNowIsORGContact=11, iNoLongerIsORGContact=12,
		iRemovedAdmin=21, iAddedAdmin=22; 
	private static final int iNowIsOrgContactLegacy = 80, iNoLongerOrgContact=81, iNowGetsEmailsLegacy=82, iNoLongerGetsEmailsLegacy=83;
	
	public int loadRequiredDocumentFromDB(ABREDBConnection pConn, RequiredDocumentDTO doc, int nid) {
		if(null == pConn) return -1;
    	if(null == doc) return -2;
    	
		String query = " SELECT n.title, n.nid, n.vid, doc.field_opp_nid_value, doc.field_file_extension_value " +
			  	       " FROM " + aszDrupalDB + "content_type_application_documents AS doc " +
				       " INNER JOIN " + aszDrupalDB + "node AS n ON doc.nid = n.nid " +
				       " WHERE n.nid='" + nid + "'";
		int ret = pConn.getDBStmt();
		if(0 != ret){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		ret = pConn.RunQuery(query);
		if(0 != ret){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		if(pConn.getNextResult()) {
			doc.setNid(pConn.getDBInt("nid"));
			doc.setVid(pConn.getDBInt("vid"));
			doc.setName(pConn.getDBString("title"));
			doc.setOppNid(pConn.getDBInt("field_opp_nid_value"));
			doc.setExtension(pConn.getDBString("field_file_extension_value"));
		}
		else
			return -3;
		return 0;
	}
}