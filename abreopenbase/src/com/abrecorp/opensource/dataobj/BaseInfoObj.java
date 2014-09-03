package com.abrecorp.opensource.dataobj;

/**
* ABRE Technology Corporation 
* Base Data Information Object Class
*/

abstract class BaseInfoObj {

	/**
	*** Error Message Level 
	*** message level verbose 
	*** Manually Added
	**/
	private int m_iErrorMsgVerbose=-1;
	public void setErrorMsgVerbose(int number){
		m_iErrorMsgVerbose = number;
	}
	public void setErrorMsgVerbose(String number){
		m_iErrorMsgVerbose = convertToInt(number);
		return;
	}
	public int getErrorMsgVerbose(){
		return m_iErrorMsgVerbose;
	}

    /**
	*** Error Message Holder for User Interface 
	*** Manually Added
	**/
	private String m_aszErrorMessage=null;
	public void setErrorMsg(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp.trim();
		if(aszTemp.length() < 1){
			m_aszErrorMessage = null;
			return;
		}
		m_aszErrorMessage = aszTemp;//.trim();
	}
	/*
	public void appendErrorMsg(String value){
		String aszTemp = value;
		if(aszTemp == null) aszTemp="";
		aszTemp = aszTemp;//.trim();
		if(aszTemp.length() < 1){
			return;
		}
		if(m_aszErrorMessage == null){
			m_aszErrorMessage = aszTemp;//.trim();
		} else {
			m_aszErrorMessage = m_aszErrorMessage + " " + aszTemp;//.trim();
		}
	}
	*/
	public void appendErrorMsg(String value){
		if(null == value){
			m_aszErrorMessage = null;
			return;
		}
		if(value.length() < 1){
			m_aszErrorMessage = null;
			return;
		}
		if(m_aszErrorMessage == null){
			m_aszErrorMessage = value;//.trim();
		}else{
			m_aszErrorMessage = m_aszErrorMessage + value;//.trim();
		}
	}
	public String getErrorMsg(){
		if(m_aszErrorMessage == null) return "";
		return m_aszErrorMessage;
	}



	  /**
	  * Covert String to Integer 
	  */
	  public int convertToInt(String number){
	    Integer intObj=null;
	    int iTemp=0;
			if(null == number) return 0;
			String asTemp = number.trim();
			if(asTemp.length() < 1) return 0;
			try {
				intObj = Integer.valueOf(asTemp);
				iTemp=intObj.intValue();
			} catch (NumberFormatException  ex){
				iTemp=0;
			}
			return iTemp;
	  }
	  // end-of method convertToInt()



	  /**
	  * Covert String to Long 
	  */
	  public long convertToLong(String number){
	    Long longObj=null;
	    long lTemp=0;
			if(null == number) return 0;
			String asTemp = number.trim();
			if(asTemp.length() < 1) return 0;
			try {
				longObj = Long.valueOf(asTemp);
				lTemp=longObj.longValue();
			} catch (NumberFormatException  ex){
				lTemp=0;
			}
			return lTemp;
	  }
	  // end-of method convertToInt()


	/**
	* Covert String to double
	*/
	public double convertToDouble(String amount){
		Double aDObj=null;
		if(null == amount) return 0.0;
      String aszTemp = getNumbersOnly(amount);
		if(aszTemp.length() < 1) return 0.0;
		try {
			aDObj = Double.valueOf(aszTemp);
			return aDObj.doubleValue();
		} catch (NumberFormatException  ex) {
			return 0.0;
		}
	}
    // end-of convertToDouble()

	/**
	* remove spaces / commas / $  from string
    * allow negative value as -2345.00 and (234.55)
	*/
	public String getNumbersOnly(String aszIn){
		String asTemp=null;
		char aChar;
		StringBuffer aBuff=null;
		int iLen=0,iIndex=0;
		if(null == aszIn) return "";
		asTemp = aszIn.trim();
		iLen = asTemp.length();
		if(iLen < 1) return "";
		aBuff = new StringBuffer(iLen + 2);
        if( asTemp.charAt(0) == '(' ){
          if(asTemp.charAt( iLen-1 ) == ')' ){
              aBuff.append('-');
          }
        }
        if( asTemp.charAt(0) == '-' ){
              aBuff.append('-');
        }
		for(iIndex=0; iIndex < iLen; iIndex++){
			aChar = asTemp.charAt(iIndex);
			switch(aChar){
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				aBuff.append(aChar);
				break;	
			}
		}
		return aBuff.toString();
	}
    // end-of getNumbersOnly()


