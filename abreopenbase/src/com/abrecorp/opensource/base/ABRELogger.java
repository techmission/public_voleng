package com.abrecorp.opensource.base;

/**
* Title:        Wrapper Class for logger services
* Description:  Wrapper Class for logger
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import java.util.logging.*;

public class ABRELogger {

  /*
  * Constructor
  */
  public ABRELogger(){}


  /**
  * close all logs
  */
  public void closeAll(){
    if(null == m_LogRoot) return;
    m_LogRoot.removeHandler( m_FileHandle );
    m_LogRoot=null;
    if(null == m_FileHandle) return;
    m_FileHandle.flush();
    m_FileHandle.close();
    m_FileHandle=null;
  }
  // end-of method closeAll()


  /**
  * create log category
  * category name, file full path location
  */
  public int createLogCategory(String categoryname , String fullpathlocation){
    if(null == categoryname) {
        System.out.println("Error: trying to create a null logger category");
        return -1;
    }
    if(categoryname.length() < 1) {
        System.out.println("Error: trying to create a blank logger category");
        return -1;
    }
    if(null == fullpathlocation) {
        System.out.println("Error: fullpathlocation file for logger require");
        return -1;
    }
    if(fullpathlocation.length() < 1) {
        System.out.println("Error: fullpathlocation file for logger require");
        return -1;
    }

    if(null == m_LogRoot) {
      m_LogRoot = Logger.getLogger( categoryname );
      // PropertyConfigurator.configure(config);
    }
    if(null == m_LogRoot){
        System.out.println("Error: allocation logger object");
        return -1;
    }
    if(null != m_FileHandle){
        m_FileHandle.flush();
        m_FileHandle.close();
        m_FileHandle=null;
    }
    try {
        // m_FileHandle = new FileHandler( fullpathlocation );
        m_FileHandle = new FileHandler( fullpathlocation, 100000, 10, true );
    } catch (java.io.IOException exp) {
        m_FileHandle=null;
        System.out.println("Error: allocation file handle object: " + fullpathlocation );
        return -1;
    }
    if(null == m_FileHandle){
        System.out.println("Error: allocation file handle object: " + fullpathlocation );
        return -1;
    }
    // set simple formatter
    m_FileHandle.setFormatter(new SimpleFormatter());
    // Send logger output to our FileHandler.
    m_LogRoot.addHandler( m_FileHandle );
    // Request that every detail gets logged.
    m_FileHandle.setLevel(Level.FINEST);
    m_LogRoot.setLevel(Level.FINEST);
    m_LogRoot.info("************Logger Initialized*****************");
    return 0;
  }
  // end-of method createLogCategory()


  /**
  * write a debug message to the log
  */
  public void logDebug(int iLevel, String msg){
    if(null == msg) return;
    if(msg.length() < 1) return;
    if(null == m_LogRoot) return;
    if(iLevel > m_iDebugLevel) return;
    if(false == m_LogRoot.isLoggable(Level.FINEST)) return;
    m_LogRoot.log(Level.FINEST, msg );
  }
  // end-of logDebug()

  /**
  * write info message to the log
  */
  public void logInfo(String msg){
    if(null == msg) return;
    if(msg.length() < 1) return;
    if(null == m_LogRoot) return;
    if(false == m_LogRoot.isLoggable(Level.INFO)) return;
    m_LogRoot.info(msg);
  }
  // end-of method logInfo()


  /**
  * write error message to the log
  */
  public void logError(String msg){
    if(null == msg) return;
    if(msg.length() < 1) return;
    if(null == m_LogRoot) return;
    m_LogRoot.log(Level.SEVERE, msg );
  }
  // end-of logError()


  /**
  * set debug level
  */
  public void setDebugLevel(String number){
    Integer intObj=null;
    if(null == number) return;
    String asTemp = number.trim();
    if(asTemp.length() < 1) return;
		try {
			intObj = Integer.valueOf(asTemp);
			m_iDebugLevel=intObj.intValue();
			return;
		} catch (NumberFormatException  ex){
			m_iDebugLevel=0;
			return;
		}
  }
  // end-of method setDebugLevel()

  public void setDebugLevel(int msg){
    m_iDebugLevel=msg;
  }
  // end-of method setDebugLevel()


  private static Logger m_LogRoot=null;
  private static FileHandler m_FileHandle=null;
  private int m_iDebugLevel=0;

}
