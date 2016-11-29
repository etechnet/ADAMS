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

#ifndef PLUGINBASE_H
#define PLUGINBASE_H

#include <dlfcn.h>
#include <stdlib.h>
#include <adams_plugin_register.h>
#include <configuretypedefs.h>
#include <queryparams.h>

/** Template per l'implementazione dei plugin. Vengono definiti in modalit� virtual i metodi per l'attivazione
  * delle funzionalit� del plugin.
  * All'interno della classe che implementa il codice della classe derivata plugin occorre definire un method
  * statico @ref pluginActivate che verr� richiamato per effettuare l'istanza del plugin e la registrazione dei metodi
  * da parte della @ref PluginBase::registerPlugin(). Ovviamente tale codice verr� definito nell'istanza reale (ossia
  * nelle classi derivate dalla PluginImpl) poich� tale classe � una pure virtual polimorfica.
  * Analogo discorso deve essere fatto per gli argomenti forniti in ingresso al method pluginWorker(): questi argomenti
  * sono definiti nell'istanza reale della classe cos� da definire un API comune fra le varie istanze del plugin e
  * gli entry point da cui questi vengono richiamati.
  *@short Tenplate per l'implementazione dei plugin.
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class PluginImpl
{
public:
	/** Questo method viene utilizzato per ottenere la struttura identificativa del plugin */
	virtual const PluginInfo & getPluginInfo() = 0;
	/** Questo method verifica la congruit� del tipo di plugin rispetto a qunto richiesto dal chiamante */
	virtual bool verifyType ( ADAMSPluginType ) = 0;
	/** In questo method vengono racchiuse le funzionalit� attive del plugin */
	virtual bool pluginWorker ( void * ) = 0;
	/** Questo method viene registrato per fornire la configurazione utente al plugin */
	virtual void pluginSetupConfig ( void *, void * ) = 0;
	/** Metodo utilizzato one-shot per consentire operazioni di inizializzazione del plugin non cicliche */
	virtual bool startup ( void * ) = 0;
	/** Metodo utilizzato one-shot per consentire operazioni di chiusura del plugin */
	virtual bool shutdown ( void * ) = 0;
};

/**Classe base per l'implementazione del meccanismo di plugin nella procedura ADAMS. Esporta i metodi virtuali definiti
  *nella @ref PluginImpl
  *@short Classe base plugin ADAMS
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class PluginBase
{
public:

	/** Typedef per la funzione worker del plugin */
	typedef bool ( *PluginWorker ) ( void );
	/** Typedef per la funzione di passaggio della configurazione */
	typedef void ( *SetupConfig ) ( void * );

	PluginBase();
	PluginBase ( const QString & pl_path );

	~PluginBase();

	/** Metodo per la registrazione dei metodi del plugin */
	bool registerPlugin ( const QString & plName, const QString & plPath, bool verbose = false );
	/** Questo method viene richiamato per ottenere un nuova istanza (polimorfica) della classe
	  * implementata dal plugin
	  */
	PluginImpl * getInstance();
	/** Imposta una directory di home per la ricerca dei plugin con path relativo */
	inline void setPluginHome ( const QString & pl_home ) {
		pluginPrivateHome = pl_home;
	}

private:
	QString pluginPrivateHome;	// alternate home for plugins
	void * handle;			// dlopen handler
	PluginWorker plWorker;		// worker method
	SetupConfig plSetup;		// setup worker
	QString pluginSoName;		// plugin .so file name
	PluginImpl * ( *dl_ptr ) ();	// plugin class instance factory
};




#endif