	/**
	* return value by position
	* move spaces / commas / $  from string
    * allow negative value as -2345.00 and (234.55)
	*/
	public String getDataByPosition(String aszIn, char sep, int pos){
		String asTemp=null;
		char aChar;
		StringBuffer aBuff=null;
		int iLen=0,iIndex=0,iCounter=0;
		if( pos < 1) return "";
		if(null == aszIn) return "";
		asTemp = aszIn.trim();
		iLen = asTemp.length();
		if(iLen < 2) return "";
		aBuff = new StringBuffer(iLen + 2);
		for(iIndex=0; iIndex < iLen; iIndex++){
			aChar = asTemp.charAt(iIndex);
			if( aChar == sep){
				iCounter++;
				if(iCounter == pos){
					return aBuff.toString();
				} else {
					aBuff = new StringBuffer(iLen + 2);
				}
			} else {
				aBuff.append(aChar);
			}
		}
		return "";
	}
    // end-of method getDataByPosition()

	/**
	* format for print
	*/
	public String formatForPrint(String aszIn, int iMaxLen){
		char aChar;
		int iLen=0,iIndex=0,iNumInLine=0;
		StringBuffer aszRealBuff=null,aszTempBuff=null;
		if(aszIn == null) return "";
		iLen = aszIn.length();
		if(iLen < iMaxLen) return aszIn;
		aszRealBuff = new StringBuffer(iLen + 20);
		aszTempBuff = new StringBuffer(120);
		for(iIndex=0; iIndex < iLen; iIndex++){
			iNumInLine++;
			aChar = aszIn.charAt(iIndex);
			switch(aChar){
				case 13:
					if(aszIn.charAt(iIndex + 1) == 10){
						iNumInLine=0;
						aChar=13;
						aszTempBuff.append(aChar);
						iIndex++;
						aChar=10;
						aszTempBuff.append(aChar);
						aszRealBuff.append(aszTempBuff.toString());
						aszTempBuff = new StringBuffer(120);
						continue;
					}
					aszTempBuff.append(aChar);
					break;
				case 10:
					iNumInLine=0;
					aChar=13;
					aszTempBuff.append(aChar);
					aChar=10;
					aszTempBuff.append(aChar);
					aszRealBuff.append(aszTempBuff.toString());
					aszTempBuff = new StringBuffer(120);
					continue;
				case ' ':
					aszRealBuff.append(aszTempBuff.toString());
					aszRealBuff.append(aChar);
					aszTempBuff = new StringBuffer(120);
					break;
				default:
					aszTempBuff.append(aChar);
					break;
			}
			if(iNumInLine > iMaxLen){
				aChar=13;
				aszRealBuff.append(aChar);
				aChar=10;
				aszRealBuff.append(aChar);
				iNumInLine=0;
				if(aszTempBuff.length() > 20){
					aszRealBuff.append(aszTempBuff.toString());
					// aszRealBuff.append('-');
					aszTempBuff = new StringBuffer(120);
				}
			}
		}
		aszRealBuff.append(aszTempBuff.toString());
		return aszRealBuff.toString();
	}
    // end-of method formatForPrint()


	/**
	* format for print
	*/
	public String formatForPrintSemi(String aszIn, int iMaxLen){
		char aChar;
		int iLen=0,iIndex=0,iNumInLine=0;
		StringBuffer aszRealBuff=null,aszTempBuff=null;
		if(aszIn == null) return "";
		iLen = aszIn.length();
		if(iLen < iMaxLen) return aszIn;
		aszRealBuff = new StringBuffer(iLen + 20);
		aszTempBuff = new StringBuffer(120);
		for(iIndex=0; iIndex < iLen; iIndex++){
			iNumInLine++;
			aChar = aszIn.charAt(iIndex);
			switch(aChar){
				case 13:
					if(aszIn.charAt(iIndex + 1) == 10){
						iNumInLine=0;
						aChar=13;
						aszTempBuff.append(aChar);
						iIndex++;
						aChar=10;
						aszTempBuff.append(aChar);
						aszRealBuff.append(aszTempBuff.toString());
						aszTempBuff = new StringBuffer(120);
						continue;
					}
					aszTempBuff.append(aChar);
					break;
				case ' ':
					aszRealBuff.append(aszTempBuff.toString());
					aszRealBuff.append(aChar);
					aszTempBuff = new StringBuffer(120);
					break;
				case ';':
					aszRealBuff.append(aszTempBuff.toString());
					aszRealBuff.append(aChar);
					aszTempBuff = new StringBuffer(120);
					break;
				default:
					aszTempBuff.append(aChar);
					break;
			}
			if(iNumInLine > iMaxLen){
				aChar=13;
				aszRealBuff.append(aChar);
				aChar=10;
				aszRealBuff.append(aChar);
				iNumInLine=0;
				if(aszTempBuff.length() > 20){
					aszRealBuff.append(aszTempBuff.toString());
					// aszRealBuff.append('-');
					aszTempBuff = new StringBuffer(120);
				}
			}
		}
		aszRealBuff.append(aszTempBuff.toString());
		return aszRealBuff.toString();
	}
    // end-of method formatForPrintSemi()

}