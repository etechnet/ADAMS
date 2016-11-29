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
-- Definition of table `tab_list_exception`
--

DROP TABLE IF EXISTS `tab_list_exception`;
CREATE TABLE `tab_list_exception` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `IDEXCEPTION` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`IDEXCEPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_exception';


--
-- Definition of table `tab_list_script`
--

DROP TABLE IF EXISTS `tab_list_script`;
CREATE TABLE `tab_list_script` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `SCRIPT` int(10),
  `SCRIPT_TYPE` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`SCRIPT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_script';


--
-- Definition of table `tab_type_counters`
--

DROP TABLE IF EXISTS `tab_type_counters`;
CREATE TABLE `tab_type_counters` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDCOUNTER` int(10),
  `TRIGGERFIELD` int(10),
  `TRIGGERVALUE` int(10),
  `TRIGGERCOUNTER` int(10),
  `COUNTERINDEX` int(10),
  `COUNTERTYPE` int(10),
  `PERCENTOF` int(10),
  `TAG` varchar(20),
  `DESCRIPTION` varchar(160),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDCOUNTER`,`TAG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_type_counters';

--
-- Definition of table `tab_report`
--

DROP TABLE IF EXISTS `tab_report`;
CREATE TABLE `tab_report` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30),
  `IDREPORTSCHEMA` int(10),
  `IDOBJECT` int(10) NOT NULL,
  `TYPE` int(10) NOT NULL,
  `SUB_TYPE` int(10) NOT NULL,
  `TAG` varchar(30),
  `HTML_VSIZE` int(10),
  `HTML_HSIZE` int(10),
  `HTML_VALIGN` int(10),
  `HTML_HALIGN` int(10),
  `HTML_WRAP` int(10),
  `HTML_FOREGROUND` int(10),
  `HTML_FOREGROUND_R` int(10),
  `HTML_FOREGROUND_G` int(10),
  `HTML_FOREGROUND_B` int(10),
  `HTML_BACKGROUND` int(10),
  `HTML_BACKGROUND_R` int(10),
  `HTML_BACKGROUND_G` int(10),
  `HTML_BACKGROUND_B` int(10),
  `HTML_STYLE` int(10),
  `HTML_FONTSIZE` int(10),
  `HTML_BOLD` int(10),
  `HTML_ITALIC` int(10),
  `HTML_UNDERLINE` int(10),
  `OBJECT_TAG` varchar(30),
  `OBJECT_HASBORDER` int(10),
  `OBJECT_SIMPLEBODY` int(10),
  `OBJECT_EXCELCSV` int(10),
  `OBJECT_IDANALISYS` int(10),
  `OBJECT_USEPLUGIN` int(10),
  `OBJECT_PLUGINNAME` varchar(256),
  `OBJECT_DEFAULT_FOREGROUND_R` int(10),
  `OBJECT_DEFAULT_FOREGROUND_G` int(10),
  `OBJECT_DEFAULT_FOREGROUND_B` int(10),
  `OBJECT_DEFAULT_BACKGROUND_R` int(10),
  `OBJECT_DEFAULT_BACKGROUND_G` int(10),
  `OBJECT_DEFAULT_BACKGROUND_B` int(10),
  `HEADER_USERLABEL` varchar(41),
  `HEADER_VALUE` int(10),
  `HEADER_USERVALUE` varchar(81),
  `HEADER_ISURL` int(10),
  `HEADER_LINE` int(10),
  `BODY_SOURCE` int(10),
  `BODY_IDKEY` int(10),
  `BODY_IDCOUNTER` int(10),
  `BODY_TAGCOUNTER` varchar(20),
  `BODY_IDSCRIPT` int(10),
  `BODY_PLUGIN_TAG` varchar(30),
  `BODY_PLUGIN_NAME` varchar(256),
  `BODY_USERVALUE` varchar(81),
  `BODY_ISURL` int(10),
  `BODY_TOTALIZER` int(10),
  `BODY_LINE` int(10),
  `BODY_MAXDECIMALDIGIT` int(10),
  `TOTALIZER_TRIGGER` int(10),
  `TOTALIZER_LABEL` varchar(41),
  `TOTALIZER_REDRAWHEADER` int(10),
  `TOTALIZER_BORDERAROUND` int(10),
  `TOTALIZER_LINE` int(10),
  `TOTALIZER_USESCRIPT` int(10),
  `TOTALIZER_IDSCRIPT` int(10),
  `FOOTER_SOURCE` int(10),
  `FOOTER_LABEL` varchar(41),
  `FOOTER_USERVALUE` varchar(81),
  `FOOTER_ISURL` int(10),
  `FOOTER_LINE` int(10),
  `POSIZIONE` int(10),
  `BODY_REPEATKEY` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDREPORTSCHEMA`,`IDOBJECT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_report';

--
-- Definition of table `tab_config`
--

DROP TABLE IF EXISTS `tab_config`;
CREATE TABLE `tab_config` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `POSIZIONE_CAMPO_CDR` int(10) NOT NULL,
  `TIPO_CAMPO` int(10) NOT NULL,
  `SIZE_CAMPO_CDR` int(10) NOT NULL,
  `FLAG_ARRAY` char(1) NOT NULL,
  `NUMERO_ELEM_ARRAY` int(10) NOT NULL,
  `LABEL_CDR_NORMALIZZ` varchar(30) NOT NULL,
  `TIPO_OGGETTO_GUI` int(10),
  `ICONA` varchar(30),
  `RANGE_VAL` varchar(15),
  `VALORE_DEFAULT` int(10),
  `RAPPRESENTAZ_ESADECIMALE` char(1),
  `PRIORITA` int(10),
  `OPERATORI_RESTRIZIONE` int(10),
  `LUNG_ELEMENTO_CHIAVE` int(10),
  `TAG` varchar(30),
  `DESCRIPTION` varchar(160),
  `PLUGIN` varchar(256),
  `PLUGIN_PATH` varchar(1536),
  `FLAG_ABILITAZIONE_KEY` char(1),
  `FLAG_CONTATORE` char(1),
  `FLAG_RESTRIZIONE` char(1),
  `FLAG_CONBINAZ_ANALISI` char(1),
  `DEFAULT_RESTRICTION` char(1),
  `ALWAYS_SHOW_REPORT` char(1),
  `LINK_TE` int(10),
  `SE_INDICE` char(1),
  `VALORE_AGGREGAZIONE` int(10),
  `LENGTH_VALORE_START` int(10),
  `SUFF_VALORE_INDICE` varchar(10),
  `DATA_INIZIO_INDICE` date,
  `DATA_FINE_INDICE` date,
  `VALORE_AGGREGAZIONE_DEF` int(10),
  `FFENABLED` char(1),
  `PLUGIN_GUI` varchar(256),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_config';

--
-- Definition of table `tab_gui_events`
--

DROP TABLE IF EXISTS `tab_gui_events`;
CREATE TABLE `tab_gui_events` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDEXCEPTION` int(10) NOT NULL,
  `TAG` varchar(30),
  `DESCRIPTION` varchar(160),
  `IDTRIGGERRESTRICTION` int(10),
  `TRIGGEREDSTATUS` int(10),
  `TRIGGEREDVALUE` varchar(26),
  `ACTION` int(10),
  `TARGETVALUE` varchar(26),
  `IDAGGREGATEEXCEPTION` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDEXCEPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_gui_events';

