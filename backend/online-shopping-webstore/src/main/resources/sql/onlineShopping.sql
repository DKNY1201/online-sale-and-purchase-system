-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_onlineshopping
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Kent','WA','98030',NULL),(2,'KENT','Washington','98030',NULL),(3,'KENT','Washington','98030','27925 122nd PL SE'),(4,'KENT','Washington','98030','27925 122nd PL SE'),(5,'KENT','Washington','98030','27925 122nd PL SE'),(6,'KENT','Washington','98030','27925 122nd PL SE'),(7,'Westminster','CA','92683','14081 Magnolia Street'),(8,'','','',''),(9,'','','',''),(10,'Fairfield','IA','52556','abc'),(11,'Fairfield','IA','52556','abc123'),(12,'Fairfield','IA','52556','abc'),(13,'Fairfield','IA','52556','11aaa'),(14,'Westminster','CA','92683','14081 Magnolia Street');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `card_number` varchar(255) NOT NULL,
  `card_holder_name` varchar(255) DEFAULT NULL,
  `ccv_number` varchar(255) DEFAULT NULL,
  `expiry_month` varchar(255) DEFAULT NULL,
  `expiry_year` varchar(255) DEFAULT NULL,
  `value` double NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES ('1111111111111111','Quan','123','01','21',20628.490000000005,'52557'),('2222222222222222','vendor','123','11','21',25000,NULL);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_detail`
--

DROP TABLE IF EXISTS `card_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_holder_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `expiry_month` varchar(255) DEFAULT NULL,
  `expiry_year` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoqdjjenj3c2e93gasdvr2uaue` (`person_id`),
  CONSTRAINT `FKoqdjjenj3c2e93gasdvr2uaue` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_detail`
--

