CREATE DATABASE  IF NOT EXISTS `zf` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `zf`;
-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: zf
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.10.2

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
-- Table structure for table `items_dos`
--

DROP TABLE IF EXISTS `items_dos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items_dos` (
  `id` int(11) NOT NULL,
  `cliente_final` varchar(150) DEFAULT NULL,
  `referencia` varchar(50) DEFAULT NULL,
  `pedido` varchar(50) DEFAULT NULL,
  `descripcion` text,
  `numero_factura` varchar(10) DEFAULT NULL,
  `dos_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `sUsuarios_id` int(11) NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_items_dos_dos1_idx` (`dos_id`),
  KEY `fk_items_dos_proveedor1_idx` (`proveedor_id`),
  KEY `fk_items_dos_sUsuarios1_idx` (`sUsuarios_id`),
  CONSTRAINT `fk_items_dos_dos1` FOREIGN KEY (`dos_id`) REFERENCES `dos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_items_dos_proveedor1` FOREIGN KEY (`proveedor_id`) REFERENCES `pproveedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_items_dos_sUsuarios1` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_dos`
--

LOCK TABLES `items_dos` WRITE;
/*!40000 ALTER TABLE `items_dos` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_dos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-25 11:39:55
