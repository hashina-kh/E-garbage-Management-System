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
	$stmt = $conn->prepare("SELECT id,waste_type,quantity,reported_mobile,latitude,longitude,description FROM homelywaste where status != 'COMPLETED'");

	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id,$wastetype,$quantity,$mobilenumber,$latitude,$longitude,$description);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id;
		$temp['wastetype'] = $wastetype; 
		$temp['quantity'] = $quantity; 
        $temp['mobile'] = $mobilenumber;
        $temp['latitude'] = $latitude;
        $temp['longitude'] = $longitude;  
        $temp['description'] = $description; 
	
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);
?>
