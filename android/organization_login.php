<?php

$conn = mysqli_connect("localhost","root","") or die("connection error".mysqli_error());
$objDB = mysqli_select_db($conn,"trashtracker") or die("error in selection".mysqli_error());

    $phonenumber=$_POST['phonenumber'];
    $password=$_POST['password'];

    $mysqli_qry = "select * from organization_registration where mobilenumber='$phonenumber' && password='$password'";

    $result = mysqli_query($conn,$mysqli_qry) or die("error in selection".mysqli_error());
    
    $row = mysqli_fetch_row($result);
    
    if(mysqli_num_rows($result) > 0) 
    {
        $arr['status'] = "1"; 
        $arr['error'] = "";	

        $arr['id'] = $row[0];
        $arr['name'] = $row[1];
        $arr['email'] = $row[2];
        $arr['mobilenumber'] = $row[3];
        $arr['password'] = $row[4];
        $arr['address'] = $row[5];
      
    }
    else 
    {
        $arr['status'] = "0"; 
        $arr['error'] = "Invalid User Info!";

       $arr['id'] = "";
        $arr['name'] = "";
        $arr['email'] = "";
        $arr['mobilenumber'] = "";
        $arr['password'] = "";
        $arr['address'] ="";
    
    }
    
    mysqli_close($conn);
    
    echo json_encode($arr);

    
?>