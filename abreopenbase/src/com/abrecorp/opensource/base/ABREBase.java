package com.abrecorp.opensource.base;

/**
* Title:        ABRE Base Class
* Description:  Provide Tracing services
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. DAvid Marcillo
* @version      1.0
*/

import java.io.*;


public abstract class ABREBase extends ABRESimpleBase {


    /**
    * set error message to this module
    */
    public void setErrMsg(String msg){
        allocErrObj();
        m_ErrMsg.clearMessages();
        m_ErrMsg.setInterface("");
        m_ErrMsg.setMessage(msg);
    }

    /**
    * Set log/trace Object
    */
    public void setLogObj(ABRELogger log){
        if(null == log) return;
        if(null == m_LogObj){
            m_LogObj=log;
        }
    }
    // end-of setLogObj()

    /**
    * close log4j file
    */
    public void closeLog4J(){
        if(null != m_LogObj){
            m_LogObj.logInfo("log file is being closed");
            m_LogObj.closeAll();
        }
        m_LogObj=null;
    }
    // end-of closeLog4J()

    /**
    * Get log/trace Object
    */
    public ABRELogger getLogObj(){
        return m_LogObj;
    }

    /**
    * Populate a passed-in error message object
    * with all error messages from this module.
    */
    public void copyErrObj(ABRELogMsg err){
        int iMaxMsg,iIndex;
        if(null == err) return;
        if(null == m_ErrMsg) return;
        iMaxMsg = m_ErrMsg.getTotalMessages();
        for (iIndex=0;iIndex < iMaxMsg; iIndex++){
            err.setMessage(m_ErrMsg.getMessages(iIndex));
        }
    }

    /**
    * Return this class error mesage object.
    */
    public ABRELogMsg getErrObj(){
        return m_ErrMsg;
    }

    /**
    * Return all error messages
    * as one String object
    */
    public String getAllMessages(){
        int iMaxMsg=0,iIndex=0;
        if(null == m_ErrMsg) return "";
        iMaxMsg = m_ErrMsg.getTotalMessages();
        if(iMaxMsg < 1) return "";
        StringBuffer aBuff = new StringBuffer();
        for (iIndex=0;iIndex < iMaxMsg; iIndex++){
            if(iIndex > 0) aBuff.append(" ");
            aBuff.append(m_ErrMsg.getMessages(iIndex));
        }
        return aBuff.toString();
    }
    // end-of getAllMessages()

    // ------------------------------- Protected Methods
    // ------------------------------- Protected Methods
    // ------------------------------- Protected Methods

    /**
    * write info message to log
    */
    protected void logInfo(String msg){
        if(null == m_LogObj) return;
        m_LogObj.logInfo(msg);
    }

    /**
    * write debug message to log
    */
    protected void logDebug(int iLevel, String msg){
        if(null == m_LogObj) return;
        m_LogObj.logDebug(iLevel,msg);
    }

    /**
    * Write error messages to the log file.
    */
    protected void ErrorsToLog(){
        WriteErrorsToLog();
    }

    /**
    * add an error message to this module
    */
    protected void setErr(String msg){
        allocErrObj();
        m_ErrMsg.setMessage(msg);
    }
    // end-of setErr()

    /**
    * init error interface and clear previous messages
    */
    protected void MethodInit(String name){
        allocErrObj();
        m_ErrMsg.clearMessages();
        m_ErrMsg.setInterface(name);
    }
    // end-of MethodInit()


    // ------------------------------- Private Methods
    // ------------------------------- Private Methods
    // ------------------------------- Private Methods


  /**
  * Init and clear error message object
  */
	private void clearErr(){
    allocErrObj();
    m_ErrMsg.clearMessages();
		m_ErrMsg.setInterface("init");
	}
  // end-of clearErr()

    /**
    * write error messages to log
    */
    private void WriteErrorsToLog(){
        int iMax=0,iIndex=0;
        if(null == m_ErrMsg) return;
        if(null == m_LogObj) return;
        if(m_ErrMsg.getModuleName().length() > 0){
            m_LogObj.logError("From: " + m_ErrMsg.getModuleName() + " Interface:" + m_ErrMsg.getInterface());
        }
        iMax=m_ErrMsg.getTotalMessages();
        for(iIndex=0; iIndex < iMax; iIndex++){
            m_LogObj.logError(m_ErrMsg.getMessages(iIndex));
        }
    }
    // end-of WriteErrorsToLog()


    /**
    * allocate error message
    */
    private void allocErrObj(){
        if(null == m_ErrMsg){
            m_ErrMsg = new ABRELogMsg();
            m_ErrMsg.setModuleName(this.getClass().getName());
            m_ErrMsg.setInterface("init");
        }
    }
    // end-of allocErrObj()


    // ------------------------------- Private Variables
    // ------------------------------- Private Variables
    // ------------------------------- Private Variables

    private ABRELogMsg m_ErrMsg=null;
    private ABRELogger m_LogObj=null;

}
