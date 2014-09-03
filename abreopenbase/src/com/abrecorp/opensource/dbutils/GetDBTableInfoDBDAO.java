package com.abrecorp.opensource.dbutils;

/**
* Code generation Utility class
* For Database tables
* ABRE Technology Inc.
* Clemente D Marcillo January 1999
* Copyright:    Copyright (c) 1998-2006
*/

import com.abrecorp.opensource.base.*;
import com.abrecorp.opensource.dataobj.DBUtilInfo;

import java.util.*;

class GetDBTableInfoDBDAO extends ABREBase {

    private static final int DBASE_FIELD_TYPE_DATE = 93 ;
    private static final int DBASE_FIELD_TYPE_INT = 4 ;
    private static final int DBASE_FIELD_TYPE_SMALLINT = 5 ;
    private static final int DBASE_FIELD_TYPE_TINYINT = -6 ;
    private static final int DBASE_FIELD_TYPE_FLOAT = 6 ;
    private static final int DBASE_FIELD_TYPE_FLOAT7 = 7 ;
    private static final int DBASE_FIELD_TYPE_FLOAT8 = 8 ;
    private static final int DBASE_FIELD_TYPE_DECIMAL = 3 ;
    private static final int DBASE_FIELD_TYPE_CHAR = 1 ;
    private static final int DBASE_FIELD_TYPE_VARCHAR = 12 ;
    private static final int DBASE_FIELD_TYPE_TEXT = -1 ;

    // oracle data types
    private static final int ORACLE_DBASE_FIELD_TYPE_NUMBER = 2 ;
    private static final int ORACLE_DBASE_FIELD_TYPE_DATE = 91 ;

    private static final int ORACLE_BLOB_TYPE_NUMBER = 2004 ;

	/**
	** Constructor
	*/
	public GetDBTableInfoDBDAO(){}

    /**
	* execute SQL input statement
    * @param DBUtilInfo - the data base utility data object
    * @return 0 if all worked ok, non-zero if any errors
    */
    public int execSQLInputString( ABREDBConnection pConn, DBUtilInfo aInfo, ArrayList aList ){
		int iRetCode=0;
        String aszTemp=null;
		MethodInit("execSQLInputString");
        if(null == aInfo){
			setErr("data object required");
			return -1;
        }
        if(null == pConn){
			setErr("database connection required");
			return -1;
        }
        aszTemp = aInfo.getSQLInputString();
        if(aszTemp.length() < 2){
            setErr("sql string required");
            return -1;
        }
        aszTemp = aInfo.getSQLExecType() ;
        if(null == aszTemp) aszTemp="select";
        if(aszTemp.length() < 2){
			aszTemp="select";
        }
        if( aszTemp.equalsIgnoreCase( "other" ) ) {
            iRetCode = runSQLUpdateStringToDB(pConn, aInfo );
            if(0 != iRetCode) return iRetCode;
            return 0;            
        }
		iRetCode = runSQLSelectStringToDB(pConn, aInfo, aList );
		if(0 != iRetCode) return iRetCode;
        return 0;
    }
    // end-of method execSQLInputString()

    /**
	* generate XML Infrastructure Classes from Database Table
    * @param DBUtilInfo - the data base utility data object
    * @return 0 if all worked ok, non-zero if any errors
    */
    public int generateXMLClasses( ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
        String aszTemp=null;
		MethodInit("generateXMLClasses");
        if(null == pConn){
			setErr("database connection required");
			return -1;
        }
        iRetCode = checkRequiredFields( aInfo ) ;
        if(0 != iRetCode) return iRetCode ;

		/* get table information */
		iRetCode = checkTableDefinitionFromDB(pConn, aInfo.getTableName());
		if(0 != iRetCode){
			return 1;
		}

		/* allocate file utility class */
		iRetCode = allocFileOut();
		if(0 != iRetCode){
			setErr("allocating file object in " + aInfo.getFolderName() );
            return 1;
        }

		/* set database Access method names class */
		iRetCode = setInternalDataAcessMethodNames( aInfo );

		/**
		** write xml datastore class
		 */
		iRetCode = writeXMLDataStoreClass( pConn, aInfo );
		if(0 != iRetCode){
    		if(null != pConn) pConn.free();
			return 1;
		}

		/* write database Access class */
		iRetCode = writeXMLSampleFile( pConn, aInfo );
		if(0 != iRetCode){
    		if(null != pConn) pConn.free();
			return 1;
		}

        clearFileOut();
        return 0;
    }
    // end-of method generateXMLClasses()

    /**
	* generate ABRE Infrastructure common Classes from Database Table
    * @param DBUtilInfo - the data base utility data object
    * @return 0 if all worked ok, non-zero if any errors
    */
    public int generateDBTableClasses( ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
        String aszTemp=null;
		MethodInit("generateDBTableClasses");
        if(null == pConn){
			setErr("database connection required");
			return -1;
        }
        iRetCode = checkRequiredFields( aInfo ) ;
        if(0 != iRetCode) return iRetCode ;

		/* get table information */
		iRetCode = checkTableDefinitionFromDB(pConn, aInfo.getTableName());
		if(0 != iRetCode){
			return -1;
		}

		/* allocate file utility class */
		iRetCode = allocFileOut();
		if(0 != iRetCode){
			setErr("allocating file object in " + aInfo.getFolderName() );
            return -1;
        }

		/* set database Access method names class */
		iRetCode = setInternalDataAcessMethodNames( aInfo );

		/**
		** write datastore class
		 */
		iRetCode = writeDataStoreClass( pConn, aInfo );
		if(0 != iRetCode){
			return -1;
		}

		/* write database Access class */
		iRetCode = writeDBAccessClass( pConn, aInfo );
		if(0 != iRetCode){
			return -1;
		}

		/* write UIWrapper class */
        iRetCode = writeUIWrapperClass( pConn, aInfo );

		/* write DBWrapper class */
        iRetCode = writeDBWrapperClass( pConn, aInfo );

        clearFileOut();

        return 0;
    }
    // end-of method generateDBTableClasses()

    //========================================= Private Methods
    //========================================= Private Methods
    //========================================= Private Methods

