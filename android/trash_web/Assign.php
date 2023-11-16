<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>E-Garbage Management Admin</title>
	<!--
		CSS
		============================================= -->
	<link rel="stylesheet" href="css/linearicons.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/nice-select.css">
	<link rel="stylesheet" href="css/nouislider.min.css">
	<link rel="stylesheet" href="css/ion.rangeSlider.css" />
	<link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
	<link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/main.css">
</head>

<body>

	<!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<!--<a class="navbar-brand logo_h" href="index.html"><img src="img/logo.png" alt=""></a>-->
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="home.html">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="drivers.php">Drivers</a></li>
							<li class="nav-item"><a class="nav-link" href="users.php">Users</a></li>
							<li class="nav-item"><a class="nav-link" href="Member.php">Ward Member</a></li>
							<li class="nav-item"><a class="nav-link" href="wastebins.php">Wastebins</a></li>
							<li class="nav-item active"><a class="nav-link" href="Assign.php">Assign Drivers</a></li>
							<li class="nav-item"><a class="nav-link" href="Assigned_list.php">Assigned List</a></li>
							<li class="nav-item"><a class="nav-link" href="Collected_list.php">Collected List</a></li>
							<li class="nav-item"><a class="nav-link" href="Ward.php">Ward Complaints</a></li>
						
							<li class="nav-item"><a class="nav-link" href="complaints.php">Complaints</a></li>
							<li class="nav-item"><a class="nav-link" href="feedbacks.php">Feedback</a></li>


						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div class="search_input" id="search_input_box">
			<div class="container">
				<form class="d-flex justify-content-between">
					<input type="text" class="form-control" id="search_input" placeholder="Search Here">
					<button type="submit" class="btn"></button>
					<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
				</form>
			</div>
		</div>
	</header>
	<!-- End Header Area -->

	<!-- start banner Area -->
	<section class="banner-area">
		<div class="container">
			<div class="row fullscreen align-items-center justify-content-start">
				<div class="col-lg-12">
					<!-- <div class="active-banner-slider owl-carousel"> -->
						<!-- single-slide -->
						<div class="row single-slide align-items-center d-flex">
							<div class="col-lg-5 col-md-6">
								<div class="banner-content">
									<!-- <h1>E-Garbage Management System</h1> -->
								</div>
							</div>
						</div>
						<!-- single-slide -->
						<div class="row single-slide">
							<div class="col-lg-5">
								<div class="banner-content">
									<h1>E-Garbage Management System</h1>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End banner Area -->

	
	<!-- Start related-product Area -->
	<section class="related-product-area section_gap_bottom">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<div class="section-title">
					    <br>
						<h1>View Reports and Assign Drivers</h1>
						
						<!-- <div class="row">
            				<div class="col-lg-12">
            					<form class="row contact_form" action="add_wastebin.php" method="post" id="contactForm" novalidate="novalidate">
            						<div class="col-md-12">
            							<div class="form-group">
            								<input type="text" class="form-control" id="email" name="lat" placeholder="Enter latitude" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter latitude'">
            							</div>
            							<div class="form-group">
            								<input type="text" class="form-control" id="subject" name="lng" placeholder="Enter longitude" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter longitude'">
            							</div>
            						</div>
            						<div class="col-md-12">
            							<button type="submit" value="submit" class="primary-btn">Upload</button>
            						</div>
            					</form>
            				</div>
            			</div>
            			 -->
            			
            			<br><br><br>
            			
            			
            			<table border="1" align="center"> 
                                <tr>
                                <td><h4>Name</h4></td>
                                <td><h4>Location</h4></td>
                                <td><h4>Description</h4></td>
                                 <td><h4>Type</h4></td>
                                 <td><h4>Ward</h4></td>
                                 
                                 <td><h4>Assign</h4></td>



                                </tr>     
                                    
                                <?php
                                
                                include('config.php');
                                
                              	$query1="select * from waste_report where status=''";
                    	        $res1=mysqli_query($conn, $query1) or die("error".mysqli_error());
                    	        
                    	        while($row1=mysqli_fetch_array($res1))
                    	        {
                        	        $name=$row1['name'];
                        	         $id=$row1['id'];
                        	        $location=$row1['location'];
                        	          $description=$row1['description'];
                        	        $type=$row1['type'];

                        	          $ward=$row1['ward'];
                                    ?>
                                
                                    <tr>
                                        <td><?php echo $name;?></td>
                                        <td><?php echo $location;?></td>
                                         <td><?php echo $description;?></td>
                                        <td><?php echo $type;?></td>
                                        <td><?php echo $ward;?></td>

            <!--     <td style="border: 1px solid #990000; border-collapse: collapse">
                <select name="m" id="faculties">
                <?php
                    $qu="select * from truckdriver where status='approved' ";
                    $re=mysqli_query($conn, $qu);
                    while($r=mysqli_fetch_array($re,MYSQLI_ASSOC))
                    {
                        $na = $r['name'];
                        // $did = $r['id'];
                ?>
                    <option value="<?php echo $na;?>">  <?php echo $na;?>  </option>
                <?php
                    }
                ?>
                </td> -->
                                        <td><a href="assign_driver.php?id=<?php echo $id;?>&&na=<?php echo $na;?>"><li class="btn header-btn">Assign</li></a></td>
                                        </tr>
                                    <?php
                    	        }
                                ?>
                            
                            </table>
            			
            			
            			
            			
						
					</div>
				</div>
			</div>
			
		</div>
	</section>
	<!-- End related-product Area -->


	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/countdown.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>