package com.abrecorp.opensource.base;

import java.util.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;

public class ABREDate {

    /**
    * Constructor
    */
    public ABREDate(){}


	// supported date and time formats
	public static final int NO_DATE = -1;
	public static final int NO_TIME = -1;
	public static final int SHORT = 1;
	public static final int MEDIUM = 2;
	public static final int LONG = 3;
	public static final int FULL = 4;
	public static final int CUSTOM_DATE_1 = 5;
    public static final int CUSTOM_DATE_2 = 6;
    public static final int SHORT2 = 7;


    //==== Current Date Methods START ==== >
    //==== Current Date Methods START ==== >
    //==== Current Date Methods START ==== >

	/**
	* return current date format mm/dd/yyy
	*/
	public String getCurrentDate(){
		int iMonth=0,iDay=0,iYear=0;
        String aszDay=null,aszMonth=null;
		Calendar cToday = Calendar.getInstance();
		int iInMonth=0,iInYear=0,iInDay=0;
		iDay = cToday.get(Calendar.DAY_OF_MONTH);
        if(iDay < 10) aszDay = "0" + iDay;
        else aszDay = "" + iDay;
		iMonth = cToday.get(Calendar.MONTH) + 1;
        if(iMonth < 10) aszMonth = "0" + iMonth;
        else aszMonth = "" + iMonth;
		iYear = cToday.get(Calendar.YEAR);
        return aszMonth + "/" + aszDay + "/" + iYear;
    }
    // end-of getCurrentDate()

	/**
	* current date Month Jan=1 feb=2
	*/
	public int getCurrentMonth(){
		Calendar cToday = Calendar.getInstance();
        return cToday.get(Calendar.MONTH) + 1;
    }
    // end-of getCurrentMonth()

	/**
	* current date Year yyyy
	*/
	public int getCurrentYear(){
		Calendar cToday = Calendar.getInstance();
        return cToday.get(Calendar.YEAR);
    }
    // end-of getCurrentYear()

	/**
	* current date Day
	*/
	public int getCurrentDay(){
		Calendar cToday = Calendar.getInstance();
        return cToday.get(Calendar.DAY_OF_MONTH);
    }
    // end-of getCurrentDay()

	/**
	* current hour of Day in 1-24
	*/
	public int getCurrentHour(){
		Calendar cToday = Calendar.getInstance();
        return cToday.get(Calendar.HOUR_OF_DAY);
    }
    // end-of getCurrentHour()


	/**
	* return current day-ofyear
	*/
	public int getCurrentDayOfYear(){
		Calendar cToday = Calendar.getInstance();
        int iDayOfYear = cToday.get(Calendar.DAY_OF_YEAR);
        return iDayOfYear ;
    }
    // end-of getCurrentDayOfYear()


	/**
	* current yyyy+day-ofyear
    * julian date ?
	*/
	public int getCurrentYearPlusDayOfYear(){
        int iNewFullYearDay=-1;
		Integer aIntObj=null;
        String aszDayOfYear=null;
		Calendar cToday = Calendar.getInstance();
        int iYear = cToday.get(Calendar.YEAR);
        int iDayOfYear = cToday.get(Calendar.DAY_OF_YEAR);
        if(iDayOfYear < 10){
            aszDayOfYear = "00" + iDayOfYear;
        } else if(iDayOfYear < 100){
            aszDayOfYear = "0" + iDayOfYear;
        } else {
            aszDayOfYear = "" + iDayOfYear;
        }
        String fullyearday = "" + iYear + aszDayOfYear;
		try {
			aIntObj = Integer.valueOf(fullyearday);
			iNewFullYearDay = aIntObj.intValue();
		} catch (NumberFormatException  ex){
			iNewFullYearDay=-1;
		}
		return iNewFullYearDay;
    }
    // end-of getCurrentYearPlusDayOfYear()

    //==== Current Date Methods END ==== >
    //==== Current Date Methods END ==== >
    //==== Current Date Methods END ==== >

