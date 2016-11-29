-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Ott 22, 2013 alle 12:57
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
CREATE DATABASE IF NOT EXISTS `adams_asp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_asp`;

-- --------------------------------------------------------

--
-- Struttura della tabella `l_index_lib`
--

CREATE TABLE IF NOT EXISTS `l_index_lib` (
  `ID_INDICE` int(4) NOT NULL,
  `DESC_INDICE` varchar(40) DEFAULT NULL,
  `NOME_FILE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_INDICE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='l_index_lib';

-- --------------------------------------------------------

--
-- Struttura della tabella `l_plugin_library`
--

CREATE TABLE IF NOT EXISTS `l_plugin_library` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL DEFAULT '',
  `NOME_PLUGIN` varchar(30) NOT NULL DEFAULT '',
  `DESCRIZIONE` longtext,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`NOME_PLUGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='l_plugin_library';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_aggregate_restrictions`
--

CREATE TABLE IF NOT EXISTS `tab_aggregate_restrictions` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `AGGREG_RESTRICTION` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`AGGREG_RESTRICTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_aggregate_restrictions';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_generator`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_generator` (
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `SHORT_DESCRIPTION` varchar(60) NOT NULL,
  `LONG_DESCRIPTION` varchar(256) DEFAULT NULL,
  `STR_PLUGIN` varchar(30) NOT NULL,
  `THRESHOLD_MANAGEMENT` char(1) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_ALARM_GENERATOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_generator';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_generator_thresholds`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_generator_thresholds` (
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `ID_THRESHOLD_GENERATOR` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_generator_thresholds';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_relation`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_relation` (
  `ID_ALARM_RELATION` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `ID_CONDITION_SCRIPT` int(6) DEFAULT NULL,
  `STR_CONDITION_PLUGIN` varchar(30) DEFAULT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `COUNTERS_KIT_ID` int(6) NOT NULL,
  `TIME_FRACTION_ELEMENT_ID` int(6) NOT NULL,
  `FAMILY_ID` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID_ALARM_RELATION`,`TIPO_DI_CONFIGURAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_relation_elements`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_relation_elements` (
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ELEMENT` int(6) NOT NULL,
  `POSITION_ELEMENT` int(10) DEFAULT NULL,
  `ENABLED_DETAIL` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation_elements';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_relation_handlers`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_relation_handlers` (
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `ID_ALARM_GENERATOR` int(6) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_relation_handlers';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_server_master`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_server_master` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `SERVER_ID` int(6) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_server_master';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_server_production`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_server_production` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `ID_ALARM_RELATION` int(6) NOT NULL,
  `SERVER_ID` int(6) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_server_production';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_alarm_threshold_generator`
--

CREATE TABLE IF NOT EXISTS `tab_alarm_threshold_generator` (
  `ID_THRESHOLD_GENERATOR` int(6) NOT NULL,
  `DESCRIPTION` varchar(256) NOT NULL,
  `TYPE_THRESHOLD` int(10) NOT NULL,
  `ENABLE_HOLYDAY_THRESHOLD` char(1) DEFAULT NULL,
  `STR_PLUGIN` varchar(30) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `THRESHOLD_PERSISTENCE` int(3) NOT NULL,
  `HOURS_AGGREGATE` int(2) NOT NULL,
  PRIMARY KEY (`ID_THRESHOLD_GENERATOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_alarm_threshold_generator';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_analysis_type`
--

CREATE TABLE IF NOT EXISTS `tab_analysis_type` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDANALISI` int(10) NOT NULL DEFAULT '0',
  `NOMEANALISI` varchar(160) DEFAULT NULL,
  `IDCOUNTERKIT` int(10) DEFAULT NULL,
  `FLAGSORT` char(1) NOT NULL,
  `FLAGDATA` char(1) NOT NULL,
  `FLAGCENTRALE` char(1) NOT NULL,
  `FLAGCUMULAZIONE` char(1) NOT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDANALISI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_analysis_type';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_config`
--

CREATE TABLE IF NOT EXISTS `tab_config` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `POSIZIONE_CAMPO_DR` int(10) NOT NULL,
  `TIPO_CAMPO` int(10) NOT NULL,
  `SIZE_CAMPO_DR` int(10) NOT NULL,
  `FLAG_ARRAY` char(1) NOT NULL,
  `NUMERO_ELEM_ARRAY` int(10) NOT NULL,
  `LABEL_DR_NORMALIZZ` varchar(30) NOT NULL,
  `TIPO_OGGETTO_GUI` int(10) DEFAULT NULL,
  `ICONA` varchar(30) DEFAULT NULL,
  `RANGE_VAL` varchar(15) DEFAULT NULL,
  `VALORE_DEFAULT` int(10) DEFAULT NULL,
  `RAPPRESENTAZ_ESADECIMALE` char(1) DEFAULT NULL,
  `PRIORITA` int(10) DEFAULT NULL,
  `OPERATORI_RESTRIZIONE` int(10) DEFAULT NULL,
  `LUNG_ELEMENTO_CHIAVE` int(10) DEFAULT NULL,
  `TAG` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(160) DEFAULT NULL,
  `PLUGIN` varchar(256) DEFAULT NULL,
  `PLUGIN_PATH` varchar(1536) DEFAULT NULL,
  `FLAG_ABILITAZIONE_KEY` char(1) DEFAULT NULL,
  `FLAG_CONTATORE` char(1) DEFAULT NULL,
  `FLAG_RESTRIZIONE` char(1) DEFAULT NULL,
  `FLAG_CONBINAZ_ANALISI` char(1) DEFAULT NULL,
  `DEFAULT_RESTRICTION` char(1) DEFAULT NULL,
  `ALWAYS_SHOW_REPORT` char(1) DEFAULT NULL,
  `LINK_TE` int(10) DEFAULT NULL,
  `SE_INDICE` char(1) DEFAULT NULL,
  `VALORE_AGGREGAZIONE` int(10) DEFAULT NULL,
  `LENGTH_VALORE_START` int(10) DEFAULT NULL,
  `SUFF_VALORE_INDICE` varchar(10) DEFAULT NULL,
  `DATA_INIZIO_INDICE` date DEFAULT NULL,
  `DATA_FINE_INDICE` date DEFAULT NULL,
  `VALORE_AGGREGAZIONE_DEF` int(10) DEFAULT NULL,
  `FFENABLED` char(1) DEFAULT NULL,
  `PLUGIN_GUI` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_config';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_counters_kit`
--

CREATE TABLE IF NOT EXISTS `tab_counters_kit` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDCOUNTER` int(10) NOT NULL DEFAULT '0',
  `TAG` varchar(30) DEFAULT NULL,
  `USEPLUGIN` char(1) NOT NULL,
  `PLUGINNAME` varchar(256) DEFAULT NULL,
  `USEPATH` char(1) NOT NULL,
  `PATHNAME` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDCOUNTER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_counters_kit';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_function_lib`
--

CREATE TABLE IF NOT EXISTS `tab_function_lib` (
  `ID_CLASS` int(3) NOT NULL DEFAULT '0',
  `ID_FUNCTION` int(5) NOT NULL,
  `FUNCTION_NAME` varchar(60) DEFAULT NULL,
  `FUNCTION_DESC` varchar(150) DEFAULT NULL,
  `VERSION` varchar(60) DEFAULT NULL,
  `RELEASED_DATA` varchar(60) DEFAULT NULL,
  `AUTHOR` varchar(50) DEFAULT NULL,
  `VENDOR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_CLASS`,`ID_FUNCTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_function_lib';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_gui_events`
--

CREATE TABLE IF NOT EXISTS `tab_gui_events` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDEXCEPTION` int(10) NOT NULL,
  `TAG` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(160) DEFAULT NULL,
  `IDTRIGGERRESTRICTION` int(10) DEFAULT NULL,
  `TRIGGEREDSTATUS` int(10) DEFAULT NULL,
  `TRIGGEREDVALUE` varchar(26) DEFAULT NULL,
  `ACTION` int(10) DEFAULT NULL,
  `TARGETVALUE` varchar(26) DEFAULT NULL,
  `IDAGGREGATEEXCEPTION` int(10) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDEXCEPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_gui_events';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_help`
--

CREATE TABLE IF NOT EXISTS `tab_help` (
  `ID` int(10) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDHELP` int(10) NOT NULL DEFAULT '0',
  `HELPVALUE` int(10) NOT NULL,
  `DESCRIPTION` varchar(160) NOT NULL DEFAULT '',
  PRIMARY KEY (`IDHELP`,`HELPVALUE`,`DESCRIPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_help';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_info_config`
--

CREATE TABLE IF NOT EXISTS `tab_info_config` (
  `DR_USE_PLUGIN` int(10) DEFAULT NULL,
  `DR_PLUGIN_NAME` varchar(256) DEFAULT NULL,
  `DR_REC_LEN` int(10) DEFAULT NULL,
  `DR_FLAG_USE_PATH` int(10) DEFAULT NULL,
  `DR_SPECIFY_PL_PATH` varchar(1536) DEFAULT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL DEFAULT '',
  `GLB_DEFAULT_PATH_PL` varchar(1536) DEFAULT NULL,
  `AUTHOR` varchar(160) DEFAULT NULL,
  `LAST_MODIFICATION_TIME` varchar(40) DEFAULT NULL,
  `BOUNDARY` int(10) DEFAULT NULL,
  `LOCK_TABLE` varchar(40) DEFAULT NULL,
  `FILE_NAME_CONF` varchar(160) DEFAULT NULL,
  `CONF_DEFAULT` char(1) DEFAULT NULL,
  `ID_INDICE` int(4) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_info_config';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_inputnode`
--

CREATE TABLE IF NOT EXISTS `tab_inputnode` (
  `IND_NODE` int(2) DEFAULT NULL,
  `FLAG_ATT` int(1) DEFAULT NULL,
  `PFX_NODE` varchar(3) DEFAULT NULL,
  `SUF_NODE` varchar(3) NOT NULL,
  `NODE` varchar(8) NOT NULL DEFAULT '',
  `NAME_NODE` varchar(12) NOT NULL,
  `TYPE_NODE` varchar(2) DEFAULT NULL,
  `LATITUDE_O` char(1) DEFAULT NULL,
  `LATITUDE_G` float(6,2) DEFAULT NULL,
  `LATITUDE_P` float(6,2) DEFAULT NULL,
  `LATITUDE_S` float(6,2) DEFAULT NULL,
  `LONGITUDE_O` char(1) DEFAULT NULL,
  `LONGITUDE_G` float(6,2) DEFAULT NULL,
  `LONGITUDE_P` float(6,2) DEFAULT NULL,
  `LONGITUDE_S` float(6,2) DEFAULT NULL,
  PRIMARY KEY (`NODE`,`SUF_NODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_inputnode';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_analysis`
--

CREATE TABLE IF NOT EXISTS `tab_list_analysis` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256) NOT NULL DEFAULT '',
  `IDANALISI` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`IDANALISI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_analysis';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_exception`
--

CREATE TABLE IF NOT EXISTS `tab_list_exception` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `IDEXCEPTION` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`IDEXCEPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_exception';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_relation`
--

CREATE TABLE IF NOT EXISTS `tab_list_relation` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256) NOT NULL DEFAULT '',
  `DIREZIONE` int(10) DEFAULT NULL,
  `HEXOUTPUT` int(10) DEFAULT NULL,
  `NETWORK` int(10) DEFAULT NULL,
  `GHOST` int(10) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_relation';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_report`
--

CREATE TABLE IF NOT EXISTS `tab_list_report` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDANALISI` int(10) NOT NULL DEFAULT '0',
  `IDREPORT` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDANALISI`,`IDREPORT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_report';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_restriction`
--

CREATE TABLE IF NOT EXISTS `tab_list_restriction` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256) NOT NULL DEFAULT '',
  `ELEMENTI_RESTRIZIONE` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`ELEMENTI_RESTRIZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_restriction';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_restriction_OB`
--

CREATE TABLE IF NOT EXISTS `tab_list_restriction_OB` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256) NOT NULL DEFAULT '',
  `ELEMENTI_RESTRIZIONE_OBBL` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`ELEMENTI_RESTRIZIONE_OBBL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_restriction_OB';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_restriction_ob`
--

CREATE TABLE IF NOT EXISTS `tab_list_restriction_ob` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256) NOT NULL DEFAULT '',
  `ELEMENTI_RESTRIZIONE_OBBL` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`ELEMENTI_RESTRIZIONE_OBBL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_restriction_ob';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_list_script`
--

CREATE TABLE IF NOT EXISTS `tab_list_script` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `SCRIPT` int(10) NOT NULL DEFAULT '0',
  `SCRIPT_TYPE` int(10) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`SCRIPT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_script';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_report`
--

CREATE TABLE IF NOT EXISTS `tab_report` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL DEFAULT '',
  `IDREPORTSCHEMA` int(10) NOT NULL DEFAULT '0',
  `IDOBJECT` int(10) NOT NULL,
  `TYPE` int(10) NOT NULL,
  `SUB_TYPE` int(10) NOT NULL,
  `TAG` varchar(30) DEFAULT NULL,
  `HTML_VSIZE` int(10) DEFAULT NULL,
  `HTML_HSIZE` int(10) DEFAULT NULL,
  `HTML_VALIGN` int(10) DEFAULT NULL,
  `HTML_HALIGN` int(10) DEFAULT NULL,
  `HTML_WRAP` int(10) DEFAULT NULL,
  `HTML_FOREGROUND` int(10) DEFAULT NULL,
  `HTML_FOREGROUND_R` int(10) DEFAULT NULL,
  `HTML_FOREGROUND_G` int(10) DEFAULT NULL,
  `HTML_FOREGROUND_B` int(10) DEFAULT NULL,
  `HTML_BACKGROUND` int(10) DEFAULT NULL,
  `HTML_BACKGROUND_R` int(10) DEFAULT NULL,
  `HTML_BACKGROUND_G` int(10) DEFAULT NULL,
  `HTML_BACKGROUND_B` int(10) DEFAULT NULL,
  `HTML_STYLE` int(10) DEFAULT NULL,
  `HTML_FONTSIZE` int(10) DEFAULT NULL,
  `HTML_BOLD` int(10) DEFAULT NULL,
  `HTML_ITALIC` int(10) DEFAULT NULL,
  `HTML_UNDERLINE` int(10) DEFAULT NULL,
  `OBJECT_TAG` varchar(30) DEFAULT NULL,
  `OBJECT_HASBORDER` int(10) DEFAULT NULL,
  `OBJECT_SIMPLEBODY` int(10) DEFAULT NULL,
  `OBJECT_EXCELCSV` int(10) DEFAULT NULL,
  `OBJECT_IDANALISYS` int(10) DEFAULT NULL,
  `OBJECT_USEPLUGIN` int(10) DEFAULT NULL,
  `OBJECT_PLUGINNAME` varchar(256) DEFAULT NULL,
  `OBJECT_DEFAULT_FOREGROUND_R` int(10) DEFAULT NULL,
  `OBJECT_DEFAULT_FOREGROUND_G` int(10) DEFAULT NULL,
  `OBJECT_DEFAULT_FOREGROUND_B` int(10) DEFAULT NULL,
  `OBJECT_DEFAULT_BACKGROUND_R` int(10) DEFAULT NULL,
  `OBJECT_DEFAULT_BACKGROUND_G` int(10) DEFAULT NULL,
  `OBJECT_DEFAULT_BACKGROUND_B` int(10) DEFAULT NULL,
  `HEADER_USERLABEL` varchar(41) DEFAULT NULL,
  `HEADER_VALUE` int(10) DEFAULT NULL,
  `HEADER_USERVALUE` varchar(81) DEFAULT NULL,
  `HEADER_ISURL` int(10) DEFAULT NULL,
  `HEADER_LINE` int(10) DEFAULT NULL,
  `BODY_SOURCE` int(10) DEFAULT NULL,
  `BODY_IDKEY` int(10) DEFAULT NULL,
  `BODY_IDCOUNTER` int(10) DEFAULT NULL,
  `BODY_TAGCOUNTER` varchar(20) DEFAULT NULL,
  `BODY_IDSCRIPT` int(10) DEFAULT NULL,
  `BODY_PLUGIN_TAG` varchar(30) DEFAULT NULL,
  `BODY_PLUGIN_NAME` varchar(256) DEFAULT NULL,
  `BODY_USERVALUE` varchar(81) DEFAULT NULL,
  `BODY_ISURL` int(10) DEFAULT NULL,
  `BODY_TOTALIZER` int(10) DEFAULT NULL,
  `BODY_LINE` int(10) DEFAULT NULL,
  `BODY_MAXDECIMALDIGIT` int(10) DEFAULT NULL,
  `TOTALIZER_TRIGGER` int(10) DEFAULT NULL,
  `TOTALIZER_LABEL` varchar(41) DEFAULT NULL,
  `TOTALIZER_REDRAWHEADER` int(10) DEFAULT NULL,
  `TOTALIZER_BORDERAROUND` int(10) DEFAULT NULL,
  `TOTALIZER_LINE` int(10) DEFAULT NULL,
  `TOTALIZER_USESCRIPT` int(10) DEFAULT NULL,
  `TOTALIZER_IDSCRIPT` int(10) DEFAULT NULL,
  `FOOTER_SOURCE` int(10) DEFAULT NULL,
  `FOOTER_LABEL` varchar(41) DEFAULT NULL,
  `FOOTER_USERVALUE` varchar(81) DEFAULT NULL,
  `FOOTER_ISURL` int(10) DEFAULT NULL,
  `FOOTER_LINE` int(10) DEFAULT NULL,
  `POSIZIONE` int(10) DEFAULT NULL,
  `BODY_REPEATKEY` int(10) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDREPORTSCHEMA`,`IDOBJECT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_report';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_script_text`
--

CREATE TABLE IF NOT EXISTS `tab_script_text` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10) DEFAULT NULL,
  `VRBTEXT` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_text';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_script_var1`
--

CREATE TABLE IF NOT EXISTS `tab_script_var1` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10) NOT NULL DEFAULT '0',
  `VRBNAME` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`VRBNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_var1';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_script_var2`
--

CREATE TABLE IF NOT EXISTS `tab_script_var2` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10) NOT NULL DEFAULT '0',
  `VRBNAME` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`VRBNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_var2';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_scripts`
--

CREATE TABLE IF NOT EXISTS `tab_scripts` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10) NOT NULL DEFAULT '0',
  `TAG` varchar(30) NOT NULL DEFAULT '',
  `NUMVARIABLE1` int(10) DEFAULT NULL,
  `NUMVARIABLE2` int(10) DEFAULT NULL,
  `NUMSCRIPTTEXT` int(10) DEFAULT NULL,
  `RESULTVARNAME` varchar(30) DEFAULT NULL,
  `RESULTTYPE` int(10) DEFAULT NULL,
  `FLAG_VALIDATE` int(10) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`TAG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_scripts';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_subst_values`
