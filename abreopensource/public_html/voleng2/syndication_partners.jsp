<%!
  public abstract static class SyndicationPartner {
    public static SyndicationPartner[][] convertToPairs(SyndicationPartner[] partners) {
      SyndicationPartner[][] pairs;
	  int m = partners.length/2 + ((partners.length % 2) > 0 ? 1 : 0);
	  pairs = new SyndicationPartner[m][];
	  for(int i = 0; i < m; i++) {
	    int remainder = partners.length - i*2;
	    int n = remainder >= 2 ? 2 : remainder%2;
		pairs[i] = new SyndicationPartner[n];
		for(int j = 0; j < n; j++) {
		  pairs[i][j] = partners[i*2 + j];
		}
	  }
	  return pairs;
    }
  
    public String iconURL() {
	  return "/images/syndication_partners/" + this.iconFile();
	}
  	protected abstract String iconFile();
	public abstract String linkURL();
	public abstract String linkText();
	public abstract String blurb();
  }
  
%>

<%
  SyndicationPartner[][] partnersTo = SyndicationPartner.convertToPairs(
    new SyndicationPartner[] {
      new SyndicationPartner() {
                protected String iconFile() {
                  return "meettheneed.png";
                }
                public String linkURL() {
                  return "http://meettheneed.org" ;
                }
                public String linkText() {
                  return "meettheneed.org";
                }
                public String blurb() {
                  return "Meet the Need is a not-for-profit charity whose mission is to dramatically increase the impact churches and ministries have in their communities by mobilizing more people to loving acts of service.";
                }
      },
      new SyndicationPartner() {
		protected String iconFile() {
		  return "simplyhired.png";
		}
		public String linkURL() {
		  return "http://simplyhired.com"; 
		}
		public String linkText() {
		  return "simplyhired.com";
		}
		public String blurb() {
		  return "Simply Hired is a employment website for job listings and online recruitment advertising network. The company aggregates job listings from thousands of sites across the Web including job boards, newspaper and classified listings, associations, social networks, content sites and company career sites.";
		}
	  },
	  new SyndicationPartner() {
		protected String iconFile() {
		  return "allforgood.png";
		}
		public String linkURL() {
		  return "http://allforgood.org" ;
		}
		public String linkText() {
		  return "allforgood.org";
		}
		public String blurb() {
		  return "All for Good's mission is to facilitate volunteerism and community service. To meet that goal, they have developed a custom volunteer opportunity oriented search engine that is powered by the largest database of volunteer opportunities on the Internet.";
		}
	  },
	  new SyndicationPartner() {
		protected String iconFile() {
		  return "trovit.png";
		}
		public String linkURL() {
		  return "http://trovit.com" ;
		}
		public String linkText() {
		  return "trovit.com";
		}
		public String blurb() {
		  return "Trovit is a vertical search engine for classifieds (jobs, cars, real estate, products). As of November 2008 it is the fifth largest real estate website in Spain based on number of monthly users.";
		}
	  },
	  new SyndicationPartner() {
		protected String iconFile() {
		  return "olx.png";
		}
		public String linkURL() {
		  return "http://olx.com" ;
		}
		public String linkText() {
		  return "olx.com";
		}
		public String blurb() {
		  return "OLX is an internet company based in New York City and Buenos Aires, Argentina. The OLX website hosts free user-generated classified advertisements for urban communities around the world and provides discussion forums sorted by various topics.";
		}
	  },
	  new SyndicationPartner() {
		protected String iconFile() {
		  return "jobinventory.png";
		}
		public String linkURL() {
		  return "http://jobinventory.com" ;
		}
		public String linkText() {
		  return "jobinventory.com";
		}
		public String blurb() {
		  return "Jobiventory.com is a search engine with a singular goal: To give job seekers the quickest access to the largest selection of jobs from the widest variety of sources on the Internet, and do it all without the influence of paid advertising.";
		}
	  },
	  new SyndicationPartner() {
		protected String iconFile() {
		  return "jobisjob.png";
		}
		public String linkURL() {
		  return "http://jobisjob.com" ;
		}
		public String linkText() {
		  return "jobisjob.com";
		}
		public String blurb() {
		  return "JobisJob is a search engine for job offers. JobisJob.com includes all the job listings from major job boards and continues to add new sites every day.";
		}
	},
        new SyndicationPartner() {
                protected String iconFile() {
                  return "jobsearchengine.png";
                }
                public String linkURL() {
                  return "http://job-search-engine.com" ;
                }
                public String linkText() {
                  return "job-search-engine.com";
                }
                public String blurb() {
                  return "Juju.com is a job search engine, not a job board. Juju's comprehensive search results link to millions of jobs found on thousands employer career portals, recruiter websites, job boards, and other employment sites all over the Internet, rather than to a limited set of job postings hosted directly on our own site.";
                }
        },
        new SyndicationPartner() {
                protected String iconFile() {
                  return "yakaz.png";
                }
                public String linkURL() {
                  return "http://yakaz.com" ;
                }
                public String linkText() {
                  return "yakaz.com";
                }
                public String blurb() {
		  return "Yakaz is a place for people to meet and exchange locally. It is a free platform to discover local life, post ads or recommend stuff you like. It is also a network for social interactions with people around you.";
                }
        },
        new SyndicationPartner() {
                protected String iconFile() {
                  return "oodle.png";
                }
                public String linkURL() {
                  return "http://oodle.com" ;
                }
                public String linkText() {
                  return "oodle.com";
                }
                public String blurb() {
                  return "Oodle provides consumers with a friendly local marketplace to buy, sell and trade.";
                }
        },
        new SyndicationPartner() {
                protected String iconFile() {
                  return "workhound.png";
                }
                public String linkURL() {
                  return "http://workhound.co.uk" ;
                }
                public String linkText() {
                  return "workhound.co.uk";
                }
                public String blurb() {
                  return "Workhound is the UK's largest job search engine. Listing thousands of jobs from all the major UK job boards, agencies and employers. It provides a one stop shop for job seekers looking for that all important career move.";
                }
        },
        new SyndicationPartner() {
                protected String iconFile() {
                  return "wowjobs.png";
                }
                public String linkURL() {
                  return "http://wowjobs.ca" ;
                }
                public String linkText() {
                  return "wowjobs.ca";
                }
                public String blurb() {
                  return "Wowjobs.ca is Canada’s largest job search engine. Wowjobs was founded with the objective of fundamentally changing the way in which job seekers look for jobs and the way employers scout for talent.";
                }
        }


      }
  );

  SyndicationPartner[][] partnersFrom = SyndicationPartner.convertToPairs(
    new SyndicationPartner[] {
      new SyndicationPartner() {
                protected String iconFile() {
                  return "shorttermmissions.png";
                }
                public String linkURL() {
                  return "http://shorttermmissions.com" ;
                }
                public String linkText() {
                  return "shorttermmissions.com";
                }
                public String blurb() {
                  return "ShortTermMissions.com is coordinated, produced, and maintained by Mission Data International (M-DAT) and its volunteers. M-DAT is an 501(c)(3) non-profit ministry that provides web services to help people make it to the mission field.";
                }
      },
      new SyndicationPartner() {
                protected String iconFile() {
                  return "meettheneed.png";
                }
                public String linkURL() {
                  return "http://meettheneed.org" ;
                }
                public String linkText() {
                  return "meettheneed.org";
                }
                public String blurb() {
                  return "Meet the Need is a not-for-profit charity whose mission is to dramatically increase the impact churches and ministries have in their communities by mobilizing more people to loving acts of service.";
                }
      },
      new SyndicationPartner() {
                protected String iconFile() {
                  return "oscar.png";
                }
                public String linkURL() {
                  return "http://oscar.org.uk" ;
                }
                public String linkText() {
                  return "oscar.org.uk";
                }
                public String blurb() {
		  return "OSCAR is designed to take the 'leg work' out of finding missions related information. It helps you locate the organisations, services and resources you need and puts you in complete control of managing your own situation, whether you're a cross-cultural worker, supporter or enquirer.";
                }
      },
      new SyndicationPartner() {
                protected String iconFile() {
                  return "simplyhired.png";
                }
                public String linkURL() {
                  return "http://simplyhired.com";
                }
                public String linkText() {
                  return "simplyhired.com";
                }
                public String blurb() {
                  return "Simply Hired is a employment website for job listings and online recruitment advertising network. The company aggregates job listings from thousands of sites across the Web including job boards, newspaper and classified listings, associations, social networks, content sites and company career sites.";
                }
          },
          new SyndicationPartner() {
                protected String iconFile() {
                  return "allforgood.png";
                }
                public String linkURL() {
                  return "http://allforgood.org" ;
                }
                public String linkText() {
                  return "allforgood.org";
                }
                public String blurb() {
                  return "All for Good's mission is to facilitate volunteerism and community service. To meet that goal, they have developed a custom volunteer opportunity oriented search engine that is powered by the largest database of volunteer opportunities on the Internet.";
                }
          },
          new SyndicationPartner() {
                protected String iconFile() {
                  return "interserve.png";
                }
                public String linkURL() {
                  return "http://interserve.org" ;
                }
                public String linkText() {
                  return "interserve.org";
                }
                public String blurb() {
                  return "Interserve is an interdenominational and truly international community of Christians, bringing God’s love to the peoples of Asia and the Arab World through word and action.";
                }
          }


    }
  );

