package com.abrecorp.opensource.struts;

import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.abrecorp.opensource.dataobj.SearchURLTranslatorDTO;

public class SearchURLTranslatorActionHelper extends DispatchAction {
	private ActionHelper m_BaseHelp = new ActionHelper();
	
	public void getFormData(DynaActionForm ofrm, SearchURLTranslatorDTO aHeadObj) {
		aHeadObj.setURL(m_BaseHelp.getFormData(ofrm, "url"));
		aHeadObj.setAPIKey(m_BaseHelp.getFormData(ofrm, "apikey"));
		aHeadObj.setOutputFormat(m_BaseHelp.getFormData(ofrm, "outputFormat"));
	}
	
	public void setFormData(DynaActionForm ofrm, SearchURLTranslatorDTO aHeadObj) {
		m_BaseHelp.setFormData(ofrm, "url", aHeadObj.getURL());
		m_BaseHelp.setFormData(ofrm, "apikey", aHeadObj.getAPIKey());
		m_BaseHelp.setFormData(ofrm, "outputFormat", aHeadObj.getOutputFormat());
		m_BaseHelp.setFormData(ofrm, "errormsg", aHeadObj.getErrorMsg());
	}
}
