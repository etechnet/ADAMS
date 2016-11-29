/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/


#include <Qt/qmap.h>

//#define _UTIL_OK 				1
//#define _UTIL_NOK				-1
#define MAX_NODE_THRESHOLD			1000000
#define BNODE_PREORDER				1
#define BNODE_POSTORDER				2
#define BNODE_INORDER				3
#define T_NUMBER				1
#define T_STRING				2
#define T_DATE					3
#define T_FLOAT					4
#define WINDOW_DD_THRESHOLD_HISTORICAL		150
#define WINDOW_DD_THRESHOLD			7
#define INSERT_INTO_T_THR_HISTORY_ALARM 	1
#define INSERT_INTO_T_THR_ALARM			2
#define NUM_INSERT_PARALLEL			20
#define LEN_STR_SELECT				4000
#define DB_NOERROR				-1

#define MAX_OCCURRENCES_HOLIDAY			10
#define MAX_OCCURRENCES_WEEKDAY			10

#define THRSHOLD_NORMAL				0
#define THRSHOLD_HOLIDAY			1
#define THRSHOLD_PREHOLIDAY			2
#define THRSHOLD_EXTRHOLIDAY			3
#define THRSHOLD_WEEKDAY			4


/* DEFINE PER Database*/
#define LEN_KEY_DB                      256
#define LEN_TYPE_CONF                   30

#ifndef _struct_dati_threshold_H
#define _struct_data_threshold_H

#define THRESHOLD_HOLIDAY  1
#define THRESHOLD_WEEKDAY  2

struct nodoTreeTHRESHOLD{
        float           value;
        float           valueMin;
        float           valueMax;
        float           valueIntermediate[WINDOW_DD_THRESHOLD_HISTORICAL];
        int             occurrences;
        int             daylyHour;
        float		coeff_K;
};

struct coeff_corrective{
	float coeff_1;
	float coeff_2;
};

typedef QMap<QString, nodoTreeTHRESHOLD> treeTHRESHOLD;
typedef QMap<QString, nodoTreeTHRESHOLD>::iterator treeTHRESHOLDiterator;

#endif