--
-- Definition of table `tab_analysis_type`
--

DROP TABLE IF EXISTS `tab_analysis_type`;
CREATE TABLE `tab_analysis_type` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDANALISI` int(10),
  `NOMEANALISI` varchar(160),
  `IDCOUNTERKIT` int(10),
  `FLAGSORT` char(1) NOT NULL,
  `FLAGDATA` char(1) NOT NULL,
  `FLAGCENTRALE` char(1) NOT NULL,
  `FLAGCUMULAZIONE` char(1) NOT NULL,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDANALISI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_analysis_type';

--
-- Definition of table `tab_aggregate_restrictions`
--

DROP TABLE IF EXISTS `tab_aggregate_restrictions`;
CREATE TABLE `tab_aggregate_restrictions` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `AGGREG_RESTRICTION` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`AGGREG_RESTRICTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_aggregate_restrictions';


--
-- Definition of table `tab_subst_values`
--

DROP TABLE IF EXISTS `tab_subst_values`;
CREATE TABLE `tab_subst_values` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `VALORI_SOSTITUZIONE` varchar(24),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`VALORI_SOSTITUZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_subst_values';


--
-- Definition of table `tab_value_type`
--

DROP TABLE IF EXISTS `tab_value_type`;
CREATE TABLE `tab_value_type` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `COD_VALORE` int(10),
  `DESC_VALORE` varchar(60),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`COD_VALORE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_value_type';

--
-- Definition of table `tab_help`
--

DROP TABLE IF EXISTS `tab_help`;
CREATE TABLE `tab_help` (
  `ID` int(10) NOT NULL,
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDHELP` int(10),
  `HELPVALUE` int(10) NOT NULL,
  `DESCRIPTION` varchar(160),
  PRIMARY KEY (`IDHELP`,`HELPVALUE`,`DESCRIPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_help';


--
-- Definition of table `tab_scripts`
--

DROP TABLE IF EXISTS `tab_scripts`;
CREATE TABLE `tab_scripts` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10),
  `TAG` varchar(30),
  `NUMVARIABLE1` int(10),
  `NUMVARIABLE2` int(10),
  `NUMSCRIPTTEXT` int(10),
  `RESULTVARNAME` varchar(30),
  `RESULTTYPE` int(10),
  `FLAG_VALIDATE` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`TAG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_scripts';


--
-- Definition of table `tab_script_var1`
--

DROP TABLE IF EXISTS `tab_script_var1`;
CREATE TABLE `tab_script_var1` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10),
  `VRBNAME` varchar(30),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`VRBNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_var1';

