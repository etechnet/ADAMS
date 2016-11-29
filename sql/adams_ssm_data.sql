-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Nov 04, 2013 alle 08:34
-- Versione del server: 5.5.33-MariaDB
-- Versione PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adams_asp`
--
CREATE DATABASE IF NOT EXISTS `adams_ssm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_ssm`;


--
-- Svuota la tabella prima dell'inserimento `t_iors`
--

TRUNCATE TABLE `t_iors`;
--
-- Dump dei dati per la tabella `t_iors`
--

INSERT INTO `t_iors` (`node_id`, `process_name`, `ior`) VALUES
(1, 'mdm_server_factory', 'IOR:010000003000000049444c3a6e65742f65746563682f4d444d2f6d646d5f7365727665725f666163746f72795f7365727665723a312e300001000000000000009c0000000101020004000000424550005c1b00005300000014010f004e5550000000270000000001000000526f6f74504f41006d646d5f7365727665725f666163746f72795f4245500000000000010000006d646d5f7365727665725f666163746f72795f7365727665720002000000000000000800000001000000004f41540100000018000000010000000100010001000000010001050901010000000000'),
(1, 'ssm_logger', 'IOR:010000002800000049444c3a6e65742f65746563682f53534d2f73736d5f6c6f676765725f7365727665723a312e3000010000000000000060000000010102000400000042455000581b00001700000014010f00525354de9c76526d180700000000000100000000020000000000000008000000012c5518004f41540100000018000000012c55180100010001000000010001050901010000000000'),
(1, 'ssm_scheduler', 'IOR:010000002b00000049444c3a6e65742f65746563682f53534d2f73736d5f7363686564756c65725f7365727665723a312e300000010000000000000060000000010102010400000042455000591b0e0e1700000014010f00525354de9c76523c4d09000000000001000000020200000000000000080000000126d7ac004f41540100000018000000012dd7ac0100010001000000010001050901010000000000'),
(1, 'asp_server', 'IOR:010000002000000049444c3a6e65742f65746563682f4153502f6173705f656e7472793a312e30000100000000000000600000000101020004000000424550005b1b00001700000014010f00525354e39c76526bb5000000000000010000000002000000000000000800000001000000004f41540100000018000000010000000100010001000000010001050901010000000000'),
(1, 'ssm_deployer', 'IOR:010000002a00000049444c3a6e65742f65746563682f53534d2f73736d5f6465706c6f7965725f7365727665723a312e300000000100000000000000600000000101020004000000424550005d1b00001700000014010f00525354e39c765213f9000000000000010000000002000000000000000800000001000000004f41540100000018000000010000000100010001000000010001050901010000000000'),
(1, 'ssm_garbage', 'IOR:010000002900000049444c3a6e65742f65746563682f53534d2f73736d5f676172626167655f7365727665723a312e30000000000100000000000000600000000101020004000000424550005a1b00001700000014010f00525354e39c765249f8000000000000010000000002000000000000000800000001000000004f41540100000018000000010000000100010001000000010001050901010000000000');

--
-- Svuota la tabella prima dell'inserimento `t_node`
--

TRUNCATE TABLE `t_node`;
--
-- Dump dei dati per la tabella `t_node`
--

INSERT INTO `t_node` (`node_id`, `name`, `description`, `process_group_id`, `active`, `location`, `port_range_min`, `port_range_max`) VALUES
(1, 'BEP', 'ADAMS BackEnd Processor', 23, 1, 'Adams Development', 7000, 7100),
(2, 'linux-0cm2.site', 'PC Raffo', 23, 1, 'Casa mia', 1024, 65535);
--
-- Svuota la tabella prima dell'inserimento `t_node_process`
--

TRUNCATE TABLE `t_node_process`;
--
-- Dump dei dati per la tabella `t_node_process`
--

INSERT INTO `t_node_process` (`node_id`, `process_name`, `process_type`, `process_id`, `active_flag`, `schedule_ignore`, `wake_time`, `start_cmd`, `last_restart`, `num_restart`, `pid`, `log_level`, `log_output`, `log_pipe_cmd`, `assigned_port`) VALUES
(1, 'asp_server', 0, 6, 1, 0, '00:00:00', 'asp_server', '2000-01-01 00:00:00', 0, 0, 0, 'File', 'none', 7002),
(1, 'mdm_server', 0, 9, 1, 1, '00:00:00', 'mdm_server', '2013-10-25 11:02:03', 1, 0, 3, 'File', 'none', 7006),
(1, 'mdm_server_factory', 0, 7, 1, 0, '00:00:00', 'mdm_server_factory', '2013-11-03 19:58:42', 1, 23673, 3, 'File', 'none', 7004),
(1, 'ssm_deployer', 0, 8, 1, 0, '05:26:32', 'ssm_deployer', '2013-11-03 19:58:42', 1, 23674, 3, 'Table', 'none', 7005),
(1, 'ssm_garbage', 1, 5, 1, 0, '05:26:32', 'ssm_garbage', '2013-11-03 19:58:42', 1, 23675, 3, 'File', 'none', 7002),
(1, 'ssm_logger', 0, 1, 1, 1, '05:26:32', 'ssm_logger', '2013-09-17 05:11:14', 2, 12345, 3, 'File', 'none', 7000),
(1, 'ssm_scheduler', 0, 4, 1, 1, '05:26:32', 'ssm_scheduler', '2013-09-17 05:11:14', 2, 12345, 3, 'File', 'none', 7001);

--
-- Svuota la tabella prima dell'inserimento `t_process`
--

TRUNCATE TABLE `t_process`;
--
-- Dump dei dati per la tabella `t_process`
--

INSERT INTO `t_process` (`process_name`, `process_type`, `process_id`, `schedule_ignore`, `wake_time`, `start_cmd`, `log_output`) VALUES
('ssm_logger', 0, 1, 1, '05:26:32', 'ssm_logger', 'File'),
('ssm_scheduler', 0, 4, 1, '05:26:32', 'ssm_scheduler', 'File'),
('ssm_garbage', 1, 5, 0, '05:26:32', 'ssm_garbage', 'File'),
('asp_server', 0, 6, 0, '00:00:00', 'asp_server', 'Table'),
('mdm_server_factory', 0, 7, 0, '00:00:00', 'mdm_server_factory', 'Table'),
('ssm_deployer', 0, 8, 0, '00:00:00', 'ssm_deployer', 'Table'),
('mdm_server', 0, 9, 1, '00:00:00', 'mdm_server', 'File');

--
-- Svuota la tabella prima dell'inserimento `t_process_groups`
--

TRUNCATE TABLE `t_process_groups`;
--
-- Dump dei dati per la tabella `t_process_groups`
--

INSERT INTO `t_process_groups` (`process_group_id`, `group_name`) VALUES
(23, 'BEP');

--
-- Svuota la tabella prima dell'inserimento `t_process_group_members`
--

TRUNCATE TABLE `t_process_group_members`;
--
-- Dump dei dati per la tabella `t_process_group_members`
--

INSERT INTO `t_process_group_members` (`process_group_id`, `process_id`) VALUES
(23, 1),
(23, 4),
(23, 5),
(23, 6);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
