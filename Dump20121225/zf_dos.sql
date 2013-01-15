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
-- Table structure for table `dos`
--

DROP TABLE IF EXISTS `dos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DO` varchar(30) DEFAULT NULL,
  `lote` varchar(45) DEFAULT NULL,
  `BL` varchar(45) NOT NULL,
  `observaciones` longtext,
  `entidades_id` int(11) NOT NULL,
  `sucursal_id` int(11) NOT NULL,
  `puerto_id` int(11) NOT NULL,
  `tipo_mercancia_id` int(11) NOT NULL,
  `sUsuarios_id` int(11) NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idDO_UNIQUE` (`id`),
  UNIQUE KEY `DO_UNIQUE` (`DO`),
  KEY `fk_dos_sucursal1_idx` (`sucursal_id`),
  KEY `fk_dos_puerto1_idx` (`puerto_id`),
  KEY `fk_dos_tipo_mercancia1_idx` (`tipo_mercancia_id`),
  KEY `fk_dos_sUsuarios1_idx` (`sUsuarios_id`),
  KEY `fk_dos_entidades1_idx` (`entidades_id`),
  CONSTRAINT `fk_dos_entidades1` FOREIGN KEY (`entidades_id`) REFERENCES `entidades` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dos_puerto1` FOREIGN KEY (`puerto_id`) REFERENCES `ppuerto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dos_sucursal1` FOREIGN KEY (`sucursal_id`) REFERENCES `psucursales` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dos_sUsuarios1` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dos_tipo_mercancia1` FOREIGN KEY (`tipo_mercancia_id`) REFERENCES `ptipos_mercancias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dos`
--

LOCK TABLES `dos` WRITE;
/*!40000 ALTER TABLE `dos` DISABLE KEYS */;
/*!40000 ALTER TABLE `dos` ENABLE KEYS */;
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
