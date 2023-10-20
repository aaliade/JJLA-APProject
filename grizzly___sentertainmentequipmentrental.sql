-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2023 at 02:10 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `grizzly’sentertainmentequipmentrental`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `username` varchar(15) NOT NULL,
  `custID` varchar(15) NOT NULL,
  `accountBalance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `username` varchar(15) NOT NULL,
  `empID` varchar(15) NOT NULL,
  `empRole` varchar(30) NOT NULL,
  `hireDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipID` varchar(15) NOT NULL,
  `equipName` varchar(20) NOT NULL,
  `categoryID` varchar(15) NOT NULL,
  `description` varchar(70) NOT NULL,
  `status` varchar(25) NOT NULL,
  `rentalRate` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `equipmentcategory`
--

CREATE TABLE `equipmentcategory` (
  `categoryID` varchar(15) NOT NULL,
  `categoryName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `eventID` varchar(15) NOT NULL,
  `eventName` varchar(30) NOT NULL,
  `custID` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `messageID` varchar(15) NOT NULL,
  `senderID` varchar(15) NOT NULL,
  `receiverID` varchar(15) NOT NULL,
  `content` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` varchar(15) NOT NULL,
  `rentalID` varchar(15) NOT NULL,
  `custID` varchar(15) NOT NULL,
  `paymentType` varchar(25) NOT NULL,
  `paymentDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rentaltransaction`
--

CREATE TABLE `rentaltransaction` (
  `rentalID` varchar(15) NOT NULL,
  `equipID` varchar(15) NOT NULL,
  `rentDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `custID` varchar(15) NOT NULL,
  `totalCost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `phoneNum` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `userType` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custID`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empID`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`equipID`),
  ADD KEY `categoryID` (`categoryID`);

--
-- Indexes for table `equipmentcategory`
--
ALTER TABLE `equipmentcategory`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`eventID`),
  ADD KEY `custID` (`custID`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`messageID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`),
  ADD KEY `custID` (`custID`),
  ADD KEY `rentalID` (`rentalID`);

--
-- Indexes for table `rentaltransaction`
--
ALTER TABLE `rentaltransaction`
  ADD PRIMARY KEY (`rentalID`),
  ADD KEY `custID` (`custID`),
  ADD KEY `equipID` (`equipID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `equipmentcategory` (`categoryID`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`),
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`rentalID`) REFERENCES `rentaltransaction` (`rentalID`);

--
-- Constraints for table `rentaltransaction`
--
ALTER TABLE `rentaltransaction`
  ADD CONSTRAINT `rentaltransaction_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`),
  ADD CONSTRAINT `rentaltransaction_ibfk_2` FOREIGN KEY (`equipID`) REFERENCES `equipment` (`equipID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
