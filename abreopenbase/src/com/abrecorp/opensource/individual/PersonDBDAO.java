package com.abrecorp.opensource.individual;

/**
* Code generated DataAccess class
* For Table userprofileinfo
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.OrganizationInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;

class PersonDBDAO extends ABREBase {

	/**
	** Constructor
	*/
    public PersonDBDAO(){}
    
    /**
	* load basic contact information - first, last, email address, uid
	*/
	public int loadUserContactDataFromDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL2=null;
		MethodInit("loadUIDFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
		
		aszSQL2 = "SELECT users.mail, users.uid, up.nid, up.field_first_name_value first_name, up.field_last_name_value last_name " +
				" FROM " + aszDrupalDB + "users users, " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile up " +
				" WHERE users.uid=n.uid AND n.type LIKE 'uprofile' AND n.nid=up.nid " +
				" AND users.uid=" + aHeadObj.getUserUID() ;
		iRetCode=pConn.PrepQuery(aszSQL2);
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
		iRetCode=-1;
		if(pConn.getNextResult()){
			iRetCode=0;
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("mail")); 
			aHeadObj.setUserUID(pConn.getDBInt("uid")); 
			aHeadObj.setUSPNameFirst(pConn.getDBString("first_name")); 
			aHeadObj.setUSPNameLast(pConn.getDBString("last_name")); 
		}else{
			aszSQL2 = "SELECT users.mail, users.uid " +
					" FROM " + aszDrupalDB + "users users " +
					" WHERE users.uid=" + aHeadObj.getUserUID() ;
			iRetCode=pConn.PrepQuery(aszSQL2);
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
			iRetCode=-1;
			if(pConn.getNextResult()){
				iRetCode=0;
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("mail")); 
				aHeadObj.setUserUID(pConn.getDBInt("uid")); 
			}
		}
		return iRetCode;
	}
    
    
    /**
	* login a user
	*/
	public int loadUIDFromDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType ){
		int iRetCode=0;
		String aszSQL2=null;
		MethodInit("loadUIDFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
		
		aszSQL2 = "SELECT users.uid FROM " + aszDrupalDB + "users users " ;
        switch( iType ){
        	case PersonInfoDTO.GET_UID_LOADBY_EMAILPWD :
        		aszSQL2 = aszSQL2 + " WHERE users.mail LIKE '" + aHeadObj.getUSPEmail1Addr() + 
        			"' AND users.pass=MD5('" + aHeadObj.getUSPPassword() + "') ";
        		break;
        	case PersonInfoDTO.LOADBY_AUTH_ADMIN_PWD : 
        		aszSQL2 = aszSQL2 + " WHERE users.mail LIKE '" + aHeadObj.getUSPEmail1Addr() + 
    			"' OR users.name LIKE '" + aHeadObj.getUSPUsername() + "''";
    		break;
        	default:
        		aHeadObj.appendErrorMsg("User not found in system  ");
                return 1;
        }
		iRetCode=pConn.PrepQuery(aszSQL2);
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
			aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
		}
		return iRetCode;
	}
    
    /**
	* login a user
	*/
	public int getUIDFromEmailDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0, iUID=0;
		String aszSQL2=null;
		MethodInit("loadUIDFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;
		
		aszSQL2 = "SELECT users.uid FROM " + aszDrupalDB + "users users " + 
			" WHERE users.mail LIKE '" + aHeadObj.getUSPEmail1Addr() ;
		iRetCode=pConn.PrepQuery(aszSQL2);
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
			iUID=pConn.getDBInt("uid");
			aHeadObj.setUserUID(iUID); // to help handle ownership on drupal interface
		}
		return iRetCode;
	}
    
    /**
	* login a user
	*/
	public int loadUserFromDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType, String aszRailsDB ){
		int iRetCode=0;
		String aszSQLSimple=null, aszEmail=null, aszUserName=null, aszSQLwhere=null;
		String aszSQL101=null, aszSQL2=null, aszTemp=null;
		String aszSQLuser=null, aszSQLuprofile=null, aszSQLlocation=null;
		int index=0, iTemp=0, iUID=0;
		int[] a_iContainer= new int[1];
		int[] a_iTemp= new int[50];// new int[15];
		String[] a_aszContainer= new String[1];
		String[] a_aszTemp= new String[50];
		MethodInit("loadUserFromDB");
		if(null == pConn) return -1;
    	if(null == aHeadObj) return -2;

		String aszSQLuserSelect = "SELECT users.name username, users.mail, users.uid, users.status ";
		String aszSQLuprofileSelect = ", n.nid, n.vid" +
				", up.field_first_name_value, up.field_last_name_value" +
				", up.field_resume_value, up.field_resume_filepath_value" +
				", up.field_is_christian_value, up.field_church_attendance_value, up.field_site_use_type_value," +
				" up.field_birth_year_value " +
				", up.field_personality_facet_ei_value, up.field_personality_facet_sn_value" +
				", up.field_personality_facet_tf_value, up.field_personality_facet_jp_value" +
				", up.field_facebook_uid_value" + ", up.field_personality_published_value" +
				", up.field_internship_interest_value, field_voluser_agree2_flag_value, up.field_subscribe_opps_posti_value ";
		String aszSQLlocationSelect = ", l.lid, l.street, l.city, l.province, l.postal_code, l.country ";
		
		String aszSQLuserFrom = "  FROM " + aszDrupalDB + "users users ";
		String aszSQLuprofileFrom = ", " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile up ";
		String aszSQLprofileFrom = ", " + aszRailsDB + "profiles p";
		String aszSQLlocationFrom = ", " + aszDrupalDB + "location_instance li, " + aszDrupalDB + "location l ";
		
		String aszSQLuprofileWhere = " AND users.uid=n.uid AND n.nid=up.nid ";
		String aszSQLprofileWhere = " AND up.nid = p.drupal_uprofile_nid ";
		String aszSQLlocationWhere = " AND n.nid=li.nid AND li.lid=l.lid ";
		

        switch( iType ){
	    	case PersonInfoDTO.SIMPLE_LOADBY_UID :
	    		if(aHeadObj.getUserUID() < 1){
	        		aHeadObj.appendErrorMsg("user id required");
	        		return -1;
	    		}
	    		aszSQLSimple = "SELECT users.name username, users.mail, users.uid, users.status " +
	    				"  FROM " + aszDrupalDB + "users users " +
	    						"WHERE users.uid=" + aHeadObj.getUserUID();
	    		break;
        	case PersonInfoDTO.LOADBY_UID :
        		if(aHeadObj.getUserUID() < 1){
            		aHeadObj.appendErrorMsg("user id required");
            		return -1;
        		}
        		aszSQLwhere = " WHERE users.uid=" + aHeadObj.getUserUID();
            	// drupal user only
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
        		break;
        	case PersonInfoDTO.LOADBY_USERNAME :
            	aszUserName = aHeadObj.getUSPUsername();
            	aszEmail = aHeadObj.getUSPEmail1Addr();
            	if(aszEmail.length() < 3){
            		aHeadObj.appendErrorMsg("A valid email address is required  ");
            		return -1;
            	}
            	if(aszUserName.length()>1){
                	aszSQLwhere = " WHERE users.name='" + aszUserName + "' "
                		+ " and users.pass=MD5('" + aHeadObj.getUSPPassword() + "')";
            	}else{
                	aszSQLwhere = " WHERE users.mail='" + aszEmail + "' "
            			+ " and users.pass=MD5('" + aHeadObj.getUSPPassword() + "')";
            	}
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
        		break;
        	case PersonInfoDTO.LOADBY_EMAIL_AS_CONTACT :
            	aszEmail = aHeadObj.getUSPEmail1Addr();
            	if(aszEmail.length() < 3){
            		aHeadObj.appendErrorMsg("A valid email address is required  ");
            		return -1;
            	}
            	
                aszSQLwhere = " WHERE users.mail='" + aszEmail + "' ";
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
        		break;
        	case PersonInfoDTO.LOADBY_AUTH_ADMIN_PWD :
            	aszUserName = aHeadObj.getUSPUsername();
            	aszEmail = aHeadObj.getUSPEmail1Addr();
            	if(aszEmail.length() < 3){
            		aHeadObj.appendErrorMsg("A valid email address is required  ");
            		return -1;
            	}
            	if(aszUserName.length()>1){
                	aszSQLwhere = " WHERE users.name='" + aszUserName + "' ";
                		//+ " and users.pass=MD5('" + aHeadObj.getUSPPassword() + "')";
            	}else{
                	aszSQLwhere = " WHERE users.mail='" + aszEmail + "' ";
            			//+ " and users.pass=MD5('" + aHeadObj.getUSPPassword() + "')";
            	}
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
        		break;
        	case PersonInfoDTO.LOADBY_EMAIL :
            	//aszUserName = aHeadObj.getUSPUsername();
            	aszEmail = aHeadObj.getUSPEmail1Addr();
            	if(aszEmail.length() < 3){
            		aHeadObj.appendErrorMsg("A valid email address is required  ");
            		return -1;
            	}
            	aszSQLwhere = " WHERE users.mail='" + aszEmail + "'";
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
            	break;
        	case PersonInfoDTO.LOADBY_UIDEMAIL :
            	//aszUserName = aHeadObj.getUSPUsername();
            	aszEmail = aHeadObj.getUSPEmail1Addr();
            	if(aszEmail.length() < 3){
            		aHeadObj.appendErrorMsg("A valid email address is required  ");
            		return -1;
            	}
            	aszSQLwhere = " WHERE users.mail='" + aszEmail + "' "
            		+ " and users.uid=" + aHeadObj.getUserUID() + " ";
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
        		break;
        	case PersonInfoDTO.LOADBY_FACEBOOK_UID :
        		//aszUserName = aHeadObj.getUSPUsername();
            	//long lFacebookUID = aHeadObj.getFacebookUID();
            	String aszFacebookUID = aHeadObj.getFacebookUID();
            	//if(lFacebookUID < 3){
            	if(aszFacebookUID.length() < 1){
            		aHeadObj.appendErrorMsg("A valid facebook userid is required  ");
            		return -1;
            	}
            	// aszSQLwhere = " WHERE up.field_facebook_user_id_value=" + lFacebookUID + " ";
            	aszSQLwhere = " WHERE up.field_facebook_uid_value LIKE '" + aszFacebookUID + "' "; // switched from bigint(64) to varchar
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLwhere;
        		// drupal user and uprofile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + 
        			aszSQLwhere + aszSQLuprofileWhere;
        		// drupal user, uprofile, and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLlocationWhere;
				break;
        	case PersonInfoDTO.LOADBY_LINKEDIN_ID:
            	String aszLinkedInId = aHeadObj.getLinkedInId();
            	if(aszLinkedInId.length() < 1){
            		aHeadObj.appendErrorMsg("A valid linkedin id is required  ");
            		return -1;
            	}
            	aszSQLwhere = " WHERE p.linkedin_id = '" + aszLinkedInId + "' ";
        		aszSQLuser = aszSQLuserSelect + aszSQLuserFrom + aszSQLprofileFrom + aszSQLwhere + aszSQLprofileWhere;
        		// drupal user and uprofile and rails profile only
        		aszSQLuprofile = aszSQLuserSelect + aszSQLuprofileSelect + 
        			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLprofileFrom +
        			aszSQLwhere + aszSQLuprofileWhere + aszSQLprofileWhere;
        		// drupal user, uprofile, rails profile and location only
        		aszSQLlocation = aszSQLuserSelect + aszSQLuprofileSelect + aszSQLlocationSelect + 
	    			aszSQLuserFrom + aszSQLuprofileFrom + aszSQLprofileFrom + aszSQLlocationFrom + 
	    			aszSQLwhere + aszSQLuprofileWhere + aszSQLprofileWhere + aszSQLlocationWhere;
        		break;
        	default:
	        	aHeadObj.appendErrorMsg("User not found in system  ");
	    		// setErr("request type not supported");
	            return 1;
        }
		
		
		if(iType== PersonInfoDTO.SIMPLE_LOADBY_UID ){
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQLSimple);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode=-1;
			if(pConn.getNextResult()){
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("mail"));
				aHeadObj.setUSPUsername(pConn.getDBString("username"));
				aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
				aHeadObj.setUSPPersonNumber(pConn.getDBInt("uid")); //(pConn.getDBInt("field_voluser_user_id_value")) 
				aHeadObj.setUSPAccountStatus(pConn.getDBString("users.status"));
				// since there is no userprofile, be sure to set these IDs to clear, so that it doesn't have old info in them
				aHeadObj.setUserProfileNID("");
				aHeadObj.setUserProfileVID("");
				aHeadObj.setUserProfileLID("");
				
				aHeadObj.setUSPInternalUserTypeComment(aszDrupalUser);
				// clearly, no taxonomy is associated, since there is no node, so just go ahead and return; don't bother w queries for loading taxonomy
				return -222;
			}
			
		}
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
			
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode = pConn.RunQuery(aszSQLlocation);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode=-1;
			if(pConn.getNextResult()){
				iRetCode=0;
				aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
				aHeadObj.setUSPPersonNumber(pConn.getDBInt("uid")); //(pConn.getDBInt("field_voluser_user_id_value")) 
				aHeadObj.setUserProfileNID(pConn.getDBInt("n.nid"));
				aHeadObj.setUserProfileVID(pConn.getDBInt("n.vid"));
				aHeadObj.setUserProfileLID(pConn.getDBInt("l.lid"));
				aHeadObj.setUSPUsername(pConn.getDBString("username"));
				aHeadObj.setUSPEmail1Addr(pConn.getDBString("users.mail"));
				aHeadObj.setUSPAccountStatus(pConn.getDBString("users.status"));
				aHeadObj.setUSPNameFirst(pConn.getDBString("up.field_first_name_value"));
				aHeadObj.setUSPNameLast(pConn.getDBString("up.field_last_name_value"));
				aHeadObj.setUSPAddrLine1(pConn.getDBString("l.street"));
				aHeadObj.setUSPAddrCity(pConn.getDBString("l.city"));
				aHeadObj.setUSPAddrStateprov(pConn.getDBString("l.province"));
				aHeadObj.setUSPAddrPostalcode(pConn.getDBString("l.postal_code"));
				aHeadObj.setUSPAddrCountryName(pConn.getDBString("l.country"));
//				aHeadObj.setUSPPhone1(pConn.getDBString("p.phone"));
				aHeadObj.setUSPVolResume(replaceLineBreak(pConn.getDBString("up.field_resume_value")));
				aHeadObj.setUSPResumeFilePath(pConn.getDBString("up.field_resume_filepath_value"));
				aHeadObj.setUSPAttendChurchP(pConn.getDBString("up.field_church_attendance_value"));
				aHeadObj.setUSPSiteUseType(pConn.getDBString("up.field_site_use_type_value"));
				aHeadObj.setBirthYear(pConn.getDBInt("up.field_birth_year_value"));
				aHeadObj.setUSPAgree2Fg(pConn.getDBString("up.field_voluser_agree2_flag_value"));
				aHeadObj.setInternshipInterest(pConn.getDBInt("up.field_internship_interest_value") == 0 ? false : true);
				aHeadObj.setUSPSubscribe(pConn.getDBString("up.field_subscribe_opps_posti_value"));
				aHeadObj.setBirthYear(pConn.getDBInt("up.field_birth_year_value"));
//				for first time linking through Facebook Login, we need to keep the facebook uid that was passed in the form
				if(! aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkFacebookAccount")){ 
					aHeadObj.setFacebookUID(pConn.getDBString("up.field_facebook_uid_value"));
				}
				aHeadObj.setPersonalityPublished(pConn.getDBInt("up.field_personality_published_value"));
				
				// if this is a full user, but still does not have a voleng person_number, they need to be voleng-ized
				if(	//(! (aHeadObj.getUSPPersonNumber() > 0) ) && 
					aHeadObj.getUSPAgree2Fg().length() > 0 &&
					aHeadObj.getUSPSiteUseType().length() > 0 &&
					aHeadObj.getUSPNameFirst().length() > 0 &&
					aHeadObj.getUSPNameLast().length() > 0
				){ 
					//return -555; // -555 will load a full user and just assign a person_number to the pre-existing drupal. --> wait until taxonomy is loaded
				}
				aHeadObj.setUSPInternalUserTypeComment(aszFullUser);
				aHeadObj.setUSPInternalUserTypeComment(aszProfileLocationUser);
				// if this is an org-only user w uprofile & location, but still does not have a voleng person_number, they need to be voleng-ized
				// can't do this, though (return -555 or -222) until we load any taxonomies as well
				
				// if the site use is only as an organization, then the user might not have a phone entry, b/c this is no longer a required field
				// login should be successful, even though it doesn't join with an entry in um_location_phone
			}else { // everything exists but the location & phone; users, uprofile

				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode = pConn.RunQuery(aszSQLuprofile);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -999;
				}
				iRetCode=-1;
				if(pConn.getNextResult()){ 
			           iRetCode=0;
					aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
					aHeadObj.setUSPPersonNumber(pConn.getDBInt("uid")); //(pConn.getDBInt("field_voluser_user_id_value")) 
					aHeadObj.setUserProfileNID(pConn.getDBInt("n.nid"));
					aHeadObj.setUserProfileVID(pConn.getDBInt("n.vid"));

					// since there is no userprofile location, be sure to set these IDs to clear, so that it doesn't have old info in them
					aHeadObj.setUserProfileLID("");
					aHeadObj.setUSPUsername(pConn.getDBString("username"));
					aHeadObj.setUSPEmail1Addr(pConn.getDBString("users.mail"));
					aHeadObj.setUSPAccountStatus(pConn.getDBString("users.status"));
					aHeadObj.setUSPNameFirst(pConn.getDBString("up.field_first_name_value"));
					aHeadObj.setUSPNameLast(pConn.getDBString("up.field_last_name_value"));
					aHeadObj.setUSPVolResume(replaceLineBreak(pConn.getDBString("up.field_resume_value")));
					aHeadObj.setUSPResumeFilePath(pConn.getDBString("up.field_resume_filepath_value"));
					aHeadObj.setUSPChristianP(pConn.getDBString("up.field_is_christian_value"));
					aHeadObj.setUSPAttendChurchP(pConn.getDBString("up.field_church_attendance_value"));
					aHeadObj.setUSPSiteUseType(pConn.getDBString("up.field_site_use_type_value"));
					aHeadObj.setBirthYear(pConn.getDBInt("up.field_birth_year_value"));
					aHeadObj.setUSPAgree2Fg(pConn.getDBString("up.field_voluser_agree2_flag_value"));
					
					aHeadObj.setUSPInternalUserTypeComment(aszProfileUser);
					// if this is an org-only user w uprofile & location, but still does not have a voleng person_number, they need to be voleng-ized
					// can't do this, though (return -555 or -222) until we load any taxonomies as well
					
					// if the site use is only as an organization, then the user might not have a location or phone entry, 
					//		b/c this is no longer required for an organization
					// login should be successful, even though it doesn't join with an entry in um_location_phone
						// allow the user to login; at this point they are an org-only user w/ a voleng person_number>0 & have agreed to T&C

				}else{ // the drupal user exists, but not a uprofile, location, or phone
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -999;
					}
					iRetCode = pConn.RunQuery(aszSQLuser);
					if(pConn.getNextResult()){
						// the user exists in urbmi5_drupal, but not in the voleng db
						aHeadObj.setUSPEmail1Addr(pConn.getDBString("mail"));
						aHeadObj.setUSPUsername(pConn.getDBString("username"));
						aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
						aHeadObj.setUSPPersonNumber(pConn.getDBInt("uid")); //(pConn.getDBInt("field_voluser_user_id_value")) 

						aHeadObj.setUSPAccountStatus(pConn.getDBString("users.status"));

						// since there is no userprofile, be sure to set these IDs to clear, so that it doesn't have old info in them
						aHeadObj.setUserProfileNID("");
						aHeadObj.setUserProfileVID("");
						aHeadObj.setUserProfileLID("");
						//aHeadObj.setUSPPassword(pConn.getDBString("password"));
						
						aHeadObj.setUSPInternalUserTypeComment(aszDrupalUser);
						// clearly, no taxonomy is associated, since there is no node, so just go ahead and return; don't bother w queries for loading taxonomy

						
						// check whether user has the Administrator User Role
						iUID=aHeadObj.getUserUID();
						aszSQL2 = " SELECT uid, rid FROM " + 
							aszDrupalDB + "users_roles " +
							"WHERE uid=" + iUID + " AND rid=" + adminUserRoleID;
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
					        // then the current user is an administrator; set the admistrator for them in the DTO
							aHeadObj.setUSPAuthTokens(PersonInfoDTO.AUTH_ADMIN);
						}
						

						return -222;
					}
					iRetCode=-1;
				}
				iRetCode=-1;
			}

		
		// check whether user has the Administrator User Role
		iUID=aHeadObj.getUserUID();
		aszSQL2 = " SELECT uid, rid FROM " + 
			aszDrupalDB + "users_roles " +
			"WHERE uid=" + iUID + " AND rid=" + adminUserRoleID;
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
	        // then the current user is an administrator; set the admistrator for them in the DTO
			aHeadObj.setUSPAuthTokens(PersonInfoDTO.AUTH_ADMIN);
		}
		
		// Natl Association - used for access rights to see if this user has the privileges to manage anything listed under associated OrgAffil
		aszSQL2=null ;
		aszSQL2 = " SELECT n.title, n.nid " +
			" FROM " + aszDrupalDB + "node n, " + aszDrupalDB + "content_field_natlassoc_manager manager " +
			"WHERE n.nid=manager.nid AND manager.field_natlassoc_manager_uid=" + aHeadObj.getUserUID() + " " ;
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
		index=0;
		a_iTemp= new int[50];
		a_aszTemp = new String[50];
		while(pConn.getNextResult()){
			if(index == 0){
				aHeadObj.setNatlAssociationNID(pConn.getDBInt("n.nid"));
				aHeadObj.setNatlAssociation(pConn.getDBString("n.title"));
			}

            iTemp=pConn.getDBInt("n.nid");
			a_iTemp[index]=iTemp;
			
			aszTemp=pConn.getDBString("n.title");
			a_aszTemp[index]=aszTemp;
			
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPNatlAssociationNIDsArray(a_iContainer);
		a_aszContainer= new String[index];
		for(int i=0; i<index; i++){
			a_aszContainer[i]=a_aszTemp[i];
		}
		if(a_aszContainer.length>0) aHeadObj.setUSPNatlAssociationsArray(a_aszContainer);
		
		if(index>0){
			int iTempNID = 0;
			int[] aszNationalAssocationNIDs = aHeadObj.getUSPNatlAssociationNIDsArray();
			String aszTempName = "";
			String[] aszNationalAssocations = aHeadObj.getUSPNatlAssociationsArray();
			// iterate through aHeadObj.getUSPNatlAssociationNIDsArray()
			int iTmpIndex=0;
			for(int i=0; i<index; i++){
				iTempNID=aszNationalAssocationNIDs[i];
				aszSQL2 = " SELECT name " +
						" FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB + "term_data td " +
						"WHERE tn.nid=" + iTempNID + " AND tn.tid=td.tid AND td.vid=" + vidPortalName ;
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
						if(iTmpIndex==0){
							aHeadObj.setNatlAssociationPortal(pConn.getDBString("name"));
						}
						aszTemp=pConn.getDBString("name") + ";" + aszNationalAssocations[i]; //  will store a semi-colon de-limited PORTAL;Nat'lAssocTitle
						a_aszTemp[i]=aszTemp;
						
						iTmpIndex++;
					}
			}
			a_aszContainer= new String[index];
			for(int i=0; i<index; i++){
				a_aszContainer[i]=a_aszTemp[i];
			}
			if(a_aszContainer.length>0) aHeadObj.setUSPNatlAssociationPortalsArray(a_aszContainer);
		}
		
		
		// ************ LOAD DATA FROM RAILS APP ******************
		// ************ LOAD DATA FROM RAILS APP ******************
		// ************ LOAD DATA FROM RAILS APP ******************
		aszSQL2=null ;
		if(aHeadObj.getUserProfileNID()>0){
			aszSQL2 = " SELECT  id " +
				" FROM " + aszRailsDB + "profiles profile " +
				" WHERE  profile.drupal_uprofile_nid=" + aHeadObj.getUserProfileNID() + " " ;
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
			index=0;
			a_aszTemp = new String[50];
			while(pConn.getNextResult()){
				aHeadObj.setUserRailsID(pConn.getDBInt("id"));
			}
		}
		aszSQL2=null ;
		if(aHeadObj.getUserProfileNID()>0){
			aszSQL2 = " SELECT phone_number, phone_type " +
				" FROM " + aszRailsDB + "profiles profile,  " + aszRailsDB + "profile_phones phones " +
				" WHERE profile.id=phones.profile_id AND profile.drupal_uprofile_nid=" + aHeadObj.getUserProfileNID() + " " ;
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
			index=0;
			a_aszTemp = new String[50];
			while(pConn.getNextResult()){
				aHeadObj.setUSPInternalUserTypeComment(aszFullUser);
				String aszPhoneType = pConn.getDBString("phone_type");
				String aszPhoneNumber = pConn.getDBString("phone_number");
				if(aHeadObj.getUSPPhone1().length()==0){
					aHeadObj.setUSPPhone1(aszPhoneNumber);
					aHeadObj.setUSPPhone1Type(aszPhoneType);
				}else if(aHeadObj.getUSPPhone2().length()==0){
					aHeadObj.setUSPPhone2(aszPhoneNumber);
					aHeadObj.setUSPPhone2Type(aszPhoneType);
				}
			}
		}
		aszSQL2=null ;
		if(aHeadObj.getUserProfileNID()>0){
			aszSQL2 = " SELECT GROUP_CONCAT(s.name) skill " +
				" FROM " + aszRailsDB + "profiles profile,  " + aszRailsDB + "profile_skills s " +
				" WHERE profile.id=s.id AND profile.drupal_uprofile_nid=" + aHeadObj.getUserProfileNID() + " " +
						"GROUP BY profile.id" ;
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
				String aszSkills = pConn.getDBString("skill");
				aszSkills = aszSkills.replaceAll("," , ", ");
				aHeadObj.setUserRailsSkills(aszSkills);
			}
		}
		
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		// ************ START DRUPAL TAXONOMY SECTION ********************
		
		// Volunteer Skills
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolSkill + " " ;
   		if(aHeadObj.getUserProfileNID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolSkills(pConn.getDBString("skills"));
			aHeadObj.setUSPVolSkills1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setUSPVolSkills2(pConn.getDBString("skills"));
				aHeadObj.setUSPVolSkills2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setUSPVolSkills3(pConn.getDBString("skills"));	
					aHeadObj.setUSPVolSkills3TID(pConn.getDBInt("tid"));
				}				
			}
		}
