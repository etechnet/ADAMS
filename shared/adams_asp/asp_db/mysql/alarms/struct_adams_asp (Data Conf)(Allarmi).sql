-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.29-rc-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- Create schema adams_conf
--

CREATE DATABASE IF NOT EXISTS adams_asp;
USE adams_asp;

--
-- Definition of table `tab_alarm_generator`
--

DROP TABLE IF EXISTS `tab_alarm_generator`;
CREATE TABLE `tab_alarm_generator` (
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `SHORT_DESCRIPTION` varchar(60) NOT NULL,
  `LONG_DESCRIPTION` varchar(256),
  `STR_PLUGIN` varchar(30) NOT NULL ,
  `THRESHOLD_MANAGEMENT` char(1) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_ALARM_GENERATOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_generator';

--
-- Definition of table `tab_alarm_generator_thresholds`
--

DROP TABLE IF EXISTS `tab_alarm_generator_thresholds`;
CREATE TABLE `tab_alarm_generator_thresholds` (
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `ID_THRESHOLD_GENERATOR` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_generator_thresholds';

--
-- Definition of table `tab_alarm_relation`
--

DROP TABLE IF EXISTS `tab_alarm_relation`;
CREATE TABLE `tab_alarm_relation` (
  `ID_ALARM_RELATION` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256),
  `ID_CONDITION_SCRIPT` int(6),
  `STR_CONDITION_PLUGIN` varchar(30),
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `COUNTERS_KIT_ID` int(6) NOT NULL,
  `TIME_FRACTION_ELEMENT_ID` int(6) NOT NULL,
  `FAMILY_ID` int(6),
  PRIMARY KEY (`ID_ALARM_RELATION`,`TIPO_DI_CONFIGURAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation';

--
-- Definition of table `tab_alarm_relation_elements`
--

DROP TABLE IF EXISTS `tab_alarm_relation_elements`;
CREATE TABLE `tab_alarm_relation_elements` (
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ELEMENT` int(6) NOT NULL,
  `POSITION_ELEMENT` int(10),
  `ENABLED_DETAIL` int(2)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation_elements';

--
-- Definition of table `tab_alarm_relation_handlers`
--

DROP TABLE IF EXISTS `tab_alarm_relation_handlers`;
CREATE TABLE `tab_alarm_relation_handlers` (
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation_handlers';

--
-- Definition of table `tab_alarm_server_master`
--

DROP TABLE IF EXISTS `tab_alarm_server_master`;
CREATE TABLE `tab_alarm_server_master` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `SERVER_ID` int(6) NOT NULL,
  `DESCRIPTION` varchar(256)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_server_master';

--
-- Definition of table `tab_alarm_server_production`
--

DROP TABLE IF EXISTS `tab_alarm_server_production`;
CREATE TABLE `tab_alarm_server_production` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `SERVER_ID` int(6) NOT NULL,
  `DESCRIPTION` varchar(256)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_server_production';

--
-- Definition of table `tab_alarm_threshold_generator`
--

DROP TABLE IF EXISTS `tab_alarm_threshold_generator`;
CREATE TABLE `tab_alarm_threshold_generator` (
  `ID_THRESHOLD_GENERATOR` int(6) NOT NULL,
  `DESCRIPTION` varchar(256) NOT NULL,
  `TYPE_THRESHOLD` int(10) NOT NULL,
  `ENABLE_HOLYDAY_THRESHOLD` char(1),
  `STR_PLUGIN` varchar(30) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `THRESHOLD_PERSISTENCE` int(3) NOT NULL,
  `HOURS_AGGREGATE` int(2) NOT NULL,
  PRIMARY KEY (`ID_THRESHOLD_GENERATOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_threshold_generator';


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
