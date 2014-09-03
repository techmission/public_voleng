package com.abrecorp.opensource.dataobj;

/**
* Parameter=Value
*/
import java.io.*;

public class ValuePair extends BaseInfoObj implements Serializable, Cloneable {

    /**
    * constructor
    */
    public ValuePair(){}


    /**
    * public clone method
    */
    public Object clone(){
        try{
            ValuePair e1 = (ValuePair) super.clone();
            return e1;
        } catch (CloneNotSupportedException exp){
            return null;
        }
    }

	/**
	* Method Name
	*/
	public void setMethodName(String name){
		if(null == name){
			m_MethodName=null;
		} else {
			m_MethodName = name.trim();
		}
	}
	public String getMethodName(){
		if(null == m_MethodName) return "";
		return m_MethodName;
	}

	/**
	* parameter
	*/
	public void setParm(String name){
		if(null == name){
			m_ParmName=null;
		} else {
			m_ParmName = name.trim();
		}
	}
	public String getParm(){
		if(null == m_ParmName) return "";
		return m_ParmName;
	}

	/**
	* value
	*/
	public void setValue(String name){
		if(null == name){
			m_Value=null;
		} else {
			m_Value = name.trim();
		}
	}
	public String getValue(){
		if(null == m_Value) return "";
		return m_Value;
	}

	/**
	* Attributes Flag
	*/
	public void setAttributeIsActiveFalg(boolean number){
        m_bAttributeIsActiveFalg=number;
	}
	public boolean getAttributeIsActiveFalg(){
		return m_bAttributeIsActiveFalg;
	}

	/**
	* Attributes Flag
	*/
	public void setAttibuteFlag(boolean number){
        m_bAttibuteFalg=number;
	}
	public boolean getAttibuteFlag(){
		return m_bAttibuteFalg;
	}

	/**
	* End Method Invocation Flag
	*/
	public void setEndMethodInvocationFlag(boolean number){
        m_bEndMethodInvocationFlag=number;
	}
	public boolean getEndMethodInvocationFlag(){
		return m_bEndMethodInvocationFlag;
	}

	/**
	* Method Invocation Flag
	*/
	public void setMethodInvocationFlag(boolean number){
        m_bMethodInvocationFlag=number;
	}
	public boolean getMethodInvocationFlag(){
		return m_bMethodInvocationFlag;
	}

	/**
	* number value
	*/
	public void setValueNumber(int number){
        m_ValueNumber=number;
	}
	public int getValueNumber(){
		return m_ValueNumber;
	}

	/**
	* number 2 value
	*/
	public void setValue2Number(int number){
        m_Value2Number=number;
	}
	public int getValue2Number(){
		return m_Value2Number;
	}

	/**
	* number 3 value
	*/
	public void setValue3Number(int number){
        m_Value3Number=number;
	}
	public int getValue3Number(){
		return m_Value3Number;
	}

	/**
	* number 4 value
	*/
	public void setValue4Number(int number){
        m_Value4Number=number;
	}
	public int getValue4Number(){
		return m_Value4Number;
	}

	/**
	* number 5 value
	*/
	public void setValue5Number(int number){
        m_Value5Number=number;
	}
	public int getValue5Number(){
		return m_Value5Number;
	}

    //============================> Private Variables
    //============================> Private Variables
    //============================> Private Variables

    private String m_Value=null;
    private String m_ParmName=null;
    private int m_ValueNumber=0;
    private int m_Value2Number=0;
    private int m_Value3Number=0;
    private int m_Value4Number=0;
    private int m_Value5Number=0;
    private String m_MethodName=null;
    private boolean m_bMethodInvocationFlag=false;
    private boolean m_bAttibuteFalg=false;
    private boolean m_bAttributeIsActiveFalg=false;
    private boolean m_bEndMethodInvocationFlag=false;


}
