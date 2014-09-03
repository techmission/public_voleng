package com.abrecorp.opensource.struts;

/**
* System:       Struts Action Layer
* Title:        Base Struts Actions
* Description:  User Interface Actions
* Copyright:    Copyright (c) 1997-2006
* Company:      ABRE Technology Corporation
* @author       C. David Marcillo
* @version      1.0
*/



import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

import com.abrecorp.opensource.base.ABREAppServer;



/**
* @author Amit Gupta
* @Web http://www.roseindia.net
* @Email struts@roseindia.net
*/

/**
 * Form bean for Struts File Upload.
 *
*/
public class StrutsUploadAndSaveForm extends ActionForm
{
  private FormFile theFile;

  /**
   * @return Returns the theFile.
   */
  public FormFile getTheFile() {
    return theFile;
  }
  /**
   * @param theFile The FormFile to set.
   */
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  

	/**
  * access to application wide services
  */
  public void setBaseAppRef(ABREAppServer aApp){
      if(null == aApp ) return ;
      if(null == m_ABREAppServerObj){
          m_ABREAppServerObj = aApp;
      }
  }
  // end-of method setBaseAppRef()

  private ABREAppServer m_ABREAppServerObj=null;

} 