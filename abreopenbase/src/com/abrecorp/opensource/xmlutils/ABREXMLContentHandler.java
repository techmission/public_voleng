package com.abrecorp.opensource.xmlutils;

/**
* System:       ABRE Abstract XML Content Handler Object
* Description:  XML Parser Base Class
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       David Marcillo
* @version      1.0
*/

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.dataobj.ValuePair;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.reflect.*;

// Import your vendor's XMLReader implementation here
// import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
// Import your vendor's XMLReader implementation here
import org.apache.xerces.parsers.SAXParser;


public abstract class ABREXMLContentHandler extends ABREBase {

	/**
	* Constructor
	*/
	public ABREXMLContentHandler(){}


	/**
	* Set XML Parser Content Handler
	*/
	public int setXMLContentHandler( ContentHandler ContObj ){
        int iRetCode = allocXMLParserObj();
        if(0 != iRetCode) return iRetCode;
        m_XMLParserObj.setContentHandler(ContObj);
        return 0;
    }
    // end-of setXMLContentHandler()

	/**
	* parse XML
	*/
	public int parseXML( String xmlfile ){
        int iRetCode = allocXMLParserObj();
        if(0 != iRetCode) return iRetCode;
        try {
            m_XMLParserObj.parse(xmlfile);
            iRetCode=0;
        } catch ( SAXException ex ) {
			setErr("error parsing: " + xmlfile);
			setErr( ex.getMessage() );
            iRetCode=1;
        } catch ( IOException ex ) {
			setErr("error parsing: " + xmlfile);
			setErr( ex.getMessage() );
            iRetCode=1;
        }
        return iRetCode;
    }
    // end-of parseXML()

    /**
    * <p>
    * Provide reference to <code>Locator</code> which provides
    * information about where in a document callbacks occur.
    * </p>
    *
    * @param locator <code>Locator</code> object tied to callback
    * process
    */
    public void setDocumentLocator(Locator locator) {
        this.m_locatorObj = locator;
        MethodInit("setDocumentLocator");
        // info( "setDocumentLocator() called" );
        // callValidationDynamicMethodWithParm( "info", "setDocumentLocator() called" );
    }
    // end-of setDocumentLocator()

    /**
    * <p>
    * This indicates the start of a Document parse—this precedes
    * all callbacks in all SAX Handlers with the sole exception
    * of <code>{@link #setDocumentLocator}</code>.
    * </p>
    *
    * @throws <code>SAXException</code> when things go wrong
    */
    public void startDocument() throws SAXException {
        m_iStartProcess=1;
        MethodInit("startDocument");
        // info( "startDocument() called" );
        // callValidationDynamicMethodWithParm( "info", "startDocument() called" );
    }
    // end-of startDocument()

    /**
    * <p>
    * This indicates the end of a Document parse—this occurs after
    * all callbacks in all SAX Handlers.</code>.
    * </p>
    *
    * @throws <code>SAXException</code> when things go wrong
    */
    public void endDocument() throws SAXException {
        m_iStartProcess=0;
        MethodInit("endDocument");
        // info( "endDocument() called" );
        // callValidationDynamicMethodWithParm( "info", "endDocument() called " );
    }
    // end-of endDocument()

    /**
    * <p>
    * This indicates the beginning of an XML Namespace prefix
    * mapping. Although this typically occurs within the root element
    * of an XML document, it can occur at any point within the
    * document. Note that a prefix mapping on an element triggers
    * this callback <i>before</i> the callback for the actual element
    * itself (<code>{@link #startElement}</code>) occurs.
    * </p>
    *
    * @param prefix <code>String</code> prefix used for the namespace
    * being reported
    * @param uri <code>String</code> URI for the namespace
    * being reported
    * @throws <code>SAXException</code> when things go wrong
    */
    public void startPrefixMapping(String prefix, String uri) {
        String aszTemp = prefix;
        if(null == aszTemp) aszTemp="";
        if( aszTemp.equalsIgnoreCase( m_XMLPrefixValue ) ){
            m_iXMLPrefixFound=1;
        } else {
            String aszMsg = "Error * expect prefix \"" + m_XMLPrefixValue + "\" , but found \"" + prefix + "\" " ;
            setErr( aszMsg );
            m_iXMLPrefixFound=0;
            callValidationDynamicMethodWithParm( "error", aszMsg );
        }
    }
    // end-of startPrefixMapping()

