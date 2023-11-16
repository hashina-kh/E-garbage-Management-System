<?php

	include('config.php');
  session_start();

$id=$_GET['id'];
$na=$_GET['na'];
// $did=$_GET['did'];

date_default_timezone_set('Asia/Kolkata');

$dateYmd = date('Y-m-d');
$time= date('h:i');

/*$query="UPDATE waste_report SET status='assigned',driverid='$did',drivername='$na',date='$dateYmd',time='$time' WHERE id='$id'";
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
    <script language="javascript">alert('Failed To Approve ');</script>
    <?php
}
*/

?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!--  <title>add-teacher</title> -->
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">






</head>
<body>
    <!-- <header class="bg-dark bg-gradient"> -->
        
            <div class="col-md-6 col-3">
                <nav class="navbar navbar-expand-lg navbar-dark">
                    <div class="container-fluid">
                      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                      </button>
                      <!-- <div class="collapse navbar-collapse" id="navbarSupportedContent">
                         <ul class="navbar-nav">
                          <li class="nav-item">
                            <a class="nav-link" href="admin.home.php">Home</a>
                          </li>
                           <li class="nav-item">
                            <a class="nav-link" href="Mentorship.php">Mentorship</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="update-admin-profile.php">profile</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="teachers.html">Teachers</a>
                          </li>
                           <li class="nav-item">
                            <a class="nav-link" href="faculty.html">Faculty</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="students.html">Students</a>
                          </li>
                         
                          <li class="nav-item">
                            <a class="nav-link" href="admin-update-password.php">Update password</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="logout.php">Log out</a>
                          </li>
                        </ul>
                      </div> -->
                    </div>
                </nav>
            </div>
            
        </div>
                
    </header>

    <div class="container-fluid" id="main">
      <div class="row row-offcanvas row-offcanvas-left">
        
          <!--/col-->
        <div class="col-md-6 main pt-5 mt-3">
                <form action="" method="POST">
                    <div class="form-group">

                                    <div class="form-group">
                                        <label for="exampleFormControlSelect1">Choose Driver</label>
                                        <select class="form-control" id="exampleFormControlSelect1" id="type" name="type">
                     <?php
                    $qu="select * from truckdriver where status='approved'";
                    $re=mysqli_query($conn, $qu);
                    while($r=mysqli_fetch_array($re,MYSQLI_ASSOC))
                    {
                       
                        $na = $r['name'];
                        $tid = $r['id'];
                        $to_email = $r['email'];


                ?>
                    <option value="<?php echo $tid;?>">  <?php echo $na;?>  </option>
                
                <?php
                    }
                ?>

                                           
   </select>
                                    </div>

                    <button type="Create" class="btn btn-primary mt-2" name="submit" >Submit</button>
                  </form>

                  <?php
// if(isset($_POST['submit']))
// {

// $to_email=$_POST['email'];
//  $subject = "Notification from E-Garbage Management System";
//  $body = " You are assigned with a work. Check your E-Garbage Management app to know more... ";
//  $headers = "From: sem4egs@gmail.com";

//  if(mail($to_email,$subject,$body,$headers)){
//   echo "Email successfully sent to $to_email...";
//  }else{
//   echo "Email sending failed...";
//  }
// }
                  ?>

        </div>

      </div>
    </div>
    <footer class="bg-light">
      <div class="container " style="height: 5em;">
          <div class="row">
             <!--  <div class="col-md-12 text-center text-dark mt-2">
                  <i class="fa-solid fa-copyright"></i>
                  <p>Copyrights</p>
              </div> -->
          </div>
      </div>
    </footer>
 
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/fe126d3d3a.js" crossorigin="anonymous"></script>
</body>
</html>
<?php

if(isset($_POST['submit']))
{
$id=$_GET['id'];
$na=$_GET['na'];
 $did=$_POST['type'];
//  $to_email=$_POST['to_email'];
//  $subject = "Notification from E-Garbage Management System";
//  $body = " You are assigned with a work. Check your E-Garbage Management app to know more... ";
//  $headers = "From: sem4egs@gmail.com";

date_default_timezone_set('Asia/Kolkata');

$dateYmd = date('Y-m-d');
$time= date('h:i');

$query="UPDATE waste_report SET status='assigned',driverid='$did',date='$dateYmd',time='$time' WHERE id='$id'";
$r=mysqli_query($conn, $query) or die("error in deletion".mysqli_error());
// $mail=mail($to_email,$subject,$body,$headers);
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
    <script language="javascript">alert('Failed To Approve ');</script>
    <?php
}
}

?>