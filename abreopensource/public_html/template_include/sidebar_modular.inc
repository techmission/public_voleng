<!-- start sidebar.inc -->


<% if(	(aszHostID.equalsIgnoreCase( "volengagrm" )) ||	
                                (aszHostID.equalsIgnoreCase( "volengabs" )) ||
				(aszHostID.equalsIgnoreCase( "volengccda" )) ||	
				(aszHostID.equalsIgnoreCase( "volengesa" )) ||	
				(aszHostID.equalsIgnoreCase( "volenggospel" ))  ||
				(aszHostID.equalsIgnoreCase( "volenggospelcom" ))  ||
				(aszHostID.equalsIgnoreCase( "volenghlic" ))  ||
                                (aszHostID.equalsIgnoreCase( "volengleadingoutreach" )) ||
                                (aszHostID.equalsIgnoreCase( "volengpartners" )) ||
                                (aszHostID.equalsIgnoreCase( "volengroundtrip" )) ||
				(aszHostID.equalsIgnoreCase( "volengsaddleback" )) ||
				(aszHostID.equalsIgnoreCase( "volengsalvationarmy" )) ||
				(aszHostID.equalsIgnoreCase( "volengsojo" )) ||
				(aszHostID.equalsIgnoreCase( "volengurbmin" )) ||
				(aszHostID.equalsIgnoreCase( "volengyouthpartners" ))
){ %>
  <%@ include file="/template_include/modular2_sidebar.inc" %>

<% //} else if(  (aszHostID.equalsIgnoreCase( "volengegc" )) || (aszHostID.equalsIgnoreCase( "volengmissionamerica" )) || (aszHostID.equalsIgnoreCase( "volenguywi" )) || (aszHostID.equalsIgnoreCase( "volengworldvision" )) || (aszHostID.equalsIgnoreCase( "volengyounglife" ))){  %>

<% } else { %>

  <%@ include file="/template_include/modular_sidebar.inc" %>

<% } %>
<!-- end sidebar.inc -->
