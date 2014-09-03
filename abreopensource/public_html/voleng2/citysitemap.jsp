<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->


<% if(	aszHostID.equalsIgnoreCase( "voleng1" )	||
		aszHostID.equalsIgnoreCase( "default" ) ||
		aszHostID.equalsIgnoreCase( "volengcatholic" ) ||
		aszHostID.equalsIgnoreCase( "volengchurch" ) ||
		aszHostID.equalsIgnoreCase( "volengfamily" )
){ 
	bHeaderSet=true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="image_src" href="/imgs/logo.gif"/>
<head>
<title>Christian Volunteer Network: City Sitemap</title>
<% } else { %>
<% } %>

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar_solr.inc" %>
  </div>
<% }else{ %>
  <div id="oppsearch">
	  <span id="title">Sitemap</span>
	<%@ include file="/template_include/navigation_search_bar.inc" %>
  </div>
<% } %>


<% if(b_includeLeftSidebar==true){ %>
<div id="result">
<!-- start sidebar information -->
<!-- start sidebar.inc -->
<%@ include file="/template_include/left_sidebar.inc" %>
<!-- end sidebar information -->
</div>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent" class="search_results">
<% }else{ %>
<!-- BEGIN MAINCONTENT -->
<div id="maincontent">
<% } %>


<div id="chrisvolTopPartnerMenu">
<!-- start navigation information -->
<%@ include file="/template/partner_navigation.inc" %>
<!-- end navigation information -->
</div>

<% if(aszNoSearchNav=="true"){ %>
<div id="pagebanner">
<span style="float: left;">Sitemap</span>
<div id="banner_side_graphic"></div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; sitemap </div>
</div>
<% } %>



 <div id="body">

 <head>

                    <meta name="keywords" content="community, computer, urban, ministry, training, technology, vulnerable communities, vunerable lives, at risk youth, low income, poor, online safety, protecting kids online, urban ministry internship, paid internship">

                    <meta name="description" content="Explore our wide range of resources in Technology and ministry">

                </head>







<div align="left">

			            	<blockquote>

<p><a href="<%=aszPortal%>/" title="Begin here to recruit urban ministry volunteers and find Christian Volunteer and Church Volunteer opportunities">Home Page</a></p>

<h5>REGIONAL VOLUNTEERING SITEMAP</h5><br />

