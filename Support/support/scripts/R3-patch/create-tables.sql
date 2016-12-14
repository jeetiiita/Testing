CREATE DATABASE  IF NOT EXISTS `clicktable` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `clicktable`;
-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: 192.168.64.28    Database: clicktable
-- ------------------------------------------------------
-- Server version	5.7.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AggregateTax`
--

DROP TABLE IF EXISTS `AggregateTax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AggregateTax` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `centralTax` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GuestConversation`
--

DROP TABLE IF EXISTS `GuestConversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GuestConversation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `guestGuid` varchar(255) DEFAULT NULL,
  `guestMobileNum` varchar(255) DEFAULT NULL,
  `message` varchar(300) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `originGuid` varchar(255) DEFAULT NULL,
  `restaurantGuid` varchar(255) DEFAULT NULL,
  `sentBy` varchar(255) DEFAULT NULL,
  `smsCount` int(11) NOT NULL,
  `smsId` varchar(255) DEFAULT NULL,
  `smsStatus` varchar(255) DEFAULT NULL,
  `smsStatusCause` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1179 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Invoice`
--

DROP TABLE IF EXISTS `Invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `basicAmount` decimal(19,2) DEFAULT NULL,
  `bookNumber` int(11) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `discount` decimal(19,2) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `finMonth` int(11) DEFAULT NULL,
  `finYear` int(11) DEFAULT NULL,
  `invoiceDate` datetime DEFAULT NULL,
  `invoiceId` bigint(20) DEFAULT NULL,
  `netAmount` int(11) DEFAULT NULL,
  `restaurantGuid` varchar(255) DEFAULT NULL,
  `roundOffAmount` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `taxAmount` decimal(19,2) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `billingInfoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoiceId` (`invoiceId`),
  KEY `FK_ompo6xblg9aselug5whnggvox` (`billingInfoId`),
  CONSTRAINT `FK_ompo6xblg9aselug5whnggvox` FOREIGN KEY (`billingInfoId`) REFERENCES `InvoiceBillingInfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1865 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `InvoiceBillingInfo`
--

DROP TABLE IF EXISTS `InvoiceBillingInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InvoiceBillingInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountId` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `legalName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1865 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `InvoiceLine`
--

DROP TABLE IF EXISTS `InvoiceLine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InvoiceLine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `basicAmount` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `finMonth` int(11) DEFAULT NULL,
  `finYear` int(11) DEFAULT NULL,
  `itemPlanPrice` decimal(19,2) DEFAULT NULL,
  `itemQty` int(11) DEFAULT NULL,
  `lineNumber` int(11) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `restaurantGuid` varchar(255) DEFAULT NULL,
  `taxAmount` decimal(19,2) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `invoiceId` bigint(20) DEFAULT NULL,
  `itemId` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aabmd1ea95n9qhfo1kp4wp26d` (`invoiceId`),
  KEY `FK_8t73b52685r1pmme3yxbuufoj` (`itemId`),
  CONSTRAINT `FK_8t73b52685r1pmme3yxbuufoj` FOREIGN KEY (`itemId`) REFERENCES `Item` (`id`),
  CONSTRAINT `FK_aabmd1ea95n9qhfo1kp4wp26d` FOREIGN KEY (`invoiceId`) REFERENCES `Invoice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1688 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `InvoiceLineTax`
--

DROP TABLE IF EXISTS `InvoiceLineTax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InvoiceLineTax` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `taxRate` decimal(19,2) DEFAULT NULL,
  `invoiceLineId` bigint(20) DEFAULT NULL,
  `taxLayerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ff3hlt4iq7mfmn6xi6vyglss3` (`invoiceLineId`),
  KEY `FK_nvx0qn7gf50b0xe6loiarykm0` (`taxLayerId`),
  CONSTRAINT `FK_ff3hlt4iq7mfmn6xi6vyglss3` FOREIGN KEY (`invoiceLineId`) REFERENCES `InvoiceLine` (`id`),
  CONSTRAINT `FK_nvx0qn7gf50b0xe6loiarykm0` FOREIGN KEY (`taxLayerId`) REFERENCES `TaxLayer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3502 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `defaultUnitPrice` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemAggregateTax`
--