    /**
    * <p>
    * This indicates the end of a prefix mapping, when the namespace
    * reported in a <code>{@link #startPrefixMapping}</code> callback
    * is no longer available.
    * </p>
    *
    * @param prefix <code>String</code> of namespace being reported
    * @throws <code>SAXException</code> when things go wrong
    */
    public void endPrefixMapping(String prefix) {
        String aszTemp = prefix;
        if(null == aszTemp) aszTemp="";
        if( aszTemp.equalsIgnoreCase( m_XMLPrefixValue ) ){
            m_iStartProcess=0;
        } else {
            String aszMsg = "endPrefixMapping:Error * expect prefix \"" + m_XMLPrefixValue + "\" , but found \"" + prefix + "\" " ;
            setErr( aszMsg );
            m_iStartProcess=0;
            callValidationDynamicMethodWithParm( "error", aszMsg );
        }
    }
    // end-of endPrefixMapping()

    /**
    * <p>
    * This indicates that a processing instruction (other than
    * the XML declaration) has been encountered.
    * </p>
    *
    * @param target <code>String</code> target of PI
    * @param data <code>String</code containing all data sent to the PI.
    * This typically looks like one or more attribute value
    * pairs.
    * @throws <code>SAXException</code> when things go wrong
    */
    public void processingInstruction(String target, String data) throws SAXException {
        // System.out.println("  PI: Target: \"" + target + "\" and Data: \"" + data + "\"" );
    }
    // end-of processingInstruction()


    /**
    * <p>
    * This reports the occurrence of an actual element. It includes
    * the element's attributes, with the exception of XML vocabulary
    * specific attributes, such as
    * <code>xmlns:[namespace prefix]</code> and
    * <code>xsi:schemaLocation</code>.
    * </p>
    *
    * @param namespaceURI <code>String</code> namespace URI this element
    * is associated with, or an empty
    * <code>String</code>
    * @param localName <code>String</code> name of element (with no
    * namespace prefix, if one is present)
    * @param rawName <code>String</code> XML 1.0 version of element name:
    * [namespace prefix]:[localName]
    * @param atts <code>Attributes</code> list for this element
    * @throws <code>SAXException</code> when things go wrong
    */
    public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
        if( ! IsRequiredTagsOK() ) return  ;
        addLocalNameToReceivedList( localName ) ;

        // is localName valid
        ValuePair aValObj = getValidLocalNameObj(localName);
        if(null == aValObj) return ;
        if( false == aValObj.getAttibuteFlag() ) return ;
        if( atts.getLength() < 1 ){
            return ;
        }

