<?php

$conn = mysqli_connect("localhost","root","") or die("connection error".mysqli_error());
$objDB = mysqli_select_db($conn,"trashtracker") or die("error in selection".mysqli_error());

    $type=$_POST['type'];
    $ward=$_POST['ward'];

    $mysqli_qry = "select * from waste where type='$type' && ward='$ward'";

    $result = mysqli_query($conn,$mysqli_qry) or die("error in selection".mysqli_error());
    
    $row = mysqli_fetch_row($result);
    
    if(mysqli_num_rows($result) > 0) 
    {
        $arr['status'] = "1"; 
        $arr['message'] = "";	

        $arr['wid'] = $row[0];
        $arr['name'] = $row[1];
        $arr['location'] = $row[2];
        $arr['type'] = $row[3];
        $arr['id'] = $row[4];
        $arr['ward'] = $row[5];
    }
    else 
    {
        $arr['status'] = "0"; 
        $arr['message'] = "Invalid User Info!";

        $arr['wid'] ="";
        $arr['name'] = "";
        $arr['location'] = "";
        $arr['type'] = "";
        $arr['id'] = "";
        $arr['ward'] = "";
    }
    
    mysqli_close($conn);
    
    echo json_encode($arr);

    
?>