package com.abrecorp.opensource.dataobj;

/**
* Code Generation DataStore Class
* Database Utility DataStore
*/

import java.io.*;
import java.util.*;

public class DBUtilInfo extends BaseInfoObj implements Serializable, Cloneable {

	/**
	* Constructor
	*/
    public DBUtilInfo(){}

    /**
    * public clone method
    */
    public Object clone(){
        try{
            DBUtilInfo e1 = (DBUtilInfo) super.clone();
            return e1;
        } catch (CloneNotSupportedException exp){
            return null;
        }
    }

    //======================================> Member Variables Methods
    //======================================> Member Variables Methods
    //======================================> Member Variables Methods


	/**
	* package name of generated Data Object classes
	*/
	public void setDataPackageName(String key){
		if(null == key){
			m_DataPackageName=null;
			return ;
		}
		m_DataPackageName=key.trim();
	}
	public String getDataPackageName(){
		if(null == m_DataPackageName) return "";
		return m_DataPackageName;
	}

	/**
	* package name of generated DB Utility classes
	*/
	public void setDBPackageName(String key){
		if(null == key){
			m_DBPackageName=null;
			return ;
		}
		m_DBPackageName=key.trim();
	}
	public String getDBPackageName(){
		if(null == m_DBPackageName) return "";
		return m_DBPackageName;
	}

	/**
	* SQL Input String
	*/
	public void setSQLInputString(String key){
		if(null == key){
			m_SQLInputString=null;
			return ;
		}
		m_SQLInputString=key.trim();
	}
	public String getSQLInputString(){
		if(null == m_SQLInputString) return "";
		return m_SQLInputString;
	}

	/**
	* SQL Exec Type Request
	*/
	public void setSQLExecType(String key){
		if(null == key){
			m_ExecType=null;
			return ;
		}
		m_ExecType=key.trim();
	}
	public String getSQLExecType(){
		if(null == m_ExecType) return "";
		return m_ExecType;
	}

	/**
	* Process Type Request
	*/
	public void setProcessType(String key){
		if(null == key){
			m_ProcessType=null;
			return ;
		}
		m_ProcessType=key.trim();
	}
	public String getProcessType(){
		if(null == m_ProcessType) return "";
		return m_ProcessType;
	}

	/**
	* Data Base Table name to use to generate classes
	*/
	public void setTableName(String key){
		if(null == key){
			m_TableName=null;
			return ;
		}
		m_TableName=key.trim();
	}
	public String getTableName(){
		if(null == m_TableName) return "";
		return m_TableName;
	}

	/**
	* Data object class name
	*/
	public void setDataClassName(String key){
		if(null == key){
			m_DataClassName=null;
			return ;
		}
		m_DataClassName=key.trim();
	}
	public String getDataClassName(){
		if(null == m_DataClassName) return "";
		return m_DataClassName;
	}

	/**
	* Data Base utility object class name
	*/
	public void setDBClassName(String key){
		if(null == key){
			m_DBClassName=null;
			return ;
		}
		m_DBClassName=key.trim();
	}
	public String getDBClassName(){
		if(null == m_DBClassName) return "";
		return m_DBClassName;
	}

	/**
	* Data Object Method Prefix
	*/
	public void setDataMethodPrefix(String key){
		if(null == key){
			m_DataMethodPrefix=null;
			return ;
		}
		m_DataMethodPrefix=key.trim();
	}
	public String getDataMethodPrefix(){
		if(null == m_DataMethodPrefix) return "";
		return m_DataMethodPrefix;
	}

	/**
	* DB Util Object Method Prefix
	*/
	public void setDBMethodPrefix(String key){
		if(null == key){
			m_DBMethodPrefix=null;
			return ;
		}
		m_DBMethodPrefix=key.trim();
	}
	public String getDBMethodPrefix(){
		if(null == m_DBMethodPrefix) return "";
		return m_DBMethodPrefix;
	}

	/**
	* DB Util Object Import packages
	*/
	public void addDBUtilImport(String key){
		if(null == key) return ;
        String aszTemp = key.trim();
        if(aszTemp.length() < 1) return;
        allocDBUtilImportList();
        m_DBImportList.addElement(aszTemp);
	}
	public void clearDBUtilImport(){
        m_DBImportList = null;
	}
	public Enumeration getDBUtilImport(){
		if(null == m_DBImportList) return null;
		return m_DBImportList.elements();
	}

	/**
	* Email Files Flag
	*/
	public void setEmailFlag(boolean flag){
        m_bEmailFiles=flag;
    }
	public boolean getEmailFlag(){
        return m_bEmailFiles;
    }

	/**
	* Folder name to use to generate classes
	*/
	public void setFolderName(String key){
		if(null == key){
			m_FolderName=null;
			return ;
		}
		m_FolderName=key.trim();
	}
	public String getFolderName(){
		if(null == m_FolderName) return "";
		return m_FolderName;
	}

	/**
	* get result list Vector
	*/
	public Vector getResultList(){
		allocResultList();
		return m_aResultList;
	}

	/**
	* set search max rows
	*/
	public void setmaxSearchResultRows(int type){
		m_iMaxSearchResultRows=type;
	}
	public void setmaxSearchResultRows(String number){
		m_iMaxSearchResultRows = convertToInt(number);
	}
	public int getmaxSearchResultRows(){
		return m_iMaxSearchResultRows;
	}


	/**
	* Process Type Request
	*/
    private String m_aszCodeGenSTyleType=null;
	public void setCodeGenSTyleType(String key){
		if(null == key){
			m_aszCodeGenSTyleType=null;
			return ;
		}
		m_aszCodeGenSTyleType=key.trim();
	}
	public String getCodeGenSTyleType(){
		if(null == m_aszCodeGenSTyleType) return "";
		return m_aszCodeGenSTyleType;
	}

    //=======================================> Private Methods
    //=======================================> Private Methods
    //=======================================> Private Methods

	/**
	* allocate DB Util Import Vector
	*/
    private void allocDBUtilImportList(){
        if(null == m_DBImportList){
            m_DBImportList = new Vector ( 2,2 );
        }
    }

	/**
	* allocate result lisy Vector
	*/
    private void allocResultList(){
        if(null == m_aResultList){
            m_aResultList = new Vector ( 2,2 );
        }
    }

    //=======================================> Private Variables
    //=======================================> Private Variables
    //=======================================> Private Variables

    private Vector m_DBImportList=null;

    private String m_ProcessType=null;
    private String m_SQLInputString=null;
    private String m_ExecType=null;

    private String m_TableName=null;
    private String m_FolderName=null;

    private String m_DBClassName=null;
    private String m_DBPackageName=null;
    private String m_DBMethodPrefix=null;

    private String m_DataClassName=null;
    private String m_DataMethodPrefix=null;
    private String m_DataPackageName=null;
    private boolean m_bEmailFiles=false;

    private Vector m_aResultList=null;
	private int m_iMaxSearchResultRows=150;



}
