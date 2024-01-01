-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: travel_ticket_booking_web
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tripName` varchar(30) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `noOfAdults` int DEFAULT NULL,
  `noOfChildren` int DEFAULT NULL,
  `booker` varchar(30) DEFAULT NULL,
  `bookingDate` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'Stand-alone foregrou',89.11875,2,1,'PHV','2023-12-16',0),(2,'Advanced human-resou',22.481250000000003,1,1,'NgHgM','2023-12-16',1),(3,'Devolved systemic mi',357.71250000000003,4,2,'PHV','2023-12-16',0),(5,'Synergized 4thgenera',228.72499999999997,2,3,'NgHgM','2023-12-16',0),(7,'Extended content-bas',443.28374999999994,2,5,'PHV','2023-12-16',1),(8,'Integrated 3rdgenera',622.05,4,4,'NgHgM','2023-12-16',1),(21,'Advanced human-resou',52.456250000000004,2,3,'NgHgM','2023-12-16',0),(22,'De-engineered logist',158.60625000000002,2,1,'jcl','2023-12-17',1);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `postName` varchar(30) DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `desc_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'Winter of Hue','https://th.bing.com/th/id/OIP.ZWUwEoZPaK6TjSi3C7sb7AHaJ4?w=127&h=180&c=7&r=0&o=5&dpr=1.6&pid=1.7','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit'),(2,'Summer of Hue','https://th.bing.com/th/id/R.8b8b897663d2116ff1b7b1a92cd18c0f?rik=RlfxGK31BQADAg&pid=ImgRaw&r=0','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit'),(3,'My Hanoi','https://th.bing.com/th/id/OIP.K1lbti7eWwBVdCX9nKFL5QHaEK?rs=1&pid=ImgDetMain','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit'),(6,'Hanoi Food Tour','https://th.bing.com/th/id/OIP.igMJbu7IKxVKn1OCWMYZ2QAAAA?rs=1&pid=ImgDetMain','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reviewer` varchar(30) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `reviewDate` date DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'NgHgM',1,4,'2023-12-17','Hello guys!',1),(2,'NgHgM',1,4,'2023-12-17','Really nice :)',1),(3,'NgHgM',1,5,'2023-12-17','I\'ll have a go',1),(4,'PHV',1,4,'2023-12-17','Me toooooo',1),(5,'PCM',1,4,'2023-12-17','Go together? Shall we?',1),(6,'PCM',2,4,'2023-12-17','Vietnammm',1),(7,'jcl',6,4,'2023-12-17','Delicious...',1),(8,'jcl',3,5,'2023-12-17','Good',1),(9,'NHM',6,3,'2023-12-17','Yeahh',1),(10,'NHM',3,5,'2023-12-17','Give it a try?',1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trips`
--

DROP TABLE IF EXISTS `trips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trips` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tripName` varchar(30) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `desc_url` varchar(100) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trips`
--

LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
INSERT INTO `trips` VALUES (1,'Stand-alone foregrou',142.59,'https://th.bing.com/th/id/OIP.UgD9Cnp8xxPnsaHGLKgZrwHaFj?rs=1&pid=ImgDetMain','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','0788 Jessica Crossing Apt.','2023-02-02','2023-02-13',1),(2,'Advanced human-resou',59.95,'https://th.bing.com/th/id/OIP.JdiwC7ScXu-G9k05lwmNFAAAAA?rs=1&pid=ImgDetMain','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','1750 Rose Skyway Apt. 6','2024-03-08','2024-03-11',1),(3,'Devolved systemic mi',286.17,'https://mysterioustrip.com/wp-content/uploads/2021/08/Holiday-Trip.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','890 Hicks Street, Benton','2024-06-22','2024-07-06',1),(4,'Object-based scalabl',100.95,'https://bsmedia.business-standard.com/_media/bs/img/article/2018-06/03/full/1528032172-4904.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','423 Butler Mount, Rodger','2024-05-31','2024-06-09',1),(5,'Synergized 4thgenera',261.4,'https://thetravellingfool.com/wp-content/uploads/2016/11/Holiday_20Flights-XL.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','832 Ashley Summit, Scott','2024-07-20','2024-07-26',1),(6,'Innovative attitude-',81.59,'https://www.justrunlah.com/wp-content/uploads/2016/12/Travel-the-world.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','Unit 7254 Box 3844, DPO','2024-06-26','2024-06-30',1),(7,'Extended content-bas',394.03,'https://americancityexpress.net/wp-content/uploads/2017/12/christmas-travel-768x716.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','USCGC Goodwin, FPO AE 7','2024-01-25','2024-01-27',1),(8,'Integrated 3rdgenera',414.7,'https://www.theguytravel.com/wp-content/uploads/2015/06/Holiday-Travel36.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','69760 Tricia Station, New','2023-07-27','2023-08-04',1),(9,'Function-based attit',175.58,'https://grandriverscommunitybank.com/wp-content/uploads/2019/01/vacation-2.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','24529 Anthony Islands Su','2024-10-15','2024-10-26',1),(10,'Advanced dedicated i',257.61,'https://th.bing.com/th/id/OIP._5cVffJnO4hCgIYFGU98uwHaEJ?rs=1&pid=ImgDetMain','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','281 Steven Knolls, Port','2023-03-25','2023-03-29',1),(11,'De-engineered logist',253.77,'https://blog.prettylittlething.com/wp-content/uploads/2017/07/holiday-getaway.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','8766 Stone Stravenue, We','2023-02-14','2023-02-26',1),(12,'Front-line 5thgenera',304.67,'https://excitingtravel.mv/wp-content/uploads/2020/02/gg.jpg','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','5657 Potts Expressway, P','2023-03-28','2023-04-09',1),(13,'Vision-oriented resp',131.87,'https://www.countryholidaystravel.com/assets/new/images/about-img.png','https://docs.google.com/document/d/1RxCygg_xP3n5S6N4CvTdYgxXBO2EOoTNBYE-NzP9IFY/edit?usp=sharing','65280 Johnson Lakes Sui','2023-06-26','2023-07-10',1);
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fullName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'janicewebb','&P0DOU@q_v','Carolyn Daniel','twilliams@example.com','1498939252','3838 Rachel Heights',1,1),(3,'anthonywhite','2hEJBNSj+h','Justin Bailey','longnicole@example.net','4969026607','PSC 0417, Box 4895',1,1),(4,'pattondeborah','z3AcFegO)r','Antonio Parrish','clarkrebecca@example.org','5939497651','8824 Jennifer Terrace',1,1),(5,'sarah45','Ow8XX8OkT@','Elizabeth Fritz','rspears@example.org','4312966385','698 Debra Village',1,0),(6,'jenkinsrebecca','A^y0%fLd!Z','Nicholas Roach','clarkamy@example.org','1756493213','3543 Hayes Meadow',1,1),(7,'melissa42','f^5lFAwA@z','Daryl Parks','ojones@example.org','1496965345','875 Alyssa Crescent',1,0),(8,'ashley57','QYk8l5dpf_','Jennifer Mcfarland','andersonemily@example.com','1460567063','01769 Jeffrey Centers',1,1),(9,'robertkennedy','%&6t&KUimp','Jessica Smith','ocox@example.net','1347481085','9851 Ashley Garden',1,0),(10,'scotthensley','p^4iEM9ecw','Benjamin Eaton','bjohnson@example.net','1218757048','793 Campbell Plaza',1,0),(11,'dwest','V*15_Ubx$z','Mitchell Harris','emily61@example.com','9486128915','442 Louis Cliff',1,0),(12,'gloverbrittany','6dUkwcW)@w','Rick Williams','walshmichelle@example.com','1983345573','6124 Paula Inlet',1,0),(13,'wendy16','6D8G6Kt1o(','Anthony Garcia','johnking@example.org','1622794762','USS Watson, FPO AE',1,0),(14,'shannon78','QD(*S3Jc#K','Jacqueline Peterson','ugill@example.org','6745172299','15977 Parks Viaduct',1,0),(15,'bmcdonald','+2)pZV(k*Z','Melanie Williams','hrobertson@example.org','1800689127','9579 Katherine Oval',1,1),(16,'cunninghamkevin','j0I0pGYg)g','Stephanie Hudson','lisacarney@example.net','5103377041','0633 Benjamin Villages',1,1),(17,'dianaaustin','+gs#pRDEc3','Miranda Dixon','jamesjohnson@example.com','7158091424','1856 Dunn Street',1,0),(18,'samantha23','R0Go+OuG+e','Evan Arnold DDS','rebeccahall@example.net','5326226064','60957 Diaz Passage',1,0),(19,'pinedajames','w(^d4_Fmru','Dana Stephens','garciapatricia@example.com','2199545582','0177 Davis Locks',1,1),(20,'kendrafuller','0Idpi8w!&T','Arthur Ross DVM','olsonnatasha@example.com','7125412993','34547 Simpson Port',1,1),(21,'annandrews','(2P*JqVc5w','Brian Willis','lorievans@example.net','1416242517','4254 Ochoa Island',1,0),(22,'yeseniawaller','oGV26Osog#','Allison Lopez','jasmine70@example.org','5405764317','PSC 9533, Box 1075',1,0),(23,'whitejon','50kSlTee)D','Scott Kaiser','john54@example.com','4593393918','63331 Jones Square',1,1),(24,'carlos68','#9BBYY$qO3','Sophia Fischer','traymond@example.com','7593237413','4816 Johnson Creek',1,0),(25,'athompson','_R*RVF@k91','Sarah Taylor','dmendez@example.org','3495750143','2279 Oneill Lodge',1,1),(26,'hernandezlaurie','*2QAz)kdxG','Sarah Buckley','hardyrachel@example.com','7406860481','71232 Stephen Route',1,1),(27,'eric24','@ARgD4aN1D','Mary Castillo','stacey66@example.org','2994948518','4245 Graham Valley',1,0),(28,'jclay','H0KpC4fS%4','Stephen Scott','gerald77@example.com','5418547775','25259 Bridges Turnpike',1,0),(29,'sshelton','p7Avun_S$3','Karen Johnson','huntjerry@example.net','1650783211','135 Amanda Fords',1,1),(30,'zherrera','MPr6Pbik@a','Destiny York','ryanosborne@example.net','8053800697','57871 Amanda Keys',1,1),(31,'jameschristensen','&8pCH4jfc5','Jamie Good','michaelsarah@example.com','7153296494','14649 Williams Cliff',1,0),(32,'heather63','z#S8_M3zRk','Christine Hansen','amber71@example.com','9534861732','Unit 4788 Box 1976',1,0),(33,'dmejia','0xN)h@rX(7','Jason Roy','michael23@example.com','2465252360','286 Peterson Parks',1,0),(34,'williamsaaron','Ol+MkGda&8','Frank White','jesse62@example.net','1255460550','00364 Sullivan Causeway',1,0),(35,'ruizkelly','f)7I75vJA7','Charles Jones','dorothystephens@example.org','2736930700','12601 Marsh Meadow',1,1),(36,'ruizkelly','f)7I75vJA7','Lucas Wallace','gbarnes@example.org','1998660847','0178 Veronica Plains',1,0),(44,'NgHgM','123','Nguyen Hoang Minh','khuongtunha1106@gmail.com','0989017306','Hanoi',1,1),(47,'NgVtH','111','Nguyen Viet Hoang','hoangnguyenviet2301@gmail.com','1111111111','Hanoi',1,0),(60,'jcl','111','Nguyen Nhat Linh','nhatlinhnguyen@gmail.com','0912062006','Hanoi',1,0),(122,'PHV','111','Pham Hien Vinh','phamhienvinh@gmail.com','1111111111','Hanoi',1,1),(123,'PCM','111','Phan Chinh Minh','phanchinhminh@gmail.com','1111111111','Hanoi',1,1),(125,'NHM','111','Nguyen Ha Minh','nguyenhaminh@gmail.com','0912345690','Hanoi',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-17  9:52:34
