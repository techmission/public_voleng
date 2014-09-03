<?php include("../includes/common.inc"); ?>
<?php include("../includes/bootstrap.inc"); ?>
<?php include("../includes/database.inc"); ?>
<?php include("../includes/database.mysql.inc"); ?>

<head>
<script language="javascript"> 
function subscribegroup(){ 
var myForm = document.forms.form1 ;
  if (myForm.groups.value == 20){  
	myForm.action='/user/subscribe?destination=subscribe/newsletters'; 
  }else{

		if (myForm.groups.value == 34){ 
			myForm.action='/user/subscribe?destination=subscribe/newsletters';
		}else{
			if (myForm.groups.value == 32){ 
				myForm.action='/user/subscribe?destination=subscribe/newsletters';
			}else{
				if (myForm.groups.value == 33){ 
					myForm.action='/user/subscribe?destination=subscribe/newsletters';
				}else{
					if (myForm.groups.value == 35){ 
						myForm.action='/user/subscribe?destination=subscribe/newsletters';
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
<div id="right" width="202" height="194">
<div id="block-obs-observed-today" class="clear-block block block-obs">

	<h3 class="section-title"><span class="hp--container">Subscribe to Newsletters</span></h3>

<form id="civicrm-subscribe-form" name="form1" action="/user/subscribe?destination=subscribe/newsletters" method="post">
    <div class="form-item"><label>Select Newsletter:</label><select onchange="subscribegroup()" name="groups" id="edit-groups">
    <option value="33">AC4 & Technology</option>
    <option value="34">Ministry Grants</option>
    <option value="32">Safe Families</option>
    <option value="20">UrbanMinistry.org</option>
    <option value="35" selected="selected">Volunteer Management</option>
    </select>
    <div class="form-item"><label for="edit-email">Email Address: </label> <input type="text" class="form-text required" id="edit-email" maxlength="128" name="email" /></div>
    <input type="submit" class="form-submit" id="edit-submit" name="op" value="Subscribe" /> <input type="hidden" id="edit-civicrm-subscribe-form-form-token" name="form_token" value="<?php echo drupal_get_token('civicrm_subscribe_form'); ?>" /> <input type="hidden" id="edit-civicrm-subscribe-form" name="form_id" value="civicrm_subscribe_form" /></div>
</form>

</div>
</div>
</body>
</html>
