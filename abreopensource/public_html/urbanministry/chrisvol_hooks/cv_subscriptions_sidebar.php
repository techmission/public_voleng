<head>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">
<link href="style_subscribe.css" rel="stylesheet" type="text/css" />

<script language="javascript"> 
function subscribegroup(){ 
var myForm = document.forms.form1 ;
  if (myForm.groups.value == 20){  
	myForm.action='http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters'; 
  }else{

		if (myForm.groups.value == 34){ 
			myForm.action='http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters';
		}else{
			if (myForm.groups.value == 32){ 
				myForm.action='http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters';
			}else{
				if (myForm.groups.value == 33){ 
					myForm.action='http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters';
				}else{
					if (myForm.groups.value == 35){ 
						myForm.action='http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters';
					}
				}
			}
		}
	
  }
}
</script>


</head>

<body>


<!-- RIGHT COLUMN -->  
<div id="sidebar">
<div id="sidebar_content">

	<h5>SUBSCRIBE TO NEWSLETTERS</h5>

<form id="civicrm-subscribe-form" name="form1" target="_blank"  action="http://www.urbanministry.org/user/subscribe?destination=subscribe/newsletters" method="post">
    <div class="form-item"><label>Select Newsletter:</label><select onchange="subscribegroup()" name="groups" id="edit-groups"  class="smalldropdown">
    <option value="33">AC4 & Technology</option>
    <option value="34">Ministry Grants</option>
    <option value="32">Safe Families</option>
    <option value="20">UrbanMinistry.org</option>
    <option value="35" selected="selected">Volunteer Managing</option>
    </select>
    <div class="form-item"><label for="edit-email">Email Address: </label> <input type="text" class="form-text required" id="edit-email" size="18" maxlength="128" name="email" /></div>
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" /> <!--<input type="hidden" id="edit-civicrm-subscribe-form-form-token" name="form_token" value="<?php /* echo drupal_get_token('civicrm_subscribe_form'); */ ?>" />--> <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" /></div>
</form>

</div>
</div>
</body>
</html>
