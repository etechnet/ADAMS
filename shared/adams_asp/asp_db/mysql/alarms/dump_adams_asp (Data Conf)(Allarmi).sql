-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.29-rc-community



--
-- Dumping data for table `tab_alarm_generator`
--

/*!40000 ALTER TABLE `tab_alarm_generator` DISABLE KEYS */;
INSERT INTO `tab_alarm_generator` (`ID_ALARM_GENERATOR`,`SHORT_DESCRIPTION`,`LONG_DESCRIPTION`,`STR_PLUGIN`,`THRESHOLD_MANAGEMENT`,`TIPO_DI_CONFIGURAZIONE`) VALUES 
 (1,'ASR0','Allarmi per ASR0','alarm_asr0.so','Y','N.T.M.-Voce-Standard'),
 (21,'NER2002','Alarmi NER2002 (Network efficency ratio)','alarm_ner.so','Y','N.T.M.-Voce-Standard'),
 (41,'MOS_IN','MOS Incoming','alarm_mos_i.so','Y','N.T.M.-Voce-Standard'),
 (42,'MOS_OUT','MOS Outgoing','alarm_mos_o.so','Y','N.T.M.-Voce-Standard'),
 (62,'REINVITE','Allarmi Reinvite','alarm_reinvite.so','N','N.T.M.-Voce-Standard'),
 (82,'ABR','ABR Alarms','alarm_abr.so','Y','N.T.M.-Voce-Standard'),
 (83,'ASR','ASR Alarms','alarm_asr.so','Y','N.T.M.-Voce-Standard'),
 (84,'ALOC','ALOC Alarms (Conversation medium time)','alarm_aloc.so','Y','N.T.M.-Voce-Standard');
/*!40000 ALTER TABLE `tab_alarm_generator` ENABLE KEYS */;


--
-- Dumping data for table `tab_alarm_generator_thresholds`
--

/*!40000 ALTER TABLE `tab_alarm_generator_thresholds` DISABLE KEYS */;
INSERT INTO `tab_alarm_generator_thresholds` (`ID_ALARM_GENERATOR`,`ID_THRESHOLD_GENERATOR`,`TIPO_DI_CONFIGURAZIONE`) VALUES 
 (1,1,'N.T.M.-Voce-Standard'),
 (21,21,'N.T.M.-Voce-Standard'),
 (21,1,'N.T.M.-Voce-Standard'),
 (42,1,'N.T.M.-Voce-Standard'),
 (42,42,'N.T.M.-Voce-Standard'),
 (84,64,'N.T.M.-Voce-Standard'),
 (84,1,'N.T.M.-Voce-Standard'),
 (82,62,'N.T.M.-Voce-Standard'),
 (82,1,'N.T.M.-Voce-Standard'),
 (83,63,'N.T.M.-Voce-Standard'),
 (83,1,'N.T.M.-Voce-Standard'),
 (41,1,'N.T.M.-Voce-Standard'),
 (41,41,'N.T.M.-Voce-Standard');
/*!40000 ALTER TABLE `tab_alarm_generator_thresholds` ENABLE KEYS */;


--
-- Dumping data for table `tab_alarm_relation`
--

