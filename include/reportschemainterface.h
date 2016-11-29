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
#ifndef _REPORTSCHEMAINTERFACE_H_
#define _REPORTSCHEMAINTERFACE_H_

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <Qt/qlist.h>
#include <reportschemaobj.h>
#include <importexport.h>
#include <assert.h>


#include <server_stub_safe_include.h>

using namespace net::etech;


#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

#define REPORTSCHEMA_HEADERTAG			"REPORTSCHEMA_HEADER"
#define REPORTSCHEMA_MAXFIELDS			211

typedef QList<ReportSchemaObj>			RSOVector;
typedef RSOVector::iterator			RSOVectorIterator;

enum rfExportTypes {
        rfex_None,
        rfex_CSV,
        rfex_XLS,
        rfex_PDF
};

class ReportSchema  						// REPORTSCHEMA CLASS DEFINITION
{

public:
	int idReportSchema;						// id schema
	ReportSchemaObj objO;						// dati schema
	RSOVector hdrV;							// cell vector header
	RSOVector bdyV;							// cell vector body
	RSOVector totV;							// cell vector totalizer
	RSOVector fooV;							// cell vector footer

	ReportSchema() {
		init();
	}
	ReportSchema ( int id ) {
		init();
		idReportSchema = objO.data.idReportSchema = id;
	}
	ReportSchema ( const ReportSchema & r ) {
		init();
		idReportSchema = r.objO.data.idReportSchema;
		objO = r.objO;
	}
	~ReportSchema() {}

	void init()    {
		idReportSchema = -1;
		objO.init ( ReportSchemaObj::ObjectT );
		hdrV.clear();
		bdyV.clear();
		totV.clear();
		fooV.clear();
	}
	inline ReportSchema & operator= ( const ReportSchema & r ) {
		idReportSchema = r.idReportSchema;
		objO = r.objO;
		hdrV = r.hdrV;
		bdyV = r.bdyV;
		totV = r.totV;
		fooV = r.fooV;
		return *this;
	}
	inline void _init ( RSOVector * v, ReportSchemaObj::CellObject t ) {
		for ( int i = 0; i < v->count(); i++ ) ( *v ) [i].init ( t );
	}
	inline void assignId ( int id ) {
		idReportSchema = objO.data.idReportSchema = id;
		_assignOwnerId ( &hdrV, id );
		_assignOwnerId ( &bdyV, id );
		_assignOwnerId ( &totV, id );
		_assignOwnerId ( &fooV, id );
	}
	inline bool append ( ReportSchemaObj & o ) {
		if ( o.data.idObject == 0 ) o.data.idObject = getUnique ( o.data.type );
		switch ( o.data.type ) {
			case ReportSchemaObj::HeaderT:
				hdrV << o;
				break;
			case ReportSchemaObj::BodyT:
				bdyV << o;
				break;
			case ReportSchemaObj::TotalizerT:
				totV << o;
				break;
			case ReportSchemaObj::FooterT:
				fooV << o;
				break;
			default:
				return false;
		}
		return true;
	}
	inline bool remove ( ReportSchemaObj & o ) {
		int n = 0;
		switch ( o.data.type ) {
			case ReportSchemaObj::HeaderT:
				n = hdrV.removeAll ( o );
				break;
			case ReportSchemaObj::BodyT:
				n = bdyV.removeAll ( o );
				break;
			case ReportSchemaObj::TotalizerT:
				n = totV.removeAll ( o );
				break;
			case ReportSchemaObj::FooterT:
				n = fooV.removeAll ( o );
				break;
		}
		return ( n == 0 ) ? false : true;
	}

	inline int getUnique ( ReportSchemaObj::CellObject t ) {
		switch ( t ) {
			case ReportSchemaObj::HeaderT:
				return _getUnique ( &hdrV );
			case ReportSchemaObj::BodyT:
				return _getUnique ( &bdyV );
			case ReportSchemaObj::TotalizerT:
				return _getUnique ( &totV );
			case ReportSchemaObj::FooterT:
				return _getUnique ( &fooV );
			default:
				return -1;
		}
	}
private:
	inline int _getUnique ( RSOVector * v ) {
		int uniq = 0;
		for ( RSOVectorIterator it = v->begin(); it != v->end(); ++it ) {
			if ( ( *it ).data.idObject > uniq ) uniq = ( *it ).data.idObject;
		}
		return uniq + 1;
	}

