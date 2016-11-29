-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Nov 14, 2013 alle 08:21
-- Versione del server: 5.5.29-MariaDB-log
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_etss`
--
CREATE DATABASE IF NOT EXISTS `adams_etss` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_etss`;


--
-- Svuota la tabella prima dell'inserimento `t_alarm`
--

TRUNCATE TABLE `t_alarm`;
--
-- Svuota la tabella prima dell'inserimento `t_alarm_counter`
--

TRUNCATE TABLE `t_alarm_counter`;
--
-- Svuota la tabella prima dell'inserimento `t_alarm_kpi`
--

TRUNCATE TABLE `t_alarm_kpi`;
--
-- Svuota la tabella prima dell'inserimento `t_alarm_kpi_sog`
--

TRUNCATE TABLE `t_alarm_kpi_sog`;
--
-- Svuota la tabella prima dell'inserimento `t_festivi`
--

TRUNCATE TABLE `t_festivi`;
--
-- Svuota la tabella prima dell'inserimento `t_policy`
--

TRUNCATE TABLE `t_policy`;
--
-- Svuota la tabella prima dell'inserimento `t_thr_alarm`
--

TRUNCATE TABLE `t_thr_alarm`;
--
-- Svuota la tabella prima dell'inserimento `t_thr_history_alarm`
--

TRUNCATE TABLE `t_thr_history_alarm`;
--
-- Svuota la tabella prima dell'inserimento `t_thr_override`
--

TRUNCATE TABLE `t_thr_override`;
--
-- Svuota la tabella prima dell'inserimento `t_thr_param`
--

TRUNCATE TABLE `t_thr_param`;
--
-- Svuota la tabella prima dell'inserimento `t_traffic_relation`
--

TRUNCATE TABLE `t_traffic_relation`;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