<%@ page import="java.util.List, java.util.LinkedList" %>
<%
  abstract class Location {
    abstract String getDisplayName();
	abstract String getSearchFilter();
  }
  
  abstract class City extends Location {
	abstract String getName();
	String getDisplayName() {
	  return getName();	
	}
	abstract String getState();
	final String getSearchFilter() {
      return "city_tax:\"" + getName() + ", " + getState() + "\"";
	}
  }
  
  abstract class State extends Location {
	abstract String getName();
	String getDisplayName() {
	  return getName();	
	}
	final String getSearchFilter() {
      return "province_tax:\"" + getName() + "\"";
	}
  }
  
  class SearchLink {
	private String text;
	private String title;
	private List<String> filters;
	
	public SearchLink(String text, String title, List<String> filters) {
	  this.text = text;
	  this.title = title;
	  this.filters = filters;
	}
	
	public String getText() {
	  return text;	
	}
	
	public String getURL(portal) {
	  String url = portal + "/oppsrch.do?method=processSearch";
	  if(filters.size() > 0) {
	    url += "#fq=" + filters.get(0);
		if(filters.size() > 1) {
	      for(String f : filters.subList(1, filters.size() - 1)) {
		    url += "&fq=" + f;
		  }
		}
	  }
      return url;
	}
	
	public String getTitle() {
	  return title;
	}
  }
  
  abstract class SitemapSection {
    abstract Location getLocation();
	abstract String getPortalURL();
	abstract protected Location[] getRelatedLocations();
	private final List<Location> getLocations() {
	  List<Location> locations = new LinkedList<Location>();
	  locations.add(0, getLocation());
	  for(Location l : getRelatedLocations()) {
	    locations.add(locations.size(), l);
	  }
	  return locations;
	}
	private final List<String> filterWithLocation(String filter) {
	  List<String> filters = new LinkedList<String>();
	  filters.add(0, getLocation().getSearchFilter());
	  filters.add(0, filter);
	  return filters;
	}
	private final List<SearchLink> searchLinksWithLocation(String[][] data) {
	  List<SearchLink> links = new LinkedList<SearchLink>();
	  for(String[] tuple : data) {
	    links.add(links.size(), new SearchLink(
		  tuple[0],
		  tuple[1],
		  filterWithLocation(tuple[2])
		));
	  }
	  return links;
	}
	List<SearchLink> getTypeSearchLinks() {
	  return searchLinksWithLocation(new String[][] {
	    {"Virtual Volunteering", "Find Christian Virtual Volunteer Opportunities here", "position_type:\"Virtual Volunteering (from home)\""},
		{"Group Volunteering", "Find Christian Group Volunteer Opportunities here", "great_for:\"Great for Groups\""},
		{"Short Term Mission Trips", "Find Christian Short-term Urban Missions Voluteer Opportunities here", "position_type:\"Short-term Missions / Volunteer Internship\""},
		{"Youth and Junior High Mission Trips", "Find Christian Youth and Junior High Missions Trips Voluteer Opportunities here", "great_for:\"Great for Teens\""}
	  });
	}
	List<SearchLink> getLocationSearchLinks() {
    return null;
	}
	List<SearchLink> getServiceAreaSearchLinks() {
		return searchLinksWithLocation(new String[][] {
		  {"Administrative"},
		  {"Children and Youth"},
		  {"Community Development"},
		  {"Education and Literacy"},
		  {"Employment Training"},
		  {"Food Ministry / Hunger"},
		  {"Homelessness and Housing"},
		  {"Immigrants and Refugees"}
		});
	}
	List<SearchLink> getSkillsSearchLinks() {
		return null;
	}
  }
  
  List<SitemapSection> sitemapSections = new LinkedList<SitemapSection>();
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Chicago";  
		  }
		  String getState() {
			return "IL";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://chicago.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Joliet";
			}
			public String getState() {
			  return "IL";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Aurora";
			}
			public String getState() {
			  return "IL";
			}
		  },
		  new State() {
		    public String getName() {
			  return "Illinois";
			}
		  }
		};
	  }
	}
  );
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Denver";  
		  }
		  String getState() {
			return "CO";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://denver.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Littleton";
			}
			public String getState() {
			  return "CO";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Fort Collins";
			}
			public String getState() {
			  return "CO";
			}
		  },
		  new State() {
		    public String getName() {
			  return "Colorado";
			}
		  }
		};
	  }
	}
  );
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Indianapolis";  
		  }
		  String getState() {
			return "IN";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://indy.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Bloomington";
			}
			public String getState() {
			  return "IN";
			}
		  },
		  new City() {
		    public String getName() {
			  return "New Castle";
			}
			public String getState() {
			  return "IN";
			}
		  },
		  new State() {
		    public String getName() {
			  return "Indiana";
			}
		  }
		};
	  }
	}
  );
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Los Angeles";  
		  }
		  String getState() {
			return "CA";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://losangeles.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Pasadena";
			}
			public String getState() {
			  return "CA";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Santa Ana";
			}
			public String getState() {
			  return "CA";
			}
		  },
		  new State() {
		    public String getName() {
			  return "California";
			}
		  }
		};
	  }
	}
  );
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Miami";  
		  }
		  String getState() {
			return "FL";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://miami.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Hollywood";
			}
			public String getState() {
			  return "FL";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Fort Lauderdale";
			}
			public String getState() {
			  return "FL";
			}
		  },
		  new State() {
		    public String getName() {
			  return "Florida";
			}
		  }
		};
	  }
	}
  );
  
  // TODO: New England
  
  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getDisplayName() {
			return "New York City";  
		  }
		  String getName() {
			return "New York";  
		  }
		  String getState() {
			return "NY";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://newyork.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Bronx";
			}
			public String getDisplayName() {
			  return "The Bronx";	
			}
			public String getState() {
			  return "NY";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Brooklyn";
			}
			public String getState() {
			  return "NY";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Manhattan";
			}
			public String getState() {
			  return "NY";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Queens";
			}
			public String getState() {
			  return "NY";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Staten Island";
			}
			public String getState() {
			  return "NY";
			}
		  },
		  new State() {
		    public String getName() {
			  return "New York";
			}
			public String getDisplayName() {
			  return "New York State";	
			}
		  }
		};
	  }
	}
  );  

  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Seattle";  
		  }
		  String getState() {
			return "WA";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://seattle.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new City() {
		    public String getName() {
			  return "Tacoma";
			}
			public String getState() {
			  return "WA";
			}
		  },
		  new City() {
		    public String getName() {
			  return "Everett";
			}
			public String getState() {
			  return "WA";
			}
		  },
		  new State() {
		    public String getName() {
			  return "Washington";
			}
		  }
		};
	  }
	}
  );

  sitemapSections.add(
	new SitemapSection() {
	  Location getLocation() {
		return new City() {
		  String getName() {
			return "Minneapolis-St. Paul";  
		  }
		  String displayName() {
			 return "Twin Cities";
		  }
		  String getState() {
			return "MN";  
		  }
		};
	  }
	  String getPortalURL() {
		return "http://twincities.christianvolunteering.org";  
	  }
	  protected Location[] getRelatedLocations() {
		return new Location[] {
		  new State() {
		    public String getName() {
			  return "Minnesota";
			}
		  },
		  new State() {
			public String getName() {
			  return "Wisconsin";	
			}
		  }
		};
	  }
	}
  );
  
