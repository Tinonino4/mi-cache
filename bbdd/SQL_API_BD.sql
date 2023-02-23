-- api.userdetails definition

CREATE TABLE `userdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(300) NOT NULL,
  `password` varchar(300) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `role` text NOT NULL,
  `email` varchar(100),
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `PID` varchar(30),
  `city` varchar(100),
  `birthday` varchar(15) default '',
  `name` varchar(100),
  `surname` varchar(100),
  `zipcode` varchar(8),
  `phone_number` varchar(20),
  `photo` longblob,
  `job_title` varchar(50),
  `education` varchar(100),
  PRIMARY KEY (`id`)
)AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_USERDETAILS_TR` BEFORE INSERT ON `userdetails` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
	SET NEW.UPDATED_AT = SYSDATE();
END;
CREATE DEFINER=`root`@`%` TRIGGER `BU_USERDETAILS_TR` BEFORE UPDATE ON `userdetails` FOR EACH ROW BEGIN
   SET NEW.UPDATED_AT = SYSDATE();
END;
*/
-- api.rol definition

-- CREATE TABLE `rol` (
  -- `id` int NOT NULL AUTO_INCREMENT,
  -- `name` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  -- PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- api.experience definition

CREATE TABLE `experience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `companyName` varchar(250) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `department` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `position` varchar(250) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `startDate` date,
  `finishDate` date,
  `functions` varchar(1000) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `experience_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`)
) AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_EXPERIENCE_TR` BEFORE INSERT ON `experience` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
	SET NEW.UPDATED_AT = SYSDATE();
END;
CREATE DEFINER=`root`@`%` TRIGGER `BU_EXPERIENCE_TR` BEFORE UPDATE ON `experience` FOR EACH ROW BEGIN
   SET NEW.UPDATED_AT = SYSDATE();
END;
*/

-- api.confirmation_token definition
CREATE TABLE `confirmation_token` (
  id int NOT NULL auto_increment,
  idUser int DEFAULT NULL,
  confirmation_token varchar(250) NOT NULL,
  created_at date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `conf_token_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`)
) AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_CONFIRMATION_TOKEN_TR` BEFORE INSERT ON `confirmation_token` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
END;
*/
-- api.cache_request definition

CREATE TABLE `cache_request` (
  id int NOT NULL auto_increment,
  idUser int NOT NULL,
  idExperience int NOT NULL,
  idRelationship int NOT NULL,
  stillWorksThere tinyint(1) NOT NULL,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  url varchar(100) NOT NULL,
  finished tinyint(1) NOT NULL DEFAULT 0,
  phone_number varchar(20),
  created_at date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `cache_request_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`),
  KEY `idExperience` (`idExperience`),
  CONSTRAINT `cache_request_ibfk_2` FOREIGN KEY (`idExperience`) REFERENCES `experience` (`id`)
) AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_CACHE_REQUEST_TR` BEFORE INSERT ON `cache_request` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
END;
*/
-- api.requested_cache definition

CREATE TABLE `requested_cache` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `idExperience` int DEFAULT NULL,
  `idCacheRequest` int DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `flexibility1` float DEFAULT NULL,
  `flexibility2` float DEFAULT NULL,
  `flexibility3` float DEFAULT NULL,
  `flexibility4` float DEFAULT NULL,
  `flexibility5` float DEFAULT NULL,
  `flexibility` float DEFAULT NULL,
  `integrity1` float DEFAULT NULL,
  `integrity2` float DEFAULT NULL,
  `integrity3` float DEFAULT NULL,
  `integrity4` float DEFAULT NULL,
  `integrity5` float DEFAULT NULL,
  `integrity` float DEFAULT NULL,
  `proactivity1` float DEFAULT NULL,
  `proactivity2` float DEFAULT NULL,
  `proactivity3` float DEFAULT NULL,
  `proactivity4` float DEFAULT NULL,
  `proactivity5` float DEFAULT NULL,
  `proactivity` float DEFAULT NULL,
  `question1` int DEFAULT NULL,
  `question2` int DEFAULT NULL,
  `question3` int DEFAULT NULL,
  `question4` varchar(100) DEFAULT NULL,
  `question5` varchar(100) DEFAULT NULL,
  `question6` int DEFAULT NULL,
  `selfconfidence1` float DEFAULT NULL,
  `selfconfidence2` float DEFAULT NULL,
  `selfconfidence3` float DEFAULT NULL,
  `selfconfidence4` float DEFAULT NULL,
  `selfconfidence5` float DEFAULT NULL,
  `selfconfidence` float DEFAULT NULL,
  `teamwork1` float DEFAULT NULL,
  `teamwork2` float DEFAULT NULL,
  `teamwork3` float DEFAULT NULL,
  `teamwork4` float DEFAULT NULL,
  `teamwork5` float DEFAULT NULL,
  `teamwork` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `requested_cache_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`),
  KEY `idExperience` (`idExperience`),
  CONSTRAINT `requested_cache_ibfk_2` FOREIGN KEY (`idExperience`) REFERENCES `experience` (`id`),
  KEY `idCacheRequest` (`idCacheRequest`),
    CONSTRAINT `requested_cache_ibfk_3` FOREIGN KEY (`idCacheRequest`) REFERENCES `cache_request` (`id`)
) AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_REQUESTED_CACHE_TR` BEFORE INSERT ON `requested_cache` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
END;
*/
-- api.user_skills_values definition

CREATE TABLE `user_skills_values` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `teamwork` float DEFAULT NULL,
  `selfconfidence` float DEFAULT NULL,
  `proactivity` float DEFAULT NULL,
  `integrity` float DEFAULT NULL,
  `flexibility` float DEFAULT NULL,
  `average` float DEFAULT NULL,
  `updated_at` date NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `user_skills_values_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`)
) AUTO_INCREMENT=1;

/*
CREATE DEFINER=`root`@`%` TRIGGER `BI_USV_TR` BEFORE INSERT ON `user_skills_values` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
   	SET NEW.UPDATED_AT = SYSDATE();
END;

CREATE DEFINER=`root`@`%` TRIGGER `Bu_USV_TR` BEFORE INSERT ON `user_skills_values` FOR EACH ROW BEGIN
   	SET NEW.CREATED_AT = SYSDATE();
END;
*/

CREATE TABLE `reset_password` (
  id int NOT NULL auto_increment,
  idUser int DEFAULT NULL,
  code varchar(6) NOT NULL,
  created_at date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `reset_pass_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `userdetails` (`id`)
) AUTO_INCREMENT=1;