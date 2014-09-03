package com.abrecorp.opensource.base;

/**
* Title:        Java Extended Properties Base Class
* Description:  Provide Access to java properties files
* Copyright:    Copyright (c) 2001
* Company:      Marcillo Inc.
* @author       C. DAvid Marcillo
* @version 1.0
*/

import java.util.Properties;
import java.io.FileInputStream;


public class ABREPropertyFile extends Properties {

    /**
    * Constructor
    */
    public ABREPropertyFile(){}


    /**
    * load property file
    */
    public int load(String filename){
        int iRetCode=0;
        if(null == filename){
            return 1;
        }
        m_PropertyFileName=filename.trim();
        if(m_PropertyFileName.length() < 1) {
            m_PropertyFileName=null;
            return 1;
        }
        iRetCode = loadProp();
        return iRetCode;
    }

    // ------------------------------ Private Methods
    // ------------------------------ Private Methods
    // ------------------------------ Private Methods

    private int loadProp(){
        boolean bError=false;
        FileInputStream in = null;
	try {
            in = new FileInputStream(m_PropertyFileName + ".properties");
	    super.load(in);
            bError=false;
	} catch (java.io.FileNotFoundException e) {
	    in = null;
            bError=true;
	} catch (java.io.IOException e) {
	    in = null;
            bError=true;
	} finally {
	    if (in != null) {
		try { in.close(); } catch (java.io.IOException e) { }
		in = null;
	    }
	}
        if(bError) return 1;
        return 0;
    }

    // ------------------------------ Private Variables
    // ------------------------------ Private Variables
    // ------------------------------ Private Variables

    private String m_PropertyFileName=null;



}
