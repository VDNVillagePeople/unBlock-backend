-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: unblock.c1u9yktiwdmi.us-east-2.rds.amazonaws.com    Database: unblock_prod
-- ------------------------------------------------------
-- Server version	5.6.29-log

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
-- Dumping data for table `Attraction`
--

/*!40000 ALTER TABLE `Attraction` DISABLE KEYS */;
INSERT INTO Attraction VALUES ('de949f41-0759-470a-8279-deb29a954f6d','This place is sick as all hell! Cheap drinks, hot chicks. Everything a party animal could want. Come in for a cheap beer and a shot courtesy of UnBlock!','13th Step Bar','ATTRACTION_DISABLED',0,0,'3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d');
/*!40000 ALTER TABLE `Attraction` ENABLE KEYS */;

--
-- Dumping data for table `Block`
--

/*!40000 ALTER TABLE `Block` DISABLE KEYS */;
INSERT INTO Block VALUES ('3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d','St Mark\'s','BLOCK_LIVE','328826e4-8b13-4302-a9cd-5a3a3a430d18',NULL),('da88750e-75b5-47a5-a085-f41dcd00bf25','Veselka','BLOCK_LIVE','328826e4-8b13-4302-a9cd-5a3a3a430d18',NULL);
/*!40000 ALTER TABLE `Block` ENABLE KEYS */;

--
-- Dumping data for table `City`
--

/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO City VALUES ('13eb309e-3c16-4672-9674-11b5473dbce0','Chicago','CITY_LIVE'),('1a2575c5-c1dc-4c3e-94d4-d710cf6e2d37','Portland','CITY_LIVE'),('5f6e294d-80b3-4cde-9019-dfbe0d18d4d4','Seattle','CITY_LIVE'),('6510a4f8-e9b2-450a-9dd4-8465ee6072de','St. Louis','CITY_LIVE'),('85fc209d-27bb-4938-a73f-47a81b95424d','Talahassee','CITY_LIVE'),('8bbaebb7-e140-4dbb-832b-042b91713d9c','New Orleans','CITY_LIVE'),('a4729c97-a7ed-4623-8d44-e5637dbe1ebe','Orlando','CITY_LIVE'),('b8a48a16-3288-4bb6-ab18-b7128bd98cb6','Manhattan','CITY_DISABLED'),('bf2b96f7-a5f6-4515-9201-aac7ed3e0f82','Zurich','CITY_LIVE'),('de5d3270-dedf-416b-90b0-d958c6769c59','Kansas City','CITY_LIVE');
/*!40000 ALTER TABLE `City` ENABLE KEYS */;

--
-- Dumping data for table `Neighborhood`
--

/*!40000 ALTER TABLE `Neighborhood` DISABLE KEYS */;
INSERT INTO Neighborhood VALUES ('18527191-4798-45c2-abeb-4668431b6d23','Freemont','NEIGHBORHOOD_STATUS_UNSPECIFIED','1a2575c5-c1dc-4c3e-94d4-d710cf6e2d37',NULL,NULL),('2508f9ff-f645-43cb-8c9c-844904be39d5','Tribeca','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6',NULL,NULL),('328826e4-8b13-4302-a9cd-5a3a3a430d18','East Village','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6',NULL,NULL),('438fa379-040e-457e-9454-cb58eb35952b','World Trade Center ','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6',NULL,NULL),('608c5919-5f96-4893-8576-997cdbac1eef','St. louis arch area','NEIGHBORHOOD_LIVE','6510a4f8-e9b2-450a-9dd4-8465ee6072de',NULL,NULL),('9b3c68b1-4162-4cd1-8a58-f414fd35ba1f','Double Wide Divid','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6',NULL,NULL);
/*!40000 ALTER TABLE `Neighborhood` ENABLE KEYS */;

--
-- Dumping data for table `Point`
--

/*!40000 ALTER TABLE `Point` DISABLE KEYS */;
INSERT INTO Point VALUES ('3962be09-22ab-4f28-8248-e4052fd8bd96',1,2,2,'3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d'),('a5d7ce9c-827b-4d9a-91c0-b510f7c6c8cf',0,5,0,'3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d'),('fd8da48b-e4ec-4bee-ad46-fcc0397a5403',2,3,3,'3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d');
/*!40000 ALTER TABLE `Point` ENABLE KEYS */;

--
-- Dumping data for table `User`
--

/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO User VALUES ('60f8df65-0305-408c-b9f7-273ac93eb2eb','dan@gmail.com','$2a$10$pz/yNoab1sc08iC.KGSMS.4IO.7DU/lPtHM3Ab9oAfsWfZqJOyrRW','dantheman','ADMIN'),('b484369b-e885-4799-a6c1-3c1f4518268e','testUser@gmail.com','$2a$10$nOoG4kM.tOPTdLF1kW823.4FVt0LqRcEd.DJdSY1vGm7Iq1FHaLcy','testUser','DEFAULT'),('e014aef1-b419-45a7-b51c-b6db6e8d2d19','lauren@gmail.com','$2a$10$7auwmLM14M3TJEQbK7.qOOG5CD5PiEQL2x4KXRv506R3BccC1c57u','lauren','ADMIN'),('f7760b39-a1d9-4d30-bcd0-de9498796e1f','scott@gmail.com','$2a$10$Rwm61vvwuGKB3Dn2sumSme2Sh0y9QWQClmhO.IgAQIKoH99LgOozy','scottTheBomb','ADMIN');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-17  9:05:17
