// JavaScript Document

<script language="javascript">

function topopp_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='inline';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "active";
document.getElementById('org').className = "";
document.getElementById('stm').className = "";
}

function toporg_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='inline';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "";
document.getElementById('org').className = "active";
document.getElementById('stm').className = "";
}

function topstm_search()
{
document.getElementById('cvsearch').style.display='inline';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='inline';
document.getElementById('searchumcontent').style.display='none';

document.getElementById('cont').className = "";
document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stm').className = "active";
}

function umcont_search()
{
document.getElementById('cvsearch').style.display='none';
document.getElementById('cvoppsearch').style.display='none';
document.getElementById('cvorgsearch').style.display='none';
document.getElementById('cvstmsearch').style.display='none';
document.getElementById('searchumcontent').style.display='inline';

document.getElementById('cont').className = "active";
document.getElementById('opp').className = "";
document.getElementById('org').className = "";
document.getElementById('stm').className = "";
}

function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = "";
}
</script>
