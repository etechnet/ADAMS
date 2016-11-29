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

#ifndef USER_H
#define USER_H

#include <adams_limits.h>

//#define USR_LOGIN_LEN		32
//#define USR_PASSWD_LEN		80
//#define MAX_ENABLE_CONFIGS	20
//#define MAX_CONFIG_FILENAME	160

/**Classe base per la definizione dell'oggetto "Utente" della configurazione di ADAMS
  *@short Classe Utenti
  *@author Piergiorgio Betti
  */

class User {
public:
	User();
	~User();

	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		char login[USR_LOGIN_LEN];
		char passwd[USR_PASSWD_LEN];
		bool userAdmin;
		bool configAdmin;
		char enabledConfigurations[MAX_ENABLE_CONFIGS][MAX_CONFIG_FILENAME];
	} DATA;

	DATA data;
	bool Delete;
};

#endif
