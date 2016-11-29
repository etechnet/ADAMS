-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Ott 22, 2013 alle 12:40
-- Versione del server: 5.5.29-MariaDB-log
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_asp_usermgt`
--
CREATE DATABASE IF NOT EXISTS `adams_asp_usermgt` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_asp_usermgt`;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_class_lib`
--

CREATE TABLE IF NOT EXISTS `t_class_lib` (
  `id_class` int(3) NOT NULL,
  `desc_class` varchar(150) DEFAULT NULL,
  KEY `id_class` (`id_class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_function_lib`
--

CREATE TABLE IF NOT EXISTS `t_function_lib` (
  `id_class` int(3) NOT NULL,
  `id_funtion` int(3) NOT NULL,
  `function_name` varchar(60) DEFAULT NULL,
  `function_desc` varchar(150) DEFAULT NULL,
  `version` varchar(60) DEFAULT NULL,
  `released_data` varchar(60) DEFAULT NULL,
  `author` varchar(60) DEFAULT NULL,
  `vendor` varchar(60) DEFAULT NULL,
  KEY `id_class` (`id_class`),
  KEY `id_funtion` (`id_funtion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_profile_class`
--

CREATE TABLE IF NOT EXISTS `t_profile_class` (
  `profile` varchar(40) NOT NULL,
  `id_class` int(3) DEFAULT NULL,
  `id_funtion` int(3) DEFAULT NULL,
  `desc_profile` varchar(150) DEFAULT NULL,
  KEY `profile` (`profile`),
  KEY `id_class` (`id_class`),
  KEY `id_funtion` (`id_funtion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_role_lib`
--

CREATE TABLE IF NOT EXISTS `t_role_lib` (
  `id_role` int(3) NOT NULL,
  `desc_role` varchar(150) DEFAULT NULL,
  KEY `id_role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `login` varchar(20) NOT NULL,
  `passwd` varchar(20) DEFAULT NULL,
  `data_token` date DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `desc_user` varchar(150) DEFAULT NULL,
  `id_role` int(3) NOT NULL DEFAULT '4',
  `enable_user` tinyint(1) NOT NULL DEFAULT '0',
  KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_user_profile`
--

CREATE TABLE IF NOT EXISTS `t_user_profile` (
  `login` varchar(20) NOT NULL,
  `profile` varchar(40) NOT NULL,
  KEY `login` (`login`),
  KEY `t_user_profile_ifbk_1` (`profile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `t_function_lib`
--
ALTER TABLE `t_function_lib`
  ADD CONSTRAINT `t_function_ibfk_1` FOREIGN KEY (`id_class`) REFERENCES `t_class_lib` (`id_class`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `t_profile_class`
--
ALTER TABLE `t_profile_class`
  ADD CONSTRAINT `t_profile_class_ibfk_1` FOREIGN KEY (`id_class`) REFERENCES `t_class_lib` (`id_class`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_profile_class_ibfk_2` FOREIGN KEY (`id_funtion`) REFERENCES `t_function_lib` (`id_funtion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `t_user_profile`
--
ALTER TABLE `t_user_profile`
  ADD CONSTRAINT `t_user_profile_ifbk_1` FOREIGN KEY (`profile`) REFERENCES `t_profile_class` (`profile`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