    //==== Date Input Methods START ==== >
    //==== Date Input Methods START ==== >
    //==== Date Input Methods START ==== >


	/**
    * get year from date object
	*/
	public int getDateYear(Date aszIn){
        if(null == aszIn) return -1;
		Calendar cToday = Calendar.getInstance();
        cToday.setTime( aszIn );
        return cToday.get(Calendar.YEAR);
    }
    // end-of getDateYear()

	/**
    * get month from date object
    * date month is 0=Jan 11=Dec
	*/
	public int getDateMonth(Date aszIn){
        if(null == aszIn) return -1;
		Calendar cToday = Calendar.getInstance();
        cToday.setTime( aszIn );
        return cToday.get(Calendar.MONTH);
    }
    // end-of getDateMonth()

	/**
    * get month-day from date object
	*/
	public int getDateDayOfMonth(Date aszIn){
        if(null == aszIn) return -1;
		Calendar cToday = Calendar.getInstance();
        cToday.setTime( aszIn );
        return cToday.get(Calendar.DAY_OF_MONTH);
    }
    // end-of getDateDayOfMonth()

	/**
    * get day-of-year from date object
	*/
	public int getDateDayOfYear(Date aszIn){
        if(null == aszIn) return -1;
		Calendar cToday = Calendar.getInstance();
        cToday.setTime( aszIn );
        return cToday.get(Calendar.DAY_OF_YEAR);
    }
    // end-of getDateDayOfYear()

	/**
    * get hour-of-dayr from date object
	*/
	public int getDateHourOfDay(Date aszIn){
        if(null == aszIn) return -1;
		Calendar cToday = Calendar.getInstance();
        cToday.setTime( aszIn );
        return cToday.get(Calendar.HOUR_OF_DAY);
    }
    // end-of getDateHourOfDay()

    //==== Date Input Methods END ==== >
    //==== Date Input Methods END ==== >
    //==== Date Input Methods END ==== >

	/**
	* conver To date object
    * input format mm/dd/yyyy
	*/
	public Date convertToDate(String aszIn){
        int iRetCode=0;
        Date aDateObj=null;
        if(null == aszIn) return null;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return null;
        if(null == m_shortDate){
            m_shortDate = DateFormat.getDateInstance(DateFormat.SHORT,m_Locale);
            m_shortDate.setCalendar(m_GregCalendar);
        }
		try {
			aDateObj=m_shortDate.parse(aszIn);
		} catch (ParseException  ex) {
			aDateObj=null;
		}
        return aDateObj;
    }
    // end-of convertToDate()