--

CREATE TABLE IF NOT EXISTS `tab_subst_values` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `VALORI_SOSTITUZIONE` varchar(24) NOT NULL DEFAULT '',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`VALORI_SOSTITUZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_subst_values';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_type_counters`
--

CREATE TABLE IF NOT EXISTS `tab_type_counters` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDCOUNTER` int(10) NOT NULL DEFAULT '0',
  `TRIGGERFIELD` int(10) DEFAULT NULL,
  `TRIGGERVALUE` int(10) DEFAULT NULL,
  `TRIGGERCOUNTER` int(10) DEFAULT NULL,
  `COUNTERINDEX` int(10) DEFAULT NULL,
  `COUNTERTYPE` int(10) DEFAULT NULL,
  `PERCENTOF` int(10) DEFAULT NULL,
  `TAG` varchar(20) NOT NULL DEFAULT '',
  `DESCRIPTION` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDCOUNTER`,`TAG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_type_counters';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_value_type`
--

CREATE TABLE IF NOT EXISTS `tab_value_type` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `COD_VALORE` int(10) NOT NULL DEFAULT '0',
  `DESC_VALORE` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`COD_VALORE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_value_type';

-- --------------------------------------------------------

--
-- Struttura della tabella `tab_value_type_SOST`
--

CREATE TABLE IF NOT EXISTS `tab_value_type_SOST` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `VALORI_SOSTITUZIONE` varchar(24) NOT NULL DEFAULT '',
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`VALORI_SOSTITUZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_value_type_SOST';
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
  ADD CONSTRAINT `t_profile_class_ibfk_2` FOREIGN KEY (`id_funtion`) REFERENCES `t_function_lib` (`id_funtion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_profile_class_ibfk_1` FOREIGN KEY (`id_class`) REFERENCES `t_class_lib` (`id_class`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `t_user_profile`
--
ALTER TABLE `t_user_profile`
  ADD CONSTRAINT `t_user_profile_ifbk_1` FOREIGN KEY (`profile`) REFERENCES `t_profile_class` (`profile`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Database: `adams_log`
--
CREATE DATABASE IF NOT EXISTS `adams_log` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_log`;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_log_application`
--

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

CREATE TABLE IF NOT EXISTS `t_log_logger` (
  `process_id` int(6) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `node_id` int(4) NOT NULL,
  `time` datetime NOT NULL,
  `urgency` tinyint(1) NOT NULL DEFAULT '0',
  `mesg` text NOT NULL,
  KEY `process_id` (`process_id`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_log_monitor`
--

CREATE TABLE IF NOT EXISTS `t_log_monitor` (
  `process_id` int(6) NOT NULL,
  `process_name` varchar(128) NOT NULL,
  `node_id` int(4) NOT NULL,
  `time` datetime NOT NULL,
  `urgency` tinyint(1) NOT NULL DEFAULT '0',
  `mesg` text NOT NULL,
  KEY `process_id` (`process_id`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

--
-- Database: `adams_ssm`
--
CREATE DATABASE IF NOT EXISTS `adams_ssm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `adams_ssm`;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_iors`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_node_process`
--

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
  PRIMARY KEY (`process_id`,`node_id`),
  KEY `process_id` (`process_id`),
  KEY `node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process_group_members`
--

CREATE TABLE IF NOT EXISTS `t_process_group_members` (
  `process_group_id` int(3) NOT NULL,
  `process_id` int(6) NOT NULL,
  KEY `group_id` (`process_group_id`,`process_id`),
  KEY `process_id` (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `t_process_groups`
--

CREATE TABLE IF NOT EXISTS `t_process_groups` (
  `process_group_id` int(3) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) NOT NULL,
  PRIMARY KEY (`process_group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;


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