--
-- Definition of table `tab_script_var2`
--

DROP TABLE IF EXISTS `tab_script_var2`;
CREATE TABLE `tab_script_var2` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10),
  `VRBNAME` varchar(30),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`VRBNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_var2';

--
-- Definition of table `TAB_SCRIPT_TEXT`
--

DROP TABLE IF EXISTS `tab_script_text`;
CREATE TABLE `tab_script_text` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDSCRIPT` int(10),
  `VRBTEXT` varchar(400)
  -- PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDSCRIPT`,`VRBTEXT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_script_text';

--
-- Definition of table `tab_info_config`
--

DROP TABLE IF EXISTS `tab_info_config`;
CREATE TABLE `tab_info_config` (
  `CDR_USE_PLUGIN` int(10),
  `CDR_PLUGIN_NAME` varchar(256),
  `CDR_REC_LEN` int(10),
  `CDR_FLAG_USE_PATH` int(10),
  `CDR_SPECIFY_PL_PATH` varchar(1536),
  `TIPO_DI_CONFIGURAZIONE` varchar(30),
  `GLB_DEFAULT_PATH_PL` varchar(1536),
  `AUTHOR` varchar(160),
  `LAST_MODIFICATION_TIME` varchar(40),
  `BOUNDARY` int(10),
  `LOCK_TABLE` varchar(40),
  `FILE_NAME_CONF` varchar(160),
  `CONF_DEFAULT` char(1),
  `ID_INDICE` int(4),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_info_config';

--
-- Definition of table `l_index_lib`
--

DROP TABLE IF EXISTS `l_index_lib`;
CREATE TABLE `l_index_lib` (
  `ID_INDICE` int(4) NOT NULL,
  `DESC_INDICE` varchar(40),
  `NOME_FILE` varchar(50),
  PRIMARY KEY (`ID_INDICE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='l_index_lib';

--
-- Definition of table `tab_list_relation`
--

DROP TABLE IF EXISTS `tab_list_relation`;
CREATE TABLE `tab_list_relation` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256),
  `DIREZIONE` int(10),
  `HEXOUTPUT` int(10),
  `NETWORK` int(10),
  `GHOST` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_relation';

--
-- Definition of table `tab_list_analysis`
--

DROP TABLE IF EXISTS `tab_list_analysis`;
CREATE TABLE `tab_list_analysis` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256),
  `IDANALISI` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`IDANALISI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_analysis';

--
-- Definition of table `tab_list_restriction`
--

DROP TABLE IF EXISTS `tab_list_restriction`;
CREATE TABLE `tab_list_restriction` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256),
  `ELEMENTI_RESTRIZIONE` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`ELEMENTI_RESTRIZIONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_restriction';

--
-- Definition of table `tab_list_restriction_ob`
--

DROP TABLE IF EXISTS `tab_list_restriction_ob`;
CREATE TABLE `tab_list_restriction_ob` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `POSIZIONE_ELEMENTO` int(10) NOT NULL,
  `RELATION_NAME` varchar(256),
  `ELEMENTI_RESTRIZIONE_OBBL` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`POSIZIONE_ELEMENTO`,`RELATION_NAME`,`ELEMENTI_RESTRIZIONE_OBBL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_restriction_ob';

--
-- Definition of table `tab_counters_kit`
--

DROP TABLE IF EXISTS `tab_counters_kit`;
CREATE TABLE `tab_counters_kit` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDCOUNTER` int(10),
  `TAG` varchar(30),
  `USEPLUGIN` char(1) NOT NULL,
  `PLUGINNAME` varchar(256),
  `USEPATH` char(1) NOT NULL,
  `PATHNAME` varchar(256),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDCOUNTER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_counters_kit';


--
-- Definition of table `tab_list_report`
--

DROP TABLE IF EXISTS `tab_list_report`;
CREATE TABLE `tab_list_report` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30) NOT NULL,
  `IDANALISI` int(10),
  `IDREPORT` int(10),
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`IDANALISI`,`IDREPORT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tab_list_report';


--
-- Definition of table `l_plugin_library`
--

DROP TABLE IF EXISTS `l_plugin_library`;
CREATE TABLE `l_plugin_library` (
  `TIPO_DI_CONFIGURAZIONE` varchar(30),
  `NOME_PLUGIN` varchar(30),
  `DESCRIZIONE` longtext,
  PRIMARY KEY (`TIPO_DI_CONFIGURAZIONE`,`NOME_PLUGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='l_plugin_library';


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
