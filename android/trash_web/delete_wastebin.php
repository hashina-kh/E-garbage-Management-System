<?php

	include('config.php');

    $id=$_GET['id'];

$query="DELETE FROM waste WHERE wid='$id'";
$r=mysqli_query($conn, $query) or die("error in deletion".mysqli_error());

if($r)
{
    ?>
    <script language="javascript">alert('Deleted Successfully');
    window.location.replace('wastebins.php');
    </script>
    <?php
}

else
{
    ?>
    <script language="javascript">alert('Failed To Delete');</script>
    <?php
}


?>