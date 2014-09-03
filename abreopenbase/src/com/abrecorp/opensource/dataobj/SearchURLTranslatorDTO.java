package com.abrecorp.opensource.dataobj;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class SearchURLTranslatorDTO extends BaseInfoObj {
	private String url = "";
	private String apiKey = "";
	private String outputFormat = "";
	private String translatedURL = "";

	public String getURL() {
		return this.url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public String getAPIKey() {
		return this.apiKey;
	}
	
	public void setAPIKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void setOutputFormat(String format) {
		this.outputFormat = format;
	}
	
	public String getOutputFormat() {
		return this.outputFormat;
	}
	
	public String getTranslatedURL() {
		return translatedURL;
	}
	
	public void translate() {
		URL url = null;
		if(this.url == null || this.url.isEmpty()) {
			this.appendErrorMsg("<li>URL is required.</li>");
		}
		else {
			try {
				url = new URL(this.url);
			}
			catch(MalformedURLException e) {
				this.appendErrorMsg("<li>URL is invalid.</li>");
			}
		}
		if(this.outputFormat == null || this.outputFormat.isEmpty()) {
			this.appendErrorMsg("<li>Output format is required.</li>");
		}
		else if( !(this.outputFormat.equals("json")
	       || this.outputFormat.equals("xml")
	       || this.outputFormat.equals("rss")
	       || this.outputFormat.equals("kml")
	       || this.outputFormat.equals("csv"))) {
			this.appendErrorMsg("<li>Output format is invalid.</li>");
		}
		if(this.apiKey == null || this.apiKey.isEmpty()) {
			this.appendErrorMsg("<li>API key is required.</li>");
		}
		if(!this.getErrorMsg().isEmpty()) return;
		
		String query = url.getQuery();
		String[] chunks = query == null ? new String[] {} : query.split("#");
		List<String> params = new LinkedList<String>();
		for(String chunk : chunks)
			for(String p : chunk.split("&"))
				params.add(p);
		
		List<String> translatedParams = new LinkedList<String>();
		for(String param : params) {
			int i = param.indexOf('=');
			if(i <= 0) {
				this.appendErrorMsg("<li>Invalid parameter: " + param + "</li>");
				continue;
			}
			String key = param.substring(0, i);
			String value;
			if(i == param.length() - 1)
				value = "";
			else
				value = param.substring(i + 1, param.length());
			
			if(key.equals("fq")) {
				translatedParams.add("fq[]=" + value);
			}
			else if(key.equals("postal_code")
				 || key.equals("method")) {
				// Ignore these
			}
			else {
				this.appendErrorMsg("<li>Unkown parameter: " + key + "</li>");
			}
		}
		
		for(String param : new String[] {"q=*%3A*", "rows=999999999", "sort=tm_member_i%20desc%2C%20popularity%20desc", "wt=" + this.outputFormat, "apikey=" + this.apiKey})
			translatedParams.add(param);
		
		this.translatedURL = "http://www.christianvolunteering.org/search/solr.php?";
		for(String param : translatedParams) this.translatedURL += ("&" + param);
	}
}