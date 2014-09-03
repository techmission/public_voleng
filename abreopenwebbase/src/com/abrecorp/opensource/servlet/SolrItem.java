package com.abrecorp.opensource.servlet;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class SolrItem {
	    
	
	
	@Field
	String id;

	@Field("nid")
	int nid;

	@Field("popularity")
	int popularity;

	@Field("tm_member_i")
	int tm_member_i;

	@Field("title")
	String[] title;

	@Field("description")
	String[] description;

	@Field("num_volunteers")
	int num_volunteers;

	@Field("num_volunteers_org")
	int num_volunteers_org;

	@Field("faith_req")
	String[] faith_req;

	@Field("frequency")
	String[] frequency;

	@Field("trip_length")
	String[] trip_length;

	@Field("org_name")
	String[] org_name;

	@Field("org_url_alias")
	String[] org_url_alias;

	@Field("url_alias")
	String[] url_alias;
	
	@Field("postal_code")
	String[] postal_code;

	@Field("city")
	String[] city;

	@Field("city_tax")
	String[] city_tax;

	@Field("province")
	String[] province;

	@Field("province_tax")
	String[] province_tax;

	@Field("country")
	String[] country;
	
	@Field("country_tax")
	String[] country_tax;
	
	@Field("region")
	String[] region;
	
	@Field("location")
	String[] location;

	@Field("service_areas")
	String[] service_areas;

	@Field("service_areas_search")
	String[] service_areas_search;

	@Field("skills")
	String[] skills;

	@Field("skills_search")
	String[] skills_search;

	@Field("position_type")
	String[] position_type;

	@Field("intern_type")
	String[] intern_type;

	@Field("pos_pref")
	String[] pos_pref;

	@Field("geo_pref")
	String[] geo_pref;

	@Field("work_environ")
	String[] work_environ;

	@Field("cvintern_applicant")
	String[] cvintern_applicant;

	@Field("cvintern_screened")
	int cvintern_screened;

	@Field("cvintern_is_placed")
	int cvintern_is_placed;

	@Field("has_bachelors")
	String[] has_bachelors;

	@Field("credits_range")
	String[] credits_range;

	@Field("intern_length")
	String[] intern_length;

	@Field("gender")
	String[] gender;

	@Field("age_range")
	String[] age_range;

	@Field("applic_nid")
	int applic_nid;

	@Field("position_type_search")
	String[] position_type_search;

	@Field("primary_opp_type")
	String[] primary_opp_type;

	@Field("primary_opp_type_search")
	String[] primary_opp_type_search;

	@Field("great_for")
	String[] great_for;

	@Field("great_for_search")
	String[] great_for_search;

	@Field("benefits_offered")
	String[] benefits_offered;

	@Field("benefits_offered_search")
	String[] benefits_offered_search;

	@Field("work_study")
	String[] work_study;

	@Field("work_study_search")
	String[] work_study_search;

	@Field("org_affil")
	String[] org_affil;

	@Field("org_affil_search")
	String[] org_affil_search;

	@Field("denom_affil")
	String[] denom_affil;

	@Field("denom_affil_search")
	String[] denom_affil_search;

	@Field("portal")
	String[] portal;

	@Field("portal_search")
	String[] portal_search;

	@Field("org_member_type")
	String[] org_member_type;

	@Field("org_member_type_search")
	String[] org_member_type_search;

	@Field("program_type")
	String[] program_type;

	@Field("program_type_search")
	String[] program_type_search;

	@Field("keywords")
	String[] keywords;

	@Field("last_updated_dt")
	Date[] last_updated_dt;

	@Field("start_date_dt")
	Date[] start_date_dt;

	@Field("end_date_dt")
	Date[] end_date_dt;

	@Field("source")
	String[] source;

	
	
	@Field
	public void setID(String c){
	   this.id = c;
	}
	public String getID(){
		if(this.id==null) return "";
		return this.id;
	}

	@Field("nid")
	public void setNID(int c){
	   this.nid = c;
	}
	public int getNID(){
		return this.nid;
	}

	@Field("applic_nid")
	public void setApplicNID(int c){
	   this.applic_nid = c;
	}
	public int getApplicNID(){
		return this.applic_nid;
	}

	@Field("cvintern_screened")
	public void setCVInternScreened(int c){
	   this.cvintern_screened = c;
	}
	public int getCVInternScreened(){
		return this.cvintern_screened;
	}

	@Field("cvintern_is_placed")
	public void setCVInternIsPlaced(int c){
	   this.cvintern_is_placed = c;
	}
	public int getCVInternIsPlaced(){
		return this.cvintern_is_placed;
	}


	@Field("popularity")
	public void setPopularity(int c){
	   this.popularity = c;
	}
	public int getPopularity(){
		return this.popularity;
	}

	@Field("tm_member_i")
	public void setOrgMember(int c){
	   this.tm_member_i = c;
	}
	public int getOrgMember(){
		return this.tm_member_i;
	}

	@Field("title")
	public void setTitle(String[] c){
		this.title = c;
	}
	public String getTitle(){
		if(this.title==null) return "";
		return this.title[0];
	}

	@Field("description")
	public void setDescription(String[] c){
		this.description = c;
	}
	public String getDescription(){
		if(this.description==null) return "";
		return this.description[0];
	}

	@Field("num_volunteers")
	public void setNumVolunteers(int c){
	   this.num_volunteers = c;
	}
	public int getNumVolunteers(){
		return this.num_volunteers;
	}

	@Field("num_volunteers_org")
	public void setNumVolunteersOrg(int c){
	   this.num_volunteers_org = c;
	}
	public int getNumVolunteersOrg(){
		return this.num_volunteers_org;
	}

	@Field("faith_req")
	public void setFaithReq(String[] c){
		this.faith_req = c;
	}
	public String getFaithReq(){
		if(this.faith_req==null) return "";
		return this.faith_req[0];
	}

	@Field("frequency")
	public void setFrequency(String[] c){
		this.frequency = c;
	}
	public String getFrequency(){
		if(this.frequency==null) return "";
		return this.frequency[0];
	}

	@Field("trip_length")
	public void setTripLength(String[] c){
		this.trip_length = c;
	}
	public String getTripLength(){
		if(this.trip_length==null) return "";
		return this.trip_length[0];
	}

	@Field("org_name")
	public void setOrgName(String[] c){
	   this.org_name = c;
	}
	public String getOrgName(){
		if(this.org_name==null) return "";
		return this.org_name[0];
	}

	@Field("org_url_alias")
	public void setOrgURLAlias(String[] c){
	   this.org_url_alias = c;
	}
	public String getOrgURLAlias(){
		if(this.org_url_alias==null) return "";
		return this.org_url_alias[0];
	}

	@Field("url_alias")
	public void setURLAlias(String[] c){
	   this.url_alias = c;
	}
	public String getURLAlias(){
		if(this.url_alias==null) return "";
		return this.url_alias[0];
	}

	@Field("postal_code")
	public void setPostalCode(String[] c){
	   this.postal_code = c;
	}
	public String getPostalCode(){
		if(this.postal_code==null) return "";
		return this.postal_code[0];
	}

	@Field("city")
	public void setCity(String[] c){
	   this.city = c;
	}
	public String getCity(){
		if(this.city==null) return "";
		return this.city[0];
	}

	@Field("city_tax")
	public void setCityTax(String[] c){
	   this.city_tax = c;
	}
	public String getCityTax(){
		if(this.city_tax==null) return "";
		return this.city_tax[0];
	}

	@Field("province")
	public void setProvince(String[] c){
	   this.province = c;
	}
	public String getProvince(){
		if(this.province==null) return "";
		return this.province[0];
	}

	@Field("province_tax")
	public void setProvinceTax(String[] c){
	   this.province_tax = c;
	}
	public String getProvinceTax(){
		if(this.province_tax==null) return "";
		return this.province_tax[0];
	}

	@Field("country")
	public void setCountryISO(String[] c){
	   this.country = c;
	}
	public String getCountryISO(){
		if(this.country==null) return "";
		return this.country[0];
	}
	
	@Field("country_tax")
	public void setCountryTax(String[] c){
		this.country_tax = c;
	}
	public String getCountryTax(){
		if(this.country_tax==null) return "";
		return this.country_tax[0];
	}
	
	@Field("region")
	public void setRegion(String[] c){
		this.region = c;
	}
	public String getRegion(){
		if(this.region==null) return "";
		return this.region[0];
	}
	
	@Field("location")
	public void setLocation(String[] c){
		this.location = c;
	}
	public String getLocation(){
		if(this.location==null) return "";
		return this.location[0];
	}

	@Field("service_areas")
	public void setServiceAreas(String[] c){
	   this.service_areas = c;
	}
	public String getServiceAreas(){
		if(this.service_areas==null) return "";
		int i_size=this.service_areas.length;
		String aszServiceAreas="";
		for(int i=0 ; i<i_size; i++){
			aszServiceAreas += this.service_areas[i];
			if(i_size-i>1) aszServiceAreas += ", ";
		}
		return aszServiceAreas;
	}

	@Field("service_areas_search")
	public void setServiceAreasSearch(String[] c){
	   this.service_areas_search = c;
	}
	public String getServiceAreasSearch(){
		if(this.service_areas_search==null) return "";
		int i_size=this.service_areas_search.length;
		String aszServiceAreas="";
		for(int i=0 ; i<i_size; i++){
			aszServiceAreas += this.service_areas_search[i];
			if(i_size-i>1) aszServiceAreas += ", ";
		}
		return aszServiceAreas;
	}

	@Field("skills")
	public void setSkills(String[] c){
	   this.skills = c;
	}
	public String getSkills(){
		if(this.skills==null) return "";
		int i_size=this.skills.length;
		String aszSkills="";
		for(int i=0 ; i<i_size; i++){
			aszSkills += this.skills[i];
			if(i_size-i>1) aszSkills += ", ";
		}
		return aszSkills;
	}

	@Field("skills_search")
	public void setSkillsSearch(String[] c){
	   this.skills_search = c;
	}
	public String getSkillsSearch(){
		if(this.skills_search==null) return "";
		int i_size=this.skills_search.length;
		String aszSkills="";
		for(int i=0 ; i<i_size; i++){
			aszSkills += this.skills_search[i];
			if(i_size-i>1) aszSkills += ", ";
		}
		return aszSkills;
	}

	@Field("intern_type")
	public void setInternType(String[] c){
	   this.intern_type = c;
	}
	public String getInternType(){
		if(this.intern_type==null) return "";
		return this.intern_type[0];
	}

	@Field("pos_pref")
	public void setInternPosType(String[] c){
	   this.pos_pref = c;
	}
	public String getInternPosType(){
		if(this.pos_pref==null) return "";
		return this.pos_pref[0];
	}

	@Field("work_environ")
	public void setWorkEnviron(String[] c){
	   this.work_environ = c;
	}
	public String getWorkEnviron(){
		if(this.work_environ==null) return "";
		return this.work_environ[0];
	}

	@Field("geo_pref")
	public void setGeoPref(String[] c){
	   this.geo_pref = c;
	}
	public String getGeoPref(){
		if(this.geo_pref==null) return "";
		return this.geo_pref[0];
	}

	@Field("cvintern_applicant")
	public void setCVInternApplicant(String[] c){
	   this.cvintern_applicant = c;
	}
	public String getCVInternApplicant(){
		if(this.cvintern_applicant==null) return "";
		return this.cvintern_applicant[0];
	}

	@Field("has_bachelors")
	public void setHasBachelors(String[] c){
	   this.has_bachelors = c;
	}
	public String getHasBachelors(){
		if(this.has_bachelors==null) return "";
		return this.has_bachelors[0];
	}

	@Field("credits_range")
	public void setCreditsRange(String[] c){
	   this.credits_range = c;
	}
	public String getCreditsRange(){
		if(this.credits_range==null) return "";
		return this.credits_range[0];
	}

	@Field("intern_length")
	public void setInternLength(String[] c){
	   this.intern_length = c;
	}
	public String getInternLength(){
		if(this.intern_length==null) return "";
		return this.intern_length[0];
	}

	@Field("gender")
	public void setGender(String[] c){
	   this.gender = c;
	}
	public String getGender(){
		if(this.gender==null) return "";
		return this.gender[0];
	}

	@Field("age_range")
	public void setAgeRange(String[] c){
	   this.age_range = c;
	}
	public String getAgeRange(){
		if(this.age_range==null) return "";
		return this.age_range[0];
	}

	@Field("position_type")
	public void setPositionType(String[] c){
	   this.position_type = c;
	}
	public String getPositionType(){
		if(this.position_type==null) return "";
		return this.position_type[0];
	}

	@Field("position_type_search")
	public void setPositionTypeSearch(String[] c){
	   this.position_type_search = c;
	}
	public String getPositionTypeSearch(){
		if(this.position_type_search==null) return "";
		return this.position_type_search[0];
	}

	@Field("primary_opp_type")
	public void setPrimaryOppTypes(String[] c){
	   this.primary_opp_type = c;
	}
	public String getPrimaryOppTypes(){
		if(this.primary_opp_type==null) return "";
		int i_size=this.primary_opp_type.length;
		String aszPrimaryOppTypes="";
		for(int i=0 ; i<i_size; i++){
			aszPrimaryOppTypes += this.primary_opp_type[i];
			if(i_size-i>1) aszPrimaryOppTypes += ", ";
		}
		return aszPrimaryOppTypes;
	}

	@Field("primary_opp_type_search")
	public void setPrimaryOppTypesSearch(String[] c){
	   this.primary_opp_type_search = c;
	}
	public String getPrimaryOppTypesSearch(){
		if(this.primary_opp_type_search==null) return "";
		int i_size=this.primary_opp_type_search.length;
		String aszPrimaryOppTypes="";
		for(int i=0 ; i<i_size; i++){
			aszPrimaryOppTypes += this.primary_opp_type_search[i];
			if(i_size-i>1) aszPrimaryOppTypes += ", ";
		}
		return aszPrimaryOppTypes;
	}

	@Field("great_for")
	public void setGreatFor(String[] c){
	   this.great_for = c;
	}
	public String getGreatFor(){
		if(this.great_for==null) return "";
		int i_size=this.great_for.length;
		String aszGreatFor="";
		for(int i=0 ; i<i_size; i++){
			aszGreatFor += this.great_for[i];
			if(i_size-i>1) aszGreatFor += ", ";
		}
		return aszGreatFor;
	}

	@Field("great_for_search")
	public void setGreatForSearch(String[] c){
	   this.great_for_search = c;
	}
	public String getGreatForSearch(){
		if(this.great_for_search==null) return "";
		int i_size=this.great_for_search.length;
		String aszGreatFor="";
		for(int i=0 ; i<i_size; i++){
			aszGreatFor += this.great_for_search[i];
			if(i_size-i>1) aszGreatFor += ", ";
		}
		return aszGreatFor;
	}


	@Field("benefits_offered")
	public void setBenefitsOffered(String[] c){
	   this.benefits_offered = c;
	}
	public String getBenefitsOffered(){
		if(this.benefits_offered==null) return "";
		int i_size=this.benefits_offered.length;
		String aszBenefitsOffered="";
		for(int i=0 ; i<i_size; i++){
			aszBenefitsOffered += this.benefits_offered[i];
			if(i_size-i>1) aszBenefitsOffered += ", ";
		}
		return aszBenefitsOffered;
	}

	@Field("benefits_offered_search")
	public void setBenefitsOfferedSearch(String[] c){
	   this.benefits_offered_search = c;
	}
	public String getBenefitsOfferedSearch(){
		if(this.benefits_offered_search==null) return "";
		int i_size=this.benefits_offered_search.length;
		String aszBenefitsOffered="";
		for(int i=0 ; i<i_size; i++){
			aszBenefitsOffered += this.benefits_offered_search[i];
			if(i_size-i>1) aszBenefitsOffered += ", ";
		}
		return aszBenefitsOffered;
	}

	@Field("work_study")
	public void setWorkStudy(String[] c){
	   this.work_study = c;
	}
	public String getWorkStudy(){
		if(this.work_study==null) return "";
		return this.work_study[0];
	}

	@Field("work_study_search")
	public void setWorkStudySearch(String[] c){
	   this.work_study_search = c;
	}
	public String getWorkStudySearch(){
		if(this.work_study_search==null) return "";
		return this.work_study_search[0];
	}

	@Field("org_affil")
	public void setOrgAffil(String[] c){
	   this.org_affil = c;
	}
	public String getOrgAffil(){
		if(this.org_affil==null) return "";
		int i_size=this.org_affil.length;
		String aszOrgAffil="";
		for(int i=0 ; i<i_size; i++){
			aszOrgAffil += this.org_affil[i];
			if(i_size-i>1) aszOrgAffil += ", ";
		}
		return aszOrgAffil;
	}

	@Field("org_affil_search")
	public void setOrgAffilSearch(String[] c){
	   this.org_affil_search = c;
	}
	public String getOrgAffilSearch(){
		if(this.org_affil_search==null) return "";
		int i_size=this.org_affil_search.length;
		String aszOrgAffil="";
		for(int i=0 ; i<i_size; i++){
			aszOrgAffil += this.org_affil_search[i];
			if(i_size-i>1) aszOrgAffil += ", ";
		}
		return aszOrgAffil;
	}

	@Field("denom_affil")
	public void setDenomAffilType(String[] c){
	   this.denom_affil = c;
	}
	public String getDenomAffil(){
		if(this.denom_affil==null) return "";
		return this.denom_affil[0];
	}

	@Field("denom_affil_search")
	public void setDenomAffilSearch(String[] c){
	   this.denom_affil_search = c;
	}
	public String getDenomAffilSearch(){
		if(this.denom_affil_search==null) return "";
		return this.denom_affil_search[0];
	}

	@Field("portal")
	public void setPortal(String[] c){
	   this.portal = c;
	}
	public String getPortal(){
		if(this.portal==null) return "";
		return this.portal[0];
	}

	@Field("portal_search")
	public void setPortalSearch(String[] c){
	   this.portal_search = c;
	}
	public String getPortalSearch(){
		if(this.portal_search==null) return "";
		return this.portal_search[0];
	}

	@Field("org_member_type")
	public void setOrgMemberType(String[] c){
	   this.org_member_type = c;
	}
	public String getOrgMemberType(){
		if(this.org_member_type==null) return "";
		return this.org_member_type[0];
	}

	@Field("org_member_type_search")
	public void setOrgMemberTypeSearch(String[] c){
	   this.org_member_type_search = c;
	}
	public String getOrgMemberTypeSearch(){
		if(this.org_member_type_search==null) return "";
		return this.org_member_type_search[0];
	}

	@Field("program_type")
	public void setProgramType(String[] c){
	   this.program_type = c;
	}
	public String getProgramType(){
		if(this.program_type==null) return "";
		int i_size=this.program_type.length;
		String aszProgramType="";
		for(int i=0 ; i<i_size; i++){
			aszProgramType += this.program_type[i];
			if(i_size-i>1) aszProgramType += ", ";
		}
		return aszProgramType;
	}

	@Field("program_type_search")
	public void setProgramTypeSearch(String[] c){
	   this.program_type_search = c;
	}
	public String getProgramTypeSearch(){
		if(this.program_type_search==null) return "";
		int i_size=this.program_type_search.length;
		String aszProgramType="";
		for(int i=0 ; i<i_size; i++){
			aszProgramType += this.program_type_search[i];
			if(i_size-i>1) aszProgramType += ", ";
		}
		return aszProgramType;
	}


	@Field("keywords")
	public void setKeywords(String[] c){
	   this.keywords = c;
	}
	public String getKeywordsSearch(){
		if(this.keywords==null) return "";
		int i_size=this.keywords.length;
		String aszKeywords="";
		for(int i=0 ; i<i_size; i++){
			aszKeywords += this.keywords[i];
			if(i_size-i>1) aszKeywords += ", ";
		}
		return aszKeywords;
	}

	@Field("last_updated_dt")
	public void setLastUpdated(Date[] c){
	   this.last_updated_dt = c;
	}
	public Date getLastUpdated(){
		if(this.last_updated_dt==null) return new Date();
		return this.last_updated_dt[0];
	}

	@Field("start_date_dt")
	public void setStartDate(Date[] c){
	   this.start_date_dt = c;
	}
	public Date getStartDate(){
		if(this.start_date_dt==null) return new Date();
		return this.start_date_dt[0];
	}

	@Field("end_date_dt")
	public void setEndDate(Date[] c){
	   this.end_date_dt = c;
	}
	public Date getEndDate(){
		if(this.end_date_dt==null) return new Date();
		return this.end_date_dt[0];
	}

	@Field("source")
	public void setSource(String[] c){
	   this.source = c;
	}
	public String getSource(){
		if(this.source==null) return "";
		return this.source[0];
	}

	// BEGIN
	// for resume fields:
	// 

	@Field("attr_content")
	String[] attr_content;

	@Field("attr_content")
	public void setAttrContent(String[] c){
	   this.attr_content = c;
	}
	public String getAttrContent(){
		if(this.attr_content==null) return "";
		return this.attr_content[0];
	}

}
