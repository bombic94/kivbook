CREATE DATABASE pia;

USE pia;

CREATE USER 'test'@'localhost';

GRANT ALL ON pia.* TO 'test'@'localhost' IDENTIFIED BY 'test1234';

GRANT ALL ON pia.* TO 'test'@'%' IDENTIFIED BY 'test1234';

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: kivbook
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `bohmannd_chat`
--

DROP TABLE IF EXISTS `bohmannd_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `seen` bit(1) NOT NULL DEFAULT b'0',
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_rehlsor91lkdydiur8v6igae6` (`user1_id`),
  KEY `FK_dapfqtywde6bnl75wgcw9kove` (`user2_id`),
  CONSTRAINT `FK_dapfqtywde6bnl75wgcw9kove` FOREIGN KEY (`user2_id`) REFERENCES `bohmannd_user` (`id`),
  CONSTRAINT `FK_rehlsor91lkdydiur8v6igae6` FOREIGN KEY (`user1_id`) REFERENCES `bohmannd_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_chat`
--

LOCK TABLES `bohmannd_chat` WRITE;
/*!40000 ALTER TABLE `bohmannd_chat` DISABLE KEYS */;
INSERT INTO `bohmannd_chat` VALUES (5,11,12,'','2017-10-11 09:16:17.062'),(6,11,13,'','2017-10-11 09:26:16.850'),(7,13,12,'','2017-10-11 18:02:53.689'),(8,14,11,'\0','2017-10-11 20:58:06.872'),(9,14,12,'','2017-10-11 21:02:48.372');
/*!40000 ALTER TABLE `bohmannd_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_chat_line`
--

DROP TABLE IF EXISTS `bohmannd_chat_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_chat_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `line_text` varchar(2000) COLLATE utf8_czech_ci NOT NULL,
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_en5ed14pm07xrrl4c7ril5u36` (`chat_id`),
  KEY `FK_6tyavof7api1ctg6yg3n1ttw8` (`sender_id`),
  CONSTRAINT `FK_6tyavof7api1ctg6yg3n1ttw8` FOREIGN KEY (`sender_id`) REFERENCES `bohmannd_user` (`id`),
  CONSTRAINT `FK_en5ed14pm07xrrl4c7ril5u36` FOREIGN KEY (`chat_id`) REFERENCES `bohmannd_chat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_chat_line`
--

LOCK TABLES `bohmannd_chat_line` WRITE;
/*!40000 ALTER TABLE `bohmannd_chat_line` DISABLE KEYS */;
INSERT INTO `bohmannd_chat_line` VALUES (9,6,13,'Hi man, you there?','2017-10-11 09:57:57.850'),(10,6,13,'Helloooooooo','2017-10-11 09:59:43.667'),(11,6,11,'Sure what you want?','2017-10-11 10:02:42.249'),(12,5,11,'I have to write something','2017-10-11 10:35:11.510'),(13,5,11,'Nice and cool','2017-10-11 10:35:21.583'),(14,7,13,'Fucking Jew!!!','2017-10-11 18:03:13.797'),(15,6,13,'Nothin','2017-10-11 18:22:27.238'),(16,7,12,'Fatso','2017-10-11 19:27:52.658'),(17,7,12,'stupid','2017-10-11 19:37:24.423'),(18,7,13,'...','2017-10-11 19:44:24.742'),(19,7,12,'123','2017-10-11 19:47:33.092'),(20,7,13,'456','2017-10-11 19:48:09.222'),(21,7,12,'123','2017-10-11 20:30:29.173'),(22,8,14,'something','2017-10-11 21:02:44.531'),(23,9,14,'bleh','2017-10-11 21:02:54.760'),(26,5,11,'123','2017-10-13 18:46:20.049'),(27,6,11,'testing','2017-10-13 19:47:41.038'),(28,6,13,'oh','2017-10-13 19:48:32.122'),(29,6,11,'yes!!!','2017-10-13 19:50:39.849'),(30,6,13,'oooh','2017-10-13 19:50:49.579'),(31,6,13,'123456','2017-10-13 20:25:18.164'),(32,6,11,'testing','2017-10-14 06:37:58.097'),(33,7,12,'123456','2017-10-14 12:07:46.171'),(34,7,13,'hi','2017-10-14 12:08:59.809'),(35,8,11,'what is new?','2017-10-14 13:42:40.895'),(41,8,11,'123','2017-10-14 21:03:45.209'),(42,11,11,'Hi Randy','2017-10-14 21:13:03.957'),(43,11,11,'123','2017-10-16 09:41:10.075'),(45,8,11,'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce aliquam vestibulum ipsum. Nullam sapien sem, ornare ac, nonummy non, lobortis a enim. Pellentesque ipsum. Fusce nibh. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce consectetuer risus a nunc. Vivamus porttitor turpis ac leo. Etiam dictum tincidunt diam. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Fusce tellus odio, dapibus id fermentum quis, suscipit id erat. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Aliquam ante. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Mauris metus. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Maecenas sollicitudin. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. Integer in sapien. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Integer tempor. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Nam sed tellus id magna elementum tincidunt. Aliquam erat volutpat. Nullam justo enim, consectetuer nec, ullamcorper ac, vestibulum in, elit. Donec quis nibh at felis congue commodo. Aliquam ante.Nulla pulvinar eleifend sem. Etiam sapien elit, consequat eget, tristique non, venenatis quis, a...','2017-10-16 18:30:58.473'),(46,8,11,'Příliš žluťoučký kůň úpěl ďábelské ódy.','2017-10-18 19:04:29.459');
/*!40000 ALTER TABLE `bohmannd_chat_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_comment`
--

DROP TABLE IF EXISTS `bohmannd_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_text` varchar(2000) COLLATE utf8_czech_ci NOT NULL,
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_m6piqutfmgauuxrlhub0o0ahp` (`status_id`),
  KEY `FK_apcd5hot40xo5joevab23q3ic` (`user_id`),
  CONSTRAINT `FK_apcd5hot40xo5joevab23q3ic` FOREIGN KEY (`user_id`) REFERENCES `bohmannd_user` (`id`),
  CONSTRAINT `FK_m6piqutfmgauuxrlhub0o0ahp` FOREIGN KEY (`status_id`) REFERENCES `bohmannd_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_comment`
--

LOCK TABLES `bohmannd_comment` WRITE;
/*!40000 ALTER TABLE `bohmannd_comment` DISABLE KEYS */;
INSERT INTO `bohmannd_comment` VALUES (6,5,12,'nice you can count to six!','2017-10-12 19:07:15.881'),(7,5,12,'123456789','2017-10-12 19:10:39.858'),(9,22,13,'Příliš žluťoučký kůň úpěl ďábelské ódy.','2017-10-18 19:25:24.308');
/*!40000 ALTER TABLE `bohmannd_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_friendship`
--

DROP TABLE IF EXISTS `bohmannd_friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_friendship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `accepted` bit(1) NOT NULL DEFAULT b'0',
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_hggpbkaibccfwh8mjcysbr3at` (`user1_id`),
  KEY `FK_m3ns25aj65dg2jeblongce8ps` (`user2_id`),
  CONSTRAINT `FK_hggpbkaibccfwh8mjcysbr3at` FOREIGN KEY (`user1_id`) REFERENCES `bohmannd_user` (`id`),
  CONSTRAINT `FK_m3ns25aj65dg2jeblongce8ps` FOREIGN KEY (`user2_id`) REFERENCES `bohmannd_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_friendship`
--

LOCK TABLES `bohmannd_friendship` WRITE;
/*!40000 ALTER TABLE `bohmannd_friendship` DISABLE KEYS */;
INSERT INTO `bohmannd_friendship` VALUES (7,14,12,'','2017-10-10 18:03:29.840'),(14,14,13,'\0','2017-10-12 16:05:35.190'),(23,13,12,'','2017-10-13 20:10:48.450'),(25,13,11,'','2017-10-13 20:25:01.359'),(33,12,11,'','2017-10-16 18:45:17.865');
/*!40000 ALTER TABLE `bohmannd_friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_like`
--

DROP TABLE IF EXISTS `bohmannd_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_4727hldt7bu1xo4twa7o0357y` (`status_id`),
  KEY `FK_r3j92qp04en2rnl2nmbiuapt8` (`user_id`),
  CONSTRAINT `FK_4727hldt7bu1xo4twa7o0357y` FOREIGN KEY (`status_id`) REFERENCES `bohmannd_status` (`id`),
  CONSTRAINT `FK_r3j92qp04en2rnl2nmbiuapt8` FOREIGN KEY (`user_id`) REFERENCES `bohmannd_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_like`
--

LOCK TABLES `bohmannd_like` WRITE;
/*!40000 ALTER TABLE `bohmannd_like` DISABLE KEYS */;
INSERT INTO `bohmannd_like` VALUES (22,22,13,'2017-10-18 19:25:27.850');
/*!40000 ALTER TABLE `bohmannd_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_status`
--

DROP TABLE IF EXISTS `bohmannd_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status_text` varchar(2000) COLLATE utf8_czech_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  KEY `FK_njckd3u36qog2174wlyrgykxj` (`user_id`),
  CONSTRAINT `FK_njckd3u36qog2174wlyrgykxj` FOREIGN KEY (`user_id`) REFERENCES `bohmannd_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_status`
--

LOCK TABLES `bohmannd_status` WRITE;
/*!40000 ALTER TABLE `bohmannd_status` DISABLE KEYS */;
INSERT INTO `bohmannd_status` VALUES (4,11,'sometext',NULL,'2017-10-11 19:11:30.384'),(5,11,'123456',NULL,'2017-10-12 18:11:03.426'),(7,11,'1',NULL,'2017-10-13 08:02:46.303'),(8,11,'2',NULL,'2017-10-13 08:03:54.994'),(9,11,'3',NULL,'2017-10-13 08:03:58.469'),(10,11,'4',NULL,'2017-10-13 08:04:00.110'),(11,11,'5',NULL,'2017-10-13 08:04:01.626'),(12,11,'6',NULL,'2017-10-13 08:04:02.988'),(13,11,'7',NULL,'2017-10-13 08:04:07.356'),(14,11,'8',NULL,'2017-10-13 08:18:03.430'),(15,11,'Příliš žluťoučký kůň úpěl ďábelské ódy',NULL,'2017-10-13 17:33:57.226'),(16,13,'i\'m not fat',NULL,'2017-10-13 20:27:05.186'),(17,11,'status testing',NULL,'2017-10-14 13:42:20.013'),(18,11,'test',NULL,'2017-10-14 13:42:54.711'),(22,11,'Příliš žluťoučký kůň úpěl ďábelské ódy.',NULL,'2017-10-18 19:03:41.279');
/*!40000 ALTER TABLE `bohmannd_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bohmannd_user`
--

DROP TABLE IF EXISTS `bohmannd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bohmannd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `active_chat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aj06pto8lyfnaybk1b4m2kwlj` (`active_chat_id`),
  CONSTRAINT `FK_aj06pto8lyfnaybk1b4m2kwlj` FOREIGN KEY (`active_chat_id`) REFERENCES `bohmannd_chat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bohmannd_user`
--

LOCK TABLES `bohmannd_user` WRITE;
/*!40000 ALTER TABLE `bohmannd_user` DISABLE KEYS */;
INSERT INTO `bohmannd_user` VALUES (11,'user1@example.com','user1','1000:ef83e287c0755d89858b2e5f674118d239cf65028be5aa2c:e6bd41640cc446c14fd793d973092e664a66acbd8c7ddc75','2000-01-01','m','Stan','Marsh','default-profile-picture.jpg','2017-10-10 17:27:02.076',8),(12,'user2@example.com','user2','1000:4ad893302aaf73ad1b37ac74ccd5de9973367b5957647635:c6bc2bf9fc2b4501647f25f9c167d59d440556270fcfe971','2001-06-13','m','Kyle','Broflovski','default-profile-picture.jpg','2017-10-10 17:29:15.508',7),(13,'user3@example.com','user3','1000:0c5d2f7147d6c06bae4f90dff9ef0993f5e33da9a7e15233:a0ed7b75fda39b249dd20daab257aed894f1426d2afd8eed','2000-12-24','m','Eric','Cartman','default-profile-picture.jpg','2017-10-10 17:30:09.814',7),(14,'user4@example.com','user4','1000:cbe00126b8f570dbc8d91f438dde5d04297f0acf2647137e:6f1dd2c50d87f72e8e13184ce9a03df6dc7aa6e8c7e17501','2000-10-29','m','Kenny','McCormick','default-profile-picture.jpg','2017-10-10 17:31:04.203',9);
/*!40000 ALTER TABLE `bohmannd_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-18 21:30:32