%>

<hr />


<b><a href="http://chicago.christianvolunteering.org" title="Chicago Volunteering in Urban Ministry and Short Term Missions">Chicago Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p></p>
<p><b>Browse by Location</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Chicago&Submit=Submit">Chicago</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Joliet&state=IL&Submit=Submit">Joliet</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Aurora&state=IL&Submit=Submit">Aurora</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=IL&Submit=Submit">Illinois</a></li>
</ul>
<p><b>Browse by Service Area</b><br>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in administrative service area">Administrative</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in children and youth service area">Children and Youth</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in community development service area"/>Community Development</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=IL&Submit=Submit" title="Find Chicago Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://denver.christianvolunteering.org" title="Denver Volunteering in Urban Ministry and Short Term Missions">Denver Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Denver Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Denver Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Denver Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Denver Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Denver&state=CO&Submit=Submit">Denver</a><br></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Littleton&state=CO&Submit=Submit">Littleton</a><br></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Fort+Collins&state=CO&Submit=Submit">Fort Collins</a><br></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=CO&Submit=Submit">Colorado</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=CO&Submit=Submit" title="Find Denver Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://indy.christianvolunteering.org" title="Indianapolis Volunteering in Urban Ministry and Short Term Missions">Indianapolis Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
</p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Indy Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Indy Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Indy Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Indy Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Indianapolis&Submit=Submit">Indianapolis</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Bloomington&Submit=Submit">Bloomington</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=New+Castle&state=IN&Submit=Submit">New Castle</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=IN&Submit=Submit">Indiana</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=INSubmit=Submit" title="Find Indy Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=IN&Submit=Submit" title="Find Indy Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://losangeles.christianvolunteering.org" title="Los Angeles Volunteering in Urban Ministry and Short Term Missions">Los Angeles Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
    <li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Los Angeles Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
    <li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Los Angeles Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Los Angeles Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Los Angeles Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Los+Angeles&Submit=Submit">Los Angeles</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Pasadena&state=CA&Submit=Submit">Pasadena</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Santa+Ana&state=CA&Submit=Submit">Santa Ana</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=CA&Submit=Submit">California</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in administrative service area">Administrative</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in children and youth service area">Children and Youth</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in community development service area"/>Community Development</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in employment training service area">Employment Training</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
     <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=CA&Submit=Submit" title="Find Los Angeles Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://miami.christianvolunteering.org" title="Miami Volunteering in Urban Ministry and Short Term Missions">Miami Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Miami Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Miami Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Miami Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Miami Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
     <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Miami&Submit=Submit">Miami</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Hollywood&state=FL&Submit=Submit">Hollywood</a></li>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Fort+Lauderdale&Submit=Submit">Fort Lauderdale</a></l>
	 <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=FL&Submit=Submit">Florida</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=FL&Submit=Submit" title="Find Miami Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://newengland.christianvolunteering.org" title="New England Volunteering in Urban Ministry and Short Term Missions">New England Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Boston Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&region=USA+New+England&focusare4=Yes&Submit=Submit" title="Find Boston Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=USA+New+England&Submit=Submit" title="Find Boston Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&region=USA+New+England&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Boston Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=CT&Submit=Submit" title="Find Christian Volunteering in Connecticut">Connecticut</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=MA&Submit=Submit" title="Find Christian Volunteering in Massachusetts">Massachusetts</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02108&distance=City&Submit=Submit" title="Find Boston Christian Volunteering">Boston</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02138&distance=5&Submit=Submit" title="Find Christian Volunteering in Cambridge, Massachusetts">Cambridge</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&postalcode=01902&distance=City&Submit=Submit" title="Find Christian Volunteering on the North Shore of Massachusetts">North Shore</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&postalcode=02184&distance=City&Submit=Submit" title="Find Christian Volunteering on the South Shore of Massachusetts">South Shore</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&postalcode=01109&distance=City&Submit=Submit" title="Find Christian Volunteering in Springfield / Worcester, Massachusetts">Springfield / Worcester</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=NH&Submit=Submit" title="Find Christian Volunteering in New Hampshire">New Hampshire</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=RI&Submit=Submit" title="Find Christian Volunteering in Rhode Island">Rhode Island</A></li>
	</ul>
