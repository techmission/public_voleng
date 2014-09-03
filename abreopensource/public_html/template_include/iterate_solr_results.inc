<logic:notEmpty name="alist" >
	<logic:iterate id="org" name="alist" type="com.abrecorp.opensource.dataobj.OrgOpportunityInfoDTO">
<%			
		// separate out categories for output and re-word categories for no-faith
		String	aszServiceAreas = org.getOPPCategories();
		if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
			aszServiceAreas=aszServiceAreas.replaceAll("Small Group/Bible Study","");
			aszServiceAreas=aszServiceAreas.replaceAll("Christian/Catholic Schools","");
			aszServiceAreas=aszServiceAreas.replaceAll("Church Planting","");
			aszServiceAreas=aszServiceAreas.replaceAll("Evangelism/Seeker Ministry","");
			aszServiceAreas=aszServiceAreas.replaceAll("Family / Adults Ministry","");
			aszServiceAreas=aszServiceAreas.replaceAll("Sunday School/Religious Ed","");
			aszServiceAreas=aszServiceAreas.replaceAll("Single Parents / Crisis Pregnancy","");
			aszServiceAreas=aszServiceAreas.replaceAll("Single Parents/Crisis Pregnancy","");
			aszServiceAreas=aszServiceAreas.replaceAll("Vacation Bible School","");
			aszServiceAreas=aszServiceAreas.replaceAll("Women's Ministry","");
			aszServiceAreas=aszServiceAreas.replaceAll("Children and Youth Ministry","Children and Youth");
			aszServiceAreas=aszServiceAreas.replaceAll("Disabilities Ministry","Disabilities Outreach");
			aszServiceAreas=aszServiceAreas.replaceAll("Visitation/Pastoral Care","Visitation");
			aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry / Hunger","Food Service / Hunger");
			aszServiceAreas=aszServiceAreas.replaceAll("Food Ministry","Foods Outreach");
			aszServiceAreas=aszServiceAreas.replaceAll("Prison Ministry","Prison Outreach");
		}
		aszServiceAreas=aszServiceAreas.replaceAll("^,","").replaceAll(",$","").replaceAll(",(?=[^()]*)", ", ");

		String aszCity= org.getOPPAddrCity();
		String aszTemp1= org.getOPPAddrCountryName();
		String aszTemp2 = org.getOPPAddrStateprov();
		String aszOppLocation = "";
		if(aszTemp1.equalsIgnoreCase("US")){
			if(null != aStateList){
				for(int index=0; index < aStateList.size(); index++){
					AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aStateList.get(index);
					if(null == aAppCode) continue;
					String aszOptRqCode = aAppCode.getCSPStateCode();
					if(aszOptRqCode.equalsIgnoreCase( aszTemp2 ) ) {
						aszOppLocation=aAppCode.getCSPStateName();
						if(aszCity.equalsIgnoreCase( "" ) ) {
							aszOppLocation=aAppCode.getCSPStateName();
						}
					}
				}
			}
		} else if(null != aCountryList){
			for(int index=0; index < aCountryList.size(); index++){
				AppCodeInfoDTO aAppCode = (AppCodeInfoDTO)aCountryList.get(index);
				if(null == aAppCode) continue;
				String aszOptRqCode = aAppCode.getCTRIso();
				if(aszOptRqCode.equalsIgnoreCase( aszTemp1 ) ) {
					aszOppLocation=aAppCode.getCTRPrintableName();
					if(aszCity.equalsIgnoreCase( "" ) ) {
						aszOppLocation=aAppCode.getCTRPrintableName();
					}
				}
			}
		}
		int iCityLength = 0;
		if (aszCity.length() > 1) {
			aszOppLocation = ", " + aszOppLocation;
		}
		aszOppLocation = org.getOPPAddrCity() + aszOppLocation;
		aszOppTitlePrint = org.getOPPTitle();
		aszOrgNamePrint = org.getORGOrgName();
		//popularity
		int iLastSpace = org.getOPPDescTeaser().lastIndexOf(" ");
		

		if(iPortalUID > 0){
			if(org.getOPPUID()==iPortalUID){
				out.print("<!--this opportunity is owned by the portal org/church; need to check for offsite or at HQ -->");
				if(org.getOPPHQorOffSite().equalsIgnoreCase("hq")){
					aszOppLocation ="Church building: " + aszOppLocation; //aszLocationHQorOffSite = "HQ: " + aszOppLocation;
				}else if(org.getOPPHQorOffSite().equalsIgnoreCase("offsite_intl")){
					out.print("<!-- sets to off site -->");
					aszOppLocation ="Global: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
				}else{
					out.print("<!-- sets to off site -->");
					aszOppLocation ="Off-Site: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
				}
			}else if(! org.getOPPAddrCountryName().equalsIgnoreCase("us")) {
				out.print("<!-- sets to off site -->");
				aszOppLocation ="Global: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
			}else{
				out.print("<!-- sets to off site -->");
				aszOppLocation ="Off-Site: "+aszOppLocation; //aszLocationHQorOffSite = "Off-Site: " + aszOppLocation;
			}
		}

		// program in forwarding if the opportunity is Faith-Filled
		String aszOPPUrlAlias=org.getOPPUrlAlias(), aszORGUrlAlias=org.getORGUrlAlias(), aszFeedsClass="";
		boolean feed=false;
		if(aszOPPUrlAlias.contains("feed/")){
			feed=true;
			aszFeedsClass="feeds";
			iFeedsResults++;
		}
		if(aszORGUrlAlias.length()<1){
			aszORGUrlAlias=aszOPPUrlAlias;
		}
		if(iPortalUID > 0){
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}else if(org.getOPPFaithSpecTID()==21998 && aszMobileSubdomain.length() > 3 ){ 
			aszOPPUrlAlias="http://m.churchvolunteering.org/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias="http://m.churchvolunteering.org/"+aszORGUrlAlias+".jsp";
		}else if(org.getOPPFaithSpecTID()==21998 && aszSecondaryHost.equalsIgnoreCase("default") ){ 
			aszOPPUrlAlias="http://www.churchvolunteering.org/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias="http://www.churchvolunteering.org/"+aszORGUrlAlias+".jsp";
		}else if(org.getOPPPositionType().contains("Virtual")){
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}else{ 
			aszOPPUrlAlias=aszPortal+"/"+aszOPPUrlAlias+".jsp";
			aszORGUrlAlias=aszPortal+"/"+aszORGUrlAlias+".jsp";
		}
		String aszMemberClass="class=\"listing\"";
		if(org.getORGMember()>0){
			aszMemberClass="class=\"member listing\"";
		}

		String	aszPositionType = org.getOPPPositionType();
		if(aszSecondaryHost.equalsIgnoreCase("volengivol")){
			aszPositionType=aszPositionType.replaceAll("Missions","Service Trip");
		}
		aszPositionType=aszPositionType.replaceAll("^,","");
		aszPositionType=aszPositionType.replaceAll(",$","");
		aszPositionType=aszPositionType.replaceAll(",(?=[^()]*)", ", ");
		String aszLocation = "";
		if(aszPositionType.contains("Virtual")){
			if(aszPositionType.contains("Local")){
				aszLocation = aszOppLocation + " or " + " Virtual Volunteering";//aszPositionType;
			}else{
				aszLocation = aszPositionType;
			}
		}else{
			aszLocation = aszOppLocation;
		}
		String	aszFrequency = org.getOPPFreq().replaceAll(",(?=[^()]*)", ", ");
		String	aszTripLength = org.getOPPTripLength().replaceAll(",(?=[^()]*)", ", ");
		String	aszGreatFor = org.getOPPGreatForAreas().replaceAll(",(?=[^()]*)", ", ");
		String	aszBenefits = org.getOPPBenefits().replaceAll(",(?=[^()]*)", ", ");
		String	aszWorkStudy = org.getOPPWorkStudy().replaceAll(",(?=[^()]*)", ", ");

		
		String aszInterestAreas = "";
		if(aszServiceAreas.length() > 1 && aszWorkStudy.length() > 1){
			aszInterestAreas = aszServiceAreas + ", " + aszWorkStudy;
		}else if(aszServiceAreas.length() > 1){
			aszInterestAreas = aszServiceAreas;
		}else if(aszWorkStudy.length() > 1){
			aszInterestAreas = aszWorkStudy;
		}
		
		String aszSTMDetails = "";
		if(aszBenefits.length()>1){
			aszSTMDetails += " <b>Benefits Offered:</b>&nbsp;" + aszBenefits + "&nbsp;";
		}
		if(aszTripLength.length()>1){
			aszSTMDetails += " <b>Duration:</b>&nbsp;" + aszTripLength + "&nbsp;";
		}
		if(aszSTMDetails.length()>1){
			aszSTMDetails = "<br>" + aszSTMDetails.substring(1);
		}
		
		
		int iTemp=0; long iTime=0;

%>


		<div id="search-results" class="<%=aszFeedsClass%>">
			<div <%=aszMemberClass%>>
				<%iCounter++;%><%=iCounter%>.&nbsp;
				<A class="opp_link" href="<%=aszOPPUrlAlias%>"><%=aszOppTitlePrint%></A>
				&nbsp;&nbsp;
				<A class="org_link" href="<%=aszORGUrlAlias%>"><%=aszOrgNamePrint%></A>
				<div class="opp-info">
					<b>Service Areas:</b>&nbsp;<%=aszInterestAreas%>
					<%=aszSTMDetails%>
					<br>
					<b>Location:</b>&nbsp;<%=aszLocation%>&nbsp;&nbsp;
				</div>
			</div>
		<hr />
		</div>

	</logic:iterate>
</logic:notEmpty>

