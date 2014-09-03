package com.abrecorp.opensource.base;

/**
* Title:        Error Message Object
* Description:  Error Message Object for Base Java Classes
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version 1.0
*/

import java.util.*;

public class ABRELogMsg {

    /**
    * constructor
    */
    public ABRELogMsg(){}

    /**
    * Set interface name.
    */
    public void setInterface(String name){
        m_Interfacename=name;
    }

    /**
    * Return interface name.
    */
    public String getInterface(){
        if(null == m_Interfacename){
            return "";
        }
        return m_Interfacename;
    }

    /**
    * Set module name.
    */
    public void setModuleName(String name){
        m_ModuleName = name;
    }

    /**
    * Return module name.
    */
    public String getModuleName(){
        if(null == m_ModuleName){
            return "";
        }
        return m_ModuleName;
    }

    /**
    * Remove all existing error messages
    * and add a new error message.
    */
    public void setFirstMessage(String msg){
        reAllocMsgList();
        m_MsgList.addElement(msg);
    }

    /**
    * Remove all existing error messages.
    */
    public void clearMessages(){
        if(null != m_MsgList){
            m_MsgList.removeAllElements();
        }
    }

    /**
    * add new error message to list.
    */
    public void setMessage(String msg){
        allocMsgList();
        if(null != m_MsgList){
            m_MsgList.addElement(msg);
        }
    }

    /**
    * Return the total number of error messages.
    */
    public int getTotalMessages(){
        if(null != m_MsgList){
            return m_MsgList.size();
        }
        return 0;
    }

    /**
    * Return a specific error message by index
    */
    public String getMessages(int index){
        int iMaxMsg;
        allocMsgList();
        if(null != m_MsgList){
            iMaxMsg = m_MsgList.size();
            if(index  < iMaxMsg){
                return (String)m_MsgList.elementAt(index);
            }
        }
        return "";
    }

    /**
    * Write out error messages to console
    */
    public void PrintToConsole(){
        int iRows,index;
        if(null != m_MsgList){
            iRows = m_MsgList.size();
            for(index=0; index < iRows; index++){
                System.out.println((String)m_MsgList.elementAt(index));
            }
            // System.out.println("");
        }
    }


    // ----------------------------- Private Methods
    // ----------------------------- Private Methods
    // ----------------------------- Private Methods


    /**
    * allocate error message list
    */
    private void allocMsgList(){
        if(null == m_MsgList){
            m_MsgList = new Vector(3,2);
        }
    }

    /**
    * clear old list
    * and allocate new message list
    */
    private void reAllocMsgList(){
        m_MsgList=null;
        allocMsgList();
    }

    // ----------------------------- Private Variables
    // ----------------------------- Private Variables
    // ----------------------------- Private Variables

    private Vector m_MsgList=null;
    private String m_Interfacename=null;
    private String m_ModuleName=null;


}
