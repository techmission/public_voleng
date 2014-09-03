package com.abrecorp.opensource.base;

/**
* File output utility
* ABRE Corporation Inc.
*/

import java.io.*;
import java.text.*;

public class ABREOutFileUtil extends ABREBase {

	/**
	* initialize error object
	*/
	public ABREOutFileUtil(){}


	/**
	* write a formatted money value to the output file
	* fill with spaces ....
	*/
	private int WriteOutMoneyBytes(double inDbl, int num){
		DecimalFormat df= new DecimalFormat("0.00");
		String atestnum= df.format(inDbl);
		return WriteOutBytes(atestnum,num);
	}

	/**
	* write a number of bytes to the output file
	* fill with spaces ....
	*/
	public int WriteOutBytes(String input,int num){
		int iLen,iIndex;
		String aszMsg=null;
		byte[] thisbyte=null;

		MethodInit("WriteOutLine");
		if(null == input){
			aszMsg="";
			for(iIndex=0; iIndex < num; iIndex++){
				aszMsg +=" ";
			}
			return WriteOut(aszMsg);
		}
		iLen = input.length();
		if(iLen < 1){
			aszMsg="";
			for(iIndex=0; iIndex < num; iIndex++){
				aszMsg +=" ";
			}
			return WriteOut(aszMsg);
		}
		if(iLen == num){
			return WriteOut(input);
		}
		/* do we need to truncate ? */
		if(iLen > num){
			aszMsg = input.substring(0,num);
			return WriteOut(aszMsg);
		}
		/* do we need to truncate ? */
		if(iLen < num){
			aszMsg = input;
			int iFill = num - iLen;
			for(iIndex=0; iIndex < iFill; iIndex++){
				aszMsg +=" ";
			}
			return WriteOut(aszMsg);
		}
		/* do we need to truncate ? */
		return 0;
	}

	/**
	* write a string followed by a line feed to the output file
	*/
	public int WriteOutLine(String input){
		MethodInit("WriteOutLine");
		if(null == input) return 0;
		String aszBuff = input + "\r\n";
		return WriteOut(aszBuff);
	}

	/**
	* close the working file
	*/
	public void close(){
		int iRetCode=0;
		if(null == m_fOutFileHandle) return;
		try {
			m_fOutFileHandle.flush();
		} catch (Exception e) {  
			iRetCode=0;
		}
		try {
			m_fOutFileHandle.close();
		} catch (Exception e) {  
			iRetCode=0;
		}
		m_OutputFileName=null;
	}

	/**
	 * write a string to the output file
	 */
	public int WriteOut(String input){
		int iLen;
		String aszMsg=null;
		byte[] thisbyte=null;

		MethodInit("WriteOut");
		if(null == input) return 0;
		iLen = input.length();
		if(iLen < 1) return 0;
		if(null == m_fOutFileHandle){
			setErr("null output file handle");
			return 1;
		}
		try {
			thisbyte = input.getBytes();
		} catch (Exception e) {  
			setErr("Error writing to file: " + e.toString());
			return 1;
		}
		try {
			m_fOutFileHandle.write(thisbyte,0,iLen);
		} catch (Exception e) {  
			setErr("Error writing to file: " + e.toString());
			setErr("Error: " + e);
			return 1;
		}
		return 0;
	}

	/**
	 * Open the output file
	 */
	public int openOutputFile(String file){
		int iRetCode=0;
		String aszTempFile=null;

		MethodInit("openOutputFile");
		if(null == file){
			setErr("null output file name");
			return 1;
		}
		aszTempFile = file.trim();
		if(aszTempFile.length() < 2){
			setErr("blank output file name");
			return 1;
		}
		m_OutputFileName = file;
		try {
			m_fOutFileHandle = new FileOutputStream(m_OutputFileName);
			iRetCode=0;
		} catch (IOException e) {  
			setErr("Error opening output file " + m_OutputFileName);
			setErr(e.getMessage());
			iRetCode=1;
		}
		return iRetCode;
	}

	private FileOutputStream m_fOutFileHandle=null;
	private String m_OutputFileName=null;

}