/*!40000 ALTER TABLE `tab_alarm_relation` DISABLE KEYS */;
INSERT INTO `tab_alarm_relation` (`ID_ALARM_RELATION`,`DESCRIPTION`,`ID_CONDITION_SCRIPT`,`STR_CONDITION_PLUGIN`,`TIPO_DI_CONFIGURAZIONE`,`COUNTERS_KIT_ID`,`TIME_FRACTION_ELEMENT_ID`,`FAMILY_ID`) VALUES 
 ('169','CC_CTO_STRG::CARRIER_LIKE::ALOCAL::SERV_TIPO::ROUTING_DA',517,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('170','AC_CTO_STRG::ALOCAL::SERV_TIPO::ROUTING_DA',-1,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('171','CAU::AC_CTO_STRG::ALOCAL::SERV_TIPO',516,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('185','ROUTE_CLASS::ROUTING_DA',-1,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('186','CARRIER_LIKE::ALOCAL::SERV_TIPO',-1,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('187','CAU::RID',-1,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('23','CALL_SCENARIO::CAE::CAUN::CC_CTO_STRG::ALOCAL::SERV_TIPO',-1,'alcond_voip.so','N.T.M.-Voce-Standard',105,56,-1),
 ('24','CARRIER_LIKE::CC_CTO_STRG::ROUTE_CLASS::ROUTING_DA',-1,NULL,'N.T.M.-Voce-Standard',85,56,-1),
 ('25','CAU::CC_CTO_STRG::ALOCAL::SERV_TIPO',-1,'alcond_intlinkOV.so','N.T.M.-Voce-Standard',85,56,-1),
 ('45','SWITCH::NULL',-1,NULL,'N.T.M.-Voce-Standard',145,170,-1);
/*!40000 ALTER TABLE `tab_alarm_relation` ENABLE KEYS */;

--
-- Dumping data for table `tab_alarm_relation_elements`
--

/*!40000 ALTER TABLE `tab_alarm_relation_elements` DISABLE KEYS */;
INSERT INTO `tab_alarm_relation_elements` (`ID_ALARM_RELATION`,`TIPO_DI_CONFIGURAZIONE`,`ID_ELEMENT`,`POSITION_ELEMENT`,`ENABLED_DETAIL`) VALUES 
 (24,'N.T.M.-Voce-Standard',392,0,1),
 (24,'N.T.M.-Voce-Standard',146,1,1),
 (24,'N.T.M.-Voce-Standard',393,2,1),
 (24,'N.T.M.-Voce-Standard',729,3,1),
 (23,'N.T.M.-Voce-Standard',756,0,1),
 (23,'N.T.M.-Voce-Standard',155,1,1),
 (25,'N.T.M.-Voce-Standard',156,0,1),
 (25,'N.T.M.-Voce-Standard',146,1,1),
 (23,'N.T.M.-Voce-Standard',885,2,1),
 (23,'N.T.M.-Voce-Standard',146,3,1),
 (23,'N.T.M.-Voce-Standard',135,4,1),
 (23,'N.T.M.-Voce-Standard',133,5,1),
 (25,'N.T.M.-Voce-Standard',135,2,1),
 (25,'N.T.M.-Voce-Standard',133,3,1),
 (187,'N.T.M.-Voce-Standard',156,0,1),
 (187,'N.T.M.-Voce-Standard',395,1,1),
 (171,'N.T.M.-Voce-Standard',156,0,1),
 (171,'N.T.M.-Voce-Standard',126,1,1),
 (171,'N.T.M.-Voce-Standard',135,2,1),
 (171,'N.T.M.-Voce-Standard',133,3,1),
 (45,'N.T.M.-Voce-Standard',949,0,1),
 (45,'N.T.M.-Voce-Standard',165,1,1),
 (169,'N.T.M.-Voce-Standard',146,0,1),
 (169,'N.T.M.-Voce-Standard',392,1,1),
 (169,'N.T.M.-Voce-Standard',135,2,1),
 (169,'N.T.M.-Voce-Standard',133,3,1),
 (169,'N.T.M.-Voce-Standard',729,4,1),
 (170,'N.T.M.-Voce-Standard',126,0,1),
 (170,'N.T.M.-Voce-Standard',135,1,1),
 (170,'N.T.M.-Voce-Standard',133,2,1),
 (170,'N.T.M.-Voce-Standard',729,3,1),
 (186,'N.T.M.-Voce-Standard',392,0,1),
 (186,'N.T.M.-Voce-Standard',135,1,1),
 (186,'N.T.M.-Voce-Standard',133,2,1),
 (185,'N.T.M.-Voce-Standard',393,0,1),
 (185,'N.T.M.-Voce-Standard',729,1,1);
/*!40000 ALTER TABLE `tab_alarm_relation_elements` ENABLE KEYS */;


--
-- Dumping data for table `tab_alarm_relation_handlers`
--

/*!40000 ALTER TABLE `tab_alarm_relation_handlers` DISABLE KEYS */;
INSERT INTO `tab_alarm_relation_handlers` (`ID_ALARM_RELATION`,`ID_ALARM_GENERATOR`,`TIPO_DI_CONFIGURAZIONE`) VALUES 
 (23,42,'N.T.M.-Voce-Standard'),
 (23,41,'N.T.M.-Voce-Standard'),
 (24,1,'N.T.M.-Voce-Standard'),
 (45,62,'N.T.M.-Voce-Standard'),
 (170,83,'N.T.M.-Voce-Standard'),
 (170,84,'N.T.M.-Voce-Standard'),
 (169,82,'N.T.M.-Voce-Standard'),
 (169,84,'N.T.M.-Voce-Standard'),
 (171,83,'N.T.M.-Voce-Standard'),
 (171,84,'N.T.M.-Voce-Standard'),
 (185,82,'N.T.M.-Voce-Standard'),
 (186,82,'N.T.M.-Voce-Standard'),
 (186,84,'N.T.M.-Voce-Standard'),
 (185,84,'N.T.M.-Voce-Standard'),
 (187,84,'N.T.M.-Voce-Standard'),
 (25,84,'N.T.M.-Voce-Standard'),
 (25,83,'N.T.M.-Voce-Standard'),
 (25,1,'N.T.M.-Voce-Standard'),
 (25,21,'N.T.M.-Voce-Standard'),
 (187,82,'N.T.M.-Voce-Standard');
/*!40000 ALTER TABLE `tab_alarm_relation_handlers` ENABLE KEYS */;

--
-- Dumping data for table `tab_alarm_server_master`
--

/*!40000 ALTER TABLE `tab_alarm_server_master` DISABLE KEYS */;
INSERT INTO `tab_alarm_server_master` (`TIPO_DI_CONFIGURAZIONE`,`ID_ALARM_RELATION`,`SERVER_ID`,`DESCRIPTION`) VALUES 
 ('N.T.M.-Voce-Standard',23,1,'Server 1'),
 ('N.T.M.-Voce-Standard',25,1,'Server 1'),
 ('N.T.M.-Voce-Standard',24,0,'Descrizione Server 0');
/*!40000 ALTER TABLE `tab_alarm_server_master` ENABLE KEYS */;


--
-- Dumping data for table `tab_alarm_server_production`
--

/*!40000 ALTER TABLE `tab_alarm_server_production` DISABLE KEYS */;
INSERT INTO `tab_alarm_server_production` (`TIPO_DI_CONFIGURAZIONE`,`ID_ALARM_RELATION`,`SERVER_ID`,`DESCRIPTION`) VALUES 
 ('N.T.M.-Voce-Standard',23,1,'Server 1'),
 ('N.T.M.-Voce-Standard',25,1,'Server 1'),
 ('N.T.M.-Voce-Standard',24,0,'Descrizione Server 0');
/*!40000 ALTER TABLE `tab_alarm_server_production` ENABLE KEYS */;


/*!40000 ALTER TABLE `tab_alarm_threshold_generator` DISABLE KEYS */;
INSERT INTO `tab_alarm_threshold_generator` (`ID_THRESHOLD_GENERATOR`,`DESCRIPTION`,`TYPE_THRESHOLD`,`ENABLE_HOLYDAY_THRESHOLD`,`STR_PLUGIN`,`TIPO_DI_CONFIGURAZIONE`,`THRESHOLD_PERSISTENCE`,`HOURS_AGGREGATE`) VALUES 
 (1,'BIDS',1,'N','threshold_bids_ma.so','N.T.M.-Voce-Standard',15,1),
 (21,'NER2002',0,'N','threshold_ner_ma.so','N.T.M.-Voce-Standard',15,1),
 (41,'MOS_IN',0,'N','threshold_mos_inc_ma.so','N.T.M.-Voce-Standard',15,1),
 (42,'MOS_OUT',0,'N','threshold_mos_out_ma.so','N.T.M.-Voce-Standard',15,1),
 (62,'ABR',0,'N','threshold_abr_ma.so','N.T.M.-Voce-Standard',15,1),
 (63,'ASR',0,'N','threshold_asr_ma.so','N.T.M.-Voce-Standard',15,1),
 (64,'ALOC',0,'N','threshold_aloc_ma.so','N.T.M.-Voce-Standard',15,1);
/*!40000 ALTER TABLE `tab_alarm_threshold_generator` ENABLE KEYS */;




