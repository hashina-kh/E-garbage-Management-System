<?php

	include('config.php');

$place = $_POST['place'];
$lat = $_POST['lat'];
$lng = $_POST['lng'];

$query="INSERT INTO disaster_camps VALUES('','$lat','$lng','$place')";
$r=mysqli_query($conn, $query) or die("error in deletion".mysqli_error());
if($r)
{
    ?>
    <script language="javascript">alert('Added Successfully');
    window.location.replace('home.html');
    </script>
    <?php
}

else
{
    ?>
    <script language="javascript">alert('Failed To Add');</script>
    <?php
}


?>