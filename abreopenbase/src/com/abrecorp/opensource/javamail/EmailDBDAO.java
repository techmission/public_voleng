package com.abrecorp.opensource.javamail;

/**
* Code Generated DataStore Class
* For Table organizationinfo
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.AppCodeInfoDTO;
import com.abrecorp.opensource.dataobj.ApplicationInfoDTO;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationDetailsInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.SearchParms;


import java.util.ArrayList;

class EmailDBDAO extends ABREBase {

	/**
	** Constructor
	*/
    public EmailDBDAO(){}



    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>
    //=== START Table emailinfo ===>

    
	/**
	* delete a row from table emailinfo
	*/
	public int deleteEmailFromDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL=null;
		String aszSQL101 = "DELETE FROM " + aszVolengDB + "emailinfo WHERE email_id= " ;
		MethodInit("deleteDBEML");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(aHeadObj.getEmailId() < 1){
			setErr("email id required");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
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
	// end-of method deleteEmailFromDB()


    /**
	* insert a row into table emailinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int logApplicationDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL101 = " INSERT INTO " + aszVolengDB + "application_inits ( " +
		"email_id, org_nid, opp_nid, vol_uid, CREATE_DT, current_status, user_agent, referrer, remote_ip)" +
		" values(?,?,?,?, {fn now()},?,?,?,?)" ;
		MethodInit("logApplicationDB");	
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

		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOrgNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOppNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailVolUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSentStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailUserAgent() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailReferrer() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailRemoteIP() );
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
	// end-of method logApplicationDB()


	/**
	* updateLogApplicationDB a row in table emailinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateLogApplicationDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE " + aszVolengDB + "application_inits SET " +
			" current_status=?" +
			" WHERE email_id=? ";
		MethodInit("updateDBEmail");
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
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSentStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
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
	// end-of method updateLogApplicationDB()



    /**
	* insert a row into table emailinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertEmailIntDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL101 = " INSERT INTO " + aszVolengDB + "emailinfo ( " +
		"email_id, org_nid, opp_nid, vol_uid, volunteer_id, opp_id, org_id, create_dt, sent_dt" +
		", sent_status, org_name, opp_name, to_email, from_email, contact_fn" +
		", contact_ln, org_addr1, org_city, org_state, org_prov, org_country" +
		", org_phone, org_web, email_subject, email_bodytxt_intro, email_bodytxt, email_bodytxt_closing" +
		", email_bodytxt_res, vol_firstname, vol_lastname, vol_phone, vol_chris, vol_skills" +
		", vol_skills2, vol_skills3, subdom,cover_letter_file,application_file,other_docs)" +
		" values(?,?,?,?,?,?,?, {fn now()} , {fn now()} ,?,?,?,?,?" +
		",?,?,?,?,?,?,?,?,?,?,?" +
		",?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		MethodInit("insertEmailIntDB");
		
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

		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOrgNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOppNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailVolUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailVolunteerId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOppId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOrgId() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSentStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOppName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailToUser() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailFromUser() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailContactFN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailContactLN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgAddr1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgState() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgProv() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgCountry() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgWeb() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSubjectText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextIntro() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( pConn.replacesinglequote(aHeadObj.getEmailBodyText()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextClosing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextRes() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolFN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolLN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolPhone1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolChris() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills3() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCoverLetterFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getApplicationFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOtherDocsFileName() );
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
		
		// get tid for portal if portal exists.  if portal exists, update row just entered and add the Portal Name + the Portal TID
		/*
alter table techmi5_voleng.emailinfo add portal_name VARCHAR(255);
alter table techmi5_voleng.emailinfo add portal_tid int(10) unsigned;
		 */
		if(aHeadObj.getPortalName().length()>0){
			int itid=0;
			aszSQL101 = " SELECT tid FROM " + aszDrupalDB + "term_data WHERE vid=" + vidPortalName + " AND name LIKE '" +  aHeadObj.getPortalName() + "'";
			
			iRetCode = pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
//				return -1;
			}
			iRetCode = pConn.ExePrepQry();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
//				return -1;
			}
			if(pConn.getNextResult()){
	            itid=(pConn.getDBInt("tid"));
			}
			
			aszSQL101 = 
				" UPDATE " + aszVolengDB + "emailinfo SET portal_name='" + aHeadObj.getPortalName() + "', " +
						" portal_tid=" + itid + " " +
						" WHERE email_id=" + aHeadObj.getEmailId();
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExecutePrepQry();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
//				return -1;
			}
		}

		return 0;
	}
	// end-of method insertEmailIntDB()

	/**
	* update a row in table emailinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE " + aszVolengDB + "emailinfo SET " +
			"org_nid=?,opp_nid=?,vol_uid=?,sent_dt={fn now()},sent_status=?,org_name=?" +
			" ,opp_name=?,to_email=?,from_email=?,contact_fn=?,contact_ln=?,org_addr1=?" +
			" ,org_city=?,org_state=?,org_prov=?,org_country=?,org_phone=?,org_web=?" +
			" ,email_subject=?,email_bodytxt_intro=?,email_bodytxt=?,email_bodytxt_closing=?,email_bodytxt_res=?,vol_firstname=?" +
			" ,vol_lastname=?,vol_phone=?,vol_chris=?,vol_skills=?,vol_skills2=?,vol_skills3=?,subdom=?,cover_letter_file=?," +
			"application_file=?, other_docs=? " +
			" WHERE email_id=? ";
		MethodInit("updateDBEmail");
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOrgNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOppNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailVolUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSentStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOppName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailToUser() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailFromUser() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailContactFN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailContactLN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgAddr1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgCity() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgState() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgProv() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgCountry() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgPhone() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailOrgWeb() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSubjectText() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextIntro() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( pConn.replacesinglequote(aHeadObj.getEmailBodyText()) );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextClosing() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailBodyTextRes() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolFN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolLN() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolPhone1() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolChris() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills2() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailVolSkills3() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCoverLetterFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getApplicationFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOtherDocsFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}

		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
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
	// end-of method updateDBEmail()

	/**
	* updateStatusDBEmail a row in table emailinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateStatusDBEmail(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101 = "UPDATE " + aszVolengDB + "emailinfo SET " +
			"org_nid=?,opp_nid=?,vol_uid=?,sent_status=?, cover_letter_file=?," +
			"application_file=?, other_docs=? " +
			" WHERE email_id=? ";
		MethodInit("updateDBEmail");
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
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOrgNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailOppNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailVolUID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getEmailSentStatus() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getCoverLetterFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getApplicationFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getOtherDocsFileName() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}


		iRetCode = pConn.setPrepQryInt( aHeadObj.getEmailId() );
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
	// end-of method updateStatusDBEmail()

    /**
	* load list of emails
	*/
	public int getDBListEmail(ABREDBConnection pConn, ArrayList aListObj, EmailInfoDTO aHeadObj, int iType){
		int iRetCode=0;
		String aszSQL2=null, aszTemp=null, aszUserName=null ;
		String aszSQL101 = " SELECT " +
			"email_id, org_nid, opp_nid, vol_uid, create_dt, org_name, opp_name, to_email, from_email,sent_status," +
			"org_city, org_state, org_country, org_phone,org_web," +
			"vol_firstname, vol_lastname, vol_phone,vol_chris,subdom, cover_letter_file, application_file, other_docs" +
			" FROM " + aszVolengDB + "emailinfo WHERE org_nid > 0" ;
		MethodInit("getDBListEmail");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
        switch( iType ){  
        	case EmailInfoDTO.LOADBY_VOLUNTEER :
            	if(aHeadObj.getEmailVolUID() < 1){
            		aHeadObj.appendErrorMsg("volunteer user ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " AND vol_uid=" + aHeadObj.getEmailVolUID() + " ORDER BY email_id DESC ";
            	break;
        	case EmailInfoDTO.LOADBY_VOLUNTEER_MAX :
            	if(aHeadObj.getEmailVolUID() < 1){
            		aHeadObj.appendErrorMsg("volunteer user ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " AND vol_uid=" + aHeadObj.getEmailVolUID() + " " +
            			"	AND create_dt >= DATE_SUB(CURDATE(), INTERVAL " + aHeadObj.getEmailMaxInterval() + ") ORDER BY email_id DESC ";
            	break;
        	case EmailInfoDTO.LOADBY_ORGANIZATION :
            	if(aHeadObj.getEmailOrgNID() < 1){
            		aHeadObj.appendErrorMsg("org node ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " AND org_nid=" + aHeadObj.getEmailOrgNID() + " ORDER BY email_id DESC ";
            	break;
        	case EmailInfoDTO.LOADBY_OPPORTUNITY :
            	if(aHeadObj.getEmailOppNID() < 1){
            		aHeadObj.appendErrorMsg("opp node ID required  ");
            		return -1;
            	}
            	aszSQL2 = aszSQL101 + " AND opp_nid=" + aHeadObj.getEmailOppNID() + " ORDER BY email_id DESC ";
            	break;
            default:
        		aHeadObj.appendErrorMsg("email ID required  ");
    			// setErr("request type not supported");
                return 1;
        }
        aszSQL2 += " LIMIT 500 ";
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
			EmailInfoDTO aEmailObj = new EmailInfoDTO();
			aEmailObj.setEmailId(pConn.getDBInt("email_id"));
			aEmailObj.setEmailCreateDt(pConn.getDBDate("create_dt"));
			aEmailObj.setEmailSentStatus(pConn.getDBString("sent_status"));
			aEmailObj.setEmailSubdom(pConn.getDBString("subdom"));

			// Organization/Opportunity information
			aEmailObj.setEmailOrgNID(pConn.getDBInt("org_nid"));
			aEmailObj.setEmailOppNID(pConn.getDBInt("opp_nid"));
			aEmailObj.setEmailOrgName(pConn.getDBString("org_name"));
			aEmailObj.setEmailOppName(pConn.getDBString("opp_name"));
			aEmailObj.setEmailToUser(pConn.getDBString("to_email"));
			aEmailObj.setEmailOrgCity(pConn.getDBString("org_city"));
			aEmailObj.setEmailOrgState(pConn.getDBString("org_state"));
			aEmailObj.setEmailOrgCountry(pConn.getDBString("org_country"));
			aEmailObj.setEmailOrgPhone(pConn.getDBString("org_phone"));
			aEmailObj.setEmailOrgWeb(pConn.getDBString("org_web"));

			// Volunteer information
			aEmailObj.setEmailVolUID(pConn.getDBInt("vol_uid"));
			aEmailObj.setEmailFromUser(pConn.getDBString("from_email"));
			aEmailObj.setEmailVolFN(pConn.getDBString("vol_firstname"));
			aEmailObj.setEmailVolLN(pConn.getDBString("vol_lastname"));
			aEmailObj.setEmailVolPhone1(pConn.getDBString("vol_phone"));

			aEmailObj.setCoverLetterFileName(pConn.getDBString("cover_letter_file"));
			aEmailObj.setApplicationFileName(pConn.getDBString("application_file"));
			aEmailObj.setOtherDocsFileName(pConn.getDBString("other_docs"));

			aListObj.add(aEmailObj);
		}
		return iRetCode;
	}
    // end-of method getDBListEmail()

	/**
	* select a list of taxonomy codes from table taxonomy um_term_data
	*/
	public int getTaxonomyCodeListFromDB(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0, iIDType=0 ;
		String aszSQL2=null ;
		String aszSQL101 = " SELECT " +
			"tid,vid,name,description,weight" +
			" FROM " + aszDrupalDB + "term_data ";
        MethodInit("getAppCodeListFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			case AppCodeInfoDTO.GET_BY_DISPLAYID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY name ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_TYPEID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY tid ASC " ;
				break;
			case AppCodeInfoDTO.GET_BY_SORTID :
				if(aSrchParmObj.getSearchOrder() < 1){
					aSrchParmObj.appendErrorMsg("id-type required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE vid=" + aSrchParmObj.getSearchOrder() + " ORDER BY weight,name ASC " ;
				aSrchParmObj.setmaxSearchResultRows(255); // so that it can load a long list, like Languages taxonomy
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
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
			AppCodeInfoDTO aHeadObj = new AppCodeInfoDTO();
			aHeadObj.setAPCIdType(pConn.getDBInt("vid"));
			aHeadObj.setAPCIdSubType(pConn.getDBInt("tid"));
			aHeadObj.setAPCIdSort(pConn.getDBInt("weight"));
			aHeadObj.setAPCKeycode(pConn.getDBString("name"));
			aHeadObj.setAPCDisplay(pConn.getDBString("name"));
			aHeadObj.setAPCDescription(pConn.getDBString("name"));
			aHeadObj.setAPCFunctionalArea(pConn.getDBString("name"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getAppCodeListFromDB()


	/**
	* select one item from table emailinfo
	*/
	public int getEmailFromDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj){
		return getEmailFromDB(pConn, aHeadObj, 0);
	}
	public int getEmailFromDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iType){
		int iRetCode=0,iCount=0;
		MethodInit("getEmailFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}

		String aszSQL2 = " SELECT " +
			"email_id,org_nid,opp_nid,vol_uid,volunteer_id,opp_id,org_id,create_dt,sent_dt," +
			"sent_status,org_name,opp_name,to_email org_email,from_email vol_email,contact_fn," +
			"contact_ln,org_addr1,org_city,org_state,org_prov,org_country," +
			"org_phone,org_web,email_subject,email_bodytxt_intro,email_bodytxt,email_bodytxt_closing," +
			"email_bodytxt_res,vol_firstname,vol_lastname,vol_phone,vol_chris,vol_skills," +
			"vol_skills2,vol_skills3,subdom,cover_letter_file, application_file, other_docs " +
			" FROM " + aszVolengDB + "emailinfo " +
			" WHERE opp_nid=" + aHeadObj.getEmailOppNID() + " AND vol_uid=" + aHeadObj.getEmailVolUID() + 
			" AND sent_status  LIKE 'sent%'";// took out the NOT --> only want to return results for cases that the application successfully sent
		
		if(iType==1){
			aszSQL2 = " SELECT " +
					"email_id,org_nid,opp_nid,vol_uid,volunteer_id,opp_id,org_id,create_dt,sent_dt," +
					"sent_status,org_name,opp_name,to_email org_email,from_email vol_email,contact_fn," +
					"contact_ln,org_addr1,org_city,org_state,org_prov,org_country," +
					"org_phone,org_web,email_subject,email_bodytxt_intro,email_bodytxt,email_bodytxt_closing," +
					"email_bodytxt_res,vol_firstname,vol_lastname,vol_phone,vol_chris,vol_skills," +
					"vol_skills2,vol_skills3,subdom,cover_letter_file, application_file, other_docs " +
					" FROM " + aszVolengDB + "emailinfo " +
					" WHERE email_id=" + aHeadObj.getEmailId() + " ";
		}
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=0;
		if(pConn.getNextResult()){
			iRetCode=1;
			aHeadObj.setEmailId(pConn.getDBInt("email_id"));
			aHeadObj.setEmailOrgNID(pConn.getDBInt("org_nid"));
			aHeadObj.setEmailOppNID(pConn.getDBInt("opp_nid"));
			aHeadObj.setEmailVolUID(pConn.getDBInt("vol_uid"));
			aHeadObj.setEmailVolunteerId(pConn.getDBInt("volunteer_id"));
			aHeadObj.setEmailOppId(pConn.getDBInt("opp_id"));
			aHeadObj.setEmailOrgId(pConn.getDBInt("org_id"));
			//aHeadObj.setEmailCreateDt(pConn.getDBTimestamp("create_dt"));
			aHeadObj.setEmailSentDt(pConn.getDBTimestamp("sent_dt"));
			aHeadObj.setEmailSentStatus(pConn.getDBString("sent_status"));
			aHeadObj.setEmailOrgName(pConn.getDBString("org_name"));
			aHeadObj.setEmailOppName(pConn.getDBString("opp_name"));
			aHeadObj.setEmailOrg(pConn.getDBString("org_email"));
			aHeadObj.setEmailFromUser(pConn.getDBString("vol_email"));
			aHeadObj.setEmailContactFN(pConn.getDBString("contact_fn"));
			aHeadObj.setEmailContactLN(pConn.getDBString("contact_ln"));
			aHeadObj.setEmailOrgAddr1(pConn.getDBString("org_addr1"));
			aHeadObj.setEmailOrgCity(pConn.getDBString("org_city"));
			aHeadObj.setEmailOrgState(pConn.getDBString("org_state"));
			aHeadObj.setEmailOrgProv(pConn.getDBString("org_prov"));
			aHeadObj.setEmailOrgCountry(pConn.getDBString("org_country"));
			aHeadObj.setEmailOrgPhone(pConn.getDBString("org_phone"));
			aHeadObj.setEmailOrgWeb(pConn.getDBString("org_web"));
			aHeadObj.setEmailSubjectText(pConn.getDBString("email_subject"));
			aHeadObj.setEmailBodyTextIntro(pConn.getDBString("email_bodytxt_intro"));
			aHeadObj.setEmailBodyText(pConn.getDBString("email_bodytxt"));
			aHeadObj.setEmailBodyTextClosing(pConn.getDBString("email_bodytxt_closing"));
			aHeadObj.setEmailBodyTextRes(pConn.getDBString("email_bodytxt_res"));
			aHeadObj.setEmailVolFN(pConn.getDBString("vol_firstname"));
			aHeadObj.setEmailVolLN(pConn.getDBString("vol_lastname"));
			aHeadObj.setEmailVolPhone1(pConn.getDBString("vol_phone"));
			aHeadObj.setEmailVolChris(pConn.getDBString("vol_chris"));
			aHeadObj.setEmailVolSkills(pConn.getDBString("vol_skills"));
			aHeadObj.setEmailVolSkills2(pConn.getDBString("vol_skills2"));
			aHeadObj.setEmailVolSkills3(pConn.getDBString("vol_skills3"));
			aHeadObj.setEmailSubdom(pConn.getDBString("subdom"));
			aHeadObj.setCoverLetterFileName(pConn.getDBString("cover_letter_file"));
			aHeadObj.setApplicationFileName(pConn.getDBString("application_file"));
			aHeadObj.setOtherDocsFileName(pConn.getDBString("other_docs"));
		}
		return iRetCode;
	}
	// end-of method getDBItemEmail()

	/**
	* select getEmailIdDB emailinfo
	*/
	public int getEmailIdDB(ABREDBConnection pConn, EmailInfoDTO aHeadObj, int iMinute){
		int iRetCode=0,iCount=0;
		MethodInit("getEmailFromDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		int iTime = 0;
		if(iMinute > 0){
			iTime = iMinute;
		}

		String aszSQL2 = " SELECT " +
			"email_id " +
			" FROM " + aszVolengDB + "application_inits " +
			" WHERE opp_nid=" + aHeadObj.getEmailOppNID() + " AND vol_uid=" + aHeadObj.getEmailVolUID() + 
			" AND CREATE_DT >= NOW() - INTERVAL " + iTime + " MINUTE";
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=1;
		if(pConn.getNextResult()){
			iRetCode=0;
			aHeadObj.setEmailId(pConn.getDBInt("email_id"));
		}
		return iRetCode;
	}
	// end-of method getEmailIdDB()

	/**
	* select a list of items from table emailinfo
	*/
/*	public int getDBListEmail(ABREDBConnection pConn, ArrayList aListObj, SearchParms aSrchParmObj){
		int iRetCode=0,iCount=0;
		String aszSQL2=null, aszTemp=null ;
		String aszSQL101 = " SELECT " +
			"email_id,org_nid,opp_nid,vol_uid,volunteer_id,org_id,create_dt,sent_dt," +
			"sent_status,org_name,opp_name,org_email,vol_email,contact_fn," +
			"contact_ln,org_addr1,org_city,org_state,org_prov,org_country," +
			"org_phone,org_web,email_subject,email_bodytxt_intro,email_bodytxt,email_bodytxt_closing," +
			"email_bodytxt_res,vol_firstname,vol_lastname,vol_phone,vol_chris,vol_skills," +
			"vol_skills2,vol_skills3,subdom" +
			" FROM " + aszVolengDB + "emailinfo ";
		MethodInit("getDBListEmail");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aListObj){
			setErr("null input Vector object");
			return -1;
		}
		if(null == aSrchParmObj){
			setErr("null input search parameter object");
			return -1;
		}
		aListObj.clear();
		switch(aSrchParmObj.getSearchType()){
			// all items
			case 1:
				aszTemp = aSrchParmObj.getSearchKey() ;
				if(aszTemp.length() < 3){
					aSrchParmObj.appendErrorMsg("minimum 3 character required");
					return -1;
				}
				aszSQL2 = aszSQL101 + " WHERE UPPER(FIELD_NAME) LIKE ('%" + aszTemp + "%')" ;
				break;
			default:
				setErr("type not supported");
				aSrchParmObj.appendErrorMsg("type not supported");
				return -1;
		}

		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
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
		while(pConn.getNextResult()){
			EmailInfoDTO aHeadObj = new EmailInfoDTO();
			
			aHeadObj.setEmailId(pConn.getDBInt("email_id"));
			aHeadObj.setEmailOrgNID(pConn.getDBInt("org_nid"));
			aHeadObj.setEmailOppNID(pConn.getDBInt("opp_nid"));
			aHeadObj.setEmailVolUID(pConn.getDBInt("vol_uid"));
			aHeadObj.setEmailVolunteerId(pConn.getDBInt("volunteer_id"));
			aHeadObj.setEmailOppId(pConn.getDBInt("opp_id"));
			aHeadObj.setEmailOrgId(pConn.getDBInt("org_id"));
			//aHeadObj.setEmailCreateDt(pConn.getDBTimestamp("create_dt"));
			aHeadObj.setEmailSentDt(pConn.getDBTimestamp("sent_dt"));
			aHeadObj.setEmailSentStatus(pConn.getDBString("sent_status"));
			aHeadObj.setEmailOrgName(pConn.getDBString("org_name"));
			aHeadObj.setEmailOppName(pConn.getDBString("opp_name"));
			aHeadObj.setEmailOrg(pConn.getDBString("org_email"));
			aHeadObj.setEmailFromUser(pConn.getDBString("vol_email"));
			aHeadObj.setEmailContactFN(pConn.getDBString("contact_fn"));
			aHeadObj.setEmailContactLN(pConn.getDBString("contact_ln"));
			aHeadObj.setEmailOrgAddr1(pConn.getDBString("org_addr1"));
			aHeadObj.setEmailOrgCity(pConn.getDBString("org_city"));
			aHeadObj.setEmailOrgState(pConn.getDBString("org_state"));
			aHeadObj.setEmailOrgProv(pConn.getDBString("org_prov"));
			aHeadObj.setEmailOrgCountry(pConn.getDBString("org_country"));
			aHeadObj.setEmailOrgPhone(pConn.getDBString("org_phone"));
			aHeadObj.setEmailOrgWeb(pConn.getDBString("org_web"));
			aHeadObj.setEmailSubjectText(pConn.getDBString("email_subject"));
			aHeadObj.setEmailBodyTextIntro(pConn.getDBString("email_bodytxt_intro"));
			aHeadObj.setEmailBodyText(pConn.getDBString("email_bodytxt"));
			aHeadObj.setEmailBodyTextClosing(pConn.getDBString("email_bodytxt_closing"));
			aHeadObj.setEmailBodyTextRes(pConn.getDBString("email_bodytxt_res"));
			aHeadObj.setEmailVolFN(pConn.getDBString("vol_firstname"));
			aHeadObj.setEmailVolLN(pConn.getDBString("vol_lastname"));
			aHeadObj.setEmailVolPh(pConn.getDBString("vol_phone"));
			aHeadObj.setEmailVolChris(pConn.getDBString("vol_chris"));
			aHeadObj.setEmailVolSkills(pConn.getDBString("vol_skills"));
			aHeadObj.setEmailVolSkills2(pConn.getDBString("vol_skills2"));
			aHeadObj.setEmailVolSkills3(pConn.getDBString("vol_skills3"));
			aHeadObj.setEmailSubdom(pConn.getDBString("subdom"));
			aListObj.add(aHeadObj);
		}
		return 0;
	}
	// end-of method getDBListEmail()
*/

	//=== END   Table emailinfo ===>
	//=== END   Table emailinfo ===>
	//=== END   Table emailinfo ===>

	
	
	
	

	
	
	
	
	

	public int loadApplicEmailFromDB(ABREDBConnection pConn, EmailInfoDTO aEmailObj, int iIdNum ){
		int iRetCode=0;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		String aszTemp="", aszNID="",aszSQLtemp ="";
        MethodInit("loadApplicEmailFromDB");
		if(null == pConn) return -1;
		if(null == aEmailObj) return -1;
		boolean feed=false, b_natlassoc=false;
		String mainDB=aszDrupalDB;
		String aszSQL101 = 
				"SELECT vid, nid, " +
				" field_email_subject_value, field_email_body_value, field_email_closing_value, " +
				" field_email_intro_value, field_email_body_format," +
				" field_email_to_value,field_email_bcc_value," +
				" field_email_from_value,field_email_from_display_name_value, field_email_trigger_days_value " +
				"FROM  " + mainDB + "content_type_cvintern_applic_emails  " + 
				" WHERE nid=" + iIdNum ;

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
            aEmailObj.setEmailId(pConn.getDBInt("nid"));
            aEmailObj.setNumDaysTrigger(pConn.getDBInt("field_email_trigger_days_value"));
			aEmailObj.setEmailToUser(pConn.getDBString("field_email_to_value"));
			aEmailObj.setEmailBCCAddress(pConn.getDBString("field_email_bcc_value"));
			aEmailObj.setEmailFromUser(pConn.getDBString("field_email_from_value"));
			aEmailObj.setEmailFromUserName(pConn.getDBString("field_email_from_display_name_value"));
			aEmailObj.setEmailSubjectText(pConn.getDBString("field_email_subject_value"));
			aEmailObj.setEmailBodyTextIntro(pConn.getDBString("field_email_intro_value"));
			aEmailObj.setEmailBodyText(pConn.getDBString("field_email_body_value"));
			aEmailObj.setEmailBodyTextClosing(pConn.getDBString("field_email_closing_value"));
		}


System.out.println("iRetCode is "+iRetCode);		
		return iRetCode;
	}
	// end-of method loadApplicEmailFromDB()


	public int loadApplicEmailFlagFromDB(ABREDBConnection pConn, EmailInfoDTO aEmailObj, int iApplicNID, int iOrgNID, int iType, int iEmailFlID ){
		int iRetCode=0;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		String aszTemp="", aszNID="",aszSQLtemp ="";
        MethodInit("loadApplicEmailFromDB");
		if(null == pConn) return -1;
		if(null == aEmailObj) return -1;
		boolean feed=false, b_natlassoc=false;
		String aszSQL101 = 
				"SELECT applic_vid, applic_nid, delta, cvintern_site_org_vid, cvintern_site_org_nid, " +
				" cvi_email_flag_nid, email_flag_status,email_sent_dt" +
				" FROM  " + aszDrupalDB + "content_field_cvi_email_flag  " ;
		switch(iType){
			case ApplicationInfoDTO.LOADBY_EMAIL_APPLICNID:
				aszSQL101 += " WHERE cvi_email_flag_nid=" + iEmailFlID +" AND applic_nid=" + iApplicNID;
				break;
			case ApplicationInfoDTO.LOADBY_EMAIL_APPLICORGNID:
				aszSQL101 += " WHERE cvi_email_flag_nid=" + iEmailFlID +" AND applic_nid=" + iApplicNID +
					" AND cvintern_site_org_nid="+iOrgNID;
				break;
			default:
				break;
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
		iRetCode=-111; // no results
		if(pConn.getNextResult()){
            iRetCode=0;
            /*
            aEmailObj.setEmailId(pConn.getDBInt("nid"));
			aEmailObj.setEmailSubjectText(pConn.getDBString("field_email_subject_value"));
			aEmailObj.setEmailBodyTextIntro(pConn.getDBString("field_email_intro_value"));
			aEmailObj.setEmailBodyText(pConn.getDBString("field_email_body_value"));
			aEmailObj.setEmailBodyTextClosing(pConn.getDBString("field_email_closing_value"));
			*/
		}
		return iRetCode;
	}
	// end-of method loadApplicEmailFlagFromDB()


    /**
	* insert a row 
	*/
	public int insertApplicEmailFlagIntDB(ABREDBConnection pConn, ApplicationInfoDTO aApplicInfoObj, OrganizationInfoDTO aOrgInfoObj, int iNID ){
		int iRetCode=0;
		String aszSQL101 = " INSERT IGNORE INTO " + aszDrupalDB + "content_field_cvi_email_flag ( " +
				"applic_vid,applic_nid,delta,cvintern_site_org_vid,cvintern_site_org_nid,cvi_email_flag_nid," +
				"email_flag_status,email_sent_dt)" +
				" values(?,?,0,?,?,"+iNID+",0, {fn now()} )" ;
		MethodInit("insertApplicEmailFlagIntDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aApplicInfoObj){
			setErr("null input object");
			return -1;
		}
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}

		iRetCode = pConn.setPrepQryInt( aApplicInfoObj.getVID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aApplicInfoObj.getNID() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		if(aOrgInfoObj==null){
			iRetCode = pConn.setPrepQryInt( 0 );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( 0 );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}else{
			iRetCode = pConn.setPrepQryInt( aOrgInfoObj.getORGVID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aOrgInfoObj.getORGNID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}


		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}

		return 0;
	}
	// end-of method insertApplicEmailFlagIntDB()
	
	
	
	//private String aszDrupalDB = "urbmi5_drupal.um_";
	private static String aszDrupalDB = "um_";
	private static String aszVolengDB = "techmi5_voleng.";
	//private String aszVolengDB = "";
	private static int vidPortalName=343;
	private static int iOrgApplicMatchEmailNID = 546506, iApplicConfirmEmailNID = 544402;
}
