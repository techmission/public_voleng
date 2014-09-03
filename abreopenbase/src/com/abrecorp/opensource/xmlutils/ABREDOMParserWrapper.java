package com.abrecorp.opensource.xmlutils;

/*
* Encapsulates a DOM parser.
*
* <http://www.apache.org/>.
*/

import org.w3c.dom.Document;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

interface ABREDOMParserWrapper {

    //
    // DOMParserWrapper methods
    //

    /** 
    * Parses the specified URI and returns the document. 
    **/
    public Document parse(String uri) throws Exception;

    public Document parseInput(String uri) throws Exception;

  /**
   * Set the state of a feature.
   *
   * Set the state of any feature in a SAX2 parser.  The parser
   * might not recognize the feature, and if it does recognize
   * it, it might not be able to fulfill the request.
   *
   * @param featureId The unique identifier (URI) of the feature.
   * @param state The requested state of the feature (true or false).
   *
   * @exception org.xml.sax.SAXNotRecognizedException If the
   *            requested feature is not known.
   * @exception org.xml.sax.SAXNotSupportedException If the
   *            requested feature is known, but the requested
   *            state is not supported.
   * @exception org.xml.sax.SAXException If there is any other
   *            problem fulfilling the request.
   */

    public void     setFeature(String featureId, boolean state)
            throws  SAXNotRecognizedException, SAXNotSupportedException; 


}
