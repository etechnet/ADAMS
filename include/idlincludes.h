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


#ifndef _IDLINCLUDES_H
#define _IDLINCLUDES_H

#define MAX_DESC_MODULE_LEN				150

#define ID_MOD_ADAMS					0
#define ID_MOD_MDM					1
#define ID_MOD_IMAGE					2
#define ID_MOD_MONITOR					3
#define ID_MOD_COMMON_MONITOR				4
#define ID_MOD_COMMON_ADMIN				5
#define ID_MOD_DTM					6
#define ID_MOD_DISPATCHER				7
#define ID_MOD_ADAMS_CONF				8
#define ID_MOD_MAG					9
#define ID_MOD_ANAGRAFICHE				10

#define ID_GET_SAVE_TPL					-3

#define PLUGIN_NOT_VALIDATE				0
#define PLUGIN_VALIDATE					1
#define PLUGIN_VALIDATE_AND_CONFIGURATED		2
#define PLUGIN_WRONG_VERSION				3

#define ALL_PLUGIN					-2
#define CONFIGURATED_PLUGIN				-1
#define USER_LIST_CONFIGURATED_PLUGINS			-3

#define MODULE_USER_GOD					1
#define ADMINISTRATOR					1
#define SUPERUSER					0

/**********************************************  DEFINE x MONITOR *********************************************************/

#define MAX_DISPATCHER_NAME_LEN				100
#define MAX_DISPATCHER_DESCRIPTION_LEN			150
#define MAX_PLUGIN_NAME_LEN				60
#define MAX_PLUGIN_TAG_LEN				60
#define MAX_LOGICAL_FUNCTION_LEN			50
#define MAX_FUNCTION_DESCRIPTION_LEN			150
#define MAX_VERSION_LEN					60
#define MAX_RELEASE_DATA_LEN				60
#define MAX_VENDOR_LEN					50
#define MAX_AUTHOR_LEN					50
#define MAX_SOURCE_DATA_LEN				60
#define MAX_SOURCE_LOCATION_LEN 			60
#define MAX_DESCRITTORE_LEN				150
#define MAX_IMPUT_NAME_LEN				15
#define MAX_ALTERNATIVE_NAME_LEN			30
#define MAX_SUB_FUNCTION_NAME_LEN			50
#define MAX_DESC_LEN					150
#define MAX_POL_RANGE_LEN				40
#define MAX_OUTPUT_BASE_UNIT_LEN			50
#define MAX_MIN_VALUE_LEN				30
#define MAX_STR_LEN					50
#define MAX_COLOR_LEN					10
#define MAX_DESC_SESSION				150
#define MAX_VALUE_LEN					50
#define MAX_HELP_DESC_LEN				150
#define MAX_HELP_VALUE_LEN				50
#define MAX_FONT_NAME_LEN				100

#define ESTENZIONE_DESCRITTORE				".txt"

#define STS_SERVER_TIMEOUT				((unsigned long)(60L*60L*1000L))

#define NORMAL_USER					0
#define IS_PROFILE					1

/**********************************************  DEFINE x IMAGE *********************************************************/