	/**
	* formats date object passed in according to specified date and time formats.
	* The contants NO_DATE and NO_TIME can be specified to suppress the printing
	* of the date portion or time portion respectively. These constants as well as
	* the real formatting constants are contained as static ints in this class.
	*/
	public String format(Date d,int dateFormat,int timeFormat){
		int iRetCode=0;
		String dateStr = null;
		String timeStr = null;

		// no nulls allowed
		if (d == null) return "";

		iRetCode = buildCalendar();
		if(iRetCode != 0) return "";

		switch (dateFormat) {
			case NO_DATE:
				dateStr = "";
				break;
			case SHORT2:  // mm/dd/yyyy
				if(null == m_shortDate2){
					m_shortDate2 = new java.text.SimpleDateFormat("MM/dd/yyyy");
			    	// m_shortDate2 = DateFormat.getDateInstance(DateFormat.SHORT,m_Locale);
			    	m_shortDate2.setCalendar(m_GregCalendar);
				}
				dateStr = m_shortDate2.format(d);
				break;
			case SHORT:  // mm/dd/yy
				if(null == m_shortDate){
					m_shortDate = DateFormat.getDateInstance(DateFormat.SHORT,m_Locale);
					m_shortDate.setCalendar(m_GregCalendar);
				}
				dateStr = m_shortDate.format(d);
				break;
			case MEDIUM: // dd-Month-yy
				if(null == m_medDate){
					m_medDate = DateFormat.getDateInstance(DateFormat.MEDIUM,m_Locale);
					m_medDate.setCalendar(m_GregCalendar);
				}
				dateStr = m_medDate.format(d);
				break;
			case LONG:  // month dd, yyyy
				if(null == m_longDate){
					m_longDate = DateFormat.getDateInstance(DateFormat.LONG,m_Locale);
					m_longDate.setCalendar(m_GregCalendar);
				}
				dateStr = m_longDate.format(d);
				break;
			case FULL:  // DayOfWeek, Month dd, yyyy
				if(null == m_fullDate){
					m_fullDate = DateFormat.getDateInstance(DateFormat.FULL,m_Locale);
					m_fullDate.setCalendar(m_GregCalendar);
				}
				dateStr = m_fullDate.format(d);
				break;
			case CUSTOM_DATE_2:  // return mmddyyyy format for ABRE Customer Date-on-invoice
                Calendar aCalendar= new GregorianCalendar(m_Locale);
                aCalendar.setTime(d);
                int iYear = aCalendar.get(Calendar.YEAR);
                int iMonth = aCalendar.get(Calendar.MONTH) + 1;
                String aszMonth=null;
                if(iMonth < 10){
                    aszMonth = "0" + iMonth;
                } else {
                    aszMonth = "" + iMonth;
                }
                int iDay = aCalendar.get(Calendar.DAY_OF_MONTH);
                String aszDay=null;
                if(iDay < 10){
                    aszDay = "0" + iDay;
                } else {
                    aszDay = "" + iDay;
                }
				dateStr = aszMonth + aszDay + iYear;
				break;
			case CUSTOM_DATE_1:
				if (m_customDate_1 != null)
					dateStr = m_customDate_1.format(d);
				else {
					dateStr = "";
					}
				break;
			default:
				return "";
			}

		switch (timeFormat) {
			case NO_TIME:
				timeStr = "";
				break;
			case SHORT:
				if(null == m_shortTime){
					m_shortTime = DateFormat.getTimeInstance(DateFormat.SHORT,m_Locale);
					m_shortTime.setCalendar(m_GregCalendar);
				}
				timeStr = m_shortTime.format(d);
				break;
			case MEDIUM:
				if(null == m_medTime){
					m_medTime = DateFormat.getTimeInstance(DateFormat.MEDIUM,m_Locale);
					m_medTime.setCalendar(m_GregCalendar);
				}
				timeStr = m_medTime.format(d);
				break;
			case LONG:
				if(null == m_longTime){
					m_longTime = DateFormat.getTimeInstance(DateFormat.LONG,m_Locale);
					m_longTime.setCalendar(m_GregCalendar);
				}
				timeStr = m_longTime.format(d);
				break;
			case FULL:
				if(null == m_fullTime){
					m_fullTime = DateFormat.getTimeInstance(DateFormat.FULL,m_Locale);
					m_fullTime.setCalendar(m_GregCalendar);
				}
				timeStr = m_fullTime.format(d);
				break;
			default:
				return "";
			}

		return dateStr + ((timeFormat == NO_TIME||dateFormat == NO_DATE) ? "" : " ") + timeStr;
	}
    // end-of method format()

