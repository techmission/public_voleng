package com.abrecorp.opensource.servlet;

/**
* Title:        ABRE Base Class For Web Based BRLO
* Description:  Provide Basic Services To Business Rules Layer Objects
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. DAvid Marcillo
* @version      1.0
*/

import javax.servlet.http.HttpServletRequest;

import com.abrecorp.opensource.base.*;

public abstract class BaseServletBRLO extends ABREBaseBRLO {

	/**
    * access to application wide services
    */
	public void setupApp(HttpServletRequest request){
		BaseServletABRE aServ = new BaseServletABRE();
		setBaseAppRef( aServ.getAppServer(request) );
		return ;
	}
    // end-of method setBaseAppRef()

}
