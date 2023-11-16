<?php 

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'trashtracker');
	
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
    }

$name=$_POST['name'];
$location=$_POST['location'];
$type=$_POST['type'];
$id=$_POST['id'];
$ward=$_POST['ward'];


 
   $Query=mysqli_query($conn,"SELECT * FROM waste WHERE id='$id' && type='$type'");
    $numrows=mysqli_num_rows($Query);
    if($numrows>0){
        $arra['status']="0";
        $arra['message']="Already Registered.Please Try again";
    }

    else{


$query="insert into waste(name,location,type,id,ward) values('$name','$location','$type','$id','$ward')" ;
$ex=mysqli_query($conn,$query) or die("cannot execute".mysqli_error());

if(!$ex){

$arra['status']="0";
$arra['message']="cannot save data";


}else{

$arra['status']='1';
$arra['message']="";

}
}

// mysqli_close($objConnect);
echo json_encode($arra);


?>
