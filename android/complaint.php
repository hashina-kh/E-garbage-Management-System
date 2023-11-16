<?php

    # Connection...
    $con = new mysqli("localhost", "root", "") or die("connection error");
    $db = mysqli_select_db($con, "trashtracker") or die("error in database");

    # Fetching data using POST method...
	$userid = $_POST["user_id"];
    $complaint	= $_POST["complaints"];
   

    # Query execution
    $q = "INSERT INTO complaints VALUES ('', '$userid','$complaint')";
    $result = mysqli_query($con, $q);

    if ($result) {
        $response['status'] = "1";
        $response['message'] = "Successful";
    }
    else {
        $response['status'] = "0";
        $response['message'] = "Sending failed. Please try again!";
    }

    # Converting to JSON (JavaScript Object Notation) Format 
    echo json_encode($response);

?>