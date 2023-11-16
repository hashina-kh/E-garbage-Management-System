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
$location=$_POST['location'];
$description=$_POST['description'];
$wid=$_POST['wid'];
$type=$_POST['type'];
$id=$_POST['id'];
$ward=$_POST['ward'];
$userid=$_POST['userid'];
// $driverid=$_POST['driverid'];
// $drivername=$_POST['drivername'];

$query="INSERT INTO waste_report(name,location,description,wid,type,mid,ward,userid,status,driverid,drivername) VALUES('$name','$location','$description','$wid','$type','$id','$ward','$userid','','','')" ;
$ex=mysqli_query($conn,$query) or die("cannot execute".mysqli_error());

if(!$ex){

$arra['status']="0";
$arra['message']="cannot save data";


}else{

$arra['status']='1';
$arra['message']="";

}

// mysqli_close($objConnect);
echo json_encode($arra);


?>
