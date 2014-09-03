function createRequestObjectLite(){

var reqLite;

if(window.XMLHttpRequest){
//For Firefox, Safari, Opera
reqLite = new XMLHttpRequest();
}
else if(window.ActiveXObject){
//For IE 5+
reqLite = new ActiveXObject("Microsoft.XMLHTTP");
}
else{
//Error for an old browser
alert('Your browser is not IE 5 or higher, or Firefox or Safari or Opera');
}

return reqLite;
}

//Make the XMLHttpRequest Object
var httpLite = createRequestObject();

function sendRequestLite(method, url){
if(method == 'get' || method == 'GET'){
http.open(method,url);
http.onreadystatechange = handleResponse;
http.send(null);
}
}

function handleResponseLite(){
if(http.readyState == 4 && http.status == 200){
var response = http.responseText;
if(response){
document.getElementById("maincontent").innerHTML = response;
}
}     
}
