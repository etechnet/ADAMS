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

#ifndef _CONFIGURETYPEDEFS_H
#define _CONFIGURETYPEDEFS_H

#include "userinterface.h"
#include "drinterface.h"
#include "dataelementinterface.h"
#include "exceptioninterface.h"
#include "relationinterface.h"
#include "analysisinterface.h"
#include "counterinterface.h"
#include "pluginregistryinterface.h"
#include "scriptinterface.h"
#include "reportschemainterface.h"
#include "thresholdgeneratorinterface.h"
#include "alarmgeneratorinterface.h"
#include "alarmrelationinterface.h"
#include "applogger.h"

#include <server_stub_safe_include.h>

/** Questa classe viene utilizzata per consentire la manipolazione globale dell'intera configurazione
  * della Matrice di Traffico. Data la complessità delle classi istanziate e l'assenza di operatori di copy-on-write
  * è consigliabile il passaggio della classe tramite puntatori. L'allocacazione delle sottoclassi si ottiene con il
  * method allocate() e lo stato dell'allocazione può essere testato con il method isNull().
  @short Shell di gestione configurazione ADAMS
  @author Piergiorgio Betti
  */
class AdamsCompleteConfig
{
public:
	AdamsCompleteConfig() :
		userInterface ( 0 ),
		drInterface ( 0 ),
		dataElementInterface ( 0 ),
		exceptionInterface ( 0 ),
		relationInterface ( 0 ),
		analysisInterface ( 0 ),
		counterInterface ( 0 ),
		pluginRegistryInterface ( 0 ),
		scriptInterface ( 0 ),
		reportInterface ( 0 ),
		thresholdGeneratorInterface ( 0 ),
		alarmGeneratorInterface ( 0 ),
		alarmRelationInterface ( 0 ),
		nullstatus ( true )
	{}

	~AdamsCompleteConfig() {
		if ( userInterface ) delete userInterface;
		if ( drInterface ) delete drInterface;
		if ( dataElementInterface ) delete dataElementInterface;
		if ( exceptionInterface ) delete exceptionInterface;
		if ( relationInterface ) delete relationInterface;
		if ( analysisInterface ) delete analysisInterface;
		if ( counterInterface ) delete counterInterface;
		if ( pluginRegistryInterface ) delete pluginRegistryInterface;
		if ( scriptInterface ) delete scriptInterface;
		if ( reportInterface ) delete reportInterface;
		if ( thresholdGeneratorInterface ) delete thresholdGeneratorInterface;
		if ( alarmGeneratorInterface ) delete alarmGeneratorInterface;
		if ( alarmRelationInterface ) delete alarmRelationInterface;
	}

	inline void allocate() {
		userInterface = new UsersInterface;
		drInterface = new DRInterface;
		dataElementInterface = new DataElementInterface;
		exceptionInterface = new ExceptionInterface;
		relationInterface = new RelationInterface;
		analysisInterface = new AnalysisInterface;
		counterInterface = new CounterInterface;
		pluginRegistryInterface = new PluginRegistryInterface;
		scriptInterface = new ScriptInterface;
		reportInterface = new ReportSchemaInterface;
		thresholdGeneratorInterface = new ThresholdGeneratorInterface;
		alarmGeneratorInterface = new AlarmGeneratorInterface;
		alarmRelationInterface = new AlarmRelationInterface;
		nullstatus = false;
	}

	AdamsCompleteConfig & operator= ( const AdamsCompleteConfig & in )
	{
		if (this == &in)
			return *this;

		*this->userInterface = *in.userInterface;
		*this->drInterface = *in.drInterface;
		*this->dataElementInterface = *in.dataElementInterface;
		*this->exceptionInterface = *in.exceptionInterface;
		*this->relationInterface = *in.relationInterface;
		*this->analysisInterface = *in.analysisInterface;
		*this->counterInterface = *in.counterInterface;
		*this->pluginRegistryInterface = *in.pluginRegistryInterface;
		*this->scriptInterface = *in.scriptInterface;
		*this->reportInterface = *in.reportInterface;
		*this->thresholdGeneratorInterface = *in.thresholdGeneratorInterface;
		*this->alarmGeneratorInterface = *in.alarmGeneratorInterface;
		*this->alarmRelationInterface = *in.alarmRelationInterface;

		return *this;
	}

	/** Test per lo stato di allocazione dei dati
	  @return True: non allocata
	  */
	inline bool isNull() {
		return nullstatus;
	}
	/** Forza la flag di allocazione dei dati. Utile nel caso le sottoclassi di interfaccia
	    vengano inizializzate manualmente
	  */
	inline void setNullStatus ( bool b ) {
		nullstatus = b;
	}

