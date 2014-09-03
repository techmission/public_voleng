package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Database Utility Related Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/

import java.util.ArrayList;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// data transfer objects
import com.abrecorp.opensource.dataobj.*;
import com.abrecorp.opensource.servlet.DBUtilServlet;

public class DBUtilActions extends DispatchAction {

	/**
	* Constructor 
	*/
	public DBUtilActions() {}

	/**
	* show execute SQL statement
	*/
    public ActionForward showSQLExec(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	return actionMapping.findForward( "showrunsql" );
    }
    // end-of method showSQLExec()

    /**
    * process execute sql statement
    */
    public ActionForward processSQLExec(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	DBUtilInfo aDBUtilInfoObj = new  DBUtilInfo();
        ArrayList aList = new ArrayList();
        // get Form Data
     	iRetCode = getDBUtilSQLExecData101FromForm( oFrm, aDBUtilInfoObj );
        allocatedDBUtilServlet( httpServletRequest );
        iRetCode = m_DBUtilServletObj.execSQLStatement( aDBUtilInfoObj, aList );
        if(iRetCode != 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", m_DBUtilServletObj.getAllMessages() );
        	return actionMapping.findForward( "showrunsql" );
        }
        httpServletRequest.setAttribute( "resultlist", aList );
        // put Form Data
        iRetCode = putDBUtilSQLExecDataIntoForm(oFrm, aDBUtilInfoObj);
    	return actionMapping.findForward( "showsqlresult" );
    }
    // end-of method processSQLExec()


	/**
	* show generate java classes for table
	*/
    public ActionForward showTableGen(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0 ;
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	DBUtilInfo aDBUtilInfoObj = new  DBUtilInfo();
        allocatedDBUtilServlet( httpServletRequest );
        // put Form Data
     	iRetCode = putDBUtilGenTableDataIntoForm( oFrm, aDBUtilInfoObj );
    	return actionMapping.findForward( "showgentable" );
    }
    // end-of method showTableGen()

    /**
    * process generate java classes for table
    */
    public ActionForward processTableGen(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
    	int iRetCode=0,iRetCode2=0 ;
     	DynaActionForm oFrm = (DynaActionForm)actionForm;
     	DBUtilInfo aDBUtilInfoObj = new  DBUtilInfo();
        // get Form Data
     	iRetCode2 = getDBUtilGenTableData101FromForm(oFrm, aDBUtilInfoObj);
        allocatedDBUtilServlet( httpServletRequest );
        iRetCode = m_DBUtilServletObj.genTableFrameWorkFiles( aDBUtilInfoObj );
        // put Form Data
        iRetCode2 = putDBUtilGenTableDataIntoForm( oFrm, aDBUtilInfoObj );
      	m_BaseHelp.setFormData(oFrm,"errormsg", m_DBUtilServletObj.getAllMessages() );
        if(iRetCode == 0){
          	m_BaseHelp.setFormData(oFrm,"errormsg", "files generated !" );
        }
    	return actionMapping.findForward( "showgentable" );
    }
    // end-of method processTableGen()

    //====== START Private Methods ======>
    //====== START Private Methods ======>
    //====== START Private Methods ======>