	inline void _assignOwnerId ( RSOVector * v, int id ) {
		for ( RSOVectorIterator it = v->begin(); it != v->end(); ++it ) {
			( *it ).data.idReportSchema = id;
		}
	}
};

typedef QMultiHash<QString, ReportSchema *>::iterator		ReportSchemasIterator;
typedef QMultiHash<QString, ReportSchema *>::const_iterator	ReportSchemasConstIterator;

/**Questa classe implementa l'interfaccia verso le classi
  *di gestione dei ReportSchema
   @short Definizione report parametrici
  *@author Piergiorgio Betti
  */

class ReportSchemaInterface : public QObject
{
	Q_OBJECT
public:
	ReportSchemaInterface();
	~ReportSchemaInterface();

	/**
	  *  Inserisce uno schema nella hash table.
	**/
	bool add ( ReportSchema * newReportSchema );
	/**
	  *  Elimina uno schema nella hash table.
	**/
	bool remove ( int ReportSchemaNum );
	/**
	  * Estrae uno schema dalla hush table (per indirizzo)
	**/
	ReportSchema * get ( int ReportSchemaNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	ReportSchema * getByTag ( const QString & srcTag );

	/**
	  *  Ritorna il tag identificativo dello schema nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of ReportSchemas in the dictionary
	**/
	inline int count() {
		return ReportSchemas->count();
	}

	/** Ritorna il numero di record (ovvero di ReportSchemaObj) totali, rispetto
	  * alle strutture di ReportSchema
	  */
	inline long objCount() {
		long cnt = 0;
		ReportSchema * rs;
		foreach ( rs, *ReportSchemas ) {
			cnt += rs->fooV.count() + rs->totV.count() + rs->bdyV.count() + rs->hdrV.count() + 1;
		}
		return cnt;
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		ReportSchema * rs;
		foreach ( rs, *ReportSchemas ) {
			if ( rs->idReportSchema > uniq ) uniq = rs->idReportSchema;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	ReportSchemasIterator getIterator() {
		assert ( ReportSchemas != 0 );
		return ReportSchemas->begin();
	}

	/** Ritorna un iteratore alla lista */
	ReportSchemasIterator hashEnd() {
		assert ( ReportSchemas != 0 );
		return ReportSchemas->end();
	}

	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *ReportSchemas );
		ReportSchemas->clear();
		ReportSchemasNumber = uniq = 0;
	}

	ReportSchemaInterface & operator= (const ReportSchemaInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (ReportSchema * d, *in.ReportSchemas) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );
	void ieHandleObjWrite ( ImportExport & ie, ReportSchemaObj::CellObject t, int id, ReportSchemaObj & d );

	void copyToCorba ( ReportSchemaSeq * seqptr );
	void fillFromCorba ( const ReportSchemaSeq * seqptr );

private:
	QMultiHash<QString, ReportSchema *> * ReportSchemas;				/* Relazioni hush table */
	int ReportSchemasNumber;
	char pathname[PATH_MAX];						/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

	void copyToCorbaCommon ( ReportSchemaObj & rso, DATA_RS_COMMON & c, DATA_RS_HTML & html );
	void copyToCorbaObject ( DATA_RSO_OBJECT & objO, ReportSchemaObj & rso );
	void copyToCorbaHeader ( DATA_RSO_HEADER & hdrO, ReportSchemaObj & rso );
	void copyToCorbaBody ( DATA_RSO_BODY & bdyO, ReportSchemaObj & rso );
	void copyToCorbaTotalizer ( DATA_RSO_TOTALIZER & totO, ReportSchemaObj & rso );
	void copyToCorbaFooter ( DATA_RSO_FOOTER & fooO, ReportSchemaObj & rso );
	void fillFromCorbaCommon ( ReportSchemaObj & rso, const DATA_RS_COMMON & c, const DATA_RS_HTML & html );
	void fillFromCorbaObject ( const DATA_RSO_OBJECT & objO, ReportSchemaObj & rso );
	void fillFromCorbaHeader ( const DATA_RSO_HEADER & hdrO, ReportSchemaObj & rso );
	void fillFromCorbaBody ( const DATA_RSO_BODY & bdyO, ReportSchemaObj & rso );
	void fillFromCorbaTotalizer ( const DATA_RSO_TOTALIZER & totO, ReportSchemaObj & rso );
	void fillFromCorbaFooter ( const DATA_RSO_FOOTER & fooO, ReportSchemaObj & rso );

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