	/**
	* input date is in format mmddyyyy
	*/
	public boolean isDayToday(String aszDate){
		String aszMonth=null,aszDay=null,aszTempDate=null,aszYear=null;
		Calendar cToday = Calendar.getInstance();
		int iMonth=0,iDay=0,iYear=0;
		int iInMonth=0,iInYear=0,iInDay=0;
		Integer aIntObj=null;
		aszTempDate = aszDate.trim();
		aszMonth = aszTempDate.substring(0,2);
		aszTempDate = aszTempDate.substring(2);
		aszDay = aszTempDate.substring(0,2);
		aszYear = aszTempDate.substring(2);
		iDay = cToday.get(Calendar.DAY_OF_MONTH);
		iMonth = cToday.get(Calendar.MONTH) + 1;
		iYear = cToday.get(Calendar.YEAR);
		try {
			aIntObj=Integer.valueOf(aszYear);
			iInYear=aIntObj.intValue();
		} catch (NumberFormatException  ex) {
			iInYear=0;
		}
		if(iInYear < 1){
			return false;
		}
		if(iInYear != iYear){
			return false;
		}
		try {
			aIntObj=Integer.valueOf(aszMonth);
			iInMonth=aIntObj.intValue();
		} catch (NumberFormatException  ex) {
			iInMonth=0;
		}
		if(iInMonth < 1){
			return false;
		}
		if(iInMonth != iMonth){
			return false;
		}
		try {
			aIntObj=Integer.valueOf(aszDay);
			iInDay=aIntObj.intValue();
		} catch (NumberFormatException  ex) {
			iInDay=0;
		}
		if(iInDay < 1){
			return false;
		}
		if(iInDay != iDay){
			return false;
		}
		return true;
	}
    // end-of isDayToday()

	/**
	* set the country
	*/
	public void setCountry(String country){
		if(null == country){
			m_aszCountry=null;
			clearAllInternal();
			return;
		}
		if(null != m_aszCountry){
			if(m_aszCountry.equalsIgnoreCase(country)) return;
		}
		m_aszCountry=country.trim();
		clearAllInternal();
	}
    // end-of setCountry()

	/**
	* set the language
	*/
	public void setLanguage(String lan){
		if(null == lan){
			m_aszLanguage=null;
			clearAllInternal();
			return;
		}
		if(null != m_aszLanguage){
			if(m_aszLanguage.equalsIgnoreCase(lan)) return;
		}
		m_aszLanguage=lan.trim();
		clearAllInternal();
	}
    // end-of setLanguage()

	/**
	* get date format mm/dd/yyyy
    * from input short month name and year to digit
    * input example Jan02 will return 01/01/2002
	*/
	public String getStringDateFromShortMonthYear(String indate){
		Integer intObj=null;
        int iMonth=0,iYear=0;
        String aszMonth=null;
        String aszYear=null;
        if(null == indate) return null;
        if(indate.length() < 5) return null;
        aszMonth = indate.substring(0,3);
        iMonth = getMonthNumberShort(aszMonth);
        if(iMonth < 1) return null;
        if(iMonth < 10){
            aszMonth="0" + iMonth;
        } else {
            aszMonth=""+iMonth;
        }
        aszYear = indate.substring(3);
        if(null == aszYear) return null;
        if(aszYear.length() < 2) return null;
		try {
			intObj = Integer.valueOf(aszYear);
			iYear=intObj.intValue();
		} catch (NumberFormatException  ex){
			return null;
		}
        if(iYear < 0) return null;
        if(iYear < 10){
            aszYear = "200" + iYear;
        } else {
            aszYear = "20" + iYear;
        }
        return aszMonth + "/01/" + aszYear;
	}
    // end-of getStringDateFromShortMonthYear()

	/**
	* get month number from short month name
    * example: Jan=1, Feb=2, Mar=3, Apr=4
	*/
	public int getMonthNumberShort(String indate){
        if(null == indate) return -1;
        if(indate.length() < 3) return -1;
        if(indate.equalsIgnoreCase("Jan")){
            return 1;
        } else if(indate.equalsIgnoreCase("Feb")){
            return 2;
        } else if(indate.equalsIgnoreCase("Feb")){
            return 2;
        } else if(indate.equalsIgnoreCase("Mar")){
            return 3;
        } else if(indate.equalsIgnoreCase("Apr")){
            return 4;
        } else if(indate.equalsIgnoreCase("May")){
            return 5;
        } else if(indate.equalsIgnoreCase("Jun")){
            return 6;
        } else if(indate.equalsIgnoreCase("Jul")){
            return 7;
        } else if(indate.equalsIgnoreCase("Aug")){
            return 8;
        } else if(indate.equalsIgnoreCase("Sep")){
            return 9;
        } else if(indate.equalsIgnoreCase("Oct")){
            return 10;
        } else if(indate.equalsIgnoreCase("Nov")){
            return 11;
        } else if(indate.equalsIgnoreCase("Dec")){
            return 12;
        }
        return -1;
    }
    // end-of getMonthNumberShort()

