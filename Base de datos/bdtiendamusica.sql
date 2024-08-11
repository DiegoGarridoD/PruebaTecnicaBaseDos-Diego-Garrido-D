-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: bdtiendamusica
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `artistas`
--

DROP TABLE IF EXISTS `artistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artistas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artistas`
--

LOCK TABLES `artistas` WRITE;
/*!40000 ALTER TABLE `artistas` DISABLE KEYS */;
INSERT INTO `artistas` VALUES (138,'Reino Unido','The Beatles'),(139,'Estados Unidos','Elvis Presley'),(140,'Estados Unidos','Michael Jackson'),(141,'Estados Unidos','Madonna'),(142,'Reino Unido','Queen'),(143,'Reino Unido','The Rolling Stones'),(144,'Reino Unido','Led Zeppelin'),(145,'Reino Unido','Pink Floyd'),(146,'Australia','AC/DC'),(147,'Reino Unido','David Bowie'),(148,'Irlanda','U2'),(149,'Estados Unidos','The Beach Boys'),(150,'Estados Unidos','Bob Dylan'),(151,'Estados Unidos','Bruce Springsteen'),(152,'Reino Unido','The Who'),(153,'Estados Unidos','Nirvana'),(154,'Reino Unido','The Police'),(155,'Estados Unidos','The Doors'),(156,'Estados Unidos','The Eagles'),(157,'Reino Unido','Fleetwood Mac'),(158,'Estados Unidos','Aerosmith'),(159,'Estados Unidos','Guns N\' Roses'),(160,'Estados Unidos','Red Hot Chili Peppers'),(161,'Reino Unido','The Clash'),(162,'Estados Unidos','Metallica'),(163,'Estados Unidos','Bon Jovi'),(164,'Estados Unidos','Pearl Jam'),(165,'Estados Unidos','Green Day'),(166,'Estados Unidos','R.E.M.'),(167,'Reino Unido','The Cure'),(168,'Reino Unido','Oasis'),(169,'Reino Unido','Blur'),(170,'Reino Unido','Radiohead'),(171,'Reino Unido','Coldplay'),(172,'Reino Unido','The Smiths'),(173,'Suecia','ABBA'),(174,'Reino Unido','Bee Gees'),(175,'Estados Unidos','Eagles'),(176,'Estados Unidos','Santana'),(177,'Reino Unido','Elton John'),(178,'Estados Unidos','Billy Joel'),(179,'Estados Unidos','Prince'),(180,'Barbados','Rihanna'),(181,'Estados Unidos','Beyoncé'),(182,'Reino Unido','Adele'),(183,'Estados Unidos','Taylor Swift'),(184,'Canadá','Justin Bieber'),(185,'Reino Unido','Ed Sheeran'),(186,'Estados Unidos','Bruno Mars'),(187,'Estados Unidos','Katy Perry'),(188,'Estados Unidos','Lady Gaga'),(189,'Colombia','Shakira'),(190,'México','Luis Miguel'),(191,'Colombia','Juanes'),(192,'México','Maná'),(193,'México','Café Tacvba'),(194,'Argentina','Soda Stereo'),(195,'Argentina','Charly García'),(196,'Argentina','Fito Páez'),(197,'México','Caifanes'),(198,'Argentina','Los Fabulosos Cadillacs'),(199,'Argentina','Los Auténticos Decadentes'),(200,'España','La Oreja de Van Gogh'),(201,'España','Mecano'),(202,'España','Alejandro Sanz'),(203,'España','Enrique Iglesias'),(205,'España','Jarabe de Palo'),(206,'España','Héroes del Silencio'),(210,'Chile','Los Prisioneros'),(211,'Chile','Chancho en Piedra'),(215,'Chile','Los Tres'),(217,'Chile','Los Bunkers');
/*!40000 ALTER TABLE `artistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discos`
--

DROP TABLE IF EXISTS `discos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `existencias` int NOT NULL,
  `precio` double NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `artista_id` int DEFAULT NULL,
  `genero_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK42c3lkah00pjkvypnynsy24qk` (`artista_id`),
  KEY `FKeaamrto94t10gpstvauaa5k9k` (`genero_id`),
  CONSTRAINT `FK42c3lkah00pjkvypnynsy24qk` FOREIGN KEY (`artista_id`) REFERENCES `artistas` (`id`),
  CONSTRAINT `FKeaamrto94t10gpstvauaa5k9k` FOREIGN KEY (`genero_id`) REFERENCES `generos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discos`
--

LOCK TABLES `discos` WRITE;
/*!40000 ALTER TABLE `discos` DISABLE KEYS */;
INSERT INTO `discos` VALUES (1,40,20000,'Abbey Road',138,1),(2,25,17000,'Thriller',140,2),(3,100,15000,'Back in Black',146,1),(4,20,23000,'The Dark Side of the Moon',145,1),(5,25,18000,'Hotel California',156,1),(6,15,20000,'Born in the USA',151,1),(7,35,16000,'Rumours',157,1),(8,10,14000,'Nevermind',153,28),(9,45,21000,'Sgt. Pepper\'s Lonely Hearts Club Band',138,1),(11,20,17000,'Appetite for Destruction',159,1),(12,29,16000,'Let It Be',138,1),(13,25,18000,'Led Zeppelin IV',144,1),(14,15,22000,'Purple Rain',179,2),(15,40,20000,'The Joshua Tree',148,1),(16,35,19000,'The Wall',145,1),(17,20,17000,'Like a Virgin',141,2),(18,50,21000,'Sticky Fingers',143,1),(20,100,21000,'La voz de los 80',210,1),(26,13,25000,'Vida de Perros',217,1);
/*!40000 ALTER TABLE `discos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Rock'),(2,'Pop'),(3,'Metal'),(4,'Trap'),(5,'K-Pop'),(7,'Tango'),(10,'Blues'),(11,'Reggae'),(12,'Hip Hop'),(15,'Classical'),(16,'Electronic'),(18,'Punk'),(19,'Soul'),(20,'Funk'),(21,'Disco'),(23,'Latin'),(24,'Folk'),(27,'Hard Rock'),(28,'Grunge'),(31,'Rock Pop');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-11 14:24:03
