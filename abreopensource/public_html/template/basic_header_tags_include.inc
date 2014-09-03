<%
String aszChurchVolDom ="www.ChurchVolunteering.org";
if(request.getParameter("portal")!=null || aszPortal.length()>0 ){
	if(request.getParameter("portal").length()>0){
out.println("<!-- portal is not empty -->");
		aszPortalName = request.getParameter("portal");
	}else if(aszPortal.contains("cityvision")){
out.println("<!-- aszPortal is not empty -->");
		aszPortalName = "cityvision";
	}
	// banner
	if(session.getAttribute( aszPortalName + "_banner") != null){
out.println("<!-- portal_banner   is not null -->");
		aszPortalBanner = session.getAttribute( aszPortalName + "_banner").toString();
	}	
	// header tags
	if(session.getAttribute( aszPortalName + "_header_tags") != null){
		aszPortalHeaderTags = session.getAttribute( aszPortalName + "_header_tags").toString();
	}	
	// header banner
	if(session.getAttribute( aszPortalName + "_header") != null){
		aszPortalHeader = session.getAttribute( aszPortalName + "_header").toString();
	}	
	// css
	if(session.getAttribute( aszPortalName + "_css") != null){
		aszPortalCSS = session.getAttribute( aszPortalName + "_css").toString();
	}	
	// footer
	if(session.getAttribute( aszPortalName + "_footer") != null){
		aszPortalFooter = session.getAttribute( aszPortalName + "_footer").toString();
	}	
	// org/church name
	if(session.getAttribute( aszPortalName + "_org_name") != null){
		aszPortalOrgName = session.getAttribute( aszPortalName + "_org_name").toString();
	}	
	// portal type
	if(session.getAttribute( aszPortalName + "_type") != null){
		aszPortalType = session.getAttribute( aszPortalName + "_type").toString();
	}	
	// nid		
	if(session.getAttribute( aszPortalName + "_nid") != null){
		aszPortalNID = session.getAttribute( aszPortalName + "_nid").toString();
		try{
			iPortalNID=Integer.parseInt(aszPortalNID);
		}catch (Exception e){}
	}			
	// uid		
	if(session.getAttribute( aszPortalName + "_uid") != null){
		aszPortalUID = session.getAttribute( aszPortalName + "_uid").toString();
		try{
			iPortalUID=Integer.parseInt(aszPortalUID);
		}catch (Exception e){}
	}			
}
String aszRedirected="";
int iCount=0;
if(request.getParameter("redirected")!=null){
	if(request.getParameter("redirected").length()>0){
		aszRedirected = request.getParameter("redirected");
	}
}
if(request.getParameter("count")!=null){
	if(request.getParameter("count").length()>0){
			try{
				iCount=Integer.parseInt(request.getParameter("count"));
				iCount++;
			}catch (Exception e){}
	}
}

if(bSkipBanner==true){
	if(request.getParameter("preview")!=null){
		try{
			aszPreviewSearch=request.getParameter("preview");
		}catch(Exception e){
			aszPreviewSearch="";
		}
	}
	if(request.getParameter("bckgrnd")!=null){
		try{
			aszBackgroundColor=request.getParameter("bckgrnd");
		}catch(Exception e){}
	}
	if(request.getParameter("brdr")!=null){
		try{
			aszBorderColor=request.getParameter("brdr");
		}catch(Exception e){}
	}
	if(request.getParameter("atxt")!=null){
		try{
			aszLinkTextColor=request.getParameter("atxt");
		}catch(Exception e){}
	}
	if(request.getParameter("txt")!=null){
		try{
			aszTextColor=request.getParameter("txt");
		}catch(Exception e){}
	}
}

boolean bHexTemp=false;
for(int i=0;i<aszBackgroundColor.length();i++){
	if(	Character.isDigit(aszBackgroundColor.charAt(i))==true ||
		(("0123456789abcdefABCDEF".indexOf(aszBackgroundColor.charAt(i))) >=0)
	){
		bHexTemp=true;
	}else{
		bHexTemp=false;
		break;
	}
}
if(bHexTemp==true){
	aszBackgroundColor="#"+aszBackgroundColor;
}

bHexTemp=false;
for(int i=0;i<aszBorderColor.length();i++){
	if(	Character.isDigit(aszBorderColor.charAt(i))==true ||
		(("0123456789abcdefABCDEF".indexOf(aszBorderColor.charAt(i))) >=0)
	){
		bHexTemp=true;
	}else{
		bHexTemp=false;
		break;
	}
}
if(bHexTemp==true){
	aszBorderColor="#"+aszBorderColor;
}

bHexTemp=false;
for(int i=0;i<aszLinkTextColor.length();i++){
	if(	Character.isDigit(aszLinkTextColor.charAt(i))==true ||
		(("0123456789abcdefABCDEF".indexOf(aszLinkTextColor.charAt(i))) >=0)
	){
		bHexTemp=true;
	}else{
		bHexTemp=false;
		break;
	}
}
if(bHexTemp==true){
	aszLinkTextColor="#"+aszLinkTextColor;
}

bHexTemp=false;
for(int i=0;i<aszTextColor.length();i++){
	if(	Character.isDigit(aszTextColor.charAt(i))==true ||
		(("0123456789abcdefABCDEF".indexOf(aszTextColor.charAt(i))) >=0)
	){
		bHexTemp=true;
	}else{
		bHexTemp=false;
		break;
	}
}
if(bHexTemp==true){
	aszTextColor="#"+aszTextColor;
}
if(aszBackgroundColor.length()>0){
	aszBackgroundColor=
		"#contentwrapper{ background-color:		" + aszBackgroundColor + ";}\n" + 
		"#maincontent{ background-color:		" + aszBackgroundColor + ";}\n" +
		"body{ background-color:		" + aszBackgroundColor + ";}\n";
}
if(aszBorderColor.length()>0){
	aszBorderColor="#maincontent{ border:		" + aszBorderColor + ";}\n";
}
if(aszLinkTextColor.length()>0){
	aszLinkTextColor="#body a{ color:		" + aszLinkTextColor + ";}\n";
}
if(aszTextColor.length()>0){
	aszTextColor=
		"#maincontent{ color:		" + aszTextColor + ";}\n"+
		"#body{ color:		" + aszTextColor + ";}\n";
}
//if(aszPreviewSearch.equals("true") ){
	// limit search restuls with a link for "more" going to the main portal listings page
//}

%>

