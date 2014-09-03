package com.abrecorp.opensource.voleng.brlayer;

import javax.servlet.http.HttpServletRequest;

import com.abrecorp.opensource.appserver.TheApplication;
import com.abrecorp.opensource.base.ABREAppServer;
import com.abrecorp.opensource.base.ABREBaseBRLO;
import com.abrecorp.opensource.servlet.BaseServletABRE;

abstract class BaseVolEngBRLO extends ABREBaseBRLO {

	public void setupApp(HttpServletRequest request){
		BaseServletABRE aServ = new BaseServletABRE();
		setBaseAppRef( aServ.getAppServer(request) );
		return ;
	}
}
