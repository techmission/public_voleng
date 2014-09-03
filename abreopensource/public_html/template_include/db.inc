<%

   /* Create string of connection url within specified 
   format with machine name, 
    port number and database name. Here machine name id 
    localhost and database name is student. */
if(aszRemoteHost.contains(":7001")){
	connectionURL = "jdbc:mysql://localhost:3306/database";
}else{
	connectionURL="jdbc:mysql://192.168.0.1:3306/database";
}
userName="userName";
password="password";

%>