/*
		// Volunteer Interest Area
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name interest_area, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolInterestArea + " " ;
   		if(aHeadObj.getUserProfileNID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolInterestArea1(pConn.getDBString("interest_area"));
			aHeadObj.setUSPVolInterestArea1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setUSPVolInterestArea2(pConn.getDBString("interest_area"));
				aHeadObj.setUSPVolInterestArea2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setUSPVolInterestArea3(pConn.getDBString("interest_area"));	
					aHeadObj.setUSPVolInterestArea3TID(pConn.getDBInt("tid"));
				}				
			}
		}
*/
		// Language Spoken
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name lang, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolLang + " " ;
   		if(aHeadObj.getUserProfileNID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolLang1(pConn.getDBString("lang"));
			aHeadObj.setUSPVolLang1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setUSPVolLang2(pConn.getDBString("lang"));
				aHeadObj.setUSPVolLang2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setUSPVolLang3(pConn.getDBString("lang"));	
					aHeadObj.setUSPVolLang3TID(pConn.getDBInt("tid"));
				}				
			}
		}

		// User's Organizational Affiliation
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name org_affil, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolOrgAffil + " " ;
   		if(aHeadObj.getUserProfileNID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPOtherAffilP(pConn.getDBString("org_affil"));
			aHeadObj.setUSPOtherAffil1TID(pConn.getDBInt("tid"));
			if(pConn.getNextResult()){
				aHeadObj.setUSPOtherAffil2(pConn.getDBString("org_affil"));
				aHeadObj.setUSPOtherAffil2TID(pConn.getDBInt("tid"));
				if(pConn.getNextResult()){
					aHeadObj.setUSPOtherAffil3(pConn.getDBString("org_affil"));	
					aHeadObj.setUSPOtherAffil3TID(pConn.getDBInt("tid"));
				}				
			}
		}

		// Load Volunteer Denomination 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name vol_denom, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolDenom + " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPDenomAffilP(pConn.getDBString("vol_denom"));
			aHeadObj.setUSPDenomAffilTID(pConn.getDBInt("tid"));
		}

		// Load Whether the user is a Volunteer
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name volunteer, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolunteer + " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolunteer(pConn.getDBString("volunteer"));
			aHeadObj.setUSPVolunteerTID(pConn.getDBInt("tid"));
		}

		/** Load Various items that the Volunteer is Looking for*/
		// Looking For... - Local Volunteering
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iLocalVolTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPLocalVolTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Group/Family Volunteering
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iGroupFamilyTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPGroupFamilyTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Volunteering on Nonprofit Board
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iVolBoardTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolBoardTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Virtual Volunteering
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iVolVirtTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolVirtTID(pConn.getDBInt("tid"));
		}

		// Looking For... - Internships
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iIntrnTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPIntrnTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Summer Internships
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iSumIntrnTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPSumIntrnTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Work Study Opportunities
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iWorkStudyTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPWorkStudyTID(pConn.getDBInt("tid"));
		}

		// Looking For... - Jobs in Urban Ministry
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iJobsTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPJobsTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Opportunities to Speak at Conference
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iConferenceTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPConferenceTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Consulting
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iConsultingTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPConsultingTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Local Social Justice & Christian Groups
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iSocJustGrpsTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPSocJustGrpsTID(pConn.getDBInt("tid"));
		}
		// Looking For... - Local Organizations
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=" + iLocalOrgsTID + " AND t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor+ " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPLocalOrgsTID(pConn.getDBInt("tid"));
		}
		/** END Load Various items that the Volunteer is Looking for*/

		
		// Load Volunteer Availability 
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name vol_avail, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolAvail + " " ;
  		if(aHeadObj.getUserProfileNID() < 1){
  			return -1;
  		}
      	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPVolAvail(pConn.getDBString("vol_avail"));
			aHeadObj.setUSPVolAvailTID(pConn.getDBInt("tid"));
		}

		
		// Volunteer Personality
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name personality, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidPersonality + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
			aHeadObj.setUSPPersonality(pConn.getDBString("personality"));
			aHeadObj.setUSPPersonalityTID(pConn.getDBInt("tid"));
		}
		

		// Volunteer Languages
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolLang + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPLanguagesArray(a_iContainer);
		
		// Volunteer LookingFor
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name looking, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidLookingFor + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPLookingFor(aHeadObj.getUSPLookingFor() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPLookingForArray(a_iContainer);
		
		// Volunteer Skills
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolSkill + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPSkillTypes(aHeadObj.getUSPSkillTypes() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
			if(index>=50)	break;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPSkillTypesArray(a_iContainer);
		
		// Volunteer Service Areas
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name service_areas, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolServiceArea + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_aszTemp=new String[50];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
            aszTemp=pConn.getDBString("service_areas");
			aHeadObj.setUSPServiceAreas(aHeadObj.getUSPServiceAreas() + iTemp + ",");
			a_iTemp[index]=iTemp;
			a_aszTemp[index]=aszTemp;
			index++;
			if(index>=50)	break;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPServiceAreasArray(a_iContainer);
		a_aszContainer= new String[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_aszContainer[i]=a_aszTemp[i];
		}
		if(a_aszContainer.length>0) aHeadObj.setUSPServiceAreasStringArray(a_aszContainer);
		
		// Volunteer Spiritual Dev
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name spiritual_dev, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + 
			aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolCause + 
			" AND h.parent = " + spiritualTID + " AND h.tid = d.tid" ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPSpiritualDev(aHeadObj.getUSPSpiritualDev() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPSpiritualDevArray(a_iContainer);
		
		// Volunteer Global Issues
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name global_issues, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + 
			aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolCause + 
			" AND h.parent = " + globalIssuesTID + " AND h.tid = d.tid";
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPGlobalIssues(aHeadObj.getUSPGlobalIssues() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPGlobalIssuesArray(a_iContainer);
		
		// Volunteer Ministry Areas
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name ministry_areas, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + 
			aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolCause + 
			" AND h.parent = " + ministryAreasTID + " AND h.tid = d.tid";
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPMinistryAreasCause(aHeadObj.getUSPMinistryAreasCause() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPMinistryAreasArray(a_iContainer);
		
		// Volunteer Organizational Dev
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name organization_dev, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + 
			aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolCause + 
			" AND h.parent = " + organizationalDevelopmentTID + " AND h.tid = d.tid";
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPOrganizationalDev(aHeadObj.getUSPOrganizationalDev() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPOrganizationalDevArray(a_iContainer);
		
		// Volunteer Reconciliation
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name reconciliation, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + 
			aszDrupalDB + "term_data d, " + aszDrupalDB + "term_hierarchy h " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidVolCause + 
			" AND h.parent = " + reconciliationTID + " AND h.tid = d.tid";
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		a_iTemp= new int[50];// new int[15];
		while(pConn.getNextResult()){
            iRetCode=0;
            iTemp=pConn.getDBInt("tid");
			aHeadObj.setUSPReconciliation(aHeadObj.getUSPReconciliation() + iTemp + ",");
			a_iTemp[index]=iTemp;
			index++;
		}
		a_iContainer= new int[index];//index-1];//???
		for(int i=0; i<index; i++){
			a_iContainer[i]=a_iTemp[i];
		}
		if(a_iContainer.length>0) aHeadObj.setUSPReconciliationArray(a_iContainer);
		
		//		 Volunteer OtherPassions
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidOtherPassions + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setUSPOtherPassions(aHeadObj.getUSPOtherPassions() + pConn.getDBInt("tid") + ",");
		}
		
		//		 Volunteer OtherSkills
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidOtherSkills + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setUSPOtherSkills(aHeadObj.getUSPOtherSkills() + pConn.getDBInt("tid") + ",");
		}

		//		 Volunteer OtherLearninginterests
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name skills, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidOtherLearningInterests + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setUSPOtherLearningInterests(aHeadObj.getUSPOtherLearningInterests() + pConn.getDBInt("tid") + ",");
		}

		
		//	City taxonomy
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name city, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidCity + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setCityTID(pConn.getDBInt("tid"));
		}
		
		//	Country taxonomy
		aszSQL2=null ;
		aszSQL101 = " SELECT SQL_CACHE  d.name country, d.tid tid FROM " + 
			aszDrupalDB + "node_revisions nr, " + aszDrupalDB + "term_node t, " + aszDrupalDB + "term_data d " +
			"WHERE t.tid=d.tid AND nr.vid=t.vid AND d.vid=" + vidCountry + " " ;
   		if(aHeadObj.getUserProfileVID() < 1){
   			return -1;
   		}
       	aszSQL2 = aszSQL101 + " AND nr.vid=" + aHeadObj.getUserProfileVID() + " ";
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
		while(pConn.getNextResult()){
            iRetCode=0;
			aHeadObj.setCountryTID(pConn.getDBInt("tid"));
		}
		
		// ************** END DRUPAL TAXONOMY SECTION ********************
		// ************** END DRUPAL TAXONOMY SECTION ********************
		// ************** END DRUPAL TAXONOMY SECTION ********************

		int iRetCode2=0, iCount=0, iNID=0;
		int[] a_iOrgNIDsTemp = new int[255];
		aszTemp=null ;
		aszSQL2 = " SELECT nid, vid " +
			" FROM " + aszDrupalDB + "content_field_volorg_owner " + 
			" WHERE field_volorg_owner_uid =" + aHeadObj.getUserUID() + " " +
			" ORDER BY nid "; 
		iRetCode2 = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
		}
		iRetCode2=0;
		while(pConn.getNextResult() && iCount < 255){
			iRetCode2=1;
			iNID=pConn.getDBInt("nid");
            a_iOrgNIDsTemp[iCount]=pConn.getDBInt("nid");
            if(iCount==0) aHeadObj.setORGNID(iNID);
            iCount++;
        }
		int[] a_iOrgNIDs = new int[iCount];
		for(int i=0; i < iCount; i++){
			a_iOrgNIDs[i]=a_iOrgNIDsTemp[i];
		}
		aHeadObj.setOrgNIDsArray(a_iOrgNIDs);

		if(iCount>0){
			iCount=0;
			int[] a_iOppNIDsTemp = new int[255];
			aszTemp=null ;
			aszSQL2 = " SELECT field_volorg_opp_reference_nid opp_nid " +
				" FROM " + aszDrupalDB + "content_field_volorg_opp_reference " + 
				" WHERE nid =" + aHeadObj.getORGNID() + " " +
				" ORDER BY nid "; 
			iRetCode2 = pConn.RunQuery(aszSQL2);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
			}
			iRetCode2=0;
			while(pConn.getNextResult() && iCount < 255){
				iRetCode2=1;
				iNID=pConn.getDBInt("opp_nid");
	            a_iOppNIDsTemp[iCount]=pConn.getDBInt("opp_nid");
	            if(iCount==0) aHeadObj.setOPPNID(iNID);
	            iCount++;
	        }
			int[] a_iOppNIDs = new int[iCount];
			for(int i=0; i < iCount; i++){
				a_iOppNIDs[i]=a_iOppNIDsTemp[i];
			}
			aHeadObj.setOrgOppNIDsArray(a_iOppNIDs);
		}
		
		int[] a_iOppNIDsTemp = new int[255];
		aszTemp=null ;
		aszSQL2 = " SELECT opp_nid " +
			" FROM " + aszDrupalDB + "usermail " + 
			" WHERE uid =" + aHeadObj.getUserUID() + " AND opp_nid>0 " +
			" ORDER BY uid, opp_nid "; 
		iRetCode2 = pConn.RunQuery(aszSQL2);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
		}
		iRetCode2=0;
		while(pConn.getNextResult() && iCount < 255){
			iRetCode2=1;
			iNID=pConn.getDBInt("opp_nid");
            a_iOppNIDsTemp[iCount]=pConn.getDBInt("opp_nid");
            iCount++;
        }
		int[] a_iUserOppNIDsArray = new int[iCount];
		for(int i=0; i < iCount; i++){
			a_iUserOppNIDsArray[i]=a_iOppNIDsTemp[i];
		}
		aHeadObj.setUserOppNIDsArray(a_iUserOppNIDsArray);
		// if this is an org-only user w uprofile & location, but still does not have a voleng person_number, they need to be voleng-ized
		if((aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)) &&
				//(! (aHeadObj.getUSPPersonNumber() > 0) ) && 
				aHeadObj.getUSPSiteUseType().length() > 0  && 
				aHeadObj.getUSPNameLast().length() > 0  && 
				aHeadObj.getUSPNameFirst().length() > 0  && 
				aHeadObj.getUSPAgree2Fg().length() > 0 
		){ 
			return 0;
			//return -555; // -555 will load a full user and just assign a person_number to the pre-existing drupal.
		}
		if(	//(! (aHeadObj.getUSPPersonNumber() > 0) ) && 
				aHeadObj.getUSPAgree2Fg().length() > 0 &&
				aHeadObj.getUSPSiteUseType().length() > 0 &&
				aHeadObj.getUSPNameFirst().length() > 0 &&
				aHeadObj.getUSPNameLast().length() > 0
			){ 
			return 0;
			//return -555; // -555 will load a full user and just assign a person_number to the pre-existing drupal.
			}

		// if the user, regardless of what type, has not agreed that they are 16yrs or older, the drupal create/login account page needs to load
		if( ! (aHeadObj.getUSPAgree2Fg().length() > 0) )  { 
			return -222; // -555 will load a full user and just assign a person_number to the pre-existing drupal.
		}
		if( 	(aHeadObj.getUSPSiteUseType().length() < 1 ) ||
				(aHeadObj.getUSPNameLast().length() < 1 ) ||
				(aHeadObj.getUSPNameFirst().length() < 1 ) ||
				(aHeadObj.getUSPAgree2Fg().length() < 1 ) ||
			(
				(aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszVolunteerUser))
				&&	(	
					//aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszFullUser)  ||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileLocationUser)  ||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)  ||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser) 
				)
			)
		){ // if not an Org-only and does not have phone
			return -222;
		}

		return iRetCode;
	}
    // end-of method loadUserFromDB()


	/**
	* Does email address exist in table userprofileinfo
	* return 0 if yesy found email address
	*/
	public int IsEmailAddrInSystem( ABREDBConnection pConn, String emailaddr ){
		int iRetCode=0;
		String aszSQL=null , aszTemp=null , aszPersonCodekey=null ;
        MethodInit("IsEmailAddrInSystem");
		if(null == pConn) return -1;
		if(null == emailaddr) return -1;
        aszTemp = emailaddr.trim();
        if(aszTemp.length() < 2) return -1;
        //aszSQL = " SELECT email1_addr FROM userprofileinfo WHERE email1_addr='" + aszTemp + "' ";
		aszSQL = " SELECT mail FROM " + aszDrupalDB + "users WHERE mail='" + aszTemp + "' ";
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
            aszPersonCodekey = pConn.getDBString("mail") ;
            return 0;
		}
		return -1;
    }
    // end-of method IsEmailAddrInSystem()

	/**
	* Does user exist in table userprofileinfo
	* return 0 if yes found username
	*/
	public int IsUserIDInSystem( ABREDBConnection pConn, String username, int iUid ){
		int iRetCode=0;
		String aszSQL=null , aszTemp=null , aszPersonCodekey=null ;
        MethodInit("IsUserIDInSystem");
		if(null == pConn) return -1;
		if(null == username) return -1;
        aszTemp = username.trim();
        if(aszTemp.length() < 2) return -1;
        aszSQL = " SELECT name FROM " + aszDrupalDB + "users WHERE name='" + aszTemp + "' and uid !=" + iUid;
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
            aszPersonCodekey = pConn.getDBString("name") ;
            return 0;
		}
		return -1;
    }
    // end-of method IsUserIDInSystem()

	/**
	* Does person codekey exist in table userprofileinfo
	* return 0 if yes found email address
	*/
	public int IsPersonCodeKeyInSystem( ABREDBConnection pConn, String username ){
		int iRetCode=0;
		String aszSQL=null , aszTemp=null , aszPersonCodekey=null ;
        MethodInit("IsPersonCodeKeyInSystem");
		if(null == pConn) return -1;
		if(null == username) return -1;
        aszTemp = username.trim();
        if(aszTemp.length() < 2) return -1;
        aszSQL = " SELECT percodekey FROM " + aszVolengDB + "userprofileinfo WHERE percodekey='" + aszTemp + "' ";
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
            aszPersonCodekey = pConn.getDBString("percodekey") ;
            return 0;
		}
		return -1;
    }
    // end-of method IsPersonCodeKeyInSystem()


	/**
	* Does session exist with given ip address in sessions table?
	* return 0 if yes found username
	*/
	public int IsSessionIDInSystem( ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL=null;
        MethodInit("IsSessionIDInSystem");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;
        aszSQL = " " +
        		"SELECT uid, sid, hostname, timestamp " +
        			" FROM " + aszDrupalVolengDB + "sessions " +
        			" WHERE sid='" + aHeadObj.getSessionValue() + "' " +
        			" AND hostname ='" + aHeadObj.getSessionIP() + "' " ;
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
            aHeadObj.setUserUID( pConn.getDBInt("uid") ) ;
            aHeadObj.setSessionTimestamp( pConn.getDBInt("timestamp") ) ;
            return 0;
		}
		return -1;
    }
    // end-of method IsSessionIDInSystem()
	
	public String getUsernameByUID( ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL=null, aszUsername="" ;
        MethodInit("IsUserIDInSystem");
		if(null == pConn) return "";
        aszSQL = " SELECT name FROM " + aszDrupalDB + "users WHERE uid =" + aHeadObj.getUserUID();
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return aszUsername;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return aszUsername;
		}
		if(pConn.getNextResult()){
            aszUsername = pConn.getDBString("name") ;
            return aszUsername;
		}
		return aszUsername;
    }
    // end-of method getUsernameByUID()


    /**
	* first step of creating new user
	*/
	public int insertUserDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB ){
		int iRetCode=0;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		int iTermsConditionsVersion = 2;
		int index=0;
		String tempTaxonomy;
		String Qry1=null;
		String aszSQLdrupal101=null;

		MethodInit("insertUserDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
  		
		if(aHeadObj.getUserRoleID()==0){
			aHeadObj.setUserRoleID(iRid);
		}
		
		long lRandom = Math.round(Math.random());
		int iPhpListUserID = 0;
		// subscribe user to phplist, if they selected it on the createaccoutn page
		if(aHeadObj.getNewsletter()==iPhpListVolunteerUser || aHeadObj.getNewsletter()==iPhpListOrgUser){
			aszSQLdrupal101="INSERT INTO " + aszPhpListDB + "phplist_user_user(email, entered, modified, uniqid, htmlemail, subscribepage) " +
					" VALUES(?,{fn now()},{fn now()}," + 
					" MD5(CONCAT(UNIX_TIMESTAMP({fn now()}),"+lRandom+")),1,1 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
			}
			iRetCode=-1;

			//	*****  Grab the last auto-incremented ID and save it as the phpuid for that user *****************
			Qry1 = "SELECT LAST_INSERT_ID() ";
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
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iPhpListUserID = pConn.getDBInt("LAST_INSERT_ID()");
			} else {
				itid=-1;
			}
			iRetCode=-1;
			
			if(iPhpListUserID>0){
				aszSQLdrupal101="INSERT INTO " + aszPhpListDB + "phplist_listuser(userid, listid, entered, modified) " +
						" VALUES("+iPhpListUserID+","+aHeadObj.getNewsletter()+",{fn now()},{fn now()} ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}
				iRetCode = pConn.ExecutePrepQry(); 
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}
				iRetCode=-1;
				
				aszSQLdrupal101="INSERT INTO " + aszPhpListDB + "phplist_user_user_history(userid, ip, date, summary, detail, systeminfo)" +
						" VALUES("+iPhpListUserID+",'"+aHeadObj.getIP()+"',{fn now()},'Subscription', " +
								"'Subscribe page: 1   List Membership:   * ChristianVolunteering Marketing', " +
								"'"+ aHeadObj.getSysInfo()+"' ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}
				iRetCode = pConn.ExecutePrepQry(); 
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
				}
				iRetCode=-1;
				
				
				//	*****  GIncrease the number of users and the number of subscriptions for the given group *****************
				Qry1 = "SELECT id, item, listid, value FROM  " + aszPhpListDB + "phplist_userstats";
				iRetCode=pConn.PrepQuery(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());	ErrorsToLog();	return -1;
				}
				iRetCode = pConn.ExePrepQry();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());	ErrorsToLog();	return -1;
				}
				int iTmpID=0, iTmpListID=0, iTmpValue=0;
				String aszTmpItem="";
				int iPhpListTotalUsers=0, iPhpListSubscribeNumber=0;
				while(pConn.getNextResult()){
					iTmpID = pConn.getDBInt("id");
					iTmpListID = pConn.getDBInt("listid");
					iTmpValue = pConn.getDBInt("value");
					aszTmpItem = pConn.getDBString("item");
					if(iTmpListID==0 && aszTmpItem.equalsIgnoreCase("total users"))	iPhpListTotalUsers=iTmpValue+1;
					else if(iTmpListID==iPhpListVolunteerUser && aszTmpItem.equalsIgnoreCase("subscribe"))	iPhpListSubscribeNumber=iTmpValue+1;
				}
				if(iPhpListTotalUsers>0){
					aszSQLdrupal101="UPDATE " + aszPhpListDB + "phplist_userstats " +
							"SET value=" + iPhpListTotalUsers + " WHERE listid=0";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode = pConn.ExecutePrepQry();
					if(iRetCode == 1062 ){
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
				}
				if(iPhpListSubscribeNumber>0){
					aszSQLdrupal101="UPDATE " + aszPhpListDB + "phplist_userstats " +
							"SET value=" + iPhpListSubscribeNumber + " WHERE listid="+iPhpListVolunteerUser;
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode = pConn.ExecutePrepQry();
					if(iRetCode == 1062 ){
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
				}
				
			}
		}
		
