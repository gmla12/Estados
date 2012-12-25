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
-- Table structure for table `Vines`
--

DROP TABLE IF EXISTS `Vines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vines` (
  `idVines` int(11) NOT NULL,
  `vin` varchar(45) DEFAULT NULL,
  `referencia` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `factura` varchar(45) DEFAULT NULL,
  `descripcion` longtext,
  `valor_fob` float DEFAULT NULL,
  `color` int(11) DEFAULT NULL,
  `huerfano` tinyint(1) DEFAULT NULL,
  `despachado` varchar(45) DEFAULT NULL,
  `FMMs_idFMMsI` int(11) NOT NULL,
  `FMMs_idFMMsS` int(11) DEFAULT NULL,
  `BLs_idBLs` int(11) NOT NULL,
  PRIMARY KEY (`idVines`),
  KEY `fk_Vines_FMMs1` (`FMMs_idFMMsS`),
  KEY `fk_Vines_BLs1` (`BLs_idBLs`),
  KEY `fk_Vines_FMMs2` (`FMMs_idFMMsI`),
  CONSTRAINT `fk_Vines_BLs1` FOREIGN KEY (`BLs_idBLs`) REFERENCES `BLs` (`idBLs`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vines_FMMs1` FOREIGN KEY (`FMMs_idFMMsS`) REFERENCES `FMMs` (`idFMMs`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vines_FMMs2` FOREIGN KEY (`FMMs_idFMMsI`) REFERENCES `FMMs` (`idFMMs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vines`
--

LOCK TABLES `Vines` WRITE;
/*!40000 ALTER TABLE `Vines` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vines` ENABLE KEYS */;
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
