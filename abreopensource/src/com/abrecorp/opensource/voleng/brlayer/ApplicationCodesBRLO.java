package com.abrecorp.opensource.voleng.brlayer;

/**
* Created 2006-05-05
* Business Rules Layer Object BRLO
* For Application Codes Processing
* @author David Marcillo
* ABRE Technology Corp.
*/

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
/*
import static org.apache.solr.handler.extraction.ExtractingParams.LITERALS_PREFIX;
import static org.apache.solr.handler.extraction.ExtractingParams.MAP_PREFIX;
import static org.apache.solr.handler.extraction.ExtractingParams.UNKNOWN_FIELD_PREFIX;
*/
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.individual.PersonObj;
import com.abrecorp.opensource.appcodes.ApplicationCodesObj;
import com.abrecorp.opensource.servlet.SolrItem;
import com.abrecorp.opensource.voleng.brlayer.searchOpportunitiesBLO;

public class ApplicationCodesBRLO extends BaseVolEngBRLO {

	/**
	* Constructor
	*/
	public ApplicationCodesBRLO() {}
	public ApplicationCodesBRLO(HttpServletRequest request) {
		super.setupApp( request );
	}

    //=== START Table country_stateprovince ===>
    //=== START Table country_stateprovince ===>
    //=== START Table country_stateprovince ===>