System.out.println("2314 PersonDBDAO aHeadObj.getUSPInternalUserTypeComment() is "+aHeadObj.getUSPInternalUserTypeComment());		
		/*
		 * ADD USER TO DRUPAL
		 *  the user does not yet exist in the drupal db, either, so add the user to drupal as well.
		 */
		// um_users table
		if(aHeadObj.getUSPInternalUserTypeComment().length() < 1){ // the user does not exist yet in any capacity - is not logged in  2009-01-10//else if(iRetCode==-1){
			// "auto-"increment the uid in " + aszDrupalDB + "sequences table - grab last Id, then increment it, then make that the new uid...
			// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
		    // added timezone to insert 2008-01-04 to try and stop um.org bug with empty nodes being created
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "users(name, pass, status, mail,init, theme, created, timezone) " +
					" VALUES(?," + 
					" MD5(?),1,?,?,'',UNIX_TIMESTAMP({fn now()}), " + aHeadObj.getUSPTimezone() + " ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPassword() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;

			//	*****  Grab the last auto-incremented ID and save it as the uid for this user/node *****************
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
				iuid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserUID(iuid);
			} else {
				itid=-1;
			}
			iRetCode=-1;
			
			
			if (aHeadObj.getUSPAgree1Fg().length() > 0){
			    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "legal_accepted(uid, version, accepted,revision,language) " +//legal_accepted(uid, tc_id, accepted) " +
					" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ,1,'en') ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			}
			
			/** if the user is coming through WorldVision (worldvisiontraining, etc), then they need to accept WorldVision terms & conditions as well */
	    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase("worldvision.christianvolunteering.org")){
	        	if(aHeadObj.getUSPAgreeWVFg().length() > 0){
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
					aszSQLdrupal101="INSERT INTO " + aszWorldVisionDB + "legal_accepted(uid, version, accepted) " +//"legal_accepted(uid, tc_id, accepted) " +
							" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ) ";
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
	        	}
	    	}
		}else{ // a drupal user exists, but we don't know yet whether uprofile, location, etc do
			if (aHeadObj.getUSPAgree1Fg().length() > 0){ // add an entry to the legal_accepted table if not already in there
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
				// prepare query
				Qry1 = "SELECT * FROM " + aszDrupalDB + "legal_accepted WHERE uid=" + aHeadObj.getUserUID() + " and version="+ iTermsConditionsVersion + "";//" and tc_id="+ iTermsConditionsVersion + "";
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
				// Get Next Item From ResultSet
				if(pConn.getNextResult()){ // there is already an entry for this user in accepting the legal
					// the user already accepted legal terms and conditions - nothing needs to be done
				} else {
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol), if not already in drupal system
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "legal_accepted(uid, version, accepted,revision,language) " +//legal_accepted(uid, tc_id, accepted) " +
						" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ,1,'en') ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
					iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=-1;
				}
			}
			
			/** if the user is coming through WorldVision (worldvisiontraining, etc), then they need to accept WorldVision terms & conditions as well */
	    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase("worldvision.christianvolunteering.org")){
	        	if(aHeadObj.getUSPAgreeWVFg().length() > 0){
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
					aszSQLdrupal101="INSERT INTO " + aszWorldVisionDB + "legal_accepted(uid, version, accepted) " +//legal_accepted(uid, tc_id, accepted) " +
							" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ) ";
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
	        	}
	    	}

		}
		
		if(aHeadObj.getUserRoleID()>-1){
			/*
			 * apply the ChristianVolunteering.org user role to the new drupal user
			 */
			aszSQLdrupal101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ aHeadObj.getUserUID() +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
		}

		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
System.out.println("2527 PersonDBDAO aHeadObj.getUSPInternalUserTypeComment() is "+aHeadObj.getUSPInternalUserTypeComment());		
		// um_content_type_uprofile table
		if(
				aHeadObj.getUSPInternalUserTypeComment().length() < 1 					 ||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser) ||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgContact)||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOppContact)||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgAdmin)
		){
System.out.println("2542 PersonDBDAO inside condition "+aHeadObj.getUSPInternalUserTypeComment());		
			// INSERT INTO um_content_type_uprofile, um_node, ......;
			//ADD USERPROFILE TO DRUPAL - the userprofile does not yet exist at all in the drupal db
			
			// "auto-"increment the nid in " + aszDrupalDB + "sequences table - then grab last Id...
			// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
			lUniqueID=-1 ;
			lNextUniqueID=-1 ;
			Qry1=null;
			if(null == pConn){
				setErr("null database connection");
				return -1;
			}
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				return -1;
			}
			// add to um_node_revisions
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, log, timestamp, format) " +
					" VALUES(?,?,'','','',UNIX_TIMESTAMP({fn now()}),0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
System.out.println("2591 PersonDBDAO - just added to um_node_revisions: ");
System.out.println(aszSQLdrupal101);
			//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
			Qry1 = "SELECT LAST_INSERT_ID() ";
			aszSQLdrupal101 = "SELECT vid FROM   " + aszDrupalDB + "node_revisions WHERE uid=" + aHeadObj.getUserUID();
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
//				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
//				return -1;
			}

//			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPvid = pConn.getDBInt("LAST_INSERT_ID()");
//				iUPvid = pConn.getDBInt("vid");
				aHeadObj.setUserProfileVID(iUPvid);
System.out.println("iUPvid is "+iUPvid);				
			} else {
				itid=-1;
			}
			iRetCode=-1;
			
			// add to um_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate," +
					" title, uid, created, changed) " +
					" VALUES("+ iUPvid +",'uprofile',0,0,0,?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
System.out.println("inserted into the " + aszDrupalDB + "node iUPvid "+iUPvid +" With ");			
System.out.println(aszSQLdrupal101);			

			iRetCode=-1;
			
			//	*****  Grab the last auto-incremented ID and save it as the nid for this node *****************
			Qry1 = "SELECT LAST_INSERT_ID() ";
			aszSQLdrupal101 = "SELECT nid FROM   " + aszDrupalDB + "node WHERE vid=" + iUPvid;
System.out.println("   2660   Qry1 is ");
System.out.println(Qry1);				
System.out.println("   2662   aszSQLdrupal101 is ");
System.out.println(aszSQLdrupal101);				

			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				//return -999;
			}
			iRetCode=pConn.PrepQuery(Qry1);
System.out.println("2672 - after PrepQuery; iRetCode="+iRetCode);
//			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
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
//				iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
System.out.println("2684  PersonInfoDBDAO inside result searching for iUPnid");
				iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
//				iUPnid = pConn.getDBInt("nid");
				aHeadObj.setUserProfileNID(iUPnid);
			} else {
				itid=-1;
			}
System.out.println("2689  PersonInfoDBDAO iUPnid is "+iUPnid+";   aHeadObj.getUserProfileNID() is "+aHeadObj.getUserProfileNID());
			if(iUPnid<1){
				
				
				
System.out.println("2692 PersonInfoDBDAO - querying again to see if we get a larger nid this time");
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
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
System.out.println("2709; PersonInfoDBDAO second query;   inside result searching for iUPnid");
					iUPnid = pConn.getDBInt("nid");
					aHeadObj.setUserProfileNID(iUPnid);
				}
System.out.println("2713 PersonInfoDBDAO  iUPnid is "+iUPnid);


				if(aHeadObj.getUserProfileNID()==0){
System.out.println("2715 PersonInfoDBDAO failed 2 times; iUPNID is "+iUPnid+"; return -1");				
					return -1;
				}
			}
			iRetCode=-1;
			
			/*
			 * add correct nid to node_revisions
			 */
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "node_revisions SET nid=? " +
					"WHERE vid=?";
System.out.println("2728  PersonInfoDBDAO aszSQLdrupal101 is "+aszSQLdrupal101+"; iUPnid is "+aHeadObj.getUserProfileNID()+ " iUPvid is "+aHeadObj.getUserProfileVID());				
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileNID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileVID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."

			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				
			}else if(0 != iRetCode){
System.out.println("2735 PersonInfoDBDAO Error: "+getErrObj());				
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			
			// add to um_node_access
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
					" VALUES("+ iUPnid +",0,'all',1,0,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_node_comment_statistics
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
					" VALUES("+ iUPnid +",UNIX_TIMESTAMP({fn now()}),?,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_content_type_uprofile
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_uprofile " +
					"(nid,vid," +
					"field_first_name_value,field_last_name_value,field_site_use_type_value," +
					"field_voluser_subdomain_value,field_voluser_agree2_flag_value,field_subscribe_opps_posti_value";
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")
			){
				aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value";
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ") " +	"VALUES " +
					"(" + iUPnid + "," + iUPvid + "," +
							"?,?,?," +
							"?,?,0";// by default, for step 1, don't subscribe this user to any kind of email system
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")
			){
				aszSQLdrupal101 = aszSQLdrupal101 +	",?";
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ")";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() ); // make sure this has a value
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") 
			){
				iRetCode=pConn.setPrepQryString( aHeadObj.getFacebookUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;//0????????????????????

		}else{ // uprofile already exists; update it
		
		    iUPnid = aHeadObj.getUserProfileNID();
		    iUPvid = aHeadObj.getUserProfileVID();
		    // update the uprofile cck
			    
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
					"SET " +
					"field_first_name_value=?,field_last_name_value=?,field_site_use_type_value=?,field_birth_year_value=?," +
					"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=? ";
			if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
				aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value=? ";
			}
					
			aszSQLdrupal101= aszSQLdrupal101 + "WHERE nid=" + iUPnid + ""; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
				iRetCode = pConn.setPrepQryString( aHeadObj.getFacebookUID() );
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
			iRetCode=0;
		}

		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		
				// add to ChrisvolOnRails app table
		aszSQLdrupal101="INSERT IGNORE INTO " + aszRailsDB + "profiles(drupal_uprofile_nid, created_at, updated_at)" +
				" VALUES("+ iUPnid +",'{fn now()}','{fn now()}')" ;
//System.out.println(aszSQLdrupal101);
ErrorsToLog();
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
//			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
//			return -1;
		}
		iRetCode=-1;

//		 if it's been successful up to this point, the new user is now a Full User
		//aHeadObj.setUSPInternalUserTypeComment(aszFullUser);
		aHeadObj.setUSPPasswordInternal(aHeadObj.getUSPPassword());
		
		 
		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()>0){
	    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + "," + aHeadObj.getUserUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
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
		    		// get number of counts of the favorites flag for this content id
					aszSQLdrupal101 = " SELECT count " +
						" FROM " + aszDrupalDB + "flag_counts fl " +
						" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + aHeadObj.getPortal() + " AND content_type LIKE 'node' "; 
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -999;
					}
					iRetCode = pConn.RunQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -999;
					}
					iRetCode=-1;
					// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
					if(pConn.getNextResult()){
						iInitCount=pConn.getDBInt("count");
					}
	
	    			iChangeNumber++;// successfully added and not a duplicate
	        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
	            	iCurrentCount = iInitCount + iChangeNumber;
	            	if(iCurrentCount<0) iCurrentCount=0;
	            	if(iCurrentCount==0){
	            	}else{
						// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
						aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
			    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + ","+iCurrentCount+") "+
			    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
			    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
			    		if(0 != iRetCode){
			    			pConn.copyErrObj(getErrObj());
			    			ErrorsToLog();
			    			//return -1;
			    		}
	            	}
				}
			}
			// if successful - insert into the taxonomy as well
			aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "term_node (tid, vid, nid) " +
    				" VALUES (" + aHeadObj.getPortal() + ","+iUPvid+ ","+iUPnid+") "; 
    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
    		if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
    			ErrorsToLog();
    			//return -1;
    		}

		}
		
		// add UserOrgAffil
		if ( aHeadObj.getUSPOtherAffil1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( tid=" +  aHeadObj.getUSPOtherAffil1TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil1TID(itid);
				aHeadObj.setUSPOtherAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add User Org Affiliation 2
		if ( aHeadObj.getUSPOtherAffil2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( tid=" +  aHeadObj.getUSPOtherAffil2TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil2TID(itid);
				aHeadObj.setUSPOtherAffil2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add User Org Affiliation 3
		if ( aHeadObj.getUSPOtherAffil3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( tid=" +  aHeadObj.getUSPOtherAffil3TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil3TID(itid);
				aHeadObj.setUSPOtherAffil3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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


		return 0;
	}
	// end-of method insertUserDB()



	/**
	* new user step 2
	*/
	public int insertUserDataDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB){
		int iRetCode=0;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1, personalityTID=-1;
		String aszSQLdrupal101 = "";
		int index=0;
		String tempTaxonomy = "";
		String aszSQL101 = "";
		String Qry1 = "";
		String Qry2 = "";

		MethodInit("insertUserDataDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
  		
		if(aHeadObj.getUserRoleID()==0){
			aHeadObj.setUserRoleID(iRid);
		}

		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

	    
		if(aHeadObj.getUserRoleID()>-1){
			/*
			 * apply the ChristianVolunteering.org user role in case there is no chrisvol role for this user yet
			 */
			aszSQL101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ iuid +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..." - user role already exists
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		}
	    
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		// if iUPnid and iUPvid are 0, then the profile node still needs to be created, and then come back to this.
		if(iUPnid==0 && iUPvid==0){
			
			
			/************************ BEGIN USERPROFILE *****************************************/
			/************************ BEGIN USERPROFILE *****************************************/
			/************************ BEGIN USERPROFILE *****************************************/
			
			// um_content_type_uprofile table
			if(
					aHeadObj.getUSPInternalUserTypeComment().length() < 1 					 ||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser) ||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgContact)||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOppContact)||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgAdmin)
			){
				// INSERT INTO um_content_type_uprofile, um_node, ......;
				//ADD USERPROFILE TO DRUPAL - the userprofile does not yet exist at all in the drupal db
				
				// "auto-"increment the nid in " + aszDrupalDB + "sequences table - then grab last Id...
				// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
				lUniqueID=-1 ;
				lNextUniqueID=-1 ;
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
				// add to um_node_revisions
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format) " +
						" VALUES(?,?,'','',UNIX_TIMESTAMP({fn now()}),0 ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry(); 
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
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
				iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  fixed to use ExePrepQry
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				// Get tid From ResultSet
				if(pConn.getNextResult()){
					iUPvid = pConn.getDBInt("LAST_INSERT_ID()");
					aHeadObj.setUserProfileVID(iUPvid);
				} else {
					itid=-1;
				}
				iRetCode=-1;
				
				// add to um_node
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate," +
						" title, uid, created, changed) " +
						" VALUES("+ iUPvid +",'uprofile'," ;
	
				aszSQLdrupal101 = aszSQLdrupal101 +	"0,0,0," ; // the user has not filled in all details, so should not yet be published
				
				aszSQLdrupal101 = aszSQLdrupal101 +	" ?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry(); 
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
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
					iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
					aHeadObj.setUserProfileNID(iUPnid);
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
				iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileNID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					 ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileVID() );
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
				
				// add to um_node_access
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
						" VALUES("+ iUPnid +",0,'all',1,0,0 ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry(); 
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
				// add to um_node_comment_statistics
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
						" VALUES("+ iUPnid +",UNIX_TIMESTAMP({fn now()}),?,0 ) ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry(); 
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
				// add to um_content_type_uprofile
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_uprofile " +
						"(nid,vid," +
						"field_first_name_value,field_last_name_value,field_site_use_type_value,field_birth_year_value," +
						"field_voluser_subdomain_value,field_voluser_agree2_flag_value,field_subscribe_opps_posti_value";
				if(
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
						//aHeadObj.getFacebookUID()>2
				){
					aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value";
				}
				aszSQLdrupal101 = aszSQLdrupal101 + ") " +	"VALUES " +
						"(" + iUPnid + "," + iUPvid + "," +
								"?,?,?,?,?,0";// by default, for step 1, don't subscribe this user to any kind of email system
				if(
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
						//aHeadObj.getFacebookUID()>2
				){
					aszSQLdrupal101 = aszSQLdrupal101 +	",?";
						//,?) "; 
				}
				aszSQLdrupal101 = aszSQLdrupal101 + ")";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() ); // make sure this has a value
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				if(
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
						aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
						//aHeadObj.getFacebookUID()>2
				){
					//iRetCode=pConn.setPrepQryLong( aHeadObj.getFacebookUID() );
					iRetCode=pConn.setPrepQryString( aHeadObj.getFacebookUID() );
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
				}
				iRetCode = pConn.ExecutePrepQry();
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;//0????????????????????
	
			}else{ // uprofile already exists; update it				
			    iUPnid = aHeadObj.getUserProfileNID();
			    iUPvid = aHeadObj.getUserProfileVID();
			    // update the uprofile cck
				    
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
						"SET " +
						"field_internship_interest_value=?,field_first_name_value=?,field_last_name_value=?,field_site_use_type_value=?,field_birth_year_value=?," +
						"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=? ";
				if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
					aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value=? ";
				}
						
				aszSQLdrupal101= aszSQLdrupal101 + "WHERE nid=" + iUPnid + ""; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				int internshipInterest = 0;
				if(aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("united states") ||
				   aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("us"))
				  internshipInterest = aHeadObj.getInternshipInterest() ? 1 : 0;
				iRetCode = pConn.setPrepQryInt(internshipInterest);
				iRetCode=pConn.PrepQry(aszSQLdrupal101);
				if(0 != iRetCode){
				    pConn.copyErrObj(getErrObj());
				    ErrorsToLog();
				    return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
					iRetCode = pConn.setPrepQryString( aHeadObj.getFacebookUID() );
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
				iRetCode=0;
			}
	
			/************************** END USERPROFILE *****************************************/
			/************************** END USERPROFILE *****************************************/
			/************************** END USERPROFILE *****************************************/
	
			
		}
		
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		 
		
	    // update the uprofile nodes, etc
		
	    // update the node record to reflect whether published or not    
		/*
		if(aHeadObj.getUSPVolResume().length() > 2){
			aszSQLdrupal101 = aszSQLdrupal101 +	"'0','0','1'," ; // unpublished by default and put into moderation if they posted a resume
		}else if(aHeadObj.getUSPVolunteerTID()==iVolDirectorytid){ 
			aszSQLdrupal101 = aszSQLdrupal101 +	"'1','0','0'," ; // published b/c user said to list them in the directory
		}else{ // this should include user profiles for organizations
			aszSQLdrupal101 = aszSQLdrupal101 +	"'0','0','0'," ; // the user has stated that they don't want to be published
		}
		status, comment, moderate
		*/
		if(aHeadObj.getUSPVolResume().length() > 2){
			aszSQL101="UPDATE " + aszDrupalDB + "node " +
				"SET status=0, comment=0, moderate=1 WHERE nid=" + iUPnid + " AND vid=" + iUPvid + " "; 
		}else if(aHeadObj.getUSPVolunteerTID()==iVolDirectorytid){
			aszSQL101="UPDATE " + aszDrupalDB + "node " +
				"SET status=1, comment=0, moderate=0 WHERE nid=" + iUPnid + " AND vid=" + iUPvid + " "; 
		}else{
			aszSQL101="UPDATE " + aszDrupalDB + "node " +
				"SET status=0, comment=0, moderate=0 WHERE nid=" + iUPnid + " AND vid=" + iUPvid + " "; 
		}
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
			return -1;
		}
		iRetCode=0;

		if(  aHeadObj.getUSPEmail2Addr().length() > 4 				&&
			(! aHeadObj.getUSPEmail1Addr().equalsIgnoreCase(aHeadObj.getUSPEmail2Addr()) )
		){
			aszSQL101="UPDATE " + aszDrupalDB + "users " +
				"SET mail='"+ aHeadObj.getUSPEmail2Addr() + "' WHERE uid=" + iuid + " "; 
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
				return -1;
			}
			iRetCode=0;
		}

	    // update the uprofile cck    
	    aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
				"SET " +
				"field_internship_interest_value=?,field_resume_value=?,field_first_name_value=?,field_last_name_value=?,field_is_christian_value=?," +
				"field_site_use_type_value=?,field_birth_year_value=?,field_church_attendance_value=?," +
				//"field_voluser_auth_tokens_value=?," +
				"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=?,field_subscribe_opps_posti_value=? ";
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){		
	    	aszSQL101 = aszSQL101 + ",field_facebook_uid_value=? "; 
	    	// grab the current value of personality type
			Qry2 = "SELECT tn.tid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB +
			"term_data td " + "WHERE tn.tid = td.tid and tn.nid =" + iUPnid + " and td.vid = 336";  
			iRetCode=pConn.PrepQuery(Qry2);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
	            ErrorsToLog();
				return -1;
			}
			if(pConn.getNextResult()){
				personalityTID = pConn.getDBInt("tid");
			}
			if(personalityTID != aHeadObj.getUSPPersonalityTID()){
				aszSQL101 = aszSQL101 + ",field_personality_published_value=? ";
			}
		}
		
		aszSQL101 = aszSQL101 +	"WHERE nid=" + iUPnid + ""; 
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		int internshipInterest = 0;
		if(aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("united states") ||
		   aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("us"))
			internshipInterest = aHeadObj.getInternshipInterest() ? 1 : 0;
		iRetCode = pConn.setPrepQryInt(internshipInterest);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;			
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getUSPVolResume() ));
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPChristianP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAttendChurchP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAuthTokens() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		*/
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getUSPSubscribe() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
			iRetCode = pConn.setPrepQryString( aHeadObj.getFacebookUID());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			if(personalityTID != aHeadObj.getUSPPersonalityTID()){
				iRetCode = pConn.setPrepQryLong( 0 );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
		}
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=0;
		
