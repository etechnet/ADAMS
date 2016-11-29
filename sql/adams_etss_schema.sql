-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Ott 22, 2013 alle 12:52
-- Versione del server: 5.5.29-MariaDB-log
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
CREATE DATABASE IF NOT EXISTS `adams_etss` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_etss`;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_alarm`
--

CREATE TABLE IF NOT EXISTS `t_alarm` (
  `LINK_ALLARME` int(12) NOT NULL,
  `ID_RELATION` int(6) NOT NULL,
  `ID_KPI` int(6) NOT NULL,
  `RELATION_KEY_VALUE` varchar(256) NOT NULL,
  `RELATION_KEY_DESC` varchar(2000) NOT NULL,
  `DATA_ALLARME` date NOT NULL,
  `DAYLY_HOUR` int(2) NOT NULL,
  `COEFF` float(5,2) NOT NULL DEFAULT 1,
  `POL_ID` char(1) NOT NULL,
  `INTERVALLO` int(2) NOT NULL,
  `PERSISTENZA` int(2) NOT NULL,
  `STATO` int(1) NOT NULL,
  PRIMARY KEY (`LINK_ALLARME`,`DATA_ALLARME`,`ID_RELATION`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_alarm';

-- --------------------------------------------------------

--
-- Struttura della tabella `t_alarm_counter`
--

CREATE TABLE IF NOT EXISTS `t_alarm_counter` (
  `LINK_ALLARME` int(12) NOT NULL,
  `DATA_ALLARME` date NOT NULL,
  `ID_KPI` int(6) NOT NULL,
  `DESCRIZIONE` varchar(256) NOT NULL,
  `COUNTER_VALUE` float(16,4) NOT NULL,
  PRIMARY KEY (`LINK_ALLARME`,`DATA_ALLARME`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_alarm_counter';

-- --------------------------------------------------------

--
-- Struttura della tabella `t_alarm_kpi`
--

CREATE TABLE IF NOT EXISTS `t_alarm_kpi` (
  `LINK_ALLARME` int(12) NOT NULL,
  `DATA_ALLARME` date NOT NULL,
  `ID_KPI` int(6) NOT NULL,
  `DESCRIZIONE` varchar(256) NOT NULL,
  `ALARM_VALUE` float(16,4) NOT NULL,
  PRIMARY KEY (`LINK_ALLARME`,`DATA_ALLARME`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_alarm_kpi';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_alarm_kpi_sog`
--

CREATE TABLE IF NOT EXISTS `t_alarm_kpi_sog` (
  `LINK_ALLARME` int(12) NOT NULL,
  `DATA_ALLARME` date NOT NULL,
  `ID_KPI` int(6) NOT NULL,
  `DESCRIZIONE` varchar(256) NOT NULL,
  `SOG_VALUE` float(16,4) NOT NULL,
  PRIMARY KEY (`LINK_ALLARME`,`DATA_ALLARME`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_alarm_kpi_sog';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_thr_history_alarm`
--

CREATE TABLE IF NOT EXISTS `t_thr_history_alarm` (
  `ID_RELATION` int(6) NOT NULL,
  `DATA_SOGLIA` date NOT NULL,
  `DAYLY_HOUR` int(2) NOT NULL,
  `RELATION_KEY` varchar(256) NOT NULL,
  `ID_THRESHOLD` int(6) NOT NULL,
  `VALUE` float(16,4) NOT NULL,
  PRIMARY KEY (`ID_RELATION`,`DATA_SOGLIA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_thr_history_alarm';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_thr_alarm`
--

CREATE TABLE IF NOT EXISTS `t_thr_alarm` (
  `ID_RELATION` int(6) NOT NULL,
  `DATA_SOGLIA` date NOT NULL,
  `DAYLY_HOUR` int(2) NOT NULL,
  `RELATION_KEY` varchar(256) NOT NULL,
  `ID_THRESHOLD` int(6) NOT NULL,
  `TIPOSOG` int(2) NOT NULL,
  `VALUE` float(16,4) NOT NULL,
  PRIMARY KEY (`ID_RELATION`,`DATA_SOGLIA`,`RELATION_KEY`,`DAYLY_HOUR`,`ID_THRESHOLD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_thr_alarm';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_thr_param`
--

CREATE TABLE IF NOT EXISTS `t_thr_param` (
  `ID_RELATION` int(6) NOT NULL,
  `DATA_SOGLIA` date NOT NULL,
  `DAYLY_HOUR` int(2) NOT NULL,
  `RELATION_KEY` varchar(256) NOT NULL,
  `ID_THRESHOLD` int(6) NOT NULL,
  `VALUE` float(16,4) NOT NULL,
  `POLICY_ID` int(2) NOT NULL,
  `OVERRIDE` char(1) NOT NULL,
  `K_FACTOR` float(5,2) NOT NULL,
  `DAY_TYPE` char(1) NOT NULL,
  `TIPOSOG` int(2) NOT NULL,
  `VIEW_TYPE` char(1),
  `SLA_REFERENCE` varchar(10),
  `LAST_UPDATE` date NOT NULL,
  PRIMARY KEY (`ID_RELATION`,`DATA_SOGLIA`,`DAYLY_HOUR`,`RELATION_KEY`,`ID_THRESHOLD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_thr_param';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_thr_override`
--

CREATE TABLE IF NOT EXISTS `t_thr_override` (
  `ID_RELATION` int(6) NOT NULL,
  `RELATION_KEY` varchar(256) NOT NULL,
  `ID_THRESHOLD` int(6) NOT NULL,
  `DAYLY_HOUR` int(2) NOT NULL,
  `DATA_TYPE` char(1) NOT NULL,
  `SLA_REFERENCE` varchar(10),
  `LAST_UPDATE` date NOT NULL,
  `OVERRIDE_COEFFICENTE` float(4,2) NOT NULL,
  `OVERRIDE_SOGLIA` float(16,4) NOT NULL,
  `OVERRIDE_SOGLIA_FESTIVO` float(16,4) NOT NULL,
  PRIMARY KEY (`ID_RELATION`,`RELATION_KEY`,`ID_THRESHOLD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_thr_override';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_policy`
--

CREATE TABLE IF NOT EXISTS `t_policy` (
  `TRAFF_ID` int(4) NOT NULL,
  `POL_ID` char(1) NOT NULL,
  `INTERVALLO` int(2) NOT NULL,
  `PERSISTENZA` int(2),
  `COEFF` float(5,2) NOT NULL DEFAULT 1,
  `ID_KPI` int(6),
  PRIMARY KEY (`TRAFF_ID`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_policy';

-- --------------------------------------------------------


--
-- Struttura della tabella `t_traffic_relation`
--

CREATE TABLE IF NOT EXISTS `t_traffic_relation` (
  `TRAFF_ID` int(4) NOT NULL,
  `DESCR` varchar(256) NOT NULL,
  `DESC_COMP` varchar(256),
  `TIPO_DI_CONFIGURAZIONE` varchar(30),
  `ID_KPI` int(6),
  PRIMARY KEY (`TRAFF_ID`,`ID_KPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_traffic_relation';

-- --------------------------------------------------------

--
-- Struttura della tabella `t_festivi`
--

CREATE TABLE IF NOT EXISTS `t_festivi` (
  `GG` date NOT NULL,
  `TIPO_GG` char(1) NOT NULL,
  `DESCR` varchar(200),
  PRIMARY KEY (`GG`,`TIPO_GG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='t_festivi';

-- --------------------------------------------------------


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;