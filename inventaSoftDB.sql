-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.2.40-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para inventasoftdb
CREATE DATABASE IF NOT EXISTS `inventasoftdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `inventasoftdb`;

-- Volcando estructura para tabla inventasoftdb.category
CREATE TABLE IF NOT EXISTS `category` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.category: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`ID`, `TYPE`) VALUES
	(1, 'Categoria 1'),
	(2, 'Categoria 2'),
	(3, 'Categoria 3');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Volcando estructura para tabla inventasoftdb.movements
CREATE TABLE IF NOT EXISTS `movements` (
  `ID` int(11) NOT NULL,
  `QUANTITY` int(3) NOT NULL,
  `DATE` date NOT NULL,
  `PRODUCT_ID` int(4) NOT NULL,
  `USERS_ID` int(3) NOT NULL,
  `TYPE_MOVEMENT_ID` int(2) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_movements_users` (`USERS_ID`),
  KEY `FK_movements_products` (`PRODUCT_ID`) USING BTREE,
  KEY `FK_movements_movements` (`TYPE_MOVEMENT_ID`) USING BTREE,
  CONSTRAINT `FK_movements_products` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_movements_type_movements` FOREIGN KEY (`TYPE_MOVEMENT_ID`) REFERENCES `type_movements` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_movements_users` FOREIGN KEY (`USERS_ID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.movements: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `movements` DISABLE KEYS */;
INSERT INTO `movements` (`ID`, `QUANTITY`, `DATE`, `PRODUCT_ID`, `USERS_ID`, `TYPE_MOVEMENT_ID`) VALUES
	(1, 123, '2021-10-07', 1, 2, 2),
	(2, 123, '2021-10-17', 1, 1, 2);
/*!40000 ALTER TABLE `movements` ENABLE KEYS */;

-- Volcando estructura para tabla inventasoftdb.products
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(20) NOT NULL,
  `PRODUCT_NAME` varchar(30) NOT NULL,
  `REFERENCE` varchar(30) NOT NULL,
  `QUANTITY` int(3) NOT NULL,
  `CATEGORY_ID` int(2) NOT NULL,
  `EXPIRATION` date DEFAULT NULL,
  `LOCATION` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_products_category` (`CATEGORY_ID`),
  CONSTRAINT `FK_products_category` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.products: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`ID`, `CODE`, `PRODUCT_NAME`, `REFERENCE`, `QUANTITY`, `CATEGORY_ID`, `EXPIRATION`, `LOCATION`) VALUES
	(1, 'prod123', 'Producto 1', 'REF123', 1426, 1, '2021-10-07', 'stante 1'),
	(2, 'prod1232', 'Producto 12', 'REF1232', 150, 1, '2021-10-07', 'stante 1'),
	(3, 'prod123', 'Producto 1', 'REF123', 12, 1, '2021-10-15', 'dasdasd'),
	(4, '1234', 'prod', 'referencia 1123', 0, 2, '2021-10-12', 'unodos');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Volcando estructura para tabla inventasoftdb.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.profile: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`ID`, `TYPE`) VALUES
	(1, 'Administrador'),
	(2, 'Usuario'),
	(3, 'Almacen');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Volcando estructura para tabla inventasoftdb.type_movements
CREATE TABLE IF NOT EXISTS `type_movements` (
  `ID` int(2) NOT NULL,
  `TYPE_MOVEMENT` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.type_movements: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `type_movements` DISABLE KEYS */;
INSERT INTO `type_movements` (`ID`, `TYPE_MOVEMENT`) VALUES
	(1, 'tipo 1'),
	(2, 'tipo 2');
/*!40000 ALTER TABLE `type_movements` ENABLE KEYS */;

-- Volcando estructura para tabla inventasoftdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(3) NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(200) NOT NULL,
  `EMAIL` varchar(120) NOT NULL,
  `USER` varchar(20) NOT NULL,
  `PASSWORD` varchar(10) NOT NULL,
  `PROFILE_ID` int(2) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_users_profile` (`PROFILE_ID`),
  CONSTRAINT `FK_users_profile` FOREIGN KEY (`PROFILE_ID`) REFERENCES `profile` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla inventasoftdb.users: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`ID`, `FULL_NAME`, `EMAIL`, `USER`, `PASSWORD`, `PROFILE_ID`) VALUES
	(1, 'john edward', 'john@hotmail.com', 'john1234', '12345678', 2),
	(2, 'Antonio Becerra', 'antonio@gmail.com', 'antonio.becerra', 'antonio', 2),
	(3, 'john', 'john@hotmail.com', 'john1234', 'john1234', 1),
	(4, 'John2233 Acevedo r', 'jhedacro2332@hotmail.com', 'john2332.acevedo', 'john2233', 3),
	(5, 'ssssss', 'jhjh@hotm.com', 'ssss3333', '12345678', 2),
	(6, 'hola', 'dasd@h.c', 'hola', 'holahola', 1),
	(7, 'brayan acevedo', 'brayan@hotmail.com', 'brayan1234', 'brayan1234', 1),
	(8, 'edward', 'edward@hot.com', 'edward', '12345678', 1),
	(9, 'Jose pabon', 'Jose@gmail.com', 'jose.pabon', 'jose', 3),
	(10, 'dairon rosas', 'dairon@gmail.com', 'dairon.rosas', 'dairon', 2),
	(11, 'ivan Tapia', 'ivan@hotmail.com', 'ivan1234', '12345678', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
