-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Nov 04, 2013 alle 08:35
-- Versione del server: 5.5.33-MariaDB
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_asp`
--
CREATE DATABASE IF NOT EXISTS `adams_log` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_log`;
-- --------------------------------------------------------

--
-- Struttura della tabella `t_log_application`
--

DROP TABLE IF EXISTS `t_log_application`;
CREATE TABLE IF NOT EXISTS `t_log_application` (
  `timestamp` varchar(19) DEFAULT NULL,
  `ip_server` varchar(15) DEFAULT NULL,
  `hostname_server` varchar(50) DEFAULT NULL,
  `ip_client` varchar(15) DEFAULT NULL,
  `hostname_client` varchar(50) DEFAULT NULL,
  `application_user` varchar(50) DEFAULT NULL,
  `client_user` varchar(50) DEFAULT NULL,
  `application` varchar(50) DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL,
  `object` varchar(50) DEFAULT NULL,
  `parameter` varchar(500) DEFAULT NULL,
  `successful` varchar(50) DEFAULT NULL,
  `return_code` int(5) DEFAULT NULL,
  KEY `ip_client` (`ip_client`),
  KEY `application` (`application`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_log_logger`
--

DROP TABLE IF EXISTS `t_log_logger`;
CREATE TABLE IF NOT EXISTS `t_log_logger` (
  `node_name` varchar(39) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `time` datetime NOT NULL,
  `mesg` text NOT NULL,
  KEY `process_name` (`process_name`),
  KEY `node_name` (`node_name`),
  KEY `process_name_2` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_log_monitor`
--

DROP TABLE IF EXISTS `t_log_monitor`;
CREATE TABLE IF NOT EXISTS `t_log_monitor` (
  `node_name` varchar(39) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `time` datetime NOT NULL,
  `urgency` int(2) DEFAULT '0',
  `mesg` text NOT NULL,
  KEY `process_name` (`process_name`),
  KEY `node_name` (`node_name`),
  KEY `process_name_2` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_proc_status`
--

DROP TABLE IF EXISTS `t_proc_status`;
CREATE TABLE IF NOT EXISTS `t_proc_status` (
  `node_name` varchar(30) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `status` enum('Starting','Checking','Running','Waiting','Sleeping','Failed') NOT NULL,
  `status_message` varchar(128) DEFAULT NULL,
  KEY `node_name` (`node_name`,`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
