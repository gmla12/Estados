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
-- Table structure for table `entidades`
--

DROP TABLE IF EXISTS `entidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `primer_nombre` varchar(150) DEFAULT NULL,
  `segundo_nombre` varchar(150) DEFAULT NULL,
  `primer_apellido` varchar(150) DEFAULT NULL,
  `segundo_apellido` varchar(150) DEFAULT NULL,
  `id_tipo_documento` int(11) NOT NULL,
  `identificacion` varchar(45) NOT NULL,
  `razon_social` varchar(300) DEFAULT NULL,
  `pMunicipios_id` varchar(3) NOT NULL,
  `pMunicipios_departamentos_id` varchar(2) NOT NULL,
  `pMunicipios_departamentos_pPaises_id` varchar(3) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `id_tipo_entidad` int(11) NOT NULL,
  `sUsuarios_id` int(11) NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entidades_sUsuarios2_idx` (`sUsuarios_id`),
  KEY `fk_entidades_pMunicipios2_idx` (`pMunicipios_id`,`pMunicipios_departamentos_id`,`pMunicipios_departamentos_pPaises_id`),
  CONSTRAINT `fk_entidades_pMunicipios2` FOREIGN KEY (`pMunicipios_id`, `pMunicipios_departamentos_id`, `pMunicipios_departamentos_pPaises_id`) REFERENCES `pmunicipios` (`id`, `departamentos_id`, `departamentos_pPaises_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entidades_sUsuarios2` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidades`
--

LOCK TABLES `entidades` WRITE;
/*!40000 ALTER TABLE `entidades` DISABLE KEYS */;
INSERT INTO `entidades` VALUES (1,'Gilberth','Miguel','Linero','Ariza',1,'1065571485',NULL,'001','13','090','Calle','3135448426','gilberth@adimporexport.com',1,1,'2012-11-29 03:25:00'),(2,'Carlos',NULL,'Castillo',NULL,1,'7919989',NULL,'001','13','090','c','1','g',1,1,'2012-12-04 11:18:00');
/*!40000 ALTER TABLE `entidades` ENABLE KEYS */;
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
