<?php

	include('config.php');

$id=$_GET['id'];

$query="UPDATE truckdriver SET status='approved' WHERE id='$id'";
$r=mysqli_query($conn, $query) or die("error in deletion".mysqli_error());
if($r)
{
    ?>
    <script language="javascript">alert('Approved Successfully');
    window.location.replace('drivers.php');
    </script>
    <?php
}

else
{
    ?>
    <script language="javascript">alert('Failed To Approve ');</script>
    <?php
}


?>