	/**
	* get month input is a number from 1 to 12
    * return month name 1=january
	*/
	public String getMonth(int mon){
		int iRetCode = buildCalendar();
		if(iRetCode != 0) return "";
		if(mon < 1) return "";
		if(mon > 12) return "";
		iRetCode = mon -1;
		String aMDay = m_MonthsOfYear[iRetCode];
		return aMDay;
	}
    // end-of getMonth()

	/**
	* get hour of day
	*/
	public String getHourOfDay(int hour){
        int iTemp=0;
		if(hour < 1) return "0 a.m.";
		if(hour < 12) return ("" + hour + " a.m.");
		if(hour == 12) return "noon";
		if(hour < 24){
            iTemp = hour - 12;
            return ("" + iTemp + " p.m.");
        }
        return "0 a.m.";
    }
    // end-of getHourOfDay()

	/**
	* get day of week month 1=jan
	*/
	public String getDayOfWeek(int year,int month,int day){
		int iNewMonth,iNewDay,iRetCode,iDayofWeek;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return "";
		if(month < 1) return "";
		if(month > 12) return "";
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayofWeek = m_GregCalendar.get(m_GregCalendar.DAY_OF_WEEK);
		if(iDayofWeek < 1) return "";
		if(iDayofWeek > 7) return "";
		return m_DaysOfWeek[iDayofWeek];
	}
    // end-of getDayOfWeek(

	/**
	* get day of week month 1=jan
	*/
	public int getDayOfWeekInt(int year,int month,int day){
		int iNewMonth,iNewDay,iRetCode,iDayofWeek;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return 0;
		if(month < 1) return 0;
		if(month > 12) return 0;
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayofWeek = m_GregCalendar.get(m_GregCalendar.DAY_OF_WEEK);
		if(iDayofWeek < 1) return 0;
		if(iDayofWeek > 7) return 0;
		return iDayofWeek;
	}
    // end-of getDayOfWeekInt(

	/**
	* get of week of month where 1=jan
	*/
	public int getWeekOfMonth(int year,int month,int day){
		int iNewMonth,iNewDay,iRetCode,iDayofWeek;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return 0;
		if(month < 1) return 0;
		if(month > 12) return 0;
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayofWeek = m_GregCalendar.get(m_GregCalendar.WEEK_OF_MONTH);
		if(iDayofWeek < 1) return 0;
		if(iDayofWeek > 7) return 0;
		return iDayofWeek;
	}
    // end-of getWeekOfMonth(

	/**
	* get of week of year wher month 1=jan
	*/
	public int getWeekOfYear(int year,int month,int day){
		int iNewMonth,iNewDay,iRetCode,iDayofWeek;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return 0;
		if(month < 1) return 0;
		if(month > 12) return 0;
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayofWeek = m_GregCalendar.get(m_GregCalendar.WEEK_OF_YEAR);
		if(iDayofWeek < 1) return 0;
		if(iDayofWeek > 54) return 0;
		return iDayofWeek;
	}
    // end-of getWeekOfYear(

	/**
	* get of day of year wher month 1=jan
	*/
	public int getDayOfYear(int year,int month,int day){
		int iNewMonth,iNewDay,iRetCode,iDayofWeek;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return 0;
		if(month < 1) return 0;
		if(month > 12) return 0;
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayofWeek = m_GregCalendar.get(m_GregCalendar.DAY_OF_YEAR);
		if(iDayofWeek < 1) return 0;
		if(iDayofWeek > 369) return 0;
		return iDayofWeek;
	}
    // end-of getDayOfYear(


