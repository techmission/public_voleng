package com.abrecorp.opensource.javamail;

/**
* System:       Java Send Email Object
* Description:  EMail Base Class
* Copyright:    Copyright (c) 1998-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
* converted to JavaMail 1.4 Marcillo 7/20/2006
*/

import com.abrecorp.opensource.base.ABREBase;
import com.abrecorp.opensource.dataobj.EmailInfoDTO;
import com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO;
import com.abrecorp.opensource.dataobj.PersonInfoDTO;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.*;
// import java.io.*; 
import java.util.*;

public class ABREJavaSendMail extends ABREBase {

	private String m_mailername = "abre-msg-send";

	/**
	* Constructor
	*/
	public ABREJavaSendMail(){}

	/**
	* check email format
	*/
    public int checkEmailFormat( String emailin ){
        int iRetCode=0;
        InternetAddress aTmpAddr=null;
        if(null == emailin) return -1;
        emailin = emailin.trim();
        if(emailin.length() < 5) return -1;
    	try {
    		aTmpAddr = new InternetAddress(emailin);
    		aTmpAddr.validate();
        	iRetCode=0;
        	emailin = aTmpAddr.getType();
    	} catch ( AddressException mex) {
    		iRetCode=-1;
    	}
        return iRetCode;
    }
    // end-of method checkEmailFormat()

	/**
	* send email message
	*/
    public int sendEmailMessage( EmailInfoDTO aHeadObj ){
    	return sendEmailMessage( aHeadObj, 0);
    }
    public int sendEmailMessage( EmailInfoDTO aHeadObj, int iType ){
        int iRetCode=0;
		MethodInit("sendEmailMessage");
        if(null == aHeadObj){
			setErr("ERROR: email message object required");
System.out.println("ERROR: email message object required");			
            return -1;
        }
        String smtpserver = aHeadObj.getEmailSMTPServerName();
        
        if(smtpserver.length() < 2){
			setErr("ERROR: SMTP server required");
			System.out.println("ERROR: SMTP server required");			
            return -1;
        }
        String from = aHeadObj.getEmailFromUser();
        if(from.length() < 5){
			setErr("ERROR: From user required");
			System.out.println("ERROR: From user required");			
            return -1;
        }
        String msgSubject = aHeadObj.getEmailSubjectText();
        if(msgSubject.length() < 5){
			setErr("ERROR: Subject required");
			System.out.println("ERROR: Subject required");			
            return -1;
        }
        if(aHeadObj.getSMTPAuthRequired()){
            String aszPassword = aHeadObj.getEmailFromUserPassword();
            if(aszPassword.length() < 3){
    			setErr("ERROR: SMTP password required");
    			System.out.println("ERROR: SMTP password required");			
                return -1;
            }
            String aszUserName = aHeadObj.getSMTPUserName();
            if(aszUserName.length() < 3){
    			setErr("ERROR: SMTP username required");
    			System.out.println("ERROR: SMTP username required");			
                return -1;
            }
        }
        switch( aHeadObj.getEmailMessageType() ){
        	case EmailInfoDTO.SINGLE_USER_TEXT_MESSAGE:
                //
        		String msgTextIntro = aHeadObj.getEmailBodyTextIntro();
        		String msgText = "";// commented out b/c it's integrated into the emailbodytextintro now aHeadObj.getEmailBodyText();
        		String msgTextClosing = aHeadObj.getEmailBodyTextClosing();
        		
                if(msgTextIntro.length() < 2){
                	if (msgText.length() < 2){
                		if (msgTextClosing.length() < 2){
                			setErr("ERROR: Be sure to include a message");
                			System.out.println("ERROR: Be sure to include a message");			
                            return -1;
                		}
                	}
                }
                //
                String to = aHeadObj.getEmailToUser();
                if(to.length() < 5){
        			setErr("ERROR: Email to required");
        			System.out.println("ERROR: Email to required");			
                    return -1;
                }
                if(iType==EmailInfoDTO.TOKEN_HTML){
                	iRetCode = sendEmailTextMessage( aHeadObj, EmailInfoDTO.TOKEN_HTML );
                }else{
                	iRetCode = sendEmailTextMessage( aHeadObj );
                }
        		break;
	        default:
				setErr("ERROR: Message type not implemented");
				System.out.println("ERROR: Message type not implemented");			
	        	iRetCode=-1;
	        	break;
        }
        return iRetCode;
    }
    // end-of method sendEmailMessage()

