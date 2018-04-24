-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 35.227.53.11    Database: db_online
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `city`    varchar(255)     DEFAULT NULL,
  `country` varchar(255)     DEFAULT NULL,
  `state`   varchar(255)     DEFAULT NULL,
  `zipcode` varchar(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address`
  DISABLE KEYS */;
INSERT INTO `address`
VALUES (1, 'Fairfield', 'US', 'Iowa', '52556'), (2, '', 'US', '', ''), (3, 'Fairfield', 'US', 'Iowa', '52556'),
  (4, 'Fairfield', 'US', 'Iowa', '52556'), (5, '', 'US', '', ''), (6, '', 'US', '', ''),
  (7, 'Fairfield', 'US', 'Iowa', '52556'), (8, '', 'US', '', ''), (9, 'Fairfield', 'US', 'Iowa', '52557'),
  (12, 'Fairfield', 'US', 'Iowa', '52556'), (13, 'Fairfield', 'US', 'Iowa', '52556'),
  (14, 'Fairfield', 'US', 'Iowa', '52556'), (15, 'Fairfield', 'US', 'Iowa', '52556'),
  (16, 'Ho Chi Minh', 'Vietnam', 'VN', '777777');
/*!40000 ALTER TABLE `address`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date             DEFAULT NULL,
  `person_id`  bigint(20)       DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnhuvki9yhynrns6vek8oe9l0h` (`person_id`),
  CONSTRAINT `FKnhuvki9yhynrns6vek8oe9l0h` FOREIGN KEY (`person_id`) REFERENCES `customer` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table`
  DISABLE KEYS */;
INSERT INTO `order_table` VALUES (2, '2018-03-11', 2), (3, '2018-03-11', 2), (4, '2018-03-11', 2), (5, '2018-03-11', 3);
/*!40000 ALTER TABLE `order_table`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderDetail`
--

DROP TABLE IF EXISTS `orderDetail`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderDetail` (
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `quantity`   int(11) NOT NULL,
  `order_id`   int(11)          DEFAULT NULL,
  `product_id` int(11)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5mx9k7h4smq6nibv9xqdxp1gj` (`order_id`),
  KEY `FKr1od9vmk4y5fqamfadvclmcpu` (`product_id`),
  CONSTRAINT `FK5mx9k7h4smq6nibv9xqdxp1gj` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`),
  CONSTRAINT `FKr1od9vmk4y5fqamfadvclmcpu` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderDetail`
--

LOCK TABLES `orderDetail` WRITE;
/*!40000 ALTER TABLE `orderDetail`
  DISABLE KEYS */;
INSERT INTO `orderDetail` VALUES (2, 1, 2, 3), (3, 2, 2, 5), (4, 2, 3, 3), (5, 1, 4, 3), (6, 1, 5, 3);
/*!40000 ALTER TABLE `orderDetail`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `email`      varchar(255)        DEFAULT NULL,
  `enable`     bit(1)     NOT NULL,
  `first_name` varchar(255)        DEFAULT NULL,
  `last_name`  varchar(255)        DEFAULT NULL,
  `phone`      varchar(255)        DEFAULT NULL,
  `address_id` int(11)             DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk7rgn6djxsv2j2bv1mvuxd4m9` (`address_id`),
  CONSTRAINT `FKk7rgn6djxsv2j2bv1mvuxd4m9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer`
  DISABLE KEYS */;
INSERT INTO `customer` VALUES (2, 'admin@mum.edu', '', 'Admin', 'Admin', '12345678', 14),
  (3, 'dddoan@mum.edu', '', 'Danh Dat', 'Doan', '', 8), (6, 'tester@mum.edu', '', 'Tester', 'Online Store', '', 15),
  (7, 'ttqmaikhtn@gmail.com', '', 'Tran Thi Quynh', 'Mai', '', 16);
/*!40000 ALTER TABLE `customer`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id`           int(11) NOT NULL AUTO_INCREMENT,
  `description`  varchar(255)     DEFAULT NULL,
  `price`        double  NOT NULL,
  `product_name` varchar(255)     DEFAULT NULL,
  `product_type` varchar(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product`
  DISABLE KEYS */;
INSERT INTO `product` VALUES (1,
                              'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
                              10, 'Online', 'BREAKFAST'), (3,
                                                           'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
                                                           10, 'Online', 'BREAKFAST'), (4,
                                                                                        'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
                                                                                        11, 'Online', 'BREAKFAST'), (5,
                                                                                                                     'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
                                                                                                                     11,
                                                                                                                     'Online',
                                                                                                                     'BREAKFAST'),
  (6,
   'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
   11, 'Online', 'BREAKFAST'), (8,
                                'Purchase supplies, create your online recipe, set your price... and start selling!\nTIP: You can change your online price during the day! Keep an eye on what potential customers think as they walk by and keep an eye on what your customers think right after',
                                10, 'Heading', 'BREAKFAST');
/*!40000 ALTER TABLE `product`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id`   int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role`
  DISABLE KEYS */;
INSERT INTO `role` VALUES (1, 'ADMIN'), (2, 'USER');
/*!40000 ALTER TABLE `role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id`       bigint(20) NOT NULL AUTO_INCREMENT,
  `email`    varchar(255)        DEFAULT NULL,
  `enabled`  bit(1)     NOT NULL,
  `password` varchar(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
  DISABLE KEYS */;
INSERT INTO `user` VALUES (1, 'admin@mum.edu', '', '$2a$10$/95KOrhWbpU1IQo7LHR3kukvbcFt6qiQe6IpdmWuxt.YBO6CTIi16'),
  (2, 'dddoan@mum.edu', '', '$2a$10$GfqhpHuyuyoBNnieuciG7evBkkvEZ4Ki7MT3XrcReya/7026HtAca'),
  (5, 'tester@mum.edu', '', '$2a$10$mgBRyoF8jTt.XwhF7VMBQerXl/umwz7ayiAHoBbQIQ/6zL3RJ50Ai'),
  (6, 'ttqmaikhtn@gmail.com', '', '$2a$10$dlusscDVuWiTKtXe0LTDVuq3Lcu2dOvNsh1CDhALTQqiX.B3Lus9q');
/*!40000 ALTER TABLE `user`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11)    NOT NULL,
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles`
  DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1, 1), (2, 2), (5, 2), (6, 2);
/*!40000 ALTER TABLE `user_roles`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-03-11 14:22:02
