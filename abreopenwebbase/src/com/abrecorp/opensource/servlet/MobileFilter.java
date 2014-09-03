package com.abrecorp.opensource.servlet;

import java.io.IOException;
import java.util.Enumeration;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
public class MobileFilter implements Filter 
{
	// Filter that intercepts every request to see if it comes from a mobile device and redirects it if it does
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
    {	
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        
    	String aszURI = request.getRequestURI();
    	aszURI = aszURI.replace("/voleng", "");
    	String aszQueryParams = request.getQueryString();
    	if(aszQueryParams == null)aszQueryParams = "";

    	HttpSession session = request.getSession();
        boolean b_firstTimeThrough = true;
        boolean b_fullSiteInit = true;
        boolean b_requestFullSite = false;
        
        if(session.getAttribute("firstTimeThrough") != null){
        	try{
        		String aszRedirectInit = session.getAttribute("firstTimeThrough").toString();
        		if(aszRedirectInit.length()>3){
        			b_firstTimeThrough=false;
        		}
        	}catch(Exception e){}
        }
        if(session.getAttribute("fullSiteInit") != null){
        	try{
        		String aszRedirectInit = session.getAttribute("fullSiteInit").toString();
        		if(aszRedirectInit.length()>3){
        			b_fullSiteInit=false;
        		}
        	}catch(Exception e){}
        }
        if(session.getAttribute("fullSite") != null){
        	try{
        		String asznonMobile = session.getAttribute("fullSite").toString();
        		if(asznonMobile.equals("true")){
        			b_requestFullSite=true;
        		}
        	}catch(Exception e){}
        }
        if(aszQueryParams.contains("fullSite") || aszQueryParams.contains("fullsite") || aszQueryParams.contains("full_site")){
        	b_requestFullSite=true;
        }
        try{
	        if ( 	b_fullSiteInit && b_requestFullSite && request.getHeader("host").contains( "m.chris" ) 
	        ){
	           	String aszNewURL = "http://www.christianvolunteering.org";
	            if(aszURI != null){
	           		aszNewURL += aszURI;
	           		if(aszQueryParams != null){
	           			if(aszQueryParams.length()>0)
	           				aszNewURL += "?"+aszQueryParams;
	           		}
	           	}
	           	if(	aszQueryParams.contains("fullsite") || aszQueryParams.contains("full_site")  )	           			
		        {
	           		session.setAttribute("fullsite", "true");
	           		session.setAttribute("fullSiteInit", "done");
		        	response.sendRedirect(aszNewURL);
		        	return;
		        }
		        else
		        	chain.doFilter(req, res);
	        }
        }catch(Exception e){
        	System.out.println("83 MobileFilter error: "+e);
        	chain.doFilter(req, res);
        	return;
        }
        
        try{
	        if ( 	b_firstTimeThrough && b_requestFullSite==false &&
	        		! (request.getHeader("host").contains( "m.chris" ) ) && 
	        		aszURI.indexOf("/img") == -1 && 
	        		aszURI.indexOf("/css") == -1 && 
	        		aszURI.indexOf("/js") == -1 
	        ){
	        	if(request.getHeader("User-Agent")==null){
	            	System.out.println("96 MobileFilter error - user agent is null");
	            	chain.doFilter(req, res); 
	            	return;
	        	}
	        	String ua = request.getHeader("User-Agent").toLowerCase();
	           	String aszNewURL = "http://m.christianvolunteering.org";
	            if(aszURI != null){
	           		aszNewURL += aszURI;
	           		if(aszQueryParams != null){
	           			if(aszQueryParams.length()>0)
	           				aszNewURL += "?"+aszQueryParams;
	           		}
	           	}
           		if(aszQueryParams != null){
		           	if(	(! (aszQueryParams.contains("Drupal") || aszQueryParams.contains("loggedin") )) &&
		           			ua.matches(".*(android.+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-"))
			        {
		           		session.setAttribute("firstTimeThrough", "done");
			        	response.sendRedirect(aszNewURL);
			        	return;
			        }else{
			        	chain.doFilter(req, res);
			        	return;
			        }
// dead  code - never triggered		        }else{
//		        	chain.doFilter(req, res);
		        }
	        }else{
	        	chain.doFilter(req, res);
	        	return;
	        }
        }catch(Exception e){
        	System.out.println("119 MobileFilter error: "+e);
        	chain.doFilter(req, res);
        	return;
        }
    }
    
    public void init(FilterConfig config) throws ServletException 
    {
    }
    
    public void destroy() 
    {
    }

}