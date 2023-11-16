<?php

	include('config.php');

$lat = $_POST['lat'];
$lng = $_POST['lng'];

$query="INSERT INTO wastebin VALUES('','$lat','$lng')";
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