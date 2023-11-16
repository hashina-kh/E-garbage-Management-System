-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2021 at 09:25 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trashtracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(4) NOT NULL,
  `description` varchar(100) NOT NULL,
  `data` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `complaints` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `feedbacks`
--

CREATE TABLE `feedbacks` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `feedback` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `homelywaste`
--

CREATE TABLE `homelywaste` (
  `id` int(4) NOT NULL,
  `waste_type` varchar(20) NOT NULL,
  `quantity` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `user_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `homelywaste`
--

INSERT INTO `homelywaste` (`id`, `waste_type`, `quantity`, `description`, `longitude`, `status`, `latitude`, `user_id`) VALUES
(1, '', '', '', '', '', '', 0),
(2, 'hdjdh', 'hhshsh', 'ydyd', '76.2117551', 'null', '10.5376967', 0);

-- --------------------------------------------------------

--
-- Table structure for table `publicwaste`
--

CREATE TABLE `publicwaste` (
  `bin_id` int(4) NOT NULL,
  `waste_type` varchar(50) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  `reported_mobile` varchar(12) NOT NULL,
  `status` varchar(10) NOT NULL,
  `latitude` varchar(200) NOT NULL,
  `longitude` varchar(200) NOT NULL,
  `description` varchar(300) NOT NULL,
  `user_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `publicwaste`
--

INSERT INTO `publicwaste` (`bin_id`, `waste_type`, `quantity`, `reported_mobile`, `status`, `latitude`, `longitude`, `description`, `user_id`) VALUES
(22, 'sdgasg', 'sgahh', '8139833517', '', '37.421999', '-122.0839957', 'wggawgaggag', 8),
(23, 'ddsgasgarrhf', 'sfsffhsfhasfhr', '9207284360', '', '37.421999', '-122.0839957', 'gafhsdfhdfhsdhdfh', 10),
(24, 'rithu', '1', '9207284360', '', '10.5388743', '76.2118797', 'verum kadam', 10),
(25, '', '', '', '', '', '', '', 0),
(26, 'rajmon unnithan', '1', '9207284360', '', '10.5377332', '76.2117562', 'vazha verum  vazha', 0),
(27, '', '', '', '', '', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `mobilenumber` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`id`, `name`, `email`, `mobilenumber`, `password`, `address`) VALUES
(8, 'sidharth', 'sidharthsushil007@gmail.com', '8139833517', 'sidharth', 'maniyath (H),karalam.'),
(10, 'hiranas', 'ashiran11400@gmail.com', '9207284360', 'hiransha', 'ammanathhouse, koppullysteels. '),
(11, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `sellitems`
--

CREATE TABLE `sellitems` (
  `id` int(4) NOT NULL,
  `item_name` varchar(30) NOT NULL,
  `description` varchar(700) NOT NULL,
  `price` varchar(10) NOT NULL,
  `quantity` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `truckdriver`
--

CREATE TABLE `truckdriver` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `truckdriver`
--

INSERT INTO `truckdriver` (`id`, `name`, `mobile`, `address`, `password`, `status`) VALUES
(4, 'sidharth', '8139833517', 'maniyath(H),karalam', 'sidharth', ''),
(5, '', '', '', '', ''),
(6, '', '', '', '', ''),
(7, '', '', '', '', ''),
(8, '', '', '', '', ''),
(9, '', '', '', '', ''),
(10, '', '', '', '', ''),
(11, '', '', '', '', ''),
(12, '', '', '', '', ''),
(13, '', '', '', '', ''),
(14, '', '', '', '', ''),
(15, '', '', '', '', ''),
(16, '', '', '', '', ''),
(17, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `wastebin`
--

CREATE TABLE `wastebin` (
  `bin_id` int(4) NOT NULL,
  `location` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedbacks`
--
ALTER TABLE `feedbacks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `homelywaste`
--
ALTER TABLE `homelywaste`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publicwaste`
--
ALTER TABLE `publicwaste`
  ADD PRIMARY KEY (`bin_id`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sellitems`
--
ALTER TABLE `sellitems`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `truckdriver`
--
ALTER TABLE `truckdriver`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wastebin`
--
ALTER TABLE `wastebin`
  ADD PRIMARY KEY (`bin_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedbacks`
--
ALTER TABLE `feedbacks`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `homelywaste`
--
ALTER TABLE `homelywaste`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `publicwaste`
--
ALTER TABLE `publicwaste`
  MODIFY `bin_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sellitems`
--
ALTER TABLE `sellitems`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `truckdriver`
--
ALTER TABLE `truckdriver`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `wastebin`
--
ALTER TABLE `wastebin`
  MODIFY `bin_id` int(4) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
