<script> FB.api('/me', function(response) {
		   //alert('query');
		   		var query = FB.Data.query('SELECT uid, name, pic_square FROM user WHERE is_app_user=1 and uid IN (SELECT uid2 FROM friend WHERE uid1 = me())');
				query.wait(function(){
					//alert(rows[0].name);
					FB.Array.forEach(query.value, function(row){
						var fb_uid = row.uid;
						exclude_ids += row.uid + ",";
					});
				});
			});
</script>
<% if(aszSecondaryHost.equalsIgnoreCase("volengivol")){ %>
<fb:serverfbml style="width: 776px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://facebook.ivolunteering.org/register.do?method=showFacebookFriendsContainer" method="POST" invite="true" type="WorldChanger" content="Join me on WorldChanger, where you can find out your personality type and find ministry opportunities that fit your personality! <fb:req-choice url='http://apps.facebook.com/worldchanger' label='Join'"> 
  <fb:multi-friend-selector cols="3" showborder="false" exclude_ids=exclude_ids actiontext="Invite your Facebook Friends to use WorldChanger" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml>
  
<% } else { %>
<fb:serverfbml style="width: 776px;">
 <script type="text/fbml">
 <fb:fbml>
  <fb:request-form action="http://facebook.christianvolunteering.org/register.do?method=showFacebookFriendsContainer" method="POST" invite="true" type="Find Your Calling" content="Join me on FindYourCalling, where you can find out your personality type and find ministry opportunities that fit your personality! <fb:req-choice url='http://apps.facebook.com/find-your-calling' label='Join'>">
  <fb:multi-friend-selector cols="3" showborder="false" exclude_ids=exclude_ids actiontext="Invite your Facebook Friends to use Find Your Calling" /> 
  </fb:request-form> 
  </fb:fbml> 
  </script> 
  </fb:serverfbml>
  <% } %>