DROP TABLE IF EXISTS `ItemAggregateTax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemAggregateTax` (
  `itemId` smallint(6) NOT NULL,
  `aggregateTaxId` int(11) NOT NULL,
  PRIMARY KEY (`itemId`,`aggregateTaxId`),
  KEY `FK_4qx40fxbmjbb720l18nssyftg` (`aggregateTaxId`),
  CONSTRAINT `FK_4qx40fxbmjbb720l18nssyftg` FOREIGN KEY (`aggregateTaxId`) REFERENCES `AggregateTax` (`id`),
  CONSTRAINT `FK_5spi7u290jaakpxm7c6vn119t` FOREIGN KEY (`itemId`) REFERENCES `Item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `bankRefNo` varchar(255) DEFAULT NULL,
  `cardName` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `failureMessage` varchar(255) DEFAULT NULL,
  `orderStatus` varchar(255) DEFAULT NULL,
  `paymentMode` varchar(255) DEFAULT NULL,
  `statusCode` int(11) DEFAULT NULL,
  `statusMessage` varchar(255) DEFAULT NULL,
  `trackingId` varchar(255) DEFAULT NULL,
  `invoiceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i248eeljqqykajyr1566t2i58` (`invoiceId`),
  CONSTRAINT `FK_i248eeljqqykajyr1566t2i58` FOREIGN KEY (`invoiceId`) REFERENCES `Invoice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Plan`
--

DROP TABLE IF EXISTS `Plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PlanItem`
--

DROP TABLE IF EXISTS `PlanItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PlanItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `itemUnitPrice` decimal(19,2) DEFAULT NULL,
  `minItemQuantity` int(11) DEFAULT NULL,
  `itemId` smallint(6) DEFAULT NULL,
  `planId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n3dadgs44gi8w1l27w5vr6nxk` (`itemId`),
  KEY `FK_in1h3psvi6my4mim4b6g0tscx` (`planId`),
  CONSTRAINT `FK_in1h3psvi6my4mim4b6g0tscx` FOREIGN KEY (`planId`) REFERENCES `Plan` (`id`),
  CONSTRAINT `FK_n3dadgs44gi8w1l27w5vr6nxk` FOREIGN KEY (`itemId`) REFERENCES `Item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PromotionSummary`
--

DROP TABLE IF EXISTS `PromotionSummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PromotionSummary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `chargableSmsCount` int(11) DEFAULT NULL,
  `dndFailCount` int(11) DEFAULT NULL,
  `failCount` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `originGuid` varchar(255) DEFAULT NULL,
  `pendingCount` int(11) DEFAULT NULL,
  `restaurantGuid` varchar(255) DEFAULT NULL,
  `sentDate` datetime DEFAULT NULL,
  `smsCount` int(11) DEFAULT NULL,
  `successCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RestaurantPlan`
--

DROP TABLE IF EXISTS `RestaurantPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RestaurantPlan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `restaurantGuid` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `planId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e8rpum2s47mgq3fcjjaf6wvmn` (`planId`),
  CONSTRAINT `FK_e8rpum2s47mgq3fcjjaf6wvmn` FOREIGN KEY (`planId`) REFERENCES `Plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SingleTax`
--

DROP TABLE IF EXISTS `SingleTax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SingleTax` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `rate` decimal(19,2) DEFAULT NULL,
  `centralTax` bit(1) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `State`
--

DROP TABLE IF EXISTS `State`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `State` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `stateCode` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TaxApplicableLayer`
--

DROP TABLE IF EXISTS `TaxApplicableLayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaxApplicableLayer` (
  `taxLayerId` int(11) NOT NULL,
  `applicableLayer` int(11) DEFAULT NULL,
  KEY `FK_abgb4oo1b0n28ql5x0e9x8kd4` (`taxLayerId`),
  CONSTRAINT `FK_abgb4oo1b0n28ql5x0e9x8kd4` FOREIGN KEY (`taxLayerId`) REFERENCES `TaxLayer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TaxLayer`
--

DROP TABLE IF EXISTS `TaxLayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaxLayer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `layerNumber` int(11) DEFAULT NULL,
  `singleTaxId` int(11) DEFAULT NULL,
  `aggregateTaxId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_88lujca61ipl184dir2oc9uxv` (`singleTaxId`),
  KEY `FK_40tvtg6v59c5q13us8tije4y8` (`aggregateTaxId`),
  CONSTRAINT `FK_40tvtg6v59c5q13us8tije4y8` FOREIGN KEY (`aggregateTaxId`) REFERENCES `AggregateTax` (`id`),
  CONSTRAINT `FK_88lujca61ipl184dir2oc9uxv` FOREIGN KEY (`singleTaxId`) REFERENCES `SingleTax` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-17 12:10:10
