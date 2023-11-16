<?php

$conn = mysqli_connect("localhost","root","") or die("connection error".mysqli_error());
$objDB = mysqli_select_db($conn,"trashtracker") or die("error in selection".mysqli_error());

    $mid=$_POST['mid'];
    

    $mysqli_qry = "select * from ward_registration where id='$mid'";

    $result = mysqli_query($conn,$mysqli_qry) or die("error in selection".mysqli_error());
    
    $row = mysqli_fetch_row($result);
    
    if(mysqli_num_rows($result) > 0) 
    {
        $arr['status'] = "1"; 
        $arr['message'] = "";	

        $arr['id'] = $row[0];
        $arr['mobilenumber'] = $row[3];
       
    }
    else 
    {
        $arr['status'] = "0"; 
        $arr['message'] = "Invalid User Info!";

        $arr['id'] ="";
       $arr['mobilenumber'] ="";
      
    }
    
    mysqli_close($conn);
    
    echo json_encode($arr);

    
?>