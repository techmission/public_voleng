
	/* Variables for personality test */
var introvert = 0;
var e = 0;
var s = 0;
var n = 0;
var f = 0;
var t = 0;
var j = 0;
var p = 0;


var individualForm = document.forms.individualForm;

//number of questions
var numQuestions = parseInt(individualForm.numberofquestions.value);


function getSelectedRadio(buttonGroup) {
   // returns the array number of the selected radio button or -1 if no button is selected
   if (buttonGroup[0]) { // if the button group is an array (one button is not an array)
      for (var i=0; i<buttonGroup.length; i++) {
         if (buttonGroup[i].checked) {
            return i
         }
      }
   } else {
      if (buttonGroup.checked) { return 0; } // if the one button is checked, return zero
   }
   // if we get to this point, no radio button is selected
   return -1;
} // Ends the "getSelectedRadio" function

function getSelectedRadioValue(buttonGroup) {
   // returns the value of the selected radio button or "" if no button is selected
   var i = getSelectedRadio(buttonGroup);
   if (i == -1) {
      return "";
   } else {
      if (buttonGroup[i]) { // Make sure the button group is an array (not just one button)
         return buttonGroup[i].value;
      } else { // The button group is just the one button, and it is checked
         return buttonGroup.value;
      }
   }
} // Ends the "getSelectedRadioValue" function

function tallyScores(){
	var individualForm = document.forms.individualForm;
	var answerSelected = true;
	for (i = 1; i <= numQuestions; i++){
		var name = i + '';
		var selected = getSelectedRadioValue(document.getElementsByName(name));
		
		switch(selected){
		case "e":
		{
			//answerSelected = true;	
			e++;
			break;
		}
		case "i":
		{
			//answerSelected = true;
			introvert++;
			break;
		}
		case "s":
		{
			//answerSelected = true;
			s++;
			break;
		}
		case "n":
		{
			//answerSelected = true;
			n++;
			break;
		}
		case "f":
		{
			//answerSelected = true;
			f++;
			break;
		}
		case "t": 
		{
			//answerSelected = true;
			t++;
			break;
		}
		case "j":
		{
			//answerSelected = true;
			j++;
			break;
		}
		case "p":
		{
			//answerSelected = true;
			p++;
			break;
		}
		case "": {
			answerSelected = false;
		}
		default:{
			break;
		}
		}
	} 
	
	if(!answerSelected){
		alert("Please answer all of the questions.");
	}
	else {
		//myForm.personalityi = introvert;

	
		individualForm.personalitypageno.value = 1 + parseInt(individualForm.personalitypageno.value);
		individualForm.personalitytypee.value = e + parseInt(individualForm.personalitytypee.value);
		individualForm.personalitytypei.value = introvert + parseInt(individualForm.personalitytypei.value);
		individualForm.personalitytypes.value = s + parseInt(individualForm.personalitytypes.value);
		individualForm.personalitytypen.value = n + parseInt(individualForm.personalitytypen.value);
		individualForm.personalitytypef.value = f + parseInt(individualForm.personalitytypef.value);
		individualForm.personalitytypet.value = t + parseInt(individualForm.personalitytypet.value);
		individualForm.personalitytypej.value = j + parseInt(individualForm.personalitytypej.value);
		individualForm.personalitytypep.value = p + parseInt(individualForm.personalitytypep.value);
		
		document.forms["individualForm"].submit();
	}
	
}

function skip(){
	var individualForm = document.forms.individualForm;
	//alert(individualForm.personalityTypeDrop.value);
	individualForm.personalitytypetid.value = individualForm.personalityTypeDrop.value;
	document.forms["individualForm"].elements["method"].value = "showPersonalityMinistryAreas";
	document.forms["individualForm"].submit();
}

function calculatePercentage(personalityType)
{
	var numOfPersonalityAnswers = 0; 
	switch(personalityType){
		case "e":
		{
			numOfPersonalityAnswers = individualForm.personalitytypee.value;
			break;
		}
		case "i":
		{
			numOfPersonalityAnswers = individualForm.personalitytypei.value;
			break;
		}
		case "s":
		{
			numOfPersonalityAnswers = individualForm.personalitytypes.value;
			break;
		}
		case "n":
		{
			numOfPersonalityAnswers = individualForm.personalitytypen.value;
			break;
		}
		case "f":
		{
			numOfPersonalityAnswers = individualForm.personalitytypef.value;
			break;
		}
		case "t": 
		{
			numOfPersonalityAnswers = individualForm.personalitytypet.value;
			break;
		}
		case "j":
		{
			numOfPersonalityAnswers = individualForm.personalitytypej.value;
			break;
		}
		case "p":
		{
			numOfPersonalityAnswers = individualForm.personalitytypep.value;
			break;
		}
		}
	
	var percentage = (numOfPersonalityAnswers / 13) * 100;
	percentage = parseInt(percentage);
	return percentage;
}