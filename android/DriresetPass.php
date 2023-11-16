<?php

$conn = mysqli_connect("localhost","root","") or die("connection error".mysqli_error());
$objDB = mysqli_select_db($conn,"trashtracker") or die("error in selection".mysqli_error());

    $Passworrd=$_POST['password'];
    $mobileNum=$_POST['mobile_num'];

    $q = "UPDATE truckdriver SET password = '$Passworrd' WHERE mobilenumber = '$mobileNum' ";


    $res = mysqli_query($conn,$q) or die("Error in query");

    if($res){

        $arra['status']="1";
        $arra['error']="Password Updated Successfully";
        
        
        }else{
        
        $arra['status']='0';
        $arra['error']="";
        
        }

        echo json_encode($arra);


?>