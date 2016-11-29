-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Ott 22, 2013 alle 12:38
-- Versione del server: 5.5.29-MariaDB-log
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_asp_usermgt`
--
CREATE DATABASE IF NOT EXISTS `adams_asp_usermgt` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_asp_usermgt`;

--
-- Svuota la tabella prima dell'inserimento `t_class_lib`
--

TRUNCATE TABLE `t_class_lib`;
--
-- Dump dei dati per la tabella `t_class_lib`
--

INSERT INTO `t_class_lib` (`id_class`, `desc_class`) VALUES
(1, 'MDM'),
(2, 'SSM'),
(3, 'ASP');

--
-- Svuota la tabella prima dell'inserimento `t_function_lib`
--

TRUNCATE TABLE `t_function_lib`;
--
-- Dump dei dati per la tabella `t_function_lib`
--

INSERT INTO `t_function_lib` (`id_class`, `id_funtion`, `function_name`, `function_desc`, `version`, `released_data`, `author`, `vendor`) VALUES
(3, 1, 'MDM Configurator', 'Configuratore per MDM', '1.0', '2013-10-15', 'Ficcadenti Raffaele', 'e-tech s.r.l.'),
(3, 2, 'USER Configurator', 'Configuratore Utenti', '1.0', '2013-10-15', 'Ficcadenti Raffaele', 'e-tech s.r.l.');

--
-- Svuota la tabella prima dell'inserimento `t_profile_class`
--

TRUNCATE TABLE `t_profile_class`;
--
-- Dump dei dati per la tabella `t_profile_class`
--

INSERT INTO `t_profile_class` (`profile`, `id_class`, `id_funtion`, `desc_profile`) VALUES
('PF_ASP_TOTAL', 3, 1, 'Profilo totale per ASP'),
('PF_ASP_TOTAL', 3, 2, 'Profilo totale per ASP');

--
-- Svuota la tabella prima dell'inserimento `t_role_lib`
--

TRUNCATE TABLE `t_role_lib`;
--
-- Dump dei dati per la tabella `t_role_lib`
--

INSERT INTO `t_role_lib` (`id_role`, `desc_role`) VALUES
(1, 'Normal User'),
(2, 'Admin User'),
(3, 'Super User'),
(777, 'E-TECH User');

--
-- Svuota la tabella prima dell'inserimento `t_user`
--

TRUNCATE TABLE `t_user`;
--
-- Dump dei dati per la tabella `t_user`
--

INSERT INTO `t_user` (`login`, `passwd`, `data_token`, `name`, `desc_user`, `id_role`, `enable_user`) VALUES
('raffo', 'raffox', '2013-10-15', 'Raffaele Ficcadenti', 'Developer', 4, 1);

--
-- Svuota la tabella prima dell'inserimento `t_user_profile`
--

TRUNCATE TABLE `t_user_profile`;
--
-- Dump dei dati per la tabella `t_user_profile`
--

INSERT INTO `t_user_profile` (`login`, `profile`) VALUES
('raffo', 'PF_ASP_TOTAL');

SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