    //============ Private Methods Follow ===>
    //============ Private Methods Follow ===>
    //============ Private Methods Follow ===>


	/**
	* send email text message
	* converted to JavaMail 1.4 Marcillo 7/20/2006
	*/
    private int sendEmailTextMessage( EmailInfoDTO aHeadObj ){
    	return sendEmailTextMessage(aHeadObj,0);
    }
    private int sendEmailTextMessage( EmailInfoDTO aHeadObj, int iType ){
    	String fromName=null,from=null,to=null,bcc=null,msgSubject=null,msgText=null,smtpserver=null,aszPassword=null,smtusername=null;
        String aszTemp=null;
        Properties aMailProps=null;
        Message msg=null;
        Session session=null;
        SMTPTransport aTranspObj=null;
        int iRetCode=0;
		MethodInit("sendEmailTextMessage");
        if(null == aHeadObj){
			setErr("ERROR: Email Message object required");
            return -1;
        }
        smtpserver = aHeadObj.getEmailSMTPServerName();
        smtusername = aHeadObj.getSMTPUserName();
        to = aHeadObj.getEmailToUser();
        bcc = aHeadObj.getEmailBCCAddress();
        from = aHeadObj.getEmailFromUser();
        fromName = aHeadObj.getEmailFromUserName();
        if(fromName.length()<1)		fromName=from;
        msgSubject = aHeadObj.getEmailSubjectText();
        aszPassword = aHeadObj.getEmailFromUserPassword();
        msgText = aHeadObj.getEmailBodyTextIntro() + 
        	"\n" + aHeadObj.getEmailBodyText() +
        	"\n" + aHeadObj.getEmailBodyTextClosing();
if(aszPassword.length()>0){
	System.out.println(" smtp password is set");
}

        // aMailProps = new Properties();
        aMailProps = System.getProperties();
        aMailProps.put("mail.smtp.host", smtpserver );
        // aMailProps.put("mail.smtp.user", from );
		aMailProps.put("mail.smtp.auth", "true");
        // session = Session.getDefaultInstance(aMailProps, null);
        session = Session.getInstance(aMailProps, null);
        if(null == session){
			setErr("ERROR: Email session create error");
            return -1;
        }
	    if (aHeadObj.getEmailDebug()) session.setDebug(true);

	    
	    
/*    
// block of code to test if the InternetAddress is valid, since in local testing, the catch block on setting msg kicks out of the method
	    Address[] arg1;
	    try{
	    	arg1 = InternetAddress.parse(to, false);
	    	String aszAli = "";
	    } catch (MessagingException mex) {
            iRetCode=-1;
            System.out.println("\n--ERROR: Exception handling in method sendEmailTextMessage() ABREJavaSendMail.java");
            if(null != mex){
    			setErr( mex.getMessage() );
                mex.printStackTrace();
                Exception ex = mex;
                do {
        		if (ex instanceof SendFailedException) {
                    SendFailedException sfex = (SendFailedException)ex;
                    Address[] invalid = sfex.getInvalidAddresses();
                    if (invalid != null) {
                        if (invalid != null) {
                            for (int i = 0; i < invalid.length; i++) 
                                setErr( "ERROR: Invalid Address: " + invalid[i] );
                        }
                    }
        		    Address[] validUnsent = sfex.getValidUnsentAddresses();
        		    if (validUnsent != null) {
                        if (validUnsent != null) {
        			    for (int i = 0; i < validUnsent.length; i++) 
                            setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                        }
                    }
                    Address[] validSent = sfex.getValidSentAddresses();
                    if (validSent != null) {
                        if (validSent != null) {
                            for (int i = 0; i < validSent.length; i++) 
                                setErr( "ERROR: ValidSent Address: " + validSent[i] );
                        }
                    }
                }
        		else if (ex instanceof MessagingException)
                    ex = ((MessagingException)ex).getNextException();
                else
                    ex = null;
                } while (ex != null);
            }
        } // end of try-catch
*/
	    
	    
	    // create a message 
    	try {
    		msg = new MimeMessage(session);
    	} catch (java.lang.NoClassDefFoundError mex) {
			System.out.println("236 ERROR: Error creating MimeMessage()");
			setErr("ERROR: Error creating MimeMessage()");
			setErr(mex.getMessage());
            return -1;
    	}
	    // create a message
    	try {
    		InternetAddress fromInternetAddress = null;
    		try{
    			fromInternetAddress = new InternetAddress(from, fromName);
    		}catch(Exception e){
    			fromInternetAddress = new InternetAddress(from);
    		}
    	    msg.setFrom(fromInternetAddress);
    	    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false)); // ADD MULTIPLE CONTACTS HERE!!!
    	    if (bcc != null) {
        	    msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
System.out.println("AbreJavaSendMail line 259 - BCC recipients are: "+bcc);            
    	    }
            msg.setSubject(msgSubject);
    	    msg.setHeader("X-Mailer", m_mailername);
    	    msg.setSentDate(new Date());
    	    // If the desired charset is known, you can use setText(text, charset)
            if(iType==EmailInfoDTO.TOKEN_HTML){
                msg.setContent(msgText, "text/html; charset=utf-8");
            }else{
                msg.setText( msgText );
            }
            
System.out.println("AbreJavaSendMail line 271 - subject is: "+msgSubject);            
System.out.println("AbreJavaSendMail line 272 - recipients are: "+to);            
System.out.println("AbreJavaSendMail line 273 - sender is: "+from+";    "+fromName);            
    	} catch (MessagingException mex) {
            iRetCode=-1;
            System.out.println("\n--ERROR: Exception handling in method sendEmailTextMessage() ABREJavaSendMail.java");
            if(null != mex){
    			setErr( mex.getMessage() );
                mex.printStackTrace();
                Exception ex = mex;
                do {
        		if (ex instanceof SendFailedException) {
                    SendFailedException sfex = (SendFailedException)ex;
                    Address[] invalid = sfex.getInvalidAddresses();
                    if (invalid != null) {
                        if (invalid != null) {
                            for (int i = 0; i < invalid.length; i++) 
                                setErr( "ERROR: Invalid Address: " + invalid[i] );
                        }
                    }
        		    Address[] validUnsent = sfex.getValidUnsentAddresses();
        		    if (validUnsent != null) {
                        if (validUnsent != null) {
        			    for (int i = 0; i < validUnsent.length; i++) 
                            setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                        }
                    }
                    Address[] validSent = sfex.getValidSentAddresses();
                    if (validSent != null) {
                        if (validSent != null) {
                            for (int i = 0; i < validSent.length; i++) 
                                setErr( "ERROR: ValidSent Address: " + validSent[i] );
                        }
                    }
                }
        		else if (ex instanceof MessagingException)
                    ex = ((MessagingException)ex).getNextException();
                else
                    ex = null;
                } while (ex != null);
            }
        } // end of try-catch

        if(0 != iRetCode) return iRetCode;

        // send email message
    	try {
            aTranspObj = (SMTPTransport)session.getTransport("smtp");
            if(aHeadObj.getSMTPAuthRequired()){
                aTranspObj.connect( smtpserver, smtusername, aszPassword);
System.out.println("298 SMTP Connect with auth info");            	
            } else {
System.out.println("SMTP Connect withOUT auth info");            	
                aTranspObj.connect();
            }
            iRetCode=0;
    	} catch (MessagingException mex) {
    		System.out.println( "304 ERROR: SMTP connect error" );
    		setErr( "ERROR: SMTP connect error" );
            iRetCode=-1;
            Exception ex=null;
			if(null != mex){
				aszTemp = mex.getMessage();
				if(null != aszTemp) setErr( aszTemp );
    			mex.printStackTrace();
                ex = mex;
        		System.out.println( "315 - exception ex is "+ex );
			}
			/*
            if(null != aTranspObj ){
				aszTemp = mex.getMessage();
				if(null != aszTemp){
	            	setErr("Response: " + aszTemp);
				}
            }
            */
            do {
    		if (ex instanceof SendFailedException) {
                SendFailedException sfex = (SendFailedException)ex;
                Address[] invalid = sfex.getInvalidAddresses();
                if (invalid != null) {
                    if (invalid != null) {
                        for (int i = 0; i < invalid.length; i++) {
                            setErr( "ERROR: Invalid Address: " + invalid[i] );
                            System.out.println( "331  ERROR: Invalid Address: " + invalid[i] );
                        }
                    }
                }
    		    Address[] validUnsent = sfex.getValidUnsentAddresses();
    		    if (validUnsent != null) {
                    if (validUnsent != null) {
        			    for (int i = 0; i < validUnsent.length; i++) {
                            setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                            System.out.println( "340  ERROR: ValidUnsent Address: " + validUnsent[i] );
        			    }
                    }
                }
                Address[] validSent = sfex.getValidSentAddresses();
                if (validSent != null) {
                    if (validSent != null) {
                        for (int i = 0; i < validSent.length; i++){ 
                            setErr( "ERROR: ValidSent Address: " + validSent[i] );
                            System.out.println( "349  ERROR: ValidSent Address: " + validSent[i] );
                        }
                    }
                }
            }
    		if (ex instanceof AuthenticationFailedException) {
    			System.out.println( "355  Authentication ERROR" );
        		setErr( "Authentication ERROR" );
                AuthenticationFailedException sfex = (AuthenticationFailedException)ex;
                if(null != sfex){
    				aszTemp = sfex.getMessage();
    				if(null != aszTemp) setErr( aszTemp );
                }
            }
            if (ex instanceof MessagingException){
                ex = ((MessagingException)ex).getNextException();
                if(null != ex){
    				aszTemp = ex.getMessage();
    				if(null != aszTemp) setErr( aszTemp );
                }
            }
            else ex = null;
            } while (ex != null);
        } // end of try-catch

    	if(0 != iRetCode) return iRetCode;

    	// send email message
    	try {
System.out.println("380 just before sendMessage()");    		
            aTranspObj.sendMessage(msg, msg.getAllRecipients());
System.out.println("382 just AFTER sendMessage()");    		
            aTranspObj.close();
    	} catch (MessagingException mex) {
            iRetCode=-1;
            Exception ex=null;
            if(null != aTranspObj ) {
            	setErr("ERROR: Response: " + aTranspObj.getLastServerResponse());
            	System.out.println("380 ERROR: Response: " + aTranspObj.getLastServerResponse());
            }
			if(null != mex){
				aszTemp = mex.getMessage();
				if(null != aszTemp) setErr( aszTemp );
    			mex.printStackTrace();
                ex = mex;
			}
            do {
    		if (ex instanceof SendFailedException) {
                SendFailedException sfex = (SendFailedException)ex;
                Address[] invalid = sfex.getInvalidAddresses();
                if (invalid != null) {
                    if (invalid != null) {
                        for (int i = 0; i < invalid.length; i++) {
                            setErr( "ERROR: Invalid Address: " + invalid[i] );
                            System.out.println( "396 ERROR: Invalid Address: " + invalid[i] );
                        }
                    }
                }
    		    Address[] validUnsent = sfex.getValidUnsentAddresses();
    		    if (validUnsent != null) {
                    if (validUnsent != null) {
        			    for (int i = 0; i < validUnsent.length; i++){ 
                            setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                            System.out.println( "405 ERROR: ValidUnsent Address: " + validUnsent[i] );
        			    }
                    }
                }
                Address[] validSent = sfex.getValidSentAddresses();
                if (validSent != null) {
                    if (validSent != null) {
                        for (int i = 0; i < validSent.length; i++) {
                            setErr( "ERROR: ValidSent Address: " + validSent[i] );
                            System.out.println( "414 ERROR: ValidSent Address: " + validSent[i] );
                        }
                    }
                }
            }
    		if (ex instanceof AuthenticationFailedException) {
                AuthenticationFailedException sfex = (AuthenticationFailedException)ex;
                if(null != sfex) {
                	setErr( "ERROR: AuthenticationFailedException: " + sfex.getMessage() );
                	System.out.println( "423 ERROR: AuthenticationFailedException: " + sfex.getMessage() );
                }
            }
            if (ex instanceof MessagingException){
                ex = ((MessagingException)ex).getNextException();
                if(null != ex) {
                	setErr( "ERROR: AuthenticationFailedException: " + ex.getMessage() );
                	System.out.println( "430 ERROR: AuthenticationFailedException: " + ex.getMessage() );
                }
            }
            else
                ex = null;
            } while (ex != null);
        } // end of try-catch

        return iRetCode;
    }
    // end-of emailTextInsideMessage()


	/**
	* send email text message and one attached file
	*/
    private int sendEmailAttachMessage( EmailInfoDTO aHeadObj ){
        String from=null,to=null,msgSubject=null,msgText=null,smtpserver=null,aszPassword=null,aszFileName=null;
        int iRetCode=0;
        Properties aMailProps=null;
        Message msg=null;
        Session session=null;
        SMTPTransport aTranspObj=null;
		MethodInit("sendEmailAttachMessage");
        if(null == aHeadObj){
			setErr("ERROR: email message object required");
            return 1;
        }
        smtpserver = aHeadObj.getEmailSMTPServerName();
        if(null == smtpserver) smtpserver="";
        smtpserver = smtpserver.trim();
        if(smtpserver.length() < 2){
			setErr("ERROR: email smtp server name required");
            return 1;
        }
        msgText = aHeadObj.getEmailBodyText();
        if(null == msgText) msgText="";
        msgText = msgText.trim();
        if(msgText.length() < 2){
			setErr("ERROR: email message required");
            return 1;
        }
        to = aHeadObj.getEmailToUser();
        if(null == to) to="";
        to = to.trim();
        if(to.length() < 5){
			setErr("ERROR: email to user required");
            return 1;
        }
        from = aHeadObj.getEmailFromUser();
        if(null == from) from="";
        from = from.trim();
        if(from.length() < 5){
			setErr("ERROR: email from user required");
            return 1;
        }
        msgSubject = aHeadObj.getEmailSubjectText();
        if(null == msgSubject) msgSubject="";
        msgSubject = msgSubject.trim();
        if(msgSubject.length() < 5){
			setErr("ERROR: email subject required");
            return 1;
        }
        aszPassword = aHeadObj.getEmailFromUserPassword();
        if(null == aszPassword) aszPassword="";
        aszPassword = aszPassword.trim();
        if(aszPassword.length() < 3){
			setErr("ERROR: smtp email user password required");
            return 1;
        }
        aszFileName = aHeadObj.getEmailAttachFileName();
        if(null == aszFileName) aszFileName="";
        aszFileName = aszFileName.trim();
        if(aszFileName.length() < 3){
			setErr("ERROR: attached file name required");
            return 1;
        }
        aMailProps = new Properties();
        aMailProps.put("mail.smtp.host", smtpserver );
        aMailProps.put("mail.smtp.user", from );
		aMailProps.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(aMailProps, null);
        // create a message
    	try {
            msg = new MimeMessage(session);
    	    msg.setFrom(new InternetAddress(from));
    	    InternetAddress[] address = {new InternetAddress(to)};
    	    msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(msgSubject);
    	    msg.setSentDate(new Date());
    	    // If the desired charset is known, you can use
            // setText(text, charset)
            // msg.setFileName(aszFullName);

            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(msgText);
            // create the second message part
            MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
       	    FileDataSource fds = new FileDataSource ( aszFileName );
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());

    	    // create the Multipart and its parts to it
            Multipart mp = new MimeMultipart();
    	    mp.addBodyPart(mbp1);
    	    mp.addBodyPart(mbp2);

            // add the Multipart to the message
    	    msg.setContent(mp);
    	} catch (MessagingException mex) {
            iRetCode=-1;
            // System.out.println("\n--Exception handling in msgsendsample.java");
            // mex.printStackTrace();
			setErr( mex.getMessage() );
            Exception ex = mex;
            do {
    		if (ex instanceof SendFailedException) {
                SendFailedException sfex = (SendFailedException)ex;
                Address[] invalid = sfex.getInvalidAddresses();
                if (invalid != null) {
                    if (invalid != null) {
                        for (int i = 0; i < invalid.length; i++) 
                            setErr( "ERROR: Invalid Address: " + invalid[i] );
                    }
                }
    		    Address[] validUnsent = sfex.getValidUnsentAddresses();
    		    if (validUnsent != null) {
                    if (validUnsent != null) {
    			    for (int i = 0; i < validUnsent.length; i++) 
                        setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                    }
                }
                Address[] validSent = sfex.getValidSentAddresses();
                if (validSent != null) {
                    if (validSent != null) {
                        for (int i = 0; i < validSent.length; i++) 
                            setErr( "ERROR: ValidSent Address: " + validSent[i] );
                    }
                }
            }
            // System.out.println();
            if (ex instanceof MessagingException)
                ex = ((MessagingException)ex).getNextException();
            else
                ex = null;
            } while (ex != null);
        } // end of try-catch

        if(0 != iRetCode) return iRetCode;
        // send email message
    	try {
            aTranspObj = (SMTPTransport)session.getTransport("smtp");
            aTranspObj.connect( smtpserver, from, aszPassword);
            // aTranspObj.send( msg );
            aTranspObj.sendMessage(msg, msg.getAllRecipients());
            aTranspObj.close();
    	} catch (MessagingException mex) {
            iRetCode=-1;
			setErr( mex.getMessage() );
            Exception ex = mex;
            do {
    		if (ex instanceof SendFailedException) {
                SendFailedException sfex = (SendFailedException)ex;
                Address[] invalid = sfex.getInvalidAddresses();
                if (invalid != null) {
                    if (invalid != null) {
                        for (int i = 0; i < invalid.length; i++) 
                            setErr( "ERROR: Invalid Address: " + invalid[i] );
                    }
                }
    		    Address[] validUnsent = sfex.getValidUnsentAddresses();
    		    if (validUnsent != null) {
                    if (validUnsent != null) {
        			    for (int i = 0; i < validUnsent.length; i++) 
                            setErr( "ERROR: ValidUnsent Address: " + validUnsent[i] );
                    }
                }
                Address[] validSent = sfex.getValidSentAddresses();
                if (validSent != null) {
                    if (validSent != null) {
                        for (int i = 0; i < validSent.length; i++) 
                            setErr( "ERROR: ValidSent Address: " + validSent[i] );
                    }
                }
            }
    		if (ex instanceof AuthenticationFailedException) {
                AuthenticationFailedException sfex = (AuthenticationFailedException)ex;
                if(null != sfex) setErr( "ERROR: AuthenticationFailedException: " + sfex.getMessage() );
            }
            if (ex instanceof MessagingException)
                ex = ((MessagingException)ex).getNextException();
            else
                ex = null;
            } while (ex != null);
        } // end of try-catch

    	return iRetCode;
    }
    // end-of sendEmailAttachMessage()


    //============ Private Variables Follow ===>
    //============ Private Variables Follow ===>
    //============ Private Variables Follow ===>



}