<p><b>Browse by Service Area</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&region=USA+New+England&Submit=Submit" title="Find Boston Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://newyork.christianvolunteering.org" title="New York Volunteering in Urban Ministry and Short Term Missions">New York Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find New York Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find New York Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find New York Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find New York Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=New+York&Submit=Submit">New York City</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Bronx&state=NY&Submit=Submit">The Bronx</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Brooklyn&state=NY&Submit=Submit">Brooklyn</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Manhattan&state=NY&Submit=Submit">Manhattan</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Queens&state=NY&Submit=Submit">Queens</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Staten+Island&state=NY&Submit=Submit">Staten Island</a></li>
	<!--PJC(11/13/07)<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Elmhurst&state=NY&Submit=Submit">Elmhurst</a></li>-->
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=NY&Submit=Submit">New York State</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=NY&Submit=Submit" title="Find New York Christian Volunteering in administrative service area">Administrative</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=NY&Submit=Submit" title="Find New York Christian Volunteering in children and youth service area">Children and Youth</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=NY&Submit=Submit" title="Find New York Christian Volunteering in community development service area"/>Community Development</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=NY&Submit=Submit" title="Find New York Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=NY&Submit=Submit" title="Find New York Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=NY&Submit=Submit" title="Find New York Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=NY&Submit=Submit" title="Find New York Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=NY&Submit=Submit" title="Find New York Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=NY&Submit=Submit" title="Find New York Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=NY&Submit=Submit" title="Find New York Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=NY&Submit=Submit" title="Find New York Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=NY&Submit=Submit" title="Find New York Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=NY&Submit=Submit" title="Find New York Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=NY&Submit=Submit" title="Find New York Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=NY&Submit=Submit" title="Find New York Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=NY&Submit=Submit" title="Find New York Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=NY&Submit=Submit" title="Find New York Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://seattle.christianvolunteering.org" title="Seattle Volunteering in Urban Ministry and Short Term Missions">Seattle Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
    <li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Seattle Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Seattle Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Seattle Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Seattle Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Seattle&state=WA&Submit=Submit">Seattle</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Tacoma&state=WA&Submit=Submit">Tacoma</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Everett&state=WA&Submit=Submit">Everett</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=WA&Submit=Submit">Washington</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&affiliation=&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=WA&Submit=Submit" title="Find Seattle Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>
