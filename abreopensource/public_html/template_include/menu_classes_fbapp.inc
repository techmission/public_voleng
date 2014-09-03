<%
out.println("<!-- current page path is: "+ aszPath + "-->");

if(aszPath.equalsIgnoreCase("/index.jsp")){
        aszBodyClass="nav1-home" ;
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test2.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test3.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test4.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp")){
        aszBodyClass="nav1-personality-test";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_my_results.jsp")){
        aszBodyClass="nav1-my-results";
}else if(aszPath.equalsIgnoreCase("/voleng2/my_ministry_opportunities.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/org_public_listing.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/opp_public_listing.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/fullsearch.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/volunteer2r.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/volunteer3r.jsp")){
        aszBodyClass="nav1-my-ministry-opps";
}else if(aszPath.equalsIgnoreCase("/voleng2/facebook_training_content.jsp")){
		aszBodyClass="nav1-training";
}else if(aszPath.equalsIgnoreCase("/voleng2/facebook_friends_container.jsp")){
		aszBodyClass="nav1-friends";
}else if(aszPath.equalsIgnoreCase("/voleng2/facebook_typeinfo_container.jsp")){
		aszBodyClass="nav1-friends";
}else{
        aszBodyClass="nav1-home";
}

out.println("<!-- current body class is: "+ aszBodyClass + "-->");


%>