        String aszAttributeName=null , aszAttributeValue=null ; 
        ValuePair aEntryObj=null;
        for (int i=0; i < atts.getLength(); i++) {
            aszAttributeName = atts.getLocalName(i);
            if(null == aszAttributeName) continue;
            aEntryObj = getValidAttributeValue( aszAttributeName ) ;
            if(null == aEntryObj) continue;
            aszAttributeValue = atts.getValue(i);
            aEntryObj = getValidAttributeValue( aszAttributeValue ) ;
            if(null == aEntryObj) continue;
            addAttributeNameToReceivedList( aszAttributeValue ) ;
            aValObj.setAttributeIsActiveFalg(true);
        }
    }

    /**
    * <p>
    * Indicates the end of an element
    * (<code>&lt;/[element name]&gt;</code>) is reached. Note that
    * the parser does not distinguish between empty
    * elements and non-empty elements, so this occurs uniformly.
    * </p>
    *
    * @param namespaceURI <code>String</code> URI of namespace this
    * element is associated with
    * @param localName <code>String</code> name of element without prefix
    * @param rawName <code>String</code> name of element in XML 1.0 form
    * @throws <code>SAXException</code> when things go wrong
    */
    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        if( ! IsRequiredTagsOK() ) return  ;
        if( IsLocalNameReceivedListEmpty() ) return ;
        if( null == localName ) return ;
        String aszTemp = localName.trim() ;
        if(aszTemp.length() < 1) return;
        if( false == IsLocalNameReceivedLastItem( aszTemp ) ){
            String aszMsg = "error: localName expected \"" + getLastItemFromLocalNameReceivedList() + "\" , but received \"" + aszTemp + "\" " ;
			setErr( aszMsg );
            callValidationDynamicMethodWithParm( "error", aszMsg );
            return ;
        }
        String aszLastLocalName = getLastItemFromLocalNameReceivedList() ;
        // is localName valid
        ValuePair aValObj = getValidLocalNameObj(aszLastLocalName);
        if(null != aValObj){
            if( aValObj.getEndMethodInvocationFlag() ){
                String aszMethodName = aValObj.getMethodName() ;
                // String aszMsg = "endElement localName \"" + aszLastLocalName + "\" calling callValidationDynamicMethod() method \"" + aszMethodName + "\" " ;
                // System.out.println( aszMsg  );
                // callValidationDynamicMethodWithParm( "info", aszMsg );
                callValidationDynamicMethod( aszMethodName ) ;
            }
        }
        removeLocalNameFromRecevivedList( aszTemp ) ;
    }
    // end-of endElement()

    /**
    * <p>
    * This reports character data (within an element).
    * </p>
    *
    * @param ch <code>char[]</code> character array with character data
    * @param start <code>int</code> index in array where data starts.
    * @param end <code>int</code> index in array where data ends.
    * @throws <code>SAXException</code> when things go wrong
    */
    public void characters(char[] ch, int start, int end) throws SAXException {
        if( ! IsRequiredTagsOK() ) return  ;
        if( IsLocalNameReceivedListEmpty() ) return ;
        String aszLastLocalName = getLastItemFromLocalNameReceivedList() ;
        // is localName valid
        ValuePair aValObj = getValidLocalNameObj(aszLastLocalName);
        if(null == aValObj) return ;

        // save current data ?
        boolean bGetData = false ;
        if( aValObj.getMethodInvocationFlag() ) bGetData=true ;
        if( aValObj.getAttibuteFlag() ) bGetData=true ;
        if( bGetData == false ) return ;

        // do we have data ?
        String aszTemp = new String(ch, start, end);
        aszTemp = aszTemp.trim() ;
        if( aszTemp.length() < 1) return ;

        String aszMethodName=null;
        // Does This Element have dynamic method ?
        if( aValObj.getMethodInvocationFlag() ) {
            aszMethodName = aValObj.getMethodName() ;
            // String aszMsg = "characters: \"" + aszTemp + "\" under localName: " + aszLastLocalName + " method: " + aszMethodName ;
            // System.out.println( aszMsg );
            // callValidationDynamicMethodWithParm( "info", aszMsg );
            sendDataToDynamicMethod( aszMethodName , aszTemp ) ;
            return ;
        }

        // Does This Element have attribute active ?
        if( aValObj.getAttributeIsActiveFalg() ) {
            String aszAttributeName = getLastItemFromAttributeNameReceivedList();
            ValuePair aEntryObj = getValidAttributeValue( aszAttributeName ) ;
            if(null == aEntryObj) return ;
            if( false == aEntryObj.getMethodInvocationFlag() ) return ;
            aszMethodName = aEntryObj.getMethodName() ;
            // String aszMsg = "characters: \"" + aszTemp + "\" under localName: " + aszLastLocalName + " attribute: " + aszAttributeName + " method: " + aszMethodName ;
            // System.out.println( aszMsg );
            // callValidationDynamicMethodWithParm( "info", aszMsg );
            sendDataToDynamicMethod( aszMethodName , aszTemp ) ;
            return ;
        }
    }

    /**
    * <p>
    * This reports whitespace that can be ignored in the
    * originating document. This is typically invoked only when
    * validation is ocurring in the parsing process.
    * </p>
    *
    * @param ch <code>char[]</code> character array with character data
    * @param start <code>int</code> index in array where data starts.
    * @param end <code>int</code> index in array where data ends.
    * @throws <code>SAXException</code> when things go wrong
    */
    public void ignorableWhitespace(char[] ch, int start, int end) throws SAXException {
        if( ! IsRequiredTagsOK() ) return  ;
        String s = new String(ch, start, end);
        if( s.length() < 1) return ;
        String aszMsg = "ignorableWhitespace: [" + s + "]" ;
        // System.out.println( aszMsg );
        callValidationDynamicMethodWithParm( "info", aszMsg );
    }

    /**
    * <p>
    * This reports an entity that is skipped by the parser. This
    * should only occur for non-validating parsers, and then is still
    * implementation-dependent behavior.
    * </p>
    *
    * @param name <code>String</code> name of entity being skipped
    * @throws <code>SAXException</code> when things go wrong
    */
    public void skippedEntity(String name) throws SAXException {
        if( ! IsRequiredTagsOK() ) return  ;
        String aszMsg = "Skipping entity \"" + name + "\"" ;
        // System.out.println( aszMsg );
        callValidationDynamicMethodWithParm( "info", aszMsg );
    }

	/**
	* provide dynamic data object full name
	*/
    public void setDataClassFullName( String name ){
        if(null == name){
            m_DataClassFullName=null;
            return ;
        }
        m_DataClassFullName = name.trim() ;
        if(m_DataClassFullName.length() < 1) m_DataClassFullName=null;
    }
    // end-of setDataClassFullName()

	/**
	* provide access to dynamic data object
	*
    public void setDataClassObj( Class aClass , Object aObj ){
        m_DataClassCreationObj = aClass;
        m_InstanceOfDataClass = aObj;
    }
    // end-of setDataClassObj() */

	/**
	* create data class instance
	*/
    public int createDataClassInstance(){
        int iRetCode=0;
        if(null == m_DataClassFullName) return 1;
        if(null != m_InstanceOfDataClass) return 0;
        // make sure you have access to the class
        iRetCode=0;
        try {
            m_DataClassCreationObj = Class.forName(m_DataClassFullName);
            iRetCode=0;
        } catch (ClassNotFoundException exp) {
			setErr("error createDataClassInstance: " + m_DataClassFullName );
            // exp.printStackTrace() ; 
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return 1;
        // create the New Class instance
        iRetCode=0;
        try {
            m_InstanceOfDataClass = m_DataClassCreationObj.newInstance();
            iRetCode=0;
        } 
        catch (InstantiationException exp) {
			setErr("error createDataClassInstance: " + m_DataClassFullName );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error createDataClassInstance: " + m_DataClassFullName );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return 1;
        return 0 ;
    }
    // end-of createDataClassIntsnce()()

	/**
	* provide dynamic data validation object full name
	*/
    public void setDataValidationClassFullName( String name ){
        if(null == name){
            m_DataValicationClassFullName=null;
            return ;
        }
        m_DataValicationClassFullName = name.trim() ;
        if(m_DataValicationClassFullName.length() < 1) m_DataValicationClassFullName=null;
    }
    // end-of setDataValidationClassFullName()

	/**
	* create data validation class instance
	*/
    public int createDataValidationClassInstance(){
        int iRetCode=0;
        if(null == m_DataValicationClassFullName) return 1;
        if(null != m_InstanceOfDataValidationClass) return 0;
        // make sure you have access to the class
        iRetCode=0;
        try {
            m_DataValidationClassCreationObj = Class.forName(m_DataValicationClassFullName);
            iRetCode=0;
        } catch (ClassNotFoundException exp) {
			setErr("error createDataValidationClassInstance: " + m_DataValicationClassFullName );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return 1;
        // create the New Class instance
        iRetCode=0;
        try {
            m_InstanceOfDataValidationClass = m_DataValidationClassCreationObj.newInstance();
            iRetCode=0;
        } 
        catch (InstantiationException exp) {
			setErr("error createDataValidationClassInstance: " + m_DataValicationClassFullName);
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error createDataValidationClassInstance: " + m_DataValicationClassFullName );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return 1;
        return 0 ;
    }
    // end-of createDataValidationClassInstance()()

	/**
	* pass data object to validation class
	*/
    public void passDataObjToValidationObject(){
        if(null == m_InstanceOfDataClass) return ;
        passValidationObject( "setXmlDataObj" , m_InstanceOfDataClass ) ;
    }
    // end-of passDataObjToValidationObject()()

	/**
	* pass object to validation class
	*/
    public void passValidationObject( String methodname , Object obj){
        int iRetCode=0;
        if(null == methodname) return ;
        String aClassMethod = methodname.trim() ;
        if( aClassMethod.length() < 1) return ;
        // Class[] aMethodParams = null ;
        Class aMethodParams[] = { java.lang.Object.class };
        Object aMethodCallparamsObj[] = null;
        Method thisMethod=null;
        if(null == m_DataValidationClassCreationObj) return ;
        if(null == m_InstanceOfDataValidationClass) return ;
        // get access to the method
        iRetCode=0;
        try {
            // thisMethod = m_DataValidationClassCreationObj.getDeclaredMethod( aClassMethod , aMethodParams  ) ;
            thisMethod = m_DataValidationClassCreationObj.getMethod( aClassMethod , aMethodParams  ) ;
            iRetCode=0;
        } catch (NoSuchMethodException exp) {
			setErr("error passValidationObject: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return ;
        aMethodCallparamsObj = new Object[] { obj } ;
        // call the method
        iRetCode=0;
        try {
            thisMethod.invoke( m_InstanceOfDataValidationClass, aMethodCallparamsObj) ;
        } 
        catch (InvocationTargetException exp) {
			setErr("error passValidationObject: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error passValidationObject: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalArgumentException exp) {
			setErr("error passValidationObject: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        iRetCode=0;
    }
    // end-of passValidationObject()

	/**
	* call dynamic validation method without parms
	*/
    public void callValidationDynamicMethodWithParm( String methodname , String aszMessage ){
        if(null == methodname) return ;
        String aClassMethod = methodname.trim() ;
        if( aClassMethod.length() < 1) return ;
        Class aMethodParams[] = {String.class};
        Object aMethodCallparamsObj[] = null;
        Method thisMethod=null;
        int iRetCode=0;
        if(null == m_DataValidationClassCreationObj) return ;
        if(null == m_InstanceOfDataValidationClass) return ;
        // get access to the method
        iRetCode=0;
        try {
            // thisMethod = m_DataValidationClassCreationObj.getDeclaredMethod( aClassMethod , aMethodParams  ) ;
            thisMethod = m_DataValidationClassCreationObj.getMethod( aClassMethod , aMethodParams  ) ;
            iRetCode=0;
        } catch (NoSuchMethodException exp) {
			setErr("error callValidationDynamicMethodWithParm: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return ;
        // add the method parameter to call the method
        // add the method parameter to call the method
        aMethodCallparamsObj = new Object[] { new String( aszMessage ) } ;
        // call the method
        iRetCode=0;
        try {
            thisMethod.invoke( m_InstanceOfDataValidationClass, aMethodCallparamsObj) ;
        } 
        catch (InvocationTargetException exp) {
			setErr("error callValidationDynamicMethodWithParm: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error callValidationDynamicMethodWithParm: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalArgumentException exp) {
			setErr("error callValidationDynamicMethodWithParm: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        iRetCode=0;
    }
    // end-of callValidationDynamicMethodWithParm()

	/**
	* call dynamic validation method without parms
	*/
    public void callValidationDynamicMethod( String methodname ){
        if(null == methodname) return ;
        String aClassMethod = methodname.trim() ;
        if( aClassMethod.length() < 1) return ;
        Class aMethodParams[] = {};
        Object aMethodCallparamsObj[] = null;
        Method thisMethod=null;
        int iRetCode=0;
        if(null == m_DataValidationClassCreationObj) return ;
        if(null == m_InstanceOfDataValidationClass) return ;
        // get access to the method
        iRetCode=0;
        try {
            // thisMethod = m_DataValidationClassCreationObj.getDeclaredMethod( aClassMethod , aMethodParams  ) ;
            thisMethod = m_DataValidationClassCreationObj.getMethod( aClassMethod , aMethodParams  ) ;
            iRetCode=0;
        } catch (NoSuchMethodException exp) {
			setErr("error callValidationDynamicMethod: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return ;
        // add the method parameter to call the method
        aMethodCallparamsObj = new Object[] {} ;
        // call the method
        iRetCode=0;
        try {
            thisMethod.invoke( m_InstanceOfDataValidationClass, aMethodCallparamsObj) ;
        } 
        catch (InvocationTargetException exp) {
			setErr("error callValidationDynamicMethod: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error callValidationDynamicMethod: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalArgumentException exp) {
			setErr("error callValidationDynamicMethod: " + m_DataValidationClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        iRetCode=0;
    }
    // end-of callValidationDynamicMethod()

    /**
    * Clear all variables
    */
    public void cleanUp(){
        cleanUpInternal();
    }
    // end-of cleanUp()

    //=======================================> Protected Methods
    //=======================================> Protected Methods
    //=======================================> Protected Methods

	/**
	* add items to localName list
	*/
    protected void addToValidLocalNameList( ValuePair aEntry ){ 
        if( null == aEntry ) return ;
        String aszParm = aEntry.getParm() ;
        if( null == aszParm ) return ;
        if( aszParm.length() < 1 ) return ;
        ValuePair aLocalEntry = (ValuePair)aEntry.clone() ;
        if( null == aLocalEntry ) return ;
        if(null == m_ValidLocalNamesHashtable) {
            m_ValidLocalNamesHashtable = new Hashtable(50);
        }

        m_ValidLocalNamesHashtable.put(aszParm,aLocalEntry);
	}
    // end-of addToValidLocalNameList()()

	/**
	* add items to valid Attribute list
	*/
    protected void addToValidAttributesList( ValuePair aEntry ){ 
        if( null == aEntry ) return ;
        String aszParm = aEntry.getParm() ;
        if( null == aszParm ) return ;
        if( aszParm.length() < 1 ) return ;
        ValuePair aLocalEntry = (ValuePair)aEntry.clone() ;
        if( null == aLocalEntry ) return ;
        if(null == m_ValidAttributesHashtable) {
            m_ValidAttributesHashtable = new Hashtable(50);
        }
        m_ValidAttributesHashtable.put(aszParm,aLocalEntry);
	}
    // end-of addToValidAttributesList()()

	/**
	* XML Prefix Value
	*/
    protected void setXMLPrefixValue( String name ){
		if(null == name){
			m_XMLPrefixValue=null;
		} else {
			m_XMLPrefixValue = name.trim();
		}
    }
    // end-of setXMLPrefixValue()()

	/**
	* Valid Local Names Loead Flag
	*/
	protected void setValidLocalNamesDoneFlag(boolean number){
        m_bValidLocalNamesDoneFlag=number;
	}
	protected boolean getValidLocalNamesDoneFlag(){
		return m_bValidLocalNamesDoneFlag;
	}
	/**
	* Valid Attribues Load Flag
	*/
	protected void setValidAttributesDoneFlag(boolean number){
        m_bValidAttributesDoneFlag=number;
	}
	protected boolean getValidAttributesDoneFlag(){
		return m_bValidAttributesDoneFlag;
	}

    //=======================================> Private Methods
    //=======================================> Private Methods
    //=======================================> Private Methods

	/**
	* verify required tags
	*/
    private boolean IsRequiredTagsOK(){
        if(m_iStartProcess != 1) return false;
        if(m_iXMLPrefixFound != 1) return false;
        return true;
    }
    // end-of IsRequiredTagsOK()()

	/**
	* look up localName in valid local Name list
	*/
    private ValuePair getValidLocalNameObj(String LookupKey){
        ValuePair aEntry=null;
        if(null == m_ValidLocalNamesHashtable) return null;
		if(m_ValidLocalNamesHashtable.isEmpty()) return null;
        if(m_ValidLocalNamesHashtable.containsKey(LookupKey)) {
            aEntry = (ValuePair)m_ValidLocalNamesHashtable.get(LookupKey);
            return aEntry;
        }
        return null;
    }
    // end-of getValidLocalNameObj()

	/**
	* is local name received list empty ?
	*/
    private boolean IsLocalNameReceivedListEmpty(){
        if( null == m_LocalNameReceivedList) return true;
        if( m_LocalNameReceivedList.isEmpty() ) return true ;
        if( m_LocalNameReceivedList.size() < 1 ) return true ;
        return false ;
    }
    // end-of IsLocalNameReceivedListEmpty()()

	/**
	* remove item from localName received list
	*/
    private void removeLocalNameFromRecevivedList( String name ){
        if(null == name) return ;
        String aszReceivedName = name.trim() ;
        if( aszReceivedName.length() < 1 ) return ;
        if( null == m_LocalNameReceivedList) return ;
        if( m_LocalNameReceivedList.isEmpty() ) return ;
        if( m_LocalNameReceivedList.size() < 1 ) return ;
        for(int index=0; index < m_LocalNameReceivedList.size() ; index++){
            String aszTemp = (String) m_LocalNameReceivedList.elementAt(index);
            if(null == aszTemp) continue ;
            if ( aszReceivedName.equalsIgnoreCase( aszTemp ) ){
                m_LocalNameReceivedList.removeElementAt( index );
                m_AttributesReceivedList=null;
            }
        }
        return ;
    }
    // end-of removeLocalNameFromRecevivedList()()

	/**
	* get last item from received list
	*/
    private String getLastItemFromLocalNameReceivedList(){
        if( null == m_LocalNameReceivedList) return "" ;
        if( m_LocalNameReceivedList.isEmpty() ) return "" ;
        if( m_LocalNameReceivedList.size() < 1 ) return "" ;
        String aszLastItem = (String) m_LocalNameReceivedList.lastElement() ;
        if(null == aszLastItem) return "" ;
        return aszLastItem ;
    }
    // end-of getLastItemFromLocalNameReceivedList()()

	/**
	* Is localName the last added to received list ?
	*/
    private boolean IsLocalNameReceivedLastItem( String name ){
        if(null == name) return false ;
        String aszReceivedName = name.trim() ;
        if( aszReceivedName.length() < 1 ) return false ;
        if( null == m_LocalNameReceivedList) return false ;
        if( m_LocalNameReceivedList.isEmpty() ) return false ;
        if( m_LocalNameReceivedList.size() < 1 ) return false ;
        int iMax = m_LocalNameReceivedList.size() ;
        String aszLastItem = (String) m_LocalNameReceivedList.lastElement() ;
        if(null == aszLastItem) return false ;
        if( aszLastItem.equalsIgnoreCase( aszReceivedName ) ) return true ;
        return false ;
    }
    // end-of IsLocalNameReceivedLastItem()()

	/**
	* add local name to received list
	*/
    private void addLocalNameToReceivedList(String name){
        if(null == name) return ;
        String aszTemp = name.trim() ;
        if( aszTemp.length() < 1 ) return ;
        allocLocalNameReceiveList();
        m_LocalNameReceivedList.addElement( aszTemp );
    }
    // end-of addLocalNameToReceivedList()()

	/**
	* add attribute name to received list
	*/
    private void addAttributeNameToReceivedList(String name){
        if(null == name) return ;
        String aszTemp = name.trim() ;
        if( aszTemp.length() < 1 ) return ;
        allocAttributeNameReceiveList();
        m_AttributesReceivedList.addElement( aszTemp );
    }
    // end-of addAttributeNameToReceivedList()()

	/**
	* get last item from attribute received list
	*/
    private String getLastItemFromAttributeNameReceivedList(){
        if( null == m_AttributesReceivedList) return "" ;
        if( m_AttributesReceivedList.isEmpty() ) return "" ;
        if( m_AttributesReceivedList.size() < 1 ) return "" ;
        String aszLastItem = (String) m_AttributesReceivedList.lastElement() ;
        if(null == aszLastItem) return "" ;
        return aszLastItem ;
    }
    // end-of getLastItemFromAttributeNameReceivedList()()

	/**
	* get a valid attribute value
	*/
    private ValuePair getValidAttributeValue(String LookupKey){
        ValuePair aEntry=null;
        if(null == m_ValidAttributesHashtable) return null;
		if(m_ValidAttributesHashtable.isEmpty()) return null;
        if(m_ValidAttributesHashtable.containsKey(LookupKey)) {
            aEntry = (ValuePair)m_ValidAttributesHashtable.get(LookupKey);
            return aEntry;
        }
        return null;
    }
    // end-of getValidAttributeValue()

	/**
	* send data to dynamic method
	*/
    private void sendDataToDynamicMethod( String methodname , String methodvalue ){
        if(null == methodname) return ;
        if(null == methodvalue) return ;
        String aClassMethod = methodname.trim() ;
        if( aClassMethod.length() < 1) return ;
        Class aMethodParams[] = {String.class};
        Object aMethodCallparamsObj[] = null;
        Method thisMethod=null;
        int iRetCode=0;
        String aszMessage = methodvalue.trim() ;
        if( aszMessage.length() < 1) return ;
        if(null == m_DataClassCreationObj) return ;
        if(null == m_InstanceOfDataClass) return ;
        // get access to the method
        iRetCode=0;
        try {
            thisMethod = m_DataClassCreationObj.getDeclaredMethod( aClassMethod , aMethodParams  ) ;
            iRetCode=0;
        } catch (NoSuchMethodException exp) {
			setErr("error sendDataToDynamicMethod: " + m_DataClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        if(0 != iRetCode) return ;
        // add the method parameter to call the method
        aMethodCallparamsObj = new Object[] { new String( aszMessage ) } ;
        // call the method
        iRetCode=0;
        try {
            thisMethod.invoke( m_InstanceOfDataClass, aMethodCallparamsObj) ;
        } 
        catch (InvocationTargetException exp) {
			setErr("error sendDataToDynamicMethod: " + m_DataClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalAccessException exp) {
			setErr("error sendDataToDynamicMethod: " + m_DataClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        catch (IllegalArgumentException exp) {
			setErr("error sendDataToDynamicMethod: " + m_DataClassCreationObj.getName() );
			setErr( exp.getMessage() );
            iRetCode=1;
        } 
        aszMessage=null;
    }
    // end-of sendDataToDynamicMethod()

    /**
	* allocate attribute name received list
	*/
    private void allocAttributeNameReceiveList(){
        if(null == m_AttributesReceivedList){
            m_AttributesReceivedList = new Vector (10,2);
        }
    }
    // end-of allocLocalNameReceiveList()()

	/**
	* allocate local name received list
	*/
    private void allocLocalNameReceiveList(){
        if(null == m_LocalNameReceivedList){
            m_LocalNameReceivedList = new Vector (10,2);
        }
    }
    // end-of allocLocalNameReceiveList()()

    /**
    * allocate XML parser object
    */
    private int allocXMLParserObj(){
        int iRetCode=0;
        if(null != m_XMLParserObj) return 0;
        try {
            m_XMLParserObj = new SAXParser();
            iRetCode=0;
        } 
        catch ( NoClassDefFoundError exp ){ 
        	setErr("error creating SAXParser() ");
        	setErr( exp.getMessage() );
            iRetCode=1;
            m_XMLParserObj=null;
        }
        finally {
            if(null == m_XMLParserObj) iRetCode=1;
        }
        return iRetCode;
    }
    // end-of allocXMLParserObj()

    /**
    * Clear all variables
    */
    private void cleanUpInternal(){
        m_locatorObj=null;
        m_iStartProcess=0;
        m_iXMLPrefixFound=0;
        m_XMLPrefixValue=null;
        m_ValidLocalNamesHashtable=null;
        m_bValidLocalNamesDoneFlag=false;
        m_LocalNameReceivedList=null;
        m_AttributesReceivedList=null;
        m_ValidAttributesHashtable=null;
        m_bValidAttributesDoneFlag=false;
        m_InstanceOfDataClass=null;
        m_DataClassCreationObj=null;
        m_DataClassFullName=null;
        m_DataValidationClassCreationObj=null;
        m_InstanceOfDataValidationClass=null;
        m_DataValicationClassFullName=null;
        m_XMLParserObj=null;
    }
    // end-of cleanUpInternal()


    //=======================================> Private Variables
    //=======================================> Private Variables
    //=======================================> Private Variables

    private XMLReader m_XMLParserObj = null;

    // Hold onto the locator for location information
    private Locator m_locatorObj=null ;
    // processing status variables
    private int m_iStartProcess=0;
    // XML prefix variable found
    private int m_iXMLPrefixFound=0;
    // XML prefix variable
    private String m_XMLPrefixValue=null;

    // holds valid localNames
	private Hashtable m_ValidLocalNamesHashtable=null;
    private boolean m_bValidLocalNamesDoneFlag=false;
    // all localName received are saved
    private Vector m_LocalNameReceivedList=null;
    // all attributes received are saved
    private Vector m_AttributesReceivedList=null;
    // holds valid attribute names
	private Hashtable m_ValidAttributesHashtable=null;
    private boolean m_bValidAttributesDoneFlag=false;

    // data validation class objects
    private String m_DataValicationClassFullName=null;
    private Class m_DataValidationClassCreationObj=null;
    private Object m_InstanceOfDataValidationClass=null;

    // data class full name
    private String m_DataClassFullName=null;
    private Class m_DataClassCreationObj=null;
    private Object m_InstanceOfDataClass=null;


}
