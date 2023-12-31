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
	$stmt = $conn->prepare("SELECT bin_id,latitude,longitude FROM wastebin");

	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($bin_id,$latitude,$longitude);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['bin_id'] = $bin_id; 
        $temp['latitude'] = $latitude;
        $temp['longitude'] = $longitude;    
        
        
	array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);
?>

