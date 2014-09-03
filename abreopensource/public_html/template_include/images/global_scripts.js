var curtMenu
function currentMenu() {
curtMenu = document.forms[0].MenuImage.value;
if (document.images[curtMenu]) {
parts = document.images[curtMenu].src.split(".jpg");
document.images[curtMenu].src = parts[0] + '_selected.jpg?openelement';
}
window.status = '';
}
if(window.event + "" == "undefined") event = null;
function HM_f_PopUp(){return false};
function HM_f_PopDown(){return false};
popUp = HM_f_PopUp;
popDown = HM_f_PopDown;
function MM_findObj(n, d) {
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}
function MM_swapImage() {
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array;if(a[0] == curtMenu) {return false;};for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_swapImgRestore() {
  var i,x,a=document.MM_sr;if(a[0] == curtMenu) {window.alert("goodbye")};for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function buildurl (query){
var tmpURL = window.document.URL;
var tmpLength = tmpURL.indexOf(".nsf");
if (tmpLength > 0) {
var fullURL = tmpURL.substring(0,(tmpLength+4));
} else {
var fullURL = "/ihq/www_sa.nsf"
}
if (query == "") {
	alert("Please enter something to search for");
	return;
}
else{
	url= fullURL + "/fm-search?openform&query="+query+"&start=1"
	var resultwin= window.open(url,"_self")
	}
}
function popup(filename,windowname,properties) {
    mywindow = window.open(filename,windowname,properties);
}
