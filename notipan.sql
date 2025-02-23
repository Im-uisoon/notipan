-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: notipan
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `member_entity`
--

DROP TABLE IF EXISTS `member_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_entity`
--

LOCK TABLES `member_entity` WRITE;
/*!40000 ALTER TABLE `member_entity` DISABLE KEYS */;
INSERT INTO `member_entity` VALUES (1,'1','P9H0DexABaBWMzKOeJ5ZqoYg9gBUQYE3cp+siZkUxkQ=','lXJOukxXNoN3RWEQILaTkg=='),(2,'2','3opieCjiZGhnM1kBngxzfU2QgTypXFO0nrdixtqsn4o=','s9QWGM378ngk1EkJRubFLA=='),(3,'3','Sdn9GDPQF5Zmxt0SH/bL8ZjG5lTo4HIeisDdPW+97sc=','1dpqf2gvai3iZasDOGQIxA==');
/*!40000 ALTER TABLE `member_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attachment` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `board` varchar(255) DEFAULT NULL,
  `content` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'첨부','admin','board1','처음으로 쓴 글','1빠'),(2,'첨부2','admin','board1','테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요','테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요테스트라글이길어요'),(3,'','admin','board1','우리집이 제일 맛나요','맛집 알아냈어요'),(4,'','admin','board1','전.. 모르거든요','미슐랭을 아세요?'),(5,NULL,'admin','board1','짧다.','짧은 글'),(6,NULL,'admin','board2','난 보이면 안돼','두 번째 게시판'),(7,'날짜','admin','board1','두번 째 페이지','테스트용 글'),(8,'','1','board3','인왕산 정상의 맑은 공기와 약수터의 물','점심 메뉴 추천'),(9,'','1','board3','면접만 보고 일 안하면 공짜 커피\r\n같은건 나쁜 생각이에요','카페 면접보면 커피 한잔 주는데'),(10,'#서울','1','board4','여기 꼬치 맛있어요','야키토리요이야마'),(11,'','1','board4','맛있긴한데 바는 아닙니다','한강에서 맥주');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-24  1:38:55