	/**
	* get int yyyy+day of year month 1=jan
    * julian date ?
	*/
	public int getYearPlusDayofYear(int year,int month,int day){
		Integer intObj=null;
		int iNewMonth,iNewDay,iRetCode,iDayOfYear;
        String aszDayOfYear=null;
        int iNewFullYearDay=-1;
		iRetCode = buildCalendar();
		if(iRetCode != 0) return -1;
		if(month < 1) return -1;
		if(month > 12) return -1;
		if(day < 1){
			iNewDay=1;
		} else if(day > 31){
			iNewDay=1;
		} else {
			iNewDay=day;
		}
		iNewMonth = month -1;
		m_GregCalendar.set(year,iNewMonth,iNewDay);
		iDayOfYear = m_GregCalendar.get(GregorianCalendar.DAY_OF_YEAR);
        if(iDayOfYear < 10){
            aszDayOfYear = "00" + iDayOfYear;
        } else if(iDayOfYear < 100){
            aszDayOfYear = "0" + iDayOfYear;
        } else {
            aszDayOfYear = "" + iDayOfYear;
        }
        String fullyearday = "" + year + aszDayOfYear;
		try {
			intObj = Integer.valueOf(fullyearday);
			iNewFullYearDay = intObj.intValue();
		} catch (NumberFormatException  ex){
			iNewFullYearDay=-1;
		}
		return iNewFullYearDay;
	}
    // end-of getYearPlusDayofYear()

    /**
    * 
    */
	public String getDateFormated(String AszLanguage){
		String AszCountry="USA";
		String aCurrDateTime=null;
		if(AszLanguage == "en"){
			AszCountry="USA";
		} else if(AszLanguage == "es"){
			AszCountry="ECU";
		}
		Locale aLoc = new Locale(AszLanguage,AszCountry);
		DateFormat aSimPleDateFormat = DateFormat.getDateInstance(DateFormat.LONG,aLoc);
		aCurrDateTime = aSimPleDateFormat.format(new Date());
		return aCurrDateTime;
	}
    // end-of getDateFormated()

    /**
    * return current date with time
    */
	public String getDateTimeFormated(){
		return format(new Date(),FULL,SHORT);
	}
    // end-of getDateTimeFormated()

    /**
    * return current date without time
    */
	public String getDateFullOnlyFormated(){
		return format(new Date(),FULL,NO_TIME);
	}
    // end-of getDateOnlyFormated()

    /**
    *  For HU Press Format date
    *  input mmddyyyy return mm/dd/yyyy
    */
	public String convertToHUPDate(String indate){
        if(null == indate) return "";
        if(indate.length() < 6) return indate;
        String mm = indate.substring(0,2);
        String dd = indate.substring(2,4);
        String yy=null;
        if(indate.length() < 8){
            yy = indate.substring(4,6);        
        } else {
            yy = indate.substring(4,8);
        }
        return mm + "/" + dd + "/" + yy;
    }
    // end-of convertToHUPDate()

    /**
    *  For HU Press Format date
    *  input mmddyyyy return mm/yy
    */
	public String convertToHUPDate2(String indate){
        if(null == indate) return "";
        if(indate.length() < 6) return indate;
        String mm = indate.substring(0,2);
        String dd = indate.substring(2,4);
        String yy=null;
        if(indate.length() < 8){
            yy = indate.substring(4,6);        
        } else {
            yy = indate.substring(6,8);
        }
        return mm + "/" + yy;
    }
    // end-of convertToHUPDate2()

    /**
    *  For HU Press Format date
    *  input mm/dd/yyyy return mmddyyyy
    */
	public String convertToHUPDate3(String indate){
		String aszDate=null;
		char aChar;
		int iLen=0,iIndex=0;
		StringBuffer aszBuff=null;
		if(null == indate){
			return "";
		}
		aszDate = indate.trim();
		iLen = aszDate.length();
		aszBuff = new StringBuffer(iLen + 1);
		for(iIndex=0;iIndex < iLen; iIndex++){
			aChar = aszDate.charAt(iIndex);
			if('/' != aChar){
				aszBuff.append(aChar);
			}
		}
		aszDate = aszBuff.toString();
		return aszDate;
    }
    // end-of convertToHUPDate3()