%>

<!-- start JSP information -->
<%@ include file="/template_include/topjspnologin1.inc" %>
<!-- end JSP information -->

<!-- start header information -->
<%@ include file="/template/header.inc" %>
<!-- end header information -->

<!-- start navigation information -->
<%@ include file="/template/navigation.inc" %>
<!-- end navigation information -->

<% if(aszNoSearchNav=="true"){ %>
<% }else if(b_includeAjaxNavigation==true){ %>
  <div id="oppsearch" class="solr">
	  <span id="title">Syndication Partners</span>
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
<span style="float: left;">Sitemap </span>
<div id="banner_side_graphic">&nbsp;&nbsp;</div>
<div id="breadcrumbs"><a href="<%=aszPortal%>/">home</a> &gt; sitemap </div>

</div>
<% } %>

<style>   
  
  table.syndication_partners {
    width: 650px;
  }
  
  table.syndication_partners table {
    width: 100%;
  }
  
  /* cell for each partner */
  table.syndication_partners > tbody > tr > td {
    border: 1px solid black;
    width: 50%;
  }
  
  /* div that contains logo and link for each partner */
  table.syndication_partners > tbody > tr > td > div:first-child {
    padding-bottom: 5px;
    border-bottom: 1px solid gray;
	margin: 5px;
  }
  
  /* cell that contains logo div */
  table.syndication_partners table tr td:first-child {
  	text-align: left;
  }  
  
  /* div that contains logo */
  table.syndication_partners table tr td:first-child div {
    text-align: left;
  }

  /* cell that contains link div */
  table.syndication_partners table td:last-child {
  
  } 
  
  /* div that contains link */
  table.syndication_partners table td:last-child div { 
    padding-right: 20px;
    text-align: right;
  }
  
  table.syndication_partners table td:last-child div a {
  }
  
  
  /* div that contains blurb about each partner */
  table.syndication_partners > tbody > tr > td > div:last-child {
  	padding: 10px;
  }
 
  
