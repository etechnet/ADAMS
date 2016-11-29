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

#ifndef _ADAMS_LIMITS_H
#define _ADAMS_LIMITS_H

#define	SHORT_DESC_LEN			30
#define LONG_DESC_LEN			160
#define PIXMAP_FILENAME_LEN		128
#define OPTION_LABEL_LEN		60
#define	MAX_OPTIONS			80
#define	VALUE_RANGE_LEN			20
#define MAX_AGGREGATE_RESTR		20
#define	MAX_EXCEPTIONS			20
#define FORMULA_MAX_LEN			255
#define NEXTLEVEL_RELATIONS		150
#define MAX_RESTR_LEVELS		10
#define MAX_RESTRICTIONS		150
#define MAX_FEP				10
#define MAX_N_VALUE             	105
#define ASCII_REST_LEN			26
#define MAX_RELATION			400
#define VALSHIFTER_MAX			20
#define VALSHIFTER_LEN			8
#define CNT_PLUGINNAME_LEN		256
#define CNT_PATH_LEN			1536
#define CNT_TAG_LEN			20
#define CNT_DESC_LEN			160
#define MAX_INT_COUNTERS		40
#define MAX_DBL_COUNTERS		20
#define CNT_NUM				(MAX_INT_COUNTERS + MAX_DBL_COUNTERS)
#define USR_LOGIN_LEN			32
#define USR_PASSWD_LEN			80
#define MAX_ENABLE_CONFIGS		20
#define MAX_CONFIG_FILENAME		160
#define DR_FIELDSDESCRIPTIONLENGHT	100
#define DR_PLUGINNAME_LEN		256
#define DR_PATH_LEN			1536
#define DR_DATE_LEN			9
#define GLB_PATH_LEN			1536
#define GLB_INFO_LEN			160
#define TE_SUFFIX_LEN			10
#define MAX_KEY_LENGTH			400
#define PLR_PLUGINNAME_LEN		256
#define SCRIPT_VARNAME_LEN		SHORT_DESC_LEN
#define SCRIPT_TEXT_LEN			160
#define SCRIPT_MAX_VAR			20
#define SCRIPT_MAX_TEXT			20
#define JQ_NODO_FEP_SIZE		20
#define JQ_DIR_DEST_SIZE		256
#define JQ_DESC_SIZE			61
#define JQ_USR_STR_SIZE			11
#define MAX_ANALYSIS_REPORTS		15
#define MAX_TE_SCRIPTS			6
#define REPO_LABEL_LEN			41
#define REPO_USER_LEN			81
#define SHORT_PLUGIN_NAME		81
#define HELPER_DESC_LEN			100
#define ERROR_TEXT_LEN			1024
#define JOB_USR_LOGIN_LEN		11
#define JOB_USR_PASSWD_LEN		11
#define ELAB_DATE_STR_LEN		9
#define MAX_DIMENSION			10
#define CORBA_NCEN		 	10
#define MAX_PIVOT_KEYS			MAX_DIMENSION
#define IP_STRING_LEN			16
#define MAX_ALARMS			100
#define MAX_ALARM_GENERATOR		20
#define MAX_THRESHOLD_GENERATOR		10
#define RELATION_FREEFORMAT_ID		-5
#define ALARM_RELATION_INKEY_DIGITS	6
#define THRESHOLD_RELATION_INKEY_DIGITS 6
#define MAX_CONCURRENT_ALARM_REQUESTS	20		// how many threads concurrently running
#define MAX_GROUP_LABEL_LEN		81		/* Lunghezza max label */
#define MAX_GROUP_ICON_URL_LEN		81		/* Lunghezza max url */
#define MAX_VAL_DESCR_LEN		101		/* size descrizioni */
#define MAX_VAL_VALUE_LEN		101		/* size valore */
#define MAX_EVENT_NAME_LEN		81		/* size nom */
#define MAX_ELEMENT_CODE_LEN		21		/* Lunghezza max codice elemento (alfanumerico) */
#define NODE_DESC_LEN			50		/* Lunghezza max descrizione nodi */
#define ALARM_SERVER_POOL_SIZE		10		/* number of alarmserver processes */
#define MAX_USER_JOB_QUEUES		100

#endif