    /**
    *  input mm/dd/yyyy return month
    */
	public int extractMonth(String indate){
		Integer aIntObj=null;
		String aszDate=null;
		int iLen=0;
        int iMonth=-1;
		if(null == indate){
			return -1;
		}
		aszDate = indate.trim();
		iLen = aszDate.length();
        if(indate.length() < 10) return -1;
        String mm = indate.substring(0,2);
		try {
			aIntObj = Integer.valueOf(mm);
			iMonth = aIntObj.intValue();
		} catch (NumberFormatException  ex){
			iMonth=-1;
		}
		return iMonth;
    }
    // end-of extractMonth()

    /**
    *  input mm/dd/yyyy return year
    */
	public int extractYear(String indate){
		Integer aIntObj=null;
		String aszDate=null;
		int iLen=0;
        int iYear=-1;
		if(null == indate){
			return -1;
		}
		aszDate = indate.trim();
		iLen = aszDate.length();
        if(indate.length() < 10) return -1;
        String aszYear = indate.substring(6,10);
		try {
			aIntObj = Integer.valueOf(aszYear);
			iYear = aIntObj.intValue();
		} catch (NumberFormatException  ex){
			iYear=-1;
		}
		return iYear;
    }
    // end-of extractYear()

    /**
    *  input mm/dd/yyyy return day
    */
	public int extractDay(String indate){
		Integer aIntObj=null;
		String aszDate=null;
		int iLen=0;
        int iDay=-1;
		if(null == indate){
			return -1;
		}
		aszDate = indate.trim();
		iLen = aszDate.length();
        if(indate.length() < 10) return -1;
        String aszDay = indate.substring(3,5);
		try {
			aIntObj = Integer.valueOf(aszDay);
			iDay = aIntObj.intValue();
		} catch (NumberFormatException  ex){
			iDay=-1;
		}
		return iDay;
    }
    // end-of extractDay()

	public void clearAll(){
		clearAllInternal();
	}
    // end-of clearAll()


    //======================> Private Methods follow
    //======================> Private Methods follow
    //======================> Private Methods follow

	private void clearAllInternal(){
		m_Locale=null;
		m_DateFmtSym=null;
		m_GregCalendar=null;
		m_DaysOfWeek=null;
		m_MonthsOfYear=null;
	}
    // end-of clearAllInternal()

	private int buildCalendar(){
		if(null == m_aszCountry){
			return 1;
		}
		if(null == m_aszLanguage){
			return 1;
		}
		if(null == m_Locale){
			m_Locale = new Locale(m_aszLanguage,m_aszCountry);
		}
		if(null == m_DateFmtSym){
			m_DateFmtSym = new DateFormatSymbols (m_Locale);
		}
		if(null == m_GregCalendar){
			m_GregCalendar = new GregorianCalendar (m_Locale);
		}
		if(null == m_DaysOfWeek){
			m_DaysOfWeek = m_DateFmtSym.getWeekdays();
		}
		if(null == m_MonthsOfYear){
			m_MonthsOfYear = m_DateFmtSym.getMonths();
		}
		return 0;
	}
    // end-of buildCalendar()

    //====================> Private Class Variables
    //====================> Private Class Variables
    //====================> Private Class Variables

	private String m_aszCountry="US";
	private String m_aszLanguage="en";
	private DateFormatSymbols m_DateFmtSym=null;
	private Locale m_Locale=null;
	private GregorianCalendar m_GregCalendar=null;
	private String[] m_DaysOfWeek=null;
	private String[] m_MonthsOfYear=null;

	// Time formatters
	private DateFormat m_fullTime;
	private DateFormat m_longTime;
	private DateFormat m_medTime;
	private DateFormat m_shortTime;

	// Date Formaters
	private DateFormat m_fullDate;
	private DateFormat m_longDate;
	private DateFormat m_medDate;
	private DateFormat m_shortDate;
	private DateFormat m_shortDate2=null;
	private DateFormat m_customDate_1;


}
