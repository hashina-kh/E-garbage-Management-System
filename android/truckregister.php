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



$name=$_POST['name'];
$phonenumber=$_POST['phone'];
$email=$_POST['email'];
$address=$_POST['address'];
$password=$_POST['password'];




$query="insert into truckdriver(name,mobilenumber,email,address,password) values('$name','$phonenumber','$email','$address','$password')" ;
$ex=mysqli_query($conn,$query) or die("cannot execute".mysqli_error());

if(!$ex){

$arra['status']="0";
$arra['error']="cannot save data";


}else{

$arra['status']='1';
$arra['error']="";

}

// mysqli_close($objConnect);
echo json_encode($arra);


?>
