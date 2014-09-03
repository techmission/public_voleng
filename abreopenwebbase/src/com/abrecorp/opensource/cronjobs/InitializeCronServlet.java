package com.abrecorp.opensource.cronjobs;

/**
* System:       Java Server Pages Test Utility
* Title:        Test Basic Servlet Functions
* Description:  
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class InitializeCronServlet extends HttpServlet {


 public void init() throws ServletException {
    
    try {
        System.out.println("Initializing NewsLetter PlugIn");
        
        CronScheduler objPlugin = new CronScheduler();
        
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    
  }
 
}