// this should all be wrapped in a conditional that checks to see if a location entry already exists; in that case, it should update.  only insert
// if none exists
		// if the user says they are using the site for ONLY organization purposes
		// && the user has not entered anything in any of the address/location fields,
		// no insert/update of location or location_phone needs to be done; otherwise, 
		// YES, it does
		
		if ( (aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)) &&
				aHeadObj.getUSPAddrLine1().length() < 1 &&
				aHeadObj.getUSPAddrCity().length() < 1 &&
				//aHeadObj.getUSPAddrStateprov().length() < 1 && // - dropdowns possibly default to values
				aHeadObj.getUSPAddrPostalcode().length() < 1 
				//&& aHeadObj.getUSPAddrCountryName().length() < 1 - dropdowns possibly default to values
		){
			// DO NOT INSERT/UPDATE A LOCATION FOR AN ORGANIZATIONAL-ONLY RECORD THAT DOES NOT HAVE ANY OF THOSE FIELDS FILLED OUT 
		}else{
			// um_location table
			// it might be BOTH instead of Organization; have some input for address, etc, BUT not have a location instance yet
			if (
				aHeadObj.getUserProfileLID() < 1		||
				aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	// users, uprofile - location does not exist
			){
				// 2009-01-10
				//INSERT INTO um_location;
				//	 the userprofile already existed, but the LOCATION did NOT exist yet - possible it used to be ORG account and is now VOL
				// insert into location
			
				// add to um_location
				lUniqueID=-1 ;
				lNextUniqueID=-1 ;
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
				
				aszSQL101="INSERT INTO " + aszDrupalDB + "location " +
						"(name,street,additional,city,province,postal_code,country,source,is_primary) " +
						"VALUES('',?,'',?,?,?,?,0,0)"; 
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
				aHeadObj.setUSPInternalUserTypeComment(aszProfileLocationUser);	
		
				iRetCode=0;
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
					aHeadObj.setUserProfileLID(ilid);
				} else {
					itid=-1;
				}
				iRetCode=0;
				
//**********************************************************				
// 				ilid=lNextUniqueID; // lid for new location instance
//**********************************************************				
				
				aszSQL101="INSERT INTO " + aszDrupalDB + "location_instance " +
						"(lid,nid, vid, genid) " +
						"VALUES(" + ilid + "," + iUPnid + "," + iUPvid + ",'')"; 
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
					return -1;
				}
				iRetCode=0;
			}else{
				//UPDATE um_location WHERE lid=aHeadObj.getUserProfileLID();
				ilid = aHeadObj.getUserProfileLID();
				// update the uprofile cck location
				aszSQL101="UPDATE " + aszDrupalDB + "location " +
						"SET " +
						"street=?,city=?,province=?,postal_code=?,country=?,source=0,is_primary=0 " + 
						"WHERE lid=" + ilid + ""; 
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
		
			// end um_location
//*
			
			int iProfileId=0;
			//	*****  Grab the last auto-incremented ID and save it as the lid for this location/node *****************
			Qry1 = "SELECT id profile_id FROM  " + aszRailsDB + "profiles WHERE drupal_uprofile_nid= "+aHeadObj.getUserProfileNID();
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
				iProfileId = pConn.getDBInt("profile_id");
			} else {
				itid=-1;
			}
			iRetCode=0;
			//um_location_phone table
			
			
			// if a phone number was entered, add it to the um_location_phone table
			if(aHeadObj.getUSPPhone1().length()>1 && iProfileId>0){
				if (
					aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileLocationUser)	
				){
					// 2009-01-10
					//INSERT INTO um_location_phone;
					aszSQL101="INSERT INTO " + aszRailsDB + "profile_phones  " +
							"(profile_id,phone_number,phone_type,created_at, updated_at) " +
							"VALUES(" + iProfileId + ",?,'main',{fn now()},{fn now()})"; 
					iRetCode=pConn.PrepQuery(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
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
					aHeadObj.setUSPInternalUserTypeComment(aszFullUser);	
					iRetCode=0;					
				}else if(iProfileId>0){
					//UPDATE um_location_phone WHERE lid=aHeadObj.getUserProfileLID();
				    ilid = aHeadObj.getUserProfileLID();
				    // update the uprofile cck
					    
					aszSQL101="UPDATE " + aszRailsDB + "profile_phones " +
							"SET phone_number=?, updated_at= {fn now()} " + 
							"WHERE profile_id=" + iProfileId + ""; 
					iRetCode=pConn.PrepQuery(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
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
//*/		
		}
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidVolLang + "," + vidVolOrgAffil + "," + vidVolAvail + 
				"," + vidVolBoard + "," + vidVolDenom + "," + vidVolInterestArea + 
				"," + vidVolSkill + "," + vidVolVirt + "," + vidVolunteer + "," + vidLookingFor ;
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
			aszSQL101 = aszSQL101 + "," + vidPersonality + "," + vidCauseTopic + "," + vidOtherSkills +
			"," + vidOtherPassions + "," + vidOtherLearningInterests;
		}
		aszSQL101 = aszSQL101 + ") ) ";
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
		
		// add Skills
		if ( ! aHeadObj.getUSPVolSkills().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills1TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills1TID(itid);
				aHeadObj.setUSPVolSkills(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Skills 2
		if ( ! aHeadObj.getUSPVolSkills2().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills2TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills2TID(itid);
				aHeadObj.setUSPVolSkills2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Skills 3
		if ( ! aHeadObj.getUSPVolSkills3().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills3TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills3TID(itid);
				aHeadObj.setUSPVolSkills3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		// add InterestArea
		if ( ! aHeadObj.getUSPVolInterestArea1().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea1TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea1TID(itid);
				aHeadObj.setUSPVolInterestArea1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add InterestArea 2
		if ( ! aHeadObj.getUSPVolInterestArea2().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea2TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea2TID(itid);
				aHeadObj.setUSPVolInterestArea2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add InterestArea 3
		if ( ! aHeadObj.getUSPVolInterestArea3().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea3TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea3TID(itid);
				aHeadObj.setUSPVolInterestArea3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		// add LangSpoken
		if ( ! aHeadObj.getUSPVolLang1().equalsIgnoreCase("") || aHeadObj.getUSPVolLang1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang1TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang1TID(itid);
				aHeadObj.setUSPVolLang1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add LangSpoken 2
		if ( ! aHeadObj.getUSPVolLang2().equalsIgnoreCase("") || aHeadObj.getUSPVolLang2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang2TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang2TID(itid);
				aHeadObj.setUSPVolLang2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add LangSpoken 3
		if ( ! aHeadObj.getUSPVolLang3().equalsIgnoreCase("") || aHeadObj.getUSPVolLang3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang3TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang3TID(itid);
				aHeadObj.setUSPVolLang3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// add UserOrgAffil
		if ( ! aHeadObj.getUSPOtherAffilP().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil1TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil1TID(itid);
				aHeadObj.setUSPOtherAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add User Org Affiliation 2
		if ( ! aHeadObj.getUSPOtherAffil2().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil2TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil2TID(itid);
				aHeadObj.setUSPOtherAffil2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add User Org Affiliation 3
		if ( ! aHeadObj.getUSPOtherAffil3().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil3TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil3TID(itid);
				aHeadObj.setUSPOtherAffil3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Volunteer Denominational Affiliation
		if ( ! aHeadObj.getUSPDenomAffilP().equalsIgnoreCase("") || aHeadObj.getUSPDenomAffilTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPDenomAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPDenomAffilTID() + 
				") AND vid = " + vidVolDenom + " ";
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
				aHeadObj.setUSPDenomAffilTID(itid);
				aHeadObj.setUSPDenomAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add whether to make this Volunteer Profile public in Volunteer Listings
		if ( ! aHeadObj.getUSPVolunteer().equalsIgnoreCase("") || aHeadObj.getUSPVolunteerTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolunteer().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolunteerTID() + 
				") AND vid = " + vidVolunteer + " ";
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
				aHeadObj.setUSPVolunteerTID(itid);
				aHeadObj.setUSPVolunteer(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Volunteer Availability
		if ( ! aHeadObj.getUSPVolAvail().equalsIgnoreCase("") || aHeadObj.getUSPVolAvailTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolAvail().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolAvailTID() + 
				") AND vid = " + vidVolAvail + " ";
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
				aHeadObj.setUSPVolAvailTID(itid);
				aHeadObj.setUSPVolAvail(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
/*
		// add Volunteer Board
		if ( ! aHeadObj.getUSPVolBoard().equalsIgnoreCase("") || aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolBoard().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolBoardTID() + 
				") AND vid = " + vidVolBoard + " ";
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
				aHeadObj.setUSPVolBoardTID(itid);
				aHeadObj.setUSPVolBoard(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Virtual Volunteer
		if ( ! aHeadObj.getUSPVolVirt().equalsIgnoreCase("") || aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolVirt().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolVirtTID() + 
				") AND vid = " + vidVolVirt + " ";
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
				aHeadObj.setUSPVolVirtTID(itid);
				aHeadObj.setUSPVolVirt(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
*/
		/** add Looking for... options */
		// add Looking For - Local Volunteering
		if ( aHeadObj.getUSPLocalVolTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalVolTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Group/Family Volunteering
		if ( aHeadObj.getUSPGroupFamilyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPGroupFamilyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Volunteer on a Nonprofit Board
		if ( aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolBoardTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Virtual Volunteering
		if ( aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolVirtTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Internship Opportunities
		if ( aHeadObj.getUSPIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Summer Internship Opportunities
		if ( aHeadObj.getUSPSumIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSumIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - WorkStudy Opportunities
		if ( aHeadObj.getUSPWorkStudyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPWorkStudyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Jobs in Urban Ministry
		if ( aHeadObj.getUSPJobsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPJobsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Opportunities to Speak at a Conference
		if ( aHeadObj.getUSPConferenceTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConferenceTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Consulting Opportunities
		if ( aHeadObj.getUSPConsultingTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConsultingTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Social Justice & Christian Groups
		if ( aHeadObj.getUSPSocJustGrpsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSocJustGrpsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Organizations
		if ( aHeadObj.getUSPLocalOrgsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalOrgsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		/** END add Looking for... options */
		
		/** add Personality Type */
		// add Personality Type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
//				java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		
//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			while(tempLookingFor.length() > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				int tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
				tempLookingFor = tempLookingFor.substring((commaPlace + 1), tempLookingFor.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidLookingFor + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			while(tempService.length() > 1){
				int commaPlace = tempService.indexOf(",");
				int tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
				tempService = tempService.substring((commaPlace + 1), tempService.length());
			
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			while(tempSkills.length() > 1){
				int commaPlace = tempSkills.indexOf(",");
				int tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			while(tempSpirit.length() > 1){
				int commaPlace = tempSpirit.indexOf(",");
				int tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
				tempSpirit = tempSpirit.substring((commaPlace + 1), tempSpirit.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				int tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
				tempMin = tempMin.substring((commaPlace + 1), tempMin.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			while(tempGlobal.length() > 1){
				int commaPlace = tempGlobal.indexOf(",");
				int tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
				tempGlobal = tempGlobal.substring((commaPlace + 1), tempGlobal.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			while(tempOrg.length() > 1){
				int commaPlace = tempOrg.indexOf(",");
				int tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
				tempOrg = tempOrg.substring((commaPlace + 1), tempOrg.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			while(tempRec.length() > 1){
				int commaPlace = tempRec.indexOf(",");
				int tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
				tempRec = tempRec.substring((commaPlace + 1), tempRec.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					itid=-1;
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
				}
				itid=-1;
				}
			}
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
			}
		}
		/*
		 * add ARRAY items from multi-select form
		 */
		// add Languages Array
		if ( aHeadObj.getUSPLanguagesArray()!=null ){
			if ( aHeadObj.getUSPLanguagesArray().length > 0 ){
				index=0;
				int[] tempLanguages = aHeadObj.getUSPLanguagesArray();
				for (int i=0; i<tempLanguages.length; i++){
					int tempTID = tempLanguages[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidVolLang + " ";
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
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						// need to add code to handle if someone chooses the same skill more than once in the same form
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						//iRetCode=-1;
					} else {
						itid=-1;
					}
					itid=-1;
				}
			}
		}		
		// add Looking For Array
		if ( aHeadObj.getUSPLookingForArray()!=null ){
			if ( aHeadObj.getUSPLookingForArray().length > 0 ){
				index=0;
				int[] tempLookingFor = aHeadObj.getUSPLookingForArray();
				for (int i=0; i<tempLookingFor.length; i++){
					int tempTID = tempLookingFor[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidLookingFor + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Service Areas Array
		if ( aHeadObj.getUSPServiceAreasArray()!=null ){
			if ( aHeadObj.getUSPServiceAreasArray().length > 0 ){
				index=0;
				int[] tempService = aHeadObj.getUSPServiceAreasArray();
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Skills Array
		if ( aHeadObj.getUSPSkillTypesArray()!=null ){
			if ( aHeadObj.getUSPSkillTypesArray().length > 0 ){
				index=0;
				int[] tempSkillTypes = aHeadObj.getUSPSkillTypesArray();
				for (int i=0; i<tempSkillTypes.length; i++){
					int tempTID = tempSkillTypes[index];
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Spiritual Dev Array
		if ( aHeadObj.getUSPSpiritualDevArray()!=null){
			if ( aHeadObj.getUSPSpiritualDevArray().length > 0 ){
				index=0;
				int[] tempSpiritualDev = aHeadObj.getUSPSpiritualDevArray();
				for (int i=0; i<tempSpiritualDev.length; i++){
					int tempTID = tempSpiritualDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Ministry Area Array
		if ( aHeadObj.getUSPMinistryAreasArray()!= null ){
			if ( aHeadObj.getUSPMinistryAreasArray().length > 0 ){
				index=0;
				int[] tempMinistryAreas = aHeadObj.getUSPMinistryAreasArray();
				for (int i=0; i<tempMinistryAreas.length; i++){
					int tempTID = tempMinistryAreas[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Global Issues Array
		if ( aHeadObj.getUSPGlobalIssuesArray()!= null){
			if ( aHeadObj.getUSPGlobalIssuesArray().length > 0 ){
				index=0;
				int[] tempGlobalIssues = aHeadObj.getUSPGlobalIssuesArray();
				for (int i=0; i<tempGlobalIssues.length; i++){
					int tempTID = tempGlobalIssues[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Organizational Development Array
		if ( aHeadObj.getUSPOrganizationalDevArray()!= null ){
			if ( aHeadObj.getUSPOrganizationalDevArray().length > 0 ){
				index=0;
				int[] tempOrganizationalDev = aHeadObj.getUSPOrganizationalDevArray();
				for (int i=0; i<tempOrganizationalDev.length; i++){
					int tempTID = tempOrganizationalDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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
		// add Reconciliation & Culture Array
		if ( aHeadObj.getUSPReconciliationArray()!=null ){
			if ( aHeadObj.getUSPReconciliationArray().length > 0 ){
				index=0;
				int[] tempReconciliation = aHeadObj.getUSPReconciliationArray();
				for (int i=0; i<tempReconciliation.length; i++){
					int tempTID = tempReconciliation[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
						//iRetCode=-1;
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

//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//iRetCode=-1;
				} else {
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		
		
		
		return 0;
	}
	// end-of method insertUserDataDB()

	


	/**
	* remove session exist with given ip address in sessions table?
	* return 0 if yes found username
	*/
	public int deleteSessionIDFromSystem( ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		String aszSQL=null;
        MethodInit("deleteSessionIDFromSystem");
		if(null == pConn) return -1;
		if(null == aHeadObj) return -1;
        aszSQL = " " +
        		"DELETE  " +
        			" FROM " + aszDrupalVolengDB + "sessions " +
        			" WHERE sid='" + aHeadObj.getSessionValue() + "' and hostname ='" + aHeadObj.getSessionIP() + "'";
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode=pConn.PrepQuery(aszSQL);
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
		return 0;
    }
    // end-of method deleteSessionIDFromSystem()


    /**
	* second part of creating contact user (drupal does actual user creation; this does node creation for the user
	*/
	public int insertContactUserDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB ){
		int iRetCode=0;
		int iUPnid=-1, iUPvid=-1, ilid=-1;
		String Qry1=null;
		String aszSQLdrupal101=null;

		MethodInit("insertContactUserDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		aHeadObj.setUserRoleID(iRid);
		/*
			 * apply the ChristianVolunteering.org user role to the new drupal user
			 */
			aszSQLdrupal101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ aHeadObj.getUserUID() +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.getDBStmt(); 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
System.out.println("will add user role");
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
System.out.println("added user role");


		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		
		// um_content_type_uprofile table
			Qry1=null;
			if(null == pConn){
				setErr("null database connection");
				return -1;
			}
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				return -1;
			}
			// add to um_node_revisions
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format) " +
					" VALUES(?,?,'','',UNIX_TIMESTAMP({fn now()}),0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
System.out.println("before node_revisions");
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
System.out.println("after node_revisions");
			iRetCode=-1;

			//	*****  Grab the last auto-incremented ID and save it as the vid for this node *****************
			Qry1 = "SELECT LAST_INSERT_ID() ";
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPvid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserProfileVID(iUPvid);
			} else {
				iUPvid=-1;
			}
			iRetCode=-1;
System.out.println("after node_revisions  iUPvid is "+iUPvid);
			
			// add to um_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate," +
					" title, uid, created, changed) " +
					" VALUES("+ iUPvid +",'uprofile'," ;

			aszSQLdrupal101 = aszSQLdrupal101 +	"0,0,0," ; // the user has not filled in all details, so should not yet be published
			
			aszSQLdrupal101 = aszSQLdrupal101 +	" ?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
System.out.println("just before running insert into um_node");
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
System.out.println("after insert into um_node");			
			//	*****  Grab the last auto-incremented ID and save it as the nid for this node *****************
			Qry1 = "SELECT LAST_INSERT_ID() ";
System.out.println("Qry1 is "+Qry1);			
			iRetCode=pConn.PrepQuery(Qry1);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
			    ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
System.out.println("adding contact user - iUPnid is "+iUPnid);				
				aHeadObj.setUserProfileNID(iUPnid);
			} else {
				iUPnid=-1;
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
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileNID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileVID() );
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
System.out.println("adding contact user - updated revisions  ");				
			
			// add to um_node_access
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
					" VALUES("+ iUPnid +",0,'all',1,0,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_node_comment_statistics
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
					" VALUES("+ iUPnid +",UNIX_TIMESTAMP({fn now()}),?,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
System.out.println("adding contact user - added to access and comment stats  ");				
			// add to um_content_type_uprofile
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_uprofile " +
					"(nid,vid," +
					"field_first_name_value,field_last_name_value,field_site_use_type_value,field_birth_year_value," +
					"field_voluser_subdomain_value,field_voluser_agree2_flag_value,field_subscribe_opps_posti_value";
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value";
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ") " +	"VALUES " +
					"(" + iUPnid + "," + iUPvid + "," +
							"?,?,?,?,?,?,0";// by default, for step 1, don't subscribe this user to any kind of email system
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				aszSQLdrupal101 = aszSQLdrupal101 +	",?";
					//,?) "; 
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ")";
			iRetCode=pConn.getDBStmt(); 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() ); // make sure this has a value
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				//iRetCode=pConn.setPrepQryLong( aHeadObj.getFacebookUID() );
				iRetCode=pConn.setPrepQryString( aHeadObj.getFacebookUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
System.out.println("adding contact user - added to uprofile  ");				
			iRetCode=-1;//0????????????????????

		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
			
			// add to ChrisvolOnRails app table
			aszSQLdrupal101="INSERT IGNORE INTO " + aszRailsDB + "profiles(drupal_uprofile_nid, created_at, updated_at)" +
					" VALUES("+ iUPnid +",'{fn now()}','{fn now()}')" ;

System.out.println("adding contact user - chrisvolon rails insert should be  "+aszSQLdrupal101);				
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
System.out.println("adding contact user - chrisvolon rails error prepping query  "+aszSQLdrupal101);				
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
System.out.println("adding contact user - chrisvolon rails error running query  "+aszSQLdrupal101);				
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=0;//-1;
System.out.println("adding contact user - added to rails  ");				
			iRetCode=-1;//0????????????????????


			//need to insert into location tables
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location " +
					"(name,street,additional,city,province,postal_code,country,source,is_primary) " +
					"VALUES('',?,'',?,?,?,?,0,0)"; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
//			aHeadObj.setUSPInternalUserTypeComment(aszProfileLocationUser);	
	
			iRetCode=0;
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
				aHeadObj.setUserProfileLID(ilid);
			} else {
				ilid=-1;
			}
			iRetCode=0;
			
//**********************************************************				
//				ilid=lNextUniqueID; // lid for new location instance
//**********************************************************				
			
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_instance " +
					"(lid,nid, vid, genid) " +
					"VALUES(" + ilid + "," + iUPnid + "," + iUPvid + ",'')"; 
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
			iRetCode=0;
			
			
//		 if it's been successful up to this point, the new user is now a Full User
		//aHeadObj.setUSPInternalUserTypeComment(aszFullUser);
		aHeadObj.setUSPPasswordInternal(aHeadObj.getUSPPassword());
		
		 
		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()>0){
System.out.println("adding contact user - in a portal  ");				
	    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + "," + aHeadObj.getUserUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
System.out.println("adding contact user - in a portal  "+aszSQLdrupal101);				
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
		    		// get number of counts of the favorites flag for this content id
					aszSQLdrupal101 = " SELECT count " +
						" FROM " + aszDrupalDB + "flag_counts fl " +
						" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + aHeadObj.getPortal() + " AND content_type LIKE 'node' "; 
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -999;
					}
					iRetCode = pConn.RunQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						//return -999;
					}
					iRetCode=-1;
					// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
					if(pConn.getNextResult()){
						iInitCount=pConn.getDBInt("count");
					}
	
	    			iChangeNumber++;// successfully added and not a duplicate
	        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
	            	iCurrentCount = iInitCount + iChangeNumber;
	            	if(iCurrentCount<0) iCurrentCount=0;
	            	if(iCurrentCount==0){
	            	}else{
						// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
						aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
			    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + ","+iCurrentCount+") "+
			    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
			    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
			    		if(0 != iRetCode){
			    			pConn.copyErrObj(getErrObj());
			    			ErrorsToLog();
			    			//return -1;
			    		}
	            	}
				}
			}
			// if successful - insert into the taxonomy as well
			aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "term_node (tid, vid, nid) " +
    				" VALUES (" + aHeadObj.getPortal() + ","+iUPvid+ ","+iUPnid+") "; 
    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
    		if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
    			ErrorsToLog();
    			//return -1;
    		}

		}
		
		return 0;
	}
	// end-of method insertContactUserDB()



	
    /**
	* a drupal user is being integrated into the voleng system; basically, needs to have a uprofile nid to work with for ownership.
	*/
	public int generateContactFromDrupalDBUSP(ABREDBConnection pConn, PersonInfoDTO aHeadObj ){
		int iRetCode=0;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		int iTermsConditionsVersion = 2;
		String Qry1=null;
		String aszSQLdrupal101=null;
		String aszUProfileExists="false";
		String aszSQLuprofile;
		MethodInit("generateContactFromDrupalDBUSP");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		if(aHeadObj.getUserRoleID()==0){
			aHeadObj.setUserRoleID(iRid);
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		aszSQLdrupal101="UPDATE " + aszDrupalDB + "users SET access=UNIX_TIMESTAMP({fn now()}) WHERE uid=" + aHeadObj.getUserUID();
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
		iRetCode=-1;

		if (aHeadObj.getUSPAgree1Fg().length() > 0){
		    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "legal_accepted(uid, version, accepted,revision,language) " +//legal_accepted(uid, tc_id, accepted) " +
					" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ,1,'en') ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		}
			
		if(aHeadObj.getUserRoleID()>-1){
			/*
			 * apply the ChristianVolunteering.org user role to the drupal user
			 */
			aszSQLdrupal101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ aHeadObj.getUserUID() +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		}
		

		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		
		// check to see if there are any uprofile's owned by the given uid; if so, we will update it.  Otherwise, we will create a new node
		aszSQLuprofile= "SELECT users.name username, users.mail, users.uid, n.nid, n.vid, up.field_first_name_value," +
				" up.field_last_name_value," +
			" up.field_site_use_type_value, up.field_birth_year_value, " + 
			" FROM  " + aszDrupalDB + "users users , " + aszDrupalDB + "node n, " + aszDrupalDB + "content_type_uprofile up " + 
			" WHERE users.uid=" + aHeadObj.getUserUID() + " AND users.uid=n.uid AND n.nid=up.nid";
		
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode = pConn.RunQuery(aszSQLuprofile);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		iRetCode=-1;
		if(pConn.getNextResult()){ // everything already exists for this user; fully chrisvol-ized from drupal
            iRetCode=0;
			aHeadObj.setUserUID(pConn.getDBInt("uid")); // to help handle ownership on drupal interface
			aHeadObj.setUserProfileNID(pConn.getDBInt("n.nid"));
			aHeadObj.setUserProfileVID(pConn.getDBInt("n.vid"));
			aHeadObj.setUSPUsername(pConn.getDBString("username"));
			aHeadObj.setUSPEmail1Addr(pConn.getDBString("users.mail"));
			aHeadObj.setUSPNameFirst(pConn.getDBString("up.field_first_name_value"));
			aHeadObj.setUSPNameLast(pConn.getDBString("up.field_last_name_value"));
			
			iUPnid=pConn.getDBInt("n.nid");
			iUPvid=pConn.getDBInt("n.vid");
			aszUProfileExists="true";
			
		}else{
			aszUProfileExists="false";
		}
		// um_content_type_uprofile table
		if(
				aszUProfileExists.equalsIgnoreCase("false")
		){
			// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
			lUniqueID=-1 ;
			lNextUniqueID=-1 ;
			Qry1=null;
			if(null == pConn){
				setErr("null database connection");
				return -1;
			}
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				return -1;
			}
			// add to um_node_revisions
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format) " +
					" VALUES(?,?,'','',UNIX_TIMESTAMP({fn now()}),0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
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
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPvid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserProfileVID(iUPvid);
			} else {
				itid=-1;
			}
			iRetCode=-1;
			

//*****************************************************			
//			aHeadObj.setUserProfileVID(lNextUniqueID);
//			iUPvid=
//*****************************************************	
			
			// add to um_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate," +
					" title, uid, created, changed) " +
					" VALUES("+ iUPvid +",'uprofile'," ;
			if(aHeadObj.getUSPVolResume().length()<2){
				aszSQLdrupal101 = aszSQLdrupal101 +	"0,0,1," ; // put volunteer user profiles into moderation if they have resume
			}else{
				aszSQLdrupal101 = aszSQLdrupal101 +	"1,0,0," ; // put volunteer user profiles into moderation
			}
			aszSQLdrupal101 = aszSQLdrupal101 +	" ?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
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
			iRetCode = pConn.ExePrepQry();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserProfileNID(iUPnid);
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
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileNID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileVID() );
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
			
//*****************************************************			
//			aHeadObj.setUserProfileNID(lNextUniqueID);
//			iUPnid=
//*****************************************************	
			
//			****************************************************************
//****** update um_node_revisions set nid=iUPnid where vid=iUPvid     ***********
//			****************************************************************
//			****************************************************************
			
			// add to um_node_access
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
					" VALUES("+ iUPnid +",0,'all',1,0,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_node_comment_statistics
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
					" VALUES("+ iUPnid +",UNIX_TIMESTAMP({fn now()}),?,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_content_type_uprofile
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_uprofile " +
					"(nid,vid,field_about_me_format,field_resume_format," +
					"field_first_name_value,field_last_name_value," +
					"field_site_use_type_value,field_birth_year_value" +
					"field_voluser_subdomain_value,field_voluser_agree2_flag_value)" +
					"VALUES (" + iUPnid + "," + iUPvid + ",1,1,?,?,?,?,?," +
							"?)";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() ); // make sure this has a value
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;//0????????????????????

		}else{ // uprofile already exists; update it
		
		    iUPnid = aHeadObj.getUserProfileNID();
		    iUPvid = aHeadObj.getUserProfileVID();
		    // update the uprofile cck
			    
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
					"SET " +
					"field_first_name_value=?,field_last_name_value=?," +
					"field_site_use_type_value=?,field_birth_year_value=?," +
					"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=? " +
					"WHERE nid=" + iUPnid + ""; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
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

		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/		
		
		return 0;
	}
	// end-of method generateContactFromDrupalDBUSP()


	/**
	* update a row in table userprofileinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBUSP(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB){
		int iRetCode=0;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1, personalityTID=-1;
		String aszSQLdrupal101 = "";
		int index=0;
		String tempTaxonomy = "";
		String aszSQL101 = "";
		String Qry1 = "";
		String Qry2 = "";
		MethodInit("updateDBUSP");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}

		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
System.out.println("inside updateDBUSP in DBDAO");
		if(aHeadObj.getUserRoleID()==0){
			aHeadObj.setUserRoleID(iRid);
		}
	    
		if(aHeadObj.getUserRoleID()>-1){
			/*
			 * apply the ChristianVolunteering.org user role in case there is no chrisvol role for this user yet
			 */
			aszSQL101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ iuid +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..." - user role already exists
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		}
	    
	    // update the uprofile nodes, etc
		
	    // update the node record to reflect whether published or not    
		if(aHeadObj.getUSPVolunteerTID()==iVolDirectorytid){
			aszSQL101="UPDATE " + aszDrupalDB + "node " +
				"SET status=1 WHERE nid=" + iUPnid + " AND vid=" + iUPvid + " "; 
		}else{
			aszSQL101="UPDATE " + aszDrupalDB + "node " +
				"SET status=0 WHERE nid=" + iUPnid + " AND vid=" + iUPvid + " "; 
		}
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
			return -1;
		}
		iRetCode=0;
		
	    // update the uprofile cck    
	    aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
				"SET " +
				"field_internship_interest_value=?,field_resume_value=?,field_first_name_value=?,field_last_name_value=?,field_is_christian_value=?," +
				"field_site_use_type_value=?,field_birth_year_value=?,field_church_attendance_value=?," +
				//"field_voluser_user_id_value=?," +
				//"field_voluser_auth_tokens_value=?," +
				"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=?,field_subscribe_opps_posti_value=? ";
				//",field_do_you_want_to_volunteer_value=? " + 		
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){		
	    	//aszSQL101 = aszSQL101 + ",field_facebook_user_id_value=? "; 
	    	aszSQL101 = aszSQL101 + ",field_facebook_uid_value=? "; 
	    	
//	    	 grab the current value of personality type
			Qry2 = "SELECT tn.tid FROM " + aszDrupalDB + "term_node tn, " + aszDrupalDB +
			"term_data td " + "WHERE tn.tid = td.tid and tn.nid =" + iUPnid + " and td.vid = 336";  
			iRetCode=pConn.PrepQuery(Qry2);
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
				personalityTID = pConn.getDBInt("tid");
			}
	    	
			if(personalityTID != aHeadObj.getUSPPersonalityTID()){
				aszSQL101 = aszSQL101 + ",field_personality_published_value=? ";
			}
		}
		
		aszSQL101 = aszSQL101 +	"WHERE nid=" + iUPnid + ""; 
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		int internshipInterest = 0;
		if(aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("us") ||
		   aHeadObj.getUSPAddrCountryName().equalsIgnoreCase("united states"))
			internshipInterest = aHeadObj.getInternshipInterest() ? 1 : 0;
		iRetCode = pConn.setPrepQryInt(internshipInterest);
		if(0 != iRetCode) {
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;			
		}
		iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getUSPVolResume() ));
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPChristianP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAttendChurchP() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		/*
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAuthTokens() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		*/
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryInt( aHeadObj.getUSPSubscribe() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
			//iRetCode = pConn.setPrepQryLong( aHeadObj.getFacebookUID());
			iRetCode = pConn.setPrepQryString( aHeadObj.getFacebookUID());
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			if(personalityTID != aHeadObj.getUSPPersonalityTID()){
				iRetCode = pConn.setPrepQryLong( 0 );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
		}
		iRetCode = pConn.ExecutePrepQry();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=0;
System.out.println("ran update on uprofile table;   aHeadObj.getUSPSiteUseType() is "+aHeadObj.getUSPSiteUseType());		
// this should all be wrapped in a conditional that checks to see if a location entry already exists; in that case, it should update.  only insert
// if none exists
		// if the user says they are using the site for ONLY organization purposes
		// && the user has not entered anything in any of the address/location fields,
		// no insert/update of location or location_phone needs to be done; otherwise, 
		// YES, it does
int iTEMP=aHeadObj.getUserProfileLID();
String aszTEMP=aHeadObj.getUSPInternalUserTypeComment();
System.out.println(aszTEMP);
		if ( //(				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszCVIntern)	)||
				(aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)) &&
				aHeadObj.getUSPAddrLine1().length() < 1 &&
				aHeadObj.getUSPAddrCity().length() < 1 &&
				//aHeadObj.getUSPAddrStateprov().length() < 1 && // - dropdowns possibly default to values
				aHeadObj.getUSPAddrPostalcode().length() < 1 
				//&& aHeadObj.getUSPAddrCountryName().length() < 1 - dropdowns possibly default to values
		){
			// DO NOT INSERT/UPDATE A LOCATION FOR AN ORGANIZATIONAL-ONLY RECORD THAT DOES NOT HAVE ANY OF THOSE FIELDS FILLED OUT 
		}else{
			// um_location table
			// it might be BOTH instead of Organization; have some input for address, etc, BUT not have a location instance yet
			if (
				aHeadObj.getUserProfileLID() < 1		||
				aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	// users, uprofile - location does not exist
			){
				// 2009-01-10
				//INSERT INTO um_location;
				//	 the userprofile already existed, but the LOCATION did NOT exist yet - possible it used to be ORG account and is now VOL
				// insert into location
			
				// add to um_location
				lUniqueID=-1 ;
				lNextUniqueID=-1 ;
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
System.out.println("inserting into location");				
				aszSQL101="INSERT INTO " + aszDrupalDB + "location " +
						"(name,street,additional,city,province,postal_code,country,source,is_primary) " +
						"VALUES('',?,'',?,?,?,?,0,0)"; 
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
				aHeadObj.setUSPInternalUserTypeComment(aszProfileLocationUser);	
		
				iRetCode=0;
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
					aHeadObj.setUserProfileLID(ilid);
				} else {
					itid=-1;
				}
				iRetCode=0;
				
//**********************************************************				
// 				ilid=lNextUniqueID; // lid for new location instance
//**********************************************************				
				
				aszSQL101="INSERT INTO " + aszDrupalDB + "location_instance " +
						"(lid,nid, vid, genid) " +
						"VALUES(" + ilid + "," + iUPnid + "," + iUPvid + ",'')"; 
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
					return -1;
				}
				iRetCode=0;
			}else{
				//UPDATE um_location WHERE lid=aHeadObj.getUserProfileLID();
				ilid = aHeadObj.getUserProfileLID();
				// update the uprofile cck location
				aszSQL101="UPDATE " + aszDrupalDB + "location " +
						"SET " +
						"street=?,city=?,province=?,postal_code=?,country=?,source=0,is_primary=0 " + 
						"WHERE lid=" + ilid + ""; 
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
		
			// end um_location
//*			
			//um_location_phone table
			
			
			int iProfileId=0;
			//	*****  Grab the last auto-incremented ID and save it as the lid for this location/node *****************
			Qry1 = "SELECT id profile_id FROM  " + aszRailsDB + "profiles WHERE drupal_uprofile_nid= "+iUPnid;
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
				iProfileId = pConn.getDBInt("profile_id");
			} else {
				itid=-1;
			}
			iRetCode=0;
			// if a phone number was entered, add it to the um_location_phone table

			
			
String tempcomment = aHeadObj.getUSPInternalUserTypeComment();
String phone =aHeadObj.getUSPPhone1();
System.out.println("8485 PersonDBDAO aHeadObj.getUSPInternalUserTypeComment() is "+aHeadObj.getUSPInternalUserTypeComment());
System.out.println("aszCVIntern is "+aszCVIntern);
System.out.println("aHeadObj.getUSPPhone1() is "+aHeadObj.getUSPPhone1());
			if(aHeadObj.getUSPPhone1().length()>1 && iProfileId>0){
				if (
					aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszCVIntern)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileLocationUser)	
				){
					// 2009-01-10
					//INSERT INTO um_location_phone;
					aszSQL101="INSERT IGNORE INTO " + aszRailsDB + "profile_phones  " +
							"(profile_id,phone_number,phone_type,created_at, updated_at) " +
							"VALUES(" + iProfileId + ",?,'main',{fn now()},{fn now()})"; 
					iRetCode=pConn.PrepQuery(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
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
					aHeadObj.setUSPInternalUserTypeComment(aszFullUser);	
					iRetCode=0;					
				}else if(iProfileId>0){
					//UPDATE um_location_phone WHERE lid=aHeadObj.getUserProfileLID();
				    ilid = aHeadObj.getUserProfileLID();
				    // update the uprofile cck
					    
					aszSQL101="UPDATE " + aszRailsDB + "profile_phones " +
							"SET phone_number=?, updated_at= {fn now()} " + 
							"WHERE profile_id=" + iProfileId + ""; 
					iRetCode=pConn.PrepQuery(aszSQL101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
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
//*/		
//*/		
		}
System.out.println("inserted into location");				
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidVolLang + "," + vidVolOrgAffil + "," + vidVolAvail + 
				"," + vidVolBoard + "," + vidVolDenom + "," + vidVolInterestArea + 
				"," + vidVolSkill + "," + vidVolVirt + "," + vidVolunteer + "," + vidLookingFor ;
		if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook")){
			aszSQL101 = aszSQL101 + "," + vidPersonality + "," + vidCauseTopic + "," + vidOtherSkills +
			"," + vidOtherPassions + "," + vidOtherLearningInterests;
		}
		aszSQL101 = aszSQL101 + ") ) ";
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
		System.out.println("persondbdao 8682 - delete terms statement aszSQL101:");
		System.out.println(aszSQL101);
		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		// add Skills
		if ( ! aHeadObj.getUSPVolSkills().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills1TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills1TID(itid);
				aHeadObj.setUSPVolSkills(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Skills 2
		if ( ! aHeadObj.getUSPVolSkills2().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills2TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills2TID(itid);
				aHeadObj.setUSPVolSkills2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add Skills 3
		if ( ! aHeadObj.getUSPVolSkills3().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills3TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills3TID(itid);
				aHeadObj.setUSPVolSkills3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		// add InterestArea
		if ( ! aHeadObj.getUSPVolInterestArea1().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea1TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea1TID(itid);
				aHeadObj.setUSPVolInterestArea1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add InterestArea 2
		if ( ! aHeadObj.getUSPVolInterestArea2().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea2TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea2TID(itid);
				aHeadObj.setUSPVolInterestArea2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add InterestArea 3
		if ( ! aHeadObj.getUSPVolInterestArea3().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea3TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea3TID(itid);
				aHeadObj.setUSPVolInterestArea3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		
		// add LangSpoken
		if ( ! aHeadObj.getUSPVolLang1().equalsIgnoreCase("") || aHeadObj.getUSPVolLang1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang1TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang1TID(itid);
				aHeadObj.setUSPVolLang1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add LangSpoken 2
		if ( ! aHeadObj.getUSPVolLang2().equalsIgnoreCase("") || aHeadObj.getUSPVolLang2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang2TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang2TID(itid);
				aHeadObj.setUSPVolLang2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add LangSpoken 3
		if ( ! aHeadObj.getUSPVolLang3().equalsIgnoreCase("") || aHeadObj.getUSPVolLang3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang3TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang3TID(itid);
				aHeadObj.setUSPVolLang3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		
		// add UserOrgAffil
		if ( ! aHeadObj.getUSPOtherAffilP().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil1TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil1TID(itid);
				aHeadObj.setUSPOtherAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add User Org Affiliation 2
		if ( ! aHeadObj.getUSPOtherAffil2().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil2TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil2TID(itid);
				aHeadObj.setUSPOtherAffil2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
		// add User Org Affiliation 3
		if ( ! aHeadObj.getUSPOtherAffil3().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil3TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil3TID(itid);
				aHeadObj.setUSPOtherAffil3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Volunteer Denominational Affiliation
		if ( ! aHeadObj.getUSPDenomAffilP().equalsIgnoreCase("") || aHeadObj.getUSPDenomAffilTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPDenomAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPDenomAffilTID() + 
				") AND vid = " + vidVolDenom + " ";
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
				aHeadObj.setUSPDenomAffilTID(itid);
				aHeadObj.setUSPDenomAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add whether to make this Volunteer Profile public in Volunteer Listings
		if ( ! aHeadObj.getUSPVolunteer().equalsIgnoreCase("") || aHeadObj.getUSPVolunteerTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolunteer().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolunteerTID() + 
				") AND vid = " + vidVolunteer + " ";
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
				aHeadObj.setUSPVolunteerTID(itid);
				aHeadObj.setUSPVolunteer(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Volunteer Availability
		if ( ! aHeadObj.getUSPVolAvail().equalsIgnoreCase("") || aHeadObj.getUSPVolAvailTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolAvail().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolAvailTID() + 
				") AND vid = " + vidVolAvail + " ";
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
				aHeadObj.setUSPVolAvailTID(itid);
				aHeadObj.setUSPVolAvail(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
/*
		// add Volunteer Board
		if ( ! aHeadObj.getUSPVolBoard().equalsIgnoreCase("") || aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolBoard().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolBoardTID() + 
				") AND vid = " + vidVolBoard + " ";
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
				aHeadObj.setUSPVolBoardTID(itid);
				aHeadObj.setUSPVolBoard(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}

		// add Virtual Volunteer
		if ( ! aHeadObj.getUSPVolVirt().equalsIgnoreCase("") || aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolVirt().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolVirtTID() + 
				") AND vid = " + vidVolVirt + " ";
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
				aHeadObj.setUSPVolVirtTID(itid);
				aHeadObj.setUSPVolVirt(pConn.getDBString("name"));
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;
				// The option the user choose for the SELECT returned no results
				//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
				//return -1;
			}
			itid=-1;
		}
*/
		/** add Looking for... options */
		// add Looking For - Local Volunteering
		if ( aHeadObj.getUSPLocalVolTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalVolTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Group/Family Volunteering
		if ( aHeadObj.getUSPGroupFamilyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPGroupFamilyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Volunteer on a Nonprofit Board
		if ( aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolBoardTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Virtual Volunteering
		if ( aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolVirtTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Internship Opportunities
		if ( aHeadObj.getUSPIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Summer Internship Opportunities
		if ( aHeadObj.getUSPSumIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSumIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - WorkStudy Opportunities
		if ( aHeadObj.getUSPWorkStudyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPWorkStudyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Jobs in Urban Ministry
		if ( aHeadObj.getUSPJobsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPJobsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Opportunities to Speak at a Conference
		if ( aHeadObj.getUSPConferenceTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConferenceTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Consulting Opportunities
		if ( aHeadObj.getUSPConsultingTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConsultingTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Social Justice & Christian Groups
		if ( aHeadObj.getUSPSocJustGrpsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSocJustGrpsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Organizations
		if ( aHeadObj.getUSPLocalOrgsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalOrgsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		/** END add Looking for... options */
		
		/** add Personality Type */
		// add Personality Type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
//				java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		
//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			int iTempLookingForLength = tempLookingFor.length();
			while(iTempLookingForLength > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				if(commaPlace<0){
					break;
				}
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
					tempLookingFor = tempLookingFor.substring((commaPlace + 1), iTempLookingForLength);
					iTempLookingForLength = tempLookingFor.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
System.out.println("line 10114 PersonDBDAO updateDBUSP triggered string out of bounds;");
System.out.println("   iTempLookingForLength is "+iTempLookingForLength+"; commaPlace is "+commaPlace+"; tempLookingFor is "+tempLookingFor+"; tempTID is "+tempTID);
				}
				
				if(tempTID>0){
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidLookingFor + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			int iTempServiceLength = tempService.length();
			while(iTempServiceLength > 1){
				int commaPlace = tempService.indexOf(",");
				if(commaPlace<0){
					break;
				}
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
					tempService = tempService.substring((commaPlace + 1), iTempServiceLength);
					iTempServiceLength = tempService.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10180 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempServiceLength is "+iTempServiceLength+"; commaPlace is "+commaPlace+"; tempService is "+tempService+"; tempTID is "+tempTID);
				}
				if(tempTID>0){
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			int iTempSkillsLength = tempSkills.length();
			while(iTempSkillsLength > 1){
				int commaPlace = tempSkills.indexOf(",");
				if(commaPlace<0){
					break;
				}
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
					tempSkills = tempSkills.substring((commaPlace + 1), iTempSkillsLength);
					iTempSkillsLength = tempSkills.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10246 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempSkillsLength is "+iTempSkillsLength+"; commaPlace is "+commaPlace+"; tempSkills is "+tempSkills+"; tempTID is "+tempTID);
				}
			if(tempTID>0){
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			int iTempSpiritLength = tempSpirit.length();
			while(iTempSpiritLength> 1){
				int commaPlace = tempSpirit.indexOf(",");
				if(commaPlace<0)	break;
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
					tempSpirit = tempSpirit.substring((commaPlace + 1), iTempSpiritLength);
					iTempSpiritLength = tempSpirit.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10311 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempSpiritLength is "+iTempSpiritLength+"; commaPlace is "+commaPlace+"; tempSpirit is "+tempSpirit+"; tempTID is "+tempTID);
				}
			if(tempTID>0){
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			int iTempMinLength = tempMin.length();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				if(commaPlace<0)	break;
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
					tempMin = tempMin.substring((commaPlace + 1), iTempMinLength);
					iTempMinLength = tempMin.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10311 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempMinLength is "+iTempMinLength+"; commaPlace is "+commaPlace+"; tempMin is "+tempMin+"; tempTID is "+tempTID);
				}
			if(tempTID>0){
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			int iTempGlobalLength = tempGlobal.length();
			while(iTempGlobalLength > 1){
				int commaPlace = tempGlobal.indexOf(",");
				if(commaPlace<0)	break;
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
					tempGlobal = tempGlobal.substring((commaPlace + 1), iTempGlobalLength);
					iTempGlobalLength = tempGlobal.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10311 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempGlobalLength is "+iTempGlobalLength+"; commaPlace is "+commaPlace+"; tempGlobal is "+tempGlobal+"; tempTID is "+tempTID);
				}
			if(tempTID>0){
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			int iTempOrgLength = tempOrg.length();
			while(iTempOrgLength > 1){
				int commaPlace = tempOrg.indexOf(",");
				if(commaPlace<0)	break;
				int tempTID = 0;
					
				try{
					tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
					tempOrg = tempOrg.substring((commaPlace + 1), iTempOrgLength);
					iTempOrgLength = tempOrg.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10508 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempOrgLength is "+iTempOrgLength+"; commaPlace is "+commaPlace+"; tempOrg is "+tempOrg+"; tempTID is "+tempTID);
				}
				if(tempTID>0){
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			int iTempRecLength = tempRec.length();
			while(iTempRecLength > 1){
				int commaPlace = tempRec.indexOf(",");
				if(commaPlace<0)	break;
				int tempTID = 0;
				
				try{
					tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
					tempRec = tempRec.substring((commaPlace + 1), iTempRecLength);
					iTempRecLength = tempRec.length();
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10508 PersonDBDAO updateDBUSP triggered string out of bounds;");
					System.out.println("   iTempRecLength is "+iTempRecLength+"; commaPlace is "+commaPlace+"; tempRec is "+tempRec+"; tempTID is "+tempTID);
				}
				if(tempTID>0){
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName="";
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				try{
					if(tempTaxonomy.contains(",")){
						commaPlace = tempTaxonomy.indexOf(",");
						tempName = tempTaxonomy.substring(0, commaPlace);
						tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
						if(tempTaxonomy.charAt(0) == ' ')
							tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
						
					}else{
						tempName = tempTaxonomy;//.substring(0, commaPlace);
						tempTaxonomy = "";//tempName;
					}
				}catch(java.lang.StringIndexOutOfBoundsException e){
					System.out.println("line 10648 PersonDBDAO updateDBUSP triggered string out of bounds; getUSPOtherPassions");					
				}
				if(tempName.length()>0){

				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
			}
			}
		}
		/*
		 * add ARRAY items from multi-select form
		 */
		// add Languages Array
		if ( aHeadObj.getUSPLanguagesArray()!=null ){
			if ( aHeadObj.getUSPLanguagesArray().length > 0 ){
				index=0;
				int[] tempLanguages = aHeadObj.getUSPLanguagesArray();
				for (int i=0; i<tempLanguages.length; i++){
					int tempTID = tempLanguages[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidVolLang + " ";
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
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
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
					}
					itid=-1;
				}
			}
		}		
		// add Looking For Array
		if ( aHeadObj.getUSPLookingForArray()!=null ){
			if ( aHeadObj.getUSPLookingForArray().length > 0 ){
				index=0;
				int[] tempLookingFor = aHeadObj.getUSPLookingForArray();
				for (int i=0; i<tempLookingFor.length; i++){
					int tempTID = tempLookingFor[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidLookingFor + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Service Areas Array
		if ( aHeadObj.getUSPServiceAreasArray()!=null ){
			if ( aHeadObj.getUSPServiceAreasArray().length > 0 ){
				index=0;
				int[] tempService = aHeadObj.getUSPServiceAreasArray();
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Skills Array
		if ( aHeadObj.getUSPSkillTypesArray()!=null ){
			if ( aHeadObj.getUSPSkillTypesArray().length > 0 ){
				index=0;
				int[] tempSkillTypes = aHeadObj.getUSPSkillTypesArray();
				for (int i=0; i<tempSkillTypes.length; i++){
					int tempTID = tempSkillTypes[index];
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Spiritual Dev Array
		if ( aHeadObj.getUSPSpiritualDevArray()!=null){
			if ( aHeadObj.getUSPSpiritualDevArray().length > 0 ){
				index=0;
				int[] tempSpiritualDev = aHeadObj.getUSPSpiritualDevArray();
				for (int i=0; i<tempSpiritualDev.length; i++){
					int tempTID = tempSpiritualDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Ministry Area Array
		if ( aHeadObj.getUSPMinistryAreasArray()!= null ){
			if ( aHeadObj.getUSPMinistryAreasArray().length > 0 ){
				index=0;
				int[] tempMinistryAreas = aHeadObj.getUSPMinistryAreasArray();
				for (int i=0; i<tempMinistryAreas.length; i++){
					int tempTID = tempMinistryAreas[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Global Issues Array
		if ( aHeadObj.getUSPGlobalIssuesArray()!= null){
			if ( aHeadObj.getUSPGlobalIssuesArray().length > 0 ){
				index=0;
				int[] tempGlobalIssues = aHeadObj.getUSPGlobalIssuesArray();
				for (int i=0; i<tempGlobalIssues.length; i++){
					int tempTID = tempGlobalIssues[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Organizational Development Array
		if ( aHeadObj.getUSPOrganizationalDevArray()!= null ){
			if ( aHeadObj.getUSPOrganizationalDevArray().length > 0 ){
				index=0;
				int[] tempOrganizationalDev = aHeadObj.getUSPOrganizationalDevArray();
				for (int i=0; i<tempOrganizationalDev.length; i++){
					int tempTID = tempOrganizationalDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Reconciliation & Culture Array
		if ( aHeadObj.getUSPReconciliationArray()!=null ){
			if ( aHeadObj.getUSPReconciliationArray().length > 0 ){
				index=0;
				int[] tempReconciliation = aHeadObj.getUSPReconciliationArray();
				for (int i=0; i<tempReconciliation.length; i++){
					int tempTID = tempReconciliation[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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

//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName="";
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				try{
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
				}catch(java.lang.IndexOutOfBoundsException e){
					System.out.println("PersonDBDAO line 11302 getUSPOtherSkills caused indexoutofbounds");
				}
			
				if(tempName.length()>0){
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
			}
		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName="";
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				try{
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
				}catch(java.lang.IndexOutOfBoundsException e){
					System.out.println("PersonDBDAO 11443 getUSPOtherLearningInterests caused outofbounds");
				}
				if(tempName.length()>0){
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
			}
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		
		
		
		return 0;
	}
	// end-of method updateDBUSP()

	
	/**
	* update a row in table users - update the login time (called on user login)
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBUSPLogin(ABREDBConnection pConn, PersonInfoDTO aHeadObj, int iType){
		int iRetCode=0;
		String aszSQL101, aszSQL101a, aszEmail=null;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPLogin");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();;

		aszSQL101 = "UPDATE " + aszDrupalDB + "users users SET users.login=UNIX_TIMESTAMP({fn now()}), access=UNIX_TIMESTAMP({fn now()}) " +
			"WHERE users.uid=" + iuid;

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
			return -1;
		}
		
        switch( iType ){
    		case PersonInfoDTO.COOKIE_USER :
    			iRetCode = IsSessionIDInSystem(pConn, aHeadObj);
    			if(iRetCode==0){ // update the session id timestamp
        			aszSQL101 = "UPDATE " + aszDrupalVolengDB + "sessions " +
								" SET uid=" + iuid + "," +
										" hostname='" + aHeadObj.getSessionIP() +"', timestamp=UNIX_TIMESTAMP({fn now()})" +
										" WHERE sid='" + aHeadObj.getSessionValue() + "'" ;
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
			    		return -1;
			    	}
    			}else{ // create the session id entry
        			aszSQL101 = "INSERT INTO " + aszDrupalVolengDB + "sessions " +
							"(uid, sid, hostname, timestamp) " +
							"VALUES(" + iuid + ",'" + aHeadObj.getSessionValue() + "'," +
									"'" + aHeadObj.getSessionIP() +"',UNIX_TIMESTAMP({fn now()}) )";
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
		    			return -1;
		    		}
    			}

        }
		return 0;
	}
	// end-of method updateDBUSPLogin()

	
	/**
	* update a row in table users - CHANGE EMAIL1
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBUSPEmail1(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, aszSQL101a, aszEmail=null;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPUserName");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

	    // update user in drupal, too **** for now this is actualy drupal.users.mail instead of drupal.users.name
		// repetitive step - already done in updateEmail, but it needs to be done in this method; maybe down the line split to name instead of mail
		aszSQL101 = "UPDATE " + aszDrupalDB + "users users SET users.mail=?, access=UNIX_TIMESTAMP({fn now()}) " +
			"WHERE users.uid=" + iuid;

		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
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
	// end-of method updateDBUSPEmail1()

	
	/**
	* update a row in table users - CHANGE USERNAME
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBUSPUserName(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, aszSQL101a, aszUsername=null, aszEmail=null ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		MethodInit("updateDBUSPUserName");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
		
		// update user in drupal, too **** for now this is actualy drupal.users.mail instead of drupal.users.name
		// repetitive step - already done in updateEmail, but it needs to be done in this method; maybe down the line split to name instead of mail
		aszSQL101 = "UPDATE " + aszDrupalDB + "users users SET users.name=?, access=UNIX_TIMESTAMP({fn now()}) " +
			"WHERE users.uid=" + iuid;

		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
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

		// update um_node - if it really is just the title (username in this case) and timestamp, this might be better suited to be in username update
		aszSQL101="UPDATE " + aszDrupalDB + "node SET changed = UNIX_TIMESTAMP({fn now()}), " +
				" title=? WHERE nid = "+ iUPnid + " AND vid = "+ iUPvid + " AND uid = " + iuid;
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			 ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;
		
		// update um_node_revisions - if it really is just the title (username in this case) and timestamp, this might be better suited to be in username update
		aszSQL101="UPDATE " + aszDrupalDB + "node_revisions SET title=?, timestamp=UNIX_TIMESTAMP({fn now()})" +
				" WHERE nid = " + iUPnid + " AND vid = " + iUPvid + " AND uid = " + iuid;
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;

		
		return 0;
	}
	// end-of method updateDBUSPUserName()

	
	
	/**
	* update a row in table users - CHANGE PASSWORD
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateDBUSPPwd(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, aszSQL101a, aszEmail=null;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		MethodInit("updateDBUSPPwd");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
		
		
		// update user in drupal, too **** for now this is actualy drupal.users.mail instead of drupal.users.name
		// repetitive step - already done in updateEmail, but it needs to be done in this method; maybe down the line split to name instead of mail
		aszSQL101 = "UPDATE " + aszDrupalDB + "users users SET users.pass=MD5(?) " +
			"WHERE users.uid=" + iuid;

		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPassword() );
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
	// end-of method updateDBUSPPwd()
	
	/**
	* update a row in table users - update the login time (called on user login)
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int updateIndividualPortalDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQLdrupal101="";
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateIndividualPortalDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()>0){
	    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + "," + aHeadObj.getUserUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
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
				}else{
		    		// get number of counts of the favorites flag for this content id
					aszSQLdrupal101 = " SELECT count " +
						" FROM " + aszDrupalDB + "flag_counts fl " +
						" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + aHeadObj.getPortal() + " AND content_type LIKE 'node' "; 
					iRetCode=pConn.getDBStmt();
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode = pConn.RunQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
					}
					iRetCode=-1;
					// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
					if(pConn.getNextResult()){
						iInitCount=pConn.getDBInt("count");
					}
	
	    			iChangeNumber++;// successfully added and not a duplicate
	        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
	            	iCurrentCount = iInitCount + iChangeNumber;
	            	if(iCurrentCount<0) iCurrentCount=0;
	            	if(iCurrentCount==0){
	            	}else{
						// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
						aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
			    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + ","+iCurrentCount+") "+
			    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
			    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
			    		if(0 != iRetCode){
			    			pConn.copyErrObj(getErrObj());
			    			ErrorsToLog();
			    		}
	            	}
				}
			}
			// if successful - insert into the taxonomy as well
			aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "term_node (tid, vid, nid) " +
    				" VALUES (" + aHeadObj.getPortal() + ","+iUPvid+ ","+iUPnid+") "; 
    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
    		if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
    			ErrorsToLog();
    		}
		}
		return 0;
	}
	// end-of method updateIndividualPortalDB()

	
	/**
	* update users personality taxonomy
	*   
	*/
	public int updateDBUSPFacebookConnection(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, currentFacebookUID;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPFacebookUID");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
	    
	    if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkFacebookAccount")){
		    // for first time linking with Facebook Connect, check if the user already has a FB user ID
		    aszSQL101="SELECT * FROM " + aszDrupalDB + "content_type_uprofile " +
		    	"WHERE nid=" + iUPnid + "";
		    iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExePrepQry();
			
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			// Get facebook uid From ResultSet
			// If the recorded Facebook ID is not empty or zero, and is not equal to the new Facebook ID
			// we want to set, flag this as an error
			if(pConn.getNextResult()){
				
				currentFacebookUID = pConn.getDBString("field_facebook_uid_value");
				if(currentFacebookUID != null){
					if(currentFacebookUID.length() > 1){
						if( ! currentFacebookUID.equalsIgnoreCase(aHeadObj.getFacebookUID())){
							return 555;
						} else { //if the facebook user ID is equal, we don't need to bother updating it
							return 0;
						}
					}
				}
			}
	    }

		//update uprofile for user with the facebook user id
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " + 
			"SET " + " field_facebook_uid_value = '" + aHeadObj.getFacebookUID() + "', " +
				" field_facebook_access_token_value = '" + aHeadObj.getFacebookAccessToken() + "' " +
				" WHERE nid=" + iUPnid + "";  
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

		return 0;
	}
	// end-of method updateDBUSPFacebookUID()
	
	/**
	* update users personality taxonomy
	*   
	*/
	public int updateDBUSPPersonality(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPPersonality");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

	    //	delete personality taxonomy for the user
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + aHeadObj.getUSPInternalTacLiteComment() + // what is aHeadObj.getUSPInternalTacLiteComment() *******
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid=" + vidPersonality + ")";
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

		//update uprofile for user with the personality facets and facebook user id
		//update uprofile for user with the personality facets and facebook user id
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " + 
			"SET " +
			"	field_personality_published_value= 0 " ;
			if(aHeadObj.getUSPPersonalityEI().length() > 0){
				aszSQL101+= ", field_personality_facet_ei_value = " + aHeadObj.getUSPPersonalityEI();
			}
			if(aHeadObj.getUSPPersonalitySN().length() > 0){
				aszSQL101+= ", field_personality_facet_sn_value = " + aHeadObj.getUSPPersonalitySN();
			}
			if(aHeadObj.getUSPPersonalityFT().length() > 0){
				aszSQL101+= ", field_personality_facet_tf_value = " + aHeadObj.getUSPPersonalityFT();
			}
			if(aHeadObj.getUSPPersonalityJP().length() > 0){
				aszSQL101+= ", field_personality_facet_jp_value = " + aHeadObj.getUSPPersonalityJP();
			}
			aszSQL101+=", field_facebook_uid_value = '" + aHeadObj.getFacebookUID() + "' " +
				" WHERE nid=" + iUPnid + "";  
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
		iRetCode=-1;
		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		// add Personality type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid
			aszSQL101 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE " + 
				"tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
			iRetCode=pConn.PrepQuery(aszSQL101);
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
				aHeadObj.setUSPPersonalityTID(itid);
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			}
		}
		return 0;
	}
	// end-of method updateDBUSPMinistryAreas()
	public int updateDBUSPMinistryAreas(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, Qry1, aszSQLdrupal101, tempTaxonomy;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPPersonality");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();


		//update uprofile for user with the personality facets and facebook user id
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " + 
		"SET " +
		"	field_personality_published_value= 0 " ;
		if(aHeadObj.getUSPPersonalityEI().length() > 0){
			aszSQL101+= ", field_personality_facet_ei_value = " + aHeadObj.getUSPPersonalityEI();
		}
		if(aHeadObj.getUSPPersonalitySN().length() > 0){
			aszSQL101+= ", field_personality_facet_sn_value = " + aHeadObj.getUSPPersonalitySN();
		}
		if(aHeadObj.getUSPPersonalityFT().length() > 0){
			aszSQL101+= ", field_personality_facet_tf_value = " + aHeadObj.getUSPPersonalityFT();
		}
		if(aHeadObj.getUSPPersonalityJP().length() > 0){
			aszSQL101+= ", field_personality_facet_jp_value = " + aHeadObj.getUSPPersonalityJP();
		}
		aszSQL101+=", field_facebook_uid_value = '" + aHeadObj.getFacebookUID() + "' " +
			" WHERE nid=" + iUPnid + "";  
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
		else {
			aHeadObj.setPersonalityPublished(0);
		}
		iRetCode=-1;
		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidVolInterestArea + 
				"," + vidVolSkill + "," + vidLookingFor  + 
				"," + vidPersonality + "," + vidCauseTopic + "," + vidOtherSkills +
				"," + vidOtherPassions + "," + vidOtherLearningInterests;
		aszSQL101 = aszSQL101 + ") ) ";
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
		
		/** add Personality Type */
		// add Personality Type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
//				java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		
//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			while(tempLookingFor.length() > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				int tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
				tempLookingFor = tempLookingFor.substring((commaPlace + 1), tempLookingFor.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidLookingFor + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			while(tempService.length() > 1){
				int commaPlace = tempService.indexOf(",");
				int tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
				tempService = tempService.substring((commaPlace + 1), tempService.length());
			
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			while(tempSkills.length() > 1){
				int commaPlace = tempSkills.indexOf(",");
				int tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			while(tempSpirit.length() > 1){
				int commaPlace = tempSpirit.indexOf(",");
				int tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
				tempSpirit = tempSpirit.substring((commaPlace + 1), tempSpirit.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				int tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
				tempMin = tempMin.substring((commaPlace + 1), tempMin.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			while(tempGlobal.length() > 1){
				int commaPlace = tempGlobal.indexOf(",");
				int tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
				tempGlobal = tempGlobal.substring((commaPlace + 1), tempGlobal.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			while(tempOrg.length() > 1){
				int commaPlace = tempOrg.indexOf(",");
				int tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
				tempOrg = tempOrg.substring((commaPlace + 1), tempOrg.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			while(tempRec.length() > 1){
				int commaPlace = tempRec.indexOf(",");
				int tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
				tempRec = tempRec.substring((commaPlace + 1), tempRec.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		
//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		return 0;
	}
	// end-of method updateDBUSPMinistryAreas()
	
	public int updateDBUSPInterestsSkills(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, Qry1, aszSQLdrupal101, tempTaxonomy;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPInterestsSkills");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the interests, skills, and looking for taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidVolInterestArea + 
				"," + vidVolSkill + "," + vidLookingFor + "," + vidOtherSkills +
				"," + vidOtherPassions;
		aszSQL101 = aszSQL101 + ") ) ";
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
		
		
//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			while(tempLookingFor.length() > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				int tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
				tempLookingFor = tempLookingFor.substring((commaPlace + 1), tempLookingFor.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidLookingFor + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			while(tempService.length() > 1){
				int commaPlace = tempService.indexOf(",");
				int tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
				tempService = tempService.substring((commaPlace + 1), tempService.length());
			
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			while(tempSkills.length() > 1){
				int commaPlace = tempSkills.indexOf(",");
				int tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		
//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		

		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		return 0;
	}
	// end-of method updateDBUSPInterestsSkills()
	
	public int updateDBUSPPersonalitySelectFields(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String personalityPage){
		int iRetCode=0;
		String aszSQL101, Qry1, aszSQLdrupal101, tempTaxonomy;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPPersonality");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (";
				
				if(personalityPage.equalsIgnoreCase("facebooktrainingcontent") || personalityPage.equalsIgnoreCase("personalityministryareas2")){
            		aszSQL101 += vidCauseTopic + "," + vidOtherLearningInterests;
                }
                else { //if(personalityPage.equalsIgnoreCase("personalityministryareas") || personalityPage.equalsIgnoreCase("personalityministryopps")) {
                	aszSQL101 += vidVolInterestArea + 
    				"," + vidVolSkill + "," + vidLookingFor + "," + vidOtherSkills +
    				"," + vidOtherPassions;
                	
                	if( (aHeadObj.getUSPPersonalityTID() > 0) ){
                		aszSQL101 += "," + vidPersonality; 
                	}

                }

		aszSQL101 = aszSQL101 + ") ) ";
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
		
	if(personalityPage.equalsIgnoreCase("facebooktrainingcontent") || personalityPage.equalsIgnoreCase("personalityministryareas2")){
//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			while(tempSpirit.length() > 1){
				int commaPlace = tempSpirit.indexOf(",");
				int tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
				tempSpirit = tempSpirit.substring((commaPlace + 1), tempSpirit.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				int tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
				tempMin = tempMin.substring((commaPlace + 1), tempMin.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			while(tempGlobal.length() > 1){
				int commaPlace = tempGlobal.indexOf(",");
				int tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
				tempGlobal = tempGlobal.substring((commaPlace + 1), tempGlobal.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			while(tempOrg.length() > 1){
				int commaPlace = tempOrg.indexOf(",");
				int tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
				tempOrg = tempOrg.substring((commaPlace + 1), tempOrg.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			while(tempRec.length() > 1){
				int commaPlace = tempRec.indexOf(",");
				int tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
				tempRec = tempRec.substring((commaPlace + 1), tempRec.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		

		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
	}
	else { //if(personalityPage.equalsIgnoreCase("personalityministryareas") || personalityPage.equalsIgnoreCase("personalityministryopps")) {
		//		 add Personality type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid
			aszSQL101 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE " + 
				"tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
			iRetCode=pConn.PrepQuery(aszSQL101);
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
				aHeadObj.setUSPPersonalityTID(itid);
				// add to um_term_node
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
					iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			}
		}
		
		//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			while(tempLookingFor.length() > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				int tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
				tempLookingFor = tempLookingFor.substring((commaPlace + 1), tempLookingFor.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidLookingFor + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			while(tempService.length() > 1){
				int commaPlace = tempService.indexOf(",");
				int tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
				tempService = tempService.substring((commaPlace + 1), tempService.length());
			
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			while(tempSkills.length() > 1){
				int commaPlace = tempSkills.indexOf(",");
				int tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		
//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
	}
	
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
	
	
	//update Facebook User ID in um_content_type_uprofile in case it hasn't been set already

	if((aHeadObj.getFacebookUID().length() > 0) && (aHeadObj.getFacebookUID() != null) ){
	
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " + 
			"SET field_facebook_uid_value = " + aHeadObj.getFacebookUID();
		aszSQL101 += " WHERE nid =" + iUPnid + ""; 
		
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
	}
	
		return 0;
	}
	// end-of method updateDBUSPMinistryAreas()
	
	public int updateDBUSPLearningInterests(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, Qry1, aszSQLdrupal101, tempTaxonomy;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPPersonality");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

		
		// Proceed to enter all NEW taxonomy input; All INSERTS, So that we handle deleting old taxonomy rather than just adding on new
		
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************
		//****************   BEGIN TAXONOMY SECTION  **********************

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + //aHeadObj.getUSPInternalTacLiteComment() + 
				//!(tid=8059) AND !(tid=1222) " + // don't delete Org Affiliations that have to do with Taxonomy Access Control
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidCauseTopic + "," + vidOtherLearningInterests;
		aszSQL101 = aszSQL101 + ") ) ";
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
		
		
//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			while(tempSpirit.length() > 1){
				int commaPlace = tempSpirit.indexOf(",");
				int tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
				tempSpirit = tempSpirit.substring((commaPlace + 1), tempSpirit.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				int tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
				tempMin = tempMin.substring((commaPlace + 1), tempMin.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			while(tempGlobal.length() > 1){
				int commaPlace = tempGlobal.indexOf(",");
				int tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
				tempGlobal = tempGlobal.substring((commaPlace + 1), tempGlobal.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			while(tempOrg.length() > 1){
				int commaPlace = tempOrg.indexOf(",");
				int tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
				tempOrg = tempOrg.substring((commaPlace + 1), tempOrg.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			while(tempRec.length() > 1){
				int commaPlace = tempRec.indexOf(",");
				int tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
				tempRec = tempRec.substring((commaPlace + 1), tempRec.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		

		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
						//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
						if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					}
				}
				itid=-1;
				}
			}
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		//****************   END TAXONOMY SECTION  **********************
		return 0;
	}
	// end-of method updateDBUSPPersonalitySelectFields()
	
	/**
	* update users facebook personality published status (toggle to the opposite of the current value)
	*   0 = Not Published
	*   1 = Published
	*/
	public int updateDBUSPPersonalityPublished(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101;
		int iUPnid=-1, iUPvid=-1, published=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPPersonalityPublished");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
	    
	    
			// grab the current value of published
			aszSQL101 = "SELECT field_personality_published_value FROM " + aszDrupalDB + "content_type_uprofile " + 
			" WHERE nid =" + iUPnid + "";  
			iRetCode=pConn.PrepQuery(aszSQL101);
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
				published = pConn.getDBInt("field_personality_published_value");
			}
	    

		//update uprofile for user with the new value for publised (toggle to true if
			// it's false; false if it's true - where 0 or null represent false and 
			// 1 represents true)
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile " + 
		"SET " +
		"field_personality_published_value = ";
		
		if((published != 1))
			aszSQL101 += "1";
		else
			aszSQL101 += "0";
		
		aszSQL101 += " WHERE nid =" + iUPnid + "";  
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
		
		if((published != 1))
			aHeadObj.setPersonalityPublished(1);
		else
			aHeadObj.setPersonalityPublished(0);
		
		iRetCode=-1;
		
		
		
		return 0;
	}
	// end-of method updateDBUSPPersonality()
	
	/**
	* updateResume resume  - store in uprofile
	*/
	public int updateResume(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0, index=0, itid=0;
		String aszSQL101="" ;
		MethodInit("updateResume");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		int iUPnid=aHeadObj.getUserProfileNID() ;
		int iUPvid=aHeadObj.getUserProfileVID() ;
		
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile SET field_resume_value=?, field_resume_filepath_value=? " +
				" WHERE nid = " + iUPnid ;
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPVolResume() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPResumeFilePath() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		
		// update Category Service Area tagging based on the Resume

		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				" WHERE nid = " + iUPnid +  
				" AND tid IN (SELECT tid FROM " + aszDrupalDB + 
				"term_data WHERE vid IN (" + vidService + ") ) ";
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
		// add Service Areas Array
		if ( aHeadObj.getUSPAutoTaggingArray()!=null ){
			if ( aHeadObj.getUSPAutoTaggingArray().length > 0 ){
				index=0;
				int[][] tempObjService = aHeadObj.getUSPAutoTaggingArray();
				for (int[] a_RelatedTerms : tempObjService ){
					
					if(a_RelatedTerms==null) break;
					
					int iTID = a_RelatedTerms[0];
					int iRelatedTID = a_RelatedTerms[1];
					
					index++;
					// grab the tid
					String Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  iTID + 
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
						String aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid, auto_tag, timestamp, auto_tag_tid_source) " +
								"VALUES("+ iUPnid + "," + iTID + "," + iUPvid + ", 1, UNIX_TIMESTAMP({fn now()}), " + iRelatedTID + " )";
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
		return 0;
	}
	// end-of method updateResume()

	
	/**
	* link the user to its resume file location - store in uprofile
	*/
	public int linkResume(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101="" ;
		MethodInit("linkResume");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		aszSQL101="UPDATE " + aszDrupalDB + "content_type_uprofile SET field_resume_filepath_value=? " +
				" WHERE nid = " + aHeadObj.getUserProfileNID() ;
		iRetCode=pConn.PrepQuery(aszSQL101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode = pConn.setPrepQryString( aHeadObj.getUSPResumeFilePath() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		return 0;
	}
	// end-of method linkResume()

	

	/**
	* LocationTaxonomyDB
	*/
	public int LocationTaxonomyDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		String aszSQL101 = "",Qry1 = "";
		String aszCountry="", aszProvince="", aszPostal="";
		String aszName="", aszRegionName="", aszSubRegionName="", aszCountryName="", aszProvinceName="", aszMetroName="";
		int iRegion=0, iSubRegion=0, iCountry=0, iProvince=0, iMetro=0;

		MethodInit("LocationTaxonomyDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}

		iuid = aHeadObj.getUserUID();;
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();

	    aszCountry=aHeadObj.getUSPAddrCountryName();
	    aszProvince=aHeadObj.getUSPAddrStateprov();
	    aszPostal=aHeadObj.getUSPAddrPostalcode();

	    iRetCode=0;

//		vidState=52, vidCity=15, vidCountry=261, 


		// delete ALL occurrences of the Volunteer Engine taxonomy for the given node
		aszSQL101 = "DELETE FROM " + aszDrupalDB + "term_node " +
				"WHERE nid = " + iUPnid + "   " + 
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
					aHeadObj.setCityTID(itid);
					aHeadObj.setCityName(aszName);
					// add to um_term_node
					aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
					aHeadObj.setStateTID(itid);
					aHeadObj.setStateName(aszName);
					// add to um_term_node
					aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
					aHeadObj.setCountryTID(itid);
					aHeadObj.setCountryName(aszName);
					// add to um_term_node
					aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
					aHeadObj.setRegionTID(itid);
					aHeadObj.setRegionName(aszName);
					// add to um_term_node
					aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		
		return 0;
	}
	// end-of method LocationTaxonomyDB()

	/*
	 * insert the Connection Source (for a user coming through Facebook Connect)
	 */
	public int linkFacebookConnectUserDB(ABREDBConnection pConn, PersonInfoDTO aHeadObj){
		int iRetCode=0;
		String aszSQL101, Qry1;
		int iUPnid=-1, iUPvid=-1, published=-1, ilid=-1, iuid=-1, itid=-1;

		MethodInit("linkFacebookConnectUserDB");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
	    
	    
	    /** add Connection Source for facebook connect */
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" + facebookConnectTID  +
				" AND vid = " + vidConnectionSource + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQL101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid, timestamp) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + ", 0 )";
				iRetCode=pConn.PrepQuery(aszSQL101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=pConn.ExecutePrepQry();
//				java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
				if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
				}else if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			} else {
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		
		return 0;
	}
	// end-of method linkFacebookAccountUserDB()
	
	/**
	* Does user exist in table userprofileinfo
	* return 0 if yes found user
	*/
	public int isFacebookUserInSystem( ABREDBConnection pConn, String facebookUID ){
		int iRetCode=0;
		String aszSQL=null , aszTemp=null , aszPersonCodekey=null ;
        MethodInit("isFacebookUserInSystem");
		if(null == pConn) return -1;
		if(null == facebookUID) return -1;
        aszTemp = facebookUID.trim();
        if(aszTemp.length() < 2) return -1;
System.out.println("inside isfacebookuiserinsystem");        
        aszSQL = " SELECT nid, field_facebook_uid_value FROM " + aszDrupalDB + "content_type_uprofile WHERE field_facebook_uid_value='" + aszTemp + "'";
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
System.out.println("the facebook user ID is in the system for node " + pConn.getDBInt("nid") );        
			// there was a result, so return 0
            return 0;
		}
		return -1;
    }
    // end-of method IsUserIDInSystem()
	
	
	


    /**
	* insert a row into table userprofileinfo
	*   date variables can use method setPrepQryDateNull() or setPrepQryDate()
	*/
	public int insertDBUSP(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String aszRailsDB ){
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
System.out.println("/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/");
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
    	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
		int iRetCode=0;
		int lUniqueID=-1, lNextUniqueID=-1 ;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;
		int iTermsConditionsVersion = 2;
		int index=0;
		String tempTaxonomy;
		String Qry1=null;
		String aszSQLdrupal101=null;

		if(aHeadObj.getUserRoleID()==0){
			aHeadObj.setUserRoleID(iRid);
		}

		MethodInit("insertDBUSP");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		String temporary = aHeadObj.getUSPInternalUserTypeComment();
		if (temporary.length()==0){
			
		}
		int temp = aHeadObj.getUserProfileVID();
		if (temp < 1){	
		}
		temp = aHeadObj.getUserProfileNID();
		if (temp < 1){
		}
		temp = aHeadObj.getUserProfileLID();
		if (temp < 1){
		}
		temp = aHeadObj.getUserUID();
		if (temp < 1){
		}
		
  		
		/*
		 * ADD USER TO DRUPAL
		 *  the user does not yet exist in the drupal db, either, so add the user to drupal as well.
		 */
		// um_users table
		if(aHeadObj.getUSPInternalUserTypeComment().length() < 1){ // the user does not exist yet in any capacity - is not logged in  2009-01-10//else if(iRetCode==-1){
			// "auto-"increment the uid in " + aszDrupalDB + "sequences table - grab last Id, then increment it, then make that the new uid...
			// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
			if(null == pConn){
				setErr("null database connection");
				return -1;
			}
/*
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				return -1;
			}
*/    
		    // added timezone to insert 2008-01-04 to try and stop um.org bug with empty nodes being created
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "users(name, pass, status, mail,init, theme, created, timezone) " +
					" VALUES(?," + 
					" MD5(?),1,?,?,'',UNIX_TIMESTAMP({fn now()}), " + aHeadObj.getUSPTimezone() + " ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPassword() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPEmail1Addr() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;

			//	*****  Grab the last auto-incremented ID and save it as the uid for this user/node *****************
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
				iuid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserUID(iuid);
			} else {
				itid=-1;
			}
			iRetCode=-1;
			
			
//**************************
//**************************
//			**********aHeadObj.setUserUID(lNextUniqueID); // 2008-09-12 - ali - for new accounts, we want the uid to be written to drupalized orgs/opps for ownership
//			********** last insert id ***********
//**************************
//**************************
			if (aHeadObj.getUSPAgree1Fg().length() > 0){
			    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "legal_accepted(uid, version, accepted,revision,language) " +//legal_accepted(uid, tc_id, accepted) " +
					" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ,1,'en') ";
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
				iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode=-1;
			}
			
			/** if the user is coming through WorldVision (worldvisiontraining, etc), then they need to accept WorldVision terms & conditions as well */
	    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase("worldvision.christianvolunteering.org")){
	        	if(aHeadObj.getUSPAgreeWVFg().length() > 0){
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
					aszSQLdrupal101="INSERT INTO " + aszWorldVisionDB + "legal_accepted(uid, version, accepted) " +//"legal_accepted(uid, tc_id, accepted) " +
							" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ) ";
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
	        	}
	    	}
		}else{ // a drupal user exists, but we don't know yet whether uprofile, location, etc do
			// 2009-01-10 *****************************************************************
			// "UPDATE um_users WHERE uid=" + aHeadObj.getUserUID();
			
			if (aHeadObj.getUSPAgree1Fg().length() > 0){ // add an entry to the legal_accepted table if not already in there
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
				// prepare query
				Qry1 = "SELECT * FROM " + aszDrupalDB + "legal_accepted WHERE uid=" + aHeadObj.getUserUID() + " and version="+ iTermsConditionsVersion + "";//" and tc_id="+ iTermsConditionsVersion + "";
				iRetCode = pConn.RunQry(Qry1);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
			        ErrorsToLog();
					return -1;
				}
				// Get Next Item From ResultSet
				if(pConn.getNextResult()){ // there is already an entry for this user in accepting the legal
					// the user already accepted legal terms and conditions - nothing needs to be done
				} else {
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol), if not already in drupal system
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "legal_accepted(uid, version, accepted,revision,language) " +//legal_accepted(uid, tc_id, accepted) " +
						" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ,1,'en') ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
					iRetCode = pConn.ExecutePrepQry();		//INSERT INTO " + aszDrupalDB + "users(name, pass, mail,init, theme) VALUES(?, MD5(?),?,?,'chrisvol') 
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=-1;
				}
			}
			
			/** if the user is coming through WorldVision (worldvisiontraining, etc), then they need to accept WorldVision terms & conditions as well */
	    	if(aHeadObj.getUSPSubdom().equalsIgnoreCase("volengworldvision") || aHeadObj.getUSPSubdom().equalsIgnoreCase("worldvision.christianvolunteering.org")){
	        	if(aHeadObj.getUSPAgreeWVFg().length() > 0){
				    // add legal accepted terms & conditions for new user (assuming they did in chrisvol)
					aszSQLdrupal101="INSERT INTO " + aszWorldVisionDB + "legal_accepted(uid, version, accepted) " +//"legal_accepted(uid, tc_id, accepted) " +
							" VALUES("+ aHeadObj.getUserUID() +"," + iTermsConditionsVersion + ", UNIX_TIMESTAMP({fn now()}) ) ";
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
	        	}
	    	}

		}
		
		if(aHeadObj.getUserRoleID()>-1){
			/*
			 * apply the ChristianVolunteering.org user role to the new drupal user
			 */
			aszSQLdrupal101="INSERT IGNORE  INTO " + aszDrupalDB + "users_roles(uid, rid) " +
					"VALUES("+ aHeadObj.getUserUID() +", "+ aHeadObj.getUserRoleID() +" ) "; //iRid +" ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry ..."
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; the user role already exists as such in users_roles
				
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
		}

		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		/************************ BEGIN USERPROFILE *****************************************/
		
		// um_content_type_uprofile table
		if(
				aHeadObj.getUSPInternalUserTypeComment().length() < 1 					 ||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser) ||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgContact)||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOppContact)||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszOrgAdmin)
		){
			// 2009-01-10
			// INSERT INTO um_content_type_uprofile, um_node, ......;
			//ADD USERPROFILE TO DRUPAL - the userprofile does not yet exist at all in the drupal db
			
			// "auto-"increment the nid in " + aszDrupalDB + "sequences table - then grab last Id...
			// drupal increments the value and then takes it... (voleng takes the NextID and then increments)
			lUniqueID=-1 ;
			lNextUniqueID=-1 ;
			Qry1=null;
			if(null == pConn){
				setErr("null database connection");
				return -1;
			}
			iRetCode=pConn.getDBStmt();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				return -1;
			}
			// add to um_node_revisions
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_revisions(uid, title, body, teaser, timestamp, format) " +
					" VALUES(?,?,'','',UNIX_TIMESTAMP({fn now()}),0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
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
			iRetCode = pConn.ExePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
		        ErrorsToLog();
				return -1;
			}
			// Get tid From ResultSet
			if(pConn.getNextResult()){
				iUPvid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserProfileVID(iUPvid);
			} else {
				itid=-1;
			}
			iRetCode=-1;
			

//*****************************************************			
//			aHeadObj.setUserProfileVID(lNextUniqueID);
//			iUPvid=
//*****************************************************	
			
			// add to um_node
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node(vid, type, status, comment, moderate," +
					" title, uid, created, changed) " +
					" VALUES("+ iUPvid +",'uprofile'," ;
			if(aHeadObj.getUSPVolResume().length() > 2){
				aszSQLdrupal101 = aszSQLdrupal101 +	"0,0,1," ; // unpublished by default and put into moderation if they posted a resume
			}else if(aHeadObj.getUSPVolunteerTID()==iVolDirectorytid){ 
				aszSQLdrupal101 = aszSQLdrupal101 +	"1,0,0," ; // published b/c user said to list them in the directory
			}else{ // this should include user profiles for organizations
				aszSQLdrupal101 = aszSQLdrupal101 +	"0,0,0," ; // the user has stated that they don't want to be published
			}
			
			aszSQLdrupal101 = aszSQLdrupal101 +	" ?,?,UNIX_TIMESTAMP({fn now()}),UNIX_TIMESTAMP({fn now()}) ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPUsername() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
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
				iUPnid = pConn.getDBInt("LAST_INSERT_ID()");
				aHeadObj.setUserProfileNID(iUPnid);
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
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileNID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				 ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserProfileVID() );
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
			
			
			
//*****************************************************			
//			aHeadObj.setUserProfileNID(lNextUniqueID);
//			iUPnid=
//*****************************************************	

			
			
//			****************************************************************
//****** update um_node_revisions set nid=iUPnid where vid=iUPvid     ***********
//			****************************************************************
//			****************************************************************
			
			// add to um_node_access
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_access(nid, gid, realm, grant_view, grant_update, grant_delete) " +
					" VALUES("+ iUPnid +",0,'all',1,0,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_node_comment_statistics
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "node_comment_statistics(nid, last_comment_timestamp, last_comment_uid, comment_count) " +
					" VALUES("+ iUPnid +",UNIX_TIMESTAMP({fn now()}),?,0 ) ";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUserUID() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
			iRetCode = pConn.ExecutePrepQry(); 
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;
			// add to um_content_type_uprofile
			aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "content_type_uprofile " +
					"(nid,vid,field_about_me_format,field_resume_format,field_resume_value," +//5
					"field_first_name_value,field_last_name_value,field_is_christian_value," +
					"field_voluser_subdomain_value,field_voluser_agree2_flag_value,field_subscribe_opps_posti_value";//3
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				//aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_user_id_value";
				aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value";//1
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ") " +	"VALUES (" + iUPnid + "," + iUPvid + ",1,1,?," +//5
					"?,?,?,?,?," +//5
					"?,?,?";//3
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				aszSQLdrupal101 = aszSQLdrupal101 +	",?";//1
					//,?) "; 
			}
			aszSQLdrupal101 = aszSQLdrupal101 + ")";
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getUSPVolResume() ));
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPChristianP() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAttendChurchP() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
/*
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAuthTokens() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
*/			
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() ); // make sure this has a value
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUSPSubscribe() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			if(
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || 
					aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook") //||
					//aHeadObj.getFacebookUID()>2
			){
				//iRetCode=pConn.setPrepQryLong( aHeadObj.getFacebookUID() );
				iRetCode=pConn.setPrepQryString( aHeadObj.getFacebookUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			iRetCode = pConn.ExecutePrepQry();
			if(iRetCode == 1062 ){ // then this is a duplicate; 
				iRetCode=0;
			}else if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=-1;//0????????????????????

		}else{ // uprofile already exists; update it
		
		    iUPnid = aHeadObj.getUserProfileNID();
		    iUPvid = aHeadObj.getUserProfileVID();
		    // update the uprofile cck
			    
			aszSQLdrupal101="UPDATE " + aszDrupalDB + "content_type_uprofile " +
					"SET " +
					"field_resume_value=?,field_first_name_value=?,field_last_name_value=?,field_is_christian_value=?," +
					"field_site_use_type_value=?,field_birth_year_value=?,field_church_attendance_value=?," +
					//"field_voluser_user_id_value=?," +
					//"field_voluser_auth_tokens_value=?," +
					"field_voluser_subdomain_value=?,field_voluser_agree2_flag_value=? ";
//					",field_do_you_want_to_volunteer_value=? " + 
			if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
				//aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_user_id_value=? ";
				aszSQLdrupal101 = aszSQLdrupal101 + ",field_facebook_uid_value=? ";
			}
					
			aszSQLdrupal101= aszSQLdrupal101 + "WHERE nid=" + iUPnid + ""; 
			iRetCode=pConn.PrepQuery(aszSQLdrupal101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( replaceCarriageReturn(aHeadObj.getUSPVolResume() ));
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameFirst() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPNameLast() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPChristianP() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSiteUseType() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryInt( aHeadObj.getBirthYear() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAttendChurchP() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			/*
			iRetCode = pConn.setPrepQryInt( aHeadObj.getUSPPersonNumber() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAuthTokens() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			*/
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPSubdom() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAgree2Fg() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("facebook") || aHeadObj.getUSPInternalComment().equalsIgnoreCase("drupalfacebook")){
				//iRetCode = pConn.setPrepQryLong( aHeadObj.getFacebookUID() );
				iRetCode = pConn.setPrepQryString( aHeadObj.getFacebookUID() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
			}
			/*
			iRetCode = pConn.setPrepQryString( aHeadObj.getUSPVolunteer() );
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			*/
			iRetCode = pConn.ExecutePrepQry();
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=0;
		}

		
		// add to ChrisvolOnRails app table
		aszSQLdrupal101="INSERT IGNORE INTO " + aszRailsDB + "profiled(drupal_uprofile_nid, created_at, updated_at)" +
				" VALUES("+ iUPnid +",'{fn now()}','{fn now()}'" ;
		iRetCode=pConn.PrepQuery(aszSQLdrupal101);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		//java.sql.SQLException: Duplicate key or integrity constraint violation,  message from server: "Duplicate entry '0' for key 1"
		iRetCode = pConn.ExecutePrepQry(); 
		if(iRetCode == 1062 ){ // then this is a duplicate; 
			iRetCode=0;
		}else if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -1;
		}
		iRetCode=-1;

		// if the user says they are using the site for ONLY organization purposes
		// && the user has not entered anything in any of the address/location fields,
		// no insert/update of location or location_phone needs to be done; otherwise, 
		// YES, it does
		if ( (aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszOrganizationUser)	||
     			aHeadObj.getUSPSiteUseType().equalsIgnoreCase(aszChurchUser)) &&
				aHeadObj.getUSPAddrLine1().length() < 1 &&
				aHeadObj.getUSPAddrCity().length() < 1 &&
				aHeadObj.getUSPAddrStateprov().length() < 1 && // - dropdowns possibly default to values
				aHeadObj.getUSPAddrPostalcode().length() < 1 
				//&& aHeadObj.getUSPAddrCountryName().length() < 1 - dropdowns possibly default to values
		){
			// DO NOT INSERT A LOCATION FOR AN ORGANIZATIONAL-ONLY RECORD THAT DOES NOT HAVE ANY OF THOSE FIELDS FILLED OUT 
			/**
			 * 
			 * SHOULD STILL INSERT EMPTY STUFF INTO THE TABLES, OR ELSE LOGGING IN (LOAD USER) WILL BE BROKEN/INCOMPLETE -- ???
			 * 
			 * 
			 * 
			 */
		}else{
			// um_location table

			if (
				aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
				aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	
			){
				// 2009-01-10
				//INSERT INTO um_location;
				//	 the userprofile already existed, but the LOCATION did NOT exist yet
				// insert into location
			
				// add to um_location
				lUniqueID=-1 ;
				lNextUniqueID=-1 ;
				Qry1=null;
				if(null == pConn){
					setErr("null database connection");
					return -1;
				}
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					return -1;
				}
				
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location " +
						"(name,street,additional,city,province,postal_code,country,source,is_primary) " +
						"VALUES('',?,'',?,?,?,?,0,0)"; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
					aHeadObj.setUserProfileLID(ilid);
				} else {
					itid=-1;
				}
				iRetCode=0;

//********************************************
//				ilid=lNextUniqueID; // lid for new location instance
//				aHeadObj.setUserProfileLID(lNextUniqueID);
//********************************************

				
				aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_instance " +
						"(lid, nid, vid, genid) " +
						"VALUES(" + ilid + "," + iUPnid + "," + iUPvid + ",'')"; 
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
				iRetCode=0;				
		
				
				
			}else{
				//UPDATE um_location WHERE lid=aHeadObj.getUserProfileLID();
				ilid = aHeadObj.getUserProfileLID();
				// update the uprofile cck location
				aszSQLdrupal101="UPDATE " + aszDrupalDB + "location " +
						"SET " +
						"street=?,city=?,province=?,postal_code=?,country=?,source=0,is_primary=0 " + 
						"WHERE lid=" + ilid + ""; 
				iRetCode=pConn.PrepQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrLine1() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCity() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrStateprov() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrPostalcode() );
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					return -1;
				}
				iRetCode = pConn.setPrepQryString( aHeadObj.getUSPAddrCountryName() );
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
			// end um_location
/*			
			//um_location_phone table
			// if a phone number was entered, add it to the um_location_phone table
			if(aHeadObj.getUSPPhone1().length()>1){
				if (
					aHeadObj.getUSPInternalUserTypeComment().length() < 1				||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszDrupalUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileUser)	||
					aHeadObj.getUSPInternalUserTypeComment().equalsIgnoreCase(aszProfileLocationUser)	
				){
					// 2009-01-10
					//INSERT INTO um_location_phone;
					
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "location_phone " +
							"(lid,phone) " +
							"VALUES(" + ilid + ",?)"; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();
					if(1062 == iRetCode){// already existed; possibly from a previous partial update
						iRetCode = 0;
					}
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode=0;
					
				}else{
					//UPDATE um_location_phone WHERE lid=aHeadObj.getUserProfileLID();
				    ilid = aHeadObj.getUserProfileLID();
				    // update the uprofile cck
					    
					aszSQLdrupal101="UPDATE " + aszDrupalDB + "location_phone " +
							"SET phone=? " + 
							"WHERE lid=" + ilid + ""; 
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.setPrepQryString( aHeadObj.getUSPPhone1() );
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
*/			
		}
		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		/************************** END USERPROFILE *****************************************/
		
		/************************** BEGIN TAXONOMY ***************************************/
		/************************** BEGIN TAXONOMY ***************************************/
		/************************** BEGIN TAXONOMY ***************************************/

		// add Skills
		if ( ! aHeadObj.getUSPVolSkills().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills1TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills1TID(itid);
				aHeadObj.setUSPVolSkills(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Skills 2
		if ( ! aHeadObj.getUSPVolSkills2().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills2TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills2TID(itid);
				aHeadObj.setUSPVolSkills2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add Skills 3
		if ( ! aHeadObj.getUSPVolSkills3().equalsIgnoreCase("") || aHeadObj.getUSPVolSkills3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolSkills3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolSkills3TID() + 
				") AND vid = " + vidVolSkill + " ";
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
				aHeadObj.setUSPVolSkills3TID(itid);
				aHeadObj.setUSPVolSkills3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		
		
		// add InterestArea
		if ( ! aHeadObj.getUSPVolInterestArea1().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea1TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea1TID(itid);
				aHeadObj.setUSPVolInterestArea1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add InterestArea 2
		if ( ! aHeadObj.getUSPVolInterestArea2().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea2TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea2TID(itid);
				aHeadObj.setUSPVolInterestArea2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add InterestArea 3
		if ( ! aHeadObj.getUSPVolInterestArea3().equalsIgnoreCase("") || aHeadObj.getUSPVolInterestArea3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolInterestArea3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolInterestArea3TID() + 
				") AND vid = " + vidVolInterestArea + " ";
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
				aHeadObj.setUSPVolInterestArea3TID(itid);
				aHeadObj.setUSPVolInterestArea3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		
		
		// add LangSpoken
		if ( ! aHeadObj.getUSPVolLang1().equalsIgnoreCase("") || aHeadObj.getUSPVolLang1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang1().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang1TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang1TID(itid);
				aHeadObj.setUSPVolLang1(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add LangSpoken 2
		if ( ! aHeadObj.getUSPVolLang2().equalsIgnoreCase("") || aHeadObj.getUSPVolLang2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang2TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang2TID(itid);
				aHeadObj.setUSPVolLang2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add LangSpoken 3
		if ( ! aHeadObj.getUSPVolLang3().equalsIgnoreCase("") || aHeadObj.getUSPVolLang3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolLang3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolLang3TID() + 
				") AND vid = " + vidVolLang + " ";
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
				aHeadObj.setUSPVolLang3TID(itid);
				aHeadObj.setUSPVolLang3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		
		// add UserOrgAffil
		if ( ! aHeadObj.getUSPOtherAffilP().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil1TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil1TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil1TID(itid);
				aHeadObj.setUSPOtherAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add User Org Affiliation 2
		if ( ! aHeadObj.getUSPOtherAffil2().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil2TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil2().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil2TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil2TID(itid);
				aHeadObj.setUSPOtherAffil2(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
		// add User Org Affiliation 3
		if ( ! aHeadObj.getUSPOtherAffil3().equalsIgnoreCase("") || aHeadObj.getUSPOtherAffil3TID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPOtherAffil3().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPOtherAffil3TID() + 
				") AND vid = " + vidVolOrgAffil + " ";
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
				aHeadObj.setUSPOtherAffil3TID(itid);
				aHeadObj.setUSPOtherAffil3(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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

		// add Volunteer Denominational Affiliation
		if ( ! aHeadObj.getUSPDenomAffilP().equalsIgnoreCase("") || aHeadObj.getUSPDenomAffilTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPDenomAffilP().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPDenomAffilTID() + 
				") AND vid = " + vidVolDenom + " ";
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
				aHeadObj.setUSPDenomAffilTID(itid);
				aHeadObj.setUSPDenomAffilP(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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

		// add whether to make this Volunteer Profile public in Volunteer Listings
		if ( ! aHeadObj.getUSPVolunteer().equalsIgnoreCase("") || aHeadObj.getUSPVolunteerTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolunteer().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolunteerTID() + 
				") AND vid = " + vidVolunteer + " ";
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
				aHeadObj.setUSPVolunteerTID(itid);
				aHeadObj.setUSPVolunteer(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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

		// add Volunteer Availability
		if ( ! aHeadObj.getUSPVolAvail().equalsIgnoreCase("") || aHeadObj.getUSPVolAvailTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolAvail().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolAvailTID() + 
				") AND vid = " + vidVolAvail + " ";
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
				aHeadObj.setUSPVolAvailTID(itid);
				aHeadObj.setUSPVolAvail(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
/*
		// add Volunteer Board
		if ( ! aHeadObj.getUSPVolBoard().equalsIgnoreCase("") || aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolBoard().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolBoardTID() + 
				") AND vid = " + vidVolBoard + " ";
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
				aHeadObj.setUSPVolBoardTID(itid);
				aHeadObj.setUSPVolBoard(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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

		// add Virtual Volunteer
		if ( ! aHeadObj.getUSPVolVirt().equalsIgnoreCase("") || aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE ( name='" + 
				replacesinglequote(aHeadObj.getUSPVolVirt().toUpperCase()) + 
				"' OR tid=" +  aHeadObj.getUSPVolVirtTID() + 
				") AND vid = " + vidVolVirt + " ";
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
				aHeadObj.setUSPVolVirtTID(itid);
				aHeadObj.setUSPVolVirt(pConn.getDBString("name"));
				// add to um_term_node
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
*/
		/** add Looking for... options */
		// add Looking For - Local Volunteering
		if ( aHeadObj.getUSPLocalVolTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalVolTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Group/Family Volunteering
		if ( aHeadObj.getUSPGroupFamilyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPGroupFamilyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Volunteer on a Nonprofit Board
		if ( aHeadObj.getUSPVolBoardTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolBoardTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Virtual Volunteering
		if ( aHeadObj.getUSPVolVirtTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPVolVirtTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Internship Opportunities
		if ( aHeadObj.getUSPIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Summer Internship Opportunities
		if ( aHeadObj.getUSPSumIntrnTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSumIntrnTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - WorkStudy Opportunities
		if ( aHeadObj.getUSPWorkStudyTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPWorkStudyTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Jobs in Urban Ministry
		if ( aHeadObj.getUSPJobsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPJobsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Opportunities to Speak at a Conference
		if ( aHeadObj.getUSPConferenceTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConferenceTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Consulting Opportunities
		if ( aHeadObj.getUSPConsultingTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPConsultingTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Social Justice & Christian Groups
		if ( aHeadObj.getUSPSocJustGrpsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPSocJustGrpsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		// add Looking For - Local Organizationss
		if ( aHeadObj.getUSPLocalOrgsTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPLocalOrgsTID() + 
				" AND vid = " + vidLookingFor + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		/** END add Looking for... options */

		/** add Personality Type */
		// add Personality Type
		if ( aHeadObj.getUSPPersonalityTID() > 0 ){
			// grab the tid - confirms that it comes from the correct vocabulary
			Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  aHeadObj.getUSPPersonalityTID() + 
				" AND vid = " + vidPersonality + " ";
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
			// add to term_node only if that tid exists in that vocabulary type
			if(pConn.getNextResult()){
				itid = pConn.getDBInt("tid");
				aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid +"," + itid +  "," + iUPvid + " )";
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
				itid=-1;// The option the user chose for the SELECT returned no results
			}
			itid=-1;
		}
		
//		 add LookingFor Comma Delimited String
		if ( aHeadObj.getUSPLookingFor().length() > 0 ){
			String tempLookingFor = aHeadObj.getUSPLookingFor();
			while(tempLookingFor.length() > 1){
				int commaPlace = tempLookingFor.indexOf(",");
				int tempTID = Integer.parseInt(tempLookingFor.substring(0, commaPlace));
				tempLookingFor = tempLookingFor.substring((commaPlace + 1), tempLookingFor.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidLookingFor + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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

//		 add Service Areas Comma Delimited String
		if ( aHeadObj.getUSPServiceAreas().length() > 0 ){
			String tempService = aHeadObj.getUSPServiceAreas();
			while(tempService.length() > 1){
				int commaPlace = tempService.indexOf(",");
				int tempTID = Integer.parseInt(tempService.substring(0, commaPlace));
				tempService = tempService.substring((commaPlace + 1), tempService.length());
			
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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

//		 add Skill Comma Delimited String
		if ( aHeadObj.getUSPSkillTypes().length() > 0 ){
			String tempSkills = aHeadObj.getUSPSkillTypes();
			while(tempSkills.length() > 1){
				int commaPlace = tempSkills.indexOf(",");
				int tempTID = Integer.parseInt(tempSkills.substring(0, commaPlace));
				tempSkills = tempSkills.substring((commaPlace + 1), tempSkills.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidVolSkill + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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

//		 add Spiritual Development Comma Delimited String
		if ( aHeadObj.getUSPSpiritualDev().length() > 0 ){
			String tempSpirit = aHeadObj.getUSPSpiritualDev();
			while(tempSpirit.length() > 1){
				int commaPlace = tempSpirit.indexOf(",");
				int tempTID = Integer.parseInt(tempSpirit.substring(0, commaPlace));
				tempSpirit = tempSpirit.substring((commaPlace + 1), tempSpirit.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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

//		 add Ministry Area Comma Delimited String
		if ( aHeadObj.getUSPMinistryAreasCause().length() > 0 ){
			String tempMin = aHeadObj.getUSPMinistryAreasCause();
			while(tempMin.length() > 1){
				int commaPlace = tempMin.indexOf(",");
				int tempTID = Integer.parseInt(tempMin.substring(0, commaPlace));
				tempMin = tempMin.substring((commaPlace + 1), tempMin.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
	
//		 add Global Issues Comma Delimited String
		if ( aHeadObj.getUSPGlobalIssues().length() > 0 ){
			String tempGlobal = aHeadObj.getUSPGlobalIssues();
			while(tempGlobal.length() > 1){
				int commaPlace = tempGlobal.indexOf(",");
				int tempTID = Integer.parseInt(tempGlobal.substring(0, commaPlace));
				tempGlobal = tempGlobal.substring((commaPlace + 1), tempGlobal.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Organizational Development Comma Delimited String
		if ( aHeadObj.getUSPOrganizationalDev().length() > 0 ){
			String tempOrg = aHeadObj.getUSPOrganizationalDev();
			while(tempOrg.length() > 1){
				int commaPlace = tempOrg.indexOf(",");
				int tempTID = Integer.parseInt(tempOrg.substring(0, commaPlace));
				tempOrg = tempOrg.substring((commaPlace + 1), tempOrg.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
//		 add Reconciliation & Culture Comma Delimited String
		if ( aHeadObj.getUSPReconciliation().length() > 0 ){
			String tempRec = aHeadObj.getUSPReconciliation();
			while(tempRec.length() > 1){
				int commaPlace = tempRec.indexOf(",");
				int tempTID = Integer.parseInt(tempRec.substring(0, commaPlace));
				tempRec = tempRec.substring((commaPlace + 1), tempRec.length());
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
					" AND vid = " + vidCauseTopic + " ";
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		/*
		 * add ARRAY items from multi-select form
		 */
		// add Looking For Array
		if ( aHeadObj.getUSPLookingForArray()!=null ){
			if ( aHeadObj.getUSPLookingForArray().length > 0 ){
				index=0;
				int[] tempLookingFor = aHeadObj.getUSPLookingForArray();
				for (int i=0; i<tempLookingFor.length; i++){
					int tempTID = tempLookingFor[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidLookingFor + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Languages Array
		if ( aHeadObj.getUSPLanguagesArray()!=null ){
			if ( aHeadObj.getUSPLanguagesArray().length > 0 ){
				index=0;
				int[] tempLanguages = aHeadObj.getUSPLanguagesArray();
				for (int i=0; i<tempLanguages.length; i++){
					int tempTID = tempLanguages[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidVolLang + " ";
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
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode=pConn.ExecutePrepQry();
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
		// add Service Areas Array
		if ( aHeadObj.getUSPServiceAreasArray()!=null ){
			if ( aHeadObj.getUSPServiceAreasArray().length > 0 ){
				index=0;
				int[] tempService = aHeadObj.getUSPServiceAreasArray();
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Skills Array
		if ( aHeadObj.getUSPSkillTypesArray()!=null ){
			if ( aHeadObj.getUSPSkillTypesArray().length > 0 ){
				index=0;
				int[] tempSkillTypes = aHeadObj.getUSPSkillTypesArray();
				for (int i=0; i<tempSkillTypes.length; i++){
					int tempTID = tempSkillTypes[index];
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Spiritual Dev Array
		if ( aHeadObj.getUSPSpiritualDevArray()!=null){
			if ( aHeadObj.getUSPSpiritualDevArray().length > 0 ){
				index=0;
				int[] tempSpiritualDev = aHeadObj.getUSPSpiritualDevArray();
				for (int i=0; i<tempSpiritualDev.length; i++){
					int tempTID = tempSpiritualDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Ministry Area Array
		if ( aHeadObj.getUSPMinistryAreasArray()!= null ){
			if ( aHeadObj.getUSPMinistryAreasArray().length > 0 ){
				index=0;
				int[] tempMinistryAreas = aHeadObj.getUSPMinistryAreasArray();
				for (int i=0; i<tempMinistryAreas.length; i++){
					int tempTID = tempMinistryAreas[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Global Issues Array
		if ( aHeadObj.getUSPGlobalIssuesArray()!= null){
			if ( aHeadObj.getUSPGlobalIssuesArray().length > 0 ){
				index=0;
				int[] tempGlobalIssues = aHeadObj.getUSPGlobalIssuesArray();
				for (int i=0; i<tempGlobalIssues.length; i++){
					int tempTID = tempGlobalIssues[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Organizational Development Array
		if ( aHeadObj.getUSPOrganizationalDevArray()!= null ){
			if ( aHeadObj.getUSPOrganizationalDevArray().length > 0 ){
				index=0;
				int[] tempOrganizationalDev = aHeadObj.getUSPOrganizationalDevArray();
				for (int i=0; i<tempOrganizationalDev.length; i++){
					int tempTID = tempOrganizationalDev[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		// add Reconciliation & Culture Array
		if ( aHeadObj.getUSPReconciliationArray()!=null ){
			if ( aHeadObj.getUSPReconciliationArray().length > 0 ){
				index=0;
				int[] tempReconciliation = aHeadObj.getUSPReconciliationArray();
				for (int i=0; i<tempReconciliation.length; i++){
					int tempTID = tempReconciliation[index];
					index++;
				
					// grab the tid
					Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE tid=" +  tempTID + 
						" AND vid = " + vidCauseTopic + " ";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
								"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
		
		//	add Other Passions Comma Delimited String
		if ( aHeadObj.getUSPOtherPassions().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherPassions());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherPassions;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherPassions + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
					}
				}
				itid=-1;
				}
			}
		
//		add Other Skills Comma Delimited String
		if ( aHeadObj.getUSPOtherSkills().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherSkills());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					}
				else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherSkills;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherSkills + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(iRetCode == 1062 ){ // then this is a duplicate; 
						iRetCode=0;
					}else if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
					}
				}
				itid=-1;
				}
			}
		
//		add Other Learning Interests Comma Delimited String
		if ( aHeadObj.getUSPOtherLearningInterests().length() > 0 ){
			tempTaxonomy = replacesinglequote(aHeadObj.getUSPOtherLearningInterests());
			while(tempTaxonomy.length() > 1){
				int commaPlace;
				String tempName;
				// the last case, when user-entered, will likely not contain a comma, so test first to see if it contains a comma
				if(tempTaxonomy.contains(",")){
					commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy.substring(0, commaPlace);
					tempTaxonomy = tempTaxonomy.substring((commaPlace + 1), tempTaxonomy.length());
					if(tempTaxonomy.charAt(0) == ' ')
						tempTaxonomy = tempTaxonomy.substring(1, tempTaxonomy.length());
					
				}else{
					//commaPlace = tempTaxonomy.indexOf(",");
					tempName = tempTaxonomy;//.substring(0, commaPlace);
					tempTaxonomy = "";//tempName;
				}
			
				// grab the tid
				Qry1 = "SELECT tid, name FROM " + aszDrupalDB + "term_data WHERE name like '" +
						tempName + "' AND vid = " + vidOtherLearningInterests;
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
					aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
							"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
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
					//itid=-1; 
					// The option the user choose for the SELECT returned no results
					//       -should this just skip, or should it fail??  i think probably just skip, and therefore not return
					//return -1;
					aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_data(name, vid, description)" +
										"VALUES('" + tempName + "'," + vidOtherLearningInterests + ", '' ) ";
					iRetCode=pConn.PrepQuery(aszSQLdrupal101);
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
					iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
					if(0 != iRetCode){
						pConn.copyErrObj(getErrObj());
						ErrorsToLog();
						return -1;
					}
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
					} else {
						itid=-1;
					}
					if(itid>0){
						aszSQLdrupal101="INSERT INTO " + aszDrupalDB + "term_hierarchy(tid, parent) " +
						"VALUES("+ itid + ",0 )";
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
						aszSQLdrupal101="INSERT IGNORE INTO " + aszDrupalDB + "term_node(nid, tid, vid) " +
						"VALUES("+ iUPnid + "," + itid + "," + iUPvid + " )";
						iRetCode=pConn.PrepQuery(aszSQLdrupal101);
						if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
						iRetCode = pConn.ExecutePrepQry();//RunQry(Qry1); gives null pointer exception -  2009-01-16 ;;; 2009-01-20 - fixed to use ExePrepQry
						if(iRetCode == 1062 ){ // then this is a duplicate; 
							iRetCode=0;
						}else if(0 != iRetCode){
							pConn.copyErrObj(getErrObj());
							ErrorsToLog();
							return -1;
						}
					}
				}
				itid=-1;
				}
			}
		/************************** END TAXONOMY *****************************************/
		/************************** END TAXONOMY *****************************************/
		/************************** END TAXONOMY *****************************************/

//		 if it's been successful up to this point, the new user is now a Full User
		aHeadObj.setUSPInternalUserTypeComment(aszFullUser);
		aHeadObj.setUSPPasswordInternal(aHeadObj.getUSPPassword());
		

		 
		// if given a portal name, etc, then make sure this new opportunity is "favorited" by the main user
		if(aHeadObj.getPortal()>0){
	    	int iChangeNumber=0, iInitCount=0, iCurrentCount=0;
			// add to flag and add to flag counts
			aszSQLdrupal101 = " INSERT INTO " +  aszDrupalDB + "flag_content(fid, content_type, content_id, uid, timestamp, weight) " +
					" VALUES(" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + "," + aHeadObj.getUserUID() + ",UNIX_TIMESTAMP({fn now()}), 0) ";	
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
			}else{
	    		// get number of counts of the favorites flag for this content id
				aszSQLdrupal101 = " SELECT count " +
					" FROM " + aszDrupalDB + "flag_counts fl " +
					" WHERE fid=" + iFlagFavorite + " AND fl.content_id=" + aHeadObj.getPortal() + " AND content_type LIKE 'node' "; 
				iRetCode=pConn.getDBStmt();
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -999;
				}
				iRetCode = pConn.RunQuery(aszSQLdrupal101);
				if(0 != iRetCode){
					pConn.copyErrObj(getErrObj());
					ErrorsToLog();
					//return -999;
				}
				iRetCode=-1;
				// create arraylist or something, but don't know what size, b/c I don't know the size of the resultset - how to find that in java
				if(pConn.getNextResult()){
					iInitCount=pConn.getDBInt("count");
				}

    			iChangeNumber++;// successfully added and not a duplicate
        		// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
            	iCurrentCount = iInitCount + iChangeNumber;
            	if(iCurrentCount<0) iCurrentCount=0;
            	if(iCurrentCount==0){
            	}else{
					// if successful - not duplicate - make the flag counts for that content_id reflect the change; in this case, increase
					aszSQLdrupal101 = " INSERT INTO  " +  aszDrupalDB + "flag_counts (fid, content_type, content_id, count) " +
		    				" VALUES (" + iFlagFavorite + ",'node'," + aHeadObj.getPortal() + ","+iCurrentCount+") "+
		    				" ON DUPLICATE KEY UPDATE count = " + iCurrentCount ; 
		    		iRetCode=pConn.RunUpdateQuery(aszSQLdrupal101);
		    		if(0 != iRetCode){
		    			pConn.copyErrObj(getErrObj());
		    			ErrorsToLog();
		    			return -1;
		    		}
            	}
			}
		}
		return 0;
	}
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	/**************** ACTUALLY, SHOULD NEVER BE USED/TRIGGERED; SHOULD BE LEGACY!  **************/
	// end-of method insertDBUSP()


	
	/**
	* Does user exist in table userprofileinfo
	* return 0 if yes found user
	*/
	public int isLinkedInUserInSystem( ABREDBConnection pConn, String linkedInId, String aszRailsDB ){
		int iRetCode=0;
		String aszSQL=null , aszTemp=null , aszPersonCodekey=null ;
        MethodInit("isLinkedInUserInSystem");
		if(null == pConn) return -1;
		if(null == linkedInId) return -1;
        aszTemp = linkedInId.trim();
        if(aszTemp.length() < 2) return -1;
System.out.println("inside isLinkedInUserInSystem");        
        aszSQL = "SELECT up.nid, p.id FROM " + aszDrupalDB + "content_type_uprofile as up " +
        "INNER JOIN " + aszRailsDB + "profiles as p ON up.nid = p.drupal_uprofile_nid " +
        "WHERE p.linkedin_id = '" + aszTemp + "'";
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
System.out.println("the facebook user ID is in the system for node " + pConn.getDBInt("nid") );        
			// there was a result, so return 0
            return 0;
		}
		return -1;
    }

	public int updateDBUSPLinkedinConnection(ABREDBConnection pConn, PersonInfoDTO aHeadObj, String railsDB) {
		int iRetCode=0;
		String aszSQL101, currentLinkedinUID;
		int iUPnid=-1, iUPvid=-1, itid=-1, ilid=-1, iuid=-1;

		MethodInit("updateDBUSPLinkedinConnection");
		if(null == pConn){
			setErr("null database connection");
			return -1;
		}
		if(null == aHeadObj){
			setErr("null input object");
			return -1;
		}
		
		iuid = aHeadObj.getUserUID();
	    iUPnid = aHeadObj.getUserProfileNID();
	    iUPvid = aHeadObj.getUserProfileVID();
	    
	    if(aHeadObj.getUSPInternalComment().equalsIgnoreCase("linkLinkedinAccount")){
		    // for first time linking with Facebook Connect, check if the user already has a FB user ID
		    aszSQL101="SELECT * FROM " + railsDB + "profiles " +
		    	"WHERE drupal_uprofile_nid=" + iUPnid + "";
		    iRetCode=pConn.PrepQuery(aszSQL101);
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			iRetCode=pConn.ExePrepQry();
			
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -1;
			}
			
			// Get linkedin id From ResultSet
			// If the recorded Facebook ID is not empty or zero, and is not equal to the new Facebook ID
			// we want to set, flag this as an error
			if(pConn.getNextResult()){
				
				currentLinkedinUID = pConn.getDBString("linkedin_id");
				if(currentLinkedinUID != null){
					if(currentLinkedinUID.length() > 1){
						if( ! currentLinkedinUID.equalsIgnoreCase(aHeadObj.getLinkedInId())){
							return 555;
						} else { //if the facebook user ID is equal, we don't need to bother updating it
							return 0;
						}
					}
				}
			}
	    }

		//update uprofile for user with the facebook user id
		aszSQL101="UPDATE " + railsDB + "profiles " + 
			"SET " + " linkedin_id = '" + aHeadObj.getLinkedInId() + "', " +
				" linkedin_access_token = '" + aHeadObj.getLinkedInAccessToken() + "', " +
			    " linkedin_access_secret = '" + aHeadObj.getLinkedInAccessSecret() + "' " +
				" WHERE drupal_uprofile_nid=" + iUPnid + "";  
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

		return 0;
	}

	public int getQuestionnaireInstanceId(ABREDBConnection pConn,
			PersonInfoDTO person, OrgOpportunityInfoDTO opp, String aszRailsDB) {
		int iRetCode=0;
		String aszSQL=null;
        MethodInit("getQuestionniareInstanceId");
		if(null == pConn) return -1;
		if(null == person) return -1;
		if(null == opp) return -1;
                
        aszSQL = " select qi.id " + 
                 " from " + aszRailsDB + "questionnaire_instances as qi " +
        		 " inner join " + aszRailsDB + " opportunities_questionnaires as oq on qi.opportunities_questionnaire_id = oq.id " +
                 " where qi.profile_id = '" + person.getUserRailsID() + "'" + 
                 " and oq.opportunity_nid = '" + opp.getOPPNID() + "' " +
                 " order by qi.created_at DESC limit 1";
		iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
		if(0 != iRetCode){
			iRetCode=pConn.getDBStmt(); // keep getting nested IO exceptions - what if this is received??
			if(0 != iRetCode){
				pConn.copyErrObj(getErrObj());
				ErrorsToLog();
				return -999;
			}
		}
		iRetCode = pConn.RunQuery(aszSQL);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			ErrorsToLog();
			return -999;
		}
		if(pConn.getNextResult()){
			return pConn.getDBInt("id");      
		}
		return 0;
	}
	
	
	private static final int iRid = 7; // set ROLE ID for ChrisVol user
	private static final String aszOrganizationUser = "organization";
	private static final String aszChurchUser = "church";
	private static final String aszVolunteerUser = "volunteer";
	private static final String aszFullUser = "users,uprofile,location,phone";
	private static final String aszProfileLocationUser = "users,uprofile,location";
	private static final String aszProfileUser = "users,uprofile";
	private static final String aszDrupalUser = "users";
	private static final String aszCVIntern = "cvintern";
	private static final String aszOrgContact = "added-org-contact", aszOppContact = "added-opp-contact", aszOrgAdmin = "added-org-admin";
	private static final int iLocalVolTID = 17254,iGroupFamilyTID = 17255,iVolBoardTID = 17256,iVolVirtTID = 17257,iIntrnTID = 17258,
			iSumIntrnTID = 17259,iWorkStudyTID = 17260,iJobsTID = 17261,iConferenceTID = 17262,iConsultingTID = 17265,
			iSocJustGrpsTID = 17266,iLocalOrgsTID = 21853,
			iVolDirectorytid = 8864;
	private static final int vidPosType=34, vidService=32, vidSkill=31, vidVolInfo=33, 
		vidDenomAffil=19, vidOrgAffil=5, 
		//vidVolDenom=262, vidVolOrgAffil=20, 
		vidVolDenom=19, vidVolOrgAffil=5, 
		vidMemberType=42, vidProgramType=41, vidLangSpoken=48,
		vidWorkStudy=264, vidTripLength=263, vidRoomBoard=265, vidStipend=266, vidPosFreq=268, vidSchedDate=269,
		//vidVolSkill=18, vidVolInterestArea=46, 
		vidVolSkill=31, vidVolInterestArea=32,
		vidVolServiceArea=32, vidVolCause=8, 
		vidState=52, vidCity=15, vidCountry=261,// vidSubRegion=261, 
		vidRegion=38, vidVolVirt=49,
		vidVolLang=48, vidVolBoard=50, vidVolAvail=47, vidVolunteer=279, vidLookingFor=332, vidPersonality=336,
		vidCauseTopic=8, vidOtherPassions=338, vidOtherSkills=339, vidOtherLearningInterests=340, vidConnectionSource = 345,
		vidPortalName=343; 
	private static final int spiritualTID=12523, globalIssuesTID=12520, organizationalDevelopmentTID=87, reconciliationTID=12519, ministryAreasTID=12521;
	private static final int facebookConnectTID = 25315;
	private static final int adminUserRoleID=3;
	private static final int iPhpListVolunteerUser=2;
	private static final int iPhpListOrgUser=4;
	// indicate the database if necessary
	//private String aszDrupalDB = "urbmi5_drupal.um_";
	private static final String aszWorldVisionDB = "wvtm5_drupal.";
	private static final String aszDrupalDB = "um_";
	private static final String aszDrupalVolengDB = "voleng_";
	private static final String aszVolengDB = "techmi5_voleng.";
	private static final String aszPhpListDB = "techmi5_phplist.";
	//private String aszVolengDB = "";
	private static final int iFlagFavorite=1;
}
