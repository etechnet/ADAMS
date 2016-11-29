/***************************************************************************
                          log_define.h  -  description
                             -------------------
    begin                : 30-03-2007
    copyright            : (C) 2007 by Raffaele Ficcadenti
    email                : raffaele.ficcadenti@e-tech.net
 ***************************************************************************/

#ifndef LOG_DEFINE_STSCONFIGSERVER_NEW_H
#define LOG_DEFINE_STSCONFIGSERVER_NEW_H
	/*
	**  ID MSG LOG
	*/
	
	/*
	**WARNING
	*/
	
	#define LOGMSG_CHANGE_DAY	101
	#define LOGMSG_START_PROC	100
	
	#define LOG_ERR 		0
	#define LOG_WRN 		1
	#define LOG_MSG 		2
	
	#define ATT_HIGH 		2
	#define ATT_MED			1
	#define ATT_LOW			0
	
	#define PRI_HIGH		1
	#define PRI_MED			2
	#define PRI_LOW			0
	
	/* MESSAGGI ERRORE PER STSConfServer NEW */
	#define LOG_IDERR_GENERAL_ERROR			0
	
	/* MESSAGGI INFORMATIVI PER STSConfServer NEW */
	#define LOG_IDMSG_START_OK			50
	
#endif