<hr />
<b><a href="http://twincities.christianvolunteering.org" title="Twin Cities Volunteering in Urban Ministry and Short Term Missions">Twin Cities Volunteering in Urban Ministry and Short Term Missions</a></b>
<p><b>Browse by Type</b></p>
<ul>
    <li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4795&Submit=Submit" title="Find Twin Cities Christian Virtual Volunteer Opportunities ">Virtual Volunteering</A></li>
    <li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&focusare4=Yes&Submit=Submit" title="Find Twin Cities Christian Group Volunteer Opportunities here">Group Volunteering</A></li>
	<li><A HREF="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&Submit=Submit" title="Find Twin Cities Christian Short-term Urban Missions Volunteer Opportunities here">Short Term Mission Trips</A></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=4796&focusare1=Yes&focusare3=Yes&Submit=Submit" title="Find Twin Cities Christian Short-term Urban Missions Volunteer Opportunities here">Youth and Junior High Mission Trips</A></li>
</ul>
<p><b>Browse by Location</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=Minneapolis&Submit=Submit">Minneapolis</a></li>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&city=St.+Paul&Submit=Submit">St. Paul</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=MN&Submit=Submit">Minnesota</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&state=WI&Submit=Submit">Wisconsin</a></li>
</ul>
<p><b>Browse by Service Area</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4759&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in administrative service area">Administrative</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4763&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in children and youth service area">Children and Youth</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4765&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in community development service area"/>Community Development</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4768&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in education and literacy service area">Education and Literacy</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4770&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in employment training service area">Employment Training</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4774&state=MNSubmit=Submit" title="Find Twin Cities Christian Volunteering in food ministry/hunger service area">Food Ministry / Hunger</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4776&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in homelessness and housing service area">Homelessness and Housing</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&servicearea1=4777&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in immigrants and refugees service area">Immigrants and Refugees</a></li>
</ul>
<p><b>Browse by Skills / Career</b></p>
<ul>
    <li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4720&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in administrator or office skills">Administrator / Office Skills</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4723&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in artist skills">Artist (Performing / Creative)</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4728&affiliation=&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in computer or tech support skills">Computer / Tech Support</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4730&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in computer trainer skills">Computer Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4740&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in fundraiser skills">Fundraiser</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4741&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in grant or proposal writer skills">Grant / Proposal Writer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4753&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in teacher or trainer skills">Teacher / Trainer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4756&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in web or graphic designer skills">Web / Graphics Designer</a></li>
	<li><a href="<%=aszPortal%>/oppsrch.do?method=processOppSearchAll&postype=&skills1id=4757&state=MN&Submit=Submit" title="Find Twin Cities Christian Volunteering in Youth Worker or Childcare skills">Youth Worker / Childcare</a></li>
</ul>


		 



					

					
<hr />
<h5>ABOUT</h5>

					<ul><li>

					<a href="<%=aszPortal%>/about.jsp" title="About Website with Christian Volunteer, Church volunteers, Urban Ministry Service, and Short Term Missions Opportunities">About ChristianVolunteering.org</a></li>

					<li><a href="<%=aszPortal%>/biblical.jsp">Why ChristianVolunteering.org</a></li>

					<li><a href="http://www.techmission.org/">About TechMission</a></li>

					<li><a href="<%=aszPortal%>/FAQs.jsp">Frequently Asked Questions</a></li>					

					<li><a href="http://www.urbanministry.org/staff">Staff </a></li>

                    <li><a href="http://www.techmission.org/cms/tm/board">Board</a></li>

                    </ul>

                 	

                 	

					<a href="http://www.urbanministry.org/terms">Privacy Policy</a><br>

					<a href="https://www.urbanministry.org/civicrm/contribute/transact?reset=1&id=13">Donate</a><br>

					<a href="<%=aszPortal%>/contact.jsp">Contact Us</a><p>

					<hr />

					

					

															

					<h5><a href="<%=aszPortal%>/volunteerlistings.jsp" color="blue" title="Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS">SEE ALL Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS</a></h5><br>

					<h5><a href="<%=aszPortal%>/organizationlistings.jsp" color="blue" title="Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service VOLUNTEER LISTINGS">SEE ALL Urban Ministry, and Short Term Missions, Virtual Volunteering, and Church Service ORGANIZATION LISTINGS</a></h5>

					

					

					

					

					</blockquote>

</div>

            

            </div>

</div>

<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>

<!-- end sidebar information -->

<!-- start footer information -->

<%@ include file="/template/footer.inc" %><!-- end footer information -->