#define MAX_IMG_LOGIN_LEN			11	/* Lunghezza MAX caratteri NOME UTENTE */
#define MAX_IMG_PASSWORD_LEN			11	/* Lunghezza MAX caratteri PASSWORD */
#define MAX_IMG_NUM_LAYOUT			30	/* Numero  MAX di LAYOUT */
#define MAX_IMG_DESC_LAYOUT_LEN			150	/* Numero caratteri descrzione LAYOUT */
#define MAX_IMG_DESC_MODULE_LEN			150	/* Numero caratteri descrzione applicativi esterni */
#define MAX_IMG_DESC_USER_LEN			150	/* Numero caratteri descrzione Utente */
#define MAX_IMG_VIEW				20	/* Numero MAX di View consentite */
#define MAX_IMG_NAME_MODULE			20
#define LEN_NOME_UTENTE				100
#define MAX_IMG_EDIT_OTHER			20
#define MAX_IMG_EXTERNAL_MODULE			10
#define MAX_IMG_DESC_TYPE_ALARM			50
#define MAX_IMG_LAYOUT_NAME_LEN			50
#define MAX_IMG_CONFIG_SERVER_NAME_LEN		150
#define MAX_IMG_ALARM_SERVER_NAME_LEN		150
#define MAX_IMG_DETAIL_SERVER_NAME_LEN		150
#define MAX_IMG_GENERAL_LEN			150
#define MAX_IMG_MAPNAME_LEN			50
#define MAX_IMG_PARAM_DESC			50
#define LEN_ICON_URL				150
#define MAX_IMG_LAYOUT_NAME			50
#define MAX_IMG_ALARM_MAX			30
#define MAX_IMG_SHORT_DESC			50
#define MAX_IMG_LONG_DESC			150
#define RGB_TRIPLE				10
#define MAX_IMG_SPOT_VALUE			50
#define MAX_IMG_NAME_CEN			50
#define MAX_IMG_DESC_CEN			150
#define	SUPER_USER_MAG				-5


#define ID_LAYOUT_IP_SCENIC_1			5
#define ID_LAYOUT_IP_SCENIC_2			6

#define TYPE_MAP				0
#define TYPE_TAB				1

/*************************************************** DEFINE PER LOG APPLICATIVO MAG ***************************************/
#define MAX_CLICK_UTE				50
#define MAX_ID_OPERAZIONE			2
#define MAX_TESTO				2000
#define MAX_ESITO_OPERAZIONE			15


/*************************************************** DEFINE PER LOG APPLICATIVO ***************************************/
#define MAX_TIME_STAMP				20
#define MAX_IP_GENERATORE			16
#define MAX_HOSTNAME_GENERATORE			50
#define MAX_IP_CLIENT				16
#define MAX_HOSTNAME_CLIENT			50
#define MAX_UTENZA_APPLICATIVA			50
#define MAX_UTENZA_CLIENT			50
#define MAX_APPLICATIVO_CLIENT			50
#define MAX_AZIONE				50
#define MAX_OGGETTO				50
#define MAX_PARAMETRI				500
#define MAX_ESITO				50

/************************************************ DEFINE PER NUOVO STSCONFIGSERVERNEW **********************************/
#define MAX_DESC_ROLE				150
#define MAX_DESC_CLASS				150
#define MAX_NAME_FUNCTION			60
#define MAX_DESC_FUNCTION			150
#define MAX_VERSION				60
#define MAX_RELEASED_DATA			60
#define MAX_AUTHOR				50
#define MAX_VENDOR				50
#define MAX_PROFILE				40
#define MAX_NAME_PROFILE			40
#define	MAX_DESC_PROFILE			300
#define MAX_LOGIN_LEN				20
#define MAX_PASSWORD_LEN			20
#define MAX_NOMEUTENTE_LEN			100
#define MAX_DESC_USER_LEN			150

#define MAX_NAME_NODE				9
#define MAX_DESC_NODE				13
#define MAX_TIPO_NODE				3

#define MAX_CONFIG_ADAMS_LEN			40

#define OP_INSERT				1
#define OP_MODIFY				2
#define OP_DELETE				3

/************************************************ DEFINE PER EMAIL MAG **********************************/
#define MAX_UTENTE				20
#define MAX_DATA_EMAIL 				20
#define MAX_DATA_DELETE_EMAIL 			20
#define MAX_EMAIL_TO 				256
#define MAX_EMAIL_CC 				256
#define MAX_EMAIL_FROM 				256
#define MAX_EMAIL_SUBJECT 			1000
#define MAX_EMAIL_ATTACH 			256
#define MAX_EMAIL_MSG 				3000

#endif
