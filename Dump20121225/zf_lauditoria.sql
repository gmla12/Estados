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
-- Table structure for table `lauditoria`
--

DROP TABLE IF EXISTS `lauditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lauditoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `accion` varchar(10) NOT NULL,
  `valor_anterior` longtext,
  `valor_nuevo` longtext,
  `sUsuarios_id` int(11) NOT NULL,
  `sFormularios_id` int(11) NOT NULL,
  `referencia` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_lAuditoria_sUsuarios1_idx` (`sUsuarios_id`),
  KEY `fk_lAuditoria_sFormularios1_idx` (`sFormularios_id`),
  CONSTRAINT `fk_lAuditoria_sFormularios1` FOREIGN KEY (`sFormularios_id`) REFERENCES `sformularios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lAuditoria_sUsuarios1` FOREIGN KEY (`sUsuarios_id`) REFERENCES `susuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lauditoria`
--

LOCK TABLES `lauditoria` WRITE;
/*!40000 ALTER TABLE `lauditoria` DISABLE KEYS */;
INSERT INTO `lauditoria` VALUES (1,'2012-12-04 16:49:12','Nuevo','','id=006&nombre=ppp',1,1,'006'),(2,'2012-12-07 11:21:21','Modificar','nombre=\'pppp\'','nombre=\'ppppp\'',1,1,'006'),(3,'2012-12-07 11:29:45','Modificar','nombre=\'ppppp\'','nombre=\'ppp\'',1,1,'006'),(4,'2012-12-07 11:29:54','Eliminar','id=006&nombre=ppp','',1,1,'006'),(5,'2012-12-07 11:32:41','Nuevo','','id=006&nombre=ppp',1,1,'006'),(6,'2012-12-07 11:34:27','Eliminar','id=006&nombre=ppp','',1,1,'006'),(7,'2012-12-07 11:34:37','Nuevo','','id=006&nombre=ppp',1,1,'006'),(8,'2012-12-11 11:22:28','Eliminar','id=006&nombre=ppp','',1,1,'006'),(9,'2012-12-11 11:22:38','Nuevo','','id=007&nombre=popopopo',1,1,'007'),(10,'2012-12-11 11:22:51','Modificar','id=\'006\'&nombre=\'ppp\'','id=\'007\'&nombre=\'popopop\'',1,1,'007'),(11,'2012-12-11 11:22:58','Eliminar','id=007&nombre=popopop','',1,1,'007'),(12,'2012-12-11 16:03:08','Nuevo','','id=01&id_pais=090&nombre=popopo',1,2,'09001'),(13,'2012-12-12 15:56:19','Modificar','id_pais=\'090\'','id_pais=\'080\'',1,2,'08001'),(14,'2012-12-12 16:00:24','Modificar','nombre=\'popopo\'','nombre=\'popo\'',1,2,'09001'),(15,'2012-12-12 16:01:24','Modificar','nombre=\'popo\'','nombre=\'popopu\'',1,2,'09001'),(16,'2012-12-12 16:02:32','Modificar','nombre=\'popopu\'','nombre=\'popopupu\'',1,2,'09001'),(22,'2012-12-12 16:07:33','Modificar','nombre=\'popopupu\'','nombre=\'popopupupo\'',1,2,'09001'),(23,'2012-12-12 16:08:32','Eliminar','id=01&id_pais=090&nombre=popopupupo','',1,2,'09001'),(24,'2012-12-13 10:50:49','Nuevo','','id=04&id_pais=005&nombre=pol',1,2,'00504'),(25,'2012-12-13 10:53:23','Eliminar','id=04&id_pais=005&nombre=pol','',1,2,'00504'),(26,'2012-12-13 10:53:50','Nuevo','','id=02&id_pais=005&nombre=pil',1,2,'00502'),(27,'2012-12-13 10:54:02','Modificar','&nombre=\'pil\'','&nombre=\'piloto\'',1,2,'00502'),(28,'2012-12-13 10:54:09','Eliminar','id=02&id_pais=005&nombre=piloto','',1,2,'00502'),(29,'2012-12-13 10:54:36','Nuevo','','id=002&nombre=pul',1,1,'002'),(30,'2012-12-13 10:54:48','Modificar','nombre=\'pul\'','nombre=\'pulto\'',1,1,'002'),(31,'2012-12-13 10:54:55','Eliminar','id=002&nombre=pulto','',1,1,'002'),(32,'2012-12-13 15:53:25','Nuevo','','id=101id_departamento=13&id_pais=090&nombre=pol',1,3,'09013101'),(33,'2012-12-13 16:07:45','Modificar','id_departamento=\'13\'&nombre=\'pol\'','id_departamento=\'null\'&nombre=\'polo\'',1,3,'090null101'),(34,'2012-12-13 16:08:50','Modificar','nombre=\'pol\'','nombre=\'polo\'',1,3,'09013101'),(35,'2012-12-13 16:09:16','Modificar','nombre=\'polo\'','nombre=\'poloo\'',1,3,'09013101'),(36,'2012-12-13 16:09:24','Eliminar','id=101&id_departamento=13&id_pais=090&nombre=poloo','',1,3,'09013101'),(37,'2012-12-17 15:34:13','Nuevo','','id=0&nombrCorto=CTG&descripcion=Cartagena',1,5,'0'),(38,'2012-12-17 15:38:01','Nuevo','','id=0&nombrCorto=CTG&descripcion=Cartagena',1,5,'0'),(39,'2012-12-17 15:39:02','Eliminar','id=2&nombreCorto=CTG&descripcion=Cartagena','',1,5,'2'),(40,'2012-12-17 15:44:53','Nuevo','','id=0&nombrCorto=BGT&descripcion=Bogota',1,5,'0'),(41,'2012-12-17 16:36:08','Eliminar','id=1&nombreCorto=CTG&descripcion=Cartagena','',1,5,'1'),(42,'2012-12-17 16:36:18','Eliminar','id=3&nombreCorto=BGT&descripcion=Bogota','',1,5,'3'),(43,'2012-12-17 16:37:20','Nuevo','','id=4&nombrCorto=CTG&descripcion=Cartagena',1,5,'4'),(44,'2012-12-17 16:42:28','Modificar','descripcion=\'Cartagena\'','descripcion=\'Cartagenaa\'',1,5,'4'),(45,'2012-12-17 16:42:36','Modificar','descripcion=\'Cartagenaa\'','descripcion=\'Cartagena\'',1,5,'4'),(46,'2012-12-17 16:42:43','Eliminar','id=4&nombreCorto=CTG&descripcion=Cartagena','',1,5,'4'),(47,'2012-12-18 15:46:37','Nuevo','','id=5&nombrCorto=CTG&descripcion=Cartagena',1,5,'5'),(48,'2012-12-18 15:48:25','Nuevo','','id=6&nombrCorto=BOG&descripcion=Bogota',1,5,'6'),(49,'2012-12-19 14:37:46','Nuevo','','&id=5&nombre_corto=MEB&descripcion=Muelles el Bosque&id_departamento=13&id_pais=090id_Municipio=001id_Sucursal=0',1,4,'5');
/*!40000 ALTER TABLE `lauditoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-25 11:39:53