    /**
	* check required fields
    */
    private int checkRequiredFields( DBUtilInfo aInfo ){
        String aszTemp=null;
        if(null == aInfo){
			setErr("data base utility data object required");
			return 1;
        }
        aszTemp = aInfo.getFolderName();
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("folder name required");
			return 1;
        }
        aszTemp = aInfo.getTableName();
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("database table name required");
			return 1;
        }
        aszTemp = aInfo.getDBClassName();
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("database utility class name required");
			return 1;
        }
        aszTemp = aInfo.getDataClassName();
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() < 2){
			setErr("data store class name required");
			return 1;
        }
        return 0;
    }
    // end-of method checkRequiredFields()

	/**
	* write UIWrapper class file
	*/
	private int writeUIWrapperClass( ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeUIWrapperClass");
		/* create the file */
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + m_UIWrapperClassName + ".html" );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		/* write start information */
        iRetCode = writeUIWrapperStart( aInfo ) ;

		iRetCode = writeUIWrapperBody(pConn,aInfo);

		/* write end information */
		iRetCode = writeUIWrapperTail( aInfo );
		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeUIWrapperClass()

	/**
	* write DBWrapper class file
	*/
	private int writeDBWrapperClass( ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeDBWrapperClass");
		/* create the class file */
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + m_DBWrapperClassName + m_OutFileType );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		/* write start information */
        iRetCode = writeDBWrapperStart( aInfo ) ;
		/* write get data from form method */
		iRetCode = writeDBWrapperFormGet1( pConn, aInfo );
		/* write put data from form method */
		iRetCode = writeDBWrapperFormPut1( pConn, aInfo );
		/* write insert method */
		iRetCode = writeDBWrapperInsert( aInfo );
		if(0 != iRetCode) return 1;
		/* write update method */
		iRetCode = writeDBWrapperUpdate( aInfo );
		if(0 != iRetCode) return 1;
		/* write delete method */
		iRetCode = writeDBWrapperDelete( aInfo );
		if(0 != iRetCode) return 1;
		/* write select one item method */
		iRetCode = writeDBWrapperSelectOne( aInfo );
		if(0 != iRetCode) return 1;
		/* write select one item method */
		iRetCode = writeDBWrapperSelectMany( aInfo );
		if(0 != iRetCode) return 1;
		/* write end information */
		iRetCode = writeDBWrapperTail( aInfo );
		if(0 != iRetCode) return 1;
		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeDBWrapperClass()

	/**
	* write DBaccess class file
	*/
	private int writeDBAccessClass(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeDBAccessClass");
		/* create the class file */
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + aInfo.getDBClassName() + m_OutFileType );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		/* write the header for the class file */
		iRetCode = writeDataAccessHead( aInfo );
		if(0 != iRetCode) return 1;
		/* write insert method */
		iRetCode = writeDataAccessInsert(pConn, aInfo );
		if(0 != iRetCode) return 1;
		/* write update method */
		iRetCode = writeDataAccessUpdate(pConn, aInfo );
		if(0 != iRetCode) return 1;
		/* write delete method */
		iRetCode = writeDataAccessDelete(pConn, aInfo );
		if(0 != iRetCode) return 1;
		/* write select one item method */
		iRetCode = writeDataAccessSelectOne(pConn, aInfo );
		if(0 != iRetCode) return 1;
		/* write select one item method */
		iRetCode = writeDataAccessSelectMany(pConn, aInfo );
		if(0 != iRetCode) return 1;
		iRetCode = writeDataAccessTail(aInfo.getDBClassName());
		if(0 != iRetCode) return 1;
		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeDBAccessClass()


	/**
	* write XML sample file
	*/
	private int writeXMLSampleFile(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeXMLSampleFile");
		/* create the class file */
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + aInfo.getDBClassName() + ".xml" );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		/* write the header for the class file */
		m_OutFileUtilObj.WriteOut("<?xml version=\"1.0\"?>\r\n\r\n");

		/* write xml sample file body */
		iRetCode = writeXMLSampleBody(pConn, aInfo );
		if(0 != iRetCode) return 1;

		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeXMLSampleFile()

	/**
	* write xml data access class file
	*/
	private int writeXMLDataStoreClass(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeXMLDataStoreClass");
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + aInfo.getDataClassName() + m_OutFileType );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		iRetCode = writeXMLDataStoreHead( aInfo );
		if(0 != iRetCode) return 1;
		iRetCode = writeXMLDataStoreBody(pConn,aInfo);
		if(0 != iRetCode) return 1;
		// write header for data store class
		iRetCode = writeXMLDataStoreTail( aInfo );
		if(0 != iRetCode) return 1;
		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeXMLDataStoreClass()

	/**
	* write data access class file
	*/
	private int writeDataStoreClass(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iRetCode=0;
		MethodInit("writeDataStoreClass");
		iRetCode = m_OutFileUtilObj.openOutputFile(aInfo.getFolderName() + aInfo.getDataClassName() + m_OutFileType );
		if(0 != iRetCode){
			m_OutFileUtilObj.copyErrObj(getErrObj());
			return 1;
		}
		iRetCode = writeDataStoreHead( aInfo );
		if(0 != iRetCode) return 1;
		iRetCode = writeDataStoreBody(pConn,aInfo);
		if(0 != iRetCode) return 1;
		// write header for data store class
		iRetCode = writeDataStoreTail( aInfo );
		if(0 != iRetCode) return 1;
		m_OutFileUtilObj.close();
		return 0;
	}
    // end-of method writeDataStoreClass()

	/**
	* run SQL update or Insert or Delete or DDL statement String
	*/
	private int runSQLUpdateStringToDB(ABREDBConnection pConn, DBUtilInfo aInfo  ){
		int iRetCode=0, iCount=0 , iMaxRow=0 ;
		int iColumnCount=0,iIndexCount=0;
		int iColumnType=0,iDisplaySize=0;
		String aszColumnName=null,aszColumnTypeName=null;
        Vector aList=null;
		MethodInit("runSQLUpdateStringToDB");
        if( null == aInfo ){
            setErr("input object required");
			return 1;
		}
        aList = aInfo.getResultList() ;
        if( null == aList ){
            setErr("result vector required");
			return 1;
		}
        aList.removeAllElements();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
		iMaxRow = aInfo.getmaxSearchResultRows();
		if(iMaxRow > 0){
			iRetCode=pConn.setMaxRows(iMaxRow);
			if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
				return 1;
			}
		}
		iRetCode = pConn.RunUpdate( aInfo.getSQLInputString() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
        String aReturnValue=null;
        Vector aRow=null;
        Vector aLabelList=null;
        iCount=0;
		while(pConn.getNextResult()){
            iColumnCount = pConn.getColumnCount();
            if(iColumnCount < 1){
                setErr("no columns returned from query");
                return 1;
            }
            if( iCount < 1 ){
                aLabelList = new Vector ( iColumnCount , 1 ) ;
            }
            aRow = new Vector ( iColumnCount , 1 ) ;
            for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
                aszColumnName = pConn.getColumnLabel(iIndexCount);
                aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
                iColumnType = getInternalColumnType( pConn, iIndexCount);
                if( iCount < 1 ) {
                    aLabelList.addElement( aszColumnName );
                }
                switch(iColumnType){
                    // text type
                    case -1:
                    // varchar type
                    case DBASE_FIELD_TYPE_CHAR :
                    case DBASE_FIELD_TYPE_VARCHAR :
                    default :
                        aReturnValue = pConn.getDBString( aszColumnName ) ;
                        break ; 
                    // timestamp type
                    case -2:
                    // date type
                    case DBASE_FIELD_TYPE_DATE :
                    case ORACLE_DBASE_FIELD_TYPE_DATE :
                        aReturnValue = "" + pConn.getDBTimestamp( aszColumnName ) ; 
                        break ;
                    // int type
                    case DBASE_FIELD_TYPE_INT :
                    case ORACLE_DBASE_FIELD_TYPE_NUMBER :
                    case DBASE_FIELD_TYPE_SMALLINT :
                    case DBASE_FIELD_TYPE_TINYINT :
                        aReturnValue = "" + pConn.getDBInt( aszColumnName );
                        break ;
                    // money type and decimal type
                    case DBASE_FIELD_TYPE_DECIMAL :
                    // double type
                    case DBASE_FIELD_TYPE_FLOAT :
                    case DBASE_FIELD_TYPE_FLOAT8 :
                    case DBASE_FIELD_TYPE_FLOAT7 :
                        aReturnValue = "" + pConn.getDBDouble( aszColumnName ) ; 
                        break ;
                }
                if(null == aReturnValue) aReturnValue="";
                aRow.addElement( aReturnValue );
                iRetCode=0;
            }
            // end for loop
            if ( iCount < 1 ) {
                aList.addElement( aLabelList );
            }
            iCount++;
            aList.addElement( aRow );
        }
        if ( iCount < 1 ) {
            setErr("no rows returned, sql run successfull: " + aInfo.getSQLInputString() );
            return 1;
        }
		return 0;
	}
    // end-of method runSQLUpdateStringToDB()

	/**
	* run SQL select String
	*/
	private int runSQLSelectStringToDB(ABREDBConnection pConn, DBUtilInfo aInfo, ArrayList aList  ){
		int iRetCode=0, iCount=0 , iMaxRow=0 ;
		int iColumnCount=0,iIndexCount=0;
		int iColumnType=0,iDisplaySize=0;
		String aszColumnName=null,aszColumnTypeName=null;
		MethodInit("runSQLSelectStringToDB");
        if( null == aInfo ){
            setErr("input object required");
			return 1;
		}
        if( null == aList ){
            setErr("input object required");
			return 1;
		}
        aList.clear();
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
		iMaxRow = aInfo.getmaxSearchResultRows();
		if(iMaxRow > 0){
			iRetCode=pConn.setMaxRows(iMaxRow);
			if(0 != iRetCode){
    			pConn.copyErrObj(getErrObj());
				return 1;
			}
		}
		iRetCode = pConn.RunQry( aInfo.getSQLInputString() );
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
        String aReturnValue=null;
        ArrayList aRow=null;
        ArrayList aLabelList=null;
        iCount=0;
		while(pConn.getNextResult()){
            iColumnCount = pConn.getColumnCount();
            if(iColumnCount < 1){
                setErr("no columns returned from query");
                return 1;
            }
            if( iCount < 1 ){
                aLabelList = new ArrayList ( iColumnCount ) ;
            }
            aRow = new ArrayList ( iColumnCount ) ;
            for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
                aszColumnName = pConn.getColumnLabel(iIndexCount);
                aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
                iColumnType = getInternalColumnType( pConn, iIndexCount);
                if( iCount < 1 ) {
                    aLabelList.add( aszColumnName );
                }
                switch(iColumnType){
                    // text type
                    case -1:
                    // varchar type
                    case DBASE_FIELD_TYPE_CHAR :
                    case DBASE_FIELD_TYPE_VARCHAR :
                    default :
                        aReturnValue = pConn.getDBString( aszColumnName ) ;
                        break ; 
                    // timestamp type
                    case -2:
                    // date type
                    case DBASE_FIELD_TYPE_DATE :
                    case ORACLE_DBASE_FIELD_TYPE_DATE :
                        aReturnValue = "" + pConn.getDBTimestamp( aszColumnName ) ; 
                        break ;
                    // int type
                    case DBASE_FIELD_TYPE_INT :
                    case ORACLE_DBASE_FIELD_TYPE_NUMBER :
                    case DBASE_FIELD_TYPE_SMALLINT :
                    case DBASE_FIELD_TYPE_TINYINT :
                        aReturnValue = "" + pConn.getDBInt( aszColumnName );
                        break ;
                    // money type and decimal type
                    case DBASE_FIELD_TYPE_DECIMAL :
                    // double type
                    case DBASE_FIELD_TYPE_FLOAT :
                    case DBASE_FIELD_TYPE_FLOAT8 :
                    case DBASE_FIELD_TYPE_FLOAT7 :
                        aReturnValue = "" + pConn.getDBDouble( aszColumnName ) ; 
                        break ;
                }
                if(null == aReturnValue) aReturnValue="";
                aRow.add( aReturnValue );
                iRetCode=0;
            }
            // end for loop
            if ( iCount < 1 ) {
                aList.add( aLabelList );
            }
            iCount++;
            aList.add( aRow );
        }
		return 0;
	}
    // end-of method runSQLSelectStringToDB()

	/**
	* get table info
	*/
	private int checkTableDefinitionFromDB(ABREDBConnection pConn, String tablename){
		int iRetCode=0;
		int iColumnCount=0,iIndexCount=0;
		int iColumnType=0,iDisplaySize=0,iPrecissionSize=0,iScalarSize=0;
        boolean bIsCurrency=false;
		String aszColumnName=null,aszColumnTypeName=null,aszJavaClassName=null,aszSchemaName=null;
		String qry1=null;
		MethodInit("checkTableDefinitionFromDB");
		qry1 = "SELECT * FROM " + tablename;
		iRetCode=pConn.getDBStmt();
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
		iRetCode=pConn.setMaxRows(1);
		if(0 != iRetCode){
			pConn.copyErrObj(getErrObj());
			return 1;
		}
		iRetCode = pConn.RunQry(qry1);
		if(0 != iRetCode){
			// pConn.copyErrObj(getErrObj());
            setErr("table \"" + tablename + "\" not found");
			return 1;
		}
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			aszSchemaName = pConn.getColumnSchemaName(iIndexCount);
			aszJavaClassName = pConn.getColumnClassName(iIndexCount);
			aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
            if(false == aszColumnTypeName.equalsIgnoreCase("BLOB")){
    			iColumnType = getInternalColumnType( pConn, iIndexCount);
                iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
                iPrecissionSize = pConn.ColumnPrecisionSize(iIndexCount);
                iScalarSize = pConn.ColumnScalarSize(iIndexCount);
                bIsCurrency = pConn.ColumnIsCurrency(iIndexCount);
            } else {
                iColumnType = getInternalColumnType( pConn, iIndexCount);
                iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
                // iPrecissionSize = pConn.ColumnPrecisionSize(iIndexCount);
                // iScalarSize = pConn.ColumnScalarSize(iIndexCount);
                // bIsCurrency = pConn.ColumnIsCurrency(iIndexCount);
            }
		}
		if(iColumnCount < 1){
			setErr("no columns found for table " + tablename);
			return 1;
		}
		return 0;
	}
    // end-of method checkTableDefinitionFromDB()

	/**
	* write header for data access class
	*/
	private int writeDataAccessHead( DBUtilInfo aInfo ){
        Enumeration aEnum=null;
        String aszTemp = aInfo.getDBPackageName();
        if(aszTemp.length() > 2){
    		m_OutFileUtilObj.WriteOut("package " + aInfo.getDBPackageName() + ";\r\n");
        }
		m_OutFileUtilObj.WriteOut("\r\n/**\r\n* ABRE Technology Code generated DataAccess class\r\n");
		m_OutFileUtilObj.WriteOut("* For Table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("*/\r\n\r\n");
        aEnum = aInfo.getDBUtilImport();
        if(null != aEnum){
            while( aEnum.hasMoreElements() ){
                aszTemp = (String)aEnum.nextElement();
        		m_OutFileUtilObj.WriteOut("import " + aszTemp + ";\r\n");
            }
        }
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("class " + aInfo.getDBClassName() + " extends ABREDatabaseAccess {\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t** Constructor\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic " + aInfo.getDBClassName() + "(){}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

		m_OutFileUtilObj.WriteOut("\r\n\t/** START Code generated DataAccess Methods");
		m_OutFileUtilObj.WriteOut(" For Table " + aInfo.getTableName() + " *******");
		m_OutFileUtilObj.WriteOut("*/\r\n\r\n");
		return 0;
	}
    // end-of method writeDataAccessHead()

	/**
	* write data access class end
	*/
	private int writeDataAccessTail(String dataacess){
		m_OutFileUtilObj.WriteOut("}\r\n");
		m_OutFileUtilObj.WriteOut("/* End Of Generated DataAccess Class " + dataacess + " */\r\n");
		return 0;
	}
    // end -of writeDataAccessTail

	/**
	* write out the table update method
	*/
	private int writeDataAccessUpdate(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		int iLoopCount=0;
		int iRetCode=0;
		String aszColumnName=null;
		String aszMethodName=null;
		aszMethodName = m_AcessUpdateName;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* update a row in table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("\t*   date variables can use method setPrepQryDateNull() or setPrepQryDate()\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
        if(bWriteFormatOne) {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
        } else {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "(ABREDBConnection " + m_aszDataAccessConnectionName + ", " + aInfo.getDataClassName() + " aHeadObj){\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL101 = \"UPDATE " + aInfo.getTableName() + " SET \" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\"");
		iColumnCount = pConn.getColumnCount();
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			if(iIndexCount > 1){
				if((iLoopCount > 5) && (iLoopCount != iColumnCount)){
					iLoopCount=0;
					m_OutFileUtilObj.WriteOut("\" +\r\n\t\t\t\" ");
				}
			}
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
			switch(iType){
				// date type
				case DBASE_FIELD_TYPE_DATE :
                case ORACLE_DBASE_FIELD_TYPE_DATE :
        			aszColumnName = pConn.getColumnLabel(iIndexCount);
                    if ( aszColumnName.equalsIgnoreCase( "create_dt" ) ){
    					iRetCode=0;
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "create_date" ) ){
    					iRetCode=0;
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "update_dt" ) ){
    					m_OutFileUtilObj.WriteOut("," + aszColumnName + "={fn now()}");
            			iLoopCount++;
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "last_update_date" ) ){
    					m_OutFileUtilObj.WriteOut("," + aszColumnName + "={fn now()}");
            			iLoopCount++;
    					break;
                    }
					m_OutFileUtilObj.WriteOut("," + aszColumnName + "=?");
        			iLoopCount++;
					break;
				// int type
                case DBASE_FIELD_TYPE_INT :
                case ORACLE_DBASE_FIELD_TYPE_NUMBER :
                case DBASE_FIELD_TYPE_SMALLINT :
                case DBASE_FIELD_TYPE_TINYINT :
        			aszColumnName = pConn.getColumnLabel(iIndexCount);
                    if ( aszColumnName.equalsIgnoreCase( "create_id" ) ){
    					iRetCode=0;
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "created_by" ) ){
    					iRetCode=0;
    					break;
                    }
					m_OutFileUtilObj.WriteOut("," + aszColumnName + "=?");
        			iLoopCount++;
					break;
				default :
					m_OutFileUtilObj.WriteOut("," + aszColumnName + "=?");
        			iLoopCount++;
					break;
			}
            // end type check
		}
		m_OutFileUtilObj.WriteOut("\" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\" WHERE something=? \";\r\n");
		/* write method start */
		iRetCode = writeMethodStart1(aszMethodName);
		if(0 != iRetCode) return 1;
		iRetCode = writeMethodStart2(aszMethodName);
		if(0 != iRetCode) return 1;
		/* write prep-query */
		iRetCode = writeDBprepQry("aszSQL101");
		if(0 != iRetCode) return 1;
		/* write set-prep-query */
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
            if( (DBASE_FIELD_TYPE_DATE == iType) || (ORACLE_DBASE_FIELD_TYPE_DATE == iType) ) {
                if ( aszColumnName.equalsIgnoreCase( "create_dt" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "create_date" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "update_dt" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "last_update_date" ) ){
                    iRetCode=0;
                } 
                else {
    				iLoopCount++;
        			iRetCode = writeSetPerpQry(iType,iLoopCount,aszColumnName,aInfo);
            		if(0 != iRetCode) return 1;
                }
            } 
            else if( (DBASE_FIELD_TYPE_INT == iType) || (iType == ORACLE_DBASE_FIELD_TYPE_NUMBER) ) {
                if ( aszColumnName.equalsIgnoreCase( "create_id" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "created_by" ) ){
                    iRetCode=0;
                } 
                else {
    				iLoopCount++;
        			iRetCode = writeSetPerpQry(iType,iLoopCount,aszColumnName,aInfo);
            		if(0 != iRetCode) return 1;
                }
            } 
            else {
				iLoopCount++;
				iRetCode = writeSetPerpQry(iType,iLoopCount,aszColumnName,aInfo);
				if(0 != iRetCode) return 1;
			}
		}
		writeDBexecUpdtPrep();
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName+ "()\r\n");
		return 0;
	}
    // end-of method writeDataAccessUpdate()

	/**
	* write DataAccess Method Insert
	*/
	private int writeDataAccessInsert(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		int iLoopCount=0;
		int iRetCode;
		String aszColumnName=null;
		String aszMethodName=null;
        aszMethodName = m_AcessInsertName;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* insert a row into table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("\t*   date variables can use method setPrepQryDateNull() or setPrepQryDate()\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
        if(bWriteFormatOne) {
            m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
        } else {
            m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "(ABREDBConnection " + m_aszDataAccessConnectionName + ", " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL101 = \" INSERT INTO " + aInfo.getTableName() + " ( \" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\"");
		iColumnCount = pConn.getColumnCount();
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			iLoopCount++;
			if(iIndexCount > 1){
				if((iLoopCount > 5) && (iLoopCount != iColumnCount)){
					iLoopCount=0;
					m_OutFileUtilObj.WriteOut("\" +\r\n\t\t\t\", ");
				} else {
					m_OutFileUtilObj.WriteOut(", ");
				}
			}
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			m_OutFileUtilObj.WriteOut(aszColumnName);
		}
		m_OutFileUtilObj.WriteOut(")\" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\" values(");
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			iLoopCount++;
			if(iIndexCount > 1){
				if((iLoopCount > 10) && (iLoopCount != iColumnCount)){
					iLoopCount=0;
					m_OutFileUtilObj.WriteOut("\" +\r\n\t\t\t\",");
				} else {
					m_OutFileUtilObj.WriteOut(",");
				}
			}
			iType = getInternalColumnType( pConn, iIndexCount);
			switch(iType){
				// date type
				case DBASE_FIELD_TYPE_DATE :
                case ORACLE_DBASE_FIELD_TYPE_DATE :
        			aszColumnName = pConn.getColumnLabel(iIndexCount);
                    if ( aszColumnName.equalsIgnoreCase( "create_dt" ) ){
    					m_OutFileUtilObj.WriteOut(" {fn now()} ");
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "create_date" ) ){
    					m_OutFileUtilObj.WriteOut(" {fn now()} ");
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "update_dt" ) ){
    					m_OutFileUtilObj.WriteOut(" {fn now()} ");
    					break;
                    }
                    else if ( aszColumnName.equalsIgnoreCase( "last_update_date" ) ){
    					m_OutFileUtilObj.WriteOut(" {fn now()} ");
    					break;
                    }
					m_OutFileUtilObj.WriteOut("?");
					break;
				default :
					m_OutFileUtilObj.WriteOut("?");
					break;
			}
		}
		m_OutFileUtilObj.WriteOut(")\" ;\r\n");

		/* write method start */
		iRetCode = writeMethodStart1(aszMethodName);
		if(0 != iRetCode) return 1;
		iRetCode = writeMethodStart2(aszMethodName);
		if(0 != iRetCode) return 1;

		/* write prep-query */
		iRetCode = writeDBprepQry("aszSQL101");
		if(0 != iRetCode) return 1;

		/* write set-prep-query */
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
            if( (DBASE_FIELD_TYPE_DATE == iType) || (ORACLE_DBASE_FIELD_TYPE_DATE == iType) ) {
                if ( aszColumnName.equalsIgnoreCase( "create_dt" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "update_dt" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "create_date" ) ){
                    iRetCode=0;
                } 
                else if ( aszColumnName.equalsIgnoreCase( "last_update_date" ) ){
                    iRetCode=0;
                } 
                else {
				iLoopCount++;
				iRetCode = writeSetPerpQry(iType,iLoopCount,aszColumnName,aInfo);
				if(0 != iRetCode) return 1;
                }
            } else {
				iLoopCount++;
				iRetCode = writeSetPerpQry(iType,iLoopCount,aszColumnName,aInfo);
				if(0 != iRetCode) return 1;
			}
		}
		writeDBexecUpdtPrep();
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName+ "()\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeDataAccessInsert()

	/**
	* write DataAccess Method Delete
	*/
	private int writeDataAccessDelete(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		int iLoopCount=0;
		int iRetCode;
		String aszColumnName=null;
		String aszMethodName=null;
		aszMethodName = m_AcessDeleteName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* delete a row from table " + aInfo.getTableName() + "\r\n\t*/\r\n");
        if(bWriteFormatOne) {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
        } else {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "(ABREDBConnection " + m_aszDataAccessConnectionName + ", " + aInfo.getDataClassName() + " aHeadObj){\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL101 = \"DELETE FROM " + aInfo.getTableName() + " WHERE " );
		iColumnCount = pConn.getColumnCount();
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			iLoopCount++;
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			m_OutFileUtilObj.WriteOut(aszColumnName + "= ");
			break;
		}
		m_OutFileUtilObj.WriteOut("\" ;\r\n");
		/* write method start */
		iRetCode = writeMethodStart1(aszMethodName);
		if(0 != iRetCode) return 1;
		iRetCode = writeMethodStart2(aszMethodName);
		if(0 != iRetCode) return 1;
		m_OutFileUtilObj.WriteOut("\t\taszSQL = aszSQL101 + \"something\" ;\r\n");
		// get a database statement
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = pConn.getDBStmt();\r\n");
		writeDBifcheck();
		// run update type statement
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = pConn.RunUpdate(aszSQL);\r\n");
		writeDBifcheck();
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName+ "()\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeDataAccessDelete()

	/**
	 * write set prep-query
	 */
	private int writeSetPerpQry(int iType, int iIndex, String field, DBUtilInfo aInfo){
		String aszColumnName=null;
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_aszDataAccessConnectionName + ".");
		switch(iType){
			// text type
			case -1:
			// timestamp type
			case -2:
			// varchar type
			case DBASE_FIELD_TYPE_CHAR :
			case DBASE_FIELD_TYPE_VARCHAR :
				m_OutFileUtilObj.WriteOut("setPrepQryString(");
				break;
			// date type
			case DBASE_FIELD_TYPE_DATE :
            case ORACLE_DBASE_FIELD_TYPE_DATE :
                if(bWriteFormatOne){
    				m_OutFileUtilObj.WriteOut("setPrepQryDateNull(");
                } else {
                    m_OutFileUtilObj.WriteOut("setPrepQryDate(");
                }
				break;
			// int type
			case DBASE_FIELD_TYPE_INT :
            case ORACLE_DBASE_FIELD_TYPE_NUMBER :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
				m_OutFileUtilObj.WriteOut("setPrepQryInt(");
				break;
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
				m_OutFileUtilObj.WriteOut("setPrepQryDouble(");
				break;
			// float type
			case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
				m_OutFileUtilObj.WriteOut("setPrepQryDouble(");
				break;
			default:
				m_OutFileUtilObj.WriteOut("setType(" + iType + ")" );
				break;
		}
		aszColumnName = fixVarName(field);
        String aszTemp = aInfo.getDataMethodPrefix();
        if(aszTemp.length() > 1){
            aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
        }
		// m_OutFileUtilObj.WriteOut(iIndex + ",aHeadObj.get" + aszColumnName + "());\r\n");
		m_OutFileUtilObj.WriteOut(" aHeadObj.get" + aszColumnName + "() );\r\n");
		writeDBifcheck();
		return 0;
	}
    // end-of method writeSetPerpQry()

	/**
	 * write method start section
	 */
	private int writeMethodStart1(String name){
		// m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + name + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == " + m_aszDataAccessConnectionName + "){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"null database connection\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		return 0;
	}
	/**
	 * write method start section
	 */
	private int writeMethodStart2(String name){
		m_OutFileUtilObj.WriteOut("\t\tif(null == aHeadObj){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"null input object\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		return 0;
	}
	/**
	 * write method start section
	 */
	private int writeMethodStart3(String name){
		m_OutFileUtilObj.WriteOut("\t\tif(null == aListObj){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"null input Vector object\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aSrchParmObj){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"null input search parameter object\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		return 0;
	}

	/**
	 * write if not zero from db
	 */
	private int writeDBifcheck(){
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
        if(bWriteFormatOne) {
    		m_OutFileUtilObj.WriteOut("\t\t\t" + m_aszDataAccessConnectionName + ".ErrorsToLog();\r\n");
        } else {
        	m_OutFileUtilObj.WriteOut("\t\t\t" + m_aszDataAccessConnectionName + ".copyErrObj(getErrObj());\r\n");
            m_OutFileUtilObj.WriteOut("\t\t\tErrorsToLog();\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		return 0;
	}

	/**
	 * write exe upd prep query
	 */
	private int writeDBexecUpdtPrep(){
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_aszDataAccessConnectionName + ".ExecutePrepQry();\r\n");
		writeDBifcheck();
		return 0;
	}

	/**
	 * write BDconn Prep Query type
	 */
	private int writeDBprepQry(String name){
		m_OutFileUtilObj.WriteOut("\t\tiRetCode=" + m_aszDataAccessConnectionName + ".PrepQuery(" + name + ");\r\n");
		writeDBifcheck();
		return 0;
	}

    // data acess methods connection name
    private String m_aszDataAccessConnectionName="m_oConn"; // pConn or m_oConn

	/**
	* write DataAccess Method Select many Items
	*/
	private int writeDataAccessSelectMany(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		int iLoopCount=0;
		String aszColumnName=null,TempColName=null;
		String aszMethodName=null;
		int iRetCode=0;
		aszMethodName = m_AcessSearchDBMethodName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* select a list of items from table " + aInfo.getTableName() + "\r\n\t*/\r\n");
        if(bWriteFormatOne) {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( ArrayList aListObj, SearchParms aSrchParmObj ){\r\n");
        } else {
    		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "(ABREDBConnection " + m_aszDataAccessConnectionName + ", ArrayList aListObj, SearchParms aSrchParmObj){\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0,iCount=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL2=null, aszTemp=null ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL101 = \" SELECT \" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\"");
		iColumnCount = pConn.getColumnCount();
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			iLoopCount++;
			if(iIndexCount > 1){
				if((iLoopCount > 5) && (iLoopCount != iColumnCount)){
					iLoopCount=0;
					m_OutFileUtilObj.WriteOut(",\" +\r\n\t\t\t\"");
				} else {
					m_OutFileUtilObj.WriteOut(",");
				}
			}
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			m_OutFileUtilObj.WriteOut(aszColumnName);
		}
		m_OutFileUtilObj.WriteOut("\" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\" FROM " + aInfo.getTableName() + " \";\r\n");
		/* write method start */
		iRetCode = writeMethodStart1(aszMethodName);
		if(0 != iRetCode) return 1;
		iRetCode = writeMethodStart3(aszMethodName);
		if(0 != iRetCode) return 1;
		// remove all items from vector
		m_OutFileUtilObj.WriteOut("\t\taListObj.clear();\r\n");
		// setup the query
		m_OutFileUtilObj.WriteOut("\t\tswitch(aSrchParmObj.getSearchType()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t// all items\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tcase 1:\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\taszTemp = aSrchParmObj.getSearchKey() ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\tif(aszTemp.length() < 3){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\t\taSrchParmObj.appendErrorMsg(\"minimum 3 character required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\taszSQL2 = aszSQL101 + \" WHERE UPPER(FIELD_NAME) LIKE ('%\" + aszTemp + \"%')\" ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\tbreak;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tdefault:\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\tsetErr(\"type not supported\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\taSrchParmObj.appendErrorMsg(\"type not supported\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		// get a database statement
		m_OutFileUtilObj.WriteOut("\t\tiRetCode=" + m_aszDataAccessConnectionName + ".getDBStmt();\r\n");
		writeDBifcheck();
		// setup max rows returned
		m_OutFileUtilObj.WriteOut("\t\tif(aSrchParmObj.getmaxSearchResultRows() > 0){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tiRetCode = " + m_aszDataAccessConnectionName + ".setMaxRows(aSrchParmObj.getmaxSearchResultRows());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tif(0 != iRetCode){\r\n");
        if(bWriteFormatOne) {
    		m_OutFileUtilObj.WriteOut("\t\t\t\t" + m_aszDataAccessConnectionName + ".ErrorsToLog();\r\n");
        } else {
        	m_OutFileUtilObj.WriteOut("\t\t\t\t" + m_aszDataAccessConnectionName + ".copyErrObj(getErrObj());\r\n");
            m_OutFileUtilObj.WriteOut("\t\t\t\tErrorsToLog();\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\t\t\treturn -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		// run the query
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_aszDataAccessConnectionName + ".RunQuery(aszSQL2);\r\n");
		writeDBifcheck();
		// get ready to receive data
		m_OutFileUtilObj.WriteOut("\t\twhile(" + m_aszDataAccessConnectionName + ".getNextResult()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + aInfo.getDataClassName() + " aHeadObj = new " + aInfo.getDataClassName() + "();\r\n");
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
			m_OutFileUtilObj.WriteOut("\t\t\taHeadObj.set" + aszColumnName + "(");
			iType = getInternalColumnType( pConn, iIndexCount);
			switch(iType){
				// text type
				case -1:
				// timestamp type
				case -2:
				// varchar type
				case DBASE_FIELD_TYPE_CHAR :
				case DBASE_FIELD_TYPE_VARCHAR :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBString(\"" + TempColName + "\")");
					break;
				// date type
				case DBASE_FIELD_TYPE_DATE :
                case ORACLE_DBASE_FIELD_TYPE_DATE :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBTimestamp(\"" + TempColName + "\")");
					break;
				// int type
				case DBASE_FIELD_TYPE_INT :
                case ORACLE_DBASE_FIELD_TYPE_NUMBER :
                case DBASE_FIELD_TYPE_SMALLINT :
                case DBASE_FIELD_TYPE_TINYINT :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBInt(\"" + TempColName + "\")");
					break;
				// money type and decimal type
				case DBASE_FIELD_TYPE_DECIMAL :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBDouble(\"" + TempColName + "\")");
					break;
				// double type
                case DBASE_FIELD_TYPE_FLOAT :
                case DBASE_FIELD_TYPE_FLOAT8 :
                case DBASE_FIELD_TYPE_FLOAT7 :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBDouble(\"" + TempColName + "\")");
					break;
			}
			m_OutFileUtilObj.WriteOut(");\r\n");
		}
		m_OutFileUtilObj.WriteOut("\t\t\taListObj.add(aHeadObj);\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName+ "()\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeDataAccessSelectMany()

	/**
	* write DataAccess Method Select One Item
	*/
	private int writeDataAccessSelectOne(ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		int iLoopCount=0;
		String aszColumnName=null,TempColName=null;
		String aszMethodName=null;
		int iRetCode=0;
		aszMethodName = m_AcessGetItemName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* select one item from table " + aInfo.getTableName() + "\r\n\t*/\r\n");
        if(bWriteFormatOne) {
            m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( " + aInfo.getDataClassName() + " aHeadObj, int iType, int iInPut1, String aszInPut2){\r\n");
        } else {
            m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "(ABREDBConnection " + m_aszDataAccessConnectionName + ", " + aInfo.getDataClassName() + " aHeadObj, int iType, int iInPut1, String aszInPut2){\r\n");
        }
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0,iCount=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL2=null, aszTemp=null ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszSQL101 = \" SELECT \" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\"");
		iColumnCount = pConn.getColumnCount();
		iLoopCount=0;
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			iLoopCount++;
			if(iIndexCount > 1){
				if((iLoopCount > 5) && (iLoopCount != iColumnCount)){
					iLoopCount=0;
					m_OutFileUtilObj.WriteOut(",\" +\r\n\t\t\t\"");
				} else {
					m_OutFileUtilObj.WriteOut(",");
				}
			}
			aszColumnName = pConn.getColumnLabel(iIndexCount);
			m_OutFileUtilObj.WriteOut(aszColumnName);
		}
		m_OutFileUtilObj.WriteOut("\" +\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\" FROM " + aInfo.getTableName() + " \" ;\r\n");
		/* write method start */
		iRetCode = writeMethodStart1(aszMethodName);
		if(0 != iRetCode) return 1;
		iRetCode = writeMethodStart2(aszMethodName);
		if(0 != iRetCode) return 1;
		// setup the query
		m_OutFileUtilObj.WriteOut("\t\tswitch(iType){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t// all items\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tcase 1:\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\taszSQL2 = aszSQL101 ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\tbreak;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tdefault:\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\tsetErr(\"type not supported\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		// get a database statement
		m_OutFileUtilObj.WriteOut("\t\tiRetCode=" + m_aszDataAccessConnectionName + ".getDBStmt();\r\n");
		writeDBifcheck();
		// run the query
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_aszDataAccessConnectionName + ".RunQuery(aszSQL2);\r\n");
		writeDBifcheck();
		// get ready to receive data
		m_OutFileUtilObj.WriteOut("\t\tiRetCode=-1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(" + m_aszDataAccessConnectionName + ".getNextResult()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tiRetCode=0;\r\n");
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
			m_OutFileUtilObj.WriteOut("\t\t\taHeadObj.set" + aszColumnName + "(");
			iType = getInternalColumnType( pConn, iIndexCount);
			switch(iType){
				// text type
				case -1:
				// timestamp type
				case -2:
				// varchar type
				case DBASE_FIELD_TYPE_CHAR :
				case DBASE_FIELD_TYPE_VARCHAR :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBString(\"" + TempColName + "\")");
					break;
				// date type
				case DBASE_FIELD_TYPE_DATE :
                case ORACLE_DBASE_FIELD_TYPE_DATE :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBTimestamp(\"" + TempColName + "\")");
					break;
				// int type
				case DBASE_FIELD_TYPE_INT :
                case ORACLE_DBASE_FIELD_TYPE_NUMBER :
                case DBASE_FIELD_TYPE_SMALLINT :
                case DBASE_FIELD_TYPE_TINYINT :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBInt(\"" + TempColName + "\")");
					break;
				// money type and decimal type
				case DBASE_FIELD_TYPE_DECIMAL :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBDouble(\"" + TempColName + "\")");
					break;
				// double type
                case DBASE_FIELD_TYPE_FLOAT :
                case DBASE_FIELD_TYPE_FLOAT8 :
                case DBASE_FIELD_TYPE_FLOAT7 :
					m_OutFileUtilObj.WriteOut(m_aszDataAccessConnectionName + ".getDBDouble(\"" + TempColName + "\")");
					break;
			}
			/*
			aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
			m_OutFileUtilObj.WriteOut(aszColumnTypeName + "\t");
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			m_OutFileUtilObj.WriteOut(iDisplaySize + "\t");
			iRetCode=0;
			m_OutFileUtilObj.WriteOut("\r\n");
			*/
			m_OutFileUtilObj.WriteOut(");\r\n");
		}
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName+ "()\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeDataAccessSelectOne()

	/**
	* write header for data store class
	*/
	private int writeDataStoreHead( DBUtilInfo aInfo ){
        String aszTemp = aInfo.getDataPackageName();
        if(null == aszTemp) aszTemp="";
        if(aszTemp.length() > 2){
    		m_OutFileUtilObj.WriteOut("package " + aInfo.getDataPackageName() + ";\r\n");
        }
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("/**\r\n");
		m_OutFileUtilObj.WriteOut("* ABRE Technology Code Generated DataStore Class\r\n");
		m_OutFileUtilObj.WriteOut("* For Table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("*/\r\n\r\n");
		m_OutFileUtilObj.WriteOut("import java.io.*;\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("public class " + aInfo.getDataClassName() + " extends BaseInfoObj implements Serializable, Cloneable {\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* constructor\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic " + aInfo.getDataClassName() + "(){}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* public clone method\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic Object clone(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\ttry{\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + aInfo.getDataClassName() + " el = (" + aInfo.getDataClassName() + ") super.clone();\r\n" );
		m_OutFileUtilObj.WriteOut("\t\t\treturn el;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t} catch (CloneNotSupportedException exp){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t//**** Start Code Generated Methods Do Not Modify *********************\r\n");
		m_OutFileUtilObj.WriteOut("\t//===> Start Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===> Start Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===> Start Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeDataStoreHead()

	/**
	 * write header for data store class
	 */
	private int writeDataStoreTail( DBUtilInfo aInfo ){
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End Code Generated Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("}\r\n");
		m_OutFileUtilObj.WriteOut("/* End Of ABRE Technology Code Generated DataStore Class " + aInfo.getDataClassName() + " */\r\n");
		return 0;
	}
    // end-of method writeDataStoreTail()

	/**
	 * fix variable name
	 */
	private String fixVarName(String name){
		String aszTempName=null;
		String aszFirstChar0=null,aszFirstChar1=null;
		int iLen=0;
	
		if(null == name) return "";
		aszTempName = name.trim();
		if(aszTempName.length() < 1) return "";
        aszTempName = aszTempName.toLowerCase();
		aszFirstChar0 = aszTempName.substring(0,1);
		aszFirstChar1 = aszFirstChar0.toUpperCase();
		aszFirstChar0 = aszTempName.substring(1);
		aszFirstChar1 += aszFirstChar0;
        while(true){
            iLen = aszFirstChar1.indexOf("_");
            if(iLen < 0) break;
            if(iLen < aszFirstChar1.length()){
                aszFirstChar0 = aszFirstChar1.substring(0,iLen);
                if(iLen >= (aszFirstChar1.length() + 2)) return aszFirstChar0;
                aszTempName = aszFirstChar1.substring(iLen + 1,iLen + 2);
                aszTempName = aszTempName.toUpperCase();
                aszFirstChar0 += aszTempName;
                aszTempName = aszFirstChar1.substring(iLen + 2,aszFirstChar1.length());
                aszFirstChar1 = aszFirstChar0 + aszTempName;
            }
        }
		return aszFirstChar1;
	}


	/**
	 * write header for data store class
	 */
	private int writeXMLDataStoreTail( DBUtilInfo aInfo ){
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> End XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("}\r\n");
		m_OutFileUtilObj.WriteOut("/* End Of Generated DataStore Class " + aInfo.getDataClassName() + " */\r\n");
		return 0;
	}
    // end-of method writeXMLDataStoreTail()

	/**
	* write XML header for data store class
	*/
	private int writeXMLDataStoreHead( DBUtilInfo aInfo ){
        String aszTemp = aInfo.getDataPackageName();
        if(null == aszTemp) aszTemp="";
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("/**\r\n");
		m_OutFileUtilObj.WriteOut("* Generated XML Methods \r\n");
		m_OutFileUtilObj.WriteOut("* For Table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("*/\r\n\r\n");
		m_OutFileUtilObj.WriteOut("import java.io.*;\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("public class " + aInfo.getDataClassName() + " extends BaseInfoObj implements Serializable, Cloneable {\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* constructor\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic " + aInfo.getDataClassName() + "(){}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Start XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Start XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Start XML Methods For Table " + aInfo.getTableName() + " \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeXMLDataStoreHead()

	/**
	* write body for XML sample file
	*/
	private int writeXMLSampleBody(ABREDBConnection pConn, DBUtilInfo aInfo){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		String aszColumnName=null,TempColName=null,aszColumnTypeName=null;
		String aszVariableName=null, aszPrefixValue=null ;
		iColumnCount = pConn.getColumnCount();
        aszPrefixValue = aInfo.getDataMethodPrefix();
        if( null == aszPrefixValue ) aszPrefixValue="";
        aszPrefixValue = aszPrefixValue.trim();

        m_OutFileUtilObj.WriteOut( "\t<" ) ; 
        if(aszPrefixValue.length() > 1){
            m_OutFileUtilObj.WriteOut( aszPrefixValue + ":"  ) ; 
        }
        m_OutFileUtilObj.WriteOut( aInfo.getTableName() + ">\r\n") ; 

		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
            aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
			aszColumnName = fixVarName(TempColName);
			m_OutFileUtilObj.WriteOut( "\t\t<" ) ; 
            if(aszPrefixValue.length() > 1){
    			m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
            }
			m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + " field=\"" ) ; 
			m_OutFileUtilObj.WriteOut( aszColumnName + "\">" +  aszColumnName + "</") ; 
            if(aszPrefixValue.length() > 1){
    			m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
            }
			m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + ">\r\n" ) ; 
		}

        m_OutFileUtilObj.WriteOut( "\t</" ) ; 
        if(aszPrefixValue.length() > 1){
            m_OutFileUtilObj.WriteOut( aszPrefixValue + ":"  ) ; 
        }
        m_OutFileUtilObj.WriteOut( aInfo.getTableName() + ">\r\n") ; 
        m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end-of method writeXMLSampleBody()

	/**
	* write body for XML data store class
	*/
	private int writeXMLDataStoreBody(ABREDBConnection pConn, DBUtilInfo aInfo){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		String aszColumnName=null,TempColName=null,aszColumnTypeName=null;
		String aszVariableName=null, aszPrefixValue=null ;
		iColumnCount = pConn.getColumnCount();
        aszPrefixValue = aInfo.getDataMethodPrefix();
        if( null == aszPrefixValue ) aszPrefixValue="";
        aszPrefixValue = aszPrefixValue.trim();
        m_OutFileUtilObj.WriteOut( "\t/**\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t* XML generation for table " + aInfo.getTableName() + " \r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t*/\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\tprivate String getXMLDATA" + aInfo.getTableName() + "( " + aInfo.getDataClassName() + " aHeadObj ){\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t\tStringBuffer aszStringBuffObj=null;\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t\tString aszTemp=null;\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t\tif(null == aHeadObj) return null;\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t\taszStringBuffObj = new StringBuffer(2000);\r\n" ) ; 
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
            aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
			aszColumnName = fixVarName(TempColName);
			switch(iType){
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
			// int type
			case DBASE_FIELD_TYPE_INT :
            case ORACLE_DBASE_FIELD_TYPE_NUMBER :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
			// double type
            case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
                // write XML Line
                m_OutFileUtilObj.WriteOut( "\t\taszStringBuffObj.append( \"<" ) ; 
                if(aszPrefixValue.length() > 1){
                    m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
                }
                m_OutFileUtilObj.WriteOut( aInfo.getTableName() + " field=\\\"" ) ; 
                m_OutFileUtilObj.WriteOut( aszColumnName + "\\\">\" + aHeadObj.get" + aszColumnName + "() + \"</") ; 
                if(aszPrefixValue.length() > 1){
                    m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
                }
                m_OutFileUtilObj.WriteOut( aInfo.getTableName() + ">\\r\\n\" ) ; \r\n" ) ; 
				break;
			// date type
            case ORACLE_DBASE_FIELD_TYPE_DATE :
			case DBASE_FIELD_TYPE_DATE :
            case DBASE_FIELD_TYPE_TEXT :
            case DBASE_FIELD_TYPE_CHAR :
            case DBASE_FIELD_TYPE_VARCHAR :
			default:
                m_OutFileUtilObj.WriteOut( "\t\taszTemp = aHeadObj.get" + aszColumnName + "();\r\n" );
                m_OutFileUtilObj.WriteOut( "\t\tif(null == aszTemp) aszTemp=\"\";\r\n" ) ; 
                m_OutFileUtilObj.WriteOut( "\t\tif(aszTemp.length() > 0){\r\n" ) ; 

                // write XML Line
                m_OutFileUtilObj.WriteOut( "\t\t\taszStringBuffObj.append( \"<" ) ; 
                if(aszPrefixValue.length() > 1){
                    m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
                }
                m_OutFileUtilObj.WriteOut( aInfo.getTableName() + " field=\\\"" ) ; 
                m_OutFileUtilObj.WriteOut( aszColumnName + "\\\">\" + aszTemp + \"</") ; 
                if(aszPrefixValue.length() > 1){
                    m_OutFileUtilObj.WriteOut( aszPrefixValue + ":" ) ; 
                }
                m_OutFileUtilObj.WriteOut( aInfo.getTableName() + ">\\r\\n\" ) ; \r\n" ) ; 

                m_OutFileUtilObj.WriteOut( "\t\t}\r\n" ) ; 
            }
		}
        m_OutFileUtilObj.WriteOut( "\t\treturn aszStringBuffObj.toString();\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t}\r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\t// end-of method getXMLDATA" + aInfo.getTableName() + "() \r\n" ) ; 
        m_OutFileUtilObj.WriteOut( "\r\n" ) ; 

        /*
        m_OutFileUtilObj.WriteOut( "\tpublic static final String XML_TABLENAME = \"" + aInfo.getTableName() + "\" ; \r\n" ) ; 
        if(aszPrefixValue.length() > 1){
            m_OutFileUtilObj.WriteOut( "\tpublic static final String XML_PREFIX = \"" + aszPrefixValue + "\" ; \r\n" ) ; 
        }
        m_OutFileUtilObj.WriteOut( "\tpublic static final String XML_ATTRIBUTE = \"" + aInfo.getDataClassName() + "\" ; \r\n" ) ; 

		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
            aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
			aszColumnName = fixVarName(TempColName);

			m_OutFileUtilObj.WriteOut( "\tpublic static final String XML_" + aInfo.getTableName() ) ; 
			m_OutFileUtilObj.WriteOut( "_" + aszColumnName + " = \"" ) ; 
			m_OutFileUtilObj.WriteOut( aszColumnName + "\" ; \r\n" ) ; 
		}
        m_OutFileUtilObj.WriteOut("\r\n");
        */
		return 0;
	}
    // end-of method writeXMLDataStoreBody()

    /**
    * internal method to correct data type for oracle number type
    */
    private int getInternalColumnType( ABREDBConnection pConn, int iIndexCount) {
        if(null == pConn) return -1;
		int iType = pConn.getColumnType(iIndexCount);
        if( iType == ORACLE_BLOB_TYPE_NUMBER ) return ORACLE_BLOB_TYPE_NUMBER;
		int iPrecissionSize = pConn.ColumnPrecisionSize(iIndexCount);
		int iScalarSize = pConn.ColumnScalarSize(iIndexCount);
		boolean bIsCurrency = pConn.ColumnIsCurrency(iIndexCount);
        // for oracle boyle standards for money changed (iPrecissionSize >= 19 )
        if(iType == ORACLE_DBASE_FIELD_TYPE_NUMBER){
            if(iPrecissionSize >= 4){
                if(iScalarSize >= 1){
                    if( bIsCurrency ){
                        iType=DBASE_FIELD_TYPE_DECIMAL;
                    }
                }
            }
        }
        return iType;
    }
    // end-of method method getInternalColumnType()

	/**
	* write body for data store class
	*/
	private int writeDataStoreBody(ABREDBConnection pConn, DBUtilInfo aInfo){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0,iPrecissionSize=0,iScalarSize=0;
        boolean bIsCurrency=false;
		String aszColumnName=null,TempColName=null,aszColumnTypeName=null;
		String aszVariableName=null;
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			m_OutFileUtilObj.WriteOut("\r\n");
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
			iPrecissionSize = pConn.ColumnPrecisionSize(iIndexCount);
			iScalarSize = pConn.ColumnScalarSize(iIndexCount);
			bIsCurrency = pConn.ColumnIsCurrency(iIndexCount);
            aszColumnTypeName = pConn.getColumnNameType(iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
            // for oracle boyle standards for money ( iPrecissionSize >= 19 )
            if(iType == ORACLE_DBASE_FIELD_TYPE_NUMBER){
                if(iPrecissionSize >= 4){
                    if(iScalarSize >= 1){
                        if( bIsCurrency ){
                            iType=DBASE_FIELD_TYPE_DECIMAL;
                        }
                    }
                }
            }
			switch(iType){
			// text type
            case DBASE_FIELD_TYPE_TEXT :
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "() in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
				aszVariableName = "m_asz" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate String ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=null;");
				m_OutFileUtilObj.WriteOut("\r\n\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String value){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(null == value){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\t" + aszVariableName + " = null;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = value.trim();\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic String get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(" + aszVariableName + " == null) return \"\";\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			// varchar type
			case DBASE_FIELD_TYPE_CHAR :
			case DBASE_FIELD_TYPE_VARCHAR :
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "(" + iDisplaySize + ") in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
				aszVariableName = "m_asz" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate String ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=null;");
				m_OutFileUtilObj.WriteOut("\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String value){\r\n");
                m_OutFileUtilObj.WriteOut("\t\tint iLen=" + iDisplaySize + ";\r\n");
                m_OutFileUtilObj.WriteOut("\t\tString aszTemp = value;\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(aszTemp == null) aszTemp=\"\";\r\n");
                m_OutFileUtilObj.WriteOut("\t\taszTemp = aszTemp.trim();\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(aszTemp.length() < 1){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\t" + aszVariableName + " = null;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(aszTemp.length() < iLen + 1){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\t" + aszVariableName + " = aszTemp;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = aszTemp.substring(0,iLen);\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic String get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(" + aszVariableName + " == null) return \"\";\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			// date type
            case ORACLE_DBASE_FIELD_TYPE_DATE :
			case DBASE_FIELD_TYPE_DATE :
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "() in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
				aszVariableName = "m_azd" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate java.util.Date ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=null;");
				m_OutFileUtilObj.WriteOut("\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(java.util.Date value){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(value == null){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\t" + aszVariableName + " = null;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = value;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic java.util.Date get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(" + aszVariableName + " == null) return null;\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
                // write private set int method
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "() in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
                // write private variable
				aszVariableName = "m_d" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate double ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=0.0;");
				m_OutFileUtilObj.WriteOut("\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(double number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = number;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write set String method
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = convertToDouble(number);\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write get double method
				m_OutFileUtilObj.WriteOut("\tpublic double get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			// int type
			case ORACLE_DBASE_FIELD_TYPE_NUMBER :
			case DBASE_FIELD_TYPE_INT :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
                // write int method comments
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "() in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
                // write private variable
				aszVariableName = "m_i" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate ");
				m_OutFileUtilObj.WriteOut("int ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=0;");
				m_OutFileUtilObj.WriteOut("\r\n");
                // write set int method
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(int number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = number;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write set String method
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = convertToInt(number);\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write get int method
				m_OutFileUtilObj.WriteOut("\tpublic int get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			// double type
			case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type " + aszColumnTypeName + "() in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
				aszVariableName = "m_d" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate ");
				m_OutFileUtilObj.WriteOut("double ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=0.0;");
				m_OutFileUtilObj.WriteOut("\r\n");
                // write private set double method
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(double number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = number;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write private set String method
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String number){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = convertToDouble(number);\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
                // write private get double method
				m_OutFileUtilObj.WriteOut("\tpublic double get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			default:
				m_OutFileUtilObj.WriteOut("\t/**\r\n");
				m_OutFileUtilObj.WriteOut("\t** " + TempColName + " type (default) " + aszColumnTypeName + "(" + iDisplaySize + ") in table " + aInfo.getTableName() + " \r\n");
				m_OutFileUtilObj.WriteOut("\t** type " + iType + "\r\n");
				m_OutFileUtilObj.WriteOut("\t**/\r\n");
				aszVariableName = "m_asz" + aszColumnName;
				m_OutFileUtilObj.WriteOut("\tprivate ");
				m_OutFileUtilObj.WriteOut("String ");
				m_OutFileUtilObj.WriteOut(aszVariableName);
				m_OutFileUtilObj.WriteOut("=null;");
				m_OutFileUtilObj.WriteOut("\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic void set" + aszColumnName + "(String value){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(value == null){\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\t" + aszVariableName + " = null;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t\treturn;\r\n");
				m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				m_OutFileUtilObj.WriteOut("\t\t" + aszVariableName + " = value.trim();\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\tpublic String get" + aszColumnName + "(){\r\n");
				m_OutFileUtilObj.WriteOut("\t\tif(" + aszVariableName + " == null) return \"\";\r\n");
				m_OutFileUtilObj.WriteOut("\t\treturn " + aszVariableName + ";\r\n");
				m_OutFileUtilObj.WriteOut("\t}\r\n");
				m_OutFileUtilObj.WriteOut("\r\n");
				break;
			}
		}
		return 0;
	}
    // end-of method writeDataStoreBody()

    //=================> UIWrapper Methods Follow
    //=================> UIWrapper Methods Follow
    //=================> UIWrapper Methods Follow

	/**
	* write UIWrapper Start
	*/
	private int writeUIWrapperStart( DBUtilInfo aInfo ){
		m_OutFileUtilObj.WriteOut("<HTML>\r\n");
		m_OutFileUtilObj.WriteOut("<!-- Generated UIWrapper File For Table " + aInfo.getTableName() + " Data Object " + aInfo.getDataClassName() + "-->\r\n");
		m_OutFileUtilObj.WriteOut("<HEAD>\r\n");
        m_OutFileUtilObj.WriteOut("<link rel=\"stylesheet\" href=\"abrestyle.css\" type=\"text/css\">\r\n");
		m_OutFileUtilObj.WriteOut("<TITLE>UIWrapper</TITLE>\r\n");
        m_OutFileUtilObj.WriteOut("</HEAD>\r\n");
        m_OutFileUtilObj.WriteOut("<%@ include file=\"topnav1.inc\" %>\r\n");
		m_OutFileUtilObj.WriteOut("<img src=\"icons/arrow_1.gif\" width=\"14\" height=\"30\" border=\"0\"><a class=\"head\">Generate UI Wrapper For " + aInfo.getDataClassName() + "</a>\r\n");
		m_OutFileUtilObj.WriteOut("<TABLE cellpadding=4 cellspacing=0 border=0 width=350 align=\"left\">\r\n");
		m_OutFileUtilObj.WriteOut("<TBODY>\r\n");
		m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
		m_OutFileUtilObj.WriteOut("\t\t<td valign=\"top\"><img src=\"icons/blank.gif\" width=\"80\" height=\"1\" border=\"0\"></td>\r\n");
		m_OutFileUtilObj.WriteOut("\t\t<td valign=\"top\"><img src=\"icons/blank.gif\" width=\"80\" height=\"1\" border=\"0\"></td>\r\n");
		m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
		m_OutFileUtilObj.WriteOut("<FORM NAME=\"ClientForm\" METHOD=\"POST\" action=\"admin\">\r\n");
		return 0;
	}
    // end -of writeUIWrapperStart

	/**
	* write UIWrapper end
	*/
	private int writeUIWrapperTail( DBUtilInfo aInfo ){
		m_OutFileUtilObj.WriteOut("</TBODY>\r\n");
		m_OutFileUtilObj.WriteOut("</TABLE>\r\n");
		m_OutFileUtilObj.WriteOut("<INPUT TYPE=\"hidden\" NAME=\"REQTYPE\" VALUE=\"DBUTILGENERATECLASSES\">\r\n");
		m_OutFileUtilObj.WriteOut("</FORM>\r\n");
		m_OutFileUtilObj.WriteOut("</BODY>\r\n");
		m_OutFileUtilObj.WriteOut("</HTML>\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end -of writeUIWrapperTail

	/**
	* write body for UIWrapper
	*/
	private int writeUIWrapperBody( ABREDBConnection pConn, DBUtilInfo aInfo ){
		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		String aszColumnName=null,TempColName=null;
		String aszVariableName=null;
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			m_OutFileUtilObj.WriteOut("\t<TR BGCOLOR=\"#cccccc\">\r\n");
			m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" COLSPAN=1><a class=\"tbldata1\"> ");
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
			switch(iType){
			// varchar type
            case -1:
			case DBASE_FIELD_TYPE_CHAR :
			case DBASE_FIELD_TYPE_VARCHAR :
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			// date type
            case ORACLE_DBASE_FIELD_TYPE_DATE :
			case DBASE_FIELD_TYPE_DATE :
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			// int type
			case DBASE_FIELD_TYPE_INT :
            case ORACLE_DBASE_FIELD_TYPE_NUMBER :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			// double type
            case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			default:
				m_OutFileUtilObj.WriteOut( aszColumnName + " </a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t<TR>\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<TD VALIGN=\"bottom\" align=\"center\" colspan=\"1\"><a class=\"tbldata1\">\r\n");
				m_OutFileUtilObj.WriteOut("\t\t<input type=\"text\" name=\"" + aszColumnName + "\" size=30 maxlength=" + iDisplaySize );
				m_OutFileUtilObj.WriteOut(" value=\"<%=a");
				m_OutFileUtilObj.WriteOut( aInfo.getDataClassName() + "Obj.get"  + aszColumnName + "()%>\" > \r\n");
				m_OutFileUtilObj.WriteOut("\t\t</a></TD>\r\n");
				m_OutFileUtilObj.WriteOut("\t</TR>\r\n");
				break;
			}
		}
		return 0;
	}
    // end-of method writeUIWrapperBody()

    //=================> DBWrapper Methods Follow
    //=================> DBWrapper Methods Follow
    //=================> DBWrapper Methods Follow

	/**
	* write DBWrapper Start
	*/
	private int writeDBWrapperStart( DBUtilInfo aInfo ){
		m_OutFileUtilObj.WriteOut("\r\n/**\r\n* generated Database Access DBWrapper class\r\n");
		m_OutFileUtilObj.WriteOut("* For Table " + aInfo.getTableName() + "\r\n");
		m_OutFileUtilObj.WriteOut("*/\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("class " + m_DBWrapperClassName + " extends ABREDatabaseAccess {\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t** Constructor\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic " + m_DBWrapperClassName + "(){}\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		return 0;
	}
    // end -of writeDBWrapperStart

	/**
	* write DBWrapper end
	*/
	private int writeDBWrapperTail( DBUtilInfo aInfo ){
        // public search parameter method
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* get search parameter object\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic SearchParms get" + aInfo.getDBMethodPrefix() + "SearchParms(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode = " + m_WrapperSearchParmAllocMethodName + "();\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn " + m_WrapperSearchParmObjName + " ;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method get" + aInfo.getDBMethodPrefix() + "SearchParms() \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
        // public search result method
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* get search result object\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic Enumeration get" + aInfo.getDBMethodPrefix() + "SearchResult(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode = " + m_WrapperSearchResultAllocMethodName + "();\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn " + m_WrapperSearchResultObjName + ".elements() ;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method get" + aInfo.getDBMethodPrefix() + "SearchResult() \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

		m_OutFileUtilObj.WriteOut("\t//===========> Private Methods \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Private Methods \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Private Methods \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

        // private search parameter allocation method
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* allocate search parameter object\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate int " + m_WrapperSearchParmAllocMethodName + "(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_WrapperSearchParmObjName + ") return 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperSearchParmObjName + " = new SearchParms();\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + m_WrapperSearchParmAllocMethodName + "() \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

        // private search result allocation method
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* allocate search result vector\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate int " + m_WrapperSearchResultAllocMethodName + "(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_WrapperSearchResultObjName + ") return 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperSearchResultObjName + " = new Vector(2,2);\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + m_WrapperSearchResultAllocMethodName + "() \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

        // private dataobject allocation method
		m_OutFileUtilObj.WriteOut("\t/**\r\n");
		m_OutFileUtilObj.WriteOut("\t* allocate data object\r\n");
		m_OutFileUtilObj.WriteOut("\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate int " + m_WrapperDataAllocMethodName + "(){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_WrapperDataObjName + ") return 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperDataObjName + " = new " + aInfo.getDataClassName() + "();\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + m_WrapperDataAllocMethodName + "() \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");

		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Private Variables \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Private Variables \r\n");
		m_OutFileUtilObj.WriteOut("\t//===========> Private Variables \r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate SearchParms " + m_WrapperSearchParmObjName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate Vector " + m_WrapperSearchResultObjName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate " + aInfo.getDataClassName() + " " + m_WrapperDataObjName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\tprivate " + aInfo.getDBClassName() + " " + m_WrapperDBObjName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("}\r\n");
		m_OutFileUtilObj.WriteOut("/* End Of Generated DBWrapper Class " + m_DBWrapperClassName + " */\r\n");
		return 0;
	}
    // end -of writeDBWrapperTail

	/**
	* write DBWrapper Method to get data from form 
	*/
	private int writeDBWrapperFormGet1( ABREDBConnection pConn, DBUtilInfo aInfo ){
		String aszMethodName=null;
		int iRetCode=0;
		aszMethodName = m_AcessGetFormDataWrapperName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper method get data from form\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( DynaActionForm oFrm, " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszTemp=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tDate aTempDateObj=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tDateUtil aDateObj = new DateUtil();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == oFrm) return -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aHeadObj) return -1;\r\n");

		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		String aszColumnName=null,TempColName=null;
		String aszVariableName=null;
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
			switch(iType){
			// int type
			case DBASE_FIELD_TYPE_INT :
            case ORACLE_DBASE_FIELD_TYPE_NUMBER :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
			// double type
            case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
			// varchar type
            case -1:
			case DBASE_FIELD_TYPE_CHAR :
			case DBASE_FIELD_TYPE_VARCHAR :
			default:
    			m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aszColumnName + "( m_BaseHelp.getFormData(oFrm,\"" + aszColumnName + "\") );\r\n");
				break;
			// date type
            case ORACLE_DBASE_FIELD_TYPE_DATE :
			case DBASE_FIELD_TYPE_DATE :
    			m_OutFileUtilObj.WriteOut("\t\taszTemp = m_BaseHelp.getFormData(oFrm,\"" + aszColumnName + "\");\r\n");
    			m_OutFileUtilObj.WriteOut("\t\taTempDateObj = aDateObj.convertToDate( aszTemp );\r\n");
    			m_OutFileUtilObj.WriteOut("\t\tif(null != aTempDateObj){\r\n");
    			m_OutFileUtilObj.WriteOut("\t\t\taHeadObj.set" + aszColumnName + "( aTempDateObj );\r\n");
    			m_OutFileUtilObj.WriteOut("\t\t} else {\r\n");
    			m_OutFileUtilObj.WriteOut("\t\t\taHeadObj.set" + aszColumnName + "( null );\r\n");
    			m_OutFileUtilObj.WriteOut("\t\t}\r\n");
				break;
			}
		}

		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperFormGet1()

	/**
	* write DBWrapper Method to put data into form
	*/
	private int writeDBWrapperFormPut1( ABREDBConnection pConn, DBUtilInfo aInfo ){
		String aszMethodName=null;
		int iRetCode=0;
		aszMethodName = m_AcessPutFormDataWrapperName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper method put data into form\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( DynaActionForm oFrm, " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tString aszTemp=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tDate aTempDateObj=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tDateUtil aDateObj = new DateUtil();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == oFrm) return -1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aHeadObj) return -1;\r\n");


		int iColumnCount=0,iIndexCount=0,iType=0,iDisplaySize=0;
		String aszColumnName=null,TempColName=null;
		String aszVariableName=null;
		iColumnCount = pConn.getColumnCount();
		for(iIndexCount=1; iIndexCount <= iColumnCount; iIndexCount++){
			TempColName = pConn.getColumnLabel(iIndexCount);
			iDisplaySize = pConn.getColumnDisplaySize(iIndexCount);
			iType = getInternalColumnType( pConn, iIndexCount);
			aszColumnName = fixVarName(TempColName);
            String aszTemp = aInfo.getDataMethodPrefix();
            if(aszTemp.length() > 1){
                aszColumnName = aInfo.getDataMethodPrefix() + aszColumnName ;
            }
			switch(iType){
			// varchar type
            case -1:
			case DBASE_FIELD_TYPE_CHAR :
			case DBASE_FIELD_TYPE_VARCHAR :
			default:
    			m_OutFileUtilObj.WriteOut("\t\tm_BaseHelp.setFormData(oFrm,\"" + aszColumnName + "\", aHeadObj.get" + aszColumnName + "() );\r\n");
				break;
			// int type
			case DBASE_FIELD_TYPE_INT :
            case ORACLE_DBASE_FIELD_TYPE_NUMBER :
            case DBASE_FIELD_TYPE_SMALLINT :
            case DBASE_FIELD_TYPE_TINYINT :
			// double type
            case DBASE_FIELD_TYPE_FLOAT :
            case DBASE_FIELD_TYPE_FLOAT8 :
            case DBASE_FIELD_TYPE_FLOAT7 :
			// money type and decimal type
			case DBASE_FIELD_TYPE_DECIMAL :
    			m_OutFileUtilObj.WriteOut("\t\tm_BaseHelp.setFormData(oFrm,\"" + aszColumnName + "\", \"\" + aHeadObj.get" + aszColumnName + "() );\r\n");
				break;

			// date type
            case ORACLE_DBASE_FIELD_TYPE_DATE :
			case DBASE_FIELD_TYPE_DATE :
    			m_OutFileUtilObj.WriteOut("\t\taszTemp = aDateObj.format( aHeadObj.get" + aszColumnName + "(),DateUtil.SHORT2,DateUtil.SHORT);\r\n");
    			m_OutFileUtilObj.WriteOut("\t\tm_BaseHelp.setFormData(oFrm,\"" + aszColumnName + "\", aszTemp );\r\n");
				break;
			}
		}


		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperFormPut1()

	/**
	* write DBWrapper Method Select One Item
	*/
	private int writeDBWrapperSelectOne( DBUtilInfo aInfo ){
		String aszMethodName=null;
		int iRetCode=0;
		aszMethodName = m_AcessGetItemWrapperName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper method load one item by unique id\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( PersonInfo aLoginToken, int number ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tABREDBConnection " + m_aszDataAccessConnectionName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + aszMethodName + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == getDbaseMgr()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"database not available\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aLoginToken){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"authorization token required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(number < 1){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"item id required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperDataObjName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperDataAllocMethodName + "();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tallocDBHelperObjects();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_aszDataAccessConnectionName + " = getDbaseMgr().getConn();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_WrapperDBObjName + "." + m_AcessGetItemName + "( " + m_aszDataAccessConnectionName + " , " + m_WrapperDataObjName + " , 100 ) ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_aszDataAccessConnectionName + ") " + m_aszDataAccessConnectionName + ".free();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + m_WrapperDBObjName + ".copyErrObj(getErrObj());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end -of writeDBWrapperSelectOne

	/**
	* write DBWrapper Method Update
	*/
	private int writeDBWrapperUpdate( DBUtilInfo aInfo ){
		String aszMethodName=null;
		aszMethodName = m_WrapperUpdateMethodName;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper edit method\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( PersonInfo aLoginToken, " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tABREDBConnection " + m_aszDataAccessConnectionName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + aszMethodName + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == getDbaseMgr()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"database not available\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aLoginToken){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"authorization token required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aHeadObj){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"data object required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aInfo.getDataMethodPrefix() + "UpdateId( aLoginToken.getPersonNumber() );\r\n");
		m_OutFileUtilObj.WriteOut("\t\tallocDBHelperObjects();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_aszDataAccessConnectionName + " = getDbaseMgr().getConn();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_WrapperDBObjName + "." + m_AcessUpdateName + "( " + m_aszDataAccessConnectionName + " , aHeadObj ) ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_aszDataAccessConnectionName + ") " + m_aszDataAccessConnectionName + ".free();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + m_WrapperDBObjName + ".copyErrObj(getErrObj());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperUpdate()

	/**
	* write DBWrapper Method Insert
	*/
	private int writeDBWrapperInsert( DBUtilInfo aInfo ){
		String aszMethodName=null;
        aszMethodName = m_WrapperInsertMethodName;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper create method\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( PersonInfo aLoginToken, " + aInfo.getDataClassName() + " aHeadObj ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tABREDBConnection " + m_aszDataAccessConnectionName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + aszMethodName + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == getDbaseMgr()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"database not available\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aLoginToken){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"authorization token required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aHeadObj){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"data object required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aInfo.getDataMethodPrefix() + "UpdateId( aLoginToken.getPersonNumber() );\r\n");
		m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aInfo.getDataMethodPrefix() + "CreateId( aLoginToken.getPersonNumber() );\r\n");
		m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aInfo.getDataMethodPrefix() + "SiteNumber( aLoginToken.getUserSiteNumber() );\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iNextID=getDbaseMgr().getNextUniqueID(\"uniqueid\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(iNextID < 1){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"error getting unique id number (uniqueid)\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tErrorsToLog();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\taHeadObj.set" + aInfo.getDataMethodPrefix() + "UniqueKey( iNextID );\r\n");

		m_OutFileUtilObj.WriteOut("\t\tallocDBHelperObjects();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_aszDataAccessConnectionName + " = getDbaseMgr().getConn();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_WrapperDBObjName + "." + m_AcessInsertName + "( " + m_aszDataAccessConnectionName + " , aHeadObj ) ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_aszDataAccessConnectionName + ") " + m_aszDataAccessConnectionName + ".free();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + m_WrapperDBObjName + ".copyErrObj(getErrObj());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperInsert()

	/**
	* write DBWrapper Method Delete
	*/
	private int writeDBWrapperDelete( DBUtilInfo aInfo ){
		String aszMethodName=null;
		aszMethodName = m_WrapperDeleteMethodName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper remove method\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( PersonInfo aLoginToken ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tABREDBConnection " + m_aszDataAccessConnectionName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + aszMethodName + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == getDbaseMgr()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"database not available\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aLoginToken){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"authorization token required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tallocDBHelperObjects();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_aszDataAccessConnectionName + " = getDbaseMgr().getConn();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_WrapperDBObjName + "." + m_AcessDeleteName + "( " + m_aszDataAccessConnectionName + " , " + m_WrapperDataObjName + " ) ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_aszDataAccessConnectionName + ") " + m_aszDataAccessConnectionName + ".free();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + m_WrapperDBObjName + ".copyErrObj(getErrObj());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperDelete()

	/**
	* write DBWrapper Method Select many Items
	*/
	private int writeDBWrapperSelectMany( DBUtilInfo aInfo ){
		String aszMethodName=null;
		aszMethodName = m_WrapperSearchMethodName ;
		m_OutFileUtilObj.WriteOut("\r\n");
		m_OutFileUtilObj.WriteOut("\t/**\r\n\t* DBWrapper search method\r\n\t*/\r\n");
		m_OutFileUtilObj.WriteOut("\tpublic int " + aszMethodName + "( PersonInfo aLoginToken ){\r\n");
		m_OutFileUtilObj.WriteOut("\t\tABREDBConnection " + m_aszDataAccessConnectionName + "=null;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tint iRetCode=0;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tMethodInit(\"" + aszMethodName + "\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == getDbaseMgr()){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"database not available\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null == aLoginToken){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"authorization token required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\talloc" + aInfo.getDBMethodPrefix() + "SearchParmsObj();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(" + m_WrapperSearchParmObjName + ".getSearchType() < 1){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\tsetErr(\"search type option required\");\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn 1;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_WrapperSearchParmObjName + ".setUserSiteNumber( aLoginToken.getUserSiteNumber() ); \r\n"); 
		m_OutFileUtilObj.WriteOut("\t\talloc" + aInfo.getDBMethodPrefix() + "SearchResultVector();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tallocDBHelperObjects();\r\n");
		m_OutFileUtilObj.WriteOut("\t\t" + m_aszDataAccessConnectionName + " = getDbaseMgr().getConn();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tiRetCode = " + m_WrapperDBObjName + "." + m_AcessSearchDBMethodName + "( " + m_aszDataAccessConnectionName + " , " + m_WrapperSearchResultObjName + " , " + m_WrapperSearchParmObjName + " ) ;\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(null != " + m_aszDataAccessConnectionName + ") " + m_aszDataAccessConnectionName + ".free();\r\n");
		m_OutFileUtilObj.WriteOut("\t\tif(0 != iRetCode){\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\t" + m_WrapperDBObjName + ".copyErrObj(getErrObj());\r\n");
		m_OutFileUtilObj.WriteOut("\t\t\treturn iRetCode;\r\n");
		m_OutFileUtilObj.WriteOut("\t\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t\treturn 0;\r\n");
		m_OutFileUtilObj.WriteOut("\t}\r\n");
		m_OutFileUtilObj.WriteOut("\t// end-of method " + aszMethodName + "() \r\n");
		return 0;
	}
    // end-of method writeDBWrapperSelectMany()


	/**
	* allocate file output utility
	*/
	private int allocFileOut(){
		if(null == m_OutFileUtilObj){
			m_OutFileUtilObj = new ABREOutFileUtil();
		}
		return 0;
	}
    // end-of method allocFileOut()

	/**
	* clear file output utility
	*/
	private void clearFileOut(){
		if(null == m_OutFileUtilObj) return ;
		m_OutFileUtilObj.close();
        m_OutFileUtilObj=null;
	}
    // end-of method clearFileOut()

	/**
	* generate internal data-access-method named
	*/
	private int setInternalDataAcessMethodNames( DBUtilInfo aInfo ){
        String aszTemp  = aInfo.getCodeGenSTyleType();
        if(aszTemp.equalsIgnoreCase("opensource")) {
            bWriteFormatOne=false;
            m_aszDataAccessConnectionName="pConn";
        }
        else if (aszTemp.equalsIgnoreCase("boyle")){
            bWriteFormatOne=true;
            m_aszDataAccessConnectionName="m_oConn";
        }
		m_AcessInsertName = "insertDB" + aInfo.getDBMethodPrefix();
		m_AcessUpdateName = "updateDB" + aInfo.getDBMethodPrefix();
		m_AcessDeleteName = "deleteDB" + aInfo.getDBMethodPrefix();
		m_AcessGetItemName = "getDBItem" + aInfo.getDBMethodPrefix();
		m_AcessSearchDBMethodName = "getDBList" + aInfo.getDBMethodPrefix();

        m_DBWrapperClassName = aInfo.getDBClassName() + "Wrapper" ;
        // DBWrapper private methods
		m_WrapperInsertMethodName = "create" + aInfo.getDBMethodPrefix() + "Item" ;
		m_WrapperUpdateMethodName = "edit" + aInfo.getDBMethodPrefix() + "Item" ;
		m_WrapperDeleteMethodName = "remove" + aInfo.getDBMethodPrefix() + "Item" ;
		m_AcessGetItemWrapperName = "get" + aInfo.getDBMethodPrefix() + "Item" ;
        m_AcessGetFormDataWrapperName = "get" + aInfo.getDBMethodPrefix() + "DataForm" ;
        m_AcessPutFormDataWrapperName = "put" + aInfo.getDBMethodPrefix() + "FormData" ;
		m_WrapperSearchMethodName = "search" + aInfo.getDBMethodPrefix() + "Items" ;

        // DBWrapper private allocation methods
        m_WrapperDataAllocMethodName = "alloc" + aInfo.getDataClassName() + "Object" ;
        m_WrapperSearchParmAllocMethodName = "alloc" + aInfo.getDBMethodPrefix() + "SearchParmsObj" ;
        m_WrapperSearchResultAllocMethodName = "alloc" + aInfo.getDBMethodPrefix() + "SearchResultVector" ;

        // DBWrapper private variables
        m_WrapperDataObjName = "m_" + aInfo.getDataClassName() + "Obj" ;
        m_WrapperDBObjName = "m_" + aInfo.getDBClassName() + "Obj" ;
        m_WrapperSearchParmObjName = "m_" + aInfo.getDBMethodPrefix() + "SearchParmsObj" ;
        m_WrapperSearchResultObjName = "m_" + aInfo.getDBMethodPrefix() + "SearchResultVectorObj" ;

        m_UIWrapperClassName = aInfo.getDataClassName() + "UIWrapper" ;

		return 0;
	}
    // end-of method setInternalDataAcessMethodNames()


	//=======================================> Private Variables
    //=======================================> Private Variables
    //=======================================> Private Variables

    private boolean bWriteFormatOne=true;

	private ABREOutFileUtil m_OutFileUtilObj=null;
    private String m_PackageName=null;

    private String m_OutFileType = ".java" ;

    // private method names used in data-access-class
    private String m_AcessInsertName=null;
    private String m_AcessUpdateName=null;
    private String m_AcessDeleteName=null;

    private String m_AcessGetItemName=null;
    private String m_AcessGetItemWrapperName=null;

    private String m_AcessGetFormDataWrapperName=null;
    private String m_AcessPutFormDataWrapperName=null;

    private String m_AcessSearchDBMethodName=null;

    private String m_UIWrapperClassName=null;
    private String m_DBWrapperClassName=null;

    // DBWrapper method names
    private String m_WrapperSearchMethodName=null;
    private String m_WrapperDeleteMethodName=null;
    private String m_WrapperInsertMethodName=null;
    private String m_WrapperUpdateMethodName=null;
    private String m_WrapperDataAllocMethodName=null;
    private String m_WrapperSearchParmAllocMethodName=null;
    private String m_WrapperSearchResultAllocMethodName=null;

    // DBWrapper private variable names
    private String m_WrapperDataObjName=null;
    private String m_WrapperDBObjName=null;
    private String m_WrapperSearchParmObjName=null;
    private String m_WrapperSearchResultObjName=null;

}
