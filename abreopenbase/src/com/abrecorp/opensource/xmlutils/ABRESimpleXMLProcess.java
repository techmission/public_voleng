package com.abrecorp.opensource.xmlutils;

/*
* public Class To Process XML with Simple Interfaces
* expects XML payload as input string variable
* this class is state-full; in other words, memory will be used and maintained while it lives...
* as of July 21, 2006 we are using The Xerces Java Parser 1.4.4 
*
*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.abrecorp.opensource.base.ABREBase;

public class ABRESimpleXMLProcess extends ABREBase {


    public static final short CDATA_SECTION_NODE = Node.CDATA_SECTION_NODE;
    public static final short TEXT_NODE = Node.TEXT_NODE;


    /**
	* Constructor 
	*/
	public ABRESimpleXMLProcess(){}

	/**
	* clean state variable 
	*/
	public void cleanup(){
		cleanupInternal();
	}
	// end-of method cleanup()

	/**
	* get element data by node type 
	*/
	public String getElementDataByNodeType( String aszTagIn, short iType ){
		String aszTempTagIn = "";
		if(null == aszTagIn){
			return aszTempTagIn;
		}
		aszTempTagIn = aszTagIn.trim();
		if(aszTempTagIn.length() < 1){
			return aszTempTagIn;
		}
		NodeList alist = getElementNodeList( aszTempTagIn );
    	if(null == alist){
			return "";
		}
		int iLen = alist.getLength();
		for(int index1=0; index1 < iLen; index1++){
			Element aItem = (Element)alist.item( index1 );
			if(null == aItem) continue;
        	NodeList alist2 = aItem.getChildNodes();
			if(null == alist2) continue;
    		int iLen2 = alist2.getLength();
			if(iLen2 < 1) continue;
    		for(int index2=0; index2 < iLen2; index2++){
    			Node node = alist2.item( index2 );
    			if(null == node) continue;
    			int type = node.getNodeType();
    			if(type == iType){
                	String attri1 = node.getNodeValue();
                	if(null == attri1) return "";
    				return attri1;
    			}
    		}
		}
		return "";
	}
	// end-of method getElementDataByNodeType()

	/**
	* check for element tag present under root element 
	*/
	public boolean isElementTagPresent( String aszTagIn ){
		if(null == aszTagIn){
			return false;
		}
		String aszTempTagIn = aszTagIn.trim();
		if(aszTempTagIn.length() < 1){
			return false;
		}
		if(null == m_ABREParser){
			return false;
		}
		NodeList alist = getElementNodeList( aszTempTagIn );
    	if(null == alist){
			return false;
		}
		return true;
	}
	// end-of method isElementTagPresent()


	/**
	* check root element 
	*/
	public boolean isRootTagEqualTo( String aszTagIn ){
		if(null == aszTagIn){
			return false;
		}
		String aszTempTagIn = aszTagIn.trim();
		if(aszTempTagIn.length() < 1){
			return false;
		}
		if(null == m_ABREParser){
			return false;
		}
        Element aRoot = getRootElement();
        if(null == aRoot){
			return false;
		}
        String aszRootName=null;
    	aszRootName = aRoot.getTagName();
    	if(null == aszRootName){
			return false;
		}
    	aszRootName = aszRootName.trim();
    	if(aszRootName.length() < 1) {
			return false;
    	}
    	if(aszRootName.equalsIgnoreCase( aszTempTagIn )){
    		return true;
    	}
    	return false;
	}
	// end-of method isRootTagEqualTo()

	/**
	* load passed in xml file 
	*/
	public int loadPayload( String aszXMLin ){
		cleanupInternal();
		if(null == aszXMLin){
			setErr("xml input required");
			return -1;
		}
		String aszTemp = aszXMLin.trim();
		if(aszTemp.length() < 5){
			setErr("xml input required");
			return -1;
		}

		// initializa XML filter class
        try {
        	m_ABREParser = (ABREDOMParserWrapper)Class.forName(DEFAULT_PARSER_NAME).newInstance();
            m_ABREParser.setFeature( "http://apache.org/xml/features/dom/defer-node-expansion",setDeferredDOM );
            m_ABREParser.setFeature( "http://xml.org/sax/features/validation",setValidation );
            m_ABREParser.setFeature( "http://xml.org/sax/features/namespaces",setNameSpaces );
            m_ABREParser.setFeature( "http://apache.org/xml/features/validation/schema",setSchemaSupport );
            m_ABREParser.setFeature( "http://apache.org/xml/features/validation/schema-full-checking",setSchemaFullSupport );
        } catch (Exception exp) {
        	setErr("error initializing XML filter class");
        	setErr( exp.getMessage() );
            exp.printStackTrace(System.err);
            return -1;
        }

        // parse document
        try {
            m_ABREDocument = m_ABREParser.parseInput( aszXMLin );
        } catch (Exception exp) {
        	setErr("error parsing XML document");
        	setErr( exp.getMessage() );
            exp.printStackTrace(System.err);
            return -1;
        }
		return 0;
	}
	// end-of method loadPayload()

	//=== START private methods ===>
	//=== START private methods ===>
	//=== START private methods ===>

	/**
	* get a list of node for a given element TAG 
	*/
	private NodeList getElementNodeList( String aszTagIn ){
		if(null == aszTagIn){
			return null;
		}
		String aszTempTagIn = aszTagIn.trim();
		if(aszTempTagIn.length() < 1){
			return null;
		}
        Element aRoot = getRootElement();
        if(null == aRoot){
			return null;
		}
    	NodeList alist = aRoot.getElementsByTagName( aszTempTagIn );
    	if(null == alist){
			return null;
		}
		int iLen = alist.getLength();
    	if(iLen < 1){
			return null;
		}
    	return alist;
	}
	// end-of method getElementNodeList()

	/**
	* Clean Up State-full variables 
	*/
	private void cleanupInternal(){
		m_ABREParser=null;
		m_ABREDocument=null;
	}
	// end-of method cleanupInternal()

	/**
	* get root element 
	*/
	private Element getRootElement(){
		if(null == m_ABREDocument){
			return null;
		}
		return m_ABREDocument.getDocumentElement();
	}
	// end-of method getRootElement()

	//=== END private methods ===>
	//=== END private methods ===>
	//=== END private methods ===>

	//=== START private Variables ===>
	//=== START private Variables ===>
	//=== START private Variables ===>

	//  Default parser name
    private static final String DEFAULT_PARSER_NAME = "com.abrecorp.opensource.xmlutils.ABREDOMParser";

    private static final boolean setValidation    = false; //defaults
    private static final boolean setNameSpaces    = true;
    private static final boolean setSchemaSupport = true;
    private static final boolean setSchemaFullSupport = false;
    private static final boolean setDeferredDOM   = true;

    // state-full variables used for XML documents
    private ABREDOMParserWrapper m_ABREParser=null;
	private Document m_ABREDocument=null;

}
