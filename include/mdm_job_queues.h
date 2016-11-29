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

#ifndef _MDM_JOB_QUEUES_H
#define _MDM_JOB_QUEUES_H

// #define MAX_DATE_ELAB           30
// #define MAXPATH                 310
// #define NUM_MAX_JOB             20
// #define DELAY_NEW_TRY           30
// #define TIMEOUT_JOB             6
// #define DELAY_POLLING           50
// #define DELAY_LAUNCH            10
// #define NJOB_SEC_USER           3
// #define NJOB_PRI_USER           10
// #define MODPERIOD               0

#define JQ_CONF_USER_MAXLEN	64
#define JQ_CONF_PASWD_MAXLEN	32

typedef struct {
	char user[JQ_CONF_USER_MAXLEN];
	char password[JQ_CONF_PASWD_MAXLEN];
	int job;
	int frequence;
	int id_user;
} JOB_CONFIG;


#endif
