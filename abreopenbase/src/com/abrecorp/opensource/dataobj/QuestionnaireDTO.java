package com.abrecorp.opensource.dataobj;

import java.io.Serializable;

public class QuestionnaireDTO extends BaseInfoObj implements Serializable, Cloneable {
	/**
	* constructor
	*/
	public QuestionnaireDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			QuestionnaireDTO el = (QuestionnaireDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}

	private int m_id;
	public void setID(int id) {
		m_id = id;
	}
	public int getID() {
		return m_id;
	}
	
	private String m_title = null;
	public void setTitle(String title) {
		m_title = title;
	}
	public String getTitle() {
		return m_title;
	}
	
	private int m_organizationNID;
	public void setOrganizationNID(int organizationNID) {
		m_organizationNID = organizationNID;
	}
	public int getOrganizationNID() {
		return m_organizationNID;
	}
}
