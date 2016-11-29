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

#ifndef _CNFGLOBALS_H
#define _CNFGLOBALS_H

#ifndef ADAMS_TRU64
#include <limits.h>
#else
#include <sys/limits.h>
#endif
#include <stdio.h>
#include <string.h>
#include <Qt/qstringlist.h>
#include <Qt/qvector.h>

#include <adams_limits.h>

// This define classes and data structs to access config file headers

#define HDR_TAG_LEN	20
#define HDR_DSCR_LEN	160
#define HDR_NUM_LEN	14	// YYYYMMDDHHMMSS
#define HDR_BOOL_LEN	1

// Other defines

#define c_qstrncpy(d, s, n)	{ memset(d,0,n); qstrncpy(d,s,n); }

typedef QVector<QString> QStringVector;

typedef struct {
	QStringList values;
	QStringList labels;
} TagList;


#endif
