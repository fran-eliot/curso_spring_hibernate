CREATE DATABASE  IF NOT EXISTS `buscador` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `buscador`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: buscador
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `idItem` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `tematica` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'http://www.amazon.es','libros','web de libros y más cosas'),(2,'http://www.fnac.es','libros','libreria completa'),(3,'http://www.travel.es','viajes','viajes por el mundo'),(4,'http://www.game.es','juegos','el mundo del juego'),(5,'http://www.fly.com','viajes','vuelos a todos los destinos'),(6,'http://www.techbooks.com','libros','libros técnicos'),(7,'http://thgame.es','juegos','juegos retro'),(8,'www.thebook.es','libros','libros antiguos'),(9,'http://mygames.es','juegos','alquiler de juegos'),(10,'http://www.spacebooks.es','libros','libros de astronomía'),(11,'pruebaurl.com','libros','bla bla'),(12,'http://www.microservicios.es','libros','web de libros de servicios'),(13,'http://www.librosworld.es','libros','libros de idiomas'),(14,'nuevaurl.com','libros','de todo un poco '),(15,'probando.com','juegos','solo jugar'),(16,'http://www.ebay.es','libros','de todo'),(17,'pruebando libros','libros','libros de prueba'),(18,'mymusic','musica','todo múscia'),(19,'http://travelnow','viajes','viajes 360'),(20,'http://www.allbooks.es','libros','libros de todo tipo'),(21,'myurl','mytematica','mydescription'),(23,'http://www.librostop.es','libros','web de libros más vendidos');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-18 10:48:36
