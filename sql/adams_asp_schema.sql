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

  
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