    /**
	* fill Generate Table Classes data into form
	*/
    private int putDBUtilGenTableDataIntoForm(DynaActionForm oFrm, DBUtilInfo aHeadObj){
    	String aszTemp=null;
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;

    	aszTemp = aHeadObj.getTableName();
    	if(aszTemp.length() < 2){
    		if(null != m_DBUtilServletObj){
    			aHeadObj.setTableName(m_DBUtilServletObj.getAppPropertyValue("sqldata.tablename.default"));
    		}
    		
    	}
		m_BaseHelp.setFormData(oFrm,"tablename", aHeadObj.getTableName() );

    	aszTemp = aHeadObj.getDataClassName();
    	if(aszTemp.length() < 2){
    		if(null != m_DBUtilServletObj){
    			aHeadObj.setDataClassName(m_DBUtilServletObj.getAppPropertyValue("sqldata.dataobjname.default"));
    		}
    		
    	}
		m_BaseHelp.setFormData(oFrm,"dtoclassname", aHeadObj.getDataClassName() );

    	aszTemp = aHeadObj.getDataMethodPrefix();
    	if(aszTemp.length() < 2){
    		if(null != m_DBUtilServletObj){
    			aHeadObj.setDataMethodPrefix(m_DBUtilServletObj.getAppPropertyValue("sqldata.dataprefix.default"));
    		}
    		
    	}
		m_BaseHelp.setFormData(oFrm,"methodprefix", aHeadObj.getDataMethodPrefix() );

    	aszTemp = aHeadObj.getDBClassName();
    	if(aszTemp.length() < 2){
    		if(null != m_DBUtilServletObj){
    			aHeadObj.setDBClassName(m_DBUtilServletObj.getAppPropertyValue("sqldata.dataaccessname.default"));
    		}
    	}
		m_BaseHelp.setFormData(oFrm,"databaseclassname", aHeadObj.getDBClassName() );
    	return 0;
    }
    // end-of method putDBUtilGenTableDataIntoForm()

    /**
	* get generate table data from form
	*/
	private int getDBUtilGenTableData101FromForm(DynaActionForm oFrm, DBUtilInfo aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
        aHeadObj.setTableName(m_BaseHelp.getFormData(oFrm,"tablename"));
        aHeadObj.setDataClassName(m_BaseHelp.getFormData(oFrm,"dtoclassname"));
        aHeadObj.setDataMethodPrefix(m_BaseHelp.getFormData(oFrm,"methodprefix"));
        aHeadObj.setDBMethodPrefix( aHeadObj.getDataMethodPrefix() );
        aHeadObj.setDBClassName(m_BaseHelp.getFormData(oFrm,"databaseclassname"));
        aHeadObj.setProcessType(m_BaseHelp.getFormData(oFrm,"PROCESSTYPE"));
        aHeadObj.setCodeGenSTyleType(m_BaseHelp.getFormData(oFrm,"codegentype"));
        aHeadObj.setEmailFlag(false);
    	return 0;
    }
    // end-of method getDBUtilGenTableData101FromForm()

    /**
	* get SQL Statement Execute data from form
	*/
	private int getDBUtilSQLExecData101FromForm(DynaActionForm oFrm, DBUtilInfo aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
        aHeadObj.setmaxSearchResultRows(m_BaseHelp.getFormData(oFrm,"maxrows"));
        aHeadObj.setSQLExecType(m_BaseHelp.getFormData(oFrm,"exectype"));
        aHeadObj.setSQLInputString(m_BaseHelp.getFormData(oFrm,"sqlstatement"));
    	return 0;
    }
    // end-of method getDBUtilSQLExecData101FromForm()

    /**
	* fill SQL Statement data into form
	*/
    private int putDBUtilSQLExecDataIntoForm(DynaActionForm oFrm, DBUtilInfo aHeadObj){
    	if(null == aHeadObj) return -1;
    	if(null == oFrm) return -1;
    	m_BaseHelp.setFormData(oFrm, "maxrows", "" + aHeadObj.getmaxSearchResultRows() );
    	m_BaseHelp.setFormData(oFrm,"exectype", "" + aHeadObj.getSQLExecType() );
		m_BaseHelp.setFormData(oFrm,"sqlstatement", aHeadObj.getSQLInputString() );
    	return 0;
    }
    // end-of method putDBUtilSQLExecDataIntoForm()


	/**
	* allocate business rule layes object for organization 
	*/
	private void allocatedDBUtilServlet( HttpServletRequest aRequest ){
		if(null == aRequest) return;
		if(null == m_DBUtilServletObj){
			m_DBUtilServletObj = new DBUtilServlet();
			m_DBUtilServletObj.setupApp( aRequest );
		}
	}
	// end-of method allocatedDBUtilServlet()


    //====== START Private Variables ===>
    //====== START Private Variables ===>
    //====== START Private Variables ===>

	private ActionHelper m_BaseHelp = new ActionHelper();
	private DBUtilServlet m_DBUtilServletObj=null;

}