	/**
	* geta list of states
	* return not zero for fail, return 0 for success
	*/
	public int getStateInList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	MethodInit("getStateInList");
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchType( iTypeID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getStateInList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
    // end-of method getStateInList()

    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>
    //=== END Table country_stateprovince ===>

    //=== START Table country ===>
    //=== START Table country ===>
    //=== START Table country ===>

    /**
	* geta list of countries
	* return not zero for fail, return 0 for success
	*/
	public int getCountryList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	MethodInit("getCountryList");
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchType( iTypeID );
    	aSrchParmObj.setmaxSearchResultRows(500);
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getCountryList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
    // end-of method getCountryList()

    //=== END   Table country ===>
    //=== END   Table country ===>
    //=== END   Table country ===>

    //=== START Table application_codes ===>
    //=== START Table application_codes ===>
    //=== START Table application_codes ===>

    /**
	* get opportinuties list for an organization
	*/
	public int getAppCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getAppCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getAppCodeList()

	
    /**
	* get list for sorting by ID (getAppCodeList sorts ASC by keyword; this is by id)
	* 
	*/
	public int getAppCodeListID( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_TYPEID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getAppCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	// end-of method getAppCodeList()

    //=== END Table application_codes ===>
    //=== END Table application_codes ===>
    //=== END Table application_codes ===>


    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getZipCodes( ArrayList aList ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getZipCodes(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getZipCodeData( ArrayList aList, String aszZIP ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setZIP(aszZIP);
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getZipCodeData(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    //=== START Table um_term_data ===>
    //=== START Table um_term_data ===>
    //=== START Table um_term_data ===>

    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getTaxonomyCodeListByRelated( ArrayList aList, int iTypeID, int iRelatedID ){
		return getTaxonomyCodeListByRelated( aList, iTypeID, iRelatedID, 0 );
	}
	public int getTaxonomyCodeListByRelated( ArrayList aList, int iTypeID, int iRelatedID, int iOrder ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setRelatedTerm(iRelatedID);
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyCodeListByRelated(pConn, aList, aSrchParmObj, iOrder );
		if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getTaxonomyCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* get taxonomy lists for opps/orgs - um_term_data
	*/
	public int getTaxonomyCodeList( ArrayList aList, int iTypeID, int iSortID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	if(iSortID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	if(iSortID == AppCodeInfoDTO.GET_BY_SORTID){
        	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_SORTID );
    	}else{
        	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	}
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyCodeList(pConn, aList, aSrchParmObj);
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* get taxonomy (hierarchy: parent-level) lists - um_term_data
	*/
	public int getTaxonomyParentCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyParentCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get taxonomy (hierarchy: parent-level) lists - um_term_data
	*/
	public int getTaxonomyChildCodeList( ArrayList aList, int iTypeID, int parentTID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyChildCodeList(pConn, aList, aSrchParmObj, parentTID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

    /**
	* get taxonomy related terms lists - um_term_relation
	*/
	public int getTaxonomyRelatedCodeList( ArrayList aList, int iTypeID, int parentTID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyRelatedCodeList(pConn, aList, aSrchParmObj, parentTID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get taxonomy lists for opps/orgs by WEIGHT - set drupal weight - um_term_data
	*/
	public int getTaxonomyWeightCodeList( ArrayList aList, int iTypeID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_SORTID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyCodeList(pConn, aList, aSrchParmObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
	// end-of method getAppCodeList()

    /**
	* get taxonomy TID from name
	*/
	public int getTaxonomyTID( ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, String aszTaxonomy, int iVid ){
		
		int iRetCode=0;
		//ABREDBConnection pConn=null;
    	if(null == aszTaxonomy) return -1;
    	if(null == aHeadObj) return -1;
    	if(null == pConn) {
    		int itemp = -1;
    		return -1;
    	}

    	
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		//pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyTID(pConn, aHeadObj, aszTaxonomy, iVid );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get taxonomy Name from TID
	*/
	public int getTaxonomyName( ABREDBConnection pConn, AppCodeInfoDTO aHeadObj, int iTid, int iVid ){
		
		int iRetCode=0;
		//ABREDBConnection pConn=null;
    	if(0 == iTid) return -1;
    	if(null == aHeadObj) return -1;
    	if(null == pConn) {
    		int itemp = -1;
    		return -1;
    	}

    	
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		//pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyName(pConn, aHeadObj, iTid, iVid );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get taxonomy list from a comma delimited string - um_term_data
	*/
	public int getTaxonomyTermList( ArrayList aList, int iTypeID, String terms ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyTermList(pConn, aList, aSrchParmObj, terms );
    	if(null != pConn) pConn.free();
    	if (iRetCode == 999){
    		iRetCode=0;
    	}
    	return iRetCode;
	}
	
    /**
	* get taxonomy list from a comma delimited string - um_term_data
	*/
	public int getTaxonomyTermListTest( ArrayList aList, int iTypeID, String terms ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyTermList(pConn, aList, aSrchParmObj, terms );
    	if(null != pConn) pConn.free();
    	if (iRetCode == 999){
    		iRetCode=0;
    	}
    	return iRetCode;
	}
	
	
    /**
	* tag data for resume
	*/
	public int scanData( String aszData, ArrayList aList, int[] a_iDataArray, String[] a_aszDataArray ){
		int iRetCode=0;
    	if(null == aszData) return -1;
    	if(null == aList) return -1;

    	aszData = aszData.toUpperCase();
		int iDataLength = aszData.length();
		int i_aListSize = aList.size();
		int[] a_iDataInit = new int[i_aListSize];
		String[] a_aszDataInit = new String[i_aListSize];
		int index=0, iFinalSize=-1;
		
		for(int i=0; i<i_aListSize; i++){
			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aList.get(i);
			if(aAppCode == null) continue;
			String aszDisplay = aAppCode.getAPCDisplay();
			String aszDisplayIgnoreCase = aAppCode.getAPCDisplay().toUpperCase();
			
			int iSubType = aAppCode.getAPCIdSubType();
			
//			if(aszData.contains( aszDisplay )){
/*
			if(
					aszData.contains( aszDisplay ) || 
					aszData.contains( " " + aszDisplay + " " ) || 
					aszData.endsWith( aszDisplay ) || 
					aszData.endsWith( aszDisplay )
			){
*/
			
			List<String> tokens = new ArrayList<String>();
			tokens.add(aszDisplayIgnoreCase);
			int iStartPos = aszData.indexOf( " " + aszDisplayIgnoreCase + " " );
			if(iStartPos < 0)	iStartPos = aszData.indexOf( " " + aszDisplayIgnoreCase + "." );
			if(iStartPos < 0)	iStartPos = aszData.indexOf( " " + aszDisplayIgnoreCase + "," );
			if( iStartPos > -1 ){
				int iEndPos = aszDisplayIgnoreCase.length();
				if(iEndPos <= iDataLength){
					
					// NOT WORKING - need this pattern to look at only around whitespace or punctuation
					String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b"; 
					if(patternString.contains("ADD")){
						System.out.print("error");
					}

					String aszDataSubstring = aszData.substring(iStartPos);
					int iDataSubstringLength = aszDataSubstring.length();
					try{
						aszDataSubstring = aszDataSubstring.substring(0, iDataSubstringLength-(iDataSubstringLength-iEndPos)+1);  //how to get a substring of iEndPos lenght????
					}catch (IndexOutOfBoundsException ex){
						aszDataSubstring.substring(0, iDataSubstringLength);
					}
					Pattern pattern = Pattern.compile(patternString);
					Matcher matcher = pattern.matcher(aszDataSubstring);

					try{
						while (matcher.find()) {
							boolean b_alreadyIndexed=false;
							for(int indexArray=0; indexArray < a_iDataInit.length; indexArray++){
								if(a_iDataInit[indexArray]==0){
									iFinalSize=indexArray;
									break;
								}else if(a_iDataInit[indexArray] == iSubType){
									b_alreadyIndexed=true;
								}
							}
							if(b_alreadyIndexed == false){
								a_iDataInit[iFinalSize] = iSubType;
								a_aszDataInit[iFinalSize] = aszDisplay;
								iFinalSize++;
							}
						}
					}catch (ArrayIndexOutOfBoundsException e){
						System.out.println("line 496 ArrayIndexOutOfBounds caught "+e);
					}
				}
			}
		}
		if(iFinalSize>0){
			a_iData = new int[iFinalSize];
			a_aszData = new String[iFinalSize];
			for(int indexArray=0; indexArray < a_iDataInit.length; indexArray++){
				if(a_iDataInit[indexArray] == 0) break;
				try{
					a_iData[indexArray] = a_iDataInit[indexArray];
					a_aszData[indexArray] = a_aszDataInit[indexArray];
				}catch (ArrayIndexOutOfBoundsException e){
					System.out.println("line 510 ArrayIndexOutOfBounds caught "+e);
				}
	
			}
		}
    	return iRetCode;
	}
	
    /**
	* tagByKeyword
	*/
	public int tagByKeyword( String aszData, ArrayList aList, int iTermVID, Object[] a_objDataArray, int[] a_iDataArray, String[] a_aszDataArray, int[] a_iUSPServiceAreasArray, String[] a_aszServiceAreasStringArray ){
		int iRetCode=0;
		if(iTermVID < 1) return -1;
    	if(null == aszData) return -1;
    	if(null == aList) return -1;
    	
    	int iSize=0;
    	ArrayList aNewDataList = new ArrayList(2);
    	boolean b_existsInAList=false;
//    	getTaxonomyCodeList(aList, iTermVID); // sets the aList to be loaded with all possible category tids

    	// a_iData has all the keyword TIDs in it; a_aszData has all the keyword TERMS in it
    	// iterate through all the a_iData to get the associated category, if any; 
    	//    add this category's TID to a new int[], and term to a new String[]
    	int iSizeData = a_iDataArray.length;
    	if(iSizeData < 1)	return 0;
    	
    	int[] a_iNewDataInit = new int[iSizeData*20];
    	String[] a_aszNewDataInit = new String[iSizeData*20];
    	int[][] a_objNewDataInit = new int[iSizeData*20][]; // object array; array of int[]

    	int iSizeFinalArray = 0;
    	// iterate through each keyword a_iData tid that has been flagged for this item, and find ALL category TIDs related 
    	for(int i=0; i<iSizeData; i++){
    		int iRelatedTID=a_iDataArray[i];
    		getTaxonomyCodeListByRelated(aNewDataList, iTermVID, iRelatedTID, 1);
    		int i_aListSize = aNewDataList.size();
    		// iterate through this aNewDataList; if the TID ALREADY EXISTS in a_iNewDataInit, continue to the next
    		//    else, write the TID, look up the name - getAPCDisplay, and write the NAME as well, to the a_aszNewDataInit
    		for(int index=0; index<i_aListSize; index++){
    			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aNewDataList.get(index);
    			if(aAppCode == null) continue;
    			String aszDisplay = aAppCode.getAPCDisplay();
    			int iSubType = aAppCode.getAPCIdSubType();
    			
				boolean b_alreadyIndexed=false;
    			// if the TID ALREADY EXISTS in a_iNewDataInit, continue to the next
    			for(int tmp=0; tmp < a_iNewDataInit.length; tmp++){
        			// if the TID ALREADY EXISTS in a_iUSPServiceAreasArray, continue to the next
        			for(int tmp2=0; tmp2 < a_iUSPServiceAreasArray.length; tmp2++){
        				if(a_iUSPServiceAreasArray[tmp2] == iSubType){
        					b_alreadyIndexed = true;
        					break;
        				}
        			}
        			try{
	    				if(a_iNewDataInit[tmp] == 0){
	    					iSize = tmp;
	    					break;
	    				}
	    				if(a_iNewDataInit[tmp] == iSubType){
	    					b_alreadyIndexed = true;
	    					break;
	    				}
					}catch (ArrayIndexOutOfBoundsException e){
						System.out.println("line 572 ArrayIndexOutOfBounds caught tmp "+tmp);
					}

    			}
    			// if it's not already indexed AND it's in the possible list of ALL categories
    			if(b_alreadyIndexed == false){ 
        			// iterate through aList as well, to verify it exists in that vocabulary
    				for(int j=0; j<aList.size(); j++){
    					AppCodeInfoDTO aAppCode2 = (AppCodeInfoDTO)aList.get(j);
    					if(aAppCode2==null) continue;
    					if(iSubType == aAppCode2.getAPCIdSubType()){
    						b_existsInAList = true;
    						break;
    					}
    				}
    				if(b_existsInAList == true){ // add it to the list, and increase the size
    					try{
							a_objNewDataInit[iSize] = new int[] { iSubType, iRelatedTID };
		    				a_iNewDataInit[iSize] = iSubType;
							a_aszNewDataInit[iSize] = aszDisplay;
							iSize++;
							iSizeFinalArray++;
    					}catch (ArrayIndexOutOfBoundsException e){
    						System.out.println("line 594 ArrayIndexOutOfBounds caught iSize "+iSize);
    					}
    				}
    			}
    		}
    	}

    	int indexArray=0;
     	int iInitServiceAreasArraySize = a_iUSPServiceAreasArray.length;
		a_iData = new int[iSizeFinalArray+iInitServiceAreasArraySize];
		a_aszData = new String[iSizeFinalArray+iInitServiceAreasArraySize];
		a_objData = new int[iSizeFinalArray+iInitServiceAreasArraySize][];
		for(indexArray=0; indexArray < iInitServiceAreasArraySize; indexArray++){
			if(a_iUSPServiceAreasArray[indexArray] == 0) break;
			try{
				a_iData[indexArray] = a_iUSPServiceAreasArray[indexArray];
				a_aszData[indexArray] = a_aszServiceAreasStringArray[indexArray];
				a_objData[indexArray] = new int[] { a_iUSPServiceAreasArray[indexArray], 0 };
			}catch (ArrayIndexOutOfBoundsException e){
				System.out.println("line 612 ArrayIndexOutOfBounds caught indexArray "+indexArray);
			}
		}
		for(int i=0; i < a_iNewDataInit.length; i++){
			if(a_iNewDataInit[i] == 0) break;
			try{
				a_iData[indexArray] = a_iNewDataInit[i];
				a_aszData[indexArray] = a_aszNewDataInit[i];
				a_objData[indexArray] = a_objNewDataInit[i];
				indexArray++;
			}catch (ArrayIndexOutOfBoundsException e){
				System.out.println("line 622 ArrayIndexOutOfBounds caught indexArray "+indexArray);
			}
		}
		iRetCode=0;
    	return iRetCode;
	}
	
    /**
	* tagBySubCategory
	*/
	public int tagBySubCategory( String aszData, ArrayList aList, int iTermVID, int[] a_iDataArray, String[] a_aszDataArray ){
		int iRetCode=0;
		if(iTermVID < 1) return -1;
    	if(null == aszData) return -1;
    	if(null == aList) return -1;
    	
    	int iSize=0;
    	ArrayList aNewDataList = new ArrayList(2);
    	boolean b_existsInAList=false;
    	getTaxonomyCodeList(aList, iTermVID); // sets the aList to be loaded with all possible category tids

    	// a_iData has all the keyword TIDs in it; a_aszData has all the keyword TERMS in it
    	// iterate through all the a_iData to get the associated category, if any; 
    	//    add this category's TID to a new int[], and term to a new String[]
    	int iSizeData = a_iDataArray.length;
    	if(iSizeData < 1)	return 0;
    	
    	int[] a_iNewDataInit = new int[iSizeData*20];
    	String[] a_aszNewDataInit = new String[iSizeData*20];
    	
    	// this final array will contain the sub & super categories, so have it start out with all the data copied over for subcategories first
    	for(int j=0; j<a_iDataArray.length; j++){
    		try{
	    		a_iNewDataInit[j] = a_iDataArray[j];
	    		a_aszNewDataInit[j] = a_aszDataArray[j];
	    		iSize=j;
			}catch (ArrayIndexOutOfBoundsException e){
				System.out.println("line 660 ArrayIndexOutOfBounds caught j "+j);
			}
    	}

    	// iterate through each keyword a_iData tid that has been flagged for this item, and find ALL category TIDs related 
    	for(int i=0; i<iSizeData; i++){
    		getTaxonomyCodeListByRelated(aNewDataList, iTermVID, a_iDataArray[i], 1);
    		int i_aListSize = aNewDataList.size();
    		// iterate through this aNewDataList; if the TID ALREADY EXISTS in a_iNewDataInit, continue to the next
    		//    else, write the TID, look up the name - getAPCDisplay, and write the NAME as well, to the a_aszNewDataInit
    		for(int index=0; index<i_aListSize; index++){
    			AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aNewDataList.get(index);
    			if(aAppCode == null) continue;
    			String aszDisplay = aAppCode.getAPCDisplay();
    			int iSubType = aAppCode.getAPCIdSubType();
    			
				boolean b_alreadyIndexed=false;
    			// if the TID ALREADY EXISTS in a_iNewDataInit, continue to the next
    			for(int tmp=0; tmp < a_iNewDataInit.length; tmp++){
    				try{
	    				if(a_iNewDataInit[tmp] == 0){
	    					iSize = tmp;
	    					break;
	    				}
	    				if(a_iNewDataInit[tmp] == iSubType){
	    					b_alreadyIndexed = true;
	    					break;
	    				}
    				}catch (ArrayIndexOutOfBoundsException e){
    					System.out.println("line 688 ArrayIndexOutOfBounds caught tmp "+tmp);
    				}
    			}
    			// if it's not already indexed AND it's in the possible list of ALL categories
    			if(b_alreadyIndexed == false){ 
        			// iterate through aList as well, to verify it exists in that vocabulary
    				for(int j=0; j<aList.size(); j++){
    					AppCodeInfoDTO aAppCode2 = (AppCodeInfoDTO)aList.get(j);
    					if(aAppCode2==null) continue;
    					if(iSubType == aAppCode2.getAPCIdSubType()){
    						b_existsInAList = true;
    						break;
    					}
    				}
    				if(b_existsInAList == true){ // add it to the list, and increase the size
    					try{
		    				a_iNewDataInit[iSize] = iSubType;
							a_aszNewDataInit[iSize] = aszDisplay;
							iSize++;
	    				}catch (ArrayIndexOutOfBoundsException e){
	    					System.out.println("line 708 ArrayIndexOutOfBounds caught iSize "+iSize);
	    				}
    				}
    			}
    		}
    	}
		
		a_iData = new int[iSize+1];
		a_aszData = new String[iSize+1];
		int iNewDataInitLength = a_iNewDataInit.length;
		int iaszNewDataInitLength = a_aszNewDataInit.length;
		for(int indexArray=0; indexArray < iNewDataInitLength; indexArray++){
			if(a_iNewDataInit[indexArray] == 0) break;
			try{
				a_iData[indexArray] = a_iNewDataInit[indexArray];
				a_aszData[indexArray] = a_aszNewDataInit[indexArray];
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("line 723 TRIGGERED error "+ e + " was at indexArray count of "+indexArray);
				System.out.println("   size of new arrays - iSize+1 is "+iSize+1);
				System.out.println("   iNewDataInitLength is "+iNewDataInitLength+";  iaszNewDataInitLength is "+iaszNewDataInitLength);
			}
		}
    	return iRetCode;
	}
	
    /**
	* tag data for resume
	*/
	public int tagResumeData( PersonInfoDTO aIndivObj, int[] a_iDataArray, String[] a_aszDataArray ){
		int iRetCode=0;
    	if(null == aIndivObj) return -1;
    	
		ArrayList aList = new ArrayList(2);
		
		ArrayList aCategoriesList = new ArrayList(2);
		ArrayList aKeywordsList = new ArrayList(2);
    	getTaxonomyCodeList(aKeywordsList, iKeywordsResumeVID);
    	getTaxonomyCodeList(aCategoriesList, iCategoriesVID);

    	a_aszData = a_aszDataArray;
    	a_iData = a_iDataArray;
    	String[] aszServiceAreasStringArray = a_aszData;
    	int[] a_iUSPServiceAreasArray = a_iData;
		String aszResume = aIndivObj.getUSPVolResume();
    	
    	// 1. tag the resume with the keywords
    	iRetCode = scanData ( aszResume, aKeywordsList, a_iData, a_aszData);
    	
    	// 2. iterate through the keywords and tag the resume with the categories
    	iRetCode = tagByKeyword ( aszResume, aCategoriesList, iCategoriesVID, a_objData, a_iData, a_aszData, a_iUSPServiceAreasArray, aszServiceAreasStringArray);
    	
    	// 3. iterate through the categories and tag the resume with all the supercategories, as well
//    	iRetCode = tagBySubCategory ( aszResume, aCategoriesList, iCategoriesVID, a_iData, a_aszData);
    	
    	// a_iData holds all the TIDs that need to be written to the db; 
    	if(a_iData != null)	{
    		if(a_iData.length>0)	aIndivObj.setUSPServiceAreasArray(a_iData);
    	}
    	if(a_objData != null)	aIndivObj.setUSPAutoTaggingArray(a_objData);
    	// a_aszData holds all the terms themselves that need to be added to solr index for this user/resume
    	if(a_aszData != null)	{
    		if(a_aszData.length>0)	aIndivObj.setUSPServiceAreasStringArray(a_aszData);
    	}
    	
    	return iRetCode;
	}
	
	
    /**
	* track download of file
	*/
	public int trackFileDownload( PersonInfoDTO aCurrentUserObj, PersonInfoDTO aContactPersonObj ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aCurrentUserObj) return -1;
    	if(null == aContactPersonObj) return -1;
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.trackFileDownload(pConn, aCurrentUserObj, aContactPersonObj );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}

	
	/**
	* get pathauto information for URL alias generation
	*/
	public String getPathAutoURLAliasInfo( AppCodeInfoDTO aHeadObj, String aszURLAlias ){
		int iRetCode=0;
		String aszPuncRemove = "s:1:\"0\"";
		String aszPuncSeparator = "s:1:\"1\"";
		String aszPuncNothing = "s:1:\"2\"";
		
		ABREDBConnection pConn=null;
		
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getPathAutoURLAliasInfo(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	
    	
    	String aszSeparator = aHeadObj.getPathAutoSeparator();
    	int iFirstColon = aszSeparator.indexOf(":", 0);
    	int iSecondColon = aszSeparator.indexOf(":", iFirstColon+1);
    	
    	int iLength= aszSeparator.length();
    	aszSeparator=aszSeparator.substring(iSecondColon+1, iLength-2);
    	aHeadObj.setPathAutoSeparator(aszSeparator);
    	
    	// cycle through all the special word cases - from um_variable where name like 'pathauto_ignore_words'
    	// 		--> s:180:"a,an, as,.........."
    	//		- also account for spaces and commas in the string of 180 chars; remove spaces. then comma delimit
    	String aszIgnoreWords = aHeadObj.getPathAutoIgnoreWords();//"s:180:\"a,an,as,at,before,but,by,for,from,is,in,into,like,of,off,on,onto,per,since,than,the,this,that,to,up,via,with, it, you, that, he, was, for, are, i, his, they, be, one, have, or, had\"";
    	iFirstColon = aszIgnoreWords.indexOf(":", 0);
    	iSecondColon = aszIgnoreWords.indexOf(":", iFirstColon+1);
    	String aszWordsCharLength = aszIgnoreWords.substring(iFirstColon+1 , iSecondColon);
    	int iWordsCharLength = Integer.parseInt(aszWordsCharLength);
    	int iWordsLength = 0;
    	for(int i=1; i<iWordsCharLength; i++){
            final char c = aszIgnoreWords.charAt(i);
            if (c == ',') {
            	iWordsLength++;
            }
    	}
    	if(iWordsLength>0)iWordsLength++;
    	int iStart=iSecondColon+2, iEnd=aszIgnoreWords.indexOf(",");
    	String tmpString = "";
    	for(int i=0; i<=iWordsLength; i++){
    		tmpString = aszIgnoreWords.substring(iStart,iEnd);
    		tmpString = tmpString.replaceAll(" ","");
    		tmpString = tmpString.replaceAll("\"","");
    		
        	aszURLAlias = aszURLAlias.replaceAll(tmpString,"-");
       		iStart = iEnd+1;
           	if(i == (iWordsLength - 1)){
        		iEnd = aszIgnoreWords.length()-1;
        	}else if(i < iWordsLength){
        		iEnd = aszIgnoreWords.indexOf(",",iStart);
        	}
    	}
    	
    	if(aHeadObj.getPathAutoPuncAmp().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("&",""); //
    	}else if(aHeadObj.getPathAutoPuncAmp().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("&",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncAstrsk().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("*",""); //
    	}else if(aHeadObj.getPathAutoPuncAstrsk().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("*",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncAt().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("@",""); //
    	}else if(aHeadObj.getPathAutoPuncAt().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("@",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncBckTck().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("`",""); //
    	}else if(aHeadObj.getPathAutoPuncBckTck().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("`",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncBckSlsh().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\",""); //
    	}else if(aHeadObj.getPathAutoPuncBckSlsh().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncCaret().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("^",""); //
    	}else if(aHeadObj.getPathAutoPuncCaret().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("^",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncColon().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(":",""); //
    	}else if(aHeadObj.getPathAutoPuncColon().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(":",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncComma().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(",",""); //
    	}else if(aHeadObj.getPathAutoPuncComma().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(",",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncDolrSign().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("$",""); //
    	}else if(aHeadObj.getPathAutoPuncDolrSign().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("$",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncDblQuotes().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\"",""); //
    	}else if(aHeadObj.getPathAutoPuncDblQuotes().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\"",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncEqual().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("=",""); //
    	}else if(aHeadObj.getPathAutoPuncEqual().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("=",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncExclam().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("!",""); //
    	}else if(aHeadObj.getPathAutoPuncExclam().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("!",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncGrtr().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(">",""); //
    	}else if(aHeadObj.getPathAutoPuncGrtr().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(">",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncHash().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("#",""); //
    	}else if(aHeadObj.getPathAutoPuncHash().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("#",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncHyphen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("-",""); //
    	}else if(aHeadObj.getPathAutoPuncHyphen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("-",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftCrly().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("{",""); //
    	}else if(aHeadObj.getPathAutoPuncLftCrly().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("{",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftParen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("(",""); //
    	}else if(aHeadObj.getPathAutoPuncLftParen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("(",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLftSq().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("[",""); //
    	}else if(aHeadObj.getPathAutoPuncLftSq().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("[",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncLess().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("<",""); //
    	}else if(aHeadObj.getPathAutoPuncLess().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("<",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPerc().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("%",""); //
    	}else if(aHeadObj.getPathAutoPuncPerc().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("%",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPeriod().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("\\.",""); //
    	}else if(aHeadObj.getPathAutoPuncPeriod().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("\\.",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPipe().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("[|]",""); //
    	}else if(aHeadObj.getPathAutoPuncPipe().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("[|]",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncPlus().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("+",""); //
    	}else if(aHeadObj.getPathAutoPuncPlus().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("+",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncQuest().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("?",""); //
    	}else if(aHeadObj.getPathAutoPuncQuest().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("?",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncSingleQut().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("'",""); //
    	}else if(aHeadObj.getPathAutoPuncSingleQut().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("'",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtCurly().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("}",""); //
    	}else if(aHeadObj.getPathAutoPuncRtCurly().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("}",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtParen().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(")",""); //
    	}else if(aHeadObj.getPathAutoPuncRtParen().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(")",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncRtSq().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("]",""); //
    	}else if(aHeadObj.getPathAutoPuncRtSq().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("]",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncSemiColon().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll(";",""); //
    	}else if(aHeadObj.getPathAutoPuncSemiColon().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll(";",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncTilde().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("~",""); //
    	}else if(aHeadObj.getPathAutoPuncTilde().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("~",aHeadObj.getPathAutoSeparator()); //
    	}

    	if(aHeadObj.getPathAutoPuncUnderScore().equalsIgnoreCase(aszPuncRemove)){
        	aszURLAlias = aszURLAlias.replaceAll("_",""); //
    	}else if(aHeadObj.getPathAutoPuncUnderScore().equalsIgnoreCase(aszPuncSeparator)){
        	aszURLAlias = aszURLAlias.replaceAll("_",aHeadObj.getPathAutoSeparator()); //
    	}

    	aszURLAlias = aszURLAlias.replaceAll(" ",""); //space
    	aszURLAlias = aszURLAlias.replaceAll("\\W","-"); //(look up regex on wikipedia)	\W 	[^\w] 	non-word character
    	aszURLAlias = aszURLAlias.replaceAll("^-",""); //leading dash
    	aszURLAlias = aszURLAlias.replaceAll("-$",""); //trailing dash
    	while(aszURLAlias.contains("--")== true ){
        	aszURLAlias = aszURLAlias.replaceAll("--","-"); //double dashes
    	}

    	
    	
    	
    	
    	return aszURLAlias;
	}
	
    /**
	* get taxonomy list from a comma delimited string - um_term_data
	*/
	public int getTaxonomyTIDListFromNames( ArrayList aList, int iTypeID, String terms, int vid ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	if(iTypeID < 1) return -1;
    	SearchParms aSrchParmObj = new SearchParms();
    	aSrchParmObj.setSearchOrder( iTypeID );
    	aSrchParmObj.setPersonNumber( iTypeID );
    	aSrchParmObj.setSearchType( AppCodeInfoDTO.GET_BY_DISPLAYID );
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getTaxonomyTIDListFromNames(pConn, aList, aSrchParmObj, terms, vid );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
	
    /**
	* get list of city vision intern sites
	*/
	public int getCVInternOrgSitesList( ArrayList aList ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == aList) return -1;
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getCVInternOrgSitesList(pConn, aList );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get a users UID given their Facebook User ID
	*/
	public int getUIDFromFacebookUID( String facebookUID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == facebookUID) return -1;
    	if(facebookUID.equalsIgnoreCase("")) return -1;
		
    	
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getUIDFromFacebookUID(pConn, facebookUID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	
    /**
	* get a users Personality Type given their Facebook User ID
	*/
	public int getPersonalityFromFacebookUID( String facebookUID ){
		int iRetCode=0;
		ABREDBConnection pConn=null;
    	if(null == facebookUID) return -1;
    	if(facebookUID.equalsIgnoreCase("")) return -1;
		
    	
    	ApplicationCodesObj aApplicationCodesObj = new ApplicationCodesObj();
		pConn = getDBConn();
		iRetCode = aApplicationCodesObj.getPersonalityFromFacebookUID(pConn, facebookUID );
    	if(null != pConn) pConn.free();
    	return iRetCode;
	}
	

    /**
	* getUsernameByUID
	*/
	public String getUsernameByUID( int iUID ){
		String aszUsername="";
		ABREDBConnection pConn=null;
    	if(1 > iUID) return "";
    	PersonInfoDTO aHeadObj = new PersonInfoDTO();
    	aHeadObj.setUserUID(iUID);
    	PersonObj aPersonObj = new PersonObj();
		pConn = getDBConn();
		aszUsername = aPersonObj.getUsernameByUID(pConn, aHeadObj );
    	if(null != pConn) pConn.free();
    	return aszUsername;
	}


	/**
	* cleanInput
	*/
    public String cleanInput( String dirtyInput, int iType ){
    	int iRetCode=0;
    	String cleanHTML = "";
    	if(dirtyInput==null) return cleanHTML;
    	dirtyInput = dirtyInput.replaceAll("Â‘", "'");
    	dirtyInput = dirtyInput.replaceAll("", "'");
    	dirtyInput = dirtyInput.replaceAll("", "'");
    	dirtyInput = dirtyInput.replaceAll("Â–", "");
    	dirtyInput = dirtyInput.replaceAll("&amp;", "&");
    	//Policy policy = null;
    	try{
    		policy=getPolicy();
    		if(policy==null){
    			if(iType==LOOSELY_CLEANED)	return dirtyInput;
    			else return cleanHTML;
    		}
//    		System.out.println("**  line 723 - about to set AntiSamy ************");
        	AntiSamy as = new AntiSamy();
        	CleanResults cr = as.scan(dirtyInput, policy);
        	cleanHTML=cr.getCleanHTML(); 
//    		System.out.println("**  line 727 - CleanedResults ************");
    	}catch(NullPointerException ex){
    		System.out.println("NullPointerException line 729");
    		iRetCode=-1;
    	}catch(Exception e){
    		iRetCode=-1;
    	}
    	return cleanHTML;
    }

    public Policy getPolicy(){
    	int iRetCode=0;
    	if(policy!=null)	return policy;
    	if(url==null){
        	try{
        		System.out.println("*********** GETTING A NEW URL line 738 ************");
        		url = new URL("http://www.christianvolunteering.org/jsprsscron/antisamy.xml");
//        		System.out.println("**  line 742 - url is set ************");
            	if(policy==null){
//            		System.out.println("**  line 744 - policy was null ************");
            		policy=Policy.getInstance(url);
//            		System.out.println("**  line 746 - policy is set ************");
            	}
        	}catch(NullPointerException ex){
        		System.out.println("NullPointerException line 66");
        	}catch(PolicyException pe){
        		System.out.println("PolicyException line 66");
        	}catch(MalformedURLException urlex){
        		System.out.println("MalformedURLException line 66");
        	}    	
        }
    	return policy;
    }
    public URL url=null;
    public Policy policy=null;
    
	  /**
	   * Method to index all types of files into Solr. 
	   * @param fileName
	   * @param solrId
	   * @throws IOException
	   * @throws SolrServerException
	   */
	  public void indexFilesSolrAltCell(String fileName, String solrId, PersonInfoDTO aIndivObj, HttpServletRequest httpServletRequest)  
			  throws IOException, SolrServerException {
			//SolrServer
			if(serverAlt==null)	serverAlt = getServerAlt(httpServletRequest);
		    
		    // update to have all the service area content
		    String[] a_aszServiceAreas = aIndivObj.getUSPServiceAreasStringArray();
			
		    QueryResponse rsp = null;
System.out.println("line 1194:  serverAlt.query(new SolrQuery(\"id:\" + " + solrId + " + \"\")");		
		    try{
		    	rsp = serverAlt.query(new SolrQuery("id:" + solrId + ""));
		    }catch(Exception e){
System.out.println("Error "+e);		    	
		    }
		    ContentStreamUpdateRequest up  = new ContentStreamUpdateRequest("/extract/tika");
		    up.addFile(new File(fileName));
		    ModifiableSolrParams mp = new ModifiableSolrParams();
		    for(String value : a_aszServiceAreas){
		        mp.add("literal." + "service_areas", value);
		    }
		    mp.add("id", solrId);
		    mp.add("title", fileName);
		    mp.add("literal." + "content_type", "resume");
		    mp.add("literal." + "id", solrId);
		    mp.add("literal." + "location", fileName); // this field doesn't exists in schema.xml, it'll be created as attr_location
		    mp.add("uprefix", "attr_");
		    mp.add("fmap." + "content", "attr_content"); // this holds the actual data of the file; need it to map to keywords somehow??
		    
		    up.setParams(mp);
		    up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
		    String t="";
System.out.println("line 1217");		    	
		    try{
				NamedList<Object> request = serverAlt.request(up);
			    try{
			    	rsp = serverAlt.query(new SolrQuery("id:" + solrId + ""));
			    }catch(Exception e){
System.out.println("1222 Error "+e);		    	
			    }
			    List<SolrItem> beans = null;
				  try{
					  beans = rsp.getBeans(SolrItem.class);
					    if(beans == null) return;
					    
			    	    Iterator<SolrItem> itr_aList = beans.iterator();
			       	    if(itr_aList.hasNext()){
			       	    	SolrItem item = itr_aList.next();
			       			String aszResumeContent = item.getAttrContent();
			       	    }
			       	    return;
				  }catch(NullPointerException ex){
						System.out.println("null pointer error on getBeans");
						ex.printStackTrace();
				  }catch(Exception e){
						System.out.println("Error in rsp.getBeans call");
						e.printStackTrace();
				  }			  
		    }catch(Exception ex){
				  System.out.println("error running stream update request: "+ex.toString());
		    }
	  }
	  public void indexFilesSolrCell(String fileName, String solrId, PersonInfoDTO aIndivObj, HttpServletRequest httpServletRequest)  
			  throws IOException, SolrServerException {
		//SolrServer
		if(server==null)	server = getServer(httpServletRequest);
	    
	    // update to have all the service area content
	    String[] a_aszServiceAreas = aIndivObj.getUSPServiceAreasStringArray();
System.out.println("line 1244:  server.query(new SolrQuery(\"id:\" + " + solrId + " + \"\")");		
		// am I able to first check if a file already exists with this solr ID; if so, delete it and this is the replacement
		// It's not necessary, apparently, to check and delete; it just updates; might want to check and delete though, b/c not sure if old data still exists
	    QueryResponse rsp = null;
	    try{
	    	rsp = server.query(new SolrQuery("id:" + solrId + ""));
	    }catch(Exception e){
	    	System.out.println("1251 Error: "+e);
	    }

//	    ContentStreamUpdateRequest up  = new ContentStreamUpdateRequest("/update/extract");
	    ContentStreamUpdateRequest up  = new ContentStreamUpdateRequest("/extract/tika");
	    up.addFile(new File(fileName));
	    ModifiableSolrParams mp = new ModifiableSolrParams();
	    for(String value : a_aszServiceAreas){
	        mp.add("literal." + "service_areas", value);
	    }
	    mp.add("id", solrId);
	    mp.add("title", fileName);
	    mp.add("literal." + "content_type", "resume");
	    mp.add("literal." + "id", solrId);
	    mp.add("literal." + "location", fileName); // this field doesn't exists in schema.xml, it'll be created as attr_location
	    mp.add("uprefix", "attr_");
	    mp.add("fmap." + "content", "attr_content"); // this holds the actual data of the file; need it to map to keywords somehow??
	    
	    up.setParams(mp);
	    up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
	    String t="";
System.out.println("1272 before try block");		
	    try{
			NamedList<Object> request = server.request(up);
System.out.println("1275 before try block");		
		    try{
				rsp = server.query(new SolrQuery("id:" + solrId + ""));
System.out.println("1277 before try block");		
		    }catch(Exception e){
		    	System.out.println("1279 Error: " + e);
		    }
		    List<SolrItem> beans = null;
			  try{
				  beans = rsp.getBeans(SolrItem.class);
				    if(beans == null) return;
				    
		    	    Iterator<SolrItem> itr_aList = beans.iterator();
		       	    if(itr_aList.hasNext()){
		       	    	SolrItem item = itr_aList.next();
		       			String aszResumeContent = item.getAttrContent();
		       			aIndivObj.setUSPVolResume(aszResumeContent);
		       	    }
		       	    return;
			  }catch(NullPointerException ex){
					System.out.println("null pointer error on getBeans");
					ex.printStackTrace();
			  }catch(Exception e){
					System.out.println("Error in rsp.getBeans call");
					e.printStackTrace();
			  }			  
	    }catch(Exception ex){
			  System.out.println("error running stream update request: "+ex.toString());
	    }
	  }
	  /**
	   * Remove the resume from the Solr Index && from the SolrAlt index
	   * @param solrId
	   * @param aIndivObj
	   * @param httpServletRequest
	   * @throws IOException
	   * @throws SolrServerException
	   */
	  public int removeFileFromSolrIndex(String solrId, PersonInfoDTO aIndivObj, HttpServletRequest httpServletRequest)  
			  throws IOException, SolrServerException {
		int iRetCode=0;
		//SolrServer
		if(server==null)	server = getServer(httpServletRequest);
		if(serverAlt==null)	serverAlt = getServerAlt(httpServletRequest);
	    
	    try{
			server.deleteById(solrId);
			server.commit();
			
			if( ! httpServletRequest.getHeader("host").contains( ":7001" )	){
				serverAlt.deleteById(solrId);
				serverAlt.commit();
			}
			
   			aIndivObj.setUSPVolResume("");
   			aIndivObj.setUSPResumeFilePath("");
	    }catch(Exception ex){
			  System.out.println("error running delete from index request: "+ex.toString());
			  iRetCode=-1;
	    }
	    return iRetCode;
	  }
	
	/**
     * CommonsHttpSolrServer is thread-safe and if you are using the following constructor,
     * you *MUST* re-use the same instance for all requests.  If instances are created on
     * the fly, it can cause a connection leak. The recommended practice is to keep a
     * static instance of CommonsHttpSolrServer per solr server url and share it for all requests.
     * See https://issues.apache.org/jira/browse/SOLR-861 for more details
	 */
		private CommonsHttpSolrServer getServer( HttpServletRequest httpServletRequest){
			if(server!=null){
System.out.println("server is already set for SOLR          httpServletRequest.getHeader(\"host\"): " + httpServletRequest.getHeader("host"));			
				return server;
			}
			String aszSolrUser = this.getSitePropertyValue(ABREAppServerDef.SOLR_USERNAME);
			String aszSolrPassword = this.getSitePropertyValue(ABREAppServerDef.SOLR_PASSWORD);
			String aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_PRIMARY_HOST);
			String aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_PRIMARY_URL);
			String mainDB=aszDrupalDB;
System.out.println("1365 httpServletRequest.getHeader(\"host\"): " + httpServletRequest.getHeader("host"));			
			if ( 	
					httpServletRequest.getHeader("host").contains(":7001")	||	
					httpServletRequest.getHeader("host").contains("staging-")	
			) {
				aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_STAGING_URL);
				aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_STAGING_HOST);
			}
System.out.println("1373 aszSolrURL: " + aszSolrURL + ";  aszSolrHost: " + aszSolrHost);			
			try{
				server = new CommonsHttpSolrServer( aszSolrURL );
			}catch(MalformedURLException e){
				System.out.print("MalformedURLException: ");
				e.printStackTrace();
			}
			server.getHttpClient().getParams().setAuthenticationPreemptive(true);
			Credentials defaultcreds = new UsernamePasswordCredentials(aszSolrUser, aszSolrPassword);
			AuthScope authScope = new AuthScope(aszSolrHost,8080, AuthScope.ANY_REALM);
			server.getHttpClient().getState().setCredentials(authScope, defaultcreds);

			return server;
		}
		private CommonsHttpSolrServer getServerAlt( HttpServletRequest httpServletRequest){
			if(serverAlt!=null){
				return serverAlt;
			}
			String aszSolrUser = this.getSitePropertyValue(ABREAppServerDef.SOLR_USERNAME);
			String aszSolrPassword = this.getSitePropertyValue(ABREAppServerDef.SOLR_PASSWORD);
			String aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_PRIMARY_HOST);
			String aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_ALT_PRIMARY_URL);
			String mainDB=aszDrupalDB;
System.out.println("1396 httpServletRequest.getHeader(\"host\"): " + httpServletRequest.getHeader("host"));			
			if ( 	
					httpServletRequest.getHeader("host").contains(":7001")	||	
					httpServletRequest.getHeader("host").contains("staging-")	
			) {
				aszSolrURL = this.getSitePropertyValue(ABREAppServerDef.SOLR_ALT_STAGING_URL);
				aszSolrHost = this.getSitePropertyValue(ABREAppServerDef.SOLR_STAGING_HOST);
			}
System.out.println("1404 aszSolrURL: " + aszSolrURL + ";  aszSolrHost: " + aszSolrHost);			
			try{
				serverAlt = new CommonsHttpSolrServer( aszSolrURL );
			}catch(MalformedURLException e){
				System.out.print("MalformedURLException: ");
				e.printStackTrace();
			}
			serverAlt.getHttpClient().getParams().setAuthenticationPreemptive(true);
			Credentials defaultcreds = new UsernamePasswordCredentials(aszSolrUser, aszSolrPassword);
			AuthScope authScope = new AuthScope(aszSolrHost,8080, AuthScope.ANY_REALM);
			serverAlt.getHttpClient().getState().setCredentials(authScope, defaultcreds);

			return serverAlt;
		}
	
	private CommonsHttpSolrServer server = null;
	private CommonsHttpSolrServer serverAlt = null;
    
    private String[] a_aszData;
    private int[] a_iData;
    private int[][] a_objData;
	private static final String aszDrupalDB = "um_";
	private static final int iByScanning=1, iByKeyword=2, iByCategory=3;
	private static final int iCategoriesVID = 32, iKeywordsVID = 350, iKeywordsResumeVID = 353;
    final static public int LOOSELY_CLEANED = 100;
    final static public int TIGHTLY_CLEANED = 200;

}
