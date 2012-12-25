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
-- Table structure for table `ppaises`
--

DROP TABLE IF EXISTS `ppaises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ppaises` (
  `id` varchar(3) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `sUsuarios_id` int(11) NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_pPaises_sUsuarios1_idx` (`sUsuarios_id`),
  CONSTRAINT `fk_pPaises_sUsuarios1` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppaises`
--

LOCK TABLES `ppaises` WRITE;
/*!40000 ALTER TABLE `ppaises` DISABLE KEYS */;
INSERT INTO `ppaises` VALUES ('005','Alemania',1,'2012-12-04 10:49:00'),('010','American Samoa',1,'2012-11-29 03:33:00'),('015','Andorra',1,'2012-11-29 03:33:00'),('020','Antillas Netherlands',1,'2012-11-29 03:33:00'),('025','Argentina',1,'2012-11-29 03:33:00'),('030','Armenia',1,'2012-11-29 03:33:00'),('035','Aruba',1,'2012-11-29 03:33:00'),('040','Australia',1,'2012-11-29 03:33:00'),('045','Austria',1,'2012-11-29 03:33:00'),('050','Bahamas',1,'2012-11-29 03:33:00'),('055','Belgica',1,'2012-11-29 03:33:00'),('060','Bermudas',1,'2012-11-29 03:33:00'),('065','Bolivia',1,'2012-11-29 03:33:00'),('070','Brasil',1,'2012-11-29 03:33:00'),('075','Bulgaria',1,'2012-11-29 03:33:00'),('080','Burundi',1,'2012-11-29 03:33:00'),('085','Canada',1,'2012-11-29 03:33:00'),('090','Colombia',1,'2012-11-29 03:33:00'),('095','Corea',1,'2012-11-29 03:33:00'),('100','Costa Rica',1,'2012-11-29 03:33:00'),('105','Croatia',1,'2012-11-29 03:33:00'),('110','Cuba',1,'2012-11-29 03:33:00'),('115','Chile',1,'2012-11-29 03:33:00'),('120','China',1,'2012-11-29 03:33:00'),('125','Dinamarca',1,'2012-11-29 03:33:00'),('130','Ecuador',1,'2012-11-29 03:33:00'),('135','El Salvador',1,'2012-11-29 03:33:00'),('140','España',1,'2012-11-29 03:33:00'),('145','Estados Unidos',1,'2012-11-29 03:33:00'),('150','Estonia',1,'2012-11-29 03:33:00'),('155','Faroe Islands',1,'2012-11-29 03:33:00'),('160','Filipinas',1,'2012-11-29 03:33:00'),('165','Finlandia',1,'2012-11-29 03:33:00'),('170','Francia',1,'2012-11-29 03:33:00'),('175','Gran Bretaña',1,'2012-11-29 03:33:00'),('180','Grecia',1,'2012-11-29 03:33:00'),('185','Greenland',1,'2012-11-29 03:33:00'),('190','Guatemala',1,'2012-11-29 03:33:00'),('195','Guayana Francesa',1,'2012-11-29 03:33:00'),('200','Honduras',1,'2012-11-29 03:33:00'),('205','Hong Kong',1,'2012-11-29 03:33:00'),('210','Hungria',1,'2012-11-29 03:33:00'),('215','Irlanda',1,'2012-11-29 03:33:00'),('220','Isla de Man',1,'2012-11-29 03:33:00'),('225','Islas Caimanes',1,'2012-11-29 03:33:00'),('230','Islas Virgenes Inglesas',1,'2012-11-29 03:33:00'),('235','Islas Virgenes U.S.',1,'2012-11-29 03:33:00'),('240','Israel',1,'2012-11-29 03:33:00'),('245','Italia',1,'2012-11-29 03:33:00'),('250','Jamaica',1,'2012-11-29 03:33:00'),('255','Japon',1,'2012-11-29 03:33:00'),('260','Jordania',1,'2012-11-29 03:33:00'),('265','Libano',1,'2012-11-29 03:33:00'),('270','Liberia',1,'2012-11-29 03:33:00'),('275','Liechenstein',1,'2012-11-29 03:33:00'),('280','Lithuania',1,'2012-11-29 03:33:00'),('285','Luxemburgo',1,'2012-11-29 03:33:00'),('290','Malaysia',1,'2012-11-29 03:33:00'),('295','Mexico',1,'2012-11-29 03:33:00'),('300','Netherlands',1,'2012-11-29 03:33:00'),('305','Nicaragua',1,'2012-11-29 03:33:00'),('310','Norfolk Island',1,'2012-11-29 03:33:00'),('315','Norway - Noruega',1,'2012-11-29 03:33:00'),('320','Nueva Zelanda',1,'2012-11-29 03:33:00'),('325','Pakistan',1,'2012-11-29 03:33:00'),('330','Panama',1,'2012-11-29 03:33:00'),('335','Paraguay',1,'2012-11-29 03:33:00'),('340','Peru',1,'2012-11-29 03:33:00'),('345','Polonia',1,'2012-11-29 03:33:00'),('350','Portugal',1,'2012-11-29 03:33:00'),('355','Puerto Rico',1,'2012-11-29 03:33:00'),('360','Republica Checa',1,'2012-11-29 03:33:00'),('365','Republica Democratica del congo',1,'2012-11-29 03:33:00'),('370','Republica Dominicana',1,'2012-11-29 03:33:00'),('375','Rumania',1,'2012-11-29 03:33:00'),('380','Rusia',1,'2012-11-29 03:33:00'),('385','Rwanda',1,'2012-11-29 03:33:00'),('390','Singapur',1,'2012-11-29 03:33:00'),('395','Slovakia',1,'2012-11-29 03:33:00'),('400','Sri Lanka',1,'2012-11-29 03:33:00'),('405','Sur Africa',1,'2012-11-29 03:33:00'),('410','Suecia',1,'2012-11-29 03:33:00'),('415','Suiza',1,'2012-11-29 03:33:00'),('420','Taiwan',1,'2012-11-29 03:33:00'),('425','Thailandia',1,'2012-11-29 03:33:00'),('430','Tonga',1,'2012-11-29 03:33:00'),('435','Trinidad y Tobago',1,'2012-11-29 03:33:00'),('440','Turkia',1,'2012-11-29 03:33:00'),('445','Turmenistan',1,'2012-11-29 03:33:00'),('450','Uganda',1,'2012-11-29 03:33:00'),('455','Ukraine',1,'2012-11-29 03:33:00'),('460','Uruguay',1,'2012-11-29 03:33:00'),('465','Venezuela',1,'2012-11-29 03:33:00'),('470','Yugoslavia',1,'2012-11-29 03:33:00'),('475','Zaire',1,'2012-11-29 03:33:00'),('480','Otros Paises',1,'2012-11-29 03:33:00');
/*!40000 ALTER TABLE `ppaises` ENABLE KEYS */;
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
