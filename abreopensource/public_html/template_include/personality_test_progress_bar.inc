<%
String aszBox1 = "boxon";
String aszBox2 = "boxoff";
String aszBox3 = "boxoff";


out.println("<!-- current page path is: "+ aszPath + "-->");

if(aszPath.equalsIgnoreCase("/voleng2/personality_test.jsp")){
	aszBox1 = "boxon";
	aszBox2 = "boxoff";
	aszBox3 = "boxoff";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test2.jsp")){
	aszBox1 = "boxon";
	aszBox2 = "boxoff";
	aszBox3 = "boxoff";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test3.jsp")){
	aszBox1 = "boxon";
	aszBox2 = "boxoff";
	aszBox3 = "boxoff";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_test4.jsp")){
	aszBox1 = "boxon";
	aszBox2 = "boxoff";
	aszBox3 = "boxoff";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas.jsp")){
	aszBox1 = "boxoff";
	aszBox2 = "boxon";
	aszBox3 = "boxoff";
}else if(aszPath.equalsIgnoreCase("/voleng2/personality_ministry_areas2.jsp")){
	aszBox1 = "boxoff";
	aszBox2 = "boxoff";
	aszBox3 = "boxon";
}
out.println("<!-- current body class is: "+ aszBodyClass + "-->");


%>

<div id="progressbar">
      <ul>
        <li><div class="<%=aszBox1%>">1</div><a href="register.do?method=showPersonalityTest&retaketest=true">Personality Test</a></li>
        <li><div class="<%=aszBox2%>">2</div><% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ out.print("<a href=\"register.do?method=showPersonalityMinistryAreas\"> Service"); } else { out.print("<a href=\"register.do?method=showPersonalityMinistryAreas\"> Ministry");} %> Interests/Skills</a></li>
        <!--<li><div class="<%=aszBox3%>">3</div><a href="register.do?method=showPersonalityMinistryAreas2&page=2">Learning Interests/Passions</a></li> -->
      </ul>
      </div>

