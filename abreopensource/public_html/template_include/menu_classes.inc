<%
out.println("<!-- current page path is: "+ aszPath + "-->");
out.println("<!-- aszLandingPage is: "+ aszLandingPage +"-->");

if(aszPath.equals("/voleng2/church_instructions.jsp")){
        aszBodyClass="nav1-churches nav2-post-volunteer-opportunities";
}else if(aszPath.equals("/voleng2/church_volunteer.jsp")){
        aszBodyClass="nav1-churches nav2-church-portal";

}else if( (aszLandingPage.equals("family")) && (aszPath.equals("/index.jsp")) ){
        aszBodyClass="nav1-volunteers nav2-find-opportunities nav3-for-families";

}else if( aszLandingPage.equals("christianjobs") && aszPath.equals("/index.jsp") ){
       aszBodyClass="nav1-jobs";
}else if(aszPath.equals("/voleng2/virtual_resources.jsp")||
	 (aszLandingPage.equals("virtual")) && (aszPath.equals("/index.jsp")) 
){
        aszBodyClass="nav1-volunteers nav2-find-opportunities nav3-virtual-volunteering";
}else if(aszPath.equals("/voleng2/short_term_missions.jsp")	||
		aszLandingPage.equals("stm")	||
		aszLandingPage.equals("christiangapyear")
){
        aszBodyClass="nav1-volunteers nav2-find-opportunities nav3-short-term-missions";
}else if(aszPath.equals("/index.jsp")){
        aszBodyClass="nav1-volunteers nav2-find-opportunities";
}else if(aszPath.equals("/voleng2/About-CV.jsp")){
        aszBodyClass="nav1-about nav2-programs nav3-christian-volunteering-org";
}else if(aszPath.equals("/voleng2/businessasmissionvolunteering.jsp")){
        aszBodyClass="nav1-volunteers nav2-find-opportunities";
}else if(aszPath.equals("/voleng2/calling.jsp")	||
			aszPath.equals("/voleng2/cityserve_news_2005_winter.jsp")	||
			aszPath.equals("/voleng2/cityserve_news_2006_Aug.jsp")	||
			aszPath.equals("/voleng2/cityserve_news_2006_Dec.jsp")	||
			aszPath.equals("/voleng2/cityserve_news_2006_Jun.jsp")	||
			aszPath.equals("/voleng2/cityserve_news_2006_Oct.jsp")
){
        aszBodyClass="nav1-volunteers nav2-learn";
}else if(aszPath.equals("/voleng2/citysitemap.jsp")){ 
        aszBodyClass="nav1-volunteers nav2-find-opportunities";
}else if(aszPath.equals("/voleng2/contactus.jsp")){
        aszBodyClass="nav1-about nav2-contact-us nav3-christian-volunteering-org";
}else if(aszPath.equals("/voleng2/disasterreliefvolunteering.jsp")){
        aszBodyClass="nav1-volunteers nav2-find-opportunities";
}else if(aszPath.equals("/voleng2/drupal_setup.jsp")){
        aszBodyClass="nav1-my-account nav2-create-account";
}else if(
			aszPath.equals("/voleng2/emailconfirmation.jsp")			||
			aszPath.equals("/voleng2/search.jsp")						|| 
			aszPath.equals("/voleng2/fullsearch.jsp") 				|| 
			aszPath.equals("/voleng2/zipmap.jsp") 					|| 
			aszPath.equals("/voleng2/search_results_ajax-solr.jsp") 	||
			aszPath.equals("/voleng2/solr_directory.jsp") 			||
			aszPath.equals("/voleng2/solr_org_directory.jsp") 		||
			aszPath.equals("/voleng2/orgnoresults.jsp") 				||
			aszPath.equals("/voleng2/iwanttohelp.jsp") 				||
			aszPath.equals("/voleng2/nonp-view21.jsp") 				|| 
			aszPath.equals("/voleng2/nonp-viewlisting1.jsp") 			||
			aszPath.equals("/voleng2/sitemap1.jsp")					||
			aszPath.equals("/voleng2/volnoresults.jsp") 				||
			aszPath.equals("/voleng2/search_results_ajax-solr.jsp") 	|| 
			aszPath.equals("/voleng2/search_results.jsp") 			|| 
			aszPath.equals("/voleng2/search_results_embed.jsp") 		|| 
			aszPath.equals("/voleng2/volunteer2r.jsp") 				|| 
			aszPath.equals("/voleng2/volunteer3r.jsp")

){
        aszBodyClass="nav1-volunteers nav2-browse-opportunities";
		
}else if(aszPath.equals("/voleng2/login1.jsp")){
        aszBodyClass="nav1-my-account nav2-login";
}else if(aszPath.equals("/voleng2/loginstatus.jsp")){
        aszBodyClass="nav1-my-account";
}else if(aszPath.equals("/voleng2/nonp-start22.jsp")){
        aszBodyClass="nav1-organizations nav2-recruit-volunteers";
}else if( 
		aszMethodName.equals("showIndAcctSum1") 			|| 
		aszPath.equals("/voleng2/myaccountsum1.jsp")		||
		aszPath.equals("/voleng2/volunteerdashboard.jsp")
){
        aszBodyClass="nav1-my-account nav2-manage-account";
}else if(aszPath.equals("/voleng2/myaccnt_switch_org.jsp")){
        aszBodyClass="nav1-my-account organization";
}else if(aszPath.equals("/voleng2/association_management.jsp")){
        aszBodyClass="nav1-organizations association";
}else if(
		aszPath.equals("/voleng2/orgmanagement.jsp")	 	||
		aszPath.equals("/voleng2/orgmanage_listings.jsp")	||
		aszPath.equals("/voleng2/nonp-start33.jsp")	||
		aszPath.equals("/voleng2/nonp-start44.jsp")	||
		aszPath.equals("/voleng2/nonp-start55.jsp")	||
		aszPath.equals("/voleng2/nonp-edit55.jsp")	||
		aszPath.equals("/voleng2/nonp-edit55.jsp")	||
		aszPath.equals("/voleng2/nonp-edit66.jsp")
){
        aszBodyClass="nav1-organizations nav2-manage-organization organization";
}else if(
		aszPath.equals("/voleng2/nonp-addlisting.jsp")	||
		aszPath.equals("/voleng2/nonp-editlisting.jsp")	
){
        aszBodyClass="nav1-organizations nav2-manage-opportunity opportunity";
}else if(
		aszPath.equals("/voleng2/opp_contact_add.jsp")	||
		aszPath.equals("/voleng2/opp_contact_manage.jsp")	||
		aszPath.equals("/voleng2/opp_contact_remove.jsp")	||
		aszPath.equals("/voleng2/org_admin_add.jsp")		||
		aszPath.equals("/voleng2/org_admin_manage.jsp")	||
		aszPath.equals("/voleng2/org_admin_remove.jsp")	||
		aszPath.equals("/voleng2/org_admin-contact_manage.jsp")	||
		aszPath.equals("/voleng2/org_contact_manage.jsp")	||
		aszPath.equals("/voleng2/org_contact_remove.jsp")	||
		aszPath.equals("/voleng2/org_contacts.jsp")	
){
        aszBodyClass="nav1-organizations nav2-manage-contacts contacts";
        
}else if(aszPath.equals("/voleng2/org_share_on_website.jsp")){
        aszBodyClass="nav1-organizations nav2-share share";
}else if(
			aszPath.equals("/voleng2/nonp-view11.jsp") 		|| 
			aszPath.equals("/voleng2/nonp-viewlisting.jsp") 	||
			aszPath.equals("/voleng2/pasteforms.jsp")			||
			aszPath.equals("/voleng2/pasteforms_advanced.jsp")
){
        aszBodyClass="nav1-organizations";
        
}else if(aszPath.equals("/voleng2/staff_recruitment.jsp")){ //****************
        aszBodyClass="nav1-volunteers nav2-learn";
}else if(aszPath.equals("/voleng2/technology_links.jsp")	||
		aszPath.equals("/voleng2/vol-stories.jsp")
){
        aszBodyClass="nav1-volunteers nav2-find-opportunities";
/* personal profile account management is now in rails pages
}else if(aszPath.equals("/voleng2/volacctedit1.jsp")){
        aszBodyClass="nav1-my-account nav2-manage-personal";
*/        
}else if(aszPath.equals("/voleng2/volcreateacct1.jsp")){
        aszBodyClass="nav1-my-account nav2-create-account";
}else if(aszPath.equals("/voleng2/volunteergettingstarted.jsp")){
        aszBodyClass="nav1-volunteers nav2-getting-started";
}else if(aszPath.equals("/voleng2/vol-start11.jsp")){
        aszBodyClass="nav1-volunteers nav2-find-opportunities nav3-search-all";
}else{
        aszBodyClass="nav1-my-account";
}

out.println("<!-- current body class is: "+ aszBodyClass + "-->");


%>

