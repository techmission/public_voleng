package com.abrecorp.opensource.xmlutils;

/*
* Wraps the Xerces DOM parser and extends NonValidatingDOMParser
* <http://www.apache.org/>.
*/

import org.w3c.dom.Document;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.InputSource;

import com.abrecorp.opensource.base.ABREBase;

import java.io.*;

class ABREDOMParser extends ABREBase implements ABREDOMParserWrapper, ErrorHandler {

	/**
	* Constructor
	*/
	public ABREDOMParser() {
        try {
            parser.setFeature( "http://xml.org/sax/features/validation", true);
        } catch (SAXException exp) {
        	setErr("constructor: error in setting up parser feature");
        	setErr( exp.getMessage() );
        }
        parser.setErrorHandler(this);
	}

    /**
    * Parser. 
    **/
    org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();

    /**
    * Parses the specified URI and returns the document. 
    **/
    public Document parse(String uri) throws Exception {
        parser.parse(uri);
        return parser.getDocument();
    } 
    // parse(String):Document

    /**
    * Parses the specified URI and returns the document. 
    **/
    public Document parseInput(String indata) throws Exception {
    	InputStream byteData=null;
    	byteData = new ByteArrayInputStream(indata.getBytes());
    	InputSource aIn = new InputSource( byteData);
        parser.parse(aIn);
        return parser.getDocument();
    } 
    // parse(String):Document


    public void     setFeature(String featureId, boolean state)
            throws  SAXNotRecognizedException, SAXNotSupportedException {
            parser.setFeature( featureId, state );
    }


    /** 
    * Warning. 
    **/
    public void warning(SAXParseException ex) {
        System.err.println("[Warning] "+
                           getLocationString(ex)+": "+
                           ex.getMessage());
    }

    /** 
    * Error. 
    **/
    public void error(SAXParseException ex) {
        System.err.println("[Error] "+
                           getLocationString(ex)+": "+
                           ex.getMessage());
    }

    /** 
     * Fatal error. 
     * */
    public void fatalError(SAXParseException ex) throws SAXException {
        System.err.println("[Fatal Error] "+
                           getLocationString(ex)+": "+
                           ex.getMessage());
        throw ex;
    }


    //
    // Private methods
    //

    /** Returns a string of the location. */
    private String getLocationString(SAXParseException ex) {
        StringBuffer str = new StringBuffer();
        String systemId = ex.getSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1) 
                systemId = systemId.substring(index + 1);
            str.append(systemId);
        }
        str.append(':');
        str.append(ex.getLineNumber());
        str.append(':');
        str.append(ex.getColumnNumber());
        return str.toString();
    } 
    // getLocationString(SAXParseException):String


}
