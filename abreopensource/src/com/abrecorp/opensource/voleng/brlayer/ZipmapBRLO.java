package com.abrecorp.opensource.voleng.brlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abrecorp.opensource.base.ABREDBConnection;

public class ZipmapBRLO extends BaseVolEngBRLO {
	public ArrayList<String[]> getZipCodes() {
		if(zipCodes != null){
			return zipCodes;
		}
		zipCodes = new ArrayList(1);
		ABREDBConnection db = this.getDBConn();
		db.getDBStmt();
		db.RunQuery("SELECT zip, latitude, longitude FROM " + aszDrupalDB + "zipcodes;");
//		List<String> zipCodes = new ArrayList<String>();
//		List<String> zipCodes = new LinkedList<String>();
		while(db.getNextResult()) {
	        String[] arrayElement = new String[3];
			arrayElement[0]=(db.getDBString("zip"));
			arrayElement[1]=(db.getDBString("latitude"));
			arrayElement[2]=(db.getDBString("longitude"));
/*
 * 			arrayElement[0]="zip";
			arrayElement[1]=(db.getDBString("zip"));
			arrayElement[2]="latitude";
			arrayElement[3]=(db.getDBString("latitude"));
			arrayElement[4]="logitude";
			arrayElement[5]=(db.getDBString("longitude"));

 */
			zipCodes.add(arrayElement);
		}
		return zipCodes;
	}
	private static final String aszDrupalDB = "um_";
    public ArrayList<String[]> zipCodes;
}