	UsersInterface			* userInterface;
	DRInterface			* drInterface;
	DataElementInterface		* dataElementInterface;
	ExceptionInterface		* exceptionInterface;
	RelationInterface		* relationInterface;
	AnalysisInterface		* analysisInterface;
	CounterInterface		* counterInterface;
	PluginRegistryInterface		* pluginRegistryInterface;
	ScriptInterface			* scriptInterface;
	ReportSchemaInterface		* reportInterface;
	ThresholdGeneratorInterface	* thresholdGeneratorInterface;
	AlarmGeneratorInterface		* alarmGeneratorInterface;
	AlarmRelationInterface		* alarmRelationInterface;
private:
	bool nullstatus;
};


/** Questa classe implementa un wrapper per semplificare le operazioni di creazione, copia e distruzione di un oggetto
  * ADAMS_COMPLETE, ovvero della copua dei dati di configurazione sulle corrispondenti sequenze CORBA.
  */
class ADAMS_COMPLETE_wrapper
{
public:
	ADAMS_COMPLETE_wrapper() {}
	~ADAMS_COMPLETE_wrapper() {}

	/** Inizializza sequenze CORBA interne da @ref AdamsCompleteConfig fornita in argomento */
	void fillFrom ( AdamsCompleteConfig * acc ) {
		fillFrom ( acc, &adams_complete_config );
	}

	/** Metodo per la copia esterna da @ref AdamsCompleteConfig a @ref ADAMS_COMPLETE_CONFIG
	    @param acc sorgente
	    @param crb destinazione
	  */
	static void fillFrom ( AdamsCompleteConfig * acc, ADAMS_COMPLETE_CONFIG * crb ) {
		if ( acc->isNull() ) return;
		acc->userInterface->copyToCorba ( &crb->userSequence );
		acc->drInterface->copyToCorba ( &crb->globalOptions );
		acc->drInterface->copyToCorba ( &crb->drSequence );
		acc->dataElementInterface->copyToCorba ( &crb->elementSequence );
		acc->relationInterface->copyToCorba ( &crb->relationSequence );
		acc->exceptionInterface->copyToCorba ( &crb->exceptionSequence );
		acc->analysisInterface->copyToCorba ( &crb->analisysSequence );
		acc->counterInterface->copyToCorba ( &crb->counterSequence );
		acc->pluginRegistryInterface->copyToCorba ( &crb->pluginRegistrySequence );
		acc->scriptInterface->copyToCorba ( &crb->scriptSequence );
		acc->reportInterface->copyToCorba ( &crb->reportSequence );
		acc->thresholdGeneratorInterface->copyToCorba ( &crb->thresholdGeneratorSequence );
		acc->alarmGeneratorInterface->copyToCorba ( &crb->alarmGeneratorSequence );
		acc->alarmRelationInterface->copyToCorba ( &crb->alarmRelationSequence );
	}

	/** Metodo per la copia esterna da @ref ADAMS_COMPLETE_CONFIG a @ref AdamsCompleteConfig
	    @param acc destinazione
	    @param crb sorgente
	  */
	static void copyTo ( AdamsCompleteConfig * acc, const ADAMS_COMPLETE_CONFIG * crb ) {
		if ( acc->isNull() ) return;
		acc->userInterface->fillFromCorba ( &crb->userSequence );
		acc->drInterface->fillFromCorba ( &crb->globalOptions );
		acc->drInterface->fillFromCorba ( &crb->drSequence );
		acc->dataElementInterface->fillFromCorba ( &crb->elementSequence );
		acc->relationInterface->fillFromCorba ( &crb->relationSequence );
		acc->exceptionInterface->fillFromCorba ( &crb->exceptionSequence );
		acc->analysisInterface->fillFromCorba ( &crb->analisysSequence );
		acc->counterInterface->fillFromCorba ( &crb->counterSequence );
		acc->pluginRegistryInterface->fillFromCorba ( &crb->pluginRegistrySequence );
		acc->scriptInterface->fillFromCorba ( &crb->scriptSequence );
		acc->reportInterface->fillFromCorba ( &crb->reportSequence );
		acc->thresholdGeneratorInterface->fillFromCorba ( &crb->thresholdGeneratorSequence );
		acc->alarmGeneratorInterface->fillFromCorba ( &crb->alarmGeneratorSequence );
		acc->alarmRelationInterface->fillFromCorba ( &crb->alarmRelationSequence );
	}

	/** Ritorna un puntatore alla classe CORBA ADAMS_COMPLETE allocata internamente */
	inline ADAMS_COMPLETE_CONFIG * reference() {
		return &adams_complete_config;
	}
private:
	ADAMS_COMPLETE_CONFIG adams_complete_config;
};


#endif