</style>


<div id="body">

  <h1>Syndication Partners</h1>

  <h2>We receive data from:</h2>
  
  <table class="syndication_partners" cellspacing="20px;">
   <tbody>
    <% for(SyndicationPartner[] pair : partnersFrom) { %>
      <tr>
	    <% for(SyndicationPartner p : pair) { %>
		  <td>
		    <div>
		      <table>
			   <tbody>
			    <tr>
			      <td>
				    <div>
		              <img width="140px" src="<%=p.iconURL()%>"/>
					</div>
				  </td>
				  <td>
				    <div>
			          <a href="<%=p.linkURL()%>"><%=p.linkText()%></a>
					</div>
				  </td>
		        </tr>
			    </tbody>
			  </table>
			</div>
			<div>
			  <%=p.blurb()%>
			</div>
		  </td>
		<% } %>
	  </tr>
    <% } %>
    <tr>
      <td colspan="2" style="border:0px;">
        We also receive data from IRS Nonprofit Database, HandsOn Network, AARP, Idealist.org, United Way, Truist, Habitat for Humanity, Service Nation, Universal Giving, ChristianVolunteering.org, Craigslist, MENTOR, Senior Corps, AmeriCorps, Girl Scouts, YMCA, Up2Us, CatchaFire, Volunteer.gov, Rock the Vote, Citizen Corps, Red Cross, Samaritan Technologies, Catchafire
      </td>
    </tr>
   </tbody>
  </table>

  <h2>We provide data to:</h2>

  <table class="syndication_partners" cellspacing="20px;">
   <tbody>
    <% for(SyndicationPartner[] pair : partnersTo) { %>
      <tr>
            <% for(SyndicationPartner p : pair) { %>
                  <td>
                    <div>
                      <table>
                           <tbody>
                            <tr>
                              <td>
                                    <div>
                              <img width="140px" src="<%=p.iconURL()%>"/>
                                        </div>
                                  </td>
                                  <td>
                                    <div>
                                  <a href="<%=p.linkURL()%>"><%=p.linkText()%></a>
                                        </div>
                                  </td>
                        </tr>
                            </tbody>
                          </table>
                        </div>
                        <div>
                          <%=p.blurb()%>
                        </div>
                  </td>
                <% } %>
          </tr>
    <% } %>
   </tbody>
  </table>

</div>

</div>


<% if( b_includeLeftSidebar==false ){ %>
<%@ include file="/template/sidebar.inc" %>
<% } %>
<!-- end sidebar information -->

<!-- start footer information -->
<%@ include file="/template/footer.inc" %><!-- end footer information -->
