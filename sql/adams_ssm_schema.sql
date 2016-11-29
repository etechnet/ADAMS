-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Nov 04, 2013 alle 08:33
-- Versione del server: 5.5.33-MariaDB
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_ssm`
--
CREATE DATABASE IF NOT EXISTS `adams_ssm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_ssm`;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_iors`
--

DROP TABLE IF EXISTS `t_iors`;
CREATE TABLE IF NOT EXISTS `t_iors` (
  `node_id` int(4) DEFAULT NULL,
  `process_name` varchar(128) DEFAULT NULL,
  `ior` text NOT NULL,
  UNIQUE KEY `name_id` (`node_id`,`process_name`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_node`
--

DROP TABLE IF EXISTS `t_node`;
CREATE TABLE IF NOT EXISTS `t_node` (
  `node_id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(128) NOT NULL,
  `process_group_id` int(3) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `location` text NOT NULL,
  `port_range_min` int(5) NOT NULL DEFAULT '1024',
  `port_range_max` int(5) NOT NULL DEFAULT '65535',
  PRIMARY KEY (`node_id`),
  UNIQUE KEY `name` (`name`),
  KEY `process_group_id` (`process_group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_node_process`
--

DROP TABLE IF EXISTS `t_node_process`;
CREATE TABLE IF NOT EXISTS `t_node_process` (
  `node_id` int(4) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `process_type` int(1) NOT NULL DEFAULT '0',
  `process_id` int(6) NOT NULL DEFAULT '0',
  `active_flag` int(1) NOT NULL DEFAULT '1',
  `schedule_ignore` int(1) NOT NULL DEFAULT '0',
  `wake_time` time NOT NULL DEFAULT '00:00:00',
  `start_cmd` varchar(512) NOT NULL,
  `last_restart` datetime NOT NULL DEFAULT '2000-01-01 00:00:00',
  `num_restart` int(4) NOT NULL DEFAULT '0',
  `pid` int(8) NOT NULL DEFAULT '0',
  `log_level` int(1) NOT NULL DEFAULT '0',
  `log_output` enum('Table','File','Pipe') NOT NULL DEFAULT 'File',
  `log_pipe_cmd` varchar(512) NOT NULL DEFAULT 'none',
  `assigned_port` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`process_name`),
  KEY `process_id` (`process_id`),
  KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process`
--

DROP TABLE IF EXISTS `t_process`;
CREATE TABLE IF NOT EXISTS `t_process` (
  `process_name` varchar(128) NOT NULL,
  `process_type` int(1) NOT NULL DEFAULT '0',
  `process_id` int(6) NOT NULL AUTO_INCREMENT,
  `schedule_ignore` int(1) NOT NULL DEFAULT '0',
  `wake_time` time NOT NULL DEFAULT '00:00:00',
  `start_cmd` varchar(512) NOT NULL DEFAULT 'none',
  `log_output` enum('Table','File','Pipe','') NOT NULL DEFAULT 'File',
  PRIMARY KEY (`process_id`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process_groups`
--

DROP TABLE IF EXISTS `t_process_groups`;
CREATE TABLE IF NOT EXISTS `t_process_groups` (
  `process_group_id` int(3) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) NOT NULL,
  PRIMARY KEY (`process_group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process_group_members`
--

DROP TABLE IF EXISTS `t_process_group_members`;
CREATE TABLE IF NOT EXISTS `t_process_group_members` (
  `process_group_id` int(3) NOT NULL,
  `process_id` int(6) NOT NULL,
  KEY `group_id` (`process_group_id`,`process_id`),
  KEY `process_id` (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `t_node`
--
ALTER TABLE `t_node`
  ADD CONSTRAINT `t_node_ibfk_2` FOREIGN KEY (`process_group_id`) REFERENCES `t_process_groups` (`process_group_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `t_process_group_members`
--
ALTER TABLE `t_process_group_members`
  ADD CONSTRAINT `t_process_group_members_ibfk_1` FOREIGN KEY (`process_group_id`) REFERENCES `t_process_groups` (`process_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_process_group_members_ibfk_2` FOREIGN KEY (`process_id`) REFERENCES `t_process` (`process_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
