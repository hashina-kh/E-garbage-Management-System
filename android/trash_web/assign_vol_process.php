<?php

	include('config.php');

    $id = $_GET['id'];
    $camp = $_POST['camp'];
    $duty = $_POST['duty'];

    $query="UPDATE disaster_volunteer SET camp_name='$camp', duty='$duty' WHERE vol_id='$id'";
    $r=mysqli_query($conn, $query) or die("error in deletion".mysqli_error());
    
    if($r)
    {
        ?>
        <script language="javascript">alert('Assigned Successfully');
        window.location.replace('home.html');
        </script>
        <?php
    }
    
    else
    {
        ?>
        <script language="javascript">alert('Failed To Assign');</script>
        <?php
    }


?>