LOCK TABLES `card_detail` WRITE;
/*!40000 ALTER TABLE `card_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `billing_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnhuvki9yhynrns6vek8oe9l0h` (`person_id`),
  CONSTRAINT `FKnhuvki9yhynrns6vek8oe9l0h` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (2,'2018-04-30',1,NULL,NULL,NULL),(3,'2018-05-08',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(4,'2018-05-08',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(5,'2018-05-08',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(6,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(7,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(8,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(9,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(10,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(11,'2018-05-10',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(12,'2018-05-11',6,NULL,'14081 Magnolia Street, spc 141','14081 Magnolia Street'),(13,'2018-05-11',10,NULL,'xxx','yyy');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5mx9k7h4smq6nibv9xqdxp1gj` (`order_id`),
  KEY `FKr1od9vmk4y5fqamfadvclmcpu` (`product_id`),
  CONSTRAINT `FK5mx9k7h4smq6nibv9xqdxp1gj` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`),
  CONSTRAINT `FKr1od9vmk4y5fqamfadvclmcpu` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (2,1,2,3),(3,1,2,2),(4,1,3,3),(5,2,4,3),(6,1,5,3),(7,1,6,3),(8,1,7,3),(9,1,8,3),(10,1,9,3),(11,1,10,3),(12,1,11,3),(13,2,12,3),(14,2,13,2),(15,2,13,4);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(255) DEFAULT NULL,
  `total_value` double NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8p50pxqlca5tru4wjbqjp4vtm` (`order_id`),
  KEY `FK44uml1am8txqmloxet86nrx9t` (`person_id`),
  CONSTRAINT `FK44uml1am8txqmloxet86nrx9t` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK8p50pxqlca5tru4wjbqjp4vtm` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'Sold',367.224,7,6),(2,'SOLD',367.224,8,6),(3,'COMMISSION',91.806,8,NULL),(4,'SOLD',343.20000000000005,9,4),(5,'COMMISSION',91.806,9,NULL),(6,'BOUGHT',459.03,9,NULL),(7,'SOLD',343.20000000000005,10,4),(8,'COMMISSION',91.806,10,NULL),(9,'BOUGHT',459.03,10,6),(10,'SOLD',343.20000000000005,11,4),(11,'COMMISSION',91.806,11,NULL),(12,'BOUGHT',459.03,11,6),(13,'SOLD',686.4000000000001,12,4),(14,'COMMISSION',171.60000000000002,12,NULL),(15,'BOUGHT',858,12,6),(16,'SOLD',1598.4,13,4),(17,'SOLD',640,13,9),(18,'COMMISSION',559.6,13,NULL),(19,'BOUGHT',2798,13,10);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`),
  KEY `FKk7rgn6djxsv2j2bv1mvuxd4m9` (`address_id`),
  CONSTRAINT `FKk7rgn6djxsv2j2bv1mvuxd4m9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'admin@gmail.com','','Super','Admin','1234567890',1),(2,'tuyen@gmail.com','','Tuyen','Ku','1212212121212',2),(3,'quytran@gmail.com','','Van','Tran','2534993872',3),(4,'quytran288@gmail.com','','Van','Tran','2534993872',5),(5,'ka@gmail.com','','kaka','kaka','2534993872',6),(6,'quandt2206@gmail.com','','Quan','Doan','6573349017',14),(9,'ducvo1983@gmail.com','','Duc','Vo','111111111',12),(10,'hellmanager119@gmail.com','','Quan','Quan','12345678901',13);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `price` double NOT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlhmpic5xty3ela7ubqfjj27c2` (`person_id`),
  CONSTRAINT `FKlhmpic5xty3ela7ubqfjj27c2` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'Phone X features an all-screen design with a 5.8-inch Super Retina HD display with HDR and True Tone.¹ Designed with the most durable glass ever in a smartphone and a surgical grade stainless steel band. Charges wirelessly.² Resists water and dust.³ 12MP dual cameras with dual optical image stabilization for great low-light photos. TrueDepth camera with Portrait selfies and new Portrait Lighting.? Face ID lets you unlock and use Apple Pay with just a glance. Powered by A11 Bionic, the most powerful and smartest chip ever in a smartphone. Supports augmented reality experiences in games and apps. With iPhone X, the next era of iPhone has begun.',999,'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6009/6009901_sd.jpg;maxHeight=640;maxWidth=550','Apple - iPhone X 64GB - Space Gray 1','ELECTRONIC',4),(3,'Easily assembles with a friend, no tools needed, in under 20 minutes\r\nStress-free fabrics were chosen to be durable and easy-to-clean\r\n76.4 inches long, with tufted back and seat cushions\r\nA naturally-strong wood frame is wrapped in cozy, supportive foam cushioning\r\nSmartly shipped in one box - all parts for assembling your sofa are located in the enclosed compartment on the bottom of the base section',429,'https://images-na.ssl-images-amazon.com/images/I/818WQ-S2ULL._SX522_.jpg','Sofa couch','FURNITURE',4),(4,'8\' Boat Shaped Conference Table with Cube Bases by Office Source Furniture',400,'https://images.e2go.biz/ImageHandler.ashx?src=OFSF%2fofs_pl_conference__no4_622.jpg&width=500&height=284','Table','FURNITURE',9),(5,'A merino sheep’s wool fibers clock in at 20% the diameter of human hair, meaning they’re sporting one superfine coat. We source nothing but 17.5 micron superfine New Zealand Merino wool, which we developed into our very own material with a premium Italian mill. It’s temperature-regulating and moisture-wicking, all without an irritating scratchiness.',123,'https://cdn.allbirds.com/image/fetch/q_auto,f_auto/q_auto,f_auto,b_rgb:F2F2F2/https://cdn.shopify.com/s/files/1/1104/4168/products/Allbirds_W_Wool_Runner_Kotare_GREY_ANGLE.png%3Fv%3D1523481604','Nike Shoes','FASHION',9);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(3,'VENDOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `available_amount` double NOT NULL,
  `payment_date` date DEFAULT NULL,
  `transaction_amount` double NOT NULL,
  `card_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK484i2t8acnct6xy8ylevl40go` (`card_id`),
  CONSTRAINT `FK484i2t8acnct6xy8ylevl40go` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'',10000,NULL,459.03,'1111111111111111'),(2,'',9540.97,NULL,459.03,'1111111111111111'),(3,'',9081.939999999999,NULL,918.06,'1111111111111111'),(4,'',8163.879999999999,NULL,459.03,'1111111111111111'),(5,'',7704.849999999999,NULL,459.03,'1111111111111111'),(6,'',7245.82,NULL,459.03,'1111111111111111'),(7,'',6786.79,NULL,459.03,'1111111111111111'),(8,'',6327.76,NULL,918.06,'1111111111111111'),(9,'',5409.700000000001,NULL,459.03,'1111111111111111'),(10,'',4950.670000000001,NULL,459.03,'1111111111111111'),(11,'',4491.640000000001,NULL,459.03,'1111111111111111'),(12,'',4032.6100000000015,NULL,459.03,'1111111111111111'),(13,'',3573.5800000000017,NULL,459.03,'1111111111111111'),(14,'',3114.550000000002,NULL,459.03,'1111111111111111'),(15,'',2655.5200000000023,NULL,459.03,'1111111111111111'),(16,'',2196.4900000000025,NULL,429,'1111111111111111'),(17,'',1767.4900000000025,NULL,858,'1111111111111111'),(18,'',909.4900000000025,NULL,429,'1111111111111111'),(19,'',480.4900000000025,NULL,429,'1111111111111111'),(20,'\0',51.49000000000251,NULL,858,'1111111111111111'),(21,'',51000.490000000005,NULL,858,'1111111111111111'),(22,'',50142.490000000005,NULL,858,'1111111111111111'),(23,'',49284.490000000005,NULL,858,'1111111111111111'),(24,'',50000,NULL,25000,'2222222222222222'),(25,'',48426.490000000005,NULL,25000,'1111111111111111'),(26,'',23426.490000000005,NULL,2798,'1111111111111111');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@gmail.com','','$2a$10$7cBZeqtaAH8iCuYLKxsrc.0YtcanBXRCYZl4Ut/0NxCw3auNEIlbC'),(2,'tuyen@gmail.com','','$2a$10$mLdO5qSmRstHmkn8KHm7IeUm0nBveQJu3SREM/d/LditABpL6fgEu'),(3,'quytran@gmail.com','','$2a$10$8x/oAIJH6uHi/h4iZfl5FupQycv/psIu2tRbyhrAC6AAbJYIhEB8e'),(4,'quytran288@gmail.com','','$2a$10$ctCTNMIepzkpkPISo.x.eO4HlWVFs9nRjAGlhREoRGRLs2IL9M2Mm'),(5,'ka@gmail.com','','$2a$10$qgo2KEwaT808Te87Lsz/suRrggyDCMwGBbAh2ICJrq7AEwz/FtCnW'),(6,'quandt2206@gmail.com','','$2a$10$7cBZeqtaAH8iCuYLKxsrc.0YtcanBXRCYZl4Ut/0NxCw3auNEIlbC'),(9,'ducvo1983@gmail.com','','$2a$10$kF8Mh3BM6Bqz2OkJYCbrDeOydCQBTokAIKFa6dyCKsnyFzkf9IOuu'),(10,'hellmanager119@gmail.com','','$2a$10$SP052s.Sgb61pl..QOHYUu816.8iN24DECXlfrrqSuE84htFnfIi2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2),(3,2),(4,3),(4,1),(5,2),(6,2),(9,3),(10,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-11 11:15:24
