<?php 
//  $objConnect=new mysqli("localhost","root","")or die("connection error");
//  $objDb=mysqli_select_db($objconnect,"trashtracker")or die("error in selection");
	//database constants
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'trashtracker');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
    }

$wastetype=$_POST['waste_type'];
$userid=$_POST['user_id'];
$quantity=$_POST['quantity'];
$description=$_POST['description'];
$latitude=$_POST['latitude'];
$longitude=$_POST['longitude'];
$phonenumber=$_POST['phone'];
$st=$_POST['status'];

$query="INSERT INTO homelywaste(waste_type,user_id,quantity,description,latitude,longitude,status,reported_mobile) VALUES('$wastetype','$userid','$quantity','$description','$latitude','$longitude','$st','$phonenumber')";
$ex=mysqli_query($conn,$query) or die("cannot execute".mysqli_error($conn));

if(!$ex){

$arra['status']="0";
$arra['error']="cannot save data";


}else{

$arra['status']='1';
$arra['error']="";

}

echo json_encode($arra);

?>