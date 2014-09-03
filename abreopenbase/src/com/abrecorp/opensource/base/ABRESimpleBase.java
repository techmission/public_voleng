package com.abrecorp.opensource.base;

/**
* ABRE Technology Corporation 
* Base Class
*/

public abstract class ABRESimpleBase {

	/**
	*** messages level
	**/
	public static final int m_iAllMessagesPlusSQL=20;
	public static final int m_iAllMessages=10;
	public static final int m_iMostMessages=8;
	public static final int m_iMediumMessages=6;
	public static final int m_iFewMessages=3;
	public static final int m_iOnlyCriticalMessages=0;
	public static final int m_iNoMessages=-1;

    /**
    * Replace all the single quotes in 
    * SQL statement with two single quotes 
    * to prepare for the query.
    */
    public String replacesinglequote(String s){
        if (s==null){ 
       	    return "";
        }
	    if (s.length()==0) {
			return "";
		}
		int pos=s.indexOf("'");
        if(pos < 1) return s;
		String temp="";
		while(pos>-1){
			temp+=s.substring(0,pos+1)+"'";
			s=s.substring(pos+1);
			pos=s.indexOf("'");
		}
		temp+=s;
		return temp;
    }
    // end-of replacesinglequote()

    /**
     * Replace single quote for insert - change ' to \\'
     * setPrepQryString probably already takes care of this - nevermind
     * DOES NEED TO BE USED on insert person - drupalized code
     * where it does not go through the PrepQryString method 
     */
     public String replaceSingleQuoteInsert(String s){
 		String temp="";
        temp=s.replaceAll( "'", "\\'" );
 		return temp;
     }
     // end-of replaceSingleQuoteInsert()

     
     /**
      * Replace all the return carriages (\n\r) in 
      * SQL statement with <br /> 
      * to prepare for the query. - added 2008-08-22 for drupalized code (which uses html for line breaks and does not read \n carriage returns)
      */
      public String replaceCarriageReturn(String s){
  		String temp="";
         temp=s.replaceAll( "\r\n", "<br />" );
  		return temp;
      }
      // end-of replaceCarriageReturn()

      
      /**
       * Replace all the replaceBeginJSPTag (\n\r) in 
       * SQL statement with <br /> 
       * to prepare for the query. - added 2008-08-22 for drupalized code (which uses html for line breaks and does not read \n carriage returns)
       */
       public static String replaceBeginJSPTag(String s){
   		String temp="";
          temp=s.replaceAll( "<%", " no jsp tags allowed " );
   		return temp;
       }
       // end-of replaceBeginJSPTag()
       
       /**
        * Replace all the replaceCloseJSPTag (\n\r) in 
        * SQL statement with <br /> 
        * to prepare for the query. - added 2008-08-22 for drupalized code (which uses html for line breaks and does not read \n carriage returns)
        */
        public static String replaceCloseJSPTag(String s){
    		String temp="";
           temp=s.replaceAll( "%>", " no jsp tags allowed " );
    		return temp;
        }
        // end-of replaceCloseJSPTag()

     
     /**
      * Replace all the line breaks (<br />) in 
      * SQL statement with \n\r for loading in the edit text area field 
      * to prepare for the query. - added 2008-08-22 for drupalized code (which uses <br /> or <p></p> and does not read \n carriage returns)
      */
      public String replaceLineBreak(String s){
  		String temp="";
  		if(!(s == null)){
  	        temp=s.replaceAll( "<br />", "\r\n" );
  	        temp=temp.replaceAll( "<br>", "\r\n" );
  	        temp=temp.replaceAll( "<p>", "\r\n" );
  	        temp=temp.replaceAll( "</p>", "\r\n" );
  		}
  		return temp;
      }
      // end-of replaceLineBreak()

      
    /**
	* remove space from string
	*/
	public String removeSpace(String aszIn){
		String asTemp=null;
		char aChar;
		StringBuffer aBuff=null;
		int iLen=0,iIndex=0;
		if(null == aszIn) return "";
		asTemp = aszIn.trim();
		iLen = asTemp.length();
		if(iLen < 1) return "";
		aBuff = new StringBuffer(iLen + 2);
		for(iIndex=0; iIndex < iLen; iIndex++){
			aChar = asTemp.charAt(iIndex);
			if(' ' == aChar){
				continue;
			}
			else if('\'' == aChar){
				continue;
			}
			else if('\"' == aChar){
				continue;
			}
			else {
				aBuff.append(aChar);
			}
		}
		return aBuff.toString();
	}
  // end-of removeSpace()


	/**
	* remove spaces / commas / $  from string
  * allow negative value as -2345.00 and (234.55)
	*/
	public String getAlphaNumbers(String aszIn){
		String asTemp=null;
		char aChar;
		StringBuffer aBuff=null;
		int iLen=0,iIndex=0;
		if(null == aszIn) return "";
		asTemp = aszIn.trim();
		iLen = asTemp.length();
		if(iLen < 1) return "";
		aBuff = new StringBuffer(iLen + 2);
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
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				aBuff.append(aChar);
				break;	
			}
		}
		return aBuff.toString();
	}
  // end-of getAlphaNumbers()

	/**
	* remove spaces / commas / $  from string
  * allow negative value as -2345.00 and (234.55)
	*/
	public String getAlphaNumbersOnly(String aszIn){
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
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case '-':
			case '.':
				aBuff.append(aChar);
				break;	
			}
		}
		return aBuff.toString();
	}
  // end-of getAlphaNumbersOnly()

  /**
  * Covert String to Integer 
  */
	public static int convertToInt(String number){
		Integer intObj=null;
    int iTemp=0;
		if(null == number) return 0;
		String asTemp = number.trim();
		if(asTemp.length() < 1) return 0;
      asTemp = getNumbersOnly( number );
		try {
			intObj = Integer.valueOf(asTemp);
			iTemp=intObj.intValue();
		} catch (NumberFormatException  ex){
			iTemp=0;
		}
		return iTemp;
	}
  // end-of convertToInt()

    /**
    * Covert String to Long 
    */
	public static long convertToLong(String number){
		Long longObj=null;
        long iTemp=0;
		if(null == number) return 0;
		String asTemp = number.trim();
		if(asTemp.length() < 1) return 0;
        asTemp = getNumbersOnly( number );
		try {
			longObj = Long.valueOf(asTemp);
			iTemp=longObj.longValue();
		} catch (NumberFormatException  ex){
			iTemp=0;
		}
		return iTemp;
	}
    // end-of convertToInt()

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
	public static String getNumbersOnly(String aszIn){
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


}
