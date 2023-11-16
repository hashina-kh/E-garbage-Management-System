<?php

$conn = mysqli_connect("localhost","root","") or die("connection error".mysqli_error());
$objDB = mysqli_select_db($conn,"trashtracker") or die("error in selection".mysqli_error());

    $phonenumber=$_POST['phonenumber'];
    $password=$_POST['password'];

    $mysqli_qry = "select * from registration where mobilenumber='$phonenumber' && password='$password'";

    $result = mysqli_query($conn,$mysqli_qry) or die("error in selection".mysqli_error());
    
    $row = mysqli_fetch_row($result);
    
    if(mysqli_num_rows($result) > 0) 
    {
        $arr['status'] = "1"; 
        $arr['error'] = "";	

        $arr['id'] = $row[0];
        $arr['name'] = $row[1];
        $arr['email'] = $row[2];
        $arr['address'] = $row[5];
        $arr['phonenumber'] = $row[3];
    }
    else 
    {
        $arr['status'] = "0"; 
        $arr['error'] = "Invalid User Info!";

        $arr['id'] = "";
        $arr['name'] = "";
        $arr['email'] = "";
        $arr['address'] = "";
        $arr['phonenumber'] = "";
    }
    
    mysqli_close($conn);
    
    echo json_encode($arr);

    
?>