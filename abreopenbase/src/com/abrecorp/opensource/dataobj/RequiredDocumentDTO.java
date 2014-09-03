package com.abrecorp.opensource.dataobj;

import java.io.File;
import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class RequiredDocumentDTO implements Serializable, Cloneable {
	/**
	* constructor
	*/
	public RequiredDocumentDTO(){}

	/**
	* public clone method
	*/
	public Object clone(){
		try{
			RequiredDocumentDTO el = (RequiredDocumentDTO) super.clone();
			return el;
		} catch (CloneNotSupportedException exp){
			return null;
		}
	}

	private int m_oppNid;
	
	public void setOppNid(int i) {
		m_oppNid = i;
	}
	
	public int getOppNid() {
		return m_oppNid;
	}
	
	private String m_name = null;
	
	public void setName(String s) {
		m_name = s;
	}
	
	public String getName() {
		return m_name;
	}
	
	private FormFile m_clientFile = null;
	
	public void setClientFile(FormFile f) {
		m_clientFile = f;
	}
	
	public FormFile getClientFile() {
		return m_clientFile;
	}
	
	private int m_nid;
	
	public void setNid(int i) {
		m_nid = i;
	}
	
	public int getNid() {
		return m_nid;
	}
	
	private int m_vid;
	
	public int getVid() {
		return m_vid;
	}
	
	public void setVid(int i) {
		m_vid = i;
	}
	
	private String m_extension;
	
	public void setExtension(String s) {
		m_extension = s;
	}
	
	public String getExtension() {
		return m_extension;
	}
	
	public String getPublicPath() {
		return "/required_documents/" + this.getNid();
	}
}
