package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Show A Web Page
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageActions extends DispatchAction {

	/**
	* Constructor 
	*/
	public ShowPageActions() {}

	/*
	* show requested page
	* sample html url
	* <a href="<%=request.getContextPath()%>/showpage.do?page=xxxx">what you want to show</a>
	*/
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	String aszForwardActionName = (String)httpServletRequest.getParameter("page");
    	if(null == aszForwardActionName) aszForwardActionName="";
    	aszForwardActionName = aszForwardActionName.trim();
    	if(aszForwardActionName.length() > 1){
        	return actionMapping.findForward( aszForwardActionName );
    	}
    	return actionMapping.findForward( "home" );
    }
    // end-of method execute()


}
