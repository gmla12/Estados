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
-- Table structure for table `ppuerto`
--

DROP TABLE IF EXISTS `ppuerto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ppuerto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_corto` varchar(5) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `pMunicipios_id` varchar(3) NOT NULL,
  `pMunicipios_departamentos_id` varchar(2) NOT NULL,
  `pMunicipios_departamentos_pPaises_id` varchar(3) NOT NULL,
  `sUsuarios_id` int(11) NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  `pSucursales_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombreCorto_UNIQUE` (`nombre_corto`),
  KEY `fk_pPuerto_pMunicipios1_idx` (`pMunicipios_id`,`pMunicipios_departamentos_id`,`pMunicipios_departamentos_pPaises_id`),
  KEY `fk_pPuerto_sUsuarios1_idx` (`sUsuarios_id`),
  KEY `fk_pSucursales1_idx` (`pSucursales_id`),
  CONSTRAINT `fk_pPuerto_pMunicipios1` FOREIGN KEY (`pMunicipios_id`, `pMunicipios_departamentos_id`, `pMunicipios_departamentos_pPaises_id`) REFERENCES `pmunicipios` (`id`, `departamentos_id`, `departamentos_pPaises_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pPuerto_sUsuarios1` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pSucursales1` FOREIGN KEY (`pSucursales_id`) REFERENCES `psucursales` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppuerto`
--

LOCK TABLES `ppuerto` WRITE;
/*!40000 ALTER TABLE `ppuerto` DISABLE KEYS */;
INSERT INTO `ppuerto` VALUES (5,'MEB','Muelles el Bosque','001','13','090',1,'2012-12-19 14:37:46',NULL);
/*!40000 ALTER TABLE `ppuerto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-25 11:39:54
