<?php

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
	
	//creating a query

        $id=$_POST['id'];


	$stmt = $conn->prepare("SELECT id,name,location,description,wid,type,mid,ward,userid,status,driverid,drivername,date,time FROM waste_report where status = 'assigned' && driverid='$id' ");

	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id,$name,$location,$description,$wid,$type,$mid,$ward,$userid,$status,$driverid,$drivername,$date,$time);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id;
		$temp['name'] = $name; 
		$temp['location'] = $location; 
		$temp['description'] = $description; 
        $temp['wid'] = $wid;
        $temp['type'] = $type;
        $temp['mid'] = $mid;  
        $temp['ward'] = $ward; 
         $temp['userid'] = $userid;
          $temp['status'] = $status;  
	       $temp['driverid'] = $driverid; 
	        $temp['drivername'] = $drivername; 
	         $temp['date'] = $date; 
	          $temp['time'] = $time; 

		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);
?>